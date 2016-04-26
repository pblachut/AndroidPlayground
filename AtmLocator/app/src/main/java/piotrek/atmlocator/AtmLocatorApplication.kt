package piotrek.atmlocator

import android.app.Application

import piotrek.atmlocator.di.ApplicationComponent
import piotrek.atmlocator.di.ApplicationModule
import piotrek.atmlocator.di.DaggerApplicationComponent

/**
 * Created by Admin on 2016-04-26.
 */
class AtmLocatorApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
    }

    companion object {

        @JvmStatic lateinit  var component: ApplicationComponent
    }
}
