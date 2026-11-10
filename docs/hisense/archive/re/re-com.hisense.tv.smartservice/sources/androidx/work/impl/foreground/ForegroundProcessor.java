package androidx.work.impl.foreground;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.work.ForegroundInfo;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public interface ForegroundProcessor {
    void startForeground(@NonNull String workSpecId, @NonNull ForegroundInfo foregroundInfo);

    void stopForeground(@NonNull String workSpecId);
}
