package com.google.common.base;

import b.a.a.a.a;
import com.google.common.annotations.GwtCompatible;
import java.util.Collections;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public final class Present<T> extends Optional<T> {
    public static final long serialVersionUID = 0;
    public final T reference;

    public Present(T t) {
        this.reference = t;
    }

    @Override // com.google.common.base.Optional
    public Set<T> asSet() {
        return Collections.singleton(this.reference);
    }

    @Override // com.google.common.base.Optional
    public boolean equals(Object obj) {
        if (obj instanceof Present) {
            return this.reference.equals(((Present) obj).reference);
        }
        return false;
    }

    @Override // com.google.common.base.Optional
    public T get() {
        return this.reference;
    }

    @Override // com.google.common.base.Optional
    public int hashCode() {
        return this.reference.hashCode() + 1502476572;
    }

    @Override // com.google.common.base.Optional
    public boolean isPresent() {
        return true;
    }

    @Override // com.google.common.base.Optional
    public T or(T t) {
        Preconditions.checkNotNull(t, "use Optional.orNull() instead of Optional.or(null)");
        return this.reference;
    }

    @Override // com.google.common.base.Optional
    public T orNull() {
        return this.reference;
    }

    @Override // com.google.common.base.Optional
    public String toString() {
        String strValueOf = String.valueOf(this.reference);
        return a.a(strValueOf.length() + 13, "Optional.of(", strValueOf, ")");
    }

    @Override // com.google.common.base.Optional
    public <V> Optional<V> transform(Function<? super T, V> function) {
        V vApply = function.apply(this.reference);
        Preconditions.checkNotNull(vApply, "the Function passed to Optional.transform() must not return null.");
        return new Present(vApply);
    }

    @Override // com.google.common.base.Optional
    public T or(Supplier<? extends T> supplier) {
        if (supplier != null) {
            return this.reference;
        }
        throw new NullPointerException();
    }

    @Override // com.google.common.base.Optional
    public Optional<T> or(Optional<? extends T> optional) {
        if (optional != null) {
            return this;
        }
        throw new NullPointerException();
    }
}
