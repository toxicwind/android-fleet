package org.mozilla.javascript.optimizer;

import j$.util.Objects;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import jdk.dynalink.StandardNamespace;
import jdk.dynalink.StandardOperation;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.LinkRequest;
import jdk.dynalink.linker.LinkerServices;
import jdk.dynalink.linker.TypeBasedGuardingDynamicLinker;
import jdk.dynalink.linker.support.Guards;
import org.mozilla.javascript.ConsString;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

/* JADX INFO: loaded from: classes8.dex */
class StringLinker implements TypeBasedGuardingDynamicLinker {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    StringLinker() {
    }

    public boolean canLinkType(Class<?> cls) {
        return String.class.equals(cls);
    }

    /* JADX WARN: Code duplicated, block: B:28:0x00a0  */
    public GuardedInvocation getGuardedInvocation(LinkRequest linkRequest, LinkerServices linkerServices) throws Exception {
        MethodHandle methodHandleFindStatic;
        MethodHandle instanceOfGuard;
        if (linkRequest.isCallSiteUnstable()) {
            return null;
        }
        Object obj = linkRequest.getArguments().length > 1 ? linkRequest.getArguments()[1] : null;
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        ParsedOperation parsedOperation = new ParsedOperation(linkRequest.getCallSiteDescriptor().getOperation());
        MethodType methodType = linkRequest.getCallSiteDescriptor().getMethodType();
        if (parsedOperation.isNamespace(RhinoNamespace.MATH)) {
            if (parsedOperation.isOperation(RhinoOperation.ADD)) {
                MethodType methodTypeChangeReturnType = methodType.changeReturnType((Class<?>) Boolean.TYPE);
                if (obj instanceof CharSequence) {
                    methodHandleFindStatic = lookup.findStatic(StringLinker.class, "add", methodType);
                    instanceOfGuard = lookup.findStatic(StringLinker.class, "testAdd", methodTypeChangeReturnType);
                } else {
                    methodHandleFindStatic = null;
                    instanceOfGuard = null;
                }
            } else if (parsedOperation.isOperation(RhinoOperation.EQ, RhinoOperation.SHALLOWEQ) && (obj instanceof String)) {
                methodHandleFindStatic = lookup.findStatic(StringLinker.class, "eq", methodType);
                instanceOfGuard = lookup.findStatic(StringLinker.class, "testEq", methodType);
            } else {
                methodHandleFindStatic = null;
                instanceOfGuard = null;
            }
        } else if (parsedOperation.isNamespace(StandardNamespace.PROPERTY) && parsedOperation.isOperation(StandardOperation.GET, RhinoOperation.GETNOWARN) && "length".equals(parsedOperation.getName())) {
            methodHandleFindStatic = lookup.findStatic(StringLinker.class, "getLength", methodType);
            instanceOfGuard = Guards.getInstanceOfGuard(String.class);
        } else {
            methodHandleFindStatic = null;
            instanceOfGuard = null;
        }
        if (methodHandleFindStatic == null) {
            return null;
        }
        if (DefaultLinker.DEBUG) {
            System.out.println(String.valueOf(parsedOperation) + " string operation");
        }
        return new GuardedInvocation(methodHandleFindStatic, instanceOfGuard);
    }

    private static boolean testAdd(Object obj, Object obj2, Context context) {
        return (obj instanceof String) && (obj2 instanceof CharSequence);
    }

    private static Object add(Object obj, Object obj2, Context context) {
        return new ConsString((String) obj, ((CharSequence) obj2).toString());
    }

    private static boolean testEq(Object obj, Object obj2) {
        return (obj instanceof String) && (obj2 instanceof String);
    }

    private static boolean eq(Object obj, Object obj2) {
        return Objects.equals(obj, obj2);
    }

    private static Object getLength(Object obj, Context context, Scriptable scriptable) {
        return Integer.valueOf(((String) obj).length());
    }
}
