package org.mozilla.javascript.optimizer;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.cast.MediaTrack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.mozilla.classfile.ByteCode;
import org.mozilla.classfile.ClassFileWriter;
import org.mozilla.javascript.CompilerEnvirons;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Evaluator;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.GeneratedClassLoader;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.NativeFunction;
import org.mozilla.javascript.RhinoException;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.SecurityController;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.Name;
import org.mozilla.javascript.ast.ScriptNode;
import org.mozilla.javascript.ast.TemplateCharacters;

/* JADX INFO: loaded from: classes8.dex */
public class Codegen implements Evaluator {
    static final String DEFAULT_MAIN_METHOD_CLASS = "org.mozilla.javascript.optimizer.OptRuntime";
    static final String FUNCTION_CONSTRUCTOR_SIGNATURE = "(Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Context;I)V";
    static final String FUNCTION_INIT_SIGNATURE = "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)V";
    static final String ID_FIELD_NAME = "_id";
    static final String REGEXP_INIT_METHOD_NAME = "_reInit";
    static final String REGEXP_INIT_METHOD_SIGNATURE = "(Lorg/mozilla/javascript/Context;)V";
    private static final String SUPER_CLASS_NAME = "org.mozilla.javascript.NativeFunction";
    static final String TEMPLATE_LITERAL_INIT_METHOD_NAME = "_qInit";
    static final String TEMPLATE_LITERAL_INIT_METHOD_SIGNATURE = "()V";
    private static final Object globalLock = new Object();
    private static int globalSerialClassCounter;
    private CompilerEnvirons compilerEnv;
    private List<OptFunctionNode> directCallTargets;
    private double[] itsConstantList;
    private int itsConstantListSize;
    String mainClassName;
    String mainClassSignature;
    private String mainMethodClass = DEFAULT_MAIN_METHOD_CLASS;
    private HashMap<ScriptNode, Integer> scriptOrFnIndexes;
    ScriptNode[] scriptOrFnNodes;

    @Override // org.mozilla.javascript.Evaluator
    public void captureStackInfo(RhinoException rhinoException) {
        throw new UnsupportedOperationException();
    }

    @Override // org.mozilla.javascript.Evaluator
    public String getSourcePositionFromStack(Context context, int[] iArr) {
        throw new UnsupportedOperationException();
    }

    @Override // org.mozilla.javascript.Evaluator
    public String getPatchedStack(RhinoException rhinoException, String str) {
        throw new UnsupportedOperationException();
    }

    @Override // org.mozilla.javascript.Evaluator
    public List<String> getScriptStack(RhinoException rhinoException) {
        throw new UnsupportedOperationException();
    }

    @Override // org.mozilla.javascript.Evaluator
    public void setEvalScriptFlag(Script script) {
        throw new UnsupportedOperationException();
    }

    @Override // org.mozilla.javascript.Evaluator
    public Object compile(CompilerEnvirons compilerEnvirons, ScriptNode scriptNode, String str, boolean z) {
        int i;
        synchronized (globalLock) {
            i = globalSerialClassCounter + 1;
            globalSerialClassCounter = i;
        }
        String strReplaceAll = "c";
        if (scriptNode.getSourceName().length() > 0) {
            strReplaceAll = scriptNode.getSourceName().replaceAll("\\W", "_");
            if (!Character.isJavaIdentifierStart(strReplaceAll.charAt(0))) {
                strReplaceAll = "_" + strReplaceAll;
            }
        }
        String str2 = "org.mozilla.javascript.gen." + strReplaceAll + "_" + i;
        return new Object[]{str2, compileToClassFile(compilerEnvirons, str2, scriptNode, str, z)};
    }

    @Override // org.mozilla.javascript.Evaluator
    public Script createScriptObject(Object obj, Object obj2) {
        try {
            return (Script) defineClass(obj, obj2).getDeclaredConstructor(null).newInstance(null);
        } catch (Exception e) {
            throw new RuntimeException("Unable to instantiate compiled class:" + e.toString());
        }
    }

    @Override // org.mozilla.javascript.Evaluator
    public Function createFunctionObject(Context context, Scriptable scriptable, Object obj, Object obj2) {
        try {
            return (NativeFunction) defineClass(obj, obj2).getConstructors()[0].newInstance(scriptable, context, 0);
        } catch (Exception e) {
            throw new RuntimeException("Unable to instantiate compiled class:" + e.toString());
        }
    }

    private Class<?> defineClass(Object obj, Object obj2) {
        Object[] objArr = (Object[]) obj;
        String str = (String) objArr[0];
        byte[] bArr = (byte[]) objArr[1];
        GeneratedClassLoader generatedClassLoaderCreateLoader = SecurityController.createLoader(getClass().getClassLoader(), obj2);
        try {
            Class<?> clsDefineClass = generatedClassLoaderCreateLoader.defineClass(str, bArr);
            generatedClassLoaderCreateLoader.linkClass(clsDefineClass);
            return clsDefineClass;
        } catch (IllegalArgumentException | SecurityException e) {
            throw new RuntimeException("Malformed optimizer package " + String.valueOf(e));
        }
    }

    public byte[] compileToClassFile(CompilerEnvirons compilerEnvirons, String str, ScriptNode scriptNode, String str2, boolean z) {
        this.compilerEnv = compilerEnvirons;
        transform(scriptNode);
        if (z) {
            scriptNode = scriptNode.getFunctionNode(0);
        }
        initScriptNodesData(scriptNode);
        this.mainClassName = str;
        this.mainClassSignature = ClassFileWriter.classNameToSignature(str);
        return generateCode(str2);
    }

    private void transform(ScriptNode scriptNode) {
        initOptFunctions_r(scriptNode);
        boolean zIsInterpretedMode = this.compilerEnv.isInterpretedMode();
        HashMap map = null;
        if (!zIsInterpretedMode && scriptNode.getType() == 150) {
            int functionCount = scriptNode.getFunctionCount();
            for (int i = 0; i != functionCount; i++) {
                OptFunctionNode optFunctionNode = OptFunctionNode.get(scriptNode, i);
                if (optFunctionNode.fnode.getFunctionType() == 1) {
                    String name = optFunctionNode.fnode.getName();
                    if (name.length() != 0) {
                        if (map == null) {
                            map = new HashMap();
                        }
                        map.put(name, optFunctionNode);
                    }
                }
            }
        }
        if (map != null) {
            this.directCallTargets = new ArrayList();
        }
        new OptTransformer(map, this.directCallTargets).transform(scriptNode, this.compilerEnv);
        if (zIsInterpretedMode) {
            return;
        }
        new Optimizer().optimize(scriptNode);
    }

    private static void initOptFunctions_r(ScriptNode scriptNode) {
        int functionCount = scriptNode.getFunctionCount();
        for (int i = 0; i != functionCount; i++) {
            FunctionNode functionNode = scriptNode.getFunctionNode(i);
            new OptFunctionNode(functionNode);
            initOptFunctions_r(functionNode);
        }
    }

    private void initScriptNodesData(ScriptNode scriptNode) {
        ArrayList arrayList = new ArrayList();
        collectScriptNodes_r(scriptNode, arrayList);
        int size = arrayList.size();
        ScriptNode[] scriptNodeArr = new ScriptNode[size];
        this.scriptOrFnNodes = scriptNodeArr;
        arrayList.toArray(scriptNodeArr);
        this.scriptOrFnIndexes = new HashMap<>();
        for (int i = 0; i != size; i++) {
            this.scriptOrFnIndexes.put(this.scriptOrFnNodes[i], Integer.valueOf(i));
        }
    }

    private static void collectScriptNodes_r(ScriptNode scriptNode, List<ScriptNode> list) {
        list.add(scriptNode);
        int functionCount = scriptNode.getFunctionCount();
        for (int i = 0; i != functionCount; i++) {
            collectScriptNodes_r(scriptNode.getFunctionNode(i), list);
        }
    }

    private byte[] generateCode(String str) {
        boolean z = true;
        boolean z2 = this.scriptOrFnNodes[0].getType() == 150;
        ScriptNode[] scriptNodeArr = this.scriptOrFnNodes;
        if (scriptNodeArr.length <= 1 && z2) {
            z = false;
        }
        boolean zIsInStrictMode = scriptNodeArr[0].isInStrictMode();
        ClassFileWriter classFileWriter = new ClassFileWriter(this.mainClassName, SUPER_CLASS_NAME, this.scriptOrFnNodes[0].getSourceName());
        classFileWriter.addField(ID_FIELD_NAME, "I", (short) 2);
        if (z) {
            generateFunctionConstructor(classFileWriter);
        }
        if (z2) {
            classFileWriter.addInterface("org/mozilla/javascript/Script");
            generateScriptCtor(classFileWriter);
            generateMain(classFileWriter);
            generateExecute(classFileWriter);
        }
        generateCallMethod(classFileWriter, zIsInStrictMode);
        generateResumeGenerator(classFileWriter);
        generateNativeFunctionOverrides(classFileWriter, str);
        int length = this.scriptOrFnNodes.length;
        for (int i = 0; i != length; i++) {
            ScriptNode scriptNode = this.scriptOrFnNodes[i];
            BodyCodegen bodyCodegen = new BodyCodegen();
            bodyCodegen.cfw = classFileWriter;
            bodyCodegen.codegen = this;
            bodyCodegen.compilerEnv = this.compilerEnv;
            bodyCodegen.scriptOrFn = scriptNode;
            bodyCodegen.scriptOrFnIndex = i;
            bodyCodegen.generateBodyCode();
            if (scriptNode.getType() == 122) {
                OptFunctionNode optFunctionNode = OptFunctionNode.get(scriptNode);
                generateFunctionInit(classFileWriter, optFunctionNode);
                if (optFunctionNode.isTargetOfDirectCall()) {
                    emitDirectConstructor(classFileWriter, optFunctionNode);
                }
            }
        }
        emitRegExpInit(classFileWriter);
        emitTemplateLiteralInit(classFileWriter);
        emitConstantDudeInitializers(classFileWriter);
        return classFileWriter.toByteArray();
    }

    private void emitDirectConstructor(ClassFileWriter classFileWriter, OptFunctionNode optFunctionNode) {
        classFileWriter.startMethod(getDirectCtorName(optFunctionNode.fnode), getBodyMethodSignature(optFunctionNode.fnode), (short) 10);
        int paramCount = optFunctionNode.fnode.getParamCount();
        int i = paramCount * 3;
        int i2 = i + 4;
        int i3 = i + 5;
        classFileWriter.addALoad(0);
        classFileWriter.addALoad(1);
        classFileWriter.addALoad(2);
        classFileWriter.addInvoke(182, "org/mozilla/javascript/BaseFunction", "createObject", "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.addAStore(i3);
        classFileWriter.addALoad(0);
        classFileWriter.addALoad(1);
        classFileWriter.addALoad(2);
        classFileWriter.addALoad(i3);
        for (int i4 = 0; i4 < paramCount; i4++) {
            int i5 = i4 * 3;
            classFileWriter.addALoad(i5 + 4);
            classFileWriter.addDLoad(i5 + 5);
        }
        classFileWriter.addALoad(i2);
        classFileWriter.addInvoke(184, this.mainClassName, getBodyMethodName(optFunctionNode.fnode), getBodyMethodSignature(optFunctionNode.fnode));
        int iAcquireLabel = classFileWriter.acquireLabel();
        classFileWriter.add(89);
        classFileWriter.add(ByteCode.INSTANCEOF, "org/mozilla/javascript/Scriptable");
        classFileWriter.add(153, iAcquireLabel);
        classFileWriter.add(192, "org/mozilla/javascript/Scriptable");
        classFileWriter.add(176);
        classFileWriter.markLabel(iAcquireLabel);
        classFileWriter.addALoad(i3);
        classFileWriter.add(176);
        classFileWriter.stopMethod((short) (i + 6));
    }

    static boolean isGenerator(ScriptNode scriptNode) {
        return scriptNode.getType() == 122 && ((FunctionNode) scriptNode).isGenerator();
    }

    private void generateResumeGenerator(ClassFileWriter classFileWriter) {
        int i = 0;
        boolean z = false;
        for (ScriptNode scriptNode : this.scriptOrFnNodes) {
            if (isGenerator(scriptNode)) {
                z = true;
            }
        }
        if (!z) {
            return;
        }
        classFileWriter.startMethod("resumeGenerator", "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", (short) 17);
        classFileWriter.addALoad(0);
        classFileWriter.addALoad(1);
        classFileWriter.addALoad(2);
        classFileWriter.addALoad(4);
        classFileWriter.addALoad(5);
        classFileWriter.addILoad(3);
        classFileWriter.addLoadThis();
        classFileWriter.add(180, classFileWriter.getClassName(), ID_FIELD_NAME, "I");
        int iAddTableSwitch = classFileWriter.addTableSwitch(0, this.scriptOrFnNodes.length - 1);
        classFileWriter.markTableSwitchDefault(iAddTableSwitch);
        int iAcquireLabel = classFileWriter.acquireLabel();
        while (true) {
            ScriptNode[] scriptNodeArr = this.scriptOrFnNodes;
            if (i < scriptNodeArr.length) {
                ScriptNode scriptNode2 = scriptNodeArr[i];
                classFileWriter.markTableSwitchCase(iAddTableSwitch, i, 6);
                if (isGenerator(scriptNode2)) {
                    classFileWriter.addInvoke(184, this.mainClassName, getBodyMethodName(scriptNode2) + "_gen", "(" + this.mainClassSignature + "Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Ljava/lang/Object;I)Ljava/lang/Object;");
                    classFileWriter.add(176);
                } else {
                    classFileWriter.add(167, iAcquireLabel);
                }
                i++;
            } else {
                classFileWriter.markLabel(iAcquireLabel);
                pushUndefined(classFileWriter);
                classFileWriter.add(176);
                classFileWriter.stopMethod(6);
                return;
            }
        }
    }

    private void generateCallMethod(ClassFileWriter classFileWriter, boolean z) {
        int iAddTableSwitch;
        int paramCount;
        classFileWriter.startMethod(NotificationCompat.CATEGORY_CALL, "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Ljava/lang/Object;", (short) 17);
        int iAcquireLabel = classFileWriter.acquireLabel();
        classFileWriter.addALoad(1);
        classFileWriter.addInvoke(184, "org/mozilla/javascript/ScriptRuntime", "hasTopCall", "(Lorg/mozilla/javascript/Context;)Z");
        classFileWriter.add(154, iAcquireLabel);
        int i = 0;
        classFileWriter.addALoad(0);
        classFileWriter.addALoad(1);
        classFileWriter.addALoad(2);
        classFileWriter.addALoad(3);
        classFileWriter.addALoad(4);
        classFileWriter.addPush(z);
        classFileWriter.addInvoke(184, "org/mozilla/javascript/ScriptRuntime", "doTopCall", "(Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;Z)Ljava/lang/Object;");
        classFileWriter.add(176);
        classFileWriter.markLabel(iAcquireLabel);
        classFileWriter.addALoad(0);
        classFileWriter.addALoad(1);
        classFileWriter.addALoad(2);
        classFileWriter.addALoad(3);
        classFileWriter.addALoad(4);
        int length = this.scriptOrFnNodes.length;
        boolean z2 = 2 <= length;
        if (z2) {
            classFileWriter.addLoadThis();
            classFileWriter.add(180, classFileWriter.getClassName(), ID_FIELD_NAME, "I");
            iAddTableSwitch = classFileWriter.addTableSwitch(1, length - 1);
        } else {
            iAddTableSwitch = 0;
        }
        int i2 = 0;
        int stackTop = 0;
        while (i2 != length) {
            ScriptNode scriptNode = this.scriptOrFnNodes[i2];
            if (z2) {
                if (i2 == 0) {
                    classFileWriter.markTableSwitchDefault(iAddTableSwitch);
                    stackTop = classFileWriter.getStackTop();
                } else {
                    classFileWriter.markTableSwitchCase(iAddTableSwitch, i2 - 1, stackTop);
                }
            }
            if (scriptNode.getType() == 122) {
                OptFunctionNode optFunctionNode = OptFunctionNode.get(scriptNode);
                if (optFunctionNode.isTargetOfDirectCall() && (paramCount = optFunctionNode.fnode.getParamCount()) != 0) {
                    for (int i3 = i; i3 != paramCount; i3++) {
                        classFileWriter.add(ByteCode.ARRAYLENGTH);
                        classFileWriter.addPush(i3);
                        int iAcquireLabel2 = classFileWriter.acquireLabel();
                        int iAcquireLabel3 = classFileWriter.acquireLabel();
                        classFileWriter.add(164, iAcquireLabel2);
                        classFileWriter.addALoad(4);
                        classFileWriter.addPush(i3);
                        classFileWriter.add(50);
                        classFileWriter.add(167, iAcquireLabel3);
                        classFileWriter.markLabel(iAcquireLabel2);
                        pushUndefined(classFileWriter);
                        classFileWriter.markLabel(iAcquireLabel3);
                        classFileWriter.adjustStackTop(-1);
                        classFileWriter.addPush(0.0d);
                        classFileWriter.addALoad(4);
                    }
                }
            }
            classFileWriter.addInvoke(184, this.mainClassName, getBodyMethodName(scriptNode), getBodyMethodSignature(scriptNode));
            classFileWriter.add(176);
            i2++;
            i = 0;
        }
        classFileWriter.stopMethod(5);
    }

    private void generateMain(ClassFileWriter classFileWriter) {
        classFileWriter.startMethod(MediaTrack.ROLE_MAIN, "([Ljava/lang/String;)V", (short) 9);
        classFileWriter.add(187, classFileWriter.getClassName());
        classFileWriter.add(89);
        classFileWriter.addInvoke(183, classFileWriter.getClassName(), "<init>", TEMPLATE_LITERAL_INIT_METHOD_SIGNATURE);
        classFileWriter.add(42);
        classFileWriter.addInvoke(184, this.mainMethodClass, MediaTrack.ROLE_MAIN, "(Lorg/mozilla/javascript/Script;[Ljava/lang/String;)V");
        classFileWriter.add(177);
        classFileWriter.stopMethod(1);
    }

    private static void generateExecute(ClassFileWriter classFileWriter) {
        classFileWriter.startMethod("exec", "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;", (short) 17);
        classFileWriter.addLoadThis();
        classFileWriter.addALoad(1);
        classFileWriter.addALoad(2);
        classFileWriter.add(89);
        classFileWriter.add(1);
        classFileWriter.addInvoke(182, classFileWriter.getClassName(), NotificationCompat.CATEGORY_CALL, "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Ljava/lang/Object;");
        classFileWriter.addALoad(1);
        classFileWriter.addInvoke(182, "org.mozilla.javascript.Context", "processMicrotasks", TEMPLATE_LITERAL_INIT_METHOD_SIGNATURE);
        classFileWriter.add(176);
        classFileWriter.stopMethod(3);
    }

    private static void generateScriptCtor(ClassFileWriter classFileWriter) {
        classFileWriter.startMethod("<init>", TEMPLATE_LITERAL_INIT_METHOD_SIGNATURE, (short) 1);
        classFileWriter.addLoadThis();
        classFileWriter.addInvoke(183, SUPER_CLASS_NAME, "<init>", TEMPLATE_LITERAL_INIT_METHOD_SIGNATURE);
        classFileWriter.addLoadThis();
        classFileWriter.addPush(0);
        classFileWriter.add(181, classFileWriter.getClassName(), ID_FIELD_NAME, "I");
        classFileWriter.add(177);
        classFileWriter.stopMethod(1);
    }

    private void generateFunctionConstructor(ClassFileWriter classFileWriter) {
        int iAddTableSwitch;
        classFileWriter.startMethod("<init>", FUNCTION_CONSTRUCTOR_SIGNATURE, (short) 1);
        int stackTop = 0;
        classFileWriter.addALoad(0);
        classFileWriter.addInvoke(183, SUPER_CLASS_NAME, "<init>", TEMPLATE_LITERAL_INIT_METHOD_SIGNATURE);
        classFileWriter.addLoadThis();
        classFileWriter.addILoad(3);
        classFileWriter.add(181, classFileWriter.getClassName(), ID_FIELD_NAME, "I");
        classFileWriter.addLoadThis();
        classFileWriter.addALoad(2);
        classFileWriter.addALoad(1);
        int i = this.scriptOrFnNodes[0].getType() == 150 ? 1 : 0;
        int length = this.scriptOrFnNodes.length;
        if (i == length) {
            throw badTree();
        }
        boolean z = 2 <= length - i;
        if (z) {
            classFileWriter.addILoad(3);
            iAddTableSwitch = classFileWriter.addTableSwitch(i + 1, length - 1);
        } else {
            iAddTableSwitch = 0;
        }
        for (int i2 = i; i2 != length; i2++) {
            if (z) {
                if (i2 == i) {
                    classFileWriter.markTableSwitchDefault(iAddTableSwitch);
                    stackTop = classFileWriter.getStackTop();
                } else {
                    classFileWriter.markTableSwitchCase(iAddTableSwitch, (i2 - 1) - i, stackTop);
                }
            }
            classFileWriter.addInvoke(183, this.mainClassName, getFunctionInitMethodName(OptFunctionNode.get(this.scriptOrFnNodes[i2])), FUNCTION_INIT_SIGNATURE);
            classFileWriter.add(177);
        }
        classFileWriter.stopMethod(4);
    }

    private void generateFunctionInit(ClassFileWriter classFileWriter, OptFunctionNode optFunctionNode) {
        classFileWriter.startMethod(getFunctionInitMethodName(optFunctionNode), FUNCTION_INIT_SIGNATURE, (short) 18);
        classFileWriter.addLoadThis();
        classFileWriter.addALoad(1);
        classFileWriter.addALoad(2);
        classFileWriter.addInvoke(182, "org/mozilla/javascript/NativeFunction", "initScriptFunction", FUNCTION_INIT_SIGNATURE);
        if (optFunctionNode.fnode.getRegexpCount() != 0) {
            classFileWriter.addALoad(1);
            classFileWriter.addInvoke(184, this.mainClassName, REGEXP_INIT_METHOD_NAME, REGEXP_INIT_METHOD_SIGNATURE);
        }
        if (optFunctionNode.fnode.getTemplateLiteralCount() != 0) {
            classFileWriter.addInvoke(184, this.mainClassName, TEMPLATE_LITERAL_INIT_METHOD_NAME, TEMPLATE_LITERAL_INIT_METHOD_SIGNATURE);
        }
        classFileWriter.add(177);
        classFileWriter.stopMethod(3);
    }

    /* JADX WARN: Code duplicated, block: B:104:0x00b3 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:25:0x007d  */
    /* JADX WARN: Code duplicated, block: B:26:0x0094  */
    /* JADX WARN: Code duplicated, block: B:29:0x0099  */
    /* JADX WARN: Code duplicated, block: B:31:0x009f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:32:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:33:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:38:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:40:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:42:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:43:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:45:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:47:0x00d0  */
    /* JADX WARN: Code duplicated, block: B:49:0x00de  */
    /* JADX WARN: Code duplicated, block: B:51:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:52:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:54:0x00f3  */
    /* JADX WARN: Code duplicated, block: B:56:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:57:0x0104 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:58:0x0106  */
    /* JADX WARN: Code duplicated, block: B:59:0x010f  */
    /* JADX WARN: Code duplicated, block: B:61:0x011b  */
    /* JADX WARN: Code duplicated, block: B:63:0x0121  */
    /* JADX WARN: Code duplicated, block: B:65:0x0126  */
    /* JADX WARN: Code duplicated, block: B:66:0x012a  */
    /* JADX WARN: Code duplicated, block: B:68:0x013b  */
    /* JADX WARN: Code duplicated, block: B:69:0x0158  */
    /* JADX WARN: Code duplicated, block: B:71:0x015e  */
    /* JADX WARN: Code duplicated, block: B:73:0x0169  */
    /* JADX WARN: Code duplicated, block: B:75:0x016c  */
    /* JADX WARN: Code duplicated, block: B:76:0x0177  */
    /* JADX WARN: Code duplicated, block: B:78:0x0183  */
    /* JADX WARN: Code duplicated, block: B:80:0x0189  */
    /* JADX WARN: Code duplicated, block: B:83:0x0192  */
    /* JADX WARN: Code duplicated, block: B:84:0x0196  */
    /* JADX WARN: Code duplicated, block: B:86:0x01aa  */
    /* JADX WARN: Code duplicated, block: B:88:0x01b9  */
    /* JADX WARN: Code duplicated, block: B:90:0x01bf  */
    /* JADX WARN: Code duplicated, block: B:91:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:93:0x01d8  */
    /* JADX WARN: Code duplicated, block: B:95:0x01e2  */
    /* JADX WARN: Code duplicated, block: B:96:0x01e8  */
    private void generateNativeFunctionOverrides(ClassFileWriter classFileWriter, String str) {
        short s;
        int length;
        int iAddTableSwitch;
        int i;
        int stackTop;
        short s2;
        ScriptNode scriptNode;
        short s3;
        int paramAndVarCount;
        short s4;
        int iAddTableSwitch2;
        int i2;
        int paramAndVarCount2;
        boolean[] paramAndVarConst;
        int iAddTableSwitch3;
        int i3;
        boolean z;
        short s5 = 1;
        classFileWriter.startMethod("getLanguageVersion", "()I", (short) 1);
        classFileWriter.addPush(this.compilerEnv.getLanguageVersion());
        int i4 = 172;
        classFileWriter.add(172);
        classFileWriter.stopMethod(1);
        int i5 = 0;
        while (i5 != 9) {
            if (i5 == 4 && str == null) {
                s2 = s5;
            } else {
                int i6 = 3;
                switch (i5) {
                    case 0:
                        classFileWriter.startMethod("getFunctionName", "()Ljava/lang/String;", s5);
                        s = s5;
                        length = this.scriptOrFnNodes.length;
                        if (length > s5) {
                            classFileWriter.addLoadThis();
                            classFileWriter.add(180, classFileWriter.getClassName(), ID_FIELD_NAME, "I");
                            iAddTableSwitch = classFileWriter.addTableSwitch(s5, length - 1);
                        } else {
                            iAddTableSwitch = 0;
                        }
                        i = 0;
                        stackTop = 0;
                        while (i != length) {
                            scriptNode = this.scriptOrFnNodes[i];
                            if (i != 0) {
                                classFileWriter.markTableSwitchCase(iAddTableSwitch, i - 1, stackTop);
                            } else if (length > s5) {
                                classFileWriter.markTableSwitchDefault(iAddTableSwitch);
                                stackTop = classFileWriter.getStackTop();
                            }
                            switch (i5) {
                                case 0:
                                    s3 = s5;
                                    if (scriptNode.getType() == 150) {
                                        classFileWriter.addPush("");
                                    } else {
                                        classFileWriter.addPush(((FunctionNode) scriptNode).getName());
                                    }
                                    classFileWriter.add(176);
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 1:
                                    if (scriptNode.hasRestParameter()) {
                                        s3 = 1;
                                        classFileWriter.addPush(scriptNode.getParamCount() - 1);
                                    } else {
                                        s3 = 1;
                                        classFileWriter.addPush(scriptNode.getParamCount());
                                    }
                                    classFileWriter.add(i4);
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 2:
                                    classFileWriter.addPush(scriptNode.getParamAndVarCount());
                                    i4 = 172;
                                    classFileWriter.add(172);
                                    s3 = 1;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 3:
                                    paramAndVarCount = scriptNode.getParamAndVarCount();
                                    if (paramAndVarCount != 0) {
                                        s4 = 1;
                                        if (paramAndVarCount == 1) {
                                            classFileWriter.addPush(scriptNode.getParamOrVarName(0));
                                            classFileWriter.add(176);
                                        } else {
                                            classFileWriter.addILoad(1);
                                            iAddTableSwitch2 = classFileWriter.addTableSwitch(1, paramAndVarCount - 1);
                                            i2 = 0;
                                            while (i2 != paramAndVarCount) {
                                                if (classFileWriter.getStackTop() != 0) {
                                                    Kit.codeBug();
                                                }
                                                String paramOrVarName = scriptNode.getParamOrVarName(i2);
                                                if (i2 == 0) {
                                                    classFileWriter.markTableSwitchDefault(iAddTableSwitch2);
                                                } else {
                                                    classFileWriter.markTableSwitchCase(iAddTableSwitch2, i2 - 1, 0);
                                                }
                                                classFileWriter.addPush(paramOrVarName);
                                                classFileWriter.add(176);
                                                i2++;
                                                i4 = 172;
                                            }
                                            s3 = 1;
                                            i++;
                                            s5 = s3;
                                            i6 = 3;
                                        }
                                    } else {
                                        s4 = 1;
                                        classFileWriter.add(1);
                                        classFileWriter.add(176);
                                    }
                                    s3 = s4;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 4:
                                    classFileWriter.addPush(scriptNode.getRawSourceStart());
                                    classFileWriter.addPush(scriptNode.getRawSourceEnd());
                                    classFileWriter.addInvoke(182, "java/lang/String", "substring", "(II)Ljava/lang/String;");
                                    classFileWriter.add(176);
                                    s3 = 1;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 5:
                                    paramAndVarCount2 = scriptNode.getParamAndVarCount();
                                    paramAndVarConst = scriptNode.getParamAndVarConst();
                                    if (paramAndVarCount2 == 0) {
                                        classFileWriter.add(i6);
                                        classFileWriter.add(i4);
                                    } else if (paramAndVarCount2 == s5) {
                                        classFileWriter.addPush(paramAndVarConst[0]);
                                        classFileWriter.add(i4);
                                    } else {
                                        classFileWriter.addILoad(s5);
                                        iAddTableSwitch3 = classFileWriter.addTableSwitch(s5, paramAndVarCount2 - 1);
                                        i3 = 0;
                                        while (i3 != paramAndVarCount2) {
                                            if (classFileWriter.getStackTop() != 0) {
                                                Kit.codeBug();
                                            }
                                            if (i3 == 0) {
                                                classFileWriter.markTableSwitchDefault(iAddTableSwitch3);
                                            } else {
                                                classFileWriter.markTableSwitchCase(iAddTableSwitch3, i3 - 1, 0);
                                            }
                                            classFileWriter.addPush(paramAndVarConst[i3]);
                                            classFileWriter.add(i4);
                                            i3++;
                                            s5 = 1;
                                        }
                                    }
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 6:
                                    if (scriptNode instanceof FunctionNode) {
                                        classFileWriter.addPush(((FunctionNode) scriptNode).isES6Generator());
                                    } else {
                                        classFileWriter.addPush(false);
                                    }
                                    classFileWriter.add(i4);
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 7:
                                    classFileWriter.addPush(scriptNode.hasRestParameter());
                                    classFileWriter.add(i4);
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 8:
                                    if (scriptNode instanceof FunctionNode) {
                                        if (scriptNode.getDefaultParams() != null) {
                                            z = s5;
                                        } else {
                                            z = false;
                                        }
                                        classFileWriter.addPush(z);
                                    } else {
                                        classFileWriter.addPush(false);
                                    }
                                    classFileWriter.add(i4);
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                default:
                                    throw Kit.codeBug();
                            }
                        }
                        s2 = s5;
                        classFileWriter.stopMethod(s);
                        break;
                    case 1:
                        classFileWriter.startMethod("getParamCount", "()I", s5);
                        s = s5;
                        length = this.scriptOrFnNodes.length;
                        if (length > s5) {
                            classFileWriter.addLoadThis();
                            classFileWriter.add(180, classFileWriter.getClassName(), ID_FIELD_NAME, "I");
                            iAddTableSwitch = classFileWriter.addTableSwitch(s5, length - 1);
                        } else {
                            iAddTableSwitch = 0;
                        }
                        i = 0;
                        stackTop = 0;
                        while (i != length) {
                            scriptNode = this.scriptOrFnNodes[i];
                            if (i != 0) {
                                classFileWriter.markTableSwitchCase(iAddTableSwitch, i - 1, stackTop);
                            } else if (length > s5) {
                                classFileWriter.markTableSwitchDefault(iAddTableSwitch);
                                stackTop = classFileWriter.getStackTop();
                            }
                            switch (i5) {
                                case 0:
                                    s3 = s5;
                                    if (scriptNode.getType() == 150) {
                                        classFileWriter.addPush("");
                                    } else {
                                        classFileWriter.addPush(((FunctionNode) scriptNode).getName());
                                    }
                                    classFileWriter.add(176);
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 1:
                                    if (scriptNode.hasRestParameter()) {
                                        s3 = 1;
                                        classFileWriter.addPush(scriptNode.getParamCount() - 1);
                                    } else {
                                        s3 = 1;
                                        classFileWriter.addPush(scriptNode.getParamCount());
                                    }
                                    classFileWriter.add(i4);
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 2:
                                    classFileWriter.addPush(scriptNode.getParamAndVarCount());
                                    i4 = 172;
                                    classFileWriter.add(172);
                                    s3 = 1;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 3:
                                    paramAndVarCount = scriptNode.getParamAndVarCount();
                                    if (paramAndVarCount != 0) {
                                        s4 = 1;
                                        if (paramAndVarCount == 1) {
                                            classFileWriter.addPush(scriptNode.getParamOrVarName(0));
                                            classFileWriter.add(176);
                                        } else {
                                            classFileWriter.addILoad(1);
                                            iAddTableSwitch2 = classFileWriter.addTableSwitch(1, paramAndVarCount - 1);
                                            i2 = 0;
                                            while (i2 != paramAndVarCount) {
                                                if (classFileWriter.getStackTop() != 0) {
                                                    Kit.codeBug();
                                                }
                                                String paramOrVarName2 = scriptNode.getParamOrVarName(i2);
                                                if (i2 == 0) {
                                                    classFileWriter.markTableSwitchDefault(iAddTableSwitch2);
                                                } else {
                                                    classFileWriter.markTableSwitchCase(iAddTableSwitch2, i2 - 1, 0);
                                                }
                                                classFileWriter.addPush(paramOrVarName2);
                                                classFileWriter.add(176);
                                                i2++;
                                                i4 = 172;
                                            }
                                            s3 = 1;
                                            i++;
                                            s5 = s3;
                                            i6 = 3;
                                        }
                                    } else {
                                        s4 = 1;
                                        classFileWriter.add(1);
                                        classFileWriter.add(176);
                                    }
                                    s3 = s4;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 4:
                                    classFileWriter.addPush(scriptNode.getRawSourceStart());
                                    classFileWriter.addPush(scriptNode.getRawSourceEnd());
                                    classFileWriter.addInvoke(182, "java/lang/String", "substring", "(II)Ljava/lang/String;");
                                    classFileWriter.add(176);
                                    s3 = 1;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 5:
                                    paramAndVarCount2 = scriptNode.getParamAndVarCount();
                                    paramAndVarConst = scriptNode.getParamAndVarConst();
                                    if (paramAndVarCount2 == 0) {
                                        classFileWriter.add(i6);
                                        classFileWriter.add(i4);
                                    } else if (paramAndVarCount2 == s5) {
                                        classFileWriter.addPush(paramAndVarConst[0]);
                                        classFileWriter.add(i4);
                                    } else {
                                        classFileWriter.addILoad(s5);
                                        iAddTableSwitch3 = classFileWriter.addTableSwitch(s5, paramAndVarCount2 - 1);
                                        i3 = 0;
                                        while (i3 != paramAndVarCount2) {
                                            if (classFileWriter.getStackTop() != 0) {
                                                Kit.codeBug();
                                            }
                                            if (i3 == 0) {
                                                classFileWriter.markTableSwitchDefault(iAddTableSwitch3);
                                            } else {
                                                classFileWriter.markTableSwitchCase(iAddTableSwitch3, i3 - 1, 0);
                                            }
                                            classFileWriter.addPush(paramAndVarConst[i3]);
                                            classFileWriter.add(i4);
                                            i3++;
                                            s5 = 1;
                                        }
                                    }
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 6:
                                    if (scriptNode instanceof FunctionNode) {
                                        classFileWriter.addPush(((FunctionNode) scriptNode).isES6Generator());
                                    } else {
                                        classFileWriter.addPush(false);
                                    }
                                    classFileWriter.add(i4);
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 7:
                                    classFileWriter.addPush(scriptNode.hasRestParameter());
                                    classFileWriter.add(i4);
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 8:
                                    if (scriptNode instanceof FunctionNode) {
                                        if (scriptNode.getDefaultParams() != null) {
                                            z = s5;
                                        } else {
                                            z = false;
                                        }
                                        classFileWriter.addPush(z);
                                    } else {
                                        classFileWriter.addPush(false);
                                    }
                                    classFileWriter.add(i4);
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                default:
                                    throw Kit.codeBug();
                            }
                        }
                        s2 = s5;
                        classFileWriter.stopMethod(s);
                        break;
                    case 2:
                        classFileWriter.startMethod("getParamAndVarCount", "()I", s5);
                        s = s5;
                        length = this.scriptOrFnNodes.length;
                        if (length > s5) {
                            classFileWriter.addLoadThis();
                            classFileWriter.add(180, classFileWriter.getClassName(), ID_FIELD_NAME, "I");
                            iAddTableSwitch = classFileWriter.addTableSwitch(s5, length - 1);
                        } else {
                            iAddTableSwitch = 0;
                        }
                        i = 0;
                        stackTop = 0;
                        while (i != length) {
                            scriptNode = this.scriptOrFnNodes[i];
                            if (i != 0) {
                                classFileWriter.markTableSwitchCase(iAddTableSwitch, i - 1, stackTop);
                            } else if (length > s5) {
                                classFileWriter.markTableSwitchDefault(iAddTableSwitch);
                                stackTop = classFileWriter.getStackTop();
                            }
                            switch (i5) {
                                case 0:
                                    s3 = s5;
                                    if (scriptNode.getType() == 150) {
                                        classFileWriter.addPush("");
                                    } else {
                                        classFileWriter.addPush(((FunctionNode) scriptNode).getName());
                                    }
                                    classFileWriter.add(176);
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 1:
                                    if (scriptNode.hasRestParameter()) {
                                        s3 = 1;
                                        classFileWriter.addPush(scriptNode.getParamCount() - 1);
                                    } else {
                                        s3 = 1;
                                        classFileWriter.addPush(scriptNode.getParamCount());
                                    }
                                    classFileWriter.add(i4);
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 2:
                                    classFileWriter.addPush(scriptNode.getParamAndVarCount());
                                    i4 = 172;
                                    classFileWriter.add(172);
                                    s3 = 1;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 3:
                                    paramAndVarCount = scriptNode.getParamAndVarCount();
                                    if (paramAndVarCount != 0) {
                                        s4 = 1;
                                        if (paramAndVarCount == 1) {
                                            classFileWriter.addPush(scriptNode.getParamOrVarName(0));
                                            classFileWriter.add(176);
                                        } else {
                                            classFileWriter.addILoad(1);
                                            iAddTableSwitch2 = classFileWriter.addTableSwitch(1, paramAndVarCount - 1);
                                            i2 = 0;
                                            while (i2 != paramAndVarCount) {
                                                if (classFileWriter.getStackTop() != 0) {
                                                    Kit.codeBug();
                                                }
                                                String paramOrVarName3 = scriptNode.getParamOrVarName(i2);
                                                if (i2 == 0) {
                                                    classFileWriter.markTableSwitchDefault(iAddTableSwitch2);
                                                } else {
                                                    classFileWriter.markTableSwitchCase(iAddTableSwitch2, i2 - 1, 0);
                                                }
                                                classFileWriter.addPush(paramOrVarName3);
                                                classFileWriter.add(176);
                                                i2++;
                                                i4 = 172;
                                            }
                                            s3 = 1;
                                            i++;
                                            s5 = s3;
                                            i6 = 3;
                                        }
                                    } else {
                                        s4 = 1;
                                        classFileWriter.add(1);
                                        classFileWriter.add(176);
                                    }
                                    s3 = s4;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 4:
                                    classFileWriter.addPush(scriptNode.getRawSourceStart());
                                    classFileWriter.addPush(scriptNode.getRawSourceEnd());
                                    classFileWriter.addInvoke(182, "java/lang/String", "substring", "(II)Ljava/lang/String;");
                                    classFileWriter.add(176);
                                    s3 = 1;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 5:
                                    paramAndVarCount2 = scriptNode.getParamAndVarCount();
                                    paramAndVarConst = scriptNode.getParamAndVarConst();
                                    if (paramAndVarCount2 == 0) {
                                        classFileWriter.add(i6);
                                        classFileWriter.add(i4);
                                    } else if (paramAndVarCount2 == s5) {
                                        classFileWriter.addPush(paramAndVarConst[0]);
                                        classFileWriter.add(i4);
                                    } else {
                                        classFileWriter.addILoad(s5);
                                        iAddTableSwitch3 = classFileWriter.addTableSwitch(s5, paramAndVarCount2 - 1);
                                        i3 = 0;
                                        while (i3 != paramAndVarCount2) {
                                            if (classFileWriter.getStackTop() != 0) {
                                                Kit.codeBug();
                                            }
                                            if (i3 == 0) {
                                                classFileWriter.markTableSwitchDefault(iAddTableSwitch3);
                                            } else {
                                                classFileWriter.markTableSwitchCase(iAddTableSwitch3, i3 - 1, 0);
                                            }
                                            classFileWriter.addPush(paramAndVarConst[i3]);
                                            classFileWriter.add(i4);
                                            i3++;
                                            s5 = 1;
                                        }
                                    }
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 6:
                                    if (scriptNode instanceof FunctionNode) {
                                        classFileWriter.addPush(((FunctionNode) scriptNode).isES6Generator());
                                    } else {
                                        classFileWriter.addPush(false);
                                    }
                                    classFileWriter.add(i4);
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 7:
                                    classFileWriter.addPush(scriptNode.hasRestParameter());
                                    classFileWriter.add(i4);
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 8:
                                    if (scriptNode instanceof FunctionNode) {
                                        if (scriptNode.getDefaultParams() != null) {
                                            z = s5;
                                        } else {
                                            z = false;
                                        }
                                        classFileWriter.addPush(z);
                                    } else {
                                        classFileWriter.addPush(false);
                                    }
                                    classFileWriter.add(i4);
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                default:
                                    throw Kit.codeBug();
                            }
                        }
                        s2 = s5;
                        classFileWriter.stopMethod(s);
                        break;
                    case 3:
                        classFileWriter.startMethod("getParamOrVarName", "(I)Ljava/lang/String;", s5);
                        s = 2;
                        length = this.scriptOrFnNodes.length;
                        if (length > s5) {
                            classFileWriter.addLoadThis();
                            classFileWriter.add(180, classFileWriter.getClassName(), ID_FIELD_NAME, "I");
                            iAddTableSwitch = classFileWriter.addTableSwitch(s5, length - 1);
                        } else {
                            iAddTableSwitch = 0;
                        }
                        i = 0;
                        stackTop = 0;
                        while (i != length) {
                            scriptNode = this.scriptOrFnNodes[i];
                            if (i != 0) {
                                classFileWriter.markTableSwitchCase(iAddTableSwitch, i - 1, stackTop);
                            } else if (length > s5) {
                                classFileWriter.markTableSwitchDefault(iAddTableSwitch);
                                stackTop = classFileWriter.getStackTop();
                            }
                            switch (i5) {
                                case 0:
                                    s3 = s5;
                                    if (scriptNode.getType() == 150) {
                                        classFileWriter.addPush("");
                                    } else {
                                        classFileWriter.addPush(((FunctionNode) scriptNode).getName());
                                    }
                                    classFileWriter.add(176);
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 1:
                                    if (scriptNode.hasRestParameter()) {
                                        s3 = 1;
                                        classFileWriter.addPush(scriptNode.getParamCount() - 1);
                                    } else {
                                        s3 = 1;
                                        classFileWriter.addPush(scriptNode.getParamCount());
                                    }
                                    classFileWriter.add(i4);
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 2:
                                    classFileWriter.addPush(scriptNode.getParamAndVarCount());
                                    i4 = 172;
                                    classFileWriter.add(172);
                                    s3 = 1;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 3:
                                    paramAndVarCount = scriptNode.getParamAndVarCount();
                                    if (paramAndVarCount != 0) {
                                        s4 = 1;
                                        if (paramAndVarCount == 1) {
                                            classFileWriter.addPush(scriptNode.getParamOrVarName(0));
                                            classFileWriter.add(176);
                                        } else {
                                            classFileWriter.addILoad(1);
                                            iAddTableSwitch2 = classFileWriter.addTableSwitch(1, paramAndVarCount - 1);
                                            i2 = 0;
                                            while (i2 != paramAndVarCount) {
                                                if (classFileWriter.getStackTop() != 0) {
                                                    Kit.codeBug();
                                                }
                                                String paramOrVarName4 = scriptNode.getParamOrVarName(i2);
                                                if (i2 == 0) {
                                                    classFileWriter.markTableSwitchDefault(iAddTableSwitch2);
                                                } else {
                                                    classFileWriter.markTableSwitchCase(iAddTableSwitch2, i2 - 1, 0);
                                                }
                                                classFileWriter.addPush(paramOrVarName4);
                                                classFileWriter.add(176);
                                                i2++;
                                                i4 = 172;
                                            }
                                            s3 = 1;
                                            i++;
                                            s5 = s3;
                                            i6 = 3;
                                        }
                                    } else {
                                        s4 = 1;
                                        classFileWriter.add(1);
                                        classFileWriter.add(176);
                                    }
                                    s3 = s4;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 4:
                                    classFileWriter.addPush(scriptNode.getRawSourceStart());
                                    classFileWriter.addPush(scriptNode.getRawSourceEnd());
                                    classFileWriter.addInvoke(182, "java/lang/String", "substring", "(II)Ljava/lang/String;");
                                    classFileWriter.add(176);
                                    s3 = 1;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 5:
                                    paramAndVarCount2 = scriptNode.getParamAndVarCount();
                                    paramAndVarConst = scriptNode.getParamAndVarConst();
                                    if (paramAndVarCount2 == 0) {
                                        classFileWriter.add(i6);
                                        classFileWriter.add(i4);
                                    } else if (paramAndVarCount2 == s5) {
                                        classFileWriter.addPush(paramAndVarConst[0]);
                                        classFileWriter.add(i4);
                                    } else {
                                        classFileWriter.addILoad(s5);
                                        iAddTableSwitch3 = classFileWriter.addTableSwitch(s5, paramAndVarCount2 - 1);
                                        i3 = 0;
                                        while (i3 != paramAndVarCount2) {
                                            if (classFileWriter.getStackTop() != 0) {
                                                Kit.codeBug();
                                            }
                                            if (i3 == 0) {
                                                classFileWriter.markTableSwitchDefault(iAddTableSwitch3);
                                            } else {
                                                classFileWriter.markTableSwitchCase(iAddTableSwitch3, i3 - 1, 0);
                                            }
                                            classFileWriter.addPush(paramAndVarConst[i3]);
                                            classFileWriter.add(i4);
                                            i3++;
                                            s5 = 1;
                                        }
                                    }
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 6:
                                    if (scriptNode instanceof FunctionNode) {
                                        classFileWriter.addPush(((FunctionNode) scriptNode).isES6Generator());
                                    } else {
                                        classFileWriter.addPush(false);
                                    }
                                    classFileWriter.add(i4);
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 7:
                                    classFileWriter.addPush(scriptNode.hasRestParameter());
                                    classFileWriter.add(i4);
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 8:
                                    if (scriptNode instanceof FunctionNode) {
                                        if (scriptNode.getDefaultParams() != null) {
                                            z = s5;
                                        } else {
                                            z = false;
                                        }
                                        classFileWriter.addPush(z);
                                    } else {
                                        classFileWriter.addPush(false);
                                    }
                                    classFileWriter.add(i4);
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                default:
                                    throw Kit.codeBug();
                            }
                        }
                        s2 = s5;
                        classFileWriter.stopMethod(s);
                        break;
                    case 4:
                        classFileWriter.startMethod("getRawSource", "()Ljava/lang/String;", s5);
                        classFileWriter.addPush(str);
                        s = s5;
                        length = this.scriptOrFnNodes.length;
                        if (length > s5) {
                            classFileWriter.addLoadThis();
                            classFileWriter.add(180, classFileWriter.getClassName(), ID_FIELD_NAME, "I");
                            iAddTableSwitch = classFileWriter.addTableSwitch(s5, length - 1);
                        } else {
                            iAddTableSwitch = 0;
                        }
                        i = 0;
                        stackTop = 0;
                        while (i != length) {
                            scriptNode = this.scriptOrFnNodes[i];
                            if (i != 0) {
                                classFileWriter.markTableSwitchCase(iAddTableSwitch, i - 1, stackTop);
                            } else if (length > s5) {
                                classFileWriter.markTableSwitchDefault(iAddTableSwitch);
                                stackTop = classFileWriter.getStackTop();
                            }
                            switch (i5) {
                                case 0:
                                    s3 = s5;
                                    if (scriptNode.getType() == 150) {
                                        classFileWriter.addPush("");
                                    } else {
                                        classFileWriter.addPush(((FunctionNode) scriptNode).getName());
                                    }
                                    classFileWriter.add(176);
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 1:
                                    if (scriptNode.hasRestParameter()) {
                                        s3 = 1;
                                        classFileWriter.addPush(scriptNode.getParamCount() - 1);
                                    } else {
                                        s3 = 1;
                                        classFileWriter.addPush(scriptNode.getParamCount());
                                    }
                                    classFileWriter.add(i4);
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 2:
                                    classFileWriter.addPush(scriptNode.getParamAndVarCount());
                                    i4 = 172;
                                    classFileWriter.add(172);
                                    s3 = 1;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 3:
                                    paramAndVarCount = scriptNode.getParamAndVarCount();
                                    if (paramAndVarCount != 0) {
                                        s4 = 1;
                                        if (paramAndVarCount == 1) {
                                            classFileWriter.addPush(scriptNode.getParamOrVarName(0));
                                            classFileWriter.add(176);
                                        } else {
                                            classFileWriter.addILoad(1);
                                            iAddTableSwitch2 = classFileWriter.addTableSwitch(1, paramAndVarCount - 1);
                                            i2 = 0;
                                            while (i2 != paramAndVarCount) {
                                                if (classFileWriter.getStackTop() != 0) {
                                                    Kit.codeBug();
                                                }
                                                String paramOrVarName5 = scriptNode.getParamOrVarName(i2);
                                                if (i2 == 0) {
                                                    classFileWriter.markTableSwitchDefault(iAddTableSwitch2);
                                                } else {
                                                    classFileWriter.markTableSwitchCase(iAddTableSwitch2, i2 - 1, 0);
                                                }
                                                classFileWriter.addPush(paramOrVarName5);
                                                classFileWriter.add(176);
                                                i2++;
                                                i4 = 172;
                                            }
                                            s3 = 1;
                                            i++;
                                            s5 = s3;
                                            i6 = 3;
                                        }
                                    } else {
                                        s4 = 1;
                                        classFileWriter.add(1);
                                        classFileWriter.add(176);
                                    }
                                    s3 = s4;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 4:
                                    classFileWriter.addPush(scriptNode.getRawSourceStart());
                                    classFileWriter.addPush(scriptNode.getRawSourceEnd());
                                    classFileWriter.addInvoke(182, "java/lang/String", "substring", "(II)Ljava/lang/String;");
                                    classFileWriter.add(176);
                                    s3 = 1;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 5:
                                    paramAndVarCount2 = scriptNode.getParamAndVarCount();
                                    paramAndVarConst = scriptNode.getParamAndVarConst();
                                    if (paramAndVarCount2 == 0) {
                                        classFileWriter.add(i6);
                                        classFileWriter.add(i4);
                                    } else if (paramAndVarCount2 == s5) {
                                        classFileWriter.addPush(paramAndVarConst[0]);
                                        classFileWriter.add(i4);
                                    } else {
                                        classFileWriter.addILoad(s5);
                                        iAddTableSwitch3 = classFileWriter.addTableSwitch(s5, paramAndVarCount2 - 1);
                                        i3 = 0;
                                        while (i3 != paramAndVarCount2) {
                                            if (classFileWriter.getStackTop() != 0) {
                                                Kit.codeBug();
                                            }
                                            if (i3 == 0) {
                                                classFileWriter.markTableSwitchDefault(iAddTableSwitch3);
                                            } else {
                                                classFileWriter.markTableSwitchCase(iAddTableSwitch3, i3 - 1, 0);
                                            }
                                            classFileWriter.addPush(paramAndVarConst[i3]);
                                            classFileWriter.add(i4);
                                            i3++;
                                            s5 = 1;
                                        }
                                    }
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 6:
                                    if (scriptNode instanceof FunctionNode) {
                                        classFileWriter.addPush(((FunctionNode) scriptNode).isES6Generator());
                                    } else {
                                        classFileWriter.addPush(false);
                                    }
                                    classFileWriter.add(i4);
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 7:
                                    classFileWriter.addPush(scriptNode.hasRestParameter());
                                    classFileWriter.add(i4);
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 8:
                                    if (scriptNode instanceof FunctionNode) {
                                        if (scriptNode.getDefaultParams() != null) {
                                            z = s5;
                                        } else {
                                            z = false;
                                        }
                                        classFileWriter.addPush(z);
                                    } else {
                                        classFileWriter.addPush(false);
                                    }
                                    classFileWriter.add(i4);
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                default:
                                    throw Kit.codeBug();
                            }
                        }
                        s2 = s5;
                        classFileWriter.stopMethod(s);
                        break;
                    case 5:
                        classFileWriter.startMethod("getParamOrVarConst", "(I)Z", s5);
                        s = 3;
                        length = this.scriptOrFnNodes.length;
                        if (length > s5) {
                            classFileWriter.addLoadThis();
                            classFileWriter.add(180, classFileWriter.getClassName(), ID_FIELD_NAME, "I");
                            iAddTableSwitch = classFileWriter.addTableSwitch(s5, length - 1);
                        } else {
                            iAddTableSwitch = 0;
                        }
                        i = 0;
                        stackTop = 0;
                        while (i != length) {
                            scriptNode = this.scriptOrFnNodes[i];
                            if (i != 0) {
                                classFileWriter.markTableSwitchCase(iAddTableSwitch, i - 1, stackTop);
                            } else if (length > s5) {
                                classFileWriter.markTableSwitchDefault(iAddTableSwitch);
                                stackTop = classFileWriter.getStackTop();
                            }
                            switch (i5) {
                                case 0:
                                    s3 = s5;
                                    if (scriptNode.getType() == 150) {
                                        classFileWriter.addPush("");
                                    } else {
                                        classFileWriter.addPush(((FunctionNode) scriptNode).getName());
                                    }
                                    classFileWriter.add(176);
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 1:
                                    if (scriptNode.hasRestParameter()) {
                                        s3 = 1;
                                        classFileWriter.addPush(scriptNode.getParamCount() - 1);
                                    } else {
                                        s3 = 1;
                                        classFileWriter.addPush(scriptNode.getParamCount());
                                    }
                                    classFileWriter.add(i4);
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 2:
                                    classFileWriter.addPush(scriptNode.getParamAndVarCount());
                                    i4 = 172;
                                    classFileWriter.add(172);
                                    s3 = 1;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 3:
                                    paramAndVarCount = scriptNode.getParamAndVarCount();
                                    if (paramAndVarCount != 0) {
                                        s4 = 1;
                                        if (paramAndVarCount == 1) {
                                            classFileWriter.addPush(scriptNode.getParamOrVarName(0));
                                            classFileWriter.add(176);
                                        } else {
                                            classFileWriter.addILoad(1);
                                            iAddTableSwitch2 = classFileWriter.addTableSwitch(1, paramAndVarCount - 1);
                                            i2 = 0;
                                            while (i2 != paramAndVarCount) {
                                                if (classFileWriter.getStackTop() != 0) {
                                                    Kit.codeBug();
                                                }
                                                String paramOrVarName6 = scriptNode.getParamOrVarName(i2);
                                                if (i2 == 0) {
                                                    classFileWriter.markTableSwitchDefault(iAddTableSwitch2);
                                                } else {
                                                    classFileWriter.markTableSwitchCase(iAddTableSwitch2, i2 - 1, 0);
                                                }
                                                classFileWriter.addPush(paramOrVarName6);
                                                classFileWriter.add(176);
                                                i2++;
                                                i4 = 172;
                                            }
                                            s3 = 1;
                                            i++;
                                            s5 = s3;
                                            i6 = 3;
                                        }
                                    } else {
                                        s4 = 1;
                                        classFileWriter.add(1);
                                        classFileWriter.add(176);
                                    }
                                    s3 = s4;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 4:
                                    classFileWriter.addPush(scriptNode.getRawSourceStart());
                                    classFileWriter.addPush(scriptNode.getRawSourceEnd());
                                    classFileWriter.addInvoke(182, "java/lang/String", "substring", "(II)Ljava/lang/String;");
                                    classFileWriter.add(176);
                                    s3 = 1;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 5:
                                    paramAndVarCount2 = scriptNode.getParamAndVarCount();
                                    paramAndVarConst = scriptNode.getParamAndVarConst();
                                    if (paramAndVarCount2 == 0) {
                                        classFileWriter.add(i6);
                                        classFileWriter.add(i4);
                                    } else if (paramAndVarCount2 == s5) {
                                        classFileWriter.addPush(paramAndVarConst[0]);
                                        classFileWriter.add(i4);
                                    } else {
                                        classFileWriter.addILoad(s5);
                                        iAddTableSwitch3 = classFileWriter.addTableSwitch(s5, paramAndVarCount2 - 1);
                                        i3 = 0;
                                        while (i3 != paramAndVarCount2) {
                                            if (classFileWriter.getStackTop() != 0) {
                                                Kit.codeBug();
                                            }
                                            if (i3 == 0) {
                                                classFileWriter.markTableSwitchDefault(iAddTableSwitch3);
                                            } else {
                                                classFileWriter.markTableSwitchCase(iAddTableSwitch3, i3 - 1, 0);
                                            }
                                            classFileWriter.addPush(paramAndVarConst[i3]);
                                            classFileWriter.add(i4);
                                            i3++;
                                            s5 = 1;
                                        }
                                    }
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 6:
                                    if (scriptNode instanceof FunctionNode) {
                                        classFileWriter.addPush(((FunctionNode) scriptNode).isES6Generator());
                                    } else {
                                        classFileWriter.addPush(false);
                                    }
                                    classFileWriter.add(i4);
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 7:
                                    classFileWriter.addPush(scriptNode.hasRestParameter());
                                    classFileWriter.add(i4);
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 8:
                                    if (scriptNode instanceof FunctionNode) {
                                        if (scriptNode.getDefaultParams() != null) {
                                            z = s5;
                                        } else {
                                            z = false;
                                        }
                                        classFileWriter.addPush(z);
                                    } else {
                                        classFileWriter.addPush(false);
                                    }
                                    classFileWriter.add(i4);
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                default:
                                    throw Kit.codeBug();
                            }
                        }
                        s2 = s5;
                        classFileWriter.stopMethod(s);
                        break;
                    case 6:
                        classFileWriter.startMethod("isGeneratorFunction", "()Z", (short) 4);
                        s = s5;
                        length = this.scriptOrFnNodes.length;
                        if (length > s5) {
                            classFileWriter.addLoadThis();
                            classFileWriter.add(180, classFileWriter.getClassName(), ID_FIELD_NAME, "I");
                            iAddTableSwitch = classFileWriter.addTableSwitch(s5, length - 1);
                        } else {
                            iAddTableSwitch = 0;
                        }
                        i = 0;
                        stackTop = 0;
                        while (i != length) {
                            scriptNode = this.scriptOrFnNodes[i];
                            if (i != 0) {
                                classFileWriter.markTableSwitchCase(iAddTableSwitch, i - 1, stackTop);
                            } else if (length > s5) {
                                classFileWriter.markTableSwitchDefault(iAddTableSwitch);
                                stackTop = classFileWriter.getStackTop();
                            }
                            switch (i5) {
                                case 0:
                                    s3 = s5;
                                    if (scriptNode.getType() == 150) {
                                        classFileWriter.addPush("");
                                    } else {
                                        classFileWriter.addPush(((FunctionNode) scriptNode).getName());
                                    }
                                    classFileWriter.add(176);
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 1:
                                    if (scriptNode.hasRestParameter()) {
                                        s3 = 1;
                                        classFileWriter.addPush(scriptNode.getParamCount() - 1);
                                    } else {
                                        s3 = 1;
                                        classFileWriter.addPush(scriptNode.getParamCount());
                                    }
                                    classFileWriter.add(i4);
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 2:
                                    classFileWriter.addPush(scriptNode.getParamAndVarCount());
                                    i4 = 172;
                                    classFileWriter.add(172);
                                    s3 = 1;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 3:
                                    paramAndVarCount = scriptNode.getParamAndVarCount();
                                    if (paramAndVarCount != 0) {
                                        s4 = 1;
                                        if (paramAndVarCount == 1) {
                                            classFileWriter.addPush(scriptNode.getParamOrVarName(0));
                                            classFileWriter.add(176);
                                        } else {
                                            classFileWriter.addILoad(1);
                                            iAddTableSwitch2 = classFileWriter.addTableSwitch(1, paramAndVarCount - 1);
                                            i2 = 0;
                                            while (i2 != paramAndVarCount) {
                                                if (classFileWriter.getStackTop() != 0) {
                                                    Kit.codeBug();
                                                }
                                                String paramOrVarName7 = scriptNode.getParamOrVarName(i2);
                                                if (i2 == 0) {
                                                    classFileWriter.markTableSwitchDefault(iAddTableSwitch2);
                                                } else {
                                                    classFileWriter.markTableSwitchCase(iAddTableSwitch2, i2 - 1, 0);
                                                }
                                                classFileWriter.addPush(paramOrVarName7);
                                                classFileWriter.add(176);
                                                i2++;
                                                i4 = 172;
                                            }
                                            s3 = 1;
                                            i++;
                                            s5 = s3;
                                            i6 = 3;
                                        }
                                    } else {
                                        s4 = 1;
                                        classFileWriter.add(1);
                                        classFileWriter.add(176);
                                    }
                                    s3 = s4;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 4:
                                    classFileWriter.addPush(scriptNode.getRawSourceStart());
                                    classFileWriter.addPush(scriptNode.getRawSourceEnd());
                                    classFileWriter.addInvoke(182, "java/lang/String", "substring", "(II)Ljava/lang/String;");
                                    classFileWriter.add(176);
                                    s3 = 1;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 5:
                                    paramAndVarCount2 = scriptNode.getParamAndVarCount();
                                    paramAndVarConst = scriptNode.getParamAndVarConst();
                                    if (paramAndVarCount2 == 0) {
                                        classFileWriter.add(i6);
                                        classFileWriter.add(i4);
                                    } else if (paramAndVarCount2 == s5) {
                                        classFileWriter.addPush(paramAndVarConst[0]);
                                        classFileWriter.add(i4);
                                    } else {
                                        classFileWriter.addILoad(s5);
                                        iAddTableSwitch3 = classFileWriter.addTableSwitch(s5, paramAndVarCount2 - 1);
                                        i3 = 0;
                                        while (i3 != paramAndVarCount2) {
                                            if (classFileWriter.getStackTop() != 0) {
                                                Kit.codeBug();
                                            }
                                            if (i3 == 0) {
                                                classFileWriter.markTableSwitchDefault(iAddTableSwitch3);
                                            } else {
                                                classFileWriter.markTableSwitchCase(iAddTableSwitch3, i3 - 1, 0);
                                            }
                                            classFileWriter.addPush(paramAndVarConst[i3]);
                                            classFileWriter.add(i4);
                                            i3++;
                                            s5 = 1;
                                        }
                                    }
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 6:
                                    if (scriptNode instanceof FunctionNode) {
                                        classFileWriter.addPush(((FunctionNode) scriptNode).isES6Generator());
                                    } else {
                                        classFileWriter.addPush(false);
                                    }
                                    classFileWriter.add(i4);
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 7:
                                    classFileWriter.addPush(scriptNode.hasRestParameter());
                                    classFileWriter.add(i4);
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 8:
                                    if (scriptNode instanceof FunctionNode) {
                                        if (scriptNode.getDefaultParams() != null) {
                                            z = s5;
                                        } else {
                                            z = false;
                                        }
                                        classFileWriter.addPush(z);
                                    } else {
                                        classFileWriter.addPush(false);
                                    }
                                    classFileWriter.add(i4);
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                default:
                                    throw Kit.codeBug();
                            }
                        }
                        s2 = s5;
                        classFileWriter.stopMethod(s);
                        break;
                    case 7:
                        classFileWriter.startMethod("hasRestParameter", "()Z", s5);
                        s = s5;
                        length = this.scriptOrFnNodes.length;
                        if (length > s5) {
                            classFileWriter.addLoadThis();
                            classFileWriter.add(180, classFileWriter.getClassName(), ID_FIELD_NAME, "I");
                            iAddTableSwitch = classFileWriter.addTableSwitch(s5, length - 1);
                        } else {
                            iAddTableSwitch = 0;
                        }
                        i = 0;
                        stackTop = 0;
                        while (i != length) {
                            scriptNode = this.scriptOrFnNodes[i];
                            if (i != 0) {
                                classFileWriter.markTableSwitchCase(iAddTableSwitch, i - 1, stackTop);
                            } else if (length > s5) {
                                classFileWriter.markTableSwitchDefault(iAddTableSwitch);
                                stackTop = classFileWriter.getStackTop();
                            }
                            switch (i5) {
                                case 0:
                                    s3 = s5;
                                    if (scriptNode.getType() == 150) {
                                        classFileWriter.addPush("");
                                    } else {
                                        classFileWriter.addPush(((FunctionNode) scriptNode).getName());
                                    }
                                    classFileWriter.add(176);
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 1:
                                    if (scriptNode.hasRestParameter()) {
                                        s3 = 1;
                                        classFileWriter.addPush(scriptNode.getParamCount() - 1);
                                    } else {
                                        s3 = 1;
                                        classFileWriter.addPush(scriptNode.getParamCount());
                                    }
                                    classFileWriter.add(i4);
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 2:
                                    classFileWriter.addPush(scriptNode.getParamAndVarCount());
                                    i4 = 172;
                                    classFileWriter.add(172);
                                    s3 = 1;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 3:
                                    paramAndVarCount = scriptNode.getParamAndVarCount();
                                    if (paramAndVarCount != 0) {
                                        s4 = 1;
                                        if (paramAndVarCount == 1) {
                                            classFileWriter.addPush(scriptNode.getParamOrVarName(0));
                                            classFileWriter.add(176);
                                        } else {
                                            classFileWriter.addILoad(1);
                                            iAddTableSwitch2 = classFileWriter.addTableSwitch(1, paramAndVarCount - 1);
                                            i2 = 0;
                                            while (i2 != paramAndVarCount) {
                                                if (classFileWriter.getStackTop() != 0) {
                                                    Kit.codeBug();
                                                }
                                                String paramOrVarName8 = scriptNode.getParamOrVarName(i2);
                                                if (i2 == 0) {
                                                    classFileWriter.markTableSwitchDefault(iAddTableSwitch2);
                                                } else {
                                                    classFileWriter.markTableSwitchCase(iAddTableSwitch2, i2 - 1, 0);
                                                }
                                                classFileWriter.addPush(paramOrVarName8);
                                                classFileWriter.add(176);
                                                i2++;
                                                i4 = 172;
                                            }
                                            s3 = 1;
                                            i++;
                                            s5 = s3;
                                            i6 = 3;
                                        }
                                    } else {
                                        s4 = 1;
                                        classFileWriter.add(1);
                                        classFileWriter.add(176);
                                    }
                                    s3 = s4;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 4:
                                    classFileWriter.addPush(scriptNode.getRawSourceStart());
                                    classFileWriter.addPush(scriptNode.getRawSourceEnd());
                                    classFileWriter.addInvoke(182, "java/lang/String", "substring", "(II)Ljava/lang/String;");
                                    classFileWriter.add(176);
                                    s3 = 1;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 5:
                                    paramAndVarCount2 = scriptNode.getParamAndVarCount();
                                    paramAndVarConst = scriptNode.getParamAndVarConst();
                                    if (paramAndVarCount2 == 0) {
                                        classFileWriter.add(i6);
                                        classFileWriter.add(i4);
                                    } else if (paramAndVarCount2 == s5) {
                                        classFileWriter.addPush(paramAndVarConst[0]);
                                        classFileWriter.add(i4);
                                    } else {
                                        classFileWriter.addILoad(s5);
                                        iAddTableSwitch3 = classFileWriter.addTableSwitch(s5, paramAndVarCount2 - 1);
                                        i3 = 0;
                                        while (i3 != paramAndVarCount2) {
                                            if (classFileWriter.getStackTop() != 0) {
                                                Kit.codeBug();
                                            }
                                            if (i3 == 0) {
                                                classFileWriter.markTableSwitchDefault(iAddTableSwitch3);
                                            } else {
                                                classFileWriter.markTableSwitchCase(iAddTableSwitch3, i3 - 1, 0);
                                            }
                                            classFileWriter.addPush(paramAndVarConst[i3]);
                                            classFileWriter.add(i4);
                                            i3++;
                                            s5 = 1;
                                        }
                                    }
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 6:
                                    if (scriptNode instanceof FunctionNode) {
                                        classFileWriter.addPush(((FunctionNode) scriptNode).isES6Generator());
                                    } else {
                                        classFileWriter.addPush(false);
                                    }
                                    classFileWriter.add(i4);
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 7:
                                    classFileWriter.addPush(scriptNode.hasRestParameter());
                                    classFileWriter.add(i4);
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 8:
                                    if (scriptNode instanceof FunctionNode) {
                                        if (scriptNode.getDefaultParams() != null) {
                                            z = s5;
                                        } else {
                                            z = false;
                                        }
                                        classFileWriter.addPush(z);
                                    } else {
                                        classFileWriter.addPush(false);
                                    }
                                    classFileWriter.add(i4);
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                default:
                                    throw Kit.codeBug();
                            }
                        }
                        s2 = s5;
                        classFileWriter.stopMethod(s);
                        break;
                    case 8:
                        classFileWriter.startMethod("hasDefaultParameters", "()Z", s5);
                        s = s5;
                        length = this.scriptOrFnNodes.length;
                        if (length > s5) {
                            classFileWriter.addLoadThis();
                            classFileWriter.add(180, classFileWriter.getClassName(), ID_FIELD_NAME, "I");
                            iAddTableSwitch = classFileWriter.addTableSwitch(s5, length - 1);
                        } else {
                            iAddTableSwitch = 0;
                        }
                        i = 0;
                        stackTop = 0;
                        while (i != length) {
                            scriptNode = this.scriptOrFnNodes[i];
                            if (i != 0) {
                                classFileWriter.markTableSwitchCase(iAddTableSwitch, i - 1, stackTop);
                            } else if (length > s5) {
                                classFileWriter.markTableSwitchDefault(iAddTableSwitch);
                                stackTop = classFileWriter.getStackTop();
                            }
                            switch (i5) {
                                case 0:
                                    s3 = s5;
                                    if (scriptNode.getType() == 150) {
                                        classFileWriter.addPush("");
                                    } else {
                                        classFileWriter.addPush(((FunctionNode) scriptNode).getName());
                                    }
                                    classFileWriter.add(176);
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 1:
                                    if (scriptNode.hasRestParameter()) {
                                        s3 = 1;
                                        classFileWriter.addPush(scriptNode.getParamCount() - 1);
                                    } else {
                                        s3 = 1;
                                        classFileWriter.addPush(scriptNode.getParamCount());
                                    }
                                    classFileWriter.add(i4);
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 2:
                                    classFileWriter.addPush(scriptNode.getParamAndVarCount());
                                    i4 = 172;
                                    classFileWriter.add(172);
                                    s3 = 1;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 3:
                                    paramAndVarCount = scriptNode.getParamAndVarCount();
                                    if (paramAndVarCount != 0) {
                                        s4 = 1;
                                        if (paramAndVarCount == 1) {
                                            classFileWriter.addPush(scriptNode.getParamOrVarName(0));
                                            classFileWriter.add(176);
                                        } else {
                                            classFileWriter.addILoad(1);
                                            iAddTableSwitch2 = classFileWriter.addTableSwitch(1, paramAndVarCount - 1);
                                            i2 = 0;
                                            while (i2 != paramAndVarCount) {
                                                if (classFileWriter.getStackTop() != 0) {
                                                    Kit.codeBug();
                                                }
                                                String paramOrVarName9 = scriptNode.getParamOrVarName(i2);
                                                if (i2 == 0) {
                                                    classFileWriter.markTableSwitchDefault(iAddTableSwitch2);
                                                } else {
                                                    classFileWriter.markTableSwitchCase(iAddTableSwitch2, i2 - 1, 0);
                                                }
                                                classFileWriter.addPush(paramOrVarName9);
                                                classFileWriter.add(176);
                                                i2++;
                                                i4 = 172;
                                            }
                                            s3 = 1;
                                            i++;
                                            s5 = s3;
                                            i6 = 3;
                                        }
                                    } else {
                                        s4 = 1;
                                        classFileWriter.add(1);
                                        classFileWriter.add(176);
                                    }
                                    s3 = s4;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 4:
                                    classFileWriter.addPush(scriptNode.getRawSourceStart());
                                    classFileWriter.addPush(scriptNode.getRawSourceEnd());
                                    classFileWriter.addInvoke(182, "java/lang/String", "substring", "(II)Ljava/lang/String;");
                                    classFileWriter.add(176);
                                    s3 = 1;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 5:
                                    paramAndVarCount2 = scriptNode.getParamAndVarCount();
                                    paramAndVarConst = scriptNode.getParamAndVarConst();
                                    if (paramAndVarCount2 == 0) {
                                        classFileWriter.add(i6);
                                        classFileWriter.add(i4);
                                    } else if (paramAndVarCount2 == s5) {
                                        classFileWriter.addPush(paramAndVarConst[0]);
                                        classFileWriter.add(i4);
                                    } else {
                                        classFileWriter.addILoad(s5);
                                        iAddTableSwitch3 = classFileWriter.addTableSwitch(s5, paramAndVarCount2 - 1);
                                        i3 = 0;
                                        while (i3 != paramAndVarCount2) {
                                            if (classFileWriter.getStackTop() != 0) {
                                                Kit.codeBug();
                                            }
                                            if (i3 == 0) {
                                                classFileWriter.markTableSwitchDefault(iAddTableSwitch3);
                                            } else {
                                                classFileWriter.markTableSwitchCase(iAddTableSwitch3, i3 - 1, 0);
                                            }
                                            classFileWriter.addPush(paramAndVarConst[i3]);
                                            classFileWriter.add(i4);
                                            i3++;
                                            s5 = 1;
                                        }
                                    }
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 6:
                                    if (scriptNode instanceof FunctionNode) {
                                        classFileWriter.addPush(((FunctionNode) scriptNode).isES6Generator());
                                    } else {
                                        classFileWriter.addPush(false);
                                    }
                                    classFileWriter.add(i4);
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 7:
                                    classFileWriter.addPush(scriptNode.hasRestParameter());
                                    classFileWriter.add(i4);
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                case 8:
                                    if (scriptNode instanceof FunctionNode) {
                                        if (scriptNode.getDefaultParams() != null) {
                                            z = s5;
                                        } else {
                                            z = false;
                                        }
                                        classFileWriter.addPush(z);
                                    } else {
                                        classFileWriter.addPush(false);
                                    }
                                    classFileWriter.add(i4);
                                    s3 = s5;
                                    i++;
                                    s5 = s3;
                                    i6 = 3;
                                    break;
                                default:
                                    throw Kit.codeBug();
                            }
                        }
                        s2 = s5;
                        classFileWriter.stopMethod(s);
                        break;
                    default:
                        throw Kit.codeBug();
                }
            }
            i5++;
            s5 = s2;
        }
    }

    private void emitRegExpInit(ClassFileWriter classFileWriter) {
        int i = 0;
        int i2 = 0;
        int regexpCount = 0;
        while (true) {
            ScriptNode[] scriptNodeArr = this.scriptOrFnNodes;
            if (i2 == scriptNodeArr.length) {
                break;
            }
            regexpCount += scriptNodeArr[i2].getRegexpCount();
            i2++;
        }
        if (regexpCount == 0) {
            return;
        }
        short s = 10;
        classFileWriter.startMethod(REGEXP_INIT_METHOD_NAME, REGEXP_INIT_METHOD_SIGNATURE, (short) 10);
        classFileWriter.addField("_reInitDone", "Z", (short) 74);
        classFileWriter.add(178, this.mainClassName, "_reInitDone", "Z");
        int iAcquireLabel = classFileWriter.acquireLabel();
        classFileWriter.add(153, iAcquireLabel);
        classFileWriter.add(177);
        classFileWriter.markLabel(iAcquireLabel);
        classFileWriter.addALoad(0);
        classFileWriter.addInvoke(184, "org/mozilla/javascript/ScriptRuntime", "checkRegExpProxy", "(Lorg/mozilla/javascript/Context;)Lorg/mozilla/javascript/RegExpProxy;");
        classFileWriter.addAStore(1);
        int i3 = 0;
        while (true) {
            ScriptNode[] scriptNodeArr2 = this.scriptOrFnNodes;
            if (i3 != scriptNodeArr2.length) {
                ScriptNode scriptNode = scriptNodeArr2[i3];
                int regexpCount2 = scriptNode.getRegexpCount();
                int i4 = i;
                while (i4 != regexpCount2) {
                    String compiledRegexpName = getCompiledRegexpName(scriptNode, i4);
                    String regexpString = scriptNode.getRegexpString(i4);
                    String regexpFlags = scriptNode.getRegexpFlags(i4);
                    classFileWriter.addField(compiledRegexpName, "Ljava/lang/Object;", s);
                    classFileWriter.addALoad(1);
                    classFileWriter.addALoad(i);
                    classFileWriter.addPush(regexpString);
                    if (regexpFlags == null) {
                        classFileWriter.add(1);
                    } else {
                        classFileWriter.addPush(regexpFlags);
                    }
                    classFileWriter.addInvoke(185, "org/mozilla/javascript/RegExpProxy", "compileRegExp", "(Lorg/mozilla/javascript/Context;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;");
                    classFileWriter.add(179, this.mainClassName, compiledRegexpName, "Ljava/lang/Object;");
                    i4++;
                    i = 0;
                    s = 10;
                }
                i3++;
                i = 0;
                s = 10;
            } else {
                classFileWriter.addPush(1);
                classFileWriter.add(179, this.mainClassName, "_reInitDone", "Z");
                classFileWriter.add(177);
                classFileWriter.stopMethod(2);
                return;
            }
        }
    }

    private void emitTemplateLiteralInit(ClassFileWriter classFileWriter) {
        int templateLiteralCount = 0;
        for (ScriptNode scriptNode : this.scriptOrFnNodes) {
            templateLiteralCount += scriptNode.getTemplateLiteralCount();
        }
        if (templateLiteralCount == 0) {
            return;
        }
        short s = 10;
        classFileWriter.startMethod(TEMPLATE_LITERAL_INIT_METHOD_NAME, TEMPLATE_LITERAL_INIT_METHOD_SIGNATURE, (short) 10);
        classFileWriter.addField("_qInitDone", "Z", (short) 74);
        classFileWriter.add(178, this.mainClassName, "_qInitDone", "Z");
        int iAcquireLabel = classFileWriter.acquireLabel();
        classFileWriter.add(153, iAcquireLabel);
        classFileWriter.add(177);
        classFileWriter.markLabel(iAcquireLabel);
        ScriptNode[] scriptNodeArr = this.scriptOrFnNodes;
        int length = scriptNodeArr.length;
        int i = 0;
        while (i < length) {
            ScriptNode scriptNode2 = scriptNodeArr[i];
            int templateLiteralCount2 = scriptNode2.getTemplateLiteralCount();
            if (templateLiteralCount2 != 0) {
                String templateLiteralName = getTemplateLiteralName(scriptNode2);
                classFileWriter.addField(templateLiteralName, "[Ljava/lang/Object;", s);
                classFileWriter.addPush(templateLiteralCount2);
                int i2 = 189;
                classFileWriter.add(189, "java/lang/Object");
                int i3 = 0;
                while (i3 < templateLiteralCount2) {
                    List<TemplateCharacters> templateLiteralStrings = scriptNode2.getTemplateLiteralStrings(i3);
                    int i4 = 89;
                    classFileWriter.add(89);
                    classFileWriter.addPush(i3);
                    classFileWriter.addPush(templateLiteralStrings.size() * 2);
                    classFileWriter.add(i2, "java/lang/String");
                    int i5 = 0;
                    for (TemplateCharacters templateCharacters : templateLiteralStrings) {
                        classFileWriter.add(i4);
                        int i6 = i5 + 1;
                        classFileWriter.addPush(i5);
                        if (templateCharacters.getValue() != null) {
                            classFileWriter.addPush(templateCharacters.getValue());
                        } else {
                            classFileWriter.add(1);
                        }
                        classFileWriter.add(83);
                        classFileWriter.add(89);
                        i5 += 2;
                        classFileWriter.addPush(i6);
                        classFileWriter.addPush(templateCharacters.getRawValue());
                        classFileWriter.add(83);
                        i4 = 89;
                    }
                    classFileWriter.add(83);
                    i3++;
                    i2 = 189;
                }
                classFileWriter.add(179, this.mainClassName, templateLiteralName, "[Ljava/lang/Object;");
            }
            i++;
            s = 10;
        }
        classFileWriter.addPush(true);
        classFileWriter.add(179, this.mainClassName, "_qInitDone", "Z");
        classFileWriter.add(177);
        classFileWriter.stopMethod(0);
    }

    private void emitConstantDudeInitializers(ClassFileWriter classFileWriter) {
        int i = this.itsConstantListSize;
        if (i == 0) {
            return;
        }
        classFileWriter.startMethod("<clinit>", TEMPLATE_LITERAL_INIT_METHOD_SIGNATURE, (short) 24);
        double[] dArr = this.itsConstantList;
        for (int i2 = 0; i2 != i; i2++) {
            double d = dArr[i2];
            String str = "_k" + i2;
            String staticConstantWrapperType = getStaticConstantWrapperType(d);
            classFileWriter.addField(str, staticConstantWrapperType, (short) 10);
            int i3 = (int) d;
            if (i3 == d) {
                classFileWriter.addPush(i3);
                classFileWriter.addInvoke(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
            } else {
                classFileWriter.addPush(d);
                addDoubleWrap(classFileWriter);
            }
            classFileWriter.add(179, this.mainClassName, str, staticConstantWrapperType);
        }
        classFileWriter.add(177);
        classFileWriter.stopMethod(0);
    }

    void pushNumberAsObject(ClassFileWriter classFileWriter, double d) {
        if (d == 0.0d) {
            if (1.0d / d > 0.0d) {
                classFileWriter.add(178, "org/mozilla/javascript/ScriptRuntime", "zeroObj", "Ljava/lang/Integer;");
                return;
            } else {
                classFileWriter.addPush(d);
                addDoubleWrap(classFileWriter);
                return;
            }
        }
        if (d == 1.0d) {
            classFileWriter.add(178, "org/mozilla/javascript/optimizer/OptRuntime", "oneObj", "Ljava/lang/Integer;");
            return;
        }
        if (d == -1.0d) {
            classFileWriter.add(178, "org/mozilla/javascript/optimizer/OptRuntime", "minusOneObj", "Ljava/lang/Integer;");
            return;
        }
        if (Double.isNaN(d)) {
            classFileWriter.add(178, "org/mozilla/javascript/ScriptRuntime", "NaNobj", "Ljava/lang/Double;");
            return;
        }
        int i = this.itsConstantListSize;
        if (i >= 2000) {
            classFileWriter.addPush(d);
            addDoubleWrap(classFileWriter);
            return;
        }
        int i2 = 0;
        if (i == 0) {
            this.itsConstantList = new double[64];
        } else {
            double[] dArr = this.itsConstantList;
            int i3 = 0;
            while (i3 != i && dArr[i3] != d) {
                i3++;
            }
            if (i == dArr.length) {
                double[] dArr2 = new double[i * 2];
                System.arraycopy(this.itsConstantList, 0, dArr2, 0, i);
                this.itsConstantList = dArr2;
            }
            i2 = i3;
        }
        if (i2 == i) {
            this.itsConstantList[i] = d;
            this.itsConstantListSize = i + 1;
        }
        classFileWriter.add(178, this.mainClassName, "_k" + i2, getStaticConstantWrapperType(d));
    }

    private static void addDoubleWrap(ClassFileWriter classFileWriter) {
        classFileWriter.addInvoke(184, "org/mozilla/javascript/optimizer/OptRuntime", "wrapDouble", "(D)Ljava/lang/Double;");
    }

    private static String getStaticConstantWrapperType(double d) {
        if (((int) d) == d) {
            return "Ljava/lang/Integer;";
        }
        return "Ljava/lang/Double;";
    }

    static void pushUndefined(ClassFileWriter classFileWriter) {
        classFileWriter.add(178, "org/mozilla/javascript/Undefined", "instance", "Ljava/lang/Object;");
    }

    int getIndex(ScriptNode scriptNode) {
        return this.scriptOrFnIndexes.get(scriptNode).intValue();
    }

    String getDirectCtorName(ScriptNode scriptNode) {
        return "_n" + getIndex(scriptNode);
    }

    String getBodyMethodName(ScriptNode scriptNode) {
        return "_c_" + cleanName(scriptNode) + "_" + getIndex(scriptNode);
    }

    String cleanName(ScriptNode scriptNode) {
        if (scriptNode instanceof FunctionNode) {
            Name functionName = ((FunctionNode) scriptNode).getFunctionName();
            if (functionName == null) {
                return "anonymous";
            }
            return functionName.getIdentifier();
        }
        return "script";
    }

    String getBodyMethodSignature(ScriptNode scriptNode) {
        StringBuilder sb = new StringBuilder("(");
        sb.append(this.mainClassSignature);
        sb.append("Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;");
        if (scriptNode.getType() == 122) {
            OptFunctionNode optFunctionNode = OptFunctionNode.get(scriptNode);
            if (optFunctionNode.isTargetOfDirectCall()) {
                int paramCount = optFunctionNode.fnode.getParamCount();
                for (int i = 0; i != paramCount; i++) {
                    sb.append("Ljava/lang/Object;D");
                }
            }
        }
        sb.append("[Ljava/lang/Object;)Ljava/lang/Object;");
        return sb.toString();
    }

    String getFunctionInitMethodName(OptFunctionNode optFunctionNode) {
        return "_i" + getIndex(optFunctionNode.fnode);
    }

    String getCompiledRegexpName(ScriptNode scriptNode, int i) {
        return "_re" + getIndex(scriptNode) + "_" + i;
    }

    String getTemplateLiteralName(ScriptNode scriptNode) {
        return "_q" + getIndex(scriptNode);
    }

    static RuntimeException badTree() {
        throw new RuntimeException("Bad tree in codegen");
    }

    public void setMainMethodClass(String str) {
        this.mainMethodClass = str;
    }
}
