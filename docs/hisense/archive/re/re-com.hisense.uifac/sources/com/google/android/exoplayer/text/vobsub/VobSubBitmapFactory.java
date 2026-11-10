package com.google.android.exoplayer.text.vobsub;

import android.graphics.Bitmap;
import android.util.SparseArray;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class VobSubBitmapFactory {
    SparseArray<BitmapLine> bitmapData;
    private int bottom;
    private int[] colorTable;
    private byte[] data;
    int dataSize;
    public long endTime;
    private int evenEnd;
    private int evenIndex;
    private int height;
    IntBuffer ib;
    private int left;
    int packageSize;
    private int right;
    public long startTime;
    public long startTimeUs;
    private int top;
    private int unevenEnd;
    private int unevenIndex;
    private int width;
    private int[] color4TableMap = new int[4];
    boolean align = false;
    boolean isEven = true;

    public static int bytesToInt(byte[] bArr, int i, int i2) {
        int i3 = i2 - 1;
        int i4 = 0;
        for (int i5 = i; i5 < i + i2; i5++) {
            i4 += (bArr[i5] & 255) << (i3 * 8);
            i3--;
        }
        return i4;
    }

    public static int toUInt(int i, int i2) {
        return i >= 0 ? i : i + ((int) Math.pow(2.0d, i2));
    }

    private void parserInfo() {
        int i;
        int i2;
        int i3;
        boolean z = false;
        int i4 = 2;
        this.packageSize = bytesToInt(this.data, 0, 2);
        this.dataSize = bytesToInt(this.data, 2, 2);
        MtkDebugLogUtil.log("VobSubBitmapFactory", "mPackageSize  = " + this.packageSize + " mDataSize = " + this.dataSize, 16);
        int i5 = this.dataSize;
        int i6 = 0;
        while (true) {
            int iBytesToInt = bytesToInt(this.data, i5, i4);
            int i7 = i5 + i4;
            int iBytesToInt2 = bytesToInt(this.data, i7, i4);
            int i8 = i7 + i4;
            MtkDebugLogUtil.log("VobSubBitmapFactory", "dcsqStm = " + iBytesToInt + " nextDcsq = " + iBytesToInt2 + " index = " + i8, 16);
            boolean z2 = z;
            while (!z2) {
                int uInt = toUInt(this.data[i8], 8);
                MtkDebugLogUtil.log("VobSubBitmapFactory", " mData[" + i8 + "] = " + uInt, 16);
                switch (uInt) {
                    case 0:
                    case 1:
                    case 2:
                    default:
                        i2 = 0;
                        break;
                    case 3:
                    case 4:
                        i2 = i4;
                        break;
                    case 5:
                        i2 = 6;
                        break;
                    case 6:
                        i2 = 4;
                        break;
                }
                if (i2 + i8 >= this.packageSize) {
                    MtkDebugLogUtil.log("VobSubBitmapFactory", "index+skipLen >= mPackageSize", 16);
                    if (i8 > iBytesToInt2 && i8 < (i = this.packageSize) && iBytesToInt2 < i - 4) {
                        i5 = iBytesToInt2;
                        z = false;
                        i4 = 2;
                    }
                } else {
                    int i9 = i8 + 1;
                    int uInt2 = toUInt(this.data[i8], 8);
                    MtkDebugLogUtil.log("VobSubBitmapFactory", " mData[" + i9 + "] = " + uInt2, 16);
                    switch (uInt2) {
                        case -1:
                            i8 = i9;
                            z2 = true;
                            break;
                        case 0:
                            z2 = z2;
                            i8 = i9;
                            i4 = 2;
                            break;
                        case 1:
                            this.startTime = this.startTimeUs + ((long) (((iBytesToInt << 10) / 90) * 1000));
                            MtkDebugLogUtil.log("VobSubBitmapFactory", " startTime = " + this.startTime, 16);
                            z2 = z2;
                            i8 = i9;
                            i4 = 2;
                            break;
                        case 2:
                            this.endTime = this.startTimeUs + ((long) (((iBytesToInt << 10) / 90) * 1000));
                            MtkDebugLogUtil.log("VobSubBitmapFactory", " endTime = " + this.endTime, 16);
                            z2 = z2;
                            i8 = i9;
                            i4 = 2;
                            break;
                        case 3:
                            int iBytesToInt3 = bytesToInt(this.data, i9, i4);
                            i9 += 2;
                            i6 = iBytesToInt3;
                            i8 = i9;
                            i4 = 2;
                            break;
                        case 4:
                            bytesToInt(this.data, i9, i4);
                            i9 += 2;
                            i8 = i9;
                            i4 = 2;
                            break;
                        case 5:
                            int[] iArr = new int[6];
                            int i10 = i9;
                            int i11 = 0;
                            while (true) {
                                int i12 = i9 + 6;
                                if (i10 < i12) {
                                    iArr[i11] = toUInt(this.data[i10], 8);
                                    i11++;
                                    i10++;
                                } else {
                                    this.left = (iArr[0] << 4) + (iArr[1] >>> 4);
                                    this.top = (iArr[3] << 4) + (iArr[4] >>> 4);
                                    this.right = ((iArr[1] & 15) << 8) + iArr[i4];
                                    this.bottom = ((iArr[4] & 15) << 8) + iArr[5];
                                    MtkDebugLogUtil.log("VobSubBitmapFactory", " i4_right = " + this.right + " i4_left = " + this.left, 16);
                                    MtkDebugLogUtil.log("VobSubBitmapFactory", " i4_bottom = " + this.bottom + " i4_top = " + this.top, 16);
                                    this.width = (this.right - this.left) + 1;
                                    this.height = (this.bottom - this.top) + 1;
                                    MtkDebugLogUtil.log("VobSubBitmapFactory", " width = " + this.width + " height = " + this.height, 16);
                                    if (this.width <= i4 || this.height <= i4) {
                                        z2 = z2;
                                        i8 = i9;
                                    } else {
                                        i8 = i12;
                                    }
                                    i4 = 2;
                                }
                                break;
                            }
                            break;
                        case 6:
                            this.evenIndex = bytesToInt(this.data, i9, i4);
                            int i13 = i9 + 2;
                            this.unevenIndex = bytesToInt(this.data, i13, i4);
                            int i14 = i13 + i4;
                            MtkDebugLogUtil.log("VobSubBitmapFactory", "evenIndex = " + this.evenIndex + " unevenIndex = " + this.unevenIndex + " mDataSize = " + this.dataSize, 16);
                            int i15 = this.evenIndex;
                            int i16 = this.unevenIndex;
                            if (i15 < i16 && i16 < (i3 = this.dataSize)) {
                                this.evenEnd = i16;
                                this.unevenEnd = i3;
                                MtkDebugLogUtil.log("VobSubBitmapFactory", "eventOff = " + (i16 - i15) + " totalOff = " + (i3 - i15), 16);
                            }
                            i8 = i14;
                            i4 = 2;
                            break;
                        default:
                            i8 = i9;
                            z2 = true;
                            i4 = 2;
                            break;
                    }
                }
            }
            if (i8 > iBytesToInt2) {
            }
        }
        for (int i17 = 0; i17 < 4; i17++) {
            if (i6 > 0) {
                int i18 = (i6 >> (i17 << 2)) & 15;
                MtkDebugLogUtil.log("VobSubBitmapFactory", "color table index = " + i18 + " color = " + Integer.toHexString(this.colorTable[i18]), 16);
                this.color4TableMap[i17] = this.colorTable[i18];
            } else {
                this.color4TableMap[i17] = this.colorTable[i17];
            }
        }
        needDefaultColorTable();
    }

    private void needDefaultColorTable() {
        MtkDebugLogUtil.log("VobSubBitmapFactory", "Default color table 1 = " + Integer.toHexString(this.color4TableMap[0]) + " 2 = " + Integer.toHexString(this.color4TableMap[1]) + " 3 = " + Integer.toHexString(this.color4TableMap[2]) + " 4 = " + Integer.toHexString(this.color4TableMap[3]), 16);
        int[] iArr = this.color4TableMap;
        iArr[0] = 0;
        if (iArr[1] == 0) {
            iArr[1] = -1;
        } else {
            iArr[1] = (iArr[1] & (-16777216)) > 0 ? iArr[1] : iArr[1] | (-16777216);
        }
        int[] iArr2 = this.color4TableMap;
        iArr2[2] = (iArr2[2] & (-16777216)) > 0 ? iArr2[2] : iArr2[2] | (-16777216);
        int[] iArr3 = this.color4TableMap;
        iArr3[3] = (iArr3[3] & (-16777216)) > 0 ? iArr3[3] : iArr3[3] | (-16777216);
        this.color4TableMap[3] = 0;
        MtkDebugLogUtil.log("VobSubBitmapFactory", "Adapter color table 1 = " + Integer.toHexString(this.color4TableMap[0]) + " 2 = " + Integer.toHexString(this.color4TableMap[1]) + " 3 = " + Integer.toHexString(this.color4TableMap[2]) + " 4 = " + Integer.toHexString(this.color4TableMap[3]), 16);
    }

    public int readNibble() {
        int uInt = (toUInt(this.data[this.isEven ? this.evenIndex : this.unevenIndex], 8) >> (this.align ? 0 : 4)) & 15;
        if (this.align) {
            if (this.isEven) {
                this.evenIndex++;
            } else {
                this.unevenIndex++;
            }
        }
        this.align = !this.align;
        return uInt;
    }

    private void parserData(Bitmap bitmap) {
        int i = this.width * this.height;
        this.bitmapData = new SparseArray<>();
        ArrayList arrayList = new ArrayList();
        this.bitmapData.put(0, new BitmapLine(this, arrayList));
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if ((!this.isEven || this.evenIndex >= this.evenEnd) && (this.isEven || this.unevenIndex >= this.unevenEnd)) {
                return;
            }
            int nibble = readNibble();
            if (nibble >= 4 || (nibble = (nibble << 4) | readNibble()) >= 16 || (nibble = (nibble << 4) | readNibble()) >= 64 || (nibble = (nibble << 4) | readNibble()) >= 256) {
                int i4 = nibble >> 2;
                int i5 = nibble & 3;
                int i6 = i2 + i4;
                int i7 = this.width;
                if (i6 > i7) {
                    i4 = i7 - i2;
                }
                int i8 = this.width * i3;
                arrayList.add(new ColorItem(this, i4, i5));
                i2 += i4;
                if (i8 + i2 >= i) {
                    MtkDebugLogUtil.log("VobSubBitmapFactory", "finish  1 break", 16);
                    return;
                } else if (i2 < this.width) {
                    continue;
                }
            }
            int i9 = nibble & 3;
            int i10 = this.width;
            int i11 = i10 - i2;
            if (i11 > 0) {
                arrayList.add(new ColorItem(this, i11, i9));
                if ((i10 * i3) + i2 + i11 >= i) {
                    MtkDebugLogUtil.log("VobSubBitmapFactory", "finish  2 break", 16);
                    return;
                }
            }
            if (this.align) {
                MtkDebugLogUtil.log("VobSubBitmapFactory", "need read left byte~~", 16);
                readNibble();
            }
            i3++;
            this.isEven = !this.isEven;
            arrayList = new ArrayList();
            this.bitmapData.put(i3, new BitmapLine(this, arrayList));
            i2 = 0;
        }
    }

    public VobSubBitmapFactory(int[] iArr, long j, byte[] bArr) {
        this.colorTable = new int[16];
        this.colorTable = iArr;
        this.data = bArr;
        this.startTimeUs = j;
        init();
    }

    private void init() {
        parserInfo();
    }

    public Bitmap createBitmap() {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888);
        parserData(bitmapCreateBitmap);
        this.ib = IntBuffer.allocate(this.width * this.height);
        for (int i = 0; i < this.height; i++) {
            for (ColorItem colorItem : this.bitmapData.get(i).items) {
                for (int i2 = 0; i2 < colorItem.colorNum; i2++) {
                    this.ib.put(this.color4TableMap[colorItem.colorIndex]);
                }
            }
        }
        this.ib.rewind();
        bitmapCreateBitmap.copyPixelsFromBuffer(this.ib);
        return bitmapCreateBitmap;
    }

    class BitmapLine {
        public List<ColorItem> items;

        public BitmapLine(VobSubBitmapFactory vobSubBitmapFactory, List<ColorItem> list) {
            this.items = list;
        }

        public String toString() {
            return "BitmapLine [items=" + this.items + "]";
        }
    }

    class ColorItem {
        public int colorIndex;
        public int colorNum;

        public ColorItem(VobSubBitmapFactory vobSubBitmapFactory, int i, int i2) {
            this.colorNum = i;
            this.colorIndex = i2;
        }

        public String toString() {
            return "ColorItem [colorNum=" + this.colorNum + ", colorIndex=" + this.colorIndex + "]";
        }
    }
}
