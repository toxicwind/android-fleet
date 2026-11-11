package org.mozilla.javascript;

import androidx.collection.SieveCacheKt;
import java.io.Serializable;
import java.util.Comparator;

/* JADX INFO: loaded from: classes8.dex */
public class ArrayLikeAbstractOperations {
    private static final Comparator<Object> STRING_COMPARATOR = new StringLikeComparator();
    private static final Comparator<Object> DEFAULT_COMPARATOR = new ElementComparator();

    public enum IterativeOperation {
        EVERY,
        FILTER,
        FOR_EACH,
        MAP,
        SOME,
        FIND,
        FIND_INDEX,
        FIND_LAST,
        FIND_LAST_INDEX
    }

    public enum ReduceOperation {
        REDUCE,
        REDUCE_RIGHT
    }

    public static long toSliceIndex(double d, long j) {
        if (d >= 0.0d) {
            return d > ((double) j) ? j : (long) d;
        }
        double d2 = d + j;
        if (d2 < 0.0d) {
            return 0L;
        }
        return (long) d2;
    }

    public static Object iterativeMethod(Context context, IterativeOperation iterativeOperation, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return iterativeMethod(context, null, iterativeOperation, scriptable, scriptable2, objArr, true);
    }

    public static Object iterativeMethod(Context context, IdFunctionObject idFunctionObject, IterativeOperation iterativeOperation, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return iterativeMethod(context, idFunctionObject, iterativeOperation, scriptable, scriptable2, objArr, false);
    }

    /* JADX WARN: Code duplicated, block: B:120:0x00ef A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:121:0x00fb A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:122:0x0102 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:123:0x011e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:125:0x0121 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:126:0x0121 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:127:0x0121 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:129:0x0121 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:131:0x0121 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:132:0x0121 A[DONT_GENERATE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:79:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:83:0x00f5  */
    /* JADX WARN: Code duplicated, block: B:86:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:90:0x0105  */
    /* JADX WARN: Code duplicated, block: B:91:0x0109  */
    /* JADX WARN: Code duplicated, block: B:93:0x010f  */
    /* JADX WARN: Code duplicated, block: B:94:0x0118  */
    private static Object iterativeMethod(Context context, IdFunctionObject idFunctionObject, IterativeOperation iterativeOperation, Scriptable scriptable, Scriptable scriptable2, Object[] objArr, boolean z) {
        Scriptable scriptableNewArray;
        Object[] objArr2;
        Object objCall;
        Object obj;
        Scriptable object = ScriptRuntime.toObject(context, scriptable, scriptable2);
        if (!z && (IterativeOperation.FIND == iterativeOperation || IterativeOperation.FIND_INDEX == iterativeOperation || IterativeOperation.FIND_LAST == iterativeOperation || IterativeOperation.FIND_LAST_INDEX == iterativeOperation)) {
            ScriptRuntimeES6.requireObjectCoercible(context, object, idFunctionObject);
        }
        long lengthProperty = NativeArray.getLengthProperty(context, object);
        char c = 0;
        if (iterativeOperation == IterativeOperation.MAP && lengthProperty > SieveCacheKt.NodeLinkMask) {
            throw ScriptRuntime.rangeError(ScriptRuntime.getMessageById("msg.arraylength.bad", new Object[0]));
        }
        Function callbackArg = getCallbackArg(context, objArr.length > 0 ? objArr[0] : Undefined.instance);
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(callbackArg);
        Scriptable object2 = (objArr.length < 2 || (obj = objArr[1]) == null || obj == Undefined.instance) ? topLevelScope : ScriptRuntime.toObject(context, scriptable, objArr[1]);
        if (iterativeOperation == IterativeOperation.FILTER || iterativeOperation == IterativeOperation.MAP) {
            scriptableNewArray = context.newArray(scriptable, iterativeOperation == IterativeOperation.MAP ? (int) lengthProperty : 0);
        } else {
            scriptableNewArray = null;
        }
        long j = 0;
        long j2 = 1;
        long j3 = (iterativeOperation == IterativeOperation.FIND_LAST || iterativeOperation == IterativeOperation.FIND_LAST_INDEX) ? lengthProperty - 1 : 0L;
        long j4 = -1;
        if (iterativeOperation == IterativeOperation.FIND_LAST || iterativeOperation == IterativeOperation.FIND_LAST_INDEX) {
            lengthProperty = -1;
        }
        if (iterativeOperation != IterativeOperation.FIND_LAST && iterativeOperation != IterativeOperation.FIND_LAST_INDEX) {
            j4 = 1;
        }
        long j5 = j3;
        while (true) {
            char c2 = c;
            if (j5 != lengthProperty) {
                Object rawElem = getRawElem(object, j5);
                long j6 = j2;
                if (rawElem == Scriptable.NOT_FOUND) {
                    if (iterativeOperation == IterativeOperation.FIND || iterativeOperation == IterativeOperation.FIND_INDEX || iterativeOperation == IterativeOperation.FIND_LAST || iterativeOperation == IterativeOperation.FIND_LAST_INDEX) {
                        rawElem = Undefined.instance;
                        Long lValueOf = Long.valueOf(j5);
                        objArr2 = new Object[3];
                        objArr2[c2] = rawElem;
                        objArr2[1] = lValueOf;
                        objArr2[2] = object;
                        objCall = callbackArg.call(context, topLevelScope, object2, objArr2);
                        switch (iterativeOperation) {
                            case EVERY:
                                if (!ScriptRuntime.toBoolean(objCall)) {
                                    return Boolean.FALSE;
                                }
                                break;
                                break;
                            case FILTER:
                                if (ScriptRuntime.toBoolean(objCall)) {
                                    defineElem(context, scriptableNewArray, j, objArr2[c2]);
                                    j += j6;
                                }
                                break;
                            case MAP:
                                defineElem(context, scriptableNewArray, j5, objCall);
                                break;
                            case SOME:
                                if (ScriptRuntime.toBoolean(objCall)) {
                                    return Boolean.TRUE;
                                }
                                break;
                            case FIND:
                            case FIND_LAST:
                                if (ScriptRuntime.toBoolean(objCall)) {
                                    return rawElem;
                                }
                                break;
                            case FIND_INDEX:
                            case FIND_LAST_INDEX:
                                if (ScriptRuntime.toBoolean(objCall)) {
                                    return ScriptRuntime.wrapNumber(j5);
                                }
                                break;
                        }
                    }
                } else {
                    Long lValueOf2 = Long.valueOf(j5);
                    objArr2 = new Object[3];
                    objArr2[c2] = rawElem;
                    objArr2[1] = lValueOf2;
                    objArr2[2] = object;
                    objCall = callbackArg.call(context, topLevelScope, object2, objArr2);
                    switch (iterativeOperation) {
                        case null:
                            if (!ScriptRuntime.toBoolean(objCall)) {
                                return Boolean.FALSE;
                            }
                            break;
                        case 1:
                            if (ScriptRuntime.toBoolean(objCall)) {
                                defineElem(context, scriptableNewArray, j, objArr2[c2]);
                                j += j6;
                            }
                            break;
                        case 3:
                            defineElem(context, scriptableNewArray, j5, objCall);
                            break;
                        case 4:
                            if (ScriptRuntime.toBoolean(objCall)) {
                                return Boolean.TRUE;
                            }
                            break;
                        case 5:
                        case 7:
                            if (ScriptRuntime.toBoolean(objCall)) {
                                return rawElem;
                            }
                            break;
                        case 6:
                        case 8:
                            if (ScriptRuntime.toBoolean(objCall)) {
                                return ScriptRuntime.wrapNumber(j5);
                            }
                            break;
                    }
                }
                j5 += j4;
                c = c2;
                j2 = j6;
            } else {
                int iOrdinal = iterativeOperation.ordinal();
                if (iOrdinal == 0) {
                    return Boolean.TRUE;
                }
                if (iOrdinal == 1 || iOrdinal == 3) {
                    return scriptableNewArray;
                }
                if (iOrdinal == 4) {
                    return Boolean.FALSE;
                }
                if (iOrdinal == 6 || iOrdinal == 8) {
                    return ScriptRuntime.wrapNumber(-1.0d);
                }
                return Undefined.instance;
            }
        }
    }

    static Function getCallbackArg(Context context, Object obj) {
        RegExpProxy regExpProxy;
        if (!(obj instanceof Function)) {
            throw ScriptRuntime.notFunctionError(obj);
        }
        Function function = (Function) obj;
        if (context.getLanguageVersion() < 200 || (regExpProxy = ScriptRuntime.getRegExpProxy(context)) == null || !regExpProxy.isRegExp(function)) {
            return function;
        }
        throw ScriptRuntime.notFunctionError(obj);
    }

    static void defineElem(Context context, Scriptable scriptable, long j, Object obj) {
        if (j > SieveCacheKt.NodeLinkMask) {
            scriptable.put(Long.toString(j), scriptable, obj);
        } else {
            scriptable.put((int) j, scriptable, obj);
        }
    }

    static Object getRawElem(Scriptable scriptable, long j) {
        if (j > SieveCacheKt.NodeLinkMask) {
            return ScriptableObject.getProperty(scriptable, Long.toString(j));
        }
        return ScriptableObject.getProperty(scriptable, (int) j);
    }

    public static Object reduceMethod(Context context, ReduceOperation reduceOperation, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Scriptable object = ScriptRuntime.toObject(context, scriptable, scriptable2);
        long lengthProperty = NativeArray.getLengthProperty(context, object);
        Object obj = objArr.length > 0 ? objArr[0] : Undefined.instance;
        if (obj == null || !(obj instanceof Function)) {
            throw ScriptRuntime.notFunctionError(obj);
        }
        Function function = (Function) obj;
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(function);
        char c = 1;
        boolean z = reduceOperation == ReduceOperation.REDUCE;
        Object objCall = objArr.length > 1 ? objArr[1] : Scriptable.NOT_FOUND;
        long j = 0;
        while (j < lengthProperty) {
            long j2 = z ? j : (lengthProperty - 1) - j;
            char c2 = c;
            Object rawElem = getRawElem(object, j2);
            if (rawElem != Scriptable.NOT_FOUND) {
                if (objCall == Scriptable.NOT_FOUND) {
                    objCall = rawElem;
                } else {
                    Long lValueOf = Long.valueOf(j2);
                    Object[] objArr2 = new Object[4];
                    objArr2[0] = objCall;
                    objArr2[c2] = rawElem;
                    objArr2[2] = lValueOf;
                    objArr2[3] = object;
                    objCall = function.call(context, topLevelScope, topLevelScope, objArr2);
                }
            }
            j++;
            c = c2;
        }
        if (objCall != Scriptable.NOT_FOUND) {
            return objCall;
        }
        throw ScriptRuntime.typeErrorById("msg.empty.array.reduce", new Object[0]);
    }

    public static Comparator<Object> getSortComparator(Context context, Scriptable scriptable, Object[] objArr) {
        if (objArr.length > 0 && Undefined.instance != objArr[0]) {
            return getSortComparatorFromArguments(context, scriptable, objArr);
        }
        return DEFAULT_COMPARATOR;
    }

    public static ElementComparator getSortComparatorFromArguments(final Context context, final Scriptable scriptable, Object[] objArr) {
        final Callable valueFunctionAndThis = ScriptRuntime.getValueFunctionAndThis(objArr[0], context);
        final Scriptable scriptableLastStoredScriptable = ScriptRuntime.lastStoredScriptable(context);
        final Object[] objArr2 = new Object[2];
        return new ElementComparator(new Comparator<Object>() { // from class: org.mozilla.javascript.ArrayLikeAbstractOperations.1
            @Override // java.util.Comparator
            public int compare(Object obj, Object obj2) {
                Object[] objArr3 = objArr2;
                objArr3[0] = obj;
                objArr3[1] = obj2;
                int iCompare = Double.compare(ScriptRuntime.toNumber(valueFunctionAndThis.call(context, scriptable, scriptableLastStoredScriptable, objArr3)), 0.0d);
                if (iCompare < 0) {
                    return -1;
                }
                return iCompare > 0 ? 1 : 0;
            }
        });
    }

    public static final class StringLikeComparator implements Comparator<Object>, Serializable {
        private static final long serialVersionUID = 5299017659728190979L;

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return ScriptRuntime.toString(obj).compareTo(ScriptRuntime.toString(obj2));
        }
    }

    public static final class ElementComparator implements Comparator<Object>, Serializable {
        private static final long serialVersionUID = -1189948017688708858L;
        private final Comparator<Object> child;

        public ElementComparator() {
            this.child = ArrayLikeAbstractOperations.STRING_COMPARATOR;
        }

        public ElementComparator(Comparator<Object> comparator) {
            this.child = comparator;
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            if (obj == Undefined.instance) {
                if (obj2 == Undefined.instance) {
                    return 0;
                }
                return obj2 == Scriptable.NOT_FOUND ? -1 : 1;
            }
            if (obj == Scriptable.NOT_FOUND) {
                return obj2 == Scriptable.NOT_FOUND ? 0 : 1;
            }
            if (obj2 == Scriptable.NOT_FOUND || obj2 == Undefined.instance) {
                return -1;
            }
            return this.child.compare(obj, obj2);
        }
    }
}
