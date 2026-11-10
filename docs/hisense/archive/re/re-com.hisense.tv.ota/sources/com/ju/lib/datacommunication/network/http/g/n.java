package com.ju.lib.datacommunication.network.http.g;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f134a;
    public int b;
    public String c;
    public List d = new CopyOnWriteArrayList();
    public long e;

    public static n a(String str) throws JSONException {
        JSONObject jSONObject;
        int iOptInt;
        if (str == null || "".equals(str) || (iOptInt = (jSONObject = new JSONObject(str)).optInt("code")) != 0) {
            return null;
        }
        n nVar = new n();
        nVar.f134a = iOptInt;
        nVar.e = jSONObject.getLong("timeStamp");
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("payload");
        if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() != 0) {
            CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                a aVar = new a();
                JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i);
                aVar.f125a = jSONObject2.optString("addressType");
                JSONArray jSONArrayOptJSONArray2 = jSONObject2.optJSONArray("addressList");
                ArrayList arrayList = new ArrayList();
                if (jSONArrayOptJSONArray2 != null) {
                    for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                        String strOptString = jSONArrayOptJSONArray2.optString(i2);
                        if (!TextUtils.isEmpty(strOptString)) {
                            arrayList.add(strOptString);
                        }
                    }
                }
                aVar.b = arrayList;
                copyOnWriteArrayList.add(aVar);
            }
            nVar.d = copyOnWriteArrayList;
            return nVar;
        }
        return null;
    }

    public static String b(n nVar) throws JSONException {
        if (nVar == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("code", nVar.f134a);
        jSONObject.put("timeStamp", nVar.e);
        JSONArray jSONArray = new JSONArray();
        List<a> list = nVar.d;
        if (list != null) {
            for (a aVar : list) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("addressType", aVar.f125a);
                JSONArray jSONArray2 = new JSONArray();
                List list2 = aVar.b;
                if (list2 != null) {
                    Iterator it = list2.iterator();
                    while (it.hasNext()) {
                        jSONArray2.put((String) it.next());
                    }
                }
                jSONObject2.put("addressList", jSONArray2);
                jSONArray.put(jSONObject2);
            }
        }
        jSONObject.put("payload", jSONArray);
        return jSONObject.toString();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{");
        stringBuffer.append("code:");
        stringBuffer.append(this.f134a);
        stringBuffer.append(";");
        stringBuffer.append("subErrCode:");
        stringBuffer.append(this.b);
        stringBuffer.append(";");
        stringBuffer.append("desc:");
        stringBuffer.append(this.c);
        stringBuffer.append(";");
        stringBuffer.append("timeStamp:");
        stringBuffer.append(this.e);
        stringBuffer.append(";");
        stringBuffer.append("payload:");
        for (a aVar : this.d) {
            stringBuffer.append("addressInfo:");
            stringBuffer.append(aVar.toString());
            stringBuffer.append(";");
        }
        stringBuffer.append("}");
        return stringBuffer.toString();
    }
}
