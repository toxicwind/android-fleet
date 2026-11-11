package com.lagradost.cloudstream3.ui.download.queue;

import androidx.recyclerview.widget.ItemTouchHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloadQueueAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/lagradost/cloudstream3/ui/download/queue/DragAndDropTouchHelper;", "Landroidx/recyclerview/widget/ItemTouchHelper;", "adapter", "Lcom/lagradost/cloudstream3/ui/download/queue/DownloadQueueAdapter;", "<init>", "(Lcom/lagradost/cloudstream3/ui/download/queue/DownloadQueueAdapter;)V", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DragAndDropTouchHelper extends ItemTouchHelper {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DragAndDropTouchHelper(DownloadQueueAdapter adapter) {
        super(new DragAndDropTouchHelperCallback(adapter));
        Intrinsics.checkNotNullParameter(adapter, "adapter");
    }
}
