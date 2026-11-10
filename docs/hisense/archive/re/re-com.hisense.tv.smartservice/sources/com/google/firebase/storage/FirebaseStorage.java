package com.google.firebase.storage;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.work.impl.background.systemalarm.CommandHandler;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.AppCheckTokenResult;
import com.google.firebase.appcheck.interop.AppCheckTokenListener;
import com.google.firebase.appcheck.interop.InternalAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.emulators.EmulatedServiceSettings;
import com.google.firebase.inject.Provider;
import com.google.firebase.storage.internal.Util;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: classes.dex */
public class FirebaseStorage {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String STORAGE_BUCKET_WITH_PATH_EXCEPTION = "The storage Uri cannot contain a path element.";
    public static final String STORAGE_URI_PARSE_EXCEPTION = "The storage Uri could not be parsed.";
    public static final String TAG = "FirebaseStorage";

    @Nullable
    public EmulatedServiceSettings emulatorSettings;

    @NonNull
    public final FirebaseApp mApp;

    @Nullable
    public final Provider<InternalAppCheckTokenProvider> mAppCheckProvider;

    @Nullable
    public final Provider<InternalAuthProvider> mAuthProvider;

    @Nullable
    public final String mBucketName;
    public long sMaxUploadRetry = CommandHandler.WORK_PROCESSING_TIME_IN_MS;
    public long sMaxDownloadRetry = CommandHandler.WORK_PROCESSING_TIME_IN_MS;
    public long sMaxQueryRetry = 120000;

    public FirebaseStorage(@Nullable String str, @NonNull FirebaseApp firebaseApp, @Nullable Provider<InternalAuthProvider> provider, @Nullable Provider<InternalAppCheckTokenProvider> provider2) {
        this.mBucketName = str;
        this.mApp = firebaseApp;
        this.mAuthProvider = provider;
        this.mAppCheckProvider = provider2;
        Provider<InternalAppCheckTokenProvider> provider3 = this.mAppCheckProvider;
        if (provider3 == null || provider3.get() == null) {
            return;
        }
        this.mAppCheckProvider.get().addAppCheckTokenListener(new AppCheckTokenListener() { // from class: com.google.firebase.storage.FirebaseStorage.1
            @Override // com.google.firebase.appcheck.interop.AppCheckTokenListener
            public void onAppCheckTokenChanged(@NonNull AppCheckTokenResult appCheckTokenResult) {
            }
        });
    }

    @Nullable
    private String getBucketName() {
        return this.mBucketName;
    }

    @NonNull
    public static FirebaseStorage getInstance() {
        FirebaseApp firebaseApp = FirebaseApp.getInstance();
        Preconditions.checkArgument(firebaseApp != null, "You must call FirebaseApp.initialize() first.");
        return getInstance(firebaseApp);
    }

    public static FirebaseStorage getInstanceImpl(@NonNull FirebaseApp firebaseApp, @Nullable Uri uri) {
        String host = uri != null ? uri.getHost() : null;
        if (uri != null && !TextUtils.isEmpty(uri.getPath())) {
            throw new IllegalArgumentException(STORAGE_BUCKET_WITH_PATH_EXCEPTION);
        }
        Preconditions.checkNotNull(firebaseApp, "Provided FirebaseApp must not be null.");
        FirebaseStorageComponent firebaseStorageComponent = (FirebaseStorageComponent) firebaseApp.get(FirebaseStorageComponent.class);
        Preconditions.checkNotNull(firebaseStorageComponent, "Firebase Storage component is not present.");
        return firebaseStorageComponent.get(host);
    }

    @NonNull
    public FirebaseApp getApp() {
        return this.mApp;
    }

    @Nullable
    public InternalAppCheckTokenProvider getAppCheckProvider() {
        Provider<InternalAppCheckTokenProvider> provider = this.mAppCheckProvider;
        if (provider != null) {
            return provider.get();
        }
        return null;
    }

    @Nullable
    public InternalAuthProvider getAuthProvider() {
        Provider<InternalAuthProvider> provider = this.mAuthProvider;
        if (provider != null) {
            return provider.get();
        }
        return null;
    }

    @Nullable
    public EmulatedServiceSettings getEmulatorSettings() {
        return this.emulatorSettings;
    }

    public long getMaxDownloadRetryTimeMillis() {
        return this.sMaxDownloadRetry;
    }

    public long getMaxOperationRetryTimeMillis() {
        return this.sMaxQueryRetry;
    }

    public long getMaxUploadRetryTimeMillis() {
        return this.sMaxUploadRetry;
    }

    @NonNull
    public StorageReference getReference() {
        if (TextUtils.isEmpty(this.mBucketName)) {
            throw new IllegalStateException("FirebaseApp was not initialized with a bucket name.");
        }
        return getReference(new Uri.Builder().scheme("gs").authority(this.mBucketName).path("/").build());
    }

    @NonNull
    public StorageReference getReferenceFromUrl(@NonNull String str) {
        Preconditions.checkArgument(!TextUtils.isEmpty(str), "location must not be null or empty");
        String lowerCase = str.toLowerCase();
        if (!lowerCase.startsWith("gs://") && !lowerCase.startsWith("https://") && !lowerCase.startsWith("http://")) {
            throw new IllegalArgumentException(STORAGE_URI_PARSE_EXCEPTION);
        }
        try {
            Uri uriNormalize = Util.normalize(this.mApp, str);
            if (uriNormalize != null) {
                return getReference(uriNormalize);
            }
            throw new IllegalArgumentException(STORAGE_URI_PARSE_EXCEPTION);
        } catch (UnsupportedEncodingException e2) {
            Log.e(TAG, "Unable to parse location:" + str, e2);
            throw new IllegalArgumentException(STORAGE_URI_PARSE_EXCEPTION);
        }
    }

    public void setMaxDownloadRetryTimeMillis(long j) {
        this.sMaxDownloadRetry = j;
    }

    public void setMaxOperationRetryTimeMillis(long j) {
        this.sMaxQueryRetry = j;
    }

    public void setMaxUploadRetryTimeMillis(long j) {
        this.sMaxUploadRetry = j;
    }

    public void useEmulator(@NonNull String str, int i) {
        this.emulatorSettings = new EmulatedServiceSettings(str, i);
    }

    @NonNull
    public static FirebaseStorage getInstance(@NonNull String str) {
        FirebaseApp firebaseApp = FirebaseApp.getInstance();
        Preconditions.checkArgument(firebaseApp != null, "You must call FirebaseApp.initialize() first.");
        return getInstance(firebaseApp, str);
    }

    @NonNull
    public static FirebaseStorage getInstance(@NonNull FirebaseApp firebaseApp) {
        Preconditions.checkArgument(firebaseApp != null, "Null is not a valid value for the FirebaseApp.");
        String storageBucket = firebaseApp.getOptions().getStorageBucket();
        if (storageBucket == null) {
            return getInstanceImpl(firebaseApp, null);
        }
        try {
            return getInstanceImpl(firebaseApp, Util.normalize(firebaseApp, "gs://" + firebaseApp.getOptions().getStorageBucket()));
        } catch (UnsupportedEncodingException e2) {
            Log.e(TAG, "Unable to parse bucket:" + storageBucket, e2);
            throw new IllegalArgumentException(STORAGE_URI_PARSE_EXCEPTION);
        }
    }

    @NonNull
    public StorageReference getReference(@NonNull String str) {
        Preconditions.checkArgument(!TextUtils.isEmpty(str), "location must not be null or empty");
        String lowerCase = str.toLowerCase();
        if (!lowerCase.startsWith("gs://") && !lowerCase.startsWith("https://") && !lowerCase.startsWith("http://")) {
            return getReference().child(str);
        }
        throw new IllegalArgumentException("location should not be a full URL.");
    }

    @NonNull
    public static FirebaseStorage getInstance(@NonNull FirebaseApp firebaseApp, @NonNull String str) {
        Preconditions.checkArgument(firebaseApp != null, "Null is not a valid value for the FirebaseApp.");
        Preconditions.checkArgument(str != null, "Null is not a valid value for the Firebase Storage URL.");
        if (str.toLowerCase().startsWith("gs://")) {
            try {
                return getInstanceImpl(firebaseApp, Util.normalize(firebaseApp, str));
            } catch (UnsupportedEncodingException e2) {
                Log.e(TAG, "Unable to parse url:" + str, e2);
                throw new IllegalArgumentException(STORAGE_URI_PARSE_EXCEPTION);
            }
        }
        throw new IllegalArgumentException("Please use a gs:// URL for your Firebase Storage bucket.");
    }

    @NonNull
    private StorageReference getReference(@NonNull Uri uri) {
        Preconditions.checkNotNull(uri, "uri must not be null");
        String str = this.mBucketName;
        Preconditions.checkArgument(TextUtils.isEmpty(str) || uri.getAuthority().equalsIgnoreCase(str), "The supplied bucketname does not match the storage bucket of the current instance.");
        return new StorageReference(uri, this);
    }
}
