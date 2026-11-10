package androidx.work.impl.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class LiveDataUtils {

    /* JADX INFO: Add missing generic type declarations: [In] */
    /* JADX INFO: renamed from: androidx.work.impl.utils.LiveDataUtils$1, reason: invalid class name */
    public class AnonymousClass1<In> implements Observer<In> {
        public Out mCurrentOutput = null;
        public final /* synthetic */ Object val$lock;
        public final /* synthetic */ Function val$mappingMethod;
        public final /* synthetic */ MediatorLiveData val$outputLiveData;
        public final /* synthetic */ TaskExecutor val$workTaskExecutor;

        public AnonymousClass1(final TaskExecutor val$workTaskExecutor, final Object val$lock, final Function val$mappingMethod, final MediatorLiveData val$outputLiveData) {
            this.val$workTaskExecutor = val$workTaskExecutor;
            this.val$lock = val$lock;
            this.val$mappingMethod = val$mappingMethod;
            this.val$outputLiveData = val$outputLiveData;
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(@Nullable final In input) {
            this.val$workTaskExecutor.executeOnBackgroundThread(new Runnable() { // from class: androidx.work.impl.utils.LiveDataUtils.1.1
                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Type inference failed for: r1v3, types: [Out, java.lang.Object] */
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (AnonymousClass1.this.val$lock) {
                        ?? Apply = AnonymousClass1.this.val$mappingMethod.apply(input);
                        if (AnonymousClass1.this.mCurrentOutput == 0 && Apply != 0) {
                            AnonymousClass1.this.mCurrentOutput = Apply;
                            AnonymousClass1.this.val$outputLiveData.postValue(Apply);
                        } else if (AnonymousClass1.this.mCurrentOutput != 0 && !AnonymousClass1.this.mCurrentOutput.equals(Apply)) {
                            AnonymousClass1.this.mCurrentOutput = Apply;
                            AnonymousClass1.this.val$outputLiveData.postValue(Apply);
                        }
                    }
                }
            });
        }
    }

    public static <In, Out> LiveData<Out> dedupedMappedLiveDataFor(@NonNull LiveData<In> inputLiveData, @NonNull final Function<In, Out> mappingMethod, @NonNull final TaskExecutor workTaskExecutor) {
        Object obj = new Object();
        MediatorLiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(inputLiveData, new AnonymousClass1(workTaskExecutor, obj, mappingMethod, mediatorLiveData));
        return mediatorLiveData;
    }
}
