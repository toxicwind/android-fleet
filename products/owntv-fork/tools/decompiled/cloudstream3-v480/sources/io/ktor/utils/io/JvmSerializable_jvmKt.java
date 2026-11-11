package io.ktor.utils.io;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: JvmSerializable.jvm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a/\u0010\u0005\u001a\u00020\u0000\"\b\b\u0000\u0010\u0001*\u00020\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u0004\u001a\u00028\u0000H\u0007¢\u0006\u0004\b\u0005\u0010\u0006*\u0010\b\u0007\u0010\t\"\u00020\u00072\u00020\u0007B\u0002\b\b¨\u0006\n"}, d2 = {"", ExifInterface.GPS_DIRECTION_TRUE, "Lio/ktor/utils/io/JvmSerializer;", "serializer", ES6Iterator.VALUE_PROPERTY, "JvmSerializerReplacement", "(Lio/ktor/utils/io/JvmSerializer;Ljava/lang/Object;)Ljava/lang/Object;", "Ljava/io/Serializable;", "Lio/ktor/utils/io/InternalAPI;", "JvmSerializable", "ktor-io"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class JvmSerializable_jvmKt {
    public static /* synthetic */ void JvmSerializable$annotations() {
    }

    public static final <T> Object JvmSerializerReplacement(JvmSerializer<T> serializer, T value) {
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        Intrinsics.checkNotNullParameter(value, "value");
        return new DefaultJvmSerializerReplacement(serializer, value);
    }
}
