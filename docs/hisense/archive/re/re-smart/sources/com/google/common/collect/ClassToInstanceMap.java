package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@DoNotMock("Use ImmutableClassToInstanceMap or MutableClassToInstanceMap")
@GwtCompatible
public interface ClassToInstanceMap<B> extends Map<Class<? extends B>, B> {
    <T extends B> T getInstance(Class<T> cls);

    @CanIgnoreReturnValue
    <T extends B> T putInstance(Class<T> cls, T t);
}
