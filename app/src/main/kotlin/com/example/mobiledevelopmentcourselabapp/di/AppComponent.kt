package com.example.mobiledevelopmentcourselabapp.di

import android.content.Context
import com.example.mobiledevelopmentcourselabapp.di.module.ApiModule
import com.example.mobiledevelopmentcourselabapp.presentation.view.article.view.ArticleFragment
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.CardFragment
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.ListFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Component(modules = [ApiModule::class])
@Singleton
interface AppComponent {

    //Fragments
    fun inject(fragment: CardFragment)
    fun inject(fragment: ListFragment)
    fun inject(fragment: ArticleFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}