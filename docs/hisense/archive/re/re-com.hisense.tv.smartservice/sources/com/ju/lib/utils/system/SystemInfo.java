package com.ju.lib.utils.system;

import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import com.ju.lib.datacommunication.network.http.dns.HttpDnsConfig;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class SystemInfo {
    public static final String TAG = "SystemInfo";
    public static long sTotalMemory;

    public static String getIpAddress() {
        try {
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                for (InetAddress inetAddress : Collections.list(((NetworkInterface) it.next()).getInetAddresses())) {
                    if (inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
            return HttpDnsConfig.UNSUPPORTED_IP;
        } catch (SocketException e2) {
            e2.printStackTrace();
            return HttpDnsConfig.UNSUPPORTED_IP;
        }
    }

    public static SpaceInfo getSdCardSpaceInfo() {
        if ("mounted".equals(Environment.getExternalStorageState())) {
            return getSpaceInfo(Environment.getExternalStorageDirectory().getPath());
        }
        return null;
    }

    public static SpaceInfo getSpaceInfo(String str) {
        StatFs statFs = new StatFs(str);
        return new SpaceInfo(statFs.getBlockSize(), statFs.getBlockCount(), statFs.getAvailableBlocks(), statFs.getFreeBlocks());
    }

    public static SpaceInfo getSystemDataSpaceInfo() {
        return getSpaceInfo(Environment.getDataDirectory().getPath());
    }

    public static long getTotalMemory() {
        long j = sTotalMemory;
        long j2 = 0;
        if (j > 0) {
            return j;
        }
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream("/proc/meminfo"), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader, 8192);
            String line = bufferedReader.readLine();
            if (line != null) {
                String[] strArrSplit = line.split("\\s+");
                for (String str : strArrSplit) {
                    Log.i(line, str + "\t");
                }
                j2 = Long.parseLong(strArrSplit[1]) * 1024;
            }
            bufferedReader.close();
            inputStreamReader.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        sTotalMemory = j2;
        return sTotalMemory;
    }
}
