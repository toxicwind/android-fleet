package org.mozilla.javascript;

/* JADX INFO: loaded from: classes8.dex */
public class AccessorSlot extends Slot {
    private static final long serialVersionUID = 1677840254177335827L;
    transient Getter getter;
    transient Setter setter;

    interface Getter {
        Function asGetterFunction(String str, Scriptable scriptable);

        Object getValue(Scriptable scriptable);

        boolean isSameGetterFunction(Object obj);
    }

    interface Setter {
        Function asSetterFunction(String str, Scriptable scriptable);

        boolean isSameSetterFunction(Object obj);

        boolean setValue(Object obj, Scriptable scriptable, Scriptable scriptable2);
    }

    @Override // org.mozilla.javascript.Slot
    boolean isSetterSlot() {
        return true;
    }

    @Override // org.mozilla.javascript.Slot
    boolean isValueSlot() {
        return false;
    }

    AccessorSlot(Object obj, int i) {
        super(obj, i, 0);
    }

    AccessorSlot(Slot slot) {
        super(slot);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.mozilla.javascript.Slot
    public AccessorSlot copySlot() {
        AccessorSlot accessorSlot = new AccessorSlot(this);
        accessorSlot.value = this.value;
        accessorSlot.getter = this.getter;
        accessorSlot.setter = this.setter;
        accessorSlot.next = null;
        accessorSlot.orderedNext = null;
        return accessorSlot;
    }

    @Override // org.mozilla.javascript.Slot
    ScriptableObject getPropertyDescriptor(Context context, Scriptable scriptable) {
        ScriptableObject scriptableObject = (ScriptableObject) context.newObject(scriptable);
        int attributes = getAttributes();
        boolean z = context.getLanguageVersion() >= 200;
        Getter getter = this.getter;
        if (z) {
            if (getter == null && this.setter == null) {
                scriptableObject.defineProperty("writable", Boolean.valueOf((attributes & 1) == 0), 0);
            }
        } else {
            scriptableObject.setCommonDescriptorProperties(attributes, getter == null && this.setter == null);
        }
        String string = this.name == null ? "f" : this.name.toString();
        Getter getter2 = this.getter;
        if (getter2 != null) {
            Object objAsGetterFunction = getter2.asGetterFunction(string, scriptable);
            if (objAsGetterFunction == null) {
                objAsGetterFunction = Undefined.instance;
            }
            scriptableObject.defineProperty("get", objAsGetterFunction, 0);
        }
        Setter setter = this.setter;
        if (setter != null) {
            Object objAsSetterFunction = setter.asSetterFunction(string, scriptable);
            if (objAsSetterFunction == null) {
                objAsSetterFunction = Undefined.instance;
            }
            scriptableObject.defineProperty("set", objAsSetterFunction, 0);
        } else if (z) {
            scriptableObject.defineProperty("set", Undefined.instance, 0);
        }
        if (z) {
            scriptableObject.defineProperty("enumerable", Boolean.valueOf((attributes & 2) == 0), 0);
            scriptableObject.defineProperty("configurable", Boolean.valueOf((attributes & 4) == 0), 0);
        }
        return scriptableObject;
    }

    @Override // org.mozilla.javascript.Slot
    public boolean setValue(Object obj, Scriptable scriptable, Scriptable scriptable2, boolean z) {
        Setter setter = this.setter;
        if (setter == null) {
            if (this.getter != null) {
                throwNoSetterException(scriptable2, obj);
                return true;
            }
            return super.setValue(obj, scriptable, scriptable2, z);
        }
        return setter.setValue(obj, scriptable, scriptable2);
    }

    @Override // org.mozilla.javascript.Slot
    public Object getValue(Scriptable scriptable) {
        Getter getter = this.getter;
        if (getter != null) {
            return getter.getValue(scriptable);
        }
        return super.getValue(scriptable);
    }

    @Override // org.mozilla.javascript.Slot
    Function getSetterFunction(String str, Scriptable scriptable) {
        Setter setter = this.setter;
        if (setter == null) {
            return null;
        }
        return setter.asSetterFunction(str, scriptable);
    }

    @Override // org.mozilla.javascript.Slot
    Function getGetterFunction(String str, Scriptable scriptable) {
        Getter getter = this.getter;
        if (getter == null) {
            return null;
        }
        return getter.asGetterFunction(str, scriptable);
    }

    @Override // org.mozilla.javascript.Slot
    boolean isSameGetterFunction(Object obj) {
        if (obj == Scriptable.NOT_FOUND) {
            return true;
        }
        Getter getter = this.getter;
        if (getter == null) {
            return ScriptRuntime.shallowEq(Undefined.instance, obj);
        }
        return getter.isSameGetterFunction(obj);
    }

    @Override // org.mozilla.javascript.Slot
    boolean isSameSetterFunction(Object obj) {
        if (obj == Scriptable.NOT_FOUND) {
            return true;
        }
        Setter setter = this.setter;
        if (setter == null) {
            return ScriptRuntime.shallowEq(Undefined.instance, obj);
        }
        return setter.isSameSetterFunction(obj);
    }

    static final class MemberBoxGetter implements Getter {
        final MemberBox member;

        MemberBoxGetter(MemberBox memberBox) {
            this.member = memberBox;
        }

        @Override // org.mozilla.javascript.AccessorSlot.Getter
        public Object getValue(Scriptable scriptable) {
            Object obj = this.member.delegateTo;
            MemberBox memberBox = this.member;
            return obj == null ? memberBox.invoke(scriptable, ScriptRuntime.emptyArgs) : memberBox.invoke(memberBox.delegateTo, new Object[]{scriptable});
        }

        @Override // org.mozilla.javascript.AccessorSlot.Getter
        public Function asGetterFunction(String str, Scriptable scriptable) {
            return this.member.asGetterFunction(str, scriptable);
        }

        @Override // org.mozilla.javascript.AccessorSlot.Getter
        public boolean isSameGetterFunction(Object obj) {
            return this.member.isSameGetterFunction(obj);
        }
    }

    static final class FunctionGetter implements Getter {
        final Object target;

        FunctionGetter(Object obj) {
            this.target = obj;
        }

        @Override // org.mozilla.javascript.AccessorSlot.Getter
        public Object getValue(Scriptable scriptable) {
            Object obj = this.target;
            if (obj instanceof Function) {
                Function function = (Function) obj;
                return function.call(Context.getContext(), function.getParentScope(), scriptable, ScriptRuntime.emptyArgs);
            }
            return Undefined.instance;
        }

        @Override // org.mozilla.javascript.AccessorSlot.Getter
        public Function asGetterFunction(String str, Scriptable scriptable) {
            Object obj = this.target;
            if (obj instanceof Function) {
                return (Function) obj;
            }
            return null;
        }

        @Override // org.mozilla.javascript.AccessorSlot.Getter
        public boolean isSameGetterFunction(Object obj) {
            Object obj2 = this.target;
            return ScriptRuntime.shallowEq(obj2 instanceof Function ? (Function) obj2 : Undefined.instance, obj);
        }
    }

    static final class MemberBoxSetter implements Setter {
        final MemberBox member;

        MemberBoxSetter(MemberBox memberBox) {
            this.member = memberBox;
        }

        @Override // org.mozilla.javascript.AccessorSlot.Setter
        public boolean setValue(Object obj, Scriptable scriptable, Scriptable scriptable2) {
            Context context = Context.getContext();
            Class<?>[] clsArr = this.member.argTypes;
            Object objConvertArg = FunctionObject.convertArg(context, scriptable2, obj, FunctionObject.getTypeTag(clsArr[clsArr.length - 1]));
            Object obj2 = this.member.delegateTo;
            MemberBox memberBox = this.member;
            if (obj2 == null) {
                memberBox.invoke(scriptable2, new Object[]{objConvertArg});
            } else {
                memberBox.invoke(memberBox.delegateTo, new Object[]{scriptable2, objConvertArg});
            }
            return true;
        }

        @Override // org.mozilla.javascript.AccessorSlot.Setter
        public Function asSetterFunction(String str, Scriptable scriptable) {
            return this.member.asSetterFunction(str, scriptable);
        }

        @Override // org.mozilla.javascript.AccessorSlot.Setter
        public boolean isSameSetterFunction(Object obj) {
            return this.member.isSameSetterFunction(obj);
        }
    }

    static final class FunctionSetter implements Setter {
        final Object target;

        FunctionSetter(Object obj) {
            this.target = obj;
        }

        @Override // org.mozilla.javascript.AccessorSlot.Setter
        public boolean setValue(Object obj, Scriptable scriptable, Scriptable scriptable2) {
            Object obj2 = this.target;
            if (obj2 instanceof Function) {
                Function function = (Function) obj2;
                function.call(Context.getContext(), function.getParentScope(), scriptable2, new Object[]{obj});
            }
            return true;
        }

        @Override // org.mozilla.javascript.AccessorSlot.Setter
        public Function asSetterFunction(String str, Scriptable scriptable) {
            Object obj = this.target;
            if (obj instanceof Function) {
                return (Function) obj;
            }
            return null;
        }

        @Override // org.mozilla.javascript.AccessorSlot.Setter
        public boolean isSameSetterFunction(Object obj) {
            Object obj2 = this.target;
            return ScriptRuntime.shallowEq(obj2 instanceof Function ? (Function) obj2 : Undefined.instance, obj);
        }
    }
}
