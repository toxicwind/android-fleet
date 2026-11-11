package com.lagradost.cloudstream3.extractors;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ByseSX.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BS\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u000e\b\u0001\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\u0003\u0012\b\b\u0001\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\nHÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J_\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u000e\b\u0003\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\b\b\u0003\u0010\b\u001a\u00020\u00032\b\b\u0003\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u0003HÆ\u0001J\u0014\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010&\u001a\u00020'HÖ\u0081\u0004J\n\u0010(\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0016\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010¨\u0006)"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Playback;", "", "algorithm", "", "iv", "payload", "keyParts", "", "expiresAt", "decryptKeys", "Lcom/lagradost/cloudstream3/extractors/DecryptKeys;", "iv2", "payload2", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Lcom/lagradost/cloudstream3/extractors/DecryptKeys;Ljava/lang/String;Ljava/lang/String;)V", "getAlgorithm", "()Ljava/lang/String;", "getIv", "getPayload", "getKeyParts", "()Ljava/util/List;", "getExpiresAt", "getDecryptKeys", "()Lcom/lagradost/cloudstream3/extractors/DecryptKeys;", "getIv2", "getPayload2", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class Playback {
    private final String algorithm;

    @JsonProperty("decrypt_keys")
    private final DecryptKeys decryptKeys;

    @JsonProperty("expires_at")
    private final String expiresAt;
    private final String iv;
    private final String iv2;

    @JsonProperty("key_parts")
    private final List<String> keyParts;
    private final String payload;
    private final String payload2;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Playback copy$default(Playback playback, String str, String str2, String str3, List list, String str4, DecryptKeys decryptKeys, String str5, String str6, int i, Object obj) {
        if ((i & 1) != 0) {
            str = playback.algorithm;
        }
        if ((i & 2) != 0) {
            str2 = playback.iv;
        }
        if ((i & 4) != 0) {
            str3 = playback.payload;
        }
        if ((i & 8) != 0) {
            list = playback.keyParts;
        }
        if ((i & 16) != 0) {
            str4 = playback.expiresAt;
        }
        if ((i & 32) != 0) {
            decryptKeys = playback.decryptKeys;
        }
        if ((i & 64) != 0) {
            str5 = playback.iv2;
        }
        if ((i & 128) != 0) {
            str6 = playback.payload2;
        }
        String str7 = str5;
        String str8 = str6;
        String str9 = str4;
        DecryptKeys decryptKeys2 = decryptKeys;
        return playback.copy(str, str2, str3, list, str9, decryptKeys2, str7, str8);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAlgorithm() {
        return this.algorithm;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getIv() {
        return this.iv;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getPayload() {
        return this.payload;
    }

    public final List<String> component4() {
        return this.keyParts;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getExpiresAt() {
        return this.expiresAt;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final DecryptKeys getDecryptKeys() {
        return this.decryptKeys;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getIv2() {
        return this.iv2;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getPayload2() {
        return this.payload2;
    }

    public final Playback copy(String algorithm, String iv, String payload, @JsonProperty("key_parts") List<String> keyParts, @JsonProperty("expires_at") String expiresAt, @JsonProperty("decrypt_keys") DecryptKeys decryptKeys, String iv2, String payload2) {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        Intrinsics.checkNotNullParameter(iv, "iv");
        Intrinsics.checkNotNullParameter(payload, "payload");
        Intrinsics.checkNotNullParameter(keyParts, "keyParts");
        Intrinsics.checkNotNullParameter(expiresAt, "expiresAt");
        Intrinsics.checkNotNullParameter(decryptKeys, "decryptKeys");
        Intrinsics.checkNotNullParameter(iv2, "iv2");
        Intrinsics.checkNotNullParameter(payload2, "payload2");
        return new Playback(algorithm, iv, payload, keyParts, expiresAt, decryptKeys, iv2, payload2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Playback)) {
            return false;
        }
        Playback playback = (Playback) other;
        return Intrinsics.areEqual(this.algorithm, playback.algorithm) && Intrinsics.areEqual(this.iv, playback.iv) && Intrinsics.areEqual(this.payload, playback.payload) && Intrinsics.areEqual(this.keyParts, playback.keyParts) && Intrinsics.areEqual(this.expiresAt, playback.expiresAt) && Intrinsics.areEqual(this.decryptKeys, playback.decryptKeys) && Intrinsics.areEqual(this.iv2, playback.iv2) && Intrinsics.areEqual(this.payload2, playback.payload2);
    }

    public int hashCode() {
        return (((((((((((((this.algorithm.hashCode() * 31) + this.iv.hashCode()) * 31) + this.payload.hashCode()) * 31) + this.keyParts.hashCode()) * 31) + this.expiresAt.hashCode()) * 31) + this.decryptKeys.hashCode()) * 31) + this.iv2.hashCode()) * 31) + this.payload2.hashCode();
    }

    public String toString() {
        return "Playback(algorithm=" + this.algorithm + ", iv=" + this.iv + ", payload=" + this.payload + ", keyParts=" + this.keyParts + ", expiresAt=" + this.expiresAt + ", decryptKeys=" + this.decryptKeys + ", iv2=" + this.iv2 + ", payload2=" + this.payload2 + ')';
    }

    public Playback(String algorithm, String iv, String payload, @JsonProperty("key_parts") List<String> keyParts, @JsonProperty("expires_at") String expiresAt, @JsonProperty("decrypt_keys") DecryptKeys decryptKeys, String iv2, String payload2) {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        Intrinsics.checkNotNullParameter(iv, "iv");
        Intrinsics.checkNotNullParameter(payload, "payload");
        Intrinsics.checkNotNullParameter(keyParts, "keyParts");
        Intrinsics.checkNotNullParameter(expiresAt, "expiresAt");
        Intrinsics.checkNotNullParameter(decryptKeys, "decryptKeys");
        Intrinsics.checkNotNullParameter(iv2, "iv2");
        Intrinsics.checkNotNullParameter(payload2, "payload2");
        this.algorithm = algorithm;
        this.iv = iv;
        this.payload = payload;
        this.keyParts = keyParts;
        this.expiresAt = expiresAt;
        this.decryptKeys = decryptKeys;
        this.iv2 = iv2;
        this.payload2 = payload2;
    }

    public final String getAlgorithm() {
        return this.algorithm;
    }

    public final String getIv() {
        return this.iv;
    }

    public final String getPayload() {
        return this.payload;
    }

    public final List<String> getKeyParts() {
        return this.keyParts;
    }

    public final String getExpiresAt() {
        return this.expiresAt;
    }

    public final DecryptKeys getDecryptKeys() {
        return this.decryptKeys;
    }

    public final String getIv2() {
        return this.iv2;
    }

    public final String getPayload2() {
        return this.payload2;
    }
}
