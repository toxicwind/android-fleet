package com.google.firebase.remoteconfig.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import androidx.annotation.Keep;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.pm.PackageInfoCompat;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Hex;
import com.google.firebase.remoteconfig.BuildConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigClientException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigServerException;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import e.b.a;
import e.b.b;
import e.b.d;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class ConfigFetchHttpClient {
    public static final String API_KEY_HEADER = "X-Goog-Api-Key";
    public static final String ETAG_HEADER = "ETag";
    public static final Pattern GMP_APP_ID_PATTERN = Pattern.compile("^[^:]+:([0-9]+):(android|ios|web):([0-9a-f]+)");
    public static final String IF_NONE_MATCH_HEADER = "If-None-Match";
    public static final String INSTALLATIONS_AUTH_TOKEN_HEADER = "X-Goog-Firebase-Installations-Auth";
    public static final String X_ANDROID_CERT_HEADER = "X-Android-Cert";
    public static final String X_ANDROID_PACKAGE_HEADER = "X-Android-Package";
    public static final String X_GOOGLE_GFE_CAN_RETRY = "X-Google-GFE-Can-Retry";
    public final String apiKey;
    public final String appId;
    public final long connectTimeoutInSeconds;
    public final Context context;
    public final String namespace;
    public final String projectNumber;
    public final long readTimeoutInSeconds;

    public ConfigFetchHttpClient(Context context, String str, String str2, String str3, long j, long j2) {
        this.context = context;
        this.appId = str;
        this.apiKey = str2;
        this.projectNumber = extractProjectNumberFromAppId(str);
        this.namespace = str3;
        this.connectTimeoutInSeconds = j;
        this.readTimeoutInSeconds = j2;
    }

    private boolean backendHasUpdates(d dVar) {
        try {
            return !dVar.a(RemoteConfigConstants.ResponseFieldKey.STATE).equals("NO_CHANGE");
        } catch (b unused) {
            return true;
        }
    }

    private d createFetchRequestBody(String str, String str2, Map<String, String> map) throws FirebaseRemoteConfigClientException {
        HashMap map2 = new HashMap();
        if (str == null) {
            throw new FirebaseRemoteConfigClientException("Fetch failed: Firebase installation id is null.");
        }
        map2.put(RemoteConfigConstants.RequestFieldKey.INSTANCE_ID, str);
        map2.put(RemoteConfigConstants.RequestFieldKey.INSTANCE_ID_TOKEN, str2);
        map2.put(RemoteConfigConstants.RequestFieldKey.APP_ID, this.appId);
        Locale locale = this.context.getResources().getConfiguration().locale;
        map2.put(RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, locale.getCountry());
        int i = Build.VERSION.SDK_INT;
        map2.put(RemoteConfigConstants.RequestFieldKey.LANGUAGE_CODE, locale.toLanguageTag());
        map2.put(RemoteConfigConstants.RequestFieldKey.PLATFORM_VERSION, Integer.toString(Build.VERSION.SDK_INT));
        map2.put(RemoteConfigConstants.RequestFieldKey.TIME_ZONE, TimeZone.getDefault().getID());
        try {
            PackageInfo packageInfo = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0);
            if (packageInfo != null) {
                map2.put("appVersion", packageInfo.versionName);
                map2.put(RemoteConfigConstants.RequestFieldKey.APP_BUILD, Long.toString(PackageInfoCompat.getLongVersionCode(packageInfo)));
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        map2.put(RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME, this.context.getPackageName());
        map2.put(RemoteConfigConstants.RequestFieldKey.SDK_VERSION, BuildConfig.VERSION_NAME);
        map2.put(RemoteConfigConstants.RequestFieldKey.ANALYTICS_USER_PROPERTIES, new d((Map<?, ?>) map));
        return new d((Map<?, ?>) map2);
    }

    public static ConfigContainer extractConfigs(d dVar, Date date) throws FirebaseRemoteConfigClientException {
        d dVarF;
        a aVarE;
        try {
            ConfigContainer.Builder builderWithFetchTime = ConfigContainer.newBuilder().withFetchTime(date);
            d dVarF2 = null;
            try {
                dVarF = dVar.f(RemoteConfigConstants.ResponseFieldKey.ENTRIES);
            } catch (b unused) {
                dVarF = null;
            }
            if (dVarF != null) {
                builderWithFetchTime.replaceConfigsWith(dVarF);
            }
            try {
                aVarE = dVar.e(RemoteConfigConstants.ResponseFieldKey.EXPERIMENT_DESCRIPTIONS);
            } catch (b unused2) {
                aVarE = null;
            }
            if (aVarE != null) {
                builderWithFetchTime.withAbtExperiments(aVarE);
            }
            try {
                dVarF2 = dVar.f(RemoteConfigConstants.ResponseFieldKey.PERSONALIZATION_METADATA);
            } catch (b unused3) {
            }
            if (dVarF2 != null) {
                builderWithFetchTime.withPersonalizationMetadata(dVarF2);
            }
            return builderWithFetchTime.build();
        } catch (b e2) {
            throw new FirebaseRemoteConfigClientException("Fetch failed: fetch response could not be parsed.", e2);
        }
    }

    public static String extractProjectNumberFromAppId(String str) {
        Matcher matcher = GMP_APP_ID_PATTERN.matcher(str);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        return null;
    }

    private d getFetchResponseBody(URLConnection uRLConnection) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(uRLConnection.getInputStream(), "utf-8"));
        StringBuilder sb = new StringBuilder();
        while (true) {
            int i = bufferedReader.read();
            if (i == -1) {
                return new d(sb.toString());
            }
            sb.append((char) i);
        }
    }

    private String getFetchUrl(String str, String str2) {
        return String.format(RemoteConfigConstants.FETCH_REGEX_URL, str, str2);
    }

    private String getFingerprintHashForPackage() {
        try {
            byte[] packageCertificateHashBytes = AndroidUtilsLight.getPackageCertificateHashBytes(this.context, this.context.getPackageName());
            if (packageCertificateHashBytes != null) {
                return Hex.bytesToStringUppercase(packageCertificateHashBytes, false);
            }
            Log.e(FirebaseRemoteConfig.TAG, "Could not get fingerprint hash for package: " + this.context.getPackageName());
            return null;
        } catch (PackageManager.NameNotFoundException e2) {
            StringBuilder sbA = b.a.a.a.a.a("No such package: ");
            sbA.append(this.context.getPackageName());
            Log.e(FirebaseRemoteConfig.TAG, sbA.toString(), e2);
            return null;
        }
    }

    private void setCommonRequestHeaders(HttpURLConnection httpURLConnection, String str) {
        httpURLConnection.setRequestProperty(API_KEY_HEADER, this.apiKey);
        httpURLConnection.setRequestProperty("X-Android-Package", this.context.getPackageName());
        httpURLConnection.setRequestProperty("X-Android-Cert", getFingerprintHashForPackage());
        httpURLConnection.setRequestProperty(X_GOOGLE_GFE_CAN_RETRY, "yes");
        httpURLConnection.setRequestProperty(INSTALLATIONS_AUTH_TOKEN_HEADER, str);
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setRequestProperty("Accept", "application/json");
    }

    private void setCustomRequestHeaders(HttpURLConnection httpURLConnection, Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
        }
    }

    private void setFetchRequestBody(HttpURLConnection httpURLConnection, byte[] bArr) throws IOException {
        httpURLConnection.setFixedLengthStreamingMode(bArr.length);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
        bufferedOutputStream.write(bArr);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }

    private void setUpUrlConnection(HttpURLConnection httpURLConnection, String str, String str2, Map<String, String> map) {
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setConnectTimeout((int) TimeUnit.SECONDS.toMillis(this.connectTimeoutInSeconds));
        httpURLConnection.setReadTimeout((int) TimeUnit.SECONDS.toMillis(this.readTimeoutInSeconds));
        httpURLConnection.setRequestProperty("If-None-Match", str);
        setCommonRequestHeaders(httpURLConnection, str2);
        setCustomRequestHeaders(httpURLConnection, map);
    }

    public HttpURLConnection createHttpURLConnection() {
        try {
            return (HttpURLConnection) new URL(getFetchUrl(this.projectNumber, this.namespace)).openConnection();
        } catch (IOException e2) {
            throw new FirebaseRemoteConfigException(e2.getMessage());
        }
    }

    @Keep
    public ConfigFetchHandler.FetchResponse fetch(HttpURLConnection httpURLConnection, String str, String str2, Map<String, String> map, String str3, Map<String, String> map2, Date date) {
        setUpUrlConnection(httpURLConnection, str3, str2, map2);
        try {
            try {
                setFetchRequestBody(httpURLConnection, createFetchRequestBody(str, str2, map).toString().getBytes("utf-8"));
                httpURLConnection.connect();
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode != 200) {
                    throw new FirebaseRemoteConfigServerException(responseCode, httpURLConnection.getResponseMessage());
                }
                String headerField = httpURLConnection.getHeaderField("ETag");
                d fetchResponseBody = getFetchResponseBody(httpURLConnection);
                httpURLConnection.disconnect();
                try {
                    httpURLConnection.getInputStream().close();
                } catch (IOException unused) {
                }
                return !backendHasUpdates(fetchResponseBody) ? ConfigFetchHandler.FetchResponse.forBackendHasNoUpdates(date) : ConfigFetchHandler.FetchResponse.forBackendUpdatesFetched(extractConfigs(fetchResponseBody, date), headerField);
            } catch (Throwable th) {
                httpURLConnection.disconnect();
                try {
                    httpURLConnection.getInputStream().close();
                } catch (IOException unused2) {
                }
                throw th;
            }
        } catch (b | IOException e2) {
            throw new FirebaseRemoteConfigClientException("The client had an error while calling the backend!", e2);
        }
    }

    @VisibleForTesting
    public long getConnectTimeoutInSeconds() {
        return this.connectTimeoutInSeconds;
    }

    @VisibleForTesting
    public long getReadTimeoutInSeconds() {
        return this.readTimeoutInSeconds;
    }
}
