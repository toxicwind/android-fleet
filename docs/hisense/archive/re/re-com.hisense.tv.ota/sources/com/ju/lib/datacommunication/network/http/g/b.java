package com.ju.lib.datacommunication.network.http.g;

import android.text.TextUtils;
import android.util.Xml;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f126a;
    public List b = new CopyOnWriteArrayList();
    public long c;
    public String d;

    public static b a(String str) throws JSONException {
        JSONObject jSONObject;
        int iOptInt;
        if (str == null || "".equals(str) || (iOptInt = (jSONObject = new JSONObject(str)).optInt("resultCode")) != 0) {
            return null;
        }
        b bVar = new b();
        bVar.f126a = iOptInt;
        bVar.d = jSONObject.optString("domainVersion");
        bVar.c = jSONObject.getLong("timeStampRet");
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("appServiceList");
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
                        JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray2.optJSONObject(i2);
                        if (jSONObjectOptJSONObject != null) {
                            arrayList.add(jSONObjectOptJSONObject.optString("address"));
                        } else {
                            arrayList.add("");
                        }
                    }
                }
                aVar.b = arrayList;
                copyOnWriteArrayList.add(aVar);
            }
            bVar.b = copyOnWriteArrayList;
            return bVar;
        }
        return null;
    }

    public static String b(b bVar) throws JSONException {
        if (bVar == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("resultCode", bVar.f126a);
        jSONObject.put("domainVersion", bVar.d);
        jSONObject.put("timeStampRet", bVar.c);
        JSONArray jSONArray = new JSONArray();
        List<a> list = bVar.b;
        if (list != null) {
            for (a aVar : list) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("addressType", aVar.f125a);
                JSONArray jSONArray2 = new JSONArray();
                List<String> list2 = aVar.b;
                if (list2 != null) {
                    for (String str : list2) {
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put("address", str);
                        jSONArray2.put(jSONObject3);
                    }
                }
                jSONObject2.put("addressList", jSONArray2);
                jSONArray.put(jSONObject2);
            }
        }
        jSONObject.put("appServiceList", jSONArray);
        return jSONObject.toString();
    }

    public static b c(String str) {
        ByteArrayInputStream byteArrayInputStream;
        String strNextText;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        b bVar = new b();
        try {
            byteArrayInputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            byteArrayInputStream = null;
        }
        try {
            XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
            xmlPullParserNewPullParser.setInput(byteArrayInputStream, "UTF-8");
            a aVar = null;
            ArrayList arrayList = null;
            ArrayList arrayList2 = null;
            for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                if (eventType == 2) {
                    String name = xmlPullParserNewPullParser.getName();
                    if (!name.equalsIgnoreCase("response")) {
                        if (name.equalsIgnoreCase("resultCode")) {
                            String strNextText2 = xmlPullParserNewPullParser.nextText();
                            if (strNextText2 != null && !strNextText2.equals("")) {
                                bVar.f126a = Integer.valueOf(strNextText2).intValue();
                            }
                        } else if (name.equalsIgnoreCase("timeStampRet")) {
                            String strNextText3 = xmlPullParserNewPullParser.nextText();
                            if (!TextUtils.isEmpty(strNextText3) && TextUtils.isDigitsOnly(strNextText3)) {
                                bVar.c = Long.valueOf(strNextText3).longValue();
                            }
                        } else if (name.equals("domainVersion")) {
                            String strNextText4 = xmlPullParserNewPullParser.nextText();
                            if (strNextText4 != null && !strNextText4.equals("")) {
                                bVar.d = strNextText4;
                            }
                        } else if (name.equals("appServiceList")) {
                            arrayList2 = new ArrayList();
                        } else if (name.equals("addressInfo")) {
                            aVar = new a();
                        } else if (name.equals("addressType")) {
                            String strNextText5 = xmlPullParserNewPullParser.nextText();
                            if (strNextText5 != null && !strNextText5.equals("")) {
                                aVar.f125a = strNextText5;
                            }
                        } else if (name.equals("addressList")) {
                            arrayList = new ArrayList();
                        } else if (name.equals("address") && (strNextText = xmlPullParserNewPullParser.nextText()) != null && !strNextText.equals("")) {
                            arrayList.add(strNextText);
                        }
                    }
                } else if (eventType != 3) {
                    continue;
                } else {
                    String name2 = xmlPullParserNewPullParser.getName();
                    if (name2.equals("addressList")) {
                        aVar.b = arrayList;
                        arrayList = null;
                    } else if (name2.equalsIgnoreCase("addressInfo")) {
                        arrayList2.add(aVar);
                        aVar = null;
                    } else if (name2.equalsIgnoreCase("appServiceList")) {
                        bVar.b = arrayList2;
                        arrayList2 = null;
                    }
                }
            }
            return bVar;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        } finally {
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{");
        stringBuffer.append("resultCode:");
        stringBuffer.append(this.f126a);
        stringBuffer.append(";");
        stringBuffer.append("timeStampRet:");
        stringBuffer.append(this.c);
        stringBuffer.append(";");
        stringBuffer.append("domainVersion:");
        stringBuffer.append(this.d);
        stringBuffer.append(";");
        stringBuffer.append("appServiceList:");
        for (a aVar : this.b) {
            stringBuffer.append("addressInfo:");
            stringBuffer.append(aVar.toString());
            stringBuffer.append(";");
        }
        stringBuffer.append("}");
        return stringBuffer.toString();
    }
}
