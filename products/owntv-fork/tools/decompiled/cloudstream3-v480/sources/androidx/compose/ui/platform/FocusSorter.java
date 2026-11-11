package androidx.compose.ui.platform;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.collection.MutableObjectList;
import androidx.collection.MutableScatterMap;
import androidx.collection.ScatterMapKt;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FocusFinderCompat.android.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\u001e\u001a\u00020\u001f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00140!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%¢\u0006\u0002\u0010&R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001d\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00060\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R!\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u00140\u0018j\b\u0012\u0004\u0012\u00020\u0014`\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR!\u0010\u001c\u001a\u0012\u0012\u0004\u0012\u00020\u00140\u0018j\b\u0012\u0004\u0012\u00020\u0014`\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001b¨\u0006'"}, d2 = {"Landroidx/compose/ui/platform/FocusSorter;", "", "<init>", "()V", "rectPool", "Landroidx/collection/MutableObjectList;", "Landroid/graphics/Rect;", "getRectPool", "()Landroidx/collection/MutableObjectList;", "lastPoolIndex", "", "getLastPoolIndex", "()I", "setLastPoolIndex", "(I)V", "rtlMult", "getRtlMult", "setRtlMult", "rectByView", "Landroidx/collection/MutableScatterMap;", "Landroid/view/View;", "getRectByView", "()Landroidx/collection/MutableScatterMap;", "topsComparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "getTopsComparator", "()Ljava/util/Comparator;", "sidesComparator", "getSidesComparator", "sort", "", "views", "", "root", "Landroid/view/ViewGroup;", "isRtl", "", "([Landroid/view/View;Landroid/view/ViewGroup;Z)V", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class FocusSorter {
    private static int lastPoolIndex;
    public static final FocusSorter INSTANCE = new FocusSorter();
    private static final MutableObjectList<Rect> rectPool = new MutableObjectList<>(0, 1, null);
    private static int rtlMult = 1;
    private static final MutableScatterMap<View, Rect> rectByView = ScatterMapKt.mutableScatterMapOf();
    private static final Comparator<View> topsComparator = new Comparator() { // from class: androidx.compose.ui.platform.FocusSorter$$ExternalSyntheticLambda0
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return FocusSorter.topsComparator$lambda$0((View) obj, (View) obj2);
        }
    };
    private static final Comparator<View> sidesComparator = new Comparator() { // from class: androidx.compose.ui.platform.FocusSorter$$ExternalSyntheticLambda1
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return FocusSorter.sidesComparator$lambda$1((View) obj, (View) obj2);
        }
    };

    private FocusSorter() {
    }

    public final MutableObjectList<Rect> getRectPool() {
        return rectPool;
    }

    public final int getLastPoolIndex() {
        return lastPoolIndex;
    }

    public final void setLastPoolIndex(int i) {
        lastPoolIndex = i;
    }

    public final int getRtlMult() {
        return rtlMult;
    }

    public final void setRtlMult(int i) {
        rtlMult = i;
    }

    public final MutableScatterMap<View, Rect> getRectByView() {
        return rectByView;
    }

    public final Comparator<View> getTopsComparator() {
        return topsComparator;
    }

    static final int topsComparator$lambda$0(View view, View view2) {
        if (view == view2) {
            return 0;
        }
        MutableScatterMap<View, Rect> mutableScatterMap = rectByView;
        Rect rect = mutableScatterMap.get(view);
        Intrinsics.checkNotNull(rect);
        Rect rect2 = rect;
        Rect rect3 = mutableScatterMap.get(view2);
        Intrinsics.checkNotNull(rect3);
        Rect rect4 = rect3;
        int i = rect2.top - rect4.top;
        return i == 0 ? rect2.bottom - rect4.bottom : i;
    }

    public final Comparator<View> getSidesComparator() {
        return sidesComparator;
    }

    static final int sidesComparator$lambda$1(View view, View view2) {
        if (view == view2) {
            return 0;
        }
        MutableScatterMap<View, Rect> mutableScatterMap = rectByView;
        Rect rect = mutableScatterMap.get(view);
        Intrinsics.checkNotNull(rect);
        Rect rect2 = rect;
        Rect rect3 = mutableScatterMap.get(view2);
        Intrinsics.checkNotNull(rect3);
        Rect rect4 = rect3;
        int i = rect2.left - rect4.left;
        if (i == 0) {
            return (rect2.right - rect4.right) * rtlMult;
        }
        return i * rtlMult;
    }

    public final void sort(View[] views, ViewGroup root, boolean isRtl) {
        int length = views.length;
        if (length < 2) {
            return;
        }
        int size = length - rectPool.getSize();
        for (int i = 0; i < size; i++) {
            rectPool.add(new Rect());
        }
        for (View view : views) {
            MutableObjectList<Rect> mutableObjectList = rectPool;
            int i2 = lastPoolIndex;
            lastPoolIndex = i2 + 1;
            Rect rect = mutableObjectList.get(i2);
            view.getDrawingRect(rect);
            root.offsetDescendantRectToMyCoords(view, rect);
            rectByView.set(view, rect);
        }
        ArraysKt.sortWith(views, topsComparator);
        Rect rect2 = rectByView.get(views[0]);
        Intrinsics.checkNotNull(rect2);
        int iMax = rect2.bottom;
        rtlMult = isRtl ? -1 : 1;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            Rect rect3 = rectByView.get(views[i4]);
            Intrinsics.checkNotNull(rect3);
            Rect rect4 = rect3;
            if (rect4.top >= iMax) {
                if (i4 - i3 > 1) {
                    ArraysKt.sortWith(views, sidesComparator, i3, i4);
                }
                iMax = rect4.bottom;
                i3 = i4;
            } else {
                iMax = Math.max(iMax, rect4.bottom);
            }
        }
        if (length - i3 > 1) {
            ArraysKt.sortWith(views, sidesComparator, i3, length);
        }
        lastPoolIndex = 0;
        rectByView.clear();
    }
}
