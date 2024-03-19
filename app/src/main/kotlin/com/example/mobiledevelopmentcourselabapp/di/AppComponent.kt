package com.example.mobiledevelopmentcourselabapp.di

import android.content.Context
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.CardFragment
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.ListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component
@Singleton
interface AppComponent {

    //Fragments
    fun inject(fragment: CardFragment)
    fun inject(fragment: ListFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}