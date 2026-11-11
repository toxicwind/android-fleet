package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public enum zzxc {
    DOUBLE(0, 1, zzxr.DOUBLE),
    FLOAT(1, 1, zzxr.FLOAT),
    INT64(2, 1, zzxr.LONG),
    UINT64(3, 1, zzxr.LONG),
    INT32(4, 1, zzxr.INT),
    FIXED64(5, 1, zzxr.LONG),
    FIXED32(6, 1, zzxr.INT),
    BOOL(7, 1, zzxr.BOOLEAN),
    STRING(8, 1, zzxr.STRING),
    MESSAGE(9, 1, zzxr.MESSAGE),
    BYTES(10, 1, zzxr.BYTE_STRING),
    UINT32(11, 1, zzxr.INT),
    ENUM(12, 1, zzxr.ENUM),
    SFIXED32(13, 1, zzxr.INT),
    SFIXED64(14, 1, zzxr.LONG),
    SINT32(15, 1, zzxr.INT),
    SINT64(16, 1, zzxr.LONG),
    GROUP(17, 1, zzxr.MESSAGE),
    DOUBLE_LIST(18, 2, zzxr.DOUBLE),
    FLOAT_LIST(19, 2, zzxr.FLOAT),
    INT64_LIST(20, 2, zzxr.LONG),
    UINT64_LIST(21, 2, zzxr.LONG),
    INT32_LIST(22, 2, zzxr.INT),
    FIXED64_LIST(23, 2, zzxr.LONG),
    FIXED32_LIST(24, 2, zzxr.INT),
    BOOL_LIST(25, 2, zzxr.BOOLEAN),
    STRING_LIST(26, 2, zzxr.STRING),
    MESSAGE_LIST(27, 2, zzxr.MESSAGE),
    BYTES_LIST(28, 2, zzxr.BYTE_STRING),
    UINT32_LIST(29, 2, zzxr.INT),
    ENUM_LIST(30, 2, zzxr.ENUM),
    SFIXED32_LIST(31, 2, zzxr.INT),
    SFIXED64_LIST(32, 2, zzxr.LONG),
    SINT32_LIST(33, 2, zzxr.INT),
    SINT64_LIST(34, 2, zzxr.LONG),
    DOUBLE_LIST_PACKED(35, 3, zzxr.DOUBLE),
    FLOAT_LIST_PACKED(36, 3, zzxr.FLOAT),
    INT64_LIST_PACKED(37, 3, zzxr.LONG),
    UINT64_LIST_PACKED(38, 3, zzxr.LONG),
    INT32_LIST_PACKED(39, 3, zzxr.INT),
    FIXED64_LIST_PACKED(40, 3, zzxr.LONG),
    FIXED32_LIST_PACKED(41, 3, zzxr.INT),
    BOOL_LIST_PACKED(42, 3, zzxr.BOOLEAN),
    UINT32_LIST_PACKED(43, 3, zzxr.INT),
    ENUM_LIST_PACKED(44, 3, zzxr.ENUM),
    SFIXED32_LIST_PACKED(45, 3, zzxr.INT),
    SFIXED64_LIST_PACKED(46, 3, zzxr.LONG),
    SINT32_LIST_PACKED(47, 3, zzxr.INT),
    SINT64_LIST_PACKED(48, 3, zzxr.LONG),
    GROUP_LIST(49, 2, zzxr.MESSAGE),
    MAP(50, 4, zzxr.VOID);

    private static final zzxc[] zzZ;
    private final int zzab;

    static {
        zzxc[] zzxcVarArrValues = values();
        zzZ = new zzxc[zzxcVarArrValues.length];
        for (zzxc zzxcVar : zzxcVarArrValues) {
            zzZ[zzxcVar.zzab] = zzxcVar;
        }
    }

    zzxc(int i, int i2, zzxr zzxrVar) {
        this.zzab = i;
        int i3 = i2 - 1;
        if (i3 == 1 || i3 == 3) {
            zzxrVar.zza();
        }
        if (i2 == 1) {
            zzxr zzxrVar2 = zzxr.VOID;
            zzxrVar.ordinal();
        }
    }

    public final int zza() {
        return this.zzab;
    }
}
