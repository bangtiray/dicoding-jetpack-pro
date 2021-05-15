package com.bangtiray.movietv.ui.fragment.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bangtiray.movietv.R
import com.bangtiray.movietv.data.MovieEntity
import com.bangtiray.movietv.databinding.FragmentMoviesBinding
import com.bangtiray.movietv.ui.adapter.ClickListener
import com.bangtiray.movietv.ui.adapter.MovieAdapter
import com.bangtiray.movietv.ui.detail.DetailActivity
import com.bangtiray.movietv.ui.fragment.ViewModel

class MoviesFragment : Fragment(), ClickListener {
    private lateinit var binding: FragmentMoviesBinding
    private lateinit var adapter: MovieAdapter
    private lateinit var viewModel:ViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MovieAdapter(this)
        activity.let{
            viewModel=ViewModelProvider(it!!,ViewModelProvider.NewInstanceFactory())[ViewModel::class.java]
        }
        populateRecyclerView(viewModel.genListMovie())
    }

    private fun populateRecyclerView(list: List<MovieEntity>) {
        with(binding) {
            progressBar.isVisible = false
            adapter.setData(list)
            populateView.recyclerView.layoutManager = GridLayoutManager(context, 2)
            populateView.recyclerView.setHasFixedSize(true)
            populateView.recyclerView.adapter = adapter

        }
    }

    override fun onCLick(position: Int, movieId: String) {

        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_MOVIE, movieId)
        intent.putExtra(DetailActivity.EXTRA_CATEGORY, resources.getString(R.string.movies))
        startActivity(intent)
    }


}