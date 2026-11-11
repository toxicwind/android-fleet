package torrServer;

/* JADX INFO: loaded from: classes8.dex */
public abstract class TorrServer {
    public static native void _init();

    public static native void addTrackers(String str);

    public static native long startTorrentServer(String str, long j);

    public static native void stopTorrentServer();

    public static void touch() {
    }

    public static native void waitTorrentServer();

    private TorrServer() {
    }
}
