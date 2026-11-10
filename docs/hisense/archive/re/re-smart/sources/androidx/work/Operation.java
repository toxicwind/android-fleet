package androidx.work;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.lifecycle.LiveData;
import com.google.common.util.concurrent.ListenableFuture;
import com.ju.lib.datareport.dynamic.DynamicDomainHelper;

/* JADX INFO: loaded from: classes.dex */
public interface Operation {

    @SuppressLint({"SyntheticAccessor"})
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final State.IN_PROGRESS IN_PROGRESS;

    @SuppressLint({"SyntheticAccessor"})
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final State.SUCCESS SUCCESS;

    public static abstract class State {

        public static final class FAILURE extends State {
            public final Throwable mThrowable;

            public FAILURE(@NonNull Throwable exception) {
                this.mThrowable = exception;
            }

            @NonNull
            public Throwable getThrowable() {
                return this.mThrowable;
            }

            @NonNull
            public String toString() {
                return String.format("FAILURE (%s)", this.mThrowable.getMessage());
            }
        }

        public static final class IN_PROGRESS extends State {
            public IN_PROGRESS() {
            }

            @NonNull
            public String toString() {
                return "IN_PROGRESS";
            }

            public /* synthetic */ IN_PROGRESS(AnonymousClass1 anonymousClass1) {
            }
        }

        public static final class SUCCESS extends State {
            public SUCCESS() {
            }

            @NonNull
            public String toString() {
                return DynamicDomainHelper.DYNAMIC_SUCCESS;
            }

            public /* synthetic */ SUCCESS(AnonymousClass1 anonymousClass1) {
            }
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public State() {
        }
    }

    static {
        AnonymousClass1 anonymousClass1 = null;
        SUCCESS = new State.SUCCESS(anonymousClass1);
        IN_PROGRESS = new State.IN_PROGRESS(anonymousClass1);
    }

    @NonNull
    ListenableFuture<State.SUCCESS> getResult();

    @NonNull
    LiveData<State> getState();
}
