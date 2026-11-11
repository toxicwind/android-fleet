package com.lagradost.cloudstream3.plugins;

import android.content.Context;
import coil3.util.UtilsKt;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsViewModelKt;
import com.lagradost.cloudstream3.ui.settings.extensions.RepositoryData;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.DataStore;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import com.lagradost.nicehttp.ResponseParser;
import j$.io.FileRetargetClass;
import j$.nio.file.Files;
import j$.nio.file.StandardCopyOption;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.AtomicMoveNotSupportedException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.ReferenceArraySerializer;
import kotlinx.serialization.modules.SerializersModule;
import org.mozilla.classfile.ByteCode;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: compiled from: RepositoryManager.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011H\u0007J\u000e\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0005J\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0013\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\u0015J\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0013\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\u0015J\u001c\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u001b\u001a\u00020\u0005H\u0082@¢\u0006\u0002\u0010\u0015J\u001e\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u00192\u0006\u0010\u001e\u001a\u00020\bH\u0086@¢\u0006\u0002\u0010\u001fJ2\u0010 \u001a\u0004\u0018\u00010\u00112\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010$\u001a\u0004\u0018\u00010\u0005H\u0086@¢\u0006\u0002\u0010%J\u0011\u0010&\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\nJ\u0016\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\bH\u0086@¢\u0006\u0002\u0010\u001fJ\u001e\u0010,\u001a\u00020*2\u0006\u0010!\u001a\u00020\"2\u0006\u0010+\u001a\u00020\bH\u0086@¢\u0006\u0002\u0010-R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R!\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/lagradost/cloudstream3/plugins/RepositoryManager;", "", "<init>", "()V", "ONLINE_PLUGINS_FOLDER", "", "PREBUILT_REPOSITORIES", "", "Lcom/lagradost/cloudstream3/ui/settings/extensions/RepositoryData;", "getPREBUILT_REPOSITORIES", "()[Lcom/lagradost/cloudstream3/ui/settings/extensions/RepositoryData;", "PREBUILT_REPOSITORIES$delegate", "Lkotlin/Lazy;", "GH_REGEX", "Lkotlin/text/Regex;", "sha256", UtilsKt.SCHEME_FILE, "Ljava/io/File;", "convertRawGitUrl", ImagesContract.URL, "parseRepoUrl", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "parseRepository", "Lcom/lagradost/cloudstream3/plugins/Repository;", "parsePlugins", "", "Lcom/lagradost/cloudstream3/plugins/SitePlugin;", "pluginUrls", "getRepoPlugins", "Lcom/lagradost/cloudstream3/plugins/PluginWrapper;", "repositoryData", "(Lcom/lagradost/cloudstream3/ui/settings/extensions/RepositoryData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downloadPluginToFile", "context", "Landroid/content/Context;", "pluginUrl", "expectedFileHash", "(Landroid/content/Context;Ljava/lang/String;Ljava/io/File;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRepositories", "repoLock", "Lkotlinx/coroutines/sync/Mutex;", "addRepository", "", "repository", "removeRepository", "(Landroid/content/Context;Lcom/lagradost/cloudstream3/ui/settings/extensions/RepositoryData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class RepositoryManager {
    public static final String ONLINE_PLUGINS_FOLDER = "Extensions";
    public static final RepositoryManager INSTANCE = new RepositoryManager();

    /* JADX INFO: renamed from: PREBUILT_REPOSITORIES$delegate, reason: from kotlin metadata */
    private static final Lazy PREBUILT_REPOSITORIES = LazyKt.lazy(new Function0() { // from class: com.lagradost.cloudstream3.plugins.RepositoryManager$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return RepositoryManager.PREBUILT_REPOSITORIES_delegate$lambda$0();
        }
    });
    private static final Regex GH_REGEX = new Regex("^https://raw.githubusercontent.com/([A-Za-z0-9-]+)/([A-Za-z0-9_.-]+)/(.*)$");
    private static final Mutex repoLock = MutexKt.Mutex$default(false, 1, null);

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.plugins.RepositoryManager$addRepository$1, reason: invalid class name */
    /* JADX INFO: compiled from: RepositoryManager.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.plugins.RepositoryManager", f = "RepositoryManager.kt", i = {0, 0, 0}, l = {284}, m = "addRepository", n = {"repository", "$this$withLock_u24default$iv", "$i$f$withLock"}, nl = {285}, s = {"L$0", "L$1", "I$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RepositoryManager.this.addRepository(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.plugins.RepositoryManager$getRepoPlugins$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RepositoryManager.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.plugins.RepositoryManager", f = "RepositoryManager.kt", i = {0, 1, 1}, l = {176, 177}, m = "getRepoPlugins", n = {"repositoryData", "repositoryData", "repo"}, nl = {177, 181}, s = {"L$0", "L$0", "L$1"}, v = 2)
    static final class C07581 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C07581(Continuation<? super C07581> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RepositoryManager.this.getRepoPlugins(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.plugins.RepositoryManager$parsePlugins$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RepositoryManager.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.plugins.RepositoryManager", f = "RepositoryManager.kt", i = {0}, l = {164}, m = "parsePlugins", n = {"pluginUrls"}, nl = {165}, s = {"L$0"}, v = 2)
    static final class C07591 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C07591(Continuation<? super C07591> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RepositoryManager.this.parsePlugins(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.plugins.RepositoryManager$removeRepository$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RepositoryManager.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.plugins.RepositoryManager", f = "RepositoryManager.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1}, l = {284, 275}, m = "removeRepository", n = {"context", "repository", "extensionsDir", "$this$withLock_u24default$iv", "$i$f$withLock", "context", "repository", "extensionsDir", UtilsKt.SCHEME_FILE}, nl = {285, 276}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "L$0", "L$1", "L$2", "L$3"}, v = 2)
    static final class C07611 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C07611(Continuation<? super C07611> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RepositoryManager.this.removeRepository(null, null, this);
        }
    }

    private RepositoryManager() {
    }

    public final RepositoryData[] getPREBUILT_REPOSITORIES() {
        return (RepositoryData[]) PREBUILT_REPOSITORIES.getValue();
    }

    static final RepositoryData[] PREBUILT_REPOSITORIES_delegate$lambda$0() {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Context context = CloudStreamApp.INSTANCE.getContext();
        Object obj = null;
        if (context != null) {
            try {
                String string = DataStore.INSTANCE.getSharedPrefs(context).getString("PREBUILT_REPOSITORIES", null);
                if (string != null) {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(RepositoryData.class), RepositoryData.INSTANCE.serializer()));
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                        try {
                            Result.Companion companion3 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(RepositoryData[].class), null, 2, null));
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
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<RepositoryData[]>() { // from class: com.lagradost.cloudstream3.plugins.RepositoryManager$PREBUILT_REPOSITORIES_delegate$lambda$0$$inlined$getKey$1
                            });
                        } catch (Throwable unused) {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<RepositoryData[]>() { // from class: com.lagradost.cloudstream3.plugins.RepositoryManager$PREBUILT_REPOSITORIES_delegate$lambda$0$$inlined$getKey$1
                            });
                        }
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<RepositoryData[]>() { // from class: com.lagradost.cloudstream3.plugins.RepositoryManager$PREBUILT_REPOSITORIES_delegate$lambda$0$$inlined$getKey$1
                        });
                    }
                    obj = objDecodeFromString;
                }
            } catch (Exception unused2) {
            }
        }
        RepositoryData[] repositoryDataArr = (RepositoryData[]) obj;
        return repositoryDataArr == null ? new RepositoryData[0] : repositoryDataArr;
    }

    public final String sha256(File file) throws NoSuchAlgorithmException {
        Intrinsics.checkNotNullParameter(file, "file");
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            FileInputStream fileInputStream2 = fileInputStream;
            byte[] bArr = new byte[8192];
            for (int i = fileInputStream2.read(bArr); i != -1; i = fileInputStream2.read(bArr)) {
                messageDigest.update(bArr, 0, i);
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(fileInputStream, null);
            StringBuilder sb = new StringBuilder("sha256-");
            byte[] bArrDigest = messageDigest.digest();
            Intrinsics.checkNotNullExpressionValue(bArrDigest, "digest(...)");
            sb.append(ArraysKt.joinToString$default(bArrDigest, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.lagradost.cloudstream3.plugins.RepositoryManager$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return RepositoryManager.sha256$lambda$1(((Byte) obj).byteValue());
                }
            }, 30, (Object) null));
            return sb.toString();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(fileInputStream, th);
                throw th2;
            }
        }
    }

    static final CharSequence sha256$lambda$1(byte b) {
        String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0078  */
    /* JADX WARN: Code duplicated, block: B:29:0x008e A[Catch: Exception -> 0x00a0, TRY_LEAVE, TryCatch #0 {Exception -> 0x00a0, blocks: (B:5:0x0025, B:8:0x0031, B:13:0x0047, B:20:0x0072, B:23:0x0079, B:29:0x008e, B:28:0x0089, B:19:0x0068, B:12:0x003d, B:16:0x004e, B:25:0x007d, B:9:0x0033), top: B:42:0x0025, inners: #1, #2, #3, #5 }] */
    /* JADX WARN: Code duplicated, block: B:47:0x007d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public final String convertRawGitUrl(String url) {
        Object objDecodeFromString;
        MatchResult matchResultFind$default;
        Object objM8936constructorimpl;
        KSerializer kSerializer;
        Intrinsics.checkNotNullParameter(url, "url");
        CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
        Context context = CloudStreamApp.INSTANCE.getContext();
        Intrinsics.checkNotNull(context);
        String string = context.getString(R.string.jsdelivr_proxy_key);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        Context context2 = companion.getContext();
        if (context2 != null) {
            try {
                String string2 = DataStore.INSTANCE.getSharedPrefs(context2).getString(string, null);
                if (string2 == null) {
                    objDecodeFromString = null;
                } else {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(BooleanSerializer.INSTANCE);
                    } catch (Throwable th) {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) == null) {
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            try {
                                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string2);
                            } catch (SerializationException e) {
                                ArchComponentExtKt.logError(e);
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string2, new TypeReference<Boolean>() { // from class: com.lagradost.cloudstream3.plugins.RepositoryManager$convertRawGitUrl$$inlined$getKey$1
                                });
                            } catch (Throwable unused) {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string2, new TypeReference<Boolean>() { // from class: com.lagradost.cloudstream3.plugins.RepositoryManager$convertRawGitUrl$$inlined$getKey$1
                                });
                            }
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string2, new TypeReference<Boolean>() { // from class: com.lagradost.cloudstream3.plugins.RepositoryManager$convertRawGitUrl$$inlined$getKey$1
                            });
                        }
                    } else {
                        try {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(Boolean.class), null, 2, null));
                        } catch (Throwable th2) {
                            Result.Companion companion5 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                        }
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string2);
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string2, new TypeReference<Boolean>() { // from class: com.lagradost.cloudstream3.plugins.RepositoryManager$convertRawGitUrl$$inlined$getKey$1
                            });
                        }
                    }
                }
            } catch (Exception unused2) {
            }
        } else {
            objDecodeFromString = null;
        }
        if (!Intrinsics.areEqual(objDecodeFromString, (Object) true) || (matchResultFind$default = Regex.find$default(GH_REGEX, url, 0, 2, null)) == null) {
            return url;
        }
        MatchResult.Destructured destructured = matchResultFind$default.getDestructured();
        return "https://cdn.jsdelivr.net/gh/" + destructured.getMatch().getGroupValues().get(1) + '/' + destructured.getMatch().getGroupValues().get(2) + '@' + destructured.getMatch().getGroupValues().get(3);
    }

    public final Object parseRepoUrl(String str, Continuation<? super String> continuation) {
        String string = StringsKt.trim((CharSequence) str).toString();
        String str2 = string;
        if (new Regex("^https?://").containsMatchIn(str2)) {
            return string;
        }
        if (new Regex("^(cloudstreamrepo://)|(https://cs\\.repo/\\??)").containsMatchIn(str2)) {
            String strReplace = new Regex("^(cloudstreamrepo://)|(https://cs\\.repo/\\??)").replace(str2, "");
            if (new Regex("^https?://").containsMatchIn(strReplace)) {
                return string;
            }
            return Utils.HTTPS + strReplace;
        }
        if (new Regex("^[a-zA-Z0-9!_-]+$").matches(str2)) {
            return ArchComponentExtKt.safeAsync(new AnonymousClass3(string, null), continuation);
        }
        return null;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.plugins.RepositoryManager$parseRepoUrl$3, reason: invalid class name */
    /* JADX INFO: compiled from: RepositoryManager.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.plugins.RepositoryManager$parseRepoUrl$3", f = "RepositoryManager.kt", i = {}, l = {144}, m = "invokeSuspend", n = {}, nl = {145}, s = {}, v = 2)
    static final class AnonymousClass3 extends SuspendLambda implements Function1<Continuation<? super String>, Object> {
        final /* synthetic */ String $fixedUrl;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(String str, Continuation<? super AnonymousClass3> continuation) {
            super(1, continuation);
            this.$fixedUrl = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass3(this.$fixedUrl, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super String> continuation) {
            return ((AnonymousClass3) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object obj2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Requests app2 = MainActivityKt.getApp();
                this.label = 1;
                obj2 = Requests.get$default(app2, "https://cutt.ly/" + this.$fixedUrl, null, null, null, null, false, 0, null, 0L, null, false, null, this, 4062, null);
                if (obj2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                obj2 = obj;
            }
            String str = ((NiceResponse) obj2).getHeaders().get("Location");
            if (str == null || StringsKt.startsWith$default(str, "https://cutt.ly/404", false, 2, (Object) null) || Intrinsics.areEqual(StringsKt.removeSuffix(str, (CharSequence) "/"), "https://cutt.ly")) {
                return null;
            }
            return str;
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.plugins.RepositoryManager$parseRepository$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RepositoryManager.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n"}, d2 = {"<anonymous>", "Lcom/lagradost/cloudstream3/plugins/Repository;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.plugins.RepositoryManager$parseRepository$2", f = "RepositoryManager.kt", i = {}, l = {156}, m = "invokeSuspend", n = {}, nl = {157}, s = {}, v = 2)
    static final class C07602 extends SuspendLambda implements Function1<Continuation<? super Repository>, Object> {
        final /* synthetic */ String $url;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07602(String str, Continuation<? super C07602> continuation) {
            super(1, continuation);
            this.$url = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new C07602(this.$url, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Repository> continuation) {
            return ((C07602) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object obj2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj2 = Requests.get$default(MainActivityKt.getApp(), RepositoryManager.INSTANCE.convertRawGitUrl(this.$url), null, null, null, null, false, 5, TimeUnit.MINUTES, 0L, null, false, null, this, 3902, null);
                if (obj2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                obj2 = obj;
            }
            NiceResponse niceResponse = (NiceResponse) obj2;
            try {
                ResponseParser parser = niceResponse.getParser();
                Intrinsics.checkNotNull(parser);
                return parser.parseSafe(niceResponse.getText(), Reflection.getOrCreateKotlinClass(Repository.class));
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public final Object parseRepository(String str, Continuation<? super Repository> continuation) {
        return ArchComponentExtKt.safeAsync(new C07602(str, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public final Object parsePlugins(String str, Continuation<? super List<SitePlugin>> continuation) {
        C07591 c07591;
        if (continuation instanceof C07591) {
            c07591 = (C07591) continuation;
            if ((c07591.label & Integer.MIN_VALUE) != 0) {
                c07591.label -= Integer.MIN_VALUE;
            } else {
                c07591 = new C07591(continuation);
            }
        } else {
            c07591 = new C07591(continuation);
        }
        Object obj = c07591.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07591.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Requests app2 = MainActivityKt.getApp();
                String strConvertRawGitUrl = convertRawGitUrl(str);
                TimeUnit timeUnit = TimeUnit.MINUTES;
                c07591.L$0 = SpillingKt.nullOutSpilledVariable(str);
                c07591.label = 1;
                Object obj2 = Requests.get$default(app2, strConvertRawGitUrl, null, null, null, null, false, 5, timeUnit, 0L, null, false, null, c07591, 3902, null);
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
            NiceResponse niceResponse = (NiceResponse) obj;
            ResponseParser parser = niceResponse.getParser();
            Intrinsics.checkNotNull(parser);
            return ArraysKt.toList((Object[]) parser.parse(niceResponse.getText(), Reflection.getOrCreateKotlinClass(SitePlugin[].class)));
        } catch (Throwable th) {
            ArchComponentExtKt.logError(th);
            return CollectionsKt.emptyList();
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x007b, code lost:
    
        if (r7 == r1) goto L24;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getRepoPlugins(com.lagradost.cloudstream3.ui.settings.extensions.RepositoryData r6, kotlin.coroutines.Continuation<? super java.util.List<com.lagradost.cloudstream3.plugins.PluginWrapper>> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.lagradost.cloudstream3.plugins.RepositoryManager.C07581
            if (r0 == 0) goto L14
            r0 = r7
            com.lagradost.cloudstream3.plugins.RepositoryManager$getRepoPlugins$1 r0 = (com.lagradost.cloudstream3.plugins.RepositoryManager.C07581) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            com.lagradost.cloudstream3.plugins.RepositoryManager$getRepoPlugins$1 r0 = new com.lagradost.cloudstream3.plugins.RepositoryManager$getRepoPlugins$1
            r0.<init>(r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L46
            if (r2 == r4) goto L3d
            if (r2 != r3) goto L35
            java.lang.Object r5 = r0.L$1
            com.lagradost.cloudstream3.plugins.Repository r5 = (com.lagradost.cloudstream3.plugins.Repository) r5
            java.lang.Object r5 = r0.L$0
            com.lagradost.cloudstream3.ui.settings.extensions.RepositoryData r5 = (com.lagradost.cloudstream3.ui.settings.extensions.RepositoryData) r5
            kotlin.ResultKt.throwOnFailure(r7)
            goto L7e
        L35:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3d:
            java.lang.Object r5 = r0.L$0
            r6 = r5
            com.lagradost.cloudstream3.ui.settings.extensions.RepositoryData r6 = (com.lagradost.cloudstream3.ui.settings.extensions.RepositoryData) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L58
        L46:
            kotlin.ResultKt.throwOnFailure(r7)
            java.lang.String r7 = r6.getUrl()
            r0.L$0 = r6
            r0.label = r4
            java.lang.Object r7 = r5.parseRepository(r7, r0)
            if (r7 != r1) goto L58
            goto L7d
        L58:
            com.lagradost.cloudstream3.plugins.Repository r7 = (com.lagradost.cloudstream3.plugins.Repository) r7
            r5 = 0
            if (r7 != 0) goto L5e
            return r5
        L5e:
            java.util.List r2 = r7.getPluginLists()
            com.lagradost.cloudstream3.plugins.RepositoryManager$getRepoPlugins$list$1 r4 = new com.lagradost.cloudstream3.plugins.RepositoryManager$getRepoPlugins$list$1
            r4.<init>(r7, r6, r5)
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)
            r0.L$0 = r5
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r7 = com.lagradost.cloudstream3.ParCollectionsKt.amap(r2, r4, r0)
            if (r7 != r1) goto L7e
        L7d:
            return r1
        L7e:
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.util.List r5 = kotlin.collections.CollectionsKt.flatten(r7)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.plugins.RepositoryManager.getRepoPlugins(com.lagradost.cloudstream3.ui.settings.extensions.RepositoryData, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.plugins.RepositoryManager$downloadPluginToFile$2, reason: invalid class name */
    /* JADX INFO: compiled from: RepositoryManager.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n"}, d2 = {"<anonymous>", "Ljava/io/File;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.plugins.RepositoryManager$downloadPluginToFile$2", f = "RepositoryManager.kt", i = {0, 0}, l = {ByteCode.IFNULL}, m = "invokeSuspend", n = {"parentDir", "tempFile"}, nl = {200}, s = {"L$0", "L$1"}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function1<Continuation<? super File>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ String $expectedFileHash;
        final /* synthetic */ File $file;
        final /* synthetic */ String $pluginUrl;
        Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(File file, Context context, String str, String str2, Continuation<? super AnonymousClass2> continuation) {
            super(1, continuation);
            this.$file = file;
            this.$context = context;
            this.$pluginUrl = str;
            this.$expectedFileHash = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass2(this.$file, this.$context, this.$pluginUrl, this.$expectedFileHash, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super File> continuation) {
            return ((AnonymousClass2) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws NoSuchAlgorithmException, IOException {
            Object obj2;
            File file;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                File parentFile = this.$file.getParentFile();
                if (parentFile == null) {
                    return null;
                }
                parentFile.mkdirs();
                File fileCreateTempFile = File.createTempFile(this.$file.getName(), ".tmp", this.$context.getCacheDir());
                this.L$0 = SpillingKt.nullOutSpilledVariable(parentFile);
                this.L$1 = fileCreateTempFile;
                this.label = 1;
                obj2 = Requests.get$default(MainActivityKt.getApp(), RepositoryManager.INSTANCE.convertRawGitUrl(this.$pluginUrl), null, null, null, null, false, 0, null, 0L, null, false, null, this, 4094, null);
                if (obj2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                file = fileCreateTempFile;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                file = (File) this.L$1;
                ResultKt.throwOnFailure(obj);
                obj2 = obj;
            }
            InputStream inputStreamByteStream = ((NiceResponse) obj2).getOkhttpResponse().body().byteStream();
            try {
                InputStream inputStream = inputStreamByteStream;
                Intrinsics.checkNotNull(file);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                try {
                    ByteStreamsKt.copyTo$default(inputStream, fileOutputStream, 0, 2, null);
                    CloseableKt.closeFinally(fileOutputStream, null);
                    CloseableKt.closeFinally(inputStreamByteStream, null);
                    if (this.$expectedFileHash != null) {
                        String strSha256 = RepositoryManager.INSTANCE.sha256(file);
                        if (!Intrinsics.areEqual(this.$expectedFileHash, strSha256)) {
                            file.delete();
                            throw new IllegalStateException("Extension hash mismatch when validating '" + this.$file.getName() + "'! Expected: '" + this.$expectedFileHash + "', got: '" + strSha256 + "'.");
                        }
                    }
                    try {
                        Files.move(FileRetargetClass.toPath(file), FileRetargetClass.toPath(this.$file), StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
                    } catch (AtomicMoveNotSupportedException unused) {
                        Files.move(FileRetargetClass.toPath(file), FileRetargetClass.toPath(this.$file), StandardCopyOption.REPLACE_EXISTING);
                    }
                    return this.$file;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(fileOutputStream, th);
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                try {
                    throw th3;
                } catch (Throwable th4) {
                    CloseableKt.closeFinally(inputStreamByteStream, th3);
                    throw th4;
                }
            }
        }
    }

    public final Object downloadPluginToFile(Context context, String str, File file, String str2, Continuation<? super File> continuation) {
        return ArchComponentExtKt.safeAsync(new AnonymousClass2(file, context, str, str2, null), continuation);
    }

    public final RepositoryData[] getRepositories() {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Context context = CloudStreamApp.INSTANCE.getContext();
        Object obj = null;
        if (context != null) {
            try {
                String string = DataStore.INSTANCE.getSharedPrefs(context).getString(ExtensionsViewModelKt.REPOSITORIES_KEY, null);
                if (string != null) {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(RepositoryData.class), RepositoryData.INSTANCE.serializer()));
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                        try {
                            Result.Companion companion3 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(RepositoryData[].class), null, 2, null));
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
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<RepositoryData[]>() { // from class: com.lagradost.cloudstream3.plugins.RepositoryManager$getRepositories$$inlined$getKey$1
                            });
                        } catch (Throwable unused) {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<RepositoryData[]>() { // from class: com.lagradost.cloudstream3.plugins.RepositoryManager$getRepositories$$inlined$getKey$1
                            });
                        }
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<RepositoryData[]>() { // from class: com.lagradost.cloudstream3.plugins.RepositoryManager$getRepositories$$inlined$getKey$1
                        });
                    }
                    obj = objDecodeFromString;
                }
            } catch (Exception unused2) {
            }
        }
        RepositoryData[] repositoryDataArr = (RepositoryData[]) obj;
        return repositoryDataArr == null ? new RepositoryData[0] : repositoryDataArr;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object addRepository(RepositoryData repositoryData, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        Mutex mutex;
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
            mutex = repoLock;
            anonymousClass1.L$0 = repositoryData;
            anonymousClass1.L$1 = mutex;
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
            Mutex mutex2 = (Mutex) anonymousClass1.L$1;
            RepositoryData repositoryData2 = (RepositoryData) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj);
            mutex = mutex2;
            repositoryData = repositoryData2;
        }
        try {
            RepositoryData[] repositories = INSTANCE.getRepositories();
            CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
            Object[] objArrPlus = ArraysKt.plus(repositories, repositoryData);
            HashSet hashSet = new HashSet();
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : objArrPlus) {
                if (hashSet.add(((RepositoryData) obj2).getUrl())) {
                    arrayList.add(obj2);
                }
            }
            companion.setKey(ExtensionsViewModelKt.REPOSITORIES_KEY, arrayList);
            Unit unit = Unit.INSTANCE;
            return Unit.INSTANCE;
        } finally {
            mutex.unlock(null);
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0193, code lost:
    
        if (r2.deleteRepositoryData(r4, r0) == r15) goto L62;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object removeRepository(android.content.Context r13, com.lagradost.cloudstream3.ui.settings.extensions.RepositoryData r14, kotlin.coroutines.Continuation<? super kotlin.Unit> r15) {
        /*
            Method dump skipped, instruction units count: 414
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.plugins.RepositoryManager.removeRepository(android.content.Context, com.lagradost.cloudstream3.ui.settings.extensions.RepositoryData, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean removeRepository$lambda$1$0(File plugin) {
        Intrinsics.checkNotNullParameter(plugin, "plugin");
        PluginManager pluginManager = PluginManager.INSTANCE;
        String absolutePath = plugin.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
        pluginManager.unloadPlugin(absolutePath);
        return false;
    }
}
