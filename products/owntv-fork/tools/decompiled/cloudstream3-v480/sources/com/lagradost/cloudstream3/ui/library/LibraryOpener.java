package com.lagradost.cloudstream3.ui.library;

import com.fasterxml.jackson.annotation.JsonProperty;
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
import kotlinx.serialization.internal.EnumsKt;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* JADX INFO: compiled from: LibraryFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 &2\u00020\u0001:\u0002%&B\u001d\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007B/\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\fJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u0016\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J%\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0001¢\u0006\u0002\b$R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0013¨\u0006'"}, d2 = {"Lcom/lagradost/cloudstream3/ui/library/LibraryOpener;", "", "openType", "Lcom/lagradost/cloudstream3/ui/library/LibraryOpenerType;", "providerData", "Lcom/lagradost/cloudstream3/ui/library/ProviderLibraryData;", "<init>", "(Lcom/lagradost/cloudstream3/ui/library/LibraryOpenerType;Lcom/lagradost/cloudstream3/ui/library/ProviderLibraryData;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/ui/library/LibraryOpenerType;Lcom/lagradost/cloudstream3/ui/library/ProviderLibraryData;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getOpenType$annotations", "()V", "getOpenType", "()Lcom/lagradost/cloudstream3/ui/library/LibraryOpenerType;", "getProviderData$annotations", "getProviderData", "()Lcom/lagradost/cloudstream3/ui/library/ProviderLibraryData;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Serializable
public final /* data */ class LibraryOpener {

    @JsonProperty("openType")
    private final LibraryOpenerType openType;

    @JsonProperty("providerData")
    private final ProviderLibraryData providerData;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<KSerializer<Object>>[] $childSerializers = {LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.ui.library.LibraryOpener$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return LibraryOpener._childSerializers$_anonymous_();
        }
    }), null};

    /* JADX INFO: Access modifiers changed from: private */
    public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
        return EnumsKt.createSimpleEnumSerializer("com.lagradost.cloudstream3.ui.library.LibraryOpenerType", LibraryOpenerType.values());
    }

    public static /* synthetic */ LibraryOpener copy$default(LibraryOpener libraryOpener, LibraryOpenerType libraryOpenerType, ProviderLibraryData providerLibraryData, int i, Object obj) {
        if ((i & 1) != 0) {
            libraryOpenerType = libraryOpener.openType;
        }
        if ((i & 2) != 0) {
            providerLibraryData = libraryOpener.providerData;
        }
        return libraryOpener.copy(libraryOpenerType, providerLibraryData);
    }

    @SerialName("openType")
    public static /* synthetic */ void getOpenType$annotations() {
    }

    @SerialName("providerData")
    public static /* synthetic */ void getProviderData$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final LibraryOpenerType getOpenType() {
        return this.openType;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ProviderLibraryData getProviderData() {
        return this.providerData;
    }

    public final LibraryOpener copy(@JsonProperty("openType") LibraryOpenerType openType, @JsonProperty("providerData") ProviderLibraryData providerData) {
        Intrinsics.checkNotNullParameter(openType, "openType");
        return new LibraryOpener(openType, providerData);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LibraryOpener)) {
            return false;
        }
        LibraryOpener libraryOpener = (LibraryOpener) other;
        return this.openType == libraryOpener.openType && Intrinsics.areEqual(this.providerData, libraryOpener.providerData);
    }

    public int hashCode() {
        int iHashCode = this.openType.hashCode() * 31;
        ProviderLibraryData providerLibraryData = this.providerData;
        return iHashCode + (providerLibraryData == null ? 0 : providerLibraryData.hashCode());
    }

    public String toString() {
        return "LibraryOpener(openType=" + this.openType + ", providerData=" + this.providerData + ')';
    }

    /* JADX INFO: compiled from: LibraryFragment.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/ui/library/LibraryOpener$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/ui/library/LibraryOpener;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<LibraryOpener> serializer() {
            return LibraryOpener$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ LibraryOpener(int i, LibraryOpenerType libraryOpenerType, ProviderLibraryData providerLibraryData, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, LibraryOpener$$serializer.INSTANCE.getDescriptor());
        }
        this.openType = libraryOpenerType;
        this.providerData = providerLibraryData;
    }

    public LibraryOpener(@JsonProperty("openType") LibraryOpenerType openType, @JsonProperty("providerData") ProviderLibraryData providerLibraryData) {
        Intrinsics.checkNotNullParameter(openType, "openType");
        this.openType = openType;
        this.providerData = providerLibraryData;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$app(LibraryOpener self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeSerializableElement(serialDesc, 0, $childSerializers[0].getValue(), self.openType);
        output.encodeNullableSerializableElement(serialDesc, 1, ProviderLibraryData$$serializer.INSTANCE, self.providerData);
    }

    public final LibraryOpenerType getOpenType() {
        return this.openType;
    }

    public final ProviderLibraryData getProviderData() {
        return this.providerData;
    }
}
