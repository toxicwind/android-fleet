package com.google.android.gms.common.util;

import android.os.Process;
import android.os.StrictMode;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
public class ProcessUtils {
    public static String zza;
    public static int zzb;

    @Nullable
    @KeepForSdk
    public static String getMyProcessName() throws Throwable {
        BufferedReader bufferedReader;
        if (zza == null) {
            int iMyPid = zzb;
            if (iMyPid == 0) {
                iMyPid = Process.myPid();
                zzb = iMyPid;
            }
            String strTrim = null;
            strTrim = null;
            strTrim = null;
            BufferedReader bufferedReader2 = null;
            if (iMyPid > 0) {
                try {
                    try {
                        StringBuilder sb = new StringBuilder(25);
                        sb.append("/proc/");
                        sb.append(iMyPid);
                        sb.append("/cmdline");
                        String string = sb.toString();
                        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
                        try {
                            bufferedReader = new BufferedReader(new FileReader(string));
                            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                            try {
                                String line = bufferedReader.readLine();
                                Preconditions.checkNotNull(line);
                                strTrim = line.trim();
                            } catch (IOException unused) {
                                if (bufferedReader != null) {
                                }
                                zza = strTrim;
                                return zza;
                            } catch (Throwable th) {
                                th = th;
                                bufferedReader2 = bufferedReader;
                                if (bufferedReader2 != null) {
                                    try {
                                        bufferedReader2.close();
                                    } catch (IOException unused2) {
                                    }
                                }
                                throw th;
                            }
                        } catch (Throwable th2) {
                            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                            throw th2;
                        }
                    } catch (IOException unused3) {
                        bufferedReader = null;
                    } catch (Throwable th3) {
                        th = th3;
                    }
                    bufferedReader.close();
                } catch (IOException unused4) {
                }
            }
            zza = strTrim;
        }
        return zza;
    }
}
