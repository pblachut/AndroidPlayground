package piotrek.tumblrviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import piotrek.tumblrviewer.PostsListFragment.ICallback;

public class PostDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        if (savedInstanceState == null){

            String url = getIntent().getStringExtra(PostDetailsFragment.URL);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.detailsContainer, PostDetailsFragment.newInstance(url))
                    .commit();
        }


    }
}
