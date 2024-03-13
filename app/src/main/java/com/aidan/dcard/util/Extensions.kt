package com.aidan.dcard.util

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater


fun <T> Activity.viewBinding(initializer: (inflater: LayoutInflater) -> T): Lazy<T> = lazy {
    initializer(this.layoutInflater)
}

fun getDensity(context: Context): Float {
    val metrics = context.resources.displayMetrics
    return metrics.density
}

fun Context.dp(dp: Float): Float {
    return dp * getDensity(this)
}