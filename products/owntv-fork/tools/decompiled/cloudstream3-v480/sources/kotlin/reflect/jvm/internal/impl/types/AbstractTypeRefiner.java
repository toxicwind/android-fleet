package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;

/* JADX INFO: compiled from: AbstractTypeRefiner.kt */
/* JADX INFO: loaded from: classes7.dex */
public abstract class AbstractTypeRefiner {
    public abstract KotlinTypeMarker refineType(KotlinTypeMarker kotlinTypeMarker);

    /* JADX INFO: compiled from: AbstractTypeRefiner.kt */
    public static final class Default extends AbstractTypeRefiner {
        public static final Default INSTANCE = new Default();

        @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeRefiner
        public KotlinTypeMarker refineType(KotlinTypeMarker type) {
            Intrinsics.checkNotNullParameter(type, "type");
            return type;
        }

        private Default() {
        }
    }
}
