package com.example.mobiledevelopmentcourselabapp.presentation.view.common.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface BaseMvpView: MvpView {
    fun setLoadingVisibility(isVisible: Boolean)
    fun showError(throwable: Throwable)
}