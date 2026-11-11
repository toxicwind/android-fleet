package com.lagradost.cloudstream3.actions.temp.fcast;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.DoubleSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: Packets.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 92\u00020\u0001:\u000289BY\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\u0016\b\u0003\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fBc\b\u0010\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u000b\u0010\u0011J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010&\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001cJ\u0010\u0010'\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001cJ\u0017\u0010(\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\nHÆ\u0003J`\u0010)\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00072\u0016\b\u0003\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\nHÆ\u0001¢\u0006\u0002\u0010*J\u0014\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010.\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010/\u001a\u00020\u0003HÖ\u0081\u0004J%\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u00002\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u000206H\u0001¢\u0006\u0002\b7R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0017\u0010\u0015R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0013\u001a\u0004\b\u0019\u0010\u0015R \u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u001d\u0012\u0004\b\u001a\u0010\u0013\u001a\u0004\b\u001b\u0010\u001cR \u0010\b\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u001d\u0012\u0004\b\u001e\u0010\u0013\u001a\u0004\b\u001f\u0010\u001cR*\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b \u0010\u0013\u001a\u0004\b!\u0010\"¨\u0006:"}, d2 = {"Lcom/lagradost/cloudstream3/actions/temp/fcast/PlayMessage;", "", TtmlNode.RUBY_CONTAINER, "", ImagesContract.URL, "content", "time", "", "speed", "headers", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/util/Map;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/util/Map;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getContainer$annotations", "()V", "getContainer", "()Ljava/lang/String;", "getUrl$annotations", "getUrl", "getContent$annotations", "getContent", "getTime$annotations", "getTime", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getSpeed$annotations", "getSpeed", "getHeaders$annotations", "getHeaders", "()Ljava/util/Map;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/util/Map;)Lcom/lagradost/cloudstream3/actions/temp/fcast/PlayMessage;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Serializable
public final /* data */ class PlayMessage {

    @JsonProperty(TtmlNode.RUBY_CONTAINER)
    private final String container;

    @JsonProperty("content")
    private final String content;

    @JsonProperty("headers")
    private final Map<String, String> headers;

    @JsonProperty("speed")
    private final Double speed;

    @JsonProperty("time")
    private final Double time;

    @JsonProperty(ImagesContract.URL)
    private final String url;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, null, null, null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.actions.temp.fcast.PlayMessage$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return PlayMessage._childSerializers$_anonymous_();
        }
    })};

    /* JADX INFO: Access modifiers changed from: private */
    public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
        return new LinkedHashMapSerializer(StringSerializer.INSTANCE, StringSerializer.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PlayMessage copy$default(PlayMessage playMessage, String str, String str2, String str3, Double d, Double d2, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            str = playMessage.container;
        }
        if ((i & 2) != 0) {
            str2 = playMessage.url;
        }
        if ((i & 4) != 0) {
            str3 = playMessage.content;
        }
        if ((i & 8) != 0) {
            d = playMessage.time;
        }
        if ((i & 16) != 0) {
            d2 = playMessage.speed;
        }
        if ((i & 32) != 0) {
            map = playMessage.headers;
        }
        Double d3 = d2;
        Map map2 = map;
        return playMessage.copy(str, str2, str3, d, d3, map2);
    }

    @SerialName(TtmlNode.RUBY_CONTAINER)
    public static /* synthetic */ void getContainer$annotations() {
    }

    @SerialName("content")
    public static /* synthetic */ void getContent$annotations() {
    }

    @SerialName("headers")
    public static /* synthetic */ void getHeaders$annotations() {
    }

    @SerialName("speed")
    public static /* synthetic */ void getSpeed$annotations() {
    }

    @SerialName("time")
    public static /* synthetic */ void getTime$annotations() {
    }

    @SerialName(ImagesContract.URL)
    public static /* synthetic */ void getUrl$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getContainer() {
        return this.container;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Double getTime() {
        return this.time;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Double getSpeed() {
        return this.speed;
    }

    public final Map<String, String> component6() {
        return this.headers;
    }

    public final PlayMessage copy(@JsonProperty(TtmlNode.RUBY_CONTAINER) String container, @JsonProperty(ImagesContract.URL) String url, @JsonProperty("content") String content, @JsonProperty("time") Double time, @JsonProperty("speed") Double speed, @JsonProperty("headers") Map<String, String> headers) {
        Intrinsics.checkNotNullParameter(container, "container");
        return new PlayMessage(container, url, content, time, speed, headers);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlayMessage)) {
            return false;
        }
        PlayMessage playMessage = (PlayMessage) other;
        return Intrinsics.areEqual(this.container, playMessage.container) && Intrinsics.areEqual(this.url, playMessage.url) && Intrinsics.areEqual(this.content, playMessage.content) && Intrinsics.areEqual((Object) this.time, (Object) playMessage.time) && Intrinsics.areEqual((Object) this.speed, (Object) playMessage.speed) && Intrinsics.areEqual(this.headers, playMessage.headers);
    }

    public int hashCode() {
        int iHashCode = this.container.hashCode() * 31;
        String str = this.url;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.content;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Double d = this.time;
        int iHashCode4 = (iHashCode3 + (d == null ? 0 : d.hashCode())) * 31;
        Double d2 = this.speed;
        int iHashCode5 = (iHashCode4 + (d2 == null ? 0 : d2.hashCode())) * 31;
        Map<String, String> map = this.headers;
        return iHashCode5 + (map != null ? map.hashCode() : 0);
    }

    public String toString() {
        return "PlayMessage(container=" + this.container + ", url=" + this.url + ", content=" + this.content + ", time=" + this.time + ", speed=" + this.speed + ", headers=" + this.headers + ')';
    }

    /* JADX INFO: compiled from: Packets.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/actions/temp/fcast/PlayMessage$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/actions/temp/fcast/PlayMessage;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<PlayMessage> serializer() {
            return PlayMessage$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ PlayMessage(int i, String str, String str2, String str3, Double d, Double d2, Map map, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 != (i & 1)) {
            PluginExceptionsKt.throwMissingFieldException(i, 1, PlayMessage$$serializer.INSTANCE.getDescriptor());
        }
        this.container = str;
        if ((i & 2) == 0) {
            this.url = null;
        } else {
            this.url = str2;
        }
        if ((i & 4) == 0) {
            this.content = null;
        } else {
            this.content = str3;
        }
        if ((i & 8) == 0) {
            this.time = null;
        } else {
            this.time = d;
        }
        if ((i & 16) == 0) {
            this.speed = null;
        } else {
            this.speed = d2;
        }
        if ((i & 32) == 0) {
            this.headers = null;
        } else {
            this.headers = map;
        }
    }

    public PlayMessage(@JsonProperty(TtmlNode.RUBY_CONTAINER) String container, @JsonProperty(ImagesContract.URL) String str, @JsonProperty("content") String str2, @JsonProperty("time") Double d, @JsonProperty("speed") Double d2, @JsonProperty("headers") Map<String, String> map) {
        Intrinsics.checkNotNullParameter(container, "container");
        this.container = container;
        this.url = str;
        this.content = str2;
        this.time = d;
        this.speed = d2;
        this.headers = map;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$app(PlayMessage self, CompositeEncoder output, SerialDescriptor serialDesc) {
        Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
        output.encodeStringElement(serialDesc, 0, self.container);
        if (output.shouldEncodeElementDefault(serialDesc, 1) || self.url != null) {
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.url);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || self.content != null) {
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.content);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 3) || self.time != null) {
            output.encodeNullableSerializableElement(serialDesc, 3, DoubleSerializer.INSTANCE, self.time);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 4) || self.speed != null) {
            output.encodeNullableSerializableElement(serialDesc, 4, DoubleSerializer.INSTANCE, self.speed);
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 5) && self.headers == null) {
            return;
        }
        output.encodeNullableSerializableElement(serialDesc, 5, lazyArr[5].getValue(), self.headers);
    }

    public /* synthetic */ PlayMessage(String str, String str2, String str3, Double d, Double d2, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : d, (i & 16) != 0 ? null : d2, (i & 32) != 0 ? null : map);
    }

    public final String getContainer() {
        return this.container;
    }

    public final String getUrl() {
        return this.url;
    }

    public final String getContent() {
        return this.content;
    }

    public final Double getTime() {
        return this.time;
    }

    public final Double getSpeed() {
        return this.speed;
    }

    public final Map<String, String> getHeaders() {
        return this.headers;
    }
}
