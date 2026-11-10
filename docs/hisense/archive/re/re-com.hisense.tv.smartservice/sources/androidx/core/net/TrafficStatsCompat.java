package androidx.core.net;

import android.net.TrafficStats;
import android.os.Build;
import androidx.annotation.NonNull;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;

/* JADX INFO: loaded from: classes.dex */
public final class TrafficStatsCompat {
    @Deprecated
    public static void clearThreadStatsTag() {
        TrafficStats.clearThreadStatsTag();
    }

    @Deprecated
    public static int getThreadStatsTag() {
        return TrafficStats.getThreadStatsTag();
    }

    @Deprecated
    public static void incrementOperationCount(int operationCount) {
        TrafficStats.incrementOperationCount(operationCount);
    }

    @Deprecated
    public static void setThreadStatsTag(int tag) {
        TrafficStats.setThreadStatsTag(tag);
    }

    public static void tagDatagramSocket(@NonNull DatagramSocket socket) throws SocketException {
        int i = Build.VERSION.SDK_INT;
        TrafficStats.tagDatagramSocket(socket);
    }

    @Deprecated
    public static void tagSocket(Socket socket) throws SocketException {
        TrafficStats.tagSocket(socket);
    }

    public static void untagDatagramSocket(@NonNull DatagramSocket socket) throws SocketException {
        int i = Build.VERSION.SDK_INT;
        TrafficStats.untagDatagramSocket(socket);
    }

    @Deprecated
    public static void untagSocket(Socket socket) throws SocketException {
        TrafficStats.untagSocket(socket);
    }

    @Deprecated
    public static void incrementOperationCount(int tag, int operationCount) {
        TrafficStats.incrementOperationCount(tag, operationCount);
    }
}
