package com.lagradost.cloudstream3.plugins;

import android.content.Context;
import android.util.Log;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.DataStore;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import com.lagradost.nicehttp.ResponseParser;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.Charsets;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.modules.SerializersModule;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;

/* JADX INFO: compiled from: VotingApi.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0019B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0002J\u0012\u0010\t\u001a\u00020\n*\u00020\u000bH\u0086@¢\u0006\u0002\u0010\fJ\n\u0010\r\u001a\u00020\u000e*\u00020\u000bJ\u0012\u0010\u000f\u001a\u00020\n*\u00020\u000bH\u0086@¢\u0006\u0002\u0010\fJ\n\u0010\u0010\u001a\u00020\u000e*\u00020\u000bJ\u0016\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0005H\u0082@¢\u0006\u0002\u0010\u0015J\u0016\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0005H\u0082@¢\u0006\u0002\u0010\u0015J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\u0015J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0005J\u000e\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0005J\u0016\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n0\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/lagradost/cloudstream3/plugins/VotingApi;", "", "<init>", "()V", "LOGKEY", "", "API_DOMAIN", "transformUrl", ImagesContract.URL, "getVotes", "", "Lcom/lagradost/cloudstream3/plugins/SitePlugin;", "(Lcom/lagradost/cloudstream3/plugins/SitePlugin;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hasVoted", "", "vote", "canVote", "votesCache", "", "readVote", "pluginUrl", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeVote", "voteLock", "Lkotlinx/coroutines/sync/Mutex;", "CountifyResult", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class VotingApi {
    private static final String API_DOMAIN = "https://api.countify.xyz";
    private static final String LOGKEY = "VotingApi";
    public static final VotingApi INSTANCE = new VotingApi();
    private static final Map<String, Integer> votesCache = new LinkedHashMap();
    private static final Mutex voteLock = MutexKt.Mutex$default(false, 1, null);

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.plugins.VotingApi$getVotes$2, reason: invalid class name */
    /* JADX INFO: compiled from: VotingApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.plugins.VotingApi", f = "VotingApi.kt", i = {0}, l = {51}, m = "getVotes", n = {"pluginUrl"}, nl = {52}, s = {"L$0"}, v = 2)
    static final class AnonymousClass2 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return VotingApi.this.getVotes((String) null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.plugins.VotingApi$readVote$1, reason: invalid class name */
    /* JADX INFO: compiled from: VotingApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.plugins.VotingApi", f = "VotingApi.kt", i = {0, 0, 0}, l = {39}, m = "readVote", n = {"pluginUrl", "id", ImagesContract.URL}, nl = {103}, s = {"L$0", "L$1", "L$2"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return VotingApi.this.readVote(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.plugins.VotingApi$vote$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: VotingApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.plugins.VotingApi", f = "VotingApi.kt", i = {0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4}, l = {108, 73, 84, 87, 92}, m = "vote", n = {"pluginUrl", "$this$withLock_u24default$iv", "$i$f$withLock", "pluginUrl", "$this$withLock_u24default$iv", "$i$f$withLock", "$i$a$-withLock$default-VotingApi$vote$3", "pluginUrl", "$this$withLock_u24default$iv", "$i$f$withLock", "$i$a$-withLock$default-VotingApi$vote$3", "pluginUrl", "$this$withLock_u24default$iv", "$i$f$withLock", "$i$a$-withLock$default-VotingApi$vote$3", "pluginUrl", "$this$withLock_u24default$iv", "$i$f$withLock", "$i$a$-withLock$default-VotingApi$vote$3"}, nl = {109, 112, 112, 88, 112}, s = {"L$0", "L$1", "I$0", "L$0", "L$1", "I$0", "I$1", "L$0", "L$1", "I$0", "I$1", "L$0", "L$1", "I$0", "I$1", "L$0", "L$1", "I$0", "I$1"}, v = 2)
    static final class C07622 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C07622(Continuation<? super C07622> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return VotingApi.this.vote((String) null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.plugins.VotingApi$writeVote$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: VotingApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.plugins.VotingApi", f = "VotingApi.kt", i = {0, 0, 0}, l = {46}, m = "writeVote", n = {"pluginUrl", "id", ImagesContract.URL}, nl = {47}, s = {"L$0", "L$1", "L$2"}, v = 2)
    static final class C07631 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C07631(Continuation<? super C07631> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return VotingApi.this.writeVote(null, this);
        }
    }

    private VotingApi() {
    }

    private final String transformUrl(String url) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = (url + "#funny-salt").getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] bArrDigest = messageDigest.digest(bytes);
        Intrinsics.checkNotNullExpressionValue(bArrDigest, "digest(...)");
        String string = "";
        for (byte b : bArrDigest) {
            StringBuilder sb = new StringBuilder();
            sb.append(string);
            String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            sb.append(str);
            string = sb.toString();
        }
        return string;
    }

    public final Object getVotes(SitePlugin sitePlugin, Continuation<? super Integer> continuation) {
        return getVotes(sitePlugin.getUrl(), continuation);
    }

    public final boolean hasVoted(SitePlugin sitePlugin) {
        Intrinsics.checkNotNullParameter(sitePlugin, "<this>");
        return hasVoted(sitePlugin.getUrl());
    }

    public final Object vote(SitePlugin sitePlugin, Continuation<? super Integer> continuation) {
        return vote(sitePlugin.getUrl(), continuation);
    }

    public final boolean canVote(SitePlugin sitePlugin) {
        Intrinsics.checkNotNullParameter(sitePlugin, "<this>");
        return canVote(sitePlugin.getUrl());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public final Object readVote(String str, Continuation<? super Integer> continuation) throws NoSuchAlgorithmException {
        AnonymousClass1 anonymousClass1;
        Object safe;
        Integer count;
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
            String strTransformUrl = transformUrl(str);
            String str2 = "https://api.countify.xyz/get-total/" + strTransformUrl;
            Log.d(LOGKEY, "Requesting GET: " + str2);
            Requests app2 = MainActivityKt.getApp();
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(str);
            anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(strTransformUrl);
            anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(str2);
            anonymousClass1.label = 1;
            Object obj2 = Requests.get$default(app2, str2, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass1, 4094, null);
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
        try {
            ResponseParser parser = niceResponse.getParser();
            Intrinsics.checkNotNull(parser);
            safe = parser.parseSafe(niceResponse.getText(), Reflection.getOrCreateKotlinClass(CountifyResult.class));
        } catch (Exception e) {
            e.printStackTrace();
            safe = null;
        }
        CountifyResult countifyResult = (CountifyResult) safe;
        return Boxing.boxInt((countifyResult == null || (count = countifyResult.getCount()) == null) ? 0 : count.intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public final Object writeVote(String str, Continuation<? super Boolean> continuation) throws NoSuchAlgorithmException {
        C07631 c07631;
        boolean z;
        Object safe;
        if (continuation instanceof C07631) {
            c07631 = (C07631) continuation;
            if ((c07631.label & Integer.MIN_VALUE) != 0) {
                c07631.label -= Integer.MIN_VALUE;
            } else {
                c07631 = new C07631(continuation);
            }
        } else {
            c07631 = new C07631(continuation);
        }
        Object obj = c07631.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07631.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            String strTransformUrl = transformUrl(str);
            String str2 = "https://api.countify.xyz/increment/" + strTransformUrl;
            Log.d(LOGKEY, "Requesting POST: " + str2);
            Requests app2 = MainActivityKt.getApp();
            Map mapEmptyMap = MapsKt.emptyMap();
            c07631.L$0 = SpillingKt.nullOutSpilledVariable(str);
            c07631.L$1 = SpillingKt.nullOutSpilledVariable(strTransformUrl);
            c07631.L$2 = SpillingKt.nullOutSpilledVariable(str2);
            c07631.label = 1;
            C07631 c07632 = c07631;
            z = true;
            Object objPost$default = Requests.post$default(app2, str2, mapEmptyMap, null, null, null, null, null, null, null, false, 0, null, 0L, null, false, null, c07632, 65532, null);
            if (objPost$default == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj = objPost$default;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            z = true;
        }
        NiceResponse niceResponse = (NiceResponse) obj;
        try {
            ResponseParser parser = niceResponse.getParser();
            Intrinsics.checkNotNull(parser);
            safe = parser.parseSafe(niceResponse.getText(), Reflection.getOrCreateKotlinClass(CountifyResult.class));
        } catch (Exception e) {
            e.printStackTrace();
            safe = null;
        }
        CountifyResult countifyResult = (CountifyResult) safe;
        return Boxing.boxBoolean((countifyResult != null ? countifyResult.getCount() : null) != null ? z : false);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object getVotes(String str, Continuation<? super Integer> continuation) throws NoSuchAlgorithmException {
        AnonymousClass2 anonymousClass2;
        int iIntValue;
        if (continuation instanceof AnonymousClass2) {
            anonymousClass2 = (AnonymousClass2) continuation;
            if ((anonymousClass2.label & Integer.MIN_VALUE) != 0) {
                anonymousClass2.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass2 = new AnonymousClass2(continuation);
            }
        } else {
            anonymousClass2 = new AnonymousClass2(continuation);
        }
        Object vote = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass2.label;
        if (i == 0) {
            ResultKt.throwOnFailure(vote);
            Integer num = votesCache.get(str);
            if (num != null) {
                iIntValue = num.intValue();
            } else {
                anonymousClass2.L$0 = str;
                anonymousClass2.label = 1;
                vote = readVote(str, anonymousClass2);
                if (vote == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Boxing.boxInt(iIntValue);
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        str = (String) anonymousClass2.L$0;
        ResultKt.throwOnFailure(vote);
        Number number = (Number) vote;
        votesCache.put(str, Boxing.boxInt(number.intValue()));
        iIntValue = number.intValue();
        return Boxing.boxInt(iIntValue);
    }

    public final boolean hasVoted(String pluginUrl) {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Intrinsics.checkNotNullParameter(pluginUrl, "pluginUrl");
        CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
        String str = "cs3-votes/" + transformUrl(pluginUrl);
        Context context = companion.getContext();
        Object obj = null;
        if (context != null) {
            try {
                String string = DataStore.INSTANCE.getSharedPrefs(context).getString(str, null);
                if (string != null) {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(BooleanSerializer.INSTANCE);
                    } catch (Throwable th) {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                        try {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(Boolean.class), null, 2, null));
                        } catch (Throwable th2) {
                            Result.Companion companion5 = Result.INSTANCE;
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
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Boolean>() { // from class: com.lagradost.cloudstream3.plugins.VotingApi$hasVoted$$inlined$getKey$1
                            });
                        } catch (Throwable unused) {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Boolean>() { // from class: com.lagradost.cloudstream3.plugins.VotingApi$hasVoted$$inlined$getKey$1
                            });
                        }
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Boolean>() { // from class: com.lagradost.cloudstream3.plugins.VotingApi$hasVoted$$inlined$getKey$1
                        });
                    }
                    obj = objDecodeFromString;
                }
            } catch (Exception unused2) {
            }
        }
        Boolean bool = (Boolean) obj;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public final boolean canVote(String pluginUrl) {
        Intrinsics.checkNotNullParameter(pluginUrl, "pluginUrl");
        return PluginManager.INSTANCE.getUrlPlugins().containsKey(pluginUrl);
    }

    /* JADX WARN: Code duplicated, block: B:60:0x012f A[Catch: all -> 0x0088, TryCatch #1 {all -> 0x0088, blocks: (B:16:0x0042, B:58:0x0127, B:60:0x012f, B:62:0x0154, B:63:0x0159, B:64:0x0160, B:26:0x0073, B:29:0x0084), top: B:75:0x002a }] */
    /* JADX WARN: Code duplicated, block: B:62:0x0154 A[Catch: all -> 0x0088, TryCatch #1 {all -> 0x0088, blocks: (B:16:0x0042, B:58:0x0127, B:60:0x012f, B:62:0x0154, B:63:0x0159, B:64:0x0160, B:26:0x0073, B:29:0x0084), top: B:75:0x002a }] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0174, code lost:
    
        if (r13 == r15) goto L76;
     */
    /* JADX WARN: Instruction removed from duplicated block: B:60:0x012f, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v6, types: [com.lagradost.cloudstream3.plugins.VotingApi] */
    /* JADX WARN: Type inference failed for: r10v0, types: [com.lagradost.cloudstream3.plugins.VotingApi, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v0 */
    /* JADX WARN: Type inference failed for: r13v17, types: [com.lagradost.cloudstream3.plugins.VotingApi] */
    /* JADX WARN: Type inference failed for: r13v20, types: [java.util.Map, java.util.Map<java.lang.String, java.lang.Integer>] */
    /* JADX WARN: Type inference failed for: r14v0, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r14v1, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r14v11 */
    /* JADX WARN: Type inference failed for: r14v13, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r14v15 */
    /* JADX WARN: Type inference failed for: r14v2, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r14v25 */
    /* JADX WARN: Type inference failed for: r14v32 */
    /* JADX WARN: Type inference failed for: r14v33 */
    /* JADX WARN: Type inference failed for: r14v34 */
    /* JADX WARN: Type inference failed for: r14v35 */
    /* JADX WARN: Type inference failed for: r14v36 */
    /* JADX WARN: Type inference failed for: r14v5 */
    /* JADX WARN: Type inference failed for: r14v7 */
    /* JADX WARN: Type inference failed for: r14v8, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r14v9, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object vote(java.lang.String r14, kotlin.coroutines.Continuation<? super java.lang.Integer> r15) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 387
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.plugins.VotingApi.vote(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: VotingApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0083\b\u0018\u0000 &2\u00020\u0001:\u0002%&B\u001f\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007B/\b\u0010\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0006\u0010\u000bJ\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0012J&\u0010\u0016\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0017J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0001¢\u0006\u0002\b$R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0013\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u0012¨\u0006'"}, d2 = {"Lcom/lagradost/cloudstream3/plugins/VotingApi$CountifyResult;", "", "id", "", PeertubeParsingHelper.COUNT_KEY, "", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/Integer;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()Ljava/lang/String;", "getCount$annotations", "getCount", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "copy", "(Ljava/lang/String;Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/plugins/VotingApi$CountifyResult;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    static final /* data */ class CountifyResult {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty(PeertubeParsingHelper.COUNT_KEY)
        private final Integer count;

        @JsonProperty("id")
        private final String id;

        /* JADX WARN: Multi-variable type inference failed */
        public CountifyResult() {
            this((String) null, (Integer) (0 == true ? 1 : 0), 3, (DefaultConstructorMarker) (0 == true ? 1 : 0));
        }

        public static /* synthetic */ CountifyResult copy$default(CountifyResult countifyResult, String str, Integer num, int i, Object obj) {
            if ((i & 1) != 0) {
                str = countifyResult.id;
            }
            if ((i & 2) != 0) {
                num = countifyResult.count;
            }
            return countifyResult.copy(str, num);
        }

        @SerialName(PeertubeParsingHelper.COUNT_KEY)
        public static /* synthetic */ void getCount$annotations() {
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getCount() {
            return this.count;
        }

        public final CountifyResult copy(@JsonProperty("id") String id, @JsonProperty(PeertubeParsingHelper.COUNT_KEY) Integer count) {
            return new CountifyResult(id, count);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CountifyResult)) {
                return false;
            }
            CountifyResult countifyResult = (CountifyResult) other;
            return Intrinsics.areEqual(this.id, countifyResult.id) && Intrinsics.areEqual(this.count, countifyResult.count);
        }

        public int hashCode() {
            String str = this.id;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            Integer num = this.count;
            return iHashCode + (num != null ? num.hashCode() : 0);
        }

        public String toString() {
            return "CountifyResult(id=" + this.id + ", count=" + this.count + ')';
        }

        /* JADX INFO: compiled from: VotingApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/plugins/VotingApi$CountifyResult$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/plugins/VotingApi$CountifyResult;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<CountifyResult> serializer() {
                return VotingApi$CountifyResult$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ CountifyResult(int i, String str, Integer num, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) == 0) {
                this.id = null;
            } else {
                this.id = str;
            }
            if ((i & 2) == 0) {
                this.count = null;
            } else {
                this.count = num;
            }
        }

        public CountifyResult(@JsonProperty("id") String str, @JsonProperty(PeertubeParsingHelper.COUNT_KEY) Integer num) {
            this.id = str;
            this.count = num;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(CountifyResult self, CompositeEncoder output, SerialDescriptor serialDesc) {
            if (output.shouldEncodeElementDefault(serialDesc, 0) || self.id != null) {
                output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.id);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 1) && self.count == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 1, IntSerializer.INSTANCE, self.count);
        }

        public /* synthetic */ CountifyResult(String str, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : num);
        }

        public final String getId() {
            return this.id;
        }

        public final Integer getCount() {
            return this.count;
        }
    }
}
