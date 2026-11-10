package a.b.a.a.d;

import android.text.TextUtils;
import android.util.Log;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class c {
    public static void a(String str, String str2) {
        if (Log.isLoggable(str, 3)) {
            Log.d(str, str2);
        }
    }

    public static String b(byte b) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        return new String(new char[]{cArr[(b >>> 4) & 15], cArr[b & 15]});
    }

    public static String c(byte[] bArr) {
        String str = "";
        for (byte b : bArr) {
            str = str + b(b);
        }
        return str;
    }

    public static String d() {
        Locale locale = Locale.getDefault();
        String language = locale.getLanguage();
        String str = language + "_" + locale.getCountry();
        if (str.equals(Locale.SIMPLIFIED_CHINESE.toString())) {
            return "0";
        }
        if (str.equals(Locale.TRADITIONAL_CHINESE.toString())) {
            return "8";
        }
        if (language.equalsIgnoreCase(Locale.ENGLISH.toString())) {
            return "1";
        }
        if (language.equalsIgnoreCase(Locale.FRENCH.toString())) {
            return "2";
        }
        return language.equalsIgnoreCase("es") ? "6" : "0";
    }

    public static byte[] e(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        byte[] bytes = str.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] f(byte[] bArr, byte[] bArr2) {
        char[] cArr = new char[256];
        g(cArr, bArr, bArr.length);
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < bArr2.length; i3++) {
            i = (i + 1) % 256;
            i2 = (i2 + cArr[i]) % 256;
            bArr2[i3] = (byte) (cArr[(cArr[i] + cArr[i2]) % 256] ^ bArr2[i3]);
        }
        return bArr2;
    }

    public static void g(char[] cArr, byte[] bArr, int i) {
        char[] cArr2 = new char[256];
        for (int i2 = 0; i2 < 256; i2++) {
            cArr[i2] = (char) i2;
            cArr2[i2] = (char) bArr[i2 % i];
        }
        int i3 = 0;
        for (int i4 = 0; i4 < 256; i4++) {
            i3 = ((i3 + cArr[i4]) + cArr2[i4]) % 256;
            char c = cArr[i4];
            cArr[i4] = cArr[i3];
            cArr[i3] = c;
        }
    }

    public static String h(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return new String(str.getBytes(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
