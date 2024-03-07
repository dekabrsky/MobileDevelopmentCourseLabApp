package com.example.mobiledevelopmentcourselabapp.presentation.view.second

import com.example.mobiledevelopmentcourselabapp.presentation.view.second.model.ItemUiModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.model.PlayerUiModel
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface ListMvpView: MvpView {
    fun showPlayers(players: List<ItemUiModel>)
}