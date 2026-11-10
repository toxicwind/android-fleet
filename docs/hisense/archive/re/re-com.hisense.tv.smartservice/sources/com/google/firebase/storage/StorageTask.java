package com.google.firebase.storage;

import android.app.Activity;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import b.e.b.i.n;
import b.e.b.i.o;
import b.e.b.i.q;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.RuntimeExecutionException;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.StorageTask.ProvideError;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public abstract class StorageTask<ResultT extends ProvideError> extends ControllableTask<ResultT> {
    public static final int INTERNAL_STATE_CANCELED = 256;
    public static final int INTERNAL_STATE_CANCELING = 32;
    public static final int INTERNAL_STATE_FAILURE = 64;
    public static final int INTERNAL_STATE_IN_PROGRESS = 4;
    public static final int INTERNAL_STATE_NOT_STARTED = 1;
    public static final int INTERNAL_STATE_PAUSED = 16;
    public static final int INTERNAL_STATE_PAUSING = 8;
    public static final int INTERNAL_STATE_QUEUED = 2;
    public static final int INTERNAL_STATE_SUCCESS = 128;
    public static final int STATES_CANCELED = 256;
    public static final int STATES_COMPLETE = 448;
    public static final int STATES_FAILURE = 64;
    public static final int STATES_INPROGRESS = -465;
    public static final int STATES_PAUSED = 16;
    public static final int STATES_SUCCESS = 128;
    public static final String TAG = "StorageTask";
    public ResultT finalResult;
    public static final HashMap<Integer, HashSet<Integer>> ValidUserInitiatedStateChanges = new HashMap<>();
    public static final HashMap<Integer, HashSet<Integer>> ValidTaskInitiatedStateChanges = new HashMap<>();
    public final Object syncObject = new Object();

    @VisibleForTesting
    public final TaskListenerImpl<OnSuccessListener<? super ResultT>, ResultT> successManager = new TaskListenerImpl<>(this, 128, new TaskListenerImpl.OnRaise() { // from class: b.e.b.i.c
        @Override // com.google.firebase.storage.TaskListenerImpl.OnRaise
        public final void raise(Object obj, Object obj2) {
            this.f637a.a((OnSuccessListener) obj, (StorageTask.ProvideError) obj2);
        }
    });

    @VisibleForTesting
    public final TaskListenerImpl<OnFailureListener, ResultT> failureManager = new TaskListenerImpl<>(this, 64, new TaskListenerImpl.OnRaise() { // from class: b.e.b.i.i
        @Override // com.google.firebase.storage.TaskListenerImpl.OnRaise
        public final void raise(Object obj, Object obj2) {
            this.f647a.a((OnFailureListener) obj, (StorageTask.ProvideError) obj2);
        }
    });

    @VisibleForTesting
    public final TaskListenerImpl<OnCompleteListener<ResultT>, ResultT> completeListener = new TaskListenerImpl<>(this, STATES_COMPLETE, new TaskListenerImpl.OnRaise() { // from class: b.e.b.i.g
        @Override // com.google.firebase.storage.TaskListenerImpl.OnRaise
        public final void raise(Object obj, Object obj2) {
            this.f643a.a((OnCompleteListener) obj, (StorageTask.ProvideError) obj2);
        }
    });

    @VisibleForTesting
    public final TaskListenerImpl<OnCanceledListener, ResultT> cancelManager = new TaskListenerImpl<>(this, 256, new TaskListenerImpl.OnRaise() { // from class: b.e.b.i.d
        @Override // com.google.firebase.storage.TaskListenerImpl.OnRaise
        public final void raise(Object obj, Object obj2) {
            this.f638a.a((OnCanceledListener) obj, (StorageTask.ProvideError) obj2);
        }
    });

    @VisibleForTesting
    public final TaskListenerImpl<OnProgressListener<? super ResultT>, ResultT> progressManager = new TaskListenerImpl<>(this, STATES_INPROGRESS, new TaskListenerImpl.OnRaise() { // from class: b.e.b.i.a
        @Override // com.google.firebase.storage.TaskListenerImpl.OnRaise
        public final void raise(Object obj, Object obj2) {
            ((OnProgressListener) obj).onProgress((StorageTask.ProvideError) obj2);
        }
    });

    @VisibleForTesting
    public final TaskListenerImpl<OnPausedListener<? super ResultT>, ResultT> pausedManager = new TaskListenerImpl<>(this, 16, new TaskListenerImpl.OnRaise() { // from class: b.e.b.i.p
        @Override // com.google.firebase.storage.TaskListenerImpl.OnRaise
        public final void raise(Object obj, Object obj2) {
            ((OnPausedListener) obj).onPaused((StorageTask.ProvideError) obj2);
        }
    });
    public volatile int currentState = 1;

    /* JADX INFO: Access modifiers changed from: protected */
    public interface ProvideError {
        Exception getError();
    }

    public class SnapshotBase implements ProvideError {
        public final Exception error;

        public SnapshotBase(Exception exc) {
            if (exc != null) {
                this.error = exc;
                return;
            }
            if (StorageTask.this.isCanceled()) {
                this.error = StorageException.fromErrorStatus(Status.RESULT_CANCELED);
            } else if (StorageTask.this.getInternalState() == 64) {
                this.error = StorageException.fromErrorStatus(Status.RESULT_INTERNAL_ERROR);
            } else {
                this.error = null;
            }
        }

        @Override // com.google.firebase.storage.StorageTask.ProvideError
        @Nullable
        public Exception getError() {
            return this.error;
        }

        @NonNull
        public StorageReference getStorage() {
            return getTask().getStorage();
        }

        @NonNull
        public StorageTask<ResultT> getTask() {
            return StorageTask.this;
        }
    }

    static {
        ValidUserInitiatedStateChanges.put(1, new HashSet<>(Arrays.asList(16, 256)));
        ValidUserInitiatedStateChanges.put(2, new HashSet<>(Arrays.asList(8, 32)));
        ValidUserInitiatedStateChanges.put(4, new HashSet<>(Arrays.asList(8, 32)));
        ValidUserInitiatedStateChanges.put(16, new HashSet<>(Arrays.asList(2, 256)));
        ValidUserInitiatedStateChanges.put(64, new HashSet<>(Arrays.asList(2, 256)));
        ValidTaskInitiatedStateChanges.put(1, new HashSet<>(Arrays.asList(2, 64)));
        ValidTaskInitiatedStateChanges.put(2, new HashSet<>(Arrays.asList(4, 64, 128)));
        ValidTaskInitiatedStateChanges.put(4, new HashSet<>(Arrays.asList(4, 64, 128)));
        ValidTaskInitiatedStateChanges.put(8, new HashSet<>(Arrays.asList(16, 64, 128)));
        ValidTaskInitiatedStateChanges.put(32, new HashSet<>(Arrays.asList(256, 64, 128)));
    }

    @NonNull
    private <ContinuationResultT> Task<ContinuationResultT> continueWithImpl(@Nullable Executor executor, @NonNull final Continuation<ResultT, ContinuationResultT> continuation) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.completeListener.addListener(null, executor, new OnCompleteListener() { // from class: b.e.b.i.e
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.f639a.a(continuation, taskCompletionSource, task);
            }
        });
        return taskCompletionSource.getTask();
    }

    @NonNull
    private <ContinuationResultT> Task<ContinuationResultT> continueWithTaskImpl(@Nullable Executor executor, @NonNull final Continuation<ResultT, Task<ContinuationResultT>> continuation) {
        final CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource(cancellationTokenSource.getToken());
        this.completeListener.addListener(null, executor, new OnCompleteListener() { // from class: b.e.b.i.j
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.f648a.a(continuation, taskCompletionSource, cancellationTokenSource, task);
            }
        });
        return taskCompletionSource.getTask();
    }

    private void ensureFinalState() {
        if (isComplete() || isPaused() || getInternalState() == 2 || tryChangeState(256, false)) {
            return;
        }
        tryChangeState(64, false);
    }

    private ResultT getFinalResult() {
        ResultT resultt = this.finalResult;
        if (resultt != null) {
            return resultt;
        }
        if (!isComplete()) {
            return null;
        }
        if (this.finalResult == null) {
            this.finalResult = (ResultT) snapState();
        }
        return this.finalResult;
    }

    private String getStateString(int i) {
        if (i == 1) {
            return "INTERNAL_STATE_NOT_STARTED";
        }
        if (i == 2) {
            return "INTERNAL_STATE_QUEUED";
        }
        if (i == 4) {
            return "INTERNAL_STATE_IN_PROGRESS";
        }
        if (i == 8) {
            return "INTERNAL_STATE_PAUSING";
        }
        if (i == 16) {
            return "INTERNAL_STATE_PAUSED";
        }
        if (i == 32) {
            return "INTERNAL_STATE_CANCELING";
        }
        if (i == 64) {
            return "INTERNAL_STATE_FAILURE";
        }
        if (i != 128) {
            return i != 256 ? "Unknown Internal State!" : "INTERNAL_STATE_CANCELED";
        }
        return "INTERNAL_STATE_SUCCESS";
    }

    private String getStateString(int[] iArr) {
        if (iArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i : iArr) {
            sb.append(getStateString(i));
            sb.append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    @NonNull
    private <ContinuationResultT> Task<ContinuationResultT> successTaskImpl(@Nullable Executor executor, @NonNull final SuccessContinuation<ResultT, ContinuationResultT> successContinuation) {
        final CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource(cancellationTokenSource.getToken());
        this.successManager.addListener(null, executor, new OnSuccessListener() { // from class: b.e.b.i.h
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                StorageTask.a(successContinuation, taskCompletionSource, cancellationTokenSource, (StorageTask.ProvideError) obj);
            }
        });
        return taskCompletionSource.getTask();
    }

    public /* synthetic */ void a(OnSuccessListener onSuccessListener, ProvideError provideError) {
        StorageTaskManager._instance.unRegister(this);
        onSuccessListener.onSuccess(provideError);
    }

    @Override // com.google.firebase.storage.CancellableTask
    public boolean cancel() {
        return tryChangeState(new int[]{256, 32}, true);
    }

    @Override // com.google.android.gms.tasks.Task
    @NonNull
    public <ContinuationResultT> Task<ContinuationResultT> continueWith(@NonNull Continuation<ResultT, ContinuationResultT> continuation) {
        return continueWithImpl(null, continuation);
    }

    @Override // com.google.android.gms.tasks.Task
    @NonNull
    public <ContinuationResultT> Task<ContinuationResultT> continueWithTask(@NonNull Continuation<ResultT, Task<ContinuationResultT>> continuation) {
        return continueWithTaskImpl(null, continuation);
    }

    @Override // com.google.android.gms.tasks.Task
    @Nullable
    public Exception getException() {
        if (getFinalResult() == null) {
            return null;
        }
        return getFinalResult().getError();
    }

    @VisibleForTesting
    public int getInternalState() {
        return this.currentState;
    }

    @VisibleForTesting
    public Runnable getRunnable() {
        return new Runnable() { // from class: b.e.b.i.f
            @Override // java.lang.Runnable
            public final void run() {
                this.f642a.a();
            }
        };
    }

    @NonNull
    public ResultT getSnapshot() {
        return (ResultT) snapState();
    }

    @VisibleForTesting
    public abstract StorageReference getStorage();

    @VisibleForTesting
    public Object getSyncObject() {
        return this.syncObject;
    }

    @Override // com.google.firebase.storage.CancellableTask, com.google.android.gms.tasks.Task
    public boolean isCanceled() {
        return getInternalState() == 256;
    }

    @Override // com.google.android.gms.tasks.Task
    public boolean isComplete() {
        return (getInternalState() & STATES_COMPLETE) != 0;
    }

    @Override // com.google.firebase.storage.CancellableTask
    public boolean isInProgress() {
        return (getInternalState() & STATES_INPROGRESS) != 0;
    }

    @Override // com.google.firebase.storage.ControllableTask
    public boolean isPaused() {
        return (getInternalState() & 16) != 0;
    }

    @Override // com.google.android.gms.tasks.Task
    public boolean isSuccessful() {
        return (getInternalState() & 128) != 0;
    }

    public void onCanceled() {
    }

    public void onFailure() {
    }

    public void onPaused() {
    }

    public void onProgress() {
    }

    public void onQueued() {
    }

    public void onSuccess() {
    }

    @Override // com.google.android.gms.tasks.Task
    @NonNull
    public <ContinuationResultT> Task<ContinuationResultT> onSuccessTask(@NonNull SuccessContinuation<ResultT, ContinuationResultT> successContinuation) {
        return successTaskImpl(null, successContinuation);
    }

    @Override // com.google.firebase.storage.ControllableTask
    public boolean pause() {
        return tryChangeState(new int[]{16, 8}, true);
    }

    @VisibleForTesting
    public boolean queue() {
        if (!tryChangeState(2, false)) {
            return false;
        }
        schedule();
        return true;
    }

    @NonNull
    public StorageTask<ResultT> removeOnCanceledListener(@NonNull OnCanceledListener onCanceledListener) {
        Preconditions.checkNotNull(onCanceledListener);
        this.cancelManager.a(onCanceledListener);
        return this;
    }

    @NonNull
    public StorageTask<ResultT> removeOnCompleteListener(@NonNull OnCompleteListener<ResultT> onCompleteListener) {
        Preconditions.checkNotNull(onCompleteListener);
        this.completeListener.a(onCompleteListener);
        return this;
    }

    @NonNull
    public StorageTask<ResultT> removeOnFailureListener(@NonNull OnFailureListener onFailureListener) {
        Preconditions.checkNotNull(onFailureListener);
        this.failureManager.a(onFailureListener);
        return this;
    }

    @NonNull
    public StorageTask<ResultT> removeOnPausedListener(@NonNull OnPausedListener<? super ResultT> onPausedListener) {
        Preconditions.checkNotNull(onPausedListener);
        this.pausedManager.a(onPausedListener);
        return this;
    }

    @NonNull
    public StorageTask<ResultT> removeOnProgressListener(@NonNull OnProgressListener<? super ResultT> onProgressListener) {
        Preconditions.checkNotNull(onProgressListener);
        this.progressManager.a(onProgressListener);
        return this;
    }

    @NonNull
    public StorageTask<ResultT> removeOnSuccessListener(@NonNull OnSuccessListener<? super ResultT> onSuccessListener) {
        Preconditions.checkNotNull(onSuccessListener);
        this.successManager.a(onSuccessListener);
        return this;
    }

    @VisibleForTesting
    public void resetState() {
    }

    @Override // com.google.firebase.storage.ControllableTask
    public boolean resume() {
        if (!tryChangeState(2, true)) {
            return false;
        }
        resetState();
        schedule();
        return true;
    }

    @VisibleForTesting
    public abstract void run();

    @VisibleForTesting
    public abstract void schedule();

    @NonNull
    @VisibleForTesting
    public ResultT snapState() {
        ResultT resultt;
        synchronized (this.syncObject) {
            resultt = (ResultT) snapStateImpl();
        }
        return resultt;
    }

    @NonNull
    @VisibleForTesting
    public abstract ResultT snapStateImpl();

    @VisibleForTesting
    public boolean tryChangeState(int[] iArr, boolean z) {
        HashMap<Integer, HashSet<Integer>> map = z ? ValidUserInitiatedStateChanges : ValidTaskInitiatedStateChanges;
        synchronized (this.syncObject) {
            for (int i : iArr) {
                HashSet<Integer> hashSet = map.get(Integer.valueOf(getInternalState()));
                if (hashSet != null && hashSet.contains(Integer.valueOf(i))) {
                    this.currentState = i;
                    int i2 = this.currentState;
                    if (i2 == 2) {
                        StorageTaskManager._instance.ensureRegistered(this);
                        onQueued();
                    } else if (i2 == 4) {
                        onProgress();
                    } else if (i2 == 16) {
                        onPaused();
                    } else if (i2 == 64) {
                        onFailure();
                    } else if (i2 == 128) {
                        onSuccess();
                    } else if (i2 == 256) {
                        onCanceled();
                    }
                    this.successManager.onInternalStateChanged();
                    this.failureManager.onInternalStateChanged();
                    this.cancelManager.onInternalStateChanged();
                    this.completeListener.onInternalStateChanged();
                    this.pausedManager.onInternalStateChanged();
                    this.progressManager.onInternalStateChanged();
                    if (Log.isLoggable(TAG, 3)) {
                        Log.d(TAG, "changed internal state to: " + getStateString(i) + " isUser: " + z + " from state:" + getStateString(this.currentState));
                    }
                    return true;
                }
            }
            Log.w(TAG, "unable to change internal state to: " + getStateString(iArr) + " isUser: " + z + " from state:" + getStateString(this.currentState));
            return false;
        }
    }

    @Override // com.google.android.gms.tasks.Task
    @NonNull
    public <ContinuationResultT> Task<ContinuationResultT> continueWith(@NonNull Executor executor, @NonNull Continuation<ResultT, ContinuationResultT> continuation) {
        return continueWithImpl(executor, continuation);
    }

    @Override // com.google.android.gms.tasks.Task
    @NonNull
    public <ContinuationResultT> Task<ContinuationResultT> continueWithTask(@NonNull Executor executor, @NonNull Continuation<ResultT, Task<ContinuationResultT>> continuation) {
        return continueWithTaskImpl(executor, continuation);
    }

    @Override // com.google.android.gms.tasks.Task
    @NonNull
    public <ContinuationResultT> Task<ContinuationResultT> onSuccessTask(@NonNull Executor executor, @NonNull SuccessContinuation<ResultT, ContinuationResultT> successContinuation) {
        return successTaskImpl(executor, successContinuation);
    }

    @Override // com.google.android.gms.tasks.Task
    @NonNull
    public ResultT getResult() {
        if (getFinalResult() != null) {
            Exception error = getFinalResult().getError();
            if (error == null) {
                return (ResultT) getFinalResult();
            }
            throw new RuntimeExecutionException(error);
        }
        throw new IllegalStateException();
    }

    public /* synthetic */ void a(OnFailureListener onFailureListener, ProvideError provideError) {
        StorageTaskManager._instance.unRegister(this);
        onFailureListener.onFailure(provideError.getError());
    }

    @Override // com.google.android.gms.tasks.Task
    @NonNull
    public StorageTask<ResultT> addOnCanceledListener(@NonNull OnCanceledListener onCanceledListener) {
        Preconditions.checkNotNull(onCanceledListener);
        this.cancelManager.addListener(null, null, onCanceledListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    @NonNull
    public StorageTask<ResultT> addOnCompleteListener(@NonNull OnCompleteListener<ResultT> onCompleteListener) {
        Preconditions.checkNotNull(onCompleteListener);
        this.completeListener.addListener(null, null, onCompleteListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    @NonNull
    public StorageTask<ResultT> addOnFailureListener(@NonNull OnFailureListener onFailureListener) {
        Preconditions.checkNotNull(onFailureListener);
        this.failureManager.addListener(null, null, onFailureListener);
        return this;
    }

    @Override // com.google.firebase.storage.ControllableTask
    @NonNull
    public StorageTask<ResultT> addOnPausedListener(@NonNull OnPausedListener<? super ResultT> onPausedListener) {
        Preconditions.checkNotNull(onPausedListener);
        this.pausedManager.addListener(null, null, onPausedListener);
        return this;
    }

    @Override // com.google.firebase.storage.CancellableTask
    @NonNull
    public StorageTask<ResultT> addOnProgressListener(@NonNull OnProgressListener<? super ResultT> onProgressListener) {
        Preconditions.checkNotNull(onProgressListener);
        this.progressManager.addListener(null, null, onProgressListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    @NonNull
    public StorageTask<ResultT> addOnSuccessListener(@NonNull OnSuccessListener<? super ResultT> onSuccessListener) {
        Preconditions.checkNotNull(onSuccessListener);
        this.successManager.addListener(null, null, onSuccessListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    @NonNull
    public StorageTask<ResultT> addOnCanceledListener(@NonNull Executor executor, @NonNull OnCanceledListener onCanceledListener) {
        Preconditions.checkNotNull(onCanceledListener);
        Preconditions.checkNotNull(executor);
        this.cancelManager.addListener(null, executor, onCanceledListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    @NonNull
    public StorageTask<ResultT> addOnCompleteListener(@NonNull Executor executor, @NonNull OnCompleteListener<ResultT> onCompleteListener) {
        Preconditions.checkNotNull(onCompleteListener);
        Preconditions.checkNotNull(executor);
        this.completeListener.addListener(null, executor, onCompleteListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    @NonNull
    public StorageTask<ResultT> addOnFailureListener(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
        Preconditions.checkNotNull(onFailureListener);
        Preconditions.checkNotNull(executor);
        this.failureManager.addListener(null, executor, onFailureListener);
        return this;
    }

    @Override // com.google.firebase.storage.ControllableTask
    @NonNull
    public StorageTask<ResultT> addOnPausedListener(@NonNull Executor executor, @NonNull OnPausedListener<? super ResultT> onPausedListener) {
        Preconditions.checkNotNull(onPausedListener);
        Preconditions.checkNotNull(executor);
        this.pausedManager.addListener(null, executor, onPausedListener);
        return this;
    }

    @Override // com.google.firebase.storage.CancellableTask
    @NonNull
    public StorageTask<ResultT> addOnProgressListener(@NonNull Executor executor, @NonNull OnProgressListener<? super ResultT> onProgressListener) {
        Preconditions.checkNotNull(onProgressListener);
        Preconditions.checkNotNull(executor);
        this.progressManager.addListener(null, executor, onProgressListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    @NonNull
    public StorageTask<ResultT> addOnSuccessListener(@NonNull Executor executor, @NonNull OnSuccessListener<? super ResultT> onSuccessListener) {
        Preconditions.checkNotNull(executor);
        Preconditions.checkNotNull(onSuccessListener);
        this.successManager.addListener(null, executor, onSuccessListener);
        return this;
    }

    public /* synthetic */ void a(OnCompleteListener onCompleteListener, ProvideError provideError) {
        StorageTaskManager._instance.unRegister(this);
        onCompleteListener.onComplete(this);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X extends java.lang.Throwable */
    @Override // com.google.android.gms.tasks.Task
    @NonNull
    public <X extends Throwable> ResultT getResult(@NonNull Class<X> cls) throws X {
        if (getFinalResult() != null) {
            if (!cls.isInstance(getFinalResult().getError())) {
                Exception error = getFinalResult().getError();
                if (error == null) {
                    return (ResultT) getFinalResult();
                }
                throw new RuntimeExecutionException(error);
            }
            throw cls.cast(getFinalResult().getError());
        }
        throw new IllegalStateException();
    }

    @Override // com.google.android.gms.tasks.Task
    @NonNull
    public StorageTask<ResultT> addOnCanceledListener(@NonNull Activity activity, @NonNull OnCanceledListener onCanceledListener) {
        Preconditions.checkNotNull(onCanceledListener);
        Preconditions.checkNotNull(activity);
        this.cancelManager.addListener(activity, null, onCanceledListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    @NonNull
    public StorageTask<ResultT> addOnCompleteListener(@NonNull Activity activity, @NonNull OnCompleteListener<ResultT> onCompleteListener) {
        Preconditions.checkNotNull(onCompleteListener);
        Preconditions.checkNotNull(activity);
        this.completeListener.addListener(activity, null, onCompleteListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    @NonNull
    public StorageTask<ResultT> addOnFailureListener(@NonNull Activity activity, @NonNull OnFailureListener onFailureListener) {
        Preconditions.checkNotNull(onFailureListener);
        Preconditions.checkNotNull(activity);
        this.failureManager.addListener(activity, null, onFailureListener);
        return this;
    }

    @Override // com.google.firebase.storage.ControllableTask
    @NonNull
    public StorageTask<ResultT> addOnPausedListener(@NonNull Activity activity, @NonNull OnPausedListener<? super ResultT> onPausedListener) {
        Preconditions.checkNotNull(onPausedListener);
        Preconditions.checkNotNull(activity);
        this.pausedManager.addListener(activity, null, onPausedListener);
        return this;
    }

    @Override // com.google.firebase.storage.CancellableTask
    @NonNull
    public StorageTask<ResultT> addOnProgressListener(@NonNull Activity activity, @NonNull OnProgressListener<? super ResultT> onProgressListener) {
        Preconditions.checkNotNull(onProgressListener);
        Preconditions.checkNotNull(activity);
        this.progressManager.addListener(activity, null, onProgressListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    @NonNull
    public StorageTask<ResultT> addOnSuccessListener(@NonNull Activity activity, @NonNull OnSuccessListener<? super ResultT> onSuccessListener) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(onSuccessListener);
        this.successManager.addListener(activity, null, onSuccessListener);
        return this;
    }

    public /* synthetic */ void a(OnCanceledListener onCanceledListener, ProvideError provideError) {
        StorageTaskManager._instance.unRegister(this);
        onCanceledListener.onCanceled();
    }

    public /* synthetic */ void a(Continuation continuation, TaskCompletionSource taskCompletionSource, Task task) {
        try {
            Object objThen = continuation.then(this);
            if (taskCompletionSource.getTask().isComplete()) {
                return;
            }
            taskCompletionSource.setResult(objThen);
        } catch (RuntimeExecutionException e2) {
            if (e2.getCause() instanceof Exception) {
                taskCompletionSource.setException((Exception) e2.getCause());
            } else {
                taskCompletionSource.setException(e2);
            }
        } catch (Exception e3) {
            taskCompletionSource.setException(e3);
        }
    }

    public /* synthetic */ void a(Continuation continuation, TaskCompletionSource taskCompletionSource, CancellationTokenSource cancellationTokenSource, Task task) {
        try {
            Task task2 = (Task) continuation.then(this);
            if (taskCompletionSource.getTask().isComplete()) {
                return;
            }
            if (task2 == null) {
                taskCompletionSource.setException(new NullPointerException("Continuation returned null"));
                return;
            }
            Objects.requireNonNull(taskCompletionSource);
            task2.addOnSuccessListener(new o(taskCompletionSource));
            Objects.requireNonNull(taskCompletionSource);
            task2.addOnFailureListener(new q(taskCompletionSource));
            Objects.requireNonNull(cancellationTokenSource);
            task2.addOnCanceledListener(new n(cancellationTokenSource));
        } catch (RuntimeExecutionException e2) {
            if (e2.getCause() instanceof Exception) {
                taskCompletionSource.setException((Exception) e2.getCause());
            } else {
                taskCompletionSource.setException(e2);
            }
        } catch (Exception e3) {
            taskCompletionSource.setException(e3);
        }
    }

    public static /* synthetic */ void a(SuccessContinuation successContinuation, TaskCompletionSource taskCompletionSource, CancellationTokenSource cancellationTokenSource, ProvideError provideError) {
        try {
            Task taskThen = successContinuation.then(provideError);
            Objects.requireNonNull(taskCompletionSource);
            taskThen.addOnSuccessListener(new o(taskCompletionSource));
            Objects.requireNonNull(taskCompletionSource);
            taskThen.addOnFailureListener(new q(taskCompletionSource));
            Objects.requireNonNull(cancellationTokenSource);
            taskThen.addOnCanceledListener(new n(cancellationTokenSource));
        } catch (RuntimeExecutionException e2) {
            if (e2.getCause() instanceof Exception) {
                taskCompletionSource.setException((Exception) e2.getCause());
            } else {
                taskCompletionSource.setException(e2);
            }
        } catch (Exception e3) {
            taskCompletionSource.setException(e3);
        }
    }

    @VisibleForTesting
    public boolean tryChangeState(int i, boolean z) {
        return tryChangeState(new int[]{i}, z);
    }

    public /* synthetic */ void a() {
        try {
            run();
        } finally {
            ensureFinalState();
        }
    }
}
