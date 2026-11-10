package com.google.firebase.firestore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.a.a.a.a;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.util.CustomClassMapper;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firestore.v1.Value;
import java.util.Date;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class DocumentSnapshot {

    @Nullable
    public final Document doc;
    public final FirebaseFirestore firestore;
    public final DocumentKey key;
    public final SnapshotMetadata metadata;

    public enum ServerTimestampBehavior {
        NONE,
        ESTIMATE,
        PREVIOUS;

        public static final ServerTimestampBehavior DEFAULT = NONE;
    }

    public DocumentSnapshot(FirebaseFirestore firebaseFirestore, DocumentKey documentKey, @Nullable Document document, boolean z, boolean z2) {
        if (firebaseFirestore == null) {
            throw new NullPointerException();
        }
        this.firestore = firebaseFirestore;
        if (documentKey == null) {
            throw new NullPointerException();
        }
        this.key = documentKey;
        this.doc = document;
        this.metadata = new SnapshotMetadata(z2, z);
    }

    @Nullable
    private <T> T castTypedValue(Object obj, String str, Class<T> cls) {
        if (obj == null) {
            return null;
        }
        if (cls.isInstance(obj)) {
            return cls.cast(obj);
        }
        StringBuilder sbB = a.b("Field '", str, "' is not a ");
        sbB.append(cls.getName());
        throw new RuntimeException(sbB.toString());
    }

    public static DocumentSnapshot fromDocument(FirebaseFirestore firebaseFirestore, Document document, boolean z, boolean z2) {
        return new DocumentSnapshot(firebaseFirestore, document.getKey(), document, z, z2);
    }

    public static DocumentSnapshot fromNoDocument(FirebaseFirestore firebaseFirestore, DocumentKey documentKey, boolean z) {
        return new DocumentSnapshot(firebaseFirestore, documentKey, null, z, false);
    }

    @Nullable
    private Object getInternal(@NonNull com.google.firebase.firestore.model.FieldPath fieldPath, @NonNull ServerTimestampBehavior serverTimestampBehavior) {
        Value field;
        Document document = this.doc;
        if (document == null || (field = document.getField(fieldPath)) == null) {
            return null;
        }
        return new UserDataWriter(this.firestore, serverTimestampBehavior).convertValue(field);
    }

    @Nullable
    private <T> T getTypedValue(String str, Class<T> cls) {
        Preconditions.checkNotNull(str, "Provided field must not be null.");
        return (T) castTypedValue(get(str, ServerTimestampBehavior.DEFAULT), str, cls);
    }

    public boolean contains(@NonNull String str) {
        return contains(FieldPath.fromDotSeparatedPath(str));
    }

    public boolean equals(@Nullable Object obj) {
        Document document;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DocumentSnapshot)) {
            return false;
        }
        DocumentSnapshot documentSnapshot = (DocumentSnapshot) obj;
        return this.firestore.equals(documentSnapshot.firestore) && this.key.equals(documentSnapshot.key) && ((document = this.doc) != null ? document.equals(documentSnapshot.doc) : documentSnapshot.doc == null) && this.metadata.equals(documentSnapshot.metadata);
    }

    public boolean exists() {
        return this.doc != null;
    }

    @Nullable
    public Object get(@NonNull String str) {
        return get(FieldPath.fromDotSeparatedPath(str), ServerTimestampBehavior.DEFAULT);
    }

    @Nullable
    public Blob getBlob(@NonNull String str) {
        return (Blob) getTypedValue(str, Blob.class);
    }

    @Nullable
    public Boolean getBoolean(@NonNull String str) {
        return (Boolean) getTypedValue(str, Boolean.class);
    }

    @Nullable
    public Map<String, Object> getData() {
        return getData(ServerTimestampBehavior.DEFAULT);
    }

    @Nullable
    public Date getDate(@NonNull String str) {
        return getDate(str, ServerTimestampBehavior.DEFAULT);
    }

    @Nullable
    public Document getDocument() {
        return this.doc;
    }

    @Nullable
    public DocumentReference getDocumentReference(@NonNull String str) {
        return (DocumentReference) getTypedValue(str, DocumentReference.class);
    }

    @Nullable
    public Double getDouble(@NonNull String str) {
        Number number = (Number) getTypedValue(str, Number.class);
        if (number != null) {
            return Double.valueOf(number.doubleValue());
        }
        return null;
    }

    @Nullable
    public GeoPoint getGeoPoint(@NonNull String str) {
        return (GeoPoint) getTypedValue(str, GeoPoint.class);
    }

    @NonNull
    public String getId() {
        return this.key.getDocumentId();
    }

    @Nullable
    public Long getLong(@NonNull String str) {
        Number number = (Number) getTypedValue(str, Number.class);
        if (number != null) {
            return Long.valueOf(number.longValue());
        }
        return null;
    }

    @NonNull
    public SnapshotMetadata getMetadata() {
        return this.metadata;
    }

    @NonNull
    public DocumentReference getReference() {
        return new DocumentReference(this.key, this.firestore);
    }

    @Nullable
    public String getString(@NonNull String str) {
        return (String) getTypedValue(str, String.class);
    }

    @Nullable
    public Timestamp getTimestamp(@NonNull String str) {
        return getTimestamp(str, ServerTimestampBehavior.DEFAULT);
    }

    public int hashCode() {
        int iHashCode = (this.key.hashCode() + (this.firestore.hashCode() * 31)) * 31;
        Document document = this.doc;
        int iHashCode2 = (iHashCode + (document != null ? document.getKey().hashCode() : 0)) * 31;
        Document document2 = this.doc;
        return this.metadata.hashCode() + ((iHashCode2 + (document2 != null ? document2.getData().hashCode() : 0)) * 31);
    }

    @Nullable
    public <T> T toObject(@NonNull Class<T> cls) {
        return (T) toObject(cls, ServerTimestampBehavior.DEFAULT);
    }

    public String toString() {
        StringBuilder sbA = a.a("DocumentSnapshot{key=");
        sbA.append(this.key);
        sbA.append(", metadata=");
        sbA.append(this.metadata);
        sbA.append(", doc=");
        return a.a(sbA, (Object) this.doc, '}');
    }

    public boolean contains(@NonNull FieldPath fieldPath) {
        Preconditions.checkNotNull(fieldPath, "Provided field path must not be null.");
        Document document = this.doc;
        return (document == null || document.getField(fieldPath.getInternalPath()) == null) ? false : true;
    }

    @Nullable
    public Object get(@NonNull String str, @NonNull ServerTimestampBehavior serverTimestampBehavior) {
        return get(FieldPath.fromDotSeparatedPath(str), serverTimestampBehavior);
    }

    @Nullable
    public Map<String, Object> getData(@NonNull ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(serverTimestampBehavior, "Provided serverTimestampBehavior value must not be null.");
        UserDataWriter userDataWriter = new UserDataWriter(this.firestore, serverTimestampBehavior);
        Document document = this.doc;
        if (document == null) {
            return null;
        }
        return userDataWriter.convertObject(document.getData().getFieldsMap());
    }

    @Nullable
    public Date getDate(@NonNull String str, @NonNull ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(str, "Provided field path must not be null.");
        Preconditions.checkNotNull(serverTimestampBehavior, "Provided serverTimestampBehavior value must not be null.");
        Timestamp timestamp = getTimestamp(str, serverTimestampBehavior);
        if (timestamp != null) {
            return timestamp.toDate();
        }
        return null;
    }

    @Nullable
    public Timestamp getTimestamp(@NonNull String str, @NonNull ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(str, "Provided field path must not be null.");
        Preconditions.checkNotNull(serverTimestampBehavior, "Provided serverTimestampBehavior value must not be null.");
        return (Timestamp) castTypedValue(getInternal(FieldPath.fromDotSeparatedPath(str).getInternalPath(), serverTimestampBehavior), str, Timestamp.class);
    }

    @Nullable
    public <T> T toObject(@NonNull Class<T> cls, @NonNull ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(cls, "Provided POJO type must not be null.");
        Preconditions.checkNotNull(serverTimestampBehavior, "Provided serverTimestampBehavior value must not be null.");
        Map<String, Object> data = getData(serverTimestampBehavior);
        if (data == null) {
            return null;
        }
        return (T) CustomClassMapper.convertToCustomClass(data, cls, getReference());
    }

    @Nullable
    public Object get(@NonNull FieldPath fieldPath) {
        return get(fieldPath, ServerTimestampBehavior.DEFAULT);
    }

    @Nullable
    public Object get(@NonNull FieldPath fieldPath, @NonNull ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(fieldPath, "Provided field path must not be null.");
        Preconditions.checkNotNull(serverTimestampBehavior, "Provided serverTimestampBehavior value must not be null.");
        return getInternal(fieldPath.getInternalPath(), serverTimestampBehavior);
    }

    @Nullable
    public <T> T get(@NonNull String str, @NonNull Class<T> cls) {
        return (T) get(FieldPath.fromDotSeparatedPath(str), cls, ServerTimestampBehavior.DEFAULT);
    }

    @Nullable
    public <T> T get(@NonNull String str, @NonNull Class<T> cls, @NonNull ServerTimestampBehavior serverTimestampBehavior) {
        return (T) get(FieldPath.fromDotSeparatedPath(str), cls, serverTimestampBehavior);
    }

    @Nullable
    public <T> T get(@NonNull FieldPath fieldPath, @NonNull Class<T> cls) {
        return (T) get(fieldPath, cls, ServerTimestampBehavior.DEFAULT);
    }

    @Nullable
    public <T> T get(@NonNull FieldPath fieldPath, @NonNull Class<T> cls, @NonNull ServerTimestampBehavior serverTimestampBehavior) {
        Object obj = get(fieldPath, serverTimestampBehavior);
        if (obj == null) {
            return null;
        }
        return (T) CustomClassMapper.convertToCustomClass(obj, cls, getReference());
    }
}
