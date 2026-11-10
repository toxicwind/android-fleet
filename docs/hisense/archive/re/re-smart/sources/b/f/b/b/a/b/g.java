package b.f.b.b.a.b;

import android.util.Log;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: loaded from: classes2.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f687a = "g";

    public static String a() {
        try {
            ArrayList<NetworkInterface> list = Collections.list(NetworkInterface.getNetworkInterfaces());
            Log.d("Utils", "all:" + list.size());
            for (NetworkInterface networkInterface : list) {
                if (networkInterface.getName().equalsIgnoreCase("eth0")) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return null;
                    }
                    Log.d("Utils", "macBytes:" + hardwareAddress.length + "," + networkInterface.getName());
                    StringBuilder sb = new StringBuilder();
                    int length = hardwareAddress.length;
                    for (int i = 0; i < length; i++) {
                        sb.append(String.format("%02X:", Byte.valueOf(hardwareAddress[i])));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString();
                }
            }
        } catch (Exception e2) {
            Log.e(f687a, e2.toString());
        }
        return null;
    }
}
