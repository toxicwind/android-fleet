package com.ju.lib.datacommunication.network.http.address;

import b.a.a.a.a;
import com.hmct.cloud.sdk.utils.Constants;
import com.hmct.cloud.sdk.utils.Params;
import com.ju.lib.datacommunication.network.http.HttpManager;
import com.ju.lib.datacommunication.network.http.builder.GetBuilder;
import com.ju.lib.datacommunication.network.http.core.HiResponse;
import com.ju.lib.datacommunication.network.http.core.signature.HiCloudKey;
import com.ju.lib.datacommunication.network.http.core.signature.XmlSignature;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class DynamicAddressManager {
    public static final String DYNAMICHOST = "http://api-dynhost.hismarttv.com";
    public static final String FILE_NAME = "ADDRESS.json";
    public static final String TAG = "DynamicAddressManager";
    public String mAppVersion;
    public String mDeviceId;
    public String mFilePath;

    public DynamicAddressManager(String str, String str2, String str3) {
        this.mFilePath = a.a(str, "/", FILE_NAME);
        this.mAppVersion = str2;
        this.mDeviceId = str3;
    }

    private void saveAddressList(AddressResponse addressResponse) throws Throwable {
        DynamicAddressTools.saveFile(AddressResponse.parseToString(addressResponse), this.mFilePath);
        HttpLog.i(TAG, "save file success");
    }

    private void setCommonParam(Map<String, String> map) {
        StringBuilder sbA = a.a("");
        sbA.append(this.mAppVersion);
        map.put("appVersion", sbA.toString());
        map.put("refAppKey", "1175952793");
        map.put("version", Constants.DEFAULTAPIVERSION);
        map.put("timeStamp", "" + (System.currentTimeMillis() / 1000));
        map.put(Params.FORMAT, "0");
        map.put(Params.LANGUAGEID, "0");
        map.put(Params.INVOKESOURCE, "1");
        map.put(Params.DEVICEID, this.mDeviceId);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AddressResponse getAddressFromRemote(String str) throws Throwable {
        HashMap map = new HashMap();
        map.put(AddressResponse.sDomainVersion, str);
        setCommonParam(map);
        HiResponse hiResponseExecute = ((GetBuilder) ((GetBuilder) HttpManager.getHttpApi().get().url("http://api-dynhost.hismarttv.com/pds/getAppPlatformDomainList")).addQueryParams(map).addQueryParams("sign", HiCloudKey.encyptMapRSA(map, HiCloudKey.getPublicKey())).signature(new XmlSignature())).execute();
        if (hiResponseExecute == null || !hiResponseExecute.isSuccessful() || hiResponseExecute.getBody() == null) {
            return null;
        }
        AddressResponse xmlToObject = AddressResponse.parseXmlToObject(hiResponseExecute.getBody().string());
        if (xmlToObject == null) {
            return xmlToObject;
        }
        try {
            if (xmlToObject.resultCode != 0) {
                return xmlToObject;
            }
            HttpLog.i(TAG, "filepath:" + this.mFilePath);
            saveAddressList(xmlToObject);
            return xmlToObject;
        } catch (Exception e2) {
            HttpLog.i(TAG, a.a("save file fail:", e2));
            return xmlToObject;
        }
    }

    public AddressResponse getAddressLocal() throws Throwable {
        if (!DynamicAddressTools.isExists(this.mFilePath)) {
            throw new FileNotFoundException("file not found");
        }
        String file = DynamicAddressTools.readFile(this.mFilePath);
        AddressResponse jsonToObject = (file == null || "".equals(file)) ? null : AddressResponse.parseJsonToObject(file);
        HttpLog.i(TAG, "read from file success");
        return jsonToObject;
    }
}
