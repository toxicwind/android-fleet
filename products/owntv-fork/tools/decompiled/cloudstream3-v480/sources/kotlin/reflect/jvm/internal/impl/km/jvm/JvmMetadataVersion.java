package kotlin.reflect.jvm.internal.impl.km.jvm;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.MetadataVersion;

/* JADX INFO: compiled from: JvmMetadataVersion.kt */
/* JADX INFO: loaded from: classes7.dex */
public final class JvmMetadataVersion implements Comparable<JvmMetadataVersion> {
    private final int major;
    private final int minor;
    private final int patch;
    public static final Companion Companion = new Companion(null);
    public static final JvmMetadataVersion LATEST_STABLE_SUPPORTED = new JvmMetadataVersion(MetadataVersion.INSTANCE.toArray());
    public static final JvmMetadataVersion HIGHEST_ALLOWED_TO_WRITE = new JvmMetadataVersion(MetadataVersion.INSTANCE_NEXT.toArray());

    public JvmMetadataVersion(int i, int i2, int i3) {
        this.major = i;
        this.minor = i2;
        this.patch = i3;
        if (i < 0) {
            throw new IllegalArgumentException("Major version should be not less than 0".toString());
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("Minor version should be not less than 0".toString());
        }
        if (i3 < 0) {
            throw new IllegalArgumentException("Patch version should be not less than 0".toString());
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public JvmMetadataVersion(int[] intArray) {
        this(intArray[0], intArray[1], intArray[2]);
        Intrinsics.checkNotNullParameter(intArray, "intArray");
    }

    @Override // java.lang.Comparable
    public int compareTo(JvmMetadataVersion other) {
        Intrinsics.checkNotNullParameter(other, "other");
        int iCompare = Intrinsics.compare(this.major, other.major);
        if (iCompare != 0) {
            return iCompare;
        }
        int iCompare2 = Intrinsics.compare(this.minor, other.minor);
        return iCompare2 != 0 ? iCompare2 : Intrinsics.compare(this.patch, other.patch);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.major);
        sb.append('.');
        sb.append(this.minor);
        sb.append('.');
        sb.append(this.patch);
        return sb.toString();
    }

    public int hashCode() {
        return (((this.major * 31) + this.minor) * 31) + this.patch;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), obj != null ? obj.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.metadata.jvm.JvmMetadataVersion");
        JvmMetadataVersion jvmMetadataVersion = (JvmMetadataVersion) obj;
        return this.major == jvmMetadataVersion.major && this.minor == jvmMetadataVersion.minor && this.patch == jvmMetadataVersion.patch;
    }

    /* JADX INFO: compiled from: JvmMetadataVersion.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
