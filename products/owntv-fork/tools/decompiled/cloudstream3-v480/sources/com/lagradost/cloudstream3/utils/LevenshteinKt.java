package com.lagradost.cloudstream3.utils;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Levenshtein.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\f\n\u0002\b\u0002\u001a#\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0002\u0010\u0006\u001a[\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002¢\u0006\u0002\u0010\u0013\u001a1\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0002¢\u0006\u0002\u0010\u0017\u001a \u0010\u0018\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\tH\u0002\u001a(\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\tH\u0002¨\u0006 "}, d2 = {"getEditOps", "", "Lcom/lagradost/cloudstream3/utils/EditOp;", "s1", "", "s2", "(Ljava/lang/String;Ljava/lang/String;)[Lcom/lagradost/cloudstream3/utils/EditOp;", "editOpsFromCostMatrix", "len1", "", "c1", "p1", "o1", "len2", "c2", "p2", "o2", "matrix", "", "(ILjava/lang/String;IIILjava/lang/String;II[I)[Lcom/lagradost/cloudstream3/utils/EditOp;", "getMatchingBlocks", "Lcom/lagradost/cloudstream3/utils/MatchingBlock;", "ops", "(II[Lcom/lagradost/cloudstream3/utils/EditOp;)[Lcom/lagradost/cloudstream3/utils/MatchingBlock;", "levEditDistance", "xcost", "memchr", "haystack", TypedValues.CycleType.S_WAVE_OFFSET, "needle", "", "num", "library"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class LevenshteinKt {

    /* JADX INFO: compiled from: Levenshtein.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[EditType.values().length];
            try {
                iArr[EditType.REPLACE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[EditType.DELETE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[EditType.INSERT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final EditOp[] getEditOps(String str, String str2) {
        int length = str.length();
        int length2 = str2.length();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (length > 0 && length2 > 0 && str.charAt(i2) == str2.charAt(i3)) {
            length--;
            length2--;
            i2++;
            i3++;
            i++;
        }
        while (length > 0 && length2 > 0 && str.charAt((i2 + length) - 1) == str2.charAt((i3 + length2) - 1)) {
            length--;
            length2--;
        }
        int i4 = length + 1;
        int i5 = length2 + 1;
        int[] iArr = new int[i5 * i4];
        for (int i6 = 0; i6 < i5; i6++) {
            iArr[i6] = i6;
        }
        for (int i7 = 1; i7 < i4; i7++) {
            iArr[i5 * i7] = i7;
        }
        for (int i8 = 1; i8 < i4; i8++) {
            int i9 = (i8 - 1) * i5;
            int i10 = i8 * i5;
            int i11 = (i10 + i5) - 1;
            char cCharAt = str.charAt((i2 + i8) - 1);
            int i12 = i10 + 1;
            int i13 = i3;
            int i14 = i8;
            while (i12 <= i11) {
                int i15 = i9 + 1;
                int i16 = i13 + 1;
                int i17 = iArr[i9] + (cCharAt != str2.charAt(i13) ? 1 : 0);
                int i18 = i14 + 1;
                if (i18 <= i17) {
                    i17 = i18;
                }
                int i19 = iArr[i15] + 1;
                i14 = i17 > i19 ? i19 : i17;
                iArr[i12] = i14;
                i12++;
                i9 = i15;
                i13 = i16;
            }
        }
        return editOpsFromCostMatrix(i4, str, i2, i, i5, str2, i3, i, iArr);
    }

    private static final EditOp[] editOpsFromCostMatrix(int i, String str, int i2, int i3, int i4, String str2, int i5, int i6, int[] iArr) {
        int i7 = i - 1;
        int i8 = i4 - 1;
        int i9 = (i * i4) - 1;
        int i10 = iArr[i9];
        EditOp[] editOpArr = new EditOp[i10];
        while (true) {
            byte b = 0;
            while (true) {
                if (i7 > 0 || i8 > 0) {
                    if (b < 0 && i8 != 0 && iArr[i9] == iArr[i9 - 1] + 1) {
                        EditOp editOp = new EditOp(null, 0, 0, 7, null);
                        i10--;
                        editOpArr[i10] = editOp;
                        editOp.setType(EditType.INSERT);
                        editOp.setSpos(i7 + i3);
                        i8--;
                        editOp.setDpos(i8 + i6);
                        i9--;
                    } else {
                        if (b > 0 && i7 != 0) {
                            int i11 = i9 - i4;
                            if (iArr[i9] == iArr[i11] + 1) {
                                EditOp editOp2 = new EditOp(null, 0, 0, 7, null);
                                i10--;
                                editOpArr[i10] = editOp2;
                                editOp2.setType(EditType.DELETE);
                                i7--;
                                editOp2.setSpos(i7 + i3);
                                editOp2.setDpos(i8 + i6);
                                i9 = i11;
                            }
                        }
                        if (i7 != 0 && i8 != 0 && iArr[i9] == iArr[(i9 - i4) - 1]) {
                            if (str.charAt((i2 + i7) - 1) == str2.charAt((i5 + i8) - 1)) {
                                i7--;
                                i8--;
                                break;
                            }
                        }
                        if (i7 != 0 && i8 != 0 && iArr[i9] == iArr[(i9 - i4) - 1] + 1) {
                            i10--;
                            EditOp editOp3 = new EditOp(null, 0, 0, 7, null);
                            editOpArr[i10] = editOp3;
                            editOp3.setType(EditType.REPLACE);
                            i7--;
                            editOp3.setSpos(i7 + i3);
                            i8--;
                            editOp3.setDpos(i8 + i6);
                            break;
                        }
                        if (b == 0 && i8 != 0 && iArr[i9] == iArr[i9 - 1] + 1) {
                            i10--;
                            EditOp editOp4 = new EditOp(null, 0, 0, 7, null);
                            editOpArr[i10] = editOp4;
                            editOp4.setType(EditType.INSERT);
                            editOp4.setSpos(i7 + i3);
                            i8--;
                            editOp4.setDpos(i8 + i6);
                            i9--;
                            b = -1;
                        } else if (b == 0 && i7 != 0) {
                            int i12 = i9 - i4;
                            if (iArr[i9] == iArr[i12] + 1) {
                                i10--;
                                EditOp editOp5 = new EditOp(null, 0, 0, 7, null);
                                editOpArr[i10] = editOp5;
                                editOp5.setType(EditType.DELETE);
                                i7--;
                                editOp5.setSpos(i7 + i3);
                                editOp5.setDpos(i8 + i6);
                                b = 1;
                                i9 = i12;
                            }
                        }
                    }
                } else {
                    return (EditOp[]) ArraysKt.requireNoNulls(editOpArr);
                }
            }
            i9 -= i4 + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MatchingBlock[] getMatchingBlocks(int i, int i2, EditOp[] editOpArr) {
        int length = editOpArr.length;
        int i3 = length;
        int spos = 0;
        int dpos = 0;
        int i4 = 0;
        int i5 = 0;
        while (i3 != 0) {
            while (editOpArr[i4].getType() == EditType.KEEP && (i3 = i3 - 1) != 0) {
                i4++;
            }
            if (i3 == 0) {
                break;
            }
            if (spos < editOpArr[i4].getSpos() || dpos < editOpArr[i4].getDpos()) {
                i5++;
                spos = editOpArr[i4].getSpos();
                dpos = editOpArr[i4].getDpos();
            }
            EditType type = editOpArr[i4].getType();
            Intrinsics.checkNotNull(type);
            int i6 = WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
            if (i6 == 1) {
                do {
                    spos++;
                    dpos++;
                    i3--;
                    i4++;
                    if (i3 == 0 || editOpArr[i4].getType() != type || spos != editOpArr[i4].getSpos()) {
                        break;
                    }
                } while (dpos == editOpArr[i4].getDpos());
            } else if (i6 == 2) {
                do {
                    spos++;
                    i3--;
                    i4++;
                    if (i3 == 0 || editOpArr[i4].getType() != type || spos != editOpArr[i4].getSpos()) {
                        break;
                    }
                } while (dpos == editOpArr[i4].getDpos());
            } else if (i6 == 3) {
                do {
                    dpos++;
                    i3--;
                    i4++;
                    if (i3 == 0 || editOpArr[i4].getType() != type || spos != editOpArr[i4].getSpos()) {
                        break;
                    }
                } while (dpos == editOpArr[i4].getDpos());
            }
        }
        if (spos < i || dpos < i2) {
            i5++;
        }
        MatchingBlock[] matchingBlockArr = new MatchingBlock[i5 + 1];
        int spos2 = 0;
        int dpos2 = 0;
        int i7 = 0;
        int i8 = 0;
        while (length != 0) {
            while (editOpArr[i7].getType() == EditType.KEEP && (length = length - 1) != 0) {
                i7++;
            }
            if (length == 0) {
                break;
            }
            if (spos2 < editOpArr[i7].getSpos() || dpos2 < editOpArr[i7].getDpos()) {
                MatchingBlock matchingBlock = new MatchingBlock(spos2, dpos2, editOpArr[i7].getSpos() - spos2);
                spos2 = editOpArr[i7].getSpos();
                dpos2 = editOpArr[i7].getDpos();
                matchingBlockArr[i8] = matchingBlock;
                i8++;
            }
            EditType type2 = editOpArr[i7].getType();
            Intrinsics.checkNotNull(type2);
            int i9 = WhenMappings.$EnumSwitchMapping$0[type2.ordinal()];
            if (i9 == 1) {
                do {
                    spos2++;
                    dpos2++;
                    length--;
                    i7++;
                    if (length == 0 || editOpArr[i7].getType() != type2 || spos2 != editOpArr[i7].getSpos()) {
                        break;
                    }
                } while (dpos2 == editOpArr[i7].getDpos());
            } else if (i9 == 2) {
                do {
                    spos2++;
                    length--;
                    i7++;
                    if (length == 0 || editOpArr[i7].getType() != type2 || spos2 != editOpArr[i7].getSpos()) {
                        break;
                    }
                } while (dpos2 == editOpArr[i7].getDpos());
            } else if (i9 == 3) {
                do {
                    dpos2++;
                    length--;
                    i7++;
                    if (length == 0 || editOpArr[i7].getType() != type2 || spos2 != editOpArr[i7].getSpos()) {
                        break;
                    }
                } while (dpos2 == editOpArr[i7].getDpos());
            }
        }
        if (spos2 < i || dpos2 < i2) {
            matchingBlockArr[i8] = new MatchingBlock(spos2, dpos2, i - spos2);
            i8++;
        }
        matchingBlockArr[i8] = new MatchingBlock(i, i2, 0);
        return (MatchingBlock[]) ArraysKt.filterNotNull(matchingBlockArr).toArray(new MatchingBlock[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int levEditDistance(String str, String str2, int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        String str3 = str;
        String str4 = str2;
        int length = str3.length();
        int length2 = str4.length();
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        while (length > 0 && length2 > 0 && str3.charAt(i9) == str4.charAt(i10)) {
            length--;
            length2--;
            i9++;
            i10++;
        }
        while (true) {
            i2 = 1;
            if (length <= 0 || length2 <= 0 || str3.charAt((i9 + length) - 1) != str4.charAt((i10 + length2) - 1)) {
                break;
            }
            length--;
            length2--;
        }
        if (length == 0) {
            return length2;
        }
        if (length2 == 0) {
            return length;
        }
        if (length > length2) {
            int i11 = length2;
            length2 = length;
            length = i11;
        } else {
            str4 = str3;
            str3 = str4;
            int i12 = i10;
            i10 = i9;
            i9 = i12;
        }
        if (length == 1) {
            if (i != 0) {
                return (length2 + 1) - (memchr(str3, i9, str4.charAt(i10), length2) * 2);
            }
            return length2 - memchr(str3, i9, str4.charAt(i10), length2);
        }
        int i13 = length + 1;
        int i14 = length2 + 1;
        int i15 = i13 >> 1;
        int[] iArr = new int[i14];
        int i16 = 0;
        while (true) {
            if (i16 >= i14 - (i != 0 ? 0 : i15)) {
                break;
            }
            iArr[i16] = i16;
            i16++;
        }
        if (i != 0) {
            for (int i17 = 1; i17 < i13; i17++) {
                char cCharAt = str4.charAt((i10 + i17) - 1);
                int i18 = i17;
                int i19 = i18;
                int i20 = i9;
                int i21 = 1;
                while (i21 <= length2) {
                    int i22 = i20 + 1;
                    int i23 = cCharAt == str3.charAt(i20) ? i19 - 1 : i18 + 1;
                    int i24 = iArr[i21] + 1;
                    i18 = i23 > i24 ? i24 : i23;
                    iArr[i21] = i18;
                    i21++;
                    i19 = i24;
                    i20 = i22;
                }
            }
        } else {
            int i25 = i13 - i15;
            iArr[0] = i25 - 1;
            int i26 = 1;
            while (i26 < i13) {
                char cCharAt2 = str4.charAt((i10 + i26) - i2);
                if (i26 >= i25) {
                    int i27 = i26 - i25;
                    int i28 = i9 + i27;
                    int i29 = i27 + 1;
                    i3 = i28 + 1;
                    i6 = iArr[i27] + (cCharAt2 != str3.charAt(i28) ? i2 : i8);
                    i5 = iArr[i29] + 1;
                    if (i5 <= i6) {
                        i6 = i5;
                    }
                    i4 = i27 + 2;
                    iArr[i29] = i6;
                } else {
                    i3 = i9;
                    i4 = i2;
                    i5 = i26;
                    i6 = i5;
                }
                int i30 = i2;
                if (i26 <= i15 + 1) {
                    length2 = ((i14 + i26) - i15) - 2;
                }
                while (true) {
                    i7 = i3;
                    if (i4 > length2) {
                        break;
                    }
                    i3 = i7 + 1;
                    int i31 = (i5 - 1) + (cCharAt2 != str3.charAt(i7) ? i30 : 0);
                    int i32 = i6 + 1;
                    if (i32 <= i31) {
                        i31 = i32;
                    }
                    int i33 = iArr[i4] + 1;
                    i6 = i31 > i33 ? i33 : i31;
                    iArr[i4] = i6;
                    i4++;
                    i5 = i33;
                }
                if (i26 <= i15) {
                    int i34 = (i5 - 1) + (cCharAt2 != str3.charAt(i7) ? i30 : 0);
                    int i35 = i6 + 1;
                    if (i35 <= i34) {
                        i34 = i35;
                    }
                    iArr[i4] = i34;
                }
                i26++;
                i2 = i30;
                i8 = 0;
            }
        }
        return iArr[length2];
    }

    private static final int memchr(String str, int i, char c, int i2) {
        if (i2 != 0) {
            int i3 = 0;
            while (str.charAt(i + i3) != c) {
                i3++;
                i2--;
                if (i2 == 0) {
                }
            }
            return 1;
        }
        return 0;
    }
}
