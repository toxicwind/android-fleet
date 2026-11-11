package org.mozilla.javascript;

import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.cast.MediaTrack;

/* JADX INFO: loaded from: classes8.dex */
final class NativeMath extends ScriptableObject {
    private static final Double Double32 = Double.valueOf(32.0d);
    private static final double LOG2E = 1.4426950408889634d;
    private static final String MATH_TAG = "Math";
    private static final long serialVersionUID = -8838847185801131569L;

    static void init(Scriptable scriptable, boolean z) {
        NativeMath nativeMath = new NativeMath();
        nativeMath.setPrototype(getObjectPrototype(scriptable));
        nativeMath.setParentScope(scriptable);
        nativeMath.defineProperty("toSource", MATH_TAG, 7);
        nativeMath.defineProperty(scriptable, "abs", 1, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda0
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.abs(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, "acos", 1, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda2
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.acos(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, "acosh", 1, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda14
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.acosh(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, "asin", 1, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda20
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.asin(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, "asinh", 1, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda21
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.asinh(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, "atan", 1, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda23
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.atan(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, "atanh", 1, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda24
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.atanh(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, "atan2", 2, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda25
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.atan2(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, "cbrt", 1, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda26
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.cbrt(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, "ceil", 1, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda27
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.ceil(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, "clz32", 1, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda11
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.clz32(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, "cos", 1, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda22
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.cos(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, "cosh", 1, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda28
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.cosh(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, "exp", 1, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda29
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.exp(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, "expm1", 1, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda30
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.expm1(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, "floor", 1, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda31
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.floor(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, "fround", 1, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda32
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.fround(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, "hypot", 2, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda33
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.hypot(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, "imul", 2, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda34
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.imul(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, "log", 1, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda1
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.log(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, "log1p", 1, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda3
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.log1p(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, "log10", 1, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda4
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.log10(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, "log2", 1, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda5
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.log2(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, "max", 2, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda6
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.max(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, "min", 2, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda7
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.min(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, "pow", 2, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda8
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.pow(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, "random", 0, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda9
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.random(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, "round", 1, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda10
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.round(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, MediaTrack.ROLE_SIGN, 1, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda12
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.sign(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, "sin", 1, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda13
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.sin(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, "sinh", 1, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda15
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.sinh(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, "sqrt", 1, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda16
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.sqrt(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, "tan", 1, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda17
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.tan(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, "tanh", 1, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda18
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.tanh(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(scriptable, "trunc", 1, new Callable() { // from class: org.mozilla.javascript.NativeMath$$ExternalSyntheticLambda19
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeMath.trunc(context, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeMath.defineProperty(ExifInterface.LONGITUDE_EAST, Double.valueOf(2.718281828459045d), 7);
        nativeMath.defineProperty("PI", Double.valueOf(3.141592653589793d), 7);
        nativeMath.defineProperty("LN10", Double.valueOf(2.302585092994046d), 7);
        nativeMath.defineProperty("LN2", Double.valueOf(0.6931471805599453d), 7);
        nativeMath.defineProperty("LOG2E", Double.valueOf(LOG2E), 7);
        nativeMath.defineProperty("LOG10E", Double.valueOf(0.4342944819032518d), 7);
        nativeMath.defineProperty("SQRT1_2", Double.valueOf(0.7071067811865476d), 7);
        nativeMath.defineProperty("SQRT2", Double.valueOf(1.4142135623730951d), 7);
        nativeMath.defineProperty(SymbolKey.TO_STRING_TAG, MATH_TAG, 3);
        ScriptableObject.defineProperty(scriptable, MATH_TAG, nativeMath, 2);
        if (z) {
            nativeMath.sealObject();
        }
    }

    private NativeMath() {
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return MATH_TAG;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object abs(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        double number = ScriptRuntime.toNumber(objArr, 0);
        if (number == 0.0d) {
            number = 0.0d;
        } else if (number < 0.0d) {
            number = -number;
        }
        return ScriptRuntime.wrapNumber(number);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object acos(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        double number = ScriptRuntime.toNumber(objArr, 0);
        return ScriptRuntime.wrapNumber((Double.isNaN(number) || -1.0d > number || number > 1.0d) ? Double.NaN : Math.acos(number));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object acosh(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        double number = ScriptRuntime.toNumber(objArr, 0);
        if (!Double.isNaN(number)) {
            return Double.valueOf(Math.log(number + Math.sqrt((number * number) - 1.0d)));
        }
        return ScriptRuntime.NaNobj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object asin(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        double number = ScriptRuntime.toNumber(objArr, 0);
        return ScriptRuntime.wrapNumber((Double.isNaN(number) || -1.0d > number || number > 1.0d) ? Double.NaN : Math.asin(number));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object asinh(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        double number = ScriptRuntime.toNumber(objArr, 0);
        if (Double.isInfinite(number)) {
            return Double.valueOf(number);
        }
        if (Double.isNaN(number)) {
            return ScriptRuntime.NaNobj;
        }
        if (number != 0.0d) {
            return Double.valueOf(Math.log(number + Math.sqrt((number * number) + 1.0d)));
        }
        if (1.0d / number > 0.0d) {
            return ScriptRuntime.zeroObj;
        }
        return ScriptRuntime.negativeZeroObj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object atan(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return ScriptRuntime.wrapNumber(Math.atan(ScriptRuntime.toNumber(objArr, 0)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object atanh(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        double number = ScriptRuntime.toNumber(objArr, 0);
        if (Double.isNaN(number) || -1.0d > number || number > 1.0d) {
            return ScriptRuntime.NaNobj;
        }
        if (number != 0.0d) {
            return Double.valueOf(Math.log((number + 1.0d) / (1.0d - number)) * 0.5d);
        }
        if (1.0d / number > 0.0d) {
            return ScriptRuntime.zeroObj;
        }
        return ScriptRuntime.negativeZeroObj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object atan2(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return ScriptRuntime.wrapNumber(Math.atan2(ScriptRuntime.toNumber(objArr, 0), ScriptRuntime.toNumber(objArr, 1)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object cbrt(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return ScriptRuntime.wrapNumber(Math.cbrt(ScriptRuntime.toNumber(objArr, 0)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object ceil(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return ScriptRuntime.wrapNumber(Math.ceil(ScriptRuntime.toNumber(objArr, 0)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object clz32(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        int i = 0;
        double number = ScriptRuntime.toNumber(objArr, 0);
        if (number == 0.0d || Double.isNaN(number) || Double.isInfinite(number)) {
            return Double32;
        }
        long uint32 = ScriptRuntime.toUint32(number);
        if (uint32 == 0) {
            return Double32;
        }
        if (((-65536) & uint32) != 0) {
            i = 16;
            uint32 >>>= 16;
        }
        if ((65280 & uint32) != 0) {
            i += 8;
            uint32 >>>= 8;
        }
        if ((240 & uint32) != 0) {
            i += 4;
            uint32 >>>= 4;
        }
        if ((12 & uint32) != 0) {
            i += 2;
            uint32 >>>= 2;
        }
        if ((2 & uint32) != 0) {
            i++;
            uint32 >>>= 1;
        }
        if ((uint32 & 1) != 0) {
            i++;
        }
        return Double.valueOf(32 - i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object cos(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        double number = ScriptRuntime.toNumber(objArr, 0);
        return ScriptRuntime.wrapNumber(Double.isInfinite(number) ? Double.NaN : Math.cos(number));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object cosh(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return ScriptRuntime.wrapNumber(Math.cosh(ScriptRuntime.toNumber(objArr, 0)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object exp(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        double number = ScriptRuntime.toNumber(objArr, 0);
        if (number != Double.POSITIVE_INFINITY) {
            number = number == Double.NEGATIVE_INFINITY ? 0.0d : Math.exp(number);
        }
        return ScriptRuntime.wrapNumber(number);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object expm1(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return ScriptRuntime.wrapNumber(Math.expm1(ScriptRuntime.toNumber(objArr, 0)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object floor(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return ScriptRuntime.wrapNumber(Math.floor(ScriptRuntime.toNumber(objArr, 0)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object fround(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return ScriptRuntime.wrapNumber((float) ScriptRuntime.toNumber(objArr, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object hypot(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        double d = 0.0d;
        if (objArr == null) {
            return Double.valueOf(0.0d);
        }
        boolean z = false;
        boolean z2 = false;
        for (Object obj : objArr) {
            double number = ScriptRuntime.toNumber(obj);
            if (Double.isNaN(number)) {
                z2 = true;
            } else if (Double.isInfinite(number)) {
                z = true;
            } else {
                d += number * number;
            }
        }
        if (z) {
            return Double.valueOf(Double.POSITIVE_INFINITY);
        }
        if (z2) {
            return Double.valueOf(Double.NaN);
        }
        return Double.valueOf(Math.sqrt(d));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object imul(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (objArr == null) {
            return 0;
        }
        return ScriptRuntime.wrapNumber(ScriptRuntime.toInt32(objArr, 0) * ScriptRuntime.toInt32(objArr, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object log(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        double number = ScriptRuntime.toNumber(objArr, 0);
        return ScriptRuntime.wrapNumber(number < 0.0d ? Double.NaN : Math.log(number));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object log1p(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return ScriptRuntime.wrapNumber(Math.log1p(ScriptRuntime.toNumber(objArr, 0)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object log10(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return ScriptRuntime.wrapNumber(Math.log10(ScriptRuntime.toNumber(objArr, 0)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object log2(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        double number = ScriptRuntime.toNumber(objArr, 0);
        return ScriptRuntime.wrapNumber(number < 0.0d ? Double.NaN : Math.log(number) * LOG2E);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object max(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        double dMax = Double.NEGATIVE_INFINITY;
        for (int i = 0; i != objArr.length; i++) {
            dMax = Math.max(dMax, ScriptRuntime.toNumber(objArr[i]));
        }
        return ScriptRuntime.wrapNumber(dMax);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object min(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        double dMin = Double.POSITIVE_INFINITY;
        for (int i = 0; i != objArr.length; i++) {
            dMin = Math.min(dMin, ScriptRuntime.toNumber(objArr[i]));
        }
        return ScriptRuntime.wrapNumber(dMin);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:39:0x0070  */
    /* JADX WARN: Code duplicated, block: B:52:0x0089 A[PHI: r5
      0x0089: PHI (r5v1 double) = (r5v0 double), (r5v0 double), (r5v0 double), (r5v0 double), (r5v0 double), (r5v0 double), (r5v2 double), (r5v0 double) binds: [B:65:0x00a6, B:55:0x008f, B:43:0x0079, B:45:0x007d, B:37:0x006d, B:22:0x0048, B:24:0x004b, B:12:0x0032] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:67:0x00a9  */
    public static Object pow(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        double number = ScriptRuntime.toNumber(objArr, 0);
        double number2 = ScriptRuntime.toNumber(objArr, 1);
        if (!Double.isNaN(number2)) {
            double d = 0.0d;
            if (number2 == 0.0d) {
                number2 = 1.0d;
            } else {
                double d2 = -0.0d;
                if (number == 0.0d) {
                    if (1.0d / number <= 0.0d) {
                        long j = (long) number2;
                        if (j == number2 && (j & 1) != 0) {
                            if (number2 <= 0.0d) {
                                d2 = Double.NEGATIVE_INFINITY;
                            }
                        } else if (number2 <= 0.0d) {
                            d = Double.POSITIVE_INFINITY;
                        }
                    } else if (number2 <= 0.0d) {
                        number2 = Double.POSITIVE_INFINITY;
                    }
                    number2 = d;
                } else {
                    double dPow = Math.pow(number, number2);
                    if (!Double.isNaN(dPow)) {
                        number2 = dPow;
                    } else if (number2 == Double.POSITIVE_INFINITY) {
                        if (number < -1.0d || 1.0d < number) {
                            number2 = Double.POSITIVE_INFINITY;
                        } else if (-1.0d >= number || number >= 1.0d) {
                            number2 = dPow;
                        } else {
                            number2 = d;
                        }
                    } else if (number2 == Double.NEGATIVE_INFINITY) {
                        if (number < -1.0d || 1.0d < number) {
                            number2 = d;
                        } else if (-1.0d >= number || number >= 1.0d) {
                            number2 = dPow;
                        } else {
                            number2 = Double.POSITIVE_INFINITY;
                        }
                    } else if (number == Double.POSITIVE_INFINITY) {
                        if (number2 > 0.0d) {
                            number2 = Double.POSITIVE_INFINITY;
                        } else {
                            number2 = d;
                        }
                    } else if (number == Double.NEGATIVE_INFINITY) {
                        long j2 = (long) number2;
                        if (j2 == number2 && (j2 & 1) != 0) {
                            number2 = number2 > 0.0d ? Double.NEGATIVE_INFINITY : d2;
                        } else if (number2 > 0.0d) {
                            number2 = Double.POSITIVE_INFINITY;
                        } else {
                            number2 = d;
                        }
                    } else {
                        number2 = dPow;
                    }
                }
            }
        }
        return ScriptRuntime.wrapNumber(number2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object random(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return ScriptRuntime.wrapNumber(Math.random());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object round(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        double number = ScriptRuntime.toNumber(objArr, 0);
        if (!Double.isNaN(number) && !Double.isInfinite(number)) {
            long jRound = Math.round(number);
            if (jRound != 0) {
                number = jRound;
            } else if (number < 0.0d) {
                number = ScriptRuntime.negativeZero;
            } else if (number != 0.0d) {
                number = 0.0d;
            }
        }
        return ScriptRuntime.wrapNumber(number);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object sign(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        double number = ScriptRuntime.toNumber(objArr, 0);
        if (Double.isNaN(number)) {
            return ScriptRuntime.NaNobj;
        }
        if (number != 0.0d) {
            return Double.valueOf(Math.signum(number));
        }
        if (1.0d / number > 0.0d) {
            return ScriptRuntime.zeroObj;
        }
        return ScriptRuntime.negativeZeroObj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object sin(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        double number = ScriptRuntime.toNumber(objArr, 0);
        return ScriptRuntime.wrapNumber(Double.isInfinite(number) ? Double.NaN : Math.sin(number));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object sinh(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return ScriptRuntime.wrapNumber(Math.sinh(ScriptRuntime.toNumber(objArr, 0)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object sqrt(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return ScriptRuntime.wrapNumber(Math.sqrt(ScriptRuntime.toNumber(objArr, 0)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object tan(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return ScriptRuntime.wrapNumber(Math.tan(ScriptRuntime.toNumber(objArr, 0)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object tanh(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return ScriptRuntime.wrapNumber(Math.tanh(ScriptRuntime.toNumber(objArr, 0)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object trunc(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        double number = ScriptRuntime.toNumber(objArr, 0);
        return ScriptRuntime.wrapNumber(number < 0.0d ? Math.ceil(number) : Math.floor(number));
    }
}
