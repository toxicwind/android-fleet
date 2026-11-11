package com.lagradost.cloudstream3.utils;

import androidx.exifinterface.media.ExifInterface;
import io.ktor.http.ContentDisposition;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Event.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u001d\u0010\r\u001a\u00020\b2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u0007H\u0086\u0002J\u001d\u0010\u000f\u001a\u00020\b2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u0007H\u0086\u0002J\u0016\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0002\u0010\u0012R \u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u0013"}, d2 = {"Lcom/lagradost/cloudstream3/utils/Event;", ExifInterface.GPS_DIRECTION_TRUE, "", "<init>", "()V", "observers", "", "Lkotlin/Function1;", "", ContentDisposition.Parameters.Size, "", "getSize", "()I", "plusAssign", "observer", "minusAssign", "invoke", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/Object;)V", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Event<T> {
    private final Set<Function1<T, Unit>> observers = new LinkedHashSet();

    public final int getSize() {
        return this.observers.size();
    }

    public final void plusAssign(Function1<? super T, Unit> observer) {
        Intrinsics.checkNotNullParameter(observer, "observer");
        synchronized (this.observers) {
            this.observers.add(observer);
        }
    }

    public final void minusAssign(Function1<? super T, Unit> observer) {
        Intrinsics.checkNotNullParameter(observer, "observer");
        synchronized (this.observers) {
            this.observers.remove(observer);
        }
    }

    public final void invoke(T value) {
        synchronized (this.observers) {
            Iterator<Function1<T, Unit>> it = this.observers.iterator();
            while (it.hasNext()) {
                it.next().invoke(value);
            }
            Unit unit = Unit.INSTANCE;
        }
    }
}
