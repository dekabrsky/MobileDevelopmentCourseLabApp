package com.example.mobiledevelopmentcourselabapp.presentation.view.article.view

import com.example.mobiledevelopmentcourselabapp.presentation.view.common.view.BaseMvpView
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

@AddToEndSingle
interface ArticleView: BaseMvpView {
    fun setScore(score: String)
    fun setJoke(joke: String)
    fun setJokeTime(formatDateTimeToUiDateTime: String)
    fun setupSpinner(categories: List<String>)
    fun setSelectedCategory(index: Int?)
    fun setJokeNinja(joke: String)
    @OneExecution
    fun openWebView(link: String)
}