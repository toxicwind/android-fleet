package com.lagradost.cloudstream3.utils;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.PreferenceManager;
import coil3.network.internal.UtilsKt;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.plugins.PluginManagerKt;
import com.lagradost.cloudstream3.syncproviders.AccountManager;
import com.lagradost.cloudstream3.syncproviders.SyncRepo;
import com.lagradost.cloudstream3.syncproviders.providers.AniListApi;
import com.lagradost.cloudstream3.syncproviders.providers.KitsuApi;
import com.lagradost.cloudstream3.syncproviders.providers.MALApi;
import com.lagradost.cloudstream3.utils.downloader.DownloadObjects;
import com.lagradost.cloudstream3.utils.downloader.DownloadQueueManager;
import com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager;
import com.lagradost.safefile.MediaFileContentType;
import com.lagradost.safefile.SafeFile;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.FloatSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.LinkedHashSetSerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import okhttp3.internal._UtilCommonKt;

/* JADX INFO: compiled from: BackupUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002)*B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0007\u001a\u00020\b*\u00020\u0006H\u0002J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J*\u0010\u0010\u001a\u00020\u00112\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\bH\u0007J\u0010\u0010\u0015\u001a\u00020\u00162\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\"\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u00062\b\b\u0002\u0010\u001a\u001a\u00020\u0006H\u0002J\n\u0010\u001b\u001a\u00020\u0011*\u00020\u001cJ\n\u0010\u001d\u001a\u00020\u0011*\u00020\u001cJ2\u0010\u001e\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u001f*\u00020\u000f2\u0014\u0010 \u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\u001f\u0018\u00010!2\b\b\u0002\u0010\"\u001a\u00020\bH\u0002J\u0010\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010\u000e\u001a\u00020\u000fJ\u001e\u0010%\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010$\u0012\u0006\u0012\u0004\u0018\u00010\u00060&2\u0006\u0010\u000e\u001a\u00020\u000fJ\u001c\u0010'\u001a\u0004\u0018\u00010$2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010(\u001a\u0004\u0018\u00010\u0006H\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u000b\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/lagradost/cloudstream3/utils/BackupUtils;", "", "<init>", "()V", "nonTransferableKeys", "", "", "isTransferable", "", "restoreFileSelector", "Landroidx/activity/result/ActivityResultLauncher;", "", "getBackup", "Lcom/lagradost/cloudstream3/utils/BackupUtils$BackupFile;", "context", "Landroid/content/Context;", "restore", "", "backupFile", "restoreSettings", "restoreDataStore", "backup", "Lkotlinx/coroutines/Job;", "setupBackupStream", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$StreamData;", "name", "ext", "setUpBackup", "Landroidx/fragment/app/FragmentActivity;", "restorePrompt", "restoreMap", ExifInterface.GPS_DIRECTION_TRUE, "map", "", "isEditingAppSettings", "getDefaultBackupDir", "Lcom/lagradost/safefile/SafeFile;", "getCurrentBackupDir", "Lkotlin/Pair;", "baseBackupPathToFile", "path", "BackupVars", "BackupFile", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class BackupUtils {
    public static final BackupUtils INSTANCE = new BackupUtils();
    private static final List<String> nonTransferableKeys = CollectionsKt.listOf((Object[]) new String[]{AniListApi.ANILIST_CACHED_LIST, MALApi.MAL_CACHED_LIST, KitsuApi.KITSU_CACHED_LIST, PluginManagerKt.PLUGINS_KEY, PluginManagerKt.PLUGINS_KEY_LOCAL, AccountManager.ACCOUNT_TOKEN, AccountManager.ACCOUNT_IDS, "biometric_key", "nginx_user", "download_path_key", "download_path_key_visual", "backup_path_key", "backup_dir_path_key", "anilist_token", "anilist_user", "mal_user", "mal_token", "mal_refresh_token", "mal_unixtime", "open_subtitles_user", "subdl_user", "simkl_token", DataStoreKt.DOWNLOAD_EPISODE_CACHE_BACKUP, DataStoreKt.DOWNLOAD_EPISODE_CACHE, VideoDownloadManager.KEY_DOWNLOAD_INFO, VideoDownloadManager.KEY_RESUME_IN_QUEUE, VideoDownloadManager.KEY_RESUME_PACKAGES, DownloadQueueManager.QUEUE_KEY, "auto_download_plugins_key2"});
    private static ActivityResultLauncher<String[]> restoreFileSelector;

    private BackupUtils() {
    }

    private final boolean isTransferable(String str) {
        List<String> list = nonTransferableKeys;
        boolean z = false;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (StringsKt.contains$default((CharSequence) str, (CharSequence) it.next(), false, 2, (Object) null)) {
                    z = true;
                    break;
                }
            }
        }
        return !z;
    }

    /* JADX INFO: compiled from: BackupUtils.kt */
    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 72\u00020\u0001:\u000267B\u009f\u0001\u0012\u0016\b\u0001\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003\u0012\u0016\b\u0001\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0003\u0012\u0016\b\u0001\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0016\b\u0001\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\n\u0018\u00010\u0003\u0012\u0016\b\u0001\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\f\u0018\u00010\u0003\u0012\u001e\b\u0001\u0010\r\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000e\u0018\u00010\u0003¢\u0006\u0004\b\u000f\u0010\u0010B§\u0001\b\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0007\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0003\u0012\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\n\u0018\u00010\u0003\u0012\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\f\u0018\u00010\u0003\u0012\u001c\u0010\r\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000e\u0018\u00010\u0003\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u000f\u0010\u0014J\u0017\u0010#\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003HÆ\u0003J\u0017\u0010$\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0003HÆ\u0003J\u0017\u0010%\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0017\u0010&\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\n\u0018\u00010\u0003HÆ\u0003J\u0017\u0010'\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\f\u0018\u00010\u0003HÆ\u0003J\u001f\u0010(\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000e\u0018\u00010\u0003HÆ\u0003J¡\u0001\u0010)\u001a\u00020\u00002\u0016\b\u0003\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00032\u0016\b\u0003\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00032\u0016\b\u0003\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0016\b\u0003\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\n\u0018\u00010\u00032\u0016\b\u0003\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\f\u0018\u00010\u00032\u001e\b\u0003\u0010\r\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000e\u0018\u00010\u0003HÆ\u0001J\u0014\u0010*\u001a\u00020\u00052\b\u0010+\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010,\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010-\u001a\u00020\u0004HÖ\u0081\u0004J%\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\u00002\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u000204H\u0001¢\u0006\u0002\b5R*\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R*\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u0016\u001a\u0004\b\u001a\u0010\u0018R*\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u0016\u001a\u0004\b\u001c\u0010\u0018R*\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\n\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u0016\u001a\u0004\b\u001e\u0010\u0018R*\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\f\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001f\u0010\u0016\u001a\u0004\b \u0010\u0018R2\u0010\r\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000e\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b!\u0010\u0016\u001a\u0004\b\"\u0010\u0018¨\u00068"}, d2 = {"Lcom/lagradost/cloudstream3/utils/BackupUtils$BackupVars;", "", "bool", "", "", "", "int", "", TypedValues.Custom.S_STRING, TypedValues.Custom.S_FLOAT, "", "long", "", "stringSet", "", "<init>", "(Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getBool$annotations", "()V", "getBool", "()Ljava/util/Map;", "getInt$annotations", "getInt", "getString$annotations", "getString", "getFloat$annotations", "getFloat", "getLong$annotations", "getLong", "getStringSet$annotations", "getStringSet", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class BackupVars {

        @JsonProperty("_Bool")
        private final Map<String, Boolean> bool;

        @JsonProperty("_Float")
        private final Map<String, Float> float;

        @JsonProperty("_Int")
        private final Map<String, Integer> int;

        @JsonProperty("_Long")
        private final Map<String, Long> long;

        @JsonProperty("_String")
        private final Map<String, String> string;

        @JsonProperty("_StringSet")
        private final Map<String, Set<String>> stringSet;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.utils.BackupUtils$BackupVars$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return BackupUtils.BackupVars._childSerializers$_anonymous_();
            }
        }), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.utils.BackupUtils$BackupVars$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return BackupUtils.BackupVars._childSerializers$_anonymous_$0();
            }
        }), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.utils.BackupUtils$BackupVars$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return BackupUtils.BackupVars._childSerializers$_anonymous_$1();
            }
        }), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.utils.BackupUtils$BackupVars$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return BackupUtils.BackupVars._childSerializers$_anonymous_$2();
            }
        }), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.utils.BackupUtils$BackupVars$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return BackupUtils.BackupVars._childSerializers$_anonymous_$3();
            }
        }), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.utils.BackupUtils$BackupVars$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return BackupUtils.BackupVars._childSerializers$_anonymous_$4();
            }
        })};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new LinkedHashMapSerializer(StringSerializer.INSTANCE, BooleanSerializer.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$0() {
            return new LinkedHashMapSerializer(StringSerializer.INSTANCE, IntSerializer.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$1() {
            return new LinkedHashMapSerializer(StringSerializer.INSTANCE, StringSerializer.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$2() {
            return new LinkedHashMapSerializer(StringSerializer.INSTANCE, FloatSerializer.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$3() {
            return new LinkedHashMapSerializer(StringSerializer.INSTANCE, LongSerializer.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$4() {
            return new LinkedHashMapSerializer(StringSerializer.INSTANCE, BuiltinSerializersKt.getNullable(new LinkedHashSetSerializer(StringSerializer.INSTANCE)));
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ BackupVars copy$default(BackupVars backupVars, Map map, Map map2, Map map3, Map map4, Map map5, Map map6, int i, Object obj) {
            if ((i & 1) != 0) {
                map = backupVars.bool;
            }
            if ((i & 2) != 0) {
                map2 = backupVars.int;
            }
            if ((i & 4) != 0) {
                map3 = backupVars.string;
            }
            if ((i & 8) != 0) {
                map4 = backupVars.float;
            }
            if ((i & 16) != 0) {
                map5 = backupVars.long;
            }
            if ((i & 32) != 0) {
                map6 = backupVars.stringSet;
            }
            Map map7 = map5;
            Map map8 = map6;
            return backupVars.copy(map, map2, map3, map4, map7, map8);
        }

        @SerialName("_Bool")
        public static /* synthetic */ void getBool$annotations() {
        }

        @SerialName("_Float")
        public static /* synthetic */ void getFloat$annotations() {
        }

        @SerialName("_Int")
        public static /* synthetic */ void getInt$annotations() {
        }

        @SerialName("_Long")
        public static /* synthetic */ void getLong$annotations() {
        }

        @SerialName("_String")
        public static /* synthetic */ void getString$annotations() {
        }

        @SerialName("_StringSet")
        public static /* synthetic */ void getStringSet$annotations() {
        }

        public final Map<String, Boolean> component1() {
            return this.bool;
        }

        public final Map<String, Integer> component2() {
            return this.int;
        }

        public final Map<String, String> component3() {
            return this.string;
        }

        public final Map<String, Float> component4() {
            return this.float;
        }

        public final Map<String, Long> component5() {
            return this.long;
        }

        public final Map<String, Set<String>> component6() {
            return this.stringSet;
        }

        public final BackupVars copy(@JsonProperty("_Bool") Map<String, Boolean> bool, @JsonProperty("_Int") Map<String, Integer> map, @JsonProperty("_String") Map<String, String> string, @JsonProperty("_Float") Map<String, Float> map2, @JsonProperty("_Long") Map<String, Long> map3, @JsonProperty("_StringSet") Map<String, ? extends Set<String>> stringSet) {
            return new BackupVars(bool, map, string, map2, map3, stringSet);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BackupVars)) {
                return false;
            }
            BackupVars backupVars = (BackupVars) other;
            return Intrinsics.areEqual(this.bool, backupVars.bool) && Intrinsics.areEqual(this.int, backupVars.int) && Intrinsics.areEqual(this.string, backupVars.string) && Intrinsics.areEqual(this.float, backupVars.float) && Intrinsics.areEqual(this.long, backupVars.long) && Intrinsics.areEqual(this.stringSet, backupVars.stringSet);
        }

        public int hashCode() {
            Map<String, Boolean> map = this.bool;
            int iHashCode = (map == null ? 0 : map.hashCode()) * 31;
            Map<String, Integer> map2 = this.int;
            int iHashCode2 = (iHashCode + (map2 == null ? 0 : map2.hashCode())) * 31;
            Map<String, String> map3 = this.string;
            int iHashCode3 = (iHashCode2 + (map3 == null ? 0 : map3.hashCode())) * 31;
            Map<String, Float> map4 = this.float;
            int iHashCode4 = (iHashCode3 + (map4 == null ? 0 : map4.hashCode())) * 31;
            Map<String, Long> map5 = this.long;
            int iHashCode5 = (iHashCode4 + (map5 == null ? 0 : map5.hashCode())) * 31;
            Map<String, Set<String>> map6 = this.stringSet;
            return iHashCode5 + (map6 != null ? map6.hashCode() : 0);
        }

        public String toString() {
            return "BackupVars(bool=" + this.bool + ", int=" + this.int + ", string=" + this.string + ", float=" + this.float + ", long=" + this.long + ", stringSet=" + this.stringSet + ')';
        }

        /* JADX INFO: compiled from: BackupUtils.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/BackupUtils$BackupVars$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/BackupUtils$BackupVars;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<BackupVars> serializer() {
                return BackupUtils$BackupVars$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ BackupVars(int i, Map map, Map map2, Map map3, Map map4, Map map5, Map map6, SerializationConstructorMarker serializationConstructorMarker) {
            if (63 != (i & 63)) {
                PluginExceptionsKt.throwMissingFieldException(i, 63, BackupUtils$BackupVars$$serializer.INSTANCE.getDescriptor());
            }
            this.bool = map;
            this.int = map2;
            this.string = map3;
            this.float = map4;
            this.long = map5;
            this.stringSet = map6;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public BackupVars(@JsonProperty("_Bool") Map<String, Boolean> map, @JsonProperty("_Int") Map<String, Integer> map2, @JsonProperty("_String") Map<String, String> map3, @JsonProperty("_Float") Map<String, Float> map4, @JsonProperty("_Long") Map<String, Long> map5, @JsonProperty("_StringSet") Map<String, ? extends Set<String>> map6) {
            this.bool = map;
            this.int = map2;
            this.string = map3;
            this.float = map4;
            this.long = map5;
            this.stringSet = map6;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(BackupVars self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            output.encodeNullableSerializableElement(serialDesc, 0, lazyArr[0].getValue(), self.bool);
            output.encodeNullableSerializableElement(serialDesc, 1, lazyArr[1].getValue(), self.int);
            output.encodeNullableSerializableElement(serialDesc, 2, lazyArr[2].getValue(), self.string);
            output.encodeNullableSerializableElement(serialDesc, 3, lazyArr[3].getValue(), self.float);
            output.encodeNullableSerializableElement(serialDesc, 4, lazyArr[4].getValue(), self.long);
            output.encodeNullableSerializableElement(serialDesc, 5, lazyArr[5].getValue(), self.stringSet);
        }

        public final Map<String, Boolean> getBool() {
            return this.bool;
        }

        public final Map<String, Integer> getInt() {
            return this.int;
        }

        public final Map<String, String> getString() {
            return this.string;
        }

        public final Map<String, Float> getFloat() {
            return this.float;
        }

        public final Map<String, Long> getLong() {
            return this.long;
        }

        public final Map<String, Set<String>> getStringSet() {
            return this.stringSet;
        }
    }

    /* JADX INFO: compiled from: BackupUtils.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 $2\u00020\u0001:\u0002#$B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B/\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0005\u0010\u000bJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u0014\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004J%\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0001¢\u0006\u0002\b\"R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u000f¨\u0006%"}, d2 = {"Lcom/lagradost/cloudstream3/utils/BackupUtils$BackupFile;", "", "datastore", "Lcom/lagradost/cloudstream3/utils/BackupUtils$BackupVars;", "settings", "<init>", "(Lcom/lagradost/cloudstream3/utils/BackupUtils$BackupVars;Lcom/lagradost/cloudstream3/utils/BackupUtils$BackupVars;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/utils/BackupUtils$BackupVars;Lcom/lagradost/cloudstream3/utils/BackupUtils$BackupVars;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getDatastore$annotations", "()V", "getDatastore", "()Lcom/lagradost/cloudstream3/utils/BackupUtils$BackupVars;", "getSettings$annotations", "getSettings", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class BackupFile {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("datastore")
        private final BackupVars datastore;

        @JsonProperty("settings")
        private final BackupVars settings;

        public static /* synthetic */ BackupFile copy$default(BackupFile backupFile, BackupVars backupVars, BackupVars backupVars2, int i, Object obj) {
            if ((i & 1) != 0) {
                backupVars = backupFile.datastore;
            }
            if ((i & 2) != 0) {
                backupVars2 = backupFile.settings;
            }
            return backupFile.copy(backupVars, backupVars2);
        }

        @SerialName("datastore")
        public static /* synthetic */ void getDatastore$annotations() {
        }

        @SerialName("settings")
        public static /* synthetic */ void getSettings$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final BackupVars getDatastore() {
            return this.datastore;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final BackupVars getSettings() {
            return this.settings;
        }

        public final BackupFile copy(@JsonProperty("datastore") BackupVars datastore, @JsonProperty("settings") BackupVars settings) {
            Intrinsics.checkNotNullParameter(datastore, "datastore");
            Intrinsics.checkNotNullParameter(settings, "settings");
            return new BackupFile(datastore, settings);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BackupFile)) {
                return false;
            }
            BackupFile backupFile = (BackupFile) other;
            return Intrinsics.areEqual(this.datastore, backupFile.datastore) && Intrinsics.areEqual(this.settings, backupFile.settings);
        }

        public int hashCode() {
            return (this.datastore.hashCode() * 31) + this.settings.hashCode();
        }

        public String toString() {
            return "BackupFile(datastore=" + this.datastore + ", settings=" + this.settings + ')';
        }

        /* JADX INFO: compiled from: BackupUtils.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/BackupUtils$BackupFile$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/BackupUtils$BackupFile;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<BackupFile> serializer() {
                return BackupUtils$BackupFile$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ BackupFile(int i, BackupVars backupVars, BackupVars backupVars2, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, BackupUtils$BackupFile$$serializer.INSTANCE.getDescriptor());
            }
            this.datastore = backupVars;
            this.settings = backupVars2;
        }

        public BackupFile(@JsonProperty("datastore") BackupVars datastore, @JsonProperty("settings") BackupVars settings) {
            Intrinsics.checkNotNullParameter(datastore, "datastore");
            Intrinsics.checkNotNullParameter(settings, "settings");
            this.datastore = datastore;
            this.settings = settings;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(BackupFile self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeSerializableElement(serialDesc, 0, BackupUtils$BackupVars$$serializer.INSTANCE, self.datastore);
            output.encodeSerializableElement(serialDesc, 1, BackupUtils$BackupVars$$serializer.INSTANCE, self.settings);
        }

        public final BackupVars getDatastore() {
            return this.datastore;
        }

        public final BackupVars getSettings() {
            return this.settings;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BackupFile getBackup(Context context) {
        Map<String, ?> all = DataStore.INSTANCE.getSharedPrefs(context).getAll();
        Intrinsics.checkNotNullExpressionValue(all, "getAll(...)");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, ?> entry : all.entrySet()) {
            BackupUtils backupUtils = INSTANCE;
            String key = entry.getKey();
            Intrinsics.checkNotNullExpressionValue(key, "<get-key>(...)");
            if (backupUtils.isTransferable(key)) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        Map<String, ?> all2 = DataStore.INSTANCE.getDefaultSharedPrefs(context).getAll();
        Intrinsics.checkNotNullExpressionValue(all2, "getAll(...)");
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Map.Entry<String, ?> entry2 : all2.entrySet()) {
            BackupUtils backupUtils2 = INSTANCE;
            String key2 = entry2.getKey();
            Intrinsics.checkNotNullExpressionValue(key2, "<get-key>(...)");
            if (backupUtils2.isTransferable(key2)) {
                linkedHashMap2.put(entry2.getKey(), entry2.getValue());
            }
        }
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        for (Map.Entry entry3 : linkedHashMap.entrySet()) {
            if (entry3.getValue() instanceof Boolean) {
                linkedHashMap3.put(entry3.getKey(), entry3.getValue());
            }
        }
        LinkedHashMap linkedHashMap4 = linkedHashMap3 instanceof Map ? linkedHashMap3 : null;
        LinkedHashMap linkedHashMap5 = new LinkedHashMap();
        for (Map.Entry entry4 : linkedHashMap.entrySet()) {
            if (entry4.getValue() instanceof Integer) {
                linkedHashMap5.put(entry4.getKey(), entry4.getValue());
            }
        }
        LinkedHashMap linkedHashMap6 = linkedHashMap5 instanceof Map ? linkedHashMap5 : null;
        LinkedHashMap linkedHashMap7 = new LinkedHashMap();
        for (Map.Entry entry5 : linkedHashMap.entrySet()) {
            if (entry5.getValue() instanceof String) {
                linkedHashMap7.put(entry5.getKey(), entry5.getValue());
            }
        }
        LinkedHashMap linkedHashMap8 = linkedHashMap7 instanceof Map ? linkedHashMap7 : null;
        LinkedHashMap linkedHashMap9 = new LinkedHashMap();
        for (Map.Entry entry6 : linkedHashMap.entrySet()) {
            if (entry6.getValue() instanceof Float) {
                linkedHashMap9.put(entry6.getKey(), entry6.getValue());
            }
        }
        LinkedHashMap linkedHashMap10 = linkedHashMap9 instanceof Map ? linkedHashMap9 : null;
        LinkedHashMap linkedHashMap11 = new LinkedHashMap();
        for (Map.Entry entry7 : linkedHashMap.entrySet()) {
            if (entry7.getValue() instanceof Long) {
                linkedHashMap11.put(entry7.getKey(), entry7.getValue());
            }
        }
        LinkedHashMap linkedHashMap12 = linkedHashMap11 instanceof Map ? linkedHashMap11 : null;
        LinkedHashMap linkedHashMap13 = new LinkedHashMap();
        for (Map.Entry entry8 : linkedHashMap.entrySet()) {
            Object value = entry8.getValue();
            if ((value instanceof Set ? (Set) value : null) != null) {
                linkedHashMap13.put(entry8.getKey(), entry8.getValue());
            }
        }
        BackupVars backupVars = new BackupVars(linkedHashMap4, linkedHashMap6, linkedHashMap8, linkedHashMap10, linkedHashMap12, linkedHashMap13 instanceof Map ? linkedHashMap13 : null);
        LinkedHashMap linkedHashMap14 = new LinkedHashMap();
        for (Map.Entry entry9 : linkedHashMap2.entrySet()) {
            if (entry9.getValue() instanceof Boolean) {
                linkedHashMap14.put(entry9.getKey(), entry9.getValue());
            }
        }
        LinkedHashMap linkedHashMap15 = linkedHashMap14 instanceof Map ? linkedHashMap14 : null;
        LinkedHashMap linkedHashMap16 = new LinkedHashMap();
        for (Map.Entry entry10 : linkedHashMap2.entrySet()) {
            if (entry10.getValue() instanceof Integer) {
                linkedHashMap16.put(entry10.getKey(), entry10.getValue());
            }
        }
        LinkedHashMap linkedHashMap17 = linkedHashMap16 instanceof Map ? linkedHashMap16 : null;
        LinkedHashMap linkedHashMap18 = new LinkedHashMap();
        for (Map.Entry entry11 : linkedHashMap2.entrySet()) {
            if (entry11.getValue() instanceof String) {
                linkedHashMap18.put(entry11.getKey(), entry11.getValue());
            }
        }
        LinkedHashMap linkedHashMap19 = linkedHashMap18 instanceof Map ? linkedHashMap18 : null;
        LinkedHashMap linkedHashMap20 = new LinkedHashMap();
        for (Map.Entry entry12 : linkedHashMap2.entrySet()) {
            if (entry12.getValue() instanceof Float) {
                linkedHashMap20.put(entry12.getKey(), entry12.getValue());
            }
        }
        LinkedHashMap linkedHashMap21 = linkedHashMap20 instanceof Map ? linkedHashMap20 : null;
        LinkedHashMap linkedHashMap22 = new LinkedHashMap();
        for (Map.Entry entry13 : linkedHashMap2.entrySet()) {
            if (entry13.getValue() instanceof Long) {
                linkedHashMap22.put(entry13.getKey(), entry13.getValue());
            }
        }
        LinkedHashMap linkedHashMap23 = linkedHashMap22 instanceof Map ? linkedHashMap22 : null;
        LinkedHashMap linkedHashMap24 = new LinkedHashMap();
        for (Map.Entry entry14 : linkedHashMap2.entrySet()) {
            Object value2 = entry14.getValue();
            if ((value2 instanceof Set ? (Set) value2 : null) != null) {
                linkedHashMap24.put(entry14.getKey(), entry14.getValue());
            }
        }
        return new BackupFile(backupVars, new BackupVars(linkedHashMap15, linkedHashMap17, linkedHashMap19, linkedHashMap21, linkedHashMap23, linkedHashMap24 instanceof Map ? linkedHashMap24 : null));
    }

    public final void restore(Context context, BackupFile backupFile, boolean restoreSettings, boolean restoreDataStore) {
        Intrinsics.checkNotNullParameter(backupFile, "backupFile");
        if (context == null) {
            return;
        }
        if (restoreSettings) {
            restoreMap(context, backupFile.getSettings().getBool(), true);
            restoreMap(context, backupFile.getSettings().getInt(), true);
            restoreMap(context, backupFile.getSettings().getString(), true);
            restoreMap(context, backupFile.getSettings().getFloat(), true);
            restoreMap(context, backupFile.getSettings().getLong(), true);
            restoreMap(context, backupFile.getSettings().getStringSet(), true);
        }
        if (restoreDataStore) {
            restoreMap$default(this, context, backupFile.getDatastore().getBool(), false, 2, null);
            restoreMap$default(this, context, backupFile.getDatastore().getInt(), false, 2, null);
            restoreMap$default(this, context, backupFile.getDatastore().getString(), false, 2, null);
            restoreMap$default(this, context, backupFile.getDatastore().getFloat(), false, 2, null);
            restoreMap$default(this, context, backupFile.getDatastore().getLong(), false, 2, null);
            restoreMap$default(this, context, backupFile.getDatastore().getStringSet(), false, 2, null);
        }
        for (SyncRepo syncRepo : AccountManager.INSTANCE.getSyncApis()) {
            syncRepo.setRequireLibraryRefresh(true);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.BackupUtils$backup$1, reason: invalid class name */
    /* JADX INFO: compiled from: BackupUtils.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/utils/BackupUtils;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.BackupUtils$backup$1", f = "BackupUtils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, BackupUtils, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Context context, Continuation<? super AnonymousClass1> continuation) {
            super(3, continuation);
            this.$context = context;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, BackupUtils backupUtils, Continuation<? super Unit> continuation) {
            return new AnonymousClass1(this.$context, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:50:0x00f8  */
        /* JADX WARN: Code duplicated, block: B:52:0x00ff  */
        /* JADX WARN: Code duplicated, block: B:64:? A[SYNTHETIC] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Throwable th;
            Object obj2;
            Exception exc;
            PrintWriter printWriter;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            if (this.$context == null) {
                return Unit.INSTANCE;
            }
            Object obj3 = null;
            try {
                if (!UIHelper.INSTANCE.checkWrite(this.$context)) {
                    CommonActivity.INSTANCE.showToast(R.string.backup_failed, Boxing.boxInt(1));
                    Activity activity = CloudStreamApp.INSTANCE.getActivity(this.$context);
                    if (activity != null) {
                        UIHelper.INSTANCE.requestRW(activity);
                    }
                    return Unit.INSTANCE;
                }
                String str = "CS3_Backup_" + new SimpleDateFormat("yyyy_MM_dd_HH_mm", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
                BackupFile backup = BackupUtils.INSTANCE.getBackup(this.$context);
                OutputStream outputStreamOpenNew = BackupUtils.setupBackupStream$default(BackupUtils.INSTANCE, this.$context, str, null, 4, null).openNew();
                try {
                    printWriter = new PrintWriter(outputStreamOpenNew);
                    try {
                        printWriter.print(AppUtils.INSTANCE.toJson(backup));
                        CommonActivity.INSTANCE.showToast(R.string.backup_success, Boxing.boxInt(1));
                        _UtilCommonKt.closeQuietly(printWriter);
                        if (outputStreamOpenNew != null) {
                            _UtilCommonKt.closeQuietly(outputStreamOpenNew);
                        }
                    } catch (Exception e) {
                        obj3 = outputStreamOpenNew;
                        exc = e;
                        try {
                            ArchComponentExtKt.logError(exc);
                            try {
                                CommonActivity.INSTANCE.showToast(TextUtilKt.txt(R.string.backup_failed_error_format, exc.toString()), Boxing.boxInt(1));
                            } catch (Exception e2) {
                                ArchComponentExtKt.logError(e2);
                            }
                            if (printWriter != null) {
                                _UtilCommonKt.closeQuietly(printWriter);
                            }
                            if (obj3 != null) {
                                _UtilCommonKt.closeQuietly((Closeable) obj3);
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            obj2 = obj3;
                            obj3 = printWriter;
                            if (obj3 != null) {
                                _UtilCommonKt.closeQuietly((Closeable) obj3);
                            }
                            if (obj2 == null) {
                                throw th;
                            }
                            _UtilCommonKt.closeQuietly((Closeable) obj2);
                            throw th;
                        }
                    } catch (Throwable th3) {
                        obj2 = outputStreamOpenNew;
                        th = th3;
                        obj3 = printWriter;
                        if (obj3 != null) {
                            _UtilCommonKt.closeQuietly((Closeable) obj3);
                        }
                        if (obj2 == null) {
                            throw th;
                        }
                        _UtilCommonKt.closeQuietly((Closeable) obj2);
                        throw th;
                    }
                } catch (Exception e3) {
                    printWriter = null;
                    obj3 = outputStreamOpenNew;
                    exc = e3;
                } catch (Throwable th4) {
                    obj2 = outputStreamOpenNew;
                    th = th4;
                    if (obj3 != null) {
                        _UtilCommonKt.closeQuietly((Closeable) obj3);
                    }
                    if (obj2 == null) {
                        throw th;
                    }
                    _UtilCommonKt.closeQuietly((Closeable) obj2);
                    throw th;
                }
                return Unit.INSTANCE;
            } catch (Exception e4) {
                exc = e4;
                printWriter = null;
            } catch (Throwable th5) {
                th = th5;
                obj2 = null;
            }
        }
    }

    public final Job backup(Context context) {
        return Coroutines.INSTANCE.ioSafe(this, new AnonymousClass1(context, null));
    }

    static /* synthetic */ DownloadObjects.StreamData setupBackupStream$default(BackupUtils backupUtils, Context context, String str, String str2, int i, Object obj) throws IOException {
        if ((i & 4) != 0) {
            str2 = "txt";
        }
        return backupUtils.setupBackupStream(context, str, str2);
    }

    private final DownloadObjects.StreamData setupBackupStream(Context context, String name, String ext) throws IOException {
        VideoDownloadManager videoDownloadManager = VideoDownloadManager.INSTANCE;
        SafeFile first = getCurrentBackupDir(context).getFirst();
        if (first != null || (first = getDefaultBackupDir(context)) != null) {
            return videoDownloadManager.setupStream(first, name, (String) null, ext, false);
        }
        throw new IOException("Bad config");
    }

    public final void setUpBackup(final FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "<this>");
        try {
            restoreFileSelector = fragmentActivity.registerForActivityResult(new ActivityResultContracts.OpenDocument(), new ActivityResultCallback() { // from class: com.lagradost.cloudstream3.utils.BackupUtils$$ExternalSyntheticLambda0
                @Override // androidx.activity.result.ActivityResultCallback
                public final void onActivityResult(Object obj) {
                    BackupUtils.setUpBackup$lambda$0(fragmentActivity, (Uri) obj);
                }
            });
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
    }

    static final void setUpBackup$lambda$0(FragmentActivity fragmentActivity, Uri uri) {
        if (uri == null) {
            return;
        }
        Coroutines.INSTANCE.ioSafe(fragmentActivity, new BackupUtils$setUpBackup$1$1(fragmentActivity, uri, fragmentActivity, null));
    }

    public final void restorePrompt(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "<this>");
        fragmentActivity.runOnUiThread(new Runnable() { // from class: com.lagradost.cloudstream3.utils.BackupUtils$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                BackupUtils.restorePrompt$lambda$0();
            }
        });
    }

    static final void restorePrompt$lambda$0() {
        try {
            ActivityResultLauncher<String[]> activityResultLauncher = restoreFileSelector;
            if (activityResultLauncher != null) {
                activityResultLauncher.launch(new String[]{UtilsKt.MIME_TYPE_TEXT_PLAIN, "text/str", "text/x-unknown", "application/json", "unknown/unknown", "content/unknown", "application/octet-stream"});
            }
        } catch (Exception e) {
            CommonActivity.showToast$default(CommonActivity.INSTANCE, e.getMessage(), (Integer) null, 2, (Object) null);
            ArchComponentExtKt.logError(e);
        }
    }

    static /* synthetic */ void restoreMap$default(BackupUtils backupUtils, Context context, Map map, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        backupUtils.restoreMap(context, map, z);
    }

    private final <T> void restoreMap(Context context, Map<String, ? extends T> map, boolean z) {
        Editor editor = DataStore.INSTANCE.editor(context, z);
        if (map != null) {
            for (Map.Entry<String, ? extends T> entry : map.entrySet()) {
                if (INSTANCE.isTransferable(entry.getKey())) {
                    editor.setKeyRaw(entry.getKey(), entry.getValue());
                }
            }
        }
        editor.apply();
    }

    public final SafeFile getDefaultBackupDir(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return SafeFile.Companion.fromMedia$default(SafeFile.INSTANCE, context, MediaFileContentType.Downloads, null, false, 12, null);
    }

    public final Pair<SafeFile, String> getCurrentBackupDir(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.backup_path_key), null);
        return TuplesKt.to(baseBackupPathToFile(context, string), string);
    }

    private final SafeFile baseBackupPathToFile(Context context, String path) {
        String str = path;
        if (str == null || StringsKt.isBlank(str)) {
            return getDefaultBackupDir(context);
        }
        if (StringsKt.startsWith$default(path, "content://", false, 2, (Object) null)) {
            return SafeFile.INSTANCE.fromUri(context, Uri.parse(path));
        }
        return SafeFile.INSTANCE.fromFilePath(context, path);
    }
}
