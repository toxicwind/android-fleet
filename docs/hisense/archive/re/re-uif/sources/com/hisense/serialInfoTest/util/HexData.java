package com.hisense.serialInfoTest.util;

/* JADX INFO: loaded from: classes.dex */
public class HexData {
    public static int decodeToInteger(byte b) {
        int i = b & 255;
        Logger.d("decodeToInteger arguments: " + i);
        return i;
    }

    public static String byteToHexString(byte b) {
        StringBuilder sb = new StringBuilder("");
        String hexString = Integer.toHexString(b & 255);
        if (hexString.length() < 2) {
            sb.append(0);
        }
        sb.append(hexString);
        Logger.d("byteToHexString: " + sb.toString().toUpperCase());
        return sb.toString().toUpperCase();
    }

    public static String bytesToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        Logger.d("bytesToHexString: " + sb.toString().toUpperCase());
        return sb.toString().toUpperCase();
    }

    public static byte[] hexStringToBytes(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        String upperCase = str.toUpperCase();
        int length = upperCase.length() / 2;
        char[] charArray = upperCase.toCharArray();
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (charToByte(charArray[i2 + 1]) | (charToByte(charArray[i2]) << 4));
            Logger.d("ddata [" + i + "]=" + ((int) bArr[i]));
        }
        return bArr;
    }

    public static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static String integerToHexString(int i) {
        String hexString;
        if (i < 0) {
            hexString = null;
        } else if (i >= 16) {
            hexString = Integer.toHexString(i);
        } else {
            hexString = "0" + Integer.toHexString(i);
        }
        Logger.d("integerToHexString: " + hexString);
        return hexString;
    }

    public static String stringToHexString(String str) {
        String str2 = "";
        for (int i = 0; i < str.length(); i++) {
            str2 = str2 + integerToHexString(str.charAt(i));
        }
        Logger.d("stringToHexString--> " + str2);
        return str2;
    }

    public static int byte2ToUnsignedShort(byte[] bArr) {
        return byte2ToUnsignedShort(bArr, 0);
    }

    public static int byte2ToUnsignedShort(byte[] bArr, int i) {
        byte b = bArr[i];
        return (bArr[i + 1] & 255) | ((b << 8) & 65280);
    }

    public static byte[] hexStringToBytes2(String str) {
        Logger.d("hexStringToBytes21 =" + str);
        if (str == null || str.equals("")) {
            return null;
        }
        int length = str.length();
        Logger.d("hexStringToBytes21 len  =" + length);
        if (length % 2 != 0) {
            str = "0" + str;
        }
        String upperCase = str.toUpperCase();
        Logger.d("hexStringToBytes2 =" + upperCase);
        int length2 = upperCase.length() / 2;
        if (upperCase.length() % 2 != 0) {
            length2++;
        }
        char[] charArray = upperCase.toCharArray();
        if (charArray == null) {
            return null;
        }
        for (int i = 0; i < charArray.length; i++) {
            Logger.d("hexChars [" + i + "]=" + charArray[i]);
        }
        byte[] bArr = new byte[length2];
        char c = 0;
        for (int i2 = 0; i2 < length2; i2++) {
            int i3 = i2 * 2;
            char c2 = charArray[i3];
            int i4 = i3 + 1;
            if (i4 < charArray.length) {
                c = charArray[i4];
            }
            bArr[i2] = (byte) ((charToByte(c2) << 4) | charToByte(c));
            Logger.d("ddata [" + i2 + "]=" + ((int) bArr[i2]));
        }
        return bArr;
    }
}
