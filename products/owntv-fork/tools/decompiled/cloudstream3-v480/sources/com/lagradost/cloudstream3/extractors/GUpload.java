package com.lagradost.cloudstream3.extractors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorApiKt;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.modules.SerializersModule;
import org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper;

/* JADX INFO: compiled from: GUpload.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001:\u0001\u0018B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000f0\u00132\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u000f0\u0013H\u0096@¢\u0006\u0002\u0010\u0017R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/GUpload;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "mainUrl", "getMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "getUrl", "", ImagesContract.URL, "referer", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "VideoInfo", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class GUpload extends ExtractorApi {
    private final boolean requiresReferer;
    private final String name = "GUpload";
    private final String mainUrl = "https://gupload.xyz";

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.GUpload$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: GUpload.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.GUpload", f = "GUpload.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1}, l = {24, 30}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "response", "playerConfigString", "playerConfig"}, nl = {26, 29}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
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

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return GUpload.getUrl$suspendImpl(GUpload.this, (String) null, (String) null, (Function1<? super SubtitleFile, Unit>) null, (Function1<? super ExtractorLink, Unit>) null, (Continuation<? super Unit>) this);
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

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public String getMainUrl() {
        return this.mainUrl;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public boolean getRequiresReferer() {
        return this.requiresReferer;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    static /* synthetic */ Object getUrl$suspendImpl(GUpload gUpload, String str, String str2, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super Unit> continuation) throws JsonProcessingException {
        AnonymousClass1 anonymousClass1;
        int i;
        String str3;
        String str4;
        Function1<? super ExtractorLink, Unit> function3;
        GUpload gUpload2;
        Object obj;
        Function1<? super SubtitleFile, Unit> function4;
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Function1<? super ExtractorLink, Unit> function5;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = gUpload.new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = gUpload.new AnonymousClass1(continuation);
        }
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object objNewExtractorLink$default = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass2.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(objNewExtractorLink$default);
                Requests app2 = MainActivityKt.getApp();
                anonymousClass2.L$0 = gUpload;
                anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str);
                anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str2);
                anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function1);
                anonymousClass2.L$4 = function2;
                anonymousClass2.label = 1;
                i = 2;
                Object obj2 = Requests.get$default(app2, str, null, str2, null, null, false, 0, null, 0L, null, false, null, anonymousClass2, 4090, null);
                if (obj2 != coroutine_suspended) {
                    anonymousClass2 = anonymousClass2;
                    str3 = str;
                    str4 = str2;
                    function3 = function2;
                    gUpload2 = gUpload;
                    obj = obj2;
                    function4 = function1;
                }
                anonymousClass2 = anonymousClass2;
                return coroutine_suspended;
            }
            if (i2 == 1) {
                Function1<? super ExtractorLink, Unit> function6 = (Function1) anonymousClass2.L$4;
                function4 = (Function1) anonymousClass2.L$3;
                str4 = (String) anonymousClass2.L$2;
                str3 = (String) anonymousClass2.L$1;
                gUpload2 = (GUpload) anonymousClass2.L$0;
                ResultKt.throwOnFailure(objNewExtractorLink$default);
                function3 = function6;
                obj = objNewExtractorLink$default;
                i = 2;
            } else {
                if (i2 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                function5 = (Function1) anonymousClass2.L$8;
                ResultKt.throwOnFailure(objNewExtractorLink$default);
            }
            function5.invoke(objNewExtractorLink$default);
            return Unit.INSTANCE;
            Result.Companion companion = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.extractors.GUpload.VideoInfo"));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
        }
        String text = ((NiceResponse) obj).getText();
        String strSubstringBefore$default = StringsKt.substringBefore$default(StringsKt.substringAfter$default(text, "const config = ", (String) null, i, (Object) null), ";", (String) null, i, (Object) null);
        AppUtils appUtils = AppUtils.INSTANCE;
        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
            try {
                Result.Companion companion3 = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(VideoInfo.class), null, i, null));
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
                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, strSubstringBefore$default);
            } catch (SerializationException e) {
                ArchComponentExtKt.logError(e);
                objDecodeFromString = MainAPIKt.getMapper().readValue(strSubstringBefore$default, new TypeReference<VideoInfo>() { // from class: com.lagradost.cloudstream3.extractors.GUpload$getUrl$suspendImpl$$inlined$parseJson$1
                });
            } catch (Throwable unused) {
                objDecodeFromString = MainAPIKt.getMapper().readValue(strSubstringBefore$default, new TypeReference<VideoInfo>() { // from class: com.lagradost.cloudstream3.extractors.GUpload$getUrl$suspendImpl$$inlined$parseJson$1
                });
            }
        } else {
            objDecodeFromString = MainAPIKt.getMapper().readValue(strSubstringBefore$default, new TypeReference<VideoInfo>() { // from class: com.lagradost.cloudstream3.extractors.GUpload$getUrl$suspendImpl$$inlined$parseJson$1
            });
        }
        VideoInfo videoInfo = (VideoInfo) objDecodeFromString;
        Function1<? super SubtitleFile, Unit> function7 = function4;
        String name = gUpload2.getName();
        String str5 = str4;
        String name2 = gUpload2.getName();
        String videoUrl = videoInfo.getVideoUrl();
        AnonymousClass2 anonymousClass3 = new AnonymousClass2(videoInfo, null);
        anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(gUpload2);
        anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str3);
        anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str5);
        anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function7);
        anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(function3);
        anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(text);
        anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(strSubstringBefore$default);
        anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(videoInfo);
        anonymousClass2.L$8 = function3;
        anonymousClass2.label = i;
        objNewExtractorLink$default = ExtractorApiKt.newExtractorLink$default(name, name2, videoUrl, null, anonymousClass3, anonymousClass2, 8, null);
        if (objNewExtractorLink$default != coroutine_suspended) {
            function5 = function3;
            function5.invoke(objNewExtractorLink$default);
            return Unit.INSTANCE;
        }
        anonymousClass2 = anonymousClass2;
        return coroutine_suspended;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.GUpload$getUrl$2, reason: invalid class name */
    /* JADX INFO: compiled from: GUpload.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.GUpload$getUrl$2", f = "GUpload.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        final /* synthetic */ VideoInfo $playerConfig;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(VideoInfo videoInfo, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$playerConfig = videoInfo;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$playerConfig, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ExtractorLink extractorLink, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(extractorLink, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            List<String> groupValues;
            String str;
            ExtractorLink extractorLink = (ExtractorLink) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            MatchResult matchResultFind$default = Regex.find$default(new Regex("/(\\d+p)\\."), this.$playerConfig.getVideoUrl(), 0, 2, null);
            if (matchResultFind$default != null && (groupValues = matchResultFind$default.getGroupValues()) != null && (str = groupValues.get(1)) != null) {
                extractorLink.setQuality(ExtractorApiKt.getQualityFromName(str));
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: GUpload.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001Bs\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0003\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\b\u0012\u0010\b\u0003\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\b\u0012\u000e\b\u0003\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\b\b\u0003\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u001e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\bHÆ\u0003J\u0011\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\bHÆ\u0003J\u000f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003J\t\u0010!\u001a\u00020\fHÆ\u0003Ju\u0010\"\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\b2\u0010\b\u0003\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\b2\u000e\b\u0003\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\b\b\u0003\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0014\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010&\u001a\u00020'HÖ\u0081\u0004J\n\u0010(\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u001e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u001c\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0016\u0010\u000b\u001a\u00020\f8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006)"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/GUpload$VideoInfo;", "", "videoUrl", "", "posterUrl", YoutubeParsingHelper.VIDEO_ID, "primaryColor", "audioTracks", "", "subtitleTracks", "vastFallbackList", "videoOwnerId", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;J)V", "getVideoUrl", "()Ljava/lang/String;", "getPosterUrl", "getVideoId", "getPrimaryColor", "getAudioTracks", "()Ljava/util/List;", "getSubtitleTracks", "getVastFallbackList", "getVideoOwnerId", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    static final /* data */ class VideoInfo {

        @JsonProperty("audioTracks")
        private final List<Object> audioTracks;

        @JsonProperty("posterUrl")
        private final String posterUrl;

        @JsonProperty("primaryColor")
        private final String primaryColor;

        @JsonProperty("subtitleTracks")
        private final List<Object> subtitleTracks;

        @JsonProperty("vastFallbackList")
        private final List<String> vastFallbackList;

        @JsonProperty(YoutubeParsingHelper.VIDEO_ID)
        private final String videoId;

        @JsonProperty("videoOwnerId")
        private final long videoOwnerId;

        @JsonProperty("videoUrl")
        private final String videoUrl;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ VideoInfo copy$default(VideoInfo videoInfo, String str, String str2, String str3, String str4, List list, List list2, List list3, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                str = videoInfo.videoUrl;
            }
            if ((i & 2) != 0) {
                str2 = videoInfo.posterUrl;
            }
            if ((i & 4) != 0) {
                str3 = videoInfo.videoId;
            }
            if ((i & 8) != 0) {
                str4 = videoInfo.primaryColor;
            }
            if ((i & 16) != 0) {
                list = videoInfo.audioTracks;
            }
            if ((i & 32) != 0) {
                list2 = videoInfo.subtitleTracks;
            }
            if ((i & 64) != 0) {
                list3 = videoInfo.vastFallbackList;
            }
            if ((i & 128) != 0) {
                j = videoInfo.videoOwnerId;
            }
            long j2 = j;
            List list4 = list2;
            List list5 = list3;
            List list6 = list;
            String str5 = str3;
            return videoInfo.copy(str, str2, str5, str4, list6, list4, list5, j2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getVideoUrl() {
            return this.videoUrl;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getPosterUrl() {
            return this.posterUrl;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getVideoId() {
            return this.videoId;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getPrimaryColor() {
            return this.primaryColor;
        }

        public final List<Object> component5() {
            return this.audioTracks;
        }

        public final List<Object> component6() {
            return this.subtitleTracks;
        }

        public final List<String> component7() {
            return this.vastFallbackList;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final long getVideoOwnerId() {
            return this.videoOwnerId;
        }

        public final VideoInfo copy(@JsonProperty("videoUrl") String videoUrl, @JsonProperty("posterUrl") String posterUrl, @JsonProperty(YoutubeParsingHelper.VIDEO_ID) String videoId, @JsonProperty("primaryColor") String primaryColor, @JsonProperty("audioTracks") List<? extends Object> audioTracks, @JsonProperty("subtitleTracks") List<? extends Object> subtitleTracks, @JsonProperty("vastFallbackList") List<String> vastFallbackList, @JsonProperty("videoOwnerId") long videoOwnerId) {
            Intrinsics.checkNotNullParameter(videoUrl, "videoUrl");
            Intrinsics.checkNotNullParameter(audioTracks, "audioTracks");
            Intrinsics.checkNotNullParameter(subtitleTracks, "subtitleTracks");
            Intrinsics.checkNotNullParameter(vastFallbackList, "vastFallbackList");
            return new VideoInfo(videoUrl, posterUrl, videoId, primaryColor, audioTracks, subtitleTracks, vastFallbackList, videoOwnerId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VideoInfo)) {
                return false;
            }
            VideoInfo videoInfo = (VideoInfo) other;
            return Intrinsics.areEqual(this.videoUrl, videoInfo.videoUrl) && Intrinsics.areEqual(this.posterUrl, videoInfo.posterUrl) && Intrinsics.areEqual(this.videoId, videoInfo.videoId) && Intrinsics.areEqual(this.primaryColor, videoInfo.primaryColor) && Intrinsics.areEqual(this.audioTracks, videoInfo.audioTracks) && Intrinsics.areEqual(this.subtitleTracks, videoInfo.subtitleTracks) && Intrinsics.areEqual(this.vastFallbackList, videoInfo.vastFallbackList) && this.videoOwnerId == videoInfo.videoOwnerId;
        }

        public int hashCode() {
            int iHashCode = this.videoUrl.hashCode() * 31;
            String str = this.posterUrl;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.videoId;
            int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.primaryColor;
            return ((((((((iHashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31) + this.audioTracks.hashCode()) * 31) + this.subtitleTracks.hashCode()) * 31) + this.vastFallbackList.hashCode()) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.videoOwnerId);
        }

        public String toString() {
            return "VideoInfo(videoUrl=" + this.videoUrl + ", posterUrl=" + this.posterUrl + ", videoId=" + this.videoId + ", primaryColor=" + this.primaryColor + ", audioTracks=" + this.audioTracks + ", subtitleTracks=" + this.subtitleTracks + ", vastFallbackList=" + this.vastFallbackList + ", videoOwnerId=" + this.videoOwnerId + ')';
        }

        public VideoInfo(@JsonProperty("videoUrl") String videoUrl, @JsonProperty("posterUrl") String str, @JsonProperty(YoutubeParsingHelper.VIDEO_ID) String str2, @JsonProperty("primaryColor") String str3, @JsonProperty("audioTracks") List<? extends Object> audioTracks, @JsonProperty("subtitleTracks") List<? extends Object> subtitleTracks, @JsonProperty("vastFallbackList") List<String> vastFallbackList, @JsonProperty("videoOwnerId") long j) {
            Intrinsics.checkNotNullParameter(videoUrl, "videoUrl");
            Intrinsics.checkNotNullParameter(audioTracks, "audioTracks");
            Intrinsics.checkNotNullParameter(subtitleTracks, "subtitleTracks");
            Intrinsics.checkNotNullParameter(vastFallbackList, "vastFallbackList");
            this.videoUrl = videoUrl;
            this.posterUrl = str;
            this.videoId = str2;
            this.primaryColor = str3;
            this.audioTracks = audioTracks;
            this.subtitleTracks = subtitleTracks;
            this.vastFallbackList = vastFallbackList;
            this.videoOwnerId = j;
        }

        public final String getVideoUrl() {
            return this.videoUrl;
        }

        public final String getPosterUrl() {
            return this.posterUrl;
        }

        public final String getVideoId() {
            return this.videoId;
        }

        public final String getPrimaryColor() {
            return this.primaryColor;
        }

        public /* synthetic */ VideoInfo(String str, String str2, String str3, String str4, List list, List list2, List list3, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? CollectionsKt.emptyList() : list, (i & 32) != 0 ? CollectionsKt.emptyList() : list2, (i & 64) != 0 ? CollectionsKt.emptyList() : list3, (i & 128) != 0 ? 0L : j);
        }

        public final List<Object> getAudioTracks() {
            return this.audioTracks;
        }

        public final List<Object> getSubtitleTracks() {
            return this.subtitleTracks;
        }

        public final List<String> getVastFallbackList() {
            return this.vastFallbackList;
        }

        public final long getVideoOwnerId() {
            return this.videoOwnerId;
        }
    }
}
