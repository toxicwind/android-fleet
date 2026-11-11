package io.ktor.utils.io;

import androidx.exifinterface.media.ExifInterface;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: JvmSerializable.jvm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0001\u0018\u0000 \u0017*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0003:\u0001\u0017B!\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u0007\u0010\bB\t\b\u0016¢\u0006\u0004\b\u0007\u0010\tJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0005\u0010\u0015R\u0018\u0010\u0006\u001a\u0004\u0018\u00018\u00008\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0006\u0010\u0016¨\u0006\u0018"}, d2 = {"Lio/ktor/utils/io/DefaultJvmSerializerReplacement;", "", ExifInterface.GPS_DIRECTION_TRUE, "Ljava/io/Externalizable;", "Lio/ktor/utils/io/JvmSerializer;", "serializer", ES6Iterator.VALUE_PROPERTY, "<init>", "(Lio/ktor/utils/io/JvmSerializer;Ljava/lang/Object;)V", "()V", "Ljava/io/ObjectOutput;", "out", "", "writeExternal", "(Ljava/io/ObjectOutput;)V", "Ljava/io/ObjectInput;", "in", "readExternal", "(Ljava/io/ObjectInput;)V", "readResolve", "()Ljava/lang/Object;", "Lio/ktor/utils/io/JvmSerializer;", "Ljava/lang/Object;", "Companion", "ktor-io"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DefaultJvmSerializerReplacement<T> implements Externalizable {
    private static final long serialVersionUID = 0;
    private JvmSerializer<T> serializer;
    private T value;

    public DefaultJvmSerializerReplacement(JvmSerializer<T> jvmSerializer, T t) {
        this.serializer = jvmSerializer;
        this.value = t;
    }

    public DefaultJvmSerializerReplacement() {
        this(null, null);
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput out) throws IOException {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeObject(this.serializer);
        JvmSerializer<T> jvmSerializer = this.serializer;
        Intrinsics.checkNotNull(jvmSerializer);
        T t = this.value;
        Intrinsics.checkNotNull(t);
        out.writeObject(jvmSerializer.jvmSerialize(t));
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput in) throws ClassNotFoundException, IOException {
        Intrinsics.checkNotNullParameter(in, "in");
        Object object = in.readObject();
        Intrinsics.checkNotNull(object, "null cannot be cast to non-null type io.ktor.utils.io.JvmSerializer<T of io.ktor.utils.io.DefaultJvmSerializerReplacement>");
        JvmSerializer<T> jvmSerializer = (JvmSerializer) object;
        this.serializer = jvmSerializer;
        Intrinsics.checkNotNull(jvmSerializer);
        Object object2 = in.readObject();
        Intrinsics.checkNotNull(object2, "null cannot be cast to non-null type kotlin.ByteArray");
        this.value = jvmSerializer.jvmDeserialize((byte[]) object2);
    }

    private final Object readResolve() {
        T t = this.value;
        Intrinsics.checkNotNull(t);
        return t;
    }
}
