package kotlin.reflect.jvm.internal.impl.km.jvm;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.km.KmClass;
import kotlin.reflect.jvm.internal.impl.km.KmLambda;
import kotlin.reflect.jvm.internal.impl.km.KmPackage;
import kotlin.reflect.jvm.internal.impl.km.jvm.internal.JvmReadUtils;

/* JADX INFO: compiled from: KotlinClassMetadata.kt */
/* JADX INFO: loaded from: classes7.dex */
public abstract class KotlinClassMetadata {
    public static final Companion Companion = new Companion(null);
    private boolean isAllowedToWrite;

    public /* synthetic */ KotlinClassMetadata(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private KotlinClassMetadata() {
        this.isAllowedToWrite = true;
    }

    public final void setAllowedToWrite$kotlin_metadata_jvm(boolean z) {
        this.isAllowedToWrite = z;
    }

    /* JADX INFO: compiled from: KotlinClassMetadata.kt */
    public static final class Class extends KotlinClassMetadata {
        private int flags;
        private KmClass kmClass;
        private JvmMetadataVersion version;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Class(KmClass kmClass, JvmMetadataVersion version, int i) {
            super(null);
            Intrinsics.checkNotNullParameter(kmClass, "kmClass");
            Intrinsics.checkNotNullParameter(version, "version");
            this.kmClass = kmClass;
            this.version = version;
            this.flags = i;
        }

        public final KmClass getKmClass() {
            return this.kmClass;
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public Class(Metadata annotationData, boolean z) {
            this(JvmReadUtils.INSTANCE.readKmClass$kotlin_metadata_jvm(annotationData), new JvmMetadataVersion(annotationData.mv()), annotationData.xi());
            Intrinsics.checkNotNullParameter(annotationData, "annotationData");
            setAllowedToWrite$kotlin_metadata_jvm(!z);
        }
    }

    /* JADX INFO: compiled from: KotlinClassMetadata.kt */
    public static final class FileFacade extends KotlinClassMetadata {
        private int flags;
        private KmPackage kmPackage;
        private JvmMetadataVersion version;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FileFacade(KmPackage kmPackage, JvmMetadataVersion version, int i) {
            super(null);
            Intrinsics.checkNotNullParameter(kmPackage, "kmPackage");
            Intrinsics.checkNotNullParameter(version, "version");
            this.kmPackage = kmPackage;
            this.version = version;
            this.flags = i;
        }

        public final KmPackage getKmPackage() {
            return this.kmPackage;
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public FileFacade(Metadata annotationData, boolean z) {
            this(JvmReadUtils.INSTANCE.readKmPackage$kotlin_metadata_jvm(annotationData), new JvmMetadataVersion(annotationData.mv()), annotationData.xi());
            Intrinsics.checkNotNullParameter(annotationData, "annotationData");
            setAllowedToWrite$kotlin_metadata_jvm(!z);
        }
    }

    /* JADX INFO: compiled from: KotlinClassMetadata.kt */
    public static final class SyntheticClass extends KotlinClassMetadata {
        private int flags;
        private KmLambda kmLambda;
        private JvmMetadataVersion version;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SyntheticClass(KmLambda kmLambda, JvmMetadataVersion version, int i) {
            super(null);
            Intrinsics.checkNotNullParameter(version, "version");
            this.kmLambda = kmLambda;
            this.version = version;
            this.flags = i;
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public SyntheticClass(Metadata annotationData, boolean z) {
            this(JvmReadUtils.INSTANCE.readKmLambda$kotlin_metadata_jvm(annotationData), new JvmMetadataVersion(annotationData.mv()), annotationData.xi());
            Intrinsics.checkNotNullParameter(annotationData, "annotationData");
            setAllowedToWrite$kotlin_metadata_jvm(!z);
        }
    }

    /* JADX INFO: compiled from: KotlinClassMetadata.kt */
    public static final class MultiFileClassFacade extends KotlinClassMetadata {
        private int flags;
        private List<String> partClassNames;
        private JvmMetadataVersion version;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MultiFileClassFacade(List<String> partClassNames, JvmMetadataVersion version, int i) {
            super(null);
            Intrinsics.checkNotNullParameter(partClassNames, "partClassNames");
            Intrinsics.checkNotNullParameter(version, "version");
            this.partClassNames = partClassNames;
            this.version = version;
            this.flags = i;
        }

        public final List<String> getPartClassNames() {
            return this.partClassNames;
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public MultiFileClassFacade(Metadata annotationData, boolean z) {
            this(ArraysKt.asList(annotationData.d1()), new JvmMetadataVersion(annotationData.mv()), annotationData.xi());
            Intrinsics.checkNotNullParameter(annotationData, "annotationData");
            setAllowedToWrite$kotlin_metadata_jvm(!z);
        }
    }

    /* JADX INFO: compiled from: KotlinClassMetadata.kt */
    public static final class MultiFileClassPart extends KotlinClassMetadata {
        private String facadeClassName;
        private int flags;
        private KmPackage kmPackage;
        private JvmMetadataVersion version;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MultiFileClassPart(KmPackage kmPackage, String facadeClassName, JvmMetadataVersion version, int i) {
            super(null);
            Intrinsics.checkNotNullParameter(kmPackage, "kmPackage");
            Intrinsics.checkNotNullParameter(facadeClassName, "facadeClassName");
            Intrinsics.checkNotNullParameter(version, "version");
            this.kmPackage = kmPackage;
            this.facadeClassName = facadeClassName;
            this.version = version;
            this.flags = i;
        }

        public final KmPackage getKmPackage() {
            return this.kmPackage;
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public MultiFileClassPart(Metadata annotationData, boolean z) {
            this(JvmReadUtils.INSTANCE.readKmPackage$kotlin_metadata_jvm(annotationData), annotationData.xs(), new JvmMetadataVersion(annotationData.mv()), annotationData.xi());
            Intrinsics.checkNotNullParameter(annotationData, "annotationData");
            setAllowedToWrite$kotlin_metadata_jvm(!z);
        }
    }

    /* JADX INFO: compiled from: KotlinClassMetadata.kt */
    public static final class Unknown extends KotlinClassMetadata {
        private int flags;
        private final boolean lenient;
        private final Metadata original;
        private JvmMetadataVersion version;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Unknown(Metadata original, boolean z) {
            super(null);
            Intrinsics.checkNotNullParameter(original, "original");
            this.original = original;
            this.lenient = z;
            this.version = new JvmMetadataVersion(original.mv());
            this.flags = original.xi();
        }
    }

    /* JADX INFO: compiled from: KotlinClassMetadata.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final KotlinClassMetadata readLenient(Metadata annotationData) {
            Intrinsics.checkNotNullParameter(annotationData, "annotationData");
            return JvmReadUtils.INSTANCE.readMetadataImpl$kotlin_metadata_jvm(annotationData, true);
        }
    }
}
