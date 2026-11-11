package com.lagradost.cloudstream3.extractors;

import androidx.core.app.NotificationCompat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.Qualities;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/* JADX INFO: compiled from: Vinovo.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001:\u0001\u0018B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000f0\u00132\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u000f0\u0013H\u0096@¢\u0006\u0002\u0010\u0017R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/VinovoTo;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "mainUrl", "", "getMainUrl", "()Ljava/lang/String;", "name", "getName", "requiresReferer", "", "getRequiresReferer", "()Z", "getUrl", "", ImagesContract.URL, "referer", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "VinovoFileResp", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class VinovoTo extends ExtractorApi {
    private final String mainUrl = "https://vinovo.to";
    private final String name = "VinovoTo";
    private final boolean requiresReferer;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.VinovoTo$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: Vinovo.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.VinovoTo", f = "Vinovo.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, l = {30, 39, 41, 54}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "fixedUrl", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "fixedUrl", "resp", "doc", "videoBaseUrl", "videoToken", "fileCode", "captchaToken", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "fixedUrl", "resp", "doc", "videoBaseUrl", "videoToken", "fileCode", "captchaToken", "captchaSolution", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "fixedUrl", "resp", "doc", "videoBaseUrl", "videoToken", "fileCode", "captchaToken", "captchaSolution", "streamInfo", "fileUrl"}, nl = {31, 38, 50, 53}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$15;
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
            return VinovoTo.getUrl$suspendImpl(VinovoTo.this, (String) null, (String) null, (Function1<? super SubtitleFile, Unit>) null, (Function1<? super ExtractorLink, Unit>) null, (Continuation<? super Unit>) this);
        }
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public Object getUrl(String str, String str2, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super Unit> continuation) {
        return getUrl$suspendImpl(this, str, str2, function1, function2, continuation);
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public String getMainUrl() {
        return this.mainUrl;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public String getName() {
        return this.name;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public boolean getRequiresReferer() {
        return this.requiresReferer;
    }

    /* JADX WARN: Code duplicated, block: B:50:0x0234  */
    /* JADX WARN: Code duplicated, block: B:52:0x0237  */
    /* JADX WARN: Code duplicated, block: B:55:0x0312  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x03db, code lost:
    
        if (r1 == r2) goto L58;
     */
    /* JADX WARN: Instruction removed from duplicated block: B:52:0x0237, please report this as an issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static /* synthetic */ java.lang.Object getUrl$suspendImpl(com.lagradost.cloudstream3.extractors.VinovoTo r34, java.lang.String r35, java.lang.String r36, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r37, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r38, kotlin.coroutines.Continuation<? super kotlin.Unit> r39) {
        /*
            Method dump skipped, instruction units count: 1008
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.extractors.VinovoTo.getUrl$suspendImpl(com.lagradost.cloudstream3.extractors.VinovoTo, java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.VinovoTo$getUrl$2, reason: invalid class name */
    /* JADX INFO: compiled from: Vinovo.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.VinovoTo$getUrl$2", f = "Vinovo.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        final /* synthetic */ Document $doc;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Document document, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$doc = document;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$doc, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ExtractorLink extractorLink, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(extractorLink, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            int value;
            List<String> groupValues;
            String str;
            Integer intOrNull;
            ExtractorLink extractorLink = (ExtractorLink) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Element elementSelectFirst = this.$doc.selectFirst("video");
            String strAttr = elementSelectFirst != null ? elementSelectFirst.attr("data-title") : null;
            if (strAttr == null) {
                strAttr = "";
            }
            MatchResult matchResultFind$default = Regex.find$default(new Regex("\\.(\\d+)p\\."), strAttr, 0, 2, null);
            if (matchResultFind$default != null && (groupValues = matchResultFind$default.getGroupValues()) != null && (str = (String) CollectionsKt.getOrNull(groupValues, 1)) != null && (intOrNull = StringsKt.toIntOrNull(str)) != null) {
                value = intOrNull.intValue();
            } else {
                value = Qualities.Unknown.getValue();
            }
            extractorLink.setQuality(value);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: compiled from: Vinovo.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/VinovoTo$VinovoFileResp;", "", NotificationCompat.CATEGORY_STATUS, "", "token", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getStatus", "()Ljava/lang/String;", "getToken", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private static final /* data */ class VinovoFileResp {

        @JsonProperty(NotificationCompat.CATEGORY_STATUS)
        private final String status;

        @JsonProperty("token")
        private final String token;

        public static /* synthetic */ VinovoFileResp copy$default(VinovoFileResp vinovoFileResp, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = vinovoFileResp.status;
            }
            if ((i & 2) != 0) {
                str2 = vinovoFileResp.token;
            }
            return vinovoFileResp.copy(str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getStatus() {
            return this.status;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getToken() {
            return this.token;
        }

        public final VinovoFileResp copy(@JsonProperty(NotificationCompat.CATEGORY_STATUS) String status, @JsonProperty("token") String token) {
            Intrinsics.checkNotNullParameter(status, "status");
            Intrinsics.checkNotNullParameter(token, "token");
            return new VinovoFileResp(status, token);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VinovoFileResp)) {
                return false;
            }
            VinovoFileResp vinovoFileResp = (VinovoFileResp) other;
            return Intrinsics.areEqual(this.status, vinovoFileResp.status) && Intrinsics.areEqual(this.token, vinovoFileResp.token);
        }

        public int hashCode() {
            return (this.status.hashCode() * 31) + this.token.hashCode();
        }

        public String toString() {
            return "VinovoFileResp(status=" + this.status + ", token=" + this.token + ')';
        }

        public VinovoFileResp(@JsonProperty(NotificationCompat.CATEGORY_STATUS) String status, @JsonProperty("token") String token) {
            Intrinsics.checkNotNullParameter(status, "status");
            Intrinsics.checkNotNullParameter(token, "token");
            this.status = status;
            this.token = token;
        }

        public final String getStatus() {
            return this.status;
        }

        public final String getToken() {
            return this.token;
        }
    }
}
