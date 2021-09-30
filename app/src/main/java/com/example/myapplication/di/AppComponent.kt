package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.di.module.BindsModule
import com.example.myapplication.di.module.NetworkModule
import com.example.myapplication.di.module.ViewModelModule

import dagger.BindsInstance
import dagger.Component
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
@Component(modules =
    [
        BindsModule::class,
        NetworkModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : InjectFragments {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}