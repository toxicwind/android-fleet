package org.mozilla.javascript.optimizer;

import androidx.core.app.NotificationCompat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.mozilla.classfile.ByteCode;
import org.mozilla.classfile.ClassFileWriter;
import org.mozilla.javascript.CompilerEnvirons;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Node;
import org.mozilla.javascript.Token;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.Jump;
import org.mozilla.javascript.ast.ScriptNode;

/* JADX INFO: loaded from: classes8.dex */
class BodyCodegen {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int ECMAERROR_EXCEPTION = 2;
    private static final int EVALUATOR_EXCEPTION = 1;
    private static final int EXCEPTION_MAX = 5;
    private static final int FINALLY_EXCEPTION = 4;
    static final int GENERATOR_START = 0;
    static final int GENERATOR_TERMINATE = -1;
    static final int GENERATOR_YIELD_START = 1;
    private static final int JAVASCRIPT_EXCEPTION = 0;
    private static final int MAX_LOCALS = 1024;
    private static final String SIGNATURE_CALL0 = "(Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
    private static final String SIGNATURE_CALL1 = "(Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
    private static final String SIGNATURE_CALL2 = "(Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
    private static final String SIGNATURE_CALLN = "(Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
    private static final int THROWABLE_EXCEPTION = 3;
    private int argsLocal;
    ClassFileWriter cfw;
    Codegen codegen;
    CompilerEnvirons compilerEnv;
    private int contextLocal;
    private int enterAreaStartLabel;
    private int epilogueLabel;
    private Map<Node, FinallyReturnPoint> finallys;
    private int firstFreeLocal;
    private OptFunctionNode fnCurrent;
    private int funObjLocal;
    private int generatorStateLocal;
    private int generatorSwitch;
    private boolean hasVarsInRegs;
    private boolean inDirectCallFunction;
    private boolean inLocalBlock;
    private boolean isGenerator;
    private boolean itsForcedObjectParameters;
    private int itsLineNumber;
    private int itsOneArgArray;
    private int itsZeroArgArray;
    private ArrayList<Node> literals;
    private int[] locals;
    private int localsMax;
    private int operationLocal;
    private int popvLocal;
    private int savedCodeOffset;
    private int savedHomeObjectLocal;
    ScriptNode scriptOrFn;
    public int scriptOrFnIndex;
    private int thisObjLocal;
    private int[] varRegisters;
    private int variableObjectLocal;
    private ExceptionManager exceptionManager = new ExceptionManager();
    private int maxLocals = 0;
    private int maxStack = 0;
    private int unnestedYieldCount = 0;
    private IdentityHashMap<Node, String> unnestedYields = new IdentityHashMap<>();

    BodyCodegen() {
    }

    void generateBodyCode() {
        this.isGenerator = Codegen.isGenerator(this.scriptOrFn);
        initBodyGeneration();
        if (this.isGenerator) {
            String str = "(" + this.codegen.mainClassSignature + "Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Ljava/lang/Object;I)Ljava/lang/Object;";
            this.cfw.startMethod(this.codegen.getBodyMethodName(this.scriptOrFn) + "_gen", str, (short) 10);
        } else {
            this.cfw.startMethod(this.codegen.getBodyMethodName(this.scriptOrFn), this.codegen.getBodyMethodSignature(this.scriptOrFn), (short) 10);
        }
        generatePrologue();
        OptFunctionNode optFunctionNode = this.fnCurrent;
        Node lastChild = this.scriptOrFn;
        if (optFunctionNode != null) {
            lastChild = lastChild.getLastChild();
        }
        generateStatement(lastChild);
        generateEpilogue();
        this.cfw.stopMethod((short) (this.localsMax + 1));
        if (this.isGenerator) {
            generateGenerator();
        }
        if (this.literals != null) {
            for (int i = 0; i < this.literals.size(); i++) {
                Node node = this.literals.get(i);
                int type = node.getType();
                if (type == 71) {
                    generateArrayLiteralFactory(node, i + 1);
                } else if (type == 72) {
                    generateObjectLiteralFactory(node, i + 1);
                } else {
                    Kit.codeBug(Token.typeToName(type));
                }
            }
        }
    }

    private void generateGenerator() {
        this.cfw.startMethod(this.codegen.getBodyMethodName(this.scriptOrFn), this.codegen.getBodyMethodSignature(this.scriptOrFn), (short) 10);
        initBodyGeneration();
        int i = this.firstFreeLocal;
        int i2 = i + 1;
        this.firstFreeLocal = i2;
        this.argsLocal = i;
        this.localsMax = i2;
        if (this.fnCurrent != null) {
            this.cfw.addALoad(this.funObjLocal);
            this.cfw.addInvoke(185, "org/mozilla/javascript/Scriptable", "getParentScope", "()Lorg/mozilla/javascript/Scriptable;");
            this.cfw.addAStore(this.variableObjectLocal);
        }
        this.cfw.addALoad(this.funObjLocal);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addALoad(this.argsLocal);
        this.cfw.addPush(this.scriptOrFn.isInStrictMode());
        this.cfw.addPush(this.scriptOrFn.hasRestParameter());
        this.cfw.addLoadThis();
        this.cfw.addInvoke(182, "org/mozilla/javascript/BaseFunction", "getHomeObject", "()Lorg/mozilla/javascript/Scriptable;");
        addScriptRuntimeInvoke("createFunctionActivation", "(Lorg/mozilla/javascript/NativeFunction;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;ZZLorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
        this.cfw.addAStore(this.variableObjectLocal);
        this.cfw.add(187, this.codegen.mainClassName);
        this.cfw.add(89);
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addPush(this.scriptOrFnIndex);
        this.cfw.addInvoke(183, this.codegen.mainClassName, "<init>", "(Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Context;I)V");
        generateNestedFunctionInits();
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addALoad(this.thisObjLocal);
        this.cfw.addLoadConstant(this.maxLocals);
        this.cfw.addLoadConstant(this.maxStack);
        addOptRuntimeInvoke("createNativeGenerator", "(Lorg/mozilla/javascript/NativeFunction;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;II)Lorg/mozilla/javascript/Scriptable;");
        this.cfw.add(176);
        this.cfw.stopMethod((short) (this.localsMax + 1));
    }

    private void generateNestedFunctionInits() {
        int functionCount = this.scriptOrFn.getFunctionCount();
        for (int i = 0; i != functionCount; i++) {
            OptFunctionNode optFunctionNode = OptFunctionNode.get(this.scriptOrFn, i);
            if (optFunctionNode.fnode.getFunctionType() == 1) {
                visitFunction(optFunctionNode, 1);
            }
        }
    }

    private void initBodyGeneration() {
        int paramAndVarCount;
        this.varRegisters = null;
        if (this.scriptOrFn.getType() == 122) {
            OptFunctionNode optFunctionNode = OptFunctionNode.get(this.scriptOrFn);
            this.fnCurrent = optFunctionNode;
            boolean zRequiresActivation = optFunctionNode.fnode.requiresActivation();
            this.hasVarsInRegs = !zRequiresActivation;
            if (!zRequiresActivation && (paramAndVarCount = this.fnCurrent.fnode.getParamAndVarCount()) != 0) {
                this.varRegisters = new int[paramAndVarCount];
            }
            boolean zIsTargetOfDirectCall = this.fnCurrent.isTargetOfDirectCall();
            this.inDirectCallFunction = zIsTargetOfDirectCall;
            if (zIsTargetOfDirectCall && !this.hasVarsInRegs) {
                Codegen.badTree();
            }
        } else {
            this.fnCurrent = null;
            this.hasVarsInRegs = false;
            this.inDirectCallFunction = false;
        }
        this.locals = new int[1024];
        this.funObjLocal = 0;
        this.contextLocal = 1;
        this.variableObjectLocal = 2;
        this.thisObjLocal = 3;
        this.localsMax = 4;
        this.firstFreeLocal = 4;
        this.popvLocal = -1;
        this.argsLocal = -1;
        this.itsZeroArgArray = -1;
        this.itsOneArgArray = -1;
        this.epilogueLabel = -1;
        this.enterAreaStartLabel = -1;
        this.generatorStateLocal = -1;
        this.savedHomeObjectLocal = -1;
    }

    private void generatePrologue() {
        String str;
        int newWordLocal;
        if (this.inDirectCallFunction) {
            int paramCount = this.scriptOrFn.getParamCount();
            if (this.firstFreeLocal != 4) {
                Kit.codeBug();
            }
            for (int i = 0; i != paramCount; i++) {
                int[] iArr = this.varRegisters;
                int i2 = this.firstFreeLocal;
                iArr[i] = i2;
                this.firstFreeLocal = i2 + 3;
            }
            if (!this.fnCurrent.getParameterNumberContext()) {
                this.itsForcedObjectParameters = true;
                for (int i3 = 0; i3 != paramCount; i3++) {
                    int i4 = this.varRegisters[i3];
                    this.cfw.addALoad(i4);
                    this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
                    int iAcquireLabel = this.cfw.acquireLabel();
                    this.cfw.add(166, iAcquireLabel);
                    this.cfw.addDLoad(i4 + 1);
                    addDoubleWrap();
                    this.cfw.addAStore(i4);
                    this.cfw.markLabel(iAcquireLabel);
                }
            }
        }
        if (this.fnCurrent != null) {
            this.cfw.addALoad(this.funObjLocal);
            this.cfw.addInvoke(185, "org/mozilla/javascript/Scriptable", "getParentScope", "()Lorg/mozilla/javascript/Scriptable;");
            this.cfw.addAStore(this.variableObjectLocal);
        }
        int i5 = this.firstFreeLocal;
        int i6 = i5 + 1;
        this.firstFreeLocal = i6;
        this.argsLocal = i5;
        this.localsMax = i6;
        if (this.isGenerator) {
            int i7 = i5 + 2;
            this.firstFreeLocal = i7;
            this.operationLocal = i6;
            this.localsMax = i7;
            this.cfw.addALoad(this.thisObjLocal);
            int i8 = this.firstFreeLocal;
            int i9 = i8 + 1;
            this.firstFreeLocal = i9;
            this.generatorStateLocal = i8;
            this.localsMax = i9;
            this.cfw.add(192, "org/mozilla/javascript/optimizer/OptRuntime$GeneratorState");
            this.cfw.add(89);
            this.cfw.addAStore(this.generatorStateLocal);
            this.cfw.add(180, "org/mozilla/javascript/optimizer/OptRuntime$GeneratorState", "thisObj", "Lorg/mozilla/javascript/Scriptable;");
            this.cfw.addAStore(this.thisObjLocal);
            if (this.epilogueLabel == -1) {
                this.epilogueLabel = this.cfw.acquireLabel();
            }
            List<Node> resumptionPoints = ((FunctionNode) this.scriptOrFn).getResumptionPoints();
            if (resumptionPoints != null) {
                generateGetGeneratorResumptionPoint();
                this.generatorSwitch = this.cfw.addTableSwitch(0, resumptionPoints.size());
                generateCheckForThrowOrClose(-1, false, 0);
            }
        }
        if (this.fnCurrent == null) {
            if (this.scriptOrFn.getRegexpCount() != 0) {
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addInvoke(184, this.codegen.mainClassName, "_reInit", "(Lorg/mozilla/javascript/Context;)V");
            }
            if (this.scriptOrFn.getTemplateLiteralCount() != 0) {
                this.cfw.addInvoke(184, this.codegen.mainClassName, "_qInit", "()V");
            }
        }
        if (this.compilerEnv.isGenerateObserverCount()) {
            saveCurrentCodeOffset();
        }
        if (this.isGenerator) {
            return;
        }
        boolean z = this.hasVarsInRegs;
        ScriptNode scriptNode = this.scriptOrFn;
        if (z) {
            int paramCount2 = scriptNode.getParamCount();
            if (paramCount2 > 0 && !this.inDirectCallFunction) {
                boolean zHasRestParameter = this.scriptOrFn.hasRestParameter();
                ClassFileWriter classFileWriter = this.cfw;
                if (zHasRestParameter) {
                    classFileWriter.addALoad(this.contextLocal);
                    this.cfw.addALoad(this.variableObjectLocal);
                    this.cfw.addALoad(this.argsLocal);
                    this.cfw.addPush(paramCount2);
                    addScriptRuntimeInvoke("padAndRestArguments", "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;I)[Ljava/lang/Object;");
                    this.cfw.addAStore(this.argsLocal);
                } else {
                    classFileWriter.addALoad(this.argsLocal);
                    this.cfw.add(ByteCode.ARRAYLENGTH);
                    this.cfw.addPush(paramCount2);
                    int iAcquireLabel2 = this.cfw.acquireLabel();
                    this.cfw.add(162, iAcquireLabel2);
                    this.cfw.addALoad(this.argsLocal);
                    this.cfw.addPush(paramCount2);
                    addScriptRuntimeInvoke("padArguments", "([Ljava/lang/Object;I)[Ljava/lang/Object;");
                    this.cfw.addAStore(this.argsLocal);
                    this.cfw.markLabel(iAcquireLabel2);
                }
            }
            int paramCount3 = this.fnCurrent.fnode.getParamCount();
            int paramAndVarCount = this.fnCurrent.fnode.getParamAndVarCount();
            boolean[] paramAndVarConst = this.fnCurrent.fnode.getParamAndVarConst();
            int i10 = -1;
            for (int i11 = 0; i11 != paramAndVarCount; i11++) {
                if (i11 < paramCount3) {
                    if (this.inDirectCallFunction) {
                        newWordLocal = -1;
                    } else {
                        newWordLocal = getNewWordLocal();
                        this.cfw.addALoad(this.argsLocal);
                        this.cfw.addPush(i11);
                        this.cfw.add(50);
                        this.cfw.addAStore(newWordLocal);
                    }
                } else if (this.fnCurrent.isNumberVar(i11)) {
                    newWordLocal = getNewWordPairLocal(paramAndVarConst[i11]);
                    this.cfw.addPush(0.0d);
                    this.cfw.addDStore(newWordLocal);
                } else {
                    newWordLocal = getNewWordLocal(paramAndVarConst[i11]);
                    ClassFileWriter classFileWriter2 = this.cfw;
                    if (i10 == -1) {
                        Codegen.pushUndefined(classFileWriter2);
                        i10 = newWordLocal;
                    } else {
                        classFileWriter2.addALoad(i10);
                    }
                    this.cfw.addAStore(newWordLocal);
                }
                if (newWordLocal >= 0) {
                    if (paramAndVarConst[i11]) {
                        this.cfw.addPush(0);
                        this.cfw.addIStore((this.fnCurrent.isNumberVar(i11) ? 2 : 1) + newWordLocal);
                    }
                    this.varRegisters[i11] = newWordLocal;
                }
                if (this.compilerEnv.isGenerateDebugInfo()) {
                    String paramOrVarName = this.fnCurrent.fnode.getParamOrVarName(i11);
                    String str2 = this.fnCurrent.isNumberVar(i11) ? "D" : "Ljava/lang/Object;";
                    int currentCodeOffset = this.cfw.getCurrentCodeOffset();
                    if (newWordLocal < 0) {
                        newWordLocal = this.varRegisters[i11];
                    }
                    this.cfw.addVariableDescriptor(paramOrVarName, str2, currentCodeOffset, newWordLocal);
                }
            }
            return;
        }
        boolean z2 = (scriptNode instanceof FunctionNode) && ((FunctionNode) scriptNode).getFunctionType() == 4;
        OptFunctionNode optFunctionNode = this.fnCurrent;
        ClassFileWriter classFileWriter3 = this.cfw;
        if (optFunctionNode != null) {
            classFileWriter3.addALoad(this.funObjLocal);
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addALoad(this.argsLocal);
            this.cfw.addPush(this.scriptOrFn.isInStrictMode());
            this.cfw.addPush(this.scriptOrFn.hasRestParameter());
            ClassFileWriter classFileWriter4 = this.cfw;
            if (!z2) {
                classFileWriter4.addLoadThis();
                this.cfw.addInvoke(182, "org/mozilla/javascript/BaseFunction", "getHomeObject", "()Lorg/mozilla/javascript/Scriptable;");
            } else {
                int iAcquireLabel3 = classFileWriter4.acquireLabel();
                int iAcquireLabel4 = this.cfw.acquireLabel();
                this.cfw.addALoad(this.variableObjectLocal);
                this.cfw.add(ByteCode.INSTANCEOF, "org/mozilla/javascript/NativeCall");
                this.cfw.add(153, iAcquireLabel3);
                this.cfw.addALoad(this.variableObjectLocal);
                this.cfw.add(192, "org/mozilla/javascript/NativeCall");
                this.cfw.addInvoke(182, "org/mozilla/javascript/NativeCall", "getHomeObject", "()Lorg/mozilla/javascript/Scriptable;");
                this.cfw.add(167, iAcquireLabel4);
                this.cfw.markLabel(iAcquireLabel3);
                this.cfw.add(1);
                this.cfw.markLabel(iAcquireLabel4);
            }
            addScriptRuntimeInvoke(z2 ? "createArrowFunctionActivation" : "createFunctionActivation", "(Lorg/mozilla/javascript/NativeFunction;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;ZZLorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
            this.cfw.addAStore(this.variableObjectLocal);
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            addScriptRuntimeInvoke("enterActivationFunction", "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)V");
            str = "activation";
        } else {
            classFileWriter3.addALoad(this.funObjLocal);
            this.cfw.addALoad(this.thisObjLocal);
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addPush(0);
            addScriptRuntimeInvoke("initScript", "(Lorg/mozilla/javascript/NativeFunction;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Z)V");
            str = "global";
        }
        this.enterAreaStartLabel = this.cfw.acquireLabel();
        this.epilogueLabel = this.cfw.acquireLabel();
        this.cfw.markLabel(this.enterAreaStartLabel);
        generateNestedFunctionInits();
        if (this.compilerEnv.isGenerateDebugInfo()) {
            ClassFileWriter classFileWriter5 = this.cfw;
            classFileWriter5.addVariableDescriptor(str, "Lorg/mozilla/javascript/Scriptable;", classFileWriter5.getCurrentCodeOffset(), this.variableObjectLocal);
        }
        OptFunctionNode optFunctionNode2 = this.fnCurrent;
        if (optFunctionNode2 == null) {
            this.popvLocal = getNewWordLocal();
            Codegen.pushUndefined(this.cfw);
            this.cfw.addAStore(this.popvLocal);
            int endLineno = this.scriptOrFn.getEndLineno();
            if (endLineno != -1) {
                this.cfw.addLineNumberEntry((short) endLineno);
                return;
            }
            return;
        }
        if (optFunctionNode2.itsContainsCalls0) {
            this.itsZeroArgArray = getNewWordLocal();
            this.cfw.add(178, "org/mozilla/javascript/ScriptRuntime", "emptyArgs", "[Ljava/lang/Object;");
            this.cfw.addAStore(this.itsZeroArgArray);
        }
        if (this.fnCurrent.itsContainsCalls1) {
            this.itsOneArgArray = getNewWordLocal();
            this.cfw.addPush(1);
            this.cfw.add(189, "java/lang/Object");
            this.cfw.addAStore(this.itsOneArgArray);
        }
    }

    private void generateGetGeneratorResumptionPoint() {
        this.cfw.addALoad(this.generatorStateLocal);
        this.cfw.add(180, "org/mozilla/javascript/optimizer/OptRuntime$GeneratorState", "resumptionPoint", "I");
    }

    private void generateSetGeneratorResumptionPoint(int i) {
        this.cfw.addALoad(this.generatorStateLocal);
        this.cfw.addLoadConstant(i);
        this.cfw.add(181, "org/mozilla/javascript/optimizer/OptRuntime$GeneratorState", "resumptionPoint", "I");
    }

    private void generateGetGeneratorStackState() {
        this.cfw.addALoad(this.generatorStateLocal);
        addOptRuntimeInvoke("getGeneratorStackState", "(Ljava/lang/Object;)[Ljava/lang/Object;");
    }

    private void generateEpilogue() {
        ClassFileWriter classFileWriter;
        if (this.compilerEnv.isGenerateObserverCount()) {
            addInstructionCount();
        }
        if (this.isGenerator) {
            Map<Node, int[]> liveLocals = ((FunctionNode) this.scriptOrFn).getLiveLocals();
            if (liveLocals != null) {
                for (Node node : ((FunctionNode) this.scriptOrFn).getResumptionPoints()) {
                    int[] iArr = liveLocals.get(node);
                    if (iArr != null) {
                        this.cfw.markTableSwitchCase(this.generatorSwitch, getNextGeneratorState(node));
                        generateGetGeneratorLocalsState();
                        int i = 0;
                        while (true) {
                            int length = iArr.length;
                            classFileWriter = this.cfw;
                            if (i >= length) {
                                break;
                            }
                            classFileWriter.add(89);
                            this.cfw.addLoadConstant(i);
                            this.cfw.add(50);
                            this.cfw.addAStore(iArr[i]);
                            i++;
                        }
                        classFileWriter.add(87);
                        this.cfw.add(167, getTargetLabel(node));
                    }
                }
            }
            Map<Node, FinallyReturnPoint> map = this.finallys;
            if (map != null) {
                for (Map.Entry<Node, FinallyReturnPoint> entry : map.entrySet()) {
                    if (entry.getKey().getType() == 138) {
                        FinallyReturnPoint value = entry.getValue();
                        this.cfw.markLabel(value.tableLabel, 1);
                        int iAddTableSwitch = this.cfw.addTableSwitch(0, value.jsrPoints.size() - 1);
                        this.cfw.markTableSwitchDefault(iAddTableSwitch);
                        int i2 = 0;
                        for (int i3 = 0; i3 < value.jsrPoints.size(); i3++) {
                            this.cfw.markTableSwitchCase(iAddTableSwitch, i2);
                            this.cfw.add(167, value.jsrPoints.get(i3).intValue());
                            i2++;
                        }
                    }
                }
            }
        }
        int i4 = this.epilogueLabel;
        if (i4 != -1) {
            this.cfw.markLabel(i4);
        }
        if (this.isGenerator) {
            if (((FunctionNode) this.scriptOrFn).getResumptionPoints() != null) {
                this.cfw.markTableSwitchDefault(this.generatorSwitch);
            }
            generateSetGeneratorResumptionPoint(-1);
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addALoad(this.generatorStateLocal);
            addOptRuntimeInvoke("throwStopIteration", "(Ljava/lang/Object;Ljava/lang/Object;)V");
            Codegen.pushUndefined(this.cfw);
            this.cfw.add(176);
            return;
        }
        if (this.hasVarsInRegs) {
            this.cfw.add(176);
            return;
        }
        if (this.fnCurrent == null) {
            this.cfw.addALoad(this.popvLocal);
            this.cfw.add(176);
            return;
        }
        generateActivationExit();
        this.cfw.add(176);
        int iAcquireLabel = this.cfw.acquireLabel();
        this.cfw.markHandler(iAcquireLabel);
        short newWordLocal = getNewWordLocal();
        this.cfw.addAStore(newWordLocal);
        generateActivationExit();
        this.cfw.addALoad(newWordLocal);
        releaseWordLocal(newWordLocal);
        this.cfw.add(ByteCode.ATHROW);
        this.cfw.addExceptionHandler(this.enterAreaStartLabel, this.epilogueLabel, iAcquireLabel, null);
    }

    private void generateGetGeneratorLocalsState() {
        this.cfw.addALoad(this.generatorStateLocal);
        addOptRuntimeInvoke("getGeneratorLocalsState", "(Ljava/lang/Object;)[Ljava/lang/Object;");
    }

    private void generateSetGeneratorReturnValue() {
        this.cfw.addALoad(this.generatorStateLocal);
        this.cfw.add(95);
        addOptRuntimeInvoke("setGeneratorReturnValue", "(Ljava/lang/Object;Ljava/lang/Object;)V");
    }

    private void generateActivationExit() {
        if (this.fnCurrent == null || this.hasVarsInRegs) {
            throw Kit.codeBug();
        }
        this.cfw.addALoad(this.contextLocal);
        addScriptRuntimeInvoke("exitActivationFunction", "(Lorg/mozilla/javascript/Context;)V");
    }

    private void generateStatement(Node node) {
        updateLineNumber(node);
        int type = node.getType();
        Node firstChild = node.getFirstChild();
        if (type == 55) {
            generateExpression(firstChild, node);
            if (this.compilerEnv.isGenerateObserverCount()) {
                addInstructionCount();
            }
            generateThrowJavaScriptException();
            return;
        }
        if (type == 56) {
            if (this.compilerEnv.isGenerateObserverCount()) {
                addInstructionCount();
            }
            this.cfw.addALoad(getLocalBlockRegister(node));
            this.cfw.add(ByteCode.ATHROW);
            return;
        }
        if (type != 70) {
            if (type == 90) {
                visitTryCatchFinally((Jump) node, firstChild);
                return;
            }
            int i = 1;
            if (type == 122) {
                OptFunctionNode optFunctionNode = OptFunctionNode.get(this.scriptOrFn, node.getExistingIntProp(1));
                int functionType = optFunctionNode.fnode.getFunctionType();
                if (functionType == 3) {
                    visitFunction(optFunctionNode, functionType);
                    return;
                } else {
                    if (functionType != 1) {
                        throw Codegen.badTree();
                    }
                    return;
                }
            }
            if (type != 127) {
                if (type != 136) {
                    if (type == 138) {
                        if (this.isGenerator) {
                            if (this.compilerEnv.isGenerateObserverCount()) {
                                saveCurrentCodeOffset();
                            }
                            this.cfw.setStackTop((short) 1);
                            short newWordLocal = getNewWordLocal();
                            int iAcquireLabel = this.cfw.acquireLabel();
                            int iAcquireLabel2 = this.cfw.acquireLabel();
                            this.cfw.markLabel(iAcquireLabel);
                            generateIntegerWrap();
                            this.cfw.addAStore(newWordLocal);
                            while (firstChild != null) {
                                generateStatement(firstChild);
                                firstChild = firstChild.getNext();
                            }
                            this.cfw.addALoad(newWordLocal);
                            this.cfw.add(192, "java/lang/Integer");
                            generateIntegerUnwrap();
                            FinallyReturnPoint finallyReturnPoint = this.finallys.get(node);
                            finallyReturnPoint.tableLabel = this.cfw.acquireLabel();
                            this.cfw.add(167, finallyReturnPoint.tableLabel);
                            this.cfw.setStackTop((short) 0);
                            releaseWordLocal(newWordLocal);
                            this.cfw.markLabel(iAcquireLabel2);
                            return;
                        }
                        return;
                    }
                    if (type != 141) {
                        if (type == 155) {
                            boolean z = this.inLocalBlock;
                            this.inLocalBlock = true;
                            short newWordLocal2 = getNewWordLocal();
                            if (this.isGenerator) {
                                this.cfw.add(1);
                                this.cfw.addAStore(newWordLocal2);
                            }
                            node.putIntProp(2, newWordLocal2);
                            while (firstChild != null) {
                                generateStatement(firstChild);
                                firstChild = firstChild.getNext();
                            }
                            releaseWordLocal(newWordLocal2);
                            node.removeProp(2);
                            this.inLocalBlock = z;
                            return;
                        }
                        if (type != 174) {
                            switch (type) {
                                case 2:
                                    generateExpression(firstChild, node);
                                    this.cfw.addALoad(this.contextLocal);
                                    this.cfw.addALoad(this.variableObjectLocal);
                                    addScriptRuntimeInvoke("enterWith", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
                                    this.cfw.addAStore(this.variableObjectLocal);
                                    incReferenceWordLocal(this.variableObjectLocal);
                                    return;
                                case 3:
                                    this.cfw.addALoad(this.variableObjectLocal);
                                    addScriptRuntimeInvoke("leaveWith", "(Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
                                    this.cfw.addAStore(this.variableObjectLocal);
                                    decReferenceWordLocal(this.variableObjectLocal);
                                    return;
                                case 4:
                                    break;
                                default:
                                    switch (type) {
                                        case 62:
                                            this.cfw.setStackTop((short) 0);
                                            int localBlockRegister = getLocalBlockRegister(node);
                                            int existingIntProp = node.getExistingIntProp(14);
                                            String string = firstChild.getType() == 44 ? firstChild.getString() : null;
                                            generateExpression(firstChild.getNext(), node);
                                            ClassFileWriter classFileWriter = this.cfw;
                                            if (existingIntProp == 0) {
                                                classFileWriter.add(1);
                                            } else {
                                                classFileWriter.addALoad(localBlockRegister);
                                            }
                                            ClassFileWriter classFileWriter2 = this.cfw;
                                            if (string != null) {
                                                classFileWriter2.addPush(string);
                                            } else {
                                                classFileWriter2.add(1);
                                            }
                                            this.cfw.addALoad(this.contextLocal);
                                            this.cfw.addALoad(this.variableObjectLocal);
                                            addScriptRuntimeInvoke("newCatchScope", "(Ljava/lang/Throwable;Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
                                            this.cfw.addAStore(localBlockRegister);
                                            return;
                                        case 63:
                                        case 64:
                                        case 65:
                                        case 66:
                                            generateExpression(firstChild, node);
                                            this.cfw.addALoad(this.contextLocal);
                                            this.cfw.addALoad(this.variableObjectLocal);
                                            if (type == 63) {
                                                i = 0;
                                            } else if (type != 64) {
                                                i = type == 66 ? 6 : 2;
                                            }
                                            this.cfw.addPush(i);
                                            addScriptRuntimeInvoke("enumInit", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Ljava/lang/Object;");
                                            this.cfw.addAStore(getLocalBlockRegister(node));
                                            return;
                                        default:
                                            switch (type) {
                                                case 143:
                                                case 144:
                                                case 146:
                                                case 150:
                                                    break;
                                                case 145:
                                                    if (this.compilerEnv.isGenerateObserverCount()) {
                                                        addInstructionCount();
                                                    }
                                                    this.cfw.markLabel(getTargetLabel(node));
                                                    if (this.compilerEnv.isGenerateObserverCount()) {
                                                        saveCurrentCodeOffset();
                                                        return;
                                                    }
                                                    return;
                                                case 147:
                                                    if (firstChild.getType() == 61) {
                                                        visitSetVar(firstChild, firstChild.getFirstChild(), false);
                                                        return;
                                                    }
                                                    if (firstChild.getType() == 170) {
                                                        visitSetConstVar(firstChild, firstChild.getFirstChild(), false);
                                                        return;
                                                    }
                                                    if (firstChild.getType() == 78 || firstChild.getType() == 179) {
                                                        generateYieldPoint(firstChild, false);
                                                        return;
                                                    }
                                                    generateExpression(firstChild, node);
                                                    int intProp = node.getIntProp(8, -1);
                                                    ClassFileWriter classFileWriter3 = this.cfw;
                                                    if (intProp != -1) {
                                                        classFileWriter3.add(88);
                                                        return;
                                                    } else {
                                                        classFileWriter3.add(87);
                                                        return;
                                                    }
                                                case 148:
                                                    generateExpression(firstChild, node);
                                                    if (this.popvLocal < 0) {
                                                        this.popvLocal = getNewWordLocal();
                                                    }
                                                    this.cfw.addAStore(this.popvLocal);
                                                    return;
                                                case 149:
                                                    break;
                                                default:
                                                    throw Codegen.badTree();
                                            }
                                            break;
                                    }
                                case 5:
                                case 6:
                                case 7:
                                    if (this.compilerEnv.isGenerateObserverCount()) {
                                        addInstructionCount();
                                    }
                                    visitGoto((Jump) node, type, firstChild);
                                    return;
                            }
                        } else {
                            return;
                        }
                    }
                }
                if (this.compilerEnv.isGenerateObserverCount()) {
                    addInstructionCount(1);
                }
                while (firstChild != null) {
                    generateStatement(firstChild);
                    firstChild = firstChild.getNext();
                }
                return;
            }
            if (this.compilerEnv.isGenerateObserverCount()) {
                addInstructionCount();
            }
            visitSwitch((Jump) node, firstChild);
            return;
        }
        if (firstChild != null) {
            generateExpression(firstChild, node);
        } else if (type == 4) {
            Codegen.pushUndefined(this.cfw);
        } else {
            int i2 = this.popvLocal;
            if (i2 < 0) {
                throw Codegen.badTree();
            }
            this.cfw.addALoad(i2);
        }
        if (this.isGenerator) {
            generateSetGeneratorReturnValue();
        }
        if (this.compilerEnv.isGenerateObserverCount()) {
            addInstructionCount();
        }
        if (this.epilogueLabel == -1) {
            if (!this.hasVarsInRegs) {
                throw Codegen.badTree();
            }
            this.epilogueLabel = this.cfw.acquireLabel();
        }
        this.cfw.add(167, this.epilogueLabel);
    }

    private void generateIntegerWrap() {
        this.cfw.addInvoke(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
    }

    private void generateIntegerUnwrap() {
        this.cfw.addInvoke(182, "java/lang/Integer", "intValue", "()I");
    }

    private void generateThrowJavaScriptException() {
        this.cfw.add(187, "org/mozilla/javascript/JavaScriptException");
        this.cfw.add(90);
        this.cfw.add(95);
        this.cfw.addPush(this.scriptOrFn.getSourceName());
        this.cfw.addPush(this.itsLineNumber);
        this.cfw.addInvoke(183, "org/mozilla/javascript/JavaScriptException", "<init>", "(Ljava/lang/Object;Ljava/lang/String;I)V");
        this.cfw.add(ByteCode.ATHROW);
    }

    private int getNextGeneratorState(Node node) {
        return ((FunctionNode) this.scriptOrFn).getResumptionPoints().indexOf(node) + 1;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:133:0x041b  */
    /* JADX WARN: Code duplicated, block: B:135:0x0426  */
    /* JADX WARN: Code duplicated, block: B:161:0x04ee  */
    /* JADX WARN: Code duplicated, block: B:169:0x0542  */
    /* JADX WARN: Code duplicated, block: B:185:0x0599  */
    /* JADX WARN: Code duplicated, block: B:187:0x05a1  */
    /* JADX WARN: Code duplicated, block: B:189:0x05ab  */
    /* JADX WARN: Code duplicated, block: B:191:0x05af  */
    /* JADX WARN: Code duplicated, block: B:193:0x05b3  */
    /* JADX WARN: Code duplicated, block: B:195:0x05b7  */
    /* JADX WARN: Code duplicated, block: B:197:0x05bb  */
    /* JADX WARN: Code duplicated, block: B:229:0x0683  */
    /* JADX WARN: Code duplicated, block: B:231:0x0696  */
    private void generateExpression(Node node, Node node2) {
        int i;
        int i2;
        int intProp;
        int intProp2;
        OptFunctionNode optFunctionNode;
        String str;
        String str2;
        int type = node.getType();
        Node firstChild = node.getFirstChild();
        if (type == 163) {
            if (firstChild.getType() == 45) {
                i = 8;
                i2 = -1;
                intProp = firstChild.getIntProp(8, -1);
            } else {
                i = 8;
                i2 = -1;
                intProp = -1;
            }
            if (intProp != i2) {
                firstChild.removeProp(i);
                generateExpression(firstChild, node);
                firstChild.putIntProp(i, intProp);
                return;
            } else {
                generateExpression(firstChild, node);
                addDoubleWrap();
                return;
            }
        }
        if (type == 164) {
            generateExpression(firstChild, node);
            addObjectToDouble();
            return;
        }
        if (type != 179) {
            if (type != 180) {
                int i3 = 0;
                switch (type) {
                    case 8:
                        visitSetName(node, firstChild);
                        return;
                    case 9:
                    case 10:
                    case 11:
                    case 18:
                    case 19:
                    case 20:
                        visitBitOp(node, type, firstChild);
                        return;
                    case 12:
                    case 13:
                        int iAcquireLabel = this.cfw.acquireLabel();
                        int iAcquireLabel2 = this.cfw.acquireLabel();
                        visitIfJumpEqOp(node, firstChild, iAcquireLabel, iAcquireLabel2);
                        addJumpedBooleanWrap(iAcquireLabel, iAcquireLabel2);
                        return;
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                        int iAcquireLabel3 = this.cfw.acquireLabel();
                        int iAcquireLabel4 = this.cfw.acquireLabel();
                        visitIfJumpRelOp(node, firstChild, iAcquireLabel3, iAcquireLabel4);
                        addJumpedBooleanWrap(iAcquireLabel3, iAcquireLabel4);
                        return;
                    case 21:
                        generateExpression(firstChild, node);
                        generateExpression(firstChild.getNext(), node);
                        int intProp3 = node.getIntProp(8, -1);
                        if (intProp3 == 0) {
                            this.cfw.add(99);
                            return;
                        }
                        if (intProp3 == 1) {
                            this.cfw.addALoad(this.contextLocal);
                            addOptRuntimeInvoke("add", "(DLjava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
                            return;
                        }
                        ClassFileWriter classFileWriter = this.cfw;
                        if (intProp3 == 2) {
                            classFileWriter.addALoad(this.contextLocal);
                            addOptRuntimeInvoke("add", "(Ljava/lang/Object;DLorg/mozilla/javascript/Context;)Ljava/lang/Object;");
                            return;
                        } else {
                            classFileWriter.addALoad(this.contextLocal);
                            addDynamicInvoke("MATH:ADD", Signatures.MATH_ADD);
                            return;
                        }
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                        visitArithmetic(node, type, firstChild, node2);
                        return;
                    case 26:
                        int iAcquireLabel5 = this.cfw.acquireLabel();
                        int iAcquireLabel6 = this.cfw.acquireLabel();
                        int iAcquireLabel7 = this.cfw.acquireLabel();
                        generateIfJump(firstChild, node, iAcquireLabel5, iAcquireLabel6);
                        this.cfw.markLabel(iAcquireLabel5);
                        this.cfw.add(178, "java/lang/Boolean", "FALSE", "Ljava/lang/Boolean;");
                        this.cfw.add(167, iAcquireLabel7);
                        this.cfw.markLabel(iAcquireLabel6);
                        this.cfw.add(178, "java/lang/Boolean", "TRUE", "Ljava/lang/Boolean;");
                        this.cfw.markLabel(iAcquireLabel7);
                        this.cfw.adjustStackTop(-1);
                        return;
                    case 27:
                        visitBitNot(node, firstChild);
                        return;
                    case 28:
                        int intProp4 = node.getIntProp(8, -1);
                        generateExpression(firstChild, node);
                        if (intProp4 == -1) {
                            addObjectToDouble();
                            addDoubleWrap();
                            return;
                        }
                        return;
                    case 29:
                        int intProp5 = node.getIntProp(8, -1);
                        generateExpression(firstChild, node);
                        if (intProp5 == -1) {
                            addObjectToNumeric();
                            addScriptRuntimeInvoke("negate", "(Ljava/lang/Number;)Ljava/lang/Number;");
                            return;
                        } else {
                            this.cfw.add(119);
                            return;
                        }
                    case 30:
                        intProp2 = node.getIntProp(10, 0);
                        if (intProp2 == 0) {
                            optFunctionNode = (OptFunctionNode) node.getProp(9);
                            if (optFunctionNode != null) {
                                visitOptimizedCall(node, optFunctionNode, type, firstChild);
                                return;
                            } else if (type == 43) {
                                visitStandardCall(node, firstChild);
                                return;
                            } else {
                                visitStandardNew(node, firstChild);
                                return;
                            }
                        }
                        visitSpecialCall(node, type, intProp2, firstChild);
                        return;
                    case 31:
                        boolean z = firstChild.getType() == 54;
                        generateExpression(firstChild, node);
                        generateExpression(firstChild.getNext(), node);
                        int intProp6 = node.getIntProp(31, 0);
                        ClassFileWriter classFileWriter2 = this.cfw;
                        if (intProp6 == 1) {
                            classFileWriter2.add(88);
                            this.cfw.addLoadConstant(0);
                            addScriptRuntimeInvoke("throwDeleteOnSuperPropertyNotAllowed", "()V");
                            return;
                        } else {
                            classFileWriter2.addALoad(this.contextLocal);
                            this.cfw.addPush(z);
                            addScriptRuntimeInvoke("delete", "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Z)Ljava/lang/Object;");
                            return;
                        }
                    case 32:
                        generateExpression(firstChild, node);
                        addScriptRuntimeInvoke("typeof", "(Ljava/lang/Object;)Ljava/lang/String;");
                        return;
                    case 33:
                    case 34:
                        visitGetProp(node, firstChild);
                        return;
                    case 37:
                        visitSetProp(type, node, firstChild);
                        return;
                    case 39:
                        generateExpression(firstChild, node);
                        if (node.getIntProp(30, 0) == 1) {
                            int iAcquireLabel8 = this.cfw.acquireLabel();
                            int iAcquireLabel9 = this.cfw.acquireLabel();
                            this.cfw.add(89);
                            addOptRuntimeInvoke("isNullOrUndefined", Signatures.MATH_TO_BOOLEAN);
                            this.cfw.add(153, iAcquireLabel8);
                            this.cfw.add(87);
                            this.cfw.add(178, "org/mozilla/javascript/Undefined", "instance", "Ljava/lang/Object;");
                            this.cfw.add(167, iAcquireLabel9);
                            this.cfw.markLabel(iAcquireLabel8);
                            finishGetElemGeneration(node, firstChild);
                            this.cfw.markLabel(iAcquireLabel9);
                            return;
                        }
                        finishGetElemGeneration(node, firstChild);
                        return;
                    case 41:
                        visitSetElem(type, node, firstChild);
                        return;
                    case 98:
                        Node next = firstChild.getNext();
                        while (true) {
                            Node node3 = firstChild;
                            firstChild = next;
                            if (firstChild != null) {
                                generateExpression(node3, node);
                                this.cfw.add(87);
                                next = firstChild.getNext();
                            } else {
                                generateExpression(node3, node);
                                return;
                            }
                        }
                        break;
                    case 115:
                        Node next2 = firstChild.getNext();
                        Node next3 = next2.getNext();
                        generateExpression(firstChild, node);
                        addDynamicInvoke("MATH:TOBOOLEAN", Signatures.MATH_TO_BOOLEAN);
                        int iAcquireLabel10 = this.cfw.acquireLabel();
                        this.cfw.add(153, iAcquireLabel10);
                        int stackTop = this.cfw.getStackTop();
                        generateExpression(next2, node);
                        int iAcquireLabel11 = this.cfw.acquireLabel();
                        this.cfw.add(167, iAcquireLabel11);
                        this.cfw.markLabel(iAcquireLabel10, stackTop);
                        generateExpression(next3, node);
                        this.cfw.markLabel(iAcquireLabel11);
                        return;
                    case 122:
                        if (this.fnCurrent == null && node2.getType() == 150) {
                            return;
                        }
                        OptFunctionNode optFunctionNode2 = OptFunctionNode.get(this.scriptOrFn, node.getExistingIntProp(1));
                        int functionType = optFunctionNode2.fnode.getFunctionType();
                        if (functionType != 2 && functionType != 4) {
                            throw Codegen.badTree();
                        }
                        visitFunction(optFunctionNode2, functionType);
                        return;
                    case 139:
                        generateExpression(firstChild, node);
                        this.cfw.add(87);
                        Codegen.pushUndefined(this.cfw);
                        return;
                    case 156:
                        generateExpression(firstChild, node);
                        Node next4 = firstChild.getNext();
                        if (type == 156) {
                            this.cfw.add(89);
                            this.cfw.addALoad(this.contextLocal);
                            addScriptRuntimeInvoke("refGet", "(Lorg/mozilla/javascript/Ref;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
                        }
                        generateExpression(next4, node);
                        this.cfw.addALoad(this.contextLocal);
                        this.cfw.addALoad(this.variableObjectLocal);
                        addScriptRuntimeInvoke("refSet", "(Lorg/mozilla/javascript/Ref;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
                        return;
                    case 160:
                        visitDotQuery(node, firstChild);
                        return;
                    case 173:
                        Node next5 = firstChild.getNext();
                        Node next6 = next5.getNext();
                        generateStatement(firstChild);
                        generateExpression(next5.getFirstChild(), next5);
                        generateStatement(next6);
                        return;
                    case 185:
                        generateExpression(firstChild, node);
                        int iAcquireLabel12 = this.cfw.acquireLabel();
                        this.cfw.add(89);
                        addOptRuntimeInvoke("isNullOrUndefined", Signatures.MATH_TO_BOOLEAN);
                        this.cfw.add(153, iAcquireLabel12);
                        this.cfw.add(87);
                        generateExpression(firstChild.getNext(), node);
                        this.cfw.markLabel(iAcquireLabel12);
                        return;
                    default:
                        switch (type) {
                            case 43:
                                intProp2 = node.getIntProp(10, 0);
                                if (intProp2 == 0) {
                                    optFunctionNode = (OptFunctionNode) node.getProp(9);
                                    if (optFunctionNode != null) {
                                        visitOptimizedCall(node, optFunctionNode, type, firstChild);
                                        return;
                                    } else if (type == 43) {
                                        visitStandardCall(node, firstChild);
                                        return;
                                    } else {
                                        visitStandardNew(node, firstChild);
                                        return;
                                    }
                                }
                                visitSpecialCall(node, type, intProp2, firstChild);
                                return;
                            case 44:
                                this.cfw.addALoad(this.variableObjectLocal);
                                this.cfw.addALoad(this.contextLocal);
                                addDynamicInvoke("NAME:GET:" + node.getString(), Signatures.NAME_GET);
                                return;
                            case 45:
                                double d = node.getDouble();
                                if (node.getIntProp(8, -1) != -1) {
                                    this.cfw.addPush(d);
                                    return;
                                } else {
                                    this.codegen.pushNumberAsObject(this.cfw, d);
                                    return;
                                }
                            case 46:
                                this.cfw.addPush(node.getString());
                                return;
                            case 47:
                                this.cfw.add(1);
                                return;
                            case 48:
                                this.cfw.addALoad(this.thisObjLocal);
                                return;
                            case 49:
                                this.cfw.add(178, "java/lang/Boolean", "FALSE", "Ljava/lang/Boolean;");
                                return;
                            case 50:
                                this.cfw.add(178, "java/lang/Boolean", "TRUE", "Ljava/lang/Boolean;");
                                return;
                            case 51:
                            case 52:
                                int iAcquireLabel13 = this.cfw.acquireLabel();
                                int iAcquireLabel14 = this.cfw.acquireLabel();
                                visitIfJumpEqOp(node, firstChild, iAcquireLabel13, iAcquireLabel14);
                                addJumpedBooleanWrap(iAcquireLabel13, iAcquireLabel14);
                                return;
                            case 53:
                                this.cfw.addALoad(this.contextLocal);
                                this.cfw.addALoad(this.variableObjectLocal);
                                this.cfw.add(178, this.codegen.mainClassName, this.codegen.getCompiledRegexpName(this.scriptOrFn, node.getExistingIntProp(4)), "Ljava/lang/Object;");
                                this.cfw.addInvoke(184, "org/mozilla/javascript/ScriptRuntime", "wrapRegExp", "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
                                return;
                            case 54:
                                while (firstChild != null) {
                                    generateExpression(firstChild, node);
                                    firstChild = firstChild.getNext();
                                }
                                this.cfw.addALoad(this.variableObjectLocal);
                                this.cfw.addALoad(this.contextLocal);
                                addDynamicInvoke("NAME:BIND:" + node.getString(), Signatures.NAME_BIND);
                                return;
                            default:
                                switch (type) {
                                    case 57:
                                    case 58:
                                        int iAcquireLabel15 = this.cfw.acquireLabel();
                                        int iAcquireLabel16 = this.cfw.acquireLabel();
                                        visitIfJumpRelOp(node, firstChild, iAcquireLabel15, iAcquireLabel16);
                                        addJumpedBooleanWrap(iAcquireLabel15, iAcquireLabel16);
                                        return;
                                    case 59:
                                        this.cfw.addALoad(getLocalBlockRegister(node));
                                        return;
                                    case 60:
                                        visitGetVar(node);
                                        return;
                                    case 61:
                                        visitSetVar(node, firstChild, true);
                                        return;
                                    default:
                                        switch (type) {
                                            case 67:
                                            case 68:
                                                this.cfw.addALoad(getLocalBlockRegister(node));
                                                this.cfw.addALoad(this.contextLocal);
                                                if (type == 67) {
                                                    addScriptRuntimeInvoke("enumNext", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/Boolean;");
                                                    return;
                                                } else {
                                                    addScriptRuntimeInvoke("enumId", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
                                                    return;
                                                }
                                            case 69:
                                                this.cfw.add(42);
                                                return;
                                            default:
                                                switch (type) {
                                                    case 71:
                                                        visitArrayLiteral(node, firstChild, false);
                                                        return;
                                                    case 72:
                                                        visitObjectLiteral(node, firstChild, false);
                                                        return;
                                                    case 73:
                                                        generateExpression(firstChild, node);
                                                        if (node.getIntProp(30, 0) == 1) {
                                                            int iAcquireLabel17 = this.cfw.acquireLabel();
                                                            int iAcquireLabel18 = this.cfw.acquireLabel();
                                                            this.cfw.add(89);
                                                            addOptRuntimeInvoke("isNullOrUndefined", Signatures.MATH_TO_BOOLEAN);
                                                            this.cfw.add(153, iAcquireLabel17);
                                                            this.cfw.add(167, iAcquireLabel18);
                                                            this.cfw.markLabel(iAcquireLabel17);
                                                            this.cfw.add(192, "org/mozilla/javascript/Ref");
                                                            finishGetRefGeneration();
                                                            this.cfw.markLabel(iAcquireLabel18);
                                                            return;
                                                        }
                                                        finishGetRefGeneration();
                                                        return;
                                                    case 74:
                                                        generateExpression(firstChild, node);
                                                        Node next7 = firstChild.getNext();
                                                        if (type == 156) {
                                                            this.cfw.add(89);
                                                            this.cfw.addALoad(this.contextLocal);
                                                            addScriptRuntimeInvoke("refGet", "(Lorg/mozilla/javascript/Ref;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
                                                        }
                                                        generateExpression(next7, node);
                                                        this.cfw.addALoad(this.contextLocal);
                                                        this.cfw.addALoad(this.variableObjectLocal);
                                                        addScriptRuntimeInvoke("refSet", "(Lorg/mozilla/javascript/Ref;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
                                                        return;
                                                    case 75:
                                                        generateExpression(firstChild, node);
                                                        this.cfw.addALoad(this.contextLocal);
                                                        addScriptRuntimeInvoke("refDel", "(Lorg/mozilla/javascript/Ref;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
                                                        return;
                                                    case 76:
                                                        generateFunctionAndThisObj(firstChild, node);
                                                        pushThisFromLastScriptable();
                                                        generateCallArgArray(node, firstChild.getNext(), false);
                                                        this.cfw.addALoad(this.contextLocal);
                                                        addScriptRuntimeInvoke("callRef", "(Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Lorg/mozilla/javascript/Ref;");
                                                        return;
                                                    case 77:
                                                        generateExpression(firstChild, node);
                                                        if (node.getIntProp(30, 0) == 1) {
                                                            int iAcquireLabel19 = this.cfw.acquireLabel();
                                                            int iAcquireLabel20 = this.cfw.acquireLabel();
                                                            this.cfw.add(89);
                                                            addOptRuntimeInvoke("isNullOrUndefined", Signatures.MATH_TO_BOOLEAN);
                                                            this.cfw.add(153, iAcquireLabel19);
                                                            this.cfw.add(87);
                                                            this.cfw.add(178, "org/mozilla/javascript/Undefined", "instance", "Ljava/lang/Object;");
                                                            this.cfw.add(167, iAcquireLabel20);
                                                            this.cfw.markLabel(iAcquireLabel19);
                                                            finishRefSpecialGeneration(node);
                                                            this.cfw.markLabel(iAcquireLabel20);
                                                            return;
                                                        }
                                                        finishRefSpecialGeneration(node);
                                                        return;
                                                    case 78:
                                                        break;
                                                    case 79:
                                                        this.cfw.addALoad(this.variableObjectLocal);
                                                        this.cfw.add(192, "org/mozilla/javascript/NativeCall");
                                                        this.cfw.addInvoke(182, "org/mozilla/javascript/NativeCall", "getHomeObject", "()Lorg/mozilla/javascript/Scriptable;");
                                                        int iAcquireLabel21 = this.cfw.acquireLabel();
                                                        int iAcquireLabel22 = this.cfw.acquireLabel();
                                                        this.cfw.add(89);
                                                        this.cfw.add(ByteCode.IFNONNULL, iAcquireLabel22);
                                                        this.cfw.add(87);
                                                        this.cfw.add(178, "org/mozilla/javascript/Undefined", "instance", "Ljava/lang/Object;");
                                                        this.cfw.add(167, iAcquireLabel21);
                                                        this.cfw.markLabel(iAcquireLabel22);
                                                        this.cfw.addInvoke(185, "org/mozilla/javascript/Scriptable", "getPrototype", "()Lorg/mozilla/javascript/Scriptable;");
                                                        this.cfw.markLabel(iAcquireLabel21);
                                                        return;
                                                    case 80:
                                                        visitStrictSetName(node, firstChild);
                                                        return;
                                                    case 81:
                                                        visitExponentiation(node, firstChild, node2);
                                                        return;
                                                    case 82:
                                                        generateExpression(firstChild, node);
                                                        this.cfw.addALoad(this.contextLocal);
                                                        addScriptRuntimeInvoke("setDefaultNamespace", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
                                                        return;
                                                    case 83:
                                                        generateExpression(firstChild, node);
                                                        this.cfw.addALoad(this.contextLocal);
                                                        addScriptRuntimeInvoke("escapeAttributeValue", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/String;");
                                                        return;
                                                    case 84:
                                                        generateExpression(firstChild, node);
                                                        this.cfw.addALoad(this.contextLocal);
                                                        addScriptRuntimeInvoke("escapeTextValue", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/String;");
                                                        return;
                                                    case 85:
                                                    case 86:
                                                    case 87:
                                                    case 88:
                                                        int intProp7 = node.getIntProp(16, 0);
                                                        do {
                                                            generateExpression(firstChild, node);
                                                            firstChild = firstChild.getNext();
                                                        } while (firstChild != null);
                                                        this.cfw.addALoad(this.contextLocal);
                                                        switch (type) {
                                                            case 85:
                                                                str = "memberRef";
                                                                str2 = "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;I)Lorg/mozilla/javascript/Ref;";
                                                                break;
                                                            case 86:
                                                                str = "memberRef";
                                                                str2 = "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;I)Lorg/mozilla/javascript/Ref;";
                                                                break;
                                                            case 87:
                                                                this.cfw.addALoad(this.variableObjectLocal);
                                                                str = "nameRef";
                                                                str2 = "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Lorg/mozilla/javascript/Ref;";
                                                                break;
                                                            case 88:
                                                                this.cfw.addALoad(this.variableObjectLocal);
                                                                str = "nameRef";
                                                                str2 = "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Lorg/mozilla/javascript/Ref;";
                                                                break;
                                                            default:
                                                                throw Kit.codeBug();
                                                        }
                                                        this.cfw.addPush(intProp7);
                                                        addScriptRuntimeInvoke(str, str2);
                                                        return;
                                                    case 89:
                                                        byte[] byteArray = node.getBigInt().toByteArray();
                                                        this.cfw.add(187, "java/math/BigInteger");
                                                        int i4 = 89;
                                                        this.cfw.add(89);
                                                        this.cfw.addPush(byteArray.length);
                                                        this.cfw.add(188, 8);
                                                        while (true) {
                                                            int length = byteArray.length;
                                                            ClassFileWriter classFileWriter3 = this.cfw;
                                                            if (i3 < length) {
                                                                classFileWriter3.add(i4);
                                                                this.cfw.addPush(i3);
                                                                this.cfw.add(16, byteArray[i3]);
                                                                this.cfw.add(84);
                                                                i3++;
                                                                i4 = 89;
                                                            } else {
                                                                classFileWriter3.addInvoke(183, "java/math/BigInteger", "<init>", "([B)V");
                                                                return;
                                                            }
                                                        }
                                                        break;
                                                    default:
                                                        switch (type) {
                                                            case 117:
                                                            case 118:
                                                                generateExpression(firstChild, node);
                                                                this.cfw.add(89);
                                                                addDynamicInvoke("MATH:TOBOOLEAN", Signatures.MATH_TO_BOOLEAN);
                                                                int iAcquireLabel23 = this.cfw.acquireLabel();
                                                                ClassFileWriter classFileWriter4 = this.cfw;
                                                                if (type == 118) {
                                                                    classFileWriter4.add(153, iAcquireLabel23);
                                                                } else {
                                                                    classFileWriter4.add(154, iAcquireLabel23);
                                                                }
                                                                this.cfw.add(87);
                                                                generateExpression(firstChild.getNext(), node);
                                                                this.cfw.markLabel(iAcquireLabel23);
                                                                return;
                                                            case 119:
                                                            case 120:
                                                                visitIncDec(node);
                                                                return;
                                                            default:
                                                                switch (type) {
                                                                    case 151:
                                                                        visitTypeofname(node);
                                                                        return;
                                                                    case 152:
                                                                        return;
                                                                    case 153:
                                                                        visitSetProp(type, node, firstChild);
                                                                        return;
                                                                    case 154:
                                                                        visitSetElem(type, node, firstChild);
                                                                        return;
                                                                    default:
                                                                        switch (type) {
                                                                            case 169:
                                                                                visitSetConst(node, firstChild);
                                                                                return;
                                                                            case 170:
                                                                                visitSetConstVar(node, firstChild, true);
                                                                                return;
                                                                            case 171:
                                                                                Node next8 = firstChild.getNext();
                                                                                generateStatement(firstChild);
                                                                                generateExpression(next8, node);
                                                                                return;
                                                                            default:
                                                                                throw new RuntimeException("Unexpected node type " + type);
                                                                        }
                                                                }
                                                        }
                                                }
                                                break;
                                        }
                                        break;
                                }
                                break;
                        }
                        break;
                }
            } else {
                visitTemplateLiteral(node);
                return;
            }
        }
        generateYieldPoint(node, true);
    }

    private void finishGetElemGeneration(Node node, Node node2) {
        generateExpression(node2.getNext(), node);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        if (node.getIntProp(31, 0) == 1) {
            this.cfw.addALoad(this.thisObjLocal);
            addDynamicInvoke("PROP:GETELEMENTSUPER", "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;)Ljava/lang/Object;");
        } else if (node.getIntProp(8, -1) != -1) {
            addDynamicInvoke("PROP:GETINDEX", Signatures.PROP_GET_INDEX);
        } else {
            addDynamicInvoke("PROP:GETELEMENT", "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
        }
    }

    private void finishGetRefGeneration() {
        this.cfw.addALoad(this.contextLocal);
        addScriptRuntimeInvoke("refGet", "(Lorg/mozilla/javascript/Ref;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
    }

    private void finishRefSpecialGeneration(Node node) {
        this.cfw.addPush((String) node.getProp(17));
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        addScriptRuntimeInvoke("specialRef", "(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Ref;");
    }

    private Node findNestedYield(Node node) {
        for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNext()) {
            if (firstChild.getType() == 78 || firstChild.getType() == 179) {
                return firstChild;
            }
            Node nodeFindNestedYield = findNestedYield(firstChild);
            if (nodeFindNestedYield != null) {
                return nodeFindNestedYield;
            }
        }
        return null;
    }

    private void generateYieldPoint(Node node, boolean z) {
        if (this.unnestedYields.containsKey(node)) {
            if (z) {
                String str = this.unnestedYields.get(node);
                this.cfw.addALoad(this.variableObjectLocal);
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                addDynamicInvoke("PROP:GETNOWARN:" + str, "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
                return;
            }
            return;
        }
        Node nodeFindNestedYield = findNestedYield(node);
        if (nodeFindNestedYield != null) {
            generateYieldPoint(nodeFindNestedYield, true);
            String str2 = "__nested__yield__" + this.unnestedYieldCount;
            this.unnestedYieldCount++;
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.add(95);
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            addDynamicInvoke("PROP:SET:" + str2, "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
            this.cfw.add(87);
            this.unnestedYields.put(nodeFindNestedYield, str2);
        }
        generateLocalYieldPoint(node, z);
    }

    private void generateLocalYieldPoint(Node node, boolean z) {
        ClassFileWriter classFileWriter;
        ClassFileWriter classFileWriter2;
        int stackTop = this.cfw.getStackTop();
        int i = this.maxStack;
        if (i <= stackTop) {
            i = stackTop;
        }
        this.maxStack = i;
        if (stackTop != 0) {
            generateGetGeneratorStackState();
            int i2 = 0;
            while (true) {
                classFileWriter2 = this.cfw;
                if (i2 >= stackTop) {
                    break;
                }
                classFileWriter2.add(90);
                this.cfw.add(95);
                this.cfw.addLoadConstant(i2);
                this.cfw.add(95);
                this.cfw.add(83);
                i2++;
            }
            classFileWriter2.add(87);
        }
        Node firstChild = node.getFirstChild();
        if (firstChild != null) {
            generateExpression(firstChild, node);
        } else {
            Codegen.pushUndefined(this.cfw);
        }
        if (node.getType() == 179) {
            this.cfw.add(187, "org/mozilla/javascript/ES6Generator$YieldStarResult");
            this.cfw.add(90);
            this.cfw.add(95);
            this.cfw.addInvoke(183, "org/mozilla/javascript/ES6Generator$YieldStarResult", "<init>", "(Ljava/lang/Object;)V");
        }
        int nextGeneratorState = getNextGeneratorState(node);
        generateSetGeneratorResumptionPoint(nextGeneratorState);
        boolean zGenerateSaveLocals = generateSaveLocals(node);
        this.cfw.add(176);
        generateCheckForThrowOrClose(getTargetLabel(node), zGenerateSaveLocals, nextGeneratorState);
        if (stackTop != 0) {
            generateGetGeneratorStackState();
            while (true) {
                stackTop--;
                classFileWriter = this.cfw;
                if (stackTop < 0) {
                    break;
                }
                classFileWriter.add(89);
                this.cfw.addLoadConstant(stackTop);
                this.cfw.add(50);
                this.cfw.add(95);
            }
            classFileWriter.add(87);
        }
        if (z) {
            this.cfw.addALoad(this.argsLocal);
        }
    }

    private void generateCheckForThrowOrClose(int i, boolean z, int i2) {
        int iAcquireLabel = this.cfw.acquireLabel();
        int iAcquireLabel2 = this.cfw.acquireLabel();
        this.cfw.markLabel(iAcquireLabel);
        this.cfw.addALoad(this.argsLocal);
        generateThrowJavaScriptException();
        this.cfw.markLabel(iAcquireLabel2);
        this.cfw.addALoad(this.argsLocal);
        this.cfw.add(192, "java/lang/Throwable");
        this.cfw.add(ByteCode.ATHROW);
        if (i != -1) {
            this.cfw.markLabel(i);
        }
        if (!z) {
            this.cfw.markTableSwitchCase(this.generatorSwitch, i2);
        }
        this.cfw.addILoad(this.operationLocal);
        this.cfw.addLoadConstant(2);
        this.cfw.add(159, iAcquireLabel2);
        this.cfw.addILoad(this.operationLocal);
        this.cfw.addLoadConstant(1);
        this.cfw.add(159, iAcquireLabel);
    }

    private void visitTemplateLiteral(Node node) {
        int existingIntProp = node.getExistingIntProp(27);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.add(178, this.codegen.mainClassName, this.codegen.getTemplateLiteralName(this.scriptOrFn), "[Ljava/lang/Object;");
        this.cfw.addPush(existingIntProp);
        this.cfw.addInvoke(184, "org/mozilla/javascript/ScriptRuntime", "getTemplateLiteralCallSite", "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;I)Lorg/mozilla/javascript/Scriptable;");
    }

    private void generateIfJump(Node node, Node node2, int i, int i2) {
        int type = node.getType();
        Node firstChild = node.getFirstChild();
        if (type == 26) {
            generateIfJump(firstChild, node, i2, i);
            return;
        }
        if (type != 51 && type != 52) {
            if (type != 57 && type != 58) {
                if (type == 117 || type == 118) {
                    int iAcquireLabel = this.cfw.acquireLabel();
                    if (type == 118) {
                        generateIfJump(firstChild, node, iAcquireLabel, i2);
                    } else {
                        generateIfJump(firstChild, node, i, iAcquireLabel);
                    }
                    this.cfw.markLabel(iAcquireLabel);
                    generateIfJump(firstChild.getNext(), node, i, i2);
                    return;
                }
                switch (type) {
                    case 12:
                    case 13:
                        break;
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                        break;
                    default:
                        generateExpression(node, node2);
                        addDynamicInvoke("MATH:TOBOOLEAN", Signatures.MATH_TO_BOOLEAN);
                        this.cfw.add(154, i);
                        this.cfw.add(167, i2);
                        break;
                }
                return;
            }
            visitIfJumpRelOp(node, firstChild, i, i2);
            return;
        }
        visitIfJumpEqOp(node, firstChild, i, i2);
    }

    private void visitFunction(OptFunctionNode optFunctionNode, int i) {
        int index = this.codegen.getIndex(optFunctionNode.fnode);
        this.cfw.add(187, this.codegen.mainClassName);
        this.cfw.add(89);
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addPush(index);
        this.cfw.addInvoke(183, this.codegen.mainClassName, "<init>", "(Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Context;I)V");
        if (i == 4) {
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addALoad(this.thisObjLocal);
            addOptRuntimeInvoke("bindThis", "(Lorg/mozilla/javascript/NativeFunction;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Function;");
        }
        if (optFunctionNode.fnode.isMethodDefinition()) {
            this.cfw.add(89);
            this.cfw.addALoad(this.savedHomeObjectLocal);
            this.cfw.addInvoke(182, "org/mozilla/javascript/BaseFunction", "setHomeObject", "(Lorg/mozilla/javascript/Scriptable;)V");
        }
        if (i == 2 || i == 4) {
            return;
        }
        this.cfw.addPush(i);
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addALoad(this.contextLocal);
        addOptRuntimeInvoke("initFunction", "(Lorg/mozilla/javascript/NativeFunction;ILorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Context;)V");
    }

    private int getTargetLabel(Node node) {
        int iLabelId = node.labelId();
        if (iLabelId != -1) {
            return iLabelId;
        }
        int iAcquireLabel = this.cfw.acquireLabel();
        node.labelId(iAcquireLabel);
        return iAcquireLabel;
    }

    private void visitGoto(Jump jump, int i, Node node) {
        Node node2 = jump.target;
        if (i != 6 && i != 7) {
            if (i == 149) {
                if (this.isGenerator) {
                    addGotoWithReturn(node2);
                    return;
                } else {
                    inlineFinally(node2);
                    return;
                }
            }
            addGoto(node2, 167);
            return;
        }
        if (node == null) {
            throw Codegen.badTree();
        }
        int targetLabel = getTargetLabel(node2);
        int iAcquireLabel = this.cfw.acquireLabel();
        if (i == 6) {
            generateIfJump(node, jump, targetLabel, iAcquireLabel);
        } else {
            generateIfJump(node, jump, iAcquireLabel, targetLabel);
        }
        this.cfw.markLabel(iAcquireLabel);
    }

    private void addGotoWithReturn(Node node) {
        FinallyReturnPoint finallyReturnPoint = this.finallys.get(node);
        this.cfw.addLoadConstant(finallyReturnPoint.jsrPoints.size());
        addGoto(node, 167);
        this.cfw.add(87);
        int iAcquireLabel = this.cfw.acquireLabel();
        this.cfw.markLabel(iAcquireLabel);
        finallyReturnPoint.jsrPoints.add(Integer.valueOf(iAcquireLabel));
    }

    private void generateArrayLiteralFactory(Node node, int i) {
        String str = this.codegen.getBodyMethodName(this.scriptOrFn) + "_literal" + i;
        initBodyGeneration();
        int i2 = this.firstFreeLocal;
        int i3 = i2 + 1;
        this.firstFreeLocal = i3;
        this.argsLocal = i2;
        this.localsMax = i3;
        this.cfw.startMethod(str, "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;", (short) 2);
        visitArrayLiteral(node, node.getFirstChild(), true);
        this.cfw.add(176);
        this.cfw.stopMethod((short) (this.localsMax + 1));
    }

    private void generateObjectLiteralFactory(Node node, int i) {
        String str = this.codegen.getBodyMethodName(this.scriptOrFn) + "_literal" + i;
        initBodyGeneration();
        int i2 = this.firstFreeLocal;
        int i3 = i2 + 1;
        this.firstFreeLocal = i3;
        this.argsLocal = i2;
        this.localsMax = i3;
        this.cfw.startMethod(str, "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;", (short) 2);
        visitObjectLiteral(node, node.getFirstChild(), true);
        this.cfw.add(176);
        this.cfw.stopMethod((short) (this.localsMax + 1));
    }

    private void visitArrayLiteral(Node node, Node node2, boolean z) {
        int iCountArguments = countArguments(node2);
        if (!z && ((iCountArguments > 10 || this.cfw.getCurrentCodeOffset() > 30000) && !this.hasVarsInRegs && !this.isGenerator && !this.inLocalBlock)) {
            if (this.literals == null) {
                this.literals = new ArrayList<>();
            }
            this.literals.add(node);
            String str = this.codegen.getBodyMethodName(this.scriptOrFn) + "_literal" + this.literals.size();
            this.cfw.addALoad(this.funObjLocal);
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addALoad(this.thisObjLocal);
            this.cfw.addALoad(this.argsLocal);
            this.cfw.addInvoke(182, this.codegen.mainClassName, str, "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
            return;
        }
        int i = 0;
        if (this.isGenerator) {
            for (int i2 = 0; i2 != iCountArguments; i2++) {
                generateExpression(node2, node);
                node2 = node2.getNext();
            }
            addNewObjectArray(iCountArguments);
            while (i != iCountArguments) {
                this.cfw.add(90);
                this.cfw.add(95);
                this.cfw.addPush((iCountArguments - i) - 1);
                this.cfw.add(95);
                this.cfw.add(83);
                i++;
            }
        } else {
            addNewObjectArray(iCountArguments);
            while (i != iCountArguments) {
                this.cfw.add(89);
                this.cfw.addPush(i);
                generateExpression(node2, node);
                this.cfw.add(83);
                node2 = node2.getNext();
                i++;
            }
        }
        int[] iArr = (int[]) node.getProp(11);
        ClassFileWriter classFileWriter = this.cfw;
        if (iArr == null) {
            classFileWriter.add(1);
            this.cfw.add(3);
        } else {
            classFileWriter.addPush(OptRuntime.encodeIntArray(iArr));
            this.cfw.addPush(iArr.length);
        }
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        addOptRuntimeInvoke("newArrayLiteral", "([Ljava/lang/Object;Ljava/lang/String;ILorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
    }

    private void addLoadProperty(Node node, Node node2, Object[] objArr, int i) {
        int i2 = 0;
        if (i == 0) {
            addNewObjectArray(0);
            addNewObjectArray(0);
            return;
        }
        if (this.isGenerator) {
            while (i2 < i) {
                addLoadPropertyId(node, objArr, i2);
                addLoadPropertyValue(node, node2);
                node2 = node2.getNext();
                i2++;
            }
            int i3 = this.firstFreeLocal;
            int i4 = i3 + 1;
            int i5 = this.localsMax;
            this.firstFreeLocal = i3 + 2;
            this.localsMax = i5 + 2;
            addNewObjectArray(i);
            this.cfw.addAStore(i3);
            addNewObjectArray(i);
            this.cfw.addAStore(i4);
            while (true) {
                i--;
                ClassFileWriter classFileWriter = this.cfw;
                if (i >= 0) {
                    classFileWriter.addALoad(i4);
                    this.cfw.add(95);
                    this.cfw.addLoadConstant(i);
                    this.cfw.add(95);
                    this.cfw.add(83);
                    this.cfw.addALoad(i3);
                    this.cfw.add(95);
                    this.cfw.addLoadConstant(i);
                    this.cfw.add(95);
                    this.cfw.add(83);
                } else {
                    classFileWriter.addALoad(i3);
                    this.cfw.addALoad(i4);
                    return;
                }
            }
        } else {
            addNewObjectArray(i);
            addNewObjectArray(i);
            while (true) {
                ClassFileWriter classFileWriter2 = this.cfw;
                if (i2 < i) {
                    classFileWriter2.add(92);
                    this.cfw.addLoadConstant(i2);
                    addLoadPropertyId(node, objArr, i2);
                    this.cfw.add(83);
                    this.cfw.addLoadConstant(i2);
                    addLoadPropertyValue(node, node2);
                    this.cfw.add(83);
                    node2 = node2.getNext();
                    i2++;
                } else {
                    classFileWriter2.add(95);
                    return;
                }
            }
        }
    }

    private void addLoadPropertyValue(Node node, Node node2) {
        int type = node2.getType();
        if (type == 165 || type == 166 || type == 177) {
            generateExpression(node2.getFirstChild(), node);
        } else {
            generateExpression(node2, node);
        }
    }

    private void addLoadPropertyId(Node node, Object[] objArr, int i) {
        Object obj = objArr[i];
        if (obj instanceof Node) {
            generateExpression(((Node) obj).getFirstChild(), node);
            return;
        }
        boolean z = obj instanceof String;
        ClassFileWriter classFileWriter = this.cfw;
        if (z) {
            classFileWriter.addPush((String) obj);
        } else {
            classFileWriter.addPush(((Integer) obj).intValue());
            addScriptRuntimeInvoke("wrapInt", "(I)Ljava/lang/Integer;");
        }
    }

    private void visitObjectLiteral(Node node, Node node2, boolean z) {
        Object[] objArr = (Object[]) node.getProp(12);
        int length = objArr == null ? 0 : objArr.length;
        if (!z && ((length > 10 || this.cfw.getCurrentCodeOffset() > 30000) && !this.hasVarsInRegs && !this.isGenerator && !this.inLocalBlock)) {
            if (this.literals == null) {
                this.literals = new ArrayList<>();
            }
            this.literals.add(node);
            String str = this.codegen.getBodyMethodName(this.scriptOrFn) + "_literal" + this.literals.size();
            this.cfw.addALoad(this.funObjLocal);
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addALoad(this.thisObjLocal);
            this.cfw.addALoad(this.argsLocal);
            this.cfw.addInvoke(182, this.codegen.mainClassName, str, "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
            return;
        }
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addInvoke(182, "org/mozilla/javascript/Context", "newObject", "(Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
        this.cfw.add(89);
        short newWordLocal = getNewWordLocal();
        this.savedHomeObjectLocal = newWordLocal;
        this.cfw.addAStore(newWordLocal);
        this.cfw.add(89);
        addLoadProperty(node, node2, objArr, length);
        Node next = node2;
        for (int i = 0; i != length; i++) {
            int type = next.getType();
            if (type != 165 && type != 166) {
                next = next.getNext();
            } else {
                this.cfw.addPush(length);
                this.cfw.add(188, 10);
                for (int i2 = 0; i2 != length; i2++) {
                    this.cfw.add(89);
                    this.cfw.addPush(i2);
                    int type2 = node2.getType();
                    if (type2 == 165) {
                        this.cfw.add(2);
                    } else {
                        ClassFileWriter classFileWriter = this.cfw;
                        if (type2 == 166) {
                            classFileWriter.add(4);
                        } else {
                            classFileWriter.add(3);
                        }
                    }
                    this.cfw.add(79);
                    node2 = node2.getNext();
                }
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                addScriptRuntimeInvoke("fillObjectLiteral", "(Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;[Ljava/lang/Object;[ILorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)V");
            }
        }
        this.cfw.add(1);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        addScriptRuntimeInvoke("fillObjectLiteral", "(Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;[Ljava/lang/Object;[ILorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)V");
    }

    private void visitSpecialCall(Node node, int i, int i2, Node node2) {
        String str;
        String str2;
        this.cfw.addALoad(this.contextLocal);
        if (i == 30) {
            generateExpression(node2, node);
        } else {
            generateFunctionAndThisObj(node2, node);
            pushThisFromLastScriptable();
        }
        generateCallArgArray(node, node2.getNext(), false);
        if (i == 30) {
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addALoad(this.thisObjLocal);
            this.cfw.addPush(i2);
            str = "newObjectSpecial";
            str2 = "(Lorg/mozilla/javascript/Context;Ljava/lang/Object;[Ljava/lang/Object;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;I)Ljava/lang/Object;";
        } else {
            boolean z = node.getIntProp(30, 0) == 1;
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addALoad(this.thisObjLocal);
            this.cfw.addPush(i2);
            String sourceName = this.scriptOrFn.getSourceName();
            ClassFileWriter classFileWriter = this.cfw;
            if (sourceName == null) {
                sourceName = "";
            }
            classFileWriter.addPush(sourceName);
            this.cfw.addPush(this.itsLineNumber);
            this.cfw.addPush(z);
            str = "callSpecial";
            str2 = "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;ILjava/lang/String;IZ)Ljava/lang/Object;";
        }
        addOptRuntimeInvoke(str, str2);
    }

    private void visitStandardCall(Node node, Node node2) {
        if (node.getType() != 43) {
            throw Codegen.badTree();
        }
        Node next = node2.getNext();
        int type = node2.getType();
        boolean z = node.getIntProp(30, 0) == 1;
        int intProp = node.getIntProp(31, 0);
        String str = SIGNATURE_CALL2;
        String str2 = "call2";
        Integer numValueOf = null;
        if (intProp == 1) {
            int iCountArguments = countArguments(next);
            generateFunctionAndThisObj(node2, node);
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addInvoke(184, "org/mozilla/javascript/ScriptRuntime", "discardLastStoredScriptable", "(Lorg/mozilla/javascript/Context;)V");
            this.cfw.addALoad(this.thisObjLocal);
            if (iCountArguments == 0) {
                str = SIGNATURE_CALL0;
                str2 = "call0";
            } else if (iCountArguments == 1) {
                generateExpression(next, node);
                str = SIGNATURE_CALL1;
                str2 = "call1";
            } else if (iCountArguments == 2) {
                generateExpression(next, node);
                generateExpression(next.getNext(), node);
            } else {
                generateCallArgArray(node, next, false);
                str = SIGNATURE_CALLN;
                str2 = "callN";
            }
        } else if (next != null) {
            boolean z2 = z;
            if (type == 44) {
                String string = node2.getString();
                if (z2) {
                    this.cfw.addALoad(this.variableObjectLocal);
                    this.cfw.addALoad(this.contextLocal);
                    addDynamicInvoke("NAME:GETWITHTHISOPTIONAL:" + string, Signatures.NAME_GET_THIS);
                    int iAcquireLabel = this.cfw.acquireLabel();
                    numValueOf = Integer.valueOf(iAcquireLabel);
                    int iAcquireLabel2 = this.cfw.acquireLabel();
                    this.cfw.add(89);
                    addOptRuntimeInvoke("isNullOrUndefined", Signatures.MATH_TO_BOOLEAN);
                    this.cfw.add(153, iAcquireLabel2);
                    this.cfw.add(87);
                    this.cfw.add(178, "org/mozilla/javascript/Undefined", "instance", "Ljava/lang/Object;");
                    ClassFileWriter classFileWriter = this.cfw;
                    numValueOf.getClass();
                    classFileWriter.add(167, iAcquireLabel);
                    this.cfw.markLabel(iAcquireLabel2);
                    pushThisFromLastScriptable();
                    generateCallArgArray(node, next, false);
                    str = SIGNATURE_CALLN;
                    str2 = "callN";
                } else {
                    generateCallArgArray(node, next, false);
                    this.cfw.addPush(string);
                    str2 = "callName";
                    str = "([Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
                }
            } else {
                int iCountArguments2 = countArguments(next);
                generateFunctionAndThisObj(node2, node);
                if (z2) {
                    int iAcquireLabel3 = this.cfw.acquireLabel();
                    numValueOf = Integer.valueOf(iAcquireLabel3);
                    int iAcquireLabel4 = this.cfw.acquireLabel();
                    this.cfw.add(89);
                    addOptRuntimeInvoke("isNullOrUndefined", Signatures.MATH_TO_BOOLEAN);
                    this.cfw.add(153, iAcquireLabel4);
                    this.cfw.add(87);
                    this.cfw.add(178, "org/mozilla/javascript/Undefined", "instance", "Ljava/lang/Object;");
                    ClassFileWriter classFileWriter2 = this.cfw;
                    numValueOf.getClass();
                    classFileWriter2.add(167, iAcquireLabel3);
                    this.cfw.markLabel(iAcquireLabel4);
                    this.cfw.add(192, "org/mozilla/javascript/Callable");
                }
                pushThisFromLastScriptable();
                if (iCountArguments2 == 1) {
                    generateExpression(next, node);
                    str = SIGNATURE_CALL1;
                    str2 = "call1";
                } else if (iCountArguments2 == 2) {
                    generateExpression(next, node);
                    generateExpression(next.getNext(), node);
                    str = SIGNATURE_CALL2;
                } else {
                    generateCallArgArray(node, next, false);
                    str = SIGNATURE_CALLN;
                    str2 = "callN";
                }
            }
        } else if (type == 44) {
            this.cfw.addPush(node2.getString());
            str2 = z ? "callName0Optional" : "callName0";
            str = "(Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
        } else if (type == 33) {
            Node firstChild = node2.getFirstChild();
            generateExpression(firstChild, node);
            this.cfw.addPush(firstChild.getNext().getString());
            str2 = z ? "callProp0Optional" : "callProp0";
            str = "(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
        } else {
            if (type == 34) {
                throw Kit.codeBug();
            }
            generateFunctionAndThisObj(node2, node);
            pushThisFromLastScriptable();
            str2 = z ? "call0Optional" : "call0";
            str = SIGNATURE_CALL0;
        }
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        addOptRuntimeInvoke(str2, str);
        if (numValueOf != null) {
            this.cfw.markLabel(numValueOf.intValue());
        }
    }

    private static int countArguments(Node node) {
        int i = 0;
        while (node != null) {
            i++;
            node = node.getNext();
        }
        return i;
    }

    private void pushThisFromLastScriptable() {
        this.cfw.addALoad(this.contextLocal);
        addScriptRuntimeInvoke("lastStoredScriptable", "(Lorg/mozilla/javascript/Context;)Lorg/mozilla/javascript/Scriptable;");
    }

    private void visitStandardNew(Node node, Node node2) {
        if (node.getType() != 30) {
            throw Codegen.badTree();
        }
        Node next = node2.getNext();
        generateExpression(node2, node);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        generateCallArgArray(node, next, false);
        addScriptRuntimeInvoke("newObject", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
    }

    private void visitOptimizedCall(Node node, OptFunctionNode optFunctionNode, int i, Node node2) {
        String bodyMethodName;
        short newWordLocal = 0;
        if (node.getIntProp(31, 0) == 1) {
            Kit.codeBug();
        }
        Node next = node2.getNext();
        String str = this.codegen.mainClassName;
        if (i == 30) {
            generateExpression(node2, node);
        } else {
            generateFunctionAndThisObj(node2, node);
            pushThisFromLastScriptable();
            newWordLocal = getNewWordLocal();
            this.cfw.addAStore(newWordLocal);
        }
        int iAcquireLabel = this.cfw.acquireLabel();
        int iAcquireLabel2 = this.cfw.acquireLabel();
        this.cfw.add(89);
        this.cfw.add(ByteCode.INSTANCEOF, str);
        this.cfw.add(153, iAcquireLabel2);
        this.cfw.add(192, str);
        this.cfw.add(89);
        this.cfw.add(180, str, "_id", "I");
        this.cfw.addPush(this.codegen.getIndex(optFunctionNode.fnode));
        this.cfw.add(160, iAcquireLabel2);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        ClassFileWriter classFileWriter = this.cfw;
        if (i == 30) {
            classFileWriter.add(1);
        } else {
            classFileWriter.addALoad(newWordLocal);
        }
        for (Node next2 = next; next2 != null; next2 = next2.getNext()) {
            int iNodeIsDirectCallParameter = nodeIsDirectCallParameter(next2);
            if (iNodeIsDirectCallParameter >= 0) {
                this.cfw.addALoad(iNodeIsDirectCallParameter);
                this.cfw.addDLoad(iNodeIsDirectCallParameter + 1);
            } else if (next2.getIntProp(8, -1) == 0) {
                this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
                generateExpression(next2, node);
            } else {
                generateExpression(next2, node);
                this.cfw.addPush(0.0d);
            }
        }
        this.cfw.add(178, "org/mozilla/javascript/ScriptRuntime", "emptyArgs", "[Ljava/lang/Object;");
        ClassFileWriter classFileWriter2 = this.cfw;
        String str2 = this.codegen.mainClassName;
        Codegen codegen = this.codegen;
        if (i == 30) {
            bodyMethodName = codegen.getDirectCtorName(optFunctionNode.fnode);
        } else {
            bodyMethodName = codegen.getBodyMethodName(optFunctionNode.fnode);
        }
        classFileWriter2.addInvoke(184, str2, bodyMethodName, this.codegen.getBodyMethodSignature(optFunctionNode.fnode));
        this.cfw.add(167, iAcquireLabel);
        this.cfw.markLabel(iAcquireLabel2);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        if (i != 30) {
            this.cfw.addALoad(newWordLocal);
            releaseWordLocal(newWordLocal);
        }
        generateCallArgArray(node, next, true);
        if (i == 30) {
            addScriptRuntimeInvoke("newObject", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
        } else {
            this.cfw.addInvoke(185, "org/mozilla/javascript/Callable", NotificationCompat.CATEGORY_CALL, "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Ljava/lang/Object;");
        }
        this.cfw.markLabel(iAcquireLabel);
    }

    private void generateCallArgArray(Node node, Node node2, boolean z) {
        int i;
        int iCountArguments = countArguments(node2);
        if (iCountArguments == 1 && (i = this.itsOneArgArray) >= 0) {
            this.cfw.addALoad(i);
        } else {
            addNewObjectArray(iCountArguments);
        }
        for (int i2 = 0; i2 != iCountArguments; i2++) {
            if (!this.isGenerator) {
                this.cfw.add(89);
                this.cfw.addPush(i2);
            }
            if (!z) {
                generateExpression(node2, node);
            } else {
                int iNodeIsDirectCallParameter = nodeIsDirectCallParameter(node2);
                if (iNodeIsDirectCallParameter >= 0) {
                    dcpLoadAsObject(iNodeIsDirectCallParameter);
                } else {
                    generateExpression(node2, node);
                    if (node2.getIntProp(8, -1) == 0) {
                        addDoubleWrap();
                    }
                }
            }
            if (this.isGenerator) {
                short newWordLocal = getNewWordLocal();
                this.cfw.addAStore(newWordLocal);
                this.cfw.add(192, "[Ljava/lang/Object;");
                this.cfw.add(89);
                this.cfw.addPush(i2);
                this.cfw.addALoad(newWordLocal);
                releaseWordLocal(newWordLocal);
            }
            this.cfw.add(83);
            node2 = node2.getNext();
        }
    }

    private void generateFunctionAndThisObj(Node node, Node node2) {
        String str;
        String str2;
        int type = node.getType();
        boolean z = node2.getIntProp(30, 0) == 1;
        int type2 = node.getType();
        if (type2 != 33) {
            if (type2 == 34) {
                throw Kit.codeBug();
            }
            if (type2 != 39) {
                if (type2 == 44) {
                    String string = node.getString();
                    this.cfw.addALoad(this.variableObjectLocal);
                    this.cfw.addALoad(this.contextLocal);
                    addDynamicInvoke("NAME:" + (z ? "GETWITHTHISOPTIONAL" : "GETWITHTHIS") + ":" + string, Signatures.NAME_GET_THIS);
                    return;
                }
                generateExpression(node, node2);
                this.cfw.addALoad(this.contextLocal);
                if (z) {
                    str2 = "getValueFunctionAndThisOptional";
                } else {
                    str2 = "getValueFunctionAndThis";
                }
                addScriptRuntimeInvoke(str2, "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Lorg/mozilla/javascript/Callable;");
                return;
            }
        }
        Node firstChild = node.getFirstChild();
        generateExpression(firstChild, node);
        Node next = firstChild.getNext();
        if (type == 33) {
            String string2 = next.getString();
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            addDynamicInvoke("PROP:" + (z ? "GETWITHTHISOPTIONAL" : "GETWITHTHIS") + ":" + string2, Signatures.PROP_GET_THIS);
            return;
        }
        generateExpression(next, node);
        if (node.getIntProp(8, -1) != -1) {
            addDoubleWrap();
        }
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        if (z) {
            str = "getElemFunctionAndThisOptional";
        } else {
            str = "getElemFunctionAndThis";
        }
        addScriptRuntimeInvoke(str, "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Callable;");
    }

    private void updateLineNumber(Node node) {
        int lineno = node.getLineno();
        this.itsLineNumber = lineno;
        if (lineno == -1) {
            return;
        }
        this.cfw.addLineNumberEntry((short) lineno);
    }

    private void visitTryCatchFinally(Jump jump, Node node) {
        int i;
        BodyCodegen bodyCodegen = this;
        short newWordLocal = bodyCodegen.getNewWordLocal();
        bodyCodegen.cfw.addALoad(bodyCodegen.variableObjectLocal);
        bodyCodegen.cfw.addAStore(newWordLocal);
        int iAcquireLabel = bodyCodegen.cfw.acquireLabel();
        bodyCodegen.cfw.markLabel(iAcquireLabel, 0);
        Node node2 = jump.target;
        Node node3 = jump.getFinally();
        int[] iArr = new int[5];
        bodyCodegen.exceptionManager.pushExceptionInfo(jump);
        if (node2 != null) {
            iArr[0] = bodyCodegen.cfw.acquireLabel();
            iArr[1] = bodyCodegen.cfw.acquireLabel();
            iArr[2] = bodyCodegen.cfw.acquireLabel();
            Context currentContext = Context.getCurrentContext();
            if (currentContext != null && currentContext.hasFeature(13)) {
                iArr[3] = bodyCodegen.cfw.acquireLabel();
            }
        }
        if (node3 != null) {
            iArr[4] = bodyCodegen.cfw.acquireLabel();
        }
        bodyCodegen.exceptionManager.setHandlers(iArr, iAcquireLabel);
        if (bodyCodegen.isGenerator && node3 != null) {
            FinallyReturnPoint finallyReturnPoint = new FinallyReturnPoint();
            if (bodyCodegen.finallys == null) {
                bodyCodegen.finallys = new HashMap();
            }
            bodyCodegen.finallys.put(node3, finallyReturnPoint);
            bodyCodegen.finallys.put(node3.getNext(), finallyReturnPoint);
        }
        for (Node next = node; next != null; next = next.getNext()) {
            if (next == node2) {
                int targetLabel = bodyCodegen.getTargetLabel(node2);
                bodyCodegen.exceptionManager.removeHandler(0, targetLabel);
                bodyCodegen.exceptionManager.removeHandler(1, targetLabel);
                bodyCodegen.exceptionManager.removeHandler(2, targetLabel);
                bodyCodegen.exceptionManager.removeHandler(3, targetLabel);
            }
            bodyCodegen.generateStatement(next);
        }
        int iAcquireLabel2 = bodyCodegen.cfw.acquireLabel();
        bodyCodegen.cfw.add(167, iAcquireLabel2);
        int localBlockRegister = getLocalBlockRegister(jump);
        if (node2 != null) {
            int iLabelId = node2.labelId();
            i = localBlockRegister;
            bodyCodegen.generateCatchBlock(0, newWordLocal, iLabelId, i, iArr[0]);
            generateCatchBlock(1, newWordLocal, iLabelId, i, iArr[1]);
            generateCatchBlock(2, newWordLocal, iLabelId, i, iArr[2]);
            Context currentContext2 = Context.getCurrentContext();
            if (currentContext2 == null || !currentContext2.hasFeature(13)) {
                bodyCodegen = this;
            } else {
                bodyCodegen = this;
                bodyCodegen.generateCatchBlock(3, newWordLocal, iLabelId, i, iArr[3]);
            }
        } else {
            i = localBlockRegister;
        }
        if (node3 != null) {
            int iAcquireLabel3 = bodyCodegen.cfw.acquireLabel();
            int iAcquireLabel4 = bodyCodegen.cfw.acquireLabel();
            bodyCodegen.cfw.markHandler(iAcquireLabel3);
            if (!bodyCodegen.isGenerator) {
                bodyCodegen.cfw.markLabel(iArr[4]);
            }
            bodyCodegen.cfw.addAStore(i);
            bodyCodegen.cfw.addALoad(newWordLocal);
            bodyCodegen.cfw.addAStore(bodyCodegen.variableObjectLocal);
            int iLabelId2 = node3.labelId();
            if (bodyCodegen.isGenerator) {
                bodyCodegen.addGotoWithReturn(node3);
            } else {
                bodyCodegen.inlineFinally(node3, iArr[4], iAcquireLabel4);
            }
            bodyCodegen.cfw.addALoad(i);
            if (bodyCodegen.isGenerator) {
                bodyCodegen.cfw.add(192, "java/lang/Throwable");
            }
            bodyCodegen.cfw.add(ByteCode.ATHROW);
            bodyCodegen.cfw.markLabel(iAcquireLabel4);
            if (bodyCodegen.isGenerator) {
                bodyCodegen.cfw.addExceptionHandler(iAcquireLabel, iLabelId2, iAcquireLabel3, null);
            }
        }
        bodyCodegen.releaseWordLocal(newWordLocal);
        bodyCodegen.cfw.markLabel(iAcquireLabel2);
        if (bodyCodegen.isGenerator) {
            return;
        }
        bodyCodegen.exceptionManager.popExceptionInfo();
    }

    private void generateCatchBlock(int i, short s, int i2, int i3, int i4) {
        if (i4 == 0) {
            i4 = this.cfw.acquireLabel();
        }
        this.cfw.markHandler(i4);
        this.cfw.addAStore(i3);
        this.cfw.addALoad(s);
        this.cfw.addAStore(this.variableObjectLocal);
        this.cfw.add(167, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String exceptionTypeToName(int i) {
        if (i == 0) {
            return "org/mozilla/javascript/JavaScriptException";
        }
        if (i == 1) {
            return "org/mozilla/javascript/EvaluatorException";
        }
        if (i == 2) {
            return "org/mozilla/javascript/EcmaError";
        }
        if (i == 3) {
            return "java/lang/Throwable";
        }
        if (i == 4) {
            return null;
        }
        throw Kit.codeBug();
    }

    private class ExceptionManager {
        private ArrayDeque<ExceptionInfo> exceptionInfo = new ArrayDeque<>();

        ExceptionManager() {
        }

        void pushExceptionInfo(Jump jump) {
            this.exceptionInfo.add(new ExceptionInfo(jump, BodyCodegen.getFinallyAtTarget(jump.getFinally())));
        }

        void addHandler(int i, int i2, int i3) {
            ExceptionInfo top = getTop();
            top.handlerLabels[i] = i2;
            top.exceptionStarts[i] = i3;
        }

        void setHandlers(int[] iArr, int i) {
            for (int i2 = 0; i2 < iArr.length; i2++) {
                int i3 = iArr[i2];
                if (i3 != 0) {
                    addHandler(i2, i3, i);
                }
            }
        }

        int removeHandler(int i, int i2) {
            ExceptionInfo top = getTop();
            if (top.handlerLabels[i] == 0) {
                return 0;
            }
            int i3 = top.handlerLabels[i];
            endCatch(top, i, i2);
            top.handlerLabels[i] = 0;
            return i3;
        }

        void popExceptionInfo() {
            this.exceptionInfo.removeLast();
        }

        void markInlineFinallyStart(Node node, int i) {
            Iterator<ExceptionInfo> itDescendingIterator = this.exceptionInfo.descendingIterator();
            while (itDescendingIterator.hasNext()) {
                ExceptionInfo next = itDescendingIterator.next();
                for (int i2 = 0; i2 < 5; i2++) {
                    if (next.handlerLabels[i2] != 0 && next.currentFinally == null) {
                        endCatch(next, i2, i);
                        next.exceptionStarts[i2] = 0;
                        next.currentFinally = node;
                    }
                }
                if (next.finallyBlock == node) {
                    return;
                }
            }
        }

        void markInlineFinallyEnd(Node node, int i) {
            Iterator<ExceptionInfo> itDescendingIterator = this.exceptionInfo.descendingIterator();
            while (itDescendingIterator.hasNext()) {
                ExceptionInfo next = itDescendingIterator.next();
                for (int i2 = 0; i2 < 5; i2++) {
                    if (next.handlerLabels[i2] != 0 && next.currentFinally == node) {
                        next.exceptionStarts[i2] = i;
                        next.currentFinally = null;
                    }
                }
                if (next.finallyBlock == node) {
                    return;
                }
            }
        }

        private void endCatch(ExceptionInfo exceptionInfo, int i, int i2) {
            if (exceptionInfo.exceptionStarts[i] == 0) {
                throw new IllegalStateException("bad exception start");
            }
            if (BodyCodegen.this.cfw.getLabelPC(exceptionInfo.exceptionStarts[i]) != BodyCodegen.this.cfw.getLabelPC(i2)) {
                BodyCodegen.this.cfw.addExceptionHandler(exceptionInfo.exceptionStarts[i], i2, exceptionInfo.handlerLabels[i], BodyCodegen.exceptionTypeToName(i));
            }
        }

        private ExceptionInfo getTop() {
            return this.exceptionInfo.getLast();
        }

        private class ExceptionInfo {
            Node finallyBlock;
            int[] handlerLabels = new int[5];
            int[] exceptionStarts = new int[5];
            Node currentFinally = null;

            ExceptionInfo(Jump jump, Node node) {
                this.finallyBlock = node;
            }
        }
    }

    private void inlineFinally(Node node, int i, int i2) {
        Node finallyAtTarget = getFinallyAtTarget(node);
        finallyAtTarget.resetTargets();
        this.exceptionManager.markInlineFinallyStart(finallyAtTarget, i);
        for (Node firstChild = finallyAtTarget.getFirstChild(); firstChild != null; firstChild = firstChild.getNext()) {
            generateStatement(firstChild);
        }
        this.exceptionManager.markInlineFinallyEnd(finallyAtTarget, i2);
    }

    private void inlineFinally(Node node) {
        int iAcquireLabel = this.cfw.acquireLabel();
        int iAcquireLabel2 = this.cfw.acquireLabel();
        this.cfw.markLabel(iAcquireLabel);
        inlineFinally(node, iAcquireLabel, iAcquireLabel2);
        this.cfw.markLabel(iAcquireLabel2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Node getFinallyAtTarget(Node node) {
        Node next;
        if (node == null) {
            return null;
        }
        if (node.getType() == 138) {
            return node;
        }
        if (node.getType() == 145 && (next = node.getNext()) != null && next.getType() == 138) {
            return next;
        }
        throw Kit.codeBug("bad finally target");
    }

    private boolean generateSaveLocals(Node node) {
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < this.firstFreeLocal; i3++) {
            if (this.locals[i3] != 0) {
                i2++;
            }
        }
        if (i2 == 0) {
            ((FunctionNode) this.scriptOrFn).addLiveLocals(node, null);
            return false;
        }
        int i4 = this.maxLocals;
        if (i4 <= i2) {
            i4 = i2;
        }
        this.maxLocals = i4;
        int[] iArr = new int[i2];
        int i5 = 0;
        for (int i6 = 0; i6 < this.firstFreeLocal; i6++) {
            if (this.locals[i6] != 0) {
                iArr[i5] = i6;
                i5++;
            }
        }
        ((FunctionNode) this.scriptOrFn).addLiveLocals(node, iArr);
        generateGetGeneratorLocalsState();
        while (true) {
            ClassFileWriter classFileWriter = this.cfw;
            if (i < i2) {
                classFileWriter.add(89);
                this.cfw.addLoadConstant(i);
                this.cfw.addALoad(iArr[i]);
                this.cfw.add(83);
                i++;
            } else {
                classFileWriter.add(87);
                return true;
            }
        }
    }

    private void visitSwitch(Jump jump, Node node) {
        generateExpression(node, jump);
        short newWordLocal = getNewWordLocal();
        this.cfw.addAStore(newWordLocal);
        for (Jump jump2 = (Jump) node.getNext(); jump2 != null; jump2 = (Jump) jump2.getNext()) {
            if (jump2.getType() != 128) {
                throw Codegen.badTree();
            }
            generateExpression(jump2.getFirstChild(), jump2);
            this.cfw.addALoad(newWordLocal);
            addDynamicInvoke("MATH:SHALLOWEQ", "(Ljava/lang/Object;Ljava/lang/Object;)Z");
            addGoto(jump2.target, 154);
        }
        releaseWordLocal(newWordLocal);
    }

    private void visitTypeofname(Node node) {
        int indexForNameNode;
        if (this.hasVarsInRegs && (indexForNameNode = this.fnCurrent.fnode.getIndexForNameNode(node)) >= 0) {
            if (this.fnCurrent.isNumberVar(indexForNameNode)) {
                this.cfw.addPush("number");
                return;
            }
            if (varIsDirectCallParameter(indexForNameNode)) {
                int i = this.varRegisters[indexForNameNode];
                this.cfw.addALoad(i);
                this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
                int iAcquireLabel = this.cfw.acquireLabel();
                this.cfw.add(165, iAcquireLabel);
                int stackTop = this.cfw.getStackTop();
                this.cfw.addALoad(i);
                addScriptRuntimeInvoke("typeof", "(Ljava/lang/Object;)Ljava/lang/String;");
                int iAcquireLabel2 = this.cfw.acquireLabel();
                this.cfw.add(167, iAcquireLabel2);
                this.cfw.markLabel(iAcquireLabel, stackTop);
                this.cfw.addPush("number");
                this.cfw.markLabel(iAcquireLabel2);
                return;
            }
            this.cfw.addALoad(this.varRegisters[indexForNameNode]);
            addScriptRuntimeInvoke("typeof", "(Ljava/lang/Object;)Ljava/lang/String;");
            return;
        }
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addPush(node.getString());
        addScriptRuntimeInvoke("typeofName", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;)Ljava/lang/String;");
    }

    private void saveCurrentCodeOffset() {
        this.savedCodeOffset = this.cfw.getCurrentCodeOffset();
    }

    private void addInstructionCount() {
        addInstructionCount(Math.max(this.cfw.getCurrentCodeOffset() - this.savedCodeOffset, 1));
    }

    private void addInstructionCount(int i) {
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addPush(i);
        addScriptRuntimeInvoke("addInstructionCount", "(Lorg/mozilla/javascript/Context;I)V");
    }

    private void visitIncDec(Node node) {
        int existingIntProp = node.getExistingIntProp(13);
        Node firstChild = node.getFirstChild();
        if (firstChild.getIntProp(31, 0) == 1) {
            visitSuperIncDec(node, firstChild, existingIntProp);
            return;
        }
        int type = firstChild.getType();
        if (type == 33) {
            Node firstChild2 = firstChild.getFirstChild();
            generateExpression(firstChild2, node);
            generateExpression(firstChild2.getNext(), node);
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addPush(existingIntProp);
            addScriptRuntimeInvoke("propIncrDecr", "(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Ljava/lang/Object;");
            return;
        }
        if (type == 34) {
            throw Kit.codeBug();
        }
        if (type == 39) {
            Node firstChild3 = firstChild.getFirstChild();
            generateExpression(firstChild3, node);
            generateExpression(firstChild3.getNext(), node);
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addPush(existingIntProp);
            if (firstChild3.getNext().getIntProp(8, -1) != -1) {
                addOptRuntimeInvoke("elemIncrDecr", "(Ljava/lang/Object;DLorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Ljava/lang/Object;");
                return;
            } else {
                addScriptRuntimeInvoke("elemIncrDecr", "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Ljava/lang/Object;");
                return;
            }
        }
        if (type == 44) {
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addPush(firstChild.getString());
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addPush(existingIntProp);
            addScriptRuntimeInvoke("nameIncrDecr", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;Lorg/mozilla/javascript/Context;I)Ljava/lang/Object;");
            return;
        }
        if (type != 60) {
            if (type == 73) {
                generateExpression(firstChild.getFirstChild(), node);
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                this.cfw.addPush(existingIntProp);
                addScriptRuntimeInvoke("refIncrDecr", "(Lorg/mozilla/javascript/Ref;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Ljava/lang/Object;");
                return;
            }
            Codegen.badTree();
            return;
        }
        if (!this.hasVarsInRegs) {
            Kit.codeBug();
        }
        boolean z = (existingIntProp & 2) != 0;
        int varIndex = this.fnCurrent.getVarIndex(firstChild);
        int i = this.varRegisters[varIndex];
        if (this.fnCurrent.fnode.getParamAndVarConst()[varIndex]) {
            if (node.getIntProp(8, -1) != -1) {
                this.cfw.addDLoad(i + (varIsDirectCallParameter(varIndex) ? 1 : 0));
                if (z) {
                    return;
                }
                this.cfw.addPush(1.0d);
                int i2 = existingIntProp & 1;
                ClassFileWriter classFileWriter = this.cfw;
                if (i2 == 0) {
                    classFileWriter.add(99);
                    return;
                } else {
                    classFileWriter.add(103);
                    return;
                }
            }
            if (varIsDirectCallParameter(varIndex)) {
                dcpLoadAsObject(i);
            } else {
                this.cfw.addALoad(i);
            }
            if (z) {
                this.cfw.add(89);
                addObjectToDouble();
                this.cfw.add(88);
                return;
            }
            addObjectToDouble();
            this.cfw.addPush(1.0d);
            int i3 = existingIntProp & 1;
            ClassFileWriter classFileWriter2 = this.cfw;
            if (i3 == 0) {
                classFileWriter2.add(99);
            } else {
                classFileWriter2.add(103);
            }
            addDoubleWrap();
            return;
        }
        if (node.getIntProp(8, -1) != -1) {
            boolean zVarIsDirectCallParameter = varIsDirectCallParameter(varIndex);
            ClassFileWriter classFileWriter3 = this.cfw;
            int i4 = i + (zVarIsDirectCallParameter ? 1 : 0);
            classFileWriter3.addDLoad(i4);
            if (z) {
                this.cfw.add(92);
            }
            this.cfw.addPush(1.0d);
            int i5 = existingIntProp & 1;
            ClassFileWriter classFileWriter4 = this.cfw;
            if (i5 == 0) {
                classFileWriter4.add(99);
            } else {
                classFileWriter4.add(103);
            }
            if (!z) {
                this.cfw.add(92);
            }
            this.cfw.addDStore(i4);
            return;
        }
        if (varIsDirectCallParameter(varIndex)) {
            dcpLoadAsObject(i);
        } else {
            this.cfw.addALoad(i);
        }
        addObjectToDouble();
        if (z) {
            this.cfw.add(92);
        }
        this.cfw.addPush(1.0d);
        int i6 = existingIntProp & 1;
        ClassFileWriter classFileWriter5 = this.cfw;
        if (i6 == 0) {
            classFileWriter5.add(99);
        } else {
            classFileWriter5.add(103);
        }
        addDoubleWrap();
        if (!z) {
            this.cfw.add(89);
        }
        this.cfw.addAStore(i);
        if (z) {
            addDoubleWrap();
        }
    }

    private void visitSuperIncDec(Node node, Node node2, int i) {
        Node firstChild = node2.getFirstChild();
        generateExpression(firstChild, node);
        this.cfw.add(89);
        int type = node2.getType();
        if (type == 33) {
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addALoad(this.thisObjLocal);
            this.cfw.addLoadConstant(0);
            addDynamicInvoke("PROP:GETSUPER:" + node2.getFirstChild().getNext().getString(), Signatures.PROP_GET_SUPER);
        } else if (type == 39) {
            generateExpression(firstChild.getNext(), node);
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addALoad(this.thisObjLocal);
            addDynamicInvoke("PROP:GETELEMENTSUPER", "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;)Ljava/lang/Object;");
        } else {
            Codegen.badTree();
        }
        int i2 = i & 2;
        if (i2 != 0) {
            this.cfw.add(95);
            this.cfw.add(92);
            this.cfw.add(87);
        }
        addObjectToDouble();
        this.cfw.addPush(1.0d);
        int i3 = i & 1;
        ClassFileWriter classFileWriter = this.cfw;
        if (i3 == 0) {
            classFileWriter.add(99);
        } else {
            classFileWriter.add(103);
        }
        addDoubleWrap();
        int type2 = node2.getType();
        if (type2 == 33) {
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addALoad(this.thisObjLocal);
            addDynamicInvoke("PROP:SETSUPER:" + node2.getFirstChild().getNext().getString(), "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;)Ljava/lang/Object;");
        } else if (type2 == 39) {
            generateExpression(firstChild.getNext(), node);
            this.cfw.add(95);
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addALoad(this.thisObjLocal);
            addDynamicInvoke("PROP:SETELEMENTSUPER", Signatures.PROP_SET_ELEMENT_SUPER);
        }
        if (i2 != 0) {
            this.cfw.add(87);
        }
    }

    private static boolean isArithmeticNode(Node node) {
        int type = node.getType();
        return type == 22 || type == 25 || type == 24 || type == 23;
    }

    private void visitArithmetic(Node node, int i, Node node2, Node node3) {
        if (node.getIntProp(8, -1) != -1) {
            generateExpression(node2, node);
            generateExpression(node2.getNext(), node);
            switch (i) {
                case 22:
                    this.cfw.add(103);
                    return;
                case 23:
                    this.cfw.add(107);
                    return;
                case 24:
                    this.cfw.add(111);
                    return;
                case 25:
                    this.cfw.add(115);
                    return;
                default:
                    throw Kit.codeBug(Token.typeToName(i));
            }
        }
        generateExpression(node2, node);
        if (!isArithmeticNode(node2)) {
            addObjectToNumeric();
        }
        generateExpression(node2.getNext(), node);
        if (!isArithmeticNode(node2.getNext())) {
            addObjectToNumeric();
        }
        switch (i) {
            case 22:
                addScriptRuntimeInvoke("subtract", "(Ljava/lang/Number;Ljava/lang/Number;)Ljava/lang/Number;");
                return;
            case 23:
                addScriptRuntimeInvoke("multiply", "(Ljava/lang/Number;Ljava/lang/Number;)Ljava/lang/Number;");
                return;
            case 24:
                addScriptRuntimeInvoke("divide", "(Ljava/lang/Number;Ljava/lang/Number;)Ljava/lang/Number;");
                return;
            case 25:
                addScriptRuntimeInvoke("remainder", "(Ljava/lang/Number;Ljava/lang/Number;)Ljava/lang/Number;");
                return;
            default:
                throw Kit.codeBug(Token.typeToName(i));
        }
    }

    private void visitExponentiation(Node node, Node node2, Node node3) {
        if (node.getIntProp(8, -1) != -1) {
            generateExpression(node2, node);
            generateExpression(node2.getNext(), node);
            this.cfw.addInvoke(184, "java/lang/Math", "pow", "(DD)D");
            return;
        }
        generateExpression(node2, node);
        generateExpression(node2.getNext(), node);
        short newWordLocal = getNewWordLocal();
        this.cfw.addAStore(newWordLocal);
        addObjectToNumeric();
        this.cfw.addALoad(newWordLocal);
        addObjectToNumeric();
        addScriptRuntimeInvoke("exponentiate", "(Ljava/lang/Number;Ljava/lang/Number;)Ljava/lang/Number;");
    }

    private void visitBitNot(Node node, Node node2) {
        int intProp = node.getIntProp(8, -1);
        generateExpression(node2, node);
        if (intProp == -1) {
            addObjectToNumeric();
            addScriptRuntimeInvoke("bitwiseNOT", "(Ljava/lang/Number;)Ljava/lang/Number;");
        } else {
            addScriptRuntimeInvoke("toInt32", "(D)I");
            this.cfw.addPush(-1);
            this.cfw.add(130);
            this.cfw.add(135);
        }
    }

    private void visitBitOp(Node node, int i, Node node2) {
        int intProp = node.getIntProp(8, -1);
        generateExpression(node2, node);
        if (i == 20) {
            addDynamicInvoke("MATH:TOUINT32", Signatures.MATH_TO_UINT32);
            generateExpression(node2.getNext(), node);
            addDynamicInvoke("MATH:TOINT32", Signatures.MATH_TO_INT32);
            this.cfw.addPush(31);
            this.cfw.add(126);
            this.cfw.add(125);
            this.cfw.add(138);
            addDoubleWrap();
            return;
        }
        if (intProp == -1) {
            addObjectToNumeric();
            generateExpression(node2.getNext(), node);
            addObjectToNumeric();
            if (i == 18) {
                addScriptRuntimeInvoke("leftShift", "(Ljava/lang/Number;Ljava/lang/Number;)Ljava/lang/Number;");
                return;
            }
            if (i != 19) {
                switch (i) {
                    case 9:
                        addScriptRuntimeInvoke("bitwiseOR", "(Ljava/lang/Number;Ljava/lang/Number;)Ljava/lang/Number;");
                        return;
                    case 10:
                        addScriptRuntimeInvoke("bitwiseXOR", "(Ljava/lang/Number;Ljava/lang/Number;)Ljava/lang/Number;");
                        return;
                    case 11:
                        addScriptRuntimeInvoke("bitwiseAND", "(Ljava/lang/Number;Ljava/lang/Number;)Ljava/lang/Number;");
                        return;
                    default:
                        throw Kit.codeBug(Token.typeToName(i));
                }
            }
            addScriptRuntimeInvoke("signedRightShift", "(Ljava/lang/Number;Ljava/lang/Number;)Ljava/lang/Number;");
            return;
        }
        addScriptRuntimeInvoke("toInt32", "(D)I");
        generateExpression(node2.getNext(), node);
        addScriptRuntimeInvoke("toInt32", "(D)I");
        if (i == 18) {
            this.cfw.add(120);
        } else if (i != 19) {
            switch (i) {
                case 9:
                    this.cfw.add(128);
                    break;
                case 10:
                    this.cfw.add(130);
                    break;
                case 11:
                    this.cfw.add(126);
                    break;
                default:
                    throw Kit.codeBug(Token.typeToName(i));
            }
        } else {
            this.cfw.add(122);
        }
        this.cfw.add(135);
    }

    private int nodeIsDirectCallParameter(Node node) {
        if (node.getType() != 60 || !this.inDirectCallFunction || this.itsForcedObjectParameters) {
            return -1;
        }
        int varIndex = this.fnCurrent.getVarIndex(node);
        if (this.fnCurrent.isParameter(varIndex)) {
            return this.varRegisters[varIndex];
        }
        return -1;
    }

    private boolean varIsDirectCallParameter(int i) {
        return this.fnCurrent.isParameter(i) && this.inDirectCallFunction && !this.itsForcedObjectParameters;
    }

    private void genSimpleCompare(int i, int i2, int i3) {
        if (i2 == -1) {
            throw Codegen.badTree();
        }
        switch (i) {
            case 14:
                this.cfw.add(152);
                this.cfw.add(155, i2);
                break;
            case 15:
                this.cfw.add(152);
                this.cfw.add(158, i2);
                break;
            case 16:
                this.cfw.add(151);
                this.cfw.add(157, i2);
                break;
            case 17:
                this.cfw.add(151);
                this.cfw.add(156, i2);
                break;
            default:
                throw Codegen.badTree();
        }
        if (i3 != -1) {
            this.cfw.add(167, i3);
        }
    }

    private void visitIfJumpRelOp(Node node, Node node2, int i, int i2) {
        String str;
        if (i == -1 || i2 == -1) {
            throw Codegen.badTree();
        }
        int type = node.getType();
        Node next = node2.getNext();
        if (type == 58 || type == 57) {
            generateExpression(node2, node);
            generateExpression(next, node);
            this.cfw.addALoad(this.contextLocal);
            addScriptRuntimeInvoke(type == 58 ? "instanceOf" : "in", "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Z");
            this.cfw.add(154, i);
            this.cfw.add(167, i2);
            return;
        }
        int intProp = node.getIntProp(8, -1);
        int iNodeIsDirectCallParameter = nodeIsDirectCallParameter(node2);
        int iNodeIsDirectCallParameter2 = nodeIsDirectCallParameter(next);
        if (intProp != -1) {
            if (intProp != 2) {
                generateExpression(node2, node);
            } else if (iNodeIsDirectCallParameter != -1) {
                dcpLoadAsNumber(iNodeIsDirectCallParameter);
            } else {
                generateExpression(node2, node);
                addObjectToDouble();
            }
            if (intProp != 1) {
                generateExpression(next, node);
            } else if (iNodeIsDirectCallParameter2 != -1) {
                dcpLoadAsNumber(iNodeIsDirectCallParameter2);
            } else {
                generateExpression(next, node);
                addObjectToDouble();
            }
            genSimpleCompare(type, i, i2);
            return;
        }
        if (iNodeIsDirectCallParameter != -1 && iNodeIsDirectCallParameter2 != -1) {
            int stackTop = this.cfw.getStackTop();
            int iAcquireLabel = this.cfw.acquireLabel();
            this.cfw.addALoad(iNodeIsDirectCallParameter);
            this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
            this.cfw.add(166, iAcquireLabel);
            this.cfw.addDLoad(iNodeIsDirectCallParameter + 1);
            dcpLoadAsNumber(iNodeIsDirectCallParameter2);
            genSimpleCompare(type, i, i2);
            if (stackTop != this.cfw.getStackTop()) {
                throw Codegen.badTree();
            }
            this.cfw.markLabel(iAcquireLabel);
            int iAcquireLabel2 = this.cfw.acquireLabel();
            this.cfw.addALoad(iNodeIsDirectCallParameter2);
            this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
            this.cfw.add(166, iAcquireLabel2);
            this.cfw.addALoad(iNodeIsDirectCallParameter);
            addObjectToDouble();
            this.cfw.addDLoad(iNodeIsDirectCallParameter2 + 1);
            genSimpleCompare(type, i, i2);
            if (stackTop != this.cfw.getStackTop()) {
                throw Codegen.badTree();
            }
            this.cfw.markLabel(iAcquireLabel2);
            this.cfw.addALoad(iNodeIsDirectCallParameter);
            this.cfw.addALoad(iNodeIsDirectCallParameter2);
        } else {
            generateExpression(node2, node);
            generateExpression(next, node);
        }
        switch (type) {
            case 14:
                str = "MATH:COMPARELT";
                break;
            case 15:
                str = "MATH:COMPARELE";
                break;
            case 16:
                str = "MATH:COMPAREGT";
                break;
            case 17:
                str = "MATH:COMPAREGE";
                break;
            default:
                throw Kit.codeBug();
        }
        addDynamicInvoke(str, "(Ljava/lang/Object;Ljava/lang/Object;)Z");
        this.cfw.add(154, i);
        this.cfw.add(167, i2);
    }

    /* JADX WARN: Code duplicated, block: B:31:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:44:0x00e8 A[PHI: r1
      0x00e8: PHI (r1v2 java.lang.String) = (r1v1 java.lang.String), (r1v7 java.lang.String) binds: [B:33:0x00d0, B:37:0x00da] A[DONT_GENERATE, DONT_INLINE]] */
    private void visitIfJumpEqOp(Node node, Node node2, int i, int i2) {
        int i3;
        int i4;
        int i5;
        Node node3 = node2;
        if (i == -1 || i2 == -1) {
            throw Codegen.badTree();
        }
        int stackTop = this.cfw.getStackTop();
        int type = node.getType();
        Node next = node3.getNext();
        if (type != 12 && type != 13 && (node3.getType() == 47 || next.getType() == 47)) {
            if (node3.getType() == 47) {
                node3 = next;
            }
            generateExpression(node3, node);
            this.cfw.add(type == 51 ? ByteCode.IFNULL : ByteCode.IFNONNULL, i);
            this.cfw.add(167, i2);
        } else {
            int iNodeIsDirectCallParameter = nodeIsDirectCallParameter(node3);
            if (iNodeIsDirectCallParameter == -1 || next.getType() != 163) {
                i3 = 153;
                i4 = 154;
            } else {
                Node firstChild = next.getFirstChild();
                if (firstChild.getType() == 45) {
                    this.cfw.addALoad(iNodeIsDirectCallParameter);
                    this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
                    int iAcquireLabel = this.cfw.acquireLabel();
                    this.cfw.add(166, iAcquireLabel);
                    this.cfw.addDLoad(iNodeIsDirectCallParameter + 1);
                    this.cfw.addPush(firstChild.getDouble());
                    this.cfw.add(151);
                    ClassFileWriter classFileWriter = this.cfw;
                    if (type == 12) {
                        i3 = 153;
                        classFileWriter.add(153, i);
                        i4 = 154;
                    } else {
                        i3 = 153;
                        i4 = 154;
                        classFileWriter.add(154, i);
                    }
                    this.cfw.add(167, i2);
                    this.cfw.markLabel(iAcquireLabel);
                } else {
                    i3 = 153;
                    i4 = 154;
                }
            }
            generateExpression(node3, node);
            generateExpression(next, node);
            String str = "MATH:EQ";
            if (type == 12) {
                i5 = i4;
            } else {
                if (type != 13) {
                    str = "MATH:SHALLOWEQ";
                    if (type == 51) {
                        i5 = i4;
                    } else if (type != 52) {
                        throw Codegen.badTree();
                    }
                }
                i5 = i3;
            }
            addDynamicInvoke(str, "(Ljava/lang/Object;Ljava/lang/Object;)Z");
            this.cfw.add(i5, i);
            this.cfw.add(167, i2);
        }
        if (stackTop != this.cfw.getStackTop()) {
            throw Codegen.badTree();
        }
    }

    private void visitSetName(Node node, Node node2) {
        String string = node.getFirstChild().getString();
        while (node2 != null) {
            generateExpression(node2, node);
            node2 = node2.getNext();
        }
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        addDynamicInvoke("NAME:SET:" + string, "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
    }

    private void visitStrictSetName(Node node, Node node2) {
        String string = node.getFirstChild().getString();
        while (node2 != null) {
            generateExpression(node2, node);
            node2 = node2.getNext();
        }
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        addDynamicInvoke("NAME:SETSTRICT:" + string, "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
    }

    private void visitSetConst(Node node, Node node2) {
        String string = node.getFirstChild().getString();
        while (node2 != null) {
            generateExpression(node2, node);
            node2 = node2.getNext();
        }
        this.cfw.addALoad(this.contextLocal);
        addDynamicInvoke("NAME:SETCONST:" + string, Signatures.NAME_SET_CONST);
    }

    private void visitGetVar(Node node) {
        if (!this.hasVarsInRegs) {
            Kit.codeBug();
        }
        int varIndex = this.fnCurrent.getVarIndex(node);
        int i = this.varRegisters[varIndex];
        if (varIsDirectCallParameter(varIndex)) {
            if (node.getIntProp(8, -1) != -1) {
                dcpLoadAsNumber(i);
                return;
            } else {
                dcpLoadAsObject(i);
                return;
            }
        }
        boolean zIsNumberVar = this.fnCurrent.isNumberVar(varIndex);
        ClassFileWriter classFileWriter = this.cfw;
        if (zIsNumberVar) {
            classFileWriter.addDLoad(i);
        } else {
            classFileWriter.addALoad(i);
        }
    }

    private void visitSetVar(Node node, Node node2, boolean z) {
        if (!this.hasVarsInRegs) {
            Kit.codeBug();
        }
        int varIndex = this.fnCurrent.getVarIndex(node);
        generateExpression(node2.getNext(), node);
        boolean z2 = node.getIntProp(8, -1) != -1;
        int i = this.varRegisters[varIndex];
        if (this.fnCurrent.fnode.getParamAndVarConst()[varIndex]) {
            if (z) {
                return;
            }
            ClassFileWriter classFileWriter = this.cfw;
            if (z2) {
                classFileWriter.add(88);
                return;
            } else {
                classFileWriter.add(87);
                return;
            }
        }
        if (varIsDirectCallParameter(varIndex)) {
            if (z2) {
                if (z) {
                    this.cfw.add(92);
                }
                this.cfw.addALoad(i);
                this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
                int iAcquireLabel = this.cfw.acquireLabel();
                int iAcquireLabel2 = this.cfw.acquireLabel();
                this.cfw.add(165, iAcquireLabel);
                int stackTop = this.cfw.getStackTop();
                addDoubleWrap();
                this.cfw.addAStore(i);
                this.cfw.add(167, iAcquireLabel2);
                this.cfw.markLabel(iAcquireLabel, stackTop);
                this.cfw.addDStore(i + 1);
                this.cfw.markLabel(iAcquireLabel2);
                return;
            }
            if (z) {
                this.cfw.add(89);
            }
            this.cfw.addAStore(i);
            return;
        }
        boolean zIsNumberVar = this.fnCurrent.isNumberVar(varIndex);
        if (!z2) {
            if (zIsNumberVar) {
                Kit.codeBug();
            }
            this.cfw.addAStore(i);
            if (z) {
                this.cfw.addALoad(i);
                return;
            }
            return;
        }
        if (zIsNumberVar) {
            this.cfw.addDStore(i);
            if (z) {
                this.cfw.addDLoad(i);
                return;
            }
            return;
        }
        if (z) {
            this.cfw.add(92);
        }
        addDoubleWrap();
        this.cfw.addAStore(i);
    }

    private void visitSetConstVar(Node node, Node node2, boolean z) {
        if (!this.hasVarsInRegs) {
            Kit.codeBug();
        }
        int varIndex = this.fnCurrent.getVarIndex(node);
        generateExpression(node2.getNext(), node);
        boolean z2 = node.getIntProp(8, -1) != -1;
        int i = this.varRegisters[varIndex];
        int iAcquireLabel = this.cfw.acquireLabel();
        int iAcquireLabel2 = this.cfw.acquireLabel();
        ClassFileWriter classFileWriter = this.cfw;
        if (z2) {
            int i2 = i + 2;
            classFileWriter.addILoad(i2);
            this.cfw.add(154, iAcquireLabel2);
            int stackTop = this.cfw.getStackTop();
            this.cfw.addPush(1);
            this.cfw.addIStore(i2);
            this.cfw.addDStore(i);
            ClassFileWriter classFileWriter2 = this.cfw;
            if (z) {
                classFileWriter2.addDLoad(i);
                this.cfw.markLabel(iAcquireLabel2, stackTop);
            } else {
                classFileWriter2.add(167, iAcquireLabel);
                this.cfw.markLabel(iAcquireLabel2, stackTop);
                this.cfw.add(88);
            }
        } else {
            int i3 = i + 1;
            classFileWriter.addILoad(i3);
            this.cfw.add(154, iAcquireLabel2);
            int stackTop2 = this.cfw.getStackTop();
            this.cfw.addPush(1);
            this.cfw.addIStore(i3);
            this.cfw.addAStore(i);
            ClassFileWriter classFileWriter3 = this.cfw;
            if (z) {
                classFileWriter3.addALoad(i);
                this.cfw.markLabel(iAcquireLabel2, stackTop2);
            } else {
                classFileWriter3.add(167, iAcquireLabel);
                this.cfw.markLabel(iAcquireLabel2, stackTop2);
                this.cfw.add(87);
            }
        }
        this.cfw.markLabel(iAcquireLabel);
    }

    private void visitGetProp(Node node, Node node2) {
        generateExpression(node2, node);
        if (node.getIntProp(30, 0) == 1) {
            int iAcquireLabel = this.cfw.acquireLabel();
            int iAcquireLabel2 = this.cfw.acquireLabel();
            this.cfw.add(89);
            addOptRuntimeInvoke("isNullOrUndefined", Signatures.MATH_TO_BOOLEAN);
            this.cfw.add(153, iAcquireLabel);
            this.cfw.add(87);
            this.cfw.add(178, "org/mozilla/javascript/Undefined", "instance", "Ljava/lang/Object;");
            this.cfw.add(167, iAcquireLabel2);
            this.cfw.markLabel(iAcquireLabel);
            finishGetPropGeneration(node, node2.getNext());
            this.cfw.markLabel(iAcquireLabel2);
            return;
        }
        finishGetPropGeneration(node, node2.getNext());
    }

    private void finishGetPropGeneration(Node node, Node node2) {
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        if (node.getIntProp(31, 0) == 1) {
            this.cfw.addALoad(this.thisObjLocal);
            this.cfw.addLoadConstant(node.getType() == 34 ? 1 : 0);
            addDynamicInvoke("PROP:GETSUPER:" + node2.getString(), Signatures.PROP_GET_SUPER);
            return;
        }
        if (node.getType() == 34) {
            addDynamicInvoke("PROP:GETNOWARN:" + node2.getString(), "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
            return;
        }
        addDynamicInvoke("PROP:GET:" + node2.getString(), "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
    }

    private void visitSetProp(int i, Node node, Node node2) {
        generateExpression(node2, node);
        Node next = node2.getNext();
        if (i == 153) {
            this.cfw.add(89);
            finishGetPropGeneration(node, next);
        }
        generateExpression(next.getNext(), node);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        if (node.getIntProp(31, 0) == 1) {
            this.cfw.addALoad(this.thisObjLocal);
            addDynamicInvoke("PROP:SETSUPER:" + next.getString(), "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;)Ljava/lang/Object;");
            return;
        }
        addDynamicInvoke("PROP:SET:" + next.getString(), "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
    }

    private void visitSetElem(int i, Node node, Node node2) {
        generateExpression(node2, node);
        Node next = node2.getNext();
        if (i == 154) {
            this.cfw.add(89);
        }
        generateExpression(next, node);
        Node next2 = next.getNext();
        boolean z = node.getIntProp(8, -1) != -1;
        boolean z2 = node.getIntProp(31, 0) == 1;
        if (i == 154) {
            if (z2) {
                this.cfw.add(90);
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                this.cfw.addALoad(this.thisObjLocal);
                addDynamicInvoke("PROP:GETELEMENTSUPER", "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;)Ljava/lang/Object;");
            } else {
                ClassFileWriter classFileWriter = this.cfw;
                if (z) {
                    classFileWriter.add(93);
                    this.cfw.addALoad(this.contextLocal);
                    this.cfw.addALoad(this.variableObjectLocal);
                    addDynamicInvoke("PROP:GETINDEX", Signatures.PROP_GET_INDEX);
                } else {
                    classFileWriter.add(90);
                    this.cfw.addALoad(this.contextLocal);
                    this.cfw.addALoad(this.variableObjectLocal);
                    addDynamicInvoke("PROP:GETELEMENT", "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
                }
            }
        }
        generateExpression(next2, node);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        if (z2) {
            this.cfw.addALoad(this.thisObjLocal);
            addDynamicInvoke("PROP:SETELEMENTSUPER", Signatures.PROP_SET_ELEMENT_SUPER);
        } else if (z) {
            addDynamicInvoke("PROP:SETINDEX", Signatures.PROP_SET_INDEX);
        } else {
            addDynamicInvoke("PROP:SETELEMENT", Signatures.PROP_SET_ELEMENT);
        }
    }

    private void visitDotQuery(Node node, Node node2) {
        updateLineNumber(node);
        generateExpression(node2, node);
        this.cfw.addALoad(this.variableObjectLocal);
        addScriptRuntimeInvoke("enterDotQuery", "(Ljava/lang/Object;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
        this.cfw.addAStore(this.variableObjectLocal);
        this.cfw.add(1);
        int iAcquireLabel = this.cfw.acquireLabel();
        this.cfw.markLabel(iAcquireLabel);
        this.cfw.add(87);
        generateExpression(node2.getNext(), node);
        addDynamicInvoke("MATH:TOBOOLEAN", Signatures.MATH_TO_BOOLEAN);
        this.cfw.addALoad(this.variableObjectLocal);
        addScriptRuntimeInvoke("updateDotQuery", "(ZLorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
        this.cfw.add(89);
        this.cfw.add(ByteCode.IFNULL, iAcquireLabel);
        this.cfw.addALoad(this.variableObjectLocal);
        addScriptRuntimeInvoke("leaveDotQuery", "(Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
        this.cfw.addAStore(this.variableObjectLocal);
    }

    private static int getLocalBlockRegister(Node node) {
        return ((Node) node.getProp(3)).getExistingIntProp(2);
    }

    private void dcpLoadAsNumber(int i) {
        this.cfw.addALoad(i);
        this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
        int iAcquireLabel = this.cfw.acquireLabel();
        this.cfw.add(165, iAcquireLabel);
        int stackTop = this.cfw.getStackTop();
        this.cfw.addALoad(i);
        addObjectToDouble();
        int iAcquireLabel2 = this.cfw.acquireLabel();
        this.cfw.add(167, iAcquireLabel2);
        this.cfw.markLabel(iAcquireLabel, stackTop);
        this.cfw.addDLoad(i + 1);
        this.cfw.markLabel(iAcquireLabel2);
    }

    private void dcpLoadAsObject(int i) {
        this.cfw.addALoad(i);
        this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
        int iAcquireLabel = this.cfw.acquireLabel();
        this.cfw.add(165, iAcquireLabel);
        int stackTop = this.cfw.getStackTop();
        this.cfw.addALoad(i);
        int iAcquireLabel2 = this.cfw.acquireLabel();
        this.cfw.add(167, iAcquireLabel2);
        this.cfw.markLabel(iAcquireLabel, stackTop);
        this.cfw.addDLoad(i + 1);
        addDoubleWrap();
        this.cfw.markLabel(iAcquireLabel2);
    }

    private void addGoto(Node node, int i) {
        this.cfw.add(i, getTargetLabel(node));
    }

    private void addObjectToDouble() {
        addDynamicInvoke("MATH:TONUMBER", Signatures.MATH_TO_NUMBER);
    }

    private void addObjectToNumeric() {
        addDynamicInvoke("MATH:TONUMERIC", Signatures.MATH_TO_NUMERIC);
    }

    private void addNewObjectArray(int i) {
        if (i == 0) {
            int i2 = this.itsZeroArgArray;
            ClassFileWriter classFileWriter = this.cfw;
            if (i2 >= 0) {
                classFileWriter.addALoad(i2);
                return;
            } else {
                classFileWriter.add(178, "org/mozilla/javascript/ScriptRuntime", "emptyArgs", "[Ljava/lang/Object;");
                return;
            }
        }
        this.cfw.addPush(i);
        this.cfw.add(189, "java/lang/Object");
    }

    private void addScriptRuntimeInvoke(String str, String str2) {
        this.cfw.addInvoke(184, "org.mozilla.javascript.ScriptRuntime", str, str2);
    }

    private void addOptRuntimeInvoke(String str, String str2) {
        this.cfw.addInvoke(184, "org/mozilla/javascript/optimizer/OptRuntime", str, str2);
    }

    private void addDynamicInvoke(String str, String str2) {
        this.cfw.addInvokeDynamic(str, str2, Bootstrapper.BOOTSTRAP_HANDLE, new Object[0]);
    }

    private void addJumpedBooleanWrap(int i, int i2) {
        this.cfw.markLabel(i2);
        int iAcquireLabel = this.cfw.acquireLabel();
        this.cfw.add(178, "java/lang/Boolean", "FALSE", "Ljava/lang/Boolean;");
        this.cfw.add(167, iAcquireLabel);
        this.cfw.markLabel(i);
        this.cfw.add(178, "java/lang/Boolean", "TRUE", "Ljava/lang/Boolean;");
        this.cfw.markLabel(iAcquireLabel);
        this.cfw.adjustStackTop(-1);
    }

    private void addDoubleWrap() {
        addOptRuntimeInvoke("wrapDouble", "(D)Ljava/lang/Double;");
    }

    private short getNewWordPairLocal(boolean z) {
        return getNewWordIntern(z ? 3 : 2);
    }

    private short getNewWordLocal(boolean z) {
        return getNewWordIntern(z ? 2 : 1);
    }

    private short getNewWordLocal() {
        return getNewWordIntern(1);
    }

    private short getNewWordIntern(int i) {
        int[] iArr = this.locals;
        int i2 = this.firstFreeLocal;
        if (i > 1) {
            loop0: while (true) {
                if (i2 + i > 1024) {
                    i2 = -1;
                    break;
                }
                int i3 = 0;
                while (true) {
                    if (i3 >= i) {
                        break loop0;
                    }
                    if (iArr[i2 + i3] != 0) {
                        break;
                    }
                    i3++;
                }
                i2 += i3 + 1;
            }
        }
        if (i2 != -1) {
            iArr[i2] = 1;
            if (i > 1) {
                iArr[i2 + 1] = 1;
            }
            if (i > 2) {
                iArr[i2 + 2] = 1;
            }
            if (i2 != this.firstFreeLocal) {
                return (short) i2;
            }
            for (int i4 = i + i2; i4 < 1024; i4++) {
                if (iArr[i4] == 0) {
                    short s = (short) i4;
                    this.firstFreeLocal = s;
                    if (this.localsMax < s) {
                        this.localsMax = s;
                    }
                    return (short) i2;
                }
            }
        }
        throw Context.reportRuntimeError("Program too complex (out of locals)");
    }

    private void incReferenceWordLocal(int i) {
        int[] iArr = this.locals;
        iArr[i] = iArr[i] + 1;
    }

    private void decReferenceWordLocal(int i) {
        int[] iArr = this.locals;
        iArr[i] = iArr[i] - 1;
    }

    private void releaseWordLocal(int i) {
        if (i < this.firstFreeLocal) {
            this.firstFreeLocal = i;
        }
        this.locals[i] = 0;
    }

    static class FinallyReturnPoint {
        public List<Integer> jsrPoints = new ArrayList();
        public int tableLabel = 0;

        FinallyReturnPoint() {
        }
    }
}
