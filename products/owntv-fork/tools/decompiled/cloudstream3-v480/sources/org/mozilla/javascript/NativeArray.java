package org.mozilla.javascript;

import androidx.collection.SieveCacheKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.common.base.Ascii;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.mozilla.javascript.xml.XMLObject;

/* JADX INFO: loaded from: classes8.dex */
public class NativeArray extends IdScriptableObject implements List {
    private static final int ConstructorId_concat = -13;
    private static final int ConstructorId_every = -17;
    private static final int ConstructorId_filter = -18;
    private static final int ConstructorId_find = -22;
    private static final int ConstructorId_findIndex = -23;
    private static final int ConstructorId_findLast = -24;
    private static final int ConstructorId_findLastIndex = -25;
    private static final int ConstructorId_forEach = -19;
    private static final int ConstructorId_from = -30;
    private static final int ConstructorId_indexOf = -15;
    private static final int ConstructorId_isArray = -28;
    private static final int ConstructorId_join = -5;
    private static final int ConstructorId_lastIndexOf = -16;
    private static final int ConstructorId_map = -20;
    private static final int ConstructorId_of = -29;
    private static final int ConstructorId_pop = -9;
    private static final int ConstructorId_push = -8;
    private static final int ConstructorId_reduce = -26;
    private static final int ConstructorId_reduceRight = -27;
    private static final int ConstructorId_reverse = -6;
    private static final int ConstructorId_shift = -10;
    private static final int ConstructorId_slice = -14;
    private static final int ConstructorId_some = -21;
    private static final int ConstructorId_sort = -7;
    private static final int ConstructorId_splice = -12;
    private static final int ConstructorId_unshift = -11;
    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    private static final double GROW_FACTOR = 1.5d;
    private static final int Id_at = 34;
    private static final int Id_concat = 13;
    private static final int Id_constructor = 1;
    private static final int Id_copyWithin = 33;
    private static final int Id_entries = 31;
    private static final int Id_every = 17;
    private static final int Id_fill = 28;
    private static final int Id_filter = 18;
    private static final int Id_find = 22;
    private static final int Id_findIndex = 23;
    private static final int Id_findLast = 24;
    private static final int Id_findLastIndex = 25;
    private static final int Id_flat = 35;
    private static final int Id_flatMap = 36;
    private static final int Id_forEach = 19;
    private static final int Id_includes = 32;
    private static final int Id_indexOf = 15;
    private static final int Id_join = 5;
    private static final int Id_keys = 29;
    private static final int Id_lastIndexOf = 16;
    private static final int Id_length = 1;
    private static final int Id_map = 20;
    private static final int Id_pop = 9;
    private static final int Id_push = 8;
    private static final int Id_reduce = 26;
    private static final int Id_reduceRight = 27;
    private static final int Id_reverse = 6;
    private static final int Id_shift = 10;
    private static final int Id_slice = 14;
    private static final int Id_some = 21;
    private static final int Id_sort = 7;
    private static final int Id_splice = 12;
    private static final int Id_toLocaleString = 3;
    private static final int Id_toReversed = 37;
    private static final int Id_toSorted = 38;
    private static final int Id_toSource = 4;
    private static final int Id_toSpliced = 39;
    private static final int Id_toString = 2;
    private static final int Id_unshift = 11;
    private static final int Id_values = 30;
    private static final int Id_with = 40;
    private static final int MAX_INSTANCE_ID = 1;
    private static final int MAX_PRE_GROW_SIZE = 1431655764;
    private static final int MAX_PROTOTYPE_ID = 41;
    private static final int SymbolId_unscopables = 41;
    private static final long serialVersionUID = 7331366857676127338L;
    private Object[] dense;
    private boolean denseOnly;
    private long length;
    private int lengthAttr;
    private transient int modCount;
    private static final Object ARRAY_TAG = "Array";
    private static final Long NEGATIVE_ONE = -1L;
    private static final String[] UNSCOPABLES = {"at", "copyWithin", "entries", "fill", "find", "findIndex", "findLast", "findLastIndex", "flat", "flatMap", "includes", "keys", "toReversed", "toSorted", "toSpliced", "values"};
    private static int maximumInitialCapacity = 10000;

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int getMaxInstanceId() {
        return 1;
    }

    static void init(Context context, Scriptable scriptable, boolean z) {
        IdFunctionObject idFunctionObjectExportAsJSClass = new NativeArray(0L).exportAsJSClass(41, scriptable, z);
        ScriptRuntimeES6.addSymbolSpecies(context, scriptable, idFunctionObjectExportAsJSClass);
        ScriptRuntimeES6.addSymbolUnscopables(context, scriptable, idFunctionObjectExportAsJSClass);
    }

    static int getMaximumInitialCapacity() {
        return maximumInitialCapacity;
    }

    static void setMaximumInitialCapacity(int i) {
        maximumInitialCapacity = i;
    }

    public NativeArray(long j) {
        this.lengthAttr = 6;
        boolean z = j <= ((long) maximumInitialCapacity);
        this.denseOnly = z;
        if (z) {
            int i = (int) j;
            Object[] objArr = new Object[i < 10 ? 10 : i];
            this.dense = objArr;
            Arrays.fill(objArr, Scriptable.NOT_FOUND);
        }
        this.length = j;
    }

    public NativeArray(Object[] objArr) {
        this.lengthAttr = 6;
        this.denseOnly = true;
        this.dense = objArr;
        this.length = objArr.length;
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "Array";
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void setInstanceIdAttributes(int i, int i2) {
        if (i == 1) {
            this.lengthAttr = i2;
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findInstanceIdInfo(String str) {
        if (str.equals("length")) {
            return instanceIdInfo(this.lengthAttr, 1);
        }
        return super.findInstanceIdInfo(str);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected String getInstanceIdName(int i) {
        if (i == 1) {
            return "length";
        }
        return super.getInstanceIdName(i);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected Object getInstanceIdValue(int i) {
        if (i == 1) {
            return ScriptRuntime.wrapNumber(this.length);
        }
        return super.getInstanceIdValue(i);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void setInstanceIdValue(int i, Object obj) {
        if (i == 1) {
            setLength(obj);
        } else {
            super.setInstanceIdValue(i, obj);
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void fillConstructorProperties(IdFunctionObject idFunctionObject) {
        Object obj = ARRAY_TAG;
        addIdFunctionProperty(idFunctionObject, obj, -5, "join", 1);
        addIdFunctionProperty(idFunctionObject, obj, -6, "reverse", 0);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_sort, "sort", 1);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_push, "push", 1);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_pop, "pop", 0);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_shift, "shift", 0);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_unshift, "unshift", 1);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_splice, "splice", 2);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_concat, "concat", 1);
        addIdFunctionProperty(idFunctionObject, obj, -14, "slice", 2);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_indexOf, "indexOf", 1);
        addIdFunctionProperty(idFunctionObject, obj, -16, "lastIndexOf", 1);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_every, "every", 1);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_filter, "filter", 1);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_forEach, "forEach", 1);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_map, "map", 1);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_some, "some", 1);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_find, "find", 1);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_findIndex, "findIndex", 1);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_findLast, "findLast", 1);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_findLastIndex, "findLastIndex", 1);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_reduce, "reduce", 1);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_reduceRight, "reduceRight", 1);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_isArray, "isArray", 1);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_of, "of", 0);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_from, TypedValues.TransitionType.S_FROM, 1);
        super.fillConstructorProperties(idFunctionObject);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void initPrototypeId(int i) {
        String str;
        String str2;
        String str3;
        String str4;
        int i2;
        if (i == 41) {
            initPrototypeValue(i, SymbolKey.UNSCOPABLES, makeUnscopables(), 3);
            return;
        }
        switch (i) {
            case 1:
                str = "constructor";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 2:
                str2 = "toString";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 3:
                str2 = "toLocaleString";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 4:
                str2 = "toSource";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 5:
                str = "join";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 6:
                str2 = "reverse";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 7:
                str = "sort";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 8:
                str = "push";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 9:
                str2 = "pop";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 10:
                str2 = "shift";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 11:
                str = "unshift";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 12:
                str3 = "splice";
                i2 = 2;
                str4 = str3;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 13:
                str = "concat";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 14:
                str3 = "slice";
                i2 = 2;
                str4 = str3;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 15:
                str = "indexOf";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 16:
                str = "lastIndexOf";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 17:
                str = "every";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 18:
                str = "filter";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 19:
                str = "forEach";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 20:
                str = "map";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 21:
                str = "some";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 22:
                str = "find";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 23:
                str = "findIndex";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 24:
                str = "findLast";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 25:
                str = "findLastIndex";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 26:
                str = "reduce";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 27:
                str = "reduceRight";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 28:
                str = "fill";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 29:
                str2 = "keys";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 30:
                str2 = "values";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 31:
                str2 = "entries";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 32:
                str = "includes";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 33:
                str3 = "copyWithin";
                i2 = 2;
                str4 = str3;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 34:
                str = "at";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 35:
                str2 = "flat";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 36:
                str = "flatMap";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 37:
                str2 = "toReversed";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 38:
                str = "toSorted";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 39:
                str3 = "toSpliced";
                i2 = 2;
                str4 = str3;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            case 40:
                str3 = JsonPOJOBuilder.DEFAULT_WITH_PREFIX;
                i2 = 2;
                str4 = str3;
                initPrototypeMethod(ARRAY_TAG, i, str4, (String) null, i2);
                return;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
    }

    /* JADX WARN: Switch 'out' block B:7:0x0016 for B:8:0x0018 already processed. Defaulting to fallback option. */
    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.IdFunctionCall
    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(ARRAY_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int iMethodId = idFunctionObject.methodId();
        Scriptable scriptable3 = scriptable2;
        Object[] objArr2 = objArr;
        while (true) {
            switch (iMethodId) {
                case ConstructorId_from /* -30 */:
                    return js_from(context, scriptable, scriptable3, objArr2);
                case ConstructorId_of /* -29 */:
                    return js_of(context, scriptable, scriptable3, objArr2);
                case ConstructorId_isArray /* -28 */:
                    return Boolean.valueOf(objArr2.length > 0 && js_isArray(objArr2[0]));
                case ConstructorId_reduceRight /* -27 */:
                case ConstructorId_reduce /* -26 */:
                case ConstructorId_findLastIndex /* -25 */:
                case ConstructorId_findLast /* -24 */:
                case ConstructorId_findIndex /* -23 */:
                case ConstructorId_find /* -22 */:
                case ConstructorId_some /* -21 */:
                case ConstructorId_map /* -20 */:
                case ConstructorId_forEach /* -19 */:
                case ConstructorId_filter /* -18 */:
                case ConstructorId_every /* -17 */:
                case -16:
                case ConstructorId_indexOf /* -15 */:
                case -14:
                case ConstructorId_concat /* -13 */:
                case ConstructorId_splice /* -12 */:
                case ConstructorId_unshift /* -11 */:
                case ConstructorId_shift /* -10 */:
                case ConstructorId_pop /* -9 */:
                case ConstructorId_push /* -8 */:
                case ConstructorId_sort /* -7 */:
                case -6:
                case -5:
                    if (objArr2.length > 0) {
                        Scriptable object = ScriptRuntime.toObject(context, scriptable, objArr2[0]);
                        int length = objArr2.length - 1;
                        Object[] objArr3 = new Object[length];
                        System.arraycopy(objArr2, 1, objArr3, 0, length);
                        scriptable3 = object;
                        objArr2 = objArr3;
                    }
                    iMethodId = -iMethodId;
                    break;
                default:
                    switch (iMethodId) {
                        case 1:
                            if (scriptable3 != null) {
                                return idFunctionObject.construct(context, scriptable, objArr2);
                            }
                            return jsConstructor(context, scriptable, objArr2);
                        case 2:
                            return toStringHelper(context, scriptable, scriptable3, context.hasFeature(4), false);
                        case 3:
                            return toStringHelper(context, scriptable, scriptable3, false, true);
                        case 4:
                            return toStringHelper(context, scriptable, scriptable3, true, false);
                        case 5:
                            return js_join(context, scriptable, scriptable3, objArr2);
                        case 6:
                            return js_reverse(context, scriptable, scriptable3, objArr2);
                        case 7:
                            return js_sort(context, scriptable, scriptable3, objArr2);
                        case 8:
                            return js_push(context, scriptable, scriptable3, objArr2);
                        case 9:
                            return js_pop(context, scriptable, scriptable3, objArr2);
                        case 10:
                            return js_shift(context, scriptable, scriptable3, objArr2);
                        case 11:
                            return js_unshift(context, scriptable, scriptable3, objArr2);
                        case 12:
                            return js_splice(context, scriptable, scriptable3, objArr2);
                        case 13:
                            return js_concat(context, scriptable, scriptable3, objArr2);
                        case 14:
                            return js_slice(context, scriptable, scriptable3, objArr2);
                        case 15:
                            return js_indexOf(context, scriptable, scriptable3, objArr2);
                        case 16:
                            return js_lastIndexOf(context, scriptable, scriptable3, objArr2);
                        case 17:
                            return ArrayLikeAbstractOperations.iterativeMethod(context, idFunctionObject, ArrayLikeAbstractOperations.IterativeOperation.EVERY, scriptable, scriptable3, objArr2);
                        case 18:
                            return ArrayLikeAbstractOperations.iterativeMethod(context, idFunctionObject, ArrayLikeAbstractOperations.IterativeOperation.FILTER, scriptable, scriptable3, objArr2);
                        case 19:
                            return ArrayLikeAbstractOperations.iterativeMethod(context, idFunctionObject, ArrayLikeAbstractOperations.IterativeOperation.FOR_EACH, scriptable, scriptable3, objArr2);
                        case 20:
                            return ArrayLikeAbstractOperations.iterativeMethod(context, idFunctionObject, ArrayLikeAbstractOperations.IterativeOperation.MAP, scriptable, scriptable3, objArr2);
                        case 21:
                            return ArrayLikeAbstractOperations.iterativeMethod(context, idFunctionObject, ArrayLikeAbstractOperations.IterativeOperation.SOME, scriptable, scriptable3, objArr2);
                        case 22:
                            return ArrayLikeAbstractOperations.iterativeMethod(context, idFunctionObject, ArrayLikeAbstractOperations.IterativeOperation.FIND, scriptable, scriptable3, objArr2);
                        case 23:
                            return ArrayLikeAbstractOperations.iterativeMethod(context, idFunctionObject, ArrayLikeAbstractOperations.IterativeOperation.FIND_INDEX, scriptable, scriptable3, objArr2);
                        case 24:
                            return ArrayLikeAbstractOperations.iterativeMethod(context, idFunctionObject, ArrayLikeAbstractOperations.IterativeOperation.FIND_LAST, scriptable, scriptable3, objArr2);
                        case 25:
                            return ArrayLikeAbstractOperations.iterativeMethod(context, idFunctionObject, ArrayLikeAbstractOperations.IterativeOperation.FIND_LAST_INDEX, scriptable, scriptable3, objArr2);
                        case 26:
                            return ArrayLikeAbstractOperations.reduceMethod(context, ArrayLikeAbstractOperations.ReduceOperation.REDUCE, scriptable, scriptable3, objArr2);
                        case 27:
                            return ArrayLikeAbstractOperations.reduceMethod(context, ArrayLikeAbstractOperations.ReduceOperation.REDUCE_RIGHT, scriptable, scriptable3, objArr2);
                        case 28:
                            return js_fill(context, scriptable, scriptable3, objArr2);
                        case 29:
                            return new NativeArrayIterator(scriptable, ScriptRuntime.toObject(context, scriptable, scriptable3), NativeArrayIterator.ARRAY_ITERATOR_TYPE.KEYS);
                        case 30:
                            return new NativeArrayIterator(scriptable, ScriptRuntime.toObject(context, scriptable, scriptable3), NativeArrayIterator.ARRAY_ITERATOR_TYPE.VALUES);
                        case 31:
                            return new NativeArrayIterator(scriptable, ScriptRuntime.toObject(context, scriptable, scriptable3), NativeArrayIterator.ARRAY_ITERATOR_TYPE.ENTRIES);
                        case 32:
                            return js_includes(context, scriptable, scriptable3, objArr2);
                        case 33:
                            return js_copyWithin(context, scriptable, scriptable3, objArr2);
                        case 34:
                            return js_at(context, scriptable, scriptable3, objArr2);
                        case 35:
                            return js_flat(context, scriptable, scriptable3, objArr2);
                        case 36:
                            return js_flatMap(context, scriptable, scriptable3, objArr2);
                        case 37:
                            return js_toReversed(context, scriptable, scriptable3, objArr2);
                        case 38:
                            return js_toSorted(context, scriptable, scriptable3, objArr2);
                        case 39:
                            return js_toSpliced(context, scriptable, scriptable3, objArr2);
                        case 40:
                            return js_with(context, scriptable, scriptable3, objArr2);
                        default:
                            throw new IllegalArgumentException("Array.prototype has no method: " + idFunctionObject.getFunctionName());
                    }
            }
        }
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void setPrototype(Scriptable scriptable) {
        super.setPrototype(scriptable);
        if (scriptable instanceof NativeArray) {
            return;
        }
        setDenseOnly(false);
    }

    private Object makeUnscopables() {
        NativeObject nativeObject;
        Context currentContext = Context.getCurrentContext();
        if (currentContext != null) {
            nativeObject = (NativeObject) currentContext.newObject(getParentScope());
        } else {
            nativeObject = new NativeObject();
        }
        ScriptableObject scriptableObjectBuildDataDescriptor = ScriptableObject.buildDataDescriptor(nativeObject, true, 0);
        for (String str : UNSCOPABLES) {
            nativeObject.defineOwnProperty(currentContext, str, scriptableObjectBuildDataDescriptor);
        }
        nativeObject.setPrototype(null);
        return nativeObject;
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public Object get(int i, Scriptable scriptable) {
        if (!this.denseOnly && isGetterOrSetter(null, i, false)) {
            return super.get(i, scriptable);
        }
        Object[] objArr = this.dense;
        return (objArr == null || i < 0 || i >= objArr.length) ? super.get(i, scriptable) : objArr[i];
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public boolean has(int i, Scriptable scriptable) {
        if (!this.denseOnly && isGetterOrSetter(null, i, false)) {
            return super.has(i, scriptable);
        }
        Object[] objArr = this.dense;
        if (objArr == null || i < 0 || i >= objArr.length) {
            return super.has(i, scriptable);
        }
        return objArr[i] != NOT_FOUND;
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.SymbolScriptable
    public boolean has(Symbol symbol, Scriptable scriptable) {
        if (SymbolKey.ITERATOR.equals(symbol)) {
            return super.has("values", scriptable);
        }
        return super.has(symbol, scriptable);
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.SymbolScriptable
    public Object get(Symbol symbol, Scriptable scriptable) {
        if (SymbolKey.ITERATOR.equals(symbol)) {
            return super.get("values", scriptable);
        }
        return super.get(symbol, scriptable);
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.SymbolScriptable
    public void put(Symbol symbol, Scriptable scriptable, Object obj) {
        if (SymbolKey.ITERATOR.equals(symbol)) {
            super.put("values", scriptable, obj);
        }
        super.put(symbol, scriptable, obj);
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.SymbolScriptable
    public void delete(Symbol symbol) {
        if (SymbolKey.ITERATOR.equals(symbol)) {
            super.delete("values");
        }
        super.delete(symbol);
    }

    private static long toArrayIndex(Object obj) {
        if (obj instanceof String) {
            return toArrayIndex((String) obj);
        }
        if (obj instanceof Number) {
            return toArrayIndex(((Number) obj).doubleValue());
        }
        return -1L;
    }

    private static long toArrayIndex(String str) {
        long arrayIndex = toArrayIndex(ScriptRuntime.toNumber(str));
        if (Long.toString(arrayIndex).equals(str)) {
            return arrayIndex;
        }
        return -1L;
    }

    private static long toArrayIndex(double d) {
        if (Double.isNaN(d)) {
            return -1L;
        }
        long uint32 = ScriptRuntime.toUint32(d);
        if (uint32 != d || uint32 == 4294967295L) {
            return -1L;
        }
        return uint32;
    }

    private static int toDenseIndex(Object obj) {
        long arrayIndex = toArrayIndex(obj);
        if (0 > arrayIndex || arrayIndex >= SieveCacheKt.NodeLinkMask) {
            return -1;
        }
        return (int) arrayIndex;
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void put(String str, Scriptable scriptable, Object obj) {
        super.put(str, scriptable, obj);
        if (scriptable == this) {
            long arrayIndex = toArrayIndex(str);
            if (arrayIndex >= this.length) {
                this.length = arrayIndex + 1;
                this.modCount++;
                this.denseOnly = false;
            }
        }
    }

    private boolean ensureCapacity(int i) {
        Object[] objArr = this.dense;
        if (i <= objArr.length) {
            return true;
        }
        if (i > MAX_PRE_GROW_SIZE) {
            this.denseOnly = false;
            return false;
        }
        int iMax = Math.max(i, (int) (((double) objArr.length) * GROW_FACTOR));
        Object[] objArr2 = new Object[iMax];
        Object[] objArr3 = this.dense;
        System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
        Arrays.fill(objArr2, this.dense.length, iMax, Scriptable.NOT_FOUND);
        this.dense = objArr2;
        return true;
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void put(int i, Scriptable scriptable, Object obj) {
        if (scriptable == this && !isSealed() && this.dense != null && i >= 0 && (this.denseOnly || !isGetterOrSetter(null, i, true))) {
            if (!isExtensible() && this.length <= i) {
                return;
            }
            Object[] objArr = this.dense;
            if (i < objArr.length) {
                objArr[i] = obj;
                long j = i;
                if (this.length <= j) {
                    this.length = j + 1;
                    this.modCount++;
                    return;
                }
                return;
            }
            if (this.denseOnly && i < ((double) objArr.length) * GROW_FACTOR && ensureCapacity(i + 1)) {
                this.dense[i] = obj;
                this.length = ((long) i) + 1;
                this.modCount++;
                return;
            }
            this.denseOnly = false;
        }
        super.put(i, scriptable, obj);
        if (scriptable == this && (this.lengthAttr & 1) == 0) {
            long j2 = i;
            if (this.length <= j2) {
                this.length = j2 + 1;
                this.modCount++;
            }
        }
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void delete(int i) {
        Object[] objArr = this.dense;
        if (objArr != null && i >= 0 && i < objArr.length && !isSealed() && (this.denseOnly || !isGetterOrSetter(null, i, true))) {
            this.dense[i] = NOT_FOUND;
        } else {
            super.delete(i);
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.ScriptableObject
    public Object[] getIds(boolean z, boolean z2) {
        Object[] ids = super.getIds(z, z2);
        Object[] objArr = this.dense;
        if (objArr != null) {
            int length = objArr.length;
            long j = this.length;
            if (length > j) {
                length = (int) j;
            }
            if (length != 0) {
                int length2 = ids.length;
                Object[] objArr2 = new Object[length + length2];
                int i = 0;
                for (int i2 = 0; i2 != length; i2++) {
                    if (this.dense[i2] != NOT_FOUND) {
                        objArr2[i] = Integer.valueOf(i2);
                        i++;
                    }
                }
                if (i != length) {
                    Object[] objArr3 = new Object[i + length2];
                    System.arraycopy(objArr2, 0, objArr3, 0, i);
                    objArr2 = objArr3;
                }
                System.arraycopy(ids, 0, objArr2, i, length2);
                return objArr2;
            }
        }
        return ids;
    }

    public List<Integer> getIndexIds() {
        Object[] ids = getIds();
        ArrayList arrayList = new ArrayList(ids.length);
        for (Object obj : ids) {
            int int32 = ScriptRuntime.toInt32(obj);
            if (int32 >= 0 && ScriptRuntime.toString(int32).equals(ScriptRuntime.toString(obj))) {
                arrayList.add(Integer.valueOf(int32));
            }
        }
        return arrayList;
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public Object getDefaultValue(Class<?> cls) {
        return (cls == ScriptRuntime.NumberClass && Context.getContext().getLanguageVersion() == 120) ? Long.valueOf(this.length) : super.getDefaultValue(cls);
    }

    private ScriptableObject defaultIndexPropertyDescriptor(Object obj) {
        Scriptable parentScope = getParentScope();
        if (parentScope != null) {
            this = parentScope;
        }
        NativeObject nativeObject = new NativeObject();
        ScriptRuntime.setBuiltinProtoAndParent(nativeObject, this, TopLevel.Builtins.Object);
        nativeObject.defineProperty(ES6Iterator.VALUE_PROPERTY, obj, 0);
        nativeObject.defineProperty("writable", Boolean.TRUE, 0);
        nativeObject.defineProperty("enumerable", Boolean.TRUE, 0);
        nativeObject.defineProperty("configurable", Boolean.TRUE, 0);
        return nativeObject;
    }

    @Override // org.mozilla.javascript.ScriptableObject
    public int getAttributes(int i) {
        Object[] objArr = this.dense;
        if (objArr == null || i < 0 || i >= objArr.length || objArr[i] == NOT_FOUND) {
            return super.getAttributes(i);
        }
        return 0;
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.ScriptableObject
    protected ScriptableObject getOwnPropertyDescriptor(Context context, Object obj) {
        int denseIndex;
        if (this.dense != null && (denseIndex = toDenseIndex(obj)) >= 0) {
            Object[] objArr = this.dense;
            if (denseIndex < objArr.length && objArr[denseIndex] != NOT_FOUND) {
                return defaultIndexPropertyDescriptor(this.dense[denseIndex]);
            }
        }
        return super.getOwnPropertyDescriptor(context, obj);
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.ScriptableObject
    protected boolean defineOwnProperty(Context context, Object obj, ScriptableObject scriptableObject, boolean z) {
        Object[] objArr;
        long arrayIndex = toArrayIndex(obj);
        if (arrayIndex >= this.length) {
            this.length = 1 + arrayIndex;
            this.modCount++;
        }
        if (arrayIndex != -1 && (objArr = this.dense) != null) {
            this.dense = null;
            this.denseOnly = false;
            for (int i = 0; i < objArr.length; i++) {
                if (objArr[i] != NOT_FOUND) {
                    if (!isExtensible()) {
                        setAttributes(i, 0);
                    }
                    put(i, this, objArr[i]);
                }
            }
        }
        super.defineOwnProperty(context, obj, scriptableObject, z);
        if ("length".equals(obj)) {
            this.lengthAttr = getAttributes("length");
        }
        return true;
    }

    private static Object jsConstructor(Context context, Scriptable scriptable, Object[] objArr) {
        if (objArr.length == 0) {
            return new NativeArray(0L);
        }
        if (context.getLanguageVersion() == 120) {
            return new NativeArray(objArr);
        }
        Object obj = objArr[0];
        if (objArr.length > 1 || !(obj instanceof Number)) {
            return new NativeArray(objArr);
        }
        long uint32 = ScriptRuntime.toUint32(obj);
        if (uint32 != ((Number) obj).doubleValue()) {
            throw ScriptRuntime.rangeError(ScriptRuntime.getMessageById("msg.arraylength.bad", new Object[0]));
        }
        return new NativeArray(uint32);
    }

    private static Scriptable callConstructorOrCreateArray(Context context, Scriptable scriptable, Scriptable scriptable2, long j, boolean z) {
        Scriptable scriptableConstruct;
        if (scriptable2 instanceof Constructable) {
            try {
                scriptableConstruct = ((Constructable) scriptable2).construct(context, scriptable, (z || j > 0) ? new Object[]{Long.valueOf(j)} : ScriptRuntime.emptyArgs);
            } catch (EcmaError e) {
                if (!"TypeError".equals(e.getName())) {
                    throw e;
                }
                scriptableConstruct = null;
            }
        } else {
            scriptableConstruct = null;
        }
        if (scriptableConstruct == null) {
            return context.newArray(scriptable, j <= SieveCacheKt.NodeLinkMask ? (int) j : 0);
        }
        return scriptableConstruct;
    }

    private static Object js_from(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Function function;
        char c = 0;
        char c2 = 1;
        Scriptable object = ScriptRuntime.toObject(scriptable, objArr.length >= 1 ? objArr[0] : Undefined.instance);
        Object obj = objArr.length >= 2 ? objArr[1] : Undefined.instance;
        Scriptable scriptableEnsureScriptable = Undefined.SCRIPTABLE_UNDEFINED;
        boolean zIsUndefined = Undefined.isUndefined(obj);
        if (zIsUndefined) {
            function = null;
        } else {
            if (!(obj instanceof Function)) {
                throw ScriptRuntime.typeErrorById("msg.map.function.not", new Object[0]);
            }
            function = (Function) obj;
            if (objArr.length >= 3) {
                scriptableEnsureScriptable = ensureScriptable(objArr[2]);
            }
        }
        Function function2 = function;
        Scriptable scriptable3 = scriptableEnsureScriptable;
        Object property = ScriptableObject.getProperty(object, SymbolKey.ITERATOR);
        long j = 1;
        if (!(object instanceof NativeArray) && property != Scriptable.NOT_FOUND && !Undefined.isUndefined(property)) {
            Object objCallIterator = ScriptRuntime.callIterator(object, context, scriptable);
            if (!Undefined.isUndefined(objCallIterator)) {
                Scriptable scriptableCallConstructorOrCreateArray = callConstructorOrCreateArray(context, scriptable, scriptable2, 0L, false);
                IteratorLikeIterable iteratorLikeIterable = new IteratorLikeIterable(context, scriptable, objCallIterator);
                try {
                    Iterator<Object> itIterator2 = iteratorLikeIterable.iterator();
                    long j2 = 0;
                    while (itIterator2.hasNext()) {
                        Object next = itIterator2.next();
                        if (!zIsUndefined) {
                            Long lValueOf = Long.valueOf(j2);
                            Object[] objArr2 = new Object[2];
                            objArr2[c] = next;
                            objArr2[c2] = lValueOf;
                            next = function2.call(context, scriptable, scriptable3, objArr2);
                        }
                        char c3 = c;
                        char c4 = c2;
                        long j3 = j2;
                        ArrayLikeAbstractOperations.defineElem(context, scriptableCallConstructorOrCreateArray, j3, next);
                        j2 = j3 + 1;
                        c2 = c4;
                        c = c3;
                    }
                    iteratorLikeIterable.close();
                    setLengthProperty(context, scriptableCallConstructorOrCreateArray, j2);
                    return scriptableCallConstructorOrCreateArray;
                } catch (Throwable th) {
                    try {
                        iteratorLikeIterable.close();
                        throw th;
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                        throw th;
                    }
                }
            }
        }
        long lengthProperty = getLengthProperty(context, object);
        Scriptable scriptableCallConstructorOrCreateArray2 = callConstructorOrCreateArray(context, scriptable, scriptable2, lengthProperty, true);
        long j4 = 0;
        while (j4 < lengthProperty) {
            Object elem = getElem(context, object, j4);
            if (!zIsUndefined) {
                elem = function2.call(context, scriptable, scriptable3, new Object[]{elem, Long.valueOf(j4)});
            }
            ArrayLikeAbstractOperations.defineElem(context, scriptableCallConstructorOrCreateArray2, j4, elem);
            j4 += j;
            j = j;
        }
        setLengthProperty(context, scriptableCallConstructorOrCreateArray2, lengthProperty);
        return scriptableCallConstructorOrCreateArray2;
    }

    private static Object js_of(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Scriptable scriptableCallConstructorOrCreateArray = callConstructorOrCreateArray(context, scriptable, scriptable2, objArr.length, true);
        int i = 0;
        if (context.getLanguageVersion() >= 200 && (scriptableCallConstructorOrCreateArray instanceof ScriptableObject)) {
            ScriptableObject scriptableObjectBuildDataDescriptor = ScriptableObject.buildDataDescriptor(scriptableCallConstructorOrCreateArray, null, 0);
            while (i < objArr.length) {
                scriptableObjectBuildDataDescriptor.put(ES6Iterator.VALUE_PROPERTY, scriptableObjectBuildDataDescriptor, objArr[i]);
                ((ScriptableObject) scriptableCallConstructorOrCreateArray).defineOwnProperty(context, Integer.valueOf(i), scriptableObjectBuildDataDescriptor);
                i++;
            }
        } else {
            while (i < objArr.length) {
                ArrayLikeAbstractOperations.defineElem(context, scriptableCallConstructorOrCreateArray, i, objArr[i]);
                i++;
            }
        }
        setLengthProperty(context, scriptableCallConstructorOrCreateArray, objArr.length);
        return scriptableCallConstructorOrCreateArray;
    }

    public long getLength() {
        return this.length;
    }

    @Deprecated
    public long jsGet_length() {
        return getLength();
    }

    void setDenseOnly(boolean z) {
        if (z && !this.denseOnly) {
            throw new IllegalArgumentException();
        }
        this.denseOnly = z;
    }

    private void setLength(Object obj) {
        if ((this.lengthAttr & 1) != 0) {
            return;
        }
        double number = ScriptRuntime.toNumber(obj);
        long uint32 = ScriptRuntime.toUint32(number);
        double d = uint32;
        if (d != number) {
            throw ScriptRuntime.rangeError(ScriptRuntime.getMessageById("msg.arraylength.bad", new Object[0]));
        }
        if (this.denseOnly) {
            long j = this.length;
            if (uint32 < j) {
                Object[] objArr = this.dense;
                Arrays.fill(objArr, (int) uint32, objArr.length, NOT_FOUND);
                this.length = uint32;
                this.modCount++;
                return;
            }
            if (uint32 < 1431655764 && d < j * GROW_FACTOR && ensureCapacity((int) uint32)) {
                this.length = uint32;
                this.modCount++;
                return;
            }
            this.denseOnly = false;
        }
        long j2 = this.length;
        if (uint32 < j2) {
            if (j2 - uint32 > 4096) {
                for (Object obj2 : getIds()) {
                    if (obj2 instanceof String) {
                        String str = (String) obj2;
                        if (toArrayIndex(str) >= uint32) {
                            delete(str);
                        }
                    } else {
                        int iIntValue = ((Integer) obj2).intValue();
                        if (iIntValue >= uint32) {
                            delete(iIntValue);
                        }
                    }
                }
            } else {
                for (long j3 = uint32; j3 < this.length; j3++) {
                    deleteElem(this, j3);
                }
            }
        }
        this.length = uint32;
        this.modCount++;
    }

    static long getLengthProperty(Context context, Scriptable scriptable) {
        if (scriptable instanceof NativeString) {
            return ((NativeString) scriptable).getLength();
        }
        if (scriptable instanceof NativeArray) {
            return ((NativeArray) scriptable).getLength();
        }
        if (scriptable instanceof XMLObject) {
            return ((Number) ((Callable) scriptable.get("length", scriptable)).call(context, scriptable, scriptable, ScriptRuntime.emptyArgs)).longValue();
        }
        Object property = ScriptableObject.getProperty(scriptable, "length");
        if (property == Scriptable.NOT_FOUND) {
            return 0L;
        }
        double number = ScriptRuntime.toNumber(property);
        if (number > 9.007199254740991E15d) {
            return 9007199254740991L;
        }
        if (number < 0.0d) {
            return 0L;
        }
        return (long) number;
    }

    private static Object setLengthProperty(Context context, Scriptable scriptable, long j) {
        Number numberWrapNumber = ScriptRuntime.wrapNumber(j);
        ScriptableObject.putProperty(scriptable, "length", numberWrapNumber);
        return numberWrapNumber;
    }

    private static void deleteElem(Scriptable scriptable, long j) {
        int i = (int) j;
        if (i == j) {
            scriptable.delete(i);
        } else {
            scriptable.delete(Long.toString(j));
        }
    }

    private static Object getElem(Context context, Scriptable scriptable, long j) {
        Object rawElem = ArrayLikeAbstractOperations.getRawElem(scriptable, j);
        return rawElem != Scriptable.NOT_FOUND ? rawElem : Undefined.instance;
    }

    private static void defineElemOrThrow(Context context, Scriptable scriptable, long j, Object obj) {
        if (j > 9.007199254740991E15d) {
            throw ScriptRuntime.typeErrorById("msg.arraylength.too.big", String.valueOf(j));
        }
        ArrayLikeAbstractOperations.defineElem(context, scriptable, j, obj);
    }

    private static void setElem(Context context, Scriptable scriptable, long j, Object obj) {
        if (j > SieveCacheKt.NodeLinkMask) {
            ScriptableObject.putProperty(scriptable, Long.toString(j), obj);
        } else {
            ScriptableObject.putProperty(scriptable, (int) j, obj);
        }
    }

    private static void setRawElem(Context context, Scriptable scriptable, long j, Object obj) {
        if (obj == NOT_FOUND) {
            deleteElem(scriptable, j);
        } else {
            setElem(context, scriptable, j, obj);
        }
    }

    private static String toStringHelper(Context context, Scriptable scriptable, Scriptable scriptable2, boolean z, boolean z2) {
        String str;
        boolean zContains;
        boolean z3;
        long j;
        long j2;
        boolean z4;
        Scriptable object = ScriptRuntime.toObject(context, scriptable, scriptable2);
        long lengthProperty = getLengthProperty(context, object);
        StringBuilder sb = new StringBuilder(256);
        if (z) {
            sb.append(AbstractJsonLexerKt.BEGIN_LIST);
            str = ", ";
        } else {
            str = ",";
        }
        if (context.iterating == null) {
            context.iterating = new HashSet();
            zContains = false;
            z3 = true;
        } else {
            zContains = context.iterating.contains(object);
            z3 = false;
        }
        long j3 = 0;
        if (zContains) {
            j2 = 0;
            j = 0;
            z4 = false;
        } else {
            try {
                context.iterating.add(object);
                boolean z5 = !z || context.getLanguageVersion() < 150;
                j = 0;
                boolean z6 = false;
                while (j < lengthProperty) {
                    if (j > j3) {
                        sb.append(str);
                    }
                    Object rawElem = ArrayLikeAbstractOperations.getRawElem(object, j);
                    if (rawElem == NOT_FOUND || (z5 && (rawElem == null || rawElem == Undefined.instance))) {
                        j3 = j3;
                        z6 = false;
                    } else {
                        if (z) {
                            sb.append(ScriptRuntime.uneval(context, scriptable, rawElem));
                        } else {
                            if (rawElem instanceof String) {
                                sb.append((String) rawElem);
                            } else {
                                if (z2) {
                                    rawElem = ScriptRuntime.getPropFunctionAndThis(rawElem, "toLocaleString", context, scriptable).call(context, scriptable, ScriptRuntime.lastStoredScriptable(context), ScriptRuntime.emptyArgs);
                                }
                                sb.append(ScriptRuntime.toString(rawElem));
                            }
                            z6 = true;
                        }
                        j3 = j3;
                        z6 = true;
                    }
                    j++;
                    j3 = j3;
                }
                j2 = j3;
                context.iterating.remove(object);
                z4 = z6;
            } catch (Throwable th) {
                if (z3) {
                    context.iterating = null;
                }
                throw th;
            }
        }
        if (z3) {
            context.iterating = null;
        }
        if (z) {
            if (!z4 && j > j2) {
                sb.append(", ]");
            } else {
                sb.append(AbstractJsonLexerKt.END_LIST);
            }
        }
        return sb.toString();
    }

    private static String js_join(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        String string;
        Object obj;
        Scriptable object = ScriptRuntime.toObject(context, scriptable, scriptable2);
        long lengthProperty = getLengthProperty(context, object);
        int i = (int) lengthProperty;
        int i2 = 0;
        if (lengthProperty != i) {
            throw Context.reportRuntimeErrorById("msg.arraylength.too.big", String.valueOf(lengthProperty));
        }
        if (objArr.length < 1 || objArr[0] == Undefined.instance) {
            string = ",";
        } else {
            string = ScriptRuntime.toString(objArr[0]);
        }
        if (object instanceof NativeArray) {
            NativeArray nativeArray = (NativeArray) object;
            if (nativeArray.denseOnly) {
                StringBuilder sb = new StringBuilder();
                while (i2 < i) {
                    if (i2 != 0) {
                        sb.append(string);
                    }
                    Object[] objArr2 = nativeArray.dense;
                    if (i2 < objArr2.length && (obj = objArr2[i2]) != null && obj != Undefined.instance && obj != Scriptable.NOT_FOUND) {
                        sb.append(ScriptRuntime.toString(obj));
                    }
                    i2++;
                }
                return sb.toString();
            }
        }
        if (i == 0) {
            return "";
        }
        String[] strArr = new String[i];
        int length = 0;
        for (int i3 = 0; i3 != i; i3++) {
            Object elem = getElem(context, object, i3);
            if (elem != null && elem != Undefined.instance) {
                String string2 = ScriptRuntime.toString(elem);
                length += string2.length();
                strArr[i3] = string2;
            }
        }
        StringBuilder sb2 = new StringBuilder(length + ((i - 1) * string.length()));
        while (i2 != i) {
            if (i2 != 0) {
                sb2.append(string);
            }
            String str = strArr[i2];
            if (str != null) {
                sb2.append(str);
            }
            i2++;
        }
        return sb2.toString();
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0034 A[LOOP:1: B:10:0x0030->B:12:0x0034, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:9:0x0026  */
    private static Scriptable js_reverse(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        long lengthProperty;
        long j;
        long j2;
        Scriptable object = ScriptRuntime.toObject(context, scriptable, scriptable2);
        if (object instanceof NativeArray) {
            NativeArray nativeArray = (NativeArray) object;
            if (nativeArray.denseOnly) {
                int i = 0;
                for (int i2 = ((int) nativeArray.length) - 1; i < i2; i2--) {
                    Object[] objArr2 = nativeArray.dense;
                    Object obj = objArr2[i];
                    objArr2[i] = objArr2[i2];
                    objArr2[i2] = obj;
                    i++;
                }
            } else {
                lengthProperty = getLengthProperty(context, object);
                j = lengthProperty / 2;
                for (j2 = 0; j2 < j; j2++) {
                    long j3 = (lengthProperty - j2) - 1;
                    Object rawElem = ArrayLikeAbstractOperations.getRawElem(object, j2);
                    setRawElem(context, object, j2, ArrayLikeAbstractOperations.getRawElem(object, j3));
                    setRawElem(context, object, j3, rawElem);
                }
            }
        } else {
            lengthProperty = getLengthProperty(context, object);
            j = lengthProperty / 2;
            while (j2 < j) {
                long j4 = (lengthProperty - j2) - 1;
                Object rawElem2 = ArrayLikeAbstractOperations.getRawElem(object, j2);
                setRawElem(context, object, j2, ArrayLikeAbstractOperations.getRawElem(object, j4));
                setRawElem(context, object, j4, rawElem2);
            }
        }
        return object;
    }

    private static Scriptable js_sort(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return sort(context, ScriptRuntime.toObject(context, scriptable, scriptable2), ArrayLikeAbstractOperations.getSortComparator(context, scriptable, objArr));
    }

    private static Scriptable sort(Context context, Scriptable scriptable, Comparator<Object> comparator) {
        long lengthProperty = getLengthProperty(context, scriptable);
        int i = (int) lengthProperty;
        if (lengthProperty != i) {
            throw Context.reportRuntimeErrorById("msg.arraylength.too.big", String.valueOf(lengthProperty));
        }
        Object[] objArr = new Object[i];
        for (int i2 = 0; i2 != i; i2++) {
            objArr[i2] = ArrayLikeAbstractOperations.getRawElem(scriptable, i2);
        }
        try {
            Arrays.sort(objArr, comparator);
            for (int i3 = 0; i3 < i; i3++) {
                setRawElem(context, scriptable, i3, objArr[i3]);
            }
        } catch (IllegalArgumentException unused) {
        }
        return scriptable;
    }

    private static Object js_push(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Scriptable object = ScriptRuntime.toObject(context, scriptable, scriptable2);
        int i = 0;
        if (object instanceof NativeArray) {
            NativeArray nativeArray = (NativeArray) object;
            if (nativeArray.denseOnly && nativeArray.ensureCapacity(((int) nativeArray.length) + objArr.length)) {
                int length = objArr.length;
                while (i < length) {
                    Object obj = objArr[i];
                    Object[] objArr2 = nativeArray.dense;
                    long j = nativeArray.length;
                    nativeArray.length = 1 + j;
                    objArr2[(int) j] = obj;
                    nativeArray.modCount++;
                    i++;
                }
                return ScriptRuntime.wrapNumber(nativeArray.length);
            }
        }
        long lengthProperty = getLengthProperty(context, object);
        while (i < objArr.length) {
            setElem(context, object, ((long) i) + lengthProperty, objArr[i]);
            i++;
        }
        Object lengthProperty2 = setLengthProperty(context, object, lengthProperty + ((long) objArr.length));
        if (context.getLanguageVersion() == 120) {
            return objArr.length == 0 ? Undefined.instance : objArr[objArr.length - 1];
        }
        return lengthProperty2;
    }

    private static Object js_pop(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Object elem;
        Scriptable object = ScriptRuntime.toObject(context, scriptable, scriptable2);
        if (object instanceof NativeArray) {
            NativeArray nativeArray = (NativeArray) object;
            if (nativeArray.denseOnly) {
                long j = nativeArray.length;
                if (j > 0) {
                    long j2 = j - 1;
                    nativeArray.length = j2;
                    nativeArray.modCount++;
                    Object[] objArr2 = nativeArray.dense;
                    Object obj = objArr2[(int) j2];
                    objArr2[(int) j2] = NOT_FOUND;
                    return obj;
                }
            }
        }
        long lengthProperty = getLengthProperty(context, object);
        if (lengthProperty > 0) {
            lengthProperty--;
            elem = getElem(context, object, lengthProperty);
            deleteElem(object, lengthProperty);
        } else {
            elem = Undefined.instance;
        }
        setLengthProperty(context, object, lengthProperty);
        return elem;
    }

    private static Object js_shift(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Object elem;
        Scriptable object = ScriptRuntime.toObject(context, scriptable, scriptable2);
        if (object instanceof NativeArray) {
            NativeArray nativeArray = (NativeArray) object;
            if (nativeArray.denseOnly) {
                long j = nativeArray.length;
                if (j > 0) {
                    long j2 = j - 1;
                    nativeArray.length = j2;
                    nativeArray.modCount++;
                    Object[] objArr2 = nativeArray.dense;
                    Object obj = objArr2[0];
                    System.arraycopy(objArr2, 1, objArr2, 0, (int) j2);
                    nativeArray.dense[(int) nativeArray.length] = NOT_FOUND;
                    return obj == NOT_FOUND ? Undefined.instance : obj;
                }
            }
        }
        long lengthProperty = getLengthProperty(context, object);
        if (lengthProperty > 0) {
            lengthProperty--;
            elem = getElem(context, object, 0L);
            if (lengthProperty > 0) {
                for (long j3 = 1; j3 <= lengthProperty; j3++) {
                    setRawElem(context, object, j3 - 1, ArrayLikeAbstractOperations.getRawElem(object, j3));
                }
            }
            deleteElem(object, lengthProperty);
        } else {
            elem = Undefined.instance;
        }
        setLengthProperty(context, object, lengthProperty);
        return elem;
    }

    private static Object js_unshift(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        long j;
        Scriptable object = ScriptRuntime.toObject(context, scriptable, scriptable2);
        int i = 0;
        if (object instanceof NativeArray) {
            NativeArray nativeArray = (NativeArray) object;
            if (nativeArray.denseOnly && nativeArray.ensureCapacity(((int) nativeArray.length) + objArr.length)) {
                Object[] objArr2 = nativeArray.dense;
                System.arraycopy(objArr2, 0, objArr2, objArr.length, (int) nativeArray.length);
                System.arraycopy(objArr, 0, nativeArray.dense, 0, objArr.length);
                long length = nativeArray.length + ((long) objArr.length);
                nativeArray.length = length;
                nativeArray.modCount++;
                return ScriptRuntime.wrapNumber(length);
            }
        }
        long lengthProperty = getLengthProperty(context, object);
        int length2 = objArr.length;
        if (length2 > 0) {
            long j2 = length2;
            long j3 = lengthProperty + j2;
            if (j3 > 9.007199254740991E15d) {
                throw ScriptRuntime.typeErrorById("msg.arraylength.too.big", Long.valueOf(j3));
            }
            if (lengthProperty > 0) {
                long j4 = lengthProperty - 1;
                while (j4 >= 0) {
                    setRawElem(context, object, j4 + j2, ArrayLikeAbstractOperations.getRawElem(object, j4));
                    j4--;
                    i = i;
                    lengthProperty = lengthProperty;
                }
            }
            j = lengthProperty;
            for (int i2 = i; i2 < objArr.length; i2++) {
                setElem(context, object, i2, objArr[i2]);
            }
        } else {
            j = lengthProperty;
        }
        return setLengthProperty(context, object, j + ((long) length2));
    }

    private static Object js_splice(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        NativeArray nativeArray;
        boolean z;
        int i;
        long j;
        boolean z2;
        long j2;
        Scriptable scriptableNewArray;
        Object obj;
        Object obj2;
        Scriptable scriptableNewArray2;
        Object elem;
        Scriptable object = ScriptRuntime.toObject(context, scriptable, scriptable2);
        if (object instanceof NativeArray) {
            nativeArray = (NativeArray) object;
            z = nativeArray.denseOnly;
        } else {
            nativeArray = null;
            z = false;
        }
        Scriptable topLevelScope = getTopLevelScope(scriptable);
        int length = objArr.length;
        if (length == 0) {
            return context.newArray(topLevelScope, 0);
        }
        long lengthProperty = getLengthProperty(context, object);
        long sliceIndex = ArrayLikeAbstractOperations.toSliceIndex(ScriptRuntime.toInteger(objArr[0]), lengthProperty);
        int i2 = length - 1;
        if (objArr.length == 1) {
            i = 1;
            j = lengthProperty - sliceIndex;
            z2 = z;
        } else {
            double integer = ScriptRuntime.toInteger(objArr[1]);
            if (integer < 0.0d) {
                z2 = z;
                i = 1;
                j = 0;
            } else {
                i = 1;
                j = lengthProperty - sliceIndex;
                z2 = z;
                if (integer <= j) {
                    j = (long) integer;
                }
            }
            i2 = length - 2;
        }
        long j3 = sliceIndex + j;
        long j4 = j;
        long j5 = i2;
        long j6 = j5 - j4;
        long j7 = lengthProperty + j6;
        if (j7 > 9.007199254740991E15d) {
            Object[] objArr2 = new Object[i];
            objArr2[0] = Long.valueOf(j7);
            throw ScriptRuntime.typeErrorById("msg.arraylength.too.big", objArr2);
        }
        if (j4 > SieveCacheKt.NodeLinkMask) {
            throw ScriptRuntime.rangeError(ScriptRuntime.getMessageById("msg.arraylength.bad", new Object[0]));
        }
        if (j4 != 0) {
            if (j4 == 1 && context.getLanguageVersion() == 120) {
                elem = getElem(context, object, sliceIndex);
                j2 = 2147483647L;
            } else if (z2) {
                j2 = 2147483647L;
                int i3 = (int) (j3 - sliceIndex);
                Object[] objArr3 = new Object[i3];
                System.arraycopy(nativeArray.dense, (int) sliceIndex, objArr3, 0, i3);
                scriptableNewArray2 = context.newArray(topLevelScope, objArr3);
            } else {
                j2 = 2147483647L;
                Scriptable scriptableNewArray3 = context.newArray(topLevelScope, 0);
                long j8 = sliceIndex;
                while (j8 != j3) {
                    Object rawElem = ArrayLikeAbstractOperations.getRawElem(object, j8);
                    long j9 = j8;
                    if (rawElem != NOT_FOUND) {
                        setElem(context, scriptableNewArray3, j9 - sliceIndex, rawElem);
                    }
                    j8 = j9 + 1;
                }
                setLengthProperty(context, scriptableNewArray3, j3 - sliceIndex);
                obj2 = scriptableNewArray3;
            }
        } else {
            j2 = 2147483647L;
            if (context.getLanguageVersion() == 120) {
                obj = Undefined.instance;
            } else {
                scriptableNewArray = context.newArray(topLevelScope, 0);
            }
        }
        if (z2) {
            obj2 = scriptableNewArray;
            obj2 = obj;
            obj2 = scriptableNewArray2;
            if (j7 < j2) {
                int i4 = (int) j7;
                if (nativeArray.ensureCapacity(i4)) {
                    Object[] objArr4 = nativeArray.dense;
                    System.arraycopy(objArr4, (int) j3, objArr4, (int) (sliceIndex + j5), (int) (lengthProperty - j3));
                    if (i2 > 0) {
                        obj2 = elem;
                        System.arraycopy(objArr, 2, nativeArray.dense, (int) sliceIndex, i2);
                    }
                    obj2 = elem;
                    if (j6 < 0) {
                        Arrays.fill(nativeArray.dense, i4, (int) lengthProperty, NOT_FOUND);
                    }
                    nativeArray.length = j7;
                    nativeArray.modCount++;
                    return obj2;
                }
            }
        }
        obj2 = scriptableNewArray;
        obj2 = obj;
        obj2 = scriptableNewArray2;
        obj2 = elem;
        obj2 = elem;
        obj2 = elem;
        long j10 = lengthProperty;
        if (j6 > 0) {
            long j11 = j10 - 1;
            while (j11 >= j3) {
                setRawElem(context, object, j11 + j6, ArrayLikeAbstractOperations.getRawElem(object, j11));
                j11--;
                j3 = j3;
            }
        } else if (j6 < 0) {
            long j12 = j3;
            while (j12 < j10) {
                long j13 = j12;
                setRawElem(context, object, j13 + j6, ArrayLikeAbstractOperations.getRawElem(object, j12));
                j12 = j13 + 1;
            }
            while (true) {
                j10--;
                if (j10 < j7) {
                    break;
                }
                deleteElem(object, j10);
            }
        }
        int length2 = objArr.length - i2;
        for (int i5 = 0; i5 < i2; i5++) {
            setElem(context, object, ((long) i5) + sliceIndex, objArr[i5 + length2]);
        }
        setLengthProperty(context, object, j7);
        return obj2;
    }

    private static boolean isConcatSpreadable(Context context, Scriptable scriptable, Object obj) {
        Object property;
        if ((obj instanceof Scriptable) && (property = ScriptableObject.getProperty((Scriptable) obj, SymbolKey.IS_CONCAT_SPREADABLE)) != Scriptable.NOT_FOUND && !Undefined.isUndefined(property)) {
            return ScriptRuntime.toBoolean(property);
        }
        if (context.getLanguageVersion() >= 200 || !ScriptRuntime.instanceOf(obj, ScriptRuntime.getExistingCtor(context, scriptable, "Array"), context)) {
            return js_isArray(obj);
        }
        return true;
    }

    private static long concatSpreadArg(Context context, Scriptable scriptable, Scriptable scriptable2, long j) {
        long lengthProperty = getLengthProperty(context, scriptable2);
        long j2 = lengthProperty + j;
        if (j2 > 9.007199254740991E15d) {
            throw ScriptRuntime.typeErrorById("msg.arraylength.too.big", Long.valueOf(j2));
        }
        if (j2 <= SieveCacheKt.NodeLinkMask && (scriptable instanceof NativeArray)) {
            NativeArray nativeArray = (NativeArray) scriptable;
            if (nativeArray.denseOnly && (scriptable2 instanceof NativeArray)) {
                NativeArray nativeArray2 = (NativeArray) scriptable2;
                if (nativeArray2.denseOnly) {
                    nativeArray.ensureCapacity((int) j2);
                    System.arraycopy(nativeArray2.dense, 0, nativeArray.dense, (int) j, (int) lengthProperty);
                    return j2;
                }
            }
        }
        long j3 = 0;
        while (j3 < lengthProperty) {
            Object rawElem = ArrayLikeAbstractOperations.getRawElem(scriptable2, j3);
            if (rawElem != Scriptable.NOT_FOUND) {
                ArrayLikeAbstractOperations.defineElem(context, scriptable, j, rawElem);
            }
            j3++;
            j++;
        }
        return j2;
    }

    private static long doConcat(Context context, Scriptable scriptable, Scriptable scriptable2, Object obj, long j) {
        if (isConcatSpreadable(context, scriptable, obj)) {
            return concatSpreadArg(context, scriptable2, (Scriptable) obj, j);
        }
        ArrayLikeAbstractOperations.defineElem(context, scriptable2, j, obj);
        return j + 1;
    }

    private static Scriptable js_concat(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Scriptable object = ScriptRuntime.toObject(context, scriptable, scriptable2);
        Scriptable topLevelScope = getTopLevelScope(scriptable);
        Scriptable scriptableNewArray = context.newArray(topLevelScope, 0);
        long jDoConcat = doConcat(context, topLevelScope, scriptableNewArray, object, 0L);
        for (Object obj : objArr) {
            jDoConcat = doConcat(context, topLevelScope, scriptableNewArray, obj, jDoConcat);
        }
        setLengthProperty(context, scriptableNewArray, jDoConcat);
        return scriptableNewArray;
    }

    private static Scriptable js_slice(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        long sliceIndex;
        Scriptable object = ScriptRuntime.toObject(context, scriptable, scriptable2);
        long lengthProperty = getLengthProperty(context, object);
        if (objArr.length == 0) {
            sliceIndex = 0;
        } else {
            sliceIndex = ArrayLikeAbstractOperations.toSliceIndex(ScriptRuntime.toInteger(objArr[0]), lengthProperty);
            if (objArr.length != 1 && objArr[1] != Undefined.instance) {
                lengthProperty = ArrayLikeAbstractOperations.toSliceIndex(ScriptRuntime.toInteger(objArr[1]), lengthProperty);
            }
        }
        long j = lengthProperty - sliceIndex;
        if (j > SieveCacheKt.NodeLinkMask) {
            throw ScriptRuntime.rangeError(ScriptRuntime.getMessageById("msg.arraylength.bad", new Object[0]));
        }
        Scriptable scriptableNewArray = context.newArray(scriptable, 0);
        for (long j2 = sliceIndex; j2 < lengthProperty; j2++) {
            Object rawElem = ArrayLikeAbstractOperations.getRawElem(object, j2);
            if (rawElem != NOT_FOUND) {
                ArrayLikeAbstractOperations.defineElem(context, scriptableNewArray, j2 - sliceIndex, rawElem);
            }
        }
        setLengthProperty(context, scriptableNewArray, Math.max(0L, j));
        return scriptableNewArray;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x002c A[PHI: r9
      0x002c: PHI (r9v9 long) = (r9v8 long), (r9v11 long) binds: [B:10:0x0024, B:12:0x0029] A[DONT_GENERATE, DONT_INLINE]] */
    private static Object js_indexOf(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Object obj = objArr.length > 0 ? objArr[0] : Undefined.instance;
        Scriptable object = ScriptRuntime.toObject(context, scriptable, scriptable2);
        long lengthProperty = getLengthProperty(context, object);
        long j = 0;
        if (objArr.length >= 2) {
            long integer = (long) ScriptRuntime.toInteger(objArr[1]);
            if (integer < 0) {
                integer += lengthProperty;
                j = integer >= 0 ? integer : 0L;
            }
            if (j > lengthProperty - 1) {
                return NEGATIVE_ONE;
            }
        }
        if (object instanceof NativeArray) {
            NativeArray nativeArray = (NativeArray) object;
            if (nativeArray.denseOnly) {
                Scriptable prototype = nativeArray.getPrototype();
                int i = (int) j;
                while (true) {
                    long j2 = i;
                    if (j2 < lengthProperty) {
                        Object property = nativeArray.dense[i];
                        if (property == NOT_FOUND && prototype != null) {
                            property = ScriptableObject.getProperty(prototype, i);
                        }
                        if (property != NOT_FOUND && ScriptRuntime.shallowEq(property, obj)) {
                            return Long.valueOf(j2);
                        }
                        i++;
                    } else {
                        return NEGATIVE_ONE;
                    }
                }
            }
        }
        while (j < lengthProperty) {
            Object rawElem = ArrayLikeAbstractOperations.getRawElem(object, j);
            if (rawElem != NOT_FOUND && ScriptRuntime.shallowEq(rawElem, obj)) {
                return Long.valueOf(j);
            }
            j++;
        }
        return NEGATIVE_ONE;
    }

    private static Object js_lastIndexOf(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        long j;
        Object obj = objArr.length > 0 ? objArr[0] : Undefined.instance;
        Scriptable object = ScriptRuntime.toObject(context, scriptable, scriptable2);
        long lengthProperty = getLengthProperty(context, object);
        if (objArr.length < 2) {
            j = lengthProperty - 1;
        } else {
            long integer = (long) ScriptRuntime.toInteger(objArr[1]);
            if (integer >= lengthProperty) {
                j = lengthProperty - 1;
            } else {
                if (integer < 0) {
                    integer += lengthProperty;
                }
                j = integer;
            }
            if (j < 0) {
                return NEGATIVE_ONE;
            }
        }
        if (object instanceof NativeArray) {
            NativeArray nativeArray = (NativeArray) object;
            if (nativeArray.denseOnly) {
                Scriptable prototype = nativeArray.getPrototype();
                for (int i = (int) j; i >= 0; i--) {
                    Object property = nativeArray.dense[i];
                    if (property == NOT_FOUND && prototype != null) {
                        property = ScriptableObject.getProperty(prototype, i);
                    }
                    if (property != NOT_FOUND && ScriptRuntime.shallowEq(property, obj)) {
                        return Long.valueOf(i);
                    }
                }
                return NEGATIVE_ONE;
            }
        }
        while (j >= 0) {
            Object rawElem = ArrayLikeAbstractOperations.getRawElem(object, j);
            if (rawElem != NOT_FOUND && ScriptRuntime.shallowEq(rawElem, obj)) {
                return Long.valueOf(j);
            }
            j--;
        }
        return NEGATIVE_ONE;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x002a A[PHI: r6
      0x002a: PHI (r6v2 long) = (r6v1 long), (r6v4 long) binds: [B:10:0x0022, B:12:0x0027] A[DONT_GENERATE, DONT_INLINE]] */
    private static Boolean js_includes(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Scriptable object = ScriptRuntime.toObject(context, scriptable, scriptable2);
        long lengthProperty = getLengthProperty(context, object);
        long j = 0;
        if (lengthProperty == 0) {
            return Boolean.FALSE;
        }
        if (objArr.length >= 2) {
            long integer = (long) ScriptRuntime.toInteger(objArr[1]);
            if (integer < 0) {
                integer += lengthProperty;
                j = integer >= 0 ? integer : 0L;
            }
            if (j > lengthProperty - 1) {
                return Boolean.FALSE;
            }
        }
        Object obj = objArr.length > 0 ? objArr[0] : Undefined.instance;
        if (object instanceof NativeArray) {
            NativeArray nativeArray = (NativeArray) object;
            if (nativeArray.denseOnly) {
                Scriptable prototype = nativeArray.getPrototype();
                for (int i = (int) j; i < lengthProperty; i++) {
                    Object property = nativeArray.dense[i];
                    if (property == NOT_FOUND && prototype != null) {
                        property = ScriptableObject.getProperty(prototype, i);
                    }
                    if (property == NOT_FOUND) {
                        property = Undefined.instance;
                    }
                    if (ScriptRuntime.sameZero(property, obj)) {
                        return Boolean.TRUE;
                    }
                }
                return Boolean.FALSE;
            }
        }
        while (j < lengthProperty) {
            Object rawElem = ArrayLikeAbstractOperations.getRawElem(object, j);
            if (rawElem == NOT_FOUND) {
                rawElem = Undefined.instance;
            }
            if (ScriptRuntime.sameZero(rawElem, obj)) {
                return Boolean.TRUE;
            }
            j++;
        }
        return Boolean.FALSE;
    }

    private static Object js_fill(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        long jMin;
        long jMin2;
        long lengthProperty = getLengthProperty(context, ScriptRuntime.toObject(context, scriptable, scriptable2));
        long integer = objArr.length >= 2 ? (long) ScriptRuntime.toInteger(objArr[1]) : 0L;
        if (integer < 0) {
            jMin = Math.max(integer + lengthProperty, 0L);
        } else {
            jMin = Math.min(integer, lengthProperty);
        }
        long integer2 = (objArr.length < 3 || Undefined.isUndefined(objArr[2])) ? lengthProperty : (long) ScriptRuntime.toInteger(objArr[2]);
        if (integer2 < 0) {
            jMin2 = Math.max(lengthProperty + integer2, 0L);
        } else {
            jMin2 = Math.min(integer2, lengthProperty);
        }
        Object obj = objArr.length > 0 ? objArr[0] : Undefined.instance;
        while (jMin < jMin2) {
            setRawElem(context, scriptable2, jMin, obj);
            jMin++;
        }
        return scriptable2;
    }

    /* JADX WARN: Code duplicated, block: B:42:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:45:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:51:0x00c1  */
    private static Object js_copyWithin(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        long jMin;
        long jMin2;
        long jMin3;
        Object rawElem;
        Scriptable object = ScriptRuntime.toObject(context, scriptable, scriptable2);
        long lengthProperty = getLengthProperty(context, object);
        int i = 1;
        long integer = (long) ScriptRuntime.toInteger(objArr.length >= 1 ? objArr[0] : Undefined.instance);
        long j = 0;
        if (integer < 0) {
            jMin = Math.max(integer + lengthProperty, 0L);
        } else {
            jMin = Math.min(integer, lengthProperty);
        }
        long integer2 = (long) ScriptRuntime.toInteger(objArr.length >= 2 ? objArr[1] : Undefined.instance);
        if (integer2 < 0) {
            jMin2 = Math.max(integer2 + lengthProperty, 0L);
        } else {
            jMin2 = Math.min(integer2, lengthProperty);
        }
        long integer3 = (objArr.length < 3 || Undefined.isUndefined(objArr[2])) ? lengthProperty : (long) ScriptRuntime.toInteger(objArr[2]);
        if (integer3 < 0) {
            jMin3 = Math.max(integer3 + lengthProperty, 0L);
        } else {
            jMin3 = Math.min(integer3, lengthProperty);
        }
        long jMin4 = Math.min(jMin3 - jMin2, lengthProperty - jMin);
        if (jMin2 < jMin) {
            long j2 = jMin2 + jMin4;
            if (jMin < j2) {
                jMin2 = j2 - 1;
                jMin = (jMin + jMin4) - 1;
                i = -1;
            }
        }
        if (!(object instanceof NativeArray) || jMin4 > SieveCacheKt.NodeLinkMask) {
            while (jMin4 > 0) {
                rawElem = ArrayLikeAbstractOperations.getRawElem(object, jMin2);
                if (rawElem != Scriptable.NOT_FOUND || Undefined.isUndefined(rawElem)) {
                    deleteElem(object, jMin);
                } else {
                    setElem(context, object, jMin, rawElem);
                }
                long j3 = i;
                jMin2 += j3;
                jMin += j3;
                jMin4--;
            }
        } else {
            NativeArray nativeArray = (NativeArray) object;
            if (nativeArray.denseOnly) {
                while (jMin4 > j) {
                    Object[] objArr2 = nativeArray.dense;
                    objArr2[(int) jMin] = objArr2[(int) jMin2];
                    long j4 = j;
                    long j5 = i;
                    jMin2 += j5;
                    jMin += j5;
                    jMin4--;
                    j = j4;
                }
            } else {
                while (jMin4 > 0) {
                    rawElem = ArrayLikeAbstractOperations.getRawElem(object, jMin2);
                    if (rawElem != Scriptable.NOT_FOUND) {
                        deleteElem(object, jMin);
                    } else {
                        deleteElem(object, jMin);
                    }
                    long j6 = i;
                    jMin2 += j6;
                    jMin += j6;
                    jMin4--;
                }
            }
        }
        return scriptable2;
    }

    private static Object js_at(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        long lengthProperty = getLengthProperty(context, ScriptRuntime.toObject(context, scriptable, scriptable2));
        long integer = objArr.length >= 1 ? (long) ScriptRuntime.toInteger(objArr[0]) : 0L;
        if (integer < 0) {
            integer += lengthProperty;
        }
        if (integer < 0 || integer >= lengthProperty) {
            return Undefined.instance;
        }
        return getElem(context, scriptable2, integer);
    }

    private static Object js_flat(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return flat(context, scriptable, ScriptRuntime.toObject(context, scriptable, scriptable2), (objArr.length < 1 || Undefined.isUndefined(objArr[0])) ? 1.0d : ScriptRuntime.toInteger(objArr[0]));
    }

    private static Scriptable flat(Context context, Scriptable scriptable, Scriptable scriptable2, double d) {
        long j;
        long lengthProperty = getLengthProperty(context, scriptable2);
        Scriptable scriptableNewArray = context.newArray(scriptable, 0);
        long j2 = 0;
        long j3 = 0;
        while (j2 < lengthProperty) {
            Object rawElem = ArrayLikeAbstractOperations.getRawElem(scriptable2, j2);
            if (rawElem == Scriptable.NOT_FOUND) {
                j = 1;
            } else if (d >= 1.0d && js_isArray(rawElem)) {
                Scriptable scriptableFlat = flat(context, scriptable, (Scriptable) rawElem, d - 1.0d);
                long lengthProperty2 = getLengthProperty(context, scriptableFlat);
                long j4 = 0;
                j = 1;
                while (j4 < lengthProperty2) {
                    defineElemOrThrow(context, scriptableNewArray, j3, ArrayLikeAbstractOperations.getRawElem(scriptableFlat, j4));
                    j4++;
                    j3++;
                }
            } else {
                j = 1;
                defineElemOrThrow(context, scriptableNewArray, j3, rawElem);
                j3++;
            }
            j2 += j;
        }
        setLengthProperty(context, scriptableNewArray, j3);
        return scriptableNewArray;
    }

    private static Object js_flatMap(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Function function;
        Object obj;
        Scriptable object = ScriptRuntime.toObject(context, scriptable, scriptable2);
        char c = 0;
        Function callbackArg = ArrayLikeAbstractOperations.getCallbackArg(context, objArr.length > 0 ? objArr[0] : Undefined.instance);
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(callbackArg);
        Scriptable object2 = (objArr.length < 2 || (obj = objArr[1]) == null || obj == Undefined.instance) ? topLevelScope : ScriptRuntime.toObject(context, scriptable, objArr[1]);
        long lengthProperty = getLengthProperty(context, object);
        Scriptable scriptableNewArray = context.newArray(scriptable, 0);
        char c2 = 1;
        long j = 0;
        long j2 = 0;
        while (j2 < lengthProperty) {
            char c3 = c;
            Object rawElem = ArrayLikeAbstractOperations.getRawElem(object, j2);
            char c4 = c2;
            if (rawElem != Scriptable.NOT_FOUND) {
                Long lValueOf = Long.valueOf(j2);
                Object[] objArr2 = new Object[3];
                objArr2[c3] = rawElem;
                objArr2[c4] = lValueOf;
                objArr2[2] = object;
                Object objCall = callbackArg.call(context, topLevelScope, object2, objArr2);
                if (js_isArray(objCall)) {
                    Scriptable scriptable3 = (Scriptable) objCall;
                    long lengthProperty2 = getLengthProperty(context, scriptable3);
                    long j3 = 0;
                    while (j3 < lengthProperty2) {
                        defineElemOrThrow(context, scriptableNewArray, j, ArrayLikeAbstractOperations.getRawElem(scriptable3, j3));
                        j3++;
                        callbackArg = callbackArg;
                        j++;
                    }
                } else {
                    object2 = object2;
                    object = object;
                    function = callbackArg;
                    defineElemOrThrow(context, scriptableNewArray, j, objCall);
                    j++;
                }
                j2++;
                object2 = object2;
                object = object;
                c = c3;
                callbackArg = function;
                c2 = c4;
            }
            function = callbackArg;
            j2++;
            object2 = object2;
            object = object;
            c = c3;
            callbackArg = function;
            c2 = c4;
        }
        setLengthProperty(context, scriptableNewArray, j);
        return scriptableNewArray;
    }

    private static boolean js_isArray(Object obj) {
        if (!(obj instanceof Scriptable)) {
            return false;
        }
        if (obj instanceof NativeProxy) {
            return js_isArray(((NativeProxy) obj).getTargetThrowIfRevoked());
        }
        return "Array".equals(((Scriptable) obj).getClassName());
    }

    private static Object js_toSorted(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Comparator<Object> sortComparator = ArrayLikeAbstractOperations.getSortComparator(context, scriptable, objArr);
        Scriptable object = ScriptRuntime.toObject(context, scriptable, scriptable2);
        long lengthProperty = getLengthProperty(context, object);
        int i = 0;
        if (lengthProperty > SieveCacheKt.NodeLinkMask) {
            throw ScriptRuntime.rangeError(ScriptRuntime.getMessageById("msg.arraylength.bad", new Object[0]));
        }
        Scriptable scriptableNewArray = context.newArray(scriptable, (int) lengthProperty);
        while (true) {
            long j = i;
            if (j < lengthProperty) {
                setElem(context, scriptableNewArray, j, getElem(context, object, j));
                i++;
            } else {
                sort(context, scriptableNewArray, sortComparator);
                return scriptableNewArray;
            }
        }
    }

    private static Object js_toReversed(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Scriptable object = ScriptRuntime.toObject(context, scriptable, scriptable2);
        long lengthProperty = getLengthProperty(context, object);
        int i = 0;
        if (lengthProperty > SieveCacheKt.NodeLinkMask) {
            throw ScriptRuntime.rangeError(ScriptRuntime.getMessageById("msg.arraylength.bad", new Object[0]));
        }
        int i2 = (int) lengthProperty;
        Scriptable scriptableNewArray = context.newArray(scriptable, i2);
        while (true) {
            long j = i;
            if (j >= lengthProperty) {
                return scriptableNewArray;
            }
            setElem(context, scriptableNewArray, j, getElem(context, object, (i2 - i) - 1));
            i++;
        }
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0053  */
    /* JADX WARN: Code duplicated, block: B:22:0x005a  */
    /* JADX WARN: Code duplicated, block: B:25:0x0068 A[LOOP:0: B:23:0x0062->B:25:0x0068, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:29:0x0075 A[LOOP:1: B:27:0x0072->B:29:0x0075, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:33:0x0083 A[LOOP:2: B:31:0x007f->B:33:0x0083, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:35:0x008e  */
    /* JADX WARN: Code duplicated, block: B:37:0x009b  */
    private static Object js_toSpliced(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        long j;
        long jMax;
        long j2;
        Scriptable scriptableNewArray;
        int i;
        long j3;
        Scriptable object = ScriptRuntime.toObject(context, scriptable, scriptable2);
        long lengthProperty = getLengthProperty(context, object);
        long j4 = 0;
        long sliceIndex = objArr.length > 0 ? ArrayLikeAbstractOperations.toSliceIndex(ScriptRuntime.toInteger(objArr[0]), lengthProperty) : 0L;
        long length = objArr.length > 2 ? objArr.length - 2 : 0L;
        if (objArr.length != 0) {
            if (objArr.length == 1) {
                jMax = lengthProperty - sliceIndex;
            } else {
                j = length;
                jMax = Math.max(0L, Math.min(ScriptRuntime.toLength(objArr, 1), lengthProperty - sliceIndex));
            }
            j2 = (lengthProperty + j) - jMax;
            if (j2 <= 9.007199254740991E15d) {
                throw ScriptRuntime.typeErrorById("msg.arraylength.too.big", Long.valueOf(j2));
            }
            if (j2 <= SieveCacheKt.NodeLinkMask) {
                throw ScriptRuntime.rangeError(ScriptRuntime.getMessageById("msg.arraylength.bad", new Object[0]));
            }
            scriptableNewArray = context.newArray(scriptable, (int) j2);
            long j5 = jMax + sliceIndex;
            while (j4 < sliceIndex) {
                setElem(context, scriptableNewArray, j4, getElem(context, object, j4));
                j4++;
            }
            for (i = 2; i < objArr.length; i++) {
                setElem(context, scriptableNewArray, j4, objArr[i]);
                j4++;
            }
            j3 = j5;
            while (j4 < j2) {
                setElem(context, scriptableNewArray, j4, getElem(context, object, j3));
                j4++;
                j3++;
            }
            return scriptableNewArray;
        }
        jMax = 0;
        j = length;
        j2 = (lengthProperty + j) - jMax;
        if (j2 <= 9.007199254740991E15d) {
            throw ScriptRuntime.typeErrorById("msg.arraylength.too.big", Long.valueOf(j2));
        }
        if (j2 <= SieveCacheKt.NodeLinkMask) {
            throw ScriptRuntime.rangeError(ScriptRuntime.getMessageById("msg.arraylength.bad", new Object[0]));
        }
        scriptableNewArray = context.newArray(scriptable, (int) j2);
        long j6 = jMax + sliceIndex;
        while (j4 < sliceIndex) {
            setElem(context, scriptableNewArray, j4, getElem(context, object, j4));
            j4++;
        }
        while (i < objArr.length) {
            setElem(context, scriptableNewArray, j4, objArr[i]);
            j4++;
        }
        j3 = j6;
        while (j4 < j2) {
            setElem(context, scriptableNewArray, j4, getElem(context, object, j3));
            j4++;
            j3++;
        }
        return scriptableNewArray;
    }

    private static Object js_with(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Object elem;
        Scriptable object = ScriptRuntime.toObject(context, scriptable, scriptable2);
        long lengthProperty = getLengthProperty(context, object);
        long integer = objArr.length > 0 ? (int) ScriptRuntime.toInteger(objArr[0]) : 0L;
        if (integer < 0) {
            integer += lengthProperty;
        }
        if (integer < 0 || integer >= lengthProperty) {
            throw ScriptRuntime.rangeError("index out of range");
        }
        if (lengthProperty > SieveCacheKt.NodeLinkMask) {
            throw ScriptRuntime.rangeError(ScriptRuntime.getMessageById("msg.arraylength.bad", new Object[0]));
        }
        Scriptable scriptableNewArray = context.newArray(scriptable, (int) lengthProperty);
        for (long j = 0; j < lengthProperty; j++) {
            if (j == integer) {
                elem = objArr.length > 1 ? objArr[1] : Undefined.instance;
            } else {
                elem = getElem(context, object, j);
            }
            setElem(context, scriptableNewArray, j, elem);
        }
        return scriptableNewArray;
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object obj) {
        return indexOf(obj) > -1;
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        return toArray(ScriptRuntime.emptyArgs);
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray(Object[] objArr) {
        int size = size();
        if (objArr.length < size) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), size);
        }
        for (int i = 0; i < size; i++) {
            objArr[i] = get(i);
        }
        return objArr;
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // org.mozilla.javascript.ScriptableObject, java.util.List, java.util.Collection
    public int size() {
        long j = this.length;
        if (j <= SieveCacheKt.NodeLinkMask) {
            return (int) j;
        }
        throw new IllegalStateException("list.length (" + this.length + ") exceeds Integer.MAX_VALUE");
    }

    @Override // org.mozilla.javascript.ScriptableObject, java.util.List, java.util.Collection
    public boolean isEmpty() {
        return this.length == 0;
    }

    public Object get(long j) {
        if (j < 0 || j >= this.length) {
            throw new IndexOutOfBoundsException();
        }
        Object rawElem = ArrayLikeAbstractOperations.getRawElem(this, j);
        if (rawElem == Scriptable.NOT_FOUND || rawElem == Undefined.instance) {
            return null;
        }
        return rawElem instanceof Wrapper ? ((Wrapper) rawElem).unwrap() : rawElem;
    }

    @Override // java.util.List
    public Object get(int i) {
        return get(i);
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        int size = size();
        int i = 0;
        if (obj == null) {
            while (i < size) {
                if (get(i) == null) {
                    return i;
                }
                i++;
            }
            return -1;
        }
        while (i < size) {
            if (obj.equals(get(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        int size = size();
        if (obj == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (get(i) == null) {
                    return i;
                }
            }
            return -1;
        }
        for (int i2 = size - 1; i2 >= 0; i2--) {
            if (obj.equals(get(i2))) {
                return i2;
            }
        }
        return -1;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public ListIterator listIterator(int i) {
        int size = size();
        if (i < 0 || i > size) {
            throw new IndexOutOfBoundsException("Index: " + i);
        }
        return new ListIterator(i, size) { // from class: org.mozilla.javascript.NativeArray.1
            int cursor;
            int modCount;
            final /* synthetic */ int val$len;
            final /* synthetic */ int val$start;

            {
                this.val$start = i;
                this.val$len = size;
                this.cursor = i;
                this.modCount = NativeArray.this.modCount;
            }

            @Override // java.util.ListIterator, java.util.Iterator
            public boolean hasNext() {
                return this.cursor < this.val$len;
            }

            @Override // java.util.ListIterator, java.util.Iterator
            public Object next() {
                NativeArray.this.checkModCount(this.modCount);
                int i2 = this.cursor;
                if (i2 == this.val$len) {
                    throw new NoSuchElementException();
                }
                NativeArray nativeArray = NativeArray.this;
                this.cursor = i2 + 1;
                return nativeArray.get(i2);
            }

            @Override // java.util.ListIterator
            public boolean hasPrevious() {
                return this.cursor > 0;
            }

            @Override // java.util.ListIterator
            public Object previous() {
                NativeArray.this.checkModCount(this.modCount);
                int i2 = this.cursor;
                if (i2 == 0) {
                    throw new NoSuchElementException();
                }
                NativeArray nativeArray = NativeArray.this;
                int i3 = i2 - 1;
                this.cursor = i3;
                return nativeArray.get(i3);
            }

            @Override // java.util.ListIterator
            public int nextIndex() {
                return this.cursor;
            }

            @Override // java.util.ListIterator
            public int previousIndex() {
                return this.cursor - 1;
            }

            @Override // java.util.ListIterator, java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.ListIterator
            public void add(Object obj) {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.ListIterator
            public void set(Object obj) {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public void add(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public Object set(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public Object remove(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public List subList(final int i, final int i2) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("fromIndex = " + i);
        }
        if (i2 > size()) {
            throw new IndexOutOfBoundsException("toIndex = " + i2);
        }
        if (i > i2) {
            throw new IllegalArgumentException("fromIndex(" + i + ") > toIndex(" + i2 + ")");
        }
        return new AbstractList() { // from class: org.mozilla.javascript.NativeArray.2
            private int mc;

            {
                this.mc = NativeArray.this.modCount;
            }

            @Override // java.util.AbstractList, java.util.List
            public Object get(int i3) {
                NativeArray.this.checkModCount(this.mc);
                return NativeArray.this.get(i3 + i);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public int size() {
                NativeArray.this.checkModCount(this.mc);
                return i2 - i;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkModCount(int i) {
        if (this.modCount != i) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(Symbol symbol) {
        if (SymbolKey.ITERATOR.equals(symbol)) {
            return 30;
        }
        return SymbolKey.UNSCOPABLES.equals(symbol) ? 41 : 0;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(String str) {
        str.hashCode();
        byte b = -1;
        switch (str.hashCode()) {
            case -2059379165:
                if (str.equals("findLastIndex")) {
                    b = 0;
                }
                break;
            case -2020003546:
                if (str.equals("toLocaleString")) {
                    b = 1;
                }
                break;
            case -1781529320:
                if (str.equals("toSorted")) {
                    b = 2;
                }
                break;
            case -1781441930:
                if (str.equals("toSource")) {
                    b = 3;
                }
                break;
            case -1776922004:
                if (str.equals("toString")) {
                    b = 4;
                }
                break;
            case -1591573360:
                if (str.equals("entries")) {
                    b = 5;
                }
                break;
            case -1588406278:
                if (str.equals("constructor")) {
                    b = 6;
                }
                break;
            case -1354795244:
                if (str.equals("concat")) {
                    b = 7;
                }
                break;
            case -1274492040:
                if (str.equals("filter")) {
                    b = 8;
                }
                break;
            case -934873754:
                if (str.equals("reduce")) {
                    b = 9;
                }
                break;
            case -895859076:
                if (str.equals("splice")) {
                    b = 10;
                }
                break;
            case -823812830:
                if (str.equals("values")) {
                    b = 11;
                }
                break;
            case -807660480:
                if (str.equals("copyWithin")) {
                    b = 12;
                }
                break;
            case -778842333:
                if (str.equals("flatMap")) {
                    b = 13;
                }
                break;
            case -679446545:
                if (str.equals("findLast")) {
                    b = 14;
                }
                break;
            case -678635926:
                if (str.equals("forEach")) {
                    b = 15;
                }
                break;
            case -467511597:
                if (str.equals("lastIndexOf")) {
                    b = 16;
                }
                break;
            case -284569251:
                if (str.equals("toReversed")) {
                    b = 17;
                }
                break;
            case -277637751:
                if (str.equals("unshift")) {
                    b = 18;
                }
                break;
            case 3123:
                if (str.equals("at")) {
                    b = 19;
                }
                break;
            case 107868:
                if (str.equals("map")) {
                    b = 20;
                }
                break;
            case 111185:
                if (str.equals("pop")) {
                    b = 21;
                }
                break;
            case 3143043:
                if (str.equals("fill")) {
                    b = Ascii.SYN;
                }
                break;
            case 3143097:
                if (str.equals("find")) {
                    b = Ascii.ETB;
                }
                break;
            case 3145593:
                if (str.equals("flat")) {
                    b = Ascii.CAN;
                }
                break;
            case 3267882:
                if (str.equals("join")) {
                    b = Ascii.EM;
                }
                break;
            case 3288564:
                if (str.equals("keys")) {
                    b = Ascii.SUB;
                }
                break;
            case 3452698:
                if (str.equals("push")) {
                    b = Ascii.ESC;
                }
                break;
            case 3536116:
                if (str.equals("some")) {
                    b = Ascii.FS;
                }
                break;
            case 3536286:
                if (str.equals("sort")) {
                    b = Ascii.GS;
                }
                break;
            case 3649734:
                if (str.equals(JsonPOJOBuilder.DEFAULT_WITH_PREFIX)) {
                    b = Ascii.RS;
                }
                break;
            case 90259659:
                if (str.equals("includes")) {
                    b = Ascii.US;
                }
                break;
            case 96891675:
                if (str.equals("every")) {
                    b = 32;
                }
                break;
            case 109407362:
                if (str.equals("shift")) {
                    b = 33;
                }
                break;
            case 109526418:
                if (str.equals("slice")) {
                    b = 34;
                }
                break;
            case 409595545:
                if (str.equals("findIndex")) {
                    b = 35;
                }
                break;
            case 629924461:
                if (str.equals("toSpliced")) {
                    b = 36;
                }
                break;
            case 965561430:
                if (str.equals("reduceRight")) {
                    b = 37;
                }
                break;
            case 1099846370:
                if (str.equals("reverse")) {
                    b = 38;
                }
                break;
            case 1943291465:
                if (str.equals("indexOf")) {
                    b = 39;
                }
                break;
        }
        switch (b) {
            case 0:
                return 25;
            case 1:
                return 3;
            case 2:
                return 38;
            case 3:
                return 4;
            case 4:
                return 2;
            case 5:
                return 31;
            case 6:
                return 1;
            case 7:
                return 13;
            case 8:
                return 18;
            case 9:
                return 26;
            case 10:
                return 12;
            case 11:
                return 30;
            case 12:
                return 33;
            case 13:
                return 36;
            case 14:
                return 24;
            case 15:
                return 19;
            case 16:
                return 16;
            case 17:
                return 37;
            case 18:
                return 11;
            case 19:
                return 34;
            case 20:
                return 20;
            case 21:
                return 9;
            case 22:
                return 28;
            case 23:
                return 22;
            case 24:
                return 35;
            case 25:
                return 5;
            case 26:
                return 29;
            case 27:
                return 8;
            case 28:
                return 21;
            case 29:
                return 7;
            case 30:
                return 40;
            case 31:
                return 32;
            case 32:
                return 17;
            case 33:
                return 10;
            case 34:
                return 14;
            case 35:
                return 23;
            case 36:
                return 39;
            case 37:
                return 27;
            case 38:
                return 6;
            case 39:
                return 15;
            default:
                return 0;
        }
    }
}
