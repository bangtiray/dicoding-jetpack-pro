package com.bangtiray.movietv.extension

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageButton
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bangtiray.movietv.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey

private const val FADE_DURATION = 350

fun ImageView.loadFromUrl(
    strUrl:String,
    colorInt: Int? = null,
    colorString: String? = null,
){
    colorInt?.let { background = ColorDrawable(it) }
    colorString?.let { background = ColorDrawable(Color.parseColor(it)) }
    Glide.with(context)
        .load(strUrl)
        .placeholder(R.drawable.ic_fluent_image_28_regular)
        .error(R.drawable.ic_fluent_dual_screen_error_24_regular)
        .transition(DrawableTransitionOptions.withCrossFade(FADE_DURATION))
        .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(16)))
        .into(this)
        .clearOnDetach()
}


fun ImageButton.setDrawableFavorite(isFavorite: Boolean) {
    this.setImageResource(if (isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border)
}