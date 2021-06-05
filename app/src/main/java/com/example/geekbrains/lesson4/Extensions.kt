package com.example.geekbrains.lesson4

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.replaceWithBackStack(cont: Int, fragment: Fragment) {
    requireActivity()
        .supportFragmentManager
        .beginTransaction()
        .addToBackStack(this.tag)
        .replace(cont, fragment)
        .commitAllowingStateLoss()
}

fun View.showSnackBar(
    text: String,
    actionText: String,
    action: (View) -> Unit,
    length: Int = Snackbar.LENGTH_INDEFINITE
) {
    Snackbar.make(this, text, length).setAction(actionText, action).show()
}