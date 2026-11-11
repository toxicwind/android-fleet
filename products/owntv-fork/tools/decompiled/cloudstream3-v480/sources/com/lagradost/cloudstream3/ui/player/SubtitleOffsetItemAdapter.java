package com.lagradost.cloudstream3.ui.player;

import android.animation.ObjectAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.viewbinding.ViewBinding;
import com.lagradost.cloudstream3.databinding.SubtitleOffsetItemBinding;
import com.lagradost.cloudstream3.ui.BaseDiffCallback;
import com.lagradost.cloudstream3.ui.NoStateAdapter;
import com.lagradost.cloudstream3.ui.ViewHolderState;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: SubtitleOffsetItemAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\b\u0010\tJ\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J&\u0010\u0011\u001a\u00020\u00072\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u000e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0004J\u000e\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0019"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/SubtitleOffsetItemAdapter;", "Lcom/lagradost/cloudstream3/ui/NoStateAdapter;", "Lcom/lagradost/cloudstream3/ui/player/SubtitleCue;", "currentTimeMs", "", "clickCallback", "Lkotlin/Function1;", "", "<init>", "(JLkotlin/jvm/functions/Function1;)V", "getClickCallback", "()Lkotlin/jvm/functions/Function1;", "onCreateContent", "Lcom/lagradost/cloudstream3/ui/ViewHolderState;", "", "parent", "Landroid/view/ViewGroup;", "onBindContent", "holder", "item", "position", "", "getLatestActiveItem", "updateTime", "timeMs", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SubtitleOffsetItemAdapter extends NoStateAdapter<SubtitleCue> {
    private final Function1<SubtitleCue, Unit> clickCallback;
    private long currentTimeMs;

    public final Function1<SubtitleCue, Unit> getClickCallback() {
        return this.clickCallback;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SubtitleOffsetItemAdapter(long j, Function1<? super SubtitleCue, Unit> clickCallback) {
        super(new BaseDiffCallback(new Function2() { // from class: com.lagradost.cloudstream3.ui.player.SubtitleOffsetItemAdapter$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(SubtitleOffsetItemAdapter._init_$lambda$0((SubtitleCue) obj, (SubtitleCue) obj2));
            }
        }, null, 2, null));
        Intrinsics.checkNotNullParameter(clickCallback, "clickCallback");
        this.currentTimeMs = j;
        this.clickCallback = clickCallback;
    }

    static final boolean _init_$lambda$0(SubtitleCue a, SubtitleCue b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return a.getStartTimeMs() == b.getStartTimeMs();
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public ViewHolderState<Object> onCreateContent(ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        SubtitleOffsetItemBinding subtitleOffsetItemBindingInflate = SubtitleOffsetItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(subtitleOffsetItemBindingInflate, "inflate(...)");
        return new ViewHolderState<>(subtitleOffsetItemBindingInflate);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onBindContent(ViewHolderState<Object> holder, final SubtitleCue item, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        ViewBinding view = holder.getView();
        SubtitleOffsetItemBinding subtitleOffsetItemBinding = view instanceof SubtitleOffsetItemBinding ? (SubtitleOffsetItemBinding) view : null;
        if (subtitleOffsetItemBinding == null) {
            return;
        }
        subtitleOffsetItemBinding.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.SubtitleOffsetItemAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.clickCallback.invoke(item);
            }
        });
        subtitleOffsetItemBinding.subtitleText.setText(CollectionsKt.joinToString$default(item.getText(), "\n", null, null, 0, null, null, 62, null));
        long j = this.currentTimeMs;
        long startTimeMs = item.getStartTimeMs();
        long endTimeMs = item.getEndTimeMs();
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(subtitleOffsetItemBinding.subtitleText, "alpha", subtitleOffsetItemBinding.subtitleText.getAlpha(), j >= startTimeMs ? 1.0f : 0.5f);
        objectAnimatorOfFloat.setInterpolator(new DecelerateInterpolator());
        objectAnimatorOfFloat.start();
        boolean z = startTimeMs <= j && j < endTimeMs;
        ContentLoadingProgressBar subtitleProgress = subtitleOffsetItemBinding.subtitleProgress;
        Intrinsics.checkNotNullExpressionValue(subtitleProgress, "subtitleProgress");
        subtitleProgress.setVisibility(!z ? 4 : 0);
        if (z) {
            ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(subtitleOffsetItemBinding.subtitleProgress, "progress", subtitleOffsetItemBinding.subtitleProgress.getProgress(), MathKt.roundToInt(((j - startTimeMs) * 1000.0f) / item.getDurationMs()));
            objectAnimatorOfInt.setInterpolator(new DecelerateInterpolator());
            objectAnimatorOfInt.start();
            return;
        }
        subtitleOffsetItemBinding.subtitleProgress.setProgress(0);
    }

    public final int getLatestActiveItem(long position) {
        Object obj = null;
        for (Object obj2 : CollectionsKt.withIndex(getImmutableCurrentList())) {
            if (position >= ((SubtitleCue) ((IndexedValue) obj2).getValue()).getStartTimeMs()) {
                obj = obj2;
            }
        }
        IndexedValue indexedValue = (IndexedValue) obj;
        if (indexedValue != null) {
            return indexedValue.getIndex();
        }
        return 0;
    }

    public final void updateTime(long timeMs) {
        long j = this.currentTimeMs;
        this.currentTimeMs = timeMs;
        long jMin = Math.min(j, timeMs);
        long jMax = Math.max(j, timeMs);
        Iterable iterableWithIndex = CollectionsKt.withIndex(getImmutableCurrentList());
        ArrayList arrayList = new ArrayList();
        for (Object obj : iterableWithIndex) {
            IndexedValue indexedValue = (IndexedValue) obj;
            long durationMs = jMin - ((SubtitleCue) indexedValue.getValue()).getDurationMs();
            long durationMs2 = ((SubtitleCue) indexedValue.getValue()).getDurationMs() + jMax;
            long startTimeMs = ((SubtitleCue) indexedValue.getValue()).getStartTimeMs();
            if (durationMs <= startTimeMs && startTimeMs <= durationMs2) {
                arrayList.add(obj);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            notifyItemChanged(((IndexedValue) it.next()).getIndex());
        }
    }
}
