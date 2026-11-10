package com.google.android.gms.dynamite;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.firebase.installations.Utils;
import dalvik.system.DelegateLastClassLoader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
public final class DynamiteModule {

    @Nullable
    public static Boolean zzb = null;

    @Nullable
    public static String zzc = null;
    public static boolean zzd = false;
    public static int zze = -1;

    @Nullable
    public static zzq zzj;

    @Nullable
    public static zzr zzk;
    public final Context zzi;
    public static final ThreadLocal<zzn> zzf = new ThreadLocal<>();
    public static final ThreadLocal<Long> zzg = new zzd();
    public static final VersionPolicy.IVersions zzh = new zze();

    @NonNull
    @KeepForSdk
    public static final VersionPolicy PREFER_REMOTE = new zzf();

    @NonNull
    @KeepForSdk
    public static final VersionPolicy PREFER_LOCAL = new zzg();

    @NonNull
    @KeepForSdk
    public static final VersionPolicy PREFER_REMOTE_VERSION_NO_FORCE_STAGING = new zzh();

    @NonNull
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION = new zzi();

    @NonNull
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new zzj();

    @NonNull
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION = new zzk();

    @NonNull
    public static final VersionPolicy zza = new zzl();

    @DynamiteApi
    public static class DynamiteLoaderClassLoader {

        @Nullable
        public static ClassLoader sClassLoader;
    }

    @KeepForSdk
    public static class LoadingException extends Exception {
        public /* synthetic */ LoadingException(String str, zzp zzpVar) {
            super(str);
        }

        public /* synthetic */ LoadingException(String str, Throwable th, zzp zzpVar) {
            super(str, th);
        }
    }

    public interface VersionPolicy {

        public interface IVersions {
            int zza(@NonNull Context context, @NonNull String str);

            int zzb(@NonNull Context context, @NonNull String str, boolean z);
        }

        public static class SelectionResult {

            @KeepForSdk
            public int localVersion = 0;

            @KeepForSdk
            public int remoteVersion = 0;

            @KeepForSdk
            public int selection = 0;
        }

        @NonNull
        @KeepForSdk
        SelectionResult selectModule(@NonNull Context context, @NonNull String str, @NonNull IVersions iVersions);
    }

    public DynamiteModule(Context context) {
        Preconditions.checkNotNull(context);
        this.zzi = context;
    }

    @KeepForSdk
    public static int getLocalVersion(@NonNull Context context, @NonNull String str) {
        try {
            ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 61);
            sb.append("com.google.android.gms.dynamite.descriptors.");
            sb.append(str);
            sb.append(".");
            sb.append("ModuleDescriptor");
            Class<?> clsLoadClass = classLoader.loadClass(sb.toString());
            Field declaredField = clsLoadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = clsLoadClass.getDeclaredField("MODULE_VERSION");
            if (Objects.equal(declaredField.get(null), str)) {
                return declaredField2.getInt(null);
            }
            String strValueOf = String.valueOf(declaredField.get(null));
            StringBuilder sb2 = new StringBuilder(strValueOf.length() + 51 + String.valueOf(str).length());
            sb2.append("Module descriptor id '");
            sb2.append(strValueOf);
            sb2.append("' didn't match expected id '");
            sb2.append(str);
            sb2.append("'");
            Log.e("DynamiteModule", sb2.toString());
            return 0;
        } catch (ClassNotFoundException unused) {
            StringBuilder sb3 = new StringBuilder(String.valueOf(str).length() + 45);
            sb3.append("Local module descriptor class for ");
            sb3.append(str);
            sb3.append(" not found.");
            Log.w("DynamiteModule", sb3.toString());
            return 0;
        } catch (Exception e2) {
            String strValueOf2 = String.valueOf(e2.getMessage());
            Log.e("DynamiteModule", strValueOf2.length() != 0 ? "Failed to load module descriptor class: ".concat(strValueOf2) : new String("Failed to load module descriptor class: "));
            return 0;
        }
    }

    @KeepForSdk
    public static int getRemoteVersion(@NonNull Context context, @NonNull String str) {
        return zza(context, str, false);
    }

    /* JADX WARN: Code duplicated, block: B:130:0x02b5 A[Catch: all -> 0x0308, TryCatch #4 {all -> 0x0308, blocks: (B:104:0x0244, B:105:0x024e, B:107:0x0250, B:109:0x0252, B:110:0x0259, B:130:0x02b5, B:131:0x02cd, B:112:0x025b, B:114:0x026d, B:116:0x0278, B:118:0x027f, B:120:0x028e, B:128:0x02ad, B:129:0x02b4, B:115:0x0272, B:132:0x02ce, B:133:0x0307, B:26:0x00bb), top: B:149:0x0082, inners: #8 }] */
    /* JADX WARN: Code duplicated, block: B:147:0x00c0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:152:0x00f7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:155:0x00bb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:15:0x0095 A[Catch: all -> 0x030c, TRY_LEAVE, TryCatch #7 {all -> 0x030c, blocks: (B:3:0x0028, B:7:0x0087, B:12:0x008f, B:15:0x0095, B:26:0x00bb), top: B:154:0x0028 }] */
    /* JADX WARN: Code duplicated, block: B:18:0x009d  */
    /* JADX WARN: Code duplicated, block: B:19:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:22:0x00b0 A[PHI: r0 r1
      0x00b0: PHI (r0v49 com.google.android.gms.dynamite.DynamiteModule) = (r0v16 com.google.android.gms.dynamite.DynamiteModule), (r0v50 com.google.android.gms.dynamite.DynamiteModule) binds: [B:126:0x02a9, B:21:0x00ae] A[DONT_GENERATE, DONT_INLINE]
      0x00b0: PHI (r1v23 android.database.Cursor) = (r1v17 android.database.Cursor), (r1v26 android.database.Cursor) binds: [B:126:0x02a9, B:21:0x00ae] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:25:0x00b9 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:32:0x00c5 A[Catch: all -> 0x0243, LoadingException -> 0x024f, RemoteException -> 0x0251, TRY_ENTER, TryCatch #8 {RemoteException -> 0x0251, LoadingException -> 0x024f, all -> 0x0243, blocks: (B:28:0x00bf, B:32:0x00c5, B:34:0x00cc, B:35:0x00f6, B:39:0x00fc, B:41:0x0106, B:43:0x010a, B:44:0x0115, B:51:0x0124, B:53:0x012a, B:55:0x0151, B:57:0x0159, B:58:0x0160, B:59:0x0167, B:54:0x013e, B:62:0x016a, B:63:0x016b, B:64:0x0172, B:65:0x0173, B:66:0x017a, B:69:0x017d, B:70:0x017e, B:72:0x01ac, B:74:0x01b3, B:76:0x01bd, B:82:0x01f5, B:84:0x01fb, B:94:0x0228, B:95:0x022f, B:77:0x01cc, B:78:0x01d3, B:80:0x01d6, B:81:0x01e6, B:96:0x0230, B:97:0x0237, B:98:0x0238, B:99:0x023f, B:102:0x0242), top: B:156:0x00bf }] */
    /* JADX WARN: Code duplicated, block: B:34:0x00cc A[Catch: all -> 0x0243, LoadingException -> 0x024f, RemoteException -> 0x0251, TryCatch #8 {RemoteException -> 0x0251, LoadingException -> 0x024f, all -> 0x0243, blocks: (B:28:0x00bf, B:32:0x00c5, B:34:0x00cc, B:35:0x00f6, B:39:0x00fc, B:41:0x0106, B:43:0x010a, B:44:0x0115, B:51:0x0124, B:53:0x012a, B:55:0x0151, B:57:0x0159, B:58:0x0160, B:59:0x0167, B:54:0x013e, B:62:0x016a, B:63:0x016b, B:64:0x0172, B:65:0x0173, B:66:0x017a, B:69:0x017d, B:70:0x017e, B:72:0x01ac, B:74:0x01b3, B:76:0x01bd, B:82:0x01f5, B:84:0x01fb, B:94:0x0228, B:95:0x022f, B:77:0x01cc, B:78:0x01d3, B:80:0x01d6, B:81:0x01e6, B:96:0x0230, B:97:0x0237, B:98:0x0238, B:99:0x023f, B:102:0x0242), top: B:156:0x00bf }] */
    /* JADX WARN: Code duplicated, block: B:39:0x00fc A[Catch: all -> 0x0243, LoadingException -> 0x024f, RemoteException -> 0x0251, TRY_ENTER, TryCatch #8 {RemoteException -> 0x0251, LoadingException -> 0x024f, all -> 0x0243, blocks: (B:28:0x00bf, B:32:0x00c5, B:34:0x00cc, B:35:0x00f6, B:39:0x00fc, B:41:0x0106, B:43:0x010a, B:44:0x0115, B:51:0x0124, B:53:0x012a, B:55:0x0151, B:57:0x0159, B:58:0x0160, B:59:0x0167, B:54:0x013e, B:62:0x016a, B:63:0x016b, B:64:0x0172, B:65:0x0173, B:66:0x017a, B:69:0x017d, B:70:0x017e, B:72:0x01ac, B:74:0x01b3, B:76:0x01bd, B:82:0x01f5, B:84:0x01fb, B:94:0x0228, B:95:0x022f, B:77:0x01cc, B:78:0x01d3, B:80:0x01d6, B:81:0x01e6, B:96:0x0230, B:97:0x0237, B:98:0x0238, B:99:0x023f, B:102:0x0242), top: B:156:0x00bf }] */
    /* JADX WARN: Code duplicated, block: B:65:0x0173 A[Catch: all -> 0x0243, LoadingException -> 0x024f, RemoteException -> 0x0251, TryCatch #8 {RemoteException -> 0x0251, LoadingException -> 0x024f, all -> 0x0243, blocks: (B:28:0x00bf, B:32:0x00c5, B:34:0x00cc, B:35:0x00f6, B:39:0x00fc, B:41:0x0106, B:43:0x010a, B:44:0x0115, B:51:0x0124, B:53:0x012a, B:55:0x0151, B:57:0x0159, B:58:0x0160, B:59:0x0167, B:54:0x013e, B:62:0x016a, B:63:0x016b, B:64:0x0172, B:65:0x0173, B:66:0x017a, B:69:0x017d, B:70:0x017e, B:72:0x01ac, B:74:0x01b3, B:76:0x01bd, B:82:0x01f5, B:84:0x01fb, B:94:0x0228, B:95:0x022f, B:77:0x01cc, B:78:0x01d3, B:80:0x01d6, B:81:0x01e6, B:96:0x0230, B:97:0x0237, B:98:0x0238, B:99:0x023f, B:102:0x0242), top: B:156:0x00bf }] */
    /* JADX WARN: Code duplicated, block: B:70:0x017e A[Catch: all -> 0x0243, LoadingException -> 0x024f, RemoteException -> 0x0251, TryCatch #8 {RemoteException -> 0x0251, LoadingException -> 0x024f, all -> 0x0243, blocks: (B:28:0x00bf, B:32:0x00c5, B:34:0x00cc, B:35:0x00f6, B:39:0x00fc, B:41:0x0106, B:43:0x010a, B:44:0x0115, B:51:0x0124, B:53:0x012a, B:55:0x0151, B:57:0x0159, B:58:0x0160, B:59:0x0167, B:54:0x013e, B:62:0x016a, B:63:0x016b, B:64:0x0172, B:65:0x0173, B:66:0x017a, B:69:0x017d, B:70:0x017e, B:72:0x01ac, B:74:0x01b3, B:76:0x01bd, B:82:0x01f5, B:84:0x01fb, B:94:0x0228, B:95:0x022f, B:77:0x01cc, B:78:0x01d3, B:80:0x01d6, B:81:0x01e6, B:96:0x0230, B:97:0x0237, B:98:0x0238, B:99:0x023f, B:102:0x0242), top: B:156:0x00bf }] */
    /* JADX WARN: Code duplicated, block: B:72:0x01ac A[Catch: all -> 0x0243, LoadingException -> 0x024f, RemoteException -> 0x0251, TryCatch #8 {RemoteException -> 0x0251, LoadingException -> 0x024f, all -> 0x0243, blocks: (B:28:0x00bf, B:32:0x00c5, B:34:0x00cc, B:35:0x00f6, B:39:0x00fc, B:41:0x0106, B:43:0x010a, B:44:0x0115, B:51:0x0124, B:53:0x012a, B:55:0x0151, B:57:0x0159, B:58:0x0160, B:59:0x0167, B:54:0x013e, B:62:0x016a, B:63:0x016b, B:64:0x0172, B:65:0x0173, B:66:0x017a, B:69:0x017d, B:70:0x017e, B:72:0x01ac, B:74:0x01b3, B:76:0x01bd, B:82:0x01f5, B:84:0x01fb, B:94:0x0228, B:95:0x022f, B:77:0x01cc, B:78:0x01d3, B:80:0x01d6, B:81:0x01e6, B:96:0x0230, B:97:0x0237, B:98:0x0238, B:99:0x023f, B:102:0x0242), top: B:156:0x00bf }] */
    /* JADX WARN: Code duplicated, block: B:74:0x01b3 A[Catch: all -> 0x0243, LoadingException -> 0x024f, RemoteException -> 0x0251, TryCatch #8 {RemoteException -> 0x0251, LoadingException -> 0x024f, all -> 0x0243, blocks: (B:28:0x00bf, B:32:0x00c5, B:34:0x00cc, B:35:0x00f6, B:39:0x00fc, B:41:0x0106, B:43:0x010a, B:44:0x0115, B:51:0x0124, B:53:0x012a, B:55:0x0151, B:57:0x0159, B:58:0x0160, B:59:0x0167, B:54:0x013e, B:62:0x016a, B:63:0x016b, B:64:0x0172, B:65:0x0173, B:66:0x017a, B:69:0x017d, B:70:0x017e, B:72:0x01ac, B:74:0x01b3, B:76:0x01bd, B:82:0x01f5, B:84:0x01fb, B:94:0x0228, B:95:0x022f, B:77:0x01cc, B:78:0x01d3, B:80:0x01d6, B:81:0x01e6, B:96:0x0230, B:97:0x0237, B:98:0x0238, B:99:0x023f, B:102:0x0242), top: B:156:0x00bf }] */
    /* JADX WARN: Code duplicated, block: B:76:0x01bd A[Catch: all -> 0x0243, LoadingException -> 0x024f, RemoteException -> 0x0251, TryCatch #8 {RemoteException -> 0x0251, LoadingException -> 0x024f, all -> 0x0243, blocks: (B:28:0x00bf, B:32:0x00c5, B:34:0x00cc, B:35:0x00f6, B:39:0x00fc, B:41:0x0106, B:43:0x010a, B:44:0x0115, B:51:0x0124, B:53:0x012a, B:55:0x0151, B:57:0x0159, B:58:0x0160, B:59:0x0167, B:54:0x013e, B:62:0x016a, B:63:0x016b, B:64:0x0172, B:65:0x0173, B:66:0x017a, B:69:0x017d, B:70:0x017e, B:72:0x01ac, B:74:0x01b3, B:76:0x01bd, B:82:0x01f5, B:84:0x01fb, B:94:0x0228, B:95:0x022f, B:77:0x01cc, B:78:0x01d3, B:80:0x01d6, B:81:0x01e6, B:96:0x0230, B:97:0x0237, B:98:0x0238, B:99:0x023f, B:102:0x0242), top: B:156:0x00bf }] */
    /* JADX WARN: Code duplicated, block: B:77:0x01cc A[Catch: all -> 0x0243, LoadingException -> 0x024f, RemoteException -> 0x0251, TryCatch #8 {RemoteException -> 0x0251, LoadingException -> 0x024f, all -> 0x0243, blocks: (B:28:0x00bf, B:32:0x00c5, B:34:0x00cc, B:35:0x00f6, B:39:0x00fc, B:41:0x0106, B:43:0x010a, B:44:0x0115, B:51:0x0124, B:53:0x012a, B:55:0x0151, B:57:0x0159, B:58:0x0160, B:59:0x0167, B:54:0x013e, B:62:0x016a, B:63:0x016b, B:64:0x0172, B:65:0x0173, B:66:0x017a, B:69:0x017d, B:70:0x017e, B:72:0x01ac, B:74:0x01b3, B:76:0x01bd, B:82:0x01f5, B:84:0x01fb, B:94:0x0228, B:95:0x022f, B:77:0x01cc, B:78:0x01d3, B:80:0x01d6, B:81:0x01e6, B:96:0x0230, B:97:0x0237, B:98:0x0238, B:99:0x023f, B:102:0x0242), top: B:156:0x00bf }] */
    /* JADX WARN: Code duplicated, block: B:79:0x01d4 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:80:0x01d6 A[Catch: all -> 0x0243, LoadingException -> 0x024f, RemoteException -> 0x0251, TryCatch #8 {RemoteException -> 0x0251, LoadingException -> 0x024f, all -> 0x0243, blocks: (B:28:0x00bf, B:32:0x00c5, B:34:0x00cc, B:35:0x00f6, B:39:0x00fc, B:41:0x0106, B:43:0x010a, B:44:0x0115, B:51:0x0124, B:53:0x012a, B:55:0x0151, B:57:0x0159, B:58:0x0160, B:59:0x0167, B:54:0x013e, B:62:0x016a, B:63:0x016b, B:64:0x0172, B:65:0x0173, B:66:0x017a, B:69:0x017d, B:70:0x017e, B:72:0x01ac, B:74:0x01b3, B:76:0x01bd, B:82:0x01f5, B:84:0x01fb, B:94:0x0228, B:95:0x022f, B:77:0x01cc, B:78:0x01d3, B:80:0x01d6, B:81:0x01e6, B:96:0x0230, B:97:0x0237, B:98:0x0238, B:99:0x023f, B:102:0x0242), top: B:156:0x00bf }] */
    /* JADX WARN: Code duplicated, block: B:81:0x01e6 A[Catch: all -> 0x0243, LoadingException -> 0x024f, RemoteException -> 0x0251, TryCatch #8 {RemoteException -> 0x0251, LoadingException -> 0x024f, all -> 0x0243, blocks: (B:28:0x00bf, B:32:0x00c5, B:34:0x00cc, B:35:0x00f6, B:39:0x00fc, B:41:0x0106, B:43:0x010a, B:44:0x0115, B:51:0x0124, B:53:0x012a, B:55:0x0151, B:57:0x0159, B:58:0x0160, B:59:0x0167, B:54:0x013e, B:62:0x016a, B:63:0x016b, B:64:0x0172, B:65:0x0173, B:66:0x017a, B:69:0x017d, B:70:0x017e, B:72:0x01ac, B:74:0x01b3, B:76:0x01bd, B:82:0x01f5, B:84:0x01fb, B:94:0x0228, B:95:0x022f, B:77:0x01cc, B:78:0x01d3, B:80:0x01d6, B:81:0x01e6, B:96:0x0230, B:97:0x0237, B:98:0x0238, B:99:0x023f, B:102:0x0242), top: B:156:0x00bf }] */
    /* JADX WARN: Code duplicated, block: B:84:0x01fb A[Catch: all -> 0x0243, LoadingException -> 0x024f, RemoteException -> 0x0251, TRY_LEAVE, TryCatch #8 {RemoteException -> 0x0251, LoadingException -> 0x024f, all -> 0x0243, blocks: (B:28:0x00bf, B:32:0x00c5, B:34:0x00cc, B:35:0x00f6, B:39:0x00fc, B:41:0x0106, B:43:0x010a, B:44:0x0115, B:51:0x0124, B:53:0x012a, B:55:0x0151, B:57:0x0159, B:58:0x0160, B:59:0x0167, B:54:0x013e, B:62:0x016a, B:63:0x016b, B:64:0x0172, B:65:0x0173, B:66:0x017a, B:69:0x017d, B:70:0x017e, B:72:0x01ac, B:74:0x01b3, B:76:0x01bd, B:82:0x01f5, B:84:0x01fb, B:94:0x0228, B:95:0x022f, B:77:0x01cc, B:78:0x01d3, B:80:0x01d6, B:81:0x01e6, B:96:0x0230, B:97:0x0237, B:98:0x0238, B:99:0x023f, B:102:0x0242), top: B:156:0x00bf }] */
    /* JADX WARN: Code duplicated, block: B:87:0x020c  */
    /* JADX WARN: Code duplicated, block: B:88:0x0212  */
    /* JADX WARN: Code duplicated, block: B:91:0x021f  */
    /* JADX WARN: Code duplicated, block: B:94:0x0228 A[Catch: all -> 0x0243, LoadingException -> 0x024f, RemoteException -> 0x0251, TRY_ENTER, TryCatch #8 {RemoteException -> 0x0251, LoadingException -> 0x024f, all -> 0x0243, blocks: (B:28:0x00bf, B:32:0x00c5, B:34:0x00cc, B:35:0x00f6, B:39:0x00fc, B:41:0x0106, B:43:0x010a, B:44:0x0115, B:51:0x0124, B:53:0x012a, B:55:0x0151, B:57:0x0159, B:58:0x0160, B:59:0x0167, B:54:0x013e, B:62:0x016a, B:63:0x016b, B:64:0x0172, B:65:0x0173, B:66:0x017a, B:69:0x017d, B:70:0x017e, B:72:0x01ac, B:74:0x01b3, B:76:0x01bd, B:82:0x01f5, B:84:0x01fb, B:94:0x0228, B:95:0x022f, B:77:0x01cc, B:78:0x01d3, B:80:0x01d6, B:81:0x01e6, B:96:0x0230, B:97:0x0237, B:98:0x0238, B:99:0x023f, B:102:0x0242), top: B:156:0x00bf }] */
    /* JADX WARN: Code duplicated, block: B:96:0x0230 A[Catch: all -> 0x0243, LoadingException -> 0x024f, RemoteException -> 0x0251, TryCatch #8 {RemoteException -> 0x0251, LoadingException -> 0x024f, all -> 0x0243, blocks: (B:28:0x00bf, B:32:0x00c5, B:34:0x00cc, B:35:0x00f6, B:39:0x00fc, B:41:0x0106, B:43:0x010a, B:44:0x0115, B:51:0x0124, B:53:0x012a, B:55:0x0151, B:57:0x0159, B:58:0x0160, B:59:0x0167, B:54:0x013e, B:62:0x016a, B:63:0x016b, B:64:0x0172, B:65:0x0173, B:66:0x017a, B:69:0x017d, B:70:0x017e, B:72:0x01ac, B:74:0x01b3, B:76:0x01bd, B:82:0x01f5, B:84:0x01fb, B:94:0x0228, B:95:0x022f, B:77:0x01cc, B:78:0x01d3, B:80:0x01d6, B:81:0x01e6, B:96:0x0230, B:97:0x0237, B:98:0x0238, B:99:0x023f, B:102:0x0242), top: B:156:0x00bf }] */
    /* JADX WARN: Code duplicated, block: B:98:0x0238 A[Catch: all -> 0x0243, LoadingException -> 0x024f, RemoteException -> 0x0251, TryCatch #8 {RemoteException -> 0x0251, LoadingException -> 0x024f, all -> 0x0243, blocks: (B:28:0x00bf, B:32:0x00c5, B:34:0x00cc, B:35:0x00f6, B:39:0x00fc, B:41:0x0106, B:43:0x010a, B:44:0x0115, B:51:0x0124, B:53:0x012a, B:55:0x0151, B:57:0x0159, B:58:0x0160, B:59:0x0167, B:54:0x013e, B:62:0x016a, B:63:0x016b, B:64:0x0172, B:65:0x0173, B:66:0x017a, B:69:0x017d, B:70:0x017e, B:72:0x01ac, B:74:0x01b3, B:76:0x01bd, B:82:0x01f5, B:84:0x01fb, B:94:0x0228, B:95:0x022f, B:77:0x01cc, B:78:0x01d3, B:80:0x01d6, B:81:0x01e6, B:96:0x0230, B:97:0x0237, B:98:0x0238, B:99:0x023f, B:102:0x0242), top: B:156:0x00bf }] */
    @NonNull
    @KeepForSdk
    public static DynamiteModule load(@NonNull Context context, @NonNull VersionPolicy versionPolicy, @NonNull String str) throws Throwable {
        long j;
        DynamiteModule dynamiteModuleZzc;
        Cursor cursor;
        int i;
        Boolean bool;
        zzq zzqVarZzf;
        int iZze;
        IObjectWrapper iObjectWrapperZzh;
        DynamiteModule dynamiteModule;
        zzn zznVar;
        Cursor cursor2;
        zzr zzrVar;
        zzn zznVar2;
        Boolean boolValueOf;
        IObjectWrapper iObjectWrapperZze;
        zzn zznVar3 = zzf.get();
        zzp zzpVar = null;
        zzn zznVar4 = new zzn(null);
        zzf.set(zznVar4);
        long jLongValue = zzg.get().longValue();
        try {
            zzg.set(Long.valueOf(SystemClock.elapsedRealtime()));
            VersionPolicy.SelectionResult selectionResultSelectModule = versionPolicy.selectModule(context, str, zzh);
            int i2 = selectionResultSelectModule.localVersion;
            int i3 = selectionResultSelectModule.remoteVersion;
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 68 + String.valueOf(str).length());
            sb.append("Considering local module ");
            sb.append(str);
            sb.append(Utils.APP_ID_IDENTIFICATION_SUBSTRING);
            sb.append(i2);
            sb.append(" and remote module ");
            sb.append(str);
            sb.append(Utils.APP_ID_IDENTIFICATION_SUBSTRING);
            sb.append(i3);
            Log.i("DynamiteModule", sb.toString());
            int i4 = selectionResultSelectModule.selection;
            try {
                if (i4 != 0) {
                    if (i4 != -1) {
                        if (i4 == 1 || selectionResultSelectModule.remoteVersion != 0) {
                            if (i4 != -1) {
                                dynamiteModuleZzc = zzc(context, str);
                                if (jLongValue == 0) {
                                    zzg.remove();
                                } else {
                                    zzg.set(Long.valueOf(jLongValue));
                                }
                                cursor = zznVar4.zza;
                                if (cursor != null) {
                                    cursor.close();
                                }
                            } else {
                                if (i4 == 1) {
                                    StringBuilder sb2 = new StringBuilder(47);
                                    sb2.append("VersionPolicy returned invalid code:");
                                    sb2.append(i4);
                                    throw new LoadingException(sb2.toString(), zzpVar);
                                }
                                try {
                                    i = selectionResultSelectModule.remoteVersion;
                                    try {
                                        synchronized (DynamiteModule.class) {
                                            bool = zzb;
                                        }
                                        if (bool != null) {
                                            throw new LoadingException("Failed to determine which loading route to use.", zzpVar);
                                        }
                                        if (bool.booleanValue()) {
                                            StringBuilder sb3 = new StringBuilder(String.valueOf(str).length() + 51);
                                            sb3.append("Selected remote version of ");
                                            sb3.append(str);
                                            sb3.append(", version >= ");
                                            sb3.append(i);
                                            Log.i("DynamiteModule", sb3.toString());
                                            synchronized (DynamiteModule.class) {
                                                zzrVar = zzk;
                                            }
                                            if (zzrVar != null) {
                                                throw new LoadingException("DynamiteLoaderV2 was not cached.", zzpVar);
                                            }
                                            zznVar2 = zzf.get();
                                            if (zznVar2 != null || zznVar2.zza == null) {
                                                throw new LoadingException("No result cursor", zzpVar);
                                            }
                                            Context applicationContext = context.getApplicationContext();
                                            Cursor cursor3 = zznVar2.zza;
                                            ObjectWrapper.wrap(null);
                                            synchronized (DynamiteModule.class) {
                                                boolValueOf = Boolean.valueOf(zze >= 2);
                                            }
                                            if (boolValueOf.booleanValue()) {
                                                Log.v("DynamiteModule", "Dynamite loader version >= 2, using loadModule2NoCrashUtils");
                                                iObjectWrapperZze = zzrVar.zzf(ObjectWrapper.wrap(applicationContext), str, i, ObjectWrapper.wrap(cursor3));
                                            } else {
                                                Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to loadModule2");
                                                iObjectWrapperZze = zzrVar.zze(ObjectWrapper.wrap(applicationContext), str, i, ObjectWrapper.wrap(cursor3));
                                            }
                                            Context context2 = (Context) ObjectWrapper.unwrap(iObjectWrapperZze);
                                            if (context2 == null) {
                                                throw new LoadingException("Failed to get module context", zzpVar);
                                            }
                                            dynamiteModule = new DynamiteModule(context2);
                                        } else {
                                            StringBuilder sb4 = new StringBuilder(String.valueOf(str).length() + 51);
                                            sb4.append("Selected remote version of ");
                                            sb4.append(str);
                                            sb4.append(", version >= ");
                                            sb4.append(i);
                                            Log.i("DynamiteModule", sb4.toString());
                                            zzqVarZzf = zzf(context);
                                            if (zzqVarZzf != null) {
                                                throw new LoadingException("Failed to create IDynamiteLoader.", zzpVar);
                                            }
                                            iZze = zzqVarZzf.zze();
                                            if (iZze >= 3) {
                                                zznVar = zzf.get();
                                                if (zznVar != null) {
                                                    throw new LoadingException("No cached result cursor holder", zzpVar);
                                                }
                                                iObjectWrapperZzh = zzqVarZzf.zzi(ObjectWrapper.wrap(context), str, i, ObjectWrapper.wrap(zznVar.zza));
                                            } else if (iZze == 2) {
                                                Log.w("DynamiteModule", "IDynamite loader version = 2");
                                                iObjectWrapperZzh = zzqVarZzf.zzj(ObjectWrapper.wrap(context), str, i);
                                            } else {
                                                Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to createModuleContext");
                                                iObjectWrapperZzh = zzqVarZzf.zzh(ObjectWrapper.wrap(context), str, i);
                                            }
                                            if (ObjectWrapper.unwrap(iObjectWrapperZzh) != null) {
                                                throw new LoadingException("Failed to load remote module.", zzpVar);
                                            }
                                            dynamiteModule = new DynamiteModule((Context) ObjectWrapper.unwrap(iObjectWrapperZzh));
                                        }
                                        if (jLongValue == 0) {
                                            zzg.remove();
                                        } else {
                                            zzg.set(Long.valueOf(jLongValue));
                                        }
                                        cursor2 = zznVar4.zza;
                                        if (cursor2 != null) {
                                            cursor2.close();
                                        }
                                        zzf.set(zznVar3);
                                        return dynamiteModule;
                                    } catch (RemoteException e2) {
                                        throw new LoadingException("Failed to load remote module.", e2, zzpVar);
                                    } catch (LoadingException e3) {
                                        throw e3;
                                    } catch (Throwable th) {
                                        CrashUtils.addDynamiteErrorToDropBox(context, th);
                                        throw new LoadingException("Failed to load remote module.", th, zzpVar);
                                    }
                                } catch (LoadingException e4) {
                                    String strValueOf = String.valueOf(e4.getMessage());
                                    Log.w("DynamiteModule", strValueOf.length() != 0 ? "Failed to load remote module: ".concat(strValueOf) : new String("Failed to load remote module: "));
                                    int i5 = selectionResultSelectModule.localVersion;
                                    if (i5 == 0 || versionPolicy.selectModule(context, str, new zzo(i5, 0)).selection != -1) {
                                        throw new LoadingException("Remote load failed. No local fallback found.", e4, zzpVar);
                                    }
                                    dynamiteModuleZzc = zzc(context, str);
                                    if (jLongValue == 0) {
                                        zzg.remove();
                                    } else {
                                        zzg.set(Long.valueOf(jLongValue));
                                    }
                                    cursor = zznVar4.zza;
                                    if (cursor != null) {
                                    }
                                }
                            }
                            zzf.set(zznVar3);
                            return dynamiteModuleZzc;
                        }
                    } else if (selectionResultSelectModule.localVersion != 0) {
                        i4 = -1;
                        if (i4 == 1) {
                        }
                        if (i4 != -1) {
                            if (i4 == 1) {
                                StringBuilder sb5 = new StringBuilder(47);
                                sb5.append("VersionPolicy returned invalid code:");
                                sb5.append(i4);
                                throw new LoadingException(sb5.toString(), zzpVar);
                            }
                            i = selectionResultSelectModule.remoteVersion;
                            synchronized (DynamiteModule.class) {
                                bool = zzb;
                                if (bool != null) {
                                    throw new LoadingException("Failed to determine which loading route to use.", zzpVar);
                                }
                                if (bool.booleanValue()) {
                                    StringBuilder sb6 = new StringBuilder(String.valueOf(str).length() + 51);
                                    sb6.append("Selected remote version of ");
                                    sb6.append(str);
                                    sb6.append(", version >= ");
                                    sb6.append(i);
                                    Log.i("DynamiteModule", sb6.toString());
                                    synchronized (DynamiteModule.class) {
                                        zzrVar = zzk;
                                        if (zzrVar != null) {
                                            throw new LoadingException("DynamiteLoaderV2 was not cached.", zzpVar);
                                        }
                                        zznVar2 = zzf.get();
                                        if (zznVar2 != null) {
                                        }
                                        throw new LoadingException("No result cursor", zzpVar);
                                    }
                                }
                                StringBuilder sb7 = new StringBuilder(String.valueOf(str).length() + 51);
                                sb7.append("Selected remote version of ");
                                sb7.append(str);
                                sb7.append(", version >= ");
                                sb7.append(i);
                                Log.i("DynamiteModule", sb7.toString());
                                zzqVarZzf = zzf(context);
                                if (zzqVarZzf != null) {
                                    throw new LoadingException("Failed to create IDynamiteLoader.", zzpVar);
                                }
                                iZze = zzqVarZzf.zze();
                                if (iZze >= 3) {
                                    zznVar = zzf.get();
                                    if (zznVar != null) {
                                        throw new LoadingException("No cached result cursor holder", zzpVar);
                                    }
                                    iObjectWrapperZzh = zzqVarZzf.zzi(ObjectWrapper.wrap(context), str, i, ObjectWrapper.wrap(zznVar.zza));
                                } else if (iZze == 2) {
                                    Log.w("DynamiteModule", "IDynamite loader version = 2");
                                    iObjectWrapperZzh = zzqVarZzf.zzj(ObjectWrapper.wrap(context), str, i);
                                } else {
                                    Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to createModuleContext");
                                    iObjectWrapperZzh = zzqVarZzf.zzh(ObjectWrapper.wrap(context), str, i);
                                }
                                if (ObjectWrapper.unwrap(iObjectWrapperZzh) != null) {
                                    throw new LoadingException("Failed to load remote module.", zzpVar);
                                }
                                dynamiteModule = new DynamiteModule((Context) ObjectWrapper.unwrap(iObjectWrapperZzh));
                                if (jLongValue == 0) {
                                    zzg.remove();
                                } else {
                                    zzg.set(Long.valueOf(jLongValue));
                                }
                                cursor2 = zznVar4.zza;
                                if (cursor2 != null) {
                                    cursor2.close();
                                }
                                zzf.set(zznVar3);
                                return dynamiteModule;
                            }
                        }
                        dynamiteModuleZzc = zzc(context, str);
                        if (jLongValue == 0) {
                            zzg.remove();
                        } else {
                            zzg.set(Long.valueOf(jLongValue));
                        }
                        cursor = zznVar4.zza;
                        if (cursor != null) {
                            cursor.close();
                        }
                        zzf.set(zznVar3);
                        return dynamiteModuleZzc;
                    }
                }
                int i6 = selectionResultSelectModule.localVersion;
                int i7 = selectionResultSelectModule.remoteVersion;
                StringBuilder sb8 = new StringBuilder(String.valueOf(str).length() + 92);
                sb8.append("No acceptable module ");
                sb8.append(str);
                sb8.append(" found. Local version is ");
                sb8.append(i6);
                sb8.append(" and remote version is ");
                sb8.append(i7);
                sb8.append(".");
                throw new LoadingException(sb8.toString(), zzpVar);
            } catch (Throwable th2) {
                th = th2;
                j = 0;
                if (jLongValue == j) {
                    zzg.remove();
                } else {
                    zzg.set(Long.valueOf(jLongValue));
                }
                Cursor cursor4 = zznVar4.zza;
                if (cursor4 != null) {
                    cursor4.close();
                }
                zzf.set(zznVar3);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            j = 0;
        }
    }

    /* JADX WARN: Code duplicated, block: B:93:0x0168 A[Catch: all -> 0x01ca, TRY_ENTER, TRY_LEAVE, TryCatch #5 {all -> 0x01ca, blocks: (B:3:0x0002, B:56:0x00d5, B:59:0x00dc, B:68:0x0102, B:89:0x015a, B:93:0x0168, B:117:0x01c3, B:118:0x01c6, B:114:0x01bd, B:62:0x00e2, B:64:0x00f4, B:66:0x00fe, B:65:0x00f9, B:121:0x01c9, B:4:0x0003, B:7:0x0008, B:8:0x0024, B:54:0x00d2, B:36:0x008e, B:39:0x0091, B:47:0x00aa, B:55:0x00d4, B:53:0x00b0), top: B:132:0x0002, inners: #0, #2 }] */
    public static int zza(@NonNull Context context, @NonNull String str, boolean z) {
        Throwable th;
        RemoteException e2;
        Cursor cursor;
        try {
            synchronized (DynamiteModule.class) {
                Boolean bool = zzb;
                Cursor cursor2 = null;
                if (bool == null) {
                    try {
                        Field declaredField = context.getApplicationContext().getClassLoader().loadClass(DynamiteLoaderClassLoader.class.getName()).getDeclaredField("sClassLoader");
                        synchronized (declaredField.getDeclaringClass()) {
                            ClassLoader classLoader = (ClassLoader) declaredField.get(null);
                            if (classLoader != null) {
                                if (classLoader == ClassLoader.getSystemClassLoader()) {
                                    bool = Boolean.FALSE;
                                } else {
                                    try {
                                        zzd(classLoader);
                                    } catch (LoadingException unused) {
                                    }
                                    bool = Boolean.TRUE;
                                }
                            } else if (zzd || Boolean.TRUE.equals(null)) {
                                declaredField.set(null, ClassLoader.getSystemClassLoader());
                                bool = Boolean.FALSE;
                            } else {
                                try {
                                    int iZzb = zzb(context, str, z);
                                    String str2 = zzc;
                                    if (str2 != null && !str2.isEmpty()) {
                                        ClassLoader classLoaderZza = zzb.zza();
                                        if (classLoaderZza == null) {
                                            if (Build.VERSION.SDK_INT >= 29) {
                                                String str3 = zzc;
                                                Preconditions.checkNotNull(str3);
                                                classLoaderZza = new DelegateLastClassLoader(str3, ClassLoader.getSystemClassLoader());
                                            } else {
                                                String str4 = zzc;
                                                Preconditions.checkNotNull(str4);
                                                classLoaderZza = new zzc(str4, ClassLoader.getSystemClassLoader());
                                            }
                                        }
                                        zzd(classLoaderZza);
                                        declaredField.set(null, classLoaderZza);
                                        zzb = Boolean.TRUE;
                                        return iZzb;
                                    }
                                    return iZzb;
                                } catch (LoadingException unused2) {
                                    declaredField.set(null, ClassLoader.getSystemClassLoader());
                                    bool = Boolean.FALSE;
                                }
                            }
                            zzb = bool;
                        }
                    } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e3) {
                        String string = e3.toString();
                        StringBuilder sb = new StringBuilder(string.length() + 30);
                        sb.append("Failed to load module via V2: ");
                        sb.append(string);
                        Log.w("DynamiteModule", sb.toString());
                        bool = Boolean.FALSE;
                    }
                }
                boolean zBooleanValue = bool.booleanValue();
                int iZzf = 0;
                if (zBooleanValue) {
                    try {
                        return zzb(context, str, z);
                    } catch (LoadingException e4) {
                        String strValueOf = String.valueOf(e4.getMessage());
                        Log.w("DynamiteModule", strValueOf.length() != 0 ? "Failed to retrieve remote module version: ".concat(strValueOf) : new String("Failed to retrieve remote module version: "));
                        return 0;
                    }
                }
                zzq zzqVarZzf = zzf(context);
                try {
                    if (zzqVarZzf != null) {
                        try {
                            int iZze = zzqVarZzf.zze();
                            if (iZze >= 3) {
                                zzn zznVar = zzf.get();
                                if (zznVar == null || (cursor = zznVar.zza) == null) {
                                    Cursor cursor3 = (Cursor) ObjectWrapper.unwrap(zzqVarZzf.zzk(new ObjectWrapper(context), str, z, zzg.get().longValue()));
                                    if (cursor3 != null) {
                                        try {
                                            if (cursor3.moveToFirst()) {
                                                int i = cursor3.getInt(0);
                                                if (i > 0 && zze(cursor3)) {
                                                    cursor3 = null;
                                                }
                                                if (cursor3 != null) {
                                                    cursor3.close();
                                                }
                                                iZzf = i;
                                            } else {
                                                Log.w("DynamiteModule", "Failed to retrieve remote module version.");
                                                if (cursor3 != null) {
                                                    cursor3.close();
                                                }
                                            }
                                        } catch (RemoteException e5) {
                                            e2 = e5;
                                            cursor2 = cursor3;
                                            String strValueOf2 = String.valueOf(e2.getMessage());
                                            Log.w("DynamiteModule", strValueOf2.length() != 0 ? "Failed to retrieve remote module version: ".concat(strValueOf2) : new String("Failed to retrieve remote module version: "));
                                            if (cursor2 != null) {
                                                cursor2.close();
                                            }
                                        } catch (Throwable th2) {
                                            th = th2;
                                            cursor2 = cursor3;
                                            if (cursor2 != null) {
                                                cursor2.close();
                                            }
                                            throw th;
                                        }
                                    } else {
                                        Log.w("DynamiteModule", "Failed to retrieve remote module version.");
                                        if (cursor3 != null) {
                                            cursor3.close();
                                        }
                                    }
                                } else {
                                    iZzf = cursor.getInt(0);
                                }
                            } else if (iZze == 2) {
                                Log.w("DynamiteModule", "IDynamite loader version = 2, no high precision latency measurement.");
                                iZzf = zzqVarZzf.zzg(new ObjectWrapper(context), str, z);
                            } else {
                                Log.w("DynamiteModule", "IDynamite loader version < 2, falling back to getModuleVersion2");
                                iZzf = zzqVarZzf.zzf(new ObjectWrapper(context), str, z);
                            }
                        } catch (RemoteException e6) {
                            e2 = e6;
                        }
                    }
                    return iZzf;
                } catch (Throwable th3) {
                    th = th3;
                }
            }
        } catch (Throwable th4) {
            CrashUtils.addDynamiteErrorToDropBox(context, th4);
            throw th4;
        }
    }

    /* JADX WARN: Code duplicated, block: B:53:0x00c5  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v15 */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v5, types: [android.database.Cursor] */
    public static int zzb(Context context, String str, boolean z) throws Throwable {
        Throwable th;
        ?? r10;
        Exception e2;
        zzp zzpVar = null;
        try {
            try {
                Cursor cursorQuery = context.getContentResolver().query(new Uri.Builder().scheme("content").authority("com.google.android.gms.chimera").path(true != z ? "api" : "api_force_staging").appendPath(str).appendQueryParameter("requestStartTime", String.valueOf(zzg.get().longValue())).build(), null, null, null, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.moveToFirst()) {
                            boolean z2 = false;
                            int i = cursorQuery.getInt(0);
                            if (i > 0) {
                                synchronized (DynamiteModule.class) {
                                    zzc = cursorQuery.getString(2);
                                    int columnIndex = cursorQuery.getColumnIndex("loaderVersion");
                                    if (columnIndex >= 0) {
                                        zze = cursorQuery.getInt(columnIndex);
                                    }
                                    int columnIndex2 = cursorQuery.getColumnIndex("disableStandaloneDynamiteLoader");
                                    if (columnIndex2 >= 0) {
                                        z2 = cursorQuery.getInt(columnIndex2) != 0;
                                        zzd = z2;
                                    }
                                }
                                if (zze(cursorQuery)) {
                                    cursorQuery = null;
                                }
                            }
                            if (z2) {
                                throw new LoadingException("forcing fallback to container DynamiteLoader impl", zzpVar);
                            }
                            if (cursorQuery != null) {
                                cursorQuery.close();
                            }
                            return i;
                        }
                    } catch (Exception e3) {
                        e2 = e3;
                        if (e2 instanceof LoadingException) {
                            throw e2;
                        }
                        throw new LoadingException("V2 version check failed", e2, zzpVar);
                    }
                }
                Log.w("DynamiteModule", "Failed to retrieve remote module version.");
                throw new LoadingException("Failed to connect to dynamite module ContentResolver.", zzpVar);
            } catch (Throwable th2) {
                th = th2;
                r10 = context;
                if (r10 != 0) {
                    r10.close();
                }
                throw th;
            }
        } catch (Exception e4) {
            e2 = e4;
        } catch (Throwable th3) {
            th = th3;
            r10 = 0;
            if (r10 != 0) {
                r10.close();
            }
            throw th;
        }
    }

    public static DynamiteModule zzc(Context context, String str) {
        String strValueOf = String.valueOf(str);
        Log.i("DynamiteModule", strValueOf.length() != 0 ? "Selected local version of ".concat(strValueOf) : new String("Selected local version of "));
        return new DynamiteModule(context.getApplicationContext());
    }

    public static void zzd(ClassLoader classLoader) throws LoadingException {
        zzr zzrVar;
        zzp zzpVar = null;
        try {
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]);
            if (iBinder == null) {
                zzrVar = null;
            } else {
                IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                zzrVar = iInterfaceQueryLocalInterface instanceof zzr ? (zzr) iInterfaceQueryLocalInterface : new zzr(iBinder);
            }
            zzk = zzrVar;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e2) {
            throw new LoadingException("Failed to instantiate dynamite loader", e2, zzpVar);
        }
    }

    public static boolean zze(Cursor cursor) {
        zzn zznVar = zzf.get();
        if (zznVar == null || zznVar.zza != null) {
            return false;
        }
        zznVar.zza = cursor;
        return true;
    }

    @Nullable
    public static zzq zzf(Context context) {
        zzq zzqVar;
        synchronized (DynamiteModule.class) {
            zzq zzqVar2 = zzj;
            if (zzqVar2 != null) {
                return zzqVar2;
            }
            try {
                IBinder iBinder = (IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                if (iBinder == null) {
                    zzqVar = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                    zzqVar = iInterfaceQueryLocalInterface instanceof zzq ? (zzq) iInterfaceQueryLocalInterface : new zzq(iBinder);
                }
                if (zzqVar != null) {
                    zzj = zzqVar;
                    return zzqVar;
                }
            } catch (Exception e2) {
                String strValueOf = String.valueOf(e2.getMessage());
                Log.e("DynamiteModule", strValueOf.length() != 0 ? "Failed to load IDynamiteLoader from GmsCore: ".concat(strValueOf) : new String("Failed to load IDynamiteLoader from GmsCore: "));
            }
            return null;
        }
    }

    @NonNull
    @KeepForSdk
    public Context getModuleContext() {
        return this.zzi;
    }

    @NonNull
    @KeepForSdk
    public IBinder instantiate(@NonNull String str) throws LoadingException {
        try {
            return (IBinder) this.zzi.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e2) {
            String strValueOf = String.valueOf(str);
            throw new LoadingException(strValueOf.length() != 0 ? "Failed to instantiate module class: ".concat(strValueOf) : new String("Failed to instantiate module class: "), e2, null);
        }
    }
}
