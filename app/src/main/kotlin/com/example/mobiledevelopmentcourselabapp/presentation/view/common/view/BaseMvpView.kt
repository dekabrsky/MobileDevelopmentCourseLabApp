package com.example.mobiledevelopmentcourselabapp.presentation.view.common.view

import android.os.Bundle
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface BaseMvpView: MvpView {
    fun setLoadingVisibility(isVisible: Boolean)
    fun showError(throwable: Throwable)
    fun back()
    fun backWithResult(bundle: Bundle)
}