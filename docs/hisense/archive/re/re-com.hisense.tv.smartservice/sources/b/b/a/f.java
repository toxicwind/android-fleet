package b.b.a;

import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.ju.lib.utils.log.LogUtil;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.MissingFormatArgumentException;
import java.util.regex.Matcher;

/* JADX INFO: loaded from: classes.dex */
public class f implements h {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ThreadLocal<String> f38c = new ThreadLocal<>();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d f36a = d.a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c f37b = c.c();

    public f() {
        this.f36a.a(a.f5b);
    }

    public final synchronized void a(int i, String str, Object... objArr) {
        a(i, str, false, objArr);
    }

    public final String b() {
        d dVar = this.f36a;
        StackTraceElement stackTraceElementA = a();
        String strA = null;
        b.b.a.b.b eVar = null;
        if (!TextUtils.isEmpty(dVar.g)) {
            String str = dVar.g;
            if (str != null) {
                try {
                    eVar = new b.b.a.b.b.C0003b().a(str);
                } catch (Exception unused) {
                    eVar = new b.b.a.b.b.e(0, 0, str);
                }
            }
            strA = eVar.a(stackTraceElementA);
        }
        if (strA != null) {
            return strA;
        }
        StackTraceElement stackTraceElementA2 = a();
        String string = stackTraceElementA2.toString();
        String strSubstring = string.substring(string.lastIndexOf(40), string.length());
        String className = stackTraceElementA2.getClassName();
        return String.format("%s.%s%s", className.substring(className.lastIndexOf(".") + 1), stackTraceElementA2.getMethodName(), strSubstring);
    }

    public void a(Object obj) {
        a(2, b.b.a.c.a.a(obj), new Object[0]);
    }

    public final void a(int i, String str, boolean z, Object... objArr) {
        String parent;
        String str2 = this.f38c.get();
        if (!TextUtils.isEmpty(str2)) {
            this.f38c.remove();
        } else {
            d dVar = this.f36a;
            str2 = TextUtils.isEmpty(dVar.f30c) ? "LogUtils" : dVar.f30c;
        }
        int i2 = 0;
        if (!z) {
            if (objArr.length > 0) {
                try {
                    str = String.format(str, objArr);
                } catch (MissingFormatArgumentException unused) {
                }
            }
            c cVar = this.f37b;
            if (cVar.f22c) {
                cVar.b();
                c cVar2 = this.f37b;
                if (i >= cVar2.f21b) {
                    String strReplace = null;
                    if (TextUtils.isEmpty(cVar2.f24e)) {
                        if ("mounted".equals(Environment.getExternalStorageState())) {
                            parent = b.a.a.a.a.a(b.a.a.a.a.b(Environment.getExternalStorageDirectory() + File.separator, "LogUtils"), File.separator, "logs");
                        } else {
                            throw new IllegalStateException("Sdcard No Access, please config Log2FilePath");
                        }
                    } else {
                        File file = new File(cVar2.f24e);
                        if (file.exists() && file.isDirectory()) {
                            parent = cVar2.f24e;
                        } else if (file.isFile() && file.getParentFile() != null && (file.getParentFile().exists() || file.getParentFile().mkdirs())) {
                            parent = file.getParent();
                        } else {
                            parent = file.mkdirs() ? cVar2.f24e : null;
                        }
                    }
                    if (TextUtils.isEmpty(parent)) {
                        int i3 = Build.VERSION.SDK_INT;
                        Log.e(str2, "LogUtils write to logFile error. No sdcard access permission?");
                    } else {
                        String str3 = this.f37b.f23d;
                        Date date = new Date();
                        if (str3 != null) {
                            Matcher matcher = b.b.a.b.b.C0003b.f16f.matcher(str3);
                            strReplace = str3;
                            while (matcher.find()) {
                                strReplace = strReplace.replace(matcher.group(0), new SimpleDateFormat(matcher.group(2)).format(date));
                            }
                        }
                        new File(parent, strReplace);
                        System.currentTimeMillis();
                        Thread.currentThread().getName();
                        this.f37b.a();
                        throw new NullPointerException("LogFileEngine must not Null");
                    }
                }
            }
        }
        d dVar2 = this.f36a;
        if (dVar2.f29b && i >= dVar2.f32e) {
            if (str.length() > 3072) {
                if (this.f36a.f31d) {
                    a(i, str2, b.b.a.c.a.a(1));
                    a(i, str2, b.b.a.c.a.a(3) + b());
                    a(i, str2, b.b.a.c.a.a(4));
                }
                ArrayList arrayList = new ArrayList();
                int length = str.length() / LogUtil.LOGCAT_MESSAGE_MAX_LENTH;
                if (length > 0) {
                    int i4 = 0;
                    while (i2 < length) {
                        int i5 = i4 + LogUtil.LOGCAT_MESSAGE_MAX_LENTH;
                        arrayList.add(str.substring(i4, i5));
                        i2++;
                        i4 = i5;
                    }
                    arrayList.add(str.substring(i4, str.length()));
                } else {
                    arrayList.add(str);
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    a(i, (String) it.next(), true, objArr);
                }
                if (this.f36a.f31d) {
                    a(i, str2, b.b.a.c.a.a(2));
                    return;
                }
                return;
            }
            if (!this.f36a.f31d) {
                a(i, str2, str);
                return;
            }
            if (z) {
                String[] strArrSplit = str.split(a.f4a);
                int length2 = strArrSplit.length;
                while (i2 < length2) {
                    a(i, str2, b.b.a.c.a.a(3) + strArrSplit[i2]);
                    i2++;
                }
                return;
            }
            a(i, str2, b.b.a.c.a.a(1));
            a(i, str2, b.b.a.c.a.a(3) + b());
            a(i, str2, b.b.a.c.a.a(4));
            String[] strArrSplit2 = str.split(a.f4a);
            int length3 = strArrSplit2.length;
            while (i2 < length3) {
                a(i, str2, b.b.a.c.a.a(3) + strArrSplit2[i2]);
                i2++;
            }
            a(i, str2, b.b.a.c.a.a(2));
        }
    }

    public final StackTraceElement a() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int iA = a(stackTrace, e.class);
        if (iA == -1 && (iA = a(stackTrace, f.class)) == -1) {
            return null;
        }
        int i = this.f36a.h;
        if (i > 0) {
            iA += i;
        }
        return stackTrace[iA];
    }

    public final int a(StackTraceElement[] stackTraceElementArr, Class cls) {
        for (int i = 5; i < stackTraceElementArr.length; i++) {
            String className = stackTraceElementArr[i].getClassName();
            if (!(cls.equals(f.class) && i < stackTraceElementArr.length - 1 && stackTraceElementArr[i + 1].getClassName().equals(f.class.getName())) && className.equals(cls.getName())) {
                return i + 1;
            }
        }
        return -1;
    }

    public final void a(int i, String str, String str2) {
        if (!this.f36a.f31d) {
            str2 = b() + ": " + str2;
        }
        switch (i) {
            case 1:
                Log.v(str, str2);
                break;
            case 2:
                Log.d(str, str2);
                break;
            case 3:
                Log.i(str, str2);
                break;
            case 4:
                Log.w(str, str2);
                break;
            case 5:
                Log.e(str, str2);
                break;
            case 6:
                Log.wtf(str, str2);
                break;
        }
    }
}
