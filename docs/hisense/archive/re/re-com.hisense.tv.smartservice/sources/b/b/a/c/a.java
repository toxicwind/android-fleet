package b.b.a.c;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemProperties;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.work.Constraints;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.impl.WorkManagerImpl;
import b.b.a.g;
import c.a.C0223ga;
import c.a.C0236u;
import c.a.b.C0181t;
import c.a.b.Tb;
import c.a.b.Wc;
import c.a.e.l;
import c.a.e.o;
import c.a.e.q;
import c.a.e.r;
import c.a.qa;
import c.a.wa;
import c.a.xa;
import c.a.za;
import com.google.common.base.Preconditions;
import com.google.common.net.InetAddresses;
import com.google.firebase.installations.local.IidStore;
import com.hisense.tv.smartsense.module.firebase.analytics.UserPropertiesWorker;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Context f25a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static SharedPreferences f26b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static SharedPreferences.Editor f27c;

    public static String a(int i) {
        if (i == 1) {
            return "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════";
        }
        if (i == 2) {
            return "╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════";
        }
        if (i != 3) {
            return i != 4 ? "" : "╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────";
        }
        return "║ ";
    }

    public static void a(Context context) {
        WorkManagerImpl.getInstance(context.getApplicationContext()).enqueueUniqueWork("UserPropertiesWorker", ExistingWorkPolicy.REPLACE, new OneTimeWorkRequest.Builder(UserPropertiesWorker.class).setConstraints(new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()).build());
    }

    public static void b(@NonNull String str, long j) {
        f27c.putLong(str, j);
        f27c.apply();
    }

    public static Optional<Integer> c() {
        Integer numValueOf;
        try {
            numValueOf = Integer.valueOf(SystemProperties.get("ro.vendor.mtk.system.timesync.existed"));
        } catch (NumberFormatException unused) {
            numValueOf = null;
        }
        return Optional.ofNullable(numValueOf);
    }

    /* JADX WARN: Code duplicated, block: B:23:0x004e  */
    public static Optional<Boolean> b() {
        byte b2;
        Boolean bool;
        String lowerCase = SystemProperties.get("vendor.mtk.inside").toLowerCase(Locale.US);
        int iHashCode = lowerCase.hashCode();
        if (iHashCode != 48) {
            if (iHashCode != 49) {
                if (iHashCode != 3569038) {
                    if (iHashCode == 97196323 && lowerCase.equals("false")) {
                        b2 = 3;
                    } else {
                        b2 = -1;
                    }
                } else if (lowerCase.equals("true")) {
                    b2 = 1;
                } else {
                    b2 = -1;
                }
            } else if (lowerCase.equals("1")) {
                b2 = 0;
            } else {
                b2 = -1;
            }
        } else if (lowerCase.equals("0")) {
            b2 = 2;
        } else {
            b2 = -1;
        }
        if (b2 != 0 && b2 != 1) {
            bool = (b2 == 2 || b2 == 3) ? Boolean.FALSE : null;
        } else {
            bool = Boolean.TRUE;
        }
        return Optional.ofNullable(bool);
    }

    public static String a(Object obj) {
        return a(obj, 0);
    }

    public static void a(String str) {
        if (str == null) {
            Log.e("ChmodZipPackageUtils", "filePath is null");
            return;
        }
        PosixFileAttributeView posixFileAttributeView = (PosixFileAttributeView) Files.getFileAttributeView(Paths.get(str, new String[0]), PosixFileAttributeView.class, new LinkOption[0]);
        if (posixFileAttributeView == null) {
            Log.e("ChmodZipPackageUtils", "ChmodFile failed, posixView is null");
            return;
        }
        Log.i("ChmodZipPackageUtils", "old permissions");
        a(posixFileAttributeView);
        try {
            posixFileAttributeView.setPermissions(EnumSet.of(PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_WRITE, PosixFilePermission.OWNER_EXECUTE, PosixFilePermission.GROUP_READ, PosixFilePermission.GROUP_WRITE, PosixFilePermission.OTHERS_READ, PosixFilePermission.OTHERS_WRITE));
            System.out.println("Permissions set successfully.");
        } catch (IOException e2) {
            Log.e("ChmodZipPackageUtils", e2.toString());
        }
        Log.i("ChmodZipPackageUtils", "new permissions");
        a(posixFileAttributeView);
    }

    public static boolean a(Class cls) {
        return cls != null && cls.isMemberClass() && (cls.getModifiers() & 8) == 8;
    }

    public static void a(StringBuilder sb, Object obj) {
        char cCharAt;
        if (obj.getClass().isArray()) {
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < obj.toString().length() && obj.toString().charAt(i3) == '['; i3++) {
                i2++;
            }
            if (i2 == 1) {
                if (obj.getClass().isArray()) {
                    String string = obj.toString();
                    cCharAt = string.substring(string.lastIndexOf("[") + 1, string.lastIndexOf("[") + 2).charAt(0);
                } else {
                    cCharAt = 0;
                }
                if (cCharAt == 'B') {
                    sb.append(Arrays.toString((byte[]) obj));
                    return;
                }
                if (cCharAt == 'D') {
                    sb.append(Arrays.toString((double[]) obj));
                    return;
                }
                if (cCharAt == 'F') {
                    sb.append(Arrays.toString((float[]) obj));
                    return;
                }
                if (cCharAt == 'L') {
                    Object[] objArr = (Object[]) obj;
                    sb.append("[");
                    while (i < objArr.length) {
                        sb.append(a(objArr[i]));
                        if (i != objArr.length - 1) {
                            sb.append(",");
                        }
                        i++;
                    }
                    sb.append("]");
                    return;
                }
                if (cCharAt == 'S') {
                    sb.append(Arrays.toString((short[]) obj));
                    return;
                }
                if (cCharAt == 'Z') {
                    sb.append(Arrays.toString((boolean[]) obj));
                    return;
                }
                if (cCharAt == 'I') {
                    sb.append(Arrays.toString((int[]) obj));
                    return;
                } else if (cCharAt != 'J') {
                    sb.append(Arrays.toString((Object[]) obj));
                    return;
                } else {
                    sb.append(Arrays.toString((long[]) obj));
                    return;
                }
            }
            sb.append("[");
            while (true) {
                Object[] objArr2 = (Object[]) obj;
                if (i < objArr2.length) {
                    a(sb, objArr2[i]);
                    if (i != objArr2.length - 1) {
                        sb.append(",");
                    }
                    i++;
                } else {
                    sb.append("]");
                    return;
                }
            }
        } else {
            sb.append("not a array!!");
        }
    }

    public static String a(Object obj, int i) {
        if (obj == null) {
            return "Object[object is null]";
        }
        if (i > 2) {
            return obj.toString();
        }
        if (b.b.a.a.a() != null && b.b.a.a.a().size() > 0) {
            for (g gVar : b.b.a.a.a()) {
                if (gVar.a().isAssignableFrom(obj.getClass())) {
                    return gVar.a(obj);
                }
            }
        }
        if (obj.getClass().isArray()) {
            StringBuilder sb = new StringBuilder();
            a(sb, obj);
            return sb.toString();
        }
        if (obj.toString().startsWith(obj.getClass().getName() + "@")) {
            StringBuilder sb2 = new StringBuilder();
            a(obj.getClass(), sb2, obj, false, i);
            Class<? super Object> superclass = obj.getClass().getSuperclass();
            if (superclass != null) {
                while (!superclass.equals(Object.class)) {
                    a(superclass, sb2, obj, true, i);
                    superclass = superclass.getSuperclass();
                }
            } else {
                sb2.append(obj.toString());
            }
            return sb2.toString();
        }
        return obj.toString();
    }

    public static <ReqT, RespT> qa<ReqT, RespT> a(o<ReqT, RespT> oVar) {
        return new q(oVar, false);
    }

    public static void a(PosixFileAttributeView posixFileAttributeView) {
        try {
            Log.i("ChmodZipPackageUtils", " permissions = " + PosixFilePermissions.toString(posixFileAttributeView.readAttributes().permissions()));
        } catch (IOException e2) {
            Log.e("ChmodZipPackageUtils", e2.toString());
        }
    }

    public static long a(String str, long j) {
        return f26b.getLong(str, j);
    }

    public static <ReqT, RespT> qa<ReqT, RespT> a(l<ReqT, RespT> lVar) {
        return new q(lVar, true);
    }

    public static String a(String str, int i, int i2) {
        String strA = a(str, i);
        if (strA == null) {
            return null;
        }
        if (i2 == 0 || i2 > strA.length()) {
            return strA;
        }
        int i3 = 0;
        if (i2 < 0) {
            int i4 = -i2;
            StringBuilder sb = new StringBuilder();
            int length = strA.length() - 1;
            while (length > 0) {
                int iLastIndexOf = strA.lastIndexOf(46, length);
                if (iLastIndexOf == -1) {
                    if (sb.length() > 0 && sb.length() + length + 1 > i4) {
                        sb.insert(0, '*');
                        break;
                    }
                    sb.insert(0, strA.substring(0, length + 1));
                    length = iLastIndexOf - 1;
                } else {
                    if (sb.length() > 0) {
                        if (((length + 1) - iLastIndexOf) + sb.length() + 1 > i4) {
                            sb.insert(0, '*');
                            break;
                        }
                    }
                    sb.insert(0, strA.substring(iLastIndexOf, length + 1));
                    length = iLastIndexOf - 1;
                }
            }
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        while (i3 < strA.length()) {
            int iIndexOf = strA.indexOf(46, i3);
            if (iIndexOf == -1) {
                if (sb2.length() > 0) {
                    sb2.insert(sb2.length(), '*');
                    break;
                }
                sb2.insert(sb2.length(), strA.substring(i3, strA.length()));
                break;
            }
            if (sb2.length() > 0 && iIndexOf + 1 > i2) {
                sb2.insert(sb2.length(), '*');
                break;
            }
            int i5 = iIndexOf + 1;
            sb2.insert(sb2.length(), strA.substring(i3, i5));
            i3 = i5;
        }
        return sb2.toString();
    }

    public static void a(Class cls, StringBuilder sb, Object obj, boolean z, int i) {
        String str;
        if (cls.equals(Object.class)) {
            return;
        }
        if (z) {
            sb.append(b.b.a.a.f4a + b.b.a.a.f4a + "=> ");
        }
        sb.append(cls.getSimpleName() + " {");
        Field[] declaredFields = cls.getDeclaredFields();
        for (int i2 = 0; i2 < declaredFields.length; i2++) {
            Field field = declaredFields[i2];
            field.setAccessible(true);
            if (!cls.isMemberClass() || a(cls) || i2 != 0) {
                try {
                    Object objA = field.get(obj);
                    if (objA != null) {
                        if (a(cls) || (!field.getName().equals("$change") && !field.getName().equalsIgnoreCase("this$0"))) {
                            if (objA instanceof String) {
                                objA = b.a.a.a.a.a("\"", objA, "\"");
                            } else if (objA instanceof Character) {
                                objA = b.a.a.a.a.a("'", objA, "'");
                            }
                            if (i < 2) {
                                objA = a(objA, i + 1);
                            }
                        }
                    }
                    String strA = b.a.a.a.a.a("", "%s = %s, ");
                    Object[] objArr = new Object[2];
                    objArr[0] = field.getName();
                    objArr[1] = objA == null ? "null" : objA.toString();
                    str = String.format(strA, objArr);
                } catch (IllegalAccessException e2) {
                    Object objA2 = e2;
                    if (a(cls) || (!field.getName().equals("$change") && !field.getName().equalsIgnoreCase("this$0"))) {
                        if (objA2 instanceof String) {
                            objA2 = b.a.a.a.a.a("\"", objA2, "\"");
                        } else if (objA2 instanceof Character) {
                            objA2 = b.a.a.a.a.a("'", objA2, "'");
                        }
                        if (i < 2) {
                            objA2 = a(objA2, i + 1);
                        }
                        String strA2 = b.a.a.a.a.a("", "%s = %s, ");
                        Object[] objArr2 = new Object[2];
                        objArr2[0] = field.getName();
                        objArr2[1] = objA2 == null ? "null" : objA2.toString();
                        str = String.format(strA2, objArr2);
                        sb.append(str);
                    }
                } catch (Throwable th) {
                    sb.append(String.format(b.a.a.a.a.a("", "%s = %s, "), field.getName(), "null"));
                    throw th;
                }
                sb.append(str);
            }
        }
        if (sb.toString().endsWith(IidStore.JSON_ENCODED_PREFIX)) {
            sb.append("}");
            return;
        }
        sb.replace(sb.length() - 2, sb.length() - 1, "}");
    }

    public static boolean a(ClassLoader classLoader) {
        try {
            Class.forName("android.app.Application", false, classLoader);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v1, types: [java.lang.Iterable, java.util.ServiceLoader] */
    /* JADX WARN: Type inference failed for: r7v2, types: [java.util.ServiceLoader] */
    /* JADX WARN: Type inference failed for: r7v3, types: [java.lang.Iterable] */
    /* JADX WARN: Type inference failed for: r7v6, types: [java.util.ArrayList, java.util.List] */
    /* JADX WARN: Type inference failed for: r8v0, types: [c.a.xa, c.a.xa<T>] */
    public static <T> List<T> a(Class<T> cls, Iterable<Class<?>> iterable, ClassLoader classLoader, xa<T> xaVar) {
        boolean z;
        ?? Load;
        try {
            Class.forName("android.app.Application", false, classLoader);
            z = true;
        } catch (Exception unused) {
            z = false;
        }
        if (z) {
            Load = new ArrayList();
            for (Class<?> cls2 : iterable) {
                try {
                    Load.add(cls2.asSubclass(cls).getConstructor(new Class[0]).newInstance(new Object[0]));
                } catch (Throwable th) {
                    throw new ServiceConfigurationError(String.format("Provider %s could not be instantiated %s", cls2.getName(), th), th);
                }
            }
        } else {
            Load = ServiceLoader.load(cls, classLoader);
            if (!Load.iterator().hasNext()) {
                Load = ServiceLoader.load(cls);
            }
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : Load) {
            if (xaVar.a(obj)) {
                arrayList.add(obj);
            }
        }
        Collections.sort(arrayList, Collections.reverseOrder(new wa(xaVar)));
        return Collections.unmodifiableList(arrayList);
    }

    public static Tb a() {
        if (Wc.f1183d == null) {
            return new Wc();
        }
        return new C0181t();
    }

    public static za a(C0236u c0236u) {
        Preconditions.checkNotNull(c0236u, "context must not be null");
        if (!c0236u.e()) {
            return null;
        }
        Throwable thB = c0236u.b();
        if (thB == null) {
            return za.f1937d.b("io.grpc.Context was cancelled without error");
        }
        if (thB instanceof TimeoutException) {
            return za.f1939f.b(thB.getMessage()).b(thB);
        }
        za zaVarA = za.a(thB);
        if (za.a.UNKNOWN.equals(zaVarA.p) && zaVarA.r == thB) {
            return za.f1937d.b("Context cancelled").b(thB);
        }
        return zaVarA.b(thB);
    }

    public static String a(String str, int i) {
        if (str == null) {
            return null;
        }
        if (i == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        int i2 = 1;
        if (i > 0) {
            int i3 = 0;
            while (i3 < str.length()) {
                int iIndexOf = str.indexOf(46, i3);
                if (iIndexOf == -1) {
                    sb.insert(sb.length(), str.substring(i3, str.length()));
                    break;
                }
                if (i2 == i) {
                    sb.insert(sb.length(), str.substring(i3, iIndexOf));
                    break;
                }
                int i4 = iIndexOf + 1;
                sb.insert(sb.length(), str.substring(i3, i4));
                i2++;
                i3 = i4;
            }
        } else if (i < 0) {
            String strA = a(str, -i);
            if (str.equals(strA)) {
                sb.insert(sb.length(), str.substring(str.lastIndexOf(46) + 1, str.length()));
            } else {
                return str.replaceFirst(strA + InetAddresses.IPV4_DELIMITER, "");
            }
        }
        return sb.toString();
    }

    public static void a(C0223ga<?, ?> c0223ga, r<?> rVar) {
        Preconditions.checkNotNull(c0223ga, "methodDescriptor");
        Preconditions.checkNotNull(rVar, "responseObserver");
        rVar.onError(za.j.b(String.format("Method %s is unimplemented", c0223ga.f1833b)).b());
    }
}
