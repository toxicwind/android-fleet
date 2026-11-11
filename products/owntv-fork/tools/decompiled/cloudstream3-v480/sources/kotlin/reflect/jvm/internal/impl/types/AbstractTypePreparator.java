package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.RigidTypeMarker;

/* JADX INFO: compiled from: AbstractTypePreparator.kt */
/* JADX INFO: loaded from: classes7.dex */
public abstract class AbstractTypePreparator {
    public RigidTypeMarker clearTypeFromUnnecessaryAttributes(RigidTypeMarker type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return type;
    }

    public abstract KotlinTypeMarker prepareType(KotlinTypeMarker kotlinTypeMarker);

    /* JADX INFO: compiled from: AbstractTypePreparator.kt */
    public static final class Default extends AbstractTypePreparator {
        public static final Default INSTANCE = new Default();

        @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypePreparator
        public KotlinTypeMarker prepareType(KotlinTypeMarker type) {
            Intrinsics.checkNotNullParameter(type, "type");
            return type;
        }

        private Default() {
        }
    }
}
