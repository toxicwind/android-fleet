package io.ktor.utils.io.core.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CharArraySequence.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0006\n\u0002\u0010\u0001\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0004H\u0086\u0082\u0004¢\u0006\u0004\b\u000b\u0010\fJ!\u0010\u000f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0086\u0080\u0004¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0012\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0014R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0015R\u001b\u0010\u0006\u001a\u00020\u00048\u0006X\u0086\u0084\b¢\u0006\f\n\u0004\b\u0006\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lio/ktor/utils/io/core/internal/CharArraySequence;", "", "", "array", "", TypedValues.CycleType.S_WAVE_OFFSET, "length", "<init>", "([CII)V", "index", "", "get", "(I)C", "startIndex", "endIndex", "subSequence", "(II)Ljava/lang/CharSequence;", "", "indexOutOfBounds", "(I)Ljava/lang/Void;", "[C", "I", "getLength", "()I", "ktor-io"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CharArraySequence implements CharSequence {
    private final char[] array;
    private final int length;
    private final int offset;

    public CharArraySequence(char[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        this.array = array;
        this.offset = i;
        this.length = i2;
    }

    @Override // java.lang.CharSequence
    public final /* bridge */ char charAt(int i) {
        return get(i);
    }

    @Override // java.lang.CharSequence
    public final /* bridge */ int length() {
        return this.length;
    }

    public final int getLength() {
        return this.length;
    }

    public final char get(int index) {
        if (index >= this.length) {
            indexOutOfBounds(index);
            throw new KotlinNothingValueException();
        }
        return this.array[index + this.offset];
    }

    @Override // java.lang.CharSequence
    public final CharSequence subSequence(int startIndex, int endIndex) {
        if (startIndex < 0) {
            throw new IllegalArgumentException(("startIndex shouldn't be negative: " + startIndex).toString());
        }
        int i = this.length;
        if (startIndex > i) {
            throw new IllegalArgumentException(("startIndex is too large: " + startIndex + " > " + this.length).toString());
        }
        if (startIndex + endIndex > i) {
            throw new IllegalArgumentException(("endIndex is too large: " + endIndex + " > " + this.length).toString());
        }
        if (endIndex < startIndex) {
            throw new IllegalArgumentException(("endIndex should be greater or equal to startIndex: " + startIndex + " > " + endIndex).toString());
        }
        return new CharArraySequence(this.array, this.offset + startIndex, endIndex - startIndex);
    }

    private final Void indexOutOfBounds(int index) {
        throw new IndexOutOfBoundsException("String index out of bounds: " + index + " > " + this.length);
    }
}
