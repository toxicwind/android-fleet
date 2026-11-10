package androidx.core.math;

/* JADX INFO: loaded from: classes.dex */
public class MathUtils {
    public static double clamp(double value, double min, double max) {
        if (value < min) {
            return min;
        }
        return value > max ? max : value;
    }

    public static float clamp(float value, float min, float max) {
        if (value < min) {
            return min;
        }
        return value > max ? max : value;
    }

    public static int clamp(int value, int min, int max) {
        if (value < min) {
            return min;
        }
        return value > max ? max : value;
    }

    public static long clamp(long value, long min, long max) {
        if (value < min) {
            return min;
        }
        return value > max ? max : value;
    }
}
