package piotrek.tumblrviewer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import piotrek.tumblrviewer.Api.TumblrApi;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostsListFragment extends ListFragment {

    public static final String BLOG_NAME = "blog_name";
    private TumblrApi api;

    public static PostsListFragment newInstance(String blogName) {

        Bundle args = new Bundle();
        args.putString(BLOG_NAME, blogName);

        PostsListFragment fragment = new PostsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        if (api == null)
           api = createApi();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ArrayAdapter<Post> adapter = new ArrayAdapter<Post>(getContext(), android.R.layout.simple_expandable_list_item_1);
        setEmptyText("List is empty");

        Call<PostsResponse> postsCall = api.getPosts(getArguments().getString(BLOG_NAME), 10, 0);

        postsCall.enqueue(new Callback<PostsResponse>() {
            @Override
            public void onResponse(Call<PostsResponse> call, retrofit2.Response<PostsResponse> response) {

                adapter.addAll(response.body().getResponse().getPosts());


                setListAdapter(adapter);
            }

            @Override
            public void onFailure(Call<PostsResponse> call, Throwable t) {
                Log.e("ERRROR", t.getMessage());
            }
        });
    }

    private TumblrApi createApi() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.tumblr.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        return retrofit.create(TumblrApi.class);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }
}
