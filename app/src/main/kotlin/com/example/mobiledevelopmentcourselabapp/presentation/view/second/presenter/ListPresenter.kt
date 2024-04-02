package com.example.mobiledevelopmentcourselabapp.presentation.view.second.presenter

import com.example.mobiledevelopmentcourselabapp.data.model.PlayerDbEntity
import com.example.mobiledevelopmentcourselabapp.domain.interactor.PlayerInteractor
import com.example.mobiledevelopmentcourselabapp.presentation.view.common.presenter.BasePresenter
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.generator.Generator
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.CardMvpView
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.ListMvpView
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.model.PlayerUiModel
import moxy.MvpPresenter
import javax.inject.Inject

class ListPresenter @Inject constructor(
    private val interactor: PlayerInteractor
): BasePresenter<ListMvpView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

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
                position = dbPlayer.position
            )
        }
    }
}