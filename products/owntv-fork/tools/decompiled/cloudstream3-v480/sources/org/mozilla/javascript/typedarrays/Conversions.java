package org.mozilla.javascript.typedarrays;

import org.mozilla.javascript.ScriptRuntime;

/* JADX INFO: loaded from: classes8.dex */
public class Conversions {
    public static int toInt8(Object obj) {
        return (byte) ScriptRuntime.toInt32(obj);
    }

    public static int toUint8(Object obj) {
        return ScriptRuntime.toInt32(obj) & 255;
    }

    public static int toUint8Clamp(Object obj) {
        double number = ScriptRuntime.toNumber(obj);
        if (number <= 0.0d) {
            return 0;
        }
        if (number >= 255.0d) {
            return 255;
        }
        double dFloor = Math.floor(number);
        double d = 0.5d + dFloor;
        if (d < number) {
            return (int) (dFloor + 1.0d);
        }
        if (number < d) {
            return (int) dFloor;
        }
        int i = (int) dFloor;
        return i % 2 != 0 ? i + 1 : i;
    }

    public static int toInt16(Object obj) {
        return (short) ScriptRuntime.toInt32(obj);
    }

    public static int toUint16(Object obj) {
        return ScriptRuntime.toInt32(obj) & 65535;
    }

    public static int toInt32(Object obj) {
        return ScriptRuntime.toInt32(obj);
    }

    public static long toUint32(Object obj) {
        return ScriptRuntime.toUint32(obj);
    }
}
