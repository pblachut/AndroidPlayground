package piotrek.tumblrviewer;

import android.app.Application;

import piotrek.tumblrviewer.di.DaggerTumblrComponent;
import piotrek.tumblrviewer.di.TumblrComponent;
import piotrek.tumblrviewer.di.TumblrModule;

/**
 * Created by Admin on 2016-04-25.
 */
public class TumblrApplication extends Application {

    public static TumblrComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerTumblrComponent.builder()
                .tumblrModule(new TumblrModule(this))
                .build();
    }
}
