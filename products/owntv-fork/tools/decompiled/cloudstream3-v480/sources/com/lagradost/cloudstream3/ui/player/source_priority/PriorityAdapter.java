package com.lagradost.cloudstream3.ui.player.source_priority;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.exifinterface.media.ExifInterface;
import androidx.viewbinding.ViewBinding;
import com.lagradost.cloudstream3.databinding.PlayerPrioritizeItemBinding;
import com.lagradost.cloudstream3.ui.NoStateAdapter;
import com.lagradost.cloudstream3.ui.ViewHolderState;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PriorityAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nH\u0016J,\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¨\u0006\u0011"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/source_priority/PriorityAdapter;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/lagradost/cloudstream3/ui/NoStateAdapter;", "Lcom/lagradost/cloudstream3/ui/player/source_priority/SourcePriority;", "<init>", "()V", "onCreateContent", "Lcom/lagradost/cloudstream3/ui/ViewHolderState;", "", "parent", "Landroid/view/ViewGroup;", "onBindContent", "", "holder", "item", "position", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class PriorityAdapter<T> extends NoStateAdapter<SourcePriority<T>> {
    public PriorityAdapter() {
        super(null, 1, null);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public ViewHolderState<Object> onCreateContent(ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        PlayerPrioritizeItemBinding playerPrioritizeItemBindingInflate = PlayerPrioritizeItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(playerPrioritizeItemBindingInflate, "inflate(...)");
        return new ViewHolderState<>(playerPrioritizeItemBindingInflate);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onBindContent(ViewHolderState<Object> holder, final SourcePriority<T> item, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        ViewBinding view = holder.getView();
        final PlayerPrioritizeItemBinding playerPrioritizeItemBinding = view instanceof PlayerPrioritizeItemBinding ? (PlayerPrioritizeItemBinding) view : null;
        if (playerPrioritizeItemBinding == null) {
            return;
        }
        playerPrioritizeItemBinding.priorityText.setText(item.getName());
        onBindContent$updatePriority(playerPrioritizeItemBinding, item);
        playerPrioritizeItemBinding.addButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.PriorityAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                PriorityAdapter.onBindContent$lambda$0(item, playerPrioritizeItemBinding, view2);
            }
        });
        playerPrioritizeItemBinding.subtractButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.PriorityAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                PriorityAdapter.onBindContent$lambda$1(item, playerPrioritizeItemBinding, view2);
            }
        });
    }

    private static final <T> void onBindContent$updatePriority(PlayerPrioritizeItemBinding playerPrioritizeItemBinding, SourcePriority<T> sourcePriority) {
        playerPrioritizeItemBinding.priorityNumber.setText(String.valueOf(sourcePriority.getPriority()));
    }

    static final void onBindContent$lambda$0(SourcePriority sourcePriority, PlayerPrioritizeItemBinding playerPrioritizeItemBinding, View view) {
        sourcePriority.setPriority(sourcePriority.getPriority() + 1);
        onBindContent$updatePriority(playerPrioritizeItemBinding, sourcePriority);
    }

    static final void onBindContent$lambda$1(SourcePriority sourcePriority, PlayerPrioritizeItemBinding playerPrioritizeItemBinding, View view) {
        sourcePriority.setPriority(sourcePriority.getPriority() - 1);
        onBindContent$updatePriority(playerPrioritizeItemBinding, sourcePriority);
    }
}
