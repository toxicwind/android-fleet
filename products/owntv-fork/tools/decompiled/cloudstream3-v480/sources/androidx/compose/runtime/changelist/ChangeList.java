package androidx.compose.runtime.changelist;

import androidx.compose.runtime.Anchor;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposeNodeLifecycleCallback;
import androidx.compose.runtime.Composition;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.ControlledComposition;
import androidx.compose.runtime.MovableContentState;
import androidx.compose.runtime.MovableContentStateReference;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RememberManager;
import androidx.compose.runtime.RememberObserverHolder;
import androidx.compose.runtime.SlotTable;
import androidx.compose.runtime.SlotWriter;
import androidx.compose.runtime.internal.IntRef;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.mozilla.javascript.ES6Iterator;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;

/* JADX INFO: compiled from: ChangeList.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000È\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\u000bJ\u0006\u0010\r\u001a\u00020\u000eJ,\u0010\u000f\u001a\u00020\u000e2\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017J\u000e\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001dJ\u0018\u0010 \u001a\u00020\u000e2\b\u0010\u0019\u001a\u0004\u0018\u00010!2\u0006\u0010\"\u001a\u00020\u0007J \u0010#\u001a\u00020\u000e2\b\u0010\u0019\u001a\u0004\u0018\u00010!2\u0006\u0010$\u001a\u00020%2\u0006\u0010\"\u001a\u00020\u0007J\u0018\u0010&\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020%2\b\u0010\u0019\u001a\u0004\u0018\u00010!J\u000e\u0010'\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\u0007J\u0006\u0010)\u001a\u00020\u000eJ\u0006\u0010*\u001a\u00020\u000eJ\u0010\u0010+\u001a\u00020\u000e2\b\u0010,\u001a\u0004\u0018\u00010!J\u0006\u0010-\u001a\u00020\u000eJ\u000e\u0010.\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020%J\u0006\u0010/\u001a\u00020\u000eJ\u0006\u00100\u001a\u00020\u000eJ\u0006\u00101\u001a\u00020\u000eJ\u0016\u00102\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020%2\u0006\u00103\u001a\u000204J\u001e\u00102\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020%2\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u000206J\u000e\u00107\u001a\u00020\u000e2\u0006\u00108\u001a\u00020\u0007J\"\u00109\u001a\u00020\u000e2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\u000e0;2\u0006\u0010=\u001a\u00020<J\u0010\u0010>\u001a\u00020\u000e2\b\u0010?\u001a\u0004\u0018\u00010!J>\u0010@\u001a\u00020\u000e\"\u0004\b\u0000\u0010A\"\u0004\b\u0001\u0010B2\u0006\u0010\u0019\u001a\u0002HB2\u001d\u0010C\u001a\u0019\u0012\u0004\u0012\u0002HA\u0012\u0004\u0012\u0002HB\u0012\u0004\u0012\u00020\u000e0D¢\u0006\u0002\bE¢\u0006\u0002\u0010FJ\u0016\u0010G\u001a\u00020\u000e2\u0006\u0010H\u001a\u00020\u00072\u0006\u0010I\u001a\u00020\u0007J\u001e\u0010J\u001a\u00020\u000e2\u0006\u0010K\u001a\u00020\u00072\u0006\u00103\u001a\u00020\u00072\u0006\u0010(\u001a\u00020\u0007J\u000e\u0010L\u001a\u00020\u000e2\u0006\u0010M\u001a\u00020\u0007J\u000e\u0010N\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\u0007J\u001b\u0010O\u001a\u00020\u000e2\u000e\u0010P\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010!0Q¢\u0006\u0002\u0010RJ\u0014\u0010S\u001a\u00020\u000e2\f\u0010T\u001a\b\u0012\u0004\u0012\u00020\u000e0UJ\u0016\u0010V\u001a\u00020\u000e2\u0006\u0010W\u001a\u00020X2\u0006\u0010$\u001a\u00020%J\u001e\u0010Y\u001a\u00020\u000e2\u000e\u0010P\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010!0Z2\u0006\u0010[\u001a\u00020XJ(\u0010\\\u001a\u00020\u000e2\b\u0010]\u001a\u0004\u0018\u00010^2\u0006\u0010_\u001a\u00020`2\u0006\u00103\u001a\u00020a2\u0006\u0010K\u001a\u00020aJ\u001e\u0010b\u001a\u00020\u000e2\u0006\u0010=\u001a\u00020c2\u0006\u0010_\u001a\u00020`2\u0006\u0010d\u001a\u00020aJ\u0006\u0010e\u001a\u00020\u000eJ\u001a\u0010f\u001a\u00020\u000e2\u0006\u0010g\u001a\u00020\u00002\n\b\u0002\u0010[\u001a\u0004\u0018\u00010XJ\u0010\u0010h\u001a\u00020i2\u0006\u0010j\u001a\u00020iH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006k"}, d2 = {"Landroidx/compose/runtime/changelist/ChangeList;", "Landroidx/compose/runtime/changelist/OperationsDebugStringFormattable;", "<init>", "()V", "operations", "Landroidx/compose/runtime/changelist/Operations;", ContentDisposition.Parameters.Size, "", "getSize", "()I", "isEmpty", "", "isNotEmpty", "clear", "", "executeAndFlushAllPendingChanges", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/RememberManager;", "errorContext", "Landroidx/compose/runtime/changelist/OperationErrorContext;", "pushRemember", ES6Iterator.VALUE_PROPERTY, "Landroidx/compose/runtime/RememberObserverHolder;", "pushRememberPausingScope", "scope", "Landroidx/compose/runtime/RecomposeScopeImpl;", "pushStartResumingScope", "pushEndResumingScope", "pushUpdateValue", "", "groupSlotIndex", "pushUpdateAnchoredValue", LinkHeader.Parameters.Anchor, "Landroidx/compose/runtime/Anchor;", "pushAppendValue", "pushTrimValues", PeertubeParsingHelper.COUNT_KEY, "pushResetSlots", "pushDeactivateCurrentGroup", "pushUpdateAuxData", "data", "pushEnsureRootStarted", "pushEnsureGroupStarted", "pushEndCurrentGroup", "pushSkipToEndOfCurrentGroup", "pushRemoveCurrentGroup", "pushInsertSlots", TypedValues.TransitionType.S_FROM, "Landroidx/compose/runtime/SlotTable;", "fixups", "Landroidx/compose/runtime/changelist/FixupList;", "pushMoveCurrentGroup", TypedValues.CycleType.S_WAVE_OFFSET, "pushEndCompositionScope", "action", "Lkotlin/Function1;", "Landroidx/compose/runtime/Composition;", "composition", "pushUseNode", "node", "pushUpdateNode", ExifInterface.GPS_DIRECTION_TRUE, ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "block", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "pushRemoveNode", "removeFrom", "moveCount", "pushMoveNode", TypedValues.TransitionType.S_TO, "pushAdvanceSlotsBy", "distance", "pushUps", "pushDowns", "nodes", "", "([Ljava/lang/Object;)V", "pushSideEffect", "effect", "Lkotlin/Function0;", "pushDetermineMovableContentNodeIndex", "effectiveNodeIndexOut", "Landroidx/compose/runtime/internal/IntRef;", "pushCopyNodesToNewAnchorLocation", "", "effectiveNodeIndex", "pushCopySlotTableToAnchorLocation", "resolvedState", "Landroidx/compose/runtime/MovableContentState;", "parentContext", "Landroidx/compose/runtime/CompositionContext;", "Landroidx/compose/runtime/MovableContentStateReference;", "pushReleaseMovableGroupAtCurrent", "Landroidx/compose/runtime/ControlledComposition;", TypedValues.Custom.S_REFERENCE, "pushEndMovableContentPlacement", "pushExecuteOperationsIn", "changeList", "toDebugString", "", "linePrefix", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ChangeList extends OperationsDebugStringFormattable {
    public static final int $stable = 8;
    private final Operations operations = new Operations();

    public final int getSize() {
        return this.operations.getOpCodesSize();
    }

    public final boolean isEmpty() {
        return this.operations.isEmpty();
    }

    public final boolean isNotEmpty() {
        return this.operations.isNotEmpty();
    }

    public final void clear() {
        this.operations.clear();
    }

    public final void executeAndFlushAllPendingChanges(Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
        this.operations.executeAndFlushAllPendingOperations(applier, slots, rememberManager, errorContext);
    }

    public final void pushRemember(RememberObserverHolder value) {
        Operations operations = this.operations;
        Operation.Remember remember = Operation.Remember.INSTANCE;
        operations.pushOp(remember);
        Operations operationsM3515constructorimpl = Operations.WriteScope.m3515constructorimpl(operations);
        Operation.Remember remember2 = Operation.Remember.INSTANCE;
        Operations.WriteScope.m3523setObjectDKhxnng(operationsM3515constructorimpl, Operation.ObjectParameter.m3492constructorimpl(0), value);
        operations.ensureAllArgumentsPushedFor(remember);
    }

    public final void pushRememberPausingScope(RecomposeScopeImpl scope) {
        Operations operations = this.operations;
        Operation.RememberPausingScope rememberPausingScope = Operation.RememberPausingScope.INSTANCE;
        operations.pushOp(rememberPausingScope);
        Operations operationsM3515constructorimpl = Operations.WriteScope.m3515constructorimpl(operations);
        Operation.RememberPausingScope rememberPausingScope2 = Operation.RememberPausingScope.INSTANCE;
        Operations.WriteScope.m3523setObjectDKhxnng(operationsM3515constructorimpl, Operation.ObjectParameter.m3492constructorimpl(0), scope);
        operations.ensureAllArgumentsPushedFor(rememberPausingScope);
    }

    public final void pushStartResumingScope(RecomposeScopeImpl scope) {
        Operations operations = this.operations;
        Operation.StartResumingScope startResumingScope = Operation.StartResumingScope.INSTANCE;
        operations.pushOp(startResumingScope);
        Operations operationsM3515constructorimpl = Operations.WriteScope.m3515constructorimpl(operations);
        Operation.StartResumingScope startResumingScope2 = Operation.StartResumingScope.INSTANCE;
        Operations.WriteScope.m3523setObjectDKhxnng(operationsM3515constructorimpl, Operation.ObjectParameter.m3492constructorimpl(0), scope);
        operations.ensureAllArgumentsPushedFor(startResumingScope);
    }

    public final void pushEndResumingScope(RecomposeScopeImpl scope) {
        Operations operations = this.operations;
        Operation.EndResumingScope endResumingScope = Operation.EndResumingScope.INSTANCE;
        operations.pushOp(endResumingScope);
        Operations operationsM3515constructorimpl = Operations.WriteScope.m3515constructorimpl(operations);
        Operation.EndResumingScope endResumingScope2 = Operation.EndResumingScope.INSTANCE;
        Operations.WriteScope.m3523setObjectDKhxnng(operationsM3515constructorimpl, Operation.ObjectParameter.m3492constructorimpl(0), scope);
        operations.ensureAllArgumentsPushedFor(endResumingScope);
    }

    public final void pushUpdateValue(Object value, int groupSlotIndex) {
        Operations operations = this.operations;
        Operation.UpdateValue updateValue = Operation.UpdateValue.INSTANCE;
        operations.pushOp(updateValue);
        Operations operationsM3515constructorimpl = Operations.WriteScope.m3515constructorimpl(operations);
        Operation.UpdateValue updateValue2 = Operation.UpdateValue.INSTANCE;
        Operations.WriteScope.m3523setObjectDKhxnng(operationsM3515constructorimpl, Operation.ObjectParameter.m3492constructorimpl(0), value);
        Operation.UpdateValue updateValue3 = Operation.UpdateValue.INSTANCE;
        operationsM3515constructorimpl.intArgs[operationsM3515constructorimpl.intArgsSize - operationsM3515constructorimpl.opCodes[operationsM3515constructorimpl.opCodesSize - 1].getInts()] = groupSlotIndex;
        operations.ensureAllArgumentsPushedFor(updateValue);
    }

    public final void pushUpdateAnchoredValue(Object value, Anchor anchor, int groupSlotIndex) {
        Operations operations = this.operations;
        Operation.UpdateAnchoredValue updateAnchoredValue = Operation.UpdateAnchoredValue.INSTANCE;
        operations.pushOp(updateAnchoredValue);
        Operations operationsM3515constructorimpl = Operations.WriteScope.m3515constructorimpl(operations);
        Operation.UpdateAnchoredValue updateAnchoredValue2 = Operation.UpdateAnchoredValue.INSTANCE;
        int iM3492constructorimpl = Operation.ObjectParameter.m3492constructorimpl(0);
        Operation.UpdateAnchoredValue updateAnchoredValue3 = Operation.UpdateAnchoredValue.INSTANCE;
        Operations.WriteScope.m3524setObjects4uCC6AY(operationsM3515constructorimpl, iM3492constructorimpl, value, Operation.ObjectParameter.m3492constructorimpl(1), anchor);
        Operation.UpdateAnchoredValue updateAnchoredValue4 = Operation.UpdateAnchoredValue.INSTANCE;
        operationsM3515constructorimpl.intArgs[operationsM3515constructorimpl.intArgsSize - operationsM3515constructorimpl.opCodes[operationsM3515constructorimpl.opCodesSize - 1].getInts()] = groupSlotIndex;
        operations.ensureAllArgumentsPushedFor(updateAnchoredValue);
    }

    public final void pushAppendValue(Anchor anchor, Object value) {
        Operations operations = this.operations;
        Operation.AppendValue appendValue = Operation.AppendValue.INSTANCE;
        operations.pushOp(appendValue);
        Operations operationsM3515constructorimpl = Operations.WriteScope.m3515constructorimpl(operations);
        Operation.AppendValue appendValue2 = Operation.AppendValue.INSTANCE;
        int iM3492constructorimpl = Operation.ObjectParameter.m3492constructorimpl(0);
        Operation.AppendValue appendValue3 = Operation.AppendValue.INSTANCE;
        Operations.WriteScope.m3524setObjects4uCC6AY(operationsM3515constructorimpl, iM3492constructorimpl, anchor, Operation.ObjectParameter.m3492constructorimpl(1), value);
        operations.ensureAllArgumentsPushedFor(appendValue);
    }

    public final void pushTrimValues(int count) {
        Operations operations = this.operations;
        Operation.TrimParentValues trimParentValues = Operation.TrimParentValues.INSTANCE;
        operations.pushOp(trimParentValues);
        Operations operationsM3515constructorimpl = Operations.WriteScope.m3515constructorimpl(operations);
        Operation.TrimParentValues trimParentValues2 = Operation.TrimParentValues.INSTANCE;
        operationsM3515constructorimpl.intArgs[operationsM3515constructorimpl.intArgsSize - operationsM3515constructorimpl.opCodes[operationsM3515constructorimpl.opCodesSize - 1].getInts()] = count;
        operations.ensureAllArgumentsPushedFor(trimParentValues);
    }

    public final void pushResetSlots() {
        this.operations.push(Operation.ResetSlots.INSTANCE);
    }

    public final void pushDeactivateCurrentGroup() {
        this.operations.push(Operation.DeactivateCurrentGroup.INSTANCE);
    }

    public final void pushUpdateAuxData(Object data) {
        Operations operations = this.operations;
        Operation.UpdateAuxData updateAuxData = Operation.UpdateAuxData.INSTANCE;
        operations.pushOp(updateAuxData);
        Operations operationsM3515constructorimpl = Operations.WriteScope.m3515constructorimpl(operations);
        Operation.UpdateAuxData updateAuxData2 = Operation.UpdateAuxData.INSTANCE;
        Operations.WriteScope.m3523setObjectDKhxnng(operationsM3515constructorimpl, Operation.ObjectParameter.m3492constructorimpl(0), data);
        operations.ensureAllArgumentsPushedFor(updateAuxData);
    }

    public final void pushEnsureRootStarted() {
        this.operations.push(Operation.EnsureRootGroupStarted.INSTANCE);
    }

    public final void pushEnsureGroupStarted(Anchor anchor) {
        Operations operations = this.operations;
        Operation.EnsureGroupStarted ensureGroupStarted = Operation.EnsureGroupStarted.INSTANCE;
        operations.pushOp(ensureGroupStarted);
        Operations operationsM3515constructorimpl = Operations.WriteScope.m3515constructorimpl(operations);
        Operation.EnsureGroupStarted ensureGroupStarted2 = Operation.EnsureGroupStarted.INSTANCE;
        Operations.WriteScope.m3523setObjectDKhxnng(operationsM3515constructorimpl, Operation.ObjectParameter.m3492constructorimpl(0), anchor);
        operations.ensureAllArgumentsPushedFor(ensureGroupStarted);
    }

    public final void pushEndCurrentGroup() {
        this.operations.push(Operation.EndCurrentGroup.INSTANCE);
    }

    public final void pushSkipToEndOfCurrentGroup() {
        this.operations.push(Operation.SkipToEndOfCurrentGroup.INSTANCE);
    }

    public final void pushRemoveCurrentGroup() {
        this.operations.push(Operation.RemoveCurrentGroup.INSTANCE);
    }

    public final void pushInsertSlots(Anchor anchor, SlotTable from) {
        Operations operations = this.operations;
        Operation.InsertSlots insertSlots = Operation.InsertSlots.INSTANCE;
        operations.pushOp(insertSlots);
        Operations operationsM3515constructorimpl = Operations.WriteScope.m3515constructorimpl(operations);
        Operation.InsertSlots insertSlots2 = Operation.InsertSlots.INSTANCE;
        int iM3492constructorimpl = Operation.ObjectParameter.m3492constructorimpl(0);
        Operation.InsertSlots insertSlots3 = Operation.InsertSlots.INSTANCE;
        Operations.WriteScope.m3524setObjects4uCC6AY(operationsM3515constructorimpl, iM3492constructorimpl, anchor, Operation.ObjectParameter.m3492constructorimpl(1), from);
        operations.ensureAllArgumentsPushedFor(insertSlots);
    }

    public final void pushInsertSlots(Anchor anchor, SlotTable from, FixupList fixups) {
        Operations operations = this.operations;
        Operation.InsertSlotsWithFixups insertSlotsWithFixups = Operation.InsertSlotsWithFixups.INSTANCE;
        operations.pushOp(insertSlotsWithFixups);
        Operations operationsM3515constructorimpl = Operations.WriteScope.m3515constructorimpl(operations);
        Operation.InsertSlotsWithFixups insertSlotsWithFixups2 = Operation.InsertSlotsWithFixups.INSTANCE;
        int iM3492constructorimpl = Operation.ObjectParameter.m3492constructorimpl(0);
        Operation.InsertSlotsWithFixups insertSlotsWithFixups3 = Operation.InsertSlotsWithFixups.INSTANCE;
        int iM3492constructorimpl2 = Operation.ObjectParameter.m3492constructorimpl(1);
        Operation.InsertSlotsWithFixups insertSlotsWithFixups4 = Operation.InsertSlotsWithFixups.INSTANCE;
        Operations.WriteScope.m3526setObjectst7hvbck(operationsM3515constructorimpl, iM3492constructorimpl, anchor, iM3492constructorimpl2, from, Operation.ObjectParameter.m3492constructorimpl(2), fixups);
        operations.ensureAllArgumentsPushedFor(insertSlotsWithFixups);
    }

    public final void pushMoveCurrentGroup(int offset) {
        Operations operations = this.operations;
        Operation.MoveCurrentGroup moveCurrentGroup = Operation.MoveCurrentGroup.INSTANCE;
        operations.pushOp(moveCurrentGroup);
        Operations operationsM3515constructorimpl = Operations.WriteScope.m3515constructorimpl(operations);
        Operation.MoveCurrentGroup moveCurrentGroup2 = Operation.MoveCurrentGroup.INSTANCE;
        operationsM3515constructorimpl.intArgs[operationsM3515constructorimpl.intArgsSize - operationsM3515constructorimpl.opCodes[operationsM3515constructorimpl.opCodesSize - 1].getInts()] = offset;
        operations.ensureAllArgumentsPushedFor(moveCurrentGroup);
    }

    public final void pushEndCompositionScope(Function1<? super Composition, Unit> action, Composition composition) {
        Operations operations = this.operations;
        Operation.EndCompositionScope endCompositionScope = Operation.EndCompositionScope.INSTANCE;
        operations.pushOp(endCompositionScope);
        Operations operationsM3515constructorimpl = Operations.WriteScope.m3515constructorimpl(operations);
        Operation.EndCompositionScope endCompositionScope2 = Operation.EndCompositionScope.INSTANCE;
        int iM3492constructorimpl = Operation.ObjectParameter.m3492constructorimpl(0);
        Operation.EndCompositionScope endCompositionScope3 = Operation.EndCompositionScope.INSTANCE;
        Operations.WriteScope.m3524setObjects4uCC6AY(operationsM3515constructorimpl, iM3492constructorimpl, action, Operation.ObjectParameter.m3492constructorimpl(1), composition);
        operations.ensureAllArgumentsPushedFor(endCompositionScope);
    }

    public final void pushUseNode(Object node) {
        if (node instanceof ComposeNodeLifecycleCallback) {
            this.operations.push(Operation.UseCurrentNode.INSTANCE);
        }
    }

    public final <T, V> void pushUpdateNode(V value, Function2<? super T, ? super V, Unit> block) {
        Operations operations = this.operations;
        Operation.UpdateNode updateNode = Operation.UpdateNode.INSTANCE;
        operations.pushOp(updateNode);
        Operations operationsM3515constructorimpl = Operations.WriteScope.m3515constructorimpl(operations);
        Operation.UpdateNode updateNode2 = Operation.UpdateNode.INSTANCE;
        int iM3492constructorimpl = Operation.ObjectParameter.m3492constructorimpl(0);
        Operation.UpdateNode updateNode3 = Operation.UpdateNode.INSTANCE;
        int iM3492constructorimpl2 = Operation.ObjectParameter.m3492constructorimpl(1);
        Intrinsics.checkNotNull(block, "null cannot be cast to non-null type @[ExtensionFunctionType] kotlin.Function2<kotlin.Any?, kotlin.Any?, kotlin.Unit>");
        Operations.WriteScope.m3524setObjects4uCC6AY(operationsM3515constructorimpl, iM3492constructorimpl, value, iM3492constructorimpl2, (Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(block, 2));
        operations.ensureAllArgumentsPushedFor(updateNode);
    }

    public final void pushRemoveNode(int removeFrom, int moveCount) {
        Operations operations = this.operations;
        Operation.RemoveNode removeNode = Operation.RemoveNode.INSTANCE;
        operations.pushOp(removeNode);
        Operations operationsM3515constructorimpl = Operations.WriteScope.m3515constructorimpl(operations);
        Operation.RemoveNode removeNode2 = Operation.RemoveNode.INSTANCE;
        Operation.RemoveNode removeNode3 = Operation.RemoveNode.INSTANCE;
        int ints = operationsM3515constructorimpl.intArgsSize - operationsM3515constructorimpl.opCodes[operationsM3515constructorimpl.opCodesSize - 1].getInts();
        int[] iArr = operationsM3515constructorimpl.intArgs;
        iArr[ints] = removeFrom;
        iArr[ints + 1] = moveCount;
        operations.ensureAllArgumentsPushedFor(removeNode);
    }

    public final void pushMoveNode(int to, int from, int count) {
        Operations operations = this.operations;
        Operation.MoveNode moveNode = Operation.MoveNode.INSTANCE;
        operations.pushOp(moveNode);
        Operations operationsM3515constructorimpl = Operations.WriteScope.m3515constructorimpl(operations);
        Operation.MoveNode moveNode2 = Operation.MoveNode.INSTANCE;
        Operation.MoveNode moveNode3 = Operation.MoveNode.INSTANCE;
        Operation.MoveNode moveNode4 = Operation.MoveNode.INSTANCE;
        int ints = operationsM3515constructorimpl.intArgsSize - operationsM3515constructorimpl.opCodes[operationsM3515constructorimpl.opCodesSize - 1].getInts();
        int[] iArr = operationsM3515constructorimpl.intArgs;
        iArr[ints + 1] = to;
        iArr[ints] = from;
        iArr[ints + 2] = count;
        operations.ensureAllArgumentsPushedFor(moveNode);
    }

    public final void pushAdvanceSlotsBy(int distance) {
        Operations operations = this.operations;
        Operation.AdvanceSlotsBy advanceSlotsBy = Operation.AdvanceSlotsBy.INSTANCE;
        operations.pushOp(advanceSlotsBy);
        Operations operationsM3515constructorimpl = Operations.WriteScope.m3515constructorimpl(operations);
        Operation.AdvanceSlotsBy advanceSlotsBy2 = Operation.AdvanceSlotsBy.INSTANCE;
        operationsM3515constructorimpl.intArgs[operationsM3515constructorimpl.intArgsSize - operationsM3515constructorimpl.opCodes[operationsM3515constructorimpl.opCodesSize - 1].getInts()] = distance;
        operations.ensureAllArgumentsPushedFor(advanceSlotsBy);
    }

    public final void pushUps(int count) {
        Operations operations = this.operations;
        Operation.Ups ups = Operation.Ups.INSTANCE;
        operations.pushOp(ups);
        Operations operationsM3515constructorimpl = Operations.WriteScope.m3515constructorimpl(operations);
        Operation.Ups ups2 = Operation.Ups.INSTANCE;
        operationsM3515constructorimpl.intArgs[operationsM3515constructorimpl.intArgsSize - operationsM3515constructorimpl.opCodes[operationsM3515constructorimpl.opCodesSize - 1].getInts()] = count;
        operations.ensureAllArgumentsPushedFor(ups);
    }

    public final void pushDowns(Object[] nodes) {
        if (nodes.length == 0) {
            return;
        }
        Operations operations = this.operations;
        Operation.Downs downs = Operation.Downs.INSTANCE;
        operations.pushOp(downs);
        Operations operationsM3515constructorimpl = Operations.WriteScope.m3515constructorimpl(operations);
        Operation.Downs downs2 = Operation.Downs.INSTANCE;
        Operations.WriteScope.m3523setObjectDKhxnng(operationsM3515constructorimpl, Operation.ObjectParameter.m3492constructorimpl(0), nodes);
        operations.ensureAllArgumentsPushedFor(downs);
    }

    public final void pushSideEffect(Function0<Unit> effect) {
        Operations operations = this.operations;
        Operation.SideEffect sideEffect = Operation.SideEffect.INSTANCE;
        operations.pushOp(sideEffect);
        Operations operationsM3515constructorimpl = Operations.WriteScope.m3515constructorimpl(operations);
        Operation.SideEffect sideEffect2 = Operation.SideEffect.INSTANCE;
        Operations.WriteScope.m3523setObjectDKhxnng(operationsM3515constructorimpl, Operation.ObjectParameter.m3492constructorimpl(0), effect);
        operations.ensureAllArgumentsPushedFor(sideEffect);
    }

    public final void pushDetermineMovableContentNodeIndex(IntRef effectiveNodeIndexOut, Anchor anchor) {
        Operations operations = this.operations;
        Operation.DetermineMovableContentNodeIndex determineMovableContentNodeIndex = Operation.DetermineMovableContentNodeIndex.INSTANCE;
        operations.pushOp(determineMovableContentNodeIndex);
        Operations operationsM3515constructorimpl = Operations.WriteScope.m3515constructorimpl(operations);
        Operation.DetermineMovableContentNodeIndex determineMovableContentNodeIndex2 = Operation.DetermineMovableContentNodeIndex.INSTANCE;
        int iM3492constructorimpl = Operation.ObjectParameter.m3492constructorimpl(0);
        Operation.DetermineMovableContentNodeIndex determineMovableContentNodeIndex3 = Operation.DetermineMovableContentNodeIndex.INSTANCE;
        Operations.WriteScope.m3524setObjects4uCC6AY(operationsM3515constructorimpl, iM3492constructorimpl, effectiveNodeIndexOut, Operation.ObjectParameter.m3492constructorimpl(1), anchor);
        operations.ensureAllArgumentsPushedFor(determineMovableContentNodeIndex);
    }

    public final void pushCopyNodesToNewAnchorLocation(List<? extends Object> nodes, IntRef effectiveNodeIndex) {
        if (nodes.isEmpty()) {
            return;
        }
        Operations operations = this.operations;
        Operation.CopyNodesToNewAnchorLocation copyNodesToNewAnchorLocation = Operation.CopyNodesToNewAnchorLocation.INSTANCE;
        operations.pushOp(copyNodesToNewAnchorLocation);
        Operations operationsM3515constructorimpl = Operations.WriteScope.m3515constructorimpl(operations);
        Operation.CopyNodesToNewAnchorLocation copyNodesToNewAnchorLocation2 = Operation.CopyNodesToNewAnchorLocation.INSTANCE;
        int iM3492constructorimpl = Operation.ObjectParameter.m3492constructorimpl(1);
        Operation.CopyNodesToNewAnchorLocation copyNodesToNewAnchorLocation3 = Operation.CopyNodesToNewAnchorLocation.INSTANCE;
        Operations.WriteScope.m3524setObjects4uCC6AY(operationsM3515constructorimpl, iM3492constructorimpl, nodes, Operation.ObjectParameter.m3492constructorimpl(0), effectiveNodeIndex);
        operations.ensureAllArgumentsPushedFor(copyNodesToNewAnchorLocation);
    }

    public final void pushCopySlotTableToAnchorLocation(MovableContentState resolvedState, CompositionContext parentContext, MovableContentStateReference from, MovableContentStateReference to) {
        Operations operations = this.operations;
        Operation.CopySlotTableToAnchorLocation copySlotTableToAnchorLocation = Operation.CopySlotTableToAnchorLocation.INSTANCE;
        operations.pushOp(copySlotTableToAnchorLocation);
        Operations operationsM3515constructorimpl = Operations.WriteScope.m3515constructorimpl(operations);
        Operation.CopySlotTableToAnchorLocation copySlotTableToAnchorLocation2 = Operation.CopySlotTableToAnchorLocation.INSTANCE;
        int iM3492constructorimpl = Operation.ObjectParameter.m3492constructorimpl(0);
        Operation.CopySlotTableToAnchorLocation copySlotTableToAnchorLocation3 = Operation.CopySlotTableToAnchorLocation.INSTANCE;
        int iM3492constructorimpl2 = Operation.ObjectParameter.m3492constructorimpl(1);
        Operation.CopySlotTableToAnchorLocation copySlotTableToAnchorLocation4 = Operation.CopySlotTableToAnchorLocation.INSTANCE;
        int iM3492constructorimpl3 = Operation.ObjectParameter.m3492constructorimpl(3);
        Operation.CopySlotTableToAnchorLocation copySlotTableToAnchorLocation5 = Operation.CopySlotTableToAnchorLocation.INSTANCE;
        Operations.WriteScope.m3525setObjectsOGa0p1M(operationsM3515constructorimpl, iM3492constructorimpl, resolvedState, iM3492constructorimpl2, parentContext, iM3492constructorimpl3, to, Operation.ObjectParameter.m3492constructorimpl(2), from);
        operations.ensureAllArgumentsPushedFor(copySlotTableToAnchorLocation);
    }

    public final void pushReleaseMovableGroupAtCurrent(ControlledComposition composition, CompositionContext parentContext, MovableContentStateReference reference) {
        Operations operations = this.operations;
        Operation.ReleaseMovableGroupAtCurrent releaseMovableGroupAtCurrent = Operation.ReleaseMovableGroupAtCurrent.INSTANCE;
        operations.pushOp(releaseMovableGroupAtCurrent);
        Operations operationsM3515constructorimpl = Operations.WriteScope.m3515constructorimpl(operations);
        Operation.ReleaseMovableGroupAtCurrent releaseMovableGroupAtCurrent2 = Operation.ReleaseMovableGroupAtCurrent.INSTANCE;
        int iM3492constructorimpl = Operation.ObjectParameter.m3492constructorimpl(0);
        Operation.ReleaseMovableGroupAtCurrent releaseMovableGroupAtCurrent3 = Operation.ReleaseMovableGroupAtCurrent.INSTANCE;
        int iM3492constructorimpl2 = Operation.ObjectParameter.m3492constructorimpl(1);
        Operation.ReleaseMovableGroupAtCurrent releaseMovableGroupAtCurrent4 = Operation.ReleaseMovableGroupAtCurrent.INSTANCE;
        Operations.WriteScope.m3526setObjectst7hvbck(operationsM3515constructorimpl, iM3492constructorimpl, composition, iM3492constructorimpl2, parentContext, Operation.ObjectParameter.m3492constructorimpl(2), reference);
        operations.ensureAllArgumentsPushedFor(releaseMovableGroupAtCurrent);
    }

    public final void pushEndMovableContentPlacement() {
        this.operations.push(Operation.EndMovableContentPlacement.INSTANCE);
    }

    public static /* synthetic */ void pushExecuteOperationsIn$default(ChangeList changeList, ChangeList changeList2, IntRef intRef, int i, Object obj) {
        if ((i & 2) != 0) {
            intRef = null;
        }
        changeList.pushExecuteOperationsIn(changeList2, intRef);
    }

    public final void pushExecuteOperationsIn(ChangeList changeList, IntRef effectiveNodeIndex) {
        if (changeList.isNotEmpty()) {
            Operations operations = this.operations;
            Operation.ApplyChangeList applyChangeList = Operation.ApplyChangeList.INSTANCE;
            operations.pushOp(applyChangeList);
            Operations operationsM3515constructorimpl = Operations.WriteScope.m3515constructorimpl(operations);
            Operation.ApplyChangeList applyChangeList2 = Operation.ApplyChangeList.INSTANCE;
            int iM3492constructorimpl = Operation.ObjectParameter.m3492constructorimpl(0);
            Operation.ApplyChangeList applyChangeList3 = Operation.ApplyChangeList.INSTANCE;
            Operations.WriteScope.m3524setObjects4uCC6AY(operationsM3515constructorimpl, iM3492constructorimpl, changeList, Operation.ObjectParameter.m3492constructorimpl(1), effectiveNodeIndex);
            operations.ensureAllArgumentsPushedFor(applyChangeList);
        }
    }

    @Override // androidx.compose.runtime.changelist.OperationsDebugStringFormattable
    public String toDebugString(String linePrefix) {
        StringBuilder sb = new StringBuilder();
        sb.append("ChangeList instance containing ");
        sb.append(getSize());
        sb.append(" operations");
        if (sb.length() > 0) {
            sb.append(":\n");
            sb.append(this.operations.toDebugString(linePrefix));
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }
}
