package piotrek.tumblrviewer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


public class PostDetailsFragment extends Fragment {

    public static final String URL = "url";

    public static PostDetailsFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString(URL, url);

        PostDetailsFragment fragment = new PostDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post_details ,container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WebView webView = (WebView) view.findViewById(R.id.post_details_webView);
        String url = getArguments().getString(URL);

        webView.loadUrl(url);

        Log.d("TAG", "opening webview with url" + url);
    }
}
