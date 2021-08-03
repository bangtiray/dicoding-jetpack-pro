@file:Suppress("DEPRECATION")

package com.bangtiray.movietv.ui.fragment.favorite

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.bangtiray.movietv.R
import com.bangtiray.movietv.ui.fragment.favorite.fragment.MovieFavoritesFragment
import com.bangtiray.movietv.ui.fragment.favorite.fragment.TvMovieFavoritesFragment

@Suppress("DEPRECATION")
class SectionPagerAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.tab_title_movie, R.string.tab_title_tvshow)
    }

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> MovieFavoritesFragment()
            else -> TvMovieFavoritesFragment()
        }

    override fun getPageTitle(position: Int): CharSequence = mContext.resources.getString(
        TAB_TITLES[position]
    )


}