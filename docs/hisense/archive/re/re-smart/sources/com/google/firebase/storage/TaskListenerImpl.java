package com.google.firebase.storage;

import android.app.Activity;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.storage.StorageTask.ProvideError;
import com.google.firebase.storage.internal.ActivityLifecycleListener;
import com.google.firebase.storage.internal.SmartHandler;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class TaskListenerImpl<ListenerTypeT, ResultT extends StorageTask.ProvideError> {
    public OnRaise<ListenerTypeT, ResultT> onRaise;
    public int targetStates;
    public StorageTask<ResultT> task;
    public final Queue<ListenerTypeT> listenerQueue = new ConcurrentLinkedQueue();
    public final HashMap<ListenerTypeT, SmartHandler> handlerMap = new HashMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public interface OnRaise<ListenerTypeT, ResultT> {
        void raise(@NonNull ListenerTypeT listenertypet, @NonNull ResultT resultt);
    }

    public TaskListenerImpl(@NonNull StorageTask<ResultT> storageTask, int i, @NonNull OnRaise<ListenerTypeT, ResultT> onRaise) {
        this.task = storageTask;
        this.targetStates = i;
        this.onRaise = onRaise;
    }

    public void addListener(@Nullable Activity activity, @Nullable Executor executor, @NonNull final ListenerTypeT listenertypet) {
        boolean z;
        SmartHandler smartHandler;
        Preconditions.checkNotNull(listenertypet);
        synchronized (this.task.getSyncObject()) {
            boolean z2 = true;
            z = (this.task.getInternalState() & this.targetStates) != 0;
            this.listenerQueue.add(listenertypet);
            smartHandler = new SmartHandler(executor);
            this.handlerMap.put(listenertypet, smartHandler);
            if (activity != null) {
                int i = Build.VERSION.SDK_INT;
                if (activity.isDestroyed()) {
                    z2 = false;
                }
                Preconditions.checkArgument(z2, "Activity is already destroyed!");
                ActivityLifecycleListener.instance.runOnActivityStopped(activity, listenertypet, new Runnable() { // from class: b.e.b.i.m
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f658a.a(listenertypet);
                    }
                });
            }
        }
        if (z) {
            final StorageTask.ProvideError provideErrorSnapState = this.task.snapState();
            smartHandler.callBack(new Runnable() { // from class: b.e.b.i.l
                @Override // java.lang.Runnable
                public final void run() {
                    this.f655a.a(listenertypet, provideErrorSnapState);
                }
            });
        }
    }

    public /* synthetic */ void b(Object obj, StorageTask.ProvideError provideError) {
        this.onRaise.raise(obj, provideError);
    }

    public int getListenerCount() {
        return Math.max(this.listenerQueue.size(), this.handlerMap.size());
    }

    public void onInternalStateChanged() {
        if ((this.task.getInternalState() & this.targetStates) != 0) {
            final StorageTask.ProvideError provideErrorSnapState = this.task.snapState();
            for (final ListenerTypeT listenertypet : this.listenerQueue) {
                SmartHandler smartHandler = this.handlerMap.get(listenertypet);
                if (smartHandler != null) {
                    smartHandler.callBack(new Runnable() { // from class: b.e.b.i.k
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f652a.b(listenertypet, provideErrorSnapState);
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: renamed from: removeListener, reason: merged with bridge method [inline-methods] */
    public void a(@NonNull ListenerTypeT listenertypet) {
        Preconditions.checkNotNull(listenertypet);
        synchronized (this.task.getSyncObject()) {
            this.handlerMap.remove(listenertypet);
            this.listenerQueue.remove(listenertypet);
            ActivityLifecycleListener.instance.removeCookie(listenertypet);
        }
    }

    public /* synthetic */ void a(Object obj, StorageTask.ProvideError provideError) {
        this.onRaise.raise(obj, provideError);
    }
}
