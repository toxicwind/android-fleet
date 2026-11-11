package androidx.media3.datasource.cronet;

import android.net.Uri;
import android.text.TextUtils;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.ConditionVariable;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.BaseDataSource;
import androidx.media3.datasource.DataSourceException;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.DefaultHttpDataSource;
import androidx.media3.datasource.HttpDataSource;
import androidx.media3.datasource.HttpUtil;
import androidx.media3.datasource.TransferListener;
import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.primitives.Longs;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.chromium.net.CronetEngine;
import org.chromium.net.CronetException;
import org.chromium.net.NetworkException;
import org.chromium.net.UrlRequest;
import org.chromium.net.UrlResponseInfo;

/* JADX INFO: loaded from: classes4.dex */
public class CronetDataSource extends BaseDataSource implements HttpDataSource {
    public static final int DEFAULT_CONNECT_TIMEOUT_MILLIS = 8000;
    private static final int DEFAULT_READ_BUFFER_SIZE_BYTES = 32768;
    public static final int DEFAULT_READ_TIMEOUT_MILLIS = 8000;
    private static final String TAG = "CronetDataSource";
    private long bytesRemaining;
    private final Clock clock;
    private final int connectTimeoutMs;
    private final Predicate<String> contentTypePredicate;
    private final CronetEngine cronetEngine;
    private volatile long currentConnectTimeoutMs;
    private DataSpec currentDataSpec;
    private UrlRequest currentUrlRequest;
    UrlRequestCallback currentUrlRequestCallback;
    private final HttpDataSource.RequestProperties defaultRequestProperties;
    private IOException exception;
    private final Executor executor;
    private boolean finished;
    private final boolean handleSetCookieRequests;
    private final boolean keepPostFor302Redirects;
    private final ConditionVariable operation;
    private ByteBuffer readBuffer;
    private final int readBufferSize;
    private final int readTimeoutMs;
    private final int requestPriority;
    private final HttpDataSource.RequestProperties requestProperties;
    private final boolean resetTimeoutOnRedirects;
    private UrlResponseInfo responseInfo;
    private boolean transferStarted;
    private final String userAgent;

    static {
        MediaLibraryInfo.registerModule("media3.datasource.cronet");
    }

    public static final class Factory implements HttpDataSource.Factory {
        private int connectTimeoutMs;
        private Predicate<String> contentTypePredicate;
        private final CronetEngine cronetEngine;
        private final HttpDataSource.RequestProperties defaultRequestProperties;
        private final Executor executor;
        private HttpDataSource.Factory fallbackFactory;
        private boolean handleSetCookieRequests;
        private final DefaultHttpDataSource.Factory internalFallbackFactory;
        private boolean keepPostFor302Redirects;
        private int readBufferSize;
        private int readTimeoutMs;
        private int requestPriority;
        private boolean resetTimeoutOnRedirects;
        private TransferListener transferListener;
        private String userAgent;

        @Override // androidx.media3.datasource.HttpDataSource.Factory
        public /* bridge */ /* synthetic */ HttpDataSource.Factory setDefaultRequestProperties(Map map) {
            return setDefaultRequestProperties((Map<String, String>) map);
        }

        public Factory(CronetEngine cronetEngine, Executor executor) {
            this.cronetEngine = (CronetEngine) Preconditions.checkNotNull(cronetEngine);
            this.executor = executor;
            this.defaultRequestProperties = new HttpDataSource.RequestProperties();
            this.internalFallbackFactory = null;
            this.requestPriority = 3;
            this.connectTimeoutMs = 8000;
            this.readTimeoutMs = 8000;
            this.readBufferSize = 32768;
        }

        @Deprecated
        public Factory(CronetEngineWrapper cronetEngineWrapper, Executor executor) {
            this.cronetEngine = cronetEngineWrapper.getCronetEngine();
            this.executor = executor;
            this.defaultRequestProperties = new HttpDataSource.RequestProperties();
            this.internalFallbackFactory = new DefaultHttpDataSource.Factory();
            this.connectTimeoutMs = 8000;
            this.readTimeoutMs = 8000;
            this.readBufferSize = 32768;
        }

        @Override // androidx.media3.datasource.HttpDataSource.Factory
        public final Factory setDefaultRequestProperties(Map<String, String> map) {
            this.defaultRequestProperties.clearAndSet(map);
            DefaultHttpDataSource.Factory factory = this.internalFallbackFactory;
            if (factory != null) {
                factory.setDefaultRequestProperties(map);
            }
            return this;
        }

        public Factory setUserAgent(String str) {
            this.userAgent = str;
            DefaultHttpDataSource.Factory factory = this.internalFallbackFactory;
            if (factory != null) {
                factory.setUserAgent(str);
            }
            return this;
        }

        public Factory setRequestPriority(int i) {
            this.requestPriority = i;
            return this;
        }

        public Factory setConnectionTimeoutMs(int i) {
            this.connectTimeoutMs = i;
            DefaultHttpDataSource.Factory factory = this.internalFallbackFactory;
            if (factory != null) {
                factory.setConnectTimeoutMs(i);
            }
            return this;
        }

        public Factory setResetTimeoutOnRedirects(boolean z) {
            this.resetTimeoutOnRedirects = z;
            return this;
        }

        public Factory setHandleSetCookieRequests(boolean z) {
            this.handleSetCookieRequests = z;
            return this;
        }

        public Factory setReadTimeoutMs(int i) {
            this.readTimeoutMs = i;
            DefaultHttpDataSource.Factory factory = this.internalFallbackFactory;
            if (factory != null) {
                factory.setReadTimeoutMs(i);
            }
            return this;
        }

        public Factory setContentTypePredicate(Predicate<String> predicate) {
            this.contentTypePredicate = predicate;
            DefaultHttpDataSource.Factory factory = this.internalFallbackFactory;
            if (factory != null) {
                factory.setContentTypePredicate(predicate);
            }
            return this;
        }

        public Factory setKeepPostFor302Redirects(boolean z) {
            this.keepPostFor302Redirects = z;
            DefaultHttpDataSource.Factory factory = this.internalFallbackFactory;
            if (factory != null) {
                factory.setKeepPostFor302Redirects(z);
            }
            return this;
        }

        public Factory setTransferListener(TransferListener transferListener) {
            this.transferListener = transferListener;
            DefaultHttpDataSource.Factory factory = this.internalFallbackFactory;
            if (factory != null) {
                factory.setTransferListener(transferListener);
            }
            return this;
        }

        @Deprecated
        public Factory setFallbackFactory(HttpDataSource.Factory factory) {
            this.fallbackFactory = factory;
            return this;
        }

        public Factory setReadBufferSize(int i) {
            this.readBufferSize = i;
            return this;
        }

        @Override // androidx.media3.datasource.DataSource.Factory
        public HttpDataSource createDataSource() {
            if (this.cronetEngine == null) {
                HttpDataSource.Factory factory = this.fallbackFactory;
                if (factory != null) {
                    return factory.createDataSource();
                }
                return ((DefaultHttpDataSource.Factory) Preconditions.checkNotNull(this.internalFallbackFactory)).createDataSource();
            }
            CronetDataSource cronetDataSource = new CronetDataSource(this.cronetEngine, this.executor, this.requestPriority, this.connectTimeoutMs, this.readTimeoutMs, this.resetTimeoutOnRedirects, this.handleSetCookieRequests, this.userAgent, this.defaultRequestProperties, this.contentTypePredicate, this.keepPostFor302Redirects, this.readBufferSize);
            TransferListener transferListener = this.transferListener;
            if (transferListener != null) {
                cronetDataSource.addTransferListener(transferListener);
            }
            return cronetDataSource;
        }
    }

    public static final class OpenException extends HttpDataSource.HttpDataSourceException {
        public final int cronetConnectionStatus;

        @Deprecated
        public OpenException(IOException iOException, DataSpec dataSpec, int i) {
            super(iOException, dataSpec, 2000, 1);
            this.cronetConnectionStatus = i;
        }

        public OpenException(IOException iOException, DataSpec dataSpec, int i, int i2) {
            super(iOException, dataSpec, i, 1);
            this.cronetConnectionStatus = i2;
        }

        @Deprecated
        public OpenException(String str, DataSpec dataSpec, int i) {
            super(str, dataSpec, 2000, 1);
            this.cronetConnectionStatus = i;
        }

        public OpenException(String str, DataSpec dataSpec, int i, int i2) {
            super(str, dataSpec, i, 1);
            this.cronetConnectionStatus = i2;
        }

        public OpenException(DataSpec dataSpec, int i, int i2) {
            super(dataSpec, i, 1);
            this.cronetConnectionStatus = i2;
        }
    }

    protected CronetDataSource(CronetEngine cronetEngine, Executor executor, int i, int i2, int i3, boolean z, boolean z2, String str, HttpDataSource.RequestProperties requestProperties, Predicate<String> predicate, boolean z3, int i4) {
        super(true);
        this.cronetEngine = (CronetEngine) Preconditions.checkNotNull(cronetEngine);
        this.executor = (Executor) Preconditions.checkNotNull(executor);
        this.requestPriority = i;
        this.connectTimeoutMs = i2;
        this.readTimeoutMs = i3;
        this.resetTimeoutOnRedirects = z;
        this.handleSetCookieRequests = z2;
        this.userAgent = str;
        this.defaultRequestProperties = requestProperties;
        this.contentTypePredicate = predicate;
        this.keepPostFor302Redirects = z3;
        this.clock = Clock.DEFAULT;
        this.readBufferSize = i4;
        this.requestProperties = new HttpDataSource.RequestProperties();
        this.operation = new ConditionVariable();
    }

    @Override // androidx.media3.datasource.HttpDataSource
    public void setRequestProperty(String str, String str2) {
        this.requestProperties.set(str, str2);
    }

    @Override // androidx.media3.datasource.HttpDataSource
    public void clearRequestProperty(String str) {
        this.requestProperties.remove(str);
    }

    @Override // androidx.media3.datasource.HttpDataSource
    public void clearAllRequestProperties() {
        this.requestProperties.clear();
    }

    @Override // androidx.media3.datasource.HttpDataSource
    public int getResponseCode() {
        UrlResponseInfo urlResponseInfo = this.responseInfo;
        if (urlResponseInfo == null || urlResponseInfo.getHttpStatusCode() <= 0) {
            return -1;
        }
        return this.responseInfo.getHttpStatusCode();
    }

    @Override // androidx.media3.datasource.BaseDataSource, androidx.media3.datasource.DataSource
    public Map<String, List<String>> getResponseHeaders() {
        UrlResponseInfo urlResponseInfo = this.responseInfo;
        return urlResponseInfo == null ? Collections.EMPTY_MAP : urlResponseInfo.getAllHeaders();
    }

    @Override // androidx.media3.datasource.DataSource
    public Uri getUri() {
        UrlResponseInfo urlResponseInfo = this.responseInfo;
        if (urlResponseInfo != null) {
            return Uri.parse(urlResponseInfo.getUrl());
        }
        DataSpec dataSpec = this.currentDataSpec;
        if (dataSpec != null) {
            return dataSpec.uri;
        }
        return null;
    }

    @Override // androidx.media3.datasource.DataSource
    public long open(DataSpec dataSpec) throws HttpDataSource.HttpDataSourceException {
        byte[] responseBody;
        String firstHeader;
        Preconditions.checkNotNull(dataSpec);
        Preconditions.checkState(!this.transferStarted);
        this.operation.close();
        resetConnectTimeout();
        this.currentDataSpec = dataSpec;
        try {
            createCurrentUrlRequestAndCallback(dataSpec);
            UrlRequest urlRequest = this.currentUrlRequest;
            urlRequest.start();
            transferInitializing(dataSpec);
            try {
                boolean zBlockUntilConnectTimeout = blockUntilConnectTimeout();
                IOException iOException = this.exception;
                if (iOException != null) {
                    String message = iOException.getMessage();
                    if (message != null && Ascii.toLowerCase(message).contains("err_cleartext_not_permitted")) {
                        throw new HttpDataSource.CleartextNotPermittedException(iOException, dataSpec);
                    }
                    throw new OpenException(iOException, dataSpec, 2001, getStatus(urlRequest));
                }
                if (!zBlockUntilConnectTimeout) {
                    throw new OpenException(new SocketTimeoutException(), dataSpec, 2002, getStatus(urlRequest));
                }
                UrlResponseInfo urlResponseInfo = (UrlResponseInfo) Preconditions.checkNotNull(this.responseInfo);
                int httpStatusCode = urlResponseInfo.getHttpStatusCode();
                Map<String, List<String>> allHeaders = urlResponseInfo.getAllHeaders();
                long j = 0;
                if (httpStatusCode < 200 || httpStatusCode > 299) {
                    if (httpStatusCode == 416) {
                        if (dataSpec.position == HttpUtil.getDocumentSize(getFirstHeader(allHeaders, "Content-Range"))) {
                            this.transferStarted = true;
                            transferStarted(dataSpec);
                            if (dataSpec.length != -1) {
                                return dataSpec.length;
                            }
                            return 0L;
                        }
                    }
                    try {
                        responseBody = readResponseBody();
                    } catch (IOException unused) {
                        responseBody = Util.EMPTY_BYTE_ARRAY;
                    }
                    throw new HttpDataSource.InvalidResponseCodeException(httpStatusCode, urlResponseInfo.getHttpStatusText(), httpStatusCode == 416 ? new DataSourceException(2008) : null, allHeaders, dataSpec, responseBody);
                }
                Predicate<String> predicate = this.contentTypePredicate;
                if (predicate != null && (firstHeader = getFirstHeader(allHeaders, "Content-Type")) != null && !predicate.apply(firstHeader)) {
                    throw new HttpDataSource.InvalidContentTypeException(firstHeader, dataSpec);
                }
                if (httpStatusCode == 200 && dataSpec.position != 0) {
                    j = dataSpec.position;
                }
                if (isCompressed(urlResponseInfo) || dataSpec.length != -1) {
                    this.bytesRemaining = dataSpec.length;
                } else {
                    long contentLength = HttpUtil.getContentLength(getFirstHeader(allHeaders, "Content-Length"), getFirstHeader(allHeaders, "Content-Range"));
                    this.bytesRemaining = contentLength != -1 ? contentLength - j : -1L;
                }
                this.transferStarted = true;
                transferStarted(dataSpec);
                skipFully(j, dataSpec);
                return this.bytesRemaining;
            } catch (InterruptedException unused2) {
                Thread.currentThread().interrupt();
                throw new OpenException(new InterruptedIOException(), dataSpec, 1004, -1);
            }
        } catch (IOException e) {
            if (e instanceof HttpDataSource.HttpDataSourceException) {
                throw ((HttpDataSource.HttpDataSourceException) e);
            }
            throw new OpenException(e, dataSpec, 2000, 0);
        }
    }

    @Override // androidx.media3.common.DataReader
    public int read(byte[] bArr, int i, int i2) throws HttpDataSource.HttpDataSourceException {
        Preconditions.checkState(this.transferStarted);
        if (i2 == 0) {
            return 0;
        }
        if (this.bytesRemaining == 0) {
            return -1;
        }
        ByteBuffer orCreateReadBuffer = getOrCreateReadBuffer();
        if (!orCreateReadBuffer.hasRemaining()) {
            this.operation.close();
            orCreateReadBuffer.clear();
            readInternal(orCreateReadBuffer, (DataSpec) Util.castNonNull(this.currentDataSpec));
            if (this.finished) {
                this.bytesRemaining = 0L;
                return -1;
            }
            orCreateReadBuffer.flip();
            Preconditions.checkState(orCreateReadBuffer.hasRemaining());
        }
        long j = this.bytesRemaining;
        if (j == -1) {
            j = Long.MAX_VALUE;
        }
        int iMin = (int) Longs.min(j, orCreateReadBuffer.remaining(), i2);
        orCreateReadBuffer.get(bArr, i, iMin);
        long j2 = this.bytesRemaining;
        if (j2 != -1) {
            this.bytesRemaining = j2 - ((long) iMin);
        }
        bytesTransferred(iMin);
        return iMin;
    }

    public int read(ByteBuffer byteBuffer) throws HttpDataSource.HttpDataSourceException {
        int iCopyByteBuffer;
        Preconditions.checkState(this.transferStarted);
        if (!byteBuffer.isDirect()) {
            throw new IllegalArgumentException("Passed buffer is not a direct ByteBuffer");
        }
        if (!byteBuffer.hasRemaining()) {
            return 0;
        }
        if (this.bytesRemaining == 0) {
            return -1;
        }
        int iRemaining = byteBuffer.remaining();
        ByteBuffer byteBuffer2 = this.readBuffer;
        if (byteBuffer2 != null && (iCopyByteBuffer = copyByteBuffer(byteBuffer2, byteBuffer)) != 0) {
            long j = this.bytesRemaining;
            if (j != -1) {
                this.bytesRemaining = j - ((long) iCopyByteBuffer);
            }
            bytesTransferred(iCopyByteBuffer);
            return iCopyByteBuffer;
        }
        this.operation.close();
        readInternal(byteBuffer, (DataSpec) Util.castNonNull(this.currentDataSpec));
        if (this.finished) {
            this.bytesRemaining = 0L;
            return -1;
        }
        Preconditions.checkState(iRemaining > byteBuffer.remaining());
        int iRemaining2 = iRemaining - byteBuffer.remaining();
        long j2 = this.bytesRemaining;
        if (j2 != -1) {
            this.bytesRemaining = j2 - ((long) iRemaining2);
        }
        bytesTransferred(iRemaining2);
        return iRemaining2;
    }

    @Override // androidx.media3.datasource.DataSource
    public synchronized void close() {
        closeCurrentUrlRequestAndCallback();
        ByteBuffer byteBuffer = this.readBuffer;
        if (byteBuffer != null) {
            byteBuffer.limit(0);
        }
        this.currentDataSpec = null;
        this.responseInfo = null;
        this.exception = null;
        this.finished = false;
        if (this.transferStarted) {
            this.transferStarted = false;
            transferEnded();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeCurrentUrlRequestAndCallback() {
        UrlRequest urlRequest = this.currentUrlRequest;
        if (urlRequest != null) {
            urlRequest.cancel();
            this.currentUrlRequest = null;
        }
        UrlRequestCallback urlRequestCallback = this.currentUrlRequestCallback;
        if (urlRequestCallback != null) {
            urlRequestCallback.close();
            this.currentUrlRequestCallback = null;
        }
    }

    protected UrlRequest getCurrentUrlRequest() {
        return this.currentUrlRequest;
    }

    protected UrlResponseInfo getCurrentUrlResponseInfo() {
        return this.responseInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @EnsuresNonNull({"this.currentUrlRequestCallback", "this.currentUrlRequest"})
    public void createCurrentUrlRequestAndCallback(DataSpec dataSpec) throws IOException {
        this.currentUrlRequestCallback = new UrlRequestCallback();
        this.currentUrlRequest = buildRequestBuilder(dataSpec).build();
    }

    @RequiresNonNull({"this.currentUrlRequestCallback"})
    @SideEffectFree
    protected UrlRequest.Builder buildRequestBuilder(DataSpec dataSpec) throws IOException {
        UrlRequest.Builder builderAllowDirectExecutor = this.cronetEngine.newUrlRequestBuilder(dataSpec.uri.toString(), this.currentUrlRequestCallback, this.executor).setPriority(this.requestPriority).allowDirectExecutor();
        HashMap map = new HashMap();
        HttpDataSource.RequestProperties requestProperties = this.defaultRequestProperties;
        if (requestProperties != null) {
            map.putAll(requestProperties.getSnapshot());
        }
        map.putAll(this.requestProperties.getSnapshot());
        map.putAll(dataSpec.httpRequestHeaders);
        for (Map.Entry entry : map.entrySet()) {
            builderAllowDirectExecutor.addHeader((String) entry.getKey(), (String) entry.getValue());
        }
        if (dataSpec.httpBody != null && !map.containsKey("Content-Type")) {
            throw new OpenException("HTTP request with non-empty body must set Content-Type", dataSpec, 1004, 0);
        }
        String strBuildRangeRequestHeader = HttpUtil.buildRangeRequestHeader(dataSpec.position, dataSpec.length);
        if (strBuildRangeRequestHeader != null) {
            builderAllowDirectExecutor.addHeader("Range", strBuildRangeRequestHeader);
        }
        String str = this.userAgent;
        if (str != null) {
            builderAllowDirectExecutor.addHeader("User-Agent", str);
        }
        builderAllowDirectExecutor.setHttpMethod(dataSpec.getHttpMethodString());
        if (dataSpec.httpBody != null) {
            builderAllowDirectExecutor.setUploadDataProvider(new ByteArrayUploadDataProvider(dataSpec.httpBody), this.executor);
        }
        return builderAllowDirectExecutor;
    }

    private boolean blockUntilConnectTimeout() throws InterruptedException {
        long jElapsedRealtime = this.clock.elapsedRealtime();
        boolean zBlock = false;
        while (!zBlock && jElapsedRealtime < this.currentConnectTimeoutMs) {
            zBlock = this.operation.block((this.currentConnectTimeoutMs - jElapsedRealtime) + 5);
            jElapsedRealtime = this.clock.elapsedRealtime();
        }
        return zBlock;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetConnectTimeout() {
        this.currentConnectTimeoutMs = this.clock.elapsedRealtime() + ((long) this.connectTimeoutMs);
    }

    private void skipFully(long j, DataSpec dataSpec) throws HttpDataSource.HttpDataSourceException {
        if (j == 0) {
            return;
        }
        ByteBuffer orCreateReadBuffer = getOrCreateReadBuffer();
        while (j > 0) {
            try {
                this.operation.close();
                orCreateReadBuffer.clear();
                readInternal(orCreateReadBuffer, dataSpec);
                if (Thread.currentThread().isInterrupted()) {
                    throw new InterruptedIOException();
                }
                if (this.finished) {
                    throw new OpenException(dataSpec, 2008, 14);
                }
                orCreateReadBuffer.flip();
                Preconditions.checkState(orCreateReadBuffer.hasRemaining());
                int iMin = (int) Math.min(orCreateReadBuffer.remaining(), j);
                orCreateReadBuffer.position(orCreateReadBuffer.position() + iMin);
                j -= (long) iMin;
            } catch (IOException e) {
                if (e instanceof HttpDataSource.HttpDataSourceException) {
                    throw ((HttpDataSource.HttpDataSourceException) e);
                }
                throw new OpenException(e, dataSpec, e instanceof SocketTimeoutException ? 2002 : 2001, 14);
            }
        }
    }

    private byte[] readResponseBody() throws IOException {
        byte[] bArrCopyOf = Util.EMPTY_BYTE_ARRAY;
        ByteBuffer orCreateReadBuffer = getOrCreateReadBuffer();
        while (!this.finished) {
            this.operation.close();
            orCreateReadBuffer.clear();
            readInternal(orCreateReadBuffer, (DataSpec) Util.castNonNull(this.currentDataSpec));
            orCreateReadBuffer.flip();
            if (orCreateReadBuffer.remaining() > 0) {
                int length = bArrCopyOf.length;
                bArrCopyOf = Arrays.copyOf(bArrCopyOf, bArrCopyOf.length + orCreateReadBuffer.remaining());
                orCreateReadBuffer.get(bArrCopyOf, length, orCreateReadBuffer.remaining());
            }
        }
        return bArrCopyOf;
    }

    private void readInternal(ByteBuffer byteBuffer, DataSpec dataSpec) throws HttpDataSource.HttpDataSourceException {
        ((UrlRequest) Util.castNonNull(this.currentUrlRequest)).read(byteBuffer);
        try {
            if (!this.operation.block(this.readTimeoutMs)) {
                throw new SocketTimeoutException();
            }
        } catch (InterruptedException unused) {
            if (byteBuffer == this.readBuffer) {
                this.readBuffer = null;
            }
            Thread.currentThread().interrupt();
            this.exception = new InterruptedIOException();
        } catch (SocketTimeoutException e) {
            if (byteBuffer == this.readBuffer) {
                this.readBuffer = null;
            }
            this.exception = new HttpDataSource.HttpDataSourceException(e, dataSpec, 2002, 2);
        }
        IOException iOException = this.exception;
        if (iOException != null) {
            if (iOException instanceof HttpDataSource.HttpDataSourceException) {
                throw ((HttpDataSource.HttpDataSourceException) iOException);
            }
            throw HttpDataSource.HttpDataSourceException.createForIOException(iOException, dataSpec, 2);
        }
    }

    private ByteBuffer getOrCreateReadBuffer() {
        if (this.readBuffer == null) {
            ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(this.readBufferSize);
            this.readBuffer = byteBufferAllocateDirect;
            byteBufferAllocateDirect.limit(0);
        }
        return this.readBuffer;
    }

    private static boolean isCompressed(UrlResponseInfo urlResponseInfo) {
        for (Map.Entry<String, String> entry : urlResponseInfo.getAllHeadersAsList()) {
            if (entry.getKey().equalsIgnoreCase("Content-Encoding")) {
                return !entry.getValue().equalsIgnoreCase("identity");
            }
        }
        return false;
    }

    private static int getStatus(UrlRequest urlRequest) throws InterruptedException {
        final ConditionVariable conditionVariable = new ConditionVariable();
        final int[] iArr = new int[1];
        urlRequest.getStatus(new UrlRequest.StatusListener() { // from class: androidx.media3.datasource.cronet.CronetDataSource.1
            @Override // org.chromium.net.UrlRequest.StatusListener
            public void onStatus(int i) {
                iArr[0] = i;
                conditionVariable.open();
            }
        });
        conditionVariable.block();
        return iArr[0];
    }

    private static String getFirstHeader(Map<String, List<String>> map, String str) {
        List<String> list = map.get(str);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    private static int copyByteBuffer(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        int iMin = Math.min(byteBuffer.remaining(), byteBuffer2.remaining());
        int iLimit = byteBuffer.limit();
        byteBuffer.limit(byteBuffer.position() + iMin);
        byteBuffer2.put(byteBuffer);
        byteBuffer.limit(iLimit);
        return iMin;
    }

    final class UrlRequestCallback extends UrlRequest.Callback {
        private volatile boolean isClosed = false;

        UrlRequestCallback() {
        }

        public void close() {
            this.isClosed = true;
        }

        @Override // org.chromium.net.UrlRequest.Callback
        public synchronized void onRedirectReceived(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo, String str) {
            DataSpec dataSpecWithUri;
            if (this.isClosed) {
                return;
            }
            Preconditions.checkNotNull(CronetDataSource.this.currentUrlRequest);
            Preconditions.checkNotNull(CronetDataSource.this.currentUrlRequestCallback);
            DataSpec dataSpec = (DataSpec) Preconditions.checkNotNull(CronetDataSource.this.currentDataSpec);
            int httpStatusCode = urlResponseInfo.getHttpStatusCode();
            if (dataSpec.httpMethod != 2 || (httpStatusCode != 307 && httpStatusCode != 308)) {
                if (CronetDataSource.this.resetTimeoutOnRedirects) {
                    CronetDataSource.this.resetConnectTimeout();
                }
                CookieHandler cookieManager = CookieHandler.getDefault();
                if (cookieManager == null && CronetDataSource.this.handleSetCookieRequests) {
                    cookieManager = new CookieManager();
                }
                String url = urlResponseInfo.getUrl();
                Map<String, List<String>> allHeaders = urlResponseInfo.getAllHeaders();
                HttpUtil.storeCookiesFromHeaders(url, allHeaders, cookieManager);
                String cookieHeader = HttpUtil.getCookieHeader(url, allHeaders, cookieManager);
                boolean z = CronetDataSource.this.keepPostFor302Redirects && dataSpec.httpMethod == 2 && httpStatusCode == 302;
                if (!z && (!CronetDataSource.this.handleSetCookieRequests || TextUtils.isEmpty(cookieHeader))) {
                    urlRequest.followRedirect();
                    return;
                }
                if (!z && dataSpec.httpMethod == 2) {
                    dataSpecWithUri = dataSpec.buildUpon().setUri(str).setHttpMethod(1).setHttpBody(null).build();
                } else {
                    dataSpecWithUri = dataSpec.withUri(Uri.parse(str));
                }
                if (!TextUtils.isEmpty(cookieHeader)) {
                    HashMap map = new HashMap();
                    map.putAll(dataSpec.httpRequestHeaders);
                    map.put("Cookie", cookieHeader);
                    dataSpecWithUri = dataSpecWithUri.buildUpon().setHttpRequestHeaders(map).build();
                }
                CronetDataSource.this.closeCurrentUrlRequestAndCallback();
                try {
                    CronetDataSource.this.createCurrentUrlRequestAndCallback(dataSpecWithUri);
                    CronetDataSource.this.currentUrlRequest.start();
                    return;
                } catch (IOException e) {
                    CronetDataSource.this.exception = e;
                    return;
                }
            }
            CronetDataSource.this.exception = new HttpDataSource.InvalidResponseCodeException(httpStatusCode, urlResponseInfo.getHttpStatusText(), null, urlResponseInfo.getAllHeaders(), dataSpec, Util.EMPTY_BYTE_ARRAY);
            CronetDataSource.this.operation.open();
        }

        @Override // org.chromium.net.UrlRequest.Callback
        public synchronized void onResponseStarted(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo) {
            if (this.isClosed) {
                return;
            }
            HttpUtil.storeCookiesFromHeaders(urlResponseInfo.getUrl(), urlResponseInfo.getAllHeaders(), CookieHandler.getDefault());
            CronetDataSource.this.responseInfo = urlResponseInfo;
            CronetDataSource.this.operation.open();
        }

        @Override // org.chromium.net.UrlRequest.Callback
        public synchronized void onReadCompleted(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo, ByteBuffer byteBuffer) {
            if (this.isClosed) {
                return;
            }
            CronetDataSource.this.operation.open();
        }

        @Override // org.chromium.net.UrlRequest.Callback
        public synchronized void onSucceeded(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo) {
            if (this.isClosed) {
                return;
            }
            CronetDataSource.this.finished = true;
            CronetDataSource.this.operation.open();
        }

        @Override // org.chromium.net.UrlRequest.Callback
        public synchronized void onFailed(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo, CronetException cronetException) {
            if (this.isClosed) {
                return;
            }
            if (!(cronetException instanceof NetworkException) || ((NetworkException) cronetException).getErrorCode() != 1) {
                CronetDataSource.this.exception = cronetException;
            } else {
                CronetDataSource.this.exception = new UnknownHostException();
            }
            CronetDataSource.this.operation.open();
        }
    }
}
