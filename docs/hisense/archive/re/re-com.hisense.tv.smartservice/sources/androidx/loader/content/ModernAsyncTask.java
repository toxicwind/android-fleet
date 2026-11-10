package androidx.loader.content;

import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import androidx.annotation.RestrictTo;
import b.a.a.a.a;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public abstract class ModernAsyncTask<Params, Progress, Result> {
    public static final int CORE_POOL_SIZE = 5;
    public static final int KEEP_ALIVE = 1;
    public static final String LOG_TAG = "AsyncTask";
    public static final int MAXIMUM_POOL_SIZE = 128;
    public static final int MESSAGE_POST_PROGRESS = 2;
    public static final int MESSAGE_POST_RESULT = 1;
    public static InternalHandler sHandler;
    public static final ThreadFactory sThreadFactory = new ThreadFactory() { // from class: androidx.loader.content.ModernAsyncTask.1
        public final AtomicInteger mCount = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            StringBuilder sbA = a.a("ModernAsyncTask #");
            sbA.append(this.mCount.getAndIncrement());
            return new Thread(runnable, sbA.toString());
        }
    };
    public static final BlockingQueue<Runnable> sPoolWorkQueue = new LinkedBlockingQueue(10);
    public static final Executor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(5, 128, 1, TimeUnit.SECONDS, sPoolWorkQueue, sThreadFactory);
    public static volatile Executor sDefaultExecutor = THREAD_POOL_EXECUTOR;
    public volatile Status mStatus = Status.PENDING;
    public final AtomicBoolean mCancelled = new AtomicBoolean();
    public final AtomicBoolean mTaskInvoked = new AtomicBoolean();
    public final WorkerRunnable<Params, Result> mWorker = new WorkerRunnable<Params, Result>() { // from class: androidx.loader.content.ModernAsyncTask.2
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // java.util.concurrent.Callable
        public Result call() {
            ModernAsyncTask.this.mTaskInvoked.set(true);
            Result result = null;
            try {
                try {
                    Process.setThreadPriority(10);
                    result = (Result) ModernAsyncTask.this.doInBackground(this.mParams);
                    Binder.flushPendingCommands();
                    ModernAsyncTask.this.postResult(result);
                    return result;
                } catch (Throwable th) {
                    ModernAsyncTask.this.mCancelled.set(true);
                    throw th;
                }
            } catch (Throwable th2) {
                ModernAsyncTask.this.postResult(result);
                throw th2;
            }
        }
    };
    public final FutureTask<Result> mFuture = new FutureTask<Result>(this.mWorker) { // from class: androidx.loader.content.ModernAsyncTask.3
        @Override // java.util.concurrent.FutureTask
        public void done() {
            try {
                ModernAsyncTask.this.postResultIfNotInvoked(get());
            } catch (InterruptedException e2) {
                Log.w(ModernAsyncTask.LOG_TAG, e2);
            } catch (CancellationException unused) {
                ModernAsyncTask.this.postResultIfNotInvoked(null);
            } catch (ExecutionException e3) {
                throw new RuntimeException("An error occurred while executing doInBackground()", e3.getCause());
            } catch (Throwable th) {
                throw new RuntimeException("An error occurred while executing doInBackground()", th);
            }
        }
    };

    /* JADX INFO: renamed from: androidx.loader.content.ModernAsyncTask$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        public static final /* synthetic */ int[] $SwitchMap$androidx$loader$content$ModernAsyncTask$Status = new int[Status.values().length];

        static {
            try {
                $SwitchMap$androidx$loader$content$ModernAsyncTask$Status[Status.RUNNING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$loader$content$ModernAsyncTask$Status[Status.FINISHED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private static class AsyncTaskResult<Data> {
        public final Data[] mData;
        public final ModernAsyncTask mTask;

        public AsyncTaskResult(ModernAsyncTask modernAsyncTask, Data... dataArr) {
            this.mTask = modernAsyncTask;
            this.mData = dataArr;
        }
    }

    private static class InternalHandler extends Handler {
        public InternalHandler() {
            super(Looper.getMainLooper());
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            AsyncTaskResult asyncTaskResult = (AsyncTaskResult) message.obj;
            int i = message.what;
            if (i == 1) {
                asyncTaskResult.mTask.finish(asyncTaskResult.mData[0]);
            } else {
                if (i != 2) {
                    return;
                }
                asyncTaskResult.mTask.onProgressUpdate(asyncTaskResult.mData);
            }
        }
    }

    public enum Status {
        PENDING,
        RUNNING,
        FINISHED
    }

    private static abstract class WorkerRunnable<Params, Result> implements Callable<Result> {
        public Params[] mParams;
    }

    public static Handler getHandler() {
        InternalHandler internalHandler;
        synchronized (ModernAsyncTask.class) {
            if (sHandler == null) {
                sHandler = new InternalHandler();
            }
            internalHandler = sHandler;
        }
        return internalHandler;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void setDefaultExecutor(Executor executor) {
        sDefaultExecutor = executor;
    }

    public final boolean cancel(boolean z) {
        this.mCancelled.set(true);
        return this.mFuture.cancel(z);
    }

    public abstract Result doInBackground(Params... paramsArr);

    public final ModernAsyncTask<Params, Progress, Result> execute(Params... paramsArr) {
        return executeOnExecutor(sDefaultExecutor, paramsArr);
    }

    public final ModernAsyncTask<Params, Progress, Result> executeOnExecutor(Executor executor, Params... paramsArr) {
        if (this.mStatus == Status.PENDING) {
            this.mStatus = Status.RUNNING;
            onPreExecute();
            this.mWorker.mParams = paramsArr;
            executor.execute(this.mFuture);
            return this;
        }
        int iOrdinal = this.mStatus.ordinal();
        if (iOrdinal == 1) {
            throw new IllegalStateException("Cannot execute task: the task is already running.");
        }
        if (iOrdinal != 2) {
            throw new IllegalStateException("We should never reach this state");
        }
        throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
    }

    public void finish(Result result) {
        if (isCancelled()) {
            onCancelled(result);
        } else {
            onPostExecute(result);
        }
        this.mStatus = Status.FINISHED;
    }

    public final Result get() {
        return this.mFuture.get();
    }

    public final Status getStatus() {
        return this.mStatus;
    }

    public final boolean isCancelled() {
        return this.mCancelled.get();
    }

    public void onCancelled() {
    }

    public void onCancelled(Result result) {
        onCancelled();
    }

    public void onPostExecute(Result result) {
    }

    public void onPreExecute() {
    }

    public void onProgressUpdate(Progress... progressArr) {
    }

    public Result postResult(Result result) {
        getHandler().obtainMessage(1, new AsyncTaskResult(this, result)).sendToTarget();
        return result;
    }

    public void postResultIfNotInvoked(Result result) {
        if (this.mTaskInvoked.get()) {
            return;
        }
        postResult(result);
    }

    public final void publishProgress(Progress... progressArr) {
        if (isCancelled()) {
            return;
        }
        getHandler().obtainMessage(2, new AsyncTaskResult(this, progressArr)).sendToTarget();
    }

    public static void execute(Runnable runnable) {
        sDefaultExecutor.execute(runnable);
    }

    public final Result get(long j, TimeUnit timeUnit) {
        return this.mFuture.get(j, timeUnit);
    }
}
