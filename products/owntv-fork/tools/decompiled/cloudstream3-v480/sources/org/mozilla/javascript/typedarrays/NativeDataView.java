package org.mozilla.javascript.typedarrays;

import j$.util.function.Function$CC;
import java.util.function.Function;
import org.mozilla.javascript.Callable;
import org.mozilla.javascript.Constructable;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.LambdaConstructor;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Undefined;

/* JADX INFO: loaded from: classes8.dex */
public class NativeDataView extends NativeArrayBufferView {
    public static final String CLASS_NAME = "DataView";
    private static final long serialVersionUID = 1427967607557438968L;

    public NativeDataView() {
    }

    public NativeDataView(NativeArrayBuffer nativeArrayBuffer, int i, int i2) {
        super(nativeArrayBuffer, i, i2);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return CLASS_NAME;
    }

    public static void init(Context context, Scriptable scriptable, boolean z) {
        LambdaConstructor lambdaConstructor = new LambdaConstructor(scriptable, CLASS_NAME, 1, 2, new Constructable() { // from class: org.mozilla.javascript.typedarrays.NativeDataView$$ExternalSyntheticLambda0
            @Override // org.mozilla.javascript.Constructable
            public final Scriptable construct(Context context2, Scriptable scriptable2, Object[] objArr) {
                return NativeDataView.js_constructor(context2, scriptable2, objArr);
            }
        });
        lambdaConstructor.setPrototypePropertyAttributes(7);
        lambdaConstructor.definePrototypeProperty(context, "buffer", new Function() { // from class: org.mozilla.javascript.typedarrays.NativeDataView$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return NativeDataView.realThis((Scriptable) obj).arrayBuffer;
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }, 3);
        lambdaConstructor.definePrototypeProperty(context, "byteLength", new Function() { // from class: org.mozilla.javascript.typedarrays.NativeDataView$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Integer.valueOf(NativeDataView.realThis((Scriptable) obj).byteLength);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }, 3);
        lambdaConstructor.definePrototypeProperty(context, "byteOffset", new Function() { // from class: org.mozilla.javascript.typedarrays.NativeDataView$$ExternalSyntheticLambda4
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Integer.valueOf(NativeDataView.realThis((Scriptable) obj).offset);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "getFloat32", 1, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeDataView$$ExternalSyntheticLambda5
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeDataView.realThis(scriptable3).js_getFloat(4, objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "getFloat64", 1, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeDataView$$ExternalSyntheticLambda6
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeDataView.realThis(scriptable3).js_getFloat(8, objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "getInt8", 1, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeDataView$$ExternalSyntheticLambda7
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeDataView.realThis(scriptable3).js_getInt(1, true, objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "getInt16", 1, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeDataView$$ExternalSyntheticLambda8
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeDataView.realThis(scriptable3).js_getInt(2, true, objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "getInt32", 1, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeDataView$$ExternalSyntheticLambda9
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeDataView.realThis(scriptable3).js_getInt(4, true, objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "getUint8", 1, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeDataView$$ExternalSyntheticLambda10
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeDataView.realThis(scriptable3).js_getInt(1, false, objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "getUint16", 1, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeDataView$$ExternalSyntheticLambda11
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeDataView.realThis(scriptable3).js_getInt(2, false, objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "getUint32", 1, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeDataView$$ExternalSyntheticLambda12
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeDataView.realThis(scriptable3).js_getInt(4, false, objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "setFloat32", 2, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeDataView$$ExternalSyntheticLambda13
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeDataView.lambda$init$11(context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "setFloat64", 2, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeDataView$$ExternalSyntheticLambda14
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeDataView.lambda$init$12(context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "setInt8", 2, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeDataView$$ExternalSyntheticLambda15
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeDataView.lambda$init$13(context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "setInt16", 2, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeDataView$$ExternalSyntheticLambda16
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeDataView.lambda$init$14(context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "setInt32", 2, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeDataView$$ExternalSyntheticLambda17
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeDataView.lambda$init$15(context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "setUint8", 2, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeDataView$$ExternalSyntheticLambda18
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeDataView.lambda$init$16(context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "setUint16", 2, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeDataView$$ExternalSyntheticLambda19
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeDataView.lambda$init$17(context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "setUint32", 2, new Callable() { // from class: org.mozilla.javascript.typedarrays.NativeDataView$$ExternalSyntheticLambda1
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeDataView.lambda$init$18(context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        ScriptableObject.defineProperty(scriptable, CLASS_NAME, lambdaConstructor, 2);
        if (z) {
            lambdaConstructor.sealObject();
        }
    }

    static /* synthetic */ Object lambda$init$11(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        realThis(scriptable2).js_setFloat(4, objArr);
        return Undefined.instance;
    }

    static /* synthetic */ Object lambda$init$12(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        realThis(scriptable2).js_setFloat(8, objArr);
        return Undefined.instance;
    }

    static /* synthetic */ Object lambda$init$13(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        realThis(scriptable2).js_setInt(1, true, objArr);
        return Undefined.instance;
    }

    static /* synthetic */ Object lambda$init$14(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        realThis(scriptable2).js_setInt(2, true, objArr);
        return Undefined.instance;
    }

    static /* synthetic */ Object lambda$init$15(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        realThis(scriptable2).js_setInt(4, true, objArr);
        return Undefined.instance;
    }

    static /* synthetic */ Object lambda$init$16(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        realThis(scriptable2).js_setInt(1, false, objArr);
        return Undefined.instance;
    }

    static /* synthetic */ Object lambda$init$17(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        realThis(scriptable2).js_setInt(2, false, objArr);
        return Undefined.instance;
    }

    static /* synthetic */ Object lambda$init$18(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        realThis(scriptable2).js_setInt(4, false, objArr);
        return Undefined.instance;
    }

    private static int determinePos(Object[] objArr) {
        if (!isArg(objArr, 0)) {
            return 0;
        }
        double number = ScriptRuntime.toNumber(objArr[0]);
        if (Double.isInfinite(number)) {
            throw ScriptRuntime.rangeError("offset out of range");
        }
        return ScriptRuntime.toInt32(number);
    }

    private void rangeCheck(int i, int i2) {
        if (i < 0 || i + i2 > this.byteLength) {
            throw ScriptRuntime.rangeError("offset out of range");
        }
    }

    private static NativeDataView realThis(Scriptable scriptable) {
        return (NativeDataView) LambdaConstructor.convertThisObject(scriptable, NativeDataView.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static NativeDataView js_constructor(Context context, Scriptable scriptable, Object[] objArr) {
        int length;
        int int32 = 0;
        if (isArg(objArr, 0)) {
            Object obj = objArr[0];
            if (obj instanceof NativeArrayBuffer) {
                NativeArrayBuffer nativeArrayBuffer = (NativeArrayBuffer) obj;
                if (isArg(objArr, 1)) {
                    double number = ScriptRuntime.toNumber(objArr[1]);
                    if (Double.isInfinite(number)) {
                        throw ScriptRuntime.rangeError("offset out of range");
                    }
                    int32 = ScriptRuntime.toInt32(number);
                }
                if (isArg(objArr, 2)) {
                    double number2 = ScriptRuntime.toNumber(objArr[2]);
                    if (Double.isInfinite(number2)) {
                        throw ScriptRuntime.rangeError("offset out of range");
                    }
                    length = ScriptRuntime.toInt32(number2);
                } else {
                    length = nativeArrayBuffer.getLength() - int32;
                }
                if (length < 0) {
                    throw ScriptRuntime.rangeError("length out of range");
                }
                if (int32 < 0 || int32 + length > nativeArrayBuffer.getLength()) {
                    throw ScriptRuntime.rangeError("offset out of range");
                }
                return new NativeDataView(nativeArrayBuffer, int32, length);
            }
        }
        throw ScriptRuntime.constructError("TypeError", "Missing parameters");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Object js_getInt(int i, boolean z, Object[] objArr) {
        int iDeterminePos = determinePos(objArr);
        rangeCheck(iDeterminePos, i);
        boolean z2 = isArg(objArr, 1) && i > 1 && ScriptRuntime.toBoolean(objArr[1]);
        if (i == 1) {
            if (z) {
                return ByteIo.readInt8(this.arrayBuffer.buffer, this.offset + iDeterminePos);
            }
            return ByteIo.readUint8(this.arrayBuffer.buffer, this.offset + iDeterminePos);
        }
        if (i == 2) {
            if (z) {
                return ByteIo.readInt16(this.arrayBuffer.buffer, this.offset + iDeterminePos, z2);
            }
            return ByteIo.readUint16(this.arrayBuffer.buffer, this.offset + iDeterminePos, z2);
        }
        if (i != 4) {
            throw new AssertionError();
        }
        if (z) {
            return ByteIo.readInt32(this.arrayBuffer.buffer, this.offset + iDeterminePos, z2);
        }
        return ByteIo.readUint32(this.arrayBuffer.buffer, this.offset + iDeterminePos, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Object js_getFloat(int i, Object[] objArr) {
        int iDeterminePos = determinePos(objArr);
        rangeCheck(iDeterminePos, i);
        boolean z = isArg(objArr, 1) && i > 1 && ScriptRuntime.toBoolean(objArr[1]);
        if (i == 4) {
            return ByteIo.readFloat32(this.arrayBuffer.buffer, this.offset + iDeterminePos, z);
        }
        if (i == 8) {
            return ByteIo.readFloat64(this.arrayBuffer.buffer, this.offset + iDeterminePos, z);
        }
        throw new AssertionError();
    }

    private void js_setInt(int i, boolean z, Object[] objArr) {
        int iDeterminePos = determinePos(objArr);
        if (iDeterminePos < 0) {
            throw ScriptRuntime.rangeError("offset out of range");
        }
        boolean z2 = isArg(objArr, 2) && i > 1 && ScriptRuntime.toBoolean(objArr[2]);
        Object obj = ScriptRuntime.zeroObj;
        if (objArr.length > 1) {
            obj = objArr[1];
        }
        if (i == 1) {
            if (z) {
                int int8 = Conversions.toInt8(obj);
                if (i + iDeterminePos > this.byteLength) {
                    throw ScriptRuntime.rangeError("offset out of range");
                }
                ByteIo.writeInt8(this.arrayBuffer.buffer, this.offset + iDeterminePos, int8);
                return;
            }
            int uint8 = Conversions.toUint8(obj);
            if (i + iDeterminePos > this.byteLength) {
                throw ScriptRuntime.rangeError("offset out of range");
            }
            ByteIo.writeUint8(this.arrayBuffer.buffer, this.offset + iDeterminePos, uint8);
            return;
        }
        if (i == 2) {
            if (z) {
                int int16 = Conversions.toInt16(obj);
                if (i + iDeterminePos > this.byteLength) {
                    throw ScriptRuntime.rangeError("offset out of range");
                }
                ByteIo.writeInt16(this.arrayBuffer.buffer, this.offset + iDeterminePos, int16, z2);
                return;
            }
            int uint16 = Conversions.toUint16(obj);
            if (i + iDeterminePos > this.byteLength) {
                throw ScriptRuntime.rangeError("offset out of range");
            }
            ByteIo.writeUint16(this.arrayBuffer.buffer, this.offset + iDeterminePos, uint16, z2);
            return;
        }
        if (i != 4) {
            throw new AssertionError();
        }
        if (z) {
            int int32 = Conversions.toInt32(obj);
            if (i + iDeterminePos > this.byteLength) {
                throw ScriptRuntime.rangeError("offset out of range");
            }
            ByteIo.writeInt32(this.arrayBuffer.buffer, this.offset + iDeterminePos, int32, z2);
            return;
        }
        long uint32 = Conversions.toUint32(obj);
        if (i + iDeterminePos > this.byteLength) {
            throw ScriptRuntime.rangeError("offset out of range");
        }
        ByteIo.writeUint32(this.arrayBuffer.buffer, this.offset + iDeterminePos, uint32, z2);
    }

    private void js_setFloat(int i, Object[] objArr) {
        int iDeterminePos = determinePos(objArr);
        if (iDeterminePos < 0) {
            throw ScriptRuntime.rangeError("offset out of range");
        }
        boolean z = isArg(objArr, 2) && i > 1 && ScriptRuntime.toBoolean(objArr[2]);
        double number = objArr.length > 1 ? ScriptRuntime.toNumber(objArr[1]) : Double.NaN;
        if (iDeterminePos + i > this.byteLength) {
            throw ScriptRuntime.rangeError("offset out of range");
        }
        if (i == 4) {
            ByteIo.writeFloat32(this.arrayBuffer.buffer, this.offset + iDeterminePos, number, z);
        } else {
            if (i == 8) {
                ByteIo.writeFloat64(this.arrayBuffer.buffer, this.offset + iDeterminePos, number, z);
                return;
            }
            throw new AssertionError();
        }
    }
}
