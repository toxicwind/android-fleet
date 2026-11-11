package org.mozilla.javascript.optimizer;

import androidx.collection.SieveCacheKt;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import jdk.dynalink.StandardNamespace;
import jdk.dynalink.StandardOperation;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.LinkRequest;
import jdk.dynalink.linker.LinkerServices;
import jdk.dynalink.linker.TypeBasedGuardingDynamicLinker;
import jdk.dynalink.linker.support.Guards;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.Scriptable;

/* JADX INFO: loaded from: classes8.dex */
class NativeArrayLinker implements TypeBasedGuardingDynamicLinker {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    NativeArrayLinker() {
    }

    public boolean canLinkType(Class<?> cls) {
        return NativeArray.class.isAssignableFrom(cls);
    }

    public GuardedInvocation getGuardedInvocation(LinkRequest linkRequest, LinkerServices linkerServices) throws Exception {
        MethodHandle methodHandleFindStatic;
        MethodHandle instanceOfGuard;
        if (linkRequest.isCallSiteUnstable()) {
            return null;
        }
        ParsedOperation parsedOperation = new ParsedOperation(linkRequest.getCallSiteDescriptor().getOperation());
        if (parsedOperation.isNamespace(StandardNamespace.PROPERTY) && parsedOperation.isOperation(StandardOperation.GET, RhinoOperation.GETNOWARN) && "length".equals(parsedOperation.getName())) {
            methodHandleFindStatic = MethodHandles.lookup().findStatic(NativeArrayLinker.class, "getLength", linkRequest.getCallSiteDescriptor().getMethodType());
            instanceOfGuard = Guards.getInstanceOfGuard(NativeArray.class);
        } else {
            methodHandleFindStatic = null;
            instanceOfGuard = null;
        }
        if (methodHandleFindStatic == null) {
            return null;
        }
        if (DefaultLinker.DEBUG) {
            System.out.println(String.valueOf(parsedOperation) + " native array operation");
        }
        return new GuardedInvocation(methodHandleFindStatic, instanceOfGuard);
    }

    private static Object getLength(Object obj, Context context, Scriptable scriptable) {
        long length = ((NativeArray) obj).getLength();
        if (length < SieveCacheKt.NodeLinkMask) {
            return Integer.valueOf((int) length);
        }
        return Double.valueOf(length);
    }
}
