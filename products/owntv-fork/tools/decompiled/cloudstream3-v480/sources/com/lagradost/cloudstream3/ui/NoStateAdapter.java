package com.lagradost.cloudstream3.ui;

import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.DiffUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00020\u0003B\u0017\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/lagradost/cloudstream3/ui/NoStateAdapter;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lcom/lagradost/cloudstream3/ui/BaseAdapter;", "diffCallback", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "<init>", "(Landroidx/recyclerview/widget/DiffUtil$ItemCallback;)V", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class NoStateAdapter<T> extends BaseAdapter<T, Object> {
    /* JADX WARN: Multi-variable type inference failed */
    public NoStateAdapter() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NoStateAdapter(DiffUtil.ItemCallback<T> diffCallback) {
        super(0, diffCallback);
        Intrinsics.checkNotNullParameter(diffCallback, "diffCallback");
    }

    public /* synthetic */ NoStateAdapter(BaseDiffCallback baseDiffCallback, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new BaseDiffCallback(null, null, 3, null) : baseDiffCallback);
    }
}
