package app.cash.zipline;

import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import app.cash.zipline.internal.ByteStringAsHexSerializer;
import app.cash.zipline.internal.SignaturePayloadKt;
import app.cash.zipline.internal.TopologicalSortKt;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.json.Json;
import okio.ByteString;

/* JADX INFO: compiled from: ZiplineManifest.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0087\b\u0018\u0000 A2\u00020\u0001:\u0004?@ABB_\b\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\u0014\b\u0002\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\f\u0010\rBo\b\u0010\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\f\u0010\u0012Js\u0010$\u001a\u00020\u00002\u0014\b\u0002\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00062\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00052\b\b\u0002\u0010\b\u001a\u00020\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010%JJ\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00052\b\b\u0002\u0010\b\u001a\u00020\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006H\u0007J\u0087\u0001\u0010$\u001a\u00020\u00002\u0014\b\u0002\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00062\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00052\b\b\u0002\u0010\b\u001a\u00020\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00062\u0014\b\u0002\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010&J\u0006\u0010+\u001a\u00020\u0006J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\u0015\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005HÆ\u0003J\t\u0010.\u001a\u00020\u0006HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0015\u00101\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003Ja\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00052\b\b\u0002\u0010\b\u001a\u00020\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00062\u0014\b\u0002\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0014\u00102\u001a\u0002032\b\u00104\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00105\u001a\u00020\u000fHÖ\u0081\u0004J\n\u00106\u001a\u00020\u0006HÖ\u0081\u0004J%\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\u00002\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020=H\u0001¢\u0006\u0002\b>R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u001d\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016R\u001d\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00058F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0016R\u0013\u0010\u001e\u001a\u0004\u0018\u00010\u001f8F¢\u0006\u0006\u001a\u0004\b \u0010!R\u0013\u0010\"\u001a\u0004\u0018\u00010\u00068F¢\u0006\u0006\u001a\u0004\b#\u0010\u0018R\u0011\u0010'\u001a\u00020(8F¢\u0006\u0006\u001a\u0004\b)\u0010*¨\u0006C"}, d2 = {"Lapp/cash/zipline/ZiplineManifest;", "", "unsigned", "Lapp/cash/zipline/ZiplineManifest$Unsigned;", "modules", "", "", "Lapp/cash/zipline/ZiplineManifest$Module;", "mainModuleId", "mainFunction", "version", TtmlNode.TAG_METADATA, "<init>", "(Lapp/cash/zipline/ZiplineManifest$Unsigned;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILapp/cash/zipline/ZiplineManifest$Unsigned;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getUnsigned", "()Lapp/cash/zipline/ZiplineManifest$Unsigned;", "getModules", "()Ljava/util/Map;", "getMainModuleId", "()Ljava/lang/String;", "getMainFunction", "getVersion", "getMetadata", "signatures", "getSignatures", "freshAtEpochMs", "", "getFreshAtEpochMs", "()Ljava/lang/Long;", "baseUrl", "getBaseUrl", "copy", "(Ljava/util/Map;Ljava/lang/Long;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lapp/cash/zipline/ZiplineManifest;", "(Ljava/util/Map;Ljava/lang/Long;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)Lapp/cash/zipline/ZiplineManifest;", "signaturePayload", "Lokio/ByteString;", "getSignaturePayload", "()Lokio/ByteString;", "encodeJson", "component1", "component2", "component3", "component4", "component5", "component6", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$zipline_release", "Unsigned", "Module", "Companion", "$serializer", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Serializable
public final /* data */ class ZiplineManifest {
    private final String mainFunction;
    private final String mainModuleId;
    private final Map<String, String> metadata;
    private final Map<String, Module> modules;
    private final Unsigned unsigned;
    private final String version;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: app.cash.zipline.ZiplineManifest$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return ZiplineManifest._childSerializers$_anonymous_();
        }
    }), null, null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: app.cash.zipline.ZiplineManifest$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return ZiplineManifest._childSerializers$_anonymous_$0();
        }
    })};

    public /* synthetic */ ZiplineManifest(Unsigned unsigned, Map map, String str, String str2, String str3, Map map2, DefaultConstructorMarker defaultConstructorMarker) {
        this(unsigned, map, str, str2, str3, map2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
        return new LinkedHashMapSerializer(StringSerializer.INSTANCE, ZiplineManifest$Module$$serializer.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$0() {
        return new LinkedHashMapSerializer(StringSerializer.INSTANCE, StringSerializer.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ZiplineManifest copy$default(ZiplineManifest ziplineManifest, Unsigned unsigned, Map map, String str, String str2, String str3, Map map2, int i, Object obj) {
        if ((i & 1) != 0) {
            unsigned = ziplineManifest.unsigned;
        }
        if ((i & 2) != 0) {
            map = ziplineManifest.modules;
        }
        if ((i & 4) != 0) {
            str = ziplineManifest.mainModuleId;
        }
        if ((i & 8) != 0) {
            str2 = ziplineManifest.mainFunction;
        }
        if ((i & 16) != 0) {
            str3 = ziplineManifest.version;
        }
        if ((i & 32) != 0) {
            map2 = ziplineManifest.metadata;
        }
        String str4 = str3;
        Map map3 = map2;
        return ziplineManifest.copy(unsigned, map, str, str2, str4, map3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Unsigned getUnsigned() {
        return this.unsigned;
    }

    public final Map<String, Module> component2() {
        return this.modules;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getMainModuleId() {
        return this.mainModuleId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getMainFunction() {
        return this.mainFunction;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getVersion() {
        return this.version;
    }

    public final Map<String, String> component6() {
        return this.metadata;
    }

    public final ZiplineManifest copy(Unsigned unsigned, Map<String, Module> modules, String mainModuleId, String mainFunction, String version, Map<String, String> metadata) {
        Intrinsics.checkNotNullParameter(unsigned, "unsigned");
        Intrinsics.checkNotNullParameter(modules, "modules");
        Intrinsics.checkNotNullParameter(mainModuleId, "mainModuleId");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        return new ZiplineManifest(unsigned, modules, mainModuleId, mainFunction, version, metadata);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ZiplineManifest)) {
            return false;
        }
        ZiplineManifest ziplineManifest = (ZiplineManifest) other;
        return Intrinsics.areEqual(this.unsigned, ziplineManifest.unsigned) && Intrinsics.areEqual(this.modules, ziplineManifest.modules) && Intrinsics.areEqual(this.mainModuleId, ziplineManifest.mainModuleId) && Intrinsics.areEqual(this.mainFunction, ziplineManifest.mainFunction) && Intrinsics.areEqual(this.version, ziplineManifest.version) && Intrinsics.areEqual(this.metadata, ziplineManifest.metadata);
    }

    public int hashCode() {
        int iHashCode = ((((this.unsigned.hashCode() * 31) + this.modules.hashCode()) * 31) + this.mainModuleId.hashCode()) * 31;
        String str = this.mainFunction;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.version;
        return ((iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + this.metadata.hashCode();
    }

    public String toString() {
        return "ZiplineManifest(unsigned=" + this.unsigned + ", modules=" + this.modules + ", mainModuleId=" + this.mainModuleId + ", mainFunction=" + this.mainFunction + ", version=" + this.version + ", metadata=" + this.metadata + ")";
    }

    public /* synthetic */ ZiplineManifest(int i, Unsigned unsigned, Map map, String str, String str2, String str3, Map map2, SerializationConstructorMarker serializationConstructorMarker) {
        if (4 != (i & 4)) {
            PluginExceptionsKt.throwMissingFieldException(i, 4, ZiplineManifest$$serializer.INSTANCE.getDescriptor());
        }
        if ((i & 1) == 0) {
            this.unsigned = new Unsigned((Map) null, (Long) null, (String) null, 7, (DefaultConstructorMarker) null);
        } else {
            this.unsigned = unsigned;
        }
        if ((i & 2) == 0) {
            this.modules = MapsKt.emptyMap();
        } else {
            this.modules = map;
        }
        this.mainModuleId = str;
        if ((i & 8) == 0) {
            this.mainFunction = null;
        } else {
            this.mainFunction = str2;
        }
        if ((i & 16) == 0) {
            this.version = null;
        } else {
            this.version = str3;
        }
        if ((i & 32) == 0) {
            this.metadata = MapsKt.emptyMap();
        } else {
            this.metadata = map2;
        }
        if (!TopologicalSortKt.isTopologicallySorted(CollectionsKt.toList(this.modules.keySet()), new Function1() { // from class: app.cash.zipline.ZiplineManifest$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ZiplineManifest._init_$lambda$2(this.f$0, (String) obj);
            }
        })) {
            throw new IllegalArgumentException("Modules are not topologically sorted and can not be loaded".toString());
        }
    }

    private ZiplineManifest(Unsigned unsigned, Map<String, Module> map, String str, String str2, String str3, Map<String, String> map2) {
        this.unsigned = unsigned;
        this.modules = map;
        this.mainModuleId = str;
        this.mainFunction = str2;
        this.version = str3;
        this.metadata = map2;
        if (!TopologicalSortKt.isTopologicallySorted(CollectionsKt.toList(map.keySet()), new Function1() { // from class: app.cash.zipline.ZiplineManifest$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ZiplineManifest._init_$lambda$0(this.f$0, (String) obj);
            }
        })) {
            throw new IllegalArgumentException("Modules are not topologically sorted and can not be loaded".toString());
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x001c  */
    @JvmStatic
    public static final /* synthetic */ void write$Self$zipline_release(ZiplineManifest self, CompositeEncoder output, SerialDescriptor serialDesc) {
        Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
        if (output.shouldEncodeElementDefault(serialDesc, 0)) {
            output.encodeSerializableElement(serialDesc, 0, ZiplineManifest$Unsigned$$serializer.INSTANCE, self.unsigned);
        } else {
            if (!Intrinsics.areEqual(self.unsigned, new Unsigned((Map) null, (Long) null, (String) null, 7, (DefaultConstructorMarker) null))) {
                output.encodeSerializableElement(serialDesc, 0, ZiplineManifest$Unsigned$$serializer.INSTANCE, self.unsigned);
            }
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.modules, MapsKt.emptyMap())) {
            output.encodeSerializableElement(serialDesc, 1, lazyArr[1].getValue(), self.modules);
        }
        output.encodeStringElement(serialDesc, 2, self.mainModuleId);
        if (output.shouldEncodeElementDefault(serialDesc, 3) || self.mainFunction != null) {
            output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.mainFunction);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 4) || self.version != null) {
            output.encodeNullableSerializableElement(serialDesc, 4, StringSerializer.INSTANCE, self.version);
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 5) && Intrinsics.areEqual(self.metadata, MapsKt.emptyMap())) {
            return;
        }
        output.encodeSerializableElement(serialDesc, 5, lazyArr[5].getValue(), self.metadata);
    }

    /* synthetic */ ZiplineManifest(Unsigned unsigned, Map map, String str, String str2, String str3, Map map2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            unsigned = new Unsigned((Map) null, (Long) null, (String) null, 7, (DefaultConstructorMarker) null);
        }
        this(unsigned, (i & 2) != 0 ? MapsKt.emptyMap() : map, str, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? MapsKt.emptyMap() : map2);
    }

    public final Unsigned getUnsigned() {
        return this.unsigned;
    }

    public final Map<String, Module> getModules() {
        return this.modules;
    }

    public final String getMainModuleId() {
        return this.mainModuleId;
    }

    public final String getMainFunction() {
        return this.mainFunction;
    }

    public final String getVersion() {
        return this.version;
    }

    public final Map<String, String> getMetadata() {
        return this.metadata;
    }

    static final Iterable _init_$lambda$0(ZiplineManifest ziplineManifest, String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        Module module = ziplineManifest.modules.get(id);
        Intrinsics.checkNotNull(module);
        return module.getDependsOnIds();
    }

    static final Iterable _init_$lambda$2(ZiplineManifest ziplineManifest, String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        Module module = ziplineManifest.modules.get(id);
        Intrinsics.checkNotNull(module);
        return module.getDependsOnIds();
    }

    /* JADX INFO: compiled from: ZiplineManifest.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 )2\u00020\u0001:\u0002()B5\u0012\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\b\u0010\tBE\b\u0010\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\b\u0010\u000eJ\u0015\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0012J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0004HÆ\u0003J<\u0010\u0019\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0002\u0010\u001aJ\u0014\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001e\u001a\u00020\u000bHÖ\u0081\u0004J\n\u0010\u001f\u001a\u00020\u0004HÖ\u0081\u0004J%\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00002\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0001¢\u0006\u0002\b'R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006*"}, d2 = {"Lapp/cash/zipline/ZiplineManifest$Unsigned;", "", "signatures", "", "", "freshAtEpochMs", "", "baseUrl", "<init>", "(Ljava/util/Map;Ljava/lang/Long;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/Map;Ljava/lang/Long;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getSignatures", "()Ljava/util/Map;", "getFreshAtEpochMs", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getBaseUrl", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "(Ljava/util/Map;Ljava/lang/Long;Ljava/lang/String;)Lapp/cash/zipline/ZiplineManifest$Unsigned;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$zipline_release", "$serializer", "Companion", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Unsigned {
        private final String baseUrl;
        private final Long freshAtEpochMs;
        private final Map<String, String> signatures;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: app.cash.zipline.ZiplineManifest$Unsigned$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ZiplineManifest.Unsigned._childSerializers$_anonymous_();
            }
        }), null, null};

        public Unsigned() {
            this((Map) null, (Long) null, (String) null, 7, (DefaultConstructorMarker) null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new LinkedHashMapSerializer(StringSerializer.INSTANCE, StringSerializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Unsigned copy$default(Unsigned unsigned, Map map, Long l, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                map = unsigned.signatures;
            }
            if ((i & 2) != 0) {
                l = unsigned.freshAtEpochMs;
            }
            if ((i & 4) != 0) {
                str = unsigned.baseUrl;
            }
            return unsigned.copy(map, l, str);
        }

        public final Map<String, String> component1() {
            return this.signatures;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Long getFreshAtEpochMs() {
            return this.freshAtEpochMs;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getBaseUrl() {
            return this.baseUrl;
        }

        public final Unsigned copy(Map<String, String> signatures, Long freshAtEpochMs, String baseUrl) {
            Intrinsics.checkNotNullParameter(signatures, "signatures");
            return new Unsigned(signatures, freshAtEpochMs, baseUrl);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Unsigned)) {
                return false;
            }
            Unsigned unsigned = (Unsigned) other;
            return Intrinsics.areEqual(this.signatures, unsigned.signatures) && Intrinsics.areEqual(this.freshAtEpochMs, unsigned.freshAtEpochMs) && Intrinsics.areEqual(this.baseUrl, unsigned.baseUrl);
        }

        public int hashCode() {
            int iHashCode = this.signatures.hashCode() * 31;
            Long l = this.freshAtEpochMs;
            int iHashCode2 = (iHashCode + (l == null ? 0 : l.hashCode())) * 31;
            String str = this.baseUrl;
            return iHashCode2 + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            return "Unsigned(signatures=" + this.signatures + ", freshAtEpochMs=" + this.freshAtEpochMs + ", baseUrl=" + this.baseUrl + ")";
        }

        /* JADX INFO: compiled from: ZiplineManifest.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lapp/cash/zipline/ZiplineManifest$Unsigned$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lapp/cash/zipline/ZiplineManifest$Unsigned;", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Unsigned> serializer() {
                return ZiplineManifest$Unsigned$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Unsigned(int i, Map map, Long l, String str, SerializationConstructorMarker serializationConstructorMarker) {
            this.signatures = (i & 1) == 0 ? MapsKt.emptyMap() : map;
            if ((i & 2) == 0) {
                this.freshAtEpochMs = null;
            } else {
                this.freshAtEpochMs = l;
            }
            if ((i & 4) == 0) {
                this.baseUrl = null;
            } else {
                this.baseUrl = str;
            }
        }

        public Unsigned(Map<String, String> signatures, Long l, String str) {
            Intrinsics.checkNotNullParameter(signatures, "signatures");
            this.signatures = signatures;
            this.freshAtEpochMs = l;
            this.baseUrl = str;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$zipline_release(Unsigned self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            if (output.shouldEncodeElementDefault(serialDesc, 0) || !Intrinsics.areEqual(self.signatures, MapsKt.emptyMap())) {
                output.encodeSerializableElement(serialDesc, 0, lazyArr[0].getValue(), self.signatures);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 1) || self.freshAtEpochMs != null) {
                output.encodeNullableSerializableElement(serialDesc, 1, LongSerializer.INSTANCE, self.freshAtEpochMs);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 2) && self.baseUrl == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.baseUrl);
        }

        public /* synthetic */ Unsigned(Map map, Long l, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? MapsKt.emptyMap() : map, (i & 2) != 0 ? null : l, (i & 4) != 0 ? null : str);
        }

        public final Map<String, String> getSignatures() {
            return this.signatures;
        }

        public final Long getFreshAtEpochMs() {
            return this.freshAtEpochMs;
        }

        public final String getBaseUrl() {
            return this.baseUrl;
        }
    }

    /* JADX INFO: compiled from: ZiplineManifest.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 )2\u00020\u0001:\u0002()B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0004\b\b\u0010\tB?\b\u0010\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\b\u0010\u000eJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J-\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0001J\u0014\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001e\u001a\u00020\u000bHÖ\u0081\u0004J\n\u0010\u001f\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00002\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0001¢\u0006\u0002\b'R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006*"}, d2 = {"Lapp/cash/zipline/ZiplineManifest$Module;", "", ImagesContract.URL, "", "sha256", "Lokio/ByteString;", "dependsOnIds", "", "<init>", "(Ljava/lang/String;Lokio/ByteString;Ljava/util/List;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Lokio/ByteString;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getUrl", "()Ljava/lang/String;", "getSha256$annotations", "()V", "getSha256", "()Lokio/ByteString;", "getDependsOnIds", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$zipline_release", "$serializer", "Companion", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Module {
        private final List<String> dependsOnIds;
        private final ByteString sha256;
        private final String url;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: app.cash.zipline.ZiplineManifest$Module$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ZiplineManifest.Module._childSerializers$_anonymous_();
            }
        })};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(StringSerializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Module copy$default(Module module, String str, ByteString byteString, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = module.url;
            }
            if ((i & 2) != 0) {
                byteString = module.sha256;
            }
            if ((i & 4) != 0) {
                list = module.dependsOnIds;
            }
            return module.copy(str, byteString, list);
        }

        @Serializable(with = ByteStringAsHexSerializer.class)
        public static /* synthetic */ void getSha256$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final ByteString getSha256() {
            return this.sha256;
        }

        public final List<String> component3() {
            return this.dependsOnIds;
        }

        public final Module copy(String url, ByteString sha256, List<String> dependsOnIds) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(sha256, "sha256");
            Intrinsics.checkNotNullParameter(dependsOnIds, "dependsOnIds");
            return new Module(url, sha256, dependsOnIds);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Module)) {
                return false;
            }
            Module module = (Module) other;
            return Intrinsics.areEqual(this.url, module.url) && Intrinsics.areEqual(this.sha256, module.sha256) && Intrinsics.areEqual(this.dependsOnIds, module.dependsOnIds);
        }

        public int hashCode() {
            return (((this.url.hashCode() * 31) + this.sha256.hashCode()) * 31) + this.dependsOnIds.hashCode();
        }

        public String toString() {
            return "Module(url=" + this.url + ", sha256=" + this.sha256 + ", dependsOnIds=" + this.dependsOnIds + ")";
        }

        /* JADX INFO: compiled from: ZiplineManifest.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lapp/cash/zipline/ZiplineManifest$Module$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lapp/cash/zipline/ZiplineManifest$Module;", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Module> serializer() {
                return ZiplineManifest$Module$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Module(int i, String str, ByteString byteString, List list, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, ZiplineManifest$Module$$serializer.INSTANCE.getDescriptor());
            }
            this.url = str;
            this.sha256 = byteString;
            if ((i & 4) == 0) {
                this.dependsOnIds = CollectionsKt.emptyList();
            } else {
                this.dependsOnIds = list;
            }
        }

        public Module(String url, ByteString sha256, List<String> dependsOnIds) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(sha256, "sha256");
            Intrinsics.checkNotNullParameter(dependsOnIds, "dependsOnIds");
            this.url = url;
            this.sha256 = sha256;
            this.dependsOnIds = dependsOnIds;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$zipline_release(Module self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            output.encodeStringElement(serialDesc, 0, self.url);
            output.encodeSerializableElement(serialDesc, 1, ByteStringAsHexSerializer.INSTANCE, self.sha256);
            if (!output.shouldEncodeElementDefault(serialDesc, 2) && Intrinsics.areEqual(self.dependsOnIds, CollectionsKt.emptyList())) {
                return;
            }
            output.encodeSerializableElement(serialDesc, 2, lazyArr[2].getValue(), self.dependsOnIds);
        }

        public final String getUrl() {
            return this.url;
        }

        public final ByteString getSha256() {
            return this.sha256;
        }

        public /* synthetic */ Module(String str, ByteString byteString, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, byteString, (i & 4) != 0 ? CollectionsKt.emptyList() : list);
        }

        public final List<String> getDependsOnIds() {
            return this.dependsOnIds;
        }
    }

    public final Map<String, String> getSignatures() {
        return this.unsigned.getSignatures();
    }

    public final Long getFreshAtEpochMs() {
        return this.unsigned.getFreshAtEpochMs();
    }

    public final String getBaseUrl() {
        return this.unsigned.getBaseUrl();
    }

    public static /* synthetic */ ZiplineManifest copy$default(ZiplineManifest ziplineManifest, Map map, Long l, String str, Map map2, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            map = ziplineManifest.getSignatures();
        }
        if ((i & 2) != 0) {
            l = ziplineManifest.getFreshAtEpochMs();
        }
        if ((i & 4) != 0) {
            str = ziplineManifest.getBaseUrl();
        }
        if ((i & 8) != 0) {
            map2 = ziplineManifest.modules;
        }
        if ((i & 16) != 0) {
            str2 = ziplineManifest.mainModuleId;
        }
        if ((i & 32) != 0) {
            str3 = ziplineManifest.mainFunction;
        }
        if ((i & 64) != 0) {
            str4 = ziplineManifest.version;
        }
        String str5 = str3;
        String str6 = str4;
        String str7 = str2;
        String str8 = str;
        return ziplineManifest.copy(map, l, str8, map2, str7, str5, str6);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This is here for binary-compatibility only")
    public final /* synthetic */ ZiplineManifest copy(Map signatures, Long freshAtEpochMs, String baseUrl, Map modules, String mainModuleId, String mainFunction, String version) {
        Intrinsics.checkNotNullParameter(signatures, "signatures");
        Intrinsics.checkNotNullParameter(modules, "modules");
        Intrinsics.checkNotNullParameter(mainModuleId, "mainModuleId");
        return copy(this.unsigned.copy(signatures, freshAtEpochMs, baseUrl), modules, mainModuleId, mainFunction, version, this.metadata);
    }

    public static /* synthetic */ ZiplineManifest copy$default(ZiplineManifest ziplineManifest, Unsigned unsigned, Map map, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            unsigned = ziplineManifest.unsigned;
        }
        if ((i & 2) != 0) {
            map = ziplineManifest.modules;
        }
        if ((i & 4) != 0) {
            str = ziplineManifest.mainModuleId;
        }
        if ((i & 8) != 0) {
            str2 = ziplineManifest.mainFunction;
        }
        if ((i & 16) != 0) {
            str3 = ziplineManifest.version;
        }
        String str4 = str3;
        String str5 = str;
        return ziplineManifest.copy(unsigned, map, str5, str2, str4);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This is here for binary-compatibility only")
    public final /* synthetic */ ZiplineManifest copy(Unsigned unsigned, Map modules, String mainModuleId, String mainFunction, String version) {
        Intrinsics.checkNotNullParameter(unsigned, "unsigned");
        Intrinsics.checkNotNullParameter(modules, "modules");
        Intrinsics.checkNotNullParameter(mainModuleId, "mainModuleId");
        return copy(unsigned, modules, mainModuleId, mainFunction, version, this.metadata);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ZiplineManifest copy$default(ZiplineManifest ziplineManifest, Map map, Long l, String str, Map map2, String str2, String str3, String str4, Map map3, int i, Object obj) {
        if ((i & 1) != 0) {
            map = ziplineManifest.getSignatures();
        }
        if ((i & 2) != 0) {
            l = ziplineManifest.getFreshAtEpochMs();
        }
        if ((i & 4) != 0) {
            str = ziplineManifest.getBaseUrl();
        }
        if ((i & 8) != 0) {
            map2 = ziplineManifest.modules;
        }
        if ((i & 16) != 0) {
            str2 = ziplineManifest.mainModuleId;
        }
        if ((i & 32) != 0) {
            str3 = ziplineManifest.mainFunction;
        }
        if ((i & 64) != 0) {
            str4 = ziplineManifest.version;
        }
        if ((i & 128) != 0) {
            map3 = ziplineManifest.metadata;
        }
        String str5 = str4;
        Map map4 = map3;
        String str6 = str2;
        String str7 = str3;
        return ziplineManifest.copy(map, l, str, map2, str6, str7, str5, map4);
    }

    public final ZiplineManifest copy(Map<String, String> signatures, Long freshAtEpochMs, String baseUrl, Map<String, Module> modules, String mainModuleId, String mainFunction, String version, Map<String, String> metadata) {
        Intrinsics.checkNotNullParameter(signatures, "signatures");
        Intrinsics.checkNotNullParameter(modules, "modules");
        Intrinsics.checkNotNullParameter(mainModuleId, "mainModuleId");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        return copy(this.unsigned.copy(signatures, freshAtEpochMs, baseUrl), modules, mainModuleId, mainFunction, version, MapsKt.toMap(metadata));
    }

    public final ByteString getSignaturePayload() {
        return ByteString.INSTANCE.encodeUtf8(SignaturePayloadKt.signaturePayload(encodeJson()));
    }

    public final String encodeJson() {
        Json jsonForManifest = app.cash.zipline.internal.SerializersKt.getJsonForManifest();
        jsonForManifest.getSerializersModule();
        String strEncodeToString = jsonForManifest.encodeToString(INSTANCE.serializer(), this);
        if (strEncodeToString.length() <= 655360) {
            return strEncodeToString;
        }
        throw new IllegalStateException(("manifest larger than 655360: " + strEncodeToString.length()).toString());
    }

    /* JADX INFO: compiled from: ZiplineManifest.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J]\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\bH\u0007¢\u0006\u0002\u0010\u0010Jq\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\b2\u0014\b\u0002\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\u0012J\u000e\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\bJ\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u0016¨\u0006\u0017"}, d2 = {"Lapp/cash/zipline/ZiplineManifest$Companion;", "", "<init>", "()V", "create", "Lapp/cash/zipline/ZiplineManifest;", "modules", "", "", "Lapp/cash/zipline/ZiplineManifest$Module;", "mainFunction", "mainModuleId", "version", "builtAtEpochMs", "", "baseUrl", "(Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;)Lapp/cash/zipline/ZiplineManifest;", TtmlNode.TAG_METADATA, "(Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/util/Map;)Lapp/cash/zipline/ZiplineManifest;", "decodeJson", CmcdData.STREAMING_FORMAT_SS, "serializer", "Lkotlinx/serialization/KSerializer;", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<ZiplineManifest> serializer() {
            return ZiplineManifest$$serializer.INSTANCE;
        }

        public static /* synthetic */ ZiplineManifest create$default(Companion companion, Map map, String str, String str2, String str3, Long l, String str4, int i, Object obj) {
            if ((i & 2) != 0) {
                str = null;
            }
            if ((i & 4) != 0) {
                str2 = null;
            }
            if ((i & 8) != 0) {
                str3 = null;
            }
            if ((i & 16) != 0) {
                l = null;
            }
            if ((i & 32) != 0) {
                str4 = null;
            }
            return companion.create(map, str, str2, str3, l, str4);
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This is here for binary-compatibility only")
        public final /* synthetic */ ZiplineManifest create(Map modules, String mainFunction, String mainModuleId, String version, Long builtAtEpochMs, String baseUrl) {
            Intrinsics.checkNotNullParameter(modules, "modules");
            return create(modules, mainFunction, mainModuleId, version, builtAtEpochMs, baseUrl, MapsKt.emptyMap());
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ZiplineManifest create$default(Companion companion, Map map, String str, String str2, String str3, Long l, String str4, Map map2, int i, Object obj) {
            if ((i & 2) != 0) {
                str = null;
            }
            if ((i & 4) != 0) {
                str2 = null;
            }
            if ((i & 8) != 0) {
                str3 = null;
            }
            if ((i & 16) != 0) {
                l = null;
            }
            if ((i & 32) != 0) {
                str4 = null;
            }
            if ((i & 64) != 0) {
                map2 = MapsKt.emptyMap();
            }
            return companion.create(map, str, str2, str3, l, str4, map2);
        }

        public final ZiplineManifest create(final Map<String, Module> modules, String mainFunction, String mainModuleId, String version, Long builtAtEpochMs, String baseUrl, Map<String, String> metadata) {
            Intrinsics.checkNotNullParameter(modules, "modules");
            Intrinsics.checkNotNullParameter(metadata, "metadata");
            List list = TopologicalSortKt.topologicalSort(CollectionsKt.toList(modules.keySet()), new Function1() { // from class: app.cash.zipline.ZiplineManifest$Companion$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ZiplineManifest.Companion.create$lambda$0(modules, (String) obj);
                }
            });
            Unsigned unsigned = new Unsigned(MapsKt.emptyMap(), builtAtEpochMs, baseUrl);
            List list2 = list;
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list2, 10)), 16));
            for (Object obj : list2) {
                String str = (String) obj;
                Module module = modules.get(str);
                if (module == null) {
                    throw new IllegalArgumentException("Unexpected [id=" + str + "] is not found in modules keys");
                }
                linkedHashMap.put(obj, module);
            }
            if (mainModuleId == null) {
                mainModuleId = (String) CollectionsKt.last(list);
            }
            return new ZiplineManifest(unsigned, linkedHashMap, mainModuleId, mainFunction, version, MapsKt.toMap(metadata), null);
        }

        static final Iterable create$lambda$0(Map map, String id) {
            List<String> dependsOnIds;
            Intrinsics.checkNotNullParameter(id, "id");
            Module module = (Module) map.get(id);
            if (module != null && (dependsOnIds = module.getDependsOnIds()) != null) {
                return dependsOnIds;
            }
            throw new IllegalArgumentException("Unexpected [id=" + id + "] is not found in modules keys");
        }

        public final ZiplineManifest decodeJson(String s) {
            Intrinsics.checkNotNullParameter(s, "s");
            if (s.length() > 655360) {
                throw new IllegalStateException(("manifest larger than 655360: " + s.length()).toString());
            }
            Json jsonForManifest = app.cash.zipline.internal.SerializersKt.getJsonForManifest();
            jsonForManifest.getSerializersModule();
            return (ZiplineManifest) jsonForManifest.decodeFromString(ZiplineManifest.INSTANCE.serializer(), s);
        }
    }
}
