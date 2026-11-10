package com.google.firebase.firestore.index;

import b.a.a.a.a;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firestore.v1.ArrayValue;
import com.google.firestore.v1.MapValue;
import com.google.firestore.v1.Value;
import com.google.protobuf.Timestamp;
import com.google.type.LatLng;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class FirestoreIndexValueWriter {
    public static final int DOCUMENT_NAME_OFFSET = 5;
    public static final int INDEX_TYPE_ARRAY = 50;
    public static final int INDEX_TYPE_BLOB = 30;
    public static final int INDEX_TYPE_BOOLEAN = 10;
    public static final int INDEX_TYPE_GEOPOINT = 45;
    public static final int INDEX_TYPE_MAP = 55;
    public static final int INDEX_TYPE_NAN = 13;
    public static final int INDEX_TYPE_NULL = 5;
    public static final int INDEX_TYPE_NUMBER = 15;
    public static final int INDEX_TYPE_REFERENCE = 37;
    public static final int INDEX_TYPE_REFERENCE_SEGMENT = 60;
    public static final int INDEX_TYPE_STRING = 25;
    public static final int INDEX_TYPE_TIMESTAMP = 20;
    public static final FirestoreIndexValueWriter INSTANCE = new FirestoreIndexValueWriter();
    public static final int NOT_TRUNCATED = 2;

    /* JADX INFO: renamed from: com.google.firebase.firestore.index.FirestoreIndexValueWriter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase = new int[Value.ValueTypeCase.values().length];

        static {
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.NULL_VALUE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.BOOLEAN_VALUE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.DOUBLE_VALUE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.INTEGER_VALUE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.TIMESTAMP_VALUE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.STRING_VALUE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.BYTES_VALUE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.REFERENCE_VALUE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.GEO_POINT_VALUE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.MAP_VALUE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.ARRAY_VALUE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    private void writeIndexArray(ArrayValue arrayValue, DirectionalIndexByteEncoder directionalIndexByteEncoder) {
        directionalIndexByteEncoder.writeLong(50);
        Iterator<Value> it = arrayValue.getValuesList().iterator();
        while (it.hasNext()) {
            writeIndexValueAux(it.next(), directionalIndexByteEncoder);
        }
    }

    private void writeIndexEntityRef(String str, DirectionalIndexByteEncoder directionalIndexByteEncoder) {
        directionalIndexByteEncoder.writeLong(37);
        ResourcePath resourcePathFromString = ResourcePath.fromString(str);
        int length = resourcePathFromString.length();
        for (int i = 5; i < length; i++) {
            String segment = resourcePathFromString.getSegment(i);
            directionalIndexByteEncoder.writeLong(60);
            directionalIndexByteEncoder.writeString(segment);
        }
    }

    private void writeIndexMap(MapValue mapValue, DirectionalIndexByteEncoder directionalIndexByteEncoder) {
        directionalIndexByteEncoder.writeLong(55);
        for (Map.Entry<String, Value> entry : mapValue.getFieldsMap().entrySet()) {
            String key = entry.getKey();
            Value value = entry.getValue();
            writeIndexString(key, directionalIndexByteEncoder);
            writeIndexValueAux(value, directionalIndexByteEncoder);
        }
    }

    private void writeIndexString(String str, DirectionalIndexByteEncoder directionalIndexByteEncoder) {
        directionalIndexByteEncoder.writeLong(25);
        directionalIndexByteEncoder.writeString(str);
    }

    private void writeIndexValueAux(Value value, DirectionalIndexByteEncoder directionalIndexByteEncoder) {
        switch (value.getValueTypeCase()) {
            case NULL_VALUE:
                directionalIndexByteEncoder.writeLong(5);
                return;
            case BOOLEAN_VALUE:
                directionalIndexByteEncoder.writeLong(10);
                directionalIndexByteEncoder.writeLong(value.getBooleanValue() ? 1L : 0L);
                return;
            case INTEGER_VALUE:
                directionalIndexByteEncoder.writeLong(15);
                directionalIndexByteEncoder.writeDouble(value.getIntegerValue());
                return;
            case DOUBLE_VALUE:
                double doubleValue = value.getDoubleValue();
                if (Double.isNaN(doubleValue)) {
                    directionalIndexByteEncoder.writeLong(13);
                    return;
                }
                directionalIndexByteEncoder.writeLong(15);
                if (doubleValue == -0.0d) {
                    directionalIndexByteEncoder.writeDouble(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                    return;
                } else {
                    directionalIndexByteEncoder.writeDouble(doubleValue);
                    return;
                }
            case TIMESTAMP_VALUE:
                Timestamp timestampValue = value.getTimestampValue();
                directionalIndexByteEncoder.writeLong(20);
                directionalIndexByteEncoder.writeLong(timestampValue.getSeconds());
                directionalIndexByteEncoder.writeLong(timestampValue.getNanos());
                return;
            case STRING_VALUE:
                writeIndexString(value.getStringValue(), directionalIndexByteEncoder);
                directionalIndexByteEncoder.writeLong(2L);
                return;
            case BYTES_VALUE:
                directionalIndexByteEncoder.writeLong(30);
                directionalIndexByteEncoder.writeBytes(value.getBytesValue());
                directionalIndexByteEncoder.writeLong(2L);
                return;
            case REFERENCE_VALUE:
                writeIndexEntityRef(value.getReferenceValue(), directionalIndexByteEncoder);
                return;
            case GEO_POINT_VALUE:
                LatLng geoPointValue = value.getGeoPointValue();
                directionalIndexByteEncoder.writeLong(45);
                directionalIndexByteEncoder.writeDouble(geoPointValue.getLatitude());
                directionalIndexByteEncoder.writeDouble(geoPointValue.getLongitude());
                return;
            case ARRAY_VALUE:
                writeIndexArray(value.getArrayValue(), directionalIndexByteEncoder);
                directionalIndexByteEncoder.writeLong(2L);
                return;
            case MAP_VALUE:
                if (Values.isMaxValue(value)) {
                    directionalIndexByteEncoder.writeLong(Integer.MAX_VALUE);
                    return;
                } else {
                    writeIndexMap(value.getMapValue(), directionalIndexByteEncoder);
                    directionalIndexByteEncoder.writeLong(2L);
                    return;
                }
            default:
                StringBuilder sbA = a.a("unknown index value type ");
                sbA.append(value.getValueTypeCase());
                throw new IllegalArgumentException(sbA.toString());
        }
    }

    private void writeTruncationMarker(DirectionalIndexByteEncoder directionalIndexByteEncoder) {
        directionalIndexByteEncoder.writeLong(2L);
    }

    private void writeUnlabeledIndexString(String str, DirectionalIndexByteEncoder directionalIndexByteEncoder) {
        directionalIndexByteEncoder.writeString(str);
    }

    private void writeValueTypeLabel(DirectionalIndexByteEncoder directionalIndexByteEncoder, int i) {
        directionalIndexByteEncoder.writeLong(i);
    }

    public void writeIndexValue(Value value, DirectionalIndexByteEncoder directionalIndexByteEncoder) {
        writeIndexValueAux(value, directionalIndexByteEncoder);
        directionalIndexByteEncoder.writeInfinity();
    }
}
