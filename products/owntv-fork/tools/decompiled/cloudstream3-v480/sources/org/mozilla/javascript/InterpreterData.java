package org.mozilla.javascript;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Map;
import org.mozilla.javascript.debug.DebuggableScript;

/* JADX INFO: loaded from: classes8.dex */
final class InterpreterData implements Serializable, DebuggableScript {
    static final int INITIAL_BIGINTTABLE_SIZE = 64;
    static final int INITIAL_MAX_ICODE_LENGTH = 1024;
    static final int INITIAL_NUMBERTABLE_SIZE = 64;
    static final int INITIAL_STRINGTABLE_SIZE = 64;
    private static final long serialVersionUID = 5067677351589230234L;
    int argCount;
    boolean[] argIsConst;
    String[] argNames;
    boolean argsHasDefaults;
    boolean argsHasRest;
    boolean declaredAsFunctionExpression;
    boolean evalScriptFlag;
    int firstLinePC = -1;
    private int icodeHashCode = 0;
    boolean isES6Generator;
    boolean isStrict;
    BigInteger[] itsBigIntTable;
    double[] itsDoubleTable;
    int[] itsExceptionTable;
    int itsFunctionType;
    byte[] itsICode;
    int itsMaxCalleeArgs;
    int itsMaxFrameArray;
    int itsMaxLocals;
    int itsMaxStack;
    int itsMaxVars;
    String itsName;
    boolean itsNeedsActivation;
    InterpreterData[] itsNestedFunctions;
    Object[] itsRegExpLiterals;
    String itsSourceFile;
    String[] itsStringTable;
    Object[] itsTemplateLiterals;
    int languageVersion;
    Object[] literalIds;
    Map<Integer, Integer> longJumps;
    InterpreterData parentData;
    String rawSource;
    int rawSourceEnd;
    int rawSourceStart;
    boolean topLevel;

    InterpreterData(int i, String str, String str2, boolean z) {
        this.languageVersion = i;
        this.itsSourceFile = str;
        this.rawSource = str2;
        this.isStrict = z;
        init();
    }

    InterpreterData(InterpreterData interpreterData) {
        this.parentData = interpreterData;
        this.languageVersion = interpreterData.languageVersion;
        this.itsSourceFile = interpreterData.itsSourceFile;
        this.rawSource = interpreterData.rawSource;
        this.isStrict = interpreterData.isStrict;
        init();
    }

    private void init() {
        this.itsICode = new byte[1024];
        this.itsStringTable = new String[64];
        this.itsBigIntTable = new BigInteger[64];
    }

    @Override // org.mozilla.javascript.debug.DebuggableScript
    public boolean isTopLevel() {
        return this.topLevel;
    }

    @Override // org.mozilla.javascript.debug.DebuggableScript
    public boolean isFunction() {
        return this.itsFunctionType != 0;
    }

    @Override // org.mozilla.javascript.debug.DebuggableScript
    public String getFunctionName() {
        return this.itsName;
    }

    @Override // org.mozilla.javascript.debug.DebuggableScript
    public int getParamCount() {
        return this.argCount;
    }

    @Override // org.mozilla.javascript.debug.DebuggableScript
    public int getParamAndVarCount() {
        return this.argNames.length;
    }

    @Override // org.mozilla.javascript.debug.DebuggableScript
    public String getParamOrVarName(int i) {
        return this.argNames[i];
    }

    public boolean getParamOrVarConst(int i) {
        return this.argIsConst[i];
    }

    @Override // org.mozilla.javascript.debug.DebuggableScript
    public String getSourceName() {
        return this.itsSourceFile;
    }

    @Override // org.mozilla.javascript.debug.DebuggableScript
    public boolean isGeneratedScript() {
        return ScriptRuntime.isGeneratedScript(this.itsSourceFile);
    }

    @Override // org.mozilla.javascript.debug.DebuggableScript
    public int[] getLineNumbers() {
        return Interpreter.getLineNumbers(this);
    }

    @Override // org.mozilla.javascript.debug.DebuggableScript
    public int getFunctionCount() {
        InterpreterData[] interpreterDataArr = this.itsNestedFunctions;
        if (interpreterDataArr == null) {
            return 0;
        }
        return interpreterDataArr.length;
    }

    @Override // org.mozilla.javascript.debug.DebuggableScript
    public DebuggableScript getFunction(int i) {
        return this.itsNestedFunctions[i];
    }

    @Override // org.mozilla.javascript.debug.DebuggableScript
    public DebuggableScript getParent() {
        return this.parentData;
    }

    public int icodeHashCode() {
        int i = this.icodeHashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = Arrays.hashCode(this.itsICode);
        this.icodeHashCode = iHashCode;
        return iHashCode;
    }

    public String toString() {
        return this.itsSourceFile + ":" + this.itsName;
    }
}
