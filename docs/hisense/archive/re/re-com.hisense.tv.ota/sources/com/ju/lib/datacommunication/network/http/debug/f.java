package com.ju.lib.datacommunication.network.http.debug;

import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.os.SystemProperties;
import android.text.TextUtils;
import com.ju.lib.datacommunication.network.http.core.HiRequest$Method;
import com.ju.lib.datacommunication.network.http.core.n;
import com.ju.lib.datacommunication.network.http.error.NetworkErrorEnum;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.c0;
import okhttp3.e0;
import okio.j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class f {
    private static String e = "DebugHelper";
    private static Boolean f;
    private static volatile f g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private volatile b f108a;
    private String b;
    private Handler c;
    private Map d = new ConcurrentHashMap();

    private f() {
        HandlerThread handlerThread = new HandlerThread("NetworkDebug", 10);
        handlerThread.start();
        this.c = new Handler(handlerThread.getLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(DebugCheckType debugCheckType, i iVar) throws Throwable {
        String strA = this.f108a.a(debugCheckType, iVar);
        com.ju.lib.datacommunication.network.http.i.b.d(e, " checkAndSaveDebugTrace: ", debugCheckType, ", preInfo: " + strA, ", url:", iVar.c);
        if (strA == null) {
            return;
        }
        if (!this.d.containsKey(debugCheckType)) {
            File file = new File(this.b + debugCheckType.filePath);
            if (!file.exists()) {
                try {
                    if (!file.createNewFile()) {
                        com.ju.lib.datacommunication.network.http.i.b.c(e, "cannot create file: " + this.b + debugCheckType.filePath);
                        return;
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            this.d.put(debugCheckType, new h(debugCheckType, m(file)));
        }
        String strA2 = ((h) this.d.get(debugCheckType)).a(iVar);
        if (strA2 != null) {
            n(debugCheckType, strA + strA2 + "\n----------------------------------------------------------------------------------------------------------------------------------------------------------------\n\n");
        }
    }

    private List e(JSONArray jSONArray) {
        Object objTrim;
        if (jSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                if (jSONArray.get(i) instanceof String) {
                    if (!"".equals(((String) jSONArray.get(i)).trim())) {
                        objTrim = ((String) jSONArray.get(i)).trim();
                    }
                } else {
                    objTrim = jSONArray.get(i);
                }
                arrayList.add(objTrim);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return arrayList;
    }

    private Map f(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        HashMap map = new HashMap();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String strTrim = itKeys.next().trim();
            map.put(strTrim, jSONObject.optString(strTrim).trim());
        }
        return map;
    }

    public static f g() {
        if (g == null) {
            synchronized (f.class) {
                if (g == null) {
                    g = new f();
                }
            }
        }
        return g;
    }

    private String h() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace == null) {
            return "no stack...";
        }
        int iMin = Math.min(stackTrace.length, 25);
        com.ju.lib.datacommunication.network.http.i.b.d(e, "stackTrace.length: " + stackTrace.length);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 6; i < iMin; i++) {
            StackTraceElement stackTraceElement = stackTrace[i];
            if (stringBuffer.length() > 0) {
                stringBuffer.append(" <- ");
                stringBuffer.append(System.getProperty("line.separator"));
            }
            stringBuffer.append(MessageFormat.format("    {0}.{1}() {2}", stackTraceElement.getClassName(), stackTraceElement.getMethodName(), Integer.valueOf(stackTraceElement.getLineNumber())));
        }
        return stringBuffer.toString();
    }

    public static boolean k() {
        if (f == null) {
            f = Boolean.valueOf("true".equals(SystemProperties.get("persist.his.ju_http_trace", "")));
        }
        return f.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l(File file) {
        try {
            JSONObject jSONObject = new JSONObject(m(file));
            this.f108a.b = f(jSONObject.getJSONObject("respRules"));
            this.f108a.f104a = e(jSONObject.getJSONArray("paramRules"));
            this.f108a.d = e(jSONObject.getJSONArray("urlRules"));
            this.f108a.c = e(jSONObject.getJSONArray("statusCodeRules"));
            com.ju.lib.datacommunication.network.http.i.b.d(e, "debugConfig: ", this.f108a);
        } catch (JSONException e2) {
            com.ju.lib.datacommunication.network.http.i.b.c(e, " debugConfig parse error!!! pls check file: ", this.b, "httpTraceRule.json");
            e2.printStackTrace();
            f = Boolean.FALSE;
        }
    }

    private String m(File file) throws Throwable {
        BufferedReader bufferedReader;
        Throwable th;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            try {
                try {
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        sb.append(line);
                        th = th;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                    String string = sb.toString();
                    try {
                        bufferedReader.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                    return string;
                } catch (Exception e4) {
                    e = e4;
                    e.printStackTrace();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    return null;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e6) {
            e = e6;
            bufferedReader = null;
        } catch (Throwable th3) {
            bufferedReader = null;
            th = th3;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v12, types: [java.io.OutputStreamWriter] */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15, types: [java.io.OutputStreamWriter] */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.io.OutputStreamWriter] */
    /* JADX WARN: Type inference failed for: r4v8 */
    private void n(DebugCheckType debugCheckType, String str) throws Throwable {
        Exception e2;
        ?? outputStreamWriter = this.b + debugCheckType.filePath;
        com.ju.lib.datacommunication.network.http.i.b.d(e, " writeFile path: " + outputStreamWriter, "append: ", str);
        try {
            try {
                try {
                    outputStreamWriter = new OutputStreamWriter(new FileOutputStream(new File((String) outputStreamWriter), true), "UTF-8");
                    try {
                        outputStreamWriter.write(str);
                        outputStreamWriter.flush();
                        outputStreamWriter.close();
                        outputStreamWriter = outputStreamWriter;
                    } catch (Exception e3) {
                        e2 = e3;
                        e2.printStackTrace();
                        if (outputStreamWriter != 0) {
                            outputStreamWriter.close();
                            outputStreamWriter = outputStreamWriter;
                        }
                    }
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            } catch (Exception e5) {
                e2 = e5;
                outputStreamWriter = 0;
            } catch (Throwable th) {
                th = th;
                outputStreamWriter = 0;
                if (outputStreamWriter != 0) {
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
    }

    public i c(n nVar, Object obj) {
        i iVar = new i();
        com.ju.lib.datacommunication.network.http.error.b bVar = new com.ju.lib.datacommunication.network.http.error.b(NetworkErrorEnum.UnknownError, nVar.i());
        iVar.c = bVar.h();
        if (nVar.e() == HiRequest$Method.POST) {
            bVar.f(com.ju.lib.datacommunication.network.http.error.c.a().f(nVar));
        }
        iVar.e = bVar.c();
        iVar.f111a = h();
        if (obj != null) {
            iVar.b = obj.getClass().getName();
        }
        this.c.post(new d(this, iVar));
        return iVar;
    }

    public void i(c0 c0Var, i iVar) {
        if (iVar != null) {
            iVar.d = c0Var.p();
            e0 e0VarA = c0Var.a();
            if (e0VarA != null && e0VarA.p() > 0 && e0VarA.p() < 1000) {
                Charset charsetA = e0VarA.B() != null ? e0VarA.B().a() : Charset.forName("UTF-8");
                j jVarP = e0VarA.P();
                jVarP.z(Long.MAX_VALUE);
                iVar.f = jVarP.c().clone().C(charsetA);
            }
            this.c.post(new e(this, iVar));
        }
    }

    public synchronized void j(String str) {
        Boolean bool = Boolean.FALSE;
        synchronized (this) {
            com.ju.lib.datacommunication.network.http.i.b.d(e, "init pkgName: " + str);
            if (k() && this.f108a == null) {
                this.b = Environment.getExternalStorageDirectory().getAbsolutePath() + "/network/";
                com.ju.lib.datacommunication.network.http.i.b.d(e, " debug mode start, init exDirPath: " + this.b);
                File file = new File(this.b + "httpTraceRule.json");
                if (file.exists() && file.canRead()) {
                    if (TextUtils.isEmpty(str)) {
                        str = Process.myPid() + "";
                        com.ju.lib.datacommunication.network.http.i.b.c(e, "ohg, this process has not set pkgName: " + str);
                    }
                    this.b += str + "/";
                    File file2 = new File(this.b);
                    if (!file2.exists() && !file2.mkdir()) {
                        com.ju.lib.datacommunication.network.http.i.b.c(e, " mkdir failed, pls delete the dir and retry: " + this.b);
                        f = bool;
                        return;
                    }
                    this.f108a = new b();
                    this.c.post(new c(this, file));
                }
                com.ju.lib.datacommunication.network.http.i.b.c(e, "file" + this.b + "httpTraceRule.json not exists, pls make sure the file existsand has read permissions for every process!!!");
                f = bool;
            }
        }
    }
}
