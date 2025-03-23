package com.example.besinkitabi.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.besinkitabi.R
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import androidx.palette.graphics.Palette

// Görseli indirip ImageView'e yüklemek için
fun ImageView.gorselIndir(url: String?, placeHolder: CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(placeHolder)
        .error(R.mipmap.ic_launcher_round)
        .transform(RoundedCorners(100)) // Resmi yuvarlaklaştırmak için
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}

// Resmin dominant rengini almak için
fun getDominantColorFromImage(context: Context, url: String?, callback: (Int) -> Unit) {
    Glide.with(context)
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                // Palette API ile dominant rengi alıyoruz
                Palette.from(resource).generate { palette ->
                    // Eğer dominant renk varsa, onu callback'e gönderiyoruz
                    val dominantColor = palette?.getDominantColor(Color.BLACK) ?: Color.BLACK
                    callback(dominantColor)
                }
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })
}

// CircularProgressDrawable oluşturma fonksiyonu
fun placeHolderYap(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}
