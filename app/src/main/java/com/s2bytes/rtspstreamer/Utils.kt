package com.s2bytes.rtspstreamer

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.annotation.StringRes
import org.videolan.libvlc.Media

fun Context.goToLink(link: String) {
    if(link.isBlank()) return
    try {
        startActivity(
            Intent(Intent.ACTION_VIEW, Uri.parse(link)).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        )
    }
    catch (ex: ActivityNotFoundException) { makeToast("Some error occured!") }
}

fun Context.makeToast(message:String, isShort:Boolean = true) = Toast.makeText(
    this, message,
    if(isShort) Toast.LENGTH_SHORT
    else Toast.LENGTH_LONG
).show()
fun Context.makeToast(@StringRes message: Int, isShort:Boolean = true) = Toast.makeText(
    this, message,
    if(isShort) Toast.LENGTH_SHORT
    else Toast.LENGTH_LONG
).show()

fun <T> MutableList<T>.add(vararg values: T) = addAll(values)
fun Media.addOptions(vararg options: String) = options.forEach { addOption(it) }