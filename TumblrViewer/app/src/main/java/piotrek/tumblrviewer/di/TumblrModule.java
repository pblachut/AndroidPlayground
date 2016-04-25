package piotrek.tumblrviewer.di;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import piotrek.tumblrviewer.Api.TumblrApi;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Admin on 2016-04-25.
 */
@Module
public class TumblrModule {

    private Context context;

    public TumblrModule(Context context){

        this.context = context;
    }

    @Provides
    public Context provideContext(){
        return context;
    }

    @Provides
    @Named("API_URL")
    public String getApiUrl(){
        return "http://api.tumblr.com/";
    }

    @Provides
    public Converter.Factory provideConverterFactory(){
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    public TumblrApi provideTumblrApi(Converter.Factory factory, @Named("API_URL") String url){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(factory)
                .build();

        return retrofit.create(TumblrApi.class);
    }
}
