package androidx.work;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/* JADX INFO: loaded from: classes.dex */
public interface RunnableScheduler {
    void cancel(@NonNull Runnable runnable);

    void scheduleWithDelay(@IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG) long delayInMillis, @NonNull Runnable runnable);
}
