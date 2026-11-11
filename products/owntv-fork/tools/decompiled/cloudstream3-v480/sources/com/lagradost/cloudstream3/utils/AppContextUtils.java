package com.lagradost.cloudstream3.utils;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.Spanned;
import android.text.SpannedString;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.text.HtmlCompat;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.fragment.FragmentKt;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.tvprovider.media.tv.PreviewChannelHelper;
import androidx.tvprovider.media.tv.TvContractCompat;
import androidx.tvprovider.media.tv.WatchNextProgram;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.common.wrappers.PackageManagerWrapper;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.tasks.Task;
import com.google.android.material.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.CommonActivity$$ExternalSyntheticApiModelOutline0;
import com.lagradost.cloudstream3.DubStatus;
import com.lagradost.cloudstream3.HomePageList;
import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivity;
import com.lagradost.cloudstream3.SearchQuality;
import com.lagradost.cloudstream3.SearchResponse;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.actions.temp.CloudStreamPackage;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.plugins.Repository;
import com.lagradost.cloudstream3.plugins.RepositoryManager;
import com.lagradost.cloudstream3.syncproviders.providers.Kitsu;
import com.lagradost.cloudstream3.ui.WebviewFragment;
import com.lagradost.cloudstream3.ui.player.SubtitleData;
import com.lagradost.cloudstream3.ui.result.ResultFragment;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.ui.settings.extensions.PluginsFragment;
import com.lagradost.cloudstream3.ui.settings.extensions.RepositoryData;
import com.lagradost.cloudstream3.utils.downloader.DownloadObjects;
import j$.net.URLDecoder;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import okhttp3.Cache;

/* JADX INFO: compiled from: AppContextUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u008e\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0004\u001a\u00020\u0005*\u00020\u0006J\n\u0010\u0007\u001a\u00020\u0005*\u00020\bJ\n\u0010\t\u001a\u00020\u0005*\u00020\bJ\f\u0010\n\u001a\u00020\u000b*\u0004\u0018\u00010\fJ\f\u0010\r\u001a\u00020\u000b*\u0004\u0018\u00010\fJ\f\u0010\u000e\u001a\u00020\u000f*\u0004\u0018\u00010\u0010J\u0010\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0010H\u0002J\"\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0003J\u0014\u0010\u001b\u001a\u00020\u000b*\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001eJ\u0014\u0010\u001f\u001a\u00020\u000b*\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\u001eJ\"\u0010\"\u001a\u00020\u000b*\u00020\u00162\u0006\u0010#\u001a\u00020\u00102\u0006\u0010$\u001a\u00020\u00102\u0006\u0010%\u001a\u00020\u0010J\u0016\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'2\u0006\u0010\u0015\u001a\u00020\u0016H\u0007J4\u0010)\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0014\u0012\u0006\u0012\u0004\u0018\u00010(0*2\u0006\u0010\u0015\u001a\u00020\u00162\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020\u00050,H\u0007J(\u0010.\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0014\u0012\u0006\u0012\u0004\u0018\u00010(0*2\u0006\u0010/\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0016H\u0003J \u00102\u001a\u00020\u000b*\u00020\u00162\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u001804H\u0087@¢\u0006\u0002\u00105J\u001a\u00106\u001a\b\u0012\u0004\u0012\u000207042\f\u00108\u001a\b\u0012\u0004\u0012\u0002070'J\u001a\u00109\u001a\u0012\u0012\u0004\u0012\u00020\u00100:j\b\u0012\u0004\u0012\u00020\u0010`;*\u00020\u0016J\u001a\u0010<\u001a\u0012\u0012\u0004\u0012\u00020=0:j\b\u0012\u0004\u0012\u00020=`;*\u00020\u0016J\u001a\u0010>\u001a\u0012\u0012\u0004\u0012\u00020\u00100:j\b\u0012\u0004\u0012\u00020\u0010`;*\u00020\u0016J\u001a\u0010?\u001a\u0012\u0012\u0004\u0012\u00020@0:j\b\u0012\u0004\u0012\u00020@`;*\u00020\u0016J\n\u0010A\u001a\u00020\u000b*\u00020\u0016J\f\u0010B\u001a\u00020\u0005*\u00020\u0016H\u0002J\n\u0010C\u001a\u00020\u0005*\u00020\u0016J\u001a\u0010D\u001a\b\u0012\u0004\u0012\u00020E04*\u00020\u00162\b\b\u0002\u0010F\u001a\u00020\u0005J\u001e\u0010G\u001a\b\u0012\u0004\u0012\u00020H04*\u00020\u00162\f\u00103\u001a\b\u0012\u0004\u0012\u00020H04J\u0012\u0010I\u001a\u00020J*\u00020\u00162\u0006\u00103\u001a\u00020JJ\u0012\u0010K\u001a\u00020\u000b*\u00020L2\u0006\u0010M\u001a\u00020\u0010J\u0012\u0010N\u001a\u00020\u000b*\u00020L2\u0006\u0010O\u001a\u00020PJ\f\u0010Q\u001a\u00020\u0005*\u00020\u0016H\u0002J\u0018\u0010R\u001a\u00020\u000b2\b\u0010S\u001a\u0004\u0018\u00010T2\u0006\u0010M\u001a\u00020\u0010J/\u0010U\u001a\u0004\u0018\u00010\u000b*\u00020\u00162\u0006\u0010M\u001a\u00020\u00102\b\b\u0002\u0010V\u001a\u00020\u00052\n\b\u0002\u0010S\u001a\u0004\u0018\u00010T¢\u0006\u0002\u0010WJ\n\u0010X\u001a\u00020\u0005*\u00020\u0016J\u001a\u0010Y\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100Z2\u0006\u0010M\u001a\u00020[J-\u0010\\\u001a\u00020\u0010*\u00020\u00162\b\u0010]\u001a\u0004\u0018\u00010\u00102\b\u0010^\u001a\u0004\u0018\u00010\u001e2\b\u0010_\u001a\u0004\u0018\u00010\u001e¢\u0006\u0002\u0010`J%\u0010a\u001a\u0004\u0018\u00010\u0010*\u00020\u00162\b\u0010^\u001a\u0004\u0018\u00010\u001e2\b\u0010_\u001a\u0004\u0018\u00010\u001e¢\u0006\u0002\u0010bJ\f\u0010c\u001a\u00020\u000b*\u0004\u0018\u00010LJ\b\u0010d\u001a\u00020\u001eH\u0002J2\u0010e\u001a\u00020\u000b2\u0006\u0010M\u001a\u00020\u00102\u0006\u0010f\u001a\u00020\u00102\u0006\u0010]\u001a\u00020\u00102\b\b\u0002\u0010g\u001a\u00020\u001e2\b\b\u0002\u0010h\u001a\u00020\u001eJ6\u0010e\u001a\u00020\u000b*\u00020i2\u0006\u0010M\u001a\u00020\u00102\u0006\u0010f\u001a\u00020\u00102\u0006\u0010]\u001a\u00020\u00102\b\b\u0002\u0010g\u001a\u00020\u001e2\b\b\u0002\u0010h\u001a\u00020\u001eJ)\u0010j\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020H2\b\b\u0002\u0010g\u001a\u00020\u001e2\n\b\u0002\u0010h\u001a\u0004\u0018\u00010\u001e¢\u0006\u0002\u0010kJ/\u0010j\u001a\u00020\u000b*\u0004\u0018\u00010L2\u0006\u0010\u0017\u001a\u00020H2\b\b\u0002\u0010g\u001a\u00020\u001e2\n\b\u0002\u0010h\u001a\u0004\u0018\u00010\u001e¢\u0006\u0002\u0010lJ\u0014\u0010m\u001a\u00020\u000b*\u00020L2\b\u0010n\u001a\u0004\u0018\u00010oJ\n\u0010y\u001a\u0004\u0018\u00010rH\u0002J\n\u0010z\u001a\u00020\u0005*\u00020\u0016J\n\u0010{\u001a\u00020\u0005*\u00020\u0016J\u0014\u0010|\u001a\u00020\u000b*\u00020}2\b\b\u0002\u0010~\u001a\u00020\u001eJ\n\u0010\u007f\u001a\u00020\u0005*\u00020\u0016J\u001a\u0010\u0080\u0001\u001a\u00020\u000b*\u0004\u0018\u00010L2\t\u0010\u0081\u0001\u001a\u0004\u0018\u00010\u0010H\u0002J\u0014\u0010\u0082\u0001\u001a\u00020\u0005*\u00020\u00162\u0007\u0010\u0083\u0001\u001a\u00020\u0010J\t\u0010\u0084\u0001\u001a\u0004\u0018\u00010oR\u000e\u00100\u001a\u000201X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010p\u001a\u0004\u0018\u00010oX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010q\u001a\u0004\u0018\u00010rX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010s\u001a\b\u0012\u0004\u0012\u00020\u00050tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bu\u0010v\"\u0004\bw\u0010x¨\u0006\u0085\u0001"}, d2 = {"Lcom/lagradost/cloudstream3/utils/AppContextUtils;", "", "<init>", "()V", "isRecyclerScrollable", "", "Landroidx/recyclerview/widget/RecyclerView;", "isLtr", "Landroid/view/View;", "isRtl", "ownHide", "", "Lcom/google/android/material/bottomsheet/BottomSheetDialog;", "ownShow", "html", "Landroid/text/Spanned;", "", "getHtmlText", "text", "buildWatchNextProgramUri", "Landroidx/tvprovider/media/tv/WatchNextProgram;", "context", "Landroid/content/Context;", "card", "Lcom/lagradost/cloudstream3/utils/DataStoreHelper$ResumeWatchingResult;", "resumeWatching", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$ResumeWatching;", "reduceDragSensitivity", "Landroidx/viewpager2/widget/ViewPager2;", "f", "", "animateProgressTo", "Landroidx/core/widget/ContentLoadingProgressBar;", TypedValues.TransitionType.S_TO, "createNotificationChannel", "channelId", "channelName", "description", "getAllWatchNextPrograms", "", "", "findFirstWatchNextProgram", "Lkotlin/Pair;", "predicate", "Lkotlin/Function1;", "Landroid/database/Cursor;", "getWatchNextProgramByVideoId", "id", "continueWatchingLock", "Lkotlinx/coroutines/sync/Mutex;", "addProgramsToContinueWatching", "data", "", "(Landroid/content/Context;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sortSubs", "Lcom/lagradost/cloudstream3/ui/player/SubtitleData;", CloudStreamPackage.SUBTITLE_EXTRA, "getApiSettings", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "getApiDubstatusSettings", "Lcom/lagradost/cloudstream3/DubStatus;", "getApiProviderLangSettings", "getApiTypeSettings", "Lcom/lagradost/cloudstream3/TvType;", "updateHasTrailers", "getHasTrailers", "shouldShowPlayerMetadata", "filterProviderByPreferredMedia", "Lcom/lagradost/cloudstream3/MainAPI;", "hasHomePageIsRequired", "filterSearchResultByFilmQuality", "Lcom/lagradost/cloudstream3/SearchResponse;", "filterHomePageListByFilmQuality", "Lcom/lagradost/cloudstream3/HomePageList;", "loadRepository", "Landroid/app/Activity;", ImagesContract.URL, "addRepositoryDialog", "repositoryData", "Lcom/lagradost/cloudstream3/ui/settings/extensions/RepositoryData;", "hasWebView", "openWebView", "fragment", "Landroidx/fragment/app/Fragment;", "openBrowser", "fallbackWebview", "(Landroid/content/Context;Ljava/lang/String;ZLandroidx/fragment/app/Fragment;)Lkotlin/Unit;", "isNetworkAvailable", "splitQuery", "", "Ljava/net/URL;", "getNameFull", "name", "episode", "season", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)Ljava/lang/String;", "getShortSeasonText", "(Landroid/content/Context;Ljava/lang/Integer;Ljava/lang/Integer;)Ljava/lang/String;", "loadCache", "getResultsId", "loadResult", "apiName", "startAction", "startValue", "Landroidx/fragment/app/FragmentActivity;", "loadSearchResult", "(Lcom/lagradost/cloudstream3/SearchResponse;ILjava/lang/Integer;)V", "(Landroid/app/Activity;Lcom/lagradost/cloudstream3/SearchResponse;ILjava/lang/Integer;)V", "requestLocalAudioFocus", "focusRequest", "Landroid/media/AudioFocusRequest;", "currentAudioFocusRequest", "currentAudioFocusChangeListener", "Landroid/media/AudioManager$OnAudioFocusChangeListener;", "onAudioFocusEvent", "Lcom/lagradost/cloudstream3/utils/Event;", "getOnAudioFocusEvent", "()Lcom/lagradost/cloudstream3/utils/Event;", "setOnAudioFocusEvent", "(Lcom/lagradost/cloudstream3/utils/Event;)V", "getAudioListener", "isCastApiAvailable", "isConnectedToChromecast", "setDefaultFocus", "Landroidx/appcompat/app/AlertDialog;", "buttonFocus", "isUsingMobileData", "cacheClass", "clazz", "isAppInstalled", "uri", "getFocusRequest", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AppContextUtils {
    private static AudioManager.OnAudioFocusChangeListener currentAudioFocusChangeListener;
    private static AudioFocusRequest currentAudioFocusRequest;
    public static final AppContextUtils INSTANCE = new AppContextUtils();
    private static final Mutex continueWatchingLock = MutexKt.Mutex$default(false, 1, null);
    private static Event<Boolean> onAudioFocusEvent = new Event<>();

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.AppContextUtils$addProgramsToContinueWatching$1, reason: invalid class name */
    /* JADX INFO: compiled from: AppContextUtils.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.AppContextUtils", f = "AppContextUtils.kt", i = {0, 0, 0, 0, 0}, l = {911}, m = "addProgramsToContinueWatching", n = {"$this$addProgramsToContinueWatching", "data", "context", "$this$withLock_u24default$iv", "$i$f$withLock"}, nl = {912}, s = {"L$0", "L$1", "L$2", "L$3", "I$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AppContextUtils.this.addProgramsToContinueWatching(null, null, this);
        }
    }

    private AppContextUtils() {
    }

    public final boolean isRecyclerScrollable(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "<this>");
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        LinearLayoutManager linearLayoutManager = layoutManager instanceof LinearLayoutManager ? (LinearLayoutManager) layoutManager : null;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        return (linearLayoutManager == null || adapter == null || linearLayoutManager.findLastCompletelyVisibleItemPosition() >= adapter.getItemCount() + (-7)) ? false : true;
    }

    public final boolean isLtr(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return view.getLayoutDirection() == 0;
    }

    public final boolean isRtl(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return view.getLayoutDirection() == 1;
    }

    public final void ownHide(BottomSheetDialog bottomSheetDialog) {
        if (bottomSheetDialog != null) {
            bottomSheetDialog.hide();
        }
    }

    public final void ownShow(final BottomSheetDialog bottomSheetDialog) {
        Window window;
        if (bottomSheetDialog != null && (window = bottomSheetDialog.getWindow()) != null) {
            window.setWindowAnimations(-1);
        }
        if (bottomSheetDialog != null) {
            bottomSheetDialog.show();
        }
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.lagradost.cloudstream3.utils.AppContextUtils$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                AppContextUtils.ownShow$lambda$0(bottomSheetDialog);
            }
        }, 200L);
    }

    static final void ownShow$lambda$0(BottomSheetDialog bottomSheetDialog) {
        Window window;
        if (bottomSheetDialog == null || (window = bottomSheetDialog.getWindow()) == null) {
            return;
        }
        window.setWindowAnimations(R.style.Animation_Design_BottomSheetDialog);
    }

    public final Spanned html(String str) {
        if (str != null) {
            return getHtmlText(str);
        }
        return SpannedString.valueOf("");
    }

    private final Spanned getHtmlText(String text) {
        try {
            Spanned spannedFromHtml = HtmlCompat.fromHtml(text, 0);
            Intrinsics.checkNotNull(spannedFromHtml);
            return spannedFromHtml;
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
            return SpannedString.valueOf(text);
        }
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0075  */
    /* JADX WARN: Multi-variable type inference failed */
    private final WatchNextProgram buildWatchNextProgramUri(Context context, DataStoreHelper.ResumeWatchingResult card, DownloadObjects.ResumeWatching resumeWatching) {
        String name;
        String url;
        Integer episode;
        TvType type = card.getType();
        boolean z = (type == null || MainAPIKt.isMovieType(type)) ? false : true;
        if (z) {
            name = getNameFull(context, card.getName(), card.getEpisode(), card.getSeason());
        } else {
            name = card.getName();
        }
        WatchNextProgram.Builder builder = (WatchNextProgram.Builder) ((WatchNextProgram.Builder) new WatchNextProgram.Builder().setEpisodeTitle(name)).setType(z ? 3 : 0).setWatchNextType(0).setTitle(name);
        String posterUrl = card.getPosterUrl();
        WatchNextProgram.Builder builder2 = (WatchNextProgram.Builder) builder.setPosterArtUri(posterUrl != null ? Uri.parse(posterUrl) : null);
        Integer id = card.getId();
        if (id != null) {
            url = "cloudstreamcontinuewatching://" + id.intValue();
            if (url == null) {
                url = card.getUrl();
            }
        } else {
            url = card.getUrl();
        }
        WatchNextProgram.Builder lastEngagementTimeUtcMillis = builder2.setIntentUri(Uri.parse(url)).setInternalProviderId(card.getUrl()).setLastEngagementTimeUtcMillis(resumeWatching != null ? resumeWatching.getUpdateTime() : System.currentTimeMillis());
        DataStoreHelper.PosDur watchPos = card.getWatchPos();
        if (watchPos != null) {
            lastEngagementTimeUtcMillis.setDurationMillis((int) watchPos.getDuration());
            lastEngagementTimeUtcMillis.setLastPlaybackPositionMillis((int) watchPos.getPosition());
        }
        if (z && (episode = card.getEpisode()) != null) {
        }
        WatchNextProgram watchNextProgramBuild = lastEngagementTimeUtcMillis.build();
        Intrinsics.checkNotNullExpressionValue(watchNextProgramBuild, "build(...)");
        return watchNextProgramBuild;
    }

    public static /* synthetic */ void reduceDragSensitivity$default(AppContextUtils appContextUtils, ViewPager2 viewPager2, int i, int i2, Object obj) throws IllegalAccessException, NoSuchFieldException {
        if ((i2 & 1) != 0) {
            i = 4;
        }
        appContextUtils.reduceDragSensitivity(viewPager2, i);
    }

    public final void reduceDragSensitivity(ViewPager2 viewPager2, int i) throws IllegalAccessException, NoSuchFieldException {
        Intrinsics.checkNotNullParameter(viewPager2, "<this>");
        Field declaredField = ViewPager2.class.getDeclaredField("mRecyclerView");
        declaredField.setAccessible(true);
        Object obj = declaredField.get(viewPager2);
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView");
        RecyclerView recyclerView = (RecyclerView) obj;
        Field declaredField2 = RecyclerView.class.getDeclaredField("mTouchSlop");
        declaredField2.setAccessible(true);
        Object obj2 = declaredField2.get(recyclerView);
        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Int");
        declaredField2.set(recyclerView, Integer.valueOf(((Integer) obj2).intValue() * i));
    }

    public final void animateProgressTo(ContentLoadingProgressBar contentLoadingProgressBar, int i) {
        if (contentLoadingProgressBar == null) {
            return;
        }
        ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(contentLoadingProgressBar, "progress", contentLoadingProgressBar.getProgress(), i);
        Intrinsics.checkNotNullExpressionValue(objectAnimatorOfInt, "ofInt(...)");
        objectAnimatorOfInt.setDuration(500L);
        objectAnimatorOfInt.setAutoCancel(true);
        objectAnimatorOfInt.setInterpolator(new DecelerateInterpolator());
        objectAnimatorOfInt.start();
    }

    public final void createNotificationChannel(Context context, String channelId, String channelName, String description) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(channelId, "channelId");
        Intrinsics.checkNotNullParameter(channelName, "channelName");
        Intrinsics.checkNotNullParameter(description, "description");
        if (Build.VERSION.SDK_INT >= 26) {
            CommonActivity$$ExternalSyntheticApiModelOutline0.m$1();
            NotificationChannel notificationChannelM = CommonActivity$$ExternalSyntheticApiModelOutline0.m(channelId, channelName, 3);
            notificationChannelM.setDescription(description);
            Object systemService = context.getSystemService("notification");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
            ((NotificationManager) systemService).createNotificationChannel(notificationChannelM);
        }
    }

    public final Set<Long> getAllWatchNextPrograms(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Cursor cursorQuery = context.getContentResolver().query(TvContractCompat.WatchNextPrograms.CONTENT_URI, WatchNextProgram.PROJECTION, null, null, null);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if (cursorQuery == null) {
            return linkedHashSet;
        }
        Cursor cursor = cursorQuery;
        try {
            Cursor cursor2 = cursor;
            if (cursor2.moveToFirst()) {
                do {
                    linkedHashSet.add(Long.valueOf(cursorQuery.getLong(0)));
                } while (cursor2.moveToNext());
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursor, null);
            return linkedHashSet;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursor, th);
                throw th2;
            }
        }
    }

    public final Pair<WatchNextProgram, Long> findFirstWatchNextProgram(Context context, Function1<? super Cursor, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Cursor cursorQuery = context.getContentResolver().query(TvContractCompat.WatchNextPrograms.CONTENT_URI, WatchNextProgram.PROJECTION, null, null, null);
        if (cursorQuery != null) {
            Cursor cursor = cursorQuery;
            try {
                Cursor cursor2 = cursor;
                if (cursor2.moveToFirst()) {
                    do {
                        if (predicate.invoke(cursorQuery).booleanValue()) {
                            Pair<WatchNextProgram, Long> pair = TuplesKt.to(WatchNextProgram.fromCursor(cursorQuery), Long.valueOf(cursorQuery.getLong(0)));
                            CloseableKt.closeFinally(cursor, null);
                            return pair;
                        }
                    } while (cursor2.moveToNext());
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(cursor, null);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(cursor, th);
                    throw th2;
                }
            }
        }
        return TuplesKt.to(null, null);
    }

    private final synchronized Pair<WatchNextProgram, Long> getWatchNextProgramByVideoId(final String id, Context context) {
        return findFirstWatchNextProgram(context, new Function1() { // from class: com.lagradost.cloudstream3.utils.AppContextUtils$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(AppContextUtils.getWatchNextProgramByVideoId$lambda$0(id, (Cursor) obj));
            }
        });
    }

    static final boolean getWatchNextProgramByVideoId$lambda$0(String str, Cursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        return Intrinsics.areEqual(cursor.getString(cursor.getColumnIndex("internal_provider_id")), str);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object addProgramsToContinueWatching(Context context, List<DataStoreHelper.ResumeWatchingResult> list, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        Mutex mutex;
        Long lBoxLong;
        long jPublishWatchNextProgram;
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
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (Build.VERSION.SDK_INT < 26) {
                return Unit.INSTANCE;
            }
            mutex = continueWatchingLock;
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(context);
            anonymousClass1.L$1 = list;
            anonymousClass1.L$2 = context;
            anonymousClass1.L$3 = mutex;
            anonymousClass1.I$0 = 0;
            anonymousClass1.label = 1;
            if (mutex.lock(null, anonymousClass1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = anonymousClass1.I$0;
            Mutex mutex2 = (Mutex) anonymousClass1.L$3;
            Context context2 = (Context) anonymousClass1.L$2;
            List<DataStoreHelper.ResumeWatchingResult> list2 = (List) anonymousClass1.L$1;
            ResultKt.throwOnFailure(obj);
            mutex = mutex2;
            context = context2;
            list = list2;
        }
        try {
            HashMap map = new HashMap();
            List<Integer> allResumeStateIds = DataStoreHelper.INSTANCE.getAllResumeStateIds();
            if (allResumeStateIds != null) {
                Iterator<T> it = allResumeStateIds.iterator();
                while (it.hasNext()) {
                    DownloadObjects.ResumeWatching lastWatched = DataStoreHelper.INSTANCE.getLastWatched(Boxing.boxInt(((Number) it.next()).intValue()));
                    if (lastWatched != null) {
                        map.put(Boxing.boxInt(lastWatched.getParentId()), lastWatched);
                    }
                }
            }
            ArrayList arrayList = new ArrayList();
            for (DataStoreHelper.ResumeWatchingResult resumeWatchingResult : list) {
                try {
                    String str = resumeWatchingResult.getId() + '|' + resumeWatchingResult.getApiName() + '|' + resumeWatchingResult.getUrl();
                    AppContextUtils appContextUtils = INSTANCE;
                    Pair<WatchNextProgram, Long> watchNextProgramByVideoId = appContextUtils.getWatchNextProgramByVideoId(str, context);
                    WatchNextProgram watchNextProgramComponent1 = watchNextProgramByVideoId.component1();
                    Long lComponent2 = watchNextProgramByVideoId.component2();
                    WatchNextProgram watchNextProgramBuildWatchNextProgramUri = appContextUtils.buildWatchNextProgramUri(context, resumeWatchingResult, (DownloadObjects.ResumeWatching) map.get(resumeWatchingResult.getId()));
                    if (watchNextProgramComponent1 != null && lComponent2 != null) {
                        new PreviewChannelHelper(context).updateWatchNextProgram(watchNextProgramBuildWatchNextProgramUri, lComponent2.longValue());
                        jPublishWatchNextProgram = lComponent2.longValue();
                    } else {
                        jPublishWatchNextProgram = new PreviewChannelHelper(context).publishWatchNextProgram(watchNextProgramBuildWatchNextProgramUri);
                    }
                    lBoxLong = Boxing.boxLong(jPublishWatchNextProgram);
                } catch (Exception e) {
                    ArchComponentExtKt.logError(e);
                    lBoxLong = null;
                }
                if (lBoxLong != null) {
                    arrayList.add(lBoxLong);
                }
            }
            Iterator it2 = SetsKt.minus((Set) INSTANCE.getAllWatchNextPrograms(context), (Iterable) CollectionsKt.toSet(arrayList)).iterator();
            while (it2.hasNext()) {
                context.getContentResolver().delete(TvContractCompat.buildWatchNextProgramUri(((Number) it2.next()).longValue()), null, null);
            }
            Unit unit = Unit.INSTANCE;
            mutex.unlock(null);
            return Unit.INSTANCE;
        } catch (Throwable th) {
            mutex.unlock(null);
            throw th;
        }
    }

    public final List<SubtitleData> sortSubs(Set<SubtitleData> subs) {
        Intrinsics.checkNotNullParameter(subs, "subs");
        final Comparator comparator = new Comparator() { // from class: com.lagradost.cloudstream3.utils.AppContextUtils$sortSubs$$inlined$compareBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(((SubtitleData) t).getOriginalName(), ((SubtitleData) t2).getOriginalName());
            }
        };
        return CollectionsKt.sortedWith(subs, new Comparator() { // from class: com.lagradost.cloudstream3.utils.AppContextUtils$sortSubs$$inlined$thenBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int iCompare = comparator.compare(t, t2);
                return iCompare != 0 ? iCompare : ComparisonsKt.compareValues(((SubtitleData) t).getNameSuffix(), ((SubtitleData) t2).getNameSuffix());
            }
        });
    }

    public final HashSet<String> getApiSettings(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        HashSet<String> hashSet = new HashSet<>();
        final HashSet<String> apiProviderLangSettings = getApiProviderLangSettings(context);
        final boolean zContains = apiProviderLangSettings.contains(MainAPIKt.AllLanguagesName);
        AtomicList<MainAPI> atomicListFilter = APIHolder.INSTANCE.getApis().filter(new Function1() { // from class: com.lagradost.cloudstream3.utils.AppContextUtils$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(AppContextUtils.getApiSettings$lambda$0(zContains, apiProviderLangSettings, (MainAPI) obj));
            }
        });
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(atomicListFilter, 10));
        Iterator<MainAPI> it = atomicListFilter.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getName());
        }
        hashSet.addAll(arrayList);
        return hashSet;
    }

    static final boolean getApiSettings$lambda$0(boolean z, HashSet hashSet, MainAPI it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return z || hashSet.contains(it.getLang());
    }

    public final HashSet<DubStatus> getApiDubstatusSettings(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        HashSet<DubStatus> hashSet = new HashSet<>();
        CollectionsKt.addAll(hashSet, DubStatus.values());
        String string = context.getString(com.lagradost.cloudstream3.R.string.display_sub_key);
        HashSet<DubStatus> hashSet2 = hashSet;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(hashSet2, 10));
        Iterator<T> it = hashSet2.iterator();
        while (it.hasNext()) {
            arrayList.add(((DubStatus) it.next()).name());
        }
        Set<String> stringSet = defaultSharedPreferences.getStringSet(string, CollectionsKt.toMutableSet(arrayList));
        if (stringSet == null) {
            return hashSet;
        }
        DubStatus[] dubStatusArrValues = DubStatus.values();
        ArrayList arrayList2 = new ArrayList(dubStatusArrValues.length);
        for (DubStatus dubStatus : dubStatusArrValues) {
            arrayList2.add(dubStatus.name());
        }
        HashSet hashSet3 = CollectionsKt.toHashSet(arrayList2);
        ArrayList arrayList3 = new ArrayList();
        for (Object obj : stringSet) {
            if (hashSet3.contains((String) obj)) {
                arrayList3.add(obj);
            }
        }
        ArrayList<String> arrayList4 = arrayList3;
        ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList4, 10));
        for (String str : arrayList4) {
            Intrinsics.checkNotNull(str);
            arrayList5.add(DubStatus.valueOf(str));
        }
        return CollectionsKt.toHashSet(arrayList5);
    }

    public final HashSet<String> getApiProviderLangSettings(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        HashSet<String> hashSetHashSetOf = SetsKt.hashSetOf(MainAPIKt.AllLanguagesName);
        Set<String> stringSet = defaultSharedPreferences.getStringSet(context.getString(com.lagradost.cloudstream3.R.string.provider_lang_key), hashSetHashSetOf);
        Set<String> set = stringSet;
        return (set == null || set.isEmpty()) ? hashSetHashSetOf : CollectionsKt.toHashSet(stringSet);
    }

    public final HashSet<TvType> getApiTypeSettings(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        HashSet<TvType> hashSet = new HashSet<>();
        CollectionsKt.addAll(hashSet, TvType.values());
        String string = context.getString(com.lagradost.cloudstream3.R.string.search_types_list_key);
        HashSet<TvType> hashSet2 = hashSet;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(hashSet2, 10));
        Iterator<T> it = hashSet2.iterator();
        while (it.hasNext()) {
            arrayList.add(((TvType) it.next()).name());
        }
        Set<String> stringSet = defaultSharedPreferences.getStringSet(string, CollectionsKt.toMutableSet(arrayList));
        Set<String> set = stringSet;
        if (set == null || set.isEmpty()) {
            return hashSet;
        }
        TvType[] tvTypeArrValues = TvType.values();
        ArrayList arrayList2 = new ArrayList(tvTypeArrValues.length);
        for (TvType tvType : tvTypeArrValues) {
            arrayList2.add(tvType.name());
        }
        HashSet hashSet3 = CollectionsKt.toHashSet(arrayList2);
        ArrayList arrayList3 = new ArrayList();
        for (Object obj : stringSet) {
            if (hashSet3.contains((String) obj)) {
                arrayList3.add(obj);
            }
        }
        ArrayList<String> arrayList4 = arrayList3;
        ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList4, 10));
        for (String str : arrayList4) {
            Intrinsics.checkNotNull(str);
            arrayList5.add(TvType.valueOf(str));
        }
        HashSet<TvType> hashSet4 = CollectionsKt.toHashSet(arrayList5);
        return hashSet4.isEmpty() ? hashSet : hashSet4;
    }

    public final void updateHasTrailers(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        LoadResponse.INSTANCE.setTrailersEnabled(getHasTrailers(context));
    }

    private final boolean getHasTrailers(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(com.lagradost.cloudstream3.R.string.show_trailers_key), true);
    }

    public final boolean shouldShowPlayerMetadata(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(com.lagradost.cloudstream3.R.string.show_player_metadata_key), true);
    }

    public static /* synthetic */ List filterProviderByPreferredMedia$default(AppContextUtils appContextUtils, Context context, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return appContextUtils.filterProviderByPreferredMedia(context, z);
    }

    public final List<MainAPI> filterProviderByPreferredMedia(Context context, final boolean z) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(TvType.class.getClassLoader());
        List listSorted = ArraysKt.sorted(TvType.values());
        ArrayList arrayList = new ArrayList();
        for (Object obj : listSorted) {
            if (((TvType) obj) != TvType.NSFW) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList3.add(Integer.valueOf(((TvType) it.next()).ordinal()));
        }
        final ArrayList arrayList4 = arrayList3;
        Thread.currentThread().setContextClassLoader(contextClassLoader);
        ArrayList arrayList5 = arrayList4;
        ArrayList arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList5, 10));
        Iterator it2 = arrayList5.iterator();
        while (it2.hasNext()) {
            arrayList6.add(String.valueOf(((Number) it2.next()).intValue()));
        }
        Set<String> set = CollectionsKt.toSet(arrayList6);
        ArrayList arrayList7 = null;
        try {
            Set<String> stringSet = PreferenceManager.getDefaultSharedPreferences(context).getStringSet(context.getString(com.lagradost.cloudstream3.R.string.prefer_media_type_key), set);
            if (stringSet != null) {
                ArrayList arrayList8 = new ArrayList();
                for (String str : stringSet) {
                    Intrinsics.checkNotNull(str);
                    Integer intOrNull = StringsKt.toIntOrNull(str);
                    if (intOrNull != null) {
                        intOrNull.intValue();
                    } else {
                        intOrNull = null;
                    }
                    if (intOrNull != null) {
                        arrayList8.add(intOrNull);
                    }
                }
                arrayList7 = arrayList8;
            }
        } catch (Throwable unused) {
        }
        if (arrayList7 != null) {
            arrayList4 = arrayList7;
        }
        final HashSet<String> apiProviderLangSettings = getApiProviderLangSettings(context);
        final boolean zContains = apiProviderLangSettings.contains(MainAPIKt.AllLanguagesName);
        AtomicList<MainAPI> atomicListFilter = APIHolder.INSTANCE.getApis().filter(new Function1() { // from class: com.lagradost.cloudstream3.utils.AppContextUtils$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return Boolean.valueOf(AppContextUtils.filterProviderByPreferredMedia$lambda$4(zContains, apiProviderLangSettings, z, (MainAPI) obj2));
            }
        });
        if (arrayList4.isEmpty()) {
            return atomicListFilter;
        }
        return atomicListFilter.filter(new Function1() { // from class: com.lagradost.cloudstream3.utils.AppContextUtils$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return Boolean.valueOf(AppContextUtils.filterProviderByPreferredMedia$lambda$5(arrayList4, (MainAPI) obj2));
            }
        });
    }

    static final boolean filterProviderByPreferredMedia$lambda$4(boolean z, HashSet hashSet, boolean z2, MainAPI api) {
        Intrinsics.checkNotNullParameter(api, "api");
        if (z || hashSet.contains(api.getLang())) {
            return api.getHasMainPage() || !z2;
        }
        return false;
    }

    static final boolean filterProviderByPreferredMedia$lambda$5(List list, MainAPI api) {
        Intrinsics.checkNotNullParameter(api, "api");
        Set<TvType> supportedTypes = api.getSupportedTypes();
        if ((supportedTypes instanceof Collection) && supportedTypes.isEmpty()) {
            return false;
        }
        Iterator<T> it = supportedTypes.iterator();
        while (it.hasNext()) {
            if (list.contains(Integer.valueOf(((TvType) it.next()).ordinal()))) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final List<SearchResponse> filterSearchResultByFilmQuality(Context context, List<? extends SearchResponse> data) {
        ArrayList arrayListEmptyList;
        Set<String> stringSet;
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(data, "data");
        if (!data.isEmpty()) {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            if (defaultSharedPreferences == null || (stringSet = defaultSharedPreferences.getStringSet(context.getString(com.lagradost.cloudstream3.R.string.pref_filter_search_quality_key), SetsKt.emptySet())) == null) {
                arrayListEmptyList = CollectionsKt.emptyList();
            } else {
                ArrayList arrayList = new ArrayList();
                for (String str : stringSet) {
                    Intrinsics.checkNotNull(str);
                    Integer intOrNull = StringsKt.toIntOrNull(str);
                    if (intOrNull != null) {
                        intOrNull.intValue();
                    } else {
                        intOrNull = null;
                    }
                    if (intOrNull != null) {
                        arrayList.add(intOrNull);
                    }
                }
                arrayListEmptyList = arrayList;
            }
            if (!arrayListEmptyList.isEmpty()) {
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : data) {
                    SearchQuality quality = ((SearchResponse) obj).getQuality();
                    if (!arrayListEmptyList.contains(Integer.valueOf(quality != null ? quality.ordinal() : -1))) {
                        arrayList2.add(obj);
                    }
                }
                return arrayList2;
            }
        }
        return data;
    }

    public final HomePageList filterHomePageListByFilmQuality(Context context, HomePageList data) {
        ArrayList arrayListEmptyList;
        Set<String> stringSet;
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(data, "data");
        if (!data.getList().isEmpty()) {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            if (defaultSharedPreferences == null || (stringSet = defaultSharedPreferences.getStringSet(context.getString(com.lagradost.cloudstream3.R.string.pref_filter_search_quality_key), SetsKt.emptySet())) == null) {
                arrayListEmptyList = CollectionsKt.emptyList();
            } else {
                ArrayList arrayList = new ArrayList();
                for (String str : stringSet) {
                    Intrinsics.checkNotNull(str);
                    Integer intOrNull = StringsKt.toIntOrNull(str);
                    if (intOrNull != null) {
                        intOrNull.intValue();
                    } else {
                        intOrNull = null;
                    }
                    if (intOrNull != null) {
                        arrayList.add(intOrNull);
                    }
                }
                arrayListEmptyList = arrayList;
            }
            if (!arrayListEmptyList.isEmpty()) {
                String name = data.getName();
                boolean zIsHorizontalImages = data.isHorizontalImages();
                List<SearchResponse> list = data.getList();
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : list) {
                    SearchQuality quality = ((SearchResponse) obj).getQuality();
                    if (!arrayListEmptyList.contains(Integer.valueOf(quality != null ? quality.ordinal() : -1))) {
                        arrayList2.add(obj);
                    }
                }
                return new HomePageList(name, arrayList2, zIsHorizontalImages);
            }
        }
        return data;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.AppContextUtils$loadRepository$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AppContextUtils.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Landroid/app/Activity;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.AppContextUtils$loadRepository$1", f = "AppContextUtils.kt", i = {0, 1, 1, 1}, l = {528, 534}, m = "invokeSuspend", n = {"$this$ioSafe", "$this$ioSafe", "repo", "data"}, nl = {529, 535}, s = {"L$0", "L$0", "L$1", "L$2"}, v = 2)
    static final class C09191 extends SuspendLambda implements Function3<CoroutineScope, Activity, Continuation<? super Unit>, Object> {
        final /* synthetic */ Activity $this_loadRepository;
        final /* synthetic */ String $url;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C09191(String str, Activity activity, Continuation<? super C09191> continuation) {
            super(3, continuation);
            this.$url = str;
            this.$this_loadRepository = activity;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, Activity activity, Continuation<? super Unit> continuation) {
            C09191 c09191 = new C09191(this.$url, this.$this_loadRepository, continuation);
            c09191.L$0 = coroutineScope;
            return c09191.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Repository repository;
            RepositoryData repositoryData;
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.L$0 = coroutineScope;
                this.label = 1;
                obj = RepositoryManager.INSTANCE.parseRepository(this.$url, this);
                if (obj != coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                repositoryData = (RepositoryData) this.L$2;
                repository = (Repository) this.L$1;
                ResultKt.throwOnFailure(obj);
            }
            Coroutines.INSTANCE.main(coroutineScope, new C01791(this.$this_loadRepository, repository, null));
            MainActivity.INSTANCE.getAfterRepositoryLoadedEvent().invoke(Boxing.boxBoolean(true));
            AppContextUtils.INSTANCE.addRepositoryDialog(this.$this_loadRepository, repositoryData);
            return Unit.INSTANCE;
            repository = (Repository) obj;
            if (repository == null) {
                return Unit.INSTANCE;
            }
            String iconUrl = repository.getIconUrl();
            if (iconUrl == null) {
                iconUrl = "";
            }
            RepositoryData repositoryData2 = new RepositoryData(iconUrl, repository.getName(), this.$url);
            this.L$0 = coroutineScope;
            this.L$1 = repository;
            this.L$2 = repositoryData2;
            this.label = 2;
            if (RepositoryManager.INSTANCE.addRepository(repositoryData2, this) != coroutine_suspended) {
                repositoryData = repositoryData2;
                Coroutines.INSTANCE.main(coroutineScope, new C01791(this.$this_loadRepository, repository, null));
                MainActivity.INSTANCE.getAfterRepositoryLoadedEvent().invoke(Boxing.boxBoolean(true));
                AppContextUtils.INSTANCE.addRepositoryDialog(this.$this_loadRepository, repositoryData);
                return Unit.INSTANCE;
            }
            return coroutine_suspended;
        }

        /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.AppContextUtils$loadRepository$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: AppContextUtils.kt */
        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "com.lagradost.cloudstream3.utils.AppContextUtils$loadRepository$1$1", f = "AppContextUtils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
        static final class C01791 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Repository $repo;
            final /* synthetic */ Activity $this_loadRepository;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01791(Activity activity, Repository repository, Continuation<? super C01791> continuation) {
                super(2, continuation);
                this.$this_loadRepository = activity;
                this.$repo = repository;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01791(this.$this_loadRepository, this.$repo, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01791) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CommonActivity.INSTANCE.showToast(this.$this_loadRepository.getString(com.lagradost.cloudstream3.R.string.player_loaded_subtitles, new Object[]{this.$repo.getName()}), Boxing.boxInt(1));
                return Unit.INSTANCE;
            }
        }
    }

    public final void loadRepository(Activity activity, String url) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        Intrinsics.checkNotNullParameter(url, "url");
        Coroutines.INSTANCE.ioSafe(activity, new C09191(url, activity, null));
    }

    public final void addRepositoryDialog(final Activity activity, final RepositoryData repositoryData) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        Intrinsics.checkNotNullParameter(repositoryData, "repositoryData");
        final RepositoryData[] repositories = RepositoryManager.INSTANCE.getRepositories();
        activity.runOnUiThread(new Runnable() { // from class: com.lagradost.cloudstream3.utils.AppContextUtils$$ExternalSyntheticLambda18
            @Override // java.lang.Runnable
            public final void run() {
                AppContextUtils.addRepositoryDialog$lambda$0(activity, repositoryData, repositories);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addRepositoryDialog$openAddedRepo(RepositoryData[] repositoryDataArr, Activity activity, RepositoryData repositoryData) {
        if (repositoryDataArr.length == 0) {
            return;
        }
        UIHelper.navigate$default(UIHelper.INSTANCE, activity, com.lagradost.cloudstream3.R.id.global_to_navigation_settings_plugins, PluginsFragment.INSTANCE.newInstance(repositoryData), null, 4, null);
    }

    static final void addRepositoryDialog$lambda$0(final Activity activity, final RepositoryData repositoryData, final RepositoryData[] repositoryDataArr) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(repositoryData.getName());
        builder.setMessage(com.lagradost.cloudstream3.R.string.download_all_plugins_from_repo);
        builder.setPositiveButton(com.lagradost.cloudstream3.R.string.open_downloaded_repo, new DialogInterface.OnClickListener() { // from class: com.lagradost.cloudstream3.utils.AppContextUtils$$ExternalSyntheticLambda7
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AppContextUtils.addRepositoryDialog$openAddedRepo(repositoryDataArr, activity, repositoryData);
            }
        });
        builder.setNegativeButton(com.lagradost.cloudstream3.R.string.dismiss, (DialogInterface.OnClickListener) null);
        AppContextUtils appContextUtils = INSTANCE;
        AlertDialog alertDialogShow = builder.show();
        Intrinsics.checkNotNullExpressionValue(alertDialogShow, "show(...)");
        setDefaultFocus$default(appContextUtils, alertDialogShow, 0, 1, null);
    }

    private final boolean hasWebView(Context context) {
        return context.getPackageManager().hasSystemFeature("android.software.webview");
    }

    public final void openWebView(final Fragment fragment, final String url) {
        Context context;
        Intrinsics.checkNotNullParameter(url, "url");
        if (fragment == null || (context = fragment.getContext()) == null || !hasWebView(context)) {
            return;
        }
        ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.utils.AppContextUtils$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AppContextUtils.openWebView$lambda$0(fragment, url);
            }
        });
    }

    static final Unit openWebView$lambda$0(Fragment fragment, String str) {
        FragmentKt.findNavController(fragment).navigate(com.lagradost.cloudstream3.R.id.navigation_webview, WebviewFragment.INSTANCE.newInstance(str));
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Unit openBrowser$default(AppContextUtils appContextUtils, Context context, String str, boolean z, Fragment fragment, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            fragment = null;
        }
        return appContextUtils.openBrowser(context, str, z, fragment);
    }

    public final Unit openBrowser(final Context context, final String url, final boolean z, final Fragment fragment) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(url, "url");
        Activity activity = CloudStreamApp.INSTANCE.getActivity(context);
        if (activity == null) {
            activity = CommonActivity.INSTANCE.getActivity();
        }
        if (activity == null) {
            return null;
        }
        activity.runOnUiThread(new Runnable() { // from class: com.lagradost.cloudstream3.utils.AppContextUtils$$ExternalSyntheticLambda17
            @Override // java.lang.Runnable
            public final void run() {
                AppContextUtils.openBrowser$lambda$0(url, fragment, context, z);
            }
        });
        return Unit.INSTANCE;
    }

    static final void openBrowser$lambda$0(final String str, final Fragment fragment, Context context, final boolean z) {
        FragmentActivity activity;
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            intent.addFlags(268468224);
            ActivityResultRegistry activityResultRegistry = (fragment == null || (activity = fragment.getActivity()) == null) ? null : activity.getActivityResultRegistry();
            if (activityResultRegistry != null) {
                activityResultRegistry.register(str, new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.lagradost.cloudstream3.utils.AppContextUtils$$ExternalSyntheticLambda6
                    @Override // androidx.activity.result.ActivityResultCallback
                    public final void onActivityResult(Object obj) {
                        AppContextUtils.openBrowser$lambda$0$0(z, fragment, str, (ActivityResult) obj);
                    }
                }).launch(intent);
            } else {
                context.startActivity(intent);
            }
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
            if (z) {
                INSTANCE.openWebView(fragment, str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void openBrowser$lambda$0$0(boolean z, Fragment fragment, String str, ActivityResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        if (result.getResultCode() == 0 && z) {
            INSTANCE.openWebView(fragment, str);
        }
    }

    public final boolean isNetworkAvailable(Context context) {
        NetworkCapabilities networkCapabilities;
        Intrinsics.checkNotNullParameter(context, "<this>");
        Object systemService = context.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
        Network activeNetwork = connectivityManager.getActiveNetwork();
        if (activeNetwork == null || (networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)) == null) {
            return false;
        }
        return networkCapabilities.hasCapability(12);
    }

    public final Map<String, String> splitQuery(URL url) throws UnsupportedEncodingException {
        Intrinsics.checkNotNullParameter(url, "url");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        String query = url.getQuery();
        Intrinsics.checkNotNullExpressionValue(query, "getQuery(...)");
        for (String str : (String[]) StringsKt.split$default((CharSequence) query, new String[]{"&"}, false, 0, 6, (Object) null).toArray(new String[0])) {
            int iIndexOf$default = StringsKt.indexOf$default((CharSequence) str, "=", 0, false, 6, (Object) null);
            String strSubstring = str.substring(0, iIndexOf$default);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            String strDecode = URLDecoder.decode(strSubstring, "UTF-8");
            String strSubstring2 = str.substring(iIndexOf$default + 1);
            Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
            linkedHashMap.put(strDecode, URLDecoder.decode(strSubstring2, "UTF-8"));
        }
        return linkedHashMap;
    }

    public final String getNameFull(Context context, String str, Integer num, Integer num2) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        if (num != null && num.intValue() == 0) {
            num = null;
        }
        if (num2 != null && num2.intValue() == 0) {
            num2 = null;
        }
        String string = context.getString(com.lagradost.cloudstream3.R.string.season);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String string2 = context.getString(com.lagradost.cloudstream3.R.string.episode);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        String string3 = context.getString(com.lagradost.cloudstream3.R.string.season_short);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        String string4 = context.getString(com.lagradost.cloudstream3.R.string.episode_short);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
        if (str == null) {
            if (num == null || num2 == null) {
                if (num2 == null) {
                    return string2 + ' ' + num;
                }
                return "";
            }
            return string + ' ' + num2.intValue() + " - " + string2 + ' ' + num.intValue();
        }
        if (num == null || num2 == null) {
            if (num == null) {
                return str;
            }
            return string2 + ' ' + num.intValue() + ". " + str;
        }
        return string3 + num2.intValue() + AbstractJsonLexerKt.COLON + string4 + num.intValue() + ' ' + str;
    }

    public final String getShortSeasonText(Context context, Integer num, Integer num2) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        if (num != null && num.intValue() == 0) {
            num = null;
        }
        if (num2 != null && num2.intValue() == 0) {
            num2 = null;
        }
        String string = context.getString(com.lagradost.cloudstream3.R.string.season_short);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String string2 = context.getString(com.lagradost.cloudstream3.R.string.episode_short);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        if (num == null || num2 == null) {
            if (num == null) {
                return null;
            }
            return string2 + num.intValue();
        }
        return string + num2.intValue() + AbstractJsonLexerKt.COLON + string2 + num.intValue();
    }

    public final void loadCache(Activity activity) {
        try {
            cacheClass(activity, JsUnpacker.INSTANCE.load("android.net.NetworkCapabilities"));
        } catch (Exception unused) {
        }
    }

    private final int getResultsId() {
        if (Globals.INSTANCE.isLayout(6)) {
            return com.lagradost.cloudstream3.R.id.global_to_navigation_results_tv;
        }
        return com.lagradost.cloudstream3.R.id.global_to_navigation_results_phone;
    }

    public static /* synthetic */ void loadResult$default(AppContextUtils appContextUtils, String str, String str2, String str3, int i, int i2, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            i = 0;
        }
        if ((i3 & 16) != 0) {
            i2 = 0;
        }
        appContextUtils.loadResult(str, str2, str3, i, i2);
    }

    public final void loadResult(String url, String apiName, String name, int startAction, int startValue) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        Intrinsics.checkNotNullParameter(name, "name");
        FragmentActivity fragmentActivity = (FragmentActivity) CommonActivity.INSTANCE.getActivity();
        if (fragmentActivity != null) {
            loadResult(fragmentActivity, url, apiName, name, startAction, startValue);
        }
    }

    public static /* synthetic */ void loadResult$default(AppContextUtils appContextUtils, FragmentActivity fragmentActivity, String str, String str2, String str3, int i, int i2, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            i = 0;
        }
        if ((i3 & 16) != 0) {
            i2 = 0;
        }
        appContextUtils.loadResult(fragmentActivity, str, str2, str3, i, i2);
    }

    public final void loadResult(final FragmentActivity fragmentActivity, final String url, final String apiName, final String name, final int i, final int i2) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "<this>");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        Intrinsics.checkNotNullParameter(name, "name");
        try {
            Kitsu.INSTANCE.setEnabled(PreferenceManager.getDefaultSharedPreferences(fragmentActivity).getBoolean(fragmentActivity.getString(com.lagradost.cloudstream3.R.string.show_kitsu_posters_key), true));
        } catch (Throwable th) {
            ArchComponentExtKt.logError(th);
        }
        fragmentActivity.runOnUiThread(new Runnable() { // from class: com.lagradost.cloudstream3.utils.AppContextUtils$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                UIHelper.navigate$default(UIHelper.INSTANCE, fragmentActivity, AppContextUtils.INSTANCE.getResultsId(), ResultFragment.INSTANCE.newInstance(url, apiName, name, i, i2), null, 4, null);
            }
        });
    }

    public static /* synthetic */ void loadSearchResult$default(AppContextUtils appContextUtils, SearchResponse searchResponse, int i, Integer num, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            num = null;
        }
        appContextUtils.loadSearchResult(searchResponse, i, num);
    }

    public final void loadSearchResult(SearchResponse card, int startAction, Integer startValue) {
        Intrinsics.checkNotNullParameter(card, "card");
        Activity activity = CommonActivity.INSTANCE.getActivity();
        if (activity != null) {
            loadSearchResult(activity, card, startAction, startValue);
        }
    }

    public static /* synthetic */ void loadSearchResult$default(AppContextUtils appContextUtils, Activity activity, SearchResponse searchResponse, int i, Integer num, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            num = null;
        }
        appContextUtils.loadSearchResult(activity, searchResponse, i, num);
    }

    public final void loadSearchResult(final Activity activity, final SearchResponse card, final int i, final Integer num) {
        Intrinsics.checkNotNullParameter(card, "card");
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.lagradost.cloudstream3.utils.AppContextUtils$$ExternalSyntheticLambda12
                @Override // java.lang.Runnable
                public final void run() {
                    UIHelper.navigate$default(UIHelper.INSTANCE, activity, AppContextUtils.INSTANCE.getResultsId(), ResultFragment.INSTANCE.newInstance(card, i, num), null, 4, null);
                }
            });
        }
    }

    public final void requestLocalAudioFocus(Activity activity, AudioFocusRequest audioFocusRequest) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        if (Build.VERSION.SDK_INT < 26) {
            Object systemService = activity.getSystemService("audio");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.media.AudioManager");
            ((AudioManager) systemService).requestAudioFocus(null, 3, 3);
        } else {
            if (audioFocusRequest == null) {
                Log.e("TAG", "focusRequest was null");
                return;
            }
            Object systemService2 = activity.getSystemService("audio");
            Intrinsics.checkNotNull(systemService2, "null cannot be cast to non-null type android.media.AudioManager");
            ((AudioManager) systemService2).requestAudioFocus(audioFocusRequest);
        }
    }

    public final Event<Boolean> getOnAudioFocusEvent() {
        return onAudioFocusEvent;
    }

    public final void setOnAudioFocusEvent(Event<Boolean> event) {
        Intrinsics.checkNotNullParameter(event, "<set-?>");
        onAudioFocusEvent = event;
    }

    private final AudioManager.OnAudioFocusChangeListener getAudioListener() {
        AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = currentAudioFocusChangeListener;
        if (onAudioFocusChangeListener != null) {
            return onAudioFocusChangeListener;
        }
        AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener2 = new AudioManager.OnAudioFocusChangeListener() { // from class: com.lagradost.cloudstream3.utils.AppContextUtils$$ExternalSyntheticLambda9
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public final void onAudioFocusChange(int i) {
                AppContextUtils.getAudioListener$lambda$0(i);
            }
        };
        currentAudioFocusChangeListener = onAudioFocusChangeListener2;
        return onAudioFocusChangeListener2;
    }

    static final void getAudioListener$lambda$0(int i) {
        Event<Boolean> event = onAudioFocusEvent;
        boolean z = false;
        if (i != 1 && i != 2 && i != 4) {
            z = true;
        }
        event.invoke(Boolean.valueOf(z));
    }

    public final boolean isCastApiAvailable(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        boolean z = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context.getApplicationContext()) == 0;
        try {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                Task<CastContext> sharedInstance = CastContext.getSharedInstance(applicationContext, new Executor() { // from class: com.lagradost.cloudstream3.utils.AppContextUtils$$ExternalSyntheticLambda19
                    @Override // java.util.concurrent.Executor
                    public final void execute(Runnable runnable) {
                        runnable.run();
                    }
                });
                Intrinsics.checkNotNullExpressionValue(sharedInstance, "getSharedInstance(...)");
                sharedInstance.getResult();
            }
            return z;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public final boolean isConnectedToChromecast(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        if (!isCastApiAvailable(context)) {
            return false;
        }
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(executorServiceNewSingleThreadExecutor, "newSingleThreadExecutor(...)");
        Task<CastContext> sharedInstance = CastContext.getSharedInstance(context, executorServiceNewSingleThreadExecutor);
        Intrinsics.checkNotNullExpressionValue(sharedInstance, "getSharedInstance(...)");
        return sharedInstance.getResult().getCastState() == 4;
    }

    public static /* synthetic */ void setDefaultFocus$default(AppContextUtils appContextUtils, AlertDialog alertDialog, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = -2;
        }
        appContextUtils.setDefaultFocus(alertDialog, i);
    }

    public final void setDefaultFocus(AlertDialog alertDialog, int i) {
        Intrinsics.checkNotNullParameter(alertDialog, "<this>");
        if (Globals.INSTANCE.isLayout(6)) {
            Button button = alertDialog.getButton(i);
            button.setFocusableInTouchMode(true);
            button.requestFocus();
        }
    }

    public final boolean isUsingMobileData(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Object systemService = context.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        return (networkCapabilities == null || !networkCapabilities.hasTransport(0) || networkCapabilities.hasTransport(1)) ? false : true;
    }

    private final void cacheClass(Activity activity, String str) {
        File cacheDir;
        if (str == null || activity == null || (cacheDir = activity.getCacheDir()) == null) {
            return;
        }
        new Cache(new File(cacheDir, FillerEpisodeCheck.INSTANCE.toClassDir(str)), 20971520L);
    }

    public final boolean isAppInstalled(Context context, String uri) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(uri, "uri");
        PackageManagerWrapper packageManagerWrapperPackageManager = Wrappers.packageManager(context);
        Intrinsics.checkNotNullExpressionValue(packageManagerWrapperPackageManager, "packageManager(...)");
        try {
            packageManagerWrapperPackageManager.getPackageInfo(uri, 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public final AudioFocusRequest getFocusRequest() {
        AudioFocusRequest audioFocusRequestBuild;
        AudioFocusRequest audioFocusRequest = currentAudioFocusRequest;
        if (audioFocusRequest != null) {
            return audioFocusRequest;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            AudioFocusRequest.Builder builderM8387m = CommonActivity$$ExternalSyntheticApiModelOutline0.m8387m(1);
            AudioAttributes.Builder builder = new AudioAttributes.Builder();
            builder.setUsage(1);
            builder.setContentType(3);
            builderM8387m.setAudioAttributes(builder.build());
            builderM8387m.setAcceptsDelayedFocusGain(true);
            AudioManager.OnAudioFocusChangeListener audioListener = INSTANCE.getAudioListener();
            if (audioListener != null) {
                builderM8387m.setOnAudioFocusChangeListener(audioListener);
            }
            audioFocusRequestBuild = builderM8387m.build();
        } else {
            audioFocusRequestBuild = null;
        }
        currentAudioFocusRequest = audioFocusRequestBuild;
        return audioFocusRequestBuild;
    }
}
