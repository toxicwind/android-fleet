package org.schabi.newpipe.extractor.utils;

import j$.util.Objects;
import java.io.Serializable;

/* JADX INFO: loaded from: classes8.dex */
public class Pair<F extends Serializable, S extends Serializable> implements Serializable {
    private F firstObject;
    private S secondObject;

    public Pair(F f, S s) {
        this.firstObject = f;
        this.secondObject = s;
    }

    public void setFirst(F f) {
        this.firstObject = f;
    }

    public void setSecond(S s) {
        this.secondObject = s;
    }

    public F getFirst() {
        return this.firstObject;
    }

    public S getSecond() {
        return this.secondObject;
    }

    public String toString() {
        return "{" + this.firstObject + ", " + this.secondObject + "}";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            Pair pair = (Pair) obj;
            if (Objects.equals(this.firstObject, pair.firstObject) && Objects.equals(this.secondObject, pair.secondObject)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.firstObject, this.secondObject);
    }
}
