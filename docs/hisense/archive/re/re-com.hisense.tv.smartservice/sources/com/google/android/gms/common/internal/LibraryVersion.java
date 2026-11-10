package com.google.android.gms.common.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
public class LibraryVersion {
    public static final GmsLogger zza = new GmsLogger("LibraryVersion", "");
    public static LibraryVersion zzb = new LibraryVersion();
    public ConcurrentHashMap<String, String> zzc = new ConcurrentHashMap<>();

    @VisibleForTesting
    public LibraryVersion() {
    }

    @NonNull
    @KeepForSdk
    public static LibraryVersion getInstance() {
        return zzb;
    }

    @NonNull
    @KeepForSdk
    public String getVersion(@NonNull String str) throws Throwable {
        String str2;
        Preconditions.checkNotEmpty(str, "Please provide a valid libraryName");
        if (this.zzc.containsKey(str)) {
            return this.zzc.get(str);
        }
        Properties properties = new Properties();
        InputStream inputStream = null;
        String property = null;
        InputStream inputStream2 = null;
        try {
            try {
                InputStream resourceAsStream = LibraryVersion.class.getResourceAsStream(String.format("/%s.properties", str));
                try {
                    if (resourceAsStream != null) {
                        properties.load(resourceAsStream);
                        property = properties.getProperty("version", null);
                        GmsLogger gmsLogger = zza;
                        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 12 + String.valueOf(property).length());
                        sb.append(str);
                        sb.append(" version is ");
                        sb.append(property);
                        gmsLogger.v("LibraryVersion", sb.toString());
                    } else {
                        GmsLogger gmsLogger2 = zza;
                        String strValueOf = String.valueOf(str);
                        gmsLogger2.w("LibraryVersion", strValueOf.length() != 0 ? "Failed to get app version for libraryName: ".concat(strValueOf) : new String("Failed to get app version for libraryName: "));
                    }
                    if (resourceAsStream != null) {
                        try {
                            resourceAsStream.close();
                        } catch (IOException unused) {
                        }
                    }
                    str2 = property;
                } catch (IOException e2) {
                    e = e2;
                    inputStream = resourceAsStream;
                    str2 = null;
                    GmsLogger gmsLogger3 = zza;
                    String strValueOf2 = String.valueOf(str);
                    gmsLogger3.e("LibraryVersion", strValueOf2.length() != 0 ? "Failed to get app version for libraryName: ".concat(strValueOf2) : new String("Failed to get app version for libraryName: "), e);
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException unused2) {
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    inputStream2 = resourceAsStream;
                    if (inputStream2 != null) {
                        try {
                            inputStream2.close();
                        } catch (IOException unused3) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e3) {
            e = e3;
            str2 = null;
        }
        if (str2 == null) {
            zza.d("LibraryVersion", ".properties file is dropped during release process. Failure to read app version is expected during Google internal testing where locally-built libraries are used");
            str2 = "UNKNOWN";
        }
        this.zzc.put(str, str2);
        return str2;
    }
}
