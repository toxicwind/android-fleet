package androidx.compose.runtime.changelist;

import androidx.compose.runtime.Applier;
import androidx.compose.runtime.RememberManager;
import androidx.compose.runtime.SlotWriter;
import androidx.exifinterface.media.ExifInterface;
import io.ktor.http.ContentDisposition;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Operations.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u001c\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001:\u0002STB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u0018J\u0006\u0010\u001a\u001a\u00020\u001bJ\u0010\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u0006H\u0007J\u0018\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010 \u001a\u00020\nH\u0002J\b\u0010!\u001a\u00020\u001bH\u0002J\u0011\u0010\"\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\nH\u0082\bJ\u0018\u0010#\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010 \u001a\u00020\nH\u0002J\u0011\u0010$\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\nH\u0082\bJ\u0018\u0010%\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010 \u001a\u00020\nH\u0002J\u000e\u0010&\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u0006J\u0010\u0010'\u001a\u00020(2\u0006\u0010\u001d\u001a\u00020\u0006H\u0002J7\u0010&\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u00062\u0017\u0010)\u001a\u0013\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u001b0*¢\u0006\u0002\b,H\u0086\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001J\u000e\u0010-\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u0006J\u0010\u0010.\u001a\u00020(2\u0006\u0010\u001d\u001a\u00020\u0006H\u0002J\u0011\u0010/\u001a\u00020\n2\u0006\u00100\u001a\u00020\nH\u0082\bJ\u0006\u00101\u001a\u00020\u001bJ\u000e\u00102\u001a\u00020\u001b2\u0006\u00103\u001a\u00020\u0000J&\u00104\u001a\u00020\u001b2\u001b\u00105\u001a\u0017\u0012\b\u0012\u000606R\u00020\u0000\u0012\u0004\u0012\u00020\u001b0*¢\u0006\u0002\b,H\u0086\bJ&\u00107\u001a\u00020\u001b2\u001b\u00108\u001a\u0017\u0012\b\u0012\u000606R\u00020\u0000\u0012\u0004\u0012\u00020\u001b0*¢\u0006\u0002\b,H\u0086\bJ,\u00109\u001a\u00020\u001b2\n\u0010:\u001a\u0006\u0012\u0002\b\u00030;2\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020?2\b\u0010@\u001a\u0004\u0018\u00010AJ\f\u0010B\u001a\u00020(*\u00020(H\u0002J\t\u0010C\u001a\u00020\u0006H\u0082\bJ\u0015\u0010D\u001a\u00020\n2\n\u0010E\u001a\u00060\nj\u0002`FH\u0082\bJ\u001c\u0010G\u001a\u00020\n2\n\u0010E\u001a\u0006\u0012\u0002\b\u00030HH\u0082\b¢\u0006\u0004\bI\u0010JJ\b\u0010K\u001a\u00020(H\u0017J\u0010\u0010L\u001a\u00020(2\u0006\u0010M\u001a\u00020(H\u0016J\u0018\u0010N\u001a\u00020(*\u000606R\u00020\u00002\u0006\u0010M\u001a\u00020(H\u0002J\u0016\u0010O\u001a\u00020(*\u0004\u0018\u00010\u000f2\u0006\u0010M\u001a\u00020(H\u0002J \u0010P\u001a\u00020(\"\u0004\b\u0000\u0010Q*\b\u0012\u0004\u0012\u0002HQ0R2\u0006\u0010M\u001a\u00020(H\u0002R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0000@\u0000X\u0081\u000e¢\u0006\n\n\u0002\u0010\b\u0012\u0004\b\u0007\u0010\u0003R\u0012\u0010\t\u001a\u00020\n8\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u00020\f8\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u00020\n8\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u00058\u0000@\u0000X\u0081\u000e¢\u0006\u0004\n\u0002\u0010\u0010R\u0012\u0010\u0011\u001a\u00020\n8\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0014\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006U"}, d2 = {"Landroidx/compose/runtime/changelist/Operations;", "Landroidx/compose/runtime/changelist/OperationsDebugStringFormattable;", "<init>", "()V", "opCodes", "", "Landroidx/compose/runtime/changelist/Operation;", "getOpCodes$runtime$annotations", "[Landroidx/compose/runtime/changelist/Operation;", "opCodesSize", "", "intArgs", "", "intArgsSize", "objectArgs", "", "[Ljava/lang/Object;", "objectArgsSize", "pushedIntMask", "pushedObjectMask", ContentDisposition.Parameters.Size, "getSize", "()I", "isEmpty", "", "isNotEmpty", "clear", "", "pushOp", "operation", "determineNewSize", "currentSize", "requiredSize", "resizeOpCodes", "ensureIntArgsSizeAtLeast", "resizeIntArgs", "ensureObjectArgsSizeAtLeast", "resizeObjectArgs", "push", "exceptionMessageForOperationPushNoScope", "", "args", "Lkotlin/Function1;", "Landroidx/compose/runtime/changelist/Operations$WriteScope;", "Lkotlin/ExtensionFunctionType;", "ensureAllArgumentsPushedFor", "exceptionMessageForOperationPushWithScope", "createExpectedArgMask", "paramCount", "pop", "popInto", "other", "drain", "sink", "Landroidx/compose/runtime/changelist/Operations$OpIterator;", "forEach", "action", "executeAndFlushAllPendingOperations", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/RememberManager;", "errorContext", "Landroidx/compose/runtime/changelist/OperationErrorContext;", "indent", "peekOperation", "topIntIndexOf", "parameter", "Landroidx/compose/runtime/changelist/IntParameter;", "topObjectIndexOf", "Landroidx/compose/runtime/changelist/Operation$ObjectParameter;", "topObjectIndexOf-31yXWZQ", "(I)I", "toString", "toDebugString", "linePrefix", "currentOpToDebugString", "formatOpArgumentToString", "toCollectionString", ExifInterface.GPS_DIRECTION_TRUE, "", "WriteScope", "OpIterator", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class Operations extends OperationsDebugStringFormattable {
    public static final int $stable = 8;
    public int intArgsSize;
    public int objectArgsSize;
    public int opCodesSize;
    private int pushedIntMask;
    private int pushedObjectMask;
    public Operation[] opCodes = new Operation[16];
    public int[] intArgs = new int[16];
    public Object[] objectArgs = new Object[16];

    private final int createExpectedArgMask(int paramCount) {
        return (paramCount == 0 ? 0 : -1) >>> (32 - paramCount);
    }

    public static /* synthetic */ void getOpCodes$runtime$annotations() {
    }

    /* JADX INFO: renamed from: getSize, reason: from getter */
    public final int getOpCodesSize() {
        return this.opCodesSize;
    }

    public final boolean isEmpty() {
        return getOpCodesSize() == 0;
    }

    public final boolean isNotEmpty() {
        return getOpCodesSize() != 0;
    }

    public final void clear() {
        this.opCodesSize = 0;
        this.intArgsSize = 0;
        ArraysKt.fill(this.objectArgs, (Object) null, 0, this.objectArgsSize);
        this.objectArgsSize = 0;
    }

    public final void pushOp(Operation operation) {
        if (this.opCodesSize == this.opCodes.length) {
            resizeOpCodes();
        }
        int ints = this.intArgsSize + operation.getInts();
        int length = this.intArgs.length;
        if (ints > length) {
            resizeIntArgs(length, ints);
        }
        int objects = this.objectArgsSize + operation.getObjects();
        int length2 = this.objectArgs.length;
        if (objects > length2) {
            resizeObjectArgs(length2, objects);
        }
        Operation[] operationArr = this.opCodes;
        int i = this.opCodesSize;
        this.opCodesSize = i + 1;
        operationArr[i] = operation;
        this.intArgsSize += operation.getInts();
        this.objectArgsSize += operation.getObjects();
    }

    private final int determineNewSize(int currentSize, int requiredSize) {
        return RangesKt.coerceAtLeast(currentSize + RangesKt.coerceAtMost(currentSize, 1024), requiredSize);
    }

    private final void resizeOpCodes() {
        int iCoerceAtMost = RangesKt.coerceAtMost(this.opCodesSize, 1024);
        int i = this.opCodesSize;
        Operation[] operationArr = new Operation[iCoerceAtMost + i];
        System.arraycopy(this.opCodes, 0, operationArr, 0, i);
        this.opCodes = operationArr;
    }

    private final void ensureIntArgsSizeAtLeast(int requiredSize) {
        int length = this.intArgs.length;
        if (requiredSize > length) {
            resizeIntArgs(length, requiredSize);
        }
    }

    private final void resizeIntArgs(int currentSize, int requiredSize) {
        int[] iArr = new int[determineNewSize(currentSize, requiredSize)];
        ArraysKt.copyInto(this.intArgs, iArr, 0, 0, currentSize);
        this.intArgs = iArr;
    }

    private final void ensureObjectArgsSizeAtLeast(int requiredSize) {
        int length = this.objectArgs.length;
        if (requiredSize > length) {
            resizeObjectArgs(length, requiredSize);
        }
    }

    private final void resizeObjectArgs(int currentSize, int requiredSize) {
        Object[] objArr = new Object[determineNewSize(currentSize, requiredSize)];
        System.arraycopy(this.objectArgs, 0, objArr, 0, currentSize);
        this.objectArgs = objArr;
    }

    public final void push(Operation operation) {
        pushOp(operation);
    }

    private final String exceptionMessageForOperationPushNoScope(Operation operation) {
        return "Cannot push " + operation + " without arguments because it expects " + operation.getInts() + " ints and " + operation.getObjects() + " objects.";
    }

    public final void push(Operation operation, Function1<? super WriteScope, Unit> args) {
        pushOp(operation);
        args.invoke(WriteScope.m3514boximpl(WriteScope.m3515constructorimpl(this)));
        ensureAllArgumentsPushedFor(operation);
    }

    public final void ensureAllArgumentsPushedFor(Operation operation) {
        int i = this.pushedIntMask;
        int ints = operation.getInts();
        if (i == ((ints == 0 ? 0 : -1) >>> (32 - ints))) {
            operation.getObjects();
        }
    }

    private final String exceptionMessageForOperationPushWithScope(Operation operation) {
        StringBuilder sb = new StringBuilder();
        int ints = operation.getInts();
        int i = 0;
        for (int i2 = 0; i2 < ints; i2++) {
            if (((1 << i2) & this.pushedIntMask) == 0) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(operation.intParamName(i2));
                i++;
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        StringBuilder sb2 = new StringBuilder();
        int objects = operation.getObjects();
        int i3 = 0;
        for (int i4 = 0; i4 < objects; i4++) {
            if (((1 << i4) & this.pushedObjectMask) == 0) {
                if (i > 0) {
                    sb2.append(", ");
                }
                sb2.append(operation.mo3466objectParamName31yXWZQ(Operation.ObjectParameter.m3492constructorimpl(i4)));
                i3++;
            }
        }
        String string2 = sb2.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
        return "Error while pushing " + operation + ". Not all arguments were provided. Missing " + i + " int arguments (" + string + ") and " + i3 + " object arguments (" + string2 + ").";
    }

    public final void pop() {
        Operation[] operationArr = this.opCodes;
        int i = this.opCodesSize - 1;
        this.opCodesSize = i;
        Operation operation = operationArr[i];
        operationArr[i] = null;
        int objects = operation.getObjects();
        for (int i2 = 0; i2 < objects; i2++) {
            Object[] objArr = this.objectArgs;
            int i3 = this.objectArgsSize - 1;
            this.objectArgsSize = i3;
            objArr[i3] = null;
        }
        this.intArgsSize -= operation.getInts();
    }

    public final void popInto(Operations other) {
        Operation[] operationArr = this.opCodes;
        int i = this.opCodesSize - 1;
        this.opCodesSize = i;
        Operation operation = operationArr[i];
        operationArr[i] = null;
        other.pushOp(operation);
        Object[] objArr = this.objectArgs;
        Object[] objArr2 = other.objectArgs;
        int objects = other.objectArgsSize - operation.getObjects();
        int objects2 = this.objectArgsSize - operation.getObjects();
        System.arraycopy(objArr, objects2, objArr2, objects, this.objectArgsSize - objects2);
        ArraysKt.fill(this.objectArgs, (Object) null, this.objectArgsSize - operation.getObjects(), this.objectArgsSize);
        ArraysKt.copyInto(this.intArgs, other.intArgs, other.intArgsSize - operation.getInts(), this.intArgsSize - operation.getInts(), this.intArgsSize);
        this.objectArgsSize -= operation.getObjects();
        this.intArgsSize -= operation.getInts();
    }

    public final void forEach(Function1<? super OpIterator, Unit> action) {
        if (isNotEmpty()) {
            OpIterator opIterator = new OpIterator();
            do {
                action.invoke(opIterator);
            } while (opIterator.next());
        }
    }

    private final String indent(String str) {
        return str + "    ";
    }

    private final Operation peekOperation() {
        return this.opCodes[this.opCodesSize - 1];
    }

    private final int topIntIndexOf(int parameter) {
        return (this.intArgsSize - this.opCodes[this.opCodesSize - 1].getInts()) + parameter;
    }

    /* JADX INFO: renamed from: topObjectIndexOf-31yXWZQ, reason: not valid java name */
    private final int m3513topObjectIndexOf31yXWZQ(int parameter) {
        return (this.objectArgsSize - this.opCodes[this.opCodesSize - 1].getObjects()) + parameter;
    }

    /* JADX INFO: compiled from: Operations.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J$\u0010\n\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\rj\u0002`\u000e2\u0006\u0010\u000f\u001a\u00020\rH\u0086\b¢\u0006\u0004\b\u0010\u0010\u0011J8\u0010\u0012\u001a\u00020\u000b2\n\u0010\u0013\u001a\u00060\rj\u0002`\u000e2\u0006\u0010\u0014\u001a\u00020\r2\n\u0010\u0015\u001a\u00060\rj\u0002`\u000e2\u0006\u0010\u0016\u001a\u00020\rH\u0086\b¢\u0006\u0004\b\u0017\u0010\u0018JL\u0010\u0012\u001a\u00020\u000b2\n\u0010\u0013\u001a\u00060\rj\u0002`\u000e2\u0006\u0010\u0014\u001a\u00020\r2\n\u0010\u0015\u001a\u00060\rj\u0002`\u000e2\u0006\u0010\u0016\u001a\u00020\r2\n\u0010\u0019\u001a\u00060\rj\u0002`\u000e2\u0006\u0010\u001a\u001a\u00020\rH\u0086\b¢\u0006\u0004\b\u0017\u0010\u001bJ)\u0010\u001c\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u001d2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u001d0\u001e2\u0006\u0010\u000f\u001a\u0002H\u001d¢\u0006\u0004\b\u001f\u0010 JE\u0010!\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u001d\"\u0004\b\u0001\u0010\"2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u001d0\u001e2\u0006\u0010\u0014\u001a\u0002H\u001d2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\"0\u001e2\u0006\u0010\u0016\u001a\u0002H\"¢\u0006\u0004\b#\u0010$Ja\u0010!\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u001d\"\u0004\b\u0001\u0010\"\"\u0004\b\u0002\u0010%2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u001d0\u001e2\u0006\u0010\u0014\u001a\u0002H\u001d2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\"0\u001e2\u0006\u0010\u0016\u001a\u0002H\"2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H%0\u001e2\u0006\u0010\u001a\u001a\u0002H%¢\u0006\u0004\b&\u0010'J}\u0010!\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u001d\"\u0004\b\u0001\u0010\"\"\u0004\b\u0002\u0010%\"\u0004\b\u0003\u0010(2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u001d0\u001e2\u0006\u0010\u0014\u001a\u0002H\u001d2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\"0\u001e2\u0006\u0010\u0016\u001a\u0002H\"2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H%0\u001e2\u0006\u0010\u001a\u001a\u0002H%2\f\u0010)\u001a\b\u0012\u0004\u0012\u0002H(0\u001e2\u0006\u0010*\u001a\u0002H(¢\u0006\u0004\b+\u0010,J\u0013\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00100\u001a\u00020\rHÖ\u0001J\t\u00101\u001a\u000202HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\t\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u00063"}, d2 = {"Landroidx/compose/runtime/changelist/Operations$WriteScope;", "", "stack", "Landroidx/compose/runtime/changelist/Operations;", "constructor-impl", "(Landroidx/compose/runtime/changelist/Operations;)Landroidx/compose/runtime/changelist/Operations;", "operation", "Landroidx/compose/runtime/changelist/Operation;", "getOperation-impl", "(Landroidx/compose/runtime/changelist/Operations;)Landroidx/compose/runtime/changelist/Operation;", "setInt", "", "parameter", "", "Landroidx/compose/runtime/changelist/IntParameter;", ES6Iterator.VALUE_PROPERTY, "setInt-impl", "(Landroidx/compose/runtime/changelist/Operations;II)V", "setInts", "parameter1", "value1", "parameter2", "value2", "setInts-impl", "(Landroidx/compose/runtime/changelist/Operations;IIII)V", "parameter3", "value3", "(Landroidx/compose/runtime/changelist/Operations;IIIIII)V", "setObject", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/runtime/changelist/Operation$ObjectParameter;", "setObject-DKhxnng", "(Landroidx/compose/runtime/changelist/Operations;ILjava/lang/Object;)V", "setObjects", "U", "setObjects-4uCC6AY", "(Landroidx/compose/runtime/changelist/Operations;ILjava/lang/Object;ILjava/lang/Object;)V", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "setObjects-t7hvbck", "(Landroidx/compose/runtime/changelist/Operations;ILjava/lang/Object;ILjava/lang/Object;ILjava/lang/Object;)V", ExifInterface.LONGITUDE_WEST, "parameter4", "value4", "setObjects-OGa0p1M", "(Landroidx/compose/runtime/changelist/Operations;ILjava/lang/Object;ILjava/lang/Object;ILjava/lang/Object;ILjava/lang/Object;)V", "equals", "", "other", "hashCode", "toString", "", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
    @JvmInline
    public static final class WriteScope {
        private final Operations stack;

        /* JADX INFO: renamed from: box-impl, reason: not valid java name */
        public static final /* synthetic */ WriteScope m3514boximpl(Operations operations) {
            return new WriteScope(operations);
        }

        /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
        public static Operations m3515constructorimpl(Operations operations) {
            return operations;
        }

        /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
        public static boolean m3516equalsimpl(Operations operations, Object obj) {
            return (obj instanceof WriteScope) && Intrinsics.areEqual(operations, ((WriteScope) obj).getStack());
        }

        /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m3517equalsimpl0(Operations operations, Operations operations2) {
            return Intrinsics.areEqual(operations, operations2);
        }

        /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
        public static int m3519hashCodeimpl(Operations operations) {
            return operations.hashCode();
        }

        /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
        public static String m3527toStringimpl(Operations operations) {
            return "WriteScope(stack=" + operations + ')';
        }

        public boolean equals(Object other) {
            return m3516equalsimpl(this.stack, other);
        }

        public int hashCode() {
            return m3519hashCodeimpl(this.stack);
        }

        public String toString() {
            return m3527toStringimpl(this.stack);
        }

        /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
        public final /* synthetic */ Operations getStack() {
            return this.stack;
        }

        private /* synthetic */ WriteScope(Operations operations) {
            this.stack = operations;
        }

        /* JADX INFO: renamed from: setInt-impl, reason: not valid java name */
        public static final void m3520setIntimpl(Operations operations, int i, int i2) {
            operations.intArgs[(operations.intArgsSize - operations.opCodes[operations.opCodesSize - 1].getInts()) + i] = i2;
        }

        /* JADX INFO: renamed from: setInts-impl, reason: not valid java name */
        public static final void m3521setIntsimpl(Operations operations, int i, int i2, int i3, int i4) {
            int ints = operations.intArgsSize - operations.opCodes[operations.opCodesSize - 1].getInts();
            int[] iArr = operations.intArgs;
            iArr[i + ints] = i2;
            iArr[ints + i3] = i4;
        }

        /* JADX INFO: renamed from: setInts-impl, reason: not valid java name */
        public static final void m3522setIntsimpl(Operations operations, int i, int i2, int i3, int i4, int i5, int i6) {
            int ints = operations.intArgsSize - operations.opCodes[operations.opCodesSize - 1].getInts();
            int[] iArr = operations.intArgs;
            iArr[i + ints] = i2;
            iArr[i3 + ints] = i4;
            iArr[ints + i5] = i6;
        }

        /* JADX INFO: renamed from: setObject-DKhxnng, reason: not valid java name */
        public static final <T> void m3523setObjectDKhxnng(Operations operations, int i, T t) {
            operations.objectArgs[(operations.objectArgsSize - operations.opCodes[operations.opCodesSize - 1].getObjects()) + i] = t;
        }

        /* JADX INFO: renamed from: setObjects-4uCC6AY, reason: not valid java name */
        public static final <T, U> void m3524setObjects4uCC6AY(Operations operations, int i, T t, int i2, U u) {
            int objects = operations.objectArgsSize - operations.opCodes[operations.opCodesSize - 1].getObjects();
            Object[] objArr = operations.objectArgs;
            objArr[i + objects] = t;
            objArr[objects + i2] = u;
        }

        /* JADX INFO: renamed from: setObjects-t7hvbck, reason: not valid java name */
        public static final <T, U, V> void m3526setObjectst7hvbck(Operations operations, int i, T t, int i2, U u, int i3, V v) {
            int objects = operations.objectArgsSize - operations.opCodes[operations.opCodesSize - 1].getObjects();
            Object[] objArr = operations.objectArgs;
            objArr[i + objects] = t;
            objArr[i2 + objects] = u;
            objArr[objects + i3] = v;
        }

        /* JADX INFO: renamed from: setObjects-OGa0p1M, reason: not valid java name */
        public static final <T, U, V, W> void m3525setObjectsOGa0p1M(Operations operations, int i, T t, int i2, U u, int i3, V v, int i4, W w) {
            int objects = operations.objectArgsSize - operations.opCodes[operations.opCodesSize - 1].getObjects();
            Object[] objArr = operations.objectArgs;
            objArr[i + objects] = t;
            objArr[i2 + objects] = u;
            objArr[i3 + objects] = v;
            objArr[objects + i4] = w;
        }

        /* JADX INFO: renamed from: getOperation-impl, reason: not valid java name */
        public static final Operation m3518getOperationimpl(Operations operations) {
            return operations.opCodes[operations.opCodesSize - 1];
        }
    }

    /* JADX INFO: compiled from: Operations.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\b\u001a\u00020\tJ\u0014\u0010\u000e\u001a\u00020\u00052\n\u0010\u000f\u001a\u00060\u0005j\u0002`\u0010H\u0016J#\u0010\u0011\u001a\u0002H\u0012\"\u0004\b\u0000\u0010\u00122\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u0006\u0010\u0016\u001a\u00020\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Landroidx/compose/runtime/changelist/Operations$OpIterator;", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "<init>", "(Landroidx/compose/runtime/changelist/Operations;)V", "opIdx", "", "intIdx", "objIdx", "next", "", "operation", "Landroidx/compose/runtime/changelist/Operation;", "getOperation", "()Landroidx/compose/runtime/changelist/Operation;", "getInt", "parameter", "Landroidx/compose/runtime/changelist/IntParameter;", "getObject", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/runtime/changelist/Operation$ObjectParameter;", "getObject-31yXWZQ", "(I)Ljava/lang/Object;", "currentOperationDebugString", "", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public final class OpIterator implements OperationArgContainer {
        private int intIdx;
        private int objIdx;
        private int opIdx;

        public OpIterator() {
        }

        public final boolean next() {
            if (this.opIdx >= Operations.this.opCodesSize) {
                return false;
            }
            Operation operation = getOperation();
            this.intIdx += operation.getInts();
            this.objIdx += operation.getObjects();
            int i = this.opIdx + 1;
            this.opIdx = i;
            return i < Operations.this.opCodesSize;
        }

        public final Operation getOperation() {
            return Operations.this.opCodes[this.opIdx];
        }

        @Override // androidx.compose.runtime.changelist.OperationArgContainer
        public int getInt(int parameter) {
            return Operations.this.intArgs[this.intIdx + parameter];
        }

        @Override // androidx.compose.runtime.changelist.OperationArgContainer
        /* JADX INFO: renamed from: getObject-31yXWZQ */
        public <T> T mo3512getObject31yXWZQ(int parameter) {
            return (T) Operations.this.objectArgs[this.objIdx + parameter];
        }

        public final String currentOperationDebugString() {
            String str = "operation[" + this.opIdx + "] = " + Operations.this.currentOpToDebugString(this, "");
            Intrinsics.checkNotNullExpressionValue(str, "toString(...)");
            return str;
        }
    }

    @Deprecated(message = "toString() will return the default implementation from Any. Did you mean to use toDebugString()?", replaceWith = @ReplaceWith(expression = "toDebugString()", imports = {}))
    public String toString() {
        return super.toString();
    }

    @Override // androidx.compose.runtime.changelist.OperationsDebugStringFormattable
    public String toDebugString(String linePrefix) {
        StringBuilder sb = new StringBuilder();
        if (isNotEmpty()) {
            OpIterator opIterator = new OpIterator();
            int i = 0;
            while (true) {
                sb.append(linePrefix);
                int i2 = i + 1;
                sb.append(i);
                sb.append(". ");
                sb.append(currentOpToDebugString(opIterator, linePrefix));
                Intrinsics.checkNotNullExpressionValue(sb, "append(...)");
                sb.append('\n');
                Intrinsics.checkNotNullExpressionValue(sb, "append(...)");
                if (!opIterator.next()) {
                    break;
                }
                i = i2;
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String currentOpToDebugString(OpIterator opIterator, String str) {
        Operation operation = opIterator.getOperation();
        if (operation.getInts() == 0 && operation.getObjects() == 0) {
            return operation.getName();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(operation.getName());
        sb.append('(');
        String strIndent = indent(str);
        int ints = operation.getInts();
        boolean z = true;
        for (int i = 0; i < ints; i++) {
            String strIntParamName = operation.intParamName(i);
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append('\n');
            Intrinsics.checkNotNullExpressionValue(sb, "append(...)");
            sb.append(strIndent);
            sb.append(strIntParamName);
            sb.append(" = ");
            sb.append(opIterator.getInt(i));
        }
        int objects = operation.getObjects();
        for (int i2 = 0; i2 < objects; i2++) {
            int iM3492constructorimpl = Operation.ObjectParameter.m3492constructorimpl(i2);
            String strMo3466objectParamName31yXWZQ = operation.mo3466objectParamName31yXWZQ(iM3492constructorimpl);
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append('\n');
            Intrinsics.checkNotNullExpressionValue(sb, "append(...)");
            sb.append(strIndent);
            sb.append(strMo3466objectParamName31yXWZQ);
            sb.append(" = ");
            sb.append(formatOpArgumentToString(opIterator.mo3512getObject31yXWZQ(iM3492constructorimpl), strIndent));
        }
        sb.append('\n');
        Intrinsics.checkNotNullExpressionValue(sb, "append(...)");
        sb.append(str);
        sb.append(")");
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String formatOpArgumentToString(Object obj, String str) {
        if (obj == null) {
            return AbstractJsonLexerKt.NULL;
        }
        if (obj instanceof Object[]) {
            return toCollectionString(ArraysKt.asIterable((Object[]) obj), str);
        }
        if (obj instanceof int[]) {
            return toCollectionString(ArraysKt.asIterable((int[]) obj), str);
        }
        if (obj instanceof long[]) {
            return toCollectionString(ArraysKt.asIterable((long[]) obj), str);
        }
        if (obj instanceof float[]) {
            return toCollectionString(ArraysKt.asIterable((float[]) obj), str);
        }
        if (obj instanceof double[]) {
            return toCollectionString(ArraysKt.asIterable((double[]) obj), str);
        }
        if (obj instanceof Iterable) {
            return toCollectionString((Iterable) obj, str);
        }
        return obj instanceof OperationsDebugStringFormattable ? ((OperationsDebugStringFormattable) obj).toDebugString(str) : obj.toString();
    }

    private final <T> String toCollectionString(Iterable<? extends T> iterable, final String str) {
        return CollectionsKt.joinToString$default(iterable, ", ", "[", "]", 0, null, new Function1() { // from class: androidx.compose.runtime.changelist.Operations$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.formatOpArgumentToString(obj, str);
            }
        }, 24, null);
    }

    public final void drain(Function1<? super OpIterator, Unit> sink) {
        if (isNotEmpty()) {
            OpIterator opIterator = new OpIterator();
            do {
                sink.invoke(opIterator);
            } while (opIterator.next());
        }
        clear();
    }

    public final void executeAndFlushAllPendingOperations(Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
        if (isNotEmpty()) {
            OpIterator opIterator = new OpIterator();
            while (true) {
                Applier<?> applier2 = applier;
                SlotWriter slotWriter = slots;
                RememberManager rememberManager2 = rememberManager;
                OperationErrorContext operationErrorContext = errorContext;
                opIterator.getOperation().executeWithComposeStackTrace(opIterator, applier2, slotWriter, rememberManager2, operationErrorContext);
                if (!opIterator.next()) {
                    break;
                }
                applier = applier2;
                slots = slotWriter;
                rememberManager = rememberManager2;
                errorContext = operationErrorContext;
            }
        }
        clear();
    }
}
