package org.mozilla.javascript;

import j$.util.DesugarArrays;
import j$.util.function.Predicate$CC;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.AstRoot;
import org.mozilla.javascript.ast.Block;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.Jump;
import org.mozilla.javascript.ast.Scope;
import org.mozilla.javascript.ast.ScriptNode;
import org.mozilla.javascript.ast.TemplateCharacters;

/* JADX INFO: loaded from: classes8.dex */
class CodeGenerator extends Icode {
    private static final int ECF_TAIL = 1;
    private static final int MIN_FIXUP_TABLE_SIZE = 40;
    private static final int MIN_LABEL_TABLE_SIZE = 32;
    private CompilerEnvirons compilerEnv;
    private int doubleTableTop;
    private int exceptionTableTop;
    private long[] fixupTable;
    private int fixupTableTop;
    private int iCodeTop;
    private InterpreterData itsData;
    private boolean itsInFunctionFlag;
    private boolean itsInTryFlag;
    private int[] labelTable;
    private int labelTableTop;
    private int localTop;
    private ScriptNode scriptOrFn;
    private int stackDepth;
    private int lineNumber = -1;
    private final HashMap<String, Integer> strings = new HashMap<>();
    private final HashMap<BigInteger, Integer> bigInts = new HashMap<>();
    private final ArrayList<Object> literalIds = new ArrayList<>();

    CodeGenerator() {
    }

    public InterpreterData compile(CompilerEnvirons compilerEnvirons, ScriptNode scriptNode, String str, boolean z) {
        this.compilerEnv = compilerEnvirons;
        new NodeTransformer().transform(scriptNode, compilerEnvirons);
        if (z) {
            this.scriptOrFn = scriptNode.getFunctionNode(0);
        } else {
            this.scriptOrFn = scriptNode;
        }
        InterpreterData interpreterData = new InterpreterData(compilerEnvirons.getLanguageVersion(), this.scriptOrFn.getSourceName(), str, this.scriptOrFn.isInStrictMode());
        this.itsData = interpreterData;
        interpreterData.topLevel = true;
        if (z) {
            generateFunctionICode();
        } else {
            generateICodeFromTree(this.scriptOrFn);
        }
        return this.itsData;
    }

    private void generateFunctionICode() {
        this.itsInFunctionFlag = true;
        FunctionNode functionNode = (FunctionNode) this.scriptOrFn;
        this.itsData.itsFunctionType = functionNode.getFunctionType();
        this.itsData.itsNeedsActivation = functionNode.requiresActivation();
        if (functionNode.getFunctionName() != null) {
            this.itsData.itsName = functionNode.getName();
        }
        if (functionNode.isGenerator()) {
            addIcode(-69);
            addUint16(functionNode.getBaseLineno() & 65535);
        }
        if (functionNode.isInStrictMode()) {
            this.itsData.isStrict = true;
        }
        if (functionNode.isES6Generator()) {
            this.itsData.isES6Generator = true;
        }
        generateICodeFromTree(functionNode.getLastChild());
    }

    private void generateICodeFromTree(Node node) {
        generateNestedFunctions();
        generateRegExpLiterals();
        generateTemplateLiterals();
        visitStatement(node, 0);
        fixLabelGotos();
        if (this.itsData.itsFunctionType == 0) {
            addToken(70);
        }
        int length = this.itsData.itsICode.length;
        int i = this.iCodeTop;
        if (length != i) {
            byte[] bArr = new byte[i];
            System.arraycopy(this.itsData.itsICode, 0, bArr, 0, this.iCodeTop);
            this.itsData.itsICode = bArr;
        }
        int size = this.strings.size();
        InterpreterData interpreterData = this.itsData;
        if (size == 0) {
            interpreterData.itsStringTable = null;
        } else {
            interpreterData.itsStringTable = new String[this.strings.size()];
            for (Map.Entry<String, Integer> entry : this.strings.entrySet()) {
                String key = entry.getKey();
                int iIntValue = entry.getValue().intValue();
                if (this.itsData.itsStringTable[iIntValue] != null) {
                    Kit.codeBug();
                }
                this.itsData.itsStringTable[iIntValue] = key;
            }
        }
        int i2 = this.doubleTableTop;
        InterpreterData interpreterData2 = this.itsData;
        if (i2 == 0) {
            interpreterData2.itsDoubleTable = null;
        } else {
            int length2 = interpreterData2.itsDoubleTable.length;
            int i3 = this.doubleTableTop;
            if (length2 != i3) {
                double[] dArr = new double[i3];
                System.arraycopy(this.itsData.itsDoubleTable, 0, dArr, 0, this.doubleTableTop);
                this.itsData.itsDoubleTable = dArr;
            }
        }
        int size2 = this.bigInts.size();
        InterpreterData interpreterData3 = this.itsData;
        if (size2 == 0) {
            interpreterData3.itsBigIntTable = null;
        } else {
            interpreterData3.itsBigIntTable = new BigInteger[this.bigInts.size()];
            for (Map.Entry<BigInteger, Integer> entry2 : this.bigInts.entrySet()) {
                BigInteger key2 = entry2.getKey();
                int iIntValue2 = entry2.getValue().intValue();
                if (this.itsData.itsBigIntTable[iIntValue2] != null) {
                    Kit.codeBug();
                }
                this.itsData.itsBigIntTable[iIntValue2] = key2;
            }
        }
        if (this.exceptionTableTop != 0) {
            int length3 = this.itsData.itsExceptionTable.length;
            int i4 = this.exceptionTableTop;
            if (length3 != i4) {
                int[] iArr = new int[i4];
                System.arraycopy(this.itsData.itsExceptionTable, 0, iArr, 0, this.exceptionTableTop);
                this.itsData.itsExceptionTable = iArr;
            }
        }
        this.itsData.itsMaxVars = this.scriptOrFn.getParamAndVarCount();
        InterpreterData interpreterData4 = this.itsData;
        interpreterData4.itsMaxFrameArray = interpreterData4.itsMaxVars + this.itsData.itsMaxLocals + this.itsData.itsMaxStack;
        this.itsData.argNames = this.scriptOrFn.getParamAndVarNames();
        this.itsData.argIsConst = this.scriptOrFn.getParamAndVarConst();
        this.itsData.argCount = this.scriptOrFn.getParamCount();
        this.itsData.argsHasRest = this.scriptOrFn.hasRestParameter();
        this.itsData.argsHasDefaults = this.scriptOrFn.getDefaultParams() != null;
        this.itsData.rawSourceStart = this.scriptOrFn.getRawSourceStart();
        this.itsData.rawSourceEnd = this.scriptOrFn.getRawSourceEnd();
        if (this.literalIds.size() != 0) {
            this.itsData.literalIds = this.literalIds.toArray();
        }
    }

    private void generateNestedFunctions() {
        int functionCount = this.scriptOrFn.getFunctionCount();
        if (functionCount == 0) {
            return;
        }
        InterpreterData[] interpreterDataArr = new InterpreterData[functionCount];
        for (int i = 0; i != functionCount; i++) {
            FunctionNode functionNode = this.scriptOrFn.getFunctionNode(i);
            CodeGenerator codeGenerator = new CodeGenerator();
            codeGenerator.compilerEnv = this.compilerEnv;
            codeGenerator.scriptOrFn = functionNode;
            codeGenerator.itsData = new InterpreterData(this.itsData);
            codeGenerator.generateFunctionICode();
            interpreterDataArr[i] = codeGenerator.itsData;
            AstNode parent = functionNode.getParent();
            if (!(parent instanceof AstRoot) && !(parent instanceof Scope) && !(parent instanceof Block)) {
                codeGenerator.itsData.declaredAsFunctionExpression = true;
            }
        }
        this.itsData.itsNestedFunctions = interpreterDataArr;
    }

    private void generateRegExpLiterals() {
        int regexpCount = this.scriptOrFn.getRegexpCount();
        if (regexpCount == 0) {
            return;
        }
        Context context = Context.getContext();
        RegExpProxy regExpProxyCheckRegExpProxy = ScriptRuntime.checkRegExpProxy(context);
        Object[] objArr = new Object[regexpCount];
        for (int i = 0; i != regexpCount; i++) {
            objArr[i] = regExpProxyCheckRegExpProxy.compileRegExp(context, this.scriptOrFn.getRegexpString(i), this.scriptOrFn.getRegexpFlags(i));
        }
        this.itsData.itsRegExpLiterals = objArr;
    }

    private void generateTemplateLiterals() {
        int templateLiteralCount = this.scriptOrFn.getTemplateLiteralCount();
        if (templateLiteralCount == 0) {
            return;
        }
        Object[] objArr = new Object[templateLiteralCount];
        for (int i = 0; i != templateLiteralCount; i++) {
            List<TemplateCharacters> templateLiteralStrings = this.scriptOrFn.getTemplateLiteralStrings(i);
            String[] strArr = new String[templateLiteralStrings.size() * 2];
            int i2 = 0;
            for (TemplateCharacters templateCharacters : templateLiteralStrings) {
                int i3 = i2 + 1;
                strArr[i2] = templateCharacters.getValue();
                i2 += 2;
                strArr[i3] = templateCharacters.getRawValue();
            }
            objArr[i] = strArr;
        }
        this.itsData.itsTemplateLiterals = objArr;
    }

    private void updateLineNumber(Node node) {
        int lineno = node.getLineno();
        if (lineno == this.lineNumber || lineno < 0) {
            return;
        }
        if (this.itsData.firstLinePC < 0) {
            this.itsData.firstLinePC = lineno;
        }
        this.lineNumber = lineno;
        addIcode(-31);
        addUint16(lineno & 65535);
    }

    private static RuntimeException badTree(Node node) {
        throw new RuntimeException(node.toString());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:73:0x018e  */
    /* JADX WARN: Code duplicated, block: B:75:0x0193 A[LOOP:0: B:74:0x0191->B:75:0x0193, LOOP_END] */
    private void visitStatement(Node node, int i) {
        CodeGenerator codeGenerator;
        int type = node.getType();
        Node firstChild = node.getFirstChild();
        if (type == -69) {
            codeGenerator = this;
        } else if (type == 70) {
            codeGenerator = this;
            codeGenerator.updateLineNumber(node);
            codeGenerator.addToken(70);
        } else if (type != 90) {
            if (type == 122) {
                int existingIntProp = node.getExistingIntProp(1);
                int functionType = this.scriptOrFn.getFunctionNode(existingIntProp).getFunctionType();
                if (functionType == 3) {
                    addIndexOp(-24, existingIntProp);
                } else if (functionType != 1) {
                    throw Kit.codeBug();
                }
                if (!this.itsInFunctionFlag) {
                    addIndexOp(-23, existingIntProp);
                    stackChange(1);
                    addIcode(-5);
                    stackChange(-1);
                }
            } else if (type == 127) {
                updateLineNumber(node);
                visitExpression(firstChild, 0);
                for (Jump jump = (Jump) firstChild.getNext(); jump != null; jump = (Jump) jump.getNext()) {
                    if (jump.getType() != 128) {
                        throw badTree(jump);
                    }
                    Node firstChild2 = jump.getFirstChild();
                    addIcode(-1);
                    stackChange(1);
                    visitExpression(firstChild2, 0);
                    addToken(51);
                    stackChange(-1);
                    addGoto(jump.target, -6);
                    stackChange(-1);
                }
                addIcode(-4);
                stackChange(-1);
            } else if (type == 136) {
                updateLineNumber(node);
                while (firstChild != null) {
                    visitStatement(firstChild, i);
                    firstChild = firstChild.getNext();
                }
            } else if (type == 138) {
                stackChange(1);
                int localBlockRef = getLocalBlockRef(node);
                addIndexOp(-29, localBlockRef);
                stackChange(-1);
                while (firstChild != null) {
                    visitStatement(firstChild, i);
                    firstChild = firstChild.getNext();
                }
                addIndexOp(-30, localBlockRef);
            } else if (type == 141) {
                updateLineNumber(node);
                while (firstChild != null) {
                    visitStatement(firstChild, i);
                    firstChild = firstChild.getNext();
                }
            } else if (type == 155) {
                int iAllocLocal = allocLocal();
                node.putIntProp(2, iAllocLocal);
                updateLineNumber(node);
                while (firstChild != null) {
                    visitStatement(firstChild, i);
                    firstChild = firstChild.getNext();
                }
                addIndexOp(-63, iAllocLocal);
                releaseLocal(iAllocLocal);
            } else if (type == 174) {
                addIcode(-71);
            } else if (type == 55) {
                updateLineNumber(node);
                visitExpression(firstChild, 0);
                addToken(55);
                addUint16(this.lineNumber & 65535);
                stackChange(-1);
            } else if (type != 56) {
                switch (type) {
                    case 2:
                        visitExpression(firstChild, 0);
                        addToken(2);
                        stackChange(-1);
                        break;
                    case 3:
                        addToken(3);
                        break;
                    case 4:
                        updateLineNumber(node);
                        if (node.getIntProp(20, 0) != 0) {
                            if (firstChild == null || this.compilerEnv.getLanguageVersion() < 200) {
                                addIcode(-70);
                                addUint16(this.lineNumber & 65535);
                            } else {
                                visitExpression(firstChild, 1);
                                addIcode(-72);
                                addUint16(this.lineNumber & 65535);
                                stackChange(-1);
                            }
                        } else if (firstChild == null) {
                            addIcode(-27);
                        } else {
                            visitExpression(firstChild, 1);
                            addToken(4);
                            stackChange(-1);
                        }
                        break;
                    case 5:
                        addGoto(((Jump) node).target, type);
                        break;
                    case 6:
                    case 7:
                        Node node2 = ((Jump) node).target;
                        visitExpression(firstChild, 0);
                        addGoto(node2, type);
                        stackChange(-1);
                        break;
                    default:
                        switch (type) {
                            case 62:
                                int localBlockRef2 = getLocalBlockRef(node);
                                int existingIntProp2 = node.getExistingIntProp(14);
                                String string = firstChild.getType() == 44 ? firstChild.getString() : "";
                                visitExpression(firstChild.getNext(), 0);
                                addStringPrefix(string);
                                addIndexPrefix(localBlockRef2);
                                addToken(62);
                                addUint8(existingIntProp2 == 0 ? 0 : 1);
                                stackChange(-1);
                                break;
                            case 63:
                            case 64:
                            case 65:
                            case 66:
                                visitExpression(firstChild, 0);
                                addIndexOp(type, getLocalBlockRef(node));
                                stackChange(-1);
                                break;
                            default:
                                switch (type) {
                                    case 143:
                                    case 144:
                                    case 146:
                                        updateLineNumber(node);
                                        while (firstChild != null) {
                                            visitStatement(firstChild, i);
                                            firstChild = firstChild.getNext();
                                        }
                                        break;
                                    case 145:
                                        markTargetLabel(node);
                                        break;
                                    case 147:
                                    case 148:
                                        updateLineNumber(node);
                                        visitExpression(firstChild, 0);
                                        addIcode(type == 147 ? -4 : -5);
                                        stackChange(-1);
                                        break;
                                    case 149:
                                        addGoto(((Jump) node).target, -28);
                                        break;
                                    case 150:
                                        while (firstChild != null) {
                                            visitStatement(firstChild, i);
                                            firstChild = firstChild.getNext();
                                        }
                                        break;
                                    default:
                                        throw badTree(node);
                                }
                                break;
                        }
                        break;
                }
            } else {
                updateLineNumber(node);
                addIndexOp(56, getLocalBlockRef(node));
            }
            codeGenerator = this;
        } else {
            Jump jump2 = (Jump) node;
            int localBlockRef3 = getLocalBlockRef(jump2);
            int iAllocLocal2 = allocLocal();
            addIndexOp(-13, iAllocLocal2);
            int i2 = this.iCodeTop;
            boolean z = this.itsInTryFlag;
            this.itsInTryFlag = true;
            while (firstChild != null) {
                visitStatement(firstChild, i);
                firstChild = firstChild.getNext();
            }
            this.itsInTryFlag = z;
            Node node3 = jump2.target;
            if (node3 != null) {
                int i3 = this.labelTable[getTargetLabel(node3)];
                codeGenerator = this;
                codeGenerator.addExceptionHandler(i2, i3, i3, false, localBlockRef3, iAllocLocal2);
            } else {
                codeGenerator = this;
            }
            Node node4 = jump2.getFinally();
            if (node4 != null) {
                int i4 = codeGenerator.labelTable[codeGenerator.getTargetLabel(node4)];
                codeGenerator.addExceptionHandler(i2, i4, i4, true, localBlockRef3, iAllocLocal2);
            }
            codeGenerator.addIndexOp(-63, iAllocLocal2);
            codeGenerator.releaseLocal(iAllocLocal2);
        }
        if (codeGenerator.stackDepth != i) {
            throw Kit.codeBug();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:107:0x0344  */
    /* JADX WARN: Code duplicated, block: B:109:0x0357  */
    /* JADX WARN: Code duplicated, block: B:112:0x0373  */
    /* JADX WARN: Code duplicated, block: B:113:0x0376  */
    /* JADX WARN: Code duplicated, block: B:122:0x03cb  */
    /* JADX WARN: Code duplicated, block: B:124:0x03de  */
    /* JADX WARN: Code duplicated, block: B:127:0x03f7  */
    /* JADX WARN: Code duplicated, block: B:128:0x03fa  */
    /* JADX WARN: Code duplicated, block: B:152:0x048d  */
    /* JADX WARN: Code duplicated, block: B:154:0x0493  */
    /* JADX WARN: Code duplicated, block: B:155:0x0495  */
    /* JADX WARN: Code duplicated, block: B:157:0x0498  */
    /* JADX WARN: Code duplicated, block: B:158:0x049d  */
    /* JADX WARN: Code duplicated, block: B:160:0x04a3  */
    /* JADX WARN: Code duplicated, block: B:170:0x04c3 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:175:0x04db  */
    /* JADX WARN: Code duplicated, block: B:177:0x04e3  */
    /* JADX WARN: Code duplicated, block: B:178:0x04e9  */
    /* JADX WARN: Code duplicated, block: B:180:0x04ed  */
    /* JADX WARN: Code duplicated, block: B:189:0x0505  */
    /* JADX WARN: Code duplicated, block: B:190:0x050a  */
    /* JADX WARN: Code duplicated, block: B:193:0x0515  */
    /* JADX WARN: Code duplicated, block: B:195:0x051b  */
    /* JADX WARN: Code duplicated, block: B:196:0x0523  */
    /* JADX WARN: Code duplicated, block: B:198:0x052a  */
    /* JADX WARN: Code duplicated, block: B:199:0x0533  */
    /* JADX WARN: Code duplicated, block: B:200:0x0537  */
    /* JADX WARN: Code duplicated, block: B:201:0x0548  */
    /* JADX WARN: Code duplicated, block: B:214:0x058d  */
    /* JADX WARN: Code duplicated, block: B:216:0x04b1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:221:0x04b9 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:222:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:68:0x01e1 A[FALL_THROUGH] */
    /* JADX WARN: Code duplicated, block: B:87:0x02a7  */
    /* JADX WARN: Code duplicated, block: B:89:0x02b2  */
    private void visitExpression(Node node, int i) {
        int i2;
        boolean z;
        CompleteOptionalCallJump completeOptionalCallJumpGenerateCallFunAndThis;
        int i3;
        String string;
        int i4;
        int i5;
        int type = node.getType();
        Node firstChild = node.getFirstChild();
        int i6 = this.stackDepth;
        if (type != 179) {
            if (type != 180) {
                i2 = 65535;
                switch (type) {
                    case 8:
                        String string2 = firstChild.getString();
                        visitExpression(firstChild, 0);
                        visitExpression(firstChild.getNext(), 0);
                        addStringOp(type, string2);
                        stackChange(-1);
                        break;
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                        visitExpression(firstChild, 0);
                        visitExpression(firstChild.getNext(), 0);
                        addToken(type);
                        stackChange(-1);
                        break;
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 32:
                        visitExpression(firstChild, 0);
                        if (type == 139) {
                            addIcode(-4);
                            addIcode(-57);
                        } else {
                            addToken(type);
                        }
                        break;
                    case 30:
                        if (node.getIntProp(30, 0) == 1) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (type == 30) {
                            visitExpression(firstChild, 0);
                            completeOptionalCallJumpGenerateCallFunAndThis = null;
                        } else {
                            completeOptionalCallJumpGenerateCallFunAndThis = generateCallFunAndThis(firstChild, z);
                            if (completeOptionalCallJumpGenerateCallFunAndThis != null) {
                                resolveForwardGoto(completeOptionalCallJumpGenerateCallFunAndThis.putArgsAndDoCallLabel);
                            }
                        }
                        i3 = 0;
                        while (true) {
                            firstChild = firstChild.getNext();
                            if (firstChild != null) {
                                visitExpression(firstChild, 0);
                                i3++;
                            } else {
                                int intProp = node.getIntProp(10, 0);
                                if (type == 76 && intProp != 0) {
                                    addIndexOp(-25, i3);
                                    addUint8(intProp);
                                    addUint8(type == 30 ? 1 : 0);
                                    addUint16(this.lineNumber & 65535);
                                } else if (node.getIntProp(31, 0) == 1) {
                                    addIndexOp(-85, i3);
                                } else {
                                    if (type == 43 && (i & 1) != 0 && !this.compilerEnv.isGenerateDebugInfo() && !this.itsInTryFlag) {
                                        type = -62;
                                    }
                                    addIndexOp(type, i3);
                                }
                                if (type == 30) {
                                    stackChange(-i3);
                                } else {
                                    stackChange((-1) - i3);
                                }
                                if (i3 > this.itsData.itsMaxCalleeArgs) {
                                    this.itsData.itsMaxCalleeArgs = i3;
                                }
                                if (completeOptionalCallJumpGenerateCallFunAndThis != null) {
                                    resolveForwardGoto(completeOptionalCallJumpGenerateCallFunAndThis.afterLabel);
                                }
                            }
                            break;
                        }
                        break;
                    case 31:
                        boolean z2 = firstChild.getType() == 54;
                        visitExpression(firstChild, 0);
                        visitExpression(firstChild.getNext(), 0);
                        if (node.getIntProp(31, 0) == 1) {
                            addIcode(-86);
                        } else if (z2) {
                            addIcode(0);
                        } else {
                            addToken(31);
                        }
                        stackChange(-1);
                        break;
                    case 33:
                    case 34:
                        visitExpression(firstChild, 0);
                        Node next = firstChild.getNext();
                        if (node.getIntProp(30, 0) == 1) {
                            addIcode(-1);
                            stackChange(1);
                            int i7 = this.iCodeTop;
                            addGotoOp(-83);
                            stackChange(-1);
                            addStringOp(type, next.getString());
                            int i8 = this.iCodeTop;
                            addGotoOp(5);
                            resolveForwardGoto(i7);
                            addIcode(-4);
                            addStringOp(44, "undefined");
                            resolveForwardGoto(i8);
                        } else if (node.getIntProp(31, 0) == 1) {
                            addStringOp(type == 33 ? 35 : 36, next.getString());
                        } else {
                            addStringOp(type, next.getString());
                        }
                        break;
                    default:
                        switch (type) {
                            case 37:
                                visitExpression(firstChild, 0);
                                Node next2 = firstChild.getNext();
                                string = next2.getString();
                                Node next3 = next2.getNext();
                                if (type == 153) {
                                    addIcode(-1);
                                    stackChange(1);
                                    addStringOp(33, string);
                                    stackChange(-1);
                                }
                                visitExpression(next3, 0);
                                if (node.getIntProp(31, 0) == 1) {
                                    i4 = 38;
                                } else {
                                    i4 = 37;
                                }
                                addStringOp(i4, string);
                                stackChange(-1);
                                break;
                            case 39:
                                visitExpression(firstChild, 0);
                                Node next4 = firstChild.getNext();
                                if (node.getIntProp(30, 0) == 1) {
                                    addIcode(-1);
                                    stackChange(1);
                                    int i9 = this.iCodeTop;
                                    addGotoOp(-83);
                                    stackChange(-1);
                                    finishGetElemGeneration(next4);
                                    int i10 = this.iCodeTop;
                                    addGotoOp(5);
                                    resolveForwardGoto(i9);
                                    addIcode(-4);
                                    addStringOp(44, "undefined");
                                    resolveForwardGoto(i10);
                                } else if (node.getIntProp(31, 0) == 1) {
                                    visitExpression(next4, 0);
                                    addToken(40);
                                    stackChange(-1);
                                } else {
                                    finishGetElemGeneration(next4);
                                }
                                break;
                            case 41:
                                visitExpression(firstChild, 0);
                                Node next5 = firstChild.getNext();
                                visitExpression(next5, 0);
                                Node next6 = next5.getNext();
                                if (type == 154) {
                                    addIcode(-2);
                                    stackChange(2);
                                    addToken(39);
                                    stackChange(-1);
                                    stackChange(-1);
                                }
                                visitExpression(next6, 0);
                                if (node.getIntProp(31, 0) == 1) {
                                    i5 = 42;
                                } else {
                                    i5 = 41;
                                }
                                addToken(i5);
                                stackChange(-2);
                                break;
                            case 98:
                                Node lastChild = node.getLastChild();
                                while (firstChild != lastChild) {
                                    visitExpression(firstChild, 0);
                                    addIcode(-4);
                                    stackChange(-1);
                                    firstChild = firstChild.getNext();
                                }
                                visitExpression(firstChild, i & 1);
                                break;
                            case 115:
                                Node next7 = firstChild.getNext();
                                Node next8 = next7.getNext();
                                visitExpression(firstChild, 0);
                                int i11 = this.iCodeTop;
                                addGotoOp(7);
                                stackChange(-1);
                                int i12 = i & 1;
                                visitExpression(next7, i12);
                                int i13 = this.iCodeTop;
                                addGotoOp(5);
                                resolveForwardGoto(i11);
                                this.stackDepth = i6;
                                visitExpression(next8, i12);
                                resolveForwardGoto(i13);
                                break;
                            case 122:
                                int existingIntProp = node.getExistingIntProp(1);
                                FunctionNode functionNode = this.scriptOrFn.getFunctionNode(existingIntProp);
                                if (functionNode.getFunctionType() != 2 && functionNode.getFunctionType() != 4) {
                                    throw Kit.codeBug();
                                }
                                addIndexOp(-23, existingIntProp);
                                if (functionNode.isMethodDefinition()) {
                                    addIcode(-37);
                                }
                                stackChange(1);
                                break;
                                break;
                            case 139:
                                visitExpression(firstChild, 0);
                                if (type == 139) {
                                    addIcode(-4);
                                    addIcode(-57);
                                } else {
                                    addToken(type);
                                }
                                break;
                            case 156:
                                visitExpression(firstChild, 0);
                                Node next9 = firstChild.getNext();
                                if (type == 156) {
                                    addIcode(-1);
                                    stackChange(1);
                                    addToken(73);
                                    stackChange(-1);
                                }
                                visitExpression(next9, 0);
                                addToken(74);
                                stackChange(-1);
                                break;
                            case 160:
                                updateLineNumber(node);
                                visitExpression(firstChild, 0);
                                addIcode(-60);
                                stackChange(-1);
                                int i14 = this.iCodeTop;
                                visitExpression(firstChild.getNext(), 0);
                                addBackwardGoto(-61, i14);
                                break;
                            case 173:
                                Node firstChild2 = node.getFirstChild();
                                Node next10 = firstChild2.getNext();
                                visitExpression(firstChild2.getFirstChild(), 0);
                                addToken(2);
                                stackChange(-1);
                                visitExpression(next10.getFirstChild(), 0);
                                addToken(3);
                                break;
                            case 185:
                                visitExpression(firstChild, 0);
                                Node next11 = firstChild.getNext();
                                addIcode(-1);
                                stackChange(1);
                                int i15 = this.iCodeTop;
                                addGotoOp(-84);
                                stackChange(-1);
                                addIcode(-4);
                                visitExpression(next11, 0);
                                stackChange(-1);
                                resolveForwardGoto(i15);
                                break;
                            default:
                                switch (type) {
                                    case 43:
                                        if (node.getIntProp(30, 0) == 1) {
                                            z = true;
                                        } else {
                                            z = false;
                                        }
                                        if (type == 30) {
                                            visitExpression(firstChild, 0);
                                            completeOptionalCallJumpGenerateCallFunAndThis = null;
                                        } else {
                                            completeOptionalCallJumpGenerateCallFunAndThis = generateCallFunAndThis(firstChild, z);
                                            if (completeOptionalCallJumpGenerateCallFunAndThis != null) {
                                                resolveForwardGoto(completeOptionalCallJumpGenerateCallFunAndThis.putArgsAndDoCallLabel);
                                            }
                                        }
                                        i3 = 0;
                                        while (true) {
                                            firstChild = firstChild.getNext();
                                            if (firstChild != null) {
                                                visitExpression(firstChild, 0);
                                                i3++;
                                            } else {
                                                int intProp2 = node.getIntProp(10, 0);
                                                if (type == 76) {
                                                    if (node.getIntProp(31, 0) == 1) {
                                                        addIndexOp(-85, i3);
                                                    } else {
                                                        if (type == 43) {
                                                            type = -62;
                                                        }
                                                        addIndexOp(type, i3);
                                                    }
                                                } else if (node.getIntProp(31, 0) == 1) {
                                                    addIndexOp(-85, i3);
                                                } else {
                                                    if (type == 43) {
                                                        type = -62;
                                                    }
                                                    addIndexOp(type, i3);
                                                }
                                                if (type == 30) {
                                                    stackChange(-i3);
                                                } else {
                                                    stackChange((-1) - i3);
                                                }
                                                if (i3 > this.itsData.itsMaxCalleeArgs) {
                                                    this.itsData.itsMaxCalleeArgs = i3;
                                                }
                                                if (completeOptionalCallJumpGenerateCallFunAndThis != null) {
                                                    resolveForwardGoto(completeOptionalCallJumpGenerateCallFunAndThis.afterLabel);
                                                }
                                            }
                                            break;
                                        }
                                        break;
                                    case 44:
                                    case 46:
                                    case 54:
                                        addStringOp(type, node.getString());
                                        stackChange(1);
                                        break;
                                    case 45:
                                        double d = node.getDouble();
                                        int i16 = (int) d;
                                        if (i16 != d) {
                                            addIndexOp(45, getDoubleIndex(d));
                                        } else if (i16 == 0) {
                                            addIcode(-58);
                                            if (1.0d / d < 0.0d) {
                                                addToken(29);
                                            }
                                        } else if (i16 == 1) {
                                            addIcode(-59);
                                        } else if (((short) i16) == i16) {
                                            addIcode(-32);
                                            addUint16(i16 & 65535);
                                        } else {
                                            addIcode(-33);
                                            addInt(i16);
                                        }
                                        stackChange(1);
                                        break;
                                    case 47:
                                    case 48:
                                    case 49:
                                    case 50:
                                        addToken(type);
                                        stackChange(1);
                                        break;
                                    case 51:
                                    case 52:
                                        visitExpression(firstChild, 0);
                                        visitExpression(firstChild.getNext(), 0);
                                        addToken(type);
                                        stackChange(-1);
                                        break;
                                    case 53:
                                        addIndexOp(53, node.getExistingIntProp(4));
                                        stackChange(1);
                                        break;
                                    default:
                                        switch (type) {
                                            case 57:
                                            case 58:
                                                visitExpression(firstChild, 0);
                                                visitExpression(firstChild.getNext(), 0);
                                                addToken(type);
                                                stackChange(-1);
                                                break;
                                            case 59:
                                                addIndexOp(59, getLocalBlockRef(node));
                                                stackChange(1);
                                                break;
                                            case 60:
                                                if (this.itsData.itsNeedsActivation) {
                                                    Kit.codeBug();
                                                }
                                                addVarOp(60, this.scriptOrFn.getIndexForNameNode(node));
                                                stackChange(1);
                                                break;
                                            case 61:
                                                if (this.itsData.itsNeedsActivation) {
                                                    Kit.codeBug();
                                                }
                                                int indexForNameNode = this.scriptOrFn.getIndexForNameNode(firstChild);
                                                visitExpression(firstChild.getNext(), 0);
                                                addVarOp(61, indexForNameNode);
                                                break;
                                            default:
                                                switch (type) {
                                                    case 67:
                                                    case 68:
                                                        addIndexOp(type, getLocalBlockRef(node));
                                                        stackChange(1);
                                                        break;
                                                    default:
                                                        switch (type) {
                                                            case 71:
                                                            case 72:
                                                                visitLiteral(node, firstChild);
                                                                break;
                                                            case 73:
                                                            case 75:
                                                                visitExpression(firstChild, 0);
                                                                if (node.getIntProp(30, 0) == 1) {
                                                                    addIcode(-1);
                                                                    stackChange(1);
                                                                    int i17 = this.iCodeTop;
                                                                    addGotoOp(-83);
                                                                    stackChange(-1);
                                                                    addToken(type);
                                                                    resolveForwardGoto(i17);
                                                                } else {
                                                                    addToken(type);
                                                                }
                                                                break;
                                                            case 74:
                                                                visitExpression(firstChild, 0);
                                                                Node next12 = firstChild.getNext();
                                                                if (type == 156) {
                                                                    addIcode(-1);
                                                                    stackChange(1);
                                                                    addToken(73);
                                                                    stackChange(-1);
                                                                }
                                                                visitExpression(next12, 0);
                                                                addToken(74);
                                                                stackChange(-1);
                                                                break;
                                                            case 76:
                                                                if (node.getIntProp(30, 0) == 1) {
                                                                    z = true;
                                                                } else {
                                                                    z = false;
                                                                }
                                                                if (type == 30) {
                                                                    visitExpression(firstChild, 0);
                                                                    completeOptionalCallJumpGenerateCallFunAndThis = null;
                                                                } else {
                                                                    completeOptionalCallJumpGenerateCallFunAndThis = generateCallFunAndThis(firstChild, z);
                                                                    if (completeOptionalCallJumpGenerateCallFunAndThis != null) {
                                                                        resolveForwardGoto(completeOptionalCallJumpGenerateCallFunAndThis.putArgsAndDoCallLabel);
                                                                    }
                                                                }
                                                                i3 = 0;
                                                                while (true) {
                                                                    firstChild = firstChild.getNext();
                                                                    if (firstChild != null) {
                                                                        visitExpression(firstChild, 0);
                                                                        i3++;
                                                                    } else {
                                                                        int intProp3 = node.getIntProp(10, 0);
                                                                        if (type == 76) {
                                                                            if (node.getIntProp(31, 0) == 1) {
                                                                                addIndexOp(-85, i3);
                                                                            } else {
                                                                                if (type == 43) {
                                                                                    type = -62;
                                                                                }
                                                                                addIndexOp(type, i3);
                                                                            }
                                                                        } else if (node.getIntProp(31, 0) == 1) {
                                                                            addIndexOp(-85, i3);
                                                                        } else {
                                                                            if (type == 43) {
                                                                                type = -62;
                                                                            }
                                                                            addIndexOp(type, i3);
                                                                        }
                                                                        if (type == 30) {
                                                                            stackChange(-i3);
                                                                        } else {
                                                                            stackChange((-1) - i3);
                                                                        }
                                                                        if (i3 > this.itsData.itsMaxCalleeArgs) {
                                                                            this.itsData.itsMaxCalleeArgs = i3;
                                                                        }
                                                                        if (completeOptionalCallJumpGenerateCallFunAndThis != null) {
                                                                            resolveForwardGoto(completeOptionalCallJumpGenerateCallFunAndThis.afterLabel);
                                                                        }
                                                                    }
                                                                    break;
                                                                }
                                                                break;
                                                            case 77:
                                                                visitExpression(firstChild, 0);
                                                                if (node.getIntProp(30, 0) == 1) {
                                                                    addIcode(-1);
                                                                    stackChange(1);
                                                                    int i18 = this.iCodeTop;
                                                                    addGotoOp(-83);
                                                                    stackChange(-1);
                                                                    addStringOp(type, (String) node.getProp(17));
                                                                    int i19 = this.iCodeTop;
                                                                    addGotoOp(5);
                                                                    resolveForwardGoto(i18);
                                                                    addIcode(-4);
                                                                    addStringOp(44, "undefined");
                                                                    resolveForwardGoto(i19);
                                                                } else {
                                                                    addStringOp(type, (String) node.getProp(17));
                                                                }
                                                                break;
                                                            case 78:
                                                                break;
                                                            case 79:
                                                                break;
                                                            case 80:
                                                                String string3 = firstChild.getString();
                                                                visitExpression(firstChild, 0);
                                                                visitExpression(firstChild.getNext(), 0);
                                                                addStringOp(type, string3);
                                                                stackChange(-1);
                                                                break;
                                                            case 81:
                                                                visitExpression(firstChild, 0);
                                                                visitExpression(firstChild.getNext(), 0);
                                                                addToken(type);
                                                                stackChange(-1);
                                                                break;
                                                            case 82:
                                                            case 83:
                                                            case 84:
                                                                visitExpression(firstChild, 0);
                                                                addToken(type);
                                                                break;
                                                            case 85:
                                                            case 86:
                                                            case 87:
                                                            case 88:
                                                                int intProp4 = node.getIntProp(16, 0);
                                                                int i20 = 0;
                                                                do {
                                                                    visitExpression(firstChild, 0);
                                                                    i20++;
                                                                    firstChild = firstChild.getNext();
                                                                } while (firstChild != null);
                                                                addIndexOp(type, intProp4);
                                                                stackChange(1 - i20);
                                                                break;
                                                            case 89:
                                                                addBigInt(node.getBigInt());
                                                                stackChange(1);
                                                                break;
                                                            default:
                                                                switch (type) {
                                                                    case 117:
                                                                    case 118:
                                                                        visitExpression(firstChild, 0);
                                                                        addIcode(-1);
                                                                        stackChange(1);
                                                                        int i21 = this.iCodeTop;
                                                                        addGotoOp(type == 118 ? 7 : 6);
                                                                        stackChange(-1);
                                                                        addIcode(-4);
                                                                        stackChange(-1);
                                                                        visitExpression(firstChild.getNext(), i & 1);
                                                                        resolveForwardGoto(i21);
                                                                        break;
                                                                    case 119:
                                                                    case 120:
                                                                        visitIncDec(node, firstChild);
                                                                        break;
                                                                    default:
                                                                        switch (type) {
                                                                            case 151:
                                                                                int indexForNameNode2 = (!this.itsInFunctionFlag || this.itsData.itsNeedsActivation) ? -1 : this.scriptOrFn.getIndexForNameNode(node);
                                                                                if (indexForNameNode2 == -1) {
                                                                                    addStringOp(-14, node.getString());
                                                                                    stackChange(1);
                                                                                } else {
                                                                                    addVarOp(60, indexForNameNode2);
                                                                                    stackChange(1);
                                                                                    addToken(32);
                                                                                }
                                                                                break;
                                                                            case 152:
                                                                                stackChange(1);
                                                                                break;
                                                                            case 153:
                                                                                visitExpression(firstChild, 0);
                                                                                Node next13 = firstChild.getNext();
                                                                                string = next13.getString();
                                                                                Node next14 = next13.getNext();
                                                                                if (type == 153) {
                                                                                    addIcode(-1);
                                                                                    stackChange(1);
                                                                                    addStringOp(33, string);
                                                                                    stackChange(-1);
                                                                                }
                                                                                visitExpression(next14, 0);
                                                                                if (node.getIntProp(31, 0) == 1) {
                                                                                    i4 = 38;
                                                                                } else {
                                                                                    i4 = 37;
                                                                                }
                                                                                addStringOp(i4, string);
                                                                                stackChange(-1);
                                                                                break;
                                                                            case 154:
                                                                                visitExpression(firstChild, 0);
                                                                                Node next15 = firstChild.getNext();
                                                                                visitExpression(next15, 0);
                                                                                Node next16 = next15.getNext();
                                                                                if (type == 154) {
                                                                                    addIcode(-2);
                                                                                    stackChange(2);
                                                                                    addToken(39);
                                                                                    stackChange(-1);
                                                                                    stackChange(-1);
                                                                                }
                                                                                visitExpression(next16, 0);
                                                                                if (node.getIntProp(31, 0) == 1) {
                                                                                    i5 = 42;
                                                                                } else {
                                                                                    i5 = 41;
                                                                                }
                                                                                addToken(i5);
                                                                                stackChange(-2);
                                                                                break;
                                                                            default:
                                                                                switch (type) {
                                                                                    case 169:
                                                                                        String string4 = firstChild.getString();
                                                                                        visitExpression(firstChild, 0);
                                                                                        visitExpression(firstChild.getNext(), 0);
                                                                                        addStringOp(-66, string4);
                                                                                        stackChange(-1);
                                                                                        break;
                                                                                    case 170:
                                                                                        if (this.itsData.itsNeedsActivation) {
                                                                                            Kit.codeBug();
                                                                                        }
                                                                                        int indexForNameNode3 = this.scriptOrFn.getIndexForNameNode(firstChild);
                                                                                        visitExpression(firstChild.getNext(), 0);
                                                                                        addVarOp(170, indexForNameNode3);
                                                                                        break;
                                                                                    case 171:
                                                                                        visitArrayComprehension(node, firstChild, firstChild.getNext());
                                                                                        break;
                                                                                    default:
                                                                                        throw badTree(node);
                                                                                }
                                                                                break;
                                                                        }
                                                                        break;
                                                                }
                                                                break;
                                                        }
                                                    case 69:
                                                        addToken(type);
                                                        stackChange(1);
                                                        break;
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
            }
            if (i6 + 1 != this.stackDepth) {
                Kit.codeBug();
            }
        }
        i2 = 65535;
        if (firstChild != null) {
            visitExpression(firstChild, 0);
        } else {
            addIcode(-57);
            stackChange(1);
        }
        if (type == 78) {
            addToken(78);
        } else {
            addIcode(-73);
        }
        addUint16(node.getLineno() & i2);
        if (i6 + 1 != this.stackDepth) {
            Kit.codeBug();
        }
    }

    private void finishGetElemGeneration(Node node) {
        visitExpression(node, 0);
        addToken(39);
        stackChange(-1);
    }

    private CompleteOptionalCallJump generateCallFunAndThis(Node node, boolean z) {
        int type = node.getType();
        if (type != 33 && type != 39) {
            if (type == 44) {
                String string = node.getString();
                if (z) {
                    addStringOp(-19, string);
                    stackChange(2);
                    return completeOptionalCallJump();
                }
                addStringOp(-15, string);
                stackChange(2);
                return null;
            }
            visitExpression(node, 0);
            if (z) {
                addIcode(-22);
                stackChange(1);
                return completeOptionalCallJump();
            }
            addIcode(-18);
            stackChange(1);
            return null;
        }
        Node firstChild = node.getFirstChild();
        visitExpression(firstChild, 0);
        Node next = firstChild.getNext();
        if (type == 33) {
            String string2 = next.getString();
            if (z) {
                addStringOp(-20, string2);
                stackChange(1);
                return completeOptionalCallJump();
            }
            addStringOp(-16, string2);
            stackChange(1);
            return null;
        }
        visitExpression(next, 0);
        if (z) {
            addIcode(-21);
            return completeOptionalCallJump();
        }
        addIcode(-17);
        return null;
    }

    private CompleteOptionalCallJump completeOptionalCallJump() {
        addIcode(-1);
        stackChange(1);
        int i = this.iCodeTop;
        addGotoOp(-84);
        stackChange(-1);
        addIcode(-4);
        addIcode(-4);
        addStringOp(44, "undefined");
        int i2 = this.iCodeTop;
        addGotoOp(5);
        return new CompleteOptionalCallJump(i, i2);
    }

    private void visitIncDec(Node node, Node node2) {
        int existingIntProp = node.getExistingIntProp(13);
        int type = node2.getType();
        if (node2.getIntProp(31, 0) == 1) {
            visitSuperIncDec(node, node2, type, existingIntProp);
            return;
        }
        if (type == 33) {
            Node firstChild = node2.getFirstChild();
            visitExpression(firstChild, 0);
            addStringOp(-9, firstChild.getNext().getString());
            addUint8(existingIntProp);
            return;
        }
        if (type == 39) {
            Node firstChild2 = node2.getFirstChild();
            visitExpression(firstChild2, 0);
            visitExpression(firstChild2.getNext(), 0);
            addIcode(-10);
            addUint8(existingIntProp);
            stackChange(-1);
            return;
        }
        if (type == 44) {
            addStringOp(-8, node2.getString());
            addUint8(existingIntProp);
            stackChange(1);
        } else {
            if (type != 60) {
                if (type == 73) {
                    visitExpression(node2.getFirstChild(), 0);
                    addIcode(-11);
                    addUint8(existingIntProp);
                    return;
                }
                throw badTree(node);
            }
            if (this.itsData.itsNeedsActivation) {
                Kit.codeBug();
            }
            addVarOp(-7, this.scriptOrFn.getIndexForNameNode(node2));
            addUint8(existingIntProp);
            stackChange(1);
        }
    }

    private void visitSuperIncDec(Node node, Node node2, int i, int i2) {
        Node firstChild = node2.getFirstChild();
        visitExpression(firstChild, 0);
        if (i == 33) {
            addStringOp(35, firstChild.getNext().getString());
        } else if (i == 39) {
            visitExpression(firstChild.getNext(), 0);
            addToken(40);
            stackChange(-1);
        } else {
            throw badTree(node);
        }
        int i3 = i2 & 2;
        if (i3 != 0) {
            addIcode(-1);
            stackChange(1);
        }
        addToken(79);
        stackChange(1);
        addIcode(-3);
        addIcode(-59);
        stackChange(1);
        if ((i2 & 1) == 0) {
            addToken(21);
        } else {
            addToken(22);
        }
        stackChange(-1);
        if (i == 33) {
            addStringOp(38, firstChild.getNext().getString());
            stackChange(-1);
        } else if (i == 39) {
            visitExpression(firstChild.getNext(), 0);
            addToken(42);
            stackChange(-2);
        }
        if (i3 != 0) {
            addIcode(-4);
            stackChange(-1);
        }
    }

    private void visitLiteral(Node node, Node node2) {
        int type = node.getType();
        if (type == 71) {
            visitArrayLiteral(node, node2);
        } else {
            if (type == 72) {
                visitObjectLiteral(node, node2);
                return;
            }
            throw badTree(node);
        }
    }

    private void visitObjectLiteral(Node node, Node node2) {
        Object[] objArr = (Object[]) node.getProp(12);
        if (objArr != null) {
            int length = objArr.length;
        }
        int i = (objArr == null || !DesugarArrays.stream(objArr).anyMatch(new Predicate() { // from class: org.mozilla.javascript.CodeGenerator$$ExternalSyntheticLambda0
            public /* synthetic */ Predicate and(Predicate predicate) {
                return Predicate$CC.$default$and(this, predicate);
            }

            public /* synthetic */ Predicate negate() {
                return Predicate$CC.$default$negate(this);
            }

            public /* synthetic */ Predicate or(Predicate predicate) {
                return Predicate$CC.$default$or(this, predicate);
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return CodeGenerator.lambda$visitObjectLiteral$0(obj);
            }
        })) ? 0 : 1;
        int size = this.literalIds.size();
        this.literalIds.add(objArr);
        addIndexOp(-34, size);
        addUint8(i);
        stackChange(4);
        int i2 = 0;
        while (node2 != null) {
            Object obj = objArr == null ? null : objArr[i2];
            if (obj instanceof Node) {
                visitExpression(((Node) obj).first, 0);
                addIcode(-82);
                stackChange(-1);
            }
            visitLiteralValue(node2);
            node2 = node2.getNext();
            i2++;
        }
        addToken(72);
        stackChange(-3);
    }

    static /* synthetic */ boolean lambda$visitObjectLiteral$0(Object obj) {
        return obj instanceof Node;
    }

    private void visitArrayLiteral(Node node, Node node2) {
        int i = 0;
        for (Node next = node2; next != null; next = next.getNext()) {
            i++;
        }
        addIndexOp(-35, i);
        stackChange(2);
        while (node2 != null) {
            visitLiteralValue(node2);
            node2 = node2.getNext();
        }
        int[] iArr = (int[]) node.getProp(11);
        if (iArr == null) {
            addToken(71);
        } else {
            int size = this.literalIds.size();
            this.literalIds.add(iArr);
            addIndexOp(-38, size);
        }
        stackChange(-1);
    }

    private void visitLiteralValue(Node node) {
        int type = node.getType();
        if (type == 165) {
            visitExpression(node.getFirstChild(), 0);
            addIcode(-64);
        } else if (type == 166) {
            visitExpression(node.getFirstChild(), 0);
            addIcode(-65);
        } else if (type == 177) {
            visitExpression(node.getFirstChild(), 0);
            addIcode(-36);
        } else {
            visitExpression(node, 0);
            addIcode(-36);
        }
        stackChange(-1);
    }

    private void visitTemplateLiteral(Node node) {
        addIndexOp(-81, node.getExistingIntProp(27));
        stackChange(1);
    }

    private void visitArrayComprehension(Node node, Node node2, Node node3) {
        visitStatement(node2, this.stackDepth);
        visitExpression(node3, 0);
    }

    private static int getLocalBlockRef(Node node) {
        return ((Node) node.getProp(3)).getExistingIntProp(2);
    }

    private int getTargetLabel(Node node) {
        int iLabelId = node.labelId();
        if (iLabelId != -1) {
            return iLabelId;
        }
        int i = this.labelTableTop;
        int[] iArr = this.labelTable;
        if (iArr == null || i == iArr.length) {
            if (iArr == null) {
                this.labelTable = new int[32];
            } else {
                int[] iArr2 = new int[iArr.length * 2];
                System.arraycopy(iArr, 0, iArr2, 0, i);
                this.labelTable = iArr2;
            }
        }
        this.labelTableTop = i + 1;
        this.labelTable[i] = -1;
        node.labelId(i);
        return i;
    }

    private void markTargetLabel(Node node) {
        int targetLabel = getTargetLabel(node);
        if (this.labelTable[targetLabel] != -1) {
            Kit.codeBug();
        }
        this.labelTable[targetLabel] = this.iCodeTop;
    }

    private void addGoto(Node node, int i) {
        int targetLabel = getTargetLabel(node);
        if (targetLabel >= this.labelTableTop) {
            Kit.codeBug();
        }
        int i2 = this.labelTable[targetLabel];
        if (i2 != -1) {
            addBackwardGoto(i, i2);
            return;
        }
        int i3 = this.iCodeTop;
        addGotoOp(i);
        int i4 = this.fixupTableTop;
        long[] jArr = this.fixupTable;
        if (jArr == null || i4 == jArr.length) {
            if (jArr == null) {
                this.fixupTable = new long[40];
            } else {
                long[] jArr2 = new long[jArr.length * 2];
                System.arraycopy(jArr, 0, jArr2, 0, i4);
                this.fixupTable = jArr2;
            }
        }
        this.fixupTableTop = i4 + 1;
        this.fixupTable[i4] = (((long) targetLabel) << 32) | ((long) i3);
    }

    private void fixLabelGotos() {
        for (int i = 0; i < this.fixupTableTop; i++) {
            long j = this.fixupTable[i];
            int i2 = (int) (j >> 32);
            int i3 = (int) j;
            int i4 = this.labelTable[i2];
            if (i4 == -1) {
                throw Kit.codeBug();
            }
            resolveGoto(i3, i4);
        }
        this.fixupTableTop = 0;
    }

    private void addBackwardGoto(int i, int i2) {
        int i3 = this.iCodeTop;
        if (i3 <= i2) {
            throw Kit.codeBug();
        }
        addGotoOp(i);
        resolveGoto(i3, i2);
    }

    private void resolveForwardGoto(int i) {
        int i2 = this.iCodeTop;
        if (i2 < i + 3) {
            throw Kit.codeBug();
        }
        resolveGoto(i, i2);
    }

    private void resolveGoto(int i, int i2) {
        int i3 = i2 - i;
        if (i3 >= 0 && i3 <= 2) {
            throw Kit.codeBug();
        }
        int i4 = i + 1;
        if (i3 != ((short) i3)) {
            if (this.itsData.longJumps == null) {
                this.itsData.longJumps = new HashMap();
            }
            this.itsData.longJumps.put(Integer.valueOf(i4), Integer.valueOf(i2));
            i3 = 0;
        }
        byte[] bArr = this.itsData.itsICode;
        bArr[i4] = (byte) (i3 >> 8);
        bArr[i + 2] = (byte) i3;
    }

    private void addToken(int i) {
        if (!Icode.validTokenCode(i)) {
            throw Kit.codeBug();
        }
        addUint8(i);
    }

    private void addIcode(int i) {
        if (!Icode.validIcode(i)) {
            throw Kit.codeBug();
        }
        addUint8(i & 255);
    }

    private void addUint8(int i) {
        if ((i & (-256)) != 0) {
            throw Kit.codeBug();
        }
        byte[] bArrIncreaseICodeCapacity = this.itsData.itsICode;
        int i2 = this.iCodeTop;
        if (i2 == bArrIncreaseICodeCapacity.length) {
            bArrIncreaseICodeCapacity = increaseICodeCapacity(1);
        }
        bArrIncreaseICodeCapacity[i2] = (byte) i;
        this.iCodeTop = i2 + 1;
    }

    private void addUint16(int i) {
        if (((-65536) & i) != 0) {
            throw Kit.codeBug();
        }
        byte[] bArrIncreaseICodeCapacity = this.itsData.itsICode;
        int i2 = this.iCodeTop;
        int i3 = i2 + 2;
        if (i3 > bArrIncreaseICodeCapacity.length) {
            bArrIncreaseICodeCapacity = increaseICodeCapacity(2);
        }
        bArrIncreaseICodeCapacity[i2] = (byte) (i >>> 8);
        bArrIncreaseICodeCapacity[i2 + 1] = (byte) i;
        this.iCodeTop = i3;
    }

    private void addInt(int i) {
        byte[] bArrIncreaseICodeCapacity = this.itsData.itsICode;
        int i2 = this.iCodeTop;
        int i3 = i2 + 4;
        if (i3 > bArrIncreaseICodeCapacity.length) {
            bArrIncreaseICodeCapacity = increaseICodeCapacity(4);
        }
        bArrIncreaseICodeCapacity[i2] = (byte) (i >>> 24);
        bArrIncreaseICodeCapacity[i2 + 1] = (byte) (i >>> 16);
        bArrIncreaseICodeCapacity[i2 + 2] = (byte) (i >>> 8);
        bArrIncreaseICodeCapacity[i2 + 3] = (byte) i;
        this.iCodeTop = i3;
    }

    private int getDoubleIndex(double d) {
        int i = this.doubleTableTop;
        InterpreterData interpreterData = this.itsData;
        if (i == 0) {
            interpreterData.itsDoubleTable = new double[64];
        } else if (interpreterData.itsDoubleTable.length == i) {
            double[] dArr = new double[i * 2];
            System.arraycopy(this.itsData.itsDoubleTable, 0, dArr, 0, i);
            this.itsData.itsDoubleTable = dArr;
        }
        this.itsData.itsDoubleTable[i] = d;
        this.doubleTableTop = i + 1;
        return i;
    }

    private void addGotoOp(int i) {
        byte[] bArrIncreaseICodeCapacity = this.itsData.itsICode;
        int i2 = this.iCodeTop;
        if (i2 + 3 > bArrIncreaseICodeCapacity.length) {
            bArrIncreaseICodeCapacity = increaseICodeCapacity(3);
        }
        bArrIncreaseICodeCapacity[i2] = (byte) i;
        this.iCodeTop = i2 + 3;
    }

    private void addVarOp(int i, int i2) {
        if (i != -7) {
            if (i == 170) {
                if (i2 < 128) {
                    addIcode(-68);
                    addUint8(i2);
                    return;
                } else {
                    addIndexOp(-67, i2);
                    return;
                }
            }
            if (i != 60 && i != 61) {
                throw Kit.codeBug();
            }
            if (i2 < 128) {
                addIcode(i == 60 ? -55 : -56);
                addUint8(i2);
                return;
            }
        }
        addIndexOp(i, i2);
    }

    private void addStringOp(int i, String str) {
        addStringPrefix(str);
        if (Icode.validIcode(i)) {
            addIcode(i);
        } else {
            addToken(i);
        }
    }

    private void addIndexOp(int i, int i2) {
        addIndexPrefix(i2);
        if (Icode.validIcode(i)) {
            addIcode(i);
        } else {
            addToken(i);
        }
    }

    private void addStringPrefix(String str) {
        int iIntValue = ((Integer) j$.util.Map.EL.getOrDefault(this.strings, str, -1)).intValue();
        if (iIntValue == -1) {
            iIntValue = this.strings.size();
            this.strings.put(str, Integer.valueOf(iIntValue));
        }
        if (iIntValue < 4) {
            addIcode((-48) - iIntValue);
            return;
        }
        if (iIntValue <= 255) {
            addIcode(-52);
            addUint8(iIntValue);
        } else if (iIntValue <= 65535) {
            addIcode(-53);
            addUint16(iIntValue);
        } else {
            addIcode(-54);
            addInt(iIntValue);
        }
    }

    private void addBigInt(BigInteger bigInteger) {
        int iIntValue = ((Integer) j$.util.Map.EL.getOrDefault(this.bigInts, bigInteger, -1)).intValue();
        if (iIntValue == -1) {
            iIntValue = this.bigInts.size();
            this.bigInts.put(bigInteger, Integer.valueOf(iIntValue));
        }
        if (iIntValue < 4) {
            addIcode((-74) - iIntValue);
        } else if (iIntValue <= 255) {
            addIcode(-78);
            addUint8(iIntValue);
        } else if (iIntValue <= 65535) {
            addIcode(-79);
            addUint16(iIntValue);
        } else {
            addIcode(-80);
            addInt(iIntValue);
        }
        addToken(89);
    }

    private void addIndexPrefix(int i) {
        if (i < 0) {
            Kit.codeBug();
        }
        if (i < 6) {
            addIcode((-39) - i);
            return;
        }
        if (i <= 255) {
            addIcode(-45);
            addUint8(i);
        } else if (i <= 65535) {
            addIcode(-46);
            addUint16(i);
        } else {
            addIcode(-47);
            addInt(i);
        }
    }

    private void addExceptionHandler(int i, int i2, int i3, boolean z, int i4, int i5) {
        int i6 = this.exceptionTableTop;
        int[] iArr = this.itsData.itsExceptionTable;
        if (iArr == null) {
            if (i6 != 0) {
                Kit.codeBug();
            }
            iArr = new int[12];
            this.itsData.itsExceptionTable = iArr;
        } else if (iArr.length == i6) {
            iArr = new int[iArr.length * 2];
            System.arraycopy(this.itsData.itsExceptionTable, 0, iArr, 0, i6);
            this.itsData.itsExceptionTable = iArr;
        }
        iArr[i6] = i;
        iArr[i6 + 1] = i2;
        iArr[i6 + 2] = i3;
        iArr[i6 + 3] = z ? 1 : 0;
        iArr[i6 + 4] = i4;
        iArr[i6 + 5] = i5;
        this.exceptionTableTop = i6 + 6;
    }

    private byte[] increaseICodeCapacity(int i) {
        int length = this.itsData.itsICode.length;
        int i2 = this.iCodeTop;
        int i3 = i + i2;
        if (i3 <= length) {
            throw Kit.codeBug();
        }
        int i4 = length * 2;
        if (i3 <= i4) {
            i3 = i4;
        }
        byte[] bArr = new byte[i3];
        System.arraycopy(this.itsData.itsICode, 0, bArr, 0, i2);
        this.itsData.itsICode = bArr;
        return bArr;
    }

    private void stackChange(int i) {
        int i2 = this.stackDepth;
        if (i <= 0) {
            this.stackDepth = i2 + i;
            return;
        }
        int i3 = i2 + i;
        if (i3 > this.itsData.itsMaxStack) {
            this.itsData.itsMaxStack = i3;
        }
        this.stackDepth = i3;
    }

    private int allocLocal() {
        int i = this.localTop;
        int i2 = i + 1;
        this.localTop = i2;
        if (i2 > this.itsData.itsMaxLocals) {
            this.itsData.itsMaxLocals = this.localTop;
        }
        return i;
    }

    private void releaseLocal(int i) {
        int i2 = this.localTop - 1;
        this.localTop = i2;
        if (i != i2) {
            Kit.codeBug();
        }
    }

    private static final class CompleteOptionalCallJump {
        private final int afterLabel;
        private final int putArgsAndDoCallLabel;

        public CompleteOptionalCallJump(int i, int i2) {
            this.putArgsAndDoCallLabel = i;
            this.afterLabel = i2;
        }
    }
}
