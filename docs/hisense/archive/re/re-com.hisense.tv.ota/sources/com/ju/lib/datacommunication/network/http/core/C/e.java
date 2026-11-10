package com.ju.lib.datacommunication.network.http.core.C;

import com.ju.lib.datacommunication.network.http.core.k;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class e extends k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    k f82a;
    String b;

    @Override // com.ju.lib.datacommunication.network.http.core.k
    public long a() {
        return this.f82a.a();
    }

    @Override // com.ju.lib.datacommunication.network.http.core.k
    public String b() {
        return this.f82a.b();
    }

    @Override // com.ju.lib.datacommunication.network.http.core.k
    public void c(OutputStream outputStream) {
        this.f82a.c(outputStream);
    }

    public HashMap d() {
        if (this.b == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(this.b);
            HashMap map = new HashMap();
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String string = itKeys.next().toString();
                map.put(string, jSONObject.optString(string));
            }
            return map;
        } catch (JSONException e) {
            com.ju.lib.datacommunication.network.http.i.b.g("HiStringBody", " parse content failed ");
            e.printStackTrace();
            return null;
        }
    }
}
