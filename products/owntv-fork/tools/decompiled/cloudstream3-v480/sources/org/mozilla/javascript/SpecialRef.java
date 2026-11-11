package org.mozilla.javascript;

/* JADX INFO: loaded from: classes8.dex */
class SpecialRef extends Ref {
    private static final int SPECIAL_NONE = 0;
    private static final int SPECIAL_PARENT = 2;
    private static final int SPECIAL_PROTO = 1;
    private static final long serialVersionUID = -7521596632456797847L;
    private String name;
    private Scriptable target;
    private int type;

    private SpecialRef(Scriptable scriptable, int i, String str) {
        this.target = scriptable;
        this.type = i;
        this.name = str;
    }

    static Ref createSpecial(Context context, Scriptable scriptable, Object obj, String str) {
        int i;
        Scriptable objectOrNull = ScriptRuntime.toObjectOrNull(context, obj, scriptable);
        if (objectOrNull == null) {
            throw ScriptRuntime.undefReadError(obj, str);
        }
        if (str.equals("__proto__")) {
            i = 1;
        } else {
            if (!str.equals("__parent__")) {
                throw new IllegalArgumentException(str);
            }
            i = 2;
        }
        if (!context.hasFeature(5)) {
            i = 0;
        }
        return new SpecialRef(objectOrNull, i, str);
    }

    @Override // org.mozilla.javascript.Ref
    public Object get(Context context) {
        int i = this.type;
        if (i == 0) {
            return ScriptRuntime.getObjectProp(this.target, this.name, context);
        }
        if (i == 1) {
            return this.target.getPrototype();
        }
        if (i == 2) {
            return this.target.getParentScope();
        }
        throw Kit.codeBug();
    }

    @Override // org.mozilla.javascript.Ref
    @Deprecated
    public Object set(Context context, Object obj) {
        throw new IllegalStateException();
    }

    @Override // org.mozilla.javascript.Ref
    public Object set(Context context, Scriptable scriptable, Object obj) {
        int i = this.type;
        if (i == 0) {
            return ScriptRuntime.setObjectProp(this.target, this.name, obj, context);
        }
        if (i == 1 || i == 2) {
            Scriptable objectOrNull = ScriptRuntime.toObjectOrNull(context, obj, scriptable);
            if (objectOrNull != null) {
                Scriptable parentScope = objectOrNull;
                do {
                    if (parentScope == this.target) {
                        throw Context.reportRuntimeErrorById("msg.cyclic.value", this.name);
                    }
                    if (this.type == 1) {
                        parentScope = parentScope.getPrototype();
                    } else {
                        parentScope = parentScope.getParentScope();
                    }
                } while (parentScope != null);
            }
            int i2 = this.type;
            Scriptable scriptable2 = this.target;
            if (i2 == 1) {
                if ((scriptable2 instanceof ScriptableObject) && !((ScriptableObject) scriptable2).isExtensible() && context.getLanguageVersion() >= 180) {
                    throw ScriptRuntime.typeErrorById("msg.not.extensible", new Object[0]);
                }
                int languageVersion = context.getLanguageVersion();
                Scriptable scriptable3 = this.target;
                if (languageVersion >= 200) {
                    String strTypeof = ScriptRuntime.typeof(scriptable3);
                    if (!"function".equals(strTypeof)) {
                        String strTypeof2 = ScriptRuntime.typeof(obj);
                        if (!NativeSymbol.TYPE_NAME.equals(strTypeof)) {
                            if ((obj != null && !"object".equals(strTypeof2)) || !"object".equals(strTypeof)) {
                                return Undefined.instance;
                            }
                            this.target.setPrototype(objectOrNull);
                            return objectOrNull;
                        }
                    } else {
                        if (obj == null) {
                            this.target.setPrototype(Undefined.SCRIPTABLE_UNDEFINED);
                            return obj;
                        }
                        String strTypeof3 = ScriptRuntime.typeof(obj);
                        if ("object".equals(strTypeof3) || "function".equals(strTypeof3)) {
                            this.target.setPrototype(objectOrNull);
                            return obj;
                        }
                    }
                    return obj;
                }
                scriptable3.setPrototype(objectOrNull);
                return objectOrNull;
            }
            scriptable2.setParentScope(objectOrNull);
            return objectOrNull;
        }
        throw Kit.codeBug();
    }

    @Override // org.mozilla.javascript.Ref
    public boolean has(Context context) {
        if (this.type == 0) {
            return ScriptRuntime.hasObjectElem(this.target, this.name, context);
        }
        return true;
    }

    @Override // org.mozilla.javascript.Ref
    public boolean delete(Context context) {
        if (this.type == 0) {
            return ScriptRuntime.deleteObjectElem(this.target, this.name, context);
        }
        return false;
    }
}
