package com.google.firebase.remoteconfig.internal;

import android.util.Log;
import androidx.annotation.AnyThread;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
@AnyThread
public class ConfigCacheClient {
    public static final long DISK_READ_TIMEOUT_IN_SECONDS = 5;

    @Nullable
    @GuardedBy("this")
    public Task<ConfigContainer> cachedContainerTask = null;
    public final ExecutorService executorService;
    public final ConfigStorageClient storageClient;

    @GuardedBy("ConfigCacheClient.class")
    public static final Map<String, ConfigCacheClient> clientInstances = new HashMap();
    public static final Executor DIRECT_EXECUTOR = new Executor() { // from class: b.e.b.h.a.h
        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            runnable.run();
        }
    };

    private static class AwaitListener<TResult> implements OnSuccessListener<TResult>, OnFailureListener, OnCanceledListener {
        public final CountDownLatch latch;

        public AwaitListener() {
            this.latch = new CountDownLatch(1);
        }

        public void await() throws InterruptedException {
            this.latch.await();
        }

        @Override // com.google.android.gms.tasks.OnCanceledListener
        public void onCanceled() {
            this.latch.countDown();
        }

        @Override // com.google.android.gms.tasks.OnFailureListener
        public void onFailure(@NonNull Exception exc) {
            this.latch.countDown();
        }

        @Override // com.google.android.gms.tasks.OnSuccessListener
        public void onSuccess(TResult tresult) {
            this.latch.countDown();
        }

        public boolean await(long j, TimeUnit timeUnit) {
            return this.latch.await(j, timeUnit);
        }
    }

    public ConfigCacheClient(ExecutorService executorService, ConfigStorageClient configStorageClient) {
        this.executorService = executorService;
        this.storageClient = configStorageClient;
    }

    public static <TResult> TResult await(Task<TResult> task, long j, TimeUnit timeUnit) throws ExecutionException, TimeoutException {
        AwaitListener awaitListener = new AwaitListener();
        task.addOnSuccessListener(DIRECT_EXECUTOR, awaitListener);
        task.addOnFailureListener(DIRECT_EXECUTOR, awaitListener);
        task.addOnCanceledListener(DIRECT_EXECUTOR, awaitListener);
        if (!awaitListener.await(j, timeUnit)) {
            throw new TimeoutException("Task await timed out.");
        }
        if (task.isSuccessful()) {
            return task.getResult();
        }
        throw new ExecutionException(task.getException());
    }

    @VisibleForTesting
    public static synchronized void clearInstancesForTest() {
        clientInstances.clear();
    }

    public static synchronized ConfigCacheClient getInstance(ExecutorService executorService, ConfigStorageClient configStorageClient) {
        String fileName;
        fileName = configStorageClient.getFileName();
        if (!clientInstances.containsKey(fileName)) {
            clientInstances.put(fileName, new ConfigCacheClient(executorService, configStorageClient));
        }
        return clientInstances.get(fileName);
    }

    private synchronized void updateInMemoryConfigContainer(ConfigContainer configContainer) {
        this.cachedContainerTask = Tasks.forResult(configContainer);
    }

    public /* synthetic */ Void a(ConfigContainer configContainer) {
        return this.storageClient.write(configContainer);
    }

    public void clear() {
        synchronized (this) {
            this.cachedContainerTask = Tasks.forResult(null);
        }
        this.storageClient.clear();
    }

    public synchronized Task<ConfigContainer> get() {
        if (this.cachedContainerTask == null || (this.cachedContainerTask.isComplete() && !this.cachedContainerTask.isSuccessful())) {
            ExecutorService executorService = this.executorService;
            final ConfigStorageClient configStorageClient = this.storageClient;
            Objects.requireNonNull(configStorageClient);
            this.cachedContainerTask = Tasks.call(executorService, new Callable() { // from class: b.e.b.h.a.i
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return configStorageClient.read();
                }
            });
        }
        return this.cachedContainerTask;
    }

    @Nullable
    public ConfigContainer getBlocking() {
        return getBlocking(5L);
    }

    @Nullable
    @VisibleForTesting
    public synchronized Task<ConfigContainer> getCachedContainerTask() {
        return this.cachedContainerTask;
    }

    public Task<ConfigContainer> put(ConfigContainer configContainer) {
        return put(configContainer, true);
    }

    public /* synthetic */ Task a(boolean z, ConfigContainer configContainer, Void r3) {
        if (z) {
            updateInMemoryConfigContainer(configContainer);
        }
        return Tasks.forResult(configContainer);
    }

    @Nullable
    @VisibleForTesting
    public ConfigContainer getBlocking(long j) {
        synchronized (this) {
            if (this.cachedContainerTask != null && this.cachedContainerTask.isSuccessful()) {
                return this.cachedContainerTask.getResult();
            }
            try {
                return (ConfigContainer) await(get(), j, TimeUnit.SECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException e2) {
                Log.d(FirebaseRemoteConfig.TAG, "Reading from storage file failed.", e2);
                return null;
            }
        }
    }

    public Task<ConfigContainer> put(final ConfigContainer configContainer, final boolean z) {
        return Tasks.call(this.executorService, new Callable() { // from class: b.e.b.h.a.b
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f603a.a(configContainer);
            }
        }).onSuccessTask(this.executorService, new SuccessContinuation() { // from class: b.e.b.h.a.a
            @Override // com.google.android.gms.tasks.SuccessContinuation
            public final Task then(Object obj) {
                return this.f600a.a(z, configContainer, (Void) obj);
            }
        });
    }
}
