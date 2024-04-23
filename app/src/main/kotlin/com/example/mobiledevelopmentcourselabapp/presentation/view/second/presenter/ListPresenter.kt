package com.example.mobiledevelopmentcourselabapp.presentation.view.second.presenter

import android.media.MediaPlayer
import com.example.mobiledevelopmentcourselabapp.data.model.PlayerDbEntity
import com.example.mobiledevelopmentcourselabapp.domain.interactor.PlayerInteractor
import com.example.mobiledevelopmentcourselabapp.presentation.view.common.presenter.BasePresenter
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.ListMvpView
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.model.PlayerUiModel
import javax.inject.Inject

class ListPresenter @Inject constructor(
    private val interactor: PlayerInteractor,
    private val mediaPlayer: MediaPlayer
): BasePresenter<ListMvpView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        mediaPlayer.prepareAsync()
        update()
    }

    fun update() {
        interactor.getAll()
            .doAsync()
            .map { mapPlayers(it) }
            .subscribe(viewState::showPlayers, viewState::showError)
            .disposeOnDestroy()
    }

    private fun mapPlayers(dbList: List<PlayerDbEntity>): List<PlayerUiModel> {
        return dbList.map { dbPlayer ->
            PlayerUiModel(
                name = dbPlayer.name,
                number = dbPlayer.number,
                position = dbPlayer.position,
                photoUri = dbPlayer.avatarUri.toString()
            )
        }
    }
}