package com.google.firebase.installations.local;

import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.stats.CodePackage;
import com.google.common.base.Ascii;
import com.google.firebase.FirebaseApp;
import com.google.firebase.installations.Utils;
import e.b.b;
import e.b.d;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/* JADX INFO: loaded from: classes.dex */
public class IidStore {
    public static final String[] ALLOWABLE_SCOPES = {"*", "FCM", CodePackage.GCM, ""};
    public static final String IID_SHARED_PREFS_NAME = "com.google.android.gms.appid";
    public static final String JSON_ENCODED_PREFIX = "{";
    public static final String JSON_TOKEN_KEY = "token";
    public static final String STORE_KEY_ID = "|S|id";
    public static final String STORE_KEY_PUB = "|S||P|";
    public static final String STORE_KEY_SEPARATOR = "|";
    public static final String STORE_KEY_TOKEN = "|T|";
    public final String defaultSenderId;

    @GuardedBy("iidPrefs")
    public final SharedPreferences iidPrefs;

    public IidStore(@NonNull FirebaseApp firebaseApp) {
        this.iidPrefs = firebaseApp.getApplicationContext().getSharedPreferences(IID_SHARED_PREFS_NAME, 0);
        this.defaultSenderId = getDefaultSenderId(firebaseApp);
    }

    private String createTokenKey(@NonNull String str, @NonNull String str2) {
        return STORE_KEY_TOKEN + str + "|" + str2;
    }

    public static String getDefaultSenderId(FirebaseApp firebaseApp) {
        String gcmSenderId = firebaseApp.getOptions().getGcmSenderId();
        if (gcmSenderId != null) {
            return gcmSenderId;
        }
        String applicationId = firebaseApp.getOptions().getApplicationId();
        if (!applicationId.startsWith("1:") && !applicationId.startsWith("2:")) {
            return applicationId;
        }
        String[] strArrSplit = applicationId.split(Utils.APP_ID_IDENTIFICATION_SUBSTRING);
        if (strArrSplit.length != 4) {
            return null;
        }
        String str = strArrSplit[1];
        if (str.isEmpty()) {
            return null;
        }
        return str;
    }

    @Nullable
    public static String getIdFromPublicKey(@NonNull PublicKey publicKey) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance("SHA1").digest(publicKey.getEncoded());
            bArrDigest[0] = (byte) (((bArrDigest[0] & Ascii.SI) + 112) & 255);
            return Base64.encodeToString(bArrDigest, 0, 8, 11);
        } catch (NoSuchAlgorithmException unused) {
            Log.w("ContentValues", "Unexpected error, device missing required algorithms");
            return null;
        }
    }

    private String parseIidTokenFromJson(String str) {
        try {
            return new d(str).h(JSON_TOKEN_KEY);
        } catch (b unused) {
            return null;
        }
    }

    @Nullable
    private PublicKey parseKey(String str) {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str, 8)));
        } catch (IllegalArgumentException | NoSuchAlgorithmException | InvalidKeySpecException e2) {
            Log.w("ContentValues", "Invalid key stored " + e2);
            return null;
        }
    }

    @Nullable
    private String readInstanceIdFromLocalStorage() {
        String string;
        synchronized (this.iidPrefs) {
            string = this.iidPrefs.getString(STORE_KEY_ID, null);
        }
        return string;
    }

    @Nullable
    private String readPublicKeyFromLocalStorageAndCalculateInstanceId() {
        synchronized (this.iidPrefs) {
            String string = this.iidPrefs.getString(STORE_KEY_PUB, null);
            if (string == null) {
                return null;
            }
            PublicKey key = parseKey(string);
            if (key == null) {
                return null;
            }
            return getIdFromPublicKey(key);
        }
    }

    @Nullable
    public String readIid() {
        synchronized (this.iidPrefs) {
            String instanceIdFromLocalStorage = readInstanceIdFromLocalStorage();
            if (instanceIdFromLocalStorage != null) {
                return instanceIdFromLocalStorage;
            }
            return readPublicKeyFromLocalStorageAndCalculateInstanceId();
        }
    }

    @Nullable
    public String readToken() {
        synchronized (this.iidPrefs) {
            for (String str : ALLOWABLE_SCOPES) {
                String string = this.iidPrefs.getString(createTokenKey(this.defaultSenderId, str), null);
                if (string != null && !string.isEmpty()) {
                    if (string.startsWith(JSON_ENCODED_PREFIX)) {
                        string = parseIidTokenFromJson(string);
                    }
                    return string;
                }
            }
            return null;
        }
    }

    @VisibleForTesting
    public IidStore(@NonNull SharedPreferences sharedPreferences, @Nullable String str) {
        this.iidPrefs = sharedPreferences;
        this.defaultSenderId = str;
    }
}
