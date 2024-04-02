package com.example.mobiledevelopmentcourselabapp.presentation.view.article.presenter

import com.example.mobiledevelopmentcourselabapp.domain.interactor.ChuckInteractor
import com.example.mobiledevelopmentcourselabapp.presentation.view.article.view.ArticleView
import com.example.mobiledevelopmentcourselabapp.presentation.view.common.presenter.BasePresenter
import com.example.mobiledevelopmentcourselabapp.utils.formatDateTimeToUiDateTime
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ArticlePresenter @Inject constructor(
    private val chuckInteractor: ChuckInteractor
): BasePresenter<ArticleView>() {

    private var score: Int = 0
        set(value) {
            viewState.setScore(field.toString())
            field = value
        }

    private var categories: List<String> = emptyList()
    private var category = ""
    private var link: String? = null

    override fun onFirstViewAttach() {
        chuckInteractor.getCategories()
            .doAsync()
            .withLoadingView(viewState)
            .subscribe({
                categories = it
                viewState.setupSpinner(it)
            }, viewState::showError)
            .disposeOnDestroy()
    }

    override fun attachView(view: ArticleView?) {
        super.attachView(view)
        viewState.setSelectedCategory(categories.indexOf(category).takeIf { it > 0 })
    }

    fun onLoadClicked() {
        chuckInteractor.getJokeByCategory(category)
            .doAsync()
            .withLoadingView(viewState)
            .subscribe({
                viewState.setJoke(it.value)
                viewState.setJokeTime(formatDateTimeToUiDateTime(it.updateTime))
                link = it.link
            }, viewState::showError)
            .disposeOnDestroy()

        chuckInteractor.getJokeFromNinja()
            .doAsync()
            .withLoadingView(viewState)
            .subscribe({ viewState.setJokeNinja(it.joke) }, viewState::showError)
            .disposeOnDestroy()
    }

    fun onCategorySelected(item: String) {
        category = item
    }

    fun onLikeClicked() = score++

    fun onDislikeClicked() = score--

    fun onOpenLinkClicked() {
        link?.let { viewState.openWebView(it) }
    }
}