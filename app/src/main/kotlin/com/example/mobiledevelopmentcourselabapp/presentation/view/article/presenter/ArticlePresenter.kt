package com.example.mobiledevelopmentcourselabapp.presentation.view.article.presenter

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder
import com.example.mobiledevelopmentcourselabapp.domain.interactor.ChuckInteractor
import com.example.mobiledevelopmentcourselabapp.presentation.view.article.view.ArticleView
import com.example.mobiledevelopmentcourselabapp.presentation.view.common.presenter.BasePresenter
import com.example.mobiledevelopmentcourselabapp.utils.formatDateTimeToUiDateTime
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ArticlePresenter @Inject constructor(
    private val chuckInteractor: ChuckInteractor,
    private val context: Context
): BasePresenter<ArticleView>() {

    private val prefs = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
    private val store = RxPreferenceDataStoreBuilder(context, APP_PREFERENCES).build()

    private var score: Int = 0
        set(value) {
            viewState.setScore(field.toString())
            field = value
        }

    private val key = stringPreferencesKey(CATEGORY_KEY)

    private var categories: List<String> = emptyList()
    private var category: String = ""
        //prefs.getString(CATEGORY_KEY, "").orEmpty()
    private var link: String? = null

    override fun onFirstViewAttach() {
        store.data()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ prefs ->
                category = prefs.get(key).orEmpty()
                updateSelectedCategory()
            }, viewState::showError)
            .disposeOnDestroy()

        chuckInteractor.getCategories()
            .doAsync()
            .withLoadingView(viewState)
            .subscribe({
                categories = it
                viewState.setupSpinner(it)
                updateSelectedCategory()
            }, viewState::showError)
            .disposeOnDestroy()
    }

    override fun attachView(view: ArticleView?) {
        super.attachView(view)
        updateSelectedCategory()
    }

    private fun updateSelectedCategory() {
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
//        with (prefs.edit()) {
//            putString(CATEGORY_KEY, category)
//            apply()
//        }

        store.updateDataAsync { prefsIn ->
            val mutablePreferences = prefsIn.toMutablePreferences()
            mutablePreferences.set(key, category)
            Single.just(mutablePreferences)
        }.subscribe()
    }

    fun onLikeClicked() = score++

    fun onDislikeClicked() = score--

    fun onOpenLinkClicked() {
        link?.let { viewState.openWebView(it) }
    }

    companion object {
        private const val APP_PREFERENCES = "APP_PREFERENCES"
        private const val CATEGORY_KEY = "CATEGORY_KEY"
    }
}