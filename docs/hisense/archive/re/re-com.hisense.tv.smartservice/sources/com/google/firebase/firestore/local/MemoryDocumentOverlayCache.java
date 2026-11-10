package com.google.firebase.firestore.local;

import android.util.Pair;
import androidx.annotation.Nullable;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.mutation.Mutation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public class MemoryDocumentOverlayCache implements DocumentOverlayCache {
    public final TreeMap<DocumentKey, Pair<Integer, Mutation>> overlays = new TreeMap<>();
    public final Map<Integer, Set<DocumentKey>> overlayByBatchId = new HashMap();

    private void saveOverlay(int i, @Nullable Mutation mutation) {
        if (mutation == null) {
            return;
        }
        Pair<Integer, Mutation> pair = this.overlays.get(mutation.getKey());
        if (pair != null) {
            this.overlayByBatchId.get(pair.first).remove(mutation.getKey());
        }
        this.overlays.put(mutation.getKey(), new Pair<>(Integer.valueOf(i), mutation));
        if (this.overlayByBatchId.get(Integer.valueOf(i)) == null) {
            this.overlayByBatchId.put(Integer.valueOf(i), new HashSet());
        }
        this.overlayByBatchId.get(Integer.valueOf(i)).add(mutation.getKey());
    }

    @Override // com.google.firebase.firestore.local.DocumentOverlayCache
    @Nullable
    public Mutation getOverlay(DocumentKey documentKey) {
        Pair<Integer, Mutation> pair = this.overlays.get(documentKey);
        if (pair != null) {
            return (Mutation) pair.second;
        }
        return null;
    }

    @Override // com.google.firebase.firestore.local.DocumentOverlayCache
    public Map<DocumentKey, Mutation> getOverlays(ResourcePath resourcePath, int i) {
        HashMap map = new HashMap();
        int length = resourcePath.length() + 1;
        for (Map.Entry<DocumentKey, Pair<Integer, Mutation>> entry : this.overlays.tailMap(new DocumentKey(resourcePath.append(""))).entrySet()) {
            DocumentKey key = entry.getKey();
            if (!resourcePath.isPrefixOf(key.getPath())) {
                break;
            }
            if (key.getPath().length() == length) {
                Pair<Integer, Mutation> value = entry.getValue();
                if (((Integer) value.first).intValue() > i) {
                    map.put(entry.getKey(), (Mutation) value.second);
                }
            }
        }
        return map;
    }

    @Override // com.google.firebase.firestore.local.DocumentOverlayCache
    public void removeOverlaysForBatchId(int i) {
        if (this.overlayByBatchId.containsKey(Integer.valueOf(i))) {
            Set<DocumentKey> set = this.overlayByBatchId.get(Integer.valueOf(i));
            this.overlayByBatchId.remove(Integer.valueOf(i));
            Iterator<DocumentKey> it = set.iterator();
            while (it.hasNext()) {
                this.overlays.remove(it.next());
            }
        }
    }

    @Override // com.google.firebase.firestore.local.DocumentOverlayCache
    public void saveOverlays(int i, Map<DocumentKey, Mutation> map) {
        Iterator<Map.Entry<DocumentKey, Mutation>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            saveOverlay(i, it.next().getValue());
        }
    }
}
