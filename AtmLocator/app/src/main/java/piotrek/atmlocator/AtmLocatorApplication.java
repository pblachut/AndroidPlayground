package piotrek.atmlocator;

import android.app.Application;

import piotrek.atmlocator.di.ApplicationComponent;
import piotrek.atmlocator.di.ApplicationModule;
import piotrek.atmlocator.di.DaggerApplicationComponent;

/**
 * Created by Admin on 2016-04-26.
 */
public class AtmLocatorApplication extends Application {

    public static ApplicationComponent component;

    public AtmLocatorApplication(){

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

    }
}
