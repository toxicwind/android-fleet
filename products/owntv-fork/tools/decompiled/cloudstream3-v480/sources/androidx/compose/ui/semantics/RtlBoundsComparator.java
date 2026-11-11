package androidx.compose.ui.semantics;

import androidx.compose.ui.geometry.Rect;
import androidx.media3.exoplayer.upstream.CmcdData;
import java.util.Comparator;
import kotlin.Metadata;

/* JADX INFO: compiled from: SemanticsSort.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"Landroidx/compose/ui/semantics/RtlBoundsComparator;", "Ljava/util/Comparator;", "Landroidx/compose/ui/semantics/SemanticsNode;", "Lkotlin/Comparator;", "<init>", "()V", "compare", "", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class RtlBoundsComparator implements Comparator<SemanticsNode> {
    public static final RtlBoundsComparator INSTANCE = new RtlBoundsComparator();

    private RtlBoundsComparator() {
    }

    @Override // java.util.Comparator
    public int compare(SemanticsNode a, SemanticsNode b) {
        Rect boundsInWindow = a.getBoundsInWindow();
        Rect boundsInWindow2 = b.getBoundsInWindow();
        int iCompare = Float.compare(boundsInWindow2.getRight(), boundsInWindow.getRight());
        if (iCompare != 0) {
            return iCompare;
        }
        int iCompare2 = Float.compare(boundsInWindow.getTop(), boundsInWindow2.getTop());
        if (iCompare2 != 0) {
            return iCompare2;
        }
        int iCompare3 = Float.compare(boundsInWindow.getBottom(), boundsInWindow2.getBottom());
        return iCompare3 != 0 ? iCompare3 : Float.compare(boundsInWindow2.getLeft(), boundsInWindow.getLeft());
    }
}
