package com.lagradost.cloudstream3.syncproviders;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
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
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.json.JsonNames;

/* JADX INFO: compiled from: AuthAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 .2\u00020\u0001:\u0002-.BA\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0016\b\u0003\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nBM\b\u0010\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\t\u0010\u000eJ\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0017\u0010\u001e\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\bHÆ\u0003JC\u0010\u001f\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0016\b\u0003\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\bHÆ\u0001J\u0014\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010#\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010$\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00002\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0001¢\u0006\u0002\b,R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0010\u001a\u0004\b\u0017\u0010\u0012R*\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0010\u001a\u0004\b\u0019\u0010\u001a¨\u0006/"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/AuthUser;", "", "name", "", "id", "", "profilePicture", "profilePictureHeaders", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;Ljava/util/Map;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;ILjava/lang/String;Ljava/util/Map;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getName$annotations", "()V", "getName", "()Ljava/lang/String;", "getId$annotations", "getId", "()I", "getProfilePicture$annotations", "getProfilePicture", "getProfilePictureHeaders$annotations", "getProfilePictureHeaders", "()Ljava/util/Map;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Serializable
public final /* data */ class AuthUser {

    @JsonProperty("id")
    private final int id;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("profilePicture")
    private final String profilePicture;

    @JsonProperty("profilePictureHeaders")
    @JsonAlias({"profilePictureHeader"})
    private final Map<String, String> profilePictureHeaders;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.AuthUser$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return AuthUser._childSerializers$_anonymous_();
        }
    })};

    /* JADX INFO: Access modifiers changed from: private */
    public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
        return new LinkedHashMapSerializer(StringSerializer.INSTANCE, StringSerializer.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AuthUser copy$default(AuthUser authUser, String str, int i, String str2, Map map, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = authUser.name;
        }
        if ((i2 & 2) != 0) {
            i = authUser.id;
        }
        if ((i2 & 4) != 0) {
            str2 = authUser.profilePicture;
        }
        if ((i2 & 8) != 0) {
            map = authUser.profilePictureHeaders;
        }
        return authUser.copy(str, i, str2, map);
    }

    @SerialName("id")
    public static /* synthetic */ void getId$annotations() {
    }

    @SerialName("name")
    public static /* synthetic */ void getName$annotations() {
    }

    @SerialName("profilePicture")
    public static /* synthetic */ void getProfilePicture$annotations() {
    }

    @SerialName("profilePictureHeaders")
    @JsonNames(names = {"profilePictureHeader"})
    public static /* synthetic */ void getProfilePictureHeaders$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getProfilePicture() {
        return this.profilePicture;
    }

    public final Map<String, String> component4() {
        return this.profilePictureHeaders;
    }

    public final AuthUser copy(@JsonProperty("name") String name, @JsonProperty("id") int id, @JsonProperty("profilePicture") String profilePicture, @JsonProperty("profilePictureHeaders") @JsonAlias({"profilePictureHeader"}) Map<String, String> profilePictureHeaders) {
        return new AuthUser(name, id, profilePicture, profilePictureHeaders);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AuthUser)) {
            return false;
        }
        AuthUser authUser = (AuthUser) other;
        return Intrinsics.areEqual(this.name, authUser.name) && this.id == authUser.id && Intrinsics.areEqual(this.profilePicture, authUser.profilePicture) && Intrinsics.areEqual(this.profilePictureHeaders, authUser.profilePictureHeaders);
    }

    public int hashCode() {
        String str = this.name;
        int iHashCode = (((str == null ? 0 : str.hashCode()) * 31) + this.id) * 31;
        String str2 = this.profilePicture;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Map<String, String> map = this.profilePictureHeaders;
        return iHashCode2 + (map != null ? map.hashCode() : 0);
    }

    public String toString() {
        return "AuthUser(name=" + this.name + ", id=" + this.id + ", profilePicture=" + this.profilePicture + ", profilePictureHeaders=" + this.profilePictureHeaders + ')';
    }

    /* JADX INFO: compiled from: AuthAPI.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/AuthUser$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/AuthUser;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<AuthUser> serializer() {
            return AuthUser$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ AuthUser(int i, String str, int i2, String str2, Map map, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, AuthUser$$serializer.INSTANCE.getDescriptor());
        }
        this.name = str;
        this.id = i2;
        if ((i & 4) == 0) {
            this.profilePicture = null;
        } else {
            this.profilePicture = str2;
        }
        if ((i & 8) == 0) {
            this.profilePictureHeaders = null;
        } else {
            this.profilePictureHeaders = map;
        }
    }

    public AuthUser(@JsonProperty("name") String str, @JsonProperty("id") int i, @JsonProperty("profilePicture") String str2, @JsonProperty("profilePictureHeaders") @JsonAlias({"profilePictureHeader"}) Map<String, String> map) {
        this.name = str;
        this.id = i;
        this.profilePicture = str2;
        this.profilePictureHeaders = map;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$app(AuthUser self, CompositeEncoder output, SerialDescriptor serialDesc) {
        Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
        output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.name);
        output.encodeIntElement(serialDesc, 1, self.id);
        if (output.shouldEncodeElementDefault(serialDesc, 2) || self.profilePicture != null) {
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.profilePicture);
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 3) && self.profilePictureHeaders == null) {
            return;
        }
        output.encodeNullableSerializableElement(serialDesc, 3, lazyArr[3].getValue(), self.profilePictureHeaders);
    }

    public /* synthetic */ AuthUser(String str, int i, String str2, Map map, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, (i2 & 4) != 0 ? null : str2, (i2 & 8) != 0 ? null : map);
    }

    public final String getName() {
        return this.name;
    }

    public final int getId() {
        return this.id;
    }

    public final String getProfilePicture() {
        return this.profilePicture;
    }

    public final Map<String, String> getProfilePictureHeaders() {
        return this.profilePictureHeaders;
    }
}
