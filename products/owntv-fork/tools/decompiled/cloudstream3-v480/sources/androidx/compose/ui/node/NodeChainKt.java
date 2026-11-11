package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Actual_jvmKt;
import androidx.compose.ui.CombinedModifier;
import androidx.compose.ui.Modifier;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NodeChain.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0018\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0000\u001a$\u0010\b\u001a\u00020\t\"\b\b\u0000\u0010\n*\u00020\u000b*\b\u0012\u0004\u0012\u0002H\n0\f2\u0006\u0010\r\u001a\u00020\u000bH\u0002\u001a.\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u000f*\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u000f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"ActionReplace", "", "ActionUpdate", "ActionReuse", "actionForModifiers", "prev", "Landroidx/compose/ui/Modifier$Element;", "next", "updateUnsafe", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/ModifierNodeElement;", "node", "fillVector", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/ui/Modifier;", "result", "stack", "ui_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class NodeChainKt {
    private static final int ActionReplace = 0;
    private static final int ActionReuse = 2;
    private static final int ActionUpdate = 1;

    public static final int actionForModifiers(Modifier.Element element, Modifier.Element element2) {
        if (Intrinsics.areEqual(element, element2)) {
            return 2;
        }
        return Actual_jvmKt.areObjectsOfSameType(element, element2) ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T extends Modifier.Node> void updateUnsafe(ModifierNodeElement<T> modifierNodeElement, Modifier.Node node) {
        Intrinsics.checkNotNull(node, "null cannot be cast to non-null type T of androidx.compose.ui.node.NodeChainKt.updateUnsafe");
        modifierNodeElement.update(node);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final MutableVector<Modifier.Element> fillVector(Modifier modifier, final MutableVector<Modifier.Element> mutableVector, MutableVector<Modifier> mutableVector2) {
        mutableVector2.add(modifier);
        Function1<Modifier.Element, Boolean> function1 = null;
        while (mutableVector2.getSize() != 0) {
            Modifier modifierRemoveAt = mutableVector2.removeAt(mutableVector2.getSize() - 1);
            if (modifierRemoveAt instanceof CombinedModifier) {
                CombinedModifier combinedModifier = (CombinedModifier) modifierRemoveAt;
                mutableVector2.add(combinedModifier.getInner());
                mutableVector2.add(combinedModifier.getOuter());
            } else if (modifierRemoveAt instanceof Modifier.Element) {
                mutableVector.add(modifierRemoveAt);
            } else {
                if (function1 == null) {
                    function1 = new Function1<Modifier.Element, Boolean>() { // from class: androidx.compose.ui.node.NodeChainKt.fillVector.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Boolean invoke(Modifier.Element element) {
                            mutableVector.add(element);
                            return true;
                        }
                    };
                }
                modifierRemoveAt.all(function1);
                function1 = function1;
            }
        }
        return mutableVector;
    }
}
