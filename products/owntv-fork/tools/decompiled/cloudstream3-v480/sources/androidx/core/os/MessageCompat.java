package androidx.core.os;

import android.os.Message;

/* JADX INFO: loaded from: classes4.dex */
public final class MessageCompat {
    public static void setAsynchronous(Message message, boolean z) {
        message.setAsynchronous(z);
    }

    public static boolean isAsynchronous(Message message) {
        return message.isAsynchronous();
    }

    private MessageCompat() {
    }
}
