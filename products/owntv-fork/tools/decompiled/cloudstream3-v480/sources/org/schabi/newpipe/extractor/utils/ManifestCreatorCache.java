package org.schabi.newpipe.extractor.utils;

import j$.lang.Iterable;
import j$.util.concurrent.ConcurrentHashMap;
import j$.util.function.Consumer$CC;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes8.dex */
public final class ManifestCreatorCache<K extends Serializable, V extends Serializable> implements Serializable {
    public static final double DEFAULT_CLEAR_FACTOR = 0.75d;
    public static final int DEFAULT_MAXIMUM_SIZE = Integer.MAX_VALUE;
    private int maximumSize = Integer.MAX_VALUE;
    private double clearFactor = 0.75d;
    private final ConcurrentHashMap<K, Pair<Integer, V>> concurrentHashMap = new ConcurrentHashMap<>();

    public boolean containsKey(K k) {
        return this.concurrentHashMap.containsKey(k);
    }

    @Nullable
    public Pair<Integer, V> get(K k) {
        return this.concurrentHashMap.get(k);
    }

    @Nullable
    public V put(K k, V v) {
        if (!this.concurrentHashMap.containsKey(k)) {
            int size = this.concurrentHashMap.size();
            int i = this.maximumSize;
            if (size == i) {
                int iRound = (int) Math.round(((double) i) * this.clearFactor);
                if (iRound == 0) {
                    iRound = 1;
                }
                keepNewestEntries(iRound);
            }
        }
        Pair<Integer, V> pairPut = this.concurrentHashMap.put(k, new Pair<>(Integer.valueOf(this.concurrentHashMap.size()), v));
        if (pairPut == null) {
            return null;
        }
        return (V) pairPut.getSecond();
    }

    public void clear() {
        this.concurrentHashMap.clear();
    }

    public void reset() {
        clear();
        resetClearFactor();
        resetMaximumSize();
    }

    public int size() {
        return this.concurrentHashMap.size();
    }

    public long getMaximumSize() {
        return this.maximumSize;
    }

    public void setMaximumSize(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Invalid maximum size");
        }
        if (i < this.maximumSize && !this.concurrentHashMap.isEmpty()) {
            int iRound = (int) Math.round(((double) i) * this.clearFactor);
            if (iRound == 0) {
                iRound = 1;
            }
            keepNewestEntries(iRound);
        }
        this.maximumSize = i;
    }

    public void resetMaximumSize() {
        this.maximumSize = Integer.MAX_VALUE;
    }

    public double getClearFactor() {
        return this.clearFactor;
    }

    public void setClearFactor(double d) {
        if (d <= 0.0d || d >= 1.0d) {
            throw new IllegalArgumentException("Invalid clear factor");
        }
        this.clearFactor = d;
    }

    public void resetClearFactor() {
        this.clearFactor = 0.75d;
    }

    @Nonnull
    public String toString() {
        return "ManifestCreatorCache[clearFactor=" + this.clearFactor + ", maximumSize=" + this.maximumSize + ", concurrentHashMap=" + this.concurrentHashMap + "]";
    }

    private void keepNewestEntries(int i) {
        final int size = this.concurrentHashMap.size() - i;
        final ArrayList arrayList = new ArrayList();
        Iterable.EL.forEach(this.concurrentHashMap.entrySet(), new Consumer() { // from class: org.schabi.newpipe.extractor.utils.ManifestCreatorCache$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ManifestCreatorCache.lambda$keepNewestEntries$0(size, arrayList, (Map.Entry) obj);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        });
        Iterable.EL.forEach(arrayList, new Consumer() { // from class: org.schabi.newpipe.extractor.utils.ManifestCreatorCache$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m11024x1502412e((Map.Entry) obj);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        });
    }

    static /* synthetic */ void lambda$keepNewestEntries$0(int i, ArrayList arrayList, Map.Entry entry) {
        Pair pair = (Pair) entry.getValue();
        if (((Integer) pair.getFirst()).intValue() < i) {
            arrayList.add(entry);
        } else {
            pair.setFirst(Integer.valueOf(((Integer) pair.getFirst()).intValue() - i));
        }
    }

    /* JADX INFO: renamed from: lambda$keepNewestEntries$1$org-schabi-newpipe-extractor-utils-ManifestCreatorCache, reason: not valid java name */
    /* synthetic */ void m11024x1502412e(Map.Entry entry) {
        this.concurrentHashMap.remove(entry.getKey(), entry.getValue());
    }
}
