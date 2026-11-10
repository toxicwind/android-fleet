package com.ju.lib.datacommunication.network.http.dns;

import com.ju.lib.datacommunication.annotation.JuMethodAnnotation;
import java.net.InetAddress;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IGSLBListener {
    @JuMethodAnnotation(description = "GSLB解析异常时，回调", parameters = "HttpGslbException", returnDes = "")
    void onException(HttpGslbException httpGslbException);

    @JuMethodAnnotation(description = "GSLB解析成功时，回调", parameters = "gslbHost：用于解析域名的GSLB服务器地址； hostName：待解析域名； ip：解析后的ip； obj：扩展信息", returnDes = "")
    void onSuccess(String str, String str2, List<InetAddress> list, Object... objArr);
}
