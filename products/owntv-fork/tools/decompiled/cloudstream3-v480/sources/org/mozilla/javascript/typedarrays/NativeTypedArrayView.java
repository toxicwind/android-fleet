package org.mozilla.javascript.typedarrays;

import androidx.collection.SieveCacheKt;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import j$.util.Optional;
import j$.util.function.Function$CC;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.mozilla.javascript.AbstractEcmaObjectOperations;
import org.mozilla.javascript.ArrayLikeAbstractOperations;
import org.mozilla.javascript.Callable;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ExternalArrayData;
import org.mozilla.javascript.LambdaConstructor;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeArrayIterator;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.SymbolKey;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.Wrapper;

/* JADX INFO: loaded from: classes8.dex */
public abstract class NativeTypedArrayView<T> extends NativeArrayBufferView implements List<T>, RandomAccess, ExternalArrayData {
    private static final long serialVersionUID = -4963053773152251274L;
    protected final int length;

    protected interface RealThis {
        NativeTypedArrayView<?> realThis(Scriptable scriptable);
    }

    protected interface TypedArrayConstructable {
        NativeTypedArrayView<?> construct(NativeArrayBuffer nativeArrayBuffer, int i, int i2);
    }

    private static int toIndex(double d) {
        int i = (int) d;
        if (i != d || i < 0) {
            return -1;
        }
        return i;
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void delete(int i) {
    }

    public abstract int getBytesPerElement();

    protected abstract Object js_get(int i);

    protected abstract Object js_set(int i, Object obj);

    protected NativeTypedArrayView() {
        this.length = 0;
    }

    protected NativeTypedArrayView(NativeArrayBuffer nativeArrayBuffer, int i, int i2, int i3) {
        super(nativeArrayBuffer, i, i3);
        this.length = i2;
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public Object get(int i, Scriptable scriptable) {
        return js_get(i);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public Object get(String str, Scriptable scriptable) {
        int index;
        Optional<Double> optionalCanonicalNumericIndexString = ScriptRuntime.canonicalNumericIndexString(str);
        if (optionalCanonicalNumericIndexString.isPresent() && (index = toIndex(optionalCanonicalNumericIndexString.get().doubleValue())) >= 0) {
            return js_get(index);
        }
        return super.get(str, scriptable);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public boolean has(int i, Scriptable scriptable) {
        return !checkIndex(i);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public boolean has(String str, Scriptable scriptable) {
        int index;
        Optional<Double> optionalCanonicalNumericIndexString = ScriptRuntime.canonicalNumericIndexString(str);
        if (optionalCanonicalNumericIndexString.isPresent() && (index = toIndex(optionalCanonicalNumericIndexString.get().doubleValue())) >= 0) {
            return !checkIndex(index);
        }
        return super.has(str, scriptable);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void put(int i, Scriptable scriptable, Object obj) {
        js_set(i, obj);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void put(String str, Scriptable scriptable, Object obj) {
        Optional<Double> optionalCanonicalNumericIndexString = ScriptRuntime.canonicalNumericIndexString(str);
        if (optionalCanonicalNumericIndexString.isPresent()) {
            int index = toIndex(optionalCanonicalNumericIndexString.get().doubleValue());
            if (index >= 0) {
                js_set(index, obj);
                return;
            }
            return;
        }
        super.put(str, scriptable, obj);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void delete(String str) {
        if (ScriptRuntime.canonicalNumericIndexString(str).isPresent()) {
            return;
        }
        super.delete(str);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public Object[] getIds() {
        Object[] objArr = new Object[this.length];
        for (int i = 0; i < this.length; i++) {
            objArr[i] = Integer.valueOf(i);
        }
        return objArr;
    }

    static void init(Context context, Scriptable scriptable, LambdaConstructor lambdaConstructor, final RealThis realThis) {
        lambdaConstructor.definePrototypeProperty(context, "buffer", new Function() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda11
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return NativeTypedArrayView.js_buffer((Scriptable) obj, realThis);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }, 3);
        lambdaConstructor.definePrototypeProperty(context, "byteLength", new Function() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return NativeTypedArrayView.js_byteLength((Scriptable) obj, realThis);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }, 3);
        lambdaConstructor.definePrototypeProperty(context, "byteOffset", new Function() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda15
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return NativeTypedArrayView.js_byteOffset((Scriptable) obj, realThis);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }, 3);
        lambdaConstructor.definePrototypeProperty(context, "length", new Function() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda23
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return NativeTypedArrayView.js_length((Scriptable) obj, realThis);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "at", 1, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda24
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeTypedArrayView.js_at(context2, scriptable2, scriptable3, objArr, realThis);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "copyWithin", 1, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda25
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeTypedArrayView.js_copyWithin(context2, scriptable2, scriptable3, objArr, realThis);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "entries", 0, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda26
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeTypedArrayView.lambda$init$6(realThis, context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "every", 1, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda27
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return ArrayLikeAbstractOperations.iterativeMethod(context2, ArrayLikeAbstractOperations.IterativeOperation.EVERY, scriptable2, realThis.realThis(scriptable3), objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "fill", 1, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda28
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeTypedArrayView.js_fill(context2, scriptable2, scriptable3, objArr, realThis);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "filter", 1, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda29
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeTypedArrayView.lambda$init$9(realThis, context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "find", 1, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda22
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return ArrayLikeAbstractOperations.iterativeMethod(context2, ArrayLikeAbstractOperations.IterativeOperation.FIND, scriptable2, realThis.realThis(scriptable3), objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "findIndex", 1, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda30
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return ArrayLikeAbstractOperations.iterativeMethod(context2, ArrayLikeAbstractOperations.IterativeOperation.FIND_INDEX, scriptable2, realThis.realThis(scriptable3), objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "findLast", 1, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda31
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return ArrayLikeAbstractOperations.iterativeMethod(context2, ArrayLikeAbstractOperations.IterativeOperation.FIND_LAST, scriptable2, realThis.realThis(scriptable3), objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "findLastIndex", 1, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda32
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return ArrayLikeAbstractOperations.iterativeMethod(context2, ArrayLikeAbstractOperations.IterativeOperation.FIND_LAST_INDEX, scriptable2, realThis.realThis(scriptable3), objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "forEach", 1, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda33
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return ArrayLikeAbstractOperations.iterativeMethod(context2, ArrayLikeAbstractOperations.IterativeOperation.FOR_EACH, scriptable2, realThis.realThis(scriptable3), objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "includes", 1, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda34
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeTypedArrayView.js_includes(context2, scriptable2, scriptable3, objArr, realThis);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "indexOf", 1, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda35
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeTypedArrayView.js_indexOf(context2, scriptable2, scriptable3, objArr, realThis);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "join", 1, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda36
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeTypedArrayView.js_join(context2, scriptable2, scriptable3, objArr, realThis);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "keys", 0, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda1
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeTypedArrayView.lambda$init$18(realThis, context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "lastIndexOf", 1, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda2
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeTypedArrayView.js_lastIndexOf(context2, scriptable2, scriptable3, objArr, realThis);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "map", 1, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda4
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return realThis.realThis(scriptable3).typedArraySpeciesCreate(context2, scriptable2, new Object[]{ArrayLikeAbstractOperations.iterativeMethod(context2, ArrayLikeAbstractOperations.IterativeOperation.MAP, scriptable2, scriptable3, objArr)}, "map");
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "reduce", 1, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda5
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return ArrayLikeAbstractOperations.reduceMethod(context2, ArrayLikeAbstractOperations.ReduceOperation.REDUCE, scriptable2, realThis.realThis(scriptable3), objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "reduceRight", 1, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda6
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return ArrayLikeAbstractOperations.reduceMethod(context2, ArrayLikeAbstractOperations.ReduceOperation.REDUCE_RIGHT, scriptable2, realThis.realThis(scriptable3), objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "reverse", 0, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda7
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeTypedArrayView.js_reverse(context2, scriptable2, scriptable3, objArr, realThis);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "set", 0, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda8
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeTypedArrayView.js_set(context2, scriptable2, scriptable3, objArr, realThis);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "slice", 2, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda9
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeTypedArrayView.js_slice(context2, scriptable2, scriptable3, objArr, realThis);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "some", 1, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda10
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return ArrayLikeAbstractOperations.iterativeMethod(context2, ArrayLikeAbstractOperations.IterativeOperation.SOME, scriptable2, realThis.realThis(scriptable3), objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "sort", 1, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda12
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeTypedArrayView.js_sort(context2, scriptable2, scriptable3, objArr, realThis);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "subarray", 2, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda13
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeTypedArrayView.js_subarray(context2, scriptable2, scriptable3, objArr, realThis);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "toLocaleString", 0, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda14
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeTypedArrayView.js_toString(context2, scriptable2, scriptable3, objArr, realThis, true);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "toReversed", 0, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda16
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeTypedArrayView.js_toReversed(context2, scriptable2, scriptable3, objArr, realThis);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "toSorted", 1, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda17
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeTypedArrayView.js_toSorted(context2, scriptable2, scriptable3, objArr, realThis);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "toString", 0, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda18
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeTypedArrayView.js_toString(context2, scriptable2, scriptable3, objArr, realThis, false);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "values", 0, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda19
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeTypedArrayView.lambda$init$33(realThis, context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, JsonPOJOBuilder.DEFAULT_WITH_PREFIX, 2, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda20
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeTypedArrayView.js_with(context2, scriptable2, scriptable3, objArr, realThis);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, SymbolKey.ITERATOR, 0, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda21
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeTypedArrayView.lambda$init$35(realThis, context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
    }

    static /* synthetic */ Object lambda$init$6(RealThis realThis, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return new NativeArrayIterator(scriptable, realThis.realThis(scriptable2), NativeArrayIterator.ARRAY_ITERATOR_TYPE.ENTRIES);
    }

    static /* synthetic */ Object lambda$init$9(RealThis realThis, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        NativeTypedArrayView<?> nativeTypedArrayViewRealThis = realThis.realThis(scriptable2);
        return nativeTypedArrayViewRealThis.typedArraySpeciesCreate(context, scriptable, new Object[]{ArrayLikeAbstractOperations.iterativeMethod(context, ArrayLikeAbstractOperations.IterativeOperation.FILTER, scriptable, nativeTypedArrayViewRealThis, objArr)}, "filter");
    }

    static /* synthetic */ Object lambda$init$18(RealThis realThis, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return new NativeArrayIterator(scriptable, realThis.realThis(scriptable2), NativeArrayIterator.ARRAY_ITERATOR_TYPE.KEYS);
    }

    static /* synthetic */ Object lambda$init$33(RealThis realThis, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return new NativeArrayIterator(scriptable, realThis.realThis(scriptable2), NativeArrayIterator.ARRAY_ITERATOR_TYPE.VALUES);
    }

    static /* synthetic */ Object lambda$init$35(RealThis realThis, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return new NativeArrayIterator(scriptable, realThis.realThis(scriptable2), NativeArrayIterator.ARRAY_ITERATOR_TYPE.VALUES);
    }

    protected boolean checkIndex(int i) {
        return i < 0 || i >= this.length;
    }

    private static NativeArrayBuffer makeArrayBuffer(Context context, Scriptable scriptable, int i, int i2) {
        return (NativeArrayBuffer) context.newObject(scriptable, NativeArrayBuffer.CLASS_NAME, new Object[]{Double.valueOf(((double) i) * ((double) i2))});
    }

    protected static NativeTypedArrayView<?> js_constructor(Context context, Scriptable scriptable, Object[] objArr, TypedArrayConstructable typedArrayConstructable, int i) {
        int length;
        int i2 = 0;
        if (!isArg(objArr, 0)) {
            return typedArrayConstructable.construct(new NativeArrayBuffer(), 0, 0);
        }
        Object obj = objArr[0];
        if (obj == null) {
            return typedArrayConstructable.construct(new NativeArrayBuffer(), 0, 0);
        }
        if ((obj instanceof Number) || (obj instanceof String)) {
            int int32 = ScriptRuntime.toInt32(obj);
            return typedArrayConstructable.construct(makeArrayBuffer(context, scriptable, int32, i), 0, int32);
        }
        if (obj instanceof NativeTypedArrayView) {
            NativeTypedArrayView nativeTypedArrayView = (NativeTypedArrayView) obj;
            NativeTypedArrayView<?> nativeTypedArrayViewConstruct = typedArrayConstructable.construct(makeArrayBuffer(context, scriptable, nativeTypedArrayView.length, i), 0, nativeTypedArrayView.length);
            while (i2 < nativeTypedArrayView.length) {
                nativeTypedArrayViewConstruct.js_set(i2, nativeTypedArrayView.js_get(i2));
                i2++;
            }
            return nativeTypedArrayViewConstruct;
        }
        if (obj instanceof NativeArrayBuffer) {
            NativeArrayBuffer nativeArrayBuffer = (NativeArrayBuffer) obj;
            int int33 = isArg(objArr, 1) ? ScriptRuntime.toInt32(objArr[1]) : 0;
            if (isArg(objArr, 2)) {
                length = ScriptRuntime.toInt32(objArr[2]) * i;
            } else {
                length = nativeArrayBuffer.getLength() - int33;
            }
            if (int33 < 0 || int33 > nativeArrayBuffer.getLength()) {
                throw ScriptRuntime.rangeError(ScriptRuntime.getMessageById("msg.typed.array.bad.offset", Integer.valueOf(int33)));
            }
            if (length < 0 || int33 + length > nativeArrayBuffer.getLength()) {
                throw ScriptRuntime.rangeError(ScriptRuntime.getMessageById("msg.typed.array.bad.length", Integer.valueOf(length)));
            }
            if (int33 % i != 0) {
                throw ScriptRuntime.rangeError(ScriptRuntime.getMessageById("msg.typed.array.bad.offset.byte.size", Integer.valueOf(int33), Integer.valueOf(i)));
            }
            if (length % i != 0) {
                throw ScriptRuntime.rangeError(ScriptRuntime.getMessageById("msg.typed.array.bad.buffer.length.byte.size", Integer.valueOf(length), Integer.valueOf(i)));
            }
            return typedArrayConstructable.construct(nativeArrayBuffer, int33, length / i);
        }
        if (obj instanceof NativeArray) {
            NativeArray nativeArray = (NativeArray) obj;
            NativeTypedArrayView<?> nativeTypedArrayViewConstruct2 = typedArrayConstructable.construct(makeArrayBuffer(context, scriptable, nativeArray.size(), i), 0, nativeArray.size());
            while (i2 < nativeArray.size()) {
                Object obj2 = nativeArray.get(i2, nativeArray);
                if (obj2 == Scriptable.NOT_FOUND || obj2 == Undefined.instance) {
                    nativeTypedArrayViewConstruct2.js_set(i2, ScriptRuntime.NaNobj);
                } else if (obj2 instanceof Wrapper) {
                    nativeTypedArrayViewConstruct2.js_set(i2, ((Wrapper) obj2).unwrap());
                } else {
                    nativeTypedArrayViewConstruct2.js_set(i2, obj2);
                }
                i2++;
            }
            return nativeTypedArrayViewConstruct2;
        }
        if (ScriptRuntime.isArrayObject(obj)) {
            Object[] arrayElements = ScriptRuntime.getArrayElements((Scriptable) obj);
            NativeTypedArrayView<?> nativeTypedArrayViewConstruct3 = typedArrayConstructable.construct(makeArrayBuffer(context, scriptable, arrayElements.length, i), 0, arrayElements.length);
            while (i2 < arrayElements.length) {
                nativeTypedArrayViewConstruct3.js_set(i2, arrayElements[i2]);
                i2++;
            }
            return nativeTypedArrayViewConstruct3;
        }
        throw ScriptRuntime.constructError("Error", "invalid argument");
    }

    private void setRange(NativeTypedArrayView<?> nativeTypedArrayView, int i) {
        int i2;
        int i3 = 0;
        if (i < 0 || i > (i2 = this.length)) {
            throw ScriptRuntime.rangeError(ScriptRuntime.getMessageById("msg.typed.array.bad.offset", Integer.valueOf(i)));
        }
        if (nativeTypedArrayView.length > i2 - i) {
            throw ScriptRuntime.rangeError(ScriptRuntime.getMessageById("msg.typed.array.bad.source.array", new Object[0]));
        }
        if (nativeTypedArrayView.arrayBuffer == this.arrayBuffer) {
            Object[] objArr = new Object[nativeTypedArrayView.length];
            for (int i4 = 0; i4 < nativeTypedArrayView.length; i4++) {
                objArr[i4] = nativeTypedArrayView.js_get(i4);
            }
            while (i3 < nativeTypedArrayView.length) {
                js_set(i3 + i, objArr[i3]);
                i3++;
            }
            return;
        }
        while (i3 < nativeTypedArrayView.length) {
            js_set(i3 + i, nativeTypedArrayView.js_get(i3));
            i3++;
        }
    }

    private void setRange(NativeArray nativeArray, int i) {
        if (i < 0 || i > this.length) {
            throw ScriptRuntime.rangeError(ScriptRuntime.getMessageById("msg.typed.array.bad.offset", Integer.valueOf(i)));
        }
        if (nativeArray.size() + i > this.length) {
            throw ScriptRuntime.rangeError(ScriptRuntime.getMessageById("msg.typed.array.bad.source.array", new Object[0]));
        }
        Iterator it = nativeArray.iterator();
        while (it.hasNext()) {
            js_set(i, it.next());
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object js_buffer(Scriptable scriptable, RealThis realThis) {
        return realThis.realThis(scriptable).arrayBuffer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object js_byteLength(Scriptable scriptable, RealThis realThis) {
        return Integer.valueOf(realThis.realThis(scriptable).byteLength);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object js_byteOffset(Scriptable scriptable, RealThis realThis) {
        return Integer.valueOf(realThis.realThis(scriptable).offset);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object js_length(Scriptable scriptable, RealThis realThis) {
        return Integer.valueOf(realThis.realThis(scriptable).length);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String js_toString(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr, RealThis realThis, boolean z) {
        NativeTypedArrayView<?> nativeTypedArrayViewRealThis = realThis.realThis(scriptable2);
        StringBuilder sb = new StringBuilder();
        if (nativeTypedArrayViewRealThis.length > 0) {
            sb.append(ScriptRuntime.toString(nativeTypedArrayViewRealThis.getElemForToString(context, scriptable, 0, z)));
        }
        for (int i = 1; i < nativeTypedArrayViewRealThis.length; i++) {
            sb.append(AbstractJsonLexerKt.COMMA);
            sb.append(ScriptRuntime.toString(nativeTypedArrayViewRealThis.getElemForToString(context, scriptable, i, z)));
        }
        return sb.toString();
    }

    private Object getElemForToString(Context context, Scriptable scriptable, int i, boolean z) {
        Object objJs_get = js_get(i);
        return z ? ScriptRuntime.getPropFunctionAndThis(objJs_get, "toLocaleString", context, scriptable).call(context, scriptable, ScriptRuntime.lastStoredScriptable(context), ScriptRuntime.emptyArgs) : objJs_get;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:18:0x0030 A[PHI: r7
      0x0030: PHI (r7v7 long) = (r7v6 long), (r7v10 long) binds: [B:14:0x0025, B:16:0x002d] A[DONT_GENERATE, DONT_INLINE]] */
    public static Boolean js_includes(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr, RealThis realThis) {
        NativeTypedArrayView<?> nativeTypedArrayViewRealThis = realThis.realThis(scriptable2);
        Object obj = objArr.length > 0 ? objArr[0] : Undefined.instance;
        if (nativeTypedArrayViewRealThis.length == 0) {
            return Boolean.FALSE;
        }
        long j = 0;
        if (objArr.length >= 2) {
            long integer = (long) ScriptRuntime.toInteger(objArr[1]);
            if (integer < 0) {
                integer += (long) nativeTypedArrayViewRealThis.length;
                j = integer >= 0 ? integer : 0L;
            }
            if (j > nativeTypedArrayViewRealThis.length - 1) {
                return Boolean.FALSE;
            }
        }
        for (int i = (int) j; i < nativeTypedArrayViewRealThis.length; i++) {
            if (ScriptRuntime.sameZero(nativeTypedArrayViewRealThis.js_get(i), obj)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:18:0x0033 A[PHI: r3
      0x0033: PHI (r3v2 long) = (r3v1 long), (r3v3 long) binds: [B:14:0x0028, B:16:0x0030] A[DONT_GENERATE, DONT_INLINE]] */
    public static Object js_indexOf(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr, RealThis realThis) {
        NativeTypedArrayView<?> nativeTypedArrayViewRealThis = realThis.realThis(scriptable2);
        Object obj = objArr.length > 0 ? objArr[0] : Undefined.instance;
        if (nativeTypedArrayViewRealThis.length == 0) {
            return -1;
        }
        long j = 0;
        if (objArr.length >= 2) {
            long integer = (long) ScriptRuntime.toInteger(objArr[1]);
            if (integer < 0) {
                integer += (long) nativeTypedArrayViewRealThis.length;
                j = integer >= 0 ? integer : 0L;
            }
            if (j > nativeTypedArrayViewRealThis.length - 1) {
                return -1;
            }
        }
        for (int i = (int) j; i < nativeTypedArrayViewRealThis.length; i++) {
            Object objJs_get = nativeTypedArrayViewRealThis.js_get(i);
            if (objJs_get != NOT_FOUND && ScriptRuntime.shallowEq(objJs_get, obj)) {
                return Long.valueOf(i);
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object js_lastIndexOf(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr, RealThis realThis) {
        long integer;
        NativeTypedArrayView<?> nativeTypedArrayViewRealThis = realThis.realThis(scriptable2);
        Object obj = objArr.length > 0 ? objArr[0] : Undefined.instance;
        int i = nativeTypedArrayViewRealThis.length;
        if (i == 0) {
            return -1;
        }
        if (objArr.length < 2) {
            integer = ((long) i) - 1;
        } else {
            integer = (long) ScriptRuntime.toInteger(objArr[1]);
            int i2 = nativeTypedArrayViewRealThis.length;
            if (integer >= i2) {
                integer = ((long) i2) - 1;
            } else if (integer < 0) {
                integer += (long) i2;
            }
            if (integer < 0) {
                return -1;
            }
        }
        for (int i3 = (int) integer; i3 >= 0; i3--) {
            Object objJs_get = nativeTypedArrayViewRealThis.js_get(i3);
            if (objJs_get != NOT_FOUND && ScriptRuntime.shallowEq(objJs_get, obj)) {
                return Long.valueOf(i3);
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Scriptable js_slice(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr, RealThis realThis) {
        long sliceIndex;
        long sliceIndex2;
        NativeTypedArrayView<?> nativeTypedArrayViewRealThis = realThis.realThis(scriptable2);
        if (objArr.length == 0) {
            sliceIndex2 = nativeTypedArrayViewRealThis.length;
            sliceIndex = 0;
        } else {
            sliceIndex = ArrayLikeAbstractOperations.toSliceIndex(ScriptRuntime.toInteger(objArr[0]), nativeTypedArrayViewRealThis.length);
            if (objArr.length == 1 || objArr[1] == Undefined.instance) {
                sliceIndex2 = nativeTypedArrayViewRealThis.length;
            } else {
                sliceIndex2 = ArrayLikeAbstractOperations.toSliceIndex(ScriptRuntime.toInteger(objArr[1]), nativeTypedArrayViewRealThis.length);
            }
        }
        long j = sliceIndex2 - sliceIndex;
        if (j > SieveCacheKt.NodeLinkMask) {
            throw ScriptRuntime.rangeError(ScriptRuntime.getMessageById("msg.arraylength.bad", new Object[0]));
        }
        Math.max(j, 0L);
        return nativeTypedArrayViewRealThis.typedArraySpeciesCreate(context, scriptable, new Object[]{nativeTypedArrayViewRealThis.arrayBuffer, Long.valueOf(sliceIndex * ((long) nativeTypedArrayViewRealThis.getBytesPerElement())), Long.valueOf(Math.max(0L, j))}, "slice");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String js_join(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr, RealThis realThis) {
        String string;
        int i;
        NativeTypedArrayView<?> nativeTypedArrayViewRealThis = realThis.realThis(scriptable2);
        if (objArr.length < 1 || objArr[0] == Undefined.instance) {
            string = ",";
        } else {
            string = ScriptRuntime.toString(objArr[0]);
        }
        int i2 = nativeTypedArrayViewRealThis.length;
        if (i2 == 0) {
            return "";
        }
        String[] strArr = new String[i2];
        int i3 = 0;
        int length = 0;
        while (true) {
            i = nativeTypedArrayViewRealThis.length;
            if (i3 == i) {
                break;
            }
            Object objJs_get = nativeTypedArrayViewRealThis.js_get(i3);
            if (objJs_get != null && objJs_get != Undefined.instance) {
                String string2 = ScriptRuntime.toString(objJs_get);
                length += string2.length();
                strArr[i3] = string2;
            }
            i3++;
        }
        StringBuilder sb = new StringBuilder(length + ((i - 1) * string.length()));
        for (int i4 = 0; i4 != nativeTypedArrayViewRealThis.length; i4++) {
            if (i4 != 0) {
                sb.append(string);
            }
            String str = strArr[i4];
            if (str != null) {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static NativeTypedArrayView<?> js_reverse(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr, RealThis realThis) {
        NativeTypedArrayView<?> nativeTypedArrayViewRealThis = realThis.realThis(scriptable2);
        int i = 0;
        for (int i2 = nativeTypedArrayViewRealThis.length - 1; i < i2; i2--) {
            Object objJs_get = nativeTypedArrayViewRealThis.js_get(i);
            nativeTypedArrayViewRealThis.js_set(i, nativeTypedArrayViewRealThis.js_get(i2));
            nativeTypedArrayViewRealThis.js_set(i2, objJs_get);
            i++;
        }
        return nativeTypedArrayViewRealThis;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static NativeTypedArrayView<?> js_fill(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr, RealThis realThis) {
        long jMin;
        long jMin2;
        NativeTypedArrayView<?> nativeTypedArrayViewRealThis = realThis.realThis(scriptable2);
        long integer = objArr.length >= 2 ? (long) ScriptRuntime.toInteger(objArr[1]) : 0L;
        if (integer < 0) {
            jMin = Math.max(((long) nativeTypedArrayViewRealThis.length) + integer, 0L);
        } else {
            jMin = Math.min(integer, nativeTypedArrayViewRealThis.length);
        }
        long integer2 = nativeTypedArrayViewRealThis.length;
        if (objArr.length >= 3 && !Undefined.isUndefined(objArr[2])) {
            integer2 = (long) ScriptRuntime.toInteger(objArr[2]);
        }
        if (integer2 < 0) {
            jMin2 = Math.max(((long) nativeTypedArrayViewRealThis.length) + integer2, 0L);
        } else {
            jMin2 = Math.min(integer2, nativeTypedArrayViewRealThis.length);
        }
        Object obj = objArr.length > 0 ? objArr[0] : Undefined.instance;
        for (int i = (int) jMin; i < jMin2; i++) {
            nativeTypedArrayViewRealThis.js_set(i, obj);
        }
        return nativeTypedArrayViewRealThis;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Scriptable js_sort(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr, RealThis realThis) {
        if (isArg(objArr, 0) && !(objArr[0] instanceof Callable)) {
            throw ScriptRuntime.typeErrorById("msg.function.expected", new Object[0]);
        }
        NativeTypedArrayView<?> nativeTypedArrayViewRealThis = realThis.realThis(scriptable2);
        Object[] objArrSortTemporaryArray = nativeTypedArrayViewRealThis.sortTemporaryArray(context, scriptable, objArr);
        for (int i = 0; i < nativeTypedArrayViewRealThis.length; i++) {
            nativeTypedArrayViewRealThis.js_set(i, objArrSortTemporaryArray[i]);
        }
        return nativeTypedArrayViewRealThis;
    }

    private Object[] sortTemporaryArray(Context context, Scriptable scriptable, Object[] objArr) {
        Comparator<Object> comparatorComparingDouble;
        if (objArr.length > 0 && Undefined.instance != objArr[0]) {
            comparatorComparingDouble = ArrayLikeAbstractOperations.getSortComparator(context, scriptable, objArr);
        } else {
            comparatorComparingDouble = j$.util.Comparator.CC.comparingDouble(new ToDoubleFunction() { // from class: org.mozilla.javascript.typedarrays.NativeTypedArrayView$$ExternalSyntheticLambda0
                @Override // java.util.function.ToDoubleFunction
                public final double applyAsDouble(Object obj) {
                    return ((Number) obj).doubleValue();
                }
            });
        }
        Object[] array = toArray();
        Arrays.sort(array, comparatorComparingDouble);
        return array;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object js_copyWithin(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr, RealThis realThis) {
        long jMin;
        long jMin2;
        long jMin3;
        NativeTypedArrayView<?> nativeTypedArrayViewRealThis = realThis.realThis(scriptable2);
        int i = 1;
        long integer = (long) ScriptRuntime.toInteger(objArr.length >= 1 ? objArr[0] : Undefined.instance);
        if (integer < 0) {
            jMin = Math.max(((long) nativeTypedArrayViewRealThis.length) + integer, 0L);
        } else {
            jMin = Math.min(integer, nativeTypedArrayViewRealThis.length);
        }
        long integer2 = (long) ScriptRuntime.toInteger(objArr.length >= 2 ? objArr[1] : Undefined.instance);
        if (integer2 < 0) {
            jMin2 = Math.max(((long) nativeTypedArrayViewRealThis.length) + integer2, 0L);
        } else {
            jMin2 = Math.min(integer2, nativeTypedArrayViewRealThis.length);
        }
        long integer3 = nativeTypedArrayViewRealThis.length;
        if (objArr.length >= 3 && !Undefined.isUndefined(objArr[2])) {
            integer3 = (long) ScriptRuntime.toInteger(objArr[2]);
        }
        if (integer3 < 0) {
            jMin3 = Math.max(((long) nativeTypedArrayViewRealThis.length) + integer3, 0L);
        } else {
            jMin3 = Math.min(integer3, nativeTypedArrayViewRealThis.length);
        }
        long jMin4 = Math.min(jMin3 - jMin2, ((long) nativeTypedArrayViewRealThis.length) - jMin);
        if (jMin2 < jMin) {
            long j = jMin2 + jMin4;
            if (jMin < j) {
                jMin2 = j - 1;
                jMin = (jMin + jMin4) - 1;
                i = -1;
            }
        }
        while (jMin4 > 0) {
            nativeTypedArrayViewRealThis.js_set((int) jMin, nativeTypedArrayViewRealThis.js_get((int) jMin2));
            long j2 = i;
            jMin2 += j2;
            jMin += j2;
            jMin4--;
        }
        return nativeTypedArrayViewRealThis;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object js_set(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr, RealThis realThis) {
        NativeTypedArrayView<?> nativeTypedArrayViewRealThis = realThis.realThis(scriptable2);
        if (objArr.length > 0) {
            Object obj = objArr[0];
            if (obj instanceof NativeTypedArrayView) {
                nativeTypedArrayViewRealThis.setRange((NativeTypedArrayView<?>) objArr[0], isArg(objArr, 1) ? ScriptRuntime.toInt32(objArr[1]) : 0);
                return Undefined.instance;
            }
            if (obj instanceof NativeArray) {
                nativeTypedArrayViewRealThis.setRange((NativeArray) objArr[0], isArg(objArr, 1) ? ScriptRuntime.toInt32(objArr[1]) : 0);
                return Undefined.instance;
            }
            if (obj instanceof Scriptable) {
                return Undefined.instance;
            }
            if (isArg(objArr, 2)) {
                return nativeTypedArrayViewRealThis.js_set(ScriptRuntime.toInt32(objArr[0]), objArr[1]);
            }
        }
        throw ScriptRuntime.constructError("Error", "invalid arguments");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object js_subarray(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr, RealThis realThis) {
        NativeTypedArrayView<?> nativeTypedArrayViewRealThis = realThis.realThis(scriptable2);
        int int32 = isArg(objArr, 0) ? ScriptRuntime.toInt32(objArr[0]) : 0;
        int int33 = isArg(objArr, 1) ? ScriptRuntime.toInt32(objArr[1]) : nativeTypedArrayViewRealThis.length;
        if (context.getLanguageVersion() >= 200 || objArr.length > 0) {
            if (int32 < 0) {
                int32 += nativeTypedArrayViewRealThis.length;
            }
            if (int33 < 0) {
                int33 += nativeTypedArrayViewRealThis.length;
            }
            int iMax = Math.max(0, int32);
            return context.newObject(scriptable, nativeTypedArrayViewRealThis.getClassName(), new Object[]{nativeTypedArrayViewRealThis.arrayBuffer, Integer.valueOf(Math.min(nativeTypedArrayViewRealThis.getByteOffset() + (iMax * nativeTypedArrayViewRealThis.getBytesPerElement()), nativeTypedArrayViewRealThis.arrayBuffer.getLength())), Integer.valueOf(Math.max(0, Math.min(nativeTypedArrayViewRealThis.length, int33) - iMax))});
        }
        throw ScriptRuntime.constructError("Error", "invalid arguments");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object js_at(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr, RealThis realThis) {
        NativeTypedArrayView<?> nativeTypedArrayViewRealThis = realThis.realThis(scriptable2);
        long integer = objArr.length >= 1 ? (long) ScriptRuntime.toInteger(objArr[0]) : 0L;
        if (integer < 0) {
            integer += (long) nativeTypedArrayViewRealThis.length;
        }
        if (integer < 0 || integer >= nativeTypedArrayViewRealThis.length) {
            return Undefined.instance;
        }
        return getProperty(scriptable2, (int) integer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Scriptable typedArraySpeciesCreate(Context context, Scriptable scriptable, Object[] objArr, String str) {
        Scriptable scriptableConstruct = AbstractEcmaObjectOperations.speciesConstructor(context, this, ScriptRuntime.getExistingCtor(context, ScriptableObject.getTopLevelScope(scriptable), getClassName())).construct(context, scriptable, objArr);
        if (scriptableConstruct instanceof NativeTypedArrayView) {
            return scriptableConstruct;
        }
        throw ScriptRuntime.typeErrorById("msg.typed.array.ctor.incompatible", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object js_toReversed(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr, RealThis realThis) {
        NativeTypedArrayView<?> nativeTypedArrayViewRealThis = realThis.realThis(scriptable2);
        int i = 0;
        Scriptable scriptableNewObject = context.newObject(scriptable, nativeTypedArrayViewRealThis.getClassName(), new Object[]{new NativeArrayBuffer(nativeTypedArrayViewRealThis.length * nativeTypedArrayViewRealThis.getBytesPerElement()), 0, Integer.valueOf(nativeTypedArrayViewRealThis.length), Integer.valueOf(nativeTypedArrayViewRealThis.getBytesPerElement())});
        while (true) {
            int i2 = nativeTypedArrayViewRealThis.length;
            if (i >= i2) {
                return scriptableNewObject;
            }
            scriptableNewObject.put(i, scriptableNewObject, nativeTypedArrayViewRealThis.js_get((i2 - i) - 1));
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object js_toSorted(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr, RealThis realThis) {
        NativeTypedArrayView<?> nativeTypedArrayViewRealThis = realThis.realThis(scriptable2);
        Object[] objArrSortTemporaryArray = nativeTypedArrayViewRealThis.sortTemporaryArray(context, scriptable, objArr);
        Scriptable scriptableNewObject = context.newObject(scriptable, nativeTypedArrayViewRealThis.getClassName(), new Object[]{new NativeArrayBuffer(nativeTypedArrayViewRealThis.length * nativeTypedArrayViewRealThis.getBytesPerElement()), 0, Integer.valueOf(nativeTypedArrayViewRealThis.length), Integer.valueOf(nativeTypedArrayViewRealThis.getBytesPerElement())});
        for (int i = 0; i < nativeTypedArrayViewRealThis.length; i++) {
            scriptableNewObject.put(i, scriptableNewObject, objArrSortTemporaryArray[i]);
        }
        return scriptableNewObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object js_with(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr, RealThis realThis) {
        NativeTypedArrayView<?> nativeTypedArrayViewRealThis = realThis.realThis(scriptable2);
        long integer = objArr.length > 0 ? (int) ScriptRuntime.toInteger(objArr[0]) : 0L;
        long j = integer >= 0 ? integer : ((long) nativeTypedArrayViewRealThis.length) + integer;
        Double dValueOf = Double.valueOf(objArr.length > 1 ? ScriptRuntime.toNumber(objArr[1]) : 0.0d);
        if (j < 0 || j >= nativeTypedArrayViewRealThis.length) {
            throw ScriptRuntime.rangeError(ScriptRuntime.getMessageById("msg.typed.array.index.out.of.bounds", Long.valueOf(integer), Integer.valueOf(nativeTypedArrayViewRealThis.length * (-1)), Integer.valueOf(nativeTypedArrayViewRealThis.length - 1)));
        }
        Scriptable scriptableNewObject = context.newObject(scriptable, nativeTypedArrayViewRealThis.getClassName(), new Object[]{new NativeArrayBuffer(nativeTypedArrayViewRealThis.length * nativeTypedArrayViewRealThis.getBytesPerElement()), 0, Integer.valueOf(nativeTypedArrayViewRealThis.length), Integer.valueOf(nativeTypedArrayViewRealThis.getBytesPerElement())});
        for (int i = 0; i < nativeTypedArrayViewRealThis.length; i++) {
            scriptableNewObject.put(i, scriptableNewObject, ((long) i) == j ? dValueOf : nativeTypedArrayViewRealThis.js_get(i));
        }
        return scriptableNewObject;
    }

    @Override // org.mozilla.javascript.ExternalArrayData
    public Object getArrayElement(int i) {
        return js_get(i);
    }

    @Override // org.mozilla.javascript.ExternalArrayData
    public void setArrayElement(int i, Object obj) {
        js_set(i, obj);
    }

    @Override // org.mozilla.javascript.ExternalArrayData
    public int getArrayLength() {
        return this.length;
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        for (int i = 0; i < this.length; i++) {
            if (obj.equals(js_get(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        for (int i = this.length - 1; i >= 0; i--) {
            if (obj.equals(js_get(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        Object[] objArr = new Object[this.length];
        for (int i = 0; i < this.length; i++) {
            objArr[i] = js_get(i);
        }
        return objArr;
    }

    @Override // java.util.List, java.util.Collection
    public <U> U[] toArray(U[] uArr) {
        if (uArr.length < this.length) {
            uArr = (U[]) ((Object[]) Array.newInstance(uArr.getClass().getComponentType(), this.length));
        }
        for (int i = 0; i < this.length; i++) {
            try {
                uArr[i] = js_get(i);
            } catch (ClassCastException unused) {
                throw new ArrayStoreException();
            }
        }
        return uArr;
    }

    @Override // org.mozilla.javascript.ScriptableObject, java.util.List, java.util.Collection
    public int size() {
        return this.length;
    }

    @Override // org.mozilla.javascript.ScriptableObject, java.util.List, java.util.Collection
    public boolean isEmpty() {
        return this.length == 0;
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.List, java.util.Collection
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof NativeTypedArrayView)) {
            return false;
        }
        NativeTypedArrayView nativeTypedArrayView = (NativeTypedArrayView) obj;
        if (this.length != nativeTypedArrayView.length) {
            return false;
        }
        for (int i = 0; i < this.length; i++) {
            if (!js_get(i).equals(nativeTypedArrayView.js_get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.List, java.util.Collection
    public int hashCode() {
        int iHashCode = 0;
        for (int i = 0; i < this.length; i++) {
            iHashCode += js_get(i).hashCode();
        }
        return iHashCode;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<T> iterator() {
        return new NativeTypedArrayIterator(this, 0);
    }

    @Override // java.util.List
    public ListIterator<T> listIterator() {
        return new NativeTypedArrayIterator(this, 0);
    }

    @Override // java.util.List
    public ListIterator<T> listIterator(int i) {
        if (checkIndex(i)) {
            throw new IndexOutOfBoundsException();
        }
        return new NativeTypedArrayIterator(this, i);
    }

    @Override // java.util.List
    public List<T> subList(int i, int i2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(T t) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public void add(int i, T t) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public T remove(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }
}
