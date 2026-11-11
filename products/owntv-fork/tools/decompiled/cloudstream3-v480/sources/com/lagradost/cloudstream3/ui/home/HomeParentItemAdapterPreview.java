package com.lagradost.cloudstream3.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.android.material.navigationrail.NavigationRailView;
import com.lagradost.cloudstream3.ActorData;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.HomePageList;
import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.MainActivity;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.Score;
import com.lagradost.cloudstream3.SearchResponse;
import com.lagradost.cloudstream3.databinding.ActivityMainBinding;
import com.lagradost.cloudstream3.databinding.FragmentHomeHeadBinding;
import com.lagradost.cloudstream3.databinding.FragmentHomeHeadTvBinding;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.mvvm.LifecycleKt;
import com.lagradost.cloudstream3.mvvm.Resource;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.BaseAdapterKt;
import com.lagradost.cloudstream3.ui.ViewHolderState;
import com.lagradost.cloudstream3.ui.WatchType;
import com.lagradost.cloudstream3.ui.account.AccountHelper;
import com.lagradost.cloudstream3.ui.account.AccountViewModel;
import com.lagradost.cloudstream3.ui.result.LinearListLayoutKt;
import com.lagradost.cloudstream3.ui.result.ResultFragment;
import com.lagradost.cloudstream3.ui.result.ResultViewModel2;
import com.lagradost.cloudstream3.ui.result.ResultViewModel2Kt;
import com.lagradost.cloudstream3.ui.search.SearchClickCallback;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.ImageLoader;
import com.lagradost.cloudstream3.utils.SingleSelectionHelper;
import com.lagradost.cloudstream3.utils.UIHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.enums.EnumEntries;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: HomeParentItemAdapterPreview.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\u0016B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0016\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0016J\u0016\u0010\u0014\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0016J\u0016\u0010\u0015\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/lagradost/cloudstream3/ui/home/HomeParentItemAdapterPreview;", "Lcom/lagradost/cloudstream3/ui/home/ParentItemAdapter;", "viewModel", "Lcom/lagradost/cloudstream3/ui/home/HomeViewModel;", "accountViewModel", "Lcom/lagradost/cloudstream3/ui/account/AccountViewModel;", "<init>", "(Lcom/lagradost/cloudstream3/ui/home/HomeViewModel;Lcom/lagradost/cloudstream3/ui/account/AccountViewModel;)V", "headers", "", "getHeaders", "()I", "onCreateHeader", "Lcom/lagradost/cloudstream3/ui/ViewHolderState;", "Landroid/os/Bundle;", "parent", "Landroid/view/ViewGroup;", "onBindHeader", "", "holder", "onViewDetachedFromWindow", "onViewAttachedToWindow", "HeaderViewHolder", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class HomeParentItemAdapterPreview extends ParentItemAdapter {
    private final AccountViewModel accountViewModel;
    private final int headers;
    private final HomeViewModel viewModel;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeParentItemAdapterPreview(final HomeViewModel viewModel, AccountViewModel accountViewModel) {
        super(749283797, new Function1() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HomeParentItemAdapterPreview._init_$lambda$0(viewModel, (SearchClickCallback) obj);
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HomeParentItemAdapterPreview._init_$lambda$1(viewModel, (HomeViewModel.ExpandableHomepageList) obj);
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HomeParentItemAdapterPreview._init_$lambda$2(viewModel, (String) obj);
            }
        });
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        Intrinsics.checkNotNullParameter(accountViewModel, "accountViewModel");
        this.viewModel = viewModel;
        this.accountViewModel = accountViewModel;
        this.headers = 1;
    }

    static final Unit _init_$lambda$0(HomeViewModel homeViewModel, SearchClickCallback it) {
        Intrinsics.checkNotNullParameter(it, "it");
        homeViewModel.click(it);
        return Unit.INSTANCE;
    }

    static final Unit _init_$lambda$1(HomeViewModel homeViewModel, HomeViewModel.ExpandableHomepageList it) {
        Intrinsics.checkNotNullParameter(it, "it");
        HomeViewModel.popup$default(homeViewModel, it, null, 2, null);
        return Unit.INSTANCE;
    }

    static final Unit _init_$lambda$2(HomeViewModel homeViewModel, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        homeViewModel.expand(it);
        return Unit.INSTANCE;
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public int getHeaders() {
        return this.headers;
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public ViewHolderState<Bundle> onCreateHeader(ViewGroup parent) {
        FragmentHomeHeadBinding fragmentHomeHeadBinding;
        Intrinsics.checkNotNullParameter(parent, "parent");
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(parent.getContext());
        if (Globals.INSTANCE.isLayout(6)) {
            FragmentHomeHeadTvBinding fragmentHomeHeadTvBindingInflate = FragmentHomeHeadTvBinding.inflate(layoutInflaterFrom, parent, false);
            Intrinsics.checkNotNullExpressionValue(fragmentHomeHeadTvBindingInflate, "inflate(...)");
            fragmentHomeHeadBinding = fragmentHomeHeadTvBindingInflate;
        } else {
            FragmentHomeHeadBinding fragmentHomeHeadBindingInflate = FragmentHomeHeadBinding.inflate(layoutInflaterFrom, parent, false);
            Intrinsics.checkNotNullExpressionValue(fragmentHomeHeadBindingInflate, "inflate(...)");
            fragmentHomeHeadBinding = fragmentHomeHeadBindingInflate;
        }
        if ((fragmentHomeHeadBinding instanceof FragmentHomeHeadTvBinding) && Globals.INSTANCE.isLayout(4)) {
            FragmentHomeHeadTvBinding fragmentHomeHeadTvBinding = (FragmentHomeHeadTvBinding) fragmentHomeHeadBinding;
            ImageView homeBookmarkParentItemMoreInfo = fragmentHomeHeadTvBinding.homeBookmarkParentItemMoreInfo;
            Intrinsics.checkNotNullExpressionValue(homeBookmarkParentItemMoreInfo, "homeBookmarkParentItemMoreInfo");
            homeBookmarkParentItemMoreInfo.setVisibility(0);
            int i = (int) (50.0f * fragmentHomeHeadTvBinding.horizontalScrollChips.getContext().getResources().getDisplayMetrics().density);
            ViewGroup.LayoutParams layoutParams = fragmentHomeHeadTvBinding.horizontalScrollChips.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.setMarginEnd(i);
            fragmentHomeHeadTvBinding.horizontalScrollChips.setLayoutParams(marginLayoutParams);
            fragmentHomeHeadTvBinding.homeWatchParentItemTitle.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, ContextCompat.getDrawable(parent.getContext(), R.drawable.ic_baseline_arrow_forward_24), (Drawable) null);
        }
        return new HeaderViewHolder(fragmentHomeHeadBinding, this.viewModel, this.accountViewModel);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onBindHeader(ViewHolderState<Bundle> holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        HeaderViewHolder headerViewHolder = holder instanceof HeaderViewHolder ? (HeaderViewHolder) holder : null;
        if (headerViewHolder != null) {
            headerViewHolder.bind();
        }
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewDetachedFromWindow(ViewHolderState<Bundle> holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (holder instanceof HeaderViewHolder) {
            ((HeaderViewHolder) holder).onViewDetachedFromWindow();
        }
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewAttachedToWindow(ViewHolderState<Bundle> holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (holder instanceof HeaderViewHolder) {
            ((HeaderViewHolder) holder).onViewAttachedToWindow();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: HomeParentItemAdapterPreview.kt */
    @Metadata(d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u000f\u001a\u00020\u0002H\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0002H\u0016J\u0016\u0010-\u001a\u00020\u00112\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u000201J\u0006\u00104\u001a\u00020\u0011J\u0006\u0010<\u001a\u00020\u0011J(\u0010=\u001a\u00020\u00112\u001e\u0010>\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020@\u0012\n\u0012\b\u0012\u0004\u0012\u00020/06070?H\u0002J\u0016\u0010A\u001a\u00020\u00112\f\u0010B\u001a\b\u0012\u0004\u0012\u00020C06H\u0002J\"\u0010D\u001a\u00020\u00112\u0018\u0010E\u001a\u0014\u0012\u0004\u0012\u00020@\u0012\n\u0012\b\u0012\u0004\u0012\u00020C0607H\u0002J\u0006\u0010F\u001a\u00020\u0011R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010 X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010&X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010 X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010 X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u0004\u0018\u00010 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u000203X\u0082\u0004¢\u0006\u0002\n\u0000R \u00105\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u0002090706X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010:\u001a\u0004\u0018\u00010;X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006G"}, d2 = {"Lcom/lagradost/cloudstream3/ui/home/HomeParentItemAdapterPreview$HeaderViewHolder;", "Lcom/lagradost/cloudstream3/ui/ViewHolderState;", "Landroid/os/Bundle;", "binding", "Landroidx/viewbinding/ViewBinding;", "viewModel", "Lcom/lagradost/cloudstream3/ui/home/HomeViewModel;", "accountViewModel", "Lcom/lagradost/cloudstream3/ui/account/AccountViewModel;", "<init>", "(Landroidx/viewbinding/ViewBinding;Lcom/lagradost/cloudstream3/ui/home/HomeViewModel;Lcom/lagradost/cloudstream3/ui/account/AccountViewModel;)V", "getBinding", "()Landroidx/viewbinding/ViewBinding;", "getViewModel", "()Lcom/lagradost/cloudstream3/ui/home/HomeViewModel;", "save", "restore", "", "state", "previewAdapter", "Lcom/lagradost/cloudstream3/ui/home/HomeScrollAdapter;", "getPreviewAdapter", "()Lcom/lagradost/cloudstream3/ui/home/HomeScrollAdapter;", "resumeAdapter", "Lcom/lagradost/cloudstream3/ui/home/ResumeItemAdapter;", "bookmarkAdapter", "Lcom/lagradost/cloudstream3/ui/home/HomeChildItemAdapter;", "previewViewpager", "Landroidx/viewpager2/widget/ViewPager2;", "previewViewpagerText", "Landroid/view/ViewGroup;", "resumeHolder", "Landroid/view/View;", "resumeRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "bookmarkHolder", "bookmarkRecyclerView", "headProfilePic", "Landroid/widget/ImageView;", "headProfilePicCard", "alternateHeadProfilePic", "alternateHeadProfilePicCard", "topPadding", "alternativeAccountPadding", "homeNonePadding", "onSelect", "item", "Lcom/lagradost/cloudstream3/LoadResponse;", "position", "", "previewCallback", "Landroidx/viewpager2/widget/ViewPager2$OnPageChangeCallback;", "onViewDetachedFromWindow", "toggleList", "", "Lkotlin/Pair;", "Lcom/google/android/material/chip/Chip;", "Lcom/lagradost/cloudstream3/ui/WatchType;", "toggleListHolder", "Lcom/google/android/material/chip/ChipGroup;", "bind", "updatePreview", "preview", "Lcom/lagradost/cloudstream3/mvvm/Resource;", "", "updateResume", "resumeWatching", "Lcom/lagradost/cloudstream3/SearchResponse;", "updateBookmarks", "data", "onViewAttachedToWindow", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    static final class HeaderViewHolder extends ViewHolderState<Bundle> {
        private final ImageView alternateHeadProfilePic;
        private final View alternateHeadProfilePicCard;
        private final View alternativeAccountPadding;
        private final ViewBinding binding;
        private final HomeChildItemAdapter bookmarkAdapter;
        private final View bookmarkHolder;
        private final RecyclerView bookmarkRecyclerView;
        private final ImageView headProfilePic;
        private final View headProfilePicCard;
        private final View homeNonePadding;
        private final HomeScrollAdapter previewAdapter;
        private final ViewPager2.OnPageChangeCallback previewCallback;
        private final ViewPager2 previewViewpager;
        private final ViewGroup previewViewpagerText;
        private final ResumeItemAdapter resumeAdapter;
        private final View resumeHolder;
        private final RecyclerView resumeRecyclerView;
        private final List<Pair<Chip, WatchType>> toggleList;
        private final ChipGroup toggleListHolder;
        private final View topPadding;
        private final HomeViewModel viewModel;

        /* JADX INFO: Access modifiers changed from: private */
        public static final void resumeAdapter$lambda$1$0$0(DialogInterface dialogInterface, int i) {
        }

        public final void bind() {
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public HeaderViewHolder(ViewBinding binding, HomeViewModel viewModel, final AccountViewModel accountViewModel) {
            super(binding);
            Intrinsics.checkNotNullParameter(binding, "binding");
            Intrinsics.checkNotNullParameter(viewModel, "viewModel");
            Intrinsics.checkNotNullParameter(accountViewModel, "accountViewModel");
            this.binding = binding;
            this.viewModel = viewModel;
            HomeScrollAdapter homeScrollAdapter = new HomeScrollAdapter(new Function3() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return HomeParentItemAdapterPreview.HeaderViewHolder.previewAdapter$lambda$0(this.f$0, (View) obj, ((Integer) obj2).intValue(), (LoadResponse) obj3);
                }
            });
            this.previewAdapter = homeScrollAdapter;
            ResumeItemAdapter resumeItemAdapter = new ResumeItemAdapter(Integer.valueOf(this.itemView.getNextFocusUpId()), Integer.valueOf(this.itemView.getNextFocusDownId()), new Function1() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return HomeParentItemAdapterPreview.HeaderViewHolder.resumeAdapter$lambda$0(this.f$0, (SearchClickCallback) obj);
                }
            }, new Function1() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return HomeParentItemAdapterPreview.HeaderViewHolder.resumeAdapter$lambda$1(this.f$0, (View) obj);
                }
            });
            this.resumeAdapter = resumeItemAdapter;
            HomeChildItemAdapter homeChildItemAdapter = new HomeChildItemAdapter(2091478329, Integer.valueOf(this.itemView.getNextFocusUpId()), Integer.valueOf(this.itemView.getNextFocusDownId()), new Function1() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return HomeParentItemAdapterPreview.HeaderViewHolder.bookmarkAdapter$lambda$0(this.f$0, (SearchClickCallback) obj);
                }
            });
            this.bookmarkAdapter = homeChildItemAdapter;
            View viewFindViewById = this.itemView.findViewById(R.id.home_preview_viewpager);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
            ViewPager2 viewPager2 = (ViewPager2) viewFindViewById;
            this.previewViewpager = viewPager2;
            View viewFindViewById2 = this.itemView.findViewById(R.id.home_preview_viewpager_text);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
            this.previewViewpagerText = (ViewGroup) viewFindViewById2;
            View viewFindViewById3 = this.itemView.findViewById(R.id.home_watch_holder);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
            this.resumeHolder = viewFindViewById3;
            View viewFindViewById4 = this.itemView.findViewById(R.id.home_watch_child_recyclerview);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "findViewById(...)");
            RecyclerView recyclerView = (RecyclerView) viewFindViewById4;
            this.resumeRecyclerView = recyclerView;
            View viewFindViewById5 = this.itemView.findViewById(R.id.home_bookmarked_holder);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "findViewById(...)");
            this.bookmarkHolder = viewFindViewById5;
            View viewFindViewById6 = this.itemView.findViewById(R.id.home_bookmarked_child_recyclerview);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "findViewById(...)");
            RecyclerView recyclerView2 = (RecyclerView) viewFindViewById6;
            this.bookmarkRecyclerView = recyclerView2;
            this.headProfilePic = (ImageView) this.itemView.findViewById(R.id.home_head_profile_pic);
            this.headProfilePicCard = this.itemView.findViewById(R.id.home_head_profile_padding);
            this.alternateHeadProfilePic = (ImageView) this.itemView.findViewById(R.id.alternate_home_head_profile_pic);
            this.alternateHeadProfilePicCard = this.itemView.findViewById(R.id.alternate_home_head_profile_padding);
            View viewFindViewById7 = this.itemView.findViewById(R.id.home_padding);
            this.topPadding = viewFindViewById7;
            this.alternativeAccountPadding = this.itemView.findViewById(R.id.alternative_account_padding);
            View viewFindViewById8 = this.itemView.findViewById(R.id.home_none_padding);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById8, "findViewById(...)");
            this.homeNonePadding = viewFindViewById8;
            this.previewCallback = new ViewPager2.OnPageChangeCallback() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$previewCallback$1
                @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
                public void onPageSelected(int position) {
                    HomeScrollAdapter previewAdapter = this.this$0.getPreviewAdapter();
                    HomeParentItemAdapterPreview.HeaderViewHolder headerViewHolder = this.this$0;
                    if (position >= previewAdapter.getItemCount() - 1 && previewAdapter.getHasMoreItems()) {
                        previewAdapter.setHasMoreItems(false);
                        headerViewHolder.getViewModel().loadMoreHomeScrollResponses();
                    }
                    LoadResponse itemOrNull = this.this$0.getPreviewAdapter().getItemOrNull(position);
                    if (itemOrNull == null) {
                        return;
                    }
                    this.this$0.onSelect(itemOrNull, position);
                }
            };
            List<Pair<Chip, WatchType>> listListOf = CollectionsKt.listOf((Object[]) new Pair[]{new Pair(this.itemView.findViewById(R.id.home_type_watching_btt), WatchType.WATCHING), new Pair(this.itemView.findViewById(R.id.home_type_completed_btt), WatchType.COMPLETED), new Pair(this.itemView.findViewById(R.id.home_type_dropped_btt), WatchType.DROPPED), new Pair(this.itemView.findViewById(R.id.home_type_on_hold_btt), WatchType.ONHOLD), new Pair(this.itemView.findViewById(R.id.home_plan_to_watch_btt), WatchType.PLANTOWATCH)});
            this.toggleList = listListOf;
            this.toggleListHolder = (ChipGroup) this.itemView.findViewById(R.id.home_type_holder);
            viewPager2.setPageTransformer(new HomeScrollTransformer());
            viewPager2.setAdapter(homeScrollAdapter);
            recyclerView.setAdapter(resumeItemAdapter);
            BaseAdapterKt.setRecycledViewPool(recyclerView2, HomeChildItemAdapter.INSTANCE.getSharedPool());
            recyclerView2.setAdapter(homeChildItemAdapter);
            LinearListLayoutKt.setLinearListLayout$default(recyclerView, false, R.id.nav_rail_view, -2, 0, 0, 25, null);
            LinearListLayoutKt.setLinearListLayout$default(recyclerView2, false, R.id.nav_rail_view, -2, 0, 0, 25, null);
            UIHelper.INSTANCE.fixPaddingStatusbarMargin(viewFindViewById7);
            for (Pair<Chip, WatchType> pair : listListOf) {
                Chip chipComponent1 = pair.component1();
                final WatchType watchTypeComponent2 = pair.component2();
                chipComponent1.setChecked(false);
                chipComponent1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda7
                    @Override // android.widget.CompoundButton.OnCheckedChangeListener
                    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                        HomeParentItemAdapterPreview.HeaderViewHolder._init_$lambda$0(this.f$0, watchTypeComponent2, compoundButton, z);
                    }
                });
            }
            View view = this.headProfilePicCard;
            if (view != null) {
                view.setVisibility(Globals.INSTANCE.isLayout(6) ? 8 : 0);
            }
            View view2 = this.alternateHeadProfilePicCard;
            if (view2 != null) {
                view2.setVisibility(Globals.INSTANCE.isLayout(6) ? 8 : 0);
            }
            ImageView imageView = this.headProfilePic;
            imageView = imageView == null ? this.alternateHeadProfilePic : imageView;
            if (imageView != null) {
                LifecycleKt.observe(imageView, this.viewModel.getCurrentAccount(), new Function1() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return HomeParentItemAdapterPreview.HeaderViewHolder._init_$lambda$1(this.f$0, (DataStoreHelper.Account) obj);
                    }
                });
            }
            View view3 = this.headProfilePicCard;
            if (view3 != null) {
                view3.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda9
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view4) {
                        HomeParentItemAdapterPreview.HeaderViewHolder._init_$lambda$2(view4);
                    }
                });
            }
            View view4 = this.alternateHeadProfilePicCard;
            if (view4 != null) {
                view4.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda11
                    @Override // android.view.View.OnLongClickListener
                    public final boolean onLongClick(View view5) {
                        return HomeParentItemAdapterPreview.HeaderViewHolder._init_$lambda$5(accountViewModel, view5);
                    }
                });
            }
            View view5 = this.headProfilePicCard;
            if (view5 != null) {
                view5.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda12
                    @Override // android.view.View.OnLongClickListener
                    public final boolean onLongClick(View view6) {
                        return HomeParentItemAdapterPreview.HeaderViewHolder._init_$lambda$6(accountViewModel, view6);
                    }
                });
            }
            View view6 = this.alternateHeadProfilePicCard;
            if (view6 != null) {
                view6.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda13
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view7) {
                        HomeParentItemAdapterPreview.HeaderViewHolder._init_$lambda$7(view7);
                    }
                });
            }
            ViewBinding viewBinding = this.binding;
            final FragmentHomeHeadTvBinding fragmentHomeHeadTvBinding = viewBinding instanceof FragmentHomeHeadTvBinding ? (FragmentHomeHeadTvBinding) viewBinding : null;
            if (fragmentHomeHeadTvBinding != null) {
                final Ref.LongRef longRef = new Ref.LongRef();
                fragmentHomeHeadTvBinding.homePreviewInfoBtt.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnFocusChangeListener
                    public final void onFocusChange(View view7, boolean z) {
                        HomeParentItemAdapterPreview.HeaderViewHolder.lambda$8$0(longRef, view7, z);
                    }
                });
                fragmentHomeHeadTvBinding.homePreviewHiddenNextFocus.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda2
                    @Override // android.view.View.OnFocusChangeListener
                    public final void onFocusChange(View view7, boolean z) {
                        HomeParentItemAdapterPreview.HeaderViewHolder.lambda$8$1(this.f$0, fragmentHomeHeadTvBinding, view7, z);
                    }
                });
                fragmentHomeHeadTvBinding.homePreviewHiddenPrevFocus.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda3
                    @Override // android.view.View.OnFocusChangeListener
                    public final void onFocusChange(View view7, boolean z) {
                        HomeParentItemAdapterPreview.HeaderViewHolder.lambda$8$2(this.f$0, view7, z);
                    }
                });
            }
            ViewBinding viewBinding2 = this.binding;
            FragmentHomeHeadBinding fragmentHomeHeadBinding = viewBinding2 instanceof FragmentHomeHeadBinding ? (FragmentHomeHeadBinding) viewBinding2 : null;
            if (fragmentHomeHeadBinding != null) {
                fragmentHomeHeadBinding.homeSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$8$1
                    @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
                    public boolean onQueryTextSubmit(String query) {
                        Intrinsics.checkNotNullParameter(query, "query");
                        this.this$0.getViewModel().queryTextSubmit(query);
                        return true;
                    }

                    @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
                    public boolean onQueryTextChange(String newText) {
                        Intrinsics.checkNotNullParameter(newText, "newText");
                        this.this$0.getViewModel().queryTextChange(newText);
                        return true;
                    }
                });
            }
        }

        public final ViewBinding getBinding() {
            return this.binding;
        }

        public final HomeViewModel getViewModel() {
            return this.viewModel;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.lagradost.cloudstream3.ui.ViewHolderState
        public Bundle save() {
            Bundle bundle = new Bundle();
            RecyclerView.LayoutManager layoutManager = this.resumeRecyclerView.getLayoutManager();
            bundle.putParcelable("resumeRecyclerView", layoutManager != null ? layoutManager.onSaveInstanceState() : null);
            RecyclerView.LayoutManager layoutManager2 = this.bookmarkRecyclerView.getLayoutManager();
            bundle.putParcelable("bookmarkRecyclerView", layoutManager2 != null ? layoutManager2.onSaveInstanceState() : null);
            return bundle;
        }

        public final HomeScrollAdapter getPreviewAdapter() {
            return this.previewAdapter;
        }

        static final Unit previewAdapter$lambda$0(HeaderViewHolder headerViewHolder, View view, int i, LoadResponse item) {
            Intrinsics.checkNotNullParameter(view, "view");
            Intrinsics.checkNotNullParameter(item, "item");
            headerViewHolder.viewModel.click(new LoadClickCallback(0, view, i, item));
            return Unit.INSTANCE;
        }

        static final Unit resumeAdapter$lambda$1(final HeaderViewHolder headerViewHolder, View v) {
            Intrinsics.checkNotNullParameter(v, "v");
            try {
                Context context = v.getContext();
                if (context == null) {
                    return Unit.INSTANCE;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(R.string.clear_history);
                String string = context.getString(R.string.delete_message);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                String str = String.format(string, Arrays.copyOf(new Object[]{context.getString(R.string.continue_watching)}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                builder.setMessage(str);
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda14
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        HomeParentItemAdapterPreview.HeaderViewHolder.resumeAdapter$lambda$1$0$0(dialogInterface, i);
                    }
                });
                builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda15
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        HomeParentItemAdapterPreview.HeaderViewHolder.resumeAdapter$lambda$1$0$1(this.f$0, dialogInterface, i);
                    }
                });
                AppContextUtils appContextUtils = AppContextUtils.INSTANCE;
                AlertDialog alertDialogShow = builder.show();
                Intrinsics.checkNotNullExpressionValue(alertDialogShow, "show(...)");
                AppContextUtils.setDefaultFocus$default(appContextUtils, alertDialogShow, 0, 1, null);
                return Unit.INSTANCE;
            } catch (Throwable th) {
                ArchComponentExtKt.logError(th);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void resumeAdapter$lambda$1$0$1(HeaderViewHolder headerViewHolder, DialogInterface dialogInterface, int i) {
            DataStoreHelper.INSTANCE.deleteAllResumeStateIds();
            headerViewHolder.viewModel.reloadStored();
        }

        static final Unit resumeAdapter$lambda$0(final HeaderViewHolder headerViewHolder, final SearchClickCallback callback) {
            Activity activity;
            Intrinsics.checkNotNullParameter(callback, "callback");
            if (callback.getAction() != 1) {
                headerViewHolder.viewModel.click(callback);
                return Unit.INSTANCE;
            }
            Context context = callback.getView().getContext();
            if (context != null && (activity = CloudStreamApp.INSTANCE.getActivity(context)) != null) {
                SingleSelectionHelper.INSTANCE.showOptionSelectStringRes(activity, callback.getView(), callback.getCard().getPosterUrl(), CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(R.string.action_open_watching), Integer.valueOf(R.string.action_remove_watching)}), CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(R.string.action_open_play), Integer.valueOf(R.string.action_open_watching), Integer.valueOf(R.string.action_remove_watching)}), new Function1() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda26
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return HomeParentItemAdapterPreview.HeaderViewHolder.resumeAdapter$lambda$0$0(this.f$0, callback, (Pair) obj);
                    }
                });
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit resumeAdapter$lambda$0$0(HeaderViewHolder headerViewHolder, SearchClickCallback searchClickCallback, Pair pair) {
            Intrinsics.checkNotNullParameter(pair, "<destruct>");
            int iIntValue = ((Number) pair.component2()).intValue() + (!((Boolean) pair.component1()).booleanValue() ? 1 : 0);
            if (iIntValue == 0) {
                headerViewHolder.viewModel.click(new SearchClickCallback(1, searchClickCallback.getView(), -1, searchClickCallback.getCard()));
            } else if (iIntValue == 1) {
                headerViewHolder.viewModel.click(new SearchClickCallback(0, searchClickCallback.getView(), -1, searchClickCallback.getCard()));
            } else if (iIntValue == 2) {
                SearchResponse card = searchClickCallback.getCard();
                if (card instanceof DataStoreHelper.ResumeWatchingResult) {
                    DataStoreHelper.INSTANCE.removeLastWatched(((DataStoreHelper.ResumeWatchingResult) card).getParentId());
                    headerViewHolder.viewModel.reloadStored();
                }
            }
            return Unit.INSTANCE;
        }

        static final Unit bookmarkAdapter$lambda$0(HeaderViewHolder headerViewHolder, SearchClickCallback callback) {
            Intrinsics.checkNotNullParameter(callback, "callback");
            if (callback.getAction() != 1) {
                headerViewHolder.viewModel.click(callback);
                return Unit.INSTANCE;
            }
            Context context = callback.getView().getContext();
            Activity activity = context != null ? CloudStreamApp.INSTANCE.getActivity(context) : null;
            MainActivity mainActivity = activity instanceof MainActivity ? (MainActivity) activity : null;
            if (mainActivity != null) {
                mainActivity.loadPopup(callback.getCard(), false);
            }
            return Unit.INSTANCE;
        }

        /* JADX WARN: Code duplicated, block: B:31:0x0091 A[PHI: r6
          0x0091: PHI (r6v65 java.lang.Double) = (r6v50 java.lang.Double), (r6v53 java.lang.Double) binds: [B:30:0x008f, B:36:0x00a6] A[DONT_GENERATE, DONT_INLINE]] */
        public final void onSelect(final LoadResponse item, final int position) {
            CharSequence charSequenceHtml;
            List<String> listEmptyList;
            List listTake;
            double dDoubleValue;
            int color;
            Intrinsics.checkNotNullParameter(item, "item");
            ViewBinding viewBinding = this.binding;
            FragmentHomeHeadTvBinding fragmentHomeHeadTvBinding = viewBinding instanceof FragmentHomeHeadTvBinding ? (FragmentHomeHeadTvBinding) viewBinding : null;
            if (fragmentHomeHeadTvBinding != null) {
                TextView homePreviewDescription = fragmentHomeHeadTvBinding.homePreviewDescription;
                Intrinsics.checkNotNullExpressionValue(homePreviewDescription, "homePreviewDescription");
                TextView textView = homePreviewDescription;
                String plot = item.getPlot();
                boolean z = true;
                textView.setVisibility(plot == null || StringsKt.isBlank(plot) ? 8 : 0);
                TextView textView2 = fragmentHomeHeadTvBinding.homePreviewDescription;
                String plot2 = item.getPlot();
                if (plot2 == null || (charSequenceHtml = AppContextUtils.INSTANCE.html(plot2)) == null) {
                    charSequenceHtml = "";
                }
                textView2.setText(charSequenceHtml);
                Score score = item.getScore();
                String stringNull$default = score != null ? Score.toStringNull$default(score, 0.1d, 10, 1, false, (char) 0, 16, null) : null;
                if (stringNull$default != null) {
                    fragmentHomeHeadTvBinding.homePreviewScore.setText(fragmentHomeHeadTvBinding.homePreviewScore.getContext().getString(R.string.extension_rating, stringNull$default));
                    Double doubleOrNull = StringsKt.toDoubleOrNull(stringNull$default);
                    if (doubleOrNull == null) {
                        Score score2 = item.getScore();
                        doubleOrNull = score2 != null ? Double.valueOf(Score.toDouble$default(score2, 0, 1, null)) : null;
                        if (doubleOrNull != null) {
                            dDoubleValue = doubleOrNull.doubleValue();
                        } else {
                            dDoubleValue = 0.0d;
                        }
                    } else {
                        dDoubleValue = doubleOrNull.doubleValue();
                    }
                    if (dDoubleValue < 5.0d) {
                        color = Color.parseColor("#eb2f2f");
                    } else if (dDoubleValue < 8.0d) {
                        color = Color.parseColor("#eda009");
                    } else {
                        color = Color.parseColor("#3bb33b");
                    }
                    fragmentHomeHeadTvBinding.homePreviewScore.setBackgroundTintList(ColorStateList.valueOf(color));
                }
                TextView homePreviewScore = fragmentHomeHeadTvBinding.homePreviewScore;
                Intrinsics.checkNotNullExpressionValue(homePreviewScore, "homePreviewScore");
                homePreviewScore.setVisibility(stringNull$default == null ? 8 : 0);
                Integer year = item.getYear();
                if (year != null) {
                    fragmentHomeHeadTvBinding.homePreviewYear.setText(String.valueOf(year.intValue()));
                }
                TextView homePreviewYear = fragmentHomeHeadTvBinding.homePreviewYear;
                Intrinsics.checkNotNullExpressionValue(homePreviewYear, "homePreviewYear");
                homePreviewYear.setVisibility(item.getYear() == null ? 8 : 0);
                Integer duration = item.getDuration();
                if (duration != null) {
                    fragmentHomeHeadTvBinding.homePreviewDuration.setText(fragmentHomeHeadTvBinding.homePreviewDuration.getContext().getString(R.string.duration_format, Integer.valueOf(duration.intValue())));
                }
                TextView homePreviewDuration = fragmentHomeHeadTvBinding.homePreviewDuration;
                Intrinsics.checkNotNullExpressionValue(homePreviewDuration, "homePreviewDuration");
                homePreviewDuration.setVisibility(duration == null || duration.intValue() <= 0 ? 8 : 0);
                List<ActorData> actors = item.getActors();
                String strJoinToString$default = (actors == null || (listTake = CollectionsKt.take(actors, 3)) == null) ? null : CollectionsKt.joinToString$default(listTake, ", ", null, null, 0, null, new Function1() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda18
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return HomeParentItemAdapterPreview.HeaderViewHolder.onSelect$lambda$0$3((ActorData) obj);
                    }
                }, 30, null);
                String str = strJoinToString$default;
                if (str != null && !StringsKt.isBlank(str)) {
                    fragmentHomeHeadTvBinding.homePreviewCast.setText(fragmentHomeHeadTvBinding.homePreviewCast.getContext().getString(R.string.cast_format, strJoinToString$default));
                    TextView homePreviewCast = fragmentHomeHeadTvBinding.homePreviewCast;
                    Intrinsics.checkNotNullExpressionValue(homePreviewCast, "homePreviewCast");
                    homePreviewCast.setVisibility(0);
                } else {
                    TextView homePreviewCast2 = fragmentHomeHeadTvBinding.homePreviewCast;
                    Intrinsics.checkNotNullExpressionValue(homePreviewCast2, "homePreviewCast");
                    homePreviewCast2.setVisibility(8);
                }
                fragmentHomeHeadTvBinding.homePreviewText.setText(AppContextUtils.INSTANCE.html(item.getName()));
                UIHelper uIHelper = UIHelper.INSTANCE;
                ChipGroup chipGroup = fragmentHomeHeadTvBinding.homePreviewTags;
                List<String> tags = item.getTags();
                if (tags == null || (listEmptyList = CollectionsKt.take(tags, 6)) == null) {
                    listEmptyList = CollectionsKt.emptyList();
                }
                uIHelper.populateChips(chipGroup, listEmptyList, R.style.ChipFilledSemiTransparent, null);
                ResultFragment resultFragment = ResultFragment.INSTANCE;
                String logoUrl = item.getLogoUrl();
                Map<String, String> posterHeaders = item.getPosterHeaders();
                TextView homePreviewText = fragmentHomeHeadTvBinding.homePreviewText;
                Intrinsics.checkNotNullExpressionValue(homePreviewText, "homePreviewText");
                ImageView homeBackgroundPosterWatermarkBadgeHolder = fragmentHomeHeadTvBinding.homeBackgroundPosterWatermarkBadgeHolder;
                Intrinsics.checkNotNullExpressionValue(homeBackgroundPosterWatermarkBadgeHolder, "homeBackgroundPosterWatermarkBadgeHolder");
                resultFragment.bindLogo(logoUrl, posterHeaders, homeBackgroundPosterWatermarkBadgeHolder, homePreviewText);
                ChipGroup homePreviewTags = fragmentHomeHeadTvBinding.homePreviewTags;
                Intrinsics.checkNotNullExpressionValue(homePreviewTags, "homePreviewTags");
                ChipGroup chipGroup2 = homePreviewTags;
                List<String> tags2 = item.getTags();
                if (tags2 != null && !tags2.isEmpty()) {
                    z = false;
                }
                chipGroup2.setVisibility(z ? 8 : 0);
                fragmentHomeHeadTvBinding.homePreviewInfoBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda19
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        HomeParentItemAdapterPreview.HeaderViewHolder.onSelect$lambda$0$4(this.f$0, position, item, view);
                    }
                });
            }
            ViewBinding viewBinding2 = this.binding;
            final FragmentHomeHeadBinding fragmentHomeHeadBinding = viewBinding2 instanceof FragmentHomeHeadBinding ? (FragmentHomeHeadBinding) viewBinding2 : null;
            if (fragmentHomeHeadBinding != null) {
                fragmentHomeHeadBinding.homePreviewPlay.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda20
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        HomeParentItemAdapterPreview.HeaderViewHolder.onSelect$lambda$1$0(this.f$0, position, item, view);
                    }
                });
                fragmentHomeHeadBinding.homePreviewInfo.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda22
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        HomeParentItemAdapterPreview.HeaderViewHolder.onSelect$lambda$1$1(this.f$0, position, item, view);
                    }
                });
                final int id = ResultViewModel2Kt.getId(item);
                WatchType resultWatchState = DataStoreHelper.INSTANCE.getResultWatchState(id);
                fragmentHomeHeadBinding.homePreviewBookmark.setText(resultWatchState.getStringRes());
                fragmentHomeHeadBinding.homePreviewBookmark.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, ContextCompat.getDrawable(fragmentHomeHeadBinding.homePreviewBookmark.getContext(), resultWatchState.getIconRes()), (Drawable) null, (Drawable) null);
                fragmentHomeHeadBinding.homePreviewBookmark.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda23
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        HomeParentItemAdapterPreview.HeaderViewHolder.onSelect$lambda$1$2(id, item, fragmentHomeHeadBinding, view);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final CharSequence onSelect$lambda$0$3(ActorData it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return it.getActor().getName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onSelect$lambda$0$4(HeaderViewHolder headerViewHolder, int i, LoadResponse loadResponse, View view) {
            HomeViewModel homeViewModel = headerViewHolder.viewModel;
            Intrinsics.checkNotNull(view);
            homeViewModel.click(new LoadClickCallback(0, view, i, loadResponse));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onSelect$lambda$1$0(HeaderViewHolder headerViewHolder, int i, LoadResponse loadResponse, View view) {
            HomeViewModel homeViewModel = headerViewHolder.viewModel;
            Intrinsics.checkNotNull(view);
            homeViewModel.click(new LoadClickCallback(1, view, i, loadResponse));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onSelect$lambda$1$1(HeaderViewHolder headerViewHolder, int i, LoadResponse loadResponse, View view) {
            HomeViewModel homeViewModel = headerViewHolder.viewModel;
            Intrinsics.checkNotNull(view);
            homeViewModel.click(new LoadClickCallback(0, view, i, loadResponse));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onSelect$lambda$1$2(int i, final LoadResponse loadResponse, final FragmentHomeHeadBinding fragmentHomeHeadBinding, final View view) {
            CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
            Context context = view.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            Activity activity = companion.getActivity(context);
            if (activity != null) {
                SingleSelectionHelper singleSelectionHelper = SingleSelectionHelper.INSTANCE;
                EnumEntries<WatchType> entries = WatchType.getEntries();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(entries, 10));
                Iterator<WatchType> it = entries.iterator();
                while (it.hasNext()) {
                    arrayList.add(view.getContext().getString(it.next().getStringRes()));
                }
                List<String> list = CollectionsKt.toList(arrayList);
                int iOrdinal = DataStoreHelper.INSTANCE.getResultWatchState(i).ordinal();
                String string = view.getContext().getString(R.string.action_add_to_bookmarks);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                singleSelectionHelper.showBottomDialog(activity, list, iOrdinal, string, false, new Function0() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda16
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Unit.INSTANCE;
                    }
                }, new Function1() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda17
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return HomeParentItemAdapterPreview.HeaderViewHolder.onSelect$lambda$1$2$2(view, loadResponse, fragmentHomeHeadBinding, ((Integer) obj).intValue());
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit onSelect$lambda$1$2$2(View view, LoadResponse loadResponse, final FragmentHomeHeadBinding fragmentHomeHeadBinding, int i) {
            final WatchType watchType = WatchType.getEntries().get(i);
            new ResultViewModel2().updateWatchStatus(watchType, view.getContext(), loadResponse, new Function1() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return HomeParentItemAdapterPreview.HeaderViewHolder.onSelect$lambda$1$2$2$0(fragmentHomeHeadBinding, watchType, ((Boolean) obj).booleanValue());
                }
            });
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit onSelect$lambda$1$2$2$0(FragmentHomeHeadBinding fragmentHomeHeadBinding, WatchType watchType, boolean z) {
            if (!z) {
                return Unit.INSTANCE;
            }
            fragmentHomeHeadBinding.homePreviewBookmark.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, ContextCompat.getDrawable(fragmentHomeHeadBinding.homePreviewBookmark.getContext(), watchType.getIconRes()), (Drawable) null, (Drawable) null);
            fragmentHomeHeadBinding.homePreviewBookmark.setText(watchType.getStringRes());
            return Unit.INSTANCE;
        }

        public final void onViewDetachedFromWindow() {
            this.previewViewpager.unregisterOnPageChangeCallback(this.previewCallback);
        }

        static final void _init_$lambda$0(HeaderViewHolder headerViewHolder, WatchType watchType, CompoundButton compoundButton, boolean z) {
            Intrinsics.checkNotNullParameter(compoundButton, "<unused var>");
            if (z) {
                headerViewHolder.viewModel.loadStoredData(SetsKt.setOf(watchType));
                return;
            }
            List<Pair<Chip, WatchType>> list = headerViewHolder.toggleList;
            if (!(list instanceof Collection) || !list.isEmpty()) {
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    if (((Chip) ((Pair) it.next()).getFirst()).isChecked()) {
                        return;
                    }
                }
            }
            headerViewHolder.viewModel.loadStoredData(SetsKt.emptySet());
        }

        static final Unit _init_$lambda$1(HeaderViewHolder headerViewHolder, DataStoreHelper.Account account) {
            ImageView imageView = headerViewHolder.headProfilePic;
            if (imageView != null) {
                ImageLoader.loadImage$default(ImageLoader.INSTANCE, imageView, account != null ? account.getImage() : null, (Function1) null, 2, (Object) null);
            }
            ImageView imageView2 = headerViewHolder.alternateHeadProfilePic;
            if (imageView2 != null) {
                ImageLoader.loadImage$default(ImageLoader.INSTANCE, imageView2, account != null ? account.getImage() : null, (Function1) null, 2, (Object) null);
            }
            return Unit.INSTANCE;
        }

        static final void _init_$lambda$2(View view) {
            Activity activity = CommonActivity.INSTANCE.getActivity();
            if (activity != null) {
                AccountHelper.INSTANCE.showAccountSelectLinear(activity);
            }
        }

        private static final boolean _init_$showAccountEditBox(final AccountViewModel accountViewModel, final Context context) {
            DataStoreHelper.Account currentAccount = DataStoreHelper.INSTANCE.getCurrentAccount();
            if (currentAccount == null) {
                return false;
            }
            AccountHelper.INSTANCE.showAccountEditDialog(context, currentAccount, false, new Function1() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda32
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return HomeParentItemAdapterPreview.HeaderViewHolder.showAccountEditBox$lambda$3(accountViewModel, context, (DataStoreHelper.Account) obj);
                }
            }, new Function1() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda33
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return HomeParentItemAdapterPreview.HeaderViewHolder.showAccountEditBox$lambda$4(accountViewModel, context, (DataStoreHelper.Account) obj);
                }
            });
            return true;
        }

        static final Unit showAccountEditBox$lambda$3(AccountViewModel accountViewModel, Context context, DataStoreHelper.Account it) {
            Intrinsics.checkNotNullParameter(it, "it");
            accountViewModel.handleAccountUpdate(it, context);
            return Unit.INSTANCE;
        }

        static final Unit showAccountEditBox$lambda$4(AccountViewModel accountViewModel, Context context, DataStoreHelper.Account it) {
            Intrinsics.checkNotNullParameter(it, "it");
            accountViewModel.handleAccountDelete(it, context);
            return Unit.INSTANCE;
        }

        static final boolean _init_$lambda$5(AccountViewModel accountViewModel, View view) {
            Context context = view.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            return _init_$showAccountEditBox(accountViewModel, context);
        }

        static final boolean _init_$lambda$6(AccountViewModel accountViewModel, View view) {
            Context context = view.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            return _init_$showAccountEditBox(accountViewModel, context);
        }

        static final void _init_$lambda$7(View view) {
            Activity activity = CommonActivity.INSTANCE.getActivity();
            if (activity != null) {
                AccountHelper.INSTANCE.showAccountSelectLinear(activity);
            }
        }

        static final void lambda$8$0(Ref.LongRef longRef, View view, boolean z) {
            long j = longRef.element;
            longRef.element = System.currentTimeMillis();
            if (z && j + 500 < System.currentTimeMillis()) {
                MainActivity.INSTANCE.centerView(view);
            }
        }

        static final void lambda$8$1(HeaderViewHolder headerViewHolder, FragmentHomeHeadTvBinding fragmentHomeHeadTvBinding, View view, boolean z) {
            if (z) {
                ViewPager2 viewPager2 = headerViewHolder.previewViewpager;
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1, true);
                fragmentHomeHeadTvBinding.homePreviewInfoBtt.requestFocus();
            }
        }

        static final void lambda$8$2(HeaderViewHolder headerViewHolder, View view, boolean z) {
            ActivityMainBinding binding;
            NavigationRailView navigationRailView;
            NavigationBarItemView navigationBarItemView;
            if (z) {
                if (headerViewHolder.previewViewpager.getCurrentItem() <= 0) {
                    Activity activity = CommonActivity.INSTANCE.getActivity();
                    MainActivity mainActivity = activity instanceof MainActivity ? (MainActivity) activity : null;
                    if (mainActivity == null || (binding = mainActivity.getBinding()) == null || (navigationRailView = binding.navRailView) == null || (navigationBarItemView = (NavigationBarItemView) navigationRailView.findViewById(R.id.navigation_home)) == null) {
                        return;
                    }
                    navigationBarItemView.requestFocus();
                    return;
                }
                ViewPager2 viewPager2 = headerViewHolder.previewViewpager;
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() - 1, true);
                ((FragmentHomeHeadTvBinding) headerViewHolder.binding).homePreviewInfoBtt.requestFocus();
            }
        }

        private final void updatePreview(Resource<? extends Pair<Boolean, ? extends List<? extends LoadResponse>>> preview) {
            FragmentHomeHeadTvBinding fragmentHomeHeadTvBinding;
            boolean z = preview instanceof Resource.Success;
            if (z) {
                View view = this.homeNonePadding;
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = 0;
                view.setLayoutParams(layoutParams);
            } else {
                UIHelper.INSTANCE.fixPaddingStatusbarView(this.homeNonePadding);
            }
            HomeScrollAdapter homeScrollAdapter = this.previewAdapter;
            if (z) {
                Resource.Success success = (Resource.Success) preview;
                BaseAdapter.submitList$default(homeScrollAdapter, (Collection) ((Pair) success.getValue()).getSecond(), null, 2, null);
                this.previewAdapter.setHasMoreItems(((Boolean) ((Pair) success.getValue()).getFirst()).booleanValue());
                this.previewViewpager.setVisibility(0);
                this.previewViewpagerText.setVisibility(0);
                View view2 = this.alternativeAccountPadding;
                if (view2 != null) {
                    view2.setVisibility(8);
                }
                ViewBinding viewBinding = this.binding;
                fragmentHomeHeadTvBinding = viewBinding instanceof FragmentHomeHeadTvBinding ? (FragmentHomeHeadTvBinding) viewBinding : null;
                if (fragmentHomeHeadTvBinding != null) {
                    CardView homePreviewInfoBtt = fragmentHomeHeadTvBinding.homePreviewInfoBtt;
                    Intrinsics.checkNotNullExpressionValue(homePreviewInfoBtt, "homePreviewInfoBtt");
                    homePreviewInfoBtt.setVisibility(0);
                }
                int currentItem = this.previewViewpager.getCurrentItem();
                LoadResponse loadResponse = (LoadResponse) CollectionsKt.getOrNull((List) ((Pair) success.getValue()).getSecond(), currentItem);
                if (loadResponse != null) {
                    onSelect(loadResponse, currentItem);
                    return;
                }
                return;
            }
            BaseAdapter.submitList$default(homeScrollAdapter, CollectionsKt.emptyList(), null, 2, null);
            this.previewViewpager.setCurrentItem(0, false);
            this.previewViewpager.setVisibility(8);
            this.previewViewpagerText.setVisibility(8);
            View view3 = this.alternativeAccountPadding;
            if (view3 != null) {
                view3.setVisibility(0);
            }
            ViewBinding viewBinding2 = this.binding;
            fragmentHomeHeadTvBinding = viewBinding2 instanceof FragmentHomeHeadTvBinding ? (FragmentHomeHeadTvBinding) viewBinding2 : null;
            if (fragmentHomeHeadTvBinding != null) {
                CardView homePreviewInfoBtt2 = fragmentHomeHeadTvBinding.homePreviewInfoBtt;
                Intrinsics.checkNotNullExpressionValue(homePreviewInfoBtt2, "homePreviewInfoBtt");
                homePreviewInfoBtt2.setVisibility(8);
            }
        }

        private final void updateResume(final List<? extends SearchResponse> resumeWatching) {
            TextView textView;
            List<? extends SearchResponse> list = resumeWatching;
            this.resumeHolder.setVisibility(!list.isEmpty() ? 0 : 8);
            final TextView textView2 = null;
            BaseAdapter.submitList$default(this.resumeAdapter, list, null, 2, null);
            ViewBinding viewBinding = this.binding;
            if ((viewBinding instanceof FragmentHomeHeadBinding) || ((viewBinding instanceof FragmentHomeHeadTvBinding) && Globals.INSTANCE.isLayout(4))) {
                ViewBinding viewBinding2 = this.binding;
                FragmentHomeHeadBinding fragmentHomeHeadBinding = viewBinding2 instanceof FragmentHomeHeadBinding ? (FragmentHomeHeadBinding) viewBinding2 : null;
                if (fragmentHomeHeadBinding == null || (textView = fragmentHomeHeadBinding.homeWatchParentItemTitle) == null) {
                    ViewBinding viewBinding3 = this.binding;
                    FragmentHomeHeadTvBinding fragmentHomeHeadTvBinding = viewBinding3 instanceof FragmentHomeHeadTvBinding ? (FragmentHomeHeadTvBinding) viewBinding3 : null;
                    if (fragmentHomeHeadTvBinding != null) {
                        textView2 = fragmentHomeHeadTvBinding.homeWatchParentItemTitle;
                    }
                } else {
                    textView2 = textView;
                }
                if (textView2 != null) {
                    textView2.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda24
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            HomeParentItemAdapterPreview.HeaderViewHolder headerViewHolder = this.f$0;
                            headerViewHolder.viewModel.popup(new HomeViewModel.ExpandableHomepageList(new HomePageList(textView2.getText().toString(), resumeWatching, false), 1, false), new Function0() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda25
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return HomeParentItemAdapterPreview.HeaderViewHolder.updateResume$lambda$0$0(headerViewHolder);
                                }
                            });
                        }
                    });
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit updateResume$lambda$0$0(HeaderViewHolder headerViewHolder) {
            headerViewHolder.viewModel.deleteResumeWatching();
            return Unit.INSTANCE;
        }

        private final void updateBookmarks(Pair<Boolean, ? extends List<? extends SearchResponse>> data) {
            FrameLayout frameLayout;
            boolean zBooleanValue = data.component1().booleanValue();
            final List<? extends SearchResponse> listComponent2 = data.component2();
            this.bookmarkHolder.setVisibility(zBooleanValue ? 0 : 8);
            FrameLayout frameLayout2 = null;
            BaseAdapter.submitList$default(this.bookmarkAdapter, listComponent2, null, 2, null);
            ViewBinding viewBinding = this.binding;
            if ((viewBinding instanceof FragmentHomeHeadBinding) || ((viewBinding instanceof FragmentHomeHeadTvBinding) && Globals.INSTANCE.isLayout(4))) {
                ViewBinding viewBinding2 = this.binding;
                FragmentHomeHeadBinding fragmentHomeHeadBinding = viewBinding2 instanceof FragmentHomeHeadBinding ? (FragmentHomeHeadBinding) viewBinding2 : null;
                if (fragmentHomeHeadBinding == null || (frameLayout = fragmentHomeHeadBinding.homeBookmarkParentItemTitle) == null) {
                    ViewBinding viewBinding3 = this.binding;
                    FragmentHomeHeadTvBinding fragmentHomeHeadTvBinding = viewBinding3 instanceof FragmentHomeHeadTvBinding ? (FragmentHomeHeadTvBinding) viewBinding3 : null;
                    if (fragmentHomeHeadTvBinding != null) {
                        frameLayout2 = fragmentHomeHeadTvBinding.homeBookmarkParentItemTitle;
                    }
                } else {
                    frameLayout2 = frameLayout;
                }
                if (frameLayout2 != null) {
                    frameLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda27
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            HomeParentItemAdapterPreview.HeaderViewHolder.updateBookmarks$lambda$0(this.f$0, listComponent2, view);
                        }
                    });
                }
            }
        }

        static final void updateBookmarks$lambda$0(final HeaderViewHolder headerViewHolder, final List list, View view) {
            List<Pair<Chip, WatchType>> list2 = headerViewHolder.toggleList;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            Iterator<T> it = list2.iterator();
            while (it.hasNext()) {
                arrayList.add((Chip) ((Pair) it.next()).getFirst());
            }
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : arrayList) {
                if (((Chip) obj).isChecked()) {
                    arrayList2.add(obj);
                }
            }
            ArrayList arrayList3 = arrayList2;
            if (arrayList3.isEmpty()) {
                return;
            }
            ArrayList arrayList4 = new ArrayList();
            Iterator it2 = arrayList3.iterator();
            while (it2.hasNext()) {
                CharSequence text = ((Chip) it2.next()).getText();
                if (text != null) {
                    arrayList4.add(text);
                }
            }
            headerViewHolder.viewModel.popup(new HomeViewModel.ExpandableHomepageList(new HomePageList(CollectionsKt.joinToString$default(arrayList4, null, null, null, 0, null, null, 63, null), list, false), 1, false), new Function0() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda21
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return HomeParentItemAdapterPreview.HeaderViewHolder.updateBookmarks$lambda$0$3(this.f$0, list);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit updateBookmarks$lambda$0$3(HeaderViewHolder headerViewHolder, List list) {
            headerViewHolder.viewModel.deleteBookmarks(list);
            return Unit.INSTANCE;
        }

        public final void onViewAttachedToWindow() {
            this.previewViewpager.registerOnPageChangeCallback(this.previewCallback);
            ViewPager2 viewPager2 = this.previewViewpager;
            LifecycleKt.observe(viewPager2, this.viewModel.getPreview(), new Function1() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda28
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return HomeParentItemAdapterPreview.HeaderViewHolder.onViewAttachedToWindow$lambda$0$0(this.f$0, (Resource) obj);
                }
            });
            LifecycleKt.observe(viewPager2, this.viewModel.getResumeWatching(), new Function1() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda29
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return HomeParentItemAdapterPreview.HeaderViewHolder.onViewAttachedToWindow$lambda$0$1(this.f$0, (List) obj);
                }
            });
            LifecycleKt.observe(viewPager2, this.viewModel.getBookmarks(), new Function1() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda30
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return HomeParentItemAdapterPreview.HeaderViewHolder.onViewAttachedToWindow$lambda$0$2(this.f$0, (Pair) obj);
                }
            });
            LifecycleKt.observe(viewPager2, this.viewModel.getAvailableWatchStatusTypes(), new Function1() { // from class: com.lagradost.cloudstream3.ui.home.HomeParentItemAdapterPreview$HeaderViewHolder$$ExternalSyntheticLambda31
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return HomeParentItemAdapterPreview.HeaderViewHolder.onViewAttachedToWindow$lambda$0$3(this.f$0, (Pair) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit onViewAttachedToWindow$lambda$0$0(HeaderViewHolder headerViewHolder, Resource it) {
            Intrinsics.checkNotNullParameter(it, "it");
            headerViewHolder.updatePreview(it);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit onViewAttachedToWindow$lambda$0$1(HeaderViewHolder headerViewHolder, List it) {
            Intrinsics.checkNotNullParameter(it, "it");
            headerViewHolder.updateResume(it);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit onViewAttachedToWindow$lambda$0$2(HeaderViewHolder headerViewHolder, Pair it) {
            Intrinsics.checkNotNullParameter(it, "it");
            headerViewHolder.updateBookmarks(it);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit onViewAttachedToWindow$lambda$0$3(HeaderViewHolder headerViewHolder, Pair pair) {
            Intrinsics.checkNotNullParameter(pair, "<destruct>");
            Set set = (Set) pair.component1();
            Set set2 = (Set) pair.component2();
            Iterator<Pair<Chip, WatchType>> it = headerViewHolder.toggleList.iterator();
            while (true) {
                int i = 0;
                if (!it.hasNext()) {
                    break;
                }
                Pair<Chip, WatchType> next = it.next();
                Chip chipComponent1 = next.component1();
                WatchType watchTypeComponent2 = next.component2();
                Chip chip = chipComponent1;
                if (!set2.contains(watchTypeComponent2)) {
                    i = 8;
                }
                chip.setVisibility(i);
                chipComponent1.setChecked(set.contains(watchTypeComponent2));
            }
            ChipGroup chipGroup = headerViewHolder.toggleListHolder;
            if (chipGroup != null) {
                chipGroup.setVisibility(set2.isEmpty() ? 8 : 0);
            }
            return Unit.INSTANCE;
        }

        @Override // com.lagradost.cloudstream3.ui.ViewHolderState
        public void restore(Bundle state) {
            RecyclerView.LayoutManager layoutManager;
            RecyclerView.LayoutManager layoutManager2;
            Intrinsics.checkNotNullParameter(state, "state");
            Parcelable parcelable = (Parcelable) (Build.VERSION.SDK_INT < 33 ? state.getParcelable("resumeRecyclerView") : state.getParcelable("resumeRecyclerView", Parcelable.class));
            if (parcelable != null && (layoutManager2 = this.resumeRecyclerView.getLayoutManager()) != null) {
                layoutManager2.onRestoreInstanceState(parcelable);
            }
            Parcelable parcelable2 = (Parcelable) (Build.VERSION.SDK_INT < 33 ? state.getParcelable("bookmarkRecyclerView") : state.getParcelable("bookmarkRecyclerView", Parcelable.class));
            if (parcelable2 == null || (layoutManager = this.bookmarkRecyclerView.getLayoutManager()) == null) {
                return;
            }
            layoutManager.onRestoreInstanceState(parcelable2);
        }
    }
}
