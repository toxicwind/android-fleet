package com.lagradost.cloudstream3.syncproviders;

import androidx.autofill.HintConstants;
import com.fasterxml.jackson.annotation.JsonProperty;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: AuthAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 +2\u00020\u0001:\u0002*+B7\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bBC\b\u0010\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0007\u0010\rJ\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J9\u0010\u001c\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010 \u001a\u00020\nHÖ\u0081\u0004J\n\u0010!\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00002\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0001¢\u0006\u0002\b)R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0011R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0015\u0010\u0011R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u000f\u001a\u0004\b\u0017\u0010\u0011¨\u0006,"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/AuthLoginResponse;", "", HintConstants.AUTOFILL_HINT_PASSWORD, "", HintConstants.AUTOFILL_HINT_USERNAME, "email", "server", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getPassword$annotations", "()V", "getPassword", "()Ljava/lang/String;", "getUsername$annotations", "getUsername", "getEmail$annotations", "getEmail", "getServer$annotations", "getServer", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Serializable
public final /* data */ class AuthLoginResponse {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @JsonProperty("email")
    private final String email;

    @JsonProperty(HintConstants.AUTOFILL_HINT_PASSWORD)
    private final String password;

    @JsonProperty("server")
    private final String server;

    @JsonProperty(HintConstants.AUTOFILL_HINT_USERNAME)
    private final String username;

    public static /* synthetic */ AuthLoginResponse copy$default(AuthLoginResponse authLoginResponse, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = authLoginResponse.password;
        }
        if ((i & 2) != 0) {
            str2 = authLoginResponse.username;
        }
        if ((i & 4) != 0) {
            str3 = authLoginResponse.email;
        }
        if ((i & 8) != 0) {
            str4 = authLoginResponse.server;
        }
        return authLoginResponse.copy(str, str2, str3, str4);
    }

    @SerialName("email")
    public static /* synthetic */ void getEmail$annotations() {
    }

    @SerialName(HintConstants.AUTOFILL_HINT_PASSWORD)
    public static /* synthetic */ void getPassword$annotations() {
    }

    @SerialName("server")
    public static /* synthetic */ void getServer$annotations() {
    }

    @SerialName(HintConstants.AUTOFILL_HINT_USERNAME)
    public static /* synthetic */ void getUsername$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getPassword() {
        return this.password;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getUsername() {
        return this.username;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getEmail() {
        return this.email;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getServer() {
        return this.server;
    }

    public final AuthLoginResponse copy(@JsonProperty(HintConstants.AUTOFILL_HINT_PASSWORD) String password, @JsonProperty(HintConstants.AUTOFILL_HINT_USERNAME) String username, @JsonProperty("email") String email, @JsonProperty("server") String server) {
        return new AuthLoginResponse(password, username, email, server);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AuthLoginResponse)) {
            return false;
        }
        AuthLoginResponse authLoginResponse = (AuthLoginResponse) other;
        return Intrinsics.areEqual(this.password, authLoginResponse.password) && Intrinsics.areEqual(this.username, authLoginResponse.username) && Intrinsics.areEqual(this.email, authLoginResponse.email) && Intrinsics.areEqual(this.server, authLoginResponse.server);
    }

    public int hashCode() {
        String str = this.password;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.username;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.email;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.server;
        return iHashCode3 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "AuthLoginResponse(password=" + this.password + ", username=" + this.username + ", email=" + this.email + ", server=" + this.server + ')';
    }

    /* JADX INFO: compiled from: AuthAPI.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/AuthLoginResponse$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/AuthLoginResponse;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<AuthLoginResponse> serializer() {
            return AuthLoginResponse$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ AuthLoginResponse(int i, String str, String str2, String str3, String str4, SerializationConstructorMarker serializationConstructorMarker) {
        if (15 != (i & 15)) {
            PluginExceptionsKt.throwMissingFieldException(i, 15, AuthLoginResponse$$serializer.INSTANCE.getDescriptor());
        }
        this.password = str;
        this.username = str2;
        this.email = str3;
        this.server = str4;
    }

    public AuthLoginResponse(@JsonProperty(HintConstants.AUTOFILL_HINT_PASSWORD) String str, @JsonProperty(HintConstants.AUTOFILL_HINT_USERNAME) String str2, @JsonProperty("email") String str3, @JsonProperty("server") String str4) {
        this.password = str;
        this.username = str2;
        this.email = str3;
        this.server = str4;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$app(AuthLoginResponse self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.password);
        output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.username);
        output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.email);
        output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.server);
    }

    public final String getPassword() {
        return this.password;
    }

    public final String getUsername() {
        return this.username;
    }

    public final String getEmail() {
        return this.email;
    }

    public final String getServer() {
        return this.server;
    }
}
