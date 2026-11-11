package org.mozilla.javascript.optimizer;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import jdk.dynalink.StandardNamespace;
import jdk.dynalink.StandardOperation;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.GuardingDynamicLinker;
import jdk.dynalink.linker.LinkRequest;
import jdk.dynalink.linker.LinkerServices;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.mozilla.javascript.Callable;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;

/* JADX INFO: loaded from: classes8.dex */
class DefaultLinker implements GuardingDynamicLinker {
    static final boolean DEBUG;

    DefaultLinker() {
    }

    static {
        String property = System.getProperty("RHINO_DEBUG_LINKER");
        if (property == null) {
            property = System.getenv("RHINO_DEBUG_LINKER");
        }
        DEBUG = Boolean.parseBoolean(property);
    }

    public GuardedInvocation getGuardedInvocation(LinkRequest linkRequest, LinkerServices linkerServices) throws IllegalAccessException, NoSuchMethodException {
        String simpleName;
        String simpleName2;
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType methodType = linkRequest.getCallSiteDescriptor().getMethodType();
        ParsedOperation parsedOperation = new ParsedOperation(linkRequest.getCallSiteDescriptor().getOperation());
        GuardedInvocation invocation = getInvocation(lookup, methodType, parsedOperation);
        if (DEBUG) {
            if (linkRequest.getReceiver() == null) {
                simpleName = AbstractJsonLexerKt.NULL;
            } else {
                simpleName = linkRequest.getReceiver().getClass().getSimpleName();
            }
            if (linkRequest.getArguments().length > 1 && linkRequest.getArguments()[1] != null) {
                simpleName2 = linkRequest.getArguments()[1].getClass().getSimpleName();
            } else {
                simpleName2 = "";
            }
            System.out.println(String.valueOf(parsedOperation) + "(" + simpleName + ", " + simpleName2 + "): default link");
        }
        return invocation;
    }

    private GuardedInvocation getInvocation(MethodHandles.Lookup lookup, MethodType methodType, ParsedOperation parsedOperation) throws IllegalAccessException, NoSuchMethodException {
        if (parsedOperation.isNamespace(StandardNamespace.PROPERTY)) {
            return getPropertyInvocation(lookup, methodType, parsedOperation);
        }
        if (parsedOperation.isNamespace(RhinoNamespace.NAME)) {
            return getNameInvocation(lookup, methodType, parsedOperation);
        }
        if (parsedOperation.isNamespace(RhinoNamespace.MATH)) {
            return getMathInvocation(lookup, methodType, parsedOperation);
        }
        throw new UnsupportedOperationException(parsedOperation.toString());
    }

    private GuardedInvocation getPropertyInvocation(MethodHandles.Lookup lookup, MethodType methodType, ParsedOperation parsedOperation) throws IllegalAccessException, NoSuchMethodException {
        MethodHandle methodHandleFindStatic;
        if (parsedOperation.isOperation(StandardOperation.GET)) {
            methodHandleFindStatic = bindStringParameter(lookup, methodType, ScriptRuntime.class, "getObjectProp", 1, parsedOperation.getName());
        } else if (parsedOperation.isOperation(RhinoOperation.GETNOWARN)) {
            methodHandleFindStatic = bindStringParameter(lookup, methodType, ScriptRuntime.class, "getObjectPropNoWarn", 1, parsedOperation.getName());
        } else if (parsedOperation.isOperation(RhinoOperation.GETSUPER)) {
            methodHandleFindStatic = bindStringParameter(lookup, methodType, ScriptRuntime.class, "getSuperProp", 1, parsedOperation.getName());
        } else if (parsedOperation.isOperation(RhinoOperation.GETWITHTHIS)) {
            methodHandleFindStatic = bindStringParameter(lookup, methodType, ScriptRuntime.class, "getPropFunctionAndThis", 1, parsedOperation.getName());
        } else if (parsedOperation.isOperation(RhinoOperation.GETWITHTHISOPTIONAL)) {
            methodHandleFindStatic = bindStringParameter(lookup, methodType, ScriptRuntime.class, "getPropFunctionAndThisOptional", 1, parsedOperation.getName());
        } else if (parsedOperation.isOperation(StandardOperation.SET)) {
            methodHandleFindStatic = bindStringParameter(lookup, methodType, ScriptRuntime.class, "setObjectProp", 1, parsedOperation.getName());
        } else if (parsedOperation.isOperation(RhinoOperation.SETSUPER)) {
            methodHandleFindStatic = bindStringParameter(lookup, methodType, ScriptRuntime.class, "setSuperProp", 1, parsedOperation.getName());
        } else if (parsedOperation.isOperation(RhinoOperation.GETELEMENT)) {
            methodHandleFindStatic = lookup.findStatic(ScriptRuntime.class, "getObjectElem", methodType);
        } else if (parsedOperation.isOperation(RhinoOperation.GETELEMENTSUPER)) {
            methodHandleFindStatic = lookup.findStatic(ScriptRuntime.class, "getSuperElem", methodType);
        } else if (parsedOperation.isOperation(RhinoOperation.GETINDEX)) {
            methodHandleFindStatic = lookup.findStatic(ScriptRuntime.class, "getObjectIndex", methodType);
        } else if (parsedOperation.isOperation(RhinoOperation.SETELEMENT)) {
            methodHandleFindStatic = lookup.findStatic(ScriptRuntime.class, "setObjectElem", methodType);
        } else if (parsedOperation.isOperation(RhinoOperation.SETELEMENTSUPER)) {
            methodHandleFindStatic = lookup.findStatic(ScriptRuntime.class, "setSuperElem", methodType);
        } else {
            methodHandleFindStatic = parsedOperation.isOperation(RhinoOperation.SETINDEX) ? lookup.findStatic(ScriptRuntime.class, "setObjectIndex", methodType) : null;
        }
        if (methodHandleFindStatic != null) {
            return new GuardedInvocation(methodHandleFindStatic);
        }
        throw new UnsupportedOperationException(parsedOperation.toString());
    }

    private GuardedInvocation getNameInvocation(MethodHandles.Lookup lookup, MethodType methodType, ParsedOperation parsedOperation) throws IllegalAccessException, NoSuchMethodException {
        MethodHandle methodHandleBindStringParameter;
        String name = parsedOperation.getName();
        if (parsedOperation.isOperation(RhinoOperation.BIND)) {
            methodHandleBindStringParameter = MethodHandles.permuteArguments(MethodHandles.insertArguments(lookup.findStatic(ScriptRuntime.class, "bind", MethodType.methodType(Scriptable.class, Context.class, Scriptable.class, String.class)), 2, name), methodType, 1, 0);
        } else if (parsedOperation.isOperation(StandardOperation.GET)) {
            methodHandleBindStringParameter = MethodHandles.permuteArguments(MethodHandles.insertArguments(lookup.findStatic(ScriptRuntime.class, "name", MethodType.methodType(Object.class, Context.class, Scriptable.class, String.class)), 2, name), methodType, 1, 0);
        } else if (parsedOperation.isOperation(RhinoOperation.GETWITHTHIS)) {
            methodHandleBindStringParameter = MethodHandles.permuteArguments(MethodHandles.insertArguments(lookup.findStatic(ScriptRuntime.class, "getNameFunctionAndThis", MethodType.methodType(Callable.class, String.class, Context.class, Scriptable.class)), 0, name), methodType, 1, 0);
        } else if (parsedOperation.isOperation(RhinoOperation.GETWITHTHISOPTIONAL)) {
            methodHandleBindStringParameter = MethodHandles.permuteArguments(MethodHandles.insertArguments(lookup.findStatic(ScriptRuntime.class, "getNameFunctionAndThisOptional", MethodType.methodType(Callable.class, String.class, Context.class, Scriptable.class)), 0, name), methodType, 1, 0);
        } else if (parsedOperation.isOperation(StandardOperation.SET)) {
            methodHandleBindStringParameter = bindStringParameter(lookup, methodType, ScriptRuntime.class, "setName", 4, name);
        } else if (parsedOperation.isOperation(RhinoOperation.SETSTRICT)) {
            methodHandleBindStringParameter = bindStringParameter(lookup, methodType, ScriptRuntime.class, "strictSetName", 4, name);
        } else {
            methodHandleBindStringParameter = parsedOperation.isOperation(RhinoOperation.SETCONST) ? bindStringParameter(lookup, methodType, ScriptRuntime.class, "setConst", 3, name) : null;
        }
        if (methodHandleBindStringParameter != null) {
            return new GuardedInvocation(methodHandleBindStringParameter);
        }
        throw new UnsupportedOperationException(parsedOperation.toString());
    }

    private GuardedInvocation getMathInvocation(MethodHandles.Lookup lookup, MethodType methodType, ParsedOperation parsedOperation) throws IllegalAccessException, NoSuchMethodException {
        MethodHandle methodHandleMakeCompare;
        if (parsedOperation.isOperation(RhinoOperation.ADD)) {
            methodHandleMakeCompare = lookup.findStatic(ScriptRuntime.class, "add", methodType);
        } else if (parsedOperation.isOperation(RhinoOperation.TONUMBER)) {
            methodHandleMakeCompare = lookup.findStatic(ScriptRuntime.class, "toNumber", methodType);
        } else if (parsedOperation.isOperation(RhinoOperation.TONUMERIC)) {
            methodHandleMakeCompare = lookup.findStatic(ScriptRuntime.class, "toNumeric", methodType);
        } else if (parsedOperation.isOperation(RhinoOperation.TOBOOLEAN)) {
            methodHandleMakeCompare = lookup.findStatic(ScriptRuntime.class, "toBoolean", methodType);
        } else if (parsedOperation.isOperation(RhinoOperation.TOINT32)) {
            methodHandleMakeCompare = lookup.findStatic(ScriptRuntime.class, "toInt32", methodType);
        } else if (parsedOperation.isOperation(RhinoOperation.TOUINT32)) {
            methodHandleMakeCompare = lookup.findStatic(ScriptRuntime.class, "toUint32", methodType);
        } else if (parsedOperation.isOperation(RhinoOperation.EQ)) {
            methodHandleMakeCompare = lookup.findStatic(ScriptRuntime.class, "eq", methodType);
        } else if (parsedOperation.isOperation(RhinoOperation.SHALLOWEQ)) {
            methodHandleMakeCompare = lookup.findStatic(ScriptRuntime.class, "shallowEq", methodType);
        } else if (parsedOperation.isOperation(RhinoOperation.COMPARE_GT)) {
            methodHandleMakeCompare = makeCompare(lookup, 16);
        } else if (parsedOperation.isOperation(RhinoOperation.COMPARE_LT)) {
            methodHandleMakeCompare = makeCompare(lookup, 14);
        } else if (parsedOperation.isOperation(RhinoOperation.COMPARE_GE)) {
            methodHandleMakeCompare = makeCompare(lookup, 17);
        } else {
            methodHandleMakeCompare = parsedOperation.isOperation(RhinoOperation.COMPARE_LE) ? makeCompare(lookup, 15) : null;
        }
        if (methodHandleMakeCompare != null) {
            return new GuardedInvocation(methodHandleMakeCompare);
        }
        throw new UnsupportedOperationException(parsedOperation.toString());
    }

    private MethodHandle makeCompare(MethodHandles.Lookup lookup, int i) throws IllegalAccessException, NoSuchMethodException {
        return MethodHandles.insertArguments(lookup.findStatic(ScriptRuntime.class, "compare", MethodType.methodType(Boolean.TYPE, Object.class, Object.class, Integer.TYPE)), 2, Integer.valueOf(i));
    }

    private static MethodHandle bindStringParameter(MethodHandles.Lookup lookup, MethodType methodType, Class<?> cls, String str, int i, String str2) throws IllegalAccessException, NoSuchMethodException {
        return MethodHandles.insertArguments(lookup.findStatic(cls, str, methodType.insertParameterTypes(i, (Class<?>[]) new Class[]{String.class})), i, str2);
    }
}
