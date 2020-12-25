package id.asistem.pareid.utils

import android.content.Context
import android.view.View
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.snackbar.Snackbar
import de.hdodenhof.circleimageview.CircleImageView
import id.asistem.pareid.PareId.Companion.db
import id.asistem.pareid.PareId.Companion.prefManager
import lecho.lib.hellocharts.view.LineChartView

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.snackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("OK") {
            snackbar.dismiss()
        }
    }.show()
}

fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun ProgressBar.hide() {
    visibility = View.GONE
}

fun Button.show() {
    visibility = View.VISIBLE
}

fun Button.hide() {
    visibility = View.GONE
}

fun AppCompatButton.show() {
    visibility = View.VISIBLE
}

fun AppCompatButton.hide() {
    visibility = View.INVISIBLE
}

fun TextView.show() {
    visibility = View.VISIBLE
}

fun TextView.hide() {
    visibility = View.GONE
}

fun ConstraintLayout.show() {
    visibility = View.VISIBLE
}

fun ConstraintLayout.hide() {
    visibility = View.GONE
}

fun LinearLayout.show() {
    visibility = View.VISIBLE
}

fun LinearLayout.hide() {
    visibility = View.GONE
}

fun ImageView.show() {
    visibility = View.VISIBLE
}

fun ImageView.hide() {
    visibility = View.GONE
}

fun CircleImageView.show() {
    visibility = View.VISIBLE
}

fun CircleImageView.hide() {
    visibility = View.GONE
}

fun SwipeRefreshLayout.show() {
    visibility = View.VISIBLE
}

fun SwipeRefreshLayout.hide() {
    visibility = View.GONE
}

fun RecyclerView.show() {
    visibility = View.VISIBLE
}

fun RecyclerView.hide() {
    visibility = View.GONE
}

fun ShimmerFrameLayout.show() {
    visibility = View.VISIBLE
}

fun ShimmerFrameLayout.hide() {
    visibility = View.GONE
}

fun LineChartView.show() {
    visibility = View.VISIBLE
}

fun LineChartView.hide() {
    visibility = View.GONE
}





