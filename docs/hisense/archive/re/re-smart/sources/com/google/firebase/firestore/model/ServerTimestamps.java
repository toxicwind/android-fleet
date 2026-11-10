package com.google.firebase.firestore.model;

import androidx.annotation.Nullable;
import com.google.firestore.v1.MapValue;
import com.google.firestore.v1.Value;
import com.google.protobuf.Timestamp;

/* JADX INFO: loaded from: classes.dex */
public final class ServerTimestamps {
    public static final String LOCAL_WRITE_TIME_KEY = "__local_write_time__";
    public static final String PREVIOUS_VALUE_KEY = "__previous_value__";
    public static final String SERVER_TIMESTAMP_SENTINEL = "server_timestamp";
    public static final String TYPE_KEY = "__type__";

    public static Timestamp getLocalWriteTime(Value value) {
        return value.getMapValue().getFieldsOrThrow(LOCAL_WRITE_TIME_KEY).getTimestampValue();
    }

    @Nullable
    public static Value getPreviousValue(Value value) {
        Value fieldsOrDefault = value.getMapValue().getFieldsOrDefault(PREVIOUS_VALUE_KEY, null);
        return isServerTimestamp(fieldsOrDefault) ? getPreviousValue(fieldsOrDefault) : fieldsOrDefault;
    }

    public static boolean isServerTimestamp(@Nullable Value value) {
        Value fieldsOrDefault = value != null ? value.getMapValue().getFieldsOrDefault(TYPE_KEY, null) : null;
        return fieldsOrDefault != null && SERVER_TIMESTAMP_SENTINEL.equals(fieldsOrDefault.getStringValue());
    }

    public static Value valueOf(com.google.firebase.Timestamp timestamp, @Nullable Value value) {
        Value valueBuild = Value.newBuilder().setStringValue(SERVER_TIMESTAMP_SENTINEL).build();
        MapValue.Builder builderPutFields = MapValue.newBuilder().putFields(TYPE_KEY, valueBuild).putFields(LOCAL_WRITE_TIME_KEY, Value.newBuilder().setTimestampValue(Timestamp.newBuilder().setSeconds(timestamp.getSeconds()).setNanos(timestamp.getNanoseconds())).build());
        if (value != null) {
            builderPutFields.putFields(PREVIOUS_VALUE_KEY, value);
        }
        return Value.newBuilder().setMapValue(builderPutFields).build();
    }
}
