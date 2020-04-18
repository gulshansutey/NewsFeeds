package com.gulshansutey.newsfeeds.utils

import android.app.AlertDialog
import android.content.Context


/**
 * Util object holds all the alert related static methods
 * */
object DialogUtils {


    /**
     * Creates an alert dialog that uses the default alert
     * dialog theme.
     *
     * @param context associated parent context
     * @param message message to be shown in alert dialog
     * */
    fun showAlertDialog(context: Context, message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Alert")
        builder.setMessage(message)
        builder.setPositiveButton("Ok") { dialog, _ ->
            dialog.cancel()
        }
        builder.create().show()
    }

}