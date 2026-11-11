package com.lagradost.cloudstream3.plugins;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.FragmentActivity;
import coil3.util.UtilsKt;
import com.fasterxml.jackson.core.type.TypeReference;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.AutoDownloadMode;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.CommonActivity$$ExternalSyntheticApiModelOutline0;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivity;
import com.lagradost.cloudstream3.ParCollectionsKt;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.actions.VideoClickAction;
import com.lagradost.cloudstream3.actions.VideoClickActionHolder;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsViewModelKt;
import com.lagradost.cloudstream3.ui.settings.extensions.RepositoryData;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.AppDebug;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.DataStore;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorApiKt;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UIHelper;
import com.lagradost.cloudstream3.utils.UiText;
import com.lagradost.cloudstream3.utils.downloader.DownloadFileManagement;
import dalvik.system.PathClassLoader;
import j$.util.function.Predicate$CC;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.internal.ReferenceArraySerializer;
import kotlinx.serialization.modules.SerializersModule;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: PluginManager.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\n\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001dB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0082@¢\u0006\u0002\u0010\u0010J\u0018\u0010\u0011\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0082@¢\u0006\u0002\u0010\u0010J\u0016\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\tH\u0086@¢\u0006\u0002\u0010\u0014J\u000e\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0017J\u0011\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0019¢\u0006\u0002\u0010\u001aJ\u0011\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0019¢\u0006\u0002\u0010\u001aJ\u001e\u00102\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u00103\u001a\u000204H\u0082@¢\u0006\u0002\u00105J\u001e\u00106\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u00107\u001a\u00020\tH\u0086@¢\u0006\u0002\u00108J\u0016\u00109\u001a\u00020\r2\u0006\u0010:\u001a\u00020;H\u0087@¢\u0006\u0002\u0010<J\u001e\u0010=\u001a\u00020\r2\u0006\u0010:\u001a\u00020;2\u0006\u0010>\u001a\u00020?H\u0087@¢\u0006\u0002\u0010@J\b\u0010A\u001a\u00020\rH\u0002J\u0016\u0010B\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0017H\u0087@¢\u0006\u0002\u0010CJ\u0018\u0010D\u001a\u00020\r2\b\u0010:\u001a\u0004\u0018\u00010EH\u0087@¢\u0006\u0002\u0010FJ\u001e\u0010G\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010H\u001a\u00020\u000bH\u0087@¢\u0006\u0002\u0010IJ\u0006\u0010J\u001a\u00020\u000bJ\u0006\u0010K\u001a\u00020\u000bJ&\u0010L\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u00103\u001a\u0002042\u0006\u0010\u000e\u001a\u00020\u000fH\u0082@¢\u0006\u0002\u0010MJ\u000e\u0010N\u001a\u00020\r2\u0006\u0010O\u001a\u00020\tJ\u000e\u0010P\u001a\u00020\t2\u0006\u0010Q\u001a\u00020\tJ\u001e\u0010R\u001a\u0002042\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010S\u001a\u00020\t2\u0006\u0010T\u001a\u00020\tJ@\u0010U\u001a\u00020\u000b2\u0006\u0010:\u001a\u00020;2\u0006\u0010V\u001a\u00020\t2\b\u0010W\u001a\u0004\u0018\u00010\t2\u0006\u0010S\u001a\u00020\t2\u0006\u0010T\u001a\u00020\t2\u0006\u0010L\u001a\u00020\u000bH\u0086@¢\u0006\u0002\u0010XJ@\u0010U\u001a\u00020\u000b2\u0006\u0010:\u001a\u00020;2\u0006\u0010V\u001a\u00020\t2\b\u0010W\u001a\u0004\u0018\u00010\t2\u0006\u0010S\u001a\u00020\t2\u0006\u00103\u001a\u0002042\u0006\u0010L\u001a\u00020\u000bH\u0086@¢\u0006\u0002\u0010YJ\u0016\u0010Z\u001a\u00020\u000b2\u0006\u00103\u001a\u000204H\u0086@¢\u0006\u0002\u0010[J\u0016\u0010\\\u001a\u00020\r2\u0006\u0010:\u001a\u00020;H\u0087@¢\u0006\u0002\u0010<J\f\u0010]\u001a\u00020\r*\u00020\u0017H\u0002J(\u0010^\u001a\u0004\u0018\u00010_2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010`\u001a\u00020a2\f\u0010b\u001a\b\u0012\u0004\u0012\u00020\t0cH\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u001e\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001d\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020%0$¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u001d\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020%0$¢\u0006\b\n\u0000\u001a\u0004\b)\u0010'R\u001a\u0010*\u001a\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020%0$X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010-\u001a\u00020\u000b2\u0006\u0010,\u001a\u00020\u000b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u001e\u00100\u001a\u00020\u000b2\u0006\u0010,\u001a\u00020\u000b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b1\u0010/¨\u0006e"}, d2 = {"Lcom/lagradost/cloudstream3/plugins/PluginManager;", "", "<init>", "()V", "lock", "Lkotlinx/coroutines/sync/Mutex;", "getLock", "()Lkotlinx/coroutines/sync/Mutex;", "TAG", "", "hasCreatedNotChanel", "", "setPluginData", "", "data", "Lcom/lagradost/cloudstream3/plugins/PluginData;", "(Lcom/lagradost/cloudstream3/plugins/PluginData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePluginData", "deleteRepositoryData", "repositoryPath", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllOatFiles", "context", "Landroid/content/Context;", "getPluginsOnline", "", "()[Lcom/lagradost/cloudstream3/plugins/PluginData;", "getPluginsLocal", "CLOUD_STREAM_FOLDER", "LOCAL_PLUGINS_PATH", "currentlyLoading", "getCurrentlyLoading", "()Ljava/lang/String;", "setCurrentlyLoading", "(Ljava/lang/String;)V", "plugins", "", "Lcom/lagradost/cloudstream3/plugins/BasePlugin;", "getPlugins", "()Ljava/util/Map;", "urlPlugins", "getUrlPlugins", "classLoaders", "Ldalvik/system/PathClassLoader;", ES6Iterator.VALUE_PROPERTY, "loadedLocalPlugins", "getLoadedLocalPlugins", "()Z", "loadedOnlinePlugins", "getLoadedOnlinePlugins", "maybeLoadPlugin", UtilsKt.SCHEME_FILE, "Ljava/io/File;", "(Landroid/content/Context;Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadSinglePlugin", "apiName", "(Landroid/content/Context;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "___DO_NOT_CALL_FROM_A_PLUGIN_updateAllOnlinePluginsAndLoadThem", "activity", "Landroid/app/Activity;", "(Landroid/app/Activity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "___DO_NOT_CALL_FROM_A_PLUGIN_downloadNotExistingPluginsAndLoad", "mode", "Lcom/lagradost/cloudstream3/AutoDownloadMode;", "(Landroid/app/Activity;Lcom/lagradost/cloudstream3/AutoDownloadMode;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "assertNonRecursiveCallstack", "___DO_NOT_CALL_FROM_A_PLUGIN_loadAllOnlinePlugins", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "___DO_NOT_CALL_FROM_A_PLUGIN_hotReloadAllLocalPlugins", "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "___DO_NOT_CALL_FROM_A_PLUGIN_loadAllLocalPlugins", "forceReload", "(Landroid/content/Context;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isSafeMode", "checkSafeModeFile", "loadPlugin", "(Landroid/content/Context;Ljava/io/File;Lcom/lagradost/cloudstream3/plugins/PluginData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unloadPlugin", "absolutePath", "getPluginSanitizedFileName", "name", "getPluginPath", "internalName", "repositoryUrl", "downloadPlugin", "pluginUrl", "pluginHash", "(Landroid/app/Activity;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Landroid/app/Activity;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/io/File;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePlugin", "(Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "___DO_NOT_CALL_FROM_A_PLUGIN_manuallyReloadAndUpdatePlugins", "createNotificationChannel", "createNotification", "Landroid/app/Notification;", "uitext", "Lcom/lagradost/cloudstream3/utils/UiText;", "extensions", "", "OnlinePluginData", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class PluginManager {
    private static final String CLOUD_STREAM_FOLDER;
    private static final String LOCAL_PLUGINS_PATH;
    public static final String TAG = "PluginManager";
    private static final Map<PathClassLoader, BasePlugin> classLoaders;
    private static String currentlyLoading;
    private static boolean hasCreatedNotChanel;
    private static boolean loadedLocalPlugins;
    private static boolean loadedOnlinePlugins;
    private static final Map<String, BasePlugin> plugins;
    private static final Map<String, BasePlugin> urlPlugins;
    public static final PluginManager INSTANCE = new PluginManager();
    private static final Mutex lock = MutexKt.Mutex$default(false, 1, null);

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.plugins.PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_downloadNotExistingPluginsAndLoad$1, reason: invalid class name */
    /* JADX INFO: compiled from: PluginManager.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.plugins.PluginManager", f = "PluginManager.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1}, l = {361, 421}, m = "___DO_NOT_CALL_FROM_A_PLUGIN_downloadNotExistingPluginsAndLoad", n = {"activity", "mode", "newDownloadPlugins", "urls", "activity", "mode", "newDownloadPlugins", "urls", "onlinePlugins", "providerLang", "notDownloadedPlugins"}, nl = {363, 435}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PluginManager.this.___DO_NOT_CALL_FROM_A_PLUGIN_downloadNotExistingPluginsAndLoad(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.plugins.PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_loadAllLocalPlugins$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PluginManager.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.plugins.PluginManager", f = "PluginManager.kt", i = {0, 0, 0, 0, 0}, l = {538}, m = "___DO_NOT_CALL_FROM_A_PLUGIN_loadAllLocalPlugins", n = {"context", "dir", "sortedPlugins", "pluginDirectory", "forceReload"}, nl = {566}, s = {"L$0", "L$1", "L$2", "L$3", "Z$0"}, v = 2)
    static final class C07441 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C07441(Continuation<? super C07441> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PluginManager.this.___DO_NOT_CALL_FROM_A_PLUGIN_loadAllLocalPlugins(null, false, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.plugins.PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_manuallyReloadAndUpdatePlugins$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PluginManager.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.plugins.PluginManager", f = "PluginManager.kt", i = {0, 1, 1, 2, 2, 2, 2, 2}, l = {835, 840, 854}, m = "___DO_NOT_CALL_FROM_A_PLUGIN_manuallyReloadAndUpdatePlugins", n = {"activity", "activity", "urls", "activity", "urls", "onlinePlugins", "allPlugins", "updatedPlugins"}, nl = {836, 842, 877}, s = {"L$0", "L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "L$4"}, v = 2)
    static final class C07471 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C07471(Continuation<? super C07471> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PluginManager.this.___DO_NOT_CALL_FROM_A_PLUGIN_manuallyReloadAndUpdatePlugins(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.plugins.PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_updateAllOnlinePluginsAndLoadThem$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PluginManager.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.plugins.PluginManager", f = "PluginManager.kt", i = {0, 1, 1, 2, 2, 2, 2, 2}, l = {278, 284, 305}, m = "___DO_NOT_CALL_FROM_A_PLUGIN_updateAllOnlinePluginsAndLoadThem", n = {"activity", "activity", "urls", "activity", "urls", "onlinePlugins", "outdatedPlugins", "updatedPlugins"}, nl = {279, 286, 324}, s = {"L$0", "L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "L$4"}, v = 2)
    static final class C07491 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C07491(Continuation<? super C07491> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PluginManager.this.___DO_NOT_CALL_FROM_A_PLUGIN_updateAllOnlinePluginsAndLoadThem(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.plugins.PluginManager$deletePlugin$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PluginManager.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.plugins.PluginManager", f = "PluginManager.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {814}, m = "deletePlugin", n = {UtilsKt.SCHEME_FILE, "list", "$this$forEach$iv", "element$iv", "it", "$i$f$forEach", "$i$a$-forEach-PluginManager$deletePlugin$2"}, nl = {972}, s = {"L$0", "L$1", "L$2", "L$4", "L$5", "I$0", "I$1"}, v = 2)
    static final class C07511 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        C07511(Continuation<? super C07511> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PluginManager.this.deletePlugin(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.plugins.PluginManager$deletePluginData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PluginManager.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.plugins.PluginManager", f = "PluginManager.kt", i = {0, 0, 0}, l = {974}, m = "deletePluginData", n = {"data", "$this$withLock_u24default$iv", "$i$f$withLock"}, nl = {975}, s = {"L$0", "L$1", "I$0"}, v = 2)
    static final class C07521 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C07521(Continuation<? super C07521> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PluginManager.this.deletePluginData(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.plugins.PluginManager$deleteRepositoryData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PluginManager.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.plugins.PluginManager", f = "PluginManager.kt", i = {0, 0, 0}, l = {974}, m = "deleteRepositoryData", n = {"repositoryPath", "$this$withLock_u24default$iv", "$i$f$withLock"}, nl = {975}, s = {"L$0", "L$1", "I$0"}, v = 2)
    static final class C07531 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C07531(Continuation<? super C07531> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PluginManager.this.deleteRepositoryData(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.plugins.PluginManager$downloadPlugin$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PluginManager.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.plugins.PluginManager", f = "PluginManager.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2}, l = {780, 792, 798}, m = "downloadPlugin", n = {"activity", "pluginUrl", "pluginHash", "internalName", UtilsKt.SCHEME_FILE, "loadPlugin", "activity", "pluginUrl", "pluginHash", "internalName", UtilsKt.SCHEME_FILE, "newFile", "data", "loadPlugin", "activity", "pluginUrl", "pluginHash", "internalName", UtilsKt.SCHEME_FILE, "newFile", "data", "loadPlugin"}, nl = {782, 798, 799}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "Z$0"}, v = 2)
    static final class C07542 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C07542(Continuation<? super C07542> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PluginManager.this.downloadPlugin((Activity) null, (String) null, (String) null, (String) null, (File) null, false, (Continuation<? super Boolean>) this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.plugins.PluginManager$loadPlugin$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PluginManager.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.plugins.PluginManager", f = "PluginManager.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {637}, m = "loadPlugin", n = {"context", UtilsKt.SCHEME_FILE, "data", "fileName", "filePath", "loader", "manifest", "name", "pluginClass", "pluginInstance", "version"}, nl = {639}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "I$0"}, v = 2)
    static final class C07551 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        C07551(Continuation<? super C07551> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PluginManager.this.loadPlugin(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.plugins.PluginManager$loadSinglePlugin$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PluginManager.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.plugins.PluginManager", f = "PluginManager.kt", i = {0, 0, 0, 0}, l = {253}, m = "loadSinglePlugin", n = {"context", "apiName", "savedData", "$i$a$-let-PluginManager$loadSinglePlugin$4"}, nl = {257}, s = {"L$0", "L$1", "L$2", "I$0"}, v = 2)
    static final class C07561 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C07561(Continuation<? super C07561> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PluginManager.this.loadSinglePlugin(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.plugins.PluginManager$setPluginData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PluginManager.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.plugins.PluginManager", f = "PluginManager.kt", i = {0, 0, 0}, l = {974}, m = "setPluginData", n = {"data", "$this$withLock_u24default$iv", "$i$f$withLock"}, nl = {975}, s = {"L$0", "L$1", "I$0"}, v = 2)
    static final class C07571 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C07571(Continuation<? super C07571> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PluginManager.this.setPluginData(null, this);
        }
    }

    private PluginManager() {
    }

    static {
        String str = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Cloudstream3/";
        CLOUD_STREAM_FOLDER = str;
        LOCAL_PLUGINS_PATH = str + "plugins";
        plugins = new LinkedHashMap();
        urlPlugins = new LinkedHashMap();
        classLoaders = new HashMap();
    }

    public final Mutex getLock() {
        return lock;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object setPluginData(PluginData pluginData, Continuation<? super Unit> continuation) {
        C07571 c07571;
        Mutex mutex;
        if (continuation instanceof C07571) {
            c07571 = (C07571) continuation;
            if ((c07571.label & Integer.MIN_VALUE) != 0) {
                c07571.label -= Integer.MIN_VALUE;
            } else {
                c07571 = new C07571(continuation);
            }
        } else {
            c07571 = new C07571(continuation);
        }
        Object obj = c07571.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07571.label;
        int i2 = 0;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            mutex = lock;
            c07571.L$0 = pluginData;
            c07571.L$1 = mutex;
            c07571.I$0 = 0;
            c07571.label = 1;
            if (mutex.lock(null, c07571) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i3 = c07571.I$0;
            Mutex mutex2 = (Mutex) c07571.L$1;
            PluginData pluginData2 = (PluginData) c07571.L$0;
            ResultKt.throwOnFailure(obj);
            mutex = mutex2;
            pluginData = pluginData2;
        }
        try {
            if (pluginData.isOnline()) {
                PluginData[] pluginsOnline = INSTANCE.getPluginsOnline();
                ArrayList arrayList = new ArrayList();
                int length = pluginsOnline.length;
                while (i2 < length) {
                    PluginData pluginData3 = pluginsOnline[i2];
                    if (!Intrinsics.areEqual(pluginData3.getFilePath(), pluginData.getFilePath())) {
                        arrayList.add(pluginData3);
                    }
                    i2++;
                }
                CloudStreamApp.INSTANCE.setKey(PluginManagerKt.PLUGINS_KEY, CollectionsKt.plus((Collection<? extends PluginData>) arrayList, pluginData));
            } else {
                PluginData[] pluginsLocal = INSTANCE.getPluginsLocal();
                CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
                ArrayList arrayList2 = new ArrayList();
                int length2 = pluginsLocal.length;
                while (i2 < length2) {
                    PluginData pluginData4 = pluginsLocal[i2];
                    if (!Intrinsics.areEqual(pluginData4.getFilePath(), pluginData.getFilePath())) {
                        arrayList2.add(pluginData4);
                    }
                    i2++;
                }
                companion.setKey(PluginManagerKt.PLUGINS_KEY_LOCAL, CollectionsKt.plus((Collection<? extends PluginData>) arrayList2, pluginData));
            }
            Unit unit = Unit.INSTANCE;
            return Unit.INSTANCE;
        } finally {
            mutex.unlock(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object deletePluginData(PluginData pluginData, Continuation<? super Unit> continuation) {
        C07521 c07521;
        Mutex mutex;
        if (continuation instanceof C07521) {
            c07521 = (C07521) continuation;
            if ((c07521.label & Integer.MIN_VALUE) != 0) {
                c07521.label -= Integer.MIN_VALUE;
            } else {
                c07521 = new C07521(continuation);
            }
        } else {
            c07521 = new C07521(continuation);
        }
        Object obj = c07521.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07521.label;
        int i2 = 0;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (pluginData == null) {
                return Unit.INSTANCE;
            }
            mutex = lock;
            c07521.L$0 = pluginData;
            c07521.L$1 = mutex;
            c07521.I$0 = 0;
            c07521.label = 1;
            if (mutex.lock(null, c07521) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i3 = c07521.I$0;
            Mutex mutex2 = (Mutex) c07521.L$1;
            PluginData pluginData2 = (PluginData) c07521.L$0;
            ResultKt.throwOnFailure(obj);
            mutex = mutex2;
            pluginData = pluginData2;
        }
        try {
            if (pluginData.isOnline()) {
                PluginData[] pluginsOnline = INSTANCE.getPluginsOnline();
                ArrayList arrayList = new ArrayList();
                int length = pluginsOnline.length;
                while (i2 < length) {
                    PluginData pluginData3 = pluginsOnline[i2];
                    if (!Intrinsics.areEqual(pluginData3.getUrl(), pluginData.getUrl())) {
                        arrayList.add(pluginData3);
                    }
                    i2++;
                }
                CloudStreamApp.INSTANCE.setKey(PluginManagerKt.PLUGINS_KEY, arrayList);
            } else {
                PluginData[] pluginsLocal = INSTANCE.getPluginsLocal();
                ArrayList arrayList2 = new ArrayList();
                int length2 = pluginsLocal.length;
                while (i2 < length2) {
                    PluginData pluginData4 = pluginsLocal[i2];
                    if (!Intrinsics.areEqual(pluginData4.getFilePath(), pluginData.getFilePath())) {
                        arrayList2.add(pluginData4);
                    }
                    i2++;
                }
                CloudStreamApp.INSTANCE.setKey(PluginManagerKt.PLUGINS_KEY_LOCAL, arrayList2);
            }
            Unit unit = Unit.INSTANCE;
            return Unit.INSTANCE;
        } finally {
            mutex.unlock(null);
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object deleteRepositoryData(String str, Continuation<? super Unit> continuation) {
        C07531 c07531;
        Mutex mutex;
        if (continuation instanceof C07531) {
            c07531 = (C07531) continuation;
            if ((c07531.label & Integer.MIN_VALUE) != 0) {
                c07531.label -= Integer.MIN_VALUE;
            } else {
                c07531 = new C07531(continuation);
            }
        } else {
            c07531 = new C07531(continuation);
        }
        Object obj = c07531.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07531.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            mutex = lock;
            c07531.L$0 = str;
            c07531.L$1 = mutex;
            c07531.I$0 = 0;
            c07531.label = 1;
            if (mutex.lock(null, c07531) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = c07531.I$0;
            Mutex mutex2 = (Mutex) c07531.L$1;
            String str2 = (String) c07531.L$0;
            ResultKt.throwOnFailure(obj);
            mutex = mutex2;
            str = str2;
        }
        try {
            PluginData[] pluginsOnline = INSTANCE.getPluginsOnline();
            ArrayList arrayList = new ArrayList();
            for (PluginData pluginData : pluginsOnline) {
                if (!StringsKt.contains$default((CharSequence) pluginData.getFilePath(), (CharSequence) str, false, 2, (Object) null)) {
                    arrayList.add(pluginData);
                }
            }
            final File file = new File(str);
            ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return PluginManager.deleteRepositoryData$lambda$0$1(file);
                }
            });
            CloudStreamApp.INSTANCE.setKey(PluginManagerKt.PLUGINS_KEY, arrayList);
            Unit unit = Unit.INSTANCE;
            return Unit.INSTANCE;
        } finally {
            mutex.unlock(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit deleteRepositoryData$lambda$0$1(File file) {
        if (file.exists()) {
            FilesKt.deleteRecursively(file);
        }
        return Unit.INSTANCE;
    }

    public final void deleteAllOatFiles(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        File[] fileArrListFiles = new File(context.getFilesDir() + "/Extensions").listFiles();
        if (fileArrListFiles != null) {
            for (File file : fileArrListFiles) {
                File[] fileArrListFiles2 = file.listFiles(new FileFilter() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$$ExternalSyntheticLambda16
                    @Override // java.io.FileFilter
                    public final boolean accept(File file2) {
                        return PluginManager.deleteAllOatFiles$lambda$0$0(file2);
                    }
                });
                if (fileArrListFiles2 != null) {
                    for (File file2 : fileArrListFiles2) {
                        Intrinsics.checkNotNull(file2);
                        Log.i(TAG, "Deleted oat directory: " + file2.getAbsolutePath() + " Success=" + FilesKt.deleteRecursively(file2));
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean deleteAllOatFiles$lambda$0$0(File file) {
        return Intrinsics.areEqual(file.getName(), "oat") && file.isDirectory();
    }

    public final PluginData[] getPluginsOnline() {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Context context = CloudStreamApp.INSTANCE.getContext();
        Object obj = null;
        if (context != null) {
            try {
                String string = DataStore.INSTANCE.getSharedPrefs(context).getString(PluginManagerKt.PLUGINS_KEY, null);
                if (string != null) {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(PluginData.class), PluginData.INSTANCE.serializer()));
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                        try {
                            Result.Companion companion3 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(PluginData[].class), null, 2, null));
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
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<PluginData[]>() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$getPluginsOnline$$inlined$getKey$1
                            });
                        } catch (Throwable unused) {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<PluginData[]>() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$getPluginsOnline$$inlined$getKey$1
                            });
                        }
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<PluginData[]>() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$getPluginsOnline$$inlined$getKey$1
                        });
                    }
                    obj = objDecodeFromString;
                }
            } catch (Exception unused2) {
            }
        }
        PluginData[] pluginDataArr = (PluginData[]) obj;
        return pluginDataArr == null ? new PluginData[0] : pluginDataArr;
    }

    public final PluginData[] getPluginsLocal() {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Context context = CloudStreamApp.INSTANCE.getContext();
        Object obj = null;
        if (context != null) {
            try {
                String string = DataStore.INSTANCE.getSharedPrefs(context).getString(PluginManagerKt.PLUGINS_KEY_LOCAL, null);
                if (string != null) {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(PluginData.class), PluginData.INSTANCE.serializer()));
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                        try {
                            Result.Companion companion3 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(PluginData[].class), null, 2, null));
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
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<PluginData[]>() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$getPluginsLocal$$inlined$getKey$1
                            });
                        } catch (Throwable unused) {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<PluginData[]>() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$getPluginsLocal$$inlined$getKey$1
                            });
                        }
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<PluginData[]>() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$getPluginsLocal$$inlined$getKey$1
                        });
                    }
                    obj = objDecodeFromString;
                }
            } catch (Exception unused2) {
            }
        }
        PluginData[] pluginDataArr = (PluginData[]) obj;
        return pluginDataArr == null ? new PluginData[0] : pluginDataArr;
    }

    public final String getCurrentlyLoading() {
        return currentlyLoading;
    }

    public final void setCurrentlyLoading(String str) {
        currentlyLoading = str;
    }

    public final Map<String, BasePlugin> getPlugins() {
        return plugins;
    }

    public final Map<String, BasePlugin> getUrlPlugins() {
        return urlPlugins;
    }

    public final boolean getLoadedLocalPlugins() {
        return loadedLocalPlugins;
    }

    public final boolean getLoadedOnlinePlugins() {
        return loadedOnlinePlugins;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object maybeLoadPlugin(Context context, File file, Continuation<? super Unit> continuation) {
        String name = file.getName();
        if (Intrinsics.areEqual(FilesKt.getExtension(file), "zip") || Intrinsics.areEqual(FilesKt.getExtension(file), "cs3")) {
            Intrinsics.checkNotNull(name);
            String absolutePath = file.getAbsolutePath();
            Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
            Object objLoadPlugin = loadPlugin(context, file, new PluginData(name, null, false, absolutePath, Integer.MIN_VALUE), continuation);
            return objLoadPlugin == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objLoadPlugin : Unit.INSTANCE;
        }
        Log.i(TAG, "Skipping invalid plugin file: " + file);
        return Unit.INSTANCE;
    }

    /* JADX INFO: compiled from: PluginManager.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0016\u001a\u00020\r2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000e¨\u0006\u001c"}, d2 = {"Lcom/lagradost/cloudstream3/plugins/PluginManager$OnlinePluginData;", "", "savedData", "Lcom/lagradost/cloudstream3/plugins/PluginData;", "onlineData", "Lcom/lagradost/cloudstream3/plugins/PluginWrapper;", "<init>", "(Lcom/lagradost/cloudstream3/plugins/PluginData;Lcom/lagradost/cloudstream3/plugins/PluginWrapper;)V", "getSavedData", "()Lcom/lagradost/cloudstream3/plugins/PluginData;", "getOnlineData", "()Lcom/lagradost/cloudstream3/plugins/PluginWrapper;", "isOutdated", "", "()Z", "isDisabled", "validOnlineData", "context", "Landroid/content/Context;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class OnlinePluginData {
        private final boolean isDisabled;
        private final boolean isOutdated;
        private final PluginWrapper onlineData;
        private final PluginData savedData;

        public static /* synthetic */ OnlinePluginData copy$default(OnlinePluginData onlinePluginData, PluginData pluginData, PluginWrapper pluginWrapper, int i, Object obj) {
            if ((i & 1) != 0) {
                pluginData = onlinePluginData.savedData;
            }
            if ((i & 2) != 0) {
                pluginWrapper = onlinePluginData.onlineData;
            }
            return onlinePluginData.copy(pluginData, pluginWrapper);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final PluginData getSavedData() {
            return this.savedData;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final PluginWrapper getOnlineData() {
            return this.onlineData;
        }

        public final OnlinePluginData copy(PluginData savedData, PluginWrapper onlineData) {
            Intrinsics.checkNotNullParameter(savedData, "savedData");
            Intrinsics.checkNotNullParameter(onlineData, "onlineData");
            return new OnlinePluginData(savedData, onlineData);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof OnlinePluginData)) {
                return false;
            }
            OnlinePluginData onlinePluginData = (OnlinePluginData) other;
            return Intrinsics.areEqual(this.savedData, onlinePluginData.savedData) && Intrinsics.areEqual(this.onlineData, onlinePluginData.onlineData);
        }

        public int hashCode() {
            return (this.savedData.hashCode() * 31) + this.onlineData.hashCode();
        }

        public String toString() {
            return "OnlinePluginData(savedData=" + this.savedData + ", onlineData=" + this.onlineData + ')';
        }

        public OnlinePluginData(PluginData savedData, PluginWrapper onlineData) {
            Intrinsics.checkNotNullParameter(savedData, "savedData");
            Intrinsics.checkNotNullParameter(onlineData, "onlineData");
            this.savedData = savedData;
            this.onlineData = onlineData;
            this.isOutdated = onlineData.getPlugin().getVersion() > savedData.getVersion() || onlineData.getPlugin().getVersion() == -1;
            this.isDisabled = onlineData.getPlugin().getStatus() == 0;
        }

        public final PluginData getSavedData() {
            return this.savedData;
        }

        public final PluginWrapper getOnlineData() {
            return this.onlineData;
        }

        /* JADX INFO: renamed from: isOutdated, reason: from getter */
        public final boolean getIsOutdated() {
            return this.isOutdated;
        }

        /* JADX INFO: renamed from: isDisabled, reason: from getter */
        public final boolean getIsDisabled() {
            return this.isDisabled;
        }

        public final boolean validOnlineData(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return Intrinsics.areEqual(PluginManager.INSTANCE.getPluginPath(context, this.savedData.getInternalName(), this.onlineData.getRepositoryData().getUrl()).getAbsolutePath(), this.savedData.getFilePath());
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object loadSinglePlugin(Context context, String str, Continuation<? super Boolean> continuation) {
        C07561 c07561;
        boolean zBooleanValue;
        PluginData pluginData;
        PluginData pluginData2;
        if (continuation instanceof C07561) {
            c07561 = (C07561) continuation;
            if ((c07561.label & Integer.MIN_VALUE) != 0) {
                c07561.label -= Integer.MIN_VALUE;
            } else {
                c07561 = new C07561(continuation);
            }
        } else {
            c07561 = new C07561(continuation);
        }
        Object objLoadPlugin = c07561.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07561.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objLoadPlugin);
            PluginData[] pluginsOnline = getPluginsOnline();
            int length = pluginsOnline.length;
            zBooleanValue = false;
            int i2 = 0;
            while (true) {
                pluginData = null;
                if (i2 >= length) {
                    pluginData2 = null;
                    break;
                }
                pluginData2 = pluginsOnline[i2];
                if (Intrinsics.areEqual(StringsKt.replace(pluginData2.getInternalName(), "provider", "", true), str)) {
                    break;
                }
                i2++;
            }
            if (pluginData2 == null) {
                for (PluginData pluginData3 : getPluginsLocal()) {
                    if (Intrinsics.areEqual(StringsKt.replace(pluginData3.getInternalName(), "provider", "", true), str)) {
                        pluginData = pluginData3;
                        break;
                    }
                }
            } else {
                pluginData = pluginData2;
            }
            if (pluginData != null) {
                PluginManager pluginManager = INSTANCE;
                File file = new File(pluginData.getFilePath());
                c07561.L$0 = SpillingKt.nullOutSpilledVariable(context);
                c07561.L$1 = SpillingKt.nullOutSpilledVariable(str);
                c07561.L$2 = SpillingKt.nullOutSpilledVariable(pluginData);
                c07561.I$0 = 0;
                c07561.label = 1;
                objLoadPlugin = pluginManager.loadPlugin(context, file, pluginData, c07561);
                if (objLoadPlugin == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Boxing.boxBoolean(zBooleanValue);
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        int i3 = c07561.I$0;
        ResultKt.throwOnFailure(objLoadPlugin);
        zBooleanValue = ((Boolean) objLoadPlugin).booleanValue();
        return Boxing.boxBoolean(zBooleanValue);
    }

    /* JADX WARN: Code duplicated, block: B:102:0x02f9  */
    /* JADX WARN: Code duplicated, block: B:112:0x0104 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:115:0x018d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:117:0x0172 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:122:0x01dc A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:124:0x0232 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:127:0x021c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:130:0x0280 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:132:0x0261 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:134:0x02b8 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:137:0x02a5 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:41:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:48:0x0115 A[Catch: Exception -> 0x0127, TRY_LEAVE, TryCatch #3 {Exception -> 0x0127, blocks: (B:24:0x009d, B:27:0x00a9, B:32:0x00ce, B:39:0x00f9, B:42:0x0100, B:48:0x0115, B:47:0x0110, B:38:0x00ef, B:31:0x00c4, B:28:0x00ab, B:35:0x00d5, B:44:0x0104), top: B:109:0x009d, inners: #1, #4, #5 }] */
    /* JADX WARN: Code duplicated, block: B:61:0x0178  */
    /* JADX WARN: Code duplicated, block: B:66:0x01a3  */
    /* JADX WARN: Code duplicated, block: B:69:0x01bd  */
    /* JADX WARN: Code duplicated, block: B:71:0x01d9  */
    /* JADX WARN: Code duplicated, block: B:76:0x01fe A[LOOP:3: B:74:0x01f8->B:76:0x01fe, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    /* JADX WARN: Code duplicated, block: B:80:0x0222  */
    /* JADX WARN: Code duplicated, block: B:87:0x0267  */
    /* JADX WARN: Code duplicated, block: B:92:0x028e  */
    /* JADX WARN: Code duplicated, block: B:95:0x02ab  */
    public final Object ___DO_NOT_CALL_FROM_A_PLUGIN_updateAllOnlinePluginsAndLoadThem(Activity activity, Continuation<? super Unit> continuation) {
        C07491 c07491;
        Object objDecodeFromString;
        RepositoryData[] repositoryDataArr;
        Activity activity2;
        Object objM8936constructorimpl;
        KSerializer kSerializer;
        HashSet hashSet;
        ArrayList arrayList;
        ArrayList arrayList2;
        PluginData[] pluginsOnline;
        ArrayList arrayList3;
        int length;
        int i;
        boolean z;
        boolean z2;
        HashSet hashSet2;
        ArrayList arrayList4;
        ArrayList arrayList5;
        ArrayList arrayList6;
        C07503 c07503;
        List list;
        Activity activity3;
        ArrayList arrayList7;
        PluginData pluginData;
        ArrayList arrayList8;
        ArrayList arrayList9;
        Iterator it;
        ArrayList arrayList10;
        Activity activity4 = activity;
        if (continuation instanceof C07491) {
            c07491 = (C07491) continuation;
            if ((c07491.label & Integer.MIN_VALUE) != 0) {
                c07491.label -= Integer.MIN_VALUE;
            } else {
                c07491 = new C07491(continuation);
            }
        } else {
            c07491 = new C07491(continuation);
        }
        Object obj = c07491.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c07491.label;
        boolean z3 = false;
        boolean z4 = true;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            assertNonRecursiveCallstack();
            c07491.L$0 = activity4;
            c07491.label = 1;
            if (___DO_NOT_CALL_FROM_A_PLUGIN_loadAllOnlinePlugins(activity4, c07491) != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        if (i2 == 1) {
            activity4 = (Activity) c07491.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            if (i2 == 2) {
                repositoryDataArr = (RepositoryData[]) c07491.L$1;
                activity2 = (Activity) c07491.L$0;
                ResultKt.throwOnFailure(obj);
                List listFlatten = CollectionsKt.flatten((Iterable) obj);
                hashSet = new HashSet();
                arrayList = new ArrayList();
                for (Object obj2 : listFlatten) {
                    if (hashSet.add(((PluginWrapper) obj2).getPlugin().getUrl())) {
                        arrayList.add(obj2);
                    }
                }
                arrayList2 = arrayList;
                pluginsOnline = getPluginsOnline();
                arrayList3 = new ArrayList(pluginsOnline.length);
                length = pluginsOnline.length;
                i = 0;
                while (i < length) {
                    pluginData = pluginsOnline[i];
                    boolean z5 = z3;
                    arrayList8 = new ArrayList();
                    for (Object obj3 : arrayList2) {
                        boolean z6 = z4;
                        if (Intrinsics.areEqual(pluginData.getInternalName(), ((PluginWrapper) obj3).getPlugin().getInternalName())) {
                            arrayList8.add(obj3);
                        }
                        z4 = z6;
                    }
                    boolean z7 = z4;
                    ArrayList arrayList11 = arrayList8;
                    arrayList9 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList11, 10));
                    it = arrayList11.iterator();
                    while (it.hasNext()) {
                        arrayList9.add(new OnlinePluginData(pluginData, (PluginWrapper) it.next()));
                    }
                    arrayList10 = new ArrayList();
                    for (Object obj4 : arrayList9) {
                        if (((OnlinePluginData) obj4).validOnlineData(activity2)) {
                            arrayList10.add(obj4);
                        }
                    }
                    arrayList3.add(arrayList10);
                    i++;
                    z3 = z5;
                    z4 = z7;
                }
                z = z3;
                z2 = z4;
                List listFlatten2 = CollectionsKt.flatten(arrayList3);
                hashSet2 = new HashSet();
                arrayList4 = new ArrayList();
                for (Object obj5 : listFlatten2) {
                    if (hashSet2.add(((OnlinePluginData) obj5).getOnlineData().getPlugin().getUrl())) {
                        arrayList4.add(obj5);
                    }
                }
                arrayList5 = arrayList4;
                if (AppDebug.INSTANCE.isDebug()) {
                    com.lagradost.api.Log log = com.lagradost.api.Log.INSTANCE;
                    StringBuilder sb = new StringBuilder("Outdated plugins: ");
                    arrayList7 = new ArrayList();
                    for (Object obj6 : arrayList5) {
                        if (((OnlinePluginData) obj6).getIsOutdated()) {
                            arrayList7.add(obj6);
                        }
                    }
                    sb.append(arrayList7);
                    log.d(ArchComponentExtKt.DEBUG_PRINT, sb.toString());
                }
                arrayList6 = new ArrayList();
                c07503 = new C07503(activity2, arrayList6, null);
                c07491.L$0 = activity2;
                c07491.L$1 = SpillingKt.nullOutSpilledVariable(repositoryDataArr);
                c07491.L$2 = SpillingKt.nullOutSpilledVariable(arrayList2);
                c07491.L$3 = SpillingKt.nullOutSpilledVariable(arrayList5);
                c07491.L$4 = arrayList6;
                c07491.label = 3;
                if (ParCollectionsKt.amap(arrayList5, c07503, c07491) != coroutine_suspended) {
                    list = arrayList6;
                    activity3 = activity2;
                }
                return coroutine_suspended;
            }
            if (i2 != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            list = (List) c07491.L$4;
            activity3 = (Activity) c07491.L$0;
            ResultKt.throwOnFailure(obj);
            z = false;
            z2 = true;
        }
        Coroutines.INSTANCE.main(this, new AnonymousClass4(list, activity3, null));
        loadedOnlinePlugins = z2;
        MainActivity.INSTANCE.getAfterPluginsLoadedEvent().invoke(Boxing.boxBoolean(z));
        Log.i(TAG, "Plugin update done!");
        return Unit.INSTANCE;
        Activity activity5 = activity4;
        MainActivity.INSTANCE.getAfterPluginsLoadedEvent().invoke(Boxing.boxBoolean(false));
        Context context = CloudStreamApp.INSTANCE.getContext();
        if (context != null) {
            try {
                String string = DataStore.INSTANCE.getSharedPrefs(context).getString(ExtensionsViewModelKt.REPOSITORIES_KEY, null);
                if (string == null) {
                    objDecodeFromString = null;
                } else {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(RepositoryData.class), RepositoryData.INSTANCE.serializer()));
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
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<RepositoryData[]>() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_updateAllOnlinePluginsAndLoadThem$$inlined$getKey$1
                                });
                            } catch (Throwable unused) {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<RepositoryData[]>() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_updateAllOnlinePluginsAndLoadThem$$inlined$getKey$1
                                });
                            }
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<RepositoryData[]>() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_updateAllOnlinePluginsAndLoadThem$$inlined$getKey$1
                            });
                        }
                    } else {
                        try {
                            Result.Companion companion3 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(RepositoryData[].class), null, 2, null));
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
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<RepositoryData[]>() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_updateAllOnlinePluginsAndLoadThem$$inlined$getKey$1
                            });
                        }
                    }
                }
            } catch (Exception unused2) {
                objDecodeFromString = null;
            }
        } else {
            objDecodeFromString = null;
        }
        RepositoryData[] repositoryDataArr2 = (RepositoryData[]) objDecodeFromString;
        if (repositoryDataArr2 == null) {
            repositoryDataArr2 = new RepositoryData[0];
        }
        repositoryDataArr = (RepositoryData[]) ArraysKt.plus((Object[]) repositoryDataArr2, (Object[]) RepositoryManager.INSTANCE.getPREBUILT_REPOSITORIES());
        List list2 = ArraysKt.toList(repositoryDataArr);
        PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_updateAllOnlinePluginsAndLoadThem$onlinePlugins$1 pluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_updateAllOnlinePluginsAndLoadThem$onlinePlugins$1 = new PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_updateAllOnlinePluginsAndLoadThem$onlinePlugins$1(null);
        c07491.L$0 = activity5;
        c07491.L$1 = SpillingKt.nullOutSpilledVariable(repositoryDataArr);
        c07491.label = 2;
        Object objAmap = ParCollectionsKt.amap(list2, pluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_updateAllOnlinePluginsAndLoadThem$onlinePlugins$1, c07491);
        if (objAmap != coroutine_suspended) {
            activity2 = activity5;
            obj = objAmap;
            List listFlatten3 = CollectionsKt.flatten((Iterable) obj);
            hashSet = new HashSet();
            arrayList = new ArrayList();
            while (r2.hasNext()) {
                if (hashSet.add(((PluginWrapper) obj2).getPlugin().getUrl())) {
                    arrayList.add(obj2);
                }
            }
            arrayList2 = arrayList;
            pluginsOnline = getPluginsOnline();
            arrayList3 = new ArrayList(pluginsOnline.length);
            length = pluginsOnline.length;
            i = 0;
            while (i < length) {
                pluginData = pluginsOnline[i];
                boolean z8 = z3;
                arrayList8 = new ArrayList();
                while (r15.hasNext()) {
                    boolean z9 = z4;
                    if (Intrinsics.areEqual(pluginData.getInternalName(), ((PluginWrapper) obj3).getPlugin().getInternalName())) {
                        arrayList8.add(obj3);
                    }
                    z4 = z9;
                }
                boolean z10 = z4;
                ArrayList arrayList12 = arrayList8;
                arrayList9 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList12, 10));
                it = arrayList12.iterator();
                while (it.hasNext()) {
                    arrayList9.add(new OnlinePluginData(pluginData, (PluginWrapper) it.next()));
                }
                arrayList10 = new ArrayList();
                while (r6.hasNext()) {
                    if (((OnlinePluginData) obj4).validOnlineData(activity2)) {
                        arrayList10.add(obj4);
                    }
                }
                arrayList3.add(arrayList10);
                i++;
                z3 = z8;
                z4 = z10;
            }
            z = z3;
            z2 = z4;
            List listFlatten4 = CollectionsKt.flatten(arrayList3);
            hashSet2 = new HashSet();
            arrayList4 = new ArrayList();
            while (r2.hasNext()) {
                if (hashSet2.add(((OnlinePluginData) obj5).getOnlineData().getPlugin().getUrl())) {
                    arrayList4.add(obj5);
                }
            }
            arrayList5 = arrayList4;
            if (AppDebug.INSTANCE.isDebug()) {
                com.lagradost.api.Log log2 = com.lagradost.api.Log.INSTANCE;
                StringBuilder sb2 = new StringBuilder("Outdated plugins: ");
                arrayList7 = new ArrayList();
                while (r8.hasNext()) {
                    if (((OnlinePluginData) obj6).getIsOutdated()) {
                        arrayList7.add(obj6);
                    }
                }
                sb2.append(arrayList7);
                log2.d(ArchComponentExtKt.DEBUG_PRINT, sb2.toString());
            }
            arrayList6 = new ArrayList();
            c07503 = new C07503(activity2, arrayList6, null);
            c07491.L$0 = activity2;
            c07491.L$1 = SpillingKt.nullOutSpilledVariable(repositoryDataArr);
            c07491.L$2 = SpillingKt.nullOutSpilledVariable(arrayList2);
            c07491.L$3 = SpillingKt.nullOutSpilledVariable(arrayList5);
            c07491.L$4 = arrayList6;
            c07491.label = 3;
            if (ParCollectionsKt.amap(arrayList5, c07503, c07491) != coroutine_suspended) {
                list = arrayList6;
                activity3 = activity2;
                Coroutines.INSTANCE.main(this, new AnonymousClass4(list, activity3, null));
                loadedOnlinePlugins = z2;
                MainActivity.INSTANCE.getAfterPluginsLoadedEvent().invoke(Boxing.boxBoolean(z));
                Log.i(TAG, "Plugin update done!");
                return Unit.INSTANCE;
            }
        }
        return coroutine_suspended;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.plugins.PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_updateAllOnlinePluginsAndLoadThem$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PluginManager.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "pluginData", "Lcom/lagradost/cloudstream3/plugins/PluginManager$OnlinePluginData;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.plugins.PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_updateAllOnlinePluginsAndLoadThem$3", f = "PluginManager.kt", i = {0}, l = {310}, m = "invokeSuspend", n = {"pluginData"}, nl = {TypedValues.AttributesType.TYPE_EASING}, s = {"L$0"}, v = 2)
    static final class C07503 extends SuspendLambda implements Function2<OnlinePluginData, Continuation<? super Unit>, Object> {
        final /* synthetic */ Activity $activity;
        final /* synthetic */ List<String> $updatedPlugins;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07503(Activity activity, List<String> list, Continuation<? super C07503> continuation) {
            super(2, continuation);
            this.$activity = activity;
            this.$updatedPlugins = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C07503 c07503 = new C07503(this.$activity, this.$updatedPlugins, continuation);
            c07503.L$0 = obj;
            return c07503;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(OnlinePluginData onlinePluginData, Continuation<? super Unit> continuation) {
            return ((C07503) create(onlinePluginData, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            OnlinePluginData onlinePluginData = (OnlinePluginData) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                if (onlinePluginData.getIsDisabled()) {
                    PluginManager.INSTANCE.unloadPlugin(onlinePluginData.getSavedData().getFilePath());
                } else if (onlinePluginData.getIsOutdated()) {
                    this.L$0 = onlinePluginData;
                    this.label = 1;
                    obj = PluginManager.INSTANCE.downloadPlugin(this.$activity, onlinePluginData.getOnlineData().getPlugin().getUrl(), onlinePluginData.getOnlineData().getPlugin().getFileHash(), onlinePluginData.getSavedData().getInternalName(), new File(onlinePluginData.getSavedData().getFilePath()), true, (Continuation<? super Boolean>) this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            }
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            List<String> list = this.$updatedPlugins;
            if (((Boolean) obj).booleanValue()) {
                list.add(onlinePluginData.getOnlineData().getPlugin().getName());
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.plugins.PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_updateAllOnlinePluginsAndLoadThem$4, reason: invalid class name */
    /* JADX INFO: compiled from: PluginManager.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/lagradost/cloudstream3/plugins/PluginManager;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.plugins.PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_updateAllOnlinePluginsAndLoadThem$4", f = "PluginManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass4 extends SuspendLambda implements Function2<PluginManager, Continuation<? super Unit>, Object> {
        final /* synthetic */ Activity $activity;
        final /* synthetic */ List<String> $updatedPlugins;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(List<String> list, Activity activity, Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
            this.$updatedPlugins = list;
            this.$activity = activity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass4(this.$updatedPlugins, this.$activity, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PluginManager pluginManager, Continuation<? super Unit> continuation) {
            return ((AnonymousClass4) create(pluginManager, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                PluginManager.INSTANCE.createNotification(this.$activity, TextUtilKt.txt(R.string.plugins_updated, Boxing.boxInt(this.$updatedPlugins.size())), this.$updatedPlugins);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: Code duplicated, block: B:115:0x00f7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:36:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:43:0x0108 A[Catch: Exception -> 0x011a, TRY_LEAVE, TryCatch #2 {Exception -> 0x011a, blocks: (B:19:0x0090, B:22:0x009c, B:27:0x00c1, B:34:0x00ec, B:37:0x00f3, B:43:0x0108, B:42:0x0103, B:33:0x00e2, B:26:0x00b7, B:30:0x00c8, B:23:0x009e, B:39:0x00f7), top: B:114:0x0090, inners: #0, #1, #4, #5 }] */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public final Object ___DO_NOT_CALL_FROM_A_PLUGIN_downloadNotExistingPluginsAndLoad(Activity activity, AutoDownloadMode autoDownloadMode, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        ArrayList arrayList;
        Object objDecodeFromString;
        Activity activity2;
        AutoDownloadMode autoDownloadMode2;
        RepositoryData[] repositoryDataArr;
        Object objM8936constructorimpl;
        KSerializer kSerializer;
        boolean z;
        List list;
        Activity activity3;
        RepositoryData[] repositoryDataArr2;
        ArrayList arrayList2;
        OnlinePluginData onlinePluginData;
        String language;
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
        boolean z2 = false;
        OnlinePluginData onlinePluginData2 = null;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            assertNonRecursiveCallstack();
            arrayList = new ArrayList();
            Context context = CloudStreamApp.INSTANCE.getContext();
            if (context != null) {
                try {
                    String string = DataStore.INSTANCE.getSharedPrefs(context).getString(ExtensionsViewModelKt.REPOSITORIES_KEY, null);
                    if (string == null) {
                        objDecodeFromString = null;
                    } else {
                        AppUtils appUtils = AppUtils.INSTANCE;
                        try {
                            Result.Companion companion = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(RepositoryData.class), RepositoryData.INSTANCE.serializer()));
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
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<RepositoryData[]>() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_downloadNotExistingPluginsAndLoad$$inlined$getKey$1
                                    });
                                } catch (Throwable unused) {
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<RepositoryData[]>() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_downloadNotExistingPluginsAndLoad$$inlined$getKey$1
                                    });
                                }
                            } else {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<RepositoryData[]>() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_downloadNotExistingPluginsAndLoad$$inlined$getKey$1
                                });
                            }
                        } else {
                            try {
                                Result.Companion companion3 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(RepositoryData[].class), null, 2, null));
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
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<RepositoryData[]>() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_downloadNotExistingPluginsAndLoad$$inlined$getKey$1
                                });
                            }
                        }
                    }
                } catch (Exception unused2) {
                    objDecodeFromString = null;
                }
            } else {
                objDecodeFromString = null;
            }
            RepositoryData[] repositoryDataArr3 = (RepositoryData[]) objDecodeFromString;
            if (repositoryDataArr3 == null) {
                repositoryDataArr3 = new RepositoryData[0];
            }
            RepositoryData[] repositoryDataArr4 = (RepositoryData[]) ArraysKt.plus((Object[]) repositoryDataArr3, (Object[]) RepositoryManager.INSTANCE.getPREBUILT_REPOSITORIES());
            List list2 = ArraysKt.toList(repositoryDataArr4);
            PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_downloadNotExistingPluginsAndLoad$onlinePlugins$1 pluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_downloadNotExistingPluginsAndLoad$onlinePlugins$1 = new PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_downloadNotExistingPluginsAndLoad$onlinePlugins$1(null);
            activity2 = activity;
            anonymousClass1.L$0 = activity2;
            autoDownloadMode2 = autoDownloadMode;
            anonymousClass1.L$1 = autoDownloadMode2;
            anonymousClass1.L$2 = arrayList;
            anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(repositoryDataArr4);
            anonymousClass1.label = 1;
            Object objAmap = ParCollectionsKt.amap(list2, pluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_downloadNotExistingPluginsAndLoad$onlinePlugins$1, anonymousClass1);
            if (objAmap != coroutine_suspended) {
                repositoryDataArr = repositoryDataArr4;
                obj = objAmap;
            }
            return coroutine_suspended;
        }
        if (i == 1) {
            RepositoryData[] repositoryDataArr5 = (RepositoryData[]) anonymousClass1.L$3;
            List list3 = (List) anonymousClass1.L$2;
            AutoDownloadMode autoDownloadMode3 = (AutoDownloadMode) anonymousClass1.L$1;
            Activity activity4 = (Activity) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj);
            repositoryDataArr = repositoryDataArr5;
            arrayList = list3;
            autoDownloadMode2 = autoDownloadMode3;
            activity2 = activity4;
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            list = (List) anonymousClass1.L$2;
            activity3 = (Activity) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj);
            z = false;
        }
        Coroutines.INSTANCE.main(this, new AnonymousClass3(list, activity3, null));
        MainActivity.INSTANCE.getAfterPluginsLoadedEvent().invoke(Boxing.boxBoolean(z));
        Log.i(TAG, "Plugin download done!");
        return Unit.INSTANCE;
        List listFlatten = CollectionsKt.flatten((Iterable) obj);
        HashSet hashSet = new HashSet();
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : listFlatten) {
            if (hashSet.add(((PluginWrapper) obj2).getPlugin().getUrl())) {
                arrayList3.add(obj2);
            }
        }
        ArrayList<PluginWrapper> arrayList4 = arrayList3;
        Activity activity5 = activity2;
        HashSet<String> apiProviderLangSettings = AppContextUtils.INSTANCE.getApiProviderLangSettings(activity5);
        ArrayList arrayList5 = new ArrayList();
        for (PluginWrapper pluginWrapper : arrayList4) {
            boolean z3 = z2;
            SitePlugin plugin = pluginWrapper.getPlugin();
            List<String> tvTypes = plugin.getTvTypes();
            if (tvTypes == null) {
                tvTypes = CollectionsKt.emptyList();
            }
            List<String> list4 = tvTypes;
            if (StringsKt.isBlank(plugin.getUrl())) {
                repositoryDataArr2 = repositoryDataArr;
                onlinePluginData = onlinePluginData2;
                arrayList2 = arrayList4;
            } else {
                String repositoryUrl = plugin.getRepositoryUrl();
                if (repositoryUrl == null || StringsKt.isBlank(repositoryUrl)) {
                    repositoryDataArr2 = repositoryDataArr;
                    arrayList2 = arrayList4;
                } else {
                    repositoryDataArr2 = repositoryDataArr;
                    arrayList2 = arrayList4;
                    if (INSTANCE.getPluginPath(activity5, plugin.getInternalName(), pluginWrapper.getRepositoryData().getUrl()).exists()) {
                        Log.i(TAG, "Skip > " + plugin.getInternalName());
                    } else if ((autoDownloadMode2 != AutoDownloadMode.NsfwOnly || list4.contains("NSFW")) && ((MainAPI.INSTANCE.getSettingsForProvider().getEnableAdult() || !list4.contains("NSFW")) && (autoDownloadMode2 != AutoDownloadMode.FilterByLang || ((language = plugin.getLanguage()) != null && (apiProviderLangSettings.contains(MainAPIKt.AllLanguagesName) || apiProviderLangSettings.contains(language)))))) {
                        onlinePluginData = new OnlinePluginData(new PluginData(plugin.getInternalName(), plugin.getUrl(), true, "", plugin.getVersion()), pluginWrapper);
                    }
                }
                onlinePluginData = null;
            }
            if (onlinePluginData != null) {
                arrayList5.add(onlinePluginData);
            }
            repositoryDataArr = repositoryDataArr2;
            arrayList4 = arrayList2;
            z2 = z3;
            onlinePluginData2 = null;
        }
        z = z2;
        ArrayList arrayList6 = arrayList5;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(activity2, arrayList, null);
        anonymousClass1.L$0 = activity2;
        anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(autoDownloadMode2);
        anonymousClass1.L$2 = arrayList;
        anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(repositoryDataArr);
        anonymousClass1.L$4 = SpillingKt.nullOutSpilledVariable(arrayList4);
        anonymousClass1.L$5 = SpillingKt.nullOutSpilledVariable(apiProviderLangSettings);
        anonymousClass1.L$6 = SpillingKt.nullOutSpilledVariable(arrayList6);
        anonymousClass1.label = 2;
        if (ParCollectionsKt.amap(arrayList6, anonymousClass2, anonymousClass1) != coroutine_suspended) {
            list = arrayList;
            activity3 = activity2;
            Coroutines.INSTANCE.main(this, new AnonymousClass3(list, activity3, null));
            MainActivity.INSTANCE.getAfterPluginsLoadedEvent().invoke(Boxing.boxBoolean(z));
            Log.i(TAG, "Plugin download done!");
            return Unit.INSTANCE;
        }
        return coroutine_suspended;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.plugins.PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_downloadNotExistingPluginsAndLoad$2, reason: invalid class name */
    /* JADX INFO: compiled from: PluginManager.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "pluginData", "Lcom/lagradost/cloudstream3/plugins/PluginManager$OnlinePluginData;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.plugins.PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_downloadNotExistingPluginsAndLoad$2", f = "PluginManager.kt", i = {0}, l = {422}, m = "invokeSuspend", n = {"pluginData"}, nl = {429}, s = {"L$0"}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<OnlinePluginData, Continuation<? super Unit>, Object> {
        final /* synthetic */ Activity $activity;
        final /* synthetic */ List<String> $newDownloadPlugins;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Activity activity, List<String> list, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$activity = activity;
            this.$newDownloadPlugins = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$activity, this.$newDownloadPlugins, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(OnlinePluginData onlinePluginData, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(onlinePluginData, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            OnlinePluginData onlinePluginData = (OnlinePluginData) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.L$0 = onlinePluginData;
                this.label = 1;
                obj = PluginManager.INSTANCE.downloadPlugin(this.$activity, onlinePluginData.getOnlineData().getPlugin().getUrl(), onlinePluginData.getOnlineData().getPlugin().getFileHash(), onlinePluginData.getSavedData().getInternalName(), onlinePluginData.getOnlineData().getRepositoryData().getUrl(), !onlinePluginData.getIsDisabled(), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            List<String> list = this.$newDownloadPlugins;
            if (((Boolean) obj).booleanValue()) {
                list.add(onlinePluginData.getOnlineData().getPlugin().getName());
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.plugins.PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_downloadNotExistingPluginsAndLoad$3, reason: invalid class name */
    /* JADX INFO: compiled from: PluginManager.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/lagradost/cloudstream3/plugins/PluginManager;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.plugins.PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_downloadNotExistingPluginsAndLoad$3", f = "PluginManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass3 extends SuspendLambda implements Function2<PluginManager, Continuation<? super Unit>, Object> {
        final /* synthetic */ Activity $activity;
        final /* synthetic */ List<String> $newDownloadPlugins;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(List<String> list, Activity activity, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$newDownloadPlugins = list;
            this.$activity = activity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass3(this.$newDownloadPlugins, this.$activity, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PluginManager pluginManager, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(pluginManager, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                PluginManager.INSTANCE.createNotification(this.$activity, TextUtilKt.txt(R.string.plugins_downloaded, Boxing.boxInt(this.$newDownloadPlugins.size())), this.$newDownloadPlugins);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    private final void assertNonRecursiveCallstack() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "getStackTrace(...)");
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (Intrinsics.areEqual(stackTraceElement.getMethodName(), "loadPlugin")) {
                throw new Error("You tried to call a function that will recursively call loadPlugin, this will cause crashes or memory leaks. Do not do this, there is better ways to implement the feature than reloading plugins. Are you sure you read the compile error or docs?");
            }
        }
    }

    public final Object ___DO_NOT_CALL_FROM_A_PLUGIN_loadAllOnlinePlugins(Context context, Continuation<? super Unit> continuation) {
        assertNonRecursiveCallstack();
        Object objAmap = ParCollectionsKt.amap(ArraysKt.toList(getPluginsOnline()), new C07462(context, null), continuation);
        return objAmap == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAmap : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.plugins.PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_loadAllOnlinePlugins$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PluginManager.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "pluginData", "Lcom/lagradost/cloudstream3/plugins/PluginData;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.plugins.PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_loadAllOnlinePlugins$2", f = "PluginManager.kt", i = {0}, l = {468}, m = "invokeSuspend", n = {"pluginData"}, nl = {472}, s = {"L$0"}, v = 2)
    static final class C07462 extends SuspendLambda implements Function2<PluginData, Continuation<? super Boolean>, Object> {
        final /* synthetic */ Context $context;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07462(Context context, Continuation<? super C07462> continuation) {
            super(2, continuation);
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C07462 c07462 = new C07462(this.$context, continuation);
            c07462.L$0 = obj;
            return c07462;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PluginData pluginData, Continuation<? super Boolean> continuation) {
            return ((C07462) create(pluginData, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            PluginData pluginData = (PluginData) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return obj;
            }
            ResultKt.throwOnFailure(obj);
            this.L$0 = SpillingKt.nullOutSpilledVariable(pluginData);
            this.label = 1;
            Object objLoadPlugin = PluginManager.INSTANCE.loadPlugin(this.$context, new File(pluginData.getFilePath()), pluginData, this);
            return objLoadPlugin == coroutine_suspended ? coroutine_suspended : objLoadPlugin;
        }
    }

    public final Object ___DO_NOT_CALL_FROM_A_PLUGIN_hotReloadAllLocalPlugins(FragmentActivity fragmentActivity, Continuation<? super Unit> continuation) {
        assertNonRecursiveCallstack();
        Log.d(TAG, "Reloading all local plugins!");
        if (fragmentActivity == null) {
            return Unit.INSTANCE;
        }
        for (PluginData pluginData : getPluginsLocal()) {
            INSTANCE.unloadPlugin(pluginData.getFilePath());
        }
        Object obj___DO_NOT_CALL_FROM_A_PLUGIN_loadAllLocalPlugins = ___DO_NOT_CALL_FROM_A_PLUGIN_loadAllLocalPlugins(fragmentActivity, true, continuation);
        return obj___DO_NOT_CALL_FROM_A_PLUGIN_loadAllLocalPlugins == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? obj___DO_NOT_CALL_FROM_A_PLUGIN_loadAllLocalPlugins : Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object ___DO_NOT_CALL_FROM_A_PLUGIN_loadAllLocalPlugins(Context context, boolean z, Continuation<? super Unit> continuation) {
        C07441 c07441;
        List listSortedWith;
        if (continuation instanceof C07441) {
            c07441 = (C07441) continuation;
            if ((c07441.label & Integer.MIN_VALUE) != 0) {
                c07441.label -= Integer.MIN_VALUE;
            } else {
                c07441 = new C07441(continuation);
            }
        } else {
            c07441 = new C07441(continuation);
        }
        Object objAmap = c07441.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07441.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objAmap);
            assertNonRecursiveCallstack();
            String str = LOCAL_PLUGINS_PATH;
            File file = new File(str);
            if (!file.exists() && !file.mkdirs()) {
                Log.w(TAG, "Failed to create local directories");
                loadedLocalPlugins = true;
                return Unit.INSTANCE;
            }
            File[] fileArrListFiles = file.listFiles();
            StringBuilder sb = new StringBuilder("Files in '");
            sb.append(str);
            sb.append("' folder: ");
            sb.append(fileArrListFiles != null ? Boxing.boxInt(fileArrListFiles.length) : null);
            Log.d(TAG, sb.toString());
            File file2 = new File(context.getExternalFilesDir(null), "plugins");
            if (!file2.exists()) {
                file2.mkdirs();
            }
            CloudStreamApp.INSTANCE.removeKey(PluginManagerKt.PLUGINS_KEY_LOCAL);
            if (fileArrListFiles != null && (listSortedWith = ArraysKt.sortedWith(fileArrListFiles, new Comparator() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_loadAllLocalPlugins$$inlined$sortedBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(((File) t).getName(), ((File) t2).getName());
                }
            })) != null) {
                C07453 c07453 = new C07453(file2, context, null);
                c07441.L$0 = SpillingKt.nullOutSpilledVariable(context);
                c07441.L$1 = SpillingKt.nullOutSpilledVariable(file);
                c07441.L$2 = SpillingKt.nullOutSpilledVariable(fileArrListFiles);
                c07441.L$3 = SpillingKt.nullOutSpilledVariable(file2);
                c07441.Z$0 = z;
                c07441.label = 1;
                objAmap = ParCollectionsKt.amap(listSortedWith, c07453, c07441);
                if (objAmap == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            loadedLocalPlugins = true;
            MainActivity.INSTANCE.getAfterPluginsLoadedEvent().invoke(Boxing.boxBoolean(z));
            return Unit.INSTANCE;
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        boolean z2 = c07441.Z$0;
        ResultKt.throwOnFailure(objAmap);
        z = z2;
        loadedLocalPlugins = true;
        MainActivity.INSTANCE.getAfterPluginsLoadedEvent().invoke(Boxing.boxBoolean(z));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.plugins.PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_loadAllLocalPlugins$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PluginManager.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n"}, d2 = {"<anonymous>", "", UtilsKt.SCHEME_FILE, "Ljava/io/File;", "kotlin.jvm.PlatformType"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.plugins.PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_loadAllLocalPlugins$3", f = "PluginManager.kt", i = {0, 0}, l = {559}, m = "invokeSuspend", n = {UtilsKt.SCHEME_FILE, "destinationFile"}, nl = {560}, s = {"L$0", "L$1"}, v = 2)
    static final class C07453 extends SuspendLambda implements Function2<File, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ File $pluginDirectory;
        /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07453(File file, Context context, Continuation<? super C07453> continuation) {
            super(2, continuation);
            this.$pluginDirectory = file;
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C07453 c07453 = new C07453(this.$pluginDirectory, this.$context, continuation);
            c07453.L$0 = obj;
            return c07453;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(File file, Continuation<? super Unit> continuation) {
            return ((C07453) create(file, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            File file = (File) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    File file2 = new File(this.$pluginDirectory, file.getName());
                    if (!file2.exists() || file2.length() != file.length() || file2.lastModified() != file.lastModified()) {
                        Intrinsics.checkNotNull(file);
                        FilesKt.copyTo$default(file, file2, true, 0, 4, null);
                        file2.setLastModified(file.lastModified());
                    }
                    this.L$0 = SpillingKt.nullOutSpilledVariable(file);
                    this.L$1 = SpillingKt.nullOutSpilledVariable(file2);
                    this.label = 1;
                    if (PluginManager.INSTANCE.maybeLoadPlugin(this.$context, file2, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
            } catch (Throwable th) {
                Log.e(PluginManager.TAG, "Failed to copy the file");
                ArchComponentExtKt.logError(th);
            }
            return Unit.INSTANCE;
        }
    }

    public final boolean isSafeMode() {
        return checkSafeModeFile() || MainActivity.INSTANCE.getLastError() != null;
    }

    public final boolean checkSafeModeFile() {
        Boolean bool = (Boolean) ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PluginManager.checkSafeModeFile$lambda$0();
            }
        });
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    static final Boolean checkSafeModeFile$lambda$0() {
        File file = new File(CLOUD_STREAM_FOLDER);
        if (!file.exists()) {
            return false;
        }
        File[] fileArrListFiles = file.listFiles(new FilenameFilter() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$$ExternalSyntheticLambda14
            @Override // java.io.FilenameFilter
            public final boolean accept(File file2, String str) {
                return PluginManager.checkSafeModeFile$lambda$0$0(file2, str);
            }
        });
        if (fileArrListFiles != null) {
            return Boolean.valueOf(ArraysKt.any(fileArrListFiles));
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean checkSafeModeFile$lambda$0$0(File file, String str) {
        return StringsKt.equals(str, "safe", true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 10491. Try increasing type updates limit count.
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:79)
        */
    public final java.lang.Object loadPlugin(android.content.Context r24, java.io.File r25, com.lagradost.cloudstream3.plugins.PluginData r26, kotlin.coroutines.Continuation<? super java.lang.Boolean> r27) {
        /*
            Method dump skipped, instruction units count: 1049
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.plugins.PluginManager.loadPlugin(android.content.Context, java.io.File, com.lagradost.cloudstream3.plugins.PluginData, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void unloadPlugin(String absolutePath) {
        Intrinsics.checkNotNullParameter(absolutePath, "absolutePath");
        Log.i(TAG, "Unloading plugin: " + absolutePath);
        final BasePlugin basePlugin = plugins.get(absolutePath);
        if (basePlugin == null) {
            Log.w(TAG, "Couldn't find plugin " + absolutePath);
            return;
        }
        try {
            basePlugin.beforeUnload();
        } catch (Throwable th) {
            Log.e(TAG, "Failed to run beforeUnload " + absolutePath + ": " + Log.getStackTraceString(th));
        }
        Iterator<MainAPI> it = APIHolder.INSTANCE.getApis().filter(new Function1() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(PluginManager.unloadPlugin$lambda$0(basePlugin, (MainAPI) obj));
            }
        }).iterator();
        while (it.hasNext()) {
            APIHolder.INSTANCE.removePluginMapping(it.next());
        }
        APIHolder.INSTANCE.getAllProviders().withLock(new Function0() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(CollectionsKt.removeAll((List) APIHolder.INSTANCE.getAllProviders(), new Function1() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(PluginManager.unloadPlugin$lambda$2$0(basePlugin, (MainAPI) obj));
                    }
                }));
            }
        });
        ExtractorApiKt.getExtractorApis().withLock(new Function0() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(CollectionsKt.removeAll((List) ExtractorApiKt.getExtractorApis(), new Function1() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$$ExternalSyntheticLambda17
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(PluginManager.unloadPlugin$lambda$3$0(basePlugin, (ExtractorApi) obj));
                    }
                }));
            }
        });
        VideoClickActionHolder.INSTANCE.getAllVideoClickActions().withLock(new Function0() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(CollectionsKt.removeAll((List) VideoClickActionHolder.INSTANCE.getAllVideoClickActions(), new Function1() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(PluginManager.unloadPlugin$lambda$4$0(basePlugin, (VideoClickAction) obj));
                    }
                }));
            }
        });
        Map<PathClassLoader, BasePlugin> map = classLoaders;
        synchronized (map) {
            Collection<BasePlugin> collectionValues = map.values();
            final Function1 function1 = new Function1() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(PluginManager.unloadPlugin$lambda$5$0(basePlugin, (BasePlugin) obj));
                }
            };
            j$.util.Collection.EL.removeIf(collectionValues, new Predicate() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$$ExternalSyntheticLambda8
                public /* synthetic */ Predicate and(Predicate predicate) {
                    return Predicate$CC.$default$and(this, predicate);
                }

                public /* synthetic */ Predicate negate() {
                    return Predicate$CC.$default$negate(this);
                }

                public /* synthetic */ Predicate or(Predicate predicate) {
                    return Predicate$CC.$default$or(this, predicate);
                }

                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return PluginManager.unloadPlugin$lambda$5$1(function1, obj);
                }
            });
        }
        Map<String, BasePlugin> map2 = plugins;
        synchronized (map2) {
            map2.remove(absolutePath);
        }
        Map<String, BasePlugin> map3 = urlPlugins;
        synchronized (map3) {
            Collection<BasePlugin> collectionValues2 = map3.values();
            final Function1 function2 = new Function1() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(PluginManager.unloadPlugin$lambda$7$0(basePlugin, (BasePlugin) obj));
                }
            };
            j$.util.Collection.EL.removeIf(collectionValues2, new Predicate() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$$ExternalSyntheticLambda10
                public /* synthetic */ Predicate and(Predicate predicate) {
                    return Predicate$CC.$default$and(this, predicate);
                }

                public /* synthetic */ Predicate negate() {
                    return Predicate$CC.$default$negate(this);
                }

                public /* synthetic */ Predicate or(Predicate predicate) {
                    return Predicate$CC.$default$or(this, predicate);
                }

                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return PluginManager.unloadPlugin$lambda$7$1(function2, obj);
                }
            });
        }
    }

    static final boolean unloadPlugin$lambda$0(BasePlugin basePlugin, MainAPI api) {
        Intrinsics.checkNotNullParameter(api, "api");
        return Intrinsics.areEqual(api.getSourcePlugin(), basePlugin.getFilename());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean unloadPlugin$lambda$2$0(BasePlugin basePlugin, MainAPI provider) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        return Intrinsics.areEqual(provider.getSourcePlugin(), basePlugin.getFilename());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean unloadPlugin$lambda$3$0(BasePlugin basePlugin, ExtractorApi provider) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        return Intrinsics.areEqual(provider.getSourcePlugin(), basePlugin.getFilename());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean unloadPlugin$lambda$4$0(BasePlugin basePlugin, VideoClickAction action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return Intrinsics.areEqual(action.getSourcePlugin(), basePlugin.getFilename());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean unloadPlugin$lambda$5$0(BasePlugin basePlugin, BasePlugin v) {
        Intrinsics.checkNotNullParameter(v, "v");
        return Intrinsics.areEqual(v, basePlugin);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean unloadPlugin$lambda$5$1(Function1 function1, Object obj) {
        return ((Boolean) function1.invoke(obj)).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean unloadPlugin$lambda$7$0(BasePlugin basePlugin, BasePlugin v) {
        Intrinsics.checkNotNullParameter(v, "v");
        return Intrinsics.areEqual(v, basePlugin);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean unloadPlugin$lambda$7$1(Function1 function1, Object obj) {
        return ((Boolean) function1.invoke(obj)).booleanValue();
    }

    public final String getPluginSanitizedFileName(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return DownloadFileManagement.INSTANCE.sanitizeFilename$app(name, true) + '.' + name.hashCode();
    }

    public final File getPluginPath(Context context, String internalName, String repositoryUrl) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(internalName, "internalName");
        Intrinsics.checkNotNullParameter(repositoryUrl, "repositoryUrl");
        return new File(context.getFilesDir() + "/Extensions/" + getPluginSanitizedFileName(repositoryUrl) + '/' + getPluginSanitizedFileName(internalName) + ".cs3");
    }

    public final Object downloadPlugin(Activity activity, String str, String str2, String str3, String str4, boolean z, Continuation<? super Boolean> continuation) {
        return downloadPlugin(activity, str, str2, str3, getPluginPath(activity, str3, str4), z, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0020  */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x019c, code lost:
    
        if (setPluginData(r1, r7) == r9) goto L45;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object downloadPlugin(android.app.Activity r19, java.lang.String r20, java.lang.String r21, java.lang.String r22, java.io.File r23, boolean r24, kotlin.coroutines.Continuation<? super java.lang.Boolean> r25) {
        /*
            Method dump skipped, instruction units count: 433
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.plugins.PluginManager.downloadPlugin(android.app.Activity, java.lang.String, java.lang.String, java.lang.String, java.io.File, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object deletePlugin(File file, Continuation<? super Boolean> continuation) {
        C07511 c07511;
        File file2;
        Iterator it;
        List list;
        Iterable iterable;
        int i;
        if (continuation instanceof C07511) {
            c07511 = (C07511) continuation;
            if ((c07511.label & Integer.MIN_VALUE) != 0) {
                c07511.label -= Integer.MIN_VALUE;
            } else {
                c07511 = new C07511(continuation);
            }
        } else {
            c07511 = new C07511(continuation);
        }
        Object obj = c07511.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c07511.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                Object[] objArrPlus = ArraysKt.plus((Object[]) getPluginsLocal(), (Object[]) getPluginsOnline());
                ArrayList arrayList = new ArrayList();
                for (Object obj2 : objArrPlus) {
                    if (Intrinsics.areEqual(((PluginData) obj2).getFilePath(), file.getAbsolutePath())) {
                        arrayList.add(obj2);
                    }
                }
                ArrayList arrayList2 = arrayList;
                if (new File(file.getAbsolutePath()).delete()) {
                    String absolutePath = file.getAbsolutePath();
                    Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
                    unloadPlugin(absolutePath);
                    ArrayList arrayList3 = arrayList2;
                    file2 = file;
                    it = arrayList3.iterator();
                    list = arrayList2;
                    iterable = arrayList3;
                    i = 0;
                }
                return Boxing.boxBoolean(false);
            }
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i3 = c07511.I$1;
            i = c07511.I$0;
            Object obj3 = c07511.L$4;
            it = (Iterator) c07511.L$3;
            iterable = (Iterable) c07511.L$2;
            list = (List) c07511.L$1;
            File file3 = (File) c07511.L$0;
            ResultKt.throwOnFailure(obj);
            file2 = file3;
            while (it.hasNext()) {
                Object next = it.next();
                PluginData pluginData = (PluginData) next;
                PluginManager pluginManager = INSTANCE;
                c07511.L$0 = SpillingKt.nullOutSpilledVariable(file2);
                c07511.L$1 = SpillingKt.nullOutSpilledVariable(list);
                c07511.L$2 = SpillingKt.nullOutSpilledVariable(iterable);
                c07511.L$3 = it;
                c07511.L$4 = SpillingKt.nullOutSpilledVariable(next);
                c07511.L$5 = SpillingKt.nullOutSpilledVariable(pluginData);
                c07511.I$0 = i;
                c07511.I$1 = 0;
                c07511.label = 1;
                if (pluginManager.deletePluginData(pluginData, c07511) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Boxing.boxBoolean(true);
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0118 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:104:0x019c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:106:0x0181 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:111:0x01e8 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:113:0x021f A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:115:0x0201 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:118:0x0268 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:120:0x0249 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:41:0x0113  */
    /* JADX WARN: Code duplicated, block: B:48:0x0129 A[Catch: Exception -> 0x013b, TRY_LEAVE, TryCatch #1 {Exception -> 0x013b, blocks: (B:24:0x00b1, B:27:0x00bd, B:32:0x00e2, B:39:0x010d, B:42:0x0114, B:48:0x0129, B:47:0x0124, B:38:0x0103, B:31:0x00d8, B:35:0x00e9, B:28:0x00bf, B:44:0x0118), top: B:98:0x00b1, inners: #0, #2, #4, #5 }] */
    /* JADX WARN: Code duplicated, block: B:60:0x0187  */
    /* JADX WARN: Code duplicated, block: B:65:0x01b1  */
    /* JADX WARN: Code duplicated, block: B:68:0x01c7  */
    /* JADX WARN: Code duplicated, block: B:70:0x01e5  */
    /* JADX WARN: Code duplicated, block: B:75:0x0207  */
    /* JADX WARN: Code duplicated, block: B:78:0x021c  */
    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    /* JADX WARN: Code duplicated, block: B:85:0x024f  */
    /* JADX WARN: Code duplicated, block: B:91:0x029c  */
    public final Object ___DO_NOT_CALL_FROM_A_PLUGIN_manuallyReloadAndUpdatePlugins(Activity activity, Continuation<? super Unit> continuation) {
        C07471 c07471;
        Activity activity2;
        Object objDecodeFromString;
        RepositoryData[] repositoryDataArr;
        Object objAmap;
        Object objM8936constructorimpl;
        KSerializer kSerializer;
        HashSet hashSet;
        ArrayList arrayList;
        ArrayList arrayList2;
        PluginData[] pluginsOnline;
        ArrayList arrayList3;
        int length;
        int i;
        boolean z;
        boolean z2;
        HashSet hashSet2;
        ArrayList arrayList4;
        ArrayList arrayList5;
        Object objAmap2;
        List list;
        Activity activity3;
        PluginData pluginData;
        ArrayList arrayList6;
        ArrayList arrayList7;
        Iterator it;
        OnlinePluginData onlinePluginData;
        Activity activity4 = activity;
        if (continuation instanceof C07471) {
            c07471 = (C07471) continuation;
            if ((c07471.label & Integer.MIN_VALUE) != 0) {
                c07471.label -= Integer.MIN_VALUE;
            } else {
                c07471 = new C07471(continuation);
            }
        } else {
            c07471 = new C07471(continuation);
        }
        Object obj = c07471.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c07471.label;
        boolean z3 = false;
        boolean z4 = true;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            assertNonRecursiveCallstack();
            CommonActivity.INSTANCE.showToast(activity4.getString(R.string.starting_plugin_update_manually), Boxing.boxInt(1));
            c07471.L$0 = activity4;
            c07471.label = 1;
            if (___DO_NOT_CALL_FROM_A_PLUGIN_loadAllOnlinePlugins(activity4, c07471) != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        if (i2 == 1) {
            activity4 = (Activity) c07471.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            if (i2 == 2) {
                repositoryDataArr = (RepositoryData[]) c07471.L$1;
                Activity activity5 = (Activity) c07471.L$0;
                ResultKt.throwOnFailure(obj);
                objAmap = obj;
                activity2 = activity5;
                List listFlatten = CollectionsKt.flatten((Iterable) objAmap);
                hashSet = new HashSet();
                arrayList = new ArrayList();
                for (Object obj2 : listFlatten) {
                    if (hashSet.add(((PluginWrapper) obj2).getPlugin().getUrl())) {
                        arrayList.add(obj2);
                    }
                }
                arrayList2 = arrayList;
                pluginsOnline = getPluginsOnline();
                arrayList3 = new ArrayList();
                length = pluginsOnline.length;
                i = 0;
                while (i < length) {
                    pluginData = pluginsOnline[i];
                    arrayList6 = new ArrayList();
                    for (Object obj3 : arrayList2) {
                        boolean z5 = z3;
                        boolean z6 = z4;
                        if (Intrinsics.areEqual(((PluginWrapper) obj3).getPlugin().getInternalName(), pluginData.getInternalName())) {
                            arrayList6.add(obj3);
                        }
                        z3 = z5;
                        z4 = z6;
                    }
                    boolean z7 = z3;
                    boolean z8 = z4;
                    arrayList7 = new ArrayList();
                    it = arrayList6.iterator();
                    while (it.hasNext()) {
                        onlinePluginData = new OnlinePluginData(pluginData, (PluginWrapper) it.next());
                        if (!onlinePluginData.validOnlineData(activity2)) {
                            onlinePluginData = null;
                        }
                        if (onlinePluginData != null) {
                            arrayList7.add(onlinePluginData);
                        }
                    }
                    CollectionsKt.addAll(arrayList3, arrayList7);
                    i++;
                    z3 = z7;
                    z4 = z8;
                }
                z = z3;
                z2 = z4;
                hashSet2 = new HashSet();
                arrayList4 = new ArrayList();
                for (Object obj4 : arrayList3) {
                    if (hashSet2.add(((OnlinePluginData) obj4).getOnlineData().getPlugin().getUrl())) {
                        arrayList4.add(obj4);
                    }
                }
                ArrayList arrayList8 = arrayList4;
                arrayList5 = new ArrayList();
                C07482 c07482 = new C07482(activity2, arrayList5, null);
                c07471.L$0 = activity2;
                c07471.L$1 = SpillingKt.nullOutSpilledVariable(repositoryDataArr);
                c07471.L$2 = SpillingKt.nullOutSpilledVariable(arrayList2);
                c07471.L$3 = SpillingKt.nullOutSpilledVariable(arrayList8);
                c07471.L$4 = arrayList5;
                c07471.label = 3;
                objAmap2 = ParCollectionsKt.amap(arrayList8, c07482, c07471);
                if (objAmap2 != coroutine_suspended) {
                    Activity activity6 = activity2;
                    obj = objAmap2;
                    list = arrayList5;
                    activity3 = activity6;
                }
                return coroutine_suspended;
            }
            if (i2 != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            list = (List) c07471.L$4;
            activity3 = (Activity) c07471.L$0;
            ResultKt.throwOnFailure(obj);
            z = false;
            z2 = true;
        }
        Coroutines.INSTANCE.main(INSTANCE, new PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_manuallyReloadAndUpdatePlugins$3$1(list, activity3, null));
        loadedOnlinePlugins = z2;
        MainActivity.INSTANCE.getAfterPluginsLoadedEvent().invoke(Boxing.boxBoolean(z));
        Log.i(TAG, "Plugin update done!");
        return Unit.INSTANCE;
        activity2 = activity4;
        MainActivity.INSTANCE.getAfterPluginsLoadedEvent().invoke(Boxing.boxBoolean(false));
        Context context = CloudStreamApp.INSTANCE.getContext();
        if (context != null) {
            try {
                String string = DataStore.INSTANCE.getSharedPrefs(context).getString(ExtensionsViewModelKt.REPOSITORIES_KEY, null);
                if (string == null) {
                    objDecodeFromString = null;
                } else {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(RepositoryData.class), RepositoryData.INSTANCE.serializer()));
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
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<RepositoryData[]>() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_manuallyReloadAndUpdatePlugins$$inlined$getKey$1
                                });
                            } catch (Throwable unused) {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<RepositoryData[]>() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_manuallyReloadAndUpdatePlugins$$inlined$getKey$1
                                });
                            }
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<RepositoryData[]>() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_manuallyReloadAndUpdatePlugins$$inlined$getKey$1
                            });
                        }
                    } else {
                        try {
                            Result.Companion companion3 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(RepositoryData[].class), null, 2, null));
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
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<RepositoryData[]>() { // from class: com.lagradost.cloudstream3.plugins.PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_manuallyReloadAndUpdatePlugins$$inlined$getKey$1
                            });
                        }
                    }
                }
            } catch (Exception unused2) {
                objDecodeFromString = null;
            }
        } else {
            objDecodeFromString = null;
        }
        RepositoryData[] repositoryDataArr2 = (RepositoryData[]) objDecodeFromString;
        if (repositoryDataArr2 == null) {
            repositoryDataArr2 = new RepositoryData[0];
        }
        repositoryDataArr = (RepositoryData[]) ArraysKt.plus((Object[]) repositoryDataArr2, (Object[]) RepositoryManager.INSTANCE.getPREBUILT_REPOSITORIES());
        List list2 = ArraysKt.toList(repositoryDataArr);
        PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_manuallyReloadAndUpdatePlugins$onlinePlugins$1 pluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_manuallyReloadAndUpdatePlugins$onlinePlugins$1 = new PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_manuallyReloadAndUpdatePlugins$onlinePlugins$1(null);
        c07471.L$0 = activity2;
        c07471.L$1 = SpillingKt.nullOutSpilledVariable(repositoryDataArr);
        c07471.label = 2;
        objAmap = ParCollectionsKt.amap(list2, pluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_manuallyReloadAndUpdatePlugins$onlinePlugins$1, c07471);
        if (objAmap != coroutine_suspended) {
            List listFlatten2 = CollectionsKt.flatten((Iterable) objAmap);
            hashSet = new HashSet();
            arrayList = new ArrayList();
            while (r5.hasNext()) {
                if (hashSet.add(((PluginWrapper) obj2).getPlugin().getUrl())) {
                    arrayList.add(obj2);
                }
            }
            arrayList2 = arrayList;
            pluginsOnline = getPluginsOnline();
            arrayList3 = new ArrayList();
            length = pluginsOnline.length;
            i = 0;
            while (i < length) {
                pluginData = pluginsOnline[i];
                arrayList6 = new ArrayList();
                while (r14.hasNext()) {
                    boolean z9 = z3;
                    boolean z10 = z4;
                    if (Intrinsics.areEqual(((PluginWrapper) obj3).getPlugin().getInternalName(), pluginData.getInternalName())) {
                        arrayList6.add(obj3);
                    }
                    z3 = z9;
                    z4 = z10;
                }
                boolean z11 = z3;
                boolean z12 = z4;
                arrayList7 = new ArrayList();
                it = arrayList6.iterator();
                while (it.hasNext()) {
                    onlinePluginData = new OnlinePluginData(pluginData, (PluginWrapper) it.next());
                    if (!onlinePluginData.validOnlineData(activity2)) {
                        onlinePluginData = null;
                    }
                    if (onlinePluginData != null) {
                        arrayList7.add(onlinePluginData);
                    }
                }
                CollectionsKt.addAll(arrayList3, arrayList7);
                i++;
                z3 = z11;
                z4 = z12;
            }
            z = z3;
            z2 = z4;
            hashSet2 = new HashSet();
            arrayList4 = new ArrayList();
            while (r5.hasNext()) {
                if (hashSet2.add(((OnlinePluginData) obj4).getOnlineData().getPlugin().getUrl())) {
                    arrayList4.add(obj4);
                }
            }
            ArrayList arrayList9 = arrayList4;
            arrayList5 = new ArrayList();
            C07482 c07483 = new C07482(activity2, arrayList5, null);
            c07471.L$0 = activity2;
            c07471.L$1 = SpillingKt.nullOutSpilledVariable(repositoryDataArr);
            c07471.L$2 = SpillingKt.nullOutSpilledVariable(arrayList2);
            c07471.L$3 = SpillingKt.nullOutSpilledVariable(arrayList9);
            c07471.L$4 = arrayList5;
            c07471.label = 3;
            objAmap2 = ParCollectionsKt.amap(arrayList9, c07483, c07471);
            if (objAmap2 != coroutine_suspended) {
                Activity activity7 = activity2;
                obj = objAmap2;
                list = arrayList5;
                activity3 = activity7;
                Coroutines.INSTANCE.main(INSTANCE, new PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_manuallyReloadAndUpdatePlugins$3$1(list, activity3, null));
                loadedOnlinePlugins = z2;
                MainActivity.INSTANCE.getAfterPluginsLoadedEvent().invoke(Boxing.boxBoolean(z));
                Log.i(TAG, "Plugin update done!");
                return Unit.INSTANCE;
            }
        }
        return coroutine_suspended;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.plugins.PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_manuallyReloadAndUpdatePlugins$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PluginManager.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "pluginData", "Lcom/lagradost/cloudstream3/plugins/PluginManager$OnlinePluginData;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.plugins.PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_manuallyReloadAndUpdatePlugins$2", f = "PluginManager.kt", i = {0, 0}, l = {865}, m = "invokeSuspend", n = {"pluginData", "existingFile"}, nl = {874}, s = {"L$0", "L$1"}, v = 2)
    static final class C07482 extends SuspendLambda implements Function2<OnlinePluginData, Continuation<? super Unit>, Object> {
        final /* synthetic */ Activity $activity;
        final /* synthetic */ List<String> $updatedPlugins;
        /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07482(Activity activity, List<String> list, Continuation<? super C07482> continuation) {
            super(2, continuation);
            this.$activity = activity;
            this.$updatedPlugins = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C07482 c07482 = new C07482(this.$activity, this.$updatedPlugins, continuation);
            c07482.L$0 = obj;
            return c07482;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(OnlinePluginData onlinePluginData, Continuation<? super Unit> continuation) {
            return ((C07482) create(onlinePluginData, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            OnlinePluginData onlinePluginData = (OnlinePluginData) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                if (onlinePluginData.getIsDisabled()) {
                    Log.e(PluginManager.TAG, "Unloading disabled plugin: " + onlinePluginData.getOnlineData().getPlugin().getName());
                    PluginManager.INSTANCE.unloadPlugin(onlinePluginData.getSavedData().getFilePath());
                } else {
                    File file = new File(onlinePluginData.getSavedData().getFilePath());
                    if (file.exists()) {
                        file.delete();
                    }
                    this.L$0 = onlinePluginData;
                    this.L$1 = SpillingKt.nullOutSpilledVariable(file);
                    this.label = 1;
                    obj = PluginManager.INSTANCE.downloadPlugin(this.$activity, onlinePluginData.getOnlineData().getPlugin().getUrl(), onlinePluginData.getOnlineData().getPlugin().getFileHash(), onlinePluginData.getSavedData().getInternalName(), file, true, (Continuation<? super Boolean>) this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            }
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            if (((Boolean) obj).booleanValue()) {
                this.$updatedPlugins.add(onlinePluginData.getOnlineData().getPlugin().getName());
            }
            return Unit.INSTANCE;
        }
    }

    private final void createNotificationChannel(Context context) {
        hasCreatedNotChanel = true;
        if (Build.VERSION.SDK_INT >= 26) {
            CommonActivity$$ExternalSyntheticApiModelOutline0.m$1();
            NotificationChannel notificationChannelM = CommonActivity$$ExternalSyntheticApiModelOutline0.m(PluginManagerKt.EXTENSIONS_CHANNEL_ID, "Extensions", 2);
            notificationChannelM.setDescription(PluginManagerKt.EXTENSIONS_CHANNEL_DESCRIPT);
            Object systemService = context.getSystemService("notification");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
            ((NotificationManager) systemService).createNotificationChannel(notificationChannelM);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Notification createNotification(Context context, UiText uitext, List<String> extensions) {
        try {
            if (extensions.isEmpty()) {
                return null;
            }
            String strJoinToString$default = CollectionsKt.joinToString$default(extensions, ", ", null, null, 0, null, null, 62, null);
            NotificationCompat.Builder contentText = new NotificationCompat.Builder(context, PluginManagerKt.EXTENSIONS_CHANNEL_ID).setAutoCancel(false).setColorized(true).setOnlyAlertOnce(true).setSilent(true).setPriority(-1).setColor(UIHelper.INSTANCE.colorFromAttribute(context, R.attr.colorPrimary)).setContentTitle(uitext.asString(context)).setSmallIcon(R.drawable.ic_baseline_extension_24).setStyle(new NotificationCompat.BigTextStyle().bigText(strJoinToString$default)).setContentText(strJoinToString$default);
            Intrinsics.checkNotNullExpressionValue(contentText, "setContentText(...)");
            if (!hasCreatedNotChanel) {
                createNotificationChannel(context);
            }
            Notification notificationBuild = contentText.build();
            Intrinsics.checkNotNullExpressionValue(notificationBuild, "build(...)");
            if (ActivityCompat.checkSelfPermission(context, "android.permission.POST_NOTIFICATIONS") == 0) {
                NotificationManagerCompat.from(context).notify((int) (System.currentTimeMillis() / 1000), notificationBuild);
            }
            return notificationBuild;
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
            return null;
        }
    }
}
