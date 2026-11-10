package com.google.firebase.firestore.bundle;

import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.core.Target;

/* JADX INFO: loaded from: classes.dex */
public class BundledQuery implements BundleElement {
    public final Query.LimitType limitType;
    public final Target target;

    public BundledQuery(Target target, Query.LimitType limitType) {
        this.target = target;
        this.limitType = limitType;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || BundledQuery.class != obj.getClass()) {
            return false;
        }
        BundledQuery bundledQuery = (BundledQuery) obj;
        return this.target.equals(bundledQuery.target) && this.limitType == bundledQuery.limitType;
    }

    public Query.LimitType getLimitType() {
        return this.limitType;
    }

    public Target getTarget() {
        return this.target;
    }

    public int hashCode() {
        return this.limitType.hashCode() + (this.target.hashCode() * 31);
    }
}
