package com.lagradost.cloudstream3;

import android.os.Build;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.lagradost.cloudstream3.plugins.PluginManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CloudStreamApp.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0012"}, d2 = {"Lcom/lagradost/cloudstream3/ExceptionHandler;", "Ljava/lang/Thread$UncaughtExceptionHandler;", "errorFile", "Ljava/io/File;", "onError", "Lkotlin/Function0;", "", "<init>", "(Ljava/io/File;Lkotlin/jvm/functions/Function0;)V", "getErrorFile", "()Ljava/io/File;", "getOnError", "()Lkotlin/jvm/functions/Function0;", "uncaughtException", "thread", "Ljava/lang/Thread;", MediaRouteProviderProtocol.SERVICE_DATA_ERROR, "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    private final File errorFile;
    private final Function0<Unit> onError;

    public ExceptionHandler(File errorFile, Function0<Unit> onError) {
        Intrinsics.checkNotNullParameter(errorFile, "errorFile");
        Intrinsics.checkNotNullParameter(onError, "onError");
        this.errorFile = errorFile;
        this.onError = onError;
    }

    public final File getErrorFile() {
        return this.errorFile;
    }

    public final Function0<Unit> getOnError() {
        return this.onError;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable error) {
        long id;
        Intrinsics.checkNotNullParameter(thread, "thread");
        Intrinsics.checkNotNullParameter(error, "error");
        try {
            if (Build.VERSION.SDK_INT >= 36) {
                id = thread.threadId();
            } else {
                id = thread.getId();
            }
            PrintStream printStream = new PrintStream(this.errorFile);
            try {
                PrintStream printStream2 = printStream;
                StringBuilder sb = new StringBuilder("Currently loading extension: ");
                String currentlyLoading = PluginManager.INSTANCE.getCurrentlyLoading();
                if (currentlyLoading == null) {
                    currentlyLoading = "none";
                }
                sb.append(currentlyLoading);
                printStream2.println(sb.toString());
                printStream2.println("Fatal exception on thread " + thread.getName() + " (" + id + ')');
                error.printStackTrace(printStream2);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(printStream, null);
                try {
                    this.onError.invoke();
                } catch (Exception unused) {
                }
                System.exit(1);
                throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(printStream, th);
                    throw th2;
                }
            }
        } catch (FileNotFoundException unused2) {
        }
    }
}
