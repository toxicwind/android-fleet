package com.ju.lib.datacommunication.network.http.address;

import android.text.TextUtils;
import b.a.a.a.a;
import com.hmct.cloud.sdk.utils.Params;
import com.ju.lib.datacommunication.network.http.HttpManager;
import com.ju.lib.datacommunication.network.http.builder.GetBuilder;
import com.ju.lib.datacommunication.network.http.core.HiResponse;
import com.ju.lib.datacommunication.network.http.core.signature.HiCloudKey;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class ThirdDynamicAddressManager {
    public static final String DYNAMICHOST = "https://bas-dynhost.hismarttv.com";
    public static final String FILE_NAME = "THIRD_ADDRESS.json";
    public static final String SIGN_KEY = "signatureServer";
    public static final String TAG = "ThirdDynamicAddressManager";
    public String mAppVersion;
    public String mDeviceId;
    public DynamicAddressParam mDynamicAddressParam;
    public String mFilePath;

    public ThirdDynamicAddressManager(String str, String str2, String str3, DynamicAddressParam dynamicAddressParam) {
        this.mDynamicAddressParam = null;
        this.mFilePath = a.a(str, "/", FILE_NAME);
        this.mAppVersion = str2;
        this.mDeviceId = str3;
        this.mDynamicAddressParam = dynamicAddressParam;
    }

    private void buildCommonParam(Map<String, String> map, DynamicAddressParam dynamicAddressParam) {
        if (!TextUtils.isEmpty(dynamicAddressParam.getAppVersion())) {
            map.put("appVersion", dynamicAddressParam.getAppVersion());
        }
        if (!TextUtils.isEmpty(dynamicAddressParam.getRefAppKey())) {
            map.put("refAppKey", dynamicAddressParam.getRefAppKey());
        }
        if (!TextUtils.isEmpty(dynamicAddressParam.getDeviceId())) {
            map.put(Params.DEVICEID, dynamicAddressParam.getDeviceId());
        }
        if (!TextUtils.isEmpty(dynamicAddressParam.version)) {
            map.put("version", dynamicAddressParam.version);
        }
        StringBuilder sbA = a.a("");
        sbA.append(System.currentTimeMillis() / 1000);
        map.put("timeStamp", sbA.toString());
        if (!TextUtils.isEmpty(dynamicAddressParam.format)) {
            map.put(Params.FORMAT, dynamicAddressParam.format);
        }
        if (!TextUtils.isEmpty(dynamicAddressParam.languageId)) {
            map.put(Params.LANGUAGEID, dynamicAddressParam.languageId);
        }
        if (TextUtils.isEmpty(dynamicAddressParam.sourceType)) {
            return;
        }
        map.put(Params.INVOKESOURCE, dynamicAddressParam.sourceType);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ThirdAddressResponse getAddressFromRemote() throws Throwable {
        HashMap map = new HashMap();
        DynamicAddressParam dynamicAddressParam = this.mDynamicAddressParam;
        ThirdAddressResponse jsonToObject = null;
        if (dynamicAddressParam == null) {
            HttpLog.i(TAG, "mDynamicAddressParam is null");
            return null;
        }
        buildCommonParam(map, dynamicAddressParam);
        HiResponse hiResponseExecute = ((GetBuilder) ((GetBuilder) ((GetBuilder) HttpManager.getHttpApi().get().url("https://bas-dynhost.hismarttv.com/pubdatasvc/getAppServiceListV2")).addQueryParams(map).addHeader(Params.APPKEY, this.mDynamicAddressParam.getRefAppKey())).addHeader("X-sign-For", HiCloudKey.encyptMapMD5WithAppSecret(map, this.mDynamicAddressParam.getAppSecretForSign()))).execute();
        if (hiResponseExecute != null && hiResponseExecute.isSuccessful() && hiResponseExecute.getBody() != null) {
            String strString = hiResponseExecute.getBody().string();
            String signValue = AppKeySignTool.getSignValue(strString, SIGN_KEY);
            String strEncyptMapMD5WithAppSecret = HiCloudKey.encyptMapMD5WithAppSecret(AppKeySignTool.getNoSignData(strString, SIGN_KEY), this.mDynamicAddressParam.getAppSecretForSign());
            if (TextUtils.isEmpty(signValue) || TextUtils.isEmpty(strEncyptMapMD5WithAppSecret)) {
                HttpLog.i(TAG, "signatureServer is empty or sin is empty");
            } else {
                if (!signValue.equals(strEncyptMapMD5WithAppSecret)) {
                    HttpLog.i(TAG, "check sign error");
                    return null;
                }
                jsonToObject = ThirdAddressResponse.parseJsonToObject(strString);
                if (jsonToObject != null) {
                    try {
                        if (jsonToObject.code == 0) {
                            HttpLog.i(TAG, "filepath:" + this.mFilePath);
                            saveAddressList(jsonToObject);
                        }
                    } catch (Exception e2) {
                        HttpLog.i(TAG, a.a("save file fail:", e2));
                    }
                }
            }
        }
        return jsonToObject;
    }

    public ThirdAddressResponse getAddressLocal() throws Throwable {
        if (!DynamicAddressTools.isExists(this.mFilePath)) {
            throw new FileNotFoundException("file not found");
        }
        String file = DynamicAddressTools.readFile(this.mFilePath);
        ThirdAddressResponse jsonToObject = (file == null || "".equals(file)) ? null : ThirdAddressResponse.parseJsonToObject(file);
        HttpLog.i(TAG, "read from file success");
        return jsonToObject;
    }

    public void saveAddressList(ThirdAddressResponse thirdAddressResponse) throws Throwable {
        DynamicAddressTools.saveFile(ThirdAddressResponse.parseToString(thirdAddressResponse), this.mFilePath);
        HttpLog.i(TAG, "save file success");
    }
}
