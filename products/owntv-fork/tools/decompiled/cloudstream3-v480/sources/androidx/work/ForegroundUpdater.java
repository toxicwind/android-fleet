package androidx.work;

import android.content.Context;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.UUID;

/* JADX INFO: loaded from: classes5.dex */
public interface ForegroundUpdater {
    ListenableFuture<Void> setForegroundAsync(Context context, UUID id, ForegroundInfo foregroundInfo);
}
