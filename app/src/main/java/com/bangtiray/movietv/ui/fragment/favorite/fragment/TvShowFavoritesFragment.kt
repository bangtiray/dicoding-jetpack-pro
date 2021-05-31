package com.bangtiray.movietv.ui.fragment.favorite.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.bangtiray.movietv.data.source.local.entity.MoviesEntity
import com.bangtiray.movietv.databinding.FragmentTvFavoritesBinding
import com.bangtiray.movietv.ui.detail.DetailActivity
import com.bangtiray.movietv.ui.fragment.MainViewModel
import com.bangtiray.movietv.ui.fragment.movies.ClickCallback
import com.bangtiray.movietv.ui.fragment.movies.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TvMovieFavoritesFragment : Fragment(), ClickCallback {
    private lateinit var binding: FragmentTvFavoritesBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.getFavoriteTV().observe(viewLifecycleOwner, {
            if (it != null) {
                binding.populateView.favRecyclerView.adapter?.let { ad ->
                    when (ad) {
                        is MoviesAdapter -> {
                            if (it.isNullOrEmpty()) {
                                binding.populateView.emptyStateView.root.visibility = VISIBLE
                                binding.populateView.favRecyclerView.visibility = GONE
                            } else {
                                binding.populateView.favRecyclerView.visibility = VISIBLE
                                ad.submitList(it)
                                ad.notifyDataSetChanged()
                            }
                        }
                    }
                }
            }
        })
    }

    private fun setupRecyclerView() {

        binding.populateView.favRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = MoviesAdapter(this@TvMovieFavoritesFragment)
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