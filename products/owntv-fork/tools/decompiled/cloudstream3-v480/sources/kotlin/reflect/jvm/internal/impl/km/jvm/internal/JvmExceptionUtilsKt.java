package kotlin.reflect.jvm.internal.impl.km.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.km.InconsistentKotlinMetadataException;

/* JADX INFO: compiled from: JvmExceptionUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
public final class JvmExceptionUtilsKt {
    public static final String[] requireNotEmpty(Metadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "<this>");
        String[] strArrD1 = metadata.d1();
        if (strArrD1.length == 0) {
            strArrD1 = null;
        }
        if (strArrD1 != null) {
            return strArrD1;
        }
        throw new InconsistentKotlinMetadataException("Metadata is missing: kotlin.Metadata.data1 must not be an empty array", null, 2, null);
    }
}
