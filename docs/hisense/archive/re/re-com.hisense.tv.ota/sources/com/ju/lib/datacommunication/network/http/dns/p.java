package com.ju.lib.datacommunication.network.http.dns;

import android.os.SystemClock;
import com.ju.lib.datacommunication.network.http.core.u;
import com.ju.lib.datacommunication.network.http.core.v;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static n f121a;
    private static n b;

    public static boolean a(String str) {
        return Pattern.compile("^((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])\\.){3}(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])$").matcher(str).matches();
    }

    private static String b(n nVar) throws JSONException {
        if (nVar == null) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("timeout", String.valueOf(nVar.b));
        jSONObject.put("cooltime", nVar.f120a);
        return jSONObject.toString();
    }

    private static n c(JSONObject jSONObject) {
        Iterator<String> itKeys = jSONObject.keys();
        int iB = -1;
        double dA = -1.0d;
        while (itKeys != null && itKeys.hasNext()) {
            String next = itKeys.next();
            if ("timeout".equalsIgnoreCase(next)) {
                dA = k.a(jSONObject.optDouble("timeout", 1.2d));
            } else if ("cooltime".equalsIgnoreCase(next)) {
                iB = k.b(jSONObject.optInt("cooltime", 180));
            }
        }
        if (iB < 0 && dA < 0.0d) {
            com.ju.lib.datacommunication.network.http.i.b.d(j.n, "ext is invalid");
            return null;
        }
        n nVar = new n(Integer.valueOf(iB), Double.valueOf(dA));
        com.ju.lib.datacommunication.network.http.i.b.d(j.n, nVar.toString());
        return n.a(nVar);
    }

    private static void d(File file, JSONObject jSONObject, o oVar) {
        n nVarC = c(jSONObject);
        if (nVarC == null) {
            return;
        }
        synchronized (p.class) {
            if (nVarC.d(f121a)) {
                return;
            }
            f121a = nVarC;
            if (oVar != null) {
                oVar.c(nVarC);
            }
            if (file == null) {
                return;
            }
            synchronized (p.class) {
                if (!nVarC.d(b) && k(file, nVarC)) {
                    b = nVarC;
                }
            }
        }
    }

    public static v e(String str, String str2) {
        return f(str, new JSONObject(str2), -1L);
    }

    public static v f(String str, JSONObject jSONObject, long j) throws JSONException {
        JSONArray jSONArrayOptJSONArray;
        long jOptLong = jSONObject.optLong("ttl");
        if (jOptLong <= 0 || (jSONArrayOptJSONArray = jSONObject.optJSONArray("ips")) == null || jSONArrayOptJSONArray.length() <= 0) {
            return null;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(jSONArrayOptJSONArray.length());
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            String string = jSONArrayOptJSONArray.getString(i);
            if (a(string)) {
                linkedHashSet.add(string);
            }
        }
        if (linkedHashSet.isEmpty()) {
            return null;
        }
        LinkedHashSet linkedHashSet2 = new LinkedHashSet(jSONArrayOptJSONArray.length());
        JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("proxy");
        if (jSONArrayOptJSONArray2 != null && jSONArrayOptJSONArray2.length() > 0) {
            for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                String string2 = jSONArrayOptJSONArray2.getString(i2);
                if (a(string2)) {
                    linkedHashSet2.add(string2);
                }
            }
        }
        if (j <= 0) {
            j = jSONObject.optLong("time", j);
            if (j <= 0) {
                j = SystemClock.elapsedRealtime();
            }
        }
        u uVar = new u();
        uVar.j(str);
        uVar.k(jOptLong);
        uVar.i(j);
        uVar.f(linkedHashSet);
        uVar.g(linkedHashSet2);
        return uVar.h();
    }

    public static List g(File file, String str, o oVar) throws JSONException {
        JSONObject jSONObject;
        JSONObject jSONObject2 = new JSONObject(str);
        if (!"0".equals(jSONObject2.getString("retCode"))) {
            return Collections.emptyList();
        }
        JSONObject jSONObject3 = jSONObject2.getJSONObject("data");
        Iterator<String> itKeys = jSONObject3.keys();
        ArrayList arrayList = new ArrayList();
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            v vVarF = f(next, jSONObject3.getJSONObject(next), jElapsedRealtime);
            if (vVarF != null) {
                arrayList.add(vVarF);
            }
        }
        if (jSONObject2.has("dnsclient") && (jSONObject = jSONObject2.getJSONObject("dnsclient")) != null) {
            d(file, jSONObject, oVar);
        }
        return arrayList;
    }

    public static void h(File file, o oVar) throws Throwable {
        File file2 = new File(file, "Config_GSLB");
        BufferedReader bufferedReader = null;
        try {
            try {
                try {
                    if (!file2.exists()) {
                        com.ju.lib.datacommunication.network.http.i.b.d(j.n, "GSLB config file doesn't exist");
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
                        d(null, new JSONObject(sb.toString()), oVar);
                        bufferedReader2.close();
                    } catch (IOException e) {
                        e = e;
                        bufferedReader = bufferedReader2;
                        e.printStackTrace();
                        if (bufferedReader == null) {
                        } else {
                            bufferedReader.close();
                        }
                    } catch (JSONException e2) {
                        e = e2;
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
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException e4) {
                e = e4;
            } catch (JSONException e5) {
                e = e5;
            }
        } catch (IOException e6) {
            e6.printStackTrace();
        }
    }

    public static String i(v vVar) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("ttl", vVar.k());
        jSONObject.put("time", vVar.j());
        List listG = vVar.g();
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < listG.size(); i++) {
            jSONArray.put(i, listG.get(i));
        }
        jSONObject.put("ips", jSONArray);
        return jSONObject.toString();
    }

    public static long j(double d) {
        return (long) (d * 1000.0d);
    }

    public static boolean k(File file, n nVar) throws Throwable {
        File file2 = new File(file, "Config_GSLB");
        OutputStreamWriter outputStreamWriter = null;
        try {
            try {
                try {
                    if (!file2.exists()) {
                        boolean zCreateNewFile = file2.createNewFile();
                        com.ju.lib.datacommunication.network.http.i.b.d(j.n, "create GSLB config file : " + zCreateNewFile);
                        if (!zCreateNewFile) {
                            return false;
                        }
                    }
                    OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(new FileOutputStream(file2), "UTF-8");
                    try {
                        outputStreamWriter2.write(b(nVar));
                        outputStreamWriter2.flush();
                        try {
                            outputStreamWriter2.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return true;
                    } catch (IOException e2) {
                        e = e2;
                        outputStreamWriter = outputStreamWriter2;
                        e.printStackTrace();
                        if (outputStreamWriter != null) {
                            outputStreamWriter.close();
                        }
                        return false;
                    } catch (NumberFormatException e3) {
                        e = e3;
                        outputStreamWriter = outputStreamWriter2;
                        e.printStackTrace();
                        if (outputStreamWriter != null) {
                            outputStreamWriter.close();
                        }
                        return false;
                    } catch (JSONException e4) {
                        e = e4;
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
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException e6) {
                e = e6;
            } catch (NumberFormatException e7) {
                e = e7;
            } catch (JSONException e8) {
                e = e8;
            }
        } catch (IOException e9) {
            e9.printStackTrace();
        }
    }
}
