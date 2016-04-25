package piotrek.tumblrviewer.di;

import javax.inject.Singleton;

import dagger.Component;
import piotrek.tumblrviewer.Api.TumblrApi;
import piotrek.tumblrviewer.PostsListFragment;

/**
 * Created by Admin on 2016-04-25.
 */

@Singleton
@Component(modules = TumblrModule.class)
public interface TumblrComponent {
    TumblrApi getTumblrApi();

    void inject(PostsListFragment fragment);
}
