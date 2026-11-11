package com.lagradost.cloudstream3.extractors;

import androidx.exifinterface.media.ExifInterface;
import androidx.tvprovider.media.tv.TvContractCompat;
import coil3.util.UtilsKt;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import dev.whyoleg.cryptography.CryptographyProvider;
import dev.whyoleg.cryptography.algorithms.AES;
import dev.whyoleg.cryptography.algorithms.Digest;
import dev.whyoleg.cryptography.algorithms.MD5;
import dev.whyoleg.cryptography.operations.Hasher;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MagicApiIntrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.modules.SerializersModule;
import org.mozilla.classfile.ByteCode;

/* JADX INFO: compiled from: Rabbitstream.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0012\n\u0002\b\u000b\b\u0016\u0018\u00002\u00020\u0001:\u00042345B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00052\b\u0010\u001a\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00180\u001c2\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00180\u001cH\u0096@¢\u0006\u0002\u0010 J\"\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\"2\u0006\u0010#\u001a\u00020\u0005H\u0096@¢\u0006\u0002\u0010$J(\u0010%\u001a\u0004\u0018\u0001H&\"\u0006\b\u0000\u0010&\u0018\u00012\u0006\u0010'\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005H\u0082\b¢\u0006\u0002\u0010(J\u0018\u0010)\u001a\u00020\u00052\u0006\u0010'\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005H\u0002J\u0018\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020+H\u0002J\u0010\u0010.\u001a\u00020+2\u0006\u0010'\u001a\u00020+H\u0002J\u0018\u0010/\u001a\u00020\u00052\u0006\u00100\u001a\u00020+2\u0006\u00101\u001a\u00020\u0005H\u0002R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0014\u0010\u0010\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0016\u0010\u0003¨\u00066"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Rabbitstream;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "mainUrl", "getMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "embed", "getEmbed", "key", "getKey", "aesCbc", "Ldev/whyoleg/cryptography/algorithms/AES$CBC;", "md5Hasher", "Ldev/whyoleg/cryptography/operations/Hasher;", "getMd5Hasher$annotations", "getUrl", "", ImagesContract.URL, "referer", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "extractRealKey", "Lkotlin/Pair;", "sources", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "decryptMapped", ExifInterface.GPS_DIRECTION_TRUE, TvContractCompat.PARAM_INPUT, "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;", "decrypt", "generateKey", "", "salt", "secret", "md5", "decryptSourceUrl", "decryptionKey", "sourceUrl", "Tracks", "Sources", "SourcesResponses", "SourcesEncrypted", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class Rabbitstream extends ExtractorApi {
    private final boolean requiresReferer;
    private final String name = "Rabbitstream";
    private final String mainUrl = "https://rabbitstream.net";
    private final String embed = "ajax/embed-4";
    private final String key = "https://raw.githubusercontent.com/eatmynerds/key/e4/key.txt";
    private final AES.CBC aesCbc = (AES.CBC) CryptographyProvider.INSTANCE.getDefault().get(AES.CBC.INSTANCE);
    private final Hasher md5Hasher = ((Digest) CryptographyProvider.INSTANCE.getDefault().get(MD5.INSTANCE)).hasher();

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.Rabbitstream$extractRealKey$1, reason: invalid class name */
    /* JADX INFO: compiled from: Rabbitstream.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Rabbitstream", f = "Rabbitstream.kt", i = {0, 0}, l = {131}, m = "extractRealKey$suspendImpl", n = {"$this", "sources"}, nl = {ByteCode.IFNONNULL}, s = {"L$0", "L$1"}, v = 2)
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
            return Rabbitstream.extractRealKey$suspendImpl(Rabbitstream.this, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.Rabbitstream$getUrl$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Rabbitstream.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Rabbitstream", f = "Rabbitstream.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, l = {93, 104, 113, 122}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "id", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "id", "response", "encryptedMap", "sources", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "id", "response", "encryptedMap", "sources", "decryptedSources", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "source", "$i$f$map", "$i$f$mapTo", "$i$a$-map-Rabbitstream$getUrl$2", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "id", "response", "encryptedMap", "sources", "decryptedSources", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "track", "$i$f$map", "$i$f$mapTo", "$i$a$-map-Rabbitstream$getUrl$3"}, nl = {99, 105, 117, 121}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$14", "L$15", "I$0", "I$1", "I$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$14", "L$15", "I$0", "I$1", "I$2"}, v = 2)
    static final class C07191 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$15;
        Object L$16;
        Object L$17;
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

        C07191(Continuation<? super C07191> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Rabbitstream.getUrl$suspendImpl(Rabbitstream.this, (String) null, (String) null, (Function1<? super SubtitleFile, Unit>) null, (Function1<? super ExtractorLink, Unit>) null, (Continuation<? super Unit>) this);
        }
    }

    private static /* synthetic */ void getMd5Hasher$annotations() {
    }

    public Object extractRealKey(String str, Continuation<? super Pair<String, String>> continuation) {
        return extractRealKey$suspendImpl(this, str, continuation);
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public Object getUrl(String str, String str2, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super Unit> continuation) {
        return getUrl$suspendImpl(this, str, str2, function1, function2, continuation);
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public String getName() {
        return this.name;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public String getMainUrl() {
        return this.mainUrl;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public boolean getRequiresReferer() {
        return this.requiresReferer;
    }

    public String getEmbed() {
        return this.embed;
    }

    public String getKey() {
        return this.key;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Code duplicated, block: B:84:0x0397  */
    /* JADX WARN: Code duplicated, block: B:86:0x03a6  */
    /* JADX WARN: Code duplicated, block: B:99:0x046c  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r27v0 */
    /* JADX WARN: Type inference failed for: r27v1 */
    /* JADX WARN: Type inference failed for: r27v2 */
    /* JADX WARN: Type inference failed for: r9v12, types: [int] */
    /* JADX WARN: Type inference failed for: r9v38 */
    /* JADX WARN: Type inference failed for: r9v39 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:120:0x056b -> B:121:0x057b). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:123:0x058a -> B:122:0x0587). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:92:0x043b -> B:93:0x0449). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    static /* synthetic */ java.lang.Object getUrl$suspendImpl(com.lagradost.cloudstream3.extractors.Rabbitstream r30, java.lang.String r31, java.lang.String r32, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r33, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r34, kotlin.coroutines.Continuation<? super kotlin.Unit> r35) {
        /*
            Method dump skipped, instruction units count: 1447
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.extractors.Rabbitstream.getUrl$suspendImpl(com.lagradost.cloudstream3.extractors.Rabbitstream, java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    static /* synthetic */ Object extractRealKey$suspendImpl(Rabbitstream rabbitstream, String str, Continuation<? super Pair<String, String>> continuation) throws JsonProcessingException {
        AnonymousClass1 anonymousClass1;
        String str2;
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = rabbitstream.new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = rabbitstream.new AnonymousClass1(continuation);
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AppUtils appUtils = AppUtils.INSTANCE;
            Requests app2 = MainActivityKt.getApp();
            String key = rabbitstream.getKey();
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(rabbitstream);
            anonymousClass1.L$1 = str;
            anonymousClass1.L$2 = appUtils;
            anonymousClass1.label = 1;
            Object obj2 = Requests.get$default(app2, key, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass1, 4094, null);
            if (obj2 == coroutine_suspended) {
                return coroutine_suspended;
            }
            str2 = str;
            obj = obj2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            str2 = (String) anonymousClass1.L$1;
            ResultKt.throwOnFailure(obj);
        }
        String text = ((NiceResponse) obj).getText();
        try {
            Result.Companion companion = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(new ArrayListSerializer(IntSerializer.INSTANCE));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
            try {
                Result.Companion companion3 = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(List.class), null, 2, null));
            } catch (Throwable th2) {
                Result.Companion companion4 = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
            }
        }
        KSerializer kSerializer = (KSerializer) (Result.m8942isFailureimpl(objM8936constructorimpl) ? null : objM8936constructorimpl);
        if (kSerializer != null) {
            try {
                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, text);
            } catch (SerializationException e) {
                ArchComponentExtKt.logError(e);
                objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<List<? extends Integer>>() { // from class: com.lagradost.cloudstream3.extractors.Rabbitstream$extractRealKey$suspendImpl$$inlined$parseJson$1
                });
            } catch (Throwable unused) {
                objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<List<? extends Integer>>() { // from class: com.lagradost.cloudstream3.extractors.Rabbitstream$extractRealKey$suspendImpl$$inlined$parseJson$1
                });
            }
        } else {
            objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<List<? extends Integer>>() { // from class: com.lagradost.cloudstream3.extractors.Rabbitstream$extractRealKey$suspendImpl$$inlined$parseJson$1
            });
        }
        List list = (List) objDecodeFromString;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Boxing.boxByte((byte) ((Number) it.next()).intValue()));
        }
        return TuplesKt.to(MainAPIKt.base64Encode(CollectionsKt.toByteArray(arrayList)), str2);
    }

    private final /* synthetic */ <T> T decryptMapped(String input, String key) {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        String strDecrypt = decrypt(input, key);
        AppUtils appUtils = AppUtils.INSTANCE;
        if (strDecrypt == null) {
            return null;
        }
        try {
            try {
                Result.Companion companion = Result.INSTANCE;
                Intrinsics.reifiedOperationMarker(6, "T?");
                MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.simple");
                objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.serializer((KType) null));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
            }
            Throwable thM8939exceptionOrNullimpl = Result.m8939exceptionOrNullimpl(objM8936constructorimpl);
            if (thM8939exceptionOrNullimpl != null) {
                try {
                    Result.Companion companion3 = Result.INSTANCE;
                    Throwable th2 = thM8939exceptionOrNullimpl;
                    SerializersModule serializersModule = MainAPIKt.getJson().getSerializersModule();
                    Intrinsics.reifiedOperationMarker(4, "T?");
                    objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(serializersModule, Reflection.getOrCreateKotlinClass(Object.class), null, 2, null));
                } catch (Throwable th3) {
                    Result.Companion companion4 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th3));
                }
            }
            if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                objM8936constructorimpl = null;
            }
            KSerializer kSerializer = (KSerializer) objM8936constructorimpl;
            if (kSerializer != null) {
                try {
                    objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, strDecrypt);
                } catch (SerializationException e) {
                    ArchComponentExtKt.logError(e);
                    JsonMapper mapper = MainAPIKt.getMapper();
                    Intrinsics.needClassReification();
                    objDecodeFromString = mapper.readValue(strDecrypt, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.extractors.Rabbitstream$decryptMapped$$inlined$tryParseJson$1
                    });
                } catch (Throwable unused) {
                    JsonMapper mapper2 = MainAPIKt.getMapper();
                    Intrinsics.needClassReification();
                    objDecodeFromString = mapper2.readValue(strDecrypt, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.extractors.Rabbitstream$decryptMapped$$inlined$tryParseJson$1
                    });
                }
            } else {
                JsonMapper mapper3 = MainAPIKt.getMapper();
                Intrinsics.needClassReification();
                objDecodeFromString = mapper3.readValue(strDecrypt, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.extractors.Rabbitstream$decryptMapped$$inlined$tryParseJson$1
                });
            }
            return (T) objDecodeFromString;
        } catch (Exception unused2) {
            return null;
        }
    }

    private final String decrypt(String input, String key) {
        return decryptSourceUrl(generateKey(ArraysKt.copyOfRange(MainAPIKt.base64DecodeArray(input), 8, 16), StringsKt.encodeToByteArray(key)), input);
    }

    private final byte[] generateKey(byte[] salt, byte[] secret) {
        byte[] bArrMd5 = md5(ArraysKt.plus(secret, salt));
        byte[] bArrMd6 = bArrMd5;
        while (bArrMd5.length < 48) {
            bArrMd6 = md5(ArraysKt.plus(ArraysKt.plus(bArrMd6, secret), salt));
            bArrMd5 = ArraysKt.plus(bArrMd5, bArrMd6);
        }
        return bArrMd5;
    }

    private final byte[] md5(byte[] input) {
        return this.md5Hasher.hashBlocking(input);
    }

    private final String decryptSourceUrl(byte[] decryptionKey, String sourceUrl) {
        byte[] bArrBase64DecodeArray = MainAPIKt.base64DecodeArray(sourceUrl);
        byte[] bArrCopyOfRange = ArraysKt.copyOfRange(bArrBase64DecodeArray, 16, bArrBase64DecodeArray.length);
        byte[] bArrCopyOfRange2 = ArraysKt.copyOfRange(decryptionKey, 0, 32);
        return StringsKt.decodeToString(this.aesCbc.keyDecoder().decodeFromByteArrayBlocking(AES.Key.Format.RAW, bArrCopyOfRange2).cipher(true).decryptWithIvBlocking(ArraysKt.copyOfRange(decryptionKey, 32, decryptionKey.length), bArrCopyOfRange));
    }

    /* JADX INFO: compiled from: Rabbitstream.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u000f\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Rabbitstream$Tracks;", "", UtilsKt.SCHEME_FILE, "", "label", "kind", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getFile", "()Ljava/lang/String;", "getLabel", "getKind", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Tracks {

        @JsonProperty(UtilsKt.SCHEME_FILE)
        private final String file;

        @JsonProperty("kind")
        private final String kind;

        @JsonProperty("label")
        private final String label;

        public Tracks() {
            this(null, null, null, 7, null);
        }

        public static /* synthetic */ Tracks copy$default(Tracks tracks, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = tracks.file;
            }
            if ((i & 2) != 0) {
                str2 = tracks.label;
            }
            if ((i & 4) != 0) {
                str3 = tracks.kind;
            }
            return tracks.copy(str, str2, str3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getFile() {
            return this.file;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getLabel() {
            return this.label;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getKind() {
            return this.kind;
        }

        public final Tracks copy(@JsonProperty(UtilsKt.SCHEME_FILE) String file, @JsonProperty("label") String label, @JsonProperty("kind") String kind) {
            return new Tracks(file, label, kind);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Tracks)) {
                return false;
            }
            Tracks tracks = (Tracks) other;
            return Intrinsics.areEqual(this.file, tracks.file) && Intrinsics.areEqual(this.label, tracks.label) && Intrinsics.areEqual(this.kind, tracks.kind);
        }

        public int hashCode() {
            String str = this.file;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.label;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.kind;
            return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
        }

        public String toString() {
            return "Tracks(file=" + this.file + ", label=" + this.label + ", kind=" + this.kind + ')';
        }

        public Tracks(@JsonProperty(UtilsKt.SCHEME_FILE) String str, @JsonProperty("label") String str2, @JsonProperty("kind") String str3) {
            this.file = str;
            this.label = str2;
            this.kind = str3;
        }

        public /* synthetic */ Tracks(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3);
        }

        public final String getFile() {
            return this.file;
        }

        public final String getLabel() {
            return this.label;
        }

        public final String getKind() {
            return this.kind;
        }
    }

    /* JADX INFO: compiled from: Rabbitstream.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u000f\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Rabbitstream$Sources;", "", UtilsKt.SCHEME_FILE, "", "type", "label", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getFile", "()Ljava/lang/String;", "getType", "getLabel", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Sources {

        @JsonProperty(UtilsKt.SCHEME_FILE)
        private final String file;

        @JsonProperty("label")
        private final String label;

        @JsonProperty("type")
        private final String type;

        public Sources() {
            this(null, null, null, 7, null);
        }

        public static /* synthetic */ Sources copy$default(Sources sources, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = sources.file;
            }
            if ((i & 2) != 0) {
                str2 = sources.type;
            }
            if ((i & 4) != 0) {
                str3 = sources.label;
            }
            return sources.copy(str, str2, str3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getFile() {
            return this.file;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getLabel() {
            return this.label;
        }

        public final Sources copy(@JsonProperty(UtilsKt.SCHEME_FILE) String file, @JsonProperty("type") String type, @JsonProperty("label") String label) {
            return new Sources(file, type, label);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Sources)) {
                return false;
            }
            Sources sources = (Sources) other;
            return Intrinsics.areEqual(this.file, sources.file) && Intrinsics.areEqual(this.type, sources.type) && Intrinsics.areEqual(this.label, sources.label);
        }

        public int hashCode() {
            String str = this.file;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.type;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.label;
            return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
        }

        public String toString() {
            return "Sources(file=" + this.file + ", type=" + this.type + ", label=" + this.label + ')';
        }

        public Sources(@JsonProperty(UtilsKt.SCHEME_FILE) String str, @JsonProperty("type") String str2, @JsonProperty("label") String str3) {
            this.file = str;
            this.type = str2;
            this.label = str3;
        }

        public /* synthetic */ Sources(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3);
        }

        public final String getFile() {
            return this.file;
        }

        public final String getType() {
            return this.type;
        }

        public final String getLabel() {
            return this.label;
        }
    }

    /* JADX INFO: compiled from: Rabbitstream.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0012\b\u0003\u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003\u0012\u0012\b\u0003\u0010\u0005\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0013\u0010\f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003HÆ\u0003J\u0013\u0010\r\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u0003HÆ\u0003J1\u0010\u000e\u001a\u00020\u00002\u0012\b\u0003\u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u00032\u0012\b\u0003\u0010\u0005\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R \u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR \u0010\u0005\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Rabbitstream$SourcesResponses;", "", "sources", "", "Lcom/lagradost/cloudstream3/extractors/Rabbitstream$Sources;", "tracks", "Lcom/lagradost/cloudstream3/extractors/Rabbitstream$Tracks;", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "getSources", "()Ljava/util/List;", "getTracks", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class SourcesResponses {

        @JsonProperty("sources")
        private final List<Sources> sources;

        @JsonProperty("tracks")
        private final List<Tracks> tracks;

        /* JADX WARN: Multi-variable type inference failed */
        public SourcesResponses() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ SourcesResponses copy$default(SourcesResponses sourcesResponses, List list, List list2, int i, Object obj) {
            if ((i & 1) != 0) {
                list = sourcesResponses.sources;
            }
            if ((i & 2) != 0) {
                list2 = sourcesResponses.tracks;
            }
            return sourcesResponses.copy(list, list2);
        }

        public final List<Sources> component1() {
            return this.sources;
        }

        public final List<Tracks> component2() {
            return this.tracks;
        }

        public final SourcesResponses copy(@JsonProperty("sources") List<Sources> sources, @JsonProperty("tracks") List<Tracks> tracks) {
            return new SourcesResponses(sources, tracks);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SourcesResponses)) {
                return false;
            }
            SourcesResponses sourcesResponses = (SourcesResponses) other;
            return Intrinsics.areEqual(this.sources, sourcesResponses.sources) && Intrinsics.areEqual(this.tracks, sourcesResponses.tracks);
        }

        public int hashCode() {
            List<Sources> list = this.sources;
            int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
            List<Tracks> list2 = this.tracks;
            return iHashCode + (list2 != null ? list2.hashCode() : 0);
        }

        public String toString() {
            return "SourcesResponses(sources=" + this.sources + ", tracks=" + this.tracks + ')';
        }

        public SourcesResponses(@JsonProperty("sources") List<Sources> list, @JsonProperty("tracks") List<Tracks> list2) {
            this.sources = list;
            this.tracks = list2;
        }

        public /* synthetic */ SourcesResponses(List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? CollectionsKt.emptyList() : list, (i & 2) != 0 ? CollectionsKt.emptyList() : list2);
        }

        public final List<Sources> getSources() {
            return this.sources;
        }

        public final List<Tracks> getTracks() {
            return this.tracks;
        }
    }

    /* JADX INFO: compiled from: Rabbitstream.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0012\b\u0003\u0010\u0006\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u0013\u0010\u0014\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u0007HÆ\u0003J:\u0010\u0015\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0012\b\u0003\u0010\u0006\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0014\u0010\u0017\u001a\u00020\u00052\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR \u0010\u0006\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Rabbitstream$SourcesEncrypted;", "", "sources", "", "encrypted", "", "tracks", "", "Lcom/lagradost/cloudstream3/extractors/Rabbitstream$Tracks;", "<init>", "(Ljava/lang/String;Ljava/lang/Boolean;Ljava/util/List;)V", "getSources", "()Ljava/lang/String;", "getEncrypted", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getTracks", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/Boolean;Ljava/util/List;)Lcom/lagradost/cloudstream3/extractors/Rabbitstream$SourcesEncrypted;", "equals", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class SourcesEncrypted {

        @JsonProperty("encrypted")
        private final Boolean encrypted;

        @JsonProperty("sources")
        private final String sources;

        @JsonProperty("tracks")
        private final List<Tracks> tracks;

        public SourcesEncrypted() {
            this(null, null, null, 7, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ SourcesEncrypted copy$default(SourcesEncrypted sourcesEncrypted, String str, Boolean bool, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = sourcesEncrypted.sources;
            }
            if ((i & 2) != 0) {
                bool = sourcesEncrypted.encrypted;
            }
            if ((i & 4) != 0) {
                list = sourcesEncrypted.tracks;
            }
            return sourcesEncrypted.copy(str, bool, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getSources() {
            return this.sources;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Boolean getEncrypted() {
            return this.encrypted;
        }

        public final List<Tracks> component3() {
            return this.tracks;
        }

        public final SourcesEncrypted copy(@JsonProperty("sources") String sources, @JsonProperty("encrypted") Boolean encrypted, @JsonProperty("tracks") List<Tracks> tracks) {
            return new SourcesEncrypted(sources, encrypted, tracks);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SourcesEncrypted)) {
                return false;
            }
            SourcesEncrypted sourcesEncrypted = (SourcesEncrypted) other;
            return Intrinsics.areEqual(this.sources, sourcesEncrypted.sources) && Intrinsics.areEqual(this.encrypted, sourcesEncrypted.encrypted) && Intrinsics.areEqual(this.tracks, sourcesEncrypted.tracks);
        }

        public int hashCode() {
            String str = this.sources;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            Boolean bool = this.encrypted;
            int iHashCode2 = (iHashCode + (bool == null ? 0 : bool.hashCode())) * 31;
            List<Tracks> list = this.tracks;
            return iHashCode2 + (list != null ? list.hashCode() : 0);
        }

        public String toString() {
            return "SourcesEncrypted(sources=" + this.sources + ", encrypted=" + this.encrypted + ", tracks=" + this.tracks + ')';
        }

        public SourcesEncrypted(@JsonProperty("sources") String str, @JsonProperty("encrypted") Boolean bool, @JsonProperty("tracks") List<Tracks> list) {
            this.sources = str;
            this.encrypted = bool;
            this.tracks = list;
        }

        public final String getSources() {
            return this.sources;
        }

        public final Boolean getEncrypted() {
            return this.encrypted;
        }

        public /* synthetic */ SourcesEncrypted(String str, Boolean bool, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : bool, (i & 4) != 0 ? CollectionsKt.emptyList() : list);
        }

        public final List<Tracks> getTracks() {
            return this.tracks;
        }
    }
}
