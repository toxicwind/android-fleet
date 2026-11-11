package org.mozilla.javascript;

import j$.util.Objects;

/* JADX INFO: loaded from: classes8.dex */
public final class ES6Generator extends IdScriptableObject {
    private static final Object GENERATOR_TAG = "Generator";
    private static final int Id_next = 1;
    private static final int Id_return = 2;
    private static final int Id_throw = 3;
    private static final int MAX_PROTOTYPE_ID = 4;
    private static final int SymbolId_iterator = 4;
    private static final long serialVersionUID = 1645892441041347273L;
    private Object delegee;
    private NativeFunction function;
    private int lineNumber;
    private String lineSource;
    private Object savedState;
    private State state = State.SUSPENDED_START;

    enum State {
        SUSPENDED_START,
        SUSPENDED_YIELD,
        EXECUTING,
        COMPLETED
    }

    static ES6Generator init(ScriptableObject scriptableObject, boolean z) {
        ES6Generator eS6Generator = new ES6Generator();
        if (scriptableObject != null) {
            eS6Generator.setParentScope(scriptableObject);
            eS6Generator.setPrototype(getObjectPrototype(scriptableObject));
        }
        eS6Generator.activatePrototypeMap(4);
        if (z) {
            eS6Generator.sealObject();
        }
        if (scriptableObject != null) {
            scriptableObject.associateValue(GENERATOR_TAG, eS6Generator);
        }
        return eS6Generator;
    }

    private ES6Generator() {
    }

    public ES6Generator(Scriptable scriptable, NativeFunction nativeFunction, Object obj) {
        this.function = nativeFunction;
        this.savedState = obj;
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable);
        setParentScope(topLevelScope);
        setPrototype((ES6Generator) ScriptableObject.getTopScopeValue(topLevelScope, GENERATOR_TAG));
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "Generator";
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void initPrototypeId(int i) {
        String str;
        if (i == 4) {
            initPrototypeMethod(GENERATOR_TAG, i, SymbolKey.ITERATOR, "[Symbol.iterator]", 0);
            return;
        }
        if (i == 1) {
            str = "next";
        } else if (i == 2) {
            str = "return";
        } else if (i == 3) {
            str = "throw";
        } else {
            throw new IllegalArgumentException(String.valueOf(i));
        }
        initPrototypeMethod(GENERATOR_TAG, i, str, 1);
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.IdFunctionCall
    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(GENERATOR_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int iMethodId = idFunctionObject.methodId();
        ES6Generator eS6Generator = (ES6Generator) ensureType(scriptable2, ES6Generator.class, idFunctionObject);
        Object obj = objArr.length >= 1 ? objArr[0] : Undefined.instance;
        if (iMethodId == 1) {
            if (eS6Generator.delegee == null) {
                return eS6Generator.resumeLocal(context, scriptable, obj);
            }
            return eS6Generator.resumeDelegee(context, scriptable, obj);
        }
        if (iMethodId == 2) {
            if (eS6Generator.delegee == null) {
                return eS6Generator.resumeAbruptLocal(context, scriptable, 2, obj);
            }
            return eS6Generator.resumeDelegeeReturn(context, scriptable, obj);
        }
        if (iMethodId != 3) {
            if (iMethodId == 4) {
                return scriptable2;
            }
            throw new IllegalArgumentException(String.valueOf(iMethodId));
        }
        if (eS6Generator.delegee == null) {
            return eS6Generator.resumeAbruptLocal(context, scriptable, 1, obj);
        }
        return eS6Generator.resumeDelegeeThrow(context, scriptable, obj);
    }

    private Scriptable resumeDelegee(Context context, Scriptable scriptable, Object obj) {
        Object[] objArr;
        try {
            if (Undefined.instance.equals(obj)) {
                objArr = ScriptRuntime.emptyArgs;
            } else {
                objArr = new Object[]{obj};
            }
            Scriptable scriptableEnsureScriptable = ScriptableObject.ensureScriptable(ScriptRuntime.getPropFunctionAndThis(this.delegee, "next", context, scriptable).call(context, scriptable, ScriptRuntime.lastStoredScriptable(context), objArr));
            if (!ScriptRuntime.isIteratorDone(context, scriptableEnsureScriptable)) {
                return scriptableEnsureScriptable;
            }
            this.delegee = null;
            return resumeLocal(context, scriptable, ScriptableObject.getProperty(scriptableEnsureScriptable, ES6Iterator.VALUE_PROPERTY));
        } catch (RhinoException e) {
            this.delegee = null;
            return resumeAbruptLocal(context, scriptable, 1, e);
        }
    }

    private Scriptable resumeDelegeeThrow(Context context, Scriptable scriptable, Object obj) {
        boolean z = false;
        try {
            Object objCall = ScriptRuntime.getPropFunctionAndThis(this.delegee, "throw", context, scriptable).call(context, scriptable, ScriptRuntime.lastStoredScriptable(context), new Object[]{obj});
            try {
                if (ScriptRuntime.isIteratorDone(context, objCall)) {
                    try {
                        callReturnOptionally(context, scriptable, Undefined.instance);
                        return resumeLocal(context, scriptable, ScriptRuntime.getObjectProp(objCall, ES6Iterator.VALUE_PROPERTY, context, scriptable));
                    } finally {
                        this.delegee = null;
                    }
                }
                return ensureScriptable(objCall);
            } catch (RhinoException e) {
                e = e;
                z = true;
            }
        } catch (RhinoException e2) {
            e = e2;
        }
        if (!z) {
            try {
                callReturnOptionally(context, scriptable, Undefined.instance);
            } catch (RhinoException e3) {
                return resumeAbruptLocal(context, scriptable, 1, e3);
            } finally {
                this.delegee = null;
            }
        }
        return resumeAbruptLocal(context, scriptable, 1, e);
    }

    private Scriptable resumeDelegeeReturn(Context context, Scriptable scriptable, Object obj) {
        try {
            Object objCallReturnOptionally = callReturnOptionally(context, scriptable, obj);
            if (objCallReturnOptionally != null) {
                if (ScriptRuntime.isIteratorDone(context, objCallReturnOptionally)) {
                    this.delegee = null;
                    return resumeAbruptLocal(context, scriptable, 2, ScriptRuntime.getObjectPropNoWarn(objCallReturnOptionally, ES6Iterator.VALUE_PROPERTY, context, scriptable));
                }
                return ensureScriptable(objCallReturnOptionally);
            }
            this.delegee = null;
            return resumeAbruptLocal(context, scriptable, 2, obj);
        } catch (RhinoException e) {
            this.delegee = null;
            return resumeAbruptLocal(context, scriptable, 1, e);
        }
    }

    /* JADX WARN: Code duplicated, block: B:62:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:63:0x00f7  */
    /* JADX WARN: Undo finally extract visitor
    java.lang.NullPointerException: Cannot invoke "Object.hashCode()" because "this.second" is null
    	at jadx.core.utils.Pair.hashCode(Pair.java:35)
    	at java.base/java.util.HashMap.hash(HashMap.java:338)
    	at java.base/java.util.HashMap.getNode(HashMap.java:577)
    	at java.base/java.util.HashMap.containsKey(HashMap.java:603)
    	at jadx.core.dex.visitors.finaly.traverser.state.TraverserGlobalCommonState.hasBlocksBeenCached(TraverserGlobalCommonState.java:35)
    	at jadx.core.dex.visitors.finaly.traverser.handlers.MergePathActivePathTraverserHandler.handle(MergePathActivePathTraverserHandler.java:174)
    	at jadx.core.dex.visitors.finaly.traverser.handlers.AbstractActivePathTraverserHandler.process(AbstractActivePathTraverserHandler.java:19)
    	at jadx.core.dex.visitors.finaly.traverser.TraverserController.processHandlerImplementations(TraverserController.java:43)
    	at jadx.core.dex.visitors.finaly.traverser.TraverserController.advance(TraverserController.java:156)
    	at jadx.core.dex.visitors.finaly.traverser.TraverserController.process(TraverserController.java:79)
    	at jadx.core.dex.visitors.finaly.MarkFinallyVisitor.findCommonInsns(MarkFinallyVisitor.java:404)
    	at jadx.core.dex.visitors.finaly.MarkFinallyVisitor.extractFinally(MarkFinallyVisitor.java:284)
    	at jadx.core.dex.visitors.finaly.MarkFinallyVisitor.processTryBlock(MarkFinallyVisitor.java:202)
    	at jadx.core.dex.visitors.finaly.MarkFinallyVisitor.visit(MarkFinallyVisitor.java:135)
     */
    private Scriptable resumeLocal(Context context, Scriptable scriptable, Object obj) {
        if (this.state == State.COMPLETED) {
            return ES6Iterator.makeIteratorResult(context, scriptable, Boolean.TRUE);
        }
        if (this.state == State.EXECUTING) {
            throw ScriptRuntime.typeErrorById("msg.generator.executing", new Object[0]);
        }
        Scriptable scriptableMakeIteratorResult = ES6Iterator.makeIteratorResult(context, scriptable, Boolean.FALSE);
        this.state = State.EXECUTING;
        try {
            try {
                try {
                    Object objResumeGenerator = this.function.resumeGenerator(context, scriptable, 0, this.savedState, obj);
                    if (!(objResumeGenerator instanceof YieldStarResult)) {
                        ScriptableObject.putProperty(scriptableMakeIteratorResult, ES6Iterator.VALUE_PROPERTY, objResumeGenerator);
                        if (this.state == State.COMPLETED) {
                            ScriptableObject.putProperty(scriptableMakeIteratorResult, ES6Iterator.DONE_PROPERTY, Boolean.TRUE);
                            return scriptableMakeIteratorResult;
                        }
                        this.state = State.SUSPENDED_YIELD;
                        return scriptableMakeIteratorResult;
                    }
                    this.state = State.SUSPENDED_YIELD;
                    try {
                        this.delegee = ScriptRuntime.callIterator(((YieldStarResult) objResumeGenerator).getResult(), context, scriptable);
                        try {
                            Scriptable scriptableResumeDelegee = resumeDelegee(context, scriptable, Undefined.instance);
                            this.state = State.EXECUTING;
                            if (ScriptRuntime.isIteratorDone(context, scriptableResumeDelegee)) {
                                this.state = State.COMPLETED;
                            }
                            if (this.state == State.COMPLETED) {
                                ScriptableObject.putProperty(scriptableMakeIteratorResult, ES6Iterator.DONE_PROPERTY, Boolean.TRUE);
                                return scriptableResumeDelegee;
                            }
                            this.state = State.SUSPENDED_YIELD;
                            return scriptableResumeDelegee;
                        } catch (Throwable th) {
                            this.state = State.EXECUTING;
                            throw th;
                        }
                    } catch (RhinoException e) {
                        Scriptable scriptableResumeAbruptLocal = resumeAbruptLocal(context, scriptable, 1, e);
                        if (this.state == State.COMPLETED) {
                            ScriptableObject.putProperty(scriptableMakeIteratorResult, ES6Iterator.DONE_PROPERTY, Boolean.TRUE);
                        } else {
                            this.state = State.SUSPENDED_YIELD;
                        }
                        return scriptableResumeAbruptLocal;
                    }
                } catch (RhinoException e2) {
                    this.lineNumber = e2.lineNumber();
                    this.lineSource = e2.lineSource();
                    throw e2;
                }
            } catch (JavaScriptException e3) {
                this.state = State.COMPLETED;
                if (e3.getValue() instanceof NativeIterator.StopIteration) {
                    ScriptableObject.putProperty(scriptableMakeIteratorResult, ES6Iterator.VALUE_PROPERTY, ((NativeIterator.StopIteration) e3.getValue()).getValue());
                    if (this.state == State.COMPLETED) {
                        ScriptableObject.putProperty(scriptableMakeIteratorResult, ES6Iterator.DONE_PROPERTY, Boolean.TRUE);
                    } else {
                        this.state = State.SUSPENDED_YIELD;
                    }
                    return scriptableMakeIteratorResult;
                }
                this.lineNumber = e3.lineNumber();
                this.lineSource = e3.lineSource();
                if (e3.getValue() instanceof RhinoException) {
                    throw ((RhinoException) e3.getValue());
                }
                throw e3;
            } catch (NativeGenerator.GeneratorClosedException unused) {
                State state = State.COMPLETED;
                this.state = state;
                if (state == State.COMPLETED) {
                    ScriptableObject.putProperty(scriptableMakeIteratorResult, ES6Iterator.DONE_PROPERTY, Boolean.TRUE);
                } else {
                    this.state = State.SUSPENDED_YIELD;
                }
                return scriptableMakeIteratorResult;
            }
        } catch (Throwable th2) {
            if (this.state == State.COMPLETED) {
                ScriptableObject.putProperty(scriptableMakeIteratorResult, ES6Iterator.DONE_PROPERTY, Boolean.TRUE);
                throw th2;
            }
            this.state = State.SUSPENDED_YIELD;
            throw th2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:53:0x00d5  */
    private Scriptable resumeAbruptLocal(Context context, Scriptable scriptable, int i, Object obj) {
        if (this.state == State.EXECUTING) {
            throw ScriptRuntime.typeErrorById("msg.generator.executing", new Object[0]);
        }
        if (this.state == State.SUSPENDED_START) {
            this.state = State.COMPLETED;
        }
        Scriptable scriptableMakeIteratorResult = ES6Iterator.makeIteratorResult(context, scriptable, Boolean.FALSE);
        if (this.state == State.COMPLETED) {
            if (i == 1) {
                throw new JavaScriptException(obj, this.lineSource, this.lineNumber);
            }
            ScriptableObject.putProperty(scriptableMakeIteratorResult, ES6Iterator.DONE_PROPERTY, Boolean.TRUE);
            return scriptableMakeIteratorResult;
        }
        this.state = State.EXECUTING;
        if (i == 2) {
            if (!(obj instanceof NativeGenerator.GeneratorClosedException)) {
                obj = new NativeGenerator.GeneratorClosedException();
            }
        } else if (obj instanceof JavaScriptException) {
            obj = ((JavaScriptException) obj).getValue();
        } else if (obj instanceof RhinoException) {
            obj = ScriptRuntime.wrapException((Throwable) obj, scriptable, context);
        }
        try {
            try {
                try {
                    ScriptableObject.putProperty(scriptableMakeIteratorResult, ES6Iterator.VALUE_PROPERTY, this.function.resumeGenerator(context, scriptable, i, this.savedState, obj));
                    State state = State.SUSPENDED_YIELD;
                    this.state = state;
                    if (state == State.COMPLETED) {
                        this.delegee = null;
                        ScriptableObject.putProperty(scriptableMakeIteratorResult, ES6Iterator.DONE_PROPERTY, Boolean.TRUE);
                        return scriptableMakeIteratorResult;
                    }
                } catch (RhinoException e) {
                    this.state = State.COMPLETED;
                    this.lineNumber = e.lineNumber();
                    this.lineSource = e.lineSource();
                    throw e;
                }
            } catch (JavaScriptException e2) {
                this.state = State.COMPLETED;
                if (!(e2.getValue() instanceof NativeIterator.StopIteration)) {
                    this.lineNumber = e2.lineNumber();
                    this.lineSource = e2.lineSource();
                    if (e2.getValue() instanceof RhinoException) {
                        throw ((RhinoException) e2.getValue());
                    }
                    throw e2;
                }
                ScriptableObject.putProperty(scriptableMakeIteratorResult, ES6Iterator.VALUE_PROPERTY, ((NativeIterator.StopIteration) e2.getValue()).getValue());
                if (this.state == State.COMPLETED) {
                    this.delegee = null;
                    ScriptableObject.putProperty(scriptableMakeIteratorResult, ES6Iterator.DONE_PROPERTY, Boolean.TRUE);
                }
            } catch (NativeGenerator.GeneratorClosedException unused) {
                State state2 = State.COMPLETED;
                this.state = state2;
                if (state2 == State.COMPLETED) {
                    this.delegee = null;
                    ScriptableObject.putProperty(scriptableMakeIteratorResult, ES6Iterator.DONE_PROPERTY, Boolean.TRUE);
                }
            }
            return scriptableMakeIteratorResult;
        } catch (Throwable th) {
            if (this.state != State.COMPLETED) {
                throw th;
            }
            this.delegee = null;
            ScriptableObject.putProperty(scriptableMakeIteratorResult, ES6Iterator.DONE_PROPERTY, Boolean.TRUE);
            throw th;
        }
    }

    private Object callReturnOptionally(Context context, Scriptable scriptable, Object obj) {
        Object[] objArr = Undefined.instance.equals(obj) ? ScriptRuntime.emptyArgs : new Object[]{obj};
        Object objectPropNoWarn = ScriptRuntime.getObjectPropNoWarn(this.delegee, "return", context, scriptable);
        if (Undefined.instance.equals(objectPropNoWarn)) {
            return null;
        }
        if (!(objectPropNoWarn instanceof Callable)) {
            throw ScriptRuntime.typeErrorById("msg.isnt.function", "return", ScriptRuntime.typeof(objectPropNoWarn));
        }
        return ((Callable) objectPropNoWarn).call(context, scriptable, ensureScriptable(this.delegee), objArr);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(Symbol symbol) {
        return SymbolKey.ITERATOR.equals(symbol) ? 4 : 0;
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(String str) {
        String str2;
        int i;
        int length = str.length();
        if (length == 4) {
            str2 = "next";
            i = 1;
        } else if (length == 5) {
            str2 = "throw";
            i = 3;
        } else if (length == 6) {
            str2 = "return";
            i = 2;
        } else {
            str2 = null;
            i = 0;
        }
        if (Objects.equals(str2, str)) {
            return i;
        }
        return 0;
    }

    public static final class YieldStarResult {
        private Object result;

        public YieldStarResult(Object obj) {
            this.result = obj;
        }

        Object getResult() {
            return this.result;
        }
    }
}
