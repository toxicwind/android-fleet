package androidx.media3.session;

import android.os.Handler;
import android.os.Looper;
import androidx.media3.common.util.Util;
import androidx.media3.session.MediaController;
import com.google.common.util.concurrent.AbstractFuture;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes4.dex */
class MediaControllerHolder<T extends MediaController> extends AbstractFuture<T> implements MediaController.ConnectionCallback {
    private boolean accepted;
    private T controller;
    private final Handler handler;

    public MediaControllerHolder(Looper looper) {
        this.handler = new Handler(looper);
    }

    public void setController(final T t) {
        this.controller = t;
        maybeSetFutureResult();
        addListener(new Runnable() { // from class: androidx.media3.session.MediaControllerHolder$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m7646x80bd5aa4(t);
            }
        }, new Executor() { // from class: androidx.media3.session.MediaControllerHolder$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                this.f$0.m7647xf63780e5(runnable);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$setController$0$androidx-media3-session-MediaControllerHolder, reason: not valid java name */
    /* synthetic */ void m7646x80bd5aa4(MediaController mediaController) {
        if (isCancelled()) {
            mediaController.release();
        }
    }

    /* JADX INFO: renamed from: lambda$setController$1$androidx-media3-session-MediaControllerHolder, reason: not valid java name */
    /* synthetic */ void m7647xf63780e5(Runnable runnable) {
        Util.postOrRun(this.handler, runnable);
    }

    @Override // androidx.media3.session.MediaController.ConnectionCallback
    public void onAccepted() {
        this.accepted = true;
        maybeSetFutureResult();
    }

    @Override // androidx.media3.session.MediaController.ConnectionCallback
    public void onRejected() {
        maybeSetException();
    }

    private void maybeSetFutureResult() {
        T t = this.controller;
        if (t == null || !this.accepted) {
            return;
        }
        set(t);
    }

    private void maybeSetException() {
        setException(new SecurityException("Session rejected the connection request."));
    }
}
