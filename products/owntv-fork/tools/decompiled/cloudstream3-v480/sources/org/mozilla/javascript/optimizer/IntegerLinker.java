package org.mozilla.javascript.optimizer;

import j$.util.Objects;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.LinkRequest;
import jdk.dynalink.linker.LinkerServices;
import jdk.dynalink.linker.TypeBasedGuardingDynamicLinker;
import jdk.dynalink.linker.support.Guards;
import kotlin.UByte$$ExternalSyntheticBackport0;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptRuntime;

/* JADX INFO: loaded from: classes8.dex */
class IntegerLinker implements TypeBasedGuardingDynamicLinker {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    IntegerLinker() {
    }

    public boolean canLinkType(Class<?> cls) {
        return Integer.class.equals(cls);
    }

    /* JADX WARN: Code duplicated, block: B:86:0x01dc  */
    public GuardedInvocation getGuardedInvocation(LinkRequest linkRequest, LinkerServices linkerServices) throws Exception {
        MethodHandle methodHandleFindStatic;
        MethodHandle instanceOfGuard;
        if (linkRequest.isCallSiteUnstable()) {
            return null;
        }
        ParsedOperation parsedOperation = new ParsedOperation(linkRequest.getCallSiteDescriptor().getOperation());
        if (parsedOperation.isNamespace(RhinoNamespace.MATH)) {
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            MethodType methodType = linkRequest.getCallSiteDescriptor().getMethodType();
            Object obj = linkRequest.getArguments().length > 1 ? linkRequest.getArguments()[1] : null;
            if (parsedOperation.isOperation(RhinoOperation.ADD) && (obj instanceof Integer)) {
                methodHandleFindStatic = lookup.findStatic(IntegerLinker.class, "add", methodType);
                instanceOfGuard = lookup.findStatic(IntegerLinker.class, "testAdd", methodType.changeReturnType((Class<?>) Boolean.TYPE));
            } else if (parsedOperation.isOperation(RhinoOperation.ADD) && (obj instanceof Double)) {
                methodHandleFindStatic = lookup.findStatic(IntegerLinker.class, "addDouble", methodType);
                instanceOfGuard = lookup.findStatic(IntegerLinker.class, "testAddDouble", methodType.changeReturnType((Class<?>) Boolean.TYPE));
            } else if (parsedOperation.isOperation(RhinoOperation.EQ, RhinoOperation.SHALLOWEQ) && (obj instanceof Integer)) {
                methodHandleFindStatic = lookup.findStatic(IntegerLinker.class, "eq", methodType);
                instanceOfGuard = lookup.findStatic(IntegerLinker.class, "testTwo", methodType);
            } else if (parsedOperation.isOperation(RhinoOperation.EQ, RhinoOperation.SHALLOWEQ) && (obj instanceof Double)) {
                methodHandleFindStatic = lookup.findStatic(IntegerLinker.class, "eqDouble", methodType);
                instanceOfGuard = lookup.findStatic(IntegerLinker.class, "testTwoDouble", methodType);
            } else if (parsedOperation.isOperation(RhinoOperation.COMPARE_LT) && (obj instanceof Integer)) {
                methodHandleFindStatic = lookup.findStatic(IntegerLinker.class, "compareLT", methodType);
                instanceOfGuard = lookup.findStatic(IntegerLinker.class, "testTwo", methodType);
            } else if (parsedOperation.isOperation(RhinoOperation.COMPARE_GT) && (obj instanceof Integer)) {
                methodHandleFindStatic = lookup.findStatic(IntegerLinker.class, "compareGT", methodType);
                instanceOfGuard = lookup.findStatic(IntegerLinker.class, "testTwo", methodType);
            } else if (parsedOperation.isOperation(RhinoOperation.COMPARE_LE) && (obj instanceof Integer)) {
                methodHandleFindStatic = lookup.findStatic(IntegerLinker.class, "compareLE", methodType);
                instanceOfGuard = lookup.findStatic(IntegerLinker.class, "testTwo", methodType);
            } else if (parsedOperation.isOperation(RhinoOperation.COMPARE_GE) && (obj instanceof Integer)) {
                methodHandleFindStatic = lookup.findStatic(IntegerLinker.class, "compareGE", methodType);
                instanceOfGuard = lookup.findStatic(IntegerLinker.class, "testTwo", methodType);
            } else if (parsedOperation.isOperation(RhinoOperation.COMPARE_LT) && (obj instanceof Double)) {
                methodHandleFindStatic = lookup.findStatic(IntegerLinker.class, "compareLTDouble", methodType);
                instanceOfGuard = lookup.findStatic(IntegerLinker.class, "testTwoDouble", methodType);
            } else if (parsedOperation.isOperation(RhinoOperation.COMPARE_GT) && (obj instanceof Double)) {
                methodHandleFindStatic = lookup.findStatic(IntegerLinker.class, "compareGTDouble", methodType);
                instanceOfGuard = lookup.findStatic(IntegerLinker.class, "testTwoDouble", methodType);
            } else if (parsedOperation.isOperation(RhinoOperation.COMPARE_LE) && (obj instanceof Double)) {
                methodHandleFindStatic = lookup.findStatic(IntegerLinker.class, "compareLEDouble", methodType);
                instanceOfGuard = lookup.findStatic(IntegerLinker.class, "testTwoDouble", methodType);
            } else if (parsedOperation.isOperation(RhinoOperation.COMPARE_GE) && (obj instanceof Double)) {
                methodHandleFindStatic = lookup.findStatic(IntegerLinker.class, "compareGEDouble", methodType);
                instanceOfGuard = lookup.findStatic(IntegerLinker.class, "testTwoDouble", methodType);
            } else if (parsedOperation.isOperation(RhinoOperation.TOBOOLEAN)) {
                methodHandleFindStatic = lookup.findStatic(IntegerLinker.class, "toBoolean", methodType);
                instanceOfGuard = Guards.getInstanceOfGuard(Integer.class);
            } else if (parsedOperation.isOperation(RhinoOperation.TONUMBER)) {
                methodHandleFindStatic = lookup.findStatic(IntegerLinker.class, "toNumber", methodType);
                instanceOfGuard = Guards.getInstanceOfGuard(Integer.class);
            } else if (parsedOperation.isOperation(RhinoOperation.TONUMERIC)) {
                methodHandleFindStatic = lookup.findStatic(IntegerLinker.class, "toNumeric", methodType);
                instanceOfGuard = Guards.getInstanceOfGuard(Integer.class);
            } else if (parsedOperation.isOperation(RhinoOperation.TOINT32)) {
                methodHandleFindStatic = lookup.findStatic(IntegerLinker.class, "toInt32", methodType);
                instanceOfGuard = Guards.getInstanceOfGuard(Integer.class);
            } else if (parsedOperation.isOperation(RhinoOperation.TOUINT32)) {
                methodHandleFindStatic = lookup.findStatic(IntegerLinker.class, "toUint32", methodType);
                instanceOfGuard = Guards.getInstanceOfGuard(Integer.class);
            } else {
                methodHandleFindStatic = null;
                instanceOfGuard = null;
            }
        } else {
            methodHandleFindStatic = null;
            instanceOfGuard = null;
        }
        if (methodHandleFindStatic == null) {
            return null;
        }
        if (DefaultLinker.DEBUG) {
            System.out.println(String.valueOf(parsedOperation) + " integer operation");
        }
        return new GuardedInvocation(methodHandleFindStatic, instanceOfGuard);
    }

    private static boolean testAdd(Object obj, Object obj2, Context context) {
        return (obj instanceof Integer) && (obj2 instanceof Integer);
    }

    private static Object add(Object obj, Object obj2, Context context) {
        return ScriptRuntime.add((Integer) obj, (Integer) obj2);
    }

    private static boolean testAddDouble(Object obj, Object obj2, Context context) {
        return (obj instanceof Integer) && (obj2 instanceof Double);
    }

    private static Object addDouble(Object obj, Object obj2, Context context) {
        return Double.valueOf(((Integer) obj).doubleValue() + ((Double) obj2).doubleValue());
    }

    private static boolean testTwo(Object obj, Object obj2) {
        return (obj instanceof Integer) && (obj2 instanceof Integer);
    }

    private static boolean testTwoDouble(Object obj, Object obj2) {
        return (obj instanceof Integer) && (obj2 instanceof Double);
    }

    private static boolean eq(Object obj, Object obj2) {
        return Objects.equals(obj, obj2);
    }

    private static boolean eqDouble(Object obj, Object obj2) {
        return ((Integer) obj).doubleValue() == ((Double) obj2).doubleValue();
    }

    private static boolean compareLT(Object obj, Object obj2) {
        return ((Integer) obj).intValue() < ((Integer) obj2).intValue();
    }

    private static boolean compareGT(Object obj, Object obj2) {
        return ((Integer) obj).intValue() > ((Integer) obj2).intValue();
    }

    private static boolean compareLE(Object obj, Object obj2) {
        return ((Integer) obj).intValue() <= ((Integer) obj2).intValue();
    }

    private static boolean compareGE(Object obj, Object obj2) {
        return ((Integer) obj).intValue() >= ((Integer) obj2).intValue();
    }

    private static boolean compareLTDouble(Object obj, Object obj2) {
        return ((Integer) obj).doubleValue() < ((Double) obj2).doubleValue();
    }

    private static boolean compareGTDouble(Object obj, Object obj2) {
        return ((Integer) obj).doubleValue() > ((Double) obj2).doubleValue();
    }

    private static boolean compareLEDouble(Object obj, Object obj2) {
        return ((Integer) obj).doubleValue() <= ((Double) obj2).doubleValue();
    }

    private static boolean compareGEDouble(Object obj, Object obj2) {
        return ((Integer) obj).doubleValue() >= ((Double) obj2).doubleValue();
    }

    private static double toNumber(Object obj) {
        return ((Integer) obj).doubleValue();
    }

    private static Number toNumeric(Object obj) {
        return (Number) obj;
    }

    private static boolean toBoolean(Object obj) {
        return ((Integer) obj).intValue() != 0;
    }

    private static int toInt32(Object obj) {
        return ((Integer) obj).intValue();
    }

    private static long toUint32(Object obj) {
        return UByte$$ExternalSyntheticBackport0.m(((Integer) obj).intValue());
    }
}
