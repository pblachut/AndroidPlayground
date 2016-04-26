package piotrek.atmlocator.di

import javax.inject.Singleton

import dagger.Component
import piotrek.atmlocator.AddAtm
import piotrek.atmlocator.MapsActivity

/**
 * Created by Admin on 2016-04-26.
 */
@Component(modules = arrayOf(ApplicationModule::class))
@Singleton
interface ApplicationComponent {
    fun inject(activity: AddAtm)
    fun inject(activity: MapsActivity)
}
