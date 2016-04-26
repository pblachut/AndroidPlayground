package piotrek.atmlocator.di;

import javax.inject.Singleton;

import dagger.Component;
import piotrek.atmlocator.AddAtm;
import piotrek.atmlocator.MapsActivity;

/**
 * Created by Admin on 2016-04-26.
 */
@Component(modules = ApplicationModule.class)
@Singleton
public interface ApplicationComponent {
    void inject(AddAtm activity);
    void inject(MapsActivity activity);
}
