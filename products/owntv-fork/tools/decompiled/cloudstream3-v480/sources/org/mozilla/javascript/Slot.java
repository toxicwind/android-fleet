package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* JADX INFO: loaded from: classes8.dex */
public class Slot implements Serializable {
    private static final long serialVersionUID = -6090581677123995491L;
    private short attributes;
    int indexOrHash;
    Object name;
    transient Slot next;
    transient Slot orderedNext;
    Object value;

    Function getGetterFunction(String str, Scriptable scriptable) {
        return null;
    }

    Function getSetterFunction(String str, Scriptable scriptable) {
        return null;
    }

    boolean isSameGetterFunction(Object obj) {
        return false;
    }

    boolean isSameSetterFunction(Object obj) {
        return false;
    }

    boolean isSetterSlot() {
        return false;
    }

    boolean isValueSlot() {
        return true;
    }

    Slot(Object obj, int i, int i2) {
        this.name = obj;
        this.indexOrHash = obj != null ? obj.hashCode() : i;
        this.attributes = (short) i2;
    }

    Slot copySlot() {
        Slot slot = new Slot(this);
        slot.next = null;
        slot.orderedNext = null;
        return slot;
    }

    protected Slot(Slot slot) {
        this.name = slot.name;
        this.indexOrHash = slot.indexOrHash;
        this.attributes = slot.attributes;
        this.value = slot.value;
        this.next = slot.next;
        this.orderedNext = slot.orderedNext;
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        Object obj = this.name;
        if (obj != null) {
            this.indexOrHash = obj.hashCode();
        }
    }

    public final boolean setValue(Object obj, Scriptable scriptable, Scriptable scriptable2) {
        return setValue(obj, scriptable, scriptable2, Context.isCurrentContextStrict());
    }

    public boolean setValue(Object obj, Scriptable scriptable, Scriptable scriptable2, boolean z) {
        if ((this.attributes & 1) != 0) {
            if (z) {
                throw ScriptRuntime.typeErrorById("msg.modify.readonly", this.name);
            }
            return true;
        }
        if (scriptable != scriptable2) {
            return false;
        }
        this.value = obj;
        return true;
    }

    public Object getValue(Scriptable scriptable) {
        return this.value;
    }

    int getAttributes() {
        return this.attributes;
    }

    void setAttributes(int i) {
        ScriptableObject.checkValidAttributes(i);
        this.attributes = (short) i;
    }

    ScriptableObject getPropertyDescriptor(Context context, Scriptable scriptable) {
        return ScriptableObject.buildDataDescriptor(scriptable, this.value, this.attributes);
    }

    protected void throwNoSetterException(Scriptable scriptable, Object obj) {
        String str;
        Context context = Context.getContext();
        if (context.isStrictMode() || context.hasFeature(11)) {
            if (this.name == null) {
                str = "";
            } else {
                str = "[" + scriptable.getClassName() + "]." + String.valueOf(this.name);
            }
            throw ScriptRuntime.typeErrorById("msg.set.prop.no.setter", str, Context.toString(obj));
        }
    }
}
