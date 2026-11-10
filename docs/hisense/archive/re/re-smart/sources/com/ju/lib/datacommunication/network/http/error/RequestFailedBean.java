package com.ju.lib.datacommunication.network.http.error;

import android.text.TextUtils;
import b.a.a.a.a;
import com.google.android.gms.common.internal.ImagesContract;
import com.ju.lib.datacommunication.annotation.JuMethodAnnotation;
import com.ju.lib.datacommunication.network.http.address.ThirdAddressResponse;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class RequestFailedBean {
    public NetworkErrorEnum code;
    public String failMsg;
    public String ip;
    public HashMap<String, String> requestParams = new HashMap<>();
    public String subCode;
    public String url;

    public RequestFailedBean(NetworkErrorEnum networkErrorEnum, String str) {
        if (TextUtils.isEmpty(str) || networkErrorEnum == null) {
            HttpLog.e("RequestFailedBean", " url or code cannot be null ");
        } else if (str.contains("?")) {
            String[] strArrSplit = str.split("\\?");
            this.url = strArrSplit[0];
            parseUrlQuery(strArrSplit[1]);
        } else {
            this.url = str;
        }
        this.code = networkErrorEnum;
    }

    private void parseUrlQuery(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        for (String str2 : str.split("&")) {
            String[] strArrSplit = str2.split("=");
            if (strArrSplit.length == 2 && !TextUtils.isEmpty(strArrSplit[0])) {
                this.requestParams.put(strArrSplit[0], strArrSplit[1]);
            }
        }
        HttpLog.i("RequestFailedBean", " parseUrlQuery: ", this.requestParams);
    }

    public RequestFailedBean code(NetworkErrorEnum networkErrorEnum) {
        this.code = networkErrorEnum;
        return this;
    }

    public RequestFailedBean failMsg(String str) {
        this.failMsg = str;
        return this;
    }

    public HashMap<String, String> getRequestParams() {
        return this.requestParams;
    }

    public RequestFailedBean ip(String str) {
        this.ip = str;
        return this;
    }

    public boolean isLocalNetworkDisConnect() {
        return this.code == NetworkErrorEnum.LocalNetworkDisConnect;
    }

    public RequestFailedBean requestParams(HashMap<String, String> map) {
        if (HttpLog.isHttpStackLogAble()) {
            HttpLog.i("RequestFailedBean", " requestParams: ", map);
        }
        if (map != null) {
            this.requestParams.putAll(map);
        }
        return this;
    }

    public RequestFailedBean subCode(String str) {
        this.subCode = str;
        return this;
    }

    @JuMethodAnnotation(description = "bean转map，外部无需关注内部数据结构；转完可裁剪，requestParams由于需要序列化，需要外部处理", parameters = "", returnDes = "HashMap")
    public HashMap<String, String> toMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put(ThirdAddressResponse.CODE, String.valueOf(this.code.ordinal()));
        map.put(ImagesContract.URL, this.url);
        if (!TextUtils.isEmpty(this.subCode)) {
            map.put("subCode", this.subCode);
        }
        if (!TextUtils.isEmpty(this.failMsg)) {
            if (this.failMsg.length() > 100) {
                this.failMsg = this.failMsg.substring(0, 100);
            }
            map.put("failMsg", this.failMsg);
        }
        if (!TextUtils.isEmpty(this.ip)) {
            map.put("parsedIp", this.ip);
        }
        return map;
    }

    public String toString() {
        StringBuilder sbA = a.a("RequestFailedBean{url=");
        sbA.append(this.url);
        sbA.append(", code=");
        sbA.append(this.code);
        sbA.append(", subCode=");
        sbA.append(this.subCode);
        sbA.append(", failMsg=");
        sbA.append(this.failMsg);
        if (HttpLog.isHttpStackLogAble()) {
            sbA.append(", requestParams=");
            sbA.append(this.requestParams);
        }
        sbA.append("}");
        return sbA.toString();
    }

    public String url() {
        return this.url;
    }
}
