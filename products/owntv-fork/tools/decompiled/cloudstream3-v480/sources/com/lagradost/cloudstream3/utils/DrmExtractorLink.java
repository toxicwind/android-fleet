package com.lagradost.cloudstream3.utils;

import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.AudioFile;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.uuid.Uuid;

/* JADX INFO: compiled from: ExtractorApi.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b-\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001BÇ\u0001\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\f\u001a\u00020\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\"\u0010\u0013\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0014j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`\u0015\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018¢\u0006\u0004\b\u001a\u0010\u001bBÇ\u0001\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012$\b\u0002\u0010\u0013\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0014j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`\u0015\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u001a\u0010\u001cB½\u0001\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012$\b\u0002\u0010\u0013\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0014j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`\u0015\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u001a\u0010\u001dJ\u0010\u00109\u001a\u00020G2\u0006\u0010\u0010\u001a\u00020HH\u0007J\b\u00107\u001a\u00020HH\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u0014\u0010\u0005\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001fR\u001a\u0010\u0006\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001f\"\u0004\b#\u0010$R\u001a\u0010\u0007\u001a\u00020\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R&\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u001f\"\u0004\b.\u0010$R\u001a\u0010\f\u001a\u00020\rX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u001f\"\u0004\b4\u0010$R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u001f\"\u0004\b6\u0010$R\u001a\u0010\u0010\u001a\u00020\u0011X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\u001f\"\u0004\b<\u0010$R6\u0010\u0013\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0014j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`\u0015X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010\u001f\"\u0004\bB\u0010$R \u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010D\"\u0004\bE\u0010F¨\u0006I"}, d2 = {"Lcom/lagradost/cloudstream3/utils/DrmExtractorLink;", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "source", "", "name", ImagesContract.URL, "referer", "quality", "", "headers", "", "extractorData", "type", "Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;", "kid", "key", "uuid", "Lkotlin/uuid/Uuid;", "kty", "keyRequestParameters", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "licenseUrl", "audioTracks", "", "Lcom/lagradost/cloudstream3/AudioFile;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/util/Map;Ljava/lang/String;Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;Ljava/lang/String;Ljava/lang/String;Lkotlin/uuid/Uuid;Ljava/lang/String;Ljava/util/HashMap;Ljava/lang/String;Ljava/util/List;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/uuid/Uuid;Ljava/lang/String;Ljava/util/HashMap;Ljava/lang/String;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILcom/lagradost/cloudstream3/utils/ExtractorLinkType;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/uuid/Uuid;Ljava/lang/String;Ljava/util/HashMap;Ljava/lang/String;)V", "getSource", "()Ljava/lang/String;", "getName", "getUrl", "getReferer", "setReferer", "(Ljava/lang/String;)V", "getQuality", "()I", "setQuality", "(I)V", "getHeaders", "()Ljava/util/Map;", "setHeaders", "(Ljava/util/Map;)V", "getExtractorData", "setExtractorData", "getType", "()Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;", "setType", "(Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;)V", "getKid", "setKid", "getKey", "setKey", "getUuid", "()Lkotlin/uuid/Uuid;", "setUuid", "(Lkotlin/uuid/Uuid;)V", "getKty", "setKty", "getKeyRequestParameters", "()Ljava/util/HashMap;", "setKeyRequestParameters", "(Ljava/util/HashMap;)V", "getLicenseUrl", "setLicenseUrl", "getAudioTracks", "()Ljava/util/List;", "setAudioTracks", "(Ljava/util/List;)V", "", "Ljava/util/UUID;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class DrmExtractorLink extends ExtractorLink {
    private List<AudioFile> audioTracks;
    private String extractorData;
    private Map<String, String> headers;
    private String key;
    private HashMap<String, String> keyRequestParameters;
    private String kid;
    private String kty;
    private String licenseUrl;
    private final String name;
    private int quality;
    private String referer;
    private final String source;
    private ExtractorLinkType type;
    private final String url;
    private Uuid uuid;

    private DrmExtractorLink(String str, String str2, String str3, String str4, int i, Map<String, String> map, String str5, ExtractorLinkType extractorLinkType, String str6, String str7, Uuid uuid, String str8, HashMap<String, String> map2, String str9, List<AudioFile> list) {
        super(str, str2, str3, str4, i, map, str5, extractorLinkType, list);
        this.source = str;
        this.name = str2;
        this.url = str3;
        this.referer = str4;
        this.quality = i;
        this.headers = map;
        this.extractorData = str5;
        this.type = extractorLinkType;
        this.kid = str6;
        this.key = str7;
        this.uuid = uuid;
        this.kty = str8;
        this.keyRequestParameters = map2;
        this.licenseUrl = str9;
        this.audioTracks = list;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorLink
    public String getSource() {
        return this.source;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorLink
    public String getName() {
        return this.name;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorLink, com.lagradost.cloudstream3.IDownloadableMinimum
    public String getUrl() {
        return this.url;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorLink, com.lagradost.cloudstream3.IDownloadableMinimum
    public String getReferer() {
        return this.referer;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorLink
    public void setReferer(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.referer = str;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorLink
    public int getQuality() {
        return this.quality;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorLink
    public void setQuality(int i) {
        this.quality = i;
    }

    /* synthetic */ DrmExtractorLink(String str, String str2, String str3, String str4, int i, Map map, String str5, ExtractorLinkType extractorLinkType, String str6, String str7, Uuid uuid, String str8, HashMap map2, String str9, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, i, (i2 & 32) != 0 ? MapsKt.emptyMap() : map, (i2 & 64) != 0 ? null : str5, extractorLinkType, (i2 & 256) != 0 ? null : str6, (i2 & 512) != 0 ? null : str7, uuid, (i2 & 2048) != 0 ? null : str8, map2, (i2 & 8192) != 0 ? null : str9, (i2 & 16384) != 0 ? CollectionsKt.emptyList() : list);
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorLink, com.lagradost.cloudstream3.IDownloadableMinimum
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorLink
    public void setHeaders(Map<String, String> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.headers = map;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorLink
    public String getExtractorData() {
        return this.extractorData;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorLink
    public void setExtractorData(String str) {
        this.extractorData = str;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorLink
    public ExtractorLinkType getType() {
        return this.type;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorLink
    public void setType(ExtractorLinkType extractorLinkType) {
        Intrinsics.checkNotNullParameter(extractorLinkType, "<set-?>");
        this.type = extractorLinkType;
    }

    public String getKid() {
        return this.kid;
    }

    public void setKid(String str) {
        this.kid = str;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public Uuid getUuid() {
        return this.uuid;
    }

    public void setUuid(Uuid uuid) {
        Intrinsics.checkNotNullParameter(uuid, "<set-?>");
        this.uuid = uuid;
    }

    public String getKty() {
        return this.kty;
    }

    public void setKty(String str) {
        this.kty = str;
    }

    public HashMap<String, String> getKeyRequestParameters() {
        return this.keyRequestParameters;
    }

    public void setKeyRequestParameters(HashMap<String, String> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.keyRequestParameters = map;
    }

    public String getLicenseUrl() {
        return this.licenseUrl;
    }

    public void setLicenseUrl(String str) {
        this.licenseUrl = str;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorLink
    public List<AudioFile> getAudioTracks() {
        return this.audioTracks;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorLink
    public void setAudioTracks(List<AudioFile> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.audioTracks = list;
    }

    public /* synthetic */ DrmExtractorLink(String str, String str2, String str3, String str4, Integer num, ExtractorLinkType extractorLinkType, Map map, String str5, String str6, String str7, Uuid uuid, String str8, HashMap map2, String str9, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : num, (i & 32) != 0 ? ExtractorApiKt.getINFER_TYPE() : extractorLinkType, (Map<String, String>) ((i & 64) != 0 ? MapsKt.emptyMap() : map), (i & 128) != 0 ? null : str5, (i & 256) != 0 ? null : str6, (i & 512) != 0 ? null : str7, (i & 1024) != 0 ? ExtractorApiKt.getCLEARKEY_DRM_UUID() : uuid, (i & 2048) != 0 ? "oct" : str8, (HashMap<String, String>) ((i & 4096) != 0 ? new HashMap() : map2), (i & 8192) != 0 ? null : str9);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use newDrmExtractorLink")
    public DrmExtractorLink(String source, String name, String url, String str, Integer num, ExtractorLinkType extractorLinkType, Map<String, String> headers, String str2, String str3, String str4, Uuid uuid, String str5, HashMap<String, String> keyRequestParameters, String str6) {
        this(source, name, url, str == null ? "" : str, num != null ? num.intValue() : Qualities.Unknown.getValue(), headers, str2, extractorLinkType == null ? ExtractorApiKt.inferTypeFromUrl(url) : extractorLinkType, str3, str4, uuid, str5, keyRequestParameters, str6, null, 16384, null);
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        Intrinsics.checkNotNullParameter(keyRequestParameters, "keyRequestParameters");
    }

    public /* synthetic */ DrmExtractorLink(String str, String str2, String str3, String str4, int i, ExtractorLinkType extractorLinkType, Map map, String str5, String str6, String str7, Uuid uuid, String str8, HashMap map2, String str9, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, i, extractorLinkType, (Map<String, String>) ((i2 & 64) != 0 ? MapsKt.emptyMap() : map), (i2 & 128) != 0 ? null : str5, (i2 & 256) != 0 ? null : str6, (i2 & 512) != 0 ? null : str7, (i2 & 1024) != 0 ? ExtractorApiKt.getCLEARKEY_DRM_UUID() : uuid, (i2 & 2048) != 0 ? "oct" : str8, (HashMap<String, String>) ((i2 & 4096) != 0 ? new HashMap() : map2), (i2 & 8192) != 0 ? null : str9);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use newDrmExtractorLink")
    public DrmExtractorLink(String source, String name, String url, String referer, int i, ExtractorLinkType extractorLinkType, Map<String, String> headers, String str, String str2, String str3, Uuid uuid, String str4, HashMap<String, String> keyRequestParameters, String str5) {
        this(source, name, url, referer, i, headers, str, extractorLinkType == null ? ExtractorApiKt.inferTypeFromUrl(url) : extractorLinkType, str2, str3, uuid, str4, keyRequestParameters, str5, null, 16384, null);
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(referer, "referer");
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        Intrinsics.checkNotNullParameter(keyRequestParameters, "keyRequestParameters");
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use Kotlin Uuid")
    public final /* synthetic */ UUID getUuid() {
        Uuid uuid = getUuid();
        return new UUID(uuid.getMostSignificantBits(), uuid.getLeastSignificantBits());
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use Kotlin Uuid")
    public final /* synthetic */ void setUuid(UUID uuid) {
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        setUuid(Uuid.INSTANCE.fromLongs(uuid.getMostSignificantBits(), uuid.getLeastSignificantBits()));
    }
}
