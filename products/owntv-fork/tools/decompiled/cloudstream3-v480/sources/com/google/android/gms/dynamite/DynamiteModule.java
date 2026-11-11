package com.google.android.gms.dynamite;

import android.content.ContentProviderClient;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import coil3.size.ScaleDrawable$$ExternalSyntheticApiModelOutline0;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.5.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class DynamiteModule {
    public static final int LOCAL = -1;
    public static final int NONE = 0;
    public static final int NO_SELECTION = 0;
    public static final int REMOTE = 1;
    private static Boolean zzb = null;
    private static String zzc = null;
    private static boolean zzd = false;
    private static int zze = -1;
    private static Boolean zzf;
    private static zzp zzk;
    private static zzq zzl;
    private final Context zzj;
    private static final ThreadLocal zzg = new ThreadLocal();
    private static final ThreadLocal zzh = new zzd();
    private static final VersionPolicy.IVersions zzi = new zze();
    public static final VersionPolicy PREFER_REMOTE = new zzf();
    public static final VersionPolicy PREFER_LOCAL = new zzg();
    public static final VersionPolicy PREFER_REMOTE_VERSION_NO_FORCE_STAGING = new zzh();
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION = new zzi();
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new zzj();
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION = new zzk();
    public static final VersionPolicy zza = new zzl();

    /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.5.0 */
    public static class DynamiteLoaderClassLoader {
        public static ClassLoader sClassLoader;
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.5.0 */
    public static class LoadingException extends Exception {
        /* synthetic */ LoadingException(String str, zzo zzoVar) {
            super(str);
        }

        /* synthetic */ LoadingException(String str, Throwable th, zzo zzoVar) {
            super(str, th);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.5.0 */
    public interface VersionPolicy {

        /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.5.0 */
        public interface IVersions {
            int zza(Context context, String str);

            int zzb(Context context, String str, boolean z) throws LoadingException;
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.5.0 */
        public static class SelectionResult {
            public int localVersion = 0;
            public int remoteVersion = 0;
            public int selection = 0;
        }

        SelectionResult selectModule(Context context, String str, IVersions iVersions) throws LoadingException;
    }

    private DynamiteModule(Context context) {
        Preconditions.checkNotNull(context);
        this.zzj = context;
    }

    public static int getLocalVersion(Context context, String str) {
        try {
            Class<?> clsLoadClass = context.getApplicationContext().getClassLoader().loadClass("com.google.android.gms.dynamite.descriptors." + str + ".ModuleDescriptor");
            Field declaredField = clsLoadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = clsLoadClass.getDeclaredField("MODULE_VERSION");
            if (Objects.equal(declaredField.get(null), str)) {
                return declaredField2.getInt(null);
            }
            Log.e("DynamiteModule", "Module descriptor id '" + String.valueOf(declaredField.get(null)) + "' didn't match expected id '" + str + "'");
            return 0;
        } catch (ClassNotFoundException unused) {
            Log.w("DynamiteModule", "Local module descriptor class for " + str + " not found.");
            return 0;
        } catch (Exception e) {
            Log.e("DynamiteModule", "Failed to load module descriptor class: ".concat(String.valueOf(e.getMessage())));
            return 0;
        }
    }

    public static int getRemoteVersion(Context context, String str) {
        return zza(context, str, false);
    }

    /* JADX WARN: Code duplicated, block: B:112:0x023c  */
    /* JADX WARN: Code duplicated, block: B:113:0x0242  */
    /* JADX WARN: Code duplicated, block: B:116:0x024b  */
    /* JADX WARN: Code duplicated, block: B:121:0x025d A[Catch: all -> 0x029e, TryCatch #5 {all -> 0x029e, blocks: (B:7:0x0050, B:11:0x0082, B:16:0x008a, B:19:0x0090, B:23:0x0099, B:97:0x01f0, B:98:0x01fb, B:100:0x01fd, B:102:0x01ff, B:103:0x0207, B:105:0x0209, B:107:0x0227, B:109:0x0234, B:119:0x0254, B:120:0x025c, B:121:0x025d, B:122:0x026f, B:123:0x0270, B:124:0x029d), top: B:145:0x0050, inners: #8 }] */
    /* JADX WARN: Code duplicated, block: B:144:0x009e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:146:0x00cc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:19:0x0090 A[Catch: all -> 0x029e, TRY_LEAVE, TryCatch #5 {all -> 0x029e, blocks: (B:7:0x0050, B:11:0x0082, B:16:0x008a, B:19:0x0090, B:23:0x0099, B:97:0x01f0, B:98:0x01fb, B:100:0x01fd, B:102:0x01ff, B:103:0x0207, B:105:0x0209, B:107:0x0227, B:109:0x0234, B:119:0x0254, B:120:0x025c, B:121:0x025d, B:122:0x026f, B:123:0x0270, B:124:0x029d), top: B:145:0x0050, inners: #8 }] */
    /* JADX WARN: Code duplicated, block: B:21:0x0096 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:22:0x0098  */
    /* JADX WARN: Code duplicated, block: B:28:0x00a4 A[Catch: all -> 0x01ec, TryCatch #4 {, blocks: (B:26:0x009e, B:28:0x00a4, B:29:0x00a6, B:91:0x01e3, B:92:0x01eb), top: B:144:0x009e, outer: #8 }] */
    /* JADX WARN: Code duplicated, block: B:31:0x00a9 A[Catch: all -> 0x01ef, LoadingException -> 0x01fc, RemoteException -> 0x01fe, TRY_ENTER, TryCatch #8 {RemoteException -> 0x01fe, LoadingException -> 0x01fc, all -> 0x01ef, blocks: (B:25:0x009d, B:31:0x00a9, B:33:0x00b0, B:34:0x00cb, B:38:0x00d1, B:40:0x00d9, B:42:0x00dd, B:43:0x00e8, B:50:0x00f5, B:52:0x00fa, B:54:0x0121, B:56:0x0129, B:57:0x0130, B:58:0x0139, B:53:0x010e, B:61:0x013c, B:62:0x013d, B:63:0x0145, B:64:0x0146, B:65:0x014e, B:68:0x0151, B:69:0x0152, B:71:0x0171, B:73:0x0178, B:75:0x0180, B:81:0x01b9, B:83:0x01bf, B:85:0x01c8, B:86:0x01d0, B:76:0x018f, B:77:0x0197, B:79:0x019a, B:80:0x01aa, B:87:0x01d1, B:88:0x01d9, B:89:0x01da, B:90:0x01e2, B:95:0x01ee, B:44:0x00e9, B:48:0x00f0, B:49:0x00f4, B:26:0x009e, B:28:0x00a4, B:29:0x00a6, B:91:0x01e3, B:92:0x01eb, B:35:0x00cc, B:36:0x00ce), top: B:149:0x009d, inners: #3, #4, #6 }] */
    /* JADX WARN: Code duplicated, block: B:33:0x00b0 A[Catch: all -> 0x01ef, LoadingException -> 0x01fc, RemoteException -> 0x01fe, TryCatch #8 {RemoteException -> 0x01fe, LoadingException -> 0x01fc, all -> 0x01ef, blocks: (B:25:0x009d, B:31:0x00a9, B:33:0x00b0, B:34:0x00cb, B:38:0x00d1, B:40:0x00d9, B:42:0x00dd, B:43:0x00e8, B:50:0x00f5, B:52:0x00fa, B:54:0x0121, B:56:0x0129, B:57:0x0130, B:58:0x0139, B:53:0x010e, B:61:0x013c, B:62:0x013d, B:63:0x0145, B:64:0x0146, B:65:0x014e, B:68:0x0151, B:69:0x0152, B:71:0x0171, B:73:0x0178, B:75:0x0180, B:81:0x01b9, B:83:0x01bf, B:85:0x01c8, B:86:0x01d0, B:76:0x018f, B:77:0x0197, B:79:0x019a, B:80:0x01aa, B:87:0x01d1, B:88:0x01d9, B:89:0x01da, B:90:0x01e2, B:95:0x01ee, B:44:0x00e9, B:48:0x00f0, B:49:0x00f4, B:26:0x009e, B:28:0x00a4, B:29:0x00a6, B:91:0x01e3, B:92:0x01eb, B:35:0x00cc, B:36:0x00ce), top: B:149:0x009d, inners: #3, #4, #6 }] */
    /* JADX WARN: Code duplicated, block: B:38:0x00d1 A[Catch: all -> 0x01ef, LoadingException -> 0x01fc, RemoteException -> 0x01fe, TRY_ENTER, TryCatch #8 {RemoteException -> 0x01fe, LoadingException -> 0x01fc, all -> 0x01ef, blocks: (B:25:0x009d, B:31:0x00a9, B:33:0x00b0, B:34:0x00cb, B:38:0x00d1, B:40:0x00d9, B:42:0x00dd, B:43:0x00e8, B:50:0x00f5, B:52:0x00fa, B:54:0x0121, B:56:0x0129, B:57:0x0130, B:58:0x0139, B:53:0x010e, B:61:0x013c, B:62:0x013d, B:63:0x0145, B:64:0x0146, B:65:0x014e, B:68:0x0151, B:69:0x0152, B:71:0x0171, B:73:0x0178, B:75:0x0180, B:81:0x01b9, B:83:0x01bf, B:85:0x01c8, B:86:0x01d0, B:76:0x018f, B:77:0x0197, B:79:0x019a, B:80:0x01aa, B:87:0x01d1, B:88:0x01d9, B:89:0x01da, B:90:0x01e2, B:95:0x01ee, B:44:0x00e9, B:48:0x00f0, B:49:0x00f4, B:26:0x009e, B:28:0x00a4, B:29:0x00a6, B:91:0x01e3, B:92:0x01eb, B:35:0x00cc, B:36:0x00ce), top: B:149:0x009d, inners: #3, #4, #6 }] */
    /* JADX WARN: Code duplicated, block: B:64:0x0146 A[Catch: all -> 0x01ef, LoadingException -> 0x01fc, RemoteException -> 0x01fe, TryCatch #8 {RemoteException -> 0x01fe, LoadingException -> 0x01fc, all -> 0x01ef, blocks: (B:25:0x009d, B:31:0x00a9, B:33:0x00b0, B:34:0x00cb, B:38:0x00d1, B:40:0x00d9, B:42:0x00dd, B:43:0x00e8, B:50:0x00f5, B:52:0x00fa, B:54:0x0121, B:56:0x0129, B:57:0x0130, B:58:0x0139, B:53:0x010e, B:61:0x013c, B:62:0x013d, B:63:0x0145, B:64:0x0146, B:65:0x014e, B:68:0x0151, B:69:0x0152, B:71:0x0171, B:73:0x0178, B:75:0x0180, B:81:0x01b9, B:83:0x01bf, B:85:0x01c8, B:86:0x01d0, B:76:0x018f, B:77:0x0197, B:79:0x019a, B:80:0x01aa, B:87:0x01d1, B:88:0x01d9, B:89:0x01da, B:90:0x01e2, B:95:0x01ee, B:44:0x00e9, B:48:0x00f0, B:49:0x00f4, B:26:0x009e, B:28:0x00a4, B:29:0x00a6, B:91:0x01e3, B:92:0x01eb, B:35:0x00cc, B:36:0x00ce), top: B:149:0x009d, inners: #3, #4, #6 }] */
    /* JADX WARN: Code duplicated, block: B:69:0x0152 A[Catch: all -> 0x01ef, LoadingException -> 0x01fc, RemoteException -> 0x01fe, TryCatch #8 {RemoteException -> 0x01fe, LoadingException -> 0x01fc, all -> 0x01ef, blocks: (B:25:0x009d, B:31:0x00a9, B:33:0x00b0, B:34:0x00cb, B:38:0x00d1, B:40:0x00d9, B:42:0x00dd, B:43:0x00e8, B:50:0x00f5, B:52:0x00fa, B:54:0x0121, B:56:0x0129, B:57:0x0130, B:58:0x0139, B:53:0x010e, B:61:0x013c, B:62:0x013d, B:63:0x0145, B:64:0x0146, B:65:0x014e, B:68:0x0151, B:69:0x0152, B:71:0x0171, B:73:0x0178, B:75:0x0180, B:81:0x01b9, B:83:0x01bf, B:85:0x01c8, B:86:0x01d0, B:76:0x018f, B:77:0x0197, B:79:0x019a, B:80:0x01aa, B:87:0x01d1, B:88:0x01d9, B:89:0x01da, B:90:0x01e2, B:95:0x01ee, B:44:0x00e9, B:48:0x00f0, B:49:0x00f4, B:26:0x009e, B:28:0x00a4, B:29:0x00a6, B:91:0x01e3, B:92:0x01eb, B:35:0x00cc, B:36:0x00ce), top: B:149:0x009d, inners: #3, #4, #6 }] */
    /* JADX WARN: Code duplicated, block: B:71:0x0171 A[Catch: all -> 0x01ef, LoadingException -> 0x01fc, RemoteException -> 0x01fe, TryCatch #8 {RemoteException -> 0x01fe, LoadingException -> 0x01fc, all -> 0x01ef, blocks: (B:25:0x009d, B:31:0x00a9, B:33:0x00b0, B:34:0x00cb, B:38:0x00d1, B:40:0x00d9, B:42:0x00dd, B:43:0x00e8, B:50:0x00f5, B:52:0x00fa, B:54:0x0121, B:56:0x0129, B:57:0x0130, B:58:0x0139, B:53:0x010e, B:61:0x013c, B:62:0x013d, B:63:0x0145, B:64:0x0146, B:65:0x014e, B:68:0x0151, B:69:0x0152, B:71:0x0171, B:73:0x0178, B:75:0x0180, B:81:0x01b9, B:83:0x01bf, B:85:0x01c8, B:86:0x01d0, B:76:0x018f, B:77:0x0197, B:79:0x019a, B:80:0x01aa, B:87:0x01d1, B:88:0x01d9, B:89:0x01da, B:90:0x01e2, B:95:0x01ee, B:44:0x00e9, B:48:0x00f0, B:49:0x00f4, B:26:0x009e, B:28:0x00a4, B:29:0x00a6, B:91:0x01e3, B:92:0x01eb, B:35:0x00cc, B:36:0x00ce), top: B:149:0x009d, inners: #3, #4, #6 }] */
    /* JADX WARN: Code duplicated, block: B:73:0x0178 A[Catch: all -> 0x01ef, LoadingException -> 0x01fc, RemoteException -> 0x01fe, TryCatch #8 {RemoteException -> 0x01fe, LoadingException -> 0x01fc, all -> 0x01ef, blocks: (B:25:0x009d, B:31:0x00a9, B:33:0x00b0, B:34:0x00cb, B:38:0x00d1, B:40:0x00d9, B:42:0x00dd, B:43:0x00e8, B:50:0x00f5, B:52:0x00fa, B:54:0x0121, B:56:0x0129, B:57:0x0130, B:58:0x0139, B:53:0x010e, B:61:0x013c, B:62:0x013d, B:63:0x0145, B:64:0x0146, B:65:0x014e, B:68:0x0151, B:69:0x0152, B:71:0x0171, B:73:0x0178, B:75:0x0180, B:81:0x01b9, B:83:0x01bf, B:85:0x01c8, B:86:0x01d0, B:76:0x018f, B:77:0x0197, B:79:0x019a, B:80:0x01aa, B:87:0x01d1, B:88:0x01d9, B:89:0x01da, B:90:0x01e2, B:95:0x01ee, B:44:0x00e9, B:48:0x00f0, B:49:0x00f4, B:26:0x009e, B:28:0x00a4, B:29:0x00a6, B:91:0x01e3, B:92:0x01eb, B:35:0x00cc, B:36:0x00ce), top: B:149:0x009d, inners: #3, #4, #6 }] */
    /* JADX WARN: Code duplicated, block: B:75:0x0180 A[Catch: all -> 0x01ef, LoadingException -> 0x01fc, RemoteException -> 0x01fe, TryCatch #8 {RemoteException -> 0x01fe, LoadingException -> 0x01fc, all -> 0x01ef, blocks: (B:25:0x009d, B:31:0x00a9, B:33:0x00b0, B:34:0x00cb, B:38:0x00d1, B:40:0x00d9, B:42:0x00dd, B:43:0x00e8, B:50:0x00f5, B:52:0x00fa, B:54:0x0121, B:56:0x0129, B:57:0x0130, B:58:0x0139, B:53:0x010e, B:61:0x013c, B:62:0x013d, B:63:0x0145, B:64:0x0146, B:65:0x014e, B:68:0x0151, B:69:0x0152, B:71:0x0171, B:73:0x0178, B:75:0x0180, B:81:0x01b9, B:83:0x01bf, B:85:0x01c8, B:86:0x01d0, B:76:0x018f, B:77:0x0197, B:79:0x019a, B:80:0x01aa, B:87:0x01d1, B:88:0x01d9, B:89:0x01da, B:90:0x01e2, B:95:0x01ee, B:44:0x00e9, B:48:0x00f0, B:49:0x00f4, B:26:0x009e, B:28:0x00a4, B:29:0x00a6, B:91:0x01e3, B:92:0x01eb, B:35:0x00cc, B:36:0x00ce), top: B:149:0x009d, inners: #3, #4, #6 }] */
    /* JADX WARN: Code duplicated, block: B:76:0x018f A[Catch: all -> 0x01ef, LoadingException -> 0x01fc, RemoteException -> 0x01fe, TryCatch #8 {RemoteException -> 0x01fe, LoadingException -> 0x01fc, all -> 0x01ef, blocks: (B:25:0x009d, B:31:0x00a9, B:33:0x00b0, B:34:0x00cb, B:38:0x00d1, B:40:0x00d9, B:42:0x00dd, B:43:0x00e8, B:50:0x00f5, B:52:0x00fa, B:54:0x0121, B:56:0x0129, B:57:0x0130, B:58:0x0139, B:53:0x010e, B:61:0x013c, B:62:0x013d, B:63:0x0145, B:64:0x0146, B:65:0x014e, B:68:0x0151, B:69:0x0152, B:71:0x0171, B:73:0x0178, B:75:0x0180, B:81:0x01b9, B:83:0x01bf, B:85:0x01c8, B:86:0x01d0, B:76:0x018f, B:77:0x0197, B:79:0x019a, B:80:0x01aa, B:87:0x01d1, B:88:0x01d9, B:89:0x01da, B:90:0x01e2, B:95:0x01ee, B:44:0x00e9, B:48:0x00f0, B:49:0x00f4, B:26:0x009e, B:28:0x00a4, B:29:0x00a6, B:91:0x01e3, B:92:0x01eb, B:35:0x00cc, B:36:0x00ce), top: B:149:0x009d, inners: #3, #4, #6 }] */
    /* JADX WARN: Code duplicated, block: B:78:0x0198 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:79:0x019a A[Catch: all -> 0x01ef, LoadingException -> 0x01fc, RemoteException -> 0x01fe, TryCatch #8 {RemoteException -> 0x01fe, LoadingException -> 0x01fc, all -> 0x01ef, blocks: (B:25:0x009d, B:31:0x00a9, B:33:0x00b0, B:34:0x00cb, B:38:0x00d1, B:40:0x00d9, B:42:0x00dd, B:43:0x00e8, B:50:0x00f5, B:52:0x00fa, B:54:0x0121, B:56:0x0129, B:57:0x0130, B:58:0x0139, B:53:0x010e, B:61:0x013c, B:62:0x013d, B:63:0x0145, B:64:0x0146, B:65:0x014e, B:68:0x0151, B:69:0x0152, B:71:0x0171, B:73:0x0178, B:75:0x0180, B:81:0x01b9, B:83:0x01bf, B:85:0x01c8, B:86:0x01d0, B:76:0x018f, B:77:0x0197, B:79:0x019a, B:80:0x01aa, B:87:0x01d1, B:88:0x01d9, B:89:0x01da, B:90:0x01e2, B:95:0x01ee, B:44:0x00e9, B:48:0x00f0, B:49:0x00f4, B:26:0x009e, B:28:0x00a4, B:29:0x00a6, B:91:0x01e3, B:92:0x01eb, B:35:0x00cc, B:36:0x00ce), top: B:149:0x009d, inners: #3, #4, #6 }] */
    /* JADX WARN: Code duplicated, block: B:80:0x01aa A[Catch: all -> 0x01ef, LoadingException -> 0x01fc, RemoteException -> 0x01fe, TryCatch #8 {RemoteException -> 0x01fe, LoadingException -> 0x01fc, all -> 0x01ef, blocks: (B:25:0x009d, B:31:0x00a9, B:33:0x00b0, B:34:0x00cb, B:38:0x00d1, B:40:0x00d9, B:42:0x00dd, B:43:0x00e8, B:50:0x00f5, B:52:0x00fa, B:54:0x0121, B:56:0x0129, B:57:0x0130, B:58:0x0139, B:53:0x010e, B:61:0x013c, B:62:0x013d, B:63:0x0145, B:64:0x0146, B:65:0x014e, B:68:0x0151, B:69:0x0152, B:71:0x0171, B:73:0x0178, B:75:0x0180, B:81:0x01b9, B:83:0x01bf, B:85:0x01c8, B:86:0x01d0, B:76:0x018f, B:77:0x0197, B:79:0x019a, B:80:0x01aa, B:87:0x01d1, B:88:0x01d9, B:89:0x01da, B:90:0x01e2, B:95:0x01ee, B:44:0x00e9, B:48:0x00f0, B:49:0x00f4, B:26:0x009e, B:28:0x00a4, B:29:0x00a6, B:91:0x01e3, B:92:0x01eb, B:35:0x00cc, B:36:0x00ce), top: B:149:0x009d, inners: #3, #4, #6 }] */
    /* JADX WARN: Code duplicated, block: B:83:0x01bf A[Catch: all -> 0x01ef, LoadingException -> 0x01fc, RemoteException -> 0x01fe, TryCatch #8 {RemoteException -> 0x01fe, LoadingException -> 0x01fc, all -> 0x01ef, blocks: (B:25:0x009d, B:31:0x00a9, B:33:0x00b0, B:34:0x00cb, B:38:0x00d1, B:40:0x00d9, B:42:0x00dd, B:43:0x00e8, B:50:0x00f5, B:52:0x00fa, B:54:0x0121, B:56:0x0129, B:57:0x0130, B:58:0x0139, B:53:0x010e, B:61:0x013c, B:62:0x013d, B:63:0x0145, B:64:0x0146, B:65:0x014e, B:68:0x0151, B:69:0x0152, B:71:0x0171, B:73:0x0178, B:75:0x0180, B:81:0x01b9, B:83:0x01bf, B:85:0x01c8, B:86:0x01d0, B:76:0x018f, B:77:0x0197, B:79:0x019a, B:80:0x01aa, B:87:0x01d1, B:88:0x01d9, B:89:0x01da, B:90:0x01e2, B:95:0x01ee, B:44:0x00e9, B:48:0x00f0, B:49:0x00f4, B:26:0x009e, B:28:0x00a4, B:29:0x00a6, B:91:0x01e3, B:92:0x01eb, B:35:0x00cc, B:36:0x00ce), top: B:149:0x009d, inners: #3, #4, #6 }] */
    /* JADX WARN: Code duplicated, block: B:85:0x01c8 A[Catch: all -> 0x01ef, LoadingException -> 0x01fc, RemoteException -> 0x01fe, TryCatch #8 {RemoteException -> 0x01fe, LoadingException -> 0x01fc, all -> 0x01ef, blocks: (B:25:0x009d, B:31:0x00a9, B:33:0x00b0, B:34:0x00cb, B:38:0x00d1, B:40:0x00d9, B:42:0x00dd, B:43:0x00e8, B:50:0x00f5, B:52:0x00fa, B:54:0x0121, B:56:0x0129, B:57:0x0130, B:58:0x0139, B:53:0x010e, B:61:0x013c, B:62:0x013d, B:63:0x0145, B:64:0x0146, B:65:0x014e, B:68:0x0151, B:69:0x0152, B:71:0x0171, B:73:0x0178, B:75:0x0180, B:81:0x01b9, B:83:0x01bf, B:85:0x01c8, B:86:0x01d0, B:76:0x018f, B:77:0x0197, B:79:0x019a, B:80:0x01aa, B:87:0x01d1, B:88:0x01d9, B:89:0x01da, B:90:0x01e2, B:95:0x01ee, B:44:0x00e9, B:48:0x00f0, B:49:0x00f4, B:26:0x009e, B:28:0x00a4, B:29:0x00a6, B:91:0x01e3, B:92:0x01eb, B:35:0x00cc, B:36:0x00ce), top: B:149:0x009d, inners: #3, #4, #6 }] */
    /* JADX WARN: Code duplicated, block: B:87:0x01d1 A[Catch: all -> 0x01ef, LoadingException -> 0x01fc, RemoteException -> 0x01fe, TryCatch #8 {RemoteException -> 0x01fe, LoadingException -> 0x01fc, all -> 0x01ef, blocks: (B:25:0x009d, B:31:0x00a9, B:33:0x00b0, B:34:0x00cb, B:38:0x00d1, B:40:0x00d9, B:42:0x00dd, B:43:0x00e8, B:50:0x00f5, B:52:0x00fa, B:54:0x0121, B:56:0x0129, B:57:0x0130, B:58:0x0139, B:53:0x010e, B:61:0x013c, B:62:0x013d, B:63:0x0145, B:64:0x0146, B:65:0x014e, B:68:0x0151, B:69:0x0152, B:71:0x0171, B:73:0x0178, B:75:0x0180, B:81:0x01b9, B:83:0x01bf, B:85:0x01c8, B:86:0x01d0, B:76:0x018f, B:77:0x0197, B:79:0x019a, B:80:0x01aa, B:87:0x01d1, B:88:0x01d9, B:89:0x01da, B:90:0x01e2, B:95:0x01ee, B:44:0x00e9, B:48:0x00f0, B:49:0x00f4, B:26:0x009e, B:28:0x00a4, B:29:0x00a6, B:91:0x01e3, B:92:0x01eb, B:35:0x00cc, B:36:0x00ce), top: B:149:0x009d, inners: #3, #4, #6 }] */
    /* JADX WARN: Code duplicated, block: B:89:0x01da A[Catch: all -> 0x01ef, LoadingException -> 0x01fc, RemoteException -> 0x01fe, TryCatch #8 {RemoteException -> 0x01fe, LoadingException -> 0x01fc, all -> 0x01ef, blocks: (B:25:0x009d, B:31:0x00a9, B:33:0x00b0, B:34:0x00cb, B:38:0x00d1, B:40:0x00d9, B:42:0x00dd, B:43:0x00e8, B:50:0x00f5, B:52:0x00fa, B:54:0x0121, B:56:0x0129, B:57:0x0130, B:58:0x0139, B:53:0x010e, B:61:0x013c, B:62:0x013d, B:63:0x0145, B:64:0x0146, B:65:0x014e, B:68:0x0151, B:69:0x0152, B:71:0x0171, B:73:0x0178, B:75:0x0180, B:81:0x01b9, B:83:0x01bf, B:85:0x01c8, B:86:0x01d0, B:76:0x018f, B:77:0x0197, B:79:0x019a, B:80:0x01aa, B:87:0x01d1, B:88:0x01d9, B:89:0x01da, B:90:0x01e2, B:95:0x01ee, B:44:0x00e9, B:48:0x00f0, B:49:0x00f4, B:26:0x009e, B:28:0x00a4, B:29:0x00a6, B:91:0x01e3, B:92:0x01eb, B:35:0x00cc, B:36:0x00ce), top: B:149:0x009d, inners: #3, #4, #6 }] */
    /* JADX WARN: Code duplicated, block: B:91:0x01e3 A[Catch: all -> 0x01ec, TRY_ENTER, TryCatch #4 {, blocks: (B:26:0x009e, B:28:0x00a4, B:29:0x00a6, B:91:0x01e3, B:92:0x01eb), top: B:144:0x009e, outer: #8 }] */
    /* JADX WARN: Instruction removed from duplicated block: B:121:0x025d, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:33:0x00b0, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:69:0x0152, please report this as an issue */
    public static DynamiteModule load(Context context, VersionPolicy versionPolicy, String str) throws Throwable {
        long j;
        DynamiteModule dynamiteModuleZzc;
        int i;
        Boolean bool;
        zzp zzpVarZzg;
        int iZze;
        IObjectWrapper iObjectWrapperZzh;
        Object objUnwrap;
        DynamiteModule dynamiteModule;
        zzm zzmVar;
        zzq zzqVar;
        zzm zzmVar2;
        boolean z;
        Boolean boolValueOf;
        IObjectWrapper iObjectWrapperZze;
        Cursor cursor;
        Context applicationContext = context.getApplicationContext();
        zzo zzoVar = null;
        if (applicationContext == null) {
            throw new LoadingException("null application Context", null);
        }
        ThreadLocal threadLocal = zzg;
        zzm zzmVar3 = (zzm) threadLocal.get();
        zzm zzmVar4 = new zzm(null);
        threadLocal.set(zzmVar4);
        ThreadLocal threadLocal2 = zzh;
        Long l = (Long) threadLocal2.get();
        long jLongValue = l.longValue();
        try {
            threadLocal2.set(Long.valueOf(SystemClock.uptimeMillis()));
            VersionPolicy.SelectionResult selectionResultSelectModule = versionPolicy.selectModule(context, str, zzi);
            int i2 = selectionResultSelectModule.localVersion;
            j = jLongValue;
            try {
                Log.i("DynamiteModule", "Considering local module " + str + ":" + i2 + " and remote module " + str + ":" + selectionResultSelectModule.remoteVersion);
                int i3 = selectionResultSelectModule.selection;
                if (i3 != 0) {
                    if (i3 != -1) {
                        if (i3 == 1 || selectionResultSelectModule.remoteVersion != 0) {
                            if (i3 == -1) {
                                dynamiteModuleZzc = zzc(applicationContext, str);
                            } else {
                                if (i3 == 1) {
                                    throw new LoadingException("VersionPolicy returned invalid code:" + i3, null);
                                }
                                try {
                                    i = selectionResultSelectModule.remoteVersion;
                                    try {
                                        synchronized (DynamiteModule.class) {
                                            if (zzf(context)) {
                                                throw new LoadingException("Remote loading disabled", null);
                                            }
                                            bool = zzb;
                                        }
                                        if (bool != null) {
                                            throw new LoadingException("Failed to determine which loading route to use.", null);
                                        }
                                        if (bool.booleanValue()) {
                                            Log.i("DynamiteModule", "Selected remote version of " + str + ", version >= " + i);
                                            synchronized (DynamiteModule.class) {
                                                zzqVar = zzl;
                                            }
                                            if (zzqVar != null) {
                                                throw new LoadingException("DynamiteLoaderV2 was not cached.", null);
                                            }
                                            zzmVar2 = (zzm) threadLocal.get();
                                            if (zzmVar2 != null || zzmVar2.zza == null) {
                                                throw new LoadingException("No result cursor", null);
                                            }
                                            Context applicationContext2 = context.getApplicationContext();
                                            Cursor cursor2 = zzmVar2.zza;
                                            ObjectWrapper.wrap(null);
                                            synchronized (DynamiteModule.class) {
                                                z = zze >= 2;
                                                boolValueOf = Boolean.valueOf(z);
                                            }
                                            boolValueOf.getClass();
                                            if (z) {
                                                Log.v("DynamiteModule", "Dynamite loader version >= 2, using loadModule2NoCrashUtils");
                                                iObjectWrapperZze = zzqVar.zzf(ObjectWrapper.wrap(applicationContext2), str, i, ObjectWrapper.wrap(cursor2));
                                            } else {
                                                Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to loadModule2");
                                                iObjectWrapperZze = zzqVar.zze(ObjectWrapper.wrap(applicationContext2), str, i, ObjectWrapper.wrap(cursor2));
                                            }
                                            Context context2 = (Context) ObjectWrapper.unwrap(iObjectWrapperZze);
                                            if (context2 == null) {
                                                throw new LoadingException("Failed to get module context", zzoVar);
                                            }
                                            dynamiteModule = new DynamiteModule(context2);
                                        } else {
                                            Log.i("DynamiteModule", "Selected remote version of " + str + ", version >= " + i);
                                            zzpVarZzg = zzg(context);
                                            if (zzpVarZzg != null) {
                                                throw new LoadingException("Failed to create IDynamiteLoader.", null);
                                            }
                                            iZze = zzpVarZzg.zze();
                                            if (iZze >= 3) {
                                                zzmVar = (zzm) threadLocal.get();
                                                if (zzmVar != null) {
                                                    throw new LoadingException("No cached result cursor holder", null);
                                                }
                                                iObjectWrapperZzh = zzpVarZzg.zzi(ObjectWrapper.wrap(context), str, i, ObjectWrapper.wrap(zzmVar.zza));
                                            } else if (iZze == 2) {
                                                Log.w("DynamiteModule", "IDynamite loader version = 2");
                                                iObjectWrapperZzh = zzpVarZzg.zzj(ObjectWrapper.wrap(context), str, i);
                                            } else {
                                                Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to createModuleContext");
                                                iObjectWrapperZzh = zzpVarZzg.zzh(ObjectWrapper.wrap(context), str, i);
                                            }
                                            objUnwrap = ObjectWrapper.unwrap(iObjectWrapperZzh);
                                            if (objUnwrap != null) {
                                                throw new LoadingException("Failed to load remote module.", null);
                                            }
                                            dynamiteModule = new DynamiteModule((Context) objUnwrap);
                                        }
                                        dynamiteModuleZzc = dynamiteModule;
                                    } catch (RemoteException e) {
                                        throw new LoadingException("Failed to load remote module.", e, null);
                                    } catch (LoadingException e2) {
                                        throw e2;
                                    } catch (Throwable th) {
                                        CrashUtils.addDynamiteErrorToDropBox(context, th);
                                        throw new LoadingException("Failed to load remote module.", th, null);
                                    }
                                } catch (LoadingException e3) {
                                    Log.w("DynamiteModule", "Failed to load remote module: " + e3.getMessage());
                                    int i4 = selectionResultSelectModule.localVersion;
                                    if (i4 == 0 || versionPolicy.selectModule(context, str, new zzn(i4, 0)).selection != -1) {
                                        throw new LoadingException("Remote load failed. No local fallback found.", e3, null);
                                    }
                                    dynamiteModuleZzc = zzc(applicationContext, str);
                                }
                            }
                            if (j == 0) {
                                zzh.remove();
                            } else {
                                zzh.set(l);
                            }
                            cursor = zzmVar4.zza;
                            if (cursor != null) {
                                cursor.close();
                            }
                            zzg.set(zzmVar3);
                            return dynamiteModuleZzc;
                        }
                    } else if (selectionResultSelectModule.localVersion != 0) {
                        i3 = -1;
                        if (i3 == 1) {
                        }
                        if (i3 == -1) {
                            dynamiteModuleZzc = zzc(applicationContext, str);
                        } else {
                            if (i3 == 1) {
                                throw new LoadingException("VersionPolicy returned invalid code:" + i3, null);
                            }
                            i = selectionResultSelectModule.remoteVersion;
                            synchronized (DynamiteModule.class) {
                                if (zzf(context)) {
                                    throw new LoadingException("Remote loading disabled", null);
                                }
                                bool = zzb;
                                if (bool != null) {
                                    throw new LoadingException("Failed to determine which loading route to use.", null);
                                }
                                if (bool.booleanValue()) {
                                    Log.i("DynamiteModule", "Selected remote version of " + str + ", version >= " + i);
                                    synchronized (DynamiteModule.class) {
                                        zzqVar = zzl;
                                        if (zzqVar != null) {
                                            throw new LoadingException("DynamiteLoaderV2 was not cached.", null);
                                        }
                                        zzmVar2 = (zzm) threadLocal.get();
                                        if (zzmVar2 != null) {
                                        }
                                        throw new LoadingException("No result cursor", null);
                                    }
                                }
                                Log.i("DynamiteModule", "Selected remote version of " + str + ", version >= " + i);
                                zzpVarZzg = zzg(context);
                                if (zzpVarZzg != null) {
                                    throw new LoadingException("Failed to create IDynamiteLoader.", null);
                                }
                                iZze = zzpVarZzg.zze();
                                if (iZze >= 3) {
                                    zzmVar = (zzm) threadLocal.get();
                                    if (zzmVar != null) {
                                        throw new LoadingException("No cached result cursor holder", null);
                                    }
                                    iObjectWrapperZzh = zzpVarZzg.zzi(ObjectWrapper.wrap(context), str, i, ObjectWrapper.wrap(zzmVar.zza));
                                } else if (iZze == 2) {
                                    Log.w("DynamiteModule", "IDynamite loader version = 2");
                                    iObjectWrapperZzh = zzpVarZzg.zzj(ObjectWrapper.wrap(context), str, i);
                                } else {
                                    Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to createModuleContext");
                                    iObjectWrapperZzh = zzpVarZzg.zzh(ObjectWrapper.wrap(context), str, i);
                                }
                                objUnwrap = ObjectWrapper.unwrap(iObjectWrapperZzh);
                                if (objUnwrap != null) {
                                    throw new LoadingException("Failed to load remote module.", null);
                                }
                                dynamiteModule = new DynamiteModule((Context) objUnwrap);
                                dynamiteModuleZzc = dynamiteModule;
                            }
                        }
                        if (j == 0) {
                            zzh.remove();
                        } else {
                            zzh.set(l);
                        }
                        cursor = zzmVar4.zza;
                        if (cursor != null) {
                            cursor.close();
                        }
                        zzg.set(zzmVar3);
                        return dynamiteModuleZzc;
                    }
                }
                throw new LoadingException("No acceptable module " + str + " found. Local version is " + selectionResultSelectModule.localVersion + " and remote version is " + selectionResultSelectModule.remoteVersion + ".", null);
            } catch (Throwable th2) {
                th = th2;
                if (j == 0) {
                    zzh.remove();
                } else {
                    zzh.set(l);
                }
                Cursor cursor3 = zzmVar4.zza;
                if (cursor3 != null) {
                    cursor3.close();
                }
                zzg.set(zzmVar3);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            j = jLongValue;
        }
    }

    /* JADX INFO: Removed unreachable split cross block B:131:0x01c7 */
    /* JADX WARN: Code duplicated, block: B:95:0x0172 A[Catch: all -> 0x01d5, TRY_ENTER, TRY_LEAVE, TryCatch #8 {all -> 0x01d5, blocks: (B:3:0x0002, B:61:0x00e3, B:63:0x00e9, B:69:0x010b, B:91:0x0163, B:95:0x0172, B:116:0x01cd, B:117:0x01d0, B:111:0x01c4, B:67:0x00f0, B:121:0x01d4, B:4:0x0003, B:7:0x0009, B:8:0x0025, B:59:0x00e0, B:19:0x0046, B:41:0x00a2, B:44:0x00a5, B:52:0x00be, B:60:0x00e2, B:58:0x00c4), top: B:137:0x0002, inners: #6, #7 }] */
    public static int zza(Context context, String str, boolean z) {
        Throwable th;
        RemoteException remoteException;
        Cursor cursor;
        try {
            synchronized (DynamiteModule.class) {
                Boolean bool = zzb;
                Cursor cursor2 = null;
                int iZzf = 0;
                if (bool == null) {
                    try {
                        Field declaredField = context.getApplicationContext().getClassLoader().loadClass(DynamiteLoaderClassLoader.class.getName()).getDeclaredField("sClassLoader");
                        synchronized (declaredField.getDeclaringClass()) {
                            try {
                                ClassLoader classLoader = (ClassLoader) declaredField.get(null);
                                if (classLoader == ClassLoader.getSystemClassLoader()) {
                                    bool = Boolean.FALSE;
                                } else if (classLoader != null) {
                                    try {
                                        zzd(classLoader);
                                    } catch (LoadingException unused) {
                                    }
                                    bool = Boolean.TRUE;
                                } else {
                                    if (!zzf(context)) {
                                        return 0;
                                    }
                                    if (zzd || Boolean.TRUE.equals(null)) {
                                        declaredField.set(null, ClassLoader.getSystemClassLoader());
                                        bool = Boolean.FALSE;
                                    } else {
                                        try {
                                            int iZzb = zzb(context, str, z, true);
                                            String str2 = zzc;
                                            if (str2 != null && !str2.isEmpty()) {
                                                ClassLoader classLoaderZza = zzb.zza();
                                                if (classLoaderZza == null) {
                                                    if (Build.VERSION.SDK_INT >= 29) {
                                                        ScaleDrawable$$ExternalSyntheticApiModelOutline0.m$2();
                                                        String str3 = zzc;
                                                        Preconditions.checkNotNull(str3);
                                                        classLoaderZza = ScaleDrawable$$ExternalSyntheticApiModelOutline0.m(str3, ClassLoader.getSystemClassLoader());
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
                                }
                                zzb = bool;
                            } catch (Throwable th2) {
                                throw th2;
                            }
                        }
                    } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
                        Log.w("DynamiteModule", "Failed to load module via V2: " + e.toString());
                        bool = Boolean.FALSE;
                    }
                }
                if (bool.booleanValue()) {
                    try {
                        return zzb(context, str, z, false);
                    } catch (LoadingException e2) {
                        Log.w("DynamiteModule", "Failed to retrieve remote module version: " + e2.getMessage());
                        return 0;
                    }
                }
                zzp zzpVarZzg = zzg(context);
                try {
                    if (zzpVarZzg != null) {
                        try {
                            int iZze = zzpVarZzg.zze();
                            if (iZze >= 3) {
                                zzm zzmVar = (zzm) zzg.get();
                                if (zzmVar == null || (cursor = zzmVar.zza) == null) {
                                    Cursor cursor3 = (Cursor) ObjectWrapper.unwrap(zzpVarZzg.zzk(ObjectWrapper.wrap(context), str, z, ((Long) zzh.get()).longValue()));
                                    if (cursor3 != null) {
                                        try {
                                            if (cursor3.moveToFirst()) {
                                                int i = cursor3.getInt(0);
                                                cursor2 = (i <= 0 || !zze(cursor3)) ? cursor3 : null;
                                                if (cursor2 != null) {
                                                    cursor2.close();
                                                }
                                                iZzf = i;
                                            } else {
                                                Log.w("DynamiteModule", "Failed to retrieve remote module version.");
                                                if (cursor3 != null) {
                                                    cursor3.close();
                                                }
                                            }
                                        } catch (RemoteException e3) {
                                            remoteException = e3;
                                            cursor2 = cursor3;
                                            Log.w("DynamiteModule", "Failed to retrieve remote module version: " + remoteException.getMessage());
                                            if (cursor2 != null) {
                                                cursor2.close();
                                            }
                                        } catch (Throwable th3) {
                                            th = th3;
                                            cursor2 = cursor3;
                                            if (cursor2 == null) {
                                                throw th;
                                            }
                                            cursor2.close();
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
                                iZzf = zzpVarZzg.zzg(ObjectWrapper.wrap(context), str, z);
                            } else {
                                Log.w("DynamiteModule", "IDynamite loader version < 2, falling back to getModuleVersion2");
                                iZzf = zzpVarZzg.zzf(ObjectWrapper.wrap(context), str, z);
                            }
                        } catch (RemoteException e4) {
                            remoteException = e4;
                        }
                    }
                    return iZzf;
                } catch (Throwable th4) {
                    th = th4;
                }
            }
        } catch (Throwable th5) {
            CrashUtils.addDynamiteErrorToDropBox(context, th5);
            throw th5;
        }
    }

    /* JADX WARN: Code duplicated, block: B:104:0x0175  */
    /* JADX WARN: Code duplicated, block: B:125:? A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 3, insn: 0x0172: MOVE (r1 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:102:0x0171 */
    private static int zzb(Context context, String str, boolean z, boolean z2) throws Throwable {
        Throwable th;
        Exception exc;
        Cursor cursor;
        MatrixCursor matrixCursor;
        Cursor cursor2 = null;
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        Object[] objArr3 = 0;
        try {
            try {
                boolean z3 = true;
                Uri uriBuild = new Uri.Builder().scheme("content").authority("com.google.android.gms.chimera").path(true != z ? "api" : "api_force_staging").appendPath(str).appendQueryParameter("requestStartUptime", String.valueOf(((Long) zzh.get()).longValue())).build();
                ContentProviderClient contentProviderClientAcquireUnstableContentProviderClient = context.getContentResolver().acquireUnstableContentProviderClient(uriBuild);
                boolean z4 = false;
                if (contentProviderClientAcquireUnstableContentProviderClient == null) {
                    matrixCursor = null;
                } else {
                    try {
                        Cursor cursorQuery = contentProviderClientAcquireUnstableContentProviderClient.query(uriBuild, null, null, null, null);
                        if (cursorQuery == null) {
                            contentProviderClientAcquireUnstableContentProviderClient.release();
                            matrixCursor = null;
                        } else {
                            try {
                                int count = cursorQuery.getCount();
                                int columnCount = cursorQuery.getColumnCount();
                                matrixCursor = new MatrixCursor(cursorQuery.getColumnNames(), count);
                                for (int i = 0; i < count; i++) {
                                    if (!cursorQuery.moveToPosition(i)) {
                                        throw new RemoteException("Cursor read incomplete (ContentProvider dead?)");
                                    }
                                    Object[] objArr4 = new Object[columnCount];
                                    for (int i2 = 0; i2 < columnCount; i2++) {
                                        int type = cursorQuery.getType(i2);
                                        if (type == 0) {
                                            objArr4[i2] = null;
                                        } else if (type == 1) {
                                            objArr4[i2] = Long.valueOf(cursorQuery.getLong(i2));
                                        } else if (type == 2) {
                                            objArr4[i2] = Double.valueOf(cursorQuery.getDouble(i2));
                                        } else if (type == 3) {
                                            objArr4[i2] = cursorQuery.getString(i2);
                                        } else {
                                            if (type != 4) {
                                                throw new RemoteException("Unknown column type");
                                            }
                                            objArr4[i2] = cursorQuery.getBlob(i2);
                                        }
                                    }
                                    matrixCursor.addRow(objArr4);
                                }
                                cursorQuery.close();
                                contentProviderClientAcquireUnstableContentProviderClient.release();
                            } catch (Throwable th2) {
                                try {
                                    cursorQuery.close();
                                    throw th2;
                                } catch (Throwable th3) {
                                    th2.addSuppressed(th3);
                                    throw th2;
                                }
                            }
                        }
                    } catch (RemoteException unused) {
                    } catch (Throwable th4) {
                        contentProviderClientAcquireUnstableContentProviderClient.release();
                        throw th4;
                    }
                }
                if (matrixCursor != null) {
                    try {
                        if (matrixCursor.moveToFirst()) {
                            int i3 = matrixCursor.getInt(0);
                            if (i3 > 0) {
                                synchronized (DynamiteModule.class) {
                                    zzc = matrixCursor.getString(2);
                                    int columnIndex = matrixCursor.getColumnIndex("loaderVersion");
                                    if (columnIndex >= 0) {
                                        zze = matrixCursor.getInt(columnIndex);
                                    }
                                    int columnIndex2 = matrixCursor.getColumnIndex("disableStandaloneDynamiteLoader2");
                                    if (columnIndex2 >= 0) {
                                        if (matrixCursor.getInt(columnIndex2) == 0) {
                                            z3 = false;
                                        }
                                        zzd = z3;
                                        z4 = z3;
                                    }
                                }
                                if (zze(matrixCursor)) {
                                    matrixCursor = null;
                                }
                            }
                            if (z2 && z4) {
                                throw new LoadingException("forcing fallback to container DynamiteLoader impl", objArr2 == true ? 1 : 0);
                            }
                            if (matrixCursor != null) {
                                matrixCursor.close();
                            }
                            return i3;
                        }
                    } catch (Exception e) {
                        exc = e;
                        if (exc instanceof LoadingException) {
                            throw exc;
                        }
                        throw new LoadingException("V2 version check failed: " + exc.getMessage(), exc, objArr == true ? 1 : 0);
                    }
                }
                Log.w("DynamiteModule", "Failed to retrieve remote module version.");
                throw new LoadingException("Failed to connect to dynamite module ContentResolver.", objArr3 == true ? 1 : 0);
            } catch (Throwable th5) {
                th = th5;
                cursor2 = cursor;
                if (cursor2 != null) {
                    throw th;
                }
                cursor2.close();
                throw th;
            }
        } catch (Exception e2) {
            exc = e2;
        } catch (Throwable th6) {
            th = th6;
            if (cursor2 != null) {
                throw th;
            }
            cursor2.close();
            throw th;
        }
    }

    private static DynamiteModule zzc(Context context, String str) {
        Log.i("DynamiteModule", "Selected local version of ".concat(String.valueOf(str)));
        return new DynamiteModule(context);
    }

    private static void zzd(ClassLoader classLoader) throws LoadingException {
        zzq zzqVar;
        zzo zzoVar = null;
        try {
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(null).newInstance(null);
            if (iBinder == null) {
                zzqVar = null;
            } else {
                IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                zzqVar = iInterfaceQueryLocalInterface instanceof zzq ? (zzq) iInterfaceQueryLocalInterface : new zzq(iBinder);
            }
            zzl = zzqVar;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new LoadingException("Failed to instantiate dynamite loader", e, zzoVar);
        }
    }

    private static boolean zze(Cursor cursor) {
        zzm zzmVar = (zzm) zzg.get();
        if (zzmVar == null || zzmVar.zza != null) {
            return false;
        }
        zzmVar.zza = cursor;
        return true;
    }

    private static boolean zzf(Context context) {
        if (Boolean.TRUE.equals(null) || Boolean.TRUE.equals(zzf)) {
            return true;
        }
        boolean z = false;
        if (zzf == null) {
            ProviderInfo providerInfoResolveContentProvider = context.getPackageManager().resolveContentProvider("com.google.android.gms.chimera", true != PlatformVersion.isAtLeastQ() ? 0 : 268435456);
            if (GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context, 10000000) == 0 && providerInfoResolveContentProvider != null && "com.google.android.gms".equals(providerInfoResolveContentProvider.packageName)) {
                z = true;
            }
            Boolean boolValueOf = Boolean.valueOf(z);
            zzf = boolValueOf;
            boolValueOf.getClass();
            if (z && providerInfoResolveContentProvider.applicationInfo != null && (providerInfoResolveContentProvider.applicationInfo.flags & 129) == 0) {
                Log.i("DynamiteModule", "Non-system-image GmsCore APK, forcing V1");
                zzd = true;
            }
        }
        if (!z) {
            Log.e("DynamiteModule", "Invalid GmsCore APK, remote loading disabled.");
        }
        return z;
    }

    private static zzp zzg(Context context) {
        zzp zzpVar;
        synchronized (DynamiteModule.class) {
            zzp zzpVar2 = zzk;
            if (zzpVar2 != null) {
                return zzpVar2;
            }
            try {
                IBinder iBinder = (IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                if (iBinder == null) {
                    zzpVar = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                    zzpVar = iInterfaceQueryLocalInterface instanceof zzp ? (zzp) iInterfaceQueryLocalInterface : new zzp(iBinder);
                }
                if (zzpVar != null) {
                    zzk = zzpVar;
                    return zzpVar;
                }
            } catch (Exception e) {
                Log.e("DynamiteModule", "Failed to load IDynamiteLoader from GmsCore: " + e.getMessage());
            }
            return null;
        }
    }

    public Context getModuleContext() {
        return this.zzj;
    }

    public IBinder instantiate(String str) throws LoadingException {
        try {
            return (IBinder) this.zzj.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new LoadingException("Failed to instantiate module class: ".concat(String.valueOf(str)), e, null);
        }
    }
}
