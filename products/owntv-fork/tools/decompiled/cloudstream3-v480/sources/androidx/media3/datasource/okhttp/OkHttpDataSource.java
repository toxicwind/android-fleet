package androidx.media3.datasource.okhttp;

import android.net.Uri;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.BaseDataSource;
import androidx.media3.datasource.DataSourceException;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.HttpDataSource;
import androidx.media3.datasource.HttpUtil;
import androidx.media3.datasource.TransferListener;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.io.ByteStreams;
import com.google.common.util.concurrent.SettableFuture;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: classes4.dex */
public class OkHttpDataSource extends BaseDataSource implements HttpDataSource {
    private long bytesRead;
    private long bytesToRead;
    private final CacheControl cacheControl;
    private final Call.Factory callFactory;
    private boolean connectionEstablished;
    private final Predicate<String> contentTypePredicate;
    private DataSpec dataSpec;
    private final HttpDataSource.RequestProperties defaultRequestProperties;
    private final HttpDataSource.RequestProperties requestProperties;
    private Response response;
    private InputStream responseByteStream;
    private final String userAgent;

    static {
        MediaLibraryInfo.registerModule("media3.datasource.okhttp");
    }

    public static final class Factory implements HttpDataSource.Factory {
        private CacheControl cacheControl;
        private final Call.Factory callFactory;
        private Predicate<String> contentTypePredicate;
        private final HttpDataSource.RequestProperties defaultRequestProperties = new HttpDataSource.RequestProperties();
        private TransferListener transferListener;
        private String userAgent;

        @Override // androidx.media3.datasource.HttpDataSource.Factory
        public /* bridge */ /* synthetic */ HttpDataSource.Factory setDefaultRequestProperties(Map map) {
            return setDefaultRequestProperties((Map<String, String>) map);
        }

        public Factory(Call.Factory factory) {
            this.callFactory = factory;
        }

        @Override // androidx.media3.datasource.HttpDataSource.Factory
        public final Factory setDefaultRequestProperties(Map<String, String> map) {
            this.defaultRequestProperties.clearAndSet(map);
            return this;
        }

        public Factory setUserAgent(String str) {
            this.userAgent = str;
            return this;
        }

        public Factory setCacheControl(CacheControl cacheControl) {
            this.cacheControl = cacheControl;
            return this;
        }

        public Factory setContentTypePredicate(Predicate<String> predicate) {
            this.contentTypePredicate = predicate;
            return this;
        }

        public Factory setTransferListener(TransferListener transferListener) {
            this.transferListener = transferListener;
            return this;
        }

        @Override // androidx.media3.datasource.HttpDataSource.Factory, androidx.media3.datasource.DataSource.Factory
        public OkHttpDataSource createDataSource() {
            OkHttpDataSource okHttpDataSource = new OkHttpDataSource(this.callFactory, this.userAgent, this.cacheControl, this.defaultRequestProperties, this.contentTypePredicate);
            TransferListener transferListener = this.transferListener;
            if (transferListener != null) {
                okHttpDataSource.addTransferListener(transferListener);
            }
            return okHttpDataSource;
        }
    }

    private OkHttpDataSource(Call.Factory factory, String str, CacheControl cacheControl, HttpDataSource.RequestProperties requestProperties, Predicate<String> predicate) {
        super(true);
        this.callFactory = (Call.Factory) Preconditions.checkNotNull(factory);
        this.userAgent = str;
        this.cacheControl = cacheControl;
        this.defaultRequestProperties = requestProperties;
        this.contentTypePredicate = predicate;
        this.requestProperties = new HttpDataSource.RequestProperties();
    }

    @Override // androidx.media3.datasource.DataSource
    public Uri getUri() {
        Response response = this.response;
        if (response != null) {
            return Uri.parse(response.request().url().getUrl());
        }
        DataSpec dataSpec = this.dataSpec;
        if (dataSpec != null) {
            return dataSpec.uri;
        }
        return null;
    }

    @Override // androidx.media3.datasource.HttpDataSource
    public int getResponseCode() {
        Response response = this.response;
        if (response == null) {
            return -1;
        }
        return response.code();
    }

    @Override // androidx.media3.datasource.BaseDataSource, androidx.media3.datasource.DataSource
    public Map<String, List<String>> getResponseHeaders() {
        Response response = this.response;
        return response == null ? Collections.EMPTY_MAP : response.headers().toMultimap();
    }

    @Override // androidx.media3.datasource.HttpDataSource
    public void setRequestProperty(String str, String str2) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(str2);
        this.requestProperties.set(str, str2);
    }

    @Override // androidx.media3.datasource.HttpDataSource
    public void clearRequestProperty(String str) {
        Preconditions.checkNotNull(str);
        this.requestProperties.remove(str);
    }

    @Override // androidx.media3.datasource.HttpDataSource
    public void clearAllRequestProperties() {
        this.requestProperties.clear();
    }

    @Override // androidx.media3.datasource.DataSource
    public long open(DataSpec dataSpec) throws HttpDataSource.HttpDataSourceException {
        byte[] byteArray;
        this.dataSpec = dataSpec;
        long j = 0;
        this.bytesRead = 0L;
        this.bytesToRead = 0L;
        transferInitializing(dataSpec);
        try {
            Response responseExecuteCall = executeCall(this.callFactory.newCall(makeRequest(dataSpec)));
            this.response = responseExecuteCall;
            ResponseBody responseBody = (ResponseBody) Preconditions.checkNotNull(responseExecuteCall.body());
            this.responseByteStream = responseBody.byteStream();
            int iCode = responseExecuteCall.code();
            if (!responseExecuteCall.getIsSuccessful()) {
                if (iCode == 416) {
                    if (dataSpec.position == HttpUtil.getDocumentSize(responseExecuteCall.headers().get("Content-Range"))) {
                        this.connectionEstablished = true;
                        transferStarted(dataSpec);
                        if (dataSpec.length != -1) {
                            return dataSpec.length;
                        }
                        return 0L;
                    }
                }
                try {
                    byteArray = ByteStreams.toByteArray((InputStream) Preconditions.checkNotNull(this.responseByteStream));
                } catch (IOException unused) {
                    byteArray = Util.EMPTY_BYTE_ARRAY;
                }
                byte[] bArr = byteArray;
                Map<String, List<String>> multimap = responseExecuteCall.headers().toMultimap();
                closeConnectionQuietly();
                throw new HttpDataSource.InvalidResponseCodeException(iCode, responseExecuteCall.message(), iCode == 416 ? new DataSourceException(2008) : null, multimap, dataSpec, bArr);
            }
            MediaType mediaType = responseBody.getMediaType();
            String string = mediaType != null ? mediaType.toString() : "";
            Predicate<String> predicate = this.contentTypePredicate;
            if (predicate != null && !predicate.apply(string)) {
                closeConnectionQuietly();
                throw new HttpDataSource.InvalidContentTypeException(string, dataSpec);
            }
            if (iCode == 200 && dataSpec.position != 0) {
                j = dataSpec.position;
            }
            if (dataSpec.length != -1) {
                this.bytesToRead = dataSpec.length;
            } else {
                long contentLength = responseBody.getContentLength();
                this.bytesToRead = contentLength != -1 ? contentLength - j : -1L;
            }
            this.connectionEstablished = true;
            transferStarted(dataSpec);
            try {
                skipFully(j, dataSpec);
                return this.bytesToRead;
            } catch (HttpDataSource.HttpDataSourceException e) {
                closeConnectionQuietly();
                throw e;
            }
        } catch (IOException e2) {
            throw HttpDataSource.HttpDataSourceException.createForIOException(e2, dataSpec, 1);
        }
    }

    @Override // androidx.media3.common.DataReader
    public int read(byte[] bArr, int i, int i2) throws HttpDataSource.HttpDataSourceException {
        try {
            return readInternal(bArr, i, i2);
        } catch (IOException e) {
            throw HttpDataSource.HttpDataSourceException.createForIOException(e, (DataSpec) Util.castNonNull(this.dataSpec), 2);
        }
    }

    @Override // androidx.media3.datasource.DataSource
    public void close() {
        if (this.connectionEstablished) {
            this.connectionEstablished = false;
            transferEnded();
            closeConnectionQuietly();
        }
        this.response = null;
        this.dataSpec = null;
    }

    private Request makeRequest(DataSpec dataSpec) throws HttpDataSource.HttpDataSourceException {
        RequestBody requestBodyCreate;
        long j = dataSpec.position;
        long j2 = dataSpec.length;
        HttpUrl httpUrl = HttpUrl.parse(dataSpec.uri.toString());
        if (httpUrl == null) {
            throw new HttpDataSource.HttpDataSourceException("Malformed URL", dataSpec, 1004, 1);
        }
        Request.Builder builderUrl = new Request.Builder().url(httpUrl);
        CacheControl cacheControl = this.cacheControl;
        if (cacheControl != null) {
            builderUrl.cacheControl(cacheControl);
        }
        HashMap map = new HashMap();
        HttpDataSource.RequestProperties requestProperties = this.defaultRequestProperties;
        if (requestProperties != null) {
            map.putAll(requestProperties.getSnapshot());
        }
        map.putAll(this.requestProperties.getSnapshot());
        map.putAll(dataSpec.httpRequestHeaders);
        for (Map.Entry entry : map.entrySet()) {
            builderUrl.header((String) entry.getKey(), (String) entry.getValue());
        }
        String strBuildRangeRequestHeader = HttpUtil.buildRangeRequestHeader(j, j2);
        if (strBuildRangeRequestHeader != null) {
            builderUrl.addHeader("Range", strBuildRangeRequestHeader);
        }
        String str = this.userAgent;
        if (str != null) {
            builderUrl.addHeader("User-Agent", str);
        }
        if (!dataSpec.isFlagSet(1)) {
            builderUrl.addHeader("Accept-Encoding", "identity");
        }
        if (dataSpec.httpBody != null) {
            requestBodyCreate = RequestBody.create(dataSpec.httpBody);
        } else {
            requestBodyCreate = dataSpec.httpMethod == 2 ? RequestBody.create(Util.EMPTY_BYTE_ARRAY) : null;
        }
        builderUrl.method(dataSpec.getHttpMethodString(), requestBodyCreate);
        return builderUrl.build();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Response executeCall(Call call) throws IOException {
        final SettableFuture settableFutureCreate = SettableFuture.create();
        call.enqueue(new Callback() { // from class: androidx.media3.datasource.okhttp.OkHttpDataSource.1
            @Override // okhttp3.Callback
            public void onFailure(Call call2, IOException iOException) {
                settableFutureCreate.setException(iOException);
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call2, Response response) {
                settableFutureCreate.set(response);
            }
        });
        try {
            return (Response) settableFutureCreate.get();
        } catch (InterruptedException unused) {
            call.cancel();
            throw new InterruptedIOException();
        } catch (ExecutionException e) {
            throw new IOException(e);
        }
    }

    private void skipFully(long j, DataSpec dataSpec) throws HttpDataSource.HttpDataSourceException {
        if (j == 0) {
            return;
        }
        byte[] bArr = new byte[4096];
        while (j > 0) {
            try {
                int i = ((InputStream) Util.castNonNull(this.responseByteStream)).read(bArr, 0, (int) Math.min(j, 4096L));
                if (Thread.currentThread().isInterrupted()) {
                    throw new InterruptedIOException();
                }
                if (i == -1) {
                    throw new HttpDataSource.HttpDataSourceException(dataSpec, 2008, 1);
                }
                j -= (long) i;
                bytesTransferred(i);
            } catch (IOException e) {
                if (e instanceof HttpDataSource.HttpDataSourceException) {
                    throw ((HttpDataSource.HttpDataSourceException) e);
                }
                throw new HttpDataSource.HttpDataSourceException(dataSpec, 2000, 1);
            }
        }
    }

    private int readInternal(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        long j = this.bytesToRead;
        if (j != -1) {
            long j2 = j - this.bytesRead;
            if (j2 == 0) {
                return -1;
            }
            i2 = (int) Math.min(i2, j2);
        }
        int i3 = ((InputStream) Util.castNonNull(this.responseByteStream)).read(bArr, i, i2);
        if (i3 == -1) {
            return -1;
        }
        this.bytesRead += (long) i3;
        bytesTransferred(i3);
        return i3;
    }

    private void closeConnectionQuietly() {
        Response response = this.response;
        if (response != null) {
            ((ResponseBody) Preconditions.checkNotNull(response.body())).close();
        }
        this.responseByteStream = null;
    }
}
