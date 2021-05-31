package com.bangtiray.movietv.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.bangtiray.movietv.data.source.remote.api.ApiResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)

        @Suppress("LeakingThis")
        val dbSource = loadFromDB()

        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                val apiResponse = createCall()
                result.addSource(dbSource) {
                    result.value = Resource.loading(it)
                }
                result.addSource(apiResponse) { response ->
                    result.removeSource(apiResponse)
                    result.removeSource(dbSource)
                    when (response.status) {
                        Status.SUCCESS -> CoroutineScope(IO).launch {
                            response.body?.let { saveCallResult(it) }
                            withContext(Main) {
                                result.addSource(loadFromDB()) { newData ->
                                    result.value = Resource.success(newData)
                                }
                            }
                        }

                        else-> {
                            onFetchFailed()
                            result.addSource(dbSource) { newData ->
                                result.value = Resource.error(response.message!!, newData)
                            }
                        }
                    }
                }
            } else {
                result.addSource(dbSource) { newData ->
                    result.value = Resource.success(newData)
                }
            }
        }
    }


    private fun onFetchFailed() {}

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)

    fun asLiveData(): LiveData<Resource<ResultType>> = result
}
