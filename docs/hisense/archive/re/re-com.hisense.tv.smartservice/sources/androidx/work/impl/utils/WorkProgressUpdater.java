package androidx.work.impl.utils;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.work.Data;
import androidx.work.Logger;
import androidx.work.ProgressUpdater;
import androidx.work.WorkInfo;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.model.WorkProgress;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.futures.SettableFuture;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class WorkProgressUpdater implements ProgressUpdater {
    public static final String TAG = Logger.tagWithPrefix("WorkProgressUpdater");
    public final TaskExecutor mTaskExecutor;
    public final WorkDatabase mWorkDatabase;

    public WorkProgressUpdater(@NonNull WorkDatabase workDatabase, @NonNull TaskExecutor taskExecutor) {
        this.mWorkDatabase = workDatabase;
        this.mTaskExecutor = taskExecutor;
    }

    @Override // androidx.work.ProgressUpdater
    @NonNull
    public ListenableFuture<Void> updateProgress(@NonNull final Context context, @NonNull final UUID id, @NonNull final Data data) {
        final SettableFuture settableFuture = new SettableFuture();
        this.mTaskExecutor.executeOnBackgroundThread(new Runnable() { // from class: androidx.work.impl.utils.WorkProgressUpdater.1
            @Override // java.lang.Runnable
            public void run() {
                String string = id.toString();
                Logger.get().debug(WorkProgressUpdater.TAG, String.format("Updating progress for %s (%s)", id, data), new Throwable[0]);
                WorkProgressUpdater.this.mWorkDatabase.beginTransaction();
                try {
                    try {
                        WorkSpec workSpec = WorkProgressUpdater.this.mWorkDatabase.workSpecDao().getWorkSpec(string);
                        if (workSpec == null) {
                            throw new IllegalStateException("Calls to setProgressAsync() must complete before a ListenableWorker signals completion of work by returning an instance of Result.");
                        }
                        if (workSpec.state == WorkInfo.State.RUNNING) {
                            WorkProgressUpdater.this.mWorkDatabase.workProgressDao().insert(new WorkProgress(string, data));
                        } else {
                            Logger.get().warning(WorkProgressUpdater.TAG, String.format("Ignoring setProgressAsync(...). WorkSpec (%s) is not in a RUNNING state.", string), new Throwable[0]);
                        }
                        settableFuture.set(null);
                        WorkProgressUpdater.this.mWorkDatabase.setTransactionSuccessful();
                        WorkProgressUpdater.this.mWorkDatabase.endTransaction();
                    } catch (Throwable th) {
                        Logger.get().error(WorkProgressUpdater.TAG, "Error updating Worker progress", th);
                        settableFuture.setException(th);
                    }
                } catch (Throwable th2) {
                    WorkProgressUpdater.this.mWorkDatabase.endTransaction();
                    throw th2;
                }
            }
        });
        return settableFuture;
    }
}
