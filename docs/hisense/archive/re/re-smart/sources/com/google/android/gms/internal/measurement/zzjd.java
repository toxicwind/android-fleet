package com.google.android.gms.internal.measurement;

import com.google.protobuf.CodedOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class zzjd extends IOException {
    public zzjd() {
        super(CodedOutputStream.OutOfSpaceException.MESSAGE);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public zzjd(String str, Throwable th) {
        String strValueOf = String.valueOf(str);
        super(strValueOf.length() != 0 ? "CodedOutputStream was writing to a flat byte array and ran out of space.: ".concat(strValueOf) : new String("CodedOutputStream was writing to a flat byte array and ran out of space.: "), th);
    }

    public zzjd(Throwable th) {
        super(CodedOutputStream.OutOfSpaceException.MESSAGE, th);
    }
}
