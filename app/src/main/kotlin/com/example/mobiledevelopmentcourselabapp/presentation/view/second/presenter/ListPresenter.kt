package com.example.mobiledevelopmentcourselabapp.presentation.view.second.presenter

import com.example.mobiledevelopmentcourselabapp.presentation.view.list.generator.Generator
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.CardMvpView
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.ListMvpView
import moxy.MvpPresenter
import javax.inject.Inject

class ListPresenter @Inject constructor(): MvpPresenter<ListMvpView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        val players = Generator.generate()
        viewState.showPlayers(players)
    }
}