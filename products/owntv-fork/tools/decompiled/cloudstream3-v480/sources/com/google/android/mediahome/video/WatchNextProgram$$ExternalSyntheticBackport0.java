package com.google.android.mediahome.video;

import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.mediahome:video@@1.0.0 */
/* JADX INFO: loaded from: classes6.dex */
public final /* synthetic */ class WatchNextProgram$$ExternalSyntheticBackport0 {
    public static /* synthetic */ boolean m(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof boolean[]) {
            return (obj2 instanceof boolean[]) && Arrays.equals((boolean[]) obj, (boolean[]) obj2);
        }
        if (obj instanceof byte[]) {
            return (obj2 instanceof byte[]) && Arrays.equals((byte[]) obj, (byte[]) obj2);
        }
        if (obj instanceof char[]) {
            return (obj2 instanceof char[]) && Arrays.equals((char[]) obj, (char[]) obj2);
        }
        if (obj instanceof double[]) {
            return (obj2 instanceof double[]) && Arrays.equals((double[]) obj, (double[]) obj2);
        }
        if (obj instanceof float[]) {
            return (obj2 instanceof float[]) && Arrays.equals((float[]) obj, (float[]) obj2);
        }
        if (obj instanceof int[]) {
            return (obj2 instanceof int[]) && Arrays.equals((int[]) obj, (int[]) obj2);
        }
        if (obj instanceof long[]) {
            return (obj2 instanceof long[]) && Arrays.equals((long[]) obj, (long[]) obj2);
        }
        if (obj instanceof short[]) {
            return (obj2 instanceof short[]) && Arrays.equals((short[]) obj, (short[]) obj2);
        }
        if (obj instanceof Object[]) {
            return (obj2 instanceof Object[]) && Arrays.deepEquals((Object[]) obj, (Object[]) obj2);
        }
        return obj.equals(obj2);
    }
}
