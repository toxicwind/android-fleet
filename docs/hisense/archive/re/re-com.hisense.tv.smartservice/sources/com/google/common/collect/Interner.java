package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;

/* JADX INFO: loaded from: classes.dex */
@DoNotMock("Use Interners.new*Interner")
@Beta
@GwtIncompatible
public interface Interner<E> {
    @CanIgnoreReturnValue
    E intern(E e2);
}
