package dev.whyoleg.cryptography.serialization.asn1;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.annotation.AnnotationTarget;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Annotations.kt */
/* JADX INFO: loaded from: classes7.dex */
@Target({})
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\u0002\u0018\u00002\u00020\u0001:\u0001\bB\u0012\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005R\u0013\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0006R\u0013\u0010\u0004\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0007¨\u0006\t"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/ContextSpecificTag;", "", "classIndex", "", "type", "Ldev/whyoleg/cryptography/serialization/asn1/ContextSpecificTag$TagType;", "()B", "()Ldev/whyoleg/cryptography/serialization/asn1/ContextSpecificTag$TagType;", "TagType", "cryptography-serialization-asn1"}, k = 1, mv = {2, 3, 0}, xi = 48)
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.PROPERTY})
@Retention(RetentionPolicy.RUNTIME)
public @interface ContextSpecificTag {

    /* JADX INFO: compiled from: Annotations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class Impl implements ContextSpecificTag {
        private final /* synthetic */ byte classIndex;
        private final /* synthetic */ TagType type;

        public Impl(byte b, TagType type) {
            Intrinsics.checkNotNullParameter(type, "type");
            this.classIndex = b;
            this.type = type;
        }

        @Override // dev.whyoleg.cryptography.serialization.asn1.ContextSpecificTag
        public final /* synthetic */ byte classIndex() {
            return this.classIndex;
        }

        @Override // dev.whyoleg.cryptography.serialization.asn1.ContextSpecificTag
        public final /* synthetic */ TagType type() {
            return this.type;
        }
    }

    /* JADX INFO: compiled from: Annotations.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/ContextSpecificTag$TagType;", "", "<init>", "(Ljava/lang/String;I)V", "IMPLICIT", "EXPLICIT", "cryptography-serialization-asn1"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public enum TagType {
        IMPLICIT,
        EXPLICIT;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<TagType> getEntries() {
            return $ENTRIES;
        }
    }

    byte classIndex();

    TagType type();
}
