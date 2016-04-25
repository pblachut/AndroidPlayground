package piotrek.tumblrviewer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;

import piotrek.tumblrviewer.Api.TumblrApi;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostsListFragment extends ListFragment {

    public static final String BLOG_NAME = "blog_name";
    private TumblrApi api;
    private ICallback callback;
    private ArrayAdapter<Post> adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        callback = (ICallback) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        callback = null;
    }

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
    public void onViewCreated(View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new ArrayAdapter<Post>(getContext(), R.layout.post_item, R.id.post_item_textView){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {


                View view = convertView;

                if (view == null){
                    view = getLayoutInflater(savedInstanceState).inflate(R.layout.post_item, parent, false);
                }

                ViewHolder viewHolder = (ViewHolder) view.getTag();
                if (viewHolder == null){
                    viewHolder = new ViewHolder(view);
                    view.setTag(viewHolder);
                }

                Post post = getItem(position);

                viewHolder.textView.setText(Html.fromHtml(post.getCaption()));

                Glide.with(PostsListFragment.this)
                        .load(post.getPhotos().get(0).getAltSizes().get(0).getUrl())
                        .into(viewHolder.imageView);

                return view;

            }

        };
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

        callback.openUrl(adapter.getItem(position).getPostUrl());
    }

    static class ViewHolder{
        ImageView imageView;
        TextView textView;

        ViewHolder(View view){
            imageView = (ImageView) view.findViewById(R.id.post_item_imageView);
            textView = (TextView) view.findViewById(R.id.post_item_textView);
        }
    }

    public interface ICallback{
        void openUrl(String url);
    }
}
