package qrcode.internals;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import qrcode.raw.ErrorCorrectionLevel;

/* JADX INFO: compiled from: RSBlock.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0080\b\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lqrcode/internals/RSBlock;", "", "totalCount", "", "dataCount", "<init>", "(II)V", "getTotalCount", "()I", "getDataCount", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "Companion", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class RSBlock {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int[][] RS_BLOCK_TABLE = {new int[]{1, 26, 19}, new int[]{1, 26, 16}, new int[]{1, 26, 13}, new int[]{1, 26, 9}, new int[]{1, 44, 34}, new int[]{1, 44, 28}, new int[]{1, 44, 22}, new int[]{1, 44, 16}, new int[]{1, 70, 55}, new int[]{1, 70, 44}, new int[]{2, 35, 17}, new int[]{2, 35, 13}, new int[]{1, 100, 80}, new int[]{2, 50, 32}, new int[]{2, 50, 24}, new int[]{4, 25, 9}, new int[]{1, 134, 108}, new int[]{2, 67, 43}, new int[]{2, 33, 15, 2, 34, 16}, new int[]{2, 33, 11, 2, 34, 12}, new int[]{2, 86, 68}, new int[]{4, 43, 27}, new int[]{4, 43, 19}, new int[]{4, 43, 15}, new int[]{2, 98, 78}, new int[]{4, 49, 31}, new int[]{2, 32, 14, 4, 33, 15}, new int[]{4, 39, 13, 1, 40, 14}, new int[]{2, 121, 97}, new int[]{2, 60, 38, 2, 61, 39}, new int[]{4, 40, 18, 2, 41, 19}, new int[]{4, 40, 14, 2, 41, 15}, new int[]{2, 146, 116}, new int[]{3, 58, 36, 2, 59, 37}, new int[]{4, 36, 16, 4, 37, 17}, new int[]{4, 36, 12, 4, 37, 13}, new int[]{2, 86, 68, 2, 87, 69}, new int[]{4, 69, 43, 1, 70, 44}, new int[]{6, 43, 19, 2, 44, 20}, new int[]{6, 43, 15, 2, 44, 16}, new int[]{4, 101, 81}, new int[]{1, 80, 50, 4, 81, 51}, new int[]{4, 50, 22, 4, 51, 23}, new int[]{3, 36, 12, 8, 37, 13}, new int[]{2, 116, 92, 2, 117, 93}, new int[]{6, 58, 36, 2, 59, 37}, new int[]{4, 46, 20, 6, 47, 21}, new int[]{7, 42, 14, 4, 43, 15}, new int[]{4, 133, 107}, new int[]{8, 59, 37, 1, 60, 38}, new int[]{8, 44, 20, 4, 45, 21}, new int[]{12, 33, 11, 4, 34, 12}, new int[]{3, 145, 115, 1, 146, 116}, new int[]{4, 64, 40, 5, 65, 41}, new int[]{11, 36, 16, 5, 37, 17}, new int[]{11, 36, 12, 5, 37, 13}, new int[]{5, 109, 87, 1, 110, 88}, new int[]{5, 65, 41, 5, 66, 42}, new int[]{5, 54, 24, 7, 55, 25}, new int[]{11, 36, 12, 7, 37, 13}, new int[]{5, 122, 98, 1, 123, 99}, new int[]{7, 73, 45, 3, 74, 46}, new int[]{15, 43, 19, 2, 44, 20}, new int[]{3, 45, 15, 13, 46, 16}, new int[]{1, 135, 107, 5, 136, 108}, new int[]{10, 74, 46, 1, 75, 47}, new int[]{1, 50, 22, 15, 51, 23}, new int[]{2, 42, 14, 17, 43, 15}, new int[]{5, 150, 120, 1, 151, 121}, new int[]{9, 69, 43, 4, 70, 44}, new int[]{17, 50, 22, 1, 51, 23}, new int[]{2, 42, 14, 19, 43, 15}, new int[]{3, 141, 113, 4, 142, 114}, new int[]{3, 70, 44, 11, 71, 45}, new int[]{17, 47, 21, 4, 48, 22}, new int[]{9, 39, 13, 16, 40, 14}, new int[]{3, 135, 107, 5, 136, 108}, new int[]{3, 67, 41, 13, 68, 42}, new int[]{15, 54, 24, 5, 55, 25}, new int[]{15, 43, 15, 10, 44, 16}, new int[]{4, 144, 116, 4, 145, 117}, new int[]{17, 68, 42}, new int[]{17, 50, 22, 6, 51, 23}, new int[]{19, 46, 16, 6, 47, 17}, new int[]{2, 139, 111, 7, 140, 112}, new int[]{17, 74, 46}, new int[]{7, 54, 24, 16, 55, 25}, new int[]{34, 37, 13}, new int[]{4, 151, 121, 5, 152, 122}, new int[]{4, 75, 47, 14, 76, 48}, new int[]{11, 54, 24, 14, 55, 25}, new int[]{16, 45, 15, 14, 46, 16}, new int[]{6, 147, 117, 4, 148, 118}, new int[]{6, 73, 45, 14, 74, 46}, new int[]{11, 54, 24, 16, 55, 25}, new int[]{30, 46, 16, 2, 47, 17}, new int[]{8, 132, 106, 4, 133, 107}, new int[]{8, 75, 47, 13, 76, 48}, new int[]{7, 54, 24, 22, 55, 25}, new int[]{22, 45, 15, 13, 46, 16}, new int[]{10, 142, 114, 2, 143, 115}, new int[]{19, 74, 46, 4, 75, 47}, new int[]{28, 50, 22, 6, 51, 23}, new int[]{33, 46, 16, 4, 47, 17}, new int[]{8, 152, 122, 4, 153, 123}, new int[]{22, 73, 45, 3, 74, 46}, new int[]{8, 53, 23, 26, 54, 24}, new int[]{12, 45, 15, 28, 46, 16}, new int[]{3, 147, 117, 10, 148, 118}, new int[]{3, 73, 45, 23, 74, 46}, new int[]{4, 54, 24, 31, 55, 25}, new int[]{11, 45, 15, 31, 46, 16}, new int[]{7, 146, 116, 7, 147, 117}, new int[]{21, 73, 45, 7, 74, 46}, new int[]{1, 53, 23, 37, 54, 24}, new int[]{19, 45, 15, 26, 46, 16}, new int[]{5, 145, 115, 10, 146, 116}, new int[]{19, 75, 47, 10, 76, 48}, new int[]{15, 54, 24, 25, 55, 25}, new int[]{23, 45, 15, 25, 46, 16}, new int[]{13, 145, 115, 3, 146, 116}, new int[]{2, 74, 46, 29, 75, 47}, new int[]{42, 54, 24, 1, 55, 25}, new int[]{23, 45, 15, 28, 46, 16}, new int[]{17, 145, 115}, new int[]{10, 74, 46, 23, 75, 47}, new int[]{10, 54, 24, 35, 55, 25}, new int[]{19, 45, 15, 35, 46, 16}, new int[]{17, 145, 115, 1, 146, 116}, new int[]{14, 74, 46, 21, 75, 47}, new int[]{29, 54, 24, 19, 55, 25}, new int[]{11, 45, 15, 46, 46, 16}, new int[]{13, 145, 115, 6, 146, 116}, new int[]{14, 74, 46, 23, 75, 47}, new int[]{44, 54, 24, 7, 55, 25}, new int[]{59, 46, 16, 1, 47, 17}, new int[]{12, 151, 121, 7, 152, 122}, new int[]{12, 75, 47, 26, 76, 48}, new int[]{39, 54, 24, 14, 55, 25}, new int[]{22, 45, 15, 41, 46, 16}, new int[]{6, 151, 121, 14, 152, 122}, new int[]{6, 75, 47, 34, 76, 48}, new int[]{46, 54, 24, 10, 55, 25}, new int[]{2, 45, 15, 64, 46, 16}, new int[]{17, 152, 122, 4, 153, 123}, new int[]{29, 74, 46, 14, 75, 47}, new int[]{49, 54, 24, 10, 55, 25}, new int[]{24, 45, 15, 46, 46, 16}, new int[]{4, 152, 122, 18, 153, 123}, new int[]{13, 74, 46, 32, 75, 47}, new int[]{48, 54, 24, 14, 55, 25}, new int[]{42, 45, 15, 32, 46, 16}, new int[]{20, 147, 117, 4, 148, 118}, new int[]{40, 75, 47, 7, 76, 48}, new int[]{43, 54, 24, 22, 55, 25}, new int[]{10, 45, 15, 67, 46, 16}, new int[]{19, 148, 118, 6, 149, 119}, new int[]{18, 75, 47, 31, 76, 48}, new int[]{34, 54, 24, 34, 55, 25}, new int[]{20, 45, 15, 61, 46, 16}};
    private final int dataCount;
    private final int totalCount;

    public static /* synthetic */ RSBlock copy$default(RSBlock rSBlock, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = rSBlock.totalCount;
        }
        if ((i3 & 2) != 0) {
            i2 = rSBlock.dataCount;
        }
        return rSBlock.copy(i, i2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getTotalCount() {
        return this.totalCount;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getDataCount() {
        return this.dataCount;
    }

    public final RSBlock copy(int totalCount, int dataCount) {
        return new RSBlock(totalCount, dataCount);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RSBlock)) {
            return false;
        }
        RSBlock rSBlock = (RSBlock) other;
        return this.totalCount == rSBlock.totalCount && this.dataCount == rSBlock.dataCount;
    }

    public int hashCode() {
        return (this.totalCount * 31) + this.dataCount;
    }

    public String toString() {
        return "RSBlock(totalCount=" + this.totalCount + ", dataCount=" + this.dataCount + ')';
    }

    /* JADX INFO: compiled from: RSBlock.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eR\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u000f"}, d2 = {"Lqrcode/internals/RSBlock$Companion;", "", "<init>", "()V", "RS_BLOCK_TABLE", "", "", "[[I", "getRSBlocks", "Lqrcode/internals/RSBlock;", "typeNumber", "", "errorCorrectionLevel", "Lqrcode/raw/ErrorCorrectionLevel;", "(ILqrcode/raw/ErrorCorrectionLevel;)[Lqrcode/internals/RSBlock;", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final RSBlock[] getRSBlocks(int typeNumber, ErrorCorrectionLevel errorCorrectionLevel) {
            Intrinsics.checkNotNullParameter(errorCorrectionLevel, "errorCorrectionLevel");
            int[] iArr = RSBlock.RS_BLOCK_TABLE[((typeNumber - 1) * 4) + errorCorrectionLevel.ordinal()];
            if (iArr.length == 3) {
                RSBlock rSBlock = new RSBlock(iArr[1], iArr[2]);
                int i = iArr[0];
                RSBlock[] rSBlockArr = new RSBlock[i];
                for (int i2 = 0; i2 < i; i2++) {
                    rSBlockArr[i2] = rSBlock;
                }
                return rSBlockArr;
            }
            int i3 = iArr[0] + iArr[3];
            RSBlock rSBlock2 = new RSBlock(iArr[1], iArr[2]);
            RSBlock rSBlock3 = new RSBlock(iArr[4], iArr[5]);
            RSBlock[] rSBlockArr2 = new RSBlock[i3];
            int i4 = 0;
            while (i4 < i3) {
                rSBlockArr2[i4] = i4 < iArr[0] ? rSBlock2 : rSBlock3;
                i4++;
            }
            return rSBlockArr2;
        }
    }

    public RSBlock(int i, int i2) {
        this.totalCount = i;
        this.dataCount = i2;
    }

    public final int getDataCount() {
        return this.dataCount;
    }

    public final int getTotalCount() {
        return this.totalCount;
    }
}
