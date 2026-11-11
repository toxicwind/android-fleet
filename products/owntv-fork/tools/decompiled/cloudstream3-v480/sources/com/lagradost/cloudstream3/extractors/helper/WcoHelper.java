package com.lagradost.cloudstream3.extractors.helper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import com.lagradost.nicehttp.ResponseParser;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: WcoHelper.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/helper/WcoHelper;", "", "<init>", "()V", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class WcoHelper {
    private static final String BACKUP_KEY_DATA = "github_keys_backup";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static Companion.ExternalKeys keys;
    private static Companion.NewExternalKeys newKeys;

    /* JADX INFO: compiled from: WcoHelper.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0002\u0010\u0011B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u000bH\u0082@¢\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\u0004\u0018\u00010\u0007H\u0086@¢\u0006\u0002\u0010\fJ\u000e\u0010\u000e\u001a\u00020\u000bH\u0082@¢\u0006\u0002\u0010\fJ\u0010\u0010\u000f\u001a\u0004\u0018\u00010\tH\u0086@¢\u0006\u0002\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/helper/WcoHelper$Companion;", "", "<init>", "()V", "BACKUP_KEY_DATA", "", "keys", "Lcom/lagradost/cloudstream3/extractors/helper/WcoHelper$Companion$ExternalKeys;", "newKeys", "Lcom/lagradost/cloudstream3/extractors/helper/WcoHelper$Companion$NewExternalKeys;", "getKeys", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWcoKey", "getNewKeys", "getNewWcoKey", "ExternalKeys", "NewExternalKeys", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: compiled from: WcoHelper.kt */
        @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 #2\u00020\u0001:\u0002\"#B\u001f\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006B/\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0005\u0010\u000bJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u0014\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0001¢\u0006\u0002\b!R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u000f¨\u0006$"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/helper/WcoHelper$Companion$ExternalKeys;", "", "wcoKey", "", "wcocipher", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getWcoKey$annotations", "()V", "getWcoKey", "()Ljava/lang/String;", "getWcocipher$annotations", "getWcocipher", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$library", "$serializer", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
        @Serializable
        public static final /* data */ class ExternalKeys {

            /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
            public static final Companion INSTANCE = new Companion(null);

            @JsonProperty("wco_key")
            private final String wcoKey;

            @JsonProperty("wco_cipher_key")
            private final String wcocipher;

            /* JADX WARN: Multi-variable type inference failed */
            public ExternalKeys() {
                this((String) null, (String) (0 == true ? 1 : 0), 3, (DefaultConstructorMarker) (0 == true ? 1 : 0));
            }

            public static /* synthetic */ ExternalKeys copy$default(ExternalKeys externalKeys, String str, String str2, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = externalKeys.wcoKey;
                }
                if ((i & 2) != 0) {
                    str2 = externalKeys.wcocipher;
                }
                return externalKeys.copy(str, str2);
            }

            @SerialName("wco_key")
            public static /* synthetic */ void getWcoKey$annotations() {
            }

            @SerialName("wco_cipher_key")
            public static /* synthetic */ void getWcocipher$annotations() {
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getWcoKey() {
                return this.wcoKey;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getWcocipher() {
                return this.wcocipher;
            }

            public final ExternalKeys copy(@JsonProperty("wco_key") String wcoKey, @JsonProperty("wco_cipher_key") String wcocipher) {
                return new ExternalKeys(wcoKey, wcocipher);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof ExternalKeys)) {
                    return false;
                }
                ExternalKeys externalKeys = (ExternalKeys) other;
                return Intrinsics.areEqual(this.wcoKey, externalKeys.wcoKey) && Intrinsics.areEqual(this.wcocipher, externalKeys.wcocipher);
            }

            public int hashCode() {
                String str = this.wcoKey;
                int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
                String str2 = this.wcocipher;
                return iHashCode + (str2 != null ? str2.hashCode() : 0);
            }

            public String toString() {
                return "ExternalKeys(wcoKey=" + this.wcoKey + ", wcocipher=" + this.wcocipher + ')';
            }

            /* JADX INFO: compiled from: WcoHelper.kt */
            @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/helper/WcoHelper$Companion$ExternalKeys$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/extractors/helper/WcoHelper$Companion$ExternalKeys;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static final class Companion {
                public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private Companion() {
                }

                public final KSerializer<ExternalKeys> serializer() {
                    return WcoHelper$Companion$ExternalKeys$$serializer.INSTANCE;
                }
            }

            public /* synthetic */ ExternalKeys(int i, String str, String str2, SerializationConstructorMarker serializationConstructorMarker) {
                if ((i & 1) == 0) {
                    this.wcoKey = null;
                } else {
                    this.wcoKey = str;
                }
                if ((i & 2) == 0) {
                    this.wcocipher = null;
                } else {
                    this.wcocipher = str2;
                }
            }

            public ExternalKeys(@JsonProperty("wco_key") String str, @JsonProperty("wco_cipher_key") String str2) {
                this.wcoKey = str;
                this.wcocipher = str2;
            }

            @JvmStatic
            public static final /* synthetic */ void write$Self$library(ExternalKeys self, CompositeEncoder output, SerialDescriptor serialDesc) {
                if (output.shouldEncodeElementDefault(serialDesc, 0) || self.wcoKey != null) {
                    output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.wcoKey);
                }
                if (!output.shouldEncodeElementDefault(serialDesc, 1) && self.wcocipher == null) {
                    return;
                }
                output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.wcocipher);
            }

            public /* synthetic */ ExternalKeys(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2);
            }

            public final String getWcoKey() {
                return this.wcoKey;
            }

            public final String getWcocipher() {
                return this.wcocipher;
            }
        }

        /* JADX INFO: compiled from: WcoHelper.kt */
        @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 '2\u00020\u0001:\u0002&'B+\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007B9\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\fJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u0018\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0001¢\u0006\u0002\b%R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0010R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0014\u0010\u0010¨\u0006("}, d2 = {"Lcom/lagradost/cloudstream3/extractors/helper/WcoHelper$Companion$NewExternalKeys;", "", "cipherkey", "", "encryptKey", "mainKey", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getCipherkey$annotations", "()V", "getCipherkey", "()Ljava/lang/String;", "getEncryptKey$annotations", "getEncryptKey", "getMainKey$annotations", "getMainKey", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$library", "$serializer", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
        @Serializable
        public static final /* data */ class NewExternalKeys {

            /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
            public static final Companion INSTANCE = new Companion(null);

            @JsonProperty("cipherKey")
            private final String cipherkey;

            @JsonProperty("encryptKey")
            private final String encryptKey;

            @JsonProperty("mainKey")
            private final String mainKey;

            public NewExternalKeys() {
                this((String) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null);
            }

            public static /* synthetic */ NewExternalKeys copy$default(NewExternalKeys newExternalKeys, String str, String str2, String str3, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = newExternalKeys.cipherkey;
                }
                if ((i & 2) != 0) {
                    str2 = newExternalKeys.encryptKey;
                }
                if ((i & 4) != 0) {
                    str3 = newExternalKeys.mainKey;
                }
                return newExternalKeys.copy(str, str2, str3);
            }

            @SerialName("cipherKey")
            public static /* synthetic */ void getCipherkey$annotations() {
            }

            @SerialName("encryptKey")
            public static /* synthetic */ void getEncryptKey$annotations() {
            }

            @SerialName("mainKey")
            public static /* synthetic */ void getMainKey$annotations() {
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getCipherkey() {
                return this.cipherkey;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getEncryptKey() {
                return this.encryptKey;
            }

            /* JADX INFO: renamed from: component3, reason: from getter */
            public final String getMainKey() {
                return this.mainKey;
            }

            public final NewExternalKeys copy(@JsonProperty("cipherKey") String cipherkey, @JsonProperty("encryptKey") String encryptKey, @JsonProperty("mainKey") String mainKey) {
                return new NewExternalKeys(cipherkey, encryptKey, mainKey);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof NewExternalKeys)) {
                    return false;
                }
                NewExternalKeys newExternalKeys = (NewExternalKeys) other;
                return Intrinsics.areEqual(this.cipherkey, newExternalKeys.cipherkey) && Intrinsics.areEqual(this.encryptKey, newExternalKeys.encryptKey) && Intrinsics.areEqual(this.mainKey, newExternalKeys.mainKey);
            }

            public int hashCode() {
                String str = this.cipherkey;
                int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
                String str2 = this.encryptKey;
                int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
                String str3 = this.mainKey;
                return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
            }

            public String toString() {
                return "NewExternalKeys(cipherkey=" + this.cipherkey + ", encryptKey=" + this.encryptKey + ", mainKey=" + this.mainKey + ')';
            }

            /* JADX INFO: compiled from: WcoHelper.kt */
            @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/helper/WcoHelper$Companion$NewExternalKeys$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/extractors/helper/WcoHelper$Companion$NewExternalKeys;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static final class Companion {
                public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private Companion() {
                }

                public final KSerializer<NewExternalKeys> serializer() {
                    return WcoHelper$Companion$NewExternalKeys$$serializer.INSTANCE;
                }
            }

            public /* synthetic */ NewExternalKeys(int i, String str, String str2, String str3, SerializationConstructorMarker serializationConstructorMarker) {
                if ((i & 1) == 0) {
                    this.cipherkey = null;
                } else {
                    this.cipherkey = str;
                }
                if ((i & 2) == 0) {
                    this.encryptKey = null;
                } else {
                    this.encryptKey = str2;
                }
                if ((i & 4) == 0) {
                    this.mainKey = null;
                } else {
                    this.mainKey = str3;
                }
            }

            public NewExternalKeys(@JsonProperty("cipherKey") String str, @JsonProperty("encryptKey") String str2, @JsonProperty("mainKey") String str3) {
                this.cipherkey = str;
                this.encryptKey = str2;
                this.mainKey = str3;
            }

            @JvmStatic
            public static final /* synthetic */ void write$Self$library(NewExternalKeys self, CompositeEncoder output, SerialDescriptor serialDesc) {
                if (output.shouldEncodeElementDefault(serialDesc, 0) || self.cipherkey != null) {
                    output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.cipherkey);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 1) || self.encryptKey != null) {
                    output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.encryptKey);
                }
                if (!output.shouldEncodeElementDefault(serialDesc, 2) && self.mainKey == null) {
                    return;
                }
                output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.mainKey);
            }

            public /* synthetic */ NewExternalKeys(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3);
            }

            public final String getCipherkey() {
                return this.cipherkey;
            }

            public final String getEncryptKey() {
                return this.encryptKey;
            }

            public final String getMainKey() {
                return this.mainKey;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Code duplicated, block: B:7:0x0016  */
        public final Object getKeys(Continuation<? super Unit> continuation) {
            WcoHelper$Companion$getKeys$1 wcoHelper$Companion$getKeys$1;
            ExternalKeys externalKeys;
            NiceResponse niceResponse;
            Object safe;
            if (continuation instanceof WcoHelper$Companion$getKeys$1) {
                wcoHelper$Companion$getKeys$1 = (WcoHelper$Companion$getKeys$1) continuation;
                if ((wcoHelper$Companion$getKeys$1.label & Integer.MIN_VALUE) != 0) {
                    wcoHelper$Companion$getKeys$1.label -= Integer.MIN_VALUE;
                } else {
                    wcoHelper$Companion$getKeys$1 = new WcoHelper$Companion$getKeys$1(this, continuation);
                }
            } else {
                wcoHelper$Companion$getKeys$1 = new WcoHelper$Companion$getKeys$1(this, continuation);
            }
            Object obj = wcoHelper$Companion$getKeys$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = wcoHelper$Companion$getKeys$1.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    externalKeys = WcoHelper.keys;
                    if (externalKeys == null) {
                        Requests app2 = MainActivityKt.getApp();
                        wcoHelper$Companion$getKeys$1.label = 1;
                        Object obj2 = Requests.get$default(app2, "https://raw.githubusercontent.com/reduplicated/Cloudstream/master/docs/keys.json", null, null, null, null, false, 0, null, 0L, null, false, null, wcoHelper$Companion$getKeys$1, 4094, null);
                        if (obj2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        obj = obj2;
                    }
                    WcoHelper.keys = externalKeys;
                    return Unit.INSTANCE;
                }
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ResponseParser parser = niceResponse.getParser();
                Intrinsics.checkNotNull(parser);
                safe = parser.parseSafe(niceResponse.getText(), Reflection.getOrCreateKotlinClass(ExternalKeys.class));
            } catch (Exception e) {
                e.printStackTrace();
                safe = null;
            }
            niceResponse = (NiceResponse) obj;
            externalKeys = (ExternalKeys) safe;
            WcoHelper.keys = externalKeys;
            return Unit.INSTANCE;
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        public final Object getWcoKey(Continuation<? super ExternalKeys> continuation) {
            WcoHelper$Companion$getWcoKey$1 wcoHelper$Companion$getWcoKey$1;
            if (continuation instanceof WcoHelper$Companion$getWcoKey$1) {
                wcoHelper$Companion$getWcoKey$1 = (WcoHelper$Companion$getWcoKey$1) continuation;
                if ((wcoHelper$Companion$getWcoKey$1.label & Integer.MIN_VALUE) != 0) {
                    wcoHelper$Companion$getWcoKey$1.label -= Integer.MIN_VALUE;
                } else {
                    wcoHelper$Companion$getWcoKey$1 = new WcoHelper$Companion$getWcoKey$1(this, continuation);
                }
            } else {
                wcoHelper$Companion$getWcoKey$1 = new WcoHelper$Companion$getWcoKey$1(this, continuation);
            }
            Object obj = wcoHelper$Companion$getWcoKey$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = wcoHelper$Companion$getWcoKey$1.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                wcoHelper$Companion$getWcoKey$1.label = 1;
                if (getKeys(wcoHelper$Companion$getWcoKey$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return WcoHelper.keys;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Code duplicated, block: B:7:0x0016  */
        public final Object getNewKeys(Continuation<? super Unit> continuation) {
            WcoHelper$Companion$getNewKeys$1 wcoHelper$Companion$getNewKeys$1;
            NewExternalKeys newExternalKeys;
            NiceResponse niceResponse;
            Object safe;
            if (continuation instanceof WcoHelper$Companion$getNewKeys$1) {
                wcoHelper$Companion$getNewKeys$1 = (WcoHelper$Companion$getNewKeys$1) continuation;
                if ((wcoHelper$Companion$getNewKeys$1.label & Integer.MIN_VALUE) != 0) {
                    wcoHelper$Companion$getNewKeys$1.label -= Integer.MIN_VALUE;
                } else {
                    wcoHelper$Companion$getNewKeys$1 = new WcoHelper$Companion$getNewKeys$1(this, continuation);
                }
            } else {
                wcoHelper$Companion$getNewKeys$1 = new WcoHelper$Companion$getNewKeys$1(this, continuation);
            }
            Object obj = wcoHelper$Companion$getNewKeys$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = wcoHelper$Companion$getNewKeys$1.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    newExternalKeys = WcoHelper.newKeys;
                    if (newExternalKeys == null) {
                        Requests app2 = MainActivityKt.getApp();
                        wcoHelper$Companion$getNewKeys$1.label = 1;
                        Object obj2 = Requests.get$default(app2, "https://raw.githubusercontent.com/chekaslowakiya/BruhFlow/main/keys.json", null, null, null, null, false, 0, null, 0L, null, false, null, wcoHelper$Companion$getNewKeys$1, 4094, null);
                        if (obj2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        obj = obj2;
                    }
                    WcoHelper.newKeys = newExternalKeys;
                    return Unit.INSTANCE;
                }
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ResponseParser parser = niceResponse.getParser();
                Intrinsics.checkNotNull(parser);
                safe = parser.parseSafe(niceResponse.getText(), Reflection.getOrCreateKotlinClass(NewExternalKeys.class));
            } catch (Exception e) {
                e.printStackTrace();
                safe = null;
            }
            niceResponse = (NiceResponse) obj;
            newExternalKeys = (NewExternalKeys) safe;
            WcoHelper.newKeys = newExternalKeys;
            return Unit.INSTANCE;
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        public final Object getNewWcoKey(Continuation<? super NewExternalKeys> continuation) {
            WcoHelper$Companion$getNewWcoKey$1 wcoHelper$Companion$getNewWcoKey$1;
            if (continuation instanceof WcoHelper$Companion$getNewWcoKey$1) {
                wcoHelper$Companion$getNewWcoKey$1 = (WcoHelper$Companion$getNewWcoKey$1) continuation;
                if ((wcoHelper$Companion$getNewWcoKey$1.label & Integer.MIN_VALUE) != 0) {
                    wcoHelper$Companion$getNewWcoKey$1.label -= Integer.MIN_VALUE;
                } else {
                    wcoHelper$Companion$getNewWcoKey$1 = new WcoHelper$Companion$getNewWcoKey$1(this, continuation);
                }
            } else {
                wcoHelper$Companion$getNewWcoKey$1 = new WcoHelper$Companion$getNewWcoKey$1(this, continuation);
            }
            Object obj = wcoHelper$Companion$getNewWcoKey$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = wcoHelper$Companion$getNewWcoKey$1.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                wcoHelper$Companion$getNewWcoKey$1.label = 1;
                if (getNewKeys(wcoHelper$Companion$getNewWcoKey$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return WcoHelper.newKeys;
        }
    }
}
