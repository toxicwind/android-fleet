package com.ju.lib.datareport;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import b.a.a.a.a;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.hmct.cloud.sdk.utils.Params;
import com.ju.lib.datacommunication.network.http.HttpManager;
import com.ju.lib.datacommunication.network.http.address.AddressResponse;
import com.ju.lib.datacommunication.network.http.builder.GetBuilder;
import com.ju.lib.datacommunication.network.http.core.HiResponse;
import com.ju.lib.datareport.dynamic.DynamicConfigure;
import com.ju.lib.datareport.dynamic.DynamicDomainHelper;
import com.ju.lib.datareport.dynamic.DynamicUrl;
import com.ju.lib.utils.log.LogUtil;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* JADX INFO: loaded from: classes2.dex */
public class StrategyHelper {
    public static final String D_A_K = "D5B6D8584F94B432";
    public static final String D_A_V = "205681D89D731A8F";
    public static final String STRATEGY_PATH = "/log/get_dotexception_strategy";
    public static final String STRATEGY_PROTOCOL_HTTP = "http://";
    public static final String STRATEGY_PROTOCOL_HTTPS = "https://";
    public static final String STRATEGY_URL = "http://api-gps.hismarttv.com/log/get_dotexception_strategy";
    public static final String TAG = "StrategyHelper";
    public DynamicUrl dynamicUrl;
    public String mAppKey;
    public Context mContext;
    public DynamicConfigure mCustomConfigure;
    public String mDeviceId;
    public final String mStrategyUrl;
    public String mVersion;
    public AES aes = new AES("D5B6D8584F94B432", "205681D89D731A8F");
    public Zip zip = new Zip();
    public String strategy_url_request = null;

    public StrategyHelper(Context context, String str, String str2, String str3, String str4) {
        LogUtil.i(TAG, "getStrategy: " + str2 + ", " + str);
        this.mContext = context;
        this.mAppKey = str;
        this.mDeviceId = str2;
        this.mVersion = str3;
        if (TextUtils.isEmpty(str4)) {
            LogUtil.d(TAG, "strategyUrl is empty set default url;");
            this.mStrategyUrl = STRATEGY_URL;
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (!str4.startsWith("https://") && !str4.startsWith("http://")) {
            sb.append("http://");
        }
        this.mStrategyUrl = a.a(sb, str4, STRATEGY_PATH);
    }

    private void checkStrategyUrl() {
        DynamicUrl dynamicUrl;
        if (isDynamic() && (dynamicUrl = this.dynamicUrl) != null && dynamicUrl.getPolicy() != null && !this.dynamicUrl.getPolicy().isEmpty()) {
            this.strategy_url_request = this.dynamicUrl.getPolicy() + DynamicDomainHelper.DynamicPolicyUrlSuffix;
        } else if (!isDynamic()) {
            this.strategy_url_request = this.mStrategyUrl;
        } else if (getDynamicConfigure().getPlatformInfo() == 1) {
            this.strategy_url_request = "https://policy-jrnl.vidaahub.com/log/get_dotexception_strategy";
        } else if (getDynamicConfigure().getPlatformInfo() == 2) {
            this.strategy_url_request = "https://policy-jrnl.intsmarthub.com/log/get_dotexception_strategy";
        }
        String str = TAG;
        StringBuilder sbA = a.a("checkStrategyUrl strategy_url_request = ");
        sbA.append(this.strategy_url_request);
        LogUtil.i(str, sbA.toString());
    }

    private DynamicConfigure getDynamicConfigure() {
        DynamicConfigure dynamicConfigure = this.mCustomConfigure;
        if (dynamicConfigure != null) {
            return dynamicConfigure;
        }
        if (ReportManager.INSTANCE != null) {
            return ReportManager.INSTANCE.getmDynamicConfigure();
        }
        printException("ReportManager.getInstance() is null");
        return null;
    }

    private DynamicUrl getDynamicUrlFromServer() {
        String md5FromCache = DynamicDomainHelper.getMd5FromCache(DynamicDomainHelper.readDynamicDomainCache(this.mContext));
        LogUtil.i(TAG, a.a("getDynamicUrlFromServer cacheMd5:", md5FromCache));
        getDynamicConfigure().getDynamicRequestInfo().setMd5(md5FromCache);
        String dynamicDomainDistribution = DynamicDomainHelper.getDynamicDomainDistribution(this.mContext, getDynamicConfigure());
        if (DynamicDomainHelper.parseDynamicResponseType(this.mContext, dynamicDomainDistribution).getType() == 0) {
            DynamicDomainHelper.saveJsonToCache(this.mContext, dynamicDomainDistribution);
        }
        DynamicUrl dynamicResponse = DynamicDomainHelper.parseDynamicResponse(this.mContext, dynamicDomainDistribution);
        DynamicDomainHelper.setDynamicUrl(dynamicResponse);
        LogUtil.i(TAG, a.a("getDynamicUrlFromServer mDynamicUrl:", dynamicResponse));
        return dynamicResponse;
    }

    private Element getItem(NodeList nodeList, int i) {
        return (Element) nodeList.item(i);
    }

    private Element getNode(Element element, String str) {
        Node nodeItem = element.getElementsByTagName(str).item(0);
        if (nodeItem != null) {
            return (Element) nodeItem;
        }
        LogUtil.i(TAG, a.a("unless: ", str));
        return null;
    }

    private NodeList getNodeList(Element element, String str) {
        Node nodeItem = element.getElementsByTagName(str).item(0);
        if (nodeItem != null) {
            return nodeItem.getChildNodes();
        }
        LogUtil.i(TAG, a.a("unless: ", str));
        return null;
    }

    private String getNodeValue(Element element, String str) {
        Element node = getNode(element, str);
        if (node != null) {
            return node.getFirstChild().getNodeValue();
        }
        LogUtil.i(TAG, a.a("unless: ", str));
        return null;
    }

    private boolean getNodeValueBoolean(Element element, String str) {
        return getNodeValueInteger(element, str) != 0;
    }

    private int getNodeValueInteger(Element element, String str) {
        try {
            return Integer.parseInt(getNodeValue(element, str));
        } catch (Exception unused) {
            return 0;
        }
    }

    private long getNodeValueLong(Element element, String str) {
        try {
            return Long.parseLong(getNodeValue(element, str));
        } catch (Exception unused) {
            return 0L;
        }
    }

    private byte[] hexStringToByteArray(String str) {
        char[] charArray = str.toCharArray();
        byte[] bArr = new byte[str.length() / 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (Character.digit(charArray[i2 + 1], 16) | (Character.digit(charArray[i2], 16) << 4));
        }
        return bArr;
    }

    private boolean isDynamic() {
        return getDynamicConfigure() != null;
    }

    private Strategy parse(InputStream inputStream) throws Throwable {
        try {
            Element documentElement = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream).getDocumentElement();
            int nodeValueInteger = getNodeValueInteger(documentElement, AddressResponse.sResultCode);
            LogUtil.i(TAG, "resultCode = " + nodeValueInteger);
            if (nodeValueInteger != 0) {
                return null;
            }
            String nodeValue = getNodeValue(documentElement, "policyContent");
            LogUtil.i(TAG, "policyContent = " + nodeValue);
            byte[] bArrUncompress = this.zip.uncompress(Base64.decode(new String(this.aes.decrypt(hexStringToByteArray(nodeValue)), "UTF-8"), 0));
            Strategy content = parseContent(new ByteArrayInputStream(bArrUncompress));
            if (content != null) {
                LogUtil.i(TAG, "content: " + new String(bArrUncompress, "UTF-8"));
                ReportDatabase.getInstance(this.mContext).putStrategyBean(new StrategyBean(this.mAppKey, bArrUncompress, content.getPolicySeq()));
            }
            return content;
        } catch (Exception e2) {
            LogUtil.e(TAG, e2, "");
            return null;
        }
    }

    private Strategy parseContent(InputStream inputStream) {
        try {
            Element documentElement = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream).getDocumentElement();
            Strategy strategy = new Strategy();
            int nodeValueInteger = getNodeValueInteger(documentElement, AddressResponse.sResultCode);
            LogUtil.i(TAG, "resultCode = " + nodeValueInteger);
            if (nodeValueInteger != 0) {
                return null;
            }
            strategy.setResultCode(nodeValueInteger);
            strategy.setNetType(getNodeValueInteger(documentElement, "netType"));
            strategy.setPolicySeq(getNodeValueInteger(documentElement, "policySeq"));
            strategy.setEncrypt(getNodeValueBoolean(documentElement, "encrypt"));
            strategy.setCompress(getNodeValueBoolean(documentElement, "compress"));
            try {
                strategy.setBusinessStrategy(new Strategy.TypeStrategy(getNodeValueBoolean(documentElement, "serStatus"), getNodeValue(documentElement, "serAddress"), getNodeValueLong(documentElement, "serduration"), getNodeValueInteger(documentElement, "serfileSize")));
            } catch (Exception unused) {
                strategy.setBusinessStrategy(Strategy.CloseTypeStrategy.INSTANCE);
            }
            try {
                strategy.setDebugStrategy(new Strategy.TypeStrategy(getNodeValueBoolean(documentElement, "dotStatus"), getNodeValue(documentElement, "dotAddress"), getNodeValueLong(documentElement, "dotduration"), getNodeValueInteger(documentElement, "dotfileSize")));
            } catch (Exception unused2) {
                strategy.setDebugStrategy(Strategy.CloseTypeStrategy.INSTANCE);
            }
            try {
                strategy.setExceptionStrategy(new Strategy.TypeStrategy(getNodeValueBoolean(documentElement, "excStatus"), getNodeValue(documentElement, "excAddress"), getNodeValueLong(documentElement, "excduration"), getNodeValueInteger(documentElement, "excfileSize")));
            } catch (Exception unused3) {
                strategy.setExceptionStrategy(Strategy.CloseTypeStrategy.INSTANCE);
            }
            NodeList nodeList = getNodeList(documentElement, "dotExcTagList");
            HashSet hashSet = new HashSet(nodeList != null ? nodeList.getLength() : 0);
            String str = TAG;
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("count = ");
            sb.append(nodeList);
            objArr[0] = sb.toString() != null ? Integer.valueOf(nodeList.getLength()) : "null";
            LogUtil.i(str, objArr);
            int length = nodeList.getLength();
            for (int i = 0; i < length; i++) {
                Element item = getItem(nodeList, i);
                if (getNodeValueBoolean(item, "eventStatus")) {
                    hashSet.add(getNodeValue(item, "eventCode"));
                }
            }
            strategy.setTags(hashSet);
            Element node = getNode(documentElement, "paramsTagList");
            if (node != null) {
                NodeList childNodes = node.getChildNodes();
                int length2 = childNodes.getLength();
                HashMap map = new HashMap(length2);
                for (int i2 = 0; i2 < length2; i2++) {
                    Element item2 = getItem(childNodes, i2);
                    if (item2.getChildNodes().getLength() == 0) {
                        map.put(item2.getNodeName(), "");
                    } else {
                        map.put(item2.getNodeName(), item2.getChildNodes().item(0).getNodeValue());
                    }
                }
                strategy.setExt(map);
            } else {
                strategy.setExt(Collections.emptyMap());
            }
            LogUtil.i(TAG, "BEAN: " + strategy);
            return strategy;
        } catch (Exception e2) {
            LogUtil.e(TAG, e2, "parseContent error! ");
            return null;
        }
    }

    private void printException(String str) {
        new Exception(str).printStackTrace();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Strategy requestServer(StrategyBean strategyBean) throws Throwable {
        Throwable th;
        InputStream inputStream;
        Exception e2;
        Uri uriBuild;
        LogUtil.i(TAG, a.a("last = ", strategyBean));
        checkStrategyUrl();
        try {
            if (isDynamic()) {
                uriBuild = Uri.parse(this.strategy_url_request).buildUpon().appendQueryParameter("version", "7.3").appendQueryParameter("policySeq", strategyBean == null ? "0" : String.valueOf(strategyBean.getPolicy())).appendQueryParameter(Params.DEVICEID, this.mDeviceId).appendQueryParameter(Params.APPKEY, this.mAppKey).appendQueryParameter("reportTag", "1").appendQueryParameter("receiveTag", "0").appendQueryParameter(RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, getDynamicConfigure().getDynamicRequestInfo().getAreacode()).appendQueryParameter("brandCode", getDynamicConfigure().getDynamicRequestInfo().getBrand()).build();
            } else {
                uriBuild = Uri.parse(this.strategy_url_request).buildUpon().appendQueryParameter("version", "7.3").appendQueryParameter("policySeq", strategyBean == null ? "0" : String.valueOf(strategyBean.getPolicy())).appendQueryParameter(Params.DEVICEID, this.mDeviceId).appendQueryParameter(Params.APPKEY, this.mAppKey).appendQueryParameter("reportTag", "1").appendQueryParameter("receiveTag", "0").appendQueryParameter("appVersion", this.mVersion).build();
            }
            String string = uriBuild.toString();
            LogUtil.i(TAG, "request strategy: " + string);
            HiResponse hiResponseExecute = ((GetBuilder) HttpManager.getHttpApi().get().url(string)).execute();
            int code = hiResponseExecute.getCode();
            LogUtil.i(TAG, "request strategy responseCode = " + code);
            if (code != 200) {
                Util.close(null);
                return null;
            }
            inputStream = hiResponseExecute.getBody().getInputStream();
            try {
                try {
                    Strategy strategy = parse(inputStream);
                    Util.close(inputStream);
                    return strategy;
                } catch (Exception e3) {
                    e2 = e3;
                    LogUtil.e(TAG, e2, "requestServer strategy error! ");
                    Util.close(inputStream);
                    return null;
                }
            } catch (Throwable th2) {
                th = th2;
                Util.close(inputStream);
                throw th;
            }
        } catch (Exception e4) {
            e2 = e4;
            inputStream = null;
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
            Util.close(inputStream);
            throw th;
        }
        LogUtil.e(TAG, e2, "requestServer strategy error! ");
        Util.close(inputStream);
        return null;
    }

    public Strategy getStrategy() {
        StrategyBean strategyBean = ReportDatabase.getInstance(this.mContext).getStrategyBean(this.mAppKey);
        if (isDynamic()) {
            this.dynamicUrl = getDynamicUrlFromServer();
        }
        Strategy strategyRequestServer = requestServer(strategyBean);
        if (strategyRequestServer != null) {
            return strategyRequestServer;
        }
        if (strategyBean == null) {
            return null;
        }
        try {
            return parseContent(new ByteArrayInputStream(strategyBean.getContent()));
        } catch (Exception unused) {
            return null;
        }
    }

    public StrategyHelper setCustomConfigure(DynamicConfigure dynamicConfigure) {
        this.mCustomConfigure = dynamicConfigure;
        return this;
    }
}
