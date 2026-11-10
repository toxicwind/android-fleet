package androidx.work;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

/* JADX INFO: loaded from: classes.dex */
public abstract class InputMergerFactory {

    /* JADX INFO: renamed from: androidx.work.InputMergerFactory$1, reason: invalid class name */
    public class AnonymousClass1 extends InputMergerFactory {
        @Override // androidx.work.InputMergerFactory
        @Nullable
        public InputMerger createInputMerger(@NonNull String className) {
            return null;
        }
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static InputMergerFactory getDefaultInputMergerFactory() {
        return new AnonymousClass1();
    }

    @Nullable
    public abstract InputMerger createInputMerger(@NonNull String className);

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final InputMerger createInputMergerWithDefaultFallback(@NonNull String className) {
        InputMerger inputMergerCreateInputMerger = createInputMerger(className);
        return inputMergerCreateInputMerger == null ? InputMerger.fromClassName(className) : inputMergerCreateInputMerger;
    }
}
