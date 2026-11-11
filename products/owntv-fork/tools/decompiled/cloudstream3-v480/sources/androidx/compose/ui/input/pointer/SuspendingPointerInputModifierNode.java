package androidx.compose.ui.input.pointer;

import androidx.compose.ui.node.PointerInputModifierNode;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: SuspendingPointerInputFilter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001J\b\u0010\u0016\u001a\u00020\u0006H&RC\u0010\u0002\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0003¢\u0006\u0002\b\b8&@&X§\u000e¢\u0006\u0012\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR$\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00108V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015\u0082\u0001\u0001\u0017ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0018À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNode;", "Landroidx/compose/ui/node/PointerInputModifierNode;", "pointerInputHandler", "Lkotlin/Function2;", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "getPointerInputHandler$annotations", "()V", "getPointerInputHandler", "()Lkotlin/jvm/functions/Function2;", "setPointerInputHandler", "(Lkotlin/jvm/functions/Function2;)V", ES6Iterator.VALUE_PROPERTY, "Landroidx/compose/ui/input/pointer/PointerInputEventHandler;", "pointerInputEventHandler", "getPointerInputEventHandler", "()Landroidx/compose/ui/input/pointer/PointerInputEventHandler;", "setPointerInputEventHandler", "(Landroidx/compose/ui/input/pointer/PointerInputEventHandler;)V", "resetPointerInputHandler", "Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNodeImpl;", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface SuspendingPointerInputModifierNode extends PointerInputModifierNode {
    PointerInputEventHandler getPointerInputEventHandler();

    Function2<PointerInputScope, Continuation<? super Unit>, Object> getPointerInputHandler();

    void resetPointerInputHandler();

    void setPointerInputEventHandler(PointerInputEventHandler pointerInputEventHandler);

    void setPointerInputHandler(Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object> function2);

    /* JADX INFO: renamed from: androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: SuspendingPointerInputFilter.kt */
    public final /* synthetic */ class CC {
        @Deprecated(level = DeprecationLevel.ERROR, message = "This property is deprecated. Use 'pointerInputEventHandler' instead.", replaceWith = @ReplaceWith(expression = "pointerInputEventHandler", imports = {"androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode.pointerInputEventHandler"}))
        public static /* synthetic */ void getPointerInputHandler$annotations() {
        }

        public static PointerInputEventHandler $default$getPointerInputEventHandler(SuspendingPointerInputModifierNode _this) {
            throw new NotImplementedError("An operation is not implemented: pointerInputEventHandler must be implemented (get()).");
        }

        public static void $default$setPointerInputEventHandler(SuspendingPointerInputModifierNode _this, PointerInputEventHandler pointerInputEventHandler) {
            throw new NotImplementedError("An operation is not implemented: " + ("pointerInputEventHandler must be implemented (set(" + pointerInputEventHandler + "))."));
        }
    }
}
