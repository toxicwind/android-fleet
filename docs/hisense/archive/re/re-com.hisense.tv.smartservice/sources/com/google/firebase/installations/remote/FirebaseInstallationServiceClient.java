package com.google.firebase.installations.remote;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.TrafficStats;
import android.text.TextUtils;
import android.util.JsonReader;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.a.a.a.a;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Hex;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.common.net.HttpHeaders;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.FirebaseInstallationsException;
import com.google.firebase.installations.local.IidStore;
import com.google.firebase.platforminfo.UserAgentPublisher;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.google.firebase.storage.network.NetworkRequest;
import e.b.b;
import e.b.d;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: loaded from: classes.dex */
public class FirebaseInstallationServiceClient {
    public static final String ACCEPT_HEADER_KEY = "Accept";
    public static final String API_KEY_HEADER = "x-goog-api-key";
    public static final String CACHE_CONTROL_DIRECTIVE = "no-cache";
    public static final String CACHE_CONTROL_HEADER_KEY = "Cache-Control";
    public static final String CONTENT_ENCODING_HEADER_KEY = "Content-Encoding";
    public static final String CONTENT_TYPE_HEADER_KEY = "Content-Type";
    public static final String CREATE_REQUEST_RESOURCE_NAME_FORMAT = "projects/%s/installations";
    public static final String DELETE_REQUEST_RESOURCE_NAME_FORMAT = "projects/%s/installations/%s";
    public static final String FIREBASE_INSTALLATIONS_API_DOMAIN = "firebaseinstallations.googleapis.com";
    public static final String FIREBASE_INSTALLATIONS_API_VERSION = "v1";
    public static final String FIREBASE_INSTALLATIONS_ID_HEARTBEAT_TAG = "fire-installations-id";
    public static final String FIREBASE_INSTALLATION_AUTH_VERSION = "FIS_v2";
    public static final String FIS_TAG = "Firebase-Installations";
    public static final String GENERATE_AUTH_TOKEN_REQUEST_RESOURCE_NAME_FORMAT = "projects/%s/installations/%s/authTokens:generate";
    public static final String GZIP_CONTENT_ENCODING = "gzip";
    public static final String HEART_BEAT_HEADER = "x-firebase-client-log-type";
    public static final String JSON_CONTENT_TYPE = "application/json";
    public static final int MAX_RETRIES = 1;
    public static final int NETWORK_TIMEOUT_MILLIS = 10000;

    @VisibleForTesting
    public static final String PARSING_EXPIRATION_TIME_ERROR_MESSAGE = "Invalid Expiration Timestamp.";
    public static final String SDK_VERSION_PREFIX = "a:";
    public static final int TRAFFIC_STATS_CREATE_INSTALLATION_TAG = 32769;
    public static final int TRAFFIC_STATS_DELETE_INSTALLATION_TAG = 32770;
    public static final int TRAFFIC_STATS_FIREBASE_INSTALLATIONS_TAG = 32768;
    public static final int TRAFFIC_STATS_GENERATE_AUTH_TOKEN_TAG = 32771;
    public static final String USER_AGENT_HEADER = "x-firebase-client";
    public static final String X_ANDROID_CERT_HEADER_KEY = "X-Android-Cert";
    public static final String X_ANDROID_IID_MIGRATION_KEY = "x-goog-fis-android-iid-migration-auth";
    public static final String X_ANDROID_PACKAGE_HEADER_KEY = "X-Android-Package";
    public final Context context;
    public final Provider<HeartBeatInfo> heartbeatInfo;
    public final RequestLimiter requestLimiter = new RequestLimiter();
    public boolean shouldServerErrorRetry;
    public final Provider<UserAgentPublisher> userAgentPublisher;
    public static final Pattern EXPIRATION_TIMESTAMP_PATTERN = Pattern.compile("[0-9]+s");
    public static final Charset UTF_8 = Charset.forName("UTF-8");

    public FirebaseInstallationServiceClient(@NonNull Context context, @NonNull Provider<UserAgentPublisher> provider, @NonNull Provider<HeartBeatInfo> provider2) {
        this.context = context;
        this.userAgentPublisher = provider;
        this.heartbeatInfo = provider2;
    }

    public static String availableFirebaseOptions(@Nullable String str, @NonNull String str2, @NonNull String str3) {
        Object[] objArr = new Object[3];
        objArr[0] = str2;
        objArr[1] = str3;
        objArr[2] = TextUtils.isEmpty(str) ? "" : a.a(", ", str);
        return String.format("Firebase options used while communicating with Firebase server APIs: %s, %s%s", objArr);
    }

    public static d buildCreateFirebaseInstallationRequestBody(@Nullable String str, @NonNull String str2) {
        try {
            d dVar = new d();
            dVar.a("fid", (Object) str);
            dVar.a(RemoteConfigConstants.RequestFieldKey.APP_ID, (Object) str2);
            dVar.a("authVersion", (Object) FIREBASE_INSTALLATION_AUTH_VERSION);
            dVar.a(RemoteConfigConstants.RequestFieldKey.SDK_VERSION, (Object) "a:17.0.0");
            return dVar;
        } catch (b e2) {
            throw new IllegalStateException(e2);
        }
    }

    public static d buildGenerateAuthTokenRequestBody() {
        try {
            d dVar = new d();
            dVar.a(RemoteConfigConstants.RequestFieldKey.SDK_VERSION, (Object) "a:17.0.0");
            d dVar2 = new d();
            dVar2.a("installation", dVar);
            return dVar2;
        } catch (b e2) {
            throw new IllegalStateException(e2);
        }
    }

    private String getFingerprintHashForPackage() {
        try {
            byte[] packageCertificateHashBytes = AndroidUtilsLight.getPackageCertificateHashBytes(this.context, this.context.getPackageName());
            if (packageCertificateHashBytes != null) {
                return Hex.bytesToStringUppercase(packageCertificateHashBytes, false);
            }
            Log.e("ContentValues", "Could not get fingerprint hash for package: " + this.context.getPackageName());
            return null;
        } catch (PackageManager.NameNotFoundException e2) {
            StringBuilder sbA = a.a("No such package: ");
            sbA.append(this.context.getPackageName());
            Log.e("ContentValues", sbA.toString(), e2);
            return null;
        }
    }

    private URL getFullyQualifiedRequestUri(String str) throws FirebaseInstallationsException {
        try {
            return new URL(String.format("https://%s/%s/%s", FIREBASE_INSTALLATIONS_API_DOMAIN, FIREBASE_INSTALLATIONS_API_VERSION, str));
        } catch (MalformedURLException e2) {
            throw new FirebaseInstallationsException(e2.getMessage(), FirebaseInstallationsException.Status.UNAVAILABLE);
        }
    }

    public static byte[] getJsonBytes(d dVar) {
        return dVar.toString().getBytes("UTF-8");
    }

    public static boolean isSuccessfulResponseCode(int i) {
        return i >= 200 && i < 300;
    }

    public static void logBadConfigError() {
        Log.e(FIS_TAG, "Firebase Installations can not communicate with Firebase server APIs due to invalid configuration. Please update your Firebase initialization process and set valid Firebase options (API key, Project ID, Application ID) when initializing Firebase.");
    }

    public static void logFisCommunicationError(HttpURLConnection httpURLConnection, @Nullable String str, @NonNull String str2, @NonNull String str3) {
        String errorResponse = readErrorResponse(httpURLConnection);
        if (TextUtils.isEmpty(errorResponse)) {
            return;
        }
        Log.w(FIS_TAG, errorResponse);
        Log.w(FIS_TAG, availableFirebaseOptions(str, str2, str3));
    }

    private HttpURLConnection openHttpURLConnection(URL url, String str) throws FirebaseInstallationsException {
        HeartBeatInfo.HeartBeat heartBeatCode;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.addRequestProperty("Content-Type", "application/json");
            httpURLConnection.addRequestProperty("Accept", "application/json");
            httpURLConnection.addRequestProperty("Content-Encoding", GZIP_CONTENT_ENCODING);
            httpURLConnection.addRequestProperty("Cache-Control", CACHE_CONTROL_DIRECTIVE);
            httpURLConnection.addRequestProperty("X-Android-Package", this.context.getPackageName());
            if (this.heartbeatInfo.get() != null && this.userAgentPublisher.get() != null && (heartBeatCode = this.heartbeatInfo.get().getHeartBeatCode(FIREBASE_INSTALLATIONS_ID_HEARTBEAT_TAG)) != HeartBeatInfo.HeartBeat.NONE) {
                httpURLConnection.addRequestProperty(USER_AGENT_HEADER, this.userAgentPublisher.get().getUserAgent());
                httpURLConnection.addRequestProperty(HEART_BEAT_HEADER, Integer.toString(heartBeatCode.getCode()));
            }
            httpURLConnection.addRequestProperty("X-Android-Cert", getFingerprintHashForPackage());
            httpURLConnection.addRequestProperty(API_KEY_HEADER, str);
            return httpURLConnection;
        } catch (IOException unused) {
            throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.", FirebaseInstallationsException.Status.UNAVAILABLE);
        }
    }

    @VisibleForTesting
    public static long parseTokenExpirationTimestamp(String str) {
        Preconditions.checkArgument(EXPIRATION_TIMESTAMP_PATTERN.matcher(str).matches(), PARSING_EXPIRATION_TIME_ERROR_MESSAGE);
        if (str == null || str.length() == 0) {
            return 0L;
        }
        return Long.parseLong(str.substring(0, str.length() - 1));
    }

    private InstallationResponse readCreateResponse(HttpURLConnection httpURLConnection) throws IOException {
        InputStream inputStream = httpURLConnection.getInputStream();
        JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream, UTF_8));
        TokenResult.Builder builder = TokenResult.builder();
        AutoValue_InstallationResponse.Builder builder2 = new AutoValue_InstallationResponse.Builder();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            if (strNextName.equals("name")) {
                builder2.setUri(jsonReader.nextString());
            } else if (strNextName.equals("fid")) {
                builder2.setFid(jsonReader.nextString());
            } else if (strNextName.equals("refreshToken")) {
                builder2.setRefreshToken(jsonReader.nextString());
            } else if (strNextName.equals("authToken")) {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    String strNextName2 = jsonReader.nextName();
                    if (strNextName2.equals(IidStore.JSON_TOKEN_KEY)) {
                        builder.setToken(jsonReader.nextString());
                    } else if (strNextName2.equals("expiresIn")) {
                        builder.setTokenExpirationTimestamp(parseTokenExpirationTimestamp(jsonReader.nextString()));
                    } else {
                        jsonReader.skipValue();
                    }
                }
                builder2.setAuthToken(builder.build());
                jsonReader.endObject();
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        jsonReader.close();
        inputStream.close();
        return builder2.setResponseCode(InstallationResponse.ResponseCode.OK).build();
    }

    @Nullable
    public static String readErrorResponse(HttpURLConnection httpURLConnection) {
        InputStream errorStream = httpURLConnection.getErrorStream();
        if (errorStream == null) {
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorStream, UTF_8));
        try {
            try {
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    sb.append(line);
                    sb.append('\n');
                    return null;
                }
                String str = String.format("Error when communicating with the Firebase Installations server API. HTTP response: [%d %s: %s]", Integer.valueOf(httpURLConnection.getResponseCode()), httpURLConnection.getResponseMessage(), sb);
                try {
                    bufferedReader.close();
                } catch (IOException unused) {
                }
                return str;
            } catch (IOException unused2) {
            }
        } catch (IOException unused3) {
            bufferedReader.close();
            return null;
        } catch (Throwable th) {
            try {
                bufferedReader.close();
            } catch (IOException unused4) {
            }
            throw th;
        }
    }

    private TokenResult readGenerateAuthTokenResponse(HttpURLConnection httpURLConnection) throws IOException {
        InputStream inputStream = httpURLConnection.getInputStream();
        JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream, UTF_8));
        TokenResult.Builder builder = TokenResult.builder();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            if (strNextName.equals(IidStore.JSON_TOKEN_KEY)) {
                builder.setToken(jsonReader.nextString());
            } else if (strNextName.equals("expiresIn")) {
                builder.setTokenExpirationTimestamp(parseTokenExpirationTimestamp(jsonReader.nextString()));
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        jsonReader.close();
        inputStream.close();
        return builder.setResponseCode(TokenResult.ResponseCode.OK).build();
    }

    private void writeFIDCreateRequestBodyToOutputStream(HttpURLConnection httpURLConnection, @Nullable String str, @NonNull String str2) throws IOException {
        writeRequestBodyToOutputStream(httpURLConnection, buildCreateFirebaseInstallationRequestBody(str, str2).toString().getBytes("UTF-8"));
    }

    private void writeGenerateAuthTokenRequestBodyToOutputStream(HttpURLConnection httpURLConnection) throws IOException {
        writeRequestBodyToOutputStream(httpURLConnection, buildGenerateAuthTokenRequestBody().toString().getBytes("UTF-8"));
    }

    public static void writeRequestBodyToOutputStream(URLConnection uRLConnection, byte[] bArr) throws IOException {
        OutputStream outputStream = uRLConnection.getOutputStream();
        if (outputStream == null) {
            throw new IOException("Cannot send request to FIS servers. No OutputStream available.");
        }
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(outputStream);
        try {
            gZIPOutputStream.write(bArr);
        } finally {
            try {
                gZIPOutputStream.close();
                outputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    @NonNull
    public InstallationResponse createFirebaseInstallation(@NonNull String str, @Nullable String str2, @NonNull String str3, @NonNull String str4, @Nullable String str5) {
        InstallationResponse createResponse;
        if (!this.requestLimiter.isRequestAllowed()) {
            throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.", FirebaseInstallationsException.Status.UNAVAILABLE);
        }
        URL fullyQualifiedRequestUri = getFullyQualifiedRequestUri(String.format(CREATE_REQUEST_RESOURCE_NAME_FORMAT, str3));
        for (int i = 0; i <= 1; i++) {
            TrafficStats.setThreadStatsTag(TRAFFIC_STATS_CREATE_INSTALLATION_TAG);
            HttpURLConnection httpURLConnectionOpenHttpURLConnection = openHttpURLConnection(fullyQualifiedRequestUri, str);
            try {
                try {
                    httpURLConnectionOpenHttpURLConnection.setRequestMethod(NetworkRequest.POST);
                    httpURLConnectionOpenHttpURLConnection.setDoOutput(true);
                    if (str5 != null) {
                        httpURLConnectionOpenHttpURLConnection.addRequestProperty(X_ANDROID_IID_MIGRATION_KEY, str5);
                    }
                    writeFIDCreateRequestBodyToOutputStream(httpURLConnectionOpenHttpURLConnection, str2, str4);
                    int responseCode = httpURLConnectionOpenHttpURLConnection.getResponseCode();
                    this.requestLimiter.setNextRequestTime(responseCode);
                    if (isSuccessfulResponseCode(responseCode)) {
                        createResponse = readCreateResponse(httpURLConnectionOpenHttpURLConnection);
                    } else {
                        logFisCommunicationError(httpURLConnectionOpenHttpURLConnection, str4, str, str3);
                        if (responseCode == 429) {
                            throw new FirebaseInstallationsException("Firebase servers have received too many requests from this client in a short period of time. Please try again later.", FirebaseInstallationsException.Status.TOO_MANY_REQUESTS);
                        }
                        if (responseCode < 500 || responseCode >= 600) {
                            logBadConfigError();
                            createResponse = new AutoValue_InstallationResponse.Builder().setResponseCode(InstallationResponse.ResponseCode.BAD_CONFIG).build();
                        } else {
                            httpURLConnectionOpenHttpURLConnection.disconnect();
                            TrafficStats.clearThreadStatsTag();
                        }
                    }
                    httpURLConnectionOpenHttpURLConnection.disconnect();
                    TrafficStats.clearThreadStatsTag();
                    return createResponse;
                } catch (IOException | AssertionError unused) {
                }
            } catch (Throwable th) {
                httpURLConnectionOpenHttpURLConnection.disconnect();
                TrafficStats.clearThreadStatsTag();
                throw th;
            }
        }
        throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.", FirebaseInstallationsException.Status.UNAVAILABLE);
    }

    @NonNull
    public void deleteFirebaseInstallation(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4) {
        int i = 0;
        URL fullyQualifiedRequestUri = getFullyQualifiedRequestUri(String.format(DELETE_REQUEST_RESOURCE_NAME_FORMAT, str3, str2));
        while (i <= 1) {
            TrafficStats.setThreadStatsTag(TRAFFIC_STATS_DELETE_INSTALLATION_TAG);
            HttpURLConnection httpURLConnectionOpenHttpURLConnection = openHttpURLConnection(fullyQualifiedRequestUri, str);
            try {
                httpURLConnectionOpenHttpURLConnection.setRequestMethod(NetworkRequest.DELETE);
                httpURLConnectionOpenHttpURLConnection.addRequestProperty(HttpHeaders.AUTHORIZATION, "FIS_v2 " + str4);
                int responseCode = httpURLConnectionOpenHttpURLConnection.getResponseCode();
                if (responseCode != 200 && responseCode != 401 && responseCode != 404) {
                    logFisCommunicationError(httpURLConnectionOpenHttpURLConnection, null, str, str3);
                    if (responseCode != 429 && (responseCode < 500 || responseCode >= 600)) {
                        logBadConfigError();
                        throw new FirebaseInstallationsException("Bad config while trying to delete FID", FirebaseInstallationsException.Status.BAD_CONFIG);
                    }
                    i++;
                    httpURLConnectionOpenHttpURLConnection.disconnect();
                    TrafficStats.clearThreadStatsTag();
                }
                httpURLConnectionOpenHttpURLConnection.disconnect();
                TrafficStats.clearThreadStatsTag();
                return;
            } catch (IOException unused) {
            } catch (Throwable th) {
                httpURLConnectionOpenHttpURLConnection.disconnect();
                TrafficStats.clearThreadStatsTag();
                throw th;
            }
        }
        throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.", FirebaseInstallationsException.Status.UNAVAILABLE);
    }

    @NonNull
    public TokenResult generateAuthToken(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4) {
        TokenResult generateAuthTokenResponse;
        if (!this.requestLimiter.isRequestAllowed()) {
            throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.", FirebaseInstallationsException.Status.UNAVAILABLE);
        }
        URL fullyQualifiedRequestUri = getFullyQualifiedRequestUri(String.format(GENERATE_AUTH_TOKEN_REQUEST_RESOURCE_NAME_FORMAT, str3, str2));
        for (int i = 0; i <= 1; i++) {
            TrafficStats.setThreadStatsTag(TRAFFIC_STATS_GENERATE_AUTH_TOKEN_TAG);
            HttpURLConnection httpURLConnectionOpenHttpURLConnection = openHttpURLConnection(fullyQualifiedRequestUri, str);
            try {
                try {
                    httpURLConnectionOpenHttpURLConnection.setRequestMethod(NetworkRequest.POST);
                    httpURLConnectionOpenHttpURLConnection.addRequestProperty(HttpHeaders.AUTHORIZATION, "FIS_v2 " + str4);
                    httpURLConnectionOpenHttpURLConnection.setDoOutput(true);
                    writeGenerateAuthTokenRequestBodyToOutputStream(httpURLConnectionOpenHttpURLConnection);
                    int responseCode = httpURLConnectionOpenHttpURLConnection.getResponseCode();
                    this.requestLimiter.setNextRequestTime(responseCode);
                    if (isSuccessfulResponseCode(responseCode)) {
                        generateAuthTokenResponse = readGenerateAuthTokenResponse(httpURLConnectionOpenHttpURLConnection);
                    } else {
                        logFisCommunicationError(httpURLConnectionOpenHttpURLConnection, null, str, str3);
                        if (responseCode == 401 || responseCode == 404) {
                            generateAuthTokenResponse = TokenResult.builder().setResponseCode(TokenResult.ResponseCode.AUTH_ERROR).build();
                        } else {
                            if (responseCode == 429) {
                                throw new FirebaseInstallationsException("Firebase servers have received too many requests from this client in a short period of time. Please try again later.", FirebaseInstallationsException.Status.TOO_MANY_REQUESTS);
                            }
                            if (responseCode < 500 || responseCode >= 600) {
                                logBadConfigError();
                                generateAuthTokenResponse = TokenResult.builder().setResponseCode(TokenResult.ResponseCode.BAD_CONFIG).build();
                            } else {
                                httpURLConnectionOpenHttpURLConnection.disconnect();
                                TrafficStats.clearThreadStatsTag();
                            }
                        }
                    }
                    httpURLConnectionOpenHttpURLConnection.disconnect();
                    TrafficStats.clearThreadStatsTag();
                    return generateAuthTokenResponse;
                } catch (Throwable th) {
                    httpURLConnectionOpenHttpURLConnection.disconnect();
                    TrafficStats.clearThreadStatsTag();
                    throw th;
                }
            } catch (IOException | AssertionError unused) {
            }
        }
        throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.", FirebaseInstallationsException.Status.UNAVAILABLE);
    }
}
