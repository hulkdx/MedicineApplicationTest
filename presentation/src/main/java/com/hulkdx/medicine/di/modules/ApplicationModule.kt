package com.hulkdx.medicine.di.modules

import android.app.Application
import android.content.Context

import dagger.Module
import dagger.Provides
import com.hulkdx.medicine.di.ApplicationContext
import com.hulkdx.medicine.executor.UiThread
import hulkdx.com.domain.executor.CustomThreadExecutor
import hulkdx.com.domain.executor.PostExecutionThread
import hulkdx.com.domain.executor.ThreadExecutor
import hulkdx.com.domain.repository.MedicineRepository
import hulkdx.com.data.repository.MedicineRepositoryImpl

/**
 * Created by Mohammad Jafarzadeh Rezvan on 09/11/2018.
 */
@Module
class ApplicationModule(private val mApplication: Application) {

    @Provides
    fun provideApplication(): Application = mApplication

    @Provides
    @ApplicationContext
    fun provideContext(): Context = mApplication

    @Provides
    fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread = uiThread

    @Provides
    fun provideThreadExecutor(customThreadExecutor: CustomThreadExecutor): ThreadExecutor {
        return customThreadExecutor
    }

    @Provides
    fun provideGithubRepoRepository(impl: MedicineRepositoryImpl): MedicineRepository {
        return impl
    }
}
