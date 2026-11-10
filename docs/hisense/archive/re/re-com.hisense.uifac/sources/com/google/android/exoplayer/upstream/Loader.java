package com.google.android.exoplayer.upstream;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.TraceUtil;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes.dex */
public final class Loader {
    private LoadTask currentTask;
    private final ExecutorService downloadExecutorService;
    private boolean loading;

    public interface Callback {
        void onLoadCanceled(Loadable loadable);

        void onLoadCompleted(Loadable loadable);

        void onLoadError(Loadable loadable, IOException iOException);
    }

    public interface Loadable {
        void cancelLoad();

        boolean isLoadCanceled();

        void load() throws InterruptedException, IOException;
    }

    public static final class UnexpectedLoaderException extends IOException {
        public UnexpectedLoaderException(Exception exc) {
            super("Unexpected " + exc.getClass().getSimpleName() + ": " + exc.getMessage(), exc);
        }
    }

    public Loader(String str) {
        this.downloadExecutorService = Util.newSingleThreadExecutor(str);
    }

    public void startLoading(Loadable loadable, Callback callback) {
        Looper looperMyLooper = Looper.myLooper();
        Assertions.checkState(looperMyLooper != null);
        startLoading(looperMyLooper, loadable, callback);
    }

    public void startLoading(Looper looper, Loadable loadable, Callback callback) {
        Assertions.checkState(!this.loading);
        this.loading = true;
        LoadTask loadTask = new LoadTask(looper, loadable, callback);
        this.currentTask = loadTask;
        this.downloadExecutorService.submit(loadTask);
    }

    public boolean isLoading() {
        return this.loading;
    }

    public void cancelLoading() {
        Assertions.checkState(this.loading);
        this.currentTask.quit();
    }

    public void release() {
        release(null);
    }

    public void release(Runnable runnable) {
        if (this.loading) {
            cancelLoading();
        }
        if (runnable != null) {
            this.downloadExecutorService.submit(runnable);
        }
        this.downloadExecutorService.shutdown();
    }

    @SuppressLint({"HandlerLeak"})
    private final class LoadTask extends Handler implements Runnable {
        private final Callback callback;
        private volatile Thread executorThread;
        private final Loadable loadable;

        public LoadTask(Looper looper, Loadable loadable, Callback callback) {
            super(looper);
            this.loadable = loadable;
            this.callback = callback;
        }

        public void quit() {
            this.loadable.cancelLoad();
            if (this.executorThread != null) {
                this.executorThread.interrupt();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.executorThread = Thread.currentThread();
                if (!this.loadable.isLoadCanceled()) {
                    TraceUtil.beginSection(this.loadable.getClass().getSimpleName() + ".load()");
                    this.loadable.load();
                    TraceUtil.endSection();
                }
                sendEmptyMessage(0);
            } catch (IOException e) {
                obtainMessage(1, e).sendToTarget();
            } catch (Error e2) {
                Log.e("LoadTask", "Unexpected error loading stream", e2);
                obtainMessage(2, e2).sendToTarget();
                throw e2;
            } catch (InterruptedException unused) {
                Assertions.checkState(this.loadable.isLoadCanceled());
                sendEmptyMessage(0);
            } catch (Exception e3) {
                Log.e("LoadTask", "Unexpected exception loading stream", e3);
                obtainMessage(1, new UnexpectedLoaderException(e3)).sendToTarget();
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 2) {
                throw ((Error) message.obj);
            }
            onFinished();
            if (this.loadable.isLoadCanceled()) {
                this.callback.onLoadCanceled(this.loadable);
                return;
            }
            int i = message.what;
            if (i == 0) {
                this.callback.onLoadCompleted(this.loadable);
            } else {
                if (i != 1) {
                    return;
                }
                this.callback.onLoadError(this.loadable, (IOException) message.obj);
            }
        }

        private void onFinished() {
            Loader.this.loading = false;
            Loader.this.currentTask = null;
        }
    }
}
