package io.ktor.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableIterator;

/* JADX INFO: Add missing generic type declarations: [Value] */
/* JADX INFO: compiled from: CaseInsensitiveMap.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0010)\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0011\u0010\u0006\u001a\u00020\u0005H\u0096\u0082\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0011\u0010\b\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0004\b\b\u0010\tJ\u0011\u0010\n\u001a\u00020\u0002H\u0096\u0080\u0004¢\u0006\u0004\b\n\u0010\u0004R\u0016\u0010\f\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\f\u0010\rR\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"io/ktor/util/CaseInsensitiveMap$ValueCollection$iterator$1", "", "", "advance", "()V", "", "hasNext", "()Z", "next", "()Ljava/lang/Object;", "remove", "", "orderIndex", "I", "", "lastKey", "Ljava/lang/String;", "ktor-utils"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CaseInsensitiveMap$ValueCollection$iterator$1<Value> implements Iterator<Value>, KMutableIterator {
    private String lastKey;
    private int orderIndex;
    final /* synthetic */ CaseInsensitiveMap<Value> this$0;

    CaseInsensitiveMap$ValueCollection$iterator$1(CaseInsensitiveMap<Value> caseInsensitiveMap) {
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
    public Value next() {
        if (hasNext()) {
            int i = ((CaseInsensitiveMap) this.this$0).insertionOrder[this.orderIndex];
            String str = ((CaseInsensitiveMap) this.this$0).keyStorage[i];
            Intrinsics.checkNotNull(str);
            this.lastKey = str;
            Value value = (Value) ((CaseInsensitiveMap) this.this$0).valueStorage[i];
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type Value of io.ktor.util.CaseInsensitiveMap");
            this.orderIndex++;
            advance();
            return value;
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
