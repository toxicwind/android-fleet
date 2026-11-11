package com.lagradost.cloudstream3.extractors;

import com.fasterxml.jackson.annotation.JsonProperty;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ByseSX.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/DecryptKeys;", "", "edge1", "", "edge2", "legacyFallback", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEdge1", "()Ljava/lang/String;", "getEdge2", "getLegacyFallback", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class DecryptKeys {

    @JsonProperty("edge_1")
    private final String edge1;

    @JsonProperty("edge_2")
    private final String edge2;

    @JsonProperty("legacy_fallback")
    private final String legacyFallback;

    public static /* synthetic */ DecryptKeys copy$default(DecryptKeys decryptKeys, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = decryptKeys.edge1;
        }
        if ((i & 2) != 0) {
            str2 = decryptKeys.edge2;
        }
        if ((i & 4) != 0) {
            str3 = decryptKeys.legacyFallback;
        }
        return decryptKeys.copy(str, str2, str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getEdge1() {
        return this.edge1;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getEdge2() {
        return this.edge2;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getLegacyFallback() {
        return this.legacyFallback;
    }

    public final DecryptKeys copy(@JsonProperty("edge_1") String edge1, @JsonProperty("edge_2") String edge2, @JsonProperty("legacy_fallback") String legacyFallback) {
        Intrinsics.checkNotNullParameter(edge1, "edge1");
        Intrinsics.checkNotNullParameter(edge2, "edge2");
        Intrinsics.checkNotNullParameter(legacyFallback, "legacyFallback");
        return new DecryptKeys(edge1, edge2, legacyFallback);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DecryptKeys)) {
            return false;
        }
        DecryptKeys decryptKeys = (DecryptKeys) other;
        return Intrinsics.areEqual(this.edge1, decryptKeys.edge1) && Intrinsics.areEqual(this.edge2, decryptKeys.edge2) && Intrinsics.areEqual(this.legacyFallback, decryptKeys.legacyFallback);
    }

    public int hashCode() {
        return (((this.edge1.hashCode() * 31) + this.edge2.hashCode()) * 31) + this.legacyFallback.hashCode();
    }

    public String toString() {
        return "DecryptKeys(edge1=" + this.edge1 + ", edge2=" + this.edge2 + ", legacyFallback=" + this.legacyFallback + ')';
    }

    public DecryptKeys(@JsonProperty("edge_1") String edge1, @JsonProperty("edge_2") String edge2, @JsonProperty("legacy_fallback") String legacyFallback) {
        Intrinsics.checkNotNullParameter(edge1, "edge1");
        Intrinsics.checkNotNullParameter(edge2, "edge2");
        Intrinsics.checkNotNullParameter(legacyFallback, "legacyFallback");
        this.edge1 = edge1;
        this.edge2 = edge2;
        this.legacyFallback = legacyFallback;
    }

    public final String getEdge1() {
        return this.edge1;
    }

    public final String getEdge2() {
        return this.edge2;
    }

    public final String getLegacyFallback() {
        return this.legacyFallback;
    }
}
