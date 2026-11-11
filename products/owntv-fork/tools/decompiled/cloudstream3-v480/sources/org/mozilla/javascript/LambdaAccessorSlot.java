package org.mozilla.javascript;

import java.util.function.BiConsumer;

/* JADX INFO: loaded from: classes8.dex */
public class LambdaAccessorSlot extends Slot {
    private transient java.util.function.Function<Scriptable, Object> getter;
    private LambdaFunction getterFunction;
    private transient BiConsumer<Scriptable, Object> setter;
    private LambdaFunction setterFunction;

    @Override // org.mozilla.javascript.Slot
    boolean isSetterSlot() {
        return true;
    }

    @Override // org.mozilla.javascript.Slot
    boolean isValueSlot() {
        return false;
    }

    LambdaAccessorSlot(Object obj, int i) {
        super(obj, i, 0);
    }

    LambdaAccessorSlot(Slot slot) {
        super(slot);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.mozilla.javascript.Slot
    public LambdaAccessorSlot copySlot() {
        LambdaAccessorSlot lambdaAccessorSlot = new LambdaAccessorSlot(this);
        lambdaAccessorSlot.value = this.value;
        lambdaAccessorSlot.getter = this.getter;
        lambdaAccessorSlot.setter = this.setter;
        lambdaAccessorSlot.getterFunction = this.getterFunction;
        lambdaAccessorSlot.setterFunction = this.setterFunction;
        lambdaAccessorSlot.next = null;
        lambdaAccessorSlot.orderedNext = null;
        return lambdaAccessorSlot;
    }

    @Override // org.mozilla.javascript.Slot
    ScriptableObject getPropertyDescriptor(Context context, Scriptable scriptable) {
        return buildPropertyDescriptor(context);
    }

    public ScriptableObject buildPropertyDescriptor(Context context) {
        NativeObject nativeObject = new NativeObject();
        int attributes = getAttributes();
        boolean z = context.getLanguageVersion() >= 200;
        LambdaFunction lambdaFunction = this.getterFunction;
        if (z) {
            if (lambdaFunction == null && this.setterFunction == null) {
                nativeObject.defineProperty("writable", Boolean.valueOf((attributes & 1) == 0), 0);
            }
        } else {
            nativeObject.setCommonDescriptorProperties(attributes, lambdaFunction == null && this.setterFunction == null);
        }
        LambdaFunction lambdaFunction2 = this.getterFunction;
        if (lambdaFunction2 != null) {
            nativeObject.defineProperty("get", lambdaFunction2, 0);
        }
        LambdaFunction lambdaFunction3 = this.setterFunction;
        if (lambdaFunction3 != null) {
            nativeObject.defineProperty("set", lambdaFunction3, 0);
        } else if (z) {
            nativeObject.defineProperty("set", Undefined.instance, 0);
        }
        if (z) {
            nativeObject.defineProperty("enumerable", Boolean.valueOf((attributes & 2) == 0), 0);
            nativeObject.defineProperty("configurable", Boolean.valueOf((attributes & 4) == 0), 0);
        }
        return nativeObject;
    }

    @Override // org.mozilla.javascript.Slot
    public boolean setValue(Object obj, Scriptable scriptable, Scriptable scriptable2, boolean z) {
        BiConsumer<Scriptable, Object> biConsumer = this.setter;
        if (biConsumer == null) {
            if (this.getter != null) {
                throwNoSetterException(scriptable2, obj);
                return true;
            }
            return super.setValue(obj, scriptable2, scriptable2, z);
        }
        biConsumer.accept(scriptable2, obj);
        return true;
    }

    @Override // org.mozilla.javascript.Slot
    public Object getValue(Scriptable scriptable) {
        java.util.function.Function<Scriptable, Object> function = this.getter;
        if (function != null) {
            return function.apply(scriptable);
        }
        return super.getValue(scriptable);
    }

    public void setGetter(Scriptable scriptable, final java.util.function.Function<Scriptable, Object> function) {
        this.getter = function;
        if (function != null) {
            this.getterFunction = new LambdaFunction(scriptable, "get " + String.valueOf(this.name), 0, new Callable() { // from class: org.mozilla.javascript.LambdaAccessorSlot$$ExternalSyntheticLambda0
                @Override // org.mozilla.javascript.Callable
                public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                    return function.apply(scriptable3);
                }
            });
        }
    }

    public void setSetter(Scriptable scriptable, final BiConsumer<Scriptable, Object> biConsumer) {
        this.setter = biConsumer;
        if (biConsumer != null) {
            this.setterFunction = new LambdaFunction(scriptable, "set " + String.valueOf(this.name), 1, new Callable() { // from class: org.mozilla.javascript.LambdaAccessorSlot$$ExternalSyntheticLambda1
                @Override // org.mozilla.javascript.Callable
                public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                    return LambdaAccessorSlot.lambda$setSetter$1(biConsumer, context, scriptable2, scriptable3, objArr);
                }
            });
        }
    }

    static /* synthetic */ Object lambda$setSetter$1(BiConsumer biConsumer, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        biConsumer.accept(scriptable2, objArr[0]);
        return Undefined.instance;
    }

    public void replaceWith(LambdaAccessorSlot lambdaAccessorSlot) {
        this.getterFunction = lambdaAccessorSlot.getterFunction;
        this.getter = lambdaAccessorSlot.getter;
        this.setterFunction = lambdaAccessorSlot.setterFunction;
        this.setter = lambdaAccessorSlot.setter;
        setAttributes(lambdaAccessorSlot.getAttributes());
    }
}
