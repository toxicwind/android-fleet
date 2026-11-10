package com.ju.lib.datacommunication.network.http.dns;

import android.os.SystemClock;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.ju.lib.datacommunication.network.http.core.HostInfo;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import e.b.a;
import e.b.b;
import e.b.d;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public class Tools {
    public static final String GSLB_CONFIG_FILE_NAME = "Config_GSLB";
    public static final String KEY_COOLTIME = "cooltime";
    public static final String KEY_DATA = "data";
    public static final String KEY_EXT = "dnsclient";
    public static final String KEY_IPS = "ips";
    public static final String KEY_PROXY_IPS = "proxy";
    public static final String KEY_RET_CODE = "retCode";
    public static final String KEY_TIME = "time";
    public static final String KEY_TIMEOUT = "timeout";
    public static final String KEY_TTL = "ttl";
    public static final String RET_CODE_SUCCESS = "0";
    public static ExtData sPreExtData;
    public static ExtData sPreWriteExtData;

    public static class ExtData {
        public int coolTime;
        public double timeout;

        public ExtData(Integer num, Double d2) {
            this.coolTime = -1;
            this.timeout = -1.0d;
            this.coolTime = num.intValue();
            this.timeout = d2.doubleValue();
        }

        public static ExtData check(ExtData extData) {
            if (extData == null) {
                return null;
            }
            if (extData.coolTime < 0 && extData.timeout < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                return null;
            }
            if (extData.coolTime < 0) {
                extData.coolTime = 180;
            }
            if (extData.timeout < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                extData.timeout = 1.2d;
            }
            return extData;
        }

        public long getCoolTime() {
            return Tools.toMilliSecond(this.coolTime);
        }

        public long getTimeout() {
            return Tools.toMilliSecond(this.timeout);
        }

        public boolean isSame(ExtData extData) {
            return extData != null && this.coolTime == extData.coolTime && this.timeout == extData.timeout;
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("timeout = ");
            stringBuffer.append(this.timeout);
            stringBuffer.append(", coolTime = ");
            stringBuffer.append(this.coolTime);
            return stringBuffer.toString();
        }
    }

    public interface IGslbConfigListener {
        void onConfigChanged(ExtData extData);
    }

    public static boolean checkIP(String str) {
        return Pattern.compile("^((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])\\.){3}(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])$").matcher(str).matches();
    }

    public static String configToJson(ExtData extData) {
        if (extData == null) {
            return "";
        }
        d dVar = new d();
        dVar.a(KEY_TIMEOUT, (Object) String.valueOf(extData.timeout));
        dVar.b(KEY_COOLTIME, extData.coolTime);
        return dVar.toString();
    }

    public static void parseExt(File file, d dVar, IGslbConfigListener iGslbConfigListener) {
        ExtData ext = parseExt(dVar);
        if (ext == null) {
            return;
        }
        synchronized (Tools.class) {
            if (ext.isSame(sPreExtData)) {
                return;
            }
            sPreExtData = ext;
            if (iGslbConfigListener != null) {
                iGslbConfigListener.onConfigChanged(ext);
            }
            if (file == null) {
                return;
            }
            synchronized (Tools.class) {
                if (!ext.isSame(sPreWriteExtData) && writeConfig(file, ext)) {
                    sPreWriteExtData = ext;
                }
            }
        }
    }

    public static HostInfo parseItem(String str, String str2) {
        return parseItem(str, new d(str2), -1L);
    }

    public static List<HostInfo> parseJson(File file, String str, IGslbConfigListener iGslbConfigListener) {
        d dVarF;
        d dVar = new d(str);
        if (!"0".equals(dVar.h(KEY_RET_CODE))) {
            return Collections.emptyList();
        }
        d dVarF2 = dVar.f("data");
        Iterator<String> itA = dVarF2.a();
        ArrayList arrayList = new ArrayList();
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        while (itA.hasNext()) {
            String next = itA.next();
            HostInfo item = parseItem(next, dVarF2.f(next), jElapsedRealtime);
            if (item != null) {
                arrayList.add(item);
            }
        }
        if (dVar.f2163b.containsKey(KEY_EXT) && (dVarF = dVar.f(KEY_EXT)) != null) {
            parseExt(file, dVarF, iGslbConfigListener);
        }
        return arrayList;
    }

    public static void readConfig(File file, IGslbConfigListener iGslbConfigListener) {
        File file2 = new File(file, GSLB_CONFIG_FILE_NAME);
        BufferedReader bufferedReader = null;
        try {
            try {
                try {
                    if (!file2.exists()) {
                        HttpLog.i(HttpDns.TAG, "GSLB config file doesn't exist");
                        return;
                    }
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(file2), "UTF-8"));
                    try {
                        StringBuilder sb = new StringBuilder();
                        while (true) {
                            String line = bufferedReader2.readLine();
                            if (line == null) {
                                break;
                            } else {
                                sb.append(line);
                            }
                        }
                        parseExt(null, new d(sb.toString()), iGslbConfigListener);
                        bufferedReader2.close();
                    } catch (b e2) {
                        e = e2;
                        bufferedReader = bufferedReader2;
                        e.printStackTrace();
                        if (bufferedReader == null) {
                        } else {
                            bufferedReader.close();
                        }
                    } catch (IOException e3) {
                        e = e3;
                        bufferedReader = bufferedReader2;
                        e.printStackTrace();
                        if (bufferedReader == null) {
                        } else {
                            bufferedReader.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (b e5) {
                e = e5;
            } catch (IOException e6) {
                e = e6;
            }
        } catch (IOException e7) {
            e7.printStackTrace();
        }
    }

    public static String toJson(HostInfo hostInfo) {
        d dVar = new d();
        dVar.b(KEY_TTL, hostInfo.getTtl());
        dVar.b("time", hostInfo.getTimeStamp());
        List<String> ips = hostInfo.getIps();
        a aVar = new a();
        for (int i = 0; i < ips.size(); i++) {
            String str = ips.get(i);
            d.a((Object) str);
            if (i < 0) {
                throw new b(b.a.a.a.a.g("JSONArray[", i, "] not found."));
            }
            if (i < aVar.a()) {
                aVar.f2161a.set(i, str);
            } else {
                while (i != aVar.a()) {
                    aVar.f2161a.add(d.f2162a);
                }
                aVar.f2161a.add(str);
            }
        }
        dVar.a(KEY_IPS, aVar);
        return dVar.toString();
    }

    public static long toMilliSecond(double d2) {
        return (long) (d2 * 1000.0d);
    }

    public static boolean writeConfig(File file, ExtData extData) throws Throwable {
        File file2 = new File(file, GSLB_CONFIG_FILE_NAME);
        OutputStreamWriter outputStreamWriter = null;
        try {
            try {
                try {
                    if (!file2.exists()) {
                        boolean zCreateNewFile = file2.createNewFile();
                        HttpLog.i(HttpDns.TAG, "create GSLB config file : " + zCreateNewFile);
                        if (!zCreateNewFile) {
                            return false;
                        }
                    }
                    OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(new FileOutputStream(file2), "UTF-8");
                    try {
                        outputStreamWriter2.write(configToJson(extData));
                        outputStreamWriter2.flush();
                        try {
                            outputStreamWriter2.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        return true;
                    } catch (b e3) {
                        e = e3;
                        outputStreamWriter = outputStreamWriter2;
                        e.printStackTrace();
                        if (outputStreamWriter != null) {
                            outputStreamWriter.close();
                        }
                        return false;
                    } catch (IOException e4) {
                        e = e4;
                        outputStreamWriter = outputStreamWriter2;
                        e.printStackTrace();
                        if (outputStreamWriter != null) {
                            outputStreamWriter.close();
                        }
                        return false;
                    } catch (NumberFormatException e5) {
                        e = e5;
                        outputStreamWriter = outputStreamWriter2;
                        e.printStackTrace();
                        if (outputStreamWriter != null) {
                            outputStreamWriter.close();
                        }
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        outputStreamWriter = outputStreamWriter2;
                        if (outputStreamWriter != null) {
                            try {
                                outputStreamWriter.close();
                            } catch (IOException e6) {
                                e6.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (b e7) {
                e = e7;
            } catch (IOException e8) {
                e = e8;
            } catch (NumberFormatException e9) {
                e = e9;
            }
        } catch (IOException e10) {
            e10.printStackTrace();
        }
    }

    public static HostInfo parseItem(String str, d dVar, long j) {
        a aVarK;
        long jA = dVar.a(KEY_TTL, 0L);
        if (jA <= 0 || (aVarK = dVar.k(KEY_IPS)) == null || aVarK.a() <= 0) {
            return null;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(aVarK.a());
        for (int i = 0; i < aVarK.a(); i++) {
            String strB = aVarK.b(i);
            if (checkIP(strB)) {
                linkedHashSet.add(strB);
            }
        }
        if (linkedHashSet.isEmpty()) {
            return null;
        }
        LinkedHashSet linkedHashSet2 = new LinkedHashSet(aVarK.a());
        a aVarK2 = dVar.k(KEY_PROXY_IPS);
        if (aVarK2 != null && aVarK2.a() > 0) {
            for (int i2 = 0; i2 < aVarK2.a(); i2++) {
                String strB2 = aVarK2.b(i2);
                if (checkIP(strB2)) {
                    linkedHashSet2.add(strB2);
                }
            }
        }
        if (j <= 0) {
            j = dVar.a("time", j);
            if (j <= 0) {
                j = SystemClock.elapsedRealtime();
            }
        }
        return new HostInfo.Builder().setHostName(str).setTtl(jA).setClientTimeStamp(j).addIps(linkedHashSet).addProxyIps(linkedHashSet2).build();
    }

    public static ExtData parseExt(d dVar) {
        Iterator<String> itA = dVar.a();
        int iCheckCoolTime = -1;
        double dCheckConnectTimeout = -1.0d;
        while (itA != null && itA.hasNext()) {
            String next = itA.next();
            if (KEY_TIMEOUT.equalsIgnoreCase(next)) {
                dCheckConnectTimeout = HttpDnsConfig.checkConnectTimeout(dVar.a(KEY_TIMEOUT, 1.2d));
            } else if (KEY_COOLTIME.equalsIgnoreCase(next)) {
                iCheckCoolTime = HttpDnsConfig.checkCoolTime(dVar.a(KEY_COOLTIME, 180));
            }
        }
        if (iCheckCoolTime < 0 && dCheckConnectTimeout < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            HttpLog.i(HttpDns.TAG, "ext is invalid");
            return null;
        }
        ExtData extData = new ExtData(Integer.valueOf(iCheckCoolTime), Double.valueOf(dCheckConnectTimeout));
        HttpLog.i(HttpDns.TAG, extData.toString());
        return ExtData.check(extData);
    }
}
