package com.ju.lib.datacommunication.network.http.g;

import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f125a;
    public List b;

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{");
        stringBuffer.append("addresstype:");
        stringBuffer.append(this.f125a);
        stringBuffer.append("addressList:");
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            stringBuffer.append((String) it.next());
            stringBuffer.append(";");
        }
        stringBuffer.append("}");
        return stringBuffer.toString();
    }
}
