package com.jesusbadenas.kotlin_clean_architecture_project.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.jesusbadenas.kotlin_clean_architecture_project.common.UIUtils

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(imageUrl: String?) {
    UIUtils.loadImageUrl(context, this, imageUrl)
}
