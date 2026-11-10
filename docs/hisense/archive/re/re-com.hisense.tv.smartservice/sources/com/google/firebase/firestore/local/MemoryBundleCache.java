package com.google.firebase.firestore.local;

import androidx.annotation.Nullable;
import com.google.firebase.firestore.bundle.BundleMetadata;
import com.google.firebase.firestore.bundle.NamedQuery;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class MemoryBundleCache implements BundleCache {
    public final Map<String, BundleMetadata> bundles = new HashMap();
    public final Map<String, NamedQuery> namedQueries = new HashMap();

    @Override // com.google.firebase.firestore.local.BundleCache
    @Nullable
    public BundleMetadata getBundleMetadata(String str) {
        return this.bundles.get(str);
    }

    @Override // com.google.firebase.firestore.local.BundleCache
    @Nullable
    public NamedQuery getNamedQuery(String str) {
        return this.namedQueries.get(str);
    }

    @Override // com.google.firebase.firestore.local.BundleCache
    public void saveBundleMetadata(BundleMetadata bundleMetadata) {
        this.bundles.put(bundleMetadata.getBundleId(), bundleMetadata);
    }

    @Override // com.google.firebase.firestore.local.BundleCache
    public void saveNamedQuery(NamedQuery namedQuery) {
        this.namedQueries.put(namedQuery.getName(), namedQuery);
    }
}
