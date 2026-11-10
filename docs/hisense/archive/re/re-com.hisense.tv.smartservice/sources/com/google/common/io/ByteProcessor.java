package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;

/* JADX INFO: loaded from: classes.dex */
@DoNotMock("Implement it normally")
@Beta
@GwtIncompatible
public interface ByteProcessor<T> {
    T getResult();

    @CanIgnoreReturnValue
    boolean processBytes(byte[] bArr, int i, int i2);
}
