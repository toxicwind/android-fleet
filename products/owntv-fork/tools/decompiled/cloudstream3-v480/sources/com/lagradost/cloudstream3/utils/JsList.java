package com.lagradost.cloudstream3.utils;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JsInterpreter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0010\u001a\u00020\tH\u0086\u0002J\u001b\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0001H\u0086\u0002R\u0019\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R$\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0013"}, d2 = {"Lcom/lagradost/cloudstream3/utils/JsList;", "", "elements", "", "<init>", "(Ljava/util/List;)V", "getElements", "()Ljava/util/List;", "v", "", "length", "getLength", "()I", "setLength", "(I)V", "get", "idx", "set", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class JsList {
    private final List<Object> elements;

    /* JADX WARN: Multi-variable type inference failed */
    public JsList() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public JsList(List<Object> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        this.elements = elements;
    }

    public /* synthetic */ JsList(ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new ArrayList() : arrayList);
    }

    public final List<Object> getElements() {
        return this.elements;
    }

    public final int getLength() {
        return this.elements.size();
    }

    public final void setLength(int i) {
        while (this.elements.size() < i) {
            this.elements.add(Unit.INSTANCE);
        }
        while (this.elements.size() > i) {
            List<Object> list = this.elements;
            list.remove(list.size() - 1);
        }
    }

    public final Object get(int idx) {
        List<Object> list = this.elements;
        return (idx < 0 || idx >= list.size()) ? Unit.INSTANCE : list.get(idx);
    }

    public final void set(int idx, Object v) {
        while (true) {
            int size = this.elements.size();
            List<Object> list = this.elements;
            if (size > idx) {
                list.set(idx, v);
                return;
            }
            list.add(Unit.INSTANCE);
        }
    }
}
