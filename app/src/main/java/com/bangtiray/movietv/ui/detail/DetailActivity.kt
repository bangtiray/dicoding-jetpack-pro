package com.bangtiray.movietv.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import com.bangtiray.movietv.R
import com.bangtiray.movietv.data.source.local.entity.MoviesEntity
import com.bangtiray.movietv.databinding.ActivityDetailBinding
import com.bangtiray.movietv.extension.getStringDate
import com.bangtiray.movietv.extension.loadFromUrl
import com.bangtiray.movietv.extension.setDrawableFavorite
import com.bangtiray.movietv.ui.fragment.MainViewModel
import com.bangtiray.movietv.utils.ConstantValue
import com.bangtiray.wvhelper.webview.src.Bangtiray
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_CATEGORY = "extra_category"
    }

    //
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: MainViewModel by viewModels()
    private var mode = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val extras = intent.extras

        if (extras != null) {
            val movieID = extras.getInt(EXTRA_MOVIE)
            mode = extras.getString(EXTRA_CATEGORY).toString()
            viewModel.getDetailData(movieID).observe(this, { setDetailInfo(it) })


        }
        binding.layoutDetail.toolbar.setNavigationOnClickListener { finish() }
    }

    @SuppressLint("SetTextI18n")
    private fun setDetailInfo(me: MoviesEntity) {
        binding.layoutDetail.backDrop.loadFromUrl("${ConstantValue.imageUrl}${me.mBackDropPath}")
        binding.layoutDetail.kenBurn.loadFromUrl("${ConstantValue.imageUrl}${me.mPosterPath}")
        binding.layoutDetail.movieTitle.text = me.mTitle
        binding.layoutDetail.releaseDate.text = "Release Date :${getStringDate(me.releaseDate)}"
        binding.layoutDetail.textOverview.text = me.mOverView
        binding.layoutDetail.textPopularity.text = me.mPopularity.toString()
        binding.layoutDetail.ratingBar.rating = me.mVoteAverage
        binding.layoutDetail.voteCount.text = "${me.mVoteAverage}%"
        binding.layoutDetail.favoriteButton.setDrawableFavorite(me.isFavorite)

        binding.layoutDetail.favoriteButton.run {
            setOnClickListener {
                viewModel.setFavoriteMovie(me, !me.isFavorite)
            }
        }

        binding.layoutDetail.imgBrowser.run {
            setOnClickListener {
                when (mode) {
                    resources.getString(R.string.movies) -> Bangtiray.showWebView(
                        this@DetailActivity,
                        "${ConstantValue.mainUrl}movie/${me.mId}",
                        "",
                        true
                    )
                    else -> Bangtiray.showWebView(
                        this@DetailActivity,
                        "${ConstantValue.mainUrl}tv/${me.mId}",
                        "",
                        true
                    )
                }
            }
        }
        binding.layoutDetail.imgShare.run {
            setOnClickListener {
                val mimeType = "text/plain"
                ShareCompat.IntentBuilder
                    .from(this@DetailActivity)
                    .setType(mimeType)
                    .setChooserTitle("Share Now.")
                    .setText(
                        "$mode : '${me.mTitle}' you can find only On TMDB, Click Me for detail " +
                                when (mode) {
                                    resources.getString(R.string.movies) -> "${ConstantValue.mainUrl}movie/${me.mId}"
                                    else -> "${ConstantValue.mainUrl}tv/${me.mId}"
                                }
                    )
                    .startChooser()
            }
        }
    }
}