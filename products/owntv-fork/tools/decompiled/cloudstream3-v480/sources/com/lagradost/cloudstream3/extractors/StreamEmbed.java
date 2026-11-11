package com.lagradost.cloudstream3.extractors;

import androidx.core.app.NotificationCompat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
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

/* JADX INFO: compiled from: StreamEmbed.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001:\u0001\u001bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00052\b\u0010\u0014\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00120\u00162\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00120\u0016H\u0096@¢\u0006\u0002\u0010\u001aR\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u0014\u0010\r\u001a\u00020\u000eX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/StreamEmbed;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "mainUrl", "getMainUrl", "setMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "getUrl", "", ImagesContract.URL, "referer", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Details", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class StreamEmbed extends ExtractorApi {
    private String name = "StreamEmbed";
    private String mainUrl = "https://watch.gxplayer.xyz";
    private final boolean requiresReferer = true;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.StreamEmbed$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: StreamEmbed.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.StreamEmbed", f = "StreamEmbed.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1}, l = {22, 26}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "jsonString", "video"}, nl = {23, 30}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return StreamEmbed.getUrl$suspendImpl(StreamEmbed.this, (String) null, (String) null, (Function1<? super SubtitleFile, Unit>) null, (Function1<? super ExtractorLink, Unit>) null, (Continuation<? super Unit>) this);
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

    /* JADX WARN: Code duplicated, block: B:50:0x01e8 A[LOOP:0: B:48:0x01e2->B:50:0x01e8, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    static /* synthetic */ Object getUrl$suspendImpl(StreamEmbed streamEmbed, String str, String str2, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super Unit> continuation) throws JsonProcessingException {
        AnonymousClass1 anonymousClass1;
        int i;
        String str3;
        String str4;
        Function1<? super ExtractorLink, Unit> function3;
        StreamEmbed streamEmbed2;
        Object obj;
        Function1<? super SubtitleFile, Unit> function4;
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Function1<? super ExtractorLink, Unit> function5;
        Iterator it;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = streamEmbed.new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = streamEmbed.new AnonymousClass1(continuation);
        }
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object objGenerateM3u8$default = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass2.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(objGenerateM3u8$default);
                Requests app2 = MainActivityKt.getApp();
                String mainUrl = streamEmbed.getMainUrl();
                anonymousClass2.L$0 = streamEmbed;
                anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str);
                anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str2);
                anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function1);
                anonymousClass2.L$4 = function2;
                anonymousClass2.label = 1;
                i = 2;
                Object obj2 = Requests.get$default(app2, str, null, mainUrl, null, null, false, 0, null, 0L, null, false, null, anonymousClass2, 4090, null);
                if (obj2 != coroutine_suspended) {
                    anonymousClass2 = anonymousClass2;
                    str3 = str;
                    str4 = str2;
                    function3 = function2;
                    streamEmbed2 = streamEmbed;
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
                streamEmbed2 = (StreamEmbed) anonymousClass2.L$0;
                ResultKt.throwOnFailure(objGenerateM3u8$default);
                function3 = function6;
                obj = objGenerateM3u8$default;
                i = 2;
            } else {
                if (i2 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                function5 = (Function1) anonymousClass2.L$4;
                ResultKt.throwOnFailure(objGenerateM3u8$default);
            }
            it = ((Iterable) objGenerateM3u8$default).iterator();
            while (it.hasNext()) {
                function5.invoke(it.next());
            }
            return Unit.INSTANCE;
            Result.Companion companion = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.extractors.StreamEmbed.Details"));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
        }
        String strSubstringBefore$default = StringsKt.substringBefore$default(StringsKt.substringAfter$default(((NiceResponse) obj).getText(), "var video = ", (String) null, i, (Object) null), ";", (String) null, i, (Object) null);
        AppUtils appUtils = AppUtils.INSTANCE;
        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
            try {
                Result.Companion companion3 = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(Details.class), null, i, null));
            } catch (Throwable th2) {
                Result.Companion companion4 = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
            }
        }
        KSerializer kSerializer = (KSerializer) (Result.m8942isFailureimpl(objM8936constructorimpl) ? null : objM8936constructorimpl);
        if (kSerializer != null) {
            try {
                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, strSubstringBefore$default);
            } catch (SerializationException e) {
                ArchComponentExtKt.logError(e);
                objDecodeFromString = MainAPIKt.getMapper().readValue(strSubstringBefore$default, new TypeReference<Details>() { // from class: com.lagradost.cloudstream3.extractors.StreamEmbed$getUrl$suspendImpl$$inlined$parseJson$1
                });
            } catch (Throwable unused) {
                objDecodeFromString = MainAPIKt.getMapper().readValue(strSubstringBefore$default, new TypeReference<Details>() { // from class: com.lagradost.cloudstream3.extractors.StreamEmbed$getUrl$suspendImpl$$inlined$parseJson$1
                });
            }
        } else {
            objDecodeFromString = MainAPIKt.getMapper().readValue(strSubstringBefore$default, new TypeReference<Details>() { // from class: com.lagradost.cloudstream3.extractors.StreamEmbed$getUrl$suspendImpl$$inlined$parseJson$1
            });
        }
        Details details = (Details) objDecodeFromString;
        Function1<? super SubtitleFile, Unit> function7 = function4;
        M3u8Helper.Companion companion5 = M3u8Helper.INSTANCE;
        String str5 = str4;
        String name = streamEmbed2.getName();
        String str6 = streamEmbed2.getMainUrl() + "/m3u8/" + details.getUid() + '/' + details.getMd5() + "/master.txt?s=1&id=" + details.getId() + "&cache=" + details.getStatus();
        String str7 = streamEmbed2.getMainUrl() + '/';
        anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(streamEmbed2);
        anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str3);
        anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str5);
        anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function7);
        anonymousClass2.L$4 = function3;
        anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(strSubstringBefore$default);
        anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(details);
        anonymousClass2.label = i;
        objGenerateM3u8$default = M3u8Helper.Companion.generateM3u8$default(companion5, name, str6, str7, null, null, null, anonymousClass2, 56, null);
        if (objGenerateM3u8$default != coroutine_suspended) {
            function5 = function3;
            it = ((Iterable) objGenerateM3u8$default).iterator();
            while (it.hasNext()) {
                function5.invoke(it.next());
            }
            return Unit.INSTANCE;
        }
        anonymousClass2 = anonymousClass2;
        return coroutine_suspended;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: StreamEmbed.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001BW\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0001\u0010\b\u001a\u00020\u0003\u0012\b\b\u0001\u0010\t\u001a\u00020\u0003\u0012\b\b\u0001\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003JY\u0010\u001e\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u00032\b\b\u0003\u0010\u0007\u001a\u00020\u00032\b\b\u0003\u0010\b\u001a\u00020\u00032\b\b\u0003\u0010\t\u001a\u00020\u00032\b\b\u0003\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\"\u001a\u00020#HÖ\u0081\u0004J\n\u0010$\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0016\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0016\u0010\n\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000e¨\u0006%"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/StreamEmbed$Details;", "", "id", "", "uid", "slug", "title", "quality", "type", NotificationCompat.CATEGORY_STATUS, "md5", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getUid", "getSlug", "getTitle", "getQuality", "getType", "getStatus", "getMd5", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    static final /* data */ class Details {

        @JsonProperty("id")
        private final String id;

        @JsonProperty("md5")
        private final String md5;

        @JsonProperty("quality")
        private final String quality;

        @JsonProperty("slug")
        private final String slug;

        @JsonProperty(NotificationCompat.CATEGORY_STATUS)
        private final String status;

        @JsonProperty("title")
        private final String title;

        @JsonProperty("type")
        private final String type;

        @JsonProperty("uid")
        private final String uid;

        public static /* synthetic */ Details copy$default(Details details, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, Object obj) {
            if ((i & 1) != 0) {
                str = details.id;
            }
            if ((i & 2) != 0) {
                str2 = details.uid;
            }
            if ((i & 4) != 0) {
                str3 = details.slug;
            }
            if ((i & 8) != 0) {
                str4 = details.title;
            }
            if ((i & 16) != 0) {
                str5 = details.quality;
            }
            if ((i & 32) != 0) {
                str6 = details.type;
            }
            if ((i & 64) != 0) {
                str7 = details.status;
            }
            if ((i & 128) != 0) {
                str8 = details.md5;
            }
            String str9 = str7;
            String str10 = str8;
            String str11 = str5;
            String str12 = str6;
            return details.copy(str, str2, str3, str4, str11, str12, str9, str10);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getUid() {
            return this.uid;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getSlug() {
            return this.slug;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getQuality() {
            return this.quality;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getStatus() {
            return this.status;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getMd5() {
            return this.md5;
        }

        public final Details copy(@JsonProperty("id") String id, @JsonProperty("uid") String uid, @JsonProperty("slug") String slug, @JsonProperty("title") String title, @JsonProperty("quality") String quality, @JsonProperty("type") String type, @JsonProperty(NotificationCompat.CATEGORY_STATUS) String status, @JsonProperty("md5") String md5) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(uid, "uid");
            Intrinsics.checkNotNullParameter(slug, "slug");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(quality, "quality");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(status, "status");
            Intrinsics.checkNotNullParameter(md5, "md5");
            return new Details(id, uid, slug, title, quality, type, status, md5);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Details)) {
                return false;
            }
            Details details = (Details) other;
            return Intrinsics.areEqual(this.id, details.id) && Intrinsics.areEqual(this.uid, details.uid) && Intrinsics.areEqual(this.slug, details.slug) && Intrinsics.areEqual(this.title, details.title) && Intrinsics.areEqual(this.quality, details.quality) && Intrinsics.areEqual(this.type, details.type) && Intrinsics.areEqual(this.status, details.status) && Intrinsics.areEqual(this.md5, details.md5);
        }

        public int hashCode() {
            return (((((((((((((this.id.hashCode() * 31) + this.uid.hashCode()) * 31) + this.slug.hashCode()) * 31) + this.title.hashCode()) * 31) + this.quality.hashCode()) * 31) + this.type.hashCode()) * 31) + this.status.hashCode()) * 31) + this.md5.hashCode();
        }

        public String toString() {
            return "Details(id=" + this.id + ", uid=" + this.uid + ", slug=" + this.slug + ", title=" + this.title + ", quality=" + this.quality + ", type=" + this.type + ", status=" + this.status + ", md5=" + this.md5 + ')';
        }

        public Details(@JsonProperty("id") String id, @JsonProperty("uid") String uid, @JsonProperty("slug") String slug, @JsonProperty("title") String title, @JsonProperty("quality") String quality, @JsonProperty("type") String type, @JsonProperty(NotificationCompat.CATEGORY_STATUS) String status, @JsonProperty("md5") String md5) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(uid, "uid");
            Intrinsics.checkNotNullParameter(slug, "slug");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(quality, "quality");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(status, "status");
            Intrinsics.checkNotNullParameter(md5, "md5");
            this.id = id;
            this.uid = uid;
            this.slug = slug;
            this.title = title;
            this.quality = quality;
            this.type = type;
            this.status = status;
            this.md5 = md5;
        }

        public final String getId() {
            return this.id;
        }

        public final String getUid() {
            return this.uid;
        }

        public final String getSlug() {
            return this.slug;
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getQuality() {
            return this.quality;
        }

        public final String getType() {
            return this.type;
        }

        public final String getStatus() {
            return this.status;
        }

        public final String getMd5() {
            return this.md5;
        }
    }
}
