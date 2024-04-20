package com.example.mobiledevelopmentcourselabapp.presentation.view.second.presenter

import android.net.Uri
import androidx.core.os.bundleOf
import com.example.mobiledevelopmentcourselabapp.R
import com.example.mobiledevelopmentcourselabapp.domain.interactor.PlayerInteractor
import com.example.mobiledevelopmentcourselabapp.domain.model.PlayerPosition
import com.example.mobiledevelopmentcourselabapp.presentation.view.common.presenter.BasePresenter
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.EditView
import com.example.mobiledevelopmentcourselabapp.utils.orZero
import javax.inject.Inject

class EditPresenter @Inject constructor(
    private val interactor: PlayerInteractor
): BasePresenter<EditView>() {

    private var uri: Uri? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.checkFilesPermission()
    }

    fun onDoneClicked(name: String, number: String, position: Int) {
        interactor.addPlayer(
            name = name,
            number = number.toIntOrNull().orZero(),
            position = when (position) {
                R.id.variantGoalkeeper -> PlayerPosition.GOALKEEPER
                R.id.variantDefender -> PlayerPosition.DEFENDER
                R.id.variantMidfielder -> PlayerPosition.MIDFIELD
                R.id.variantForward -> PlayerPosition.FORWARD
                else -> PlayerPosition.NONE
            },
            uri = uri
        )
            .doAsync()
            .subscribe({ backWithResult(bundleOf(NEED_TO_UPDATE to true)) }, viewState::showError)
            .disposeOnDestroy()
    }

    fun onImageSelected(uri: Uri?) {
        uri?.let {
            this.uri = uri
            viewState.setAvatar(uri)
        }
    }

    companion object {
        const val NEED_TO_UPDATE = "NEED_TO_UPDATE"
    }
}