package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.viewbinding.ViewBinding;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class FragmentWebviewBinding implements ViewBinding {
    private final WebView rootView;
    public final WebView webView;

    private FragmentWebviewBinding(WebView webView, WebView webView2) {
        this.rootView = webView;
        this.webView = webView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public WebView getRoot() {
        return this.rootView;
    }

    public static FragmentWebviewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentWebviewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_webview, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentWebviewBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        WebView webView = (WebView) view;
        return new FragmentWebviewBinding(webView, webView);
    }
}
