package com.google.firebase.installations.local;

import androidx.annotation.NonNull;
import b.a.a.a.a;
import com.google.firebase.FirebaseApp;
import e.b.b;
import e.b.d;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class PersistedInstallation {
    public static final String AUTH_TOKEN_KEY = "AuthToken";
    public static final String EXPIRES_IN_SECONDS_KEY = "ExpiresInSecs";
    public static final String FIREBASE_INSTALLATION_ID_KEY = "Fid";
    public static final String FIS_ERROR_KEY = "FisError";
    public static final String PERSISTED_STATUS_KEY = "Status";
    public static final String REFRESH_TOKEN_KEY = "RefreshToken";
    public static final String SETTINGS_FILE_NAME_PREFIX = "PersistedInstallation";
    public static final String TOKEN_CREATION_TIME_IN_SECONDS_KEY = "TokenCreationEpochInSecs";
    public final File dataFile;

    @NonNull
    public final FirebaseApp firebaseApp;

    public enum RegistrationStatus {
        ATTEMPT_MIGRATION,
        NOT_GENERATED,
        UNREGISTERED,
        REGISTERED,
        REGISTER_ERROR
    }

    public PersistedInstallation(@NonNull FirebaseApp firebaseApp) {
        File filesDir = firebaseApp.getApplicationContext().getFilesDir();
        StringBuilder sbA = a.a("PersistedInstallation.");
        sbA.append(firebaseApp.getPersistenceKey());
        sbA.append(".json");
        this.dataFile = new File(filesDir, sbA.toString());
        this.firebaseApp = firebaseApp;
    }

    private d readJSONFromFile() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[16384];
        try {
            FileInputStream fileInputStream = new FileInputStream(this.dataFile);
            while (true) {
                try {
                    int i = fileInputStream.read(bArr, 0, bArr.length);
                    if (i < 0) {
                        d dVar = new d(byteArrayOutputStream.toString());
                        fileInputStream.close();
                        return dVar;
                    }
                    byteArrayOutputStream.write(bArr, 0, i);
                } catch (Throwable th) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            }
        } catch (b | IOException unused) {
            return new d();
        }
    }

    public void clearForTesting() {
        this.dataFile.delete();
    }

    @NonNull
    public PersistedInstallationEntry insertOrUpdatePersistedInstallationEntry(@NonNull PersistedInstallationEntry persistedInstallationEntry) {
        try {
            d dVar = new d();
            dVar.a(FIREBASE_INSTALLATION_ID_KEY, (Object) persistedInstallationEntry.getFirebaseInstallationId());
            dVar.b(PERSISTED_STATUS_KEY, persistedInstallationEntry.getRegistrationStatus().ordinal());
            dVar.a(AUTH_TOKEN_KEY, (Object) persistedInstallationEntry.getAuthToken());
            dVar.a(REFRESH_TOKEN_KEY, (Object) persistedInstallationEntry.getRefreshToken());
            dVar.b(TOKEN_CREATION_TIME_IN_SECONDS_KEY, persistedInstallationEntry.getTokenCreationEpochInSecs());
            dVar.b(EXPIRES_IN_SECONDS_KEY, persistedInstallationEntry.getExpiresInSecs());
            dVar.a(FIS_ERROR_KEY, (Object) persistedInstallationEntry.getFisError());
            File fileCreateTempFile = File.createTempFile(SETTINGS_FILE_NAME_PREFIX, "tmp", this.firebaseApp.getApplicationContext().getFilesDir());
            FileOutputStream fileOutputStream = new FileOutputStream(fileCreateTempFile);
            fileOutputStream.write(dVar.toString().getBytes("UTF-8"));
            fileOutputStream.close();
            if (!fileCreateTempFile.renameTo(this.dataFile)) {
                throw new IOException("unable to rename the tmpfile to PersistedInstallation");
            }
        } catch (b | IOException unused) {
        }
        return persistedInstallationEntry;
    }

    @NonNull
    public PersistedInstallationEntry readPersistedInstallationEntryValue() {
        d jSONFromFile = readJSONFromFile();
        String strA = jSONFromFile.a(FIREBASE_INSTALLATION_ID_KEY, (String) null);
        int iA = jSONFromFile.a(PERSISTED_STATUS_KEY, RegistrationStatus.ATTEMPT_MIGRATION.ordinal());
        String strA2 = jSONFromFile.a(AUTH_TOKEN_KEY, (String) null);
        String strA3 = jSONFromFile.a(REFRESH_TOKEN_KEY, (String) null);
        long jA = jSONFromFile.a(TOKEN_CREATION_TIME_IN_SECONDS_KEY, 0L);
        long jA2 = jSONFromFile.a(EXPIRES_IN_SECONDS_KEY, 0L);
        return PersistedInstallationEntry.builder().setFirebaseInstallationId(strA).setRegistrationStatus(RegistrationStatus.values()[iA]).setAuthToken(strA2).setRefreshToken(strA3).setTokenCreationEpochInSecs(jA).setExpiresInSecs(jA2).setFisError(jSONFromFile.a(FIS_ERROR_KEY, (String) null)).build();
    }
}
