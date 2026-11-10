package androidx.work;

import android.annotation.SuppressLint;
import android.os.Build;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.work.impl.DefaultRunnableScheduler;
import b.a.a.a.a;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class Configuration {

    @SuppressLint({"MinMaxConstant"})
    public static final int MIN_SCHEDULER_LIMIT = 20;

    @Nullable
    public final String mDefaultProcessName;

    @Nullable
    public final InitializationExceptionHandler mExceptionHandler;

    @NonNull
    public final Executor mExecutor;

    @NonNull
    public final InputMergerFactory mInputMergerFactory;
    public final boolean mIsUsingDefaultTaskExecutor;
    public final int mLoggingLevel;
    public final int mMaxJobSchedulerId;
    public final int mMaxSchedulerLimit;
    public final int mMinJobSchedulerId;

    @NonNull
    public final RunnableScheduler mRunnableScheduler;

    @NonNull
    public final Executor mTaskExecutor;

    @NonNull
    public final WorkerFactory mWorkerFactory;

    /* JADX INFO: renamed from: androidx.work.Configuration$1, reason: invalid class name */
    public class AnonymousClass1 implements ThreadFactory {
        public final AtomicInteger mThreadCount = new AtomicInteger(0);
        public final /* synthetic */ boolean val$isTaskExecutor;

        public AnonymousClass1(final boolean val$isTaskExecutor) {
            this.val$isTaskExecutor = val$isTaskExecutor;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            StringBuilder sbA = a.a(this.val$isTaskExecutor ? "WM.task-" : "androidx.work-");
            sbA.append(this.mThreadCount.incrementAndGet());
            return new Thread(runnable, sbA.toString());
        }
    }

    public interface Provider {
        @NonNull
        Configuration getWorkManagerConfiguration();
    }

    public Configuration(@NonNull Builder builder) {
        Executor executor = builder.mExecutor;
        if (executor == null) {
            this.mExecutor = createDefaultExecutor(false);
        } else {
            this.mExecutor = executor;
        }
        Executor executor2 = builder.mTaskExecutor;
        if (executor2 == null) {
            this.mIsUsingDefaultTaskExecutor = true;
            this.mTaskExecutor = createDefaultExecutor(true);
        } else {
            this.mIsUsingDefaultTaskExecutor = false;
            this.mTaskExecutor = executor2;
        }
        WorkerFactory workerFactory = builder.mWorkerFactory;
        if (workerFactory == null) {
            this.mWorkerFactory = WorkerFactory.getDefaultWorkerFactory();
        } else {
            this.mWorkerFactory = workerFactory;
        }
        InputMergerFactory inputMergerFactory = builder.mInputMergerFactory;
        if (inputMergerFactory == null) {
            this.mInputMergerFactory = new InputMergerFactory.AnonymousClass1();
        } else {
            this.mInputMergerFactory = inputMergerFactory;
        }
        RunnableScheduler runnableScheduler = builder.mRunnableScheduler;
        if (runnableScheduler == null) {
            this.mRunnableScheduler = new DefaultRunnableScheduler();
        } else {
            this.mRunnableScheduler = runnableScheduler;
        }
        this.mLoggingLevel = builder.mLoggingLevel;
        this.mMinJobSchedulerId = builder.mMinJobSchedulerId;
        this.mMaxJobSchedulerId = builder.mMaxJobSchedulerId;
        this.mMaxSchedulerLimit = builder.mMaxSchedulerLimit;
        this.mExceptionHandler = builder.mExceptionHandler;
        this.mDefaultProcessName = builder.mDefaultProcessName;
    }

    @NonNull
    private Executor createDefaultExecutor(boolean isTaskExecutor) {
        return Executors.newFixedThreadPool(Math.max(2, Math.min(Runtime.getRuntime().availableProcessors() - 1, 4)), new AnonymousClass1(isTaskExecutor));
    }

    @NonNull
    private ThreadFactory createDefaultThreadFactory(boolean isTaskExecutor) {
        return new AnonymousClass1(isTaskExecutor);
    }

    @Nullable
    public String getDefaultProcessName() {
        return this.mDefaultProcessName;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public InitializationExceptionHandler getExceptionHandler() {
        return this.mExceptionHandler;
    }

    @NonNull
    public Executor getExecutor() {
        return this.mExecutor;
    }

    @NonNull
    public InputMergerFactory getInputMergerFactory() {
        return this.mInputMergerFactory;
    }

    public int getMaxJobSchedulerId() {
        return this.mMaxJobSchedulerId;
    }

    @IntRange(from = 20, to = 50)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getMaxSchedulerLimit() {
        int i = Build.VERSION.SDK_INT;
        return this.mMaxSchedulerLimit;
    }

    public int getMinJobSchedulerId() {
        return this.mMinJobSchedulerId;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getMinimumLoggingLevel() {
        return this.mLoggingLevel;
    }

    @NonNull
    public RunnableScheduler getRunnableScheduler() {
        return this.mRunnableScheduler;
    }

    @NonNull
    public Executor getTaskExecutor() {
        return this.mTaskExecutor;
    }

    @NonNull
    public WorkerFactory getWorkerFactory() {
        return this.mWorkerFactory;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isUsingDefaultTaskExecutor() {
        return this.mIsUsingDefaultTaskExecutor;
    }

    public static final class Builder {

        @Nullable
        public String mDefaultProcessName;

        @Nullable
        public InitializationExceptionHandler mExceptionHandler;
        public Executor mExecutor;
        public InputMergerFactory mInputMergerFactory;
        public int mLoggingLevel;
        public int mMaxJobSchedulerId;
        public int mMaxSchedulerLimit;
        public int mMinJobSchedulerId;
        public RunnableScheduler mRunnableScheduler;
        public Executor mTaskExecutor;
        public WorkerFactory mWorkerFactory;

        public Builder() {
            this.mLoggingLevel = 4;
            this.mMinJobSchedulerId = 0;
            this.mMaxJobSchedulerId = Integer.MAX_VALUE;
            this.mMaxSchedulerLimit = 20;
        }

        @NonNull
        public Configuration build() {
            return new Configuration(this);
        }

        @NonNull
        public Builder setDefaultProcessName(@NonNull String processName) {
            this.mDefaultProcessName = processName;
            return this;
        }

        @NonNull
        public Builder setExecutor(@NonNull Executor executor) {
            this.mExecutor = executor;
            return this;
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setInitializationExceptionHandler(@NonNull InitializationExceptionHandler exceptionHandler) {
            this.mExceptionHandler = exceptionHandler;
            return this;
        }

        @NonNull
        public Builder setInputMergerFactory(@NonNull InputMergerFactory inputMergerFactory) {
            this.mInputMergerFactory = inputMergerFactory;
            return this;
        }

        @NonNull
        public Builder setJobSchedulerJobIdRange(int minJobSchedulerId, int maxJobSchedulerId) {
            if (maxJobSchedulerId - minJobSchedulerId < 1000) {
                throw new IllegalArgumentException("WorkManager needs a range of at least 1000 job ids.");
            }
            this.mMinJobSchedulerId = minJobSchedulerId;
            this.mMaxJobSchedulerId = maxJobSchedulerId;
            return this;
        }

        @NonNull
        public Builder setMaxSchedulerLimit(int maxSchedulerLimit) {
            if (maxSchedulerLimit < 20) {
                throw new IllegalArgumentException("WorkManager needs to be able to schedule at least 20 jobs in JobScheduler.");
            }
            this.mMaxSchedulerLimit = Math.min(maxSchedulerLimit, 50);
            return this;
        }

        @NonNull
        public Builder setMinimumLoggingLevel(int loggingLevel) {
            this.mLoggingLevel = loggingLevel;
            return this;
        }

        @NonNull
        public Builder setRunnableScheduler(@NonNull RunnableScheduler runnableScheduler) {
            this.mRunnableScheduler = runnableScheduler;
            return this;
        }

        @NonNull
        public Builder setTaskExecutor(@NonNull Executor taskExecutor) {
            this.mTaskExecutor = taskExecutor;
            return this;
        }

        @NonNull
        public Builder setWorkerFactory(@NonNull WorkerFactory workerFactory) {
            this.mWorkerFactory = workerFactory;
            return this;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder(@NonNull Configuration configuration) {
            this.mExecutor = configuration.mExecutor;
            this.mWorkerFactory = configuration.mWorkerFactory;
            this.mInputMergerFactory = configuration.mInputMergerFactory;
            this.mTaskExecutor = configuration.mTaskExecutor;
            this.mLoggingLevel = configuration.mLoggingLevel;
            this.mMinJobSchedulerId = configuration.mMinJobSchedulerId;
            this.mMaxJobSchedulerId = configuration.mMaxJobSchedulerId;
            this.mMaxSchedulerLimit = configuration.mMaxSchedulerLimit;
            this.mRunnableScheduler = configuration.mRunnableScheduler;
            this.mExceptionHandler = configuration.mExceptionHandler;
            this.mDefaultProcessName = configuration.mDefaultProcessName;
        }
    }
}
