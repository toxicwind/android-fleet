package androidx.work;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.startup.Initializer;
import androidx.work.impl.WorkManagerImpl;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class WorkManagerInitializer implements Initializer<WorkManager> {
    public static final String TAG = Logger.tagWithPrefix("WrkMgrInitializer");

    @Override // androidx.startup.Initializer
    @NonNull
    public List<Class<? extends Initializer<?>>> dependencies() {
        return Collections.emptyList();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.startup.Initializer
    @NonNull
    public WorkManager create(@NonNull Context context) {
        Logger.get().debug(TAG, "Initializing WorkManager with default configuration.", new Throwable[0]);
        WorkManagerImpl.initialize(context, new Configuration.Builder().build());
        return WorkManagerImpl.getInstance(context);
    }
}
