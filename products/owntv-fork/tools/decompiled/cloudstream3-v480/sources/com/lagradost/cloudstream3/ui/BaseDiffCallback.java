package com.lagradost.cloudstream3.ui;

import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.DiffUtil;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B?\u0012\u001a\b\u0002\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u001a\b\u0002\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\b\u0010\tJ\u001d\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0010J\u001d\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0010J\u001f\u0010\u0012\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000e\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0013R#\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR#\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/lagradost/cloudstream3/ui/BaseDiffCallback;", ExifInterface.GPS_DIRECTION_TRUE, "", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "itemSame", "Lkotlin/Function2;", "", "contentSame", "<init>", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;)V", "getItemSame", "()Lkotlin/jvm/functions/Function2;", "getContentSame", "areItemsTheSame", "oldItem", "newItem", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "areContentsTheSame", "getChangePayload", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class BaseDiffCallback<T> extends DiffUtil.ItemCallback<T> {
    private final Function2<T, T, Boolean> contentSame;
    private final Function2<T, T, Boolean> itemSame;

    /* JADX WARN: Multi-variable type inference failed */
    public BaseDiffCallback() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public BaseDiffCallback(Function2<? super T, ? super T, Boolean> itemSame, Function2<? super T, ? super T, Boolean> contentSame) {
        Intrinsics.checkNotNullParameter(itemSame, "itemSame");
        Intrinsics.checkNotNullParameter(contentSame, "contentSame");
        this.itemSame = itemSame;
        this.contentSame = contentSame;
    }

    public /* synthetic */ BaseDiffCallback(Function2 function2, Function2 function3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new Function2() { // from class: com.lagradost.cloudstream3.ui.BaseDiffCallback$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(BaseDiffCallback._init_$lambda$0(obj, obj2));
            }
        } : function2, (i & 2) != 0 ? new Function2() { // from class: com.lagradost.cloudstream3.ui.BaseDiffCallback$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(BaseDiffCallback._init_$lambda$1(obj, obj2));
            }
        } : function3);
    }

    static final boolean _init_$lambda$0(Object a, Object b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return a.hashCode() == b.hashCode();
    }

    public final Function2<T, T, Boolean> getItemSame() {
        return this.itemSame;
    }

    static final boolean _init_$lambda$1(Object a, Object b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return a.hashCode() == b.hashCode();
    }

    public final Function2<T, T, Boolean> getContentSame() {
        return this.contentSame;
    }

    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public boolean areItemsTheSame(T oldItem, T newItem) {
        Intrinsics.checkNotNullParameter(oldItem, "oldItem");
        Intrinsics.checkNotNullParameter(newItem, "newItem");
        return this.itemSame.invoke(oldItem, newItem).booleanValue();
    }

    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public boolean areContentsTheSame(T oldItem, T newItem) {
        Intrinsics.checkNotNullParameter(oldItem, "oldItem");
        Intrinsics.checkNotNullParameter(newItem, "newItem");
        return this.contentSame.invoke(oldItem, newItem).booleanValue();
    }

    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public Object getChangePayload(T oldItem, T newItem) {
        Intrinsics.checkNotNullParameter(oldItem, "oldItem");
        Intrinsics.checkNotNullParameter(newItem, "newItem");
        return new Object();
    }
}
