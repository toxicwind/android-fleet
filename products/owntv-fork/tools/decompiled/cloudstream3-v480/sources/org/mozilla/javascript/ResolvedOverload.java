package org.mozilla.javascript;

import java.util.Arrays;

/* JADX INFO: compiled from: NativeJavaMethod.java */
/* JADX INFO: loaded from: classes8.dex */
class ResolvedOverload {
    final int index;
    final Class<?>[] types;

    ResolvedOverload(Object[] objArr, int i) {
        this.index = i;
        this.types = new Class[objArr.length];
        int length = objArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            Object objUnwrap = objArr[i2];
            objUnwrap = objUnwrap instanceof Wrapper ? ((Wrapper) objUnwrap).unwrap() : objUnwrap;
            this.types[i2] = objUnwrap == null ? null : objUnwrap.getClass();
        }
    }

    boolean matches(Object[] objArr) {
        if (objArr.length != this.types.length) {
            return false;
        }
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            Object objUnwrap = objArr[i];
            if (objUnwrap instanceof Wrapper) {
                objUnwrap = ((Wrapper) objUnwrap).unwrap();
            }
            if (objUnwrap == null) {
                if (this.types[i] != null) {
                    return false;
                }
            } else if (objUnwrap.getClass() != this.types[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ResolvedOverload)) {
            return false;
        }
        ResolvedOverload resolvedOverload = (ResolvedOverload) obj;
        return Arrays.equals(this.types, resolvedOverload.types) && this.index == resolvedOverload.index;
    }

    public int hashCode() {
        return Arrays.hashCode(this.types);
    }
}
