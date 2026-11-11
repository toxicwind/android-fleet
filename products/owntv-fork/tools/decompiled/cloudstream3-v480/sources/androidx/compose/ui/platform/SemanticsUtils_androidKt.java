package androidx.compose.ui.platform;

import android.view.View;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.semantics.AccessibilityAction;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsConfigurationKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.viewinterop.AndroidViewHolder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: SemanticsUtils.android.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0017\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0002\u001a\u00020\u0003H\u0001¢\u0006\u0002\u0010\u0006\u001a\u001c\u0010\u0007\u001a\u0004\u0018\u00010\b*\b\u0012\u0004\u0012\u00020\b0\t2\u0006\u0010\n\u001a\u00020\u000bH\u0000\u001a\u0015\u0010\f\u001a\u0004\u0018\u00010\r*\u00020\u000eH\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u00020\u00132\u0006\u0010\n\u001a\u00020\u000bH\u0000¨\u0006\u0014"}, d2 = {"getTextLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "configuration", "Landroidx/compose/ui/semantics/SemanticsConfiguration;", "getScrollViewportLength", "", "(Landroidx/compose/ui/semantics/SemanticsConfiguration;)Ljava/lang/Float;", "findById", "Landroidx/compose/ui/platform/ScrollObservationScope;", "", "id", "", "toLegacyClassName", "", "Landroidx/compose/ui/semantics/Role;", "toLegacyClassName-V4PA4sw", "(I)Ljava/lang/String;", "semanticsIdToView", "Landroid/view/View;", "Landroidx/compose/ui/platform/AndroidViewsHandler;", "ui_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SemanticsUtils_androidKt {
    public static final TextLayoutResult getTextLayoutResult(SemanticsConfiguration semanticsConfiguration) {
        Function1 function1;
        ArrayList arrayList = new ArrayList();
        AccessibilityAction accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, SemanticsActions.INSTANCE.getGetTextLayoutResult());
        if (accessibilityAction == null || (function1 = (Function1) accessibilityAction.getAction()) == null || !((Boolean) function1.invoke(arrayList)).booleanValue()) {
            return null;
        }
        return (TextLayoutResult) arrayList.get(0);
    }

    public static final Float getScrollViewportLength(SemanticsConfiguration semanticsConfiguration) {
        Function1 function1;
        ArrayList arrayList = new ArrayList();
        AccessibilityAction accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, SemanticsActions.INSTANCE.getGetScrollViewportLength());
        if (accessibilityAction == null || (function1 = (Function1) accessibilityAction.getAction()) == null || !((Boolean) function1.invoke(arrayList)).booleanValue()) {
            return null;
        }
        return (Float) arrayList.get(0);
    }

    public static final ScrollObservationScope findById(List<ScrollObservationScope> list, int i) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (list.get(i2).getSemanticsNodeId() == i) {
                return list.get(i2);
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: toLegacyClassName-V4PA4sw, reason: not valid java name */
    public static final String m6089toLegacyClassNameV4PA4sw(int i) {
        if (Role.m6111equalsimpl0(i, Role.INSTANCE.m6115getButtono7Vup1c())) {
            return "android.widget.Button";
        }
        if (Role.m6111equalsimpl0(i, Role.INSTANCE.m6117getCheckboxo7Vup1c())) {
            return "android.widget.CheckBox";
        }
        if (Role.m6111equalsimpl0(i, Role.INSTANCE.m6120getRadioButtono7Vup1c())) {
            return "android.widget.RadioButton";
        }
        if (Role.m6111equalsimpl0(i, Role.INSTANCE.m6119getImageo7Vup1c())) {
            return "android.widget.ImageView";
        }
        if (Role.m6111equalsimpl0(i, Role.INSTANCE.m6118getDropdownListo7Vup1c())) {
            return "android.widget.Spinner";
        }
        if (Role.m6111equalsimpl0(i, Role.INSTANCE.m6123getValuePickero7Vup1c())) {
            return "android.widget.NumberPicker";
        }
        return null;
    }

    public static final View semanticsIdToView(AndroidViewsHandler androidViewsHandler, int i) {
        Object next;
        Iterator<T> it = androidViewsHandler.getLayoutNodeToHolder().entrySet().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (((LayoutNode) ((Map.Entry) next).getKey()).getSemanticsId() != i);
        Map.Entry entry = (Map.Entry) next;
        return entry != null ? (AndroidViewHolder) entry.getValue() : null;
    }
}
