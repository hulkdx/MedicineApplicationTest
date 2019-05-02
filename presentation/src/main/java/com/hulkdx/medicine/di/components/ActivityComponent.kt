package com.hulkdx.medicine.di.components


import dagger.Subcomponent
import com.hulkdx.medicine.di.PerActivity
import com.hulkdx.medicine.di.modules.ActivityModule
import com.hulkdx.medicine.ui.main.MainActivity
import com.hulkdx.medicine.ui.main.MainFragment

/**
 * Created by Mohammad Jafarzadeh Rezvan on 09/11/2018.
 */
@PerActivity
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)
    fun inject(mainFragment: MainFragment)
}
