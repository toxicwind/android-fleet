package com.lagradost.cloudstream3.ui.download.queue;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.lagradost.cloudstream3.utils.downloader.DownloadObjects;
import com.lagradost.cloudstream3.utils.downloader.DownloadQueueManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloadQueueAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J \u0010\f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0007H\u0016J\b\u0010\u0013\u001a\u00020\rH\u0016J\b\u0010\u0014\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/lagradost/cloudstream3/ui/download/queue/DragAndDropTouchHelperCallback;", "Landroidx/recyclerview/widget/ItemTouchHelper$Callback;", "adapter", "Lcom/lagradost/cloudstream3/ui/download/queue/DownloadQueueAdapter;", "<init>", "(Lcom/lagradost/cloudstream3/ui/download/queue/DownloadQueueAdapter;)V", "getMovementFlags", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "viewHolder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "onMove", "", "source", TypedValues.AttributesType.S_TARGET, "onSwiped", "", "direction", "isLongPressDragEnabled", "isItemViewSwipeEnabled", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class DragAndDropTouchHelperCallback extends ItemTouchHelper.Callback {
    private final DownloadQueueAdapter adapter;

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
    }

    public DragAndDropTouchHelperCallback(DownloadQueueAdapter adapter) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        this.adapter = adapter;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0025  */
    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        boolean z;
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        DownloadAdapterItem item = this.adapter.getItem(viewHolder.getAbsoluteAdapterPosition());
        DownloadObjects.DownloadQueueWrapper item2 = item.getItem();
        if (item2 != null) {
            z = item2.isCurrentlyDownloading();
        }
        return ItemTouchHelper.Callback.makeMovementFlags((item.getIsSeparator() || z) ? 0 : 3, 0);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder source, RecyclerView.ViewHolder target) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(target, "target");
        int absoluteAdapterPosition = source.getAbsoluteAdapterPosition();
        int absoluteAdapterPosition2 = target.getAbsoluteAdapterPosition();
        int currentDownloads = this.adapter.getCurrentDownloads();
        if (currentDownloads < absoluteAdapterPosition2) {
            absoluteAdapterPosition2 -= currentDownloads;
        }
        if (Intrinsics.areEqual(source.itemView.getTag(), DownloadQueueAdapter.INSTANCE.getDOWNLOAD_SEPARATOR_TAG())) {
            return false;
        }
        DownloadObjects.DownloadQueueWrapper item = this.adapter.getItem(absoluteAdapterPosition).getItem();
        if (item != null) {
            DownloadQueueManager.INSTANCE.reorderItem(item, absoluteAdapterPosition2 - 1);
        }
        return true;
    }
}
