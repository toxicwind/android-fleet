package com.google.firebase.firestore.core;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.remote.Datastore;
import com.google.firebase.firestore.remote.RemoteStore;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.ExponentialBackoff;
import com.google.firebase.firestore.util.Function;

/* JADX INFO: loaded from: classes.dex */
public class TransactionRunner<TResult> {
    public static final int DEFAULT_MAX_ATTEMPTS_COUNT = 5;
    public AsyncQueue asyncQueue;
    public ExponentialBackoff backoff;
    public RemoteStore remoteStore;
    public Function<Transaction, Task<TResult>> updateFunction;
    public TaskCompletionSource<TResult> taskSource = new TaskCompletionSource<>();
    public int attemptsRemaining = 5;

    public TransactionRunner(AsyncQueue asyncQueue, RemoteStore remoteStore, Function<Transaction, Task<TResult>> function) {
        this.asyncQueue = asyncQueue;
        this.remoteStore = remoteStore;
        this.updateFunction = function;
        this.backoff = new ExponentialBackoff(asyncQueue, AsyncQueue.TimerId.RETRY_TRANSACTION);
    }

    private void handleTransactionError(Task task) {
        if (this.attemptsRemaining <= 0 || !isRetryableTransactionError(task.getException())) {
            this.taskSource.setException(task.getException());
        } else {
            runWithBackoff();
        }
    }

    public static boolean isRetryableTransactionError(Exception exc) {
        if (!(exc instanceof FirebaseFirestoreException)) {
            return false;
        }
        FirebaseFirestoreException firebaseFirestoreException = (FirebaseFirestoreException) exc;
        FirebaseFirestoreException.Code code = firebaseFirestoreException.getCode();
        return code == FirebaseFirestoreException.Code.ABORTED || code == FirebaseFirestoreException.Code.FAILED_PRECONDITION || !Datastore.isPermanentError(firebaseFirestoreException.getCode());
    }

    private void runWithBackoff() {
        this.attemptsRemaining--;
        this.backoff.backoffAndRun(new Runnable() { // from class: b.e.b.d.b.B
            @Override // java.lang.Runnable
            public final void run() {
                this.f266a.a();
            }
        });
    }

    public /* synthetic */ void a() {
        final Transaction transactionCreateTransaction = this.remoteStore.createTransaction();
        this.updateFunction.apply(transactionCreateTransaction).addOnCompleteListener(this.asyncQueue.getExecutor(), new OnCompleteListener() { // from class: b.e.b.d.b.C
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.f267a.a(transactionCreateTransaction, task);
            }
        });
    }

    public Task<TResult> run() {
        runWithBackoff();
        return this.taskSource.getTask();
    }

    public /* synthetic */ void a(Transaction transaction, final Task task) {
        if (!task.isSuccessful()) {
            handleTransactionError(task);
        } else {
            transaction.commit().addOnCompleteListener(this.asyncQueue.getExecutor(), new OnCompleteListener() { // from class: b.e.b.d.b.A
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task2) {
                    this.f264a.a(task, task2);
                }
            });
        }
    }

    public /* synthetic */ void a(Task task, Task task2) {
        if (task2.isSuccessful()) {
            this.taskSource.setResult((TResult) task.getResult());
        } else {
            handleTransactionError(task2);
        }
    }
}
