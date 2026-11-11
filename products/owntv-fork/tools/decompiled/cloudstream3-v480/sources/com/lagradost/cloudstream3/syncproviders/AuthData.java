package com.lagradost.cloudstream3.syncproviders;

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

/* JADX INFO: compiled from: AuthAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 &2\u00020\u0001:\u0002%&B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B/\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\fJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0016\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J%\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0001¢\u0006\u0002\b$R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0013¨\u0006'"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/AuthData;", "", "user", "Lcom/lagradost/cloudstream3/syncproviders/AuthUser;", "token", "Lcom/lagradost/cloudstream3/syncproviders/AuthToken;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/AuthUser;Lcom/lagradost/cloudstream3/syncproviders/AuthToken;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/AuthUser;Lcom/lagradost/cloudstream3/syncproviders/AuthToken;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getUser$annotations", "()V", "getUser", "()Lcom/lagradost/cloudstream3/syncproviders/AuthUser;", "getToken$annotations", "getToken", "()Lcom/lagradost/cloudstream3/syncproviders/AuthToken;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Serializable
public final /* data */ class AuthData {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @JsonProperty("token")
    private final AuthToken token;

    @JsonProperty("user")
    private final AuthUser user;

    public static /* synthetic */ AuthData copy$default(AuthData authData, AuthUser authUser, AuthToken authToken, int i, Object obj) {
        if ((i & 1) != 0) {
            authUser = authData.user;
        }
        if ((i & 2) != 0) {
            authToken = authData.token;
        }
        return authData.copy(authUser, authToken);
    }

    @SerialName("token")
    public static /* synthetic */ void getToken$annotations() {
    }

    @SerialName("user")
    public static /* synthetic */ void getUser$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final AuthUser getUser() {
        return this.user;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final AuthToken getToken() {
        return this.token;
    }

    public final AuthData copy(@JsonProperty("user") AuthUser user, @JsonProperty("token") AuthToken token) {
        Intrinsics.checkNotNullParameter(user, "user");
        Intrinsics.checkNotNullParameter(token, "token");
        return new AuthData(user, token);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AuthData)) {
            return false;
        }
        AuthData authData = (AuthData) other;
        return Intrinsics.areEqual(this.user, authData.user) && Intrinsics.areEqual(this.token, authData.token);
    }

    public int hashCode() {
        return (this.user.hashCode() * 31) + this.token.hashCode();
    }

    public String toString() {
        return "AuthData(user=" + this.user + ", token=" + this.token + ')';
    }

    /* JADX INFO: compiled from: AuthAPI.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/AuthData$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/AuthData;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<AuthData> serializer() {
            return AuthData$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ AuthData(int i, AuthUser authUser, AuthToken authToken, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, AuthData$$serializer.INSTANCE.getDescriptor());
        }
        this.user = authUser;
        this.token = authToken;
    }

    public AuthData(@JsonProperty("user") AuthUser user, @JsonProperty("token") AuthToken token) {
        Intrinsics.checkNotNullParameter(user, "user");
        Intrinsics.checkNotNullParameter(token, "token");
        this.user = user;
        this.token = token;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$app(AuthData self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeSerializableElement(serialDesc, 0, AuthUser$$serializer.INSTANCE, self.user);
        output.encodeSerializableElement(serialDesc, 1, AuthToken$$serializer.INSTANCE, self.token);
    }

    public final AuthUser getUser() {
        return this.user;
    }

    public final AuthToken getToken() {
        return this.token;
    }
}
