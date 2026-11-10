package com.ju.lib.datacommunication.network.http.address;

import android.text.TextUtils;
import com.google.firebase.installations.local.IidStore;
import e.b.a;
import e.b.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class ThirdAddressResponse {
    public static final String ADDRESS_LIST = "addressList";
    public static final String ADDRESS_TYPE = "addressType";
    public static final String CODE = "code";
    public static final String DESC = "desc";
    public static final String PAYLOAD = "payload";
    public static final String STATIC_FLAG = "staticflag";
    public static final String SUB_ERRCODE = "subErrCode";
    public static final String TAG = "ThirdAddressResponse";
    public static final String TIME_STAMP = "timeStamp";
    public int code;
    public String desc;
    public List<AddressInfo> payload = new CopyOnWriteArrayList();
    public int subErrCode;
    public long timeStamp;

    public static ThirdAddressResponse parseJsonToObject(String str) {
        d dVar;
        int iA;
        if (str == null || "".equals(str) || (iA = (dVar = new d(str)).a(CODE, 0)) != 0) {
            return null;
        }
        ThirdAddressResponse thirdAddressResponse = new ThirdAddressResponse();
        thirdAddressResponse.code = iA;
        thirdAddressResponse.timeStamp = dVar.g("timeStamp");
        a aVarK = dVar.k(PAYLOAD);
        if (aVarK != null && aVarK.a() != 0) {
            CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
            for (int i = 0; i < aVarK.a(); i++) {
                AddressInfo addressInfo = new AddressInfo();
                d dVarA = aVarK.a(i);
                addressInfo.addresstype = dVarA.a("addressType", "");
                a aVarK2 = dVarA.k("addressList");
                ArrayList arrayList = new ArrayList();
                if (aVarK2 != null) {
                    for (int i2 = 0; i2 < aVarK2.a(); i2++) {
                        Object objC = aVarK2.c(i2);
                        String string = d.f2162a.equals(objC) ? "" : objC.toString();
                        if (!TextUtils.isEmpty(string)) {
                            arrayList.add(string);
                        }
                    }
                }
                addressInfo.addressList = arrayList;
                copyOnWriteArrayList.add(addressInfo);
            }
            thirdAddressResponse.payload = copyOnWriteArrayList;
            return thirdAddressResponse;
        }
        return null;
    }

    public static String parseToString(ThirdAddressResponse thirdAddressResponse) {
        if (thirdAddressResponse == null) {
            return null;
        }
        d dVar = new d();
        dVar.b(CODE, thirdAddressResponse.code);
        dVar.b("timeStamp", thirdAddressResponse.timeStamp);
        a aVar = new a();
        List<AddressInfo> list = thirdAddressResponse.payload;
        if (list != null) {
            for (AddressInfo addressInfo : list) {
                d dVar2 = new d();
                dVar2.a("addressType", (Object) addressInfo.addresstype);
                a aVar2 = new a();
                List<String> list2 = addressInfo.addressList;
                if (list2 != null) {
                    Iterator<String> it = list2.iterator();
                    while (it.hasNext()) {
                        aVar2.f2161a.add(it.next());
                    }
                }
                dVar2.a("addressList", aVar2);
                aVar.f2161a.add(dVar2);
            }
        }
        dVar.a(PAYLOAD, aVar);
        return dVar.toString();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(IidStore.JSON_ENCODED_PREFIX);
        stringBuffer.append("code:");
        stringBuffer.append(this.code);
        stringBuffer.append(";");
        stringBuffer.append("subErrCode:");
        stringBuffer.append(this.subErrCode);
        stringBuffer.append(";");
        stringBuffer.append("desc:");
        stringBuffer.append(this.desc);
        stringBuffer.append(";");
        stringBuffer.append("timeStamp:");
        stringBuffer.append(this.timeStamp);
        stringBuffer.append(";");
        stringBuffer.append("payload:");
        for (AddressInfo addressInfo : this.payload) {
            stringBuffer.append("addressInfo:");
            stringBuffer.append(addressInfo.toString());
            stringBuffer.append(";");
        }
        stringBuffer.append("}");
        return stringBuffer.toString();
    }
}
