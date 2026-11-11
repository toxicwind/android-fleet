package com.lagradost.cloudstream3;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import androidx.core.view.ViewGroupKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavGraph;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.BottomNavigationViewKt;
import androidx.preference.PreferenceManager;
import androidx.profileinstaller.ProfileVerifier;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import coil3.util.UtilsKt;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigationrail.NavigationRailView;
import com.google.common.collect.Comparators;
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;
import com.lagradost.cloudstream3.actions.temp.fcast.FcastManager;
import com.lagradost.cloudstream3.databinding.ActivityMainBinding;
import com.lagradost.cloudstream3.databinding.ActivityMainTvBinding;
import com.lagradost.cloudstream3.databinding.BottomResultviewPreviewBinding;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.mvvm.LifecycleKt;
import com.lagradost.cloudstream3.mvvm.Resource;
import com.lagradost.cloudstream3.network.RequestsHelperKt;
import com.lagradost.cloudstream3.plugins.PluginManager;
import com.lagradost.cloudstream3.receivers.VideoDownloadRestartReceiver;
import com.lagradost.cloudstream3.services.SubscriptionWorkManager;
import com.lagradost.cloudstream3.syncproviders.AccountManager;
import com.lagradost.cloudstream3.syncproviders.AuthRepo;
import com.lagradost.cloudstream3.syncproviders.SyncAPI;
import com.lagradost.cloudstream3.syncproviders.SyncRepo;
import com.lagradost.cloudstream3.ui.APIRepository;
import com.lagradost.cloudstream3.ui.SyncWatchType;
import com.lagradost.cloudstream3.ui.WatchType;
import com.lagradost.cloudstream3.ui.account.AccountHelper;
import com.lagradost.cloudstream3.ui.download.DownloadFragmentKt;
import com.lagradost.cloudstream3.ui.home.HomeViewModel;
import com.lagradost.cloudstream3.ui.library.LibraryViewModel;
import com.lagradost.cloudstream3.ui.player.BasicLink;
import com.lagradost.cloudstream3.ui.player.GeneratorPlayer;
import com.lagradost.cloudstream3.ui.player.LinkGenerator;
import com.lagradost.cloudstream3.ui.result.LinearListLayout;
import com.lagradost.cloudstream3.ui.result.ResultData;
import com.lagradost.cloudstream3.ui.result.ResultViewModel2;
import com.lagradost.cloudstream3.ui.result.SyncViewModel;
import com.lagradost.cloudstream3.ui.search.SearchFragment;
import com.lagradost.cloudstream3.ui.search.SearchResultBuilder;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.ui.settings.SettingsGeneral;
import com.lagradost.cloudstream3.ui.setup.SetupFragmentExtensions;
import com.lagradost.cloudstream3.ui.setup.SetupFragmentLanguageKt;
import com.lagradost.cloudstream3.utils.ApkInstaller;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.AtomicList;
import com.lagradost.cloudstream3.utils.BackPressedCallbackHelper;
import com.lagradost.cloudstream3.utils.BackupUtils;
import com.lagradost.cloudstream3.utils.BiometricAuthenticator;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.DataStore;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.DataStoreKt;
import com.lagradost.cloudstream3.utils.Event;
import com.lagradost.cloudstream3.utils.ImageLoader;
import com.lagradost.cloudstream3.utils.InAppUpdater;
import com.lagradost.cloudstream3.utils.SingleSelectionHelper;
import com.lagradost.cloudstream3.utils.SnackbarHelper;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.TvChannelUtils;
import com.lagradost.cloudstream3.utils.UIHelper;
import com.lagradost.cloudstream3.utils.downloader.DownloadQueueManager;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import com.lagradost.safefile.SafeFile;
import j$.net.URLDecoder;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntries;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.full.KClasses;
import kotlin.sequences.Sequence;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.LinkedHashSetSerializer;
import kotlinx.serialization.internal.ReferenceArraySerializer;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.modules.SerializersModule;
import org.mozilla.javascript.ES6Iterator;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000â\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 y2\u00020\u00012\u00020\u00022\u00020\u0003:\u0003yz{B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00072\b\b\u0002\u0010\u0015\u001a\u00020\u0016J\u0018\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019H\u0016J\u0010\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u00132\u0006\u0010 \u001a\u00020!H\u0002J\b\u0010/\u001a\u00020\u0013H\u0014J\b\u00100\u001a\u00020\u0013H\u0014J\u0010\u00101\u001a\u00020\u00162\u0006\u00102\u001a\u000203H\u0016J\u001a\u00104\u001a\u00020\u00162\u0006\u00105\u001a\u00020\u00192\b\u00102\u001a\u0004\u0018\u000103H\u0016J\b\u00106\u001a\u00020\u0013H\u0014J\u0010\u00107\u001a\u00020\u00132\u0006\u00108\u001a\u000209H\u0003J\b\u0010:\u001a\u00020\u0013H\u0014J\u0010\u0010;\u001a\u00020\u00132\u0006\u0010<\u001a\u00020=H\u0014J\u0012\u0010>\u001a\u00020\u00132\b\u0010<\u001a\u0004\u0018\u00010=H\u0002J\u0016\u0010?\u001a\u00020\u0016*\u00020!2\b\b\u0001\u0010@\u001a\u00020\u0019H\u0002J\u0018\u0010C\u001a\u00020\u00162\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020GH\u0002J\u0012\u0010J\u001a\u00020\u00132\b\b\u0002\u0010K\u001a\u00020\u0016H\u0002J\"\u0010^\u001a\u0004\u0018\u00010_2\u0006\u0010`\u001a\u00020a2\u0006\u0010b\u001a\u00020c2\u0006\u0010d\u001a\u00020eH\u0016J\b\u0010f\u001a\u00020\u0013H\u0002J\b\u0010k\u001a\u00020jH\u0002J\u0012\u0010r\u001a\u00020\u00132\b\u0010s\u001a\u0004\u0018\u00010tH\u0014J\b\u0010u\u001a\u00020\u0013H\u0016J\b\u0010v\u001a\u00020\u0013H\u0016J\u000e\u0010w\u001a\u00020\u0016H\u0086@¢\u0006\u0002\u0010xR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\"\u001a\u0004\u0018\u00010#X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R!\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b-\u0010.\u001a\u0004\b+\u0010,R\u000e\u0010A\u001a\u00020BX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u00020IX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010L\u001a\u00020MX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR\u001a\u0010R\u001a\u00020SX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR\u0010\u0010X\u001a\u0004\u0018\u00010YX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010Z\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]R\u0010\u0010g\u001a\u0004\u0018\u00010hX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010i\u001a\u0004\u0018\u00010jX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010l\u001a\u0004\u0018\u00010mX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bn\u0010o\"\u0004\bp\u0010q¨\u0006|"}, d2 = {"Lcom/lagradost/cloudstream3/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/jaredrummler/android/colorpicker/ColorPickerDialogListener;", "Lcom/lagradost/cloudstream3/utils/BiometricAuthenticator$BiometricCallback;", "<init>", "()V", "lastPopup", "Lcom/lagradost/cloudstream3/SearchResponse;", "getLastPopup", "()Lcom/lagradost/cloudstream3/SearchResponse;", "setLastPopup", "(Lcom/lagradost/cloudstream3/SearchResponse;)V", "lastPopupJob", "Lkotlinx/coroutines/Job;", "getLastPopupJob", "()Lkotlinx/coroutines/Job;", "setLastPopupJob", "(Lkotlinx/coroutines/Job;)V", "loadPopup", "", "result", "load", "", "onColorSelected", "dialogId", "", "color", "onDialogDismissed", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "updateNavBar", "destination", "Landroidx/navigation/NavDestination;", "mSessionManager", "Lcom/google/android/gms/cast/framework/SessionManager;", "getMSessionManager", "()Lcom/google/android/gms/cast/framework/SessionManager;", "setMSessionManager", "(Lcom/google/android/gms/cast/framework/SessionManager;)V", "mSessionManagerListener", "Lcom/google/android/gms/cast/framework/SessionManagerListener;", "Lcom/google/android/gms/cast/framework/Session;", "getMSessionManagerListener", "()Lcom/google/android/gms/cast/framework/SessionManagerListener;", "mSessionManagerListener$delegate", "Lkotlin/Lazy;", "onResume", "onPause", "dispatchKeyEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/KeyEvent;", "onKeyDown", "keyCode", "onUserLeaveHint", "showConfirmExitDialog", "settingsManager", "Landroid/content/SharedPreferences;", "onDestroy", "onNewIntent", "intent", "Landroid/content/Intent;", "handleAppIntent", "matchDestination", "destId", "lastNavTime", "", "onNavDestinationSelected", "item", "Landroid/view/MenuItem;", "navController", "Landroidx/navigation/NavController;", "pluginsLock", "Lkotlinx/coroutines/sync/Mutex;", "onAllPluginsLoaded", "success", "viewModel", "Lcom/lagradost/cloudstream3/ui/result/ResultViewModel2;", "getViewModel", "()Lcom/lagradost/cloudstream3/ui/result/ResultViewModel2;", "setViewModel", "(Lcom/lagradost/cloudstream3/ui/result/ResultViewModel2;)V", "syncViewModel", "Lcom/lagradost/cloudstream3/ui/result/SyncViewModel;", "getSyncViewModel", "()Lcom/lagradost/cloudstream3/ui/result/SyncViewModel;", "setSyncViewModel", "(Lcom/lagradost/cloudstream3/ui/result/SyncViewModel;)V", "libraryViewModel", "Lcom/lagradost/cloudstream3/ui/library/LibraryViewModel;", "isLocalList", "()Z", "setLocalList", "(Z)V", "onCreateView", "Landroid/view/View;", "name", "", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "hidePreviewPopupDialog", "bottomPreviewPopup", "Landroid/app/Dialog;", "bottomPreviewBinding", "Lcom/lagradost/cloudstream3/databinding/BottomResultviewPreviewBinding;", "showPreviewPopupDialog", "binding", "Lcom/lagradost/cloudstream3/databinding/ActivityMainBinding;", "getBinding", "()Lcom/lagradost/cloudstream3/databinding/ActivityMainBinding;", "setBinding", "(Lcom/lagradost/cloudstream3/databinding/ActivityMainBinding;)V", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onAuthenticationSuccess", "onAuthenticationError", "checkGithubConnectivity", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "SessionManagerListenerImpl", "TvFocus", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class MainActivity extends AppCompatActivity implements ColorPickerDialogListener, BiometricAuthenticator.BiometricCallback {
    public static final boolean ANIMATED_OUTLINE = false;
    public static final String API_NAME_EXTRA_KEY = "API_NAME_EXTRA_KEY";
    private static final String FILE_DELETE_KEY = "FILES_TO_DELETE_KEY";
    public static final String TAG = "MAINACT";
    private static ActivityResultLauncher<Intent> activityResultLauncher;
    private static String lastError;
    private static String nextSearchQuery;
    private ActivityMainBinding binding;
    private BottomResultviewPreviewBinding bottomPreviewBinding;
    private Dialog bottomPreviewPopup;
    private boolean isLocalList;
    private long lastNavTime;
    private SearchResponse lastPopup;
    private Job lastPopupJob;
    private LibraryViewModel libraryViewModel;
    private SessionManager mSessionManager;

    /* JADX INFO: renamed from: mSessionManagerListener$delegate, reason: from kotlin metadata */
    private final Lazy mSessionManagerListener = LazyKt.lazy(new Function0() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda23
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return MainActivity.mSessionManagerListener_delegate$lambda$0(this.f$0);
        }
    });
    private final Mutex pluginsLock = MutexKt.Mutex$default(false, 1, null);
    public SyncViewModel syncViewModel;
    public ResultViewModel2 viewModel;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Event<Boolean> afterPluginsLoadedEvent = new Event<>();
    private static final Event<Boolean> mainPluginsLoadedEvent = new Event<>();
    private static final Event<Boolean> afterRepositoryLoadedEvent = new Event<>();
    private static final Event<Boolean> bookmarksUpdatedEvent = new Event<>();
    private static final Event<Boolean> reloadHomeEvent = new Event<>();
    private static final Event<Boolean> reloadLibraryEvent = new Event<>();
    private static final Event<Boolean> reloadAccountEvent = new Event<>();

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainActivity$checkGithubConnectivity$1, reason: invalid class name */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.MainActivity", f = "MainActivity.kt", i = {}, l = {2068}, m = "checkGithubConnectivity", n = {}, nl = {2071}, s = {}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MainActivity.this.checkGithubConnectivity(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$10$1(DialogInterface dialogInterface, int i) {
    }

    static final void showConfirmExitDialog$lambda$0(DialogInterface dialogInterface, int i) {
    }

    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0012\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\"J.\u00106\u001a\u00020\u000e2\b\u00107\u001a\u0004\u0018\u0001082\b\u00109\u001a\u0004\u0018\u00010\f2\u0006\u0010:\u001a\u00020\u000e2\n\b\u0002\u0010;\u001a\u0004\u0018\u00010<J\u0010\u0010=\u001a\u00020\u00142\b\u0010>\u001a\u0004\u0018\u00010?R\"\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0086T¢\u0006\u0002\n\u0000R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0017\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R0\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\f0\u001a2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\f0\u001a8B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001c\u0010#\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0011\"\u0004\b%\u0010\u0013R\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020\u000e0'¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0017\u0010*\u001a\b\u0012\u0004\u0012\u00020\u000e0'¢\u0006\b\n\u0000\u001a\u0004\b+\u0010)R\u0017\u0010,\u001a\b\u0012\u0004\u0012\u00020\u000e0'¢\u0006\b\n\u0000\u001a\u0004\b-\u0010)R\u0017\u0010.\u001a\b\u0012\u0004\u0012\u00020\u000e0'¢\u0006\b\n\u0000\u001a\u0004\b/\u0010)R\u0017\u00100\u001a\b\u0012\u0004\u0012\u00020\u000e0'¢\u0006\b\n\u0000\u001a\u0004\b1\u0010)R\u0017\u00102\u001a\b\u0012\u0004\u0012\u00020\u000e0'¢\u0006\b\n\u0000\u001a\u0004\b3\u0010)R\u0017\u00104\u001a\b\u0012\u0004\u0012\u00020\u000e0'¢\u0006\b\n\u0000\u001a\u0004\b5\u0010)¨\u0006@"}, d2 = {"Lcom/lagradost/cloudstream3/MainActivity$Companion;", "", "<init>", "()V", "activityResultLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "getActivityResultLauncher", "()Landroidx/activity/result/ActivityResultLauncher;", "setActivityResultLauncher", "(Landroidx/activity/result/ActivityResultLauncher;)V", "TAG", "", "ANIMATED_OUTLINE", "", "lastError", "getLastError", "()Ljava/lang/String;", "setLastError", "(Ljava/lang/String;)V", "", "context", "Landroid/content/Context;", "FILE_DELETE_KEY", MainActivity.API_NAME_EXTRA_KEY, ES6Iterator.VALUE_PROPERTY, "", "filesToDelete", "getFilesToDelete", "()Ljava/util/Set;", "setFilesToDelete", "(Ljava/util/Set;)V", "deleteFileOnExit", UtilsKt.SCHEME_FILE, "Ljava/io/File;", "nextSearchQuery", "getNextSearchQuery", "setNextSearchQuery", "afterPluginsLoadedEvent", "Lcom/lagradost/cloudstream3/utils/Event;", "getAfterPluginsLoadedEvent", "()Lcom/lagradost/cloudstream3/utils/Event;", "mainPluginsLoadedEvent", "getMainPluginsLoadedEvent", "afterRepositoryLoadedEvent", "getAfterRepositoryLoadedEvent", "bookmarksUpdatedEvent", "getBookmarksUpdatedEvent", "reloadHomeEvent", "getReloadHomeEvent", "reloadLibraryEvent", "getReloadLibraryEvent", "reloadAccountEvent", "getReloadAccountEvent", "handleAppIntentUrl", "activity", "Landroidx/fragment/app/FragmentActivity;", "str", "isWebview", "extraArgs", "Landroid/os/Bundle;", "centerView", "view", "Landroid/view/View;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ActivityResultLauncher<Intent> getActivityResultLauncher() {
            return MainActivity.activityResultLauncher;
        }

        public final void setActivityResultLauncher(ActivityResultLauncher<Intent> activityResultLauncher) {
            MainActivity.activityResultLauncher = activityResultLauncher;
        }

        public final String getLastError() {
            return MainActivity.lastError;
        }

        public final void setLastError(String str) {
            MainActivity.lastError = str;
        }

        public final void setLastError(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (getLastError() != null) {
                return;
            }
            File filesDir = context.getFilesDir();
            Intrinsics.checkNotNullExpressionValue(filesDir, "getFilesDir(...)");
            File fileResolve = FilesKt.resolve(filesDir, "last_error");
            if (fileResolve.exists() && fileResolve.isFile()) {
                Charset charsetDefaultCharset = Charset.defaultCharset();
                Intrinsics.checkNotNullExpressionValue(charsetDefaultCharset, "defaultCharset(...)");
                setLastError(FilesKt.readText(fileResolve, charsetDefaultCharset));
                fileResolve.delete();
                return;
            }
            setLastError((String) null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Set<String> getFilesToDelete() {
            Object objM8936constructorimpl;
            Object objDecodeFromString;
            Context context = CloudStreamApp.INSTANCE.getContext();
            Object obj = null;
            if (context != null) {
                try {
                    String string = DataStore.INSTANCE.getSharedPrefs(context).getString(MainActivity.FILE_DELETE_KEY, null);
                    if (string != null) {
                        AppUtils appUtils = AppUtils.INSTANCE;
                        try {
                            Result.Companion companion = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(new LinkedHashSetSerializer(StringSerializer.INSTANCE));
                        } catch (Throwable th) {
                            Result.Companion companion2 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                        }
                        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                            try {
                                Result.Companion companion3 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(Set.class), null, 2, null));
                            } catch (Throwable th2) {
                                Result.Companion companion4 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                            }
                        }
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        KSerializer kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            try {
                                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                            } catch (SerializationException e) {
                                ArchComponentExtKt.logError(e);
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Set<? extends String>>() { // from class: com.lagradost.cloudstream3.MainActivity$Companion$special$$inlined$getKey$1
                                });
                            } catch (Throwable unused) {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Set<? extends String>>() { // from class: com.lagradost.cloudstream3.MainActivity$Companion$special$$inlined$getKey$1
                                });
                            }
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Set<? extends String>>() { // from class: com.lagradost.cloudstream3.MainActivity$Companion$special$$inlined$getKey$1
                            });
                        }
                        obj = objDecodeFromString;
                    }
                } catch (Exception unused2) {
                }
            }
            Set<String> set = (Set) obj;
            return set == null ? SetsKt.emptySet() : set;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setFilesToDelete(Set<String> set) {
            CloudStreamApp.INSTANCE.setKey(MainActivity.FILE_DELETE_KEY, set);
        }

        public final void deleteFileOnExit(File file) {
            Intrinsics.checkNotNullParameter(file, "file");
            setFilesToDelete(SetsKt.plus(getFilesToDelete(), file.getPath()));
        }

        public final String getNextSearchQuery() {
            return MainActivity.nextSearchQuery;
        }

        public final void setNextSearchQuery(String str) {
            MainActivity.nextSearchQuery = str;
        }

        public final Event<Boolean> getAfterPluginsLoadedEvent() {
            return MainActivity.afterPluginsLoadedEvent;
        }

        public final Event<Boolean> getMainPluginsLoadedEvent() {
            return MainActivity.mainPluginsLoadedEvent;
        }

        public final Event<Boolean> getAfterRepositoryLoadedEvent() {
            return MainActivity.afterRepositoryLoadedEvent;
        }

        public final Event<Boolean> getBookmarksUpdatedEvent() {
            return MainActivity.bookmarksUpdatedEvent;
        }

        public final Event<Boolean> getReloadHomeEvent() {
            return MainActivity.reloadHomeEvent;
        }

        public final Event<Boolean> getReloadLibraryEvent() {
            return MainActivity.reloadLibraryEvent;
        }

        public final Event<Boolean> getReloadAccountEvent() {
            return MainActivity.reloadAccountEvent;
        }

        public static /* synthetic */ boolean handleAppIntentUrl$default(Companion companion, FragmentActivity fragmentActivity, String str, boolean z, Bundle bundle, int i, Object obj) {
            if ((i & 8) != 0) {
                bundle = null;
            }
            return companion.handleAppIntentUrl(fragmentActivity, str, z, bundle);
        }

        private static final URI handleAppIntentUrl$lambda$0$safeURI(final String str) {
            return (URI) ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.MainActivity$Companion$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MainActivity.Companion.handleAppIntentUrl$lambda$0$safeURI$0(str);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final URI handleAppIntentUrl$lambda$0$safeURI$0(String str) {
            return new URI(str);
        }

        public final boolean handleAppIntentUrl(FragmentActivity activity, final String str, boolean isWebview, Bundle extraArgs) throws UnsupportedEncodingException {
            String string;
            NavigationRailView navigationRailView;
            BottomNavigationView bottomNavigationView;
            if (str != null && activity != null) {
                if (StringsKt.startsWith$default(str, "https://cs.repo", false, 2, (Object) null)) {
                    String str2 = Utils.HTTPS + StringsKt.substringAfter$default(str, "?", (String) null, 2, (Object) null);
                    System.out.println((Object) ("Repository url: " + str2));
                    AppContextUtils.INSTANCE.loadRepository(activity, str2);
                    return true;
                }
                if (StringsKt.contains$default((CharSequence) str, (CharSequence) AccountManager.APP_STRING, false, 2, (Object) null)) {
                    for (AuthRepo authRepo : AccountManager.INSTANCE.getAllApis()) {
                        if (authRepo.isValidRedirectUrl(str)) {
                            Coroutines.INSTANCE.ioSafe(activity, new MainActivity$Companion$handleAppIntentUrl$1$1(str, authRepo, null));
                            return true;
                        }
                    }
                    if (Intrinsics.areEqual(str, "cloudstreamapp:")) {
                        Coroutines.INSTANCE.ioSafe(activity, new MainActivity$Companion$handleAppIntentUrl$1$2(activity, null));
                    }
                } else {
                    URI uriHandleAppIntentUrl$lambda$0$safeURI = handleAppIntentUrl$lambda$0$safeURI(str);
                    if (Intrinsics.areEqual(uriHandleAppIntentUrl$lambda$0$safeURI != null ? uriHandleAppIntentUrl$lambda$0$safeURI.getScheme() : null, AccountManager.APP_STRING_REPO)) {
                        AppContextUtils.INSTANCE.loadRepository(activity, StringsKt.replaceFirst$default(str, AccountManager.APP_STRING_REPO, "https", false, 4, (Object) null));
                        return true;
                    }
                    URI uriHandleAppIntentUrl$lambda$0$safeURI2 = handleAppIntentUrl$lambda$0$safeURI(str);
                    if (Intrinsics.areEqual(uriHandleAppIntentUrl$lambda$0$safeURI2 != null ? uriHandleAppIntentUrl$lambda$0$safeURI2.getScheme() : null, AccountManager.APP_STRING_SEARCH)) {
                        String strSubstringAfter$default = StringsKt.substringAfter$default(str, "cloudstreamsearch://", (String) null, 2, (Object) null);
                        Companion companion = MainActivity.INSTANCE;
                        try {
                            strSubstringAfter$default = URLDecoder.decode(strSubstringAfter$default, "UTF-8");
                        } catch (Throwable th) {
                            ArchComponentExtKt.logError(th);
                        }
                        companion.setNextSearchQuery(strSubstringAfter$default);
                        if (activity != null && (bottomNavigationView = (BottomNavigationView) activity.findViewById(R.id.nav_view)) != null) {
                            bottomNavigationView.setSelectedItemId(R.id.navigation_search);
                        }
                        if (activity != null && (navigationRailView = (NavigationRailView) activity.findViewById(R.id.nav_rail_view)) != null) {
                            navigationRailView.setSelectedItemId(R.id.navigation_search);
                        }
                    } else {
                        URI uriHandleAppIntentUrl$lambda$0$safeURI3 = handleAppIntentUrl$lambda$0$safeURI(str);
                        if (!Intrinsics.areEqual(uriHandleAppIntentUrl$lambda$0$safeURI3 != null ? uriHandleAppIntentUrl$lambda$0$safeURI3.getScheme() : null, AccountManager.APP_STRING_PLAYER)) {
                            URI uriHandleAppIntentUrl$lambda$0$safeURI4 = handleAppIntentUrl$lambda$0$safeURI(str);
                            if (Intrinsics.areEqual(uriHandleAppIntentUrl$lambda$0$safeURI4 != null ? uriHandleAppIntentUrl$lambda$0$safeURI4.getScheme() : null, AccountManager.APP_STRING_RESUME_WATCHING)) {
                                Integer intOrNull = StringsKt.toIntOrNull(StringsKt.substringAfter$default(str, "cloudstreamcontinuewatching://", (String) null, 2, (Object) null));
                                if (intOrNull == null) {
                                    return false;
                                }
                                Coroutines.INSTANCE.ioSafe(activity, new MainActivity$Companion$handleAppIntentUrl$1$3(activity, intOrNull.intValue(), null));
                            } else {
                                if (StringsKt.startsWith$default(str, AccountManager.APP_STRING_SHARE, false, 2, (Object) null)) {
                                    try {
                                        List listSplit$default = StringsKt.split$default((CharSequence) StringsKt.substringAfter$default(str, "csshare:", (String) null, 2, (Object) null), new String[]{"?"}, false, 2, 2, (Object) null);
                                        AppContextUtils.loadResult$default(AppContextUtils.INSTANCE, activity, new String(MainAPIKt.base64DecodeArray((String) listSplit$default.get(1)), Charsets.UTF_8), new String(MainAPIKt.base64DecodeArray((String) listSplit$default.get(0)), Charsets.UTF_8), "", 0, 0, 24, null);
                                        return true;
                                    } catch (Exception unused) {
                                        CommonActivity.INSTANCE.showToast("Invalid Uri", (Integer) 0);
                                        return false;
                                    }
                                }
                                if (!isWebview) {
                                    if (StringsKt.startsWith$default(str, DownloadFragmentKt.DOWNLOAD_NAVIGATE_TO, false, 2, (Object) null)) {
                                        UIHelper.navigate$default(UIHelper.INSTANCE, activity, R.id.navigation_downloads, null, null, 6, null);
                                        return true;
                                    }
                                    String str3 = (extraArgs == null || (string = extraArgs.getString(MainActivity.API_NAME_EXTRA_KEY)) == null || StringsKt.isBlank(string)) ? null : string;
                                    if (str3 != null) {
                                        AppContextUtils.loadResult$default(AppContextUtils.INSTANCE, activity, str, str3, "", 0, 0, 24, null);
                                        return true;
                                    }
                                    MainAPI mainAPI = (MainAPI) CollectionsKt.firstOrNull((List) APIHolder.INSTANCE.getApis().filter(new Function1() { // from class: com.lagradost.cloudstream3.MainActivity$Companion$$ExternalSyntheticLambda1
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj) {
                                            return Boolean.valueOf(MainActivity.Companion.handleAppIntentUrl$lambda$0$2(str, (MainAPI) obj));
                                        }
                                    }));
                                    if (mainAPI != null) {
                                        AppContextUtils.loadResult$default(AppContextUtils.INSTANCE, activity, str, mainAPI.getName(), "", 0, 0, 24, null);
                                        return true;
                                    }
                                }
                            }
                        } else {
                            Uri uri = Uri.parse(str);
                            String queryParameter = uri.getQueryParameter("name");
                            String strDecode = URLDecoder.decode(uri.getAuthority(), "UTF-8");
                            int i = R.id.global_to_navigation_player;
                            GeneratorPlayer.Companion companion2 = GeneratorPlayer.INSTANCE;
                            Intrinsics.checkNotNull(strDecode);
                            UIHelper.navigate$default(UIHelper.INSTANCE, activity, i, GeneratorPlayer.Companion.newInstance$default(companion2, new LinkGenerator(CollectionsKt.listOf(new BasicLink(strDecode, queryParameter)), true, null, Integer.valueOf(strDecode.hashCode()), 4, null), 0, null, 4, null), null, 4, null);
                        }
                    }
                }
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean handleAppIntentUrl$lambda$0$2(String str, MainAPI it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return StringsKt.startsWith$default(str, it.getMainUrl(), false, 2, (Object) null);
        }

        public final void centerView(View view) {
            if (view == null) {
                return;
            }
            try {
                Log.v(MainActivity.TAG, "centerView: " + view);
                Rect rect = new Rect(0, 0, 0, 0);
                view.getDrawingRect(rect);
                int iCenterX = rect.centerX();
                int iCenterY = rect.centerY();
                int iWidth = rect.width() / 2;
                int screenHeight = CommonActivity.INSTANCE.getScreenHeight() / 2;
                view.requestRectangleOnScreen(new Rect(iCenterX - iWidth, iCenterY - screenHeight, iCenterX + iWidth, iCenterY + screenHeight), false);
            } catch (Throwable unused) {
            }
        }
    }

    public final SearchResponse getLastPopup() {
        return this.lastPopup;
    }

    public final void setLastPopup(SearchResponse searchResponse) {
        this.lastPopup = searchResponse;
    }

    public final Job getLastPopupJob() {
        return this.lastPopupJob;
    }

    public final void setLastPopupJob(Job job) {
        this.lastPopupJob = job;
    }

    public static /* synthetic */ void loadPopup$default(MainActivity mainActivity, SearchResponse searchResponse, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        mainActivity.loadPopup(searchResponse, z);
    }

    public final void loadPopup(SearchResponse result, boolean load) {
        Job jobLoadSmall;
        Intrinsics.checkNotNullParameter(result, "result");
        this.lastPopup = result;
        String strSyncName = getSyncViewModel().syncName(result.getApiName());
        if ((result instanceof SyncAPI.LibraryItem) && strSyncName != null) {
            this.isLocalList = false;
            getSyncViewModel().setSync(strSyncName, ((SyncAPI.LibraryItem) result).getSyncId());
            getSyncViewModel().updateMetaAndUser();
        } else {
            this.isLocalList = true;
            getSyncViewModel().clear();
        }
        Job job = this.lastPopupJob;
        if (job != null) {
            Job.CC.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        if (!load) {
            jobLoadSmall = getViewModel().loadSmall(result);
        } else {
            jobLoadSmall = getViewModel().load(this, result.getUrl(), result.getApiName(), false, AppContextUtils.INSTANCE.getApiDubstatusSettings(this).contains(DubStatus.Dubbed) ? DubStatus.Dubbed : DubStatus.Subbed, null, (64 & 64) != 0);
        }
        this.lastPopupJob = jobLoadSmall;
    }

    @Override // com.jaredrummler.android.colorpicker.ColorPickerDialogListener
    public void onColorSelected(int dialogId, int color) {
        CommonActivity.INSTANCE.getOnColorSelectedEvent().invoke(new Pair<>(Integer.valueOf(dialogId), Integer.valueOf(color)));
    }

    @Override // com.jaredrummler.android.colorpicker.ColorPickerDialogListener
    public void onDialogDismissed(int dialogId) {
        CommonActivity.INSTANCE.getOnDialogDismissedEvent().invoke(Integer.valueOf(dialogId));
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        CommonActivity.INSTANCE.updateLocale(this);
        CommonActivity.INSTANCE.updateTheme(this);
        Fragment fragmentFindFragmentById = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        Intrinsics.checkNotNull(fragmentFindFragmentById, "null cannot be cast to non-null type androidx.navigation.fragment.NavHostFragment");
        NavDestination currentDestination = ((NavHostFragment) fragmentFindFragmentById).getNavController().getCurrentDestination();
        if (currentDestination != null) {
            updateNavBar(currentDestination);
        }
    }

    private final void updateNavBar(NavDestination destination) {
        LinearLayout linearLayout;
        UIHelper.INSTANCE.hideKeyboard(this);
        ActivityMainBinding activityMainBinding = this.binding;
        if (activityMainBinding != null && (linearLayout = activityMainBinding.castMiniControllerHolder) != null) {
            linearLayout.setVisibility(!CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(R.id.navigation_results_phone), Integer.valueOf(R.id.navigation_results_tv), Integer.valueOf(R.id.navigation_player)}).contains(Integer.valueOf(destination.getId())) ? 0 : 8);
        }
        boolean zContains = CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(R.id.navigation_home), Integer.valueOf(R.id.navigation_search), Integer.valueOf(R.id.navigation_library), Integer.valueOf(R.id.navigation_downloads), Integer.valueOf(R.id.navigation_settings), Integer.valueOf(R.id.navigation_download_child), Integer.valueOf(R.id.navigation_download_queue), Integer.valueOf(R.id.navigation_subtitles), Integer.valueOf(R.id.navigation_chrome_subtitles), Integer.valueOf(R.id.navigation_settings_player), Integer.valueOf(R.id.navigation_settings_updates), Integer.valueOf(R.id.navigation_settings_ui), Integer.valueOf(R.id.navigation_settings_account), Integer.valueOf(R.id.navigation_settings_providers), Integer.valueOf(R.id.navigation_settings_general), Integer.valueOf(R.id.navigation_settings_extensions), Integer.valueOf(R.id.navigation_settings_plugins), Integer.valueOf(R.id.navigation_test_providers)}).contains(Integer.valueOf(destination.getId()));
        ActivityMainBinding activityMainBinding2 = this.binding;
        if (activityMainBinding2 != null) {
            NavigationRailView navRailView = activityMainBinding2.navRailView;
            Intrinsics.checkNotNullExpressionValue(navRailView, "navRailView");
            navRailView.setVisibility(zContains && Globals.INSTANCE.isLandscape() ? 0 : 8);
            BottomNavigationView navView = activityMainBinding2.navView;
            Intrinsics.checkNotNullExpressionValue(navView, "navView");
            navView.setVisibility(zContains && !Globals.INSTANCE.isLandscape() ? 0 : 8);
            FragmentContainerView fragmentContainerView = activityMainBinding2.navHostFragment;
            int dimensionPixelSize = fragmentContainerView.getResources().getDimensionPixelSize(R.dimen.nav_rail_view_width);
            ViewGroup.LayoutParams layoutParams = activityMainBinding2.navHostFragment.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            if (!zContains || !Globals.INSTANCE.isLandscape() || !Globals.INSTANCE.isLayout(6)) {
                dimensionPixelSize = 0;
            }
            marginLayoutParams.setMarginStart(dimensionPixelSize);
            fragmentContainerView.setLayoutParams(marginLayoutParams);
            int id = destination.getId();
            if (CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(R.id.navigation_downloads), Integer.valueOf(R.id.navigation_download_child), Integer.valueOf(R.id.navigation_download_queue)}).contains(Integer.valueOf(id))) {
                activityMainBinding2.navRailView.getMenu().findItem(R.id.navigation_downloads).setChecked(true);
                activityMainBinding2.navView.getMenu().findItem(R.id.navigation_downloads).setChecked(true);
            } else if (CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(R.id.navigation_settings), Integer.valueOf(R.id.navigation_subtitles), Integer.valueOf(R.id.navigation_chrome_subtitles), Integer.valueOf(R.id.navigation_settings_player), Integer.valueOf(R.id.navigation_settings_updates), Integer.valueOf(R.id.navigation_settings_ui), Integer.valueOf(R.id.navigation_settings_account), Integer.valueOf(R.id.navigation_settings_providers), Integer.valueOf(R.id.navigation_settings_general), Integer.valueOf(R.id.navigation_settings_extensions), Integer.valueOf(R.id.navigation_settings_plugins), Integer.valueOf(R.id.navigation_test_providers)}).contains(Integer.valueOf(id))) {
                activityMainBinding2.navRailView.getMenu().findItem(R.id.navigation_settings).setChecked(true);
                activityMainBinding2.navView.getMenu().findItem(R.id.navigation_settings).setChecked(true);
            }
        }
    }

    public final SessionManager getMSessionManager() {
        return this.mSessionManager;
    }

    public final void setMSessionManager(SessionManager sessionManager) {
        this.mSessionManager = sessionManager;
    }

    private final SessionManagerListener<Session> getMSessionManagerListener() {
        return (SessionManagerListener) this.mSessionManagerListener.getValue();
    }

    static final SessionManagerListenerImpl mSessionManagerListener_delegate$lambda$0(MainActivity mainActivity) {
        return mainActivity.new SessionManagerListenerImpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\rH\u0016J\u0018\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\nH\u0016¨\u0006\u0018"}, d2 = {"Lcom/lagradost/cloudstream3/MainActivity$SessionManagerListenerImpl;", "Lcom/google/android/gms/cast/framework/SessionManagerListener;", "Lcom/google/android/gms/cast/framework/Session;", "<init>", "(Lcom/lagradost/cloudstream3/MainActivity;)V", "onSessionStarting", "", "session", "onSessionStarted", "sessionId", "", "onSessionStartFailed", CmcdData.OBJECT_TYPE_INIT_SEGMENT, "", "onSessionEnding", "onSessionResumed", "wasSuspended", "", "onSessionResumeFailed", "onSessionSuspended", "onSessionEnded", MediaRouteProviderProtocol.SERVICE_DATA_ERROR, "onSessionResuming", CmcdData.STREAMING_FORMAT_SS, "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    final class SessionManagerListenerImpl implements SessionManagerListener<Session> {
        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionEnded(Session session, int error) {
            Intrinsics.checkNotNullParameter(session, "session");
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionEnding(Session session) {
            Intrinsics.checkNotNullParameter(session, "session");
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionResumeFailed(Session session, int i) {
            Intrinsics.checkNotNullParameter(session, "session");
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionResuming(Session session, String s) {
            Intrinsics.checkNotNullParameter(session, "session");
            Intrinsics.checkNotNullParameter(s, "s");
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionStartFailed(Session session, int i) {
            Intrinsics.checkNotNullParameter(session, "session");
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionStarting(Session session) {
            Intrinsics.checkNotNullParameter(session, "session");
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionSuspended(Session session, int i) {
            Intrinsics.checkNotNullParameter(session, "session");
        }

        public SessionManagerListenerImpl() {
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionStarted(Session session, String sessionId) {
            Intrinsics.checkNotNullParameter(session, "session");
            Intrinsics.checkNotNullParameter(sessionId, "sessionId");
            MainActivity.this.invalidateOptionsMenu();
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionResumed(Session session, boolean wasSuspended) {
            Intrinsics.checkNotNullParameter(session, "session");
            MainActivity.this.invalidateOptionsMenu();
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainActivity$onResume$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C07091 extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
        C07091(Object obj) {
            super(1, obj, MainActivity.class, "onAllPluginsLoaded", "onAllPluginsLoaded(Z)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            ((MainActivity) this.receiver).onAllPluginsLoaded(z);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        SessionManager sessionManager;
        super.onResume();
        afterPluginsLoadedEvent.plusAssign(new C07091(this));
        CommonActivity.INSTANCE.setActivityInstance(this);
        try {
            if (!AppContextUtils.INSTANCE.isCastApiAvailable(this) || (sessionManager = this.mSessionManager) == null) {
                return;
            }
            sessionManager.addSessionManagerListener(getMSessionManagerListener());
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        SessionManager sessionManager;
        super.onPause();
        ApkInstaller.DelayedInstaller delayedInstaller = ApkInstaller.INSTANCE.getDelayedInstaller();
        if (delayedInstaller != null && delayedInstaller.startInstallation()) {
            Toast.makeText(this, R.string.update_started, 1).show();
        }
        try {
            if (!AppContextUtils.INSTANCE.isCastApiAvailable(this) || (sessionManager = this.mSessionManager) == null) {
                return;
            }
            sessionManager.removeSessionManagerListener(getMSessionManagerListener());
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        Boolean boolDispatchKeyEvent = CommonActivity.INSTANCE.dispatchKeyEvent(this, event);
        return boolDispatchKeyEvent != null ? boolDispatchKeyEvent.booleanValue() : super.dispatchKeyEvent(event);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Boolean boolOnKeyDown = CommonActivity.INSTANCE.onKeyDown(this, keyCode, event);
        return boolOnKeyDown != null ? boolOnKeyDown.booleanValue() : super.onKeyDown(keyCode, event);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        CommonActivity.INSTANCE.onUserLeaveHint(this);
    }

    private final void showConfirmExitDialog(final SharedPreferences settingsManager) {
        int i = settingsManager.getInt(getString(R.string.confirm_exit_key), -1);
        if (i == 1 || (i == -1 && Globals.INSTANCE.isLayout(1))) {
            if (Globals.INSTANCE.isLayout(2)) {
                System.exit(0);
                throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
            }
            finish();
            return;
        }
        View viewInflate = getLayoutInflater().inflate(R.layout.confirm_exit_dialog, (ViewGroup) null);
        View viewFindViewById = viewInflate.findViewById(R.id.checkboxDontShowAgain);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        final CheckBox checkBox = (CheckBox) viewFindViewById;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(viewInflate).setTitle(R.string.confirm_exit_dialog).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda13
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                MainActivity.showConfirmExitDialog$lambda$0(dialogInterface, i2);
            }
        }).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda14
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                MainActivity.showConfirmExitDialog$lambda$1(checkBox, settingsManager, this, dialogInterface, i2);
            }
        });
        AppContextUtils appContextUtils = AppContextUtils.INSTANCE;
        AlertDialog alertDialogShow = builder.show();
        Intrinsics.checkNotNullExpressionValue(alertDialogShow, "show(...)");
        AppContextUtils.setDefaultFocus$default(appContextUtils, alertDialogShow, 0, 1, null);
    }

    static final void showConfirmExitDialog$lambda$1(CheckBox checkBox, SharedPreferences sharedPreferences, MainActivity mainActivity, DialogInterface dialogInterface, int i) {
        if (checkBox.isChecked()) {
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putInt(mainActivity.getString(R.string.confirm_exit_key), 1);
            editorEdit.commit();
        }
        if (Globals.INSTANCE.isLayout(2)) {
            System.exit(0);
            throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
        }
        mainActivity.finish();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        for (String str : INSTANCE.getFilesToDelete()) {
            if (FilesKt.deleteRecursively(new File(str))) {
                Log.d(TAG, "Deleted temporary file: " + str);
            } else {
                Log.d(TAG, "Failed to delete temporary file: " + str);
            }
        }
        INSTANCE.setFilesToDelete(SetsKt.emptySet());
        Intent intent = new Intent();
        intent.setAction("restart_service");
        intent.setClass(this, VideoDownloadRestartReceiver.class);
        sendBroadcast(intent);
        afterPluginsLoadedEvent.minusAssign(new AnonymousClass2(this));
        BackPressedCallbackHelper.INSTANCE.detachBackPressedCallback(this, "MainActivityDefault");
        super.onDestroy();
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainActivity$onDestroy$2, reason: invalid class name */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
        AnonymousClass2(Object obj) {
            super(1, obj, MainActivity.class, "onAllPluginsLoaded", "onAllPluginsLoaded(Z)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            ((MainActivity) this.receiver).onAllPluginsLoaded(z);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) throws UnsupportedEncodingException {
        Intrinsics.checkNotNullParameter(intent, "intent");
        handleAppIntent(intent);
        super.onNewIntent(intent);
    }

    private final void handleAppIntent(Intent intent) throws UnsupportedEncodingException {
        if (intent == null) {
            return;
        }
        String dataString = intent.getDataString();
        AppContextUtils.INSTANCE.loadCache(this);
        INSTANCE.handleAppIntentUrl(this, dataString, false, intent.getExtras());
    }

    private final boolean matchDestination(NavDestination navDestination, int i) {
        Iterator<NavDestination> it = NavDestination.INSTANCE.getHierarchy(navDestination).iterator();
        while (it.hasNext()) {
            if (it.next().getId() == i) {
                return true;
            }
        }
        return false;
    }

    private final boolean onNavDestinationSelected(MenuItem item, NavController navController) {
        Integer numValueOf;
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.lastNavTime < 400) {
            return false;
        }
        this.lastNavTime = jCurrentTimeMillis;
        int itemId = item.getItemId();
        NavDestination currentDestination = navController.getCurrentDestination();
        if (currentDestination != null && currentDestination.getId() == itemId) {
            return false;
        }
        if (itemId == R.id.navigation_search || itemId == R.id.navigation_library) {
            numValueOf = Integer.valueOf(R.id.main_search);
        } else {
            numValueOf = itemId == R.id.navigation_downloads ? Integer.valueOf(R.id.download_appbar) : null;
        }
        if (numValueOf != null && Globals.INSTANCE.isLayout(6)) {
            ActivityMainBinding activityMainBinding = this.binding;
            NavigationRailView navigationRailView = activityMainBinding != null ? activityMainBinding.navRailView : null;
            if (navigationRailView != null) {
                navigationRailView.setNextFocusRightId(numValueOf.intValue());
                Integer[] numArr = {Integer.valueOf(R.id.navigation_downloads), Integer.valueOf(R.id.navigation_home), Integer.valueOf(R.id.navigation_search), Integer.valueOf(R.id.navigation_library), Integer.valueOf(R.id.navigation_settings)};
                for (int i = 0; i < 5; i++) {
                    View viewFindViewById = navigationRailView.findViewById(numArr[i].intValue());
                    if (viewFindViewById != null) {
                        viewFindViewById.setNextFocusRightId(numValueOf.intValue());
                    }
                }
            }
        }
        NavOptions.Builder popExitAnim = new NavOptions.Builder().setLaunchSingleTop(true).setRestoreState(true).setEnterAnim(R.anim.enter_anim).setExitAnim(R.anim.exit_anim).setPopEnterAnim(R.anim.pop_enter).setPopExitAnim(R.anim.pop_exit);
        if ((item.getOrder() & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            popExitAnim.setPopUpTo(NavGraph.INSTANCE.findStartDestination(navController.getGraph()).getId(), false, true);
        }
        try {
            navController.navigate(itemId, (Bundle) null, popExitAnim.build());
            NavDestination currentDestination2 = navController.getCurrentDestination();
            return currentDestination2 != null && matchDestination(currentDestination2, itemId);
        } catch (IllegalArgumentException e) {
            Log.e("NavigationError", "Failed to navigate: " + e.getMessage());
            return false;
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainActivity$onAllPluginsLoaded$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/MainActivity;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.MainActivity$onAllPluginsLoaded$1", f = "MainActivity.kt", i = {0, 0}, l = {2083}, m = "invokeSuspend", n = {"$this$withLock_u24default$iv", "$i$f$withLock"}, nl = {2084}, s = {"L$0", "I$0"}, v = 2)
    static final class C07081 extends SuspendLambda implements Function3<CoroutineScope, MainActivity, Continuation<? super Unit>, Object> {
        int I$0;
        Object L$0;
        Object L$1;
        int label;

        C07081(Continuation<? super C07081> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, MainActivity mainActivity, Continuation<? super Unit> continuation) {
            return MainActivity.this.new C07081(continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Mutex mutex;
            final MainActivity mainActivity;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Mutex mutex2 = MainActivity.this.pluginsLock;
                MainActivity mainActivity2 = MainActivity.this;
                this.L$0 = mutex2;
                this.L$1 = mainActivity2;
                this.I$0 = 0;
                this.label = 1;
                if (mutex2.lock(null, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                mutex = mutex2;
                mainActivity = mainActivity2;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                mainActivity = (MainActivity) this.L$1;
                mutex = (Mutex) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            try {
                APIHolder.INSTANCE.getAllProviders().withLock(new Function0() { // from class: com.lagradost.cloudstream3.MainActivity$onAllPluginsLoaded$1$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MainActivity.C07081.invokeSuspend$lambda$0$0(mainActivity);
                    }
                });
                Unit unit = Unit.INSTANCE;
                return Unit.INSTANCE;
            } finally {
                mutex.unlock(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Code duplicated, block: B:21:0x006a  */
        /* JADX WARN: Code duplicated, block: B:28:0x0080 A[Catch: Exception -> 0x0092, TRY_LEAVE, TryCatch #5 {Exception -> 0x0092, blocks: (B:4:0x0007, B:7:0x0013, B:12:0x0038, B:19:0x0064, B:22:0x006b, B:28:0x0080, B:27:0x007b, B:18:0x005a, B:11:0x002e, B:15:0x003f, B:8:0x0015, B:24:0x006f), top: B:60:0x0007, inners: #3, #4, #6 }] */
        /* JADX WARN: Code duplicated, block: B:61:0x006f A[EXC_TOP_SPLITTER, SYNTHETIC] */
        public static final Unit invokeSuspend$lambda$0$0(MainActivity mainActivity) {
            Object objDecodeFromString;
            MainAPI next;
            Object objM8936constructorimpl;
            KSerializer kSerializer;
            try {
                try {
                    String string = DataStore.INSTANCE.getSharedPrefs(mainActivity).getString(DataStoreKt.USER_PROVIDER_API, null);
                    if (string == null) {
                        objDecodeFromString = null;
                    } else {
                        AppUtils appUtils = AppUtils.INSTANCE;
                        try {
                            Result.Companion companion = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(SettingsGeneral.CustomSite.class), SettingsGeneral.CustomSite.INSTANCE.serializer()));
                        } catch (Throwable th) {
                            Result.Companion companion2 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                        }
                        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) == null) {
                            if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                                objM8936constructorimpl = null;
                            }
                            kSerializer = (KSerializer) objM8936constructorimpl;
                            if (kSerializer != null) {
                                try {
                                    objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                                } catch (SerializationException e) {
                                    ArchComponentExtKt.logError(e);
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<SettingsGeneral.CustomSite[]>() { // from class: com.lagradost.cloudstream3.MainActivity$onAllPluginsLoaded$1$invokeSuspend$lambda$0$0$$inlined$getKey$1
                                    });
                                } catch (Throwable unused) {
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<SettingsGeneral.CustomSite[]>() { // from class: com.lagradost.cloudstream3.MainActivity$onAllPluginsLoaded$1$invokeSuspend$lambda$0$0$$inlined$getKey$1
                                    });
                                }
                            } else {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<SettingsGeneral.CustomSite[]>() { // from class: com.lagradost.cloudstream3.MainActivity$onAllPluginsLoaded$1$invokeSuspend$lambda$0$0$$inlined$getKey$1
                                });
                            }
                        } else {
                            try {
                                Result.Companion companion3 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(SettingsGeneral.CustomSite[].class), null, 2, null));
                            } catch (Throwable th2) {
                                Result.Companion companion4 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                            }
                            if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                                objM8936constructorimpl = null;
                            }
                            kSerializer = (KSerializer) objM8936constructorimpl;
                            if (kSerializer != null) {
                                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                            } else {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<SettingsGeneral.CustomSite[]>() { // from class: com.lagradost.cloudstream3.MainActivity$onAllPluginsLoaded$1$invokeSuspend$lambda$0$0$$inlined$getKey$1
                                });
                            }
                        }
                    }
                } catch (Exception unused2) {
                    objDecodeFromString = null;
                }
                SettingsGeneral.CustomSite[] customSiteArr = (SettingsGeneral.CustomSite[]) objDecodeFromString;
                if (customSiteArr != null) {
                    for (SettingsGeneral.CustomSite customSite : customSiteArr) {
                        Iterator<MainAPI> it = APIHolder.INSTANCE.getAllProviders().iterator();
                        do {
                            if (!it.hasNext()) {
                                next = null;
                                break;
                            }
                            next = it.next();
                        } while (!Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(next.getClass()).getSimpleName(), customSite.getParentClassName()));
                        MainAPI mainAPI = next;
                        if (mainAPI != null) {
                            AtomicList allProviders = APIHolder.INSTANCE.getAllProviders();
                            Object objCreateInstance = KClasses.createInstance(Reflection.getOrCreateKotlinClass(mainAPI.getClass()));
                            MainAPI mainAPI2 = (MainAPI) objCreateInstance;
                            mainAPI2.setName(customSite.getName());
                            mainAPI2.setLang(customSite.getLang());
                            mainAPI2.setMainUrl(StringsKt.trimEnd(customSite.getUrl(), '/'));
                            mainAPI2.setCanBeOverridden(false);
                            allProviders.add(objCreateInstance);
                        }
                    }
                }
                APIHolder.INSTANCE.setApis(APIHolder.INSTANCE.getAllProviders().distinctBy(new Function1() { // from class: com.lagradost.cloudstream3.MainActivity$onAllPluginsLoaded$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return MainActivity.C07081.invokeSuspend$lambda$0$0$1((MainAPI) obj);
                    }
                }));
                APIHolder.INSTANCE.setApiMap(null);
            } catch (Exception e2) {
                ArchComponentExtKt.logError(e2);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Object invokeSuspend$lambda$0$0$1(MainAPI mainAPI) {
            return mainAPI.getLang() + mainAPI.getName() + mainAPI.getMainUrl() + Reflection.getOrCreateKotlinClass(mainAPI.getClass()).getQualifiedName();
        }
    }

    static /* synthetic */ void onAllPluginsLoaded$default(MainActivity mainActivity, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        mainActivity.onAllPluginsLoaded(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onAllPluginsLoaded(boolean success) {
        Coroutines.INSTANCE.ioSafe(this, new C07081(null));
    }

    public final ResultViewModel2 getViewModel() {
        ResultViewModel2 resultViewModel2 = this.viewModel;
        if (resultViewModel2 != null) {
            return resultViewModel2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        return null;
    }

    public final void setViewModel(ResultViewModel2 resultViewModel2) {
        Intrinsics.checkNotNullParameter(resultViewModel2, "<set-?>");
        this.viewModel = resultViewModel2;
    }

    public final SyncViewModel getSyncViewModel() {
        SyncViewModel syncViewModel = this.syncViewModel;
        if (syncViewModel != null) {
            return syncViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("syncViewModel");
        return null;
    }

    public final void setSyncViewModel(SyncViewModel syncViewModel) {
        Intrinsics.checkNotNullParameter(syncViewModel, "<set-?>");
        this.syncViewModel = syncViewModel;
    }

    /* JADX INFO: renamed from: isLocalList, reason: from getter */
    public final boolean getIsLocalList() {
        return this.isLocalList;
    }

    public final void setLocalList(boolean z) {
        this.isLocalList = z;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.view.LayoutInflater.Factory
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        MainActivity mainActivity = this;
        setViewModel((ResultViewModel2) new ViewModelProvider(mainActivity).get(ResultViewModel2.class));
        setSyncViewModel((SyncViewModel) new ViewModelProvider(mainActivity).get(SyncViewModel.class));
        return super.onCreateView(name, context, attrs);
    }

    private final void hidePreviewPopupDialog() {
        UIHelper.INSTANCE.dismissSafe(this.bottomPreviewPopup, this);
        Job job = this.lastPopupJob;
        if (job != null) {
            Job.CC.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.lastPopupJob = null;
        this.bottomPreviewPopup = null;
        this.bottomPreviewBinding = null;
    }

    private final BottomResultviewPreviewBinding showPreviewPopupDialog() {
        BottomSheetDialog dialog;
        int i;
        BottomResultviewPreviewBinding bottomResultviewPreviewBinding = this.bottomPreviewBinding;
        if (bottomResultviewPreviewBinding != null) {
            return bottomResultviewPreviewBinding;
        }
        if (Globals.INSTANCE.isLayout(1)) {
            dialog = new BottomSheetDialog(this);
            i = R.layout.bottom_resultview_preview;
        } else {
            dialog = new Dialog(this, R.style.DialogHalfFullscreen);
            i = R.layout.bottom_resultview_preview_tv;
            Window window = dialog.getWindow();
            if (window != null) {
                window.setGravity(8388629);
            }
        }
        View viewInflate = getLayoutInflater().inflate(i, (ViewGroup) null, false);
        BottomResultviewPreviewBinding bottomResultviewPreviewBindingBind = BottomResultviewPreviewBinding.bind(viewInflate);
        Intrinsics.checkNotNullExpressionValue(bottomResultviewPreviewBindingBind, "bind(...)");
        this.bottomPreviewBinding = bottomResultviewPreviewBindingBind;
        dialog.setContentView(viewInflate);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda12
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                MainActivity.showPreviewPopupDialog$lambda$0$0(this.f$0, dialogInterface);
            }
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        this.bottomPreviewPopup = dialog;
        return bottomResultviewPreviewBindingBind;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showPreviewPopupDialog$lambda$0$0(MainActivity mainActivity, DialogInterface dialogInterface) {
        mainActivity.bottomPreviewPopup = null;
        mainActivity.bottomPreviewBinding = null;
        mainActivity.getViewModel().clear();
    }

    public final ActivityMainBinding getBinding() {
        return this.binding;
    }

    public final void setBinding(ActivityMainBinding activityMainBinding) {
        this.binding = activityMainBinding;
    }

    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001,B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0005H\u0002J\u001c\u0010)\u001a\u00020\u001c2\b\u0010*\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010+\u001a\u00020!H\u0007R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR \u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R \u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020!X\u0082T¢\u0006\u0002\n\u0000R\u001d\u0010#\u001a\u0004\u0018\u00010$8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b%\u0010&¨\u0006-"}, d2 = {"Lcom/lagradost/cloudstream3/MainActivity$TvFocus;", "", "<init>", "()V", "last", "Lcom/lagradost/cloudstream3/MainActivity$TvFocus$FocusTarget;", "getLast", "()Lcom/lagradost/cloudstream3/MainActivity$TvFocus$FocusTarget;", "setLast", "(Lcom/lagradost/cloudstream3/MainActivity$TvFocus$FocusTarget;)V", "current", "getCurrent", "setCurrent", "focusOutline", "Ljava/lang/ref/WeakReference;", "Landroid/view/View;", "getFocusOutline", "()Ljava/lang/ref/WeakReference;", "setFocusOutline", "(Ljava/lang/ref/WeakReference;)V", "lastFocus", "getLastFocus", "setLastFocus", "layoutListener", "Landroid/view/View$OnLayoutChangeListener;", "attachListener", "Landroid/view/View$OnAttachStateChangeListener;", "setTargetPosition", "", TypedValues.AttributesType.S_TARGET, "animator", "Landroid/animation/ValueAnimator;", "NO_MOVE_LIST", "", "LEFTMOST_MOVE_LIST", "reflectedScroll", "Ljava/lang/reflect/Method;", "getReflectedScroll", "()Ljava/lang/reflect/Method;", "reflectedScroll$delegate", "Lkotlin/Lazy;", "updateFocusView", "newFocus", "same", "FocusTarget", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class TvFocus {
        private static final boolean LEFTMOST_MOVE_LIST = true;
        private static final boolean NO_MOVE_LIST = false;
        private static ValueAnimator animator;
        public static final TvFocus INSTANCE = new TvFocus();
        private static FocusTarget last = new FocusTarget(0, 0, 0.0f, 0.0f);
        private static FocusTarget current = new FocusTarget(0, 0, 0.0f, 0.0f);
        private static WeakReference<View> focusOutline = new WeakReference<>(null);
        private static WeakReference<View> lastFocus = new WeakReference<>(null);
        private static final View.OnLayoutChangeListener layoutListener = new View.OnLayoutChangeListener() { // from class: com.lagradost.cloudstream3.MainActivity$TvFocus$$ExternalSyntheticLambda0
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                MainActivity.TvFocus.layoutListener$lambda$0(view, i, i2, i3, i4, i5, i6, i7, i8);
            }
        };
        private static final View.OnAttachStateChangeListener attachListener = new View.OnAttachStateChangeListener() { // from class: com.lagradost.cloudstream3.MainActivity$TvFocus$attachListener$1
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View v) {
                Intrinsics.checkNotNullParameter(v, "v");
                MainActivity.TvFocus.updateFocusView$default(MainActivity.TvFocus.INSTANCE, v, false, 2, null);
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View v) {
                Intrinsics.checkNotNullParameter(v, "v");
                View view = MainActivity.TvFocus.INSTANCE.getFocusOutline().get();
                if (view != null) {
                    view.setVisibility(8);
                }
            }
        };

        /* JADX INFO: renamed from: reflectedScroll$delegate, reason: from kotlin metadata */
        private static final Lazy reflectedScroll = LazyKt.lazy(new Function0() { // from class: com.lagradost.cloudstream3.MainActivity$TvFocus$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.TvFocus.reflectedScroll_delegate$lambda$0();
            }
        });

        /* JADX INFO: compiled from: MainActivity.kt */
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000e¨\u0006\u001c"}, d2 = {"Lcom/lagradost/cloudstream3/MainActivity$TvFocus$FocusTarget;", "", "width", "", "height", "x", "", "y", "<init>", "(IIFF)V", "getWidth", "()I", "getHeight", "getX", "()F", "getY", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final /* data */ class FocusTarget {

            /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
            public static final Companion INSTANCE = new Companion(null);
            private final int height;
            private final int width;
            private final float x;
            private final float y;

            public static /* synthetic */ FocusTarget copy$default(FocusTarget focusTarget, int i, int i2, float f, float f2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    i = focusTarget.width;
                }
                if ((i3 & 2) != 0) {
                    i2 = focusTarget.height;
                }
                if ((i3 & 4) != 0) {
                    f = focusTarget.x;
                }
                if ((i3 & 8) != 0) {
                    f2 = focusTarget.y;
                }
                return focusTarget.copy(i, i2, f, f2);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final int getWidth() {
                return this.width;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final int getHeight() {
                return this.height;
            }

            /* JADX INFO: renamed from: component3, reason: from getter */
            public final float getX() {
                return this.x;
            }

            /* JADX INFO: renamed from: component4, reason: from getter */
            public final float getY() {
                return this.y;
            }

            public final FocusTarget copy(int width, int height, float x, float y) {
                return new FocusTarget(width, height, x, y);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof FocusTarget)) {
                    return false;
                }
                FocusTarget focusTarget = (FocusTarget) other;
                return this.width == focusTarget.width && this.height == focusTarget.height && Float.compare(this.x, focusTarget.x) == 0 && Float.compare(this.y, focusTarget.y) == 0;
            }

            public int hashCode() {
                return (((((this.width * 31) + this.height) * 31) + Float.floatToIntBits(this.x)) * 31) + Float.floatToIntBits(this.y);
            }

            public String toString() {
                return "FocusTarget(width=" + this.width + ", height=" + this.height + ", x=" + this.x + ", y=" + this.y + ')';
            }

            public FocusTarget(int i, int i2, float f, float f2) {
                this.width = i;
                this.height = i2;
                this.x = f;
                this.y = f2;
            }

            public final int getWidth() {
                return this.width;
            }

            public final int getHeight() {
                return this.height;
            }

            public final float getX() {
                return this.x;
            }

            public final float getY() {
                return this.y;
            }

            /* JADX INFO: compiled from: MainActivity.kt */
            @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/lagradost/cloudstream3/MainActivity$TvFocus$FocusTarget$Companion;", "", "<init>", "()V", "lerp", "Lcom/lagradost/cloudstream3/MainActivity$TvFocus$FocusTarget;", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static final class Companion {
                public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private Companion() {
                }

                public final FocusTarget lerp(FocusTarget a, FocusTarget b, float lerp) {
                    Intrinsics.checkNotNullParameter(a, "a");
                    Intrinsics.checkNotNullParameter(b, "b");
                    float f = 1.0f - lerp;
                    return new FocusTarget((int) ((a.getWidth() * f) + (b.getWidth() * lerp)), (int) ((a.getHeight() * f) + (b.getHeight() * lerp)), (a.getX() * f) + (b.getX() * lerp), (a.getY() * f) + (b.getY() * lerp));
                }
            }
        }

        private TvFocus() {
        }

        public final FocusTarget getLast() {
            return last;
        }

        public final void setLast(FocusTarget focusTarget) {
            Intrinsics.checkNotNullParameter(focusTarget, "<set-?>");
            last = focusTarget;
        }

        public final FocusTarget getCurrent() {
            return current;
        }

        public final void setCurrent(FocusTarget focusTarget) {
            Intrinsics.checkNotNullParameter(focusTarget, "<set-?>");
            current = focusTarget;
        }

        public final WeakReference<View> getFocusOutline() {
            return focusOutline;
        }

        public final void setFocusOutline(WeakReference<View> weakReference) {
            Intrinsics.checkNotNullParameter(weakReference, "<set-?>");
            focusOutline = weakReference;
        }

        public final WeakReference<View> getLastFocus() {
            return lastFocus;
        }

        public final void setLastFocus(WeakReference<View> weakReference) {
            Intrinsics.checkNotNullParameter(weakReference, "<set-?>");
            lastFocus = weakReference;
        }

        static final void layoutListener$lambda$0(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            TvFocus tvFocus = INSTANCE;
            View view2 = lastFocus.get();
            if (view2 != null) {
                tvFocus.updateFocusView(view2, true);
                view2.postDelayed(new Runnable() { // from class: com.lagradost.cloudstream3.MainActivity$TvFocus$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        MainActivity.TvFocus.layoutListener$lambda$0$0$0();
                    }
                }, 300L);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void layoutListener$lambda$0$0$0() {
            INSTANCE.updateFocusView(lastFocus.get(), false);
        }

        private final void setTargetPosition(FocusTarget target) {
            View view = focusOutline.get();
            if (view != null) {
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.width = target.getWidth();
                    layoutParams.height = target.getHeight();
                } else {
                    layoutParams = null;
                }
                view.setLayoutParams(layoutParams);
                view.setTranslationX(target.getX());
                view.setTranslationY(target.getY());
                view.bringToFront();
            }
        }

        private final Method getReflectedScroll() {
            return (Method) reflectedScroll.getValue();
        }

        static final Method reflectedScroll_delegate$lambda$0() {
            Method method;
            try {
                Method[] declaredMethods = RecyclerView.class.getDeclaredMethods();
                Intrinsics.checkNotNullExpressionValue(declaredMethods, "getDeclaredMethods(...)");
                Method[] methodArr = declaredMethods;
                int length = methodArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        method = null;
                        break;
                    }
                    method = methodArr[i];
                    if (Intrinsics.areEqual(method.getName(), "scrollStep")) {
                        break;
                    }
                    i++;
                }
                Method method2 = method;
                if (method2 != null) {
                    method2.setAccessible(true);
                    return method2;
                }
            } catch (Throwable unused) {
            }
            return null;
        }

        public static /* synthetic */ void updateFocusView$default(TvFocus tvFocus, View view, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                z = false;
            }
            tvFocus.updateFocusView(view, z);
        }

        public final void updateFocusView(View newFocus, boolean same) {
            View view = focusOutline.get();
            if (view == null) {
                return;
            }
            View view2 = lastFocus.get();
            boolean z = Intrinsics.areEqual(view2, newFocus) && newFocus != null;
            if (!z) {
                if (view2 != null) {
                    view2.removeOnLayoutChangeListener(layoutListener);
                }
                if (view2 != null) {
                    view2.removeOnAttachStateChangeListener(attachListener);
                }
                ViewParent parent = view2 != null ? view2.getParent() : null;
                RecyclerView recyclerView = parent instanceof RecyclerView ? (RecyclerView) parent : null;
                if (recyclerView != null) {
                    recyclerView.removeOnLayoutChangeListener(layoutListener);
                }
            }
            boolean z2 = view.getVisibility() == 8;
            view.setVisibility(newFocus != null && newFocus.getMeasuredHeight() > 0 && newFocus.getMeasuredWidth() > 0 && newFocus.isShown() && !Intrinsics.areEqual(newFocus.getTag(), "tv_no_focus_tag") ? 0 : 8);
            if (newFocus != null) {
                lastFocus = new WeakReference<>(newFocus);
                Object parent2 = newFocus.getParent();
                if (parent2 instanceof RecyclerView) {
                    RecyclerView recyclerView2 = (RecyclerView) parent2;
                    RecyclerView.LayoutManager layoutManager = recyclerView2.getLayoutManager();
                    if (layoutManager instanceof LinearListLayout) {
                        LinearListLayout linearListLayout = (LinearListLayout) layoutManager;
                        if (linearListLayout.getOrientation() == 0) {
                            int[] iArrCalculateDistanceToFinalSnap = new LinearSnapHelper().calculateDistanceToFinalSnap(layoutManager, newFocus);
                            Integer numValueOf = iArrCalculateDistanceToFinalSnap != null ? Integer.valueOf(iArrCalculateDistanceToFinalSnap[0]) : null;
                            if (numValueOf != null) {
                                int width = ((linearListLayout.getWidth() - linearListLayout.getPaddingStart()) - newFocus.getMeasuredWidth()) / 2;
                                ViewGroup.LayoutParams layoutParams = newFocus.getLayoutParams();
                                int marginStart = width - (layoutParams instanceof ViewGroup.MarginLayoutParams ? ((ViewGroup.MarginLayoutParams) layoutParams).getMarginStart() : 0);
                                int iIntValue = numValueOf.intValue();
                                if (AppContextUtils.INSTANCE.isRtl((View) parent2)) {
                                    marginStart = -marginStart;
                                }
                                recyclerView2.smoothScrollBy(iIntValue + marginStart, 0);
                            }
                        }
                    }
                }
                int[] iArr = new int[2];
                newFocus.getLocationInWindow(iArr);
                int i = iArr[0];
                int i2 = iArr[1];
                Pair pair = TuplesKt.to(Float.valueOf(i), Float.valueOf(i2));
                float fFloatValue = ((Number) pair.component1()).floatValue();
                float fFloatValue2 = ((Number) pair.component2()).floatValue();
                Pair pair2 = TuplesKt.to(Float.valueOf(view.getTranslationX()), Float.valueOf(view.getTranslationY()));
                float fFloatValue3 = ((Number) pair2.component1()).floatValue();
                float fFloatValue4 = ((Number) pair2.component2()).floatValue();
                if (!AppContextUtils.INSTANCE.isLtr(newFocus)) {
                    fFloatValue = (fFloatValue - view.getRootView().getWidth()) + newFocus.getMeasuredWidth();
                }
                float f = fFloatValue - 0.0f;
                if (i == 0 && i2 == 0) {
                    view.setVisibility(8);
                }
                if (!z) {
                    ViewParent parent3 = newFocus.getParent();
                    RecyclerView recyclerView3 = parent3 instanceof RecyclerView ? (RecyclerView) parent3 : null;
                    if (recyclerView3 != null) {
                        recyclerView3.addOnLayoutChangeListener(layoutListener);
                    }
                    newFocus.addOnLayoutChangeListener(layoutListener);
                    newFocus.addOnAttachStateChangeListener(attachListener);
                }
                FocusTarget focusTarget = new FocusTarget(view.getMeasuredWidth(), view.getMeasuredHeight(), fFloatValue3, fFloatValue4);
                FocusTarget focusTarget2 = new FocusTarget(newFocus.getMeasuredWidth(), newFocus.getMeasuredHeight(), f, fFloatValue2);
                Integer num = (Integer) Comparators.min(Integer.valueOf(focusTarget2.getWidth() / 2), Integer.valueOf(UIHelper.INSTANCE.getToPx(60)));
                Integer num2 = (Integer) Comparators.min(Integer.valueOf(focusTarget2.getHeight() / 2), Integer.valueOf(UIHelper.INSTANCE.getToPx(60)));
                if (focusTarget.getWidth() == focusTarget2.getWidth() && focusTarget.getHeight() == focusTarget2.getHeight() && Math.abs(focusTarget.getX() - focusTarget2.getX()) < num.intValue() && Math.abs(focusTarget.getY() - focusTarget2.getY()) < num2.intValue()) {
                    ValueAnimator valueAnimator = animator;
                    if (valueAnimator != null) {
                        valueAnimator.cancel();
                    }
                    last = focusTarget;
                    current = focusTarget2;
                    setTargetPosition(focusTarget2);
                    return;
                }
                ValueAnimator valueAnimator2 = animator;
                if (valueAnimator2 != null && valueAnimator2.isRunning()) {
                    current = focusTarget2;
                    return;
                }
                ValueAnimator valueAnimator3 = animator;
                if (valueAnimator3 != null) {
                    valueAnimator3.cancel();
                }
                last = focusTarget;
                current = focusTarget2;
                if (z2) {
                    setTargetPosition(focusTarget2);
                    return;
                }
                ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
                valueAnimatorOfFloat.setStartDelay(0L);
                valueAnimatorOfFloat.setDuration(200L);
                valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.lagradost.cloudstream3.MainActivity$TvFocus$$ExternalSyntheticLambda2
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator4) {
                        MainActivity.TvFocus.updateFocusView$lambda$2$0(valueAnimator4);
                    }
                });
                valueAnimatorOfFloat.start();
                animator = valueAnimatorOfFloat;
                if (same) {
                    return;
                }
                newFocus.postDelayed(new Runnable() { // from class: com.lagradost.cloudstream3.MainActivity$TvFocus$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        MainActivity.TvFocus.INSTANCE.updateFocusView(MainActivity.TvFocus.lastFocus.get(), true);
                    }
                }, 200L);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void updateFocusView$lambda$2$0(ValueAnimator animation) {
            Intrinsics.checkNotNullParameter(animation, "animation");
            Object animatedValue = animation.getAnimatedValue();
            Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
            float fFloatValue = ((Float) animatedValue).floatValue();
            INSTANCE.setTargetPosition(FocusTarget.INSTANCE.lerp(last, current, Math.min(fFloatValue, 1.0f)));
        }
    }

    /* JADX WARN: Code duplicated, block: B:209:0x06f2  */
    /* JADX WARN: Code duplicated, block: B:216:0x0708 A[Catch: Exception -> 0x071a, TRY_LEAVE, TryCatch #19 {Exception -> 0x071a, blocks: (B:192:0x069e, B:195:0x06aa, B:200:0x06c0, B:207:0x06ec, B:210:0x06f3, B:216:0x0708, B:215:0x0703, B:206:0x06e2, B:199:0x06b6, B:203:0x06c7, B:196:0x06ac, B:212:0x06f7), top: B:303:0x069e, inners: #2, #18, #23 }] */
    /* JADX WARN: Code duplicated, block: B:308:0x02ef A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:310:0x06f7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:86:0x02ea  */
    /* JADX WARN: Code duplicated, block: B:93:0x0300 A[Catch: Exception -> 0x0312, TRY_LEAVE, TryCatch #14 {Exception -> 0x0312, blocks: (B:69:0x0297, B:72:0x02a3, B:77:0x02b9, B:84:0x02e4, B:87:0x02eb, B:93:0x0300, B:92:0x02fb, B:83:0x02da, B:76:0x02af, B:73:0x02a5, B:80:0x02c0, B:89:0x02ef), top: B:295:0x0297, inners: #13, #20, #24 }] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r19v0 */
    /* JADX WARN: Type inference failed for: r19v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v0 */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws UnsupportedEncodingException {
        int i;
        int i2;
        ActivityMainBinding activityMainBindingBind;
        Object objDecodeFromString;
        Object objDecodeFromString2;
        Object obj;
        Object obj2;
        Object objM8936constructorimpl;
        Object objDecodeFromString3;
        Object objM8936constructorimpl2;
        KSerializer kSerializer;
        View viewFindViewById;
        View viewFindViewById2;
        View viewFindViewById3;
        View viewFindViewById4;
        NavigationRailView navigationRailView;
        NavigationRailView navigationRailView2;
        BottomNavigationView bottomNavigationView;
        Object objM8936constructorimpl3;
        KSerializer kSerializer2;
        FragmentContainerView fragmentContainerView;
        BiometricPrompt biometricPrompt;
        FrameLayout frameLayout;
        MainActivity mainActivity = this;
        int i3 = 0;
        RequestsHelperKt.initClient(MainActivityKt.getApp(), mainActivity, false);
        ?? r6 = 1;
        RequestsHelperKt.initClient(MainActivityKt.getInsecureApp(), mainActivity, true);
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mainActivity);
        INSTANCE.setLastError(mainActivity);
        SettingsJson settingsJson = new SettingsJson(false, 1, null);
        settingsJson.setEnableAdult(defaultSharedPreferences.getBoolean(getString(R.string.enable_nsfw_on_providers_key), false));
        MainAPI.INSTANCE.setSettingsForProvider(settingsJson);
        MainActivity mainActivity2 = this;
        CommonActivity.INSTANCE.loadThemes(mainActivity2);
        UIHelper.INSTANCE.enableEdgeToEdgeCompat(mainActivity2);
        UIHelper.INSTANCE.setNavigationBarColorCompat(mainActivity2, R.attr.primaryGrayBackground);
        CommonActivity.INSTANCE.updateLocale(mainActivity);
        super.onCreate(savedInstanceState);
        try {
            if (AppContextUtils.INSTANCE.isCastApiAvailable(this)) {
                Task<CastContext> sharedInstance = CastContext.getSharedInstance(this, new Executor() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda11
                    @Override // java.util.concurrent.Executor
                    public final void execute(Runnable runnable) {
                        runnable.run();
                    }
                });
                final Function1 function1 = new Function1() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return MainActivity.onCreate$lambda$1(this.f$0, (CastContext) obj3);
                    }
                };
                sharedInstance.addOnSuccessListener(new OnSuccessListener() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda3
                    @Override // com.google.android.gms.tasks.OnSuccessListener
                    public final void onSuccess(Object obj3) {
                        function1.invoke(obj3);
                    }
                });
            }
        } catch (Throwable th) {
            ArchComponentExtKt.logError(th);
        }
        getWindow().setSoftInputMode(3);
        Globals.INSTANCE.updateTv(mainActivity);
        ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.onCreate$lambda$3(this.f$0);
            }
        });
        int i4 = 8;
        try {
            i2 = 5;
            try {
                if (Globals.INSTANCE.isLayout(6)) {
                    try {
                        ActivityMainTvBinding activityMainTvBindingInflate = ActivityMainTvBinding.inflate(getLayoutInflater(), null, false);
                        Intrinsics.checkNotNullExpressionValue(activityMainTvBindingInflate, "inflate(...)");
                        setContentView(activityMainTvBindingInflate.getRoot());
                        Globals.INSTANCE.isLayout(2);
                        ImageView focusOutline = activityMainTvBindingInflate.focusOutline;
                        i = 6;
                        try {
                            Intrinsics.checkNotNullExpressionValue(focusOutline, "focusOutline");
                            focusOutline.setVisibility(8);
                            if (Globals.INSTANCE.isLayout(2)) {
                                i4 = 8;
                                final List listListOf = CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(R.id.home_preview_info_btt), Integer.valueOf(R.id.home_preview_hidden_next_focus), Integer.valueOf(R.id.home_preview_hidden_prev_focus), Integer.valueOf(R.id.result_play_movie_button), Integer.valueOf(R.id.result_play_series_button), Integer.valueOf(R.id.result_resume_series_button), Integer.valueOf(R.id.result_play_trailer_button), Integer.valueOf(R.id.result_bookmark_Button), Integer.valueOf(R.id.result_favorite_Button), Integer.valueOf(R.id.result_subscribe_Button), Integer.valueOf(R.id.result_search_Button), Integer.valueOf(R.id.result_episodes_show_button)});
                                activityMainTvBindingInflate.getRoot().getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda5
                                    @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
                                    public final void onGlobalFocusChanged(View view, View view2) {
                                        MainActivity.onCreate$lambda$6(listListOf, view, view2);
                                    }
                                });
                            } else {
                                i4 = 8;
                            }
                            activityMainBindingBind = ActivityMainBinding.bind(activityMainTvBindingInflate.getRoot());
                        } catch (Throwable th2) {
                            th = th2;
                            CommonActivity commonActivity = CommonActivity.INSTANCE;
                            int i5 = R.string.unable_to_inflate;
                            String message = th.getMessage();
                            if (message == null) {
                                message = "";
                            }
                            commonActivity.showToast(TextUtilKt.txt(i5, message), (Integer) 1);
                            activityMainBindingBind = null;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        i = 6;
                    }
                } else {
                    i4 = 8;
                    i = 6;
                    activityMainBindingBind = ActivityMainBinding.inflate(getLayoutInflater(), null, false);
                    Intrinsics.checkNotNullExpressionValue(activityMainBindingBind, "inflate(...)");
                    setContentView(activityMainBindingBind.getRoot());
                }
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (Throwable th5) {
            th = th5;
            i = 6;
            i2 = 5;
        }
        this.binding = activityMainBindingBind;
        if (activityMainBindingBind != null) {
            UIHelper uIHelper = UIHelper.INSTANCE;
            BottomNavigationView navView = activityMainBindingBind.navView;
            Intrinsics.checkNotNullExpressionValue(navView, "navView");
            UIHelper.fixSystemBarsPadding$default(uIHelper, navView, Integer.valueOf(R.dimen.nav_view_height), null, false, false, false, false, false, false, 372, null);
            UIHelper uIHelper2 = UIHelper.INSTANCE;
            NavigationRailView navRailView = activityMainBindingBind.navRailView;
            Intrinsics.checkNotNullExpressionValue(navRailView, "navRailView");
            UIHelper.fixSystemBarsPadding$default(uIHelper2, navRailView, null, Integer.valueOf(R.dimen.nav_rail_view_width), false, false, false, false, false, false, 434, null);
        }
        int toPx = UIHelper.INSTANCE.getToPx(defaultSharedPreferences.getInt(getString(R.string.overscan_key), 0));
        ActivityMainBinding activityMainBinding = this.binding;
        if (activityMainBinding != null && (frameLayout = activityMainBinding.homeRoot) != null) {
            frameLayout.setPadding(toPx, toPx, toPx, toPx);
            Unit unit = Unit.INSTANCE;
        }
        UIHelper.INSTANCE.changeStatusBarState(mainActivity2, Globals.INSTANCE.isLayout(4));
        boolean z = defaultSharedPreferences.getBoolean(getString(R.string.skip_startup_account_select_key), false) || DataStoreHelper.INSTANCE.getAccounts().length <= 1;
        if (Globals.INSTANCE.isLayout(1) && BiometricAuthenticator.INSTANCE.isAuthEnabled(mainActivity) && z && BiometricAuthenticator.INSTANCE.deviceHasPasswordPinLock(mainActivity)) {
            BiometricAuthenticator.INSTANCE.startBiometricAuthentication(this, R.string.biometric_authentication_title, false);
            BiometricPrompt.PromptInfo promptInfo = BiometricAuthenticator.INSTANCE.getPromptInfo();
            if (promptInfo != null && (biometricPrompt = BiometricAuthenticator.INSTANCE.getBiometricPrompt()) != null) {
                biometricPrompt.authenticate(promptInfo);
                Unit unit2 = Unit.INSTANCE;
            }
            ActivityMainBinding activityMainBinding2 = this.binding;
            if (activityMainBinding2 != null && (fragmentContainerView = activityMainBinding2.navHostFragment) != null) {
                fragmentContainerView.setVisibility(4);
                Unit unit3 = Unit.INSTANCE;
            }
        }
        DataStore dataStore = DataStore.INSTANCE;
        String string = getString(R.string.jsdelivr_proxy_key);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        try {
            String string2 = dataStore.getSharedPrefs(mainActivity).getString(string, null);
            if (string2 == null) {
                objDecodeFromString = null;
            } else {
                AppUtils appUtils = AppUtils.INSTANCE;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM8936constructorimpl3 = Result.m8936constructorimpl(BooleanSerializer.INSTANCE);
                } catch (Throwable th6) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM8936constructorimpl3 = Result.m8936constructorimpl(ResultKt.createFailure(th6));
                }
                if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl3) == null) {
                    if (Result.m8942isFailureimpl(objM8936constructorimpl3)) {
                        objM8936constructorimpl3 = null;
                    }
                    kSerializer2 = (KSerializer) objM8936constructorimpl3;
                    if (kSerializer2 != null) {
                        try {
                            objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer2, string2);
                        } catch (SerializationException e) {
                            ArchComponentExtKt.logError(e);
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string2, new TypeReference<Boolean>() { // from class: com.lagradost.cloudstream3.MainActivity$onCreate$$inlined$getKey$1
                            });
                        } catch (Throwable unused) {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string2, new TypeReference<Boolean>() { // from class: com.lagradost.cloudstream3.MainActivity$onCreate$$inlined$getKey$1
                            });
                        }
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string2, new TypeReference<Boolean>() { // from class: com.lagradost.cloudstream3.MainActivity$onCreate$$inlined$getKey$1
                        });
                    }
                } else {
                    try {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl3 = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(Boolean.class), null, 2, null));
                    } catch (Throwable th7) {
                        Result.Companion companion4 = Result.INSTANCE;
                        objM8936constructorimpl3 = Result.m8936constructorimpl(ResultKt.createFailure(th7));
                    }
                    if (Result.m8942isFailureimpl(objM8936constructorimpl3)) {
                        objM8936constructorimpl3 = null;
                    }
                    kSerializer2 = (KSerializer) objM8936constructorimpl3;
                    if (kSerializer2 != null) {
                        objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer2, string2);
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string2, new TypeReference<Boolean>() { // from class: com.lagradost.cloudstream3.MainActivity$onCreate$$inlined$getKey$1
                        });
                    }
                }
            }
        } catch (Exception unused2) {
            objDecodeFromString = null;
        }
        if (objDecodeFromString == null && AppContextUtils.INSTANCE.isNetworkAvailable(mainActivity)) {
            Coroutines.INSTANCE.main(this, new AnonymousClass9(null));
        }
        Coroutines.INSTANCE.ioSafe(this, new AnonymousClass10(null));
        if (PluginManager.INSTANCE.checkSafeModeFile()) {
            ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MainActivity.onCreate$lambda$9();
                }
            });
        } else if (lastError == null) {
            Coroutines.INSTANCE.ioSafe(this, new AnonymousClass12(defaultSharedPreferences, null));
        } else {
            final AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            builder.setTitle(R.string.safe_mode_title);
            builder.setMessage(R.string.safe_mode_description);
            builder.setPositiveButton(R.string.safe_mode_crash_info, new DialogInterface.OnClickListener() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda7
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i6) {
                    MainActivity.onCreate$lambda$10$0(builder, dialogInterface, i6);
                }
            });
            builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda8
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i6) {
                    MainActivity.onCreate$lambda$10$1(dialogInterface, i6);
                }
            });
            AppContextUtils appContextUtils = AppContextUtils.INSTANCE;
            AlertDialog alertDialogShow = builder.show();
            Intrinsics.checkNotNullExpressionValue(alertDialogShow, "show(...)");
            AppContextUtils.setDefaultFocus$default(appContextUtils, alertDialogShow, 0, 1, null);
            Unit unit4 = Unit.INSTANCE;
        }
        MainActivity mainActivity3 = this;
        LifecycleKt.observe(mainActivity3, getViewModel().getWatchStatus(), new AnonymousClass14());
        LifecycleKt.observe(mainActivity3, getSyncViewModel().getUserData(), new AnonymousClass15());
        LifecycleKt.observeNullable(mainActivity3, getViewModel().getSubscribeStatus(), new AnonymousClass16());
        LifecycleKt.observeNullable(mainActivity3, getViewModel().getPage(), new Function1() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj3) {
                return MainActivity.onCreate$lambda$14(this.f$0, (Resource) obj3);
            }
        });
        Coroutines.INSTANCE.ioSafe(this, new AnonymousClass18(null));
        SearchResultBuilder.INSTANCE.updateCache(mainActivity);
        Coroutines.INSTANCE.ioSafe(this, new AnonymousClass19(null));
        BackupUtils.INSTANCE.setUpBackup(this);
        CommonActivity.INSTANCE.init(mainActivity2);
        Fragment fragmentFindFragmentById = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        Intrinsics.checkNotNull(fragmentFindFragmentById, "null cannot be cast to non-null type androidx.navigation.fragment.NavHostFragment");
        final NavController navController = ((NavHostFragment) fragmentFindFragmentById).getNavController();
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda10
            @Override // androidx.navigation.NavController.OnDestinationChangedListener
            public final void onDestinationChanged(NavController navController2, NavDestination navDestination, Bundle bundle) {
                MainActivity.onCreate$lambda$15(this.f$0, defaultSharedPreferences, navController2, navDestination, bundle);
            }
        });
        ColorStateList colorStateListValueOf = ColorStateList.valueOf(UIHelper.INSTANCE.getResourceColor(mainActivity, R.attr.colorPrimary, 0.1f));
        Intrinsics.checkNotNullExpressionValue(colorStateListValueOf, "valueOf(...)");
        ActivityMainBinding activityMainBinding3 = this.binding;
        if (activityMainBinding3 != null && (bottomNavigationView = activityMainBinding3.navView) != null) {
            bottomNavigationView.setItemRippleColor(colorStateListValueOf);
            bottomNavigationView.setItemActiveIndicatorColor(colorStateListValueOf);
            BottomNavigationViewKt.setupWithNavController(bottomNavigationView, navController);
            bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda22
                @Override // com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
                public final boolean onNavigationItemSelected(MenuItem menuItem) {
                    return MainActivity.onCreate$lambda$16$0(this.f$0, navController, menuItem);
                }
            });
        }
        ActivityMainBinding activityMainBinding4 = this.binding;
        if (activityMainBinding4 == null || (navigationRailView2 = activityMainBinding4.navRailView) == null) {
            i3 = 0;
            r6 = 1;
        } else {
            if (Globals.INSTANCE.isLayout(1)) {
                navigationRailView2.setItemRippleColor(colorStateListValueOf);
                navigationRailView2.setItemActiveIndicatorColor(colorStateListValueOf);
            } else {
                ColorStateList colorStateListValueOf2 = ColorStateList.valueOf(UIHelper.INSTANCE.getResourceColor(mainActivity, R.attr.textColor, 1.0f));
                Intrinsics.checkNotNullExpressionValue(colorStateListValueOf2, "valueOf(...)");
                ColorStateList colorStateListValueOf3 = ColorStateList.valueOf(UIHelper.INSTANCE.getResourceColor(mainActivity, R.attr.textColor, 0.2f));
                Intrinsics.checkNotNullExpressionValue(colorStateListValueOf3, "valueOf(...)");
                navigationRailView2.setItemSpacing(UIHelper.INSTANCE.getToPx(12));
                navigationRailView2.setItemRippleColor(colorStateListValueOf3);
                navigationRailView2.setItemActiveIndicatorColor(colorStateListValueOf2);
            }
            BottomNavigationViewKt.setupWithNavController(navigationRailView2, navController);
            navigationRailView2.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda31
                @Override // com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
                public final boolean onNavigationItemSelected(MenuItem menuItem) {
                    return MainActivity.onCreate$lambda$17$0(this.f$0, navController, menuItem);
                }
            });
            final LinearLayout linearLayout = (LinearLayout) navigationRailView2.findViewById(R.id.nav_footer_root);
            if (Globals.INSTANCE.isLayout(i)) {
                final ImageView imageView = (ImageView) navigationRailView2.findViewById(R.id.nav_footer_profile_pic);
                CardView cardView = (CardView) navigationRailView2.findViewById(R.id.nav_footer_profile_card);
                if (cardView != null) {
                    cardView.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda32
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            MainActivity.onCreate$lambda$17$2(this.f$0, view);
                        }
                    });
                    Unit unit5 = Unit.INSTANCE;
                }
                LifecycleKt.observe(navigationRailView2, ((HomeViewModel) new ViewModelProvider(this).get(HomeViewModel.class)).getCurrentAccount(), new Function1() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda33
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return MainActivity.onCreate$lambda$17$3(imageView, linearLayout, (DataStoreHelper.Account) obj3);
                    }
                });
            } else {
                Intrinsics.checkNotNull(linearLayout);
                linearLayout.setVisibility(i4);
            }
        }
        ActivityMainBinding activityMainBinding5 = this.binding;
        NavigationRailView navigationRailView3 = activityMainBinding5 != null ? activityMainBinding5.navRailView : null;
        if (navigationRailView3 != null) {
            ActivityMainBinding activityMainBinding6 = this.binding;
            if (activityMainBinding6 != null && (navigationRailView = activityMainBinding6.navRailView) != null) {
                navigationRailView.setLabelVisibilityMode(2);
                Unit unit6 = Unit.INSTANCE;
            }
            View viewFindViewById5 = navigationRailView3.findViewById(R.id.navigation_settings);
            if (viewFindViewById5 != null) {
                viewFindViewById5.setNextFocusDownId(R.id.nav_footer_profile_card);
                Unit unit7 = Unit.INSTANCE;
            }
            Integer numValueOf = Integer.valueOf(R.id.navigation_home);
            Integer numValueOf2 = Integer.valueOf(R.id.navigation_search);
            Integer numValueOf3 = Integer.valueOf(R.id.navigation_library);
            Integer numValueOf4 = Integer.valueOf(R.id.navigation_downloads);
            Integer numValueOf5 = Integer.valueOf(R.id.navigation_settings);
            int i6 = i2;
            Integer[] numArr = new Integer[i6];
            numArr[i3] = numValueOf;
            numArr[r6] = numValueOf2;
            numArr[2] = numValueOf3;
            numArr[3] = numValueOf4;
            numArr[4] = numValueOf5;
            int i7 = i3;
            Integer num = null;
            View view = null;
            while (i7 < i6) {
                Integer num2 = numArr[i7];
                int iIntValue = num2.intValue();
                View viewFindViewById6 = navigationRailView3.findViewById(iIntValue);
                if (viewFindViewById6 != null) {
                    if (num != null) {
                        viewFindViewById6.setNextFocusUpId(num.intValue());
                        Unit unit8 = Unit.INSTANCE;
                        Unit unit9 = Unit.INSTANCE;
                    }
                    if (view != null) {
                        view.setNextFocusDownId(iIntValue);
                        Unit unit10 = Unit.INSTANCE;
                    }
                    num = num2;
                    view = viewFindViewById6;
                }
                i7++;
                i6 = 5;
            }
        }
        NavigationBarView[] navigationBarViewArr = new NavigationBarView[2];
        ActivityMainBinding activityMainBinding7 = this.binding;
        navigationBarViewArr[i3] = activityMainBinding7 != null ? activityMainBinding7.navView : null;
        ActivityMainBinding activityMainBinding8 = this.binding;
        navigationBarViewArr[r6] = activityMainBinding8 != null ? activityMainBinding8.navRailView : null;
        for (NavigationBarView navigationBarView : CollectionsKt.listOf((Object[]) navigationBarViewArr)) {
            if (navigationBarView != null && (viewFindViewById4 = navigationBarView.findViewById(R.id.navigation_home)) != null) {
                viewFindViewById4.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda34
                    @Override // android.view.View.OnLongClickListener
                    public final boolean onLongClick(View view2) {
                        return MainActivity.onCreate$lambda$19(this.f$0, view2);
                    }
                });
                Unit unit11 = Unit.INSTANCE;
            }
            if (navigationBarView != null && (viewFindViewById3 = navigationBarView.findViewById(R.id.navigation_library)) != null) {
                viewFindViewById3.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda35
                    @Override // android.view.View.OnLongClickListener
                    public final boolean onLongClick(View view2) {
                        return MainActivity.onCreate$lambda$20(this.f$0, view2);
                    }
                });
                Unit unit12 = Unit.INSTANCE;
            }
            if (navigationBarView != null && (viewFindViewById2 = navigationBarView.findViewById(R.id.navigation_search)) != null) {
                viewFindViewById2.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda36
                    @Override // android.view.View.OnLongClickListener
                    public final boolean onLongClick(View view2) {
                        return MainActivity.onCreate$lambda$21(this.f$0, view2);
                    }
                });
                Unit unit13 = Unit.INSTANCE;
            }
            if (navigationBarView != null && (viewFindViewById = navigationBarView.findViewById(R.id.navigation_downloads)) != null) {
                viewFindViewById.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda37
                    @Override // android.view.View.OnLongClickListener
                    public final boolean onLongClick(View view2) {
                        return MainActivity.onCreate$lambda$22(this.f$0, view2);
                    }
                });
                Unit unit14 = Unit.INSTANCE;
            }
        }
        AppContextUtils.INSTANCE.loadCache(mainActivity2);
        AppContextUtils.INSTANCE.updateHasTrailers(mainActivity);
        if (!UIHelper.INSTANCE.checkWrite(mainActivity)) {
            UIHelper.INSTANCE.requestRW(mainActivity2);
            if (UIHelper.INSTANCE.checkWrite(mainActivity)) {
                return;
            }
        }
        handleAppIntent(getIntent());
        Coroutines.INSTANCE.ioSafe(this, new AnonymousClass29(null));
        new FcastManager().init(mainActivity, i3);
        APIRepository.INSTANCE.setDubStatusActive(AppContextUtils.INSTANCE.getApiDubstatusSettings(mainActivity));
        try {
            AppContextUtils.INSTANCE.loadCache(this);
            FilesKt.deleteRecursively(new File(getFilesDir(), "exoplayer"));
            INSTANCE.deleteFileOnExit(new File(getCacheDir(), "exoplayer"));
        } catch (Exception e2) {
            ArchComponentExtKt.logError(e2);
        }
        System.out.println((Object) "Loaded everything");
        Coroutines.INSTANCE.ioSafe(this, new AnonymousClass30(null));
        Coroutines.INSTANCE.main(this, new AnonymousClass31(null));
        try {
            String string3 = DataStore.INSTANCE.getSharedPrefs(mainActivity).getString(DataStoreKt.USER_SELECTED_HOMEPAGE_API, null);
            if (string3 == null) {
                objDecodeFromString2 = null;
            } else {
                AppUtils appUtils2 = AppUtils.INSTANCE;
                try {
                    Result.Companion companion5 = Result.INSTANCE;
                    objM8936constructorimpl2 = Result.m8936constructorimpl(StringSerializer.INSTANCE);
                } catch (Throwable th8) {
                    Result.Companion companion6 = Result.INSTANCE;
                    objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th8));
                }
                if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl2) == null) {
                    if (Result.m8942isFailureimpl(objM8936constructorimpl2)) {
                        objM8936constructorimpl2 = null;
                    }
                    kSerializer = (KSerializer) objM8936constructorimpl2;
                    if (kSerializer != null) {
                        try {
                            objDecodeFromString2 = MainAPIKt.getJson().decodeFromString(kSerializer, string3);
                        } catch (SerializationException e3) {
                            ArchComponentExtKt.logError(e3);
                            objDecodeFromString2 = MainAPIKt.getMapper().readValue(string3, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.MainActivity$onCreate$$inlined$getKey$2
                            });
                        } catch (Throwable unused3) {
                            objDecodeFromString2 = MainAPIKt.getMapper().readValue(string3, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.MainActivity$onCreate$$inlined$getKey$2
                            });
                        }
                    } else {
                        objDecodeFromString2 = MainAPIKt.getMapper().readValue(string3, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.MainActivity$onCreate$$inlined$getKey$2
                        });
                    }
                } else {
                    try {
                        Result.Companion companion7 = Result.INSTANCE;
                        objM8936constructorimpl2 = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(String.class), null, 2, null));
                    } catch (Throwable th9) {
                        Result.Companion companion8 = Result.INSTANCE;
                        objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th9));
                    }
                    if (Result.m8942isFailureimpl(objM8936constructorimpl2)) {
                        objM8936constructorimpl2 = null;
                    }
                    kSerializer = (KSerializer) objM8936constructorimpl2;
                    if (kSerializer != null) {
                        objDecodeFromString2 = MainAPIKt.getJson().decodeFromString(kSerializer, string3);
                    } else {
                        objDecodeFromString2 = MainAPIKt.getMapper().readValue(string3, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.MainActivity$onCreate$$inlined$getKey$2
                        });
                    }
                }
            }
        } catch (Exception unused4) {
            objDecodeFromString2 = null;
        }
        String str = (String) objDecodeFromString2;
        if (str != null) {
            DataStoreHelper.INSTANCE.setCurrentHomePage(str);
            CloudStreamApp.INSTANCE.removeKey(DataStoreKt.USER_SELECTED_HOMEPAGE_API);
            Unit unit15 = Unit.INSTANCE;
            Unit unit16 = Unit.INSTANCE;
        }
        try {
            try {
                obj = null;
                try {
                    String string4 = DataStore.INSTANCE.getSharedPrefs(this).getString(SetupFragmentLanguageKt.HAS_DONE_SETUP_KEY, null);
                    if (string4 == null) {
                        obj2 = false;
                    } else {
                        AppUtils appUtils3 = AppUtils.INSTANCE;
                        try {
                            Result.Companion companion9 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(BooleanSerializer.INSTANCE);
                        } catch (Throwable th10) {
                            Result.Companion companion10 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th10));
                        }
                        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) == null) {
                            obj = null;
                        } else {
                            try {
                                Result.Companion companion11 = Result.INSTANCE;
                                obj = null;
                                try {
                                    objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(Boolean.class), null, 2, null));
                                } catch (Throwable th11) {
                                    th = th11;
                                    Result.Companion companion12 = Result.INSTANCE;
                                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                                }
                            } catch (Throwable th12) {
                                th = th12;
                                obj = null;
                            }
                        }
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = obj;
                        }
                        KSerializer kSerializer3 = (KSerializer) objM8936constructorimpl;
                        if (kSerializer3 != null) {
                            try {
                                objDecodeFromString3 = MainAPIKt.getJson().decodeFromString(kSerializer3, string4);
                            } catch (SerializationException e4) {
                                ArchComponentExtKt.logError(e4);
                                objDecodeFromString3 = MainAPIKt.getMapper().readValue(string4, new TypeReference<Boolean>() { // from class: com.lagradost.cloudstream3.MainActivity$onCreate$$inlined$getKey$3
                                });
                            } catch (Throwable unused5) {
                                objDecodeFromString3 = MainAPIKt.getMapper().readValue(string4, new TypeReference<Boolean>() { // from class: com.lagradost.cloudstream3.MainActivity$onCreate$$inlined$getKey$3
                                });
                            }
                        } else {
                            objDecodeFromString3 = MainAPIKt.getMapper().readValue(string4, new TypeReference<Boolean>() { // from class: com.lagradost.cloudstream3.MainActivity$onCreate$$inlined$getKey$3
                            });
                        }
                        obj2 = objDecodeFromString3;
                    }
                } catch (Exception unused6) {
                    obj2 = obj;
                }
            } catch (Exception unused7) {
                obj = null;
                obj2 = obj;
            }
            if (!Intrinsics.areEqual(obj2, Boolean.valueOf((boolean) r6))) {
                navController.navigate(R.id.navigation_setup_language);
            } else if (PluginManager.INSTANCE.getPluginsOnline().length == 0 && PluginManager.INSTANCE.getPluginsLocal().length == 0) {
                navController.navigate(R.id.navigation_setup_extensions, SetupFragmentExtensions.INSTANCE.newInstance(false));
            }
        } catch (Exception e5) {
            ArchComponentExtKt.logError(e5);
        }
        BackPressedCallbackHelper.INSTANCE.attachBackPressedCallback(mainActivity3, "MainActivityDefault", new Function1() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj3) {
                return MainActivity.onCreate$lambda$25(this.f$0, (BackPressedCallbackHelper.CallbackHelper) obj3);
            }
        });
        DownloadQueueManager.INSTANCE.init(mainActivity);
    }

    static final Unit onCreate$lambda$1(MainActivity mainActivity, CastContext castContext) {
        mainActivity.mSessionManager = castContext.getSessionManager();
        return Unit.INSTANCE;
    }

    static final Unit onCreate$lambda$3(final MainActivity mainActivity) {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        MainActivity mainActivity2 = mainActivity;
        Object obj = null;
        try {
            String string = DataStore.INSTANCE.getSharedPrefs(mainActivity2).getString("VERSION_NAME", null);
            if (string != null) {
                AppUtils appUtils = AppUtils.INSTANCE;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(StringSerializer.INSTANCE);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                    try {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(String.class), null, 2, null));
                    } catch (Throwable th2) {
                        Result.Companion companion4 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                    }
                }
                if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                    objM8936constructorimpl = null;
                }
                KSerializer kSerializer = (KSerializer) objM8936constructorimpl;
                if (kSerializer != null) {
                    try {
                        objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                    } catch (SerializationException e) {
                        ArchComponentExtKt.logError(e);
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.MainActivity$onCreate$lambda$3$$inlined$getKey$1
                        });
                    } catch (Throwable unused) {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.MainActivity$onCreate$lambda$3$$inlined$getKey$1
                        });
                    }
                } else {
                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.MainActivity$onCreate$lambda$3$$inlined$getKey$1
                    });
                }
                obj = objDecodeFromString;
            }
        } catch (Exception unused2) {
        }
        String str = (String) obj;
        if (str == null) {
            str = "";
        }
        if (!Intrinsics.areEqual(BuildConfig.VERSION_NAME, str)) {
            DataStore.INSTANCE.setKey(mainActivity2, "VERSION_NAME", BuildConfig.VERSION_NAME);
            if (str.length() == 0) {
                return Unit.INSTANCE;
            }
            ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda28
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MainActivity.onCreate$lambda$3$0(this.f$0);
                }
            });
            ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda29
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MainActivity.onCreate$lambda$3$1(this.f$0);
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Job onCreate$lambda$3$0(MainActivity mainActivity) {
        return BackupUtils.INSTANCE.backup(mainActivity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$3$1(MainActivity mainActivity) {
        PluginManager.INSTANCE.deleteAllOatFiles(mainActivity);
        return Unit.INSTANCE;
    }

    static final void onCreate$lambda$6(List list, View view, View view2) {
        if (CollectionsKt.contains(list, view2 != null ? Integer.valueOf(view2.getId()) : null)) {
            return;
        }
        INSTANCE.centerView(view2);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainActivity$onCreate$9, reason: invalid class name */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/lagradost/cloudstream3/MainActivity;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.MainActivity$onCreate$9", f = "MainActivity.kt", i = {}, l = {1330}, m = "invokeSuspend", n = {}, nl = {1331}, s = {}, v = 2)
    static final class AnonymousClass9 extends SuspendLambda implements Function2<MainActivity, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass9(Continuation<? super AnonymousClass9> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MainActivity.this.new AnonymousClass9(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(MainActivity mainActivity, Continuation<? super Unit> continuation) {
            return ((AnonymousClass9) create(mainActivity, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = MainActivity.this.checkGithubConnectivity(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            if (((Boolean) obj).booleanValue()) {
                DataStore dataStore = DataStore.INSTANCE;
                MainActivity mainActivity = MainActivity.this;
                MainActivity mainActivity2 = mainActivity;
                String string = mainActivity.getString(R.string.jsdelivr_proxy_key);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                dataStore.setKey(mainActivity2, string, Boxing.boxBoolean(false));
            } else {
                DataStore dataStore2 = DataStore.INSTANCE;
                MainActivity mainActivity3 = MainActivity.this;
                MainActivity mainActivity4 = mainActivity3;
                String string2 = mainActivity3.getString(R.string.jsdelivr_proxy_key);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                dataStore2.setKey(mainActivity4, string2, Boxing.boxBoolean(true));
                SnackbarHelper snackbarHelper = SnackbarHelper.INSTANCE;
                MainActivity mainActivity5 = MainActivity.this;
                int i2 = R.string.jsdelivr_enabled;
                Integer numBoxInt = Boxing.boxInt(R.string.revert);
                final MainActivity mainActivity6 = MainActivity.this;
                snackbarHelper.showSnackbar(mainActivity5, i2, 0, numBoxInt, new Function0() { // from class: com.lagradost.cloudstream3.MainActivity$onCreate$9$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MainActivity.AnonymousClass9.invokeSuspend$lambda$0(mainActivity6);
                    }
                });
            }
            return Unit.INSTANCE;
        }

        static final Unit invokeSuspend$lambda$0(MainActivity mainActivity) {
            DataStore dataStore = DataStore.INSTANCE;
            MainActivity mainActivity2 = mainActivity;
            String string = mainActivity.getString(R.string.jsdelivr_proxy_key);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            dataStore.setKey(mainActivity2, string, false);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainActivity$onCreate$10, reason: invalid class name */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/MainActivity;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.MainActivity$onCreate$10", f = "MainActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass10 extends SuspendLambda implements Function3<CoroutineScope, MainActivity, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass10(Continuation<? super AnonymousClass10> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, MainActivity mainActivity, Continuation<? super Unit> continuation) {
            return MainActivity.this.new AnonymousClass10(continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            SafeFile.INSTANCE.check(MainActivity.this);
            return Unit.INSTANCE;
        }
    }

    static final Unit onCreate$lambda$9() {
        CommonActivity.INSTANCE.showToast(R.string.safe_mode_file, (Integer) 1);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainActivity$onCreate$12, reason: invalid class name */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/MainActivity;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.MainActivity$onCreate$12", f = "MainActivity.kt", i = {0, 0, 0}, l = {1353}, m = "invokeSuspend", n = {"$this$ioSafe", "homeApi", "$i$a$-let-MainActivity$onCreate$12$1"}, nl = {1354}, s = {"L$0", "L$1", "I$0"}, v = 2)
    static final class AnonymousClass12 extends SuspendLambda implements Function3<CoroutineScope, MainActivity, Continuation<? super Unit>, Object> {
        final /* synthetic */ SharedPreferences $settingsManager;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass12(SharedPreferences sharedPreferences, Continuation<? super AnonymousClass12> continuation) {
            super(3, continuation);
            this.$settingsManager = sharedPreferences;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, MainActivity mainActivity, Continuation<? super Unit> continuation) {
            AnonymousClass12 anonymousClass12 = MainActivity.this.new AnonymousClass12(this.$settingsManager, continuation);
            anonymousClass12.L$0 = coroutineScope;
            return anonymousClass12.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Event event;
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                String currentHomePage = DataStoreHelper.INSTANCE.getCurrentHomePage();
                if (currentHomePage != null) {
                    MainActivity mainActivity = MainActivity.this;
                    Event<Boolean> mainPluginsLoadedEvent = MainActivity.INSTANCE.getMainPluginsLoadedEvent();
                    this.L$0 = coroutineScope;
                    this.L$1 = SpillingKt.nullOutSpilledVariable(currentHomePage);
                    this.L$2 = mainPluginsLoadedEvent;
                    this.I$0 = 0;
                    this.label = 1;
                    obj = PluginManager.INSTANCE.loadSinglePlugin(mainActivity, currentHomePage, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    event = mainPluginsLoadedEvent;
                } else {
                    MainActivity.INSTANCE.getMainPluginsLoadedEvent().invoke(Boxing.boxBoolean(false));
                }
                Coroutines.INSTANCE.ioSafe(coroutineScope, new AnonymousClass3(this.$settingsManager, MainActivity.this, null));
                Coroutines.INSTANCE.ioSafe(coroutineScope, new AnonymousClass4(MainActivity.this, null));
                return Unit.INSTANCE;
            }
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            event = (Event) this.L$2;
            ResultKt.throwOnFailure(obj);
            event.invoke(obj);
            Coroutines.INSTANCE.ioSafe(coroutineScope, new AnonymousClass3(this.$settingsManager, MainActivity.this, null));
            Coroutines.INSTANCE.ioSafe(coroutineScope, new AnonymousClass4(MainActivity.this, null));
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainActivity$onCreate$12$3, reason: invalid class name */
        /* JADX INFO: compiled from: MainActivity.kt */
        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "com.lagradost.cloudstream3.MainActivity$onCreate$12$3", f = "MainActivity.kt", i = {2}, l = {1364, 1368, 1379}, m = "invokeSuspend", n = {"autoDownloadPlugin"}, nl = {1368, 1372, 1384}, s = {"L$0"}, v = 2)
        static final class AnonymousClass3 extends SuspendLambda implements Function3<CoroutineScope, CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ SharedPreferences $settingsManager;
            Object L$0;
            int label;
            final /* synthetic */ MainActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass3(SharedPreferences sharedPreferences, MainActivity mainActivity, Continuation<? super AnonymousClass3> continuation) {
                super(3, continuation);
                this.$settingsManager = sharedPreferences;
                this.this$0 = mainActivity;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(CoroutineScope coroutineScope, CoroutineScope coroutineScope2, Continuation<? super Unit> continuation) {
                return new AnonymousClass3(this.$settingsManager, this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code restructure failed: missing block: B:25:0x008f, code lost:
            
                if (com.lagradost.cloudstream3.plugins.PluginManager.INSTANCE.___DO_NOT_CALL_FROM_A_PLUGIN_downloadNotExistingPluginsAndLoad(r6.this$0, r7, r6) == r0) goto L26;
             */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r7) {
                /*
                    r6 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r1 = r6.label
                    r2 = 3
                    r3 = 2
                    r4 = 1
                    if (r1 == 0) goto L26
                    if (r1 == r4) goto L22
                    if (r1 == r3) goto L22
                    if (r1 != r2) goto L1a
                    java.lang.Object r6 = r6.L$0
                    com.lagradost.cloudstream3.AutoDownloadMode r6 = (com.lagradost.cloudstream3.AutoDownloadMode) r6
                    kotlin.ResultKt.throwOnFailure(r7)
                    goto L92
                L1a:
                    java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                    java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                    r6.<init>(r7)
                    throw r6
                L22:
                    kotlin.ResultKt.throwOnFailure(r7)
                    goto L5d
                L26:
                    kotlin.ResultKt.throwOnFailure(r7)
                    android.content.SharedPreferences r7 = r6.$settingsManager
                    com.lagradost.cloudstream3.MainActivity r1 = r6.this$0
                    int r5 = com.lagradost.cloudstream3.R.string.auto_update_plugins_key
                    java.lang.String r1 = r1.getString(r5)
                    boolean r7 = r7.getBoolean(r1, r4)
                    if (r7 == 0) goto L4b
                    com.lagradost.cloudstream3.plugins.PluginManager r7 = com.lagradost.cloudstream3.plugins.PluginManager.INSTANCE
                    com.lagradost.cloudstream3.MainActivity r1 = r6.this$0
                    android.app.Activity r1 = (android.app.Activity) r1
                    r3 = r6
                    kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
                    r6.label = r4
                    java.lang.Object r7 = r7.___DO_NOT_CALL_FROM_A_PLUGIN_updateAllOnlinePluginsAndLoadThem(r1, r3)
                    if (r7 != r0) goto L5d
                    goto L91
                L4b:
                    com.lagradost.cloudstream3.plugins.PluginManager r7 = com.lagradost.cloudstream3.plugins.PluginManager.INSTANCE
                    com.lagradost.cloudstream3.MainActivity r1 = r6.this$0
                    android.content.Context r1 = (android.content.Context) r1
                    r4 = r6
                    kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                    r6.label = r3
                    java.lang.Object r7 = r7.___DO_NOT_CALL_FROM_A_PLUGIN_loadAllOnlinePlugins(r1, r4)
                    if (r7 != r0) goto L5d
                    goto L91
                L5d:
                    com.lagradost.cloudstream3.AutoDownloadMode$Companion r7 = com.lagradost.cloudstream3.AutoDownloadMode.INSTANCE
                    android.content.SharedPreferences r1 = r6.$settingsManager
                    com.lagradost.cloudstream3.MainActivity r3 = r6.this$0
                    int r4 = com.lagradost.cloudstream3.R.string.auto_download_plugins_key
                    java.lang.String r3 = r3.getString(r4)
                    r4 = 0
                    int r1 = r1.getInt(r3, r4)
                    com.lagradost.cloudstream3.AutoDownloadMode r7 = r7.getEnum(r1)
                    if (r7 != 0) goto L76
                    com.lagradost.cloudstream3.AutoDownloadMode r7 = com.lagradost.cloudstream3.AutoDownloadMode.Disable
                L76:
                    com.lagradost.cloudstream3.AutoDownloadMode r1 = com.lagradost.cloudstream3.AutoDownloadMode.Disable
                    if (r7 == r1) goto L92
                    com.lagradost.cloudstream3.plugins.PluginManager r1 = com.lagradost.cloudstream3.plugins.PluginManager.INSTANCE
                    com.lagradost.cloudstream3.MainActivity r3 = r6.this$0
                    android.app.Activity r3 = (android.app.Activity) r3
                    r4 = r6
                    kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                    java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
                    r6.L$0 = r5
                    r6.label = r2
                    java.lang.Object r6 = r1.___DO_NOT_CALL_FROM_A_PLUGIN_downloadNotExistingPluginsAndLoad(r3, r7, r4)
                    if (r6 != r0) goto L92
                L91:
                    return r0
                L92:
                    kotlin.Unit r6 = kotlin.Unit.INSTANCE
                    return r6
                */
                throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.MainActivity.AnonymousClass12.AnonymousClass3.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainActivity$onCreate$12$4, reason: invalid class name */
        /* JADX INFO: compiled from: MainActivity.kt */
        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "com.lagradost.cloudstream3.MainActivity$onCreate$12$4", f = "MainActivity.kt", i = {}, l = {1387}, m = "invokeSuspend", n = {}, nl = {1391}, s = {}, v = 2)
        static final class AnonymousClass4 extends SuspendLambda implements Function3<CoroutineScope, CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ MainActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass4(MainActivity mainActivity, Continuation<? super AnonymousClass4> continuation) {
                super(3, continuation);
                this.this$0 = mainActivity;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(CoroutineScope coroutineScope, CoroutineScope coroutineScope2, Continuation<? super Unit> continuation) {
                return new AnonymousClass4(this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    if (PluginManager.INSTANCE.___DO_NOT_CALL_FROM_A_PLUGIN_loadAllLocalPlugins(this.this$0, false, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$10$0(AlertDialog.Builder builder, DialogInterface dialogInterface, int i) {
        AlertDialog.Builder builder2 = new AlertDialog.Builder(builder.getContext());
        builder2.setTitle(R.string.safe_mode_title);
        builder2.setMessage(lastError);
        builder2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$setUserData(MainActivity mainActivity, Resource<? extends SyncAPI.AbstractSyncStatus> resource) {
        BottomResultviewPreviewBinding bottomResultviewPreviewBinding;
        if (mainActivity.isLocalList || (bottomResultviewPreviewBinding = mainActivity.bottomPreviewBinding) == null) {
            return;
        }
        if (resource instanceof Resource.Success) {
            bottomResultviewPreviewBinding.resultviewPreviewBookmark.setEnabled(true);
            Resource.Success success = (Resource.Success) resource;
            bottomResultviewPreviewBinding.resultviewPreviewBookmark.setText(((SyncAPI.AbstractSyncStatus) success.getValue()).getStatus().getStringRes());
            bottomResultviewPreviewBinding.resultviewPreviewBookmark.setIconResource(((SyncAPI.AbstractSyncStatus) success.getValue()).getStatus().getIconRes());
            return;
        }
        if (resource instanceof Resource.Failure) {
            bottomResultviewPreviewBinding.resultviewPreviewBookmark.setEnabled(false);
            bottomResultviewPreviewBinding.resultviewPreviewBookmark.setIconResource(R.drawable.ic_baseline_bookmark_border_24);
            bottomResultviewPreviewBinding.resultviewPreviewBookmark.setText(((Resource.Failure) resource).getErrorString());
        } else {
            bottomResultviewPreviewBinding.resultviewPreviewBookmark.setEnabled(false);
            UIHelper uIHelper = UIHelper.INSTANCE;
            MaterialButton resultviewPreviewBookmark = bottomResultviewPreviewBinding.resultviewPreviewBookmark;
            Intrinsics.checkNotNullExpressionValue(resultviewPreviewBookmark, "resultviewPreviewBookmark");
            UIHelper.showProgress$default(uIHelper, resultviewPreviewBookmark, 0, 1, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$setWatchStatus(MainActivity mainActivity, WatchType watchType) {
        BottomResultviewPreviewBinding bottomResultviewPreviewBinding;
        MaterialButton materialButton;
        if (!mainActivity.isLocalList || watchType == null || (bottomResultviewPreviewBinding = mainActivity.bottomPreviewBinding) == null || (materialButton = bottomResultviewPreviewBinding.resultviewPreviewBookmark) == null) {
            return;
        }
        materialButton.setIconResource(watchType.getIconRes());
        materialButton.setText(watchType.getStringRes());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$setSubscribeStatus(final MainActivity mainActivity, Boolean bool) {
        final ImageView imageView;
        int i;
        BottomResultviewPreviewBinding bottomResultviewPreviewBinding = mainActivity.bottomPreviewBinding;
        if (bottomResultviewPreviewBinding == null || (imageView = bottomResultviewPreviewBinding.resultviewPreviewSubscribe) == null) {
            return;
        }
        if (bool != null) {
            if (bool.booleanValue()) {
                i = R.drawable.ic_baseline_notifications_active_24;
            } else {
                i = R.drawable.baseline_notifications_none_24;
            }
            imageView.setImageResource(i);
        }
        imageView.setVisibility(bool != null ? 0 : 8);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda30
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.onCreate$setSubscribeStatus$lambda$13$0(this.f$0, imageView, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$setSubscribeStatus$lambda$13$0(final MainActivity mainActivity, final ImageView imageView, View view) {
        mainActivity.getViewModel().toggleSubscriptionStatus(imageView.getContext(), new Function1() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.onCreate$setSubscribeStatus$lambda$13$0$0(imageView, mainActivity, (Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$setSubscribeStatus$lambda$13$0$0(ImageView imageView, MainActivity mainActivity, Boolean bool) {
        int i;
        String strAsStringNull;
        ResultData resultData;
        if (bool == null) {
            return Unit.INSTANCE;
        }
        if (bool.booleanValue()) {
            SubscriptionWorkManager.INSTANCE.enqueuePeriodicWork(imageView.getContext());
            i = R.string.subscription_new;
        } else {
            i = R.string.subscription_deleted;
        }
        Resource<ResultData> value = mainActivity.getViewModel().getPage().getValue();
        Resource.Success success = value instanceof Resource.Success ? (Resource.Success) value : null;
        if ((success == null || (resultData = (ResultData) success.getValue()) == null || (strAsStringNull = resultData.getTitle()) == null) && (strAsStringNull = TextUtilKt.txt(R.string.no_data, new Object[0]).asStringNull(imageView.getContext())) == null) {
            strAsStringNull = "";
        }
        CommonActivity.INSTANCE.showToast(TextUtilKt.txt(i, strAsStringNull), (Integer) 0);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainActivity$onCreate$14, reason: invalid class name */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass14 extends FunctionReferenceImpl implements Function1<WatchType, Unit> {
        AnonymousClass14() {
            super(1, Intrinsics.Kotlin.class, "setWatchStatus", "onCreate$setWatchStatus(Lcom/lagradost/cloudstream3/MainActivity;Lcom/lagradost/cloudstream3/ui/WatchType;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(WatchType watchType) {
            invoke2(watchType);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(WatchType watchType) {
            MainActivity.onCreate$setWatchStatus(MainActivity.this, watchType);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainActivity$onCreate$15, reason: invalid class name */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass15 extends FunctionReferenceImpl implements Function1<Resource<? extends SyncAPI.AbstractSyncStatus>, Unit> {
        AnonymousClass15() {
            super(1, Intrinsics.Kotlin.class, "setUserData", "onCreate$setUserData(Lcom/lagradost/cloudstream3/MainActivity;Lcom/lagradost/cloudstream3/mvvm/Resource;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Resource<? extends SyncAPI.AbstractSyncStatus> resource) {
            invoke2(resource);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Resource<? extends SyncAPI.AbstractSyncStatus> resource) {
            MainActivity.onCreate$setUserData(MainActivity.this, resource);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainActivity$onCreate$16, reason: invalid class name */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass16 extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
        AnonymousClass16() {
            super(1, Intrinsics.Kotlin.class, "setSubscribeStatus", "onCreate$setSubscribeStatus(Lcom/lagradost/cloudstream3/MainActivity;Ljava/lang/Boolean;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke2(bool);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Boolean bool) {
            MainActivity.onCreate$setSubscribeStatus(MainActivity.this, bool);
        }
    }

    static final Unit onCreate$lambda$14(final MainActivity mainActivity, Resource resource) {
        if (resource == null) {
            mainActivity.hidePreviewPopupDialog();
            return Unit.INSTANCE;
        }
        if (resource instanceof Resource.Failure) {
            CommonActivity.showToast$default(CommonActivity.INSTANCE, R.string.error, (Integer) null, 2, (Object) null);
            mainActivity.getViewModel().clear();
            mainActivity.hidePreviewPopupDialog();
        } else if (resource instanceof Resource.Loading) {
            BottomResultviewPreviewBinding bottomResultviewPreviewBindingShowPreviewPopupDialog = mainActivity.showPreviewPopupDialog();
            FrameLayout resultviewPreviewLoading = bottomResultviewPreviewBindingShowPreviewPopupDialog.resultviewPreviewLoading;
            Intrinsics.checkNotNullExpressionValue(resultviewPreviewLoading, "resultviewPreviewLoading");
            resultviewPreviewLoading.setVisibility(0);
            LinearLayout resultviewPreviewResult = bottomResultviewPreviewBindingShowPreviewPopupDialog.resultviewPreviewResult;
            Intrinsics.checkNotNullExpressionValue(resultviewPreviewResult, "resultviewPreviewResult");
            resultviewPreviewResult.setVisibility(8);
            bottomResultviewPreviewBindingShowPreviewPopupDialog.resultviewPreviewLoadingShimmer.startShimmer();
        } else {
            if (!(resource instanceof Resource.Success)) {
                throw new NoWhenBranchMatchedException();
            }
            final ResultData resultData = (ResultData) ((Resource.Success) resource).getValue();
            final BottomResultviewPreviewBinding bottomResultviewPreviewBindingShowPreviewPopupDialog2 = mainActivity.showPreviewPopupDialog();
            FrameLayout resultviewPreviewLoading2 = bottomResultviewPreviewBindingShowPreviewPopupDialog2.resultviewPreviewLoading;
            Intrinsics.checkNotNullExpressionValue(resultviewPreviewLoading2, "resultviewPreviewLoading");
            resultviewPreviewLoading2.setVisibility(8);
            LinearLayout resultviewPreviewResult2 = bottomResultviewPreviewBindingShowPreviewPopupDialog2.resultviewPreviewResult;
            Intrinsics.checkNotNullExpressionValue(resultviewPreviewResult2, "resultviewPreviewResult");
            resultviewPreviewResult2.setVisibility(0);
            bottomResultviewPreviewBindingShowPreviewPopupDialog2.resultviewPreviewLoadingShimmer.stopShimmer();
            bottomResultviewPreviewBindingShowPreviewPopupDialog2.resultviewPreviewTitle.setText(resultData.getTitle());
            TextUtilKt.setText(bottomResultviewPreviewBindingShowPreviewPopupDialog2.resultviewPreviewMetaType, resultData.getTypeText());
            TextUtilKt.setText(bottomResultviewPreviewBindingShowPreviewPopupDialog2.resultviewPreviewMetaYear, resultData.getYearText());
            TextUtilKt.setText(bottomResultviewPreviewBindingShowPreviewPopupDialog2.resultviewPreviewMetaDuration, resultData.getDurationText());
            TextUtilKt.setText(bottomResultviewPreviewBindingShowPreviewPopupDialog2.resultviewPreviewMetaRating, resultData.getRatingText());
            TextUtilKt.setTextHtml(bottomResultviewPreviewBindingShowPreviewPopupDialog2.resultviewPreviewDescription, resultData.getPlotText());
            if (Globals.INSTANCE.isLayout(1)) {
                ImageLoader imageLoader = ImageLoader.INSTANCE;
                ImageView resultviewPreviewPoster = bottomResultviewPreviewBindingShowPreviewPopupDialog2.resultviewPreviewPoster;
                Intrinsics.checkNotNullExpressionValue(resultviewPreviewPoster, "resultviewPreviewPoster");
                String posterImage = resultData.getPosterImage();
                if (posterImage == null) {
                    posterImage = resultData.getPosterBackgroundImage();
                }
                ImageLoader.loadImage$default(imageLoader, resultviewPreviewPoster, posterImage, resultData.getPosterHeaders(), (Function1) null, 4, (Object) null);
            } else {
                ImageLoader imageLoader2 = ImageLoader.INSTANCE;
                ImageView resultviewPreviewPoster2 = bottomResultviewPreviewBindingShowPreviewPopupDialog2.resultviewPreviewPoster;
                Intrinsics.checkNotNullExpressionValue(resultviewPreviewPoster2, "resultviewPreviewPoster");
                String posterBackgroundImage = resultData.getPosterBackgroundImage();
                if (posterBackgroundImage == null) {
                    posterBackgroundImage = resultData.getPosterImage();
                }
                ImageLoader.loadImage$default(imageLoader2, resultviewPreviewPoster2, posterBackgroundImage, resultData.getPosterHeaders(), (Function1) null, 4, (Object) null);
            }
            onCreate$setUserData(mainActivity, mainActivity.getSyncViewModel().getUserData().getValue());
            onCreate$setWatchStatus(mainActivity, mainActivity.getViewModel().getWatchStatus().getValue());
            onCreate$setSubscribeStatus(mainActivity, mainActivity.getViewModel().getSubscribeStatus().getValue());
            bottomResultviewPreviewBindingShowPreviewPopupDialog2.resultviewPreviewBookmark.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda17
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MainActivity.onCreate$lambda$14$1$0(this.f$0, view);
                }
            });
            LifecycleKt.observeNullable(mainActivity, mainActivity.getViewModel().getFavoriteStatus(), new Function1() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda18
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MainActivity.onCreate$lambda$14$1$1(bottomResultviewPreviewBindingShowPreviewPopupDialog2, (Boolean) obj);
                }
            });
            bottomResultviewPreviewBindingShowPreviewPopupDialog2.resultviewPreviewFavorite.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda19
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MainActivity.onCreate$lambda$14$1$2(this.f$0, view);
                }
            });
            if (Globals.INSTANCE.isLayout(1)) {
                bottomResultviewPreviewBindingShowPreviewPopupDialog2.resultviewPreviewDescription.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda20
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        MainActivity.onCreate$lambda$14$1$3(resultData, view);
                    }
                });
            }
            bottomResultviewPreviewBindingShowPreviewPopupDialog2.resultviewPreviewMoreInfo.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda21
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MainActivity.onCreate$lambda$14$1$4(this.f$0, view);
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$14$1$0(final MainActivity mainActivity, View view) {
        SyncWatchType status;
        SyncAPI.AbstractSyncStatus abstractSyncStatus;
        if (mainActivity.isLocalList) {
            WatchType value = mainActivity.getViewModel().getWatchStatus().getValue();
            if (value == null) {
                value = WatchType.NONE;
            }
            SingleSelectionHelper singleSelectionHelper = SingleSelectionHelper.INSTANCE;
            MainActivity mainActivity2 = mainActivity;
            EnumEntries<WatchType> entries = WatchType.getEntries();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(entries, 10));
            Iterator<WatchType> it = entries.iterator();
            while (it.hasNext()) {
                arrayList.add(mainActivity.getString(it.next().getStringRes()));
            }
            List<String> list = CollectionsKt.toList(arrayList);
            int iOrdinal = value.ordinal();
            String string = mainActivity.getString(R.string.action_add_to_bookmarks);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            singleSelectionHelper.showBottomDialog(mainActivity2, list, iOrdinal, string, false, new Function0() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda24
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            }, new Function1() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda25
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MainActivity.onCreate$lambda$14$1$0$2(this.f$0, ((Integer) obj).intValue());
                }
            });
            return;
        }
        Resource<SyncAPI.AbstractSyncStatus> value2 = mainActivity.getSyncViewModel().getUserData().getValue();
        Resource.Success success = value2 instanceof Resource.Success ? (Resource.Success) value2 : null;
        if (success == null || (abstractSyncStatus = (SyncAPI.AbstractSyncStatus) success.getValue()) == null || (status = abstractSyncStatus.getStatus()) == null) {
            status = SyncWatchType.NONE;
        }
        SingleSelectionHelper singleSelectionHelper2 = SingleSelectionHelper.INSTANCE;
        MainActivity mainActivity3 = mainActivity;
        EnumEntries<SyncWatchType> entries2 = SyncWatchType.getEntries();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(entries2, 10));
        Iterator<SyncWatchType> it2 = entries2.iterator();
        while (it2.hasNext()) {
            arrayList2.add(mainActivity.getString(it2.next().getStringRes()));
        }
        List<String> list2 = CollectionsKt.toList(arrayList2);
        int iOrdinal2 = status.ordinal();
        String string2 = mainActivity.getString(R.string.action_add_to_bookmarks);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        singleSelectionHelper2.showBottomDialog(mainActivity3, list2, iOrdinal2, string2, false, new Function0() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda26
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda27
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.onCreate$lambda$14$1$0$5(this.f$0, ((Integer) obj).intValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$14$1$0$2(MainActivity mainActivity, int i) {
        ResultViewModel2.updateWatchStatus$default(mainActivity.getViewModel(), WatchType.getEntries().get(i), mainActivity, null, null, 12, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$14$1$0$5(MainActivity mainActivity, int i) {
        mainActivity.getSyncViewModel().setStatus(SyncWatchType.getEntries().get(i).getInternalId());
        mainActivity.getSyncViewModel().publishUserData();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$14$1$1(BottomResultviewPreviewBinding bottomResultviewPreviewBinding, Boolean bool) {
        int i;
        ImageView resultviewPreviewFavorite = bottomResultviewPreviewBinding.resultviewPreviewFavorite;
        Intrinsics.checkNotNullExpressionValue(resultviewPreviewFavorite, "resultviewPreviewFavorite");
        resultviewPreviewFavorite.setVisibility(bool != null ? 0 : 8);
        if (bool == null) {
            return Unit.INSTANCE;
        }
        if (bool.booleanValue()) {
            i = R.drawable.ic_baseline_favorite_24;
        } else {
            i = R.drawable.ic_baseline_favorite_border_24;
        }
        bottomResultviewPreviewBinding.resultviewPreviewFavorite.setImageResource(i);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$14$1$2(final MainActivity mainActivity, View view) {
        mainActivity.getViewModel().toggleFavoriteStatus(mainActivity, new Function1() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.onCreate$lambda$14$1$2$0(this.f$0, (Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$14$1$2$0(MainActivity mainActivity, Boolean bool) {
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
        Resource<ResultData> value = mainActivity.getViewModel().getPage().getValue();
        Resource.Success success = value instanceof Resource.Success ? (Resource.Success) value : null;
        if ((success == null || (resultData = (ResultData) success.getValue()) == null || (strAsStringNull = resultData.getTitle()) == null) && (strAsStringNull = TextUtilKt.txt(R.string.no_data, new Object[0]).asStringNull(mainActivity)) == null) {
            strAsStringNull = "";
        }
        CommonActivity.INSTANCE.showToast(TextUtilKt.txt(i, strAsStringNull), (Integer) 0);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$14$1$3(ResultData resultData, View view) {
        Context context = view.getContext();
        if (context != null) {
            new AlertDialog.Builder(context, R.style.AlertDialogCustom).setMessage(AppContextUtils.INSTANCE.html(resultData.getPlotText().asString(context))).setTitle(resultData.getPlotHeaderText().asString(context)).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$14$1$4(MainActivity mainActivity, View view) {
        mainActivity.getViewModel().clear();
        mainActivity.hidePreviewPopupDialog();
        SearchResponse searchResponse = mainActivity.lastPopup;
        if (searchResponse != null) {
            AppContextUtils.loadSearchResult$default(AppContextUtils.INSTANCE, mainActivity, searchResponse, 0, null, 6, null);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainActivity$onCreate$18, reason: invalid class name */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/MainActivity;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.MainActivity$onCreate$18", f = "MainActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass18 extends SuspendLambda implements Function3<CoroutineScope, MainActivity, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass18(Continuation<? super AnonymousClass18> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, MainActivity mainActivity, Continuation<? super Unit> continuation) {
            return MainActivity.this.new AnonymousClass18(continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            final MainActivity mainActivity = MainActivity.this;
            mainActivity.runOnUiThread(new Runnable() { // from class: com.lagradost.cloudstream3.MainActivity$onCreate$18$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.AnonymousClass18.invokeSuspend$lambda$0(mainActivity);
                }
            });
            return Unit.INSTANCE;
        }

        static final void invokeSuspend$lambda$0(final MainActivity mainActivity) {
            LiveData<String> currentApiName;
            mainActivity.libraryViewModel = (LibraryViewModel) new ViewModelProvider(mainActivity).get(LibraryViewModel.class);
            LibraryViewModel libraryViewModel = mainActivity.libraryViewModel;
            if (libraryViewModel == null || (currentApiName = libraryViewModel.getCurrentApiName()) == null) {
                return;
            }
            currentApiName.observe(mainActivity, new MainActivity$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.lagradost.cloudstream3.MainActivity$onCreate$18$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MainActivity.AnonymousClass18.invokeSuspend$lambda$0$0(mainActivity, (String) obj);
                }
            }));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invokeSuspend$lambda$0$0(MainActivity mainActivity, String str) {
            Integer icon;
            LibraryViewModel libraryViewModel = mainActivity.libraryViewModel;
            SyncRepo currentSyncApi = libraryViewModel != null ? libraryViewModel.getCurrentSyncApi() : null;
            StringBuilder sb = new StringBuilder();
            sb.append(currentSyncApi != null ? currentSyncApi.getName() : null);
            sb.append(", ");
            sb.append(currentSyncApi != null ? currentSyncApi.getIdPrefix() : null);
            Log.i("SYNC_API", sb.toString());
            int iIntValue = (Intrinsics.areEqual(currentSyncApi != null ? currentSyncApi.getIdPrefix() : null, AccountManager.INSTANCE.getLocalListApi().getIdPrefix()) || currentSyncApi == null || (icon = currentSyncApi.getIcon()) == null) ? R.drawable.library_icon_selector : icon.intValue();
            ActivityMainBinding binding = mainActivity.getBinding();
            if (binding != null) {
                MenuItem menuItemFindItem = binding.navRailView.getMenu().findItem(R.id.navigation_library);
                if (menuItemFindItem != null) {
                    menuItemFindItem.setIcon(iIntValue);
                }
                MenuItem menuItemFindItem2 = binding.navView.getMenu().findItem(R.id.navigation_library);
                if (menuItemFindItem2 != null) {
                    menuItemFindItem2.setIcon(iIntValue);
                }
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainActivity$onCreate$19, reason: invalid class name */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/MainActivity;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.MainActivity$onCreate$19", f = "MainActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass19 extends SuspendLambda implements Function3<CoroutineScope, MainActivity, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass19(Continuation<? super AnonymousClass19> continuation) {
            super(3, continuation);
        }

        static final Object invokeSuspend$lambda$0(MainAPI mainAPI) {
            return mainAPI;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, MainActivity mainActivity, Continuation<? super Unit> continuation) {
            return new AnonymousClass19(continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            APIHolder.INSTANCE.initAll();
            APIHolder.INSTANCE.setApis(APIHolder.INSTANCE.getAllProviders().distinctBy(new Function1() { // from class: com.lagradost.cloudstream3.MainActivity$onCreate$19$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return MainActivity.AnonymousClass19.invokeSuspend$lambda$0((MainAPI) obj2);
                }
            }));
            return Unit.INSTANCE;
        }
    }

    static final void onCreate$lambda$15(final MainActivity mainActivity, final SharedPreferences sharedPreferences, NavController navController, NavDestination navDestination, Bundle bundle) {
        String str;
        Intrinsics.checkNotNullParameter(navController, "<unused var>");
        Intrinsics.checkNotNullParameter(navDestination, "navDestination");
        mainActivity.updateNavBar(navDestination);
        if (mainActivity.matchDestination(navDestination, R.id.navigation_search) && (str = nextSearchQuery) != null && !StringsKt.isBlank(str) && bundle != null) {
            bundle.putString(SearchFragment.SEARCH_QUERY, nextSearchQuery);
        }
        if (mainActivity.matchDestination(navDestination, R.id.navigation_home)) {
            BackPressedCallbackHelper.INSTANCE.attachBackPressedCallback(mainActivity, "MainActivity", new Function1() { // from class: com.lagradost.cloudstream3.MainActivity$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MainActivity.onCreate$lambda$15$1(this.f$0, sharedPreferences, (BackPressedCallbackHelper.CallbackHelper) obj);
                }
            });
        } else {
            BackPressedCallbackHelper.INSTANCE.detachBackPressedCallback(mainActivity, "MainActivity");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$15$1(MainActivity mainActivity, SharedPreferences sharedPreferences, BackPressedCallbackHelper.CallbackHelper attachBackPressedCallback) {
        Intrinsics.checkNotNullParameter(attachBackPressedCallback, "$this$attachBackPressedCallback");
        Intrinsics.checkNotNull(sharedPreferences);
        mainActivity.showConfirmExitDialog(sharedPreferences);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreate$lambda$16$0(MainActivity mainActivity, NavController navController, MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        return mainActivity.onNavDestinationSelected(item, navController);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreate$lambda$17$0(MainActivity mainActivity, NavController navController, MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        return mainActivity.onNavDestinationSelected(item, navController);
    }

    private static final void onCreate$lambda$17$noFocus(View view) {
        view.setTag(view.getContext().getString(R.string.tv_no_focus_tag));
        ViewGroup viewGroup = view instanceof ViewGroup ? (ViewGroup) view : null;
        if (viewGroup != null) {
            Iterator<View> it = ViewGroupKt.getChildren(viewGroup).iterator();
            while (it.hasNext()) {
                onCreate$lambda$17$noFocus(it.next());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$17$2(MainActivity mainActivity, View view) {
        AccountHelper.INSTANCE.showAccountSelectLinear(mainActivity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$17$3(ImageView imageView, LinearLayout linearLayout, DataStoreHelper.Account account) {
        if (account != null) {
            if (imageView != null) {
                ImageLoader.loadImage$default(ImageLoader.INSTANCE, imageView, account.getImage(), (Function1) null, 2, (Object) null);
            }
            Intrinsics.checkNotNull(linearLayout);
            linearLayout.setVisibility(0);
        } else {
            Intrinsics.checkNotNull(linearLayout);
            linearLayout.setVisibility(8);
        }
        return Unit.INSTANCE;
    }

    static final boolean onCreate$lambda$19(MainActivity mainActivity, View view) {
        FrameLayout root;
        ActivityMainBinding activityMainBinding = mainActivity.binding;
        RecyclerView recyclerView = (activityMainBinding == null || (root = activityMainBinding.getRoot()) == null) ? null : (RecyclerView) root.findViewById(R.id.home_master_recycler);
        if (recyclerView != null) {
            recyclerView.smoothScrollToPosition(0);
        }
        return recyclerView != null;
    }

    static final boolean onCreate$lambda$20(MainActivity mainActivity, View view) {
        FrameLayout root;
        ViewPager2 viewPager2;
        Sequence<View> children;
        ActivityMainBinding activityMainBinding = mainActivity.binding;
        if (activityMainBinding == null || (root = activityMainBinding.getRoot()) == null || (viewPager2 = (ViewPager2) root.findViewById(R.id.viewpager)) == null) {
            return false;
        }
        try {
            View view2 = ViewGroupKt.get(viewPager2, 0);
            RecyclerView recyclerView = view2 instanceof RecyclerView ? (RecyclerView) view2 : null;
            if (recyclerView != null && (children = ViewGroupKt.getChildren(recyclerView)) != null) {
                Iterator<View> it = children.iterator();
                while (it.hasNext()) {
                    RecyclerView recyclerView2 = (RecyclerView) it.next().findViewById(R.id.page_recyclerview);
                    if (recyclerView2 != null) {
                        recyclerView2.smoothScrollToPosition(0);
                    }
                }
                return true;
            }
            return false;
        } catch (IndexOutOfBoundsException unused) {
            return true;
        } catch (Throwable th) {
            ArchComponentExtKt.logError(th);
            return true;
        }
    }

    static final boolean onCreate$lambda$21(MainActivity mainActivity, View view) {
        FrameLayout root;
        RecyclerView recyclerView;
        Integer[] numArr = {Integer.valueOf(R.id.search_master_recycler), Integer.valueOf(R.id.search_autofit_results), Integer.valueOf(R.id.search_history_recycler)};
        for (int i = 0; i < 3; i++) {
            int iIntValue = numArr[i].intValue();
            ActivityMainBinding activityMainBinding = mainActivity.binding;
            if (activityMainBinding == null || (root = activityMainBinding.getRoot()) == null || (recyclerView = (RecyclerView) root.findViewById(iIntValue)) == null) {
                return false;
            }
            recyclerView.smoothScrollToPosition(0);
        }
        return true;
    }

    static final boolean onCreate$lambda$22(MainActivity mainActivity, View view) {
        RecyclerView recyclerView;
        FrameLayout root;
        FrameLayout root2;
        ActivityMainBinding activityMainBinding = mainActivity.binding;
        if (activityMainBinding == null || (root2 = activityMainBinding.getRoot()) == null || (recyclerView = (RecyclerView) root2.findViewById(R.id.download_list)) == null) {
            ActivityMainBinding activityMainBinding2 = mainActivity.binding;
            recyclerView = (activityMainBinding2 == null || (root = activityMainBinding2.getRoot()) == null) ? null : (RecyclerView) root.findViewById(R.id.download_child_list);
        }
        if (recyclerView != null) {
            recyclerView.smoothScrollToPosition(0);
        }
        return recyclerView != null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v6, types: [T, java.lang.String] */
    private static final Unit onCreate$lambda$23(Ref.ObjectRef objectRef) {
        Iterator<MainAPI> it = APIHolder.INSTANCE.getAllProviders().iterator();
        while (it.hasNext()) {
            objectRef.element = ((String) objectRef.element) + "<data android:scheme=\"https\" android:host=\"" + StringsKt.removePrefix(it.next().getMainUrl(), (CharSequence) Utils.HTTPS) + "\" android:pathPrefix=\"/\"/>\n";
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainActivity$onCreate$29, reason: invalid class name */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/MainActivity;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.MainActivity$onCreate$29", f = "MainActivity.kt", i = {}, l = {1983}, m = "invokeSuspend", n = {}, nl = {1984}, s = {}, v = 2)
    static final class AnonymousClass29 extends SuspendLambda implements Function3<CoroutineScope, MainActivity, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass29(Continuation<? super AnonymousClass29> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, MainActivity mainActivity, Continuation<? super Unit> continuation) {
            return MainActivity.this.new AnonymousClass29(continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (InAppUpdater.runAutoUpdate$default(InAppUpdater.INSTANCE, MainActivity.this, false, false, this, 3, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainActivity$onCreate$30, reason: invalid class name */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/MainActivity;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.MainActivity$onCreate$30", f = "MainActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass30 extends SuspendLambda implements Function3<CoroutineScope, MainActivity, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass30(Continuation<? super AnonymousClass30> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, MainActivity mainActivity, Continuation<? super Unit> continuation) {
            return new AnonymousClass30(continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            DataStoreHelper.INSTANCE.migrateResumeWatching();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainActivity$onCreate$31, reason: invalid class name */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/lagradost/cloudstream3/MainActivity;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.MainActivity$onCreate$31", f = "MainActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass31 extends SuspendLambda implements Function2<MainActivity, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass31(Continuation<? super AnonymousClass31> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MainActivity.this.new AnonymousClass31(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(MainActivity mainActivity, Continuation<? super Unit> continuation) {
            return ((AnonymousClass31) create(mainActivity, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            TvChannelUtils tvChannelUtils = TvChannelUtils.INSTANCE;
            MainActivity mainActivity = MainActivity.this;
            MainActivity mainActivity2 = mainActivity;
            String string = mainActivity.getString(R.string.app_name);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            Long channelId = tvChannelUtils.getChannelId(mainActivity2, string);
            if (channelId == null) {
                Log.d("TvChannel", "Channel not found, creating");
                TvChannelUtils.INSTANCE.createTvChannel(MainActivity.this);
            } else {
                Boxing.boxInt(Log.d("TvChannel", "Channel ID: " + channelId.longValue()));
            }
            return Unit.INSTANCE;
        }
    }

    static final Unit onCreate$lambda$25(MainActivity mainActivity, BackPressedCallbackHelper.CallbackHelper attachBackPressedCallback) {
        Intrinsics.checkNotNullParameter(attachBackPressedCallback, "$this$attachBackPressedCallback");
        UIHelper.INSTANCE.setNavigationBarColorCompat(mainActivity, R.attr.primaryGrayBackground);
        CommonActivity.INSTANCE.updateLocale(mainActivity);
        attachBackPressedCallback.runDefault();
        return Unit.INSTANCE;
    }

    @Override // com.lagradost.cloudstream3.utils.BiometricAuthenticator.BiometricCallback
    public void onAuthenticationSuccess() {
        FragmentContainerView fragmentContainerView;
        ActivityMainBinding activityMainBinding = this.binding;
        if (activityMainBinding == null || (fragmentContainerView = activityMainBinding.navHostFragment) == null) {
            return;
        }
        fragmentContainerView.setVisibility(0);
    }

    @Override // com.lagradost.cloudstream3.utils.BiometricAuthenticator.BiometricCallback
    public void onAuthenticationError() {
        finish();
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public final Object checkGithubConnectivity(Continuation<? super Boolean> continuation) {
        AnonymousClass1 anonymousClass1;
        boolean zAreEqual;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Requests app2 = MainActivityKt.getApp();
                anonymousClass1.label = 1;
                Object obj2 = Requests.get$default(app2, "https://raw.githubusercontent.com/recloudstream/.github/master/connectivitycheck", null, null, null, null, false, 0, null, 5L, null, false, null, anonymousClass1, 3838, null);
                if (obj2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj = obj2;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            zAreEqual = Intrinsics.areEqual(StringsKt.trim((CharSequence) ((NiceResponse) obj).getText()).toString(), "ok");
        } catch (Throwable unused) {
            zAreEqual = false;
        }
        return Boxing.boxBoolean(zAreEqual);
    }
}
