package com.google.firebase.storage.network;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import b.a.a.a.a;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.common.net.HttpHeaders;
import com.google.firebase.FirebaseApp;
import com.google.firebase.emulators.EmulatedServiceSettings;
import com.google.firebase.installations.Utils;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.internal.StorageReferenceUri;
import com.google.firebase.storage.network.connection.HttpURLConnectionFactory;
import com.google.firebase.storage.network.connection.HttpURLConnectionFactoryImpl;
import e.b.b;
import e.b.d;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class NetworkRequest {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String APPLICATION_JSON = "application/json";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String DELETE = "DELETE";
    public static final String GET = "GET";
    public static final int INITIALIZATION_EXCEPTION = -1;
    public static final int MAXIMUM_TOKEN_WAIT_TIME_MS = 30000;
    public static final int NETWORK_UNAVAILABLE = -2;
    public static final String PATCH = "PATCH";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String TAG = "NetworkRequest";
    public static final String UTF_8 = "UTF-8";
    public static final String X_FIREBASE_APPCHECK = "x-firebase-appcheck";
    public static final String X_FIREBASE_GMPID = "x-firebase-gmpid";
    public static String gmsCoreVersion;
    public HttpURLConnection connection;
    public Context context;
    public Exception mException;
    public String rawStringResponse;
    public Map<String, String> requestHeaders = new HashMap();
    public int resultCode;
    public Map<String, List<String>> resultHeaders;
    public InputStream resultInputStream;
    public int resultingContentLength;
    public StorageReferenceUri storageReferenceUri;
    public static final Uri PROD_BASE_URL = Uri.parse("https://firebasestorage.googleapis.com/v0");
    public static HttpURLConnectionFactory connectionFactory = new HttpURLConnectionFactoryImpl();

    public NetworkRequest(@NonNull StorageReferenceUri storageReferenceUri, @NonNull FirebaseApp firebaseApp) {
        Preconditions.checkNotNull(storageReferenceUri);
        Preconditions.checkNotNull(firebaseApp);
        this.storageReferenceUri = storageReferenceUri;
        this.context = firebaseApp.getApplicationContext();
        setCustomHeader(X_FIREBASE_GMPID, firebaseApp.getOptions().getApplicationId());
    }

    private void constructMessage(@NonNull HttpURLConnection httpURLConnection, @Nullable String str, @Nullable String str2) throws UnsupportedEncodingException {
        byte[] outputRaw;
        int outputRawSize;
        Preconditions.checkNotNull(httpURLConnection);
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "no auth token for request");
        } else {
            httpURLConnection.setRequestProperty(HttpHeaders.AUTHORIZATION, "Firebase " + str);
        }
        if (TextUtils.isEmpty(str2)) {
            Log.w(TAG, "No App Check token for request.");
        } else {
            httpURLConnection.setRequestProperty(X_FIREBASE_APPCHECK, str2);
        }
        StringBuilder sb = new StringBuilder("Android/");
        String gmsCoreVersion2 = getGmsCoreVersion(this.context);
        if (!TextUtils.isEmpty(gmsCoreVersion2)) {
            sb.append(gmsCoreVersion2);
        }
        httpURLConnection.setRequestProperty("X-Firebase-Storage-Version", sb.toString());
        for (Map.Entry<String, String> entry : this.requestHeaders.entrySet()) {
            httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
        }
        d outputJSON = getOutputJSON();
        if (outputJSON != null) {
            outputRaw = outputJSON.toString().getBytes("UTF-8");
            outputRawSize = outputRaw.length;
        } else {
            outputRaw = getOutputRaw();
            outputRawSize = getOutputRawSize();
            if (outputRawSize == 0 && outputRaw != null) {
                outputRawSize = outputRaw.length;
            }
        }
        if (outputRaw == null || outputRaw.length <= 0) {
            httpURLConnection.setRequestProperty("Content-Length", "0");
        } else {
            if (outputJSON != null) {
                httpURLConnection.setRequestProperty("Content-Type", "application/json");
            }
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-Length", Integer.toString(outputRawSize));
        }
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoInput(true);
        if (outputRaw == null || outputRaw.length <= 0) {
            return;
        }
        OutputStream outputStream = httpURLConnection.getOutputStream();
        if (outputStream == null) {
            Log.e(TAG, "Unable to write to the http request!");
            return;
        }
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        try {
            bufferedOutputStream.write(outputRaw, 0, outputRawSize);
        } finally {
            bufferedOutputStream.close();
        }
    }

    private HttpURLConnection createConnection() {
        Uri url = getURL();
        Map<String, String> queryParameters = getQueryParameters();
        if (queryParameters != null) {
            Uri.Builder builderBuildUpon = url.buildUpon();
            for (Map.Entry<String, String> entry : queryParameters.entrySet()) {
                builderBuildUpon.appendQueryParameter(entry.getKey(), entry.getValue());
            }
            url = builderBuildUpon.build();
        }
        return connectionFactory.createInstance(new URL(url.toString()));
    }

    private boolean ensureNetworkAvailable(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        this.mException = new SocketException("Network subsystem is unavailable");
        this.resultCode = -2;
        return false;
    }

    @NonNull
    public static Uri getBaseUrl(@Nullable EmulatedServiceSettings emulatedServiceSettings) {
        if (emulatedServiceSettings == null) {
            return Uri.parse("https://firebasestorage.googleapis.com/v0");
        }
        StringBuilder sbA = a.a("http://");
        sbA.append(emulatedServiceSettings.getHost());
        sbA.append(Utils.APP_ID_IDENTIFICATION_SUBSTRING);
        sbA.append(emulatedServiceSettings.getPort());
        sbA.append("/v0");
        return Uri.parse(sbA.toString());
    }

    @NonNull
    public static String getGmsCoreVersion(Context context) {
        if (gmsCoreVersion == null) {
            try {
                gmsCoreVersion = context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionName;
            } catch (PackageManager.NameNotFoundException e2) {
                Log.e(TAG, "Unable to find gmscore in package manager", e2);
            }
            if (gmsCoreVersion == null) {
                gmsCoreVersion = "[No Gmscore]";
            }
        }
        return gmsCoreVersion;
    }

    public static String getPathWithoutBucket(@NonNull Uri uri) {
        String path = uri.getPath();
        if (path == null) {
            return "";
        }
        return path.startsWith("/") ? path.substring(1) : path;
    }

    private void parseResponse(@NonNull HttpURLConnection httpURLConnection) {
        Preconditions.checkNotNull(httpURLConnection);
        this.resultCode = httpURLConnection.getResponseCode();
        this.resultHeaders = httpURLConnection.getHeaderFields();
        this.resultingContentLength = httpURLConnection.getContentLength();
        if (isResultSuccess()) {
            this.resultInputStream = httpURLConnection.getInputStream();
        } else {
            this.resultInputStream = httpURLConnection.getErrorStream();
        }
    }

    private final void performRequest(@Nullable String str, @Nullable String str2) {
        performRequestStart(str, str2);
        try {
            processResponseStream();
        } catch (IOException e2) {
            StringBuilder sbA = a.a("error sending network request ");
            sbA.append(getAction());
            sbA.append(" ");
            sbA.append(getURL());
            Log.w(TAG, sbA.toString(), e2);
            this.mException = e2;
            this.resultCode = -2;
        }
        performRequestEnd();
    }

    private void processResponseStream() throws IOException {
        if (isResultSuccess()) {
            parseSuccessulResponse(this.resultInputStream);
        } else {
            parseErrorResponse(this.resultInputStream);
        }
    }

    public <TResult> void completeTask(TaskCompletionSource<TResult> taskCompletionSource, TResult tresult) {
        Exception exception = getException();
        if (isResultSuccess() && exception == null) {
            taskCompletionSource.setResult(tresult);
        } else {
            taskCompletionSource.setException(StorageException.fromExceptionAndHttpCode(exception, getResultCode()));
        }
    }

    @NonNull
    public abstract String getAction();

    @Nullable
    public Exception getException() {
        return this.mException;
    }

    @Nullable
    public d getOutputJSON() {
        return null;
    }

    @Nullable
    public byte[] getOutputRaw() {
        return null;
    }

    public int getOutputRawSize() {
        return 0;
    }

    @Nullable
    public Map<String, String> getQueryParameters() {
        return null;
    }

    @Nullable
    public String getRawResult() {
        return this.rawStringResponse;
    }

    public d getResultBody() {
        if (TextUtils.isEmpty(this.rawStringResponse)) {
            return new d();
        }
        try {
            return new d(this.rawStringResponse);
        } catch (b e2) {
            StringBuilder sbA = a.a("error parsing result into JSON:");
            sbA.append(this.rawStringResponse);
            Log.e(TAG, sbA.toString(), e2);
            return new d();
        }
    }

    public int getResultCode() {
        return this.resultCode;
    }

    @NonNull
    public Map<String, String> getResultHeaders() {
        return this.requestHeaders;
    }

    @Nullable
    public Map<String, List<String>> getResultHeadersImpl() {
        return this.resultHeaders;
    }

    @Nullable
    public String getResultString(String str) {
        List<String> list;
        Map<String, List<String>> resultHeadersImpl = getResultHeadersImpl();
        if (resultHeadersImpl == null || (list = resultHeadersImpl.get(str)) == null || list.size() <= 0) {
            return null;
        }
        return list.get(0);
    }

    public int getResultingContentLength() {
        return this.resultingContentLength;
    }

    @NonNull
    public StorageReferenceUri getStorageReferenceUri() {
        return this.storageReferenceUri;
    }

    public InputStream getStream() {
        return this.resultInputStream;
    }

    @NonNull
    @VisibleForTesting
    public Uri getURL() {
        return this.storageReferenceUri.getHttpUri();
    }

    public boolean isResultSuccess() {
        int i = this.resultCode;
        return i >= 200 && i < 300;
    }

    public void parseErrorResponse(@Nullable InputStream inputStream) throws IOException {
        parseResponse(inputStream);
    }

    public void parseSuccessulResponse(@Nullable InputStream inputStream) throws IOException {
        parseResponse(inputStream);
    }

    public void performRequestEnd() {
        HttpURLConnection httpURLConnection = this.connection;
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    public void performRequestStart(@Nullable String str, @Nullable String str2) {
        if (this.mException != null) {
            this.resultCode = -1;
            return;
        }
        if (Log.isLoggable(TAG, 3)) {
            StringBuilder sbA = a.a("sending network request ");
            sbA.append(getAction());
            sbA.append(" ");
            sbA.append(getURL());
            Log.d(TAG, sbA.toString());
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            this.resultCode = -2;
            this.mException = new SocketException("Network subsystem is unavailable");
            return;
        }
        try {
            this.connection = createConnection();
            this.connection.setRequestMethod(getAction());
            constructMessage(this.connection, str, str2);
            parseResponse(this.connection);
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "network request result " + this.resultCode);
            }
        } catch (IOException e2) {
            StringBuilder sbA2 = a.a("error sending network request ");
            sbA2.append(getAction());
            sbA2.append(" ");
            sbA2.append(getURL());
            Log.w(TAG, sbA2.toString(), e2);
            this.mException = e2;
            this.resultCode = -2;
        }
    }

    public final void reset() {
        this.mException = null;
        this.resultCode = 0;
    }

    public void setCustomHeader(String str, String str2) {
        this.requestHeaders.put(str, str2);
    }

    public String getPathWithoutBucket() {
        return getPathWithoutBucket(this.storageReferenceUri.getGsUri());
    }

    public void performRequest(@Nullable String str, @Nullable String str2, @NonNull Context context) {
        if (ensureNetworkAvailable(context)) {
            performRequest(str, str2);
        }
    }

    private void parseResponse(@Nullable InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (inputStream != null) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while (true) {
                try {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    } else {
                        sb.append(line);
                    }
                } catch (Throwable th) {
                    bufferedReader.close();
                    throw th;
                }
            }
            bufferedReader.close();
        }
        this.rawStringResponse = sb.toString();
        if (isResultSuccess()) {
            return;
        }
        this.mException = new IOException(this.rawStringResponse);
    }
}
