package com.google.firebase.storage;

import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.storage.internal.Slashes;
import com.google.firebase.storage.internal.Util;
import e.b.d;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class StorageMetadata {

    @NonNull
    public static final String BUCKET_KEY = "bucket";

    @NonNull
    public static final String CACHE_CONTROL = "cacheControl";

    @NonNull
    public static final String CONTENT_DISPOSITION = "contentDisposition";

    @NonNull
    public static final String CONTENT_ENCODING = "contentEncoding";

    @NonNull
    public static final String CONTENT_LANGUAGE = "contentLanguage";

    @NonNull
    public static final String CONTENT_TYPE_KEY = "contentType";

    @NonNull
    public static final String CUSTOM_METADATA_KEY = "metadata";

    @NonNull
    public static final String GENERATION_KEY = "generation";

    @NonNull
    public static final String MD5_HASH_KEY = "md5Hash";

    @NonNull
    public static final String META_GENERATION_KEY = "metageneration";

    @NonNull
    public static final String NAME_KEY = "name";

    @NonNull
    public static final String SIZE_KEY = "size";
    public static final String TAG = "StorageMetadata";

    @NonNull
    public static final String TIME_CREATED_KEY = "timeCreated";

    @NonNull
    public static final String TIME_UPDATED_KEY = "updated";
    public String mBucket;
    public MetadataValue<String> mCacheControl;
    public MetadataValue<String> mContentDisposition;
    public MetadataValue<String> mContentEncoding;
    public MetadataValue<String> mContentLanguage;
    public MetadataValue<String> mContentType;
    public String mCreationTime;
    public MetadataValue<Map<String, String>> mCustomMetadata;
    public String mGeneration;
    public String mMD5Hash;
    public String mMetadataGeneration;
    public String mPath;
    public long mSize;
    public FirebaseStorage mStorage;
    public StorageReference mStorageRef;
    public String mUpdatedTime;

    private static class MetadataValue<T> {
        public final boolean userProvided;

        @Nullable
        public final T value;

        public MetadataValue(@Nullable T t, boolean z) {
            this.userProvided = z;
            this.value = t;
        }

        public static <T> MetadataValue<T> withDefaultValue(T t) {
            return new MetadataValue<>(t, false);
        }

        public static <T> MetadataValue<T> withUserValue(@Nullable T t) {
            return new MetadataValue<>(t, true);
        }

        @Nullable
        public T getValue() {
            return this.value;
        }

        public boolean isUserProvided() {
            return this.userProvided;
        }
    }

    @NonNull
    public d createJSONObject() {
        HashMap map = new HashMap();
        if (this.mContentType.isUserProvided()) {
            map.put(CONTENT_TYPE_KEY, getContentType());
        }
        if (this.mCustomMetadata.isUserProvided()) {
            map.put(CUSTOM_METADATA_KEY, new d((Map<?, ?>) this.mCustomMetadata.getValue()));
        }
        if (this.mCacheControl.isUserProvided()) {
            map.put(CACHE_CONTROL, getCacheControl());
        }
        if (this.mContentDisposition.isUserProvided()) {
            map.put(CONTENT_DISPOSITION, getContentDisposition());
        }
        if (this.mContentEncoding.isUserProvided()) {
            map.put(CONTENT_ENCODING, getContentEncoding());
        }
        if (this.mContentLanguage.isUserProvided()) {
            map.put(CONTENT_LANGUAGE, getContentLanguage());
        }
        return new d((Map<?, ?>) map);
    }

    @Nullable
    public String getBucket() {
        return this.mBucket;
    }

    @Nullable
    public String getCacheControl() {
        return this.mCacheControl.getValue();
    }

    @Nullable
    public String getContentDisposition() {
        return this.mContentDisposition.getValue();
    }

    @Nullable
    public String getContentEncoding() {
        return this.mContentEncoding.getValue();
    }

    @Nullable
    public String getContentLanguage() {
        return this.mContentLanguage.getValue();
    }

    @Nullable
    public String getContentType() {
        return this.mContentType.getValue();
    }

    public long getCreationTimeMillis() {
        return Util.parseDateTime(this.mCreationTime);
    }

    @Nullable
    public String getCustomMetadata(@NonNull String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.mCustomMetadata.getValue().get(str);
    }

    @NonNull
    public Set<String> getCustomMetadataKeys() {
        return this.mCustomMetadata.getValue().keySet();
    }

    @Nullable
    public String getGeneration() {
        return this.mGeneration;
    }

    @Nullable
    public String getMd5Hash() {
        return this.mMD5Hash;
    }

    @Nullable
    public String getMetadataGeneration() {
        return this.mMetadataGeneration;
    }

    @Nullable
    public String getName() {
        String path = getPath();
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        int iLastIndexOf = path.lastIndexOf(47);
        return iLastIndexOf != -1 ? path.substring(iLastIndexOf + 1) : path;
    }

    @NonNull
    public String getPath() {
        String str = this.mPath;
        return str != null ? str : "";
    }

    @Nullable
    public StorageReference getReference() {
        if (this.mStorageRef != null || this.mStorage == null) {
            return this.mStorageRef;
        }
        String bucket = getBucket();
        String path = getPath();
        if (TextUtils.isEmpty(bucket) || TextUtils.isEmpty(path)) {
            return null;
        }
        return new StorageReference(new Uri.Builder().scheme("gs").authority(bucket).encodedPath(Slashes.preserveSlashEncode(path)).build(), this.mStorage);
    }

    public long getSizeBytes() {
        return this.mSize;
    }

    public long getUpdatedTimeMillis() {
        return Util.parseDateTime(this.mUpdatedTime);
    }

    public static class Builder {
        public boolean mFromJSON;
        public StorageMetadata mMetadata;

        public Builder() {
            this.mMetadata = new StorageMetadata();
        }

        @Nullable
        private String extractString(d dVar, String str) {
            if (!dVar.f2163b.containsKey(str) || d.f2162a.equals(dVar.j(str))) {
                return null;
            }
            return dVar.h(str);
        }

        private void parseJSON(d dVar) {
            this.mMetadata.mGeneration = dVar.a(StorageMetadata.GENERATION_KEY, "");
            this.mMetadata.mPath = dVar.a("name", "");
            this.mMetadata.mBucket = dVar.a(StorageMetadata.BUCKET_KEY, "");
            this.mMetadata.mMetadataGeneration = dVar.a(StorageMetadata.META_GENERATION_KEY, "");
            this.mMetadata.mCreationTime = dVar.a(StorageMetadata.TIME_CREATED_KEY, "");
            this.mMetadata.mUpdatedTime = dVar.a(StorageMetadata.TIME_UPDATED_KEY, "");
            this.mMetadata.mSize = dVar.a(StorageMetadata.SIZE_KEY, 0L);
            this.mMetadata.mMD5Hash = dVar.a(StorageMetadata.MD5_HASH_KEY, "");
            if (dVar.f2163b.containsKey(StorageMetadata.CUSTOM_METADATA_KEY) && !d.f2162a.equals(dVar.j(StorageMetadata.CUSTOM_METADATA_KEY))) {
                d dVarF = dVar.f(StorageMetadata.CUSTOM_METADATA_KEY);
                Iterator<String> itA = dVarF.a();
                while (itA.hasNext()) {
                    String next = itA.next();
                    setCustomMetadata(next, dVarF.h(next));
                }
            }
            String strExtractString = extractString(dVar, StorageMetadata.CONTENT_TYPE_KEY);
            if (strExtractString != null) {
                setContentType(strExtractString);
            }
            String strExtractString2 = extractString(dVar, StorageMetadata.CACHE_CONTROL);
            if (strExtractString2 != null) {
                setCacheControl(strExtractString2);
            }
            String strExtractString3 = extractString(dVar, StorageMetadata.CONTENT_DISPOSITION);
            if (strExtractString3 != null) {
                setContentDisposition(strExtractString3);
            }
            String strExtractString4 = extractString(dVar, StorageMetadata.CONTENT_ENCODING);
            if (strExtractString4 != null) {
                setContentEncoding(strExtractString4);
            }
            String strExtractString5 = extractString(dVar, StorageMetadata.CONTENT_LANGUAGE);
            if (strExtractString5 != null) {
                setContentLanguage(strExtractString5);
            }
        }

        @NonNull
        public StorageMetadata build() {
            return new StorageMetadata(this.mMetadata, this.mFromJSON);
        }

        @Nullable
        public String getCacheControl() {
            return (String) this.mMetadata.mCacheControl.getValue();
        }

        @Nullable
        public String getContentDisposition() {
            return (String) this.mMetadata.mContentDisposition.getValue();
        }

        @Nullable
        public String getContentEncoding() {
            return (String) this.mMetadata.mContentEncoding.getValue();
        }

        @Nullable
        public String getContentLanguage() {
            return (String) this.mMetadata.mContentLanguage.getValue();
        }

        @Nullable
        public String getContentType() {
            return (String) this.mMetadata.mContentType.getValue();
        }

        @NonNull
        public Builder setCacheControl(@Nullable String str) {
            this.mMetadata.mCacheControl = MetadataValue.withUserValue(str);
            return this;
        }

        @NonNull
        public Builder setContentDisposition(@Nullable String str) {
            this.mMetadata.mContentDisposition = MetadataValue.withUserValue(str);
            return this;
        }

        @NonNull
        public Builder setContentEncoding(@Nullable String str) {
            this.mMetadata.mContentEncoding = MetadataValue.withUserValue(str);
            return this;
        }

        @NonNull
        public Builder setContentLanguage(@Nullable String str) {
            this.mMetadata.mContentLanguage = MetadataValue.withUserValue(str);
            return this;
        }

        @NonNull
        public Builder setContentType(@Nullable String str) {
            this.mMetadata.mContentType = MetadataValue.withUserValue(str);
            return this;
        }

        @NonNull
        public Builder setCustomMetadata(@NonNull String str, @Nullable String str2) {
            if (!this.mMetadata.mCustomMetadata.isUserProvided()) {
                this.mMetadata.mCustomMetadata = MetadataValue.withUserValue(new HashMap());
            }
            ((Map) this.mMetadata.mCustomMetadata.getValue()).put(str, str2);
            return this;
        }

        public Builder(@NonNull StorageMetadata storageMetadata) {
            this.mMetadata = new StorageMetadata(storageMetadata, false);
        }

        public Builder(d dVar, StorageReference storageReference) {
            this(dVar);
            this.mMetadata.mStorageRef = storageReference;
        }

        public Builder(d dVar) {
            this.mMetadata = new StorageMetadata();
            if (dVar != null) {
                parseJSON(dVar);
                this.mFromJSON = true;
            }
        }
    }

    public StorageMetadata() {
        this.mPath = null;
        this.mStorage = null;
        this.mStorageRef = null;
        this.mBucket = null;
        this.mGeneration = null;
        this.mContentType = MetadataValue.withDefaultValue("");
        this.mMetadataGeneration = null;
        this.mCreationTime = null;
        this.mUpdatedTime = null;
        this.mMD5Hash = null;
        this.mCacheControl = MetadataValue.withDefaultValue("");
        this.mContentDisposition = MetadataValue.withDefaultValue("");
        this.mContentEncoding = MetadataValue.withDefaultValue("");
        this.mContentLanguage = MetadataValue.withDefaultValue("");
        this.mCustomMetadata = MetadataValue.withDefaultValue(Collections.emptyMap());
    }

    public StorageMetadata(@NonNull StorageMetadata storageMetadata, boolean z) {
        this.mPath = null;
        this.mStorage = null;
        this.mStorageRef = null;
        this.mBucket = null;
        this.mGeneration = null;
        this.mContentType = MetadataValue.withDefaultValue("");
        this.mMetadataGeneration = null;
        this.mCreationTime = null;
        this.mUpdatedTime = null;
        this.mMD5Hash = null;
        this.mCacheControl = MetadataValue.withDefaultValue("");
        this.mContentDisposition = MetadataValue.withDefaultValue("");
        this.mContentEncoding = MetadataValue.withDefaultValue("");
        this.mContentLanguage = MetadataValue.withDefaultValue("");
        this.mCustomMetadata = MetadataValue.withDefaultValue(Collections.emptyMap());
        Preconditions.checkNotNull(storageMetadata);
        this.mPath = storageMetadata.mPath;
        this.mStorage = storageMetadata.mStorage;
        this.mStorageRef = storageMetadata.mStorageRef;
        this.mBucket = storageMetadata.mBucket;
        this.mContentType = storageMetadata.mContentType;
        this.mCacheControl = storageMetadata.mCacheControl;
        this.mContentDisposition = storageMetadata.mContentDisposition;
        this.mContentEncoding = storageMetadata.mContentEncoding;
        this.mContentLanguage = storageMetadata.mContentLanguage;
        this.mCustomMetadata = storageMetadata.mCustomMetadata;
        if (z) {
            this.mMD5Hash = storageMetadata.mMD5Hash;
            this.mSize = storageMetadata.mSize;
            this.mUpdatedTime = storageMetadata.mUpdatedTime;
            this.mCreationTime = storageMetadata.mCreationTime;
            this.mMetadataGeneration = storageMetadata.mMetadataGeneration;
            this.mGeneration = storageMetadata.mGeneration;
        }
    }
}
