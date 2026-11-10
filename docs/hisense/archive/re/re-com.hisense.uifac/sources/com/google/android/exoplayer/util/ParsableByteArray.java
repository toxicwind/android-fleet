package com.google.android.exoplayer.util;

import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public final class ParsableByteArray {
    private Charset charset;
    public byte[] data;
    private int limit;
    private int position;

    public ParsableByteArray() {
    }

    public ParsableByteArray(int i) {
        byte[] bArr = new byte[i];
        this.data = bArr;
        this.limit = bArr.length;
    }

    public ParsableByteArray(byte[] bArr) {
        this.data = bArr;
        this.limit = bArr.length;
    }

    public ParsableByteArray(byte[] bArr, int i) {
        this.data = bArr;
        this.limit = i;
    }

    public void reset(int i) {
        reset(capacity() < i ? new byte[i] : this.data, i);
    }

    public void reset(byte[] bArr, int i) {
        this.data = bArr;
        this.limit = i;
        this.position = 0;
    }

    public void reset() {
        this.position = 0;
        this.limit = 0;
    }

    public int bytesLeft() {
        return this.limit - this.position;
    }

    public int limit() {
        return this.limit;
    }

    public void setLimit(int i) {
        Assertions.checkArgument(i >= 0 && i <= this.data.length);
        this.limit = i;
    }

    public int getPosition() {
        return this.position;
    }

    public int capacity() {
        byte[] bArr = this.data;
        if (bArr == null) {
            return 0;
        }
        return bArr.length;
    }

    public void setPosition(int i) {
        Assertions.checkArgument(i >= 0 && i <= this.limit);
        this.position = i;
    }

    public void skipBytes(int i) {
        setPosition(this.position + i);
    }

    public void readBytes(ParsableBitArray parsableBitArray, int i) {
        readBytes(parsableBitArray.data, 0, i);
        parsableBitArray.setPosition(0);
    }

    public int readBytes(byte[] bArr, int i, int i2) {
        System.arraycopy(this.data, this.position, bArr, i, i2);
        this.position += i2;
        return i;
    }

    public int readUnsignedByte() {
        byte[] bArr = this.data;
        int i = this.position;
        this.position = i + 1;
        return bArr[i] & 255;
    }

    public int readUnsignedShort() {
        byte[] bArr = this.data;
        int i = this.position;
        int i2 = i + 1;
        this.position = i2;
        int i3 = (bArr[i] & 255) << 8;
        this.position = i2 + 1;
        return (bArr[i2] & 255) | i3;
    }

    public int readLittleEndianUnsignedShort() {
        byte[] bArr = this.data;
        int i = this.position;
        int i2 = i + 1;
        this.position = i2;
        int i3 = bArr[i] & 255;
        this.position = i2 + 1;
        return ((bArr[i2] & 255) << 8) | i3;
    }

    public short readShort() {
        byte[] bArr = this.data;
        int i = this.position;
        int i2 = i + 1;
        this.position = i2;
        int i3 = (bArr[i] & 255) << 8;
        this.position = i2 + 1;
        return (short) ((bArr[i2] & 255) | i3);
    }

    public int readUnsignedInt24() {
        byte[] bArr = this.data;
        int i = this.position;
        int i2 = i + 1;
        this.position = i2;
        int i3 = (bArr[i] & 255) << 16;
        int i4 = i2 + 1;
        this.position = i4;
        int i5 = i3 | ((bArr[i2] & 255) << 8);
        this.position = i4 + 1;
        return (bArr[i4] & 255) | i5;
    }

    public long readUnsignedInt() {
        byte[] bArr = this.data;
        int i = this.position;
        int i2 = i + 1;
        this.position = i2;
        long j = (((long) bArr[i]) & 255) << 24;
        int i3 = i2 + 1;
        this.position = i3;
        long j2 = j | ((((long) bArr[i2]) & 255) << 16);
        int i4 = i3 + 1;
        this.position = i4;
        long j3 = j2 | ((((long) bArr[i3]) & 255) << 8);
        this.position = i4 + 1;
        return j3 | (((long) bArr[i4]) & 255);
    }

    public long readLittleEndianUnsignedInt() {
        byte[] bArr = this.data;
        int i = this.position;
        int i2 = i + 1;
        this.position = i2;
        long j = ((long) bArr[i]) & 255;
        int i3 = i2 + 1;
        this.position = i3;
        long j2 = j | ((((long) bArr[i2]) & 255) << 8);
        int i4 = i3 + 1;
        this.position = i4;
        long j3 = j2 | ((((long) bArr[i3]) & 255) << 16);
        this.position = i4 + 1;
        return j3 | ((((long) bArr[i4]) & 255) << 24);
    }

    public int readInt() {
        byte[] bArr = this.data;
        int i = this.position;
        int i2 = i + 1;
        this.position = i2;
        int i3 = (bArr[i] & 255) << 24;
        int i4 = i2 + 1;
        this.position = i4;
        int i5 = i3 | ((bArr[i2] & 255) << 16);
        int i6 = i4 + 1;
        this.position = i6;
        int i7 = i5 | ((bArr[i4] & 255) << 8);
        this.position = i6 + 1;
        return (bArr[i6] & 255) | i7;
    }

    public int readLittleEndianInt() {
        byte[] bArr = this.data;
        int i = this.position;
        int i2 = i + 1;
        this.position = i2;
        int i3 = bArr[i] & 255;
        int i4 = i2 + 1;
        this.position = i4;
        int i5 = i3 | ((bArr[i2] & 255) << 8);
        int i6 = i4 + 1;
        this.position = i6;
        int i7 = i5 | ((bArr[i4] & 255) << 16);
        this.position = i6 + 1;
        return ((bArr[i6] & 255) << 24) | i7;
    }

    public long readLong() {
        byte[] bArr = this.data;
        int i = this.position;
        int i2 = i + 1;
        this.position = i2;
        long j = (((long) bArr[i]) & 255) << 56;
        int i3 = i2 + 1;
        this.position = i3;
        long j2 = j | ((((long) bArr[i2]) & 255) << 48);
        int i4 = i3 + 1;
        this.position = i4;
        long j3 = j2 | ((((long) bArr[i3]) & 255) << 40);
        int i5 = i4 + 1;
        this.position = i5;
        long j4 = j3 | ((((long) bArr[i4]) & 255) << 32);
        int i6 = i5 + 1;
        this.position = i6;
        long j5 = j4 | ((((long) bArr[i5]) & 255) << 24);
        int i7 = i6 + 1;
        this.position = i7;
        long j6 = j5 | ((((long) bArr[i6]) & 255) << 16);
        int i8 = i7 + 1;
        this.position = i8;
        long j7 = j6 | ((((long) bArr[i7]) & 255) << 8);
        this.position = i8 + 1;
        return j7 | (((long) bArr[i8]) & 255);
    }

    public long readLittleEndianLong() {
        byte[] bArr = this.data;
        int i = this.position;
        int i2 = i + 1;
        this.position = i2;
        long j = ((long) bArr[i]) & 255;
        int i3 = i2 + 1;
        this.position = i3;
        long j2 = j | ((((long) bArr[i2]) & 255) << 8);
        int i4 = i3 + 1;
        this.position = i4;
        long j3 = j2 | ((((long) bArr[i3]) & 255) << 16);
        int i5 = i4 + 1;
        this.position = i5;
        long j4 = j3 | ((((long) bArr[i4]) & 255) << 24);
        int i6 = i5 + 1;
        this.position = i6;
        long j5 = j4 | ((((long) bArr[i5]) & 255) << 32);
        int i7 = i6 + 1;
        this.position = i7;
        long j6 = j5 | ((((long) bArr[i6]) & 255) << 40);
        int i8 = i7 + 1;
        this.position = i8;
        long j7 = j6 | ((((long) bArr[i7]) & 255) << 48);
        this.position = i8 + 1;
        return j7 | ((((long) bArr[i8]) & 255) << 56);
    }

    public int readUnsignedFixedPoint1616() {
        byte[] bArr = this.data;
        int i = this.position;
        int i2 = i + 1;
        this.position = i2;
        int i3 = (bArr[i] & 255) << 8;
        int i4 = i2 + 1;
        this.position = i4;
        int i5 = (bArr[i2] & 255) | i3;
        this.position = i4 + 2;
        return i5;
    }

    public int readSynchSafeInt() {
        return readUnsignedByte() | (readUnsignedByte() << 21) | (readUnsignedByte() << 14) | (readUnsignedByte() << 7);
    }

    public int readUnsignedIntToInt() {
        int i = readInt();
        if (i >= 0) {
            return i;
        }
        throw new IllegalStateException("Top bit not zero: " + i);
    }

    public int readLittleEndianUnsignedIntToInt() {
        int littleEndianInt = readLittleEndianInt();
        if (littleEndianInt >= 0) {
            return littleEndianInt;
        }
        throw new IllegalStateException("Top bit not zero: " + littleEndianInt);
    }

    public long readUnsignedLongToLong() {
        long j = readLong();
        if (j >= 0) {
            return j;
        }
        throw new IllegalStateException("Top bit not zero: " + j);
    }

    public double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    public String readString(int i) {
        return readString(i, Charset.defaultCharset());
    }

    public String readString(int i, Charset charset) {
        String str = new String(this.data, this.position, i, charset);
        this.position += i;
        return str;
    }

    public String readLine() {
        String str;
        if (bytesLeft() == 0) {
            return null;
        }
        int i = this.position;
        while (i < this.limit) {
            byte[] bArr = this.data;
            if (bArr[i] == 10 || bArr[i] == 13) {
                break;
            }
            i++;
        }
        int i2 = this.position;
        if (i - i2 >= 3) {
            byte[] bArr2 = this.data;
            if (bArr2[i2] == -17 && bArr2[i2 + 1] == -69 && bArr2[i2 + 2] == -65) {
                this.position = i2 + 3;
            }
        }
        if (this.charset != null) {
            byte[] bArr3 = this.data;
            int i3 = this.position;
            str = new String(bArr3, i3, i - i3, this.charset);
        } else {
            byte[] bArr4 = this.data;
            int i4 = this.position;
            str = new String(bArr4, i4, i - i4);
        }
        this.position = i;
        int i5 = this.limit;
        if (i == i5) {
            return str;
        }
        if (this.data[i] == 13) {
            int i6 = i + 1;
            this.position = i6;
            if (i6 == i5) {
                return str;
            }
        }
        byte[] bArr5 = this.data;
        int i7 = this.position;
        if (bArr5[i7] == 10) {
            this.position = i7 + 1;
        }
        return str;
    }

    public long readUTF8EncodedLong() {
        int i;
        int i2;
        long j = this.data[this.position];
        int i3 = 7;
        while (true) {
            if (i3 >= 0) {
                int i4 = 1 << i3;
                if ((((long) i4) & j) == 0) {
                    if (i3 < 6) {
                        j &= (long) (i4 - 1);
                        i2 = 7 - i3;
                        break;
                    }
                    if (i3 == 7) {
                        i2 = 1;
                        break;
                    }
                } else {
                    i3--;
                }
            }
            i2 = 0;
            break;
        }
        if (i2 == 0) {
            throw new NumberFormatException("Invalid UTF-8 sequence first byte: " + j);
        }
        for (i = 1; i < i2; i++) {
            byte b = this.data[this.position + i];
            if ((b & 192) != 128) {
                throw new NumberFormatException("Invalid UTF-8 sequence continuation byte: " + j);
            }
            j = (j << 6) | ((long) (b & 63));
        }
        this.position += i2;
        return j;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }
}
