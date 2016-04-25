package piotrek.tumblrviewer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PostsListFragment extends ListFragment {

    public static final String BLOG_NAME = "blog_name";

    public static PostsListFragment newInstance(String blogName) {

        Bundle args = new Bundle();
        args.putString(BLOG_NAME, blogName);

        PostsListFragment fragment = new PostsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_expandable_list_item_1);
        adapter.addAll("w1", "32323", "2323232", "232323");
        setEmptyText("List is empty");

        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }
}
