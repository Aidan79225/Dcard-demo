package com.aidan.dcard

import android.app.Activity
import android.view.LayoutInflater

fun <T> Activity.viewBinding(initializer: (inflater: LayoutInflater) -> T): Lazy<T> = lazy {
    initializer(this.layoutInflater)
}
