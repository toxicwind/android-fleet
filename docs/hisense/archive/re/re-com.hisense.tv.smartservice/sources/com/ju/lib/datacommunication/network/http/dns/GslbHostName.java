package com.ju.lib.datacommunication.network.http.dns;

import b.a.a.a.a;
import com.ju.lib.datacommunication.network.http.core.HostInfo;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import e.b.b;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public abstract class GslbHostName {
    public static final String DOMAIN_GSLB_SERVER = "lbgs.hismarttv.com";
    public static final String GSLB_SERVER_IP1 = "118.178.149.42";
    public static final String GSLB_SERVER_IP2 = "49.233.125.19";
    public static final String GSLB_SERVER_SIT_IP1 = "10.18.217.70";
    public static final String GSLB_SERVER_SIT_IP2 = "10.18.217.71";
    public static final String GSLB_SERVER_UT_IP1 = "10.18.210.145";
    public static final String GSLB_SERVER_UT_IP2 = "10.18.210.156";
    public static final String SIT = "SIT";
    public static final String TAG = "HTTP2.GslbHostName";
    public static final String UT = "UT";
    public static String[] sDefaultGslbIps;
    public static final Object LOCK = new Object();
    public static Pattern sGSLBPattern = Pattern.compile("GSLB\\d+", 66);

    private static final class FileImpl extends GslbHostName {
        public static final String GSLB_IP_FILE_NAME = "GSLB";
        public final File mDirectory;
        public HostInfo mHostInfo;

        public FileImpl(File file) {
            if (file == null) {
                throw new NullPointerException("Directory file is null! ");
            }
            if (!file.isDirectory()) {
                throw new IllegalArgumentException("Directory file is not a folder! ");
            }
            this.mDirectory = file;
        }

        private String formatName(long j) {
            return a.a(GSLB_IP_FILE_NAME, j);
        }

        private long parseName(String str) {
            try {
                if (str.startsWith(GSLB_IP_FILE_NAME)) {
                    return Long.parseLong(str.substring(4));
                }
                return 0L;
            } catch (Exception unused) {
                return 0L;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [java.io.File[]] */
        /* JADX WARN: Type inference failed for: r0v3, types: [java.io.BufferedReader] */
        /* JADX WARN: Type inference failed for: r0v6 */
        /* JADX WARN: Type inference failed for: r4v0 */
        /* JADX WARN: Type inference failed for: r4v1, types: [java.io.File] */
        /* JADX WARN: Type inference failed for: r4v4 */
        /* JADX WARN: Type inference failed for: r4v5 */
        /* JADX WARN: Type inference failed for: r4v6 */
        /* JADX WARN: Type inference failed for: r4v7 */
        /* JADX WARN: Type inference failed for: r4v8 */
        /* JADX WARN: Type inference failed for: r7v0, types: [java.io.File] */
        private HostInfo readHostInfo() throws Throwable {
            ?? r4;
            BufferedReader bufferedReader;
            ?? ListFiles = this.mDirectory.listFiles();
            if (ListFiles != 0) {
                int length = ListFiles.length;
                int i = 0;
                long j = -1;
                r4 = 0;
                while (i < length) {
                    ?? r7 = ListFiles[i];
                    long name = parseName(r7.getName());
                    if (name > j) {
                        r4 = r7;
                        j = name;
                    }
                    i++;
                    r4 = r4;
                }
            } else {
                r4 = 0;
            }
            try {
                try {
                    if (r4 != 0) {
                        try {
                            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream((File) r4), "UTF-8"));
                            try {
                                StringBuilder sb = new StringBuilder();
                                while (true) {
                                    String line = bufferedReader.readLine();
                                    if (line == null) {
                                        break;
                                    }
                                    sb.append(line);
                                }
                                HostInfo item = Tools.parseItem(GslbHostName.DOMAIN_GSLB_SERVER, sb.toString());
                                try {
                                    bufferedReader.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                                return item;
                            } catch (b e3) {
                                e = e3;
                                e.printStackTrace();
                                if (bufferedReader != null) {
                                    bufferedReader.close();
                                }
                                return null;
                            } catch (IOException e4) {
                                e = e4;
                                e.printStackTrace();
                                if (bufferedReader != null) {
                                    bufferedReader.close();
                                }
                                return null;
                            }
                        } catch (b e5) {
                            e = e5;
                            bufferedReader = null;
                        } catch (IOException e6) {
                            e = e6;
                            bufferedReader = null;
                        } catch (Throwable th) {
                            th = th;
                            ListFiles = 0;
                            if (ListFiles != 0) {
                                try {
                                    ListFiles.close();
                                } catch (IOException e7) {
                                    e7.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                } catch (IOException e8) {
                    e8.printStackTrace();
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
            }
        }

        /* JADX WARN: Code duplicated, block: B:37:0x0071  */
        /* JADX WARN: Code duplicated, block: B:39:0x0074  */
        /* JADX WARN: Code duplicated, block: B:42:0x007d  */
        /* JADX WARN: Code duplicated, block: B:44:0x008d  */
        /* JADX WARN: Code duplicated, block: B:46:0x0093 A[ORIG_RETURN, RETURN] */
        /* JADX WARN: Code duplicated, block: B:67:0x007c A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:69:0x0090 A[SYNTHETIC] */
        /* JADX WARN: Instruction removed from duplicated block: B:37:0x0071, please report this as an issue */
        private void writeHostInfo(HostInfo hostInfo) throws Throwable {
            File[] fileArrListFiles = this.mDirectory.listFiles();
            long j = 0;
            if (fileArrListFiles != null) {
                long j2 = 0;
                for (File file : fileArrListFiles) {
                    long name = parseName(file.getName());
                    if (name > j2) {
                        j2 = name;
                    }
                }
                j = j2;
            }
            OutputStreamWriter outputStreamWriter = null;
            try {
                try {
                    try {
                        OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(new FileOutputStream(new File(this.mDirectory, formatName(j + 1))), "UTF-8");
                        try {
                            outputStreamWriter2.write(Tools.toJson(hostInfo));
                            outputStreamWriter2.flush();
                            outputStreamWriter2.close();
                        } catch (b e2) {
                            e = e2;
                            outputStreamWriter = outputStreamWriter2;
                            e.printStackTrace();
                            if (outputStreamWriter != null) {
                                outputStreamWriter.close();
                            }
                            if (fileArrListFiles != null) {
                                for (File file2 : fileArrListFiles) {
                                    if (!file2.isFile()) {
                                        return;
                                    }
                                    if (GslbHostName.sGSLBPattern.matcher(file2.getName()).find()) {
                                        file2.delete();
                                    }
                                }
                            }
                        } catch (IOException e3) {
                            e = e3;
                            outputStreamWriter = outputStreamWriter2;
                            e.printStackTrace();
                            if (outputStreamWriter != null) {
                                outputStreamWriter.close();
                            }
                            if (fileArrListFiles != null) {
                                while (i < r10) {
                                    if (!file2.isFile()) {
                                        return;
                                    }
                                    if (GslbHostName.sGSLBPattern.matcher(file2.getName()).find()) {
                                        file2.delete();
                                    }
                                }
                            }
                        } catch (Throwable th) {
                            th = th;
                            outputStreamWriter = outputStreamWriter2;
                            if (outputStreamWriter != null) {
                                try {
                                    outputStreamWriter.close();
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
            if (fileArrListFiles != null) {
                while (i < r10) {
                    if (!file2.isFile()) {
                        return;
                    }
                    if (GslbHostName.sGSLBPattern.matcher(file2.getName()).find()) {
                        file2.delete();
                    }
                }
            }
        }

        @Override // com.ju.lib.datacommunication.network.http.dns.GslbHostName
        public HostInfo getHostInfo() {
            HostInfo hostInfo = this.mHostInfo;
            return hostInfo != null ? hostInfo : readHostInfo();
        }

        @Override // com.ju.lib.datacommunication.network.http.dns.GslbHostName
        public void putHostInfo(HostInfo hostInfo) throws Throwable {
            this.mHostInfo = hostInfo;
            writeHostInfo(hostInfo);
        }
    }

    private static final class RamImpl extends GslbHostName {
        public HostInfo mHostInfo;

        public RamImpl() {
        }

        @Override // com.ju.lib.datacommunication.network.http.dns.GslbHostName
        public HostInfo getHostInfo() {
            return this.mHostInfo;
        }

        @Override // com.ju.lib.datacommunication.network.http.dns.GslbHostName
        public void putHostInfo(HostInfo hostInfo) {
            this.mHostInfo = hostInfo;
        }

        public /* synthetic */ RamImpl(AnonymousClass1 anonymousClass1) {
        }
    }

    public static List<String> getDefault() {
        if (sDefaultGslbIps == null) {
            String prop = getProp("log.tag.httpdns");
            if (SIT.equals(prop)) {
                sDefaultGslbIps = new String[]{GSLB_SERVER_SIT_IP1, GSLB_SERVER_SIT_IP2};
            } else if (UT.equals(prop)) {
                sDefaultGslbIps = new String[]{GSLB_SERVER_UT_IP1, GSLB_SERVER_UT_IP2};
            } else {
                sDefaultGslbIps = new String[]{GSLB_SERVER_IP1, GSLB_SERVER_IP2};
            }
            if (HttpLog.isHttpStackLogAble()) {
                HttpLog.d(TAG, "Default gslb ips: ", Arrays.toString(sDefaultGslbIps), ",httpdns flag:", prop);
            }
        }
        return Arrays.asList(sDefaultGslbIps);
    }

    public static final String getProp(String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, str);
        } catch (Exception e2) {
            HttpLog.w(TAG, e2, "getProp");
            return "";
        }
    }

    public static final GslbHostName newInstance(File file) {
        if (file != null && file.exists() && file.isDirectory()) {
            try {
                return new FileImpl(file);
            } catch (Exception e2) {
                HttpLog.w(TAG, e2, "create FileImpl error! ");
            }
        }
        return new RamImpl(null);
    }

    public abstract HostInfo getHostInfo();

    public final List<String> getHosts() {
        List<String> ips;
        ArrayList arrayList;
        synchronized (LOCK) {
            HostInfo hostInfo = getHostInfo();
            if (hostInfo == null) {
                ips = getDefault();
            } else {
                ips = hostInfo.getIps();
                if (ips.isEmpty()) {
                    ips = getDefault();
                }
            }
            arrayList = new ArrayList(ips.size() + 1);
            arrayList.addAll(ips);
            arrayList.add(DOMAIN_GSLB_SERVER);
        }
        return arrayList;
    }

    public final void put(HostInfo hostInfo) {
        synchronized (LOCK) {
            putHostInfo(hostInfo);
        }
    }

    public abstract void putHostInfo(HostInfo hostInfo);
}
