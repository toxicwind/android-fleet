package com.lagradost.cloudstream3.extractors;

import coil3.util.UtilsKt;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Tantifilm.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001:\u0002\u0017\u0018B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00122\u0006\u0010\u0014\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u0005H\u0096@¢\u0006\u0002\u0010\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u0014\u0010\r\u001a\u00020\u000eX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0019"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Tantifilm;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "mainUrl", "getMainUrl", "setMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "getUrl", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", ImagesContract.URL, "referer", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "TantifilmJsonData", "TantifilmData", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class Tantifilm extends ExtractorApi {
    private final boolean requiresReferer;
    private String name = "Tantifilm";
    private String mainUrl = "https://cercafilm.net";

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.Tantifilm$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: Tantifilm.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Tantifilm", f = "Tantifilm.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {30, 33}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "link", "$this", ImagesContract.URL, "referer", "link", "response", "jsonvideodata", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "it", "$i$f$map", "$i$f$mapTo", "$i$a$-map-Tantifilm$getUrl$2"}, nl = {31, 40}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$10", "L$11", "I$0", "I$1", "I$2"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
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
            return Tantifilm.getUrl$suspendImpl(Tantifilm.this, (String) null, (String) null, (Continuation<? super List<? extends ExtractorLink>>) this);
        }
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public Object getUrl(String str, String str2, Continuation<? super List<? extends ExtractorLink>> continuation) {
        return getUrl$suspendImpl(this, str, str2, continuation);
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

    /* JADX INFO: compiled from: Tantifilm.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0001\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u000e\b\u0001\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005\u0012\b\b\u0001\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J=\u0010\u0015\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\u000e\b\u0003\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0003\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00052\b\b\u0003\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0016\u001a\u00020\u00032\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\bHÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0016\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\r¨\u0006\u001b"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Tantifilm$TantifilmJsonData;", "", "success", "", "data", "", "Lcom/lagradost/cloudstream3/extractors/Tantifilm$TantifilmData;", "captions", "", "is_vr", "<init>", "(ZLjava/util/List;Ljava/util/List;Z)V", "getSuccess", "()Z", "getData", "()Ljava/util/List;", "getCaptions", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class TantifilmJsonData {

        @JsonProperty("captions")
        private final List<String> captions;

        @JsonProperty("data")
        private final List<TantifilmData> data;

        @JsonProperty("is_vr")
        private final boolean is_vr;

        @JsonProperty("success")
        private final boolean success;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ TantifilmJsonData copy$default(TantifilmJsonData tantifilmJsonData, boolean z, List list, List list2, boolean z2, int i, Object obj) {
            if ((i & 1) != 0) {
                z = tantifilmJsonData.success;
            }
            if ((i & 2) != 0) {
                list = tantifilmJsonData.data;
            }
            if ((i & 4) != 0) {
                list2 = tantifilmJsonData.captions;
            }
            if ((i & 8) != 0) {
                z2 = tantifilmJsonData.is_vr;
            }
            return tantifilmJsonData.copy(z, list, list2, z2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getSuccess() {
            return this.success;
        }

        public final List<TantifilmData> component2() {
            return this.data;
        }

        public final List<String> component3() {
            return this.captions;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getIs_vr() {
            return this.is_vr;
        }

        public final TantifilmJsonData copy(@JsonProperty("success") boolean success, @JsonProperty("data") List<TantifilmData> data, @JsonProperty("captions") List<String> captions, @JsonProperty("is_vr") boolean is_vr) {
            Intrinsics.checkNotNullParameter(data, "data");
            Intrinsics.checkNotNullParameter(captions, "captions");
            return new TantifilmJsonData(success, data, captions, is_vr);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TantifilmJsonData)) {
                return false;
            }
            TantifilmJsonData tantifilmJsonData = (TantifilmJsonData) other;
            return this.success == tantifilmJsonData.success && Intrinsics.areEqual(this.data, tantifilmJsonData.data) && Intrinsics.areEqual(this.captions, tantifilmJsonData.captions) && this.is_vr == tantifilmJsonData.is_vr;
        }

        public int hashCode() {
            return (((((NextAiring$$ExternalSyntheticBackport0.m(this.success) * 31) + this.data.hashCode()) * 31) + this.captions.hashCode()) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.is_vr);
        }

        public String toString() {
            return "TantifilmJsonData(success=" + this.success + ", data=" + this.data + ", captions=" + this.captions + ", is_vr=" + this.is_vr + ')';
        }

        public TantifilmJsonData(@JsonProperty("success") boolean z, @JsonProperty("data") List<TantifilmData> data, @JsonProperty("captions") List<String> captions, @JsonProperty("is_vr") boolean z2) {
            Intrinsics.checkNotNullParameter(data, "data");
            Intrinsics.checkNotNullParameter(captions, "captions");
            this.success = z;
            this.data = data;
            this.captions = captions;
            this.is_vr = z2;
        }

        public final boolean getSuccess() {
            return this.success;
        }

        public final List<TantifilmData> getData() {
            return this.data;
        }

        public final List<String> getCaptions() {
            return this.captions;
        }

        public final boolean is_vr() {
            return this.is_vr;
        }
    }

    /* JADX INFO: compiled from: Tantifilm.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Tantifilm$TantifilmData;", "", UtilsKt.SCHEME_FILE, "", "label", "type", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getFile", "()Ljava/lang/String;", "getLabel", "getType", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class TantifilmData {

        @JsonProperty(UtilsKt.SCHEME_FILE)
        private final String file;

        @JsonProperty("label")
        private final String label;

        @JsonProperty("type")
        private final String type;

        public static /* synthetic */ TantifilmData copy$default(TantifilmData tantifilmData, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = tantifilmData.file;
            }
            if ((i & 2) != 0) {
                str2 = tantifilmData.label;
            }
            if ((i & 4) != 0) {
                str3 = tantifilmData.type;
            }
            return tantifilmData.copy(str, str2, str3);
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
        public final String getType() {
            return this.type;
        }

        public final TantifilmData copy(@JsonProperty(UtilsKt.SCHEME_FILE) String file, @JsonProperty("label") String label, @JsonProperty("type") String type) {
            Intrinsics.checkNotNullParameter(file, "file");
            Intrinsics.checkNotNullParameter(label, "label");
            Intrinsics.checkNotNullParameter(type, "type");
            return new TantifilmData(file, label, type);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TantifilmData)) {
                return false;
            }
            TantifilmData tantifilmData = (TantifilmData) other;
            return Intrinsics.areEqual(this.file, tantifilmData.file) && Intrinsics.areEqual(this.label, tantifilmData.label) && Intrinsics.areEqual(this.type, tantifilmData.type);
        }

        public int hashCode() {
            return (((this.file.hashCode() * 31) + this.label.hashCode()) * 31) + this.type.hashCode();
        }

        public String toString() {
            return "TantifilmData(file=" + this.file + ", label=" + this.label + ", type=" + this.type + ')';
        }

        public TantifilmData(@JsonProperty(UtilsKt.SCHEME_FILE) String file, @JsonProperty("label") String label, @JsonProperty("type") String type) {
            Intrinsics.checkNotNullParameter(file, "file");
            Intrinsics.checkNotNullParameter(label, "label");
            Intrinsics.checkNotNullParameter(type, "type");
            this.file = file;
            this.label = label;
            this.type = type;
        }

        public final String getFile() {
            return this.file;
        }

        public final String getLabel() {
            return this.label;
        }

        public final String getType() {
            return this.type;
        }
    }

    /* JADX WARN: Code duplicated, block: B:51:0x01e5  */
    /* JADX WARN: Code duplicated, block: B:54:0x027c  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:54:0x027c -> B:55:0x0288). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    static /* synthetic */ java.lang.Object getUrl$suspendImpl(com.lagradost.cloudstream3.extractors.Tantifilm r26, java.lang.String r27, java.lang.String r28, kotlin.coroutines.Continuation<? super java.util.List<? extends com.lagradost.cloudstream3.utils.ExtractorLink>> r29) {
        /*
            Method dump skipped, instruction units count: 668
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.extractors.Tantifilm.getUrl$suspendImpl(com.lagradost.cloudstream3.extractors.Tantifilm, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
