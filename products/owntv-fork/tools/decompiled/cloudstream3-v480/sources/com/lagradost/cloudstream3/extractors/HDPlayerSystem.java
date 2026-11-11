package com.lagradost.cloudstream3.extractors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.net.HttpHeaders;
import com.lagradost.cloudstream3.ErrorLoadingException;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorApiKt;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import com.lagradost.nicehttp.ResponseParser;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
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
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: HDPlayerSystemExtractor.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001:\u0001\u0018B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000f0\u00132\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u000f0\u0013H\u0096@¢\u0006\u0002\u0010\u0017R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/HDPlayerSystem;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "mainUrl", "getMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "getUrl", "", ImagesContract.URL, "referer", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "SystemResponse", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class HDPlayerSystem extends ExtractorApi {
    private final String name = "HDPlayerSystem";
    private final String mainUrl = "https://hdplayersystem.live";
    private final boolean requiresReferer = true;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.HDPlayerSystem$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: HDPlayerSystemExtractor.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.HDPlayerSystem", f = "HDPlayerSystemExtractor.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {24, 41}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "extRef", "vidId", "postUrl", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "extRef", "vidId", "postUrl", "response", "videoResponse", "m3uLink"}, nl = {37, 40}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
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

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HDPlayerSystem.getUrl$suspendImpl(HDPlayerSystem.this, (String) null, (String) null, (Function1<? super SubtitleFile, Unit>) null, (Function1<? super ExtractorLink, Unit>) null, (Continuation<? super Unit>) this);
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

    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    static /* synthetic */ Object getUrl$suspendImpl(HDPlayerSystem hDPlayerSystem, String str, String str2, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super Unit> continuation) throws ErrorLoadingException {
        AnonymousClass1 anonymousClass1;
        String strSubstringAfter$default;
        String str3;
        String str4;
        String str5;
        Continuation continuation2;
        Object objPost$default;
        String str6;
        Function1<? super SubtitleFile, Unit> function3;
        HDPlayerSystem hDPlayerSystem2;
        String str7;
        Function1<? super ExtractorLink, Unit> function4;
        NiceResponse niceResponse;
        Object safe;
        Function1<? super ExtractorLink, Unit> function5;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = hDPlayerSystem.new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = hDPlayerSystem.new AnonymousClass1(continuation);
        }
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object objNewExtractorLink$default = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass2.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(objNewExtractorLink$default);
                String str8 = str2 == null ? "" : str2;
                if (StringsKt.contains$default((CharSequence) str, (CharSequence) "video/", false, 2, (Object) null)) {
                    strSubstringAfter$default = StringsKt.substringAfter$default(str, "video/", (String) null, 2, (Object) null);
                } else {
                    strSubstringAfter$default = StringsKt.substringAfter$default(str, "?data=", (String) null, 2, (Object) null);
                }
                String str9 = hDPlayerSystem.getMainUrl() + "/player/index.php?data=" + strSubstringAfter$default + "&do=getVideo";
                Requests app2 = MainActivityKt.getApp();
                Map mapMapOf = MapsKt.mapOf(TuplesKt.to("hash", strSubstringAfter$default), TuplesKt.to("r", str8));
                Map mapMapOf2 = MapsKt.mapOf(TuplesKt.to("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8"), TuplesKt.to(HttpHeaders.X_REQUESTED_WITH, "XMLHttpRequest"));
                anonymousClass2.L$0 = hDPlayerSystem;
                anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str);
                anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str2);
                anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function1);
                anonymousClass2.L$4 = function2;
                anonymousClass2.L$5 = str8;
                anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(strSubstringAfter$default);
                anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(str9);
                anonymousClass2.label = 1;
                str3 = str9;
                str4 = strSubstringAfter$default;
                str5 = str8;
                continuation2 = null;
                objPost$default = Requests.post$default(app2, str3, mapMapOf2, str5, null, null, mapMapOf, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass2, 65496, null);
                anonymousClass2 = anonymousClass2;
                if (objPost$default != coroutine_suspended) {
                    str6 = str2;
                    function3 = function1;
                    hDPlayerSystem2 = hDPlayerSystem;
                    str7 = str;
                    function4 = function2;
                }
                return coroutine_suspended;
            }
            if (i == 1) {
                String str10 = (String) anonymousClass2.L$7;
                String str11 = (String) anonymousClass2.L$6;
                String str12 = (String) anonymousClass2.L$5;
                Function1<? super ExtractorLink, Unit> function6 = (Function1) anonymousClass2.L$4;
                function3 = (Function1) anonymousClass2.L$3;
                str6 = (String) anonymousClass2.L$2;
                str7 = (String) anonymousClass2.L$1;
                hDPlayerSystem2 = (HDPlayerSystem) anonymousClass2.L$0;
                ResultKt.throwOnFailure(objNewExtractorLink$default);
                str4 = str11;
                str5 = str12;
                function4 = function6;
                str3 = str10;
                objPost$default = objNewExtractorLink$default;
                continuation2 = null;
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                function5 = (Function1) anonymousClass2.L$11;
                ResultKt.throwOnFailure(objNewExtractorLink$default);
            }
            function5.invoke(objNewExtractorLink$default);
            return Unit.INSTANCE;
            ResponseParser parser = niceResponse.getParser();
            Intrinsics.checkNotNull(parser);
            safe = parser.parseSafe(niceResponse.getText(), Reflection.getOrCreateKotlinClass(SystemResponse.class));
        } catch (Exception e) {
            e.printStackTrace();
            safe = continuation2;
        }
        niceResponse = (NiceResponse) objPost$default;
        SystemResponse systemResponse = (SystemResponse) safe;
        if (systemResponse == null) {
            throw new ErrorLoadingException("failed to parse response");
        }
        String securedLink = systemResponse.getSecuredLink();
        String name = hDPlayerSystem2.getName();
        String str13 = str3;
        String name2 = hDPlayerSystem2.getName();
        AnonymousClass2 anonymousClass3 = new AnonymousClass2(str5, continuation2);
        anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(hDPlayerSystem2);
        anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str7);
        anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str6);
        anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function3);
        anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(function4);
        anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(str5);
        anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(str4);
        anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(str13);
        anonymousClass2.L$8 = SpillingKt.nullOutSpilledVariable(niceResponse);
        anonymousClass2.L$9 = SpillingKt.nullOutSpilledVariable(systemResponse);
        anonymousClass2.L$10 = SpillingKt.nullOutSpilledVariable(securedLink);
        anonymousClass2.L$11 = function4;
        anonymousClass2.label = 2;
        objNewExtractorLink$default = ExtractorApiKt.newExtractorLink$default(name, name2, securedLink, null, anonymousClass3, anonymousClass2, 8, null);
        if (objNewExtractorLink$default != coroutine_suspended) {
            function5 = function4;
            function5.invoke(objNewExtractorLink$default);
            return Unit.INSTANCE;
        }
        return coroutine_suspended;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.HDPlayerSystem$getUrl$2, reason: invalid class name */
    /* JADX INFO: compiled from: HDPlayerSystemExtractor.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.HDPlayerSystem$getUrl$2", f = "HDPlayerSystemExtractor.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $extRef;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$extRef = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$extRef, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ExtractorLink extractorLink, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(extractorLink, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            ExtractorLink extractorLink = (ExtractorLink) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            extractorLink.setReferer(this.$extRef);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: compiled from: HDPlayerSystemExtractor.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J3\u0010\u0012\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/HDPlayerSystem$SystemResponse;", "", "hls", "", "videoImage", "videoSource", "securedLink", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getHls", "()Ljava/lang/String;", "getVideoImage", "getVideoSource", "getSecuredLink", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class SystemResponse {

        @JsonProperty("hls")
        private final String hls;

        @JsonProperty("securedLink")
        private final String securedLink;

        @JsonProperty("videoImage")
        private final String videoImage;

        @JsonProperty("videoSource")
        private final String videoSource;

        public static /* synthetic */ SystemResponse copy$default(SystemResponse systemResponse, String str, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                str = systemResponse.hls;
            }
            if ((i & 2) != 0) {
                str2 = systemResponse.videoImage;
            }
            if ((i & 4) != 0) {
                str3 = systemResponse.videoSource;
            }
            if ((i & 8) != 0) {
                str4 = systemResponse.securedLink;
            }
            return systemResponse.copy(str, str2, str3, str4);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getHls() {
            return this.hls;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getVideoImage() {
            return this.videoImage;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getVideoSource() {
            return this.videoSource;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getSecuredLink() {
            return this.securedLink;
        }

        public final SystemResponse copy(@JsonProperty("hls") String hls, @JsonProperty("videoImage") String videoImage, @JsonProperty("videoSource") String videoSource, @JsonProperty("securedLink") String securedLink) {
            Intrinsics.checkNotNullParameter(hls, "hls");
            Intrinsics.checkNotNullParameter(videoSource, "videoSource");
            Intrinsics.checkNotNullParameter(securedLink, "securedLink");
            return new SystemResponse(hls, videoImage, videoSource, securedLink);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SystemResponse)) {
                return false;
            }
            SystemResponse systemResponse = (SystemResponse) other;
            return Intrinsics.areEqual(this.hls, systemResponse.hls) && Intrinsics.areEqual(this.videoImage, systemResponse.videoImage) && Intrinsics.areEqual(this.videoSource, systemResponse.videoSource) && Intrinsics.areEqual(this.securedLink, systemResponse.securedLink);
        }

        public int hashCode() {
            int iHashCode = this.hls.hashCode() * 31;
            String str = this.videoImage;
            return ((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.videoSource.hashCode()) * 31) + this.securedLink.hashCode();
        }

        public String toString() {
            return "SystemResponse(hls=" + this.hls + ", videoImage=" + this.videoImage + ", videoSource=" + this.videoSource + ", securedLink=" + this.securedLink + ')';
        }

        public SystemResponse(@JsonProperty("hls") String hls, @JsonProperty("videoImage") String str, @JsonProperty("videoSource") String videoSource, @JsonProperty("securedLink") String securedLink) {
            Intrinsics.checkNotNullParameter(hls, "hls");
            Intrinsics.checkNotNullParameter(videoSource, "videoSource");
            Intrinsics.checkNotNullParameter(securedLink, "securedLink");
            this.hls = hls;
            this.videoImage = str;
            this.videoSource = videoSource;
            this.securedLink = securedLink;
        }

        public /* synthetic */ SystemResponse(String str, String str2, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : str2, str3, str4);
        }

        public final String getHls() {
            return this.hls;
        }

        public final String getVideoImage() {
            return this.videoImage;
        }

        public final String getVideoSource() {
            return this.videoSource;
        }

        public final String getSecuredLink() {
            return this.securedLink;
        }
    }
}
