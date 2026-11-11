package com.lagradost.cloudstream3.ui.result;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.mediarouter.app.MediaRouteButton;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;
import coil3.Image;
import coil3.network.internal.UtilsKt;
import coil3.request.ImageRequest;
import com.discord.panels.OverlappingPanelsLayout;
import com.discord.panels.PanelState;
import com.discord.panels.PanelsChildGestureRegionObserver;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.cast.framework.CastButtonFactory;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.slider.Slider;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.ActorData;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.DubStatus;
import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivity;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.Score;
import com.lagradost.cloudstream3.SearchResponse;
import com.lagradost.cloudstream3.databinding.FragmentResultBinding;
import com.lagradost.cloudstream3.databinding.FragmentResultSwipeBinding;
import com.lagradost.cloudstream3.databinding.FragmentTrailerBinding;
import com.lagradost.cloudstream3.databinding.ResultRecommendationsBinding;
import com.lagradost.cloudstream3.databinding.ResultSyncBinding;
import com.lagradost.cloudstream3.databinding.TrailerCustomLayoutBinding;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.mvvm.LifecycleKt;
import com.lagradost.cloudstream3.mvvm.Resource;
import com.lagradost.cloudstream3.services.SubscriptionWorkManager;
import com.lagradost.cloudstream3.syncproviders.SyncAPI;
import com.lagradost.cloudstream3.ui.APIRepository;
import com.lagradost.cloudstream3.ui.AutofitRecyclerView;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.BaseAdapterKt;
import com.lagradost.cloudstream3.ui.BaseFragment;
import com.lagradost.cloudstream3.ui.WatchType;
import com.lagradost.cloudstream3.ui.download.DownloadButtonSetup;
import com.lagradost.cloudstream3.ui.download.DownloadClickEvent;
import com.lagradost.cloudstream3.ui.download.button.DownloadButton;
import com.lagradost.cloudstream3.ui.player.CS3IPlayer;
import com.lagradost.cloudstream3.ui.player.CSPlayerEvent;
import com.lagradost.cloudstream3.ui.player.DownloadEvent;
import com.lagradost.cloudstream3.ui.player.IPlayer;
import com.lagradost.cloudstream3.ui.player.PlayerView;
import com.lagradost.cloudstream3.ui.player.SubtitleData;
import com.lagradost.cloudstream3.ui.quicksearch.QuickSearchFragment;
import com.lagradost.cloudstream3.ui.search.SearchAdapter;
import com.lagradost.cloudstream3.ui.search.SearchClickCallback;
import com.lagradost.cloudstream3.ui.search.SearchHelper;
import com.lagradost.cloudstream3.ui.settings.SettingsGeneral;
import com.lagradost.cloudstream3.ui.settings.utils.DirectoryPickerKt;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.BackPressedCallbackHelper;
import com.lagradost.cloudstream3.utils.BatteryOptimizationChecker;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.ImageLoader;
import com.lagradost.cloudstream3.utils.ImageUtilKt;
import com.lagradost.cloudstream3.utils.SingleSelectionHelper;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UIHelper;
import com.lagradost.cloudstream3.utils.UiText;
import com.lagradost.cloudstream3.utils.downloader.DownloadFileManagement;
import com.lagradost.cloudstream3.utils.downloader.DownloadObjects;
import com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager;
import com.lagradost.cloudstream3.utils.videoskip.VideoSkipStamp;
import com.lagradost.safefile.SafeFile;
import j$.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntries;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: ResultFragmentPhone.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000é\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0007\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\"\u0010\u000e\u001a\u00020\u000f2\u0018\u0010\u0010\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b0\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010T\u001a\u00020\u000fH\u0016J\b\u0010U\u001a\u00020\u000fH\u0004J\b\u0010V\u001a\u00020\u000fH\u0016J\b\u0010W\u001a\u00020\u000fH\u0016J(\u0010X\u001a\u00020\u000f2\u0006\u0010Y\u001a\u00020Z2\b\u0010[\u001a\u0004\u0018\u00010\\2\f\u0010]\u001a\b\u0012\u0004\u0012\u00020\u000f0^H\u0016J\u0010\u0010_\u001a\u00020\u000f2\u0006\u0010`\u001a\u00020aH\u0016J\u0012\u0010b\u001a\u00020\u000f2\b\u0010c\u001a\u0004\u0018\u00010dH\u0016J\b\u0010r\u001a\u00020\u000fH\u0016J\b\u0010s\u001a\u00020<H\u0016J\u0010\u0010t\u001a\u00020\u000f2\u0006\u0010u\u001a\u00020vH\u0016J\u0019\u0010w\u001a\u00020\u000f2\n\b\u0002\u0010x\u001a\u0004\u0018\u00010\fH\u0002¢\u0006\u0002\u0010yJ$\u0010z\u001a\u00020\u000f2\u001a\u0010{\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020g\u0012\u0004\u0012\u00020h0\u000b\u0018\u00010fH\u0002J\b\u0010|\u001a\u00020\u000fH\u0016J\u001f\u0010\u0086\u0001\u001a\u00020\u000f2\t\u0010\u0087\u0001\u001a\u0004\u0018\u00010a2\t\u0010\u0088\u0001\u001a\u0004\u0018\u00010aH\u0002J\u0014\u0010\u0091\u0001\u001a\u00020\u000f2\t\u0010\u0092\u0001\u001a\u0004\u0018\u00010hH\u0002J\u0012\u0010\u0093\u0001\u001a\u00020\u000f2\u0007\u0010\u0094\u0001\u001a\u00020<H\u0002J\t\u0010\u0095\u0001\u001a\u00020\u000fH\u0016J\t\u0010\u0096\u0001\u001a\u00020\u000fH\u0016J\u0019\u0010\u0097\u0001\u001a\u00020\u000f2\t\u0010\u0098\u0001\u001a\u0004\u0018\u00010\fH\u0002¢\u0006\u0002\u0010yJ\u001c\u0010\u0099\u0001\u001a\u00020\u000f2\u0007\u0010\u009a\u0001\u001a\u00020\u00022\b\u0010c\u001a\u0004\u0018\u00010dH\u0016J\u001d\u0010\u009b\u0001\u001a\u00020\u000f2\b\u0010\u009c\u0001\u001a\u00030\u009d\u00012\b\u0010\u009e\u0001\u001a\u00030\u009f\u0001H\u0002J\t\u0010 \u0001\u001a\u00020\u000fH\u0016J&\u0010¡\u0001\u001a\u00020\u000f2\u0010\u0010¢\u0001\u001a\u000b\u0012\u0005\u0012\u00030£\u0001\u0018\u00010f2\t\u0010¤\u0001\u001a\u0004\u0018\u00010hH\u0002R\u0010\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\bR \u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u00020\u0018X\u0084.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u001eX\u0084.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001c\u0010#\u001a\u0004\u0018\u00010$X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001c\u0010)\u001a\u0004\u0018\u00010*X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001c\u0010/\u001a\u0004\u0018\u000100X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u001a\u00105\u001a\u000206X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u001a\u0010;\u001a\u00020<X\u0094\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u001a\u0010A\u001a\u00020<X\u0094\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010>\"\u0004\bB\u0010@R\u001a\u0010C\u001a\u00020<X\u0094\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010>\"\u0004\bE\u0010@R\u001c\u0010F\u001a\u0004\u0018\u00010GX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR\u001a\u0010L\u001a\u00020<X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010>\"\u0004\bM\u0010@R\u001c\u0010N\u001a\u0004\u0018\u00010OX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR,\u0010e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020g\u0012\u0004\u0012\u00020h0\u000b0fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bi\u0010j\"\u0004\bk\u0010lR\u001a\u0010m\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bn\u0010o\"\u0004\bp\u0010qR\u001f\u0010}\u001a\u0004\u0018\u00010~X\u0086\u000e¢\u0006\u0011\n\u0000\u001a\u0005\b\u007f\u0010\u0080\u0001\"\u0006\b\u0081\u0001\u0010\u0082\u0001R!\u0010\u0083\u0001\u001a\u0004\u0018\u00010~X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0084\u0001\u0010\u0080\u0001\"\u0006\b\u0085\u0001\u0010\u0082\u0001R!\u0010\u0089\u0001\u001a\u0004\u0018\u00010hX\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u008a\u0001\u0010\u008b\u0001\"\u0006\b\u008c\u0001\u0010\u008d\u0001R!\u0010\u008e\u0001\u001a\u0004\u0018\u00010hX\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u008f\u0001\u0010\u008b\u0001\"\u0006\b\u0090\u0001\u0010\u008d\u0001¨\u0006¥\u0001"}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/ResultFragmentPhone;", "Lcom/lagradost/cloudstream3/ui/BaseFragment;", "Lcom/lagradost/cloudstream3/databinding/FragmentResultSwipeBinding;", "Lcom/lagradost/cloudstream3/ui/player/PlayerView$Callbacks;", "<init>", "()V", "gestureRegionsListener", "com/lagradost/cloudstream3/ui/result/ResultFragmentPhone$gestureRegionsListener$1", "Lcom/lagradost/cloudstream3/ui/result/ResultFragmentPhone$gestureRegionsListener$1;", "pendingPathActions", "Ljava/util/concurrent/ConcurrentLinkedDeque;", "Lkotlin/Pair;", "", "Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "requirePathForActions", "", "list", "", "processPendingActions", "Lkotlinx/coroutines/Job;", "pathPicker", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/net/Uri;", "viewModel", "Lcom/lagradost/cloudstream3/ui/result/ResultViewModel2;", "getViewModel", "()Lcom/lagradost/cloudstream3/ui/result/ResultViewModel2;", "setViewModel", "(Lcom/lagradost/cloudstream3/ui/result/ResultViewModel2;)V", "syncModel", "Lcom/lagradost/cloudstream3/ui/result/SyncViewModel;", "getSyncModel", "()Lcom/lagradost/cloudstream3/ui/result/SyncViewModel;", "setSyncModel", "(Lcom/lagradost/cloudstream3/ui/result/SyncViewModel;)V", "resultBinding", "Lcom/lagradost/cloudstream3/databinding/FragmentResultBinding;", "getResultBinding", "()Lcom/lagradost/cloudstream3/databinding/FragmentResultBinding;", "setResultBinding", "(Lcom/lagradost/cloudstream3/databinding/FragmentResultBinding;)V", "recommendationBinding", "Lcom/lagradost/cloudstream3/databinding/ResultRecommendationsBinding;", "getRecommendationBinding", "()Lcom/lagradost/cloudstream3/databinding/ResultRecommendationsBinding;", "setRecommendationBinding", "(Lcom/lagradost/cloudstream3/databinding/ResultRecommendationsBinding;)V", "syncBinding", "Lcom/lagradost/cloudstream3/databinding/ResultSyncBinding;", "getSyncBinding", "()Lcom/lagradost/cloudstream3/databinding/ResultSyncBinding;", "setSyncBinding", "(Lcom/lagradost/cloudstream3/databinding/ResultSyncBinding;)V", "player", "Lcom/lagradost/cloudstream3/ui/player/IPlayer;", "getPlayer", "()Lcom/lagradost/cloudstream3/ui/player/IPlayer;", "setPlayer", "(Lcom/lagradost/cloudstream3/ui/player/IPlayer;)V", "hasPipModeSupport", "", "getHasPipModeSupport", "()Z", "setHasPipModeSupport", "(Z)V", "isFullScreenPlayer", "setFullScreenPlayer", "lockRotation", "getLockRotation", "setLockRotation", "playerBinding", "Lcom/lagradost/cloudstream3/databinding/TrailerCustomLayoutBinding;", "getPlayerBinding", "()Lcom/lagradost/cloudstream3/databinding/TrailerCustomLayoutBinding;", "setPlayerBinding", "(Lcom/lagradost/cloudstream3/databinding/TrailerCustomLayoutBinding;)V", "isShowing", "setShowing", "playerHostView", "Lcom/lagradost/cloudstream3/ui/player/PlayerView;", "getPlayerHostView", "()Lcom/lagradost/cloudstream3/ui/player/PlayerView;", "setPlayerHostView", "(Lcom/lagradost/cloudstream3/ui/player/PlayerView;)V", "updateUIVisibility", "uiReset", "showMirrorsDialogue", "showTracksDialogue", "openOnlineSubPicker", "context", "Landroid/content/Context;", "loadResponse", "Lcom/lagradost/cloudstream3/LoadResponse;", "dismissCallback", "Lkotlin/Function0;", "fixLayout", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "currentTrailers", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "", "getCurrentTrailers", "()Ljava/util/List;", "setCurrentTrailers", "(Ljava/util/List;)V", "currentTrailerIndex", "getCurrentTrailerIndex", "()I", "setCurrentTrailerIndex", "(I)V", "nextMirror", "hasNextMirror", "playerError", "exception", "", "loadTrailer", "index", "(Ljava/lang/Integer;)V", "setTrailers", "trailers", "onDestroyView", "loadingDialog", "Landroid/app/Dialog;", "getLoadingDialog", "()Landroid/app/Dialog;", "setLoadingDialog", "(Landroid/app/Dialog;)V", "popupDialog", "getPopupDialog", "setPopupDialog", "setFocusUpAndDown", "upper", "down", "selectSeason", "getSelectSeason", "()Ljava/lang/String;", "setSelectSeason", "(Ljava/lang/String;)V", "selectEpisodeRange", "getSelectEpisodeRange", "setSelectEpisodeRange", "setUrl", ImagesContract.URL, "reloadViewModel", "forceReload", "onResume", "onStop", "updateUI", "id", "onBindingCreated", "binding", "resumeAction", "storedData", "Lcom/lagradost/cloudstream3/ui/result/ResultFragment$StoredData;", "resume", "Lcom/lagradost/cloudstream3/ui/result/ResumeWatchingStatus;", "onPause", "setRecommendations", "rec", "Lcom/lagradost/cloudstream3/SearchResponse;", "validApiName", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class ResultFragmentPhone extends BaseFragment<FragmentResultSwipeBinding> implements PlayerView.Callbacks {
    private int currentTrailerIndex;
    private List<? extends Pair<? extends ExtractorLink, String>> currentTrailers;
    private final ResultFragmentPhone$gestureRegionsListener$1 gestureRegionsListener;
    private boolean hasPipModeSupport;
    private boolean isFullScreenPlayer;
    private boolean isShowing;
    private Dialog loadingDialog;
    private boolean lockRotation;
    private final ActivityResultLauncher<Uri> pathPicker;
    private final ConcurrentLinkedDeque<Pair<Integer, ResultEpisode>> pendingPathActions;
    private IPlayer player;
    private TrailerCustomLayoutBinding playerBinding;
    private PlayerView playerHostView;
    private Dialog popupDialog;
    private ResultRecommendationsBinding recommendationBinding;
    private FragmentResultBinding resultBinding;
    private String selectEpisodeRange;
    private String selectSeason;
    private ResultSyncBinding syncBinding;
    protected SyncViewModel syncModel;
    protected ResultViewModel2 viewModel;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$9$0$1$1(DialogInterface dialogInterface, int i) {
    }

    public void openOnlineSubPicker(Context context, LoadResponse loadResponse, Function0<Unit> dismissCallback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dismissCallback, "dismissCallback");
    }

    public void showMirrorsDialogue() {
    }

    public void showTracksDialogue() {
    }

    public void updateUIVisibility() {
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$1, reason: invalid class name */
    /* JADX INFO: compiled from: ResultFragmentPhone.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function3<LayoutInflater, ViewGroup, Boolean, FragmentResultSwipeBinding> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(3, FragmentResultSwipeBinding.class, "inflate", "inflate(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Z)Lcom/lagradost/cloudstream3/databinding/FragmentResultSwipeBinding;", 0);
        }

        public final FragmentResultSwipeBinding invoke(LayoutInflater p0, ViewGroup viewGroup, boolean z) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return FragmentResultSwipeBinding.inflate(p0, viewGroup, z);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ FragmentResultSwipeBinding invoke(LayoutInflater layoutInflater, ViewGroup viewGroup, Boolean bool) {
            return invoke(layoutInflater, viewGroup, bool.booleanValue());
        }
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void embeddedSubtitlesFetched(List<SubtitleData> list) {
        Intrinsics.checkNotNullParameter(list, "subtitles");
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void exitedPipMode() {
        PlayerView.Callbacks.CC.$default$exitedPipMode(this);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ boolean isUIShowing() {
        return PlayerView.Callbacks.CC.$default$isUIShowing(this);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void nextEpisode() {
        PlayerView.Callbacks.CC.$default$nextEpisode(this);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void onAutoHideUI() {
        PlayerView.Callbacks.CC.$default$onAutoHideUI(this);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void onBrightnessExtra(float f) {
        PlayerView.Callbacks.CC.$default$onBrightnessExtra(this, f);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void onDownload(DownloadEvent downloadEvent) {
        Intrinsics.checkNotNullParameter(downloadEvent, "event");
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void onGestureEnd(boolean z, boolean z2) {
        PlayerView.Callbacks.CC.$default$onGestureEnd(this, z, z2);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void onHidePlayerUI() {
        PlayerView.Callbacks.CC.$default$onHidePlayerUI(this);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void onHoldSpeedUp(boolean z) {
        PlayerView.Callbacks.CC.$default$onHoldSpeedUp(this, z);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void onSeekPreviewText(String str) {
        PlayerView.Callbacks.CC.$default$onSeekPreviewText(this, str);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void onSingleTap() {
        PlayerView.Callbacks.CC.$default$onSingleTap(this);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void onTimestamp(VideoSkipStamp videoSkipStamp) {
        PlayerView.Callbacks.CC.$default$onTimestamp(this, videoSkipStamp);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void onTimestampSkipped(VideoSkipStamp videoSkipStamp) {
        Intrinsics.checkNotNullParameter(videoSkipStamp, "timestamp");
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void onTouchDown() {
        PlayerView.Callbacks.CC.$default$onTouchDown(this);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void onTracksInfoChanged() {
        PlayerView.Callbacks.CC.$default$onTracksInfoChanged(this);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void playerDimensionsLoaded(int i, int i2) {
        PlayerView.Callbacks.CC.$default$playerDimensionsLoaded(this, i, i2);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void playerPositionChanged(long j, long j2) {
        PlayerView.Callbacks.CC.$default$playerPositionChanged(this, j, j2);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void playerStatusChanged() {
        PlayerView.Callbacks.CC.$default$playerStatusChanged(this);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void playerUpdated(Object obj) {
        PlayerView.Callbacks.CC.$default$playerUpdated(this, obj);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void prevEpisode() {
        PlayerView.Callbacks.CC.$default$prevEpisode(this);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void subtitlesChanged() {
        PlayerView.Callbacks.CC.$default$subtitlesChanged(this);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$gestureRegionsListener$1] */
    public ResultFragmentPhone() {
        super(new BaseFragment.BindingCreator.Inflate(AnonymousClass1.INSTANCE));
        this.gestureRegionsListener = new PanelsChildGestureRegionObserver.GestureRegionsListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$gestureRegionsListener$1
            @Override // com.discord.panels.PanelsChildGestureRegionObserver.GestureRegionsListener
            public void onGestureRegionsUpdate(List<Rect> gestureRegions) {
                OverlappingPanelsLayout overlappingPanelsLayout;
                Intrinsics.checkNotNullParameter(gestureRegions, "gestureRegions");
                FragmentResultSwipeBinding binding = this.this$0.getBinding();
                if (binding == null || (overlappingPanelsLayout = binding.resultOverlappingPanels) == null) {
                    return;
                }
                overlappingPanelsLayout.setChildGestureRegions(gestureRegions);
            }
        };
        this.pendingPathActions = new ConcurrentLinkedDeque<>();
        this.pathPicker = DirectoryPickerKt.getChooseFolderLauncher(this, new Function2() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda52
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ResultFragmentPhone.pathPicker$lambda$0(this.f$0, (Uri) obj, (String) obj2);
            }
        });
        this.player = new CS3IPlayer();
        this.isFullScreenPlayer = true;
        this.lockRotation = true;
        this.currentTrailers = CollectionsKt.emptyList();
    }

    private final void requirePathForActions(Collection<Pair<Integer, ResultEpisode>> list) {
        Pair<SafeFile, String> basePath$app;
        this.pendingPathActions.addAll(list);
        Context context = getContext();
        if (context == null || (basePath$app = DownloadFileManagement.INSTANCE.getBasePath$app(context)) == null) {
            return;
        }
        if (basePath$app.component2() == null) {
            try {
                CommonActivity.showToast$default(CommonActivity.INSTANCE, R.string.download_path_pref, (Integer) null, 2, (Object) null);
                this.pathPicker.launch(Uri.EMPTY);
                Unit unit = Unit.INSTANCE;
                return;
            } catch (Throwable th) {
                ArchComponentExtKt.logError(th);
                processPendingActions();
                return;
            }
        }
        processPendingActions();
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$processPendingActions$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ResultFragmentPhone.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$processPendingActions$1", f = "ResultFragmentPhone.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C08761 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C08761(Continuation<? super C08761> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ResultFragmentPhone.this.new C08761(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08761) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                while (!ResultFragmentPhone.this.pendingPathActions.isEmpty()) {
                    try {
                        Pair pair = (Pair) ResultFragmentPhone.this.pendingPathActions.pop();
                        ResultFragmentPhone.this.getViewModel().handleAction(new EpisodeClickEvent(((Number) pair.component1()).intValue(), (ResultEpisode) pair.component2()));
                    } catch (NoSuchElementException unused) {
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    private final Job processPendingActions() {
        return ArchComponentExtKt.launchSafe$default(ViewModelKt.getViewModelScope(getViewModel()), null, null, new C08761(null), 3, null);
    }

    static final Unit pathPicker$lambda$0(ResultFragmentPhone resultFragmentPhone, Uri uri, String str) {
        if (uri == null) {
            if (!resultFragmentPhone.pendingPathActions.isEmpty()) {
                CommonActivity.showToast$default(CommonActivity.INSTANCE, R.string.download_canceled, (Integer) null, 2, (Object) null);
                resultFragmentPhone.pendingPathActions.clear();
            }
        } else {
            SettingsGeneral.INSTANCE.pickDownloadPath(resultFragmentPhone, uri, str);
            resultFragmentPhone.processPendingActions();
        }
        return Unit.INSTANCE;
    }

    protected final ResultViewModel2 getViewModel() {
        ResultViewModel2 resultViewModel2 = this.viewModel;
        if (resultViewModel2 != null) {
            return resultViewModel2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        return null;
    }

    protected final void setViewModel(ResultViewModel2 resultViewModel2) {
        Intrinsics.checkNotNullParameter(resultViewModel2, "<set-?>");
        this.viewModel = resultViewModel2;
    }

    protected final SyncViewModel getSyncModel() {
        SyncViewModel syncViewModel = this.syncModel;
        if (syncViewModel != null) {
            return syncViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("syncModel");
        return null;
    }

    protected final void setSyncModel(SyncViewModel syncViewModel) {
        Intrinsics.checkNotNullParameter(syncViewModel, "<set-?>");
        this.syncModel = syncViewModel;
    }

    protected final FragmentResultBinding getResultBinding() {
        return this.resultBinding;
    }

    protected final void setResultBinding(FragmentResultBinding fragmentResultBinding) {
        this.resultBinding = fragmentResultBinding;
    }

    protected final ResultRecommendationsBinding getRecommendationBinding() {
        return this.recommendationBinding;
    }

    protected final void setRecommendationBinding(ResultRecommendationsBinding resultRecommendationsBinding) {
        this.recommendationBinding = resultRecommendationsBinding;
    }

    protected final ResultSyncBinding getSyncBinding() {
        return this.syncBinding;
    }

    protected final void setSyncBinding(ResultSyncBinding resultSyncBinding) {
        this.syncBinding = resultSyncBinding;
    }

    public final IPlayer getPlayer() {
        return this.player;
    }

    public final void setPlayer(IPlayer iPlayer) {
        Intrinsics.checkNotNullParameter(iPlayer, "<set-?>");
        this.player = iPlayer;
    }

    protected boolean getHasPipModeSupport() {
        return this.hasPipModeSupport;
    }

    protected void setHasPipModeSupport(boolean z) {
        this.hasPipModeSupport = z;
    }

    /* JADX INFO: renamed from: isFullScreenPlayer, reason: from getter */
    protected boolean getIsFullScreenPlayer() {
        return this.isFullScreenPlayer;
    }

    protected void setFullScreenPlayer(boolean z) {
        this.isFullScreenPlayer = z;
    }

    protected boolean getLockRotation() {
        return this.lockRotation;
    }

    protected void setLockRotation(boolean z) {
        this.lockRotation = z;
    }

    protected final TrailerCustomLayoutBinding getPlayerBinding() {
        return this.playerBinding;
    }

    protected final void setPlayerBinding(TrailerCustomLayoutBinding trailerCustomLayoutBinding) {
        this.playerBinding = trailerCustomLayoutBinding;
    }

    /* JADX INFO: renamed from: isShowing, reason: from getter */
    protected final boolean getIsShowing() {
        return this.isShowing;
    }

    protected final void setShowing(boolean z) {
        this.isShowing = z;
    }

    protected final PlayerView getPlayerHostView() {
        return this.playerHostView;
    }

    protected final void setPlayerHostView(PlayerView playerView) {
        this.playerHostView = playerView;
    }

    protected final void uiReset() {
        this.isShowing = false;
        updateUIVisibility();
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void fixLayout(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        UIHelper.fixSystemBarsPadding$default(UIHelper.INSTANCE, view, null, null, false, false, false, false, false, false, TypedValues.PositionType.TYPE_POSITION_TYPE, null);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        RecyclerView recyclerView;
        super.onCreate(savedInstanceState);
        PanelsChildGestureRegionObserver panelsChildGestureRegionObserver = PanelsChildGestureRegionObserver.Provider.get();
        FragmentResultBinding fragmentResultBinding = this.resultBinding;
        if (fragmentResultBinding == null || (recyclerView = fragmentResultBinding.resultCastItems) == null) {
            return;
        }
        panelsChildGestureRegionObserver.register(recyclerView);
    }

    public final List<Pair<ExtractorLink, String>> getCurrentTrailers() {
        return this.currentTrailers;
    }

    public final void setCurrentTrailers(List<? extends Pair<? extends ExtractorLink, String>> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.currentTrailers = list;
    }

    public final int getCurrentTrailerIndex() {
        return this.currentTrailerIndex;
    }

    public final void setCurrentTrailerIndex(int i) {
        this.currentTrailerIndex = i;
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void nextMirror() {
        this.currentTrailerIndex++;
        loadTrailer$default(this, null, 1, null);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public boolean hasNextMirror() {
        return this.currentTrailerIndex + 1 < this.currentTrailers.size();
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void playerError(Throwable exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        if (this.player.getIsPlaying()) {
            PlayerView playerView = this.playerHostView;
            if (playerView != null) {
                playerView.playerError(exception);
                return;
            }
            return;
        }
        nextMirror();
    }

    static /* synthetic */ void loadTrailer$default(ResultFragmentPhone resultFragmentPhone, Integer num, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: loadTrailer");
        }
        if ((i & 1) != 0) {
            num = null;
        }
        resultFragmentPhone.loadTrailer(num);
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0052  */
    /* JADX WARN: Code duplicated, block: B:21:0x0057 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:22:0x0059  */
    /* JADX WARN: Code duplicated, block: B:25:0x007a  */
    /* JADX WARN: Code duplicated, block: B:26:0x007c  */
    /* JADX WARN: Code duplicated, block: B:29:0x008a  */
    /* JADX WARN: Code duplicated, block: B:30:0x008d  */
    /* JADX WARN: Code duplicated, block: B:33:0x00af  */
    /* JADX WARN: Code duplicated, block: B:34:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:50:? A[RETURN, SYNTHETIC] */
    private final void loadTrailer(Integer index) {
        boolean z;
        boolean z2;
        FragmentResultBinding fragmentResultBinding;
        int i;
        float f;
        int i2;
        FragmentResultSwipeBinding binding;
        FrameLayout frameLayout;
        Pair pair = (Pair) CollectionsKt.getOrNull(this.currentTrailers, index != null ? index.intValue() : this.currentTrailerIndex);
        if (pair != null) {
            ExtractorLink extractorLink = (ExtractorLink) pair.component1();
            Context context = getContext();
            if (context != null) {
                this.player.onPause();
                this.player.loadPlayer(context, false, extractorLink, null, 0L, SetsKt.emptySet(), null, false, false);
                z = true;
            }
            if (!z || getIsFullScreenPlayer()) {
                z2 = false;
            } else {
                z2 = true;
            }
            fragmentResultBinding = this.resultBinding;
            if (fragmentResultBinding != null) {
                if (z) {
                    ResultFragment resultFragment = ResultFragment.INSTANCE;
                    ImageView backgroundPosterWatermarkBadge = fragmentResultBinding.backgroundPosterWatermarkBadge;
                    Intrinsics.checkNotNullExpressionValue(backgroundPosterWatermarkBadge, "backgroundPosterWatermarkBadge");
                    TextView resultTitle = fragmentResultBinding.resultTitle;
                    Intrinsics.checkNotNullExpressionValue(resultTitle, "resultTitle");
                    resultFragment.bindLogo(null, null, backgroundPosterWatermarkBadge, resultTitle);
                }
                FrameLayout resultSmallscreenHolder = fragmentResultBinding.resultSmallscreenHolder;
                Intrinsics.checkNotNullExpressionValue(resultSmallscreenHolder, "resultSmallscreenHolder");
                FrameLayout frameLayout2 = resultSmallscreenHolder;
                if (z2) {
                    i = 0;
                } else {
                    i = 8;
                }
                frameLayout2.setVisibility(i);
                FrameLayout frameLayout3 = fragmentResultBinding.resultPosterBackgroundHolder;
                float alpha = frameLayout3.getAlpha();
                if (z2) {
                    f = 1.0f;
                } else {
                    f = 0.0f;
                }
                AlphaAnimation alphaAnimation = new AlphaAnimation(alpha, f);
                alphaAnimation.setInterpolator(new DecelerateInterpolator());
                alphaAnimation.setDuration(200L);
                alphaAnimation.setFillAfter(true);
                frameLayout3.clearAnimation();
                frameLayout3.startAnimation(alphaAnimation);
                FrameLayout frameLayout4 = fragmentResultBinding.resultSmallscreenHolder;
                if (z) {
                    i2 = 262144;
                } else {
                    i2 = 393216;
                }
                frameLayout4.setDescendantFocusability(i2);
                binding = getBinding();
                if (binding != null || (frameLayout = binding.resultFullscreenHolder) == null) {
                }
                frameLayout.setVisibility(!z && getIsFullScreenPlayer() ? 0 : 8);
                return;
            }
        }
        z = false;
        if (z) {
            z2 = false;
        } else {
            z2 = false;
        }
        fragmentResultBinding = this.resultBinding;
        if (fragmentResultBinding != null) {
            if (z) {
                ResultFragment resultFragment2 = ResultFragment.INSTANCE;
                ImageView backgroundPosterWatermarkBadge2 = fragmentResultBinding.backgroundPosterWatermarkBadge;
                Intrinsics.checkNotNullExpressionValue(backgroundPosterWatermarkBadge2, "backgroundPosterWatermarkBadge");
                TextView resultTitle2 = fragmentResultBinding.resultTitle;
                Intrinsics.checkNotNullExpressionValue(resultTitle2, "resultTitle");
                resultFragment2.bindLogo(null, null, backgroundPosterWatermarkBadge2, resultTitle2);
            }
            FrameLayout resultSmallscreenHolder2 = fragmentResultBinding.resultSmallscreenHolder;
            Intrinsics.checkNotNullExpressionValue(resultSmallscreenHolder2, "resultSmallscreenHolder");
            FrameLayout frameLayout5 = resultSmallscreenHolder2;
            if (z2) {
                i = 0;
            } else {
                i = 8;
            }
            frameLayout5.setVisibility(i);
            FrameLayout frameLayout6 = fragmentResultBinding.resultPosterBackgroundHolder;
            float alpha2 = frameLayout6.getAlpha();
            if (z2) {
                f = 1.0f;
            } else {
                f = 0.0f;
            }
            AlphaAnimation alphaAnimation2 = new AlphaAnimation(alpha2, f);
            alphaAnimation2.setInterpolator(new DecelerateInterpolator());
            alphaAnimation2.setDuration(200L);
            alphaAnimation2.setFillAfter(true);
            frameLayout6.clearAnimation();
            frameLayout6.startAnimation(alphaAnimation2);
            FrameLayout frameLayout7 = fragmentResultBinding.resultSmallscreenHolder;
            if (z) {
                i2 = 262144;
            } else {
                i2 = 393216;
            }
            frameLayout7.setDescendantFocusability(i2);
            binding = getBinding();
            if (binding != null) {
            }
        }
    }

    private final void setTrailers(List<? extends Pair<? extends ExtractorLink, String>> trailers) {
        List<? extends Pair<? extends ExtractorLink, String>> listEmptyList;
        Context context = getContext();
        if (context != null) {
            AppContextUtils.INSTANCE.updateHasTrailers(context);
        }
        if (LoadResponse.INSTANCE.isTrailersEnabled()) {
            if (trailers == null || (listEmptyList = CollectionsKt.sortedWith(trailers, new Comparator() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$setTrailers$$inlined$sortedBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Integer.valueOf(-((ExtractorLink) ((Pair) t).getFirst()).getQuality()), Integer.valueOf(-((ExtractorLink) ((Pair) t2).getFirst()).getQuality()));
                }
            })) == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            this.currentTrailers = listEmptyList;
            loadTrailer$default(this, null, 1, null);
        }
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        NestedScrollView nestedScrollView;
        RecyclerView recyclerView;
        PanelsChildGestureRegionObserver panelsChildGestureRegionObserver = PanelsChildGestureRegionObserver.Provider.get();
        FragmentResultBinding fragmentResultBinding = this.resultBinding;
        if (fragmentResultBinding != null && (recyclerView = fragmentResultBinding.resultCastItems) != null) {
            panelsChildGestureRegionObserver.unregister(recyclerView);
        }
        panelsChildGestureRegionObserver.removeGestureRegionsUpdateListener(this.gestureRegionsListener);
        ResultFragment.INSTANCE.getUpdateUIEvent().minusAssign(new AnonymousClass2(this));
        PlayerView playerView = this.playerHostView;
        if (playerView != null) {
            playerView.release();
        }
        this.playerBinding = null;
        FragmentResultBinding fragmentResultBinding2 = this.resultBinding;
        if (fragmentResultBinding2 != null && (nestedScrollView = fragmentResultBinding2.resultScroll) != null) {
            nestedScrollView.setOnClickListener(null);
        }
        this.resultBinding = null;
        this.syncBinding = null;
        this.recommendationBinding = null;
        FragmentActivity activity = getActivity();
        if (activity != null) {
            String string = toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            BackPressedCallbackHelper.INSTANCE.detachBackPressedCallback(activity, string);
        }
        super.onDestroyView();
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$onDestroyView$2, reason: invalid class name */
    /* JADX INFO: compiled from: ResultFragmentPhone.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function1<Integer, Unit> {
        AnonymousClass2(Object obj) {
            super(1, obj, ResultFragmentPhone.class, "updateUI", "updateUI(Ljava/lang/Integer;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
            invoke2(num);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Integer num) {
            ((ResultFragmentPhone) this.receiver).updateUI(num);
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

    private final void setFocusUpAndDown(View upper, View down) {
        if (upper == null || down == null) {
            return;
        }
        upper.setNextFocusDownId(down.getId());
        down.setNextFocusUpId(upper.getId());
    }

    public final String getSelectSeason() {
        return this.selectSeason;
    }

    public final void setSelectSeason(String str) {
        this.selectSeason = str;
    }

    public final String getSelectEpisodeRange() {
        return this.selectEpisodeRange;
    }

    public final void setSelectEpisodeRange(String str) {
        this.selectEpisodeRange = str;
    }

    private final void setUrl(final String url) {
        MaterialButton materialButton;
        MaterialButton materialButton2;
        final ImageView imageView;
        ImageView imageView2;
        if (url == null) {
            FragmentResultSwipeBinding binding = getBinding();
            if (binding == null || (imageView2 = binding.resultOpenInBrowser) == null) {
                return;
            }
            imageView2.setVisibility(8);
            return;
        }
        boolean zStartsWith$default = StringsKt.startsWith$default(url, "http", false, 2, (Object) null);
        FragmentResultSwipeBinding binding2 = getBinding();
        if (binding2 != null && (imageView = binding2.resultOpenInBrowser) != null) {
            imageView.setVisibility(zStartsWith$default ? 0 : 8);
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda62
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ResultFragmentPhone.setUrl$lambda$0$0(imageView, url, view);
                }
            });
        }
        FragmentResultBinding fragmentResultBinding = this.resultBinding;
        if (fragmentResultBinding != null && (materialButton2 = fragmentResultBinding.resultReloadConnectionOpenInBrowser) != null) {
            materialButton2.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda63
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ResultFragmentPhone.setUrl$lambda$1(this.f$0, url, view);
                }
            });
        }
        FragmentResultBinding fragmentResultBinding2 = this.resultBinding;
        if (fragmentResultBinding2 == null || (materialButton = fragmentResultBinding2.resultMetaSite) == null) {
            return;
        }
        materialButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda64
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ResultFragmentPhone.setUrl$lambda$2(this.f$0, url, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setUrl$lambda$0$0(ImageView imageView, String str, View view) {
        Context context = imageView.getContext();
        if (context != null) {
            AppContextUtils.openBrowser$default(AppContextUtils.INSTANCE, context, str, false, null, 6, null);
        }
    }

    static final void setUrl$lambda$1(ResultFragmentPhone resultFragmentPhone, String str, View view) {
        Context context;
        View view2 = resultFragmentPhone.getView();
        if (view2 == null || (context = view2.getContext()) == null) {
            return;
        }
        AppContextUtils.openBrowser$default(AppContextUtils.INSTANCE, context, str, false, null, 6, null);
    }

    static final void setUrl$lambda$2(ResultFragmentPhone resultFragmentPhone, String str, View view) {
        Context context;
        View view2 = resultFragmentPhone.getView();
        if (view2 == null || (context = view2.getContext()) == null) {
            return;
        }
        AppContextUtils.openBrowser$default(AppContextUtils.INSTANCE, context, str, false, null, 6, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reloadViewModel(boolean forceReload) {
        ResultFragment.StoredData storedData;
        if ((!getViewModel().hasLoaded() || forceReload) && (storedData = ResultFragment.INSTANCE.getStoredData(this)) != null) {
            getViewModel().load(getActivity(), storedData.getUrl(), storedData.getApiName(), storedData.getShowFillers(), storedData.getDubStatus(), storedData.getStart(), (64 & 64) != 0);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$onResume$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ResultFragmentPhone.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C08741 extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
        C08741(Object obj) {
            super(1, obj, ResultFragmentPhone.class, "reloadViewModel", "reloadViewModel(Z)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            ((ResultFragmentPhone) this.receiver).reloadViewModel(z);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        MainActivity.INSTANCE.getAfterPluginsLoadedEvent().plusAssign(new C08741(this));
        FragmentActivity activity = getActivity();
        if (activity != null) {
            UIHelper.INSTANCE.setNavigationBarColorCompat(activity, R.attr.primaryBlackBackground);
        }
        Context context = getContext();
        if (context != null) {
            PlayerView playerView = this.playerHostView;
            if (playerView != null) {
                playerView.onResume(context);
            }
            PlayerView playerView2 = this.playerHostView;
            if (playerView2 != null) {
                playerView2.setupKeyEventListener();
            }
        }
        super.onResume();
        PanelsChildGestureRegionObserver.Provider.get().addGestureRegionsUpdateListener(this.gestureRegionsListener);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$onStop$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ResultFragmentPhone.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C08751 extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
        C08751(Object obj) {
            super(1, obj, ResultFragmentPhone.class, "reloadViewModel", "reloadViewModel(Z)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            ((ResultFragmentPhone) this.receiver).reloadViewModel(z);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        MainActivity.INSTANCE.getAfterPluginsLoadedEvent().minusAssign(new C08751(this));
        PlayerView playerView = this.playerHostView;
        if (playerView != null) {
            playerView.onStop();
        }
        super.onStop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateUI(Integer id) {
        getSyncModel().updateUserData();
        getViewModel().reloadEpisodes();
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void onBindingCreated(final FragmentResultSwipeBinding binding, Bundle savedInstanceState) {
        MaterialButton materialButton;
        Window window;
        View decorView;
        Intrinsics.checkNotNullParameter(binding, "binding");
        ResultFragmentPhone resultFragmentPhone = this;
        setViewModel((ResultViewModel2) new ViewModelProvider(resultFragmentPhone).get(ResultViewModel2.class));
        setSyncModel((SyncViewModel) new ViewModelProvider(resultFragmentPhone).get(SyncViewModel.class));
        ResultFragment.INSTANCE.getUpdateUIEvent().plusAssign(new C08731(this));
        this.resultBinding = binding.fragmentResult;
        this.recommendationBinding = binding.resultRecommendations;
        this.syncBinding = binding.resultSync;
        Context context = getContext();
        if (context == null) {
            return;
        }
        PlayerView playerView = new PlayerView(context, null, 2, null);
        this.playerHostView = playerView;
        playerView.setPlayer(this.player);
        PlayerView playerView2 = this.playerHostView;
        if (playerView2 != null) {
            playerView2.setHasPipModeSupport(getHasPipModeSupport());
        }
        PlayerView playerView3 = this.playerHostView;
        if (playerView3 != null) {
            playerView3.setCallbacks(this);
        }
        PlayerView playerView4 = this.playerHostView;
        if (playerView4 != null) {
            LinearLayout root = binding.getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
            playerView4.bindViews(root);
        }
        View viewFindViewById = binding.getRoot().findViewById(R.id.player_holder);
        this.playerBinding = viewFindViewById != null ? TrailerCustomLayoutBinding.bind(viewFindViewById) : null;
        PlayerView playerView5 = this.playerHostView;
        if (playerView5 != null) {
            playerView5.initialize();
        }
        final ResultFragment.StoredData storedData = ResultFragment.INSTANCE.getStoredData(this);
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
        Context context2 = getContext();
        if (context2 != null) {
            AppContextUtils.INSTANCE.updateHasTrailers(context2);
        }
        UIHelper.INSTANCE.hideKeyboard(binding.getRoot());
        if (storedData.getRestart() || !getViewModel().hasLoaded()) {
            getViewModel().load(getActivity(), storedData.getUrl(), storedData.getApiName(), storedData.getShowFillers(), storedData.getDubStatus(), storedData.getStart(), (64 & 64) != 0);
        }
        setUrl(storedData.getUrl());
        getSyncModel().addFromUrl(storedData.getUrl());
        MainAPI apiFromNameNull = APIHolder.INSTANCE.getApiFromNameNull(storedData.getApiName());
        binding.resultOverlappingPanels.registerEndPanelStateListeners(new OverlappingPanelsLayout.PanelStateListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone.onBindingCreated.3
            @Override // com.discord.panels.OverlappingPanelsLayout.PanelStateListener
            public void onPanelStateChange(PanelState panelState) {
                RecyclerView recyclerView;
                Intrinsics.checkNotNullParameter(panelState, "panelState");
                PanelsChildGestureRegionObserver panelsChildGestureRegionObserver = PanelsChildGestureRegionObserver.Provider.get();
                FragmentResultBinding resultBinding = ResultFragmentPhone.this.getResultBinding();
                if (resultBinding == null || (recyclerView = resultBinding.resultCastItems) == null) {
                    return;
                }
                panelsChildGestureRegionObserver.register(recyclerView);
            }
        });
        ImageView resultSearch = binding.resultSearch;
        Intrinsics.checkNotNullExpressionValue(resultSearch, "resultSearch");
        resultSearch.setVisibility(StringsKt.isBlank(storedData.getName()) ? 8 : 0);
        binding.resultSearch.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QuickSearchFragment.Companion.pushSearch$default(QuickSearchFragment.INSTANCE, this.f$0.getActivity(), storedData.getName(), null, 4, null);
            }
        });
        final FragmentResultBinding fragmentResultBinding = this.resultBinding;
        if (fragmentResultBinding != null) {
            fragmentResultBinding.resultReloadConnectionerror.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda14
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ResultFragmentPhone.onBindingCreated$lambda$2$0(this.f$0, storedData, view);
                }
            });
            LinearListLayoutKt.setLinearListLayout$default(fragmentResultBinding.resultCastItems, true, -2, -2, 0, 0, 24, null);
            RecyclerView resultCastItems = fragmentResultBinding.resultCastItems;
            Intrinsics.checkNotNullExpressionValue(resultCastItems, "resultCastItems");
            BaseAdapterKt.setRecycledViewPool(resultCastItems, ActorAdaptor.INSTANCE.getSharedPool());
            fragmentResultBinding.resultCastItems.setAdapter(new ActorAdaptor(null, null, 3, null));
            RecyclerView resultEpisodes = fragmentResultBinding.resultEpisodes;
            Intrinsics.checkNotNullExpressionValue(resultEpisodes, "resultEpisodes");
            BaseAdapterKt.setRecycledViewPool(resultEpisodes, EpisodeAdapter.INSTANCE.getSharedPool());
            fragmentResultBinding.resultEpisodes.setAdapter(new EpisodeAdapter(apiFromNameNull != null && apiFromNameNull.getHasDownloadSupport(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda26
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ResultFragmentPhone.onBindingCreated$lambda$2$1(this.f$0, (EpisodeClickEvent) obj);
                }
            }, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda38
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ResultFragmentPhone.onBindingCreated$lambda$2$2((DownloadClickEvent) obj);
                }
            }));
            ResultFragmentPhone resultFragmentPhone2 = this;
            LifecycleKt.observeNullable(resultFragmentPhone2, getViewModel().getSelectedSorting(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda42
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ResultFragmentPhone.onBindingCreated$lambda$2$3(fragmentResultBinding, (UiText) obj);
                }
            });
            LifecycleKt.observe(resultFragmentPhone2, getViewModel().getSortSelections(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda43
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ResultFragmentPhone.onBindingCreated$lambda$2$4(this.f$0, (List) obj);
                }
            });
            fragmentResultBinding.resultScroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda45
                @Override // androidx.core.widget.NestedScrollView.OnScrollChangeListener
                public final void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
                    ResultFragmentPhone.onBindingCreated$lambda$2$5(binding, this, nestedScrollView, i, i2, i3, i4);
                }
            });
        }
        binding.resultOverlappingPanels.setStartPanelLockState(OverlappingPanelsLayout.LockState.CLOSE);
        binding.resultOverlappingPanels.setEndPanelLockState(OverlappingPanelsLayout.LockState.CLOSE);
        binding.resultBack.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda46
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ResultFragmentPhone.onBindingCreated$lambda$3$0(this.f$0, view);
            }
        });
        FragmentActivity activity3 = getActivity();
        if (activity3 != null) {
            String string = toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            BackPressedCallbackHelper.INSTANCE.attachBackPressedCallback(activity3, string, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda47
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ResultFragmentPhone.onBindingCreated$lambda$3$1(binding, (BackPressedCallbackHelper.CallbackHelper) obj);
                }
            });
        }
        binding.resultMiniSync.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda48
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ResultFragmentPhone.onBindingCreated$lambda$3$2(binding, view);
            }
        });
        binding.resultSubscribe.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ResultFragmentPhone.onBindingCreated$lambda$3$3(this.f$0, view);
            }
        });
        binding.resultFavorite.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ResultFragmentPhone.onBindingCreated$lambda$3$4(this.f$0, view);
            }
        });
        final MediaRouteButton mediaRouteButton = binding.mediaRouteButton;
        boolean z = apiFromNameNull != null && apiFromNameNull.getHasChromecastSupport();
        mediaRouteButton.setAlpha(z ? 1.0f : 0.3f);
        if (!z) {
            mediaRouteButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ResultFragmentPhone.onBindingCreated$lambda$3$5$0(view);
                }
            });
        }
        FragmentActivity activity4 = getActivity();
        if (activity4 != null && AppContextUtils.INSTANCE.isCastApiAvailable(activity4)) {
            try {
                CastButtonFactory.setUpMediaRouteButton(activity4, mediaRouteButton);
                Intrinsics.checkNotNull(CastContext.getSharedInstance(activity4.getApplicationContext(), new Executor() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda6
                    @Override // java.util.concurrent.Executor
                    public final void execute(Runnable runnable) {
                        runnable.run();
                    }
                }).addOnCompleteListener(new OnCompleteListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda7
                    @Override // com.google.android.gms.tasks.OnCompleteListener
                    public final void onComplete(Task task) {
                        ResultFragmentPhone.onBindingCreated$lambda$3$5$1$1(mediaRouteButton, task);
                    }
                }));
            } catch (Exception e) {
                ArchComponentExtKt.logError(e);
            }
        }
        TrailerCustomLayoutBinding trailerCustomLayoutBinding = this.playerBinding;
        if (trailerCustomLayoutBinding != null) {
            trailerCustomLayoutBinding.playerOpenSource.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ResultFragmentPhone.onBindingCreated$lambda$4$0(this.f$0, view);
                }
            });
        }
        ResultRecommendationsBinding resultRecommendationsBinding = this.recommendationBinding;
        if (resultRecommendationsBinding != null) {
            AutofitRecyclerView autofitRecyclerView = resultRecommendationsBinding.resultRecommendationsList;
            autofitRecyclerView.setSpanCount(3);
            Intrinsics.checkNotNull(autofitRecyclerView);
            BaseAdapterKt.setRecycledViewPool(autofitRecyclerView, SearchAdapter.INSTANCE.getSharedPool());
            autofitRecyclerView.setAdapter(new SearchAdapter(autofitRecyclerView, false, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ResultFragmentPhone.onBindingCreated$lambda$5$0$0((SearchClickCallback) obj);
                }
            }, 2, null));
        }
        ResultFragmentPhone resultFragmentPhone3 = this;
        LifecycleKt.observeNullable(resultFragmentPhone3, getViewModel().getResumeWatching(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentPhone.onBindingCreated$lambda$6(this.f$0, storedData, (ResumeWatchingStatus) obj);
            }
        });
        LifecycleKt.observeNullable(resultFragmentPhone3, getViewModel().getSubscribeStatus(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentPhone.onBindingCreated$lambda$7(binding, (Boolean) obj);
            }
        });
        LifecycleKt.observeNullable(resultFragmentPhone3, getViewModel().getFavoriteStatus(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentPhone.onBindingCreated$lambda$8(binding, (Boolean) obj);
            }
        });
        LifecycleKt.observeNullable(resultFragmentPhone3, getViewModel().getEpisodes(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentPhone.onBindingCreated$lambda$9(this.f$0, (Resource) obj);
            }
        });
        LifecycleKt.observeNullable(resultFragmentPhone3, getViewModel().m8575getMovie(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentPhone.onBindingCreated$lambda$10(this.f$0, (Resource) obj);
            }
        });
        LifecycleKt.observe(resultFragmentPhone3, getViewModel().getPage(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentPhone.onBindingCreated$lambda$11(this.f$0, binding, storedData, (Resource) obj);
            }
        });
        LifecycleKt.observeNullable(resultFragmentPhone3, getViewModel().getEpisodesCountText(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentPhone.onBindingCreated$lambda$12(this.f$0, (UiText) obj);
            }
        });
        LifecycleKt.observeNullable(resultFragmentPhone3, getViewModel().getSelectPopup(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda19
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentPhone.onBindingCreated$lambda$13(this.f$0, (SelectPopup) obj);
            }
        });
        LifecycleKt.observe(resultFragmentPhone3, getViewModel().getTrailers(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentPhone.onBindingCreated$lambda$14(this.f$0, (List) obj);
            }
        });
        LifecycleKt.observe(resultFragmentPhone3, getSyncModel().getSynced(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda21
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentPhone.onBindingCreated$lambda$15(this.f$0, binding, (List) obj);
            }
        });
        final Ref.IntRef intRef = new Ref.IntRef();
        LifecycleKt.observe(resultFragmentPhone3, getSyncModel().getMetadata(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda23
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentPhone.onBindingCreated$lambda$17(this.f$0, intRef, (Resource) obj);
            }
        });
        LifecycleKt.observe(resultFragmentPhone3, getSyncModel().getUserData(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda24
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentPhone.onBindingCreated$lambda$18(this.f$0, binding, intRef, (Resource) obj);
            }
        });
        LifecycleKt.observe(resultFragmentPhone3, getViewModel().getRecommendations(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda25
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentPhone.onBindingCreated$lambda$19(this.f$0, (List) obj);
            }
        });
        Context context3 = getContext();
        if (context3 != null) {
            ArrayAdapter arrayAdapter = new ArrayAdapter(context3, R.layout.sort_bottom_single_choice);
            List listListOf = CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(R.string.none), Integer.valueOf(R.string.type_watching), Integer.valueOf(R.string.type_completed), Integer.valueOf(R.string.type_on_hold), Integer.valueOf(R.string.type_dropped), Integer.valueOf(R.string.type_plan_to_watch), Integer.valueOf(R.string.type_re_watching)});
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listListOf, 10));
            Iterator it = listListOf.iterator();
            while (it.hasNext()) {
                arrayList.add(context3.getString(((Number) it.next()).intValue()));
            }
            arrayAdapter.addAll(arrayList);
            ResultSyncBinding resultSyncBinding = this.syncBinding;
            if (resultSyncBinding != null) {
                resultSyncBinding.resultSyncCheck.setChoiceMode(1);
                resultSyncBinding.resultSyncCheck.setAdapter((ListAdapter) arrayAdapter);
                UIHelper.INSTANCE.setListViewHeightBasedOnItems(resultSyncBinding.resultSyncCheck);
                resultSyncBinding.resultSyncCheck.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda27
                    @Override // android.widget.AdapterView.OnItemClickListener
                    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                        ResultFragmentPhone.onBindingCreated$lambda$20$1$0(this.f$0, adapterView, view, i, j);
                    }
                });
                resultSyncBinding.resultSyncRating.addOnChangeListener(new Slider.OnChangeListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda28
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.google.android.material.slider.BaseOnChangeListener
                    public final void onValueChange(Slider slider, float f, boolean z2) {
                        ResultFragmentPhone.onBindingCreated$lambda$20$1$1(this.f$0, slider, f, z2);
                    }
                });
                resultSyncBinding.resultSyncAddEpisode.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda29
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ResultFragmentPhone.onBindingCreated$lambda$20$1$2(this.f$0, view);
                    }
                });
                resultSyncBinding.resultSyncSubEpisode.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda30
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ResultFragmentPhone.onBindingCreated$lambda$20$1$3(this.f$0, view);
                    }
                });
                EditText resultSyncCurrentEpisodes = resultSyncBinding.resultSyncCurrentEpisodes;
                Intrinsics.checkNotNullExpressionValue(resultSyncCurrentEpisodes, "resultSyncCurrentEpisodes");
                resultSyncCurrentEpisodes.addTextChangedListener(new TextWatcher() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$onBindingCreated$lambda$20$1$$inlined$doOnTextChanged$1
                    @Override // android.text.TextWatcher
                    public void afterTextChanged(Editable s) {
                    }

                    @Override // android.text.TextWatcher
                    public void beforeTextChanged(CharSequence text, int start, int count, int after) {
                    }

                    @Override // android.text.TextWatcher
                    public void onTextChanged(CharSequence text, int start, int before, int count) {
                        String string2;
                        Integer intOrNull;
                        if (count == before || text == null || (string2 = text.toString()) == null || (intOrNull = StringsKt.toIntOrNull(string2)) == null) {
                            return;
                        }
                        this.this$0.getSyncModel().setEpisodes(intOrNull.intValue());
                    }
                });
            }
        }
        ResultSyncBinding resultSyncBinding2 = this.syncBinding;
        if (resultSyncBinding2 != null && (materialButton = resultSyncBinding2.resultSyncSetScore) != null) {
            materialButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda31
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.getSyncModel().publishUserData();
                }
            });
        }
        LifecycleKt.observe(resultFragmentPhone3, getViewModel().getWatchStatus(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda32
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentPhone.onBindingCreated$lambda$22(binding, this, (WatchType) obj);
            }
        });
        LifecycleKt.observeNullable(resultFragmentPhone3, getViewModel().getLoadedLinks(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda34
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentPhone.onBindingCreated$lambda$23(this.f$0, (LinkProgress) obj);
            }
        });
        LifecycleKt.observeNullable(resultFragmentPhone3, getViewModel().getSelectedSeason(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda35
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentPhone.onBindingCreated$lambda$24(this.f$0, (UiText) obj);
            }
        });
        LifecycleKt.observeNullable(resultFragmentPhone3, getViewModel().getSelectedDubStatus(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda36
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentPhone.onBindingCreated$lambda$25(this.f$0, (UiText) obj);
            }
        });
        LifecycleKt.observeNullable(resultFragmentPhone3, getViewModel().getSelectedRange(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda37
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentPhone.onBindingCreated$lambda$26(this.f$0, (UiText) obj);
            }
        });
        LifecycleKt.observe(resultFragmentPhone3, getViewModel().getDubSubSelections(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda39
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentPhone.onBindingCreated$lambda$27(this.f$0, (List) obj);
            }
        });
        LifecycleKt.observe(resultFragmentPhone3, getViewModel().getRangeSelections(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda40
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentPhone.onBindingCreated$lambda$28(this.f$0, (List) obj);
            }
        });
        LifecycleKt.observe(resultFragmentPhone3, getViewModel().getSeasonSelections(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda41
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentPhone.onBindingCreated$lambda$29(this.f$0, (List) obj);
            }
        });
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$onBindingCreated$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ResultFragmentPhone.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C08731 extends FunctionReferenceImpl implements Function1<Integer, Unit> {
        C08731(Object obj) {
            super(1, obj, ResultFragmentPhone.class, "updateUI", "updateUI(Ljava/lang/Integer;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
            invoke2(num);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Integer num) {
            ((ResultFragmentPhone) this.receiver).updateUI(num);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$2$0(ResultFragmentPhone resultFragmentPhone, ResultFragment.StoredData storedData, View view) {
        resultFragmentPhone.getViewModel().load(resultFragmentPhone.getActivity(), storedData.getUrl(), storedData.getApiName(), storedData.getShowFillers(), storedData.getDubStatus(), storedData.getStart(), (64 & 64) != 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$2$1(ResultFragmentPhone resultFragmentPhone, EpisodeClickEvent episodeClick) {
        Intrinsics.checkNotNullParameter(episodeClick, "episodeClick");
        int action = episodeClick.getAction();
        if (action == 6 || action == 7) {
            resultFragmentPhone.requirePathForActions(CollectionsKt.listOf(TuplesKt.to(Integer.valueOf(episodeClick.getAction()), episodeClick.getData())));
        } else {
            resultFragmentPhone.getViewModel().handleAction(episodeClick);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$2$2(DownloadClickEvent downloadClickEvent) {
        Intrinsics.checkNotNullParameter(downloadClickEvent, "downloadClickEvent");
        DownloadButtonSetup.INSTANCE.handleDownloadClick(downloadClickEvent);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$2$3(FragmentResultBinding fragmentResultBinding, UiText uiText) {
        TextUtilKt.setText(fragmentResultBinding.resultSortButton, uiText);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$2$4(final ResultFragmentPhone resultFragmentPhone, final List sort) {
        MaterialButton materialButton;
        Intrinsics.checkNotNullParameter(sort, "sort");
        FragmentResultBinding fragmentResultBinding = resultFragmentPhone.resultBinding;
        if (fragmentResultBinding != null && (materialButton = fragmentResultBinding.resultSortButton) != null) {
            materialButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda82
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ResultFragmentPhone.onBindingCreated$lambda$2$4$0(sort, resultFragmentPhone, view);
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$2$4$0(List list, final ResultFragmentPhone resultFragmentPhone, View view) {
        Context context;
        if (view == null || (context = view.getContext()) == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            UiText uiText = (UiText) pair.component1();
            EpisodeSortType episodeSortType = (EpisodeSortType) pair.component2();
            String strAsStringNull = uiText.asStringNull(context);
            Pair pair2 = strAsStringNull == null ? null : TuplesKt.to(episodeSortType, strAsStringNull);
            if (pair2 != null) {
                arrayList.add(pair2);
            }
        }
        final ArrayList arrayList2 = arrayList;
        FragmentActivity activity = resultFragmentPhone.getActivity();
        if (activity != null) {
            SingleSelectionHelper singleSelectionHelper = SingleSelectionHelper.INSTANCE;
            FragmentActivity fragmentActivity = activity;
            ArrayList arrayList3 = arrayList2;
            ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
            Iterator it2 = arrayList3.iterator();
            while (it2.hasNext()) {
                arrayList4.add((String) ((Pair) it2.next()).getSecond());
            }
            ArrayList arrayList5 = arrayList4;
            Integer value = resultFragmentPhone.getViewModel().getSelectedSortingIndex().getValue();
            int iIntValue = value != null ? value.intValue() : -1;
            String string = context.getString(R.string.sort_by);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            singleSelectionHelper.showDialog(fragmentActivity, arrayList5, iIntValue, string, false, new Function0() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda80
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            }, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda81
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ResultFragmentPhone.onBindingCreated$lambda$2$4$0$0$3(this.f$0, arrayList2, ((Integer) obj).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$2$4$0$0$3(ResultFragmentPhone resultFragmentPhone, List list, int i) {
        resultFragmentPhone.getViewModel().setSort((EpisodeSortType) ((Pair) list.get(i)).getFirst());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$2$5(FragmentResultSwipeBinding fragmentResultSwipeBinding, ResultFragmentPhone resultFragmentPhone, NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
        FragmentTrailerBinding fragmentTrailerBinding;
        ConstraintLayout constraintLayout;
        Intrinsics.checkNotNullParameter(nestedScrollView, "<unused var>");
        int i5 = i2 - i4;
        if (i5 > 0) {
            fragmentResultSwipeBinding.resultBookmarkFab.shrink();
        } else if (i5 < -5) {
            fragmentResultSwipeBinding.resultBookmarkFab.extend();
        }
        if (resultFragmentPhone.getIsFullScreenPlayer() || !resultFragmentPhone.player.getIsPlaying()) {
            return;
        }
        FragmentResultBinding fragmentResultBinding = resultFragmentPhone.resultBinding;
        if (i2 > ((fragmentResultBinding == null || (fragmentTrailerBinding = fragmentResultBinding.fragmentTrailer) == null || (constraintLayout = fragmentTrailerBinding.playerBackground) == null) ? i2 : constraintLayout.getHeight())) {
            IPlayer.CC.handleEvent$default(resultFragmentPhone.player, CSPlayerEvent.Pause, null, 2, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$3$0(ResultFragmentPhone resultFragmentPhone, View view) {
        FragmentActivity activity = resultFragmentPhone.getActivity();
        if (activity != null) {
            UIHelper.popCurrentPage$default(UIHelper.INSTANCE, activity, null, 1, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$3$1(FragmentResultSwipeBinding fragmentResultSwipeBinding, BackPressedCallbackHelper.CallbackHelper attachBackPressedCallback) {
        Intrinsics.checkNotNullParameter(attachBackPressedCallback, "$this$attachBackPressedCallback");
        if (fragmentResultSwipeBinding.resultOverlappingPanels.getSelectedPanel().ordinal() == 1) {
            attachBackPressedCallback.runDefault();
        } else {
            fragmentResultSwipeBinding.resultOverlappingPanels.closePanels();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$3$2(FragmentResultSwipeBinding fragmentResultSwipeBinding, View view) {
        if (fragmentResultSwipeBinding.resultOverlappingPanels.getSelectedPanel().ordinal() == 1) {
            fragmentResultSwipeBinding.resultOverlappingPanels.openStartPanel();
        } else {
            fragmentResultSwipeBinding.resultOverlappingPanels.closePanels();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$3$3(final ResultFragmentPhone resultFragmentPhone, View view) {
        resultFragmentPhone.getViewModel().toggleSubscriptionStatus(resultFragmentPhone.getContext(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda76
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentPhone.onBindingCreated$lambda$3$3$0(this.f$0, (Boolean) obj);
            }
        });
        Context context = resultFragmentPhone.getContext();
        if (context != null) {
            BatteryOptimizationChecker.INSTANCE.openBatteryOptimizationSettings(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$3$3$0(ResultFragmentPhone resultFragmentPhone, Boolean bool) {
        int i;
        String strAsStringNull;
        ResultData resultData;
        if (bool == null) {
            return Unit.INSTANCE;
        }
        if (bool.booleanValue()) {
            SubscriptionWorkManager.INSTANCE.enqueuePeriodicWork(resultFragmentPhone.getContext());
            i = R.string.subscription_new;
        } else {
            i = R.string.subscription_deleted;
        }
        Resource<ResultData> value = resultFragmentPhone.getViewModel().getPage().getValue();
        Resource.Success success = value instanceof Resource.Success ? (Resource.Success) value : null;
        if ((success == null || (resultData = (ResultData) success.getValue()) == null || (strAsStringNull = resultData.getTitle()) == null) && (strAsStringNull = TextUtilKt.txt(R.string.no_data, new Object[0]).asStringNull(resultFragmentPhone.getContext())) == null) {
            strAsStringNull = "";
        }
        CommonActivity.INSTANCE.showToast(TextUtilKt.txt(i, strAsStringNull), (Integer) 0);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$3$4(final ResultFragmentPhone resultFragmentPhone, View view) {
        resultFragmentPhone.getViewModel().toggleFavoriteStatus(resultFragmentPhone.getContext(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda33
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentPhone.onBindingCreated$lambda$3$4$0(this.f$0, (Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$3$4$0(ResultFragmentPhone resultFragmentPhone, Boolean bool) {
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
        Resource<ResultData> value = resultFragmentPhone.getViewModel().getPage().getValue();
        Resource.Success success = value instanceof Resource.Success ? (Resource.Success) value : null;
        if ((success == null || (resultData = (ResultData) success.getValue()) == null || (strAsStringNull = resultData.getTitle()) == null) && (strAsStringNull = TextUtilKt.txt(R.string.no_data, new Object[0]).asStringNull(resultFragmentPhone.getContext())) == null) {
            strAsStringNull = "";
        }
        CommonActivity.INSTANCE.showToast(TextUtilKt.txt(i, strAsStringNull), (Integer) 0);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$3$5$0(View view) {
        CommonActivity.INSTANCE.showToast(R.string.no_chromecast_support_toast, (Integer) 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$3$5$1$1(MediaRouteButton mediaRouteButton, Task it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Intrinsics.checkNotNull(mediaRouteButton);
        mediaRouteButton.setVisibility(!it.isSuccessful() ? 8 : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$4$0(ResultFragmentPhone resultFragmentPhone, View view) {
        Pair pair = (Pair) CollectionsKt.getOrNull(resultFragmentPhone.currentTrailers, resultFragmentPhone.currentTrailerIndex);
        if (pair != null) {
            String str = (String) pair.component2();
            Context context = resultFragmentPhone.getContext();
            if (context != null) {
                AppContextUtils.openBrowser$default(AppContextUtils.INSTANCE, context, str, false, null, 6, null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$5$0$0(SearchClickCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        SearchHelper.INSTANCE.handleSearchClickCallback(callback);
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$6(final ResultFragmentPhone resultFragmentPhone, final ResultFragment.StoredData storedData, final ResumeWatchingStatus resumeWatchingStatus) {
        FragmentResultBinding fragmentResultBinding = resultFragmentPhone.resultBinding;
        if (fragmentResultBinding != null) {
            if (resumeWatchingStatus == null) {
                LinearLayout resultResumeParent = fragmentResultBinding.resultResumeParent;
                Intrinsics.checkNotNullExpressionValue(resultResumeParent, "resultResumeParent");
                resultResumeParent.setVisibility(8);
                LinearLayout resultPlayParent = fragmentResultBinding.resultPlayParent;
                Intrinsics.checkNotNullExpressionValue(resultPlayParent, "resultPlayParent");
                resultPlayParent.setVisibility(0);
                FrameLayout resultResumeProgressHolder = fragmentResultBinding.resultResumeProgressHolder;
                Intrinsics.checkNotNullExpressionValue(resultResumeProgressHolder, "resultResumeProgressHolder");
                resultResumeProgressHolder.setVisibility(8);
                return Unit.INSTANCE;
            }
            LinearLayout resultResumeParent2 = fragmentResultBinding.resultResumeParent;
            Intrinsics.checkNotNullExpressionValue(resultResumeParent2, "resultResumeParent");
            resultResumeParent2.setVisibility(0);
            ResumeProgress progress = resumeWatchingStatus.getProgress();
            CharSequence nameFull = null;
            if (progress != null) {
                MaterialButton resultNextSeriesButton = fragmentResultBinding.resultNextSeriesButton;
                Intrinsics.checkNotNullExpressionValue(resultNextSeriesButton, "resultNextSeriesButton");
                resultNextSeriesButton.setVisibility(8);
                TextView textView = fragmentResultBinding.resultResumeSeriesTitle;
                Intrinsics.checkNotNull(textView);
                textView.setVisibility(!resumeWatchingStatus.isMovie() ? 0 : 8);
                if (!resumeWatchingStatus.isMovie()) {
                    Context context = textView.getContext();
                    nameFull = context != null ? AppContextUtils.INSTANCE.getNameFull(context, resumeWatchingStatus.getResult().getName(), Integer.valueOf(resumeWatchingStatus.getResult().getEpisode()), resumeWatchingStatus.getResult().getSeason()) : null;
                }
                textView.setText(nameFull);
                if (resumeWatchingStatus.isMovie()) {
                    LinearLayout resultPlayParent2 = fragmentResultBinding.resultPlayParent;
                    Intrinsics.checkNotNullExpressionValue(resultPlayParent2, "resultPlayParent");
                    resultPlayParent2.setVisibility(8);
                    TextView resultResumeSeriesProgressText = fragmentResultBinding.resultResumeSeriesProgressText;
                    Intrinsics.checkNotNullExpressionValue(resultResumeSeriesProgressText, "resultResumeSeriesProgressText");
                    resultResumeSeriesProgressText.setVisibility(0);
                    TextUtilKt.setText(fragmentResultBinding.resultResumeSeriesProgressText, progress.getProgressLeft());
                }
                CircularProgressIndicator circularProgressIndicator = fragmentResultBinding.resultResumeSeriesProgress;
                Intrinsics.checkNotNull(circularProgressIndicator);
                circularProgressIndicator.setVisibility(0);
                circularProgressIndicator.setMax(progress.getMaxProgress());
                circularProgressIndicator.setProgress(progress.getProgress());
                FrameLayout resultResumeProgressHolder2 = fragmentResultBinding.resultResumeProgressHolder;
                Intrinsics.checkNotNullExpressionValue(resultResumeProgressHolder2, "resultResumeProgressHolder");
                resultResumeProgressHolder2.setVisibility(0);
            } else {
                FrameLayout resultResumeProgressHolder3 = fragmentResultBinding.resultResumeProgressHolder;
                Intrinsics.checkNotNullExpressionValue(resultResumeProgressHolder3, "resultResumeProgressHolder");
                resultResumeProgressHolder3.setVisibility(8);
                if (!resumeWatchingStatus.isMovie()) {
                    MaterialButton resultNextSeriesButton2 = fragmentResultBinding.resultNextSeriesButton;
                    Intrinsics.checkNotNullExpressionValue(resultNextSeriesButton2, "resultNextSeriesButton");
                    resultNextSeriesButton2.setVisibility(0);
                    MaterialButton materialButton = fragmentResultBinding.resultNextSeriesButton;
                    Context context2 = resultFragmentPhone.getContext();
                    materialButton.setText(context2 != null ? AppContextUtils.INSTANCE.getNameFull(context2, resumeWatchingStatus.getResult().getName(), Integer.valueOf(resumeWatchingStatus.getResult().getEpisode()), resumeWatchingStatus.getResult().getSeason()) : null);
                }
                CircularProgressIndicator resultResumeSeriesProgress = fragmentResultBinding.resultResumeSeriesProgress;
                Intrinsics.checkNotNullExpressionValue(resultResumeSeriesProgress, "resultResumeSeriesProgress");
                resultResumeSeriesProgress.setVisibility(8);
                TextView resultResumeSeriesTitle = fragmentResultBinding.resultResumeSeriesTitle;
                Intrinsics.checkNotNullExpressionValue(resultResumeSeriesTitle, "resultResumeSeriesTitle");
                resultResumeSeriesTitle.setVisibility(8);
                TextView resultResumeSeriesProgressText2 = fragmentResultBinding.resultResumeSeriesProgressText;
                Intrinsics.checkNotNullExpressionValue(resultResumeSeriesProgressText2, "resultResumeSeriesProgressText");
                resultResumeSeriesProgressText2.setVisibility(8);
            }
            fragmentResultBinding.resultResumeSeriesButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda78
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.resumeAction(storedData, resumeWatchingStatus);
                }
            });
            fragmentResultBinding.resultNextSeriesButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda79
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.resumeAction(storedData, resumeWatchingStatus);
                }
            });
        }
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$7(FragmentResultSwipeBinding fragmentResultSwipeBinding, Boolean bool) {
        int i;
        ImageView resultSubscribe = fragmentResultSwipeBinding.resultSubscribe;
        Intrinsics.checkNotNullExpressionValue(resultSubscribe, "resultSubscribe");
        resultSubscribe.setVisibility(bool != null ? 0 : 8);
        if (bool == null) {
            return Unit.INSTANCE;
        }
        if (bool.booleanValue()) {
            i = R.drawable.ic_baseline_notifications_active_24;
        } else {
            i = R.drawable.baseline_notifications_none_24;
        }
        fragmentResultSwipeBinding.resultSubscribe.setImageResource(i);
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$8(FragmentResultSwipeBinding fragmentResultSwipeBinding, Boolean bool) {
        int i;
        ImageView resultFavorite = fragmentResultSwipeBinding.resultFavorite;
        Intrinsics.checkNotNullExpressionValue(resultFavorite, "resultFavorite");
        resultFavorite.setVisibility(bool != null ? 0 : 8);
        if (bool == null) {
            return Unit.INSTANCE;
        }
        if (bool.booleanValue()) {
            i = R.drawable.ic_baseline_favorite_24;
        } else {
            i = R.drawable.ic_baseline_favorite_border_24;
        }
        fragmentResultSwipeBinding.resultFavorite.setImageResource(i);
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$9(final ResultFragmentPhone resultFragmentPhone, final Resource resource) {
        final FragmentResultBinding fragmentResultBinding = resultFragmentPhone.resultBinding;
        if (fragmentResultBinding != null) {
            ShimmerFrameLayout resultEpisodeLoading = fragmentResultBinding.resultEpisodeLoading;
            Intrinsics.checkNotNullExpressionValue(resultEpisodeLoading, "resultEpisodeLoading");
            resultEpisodeLoading.setVisibility(resource instanceof Resource.Loading ? 0 : 8);
            RecyclerView resultEpisodes = fragmentResultBinding.resultEpisodes;
            Intrinsics.checkNotNullExpressionValue(resultEpisodes, "resultEpisodes");
            boolean z = resource instanceof Resource.Success;
            resultEpisodes.setVisibility(z ? 0 : 8);
            ImageView resultBatchDownloadButton = fragmentResultBinding.resultBatchDownloadButton;
            Intrinsics.checkNotNullExpressionValue(resultBatchDownloadButton, "resultBatchDownloadButton");
            resultBatchDownloadButton.setVisibility(z && !((Collection) ((Resource.Success) resource).getValue()).isEmpty() ? 0 : 8);
            if (z) {
                RecyclerView.Adapter adapter = fragmentResultBinding.resultEpisodes.getAdapter();
                EpisodeAdapter episodeAdapter = adapter instanceof EpisodeAdapter ? (EpisodeAdapter) adapter : null;
                if (episodeAdapter != null) {
                    BaseAdapter.submitList$default(episodeAdapter, (Collection) ((Resource.Success) resource).getValue(), null, 2, null);
                }
                fragmentResultBinding.resultBatchDownloadButton.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda44
                    @Override // android.view.View.OnLongClickListener
                    public final boolean onLongClick(View view) {
                        return ResultFragmentPhone.onBindingCreated$lambda$9$0$0(fragmentResultBinding, resultFragmentPhone, view);
                    }
                });
                fragmentResultBinding.resultBatchDownloadButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda55
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ResultFragmentPhone.onBindingCreated$lambda$9$0$1(resource, resultFragmentPhone, view);
                    }
                });
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindingCreated$lambda$9$0$0(FragmentResultBinding fragmentResultBinding, ResultFragmentPhone resultFragmentPhone, View view) {
        Coroutines.INSTANCE.ioSafe(fragmentResultBinding, new ResultFragmentPhone$onBindingCreated$12$1$1$1(resultFragmentPhone, null));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$9$0$1(final Resource resource, final ResultFragmentPhone resultFragmentPhone, View view) {
        String strAsString;
        Resource.Success success = (Resource.Success) resource;
        ResultEpisode resultEpisode = (ResultEpisode) CollectionsKt.firstOrNull((List) success.getValue());
        if (resultEpisode != null) {
            int episode = resultEpisode.getEpisode();
            ResultEpisode resultEpisode2 = (ResultEpisode) CollectionsKt.lastOrNull((List) success.getValue());
            if (resultEpisode2 != null) {
                int episode2 = resultEpisode2.getEpisode();
                if (episode == episode2) {
                    strAsString = String.valueOf(episode);
                } else {
                    UiText uiTextTxt = TextUtilKt.txt(R.string.episodes_range, Integer.valueOf(episode), Integer.valueOf(episode2));
                    Context context = view.getContext();
                    Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
                    strAsString = uiTextTxt.asString(context);
                }
                UiText uiTextTxt2 = TextUtilKt.txt(R.string.download_episode_range, strAsString);
                Context context2 = view.getContext();
                Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                new AlertDialog.Builder(view.getContext(), R.style.AlertDialogCustom).setTitle(R.string.download_all).setMessage(uiTextTxt2.asString(context2)).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda49
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        ResultFragmentPhone.onBindingCreated$lambda$9$0$1$0(this.f$0, resource, dialogInterface, i);
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda50
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        ResultFragmentPhone.onBindingCreated$lambda$9$0$1$1(dialogInterface, i);
                    }
                }).show();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$9$0$1$0(ResultFragmentPhone resultFragmentPhone, Resource resource, DialogInterface dialogInterface, int i) {
        Iterable iterable = (Iterable) ((Resource.Success) resource).getValue();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(TuplesKt.to(6, (ResultEpisode) it.next()));
        }
        resultFragmentPhone.requirePathForActions(arrayList);
    }

    /* JADX WARN: Code duplicated, block: B:17:0x003d  */
    static final Unit onBindingCreated$lambda$10(final ResultFragmentPhone resultFragmentPhone, Resource resource) {
        boolean z;
        Pair pair;
        APIRepository currentRepo;
        MainAPI api;
        FragmentResultBinding fragmentResultBinding = resultFragmentPhone.resultBinding;
        if (fragmentResultBinding != null) {
            MaterialButton resultPlayMovie = fragmentResultBinding.resultPlayMovie;
            Intrinsics.checkNotNullExpressionValue(resultPlayMovie, "resultPlayMovie");
            boolean z2 = resource instanceof Resource.Success;
            resultPlayMovie.setVisibility(z2 ? 0 : 8);
            DownloadButton downloadButton = fragmentResultBinding.downloadButton;
            Intrinsics.checkNotNullExpressionValue(downloadButton, "downloadButton");
            DownloadButton downloadButton2 = downloadButton;
            if (z2 && (currentRepo = resultFragmentPhone.getViewModel().getCurrentRepo()) != null && (api = currentRepo.getApi()) != null) {
                z = api.getHasDownloadSupport();
            }
            downloadButton2.setVisibility(z ? 0 : 8);
            Resource.Success success = z2 ? (Resource.Success) resource : null;
            if (success != null && (pair = (Pair) success.getValue()) != null) {
                UiText uiText = (UiText) pair.component1();
                final ResultEpisode resultEpisode = (ResultEpisode) pair.component2();
                TextUtilKt.setText(fragmentResultBinding.resultPlayMovie, uiText);
                fragmentResultBinding.resultPlayMovie.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda56
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ResultFragmentPhone.onBindingCreated$lambda$10$0$0$0(this.f$0, resultEpisode, view);
                    }
                });
                fragmentResultBinding.resultPlayMovie.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda57
                    @Override // android.view.View.OnLongClickListener
                    public final boolean onLongClick(View view) {
                        return ResultFragmentPhone.onBindingCreated$lambda$10$0$0$1(this.f$0, resultEpisode, view);
                    }
                });
                fragmentResultBinding.resultResumeSeriesButton.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda58
                    @Override // android.view.View.OnLongClickListener
                    public final boolean onLongClick(View view) {
                        return ResultFragmentPhone.onBindingCreated$lambda$10$0$0$2(this.f$0, resultEpisode, view);
                    }
                });
                fragmentResultBinding.downloadButton.setStatus(VideoDownloadManager.INSTANCE.getDownloadStatus().get(Integer.valueOf(resultEpisode.getId())));
                fragmentResultBinding.downloadButton.setDefaultClickListener(new DownloadObjects.DownloadEpisodeCached(resultEpisode.getName(), resultEpisode.getPoster(), 0, null, resultEpisode.getId(), resultEpisode.getScore(), resultEpisode.getDescription(), System.currentTimeMillis(), resultEpisode.getId()), null, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda59
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ResultFragmentPhone.onBindingCreated$lambda$10$0$0$3(this.f$0, resultEpisode, (DownloadClickEvent) obj);
                    }
                });
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$10$0$0$0(ResultFragmentPhone resultFragmentPhone, ResultEpisode resultEpisode, View view) {
        resultFragmentPhone.getViewModel().handleAction(new EpisodeClickEvent(11, resultEpisode));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindingCreated$lambda$10$0$0$1(ResultFragmentPhone resultFragmentPhone, ResultEpisode resultEpisode, View view) {
        resultFragmentPhone.getViewModel().handleAction(new EpisodeClickEvent(10, resultEpisode));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindingCreated$lambda$10$0$0$2(ResultFragmentPhone resultFragmentPhone, ResultEpisode resultEpisode, View view) {
        resultFragmentPhone.getViewModel().handleAction(new EpisodeClickEvent(10, resultEpisode));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$10$0$0$3(ResultFragmentPhone resultFragmentPhone, ResultEpisode resultEpisode, DownloadClickEvent click) {
        Intrinsics.checkNotNullParameter(click, "click");
        Context context = resultFragmentPhone.getContext();
        if (context != null) {
            BatteryOptimizationChecker.INSTANCE.openBatteryOptimizationSettings(context);
        }
        int action = click.getAction();
        if (action == 4) {
            resultFragmentPhone.requirePathForActions(CollectionsKt.listOf(TuplesKt.to(6, resultEpisode)));
        } else if (action == 5) {
            resultFragmentPhone.requirePathForActions(CollectionsKt.listOf(TuplesKt.to(7, resultEpisode)));
        } else {
            DownloadButtonSetup.INSTANCE.handleDownloadClick(click);
        }
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$11(final ResultFragmentPhone resultFragmentPhone, FragmentResultSwipeBinding fragmentResultSwipeBinding, ResultFragment.StoredData storedData, Resource resource) {
        final ResultData resultData;
        List<ActorData> actors;
        if (resource == null) {
            return Unit.INSTANCE;
        }
        final FragmentResultBinding fragmentResultBinding = resultFragmentPhone.resultBinding;
        if (fragmentResultBinding != null) {
            PanelsChildGestureRegionObserver panelsChildGestureRegionObserver = PanelsChildGestureRegionObserver.Provider.get();
            RecyclerView resultCastItems = fragmentResultBinding.resultCastItems;
            Intrinsics.checkNotNullExpressionValue(resultCastItems, "resultCastItems");
            panelsChildGestureRegionObserver.register(resultCastItems);
            boolean z = resource instanceof Resource.Success;
            Resource.Success success = z ? (Resource.Success) resource : null;
            if (success != null && (resultData = (ResultData) success.getValue()) != null) {
                TextUtilKt.setText(fragmentResultBinding.resultVpn, resultData.getVpnText());
                TextUtilKt.setText(fragmentResultBinding.resultInfo, resultData.getMetaText());
                TextUtilKt.setText(fragmentResultBinding.resultNoEpisodes, resultData.getNoEpisodesFoundText());
                TextUtilKt.setText(fragmentResultBinding.resultTitle, resultData.getTitleText());
                TextUtilKt.setText(fragmentResultBinding.resultMetaSite, resultData.getApiName());
                TextUtilKt.setText(fragmentResultBinding.resultMetaType, resultData.getTypeText());
                TextUtilKt.setText(fragmentResultBinding.resultMetaYear, resultData.getYearText());
                TextUtilKt.setText(fragmentResultBinding.resultMetaDuration, resultData.getDurationText());
                TextUtilKt.setText(fragmentResultBinding.resultMetaRating, resultData.getRatingText());
                TextUtilKt.setText(fragmentResultBinding.resultMetaStatus, resultData.getOnGoingText());
                TextUtilKt.setText(fragmentResultBinding.resultMetaContentRating, resultData.getContentRatingText());
                TextUtilKt.setText(fragmentResultBinding.resultCastText, resultData.getActorsText());
                TextUtilKt.setText(fragmentResultBinding.resultNextAiring, resultData.getNextAiringEpisode());
                TextUtilKt.setText(fragmentResultBinding.resultNextAiringTime, resultData.getNextAiringDate());
                ImageLoader imageLoader = ImageLoader.INSTANCE;
                ImageView resultPoster = fragmentResultBinding.resultPoster;
                Intrinsics.checkNotNullExpressionValue(resultPoster, "resultPoster");
                imageLoader.loadImage(resultPoster, resultData.getPosterImage(), resultData.getPosterHeaders(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda67
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ResultFragmentPhone.onBindingCreated$lambda$11$0$1$0(this.f$0, (ImageRequest.Builder) obj);
                    }
                });
                ImageLoader imageLoader2 = ImageLoader.INSTANCE;
                ImageView resultPosterBackground = fragmentResultBinding.resultPosterBackground;
                Intrinsics.checkNotNullExpressionValue(resultPosterBackground, "resultPosterBackground");
                imageLoader2.loadImage(resultPosterBackground, resultData.getPosterBackgroundImage(), resultData.getPosterHeaders(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda68
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ResultFragmentPhone.onBindingCreated$lambda$11$0$1$1(this.f$0, (ImageRequest.Builder) obj);
                    }
                });
                ResultFragment resultFragment = ResultFragment.INSTANCE;
                String logoUrl = resultData.getLogoUrl();
                Map<String, String> posterHeaders = resultData.getPosterHeaders();
                TextView resultTitle = fragmentResultBinding.resultTitle;
                Intrinsics.checkNotNullExpressionValue(resultTitle, "resultTitle");
                ImageView backgroundPosterWatermarkBadge = fragmentResultBinding.backgroundPosterWatermarkBadge;
                Intrinsics.checkNotNullExpressionValue(backgroundPosterWatermarkBadge, "backgroundPosterWatermarkBadge");
                resultFragment.bindLogo(logoUrl, posterHeaders, backgroundPosterWatermarkBadge, resultTitle);
                final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                final TextView textView = fragmentResultBinding.resultDescription;
                TextUtilKt.setTextHtml(textView, resultData.getPlotText());
                textView.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda69
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ResultFragmentPhone.onBindingCreated$lambda$11$0$1$2$0(booleanRef, textView, view);
                    }
                });
                UIHelper.populateChips$default(UIHelper.INSTANCE, fragmentResultBinding.resultTag, resultData.getTags(), 0, null, 12, null);
                TextView resultComingSoon = fragmentResultBinding.resultComingSoon;
                Intrinsics.checkNotNullExpressionValue(resultComingSoon, "resultComingSoon");
                resultComingSoon.setVisibility(resultData.getComingSoon() ? 0 : 8);
                LinearLayout resultDataHolder = fragmentResultBinding.resultDataHolder;
                Intrinsics.checkNotNullExpressionValue(resultDataHolder, "resultDataHolder");
                resultDataHolder.setVisibility(resultData.getComingSoon() ? 8 : 0);
                boolean z2 = true;
                boolean z3 = PreferenceManager.getDefaultSharedPreferences(fragmentResultBinding.getRoot().getContext()).getBoolean(fragmentResultBinding.getRoot().getContext().getString(R.string.show_cast_in_details_key), true);
                RecyclerView resultCastItems2 = fragmentResultBinding.resultCastItems;
                Intrinsics.checkNotNullExpressionValue(resultCastItems2, "resultCastItems");
                RecyclerView recyclerView = resultCastItems2;
                if (z3 && (actors = resultData.getActors()) != null && !actors.isEmpty()) {
                    z2 = false;
                }
                recyclerView.setVisibility(z2 ? 8 : 0);
                RecyclerView.Adapter adapter = fragmentResultBinding.resultCastItems.getAdapter();
                ActorAdaptor actorAdaptor = adapter instanceof ActorAdaptor ? (ActorAdaptor) adapter : null;
                if (actorAdaptor != null) {
                    BaseAdapter.submitList$default(actorAdaptor, z3 ? resultData.getActors() : CollectionsKt.emptyList(), null, 2, null);
                }
                if (resultData.getContentRatingText() == null) {
                    fragmentResultBinding.resultMetaContentRating.setWidth(0);
                }
                if (resultFragmentPhone.getSyncModel().addSyncs(resultData.getSyncData())) {
                    resultFragmentPhone.getSyncModel().updateMetaAndUser();
                    resultFragmentPhone.getSyncModel().updateSynced();
                } else {
                    resultFragmentPhone.getSyncModel().addFromUrl(resultData.getUrl());
                }
                ImageView resultSearch = fragmentResultSwipeBinding.resultSearch;
                Intrinsics.checkNotNullExpressionValue(resultSearch, "resultSearch");
                resultSearch.setVisibility(StringsKt.isBlank(resultData.getTitle()) ? 8 : 0);
                fragmentResultSwipeBinding.resultSearch.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda70
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ResultFragmentPhone.onBindingCreated$lambda$11$0$1$3$0(this.f$0, resultData, view);
                    }
                });
                fragmentResultSwipeBinding.resultShare.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda71
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ResultFragmentPhone.onBindingCreated$lambda$11$0$1$3$1(resultData, resultFragmentPhone, view);
                    }
                });
                resultFragmentPhone.setUrl(resultData.getUrl());
                ExtendedFloatingActionButton extendedFloatingActionButton = fragmentResultSwipeBinding.resultBookmarkFab;
                Intrinsics.checkNotNull(extendedFloatingActionButton);
                extendedFloatingActionButton.setVisibility(0);
                extendedFloatingActionButton.extend();
            }
            boolean z4 = resource instanceof Resource.Failure;
            Resource.Failure failure = z4 ? (Resource.Failure) resource : null;
            if (failure != null) {
                fragmentResultBinding.resultErrorText.setText(storedData.getUrl() + '\n' + failure.getErrorString());
            }
            ExtendedFloatingActionButton resultBookmarkFab = fragmentResultSwipeBinding.resultBookmarkFab;
            Intrinsics.checkNotNullExpressionValue(resultBookmarkFab, "resultBookmarkFab");
            resultBookmarkFab.setVisibility(z ? 0 : 8);
            LinearLayout resultFinishLoading = fragmentResultBinding.resultFinishLoading;
            Intrinsics.checkNotNullExpressionValue(resultFinishLoading, "resultFinishLoading");
            resultFinishLoading.setVisibility(z ? 0 : 8);
            ShimmerFrameLayout resultLoading = fragmentResultBinding.resultLoading;
            Intrinsics.checkNotNullExpressionValue(resultLoading, "resultLoading");
            resultLoading.setVisibility(resource instanceof Resource.Loading ? 0 : 8);
            LinearLayout resultLoadingError = fragmentResultBinding.resultLoadingError;
            Intrinsics.checkNotNullExpressionValue(resultLoadingError, "resultLoadingError");
            resultLoadingError.setVisibility(z4 ? 0 : 8);
            TextView resultErrorText = fragmentResultBinding.resultErrorText;
            Intrinsics.checkNotNullExpressionValue(resultErrorText, "resultErrorText");
            resultErrorText.setVisibility(z4 ? 0 : 8);
            MaterialButton resultReloadConnectionOpenInBrowser = fragmentResultBinding.resultReloadConnectionOpenInBrowser;
            Intrinsics.checkNotNullExpressionValue(resultReloadConnectionOpenInBrowser, "resultReloadConnectionOpenInBrowser");
            resultReloadConnectionOpenInBrowser.setVisibility(z4 ? 0 : 8);
            fragmentResultBinding.resultTitle.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda72
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    return ResultFragmentPhone.onBindingCreated$lambda$11$0$3(fragmentResultBinding, view);
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$11$0$1$0(final ResultFragmentPhone resultFragmentPhone, ImageRequest.Builder loadImage) {
        Intrinsics.checkNotNullParameter(loadImage, "$this$loadImage");
        loadImage.error(new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda22
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentPhone.onBindingCreated$lambda$11$0$1$0$0(this.f$0, (ImageRequest) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Image onBindingCreated$lambda$11$0$1$0$0(ResultFragmentPhone resultFragmentPhone, ImageRequest it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Context context = resultFragmentPhone.getContext();
        if (context == null) {
            return null;
        }
        return ImageUtilKt.getImageFromDrawable(context, R.drawable.default_cover);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$11$0$1$1(final ResultFragmentPhone resultFragmentPhone, ImageRequest.Builder loadImage) {
        Intrinsics.checkNotNullParameter(loadImage, "$this$loadImage");
        loadImage.error(new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentPhone.onBindingCreated$lambda$11$0$1$1$0(this.f$0, (ImageRequest) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Image onBindingCreated$lambda$11$0$1$1$0(ResultFragmentPhone resultFragmentPhone, ImageRequest it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Context context = resultFragmentPhone.getContext();
        if (context == null) {
            return null;
        }
        return ImageUtilKt.getImageFromDrawable(context, R.drawable.default_cover);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$11$0$1$2$0(Ref.BooleanRef booleanRef, TextView textView, View view) {
        booleanRef.element = !booleanRef.element;
        textView.setMaxLines(booleanRef.element ? Integer.MAX_VALUE : 10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$11$0$1$3$0(ResultFragmentPhone resultFragmentPhone, ResultData resultData, View view) {
        QuickSearchFragment.Companion.pushSearch$default(QuickSearchFragment.INSTANCE, resultFragmentPhone.getActivity(), resultData.getTitle(), null, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$11$0$1$3$1(ResultData resultData, ResultFragmentPhone resultFragmentPhone, View view) {
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            byte[] bytes = resultData.getApiName().toString().getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            String strBase64Encode = MainAPIKt.base64Encode(bytes);
            byte[] bytes2 = resultData.getUrl().getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes2, "getBytes(...)");
            String strEncode = URLEncoder.encode("csshare:" + strBase64Encode + '?' + MainAPIKt.base64Encode(bytes2), "UTF-8");
            StringBuilder sb = new StringBuilder("https://recloudstream.github.io/csredirect?redirectto=");
            sb.append(strEncode);
            String string = sb.toString();
            intent.setType(UtilsKt.MIME_TYPE_TEXT_PLAIN);
            intent.putExtra("android.intent.extra.SUBJECT", resultData.getTitle());
            intent.putExtra("android.intent.extra.TEXT", string);
            resultFragmentPhone.startActivity(Intent.createChooser(intent, resultData.getTitle()));
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindingCreated$lambda$11$0$3(FragmentResultBinding fragmentResultBinding, View view) {
        UIHelper uIHelper = UIHelper.INSTANCE;
        UiText uiTextTxt = TextUtilKt.txt(R.string.title, new Object[0]);
        CharSequence text = fragmentResultBinding.resultTitle.getText();
        Intrinsics.checkNotNullExpressionValue(text, "getText(...)");
        uIHelper.clipboardHelper(uiTextTxt, text);
        return true;
    }

    static final Unit onBindingCreated$lambda$12(ResultFragmentPhone resultFragmentPhone, UiText uiText) {
        FragmentResultBinding fragmentResultBinding = resultFragmentPhone.resultBinding;
        TextUtilKt.setText(fragmentResultBinding != null ? fragmentResultBinding.resultEpisodesText : null, uiText);
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$13(final ResultFragmentPhone resultFragmentPhone, final SelectPopup selectPopup) {
        BottomSheetDialog bottomSheetDialogShowBottomDialogInstant = null;
        if (selectPopup == null) {
            Dialog dialog = resultFragmentPhone.popupDialog;
            if (dialog != null) {
                UIHelper.INSTANCE.dismissSafe(dialog, resultFragmentPhone.getActivity());
            }
            resultFragmentPhone.popupDialog = null;
            return Unit.INSTANCE;
        }
        Dialog dialog2 = resultFragmentPhone.popupDialog;
        if (dialog2 != null) {
            UIHelper.INSTANCE.dismissSafe(dialog2, resultFragmentPhone.getActivity());
        }
        FragmentActivity activity = resultFragmentPhone.getActivity();
        if (activity != null) {
            FragmentActivity fragmentActivity = activity;
            bottomSheetDialogShowBottomDialogInstant = SingleSelectionHelper.INSTANCE.showBottomDialogInstant(activity, ResultViewModel2Kt.getOptions(selectPopup, fragmentActivity), ResultViewModel2Kt.getTitle(selectPopup, fragmentActivity), new Function0() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda53
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return ResultFragmentPhone.onBindingCreated$lambda$13$0$0(this.f$0, selectPopup);
                }
            }, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda54
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ResultFragmentPhone.onBindingCreated$lambda$13$0$1(this.f$0, selectPopup, ((Integer) obj).intValue());
                }
            });
        }
        resultFragmentPhone.popupDialog = bottomSheetDialogShowBottomDialogInstant;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$13$0$0(ResultFragmentPhone resultFragmentPhone, SelectPopup selectPopup) {
        resultFragmentPhone.popupDialog = null;
        ResultViewModel2Kt.callback(selectPopup, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$13$0$1(ResultFragmentPhone resultFragmentPhone, SelectPopup selectPopup, int i) {
        resultFragmentPhone.popupDialog = null;
        ResultViewModel2Kt.callback(selectPopup, Integer.valueOf(i));
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$14(ResultFragmentPhone resultFragmentPhone, List trailers) {
        Intrinsics.checkNotNullParameter(trailers, "trailers");
        ArrayList arrayList = new ArrayList();
        Iterator it = trailers.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, ((ExtractedTrailerData) it.next()).getMirros());
        }
        resultFragmentPhone.setTrailers(arrayList);
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$15(ResultFragmentPhone resultFragmentPhone, FragmentResultSwipeBinding fragmentResultSwipeBinding, List list) {
        TextView textView;
        Intrinsics.checkNotNullParameter(list, "list");
        ResultSyncBinding resultSyncBinding = resultFragmentPhone.syncBinding;
        if (resultSyncBinding != null && (textView = resultSyncBinding.resultSyncNames) != null) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : list) {
                CurrentSynced currentSynced = (CurrentSynced) obj;
                if (currentSynced.isSynced() && currentSynced.getHasAccount()) {
                    arrayList.add(obj);
                }
            }
            textView.setText(CollectionsKt.joinToString$default(arrayList, null, null, null, 0, null, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda51
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return ResultFragmentPhone.onBindingCreated$lambda$15$1((CurrentSynced) obj2);
                }
            }, 31, null));
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : list) {
            CurrentSynced currentSynced2 = (CurrentSynced) obj2;
            if (currentSynced2.isSynced() && currentSynced2.getHasAccount()) {
                arrayList2.add(obj2);
            }
        }
        ImageView resultMiniSync = fragmentResultSwipeBinding.resultMiniSync;
        Intrinsics.checkNotNullExpressionValue(resultMiniSync, "resultMiniSync");
        resultMiniSync.setVisibility(!arrayList2.isEmpty() ? 0 : 8);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence onBindingCreated$lambda$15$1(CurrentSynced it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getName();
    }

    private static final void onBindingCreated$setSyncMaxEpisodes(final ResultFragmentPhone resultFragmentPhone, final Integer num) {
        ContentLoadingProgressBar contentLoadingProgressBar;
        ResultSyncBinding resultSyncBinding = resultFragmentPhone.syncBinding;
        if (resultSyncBinding != null && (contentLoadingProgressBar = resultSyncBinding.resultSyncEpisodes) != null) {
            contentLoadingProgressBar.setMax((num != null ? num.intValue() : 0) * 1000);
        }
        ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda86
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ResultFragmentPhone.onBindingCreated$setSyncMaxEpisodes$lambda$16(this.f$0, num);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0047 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:22:0x0049  */
    static final Unit onBindingCreated$setSyncMaxEpisodes$lambda$16(ResultFragmentPhone resultFragmentPhone, Integer num) {
        TextView textView;
        String string;
        String str;
        String string2;
        ContentLoadingProgressBar contentLoadingProgressBar;
        ResultSyncBinding resultSyncBinding = resultFragmentPhone.syncBinding;
        Context context = (resultSyncBinding == null || (contentLoadingProgressBar = resultSyncBinding.resultSyncEpisodes) == null) ? null : contentLoadingProgressBar.getContext();
        ResultSyncBinding resultSyncBinding2 = resultFragmentPhone.syncBinding;
        if (resultSyncBinding2 != null && (textView = resultSyncBinding2.resultSyncMaxEpisodes) != null) {
            if (num != null) {
                int iIntValue = num.intValue();
                if (context == null || (string2 = context.getString(R.string.sync_total_episodes_some)) == null) {
                    str = null;
                } else {
                    str = String.format(string2, Arrays.copyOf(new Object[]{Integer.valueOf(iIntValue)}, 1));
                    Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                }
                if (str != null) {
                    string = str;
                } else {
                    string = context != null ? context.getString(R.string.sync_total_episodes_none) : null;
                }
            } else {
                string = context != null ? context.getString(R.string.sync_total_episodes_none) : null;
            }
            textView.setText(string);
        }
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$17(ResultFragmentPhone resultFragmentPhone, Ref.IntRef intRef, Resource resource) {
        ResultSyncBinding resultSyncBinding;
        TextView textView;
        TextView textView2;
        Context context;
        ContentLoadingProgressBar contentLoadingProgressBar;
        if (resource instanceof Resource.Success) {
            SyncAPI.SyncResult syncResult = (SyncAPI.SyncResult) ((Resource.Success) resource).getValue();
            ResultSyncBinding resultSyncBinding2 = resultFragmentPhone.syncBinding;
            if (resultSyncBinding2 != null && (contentLoadingProgressBar = resultSyncBinding2.resultSyncEpisodes) != null) {
                contentLoadingProgressBar.setProgress(intRef.element * 1000);
            }
            onBindingCreated$setSyncMaxEpisodes(resultFragmentPhone, syncResult.getTotalEpisodes());
            resultFragmentPhone.getViewModel().setMeta(syncResult, resultFragmentPhone.getSyncModel().getSyncs());
        } else if ((resource instanceof Resource.Loading) && (resultSyncBinding = resultFragmentPhone.syncBinding) != null && (textView = resultSyncBinding.resultSyncMaxEpisodes) != null) {
            ResultSyncBinding resultSyncBinding3 = resultFragmentPhone.syncBinding;
            textView.setText((resultSyncBinding3 == null || (textView2 = resultSyncBinding3.resultSyncMaxEpisodes) == null || (context = textView2.getContext()) == null) ? null : context.getString(R.string.sync_total_episodes_none));
        }
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$18(final ResultFragmentPhone resultFragmentPhone, FragmentResultSwipeBinding fragmentResultSwipeBinding, Ref.IntRef intRef, Resource resource) {
        final ResultSyncBinding resultSyncBinding = resultFragmentPhone.syncBinding;
        boolean z = false;
        if (resultSyncBinding != null) {
            if (resource instanceof Resource.Failure) {
                resultSyncBinding.resultSyncLoadingShimmer.stopShimmer();
                ShimmerFrameLayout resultSyncLoadingShimmer = resultSyncBinding.resultSyncLoadingShimmer;
                Intrinsics.checkNotNullExpressionValue(resultSyncLoadingShimmer, "resultSyncLoadingShimmer");
                resultSyncLoadingShimmer.setVisibility(8);
                ScrollView resultSyncHolder = resultSyncBinding.resultSyncHolder;
                Intrinsics.checkNotNullExpressionValue(resultSyncHolder, "resultSyncHolder");
                resultSyncHolder.setVisibility(8);
                z = true;
            } else if (resource instanceof Resource.Loading) {
                resultSyncBinding.resultSyncLoadingShimmer.startShimmer();
                ShimmerFrameLayout resultSyncLoadingShimmer2 = resultSyncBinding.resultSyncLoadingShimmer;
                Intrinsics.checkNotNullExpressionValue(resultSyncLoadingShimmer2, "resultSyncLoadingShimmer");
                resultSyncLoadingShimmer2.setVisibility(0);
                ScrollView resultSyncHolder2 = resultSyncBinding.resultSyncHolder;
                Intrinsics.checkNotNullExpressionValue(resultSyncHolder2, "resultSyncHolder");
                resultSyncHolder2.setVisibility(8);
            } else if (resource instanceof Resource.Success) {
                resultSyncBinding.resultSyncLoadingShimmer.stopShimmer();
                ShimmerFrameLayout resultSyncLoadingShimmer3 = resultSyncBinding.resultSyncLoadingShimmer;
                Intrinsics.checkNotNullExpressionValue(resultSyncLoadingShimmer3, "resultSyncLoadingShimmer");
                resultSyncLoadingShimmer3.setVisibility(8);
                ScrollView resultSyncHolder3 = resultSyncBinding.resultSyncHolder;
                Intrinsics.checkNotNullExpressionValue(resultSyncHolder3, "resultSyncHolder");
                resultSyncHolder3.setVisibility(0);
                final SyncAPI.AbstractSyncStatus abstractSyncStatus = (SyncAPI.AbstractSyncStatus) ((Resource.Success) resource).getValue();
                Score score = abstractSyncStatus.getScore();
                resultSyncBinding.resultSyncRating.setValue(MathKt.roundToInt((resultSyncBinding.resultSyncRating.getValueTo() / resultSyncBinding.resultSyncRating.getStepSize()) * (score != null ? score.toFloat(1) : 0.0f)) * resultSyncBinding.resultSyncRating.getStepSize());
                resultSyncBinding.resultSyncCheck.setItemChecked(abstractSyncStatus.getStatus().getInternalId() + 1, true);
                Integer watchedEpisodes = abstractSyncStatus.getWatchedEpisodes();
                int iIntValue = watchedEpisodes != null ? watchedEpisodes.intValue() : 0;
                intRef.element = iIntValue;
                Integer maxEpisodes = abstractSyncStatus.getMaxEpisodes();
                if (maxEpisodes != null) {
                    onBindingCreated$setSyncMaxEpisodes(resultFragmentPhone, Integer.valueOf(maxEpisodes.intValue()));
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    resultSyncBinding.resultSyncEpisodes.setProgress(iIntValue * 1000, true);
                } else {
                    resultSyncBinding.resultSyncEpisodes.setProgress(iIntValue * 1000);
                }
                EditText editText = resultSyncBinding.resultSyncCurrentEpisodes;
                Editable.Factory factory = Editable.Factory.getInstance();
                editText.setText(factory != null ? factory.newEditable(String.valueOf(iIntValue)) : null);
            } else if (resource != null) {
                throw new NoWhenBranchMatchedException();
            }
        }
        fragmentResultSwipeBinding.resultOverlappingPanels.setStartPanelLockState(z ? OverlappingPanelsLayout.LockState.CLOSE : OverlappingPanelsLayout.LockState.UNLOCKED);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:11:0x0039  */
    public static final Unit onBindingCreated$lambda$18$0$1(SyncAPI.AbstractSyncStatus abstractSyncStatus, ResultSyncBinding resultSyncBinding, ResultFragmentPhone resultFragmentPhone) {
        String str;
        String string;
        Score score = abstractSyncStatus.getScore();
        if (score != null) {
            int iRoundToInt = MathKt.roundToInt(score.toFloat(10));
            Context context = resultFragmentPhone.getContext();
            if (context == null || (string = context.getString(R.string.sync_score_format)) == null) {
                str = null;
            } else {
                str = String.format(string, Arrays.copyOf(new Object[]{Integer.valueOf(iRoundToInt)}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            }
            if (str == null) {
                str = "?";
            }
        } else {
            str = "?";
        }
        resultSyncBinding.resultSyncScoreText.setText(str);
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$19(ResultFragmentPhone resultFragmentPhone, List recommendations) {
        Intrinsics.checkNotNullParameter(recommendations, "recommendations");
        resultFragmentPhone.setRecommendations(recommendations, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$20$1$0(ResultFragmentPhone resultFragmentPhone, AdapterView adapterView, View view, int i, long j) {
        resultFragmentPhone.getSyncModel().setStatus(i - 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$20$1$1(ResultFragmentPhone resultFragmentPhone, Slider it, float f, boolean z) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (z) {
            resultFragmentPhone.getSyncModel().setScore(Score.INSTANCE.from(Float.valueOf(f), MathKt.roundToInt(it.getValueTo())));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$20$1$2(ResultFragmentPhone resultFragmentPhone, View view) {
        resultFragmentPhone.getSyncModel().setEpisodesDelta(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$20$1$3(ResultFragmentPhone resultFragmentPhone, View view) {
        resultFragmentPhone.getSyncModel().setEpisodesDelta(-1);
    }

    static final Unit onBindingCreated$lambda$22(FragmentResultSwipeBinding fragmentResultSwipeBinding, final ResultFragmentPhone resultFragmentPhone, final WatchType watchType) {
        Intrinsics.checkNotNullParameter(watchType, "watchType");
        final ExtendedFloatingActionButton extendedFloatingActionButton = fragmentResultSwipeBinding.resultBookmarkFab;
        extendedFloatingActionButton.setText(watchType.getStringRes());
        Integer numValueOf = null;
        if (watchType == WatchType.NONE) {
            Context context = extendedFloatingActionButton.getContext();
            if (context != null) {
                numValueOf = Integer.valueOf(UIHelper.INSTANCE.colorFromAttribute(context, R.attr.white));
            }
        } else {
            Context context2 = extendedFloatingActionButton.getContext();
            if (context2 != null) {
                numValueOf = Integer.valueOf(UIHelper.INSTANCE.colorFromAttribute(context2, R.attr.colorPrimary));
            }
        }
        if (numValueOf != null) {
            ColorStateList colorStateListValueOf = ColorStateList.valueOf(numValueOf.intValue());
            Intrinsics.checkNotNullExpressionValue(colorStateListValueOf, "valueOf(...)");
            extendedFloatingActionButton.setIconTint(colorStateListValueOf);
            extendedFloatingActionButton.setTextColor(colorStateListValueOf);
        }
        extendedFloatingActionButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda85
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ResultFragmentPhone.onBindingCreated$lambda$22$0$1(this.f$0, watchType, extendedFloatingActionButton, view);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$22$0$1(final ResultFragmentPhone resultFragmentPhone, WatchType watchType, final ExtendedFloatingActionButton extendedFloatingActionButton, View view) {
        FragmentActivity activity = resultFragmentPhone.getActivity();
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
            singleSelectionHelper.showBottomDialog(fragmentActivity, list, iOrdinal, string, false, new Function0() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda87
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            }, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda89
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ResultFragmentPhone.onBindingCreated$lambda$22$0$1$2(this.f$0, extendedFloatingActionButton, ((Integer) obj).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$22$0$1$2(ResultFragmentPhone resultFragmentPhone, ExtendedFloatingActionButton extendedFloatingActionButton, int i) {
        ResultViewModel2.updateWatchStatus$default(resultFragmentPhone.getViewModel(), WatchType.getEntries().get(i), extendedFloatingActionButton.getContext(), null, null, 12, null);
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$23(final ResultFragmentPhone resultFragmentPhone, LinkProgress linkProgress) {
        MaterialButton materialButton;
        BottomSheetDialog bottomSheetDialog = null;
        if (linkProgress == null) {
            Dialog dialog = resultFragmentPhone.loadingDialog;
            if (dialog != null) {
                UIHelper.INSTANCE.dismissSafe(dialog, resultFragmentPhone.getActivity());
            }
            resultFragmentPhone.loadingDialog = null;
            return Unit.INSTANCE;
        }
        Dialog dialog2 = resultFragmentPhone.loadingDialog;
        if (dialog2 == null || !dialog2.isShowing()) {
            Dialog dialog3 = resultFragmentPhone.loadingDialog;
            if (dialog3 != null) {
                UIHelper.INSTANCE.dismissSafe(dialog3, resultFragmentPhone.getActivity());
            }
            resultFragmentPhone.loadingDialog = null;
        }
        BottomSheetDialog bottomSheetDialog2 = resultFragmentPhone.loadingDialog;
        if (bottomSheetDialog2 == null) {
            Context context = resultFragmentPhone.getContext();
            if (context != null) {
                bottomSheetDialog = new BottomSheetDialog(context);
                bottomSheetDialog.setContentView(R.layout.bottom_loading);
                bottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda88
                    @Override // android.content.DialogInterface.OnDismissListener
                    public final void onDismiss(DialogInterface dialogInterface) {
                        ResultFragmentPhone.onBindingCreated$lambda$23$0$0(this.f$0, dialogInterface);
                    }
                });
                bottomSheetDialog.setCanceledOnTouchOutside(true);
                bottomSheetDialog.show();
            }
            bottomSheetDialog2 = bottomSheetDialog;
        }
        resultFragmentPhone.loadingDialog = bottomSheetDialog2;
        if (bottomSheetDialog2 != null && (materialButton = (MaterialButton) bottomSheetDialog2.findViewById(R.id.overlay_loading_skip_button)) != null) {
            if (linkProgress.getLinksLoaded() > 0) {
                materialButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda93
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ResultFragmentPhone.onBindingCreated$lambda$23$1$0(this.f$0, view);
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
    public static final void onBindingCreated$lambda$23$0$0(ResultFragmentPhone resultFragmentPhone, DialogInterface dialogInterface) {
        resultFragmentPhone.loadingDialog = null;
        resultFragmentPhone.getViewModel().cancelLinks();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$23$1$0(ResultFragmentPhone resultFragmentPhone, View view) {
        resultFragmentPhone.getViewModel().skipLoading();
    }

    static final Unit onBindingCreated$lambda$24(ResultFragmentPhone resultFragmentPhone, UiText uiText) {
        FragmentResultBinding fragmentResultBinding = resultFragmentPhone.resultBinding;
        if (fragmentResultBinding != null) {
            TextUtilKt.setText(fragmentResultBinding.resultSeasonButton, uiText);
            resultFragmentPhone.selectSeason = uiText != null ? uiText.asStringNull(fragmentResultBinding.resultSeasonButton.getContext()) : null;
            MaterialButton resultSeasonButton = fragmentResultBinding.resultSeasonButton;
            Intrinsics.checkNotNullExpressionValue(resultSeasonButton, "resultSeasonButton");
            if (resultSeasonButton.getVisibility() == 0) {
                LinearLayout resultResumeParent = fragmentResultBinding.resultResumeParent;
                Intrinsics.checkNotNullExpressionValue(resultResumeParent, "resultResumeParent");
                if (resultResumeParent.getVisibility() == 0) {
                    resultFragmentPhone.setFocusUpAndDown(fragmentResultBinding.resultResumeSeriesButton, fragmentResultBinding.resultSeasonButton);
                }
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$25(ResultFragmentPhone resultFragmentPhone, UiText uiText) {
        FragmentResultBinding fragmentResultBinding = resultFragmentPhone.resultBinding;
        if (fragmentResultBinding != null) {
            TextUtilKt.setText(fragmentResultBinding.resultDubSelect, uiText);
            MaterialButton resultDubSelect = fragmentResultBinding.resultDubSelect;
            Intrinsics.checkNotNullExpressionValue(resultDubSelect, "resultDubSelect");
            if (resultDubSelect.getVisibility() == 0) {
                MaterialButton resultSeasonButton = fragmentResultBinding.resultSeasonButton;
                Intrinsics.checkNotNullExpressionValue(resultSeasonButton, "resultSeasonButton");
                if (resultSeasonButton.getVisibility() != 0) {
                    MaterialButton resultEpisodeSelect = fragmentResultBinding.resultEpisodeSelect;
                    Intrinsics.checkNotNullExpressionValue(resultEpisodeSelect, "resultEpisodeSelect");
                    if (resultEpisodeSelect.getVisibility() != 0) {
                        LinearLayout resultResumeParent = fragmentResultBinding.resultResumeParent;
                        Intrinsics.checkNotNullExpressionValue(resultResumeParent, "resultResumeParent");
                        if (resultResumeParent.getVisibility() == 0) {
                            resultFragmentPhone.setFocusUpAndDown(fragmentResultBinding.resultResumeSeriesButton, fragmentResultBinding.resultDubSelect);
                        }
                    }
                }
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$26(ResultFragmentPhone resultFragmentPhone, UiText uiText) {
        FragmentResultBinding fragmentResultBinding = resultFragmentPhone.resultBinding;
        if (fragmentResultBinding != null) {
            TextUtilKt.setText(fragmentResultBinding.resultEpisodeSelect, uiText);
            resultFragmentPhone.selectEpisodeRange = uiText != null ? uiText.asStringNull(fragmentResultBinding.resultEpisodeSelect.getContext()) : null;
            MaterialButton resultEpisodeSelect = fragmentResultBinding.resultEpisodeSelect;
            Intrinsics.checkNotNullExpressionValue(resultEpisodeSelect, "resultEpisodeSelect");
            if (resultEpisodeSelect.getVisibility() == 0) {
                MaterialButton resultSeasonButton = fragmentResultBinding.resultSeasonButton;
                Intrinsics.checkNotNullExpressionValue(resultSeasonButton, "resultSeasonButton");
                if (resultSeasonButton.getVisibility() != 0) {
                    LinearLayout resultResumeParent = fragmentResultBinding.resultResumeParent;
                    Intrinsics.checkNotNullExpressionValue(resultResumeParent, "resultResumeParent");
                    if (resultResumeParent.getVisibility() == 0) {
                        resultFragmentPhone.setFocusUpAndDown(fragmentResultBinding.resultResumeSeriesButton, fragmentResultBinding.resultEpisodeSelect);
                    }
                }
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$27(final ResultFragmentPhone resultFragmentPhone, final List range) {
        MaterialButton materialButton;
        Intrinsics.checkNotNullParameter(range, "range");
        FragmentResultBinding fragmentResultBinding = resultFragmentPhone.resultBinding;
        if (fragmentResultBinding != null && (materialButton = fragmentResultBinding.resultDubSelect) != null) {
            materialButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda60
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ResultFragmentPhone.onBindingCreated$lambda$27$0(range, resultFragmentPhone, view);
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$27$0(List list, final ResultFragmentPhone resultFragmentPhone, View view) {
        Context context;
        String strAsStringNull;
        if (view == null || (context = view.getContext()) == null) {
            return;
        }
        UIHelper uIHelper = UIHelper.INSTANCE;
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            UiText uiText = (UiText) pair.component1();
            Pair pair2 = (uiText == null || (strAsStringNull = uiText.asStringNull(context)) == null) ? null : new Pair(Integer.valueOf(((DubStatus) pair.component2()).ordinal()), strAsStringNull);
            if (pair2 != null) {
                arrayList.add(pair2);
            }
        }
        uIHelper.popupMenuNoIconsAndNoStringRes(view, arrayList, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda73
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultFragmentPhone.onBindingCreated$lambda$27$0$0$1(this.f$0, (MenuItem) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$27$0$0$1(ResultFragmentPhone resultFragmentPhone, MenuItem popupMenuNoIconsAndNoStringRes) {
        Intrinsics.checkNotNullParameter(popupMenuNoIconsAndNoStringRes, "$this$popupMenuNoIconsAndNoStringRes");
        resultFragmentPhone.getViewModel().changeDubStatus(DubStatus.getEntries().get(popupMenuNoIconsAndNoStringRes.getItemId()));
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$28(final ResultFragmentPhone resultFragmentPhone, final List range) {
        MaterialButton materialButton;
        Intrinsics.checkNotNullParameter(range, "range");
        FragmentResultBinding fragmentResultBinding = resultFragmentPhone.resultBinding;
        if (fragmentResultBinding != null && (materialButton = fragmentResultBinding.resultEpisodeSelect) != null) {
            materialButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda61
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ResultFragmentPhone.onBindingCreated$lambda$28$0(range, resultFragmentPhone, view);
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$28$0(List list, final ResultFragmentPhone resultFragmentPhone, View view) {
        Context context;
        String strAsStringNull;
        if (view == null || (context = view.getContext()) == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            UiText uiText = (UiText) pair.component1();
            Pair pair2 = (uiText == null || (strAsStringNull = uiText.asStringNull(context)) == null) ? null : TuplesKt.to((EpisodeRange) pair.component2(), strAsStringNull);
            if (pair2 != null) {
                arrayList.add(pair2);
            }
        }
        final ArrayList arrayList2 = arrayList;
        FragmentActivity activity = resultFragmentPhone.getActivity();
        if (activity != null) {
            SingleSelectionHelper singleSelectionHelper = SingleSelectionHelper.INSTANCE;
            FragmentActivity fragmentActivity = activity;
            ArrayList arrayList3 = arrayList2;
            ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
            Iterator it2 = arrayList3.iterator();
            while (it2.hasNext()) {
                arrayList4.add((String) ((Pair) it2.next()).getSecond());
            }
            ArrayList arrayList5 = arrayList4;
            Iterator it3 = arrayList2.iterator();
            int i = 0;
            while (it3.hasNext()) {
                if (Intrinsics.areEqual(((Pair) it3.next()).getSecond(), resultFragmentPhone.selectEpisodeRange)) {
                    String string = context.getString(R.string.episodes);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    singleSelectionHelper.showDialog(fragmentActivity, arrayList5, i, string, false, new Function0() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda74
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Unit.INSTANCE;
                        }
                    }, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda75
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return ResultFragmentPhone.onBindingCreated$lambda$28$0$0$4(this.f$0, arrayList2, ((Integer) obj).intValue());
                        }
                    });
                }
                i++;
            }
            i = -1;
            String string2 = context.getString(R.string.episodes);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            singleSelectionHelper.showDialog(fragmentActivity, arrayList5, i, string2, false, new Function0() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda74
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            }, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda75
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ResultFragmentPhone.onBindingCreated$lambda$28$0$0$4(this.f$0, arrayList2, ((Integer) obj).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$28$0$0$4(ResultFragmentPhone resultFragmentPhone, List list, int i) {
        resultFragmentPhone.getViewModel().changeRange((EpisodeRange) ((Pair) list.get(i)).getFirst());
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$29(final ResultFragmentPhone resultFragmentPhone, final List seasonList) {
        MaterialButton materialButton;
        Intrinsics.checkNotNullParameter(seasonList, "seasonList");
        FragmentResultBinding fragmentResultBinding = resultFragmentPhone.resultBinding;
        if (fragmentResultBinding != null && (materialButton = fragmentResultBinding.resultSeasonButton) != null) {
            materialButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda11
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ResultFragmentPhone.onBindingCreated$lambda$29$0(seasonList, resultFragmentPhone, view);
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$29$0(List list, final ResultFragmentPhone resultFragmentPhone, View view) {
        Context context;
        String strAsStringNull;
        if (view == null || (context = view.getContext()) == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            UiText uiText = (UiText) pair.component1();
            Pair pair2 = (uiText == null || (strAsStringNull = uiText.asStringNull(context)) == null) ? null : TuplesKt.to(Integer.valueOf(((Number) pair.component2()).intValue()), strAsStringNull);
            if (pair2 != null) {
                arrayList.add(pair2);
            }
        }
        final ArrayList arrayList2 = arrayList;
        FragmentActivity activity = resultFragmentPhone.getActivity();
        if (activity != null) {
            SingleSelectionHelper singleSelectionHelper = SingleSelectionHelper.INSTANCE;
            FragmentActivity fragmentActivity = activity;
            ArrayList arrayList3 = arrayList2;
            ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
            Iterator it2 = arrayList3.iterator();
            while (it2.hasNext()) {
                arrayList4.add((String) ((Pair) it2.next()).getSecond());
            }
            ArrayList arrayList5 = arrayList4;
            Iterator it3 = arrayList2.iterator();
            int i = 0;
            while (it3.hasNext()) {
                if (Intrinsics.areEqual(((Pair) it3.next()).getSecond(), resultFragmentPhone.selectSeason)) {
                    String string = context.getString(R.string.season);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    singleSelectionHelper.showDialog(fragmentActivity, arrayList5, i, string, false, new Function0() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda66
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Unit.INSTANCE;
                        }
                    }, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda77
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return ResultFragmentPhone.onBindingCreated$lambda$29$0$0$4(this.f$0, arrayList2, ((Integer) obj).intValue());
                        }
                    });
                }
                i++;
            }
            i = -1;
            String string2 = context.getString(R.string.season);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            singleSelectionHelper.showDialog(fragmentActivity, arrayList5, i, string2, false, new Function0() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda66
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            }, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda77
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ResultFragmentPhone.onBindingCreated$lambda$29$0$0$4(this.f$0, arrayList2, ((Integer) obj).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$29$0$0$4(ResultFragmentPhone resultFragmentPhone, List list, int i) {
        resultFragmentPhone.getViewModel().changeSeason(((Number) ((Pair) list.get(i)).getFirst()).intValue());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resumeAction(ResultFragment.StoredData storedData, ResumeWatchingStatus resume) {
        getViewModel().handleAction(new EpisodeClickEvent(storedData.getPlayerAction(), resume.getResult()));
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        PlayerView playerView = this.playerHostView;
        if (playerView != null) {
            playerView.releaseKeyEventListener();
        }
        super.onPause();
        PanelsChildGestureRegionObserver.Provider.get().addGestureRegionsUpdateListener(this.gestureRegionsListener);
    }

    private final void setRecommendations(final List<? extends SearchResponse> rec, final String validApiName) {
        MaterialButton materialButton;
        MaterialButton materialButton2;
        SearchResponse searchResponse;
        List<? extends SearchResponse> list = rec;
        boolean z = list == null || list.isEmpty();
        MaterialButton materialButton3 = null;
        if (validApiName == null) {
            validApiName = (rec == null || (searchResponse = (SearchResponse) CollectionsKt.firstOrNull((List) rec)) == null) ? null : searchResponse.getApiName();
        }
        final ResultRecommendationsBinding resultRecommendationsBinding = this.recommendationBinding;
        if (resultRecommendationsBinding != null) {
            NestedScrollView root = resultRecommendationsBinding.getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
            root.setVisibility(z ? 8 : 0);
            resultRecommendationsBinding.getRoot().post(new Runnable() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda90
                @Override // java.lang.Runnable
                public final void run() {
                    ResultFragmentPhone.setRecommendations$lambda$0$0(rec, resultRecommendationsBinding, validApiName);
                }
            });
        }
        final FragmentResultSwipeBinding binding = getBinding();
        if (binding != null) {
            ImageView resultRecommendationsBtt = binding.resultRecommendationsBtt;
            Intrinsics.checkNotNullExpressionValue(resultRecommendationsBtt, "resultRecommendationsBtt");
            resultRecommendationsBtt.setVisibility(z ? 8 : 0);
            binding.resultRecommendationsBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda91
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ResultFragmentPhone.setRecommendations$lambda$1$0(binding, this, view);
                }
            });
            binding.resultOverlappingPanels.setEndPanelLockState(z ? OverlappingPanelsLayout.LockState.CLOSE : OverlappingPanelsLayout.LockState.UNLOCKED);
            if (rec != null) {
                List<? extends SearchResponse> list2 = rec;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                Iterator<T> it = list2.iterator();
                while (it.hasNext()) {
                    arrayList.add(((SearchResponse) it.next()).getApiName());
                }
                final List listDistinct = CollectionsKt.distinct(arrayList);
                if (listDistinct != null) {
                    ResultRecommendationsBinding resultRecommendationsBinding2 = this.recommendationBinding;
                    if (resultRecommendationsBinding2 != null && (materialButton2 = resultRecommendationsBinding2.resultRecommendationsFilterButton) != null) {
                        materialButton2.setVisibility(listDistinct.size() > 1 ? 0 : 8);
                        materialButton2.setText(validApiName);
                        materialButton2.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda92
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                ResultFragmentPhone.setRecommendations$lambda$1$2$0$0(this.f$0, listDistinct, validApiName, rec, view);
                            }
                        });
                        materialButton3 = materialButton2;
                    }
                    if (materialButton3 != null) {
                        return;
                    }
                }
            }
            ResultRecommendationsBinding resultRecommendationsBinding3 = this.recommendationBinding;
            if (resultRecommendationsBinding3 != null && (materialButton = resultRecommendationsBinding3.resultRecommendationsFilterButton) != null) {
                materialButton.setVisibility(8);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setRecommendations$lambda$0$0(List list, ResultRecommendationsBinding resultRecommendationsBinding, String str) {
        if (list != null) {
            RecyclerView.Adapter adapter = resultRecommendationsBinding.resultRecommendationsList.getAdapter();
            SearchAdapter searchAdapter = adapter instanceof SearchAdapter ? (SearchAdapter) adapter : null;
            if (searchAdapter != null) {
                SearchAdapter searchAdapter2 = searchAdapter;
                ArrayList arrayList = new ArrayList();
                for (Object obj : list) {
                    if (Intrinsics.areEqual(((SearchResponse) obj).getApiName(), str)) {
                        arrayList.add(obj);
                    }
                }
                BaseAdapter.submitList$default(searchAdapter2, arrayList, null, 2, null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setRecommendations$lambda$1$0(FragmentResultSwipeBinding fragmentResultSwipeBinding, ResultFragmentPhone resultFragmentPhone, View view) {
        int i;
        if (fragmentResultSwipeBinding.resultOverlappingPanels.getSelectedPanel().ordinal() == 1) {
            fragmentResultSwipeBinding.resultOverlappingPanels.openEndPanel();
            i = R.id.result_recommendations;
        } else {
            fragmentResultSwipeBinding.resultOverlappingPanels.closePanels();
            i = R.id.result_description;
        }
        if (resultFragmentPhone.resultBinding != null) {
            fragmentResultSwipeBinding.resultRecommendationsBtt.setNextFocusDownId(i);
            fragmentResultSwipeBinding.resultSearch.setNextFocusDownId(i);
            fragmentResultSwipeBinding.resultOpenInBrowser.setNextFocusDownId(i);
            fragmentResultSwipeBinding.resultShare.setNextFocusDownId(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setRecommendations$lambda$1$2$0$0(final ResultFragmentPhone resultFragmentPhone, final List list, String str, final List list2, View view) {
        FragmentActivity activity = resultFragmentPhone.getActivity();
        if (activity != null) {
            int iIndexOf = CollectionsKt.indexOf((List<? extends String>) list, str);
            String string = resultFragmentPhone.getString(R.string.home_change_provider_img_des);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            SingleSelectionHelper.INSTANCE.showBottomDialog(activity, list, iIndexOf, string, false, new Function0() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda83
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            }, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$$ExternalSyntheticLambda84
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ResultFragmentPhone.setRecommendations$lambda$1$2$0$0$1(this.f$0, list2, list, ((Integer) obj).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setRecommendations$lambda$1$2$0$0$1(ResultFragmentPhone resultFragmentPhone, List list, List list2, int i) {
        resultFragmentPhone.setRecommendations(list, (String) list2.get(i));
        return Unit.INSTANCE;
    }
}
