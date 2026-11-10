package com.google.firebase.firestore.core;

import com.google.firebase.firestore.util.Assert;

/* JADX INFO: loaded from: classes.dex */
public class TargetIdGenerator {
    public static final int QUERY_CACHE_ID = 0;
    public static final int RESERVED_BITS = 1;
    public static final int SYNC_ENGINE_ID = 1;
    public int generatorId;
    public int nextId;

    public TargetIdGenerator(int i, int i2) {
        Assert.hardAssert((i & 1) == i, "Generator ID %d contains more than %d reserved bits", Integer.valueOf(i), 1);
        this.generatorId = i;
        seek(i2);
    }

    public static TargetIdGenerator forSyncEngine() {
        return new TargetIdGenerator(1, 1);
    }

    public static TargetIdGenerator forTargetCache(int i) {
        TargetIdGenerator targetIdGenerator = new TargetIdGenerator(0, i);
        targetIdGenerator.nextId();
        return targetIdGenerator;
    }

    private void seek(int i) {
        Assert.hardAssert((i & 1) == this.generatorId, "Cannot supply target ID from different generator ID", new Object[0]);
        this.nextId = i;
    }

    public int nextId() {
        int i = this.nextId;
        this.nextId = i + 2;
        return i;
    }
}
