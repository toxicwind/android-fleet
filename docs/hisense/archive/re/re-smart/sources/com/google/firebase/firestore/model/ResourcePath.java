package com.google.firebase.firestore.model;

import b.a.a.a.a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ResourcePath extends BasePath<ResourcePath> {
    public static final ResourcePath EMPTY = new ResourcePath(Collections.emptyList());

    public ResourcePath(List<String> list) {
        super(list);
    }

    public static ResourcePath fromSegments(List<String> list) {
        return list.isEmpty() ? EMPTY : new ResourcePath(list);
    }

    public static ResourcePath fromString(String str) {
        if (str.contains("//")) {
            throw new IllegalArgumentException(a.a("Invalid path (", str, "). Paths must not contain // in them."));
        }
        String[] strArrSplit = str.split("/");
        ArrayList arrayList = new ArrayList(strArrSplit.length);
        for (String str2 : strArrSplit) {
            if (!str2.isEmpty()) {
                arrayList.add(str2);
            }
        }
        return new ResourcePath(arrayList);
    }

    @Override // com.google.firebase.firestore.model.BasePath
    public String canonicalString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.segments.size(); i++) {
            if (i > 0) {
                sb.append("/");
            }
            sb.append(this.segments.get(i));
        }
        return sb.toString();
    }

    @Override // com.google.firebase.firestore.model.BasePath
    public /* bridge */ /* synthetic */ BasePath createPathWithSegments(List list) {
        return createPathWithSegments((List<String>) list);
    }

    @Override // com.google.firebase.firestore.model.BasePath
    public ResourcePath createPathWithSegments(List<String> list) {
        return new ResourcePath(list);
    }
}
