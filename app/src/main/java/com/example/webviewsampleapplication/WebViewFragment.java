package com.example.webviewsampleapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.webviewsampleapplication.R;
import com.example.webviewsampleapplication.databinding.FragmentWebviewBinding;

public class WebViewFragment extends Fragment {

    private FragmentWebviewBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentWebviewBinding.inflate(inflater, container, false);

      //View rootView = inflater.inflate(R.layout.content_main, container, false);
        View rootView = binding.getRoot();

        // This is how to set URL of Web view when it's in a fragment ( NOT in main activity)
        String url = "http://example.com/";
        WebView view = (WebView) rootView.findViewById(R.id.webViewOne);
        view.getSettings().setJavaScriptEnabled(true);
        view.setWebViewClient(new WebViewClient()); // if this NOT the URL opens in the browser
        view.loadUrl(url);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}
