package com.lagradost.cloudstream3.actions.temp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.BuildConfig;
import com.lagradost.cloudstream3.SkipSerializationTest;
import com.lagradost.cloudstream3.actions.OpenInAppAction;
import com.lagradost.cloudstream3.actions.temp.fcast.FcastManager;
import com.lagradost.cloudstream3.ui.player.ExtractorUri;
import com.lagradost.cloudstream3.ui.player.SubtitleData;
import com.lagradost.cloudstream3.ui.player.SubtitleOrigin;
import com.lagradost.cloudstream3.ui.result.LinkLoadingResult;
import com.lagradost.cloudstream3.ui.result.ResultEpisode;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.DrmExtractorLink;
import com.lagradost.cloudstream3.utils.ExtractorApiKt;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.ExtractorLinkPlayList;
import com.lagradost.cloudstream3.utils.ExtractorLinkType;
import com.lagradost.cloudstream3.utils.SubtitleHelper;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.serializers.UriSerializer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: CloudStreamPackage.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00182\u00020\u0001:\u0003\u0018\u0019\u001aB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J8\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0096@¢\u0006\u0002\u0010\u0014J\u001a\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u001b"}, d2 = {"Lcom/lagradost/cloudstream3/actions/temp/CloudStreamPackage;", "Lcom/lagradost/cloudstream3/actions/OpenInAppAction;", "<init>", "()V", "oneSource", "", "getOneSource", "()Z", "putExtra", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "video", "Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "result", "Lcom/lagradost/cloudstream3/ui/result/LinkLoadingResult;", "index", "", "(Landroid/content/Context;Landroid/content/Intent;Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;Lcom/lagradost/cloudstream3/ui/result/LinkLoadingResult;Ljava/lang/Integer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onResult", "activity", "Landroid/app/Activity;", "Companion", "MinimalVideoLink", "MinimalSubtitleLink", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CloudStreamPackage extends OpenInAppAction {
    public static final String DURATION_EXTRA = "dur";
    public static final String ID_EXTRA = "id";
    public static final String LINKS_EXTRA = "links";
    public static final String POSITION_EXTRA = "pos";
    public static final String SUBTITLE_EXTRA = "subs";
    public static final String TITLE_EXTRA = "title";
    private final boolean oneSource;

    @Override // com.lagradost.cloudstream3.actions.OpenInAppAction
    public void onResult(Activity activity, Intent intent) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    public CloudStreamPackage() {
        super(TextUtilKt.txt(FcastManager.APP_PREFIX), BuildConfig.APPLICATION_ID, "com.lagradost.cloudstream3.ui.player.DownloadedPlayerActivity", null, 8, null);
    }

    @Override // com.lagradost.cloudstream3.actions.VideoClickAction
    public boolean getOneSource() {
        return this.oneSource;
    }

    /* JADX INFO: compiled from: CloudStreamPackage.kt */
    @SkipSerializationTest
    @Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 @2\u00020\u0001:\u0002@ABW\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u0014\b\u0003\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\t\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\f\u0010\rBc\b\u0010\u0012\u0006\u0010\u000e\u001a\u00020\u000b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\f\u0010\u0011J\u001e\u0010&\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010(\u0012\u0006\u0012\u0004\u0018\u00010)0'H\u0086@¢\u0006\u0002\u0010*J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010-\u001a\u00020\u0005HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0015\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\tHÆ\u0003J\u0010\u00100\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010$J^\u00101\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00052\u0014\b\u0003\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\t2\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u000bHÆ\u0001¢\u0006\u0002\u00102J\u0014\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00106\u001a\u00020\u000bHÖ\u0081\u0004J\n\u00107\u001a\u00020\u0005HÖ\u0081\u0004J%\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\u00002\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020>H\u0001¢\u0006\u0002\b?R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u0013\u001a\u0004\b\u001a\u0010\u0018R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u0013\u001a\u0004\b\u001c\u0010\u0018R0\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001d\u0010\u0013\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R \u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010%\u0012\u0004\b\"\u0010\u0013\u001a\u0004\b#\u0010$¨\u0006B"}, d2 = {"Lcom/lagradost/cloudstream3/actions/temp/CloudStreamPackage$MinimalVideoLink;", "", "uri", "Landroid/net/Uri;", ImagesContract.URL, "", "mimeType", "name", "headers", "", "quality", "", "<init>", "(Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/Integer;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILandroid/net/Uri;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/Integer;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getUri$annotations", "()V", "getUri", "()Landroid/net/Uri;", "getUrl$annotations", "getUrl", "()Ljava/lang/String;", "getMimeType$annotations", "getMimeType", "getName$annotations", "getName", "getHeaders$annotations", "getHeaders", "()Ljava/util/Map;", "setHeaders", "(Ljava/util/Map;)V", "getQuality$annotations", "getQuality", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "toExtractorLink", "Lkotlin/Pair;", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "Lcom/lagradost/cloudstream3/ui/player/ExtractorUri;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/actions/temp/CloudStreamPackage$MinimalVideoLink;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "Companion", "$serializer", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class MinimalVideoLink {

        @JsonProperty("headers")
        private Map<String, String> headers;

        @JsonProperty("mimeType")
        private final String mimeType;

        @JsonProperty("name")
        private final String name;

        @JsonProperty("quality")
        private final Integer quality;

        @JsonProperty("uri")
        private final Uri uri;

        @JsonProperty(ImagesContract.URL)
        private final String url;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, null, null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.actions.temp.CloudStreamPackage$MinimalVideoLink$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return CloudStreamPackage.MinimalVideoLink._childSerializers$_anonymous_();
            }
        }), null};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new LinkedHashMapSerializer(StringSerializer.INSTANCE, StringSerializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ MinimalVideoLink copy$default(MinimalVideoLink minimalVideoLink, Uri uri, String str, String str2, String str3, Map map, Integer num, int i, Object obj) {
            if ((i & 1) != 0) {
                uri = minimalVideoLink.uri;
            }
            if ((i & 2) != 0) {
                str = minimalVideoLink.url;
            }
            if ((i & 4) != 0) {
                str2 = minimalVideoLink.mimeType;
            }
            if ((i & 8) != 0) {
                str3 = minimalVideoLink.name;
            }
            if ((i & 16) != 0) {
                map = minimalVideoLink.headers;
            }
            if ((i & 32) != 0) {
                num = minimalVideoLink.quality;
            }
            Map map2 = map;
            Integer num2 = num;
            return minimalVideoLink.copy(uri, str, str2, str3, map2, num2);
        }

        @SerialName("headers")
        public static /* synthetic */ void getHeaders$annotations() {
        }

        @SerialName("mimeType")
        public static /* synthetic */ void getMimeType$annotations() {
        }

        @SerialName("name")
        public static /* synthetic */ void getName$annotations() {
        }

        @SerialName("quality")
        public static /* synthetic */ void getQuality$annotations() {
        }

        @SerialName("uri")
        @Serializable(with = UriSerializer.class)
        public static /* synthetic */ void getUri$annotations() {
        }

        @SerialName(ImagesContract.URL)
        public static /* synthetic */ void getUrl$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Uri getUri() {
            return this.uri;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getMimeType() {
            return this.mimeType;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getName() {
            return this.name;
        }

        public final Map<String, String> component5() {
            return this.headers;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Integer getQuality() {
            return this.quality;
        }

        public final MinimalVideoLink copy(@JsonProperty("uri") Uri uri, @JsonProperty(ImagesContract.URL) String url, @JsonProperty("mimeType") String mimeType, @JsonProperty("name") String name, @JsonProperty("headers") Map<String, String> headers, @JsonProperty("quality") Integer quality) {
            Intrinsics.checkNotNullParameter(mimeType, "mimeType");
            Intrinsics.checkNotNullParameter(headers, "headers");
            return new MinimalVideoLink(uri, url, mimeType, name, headers, quality);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MinimalVideoLink)) {
                return false;
            }
            MinimalVideoLink minimalVideoLink = (MinimalVideoLink) other;
            return Intrinsics.areEqual(this.uri, minimalVideoLink.uri) && Intrinsics.areEqual(this.url, minimalVideoLink.url) && Intrinsics.areEqual(this.mimeType, minimalVideoLink.mimeType) && Intrinsics.areEqual(this.name, minimalVideoLink.name) && Intrinsics.areEqual(this.headers, minimalVideoLink.headers) && Intrinsics.areEqual(this.quality, minimalVideoLink.quality);
        }

        public int hashCode() {
            Uri uri = this.uri;
            int iHashCode = (uri == null ? 0 : uri.hashCode()) * 31;
            String str = this.url;
            int iHashCode2 = (((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.mimeType.hashCode()) * 31;
            String str2 = this.name;
            int iHashCode3 = (((iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31) + this.headers.hashCode()) * 31;
            Integer num = this.quality;
            return iHashCode3 + (num != null ? num.hashCode() : 0);
        }

        public String toString() {
            return "MinimalVideoLink(uri=" + this.uri + ", url=" + this.url + ", mimeType=" + this.mimeType + ", name=" + this.name + ", headers=" + this.headers + ", quality=" + this.quality + ')';
        }

        public /* synthetic */ MinimalVideoLink(int i, Uri uri, String str, String str2, String str3, Map map, Integer num, SerializationConstructorMarker serializationConstructorMarker) {
            if (43 != (i & 43)) {
                PluginExceptionsKt.throwMissingFieldException(i, 43, CloudStreamPackage$MinimalVideoLink$$serializer.INSTANCE.getDescriptor());
            }
            this.uri = uri;
            this.url = str;
            if ((i & 4) == 0) {
                this.mimeType = "video/mp4";
            } else {
                this.mimeType = str2;
            }
            this.name = str3;
            if ((i & 16) == 0) {
                this.headers = MapsKt.emptyMap();
            } else {
                this.headers = map;
            }
            this.quality = num;
        }

        public MinimalVideoLink(@JsonProperty("uri") Uri uri, @JsonProperty(ImagesContract.URL) String str, @JsonProperty("mimeType") String mimeType, @JsonProperty("name") String str2, @JsonProperty("headers") Map<String, String> headers, @JsonProperty("quality") Integer num) {
            Intrinsics.checkNotNullParameter(mimeType, "mimeType");
            Intrinsics.checkNotNullParameter(headers, "headers");
            this.uri = uri;
            this.url = str;
            this.mimeType = mimeType;
            this.name = str2;
            this.headers = headers;
            this.quality = num;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(MinimalVideoLink self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            output.encodeNullableSerializableElement(serialDesc, 0, UriSerializer.INSTANCE, self.uri);
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.url);
            if (output.shouldEncodeElementDefault(serialDesc, 2) || !Intrinsics.areEqual(self.mimeType, "video/mp4")) {
                output.encodeStringElement(serialDesc, 2, self.mimeType);
            }
            output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.name);
            if (output.shouldEncodeElementDefault(serialDesc, 4) || !Intrinsics.areEqual(self.headers, MapsKt.emptyMap())) {
                output.encodeSerializableElement(serialDesc, 4, lazyArr[4].getValue(), self.headers);
            }
            output.encodeNullableSerializableElement(serialDesc, 5, IntSerializer.INSTANCE, self.quality);
        }

        public final Uri getUri() {
            return this.uri;
        }

        public final String getUrl() {
            return this.url;
        }

        public /* synthetic */ MinimalVideoLink(Uri uri, String str, String str2, String str3, Map map, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(uri, str, (i & 4) != 0 ? "video/mp4" : str2, str3, (i & 16) != 0 ? MapsKt.emptyMap() : map, num);
        }

        public final String getMimeType() {
            return this.mimeType;
        }

        public final String getName() {
            return this.name;
        }

        public final Map<String, String> getHeaders() {
            return this.headers;
        }

        public final void setHeaders(Map<String, String> map) {
            Intrinsics.checkNotNullParameter(map, "<set-?>");
            this.headers = map;
        }

        public final Integer getQuality() {
            return this.quality;
        }

        /* JADX INFO: compiled from: CloudStreamPackage.kt */
        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t¨\u0006\n"}, d2 = {"Lcom/lagradost/cloudstream3/actions/temp/CloudStreamPackage$MinimalVideoLink$Companion;", "", "<init>", "()V", "fromExtractor", "Lcom/lagradost/cloudstream3/actions/temp/CloudStreamPackage$MinimalVideoLink;", "link", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "serializer", "Lkotlinx/serialization/KSerializer;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<MinimalVideoLink> serializer() {
                return CloudStreamPackage$MinimalVideoLink$$serializer.INSTANCE;
            }

            public final MinimalVideoLink fromExtractor(ExtractorLink link) {
                Intrinsics.checkNotNullParameter(link, "link");
                return new MinimalVideoLink(null, link.getUrl(), link.getType().getMimeType(), link.getName(), StringsKt.isBlank(link.getReferer()) ? MapsKt.emptyMap() : MapsKt.plus(MapsKt.mapOf(TuplesKt.to("referer", link.getReferer())), link.getHeaders()), Integer.valueOf(link.getQuality()));
            }
        }

        /* JADX WARN: Code duplicated, block: B:36:0x009c  */
        /* JADX WARN: Code duplicated, block: B:38:0x00a2  */
        /* JADX WARN: Code duplicated, block: B:39:0x00a4  */
        /* JADX WARN: Code duplicated, block: B:7:0x0018  */
        public final Object toExtractorLink(Continuation<? super Pair<? extends ExtractorLink, ExtractorUri>> continuation) {
            CloudStreamPackage$MinimalVideoLink$toExtractorLink$1 cloudStreamPackage$MinimalVideoLink$toExtractorLink$1;
            ExtractorLink extractorLink;
            ExtractorLinkType next;
            Uri uri;
            String str;
            String str2;
            if (continuation instanceof CloudStreamPackage$MinimalVideoLink$toExtractorLink$1) {
                cloudStreamPackage$MinimalVideoLink$toExtractorLink$1 = (CloudStreamPackage$MinimalVideoLink$toExtractorLink$1) continuation;
                if ((cloudStreamPackage$MinimalVideoLink$toExtractorLink$1.label & Integer.MIN_VALUE) != 0) {
                    cloudStreamPackage$MinimalVideoLink$toExtractorLink$1.label -= Integer.MIN_VALUE;
                } else {
                    cloudStreamPackage$MinimalVideoLink$toExtractorLink$1 = new CloudStreamPackage$MinimalVideoLink$toExtractorLink$1(this, continuation);
                }
            } else {
                cloudStreamPackage$MinimalVideoLink$toExtractorLink$1 = new CloudStreamPackage$MinimalVideoLink$toExtractorLink$1(this, continuation);
            }
            CloudStreamPackage$MinimalVideoLink$toExtractorLink$1 cloudStreamPackage$MinimalVideoLink$toExtractorLink$2 = cloudStreamPackage$MinimalVideoLink$toExtractorLink$1;
            Object objNewExtractorLink = cloudStreamPackage$MinimalVideoLink$toExtractorLink$2.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = cloudStreamPackage$MinimalVideoLink$toExtractorLink$2.label;
            ExtractorUri extractorUri = null;
            if (i == 0) {
                ResultKt.throwOnFailure(objNewExtractorLink);
                String str3 = this.url;
                if (str3 != null) {
                    String str4 = this.name;
                    if (str4 == null) {
                        str4 = "Unknown";
                    }
                    Iterator<ExtractorLinkType> it = ExtractorLinkType.getEntries().iterator();
                    do {
                        if (!it.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it.next();
                    } while (!Intrinsics.areEqual(next.getMimeType(), this.mimeType));
                    ExtractorLinkType extractorLinkType = next;
                    if (extractorLinkType == null) {
                        extractorLinkType = ExtractorLinkType.VIDEO;
                    }
                    CloudStreamPackage$MinimalVideoLink$toExtractorLink$2$2 cloudStreamPackage$MinimalVideoLink$toExtractorLink$2$2 = new CloudStreamPackage$MinimalVideoLink$toExtractorLink$2$2(this, null);
                    cloudStreamPackage$MinimalVideoLink$toExtractorLink$2.L$0 = SpillingKt.nullOutSpilledVariable(str3);
                    cloudStreamPackage$MinimalVideoLink$toExtractorLink$2.I$0 = 0;
                    cloudStreamPackage$MinimalVideoLink$toExtractorLink$2.label = 1;
                    objNewExtractorLink = ExtractorApiKt.newExtractorLink("NONE", str4, str3, extractorLinkType, cloudStreamPackage$MinimalVideoLink$toExtractorLink$2$2, cloudStreamPackage$MinimalVideoLink$toExtractorLink$2);
                    if (objNewExtractorLink == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    extractorLink = null;
                }
                uri = this.uri;
                if (uri != null) {
                    str = this.name;
                    if (str == null) {
                        str2 = "Unknown";
                    } else {
                        str2 = str;
                    }
                    extractorUri = new ExtractorUri(uri, str2, null, null, null, null, null, null, null, null, null, 2044, null);
                }
                return TuplesKt.to(extractorLink, extractorUri);
            }
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = cloudStreamPackage$MinimalVideoLink$toExtractorLink$2.I$0;
            ResultKt.throwOnFailure(objNewExtractorLink);
            extractorLink = (ExtractorLink) objNewExtractorLink;
            uri = this.uri;
            if (uri != null) {
                str = this.name;
                if (str == null) {
                    str2 = "Unknown";
                } else {
                    str2 = str;
                }
                extractorUri = new ExtractorUri(uri, str2, null, null, null, null, null, null, null, null, null, 2044, null);
            }
            return TuplesKt.to(extractorLink, extractorUri);
        }
    }

    /* JADX INFO: compiled from: CloudStreamPackage.kt */
    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 02\u00020\u0001:\u000201B=\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0014\b\u0003\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0004\b\b\u0010\tBO\b\u0010\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\b\u0010\u000eJ\u0006\u0010\u001c\u001a\u00020\u001dJ\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J?\u0010\"\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\u0014\b\u0003\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0001J\u0014\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010&\u001a\u00020\u000bHÖ\u0081\u0004J\n\u0010'\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u00002\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0001¢\u0006\u0002\b/R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0014\u0010\u0012R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0016\u0010\u0012R0\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0017\u0010\u0010\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u00062"}, d2 = {"Lcom/lagradost/cloudstream3/actions/temp/CloudStreamPackage$MinimalSubtitleLink;", "", ImagesContract.URL, "", "mimeType", "name", "headers", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getUrl$annotations", "()V", "getUrl", "()Ljava/lang/String;", "getMimeType$annotations", "getMimeType", "getName$annotations", "getName", "getHeaders$annotations", "getHeaders", "()Ljava/util/Map;", "setHeaders", "(Ljava/util/Map;)V", "toSubtitleData", "Lcom/lagradost/cloudstream3/ui/player/SubtitleData;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "Companion", "$serializer", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class MinimalSubtitleLink {

        @JsonProperty("headers")
        private Map<String, String> headers;

        @JsonProperty("mimeType")
        private final String mimeType;

        @JsonProperty("name")
        private final String name;

        @JsonProperty(ImagesContract.URL)
        private final String url;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.actions.temp.CloudStreamPackage$MinimalSubtitleLink$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return CloudStreamPackage.MinimalSubtitleLink._childSerializers$_anonymous_();
            }
        })};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new LinkedHashMapSerializer(StringSerializer.INSTANCE, StringSerializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ MinimalSubtitleLink copy$default(MinimalSubtitleLink minimalSubtitleLink, String str, String str2, String str3, Map map, int i, Object obj) {
            if ((i & 1) != 0) {
                str = minimalSubtitleLink.url;
            }
            if ((i & 2) != 0) {
                str2 = minimalSubtitleLink.mimeType;
            }
            if ((i & 4) != 0) {
                str3 = minimalSubtitleLink.name;
            }
            if ((i & 8) != 0) {
                map = minimalSubtitleLink.headers;
            }
            return minimalSubtitleLink.copy(str, str2, str3, map);
        }

        @SerialName("headers")
        public static /* synthetic */ void getHeaders$annotations() {
        }

        @SerialName("mimeType")
        public static /* synthetic */ void getMimeType$annotations() {
        }

        @SerialName("name")
        public static /* synthetic */ void getName$annotations() {
        }

        @SerialName(ImagesContract.URL)
        public static /* synthetic */ void getUrl$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMimeType() {
            return this.mimeType;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getName() {
            return this.name;
        }

        public final Map<String, String> component4() {
            return this.headers;
        }

        public final MinimalSubtitleLink copy(@JsonProperty(ImagesContract.URL) String url, @JsonProperty("mimeType") String mimeType, @JsonProperty("name") String name, @JsonProperty("headers") Map<String, String> headers) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(mimeType, "mimeType");
            Intrinsics.checkNotNullParameter(headers, "headers");
            return new MinimalSubtitleLink(url, mimeType, name, headers);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MinimalSubtitleLink)) {
                return false;
            }
            MinimalSubtitleLink minimalSubtitleLink = (MinimalSubtitleLink) other;
            return Intrinsics.areEqual(this.url, minimalSubtitleLink.url) && Intrinsics.areEqual(this.mimeType, minimalSubtitleLink.mimeType) && Intrinsics.areEqual(this.name, minimalSubtitleLink.name) && Intrinsics.areEqual(this.headers, minimalSubtitleLink.headers);
        }

        public int hashCode() {
            int iHashCode = ((this.url.hashCode() * 31) + this.mimeType.hashCode()) * 31;
            String str = this.name;
            return ((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.headers.hashCode();
        }

        public String toString() {
            return "MinimalSubtitleLink(url=" + this.url + ", mimeType=" + this.mimeType + ", name=" + this.name + ", headers=" + this.headers + ')';
        }

        public /* synthetic */ MinimalSubtitleLink(int i, String str, String str2, String str3, Map map, SerializationConstructorMarker serializationConstructorMarker) {
            if (5 != (i & 5)) {
                PluginExceptionsKt.throwMissingFieldException(i, 5, CloudStreamPackage$MinimalSubtitleLink$$serializer.INSTANCE.getDescriptor());
            }
            this.url = str;
            if ((i & 2) == 0) {
                this.mimeType = "text/vtt";
            } else {
                this.mimeType = str2;
            }
            this.name = str3;
            if ((i & 8) == 0) {
                this.headers = MapsKt.emptyMap();
            } else {
                this.headers = map;
            }
        }

        public MinimalSubtitleLink(@JsonProperty(ImagesContract.URL) String url, @JsonProperty("mimeType") String mimeType, @JsonProperty("name") String str, @JsonProperty("headers") Map<String, String> headers) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(mimeType, "mimeType");
            Intrinsics.checkNotNullParameter(headers, "headers");
            this.url = url;
            this.mimeType = mimeType;
            this.name = str;
            this.headers = headers;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(MinimalSubtitleLink self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            output.encodeStringElement(serialDesc, 0, self.url);
            if (output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.mimeType, "text/vtt")) {
                output.encodeStringElement(serialDesc, 1, self.mimeType);
            }
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.name);
            if (!output.shouldEncodeElementDefault(serialDesc, 3) && Intrinsics.areEqual(self.headers, MapsKt.emptyMap())) {
                return;
            }
            output.encodeSerializableElement(serialDesc, 3, lazyArr[3].getValue(), self.headers);
        }

        public final String getUrl() {
            return this.url;
        }

        public /* synthetic */ MinimalSubtitleLink(String str, String str2, String str3, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? "text/vtt" : str2, str3, (i & 8) != 0 ? MapsKt.emptyMap() : map);
        }

        public final String getMimeType() {
            return this.mimeType;
        }

        public final String getName() {
            return this.name;
        }

        public final Map<String, String> getHeaders() {
            return this.headers;
        }

        public final void setHeaders(Map<String, String> map) {
            Intrinsics.checkNotNullParameter(map, "<set-?>");
            this.headers = map;
        }

        /* JADX INFO: compiled from: CloudStreamPackage.kt */
        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t¨\u0006\n"}, d2 = {"Lcom/lagradost/cloudstream3/actions/temp/CloudStreamPackage$MinimalSubtitleLink$Companion;", "", "<init>", "()V", "fromSubtitle", "Lcom/lagradost/cloudstream3/actions/temp/CloudStreamPackage$MinimalSubtitleLink;", "sub", "Lcom/lagradost/cloudstream3/ui/player/SubtitleData;", "serializer", "Lkotlinx/serialization/KSerializer;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<MinimalSubtitleLink> serializer() {
                return CloudStreamPackage$MinimalSubtitleLink$$serializer.INSTANCE;
            }

            public final MinimalSubtitleLink fromSubtitle(SubtitleData sub) {
                Intrinsics.checkNotNullParameter(sub, "sub");
                return new MinimalSubtitleLink(sub.getUrl(), sub.getMimeType(), sub.getOriginalName(), sub.getHeaders());
            }
        }

        public final SubtitleData toSubtitleData() {
            String str = this.url;
            String str2 = this.mimeType;
            String str3 = this.name;
            if (str3 == null) {
                str3 = "Unknown";
            }
            String str4 = str3;
            Map<String, String> map = this.headers;
            SubtitleOrigin subtitleOrigin = SubtitleOrigin.URL;
            String strFromCodeToLangTagIETF = SubtitleHelper.INSTANCE.fromCodeToLangTagIETF(this.name);
            if (strFromCodeToLangTagIETF == null && (strFromCodeToLangTagIETF = SubtitleHelper.INSTANCE.fromLanguageToTagIETF(this.name, true)) == null) {
                strFromCodeToLangTagIETF = this.name;
            }
            return new SubtitleData(str4, "", str, subtitleOrigin, str2, map, strFromCodeToLangTagIETF);
        }
    }

    @Override // com.lagradost.cloudstream3.actions.OpenInAppAction
    public Object putExtra(Context context, Intent intent, ResultEpisode resultEpisode, LinkLoadingResult linkLoadingResult, Integer num, Continuation<? super Unit> continuation) {
        DataStoreHelper.PosDur viewPos = DataStoreHelper.INSTANCE.getViewPos(Boxing.boxInt(resultEpisode.getId()));
        Long lBoxLong = viewPos != null ? Boxing.boxLong(viewPos.getPosition()) : null;
        if (lBoxLong != null) {
            intent.putExtra(POSITION_EXTRA, lBoxLong.longValue());
        }
        intent.putExtra("id", resultEpisode.getId());
        intent.putExtra("title", resultEpisode.getName());
        List<SubtitleData> subs = linkLoadingResult.getSubs();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(subs, 10));
        Iterator<T> it = subs.iterator();
        while (it.hasNext()) {
            arrayList.add(AppUtils.INSTANCE.toJson(MinimalSubtitleLink.INSTANCE.fromSubtitle((SubtitleData) it.next())));
        }
        intent.putExtra(SUBTITLE_EXTRA, (String[]) arrayList.toArray(new String[0]));
        List<ExtractorLink> links = linkLoadingResult.getLinks();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : links) {
            ExtractorLink extractorLink = (ExtractorLink) obj;
            if (!(extractorLink instanceof ExtractorLinkPlayList) && !(extractorLink instanceof DrmExtractorLink)) {
                arrayList2.add(obj);
            }
        }
        ArrayList arrayList3 = arrayList2;
        ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
        Iterator it2 = arrayList3.iterator();
        while (it2.hasNext()) {
            arrayList4.add(AppUtils.INSTANCE.toJson(MinimalVideoLink.INSTANCE.fromExtractor((ExtractorLink) it2.next())));
        }
        intent.putExtra(LINKS_EXTRA, (String[]) arrayList4.toArray(new String[0]));
        return Unit.INSTANCE;
    }
}
