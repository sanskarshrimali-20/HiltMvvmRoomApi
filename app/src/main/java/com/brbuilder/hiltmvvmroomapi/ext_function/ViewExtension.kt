package com.brbuilder.hiltmvvmroomapi.ext_function

import android.view.View

fun View.hide() {
    this.visibility = View.GONE
}
fun View.invisible() {
    this.visibility = View.INVISIBLE
}
fun View.show() {
    this.visibility = View.VISIBLE
}