package com.lagradost.cloudstream3.extractors;

import coil3.util.UtilsKt;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.actions.temp.CloudStreamPackage;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.text.CharsKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;

/* JADX INFO: compiled from: StreamSB.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u0001:\u0003\u001f !B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00130\u00172\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00130\u0017H\u0096@¢\u0006\u0002\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0005H\u0002J\b\u0010\u001e\u001a\u00020\u0005H\u0002R\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u0014\u0010\r\u001a\u00020\u000eX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/lagradost/cloudstream3/extractors/StreamSB;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "mainUrl", "getMainUrl", "setMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "alphabet", "getUrl", "", ImagesContract.URL, "referer", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "encodeId", "id", "createHashTable", "Subs", "StreamData", "Main", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class StreamSB extends ExtractorApi {
    private final boolean requiresReferer;
    private String name = "StreamSB";
    private String mainUrl = "https://watchsb.com";
    private final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.StreamSB$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: StreamSB.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.StreamSB", f = "StreamSB.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {155, 160, 169}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "regexID", "id", "master", "headers", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "regexID", "id", "master", "headers", "mapped", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "regexID", "id", "master", "headers", "mapped", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "sub", "$i$f$map", "$i$f$mapTo", "$i$a$-map-StreamSB$getUrl$2"}, nl = {159, 165, 168}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$14", "L$15", "I$0", "I$1", "I$2"}, v = 2)
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

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return StreamSB.getUrl$suspendImpl(StreamSB.this, (String) null, (String) null, (Function1<? super SubtitleFile, Unit>) null, (Function1<? super ExtractorLink, Unit>) null, (Continuation<? super Unit>) this);
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

    /* JADX WARN: Code duplicated, block: B:47:0x02a9  */
    /* JADX WARN: Code duplicated, block: B:49:0x02bf  */
    /* JADX WARN: Code duplicated, block: B:50:0x02d4  */
    /* JADX WARN: Code duplicated, block: B:53:0x0354  */
    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:49:0x02bf -> B:55:0x0373). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:53:0x0354 -> B:54:0x0365). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    static /* synthetic */ java.lang.Object getUrl$suspendImpl(com.lagradost.cloudstream3.extractors.StreamSB r26, java.lang.String r27, java.lang.String r28, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r29, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r30, kotlin.coroutines.Continuation<? super kotlin.Unit> r31) {
        /*
            Method dump skipped, instruction units count: 912
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.extractors.StreamSB.getUrl$suspendImpl(com.lagradost.cloudstream3.extractors.StreamSB, java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static final String getUrl$lambda$0(MatchResult it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return new Regex("(embed-|/e/)").replace(it.getValue(), "");
    }

    private final String encodeId(String id) {
        char[] charArray = (createHashTable() + "||" + id + "||" + createHashTable() + "||streamsb").toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "toCharArray(...)");
        return ArraysKt.joinToString$default(charArray, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.lagradost.cloudstream3.extractors.StreamSB$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return StreamSB.encodeId$lambda$0(((Character) obj).charValue());
            }
        }, 30, (Object) null);
    }

    static final CharSequence encodeId$lambda$0(char c) {
        String string = Integer.toString(c, CharsKt.checkRadix(16));
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    private final String createHashTable() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            sb.append(this.alphabet.charAt(Random.INSTANCE.nextInt(this.alphabet.length())));
        }
        return sb.toString();
    }

    /* JADX INFO: compiled from: StreamSB.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\f\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/StreamSB$Subs;", "", UtilsKt.SCHEME_FILE, "", "label", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getFile", "()Ljava/lang/String;", "getLabel", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Subs {

        @JsonProperty(UtilsKt.SCHEME_FILE)
        private final String file;

        @JsonProperty("label")
        private final String label;

        /* JADX WARN: Multi-variable type inference failed */
        public Subs() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ Subs copy$default(Subs subs, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = subs.file;
            }
            if ((i & 2) != 0) {
                str2 = subs.label;
            }
            return subs.copy(str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getFile() {
            return this.file;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getLabel() {
            return this.label;
        }

        public final Subs copy(@JsonProperty(UtilsKt.SCHEME_FILE) String file, @JsonProperty("label") String label) {
            return new Subs(file, label);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Subs)) {
                return false;
            }
            Subs subs = (Subs) other;
            return Intrinsics.areEqual(this.file, subs.file) && Intrinsics.areEqual(this.label, subs.label);
        }

        public int hashCode() {
            String str = this.file;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.label;
            return iHashCode + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "Subs(file=" + this.file + ", label=" + this.label + ')';
        }

        public Subs(@JsonProperty(UtilsKt.SCHEME_FILE) String str, @JsonProperty("label") String str2) {
            this.file = str;
            this.label = str2;
        }

        public /* synthetic */ Subs(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2);
        }

        public final String getFile() {
            return this.file;
        }

        public final String getLabel() {
            return this.label;
        }
    }

    /* JADX INFO: compiled from: StreamSB.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bk\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003\u0012\u001c\b\u0003\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007j\n\u0012\u0004\u0012\u00020\b\u0018\u0001`\t\u0012\b\b\u0001\u0010\n\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0001\u0010\f\u001a\u00020\u0003\u0012\b\b\u0001\u0010\r\u001a\u00020\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u001d\u001a\u0016\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007j\n\u0012\u0004\u0012\u00020\b\u0018\u0001`\tHÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003Jm\u0010\"\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00032\u001c\b\u0003\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007j\n\u0012\u0004\u0012\u00020\b\u0018\u0001`\t2\b\b\u0003\u0010\n\u001a\u00020\u00032\b\b\u0003\u0010\u000b\u001a\u00020\u00032\b\b\u0003\u0010\f\u001a\u00020\u00032\b\b\u0003\u0010\r\u001a\u00020\u0003HÆ\u0001J\u0014\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010&\u001a\u00020'HÖ\u0081\u0004J\n\u0010(\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R*\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007j\n\u0012\u0004\u0012\u00020\b\u0018\u0001`\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\n\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0016\u0010\u000b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0016\u0010\f\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u0016\u0010\r\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011¨\u0006)"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/StreamSB$StreamData;", "", UtilsKt.SCHEME_FILE, "", "cdnImg", "hash", CloudStreamPackage.SUBTITLE_EXTRA, "Ljava/util/ArrayList;", "Lcom/lagradost/cloudstream3/extractors/StreamSB$Subs;", "Lkotlin/collections/ArrayList;", "length", "id", "title", "backup", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/ArrayList;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getFile", "()Ljava/lang/String;", "getCdnImg", "getHash", "getSubs", "()Ljava/util/ArrayList;", "getLength", "getId", "getTitle", "getBackup", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class StreamData {

        @JsonProperty("backup")
        private final String backup;

        @JsonProperty("cdn_img")
        private final String cdnImg;

        @JsonProperty(UtilsKt.SCHEME_FILE)
        private final String file;

        @JsonProperty("hash")
        private final String hash;

        @JsonProperty("id")
        private final String id;

        @JsonProperty("length")
        private final String length;

        @JsonProperty(CloudStreamPackage.SUBTITLE_EXTRA)
        private final ArrayList<Subs> subs;

        @JsonProperty("title")
        private final String title;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ StreamData copy$default(StreamData streamData, String str, String str2, String str3, ArrayList arrayList, String str4, String str5, String str6, String str7, int i, Object obj) {
            if ((i & 1) != 0) {
                str = streamData.file;
            }
            if ((i & 2) != 0) {
                str2 = streamData.cdnImg;
            }
            if ((i & 4) != 0) {
                str3 = streamData.hash;
            }
            if ((i & 8) != 0) {
                arrayList = streamData.subs;
            }
            if ((i & 16) != 0) {
                str4 = streamData.length;
            }
            if ((i & 32) != 0) {
                str5 = streamData.id;
            }
            if ((i & 64) != 0) {
                str6 = streamData.title;
            }
            if ((i & 128) != 0) {
                str7 = streamData.backup;
            }
            String str8 = str6;
            String str9 = str7;
            String str10 = str4;
            String str11 = str5;
            return streamData.copy(str, str2, str3, arrayList, str10, str11, str8, str9);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getFile() {
            return this.file;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getCdnImg() {
            return this.cdnImg;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getHash() {
            return this.hash;
        }

        public final ArrayList<Subs> component4() {
            return this.subs;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getLength() {
            return this.length;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getBackup() {
            return this.backup;
        }

        public final StreamData copy(@JsonProperty(UtilsKt.SCHEME_FILE) String file, @JsonProperty("cdn_img") String cdnImg, @JsonProperty("hash") String hash, @JsonProperty(CloudStreamPackage.SUBTITLE_EXTRA) ArrayList<Subs> subs, @JsonProperty("length") String length, @JsonProperty("id") String id, @JsonProperty("title") String title, @JsonProperty("backup") String backup) {
            Intrinsics.checkNotNullParameter(file, "file");
            Intrinsics.checkNotNullParameter(cdnImg, "cdnImg");
            Intrinsics.checkNotNullParameter(hash, "hash");
            Intrinsics.checkNotNullParameter(length, "length");
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(backup, "backup");
            return new StreamData(file, cdnImg, hash, subs, length, id, title, backup);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof StreamData)) {
                return false;
            }
            StreamData streamData = (StreamData) other;
            return Intrinsics.areEqual(this.file, streamData.file) && Intrinsics.areEqual(this.cdnImg, streamData.cdnImg) && Intrinsics.areEqual(this.hash, streamData.hash) && Intrinsics.areEqual(this.subs, streamData.subs) && Intrinsics.areEqual(this.length, streamData.length) && Intrinsics.areEqual(this.id, streamData.id) && Intrinsics.areEqual(this.title, streamData.title) && Intrinsics.areEqual(this.backup, streamData.backup);
        }

        public int hashCode() {
            int iHashCode = ((((this.file.hashCode() * 31) + this.cdnImg.hashCode()) * 31) + this.hash.hashCode()) * 31;
            ArrayList<Subs> arrayList = this.subs;
            return ((((((((iHashCode + (arrayList == null ? 0 : arrayList.hashCode())) * 31) + this.length.hashCode()) * 31) + this.id.hashCode()) * 31) + this.title.hashCode()) * 31) + this.backup.hashCode();
        }

        public String toString() {
            return "StreamData(file=" + this.file + ", cdnImg=" + this.cdnImg + ", hash=" + this.hash + ", subs=" + this.subs + ", length=" + this.length + ", id=" + this.id + ", title=" + this.title + ", backup=" + this.backup + ')';
        }

        public StreamData(@JsonProperty(UtilsKt.SCHEME_FILE) String file, @JsonProperty("cdn_img") String cdnImg, @JsonProperty("hash") String hash, @JsonProperty(CloudStreamPackage.SUBTITLE_EXTRA) ArrayList<Subs> arrayList, @JsonProperty("length") String length, @JsonProperty("id") String id, @JsonProperty("title") String title, @JsonProperty("backup") String backup) {
            Intrinsics.checkNotNullParameter(file, "file");
            Intrinsics.checkNotNullParameter(cdnImg, "cdnImg");
            Intrinsics.checkNotNullParameter(hash, "hash");
            Intrinsics.checkNotNullParameter(length, "length");
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(backup, "backup");
            this.file = file;
            this.cdnImg = cdnImg;
            this.hash = hash;
            this.subs = arrayList;
            this.length = length;
            this.id = id;
            this.title = title;
            this.backup = backup;
        }

        public final String getFile() {
            return this.file;
        }

        public final String getCdnImg() {
            return this.cdnImg;
        }

        public final String getHash() {
            return this.hash;
        }

        public /* synthetic */ StreamData(String str, String str2, String str3, ArrayList arrayList, String str4, String str5, String str6, String str7, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, (i & 8) != 0 ? new ArrayList() : arrayList, str4, str5, str6, str7);
        }

        public final ArrayList<Subs> getSubs() {
            return this.subs;
        }

        public final String getLength() {
            return this.length;
        }

        public final String getId() {
            return this.id;
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getBackup() {
            return this.backup;
        }
    }

    /* JADX INFO: compiled from: StreamSB.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/StreamSB$Main;", "", "streamData", "Lcom/lagradost/cloudstream3/extractors/StreamSB$StreamData;", "statusCode", "", "<init>", "(Lcom/lagradost/cloudstream3/extractors/StreamSB$StreamData;I)V", "getStreamData", "()Lcom/lagradost/cloudstream3/extractors/StreamSB$StreamData;", "getStatusCode", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Main {

        @JsonProperty("status_code")
        private final int statusCode;

        @JsonProperty("stream_data")
        private final StreamData streamData;

        public static /* synthetic */ Main copy$default(Main main, StreamData streamData, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                streamData = main.streamData;
            }
            if ((i2 & 2) != 0) {
                i = main.statusCode;
            }
            return main.copy(streamData, i);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final StreamData getStreamData() {
            return this.streamData;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getStatusCode() {
            return this.statusCode;
        }

        public final Main copy(@JsonProperty("stream_data") StreamData streamData, @JsonProperty("status_code") int statusCode) {
            Intrinsics.checkNotNullParameter(streamData, "streamData");
            return new Main(streamData, statusCode);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Main)) {
                return false;
            }
            Main main = (Main) other;
            return Intrinsics.areEqual(this.streamData, main.streamData) && this.statusCode == main.statusCode;
        }

        public int hashCode() {
            return (this.streamData.hashCode() * 31) + this.statusCode;
        }

        public String toString() {
            return "Main(streamData=" + this.streamData + ", statusCode=" + this.statusCode + ')';
        }

        public Main(@JsonProperty("stream_data") StreamData streamData, @JsonProperty("status_code") int i) {
            Intrinsics.checkNotNullParameter(streamData, "streamData");
            this.streamData = streamData;
            this.statusCode = i;
        }

        public final StreamData getStreamData() {
            return this.streamData;
        }

        public final int getStatusCode() {
            return this.statusCode;
        }
    }
}
