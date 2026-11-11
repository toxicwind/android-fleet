package androidx.compose.ui.semantics;

import androidx.collection.MutableObjectList;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: SemanticsInfo.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0001H\u0000\u001a\u000e\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0001H\u0000\u001a\u000e\u0010\u0003\u001a\u0004\u0018\u00010\u0004*\u00020\u0001H\u0000¨\u0006\u0005"}, d2 = {"nearestParentThatHasSemantics", "Landroidx/compose/ui/semantics/SemanticsInfo;", "findMergingSemanticsParent", "mergedSemanticsConfiguration", "Landroidx/compose/ui/semantics/SemanticsConfiguration;", "ui_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SemanticsInfoKt {
    public static final SemanticsInfo nearestParentThatHasSemantics(SemanticsInfo semanticsInfo) {
        for (SemanticsInfo parentInfo = semanticsInfo.getParentInfo(); parentInfo != null; parentInfo = parentInfo.getParentInfo()) {
            if (parentInfo.getSemanticsConfiguration() != null) {
                return parentInfo;
            }
        }
        return null;
    }

    public static final SemanticsInfo findMergingSemanticsParent(SemanticsInfo semanticsInfo) {
        for (SemanticsInfo parentInfo = semanticsInfo.getParentInfo(); parentInfo != null; parentInfo = parentInfo.getParentInfo()) {
            SemanticsConfiguration semanticsConfiguration = parentInfo.getSemanticsConfiguration();
            if (semanticsConfiguration != null && semanticsConfiguration.getIsMergingSemanticsOfDescendants()) {
                return parentInfo;
            }
        }
        return null;
    }

    public static final SemanticsConfiguration mergedSemanticsConfiguration(SemanticsInfo semanticsInfo) {
        SemanticsConfiguration semanticsConfiguration = semanticsInfo.getSemanticsConfiguration();
        if (semanticsConfiguration != null && semanticsConfiguration.getIsMergingSemanticsOfDescendants() && !semanticsConfiguration.getIsClearingSemantics()) {
            semanticsConfiguration = semanticsConfiguration.copy();
            MutableObjectList mutableObjectList = new MutableObjectList(semanticsInfo.getChildrenInfo().size());
            mutableObjectList.addAll((List) semanticsInfo.getChildrenInfo());
            while (mutableObjectList.isNotEmpty()) {
                SemanticsInfo semanticsInfo2 = (SemanticsInfo) mutableObjectList.removeAt(mutableObjectList._size - 1);
                SemanticsConfiguration semanticsConfiguration2 = semanticsInfo2.getSemanticsConfiguration();
                if (semanticsConfiguration2 != null && !semanticsConfiguration2.getIsMergingSemanticsOfDescendants()) {
                    semanticsConfiguration.mergeChild$ui_release(semanticsConfiguration2);
                    if (!semanticsConfiguration2.getIsClearingSemantics()) {
                        mutableObjectList.addAll((List) semanticsInfo2.getChildrenInfo());
                    }
                }
            }
        }
        return semanticsConfiguration;
    }
}
