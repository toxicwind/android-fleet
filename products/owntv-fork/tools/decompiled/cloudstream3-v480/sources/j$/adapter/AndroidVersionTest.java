package j$.adapter;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AndroidVersionTest {
    public static final boolean is24OrAbove = setUp("java.util.StringJoiner");
    public static final boolean is26OrAbove = setUp("java.nio.file.FileSystems");
    public static final boolean isHeadfull = setUp("android.os.Build");

    private static boolean setUp(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}
