package org.mozilla.javascript;

import java.util.function.Consumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes8.dex */
public class LambdaSlot extends Slot {
    private static final long serialVersionUID = -3046681698806493052L;
    transient Supplier<Object> getter;
    transient Consumer<Object> setter;

    @Override // org.mozilla.javascript.Slot
    boolean isSetterSlot() {
        return false;
    }

    @Override // org.mozilla.javascript.Slot
    boolean isValueSlot() {
        return false;
    }

    LambdaSlot(Object obj, int i) {
        super(obj, i, 0);
    }

    LambdaSlot(Slot slot) {
        super(slot);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.mozilla.javascript.Slot
    public LambdaSlot copySlot() {
        LambdaSlot lambdaSlot = new LambdaSlot(this);
        lambdaSlot.value = this.value;
        lambdaSlot.getter = this.getter;
        lambdaSlot.setter = this.setter;
        lambdaSlot.next = null;
        lambdaSlot.orderedNext = null;
        return lambdaSlot;
    }

    @Override // org.mozilla.javascript.Slot
    ScriptableObject getPropertyDescriptor(Context context, Scriptable scriptable) {
        ScriptableObject scriptableObject = (ScriptableObject) context.newObject(scriptable);
        Supplier<Object> supplier = this.getter;
        if (supplier != null) {
            scriptableObject.defineProperty(ES6Iterator.VALUE_PROPERTY, supplier.get(), 0);
        } else {
            scriptableObject.defineProperty(ES6Iterator.VALUE_PROPERTY, this.value, 0);
        }
        scriptableObject.setCommonDescriptorProperties(getAttributes(), true);
        return scriptableObject;
    }

    @Override // org.mozilla.javascript.Slot
    public boolean setValue(Object obj, Scriptable scriptable, Scriptable scriptable2, boolean z) {
        Consumer<Object> consumer = this.setter;
        if (consumer == null) {
            return super.setValue(obj, scriptable, scriptable2, z);
        }
        if (scriptable != scriptable2) {
            return false;
        }
        consumer.accept(obj);
        return true;
    }

    @Override // org.mozilla.javascript.Slot
    public Object getValue(Scriptable scriptable) {
        Supplier<Object> supplier = this.getter;
        if (supplier != null) {
            return supplier.get();
        }
        return super.getValue(scriptable);
    }
}
