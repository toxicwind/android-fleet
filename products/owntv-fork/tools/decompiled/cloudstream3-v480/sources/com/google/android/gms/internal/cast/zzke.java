package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzke implements zzkd {
    zzke() {
    }

    /* JADX WARN: Code duplicated, block: B:14:0x002c  */
    /* JADX WARN: Code duplicated, block: B:16:0x002f A[RETURN] */
    @Override // com.google.android.gms.internal.cast.zzkd
    public final StackTraceElement zza(Class cls, int i) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        String name = cls.getName();
        int i2 = 3;
        boolean z = false;
        while (i2 < stackTrace.length) {
            if (stackTrace[i2].getClassName().equals(name)) {
                z = true;
            } else {
                if (z) {
                    if (i2 != -1) {
                        return stackTrace[i2];
                    }
                    return null;
                }
                z = false;
            }
            i2++;
        }
        i2 = -1;
        if (i2 != -1) {
            return stackTrace[i2];
        }
        return null;
    }
}
