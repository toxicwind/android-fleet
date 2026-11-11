package com.lagradost.cloudstream3.extractors;

import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.M3u8Helper;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import com.lagradost.nicehttp.ResponseParser;
import dev.whyoleg.cryptography.CryptographyProvider;
import dev.whyoleg.cryptography.algorithms.AES;
import io.ktor.http.CodecsKt;
import io.ktor.http.URLUtilsKt;
import io.ktor.http.Url;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: ByseSX.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0005H\u0002J\u0010\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0005H\u0002J\u0010\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0005H\u0002J\u0018\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\n\u001a\u00020\u0005H\u0082@¢\u0006\u0002\u0010\u001bJ\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\n\u001a\u00020\u0005H\u0082@¢\u0006\u0002\u0010\u001bJ\u0010\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0012\u0010!\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001f\u001a\u00020 H\u0002JH\u0010\"\u001a\u00020#2\u0006\u0010\u0017\u001a\u00020\u00052\b\u0010$\u001a\u0004\u0018\u00010\u00052\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020#0&2\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020#0&H\u0096@¢\u0006\u0002\u0010*R\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u0014\u0010\r\u001a\u00020\u000eX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/ByseSX;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "mainUrl", "getMainUrl", "setMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "aesGcm", "Ldev/whyoleg/cryptography/algorithms/AES$GCM;", "b64UrlDecode", "", CmcdData.STREAMING_FORMAT_SS, "getBaseUrl", ImagesContract.URL, "getCodeFromUrl", "getDetails", "Lcom/lagradost/cloudstream3/extractors/DetailsRoot;", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPlayback", "Lcom/lagradost/cloudstream3/extractors/PlaybackRoot;", "buildAesKey", "playback", "Lcom/lagradost/cloudstream3/extractors/Playback;", "decryptPlayback", "getUrl", "", "referer", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class ByseSX extends ExtractorApi {
    private String name = "Byse";
    private String mainUrl = "https://byse.sx";
    private final boolean requiresReferer = true;
    private final AES.GCM aesGcm = (AES.GCM) CryptographyProvider.INSTANCE.getDefault().get(AES.GCM.INSTANCE);

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.ByseSX$getDetails$1, reason: invalid class name */
    /* JADX INFO: compiled from: ByseSX.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.ByseSX", f = "ByseSX.kt", i = {0, 0, 0, 0}, l = {63}, m = "getDetails", n = {"mainUrl", TtmlNode.RUBY_BASE, "code", ImagesContract.URL}, nl = {188}, s = {"L$0", "L$1", "L$2", "L$3"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
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
            return ByseSX.this.getDetails(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.ByseSX$getPlayback$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByseSX.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.ByseSX", f = "ByseSX.kt", i = {0, 1, 1, 1, 1, 1, 1, 1}, l = {67, 79}, m = "getPlayback", n = {"mainUrl", "mainUrl", "details", "embedFrameUrl", "embedBase", "code", "playbackUrl", "headers"}, nl = {68, 188}, s = {"L$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"}, v = 2)
    static final class C07161 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        C07161(Continuation<? super C07161> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByseSX.this.getPlayback(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.ByseSX$getUrl$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByseSX.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.ByseSX", f = "ByseSX.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {118, 122}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "refererUrl", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "refererUrl", "playbackRoot", "streamUrl", "headers"}, nl = {119, 127}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8"}, v = 2)
    static final class C07171 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        int label;
        /* synthetic */ Object result;

        C07171(Continuation<? super C07171> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByseSX.getUrl$suspendImpl(ByseSX.this, (String) null, (String) null, (Function1<? super SubtitleFile, Unit>) null, (Function1<? super ExtractorLink, Unit>) null, (Continuation<? super Unit>) this);
        }
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public Object getUrl(String str, String str2, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super Unit> continuation) {
        return getUrl$suspendImpl(this, str, str2, function1, function2, continuation);
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public String getMainUrl() {
        return this.mainUrl;
    }

    public void setMainUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mainUrl = str;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public boolean getRequiresReferer() {
        return this.requiresReferer;
    }

    private final byte[] b64UrlDecode(String s) {
        String strReplace$default = StringsKt.replace$default(StringsKt.replace$default(s, '-', '+', false, 4, (Object) null), '_', '/', false, 4, (Object) null);
        return MainAPIKt.base64DecodeArray(strReplace$default + StringsKt.repeat("=", (4 - (strReplace$default.length() % 4)) % 4));
    }

    private final String getBaseUrl(String url) {
        Url Url = URLUtilsKt.Url(url);
        return Url.getProtocol().getName() + "://" + Url.getHost();
    }

    private final String getCodeFromUrl(String url) {
        return StringsKt.substringAfterLast$default(StringsKt.trimEnd(CodecsKt.decodeURLPart$default(URLUtilsKt.Url(url).getEncodedPath(), 0, 0, null, 7, null), '/'), '/', (String) null, 2, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public final Object getDetails(String str, Continuation<? super DetailsRoot> continuation) {
        AnonymousClass1 anonymousClass1;
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
            String baseUrl = getBaseUrl(str);
            String codeFromUrl = getCodeFromUrl(str);
            String str2 = baseUrl + "/api/videos/" + codeFromUrl + "/embed/details";
            Requests app2 = MainActivityKt.getApp();
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(str);
            anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(baseUrl);
            anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(codeFromUrl);
            anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(str2);
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
            return parser.parseSafe(niceResponse.getText(), Reflection.getOrCreateKotlinClass(DetailsRoot.class));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    public final Object getPlayback(String str, Continuation<? super PlaybackRoot> continuation) {
        C07161 c07161;
        String str2 = str;
        if (continuation instanceof C07161) {
            c07161 = (C07161) continuation;
            if ((c07161.label & Integer.MIN_VALUE) != 0) {
                c07161.label -= Integer.MIN_VALUE;
            } else {
                c07161 = new C07161(continuation);
            }
        } else {
            c07161 = new C07161(continuation);
        }
        Object details = c07161.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07161.label;
        if (i != 0) {
            if (i == 1) {
                str2 = (String) c07161.L$0;
                ResultKt.throwOnFailure(details);
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(details);
            }
            NiceResponse niceResponse = (NiceResponse) details;
            try {
                ResponseParser parser = niceResponse.getParser();
                Intrinsics.checkNotNull(parser);
                return parser.parseSafe(niceResponse.getText(), Reflection.getOrCreateKotlinClass(PlaybackRoot.class));
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        ResultKt.throwOnFailure(details);
        c07161.L$0 = str2;
        c07161.label = 1;
        details = getDetails(str2, c07161);
        if (details == coroutine_suspended) {
            return coroutine_suspended;
        }
        DetailsRoot detailsRoot = (DetailsRoot) details;
        if (detailsRoot == null) {
            return null;
        }
        String embedFrameUrl = detailsRoot.getEmbedFrameUrl();
        String baseUrl = getBaseUrl(embedFrameUrl);
        String codeFromUrl = getCodeFromUrl(embedFrameUrl);
        String str3 = baseUrl + "/api/videos/" + codeFromUrl + "/embed/playback";
        Map mapMapOf = MapsKt.mapOf(TuplesKt.to("accept", "*/*"), TuplesKt.to("accept-language", "en-US,en;q=0.5"), TuplesKt.to("priority", "u=1, i"), TuplesKt.to("referer", embedFrameUrl), TuplesKt.to("x-embed-parent", str2));
        Requests app2 = MainActivityKt.getApp();
        c07161.L$0 = SpillingKt.nullOutSpilledVariable(str2);
        c07161.L$1 = SpillingKt.nullOutSpilledVariable(detailsRoot);
        c07161.L$2 = SpillingKt.nullOutSpilledVariable(embedFrameUrl);
        c07161.L$3 = SpillingKt.nullOutSpilledVariable(baseUrl);
        c07161.L$4 = SpillingKt.nullOutSpilledVariable(codeFromUrl);
        c07161.L$5 = SpillingKt.nullOutSpilledVariable(str3);
        c07161.L$6 = SpillingKt.nullOutSpilledVariable(mapMapOf);
        c07161.label = 2;
        details = Requests.get$default(app2, str3, mapMapOf, null, null, null, false, 0, null, 0L, null, false, null, c07161, 4092, null);
        if (details == coroutine_suspended) {
            return coroutine_suspended;
        }
        NiceResponse niceResponse2 = (NiceResponse) details;
        ResponseParser parser2 = niceResponse2.getParser();
        Intrinsics.checkNotNull(parser2);
        return parser2.parseSafe(niceResponse2.getText(), Reflection.getOrCreateKotlinClass(PlaybackRoot.class));
    }

    private final byte[] buildAesKey(Playback playback) {
        return ArraysKt.plus(b64UrlDecode(playback.getKeyParts().get(0)), b64UrlDecode(playback.getKeyParts().get(1)));
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0095  */
    /* JADX WARN: Code duplicated, block: B:28:0x00ab A[Catch: Exception -> 0x0049, TRY_LEAVE, TryCatch #1 {Exception -> 0x0049, blocks: (B:12:0x0064, B:19:0x008f, B:22:0x0096, B:28:0x00ab, B:27:0x00a6, B:18:0x0085, B:11:0x005a, B:15:0x006b, B:8:0x004c, B:24:0x009a), top: B:42:0x0047, inners: #0, #2, #4, #6 }] */
    /* JADX WARN: Code duplicated, block: B:47:0x009a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    private final String decryptPlayback(Playback playback) {
        Object objDecodeFromString;
        List<PlaybackDecryptSource> sources;
        PlaybackDecryptSource playbackDecryptSource;
        Object objM8936constructorimpl;
        KSerializer kSerializer;
        String strDecodeToString = StringsKt.decodeToString(AES.GCM.Key.CC.m8679cipher6q1zMKY$default(this.aesGcm.keyDecoder().decodeFromByteArrayBlocking(AES.Key.Format.RAW, buildAesKey(playback)), 0, 1, null).decryptWithIvBlocking(b64UrlDecode(playback.getIv()), b64UrlDecode(playback.getPayload())));
        if (StringsKt.startsWith$default(strDecodeToString, "\ufeff", false, 2, (Object) null)) {
            strDecodeToString = strDecodeToString.substring(1);
            Intrinsics.checkNotNullExpressionValue(strDecodeToString, "substring(...)");
        }
        try {
            AppUtils appUtils = AppUtils.INSTANCE;
            try {
                if (strDecodeToString == null) {
                    objDecodeFromString = null;
                } else {
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.extractors.PlaybackDecrypt"));
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
                                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, strDecodeToString);
                            } catch (SerializationException e) {
                                ArchComponentExtKt.logError(e);
                                objDecodeFromString = MainAPIKt.getMapper().readValue(strDecodeToString, new TypeReference<PlaybackDecrypt>() { // from class: com.lagradost.cloudstream3.extractors.ByseSX$decryptPlayback$$inlined$tryParseJson$1
                                });
                            } catch (Throwable unused) {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(strDecodeToString, new TypeReference<PlaybackDecrypt>() { // from class: com.lagradost.cloudstream3.extractors.ByseSX$decryptPlayback$$inlined$tryParseJson$1
                                });
                            }
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(strDecodeToString, new TypeReference<PlaybackDecrypt>() { // from class: com.lagradost.cloudstream3.extractors.ByseSX$decryptPlayback$$inlined$tryParseJson$1
                            });
                        }
                    } else {
                        try {
                            Result.Companion companion3 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(PlaybackDecrypt.class), null, 2, null));
                        } catch (Throwable th2) {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                        }
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, strDecodeToString);
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(strDecodeToString, new TypeReference<PlaybackDecrypt>() { // from class: com.lagradost.cloudstream3.extractors.ByseSX$decryptPlayback$$inlined$tryParseJson$1
                            });
                        }
                    }
                }
            } catch (Exception unused2) {
                objDecodeFromString = null;
            }
            PlaybackDecrypt playbackDecrypt = (PlaybackDecrypt) objDecodeFromString;
            if (playbackDecrypt == null || (sources = playbackDecrypt.getSources()) == null || (playbackDecryptSource = (PlaybackDecryptSource) CollectionsKt.firstOrNull((List) sources)) == null) {
                return null;
            }
            return playbackDecryptSource.getUrl();
        } catch (Exception unused3) {
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:35:0x012b A[LOOP:0: B:33:0x0125->B:35:0x012b, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    static /* synthetic */ Object getUrl$suspendImpl(ByseSX byseSX, String str, String str2, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super Unit> continuation) {
        C07171 c07171;
        String baseUrl;
        String str3;
        Function1<? super ExtractorLink, Unit> function3;
        Object obj;
        String str4;
        Function1<? super SubtitleFile, Unit> function4;
        String strDecryptPlayback;
        Function1<? super ExtractorLink, Unit> function5;
        Iterator it;
        if (continuation instanceof C07171) {
            c07171 = (C07171) continuation;
            if ((c07171.label & Integer.MIN_VALUE) != 0) {
                c07171.label -= Integer.MIN_VALUE;
            } else {
                c07171 = byseSX.new C07171(continuation);
            }
        } else {
            c07171 = byseSX.new C07171(continuation);
        }
        C07171 c07172 = c07171;
        Object objGenerateM3u8$default = c07172.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07172.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objGenerateM3u8$default);
            baseUrl = byseSX.getBaseUrl(str);
            c07172.L$0 = byseSX;
            c07172.L$1 = SpillingKt.nullOutSpilledVariable(str);
            c07172.L$2 = SpillingKt.nullOutSpilledVariable(str2);
            c07172.L$3 = SpillingKt.nullOutSpilledVariable(function1);
            c07172.L$4 = function2;
            c07172.L$5 = baseUrl;
            c07172.label = 1;
            Object playback = byseSX.getPlayback(str, c07172);
            if (playback != coroutine_suspended) {
                str3 = str;
                function3 = function2;
                obj = playback;
                str4 = str2;
                function4 = function1;
            }
            return coroutine_suspended;
        }
        if (i == 1) {
            String str5 = (String) c07172.L$5;
            Function1<? super ExtractorLink, Unit> function6 = (Function1) c07172.L$4;
            Function1<? super SubtitleFile, Unit> function7 = (Function1) c07172.L$3;
            str4 = (String) c07172.L$2;
            String str6 = (String) c07172.L$1;
            ByseSX byseSX2 = (ByseSX) c07172.L$0;
            ResultKt.throwOnFailure(objGenerateM3u8$default);
            function3 = function6;
            function4 = function7;
            str3 = str6;
            obj = objGenerateM3u8$default;
            baseUrl = str5;
            byseSX = byseSX2;
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            function5 = (Function1) c07172.L$4;
            ResultKt.throwOnFailure(objGenerateM3u8$default);
        }
        it = ((Iterable) objGenerateM3u8$default).iterator();
        while (it.hasNext()) {
            function5.invoke(it.next());
        }
        return Unit.INSTANCE;
        PlaybackRoot playbackRoot = (PlaybackRoot) obj;
        if (playbackRoot != null && (strDecryptPlayback = byseSX.decryptPlayback(playbackRoot.getPlayback())) != null) {
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to("Referer", baseUrl));
            Function1<? super SubtitleFile, Unit> function8 = function4;
            M3u8Helper.Companion companion = M3u8Helper.INSTANCE;
            String name = byseSX.getName();
            String str7 = str4;
            String mainUrl = byseSX.getMainUrl();
            c07172.L$0 = SpillingKt.nullOutSpilledVariable(byseSX);
            c07172.L$1 = SpillingKt.nullOutSpilledVariable(str3);
            c07172.L$2 = SpillingKt.nullOutSpilledVariable(str7);
            c07172.L$3 = SpillingKt.nullOutSpilledVariable(function8);
            c07172.L$4 = function3;
            c07172.L$5 = SpillingKt.nullOutSpilledVariable(baseUrl);
            c07172.L$6 = SpillingKt.nullOutSpilledVariable(playbackRoot);
            c07172.L$7 = SpillingKt.nullOutSpilledVariable(strDecryptPlayback);
            c07172.L$8 = SpillingKt.nullOutSpilledVariable(mapMapOf);
            c07172.label = 2;
            objGenerateM3u8$default = M3u8Helper.Companion.generateM3u8$default(companion, name, strDecryptPlayback, mainUrl, null, mapMapOf, null, c07172, 40, null);
            if (objGenerateM3u8$default != coroutine_suspended) {
                function5 = function3;
                it = ((Iterable) objGenerateM3u8$default).iterator();
                while (it.hasNext()) {
                    function5.invoke(it.next());
                }
                return Unit.INSTANCE;
            }
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
