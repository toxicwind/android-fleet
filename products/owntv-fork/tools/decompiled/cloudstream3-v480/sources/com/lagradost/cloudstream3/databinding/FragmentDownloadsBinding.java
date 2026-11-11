package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class FragmentDownloadsBinding implements ViewBinding {
    public final ImageButton btnCancel;
    public final Button btnDelete;
    public final Button btnToggleAll;
    public final View downloadApp;
    public final TextView downloadAppTxt;
    public final LinearLayout downloadAppbar;
    public final LinearLayout downloadBytesBar;
    public final LinearLayout downloadDeleteAppbar;
    public final View downloadFree;
    public final TextView downloadFreeTxt;
    public final RecyclerView downloadList;
    public final ShimmerFrameLayout downloadLoading;
    public final ShimmerFrameLayout downloadLoadingBytes;
    public final LinearLayout downloadQueueButton;
    public final TextView downloadQueueText;
    public final CoordinatorLayout downloadRoot;
    public final LinearLayout downloadStorageAppbar;
    public final ExtendedFloatingActionButton downloadStreamButton;
    public final ImageView downloadStreamButtonTv;
    public final View downloadUsed;
    public final TextView downloadUsedTxt;
    public final FloatingActionButton openLocalVideoButton;
    private final CoordinatorLayout rootView;
    public final TextView selectItemsText;
    public final LinearLayout steamImageviewHolder;
    public final TextView textNoDownloads;

    private FragmentDownloadsBinding(CoordinatorLayout coordinatorLayout, ImageButton imageButton, Button button, Button button2, View view, TextView textView, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, View view2, TextView textView2, RecyclerView recyclerView, ShimmerFrameLayout shimmerFrameLayout, ShimmerFrameLayout shimmerFrameLayout2, LinearLayout linearLayout4, TextView textView3, CoordinatorLayout coordinatorLayout2, LinearLayout linearLayout5, ExtendedFloatingActionButton extendedFloatingActionButton, ImageView imageView, View view3, TextView textView4, FloatingActionButton floatingActionButton, TextView textView5, LinearLayout linearLayout6, TextView textView6) {
        this.rootView = coordinatorLayout;
        this.btnCancel = imageButton;
        this.btnDelete = button;
        this.btnToggleAll = button2;
        this.downloadApp = view;
        this.downloadAppTxt = textView;
        this.downloadAppbar = linearLayout;
        this.downloadBytesBar = linearLayout2;
        this.downloadDeleteAppbar = linearLayout3;
        this.downloadFree = view2;
        this.downloadFreeTxt = textView2;
        this.downloadList = recyclerView;
        this.downloadLoading = shimmerFrameLayout;
        this.downloadLoadingBytes = shimmerFrameLayout2;
        this.downloadQueueButton = linearLayout4;
        this.downloadQueueText = textView3;
        this.downloadRoot = coordinatorLayout2;
        this.downloadStorageAppbar = linearLayout5;
        this.downloadStreamButton = extendedFloatingActionButton;
        this.downloadStreamButtonTv = imageView;
        this.downloadUsed = view3;
        this.downloadUsedTxt = textView4;
        this.openLocalVideoButton = floatingActionButton;
        this.selectItemsText = textView5;
        this.steamImageviewHolder = linearLayout6;
        this.textNoDownloads = textView6;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static FragmentDownloadsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentDownloadsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_downloads, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentDownloadsBinding bind(View view) {
        View viewFindChildViewById;
        View viewFindChildViewById2;
        View viewFindChildViewById3;
        int i = R.id.btnCancel;
        ImageButton imageButton = (ImageButton) ViewBindings.findChildViewById(view, i);
        if (imageButton != null) {
            i = R.id.btnDelete;
            Button button = (Button) ViewBindings.findChildViewById(view, i);
            if (button != null) {
                i = R.id.btnToggleAll;
                Button button2 = (Button) ViewBindings.findChildViewById(view, i);
                if (button2 != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.download_app))) != null) {
                    i = R.id.download_app_txt;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView != null) {
                        i = R.id.download_appbar;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                        if (linearLayout != null) {
                            i = R.id.download_bytes_bar;
                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                            if (linearLayout2 != null) {
                                i = R.id.download_delete_appbar;
                                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                if (linearLayout3 != null && (viewFindChildViewById2 = ViewBindings.findChildViewById(view, (i = R.id.download_free))) != null) {
                                    i = R.id.download_free_txt;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                    if (textView2 != null) {
                                        i = R.id.download_list;
                                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                                        if (recyclerView != null) {
                                            i = R.id.download_loading;
                                            ShimmerFrameLayout shimmerFrameLayout = (ShimmerFrameLayout) ViewBindings.findChildViewById(view, i);
                                            if (shimmerFrameLayout != null) {
                                                i = R.id.download_loading_bytes;
                                                ShimmerFrameLayout shimmerFrameLayout2 = (ShimmerFrameLayout) ViewBindings.findChildViewById(view, i);
                                                if (shimmerFrameLayout2 != null) {
                                                    i = R.id.download_queue_button;
                                                    LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                    if (linearLayout4 != null) {
                                                        i = R.id.download_queue_text;
                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                                        if (textView3 != null) {
                                                            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view;
                                                            i = R.id.download_storage_appbar;
                                                            LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                            if (linearLayout5 != null) {
                                                                i = R.id.download_stream_button;
                                                                ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) ViewBindings.findChildViewById(view, i);
                                                                if (extendedFloatingActionButton != null) {
                                                                    i = R.id.download_stream_button_tv;
                                                                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                                                                    if (imageView != null && (viewFindChildViewById3 = ViewBindings.findChildViewById(view, (i = R.id.download_used))) != null) {
                                                                        i = R.id.download_used_txt;
                                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                        if (textView4 != null) {
                                                                            i = R.id.open_local_video_button;
                                                                            FloatingActionButton floatingActionButton = (FloatingActionButton) ViewBindings.findChildViewById(view, i);
                                                                            if (floatingActionButton != null) {
                                                                                i = R.id.selectItemsText;
                                                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                if (textView5 != null) {
                                                                                    i = R.id.steam_imageview_holder;
                                                                                    LinearLayout linearLayout6 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                    if (linearLayout6 != null) {
                                                                                        i = R.id.text_no_downloads;
                                                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                        if (textView6 != null) {
                                                                                            return new FragmentDownloadsBinding(coordinatorLayout, imageButton, button, button2, viewFindChildViewById, textView, linearLayout, linearLayout2, linearLayout3, viewFindChildViewById2, textView2, recyclerView, shimmerFrameLayout, shimmerFrameLayout2, linearLayout4, textView3, coordinatorLayout, linearLayout5, extendedFloatingActionButton, imageView, viewFindChildViewById3, textView4, floatingActionButton, textView5, linearLayout6, textView6);
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
