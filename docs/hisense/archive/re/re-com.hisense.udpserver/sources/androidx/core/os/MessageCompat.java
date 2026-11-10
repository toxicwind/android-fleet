package androidx.core.os;

import android.annotation.SuppressLint;
import android.os.Message;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public final class MessageCompat {
    public static boolean sTryIsAsynchronous = true;
    public static boolean sTrySetAsynchronous = true;

    @SuppressLint({"NewApi"})
    public static boolean isAsynchronous(@NonNull Message message) {
        return message.isAsynchronous();
    }

    @SuppressLint({"NewApi"})
    public static void setAsynchronous(@NonNull Message message, boolean z) {
        message.setAsynchronous(z);
    }
}
