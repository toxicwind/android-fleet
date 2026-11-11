package kotlin.reflect.jvm.internal.impl.builtins.functions;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* JADX INFO: compiled from: FunctionTypeKind.kt */
/* JADX INFO: loaded from: classes7.dex */
public abstract class FunctionTypeKind {
    public static final Companion Companion = new Companion(null);
    private final ClassId annotationOnInvokeClassId;
    private final String classNamePrefix;
    private final boolean isInlineable;
    private final boolean isReflectType;
    private final int maxArity;
    private final FqName packageFqName;

    public FunctionTypeKind(FqName packageFqName, String classNamePrefix, boolean z, ClassId classId, boolean z2, int i) {
        Intrinsics.checkNotNullParameter(packageFqName, "packageFqName");
        Intrinsics.checkNotNullParameter(classNamePrefix, "classNamePrefix");
        this.packageFqName = packageFqName;
        this.classNamePrefix = classNamePrefix;
        this.isReflectType = z;
        this.annotationOnInvokeClassId = classId;
        this.isInlineable = z2;
        this.maxArity = i;
    }

    public /* synthetic */ FunctionTypeKind(FqName fqName, String str, boolean z, ClassId classId, boolean z2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(fqName, str, z, classId, z2, (i2 & 32) != 0 ? 254 : i);
    }

    public final FqName getPackageFqName() {
        return this.packageFqName;
    }

    public final String getClassNamePrefix() {
        return this.classNamePrefix;
    }

    public final int getMaxArity() {
        return this.maxArity;
    }

    public final Name numberedClassName(int i) {
        Name nameIdentifier = Name.identifier(this.classNamePrefix + i);
        Intrinsics.checkNotNullExpressionValue(nameIdentifier, "identifier(...)");
        return nameIdentifier;
    }

    public final ClassId numberedClassId(int i) {
        return new ClassId(this.packageFqName, numberedClassName(i));
    }

    public String toString() {
        return this.packageFqName + '.' + this.classNamePrefix + 'N';
    }

    /* JADX INFO: compiled from: FunctionTypeKind.kt */
    public static final class Function extends FunctionTypeKind {
        public static final Function INSTANCE = new Function();

        private Function() {
            super(StandardNames.BUILT_INS_PACKAGE_FQ_NAME, "Function", false, null, true, 0, 32, null);
        }
    }

    /* JADX INFO: compiled from: FunctionTypeKind.kt */
    public static final class SuspendFunction extends FunctionTypeKind {
        public static final SuspendFunction INSTANCE = new SuspendFunction();

        private SuspendFunction() {
            super(StandardNames.COROUTINES_PACKAGE_FQ_NAME, "SuspendFunction", false, null, true, Function.INSTANCE.getMaxArity() - 1);
        }
    }

    /* JADX INFO: compiled from: FunctionTypeKind.kt */
    public static final class KFunction extends FunctionTypeKind {
        public static final KFunction INSTANCE = new KFunction();

        private KFunction() {
            super(StandardNames.KOTLIN_REFLECT_FQ_NAME, "KFunction", true, null, false, Function.INSTANCE.getMaxArity());
        }
    }

    /* JADX INFO: compiled from: FunctionTypeKind.kt */
    public static final class KSuspendFunction extends FunctionTypeKind {
        public static final KSuspendFunction INSTANCE = new KSuspendFunction();

        private KSuspendFunction() {
            super(StandardNames.KOTLIN_REFLECT_FQ_NAME, "KSuspendFunction", true, null, false, SuspendFunction.INSTANCE.getMaxArity());
        }
    }

    /* JADX INFO: compiled from: FunctionTypeKind.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
