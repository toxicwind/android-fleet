package org.mozilla.javascript.optimizer;

import org.mozilla.javascript.ArrowFunction;
import org.mozilla.javascript.Callable;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextAction;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.ES6Generator;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.JavaScriptException;
import org.mozilla.javascript.NativeFunction;
import org.mozilla.javascript.NativeGenerator;
import org.mozilla.javascript.NativeIterator;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Undefined;

/* JADX INFO: loaded from: classes8.dex */
public final class OptRuntime extends ScriptRuntime {
    public static final Integer oneObj = 1;
    public static final Integer minusOneObj = -1;

    public static Object call0(Callable callable, Scriptable scriptable, Context context, Scriptable scriptable2) {
        return callable.call(context, scriptable2, scriptable, ScriptRuntime.emptyArgs);
    }

    public static Object call0Optional(Callable callable, Scriptable scriptable, Context context, Scriptable scriptable2) {
        if (callable == null) {
            return Undefined.instance;
        }
        return call0(callable, scriptable, context, scriptable2);
    }

    public static Object call1(Callable callable, Scriptable scriptable, Object obj, Context context, Scriptable scriptable2) {
        return callable.call(context, scriptable2, scriptable, new Object[]{obj});
    }

    public static Object call2(Callable callable, Scriptable scriptable, Object obj, Object obj2, Context context, Scriptable scriptable2) {
        return callable.call(context, scriptable2, scriptable, new Object[]{obj, obj2});
    }

    public static Object callN(Callable callable, Scriptable scriptable, Object[] objArr, Context context, Scriptable scriptable2) {
        return callable.call(context, scriptable2, scriptable, objArr);
    }

    public static Object callName(Object[] objArr, String str, Context context, Scriptable scriptable) {
        return getNameFunctionAndThis(str, context, scriptable).call(context, scriptable, lastStoredScriptable(context), objArr);
    }

    public static Object callName0(String str, Context context, Scriptable scriptable) {
        return getNameFunctionAndThis(str, context, scriptable).call(context, scriptable, lastStoredScriptable(context), ScriptRuntime.emptyArgs);
    }

    public static Object callName0Optional(String str, Context context, Scriptable scriptable) {
        Callable nameFunctionAndThisOptional = getNameFunctionAndThisOptional(str, context, scriptable);
        if (nameFunctionAndThisOptional == null) {
            return Undefined.instance;
        }
        return nameFunctionAndThisOptional.call(context, scriptable, lastStoredScriptable(context), ScriptRuntime.emptyArgs);
    }

    public static Object callProp0(Object obj, String str, Context context, Scriptable scriptable) {
        return getPropFunctionAndThis(obj, str, context, scriptable).call(context, scriptable, lastStoredScriptable(context), ScriptRuntime.emptyArgs);
    }

    public static Object callProp0Optional(Object obj, String str, Context context, Scriptable scriptable) {
        Callable propFunctionAndThisOptional = getPropFunctionAndThisOptional(obj, str, context, scriptable);
        if (propFunctionAndThisOptional == null) {
            return Undefined.instance;
        }
        return propFunctionAndThisOptional.call(context, scriptable, lastStoredScriptable(context), ScriptRuntime.emptyArgs);
    }

    public static Object add(Object obj, double d, Context context) {
        if (obj instanceof Double) {
            return Double.valueOf(((Double) obj).doubleValue() + d);
        }
        if (obj instanceof Integer) {
            return Double.valueOf(((double) ((Integer) obj).intValue()) + d);
        }
        return ScriptRuntime.add(obj, Double.valueOf(d), context);
    }

    public static Object add(double d, Object obj, Context context) {
        if (obj instanceof Double) {
            return Double.valueOf(d + ((Double) obj).doubleValue());
        }
        if (obj instanceof Integer) {
            return Double.valueOf(d + ((double) ((Integer) obj).intValue()));
        }
        return ScriptRuntime.add(Double.valueOf(d), obj, context);
    }

    @Deprecated
    public static Object elemIncrDecr(Object obj, double d, Context context, int i) {
        return elemIncrDecr(obj, d, context, getTopCallScope(context), i);
    }

    public static Object elemIncrDecr(Object obj, double d, Context context, Scriptable scriptable, int i) {
        return ScriptRuntime.elemIncrDecr(obj, Double.valueOf(d), context, scriptable, i);
    }

    public static Object[] padStart(Object[] objArr, int i) {
        Object[] objArr2 = new Object[objArr.length + i];
        System.arraycopy(objArr, 0, objArr2, i, objArr.length);
        return objArr2;
    }

    public static void initFunction(NativeFunction nativeFunction, int i, Scriptable scriptable, Context context) {
        ScriptRuntime.initFunction(context, scriptable, nativeFunction, i, false);
    }

    public static Function bindThis(NativeFunction nativeFunction, Context context, Scriptable scriptable, Scriptable scriptable2) {
        return new ArrowFunction(context, scriptable, nativeFunction, scriptable2, null);
    }

    public static Object callSpecial(Context context, Callable callable, Scriptable scriptable, Object[] objArr, Scriptable scriptable2, Scriptable scriptable3, int i, String str, int i2, boolean z) {
        return ScriptRuntime.callSpecial(context, callable, scriptable, objArr, scriptable2, scriptable3, i, str, i2, z);
    }

    public static Object newObjectSpecial(Context context, Object obj, Object[] objArr, Scriptable scriptable, Scriptable scriptable2, int i) {
        return ScriptRuntime.newSpecial(context, obj, objArr, scriptable, i);
    }

    public static Double wrapDouble(double d) {
        if (Double.isNaN(d)) {
            return NaNobj;
        }
        return Double.valueOf(d);
    }

    static String encodeIntArray(int[] iArr) {
        if (iArr == null) {
            return null;
        }
        int length = iArr.length;
        char[] cArr = new char[(length * 2) + 1];
        cArr[0] = 1;
        for (int i = 0; i != length; i++) {
            int i2 = iArr[i];
            int i3 = i * 2;
            cArr[i3 + 1] = (char) (i2 >>> 16);
            cArr[i3 + 2] = (char) i2;
        }
        return new String(cArr);
    }

    private static int[] decodeIntArray(String str, int i) {
        if (i == 0) {
            if (str == null) {
                return null;
            }
            throw new IllegalArgumentException();
        }
        if (str.length() != (i * 2) + 1 && str.charAt(0) != 1) {
            throw new IllegalArgumentException();
        }
        int[] iArr = new int[i];
        for (int i2 = 0; i2 != i; i2++) {
            int i3 = i2 * 2;
            iArr[i2] = str.charAt(i3 + 2) | (str.charAt(i3 + 1) << 16);
        }
        return iArr;
    }

    public static Scriptable newArrayLiteral(Object[] objArr, String str, int i, Context context, Scriptable scriptable) {
        return newArrayLiteral(objArr, decodeIntArray(str, i), context, scriptable);
    }

    public static void main(final Script script, final String[] strArr) {
        ContextFactory.getGlobal().call(new ContextAction() { // from class: org.mozilla.javascript.optimizer.OptRuntime$$ExternalSyntheticLambda0
            @Override // org.mozilla.javascript.ContextAction
            public final Object run(Context context) {
                return OptRuntime.lambda$main$0(strArr, script, context);
            }
        });
    }

    static /* synthetic */ Object lambda$main$0(String[] strArr, Script script, Context context) {
        ScriptableObject global = getGlobal(context);
        Object[] objArr = new Object[strArr.length];
        System.arraycopy(strArr, 0, objArr, 0, strArr.length);
        global.defineProperty("arguments", context.newArray(global, objArr), 2);
        script.exec(context, global);
        return null;
    }

    public static void throwStopIteration(Object obj, Object obj2) {
        Object stopIteration;
        Object generatorReturnValue = getGeneratorReturnValue(obj2);
        if (generatorReturnValue == Undefined.instance) {
            stopIteration = NativeIterator.getStopIterationObject((Scriptable) obj);
        } else {
            stopIteration = new NativeIterator.StopIteration(generatorReturnValue);
        }
        throw new JavaScriptException(stopIteration, "", 0);
    }

    public static Scriptable createNativeGenerator(NativeFunction nativeFunction, Scriptable scriptable, Scriptable scriptable2, int i, int i2) {
        GeneratorState generatorState = new GeneratorState(scriptable2, i, i2);
        if (Context.getCurrentContext().getLanguageVersion() >= 200) {
            return new ES6Generator(scriptable, nativeFunction, generatorState);
        }
        return new NativeGenerator(scriptable, nativeFunction, generatorState);
    }

    public static Object[] getGeneratorStackState(Object obj) {
        GeneratorState generatorState = (GeneratorState) obj;
        if (generatorState.stackState == null) {
            generatorState.stackState = new Object[generatorState.maxStack];
        }
        return generatorState.stackState;
    }

    public static Object[] getGeneratorLocalsState(Object obj) {
        GeneratorState generatorState = (GeneratorState) obj;
        if (generatorState.localsState == null) {
            generatorState.localsState = new Object[generatorState.maxLocals];
        }
        return generatorState.localsState;
    }

    public static void setGeneratorReturnValue(Object obj, Object obj2) {
        ((GeneratorState) obj).returnValue = obj2;
    }

    public static Object getGeneratorReturnValue(Object obj) {
        GeneratorState generatorState = (GeneratorState) obj;
        return generatorState.returnValue == null ? Undefined.instance : generatorState.returnValue;
    }

    public static boolean isNullOrUndefined(Object obj) {
        return obj == null || Undefined.isUndefined(obj);
    }

    public static class GeneratorState {
        static final String CLASS_NAME = "org/mozilla/javascript/optimizer/OptRuntime$GeneratorState";
        static final String resumptionPoint_NAME = "resumptionPoint";
        static final String resumptionPoint_TYPE = "I";
        static final String thisObj_NAME = "thisObj";
        static final String thisObj_TYPE = "Lorg/mozilla/javascript/Scriptable;";
        Object[] localsState;
        int maxLocals;
        int maxStack;
        public int resumptionPoint;
        Object returnValue;
        Object[] stackState;
        public Scriptable thisObj;

        GeneratorState(Scriptable scriptable, int i, int i2) {
            this.thisObj = scriptable;
            this.maxLocals = i;
            this.maxStack = i2;
        }
    }
}
