package androidx.work;

import android.os.Build;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class OneTimeWorkRequest extends WorkRequest {

    public static final class Builder extends WorkRequest.Builder<Builder, OneTimeWorkRequest> {
        public Builder(@NonNull Class<? extends ListenableWorker> workerClass) {
            super(workerClass);
            this.mWorkSpec.inputMergerClassName = OverwritingInputMerger.class.getName();
        }

        @Override // androidx.work.WorkRequest.Builder
        @NonNull
        public Builder getThis() {
            return this;
        }

        @NonNull
        public Builder setInputMerger(@NonNull Class<? extends InputMerger> inputMerger) {
            this.mWorkSpec.inputMergerClassName = inputMerger.getName();
            return this;
        }

        @Override // androidx.work.WorkRequest.Builder
        @NonNull
        public OneTimeWorkRequest buildInternal() {
            if (this.mBackoffCriteriaSet) {
                int i = Build.VERSION.SDK_INT;
                if (this.mWorkSpec.constraints.requiresDeviceIdle()) {
                    throw new IllegalArgumentException("Cannot set backoff criteria on an idle mode job");
                }
            }
            return new OneTimeWorkRequest(this);
        }
    }

    public OneTimeWorkRequest(Builder builder) {
        super(builder.mId, builder.mWorkSpec, builder.mTags);
    }

    @NonNull
    public static OneTimeWorkRequest from(@NonNull Class<? extends ListenableWorker> workerClass) {
        return new Builder(workerClass).build();
    }

    @NonNull
    public static List<OneTimeWorkRequest> from(@NonNull List<Class<? extends ListenableWorker>> workerClasses) {
        ArrayList arrayList = new ArrayList(workerClasses.size());
        Iterator<Class<? extends ListenableWorker>> it = workerClasses.iterator();
        while (it.hasNext()) {
            arrayList.add(new Builder(it.next()).build());
        }
        return arrayList;
    }
}
