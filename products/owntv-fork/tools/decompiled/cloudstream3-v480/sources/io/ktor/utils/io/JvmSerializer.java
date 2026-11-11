package io.ktor.utils.io;

import androidx.exifinterface.media.ExifInterface;
import java.io.Serializable;
import kotlin.Metadata;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: JvmSerializable.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\bg\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00060\u0002j\u0002`\u0003J\u0017\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00028\u0000H&¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\b\u001a\u00028\u00002\u0006\u0010\u0004\u001a\u00020\u0005H&¢\u0006\u0004\b\b\u0010\t¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lio/ktor/utils/io/JvmSerializer;", ExifInterface.GPS_DIRECTION_TRUE, "Ljava/io/Serializable;", "Lio/ktor/utils/io/JvmSerializable;", ES6Iterator.VALUE_PROPERTY, "", "jvmSerialize", "(Ljava/lang/Object;)[B", "jvmDeserialize", "([B)Ljava/lang/Object;", "ktor-io"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface JvmSerializer<T> extends Serializable {
    T jvmDeserialize(byte[] value);

    byte[] jvmSerialize(T value);
}
