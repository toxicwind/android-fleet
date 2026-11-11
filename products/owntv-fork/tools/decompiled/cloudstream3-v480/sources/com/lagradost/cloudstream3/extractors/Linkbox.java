package com.lagradost.cloudstream3.extractors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Linkbox.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001:\u0004\u0018\u0019\u001a\u001bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000f0\u00132\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u000f0\u0013H\u0096@¢\u0006\u0002\u0010\u0017R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u001c"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Linkbox;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "mainUrl", "getMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "getUrl", "", ImagesContract.URL, "referer", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Resolutions", "ItemInfo", "Data", "Responses", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class Linkbox extends ExtractorApi {
    private final String name = "Linkbox";
    private final String mainUrl = "https://www.linkbox.to";
    private final boolean requiresReferer = true;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.Linkbox$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: Linkbox.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Linkbox", f = "Linkbox.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {23, 24, 27}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "token", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "token", "id", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "token", "id", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "link", "$i$f$map", "$i$f$mapTo", "$i$a$-map-Linkbox$getUrl$2"}, nl = {58, 25, 26}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$11", "L$12", "I$0", "I$1", "I$2"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
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
            return Linkbox.getUrl$suspendImpl(Linkbox.this, (String) null, (String) null, (Function1<? super SubtitleFile, Unit>) null, (Function1<? super ExtractorLink, Unit>) null, (Continuation<? super Unit>) this);
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

    /* JADX WARN: Code duplicated, block: B:60:0x026e  */
    /* JADX WARN: Code duplicated, block: B:62:0x0285  */
    /* JADX WARN: Code duplicated, block: B:63:0x028f  */
    /* JADX WARN: Code duplicated, block: B:66:0x02f9  */
    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:62:0x0285 -> B:68:0x0319). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:66:0x02f9 -> B:67:0x0307). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    static /* synthetic */ java.lang.Object getUrl$suspendImpl(com.lagradost.cloudstream3.extractors.Linkbox r26, java.lang.String r27, java.lang.String r28, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r29, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r30, kotlin.coroutines.Continuation<? super kotlin.Unit> r31) {
        /*
            Method dump skipped, instruction units count: 804
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.extractors.Linkbox.getUrl$suspendImpl(com.lagradost.cloudstream3.extractors.Linkbox, java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: compiled from: Linkbox.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\f\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Linkbox$Resolutions;", "", ImagesContract.URL, "", "resolution", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getUrl", "()Ljava/lang/String;", "getResolution", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Resolutions {

        @JsonProperty("resolution")
        private final String resolution;

        @JsonProperty(ImagesContract.URL)
        private final String url;

        /* JADX WARN: Multi-variable type inference failed */
        public Resolutions() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ Resolutions copy$default(Resolutions resolutions, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = resolutions.url;
            }
            if ((i & 2) != 0) {
                str2 = resolutions.resolution;
            }
            return resolutions.copy(str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getResolution() {
            return this.resolution;
        }

        public final Resolutions copy(@JsonProperty(ImagesContract.URL) String url, @JsonProperty("resolution") String resolution) {
            return new Resolutions(url, resolution);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Resolutions)) {
                return false;
            }
            Resolutions resolutions = (Resolutions) other;
            return Intrinsics.areEqual(this.url, resolutions.url) && Intrinsics.areEqual(this.resolution, resolutions.resolution);
        }

        public int hashCode() {
            String str = this.url;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.resolution;
            return iHashCode + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "Resolutions(url=" + this.url + ", resolution=" + this.resolution + ')';
        }

        public Resolutions(@JsonProperty(ImagesContract.URL) String str, @JsonProperty("resolution") String str2) {
            this.url = str;
            this.resolution = str2;
        }

        public /* synthetic */ Resolutions(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2);
        }

        public final String getUrl() {
            return this.url;
        }

        public final String getResolution() {
            return this.resolution;
        }
    }

    /* JADX INFO: compiled from: Linkbox.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u001c\b\u0003\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\n\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005HÆ\u0003J'\u0010\u000b\u001a\u00020\u00002\u001c\b\u0003\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005HÆ\u0001J\u0014\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004R*\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0013"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Linkbox$ItemInfo;", "", "resolutionList", "Ljava/util/ArrayList;", "Lcom/lagradost/cloudstream3/extractors/Linkbox$Resolutions;", "Lkotlin/collections/ArrayList;", "<init>", "(Ljava/util/ArrayList;)V", "getResolutionList", "()Ljava/util/ArrayList;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class ItemInfo {

        @JsonProperty("resolutionList")
        private final ArrayList<Resolutions> resolutionList;

        /* JADX WARN: Multi-variable type inference failed */
        public ItemInfo() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ItemInfo copy$default(ItemInfo itemInfo, ArrayList arrayList, int i, Object obj) {
            if ((i & 1) != 0) {
                arrayList = itemInfo.resolutionList;
            }
            return itemInfo.copy(arrayList);
        }

        public final ArrayList<Resolutions> component1() {
            return this.resolutionList;
        }

        public final ItemInfo copy(@JsonProperty("resolutionList") ArrayList<Resolutions> resolutionList) {
            return new ItemInfo(resolutionList);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof ItemInfo) && Intrinsics.areEqual(this.resolutionList, ((ItemInfo) other).resolutionList);
        }

        public int hashCode() {
            ArrayList<Resolutions> arrayList = this.resolutionList;
            if (arrayList == null) {
                return 0;
            }
            return arrayList.hashCode();
        }

        public String toString() {
            return "ItemInfo(resolutionList=" + this.resolutionList + ')';
        }

        public ItemInfo(@JsonProperty("resolutionList") ArrayList<Resolutions> arrayList) {
            this.resolutionList = arrayList;
        }

        public /* synthetic */ ItemInfo(ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? new ArrayList() : arrayList);
        }

        public final ArrayList<Resolutions> getResolutionList() {
            return this.resolutionList;
        }
    }

    /* JADX INFO: compiled from: Linkbox.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\u000e\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0005HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Linkbox$Data;", "", "itemInfo", "Lcom/lagradost/cloudstream3/extractors/Linkbox$ItemInfo;", "itemId", "", "<init>", "(Lcom/lagradost/cloudstream3/extractors/Linkbox$ItemInfo;Ljava/lang/String;)V", "getItemInfo", "()Lcom/lagradost/cloudstream3/extractors/Linkbox$ItemInfo;", "getItemId", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Data {

        @JsonProperty("itemId")
        private final String itemId;

        @JsonProperty("itemInfo")
        private final ItemInfo itemInfo;

        /* JADX WARN: Multi-variable type inference failed */
        public Data() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ Data copy$default(Data data, ItemInfo itemInfo, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                itemInfo = data.itemInfo;
            }
            if ((i & 2) != 0) {
                str = data.itemId;
            }
            return data.copy(itemInfo, str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final ItemInfo getItemInfo() {
            return this.itemInfo;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getItemId() {
            return this.itemId;
        }

        public final Data copy(@JsonProperty("itemInfo") ItemInfo itemInfo, @JsonProperty("itemId") String itemId) {
            return new Data(itemInfo, itemId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Data)) {
                return false;
            }
            Data data = (Data) other;
            return Intrinsics.areEqual(this.itemInfo, data.itemInfo) && Intrinsics.areEqual(this.itemId, data.itemId);
        }

        public int hashCode() {
            ItemInfo itemInfo = this.itemInfo;
            int iHashCode = (itemInfo == null ? 0 : itemInfo.hashCode()) * 31;
            String str = this.itemId;
            return iHashCode + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            return "Data(itemInfo=" + this.itemInfo + ", itemId=" + this.itemId + ')';
        }

        public Data(@JsonProperty("itemInfo") ItemInfo itemInfo, @JsonProperty("itemId") String str) {
            this.itemInfo = itemInfo;
            this.itemId = str;
        }

        public /* synthetic */ Data(ItemInfo itemInfo, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : itemInfo, (i & 2) != 0 ? null : str);
        }

        public final ItemInfo getItemInfo() {
            return this.itemInfo;
        }

        public final String getItemId() {
            return this.itemId;
        }
    }

    /* JADX INFO: compiled from: Linkbox.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Linkbox$Responses;", "", "data", "Lcom/lagradost/cloudstream3/extractors/Linkbox$Data;", "<init>", "(Lcom/lagradost/cloudstream3/extractors/Linkbox$Data;)V", "getData", "()Lcom/lagradost/cloudstream3/extractors/Linkbox$Data;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Responses {

        @JsonProperty("data")
        private final Data data;

        /* JADX WARN: Multi-variable type inference failed */
        public Responses() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ Responses copy$default(Responses responses, Data data, int i, Object obj) {
            if ((i & 1) != 0) {
                data = responses.data;
            }
            return responses.copy(data);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Data getData() {
            return this.data;
        }

        public final Responses copy(@JsonProperty("data") Data data) {
            return new Responses(data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Responses) && Intrinsics.areEqual(this.data, ((Responses) other).data);
        }

        public int hashCode() {
            Data data = this.data;
            if (data == null) {
                return 0;
            }
            return data.hashCode();
        }

        public String toString() {
            return "Responses(data=" + this.data + ')';
        }

        public Responses(@JsonProperty("data") Data data) {
            this.data = data;
        }

        public /* synthetic */ Responses(Data data, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : data);
        }

        public final Data getData() {
            return this.data;
        }
    }
}
