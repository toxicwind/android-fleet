package com.ju.lib.datacommunication.network.http.address;

import android.text.TextUtils;
import android.util.Xml;
import com.google.firebase.installations.local.IidStore;
import e.b.a;
import e.b.d;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes2.dex */
public class AddressResponse {
    public static final String TAG = "AddressResponse";
    public static final String sAddress = "address";
    public static final String sAddressInfo = "addressInfo";
    public static final String sAddressList = "addressList";
    public static final String sAddressType = "addressType";
    public static final String sAppServiceList = "appServiceList";
    public static final String sDomainVersion = "domainVersion";
    public static final String sResponseTag = "response";
    public static final String sResultCode = "resultCode";
    public static final String sTimeStampRet = "timeStampRet";
    public List<AddressInfo> appServiceList = new CopyOnWriteArrayList();
    public String domainVersion;
    public int resultCode;
    public long timeStampRet;

    public static AddressResponse parseJsonToObject(String str) {
        d dVar;
        int iA;
        if (str == null || "".equals(str) || (iA = (dVar = new d(str)).a(sResultCode, 0)) != 0) {
            return null;
        }
        AddressResponse addressResponse = new AddressResponse();
        addressResponse.resultCode = iA;
        addressResponse.domainVersion = dVar.a(sDomainVersion, "");
        addressResponse.timeStampRet = dVar.g(sTimeStampRet);
        a aVarK = dVar.k(sAppServiceList);
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
                        d dVarD = aVarK2.d(i2);
                        if (dVarD != null) {
                            arrayList.add(dVarD.a(sAddress, ""));
                        } else {
                            arrayList.add("");
                        }
                    }
                }
                addressInfo.addressList = arrayList;
                copyOnWriteArrayList.add(addressInfo);
            }
            addressResponse.appServiceList = copyOnWriteArrayList;
            return addressResponse;
        }
        return null;
    }

    public static String parseToString(AddressResponse addressResponse) {
        if (addressResponse == null) {
            return null;
        }
        d dVar = new d();
        dVar.b(sResultCode, addressResponse.resultCode);
        dVar.a(sDomainVersion, (Object) addressResponse.domainVersion);
        dVar.b(sTimeStampRet, addressResponse.timeStampRet);
        a aVar = new a();
        List<AddressInfo> list = addressResponse.appServiceList;
        if (list != null) {
            for (AddressInfo addressInfo : list) {
                d dVar2 = new d();
                dVar2.a("addressType", (Object) addressInfo.addresstype);
                a aVar2 = new a();
                List<String> list2 = addressInfo.addressList;
                if (list2 != null) {
                    for (String str : list2) {
                        d dVar3 = new d();
                        dVar3.a(sAddress, (Object) str);
                        aVar2.f2161a.add(dVar3);
                    }
                }
                dVar2.a("addressList", aVar2);
                aVar.f2161a.add(dVar2);
            }
        }
        dVar.a(sAppServiceList, aVar);
        return dVar.toString();
    }

    public static AddressResponse parseXmlToObject(String str) {
        ByteArrayInputStream byteArrayInputStream;
        String strNextText;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        AddressResponse addressResponse = new AddressResponse();
        try {
            byteArrayInputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            byteArrayInputStream = null;
        }
        try {
            XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
            xmlPullParserNewPullParser.setInput(byteArrayInputStream, "UTF-8");
            AddressInfo addressInfo = null;
            ArrayList arrayList = null;
            ArrayList arrayList2 = null;
            for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                if (eventType != 0) {
                    if (eventType == 2) {
                        String name = xmlPullParserNewPullParser.getName();
                        if (!name.equalsIgnoreCase(sResponseTag)) {
                            if (name.equalsIgnoreCase(sResultCode)) {
                                String strNextText2 = xmlPullParserNewPullParser.nextText();
                                if (strNextText2 != null && !strNextText2.equals("")) {
                                    addressResponse.resultCode = Integer.valueOf(strNextText2).intValue();
                                }
                            } else if (name.equalsIgnoreCase(sTimeStampRet)) {
                                String strNextText3 = xmlPullParserNewPullParser.nextText();
                                if (!TextUtils.isEmpty(strNextText3) && TextUtils.isDigitsOnly(strNextText3)) {
                                    addressResponse.timeStampRet = Long.valueOf(strNextText3).longValue();
                                }
                            } else if (name.equals(sDomainVersion)) {
                                String strNextText4 = xmlPullParserNewPullParser.nextText();
                                if (strNextText4 != null && !strNextText4.equals("")) {
                                    addressResponse.domainVersion = strNextText4;
                                }
                            } else if (name.equals(sAppServiceList)) {
                                arrayList2 = new ArrayList();
                            } else if (name.equals(sAddressInfo)) {
                                addressInfo = new AddressInfo();
                            } else if (name.equals("addressType")) {
                                String strNextText5 = xmlPullParserNewPullParser.nextText();
                                if (strNextText5 != null && !strNextText5.equals("")) {
                                    addressInfo.addresstype = strNextText5;
                                }
                            } else if (name.equals("addressList")) {
                                arrayList = new ArrayList();
                            } else if (name.equals(sAddress) && (strNextText = xmlPullParserNewPullParser.nextText()) != null && !strNextText.equals("")) {
                                arrayList.add(strNextText);
                            }
                        }
                    } else if (eventType != 3) {
                        continue;
                    } else {
                        String name2 = xmlPullParserNewPullParser.getName();
                        if (name2.equals("addressList")) {
                            addressInfo.addressList = arrayList;
                            arrayList = null;
                        } else if (name2.equalsIgnoreCase(sAddressInfo)) {
                            arrayList2.add(addressInfo);
                            addressInfo = null;
                        } else if (name2.equalsIgnoreCase(sAppServiceList)) {
                            addressResponse.appServiceList = arrayList2;
                            arrayList2 = null;
                        }
                    }
                }
            }
            return addressResponse;
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        } finally {
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(IidStore.JSON_ENCODED_PREFIX);
        stringBuffer.append("resultCode:");
        stringBuffer.append(this.resultCode);
        stringBuffer.append(";");
        stringBuffer.append("timeStampRet:");
        stringBuffer.append(this.timeStampRet);
        stringBuffer.append(";");
        stringBuffer.append("domainVersion:");
        stringBuffer.append(this.domainVersion);
        stringBuffer.append(";");
        stringBuffer.append("appServiceList:");
        for (AddressInfo addressInfo : this.appServiceList) {
            stringBuffer.append("addressInfo:");
            stringBuffer.append(addressInfo.toString());
            stringBuffer.append(";");
        }
        stringBuffer.append("}");
        return stringBuffer.toString();
    }
}
