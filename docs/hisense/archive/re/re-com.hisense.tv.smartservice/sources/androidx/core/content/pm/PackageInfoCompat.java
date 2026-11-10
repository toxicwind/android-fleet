package androidx.core.content.pm;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.Size;
import b.a.a.a.a;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class PackageInfoCompat {

    @RequiresApi(28)
    private static class Api28Impl {
        @Nullable
        public static Signature[] getApkContentsSigners(@NonNull SigningInfo signingInfo) {
            return signingInfo.getApkContentsSigners();
        }

        @Nullable
        public static Signature[] getSigningCertificateHistory(@NonNull SigningInfo signingInfo) {
            return signingInfo.getSigningCertificateHistory();
        }

        public static boolean hasMultipleSigners(@NonNull SigningInfo signingInfo) {
            return signingInfo.hasMultipleSigners();
        }

        public static boolean hasSigningCertificate(@NonNull PackageManager packageManager, @NonNull String packageName, @NonNull byte[] bytes, int type) {
            return packageManager.hasSigningCertificate(packageName, bytes, type);
        }
    }

    public static boolean byteArrayContains(@NonNull byte[][] array, @NonNull byte[] expected) {
        for (byte[] bArr : array) {
            if (Arrays.equals(expected, bArr)) {
                return true;
            }
        }
        return false;
    }

    public static byte[] computeSHA256Digest(byte[] bytes) {
        try {
            return MessageDigest.getInstance("SHA256").digest(bytes);
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException("Device doesn't support SHA256 cert checking", e2);
        }
    }

    public static long getLongVersionCode(@NonNull PackageInfo info) {
        return Build.VERSION.SDK_INT >= 28 ? info.getLongVersionCode() : info.versionCode;
    }

    @NonNull
    public static List<Signature> getSignatures(@NonNull PackageManager packageManager, @NonNull String packageName) {
        Signature[] apkContentsSigners;
        if (Build.VERSION.SDK_INT >= 28) {
            SigningInfo signingInfo = packageManager.getPackageInfo(packageName, 134217728).signingInfo;
            apkContentsSigners = signingInfo.hasMultipleSigners() ? signingInfo.getApkContentsSigners() : signingInfo.getSigningCertificateHistory();
        } else {
            apkContentsSigners = packageManager.getPackageInfo(packageName, 64).signatures;
        }
        return apkContentsSigners == null ? Collections.emptyList() : Arrays.asList(apkContentsSigners);
    }

    public static boolean hasSignatures(@NonNull PackageManager packageManager, @NonNull String packageName, @NonNull @Size(min = 1) Map<byte[], Integer> certificatesAndType, boolean matchExact) {
        if (certificatesAndType.isEmpty()) {
            return false;
        }
        Set<byte[]> setKeySet = certificatesAndType.keySet();
        for (byte[] bArr : setKeySet) {
            if (bArr == null) {
                throw new IllegalArgumentException(a.a("Cert byte array cannot be null when verifying ", packageName));
            }
            Integer num = certificatesAndType.get(bArr);
            if (num == null) {
                throw new IllegalArgumentException(a.a("Type must be specified for cert when verifying ", packageName));
            }
            int iIntValue = num.intValue();
            if (iIntValue != 0 && iIntValue != 1) {
                throw new IllegalArgumentException("Unsupported certificate type " + num + " when verifying " + packageName);
            }
        }
        List<Signature> signatures = getSignatures(packageManager, packageName);
        if (!matchExact && Build.VERSION.SDK_INT >= 28) {
            for (byte[] bArr2 : setKeySet) {
                if (!packageManager.hasSigningCertificate(packageName, bArr2, certificatesAndType.get(bArr2).intValue())) {
                    return false;
                }
            }
            return true;
        }
        if (signatures.size() != 0 && certificatesAndType.size() <= signatures.size() && (!matchExact || certificatesAndType.size() == signatures.size())) {
            byte[][] bArr3 = null;
            if (certificatesAndType.containsValue(1)) {
                bArr3 = new byte[signatures.size()][];
                for (int i = 0; i < signatures.size(); i++) {
                    bArr3[i] = computeSHA256Digest(signatures.get(i).toByteArray());
                }
            }
            Iterator<byte[]> it = setKeySet.iterator();
            if (it.hasNext()) {
                byte[] next = it.next();
                Integer num2 = certificatesAndType.get(next);
                int iIntValue2 = num2.intValue();
                if (iIntValue2 != 0) {
                    if (iIntValue2 != 1) {
                        throw new IllegalArgumentException(a.a("Unsupported certificate type ", num2));
                    }
                    if (!byteArrayContains(bArr3, next)) {
                        return false;
                    }
                } else if (!signatures.contains(new Signature(next))) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }
}
