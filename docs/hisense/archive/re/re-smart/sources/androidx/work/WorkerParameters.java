package androidx.work;

import android.net.Network;
import android.net.Uri;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class WorkerParameters {

    @NonNull
    public Executor mBackgroundExecutor;

    @NonNull
    public ForegroundUpdater mForegroundUpdater;

    @NonNull
    public UUID mId;

    @NonNull
    public Data mInputData;

    @NonNull
    public ProgressUpdater mProgressUpdater;
    public int mRunAttemptCount;

    @NonNull
    public RuntimeExtras mRuntimeExtras;

    @NonNull
    public Set<String> mTags;

    @NonNull
    public TaskExecutor mWorkTaskExecutor;

    @NonNull
    public WorkerFactory mWorkerFactory;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static class RuntimeExtras {

        @RequiresApi(28)
        public Network network;

        @NonNull
        public List<String> triggeredContentAuthorities = Collections.emptyList();

        @NonNull
        public List<Uri> triggeredContentUris = Collections.emptyList();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public WorkerParameters(@NonNull UUID id, @NonNull Data inputData, @NonNull Collection<String> tags, @NonNull RuntimeExtras runtimeExtras, @IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG) int runAttemptCount, @NonNull Executor backgroundExecutor, @NonNull TaskExecutor workTaskExecutor, @NonNull WorkerFactory workerFactory, @NonNull ProgressUpdater progressUpdater, @NonNull ForegroundUpdater foregroundUpdater) {
        this.mId = id;
        this.mInputData = inputData;
        this.mTags = new HashSet(tags);
        this.mRuntimeExtras = runtimeExtras;
        this.mRunAttemptCount = runAttemptCount;
        this.mBackgroundExecutor = backgroundExecutor;
        this.mWorkTaskExecutor = workTaskExecutor;
        this.mWorkerFactory = workerFactory;
        this.mProgressUpdater = progressUpdater;
        this.mForegroundUpdater = foregroundUpdater;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Executor getBackgroundExecutor() {
        return this.mBackgroundExecutor;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public ForegroundUpdater getForegroundUpdater() {
        return this.mForegroundUpdater;
    }

    @NonNull
    public UUID getId() {
        return this.mId;
    }

    @NonNull
    public Data getInputData() {
        return this.mInputData;
    }

    @Nullable
    @RequiresApi(28)
    public Network getNetwork() {
        return this.mRuntimeExtras.network;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public ProgressUpdater getProgressUpdater() {
        return this.mProgressUpdater;
    }

    @IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG)
    public int getRunAttemptCount() {
        return this.mRunAttemptCount;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public RuntimeExtras getRuntimeExtras() {
        return this.mRuntimeExtras;
    }

    @NonNull
    public Set<String> getTags() {
        return this.mTags;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public TaskExecutor getTaskExecutor() {
        return this.mWorkTaskExecutor;
    }

    @NonNull
    @RequiresApi(24)
    public List<String> getTriggeredContentAuthorities() {
        return this.mRuntimeExtras.triggeredContentAuthorities;
    }

    @NonNull
    @RequiresApi(24)
    public List<Uri> getTriggeredContentUris() {
        return this.mRuntimeExtras.triggeredContentUris;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public WorkerFactory getWorkerFactory() {
        return this.mWorkerFactory;
    }
}
