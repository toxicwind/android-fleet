package com.google.android.exoplayer.upstream;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.Predicate;
import com.google.android.exoplayer.util.Util;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;
import com.mediatek.twoworlds.tv.common.MtkTvConfigTypeBase;
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.NoRouteToHostException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: classes.dex */
public class DefaultHttpDataSource implements HttpDataSource {
    private static final Pattern CONTENT_RANGE_HEADER = Pattern.compile("^bytes (\\d+)-(\\d+)/(\\d+)$");
    private static final AtomicReference<byte[]> skipBufferReference = new AtomicReference<>();
    private final boolean allowCrossProtocolRedirects;
    private long bytesRead;
    private long bytesSkipped;
    private long bytesToRead;
    private long bytesToSkip;
    private Callback callback;
    private final int connectTimeoutMillis;
    private HttpURLConnection connection;
    private final Predicate<String> contentTypePredicate;
    private DataSpec dataSpec;
    private HttpsDelegate httpsDelegate;
    private InputStream inputStream;
    private String keyPath;
    private String keyStorePath;
    private final TransferListener listener;
    private boolean opened;
    private String password;
    private final int readTimeoutMillis;
    private final HashMap<String, String> requestProperties;
    private final String userAgent;

    public interface Callback {
        String getCookie(String str);

        String getSSLKeyPath();

        String getSSLKeyStorePath();

        String getSSLPassword();

        void setCookie(String str, String[] strArr);
    }

    public DefaultHttpDataSource(String str, Predicate<String> predicate, TransferListener transferListener, int i, int i2, boolean z) {
        this.httpsDelegate = new HttpsDelegate(this);
        Assertions.checkNotEmpty(str);
        this.userAgent = str;
        this.contentTypePredicate = predicate;
        this.listener = transferListener;
        this.requestProperties = new HashMap<>();
        this.connectTimeoutMillis = i;
        this.readTimeoutMillis = i2;
        this.allowCrossProtocolRedirects = z;
        this.password = "";
        this.keyStorePath = "";
        this.keyPath = "";
    }

    @Override // com.google.android.exoplayer.upstream.UriDataSource
    public String getUri() {
        HttpURLConnection httpURLConnection = this.connection;
        if (httpURLConnection == null) {
            return null;
        }
        return httpURLConnection.getURL().toString();
    }

    public void setRequestProperty(String str, String str2) {
        Assertions.checkNotNull(str);
        Assertions.checkNotNull(str2);
        synchronized (this.requestProperties) {
            this.requestProperties.put(str, str2);
        }
    }

    @Override // com.google.android.exoplayer.upstream.DataSource
    public long open(DataSpec dataSpec) throws HttpDataSource.HttpDataSourceException {
        List<String> list;
        Callback callback;
        this.dataSpec = dataSpec;
        long j = 0;
        this.bytesRead = 0L;
        this.bytesSkipped = 0L;
        try {
            if (isSafeConnection(dataSpec.uri.toString())) {
                Log.d("DefaultHttpDataSource", "Using SSL connection");
                this.connection = makeHttpsConnection(dataSpec);
            } else {
                this.connection = makeConnection(dataSpec);
            }
            try {
                int responseCode = this.connection.getResponseCode();
                Log.d("DefaultHttpDataSource", "responseCode[" + responseCode + "]");
                if (responseCode < 200 || responseCode > 299) {
                    Map<String, List<String>> headerFields = this.connection.getHeaderFields();
                    closeConnectionQuietly();
                    throw new HttpDataSource.InvalidResponseCodeException(responseCode, headerFields, dataSpec);
                }
                String contentType = this.connection.getContentType();
                Predicate<String> predicate = this.contentTypePredicate;
                if (predicate != null && !predicate.evaluate(contentType)) {
                    closeConnectionQuietly();
                    throw new HttpDataSource.InvalidContentTypeException(contentType, dataSpec);
                }
                if (responseCode == 200) {
                    long j2 = dataSpec.position;
                    if (j2 != 0) {
                        j = j2;
                    }
                }
                this.bytesToSkip = j;
                if ((dataSpec.flags & 1) == 0) {
                    long contentLength = getContentLength(this.connection);
                    long j3 = dataSpec.length;
                    if (j3 == -1) {
                        j3 = contentLength != -1 ? contentLength - this.bytesToSkip : -1L;
                    }
                    this.bytesToRead = j3;
                } else {
                    this.bytesToRead = dataSpec.length;
                }
                try {
                    this.inputStream = this.connection.getInputStream();
                    this.opened = true;
                    TransferListener transferListener = this.listener;
                    if (transferListener != null) {
                        transferListener.onTransferStart();
                    }
                    Map<String, List<String>> headerFields2 = this.connection.getHeaderFields();
                    if (headerFields2 != null && (list = headerFields2.get("Set-Cookie")) != null && (callback = this.callback) != null) {
                        callback.setCookie(dataSpec.uri.toString(), (String[]) list.toArray(new String[0]));
                    }
                    return this.bytesToRead;
                } catch (IOException e) {
                    closeConnectionQuietly();
                    throw new HttpDataSource.HttpDataSourceException(e, dataSpec, 1);
                }
            } catch (IOException e2) {
                closeConnectionQuietly();
                throw new HttpDataSource.HttpDataSourceException("Unable to connect to " + dataSpec.uri.toString(), e2, dataSpec, 1);
            }
        } catch (IOException e3) {
            throw new HttpDataSource.HttpDataSourceException("Unable to connect to " + dataSpec.uri.toString(), e3, dataSpec, 1);
        }
    }

    @Override // com.google.android.exoplayer.upstream.DataSource
    public int read(byte[] bArr, int i, int i2) throws HttpDataSource.HttpDataSourceException {
        try {
            skipInternal();
            return readInternal(bArr, i, i2);
        } catch (IOException e) {
            throw new HttpDataSource.HttpDataSourceException(e, this.dataSpec, 2);
        }
    }

    @Override // com.google.android.exoplayer.upstream.DataSource
    public void close() throws HttpDataSource.HttpDataSourceException {
        try {
            if (this.inputStream != null) {
                Util.maybeTerminateInputStream(this.connection, bytesRemaining());
                try {
                    this.inputStream.close();
                } catch (IOException e) {
                    throw new HttpDataSource.HttpDataSourceException(e, this.dataSpec, 3);
                }
            }
            this.inputStream = null;
            closeConnectionQuietly();
            if (this.opened) {
                this.opened = false;
                TransferListener transferListener = this.listener;
                if (transferListener != null) {
                    transferListener.onTransferEnd();
                }
            }
        } catch (Throwable th) {
            this.inputStream = null;
            closeConnectionQuietly();
            if (this.opened) {
                this.opened = false;
                TransferListener transferListener2 = this.listener;
                if (transferListener2 != null) {
                    transferListener2.onTransferEnd();
                }
            }
            throw th;
        }
    }

    protected final long bytesRemaining() {
        long j = this.bytesToRead;
        return j == -1 ? j : j - this.bytesRead;
    }

    private HttpURLConnection makeConnection(DataSpec dataSpec) throws IOException {
        URL url = new URL(dataSpec.uri.toString().replace(" ", "%20"));
        byte[] bArr = dataSpec.postBody;
        Log.d("DefaultHttpDataSource", "URL[" + url.toString() + "]");
        long j = dataSpec.position;
        long j2 = dataSpec.length;
        int i = 0;
        boolean z = (dataSpec.flags & 1) != 0;
        if (!this.allowCrossProtocolRedirects) {
            return makeConnection(url, bArr, j, j2, z, true);
        }
        while (true) {
            int i2 = i + 1;
            if (i <= 20) {
                HttpURLConnection httpURLConnectionMakeConnection = makeConnection(url, bArr, j, j2, z, false);
                int responseCode = httpURLConnectionMakeConnection.getResponseCode();
                if (responseCode != 300 && responseCode != 301 && responseCode != 302 && responseCode != 303 && (bArr != null || (responseCode != 307 && responseCode != 308))) {
                    return httpURLConnectionMakeConnection;
                }
                bArr = null;
                String headerField = httpURLConnectionMakeConnection.getHeaderField("Location");
                httpURLConnectionMakeConnection.disconnect();
                url = handleRedirect(url, headerField);
                i = i2;
            } else {
                throw new NoRouteToHostException("Too many redirects: " + i2);
            }
        }
    }

    private HttpURLConnection makeConnection(URL url, byte[] bArr, long j, long j2, boolean z, boolean z2) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(this.connectTimeoutMillis);
        httpURLConnection.setReadTimeout(this.readTimeoutMillis);
        Callback callback = this.callback;
        if (callback != null) {
            String cookie = callback.getCookie(this.dataSpec.uri.toString());
            if (!TextUtils.isEmpty(cookie)) {
                setRequestProperty("Cookie", cookie);
            }
        }
        synchronized (this.requestProperties) {
            for (Map.Entry<String, String> entry : this.requestProperties.entrySet()) {
                httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        if (j != 0 || j2 != -1) {
            String str = "bytes=" + j + MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING;
            if (j2 != -1) {
                str = str + ((j + j2) - 1);
            }
            httpURLConnection.setRequestProperty("Range", str);
        }
        httpURLConnection.setRequestProperty("User-Agent", this.userAgent);
        if (!z) {
            httpURLConnection.setRequestProperty("Accept-Encoding", "identity");
        }
        httpURLConnection.setInstanceFollowRedirects(z2);
        httpURLConnection.setDoOutput(bArr != null);
        if (bArr != null) {
            httpURLConnection.setRequestMethod("POST");
            if (bArr.length == 0) {
                httpURLConnection.connect();
            } else {
                httpURLConnection.setFixedLengthStreamingMode(bArr.length);
                httpURLConnection.connect();
                OutputStream outputStream = httpURLConnection.getOutputStream();
                try {
                    outputStream.write(bArr);
                } catch (IOException unused) {
                    Log.e("DefaultHttpDataSource", "OutputStream write fail!");
                }
                outputStream.close();
            }
        } else {
            httpURLConnection.connect();
        }
        return httpURLConnection;
    }

    private static URL handleRedirect(URL url, String str) throws IOException {
        if (str == null) {
            throw new ProtocolException("Null location redirect");
        }
        URL url2 = new URL(url, str);
        String protocol = url2.getProtocol();
        if ("https".equals(protocol) || "http".equals(protocol)) {
            return url2;
        }
        throw new ProtocolException("Unsupported protocol redirect: " + protocol);
    }

    private static long getContentLength(HttpURLConnection httpURLConnection) {
        long j;
        String headerField = httpURLConnection.getHeaderField("Content-Length");
        if (TextUtils.isEmpty(headerField)) {
            j = -1;
        } else {
            try {
                j = Long.parseLong(headerField);
            } catch (NumberFormatException unused) {
                Log.e("DefaultHttpDataSource", "Unexpected Content-Length [" + headerField + "]");
                j = -1;
            }
        }
        String headerField2 = httpURLConnection.getHeaderField("Content-Range");
        if (TextUtils.isEmpty(headerField2)) {
            return j;
        }
        Matcher matcher = CONTENT_RANGE_HEADER.matcher(headerField2);
        if (!matcher.find()) {
            return j;
        }
        try {
            long j2 = (Long.parseLong(matcher.group(2)) - Long.parseLong(matcher.group(1))) + 1;
            if (j < 0) {
                return j2;
            }
            if (j == j2) {
                return j;
            }
            Log.w("DefaultHttpDataSource", "Inconsistent headers [" + headerField + "] [" + headerField2 + "]");
            return Math.max(j, j2);
        } catch (NumberFormatException unused2) {
            Log.e("DefaultHttpDataSource", "Unexpected Content-Range [" + headerField2 + "]");
            return j;
        }
    }

    private void skipInternal() throws IOException {
        if (this.bytesSkipped == this.bytesToSkip) {
            return;
        }
        byte[] andSet = skipBufferReference.getAndSet(null);
        if (andSet == null) {
            andSet = new byte[4096];
        }
        while (true) {
            long j = this.bytesSkipped;
            long j2 = this.bytesToSkip;
            if (j != j2) {
                int i = this.inputStream.read(andSet, 0, (int) Math.min(j2 - j, andSet.length));
                if (Thread.interrupted()) {
                    throw new InterruptedIOException();
                }
                if (i == -1) {
                    throw new EOFException();
                }
                this.bytesSkipped += (long) i;
                TransferListener transferListener = this.listener;
                if (transferListener != null) {
                    transferListener.onBytesTransferred(i);
                }
            } else {
                skipBufferReference.set(andSet);
                return;
            }
        }
    }

    private int readInternal(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        int i3 = this.inputStream.read(bArr, i, i2);
        if (i3 == -1) {
            long j = this.bytesToRead;
            if (j == -1 || j == this.bytesRead) {
                return -1;
            }
            throw new EOFException();
        }
        this.bytesRead += (long) i3;
        TransferListener transferListener = this.listener;
        if (transferListener != null) {
            transferListener.onBytesTransferred(i3);
        }
        return i3;
    }

    private void closeConnectionQuietly() {
        HttpURLConnection httpURLConnection = this.connection;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception e) {
                Log.e("DefaultHttpDataSource", "Unexpected error while disconnecting", e);
            }
            this.connection = null;
        }
    }

    public DefaultHttpDataSource(String str, Predicate<String> predicate, TransferListener transferListener, int i, int i2, boolean z, Callback callback) {
        this(str, predicate, transferListener, i, i2, z);
        this.callback = callback;
    }

    public class HttpsDelegate {
        public HttpsDelegate(DefaultHttpDataSource defaultHttpDataSource) {
        }

        public TrustManager[] prepareTrustManager(InputStream inputStream) {
            if (inputStream == null) {
                return null;
            }
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                keyStore.load(null);
                keyStore.setCertificateEntry("trust", certificateFactory.generateCertificate(inputStream));
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init(keyStore);
                return trustManagerFactory.getTrustManagers();
            } catch (KeyStoreException e) {
                e.printStackTrace();
                Log.e("DefaultHttpDataSource", "return null");
                return null;
            } catch (NoSuchAlgorithmException e2) {
                e2.printStackTrace();
                Log.e("DefaultHttpDataSource", "return null");
                return null;
            } catch (CertificateException e3) {
                e3.printStackTrace();
                Log.e("DefaultHttpDataSource", "return null");
                return null;
            } catch (Exception e4) {
                e4.printStackTrace();
                Log.e("DefaultHttpDataSource", "return null");
                return null;
            }
        }

        public KeyManager[] prepareKeyManager(String str, String str2) {
            try {
                try {
                    if (!str.isEmpty() && !str2.isEmpty()) {
                        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(str));
                        KeyStore keyStore = KeyStore.getInstance("BKS");
                        try {
                            keyStore.load(bufferedInputStream, str2.toCharArray());
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (CertificateException e2) {
                            e2.printStackTrace();
                        }
                        bufferedInputStream.close();
                        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                        keyManagerFactory.init(keyStore, str2.toCharArray());
                        return keyManagerFactory.getKeyManagers();
                    }
                    return null;
                } catch (IOException e3) {
                    e3.printStackTrace();
                    return null;
                }
            } catch (KeyStoreException e4) {
                e4.printStackTrace();
                return null;
            } catch (NoSuchAlgorithmException e5) {
                e5.printStackTrace();
                return null;
            } catch (UnrecoverableKeyException e6) {
                e6.printStackTrace();
                return null;
            } catch (Exception e7) {
                e7.printStackTrace();
                return null;
            }
        }

        public void setCertificates(HttpsURLConnection httpsURLConnection, String str, String str2, String str3) throws IOException {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(str));
            try {
                try {
                    try {
                        TrustManager[] trustManagerArrPrepareTrustManager = prepareTrustManager(bufferedInputStream);
                        KeyManager[] keyManagerArrPrepareKeyManager = prepareKeyManager(str2, str3);
                        SSLContext sSLContext = SSLContext.getInstance(MtkTvConfigTypeBase.S3166_CFG_COUNT_TLS);
                        sSLContext.init(keyManagerArrPrepareKeyManager, new TrustManager[]{new MyTrustManager(this, chooseTrustManager(trustManagerArrPrepareTrustManager))}, new SecureRandom());
                        sSLContext.init(keyManagerArrPrepareKeyManager, new TrustManager[]{new MyTrustManager(this, chooseTrustManager(trustManagerArrPrepareTrustManager))}, new SecureRandom());
                        httpsURLConnection.setSSLSocketFactory(sSLContext.getSocketFactory());
                    } catch (KeyStoreException e) {
                        e.printStackTrace();
                    }
                } catch (KeyManagementException e2) {
                    e2.printStackTrace();
                } catch (NoSuchAlgorithmException e3) {
                    e3.printStackTrace();
                }
            } finally {
                bufferedInputStream.close();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public X509TrustManager chooseTrustManager(TrustManager[] trustManagerArr) {
            if (trustManagerArr == null) {
                return null;
            }
            for (TrustManager trustManager : trustManagerArr) {
                if (trustManager instanceof X509TrustManager) {
                    return (X509TrustManager) trustManager;
                }
            }
            return null;
        }

        public class MyTrustManager implements X509TrustManager {
            private X509TrustManager defaultTrustManager;
            private X509TrustManager localTrustManager;

            @Override // javax.net.ssl.X509TrustManager
            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }

            @Override // javax.net.ssl.X509TrustManager
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            public MyTrustManager(HttpsDelegate httpsDelegate, X509TrustManager x509TrustManager) throws NoSuchAlgorithmException, KeyStoreException {
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init((KeyStore) null);
                this.defaultTrustManager = httpsDelegate.chooseTrustManager(trustManagerFactory.getTrustManagers());
                this.localTrustManager = x509TrustManager;
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                try {
                    this.defaultTrustManager.checkServerTrusted(x509CertificateArr, str);
                } catch (CertificateException unused) {
                    this.localTrustManager.checkServerTrusted(x509CertificateArr, str);
                }
            }
        }
    }

    private HttpsURLConnection makeHttpsConnection(URL url, byte[] bArr, long j, long j2, boolean z, boolean z2) throws IOException {
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
        httpsURLConnection.setConnectTimeout(this.connectTimeoutMillis);
        httpsURLConnection.setReadTimeout(this.readTimeoutMillis);
        Callback callback = this.callback;
        if (callback != null) {
            String cookie = callback.getCookie(this.dataSpec.uri.toString());
            if (!TextUtils.isEmpty(cookie)) {
                Log.d("DefaultHttpDataSource", "getCookie:URL[" + this.dataSpec.uri.toString() + "], cookie[" + cookie.toString());
                setRequestProperty("Cookie", cookie);
            }
            try {
                this.httpsDelegate.setCertificates(httpsURLConnection, this.keyStorePath, this.keyPath, this.password);
            } catch (Exception e) {
                Log.d("DefaultHttpDataSource", "Init SSL failed.");
                e.printStackTrace();
                return null;
            }
        }
        synchronized (this.requestProperties) {
            for (Map.Entry<String, String> entry : this.requestProperties.entrySet()) {
                httpsURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        if (j != 0 || j2 != -1) {
            String str = "bytes=" + j + MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING;
            if (j2 != -1) {
                str = str + ((j + j2) - 1);
            }
            httpsURLConnection.setRequestProperty("Range", str);
        }
        httpsURLConnection.setRequestProperty("User-Agent", this.userAgent);
        if (!z) {
            httpsURLConnection.setRequestProperty("Accept-Encoding", "identity");
        }
        httpsURLConnection.setInstanceFollowRedirects(z2);
        httpsURLConnection.setDoOutput(bArr != null);
        if (bArr != null) {
            httpsURLConnection.setFixedLengthStreamingMode(bArr.length);
            httpsURLConnection.connect();
            OutputStream outputStream = httpsURLConnection.getOutputStream();
            try {
                outputStream.write(bArr);
            } catch (IOException unused) {
                Log.e("DefaultHttpDataSource", "OutputStream write fail!");
            }
            outputStream.close();
        } else {
            httpsURLConnection.connect();
        }
        return httpsURLConnection;
    }

    private HttpsURLConnection makeHttpsConnection(DataSpec dataSpec) throws IOException {
        URL url = new URL(dataSpec.uri.toString().replace(" ", "%20"));
        byte[] bArr = dataSpec.postBody;
        Log.d("DefaultHttpDataSource", "URL[" + url.toString() + "]");
        long j = dataSpec.position;
        long j2 = dataSpec.length;
        int i = 0;
        boolean z = (dataSpec.flags & 1) != 0;
        if (!this.allowCrossProtocolRedirects) {
            return makeHttpsConnection(url, bArr, j, j2, z, true);
        }
        while (true) {
            int i2 = i + 1;
            if (i <= 20) {
                long j3 = j2;
                HttpsURLConnection httpsURLConnectionMakeHttpsConnection = makeHttpsConnection(url, bArr, j, j2, z, false);
                if (httpsURLConnectionMakeHttpsConnection == null) {
                    Log.e("DefaultHttpDataSource", "get connection failed.");
                    throw new IOException();
                }
                int responseCode = httpsURLConnectionMakeHttpsConnection.getResponseCode();
                if (responseCode != 300 && responseCode != 301 && responseCode != 302 && responseCode != 303 && (bArr != null || (responseCode != 307 && responseCode != 308))) {
                    return httpsURLConnectionMakeHttpsConnection;
                }
                bArr = null;
                String headerField = httpsURLConnectionMakeHttpsConnection.getHeaderField("Location");
                httpsURLConnectionMakeHttpsConnection.disconnect();
                url = handleRedirect(url, headerField);
                i = i2;
                j2 = j3;
            } else {
                throw new NoRouteToHostException("Too many redirects: " + i2);
            }
        }
    }

    private boolean isSafeConnection(String str) {
        Callback callback;
        if (!str.startsWith("https://") || (callback = this.callback) == null) {
            return false;
        }
        this.password = callback.getSSLPassword();
        this.keyStorePath = this.callback.getSSLKeyStorePath();
        this.keyPath = this.callback.getSSLKeyPath();
        if (!this.password.isEmpty() && !this.keyStorePath.isEmpty() && !this.keyPath.isEmpty()) {
            return fileExist(this.keyStorePath) && fileExist(this.keyPath);
        }
        Log.e("DefaultHttpDataSource", "password or keyStorePath is empty.");
        return false;
    }

    private boolean fileExist(String str) {
        try {
            return new File(str).exists();
        } catch (Exception unused) {
            return false;
        }
    }
}
