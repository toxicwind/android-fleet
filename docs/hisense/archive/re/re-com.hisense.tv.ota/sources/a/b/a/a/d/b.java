package a.b.a.a.d;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f3a = "007";
    private static String b = "0123456789ABCDEFGHJKLMNPQRSTUVWXYZ";

    private static String a() {
        String str = "35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10) + (Build.CPU_ABI.length() % 10) + (Build.DEVICE.length() % 10) + (Build.DISPLAY.length() % 10) + (Build.HOST.length() % 10) + (Build.ID.length() % 10) + (Build.MANUFACTURER.length() % 10) + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10) + (Build.TAGS.length() % 10) + (Build.TYPE.length() % 10) + (Build.USER.length() % 10);
        try {
            return new UUID(str.hashCode(), (Build.VERSION.SDK_INT >= 26 ? Build.getSerial() : Build.SERIAL).hashCode()).toString();
        } catch (Exception unused) {
            return new UUID(str.hashCode(), -905839116).toString();
        }
    }

    private static String b(Context context) {
        String strD = d(context);
        if (strD == null || strD.length() == 0) {
            String strI = i(context);
            strD = strI.length() == 0 ? "00000000000000000000000000000000" : strI.replace(":", "").toLowerCase();
        }
        return c(strD, 32);
    }

    private static String c(String str, int i) {
        c.a("HiCloudSDK", "devicecod from formatDeviceCode====" + str);
        return str.length() >= i ? str.substring(str.length() - i) : f(str, i);
    }

    private static String d(Context context) {
        TelephonyManager telephonyManager;
        int i = Build.VERSION.SDK_INT;
        Log.i("DeviceConfig", "getSerialNO() Build.VERSION.SDK_INT = " + i);
        if (i > 28) {
            return k(context);
        }
        try {
            telephonyManager = (TelephonyManager) context.getSystemService("phone");
        } catch (Exception e) {
            e.printStackTrace();
            telephonyManager = null;
        }
        if (telephonyManager != null) {
            return telephonyManager.getDeviceId();
        }
        return null;
    }

    private static String e(String str) throws IOException {
        StringBuffer stringBuffer = new StringBuffer(1000);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
        char[] cArr = new char[1024];
        while (true) {
            int i = bufferedReader.read(cArr);
            if (i == -1) {
                bufferedReader.close();
                return stringBuffer.toString();
            }
            stringBuffer.append(String.valueOf(cArr, 0, i));
        }
    }

    private static String f(String str, int i) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int length = i - str.length(); length > 0; length--) {
            stringBuffer.append("0");
        }
        stringBuffer.append(str);
        return stringBuffer.toString();
    }

    private static String g(Context context) {
        String strJ = j("ro.product.hitdeviceseq");
        if (TextUtils.isEmpty(strJ)) {
            strJ = j("sys.product.hitdeviceseq");
        }
        if (TextUtils.isEmpty(strJ)) {
            String strJ2 = j("ro.mos.uuid");
            if (!TextUtils.isEmpty(strJ2)) {
                return strJ2;
            }
            String strI = i(context);
            return !TextUtils.isEmpty(strI) ? c(strI.replace(":", "").toLowerCase(), 8) : "00000000";
        }
        String strC = c(strJ, 9);
        String hexString = Long.toHexString((((long) ((((b.indexOf(strC.charAt(0)) - 1) * 372) + ((b.indexOf(strC.charAt(1)) - 1) * 31) + (b.indexOf(strC.charAt(2)) - 1)) * 34 * 34)) * 10000) + (((long) ((b.indexOf(strC.charAt(3)) * 34) + b.indexOf(strC.charAt(4)))) * 10000) + ((long) ((b.indexOf(strC.charAt(5)) * 1000) + (b.indexOf(strC.charAt(6)) * 100) + (b.indexOf(strC.charAt(7)) * 10) + b.indexOf(strC.charAt(8)))));
        Log.i("DeviceConfig", "devicecod from getDeviceCodeBySeq ====" + hexString);
        return hexString;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x00bb A[PHI: r0
      0x00bb: PHI (r0v5 java.lang.String) = (r0v4 java.lang.String), (r0v7 java.lang.String) binds: [B:28:0x00b4, B:24:0x008b] A[DONT_GENERATE, DONT_INLINE]] */
    public static final String h(Context context) {
        String strD;
        StringBuilder sb;
        Log.i("DeviceConfig", "getDeviceId()");
        String strJ = null;
        if ("1".equals(j("ro.his.androidtv"))) {
            String strJ2 = j("ro.product.hitdeviceprefix");
            if (TextUtils.isEmpty(strJ2)) {
                return null;
            }
            String strJ3 = j("persist.sys.devicecode");
            if (TextUtils.isEmpty(strJ3)) {
                return null;
            }
            sb = new StringBuilder();
            sb.append(strJ2);
            sb.append(strJ3);
        } else {
            String strJ4 = j("ro.product.hitdeviceprefix");
            if (TextUtils.isEmpty(strJ4)) {
                if ("PPTV".equals(j("ro.product.manufacturer"))) {
                    strJ = j("persist.sys.ChannelROM");
                    if (TextUtils.isEmpty(strJ)) {
                        strJ = j("ro.product.channel");
                    }
                }
                if (!TextUtils.isEmpty(strJ)) {
                    strJ4 = "863f000010" + strJ + "00000000";
                }
                if (TextUtils.isEmpty(strJ4)) {
                    strJ4 = "862fff00fffefff00000fffe";
                    strD = b(context);
                } else {
                    strD = g(context);
                }
            } else if (strJ4.substring(3, 6).equals(f3a)) {
                strD = d(context);
            } else {
                strD = g(context);
            }
            String strC = c(strD, TextUtils.isEmpty(j("ro.mos.uuid")) ? "2".equals(String.valueOf(strJ4.charAt(2))) ? 32 : 8 : 32);
            sb = new StringBuilder();
            sb.append(strJ4);
            sb.append(strC);
        }
        return sb.toString().toLowerCase();
    }

    public static String i(Context context) {
        String strL = null;
        try {
            strL = e("/sys/class/net/eth0/address").substring(0, 17);
            Log.i("DeviceConfig", "mac=" + strL);
        } catch (IOException e) {
            Log.e("DeviceConfig", e.getMessage());
        }
        if (TextUtils.isEmpty(strL)) {
            c.a("HiCloudSDK", "wlan mac is null  to getWiredMacAddress ");
            strL = l(context);
        }
        return strL == null ? "" : strL;
    }

    public static final String j(String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, str);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String k(Context context) {
        String strA;
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        if (TextUtils.isEmpty(string) || "9774d56d682e549c".equals(string)) {
            strA = null;
        } else {
            try {
                strA = UUID.nameUUIDFromBytes(string.getBytes("utf8")).toString();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                strA = null;
            }
        }
        if (TextUtils.isEmpty(strA)) {
            strA = a();
        }
        if (TextUtils.isEmpty(strA)) {
            strA = a.b.a.a.a.a.a(context);
        }
        return strA.replace("-", "");
    }

    public static String l(Context context) {
        StringBuilder sb;
        WifiInfo connectionInfo;
        int i = Build.VERSION.SDK_INT;
        c.a("HiCloudSDK", "Build.VERSION.SDK_INT=" + i);
        String string = null;
        if (i >= 26) {
            try {
                ArrayList<NetworkInterface> list = Collections.list(NetworkInterface.getNetworkInterfaces());
                if (list == null || list.isEmpty()) {
                    c.a("HiCloudSDK", "all is null");
                } else {
                    c.a("HiCloudSDK", "all.lenth=" + list.size());
                    for (NetworkInterface networkInterface : list) {
                        c.a("HiCloudSDK", "nif.getName()=" + networkInterface.getName());
                        if (networkInterface.getName().equalsIgnoreCase("wlan0")) {
                            byte[] hardwareAddress = networkInterface.getHardwareAddress();
                            c.a("HiCloudSDK", "nif.getName()=" + hardwareAddress);
                            if (hardwareAddress != null) {
                                StringBuilder sb2 = new StringBuilder();
                                for (byte b2 : hardwareAddress) {
                                    c.a("HiCloudSDK", "macBytes=" + ((int) b2));
                                    sb2.append(String.format("%02X:", Byte.valueOf(b2)));
                                }
                                c.a("HiCloudSDK", "res1=" + ((Object) sb2));
                                if (sb2.length() > 0) {
                                    sb2.deleteCharAt(sb2.length() - 1);
                                }
                                string = sb2.toString();
                                break;
                            }
                        }
                    }
                }
                Log.i("DeviceConfig", "getNetworkInterfaces=" + string);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (i >= 23) {
                try {
                    string = e("/sys/class/net/wlan0/address").substring(0, 17);
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                sb = new StringBuilder("wlan0address=");
            } else {
                try {
                    connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
                } catch (Exception e3) {
                    e3.printStackTrace();
                    connectionInfo = null;
                }
                string = connectionInfo != null ? connectionInfo.getMacAddress() : null;
                sb = new StringBuilder("WIFI_SERVICE=");
            }
            sb.append(string);
            Log.i("DeviceConfig", sb.toString());
        }
        return string == null ? "" : string;
    }
}
