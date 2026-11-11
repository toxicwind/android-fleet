package androidx.media3.session;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import androidx.media3.common.util.BackgroundExecutor;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes4.dex */
final class AndroidAutoConnectionStateObserver {
    private static final String BROADCAST_INTENT = "androidx.car.app.connection.action.CAR_CONNECTION_UPDATED";
    private static final String QUERY_COLUMN = "CarConnectionState";
    private static final Uri QUERY_URI = Uri.parse("content://androidx.car.app.connection");
    private final Executor backgroundExecutor;
    private final AndroidAutoChangeReceiver changeReceiver;
    private final Context context;
    private final AtomicBoolean isConnected;
    private final AtomicBoolean isReleased;
    private final Runnable listener;

    public AndroidAutoConnectionStateObserver(Context context, Runnable runnable) {
        this.context = context.getApplicationContext();
        this.listener = runnable;
        Executor executor = BackgroundExecutor.get();
        this.backgroundExecutor = executor;
        this.changeReceiver = new AndroidAutoChangeReceiver();
        this.isConnected = new AtomicBoolean();
        this.isReleased = new AtomicBoolean();
        executor.execute(new Runnable() { // from class: androidx.media3.session.AndroidAutoConnectionStateObserver$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m7634x28baf9f1();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$new$0$androidx-media3-session-AndroidAutoConnectionStateObserver, reason: not valid java name */
    /* synthetic */ void m7634x28baf9f1() {
        IntentFilter intentFilter = new IntentFilter(BROADCAST_INTENT);
        int i = Build.VERSION.SDK_INT;
        Context context = this.context;
        if (i >= 33) {
            context.registerReceiver(this.changeReceiver, intentFilter, 2);
        } else {
            context.registerReceiver(this.changeReceiver, intentFilter);
        }
        updateConnectionState();
    }

    public void release() {
        if (this.isReleased.getAndSet(true)) {
            return;
        }
        this.backgroundExecutor.execute(new Runnable() { // from class: androidx.media3.session.AndroidAutoConnectionStateObserver$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m7635x22e409c9();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$release$1$androidx-media3-session-AndroidAutoConnectionStateObserver, reason: not valid java name */
    /* synthetic */ void m7635x22e409c9() {
        this.context.unregisterReceiver(this.changeReceiver);
    }

    public boolean isConnected() {
        return this.isConnected.get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateConnectionState() {
        boolean z = this.isConnected.get();
        boolean zQueryConnectionState = queryConnectionState();
        this.isConnected.set(zQueryConnectionState);
        if (z == zQueryConnectionState || this.isReleased.get()) {
            return;
        }
        this.listener.run();
    }

    private boolean queryConnectionState() {
        try {
            Cursor cursorQuery = this.context.getContentResolver().query(QUERY_URI, new String[]{QUERY_COLUMN}, null, null, null);
            if (cursorQuery == null) {
                if (cursorQuery != null) {
                }
                return false;
            }
            try {
                int columnIndex = cursorQuery.getColumnIndex(QUERY_COLUMN);
                if (columnIndex != -1) {
                    if (cursorQuery.moveToNext()) {
                        boolean z = cursorQuery.getInt(columnIndex) != 0;
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return z;
                    }
                    if (cursorQuery == null) {
                        return false;
                    }
                    return false;
                }
                if (cursorQuery == null) {
                    return false;
                }
            } catch (Throwable th) {
                if (cursorQuery == null) {
                    throw th;
                }
                try {
                    cursorQuery.close();
                    throw th;
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                    throw th;
                }
            }
            cursorQuery.close();
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    final class AndroidAutoChangeReceiver extends BroadcastReceiver {
        private AndroidAutoChangeReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Executor executor = AndroidAutoConnectionStateObserver.this.backgroundExecutor;
            final AndroidAutoConnectionStateObserver androidAutoConnectionStateObserver = AndroidAutoConnectionStateObserver.this;
            executor.execute(new Runnable() { // from class: androidx.media3.session.AndroidAutoConnectionStateObserver$AndroidAutoChangeReceiver$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    androidAutoConnectionStateObserver.updateConnectionState();
                }
            });
        }
    }
}
