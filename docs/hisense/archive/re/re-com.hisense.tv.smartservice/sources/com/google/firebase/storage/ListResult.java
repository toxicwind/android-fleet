package com.google.firebase.storage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import e.b.a;
import e.b.d;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ListResult {
    public static final String ITEMS_KEY = "items";
    public static final String NAME_KEY = "name";
    public static final String PAGE_TOKEN_KEY = "nextPageToken";
    public static final String PREFIXES_KEY = "prefixes";
    public final List<StorageReference> items;

    @Nullable
    public final String pageToken;
    public final List<StorageReference> prefixes;

    public ListResult(List<StorageReference> list, List<StorageReference> list2, @Nullable String str) {
        this.prefixes = list;
        this.items = list2;
        this.pageToken = str;
    }

    public static ListResult fromJSON(FirebaseStorage firebaseStorage, d dVar) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (dVar.f2163b.containsKey(PREFIXES_KEY)) {
            a aVarE = dVar.e(PREFIXES_KEY);
            for (int i = 0; i < aVarE.a(); i++) {
                String strB = aVarE.b(i);
                if (strB.endsWith("/")) {
                    strB = strB.substring(0, strB.length() - 1);
                }
                arrayList.add(firebaseStorage.getReference(strB));
            }
        }
        if (dVar.f2163b.containsKey("items")) {
            a aVarE2 = dVar.e("items");
            for (int i2 = 0; i2 < aVarE2.a(); i2++) {
                arrayList2.add(firebaseStorage.getReference(aVarE2.a(i2).h("name")));
            }
        }
        return new ListResult(arrayList, arrayList2, dVar.a(PAGE_TOKEN_KEY, (String) null));
    }

    @NonNull
    public List<StorageReference> getItems() {
        return this.items;
    }

    @Nullable
    public String getPageToken() {
        return this.pageToken;
    }

    @NonNull
    public List<StorageReference> getPrefixes() {
        return this.prefixes;
    }
}
