package androidx.compose.runtime;

import kotlin.Metadata;

/* JADX INFO: compiled from: BitwiseOperators.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0080\f\u001a\u0015\u0010\u0000\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0001H\u0080\f\u001a\u0015\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0080\f\u001a\u0015\u0010\u0004\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0001H\u0080\f¨\u0006\u0005"}, d2 = {"ror", "", "other", "", "rol", "runtime"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class BitwiseOperatorsKt {
    public static final int ror(int i, int i2) {
        return Integer.rotateRight(i, i2);
    }

    public static final long ror(long j, int i) {
        return Long.rotateRight(j, i);
    }

    public static final int rol(int i, int i2) {
        return Integer.rotateLeft(i, i2);
    }

    public static final long rol(long j, int i) {
        return Long.rotateLeft(j, i);
    }
}
