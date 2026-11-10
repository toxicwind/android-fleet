package com.ju.lib.datacommunication.network.http.address;

import com.google.firebase.installations.local.IidStore;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AddressInfo {
    public List<String> addressList;
    public String addresstype;

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(IidStore.JSON_ENCODED_PREFIX);
        stringBuffer.append("addresstype:");
        stringBuffer.append(this.addresstype);
        stringBuffer.append("addressList:");
        Iterator<String> it = this.addressList.iterator();
        while (it.hasNext()) {
            stringBuffer.append(it.next());
            stringBuffer.append(";");
        }
        stringBuffer.append("}");
        return stringBuffer.toString();
    }
}
