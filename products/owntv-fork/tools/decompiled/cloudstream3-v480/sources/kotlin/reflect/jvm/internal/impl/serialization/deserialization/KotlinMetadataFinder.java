package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.io.InputStream;
import kotlin.reflect.jvm.internal.impl.name.FqName;

/* JADX INFO: compiled from: KotlinMetadataFinder.kt */
/* JADX INFO: loaded from: classes7.dex */
public interface KotlinMetadataFinder {
    InputStream findBuiltInsData(FqName fqName);
}
