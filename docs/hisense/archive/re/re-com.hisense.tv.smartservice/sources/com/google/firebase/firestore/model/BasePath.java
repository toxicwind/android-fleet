package com.google.firebase.firestore.model;

import androidx.annotation.NonNull;
import com.google.firebase.firestore.model.BasePath;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Util;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class BasePath<B extends BasePath<B>> implements Comparable<B> {
    public final List<String> segments;

    public BasePath(List<String> list) {
        this.segments = list;
    }

    public B append(String str) {
        ArrayList arrayList = new ArrayList(this.segments);
        arrayList.add(str);
        return (B) createPathWithSegments(arrayList);
    }

    public abstract String canonicalString();

    public abstract B createPathWithSegments(List<String> list);

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof BasePath) && compareTo((BasePath) obj) == 0;
    }

    public String getFirstSegment() {
        return this.segments.get(0);
    }

    public String getLastSegment() {
        return this.segments.get(length() - 1);
    }

    public String getSegment(int i) {
        return this.segments.get(i);
    }

    public int hashCode() {
        return this.segments.hashCode() + ((getClass().hashCode() + 37) * 37);
    }

    public boolean isEmpty() {
        return length() == 0;
    }

    public boolean isImmediateParentOf(B b2) {
        if (length() + 1 != b2.length()) {
            return false;
        }
        for (int i = 0; i < length(); i++) {
            if (!getSegment(i).equals(b2.getSegment(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean isPrefixOf(B b2) {
        if (length() > b2.length()) {
            return false;
        }
        for (int i = 0; i < length(); i++) {
            if (!getSegment(i).equals(b2.getSegment(i))) {
                return false;
            }
        }
        return true;
    }

    public B keepFirst(int i) {
        return (B) createPathWithSegments(this.segments.subList(0, i));
    }

    public int length() {
        return this.segments.size();
    }

    public B popFirst() {
        return (B) popFirst(1);
    }

    public B popLast() {
        return (B) createPathWithSegments(this.segments.subList(0, length() - 1));
    }

    public String toString() {
        return canonicalString();
    }

    @Override // java.lang.Comparable
    public int compareTo(@NonNull B b2) {
        int length = length();
        int length2 = b2.length();
        for (int i = 0; i < length && i < length2; i++) {
            int iCompareTo = getSegment(i).compareTo(b2.getSegment(i));
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return Util.compareIntegers(length, length2);
    }

    public B popFirst(int i) {
        int length = length();
        Assert.hardAssert(length >= i, "Can't call popFirst with count > length() (%d > %d)", Integer.valueOf(i), Integer.valueOf(length));
        return (B) createPathWithSegments(this.segments.subList(i, length));
    }

    public B append(B b2) {
        ArrayList arrayList = new ArrayList(this.segments);
        arrayList.addAll(b2.segments);
        return (B) createPathWithSegments(arrayList);
    }
}
