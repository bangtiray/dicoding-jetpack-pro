package com.bangtiray.movietv.ui.fragment.movies

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.bangtiray.movietv.data.source.Status
import com.bangtiray.movietv.data.source.local.entity.MoviesEntity
import com.bangtiray.movietv.databinding.FragmentMoviesBinding
import com.bangtiray.movietv.ui.detail.DetailActivity
import com.bangtiray.movietv.ui.fragment.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MoviesFragment : Fragment(), ClickCallback {
    private lateinit var binding: FragmentMoviesBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.getPopularMovies().observe(viewLifecycleOwner, { movies ->
            if (movies != null) {
                when (movies.status) {
                    Status.LOADING -> binding.progressBar.isVisible = true
                    Status.SUCCESS -> {
                        binding.progressBar.isVisible = false
                        binding.populateView.recyclerView.adapter?.let { ad ->
                            when (ad) {
                                is MoviesAdapter -> {
                                    ad.submitList(movies.data)
                                    ad.notifyDataSetChanged()
                                }
                            }
                        }

                    }
                    Status.ERROR -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(
                            context,
                            "Check your internet connection",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
    }

    private fun setupRecyclerView() {

        binding.populateView.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = MoviesAdapter(this@MoviesFragment)
        }
    }

    override fun onItemClicked(data: MoviesEntity) {
        Log.i("test Data", data.toString())
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_MOVIE, data.mId)
        intent.putExtra(DetailActivity.EXTRA_CATEGORY, data.mType)
        startActivity(intent)
    }


}