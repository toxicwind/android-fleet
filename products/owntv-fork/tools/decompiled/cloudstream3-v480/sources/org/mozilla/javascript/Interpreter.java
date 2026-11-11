package org.mozilla.javascript;

import com.google.common.base.Ascii;
import j$.util.Objects;
import j$.util.function.Function$CC;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.mozilla.javascript.ast.ScriptNode;
import org.mozilla.javascript.debug.DebugFrame;
import qrcode.color.Colors;

/* JADX INFO: loaded from: classes8.dex */
public final class Interpreter extends Icode implements Evaluator {
    private static final int EXCEPTION_COST = 100;
    static final int EXCEPTION_HANDLER_SLOT = 2;
    static final int EXCEPTION_LOCAL_SLOT = 4;
    static final int EXCEPTION_SCOPE_SLOT = 5;
    static final int EXCEPTION_SLOT_SIZE = 6;
    static final int EXCEPTION_TRY_END_SLOT = 1;
    static final int EXCEPTION_TRY_START_SLOT = 0;
    static final int EXCEPTION_TYPE_SLOT = 3;
    private static final int INVOCATION_COST = 100;
    private static final Object undefined = Undefined.instance;
    InterpreterData itsData;

    static void dumpICode(InterpreterData interpreterData) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class CallFrame implements Cloneable, Serializable {
        private static final long serialVersionUID = -2843792508994958978L;
        final DebugFrame debuggerFrame;
        final int emptyStackTop;
        final InterpretedFunction fnOrScript;
        int frameIndex;
        boolean frozen;
        final InterpreterData idata;
        boolean isContinuationsTopFrame;
        final int localShift;
        CallFrame parentFrame;
        int pc;
        int pcPrevBranch;
        int pcSourceLineStart;
        Object result;
        double resultDbl;
        double[] sDbl;
        int savedCallOp;
        int savedStackTop;
        Scriptable scope;
        Object[] stack;
        int[] stackAttributes;
        final Scriptable thisObj;
        Object throwable;
        final boolean useActivation;
        final CallFrame varSource;

        CallFrame(Context context, Scriptable scriptable, InterpretedFunction interpretedFunction, CallFrame callFrame) {
            InterpreterData interpreterData = interpretedFunction.idata;
            this.idata = interpreterData;
            DebugFrame frame = context.debugger != null ? context.debugger.getFrame(context, interpreterData) : null;
            this.debuggerFrame = frame;
            this.useActivation = frame != null || interpreterData.itsNeedsActivation;
            int i = (interpreterData.itsMaxVars + interpreterData.itsMaxLocals) - 1;
            this.emptyStackTop = i;
            this.fnOrScript = interpretedFunction;
            this.varSource = this;
            this.localShift = interpreterData.itsMaxVars;
            this.thisObj = scriptable;
            this.parentFrame = callFrame;
            int i2 = callFrame != null ? callFrame.frameIndex + 1 : 0;
            this.frameIndex = i2;
            if (i2 > context.getMaximumInterpreterStackDepth()) {
                throw Context.reportRuntimeError("Exceeded maximum stack depth");
            }
            this.result = Undefined.instance;
            this.pcSourceLineStart = interpreterData.firstLinePC;
            this.savedStackTop = i;
        }

        void initializeArgs(Context context, Scriptable scriptable, Object[] objArr, double[] dArr, int i, int i2, Scriptable scriptable2) {
            Object[] objArr2;
            double[] dArr2;
            int i3;
            InterpreterData interpreterData;
            Object[] objArr3;
            if (this.useActivation) {
                if (dArr != null) {
                    objArr = Interpreter.getArgsArray(objArr, dArr, i, i2);
                }
                dArr2 = null;
                objArr2 = objArr;
                i3 = 0;
            } else {
                objArr2 = objArr;
                dArr2 = dArr;
                i3 = i;
            }
            if (this.idata.itsFunctionType != 0) {
                this.scope = this.fnOrScript.getParentScope();
                if (this.useActivation) {
                    int i4 = this.idata.itsFunctionType;
                    InterpretedFunction interpretedFunction = this.fnOrScript;
                    if (i4 == 4) {
                        this.scope = ScriptRuntime.createArrowFunctionActivation(interpretedFunction, context, this.scope, objArr2, this.idata.isStrict, this.idata.argsHasRest, scriptable2);
                    } else {
                        this.scope = ScriptRuntime.createFunctionActivation(interpretedFunction, context, this.scope, objArr2, this.idata.isStrict, this.idata.argsHasRest, scriptable2);
                    }
                }
            } else {
                this.scope = scriptable;
                InterpretedFunction interpretedFunction2 = this.fnOrScript;
                ScriptRuntime.initScript(interpretedFunction2, this.thisObj, context, scriptable, interpretedFunction2.idata.evalScriptFlag);
            }
            if (this.idata.itsNestedFunctions != null) {
                if (this.idata.itsFunctionType != 0 && !this.idata.itsNeedsActivation) {
                    Kit.codeBug();
                }
                for (int i5 = 0; i5 < this.idata.itsNestedFunctions.length; i5++) {
                    if (this.idata.itsNestedFunctions[i5].itsFunctionType == 1) {
                        Interpreter.initFunction(context, this.scope, this.fnOrScript, i5);
                    }
                }
            }
            int i6 = this.idata.itsMaxFrameArray;
            if (i6 != this.emptyStackTop + this.idata.itsMaxStack + 1) {
                Kit.codeBug();
            }
            this.stack = new Object[i6];
            this.stackAttributes = new int[i6];
            this.sDbl = new double[i6];
            int paramAndVarCount = this.idata.getParamAndVarCount();
            int i7 = 0;
            while (true) {
                interpreterData = this.idata;
                if (i7 >= paramAndVarCount) {
                    break;
                }
                if (interpreterData.getParamOrVarConst(i7)) {
                    this.stackAttributes[i7] = 13;
                }
                i7++;
            }
            int i8 = interpreterData.argCount;
            if (i8 > i2) {
                i8 = i2;
            }
            System.arraycopy(objArr2, i3, this.stack, 0, i8);
            if (dArr2 != null) {
                System.arraycopy(dArr2, i3, this.sDbl, 0, i8);
            }
            while (i8 != this.idata.itsMaxVars) {
                this.stack[i8] = Undefined.instance;
                i8++;
            }
            if (this.idata.argsHasRest) {
                int i9 = this.idata.argCount - 1;
                if (i2 >= this.idata.argCount) {
                    int i10 = i2 - i9;
                    objArr3 = new Object[i10];
                    int i11 = i3 + i9;
                    for (int i12 = 0; i12 != i10; i12++) {
                        Object objWrapNumber = objArr2[i11];
                        if (objWrapNumber == UniqueTag.DOUBLE_MARK) {
                            objWrapNumber = ScriptRuntime.wrapNumber(dArr2[i11]);
                        }
                        objArr3[i12] = objWrapNumber;
                        i11++;
                    }
                } else {
                    objArr3 = ScriptRuntime.emptyArgs;
                }
                this.stack[i9] = context.newArray(this.scope, objArr3);
            }
        }

        Object[] ensureStackLength(int i) {
            Object[] objArr = this.stack;
            if (i > objArr.length) {
                this.stack = Arrays.copyOf(objArr, i);
                this.sDbl = Arrays.copyOf(this.sDbl, i);
            }
            return this.stack;
        }

        CallFrame cloneFrozen() {
            if (!this.frozen) {
                Kit.codeBug();
            }
            try {
                CallFrame callFrame = (CallFrame) clone();
                callFrame.stack = (Object[]) this.stack.clone();
                callFrame.stackAttributes = (int[]) this.stackAttributes.clone();
                callFrame.sDbl = (double[]) this.sDbl.clone();
                callFrame.frozen = false;
                return callFrame;
            } catch (CloneNotSupportedException unused) {
                throw new IllegalStateException();
            }
        }

        public boolean equals(final Object obj) {
            if (!(obj instanceof CallFrame)) {
                return false;
            }
            Context contextEnter = Context.enter();
            try {
                if (ScriptRuntime.hasTopCall(contextEnter)) {
                    boolean zBooleanValue = equalsInTopScope(obj).booleanValue();
                    if (contextEnter != null) {
                        contextEnter.close();
                    }
                    return zBooleanValue;
                }
                Scriptable topLevelScope = ScriptableObject.getTopLevelScope(this.scope);
                boolean zBooleanValue2 = ((Boolean) ScriptRuntime.doTopCall(new Callable() { // from class: org.mozilla.javascript.Interpreter$CallFrame$$ExternalSyntheticLambda1
                    @Override // org.mozilla.javascript.Callable
                    public final Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
                        return this.f$0.m10876lambda$equals$0$orgmozillajavascriptInterpreter$CallFrame(obj, context, scriptable, scriptable2, objArr);
                    }
                }, contextEnter, topLevelScope, topLevelScope, ScriptRuntime.emptyArgs, isStrictTopFrame())).booleanValue();
                if (contextEnter != null) {
                    contextEnter.close();
                }
                return zBooleanValue2;
            } catch (Throwable th) {
                if (contextEnter == null) {
                    throw th;
                }
                try {
                    contextEnter.close();
                    throw th;
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                    throw th;
                }
            }
        }

        /* JADX INFO: renamed from: lambda$equals$0$org-mozilla-javascript-Interpreter$CallFrame, reason: not valid java name */
        /* synthetic */ Object m10876lambda$equals$0$orgmozillajavascriptInterpreter$CallFrame(Object obj, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
            return equalsInTopScope(obj);
        }

        public int hashCode() {
            int iIcodeHashCode = 0;
            int i = 0;
            while (true) {
                iIcodeHashCode = (((iIcodeHashCode * 31) + this.pc) * 31) + this.idata.icodeHashCode();
                this = this.parentFrame;
                if (this == null) {
                    break;
                }
                int i2 = i + 1;
                if (i >= 8) {
                    break;
                }
                i = i2;
            }
            return iIcodeHashCode;
        }

        private Boolean equalsInTopScope(final Object obj) {
            return (Boolean) EqualObjectGraphs.withThreadLocal(new java.util.function.Function() { // from class: org.mozilla.javascript.Interpreter$CallFrame$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public /* synthetic */ java.util.function.Function andThen(java.util.function.Function function) {
                    return Function$CC.$default$andThen(this, function);
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj2) {
                    return this.f$0.m10877xc275b982(obj, (EqualObjectGraphs) obj2);
                }

                public /* synthetic */ java.util.function.Function compose(java.util.function.Function function) {
                    return Function$CC.$default$compose(this, function);
                }
            });
        }

        /* JADX INFO: renamed from: lambda$equalsInTopScope$1$org-mozilla-javascript-Interpreter$CallFrame, reason: not valid java name */
        /* synthetic */ Boolean m10877xc275b982(Object obj, EqualObjectGraphs equalObjectGraphs) {
            return equals(this, (CallFrame) obj, equalObjectGraphs);
        }

        private boolean isStrictTopFrame() {
            while (true) {
                CallFrame callFrame = this.parentFrame;
                if (callFrame == null) {
                    return this.idata.isStrict;
                }
                this = callFrame;
            }
        }

        private static Boolean equals(CallFrame callFrame, CallFrame callFrame2, EqualObjectGraphs equalObjectGraphs) {
            while (callFrame != callFrame2) {
                if (callFrame == null || callFrame2 == null) {
                    return Boolean.FALSE;
                }
                if (!callFrame.fieldsEqual(callFrame2, equalObjectGraphs)) {
                    return Boolean.FALSE;
                }
                callFrame = callFrame.parentFrame;
                callFrame2 = callFrame2.parentFrame;
            }
            return Boolean.TRUE;
        }

        private boolean fieldsEqual(CallFrame callFrame, EqualObjectGraphs equalObjectGraphs) {
            return this.frameIndex == callFrame.frameIndex && this.pc == callFrame.pc && Interpreter.compareIdata(this.idata, callFrame.idata) && equalObjectGraphs.equalGraphs(this.varSource.stack, callFrame.varSource.stack) && Arrays.equals(this.varSource.sDbl, callFrame.varSource.sDbl) && equalObjectGraphs.equalGraphs(this.thisObj, callFrame.thisObj) && equalObjectGraphs.equalGraphs(this.fnOrScript, callFrame.fnOrScript) && equalObjectGraphs.equalGraphs(this.scope, callFrame.scope);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean compareIdata(InterpreterData interpreterData, InterpreterData interpreterData2) {
        return interpreterData == interpreterData2 || Objects.equals(getRawSource(interpreterData), getRawSource(interpreterData2));
    }

    private static final class ContinuationJump implements Serializable {
        private static final long serialVersionUID = 7687739156004308247L;
        CallFrame branchFrame;
        CallFrame capturedFrame;
        Object result;
        double resultDbl;

        ContinuationJump(NativeContinuation nativeContinuation, CallFrame callFrame) {
            CallFrame callFrame2;
            CallFrame callFrame3 = (CallFrame) nativeContinuation.getImplementation();
            this.capturedFrame = callFrame3;
            if (callFrame3 == null || callFrame == null) {
                this.branchFrame = null;
                return;
            }
            int i = callFrame3.frameIndex - callFrame.frameIndex;
            if (i != 0) {
                if (i < 0) {
                    callFrame2 = this.capturedFrame;
                    i = -i;
                } else {
                    callFrame = callFrame3;
                    callFrame2 = callFrame;
                }
                do {
                    callFrame = callFrame.parentFrame;
                    i--;
                } while (i != 0);
                if (callFrame.frameIndex != callFrame2.frameIndex) {
                    Kit.codeBug();
                }
                callFrame = callFrame2;
                callFrame3 = callFrame;
            }
            while (!Objects.equals(callFrame3, callFrame) && callFrame3 != null) {
                callFrame3 = callFrame3.parentFrame;
                callFrame = callFrame.parentFrame;
            }
            this.branchFrame = callFrame3;
            if (callFrame3 == null || callFrame3.frozen) {
                return;
            }
            Kit.codeBug();
        }
    }

    private static CallFrame captureFrameForGenerator(CallFrame callFrame) {
        callFrame.frozen = true;
        CallFrame callFrameCloneFrozen = callFrame.cloneFrozen();
        callFrame.frozen = false;
        callFrameCloneFrozen.parentFrame = null;
        callFrameCloneFrozen.frameIndex = 0;
        return callFrameCloneFrozen;
    }

    @Override // org.mozilla.javascript.Evaluator
    public Object compile(CompilerEnvirons compilerEnvirons, ScriptNode scriptNode, String str, boolean z) {
        InterpreterData interpreterDataCompile = new CodeGenerator().compile(compilerEnvirons, scriptNode, str, z);
        this.itsData = interpreterDataCompile;
        return interpreterDataCompile;
    }

    @Override // org.mozilla.javascript.Evaluator
    public Script createScriptObject(Object obj, Object obj2) {
        if (obj != this.itsData) {
            Kit.codeBug();
        }
        return InterpretedFunction.createScript(this.itsData, obj2);
    }

    @Override // org.mozilla.javascript.Evaluator
    public void setEvalScriptFlag(Script script) {
        ((InterpretedFunction) script).idata.evalScriptFlag = true;
    }

    @Override // org.mozilla.javascript.Evaluator
    public Function createFunctionObject(Context context, Scriptable scriptable, Object obj, Object obj2) {
        if (obj != this.itsData) {
            Kit.codeBug();
        }
        return InterpretedFunction.createFunction(context, scriptable, this.itsData, obj2);
    }

    private static int getShort(byte[] bArr, int i) {
        return (bArr[i + 1] & 255) | (bArr[i] << 8);
    }

    private static int getIndex(byte[] bArr, int i) {
        return (bArr[i + 1] & 255) | ((bArr[i] & 255) << 8);
    }

    private static int getInt(byte[] bArr, int i) {
        return (bArr[i + 3] & 255) | (bArr[i] << Ascii.CAN) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
    }

    private static int getExceptionHandler(CallFrame callFrame, boolean z) {
        int[] iArr = callFrame.idata.itsExceptionTable;
        int i = -1;
        if (iArr == null) {
            return -1;
        }
        int i2 = callFrame.pc - 1;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 != iArr.length; i5 += 6) {
            int i6 = iArr[i5];
            int i7 = iArr[i5 + 1];
            if (i6 <= i2 && i2 < i7 && (!z || iArr[i5 + 3] == 1)) {
                if (i < 0) {
                    i = i5;
                    i4 = i6;
                    i3 = i7;
                } else if (i3 >= i7) {
                    if (i4 > i6) {
                        Kit.codeBug();
                    }
                    if (i3 == i7) {
                        Kit.codeBug();
                    }
                    i = i5;
                    i4 = i6;
                    i3 = i7;
                }
            }
        }
        return i;
    }

    /* JADX WARN: Code duplicated, block: B:49:0x005c A[RETURN] */
    private static int bytecodeSpan(int i) {
        if (i != -84 && i != -83 && i != -73 && i != -72 && i != -61 && i != -28 && i != 55) {
            if (i == 62) {
                return 2;
            }
            if (i != 78) {
                if (i != -26 && i != -25) {
                    if (i != 5 && i != 6 && i != 7) {
                        switch (i) {
                            case -70:
                            case -69:
                                break;
                            case -68:
                                return 2;
                            default:
                                switch (i) {
                                    case -56:
                                    case -55:
                                        return 2;
                                    case -54:
                                        return 5;
                                    case -53:
                                        return 3;
                                    case -52:
                                        return 2;
                                    default:
                                        switch (i) {
                                            case -47:
                                                return 5;
                                            case -46:
                                                return 3;
                                            default:
                                                switch (i) {
                                                    case -34:
                                                        break;
                                                    case -33:
                                                        return 5;
                                                    case Colors.LIGHT_YELLOW /* -32 */:
                                                    case -31:
                                                        return 3;
                                                    default:
                                                        switch (i) {
                                                            case -11:
                                                            case -10:
                                                            case -9:
                                                            case -8:
                                                            case -7:
                                                                return 2;
                                                            case -6:
                                                                break;
                                                            default:
                                                                if (validBytecode(i)) {
                                                                    return 1;
                                                                }
                                                                throw Kit.codeBug();
                                                        }
                                                        break;
                                                }
                                            case -45:
                                                return 2;
                                        }
                                        break;
                                }
                                break;
                        }
                    }
                } else {
                    return 5;
                }
            }
        }
        return 3;
    }

    static int[] getLineNumbers(InterpreterData interpreterData) {
        HashSet hashSet = new HashSet();
        byte[] bArr = interpreterData.itsICode;
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i2 != length) {
            byte b = bArr[i2];
            int iBytecodeSpan = bytecodeSpan(b);
            if (b == -31) {
                if (iBytecodeSpan != 3) {
                    Kit.codeBug();
                }
                hashSet.add(Integer.valueOf(getIndex(bArr, i2 + 1)));
            }
            i2 += iBytecodeSpan;
        }
        int[] iArr = new int[hashSet.size()];
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            iArr[i] = ((Integer) it.next()).intValue();
            i++;
        }
        return iArr;
    }

    @Override // org.mozilla.javascript.Evaluator
    public void captureStackInfo(RhinoException rhinoException) {
        CallFrame[] callFrameArr;
        Context currentContext = Context.getCurrentContext();
        if (currentContext == null || currentContext.lastInterpreterFrame == null) {
            rhinoException.interpreterStackInfo = null;
            rhinoException.interpreterLineData = null;
            return;
        }
        if (currentContext.previousInterpreterInvocations == null || currentContext.previousInterpreterInvocations.size() == 0) {
            callFrameArr = new CallFrame[1];
        } else {
            int size = currentContext.previousInterpreterInvocations.size();
            if (currentContext.previousInterpreterInvocations.peek() == currentContext.lastInterpreterFrame) {
                size--;
            }
            callFrameArr = new CallFrame[size + 1];
            ArrayList arrayList = new ArrayList(currentContext.previousInterpreterInvocations);
            Collections.reverse(arrayList);
            arrayList.toArray(callFrameArr);
        }
        callFrameArr[callFrameArr.length - 1] = (CallFrame) currentContext.lastInterpreterFrame;
        int i = 0;
        for (int i2 = 0; i2 != callFrameArr.length; i2++) {
            i += callFrameArr[i2].frameIndex + 1;
        }
        int[] iArr = new int[i];
        int length = callFrameArr.length;
        while (length != 0) {
            length--;
            for (CallFrame callFrame = callFrameArr[length]; callFrame != null; callFrame = callFrame.parentFrame) {
                i--;
                iArr[i] = callFrame.pcSourceLineStart;
            }
        }
        if (i != 0) {
            Kit.codeBug();
        }
        rhinoException.interpreterStackInfo = callFrameArr;
        rhinoException.interpreterLineData = iArr;
    }

    @Override // org.mozilla.javascript.Evaluator
    public String getSourcePositionFromStack(Context context, int[] iArr) {
        CallFrame callFrame = (CallFrame) context.lastInterpreterFrame;
        InterpreterData interpreterData = callFrame.idata;
        if (callFrame.pcSourceLineStart >= 0) {
            iArr[0] = getIndex(interpreterData.itsICode, callFrame.pcSourceLineStart);
        } else {
            iArr[0] = 0;
        }
        return interpreterData.itsSourceFile;
    }

    @Override // org.mozilla.javascript.Evaluator
    public String getPatchedStack(RhinoException rhinoException, String str) {
        char cCharAt;
        StringBuilder sb = new StringBuilder(str.length() + 1000);
        String systemProperty = SecurityUtilities.getSystemProperty("line.separator");
        CallFrame[] callFrameArr = (CallFrame[]) rhinoException.interpreterStackInfo;
        int[] iArr = rhinoException.interpreterLineData;
        int length = callFrameArr.length;
        int length2 = iArr.length;
        int i = 0;
        while (length != 0) {
            length--;
            int iIndexOf = str.indexOf("org.mozilla.javascript.Interpreter.interpretLoop", i);
            if (iIndexOf < 0) {
                break;
            }
            int i2 = iIndexOf + 48;
            while (i2 != str.length() && (cCharAt = str.charAt(i2)) != '\n' && cCharAt != '\r') {
                i2++;
            }
            sb.append((CharSequence) str, i, i2);
            for (CallFrame callFrame = callFrameArr[length]; callFrame != null; callFrame = callFrame.parentFrame) {
                if (length2 == 0) {
                    Kit.codeBug();
                }
                length2--;
                InterpreterData interpreterData = callFrame.idata;
                sb.append(systemProperty);
                sb.append("\tat script");
                if (interpreterData.itsName != null && interpreterData.itsName.length() != 0) {
                    sb.append('.');
                    sb.append(interpreterData.itsName);
                }
                sb.append('(');
                sb.append(interpreterData.itsSourceFile);
                int i3 = iArr[length2];
                if (i3 >= 0) {
                    sb.append(AbstractJsonLexerKt.COLON);
                    sb.append(getIndex(interpreterData.itsICode, i3));
                }
                sb.append(')');
            }
            i = i2;
        }
        sb.append(str.substring(i));
        return sb.toString();
    }

    @Override // org.mozilla.javascript.Evaluator
    public List<String> getScriptStack(RhinoException rhinoException) {
        ScriptStackElement[][] scriptStackElements = getScriptStackElements(rhinoException);
        ArrayList arrayList = new ArrayList(scriptStackElements.length);
        String systemProperty = SecurityUtilities.getSystemProperty("line.separator");
        for (ScriptStackElement[] scriptStackElementArr : scriptStackElements) {
            StringBuilder sb = new StringBuilder();
            for (ScriptStackElement scriptStackElement : scriptStackElementArr) {
                scriptStackElement.renderJavaStyle(sb);
                sb.append(systemProperty);
            }
            arrayList.add(sb.toString());
        }
        return arrayList;
    }

    public ScriptStackElement[][] getScriptStackElements(RhinoException rhinoException) {
        if (rhinoException.interpreterStackInfo == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        CallFrame[] callFrameArr = (CallFrame[]) rhinoException.interpreterStackInfo;
        int[] iArr = rhinoException.interpreterLineData;
        int length = callFrameArr.length;
        int length2 = iArr.length;
        while (length != 0) {
            length--;
            CallFrame callFrame = callFrameArr[length];
            ArrayList arrayList2 = new ArrayList();
            while (callFrame != null) {
                if (length2 == 0) {
                    Kit.codeBug();
                }
                length2--;
                InterpreterData interpreterData = callFrame.idata;
                String str = interpreterData.itsSourceFile;
                int i = iArr[length2];
                int index = i >= 0 ? getIndex(interpreterData.itsICode, i) : -1;
                String str2 = (interpreterData.itsName == null || interpreterData.itsName.length() == 0) ? null : interpreterData.itsName;
                callFrame = callFrame.parentFrame;
                arrayList2.add(new ScriptStackElement(str, str2, index));
            }
            arrayList.add((ScriptStackElement[]) arrayList2.toArray(new ScriptStackElement[0]));
        }
        return (ScriptStackElement[][]) arrayList.toArray(new ScriptStackElement[arrayList.size()][]);
    }

    static String getRawSource(InterpreterData interpreterData) {
        if (interpreterData.rawSource == null) {
            return null;
        }
        return interpreterData.rawSource.substring(interpreterData.rawSourceStart, interpreterData.rawSourceEnd);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void initFunction(Context context, Scriptable scriptable, InterpretedFunction interpretedFunction, int i) {
        InterpretedFunction interpretedFunctionCreateFunction = InterpretedFunction.createFunction(context, scriptable, interpretedFunction, i);
        ScriptRuntime.initFunction(context, scriptable, interpretedFunctionCreateFunction, interpretedFunctionCreateFunction.idata.itsFunctionType, interpretedFunction.idata.evalScriptFlag);
    }

    static Object interpret(InterpretedFunction interpretedFunction, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!ScriptRuntime.hasTopCall(context)) {
            Kit.codeBug();
        }
        if (context.interpreterSecurityDomain != interpretedFunction.securityDomain) {
            Object obj = context.interpreterSecurityDomain;
            context.interpreterSecurityDomain = interpretedFunction.securityDomain;
            try {
                return interpretedFunction.securityController.callWithDomain(interpretedFunction.securityDomain, context, interpretedFunction, scriptable, scriptable2, objArr);
            } finally {
                context.interpreterSecurityDomain = obj;
            }
        }
        CallFrame callFrameInitFrame = initFrame(context, scriptable, scriptable2, interpretedFunction.getHomeObject(), objArr, null, 0, objArr.length, interpretedFunction, null);
        callFrameInitFrame.isContinuationsTopFrame = context.isContinuationsTopCall;
        context.isContinuationsTopCall = false;
        return interpretLoop(context, callFrameInitFrame, null);
    }

    static class GeneratorState {
        int operation;
        RuntimeException returnedException;
        Object value;

        GeneratorState(int i, Object obj) {
            this.operation = i;
            this.value = obj;
        }
    }

    public static Object resumeGenerator(Context context, Scriptable scriptable, int i, Object obj, Object obj2) {
        CallFrame callFrame = (CallFrame) obj;
        GeneratorState generatorState = new GeneratorState(i, obj2);
        if (i == 2) {
            try {
                return interpretLoop(context, callFrame, generatorState);
            } catch (RuntimeException e) {
                if (e != obj2) {
                    throw e;
                }
                return Undefined.instance;
            }
        }
        Object objInterpretLoop = interpretLoop(context, callFrame, generatorState);
        if (generatorState.returnedException == null) {
            return objInterpretLoop;
        }
        throw generatorState.returnedException;
    }

    public static Object restartContinuation(NativeContinuation nativeContinuation, Context context, Scriptable scriptable, Object[] objArr) {
        Object obj;
        if (!ScriptRuntime.hasTopCall(context)) {
            return ScriptRuntime.doTopCall(nativeContinuation, context, scriptable, null, objArr, context.isTopLevelStrict);
        }
        if (objArr.length == 0) {
            obj = Undefined.instance;
        } else {
            obj = objArr[0];
        }
        if (((CallFrame) nativeContinuation.getImplementation()) == null) {
            return obj;
        }
        ContinuationJump continuationJump = new ContinuationJump(nativeContinuation, null);
        continuationJump.result = obj;
        return interpretLoop(context, null, continuationJump);
    }

    private static class NewState {
        private NewState() {
        }
    }

    private static final class StateContinue extends NewState {
        private final CallFrame frame;

        /* JADX INFO: renamed from: -$$Nest$fgetframe, reason: not valid java name */
        static /* bridge */ /* synthetic */ CallFrame m10882$$Nest$fgetframe(StateContinue stateContinue) {
            return stateContinue.frame;
        }

        private StateContinue(CallFrame callFrame) {
            super();
            this.frame = callFrame;
        }
    }

    private static final class NewThrowable extends NewState {
        private final Object throwable;

        /* JADX INFO: renamed from: -$$Nest$fgetthrowable, reason: not valid java name */
        static /* bridge */ /* synthetic */ Object m10881$$Nest$fgetthrowable(NewThrowable newThrowable) {
            return newThrowable.throwable;
        }

        private NewThrowable(Object obj) {
            super();
            this.throwable = obj;
        }
    }

    private static final class ContinueLoop extends NewState {
        private final CallFrame frame;
        private final int indexReg;
        private final int stackTop;

        /* JADX INFO: renamed from: -$$Nest$fgetframe, reason: not valid java name */
        static /* bridge */ /* synthetic */ CallFrame m10878$$Nest$fgetframe(ContinueLoop continueLoop) {
            return continueLoop.frame;
        }

        /* JADX INFO: renamed from: -$$Nest$fgetindexReg, reason: not valid java name */
        static /* bridge */ /* synthetic */ int m10879$$Nest$fgetindexReg(ContinueLoop continueLoop) {
            return continueLoop.indexReg;
        }

        /* JADX INFO: renamed from: -$$Nest$fgetstackTop, reason: not valid java name */
        static /* bridge */ /* synthetic */ int m10880$$Nest$fgetstackTop(ContinueLoop continueLoop) {
            return continueLoop.stackTop;
        }

        private ContinueLoop(CallFrame callFrame, int i, int i2) {
            super();
            this.frame = callFrame;
            this.stackTop = i;
            this.indexReg = i2;
        }
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 75281. Try increasing type updates limit count.
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:79)
        */
    private static java.lang.Object interpretLoop(org.mozilla.javascript.Context r39, org.mozilla.javascript.Interpreter.CallFrame r40, java.lang.Object r41) {
        /*
            Method dump skipped, instruction units count: 7528
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.Interpreter.interpretLoop(org.mozilla.javascript.Context, org.mozilla.javascript.Interpreter$CallFrame, java.lang.Object):java.lang.Object");
    }

    private static final NewState doCallByteCode(Context context, CallFrame callFrame, boolean z, int i, int i2, int i3) {
        CallFrame callFrame2;
        Scriptable scriptable;
        Callable callable;
        Object[] objArr;
        Scriptable scriptable2;
        CallFrame callFrame3;
        Object[] applyArguments;
        CallFrame callFrame4 = callFrame;
        Object[] objArr2 = callFrame4.stack;
        double[] dArr = callFrame4.sDbl;
        if (z) {
            context.instructionCount += 100;
        }
        int i4 = i2 - (i3 + 1);
        Callable callable2 = (Callable) objArr2[i4];
        Scriptable scriptable3 = (Scriptable) objArr2[i4 + 1];
        Scriptable homeObject = callable2 instanceof BaseFunction ? ((BaseFunction) callable2).getHomeObject() : null;
        if (i == -85) {
            scriptable3 = callFrame4.thisObj;
        }
        if (i == 76) {
            objArr2[i4] = ScriptRuntime.callRef(callable2, scriptable3, getArgsArray(objArr2, dArr, i4 + 2, i3), context);
            return new ContinueLoop(callFrame4, i4, i3);
        }
        Scriptable topLevelScope = callFrame4.scope;
        if (callFrame4.useActivation) {
            topLevelScope = ScriptableObject.getTopLevelScope(callFrame4.scope);
        }
        int i5 = i3;
        Object[] objArrEnsureStackLength = objArr2;
        double[] dArr2 = dArr;
        Callable target = callable2;
        while (true) {
            if (target instanceof ArrowFunction) {
                ArrowFunction arrowFunction = (ArrowFunction) target;
                Callable targetFunction = arrowFunction.getTargetFunction();
                Scriptable callThis = arrowFunction.getCallThis(context);
                homeObject = arrowFunction.getBoundHomeObject();
                target = targetFunction;
                scriptable3 = callThis;
            } else {
                if (!(target instanceof LambdaConstructor)) {
                    if (target instanceof LambdaFunction) {
                        target = ((LambdaFunction) target).getTarget();
                    } else if (target instanceof BoundFunction) {
                        BoundFunction boundFunction = (BoundFunction) target;
                        Callable targetFunction2 = boundFunction.getTargetFunction();
                        Scriptable callThis2 = boundFunction.getCallThis(context, topLevelScope);
                        Object[] boundArgs = boundFunction.getBoundArgs();
                        int length = boundArgs.length;
                        if (length > 0) {
                            objArrEnsureStackLength = callFrame4.ensureStackLength(length + i4 + 2 + i5);
                            dArr2 = callFrame4.sDbl;
                            int i6 = i4 + 2;
                            int i7 = i6 + length;
                            System.arraycopy(objArrEnsureStackLength, i6, objArrEnsureStackLength, i7, i5);
                            System.arraycopy(dArr2, i6, dArr2, i7, i5);
                            System.arraycopy(boundArgs, 0, objArrEnsureStackLength, i6, length);
                            i5 += length;
                        }
                        target = targetFunction2;
                        scriptable3 = callThis2;
                    } else {
                        if (target instanceof IdFunctionObject) {
                            IdFunctionObject idFunctionObject = (IdFunctionObject) target;
                            if (BaseFunction.isApplyOrCall(idFunctionObject)) {
                                Callable callable3 = ScriptRuntime.getCallable(scriptable3);
                                int i8 = i4 + 2;
                                Scriptable applyThis = getApplyThis(context, objArrEnsureStackLength, dArr2, i8, i5, callable3, callFrame4);
                                callFrame2 = callFrame4;
                                if (BaseFunction.isApply(idFunctionObject)) {
                                    if (i5 < 2) {
                                        applyArguments = ScriptRuntime.emptyArgs;
                                    } else {
                                        applyArguments = ScriptRuntime.getApplyArguments(context, objArrEnsureStackLength[i4 + 3]);
                                    }
                                    int length2 = applyArguments.length;
                                    Object[] objArrEnsureStackLength2 = callFrame2.ensureStackLength(length2 + i4 + 2);
                                    double[] dArr3 = callFrame2.sDbl;
                                    System.arraycopy(applyArguments, 0, objArrEnsureStackLength2, i8, length2);
                                    objArrEnsureStackLength = objArrEnsureStackLength2;
                                    i5 = length2;
                                    dArr2 = dArr3;
                                } else if (i5 > 0) {
                                    if (i5 > 1) {
                                        int i9 = i4 + 3;
                                        int i10 = i5 - 1;
                                        System.arraycopy(objArrEnsureStackLength, i9, objArrEnsureStackLength, i8, i10);
                                        System.arraycopy(dArr2, i9, dArr2, i8, i10);
                                    }
                                    i5--;
                                }
                                target = callable3;
                                scriptable3 = applyThis;
                            }
                        } else {
                            callFrame2 = callFrame4;
                            if (!(target instanceof ScriptRuntime.NoSuchMethodShim)) {
                                if (target != null) {
                                    break;
                                }
                                throw ScriptRuntime.notFunctionError(null, null);
                            }
                            ScriptRuntime.NoSuchMethodShim noSuchMethodShim = (ScriptRuntime.NoSuchMethodShim) target;
                            objArrEnsureStackLength = callFrame2.ensureStackLength(i4 + 4);
                            dArr2 = callFrame2.sDbl;
                            int i11 = i4 + 2;
                            Object[] argsArray = getArgsArray(objArrEnsureStackLength, dArr2, i11, i5);
                            Callable callable4 = noSuchMethodShim.noSuchMethodMethod;
                            objArrEnsureStackLength[i11] = noSuchMethodShim.methodName;
                            objArrEnsureStackLength[i4 + 3] = context.newArray(topLevelScope, argsArray);
                            target = callable4;
                            i5 = 2;
                        }
                        callFrame4 = callFrame2;
                    }
                }
                callFrame2 = callFrame4;
                break;
            }
        }
        if (target instanceof InterpretedFunction) {
            callable = target;
            Scriptable scriptable4 = homeObject;
            int i12 = i5;
            Object[] objArr3 = objArrEnsureStackLength;
            scriptable2 = topLevelScope;
            InterpretedFunction interpretedFunction = (InterpretedFunction) callable;
            if (callFrame2.fnOrScript.securityDomain == interpretedFunction.securityDomain) {
                if (i == -62) {
                    CallFrame callFrame5 = callFrame2.parentFrame;
                    exitFrame(context, callFrame2, null);
                    callFrame3 = callFrame5;
                } else {
                    callFrame3 = callFrame2;
                }
                CallFrame callFrameInitFrame = initFrame(context, scriptable2, scriptable3, scriptable4, objArr3, dArr2, i4 + 2, i12, interpretedFunction, callFrame3);
                if (i != -62) {
                    callFrame2.savedStackTop = i4;
                    callFrame2.savedCallOp = i;
                }
                return new StateContinue(callFrameInitFrame);
            }
            scriptable = scriptable3;
            objArr = objArr3;
            i5 = i12;
        } else {
            scriptable = scriptable3;
            callable = target;
            objArr = objArrEnsureStackLength;
            scriptable2 = topLevelScope;
        }
        if (callable instanceof NativeContinuation) {
            ContinuationJump continuationJump = new ContinuationJump((NativeContinuation) callable, callFrame2);
            if (i5 == 0) {
                continuationJump.result = undefined;
            } else {
                int i13 = i4 + 2;
                continuationJump.result = objArr[i13];
                continuationJump.resultDbl = dArr2[i13];
            }
            return new NewThrowable(continuationJump);
        }
        if ((callable instanceof IdFunctionObject) && NativeContinuation.isContinuationConstructor((IdFunctionObject) callable)) {
            callFrame2.stack[i4] = captureContinuation(context, callFrame2.parentFrame, false);
            return new ContinueLoop(callFrame2, i4, i5);
        }
        context.lastInterpreterFrame = callFrame2;
        callFrame2.savedCallOp = i;
        callFrame2.savedStackTop = i4;
        objArr[i4] = callable.call(context, scriptable2, scriptable, getArgsArray(objArr, dArr2, i4 + 2, i5));
        return new ContinueLoop(callFrame2, i4, i5);
    }

    private static Scriptable getCurrentFrameHomeObject(CallFrame callFrame) {
        if (callFrame.scope instanceof NativeCall) {
            return ((NativeCall) callFrame.scope).getHomeObject();
        }
        return null;
    }

    private static int doInOrInstanceof(Context context, int i, Object[] objArr, double[] dArr, int i2) {
        boolean zInstanceOf;
        Object objWrapNumber = objArr[i2];
        if (objWrapNumber == UniqueTag.DOUBLE_MARK) {
            objWrapNumber = ScriptRuntime.wrapNumber(dArr[i2]);
        }
        int i3 = i2 - 1;
        Object objWrapNumber2 = objArr[i3];
        if (objWrapNumber2 == UniqueTag.DOUBLE_MARK) {
            objWrapNumber2 = ScriptRuntime.wrapNumber(dArr[i3]);
        }
        if (i == 57) {
            zInstanceOf = ScriptRuntime.in(objWrapNumber2, objWrapNumber, context);
        } else {
            zInstanceOf = ScriptRuntime.instanceOf(objWrapNumber2, objWrapNumber, context);
        }
        objArr[i3] = ScriptRuntime.wrapBoolean(zInstanceOf);
        return i3;
    }

    private static int doCompare(CallFrame callFrame, int i, Object[] objArr, double[] dArr, int i2) {
        boolean zCompare;
        Number numberValueOf;
        Number numberStack_numeric;
        int i3 = i2 - 1;
        Object obj = objArr[i2];
        Object obj2 = objArr[i3];
        if (obj == UniqueTag.DOUBLE_MARK) {
            numberValueOf = Double.valueOf(dArr[i2]);
            numberStack_numeric = stack_numeric(callFrame, i3);
        } else {
            if (obj2 == UniqueTag.DOUBLE_MARK) {
                Number numeric = ScriptRuntime.toNumeric(obj);
                Double dValueOf = Double.valueOf(dArr[i3]);
                numberValueOf = numeric;
                numberStack_numeric = dValueOf;
            } else {
                zCompare = ScriptRuntime.compare(obj2, obj, i);
            }
            objArr[i3] = ScriptRuntime.wrapBoolean(zCompare);
            return i3;
        }
        zCompare = ScriptRuntime.compare(numberStack_numeric, numberValueOf, i);
        objArr[i3] = ScriptRuntime.wrapBoolean(zCompare);
        return i3;
    }

    private static int doBitOp(CallFrame callFrame, int i, Object[] objArr, double[] dArr, int i2) {
        Number numberLeftShift;
        Number numberStack_numeric = stack_numeric(callFrame, i2 - 1);
        Number numberStack_numeric2 = stack_numeric(callFrame, i2);
        int i3 = i2 - 1;
        if (i == 18) {
            numberLeftShift = ScriptRuntime.leftShift(numberStack_numeric, numberStack_numeric2);
        } else if (i != 19) {
            switch (i) {
                case 9:
                    numberLeftShift = ScriptRuntime.bitwiseOR(numberStack_numeric, numberStack_numeric2);
                    break;
                case 10:
                    numberLeftShift = ScriptRuntime.bitwiseXOR(numberStack_numeric, numberStack_numeric2);
                    break;
                case 11:
                    numberLeftShift = ScriptRuntime.bitwiseAND(numberStack_numeric, numberStack_numeric2);
                    break;
                default:
                    numberLeftShift = null;
                    break;
            }
        } else {
            numberLeftShift = ScriptRuntime.signedRightShift(numberStack_numeric, numberStack_numeric2);
        }
        if (numberLeftShift instanceof BigInteger) {
            objArr[i3] = numberLeftShift;
            return i3;
        }
        objArr[i3] = UniqueTag.DOUBLE_MARK;
        dArr[i3] = numberLeftShift.doubleValue();
        return i3;
    }

    private static int doBitNOT(CallFrame callFrame, Object[] objArr, double[] dArr, int i) {
        Number numberBitwiseNOT = ScriptRuntime.bitwiseNOT(stack_numeric(callFrame, i));
        if (numberBitwiseNOT instanceof BigInteger) {
            objArr[i] = numberBitwiseNOT;
            return i;
        }
        objArr[i] = UniqueTag.DOUBLE_MARK;
        dArr[i] = numberBitwiseNOT.doubleValue();
        return i;
    }

    private static int doDelName(Context context, CallFrame callFrame, int i, Object[] objArr, double[] dArr, int i2) {
        Object objWrapNumber = objArr[i2];
        if (objWrapNumber == UniqueTag.DOUBLE_MARK) {
            objWrapNumber = ScriptRuntime.wrapNumber(dArr[i2]);
        }
        int i3 = i2 - 1;
        Object objWrapNumber2 = objArr[i3];
        if (objWrapNumber2 == UniqueTag.DOUBLE_MARK) {
            objWrapNumber2 = ScriptRuntime.wrapNumber(dArr[i3]);
        }
        objArr[i3] = ScriptRuntime.delete(objWrapNumber2, objWrapNumber, context, callFrame.scope, i == 0);
        return i3;
    }

    private static int doGetElem(Context context, CallFrame callFrame, Object[] objArr, double[] dArr, int i) {
        Object objectIndex;
        int i2 = i - 1;
        Object objWrapNumber = objArr[i2];
        if (objWrapNumber == UniqueTag.DOUBLE_MARK) {
            objWrapNumber = ScriptRuntime.wrapNumber(dArr[i2]);
        }
        Object obj = objArr[i];
        if (obj != UniqueTag.DOUBLE_MARK) {
            objectIndex = ScriptRuntime.getObjectElem(objWrapNumber, obj, context, callFrame.scope);
        } else {
            objectIndex = ScriptRuntime.getObjectIndex(objWrapNumber, dArr[i], context, callFrame.scope);
        }
        objArr[i2] = objectIndex;
        return i2;
    }

    private static int doGetElemSuper(Context context, CallFrame callFrame, Object[] objArr, double[] dArr, int i) {
        Object superIndex;
        int i2 = i - 1;
        Object obj = objArr[i2];
        if (obj == UniqueTag.DOUBLE_MARK) {
            Kit.codeBug();
        }
        Object obj2 = objArr[i];
        if (obj2 != UniqueTag.DOUBLE_MARK) {
            superIndex = ScriptRuntime.getSuperElem(obj, obj2, context, callFrame.scope, callFrame.thisObj);
        } else {
            superIndex = ScriptRuntime.getSuperIndex(obj, dArr[i], context, callFrame.scope, callFrame.thisObj);
        }
        objArr[i2] = superIndex;
        return i2;
    }

    private static int doSetElem(Context context, CallFrame callFrame, Object[] objArr, double[] dArr, int i) {
        Object objectIndex;
        int i2 = i - 2;
        Object objWrapNumber = objArr[i];
        if (objWrapNumber == UniqueTag.DOUBLE_MARK) {
            objWrapNumber = ScriptRuntime.wrapNumber(dArr[i]);
        }
        Object obj = objWrapNumber;
        Object objWrapNumber2 = objArr[i2];
        if (objWrapNumber2 == UniqueTag.DOUBLE_MARK) {
            objWrapNumber2 = ScriptRuntime.wrapNumber(dArr[i2]);
        }
        Object obj2 = objWrapNumber2;
        int i3 = i - 1;
        Object obj3 = objArr[i3];
        if (obj3 != UniqueTag.DOUBLE_MARK) {
            objectIndex = ScriptRuntime.setObjectElem(obj2, obj3, obj, context, callFrame.scope);
        } else {
            objectIndex = ScriptRuntime.setObjectIndex(obj2, dArr[i3], obj, context, callFrame.scope);
        }
        objArr[i2] = objectIndex;
        return i2;
    }

    private static int doSetElemSuper(Context context, CallFrame callFrame, Object[] objArr, double[] dArr, int i) {
        Object superIndex;
        int i2 = i - 2;
        Object objWrapNumber = objArr[i];
        if (objWrapNumber == UniqueTag.DOUBLE_MARK) {
            objWrapNumber = ScriptRuntime.wrapNumber(dArr[i]);
        }
        Object obj = objWrapNumber;
        Object obj2 = objArr[i2];
        if (obj2 == UniqueTag.DOUBLE_MARK) {
            Kit.codeBug();
        }
        int i3 = i - 1;
        Object obj3 = objArr[i3];
        if (obj3 != UniqueTag.DOUBLE_MARK) {
            superIndex = ScriptRuntime.setSuperElem(obj2, obj3, obj, context, callFrame.scope, callFrame.thisObj);
        } else {
            superIndex = ScriptRuntime.setSuperIndex(obj2, dArr[i3], obj, context, callFrame.scope, callFrame.thisObj);
        }
        objArr[i2] = superIndex;
        return i2;
    }

    private static int doElemIncDec(Context context, CallFrame callFrame, byte[] bArr, Object[] objArr, double[] dArr, int i) {
        Object objWrapNumber = objArr[i];
        if (objWrapNumber == UniqueTag.DOUBLE_MARK) {
            objWrapNumber = ScriptRuntime.wrapNumber(dArr[i]);
        }
        int i2 = i - 1;
        Object objWrapNumber2 = objArr[i2];
        if (objWrapNumber2 == UniqueTag.DOUBLE_MARK) {
            objWrapNumber2 = ScriptRuntime.wrapNumber(dArr[i2]);
        }
        objArr[i2] = ScriptRuntime.elemIncrDecr(objWrapNumber2, objWrapNumber, context, callFrame.scope, bArr[callFrame.pc]);
        callFrame.pc++;
        return i2;
    }

    private static int doCallSpecial(Context context, CallFrame callFrame, Object[] objArr, double[] dArr, int i, byte[] bArr, int i2, boolean z) {
        int i3;
        int i4 = bArr[callFrame.pc] & 255;
        boolean z2 = bArr[callFrame.pc + 1] != 0;
        int index = getIndex(bArr, callFrame.pc + 2);
        if (z2) {
            i3 = i - i2;
            Object objWrapNumber = objArr[i3];
            if (objWrapNumber == UniqueTag.DOUBLE_MARK) {
                objWrapNumber = ScriptRuntime.wrapNumber(dArr[i3]);
            }
            objArr[i3] = ScriptRuntime.newSpecial(context, objWrapNumber, getArgsArray(objArr, dArr, i3 + 1, i2), callFrame.scope, i4);
        } else {
            i3 = i - (i2 + 1);
            objArr[i3] = ScriptRuntime.callSpecial(context, (Callable) objArr[i3], (Scriptable) objArr[i3 + 1], getArgsArray(objArr, dArr, i3 + 2, i2), callFrame.scope, callFrame.thisObj, i4, callFrame.idata.itsSourceFile, index, z);
        }
        callFrame.pc += 4;
        return i3;
    }

    private static int doSetConstVar(CallFrame callFrame, Object[] objArr, double[] dArr, int i, Object[] objArr2, double[] dArr2, int[] iArr, int i2) {
        if (!callFrame.useActivation) {
            int i3 = iArr[i2];
            if ((i3 & 1) == 0) {
                throw Context.reportRuntimeErrorById("msg.var.redecl", callFrame.idata.argNames[i2]);
            }
            if ((i3 & 8) != 0) {
                objArr2[i2] = objArr[i];
                iArr[i2] = i3 & (-9);
                dArr2[i2] = dArr[i];
            }
            return i;
        }
        Object objWrapNumber = objArr[i];
        if (objWrapNumber == UniqueTag.DOUBLE_MARK) {
            objWrapNumber = ScriptRuntime.wrapNumber(dArr[i]);
        }
        String str = callFrame.idata.argNames[i2];
        if (callFrame.scope instanceof ConstProperties) {
            ((ConstProperties) callFrame.scope).putConst(str, callFrame.scope, objWrapNumber);
            return i;
        }
        throw Kit.codeBug();
    }

    private static int doSetVar(CallFrame callFrame, Object[] objArr, double[] dArr, int i, Object[] objArr2, double[] dArr2, int[] iArr, int i2) {
        if (!callFrame.useActivation) {
            if ((iArr[i2] & 1) == 0) {
                objArr2[i2] = objArr[i];
                dArr2[i2] = dArr[i];
            }
            return i;
        }
        Object objWrapNumber = objArr[i];
        if (objWrapNumber == UniqueTag.DOUBLE_MARK) {
            objWrapNumber = ScriptRuntime.wrapNumber(dArr[i]);
        }
        callFrame.scope.put(callFrame.idata.argNames[i2], callFrame.scope, objWrapNumber);
        return i;
    }

    private static int doGetVar(CallFrame callFrame, Object[] objArr, double[] dArr, int i, Object[] objArr2, double[] dArr2, int i2) {
        int i3 = i + 1;
        if (!callFrame.useActivation) {
            objArr[i3] = objArr2[i2];
            dArr[i3] = dArr2[i2];
            return i3;
        }
        objArr[i3] = callFrame.scope.get(callFrame.idata.argNames[i2], callFrame.scope);
        return i3;
    }

    private static int doVarIncDec(Context context, CallFrame callFrame, Object[] objArr, double[] dArr, int i, Object[] objArr2, double[] dArr2, int[] iArr, int i2) {
        double dDoubleValue;
        BigInteger bigIntegerSubtract;
        boolean z;
        int i3 = i + 1;
        byte b = callFrame.idata.itsICode[callFrame.pc];
        if (!callFrame.useActivation) {
            Object obj = objArr2[i2];
            BigInteger bigInteger = null;
            if (obj == UniqueTag.DOUBLE_MARK) {
                dDoubleValue = dArr2[i2];
            } else {
                Number numeric = ScriptRuntime.toNumeric(obj);
                if (numeric instanceof BigInteger) {
                    bigInteger = (BigInteger) numeric;
                    dDoubleValue = 0.0d;
                } else {
                    dDoubleValue = numeric.doubleValue();
                }
            }
            if (bigInteger == null) {
                double d = (b & 1) == 0 ? 1.0d + dDoubleValue : dDoubleValue - 1.0d;
                z = (b & 2) != 0;
                if ((iArr[i2] & 1) == 0) {
                    if (obj != UniqueTag.DOUBLE_MARK) {
                        objArr2[i2] = UniqueTag.DOUBLE_MARK;
                    }
                    dArr2[i2] = d;
                    objArr[i3] = UniqueTag.DOUBLE_MARK;
                    if (!z) {
                        dDoubleValue = d;
                    }
                    dArr[i3] = dDoubleValue;
                } else if (z && obj != UniqueTag.DOUBLE_MARK) {
                    objArr[i3] = obj;
                } else {
                    objArr[i3] = UniqueTag.DOUBLE_MARK;
                    if (!z) {
                        dDoubleValue = d;
                    }
                    dArr[i3] = dDoubleValue;
                }
            } else {
                if ((b & 1) == 0) {
                    bigIntegerSubtract = bigInteger.add(BigInteger.ONE);
                } else {
                    bigIntegerSubtract = bigInteger.subtract(BigInteger.ONE);
                }
                z = (b & 2) != 0;
                if ((iArr[i2] & 1) == 0) {
                    objArr2[i2] = bigIntegerSubtract;
                    if (!z) {
                        bigInteger = bigIntegerSubtract;
                    }
                    objArr[i3] = bigInteger;
                } else if (z && obj != UniqueTag.DOUBLE_MARK) {
                    objArr[i3] = obj;
                } else {
                    if (!z) {
                        bigInteger = bigIntegerSubtract;
                    }
                    objArr[i3] = bigInteger;
                }
            }
        } else {
            objArr[i3] = ScriptRuntime.nameIncrDecr(callFrame.scope, callFrame.idata.argNames[i2], context, b);
        }
        callFrame.pc++;
        return i3;
    }

    private static int doRefMember(Context context, Object[] objArr, double[] dArr, int i, int i2) {
        Object objWrapNumber = objArr[i];
        if (objWrapNumber == UniqueTag.DOUBLE_MARK) {
            objWrapNumber = ScriptRuntime.wrapNumber(dArr[i]);
        }
        int i3 = i - 1;
        Object objWrapNumber2 = objArr[i3];
        if (objWrapNumber2 == UniqueTag.DOUBLE_MARK) {
            objWrapNumber2 = ScriptRuntime.wrapNumber(dArr[i3]);
        }
        objArr[i3] = ScriptRuntime.memberRef(objWrapNumber2, objWrapNumber, context, i2);
        return i3;
    }

    private static int doRefNsMember(Context context, Object[] objArr, double[] dArr, int i, int i2) {
        Object objWrapNumber = objArr[i];
        if (objWrapNumber == UniqueTag.DOUBLE_MARK) {
            objWrapNumber = ScriptRuntime.wrapNumber(dArr[i]);
        }
        int i3 = i - 1;
        Object objWrapNumber2 = objArr[i3];
        if (objWrapNumber2 == UniqueTag.DOUBLE_MARK) {
            objWrapNumber2 = ScriptRuntime.wrapNumber(dArr[i3]);
        }
        int i4 = i - 2;
        Object objWrapNumber3 = objArr[i4];
        if (objWrapNumber3 == UniqueTag.DOUBLE_MARK) {
            objWrapNumber3 = ScriptRuntime.wrapNumber(dArr[i4]);
        }
        objArr[i4] = ScriptRuntime.memberRef(objWrapNumber3, objWrapNumber2, objWrapNumber, context, i2);
        return i4;
    }

    private static int doRefNsName(Context context, CallFrame callFrame, Object[] objArr, double[] dArr, int i, int i2) {
        Object objWrapNumber = objArr[i];
        if (objWrapNumber == UniqueTag.DOUBLE_MARK) {
            objWrapNumber = ScriptRuntime.wrapNumber(dArr[i]);
        }
        int i3 = i - 1;
        Object objWrapNumber2 = objArr[i3];
        if (objWrapNumber2 == UniqueTag.DOUBLE_MARK) {
            objWrapNumber2 = ScriptRuntime.wrapNumber(dArr[i3]);
        }
        objArr[i3] = ScriptRuntime.nameRef(objWrapNumber2, objWrapNumber, context, callFrame.scope, i2);
        return i3;
    }

    private static boolean doEquals(Object[] objArr, double[] dArr, int i) {
        int i2 = i + 1;
        Object obj = objArr[i2];
        Object obj2 = objArr[i];
        if (obj == UniqueTag.DOUBLE_MARK) {
            if (obj2 == UniqueTag.DOUBLE_MARK) {
                return dArr[i] == dArr[i2];
            }
            return ScriptRuntime.eqNumber(dArr[i2], obj2);
        }
        if (obj2 == UniqueTag.DOUBLE_MARK) {
            return ScriptRuntime.eqNumber(dArr[i], obj);
        }
        return ScriptRuntime.eq(obj2, obj);
    }

    private static boolean doShallowEquals(Object[] objArr, double[] dArr, int i) {
        double dDoubleValue;
        double dDoubleValue2;
        int i2 = i + 1;
        Object obj = objArr[i2];
        Object obj2 = objArr[i];
        UniqueTag uniqueTag = UniqueTag.DOUBLE_MARK;
        if (obj != uniqueTag) {
            if (obj2 == uniqueTag) {
                dDoubleValue = dArr[i];
                if ((obj instanceof Number) && !(obj instanceof BigInteger)) {
                    dDoubleValue2 = ((Number) obj).doubleValue();
                }
            }
            return ScriptRuntime.shallowEq(obj2, obj);
        }
        dDoubleValue2 = dArr[i2];
        if (obj2 == uniqueTag) {
            dDoubleValue = dArr[i];
        } else {
            if (!(obj2 instanceof Number) || (obj2 instanceof BigInteger)) {
                return false;
            }
            dDoubleValue = ((Number) obj2).doubleValue();
        }
        return dDoubleValue == dDoubleValue2;
    }

    private static CallFrame processThrowable(Context context, Object obj, CallFrame callFrame, int i, boolean z) {
        if (i >= 0) {
            if (callFrame.frozen) {
                callFrame = callFrame.cloneFrozen();
            }
            int[] iArr = callFrame.idata.itsExceptionTable;
            callFrame.pc = iArr[i + 2];
            if (z) {
                callFrame.pcPrevBranch = callFrame.pc;
            }
            callFrame.savedStackTop = callFrame.emptyStackTop;
            int i2 = callFrame.localShift + iArr[i + 5];
            int i3 = callFrame.localShift + iArr[i + 4];
            callFrame.scope = (Scriptable) callFrame.stack[i2];
            callFrame.stack[i3] = obj;
        } else {
            ContinuationJump continuationJump = (ContinuationJump) obj;
            if (!Objects.equals(continuationJump.branchFrame, callFrame)) {
                Kit.codeBug();
            }
            if (continuationJump.capturedFrame == null) {
                Kit.codeBug();
            }
            int i4 = continuationJump.capturedFrame.frameIndex + 1;
            if (continuationJump.branchFrame != null) {
                i4 -= continuationJump.branchFrame.frameIndex;
            }
            CallFrame callFrame2 = continuationJump.capturedFrame;
            CallFrame[] callFrameArr = null;
            int i5 = 0;
            for (int i6 = 0; i6 != i4; i6++) {
                if (!callFrame2.frozen) {
                    Kit.codeBug();
                }
                if (callFrame2.useActivation) {
                    if (callFrameArr == null) {
                        callFrameArr = new CallFrame[i4 - i6];
                    }
                    callFrameArr[i5] = callFrame2;
                    i5++;
                }
                callFrame2 = callFrame2.parentFrame;
            }
            while (i5 != 0) {
                i5--;
                enterFrame(context, callFrameArr[i5], ScriptRuntime.emptyArgs, true);
            }
            callFrame = continuationJump.capturedFrame.cloneFrozen();
            setCallResult(callFrame, continuationJump.result, continuationJump.resultDbl);
        }
        callFrame.throwable = null;
        return callFrame;
    }

    private static Object freezeGenerator(Context context, CallFrame callFrame, int i, GeneratorState generatorState, boolean z) {
        Object objWrapNumber;
        if (generatorState.operation == 2) {
            throw ScriptRuntime.typeErrorById("msg.yield.closing", new Object[0]);
        }
        callFrame.frozen = true;
        callFrame.result = callFrame.stack[i];
        callFrame.resultDbl = callFrame.sDbl[i];
        callFrame.savedStackTop = i;
        callFrame.pc--;
        ScriptRuntime.exitActivationFunction(context);
        if (callFrame.result != UniqueTag.DOUBLE_MARK) {
            objWrapNumber = callFrame.result;
        } else {
            objWrapNumber = ScriptRuntime.wrapNumber(callFrame.resultDbl);
        }
        return z ? new ES6Generator.YieldStarResult(objWrapNumber) : objWrapNumber;
    }

    private static Object thawGenerator(CallFrame callFrame, int i, GeneratorState generatorState, int i2) {
        callFrame.frozen = false;
        int index = getIndex(callFrame.idata.itsICode, callFrame.pc);
        callFrame.pc += 2;
        if (generatorState.operation == 1) {
            return new JavaScriptException(generatorState.value, callFrame.idata.itsSourceFile, index);
        }
        if (generatorState.operation == 2) {
            return generatorState.value;
        }
        if (generatorState.operation != 0) {
            throw Kit.codeBug();
        }
        if (i2 == 78 || i2 == -73) {
            callFrame.stack[i] = generatorState.value;
        }
        return Scriptable.NOT_FOUND;
    }

    private static Scriptable getApplyThis(Context context, Object[] objArr, double[] dArr, int i, int i2, Callable callable, CallFrame callFrame) {
        Object objWrapNumber = null;
        Scriptable objectOrNull = null;
        if (!(callable instanceof InterpretedFunction)) {
            if (i2 != 0 && (objWrapNumber = objArr[i]) == UniqueTag.DOUBLE_MARK) {
                objWrapNumber = ScriptRuntime.wrapNumber(dArr[i]);
            }
            return ScriptRuntime.getApplyOrCallThis(context, callFrame.scope, objWrapNumber, i2);
        }
        if (i2 != 0) {
            Object objWrapNumber2 = objArr[i];
            if (objWrapNumber2 == UniqueTag.DOUBLE_MARK) {
                objWrapNumber2 = ScriptRuntime.wrapNumber(dArr[i]);
            }
            objectOrNull = ScriptRuntime.toObjectOrNull(context, objWrapNumber2, callFrame.scope);
        }
        return objectOrNull == null ? ScriptRuntime.getTopCallScope(context) : objectOrNull;
    }

    private static CallFrame initFrame(Context context, Scriptable scriptable, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr, double[] dArr, int i, int i2, InterpretedFunction interpretedFunction, CallFrame callFrame) {
        CallFrame callFrame2 = new CallFrame(context, scriptable2, interpretedFunction, callFrame);
        callFrame2.initializeArgs(context, scriptable, objArr, dArr, i, i2, scriptable3);
        enterFrame(context, callFrame2, objArr, false);
        return callFrame2;
    }

    private static void enterFrame(Context context, CallFrame callFrame, Object[] objArr, boolean z) {
        boolean z2 = callFrame.idata.itsNeedsActivation;
        boolean z3 = callFrame.debuggerFrame != null;
        if (z2 || z3) {
            Scriptable parentScope = callFrame.scope;
            if (parentScope == null) {
                Kit.codeBug();
            } else if (z) {
                while (parentScope instanceof NativeWith) {
                    parentScope = parentScope.getParentScope();
                    if (parentScope == null || (callFrame.parentFrame != null && callFrame.parentFrame.scope == parentScope)) {
                        Kit.codeBug();
                        break;
                    }
                }
            }
            if (z3) {
                callFrame.debuggerFrame.onEnter(context, parentScope, callFrame.thisObj, objArr);
            }
            if (z2) {
                ScriptRuntime.enterActivationFunction(context, parentScope);
            }
        }
    }

    private static void exitFrame(Context context, CallFrame callFrame, Object obj) {
        Object objWrapNumber;
        double d;
        if (callFrame.idata.itsNeedsActivation) {
            ScriptRuntime.exitActivationFunction(context);
        }
        if (callFrame.debuggerFrame != null) {
            try {
                if (obj instanceof Throwable) {
                    callFrame.debuggerFrame.onExit(context, true, obj);
                    return;
                }
                ContinuationJump continuationJump = (ContinuationJump) obj;
                if (continuationJump == null) {
                    objWrapNumber = callFrame.result;
                } else {
                    objWrapNumber = continuationJump.result;
                }
                if (objWrapNumber == UniqueTag.DOUBLE_MARK) {
                    if (continuationJump == null) {
                        d = callFrame.resultDbl;
                    } else {
                        d = continuationJump.resultDbl;
                    }
                    objWrapNumber = ScriptRuntime.wrapNumber(d);
                }
                callFrame.debuggerFrame.onExit(context, false, objWrapNumber);
            } catch (Throwable th) {
                System.err.println("RHINO USAGE WARNING: onExit terminated with exception");
                th.printStackTrace(System.err);
            }
        }
    }

    private static void setCallResult(CallFrame callFrame, Object obj, double d) {
        if (callFrame.savedCallOp == 43 || callFrame.savedCallOp == -85) {
            callFrame.stack[callFrame.savedStackTop] = obj;
            callFrame.sDbl[callFrame.savedStackTop] = d;
        } else if (callFrame.savedCallOp == 30) {
            if (obj instanceof Scriptable) {
                callFrame.stack[callFrame.savedStackTop] = obj;
            }
        } else {
            Kit.codeBug();
        }
        callFrame.savedCallOp = 0;
    }

    public static NativeContinuation captureContinuation(Context context) {
        if (context.lastInterpreterFrame == null || !(context.lastInterpreterFrame instanceof CallFrame)) {
            throw new IllegalStateException("Interpreter frames not found");
        }
        return captureContinuation(context, (CallFrame) context.lastInterpreterFrame, true);
    }

    private static NativeContinuation captureContinuation(Context context, CallFrame callFrame, boolean z) {
        NativeContinuation nativeContinuation = new NativeContinuation();
        ScriptRuntime.setObjectProtoAndParent(nativeContinuation, ScriptRuntime.getTopCallScope(context));
        CallFrame callFrame2 = callFrame;
        CallFrame callFrame3 = callFrame2;
        while (callFrame2 != null && !callFrame2.frozen) {
            callFrame2.frozen = true;
            for (int i = callFrame2.savedStackTop + 1; i != callFrame2.stack.length; i++) {
                callFrame2.stack[i] = null;
                callFrame2.stackAttributes[i] = 0;
            }
            if (callFrame2.savedCallOp == 43 || callFrame2.savedCallOp == -85) {
                callFrame2.stack[callFrame2.savedStackTop] = null;
            } else if (callFrame2.savedCallOp != 30) {
                Kit.codeBug();
            }
            callFrame3 = callFrame2;
            callFrame2 = callFrame2.parentFrame;
        }
        if (z) {
            while (callFrame3.parentFrame != null) {
                callFrame3 = callFrame3.parentFrame;
            }
            if (!callFrame3.isContinuationsTopFrame) {
                throw new IllegalStateException("Cannot capture continuation from JavaScript code not called directly by executeScriptWithContinuations or callFunctionWithContinuations");
            }
        }
        nativeContinuation.initImplementation(callFrame);
        return nativeContinuation;
    }

    private static int stack_int32(CallFrame callFrame, int i) {
        Object obj = callFrame.stack[i];
        if (obj == UniqueTag.DOUBLE_MARK) {
            return ScriptRuntime.toInt32(callFrame.sDbl[i]);
        }
        return ScriptRuntime.toInt32(obj);
    }

    private static double stack_double(CallFrame callFrame, int i) {
        Object obj = callFrame.stack[i];
        if (obj != UniqueTag.DOUBLE_MARK) {
            return ScriptRuntime.toNumber(obj);
        }
        return callFrame.sDbl[i];
    }

    private static Number stack_numeric(CallFrame callFrame, int i) {
        Object obj = callFrame.stack[i];
        if (obj != UniqueTag.DOUBLE_MARK) {
            return ScriptRuntime.toNumeric(obj);
        }
        return Double.valueOf(callFrame.sDbl[i]);
    }

    private static boolean stack_boolean(CallFrame callFrame, int i) {
        Object obj = callFrame.stack[i];
        if (Boolean.TRUE.equals(obj)) {
            return true;
        }
        if (Boolean.FALSE.equals(obj)) {
            return false;
        }
        if (obj == UniqueTag.DOUBLE_MARK) {
            double d = callFrame.sDbl[i];
            return (Double.isNaN(d) || d == 0.0d) ? false : true;
        }
        if (obj == null || obj == Undefined.instance) {
            return false;
        }
        if (obj instanceof BigInteger) {
            return !obj.equals(BigInteger.ZERO);
        }
        if (obj instanceof Number) {
            double dDoubleValue = ((Number) obj).doubleValue();
            return (Double.isNaN(dDoubleValue) || dDoubleValue == 0.0d) ? false : true;
        }
        return ScriptRuntime.toBoolean(obj);
    }

    private static void doAdd(Object[] objArr, double[] dArr, int i, Context context) {
        double d;
        boolean z;
        int i2 = i + 1;
        Object obj = objArr[i2];
        Object obj2 = objArr[i];
        if (obj == UniqueTag.DOUBLE_MARK) {
            d = dArr[i2];
            if (obj2 == UniqueTag.DOUBLE_MARK) {
                dArr[i] = dArr[i] + d;
                return;
            }
            z = true;
        } else if (obj2 == UniqueTag.DOUBLE_MARK) {
            obj2 = obj;
            d = dArr[i];
            z = false;
        } else {
            if ((obj2 instanceof Scriptable) || (obj instanceof Scriptable)) {
                objArr[i] = ScriptRuntime.add(obj2, obj, context);
                return;
            }
            if (obj2 instanceof CharSequence) {
                if (obj instanceof CharSequence) {
                    objArr[i] = new ConsString((CharSequence) obj2, (CharSequence) obj);
                    return;
                } else {
                    objArr[i] = new ConsString((CharSequence) obj2, ScriptRuntime.toCharSequence(obj));
                    return;
                }
            }
            if (obj instanceof CharSequence) {
                objArr[i] = new ConsString(ScriptRuntime.toCharSequence(obj2), (CharSequence) obj);
                return;
            }
            Number numeric = obj2 instanceof Number ? (Number) obj2 : ScriptRuntime.toNumeric(obj2);
            Number numeric2 = obj instanceof Number ? (Number) obj : ScriptRuntime.toNumeric(obj);
            boolean z2 = numeric instanceof BigInteger;
            if (z2 && (numeric2 instanceof BigInteger)) {
                objArr[i] = ((BigInteger) numeric).add((BigInteger) numeric2);
                return;
            } else {
                if (z2 || (numeric2 instanceof BigInteger)) {
                    throw ScriptRuntime.typeErrorById("msg.cant.convert.to.number", "BigInt");
                }
                objArr[i] = UniqueTag.DOUBLE_MARK;
                dArr[i] = numeric.doubleValue() + numeric2.doubleValue();
                return;
            }
        }
        if (obj2 instanceof Scriptable) {
            Object objWrapNumber = ScriptRuntime.wrapNumber(d);
            if (!z) {
                Object obj3 = obj2;
                obj2 = objWrapNumber;
                objWrapNumber = obj3;
            }
            objArr[i] = ScriptRuntime.add(obj2, objWrapNumber, context);
            return;
        }
        if (obj2 instanceof CharSequence) {
            String strNumberToString = ScriptRuntime.numberToString(d, 10);
            if (z) {
                objArr[i] = new ConsString((CharSequence) obj2, strNumberToString);
                return;
            } else {
                objArr[i] = new ConsString(strNumberToString, (CharSequence) obj2);
                return;
            }
        }
        Number numeric3 = obj2 instanceof Number ? (Number) obj2 : ScriptRuntime.toNumeric(obj2);
        if (numeric3 instanceof BigInteger) {
            throw ScriptRuntime.typeErrorById("msg.cant.convert.to.number", "BigInt");
        }
        objArr[i] = UniqueTag.DOUBLE_MARK;
        dArr[i] = numeric3.doubleValue() + d;
    }

    private static int doArithmetic(CallFrame callFrame, int i, Object[] objArr, double[] dArr, int i2) {
        Number numberExponentiate;
        Number numberStack_numeric = stack_numeric(callFrame, i2 - 1);
        Number numberStack_numeric2 = stack_numeric(callFrame, i2);
        int i3 = i2 - 1;
        if (i != 81) {
            switch (i) {
                case 22:
                    numberExponentiate = ScriptRuntime.subtract(numberStack_numeric, numberStack_numeric2);
                    break;
                case 23:
                    numberExponentiate = ScriptRuntime.multiply(numberStack_numeric, numberStack_numeric2);
                    break;
                case 24:
                    numberExponentiate = ScriptRuntime.divide(numberStack_numeric, numberStack_numeric2);
                    break;
                case 25:
                    numberExponentiate = ScriptRuntime.remainder(numberStack_numeric, numberStack_numeric2);
                    break;
                default:
                    numberExponentiate = null;
                    break;
            }
        } else {
            numberExponentiate = ScriptRuntime.exponentiate(numberStack_numeric, numberStack_numeric2);
        }
        if (numberExponentiate instanceof BigInteger) {
            objArr[i3] = numberExponentiate;
            return i3;
        }
        objArr[i3] = UniqueTag.DOUBLE_MARK;
        dArr[i3] = numberExponentiate.doubleValue();
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object[] getArgsArray(Object[] objArr, double[] dArr, int i, int i2) {
        if (i2 == 0) {
            return ScriptRuntime.emptyArgs;
        }
        Object[] objArr2 = new Object[i2];
        int i3 = 0;
        while (i3 != i2) {
            Object objWrapNumber = objArr[i];
            if (objWrapNumber == UniqueTag.DOUBLE_MARK) {
                objWrapNumber = ScriptRuntime.wrapNumber(dArr[i]);
            }
            objArr2[i3] = objWrapNumber;
            i3++;
            i++;
        }
        return objArr2;
    }

    private static void addInstructionCount(Context context, CallFrame callFrame, int i) {
        context.instructionCount += (callFrame.pc - callFrame.pcPrevBranch) + i;
        if (context.instructionCount > context.instructionThreshold) {
            context.observeInstructionCount(context.instructionCount);
            context.instructionCount = 0;
        }
    }
}
