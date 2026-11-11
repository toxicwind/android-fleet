package org.mozilla.javascript.optimizer;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.List;
import jdk.dynalink.StandardNamespace;
import jdk.dynalink.StandardOperation;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.LinkRequest;
import jdk.dynalink.linker.LinkerServices;
import jdk.dynalink.linker.TypeBasedGuardingDynamicLinker;
import jdk.dynalink.linker.support.Guards;
import org.mozilla.javascript.NativeWith;
import org.mozilla.javascript.RhinoException;
import org.mozilla.javascript.ScriptableObject;

/* JADX INFO: loaded from: classes8.dex */
class ConstAwareLinker implements TypeBasedGuardingDynamicLinker {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    ConstAwareLinker() {
    }

    public boolean canLinkType(Class<?> cls) {
        return ScriptableObject.class.isAssignableFrom(cls) || NativeWith.class.isAssignableFrom(cls);
    }

    public GuardedInvocation getGuardedInvocation(LinkRequest linkRequest, LinkerServices linkerServices) {
        Object constValue;
        if (linkRequest.isCallSiteUnstable()) {
            return null;
        }
        ParsedOperation parsedOperation = new ParsedOperation(linkRequest.getCallSiteDescriptor().getOperation());
        Object receiver = linkRequest.getReceiver();
        if (!((parsedOperation.isNamespace(RhinoNamespace.NAME) && parsedOperation.isOperation(StandardOperation.GET)) || (parsedOperation.isNamespace(StandardNamespace.PROPERTY) && parsedOperation.isOperation(StandardOperation.GET, RhinoOperation.GETNOWARN))) || (constValue = getConstValue(receiver, parsedOperation.getName())) == null) {
            return null;
        }
        MethodType methodType = linkRequest.getCallSiteDescriptor().getMethodType();
        MethodHandle methodHandleAsType = Guards.asType(Guards.getIdentityGuard(receiver), methodType);
        MethodHandle methodHandleDropArguments = MethodHandles.dropArguments(MethodHandles.constant(Object.class, constValue), 0, (List<Class<?>>) methodType.parameterList());
        if (DefaultLinker.DEBUG) {
            System.out.println(String.valueOf(parsedOperation) + ": constant");
        }
        return new GuardedInvocation(methodHandleDropArguments, methodHandleAsType);
    }

    private Object getConstValue(Object obj, String str) {
        if (obj instanceof NativeWith) {
            return getConstValue(((NativeWith) obj).getPrototype(), str);
        }
        try {
            ScriptableObject scriptableObject = (ScriptableObject) obj;
            if (!scriptableObject.has(str, scriptableObject)) {
                return null;
            }
            int attributes = scriptableObject.getAttributes(str);
            if ((attributes & 1) == 0 || (attributes & 4) == 0 || (attributes & 8) != 0) {
                return null;
            }
            return scriptableObject.get(str, scriptableObject);
        } catch (RhinoException unused) {
            return null;
        }
    }
}
