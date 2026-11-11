package androidx.media3.datasource;

import androidx.media3.common.util.Util;
import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes4.dex */
public final class AesFlushingCipher {
    private final int blockSize;
    private final Cipher cipher;
    private final byte[] flushedBlock;
    private int pendingXorBytes;
    private final byte[] zerosBlock;

    public AesFlushingCipher(int i, byte[] bArr, String str, long j) {
        this(i, bArr, getFNV64Hash(str), j);
    }

    public AesFlushingCipher(int i, byte[] bArr, long j, long j2) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
            this.cipher = cipher;
            int blockSize = cipher.getBlockSize();
            this.blockSize = blockSize;
            this.zerosBlock = new byte[blockSize];
            this.flushedBlock = new byte[blockSize];
            long j3 = j2 / ((long) blockSize);
            int i2 = (int) (j2 % ((long) blockSize));
            cipher.init(i, new SecretKeySpec(bArr, Util.splitAtFirst(cipher.getAlgorithm(), "/")[0]), new IvParameterSpec(getInitializationVector(j, j3)));
            if (i2 != 0) {
                updateInPlace(new byte[i2], 0, i2);
            }
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateInPlace(byte[] bArr, int i, int i2) {
        update(bArr, i, i2, bArr, i);
    }

    public void update(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        do {
            int i4 = this.pendingXorBytes;
            if (i4 > 0) {
                bArr2[i3] = (byte) (bArr[i] ^ this.flushedBlock[this.blockSize - i4]);
                i3++;
                i++;
                this.pendingXorBytes = i4 - 1;
                i2--;
            } else {
                int iNonFlushingUpdate = nonFlushingUpdate(bArr, i, i2, bArr2, i3);
                if (i2 == iNonFlushingUpdate) {
                    return;
                }
                int i5 = i2 - iNonFlushingUpdate;
                int i6 = 0;
                Preconditions.checkState(i5 < this.blockSize);
                int i7 = i3 + iNonFlushingUpdate;
                int i8 = this.blockSize - i5;
                this.pendingXorBytes = i8;
                Preconditions.checkState(nonFlushingUpdate(this.zerosBlock, 0, i8, this.flushedBlock, 0) == this.blockSize);
                while (i6 < i5) {
                    bArr2[i7] = this.flushedBlock[i6];
                    i6++;
                    i7++;
                }
                return;
            }
        } while (i2 != 0);
    }

    private int nonFlushingUpdate(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        try {
            return this.cipher.update(bArr, i, i2, bArr2, i3);
        } catch (ShortBufferException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] getInitializationVector(long j, long j2) {
        return ByteBuffer.allocate(16).putLong(j).putLong(j2).array();
    }

    private static long getFNV64Hash(String str) {
        long j = 0;
        if (str == null) {
            return 0L;
        }
        for (int i = 0; i < str.length(); i++) {
            long jCharAt = j ^ ((long) str.charAt(i));
            j = jCharAt + (jCharAt << 1) + (jCharAt << 4) + (jCharAt << 5) + (jCharAt << 7) + (jCharAt << 8) + (jCharAt << 40);
        }
        return j;
    }
}
