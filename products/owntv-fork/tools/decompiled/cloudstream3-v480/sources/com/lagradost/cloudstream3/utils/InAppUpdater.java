package com.lagradost.cloudstream3.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import androidx.appcompat.app.AlertDialog;
import androidx.compose.runtime.ComposerKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.mediarouter.media.GlobalMediaRouter;
import androidx.preference.PreferenceManager;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivity;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.actions.temp.fcast.FcastManager;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.services.PackageInstallerService;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import io.ktor.http.ContentDisposition;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.ReferenceArraySerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.modules.SerializersModule;
import okio.BufferedSink;
import okio.Okio;
import okio.Okio__JvmOkioKt;
import org.mozilla.universalchardet.prober.contextanalysis.SJISContextAnalysis;

/* JADX INFO: compiled from: InAppUpdater.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\bÆ\u0002\u0018\u00002\u00020\u0001:\u0005&'()*B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\t\u001a\u00020\n*\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0082@¢\u0006\u0002\u0010\u000eJ\u0012\u0010\u000f\u001a\u00020\n*\u00020\u000bH\u0082@¢\u0006\u0002\u0010\u0010J\u0012\u0010\u0011\u001a\u00020\n*\u00020\u000bH\u0082@¢\u0006\u0002\u0010\u0010J\u001a\u0010\u0014\u001a\u00020\r*\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u0005H\u0082@¢\u0006\u0002\u0010\u0016J\u001f\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002¢\u0006\u0002\u0010\u001dJ\n\u0010\u001e\u001a\u00020\u001f*\u00020\u000bJ&\u0010 \u001a\u00020\r*\u00020\u000b2\b\b\u0002\u0010!\u001a\u00020\r2\b\b\u0002\u0010\f\u001a\u00020\rH\u0086@¢\u0006\u0002\u0010\"J\b\u0010#\u001a\u00020\rH\u0002J\u0012\u0010$\u001a\u0004\u0018\u00010\u00052\u0006\u0010%\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/lagradost/cloudstream3/utils/InAppUpdater;", "", "<init>", "()V", "GITHUB_USER_NAME", "", "GITHUB_REPO", "PRERELEASE_PACKAGE_NAME", "LOG_TAG", "getAppUpdate", "Lcom/lagradost/cloudstream3/utils/InAppUpdater$Update;", "Landroid/app/Activity;", "installPrerelease", "", "(Landroid/app/Activity;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getReleaseUpdate", "(Landroid/app/Activity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPreReleaseUpdate", "updateLock", "Lkotlinx/coroutines/sync/Mutex;", "downloadUpdate", ImagesContract.URL, "(Landroid/app/Activity;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "openApk", "", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "(Landroid/content/Context;Landroid/net/Uri;)Lkotlin/Unit;", "installPreReleaseIfNeeded", "Lkotlinx/coroutines/Job;", "runAutoUpdate", "checkAutoUpdate", "(Landroid/app/Activity;ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isMiUi", "getSystemProperty", "propName", "GithubAsset", "GithubRelease", "GithubObject", "GithubTag", "Update", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class InAppUpdater {
    private static final String GITHUB_REPO = "cloudstream";
    private static final String GITHUB_USER_NAME = "recloudstream";
    private static final String LOG_TAG = "InAppUpdater";
    private static final String PRERELEASE_PACKAGE_NAME = "com.lagradost.cloudstream3.prerelease";
    public static final InAppUpdater INSTANCE = new InAppUpdater();
    private static final Mutex updateLock = MutexKt.Mutex$default(false, 1, null);

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.InAppUpdater$downloadUpdate$1, reason: invalid class name */
    /* JADX INFO: compiled from: InAppUpdater.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.InAppUpdater", f = "InAppUpdater.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {RendererCapabilities.DECODER_SUPPORT_MASK, ComposerKt.providerMapsKey}, m = "downloadUpdate", n = {"$this$downloadUpdate", ImagesContract.URL, "appUpdateName", "appUpdateSuffix", "downloadedFile", "sink", "$this$withLock_u24default$iv", "$i$f$withLock", "$this$downloadUpdate", ImagesContract.URL, "appUpdateName", "appUpdateSuffix", "downloadedFile", "sink", "$this$withLock_u24default$iv", "$i$f$withLock", "$i$a$-withLock$default-InAppUpdater$downloadUpdate$4"}, nl = {385, 205}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "I$0", "I$1"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InAppUpdater.this.downloadUpdate(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.InAppUpdater$getAppUpdate$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppUpdater.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.InAppUpdater", f = "InAppUpdater.kt", i = {0, 0, 1, 1}, l = {91, 92}, m = "getAppUpdate", n = {"$this$getAppUpdate", "installPrerelease", "$this$getAppUpdate", "installPrerelease"}, nl = {92, 94}, s = {"L$0", "Z$0", "L$0", "Z$0"}, v = 2)
    static final class C09301 extends ContinuationImpl {
        Object L$0;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C09301(Continuation<? super C09301> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InAppUpdater.this.getAppUpdate(null, false, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.InAppUpdater$getPreReleaseUpdate$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppUpdater.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.InAppUpdater", f = "InAppUpdater.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1}, l = {159, 174}, m = "getPreReleaseUpdate", n = {"$this$getPreReleaseUpdate", "tagUrl", "releaseUrl", "headers", "$this$getPreReleaseUpdate", "tagUrl", "releaseUrl", "headers", "response", "found", "foundAsset"}, nl = {158, TypedValues.CycleType.TYPE_VISIBILITY}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"}, v = 2)
    static final class C09311 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        /* synthetic */ Object result;

        C09311(Continuation<? super C09311> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InAppUpdater.this.getPreReleaseUpdate(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.InAppUpdater$getReleaseUpdate$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppUpdater.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.InAppUpdater", f = "InAppUpdater.kt", i = {0, 0, 0}, l = {104}, m = "getReleaseUpdate", n = {"$this$getReleaseUpdate", ImagesContract.URL, "headers"}, nl = {103}, s = {"L$0", "L$1", "L$2"}, v = 2)
    static final class C09321 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C09321(Continuation<? super C09321> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InAppUpdater.this.getReleaseUpdate(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.InAppUpdater$runAutoUpdate$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppUpdater.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.InAppUpdater", f = "InAppUpdater.kt", i = {0, 0, 0, 0, 0}, l = {GlobalMediaRouter.CallbackHandler.MSG_ROUTE_VOLUME_CHANGED}, m = "runAutoUpdate", n = {"$this$runAutoUpdate", "settingsManager", "checkAutoUpdate", "installPrerelease", "autoUpdateEnabled"}, nl = {GlobalMediaRouter.CallbackHandler.MSG_ROUTE_PRESENTATION_DISPLAY_CHANGED}, s = {"L$0", "L$1", "Z$0", "Z$1", "Z$2"}, v = 2)
    static final class C09341 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        boolean Z$0;
        boolean Z$1;
        boolean Z$2;
        int label;
        /* synthetic */ Object result;

        C09341(Continuation<? super C09341> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InAppUpdater.this.runAutoUpdate(null, false, false, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void runAutoUpdate$lambda$0$0$2$1(DialogInterface dialogInterface, int i) {
    }

    private InAppUpdater() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: InAppUpdater.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0083\b\u0018\u0000 ,2\u00020\u0001:\u0002+,B/\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tBA\b\u0010\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\b\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J1\u0010\u001d\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00032\b\b\u0003\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010!\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\"\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u00002\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)H\u0001¢\u0006\u0002\b*R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u000f\u001a\u0004\b\u0016\u0010\u0011R\u001c\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u000f\u001a\u0004\b\u0018\u0010\u0011¨\u0006-"}, d2 = {"Lcom/lagradost/cloudstream3/utils/InAppUpdater$GithubAsset;", "", "name", "", ContentDisposition.Parameters.Size, "", "browserDownloadUrl", "contentType", "<init>", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getName$annotations", "()V", "getName", "()Ljava/lang/String;", "getSize$annotations", "getSize", "()I", "getBrowserDownloadUrl$annotations", "getBrowserDownloadUrl", "getContentType$annotations", "getContentType", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    static final /* data */ class GithubAsset {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("browser_download_url")
        private final String browserDownloadUrl;

        @JsonProperty("content_type")
        private final String contentType;

        @JsonProperty("name")
        private final String name;

        @JsonProperty(ContentDisposition.Parameters.Size)
        private final int size;

        public static /* synthetic */ GithubAsset copy$default(GithubAsset githubAsset, String str, int i, String str2, String str3, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = githubAsset.name;
            }
            if ((i2 & 2) != 0) {
                i = githubAsset.size;
            }
            if ((i2 & 4) != 0) {
                str2 = githubAsset.browserDownloadUrl;
            }
            if ((i2 & 8) != 0) {
                str3 = githubAsset.contentType;
            }
            return githubAsset.copy(str, i, str2, str3);
        }

        @SerialName("browser_download_url")
        public static /* synthetic */ void getBrowserDownloadUrl$annotations() {
        }

        @SerialName("content_type")
        public static /* synthetic */ void getContentType$annotations() {
        }

        @SerialName("name")
        public static /* synthetic */ void getName$annotations() {
        }

        @SerialName(ContentDisposition.Parameters.Size)
        public static /* synthetic */ void getSize$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getSize() {
            return this.size;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getBrowserDownloadUrl() {
            return this.browserDownloadUrl;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getContentType() {
            return this.contentType;
        }

        public final GithubAsset copy(@JsonProperty("name") String name, @JsonProperty(ContentDisposition.Parameters.Size) int size, @JsonProperty("browser_download_url") String browserDownloadUrl, @JsonProperty("content_type") String contentType) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(browserDownloadUrl, "browserDownloadUrl");
            Intrinsics.checkNotNullParameter(contentType, "contentType");
            return new GithubAsset(name, size, browserDownloadUrl, contentType);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof GithubAsset)) {
                return false;
            }
            GithubAsset githubAsset = (GithubAsset) other;
            return Intrinsics.areEqual(this.name, githubAsset.name) && this.size == githubAsset.size && Intrinsics.areEqual(this.browserDownloadUrl, githubAsset.browserDownloadUrl) && Intrinsics.areEqual(this.contentType, githubAsset.contentType);
        }

        public int hashCode() {
            return (((((this.name.hashCode() * 31) + this.size) * 31) + this.browserDownloadUrl.hashCode()) * 31) + this.contentType.hashCode();
        }

        public String toString() {
            return "GithubAsset(name=" + this.name + ", size=" + this.size + ", browserDownloadUrl=" + this.browserDownloadUrl + ", contentType=" + this.contentType + ')';
        }

        /* JADX INFO: compiled from: InAppUpdater.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/InAppUpdater$GithubAsset$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/InAppUpdater$GithubAsset;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<GithubAsset> serializer() {
                return InAppUpdater$GithubAsset$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ GithubAsset(int i, String str, int i2, String str2, String str3, SerializationConstructorMarker serializationConstructorMarker) {
            if (15 != (i & 15)) {
                PluginExceptionsKt.throwMissingFieldException(i, 15, InAppUpdater$GithubAsset$$serializer.INSTANCE.getDescriptor());
            }
            this.name = str;
            this.size = i2;
            this.browserDownloadUrl = str2;
            this.contentType = str3;
        }

        public GithubAsset(@JsonProperty("name") String name, @JsonProperty(ContentDisposition.Parameters.Size) int i, @JsonProperty("browser_download_url") String browserDownloadUrl, @JsonProperty("content_type") String contentType) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(browserDownloadUrl, "browserDownloadUrl");
            Intrinsics.checkNotNullParameter(contentType, "contentType");
            this.name = name;
            this.size = i;
            this.browserDownloadUrl = browserDownloadUrl;
            this.contentType = contentType;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(GithubAsset self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeStringElement(serialDesc, 0, self.name);
            output.encodeIntElement(serialDesc, 1, self.size);
            output.encodeStringElement(serialDesc, 2, self.browserDownloadUrl);
            output.encodeStringElement(serialDesc, 3, self.contentType);
        }

        public final String getName() {
            return this.name;
        }

        public final int getSize() {
            return this.size;
        }

        public final String getBrowserDownloadUrl() {
            return this.browserDownloadUrl;
        }

        public final String getContentType() {
            return this.contentType;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: InAppUpdater.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0083\b\u0018\u0000 72\u00020\u0001:\u000267BI\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\u000e\b\u0001\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\b\b\u0001\u0010\b\u001a\u00020\u0003\u0012\b\b\u0001\u0010\t\u001a\u00020\n\u0012\b\b\u0001\u0010\u000b\u001a\u00020\u0003¢\u0006\u0004\b\f\u0010\rB[\b\u0010\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\f\u0010\u0012J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\u000f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\nHÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003JK\u0010)\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\u000e\b\u0003\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0003\u0010\b\u001a\u00020\u00032\b\b\u0003\u0010\t\u001a\u00020\n2\b\b\u0003\u0010\u000b\u001a\u00020\u0003HÆ\u0001J\u0014\u0010*\u001a\u00020\n2\b\u0010+\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010,\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010-\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\u00002\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u000204H\u0001¢\u0006\u0002\b5R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0014\u001a\u0004\b\u0018\u0010\u0016R\"\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u0014\u001a\u0004\b\u001a\u0010\u001bR\u001c\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001c\u0010\u0014\u001a\u0004\b\u001d\u0010\u0016R\u001c\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001e\u0010\u0014\u001a\u0004\b\u001f\u0010 R\u001c\u0010\u000b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b!\u0010\u0014\u001a\u0004\b\"\u0010\u0016¨\u00068"}, d2 = {"Lcom/lagradost/cloudstream3/utils/InAppUpdater$GithubRelease;", "", "tagName", "", TtmlNode.TAG_BODY, "assets", "", "Lcom/lagradost/cloudstream3/utils/InAppUpdater$GithubAsset;", "targetCommitish", "prerelease", "", "nodeId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;ZLjava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;ZLjava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getTagName$annotations", "()V", "getTagName", "()Ljava/lang/String;", "getBody$annotations", "getBody", "getAssets$annotations", "getAssets", "()Ljava/util/List;", "getTargetCommitish$annotations", "getTargetCommitish", "getPrerelease$annotations", "getPrerelease", "()Z", "getNodeId$annotations", "getNodeId", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    static final /* data */ class GithubRelease {

        @JsonProperty("assets")
        private final List<GithubAsset> assets;

        @JsonProperty(TtmlNode.TAG_BODY)
        private final String body;

        @JsonProperty("node_id")
        private final String nodeId;

        @JsonProperty("prerelease")
        private final boolean prerelease;

        @JsonProperty("tag_name")
        private final String tagName;

        @JsonProperty("target_commitish")
        private final String targetCommitish;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.utils.InAppUpdater$GithubRelease$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return InAppUpdater.GithubRelease._childSerializers$_anonymous_();
            }
        }), null, null, null};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(InAppUpdater$GithubAsset$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ GithubRelease copy$default(GithubRelease githubRelease, String str, String str2, List list, String str3, boolean z, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                str = githubRelease.tagName;
            }
            if ((i & 2) != 0) {
                str2 = githubRelease.body;
            }
            if ((i & 4) != 0) {
                list = githubRelease.assets;
            }
            if ((i & 8) != 0) {
                str3 = githubRelease.targetCommitish;
            }
            if ((i & 16) != 0) {
                z = githubRelease.prerelease;
            }
            if ((i & 32) != 0) {
                str4 = githubRelease.nodeId;
            }
            boolean z2 = z;
            String str5 = str4;
            return githubRelease.copy(str, str2, list, str3, z2, str5);
        }

        @SerialName("assets")
        public static /* synthetic */ void getAssets$annotations() {
        }

        @SerialName(TtmlNode.TAG_BODY)
        public static /* synthetic */ void getBody$annotations() {
        }

        @SerialName("node_id")
        public static /* synthetic */ void getNodeId$annotations() {
        }

        @SerialName("prerelease")
        public static /* synthetic */ void getPrerelease$annotations() {
        }

        @SerialName("tag_name")
        public static /* synthetic */ void getTagName$annotations() {
        }

        @SerialName("target_commitish")
        public static /* synthetic */ void getTargetCommitish$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getTagName() {
            return this.tagName;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getBody() {
            return this.body;
        }

        public final List<GithubAsset> component3() {
            return this.assets;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getTargetCommitish() {
            return this.targetCommitish;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final boolean getPrerelease() {
            return this.prerelease;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getNodeId() {
            return this.nodeId;
        }

        public final GithubRelease copy(@JsonProperty("tag_name") String tagName, @JsonProperty(TtmlNode.TAG_BODY) String body, @JsonProperty("assets") List<GithubAsset> assets, @JsonProperty("target_commitish") String targetCommitish, @JsonProperty("prerelease") boolean prerelease, @JsonProperty("node_id") String nodeId) {
            Intrinsics.checkNotNullParameter(tagName, "tagName");
            Intrinsics.checkNotNullParameter(body, "body");
            Intrinsics.checkNotNullParameter(assets, "assets");
            Intrinsics.checkNotNullParameter(targetCommitish, "targetCommitish");
            Intrinsics.checkNotNullParameter(nodeId, "nodeId");
            return new GithubRelease(tagName, body, assets, targetCommitish, prerelease, nodeId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof GithubRelease)) {
                return false;
            }
            GithubRelease githubRelease = (GithubRelease) other;
            return Intrinsics.areEqual(this.tagName, githubRelease.tagName) && Intrinsics.areEqual(this.body, githubRelease.body) && Intrinsics.areEqual(this.assets, githubRelease.assets) && Intrinsics.areEqual(this.targetCommitish, githubRelease.targetCommitish) && this.prerelease == githubRelease.prerelease && Intrinsics.areEqual(this.nodeId, githubRelease.nodeId);
        }

        public int hashCode() {
            return (((((((((this.tagName.hashCode() * 31) + this.body.hashCode()) * 31) + this.assets.hashCode()) * 31) + this.targetCommitish.hashCode()) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.prerelease)) * 31) + this.nodeId.hashCode();
        }

        public String toString() {
            return "GithubRelease(tagName=" + this.tagName + ", body=" + this.body + ", assets=" + this.assets + ", targetCommitish=" + this.targetCommitish + ", prerelease=" + this.prerelease + ", nodeId=" + this.nodeId + ')';
        }

        /* JADX INFO: compiled from: InAppUpdater.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/InAppUpdater$GithubRelease$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/InAppUpdater$GithubRelease;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<GithubRelease> serializer() {
                return InAppUpdater$GithubRelease$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ GithubRelease(int i, String str, String str2, List list, String str3, boolean z, String str4, SerializationConstructorMarker serializationConstructorMarker) {
            if (63 != (i & 63)) {
                PluginExceptionsKt.throwMissingFieldException(i, 63, InAppUpdater$GithubRelease$$serializer.INSTANCE.getDescriptor());
            }
            this.tagName = str;
            this.body = str2;
            this.assets = list;
            this.targetCommitish = str3;
            this.prerelease = z;
            this.nodeId = str4;
        }

        public GithubRelease(@JsonProperty("tag_name") String tagName, @JsonProperty(TtmlNode.TAG_BODY) String body, @JsonProperty("assets") List<GithubAsset> assets, @JsonProperty("target_commitish") String targetCommitish, @JsonProperty("prerelease") boolean z, @JsonProperty("node_id") String nodeId) {
            Intrinsics.checkNotNullParameter(tagName, "tagName");
            Intrinsics.checkNotNullParameter(body, "body");
            Intrinsics.checkNotNullParameter(assets, "assets");
            Intrinsics.checkNotNullParameter(targetCommitish, "targetCommitish");
            Intrinsics.checkNotNullParameter(nodeId, "nodeId");
            this.tagName = tagName;
            this.body = body;
            this.assets = assets;
            this.targetCommitish = targetCommitish;
            this.prerelease = z;
            this.nodeId = nodeId;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(GithubRelease self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            output.encodeStringElement(serialDesc, 0, self.tagName);
            output.encodeStringElement(serialDesc, 1, self.body);
            output.encodeSerializableElement(serialDesc, 2, lazyArr[2].getValue(), self.assets);
            output.encodeStringElement(serialDesc, 3, self.targetCommitish);
            output.encodeBooleanElement(serialDesc, 4, self.prerelease);
            output.encodeStringElement(serialDesc, 5, self.nodeId);
        }

        public final String getTagName() {
            return this.tagName;
        }

        public final String getBody() {
            return this.body;
        }

        public final List<GithubAsset> getAssets() {
            return this.assets;
        }

        public final String getTargetCommitish() {
            return this.targetCommitish;
        }

        public final boolean getPrerelease() {
            return this.prerelease;
        }

        public final String getNodeId() {
            return this.nodeId;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: InAppUpdater.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0083\b\u0018\u0000 '2\u00020\u0001:\u0002&'B%\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007B9\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\fJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J'\u0010\u0018\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0001¢\u0006\u0002\b%R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0010R\u001c\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0014\u0010\u0010¨\u0006("}, d2 = {"Lcom/lagradost/cloudstream3/utils/InAppUpdater$GithubObject;", "", "sha", "", "type", ImagesContract.URL, "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getSha$annotations", "()V", "getSha", "()Ljava/lang/String;", "getType$annotations", "getType", "getUrl$annotations", "getUrl", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    static final /* data */ class GithubObject {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("sha")
        private final String sha;

        @JsonProperty("type")
        private final String type;

        @JsonProperty(ImagesContract.URL)
        private final String url;

        public static /* synthetic */ GithubObject copy$default(GithubObject githubObject, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = githubObject.sha;
            }
            if ((i & 2) != 0) {
                str2 = githubObject.type;
            }
            if ((i & 4) != 0) {
                str3 = githubObject.url;
            }
            return githubObject.copy(str, str2, str3);
        }

        @SerialName("sha")
        public static /* synthetic */ void getSha$annotations() {
        }

        @SerialName("type")
        public static /* synthetic */ void getType$annotations() {
        }

        @SerialName(ImagesContract.URL)
        public static /* synthetic */ void getUrl$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getSha() {
            return this.sha;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        public final GithubObject copy(@JsonProperty("sha") String sha, @JsonProperty("type") String type, @JsonProperty(ImagesContract.URL) String url) {
            Intrinsics.checkNotNullParameter(sha, "sha");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(url, "url");
            return new GithubObject(sha, type, url);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof GithubObject)) {
                return false;
            }
            GithubObject githubObject = (GithubObject) other;
            return Intrinsics.areEqual(this.sha, githubObject.sha) && Intrinsics.areEqual(this.type, githubObject.type) && Intrinsics.areEqual(this.url, githubObject.url);
        }

        public int hashCode() {
            return (((this.sha.hashCode() * 31) + this.type.hashCode()) * 31) + this.url.hashCode();
        }

        public String toString() {
            return "GithubObject(sha=" + this.sha + ", type=" + this.type + ", url=" + this.url + ')';
        }

        /* JADX INFO: compiled from: InAppUpdater.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/InAppUpdater$GithubObject$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/InAppUpdater$GithubObject;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<GithubObject> serializer() {
                return InAppUpdater$GithubObject$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ GithubObject(int i, String str, String str2, String str3, SerializationConstructorMarker serializationConstructorMarker) {
            if (7 != (i & 7)) {
                PluginExceptionsKt.throwMissingFieldException(i, 7, InAppUpdater$GithubObject$$serializer.INSTANCE.getDescriptor());
            }
            this.sha = str;
            this.type = str2;
            this.url = str3;
        }

        public GithubObject(@JsonProperty("sha") String sha, @JsonProperty("type") String type, @JsonProperty(ImagesContract.URL) String url) {
            Intrinsics.checkNotNullParameter(sha, "sha");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(url, "url");
            this.sha = sha;
            this.type = type;
            this.url = url;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(GithubObject self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeStringElement(serialDesc, 0, self.sha);
            output.encodeStringElement(serialDesc, 1, self.type);
            output.encodeStringElement(serialDesc, 2, self.url);
        }

        public final String getSha() {
            return this.sha;
        }

        public final String getType() {
            return this.type;
        }

        public final String getUrl() {
            return this.url;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: InAppUpdater.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0083\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/utils/InAppUpdater$GithubTag;", "", "githubObject", "Lcom/lagradost/cloudstream3/utils/InAppUpdater$GithubObject;", "<init>", "(Lcom/lagradost/cloudstream3/utils/InAppUpdater$GithubObject;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/utils/InAppUpdater$GithubObject;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getGithubObject$annotations", "()V", "getGithubObject", "()Lcom/lagradost/cloudstream3/utils/InAppUpdater$GithubObject;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    static final /* data */ class GithubTag {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("object")
        private final GithubObject githubObject;

        public static /* synthetic */ GithubTag copy$default(GithubTag githubTag, GithubObject githubObject, int i, Object obj) {
            if ((i & 1) != 0) {
                githubObject = githubTag.githubObject;
            }
            return githubTag.copy(githubObject);
        }

        @SerialName("object")
        public static /* synthetic */ void getGithubObject$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final GithubObject getGithubObject() {
            return this.githubObject;
        }

        public final GithubTag copy(@JsonProperty("object") GithubObject githubObject) {
            Intrinsics.checkNotNullParameter(githubObject, "githubObject");
            return new GithubTag(githubObject);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof GithubTag) && Intrinsics.areEqual(this.githubObject, ((GithubTag) other).githubObject);
        }

        public int hashCode() {
            return this.githubObject.hashCode();
        }

        public String toString() {
            return "GithubTag(githubObject=" + this.githubObject + ')';
        }

        /* JADX INFO: compiled from: InAppUpdater.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/InAppUpdater$GithubTag$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/InAppUpdater$GithubTag;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<GithubTag> serializer() {
                return InAppUpdater$GithubTag$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ GithubTag(int i, GithubObject githubObject, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, InAppUpdater$GithubTag$$serializer.INSTANCE.getDescriptor());
            }
            this.githubObject = githubObject;
        }

        public GithubTag(@JsonProperty("object") GithubObject githubObject) {
            Intrinsics.checkNotNullParameter(githubObject, "githubObject");
            this.githubObject = githubObject;
        }

        public final GithubObject getGithubObject() {
            return this.githubObject;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: InAppUpdater.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0083\b\u0018\u0000 02\u00020\u0001:\u0002/0BA\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\t\u0010\nBK\b\u0010\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\t\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0005HÆ\u0003JC\u0010\"\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010#\u001a\u00020\u00032\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010%\u001a\u00020\fHÖ\u0081\u0004J\n\u0010&\u001a\u00020\u0005HÖ\u0081\u0004J%\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0001¢\u0006\u0002\b.R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0011\u001a\u0004\b\u0018\u0010\u0016R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u0011\u001a\u0004\b\u001a\u0010\u0016R\u001e\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u0011\u001a\u0004\b\u001c\u0010\u0016¨\u00061"}, d2 = {"Lcom/lagradost/cloudstream3/utils/InAppUpdater$Update;", "", "shouldUpdate", "", "updateURL", "", "updateVersion", "changelog", "updateNodeId", "<init>", "(ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getShouldUpdate$annotations", "()V", "getShouldUpdate", "()Z", "getUpdateURL$annotations", "getUpdateURL", "()Ljava/lang/String;", "getUpdateVersion$annotations", "getUpdateVersion", "getChangelog$annotations", "getChangelog", "getUpdateNodeId$annotations", "getUpdateNodeId", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    static final /* data */ class Update {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("changelog")
        private final String changelog;

        @JsonProperty("shouldUpdate")
        private final boolean shouldUpdate;

        @JsonProperty("updateNodeId")
        private final String updateNodeId;

        @JsonProperty("updateURL")
        private final String updateURL;

        @JsonProperty("updateVersion")
        private final String updateVersion;

        public static /* synthetic */ Update copy$default(Update update, boolean z, String str, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                z = update.shouldUpdate;
            }
            if ((i & 2) != 0) {
                str = update.updateURL;
            }
            if ((i & 4) != 0) {
                str2 = update.updateVersion;
            }
            if ((i & 8) != 0) {
                str3 = update.changelog;
            }
            if ((i & 16) != 0) {
                str4 = update.updateNodeId;
            }
            String str5 = str4;
            String str6 = str2;
            return update.copy(z, str, str6, str3, str5);
        }

        @SerialName("changelog")
        public static /* synthetic */ void getChangelog$annotations() {
        }

        @SerialName("shouldUpdate")
        public static /* synthetic */ void getShouldUpdate$annotations() {
        }

        @SerialName("updateNodeId")
        public static /* synthetic */ void getUpdateNodeId$annotations() {
        }

        @SerialName("updateURL")
        public static /* synthetic */ void getUpdateURL$annotations() {
        }

        @SerialName("updateVersion")
        public static /* synthetic */ void getUpdateVersion$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getShouldUpdate() {
            return this.shouldUpdate;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getUpdateURL() {
            return this.updateURL;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getUpdateVersion() {
            return this.updateVersion;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getChangelog() {
            return this.changelog;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getUpdateNodeId() {
            return this.updateNodeId;
        }

        public final Update copy(@JsonProperty("shouldUpdate") boolean shouldUpdate, @JsonProperty("updateURL") String updateURL, @JsonProperty("updateVersion") String updateVersion, @JsonProperty("changelog") String changelog, @JsonProperty("updateNodeId") String updateNodeId) {
            return new Update(shouldUpdate, updateURL, updateVersion, changelog, updateNodeId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Update)) {
                return false;
            }
            Update update = (Update) other;
            return this.shouldUpdate == update.shouldUpdate && Intrinsics.areEqual(this.updateURL, update.updateURL) && Intrinsics.areEqual(this.updateVersion, update.updateVersion) && Intrinsics.areEqual(this.changelog, update.changelog) && Intrinsics.areEqual(this.updateNodeId, update.updateNodeId);
        }

        public int hashCode() {
            int iM = NextAiring$$ExternalSyntheticBackport0.m(this.shouldUpdate) * 31;
            String str = this.updateURL;
            int iHashCode = (iM + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.updateVersion;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.changelog;
            int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.updateNodeId;
            return iHashCode3 + (str4 != null ? str4.hashCode() : 0);
        }

        public String toString() {
            return "Update(shouldUpdate=" + this.shouldUpdate + ", updateURL=" + this.updateURL + ", updateVersion=" + this.updateVersion + ", changelog=" + this.changelog + ", updateNodeId=" + this.updateNodeId + ')';
        }

        /* JADX INFO: compiled from: InAppUpdater.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/InAppUpdater$Update$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/InAppUpdater$Update;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Update> serializer() {
                return InAppUpdater$Update$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Update(int i, boolean z, String str, String str2, String str3, String str4, SerializationConstructorMarker serializationConstructorMarker) {
            if (31 != (i & 31)) {
                PluginExceptionsKt.throwMissingFieldException(i, 31, InAppUpdater$Update$$serializer.INSTANCE.getDescriptor());
            }
            this.shouldUpdate = z;
            this.updateURL = str;
            this.updateVersion = str2;
            this.changelog = str3;
            this.updateNodeId = str4;
        }

        public Update(@JsonProperty("shouldUpdate") boolean z, @JsonProperty("updateURL") String str, @JsonProperty("updateVersion") String str2, @JsonProperty("changelog") String str3, @JsonProperty("updateNodeId") String str4) {
            this.shouldUpdate = z;
            this.updateURL = str;
            this.updateVersion = str2;
            this.changelog = str3;
            this.updateNodeId = str4;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Update self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeBooleanElement(serialDesc, 0, self.shouldUpdate);
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.updateURL);
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.updateVersion);
            output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.changelog);
            output.encodeNullableSerializableElement(serialDesc, 4, StringSerializer.INSTANCE, self.updateNodeId);
        }

        public final boolean getShouldUpdate() {
            return this.shouldUpdate;
        }

        public final String getUpdateURL() {
            return this.updateURL;
        }

        public final String getUpdateVersion() {
            return this.updateVersion;
        }

        public final String getChangelog() {
            return this.changelog;
        }

        public final String getUpdateNodeId() {
            return this.updateNodeId;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x006c, code lost:
    
        if (r9 == r1) goto L29;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getAppUpdate(android.app.Activity r7, boolean r8, kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.utils.InAppUpdater.Update> r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof com.lagradost.cloudstream3.utils.InAppUpdater.C09301
            if (r0 == 0) goto L14
            r0 = r9
            com.lagradost.cloudstream3.utils.InAppUpdater$getAppUpdate$1 r0 = (com.lagradost.cloudstream3.utils.InAppUpdater.C09301) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            com.lagradost.cloudstream3.utils.InAppUpdater$getAppUpdate$1 r0 = new com.lagradost.cloudstream3.utils.InAppUpdater$getAppUpdate$1
            r0.<init>(r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L45
            if (r2 == r4) goto L3b
            if (r2 != r3) goto L33
            boolean r6 = r0.Z$0
            java.lang.Object r6 = r0.L$0
            android.app.Activity r6 = (android.app.Activity) r6
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Exception -> L72
            goto L6f
        L33:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3b:
            boolean r6 = r0.Z$0
            java.lang.Object r6 = r0.L$0
            android.app.Activity r6 = (android.app.Activity) r6
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Exception -> L72
            goto L5b
        L45:
            kotlin.ResultKt.throwOnFailure(r9)
            if (r8 == 0) goto L5e
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch: java.lang.Exception -> L72
            r0.L$0 = r9     // Catch: java.lang.Exception -> L72
            r0.Z$0 = r8     // Catch: java.lang.Exception -> L72
            r0.label = r4     // Catch: java.lang.Exception -> L72
            java.lang.Object r9 = r6.getPreReleaseUpdate(r7, r0)     // Catch: java.lang.Exception -> L72
            if (r9 != r1) goto L5b
            goto L6e
        L5b:
            com.lagradost.cloudstream3.utils.InAppUpdater$Update r9 = (com.lagradost.cloudstream3.utils.InAppUpdater.Update) r9     // Catch: java.lang.Exception -> L72
            return r9
        L5e:
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch: java.lang.Exception -> L72
            r0.L$0 = r9     // Catch: java.lang.Exception -> L72
            r0.Z$0 = r8     // Catch: java.lang.Exception -> L72
            r0.label = r3     // Catch: java.lang.Exception -> L72
            java.lang.Object r9 = r6.getReleaseUpdate(r7, r0)     // Catch: java.lang.Exception -> L72
            if (r9 != r1) goto L6f
        L6e:
            return r1
        L6f:
            com.lagradost.cloudstream3.utils.InAppUpdater$Update r9 = (com.lagradost.cloudstream3.utils.InAppUpdater.Update) r9     // Catch: java.lang.Exception -> L72
            return r9
        L72:
            r0 = move-exception
            r6 = r0
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            java.lang.String r6 = android.util.Log.getStackTraceString(r6)
            java.lang.String r7 = "InAppUpdater"
            android.util.Log.e(r7, r6)
            com.lagradost.cloudstream3.utils.InAppUpdater$Update r0 = new com.lagradost.cloudstream3.utils.InAppUpdater$Update
            r4 = 0
            r5 = 0
            r1 = 0
            r2 = 0
            r3 = 0
            r0.<init>(r1, r2, r3, r4, r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.utils.InAppUpdater.getAppUpdate(android.app.Activity, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:66:0x01c2  */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public final Object getReleaseUpdate(Activity activity, Continuation<? super Update> continuation) throws JsonProcessingException {
        C09321 c09321;
        boolean z;
        Activity activity2;
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        boolean z2;
        String str;
        List<String> groupValues;
        String name;
        List<GithubAsset> assets;
        if (continuation instanceof C09321) {
            c09321 = (C09321) continuation;
            if ((c09321.label & Integer.MIN_VALUE) != 0) {
                c09321.label -= Integer.MIN_VALUE;
            } else {
                c09321 = new C09321(continuation);
            }
        } else {
            c09321 = new C09321(continuation);
        }
        Object obj = c09321.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c09321.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to("Accept", "application/vnd.github.v3+json"));
            AppUtils appUtils = AppUtils.INSTANCE;
            Requests app2 = MainActivityKt.getApp();
            c09321.L$0 = activity;
            c09321.L$1 = SpillingKt.nullOutSpilledVariable("https://api.github.com/repos/recloudstream/cloudstream/releases");
            c09321.L$2 = SpillingKt.nullOutSpilledVariable(mapMapOf);
            c09321.L$3 = appUtils;
            c09321.label = 1;
            C09321 c09322 = c09321;
            z = true;
            Object obj2 = Requests.get$default(app2, "https://api.github.com/repos/recloudstream/cloudstream/releases", mapMapOf, null, null, null, false, 0, null, 0L, null, false, null, c09322, 4092, null);
            if (obj2 == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj = obj2;
            activity2 = activity;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Activity activity3 = (Activity) c09321.L$0;
            ResultKt.throwOnFailure(obj);
            activity2 = activity3;
            z = true;
        }
        String text = ((NiceResponse) obj).getText();
        try {
            Result.Companion companion = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(GithubRelease.class), GithubRelease.INSTANCE.serializer()));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
        }
        Integer numBoxInt = null;
        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
            try {
                Result.Companion companion3 = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(GithubRelease[].class), null, 2, null));
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
                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, text);
            } catch (SerializationException e) {
                ArchComponentExtKt.logError(e);
                objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<GithubRelease[]>() { // from class: com.lagradost.cloudstream3.utils.InAppUpdater$getReleaseUpdate$$inlined$parseJson$1
                });
            } catch (Throwable unused) {
                objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<GithubRelease[]>() { // from class: com.lagradost.cloudstream3.utils.InAppUpdater$getReleaseUpdate$$inlined$parseJson$1
                });
            }
        } else {
            objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<GithubRelease[]>() { // from class: com.lagradost.cloudstream3.utils.InAppUpdater$getReleaseUpdate$$inlined$parseJson$1
            });
        }
        List list = ArraysKt.toList((Object[]) objDecodeFromString);
        final Regex regex = new Regex("(.*?((\\d+)\\.(\\d+)\\.(\\d+))\\.apk)");
        Regex regex2 = new Regex("(.*?((\\d+)\\.(\\d+)\\.(\\d+)).*)");
        ArrayList arrayList = new ArrayList();
        for (Object obj3 : list) {
            if (!((GithubRelease) obj3).getPrerelease()) {
                arrayList.add(obj3);
            }
        }
        GithubRelease githubRelease = (GithubRelease) CollectionsKt.lastOrNull(CollectionsKt.toList(CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: com.lagradost.cloudstream3.utils.InAppUpdater$getReleaseUpdate$$inlined$compareBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                Integer numValueOf;
                T next;
                T next2;
                String name2;
                MatchResult matchResultFind$default;
                List<String> groupValues2;
                String name3;
                MatchResult matchResultFind$default2;
                List<String> groupValues3;
                Iterator<T> it = ((InAppUpdater.GithubRelease) t).getAssets().iterator();
                do {
                    numValueOf = null;
                    if (!it.hasNext()) {
                        next = (T) null;
                        break;
                    }
                    next = it.next();
                } while (!Intrinsics.areEqual(((InAppUpdater.GithubAsset) next).getContentType(), "application/vnd.android.package-archive"));
                InAppUpdater.GithubAsset githubAsset = next;
                Integer numValueOf2 = (githubAsset == null || (name3 = githubAsset.getName()) == null || (matchResultFind$default2 = Regex.find$default(regex, name3, 0, 2, null)) == null || (groupValues3 = matchResultFind$default2.getGroupValues()) == null) ? null : Integer.valueOf((Integer.parseInt(groupValues3.get(3)) * 100000000) + (Integer.parseInt(groupValues3.get(4)) * 10000) + Integer.parseInt(groupValues3.get(5)));
                Iterator<T> it2 = ((InAppUpdater.GithubRelease) t2).getAssets().iterator();
                do {
                    if (!it2.hasNext()) {
                        next2 = (T) null;
                        break;
                    }
                    next2 = it2.next();
                } while (!Intrinsics.areEqual(((InAppUpdater.GithubAsset) next2).getContentType(), "application/vnd.android.package-archive"));
                InAppUpdater.GithubAsset githubAsset2 = next2;
                if (githubAsset2 != null && (name2 = githubAsset2.getName()) != null && (matchResultFind$default = Regex.find$default(regex, name2, 0, 2, null)) != null && (groupValues2 = matchResultFind$default.getGroupValues()) != null) {
                    numValueOf = Integer.valueOf((Integer.parseInt(groupValues2.get(3)) * 100000000) + (Integer.parseInt(groupValues2.get(4)) * 10000) + Integer.parseInt(groupValues2.get(5)));
                }
                return ComparisonsKt.compareValues(numValueOf2, numValueOf);
            }
        })));
        GithubAsset githubAsset = (githubRelease == null || (assets = githubRelease.getAssets()) == null) ? null : (GithubAsset) CollectionsKt.getOrNull(assets, 0);
        MatchResult matchResultFind$default = (githubAsset == null || (name = githubAsset.getName()) == null) ? null : Regex.find$default(regex, name, 0, 2, null);
        if (matchResultFind$default == null) {
            return new Update(false, null, null, null, null);
        }
        String packageName = activity2.getPackageName();
        PackageInfo packageInfo = packageName != null ? activity2.getPackageManager().getPackageInfo(packageName, 0) : null;
        if (StringsKt.isBlank(githubAsset.getBrowserDownloadUrl())) {
            z2 = false;
        } else {
            if (packageInfo != null && (str = packageInfo.versionName) != null) {
                MatchResult matchResultFind$default2 = Regex.find$default(regex2, str, 0, 2, null);
                Integer numBoxInt2 = (matchResultFind$default2 == null || (groupValues = matchResultFind$default2.getGroupValues()) == null) ? null : Boxing.boxInt((Integer.parseInt(groupValues.get(3)) * 100000000) + (Integer.parseInt(groupValues.get(4)) * 10000) + Integer.parseInt(groupValues.get(5)));
                if (numBoxInt2 != null) {
                    int iIntValue = numBoxInt2.intValue();
                    List<String> groupValues2 = matchResultFind$default.getGroupValues();
                    numBoxInt = Boxing.boxInt(Intrinsics.compare(iIntValue, (Integer.parseInt(groupValues2.get(3)) * 100000000) + (Integer.parseInt(groupValues2.get(4)) * 10000) + Integer.parseInt(groupValues2.get(5))));
                }
            }
            Intrinsics.checkNotNull(numBoxInt);
            if (numBoxInt.intValue() < 0) {
                z2 = z;
            } else {
                z2 = false;
            }
        }
        return new Update(z2, githubAsset.getBrowserDownloadUrl(), matchResultFind$default.getGroupValues().get(2), githubRelease.getBody(), githubRelease.getNodeId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:102:0x02ac  */
    /* JADX WARN: Code duplicated, block: B:117:0x0266 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:119:0x029b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Code duplicated, block: B:82:0x0263  */
    /* JADX WARN: Code duplicated, block: B:95:0x0296  */
    public final Object getPreReleaseUpdate(Activity activity, Continuation<? super Update> continuation) throws JsonProcessingException {
        C09311 c09311;
        Map map;
        Object obj;
        C09311 c09312;
        String str;
        String str2;
        Activity activity2;
        Object objM8936constructorimpl;
        int i;
        Object objDecodeFromString;
        ListIterator listIterator;
        Object obj2;
        GithubAsset githubAsset;
        GithubRelease githubRelease;
        Activity activity3;
        GithubAsset githubAsset2;
        List<GithubAsset> assets;
        String text;
        Object objM8936constructorimpl2;
        Object obj3;
        Object obj4;
        KSerializer kSerializer;
        Object objDecodeFromString2;
        if (continuation instanceof C09311) {
            c09311 = (C09311) continuation;
            if ((c09311.label & Integer.MIN_VALUE) != 0) {
                c09311.label -= Integer.MIN_VALUE;
            } else {
                c09311 = new C09311(continuation);
            }
        } else {
            c09311 = new C09311(continuation);
        }
        Object obj5 = c09311.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c09311.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj5);
                Map mapMapOf = MapsKt.mapOf(TuplesKt.to("Accept", "application/vnd.github.v3+json"));
                AppUtils appUtils = AppUtils.INSTANCE;
                Requests app2 = MainActivityKt.getApp();
                c09311.L$0 = activity;
                c09311.L$1 = "https://api.github.com/repos/recloudstream/cloudstream/git/ref/tags/pre-release";
                c09311.L$2 = SpillingKt.nullOutSpilledVariable("https://api.github.com/repos/recloudstream/cloudstream/releases");
                c09311.L$3 = mapMapOf;
                c09311.L$4 = appUtils;
                c09311.label = 1;
                map = mapMapOf;
                C09311 c09313 = c09311;
                obj = coroutine_suspended;
                Object obj6 = Requests.get$default(app2, "https://api.github.com/repos/recloudstream/cloudstream/releases", map, null, null, null, false, 0, null, 0L, null, false, null, c09313, 4092, null);
                c09312 = c09313;
                if (obj6 != obj) {
                    str = "https://api.github.com/repos/recloudstream/cloudstream/releases";
                    str2 = "https://api.github.com/repos/recloudstream/cloudstream/git/ref/tags/pre-release";
                    obj5 = obj6;
                    activity2 = activity;
                }
                return obj;
            }
            if (i2 == 1) {
                Map map2 = (Map) c09311.L$3;
                str = (String) c09311.L$2;
                String str3 = (String) c09311.L$1;
                Activity activity4 = (Activity) c09311.L$0;
                ResultKt.throwOnFailure(obj5);
                c09312 = c09311;
                obj = coroutine_suspended;
                activity2 = activity4;
                map = map2;
                str2 = str3;
            } else {
                if (i2 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                githubAsset2 = (GithubAsset) c09311.L$6;
                githubRelease = (GithubRelease) c09311.L$5;
                activity3 = (Activity) c09311.L$0;
                ResultKt.throwOnFailure(obj5);
            }
            text = ((NiceResponse) obj5).getText();
            try {
                Result.Companion companion = Result.INSTANCE;
                objM8936constructorimpl2 = Result.m8936constructorimpl(GithubTag.INSTANCE.serializer());
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl2) == null) {
                obj3 = null;
            } else {
                try {
                    Result.Companion companion3 = Result.INSTANCE;
                    obj3 = null;
                    try {
                        objM8936constructorimpl2 = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(GithubTag.class), null, 2, null));
                    } catch (Throwable th2) {
                        th = th2;
                        Result.Companion companion4 = Result.INSTANCE;
                        objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                } catch (Throwable th3) {
                    th = th3;
                    obj3 = null;
                }
            }
            obj4 = objM8936constructorimpl2;
            if (Result.m8942isFailureimpl(obj4)) {
                obj4 = obj3;
            }
            kSerializer = (KSerializer) obj4;
            if (kSerializer != null) {
                try {
                    objDecodeFromString2 = MainAPIKt.getJson().decodeFromString(kSerializer, text);
                } catch (SerializationException e) {
                    ArchComponentExtKt.logError(e);
                    objDecodeFromString2 = MainAPIKt.getMapper().readValue(text, new TypeReference<GithubTag>() { // from class: com.lagradost.cloudstream3.utils.InAppUpdater$getPreReleaseUpdate$$inlined$parseJson$2
                    });
                } catch (Throwable unused) {
                    objDecodeFromString2 = MainAPIKt.getMapper().readValue(text, new TypeReference<GithubTag>() { // from class: com.lagradost.cloudstream3.utils.InAppUpdater$getPreReleaseUpdate$$inlined$parseJson$2
                    });
                }
            } else {
                objDecodeFromString2 = MainAPIKt.getMapper().readValue(text, new TypeReference<GithubTag>() { // from class: com.lagradost.cloudstream3.utils.InAppUpdater$getPreReleaseUpdate$$inlined$parseJson$2
                });
            }
            String strTake = StringsKt.take(StringsKt.trim((CharSequence) ((GithubTag) objDecodeFromString2).getGithubObject().getSha()).toString(), 7);
            Log.d(LOG_TAG, "Fetched GitHub tag: " + strTake);
            return new Update(!Intrinsics.areEqual(GitInfo.INSTANCE.currentCommitHash(activity3), strTake), githubAsset2.getBrowserDownloadUrl(), strTake, githubRelease.getBody(), githubRelease.getNodeId());
            Result.Companion companion5 = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(GithubRelease.class), GithubRelease.INSTANCE.serializer()));
            while (true) {
                if (!listIterator.hasPrevious()) {
                    obj2 = null;
                    break;
                }
                Object objPrevious = listIterator.previous();
                GithubRelease githubRelease2 = (GithubRelease) objPrevious;
                if (githubRelease2.getPrerelease() || Intrinsics.areEqual(githubRelease2.getTagName(), "pre-release")) {
                    obj2 = objPrevious;
                    break;
                }
            }
        } catch (Throwable th4) {
            Result.Companion companion6 = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th4));
        }
        String text2 = ((NiceResponse) obj5).getText();
        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) == null) {
            i = 2;
        } else {
            try {
                Result.Companion companion7 = Result.INSTANCE;
                i = 2;
                try {
                    objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(GithubRelease[].class), null, 2, null));
                } catch (Throwable th5) {
                    th = th5;
                    Result.Companion companion8 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                }
            } catch (Throwable th6) {
                th = th6;
                i = 2;
            }
        }
        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
            objM8936constructorimpl = null;
        }
        KSerializer kSerializer2 = (KSerializer) objM8936constructorimpl;
        if (kSerializer2 != null) {
            try {
                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer2, text2);
            } catch (SerializationException e2) {
                ArchComponentExtKt.logError(e2);
                objDecodeFromString = MainAPIKt.getMapper().readValue(text2, new TypeReference<GithubRelease[]>() { // from class: com.lagradost.cloudstream3.utils.InAppUpdater$getPreReleaseUpdate$$inlined$parseJson$1
                });
            } catch (Throwable unused2) {
                objDecodeFromString = MainAPIKt.getMapper().readValue(text2, new TypeReference<GithubRelease[]>() { // from class: com.lagradost.cloudstream3.utils.InAppUpdater$getPreReleaseUpdate$$inlined$parseJson$1
                });
            }
        } else {
            objDecodeFromString = MainAPIKt.getMapper().readValue(text2, new TypeReference<GithubRelease[]>() { // from class: com.lagradost.cloudstream3.utils.InAppUpdater$getPreReleaseUpdate$$inlined$parseJson$1
            });
        }
        List list = ArraysKt.toList((Object[]) objDecodeFromString);
        listIterator = list.listIterator(list.size());
        GithubRelease githubRelease3 = (GithubRelease) obj2;
        if (githubRelease3 == null || (assets = githubRelease3.getAssets()) == null) {
            githubAsset = null;
        } else {
            ArrayList arrayList = new ArrayList();
            for (Object obj7 : assets) {
                if (Intrinsics.areEqual(((GithubAsset) obj7).getContentType(), "application/vnd.android.package-archive")) {
                    arrayList.add(obj7);
                }
            }
            githubAsset = (GithubAsset) CollectionsKt.getOrNull(arrayList, 0);
        }
        if (githubAsset == null) {
            return new Update(false, null, null, null, null);
        }
        AppUtils appUtils2 = AppUtils.INSTANCE;
        Requests app3 = MainActivityKt.getApp();
        c09312.L$0 = activity2;
        c09312.L$1 = SpillingKt.nullOutSpilledVariable(str2);
        c09312.L$2 = SpillingKt.nullOutSpilledVariable(str);
        c09312.L$3 = SpillingKt.nullOutSpilledVariable(map);
        c09312.L$4 = SpillingKt.nullOutSpilledVariable(list);
        c09312.L$5 = githubRelease3;
        c09312.L$6 = githubAsset;
        c09312.L$7 = appUtils2;
        c09312.label = i;
        GithubAsset githubAsset3 = githubAsset;
        Activity activity5 = activity2;
        Object obj8 = Requests.get$default(app3, str2, map, null, null, null, false, 0, null, 0L, null, false, null, c09312, 4092, null);
        if (obj8 != obj) {
            githubRelease = githubRelease3;
            obj5 = obj8;
            activity3 = activity5;
            githubAsset2 = githubAsset3;
            text = ((NiceResponse) obj5).getText();
            Result.Companion companion9 = Result.INSTANCE;
            objM8936constructorimpl2 = Result.m8936constructorimpl(GithubTag.INSTANCE.serializer());
            if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl2) == null) {
                obj3 = null;
            } else {
                Result.Companion companion10 = Result.INSTANCE;
                obj3 = null;
                objM8936constructorimpl2 = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(GithubTag.class), null, 2, null));
            }
            obj4 = objM8936constructorimpl2;
            if (Result.m8942isFailureimpl(obj4)) {
                obj4 = obj3;
            }
            kSerializer = (KSerializer) obj4;
            if (kSerializer != null) {
                objDecodeFromString2 = MainAPIKt.getJson().decodeFromString(kSerializer, text);
            } else {
                objDecodeFromString2 = MainAPIKt.getMapper().readValue(text, new TypeReference<GithubTag>() { // from class: com.lagradost.cloudstream3.utils.InAppUpdater$getPreReleaseUpdate$$inlined$parseJson$2
                });
            }
            String strTake2 = StringsKt.take(StringsKt.trim((CharSequence) ((GithubTag) objDecodeFromString2).getGithubObject().getSha()).toString(), 7);
            Log.d(LOG_TAG, "Fetched GitHub tag: " + strTake2);
            return new Update(!Intrinsics.areEqual(GitInfo.INSTANCE.currentCommitHash(activity3), strTake2), githubAsset2.getBrowserDownloadUrl(), strTake2, githubRelease.getBody(), githubRelease.getNodeId());
        }
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    public final Object downloadUpdate(Activity activity, String str, Continuation<? super Boolean> continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        BufferedSink bufferedSinkBuffer;
        String str2;
        Activity activity2;
        Mutex mutex;
        String str3;
        String str4;
        File file;
        int i;
        Object obj;
        Mutex mutex2;
        Mutex mutex3;
        Requests app2;
        File file2;
        boolean z;
        AnonymousClass1 anonymousClass2;
        BufferedSink bufferedSink;
        Activity activity3;
        BufferedSink bufferedSink2;
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
        Object obj2 = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass1.label;
        try {
            try {
                try {
                    try {
                        if (i2 == 0) {
                            ResultKt.throwOnFailure(obj2);
                            Log.d(LOG_TAG, "Downloading update: " + str);
                            File[] fileArrListFiles = activity.getCacheDir().listFiles();
                            if (fileArrListFiles != null) {
                                ArrayList<File> arrayList = new ArrayList();
                                for (File file3 : fileArrListFiles) {
                                    String name = file3.getName();
                                    Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                                    if (StringsKt.startsWith$default(name, FcastManager.APP_PREFIX, false, 2, (Object) null)) {
                                        Intrinsics.checkNotNull(file3);
                                        if (Intrinsics.areEqual(FilesKt.getExtension(file3), "apk")) {
                                            arrayList.add(file3);
                                        }
                                    }
                                }
                                for (File file4 : arrayList) {
                                    MainActivity.Companion companion = MainActivity.INSTANCE;
                                    Intrinsics.checkNotNull(file4);
                                    companion.deleteFileOnExit(file4);
                                }
                            }
                            File fileCreateTempFile = File.createTempFile(FcastManager.APP_PREFIX, ".apk");
                            Intrinsics.checkNotNull(fileCreateTempFile);
                            bufferedSinkBuffer = Okio.buffer(Okio__JvmOkioKt.sink$default(fileCreateTempFile, false, 1, null));
                            Mutex mutex4 = updateLock;
                            anonymousClass1.L$0 = activity;
                            anonymousClass1.L$1 = str;
                            anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(FcastManager.APP_PREFIX);
                            anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable("apk");
                            anonymousClass1.L$4 = fileCreateTempFile;
                            anonymousClass1.L$5 = bufferedSinkBuffer;
                            anonymousClass1.L$6 = mutex4;
                            anonymousClass1.I$0 = 0;
                            anonymousClass1.label = 1;
                            if (mutex4.lock(null, anonymousClass1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            str2 = str;
                            activity2 = activity;
                            mutex = mutex4;
                            str3 = FcastManager.APP_PREFIX;
                            str4 = "apk";
                            file = fileCreateTempFile;
                            i = 0;
                        } else {
                            if (i2 != 1) {
                                if (i2 != 2) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                int i3 = anonymousClass1.I$1;
                                int i4 = anonymousClass1.I$0;
                                bufferedSink2 = (BufferedSink) anonymousClass1.L$7;
                                mutex3 = (Mutex) anonymousClass1.L$6;
                                BufferedSink bufferedSink3 = (BufferedSink) anonymousClass1.L$5;
                                File file5 = (File) anonymousClass1.L$4;
                                Activity activity4 = (Activity) anonymousClass1.L$0;
                                try {
                                    ResultKt.throwOnFailure(obj2);
                                    file2 = file5;
                                    z = true;
                                    bufferedSink = bufferedSink3;
                                    activity3 = activity4;
                                    obj = null;
                                    try {
                                        bufferedSink2.writeAll(((NiceResponse) obj2).getBody().getSource());
                                        bufferedSink.close();
                                        Uri uriFromFile = Uri.fromFile(file2);
                                        Intrinsics.checkNotNullExpressionValue(uriFromFile, "fromFile(...)");
                                        INSTANCE.openApk(activity3, uriFromFile);
                                        mutex3.unlock(obj);
                                        return Boxing.boxBoolean(z);
                                    } catch (Throwable th) {
                                        th = th;
                                        mutex3.unlock(obj);
                                        throw th;
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    obj = null;
                                    mutex3.unlock(obj);
                                    throw th;
                                }
                            }
                            int i5 = anonymousClass1.I$0;
                            Mutex mutex5 = (Mutex) anonymousClass1.L$6;
                            BufferedSink bufferedSink4 = (BufferedSink) anonymousClass1.L$5;
                            File file6 = (File) anonymousClass1.L$4;
                            String str5 = (String) anonymousClass1.L$3;
                            String str6 = (String) anonymousClass1.L$2;
                            String str7 = (String) anonymousClass1.L$1;
                            Activity activity5 = (Activity) anonymousClass1.L$0;
                            ResultKt.throwOnFailure(obj2);
                            mutex = mutex5;
                            bufferedSinkBuffer = bufferedSink4;
                            str2 = str7;
                            str3 = str6;
                            str4 = str5;
                            file = file6;
                            i = i5;
                            activity2 = activity5;
                        }
                        Object obj3 = Requests.get$default(app2, str2, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass2, 4094, null);
                        if (obj3 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        bufferedSink = bufferedSinkBuffer;
                        obj2 = obj3;
                        activity3 = activity2;
                        bufferedSink2 = bufferedSink;
                        mutex3 = mutex2;
                        bufferedSink2.writeAll(((NiceResponse) obj2).getBody().getSource());
                        bufferedSink.close();
                        Uri uriFromFile2 = Uri.fromFile(file2);
                        Intrinsics.checkNotNullExpressionValue(uriFromFile2, "fromFile(...)");
                        INSTANCE.openApk(activity3, uriFromFile2);
                        mutex3.unlock(obj);
                        return Boxing.boxBoolean(z);
                    } catch (Throwable th3) {
                        th = th3;
                        mutex3 = mutex2;
                        mutex3.unlock(obj);
                        throw th;
                    }
                    app2 = MainActivityKt.getApp();
                    anonymousClass1.L$0 = activity2;
                    anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(str2);
                    anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(str3);
                    anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(str4);
                    anonymousClass1.L$4 = file;
                    anonymousClass1.L$5 = bufferedSinkBuffer;
                    anonymousClass1.L$6 = mutex;
                    anonymousClass1.L$7 = bufferedSinkBuffer;
                    anonymousClass1.I$0 = i;
                    anonymousClass1.I$1 = 0;
                    anonymousClass1.label = 2;
                    file2 = file;
                    z = true;
                    mutex2 = mutex;
                    anonymousClass2 = anonymousClass1;
                    obj = null;
                } catch (Throwable th4) {
                    th = th4;
                    obj = null;
                    mutex2 = mutex;
                }
            } catch (Exception e) {
                e = e;
                ArchComponentExtKt.logError(e);
                return Boxing.boxBoolean(false);
            }
        } catch (Exception e2) {
            e = e2;
            ArchComponentExtKt.logError(e);
            return Boxing.boxBoolean(false);
        }
    }

    private final Unit openApk(final Context context, final Uri uri) {
        return (Unit) ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.utils.InAppUpdater$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return InAppUpdater.openApk$lambda$0(uri, context);
            }
        });
    }

    static final Unit openApk$lambda$0(Uri uri, Context context) {
        String path = uri.getPath();
        if (path == null) {
            return Unit.INSTANCE;
        }
        Uri uriForFile = FileProvider.getUriForFile(context, "com.lagradost.cloudstream3.provider", new File(path));
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(1);
        intent.addFlags(67108864);
        intent.putExtra("android.intent.extra.NOT_UNKNOWN_SOURCE", true);
        intent.setData(uriForFile);
        context.startActivity(intent);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.InAppUpdater$installPreReleaseIfNeeded$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppUpdater.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Landroid/app/Activity;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.InAppUpdater$installPreReleaseIfNeeded$1", f = "InAppUpdater.kt", i = {0}, l = {240}, m = "invokeSuspend", n = {"isInstalled"}, nl = {SJISContextAnalysis.HIRAGANA_LOWBYTE_END}, s = {"I$0"}, v = 2)
    static final class C09331 extends SuspendLambda implements Function3<CoroutineScope, Activity, Continuation<? super Unit>, Object> {
        final /* synthetic */ Activity $this_installPreReleaseIfNeeded;
        int I$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C09331(Activity activity, Continuation<? super C09331> continuation) {
            super(3, continuation);
            this.$this_installPreReleaseIfNeeded = activity;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, Activity activity, Continuation<? super Unit> continuation) {
            return new C09331(this.$this_installPreReleaseIfNeeded, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            int i;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                try {
                    this.$this_installPreReleaseIfNeeded.getPackageManager().getPackageInfo(InAppUpdater.PRERELEASE_PACKAGE_NAME, 0);
                    i = 1;
                } catch (PackageManager.NameNotFoundException unused) {
                    i = 0;
                }
                if (i != 0) {
                    CommonActivity.showToast$default(CommonActivity.INSTANCE, R.string.prerelease_already_installed, (Integer) null, 2, (Object) null);
                } else {
                    this.I$0 = i;
                    this.label = 1;
                    obj = InAppUpdater.INSTANCE.runAutoUpdate(this.$this_installPreReleaseIfNeeded, false, true, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            }
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            if (!((Boolean) obj).booleanValue()) {
                CommonActivity.showToast$default(CommonActivity.INSTANCE, R.string.prerelease_install_failed, (Integer) null, 2, (Object) null);
            }
            return Unit.INSTANCE;
        }
    }

    public final Job installPreReleaseIfNeeded(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        return Coroutines.INSTANCE.ioSafe(activity, new C09331(activity, null));
    }

    public static /* synthetic */ Object runAutoUpdate$default(InAppUpdater inAppUpdater, Activity activity, boolean z, boolean z2, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            z2 = false;
        }
        return inAppUpdater.runAutoUpdate(activity, z, z2, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object runAutoUpdate(final Activity activity, final boolean z, boolean z2, Continuation<? super Boolean> continuation) {
        C09341 c09341;
        final SharedPreferences sharedPreferences;
        if (continuation instanceof C09341) {
            c09341 = (C09341) continuation;
            if ((c09341.label & Integer.MIN_VALUE) != 0) {
                c09341.label -= Integer.MIN_VALUE;
            } else {
                c09341 = new C09341(continuation);
            }
        } else {
            c09341 = new C09341(continuation);
        }
        Object obj = c09341.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c09341.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
            boolean z3 = defaultSharedPreferences.getBoolean(activity.getString(R.string.auto_update_key), true);
            if (z && !z3) {
                return Boxing.boxBoolean(false);
            }
            c09341.L$0 = activity;
            c09341.L$1 = defaultSharedPreferences;
            c09341.Z$0 = z;
            c09341.Z$1 = z2;
            c09341.Z$2 = z3;
            c09341.label = 1;
            Object appUpdate = getAppUpdate(activity, z2, c09341);
            if (appUpdate == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj = appUpdate;
            sharedPreferences = defaultSharedPreferences;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            boolean z4 = c09341.Z$2;
            boolean z5 = c09341.Z$1;
            z = c09341.Z$0;
            sharedPreferences = (SharedPreferences) c09341.L$1;
            activity = (Activity) c09341.L$0;
            ResultKt.throwOnFailure(obj);
        }
        final Update update = (Update) obj;
        if (!update.getShouldUpdate() || update.getUpdateURL() == null) {
            return Boxing.boxBoolean(false);
        }
        if (StringsKt.equals$default(update.getUpdateNodeId(), sharedPreferences.getString(activity.getString(R.string.skip_update_key), ""), false, 2, null) && z) {
            return Boxing.boxBoolean(false);
        }
        activity.runOnUiThread(new Runnable() { // from class: com.lagradost.cloudstream3.utils.InAppUpdater$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.utils.InAppUpdater$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return InAppUpdater.runAutoUpdate$lambda$0$0(activity, update, z, sharedPreferences);
                    }
                });
            }
        });
        return Boxing.boxBoolean(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit runAutoUpdate$lambda$0$0(final Activity activity, final Update update, boolean z, final SharedPreferences sharedPreferences) {
        String packageName = activity.getPackageName();
        PackageInfo packageInfo = packageName != null ? activity.getPackageManager().getPackageInfo(packageName, 0) : null;
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.AlertDialogCustom);
        String string = activity.getString(R.string.new_update_format);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String str = String.format(string, Arrays.copyOf(new Object[]{packageInfo != null ? packageInfo.versionName : null, update.getUpdateVersion()}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        builder.setTitle(str);
        Regex regex = new Regex("\\[(.*?)]\\((.*?)\\)");
        String changelog = update.getChangelog();
        builder.setMessage(changelog != null ? regex.replace(changelog, new Function1() { // from class: com.lagradost.cloudstream3.utils.InAppUpdater$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return InAppUpdater.runAutoUpdate$lambda$0$0$1((MatchResult) obj);
            }
        }) : null);
        builder.setPositiveButton(R.string.update, new DialogInterface.OnClickListener() { // from class: com.lagradost.cloudstream3.utils.InAppUpdater$$ExternalSyntheticLambda4
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                InAppUpdater.runAutoUpdate$lambda$0$0$2$0(sharedPreferences, activity, update, builder, dialogInterface, i);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.lagradost.cloudstream3.utils.InAppUpdater$$ExternalSyntheticLambda5
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                InAppUpdater.runAutoUpdate$lambda$0$0$2$1(dialogInterface, i);
            }
        });
        if (z) {
            builder.setNeutralButton(R.string.skip_update, new DialogInterface.OnClickListener() { // from class: com.lagradost.cloudstream3.utils.InAppUpdater$$ExternalSyntheticLambda6
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    InAppUpdater.runAutoUpdate$lambda$0$0$2$2(sharedPreferences, activity, update, dialogInterface, i);
                }
            });
        }
        AppContextUtils appContextUtils = AppContextUtils.INSTANCE;
        AlertDialog alertDialogShow = builder.show();
        Intrinsics.checkNotNullExpressionValue(alertDialogShow, "show(...)");
        AppContextUtils.setDefaultFocus$default(appContextUtils, alertDialogShow, 0, 1, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence runAutoUpdate$lambda$0$0$1(MatchResult matchResult) {
        Intrinsics.checkNotNullParameter(matchResult, "matchResult");
        return matchResult.getGroupValues().get(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void runAutoUpdate$lambda$0$0$2$0(SharedPreferences sharedPreferences, Activity activity, Update update, AlertDialog.Builder builder, DialogInterface dialogInterface, int i) {
        ApkInstaller.DelayedInstaller delayedInstaller = ApkInstaller.INSTANCE.getDelayedInstaller();
        if (delayedInstaller == null || !delayedInstaller.startInstallation()) {
            CommonActivity.INSTANCE.showToast(R.string.download_started, (Integer) 1);
            if (sharedPreferences.getInt(activity.getString(R.string.apk_installer_key), -1) == -1 && INSTANCE.isMiUi()) {
                Intrinsics.checkNotNull(sharedPreferences);
                SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                editorEdit.putInt(activity.getString(R.string.apk_installer_key), 1);
                editorEdit.apply();
            }
            int i2 = sharedPreferences.getInt(activity.getString(R.string.apk_installer_key), 1);
            if (i2 == 0) {
                Activity activity2 = activity;
                ContextCompat.startForegroundService(activity2, PackageInstallerService.INSTANCE.getIntent(activity2, update.getUpdateURL()));
            } else {
                if (i2 != 1) {
                    return;
                }
                Coroutines.INSTANCE.ioSafe(builder, new InAppUpdater$runAutoUpdate$2$1$1$1$2(activity, update, null));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void runAutoUpdate$lambda$0$0$2$2(SharedPreferences sharedPreferences, Activity activity, Update update, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNull(sharedPreferences);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        String string = activity.getString(R.string.skip_update_key);
        String updateNodeId = update.getUpdateNodeId();
        if (updateNodeId == null) {
            updateNodeId = "";
        }
        editorEdit.putString(string, updateNodeId);
        editorEdit.apply();
    }

    private final boolean isMiUi() {
        String systemProperty = getSystemProperty("ro.miui.ui.version.name");
        return !(systemProperty == null || systemProperty.length() == 0);
    }

    private final String getSystemProperty(String propName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + propName).getInputStream()), 1024);
            try {
                String line = bufferedReader.readLine();
                CloseableKt.closeFinally(bufferedReader, null);
                return line;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(bufferedReader, th);
                    throw th2;
                }
            }
        } catch (IOException unused) {
            return null;
        }
    }
}
