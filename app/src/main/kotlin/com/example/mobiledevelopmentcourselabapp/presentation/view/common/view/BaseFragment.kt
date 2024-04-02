package com.example.mobiledevelopmentcourselabapp.presentation.view.common.view

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import com.example.mobiledevelopmentcourselabapp.R
import moxy.MvpAppCompatFragment

open class BaseFragment: MvpAppCompatFragment(), BaseMvpView {

    private var loadingDialog: Dialog? = null

    override fun setLoadingVisibility(isVisible: Boolean) {
        if (isVisible) {
            if (loadingDialog != null) return
            loadingDialog =
                AlertDialog.Builder(requireActivity())
                    .setMessage(getString(R.string.loading))
                    .setCancelable(false)
                    .create()
            loadingDialog?.show()
        } else {
            loadingDialog?.dismiss()
            loadingDialog = null
        }
    }

    override fun showError(throwable: Throwable) {
        context?.let { Toast.makeText(it, throwable.localizedMessage, Toast.LENGTH_LONG).show() }
    }

    override fun back() {
        view?.findNavController()?.navigateUp()
    }

    override fun backWithResult(bundle: Bundle) {
        setFragmentResult(DEFAULT_RESULT_KEY, bundle)
        back()
    }

    companion object {
        @JvmStatic
        protected val DEFAULT_RESULT_KEY = "DEFAULT_RESULT_KEY"
    }
}