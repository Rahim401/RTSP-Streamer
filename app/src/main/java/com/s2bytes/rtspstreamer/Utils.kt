package com.s2bytes.rtspstreamer

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.core.text.HtmlCompat
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

fun Spanned.toAnnotatedString(): AnnotatedString = buildAnnotatedString {
    val spanned = this@toAnnotatedString
    append(spanned.toString())
    getSpans(0, spanned.length, Any::class.java).forEach { span ->
        val start = getSpanStart(span)
        val end = getSpanEnd(span)
        when (span) {
            is StyleSpan -> when (span.style) {
                Typeface.BOLD -> addStyle(SpanStyle(fontWeight = FontWeight.Bold), start, end)
                Typeface.ITALIC -> addStyle(SpanStyle(fontStyle = FontStyle.Italic), start, end)
                Typeface.BOLD_ITALIC -> addStyle(SpanStyle(fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic), start, end)
            }
            is UnderlineSpan -> addStyle(SpanStyle(textDecoration = TextDecoration.Underline), start, end)
            is ForegroundColorSpan -> addStyle(SpanStyle(color = Color(span.foregroundColor)), start, end)
        }
    }
}
fun String.fromHtml(): AnnotatedString = HtmlCompat.fromHtml(
    this, HtmlCompat.FROM_HTML_MODE_COMPACT
).toAnnotatedString()
fun String.toBoldHS() = "<b>${this}</b>"