package com.android.chinewsfeed.util

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.android.chinewsfeed.R


object NewFeedDialogsUtils {


    fun showErrorMessageAlert(context: Context, title: String, message: String, clickListener: DialogInterface.OnClickListener) {

        AlertDialog.Builder(context).setTitle(title).setMessage(message).setCancelable(false).setNegativeButton("Ok", clickListener).create().show()
    }

    fun showRetryMessage(context: Context, title: String, message: String, onClickListener: DialogInterface.OnClickListener) {
        android.support.v7.app.AlertDialog.Builder(context).setTitle(title).setMessage(message)
                .setPositiveButton(context.getString(R.string.rety_text), onClickListener)
                .setNegativeButton(context.getString(R.string.cancel_text)) { dialog, _ ->
                    dialog.dismiss()
                }.setCancelable(false)
                .create().show()
    }
}