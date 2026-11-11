package com.lagradost.cloudstream3.ui;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aJ\u0010\u0000\u001a+\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u00070\u00012\u0019\b\u0002\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007\u001a;\u0010\t\u001a\u00020\u0006*\u00020\n2/\u0010\u000b\u001a+\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u00070\u0001\u001a3\u0010\f\u001a\u00020\u0006*+\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u00070\u0001¨\u0006\r"}, d2 = {"newSharedPool", "Lkotlin/Pair;", "Ljava/util/WeakHashMap;", "Landroid/content/Context;", "Landroidx/recyclerview/widget/RecyclerView$RecycledViewPool;", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "lambda", "setRecycledViewPool", "Landroidx/recyclerview/widget/RecyclerView;", "pool", "clear", "app"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class BaseAdapterKt {
    public static /* synthetic */ Pair newSharedPool$default(Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1() { // from class: com.lagradost.cloudstream3.ui.BaseAdapterKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return BaseAdapterKt.newSharedPool$lambda$0((RecyclerView.RecycledViewPool) obj2);
                }
            };
        }
        return newSharedPool(function1);
    }

    static final Unit newSharedPool$lambda$0(RecyclerView.RecycledViewPool recycledViewPool) {
        Intrinsics.checkNotNullParameter(recycledViewPool, "<this>");
        return Unit.INSTANCE;
    }

    public static final Pair<WeakHashMap<Context, RecyclerView.RecycledViewPool>, Function1<RecyclerView.RecycledViewPool, Unit>> newSharedPool(Function1<? super RecyclerView.RecycledViewPool, Unit> lambda) {
        Intrinsics.checkNotNullParameter(lambda, "lambda");
        return TuplesKt.to(new WeakHashMap(), lambda);
    }

    public static final void setRecycledViewPool(RecyclerView recyclerView, Pair<? extends WeakHashMap<Context, RecyclerView.RecycledViewPool>, ? extends Function1<? super RecyclerView.RecycledViewPool, Unit>> pool) {
        Intrinsics.checkNotNullParameter(recyclerView, "<this>");
        Intrinsics.checkNotNullParameter(pool, "pool");
        Context context = recyclerView.getContext();
        if (context == null) {
            return;
        }
        synchronized (pool.getFirst()) {
            WeakHashMap<Context, RecyclerView.RecycledViewPool> first = pool.getFirst();
            RecyclerView.RecycledViewPool recycledViewPool = first.get(context);
            if (recycledViewPool == null) {
                recycledViewPool = new RecyclerView.RecycledViewPool();
                pool.getSecond().invoke(recycledViewPool);
                first.put(context, recycledViewPool);
            }
            recyclerView.setRecycledViewPool(recycledViewPool);
            Unit unit = Unit.INSTANCE;
        }
    }

    public static final void clear(Pair<? extends WeakHashMap<Context, RecyclerView.RecycledViewPool>, ? extends Function1<? super RecyclerView.RecycledViewPool, Unit>> pair) {
        Intrinsics.checkNotNullParameter(pair, "<this>");
        synchronized (pair.getFirst()) {
            for (RecyclerView.RecycledViewPool recycledViewPool : pair.getFirst().values()) {
                if (recycledViewPool != null) {
                    recycledViewPool.clear();
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }
}
