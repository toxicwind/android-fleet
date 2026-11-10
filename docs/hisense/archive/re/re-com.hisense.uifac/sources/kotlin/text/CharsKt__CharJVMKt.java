package kotlin.text;

import kotlin.ranges.IntRange;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: CharJVM.kt */
/* JADX INFO: loaded from: classes.dex */
public class CharsKt__CharJVMKt {
    public static int checkRadix(int i) {
        if (2 <= i && 36 >= i) {
            return i;
        }
        throw new IllegalArgumentException("radix " + i + " was not in valid range " + new IntRange(2, 36));
    }
}
