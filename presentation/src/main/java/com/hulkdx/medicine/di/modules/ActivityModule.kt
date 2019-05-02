package com.hulkdx.medicine.di.modules

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import dagger.Module
import dagger.Provides
import com.hulkdx.medicine.di.ActivityContext

/**
 * Created by Mohammad Jafarzadeh Rezvan on 09/11/2018.
 */

@Module
class ActivityModule(private val mActivity: Activity) {

    @Provides
    fun provideActivity(): Activity = mActivity

    @Provides
    @ActivityContext
    fun providesContext(): Context = mActivity
}
