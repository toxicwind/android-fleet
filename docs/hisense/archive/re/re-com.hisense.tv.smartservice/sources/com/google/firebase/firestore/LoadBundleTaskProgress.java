package com.google.firebase.firestore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.google.firebase.firestore.bundle.BundleMetadata;

/* JADX INFO: loaded from: classes.dex */
public final class LoadBundleTaskProgress {
    public static final LoadBundleTaskProgress INITIAL = new LoadBundleTaskProgress(0, 0, 0, 0, null, TaskState.SUCCESS);
    public final long bytesLoaded;
    public final int documentsLoaded;

    @Nullable
    public final Exception exception;

    @NonNull
    public final TaskState taskState;
    public final long totalBytes;
    public final int totalDocuments;

    public enum TaskState {
        ERROR,
        RUNNING,
        SUCCESS
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public LoadBundleTaskProgress(int i, int i2, long j, long j2, @Nullable Exception exc, @NonNull TaskState taskState) {
        this.documentsLoaded = i;
        this.totalDocuments = i2;
        this.bytesLoaded = j;
        this.totalBytes = j2;
        this.taskState = taskState;
        this.exception = exc;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static LoadBundleTaskProgress forInitial(@NonNull BundleMetadata bundleMetadata) {
        return new LoadBundleTaskProgress(0, bundleMetadata.getTotalDocuments(), 0L, bundleMetadata.getTotalBytes(), null, TaskState.RUNNING);
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static LoadBundleTaskProgress forSuccess(@NonNull BundleMetadata bundleMetadata) {
        return new LoadBundleTaskProgress(bundleMetadata.getTotalDocuments(), bundleMetadata.getTotalDocuments(), bundleMetadata.getTotalBytes(), bundleMetadata.getTotalBytes(), null, TaskState.SUCCESS);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || LoadBundleTaskProgress.class != obj.getClass()) {
            return false;
        }
        LoadBundleTaskProgress loadBundleTaskProgress = (LoadBundleTaskProgress) obj;
        if (this.documentsLoaded != loadBundleTaskProgress.documentsLoaded || this.totalDocuments != loadBundleTaskProgress.totalDocuments || this.bytesLoaded != loadBundleTaskProgress.bytesLoaded || this.totalBytes != loadBundleTaskProgress.totalBytes || this.taskState != loadBundleTaskProgress.taskState) {
            return false;
        }
        Exception exc = this.exception;
        if (exc != null) {
            return exc.equals(loadBundleTaskProgress.exception);
        }
        return loadBundleTaskProgress.exception == null;
    }

    public long getBytesLoaded() {
        return this.bytesLoaded;
    }

    public int getDocumentsLoaded() {
        return this.documentsLoaded;
    }

    @Nullable
    public Exception getException() {
        return this.exception;
    }

    @NonNull
    public TaskState getTaskState() {
        return this.taskState;
    }

    public long getTotalBytes() {
        return this.totalBytes;
    }

    public int getTotalDocuments() {
        return this.totalDocuments;
    }

    public int hashCode() {
        int i = ((this.documentsLoaded * 31) + this.totalDocuments) * 31;
        long j = this.bytesLoaded;
        int i2 = (i + ((int) (j ^ (j >>> 32)))) * 31;
        long j2 = this.totalBytes;
        int iHashCode = (this.taskState.hashCode() + ((i2 + ((int) (j2 ^ (j2 >>> 32)))) * 31)) * 31;
        Exception exc = this.exception;
        return iHashCode + (exc != null ? exc.hashCode() : 0);
    }
}
