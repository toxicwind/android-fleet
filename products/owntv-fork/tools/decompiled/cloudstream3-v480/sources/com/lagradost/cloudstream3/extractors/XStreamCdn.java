package com.lagradost.cloudstream3.extractors;

import coil3.util.UtilsKt;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: XStreamCdn.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001:\u0004\u001e\u001f !B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0005H\u0016JH\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00052\b\u0010\u0017\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00150\u00192\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00150\u0019H\u0096@¢\u0006\u0002\u0010\u001dR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0007\"\u0004\b\u0010\u0010\u0011¨\u0006\""}, d2 = {"Lcom/lagradost/cloudstream3/extractors/XStreamCdn;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "mainUrl", "getMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "domainUrl", "getDomainUrl", "setDomainUrl", "(Ljava/lang/String;)V", "getExtractorUrl", "id", "getUrl", "", ImagesContract.URL, "referer", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ResponseData", "Player", "ResponseJson", "Captions", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class XStreamCdn extends ExtractorApi {
    private final boolean requiresReferer;
    private final String name = "XStreamCdn";
    private final String mainUrl = "https://embedsito.com";
    private String domainUrl = "embedsito.com";

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.XStreamCdn$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: XStreamCdn.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.XStreamCdn", f = "XStreamCdn.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {119, 125, 141}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "headers", "id", "newUrl", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "headers", "id", "newUrl", "res", "it", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "source", "sources", "$i$a$-let-XStreamCdn$getUrl$2", "$i$a$-let-XStreamCdn$getUrl$2$1", "$i$f$map", "$i$f$mapTo", "$i$a$-map-XStreamCdn$getUrl$2$1$1", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "headers", "id", "newUrl", "res", "userData", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "it", "sources", "$i$a$-let-XStreamCdn$getUrl$2", "$i$f$map", "$i$f$mapTo", "$i$a$-map-XStreamCdn$getUrl$2$2"}, nl = {120, 124, 140}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$14", "L$15", "L$17", "I$0", "I$1", "I$2", "I$3", "I$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$14", "L$15", "L$17", "I$0", "I$1", "I$2", "I$3"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
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
        Object L$18;
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
            return XStreamCdn.getUrl$suspendImpl(XStreamCdn.this, (String) null, (String) null, (Function1<? super SubtitleFile, Unit>) null, (Function1<? super ExtractorLink, Unit>) null, (Continuation<? super Unit>) this);
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

    public String getDomainUrl() {
        return this.domainUrl;
    }

    public void setDomainUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.domainUrl = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: XStreamCdn.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/XStreamCdn$ResponseData;", "", UtilsKt.SCHEME_FILE, "", "label", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getFile", "()Ljava/lang/String;", "getLabel", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    static final /* data */ class ResponseData {

        @JsonProperty(UtilsKt.SCHEME_FILE)
        private final String file;

        @JsonProperty("label")
        private final String label;

        public static /* synthetic */ ResponseData copy$default(ResponseData responseData, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = responseData.file;
            }
            if ((i & 2) != 0) {
                str2 = responseData.label;
            }
            return responseData.copy(str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getFile() {
            return this.file;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getLabel() {
            return this.label;
        }

        public final ResponseData copy(@JsonProperty(UtilsKt.SCHEME_FILE) String file, @JsonProperty("label") String label) {
            Intrinsics.checkNotNullParameter(file, "file");
            Intrinsics.checkNotNullParameter(label, "label");
            return new ResponseData(file, label);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ResponseData)) {
                return false;
            }
            ResponseData responseData = (ResponseData) other;
            return Intrinsics.areEqual(this.file, responseData.file) && Intrinsics.areEqual(this.label, responseData.label);
        }

        public int hashCode() {
            return (this.file.hashCode() * 31) + this.label.hashCode();
        }

        public String toString() {
            return "ResponseData(file=" + this.file + ", label=" + this.label + ')';
        }

        public ResponseData(@JsonProperty(UtilsKt.SCHEME_FILE) String file, @JsonProperty("label") String label) {
            Intrinsics.checkNotNullParameter(file, "file");
            Intrinsics.checkNotNullParameter(label, "label");
            this.file = file;
            this.label = label;
        }

        public final String getFile() {
            return this.file;
        }

        public final String getLabel() {
            return this.label;
        }
    }

    /* JADX INFO: compiled from: XStreamCdn.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/XStreamCdn$Player;", "", "poster_file", "", "<init>", "(Ljava/lang/String;)V", "getPoster_file", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private static final /* data */ class Player {

        @JsonProperty("poster_file")
        private final String poster_file;

        /* JADX WARN: Multi-variable type inference failed */
        public Player() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ Player copy$default(Player player, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = player.poster_file;
            }
            return player.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getPoster_file() {
            return this.poster_file;
        }

        public final Player copy(@JsonProperty("poster_file") String poster_file) {
            return new Player(poster_file);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Player) && Intrinsics.areEqual(this.poster_file, ((Player) other).poster_file);
        }

        public int hashCode() {
            String str = this.poster_file;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        public String toString() {
            return "Player(poster_file=" + this.poster_file + ')';
        }

        public Player(@JsonProperty("poster_file") String str) {
            this.poster_file = str;
        }

        public /* synthetic */ Player(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str);
        }

        public final String getPoster_file() {
            return this.poster_file;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: XStreamCdn.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001BC\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0001\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\u0012\b\u0001\u0010\t\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u0007¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0011\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0003J\u0013\u0010\u0017\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u0007HÆ\u0003JE\u0010\u0018\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0010\b\u0003\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\u0012\b\u0003\u0010\t\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u0007HÆ\u0001J\u0014\u0010\u0019\u001a\u00020\u00032\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u001eHÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R \u0010\t\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012¨\u0006\u001f"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/XStreamCdn$ResponseJson;", "", "success", "", "player", "Lcom/lagradost/cloudstream3/extractors/XStreamCdn$Player;", "data", "", "Lcom/lagradost/cloudstream3/extractors/XStreamCdn$ResponseData;", "captions", "Lcom/lagradost/cloudstream3/extractors/XStreamCdn$Captions;", "<init>", "(ZLcom/lagradost/cloudstream3/extractors/XStreamCdn$Player;Ljava/util/List;Ljava/util/List;)V", "getSuccess", "()Z", "getPlayer", "()Lcom/lagradost/cloudstream3/extractors/XStreamCdn$Player;", "getData", "()Ljava/util/List;", "getCaptions", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    static final /* data */ class ResponseJson {

        @JsonProperty("captions")
        private final List<Captions> captions;

        @JsonProperty("data")
        private final List<ResponseData> data;

        @JsonProperty("player")
        private final Player player;

        @JsonProperty("success")
        private final boolean success;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ResponseJson copy$default(ResponseJson responseJson, boolean z, Player player, List list, List list2, int i, Object obj) {
            if ((i & 1) != 0) {
                z = responseJson.success;
            }
            if ((i & 2) != 0) {
                player = responseJson.player;
            }
            if ((i & 4) != 0) {
                list = responseJson.data;
            }
            if ((i & 8) != 0) {
                list2 = responseJson.captions;
            }
            return responseJson.copy(z, player, list, list2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getSuccess() {
            return this.success;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Player getPlayer() {
            return this.player;
        }

        public final List<ResponseData> component3() {
            return this.data;
        }

        public final List<Captions> component4() {
            return this.captions;
        }

        public final ResponseJson copy(@JsonProperty("success") boolean success, @JsonProperty("player") Player player, @JsonProperty("data") List<ResponseData> data, @JsonProperty("captions") List<Captions> captions) {
            return new ResponseJson(success, player, data, captions);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ResponseJson)) {
                return false;
            }
            ResponseJson responseJson = (ResponseJson) other;
            return this.success == responseJson.success && Intrinsics.areEqual(this.player, responseJson.player) && Intrinsics.areEqual(this.data, responseJson.data) && Intrinsics.areEqual(this.captions, responseJson.captions);
        }

        public int hashCode() {
            int iM = NextAiring$$ExternalSyntheticBackport0.m(this.success) * 31;
            Player player = this.player;
            int iHashCode = (iM + (player == null ? 0 : player.hashCode())) * 31;
            List<ResponseData> list = this.data;
            int iHashCode2 = (iHashCode + (list == null ? 0 : list.hashCode())) * 31;
            List<Captions> list2 = this.captions;
            return iHashCode2 + (list2 != null ? list2.hashCode() : 0);
        }

        public String toString() {
            return "ResponseJson(success=" + this.success + ", player=" + this.player + ", data=" + this.data + ", captions=" + this.captions + ')';
        }

        public ResponseJson(@JsonProperty("success") boolean z, @JsonProperty("player") Player player, @JsonProperty("data") List<ResponseData> list, @JsonProperty("captions") List<Captions> list2) {
            this.success = z;
            this.player = player;
            this.data = list;
            this.captions = list2;
        }

        public /* synthetic */ ResponseJson(boolean z, Player player, List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(z, (i & 2) != 0 ? null : player, list, list2);
        }

        public final boolean getSuccess() {
            return this.success;
        }

        public final Player getPlayer() {
            return this.player;
        }

        public final List<ResponseData> getData() {
            return this.data;
        }

        public final List<Captions> getCaptions() {
            return this.captions;
        }
    }

    /* JADX INFO: compiled from: XStreamCdn.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B/\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J1\u0010\u0012\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/XStreamCdn$Captions;", "", "id", "", "hash", "language", "extension", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getHash", "getLanguage", "getExtension", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private static final /* data */ class Captions {

        @JsonProperty("extension")
        private final String extension;

        @JsonProperty("hash")
        private final String hash;

        @JsonProperty("id")
        private final String id;

        @JsonProperty("language")
        private final String language;

        public static /* synthetic */ Captions copy$default(Captions captions, String str, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                str = captions.id;
            }
            if ((i & 2) != 0) {
                str2 = captions.hash;
            }
            if ((i & 4) != 0) {
                str3 = captions.language;
            }
            if ((i & 8) != 0) {
                str4 = captions.extension;
            }
            return captions.copy(str, str2, str3, str4);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getHash() {
            return this.hash;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getLanguage() {
            return this.language;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getExtension() {
            return this.extension;
        }

        public final Captions copy(@JsonProperty("id") String id, @JsonProperty("hash") String hash, @JsonProperty("language") String language, @JsonProperty("extension") String extension) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(hash, "hash");
            Intrinsics.checkNotNullParameter(language, "language");
            Intrinsics.checkNotNullParameter(extension, "extension");
            return new Captions(id, hash, language, extension);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Captions)) {
                return false;
            }
            Captions captions = (Captions) other;
            return Intrinsics.areEqual(this.id, captions.id) && Intrinsics.areEqual(this.hash, captions.hash) && Intrinsics.areEqual(this.language, captions.language) && Intrinsics.areEqual(this.extension, captions.extension);
        }

        public int hashCode() {
            return (((((this.id.hashCode() * 31) + this.hash.hashCode()) * 31) + this.language.hashCode()) * 31) + this.extension.hashCode();
        }

        public String toString() {
            return "Captions(id=" + this.id + ", hash=" + this.hash + ", language=" + this.language + ", extension=" + this.extension + ')';
        }

        public Captions(@JsonProperty("id") String id, @JsonProperty("hash") String hash, @JsonProperty("language") String language, @JsonProperty("extension") String extension) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(hash, "hash");
            Intrinsics.checkNotNullParameter(language, "language");
            Intrinsics.checkNotNullParameter(extension, "extension");
            this.id = id;
            this.hash = hash;
            this.language = language;
            this.extension = extension;
        }

        public final String getId() {
            return this.id;
        }

        public final String getHash() {
            return this.hash;
        }

        public final String getLanguage() {
            return this.language;
        }

        public final String getExtension() {
            return this.extension;
        }
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public String getExtractorUrl(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        return getDomainUrl() + "/api/source/" + id;
    }

    /* JADX WARN: Code duplicated, block: B:56:0x0310  */
    /* JADX WARN: Code duplicated, block: B:59:0x03af  */
    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:59:0x03af -> B:60:0x03ca). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:97:0x053e -> B:98:0x0555). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    static /* synthetic */ java.lang.Object getUrl$suspendImpl(com.lagradost.cloudstream3.extractors.XStreamCdn r34, java.lang.String r35, java.lang.String r36, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r37, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r38, kotlin.coroutines.Continuation<? super kotlin.Unit> r39) {
        /*
            Method dump skipped, instruction units count: 1402
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.extractors.XStreamCdn.getUrl$suspendImpl(com.lagradost.cloudstream3.extractors.XStreamCdn, java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
