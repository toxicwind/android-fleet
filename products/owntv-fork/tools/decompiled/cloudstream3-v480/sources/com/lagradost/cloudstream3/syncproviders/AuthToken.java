package com.lagradost.cloudstream3.syncproviders;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lagradost.cloudstream3.APIHolder;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: AuthAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 62\u00020\u0001:\u000256BC\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\t\u0010\nBM\b\u0010\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\t\u0010\u000fJ\u0010\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020\u0006J\u0010\u0010!\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020\u0006J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010$\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0018J\u0010\u0010%\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0018J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003JJ\u0010'\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010(J\u0014\u0010)\u001a\u00020\u001f2\b\u0010*\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010+\u001a\u00020\fHÖ\u0081\u0004J\n\u0010,\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u00002\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u000203H\u0001¢\u0006\u0002\b4R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0013R \u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0019\u0012\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0017\u0010\u0018R \u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0019\u0012\u0004\b\u001a\u0010\u0011\u001a\u0004\b\u001b\u0010\u0018R\u001e\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001c\u0010\u0011\u001a\u0004\b\u001d\u0010\u0013¨\u00067"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/AuthToken;", "", "accessToken", "", "refreshToken", "accessTokenLifetime", "", "refreshTokenLifetime", "payload", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getAccessToken$annotations", "()V", "getAccessToken", "()Ljava/lang/String;", "getRefreshToken$annotations", "getRefreshToken", "getAccessTokenLifetime$annotations", "getAccessTokenLifetime", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getRefreshTokenLifetime$annotations", "getRefreshTokenLifetime", "getPayload$annotations", "getPayload", "isAccessTokenExpired", "", "marginSec", "isRefreshTokenExpired", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;)Lcom/lagradost/cloudstream3/syncproviders/AuthToken;", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Serializable
public final /* data */ class AuthToken {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @JsonProperty("accessToken")
    private final String accessToken;

    @JsonProperty("accessTokenLifetime")
    private final Long accessTokenLifetime;

    @JsonProperty("payload")
    private final String payload;

    @JsonProperty("refreshToken")
    private final String refreshToken;

    @JsonProperty("refreshTokenLifetime")
    private final Long refreshTokenLifetime;

    public AuthToken() {
        this((String) null, (String) null, (Long) null, (Long) null, (String) null, 31, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ AuthToken copy$default(AuthToken authToken, String str, String str2, Long l, Long l2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = authToken.accessToken;
        }
        if ((i & 2) != 0) {
            str2 = authToken.refreshToken;
        }
        if ((i & 4) != 0) {
            l = authToken.accessTokenLifetime;
        }
        if ((i & 8) != 0) {
            l2 = authToken.refreshTokenLifetime;
        }
        if ((i & 16) != 0) {
            str3 = authToken.payload;
        }
        String str4 = str3;
        Long l3 = l;
        return authToken.copy(str, str2, l3, l2, str4);
    }

    @SerialName("accessToken")
    public static /* synthetic */ void getAccessToken$annotations() {
    }

    @SerialName("accessTokenLifetime")
    public static /* synthetic */ void getAccessTokenLifetime$annotations() {
    }

    @SerialName("payload")
    public static /* synthetic */ void getPayload$annotations() {
    }

    @SerialName("refreshToken")
    public static /* synthetic */ void getRefreshToken$annotations() {
    }

    @SerialName("refreshTokenLifetime")
    public static /* synthetic */ void getRefreshTokenLifetime$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAccessToken() {
        return this.accessToken;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getRefreshToken() {
        return this.refreshToken;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Long getAccessTokenLifetime() {
        return this.accessTokenLifetime;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Long getRefreshTokenLifetime() {
        return this.refreshTokenLifetime;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getPayload() {
        return this.payload;
    }

    public final AuthToken copy(@JsonProperty("accessToken") String accessToken, @JsonProperty("refreshToken") String refreshToken, @JsonProperty("accessTokenLifetime") Long accessTokenLifetime, @JsonProperty("refreshTokenLifetime") Long refreshTokenLifetime, @JsonProperty("payload") String payload) {
        return new AuthToken(accessToken, refreshToken, accessTokenLifetime, refreshTokenLifetime, payload);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AuthToken)) {
            return false;
        }
        AuthToken authToken = (AuthToken) other;
        return Intrinsics.areEqual(this.accessToken, authToken.accessToken) && Intrinsics.areEqual(this.refreshToken, authToken.refreshToken) && Intrinsics.areEqual(this.accessTokenLifetime, authToken.accessTokenLifetime) && Intrinsics.areEqual(this.refreshTokenLifetime, authToken.refreshTokenLifetime) && Intrinsics.areEqual(this.payload, authToken.payload);
    }

    public int hashCode() {
        String str = this.accessToken;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.refreshToken;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Long l = this.accessTokenLifetime;
        int iHashCode3 = (iHashCode2 + (l == null ? 0 : l.hashCode())) * 31;
        Long l2 = this.refreshTokenLifetime;
        int iHashCode4 = (iHashCode3 + (l2 == null ? 0 : l2.hashCode())) * 31;
        String str3 = this.payload;
        return iHashCode4 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "AuthToken(accessToken=" + this.accessToken + ", refreshToken=" + this.refreshToken + ", accessTokenLifetime=" + this.accessTokenLifetime + ", refreshTokenLifetime=" + this.refreshTokenLifetime + ", payload=" + this.payload + ')';
    }

    /* JADX INFO: compiled from: AuthAPI.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/AuthToken$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/AuthToken;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<AuthToken> serializer() {
            return AuthToken$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ AuthToken(int i, String str, String str2, Long l, Long l2, String str3, SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 1) == 0) {
            this.accessToken = null;
        } else {
            this.accessToken = str;
        }
        if ((i & 2) == 0) {
            this.refreshToken = null;
        } else {
            this.refreshToken = str2;
        }
        if ((i & 4) == 0) {
            this.accessTokenLifetime = null;
        } else {
            this.accessTokenLifetime = l;
        }
        if ((i & 8) == 0) {
            this.refreshTokenLifetime = null;
        } else {
            this.refreshTokenLifetime = l2;
        }
        if ((i & 16) == 0) {
            this.payload = null;
        } else {
            this.payload = str3;
        }
    }

    public AuthToken(@JsonProperty("accessToken") String str, @JsonProperty("refreshToken") String str2, @JsonProperty("accessTokenLifetime") Long l, @JsonProperty("refreshTokenLifetime") Long l2, @JsonProperty("payload") String str3) {
        this.accessToken = str;
        this.refreshToken = str2;
        this.accessTokenLifetime = l;
        this.refreshTokenLifetime = l2;
        this.payload = str3;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$app(AuthToken self, CompositeEncoder output, SerialDescriptor serialDesc) {
        if (output.shouldEncodeElementDefault(serialDesc, 0) || self.accessToken != null) {
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.accessToken);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || self.refreshToken != null) {
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.refreshToken);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || self.accessTokenLifetime != null) {
            output.encodeNullableSerializableElement(serialDesc, 2, LongSerializer.INSTANCE, self.accessTokenLifetime);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 3) || self.refreshTokenLifetime != null) {
            output.encodeNullableSerializableElement(serialDesc, 3, LongSerializer.INSTANCE, self.refreshTokenLifetime);
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 4) && self.payload == null) {
            return;
        }
        output.encodeNullableSerializableElement(serialDesc, 4, StringSerializer.INSTANCE, self.payload);
    }

    public /* synthetic */ AuthToken(String str, String str2, Long l, Long l2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : l, (i & 8) != 0 ? null : l2, (i & 16) != 0 ? null : str3);
    }

    public final String getAccessToken() {
        return this.accessToken;
    }

    public final String getRefreshToken() {
        return this.refreshToken;
    }

    public final Long getAccessTokenLifetime() {
        return this.accessTokenLifetime;
    }

    public final Long getRefreshTokenLifetime() {
        return this.refreshTokenLifetime;
    }

    public final String getPayload() {
        return this.payload;
    }

    public static /* synthetic */ boolean isAccessTokenExpired$default(AuthToken authToken, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 10;
        }
        return authToken.isAccessTokenExpired(j);
    }

    public final boolean isAccessTokenExpired(long marginSec) {
        return this.accessTokenLifetime != null && APIHolder.INSTANCE.getUnixTime() + marginSec >= this.accessTokenLifetime.longValue();
    }

    public static /* synthetic */ boolean isRefreshTokenExpired$default(AuthToken authToken, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 10;
        }
        return authToken.isRefreshTokenExpired(j);
    }

    public final boolean isRefreshTokenExpired(long marginSec) {
        return this.refreshTokenLifetime != null && APIHolder.INSTANCE.getUnixTime() + marginSec >= this.refreshTokenLifetime.longValue();
    }
}
