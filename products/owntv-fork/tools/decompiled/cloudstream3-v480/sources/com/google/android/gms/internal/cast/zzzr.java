package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public enum zzzr {
    DOUBLE(zzzs.DOUBLE, 1),
    FLOAT(zzzs.FLOAT, 5),
    INT64(zzzs.LONG, 0),
    UINT64(zzzs.LONG, 0),
    INT32(zzzs.INT, 0),
    FIXED64(zzzs.LONG, 1),
    FIXED32(zzzs.INT, 5),
    BOOL(zzzs.BOOLEAN, 0),
    STRING(zzzs.STRING, 2),
    GROUP(zzzs.MESSAGE, 3),
    MESSAGE(zzzs.MESSAGE, 2),
    BYTES(zzzs.BYTE_STRING, 2),
    UINT32(zzzs.INT, 0),
    ENUM(zzzs.ENUM, 0),
    SFIXED32(zzzs.INT, 5),
    SFIXED64(zzzs.LONG, 1),
    SINT32(zzzs.INT, 0),
    SINT64(zzzs.LONG, 0);

    private final zzzs zzt;

    zzzr(zzzs zzzsVar, int i) {
        this.zzt = zzzsVar;
    }

    public final zzzs zza() {
        return this.zzt;
    }
}
