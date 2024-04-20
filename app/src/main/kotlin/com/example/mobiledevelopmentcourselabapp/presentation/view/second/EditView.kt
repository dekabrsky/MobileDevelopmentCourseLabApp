package com.example.mobiledevelopmentcourselabapp.presentation.view.second

import android.net.Uri
import com.example.mobiledevelopmentcourselabapp.presentation.view.common.view.BaseMvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface EditView: BaseMvpView {
    fun setAvatar(uri: Uri)
    fun checkFilesPermission()
}