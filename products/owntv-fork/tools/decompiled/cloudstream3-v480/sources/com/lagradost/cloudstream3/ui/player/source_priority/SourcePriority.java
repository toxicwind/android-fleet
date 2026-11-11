package com.lagradost.cloudstream3.ui.player.source_priority;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PriorityAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\u0013\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J2\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001¢\u0006\u0002\u0010\u0017J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0002HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u0005HÖ\u0081\u0004R\u0013\u0010\u0003\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u001d"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/source_priority/SourcePriority;", ExifInterface.GPS_DIRECTION_TRUE, "", "data", "name", "", "priority", "", "<init>", "(Ljava/lang/Object;Ljava/lang/String;I)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getName", "()Ljava/lang/String;", "getPriority", "()I", "setPriority", "(I)V", "component1", "component2", "component3", "copy", "(Ljava/lang/Object;Ljava/lang/String;I)Lcom/lagradost/cloudstream3/ui/player/source_priority/SourcePriority;", "equals", "", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SourcePriority<T> {
    private final T data;
    private final String name;
    private int priority;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SourcePriority copy$default(SourcePriority sourcePriority, Object obj, String str, int i, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            obj = sourcePriority.data;
        }
        if ((i2 & 2) != 0) {
            str = sourcePriority.name;
        }
        if ((i2 & 4) != 0) {
            i = sourcePriority.priority;
        }
        return sourcePriority.copy(obj, str, i);
    }

    public final T component1() {
        return this.data;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getPriority() {
        return this.priority;
    }

    public final SourcePriority<T> copy(T data, String name, int priority) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new SourcePriority<>(data, name, priority);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SourcePriority)) {
            return false;
        }
        SourcePriority sourcePriority = (SourcePriority) other;
        return Intrinsics.areEqual(this.data, sourcePriority.data) && Intrinsics.areEqual(this.name, sourcePriority.name) && this.priority == sourcePriority.priority;
    }

    public int hashCode() {
        T t = this.data;
        return ((((t == null ? 0 : t.hashCode()) * 31) + this.name.hashCode()) * 31) + this.priority;
    }

    public String toString() {
        return "SourcePriority(data=" + this.data + ", name=" + this.name + ", priority=" + this.priority + ')';
    }

    public SourcePriority(T t, String name, int i) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.data = t;
        this.name = name;
        this.priority = i;
    }

    public final T getData() {
        return this.data;
    }

    public final String getName() {
        return this.name;
    }

    public final int getPriority() {
        return this.priority;
    }

    public final void setPriority(int i) {
        this.priority = i;
    }
}
