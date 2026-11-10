package androidx.core.provider;

import android.os.Handler;
import android.os.Process;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.core.util.Consumer;
import b.a.a.a.a;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.ju.lib.datacommunication.network.http.dns.Tools;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
public class RequestExecutor {

    private static class DefaultThreadFactory implements ThreadFactory {
        public int mPriority;
        public String mThreadName;

        private static class ProcessPriorityThread extends Thread {
            public final int mPriority;

            public ProcessPriorityThread(Runnable target, String name, int priority) {
                super(target, name);
                this.mPriority = priority;
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Process.setThreadPriority(this.mPriority);
                super.run();
            }
        }

        public DefaultThreadFactory(@NonNull String threadName, int priority) {
            this.mThreadName = threadName;
            this.mPriority = priority;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new ProcessPriorityThread(runnable, this.mThreadName, this.mPriority);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class HandlerExecutor implements Executor {
        public final Handler mHandler;

        public HandlerExecutor(@NonNull Handler handler) {
            if (handler == null) {
                throw new NullPointerException();
            }
            this.mHandler = handler;
        }

        @Override // java.util.concurrent.Executor
        public void execute(@NonNull Runnable command) {
            Handler handler = this.mHandler;
            if (command == null) {
                throw new NullPointerException();
            }
            if (!handler.post(command)) {
                throw new RejectedExecutionException(a.a(new StringBuilder(), this.mHandler, " is shutting down"));
            }
        }
    }

    private static class ReplyRunnable<T> implements Runnable {

        @NonNull
        public Callable<T> mCallable;

        @NonNull
        public Consumer<T> mConsumer;

        @NonNull
        public Handler mHandler;

        public ReplyRunnable(@NonNull Handler handler, @NonNull Callable<T> callable, @NonNull Consumer<T> consumer) {
            this.mCallable = callable;
            this.mConsumer = consumer;
            this.mHandler = handler;
        }

        @Override // java.lang.Runnable
        public void run() {
            final T tCall;
            try {
                tCall = this.mCallable.call();
            } catch (Exception unused) {
                tCall = null;
            }
            final Consumer<T> consumer = this.mConsumer;
            this.mHandler.post(new Runnable() { // from class: androidx.core.provider.RequestExecutor.ReplyRunnable.1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.lang.Runnable
                public void run() {
                    consumer.accept(tCall);
                }
            });
        }
    }

    public static ThreadPoolExecutor createDefaultExecutor(@NonNull String threadName, int threadPriority, @IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG) int keepAliveTimeInMillis) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, keepAliveTimeInMillis, TimeUnit.MILLISECONDS, new LinkedBlockingDeque(), new DefaultThreadFactory(threadName, threadPriority));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return threadPoolExecutor;
    }

    public static Executor createHandlerExecutor(@NonNull Handler handler) {
        return new HandlerExecutor(handler);
    }

    public static <T> void execute(@NonNull Executor executor, @NonNull Callable<T> callable, @NonNull Consumer<T> consumer) {
        executor.execute(new ReplyRunnable(CalleeHandler.create(), callable, consumer));
    }

    public static <T> T submit(@NonNull ExecutorService executor, @NonNull final Callable<T> callable, @IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG) int timeoutMillis) throws InterruptedException {
        try {
            return executor.submit(callable).get(timeoutMillis, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e2) {
            throw e2;
        } catch (ExecutionException e3) {
            throw new RuntimeException(e3);
        } catch (TimeoutException unused) {
            throw new InterruptedException(Tools.KEY_TIMEOUT);
        }
    }
}
