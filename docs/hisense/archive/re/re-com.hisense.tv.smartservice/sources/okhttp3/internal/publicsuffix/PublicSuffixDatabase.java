package okhttp3.internal.publicsuffix;

import com.google.common.net.InetAddresses;
import d.i;
import d.n;
import d.t;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.IDN;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.internal.Util;
import okhttp3.internal.platform.Platform;

/* JADX INFO: loaded from: classes2.dex */
public final class PublicSuffixDatabase {
    public static final byte EXCEPTION_MARKER = 33;
    public static final String PUBLIC_SUFFIX_RESOURCE = "publicsuffixes.gz";
    public byte[] publicSuffixExceptionListBytes;
    public byte[] publicSuffixListBytes;
    public static final byte[] WILDCARD_LABEL = {42};
    public static final String[] EMPTY_RULE = new String[0];
    public static final String[] PREVAILING_RULE = {"*"};
    public static final PublicSuffixDatabase instance = new PublicSuffixDatabase();
    public final AtomicBoolean listRead = new AtomicBoolean(false);
    public final CountDownLatch readCompleteLatch = new CountDownLatch(1);

    public static String binarySearchBytes(byte[] bArr, byte[][] bArr2, int i) {
        int i2;
        int i3;
        int i4;
        int length = bArr.length;
        int i5 = 0;
        while (i5 < length) {
            int i6 = (i5 + length) / 2;
            while (i6 > -1 && bArr[i6] != 10) {
                i6--;
            }
            int i7 = i6 + 1;
            int i8 = 1;
            while (true) {
                i2 = i7 + i8;
                if (bArr[i2] == 10) {
                    break;
                }
                i8++;
            }
            int i9 = i2 - i7;
            int i10 = i;
            boolean z = false;
            int i11 = 0;
            int i12 = 0;
            while (true) {
                if (z) {
                    i3 = 46;
                    z = false;
                } else {
                    i3 = bArr2[i10][i11] & 255;
                }
                i4 = i3 - (bArr[i7 + i12] & 255);
                if (i4 == 0) {
                    i12++;
                    i11++;
                    if (i12 == i9) {
                        break;
                    }
                    if (bArr2[i10].length == i11) {
                        if (i10 == bArr2.length - 1) {
                            break;
                        }
                        i10++;
                        i11 = -1;
                        z = true;
                    }
                } else {
                    break;
                }
            }
            if (i4 >= 0) {
                if (i4 <= 0) {
                    int i13 = i9 - i12;
                    int length2 = bArr2[i10].length - i11;
                    while (true) {
                        i10++;
                        if (i10 >= bArr2.length) {
                            break;
                        }
                        length2 += bArr2[i10].length;
                    }
                    if (length2 >= i13) {
                        if (length2 <= i13) {
                            return new String(bArr, i7, i9, Util.UTF_8);
                        }
                    }
                }
                i5 = i2 + 1;
            }
            length = i7 - 1;
        }
        return null;
    }

    private String[] findMatchingRule(String[] strArr) {
        String strBinarySearchBytes;
        String strBinarySearchBytes2;
        String strBinarySearchBytes3;
        int i = 0;
        if (this.listRead.get() || !this.listRead.compareAndSet(false, true)) {
            try {
                this.readCompleteLatch.await();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        } else {
            readTheListUninterruptibly();
        }
        synchronized (this) {
            if (this.publicSuffixListBytes == null) {
                throw new IllegalStateException("Unable to load publicsuffixes.gz resource from the classpath.");
            }
        }
        byte[][] bArr = new byte[strArr.length][];
        for (int i2 = 0; i2 < strArr.length; i2++) {
            bArr[i2] = strArr[i2].getBytes(Util.UTF_8);
        }
        int i3 = 0;
        while (true) {
            if (i3 >= bArr.length) {
                strBinarySearchBytes = null;
                break;
            }
            strBinarySearchBytes = binarySearchBytes(this.publicSuffixListBytes, bArr, i3);
            if (strBinarySearchBytes != null) {
                break;
            }
            i3++;
        }
        if (bArr.length <= 1) {
            strBinarySearchBytes2 = null;
            break;
        }
        byte[][] bArr2 = (byte[][]) bArr.clone();
        int i4 = 0;
        while (true) {
            if (i4 >= bArr2.length - 1) {
                strBinarySearchBytes2 = null;
                break;
            }
            bArr2[i4] = WILDCARD_LABEL;
            strBinarySearchBytes2 = binarySearchBytes(this.publicSuffixListBytes, bArr2, i4);
            if (strBinarySearchBytes2 != null) {
                break;
            }
            i4++;
        }
        if (strBinarySearchBytes2 == null) {
            strBinarySearchBytes3 = null;
            break;
        }
        while (true) {
            if (i >= bArr.length - 1) {
                strBinarySearchBytes3 = null;
                break;
            }
            strBinarySearchBytes3 = binarySearchBytes(this.publicSuffixExceptionListBytes, bArr, i);
            if (strBinarySearchBytes3 != null) {
                break;
            }
            i++;
        }
        if (strBinarySearchBytes3 != null) {
            return ("!" + strBinarySearchBytes3).split("\\.");
        }
        if (strBinarySearchBytes == null && strBinarySearchBytes2 == null) {
            return PREVAILING_RULE;
        }
        String[] strArrSplit = strBinarySearchBytes != null ? strBinarySearchBytes.split("\\.") : EMPTY_RULE;
        String[] strArrSplit2 = strBinarySearchBytes2 != null ? strBinarySearchBytes2.split("\\.") : EMPTY_RULE;
        return strArrSplit.length > strArrSplit2.length ? strArrSplit : strArrSplit2;
    }

    public static PublicSuffixDatabase get() {
        return instance;
    }

    private void readTheList() {
        InputStream resourceAsStream = PublicSuffixDatabase.class.getResourceAsStream(PUBLIC_SUFFIX_RESOURCE);
        if (resourceAsStream == null) {
            return;
        }
        i iVarA = t.a(new n(t.a(resourceAsStream)));
        try {
            byte[] bArr = new byte[iVarA.readInt()];
            iVarA.readFully(bArr);
            byte[] bArr2 = new byte[iVarA.readInt()];
            iVarA.readFully(bArr2);
            Util.closeQuietly(iVarA);
            synchronized (this) {
                this.publicSuffixListBytes = bArr;
                this.publicSuffixExceptionListBytes = bArr2;
            }
            this.readCompleteLatch.countDown();
        } catch (Throwable th) {
            Util.closeQuietly(iVarA);
            throw th;
        }
    }

    private void readTheListUninterruptibly() {
        boolean z = false;
        while (true) {
            try {
                try {
                    readTheList();
                    break;
                } catch (InterruptedIOException unused) {
                    Thread.interrupted();
                    z = true;
                } catch (IOException e2) {
                    Platform.PLATFORM.log(5, "Failed to read public suffix list", e2);
                    if (z) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    return;
                }
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
    }

    public String getEffectiveTldPlusOne(String str) {
        int length;
        int length2;
        if (str == null) {
            throw new NullPointerException("domain == null");
        }
        String[] strArrSplit = IDN.toUnicode(str).split("\\.");
        String[] strArrFindMatchingRule = findMatchingRule(strArrSplit);
        if (strArrSplit.length == strArrFindMatchingRule.length && strArrFindMatchingRule[0].charAt(0) != '!') {
            return null;
        }
        if (strArrFindMatchingRule[0].charAt(0) == '!') {
            length = strArrSplit.length;
            length2 = strArrFindMatchingRule.length;
        } else {
            length = strArrSplit.length;
            length2 = strArrFindMatchingRule.length + 1;
        }
        StringBuilder sb = new StringBuilder();
        String[] strArrSplit2 = str.split("\\.");
        for (int i = length - length2; i < strArrSplit2.length; i++) {
            sb.append(strArrSplit2[i]);
            sb.append(InetAddresses.IPV4_DELIMITER);
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public void setListBytes(byte[] bArr, byte[] bArr2) {
        this.publicSuffixListBytes = bArr;
        this.publicSuffixExceptionListBytes = bArr2;
        this.listRead.set(true);
        this.readCompleteLatch.countDown();
    }
}
