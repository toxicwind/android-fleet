package androidx.work;

import android.content.Context;
import androidx.annotation.NonNull;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public interface ForegroundUpdater {
    @NonNull
    ListenableFuture<Void> setForegroundAsync(@NonNull Context context, @NonNull UUID id, @NonNull ForegroundInfo foregroundInfo);
}
