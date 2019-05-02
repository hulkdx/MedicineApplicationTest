package com.hulkdx.medicine.di.components


import android.app.Application
import android.content.Context
import javax.inject.Singleton

import dagger.Component
import com.hulkdx.medicine.di.ApplicationContext
import com.hulkdx.medicine.di.modules.ApplicationModule
import com.hulkdx.medicine.di.modules.NetworkModule
import hulkdx.com.domain.interactor.MedicineUseCase

/**
 * Created by Mohammad Jafarzadeh Rezvan on 09/11/2018.
 */
@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {

    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun getGithubRepositoryList(): MedicineUseCase
}
