package com.lagradost.cloudstream3.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.fragment.FragmentKt;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivity;
import com.lagradost.cloudstream3.databinding.FragmentWebviewBinding;
import com.lagradost.cloudstream3.network.WebViewResolver;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WebviewFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u000b\fB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0002H\u0016¨\u0006\r"}, d2 = {"Lcom/lagradost/cloudstream3/ui/WebviewFragment;", "Lcom/lagradost/cloudstream3/ui/BaseFragment;", "Lcom/lagradost/cloudstream3/databinding/FragmentWebviewBinding;", "<init>", "()V", "fixLayout", "", "view", "Landroid/view/View;", "onBindingCreated", "binding", "Companion", "RepoApi", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class WebviewFragment extends BaseFragment<FragmentWebviewBinding> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String WEBVIEW_URL = "webview_url";

    @Override // com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void fixLayout(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.WebviewFragment$1, reason: invalid class name */
    /* JADX INFO: compiled from: WebviewFragment.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function3<LayoutInflater, ViewGroup, Boolean, FragmentWebviewBinding> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(3, FragmentWebviewBinding.class, "inflate", "inflate(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Z)Lcom/lagradost/cloudstream3/databinding/FragmentWebviewBinding;", 0);
        }

        public final FragmentWebviewBinding invoke(LayoutInflater p0, ViewGroup viewGroup, boolean z) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return FragmentWebviewBinding.inflate(p0, viewGroup, z);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ FragmentWebviewBinding invoke(LayoutInflater layoutInflater, ViewGroup viewGroup, Boolean bool) {
            return invoke(layoutInflater, viewGroup, bool.booleanValue());
        }
    }

    public WebviewFragment() {
        super(new BaseFragment.BindingCreator.Inflate(AnonymousClass1.INSTANCE));
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void onBindingCreated(FragmentWebviewBinding binding) {
        String string;
        Intrinsics.checkNotNullParameter(binding, "binding");
        Bundle arguments = getArguments();
        if (arguments == null || (string = arguments.getString(WEBVIEW_URL)) == null) {
            FragmentKt.findNavController(this).popBackStack();
            string = "";
        }
        binding.webView.setWebViewClient(new WebViewClient() { // from class: com.lagradost.cloudstream3.ui.WebviewFragment.onBindingCreated.1
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (MainActivity.Companion.handleAppIntentUrl$default(MainActivity.INSTANCE, WebviewFragment.this.getActivity(), String.valueOf(request != null ? request.getUrl() : null), true, null, 8, null)) {
                    FragmentKt.findNavController(WebviewFragment.this).popBackStack();
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
        WebView webView = binding.webView;
        WebViewResolver.INSTANCE.setWebViewUserAgent(webView.getSettings().getUserAgentString());
        webView.addJavascriptInterface(new RepoApi(getActivity()), "RepoApi");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUserAgentString(MainAPIKt.USER_AGENT);
        webView.getSettings().setDomStorageEnabled(true);
        webView.loadUrl(string);
    }

    /* JADX INFO: compiled from: WebviewFragment.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/lagradost/cloudstream3/ui/WebviewFragment$Companion;", "", "<init>", "()V", "WEBVIEW_URL", "", "newInstance", "Landroid/os/Bundle;", "webViewUrl", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Bundle newInstance(String webViewUrl) {
            Intrinsics.checkNotNullParameter(webViewUrl, "webViewUrl");
            Bundle bundle = new Bundle();
            bundle.putString(WebviewFragment.WEBVIEW_URL, webViewUrl);
            return bundle;
        }
    }

    /* JADX INFO: compiled from: WebviewFragment.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Lcom/lagradost/cloudstream3/ui/WebviewFragment$RepoApi;", "", "activity", "Landroidx/fragment/app/FragmentActivity;", "<init>", "(Landroidx/fragment/app/FragmentActivity;)V", "getActivity", "()Landroidx/fragment/app/FragmentActivity;", "installRepo", "", "repoUrl", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private static final class RepoApi {
        private final FragmentActivity activity;

        public RepoApi(FragmentActivity fragmentActivity) {
            this.activity = fragmentActivity;
        }

        public final FragmentActivity getActivity() {
            return this.activity;
        }

        @JavascriptInterface
        public final void installRepo(String repoUrl) {
            Intrinsics.checkNotNullParameter(repoUrl, "repoUrl");
            FragmentActivity fragmentActivity = this.activity;
            if (fragmentActivity != null) {
                AppContextUtils.INSTANCE.loadRepository(fragmentActivity, repoUrl);
            }
        }
    }
}
