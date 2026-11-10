package com.ju.lib.datacommunication.network.http.dns;

import android.os.SystemClock;
import android.text.TextUtils;
import b.a.a.a.a;
import com.google.common.net.HttpHeaders;
import com.ju.lib.datacommunication.network.http.INetworkConnectDetector;
import com.ju.lib.datacommunication.network.http.core.HiHttpClient;
import com.ju.lib.datacommunication.network.http.core.HiRequest;
import com.ju.lib.datacommunication.network.http.core.HiResponse;
import com.ju.lib.datacommunication.network.http.core.HostInfo;
import com.ju.lib.datacommunication.network.http.core.HttpException;
import com.ju.lib.datacommunication.network.http.core.HttpStack;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import com.ju.lib.datareport.ReportService;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import okhttp3.internal.http2.Http2Codec;

/* JADX INFO: loaded from: classes2.dex */
public class HttpDns implements HttpStack.Dns, Tools.IGslbConfigListener {
    public static long HTTP_DNS_ERROR_MAX_TIMEOUT;
    public static volatile long HTTP_DNS_ERROR_TIMEOUT;
    public static final byte[] KEYS;
    public static final String TAG;
    public String mAppPackageName;
    public String mAppVersionCode;
    public String mAppVersionName;
    public File mCacheDirectory;
    public HiHttpClient mClient;
    public String mCustomId;
    public String mDeviceId;
    public IGSLBListener mGSLBListener;
    public GslbHostName mGslbHostName;
    public volatile long mHttpDnsErrorTime = -HTTP_DNS_ERROR_MAX_TIMEOUT;
    public String mJuAppVersion;
    public NetworkConnectChecker mNetworkConnectChecker;
    public Secret mSecret;

    private static class NetworkConnectChecker {
        public INetworkConnectDetector networkConnectDetector;

        public NetworkConnectChecker(INetworkConnectDetector iNetworkConnectDetector) {
            this.networkConnectDetector = iNetworkConnectDetector;
            HttpDns.dnsLogI("networkConnectDetector = ", iNetworkConnectDetector);
        }

        public boolean isNetworkConnected() {
            INetworkConnectDetector iNetworkConnectDetector = this.networkConnectDetector;
            if (iNetworkConnectDetector == null) {
                return true;
            }
            boolean zIsLocalNetworkConnected = iNetworkConnectDetector.isLocalNetworkConnected();
            HttpDns.dnsLogI("localNetworkConnected = ", Boolean.valueOf(zIsLocalNetworkConnected));
            return zIsLocalNetworkConnected;
        }
    }

    private static class Result {
        public String gslbHost;
        public List<HostInfo> list;

        public Result(List<HostInfo> list, String str) {
            this.list = list;
            this.gslbHost = str;
        }
    }

    static {
        StringBuilder sbA = a.a(HiHttpClient.HTTP_COMMON_TAG);
        sbA.append(HttpDns.class.getSimpleName());
        TAG = sbA.toString();
        HTTP_DNS_ERROR_TIMEOUT = Tools.toMilliSecond(180.0d);
        HTTP_DNS_ERROR_MAX_TIMEOUT = Tools.toMilliSecond(300.0d);
        KEYS = new byte[]{105, 108, 111, 118, 101, 36, 38, 104, 97, 116, 101, 119, 111, 114, 107, 44, 53, 49, 56};
    }

    public HttpDns(HiHttpClient hiHttpClient, File file, String str, String str2, String str3, String str4, String str5, IGSLBListener iGSLBListener, INetworkConnectDetector iNetworkConnectDetector) {
        this.mAppVersionCode = "";
        this.mAppVersionName = "";
        this.mAppPackageName = "";
        this.mJuAppVersion = "";
        if (hiHttpClient == null) {
            throw new NullPointerException("HiHttpClient is null! ");
        }
        if (TextUtils.isEmpty(str)) {
            throw new NullPointerException("DeviceId is empty! ");
        }
        this.mCacheDirectory = file;
        this.mGslbHostName = GslbHostName.newInstance(file);
        this.mClient = hiHttpClient;
        this.mDeviceId = str;
        this.mAppVersionCode = str2;
        this.mAppVersionName = str3;
        this.mAppPackageName = str4;
        this.mJuAppVersion = str5;
        this.mGSLBListener = iGSLBListener;
        this.mNetworkConnectChecker = new NetworkConnectChecker(iNetworkConnectDetector);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update((str + new String(KEYS, "UTF-8")).getBytes("UTF-8"));
            this.mSecret = new Secret(messageDigest.digest());
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        } catch (NoSuchAlgorithmException e3) {
            throw new RuntimeException(e3);
        }
        Tools.readConfig(this.mCacheDirectory, this);
    }

    private List<HttpGslbException.GslbException> addGslbException(List<HttpGslbException.GslbException> list, HttpGslbException.GslbException gslbException) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(gslbException);
        return list;
    }

    public static void dnsLogI(Object... objArr) {
        if (HttpLog.isHttpStackLogAble()) {
            HttpLog.i(TAG, objArr);
        }
    }

    private void onException(String str, String str2, HttpGslbException.Status status) {
        IGSLBListener iGSLBListener = this.mGSLBListener;
        if (iGSLBListener != null) {
            iGSLBListener.onException(new HttpGslbException(str, new Exception(str2)).setStatus(status));
        }
    }

    private void onSuccess(String str, String str2, List<InetAddress> list) {
        IGSLBListener iGSLBListener = this.mGSLBListener;
        if (iGSLBListener != null) {
            iGSLBListener.onSuccess(str, str2, list, new Object[0]);
        }
    }

    private Result requestGslb(String... strArr) throws HttpGslbException {
        List<HttpGslbException.GslbException> listAddGslbException;
        char c2 = 2;
        char c3 = 0;
        dnsLogI("Gslb requestGslb: ", Arrays.toString(strArr));
        HashMap map = new HashMap();
        map.put(ReportService.KEY_DEVICE_ID, this.mDeviceId);
        map.put("appVersionCode", this.mAppVersionCode);
        map.put("appVersionName", this.mAppVersionName);
        map.put("appPackageName", this.mAppPackageName);
        map.put("appVersion", this.mJuAppVersion);
        map.put("timeStamp", String.valueOf(System.currentTimeMillis()));
        StringBuilder sb = new StringBuilder();
        try {
            if (TextUtils.isEmpty(this.mCustomId)) {
                map.put("custom_id", this.mSecret.encrypt(""));
            } else {
                map.put("custom_id", this.mSecret.encrypt(this.mCustomId));
            }
            for (int i = 0; i < strArr.length; i++) {
                if (i != 0) {
                    sb.append(";");
                }
                sb.append(strArr[i]);
            }
            map.put(Http2Codec.HOST, this.mSecret.encrypt(sb.toString()));
            List<HttpGslbException.GslbException> listAddGslbException2 = null;
            for (String str : this.mGslbHostName.getHosts()) {
                try {
                    HiResponse hiResponseExecute = this.mClient.execute(new HiRequest.Builder().url("http://" + str + "/d", map).header(HttpHeaders.HOST, GslbHostName.DOMAIN_GSLB_SERVER).build());
                    if (hiResponseExecute.isSuccessful()) {
                        String strDecrypt = this.mSecret.decrypt(hiResponseExecute.getBody().string());
                        Object[] objArr = new Object[6];
                        objArr[c3] = "Gslb host=";
                        objArr[1] = strArr[c3];
                        objArr[c2] = ", gslbHost=";
                        objArr[3] = str;
                        objArr[4] = ", body = ";
                        objArr[5] = strDecrypt;
                        dnsLogI(objArr);
                        return new Result(Tools.parseJson(this.mCacheDirectory, strDecrypt, this), str);
                    }
                    listAddGslbException = addGslbException(listAddGslbException2, new HttpGslbException.GslbException(str, sb.toString(), false, hiResponseExecute.getCode(), hiResponseExecute.getMessage(), null));
                    c3 = 0;
                    listAddGslbException2 = listAddGslbException;
                    c2 = 2;
                } catch (HttpException e2) {
                    HttpLog.w(TAG, e2, a.a("request: ", str));
                    if (!this.mNetworkConnectChecker.isNetworkConnected()) {
                        throw new HttpGslbException(sb.toString(), e2).setStatus(HttpGslbException.Status.NetworkNotConnected);
                    }
                    listAddGslbException2 = addGslbException(listAddGslbException2, new HttpGslbException.GslbException(str, sb.toString(), false, -1, e2.getMessage(), e2));
                    c3 = 0;
                } catch (Error e3) {
                    HttpLog.w(TAG, e3, a.a("request: ", str));
                    listAddGslbException = addGslbException(listAddGslbException2, new HttpGslbException.GslbException(str, sb.toString(), false, -3, e3.getMessage(), e3));
                } catch (Exception e4) {
                    HttpLog.w(TAG, e4, a.a("request: ", str));
                    listAddGslbException = addGslbException(listAddGslbException2, new HttpGslbException.GslbException(str, sb.toString(), false, -2, e4.getMessage(), e4));
                } catch (Throwable th) {
                    HttpLog.w(TAG, th, a.a("request: ", str));
                    listAddGslbException = addGslbException(listAddGslbException2, new HttpGslbException.GslbException(str, sb.toString(), false, -4, th.getMessage(), th));
                }
            }
            throw new HttpGslbException(sb.toString(), listAddGslbException2).setStatus(HttpGslbException.Status.Other);
        } catch (Exception e5) {
            throw new HttpGslbException(sb.toString(), e5).setStatus(HttpGslbException.Status.RequestParamError);
        }
    }

    private void resetGslbCacheAndErrorTime() {
        this.mHttpDnsErrorTime = -HTTP_DNS_ERROR_MAX_TIMEOUT;
        DnsCache.Holder.INSTANCE.resetCacheDns();
    }

    private HostInfo systemDns(String str) throws UnknownHostException {
        InetAddress[] allByName = InetAddress.getAllByName(str);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (InetAddress inetAddress : allByName) {
            String string = inetAddress.toString();
            linkedHashSet.add(string.substring(string.lastIndexOf("/") + 1));
        }
        return new HostInfo.Builder().setHostName(str).addIps(linkedHashSet).build();
    }

    @Override // com.ju.lib.datacommunication.network.http.core.HttpStack.Dns
    public void failed(String str, InetAddress inetAddress) {
        if (!HttpLog.isHttpStackLogAble()) {
            DnsCache.Holder.INSTANCE.failed(str, inetAddress, 300L);
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("connect failed: ");
        stringBuffer.append(str);
        stringBuffer.append(" , ");
        stringBuffer.append(inetAddress);
        HttpLog.i(TAG, stringBuffer.toString());
    }

    @Override // com.ju.lib.datacommunication.network.http.dns.Tools.IGslbConfigListener
    public void onConfigChanged(Tools.ExtData extData) {
        HttpStack.HttpStackConfig httpStackConfig = this.mClient.getStack().getHttpStackConfig();
        long timeout = extData.getTimeout();
        if (httpStackConfig.getConnectTimeOut() != timeout || httpStackConfig.getReadTimeOut() != timeout) {
            this.mClient = new HiHttpClient(HttpDnsConfig.createDnsStack(timeout, null));
        }
        HTTP_DNS_ERROR_TIMEOUT = extData.getCoolTime();
    }

    @Override // com.ju.lib.datacommunication.network.http.core.HttpStack.Dns
    public HostInfo parse(String str) throws UnknownHostException {
        dnsLogI("Gslb parse, ", str);
        if (TextUtils.isEmpty(str)) {
            throw new UnknownHostException("hostname == null");
        }
        if (Tools.checkIP(str)) {
            dnsLogI(" parse ip: ", str);
            return systemDns(str);
        }
        synchronized (str.intern()) {
            if (DnsCache.Holder.INSTANCE.isUnsupportedHostname(str)) {
                dnsLogI("Unsupported: ", str);
                return systemDns(str);
            }
            HostInfo hostInfo = DnsCache.Holder.INSTANCE.get(str);
            if (hostInfo != null) {
                return hostInfo;
            }
            if (SystemClock.elapsedRealtime() - this.mHttpDnsErrorTime < HTTP_DNS_ERROR_TIMEOUT) {
                dnsLogI("Gslb error time! ");
                onException(str, "gslb frozen", HttpGslbException.Status.Frozen);
                return systemDns(str);
            }
            try {
                Result resultRequestGslb = requestGslb(str, GslbHostName.DOMAIN_GSLB_SERVER);
                HostInfo hostInfo2 = null;
                boolean z = false;
                for (HostInfo hostInfo3 : resultRequestGslb.list) {
                    if (hostInfo3 != null) {
                        if (GslbHostName.DOMAIN_GSLB_SERVER.equals(hostInfo3.getHostName())) {
                            if (!hostInfo3.getIps().contains(HttpDnsConfig.UNSUPPORTED_IP)) {
                                this.mGslbHostName.put(hostInfo3);
                            }
                        } else if (hostInfo3.getIps().contains(HttpDnsConfig.UNSUPPORTED_IP)) {
                            DnsCache.Holder.INSTANCE.addUnsupportedHostname(hostInfo3.getHostName());
                            if (str.equals(hostInfo3.getHostName())) {
                                z = true;
                            }
                        } else {
                            DnsCache.Holder.INSTANCE.put(hostInfo3);
                            if (str.equals(hostInfo3.getHostName())) {
                                onSuccess(resultRequestGslb.gslbHost, str, hostInfo3.getInetAddress());
                                hostInfo2 = hostInfo3;
                            }
                        }
                    }
                }
                if (hostInfo2 != null) {
                    return hostInfo2;
                }
                if (z) {
                    onException(str, "host name not supported", HttpGslbException.Status.ParsedListUnsupported);
                } else {
                    onException(str, "parsed list invalid", HttpGslbException.Status.ParsedListInvalid);
                }
                HttpLog.i(TAG, "System Dns! ");
                return systemDns(str);
            } catch (HttpGslbException e2) {
                if (HttpLog.isHttpStackLogAble()) {
                    HttpLog.w(TAG, e2, "requestGslb gslb error! ");
                }
                IGSLBListener iGSLBListener = this.mGSLBListener;
                if (iGSLBListener != null) {
                    iGSLBListener.onException(e2);
                }
                if (e2.getStatus() != HttpGslbException.Status.NetworkNotConnected) {
                    this.mHttpDnsErrorTime = SystemClock.elapsedRealtime();
                }
                return systemDns(str);
            }
        }
    }

    @Override // com.ju.lib.datacommunication.network.http.core.HttpStack.Dns
    public void resetCustomId(String str) {
        if (TextUtils.isEmpty(str) || str.equals(this.mCustomId)) {
            return;
        }
        this.mCustomId = str;
        resetGslbCacheAndErrorTime();
    }

    private void onException(HttpGslbException httpGslbException) {
        IGSLBListener iGSLBListener = this.mGSLBListener;
        if (iGSLBListener != null) {
            iGSLBListener.onException(httpGslbException);
        }
    }
}
