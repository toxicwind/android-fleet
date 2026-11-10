package com.google.firebase.firestore;

import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import b.a.a.a.a;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ServerTimestamps;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Logger;
import com.google.firestore.v1.ArrayValue;
import com.google.firestore.v1.Value;
import com.google.protobuf.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class UserDataWriter {
    public final FirebaseFirestore firestore;
    public final DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior;

    /* JADX INFO: renamed from: com.google.firebase.firestore.UserDataWriter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$DocumentSnapshot$ServerTimestampBehavior = new int[DocumentSnapshot.ServerTimestampBehavior.values().length];

        static {
            try {
                $SwitchMap$com$google$firebase$firestore$DocumentSnapshot$ServerTimestampBehavior[DocumentSnapshot.ServerTimestampBehavior.PREVIOUS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$DocumentSnapshot$ServerTimestampBehavior[DocumentSnapshot.ServerTimestampBehavior.ESTIMATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @VisibleForTesting
    public UserDataWriter(FirebaseFirestore firebaseFirestore, DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior) {
        this.firestore = firebaseFirestore;
        this.serverTimestampBehavior = serverTimestampBehavior;
    }

    private List<Object> convertArray(ArrayValue arrayValue) {
        ArrayList arrayList = new ArrayList(arrayValue.getValuesCount());
        Iterator<Value> it = arrayValue.getValuesList().iterator();
        while (it.hasNext()) {
            arrayList.add(convertValue(it.next()));
        }
        return arrayList;
    }

    private Object convertReference(Value value) {
        DatabaseId databaseIdFromName = DatabaseId.fromName(value.getReferenceValue());
        DocumentKey documentKeyFromName = DocumentKey.fromName(value.getReferenceValue());
        DatabaseId databaseId = this.firestore.getDatabaseId();
        if (!databaseIdFromName.equals(databaseId)) {
            Logger.warn("DocumentSnapshot", "Document %s contains a document reference within a different database (%s/%s) which is not supported. It will be treated as a reference in the current database (%s/%s) instead.", documentKeyFromName.getPath(), databaseIdFromName.getProjectId(), databaseIdFromName.getDatabaseId(), databaseId.getProjectId(), databaseId.getDatabaseId());
        }
        return new DocumentReference(documentKeyFromName, this.firestore);
    }

    private Object convertServerTimestamp(Value value) {
        Value previousValue;
        int iOrdinal = this.serverTimestampBehavior.ordinal();
        if (iOrdinal == 1) {
            return convertTimestamp(ServerTimestamps.getLocalWriteTime(value));
        }
        if (iOrdinal == 2 && (previousValue = ServerTimestamps.getPreviousValue(value)) != null) {
            return convertValue(previousValue);
        }
        return null;
    }

    private Object convertTimestamp(Timestamp timestamp) {
        return new com.google.firebase.Timestamp(timestamp.getSeconds(), timestamp.getNanos());
    }

    public Map<String, Object> convertObject(Map<String, Value> map) {
        HashMap map2 = new HashMap();
        for (Map.Entry<String, Value> entry : map.entrySet()) {
            map2.put(entry.getKey(), convertValue(entry.getValue()));
        }
        return map2;
    }

    @VisibleForTesting
    public Object convertValue(Value value) {
        switch (Values.typeOrder(value)) {
            case 0:
                return null;
            case 1:
                return Boolean.valueOf(value.getBooleanValue());
            case 2:
                return value.getValueTypeCase().equals(Value.ValueTypeCase.INTEGER_VALUE) ? Long.valueOf(value.getIntegerValue()) : Double.valueOf(value.getDoubleValue());
            case 3:
                return convertTimestamp(value.getTimestampValue());
            case 4:
                return convertServerTimestamp(value);
            case 5:
                return value.getStringValue();
            case 6:
                return Blob.fromByteString(value.getBytesValue());
            case 7:
                return convertReference(value);
            case 8:
                return new GeoPoint(value.getGeoPointValue().getLatitude(), value.getGeoPointValue().getLongitude());
            case 9:
                return convertArray(value.getArrayValue());
            case 10:
                return convertObject(value.getMapValue().getFieldsMap());
            default:
                StringBuilder sbA = a.a("Unknown value type: ");
                sbA.append(value.getValueTypeCase());
                Assert.fail(sbA.toString(), new Object[0]);
                throw null;
        }
    }
}
