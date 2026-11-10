package com.google.firebase.firestore.model.mutation;

import b.a.a.a.a;
import com.google.firebase.firestore.model.FieldPath;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class FieldMask {
    public static FieldMask EMPTY = new FieldMask(new HashSet());
    public final Set<FieldPath> mask;

    public FieldMask(Set<FieldPath> set) {
        this.mask = set;
    }

    public static FieldMask fromSet(Set<FieldPath> set) {
        return new FieldMask(set);
    }

    public boolean covers(FieldPath fieldPath) {
        Iterator<FieldPath> it = this.mask.iterator();
        while (it.hasNext()) {
            if (it.next().isPrefixOf(fieldPath)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || FieldMask.class != obj.getClass()) {
            return false;
        }
        return this.mask.equals(((FieldMask) obj).mask);
    }

    public Set<FieldPath> getMask() {
        return this.mask;
    }

    public int hashCode() {
        return this.mask.hashCode();
    }

    public String toString() {
        StringBuilder sbA = a.a("FieldMask{mask=");
        sbA.append(this.mask.toString());
        sbA.append("}");
        return sbA.toString();
    }
}
