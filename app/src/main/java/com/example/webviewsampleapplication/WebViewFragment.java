package com.example.webviewsampleapplication;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.webkit.WebViewAssetLoader;
import androidx.webkit.WebViewClientCompat;

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
        WebView view = (WebView) rootView.findViewById(R.id.webViewOne);

        // Use JavaScript in WebView
        view.getSettings().setJavaScriptEnabled(true);
        view.addJavascriptInterface(new WebAppInterface(getActivity()), "Android");

        /*
        fetch over the internet or Web-server
        * */
        //view.setWebViewClient(new WebViewClient()); // if this NOT the URL opens in the browser
        //String url = "http://example.com/";
        //view.loadUrl(url);

        /*
        https://developer.android.com/develop/ui/views/layout/webapps/load-local-content
        -- Config: In-app content use in web view --
            You can provide web-based content—such as HTML, JavaScript, and CSS—for your app to use
            that you statically compile into the application rather than fetch over the internet
        * */
        final WebViewAssetLoader assetLoader = new WebViewAssetLoader.Builder()
                .addPathHandler("/assets/", new WebViewAssetLoader.AssetsPathHandler(getActivity()))
                .addPathHandler("/res/", new WebViewAssetLoader.ResourcesPathHandler(getActivity()))
                .build();
        view.setWebViewClient(new LocalContentWebViewClient(assetLoader));
        view.loadUrl("https://appassets.androidplatform.net/assets/index.html");

        // Create an unencoded HTML string
        // then convert the unencoded HTML string into bytes, encode
        // it with Base64, and load the data.
        // String unencodedHtml = "<html><body>'%23' is the percent code for ‘#‘ </body></html>";
        // String encodedHtml = Base64.encodeToString(unencodedHtml.getBytes(), Base64.NO_PADDING);
        // view.loadData(encodedHtml, "text/html", "base64");

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View rootView = binding.getRoot();
        WebView webViewOne = (WebView) rootView.findViewById(R.id.webViewOne);

        String s = "Hi Web";
        webViewOne.evaluateJavascript("javascript: " +
                        "updateFromAndroid(\"" + s + "\")",
                null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //-- Config: In-app content use in web view --
    private static class LocalContentWebViewClient extends WebViewClientCompat {

        private final WebViewAssetLoader mAssetLoader;

        LocalContentWebViewClient(WebViewAssetLoader assetLoader) {
            mAssetLoader = assetLoader;
        }

        @Override
        @RequiresApi(21)
        public WebResourceResponse shouldInterceptRequest(WebView view,
                                                          WebResourceRequest request) {
            return mAssetLoader.shouldInterceptRequest(request.getUrl());
        }

        @Override
        @SuppressWarnings("deprecation") // to support API < 21
        public WebResourceResponse shouldInterceptRequest(WebView view,
                                                          String url) {
            return mAssetLoader.shouldInterceptRequest(Uri.parse(url));
        }
    }

}
