package com.google.common.base;

import b.a.a.a.a;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
@Beta
@GwtCompatible
public final class FunctionalEquivalence<F, T> extends Equivalence<F> implements Serializable {
    public static final long serialVersionUID = 0;
    public final Function<F, ? extends T> function;
    public final Equivalence<T> resultEquivalence;

    public FunctionalEquivalence(Function<F, ? extends T> function, Equivalence<T> equivalence) {
        if (function == null) {
            throw new NullPointerException();
        }
        this.function = function;
        if (equivalence == null) {
            throw new NullPointerException();
        }
        this.resultEquivalence = equivalence;
    }

    @Override // com.google.common.base.Equivalence
    public boolean doEquivalent(F f2, F f3) {
        return this.resultEquivalence.equivalent(this.function.apply(f2), this.function.apply(f3));
    }

    @Override // com.google.common.base.Equivalence
    public int doHash(F f2) {
        return this.resultEquivalence.hash(this.function.apply(f2));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FunctionalEquivalence)) {
            return false;
        }
        FunctionalEquivalence functionalEquivalence = (FunctionalEquivalence) obj;
        return this.function.equals(functionalEquivalence.function) && this.resultEquivalence.equals(functionalEquivalence.resultEquivalence);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.function, this.resultEquivalence});
    }

    public String toString() {
        String strValueOf = String.valueOf(this.resultEquivalence);
        String strValueOf2 = String.valueOf(this.function);
        return a.b(strValueOf2.length() + strValueOf.length() + 13, strValueOf, ".onResultOf(", strValueOf2, ")");
    }
}
