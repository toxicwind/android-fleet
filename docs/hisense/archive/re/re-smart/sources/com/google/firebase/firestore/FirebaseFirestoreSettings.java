package com.google.firebase.firestore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.a.a.a.a;
import com.google.firebase.firestore.util.Preconditions;

/* JADX INFO: loaded from: classes.dex */
public final class FirebaseFirestoreSettings {
    public static final long CACHE_SIZE_UNLIMITED = -1;
    public static final long DEFAULT_CACHE_SIZE_BYTES = 104857600;
    public static final String DEFAULT_HOST = "firestore.googleapis.com";
    public static final long MINIMUM_CACHE_BYTES = 1048576;
    public final long cacheSizeBytes;
    public final String host;
    public final boolean persistenceEnabled;
    public final boolean sslEnabled;

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || FirebaseFirestoreSettings.class != obj.getClass()) {
            return false;
        }
        FirebaseFirestoreSettings firebaseFirestoreSettings = (FirebaseFirestoreSettings) obj;
        return this.host.equals(firebaseFirestoreSettings.host) && this.sslEnabled == firebaseFirestoreSettings.sslEnabled && this.persistenceEnabled == firebaseFirestoreSettings.persistenceEnabled && this.cacheSizeBytes == firebaseFirestoreSettings.cacheSizeBytes;
    }

    public long getCacheSizeBytes() {
        return this.cacheSizeBytes;
    }

    @NonNull
    public String getHost() {
        return this.host;
    }

    public int hashCode() {
        return (((((this.host.hashCode() * 31) + (this.sslEnabled ? 1 : 0)) * 31) + (this.persistenceEnabled ? 1 : 0)) * 31) + ((int) this.cacheSizeBytes);
    }

    public boolean isPersistenceEnabled() {
        return this.persistenceEnabled;
    }

    public boolean isSslEnabled() {
        return this.sslEnabled;
    }

    @NonNull
    public String toString() {
        StringBuilder sbA = a.a("FirebaseFirestoreSettings{host=");
        sbA.append(this.host);
        sbA.append(", sslEnabled=");
        sbA.append(this.sslEnabled);
        sbA.append(", persistenceEnabled=");
        sbA.append(this.persistenceEnabled);
        sbA.append(", cacheSizeBytes=");
        sbA.append(this.cacheSizeBytes);
        sbA.append("}");
        return sbA.toString();
    }

    public FirebaseFirestoreSettings(Builder builder) {
        this.host = builder.host;
        this.sslEnabled = builder.sslEnabled;
        this.persistenceEnabled = builder.persistenceEnabled;
        this.cacheSizeBytes = builder.cacheSizeBytes;
    }

    public static final class Builder {
        public long cacheSizeBytes;
        public String host;
        public boolean persistenceEnabled;
        public boolean sslEnabled;

        public Builder() {
            this.host = FirebaseFirestoreSettings.DEFAULT_HOST;
            this.sslEnabled = true;
            this.persistenceEnabled = true;
            this.cacheSizeBytes = 104857600L;
        }

        @NonNull
        public FirebaseFirestoreSettings build() {
            if (this.sslEnabled || !this.host.equals(FirebaseFirestoreSettings.DEFAULT_HOST)) {
                return new FirebaseFirestoreSettings(this);
            }
            throw new IllegalStateException("You can't set the 'sslEnabled' setting unless you also set a non-default 'host'.");
        }

        public long getCacheSizeBytes() {
            return this.cacheSizeBytes;
        }

        @NonNull
        public String getHost() {
            return this.host;
        }

        public boolean isPersistenceEnabled() {
            return this.persistenceEnabled;
        }

        public boolean isSslEnabled() {
            return this.sslEnabled;
        }

        @NonNull
        public Builder setCacheSizeBytes(long j) {
            if (j != -1 && j < FirebaseFirestoreSettings.MINIMUM_CACHE_BYTES) {
                throw new IllegalArgumentException("Cache size must be set to at least 1048576 bytes");
            }
            this.cacheSizeBytes = j;
            return this;
        }

        @NonNull
        public Builder setHost(@NonNull String str) {
            Preconditions.checkNotNull(str, "Provided host must not be null.");
            this.host = str;
            return this;
        }

        @NonNull
        public Builder setPersistenceEnabled(boolean z) {
            this.persistenceEnabled = z;
            return this;
        }

        @NonNull
        public Builder setSslEnabled(boolean z) {
            this.sslEnabled = z;
            return this;
        }

        public Builder(@NonNull FirebaseFirestoreSettings firebaseFirestoreSettings) {
            Preconditions.checkNotNull(firebaseFirestoreSettings, "Provided settings must not be null.");
            this.host = firebaseFirestoreSettings.host;
            this.sslEnabled = firebaseFirestoreSettings.sslEnabled;
            this.persistenceEnabled = firebaseFirestoreSettings.persistenceEnabled;
            this.cacheSizeBytes = firebaseFirestoreSettings.cacheSizeBytes;
        }
    }
}
