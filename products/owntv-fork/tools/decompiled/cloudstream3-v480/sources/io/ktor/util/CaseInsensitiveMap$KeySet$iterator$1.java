package io.ktor.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableIterator;

/* JADX INFO: compiled from: CaseInsensitiveMap.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0010)\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u000f\u0010\u0004\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010\u0007\u001a\u00020\u0006H\u0096\u0082\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0011\u0010\t\u001a\u00020\u0002H\u0096\u0082\u0004¢\u0006\u0004\b\t\u0010\nJ\u0011\u0010\u000b\u001a\u00020\u0003H\u0096\u0080\u0004¢\u0006\u0004\b\u000b\u0010\u0005R\u0016\u0010\r\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"io/ktor/util/CaseInsensitiveMap$KeySet$iterator$1", "", "", "", "advance", "()V", "", "hasNext", "()Z", "next", "()Ljava/lang/String;", "remove", "", "orderIndex", "I", "lastKey", "Ljava/lang/String;", "ktor-utils"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CaseInsensitiveMap$KeySet$iterator$1 implements Iterator<String>, KMutableIterator {
    private String lastKey;
    private int orderIndex;
    final /* synthetic */ CaseInsensitiveMap<Value> this$0;

    CaseInsensitiveMap$KeySet$iterator$1(CaseInsensitiveMap<Value> caseInsensitiveMap) {
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
    public String next() {
        if (hasNext()) {
            String str = ((CaseInsensitiveMap) this.this$0).keyStorage[((CaseInsensitiveMap) this.this$0).insertionOrder[this.orderIndex]];
            Intrinsics.checkNotNull(str);
            this.lastKey = str;
            this.orderIndex++;
            advance();
            String str2 = this.lastKey;
            Intrinsics.checkNotNull(str2);
            return str2;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public void remove() {
        Object obj = this.lastKey;
        if (obj == null) {
            throw new IllegalStateException("next() must be called before remove()");
        }
        this.this$0.remove(obj);
        this.lastKey = null;
    }
}
