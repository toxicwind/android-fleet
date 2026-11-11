package com.lagradost.cloudstream3.ui.result;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;
import coil3.Image;
import coil3.request.ImageRequest;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.lagradost.cloudstream3.ActorData;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.DubStatus;
import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.MainActivity;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.SearchResponse;
import com.lagradost.cloudstream3.databinding.FragmentResultTvBinding;
import com.lagradost.cloudstream3.mvvm.LifecycleKt;
import com.lagradost.cloudstream3.mvvm.Resource;
import com.lagradost.cloudstream3.services.SubscriptionWorkManager;
import com.lagradost.cloudstream3.ui.AutofitRecyclerView;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.BaseAdapterKt;
import com.lagradost.cloudstream3.ui.BaseFragment;
import com.lagradost.cloudstream3.ui.MaxRecyclerView;
import com.lagradost.cloudstream3.ui.WatchType;
import com.lagradost.cloudstream3.ui.download.DownloadButtonSetup;
import com.lagradost.cloudstream3.ui.download.DownloadClickEvent;
import com.lagradost.cloudstream3.ui.player.ExtractorLinkGenerator;
import com.lagradost.cloudstream3.ui.player.GeneratorPlayer;
import com.lagradost.cloudstream3.ui.quicksearch.QuickSearchFragment;
import com.lagradost.cloudstream3.ui.search.SearchAdapter;
import com.lagradost.cloudstream3.ui.search.SearchClickCallback;
import com.lagradost.cloudstream3.ui.search.SearchHelper;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.BackPressedCallbackHelper;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.ImageLoader;
import com.lagradost.cloudstream3.utils.ImageUtilKt;
import com.lagradost.cloudstream3.utils.PercentageCropImageView;
import com.lagradost.cloudstream3.utils.SingleSelectionHelper;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UIHelper;
import com.lagradost.cloudstream3.utils.UiText;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.random.Random;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: ResultFragmentTv.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u000b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J&\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u0017\u0010\u0011\u001a\u00020\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0002¢\u0006\u0002\u0010\u0014J\u0010\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0016\u0010\u001b\u001a\u00020\b*\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u0013H\u0002J.\u0010\u001e\u001a\u00020\b*\u0004\u0018\u00010\u001c2\u001e\u0010\u0019\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0006\u0012\u0004\u0018\u00010 \u0012\u0004\u0012\u00020\u001a0\u001fj\u0002`!0\u0016H\u0002J\u000e\u0010\"\u001a\u00020\b*\u0004\u0018\u00010\u001cH\u0002J\b\u0010#\u001a\u00020\bH\u0002J\"\u0010$\u001a\u00020\b2\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u00162\b\u0010&\u001a\u0004\u0018\u00010'H\u0002J\u0010\u00101\u001a\u00020\b2\u0006\u00102\u001a\u000203H\u0002J\b\u00104\u001a\u00020\bH\u0016J\b\u00105\u001a\u00020\bH\u0016J\u0014\u00106\u001a\u00020\b*\u00020\n2\u0006\u00107\u001a\u000203H\u0002J\u0010\u00108\u001a\u00020\b2\u0006\u00109\u001a\u000203H\u0002J\u0010\u0010:\u001a\u00020\b2\u0006\u0010;\u001a\u00020\nH\u0016J\u0010\u0010<\u001a\u00020\b2\u0006\u0010=\u001a\u00020\u0002H\u0017R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010(\u001a\u0004\u0018\u00010)X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001c\u0010.\u001a\u0004\u0018\u00010)X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010+\"\u0004\b0\u0010-¨\u0006>"}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/ResultFragmentTv;", "Lcom/lagradost/cloudstream3/ui/BaseFragment;", "Lcom/lagradost/cloudstream3/databinding/FragmentResultTvBinding;", "<init>", "()V", "viewModel", "Lcom/lagradost/cloudstream3/ui/result/ResultViewModel2;", "onDestroyView", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "updateUI", "id", "", "(Ljava/lang/Integer;)V", "currentRecommendations", "", "Lcom/lagradost/cloudstream3/SearchResponse;", "handleSelection", "data", "", "select", "Landroidx/recyclerview/widget/RecyclerView;", "index", "update", "Lkotlin/Pair;", "Lcom/lagradost/cloudstream3/utils/UiText;", "Lcom/lagradost/cloudstream3/ui/result/SelectData;", "setAdapter", "focusPlayButton", "setRecommendations", "rec", "validApiName", "", "loadingDialog", "Landroid/app/Dialog;", "getLoadingDialog", "()Landroid/app/Dialog;", "setLoadingDialog", "(Landroid/app/Dialog;)V", "popupDialog", "getPopupDialog", "setPopupDialog", "reloadViewModel", "forceReload", "", "onResume", "onStop", "fade", "turnVisible", "toggleEpisodes", "show", "fixLayout", "view", "onBindingCreated", "binding", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ResultFragmentTv extends BaseFragment<FragmentResultTvBinding> {
    private List<? extends SearchResponse> currentRecommendations;
    private Dialog loadingDialog;
    private Dialog popupDialog;
    private ResultViewModel2 viewModel;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$1, reason: invalid class name */
    /* JADX INFO: compiled from: ResultFragmentTv.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function3<LayoutInflater, ViewGroup, Boolean, FragmentResultTvBinding> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(3, FragmentResultTvBinding.class, "inflate", "inflate(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Z)Lcom/lagradost/cloudstream3/databinding/FragmentResultTvBinding;", 0);
        }

        public final FragmentResultTvBinding invoke(LayoutInflater p0, ViewGroup viewGroup, boolean z) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return FragmentResultTvBinding.inflate(p0, viewGroup, z);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ FragmentResultTvBinding invoke(LayoutInflater layoutInflater, ViewGroup viewGroup, Boolean bool) {
            return invoke(layoutInflater, viewGroup, bool.booleanValue());
        }
    }

    public ResultFragmentTv() {
        super(new BaseFragment.BindingCreator.Inflate(AnonymousClass1.INSTANCE));
        this.currentRecommendations = CollectionsKt.emptyList();
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$onDestroyView$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ResultFragmentTv.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C08781 extends FunctionReferenceImpl implements Function1<Integer, Unit> {
        C08781(Object obj) {
            super(1, obj, ResultFragmentTv.class, "updateUI", "updateUI(Ljava/lang/Integer;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
            invoke2(num);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Integer num) {
            ((ResultFragmentTv) this.receiver).updateUI(num);
        }
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        ResultFragment.INSTANCE.getUpdateUIEvent().minusAssign(new C08781(this));
        FragmentActivity activity = getActivity();
        if (activity != null) {
            String string = toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            BackPressedCallbackHelper.INSTANCE.detachBackPressedCallback(activity, string);
        }
        super.onDestroyView();
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ResultViewModel2 resultViewModel2 = (ResultViewModel2) new ViewModelProvider(this).get(ResultViewModel2.class);
        this.viewModel = resultViewModel2;
        if (resultViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel2 = null;
        }
        resultViewModel2.setEPISODE_RANGE_SIZE(50);
        ResultFragment.INSTANCE.getUpdateUIEvent().plusAssign(new C08771(this));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$onCreateView$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ResultFragmentTv.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C08771 extends FunctionReferenceImpl implements Function1<Integer, Unit> {
        C08771(Object obj) {
            super(1, obj, ResultFragmentTv.class, "updateUI", "updateUI(Ljava/lang/Integer;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
            invoke2(num);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Integer num) {
            ((ResultFragmentTv) this.receiver).updateUI(num);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateUI(Integer id) {
        ResultViewModel2 resultViewModel2 = this.viewModel;
        if (resultViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel2 = null;
        }
        resultViewModel2.reloadEpisodes();
    }

    private final void handleSelection(Object data) {
        ResultViewModel2 resultViewModel2 = null;
        if (data instanceof EpisodeRange) {
            ResultViewModel2 resultViewModel3 = this.viewModel;
            if (resultViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                resultViewModel2 = resultViewModel3;
            }
            resultViewModel2.changeRange((EpisodeRange) data);
            return;
        }
        if (data instanceof Integer) {
            ResultViewModel2 resultViewModel4 = this.viewModel;
            if (resultViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                resultViewModel2 = resultViewModel4;
            }
            resultViewModel2.changeSeason(((Number) data).intValue());
            return;
        }
        if (data instanceof DubStatus) {
            ResultViewModel2 resultViewModel5 = this.viewModel;
            if (resultViewModel5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                resultViewModel2 = resultViewModel5;
            }
            resultViewModel2.changeDubStatus((DubStatus) data);
            return;
        }
        if (data instanceof String) {
            setRecommendations(this.currentRecommendations, (String) data);
        }
    }

    private final void select(RecyclerView recyclerView, int i) {
        RecyclerView.Adapter adapter = recyclerView != null ? recyclerView.getAdapter() : null;
        SelectAdaptor selectAdaptor = adapter instanceof SelectAdaptor ? (SelectAdaptor) adapter : null;
        if (selectAdaptor != null) {
            selectAdaptor.select(i, recyclerView);
        }
    }

    private final void update(RecyclerView recyclerView, List<? extends Pair<? extends UiText, ? extends Object>> list) {
        RecyclerView.Adapter adapter = recyclerView != null ? recyclerView.getAdapter() : null;
        SelectAdaptor selectAdaptor = adapter instanceof SelectAdaptor ? (SelectAdaptor) adapter : null;
        if (selectAdaptor != null) {
            BaseAdapter.submitList$default(selectAdaptor, list, null, 2, null);
        }
        if (recyclerView != null) {
            recyclerView.setVisibility(list.size() > 1 ? 0 : 8);
        }
    }

    private final void setAdapter(RecyclerView recyclerView) {
        if (recyclerView != null) {
            recyclerView.setAdapter(new SelectAdaptor(new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda45
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ResultFragmentTv.setAdapter$lambda$0(this.f$0, obj);
                }
            }));
        }
    }

    static final Unit setAdapter$lambda$0(ResultFragmentTv resultFragmentTv, Object data) {
        Intrinsics.checkNotNullParameter(data, "data");
        resultFragmentTv.handleSelection(data);
        return Unit.INSTANCE;
    }

    private final void focusPlayButton() {
        MaterialButton materialButton;
        MaterialButton materialButton2;
        MaterialButton materialButton3;
        FragmentResultTvBinding binding = getBinding();
        if (binding != null && (materialButton3 = binding.resultPlayMovieButton) != null) {
            materialButton3.requestFocus();
        }
        FragmentResultTvBinding binding2 = getBinding();
        if (binding2 != null && (materialButton2 = binding2.resultPlaySeriesButton) != null) {
            materialButton2.requestFocus();
        }
        FragmentResultTvBinding binding3 = getBinding();
        if (binding3 == null || (materialButton = binding3.resultResumeSeriesButton) == null) {
            return;
        }
        materialButton.requestFocus();
    }

    private final void setRecommendations(List<? extends SearchResponse> rec, String validApiName) {
        List listEmptyList;
        SearchResponse searchResponse;
        this.currentRecommendations = rec == null ? CollectionsKt.emptyList() : rec;
        List<? extends SearchResponse> list = rec;
        boolean z = list == null || list.isEmpty();
        FragmentResultTvBinding binding = getBinding();
        if (binding != null) {
            AutofitRecyclerView resultRecommendationsList = binding.resultRecommendationsList;
            Intrinsics.checkNotNullExpressionValue(resultRecommendationsList, "resultRecommendationsList");
            resultRecommendationsList.setVisibility(z ? 8 : 0);
            LinearLayout resultRecommendationsHolder = binding.resultRecommendationsHolder;
            Intrinsics.checkNotNullExpressionValue(resultRecommendationsHolder, "resultRecommendationsHolder");
            resultRecommendationsHolder.setVisibility(z ? 8 : 0);
            if (validApiName == null) {
                validApiName = (rec == null || (searchResponse = (SearchResponse) CollectionsKt.firstOrNull((List) rec)) == null) ? null : searchResponse.getApiName();
            }
            RecyclerView.Adapter adapter = binding.resultRecommendationsList.getAdapter();
            SearchAdapter searchAdapter = adapter instanceof SearchAdapter ? (SearchAdapter) adapter : null;
            if (searchAdapter != null) {
                SearchAdapter searchAdapter2 = searchAdapter;
                if (rec != null) {
                    ArrayList arrayList = new ArrayList();
                    for (Object obj : rec) {
                        if (Intrinsics.areEqual(((SearchResponse) obj).getApiName(), validApiName)) {
                            arrayList.add(obj);
                        }
                    }
                    listEmptyList = arrayList;
                } else {
                    listEmptyList = CollectionsKt.emptyList();
                }
                BaseAdapter.submitList$default(searchAdapter2, listEmptyList, null, 2, null);
            }
            if (rec != null) {
                List<? extends SearchResponse> list2 = rec;
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                Iterator<T> it = list2.iterator();
                while (it.hasNext()) {
                    arrayList2.add(((SearchResponse) it.next()).getApiName());
                }
                List listDistinct = CollectionsKt.distinct(arrayList2);
                if (listDistinct != null) {
                    RecyclerView resultRecommendationsFilterSelection = binding.resultRecommendationsFilterSelection;
                    Intrinsics.checkNotNullExpressionValue(resultRecommendationsFilterSelection, "resultRecommendationsFilterSelection");
                    resultRecommendationsFilterSelection.setVisibility(listDistinct.size() > 1 ? 0 : 8);
                    RecyclerView recyclerView = binding.resultRecommendationsFilterSelection;
                    List<String> list3 = listDistinct;
                    ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
                    for (String str : list3) {
                        arrayList3.add(TuplesKt.to(TextUtilKt.txt(str), str));
                    }
                    update(recyclerView, arrayList3);
                    select(binding.resultRecommendationsFilterSelection, CollectionsKt.indexOf((List<? extends String>) listDistinct, validApiName));
                    return;
                }
            }
            RecyclerView resultRecommendationsFilterSelection2 = binding.resultRecommendationsFilterSelection;
            Intrinsics.checkNotNullExpressionValue(resultRecommendationsFilterSelection2, "resultRecommendationsFilterSelection");
            resultRecommendationsFilterSelection2.setVisibility(8);
        }
    }

    public final Dialog getLoadingDialog() {
        return this.loadingDialog;
    }

    public final void setLoadingDialog(Dialog dialog) {
        this.loadingDialog = dialog;
    }

    public final Dialog getPopupDialog() {
        return this.popupDialog;
    }

    public final void setPopupDialog(Dialog dialog) {
        this.popupDialog = dialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reloadViewModel(boolean forceReload) {
        ResultFragment.StoredData storedData;
        ResultViewModel2 resultViewModel2;
        ResultViewModel2 resultViewModel3 = this.viewModel;
        if (resultViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel3 = null;
        }
        if ((!resultViewModel3.hasLoaded() || forceReload) && (storedData = ResultFragment.INSTANCE.getStoredData(this)) != null) {
            ResultViewModel2 resultViewModel4 = this.viewModel;
            if (resultViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                resultViewModel2 = null;
            } else {
                resultViewModel2 = resultViewModel4;
            }
            resultViewModel2.load(getActivity(), storedData.getUrl(), storedData.getApiName(), storedData.getShowFillers(), storedData.getDubStatus(), storedData.getStart(), (64 & 64) != 0);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$onResume$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ResultFragmentTv.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C08791 extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
        C08791(Object obj) {
            super(1, obj, ResultFragmentTv.class, "reloadViewModel", "reloadViewModel(Z)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            ((ResultFragmentTv) this.receiver).reloadViewModel(z);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            UIHelper.INSTANCE.setNavigationBarColorCompat(activity, R.attr.primaryBlackBackground);
        }
        MainActivity.INSTANCE.getAfterPluginsLoadedEvent().plusAssign(new C08791(this));
        super.onResume();
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$onStop$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ResultFragmentTv.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C08801 extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
        C08801(Object obj) {
            super(1, obj, ResultFragmentTv.class, "reloadViewModel", "reloadViewModel(Z)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            ((ResultFragmentTv) this.receiver).reloadViewModel(z);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        MainActivity.INSTANCE.getAfterPluginsLoadedEvent().minusAssign(new C08801(this));
        super.onStop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void toggleEpisodes(boolean show) {
        FragmentResultTvBinding binding = getBinding();
        if (binding != null) {
            if (show) {
                FragmentActivity activity = getActivity();
                if (activity != null) {
                    String string = toString();
                    Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                    BackPressedCallbackHelper.INSTANCE.attachBackPressedCallback(activity, string, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda52
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return ResultFragmentTv.toggleEpisodes$lambda$0$0(this.f$0, (BackPressedCallbackHelper.CallbackHelper) obj);
                        }
                    });
                }
            } else {
                FragmentActivity activity2 = getActivity();
                if (activity2 != null) {
                    String string2 = toString();
                    Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
                    BackPressedCallbackHelper.INSTANCE.detachBackPressedCallback(activity2, string2);
                }
            }
            ConstraintLayout episodesShadow = binding.episodesShadow;
            Intrinsics.checkNotNullExpressionValue(episodesShadow, "episodesShadow");
            fade(episodesShadow, show);
            LinearLayout episodeHolderTv = binding.episodeHolderTv;
            Intrinsics.checkNotNullExpressionValue(episodeHolderTv, "episodeHolderTv");
            fade(episodeHolderTv, show);
            AppContextUtils appContextUtils = AppContextUtils.INSTANCE;
            ConstraintLayout episodesShadow2 = binding.episodesShadow;
            Intrinsics.checkNotNullExpressionValue(episodesShadow2, "episodesShadow");
            if (appContextUtils.isRtl(episodesShadow2)) {
                binding.episodesShadowBackground.setScaleX(-1.0f);
            } else {
                binding.episodesShadowBackground.setScaleX(1.0f);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit toggleEpisodes$lambda$0$0(ResultFragmentTv resultFragmentTv, BackPressedCallbackHelper.CallbackHelper attachBackPressedCallback) {
        Intrinsics.checkNotNullParameter(attachBackPressedCallback, "$this$attachBackPressedCallback");
        resultFragmentTv.toggleEpisodes(false);
        return Unit.INSTANCE;
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void fixLayout(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        UIHelper.fixSystemBarsPadding$default(UIHelper.INSTANCE, view, null, null, false, false, false, false, false, false, TypedValues.PositionType.TYPE_DRAWPATH, null);
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0053  */
    /* JADX WARN: Code duplicated, block: B:24:0x0057  */
    /* JADX WARN: Code duplicated, block: B:25:0x005c  */
    @Override // com.lagradost.cloudstream3.ui.BaseFragment, com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void onBindingCreated(final FragmentResultTvBinding binding) {
        ResultViewModel2 resultViewModel2;
        ResultViewModel2 resultViewModel3;
        Object next;
        LinearLayout linearLayout;
        Window window;
        View decorView;
        Intrinsics.checkNotNullParameter(binding, "binding");
        ResultFragmentTv resultFragmentTv = this;
        final ResultFragment.StoredData storedData = ResultFragment.INSTANCE.getStoredData(resultFragmentTv);
        if (storedData == null) {
            return;
        }
        FragmentActivity activity = getActivity();
        if (activity != null && (window = activity.getWindow()) != null && (decorView = window.getDecorView()) != null) {
            decorView.clearFocus();
        }
        FragmentActivity activity2 = getActivity();
        if (activity2 != null) {
            AppContextUtils.INSTANCE.loadCache(activity2);
        }
        UIHelper.INSTANCE.hideKeyboard(resultFragmentTv);
        ResultViewModel2 resultViewModel4 = null;
        if (storedData.getRestart()) {
            resultViewModel2 = this.viewModel;
            if (resultViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                resultViewModel3 = null;
            } else {
                resultViewModel3 = resultViewModel2;
            }
            resultViewModel3.load(getActivity(), storedData.getUrl(), storedData.getApiName(), storedData.getShowFillers(), storedData.getDubStatus(), storedData.getStart(), (64 & 64) != 0);
        } else {
            ResultViewModel2 resultViewModel5 = this.viewModel;
            if (resultViewModel5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                resultViewModel5 = null;
            }
            if (!resultViewModel5.hasLoaded()) {
                resultViewModel2 = this.viewModel;
                if (resultViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    resultViewModel3 = null;
                } else {
                    resultViewModel3 = resultViewModel2;
                }
                resultViewModel3.load(getActivity(), storedData.getUrl(), storedData.getApiName(), storedData.getShowFillers(), storedData.getDubStatus(), storedData.getStart(), (64 & 64) != 0);
            }
        }
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        binding.resultFinishLoading.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda53
            @Override // androidx.core.widget.NestedScrollView.OnScrollChangeListener
            public final void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
                ResultFragmentTv.onBindingCreated$lambda$0$0(binding, nestedScrollView, i, i2, i3, i4);
            }
        });
        binding.redirectToPlay.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda9
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                ResultFragmentTv.onBindingCreated$lambda$0$1(this.f$0, binding, view, z);
            }
        });
        binding.redirectToEpisodes.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda21
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                ResultFragmentTv.onBindingCreated$lambda$0$2(this.f$0, binding, view, z);
            }
        });
        for (Map.Entry entry : MapsKt.mapOf(TuplesKt.to(binding.resultPlayMovieButton, binding.resultPlayMovieText), TuplesKt.to(binding.resultPlaySeriesButton, binding.resultPlaySeriesText), TuplesKt.to(binding.resultResumeSeriesButton, binding.resultResumeSeriesText), TuplesKt.to(binding.resultPlayTrailerButton, binding.resultPlayTrailerText), TuplesKt.to(binding.resultBookmarkButton, binding.resultBookmarkText), TuplesKt.to(binding.resultFavoriteButton, binding.resultFavoriteText), TuplesKt.to(binding.resultSubscribeButton, binding.resultSubscribeText), TuplesKt.to(binding.resultSearchButton, binding.resultSearchText), TuplesKt.to(binding.resultEpisodesShowButton, binding.resultEpisodesShowText)).entrySet()) {
            Object key = entry.getKey();
            Intrinsics.checkNotNullExpressionValue(key, "component1(...)");
            final MaterialButton materialButton = (MaterialButton) key;
            Object value = entry.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "component2(...)");
            final TextView textView = (TextView) value;
            materialButton.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda24
                @Override // android.view.View.OnFocusChangeListener
                public final void onFocusChange(View view, boolean z) {
                    ResultFragmentTv.onBindingCreated$lambda$0$3$0(textView, this, materialButton, binding, view, z);
                }
            });
        }
        binding.resultEpisodesShowButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda25
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ResultFragmentTv.onBindingCreated$lambda$0$4(this.f$0, binding, view);
            }
        });
        LinearListLayoutKt.setLinearListLayout$default(binding.resultEpisodes, false, 0, -2, -2, -2, 2, null);
        LinearListLayoutKt.setLinearListLayout$default(binding.resultDubSelection, false, 0, 0, -2, -2, 6, null);
        LinearListLayoutKt.setLinearListLayout$default(binding.resultRangeSelection, false, 0, 0, -2, -2, 6, null);
        LinearListLayoutKt.setLinearListLayout$default(binding.resultSeasonSelection, false, 0, 0, -2, -2, 6, null);
        binding.resultReloadConnectionerror.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda26
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ResultFragmentTv.onBindingCreated$lambda$0$5(this.f$0, storedData, view);
            }
        });
        binding.resultMetaSite.setFocusable(false);
        setAdapter(binding.resultSeasonSelection);
        setAdapter(binding.resultRangeSelection);
        setAdapter(binding.resultDubSelection);
        setAdapter(binding.resultRecommendationsFilterSelection);
        binding.resultCastItems.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda27
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                ResultFragmentTv.onBindingCreated$lambda$0$6(binding, view, z);
            }
        });
        binding.resultRecommendationsList.setSpanCount(8);
        AutofitRecyclerView resultRecommendationsList = binding.resultRecommendationsList;
        Intrinsics.checkNotNullExpressionValue(resultRecommendationsList, "resultRecommendationsList");
        BaseAdapterKt.setRecycledViewPool(resultRecommendationsList, SearchAdapter.INSTANCE.getSharedPool());
        AutofitRecyclerView autofitRecyclerView = binding.resultRecommendationsList;
        AutofitRecyclerView resultRecommendationsList2 = binding.resultRecommendationsList;
        Intrinsics.checkNotNullExpressionValue(resultRecommendationsList2, "resultRecommendationsList");
        autofitRecyclerView.setAdapter(new SearchAdapter(resultRecommendationsList2, false, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda28
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentTv.onBindingCreated$lambda$0$7(this.f$0, (SearchClickCallback) obj);
            }
        }, 2, null));
        RecyclerView resultEpisodes = binding.resultEpisodes;
        Intrinsics.checkNotNullExpressionValue(resultEpisodes, "resultEpisodes");
        BaseAdapterKt.setRecycledViewPool(resultEpisodes, EpisodeAdapter.INSTANCE.getSharedPool());
        binding.resultEpisodes.setAdapter(new EpisodeAdapter(false, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda29
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentTv.onBindingCreated$lambda$0$8(this.f$0, (EpisodeClickEvent) obj);
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda30
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentTv.onBindingCreated$lambda$0$9((DownloadClickEvent) obj);
            }
        }));
        RecyclerView recyclerView = binding.resultCastItems;
        final Context context = binding.getRoot().getContext();
        LinearListLayout linearListLayout = new LinearListLayout(context) { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$onBindingCreated$1$11
            @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean onRequestChildFocus(RecyclerView parent, RecyclerView.State state, View child, View focused) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                Intrinsics.checkNotNullParameter(state, "state");
                Intrinsics.checkNotNullParameter(child, "child");
                if (parent.getFocusedChild() == null) {
                    scrollToPosition(findFirstCompletelyVisibleItemPosition());
                    return true;
                }
                return super.onRequestChildFocus(parent, state, child, focused);
            }
        };
        linearListLayout.setHorizontal();
        recyclerView.setLayoutManager(linearListLayout);
        Iterator it = CollectionsKt.listOf((Object[]) new LinearLayout[]{binding.resultEpisodesShow, binding.resultBookmark, binding.resultFavorite, binding.resultSubscribe}).iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                linearLayout = (LinearLayout) next;
                Intrinsics.checkNotNull(linearLayout);
            }
        } while (linearLayout.getVisibility() != 0);
        LinearLayout linearLayout2 = (LinearLayout) next;
        RecyclerView resultCastItems = binding.resultCastItems;
        Intrinsics.checkNotNullExpressionValue(resultCastItems, "resultCastItems");
        BaseAdapterKt.setRecycledViewPool(resultCastItems, ActorAdaptor.INSTANCE.getSharedPool());
        binding.resultCastItems.setAdapter(new ActorAdaptor(linearLayout2 != null ? Integer.valueOf(linearLayout2.getId()) : null, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda54
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentTv.onBindingCreated$lambda$0$12(this.f$0, (View) obj);
            }
        }));
        if (Globals.INSTANCE.isLayout(4)) {
            binding.episodesShadow.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda55
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.toggleEpisodes(false);
                }
            });
        }
        ResultFragmentTv resultFragmentTv2 = this;
        ResultViewModel2 resultViewModel6 = this.viewModel;
        if (resultViewModel6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel6 = null;
        }
        LifecycleKt.observeNullable(resultFragmentTv2, resultViewModel6.getResumeWatching(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentTv.onBindingCreated$lambda$1(binding, this, storedData, (ResumeWatchingStatus) obj);
            }
        });
        ResultViewModel2 resultViewModel7 = this.viewModel;
        if (resultViewModel7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel7 = null;
        }
        LifecycleKt.observe(resultFragmentTv2, resultViewModel7.getTrailers(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentTv.onBindingCreated$lambda$2(this.f$0, binding, (List) obj);
            }
        });
        ResultViewModel2 resultViewModel8 = this.viewModel;
        if (resultViewModel8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel8 = null;
        }
        LifecycleKt.observe(resultFragmentTv2, resultViewModel8.getWatchStatus(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentTv.onBindingCreated$lambda$3(binding, this, (WatchType) obj);
            }
        });
        ResultViewModel2 resultViewModel9 = this.viewModel;
        if (resultViewModel9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel9 = null;
        }
        LifecycleKt.observeNullable(resultFragmentTv2, resultViewModel9.getFavoriteStatus(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentTv.onBindingCreated$lambda$4(binding, this, (Boolean) obj);
            }
        });
        ResultViewModel2 resultViewModel10 = this.viewModel;
        if (resultViewModel10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel10 = null;
        }
        LifecycleKt.observeNullable(resultFragmentTv2, resultViewModel10.getSubscribeStatus(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentTv.onBindingCreated$lambda$5(binding, this, (Boolean) obj);
            }
        });
        ResultViewModel2 resultViewModel11 = this.viewModel;
        if (resultViewModel11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel11 = null;
        }
        LifecycleKt.observeNullable(resultFragmentTv2, resultViewModel11.m8575getMovie(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentTv.onBindingCreated$lambda$6(binding, booleanRef, this, (Resource) obj);
            }
        });
        ResultViewModel2 resultViewModel12 = this.viewModel;
        if (resultViewModel12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel12 = null;
        }
        LifecycleKt.observeNullable(resultFragmentTv2, resultViewModel12.getSelectPopup(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentTv.onBindingCreated$lambda$7(this.f$0, (SelectPopup) obj);
            }
        });
        ResultViewModel2 resultViewModel13 = this.viewModel;
        if (resultViewModel13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel13 = null;
        }
        LifecycleKt.observeNullable(resultFragmentTv2, resultViewModel13.getLoadedLinks(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentTv.onBindingCreated$lambda$8(this.f$0, (LinkProgress) obj);
            }
        });
        ResultViewModel2 resultViewModel14 = this.viewModel;
        if (resultViewModel14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel14 = null;
        }
        LifecycleKt.observeNullable(resultFragmentTv2, resultViewModel14.getEpisodesCountText(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentTv.onBindingCreated$lambda$9(binding, (UiText) obj);
            }
        });
        ResultViewModel2 resultViewModel15 = this.viewModel;
        if (resultViewModel15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel15 = null;
        }
        LifecycleKt.observe(resultFragmentTv2, resultViewModel15.getSelectedRangeIndex(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentTv.onBindingCreated$lambda$10(this.f$0, binding, ((Integer) obj).intValue());
            }
        });
        ResultViewModel2 resultViewModel16 = this.viewModel;
        if (resultViewModel16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel16 = null;
        }
        LifecycleKt.observe(resultFragmentTv2, resultViewModel16.getSelectedSeasonIndex(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentTv.onBindingCreated$lambda$11(this.f$0, binding, ((Integer) obj).intValue());
            }
        });
        ResultViewModel2 resultViewModel17 = this.viewModel;
        if (resultViewModel17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel17 = null;
        }
        LifecycleKt.observe(resultFragmentTv2, resultViewModel17.getSelectedDubStatusIndex(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentTv.onBindingCreated$lambda$12(this.f$0, binding, ((Integer) obj).intValue());
            }
        });
        ResultViewModel2 resultViewModel18 = this.viewModel;
        if (resultViewModel18 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel18 = null;
        }
        LifecycleKt.observe(resultFragmentTv2, resultViewModel18.getRangeSelections(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentTv.onBindingCreated$lambda$13(this.f$0, binding, (List) obj);
            }
        });
        ResultViewModel2 resultViewModel19 = this.viewModel;
        if (resultViewModel19 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel19 = null;
        }
        LifecycleKt.observe(resultFragmentTv2, resultViewModel19.getDubSubSelections(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentTv.onBindingCreated$lambda$14(this.f$0, binding, (List) obj);
            }
        });
        ResultViewModel2 resultViewModel20 = this.viewModel;
        if (resultViewModel20 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel20 = null;
        }
        LifecycleKt.observe(resultFragmentTv2, resultViewModel20.getSeasonSelections(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentTv.onBindingCreated$lambda$15(this.f$0, binding, (List) obj);
            }
        });
        ResultViewModel2 resultViewModel21 = this.viewModel;
        if (resultViewModel21 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel21 = null;
        }
        LifecycleKt.observe(resultFragmentTv2, resultViewModel21.getRecommendations(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentTv.onBindingCreated$lambda$16(this.f$0, (List) obj);
            }
        });
        if (Globals.INSTANCE.isLayout(2)) {
            ResultViewModel2 resultViewModel22 = this.viewModel;
            if (resultViewModel22 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                resultViewModel22 = null;
            }
            LifecycleKt.observe(resultFragmentTv2, resultViewModel22.getEpisodeSynopsis(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda19
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ResultFragmentTv.onBindingCreated$lambda$17(this.f$0, (String) obj);
                }
            });
        }
        final Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
        ResultViewModel2 resultViewModel23 = this.viewModel;
        if (resultViewModel23 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel23 = null;
        }
        LifecycleKt.observeNullable(resultFragmentTv2, resultViewModel23.getEpisodes(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentTv.onBindingCreated$lambda$18(binding, booleanRef, this, booleanRef2, (Resource) obj);
            }
        });
        ResultViewModel2 resultViewModel24 = this.viewModel;
        if (resultViewModel24 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            resultViewModel4 = resultViewModel24;
        }
        LifecycleKt.observeNullable(resultFragmentTv2, resultViewModel4.getPage(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda23
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentTv.onBindingCreated$lambda$19(binding, booleanRef, storedData, this, (Resource) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$0$0(FragmentResultTvBinding fragmentResultTvBinding, NestedScrollView view, int i, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(view, "view");
        fragmentResultTvBinding.backgroundPosterHolder.setTranslationY((-i2) * 0.8f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$0$1(ResultFragmentTv resultFragmentTv, FragmentResultTvBinding fragmentResultTvBinding, View view, boolean z) {
        if (z) {
            resultFragmentTv.toggleEpisodes(false);
            for (Object obj : CollectionsKt.listOf((Object[]) new MaterialButton[]{fragmentResultTvBinding.resultPlayMovieButton, fragmentResultTvBinding.resultPlaySeriesButton, fragmentResultTvBinding.resultResumeSeriesButton, fragmentResultTvBinding.resultPlayTrailerButton, fragmentResultTvBinding.resultBookmarkButton, fragmentResultTvBinding.resultFavoriteButton, fragmentResultTvBinding.resultSubscribeButton, fragmentResultTvBinding.resultSearchButton})) {
                Intrinsics.checkNotNullExpressionValue(obj, "next(...)");
                MaterialButton materialButton = (MaterialButton) obj;
                if (materialButton.getVisibility() == 0 && materialButton.requestFocus()) {
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$0$2(ResultFragmentTv resultFragmentTv, FragmentResultTvBinding fragmentResultTvBinding, View view, boolean z) {
        if (z) {
            resultFragmentTv.toggleEpisodes(true);
            MaxRecyclerView resultDubSelection = fragmentResultTvBinding.resultDubSelection;
            Intrinsics.checkNotNullExpressionValue(resultDubSelection, "resultDubSelection");
            MaxRecyclerView resultSeasonSelection = fragmentResultTvBinding.resultSeasonSelection;
            Intrinsics.checkNotNullExpressionValue(resultSeasonSelection, "resultSeasonSelection");
            MaxRecyclerView resultRangeSelection = fragmentResultTvBinding.resultRangeSelection;
            Intrinsics.checkNotNullExpressionValue(resultRangeSelection, "resultRangeSelection");
            RecyclerView resultEpisodes = fragmentResultTvBinding.resultEpisodes;
            Intrinsics.checkNotNullExpressionValue(resultEpisodes, "resultEpisodes");
            MaterialButton resultPlayTrailerButton = fragmentResultTvBinding.resultPlayTrailerButton;
            Intrinsics.checkNotNullExpressionValue(resultPlayTrailerButton, "resultPlayTrailerButton");
            for (View view2 : CollectionsKt.listOf((Object[]) new View[]{resultDubSelection, resultSeasonSelection, resultRangeSelection, resultEpisodes, resultPlayTrailerButton})) {
                if (view2.isShown() && view2.requestFocus()) {
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$0$3$0(TextView textView, ResultFragmentTv resultFragmentTv, MaterialButton materialButton, FragmentResultTvBinding fragmentResultTvBinding, View view, boolean z) {
        if (!z) {
            textView.setSelected(false);
            if (view.getId() == R.id.result_episodes_show_button) {
                resultFragmentTv.toggleEpisodes(false);
                return;
            }
            return;
        }
        textView.setSelected(true);
        Object tag = materialButton.getTag();
        Context context = resultFragmentTv.getContext();
        if (Intrinsics.areEqual(tag, context != null ? context.getString(R.string.tv_no_focus_tag) : null)) {
            fragmentResultTvBinding.resultFinishLoading.scrollTo(0, 0);
        }
        if (materialButton.getId() == R.id.result_episodes_show_button) {
            resultFragmentTv.toggleEpisodes(true);
        } else {
            resultFragmentTv.toggleEpisodes(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$0$4(ResultFragmentTv resultFragmentTv, FragmentResultTvBinding fragmentResultTvBinding, View view) {
        LinearLayout episodeHolderTv = fragmentResultTvBinding.episodeHolderTv;
        Intrinsics.checkNotNullExpressionValue(episodeHolderTv, "episodeHolderTv");
        resultFragmentTv.toggleEpisodes(!(episodeHolderTv.getVisibility() == 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$0$5(ResultFragmentTv resultFragmentTv, ResultFragment.StoredData storedData, View view) {
        ResultViewModel2 resultViewModel2 = resultFragmentTv.viewModel;
        if (resultViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel2 = null;
        }
        resultViewModel2.load(resultFragmentTv.getActivity(), storedData.getUrl(), storedData.getApiName(), storedData.getShowFillers(), storedData.getDubStatus(), storedData.getStart(), (64 & 64) != 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$0$6(FragmentResultTvBinding fragmentResultTvBinding, View view, boolean z) {
        if (z) {
            fragmentResultTvBinding.resultBookmarkButton.requestFocus();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$0$7(ResultFragmentTv resultFragmentTv, SearchClickCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (callback.getAction() == 4) {
            resultFragmentTv.toggleEpisodes(false);
        } else {
            SearchHelper.INSTANCE.handleSearchClickCallback(callback);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$0$8(ResultFragmentTv resultFragmentTv, EpisodeClickEvent episodeClick) {
        Intrinsics.checkNotNullParameter(episodeClick, "episodeClick");
        ResultViewModel2 resultViewModel2 = resultFragmentTv.viewModel;
        if (resultViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel2 = null;
        }
        resultViewModel2.handleAction(episodeClick);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$0$9(DownloadClickEvent downloadClickEvent) {
        Intrinsics.checkNotNullParameter(downloadClickEvent, "downloadClickEvent");
        DownloadButtonSetup.INSTANCE.handleDownloadClick(downloadClickEvent);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$0$12(ResultFragmentTv resultFragmentTv, View view) {
        resultFragmentTv.toggleEpisodes(false);
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$1(FragmentResultTvBinding fragmentResultTvBinding, final ResultFragmentTv resultFragmentTv, final ResultFragment.StoredData storedData, final ResumeWatchingStatus resumeWatchingStatus) {
        String string;
        String nameFull;
        if (resumeWatchingStatus == null) {
            return Unit.INSTANCE;
        }
        LinearLayout resultResumeSeries = fragmentResultTvBinding.resultResumeSeries;
        Intrinsics.checkNotNullExpressionValue(resultResumeSeries, "resultResumeSeries");
        resultResumeSeries.setVisibility(0);
        LinearLayout resultPlayMovie = fragmentResultTvBinding.resultPlayMovie;
        Intrinsics.checkNotNullExpressionValue(resultPlayMovie, "resultPlayMovie");
        resultPlayMovie.setVisibility(8);
        LinearLayout resultPlaySeries = fragmentResultTvBinding.resultPlaySeries;
        Intrinsics.checkNotNullExpressionValue(resultPlaySeries, "resultPlaySeries");
        resultPlaySeries.setVisibility(8);
        ResumeProgress progress = resumeWatchingStatus.getProgress();
        if (progress != null) {
            TextView textView = fragmentResultTvBinding.resultResumeSeriesTitle;
            Intrinsics.checkNotNull(textView);
            textView.setVisibility(resumeWatchingStatus.isMovie() ? 8 : 0);
            if (resumeWatchingStatus.isMovie()) {
                nameFull = null;
            } else {
                Context context = textView.getContext();
                nameFull = context != null ? AppContextUtils.INSTANCE.getNameFull(context, resumeWatchingStatus.getResult().getName(), Integer.valueOf(resumeWatchingStatus.getResult().getEpisode()), resumeWatchingStatus.getResult().getSeason()) : null;
            }
            textView.setText(nameFull);
            TextUtilKt.setText(fragmentResultTvBinding.resultResumeSeriesProgressText, progress.getProgressLeft());
            CircularProgressIndicator circularProgressIndicator = fragmentResultTvBinding.resultResumeSeriesProgress;
            Intrinsics.checkNotNull(circularProgressIndicator);
            circularProgressIndicator.setVisibility(0);
            circularProgressIndicator.setMax(progress.getMaxProgress());
            circularProgressIndicator.setProgress(progress.getProgress());
            LinearLayout resultResumeProgressHolder = fragmentResultTvBinding.resultResumeProgressHolder;
            Intrinsics.checkNotNullExpressionValue(resultResumeProgressHolder, "resultResumeProgressHolder");
            resultResumeProgressHolder.setVisibility(0);
        } else {
            LinearLayout resultResumeProgressHolder2 = fragmentResultTvBinding.resultResumeProgressHolder;
            Intrinsics.checkNotNullExpressionValue(resultResumeProgressHolder2, "resultResumeProgressHolder");
            resultResumeProgressHolder2.setVisibility(8);
        }
        resultFragmentTv.focusPlayButton();
        if (resumeWatchingStatus.isMovie()) {
            fragmentResultTvBinding.resultSearchButton.setNextFocusRightId(R.id.result_search_Button);
        }
        TextView textView2 = fragmentResultTvBinding.resultResumeSeriesText;
        if (resumeWatchingStatus.isMovie()) {
            Context context2 = resultFragmentTv.getContext();
            string = context2 != null ? context2.getString(R.string.resume) : null;
        } else if (resumeWatchingStatus.getResult().getSeason() != null) {
            string = resultFragmentTv.getString(R.string.season_short) + resumeWatchingStatus.getResult().getSeason().intValue() + AbstractJsonLexerKt.COLON + resultFragmentTv.getString(R.string.episode_short) + resumeWatchingStatus.getResult().getEpisode();
        } else {
            string = resultFragmentTv.getString(R.string.episode) + ' ' + resumeWatchingStatus.getResult().getEpisode();
        }
        textView2.setText(string);
        fragmentResultTvBinding.resultResumeSeriesButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda47
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ResultFragmentTv.onBindingCreated$lambda$1$0$2(this.f$0, storedData, resumeWatchingStatus, view);
            }
        });
        fragmentResultTvBinding.resultResumeSeriesButton.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda48
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return ResultFragmentTv.onBindingCreated$lambda$1$0$3(this.f$0, resumeWatchingStatus, view);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$1$0$2(ResultFragmentTv resultFragmentTv, ResultFragment.StoredData storedData, ResumeWatchingStatus resumeWatchingStatus, View view) {
        ResultViewModel2 resultViewModel2 = resultFragmentTv.viewModel;
        if (resultViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel2 = null;
        }
        resultViewModel2.handleAction(new EpisodeClickEvent(storedData.getPlayerAction(), resumeWatchingStatus.getResult()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindingCreated$lambda$1$0$3(ResultFragmentTv resultFragmentTv, ResumeWatchingStatus resumeWatchingStatus, View view) {
        ResultViewModel2 resultViewModel2 = resultFragmentTv.viewModel;
        if (resultViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel2 = null;
        }
        resultViewModel2.handleAction(new EpisodeClickEvent(10, resumeWatchingStatus.getResult()));
        return true;
    }

    static final Unit onBindingCreated$lambda$2(final ResultFragmentTv resultFragmentTv, FragmentResultTvBinding fragmentResultTvBinding, List trailersLinks) {
        Intrinsics.checkNotNullParameter(trailersLinks, "trailersLinks");
        Context context = resultFragmentTv.getContext();
        if (context != null) {
            AppContextUtils.INSTANCE.updateHasTrailers(context);
        }
        if (!LoadResponse.INSTANCE.isTrailersEnabled()) {
            return Unit.INSTANCE;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = trailersLinks.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, ((ExtractedTrailerData) it.next()).getMirros());
        }
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            arrayList3.add((ExtractorLink) ((Pair) it2.next()).component1());
        }
        final ArrayList arrayList4 = arrayList3;
        LinearLayout resultPlayTrailer = fragmentResultTvBinding.resultPlayTrailer;
        Intrinsics.checkNotNullExpressionValue(resultPlayTrailer, "resultPlayTrailer");
        resultPlayTrailer.setVisibility(arrayList4.isEmpty() ? 8 : 0);
        fragmentResultTvBinding.resultPlayTrailerButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda33
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ResultFragmentTv.onBindingCreated$lambda$2$2$0(arrayList4, resultFragmentTv, view);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$2$2$0(List list, ResultFragmentTv resultFragmentTv, View view) {
        if (list.isEmpty()) {
            return;
        }
        UIHelper.navigate$default(UIHelper.INSTANCE, resultFragmentTv.getActivity(), R.id.global_to_navigation_player, GeneratorPlayer.Companion.newInstance$default(GeneratorPlayer.INSTANCE, new ExtractorLinkGenerator(list, CollectionsKt.emptyList()), 0, null, 4, null), null, 4, null);
    }

    static final Unit onBindingCreated$lambda$3(FragmentResultTvBinding fragmentResultTvBinding, final ResultFragmentTv resultFragmentTv, final WatchType watchType) {
        int i;
        Intrinsics.checkNotNullParameter(watchType, "watchType");
        fragmentResultTvBinding.resultBookmarkText.setText(watchType.getStringRes());
        final MaterialButton materialButton = fragmentResultTvBinding.resultBookmarkButton;
        if (watchType.getStringRes() == R.string.type_none) {
            i = R.drawable.outline_bookmark_add_24;
        } else {
            i = R.drawable.ic_baseline_bookmark_24;
        }
        materialButton.setIconResource(i);
        materialButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda46
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ResultFragmentTv.onBindingCreated$lambda$3$0$0$0(this.f$0, watchType, materialButton, view);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$3$0$0$0(final ResultFragmentTv resultFragmentTv, WatchType watchType, final MaterialButton materialButton, View view) {
        FragmentActivity activity = resultFragmentTv.getActivity();
        if (activity != null) {
            SingleSelectionHelper singleSelectionHelper = SingleSelectionHelper.INSTANCE;
            FragmentActivity fragmentActivity = activity;
            EnumEntries<WatchType> entries = WatchType.getEntries();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(entries, 10));
            Iterator<WatchType> it = entries.iterator();
            while (it.hasNext()) {
                arrayList.add(view.getContext().getString(it.next().getStringRes()));
            }
            List<String> list = CollectionsKt.toList(arrayList);
            int iOrdinal = watchType.ordinal();
            String string = view.getContext().getString(R.string.action_add_to_bookmarks);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            singleSelectionHelper.showBottomDialog(fragmentActivity, list, iOrdinal, string, false, new Function0() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda41
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            }, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda42
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ResultFragmentTv.onBindingCreated$lambda$3$0$0$0$2(this.f$0, materialButton, ((Integer) obj).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$3$0$0$0$2(ResultFragmentTv resultFragmentTv, MaterialButton materialButton, int i) {
        ResultViewModel2 resultViewModel2 = resultFragmentTv.viewModel;
        if (resultViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel2 = null;
        }
        ResultViewModel2.updateWatchStatus$default(resultViewModel2, WatchType.getEntries().get(i), materialButton.getContext(), null, null, 12, null);
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$4(FragmentResultTvBinding fragmentResultTvBinding, final ResultFragmentTv resultFragmentTv, Boolean bool) {
        int i;
        int i2;
        LinearLayout resultFavorite = fragmentResultTvBinding.resultFavorite;
        Intrinsics.checkNotNullExpressionValue(resultFavorite, "resultFavorite");
        resultFavorite.setVisibility(bool != null ? 0 : 8);
        final MaterialButton materialButton = fragmentResultTvBinding.resultFavoriteButton;
        if (bool == null) {
            return Unit.INSTANCE;
        }
        if (bool.booleanValue()) {
            i = R.drawable.ic_baseline_favorite_24;
        } else {
            i = R.drawable.ic_baseline_favorite_border_24;
        }
        materialButton.setIconResource(i);
        materialButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ResultFragmentTv.onBindingCreated$lambda$4$0$0(this.f$0, materialButton, view);
            }
        });
        TextView textView = fragmentResultTvBinding.resultFavoriteText;
        if (Intrinsics.areEqual((Object) bool, (Object) true)) {
            i2 = R.string.unfavorite;
        } else {
            i2 = R.string.favorite;
        }
        textView.setText(i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$4$0$0(final ResultFragmentTv resultFragmentTv, final MaterialButton materialButton, View view) {
        ResultViewModel2 resultViewModel2 = resultFragmentTv.viewModel;
        if (resultViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel2 = null;
        }
        resultViewModel2.toggleFavoriteStatus(materialButton.getContext(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda43
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentTv.onBindingCreated$lambda$4$0$0$0(this.f$0, materialButton, (Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$4$0$0$0(ResultFragmentTv resultFragmentTv, MaterialButton materialButton, Boolean bool) {
        int i;
        String strAsStringNull;
        ResultData resultData;
        if (bool == null) {
            return Unit.INSTANCE;
        }
        if (bool.booleanValue()) {
            i = R.string.favorite_added;
        } else {
            i = R.string.favorite_removed;
        }
        ResultViewModel2 resultViewModel2 = resultFragmentTv.viewModel;
        if (resultViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel2 = null;
        }
        Resource<ResultData> value = resultViewModel2.getPage().getValue();
        Resource.Success success = value instanceof Resource.Success ? (Resource.Success) value : null;
        if ((success == null || (resultData = (ResultData) success.getValue()) == null || (strAsStringNull = resultData.getTitle()) == null) && (strAsStringNull = TextUtilKt.txt(R.string.no_data, new Object[0]).asStringNull(materialButton.getContext())) == null) {
            strAsStringNull = "";
        }
        CommonActivity.INSTANCE.showToast(TextUtilKt.txt(i, strAsStringNull), (Integer) 0);
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$5(FragmentResultTvBinding fragmentResultTvBinding, final ResultFragmentTv resultFragmentTv, Boolean bool) {
        int i;
        int i2;
        LinearLayout resultSubscribe = fragmentResultTvBinding.resultSubscribe;
        Intrinsics.checkNotNullExpressionValue(resultSubscribe, "resultSubscribe");
        resultSubscribe.setVisibility(bool != null && Globals.INSTANCE.isLayout(4) ? 0 : 8);
        final MaterialButton materialButton = fragmentResultTvBinding.resultSubscribeButton;
        if (bool == null) {
            return Unit.INSTANCE;
        }
        if (bool.booleanValue()) {
            i = R.drawable.ic_baseline_notifications_active_24;
        } else {
            i = R.drawable.baseline_notifications_none_24;
        }
        materialButton.setIconResource(i);
        materialButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda22
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ResultFragmentTv.onBindingCreated$lambda$5$0$0(this.f$0, materialButton, view);
            }
        });
        TextView textView = fragmentResultTvBinding.resultSubscribeText;
        if (bool.booleanValue()) {
            i2 = R.string.action_unsubscribe;
        } else {
            i2 = R.string.action_subscribe;
        }
        textView.setText(i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$5$0$0(final ResultFragmentTv resultFragmentTv, final MaterialButton materialButton, View view) {
        ResultViewModel2 resultViewModel2 = resultFragmentTv.viewModel;
        if (resultViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel2 = null;
        }
        resultViewModel2.toggleSubscriptionStatus(materialButton.getContext(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentTv.onBindingCreated$lambda$5$0$0$0(materialButton, resultFragmentTv, (Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$5$0$0$0(MaterialButton materialButton, ResultFragmentTv resultFragmentTv, Boolean bool) {
        int i;
        String strAsStringNull;
        ResultData resultData;
        if (bool == null) {
            return Unit.INSTANCE;
        }
        if (bool.booleanValue()) {
            SubscriptionWorkManager.INSTANCE.enqueuePeriodicWork(materialButton.getContext());
            i = R.string.subscription_new;
        } else {
            i = R.string.subscription_deleted;
        }
        ResultViewModel2 resultViewModel2 = resultFragmentTv.viewModel;
        if (resultViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel2 = null;
        }
        Resource<ResultData> value = resultViewModel2.getPage().getValue();
        Resource.Success success = value instanceof Resource.Success ? (Resource.Success) value : null;
        if ((success == null || (resultData = (ResultData) success.getValue()) == null || (strAsStringNull = resultData.getTitle()) == null) && (strAsStringNull = TextUtilKt.txt(R.string.no_data, new Object[0]).asStringNull(materialButton.getContext())) == null) {
            strAsStringNull = "";
        }
        CommonActivity.INSTANCE.showToast(TextUtilKt.txt(i, strAsStringNull), (Integer) 0);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0052  */
    static final Unit onBindingCreated$lambda$6(FragmentResultTvBinding fragmentResultTvBinding, Ref.BooleanRef booleanRef, final ResultFragmentTv resultFragmentTv, Resource resource) {
        Pair pair;
        boolean z;
        if (resource == null) {
            return Unit.INSTANCE;
        }
        Resource.Success success = resource instanceof Resource.Success ? (Resource.Success) resource : null;
        if (success != null && (pair = (Pair) success.getValue()) != null) {
            final ResultEpisode resultEpisode = (ResultEpisode) pair.component2();
            fragmentResultTvBinding.resultPlayMovieButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda32
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ResultFragmentTv.onBindingCreated$lambda$6$0$0$0(this.f$0, resultEpisode, view);
                }
            });
            fragmentResultTvBinding.resultPlayMovieButton.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda34
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    return ResultFragmentTv.onBindingCreated$lambda$6$0$0$1(this.f$0, resultEpisode, view);
                }
            });
            LinearLayout resultPlayMovie = fragmentResultTvBinding.resultPlayMovie;
            Intrinsics.checkNotNullExpressionValue(resultPlayMovie, "resultPlayMovie");
            LinearLayout linearLayout = resultPlayMovie;
            if (booleanRef.element) {
                z = false;
            } else {
                LinearLayout resultResumeSeries = fragmentResultTvBinding.resultResumeSeries;
                Intrinsics.checkNotNullExpressionValue(resultResumeSeries, "resultResumeSeries");
                if (resultResumeSeries.getVisibility() == 8) {
                    z = true;
                } else {
                    z = false;
                }
            }
            linearLayout.setVisibility(z ? 0 : 8);
            if (booleanRef.element) {
                fragmentResultTvBinding.resultBookmarkButton.requestFocus();
            } else {
                fragmentResultTvBinding.resultPlayMovieButton.requestFocus();
            }
            fragmentResultTvBinding.resultSearchButton.setNextFocusRightId(R.id.result_search_Button);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$6$0$0$0(ResultFragmentTv resultFragmentTv, ResultEpisode resultEpisode, View view) {
        ResultViewModel2 resultViewModel2 = resultFragmentTv.viewModel;
        if (resultViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel2 = null;
        }
        resultViewModel2.handleAction(new EpisodeClickEvent(11, resultEpisode));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindingCreated$lambda$6$0$0$1(ResultFragmentTv resultFragmentTv, ResultEpisode resultEpisode, View view) {
        ResultViewModel2 resultViewModel2 = resultFragmentTv.viewModel;
        if (resultViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel2 = null;
        }
        resultViewModel2.handleAction(new EpisodeClickEvent(10, resultEpisode));
        return true;
    }

    static final Unit onBindingCreated$lambda$7(final ResultFragmentTv resultFragmentTv, final SelectPopup selectPopup) {
        BottomSheetDialog bottomSheetDialogShowBottomDialogInstant = null;
        if (selectPopup == null) {
            Dialog dialog = resultFragmentTv.popupDialog;
            if (dialog != null) {
                UIHelper.INSTANCE.dismissSafe(dialog, resultFragmentTv.getActivity());
            }
            resultFragmentTv.popupDialog = null;
            return Unit.INSTANCE;
        }
        Dialog dialog2 = resultFragmentTv.popupDialog;
        if (dialog2 != null) {
            UIHelper.INSTANCE.dismissSafe(dialog2, resultFragmentTv.getActivity());
        }
        FragmentActivity activity = resultFragmentTv.getActivity();
        if (activity != null) {
            FragmentActivity fragmentActivity = activity;
            bottomSheetDialogShowBottomDialogInstant = SingleSelectionHelper.INSTANCE.showBottomDialogInstant(activity, ResultViewModel2Kt.getOptions(selectPopup, fragmentActivity), ResultViewModel2Kt.getTitle(selectPopup, fragmentActivity), new Function0() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda44
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return ResultFragmentTv.onBindingCreated$lambda$7$0$0(this.f$0, selectPopup);
                }
            }, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda51
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ResultFragmentTv.onBindingCreated$lambda$7$0$1(this.f$0, selectPopup, ((Integer) obj).intValue());
                }
            });
        }
        resultFragmentTv.popupDialog = bottomSheetDialogShowBottomDialogInstant;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$7$0$0(ResultFragmentTv resultFragmentTv, SelectPopup selectPopup) {
        resultFragmentTv.popupDialog = null;
        ResultViewModel2Kt.callback(selectPopup, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$7$0$1(ResultFragmentTv resultFragmentTv, SelectPopup selectPopup, int i) {
        resultFragmentTv.popupDialog = null;
        ResultViewModel2Kt.callback(selectPopup, Integer.valueOf(i));
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$8(final ResultFragmentTv resultFragmentTv, LinkProgress linkProgress) {
        MaterialButton materialButton;
        BottomSheetDialog bottomSheetDialog = null;
        if (linkProgress == null) {
            Dialog dialog = resultFragmentTv.loadingDialog;
            if (dialog != null) {
                UIHelper.INSTANCE.dismissSafe(dialog, resultFragmentTv.getActivity());
            }
            resultFragmentTv.loadingDialog = null;
            return Unit.INSTANCE;
        }
        Dialog dialog2 = resultFragmentTv.loadingDialog;
        if (dialog2 == null || !dialog2.isShowing()) {
            Dialog dialog3 = resultFragmentTv.loadingDialog;
            if (dialog3 != null) {
                UIHelper.INSTANCE.dismissSafe(dialog3, resultFragmentTv.getActivity());
            }
            resultFragmentTv.loadingDialog = null;
        }
        BottomSheetDialog bottomSheetDialog2 = resultFragmentTv.loadingDialog;
        if (bottomSheetDialog2 == null) {
            Context context = resultFragmentTv.getContext();
            if (context != null) {
                bottomSheetDialog = new BottomSheetDialog(context);
                bottomSheetDialog.setContentView(R.layout.bottom_loading);
                bottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda49
                    @Override // android.content.DialogInterface.OnDismissListener
                    public final void onDismiss(DialogInterface dialogInterface) {
                        ResultFragmentTv.onBindingCreated$lambda$8$0$0(this.f$0, dialogInterface);
                    }
                });
                bottomSheetDialog.setCanceledOnTouchOutside(true);
                bottomSheetDialog.show();
            }
            bottomSheetDialog2 = bottomSheetDialog;
        }
        resultFragmentTv.loadingDialog = bottomSheetDialog2;
        if (bottomSheetDialog2 != null && (materialButton = (MaterialButton) bottomSheetDialog2.findViewById(R.id.overlay_loading_skip_button)) != null) {
            if (linkProgress.getLinksLoaded() > 0) {
                materialButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda50
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ResultFragmentTv.onBindingCreated$lambda$8$1$0(this.f$0, view);
                    }
                });
                materialButton.setVisibility(0);
                materialButton.setText(materialButton.getContext().getString(R.string.skip_loading) + " (" + linkProgress.getLinksLoaded() + ')');
            } else {
                materialButton.setVisibility(4);
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$8$0$0(ResultFragmentTv resultFragmentTv, DialogInterface dialogInterface) {
        ResultViewModel2 resultViewModel2 = null;
        resultFragmentTv.loadingDialog = null;
        ResultViewModel2 resultViewModel3 = resultFragmentTv.viewModel;
        if (resultViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            resultViewModel2 = resultViewModel3;
        }
        resultViewModel2.cancelLinks();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$8$1$0(ResultFragmentTv resultFragmentTv, View view) {
        ResultViewModel2 resultViewModel2 = resultFragmentTv.viewModel;
        if (resultViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel2 = null;
        }
        resultViewModel2.skipLoading();
    }

    static final Unit onBindingCreated$lambda$9(FragmentResultTvBinding fragmentResultTvBinding, UiText uiText) {
        TextUtilKt.setText(fragmentResultTvBinding.resultEpisodesText, uiText);
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$10(ResultFragmentTv resultFragmentTv, FragmentResultTvBinding fragmentResultTvBinding, int i) {
        resultFragmentTv.select(fragmentResultTvBinding.resultRangeSelection, i);
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$11(ResultFragmentTv resultFragmentTv, FragmentResultTvBinding fragmentResultTvBinding, int i) {
        resultFragmentTv.select(fragmentResultTvBinding.resultSeasonSelection, i);
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$12(ResultFragmentTv resultFragmentTv, FragmentResultTvBinding fragmentResultTvBinding, int i) {
        resultFragmentTv.select(fragmentResultTvBinding.resultDubSelection, i);
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$13(ResultFragmentTv resultFragmentTv, FragmentResultTvBinding fragmentResultTvBinding, List it) {
        Intrinsics.checkNotNullParameter(it, "it");
        resultFragmentTv.update(fragmentResultTvBinding.resultRangeSelection, it);
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$14(ResultFragmentTv resultFragmentTv, FragmentResultTvBinding fragmentResultTvBinding, List it) {
        Intrinsics.checkNotNullParameter(it, "it");
        resultFragmentTv.update(fragmentResultTvBinding.resultDubSelection, it);
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$15(ResultFragmentTv resultFragmentTv, FragmentResultTvBinding fragmentResultTvBinding, List it) {
        Intrinsics.checkNotNullParameter(it, "it");
        resultFragmentTv.update(fragmentResultTvBinding.resultSeasonSelection, it);
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$16(ResultFragmentTv resultFragmentTv, List recommendations) {
        Intrinsics.checkNotNullParameter(recommendations, "recommendations");
        resultFragmentTv.setRecommendations(recommendations, null);
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$17(final ResultFragmentTv resultFragmentTv, String str) {
        Context context = resultFragmentTv.getContext();
        if (context != null) {
            new AlertDialog.Builder(context, R.style.AlertDialogCustom).setMessage(AppContextUtils.INSTANCE.html(str)).setTitle(R.string.synopsis).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda31
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    ResultFragmentTv.onBindingCreated$lambda$17$0$0(this.f$0, dialogInterface);
                }
            }).show();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$17$0$0(ResultFragmentTv resultFragmentTv, DialogInterface dialogInterface) {
        ResultViewModel2 resultViewModel2 = resultFragmentTv.viewModel;
        if (resultViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel2 = null;
        }
        resultViewModel2.releaseEpisodeSynopsis();
    }

    static final Unit onBindingCreated$lambda$18(FragmentResultTvBinding fragmentResultTvBinding, Ref.BooleanRef booleanRef, final ResultFragmentTv resultFragmentTv, Ref.BooleanRef booleanRef2, Resource resource) {
        int iNextIndex;
        String str;
        if (resource == null) {
            return Unit.INSTANCE;
        }
        if (booleanRef.element) {
            fragmentResultTvBinding.resultBookmarkButton.requestFocus();
        }
        if (resource instanceof Resource.Success) {
            Resource.Success success = (Resource.Success) resource;
            List list = (List) success.getValue();
            ListIterator listIterator = list.listIterator(list.size());
            while (true) {
                if (!listIterator.hasPrevious()) {
                    iNextIndex = -1;
                    break;
                }
                ResultEpisode resultEpisode = (ResultEpisode) listIterator.previous();
                if (ResultFragmentKt.getWatchProgress(resultEpisode) >= 0.9f || resultEpisode.getVideoWatchState() == VideoWatchState.Watched) {
                    iNextIndex = listIterator.nextIndex();
                    break;
                }
            }
            List list2 = (List) success.getValue();
            int i = iNextIndex + 1;
            final ResultEpisode resultEpisode2 = (ResultEpisode) ((i < 0 || i >= list2.size()) ? (ResultEpisode) CollectionsKt.firstOrNull((List) success.getValue()) : list2.get(i));
            if (resultEpisode2 != null) {
                TextView textView = fragmentResultTvBinding.resultPlaySeriesText;
                if (resultEpisode2.getSeason() != null) {
                    str = resultFragmentTv.getString(R.string.season_short) + resultEpisode2.getSeason().intValue() + AbstractJsonLexerKt.COLON + resultFragmentTv.getString(R.string.episode_short) + resultEpisode2.getEpisode();
                } else {
                    str = resultFragmentTv.getString(R.string.episode) + ' ' + resultEpisode2.getEpisode();
                }
                textView.setText(str);
                fragmentResultTvBinding.resultPlaySeriesButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda39
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ResultFragmentTv.onBindingCreated$lambda$18$0$2(this.f$0, resultEpisode2, view);
                    }
                });
                fragmentResultTvBinding.resultPlaySeriesButton.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda40
                    @Override // android.view.View.OnLongClickListener
                    public final boolean onLongClick(View view) {
                        return ResultFragmentTv.onBindingCreated$lambda$18$0$3(this.f$0, resultEpisode2, view);
                    }
                });
                if (!booleanRef2.element) {
                    booleanRef2.element = true;
                    LinearLayout resultPlaySeries = fragmentResultTvBinding.resultPlaySeries;
                    Intrinsics.checkNotNullExpressionValue(resultPlaySeries, "resultPlaySeries");
                    LinearLayout linearLayout = resultPlaySeries;
                    LinearLayout resultResumeSeries = fragmentResultTvBinding.resultResumeSeries;
                    Intrinsics.checkNotNullExpressionValue(resultResumeSeries, "resultResumeSeries");
                    linearLayout.setVisibility(resultResumeSeries.getVisibility() == 8 && !booleanRef.element ? 0 : 8);
                    LinearLayout resultEpisodesShow = fragmentResultTvBinding.resultEpisodesShow;
                    Intrinsics.checkNotNullExpressionValue(resultEpisodesShow, "resultEpisodesShow");
                    resultEpisodesShow.setVisibility(booleanRef.element ? 8 : 0);
                    fragmentResultTvBinding.resultPlaySeriesButton.requestFocus();
                }
            }
            RecyclerView.Adapter adapter = fragmentResultTvBinding.resultEpisodes.getAdapter();
            EpisodeAdapter episodeAdapter = adapter instanceof EpisodeAdapter ? (EpisodeAdapter) adapter : null;
            if (episodeAdapter != null) {
                BaseAdapter.submitList$default(episodeAdapter, (Collection) success.getValue(), null, 2, null);
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$18$0$2(ResultFragmentTv resultFragmentTv, ResultEpisode resultEpisode, View view) {
        ResultViewModel2 resultViewModel2 = resultFragmentTv.viewModel;
        if (resultViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel2 = null;
        }
        resultViewModel2.handleAction(new EpisodeClickEvent(11, resultEpisode));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindingCreated$lambda$18$0$3(ResultFragmentTv resultFragmentTv, ResultEpisode resultEpisode, View view) {
        ResultViewModel2 resultViewModel2 = resultFragmentTv.viewModel;
        if (resultViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            resultViewModel2 = null;
        }
        resultViewModel2.handleAction(new EpisodeClickEvent(10, resultEpisode));
        return true;
    }

    static final Unit onBindingCreated$lambda$19(FragmentResultTvBinding fragmentResultTvBinding, Ref.BooleanRef booleanRef, ResultFragment.StoredData storedData, final ResultFragmentTv resultFragmentTv, Resource resource) {
        List<ActorData> actors;
        if (resource == null) {
            return Unit.INSTANCE;
        }
        boolean z = resource instanceof Resource.Success;
        if (z) {
            final ResultData resultData = (ResultData) ((Resource.Success) resource).getValue();
            TextUtilKt.setText(fragmentResultTvBinding.resultVpn, resultData.getVpnText());
            TextUtilKt.setText(fragmentResultTvBinding.resultInfo, resultData.getMetaText());
            TextUtilKt.setText(fragmentResultTvBinding.resultNoEpisodes, resultData.getNoEpisodesFoundText());
            TextUtilKt.setText(fragmentResultTvBinding.resultTitle, resultData.getTitleText());
            TextUtilKt.setText(fragmentResultTvBinding.resultMetaSite, resultData.getApiName());
            TextUtilKt.setText(fragmentResultTvBinding.resultMetaType, resultData.getTypeText());
            TextUtilKt.setText(fragmentResultTvBinding.resultMetaYear, resultData.getYearText());
            TextUtilKt.setText(fragmentResultTvBinding.resultMetaDuration, resultData.getDurationText());
            TextUtilKt.setText(fragmentResultTvBinding.resultMetaRating, resultData.getRatingText());
            TextUtilKt.setText(fragmentResultTvBinding.resultMetaStatus, resultData.getOnGoingText());
            TextUtilKt.setText(fragmentResultTvBinding.resultMetaContentRating, resultData.getContentRatingText());
            TextUtilKt.setText(fragmentResultTvBinding.resultCastText, resultData.getActorsText());
            TextUtilKt.setText(fragmentResultTvBinding.resultNextAiring, resultData.getNextAiringEpisode());
            TextUtilKt.setText(fragmentResultTvBinding.resultNextAiringTime, resultData.getNextAiringDate());
            ImageLoader imageLoader = ImageLoader.INSTANCE;
            ImageView resultPoster = fragmentResultTvBinding.resultPoster;
            Intrinsics.checkNotNullExpressionValue(resultPoster, "resultPoster");
            ImageLoader.loadImage$default(imageLoader, resultPoster, resultData.getPosterImage(), (Map) null, (Function1) null, 6, (Object) null);
            final Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
            final TextView textView = fragmentResultTvBinding.resultDescription;
            TextUtilKt.setTextHtml(textView, resultData.getPlotText());
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda36
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ResultFragmentTv.onBindingCreated$lambda$19$0$0$0(booleanRef2, textView, resultData, view);
                }
            });
            boolean z2 = true;
            final int iIntValue = ((Number) CollectionsKt.random(CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(R.drawable.profile_bg_dark_blue), Integer.valueOf(R.drawable.profile_bg_blue), Integer.valueOf(R.drawable.profile_bg_orange), Integer.valueOf(R.drawable.profile_bg_pink), Integer.valueOf(R.drawable.profile_bg_purple), Integer.valueOf(R.drawable.profile_bg_red), Integer.valueOf(R.drawable.profile_bg_teal)}), Random.INSTANCE)).intValue();
            ImageLoader imageLoader2 = ImageLoader.INSTANCE;
            PercentageCropImageView backgroundPoster = fragmentResultTvBinding.backgroundPoster;
            Intrinsics.checkNotNullExpressionValue(backgroundPoster, "backgroundPoster");
            ImageLoader.loadImage$default(imageLoader2, backgroundPoster, resultData.getPosterBackgroundImage(), (Map) null, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda37
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ResultFragmentTv.onBindingCreated$lambda$19$0$1(this.f$0, iIntValue, (ImageRequest.Builder) obj);
                }
            }, 2, (Object) null);
            ResultFragment resultFragment = ResultFragment.INSTANCE;
            String logoUrl = resultData.getLogoUrl();
            Map<String, String> posterHeaders = resultData.getPosterHeaders();
            TextView resultTitle = fragmentResultTvBinding.resultTitle;
            Intrinsics.checkNotNullExpressionValue(resultTitle, "resultTitle");
            ImageView backgroundPosterWatermarkBadgeHolder = fragmentResultTvBinding.backgroundPosterWatermarkBadgeHolder;
            Intrinsics.checkNotNullExpressionValue(backgroundPosterWatermarkBadgeHolder, "backgroundPosterWatermarkBadgeHolder");
            resultFragment.bindLogo(logoUrl, posterHeaders, backgroundPosterWatermarkBadgeHolder, resultTitle);
            booleanRef.element = resultData.getComingSoon();
            TextView resultTvComingSoon = fragmentResultTvBinding.resultTvComingSoon;
            Intrinsics.checkNotNullExpressionValue(resultTvComingSoon, "resultTvComingSoon");
            resultTvComingSoon.setVisibility(resultData.getComingSoon() ? 0 : 8);
            UIHelper.populateChips$default(UIHelper.INSTANCE, fragmentResultTvBinding.resultTag, resultData.getTags(), 0, null, 12, null);
            boolean z3 = PreferenceManager.getDefaultSharedPreferences(fragmentResultTvBinding.getRoot().getContext()).getBoolean(fragmentResultTvBinding.getRoot().getContext().getString(R.string.show_cast_in_details_key), true);
            RecyclerView resultCastItems = fragmentResultTvBinding.resultCastItems;
            Intrinsics.checkNotNullExpressionValue(resultCastItems, "resultCastItems");
            RecyclerView recyclerView = resultCastItems;
            if (z3 && (actors = resultData.getActors()) != null && !actors.isEmpty()) {
                z2 = false;
            }
            recyclerView.setVisibility(z2 ? 8 : 0);
            RecyclerView.Adapter adapter = fragmentResultTvBinding.resultCastItems.getAdapter();
            ActorAdaptor actorAdaptor = adapter instanceof ActorAdaptor ? (ActorAdaptor) adapter : null;
            if (actorAdaptor != null) {
                BaseAdapter.submitList$default(actorAdaptor, z3 ? resultData.getActors() : CollectionsKt.emptyList(), null, 2, null);
            }
            if (resultData.getContentRatingText() == null) {
                fragmentResultTvBinding.resultMetaContentRating.setWidth(0);
            }
            fragmentResultTvBinding.resultSearchButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda38
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ResultFragmentTv.onBindingCreated$lambda$19$0$2(this.f$0, resultData, view);
                }
            });
        } else if (!(resource instanceof Resource.Loading)) {
            if (!(resource instanceof Resource.Failure)) {
                throw new NoWhenBranchMatchedException();
            }
            fragmentResultTvBinding.resultErrorText.setText(storedData.getUrl() + '\n' + ((Resource.Failure) resource).getErrorString());
        }
        NestedScrollView resultFinishLoading = fragmentResultTvBinding.resultFinishLoading;
        Intrinsics.checkNotNullExpressionValue(resultFinishLoading, "resultFinishLoading");
        resultFinishLoading.setVisibility(z ? 0 : 8);
        ShimmerFrameLayout resultLoading = fragmentResultTvBinding.resultLoading;
        Intrinsics.checkNotNullExpressionValue(resultLoading, "resultLoading");
        resultLoading.setVisibility(resource instanceof Resource.Loading ? 0 : 8);
        LinearLayout resultLoadingError = fragmentResultTvBinding.resultLoadingError;
        Intrinsics.checkNotNullExpressionValue(resultLoadingError, "resultLoadingError");
        resultLoadingError.setVisibility(resource instanceof Resource.Failure ? 0 : 8);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$19$0$0$0(Ref.BooleanRef booleanRef, TextView textView, ResultData resultData, View view) {
        if (Globals.INSTANCE.isLayout(4)) {
            booleanRef.element = !booleanRef.element;
            textView.setMaxLines(booleanRef.element ? Integer.MAX_VALUE : 10);
        } else {
            Context context = textView.getContext();
            if (context != null) {
                new AlertDialog.Builder(context, R.style.AlertDialogCustom).setMessage(AppContextUtils.INSTANCE.html(resultData.getPlotText().asString(context))).setTitle(resultData.getPlotHeaderText().asString(context)).show();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$19$0$1(final ResultFragmentTv resultFragmentTv, final int i, ImageRequest.Builder loadImage) {
        Intrinsics.checkNotNullParameter(loadImage, "$this$loadImage");
        loadImage.error(new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$$ExternalSyntheticLambda35
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentTv.onBindingCreated$lambda$19$0$1$0(this.f$0, i, (ImageRequest) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Image onBindingCreated$lambda$19$0$1$0(ResultFragmentTv resultFragmentTv, int i, ImageRequest it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Context context = resultFragmentTv.getContext();
        if (context == null) {
            return null;
        }
        return ImageUtilKt.getImageFromDrawable(context, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$19$0$2(ResultFragmentTv resultFragmentTv, ResultData resultData, View view) {
        QuickSearchFragment.Companion.pushSearch$default(QuickSearchFragment.INSTANCE, resultFragmentTv.getActivity(), resultData.getTitle(), null, 4, null);
    }

    private final void fade(final View view, final boolean z) {
        if (z) {
            view.setVisibility(0);
        }
        float f = 0.0f;
        ViewPropertyAnimator viewPropertyAnimatorAlpha = view.animate().alpha(z ? 0.97f : 0.0f);
        viewPropertyAnimatorAlpha.setDuration(200L);
        viewPropertyAnimatorAlpha.setInterpolator(new DecelerateInterpolator());
        viewPropertyAnimatorAlpha.setListener(new Animator.AnimatorListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentTv$fade$1$1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                view.setVisibility(z ? 0 : 8);
            }
        });
        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
        if (!z) {
            f = AppContextUtils.INSTANCE.isRtl(view) ? -100.0f : 100.0f;
        }
        ViewPropertyAnimator viewPropertyAnimatorTranslationX = viewPropertyAnimatorAnimate.translationX(f);
        viewPropertyAnimatorTranslationX.setDuration(200L);
        viewPropertyAnimatorTranslationX.setInterpolator(new DecelerateInterpolator());
    }
}
