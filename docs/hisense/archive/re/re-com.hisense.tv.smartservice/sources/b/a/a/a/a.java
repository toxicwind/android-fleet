package b.a.a.a;

import android.util.Log;
import c.a.Ea;
import com.google.android.gms.internal.measurement.zzh;
import com.google.android.gms.measurement.internal.zzfv;
import com.google.common.base.Preconditions;
import com.google.protobuf.BinaryWriter;
import com.hisense.avmiddleware.HisAudioApi;
import com.hisense.avmiddleware.HisLogger;
import com.hisense.avmiddleware.HisVideoApi;
import com.mediatek.twoworlds.tv.TvDebugLog;
import java.util.List;
import java.util.Queue;

/* JADX INFO: compiled from: outline */
/* JADX INFO: loaded from: classes.dex */
public class a {
    public static int a(int i, int i2, int i3, int i4) {
        return ((i * i2) / i3) + i4;
    }

    public static int a(Object obj, int i) {
        return String.valueOf(obj).length() + i;
    }

    public static int a(StringBuilder sb, int i, String str, String str2, int i2, int i3) {
        sb.append(i);
        sb.append(str);
        Log.d(str2, sb.toString());
        return i2 + i3;
    }

    public static Object a(Enum r0, int i, List list, int i2) {
        zzh.zzh(r0.name(), i, list);
        return list.get(i2);
    }

    public static String a(int i, String str, int i2) {
        StringBuilder sb = new StringBuilder(i);
        sb.append(str);
        sb.append(i2);
        return sb.toString();
    }

    public static String a(int i, String str, String str2) {
        StringBuilder sb = new StringBuilder(i);
        sb.append(str);
        sb.append(str2);
        return sb.toString();
    }

    public static String a(int i, String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(i);
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
        return sb.toString();
    }

    public static String a(String str, int i) {
        return str + i;
    }

    public static String a(String str, int i, String str2, int i2, String str3) {
        return str + i + str2 + i2 + str3;
    }

    public static String a(String str, long j) {
        return str + j;
    }

    public static String a(String str, Object obj) {
        return str + obj;
    }

    public static String a(String str, Object obj, String str2) {
        return str + obj + str2;
    }

    public static String a(String str, String str2) {
        return str + str2;
    }

    public static String a(String str, String str2, String str3) {
        return str + str2 + str3;
    }

    public static String a(StringBuilder sb, int i, String str) {
        sb.append(i);
        sb.append(str);
        return sb.toString();
    }

    public static String a(StringBuilder sb, int i, String str, int i2, String str2) {
        sb.append(i);
        sb.append(str);
        sb.append(i2);
        sb.append(str2);
        return sb.toString();
    }

    public static String a(StringBuilder sb, Object obj, char c2) {
        sb.append(obj);
        sb.append(c2);
        return sb.toString();
    }

    public static String a(StringBuilder sb, Object obj, String str) {
        sb.append(obj);
        sb.append(str);
        return sb.toString();
    }

    public static String a(StringBuilder sb, String str, String str2) {
        sb.append(str);
        sb.append(str2);
        return sb.toString();
    }

    public static String a(StringBuilder sb, String str, String str2, String str3) {
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
        return sb.toString();
    }

    public static StringBuilder a(int i, String str, String str2, String str3, String str4) {
        StringBuilder sb = new StringBuilder(i);
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
        sb.append(str4);
        return sb;
    }

    public static StringBuilder a(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        return sb;
    }

    public static StringBuilder a(String str, int i, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(i);
        sb.append(str2);
        return sb;
    }

    public static void a(zzfv zzfvVar, String str) {
        zzfvVar.zzay().zzd().zza(str);
    }

    public static void a(zzfv zzfvVar, String str, Object obj) {
        zzfvVar.zzay().zzd().zzb(str, obj);
    }

    public static void a(BinaryWriter binaryWriter, int i, int i2, int i3) {
        binaryWriter.writeVarint32(binaryWriter.getTotalBytesWritten() - i);
        binaryWriter.writeTag(i2, i3);
    }

    public static void a(Object obj, Object obj2, Queue queue, Object obj3, Ea ea) {
        Preconditions.checkNotNull(obj, obj2);
        queue.add(obj3);
        ea.a();
    }

    public static void a(String str, int i, String str2, String str3) {
        Log.d(str3, str + i + str2);
    }

    public static void a(String str, String str2, String str3, String str4) {
        Log.d(str4, str + str2 + str3);
    }

    public static void a(String str, boolean z, String str2) {
        Log.d(str2, str + z);
    }

    public static void a(StringBuilder sb, int i, String str, String str2) {
        sb.append(i);
        sb.append(str);
        Log.d(str2, sb.toString());
    }

    public static void a(StringBuilder sb, String str, char c2, String str2) {
        sb.append(str);
        sb.append(c2);
        sb.append(str2);
    }

    public static void a(StringBuilder sb, String str, String str2, String str3, String str4) {
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
        sb.append(str4);
    }

    public static String b(int i, String str, String str2, String str3, String str4) {
        StringBuilder sb = new StringBuilder(i);
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
        sb.append(str4);
        return sb.toString();
    }

    public static StringBuilder b(String str, int i, String str2, int i2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(i);
        sb.append(str2);
        sb.append(i2);
        sb.append(str3);
        return sb;
    }

    public static StringBuilder b(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str2);
        return sb;
    }

    public static StringBuilder b(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
        return sb;
    }

    public static void b(zzfv zzfvVar, String str) {
        zzfvVar.zzay().zzj().zza(str);
    }

    public static void b(String str, int i, String str2) {
        Log.d(str2, str + i);
    }

    public static void b(String str, int i, String str2, String str3) {
        Log.e(str3, str + i + str2);
    }

    public static void b(String str, Object obj, String str2) {
        Log.d(str2, str + obj);
    }

    public static void b(StringBuilder sb, int i, String str) {
        sb.append(i);
        Log.d(str, sb.toString());
    }

    public static void c(zzfv zzfvVar, String str) {
        zzfvVar.zzay().zzk().zza(str);
    }

    public static void c(String str, int i, String str2) {
        HisLogger.d(str2, str + i);
    }

    public static void c(String str, int i, String str2, String str3) {
        Log.v(str3, str + i + str2);
    }

    public static void c(String str, Object obj, String str2) {
        Log.e(str2, str + obj);
    }

    public static void c(String str, String str2, String str3) {
        Log.d(str3, str + str2);
    }

    public static void c(StringBuilder sb, int i, String str) {
        sb.append(i);
        HisLogger.d(str, sb.toString());
    }

    public static void d(String str, int i, String str2) {
        TvDebugLog.d(str2, str + i);
    }

    public static void d(String str, Object obj, String str2) {
        Log.v(str2, str + obj);
    }

    public static boolean e(String str, int i, String str2) {
        HisLogger.d(str2, str + i);
        return HisAudioApi.getHidlService();
    }

    public static boolean f(String str, int i, String str2) {
        HisLogger.d(str2, str + i);
        return HisVideoApi.getHidlService();
    }

    public static String g(String str, int i, String str2) {
        return str + i + str2;
    }
}
