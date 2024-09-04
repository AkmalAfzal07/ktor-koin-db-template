package com.example.koinstructure.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.koinstructure.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object Utils {

    fun hasInternetConnection(context: Context?): Boolean {
        try {
            if (context == null)
                return false
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } catch (e: Exception) {
            return false
        }
    }

    fun Context.setLoadingDialog(dialog: Dialog?, shouldShow: Boolean): Dialog? {
        return if (shouldShow) {
            // Show the loading dialog
            dialog ?: try {
                val newDialog = Dialog(this)
                val inflate = LayoutInflater.from(this).inflate(R.layout.dialog_processing, null)
                newDialog.setContentView(inflate)
                newDialog.setCancelable(false)
                newDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                newDialog.show()
                newDialog
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        } else {
            // Hide the loading dialog
            dialog?.hideLoadingDialog()
            null
        }
    }

    private fun Dialog?.hideLoadingDialog() {
        this?.let {
            if (it.isShowing && it.window != null) {
                try {
                    it.dismiss()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    inline fun Fragment.launchAndRepeatWithViewLifecycle(
        minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
        crossinline block: suspend CoroutineScope.() -> Unit
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(minActiveState) {
                block()
            }
        }
    }
}