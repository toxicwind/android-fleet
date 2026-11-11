package com.lagradost.cloudstream3.utils;

import io.ktor.http.ContentDisposition;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Event.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0006H\u0086\u0002J\u0011\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0006H\u0086\u0002J\t\u0010\u000f\u001a\u00020\fH\u0086\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0010"}, d2 = {"Lcom/lagradost/cloudstream3/utils/EmptyEvent;", "", "<init>", "()V", "observers", "", "Ljava/lang/Runnable;", ContentDisposition.Parameters.Size, "", "getSize", "()I", "plusAssign", "", "observer", "minusAssign", "invoke", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class EmptyEvent {
    private final Set<Runnable> observers = new LinkedHashSet();

    public final int getSize() {
        return this.observers.size();
    }

    public final void plusAssign(Runnable observer) {
        Intrinsics.checkNotNullParameter(observer, "observer");
        synchronized (this.observers) {
            this.observers.add(observer);
        }
    }

    public final void minusAssign(Runnable observer) {
        Intrinsics.checkNotNullParameter(observer, "observer");
        synchronized (this.observers) {
            this.observers.remove(observer);
        }
    }

    public final void invoke() {
        synchronized (this.observers) {
            Iterator<Runnable> it = this.observers.iterator();
            while (it.hasNext()) {
                it.next().run();
            }
            Unit unit = Unit.INSTANCE;
        }
    }
}
