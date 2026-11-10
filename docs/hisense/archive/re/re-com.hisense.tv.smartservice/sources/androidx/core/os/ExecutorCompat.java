package androidx.core.os;

import android.os.Handler;
import androidx.annotation.NonNull;
import b.a.a.a.a;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: loaded from: classes.dex */
public final class ExecutorCompat {

    /* JADX INFO: Access modifiers changed from: private */
    public static class HandlerExecutor implements Executor {
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

    @NonNull
    public static Executor create(@NonNull Handler handler) {
        return new HandlerExecutor(handler);
    }
}
