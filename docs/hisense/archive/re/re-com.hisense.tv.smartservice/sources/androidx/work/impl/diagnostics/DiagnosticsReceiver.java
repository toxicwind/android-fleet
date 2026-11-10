package androidx.work.impl.diagnostics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.work.ListenableWorker;
import androidx.work.Logger;
import androidx.work.OneTimeWorkRequest;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.workers.DiagnosticsWorker;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class DiagnosticsReceiver extends BroadcastReceiver {
    public static final String TAG = Logger.tagWithPrefix("DiagnosticsRcvr");

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NonNull Context context, @Nullable Intent intent) {
        if (intent == null) {
            return;
        }
        Logger.get().debug(TAG, "Requesting diagnostics", new Throwable[0]);
        try {
            WorkManagerImpl.getInstance(context).enqueue(OneTimeWorkRequest.from((Class<? extends ListenableWorker>) DiagnosticsWorker.class));
        } catch (IllegalStateException e2) {
            Logger.get().error(TAG, "WorkManager is not initialized", e2);
        }
    }
}
