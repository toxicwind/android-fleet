package com.ju.lib.datacommunication.network.http.core.request;

import com.ju.lib.datacommunication.network.http.core.HiRequest;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import e.b.b;
import e.b.d;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class HiStringBody extends HiRequest.Body {
    public String content;
    public HiRequest.Body realBody;

    public HiStringBody(HiRequest.Body body, String str) {
        this.realBody = body;
        this.content = str;
    }

    @Override // com.ju.lib.datacommunication.network.http.core.HiRequest.Body
    public long contentLength() {
        return this.realBody.contentLength();
    }

    @Override // com.ju.lib.datacommunication.network.http.core.HiRequest.Body
    public String contentType() {
        return this.realBody.contentType();
    }

    public HashMap<String, String> getContent() {
        String str = this.content;
        if (str == null) {
            return null;
        }
        try {
            d dVar = new d(str);
            HashMap<String, String> map = new HashMap<>();
            Iterator<String> itA = dVar.a();
            while (itA.hasNext()) {
                String string = itA.next().toString();
                map.put(string, dVar.m(string));
            }
            return map;
        } catch (b e2) {
            HttpLog.w("HiStringBody", " parse content failed ");
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.ju.lib.datacommunication.network.http.core.HiRequest.Body
    public void writeTo(OutputStream outputStream) {
        this.realBody.writeTo(outputStream);
    }
}
