/**
 * Created by Mohammad Jafarzadeh Rezvan on 7/6/2017.
 */

package com.hulkdx.medicine.di.components

import dagger.Component
import com.hulkdx.medicine.di.ConfigPersistent
import com.hulkdx.medicine.di.modules.ActivityModule
import com.hulkdx.medicine.di.modules.ConfigPersistentModule

/**
 * Created by Mohammad Jafarzadeh Rezvan on 09/11/2018.
 */

@ConfigPersistent
@Component(dependencies = [ApplicationComponent::class],
           modules = [ConfigPersistentModule::class])
interface ConfigPersistentComponent {

    fun activityComponent(activityModule: ActivityModule): ActivityComponent

}