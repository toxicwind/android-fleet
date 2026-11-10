package com.ju.lib.datareport;

import b.a.a.a.a;
import com.ju.lib.datacommunication.network.http.HttpManager;
import com.ju.lib.datacommunication.network.http.core.HiRequest;
import com.ju.lib.datacommunication.network.http.core.HttpException;
import com.ju.lib.datacommunication.network.http.core.request.HiMultipartBody;
import com.ju.lib.datareport.dynamic.DynamicDomainHelper;
import com.ju.lib.datareport.dynamic.DynamicUrl;
import com.ju.lib.utils.log.LogUtil;
import e.b.b;
import e.b.d;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class ReportPoster {
    public static final String D_A_K = "CT4M92FPB76HLK3R";
    public static final String D_A_V = "0000000000000005";
    public static final String TAG = "ReportPoster";
    public DynamicUrl dynamicUrl;
    public final TransChain mChain;

    private static class TransChain {
        public TransChain mNext;

        public TransChain() {
        }

        public byte[] doTrans(byte[] bArr) {
            return bArr;
        }

        public final byte[] trans(byte[] bArr) {
            byte[] bArrDoTrans = doTrans(bArr);
            TransChain transChain = this.mNext;
            return transChain == null ? bArrDoTrans : transChain.trans(bArrDoTrans);
        }

        public TransChain(TransChain transChain) {
            this.mNext = transChain;
        }
    }

    public ReportPoster(Strategy strategy) {
        this.dynamicUrl = null;
        if (strategy.isCompress()) {
            if (strategy.isEncrypt()) {
                this.mChain = new ZipChain(new SecretChain());
            } else {
                this.mChain = new ZipChain();
            }
        } else if (strategy.isEncrypt()) {
            this.mChain = new SecretChain();
        } else {
            this.mChain = new TransChain();
        }
        this.dynamicUrl = DynamicDomainHelper.dynamicUrl;
        String str = TAG;
        StringBuilder sbA = a.a("dynamicUrl:");
        sbA.append(this.dynamicUrl);
        LogUtil.i(str, sbA.toString());
    }

    private String checkDynamicUrl(String str, int i) {
        URL url;
        String str2;
        try {
            url = new URL(str);
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
            url = null;
        }
        LogUtil.i(TAG, a.a("checkDynamicUrl type: ", i));
        if (i == 2) {
            str2 = this.dynamicUrl.getTer() + str.substring(url.getHost().length() + 9, str.length());
        } else if (i == 0) {
            str2 = this.dynamicUrl.getExc() + str.substring(url.getHost().length() + 9, str.length());
        } else {
            str2 = "";
        }
        LogUtil.i(TAG, a.a("checkDynamicUrl result: ", str2));
        return str2;
    }

    private void upload(String str, byte[] bArr) throws HttpException {
        if (bArr == null || bArr.length == 0) {
            LogUtil.e(TAG, "upload data length is 0");
            return;
        }
        String str2 = TAG;
        StringBuilder sbB = a.b("upload: ", str, ", length = ");
        sbB.append(bArr.length);
        LogUtil.i(str2, sbB.toString());
        int code = HttpManager.getHttpApi().put().body(new HiMultipartBody.Builder().setType("application/octet-stream").addPart(HiRequest.createBody("application/octet-stream", bArr)).build()).url(str).execute().getCode();
        LogUtil.i(TAG, str + " RESPONSE " + code);
        if (code != 200) {
            throw new HttpException(code, a.a("Response code: ", code));
        }
    }

    public void post(ReportExecutor.TypeInfo typeInfo, Collection<ReportBean> collection) {
        e.b.a aVar = new e.b.a();
        Iterator<ReportBean> it = collection.iterator();
        while (it.hasNext()) {
            try {
                aVar.f2161a.add(new d(it.next().getData()));
            } catch (b unused) {
            }
        }
        upload(typeInfo, this.mChain.trans(aVar.toString().getBytes(Charset.forName("UTF-8"))));
    }

    private static class SecretChain extends TransChain {
        public AES mSectet;

        public SecretChain() {
            this.mSectet = new AES("CT4M92FPB76HLK3R", "0000000000000005");
        }

        @Override // com.ju.lib.datareport.ReportPoster.TransChain
        public byte[] doTrans(byte[] bArr) {
            return this.mSectet.encrypt(bArr);
        }

        public SecretChain(TransChain transChain) {
            super(transChain);
            this.mSectet = new AES("CT4M92FPB76HLK3R", "0000000000000005");
        }
    }

    private static class ZipChain extends TransChain {
        public Zip mZip;

        public ZipChain() {
            this.mZip = new Zip();
        }

        @Override // com.ju.lib.datareport.ReportPoster.TransChain
        public byte[] doTrans(byte[] bArr) {
            return this.mZip.compress(bArr);
        }

        public ZipChain(TransChain transChain) {
            super(transChain);
            this.mZip = new Zip();
        }
    }

    private String checkDynamicUrl(ReportExecutor.TypeInfo typeInfo) {
        URL url;
        String str;
        String url2 = typeInfo.mStrategy.getUrl();
        try {
            url = new URL(typeInfo.mStrategy.getUrl());
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
            url = null;
        }
        String str2 = TAG;
        StringBuilder sbA = a.a("checkDynamicUrl type.mType: ");
        sbA.append(typeInfo.mType);
        LogUtil.i(str2, sbA.toString());
        int i = typeInfo.mType;
        if (i == 2) {
            str = this.dynamicUrl.getTer() + url2.substring(url.getHost().length() + 9, url2.length());
        } else if (i == 0) {
            str = this.dynamicUrl.getExc() + url2.substring(url.getHost().length() + 9, url2.length());
        } else {
            str = "";
        }
        LogUtil.i(TAG, a.a("checkDynamicUrl result: ", str));
        return str;
    }

    public void post(String str, Collection<ReportBean> collection) {
        e.b.a aVar = new e.b.a();
        Iterator<ReportBean> it = collection.iterator();
        while (it.hasNext()) {
            try {
                aVar.f2161a.add(new d(it.next().getData()));
            } catch (b unused) {
            }
        }
        upload(str, this.mChain.trans(aVar.toString().getBytes(Charset.forName("UTF-8"))));
    }

    private void upload(String str, byte[] bArr, int i) throws HttpException {
        if (bArr != null && bArr.length != 0) {
            String str2 = TAG;
            StringBuilder sbB = a.b("upload: ", str, ", length = ");
            sbB.append(bArr.length);
            LogUtil.i(str2, sbB.toString());
            DynamicUrl dynamicUrl = this.dynamicUrl;
            if (dynamicUrl != null && dynamicUrl.isValide()) {
                str = checkDynamicUrl(str, i);
            }
            int code = HttpManager.getHttpApi().put().body(new HiMultipartBody.Builder().setType("application/octet-stream").addPart(HiRequest.createBody("application/octet-stream", bArr)).build()).url(str).execute().getCode();
            LogUtil.i(TAG, str + " RESPONSE " + code);
            if (code != 200) {
                throw new HttpException(code, a.a("Response code: ", code));
            }
            return;
        }
        LogUtil.e(TAG, "upload data length is 0");
    }

    public void post(String str, Collection<ReportBean> collection, int i) {
        e.b.a aVar = new e.b.a();
        Iterator<ReportBean> it = collection.iterator();
        while (it.hasNext()) {
            try {
                aVar.f2161a.add(new d(it.next().getData()));
            } catch (b unused) {
            }
        }
        upload(str, this.mChain.trans(aVar.toString().getBytes(Charset.forName("UTF-8"))), i);
    }

    private void upload(ReportExecutor.TypeInfo typeInfo, byte[] bArr) throws HttpException {
        if (bArr != null && bArr.length != 0) {
            String str = TAG;
            StringBuilder sbA = a.a("upload: type.mType:");
            sbA.append(typeInfo.mType);
            LogUtil.i(str, sbA.toString());
            String str2 = TAG;
            StringBuilder sbA2 = a.a("upload: ");
            sbA2.append(typeInfo.mStrategy.getUrl());
            sbA2.append(", length = ");
            sbA2.append(bArr.length);
            LogUtil.i(str2, sbA2.toString());
            String url = typeInfo.mStrategy.getUrl();
            DynamicUrl dynamicUrl = this.dynamicUrl;
            if (dynamicUrl != null && dynamicUrl.isValide()) {
                url = checkDynamicUrl(typeInfo);
            }
            int code = HttpManager.getHttpApi().put().body(new HiMultipartBody.Builder().setType("application/octet-stream").addPart(HiRequest.createBody("application/octet-stream", bArr)).build()).url(url).execute().getCode();
            LogUtil.i(TAG, url + " RESPONSE " + code);
            if (code != 200) {
                throw new HttpException(code, a.a("Response code: ", code));
            }
            return;
        }
        LogUtil.e(TAG, "upload data length is 0");
    }
}
