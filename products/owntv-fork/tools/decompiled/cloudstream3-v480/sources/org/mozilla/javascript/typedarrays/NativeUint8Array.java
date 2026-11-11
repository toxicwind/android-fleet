package org.mozilla.javascript.typedarrays;

import org.mozilla.javascript.Constructable;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.LambdaConstructor;
import org.mozilla.javascript.ScriptRuntimeES6;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Undefined;

/* JADX INFO: loaded from: classes8.dex */
public class NativeUint8Array extends NativeTypedArrayView<Integer> {
    private static final String CLASS_NAME = "Uint8Array";
    private static final long serialVersionUID = -3349419704390398895L;

    @Override // org.mozilla.javascript.typedarrays.NativeTypedArrayView
    public int getBytesPerElement() {
        return 1;
    }

    public NativeUint8Array() {
    }

    public NativeUint8Array(NativeArrayBuffer nativeArrayBuffer, int i, int i2) {
        super(nativeArrayBuffer, i, i2, i2);
    }

    public NativeUint8Array(int i) {
        this(new NativeArrayBuffer(i), 0, i);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return CLASS_NAME;
    }

    public static void init(Context context, Scriptable scriptable, boolean z) {
        LambdaConstructor lambdaConstructor = new LambdaConstructor(scriptable, CLASS_NAME, 3, 2, new Constructable() { // from class: org.mozilla.javascript.typedarrays.NativeUint8Array$$ExternalSyntheticLambda1
            @Override // org.mozilla.javascript.Constructable
            public final Scriptable construct(Context context2, Scriptable scriptable2, Object[] objArr) {
                return NativeTypedArrayView.js_constructor(context2, scriptable2, objArr, new NativeTypedArrayView.TypedArrayConstructable() { // from class: org.mozilla.javascript.typedarrays.NativeUint8Array$$ExternalSyntheticLambda0
                    @Override // org.mozilla.javascript.typedarrays.NativeTypedArrayView.TypedArrayConstructable
                    public final NativeTypedArrayView construct(NativeArrayBuffer nativeArrayBuffer, int i, int i2) {
                        return new NativeUint8Array(nativeArrayBuffer, i, i2);
                    }
                }, 1);
            }
        });
        lambdaConstructor.setPrototypePropertyAttributes(7);
        NativeTypedArrayView.init(context, scriptable, lambdaConstructor, new NativeTypedArrayView.RealThis() { // from class: org.mozilla.javascript.typedarrays.NativeUint8Array$$ExternalSyntheticLambda2
            @Override // org.mozilla.javascript.typedarrays.NativeTypedArrayView.RealThis
            public final NativeTypedArrayView realThis(Scriptable scriptable2) {
                return NativeUint8Array.realThis(scriptable2);
            }
        });
        lambdaConstructor.defineProperty("BYTES_PER_ELEMENT", (Object) 1, 7);
        lambdaConstructor.definePrototypeProperty("BYTES_PER_ELEMENT", (Object) 1, 7);
        ScriptRuntimeES6.addSymbolSpecies(context, scriptable, lambdaConstructor);
        ScriptableObject.defineProperty(scriptable, CLASS_NAME, lambdaConstructor, 2);
        if (z) {
            lambdaConstructor.sealObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static NativeUint8Array realThis(Scriptable scriptable) {
        return (NativeUint8Array) LambdaConstructor.convertThisObject(scriptable, NativeUint8Array.class);
    }

    @Override // org.mozilla.javascript.typedarrays.NativeTypedArrayView
    protected Object js_get(int i) {
        if (checkIndex(i)) {
            return Undefined.instance;
        }
        return ByteIo.readUint8(this.arrayBuffer.buffer, i + this.offset);
    }

    @Override // org.mozilla.javascript.typedarrays.NativeTypedArrayView
    protected Object js_set(int i, Object obj) {
        if (checkIndex(i)) {
            return Undefined.instance;
        }
        ByteIo.writeUint8(this.arrayBuffer.buffer, i + this.offset, Conversions.toUint8(obj));
        return null;
    }

    @Override // java.util.List
    public Integer get(int i) {
        if (checkIndex(i)) {
            throw new IndexOutOfBoundsException();
        }
        return (Integer) js_get(i);
    }

    @Override // java.util.List
    public Integer set(int i, Integer num) {
        if (checkIndex(i)) {
            throw new IndexOutOfBoundsException();
        }
        return (Integer) js_set(i, num);
    }
}
