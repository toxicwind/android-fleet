package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import java.util.Collections;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public final class Absent<T> extends Optional<T> {
    public static final Absent<Object> INSTANCE = new Absent<>();
    public static final long serialVersionUID = 0;

    private Object readResolve() {
        return INSTANCE;
    }

    public static <T> Optional<T> withType() {
        return INSTANCE;
    }

    @Override // com.google.common.base.Optional
    public Set<T> asSet() {
        return Collections.emptySet();
    }

    @Override // com.google.common.base.Optional
    public boolean equals(Object obj) {
        return obj == this;
    }

    @Override // com.google.common.base.Optional
    public T get() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    @Override // com.google.common.base.Optional
    public int hashCode() {
        return 2040732332;
    }

    @Override // com.google.common.base.Optional
    public boolean isPresent() {
        return false;
    }

    @Override // com.google.common.base.Optional
    public T or(T t) {
        Preconditions.checkNotNull(t, "use Optional.orNull() instead of Optional.or(null)");
        return t;
    }

    @Override // com.google.common.base.Optional
    public T orNull() {
        return null;
    }

    @Override // com.google.common.base.Optional
    public String toString() {
        return "Optional.absent()";
    }

    @Override // com.google.common.base.Optional
    public <V> Optional<V> transform(Function<? super T, V> function) {
        if (function != null) {
            return INSTANCE;
        }
        throw new NullPointerException();
    }

    @Override // com.google.common.base.Optional
    public T or(Supplier<? extends T> supplier) {
        T t = supplier.get();
        Preconditions.checkNotNull(t, "use Optional.orNull() instead of a Supplier that returns null");
        return t;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.base.Optional
    public Optional<T> or(Optional<? extends T> optional) {
        if (optional != 0) {
            return optional;
        }
        throw new NullPointerException();
    }
}
