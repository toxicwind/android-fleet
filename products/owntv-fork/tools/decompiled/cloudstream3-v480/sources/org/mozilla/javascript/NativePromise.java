package org.mozilla.javascript;

import androidx.core.app.NotificationCompat;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes8.dex */
public class NativePromise extends ScriptableObject {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private State state = State.PENDING;
    private Object result = null;
    private boolean handled = false;
    private ArrayList<Reaction> fulfillReactions = new ArrayList<>();
    private ArrayList<Reaction> rejectReactions = new ArrayList<>();

    enum ReactionType {
        FULFILL,
        REJECT
    }

    enum State {
        PENDING,
        FULFILLED,
        REJECTED
    }

    static /* synthetic */ Object lambda$makeThenFinally$4(Object obj, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return obj;
    }

    public static void init(Context context, Scriptable scriptable, boolean z) {
        final LambdaConstructor lambdaConstructor = new LambdaConstructor(scriptable, "Promise", 1, 2, new Constructable() { // from class: org.mozilla.javascript.NativePromise$$ExternalSyntheticLambda11
            @Override // org.mozilla.javascript.Constructable
            public final Scriptable construct(Context context2, Scriptable scriptable2, Object[] objArr) {
                return NativePromise.constructor(context2, scriptable2, objArr);
            }
        });
        lambdaConstructor.setPrototypePropertyAttributes(7);
        lambdaConstructor.defineConstructorMethod(scriptable, "resolve", 1, new Callable() { // from class: org.mozilla.javascript.NativePromise$$ExternalSyntheticLambda12
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativePromise.resolve(context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        lambdaConstructor.defineConstructorMethod(scriptable, "reject", 1, new Callable() { // from class: org.mozilla.javascript.NativePromise$$ExternalSyntheticLambda13
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativePromise.reject(context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        lambdaConstructor.defineConstructorMethod(scriptable, "all", 1, new Callable() { // from class: org.mozilla.javascript.NativePromise$$ExternalSyntheticLambda14
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativePromise.all(context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        lambdaConstructor.defineConstructorMethod(scriptable, "allSettled", 1, new Callable() { // from class: org.mozilla.javascript.NativePromise$$ExternalSyntheticLambda15
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativePromise.allSettled(context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        lambdaConstructor.defineConstructorMethod(scriptable, "race", 1, new Callable() { // from class: org.mozilla.javascript.NativePromise$$ExternalSyntheticLambda16
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativePromise.race(context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        lambdaConstructor.defineConstructorMethod(scriptable, "any", 1, new Callable() { // from class: org.mozilla.javascript.NativePromise$$ExternalSyntheticLambda17
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativePromise.any(context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        ScriptRuntimeES6.addSymbolSpecies(context, scriptable, lambdaConstructor);
        lambdaConstructor.definePrototypeMethod(scriptable, "then", 2, new Callable() { // from class: org.mozilla.javascript.NativePromise$$ExternalSyntheticLambda1
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return ((NativePromise) LambdaConstructor.convertThisObject(scriptable3, NativePromise.class)).then(context2, scriptable2, lambdaConstructor, objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "catch", 1, new Callable() { // from class: org.mozilla.javascript.NativePromise$$ExternalSyntheticLambda2
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativePromise.doCatch(context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "finally", 1, new Callable() { // from class: org.mozilla.javascript.NativePromise$$ExternalSyntheticLambda3
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativePromise.doFinally(context2, scriptable2, scriptable3, lambdaConstructor, objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeProperty(SymbolKey.TO_STRING_TAG, "Promise", 3);
        ScriptableObject.defineProperty(scriptable, "Promise", lambdaConstructor, 2);
        if (z) {
            lambdaConstructor.sealObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Scriptable constructor(Context context, Scriptable scriptable, Object[] objArr) {
        Scriptable scriptable2;
        if (objArr.length >= 1) {
            Object obj = objArr[0];
            if (obj instanceof Callable) {
                Callable callable = (Callable) obj;
                NativePromise nativePromise = new NativePromise();
                ResolvingFunctions resolvingFunctions = new ResolvingFunctions(scriptable, nativePromise);
                Scriptable scriptable3 = Undefined.SCRIPTABLE_UNDEFINED;
                if (!context.isStrictMode() && (scriptable2 = context.topCallScope) != null) {
                    scriptable3 = scriptable2;
                }
                try {
                    callable.call(context, scriptable, scriptable3, new Object[]{resolvingFunctions.resolve, resolvingFunctions.reject});
                    return nativePromise;
                } catch (RhinoException e) {
                    resolvingFunctions.reject.call(context, scriptable, scriptable3, new Object[]{getErrorObject(context, scriptable, e)});
                    return nativePromise;
                }
            }
        }
        throw ScriptRuntime.typeErrorById("msg.function.expected", new Object[0]);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "Promise";
    }

    Object getResult() {
        return this.result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object resolve(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (ScriptRuntime.isObject(scriptable2)) {
            return resolveInternal(context, scriptable, scriptable2, objArr.length > 0 ? objArr[0] : Undefined.instance);
        }
        throw ScriptRuntime.typeErrorById("msg.arg.not.object", ScriptRuntime.typeof(scriptable2));
    }

    private static Object resolveInternal(Context context, Scriptable scriptable, Object obj, Object obj2) {
        if ((obj2 instanceof NativePromise) && ScriptRuntime.getObjectProp(obj2, "constructor", context, scriptable) == obj) {
            return obj2;
        }
        Capability capability = new Capability(context, scriptable, obj);
        capability.resolve.call(context, scriptable, Undefined.SCRIPTABLE_UNDEFINED, new Object[]{obj2});
        return capability.promise;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object reject(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!ScriptRuntime.isObject(scriptable2)) {
            throw ScriptRuntime.typeErrorById("msg.arg.not.object", ScriptRuntime.typeof(scriptable2));
        }
        Object obj = objArr.length > 0 ? objArr[0] : Undefined.instance;
        Capability capability = new Capability(context, scriptable, scriptable2);
        capability.reject.call(context, scriptable, Undefined.SCRIPTABLE_UNDEFINED, new Object[]{obj});
        return capability.promise;
    }

    /* JADX WARN: Type inference failed for: r8v5, types: [org.mozilla.javascript.IteratorLikeIterable$Itr] */
    private static Object doAll(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr, boolean z) {
        Capability capability = new Capability(context, scriptable, scriptable2);
        try {
            IteratorLikeIterable iteratorLikeIterable = new IteratorLikeIterable(context, scriptable, ScriptRuntime.callIterator(objArr.length > 0 ? objArr[0] : Undefined.instance, context, scriptable));
            ?? it = iteratorLikeIterable.iterator();
            try {
                try {
                    return new PromiseAllResolver(it, scriptable2, capability, z).resolve(context, scriptable);
                } finally {
                    if (!it.isDone()) {
                        iteratorLikeIterable.close();
                    }
                }
            } catch (RhinoException e) {
                capability.reject.call(context, scriptable, Undefined.SCRIPTABLE_UNDEFINED, new Object[]{getErrorObject(context, scriptable, e)});
                return capability.promise;
            }
        } catch (RhinoException e2) {
            capability.reject.call(context, scriptable, Undefined.SCRIPTABLE_UNDEFINED, new Object[]{getErrorObject(context, scriptable, e2)});
            return capability.promise;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object all(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return doAll(context, scriptable, scriptable2, objArr, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object allSettled(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return doAll(context, scriptable, scriptable2, objArr, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r7v5, types: [org.mozilla.javascript.IteratorLikeIterable$Itr] */
    public static Object race(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Capability capability = new Capability(context, scriptable, scriptable2);
        try {
            IteratorLikeIterable iteratorLikeIterable = new IteratorLikeIterable(context, scriptable, ScriptRuntime.callIterator(objArr.length > 0 ? objArr[0] : Undefined.instance, context, scriptable));
            ?? it = iteratorLikeIterable.iterator();
            try {
                try {
                    return performRace(context, scriptable, it, scriptable2, capability);
                } finally {
                    if (!it.isDone()) {
                        iteratorLikeIterable.close();
                    }
                }
            } catch (RhinoException e) {
                capability.reject.call(context, scriptable, Undefined.SCRIPTABLE_UNDEFINED, new Object[]{getErrorObject(context, scriptable, e)});
                return capability.promise;
            }
        } catch (RhinoException e2) {
            capability.reject.call(context, scriptable, Undefined.SCRIPTABLE_UNDEFINED, new Object[]{getErrorObject(context, scriptable, e2)});
            return capability.promise;
        }
    }

    private static Object performRace(Context context, Scriptable scriptable, IteratorLikeIterable.Itr itr, Scriptable scriptable2, Capability capability) {
        Callable propFunctionAndThis = ScriptRuntime.getPropFunctionAndThis(scriptable2, "resolve", context, scriptable);
        Scriptable scriptableLastStoredScriptable = ScriptRuntime.lastStoredScriptable(context);
        while (true) {
            Object next = Undefined.instance;
            try {
                boolean zHasNext = itr.hasNext();
                if (zHasNext) {
                    next = itr.next();
                }
                if (!zHasNext) {
                    return capability.promise;
                }
                ScriptRuntime.getPropFunctionAndThis(propFunctionAndThis.call(context, scriptable, scriptableLastStoredScriptable, new Object[]{next}), "then", context, scriptable).call(context, scriptable, ScriptRuntime.lastStoredScriptable(context), new Object[]{capability.resolve, capability.reject});
            } catch (Throwable th) {
                itr.setDone(true);
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r8v5, types: [org.mozilla.javascript.IteratorLikeIterable$Itr] */
    public static Object any(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Capability capability = new Capability(context, scriptable, scriptable2);
        try {
            IteratorLikeIterable iteratorLikeIterable = new IteratorLikeIterable(context, scriptable, ScriptRuntime.callIterator(objArr.length > 0 ? objArr[0] : Undefined.instance, context, scriptable));
            ?? it = iteratorLikeIterable.iterator();
            try {
                try {
                    return new PromiseAnyRejector(it, scriptable2, capability).reject(context, scriptable);
                } finally {
                    if (!it.isDone()) {
                        iteratorLikeIterable.close();
                    }
                }
            } catch (RhinoException e) {
                capability.reject.call(context, scriptable, Undefined.SCRIPTABLE_UNDEFINED, new Object[]{getErrorObject(context, scriptable, e)});
                return capability.promise;
            }
        } catch (RhinoException e2) {
            capability.reject.call(context, scriptable, Undefined.SCRIPTABLE_UNDEFINED, new Object[]{getErrorObject(context, scriptable, e2)});
            return capability.promise;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public Object then(final Context context, final Scriptable scriptable, LambdaConstructor lambdaConstructor, Object[] objArr) {
        Callable callable;
        Capability capability = new Capability(context, scriptable, AbstractEcmaObjectOperations.speciesConstructor(context, this, lambdaConstructor));
        Callable callable2 = null;
        if (objArr.length >= 1) {
            Object obj = objArr[0];
            if (obj instanceof Callable) {
                callable = (Callable) obj;
            } else {
                callable = null;
            }
        } else {
            callable = null;
        }
        if (objArr.length >= 2) {
            Object obj2 = objArr[1];
            if (obj2 instanceof Callable) {
                callable2 = (Callable) obj2;
            }
        }
        final Reaction reaction = new Reaction(capability, ReactionType.FULFILL, callable);
        final Reaction reaction2 = new Reaction(capability, ReactionType.REJECT, callable2);
        if (this.state == State.PENDING) {
            this.fulfillReactions.add(reaction);
            this.rejectReactions.add(reaction2);
        } else if (this.state == State.FULFILLED) {
            context.enqueueMicrotask(new Runnable() { // from class: org.mozilla.javascript.NativePromise$$ExternalSyntheticLambda9
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m10911lambda$then$2$orgmozillajavascriptNativePromise(reaction, context, scriptable);
                }
            });
        } else {
            markHandled(context);
            context.enqueueMicrotask(new Runnable() { // from class: org.mozilla.javascript.NativePromise$$ExternalSyntheticLambda10
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m10912lambda$then$3$orgmozillajavascriptNativePromise(reaction2, context, scriptable);
                }
            });
        }
        return capability.promise;
    }

    /* JADX INFO: renamed from: lambda$then$2$org-mozilla-javascript-NativePromise, reason: not valid java name */
    /* synthetic */ void m10911lambda$then$2$orgmozillajavascriptNativePromise(Reaction reaction, Context context, Scriptable scriptable) {
        reaction.invoke(context, scriptable, this.result);
    }

    /* JADX INFO: renamed from: lambda$then$3$org-mozilla-javascript-NativePromise, reason: not valid java name */
    /* synthetic */ void m10912lambda$then$3$orgmozillajavascriptNativePromise(Reaction reaction, Context context, Scriptable scriptable) {
        reaction.invoke(context, scriptable, this.result);
    }

    private void markHandled(Context context) {
        if (this.handled) {
            return;
        }
        context.getUnhandledPromiseTracker().promiseHandled(this);
        this.handled = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object doCatch(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return ScriptRuntime.getPropFunctionAndThis(ScriptRuntime.toObject(context, scriptable, scriptable2), "then", context, scriptable).call(context, scriptable, ScriptRuntime.lastStoredScriptable(context), new Object[]{Undefined.instance, objArr.length > 0 ? objArr[0] : Undefined.instance});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object doFinally(Context context, Scriptable scriptable, Scriptable scriptable2, LambdaConstructor lambdaConstructor, Object[] objArr) {
        Object objMakeCatchFinally;
        if (!ScriptRuntime.isObject(scriptable2)) {
            throw ScriptRuntime.typeErrorById("msg.arg.not.object", ScriptRuntime.typeof(scriptable2));
        }
        Object obj = objArr.length > 0 ? objArr[0] : Undefined.SCRIPTABLE_UNDEFINED;
        Constructable constructableSpeciesConstructor = AbstractEcmaObjectOperations.speciesConstructor(context, scriptable2, lambdaConstructor);
        if (obj instanceof Callable) {
            Callable callable = (Callable) obj;
            Callable callableMakeThenFinally = makeThenFinally(scriptable, constructableSpeciesConstructor, callable);
            objMakeCatchFinally = makeCatchFinally(scriptable, constructableSpeciesConstructor, callable);
            obj = callableMakeThenFinally;
        } else {
            objMakeCatchFinally = obj;
        }
        return ScriptRuntime.getPropFunctionAndThis(scriptable2, "then", context, scriptable).call(context, scriptable, ScriptRuntime.lastStoredScriptable(context), new Object[]{obj, objMakeCatchFinally});
    }

    private static Callable makeThenFinally(final Scriptable scriptable, final Object obj, final Callable callable) {
        return new LambdaFunction(scriptable, 1, new Callable() { // from class: org.mozilla.javascript.NativePromise$$ExternalSyntheticLambda6
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativePromise.lambda$makeThenFinally$5(scriptable, callable, obj, context, scriptable2, scriptable3, objArr);
            }
        });
    }

    static /* synthetic */ Object lambda$makeThenFinally$5(Scriptable scriptable, Callable callable, Object obj, Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
        final Object obj2 = objArr.length > 0 ? objArr[0] : Undefined.instance;
        return ScriptRuntime.getPropFunctionAndThis(resolveInternal(context, scriptable, obj, callable.call(context, scriptable2, Undefined.SCRIPTABLE_UNDEFINED, ScriptRuntime.emptyArgs)), "then", context, scriptable).call(context, scriptable, ScriptRuntime.lastStoredScriptable(context), new Object[]{new LambdaFunction(scriptable, 0, new Callable() { // from class: org.mozilla.javascript.NativePromise$$ExternalSyntheticLambda4
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable4, Scriptable scriptable5, Object[] objArr2) {
                return NativePromise.lambda$makeThenFinally$4(obj2, context2, scriptable4, scriptable5, objArr2);
            }
        })});
    }

    private static Callable makeCatchFinally(final Scriptable scriptable, final Object obj, final Callable callable) {
        return new LambdaFunction(scriptable, 1, new Callable() { // from class: org.mozilla.javascript.NativePromise$$ExternalSyntheticLambda8
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativePromise.lambda$makeCatchFinally$7(scriptable, callable, obj, context, scriptable2, scriptable3, objArr);
            }
        });
    }

    static /* synthetic */ Object lambda$makeCatchFinally$7(Scriptable scriptable, Callable callable, Object obj, Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
        final Object obj2 = objArr.length > 0 ? objArr[0] : Undefined.instance;
        return ScriptRuntime.getPropFunctionAndThis(resolveInternal(context, scriptable, obj, callable.call(context, scriptable2, Undefined.SCRIPTABLE_UNDEFINED, ScriptRuntime.emptyArgs)), "then", context, scriptable).call(context, scriptable, ScriptRuntime.lastStoredScriptable(context), new Object[]{new LambdaFunction(scriptable, 0, new Callable() { // from class: org.mozilla.javascript.NativePromise$$ExternalSyntheticLambda7
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable4, Scriptable scriptable5, Object[] objArr2) {
                return NativePromise.lambda$makeCatchFinally$6(obj2, context2, scriptable4, scriptable5, objArr2);
            }
        })});
    }

    static /* synthetic */ Object lambda$makeCatchFinally$6(Object obj, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        throw new JavaScriptException(obj, null, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Object fulfillPromise(final Context context, final Scriptable scriptable, final Object obj) {
        this.result = obj;
        ArrayList<Reaction> arrayList = this.fulfillReactions;
        this.fulfillReactions = new ArrayList<>();
        if (!this.rejectReactions.isEmpty()) {
            this.rejectReactions = new ArrayList<>();
        }
        this.state = State.FULFILLED;
        for (final Reaction reaction : arrayList) {
            context.enqueueMicrotask(new Runnable() { // from class: org.mozilla.javascript.NativePromise$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    reaction.invoke(context, scriptable, obj);
                }
            });
        }
        return Undefined.instance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Object rejectPromise(final Context context, final Scriptable scriptable, final Object obj) {
        this.result = obj;
        ArrayList<Reaction> arrayList = this.rejectReactions;
        this.rejectReactions = new ArrayList<>();
        if (!this.fulfillReactions.isEmpty()) {
            this.fulfillReactions = new ArrayList<>();
        }
        this.state = State.REJECTED;
        context.getUnhandledPromiseTracker().promiseRejected(this);
        for (final Reaction reaction : arrayList) {
            context.enqueueMicrotask(new Runnable() { // from class: org.mozilla.javascript.NativePromise$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    reaction.invoke(context, scriptable, obj);
                }
            });
        }
        if (!arrayList.isEmpty()) {
            markHandled(context);
        }
        return Undefined.instance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callThenable(Context context, Scriptable scriptable, Object obj, Callable callable) {
        Scriptable scriptable2;
        ResolvingFunctions resolvingFunctions = new ResolvingFunctions(scriptable, this);
        if (obj instanceof Scriptable) {
            scriptable2 = (Scriptable) obj;
        } else {
            scriptable2 = Undefined.SCRIPTABLE_UNDEFINED;
        }
        try {
            callable.call(context, scriptable, scriptable2, new Object[]{resolvingFunctions.resolve, resolvingFunctions.reject});
        } catch (RhinoException e) {
            resolvingFunctions.reject.call(context, scriptable, Undefined.SCRIPTABLE_UNDEFINED, new Object[]{getErrorObject(context, scriptable, e)});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object getErrorObject(Context context, Scriptable scriptable, RhinoException rhinoException) {
        if (rhinoException instanceof JavaScriptException) {
            return ((JavaScriptException) rhinoException).getValue();
        }
        TopLevel.NativeErrors nativeErrors = TopLevel.NativeErrors.Error;
        if (rhinoException instanceof EcmaError) {
            String name = ((EcmaError) rhinoException).getName();
            name.hashCode();
            switch (name) {
                case "TypeError":
                    nativeErrors = TopLevel.NativeErrors.TypeError;
                    break;
                case "InternalError":
                    nativeErrors = TopLevel.NativeErrors.InternalError;
                    break;
                case "EvalError":
                    nativeErrors = TopLevel.NativeErrors.EvalError;
                    break;
                case "JavaException":
                    nativeErrors = TopLevel.NativeErrors.JavaException;
                    break;
                case "URIError":
                    nativeErrors = TopLevel.NativeErrors.URIError;
                    break;
                case "RangeError":
                    nativeErrors = TopLevel.NativeErrors.RangeError;
                    break;
                case "ReferenceError":
                    nativeErrors = TopLevel.NativeErrors.ReferenceError;
                    break;
                case "SyntaxError":
                    nativeErrors = TopLevel.NativeErrors.SyntaxError;
                    break;
            }
        }
        return ScriptRuntime.newNativeError(context, scriptable, nativeErrors, new Object[]{rhinoException.getMessage()});
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class ResolvingFunctions {
        private boolean alreadyResolved = false;
        LambdaFunction reject;
        LambdaFunction resolve;

        ResolvingFunctions(Scriptable scriptable, final NativePromise nativePromise) {
            this.resolve = new LambdaFunction(scriptable, 1, new Callable() { // from class: org.mozilla.javascript.NativePromise$ResolvingFunctions$$ExternalSyntheticLambda0
                @Override // org.mozilla.javascript.Callable
                public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                    return this.f$0.m10917xc2c271d7(nativePromise, context, scriptable2, scriptable3, objArr);
                }
            });
            this.reject = new LambdaFunction(scriptable, 1, new Callable() { // from class: org.mozilla.javascript.NativePromise$ResolvingFunctions$$ExternalSyntheticLambda1
                @Override // org.mozilla.javascript.Callable
                public final Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                    return this.f$0.m10918xe8567ad8(nativePromise, context, scriptable2, scriptable3, objArr);
                }
            });
        }

        /* JADX INFO: renamed from: lambda$new$0$org-mozilla-javascript-NativePromise$ResolvingFunctions, reason: not valid java name */
        /* synthetic */ Object m10917xc2c271d7(NativePromise nativePromise, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
            return resolve(context, scriptable, nativePromise, objArr.length > 0 ? objArr[0] : Undefined.instance);
        }

        /* JADX INFO: renamed from: lambda$new$1$org-mozilla-javascript-NativePromise$ResolvingFunctions, reason: not valid java name */
        /* synthetic */ Object m10918xe8567ad8(NativePromise nativePromise, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
            return reject(context, scriptable, nativePromise, objArr.length > 0 ? objArr[0] : Undefined.instance);
        }

        private Object reject(Context context, Scriptable scriptable, NativePromise nativePromise, Object obj) {
            if (this.alreadyResolved) {
                return Undefined.instance;
            }
            this.alreadyResolved = true;
            return nativePromise.rejectPromise(context, scriptable, obj);
        }

        private Object resolve(final Context context, final Scriptable scriptable, final NativePromise nativePromise, final Object obj) {
            if (this.alreadyResolved) {
                return Undefined.instance;
            }
            this.alreadyResolved = true;
            if (obj == nativePromise) {
                return nativePromise.rejectPromise(context, scriptable, ScriptRuntime.newNativeError(context, scriptable, TopLevel.NativeErrors.TypeError, new Object[]{"No promise self-resolution"}));
            }
            if (!ScriptRuntime.isObject(obj)) {
                return nativePromise.fulfillPromise(context, scriptable, obj);
            }
            final Object property = ScriptableObject.getProperty(ScriptableObject.ensureScriptable(obj), "then");
            if (!(property instanceof Callable)) {
                return nativePromise.fulfillPromise(context, scriptable, obj);
            }
            context.enqueueMicrotask(new Runnable() { // from class: org.mozilla.javascript.NativePromise$ResolvingFunctions$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    nativePromise.callThenable(context, scriptable, obj, (Callable) property);
                }
            });
            return Undefined.instance;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class Reaction {
        Capability capability;
        Callable handler;
        ReactionType reaction;

        Reaction(Capability capability, ReactionType reactionType, Callable callable) {
            ReactionType reactionType2 = ReactionType.REJECT;
            this.capability = capability;
            this.reaction = reactionType;
            this.handler = callable;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void invoke(Context context, Scriptable scriptable, Object obj) {
            try {
                Callable callable = this.handler;
                if (callable == null) {
                    int iOrdinal = this.reaction.ordinal();
                    if (iOrdinal != 0) {
                        if (iOrdinal == 1) {
                            this.capability.reject.call(context, scriptable, Undefined.SCRIPTABLE_UNDEFINED, new Object[]{obj});
                            return;
                        }
                        obj = null;
                    }
                } else {
                    obj = callable.call(context, scriptable, Undefined.SCRIPTABLE_UNDEFINED, new Object[]{obj});
                }
                this.capability.resolve.call(context, scriptable, Undefined.SCRIPTABLE_UNDEFINED, new Object[]{obj});
            } catch (RhinoException e) {
                this.capability.reject.call(context, scriptable, Undefined.SCRIPTABLE_UNDEFINED, new Object[]{NativePromise.getErrorObject(context, scriptable, e)});
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class Capability {
        Object promise;
        Callable reject;
        Callable resolve;
        private Object rawResolve = Undefined.instance;
        private Object rawReject = Undefined.instance;

        Capability(Context context, Scriptable scriptable, Object obj) {
            if (!(obj instanceof Constructable)) {
                throw ScriptRuntime.typeErrorById("msg.constructor.expected", new Object[0]);
            }
            this.promise = ((Constructable) obj).construct(context, scriptable, new Object[]{new LambdaFunction(scriptable, 2, new Callable() { // from class: org.mozilla.javascript.NativePromise$Capability$$ExternalSyntheticLambda0
                @Override // org.mozilla.javascript.Callable
                public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                    return this.f$0.m10913lambda$new$0$orgmozillajavascriptNativePromise$Capability(context2, scriptable2, scriptable3, objArr);
                }
            })});
            Object obj2 = this.rawResolve;
            if (!(obj2 instanceof Callable)) {
                throw ScriptRuntime.typeErrorById("msg.function.expected", new Object[0]);
            }
            this.resolve = (Callable) obj2;
            Object obj3 = this.rawReject;
            if (!(obj3 instanceof Callable)) {
                throw ScriptRuntime.typeErrorById("msg.function.expected", new Object[0]);
            }
            this.reject = (Callable) obj3;
        }

        /* JADX INFO: renamed from: lambda$new$0$org-mozilla-javascript-NativePromise$Capability, reason: not valid java name */
        /* synthetic */ Object m10913lambda$new$0$orgmozillajavascriptNativePromise$Capability(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
            return executor(objArr);
        }

        private Object executor(Object[] objArr) {
            if (!Undefined.isUndefined(this.rawResolve) || !Undefined.isUndefined(this.rawReject)) {
                throw ScriptRuntime.typeErrorById("msg.promise.capability.state", new Object[0]);
            }
            if (objArr.length > 0) {
                this.rawResolve = objArr[0];
            }
            if (objArr.length > 1) {
                this.rawReject = objArr[1];
            }
            return Undefined.instance;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class PromiseAllResolver {
        private static final int MAX_PROMISES = 2097152;
        Capability capability;
        boolean failFast;
        IteratorLikeIterable.Itr iterator;
        Scriptable thisObj;
        final ArrayList<Object> values = new ArrayList<>();
        int remainingElements = 1;

        PromiseAllResolver(IteratorLikeIterable.Itr itr, Scriptable scriptable, Capability capability, boolean z) {
            this.iterator = itr;
            this.thisObj = scriptable;
            this.capability = capability;
            this.failFast = z;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r4v4, types: [org.mozilla.javascript.Callable] */
        /* JADX WARN: Type inference failed for: r8v2, types: [org.mozilla.javascript.Callable] */
        /* JADX WARN: Type inference failed for: r8v3 */
        /* JADX WARN: Type inference failed for: r8v4, types: [org.mozilla.javascript.LambdaFunction] */
        /* JADX WARN: Type inference failed for: r9v2, types: [java.lang.Object[]] */
        Object resolve(Context context, Scriptable scriptable) {
            Callable propFunctionAndThis = ScriptRuntime.getPropFunctionAndThis(this.thisObj, "resolve", context, scriptable);
            Scriptable scriptableLastStoredScriptable = ScriptRuntime.lastStoredScriptable(context);
            for (int i = 0; i != 2097152; i++) {
                Object next = Undefined.instance;
                try {
                    boolean zHasNext = this.iterator.hasNext();
                    if (zHasNext) {
                        next = this.iterator.next();
                    }
                    if (!zHasNext) {
                        int i2 = this.remainingElements - 1;
                        this.remainingElements = i2;
                        if (i2 == 0) {
                            finalResolution(context, scriptable);
                        }
                        return this.capability.promise;
                    }
                    this.values.add(Undefined.instance);
                    Object objCall = propFunctionAndThis.call(context, scriptable, scriptableLastStoredScriptable, new Object[]{next});
                    final PromiseElementResolver promiseElementResolver = new PromiseElementResolver(i);
                    LambdaFunction lambdaFunction = new LambdaFunction(scriptable, 1, new Callable() { // from class: org.mozilla.javascript.NativePromise$PromiseAllResolver$$ExternalSyntheticLambda0
                        @Override // org.mozilla.javascript.Callable
                        public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                            return this.f$0.m10914x5502305d(promiseElementResolver, context2, scriptable2, scriptable3, objArr);
                        }
                    });
                    ?? lambdaFunction2 = this.capability.reject;
                    if (!this.failFast) {
                        lambdaFunction2 = new LambdaFunction(scriptable, 1, new Callable() { // from class: org.mozilla.javascript.NativePromise$PromiseAllResolver$$ExternalSyntheticLambda1
                            @Override // org.mozilla.javascript.Callable
                            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                                return this.f$0.m10915x7a96395e(promiseElementResolver, context2, scriptable2, scriptable3, objArr);
                            }
                        });
                        lambdaFunction2.setStandardPropertyAttributes(3);
                    }
                    this.remainingElements++;
                    ScriptRuntime.getPropFunctionAndThis(objCall, "then", context, scriptable).call(context, scriptable, ScriptRuntime.lastStoredScriptable(context), new Object[]{lambdaFunction, lambdaFunction2});
                } catch (Throwable th) {
                    this.iterator.setDone(true);
                    throw th;
                }
            }
            throw ScriptRuntime.rangeErrorById("msg.promise.all.toobig", new Object[0]);
        }

        /* JADX INFO: renamed from: lambda$resolve$0$org-mozilla-javascript-NativePromise$PromiseAllResolver, reason: not valid java name */
        /* synthetic */ Object m10914x5502305d(PromiseElementResolver promiseElementResolver, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
            Object obj = objArr.length > 0 ? objArr[0] : Undefined.instance;
            if (!this.failFast) {
                Scriptable scriptableNewObject = context.newObject(scriptable);
                scriptableNewObject.put(NotificationCompat.CATEGORY_STATUS, scriptableNewObject, "fulfilled");
                scriptableNewObject.put(ES6Iterator.VALUE_PROPERTY, scriptableNewObject, obj);
                obj = scriptableNewObject;
            }
            return promiseElementResolver.resolve(context, scriptable, obj, this);
        }

        /* JADX INFO: renamed from: lambda$resolve$1$org-mozilla-javascript-NativePromise$PromiseAllResolver, reason: not valid java name */
        /* synthetic */ Object m10915x7a96395e(PromiseElementResolver promiseElementResolver, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
            Scriptable scriptableNewObject = context.newObject(scriptable);
            scriptableNewObject.put(NotificationCompat.CATEGORY_STATUS, scriptableNewObject, " rejected");
            scriptableNewObject.put("reason", scriptableNewObject, objArr.length > 0 ? objArr[0] : Undefined.instance);
            return promiseElementResolver.resolve(context, scriptable, scriptableNewObject, this);
        }

        void finalResolution(Context context, Scriptable scriptable) {
            this.capability.resolve.call(context, scriptable, Undefined.SCRIPTABLE_UNDEFINED, new Object[]{context.newArray(scriptable, this.values.toArray())});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class PromiseAnyRejector {
        private static final int MAX_PROMISES = 2097152;
        Capability capability;
        IteratorLikeIterable.Itr iterator;
        Scriptable thisObj;
        final ArrayList<Object> errors = new ArrayList<>();
        int remainingElements = 1;

        PromiseAnyRejector(IteratorLikeIterable.Itr itr, Scriptable scriptable, Capability capability) {
            this.iterator = itr;
            this.thisObj = scriptable;
            this.capability = capability;
        }

        Object reject(Context context, Scriptable scriptable) {
            Callable propFunctionAndThis = ScriptRuntime.getPropFunctionAndThis(this.thisObj, "resolve", context, scriptable);
            Scriptable scriptableLastStoredScriptable = ScriptRuntime.lastStoredScriptable(context);
            for (int i = 0; i != 2097152; i++) {
                Object next = Undefined.instance;
                try {
                    boolean zHasNext = this.iterator.hasNext();
                    if (zHasNext) {
                        next = this.iterator.next();
                    }
                    if (!zHasNext) {
                        int i2 = this.remainingElements - 1;
                        this.remainingElements = i2;
                        if (i2 == 0) {
                            throw new JavaScriptException((NativeError) context.newObject(scriptable, "AggregateError", new Object[]{context.newArray(scriptable, this.errors.toArray())}), null, 0);
                        }
                        return this.capability.promise;
                    }
                    this.errors.add(Undefined.instance);
                    Object objCall = propFunctionAndThis.call(context, scriptable, scriptableLastStoredScriptable, new Object[]{next});
                    final PromiseElementResolver promiseElementResolver = new PromiseElementResolver(i);
                    LambdaFunction lambdaFunction = new LambdaFunction(scriptable, 1, new Callable() { // from class: org.mozilla.javascript.NativePromise$PromiseAnyRejector$$ExternalSyntheticLambda0
                        @Override // org.mozilla.javascript.Callable
                        public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                            return this.f$0.m10916xcee3be31(promiseElementResolver, context2, scriptable2, scriptable3, objArr);
                        }
                    });
                    this.remainingElements++;
                    ScriptRuntime.getPropFunctionAndThis(objCall, "then", context, scriptable).call(context, scriptable, ScriptRuntime.lastStoredScriptable(context), new Object[]{this.capability.resolve, lambdaFunction});
                } catch (Throwable th) {
                    this.iterator.setDone(true);
                    throw th;
                }
            }
            throw ScriptRuntime.rangeErrorById("msg.promise.any.toobig", new Object[0]);
        }

        /* JADX INFO: renamed from: lambda$reject$0$org-mozilla-javascript-NativePromise$PromiseAnyRejector, reason: not valid java name */
        /* synthetic */ Object m10916xcee3be31(PromiseElementResolver promiseElementResolver, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
            return promiseElementResolver.reject(context, scriptable, objArr.length > 0 ? objArr[0] : Undefined.instance, this);
        }

        void finalRejection(Context context, Scriptable scriptable) {
            this.capability.reject.call(context, scriptable, Undefined.SCRIPTABLE_UNDEFINED, new Object[]{(NativeError) context.newObject(scriptable, "AggregateError", new Object[]{context.newArray(scriptable, this.errors.toArray())})});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class PromiseElementResolver {
        private boolean alreadyCalled = false;
        private final int index;

        PromiseElementResolver(int i) {
            this.index = i;
        }

        Object resolve(Context context, Scriptable scriptable, Object obj, PromiseAllResolver promiseAllResolver) {
            if (this.alreadyCalled) {
                return Undefined.instance;
            }
            this.alreadyCalled = true;
            promiseAllResolver.values.set(this.index, obj);
            int i = promiseAllResolver.remainingElements - 1;
            promiseAllResolver.remainingElements = i;
            if (i == 0) {
                promiseAllResolver.finalResolution(context, scriptable);
            }
            return Undefined.instance;
        }

        Object reject(Context context, Scriptable scriptable, Object obj, PromiseAnyRejector promiseAnyRejector) {
            if (this.alreadyCalled) {
                return Undefined.instance;
            }
            this.alreadyCalled = true;
            promiseAnyRejector.errors.set(this.index, obj);
            int i = promiseAnyRejector.remainingElements - 1;
            promiseAnyRejector.remainingElements = i;
            if (i == 0) {
                promiseAnyRejector.finalRejection(context, scriptable);
            }
            return Undefined.instance;
        }
    }
}
