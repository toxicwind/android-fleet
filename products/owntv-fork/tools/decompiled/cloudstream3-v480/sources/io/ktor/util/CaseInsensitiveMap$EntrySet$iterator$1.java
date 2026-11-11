package io.ktor.util;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableIterator;

/* JADX INFO: Add missing generic type declarations: [Value] */
/* JADX INFO: compiled from: CaseInsensitiveMap.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0010)\n\u0002\u0010'\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00028\u00000\u00020\u0001J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0011\u0010\b\u001a\u00020\u0007H\u0096\u0082\u0004¢\u0006\u0004\b\b\u0010\tJ\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00028\u00000\u0002H\u0096\u0082\u0004¢\u0006\u0004\b\n\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u0004H\u0096\u0080\u0004¢\u0006\u0004\b\f\u0010\u0006R\u0016\u0010\u000e\u001a\u00020\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"io/ktor/util/CaseInsensitiveMap$EntrySet$iterator$1", "", "", "", "", "advance", "()V", "", "hasNext", "()Z", "next", "()Ljava/util/Map$Entry;", "remove", "", "orderIndex", "I", "lastKey", "Ljava/lang/String;", "ktor-utils"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CaseInsensitiveMap$EntrySet$iterator$1<Value> implements Iterator<Map.Entry<String, Value>>, KMutableIterator {
    private String lastKey;
    private int orderIndex;
    final /* synthetic */ CaseInsensitiveMap<Value> this$0;

    CaseInsensitiveMap$EntrySet$iterator$1(CaseInsensitiveMap<Value> caseInsensitiveMap) {
        this.this$0 = caseInsensitiveMap;
        advance();
    }

    private final void advance() {
        while (this.orderIndex < ((CaseInsensitiveMap) this.this$0).insertionCount) {
            int i = ((CaseInsensitiveMap) this.this$0).insertionOrder[this.orderIndex];
            if (i >= 0 && ((CaseInsensitiveMap) this.this$0).keyStorage[i] != null) {
                return;
            } else {
                this.orderIndex++;
            }
        }
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.orderIndex < ((CaseInsensitiveMap) this.this$0).insertionCount;
    }

    @Override // java.util.Iterator
    public Map.Entry<String, Value> next() {
        if (hasNext()) {
            int i = ((CaseInsensitiveMap) this.this$0).insertionOrder[this.orderIndex];
            String str = ((CaseInsensitiveMap) this.this$0).keyStorage[i];
            Intrinsics.checkNotNull(str);
            this.lastKey = str;
            CaseInsensitiveMap<Value> caseInsensitiveMap = this.this$0;
            String str2 = this.lastKey;
            Intrinsics.checkNotNull(str2);
            Object obj = ((CaseInsensitiveMap) this.this$0).valueStorage[i];
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type Value of io.ktor.util.CaseInsensitiveMap");
            CaseInsensitiveMap.MapEntry mapEntry = new CaseInsensitiveMap.MapEntry(caseInsensitiveMap, str2, obj);
            this.orderIndex++;
            advance();
            return mapEntry;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public void remove() {
        String str = this.lastKey;
        if (str == null) {
            throw new IllegalStateException("next() must be called before remove()");
        }
        this.this$0.remove((Object) str);
        this.lastKey = null;
    }
}
