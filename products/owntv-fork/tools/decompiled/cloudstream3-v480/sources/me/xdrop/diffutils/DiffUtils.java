package me.xdrop.diffutils;

import me.xdrop.diffutils.structs.EditOp;
import me.xdrop.diffutils.structs.EditType;
import me.xdrop.diffutils.structs.MatchingBlock;
import me.xdrop.diffutils.structs.OpCode;

/* JADX INFO: loaded from: classes5.dex */
public class DiffUtils {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public static EditOp[] getEditOps(String str, String str2) {
        return getEditOps(str.length(), str, str2.length(), str2);
    }

    private static EditOp[] getEditOps(int i, String str, int i2, String str2) {
        char[] charArray = str.toCharArray();
        char[] charArray2 = str2.toCharArray();
        int i3 = i;
        int i4 = i2;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i3 > 0 && i4 > 0 && charArray[i5] == charArray2[i6]) {
            i3--;
            i4--;
            i5++;
            i6++;
            i7++;
        }
        while (i3 > 0 && i4 > 0 && charArray[(i5 + i3) - 1] == charArray2[(i6 + i4) - 1]) {
            i3--;
            i4--;
        }
        int i8 = i3 + 1;
        int i9 = i4 + 1;
        int[] iArr = new int[i9 * i8];
        for (int i10 = 0; i10 < i9; i10++) {
            iArr[i10] = i10;
        }
        for (int i11 = 1; i11 < i8; i11++) {
            iArr[i9 * i11] = i11;
        }
        for (int i12 = 1; i12 < i8; i12++) {
            int i13 = (i12 - 1) * i9;
            int i14 = i12 * i9;
            int i15 = (i14 + i9) - 1;
            char c = charArray[(i5 + i12) - 1];
            int i16 = i14 + 1;
            int i17 = i6;
            int i18 = i12;
            while (i16 <= i15) {
                int i19 = i13 + 1;
                int i20 = i17 + 1;
                int i21 = iArr[i13] + (c != charArray2[i17] ? 1 : 0);
                int i22 = i18 + 1;
                if (i22 <= i21) {
                    i21 = i22;
                }
                int i23 = iArr[i19] + 1;
                i18 = i21 > i23 ? i23 : i21;
                iArr[i16] = i18;
                i16++;
                i13 = i19;
                i17 = i20;
            }
        }
        return editOpsFromCostMatrix(i8, charArray, i5, i7, i9, charArray2, i6, i7, iArr);
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0081  */
    private static EditOp[] editOpsFromCostMatrix(int i, char[] cArr, int i2, int i3, int i4, char[] cArr2, int i5, int i6, int[] iArr) {
        int i7;
        int i8 = (i * i4) - 1;
        int i9 = iArr[i8];
        EditOp[] editOpArr = new EditOp[i9];
        int i10 = i - 1;
        int i11 = i4 - 1;
        while (true) {
            byte b = 0;
            while (true) {
                if (i10 <= 0 && i11 <= 0) {
                    return editOpArr;
                }
                if (i10 != 0 && i11 != 0 && iArr[i8] == iArr[(i8 - i4) - 1] && cArr[(i2 + i10) - 1] == cArr2[(i5 + i11) - 1]) {
                    i10--;
                    i11--;
                    break;
                }
                if (b < 0 && i11 != 0 && iArr[i8] == iArr[i8 - 1] + 1) {
                    EditOp editOp = new EditOp();
                    i9--;
                    editOpArr[i9] = editOp;
                    editOp.type = EditType.INSERT;
                    editOp.spos = i10 + i3;
                    i11--;
                    editOp.dpos = i11 + i6;
                    i8--;
                } else if (b > 0 && i10 != 0) {
                    i7 = i8 - i4;
                    if (iArr[i8] == iArr[i7] + 1) {
                        EditOp editOp2 = new EditOp();
                        i9--;
                        editOpArr[i9] = editOp2;
                        editOp2.type = EditType.DELETE;
                        i10--;
                        editOp2.spos = i10 + i3;
                        editOp2.dpos = i11 + i6;
                    } else {
                        if (i10 == 0) {
                        }
                        if (b != 0) {
                        }
                        if (b != 0) {
                        }
                    }
                    i8 = i7;
                } else {
                    if (i10 == 0 && i11 != 0 && iArr[i8] == iArr[(i8 - i4) - 1] + 1) {
                        i9--;
                        EditOp editOp3 = new EditOp();
                        editOpArr[i9] = editOp3;
                        editOp3.type = EditType.REPLACE;
                        i10--;
                        editOp3.spos = i10 + i3;
                        i11--;
                        editOp3.dpos = i11 + i6;
                        break;
                    }
                    if (b != 0 && i11 != 0 && iArr[i8] == iArr[i8 - 1] + 1) {
                        i9--;
                        EditOp editOp4 = new EditOp();
                        editOpArr[i9] = editOp4;
                        editOp4.type = EditType.INSERT;
                        editOp4.spos = i10 + i3;
                        i11--;
                        editOp4.dpos = i11 + i6;
                        i8--;
                        b = -1;
                    } else if (b != 0 && i10 != 0) {
                        i7 = i8 - i4;
                        if (iArr[i8] == iArr[i7] + 1) {
                            i9--;
                            EditOp editOp5 = new EditOp();
                            editOpArr[i9] = editOp5;
                            editOp5.type = EditType.DELETE;
                            i10--;
                            editOp5.spos = i10 + i3;
                            editOp5.dpos = i11 + i6;
                            b = 1;
                            i8 = i7;
                        }
                    }
                }
            }
            i8 -= i4 + 1;
        }
    }

    public static MatchingBlock[] getMatchingBlocks(String str, String str2) {
        return getMatchingBlocks(str.length(), str2.length(), getEditOps(str, str2));
    }

    public static MatchingBlock[] getMatchingBlocks(int i, int i2, OpCode[] opCodeArr) {
        int length = opCodeArr.length;
        int i3 = length;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            int i6 = i3 - 1;
            if (i3 == 0) {
                break;
            }
            if (opCodeArr[i4].type == EditType.KEEP) {
                i5++;
                while (i6 != 0 && opCodeArr[i4].type == EditType.KEEP) {
                    i6--;
                    i4++;
                }
                if (i6 == 0) {
                    break;
                }
            }
            i3 = i6;
            i4++;
        }
        MatchingBlock[] matchingBlockArr = new MatchingBlock[i5 + 1];
        matchingBlockArr[0] = new MatchingBlock();
        int i7 = 0;
        int i8 = 0;
        while (length != 0) {
            if (opCodeArr[i7].type == EditType.KEEP) {
                matchingBlockArr[i8].spos = opCodeArr[i7].sbeg;
                matchingBlockArr[i8].dpos = opCodeArr[i7].dbeg;
                while (length != 0 && opCodeArr[i7].type == EditType.KEEP) {
                    length--;
                    i7++;
                }
                if (length == 0) {
                    MatchingBlock matchingBlock = matchingBlockArr[i8];
                    matchingBlock.length = i - matchingBlock.spos;
                    i8++;
                    break;
                }
                matchingBlockArr[i8].length = opCodeArr[i7].sbeg - matchingBlockArr[i8].spos;
                i8++;
                matchingBlockArr[i8] = new MatchingBlock();
            }
            length--;
            i7++;
        }
        MatchingBlock matchingBlock2 = new MatchingBlock();
        matchingBlock2.spos = i;
        matchingBlock2.dpos = i2;
        matchingBlock2.length = 0;
        matchingBlockArr[i8] = matchingBlock2;
        return matchingBlockArr;
    }

    private static MatchingBlock[] getMatchingBlocks(int i, int i2, EditOp[] editOpArr) {
        int length = editOpArr.length;
        int i3 = length;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i3 != 0) {
            while (editOpArr[i6].type == EditType.KEEP && (i3 = i3 - 1) != 0) {
                i6++;
            }
            if (i3 == 0) {
                break;
            }
            if (i4 < editOpArr[i6].spos || i5 < editOpArr[i6].dpos) {
                i7++;
                i4 = editOpArr[i6].spos;
                i5 = editOpArr[i6].dpos;
            }
            EditType editType = editOpArr[i6].type;
            int i8 = AnonymousClass1.$SwitchMap$me$xdrop$diffutils$structs$EditType[editType.ordinal()];
            if (i8 == 1) {
                do {
                    i4++;
                    i5++;
                    i3--;
                    i6++;
                    if (i3 == 0 || editOpArr[i6].type != editType || i4 != editOpArr[i6].spos) {
                        break;
                    }
                } while (i5 == editOpArr[i6].dpos);
            } else if (i8 == 2) {
                do {
                    i4++;
                    i3--;
                    i6++;
                    if (i3 == 0 || editOpArr[i6].type != editType || i4 != editOpArr[i6].spos) {
                        break;
                    }
                } while (i5 == editOpArr[i6].dpos);
            } else if (i8 == 3) {
                do {
                    i5++;
                    i3--;
                    i6++;
                    if (i3 == 0 || editOpArr[i6].type != editType || i4 != editOpArr[i6].spos) {
                        break;
                    }
                } while (i5 == editOpArr[i6].dpos);
            }
        }
        if (i4 < i || i5 < i2) {
            i7++;
        }
        MatchingBlock[] matchingBlockArr = new MatchingBlock[i7 + 1];
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        while (length != 0) {
            while (editOpArr[i11].type == EditType.KEEP && (length = length - 1) != 0) {
                i11++;
            }
            if (length == 0) {
                break;
            }
            if (i9 < editOpArr[i11].spos || i10 < editOpArr[i11].dpos) {
                MatchingBlock matchingBlock = new MatchingBlock();
                matchingBlock.spos = i9;
                matchingBlock.dpos = i10;
                matchingBlock.length = editOpArr[i11].spos - i9;
                i9 = editOpArr[i11].spos;
                i10 = editOpArr[i11].dpos;
                matchingBlockArr[i12] = matchingBlock;
                i12++;
            }
            EditType editType2 = editOpArr[i11].type;
            int i13 = AnonymousClass1.$SwitchMap$me$xdrop$diffutils$structs$EditType[editType2.ordinal()];
            if (i13 == 1) {
                do {
                    i9++;
                    i10++;
                    length--;
                    i11++;
                    if (length == 0 || editOpArr[i11].type != editType2 || i9 != editOpArr[i11].spos) {
                        break;
                    }
                } while (i10 == editOpArr[i11].dpos);
            } else if (i13 == 2) {
                do {
                    i9++;
                    length--;
                    i11++;
                    if (length == 0 || editOpArr[i11].type != editType2 || i9 != editOpArr[i11].spos) {
                        break;
                    }
                } while (i10 == editOpArr[i11].dpos);
            } else if (i13 == 3) {
                do {
                    i10++;
                    length--;
                    i11++;
                    if (length == 0 || editOpArr[i11].type != editType2 || i9 != editOpArr[i11].spos) {
                        break;
                    }
                } while (i10 == editOpArr[i11].dpos);
            }
        }
        if (i9 < i || i10 < i2) {
            MatchingBlock matchingBlock2 = new MatchingBlock();
            matchingBlock2.spos = i9;
            matchingBlock2.dpos = i10;
            matchingBlock2.length = i - i9;
            matchingBlockArr[i12] = matchingBlock2;
            i12++;
        }
        MatchingBlock matchingBlock3 = new MatchingBlock();
        matchingBlock3.spos = i;
        matchingBlock3.dpos = i2;
        matchingBlock3.length = 0;
        matchingBlockArr[i12] = matchingBlock3;
        return matchingBlockArr;
    }

    /* JADX INFO: renamed from: me.xdrop.diffutils.DiffUtils$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$me$xdrop$diffutils$structs$EditType;

        static {
            int[] iArr = new int[EditType.values().length];
            $SwitchMap$me$xdrop$diffutils$structs$EditType = iArr;
            try {
                iArr[EditType.REPLACE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$me$xdrop$diffutils$structs$EditType[EditType.DELETE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$me$xdrop$diffutils$structs$EditType[EditType.INSERT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static OpCode[] editOpsToOpCodes(EditOp[] editOpArr, int i, int i2) {
        int length = editOpArr.length;
        int i3 = 0;
        int i4 = length;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i4 != 0) {
            while (editOpArr[i7].type == EditType.KEEP && (i4 = i4 - 1) != 0) {
                i7++;
            }
            if (i4 == 0) {
                break;
            }
            if (i5 < editOpArr[i7].spos || i6 < editOpArr[i7].dpos) {
                i8++;
                i5 = editOpArr[i7].spos;
                i6 = editOpArr[i7].dpos;
            }
            i8++;
            EditType editType = editOpArr[i7].type;
            int i9 = AnonymousClass1.$SwitchMap$me$xdrop$diffutils$structs$EditType[editType.ordinal()];
            if (i9 == 1) {
                do {
                    i5++;
                    i6++;
                    i4--;
                    i7++;
                    if (i4 == 0 || editOpArr[i7].type != editType || i5 != editOpArr[i7].spos) {
                        break;
                    }
                } while (i6 == editOpArr[i7].dpos);
            } else if (i9 == 2) {
                do {
                    i5++;
                    i4--;
                    i7++;
                    if (i4 == 0 || editOpArr[i7].type != editType || i5 != editOpArr[i7].spos) {
                        break;
                    }
                } while (i6 == editOpArr[i7].dpos);
            } else if (i9 == 3) {
                do {
                    i6++;
                    i4--;
                    i7++;
                    if (i4 == 0 || editOpArr[i7].type != editType || i5 != editOpArr[i7].spos) {
                        break;
                    }
                } while (i6 == editOpArr[i7].dpos);
            }
        }
        if (i5 < i || i6 < i2) {
            i8++;
        }
        OpCode[] opCodeArr = new OpCode[i8];
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        while (length != 0) {
            while (editOpArr[i11].type == EditType.KEEP && (length = length - 1) != 0) {
                i11++;
            }
            if (length == 0) {
                break;
            }
            OpCode opCode = new OpCode();
            opCodeArr[i12] = opCode;
            opCode.sbeg = i3;
            opCode.dbeg = i10;
            if (i3 < editOpArr[i11].spos || i10 < editOpArr[i11].dpos) {
                opCode.type = EditType.KEEP;
                i3 = editOpArr[i11].spos;
                opCode.send = i3;
                i10 = editOpArr[i11].dpos;
                opCode.dend = i10;
                i12++;
                OpCode opCode2 = new OpCode();
                opCodeArr[i12] = opCode2;
                opCode2.sbeg = i3;
                opCode2.dbeg = i10;
            }
            EditType editType2 = editOpArr[i11].type;
            int i13 = AnonymousClass1.$SwitchMap$me$xdrop$diffutils$structs$EditType[editType2.ordinal()];
            if (i13 == 1) {
                do {
                    i3++;
                    i10++;
                    length--;
                    i11++;
                    if (length == 0 || editOpArr[i11].type != editType2 || i3 != editOpArr[i11].spos) {
                        break;
                    }
                } while (i10 == editOpArr[i11].dpos);
            } else if (i13 == 2) {
                do {
                    i3++;
                    length--;
                    i11++;
                    if (length == 0 || editOpArr[i11].type != editType2 || i3 != editOpArr[i11].spos) {
                        break;
                    }
                } while (i10 == editOpArr[i11].dpos);
            } else if (i13 == 3) {
                do {
                    i10++;
                    length--;
                    i11++;
                    if (length == 0 || editOpArr[i11].type != editType2 || i3 != editOpArr[i11].spos) {
                        break;
                    }
                } while (i10 == editOpArr[i11].dpos);
            }
            opCodeArr[i12].type = editType2;
            opCodeArr[i12].send = i3;
            opCodeArr[i12].dend = i10;
            i12++;
        }
        if (i3 < i || i10 < i2) {
            if (opCodeArr[i12] == null) {
                opCodeArr[i12] = new OpCode();
            }
            opCodeArr[i12].type = EditType.KEEP;
            opCodeArr[i12].sbeg = i3;
            opCodeArr[i12].dbeg = i10;
            opCodeArr[i12].send = i;
            opCodeArr[i12].dend = i2;
        }
        return opCodeArr;
    }

    public static int levEditDistance(String str, String str2, int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        char[] charArray = str.toCharArray();
        char[] charArray2 = str2.toCharArray();
        int length = str.length();
        int length2 = str2.length();
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (length > 0 && length2 > 0 && charArray[i8] == charArray2[i9]) {
            length--;
            length2--;
            i8++;
            i9++;
        }
        while (true) {
            i2 = 1;
            if (length <= 0 || length2 <= 0 || charArray[(i8 + length) - 1] != charArray2[(i9 + length2) - 1]) {
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
            int i10 = length2;
            length2 = length;
            length = i10;
        } else {
            charArray2 = charArray;
            charArray = charArray2;
            int i11 = i9;
            i9 = i8;
            i8 = i11;
        }
        if (length == 1) {
            if (i != 0) {
                return (length2 + 1) - (memchr(charArray, i8, charArray2[i9], length2) * 2);
            }
            return length2 - memchr(charArray, i8, charArray2[i9], length2);
        }
        int i12 = length + 1;
        int i13 = length2 + 1;
        int i14 = i12 >> 1;
        int[] iArr = new int[i13];
        int i15 = 0;
        while (true) {
            if (i15 >= i13 - (i != 0 ? 0 : i14)) {
                break;
            }
            iArr[i15] = i15;
            i15++;
        }
        if (i != 0) {
            for (int i16 = 1; i16 < i12; i16++) {
                char c = charArray2[(i9 + i16) - 1];
                int i17 = i16;
                int i18 = i17;
                int i19 = i8;
                int i20 = 1;
                while (i20 <= length2) {
                    int i21 = i19 + 1;
                    int i22 = c == charArray[i19] ? i18 - 1 : i17 + 1;
                    int i23 = iArr[i20] + 1;
                    i17 = i22 > i23 ? i23 : i22;
                    iArr[i20] = i17;
                    i20++;
                    i18 = i23;
                    i19 = i21;
                }
            }
        } else {
            int i24 = i12 - i14;
            iArr[0] = i24 - 1;
            int i25 = 1;
            while (i25 < i12) {
                char c2 = charArray2[(i9 + i25) - i2];
                if (i25 >= i24) {
                    int i26 = i25 - i24;
                    int i27 = i8 + i26;
                    int i28 = i26 + 1;
                    i3 = i27 + 1;
                    i6 = iArr[i26] + (c2 != charArray[i27] ? i2 : i7);
                    i5 = iArr[i28] + 1;
                    if (i5 <= i6) {
                        i6 = i5;
                    }
                    i4 = i26 + 2;
                    iArr[i28] = i6;
                } else {
                    i3 = i8;
                    i4 = i2;
                    i5 = i25;
                    i6 = i5;
                }
                int i29 = i2;
                if (i25 <= i14 + 1) {
                    length2 = ((i13 + i25) - i14) - 2;
                }
                while (i4 <= length2) {
                    int i30 = i3 + 1;
                    char[] cArr = charArray;
                    int i31 = (i5 - 1) + (c2 != cArr[i3] ? i29 : 0);
                    int i32 = i6 + 1;
                    if (i32 <= i31) {
                        i31 = i32;
                    }
                    int i33 = iArr[i4] + 1;
                    i6 = i31 > i33 ? i33 : i31;
                    iArr[i4] = i6;
                    i4++;
                    i3 = i30;
                    i5 = i33;
                    charArray = cArr;
                }
                char[] cArr2 = charArray;
                if (i25 <= i14) {
                    int i34 = (i5 - 1) + (c2 != cArr2[i3] ? i29 : 0);
                    int i35 = i6 + 1;
                    if (i35 <= i34) {
                        i34 = i35;
                    }
                    iArr[i4] = i34;
                }
                i25++;
                i2 = i29;
                charArray = cArr2;
                i7 = 0;
            }
        }
        return iArr[length2];
    }

    private static int memchr(char[] cArr, int i, char c, int i2) {
        if (i2 != 0) {
            int i3 = 0;
            while (cArr[i + i3] != c) {
                i3++;
                i2--;
                if (i2 == 0) {
                }
            }
            return 1;
        }
        return 0;
    }

    public static double getRatio(String str, String str2) {
        int length = str.length() + str2.length();
        return ((double) (length - levEditDistance(str, str2, 1))) / ((double) length);
    }
}
