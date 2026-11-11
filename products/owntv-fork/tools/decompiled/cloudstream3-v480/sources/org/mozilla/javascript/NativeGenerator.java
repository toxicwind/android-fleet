package org.mozilla.javascript;

/* JADX INFO: loaded from: classes8.dex */
public final class NativeGenerator extends IdScriptableObject {
    public static final int GENERATOR_CLOSE = 2;
    public static final int GENERATOR_SEND = 0;
    private static final Object GENERATOR_TAG = "Generator";
    public static final int GENERATOR_THROW = 1;
    private static final int Id___iterator__ = 5;
    private static final int Id_close = 1;
    private static final int Id_next = 2;
    private static final int Id_send = 3;
    private static final int Id_throw = 4;
    private static final int MAX_PROTOTYPE_ID = 5;
    private static final long serialVersionUID = 1645892441041347273L;
    private boolean firstTime = true;
    private NativeFunction function;
    private int lineNumber;
    private String lineSource;
    private boolean locked;
    private Object savedState;

    public static class GeneratorClosedException extends RuntimeException {
        private static final long serialVersionUID = 2561315658662379681L;
    }

    static NativeGenerator init(ScriptableObject scriptableObject, boolean z) {
        NativeGenerator nativeGenerator = new NativeGenerator();
        if (scriptableObject != null) {
            nativeGenerator.setParentScope(scriptableObject);
            nativeGenerator.setPrototype(getObjectPrototype(scriptableObject));
        }
        nativeGenerator.activatePrototypeMap(5);
        if (z) {
            nativeGenerator.sealObject();
        }
        if (scriptableObject != null) {
            scriptableObject.associateValue(GENERATOR_TAG, nativeGenerator);
        }
        return nativeGenerator;
    }

    private NativeGenerator() {
    }

    public NativeGenerator(Scriptable scriptable, NativeFunction nativeFunction, Object obj) {
        this.function = nativeFunction;
        this.savedState = obj;
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable);
        setParentScope(topLevelScope);
        setPrototype((NativeGenerator) ScriptableObject.getTopScopeValue(topLevelScope, GENERATOR_TAG));
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "Generator";
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void initPrototypeId(int i) {
        String str;
        int i2 = 1;
        if (i == 1) {
            str = "close";
        } else if (i != 2) {
            if (i == 3) {
                str = "send";
            } else if (i == 4) {
                str = "throw";
            } else if (i == 5) {
                str = NativeIterator.ITERATOR_PROPERTY_NAME;
            } else {
                throw new IllegalArgumentException(String.valueOf(i));
            }
            i2 = 0;
        } else {
            str = "next";
        }
        initPrototypeMethod(GENERATOR_TAG, i, str, i2);
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.IdFunctionCall
    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(GENERATOR_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int iMethodId = idFunctionObject.methodId();
        NativeGenerator nativeGenerator = (NativeGenerator) ensureType(scriptable2, NativeGenerator.class, idFunctionObject);
        if (iMethodId == 1) {
            return nativeGenerator.resume(context, scriptable, 2, new GeneratorClosedException());
        }
        if (iMethodId == 2) {
            nativeGenerator.firstTime = false;
            return nativeGenerator.resume(context, scriptable, 0, Undefined.instance);
        }
        if (iMethodId != 3) {
            if (iMethodId == 4) {
                return nativeGenerator.resume(context, scriptable, 1, objArr.length > 0 ? objArr[0] : Undefined.instance);
            }
            if (iMethodId == 5) {
                return scriptable2;
            }
            throw new IllegalArgumentException(String.valueOf(iMethodId));
        }
        Object obj = objArr.length > 0 ? objArr[0] : Undefined.instance;
        if (nativeGenerator.firstTime && !obj.equals(Undefined.instance)) {
            throw ScriptRuntime.typeErrorById("msg.send.newborn", new Object[0]);
        }
        return nativeGenerator.resume(context, scriptable, 0, obj);
    }

    /* JADX WARN: Code duplicated, block: B:57:0x0071  */
    /* JADX WARN: Code duplicated, block: B:79:0x006c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    private Object resume(Context context, Scriptable scriptable, int i, Object obj) throws Throwable {
        if (this.savedState == null) {
            if (i == 2) {
                return Undefined.instance;
            }
            if (i != 1) {
                obj = NativeIterator.getStopIterationObject(scriptable);
            }
            throw new JavaScriptException(obj, this.lineSource, this.lineNumber);
        }
        try {
            try {
                try {
                    try {
                        synchronized (this) {
                            try {
                                if (this.locked) {
                                    throw ScriptRuntime.typeErrorById("msg.already.exec.gen", new Object[0]);
                                }
                                this.locked = true;
                                Object objResumeGenerator = this.function.resumeGenerator(context, scriptable, i, this.savedState, obj);
                                synchronized (this) {
                                    this.locked = false;
                                }
                                if (i == 2) {
                                    this.savedState = null;
                                }
                                return objResumeGenerator;
                            } catch (Throwable th) {
                                th = th;
                                throw th;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (GeneratorClosedException unused) {
                    Object obj2 = Undefined.instance;
                    synchronized (this) {
                        this.locked = false;
                    }
                    if (i == 2) {
                        this.savedState = null;
                    }
                    return obj2;
                } catch (RhinoException e) {
                    e = e;
                    RhinoException rhinoException = e;
                    this.lineNumber = rhinoException.lineNumber();
                    this.lineSource = rhinoException.lineSource();
                    this.savedState = null;
                    throw rhinoException;
                } catch (Throwable th3) {
                    th = th3;
                    Throwable th4 = th;
                    synchronized (this) {
                        this.locked = false;
                    }
                    if (i != 2) {
                        throw th4;
                    }
                    this.savedState = null;
                    throw th4;
                }
            } catch (GeneratorClosedException unused2) {
                Object obj3 = Undefined.instance;
                synchronized (this) {
                    this.locked = false;
                    if (i == 2) {
                        this.savedState = null;
                    }
                    return obj3;
                }
            } catch (RhinoException e2) {
                e = e2;
                RhinoException rhinoException2 = e;
                this.lineNumber = rhinoException2.lineNumber();
                this.lineSource = rhinoException2.lineSource();
                this.savedState = null;
                throw rhinoException2;
            }
        } catch (Throwable th5) {
            th = th5;
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(String str) {
        str.hashCode();
        switch (str) {
            case "__iterator__":
                return 5;
            case "next":
                return 2;
            case "send":
                return 3;
            case "close":
                return 1;
            case "throw":
                return 4;
            default:
                return 0;
        }
    }
}
