package kotlin.reflect.jvm.internal.impl.km.jvm.internal;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.km.InconsistentKotlinMetadataException;
import kotlin.reflect.jvm.internal.impl.km.KmClass;
import kotlin.reflect.jvm.internal.impl.km.KmLambda;
import kotlin.reflect.jvm.internal.impl.km.KmPackage;
import kotlin.reflect.jvm.internal.impl.km.internal.ReadersKt;
import kotlin.reflect.jvm.internal.impl.km.jvm.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.km.jvm.KotlinClassMetadata;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.MetadataVersion;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmNameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;

/* JADX INFO: compiled from: JvmReadUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
public final class JvmReadUtils {
    public static final JvmReadUtils INSTANCE = new JvmReadUtils();

    private JvmReadUtils() {
    }

    private final boolean isLessThan14(Metadata metadata) {
        return new JvmMetadataVersion(metadata.mv()).compareTo(new JvmMetadataVersion(1, 4, 0)) < 0;
    }

    public final KmClass readKmClass$kotlin_metadata_jvm(Metadata annotationData) {
        Intrinsics.checkNotNullParameter(annotationData, "annotationData");
        Pair<JvmNameResolver, ProtoBuf.Class> classDataFrom = JvmProtoBufUtil.readClassDataFrom(JvmExceptionUtilsKt.requireNotEmpty(annotationData), annotationData.d2());
        return ReadersKt.toKmClass$default(classDataFrom.component2(), classDataFrom.component1(), isLessThan14(annotationData), null, 4, null);
    }

    public final KmPackage readKmPackage$kotlin_metadata_jvm(Metadata annotationData) {
        Intrinsics.checkNotNullParameter(annotationData, "annotationData");
        Pair<JvmNameResolver, ProtoBuf.Package> packageDataFrom = JvmProtoBufUtil.readPackageDataFrom(JvmExceptionUtilsKt.requireNotEmpty(annotationData), annotationData.d2());
        return ReadersKt.toKmPackage$default(packageDataFrom.component2(), packageDataFrom.component1(), isLessThan14(annotationData), null, 4, null);
    }

    public final KmLambda readKmLambda$kotlin_metadata_jvm(Metadata annotationData) {
        Pair<JvmNameResolver, ProtoBuf.Function> functionDataFrom;
        Intrinsics.checkNotNullParameter(annotationData, "annotationData");
        String[] strArrD1 = annotationData.d1();
        if (strArrD1.length == 0) {
            strArrD1 = null;
        }
        if (strArrD1 == null || (functionDataFrom = JvmProtoBufUtil.readFunctionDataFrom(strArrD1, annotationData.d2())) == null) {
            return null;
        }
        return ReadersKt.toKmLambda(functionDataFrom.component2(), functionDataFrom.component1(), isLessThan14(annotationData));
    }

    public final KotlinClassMetadata readMetadataImpl$kotlin_metadata_jvm(Metadata annotationData, boolean z) {
        Intrinsics.checkNotNullParameter(annotationData, "annotationData");
        checkMetadataVersionForRead(annotationData, z);
        try {
            int iK = annotationData.k();
            if (iK == 1) {
                return new KotlinClassMetadata.Class(annotationData, z);
            }
            if (iK == 2) {
                return new KotlinClassMetadata.FileFacade(annotationData, z);
            }
            if (iK == 3) {
                return new KotlinClassMetadata.SyntheticClass(annotationData, z);
            }
            if (iK == 4) {
                return new KotlinClassMetadata.MultiFileClassFacade(annotationData, z);
            }
            if (iK == 5) {
                return new KotlinClassMetadata.MultiFileClassPart(annotationData, z);
            }
            return new KotlinClassMetadata.Unknown(annotationData, z);
        } catch (Throwable th) {
            if ((th instanceof IllegalArgumentException) || (th instanceof VirtualMachineError) || (th instanceof ThreadDeath)) {
                throw th;
            }
            throw new InconsistentKotlinMetadataException("Exception occurred when reading Kotlin metadata", th);
        }
    }

    private final void checkMetadataVersionForRead(Metadata metadata, boolean z) {
        if (metadata.mv().length == 0) {
            throw new IllegalArgumentException("Provided Metadata instance does not have metadataVersion in it and therefore is malformed and cannot be read.");
        }
        throwIfNotCompatible$kotlin_metadata_jvm(new MetadataVersion(metadata.mv(), (metadata.xi() & 8) != 0), z);
    }

    public final void throwIfNotCompatible$kotlin_metadata_jvm(MetadataVersion jvmMetadataVersion, boolean z) {
        String string;
        Intrinsics.checkNotNullParameter(jvmMetadataVersion, "jvmMetadataVersion");
        boolean zIsAtLeast = jvmMetadataVersion.isAtLeast(1, 1, 0);
        if (z ? zIsAtLeast : jvmMetadataVersion.isCompatibleWithCurrentCompilerVersion()) {
            return;
        }
        if (!zIsAtLeast) {
            string = "while minimum supported version is 1.1.0 (Kotlin 1.0).";
        } else {
            StringBuilder sb = new StringBuilder("while maximum supported version is ");
            sb.append(jvmMetadataVersion.isStrictSemantics() ? MetadataVersion.INSTANCE : MetadataVersion.INSTANCE_NEXT);
            sb.append(". To support newer versions, update the kotlin-metadata-jvm library.");
            string = sb.toString();
        }
        throw new IllegalArgumentException("Provided Metadata instance has version " + jvmMetadataVersion + ", " + string);
    }
}
