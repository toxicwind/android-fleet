package org.mozilla.javascript.optimizer;

import coil3.network.internal.UtilsKt;
import com.google.common.base.Ascii;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Arrays;
import java.util.regex.Pattern;
import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.DynamicLinker;
import jdk.dynalink.DynamicLinkerFactory;
import jdk.dynalink.Operation;
import jdk.dynalink.StandardNamespace;
import jdk.dynalink.StandardOperation;
import jdk.dynalink.linker.GuardingDynamicLinker;
import jdk.dynalink.linker.support.CompositeTypeBasedGuardingDynamicLinker;
import jdk.dynalink.support.ChainedCallSite;
import org.mozilla.classfile.ClassFileWriter;
import retrofit2.Reflection$Java8$$ExternalSyntheticApiModelOutline0;

/* JADX INFO: loaded from: classes8.dex */
public class Bootstrapper {
    private static final DynamicLinker linker;
    private static final Pattern SEPARATOR = Pattern.compile(":");
    public static final ClassFileWriter.MHandle BOOTSTRAP_HANDLE = new ClassFileWriter.MHandle((byte) 6, "org.mozilla.javascript.optimizer.Bootstrapper", "bootstrap", "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;");

    static {
        DynamicLinkerFactory dynamicLinkerFactory = new DynamicLinkerFactory();
        dynamicLinkerFactory.setPrioritizedLinkers(new GuardingDynamicLinker[]{new CompositeTypeBasedGuardingDynamicLinker(Arrays.asList(new ConstAwareLinker(), new BooleanLinker(), new IntegerLinker(), new DoubleLinker(), new StringLinker(), new ConsStringLinker(), new NativeArrayLinker(), new BaseFunctionLinker())), new DefaultLinker()});
        linker = dynamicLinkerFactory.createLinker();
    }

    public static CallSite bootstrap(MethodHandles.Lookup lookup, String str, MethodType methodType) throws NoSuchMethodException {
        return Reflection$Java8$$ExternalSyntheticApiModelOutline0.m((Object) linker.link(new ChainedCallSite(new CallSiteDescriptor(lookup, parseOperation(str), methodType))));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:140:0x0270  */
    /* JADX WARN: Code duplicated, block: B:6:0x003f  */
    /* JADX WARN: Code duplicated, block: B:91:0x0198  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private static Operation parseOperation(String str) throws NoSuchMethodException {
        byte b;
        String[] strArrSplit = SEPARATOR.split(str, -1);
        String nameSegment = getNameSegment(strArrSplit, str, 0);
        String nameSegment2 = getNameSegment(strArrSplit, str, 1);
        if ("PROP".equals(nameSegment)) {
            nameSegment2.hashCode();
            switch (nameSegment2.hashCode()) {
                case -2087728720:
                    if (!nameSegment2.equals("SETINDEX")) {
                        b = -1;
                    } else {
                        b = 0;
                    }
                    break;
                case -2078273447:
                    if (!nameSegment2.equals("SETSUPER")) {
                        b = -1;
                    } else {
                        b = 1;
                    }
                    break;
                case -1805537562:
                    if (!nameSegment2.equals("GETELEMENT")) {
                        b = -1;
                    } else {
                        b = 2;
                    }
                    break;
                case -1526616260:
                    if (!nameSegment2.equals("GETINDEX")) {
                        b = -1;
                    } else {
                        b = 3;
                    }
                    break;
                case -1517160987:
                    if (!nameSegment2.equals("GETSUPER")) {
                        b = -1;
                    } else {
                        b = 4;
                    }
                    break;
                case -1359469759:
                    if (!nameSegment2.equals("SETELEMENTSUPER")) {
                        b = -1;
                    } else {
                        b = 5;
                    }
                    break;
                case -1068525158:
                    if (!nameSegment2.equals("GETWITHTHIS")) {
                        b = -1;
                    } else {
                        b = 6;
                    }
                    break;
                case 70454:
                    if (!nameSegment2.equals(UtilsKt.HTTP_METHOD_GET)) {
                        b = -1;
                    } else {
                        b = 7;
                    }
                    break;
                case 81986:
                    if (!nameSegment2.equals("SET")) {
                        b = -1;
                    } else {
                        b = 8;
                    }
                    break;
                case 64167549:
                    if (!nameSegment2.equals("GETNOWARN")) {
                        b = -1;
                    } else {
                        b = 9;
                    }
                    break;
                case 131267674:
                    if (!nameSegment2.equals("SETELEMENT")) {
                        b = -1;
                    } else {
                        b = 10;
                    }
                    break;
                case 1119984378:
                    if (!nameSegment2.equals("GETWITHTHISOPTIONAL")) {
                        b = -1;
                    } else {
                        b = 11;
                    }
                    break;
                case 2024745013:
                    if (!nameSegment2.equals("GETELEMENTSUPER")) {
                        b = -1;
                    } else {
                        b = Ascii.FF;
                    }
                    break;
                default:
                    b = -1;
                    break;
            }
            switch (b) {
                case 0:
                    return RhinoOperation.SETINDEX.withNamespace(StandardNamespace.PROPERTY);
                case 1:
                    return RhinoOperation.SETSUPER.withNamespace(StandardNamespace.PROPERTY).named(getNameSegment(strArrSplit, str, 2));
                case 2:
                    return RhinoOperation.GETELEMENT.withNamespace(StandardNamespace.PROPERTY);
                case 3:
                    return RhinoOperation.GETINDEX.withNamespace(StandardNamespace.PROPERTY);
                case 4:
                    return RhinoOperation.GETSUPER.withNamespace(StandardNamespace.PROPERTY).named(getNameSegment(strArrSplit, str, 2));
                case 5:
                    return RhinoOperation.SETELEMENTSUPER.withNamespace(StandardNamespace.PROPERTY);
                case 6:
                    return RhinoOperation.GETWITHTHIS.withNamespace(StandardNamespace.PROPERTY).named(getNameSegment(strArrSplit, str, 2));
                case 7:
                    return StandardOperation.GET.withNamespace(StandardNamespace.PROPERTY).named(getNameSegment(strArrSplit, str, 2));
                case 8:
                    return StandardOperation.SET.withNamespace(StandardNamespace.PROPERTY).named(getNameSegment(strArrSplit, str, 2));
                case 9:
                    return RhinoOperation.GETNOWARN.withNamespace(StandardNamespace.PROPERTY).named(getNameSegment(strArrSplit, str, 2));
                case 10:
                    return RhinoOperation.SETELEMENT.withNamespace(StandardNamespace.PROPERTY);
                case 11:
                    return RhinoOperation.GETWITHTHISOPTIONAL.withNamespace(StandardNamespace.PROPERTY).named(getNameSegment(strArrSplit, str, 2));
                case 12:
                    return RhinoOperation.GETELEMENTSUPER.withNamespace(StandardNamespace.PROPERTY);
            }
        }
        if ("NAME".equals(nameSegment)) {
            nameSegment2.hashCode();
            switch (nameSegment2) {
                case "SETCONST":
                    return RhinoOperation.SETCONST.withNamespace(RhinoNamespace.NAME).named(getNameSegment(strArrSplit, str, 2));
                case "GETWITHTHIS":
                    return RhinoOperation.GETWITHTHIS.withNamespace(RhinoNamespace.NAME).named(getNameSegment(strArrSplit, str, 2));
                case "SETSTRICT":
                    return RhinoOperation.SETSTRICT.withNamespace(RhinoNamespace.NAME).named(getNameSegment(strArrSplit, str, 2));
                case "GET":
                    return StandardOperation.GET.withNamespace(RhinoNamespace.NAME).named(getNameSegment(strArrSplit, str, 2));
                case "SET":
                    return StandardOperation.SET.withNamespace(RhinoNamespace.NAME).named(getNameSegment(strArrSplit, str, 2));
                case "BIND":
                    return RhinoOperation.BIND.withNamespace(RhinoNamespace.NAME).named(getNameSegment(strArrSplit, str, 2));
                case "GETWITHTHISOPTIONAL":
                    return RhinoOperation.GETWITHTHISOPTIONAL.withNamespace(RhinoNamespace.NAME).named(getNameSegment(strArrSplit, str, 2));
            }
        }
        if ("MATH".equals(nameSegment)) {
            nameSegment2.hashCode();
            switch (nameSegment2) {
                case "TOBOOLEAN":
                    return RhinoOperation.TOBOOLEAN.withNamespace(RhinoNamespace.MATH);
                case "TOINT32":
                    return RhinoOperation.TOINT32.withNamespace(RhinoNamespace.MATH);
                case "TONUMBER":
                    return RhinoOperation.TONUMBER.withNamespace(RhinoNamespace.MATH);
                case "TOUINT32":
                    return RhinoOperation.TOUINT32.withNamespace(RhinoNamespace.MATH);
                case "EQ":
                    return RhinoOperation.EQ.withNamespace(RhinoNamespace.MATH);
                case "ADD":
                    return RhinoOperation.ADD.withNamespace(RhinoNamespace.MATH);
                case "SHALLOWEQ":
                    return RhinoOperation.SHALLOWEQ.withNamespace(RhinoNamespace.MATH);
                case "COMPAREGE":
                    return RhinoOperation.COMPARE_GE.withNamespace(RhinoNamespace.MATH);
                case "COMPAREGT":
                    return RhinoOperation.COMPARE_GT.withNamespace(RhinoNamespace.MATH);
                case "COMPARELE":
                    return RhinoOperation.COMPARE_LE.withNamespace(RhinoNamespace.MATH);
                case "COMPARELT":
                    return RhinoOperation.COMPARE_LT.withNamespace(RhinoNamespace.MATH);
                case "TONUMERIC":
                    return RhinoOperation.TONUMERIC.withNamespace(RhinoNamespace.MATH);
            }
        }
        throw new NoSuchMethodException(str);
    }

    private static String getNameSegment(String[] strArr, String str, int i) {
        if (i >= strArr.length) {
            return "";
        }
        return strArr[i].intern();
    }
}
