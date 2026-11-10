package com.google.firebase.firestore.model;

import androidx.annotation.Nullable;
import b.a.a.a.a;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Util;
import com.google.firebase.installations.Utils;
import com.google.firebase.installations.local.IidStore;
import com.google.firestore.v1.ArrayValue;
import com.google.firestore.v1.ArrayValueOrBuilder;
import com.google.firestore.v1.MapValue;
import com.google.firestore.v1.Value;
import com.google.protobuf.ByteString;
import com.google.protobuf.NullValue;
import com.google.protobuf.Timestamp;
import com.google.type.LatLng;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public class Values {
    public static final int TYPE_ORDER_ARRAY = 9;
    public static final int TYPE_ORDER_BLOB = 6;
    public static final int TYPE_ORDER_BOOLEAN = 1;
    public static final int TYPE_ORDER_GEOPOINT = 8;
    public static final int TYPE_ORDER_MAP = 10;
    public static final int TYPE_ORDER_NULL = 0;
    public static final int TYPE_ORDER_NUMBER = 2;
    public static final int TYPE_ORDER_REFERENCE = 7;
    public static final int TYPE_ORDER_SERVER_TIMESTAMP = 4;
    public static final int TYPE_ORDER_STRING = 5;
    public static final int TYPE_ORDER_TIMESTAMP = 3;
    public static final Value NAN_VALUE = Value.newBuilder().setDoubleValue(Double.NaN).build();
    public static final Value NULL_VALUE = Value.newBuilder().setNullValue(NullValue.NULL_VALUE).build();
    public static final Value MIN_VALUE = NULL_VALUE;
    public static final Value MAX_VALUE = Value.newBuilder().setMapValue(MapValue.newBuilder().putFields(ServerTimestamps.TYPE_KEY, Value.newBuilder().setStringValue("__max__").build())).build();

    /* JADX INFO: renamed from: com.google.firebase.firestore.model.Values$1, reason: invalid class name */
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
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.INTEGER_VALUE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.DOUBLE_VALUE.ordinal()] = 4;
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
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.ARRAY_VALUE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.MAP_VALUE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    public static boolean arrayEquals(Value value, Value value2) {
        ArrayValue arrayValue = value.getArrayValue();
        ArrayValue arrayValue2 = value2.getArrayValue();
        if (arrayValue.getValuesCount() != arrayValue2.getValuesCount()) {
            return false;
        }
        for (int i = 0; i < arrayValue.getValuesCount(); i++) {
            if (!equals(arrayValue.getValues(i), arrayValue2.getValues(i))) {
                return false;
            }
        }
        return true;
    }

    public static String canonicalId(Value value) {
        StringBuilder sb = new StringBuilder();
        canonifyValue(sb, value);
        return sb.toString();
    }

    public static void canonifyArray(StringBuilder sb, ArrayValue arrayValue) {
        sb.append("[");
        for (int i = 0; i < arrayValue.getValuesCount(); i++) {
            canonifyValue(sb, arrayValue.getValues(i));
            if (i != arrayValue.getValuesCount() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
    }

    public static void canonifyGeoPoint(StringBuilder sb, LatLng latLng) {
        sb.append(String.format("geo(%s,%s)", Double.valueOf(latLng.getLatitude()), Double.valueOf(latLng.getLongitude())));
    }

    public static void canonifyObject(StringBuilder sb, MapValue mapValue) {
        ArrayList<String> arrayList = new ArrayList(mapValue.getFieldsMap().keySet());
        Collections.sort(arrayList);
        sb.append(IidStore.JSON_ENCODED_PREFIX);
        boolean z = true;
        for (String str : arrayList) {
            if (z) {
                z = false;
            } else {
                sb.append(",");
            }
            sb.append(str);
            sb.append(Utils.APP_ID_IDENTIFICATION_SUBSTRING);
            canonifyValue(sb, mapValue.getFieldsOrThrow(str));
        }
        sb.append("}");
    }

    public static void canonifyReference(StringBuilder sb, Value value) {
        Assert.hardAssert(isReferenceValue(value), "Value should be a ReferenceValue", new Object[0]);
        sb.append(DocumentKey.fromName(value.getReferenceValue()));
    }

    public static void canonifyTimestamp(StringBuilder sb, Timestamp timestamp) {
        sb.append(String.format("time(%s,%s)", Long.valueOf(timestamp.getSeconds()), Integer.valueOf(timestamp.getNanos())));
    }

    public static void canonifyValue(StringBuilder sb, Value value) {
        switch (value.getValueTypeCase()) {
            case NULL_VALUE:
                sb.append("null");
                return;
            case BOOLEAN_VALUE:
                sb.append(value.getBooleanValue());
                return;
            case INTEGER_VALUE:
                sb.append(value.getIntegerValue());
                return;
            case DOUBLE_VALUE:
                sb.append(value.getDoubleValue());
                return;
            case TIMESTAMP_VALUE:
                canonifyTimestamp(sb, value.getTimestampValue());
                return;
            case STRING_VALUE:
                sb.append(value.getStringValue());
                return;
            case BYTES_VALUE:
                sb.append(Util.toDebugString(value.getBytesValue()));
                return;
            case REFERENCE_VALUE:
                canonifyReference(sb, value);
                return;
            case GEO_POINT_VALUE:
                canonifyGeoPoint(sb, value.getGeoPointValue());
                return;
            case ARRAY_VALUE:
                canonifyArray(sb, value.getArrayValue());
                return;
            case MAP_VALUE:
                canonifyObject(sb, value.getMapValue());
                return;
            default:
                StringBuilder sbA = a.a("Invalid value type: ");
                sbA.append(value.getValueTypeCase());
                Assert.fail(sbA.toString(), new Object[0]);
                throw null;
        }
    }

    public static int compare(Value value, Value value2) {
        int iTypeOrder = typeOrder(value);
        int iTypeOrder2 = typeOrder(value2);
        if (iTypeOrder != iTypeOrder2) {
            return Util.compareIntegers(iTypeOrder, iTypeOrder2);
        }
        switch (iTypeOrder) {
            case 0:
                return 0;
            case 1:
                return Util.compareBooleans(value.getBooleanValue(), value2.getBooleanValue());
            case 2:
                return compareNumbers(value, value2);
            case 3:
                return compareTimestamps(value.getTimestampValue(), value2.getTimestampValue());
            case 4:
                return compareTimestamps(ServerTimestamps.getLocalWriteTime(value), ServerTimestamps.getLocalWriteTime(value2));
            case 5:
                return value.getStringValue().compareTo(value2.getStringValue());
            case 6:
                return Util.compareByteStrings(value.getBytesValue(), value2.getBytesValue());
            case 7:
                return compareReferences(value.getReferenceValue(), value2.getReferenceValue());
            case 8:
                return compareGeoPoints(value.getGeoPointValue(), value2.getGeoPointValue());
            case 9:
                return compareArrays(value.getArrayValue(), value2.getArrayValue());
            case 10:
                return compareMaps(value.getMapValue(), value2.getMapValue());
            default:
                Assert.fail(a.a("Invalid value type: ", iTypeOrder), new Object[0]);
                throw null;
        }
    }

    public static int compareArrays(ArrayValue arrayValue, ArrayValue arrayValue2) {
        int iMin = Math.min(arrayValue.getValuesCount(), arrayValue2.getValuesCount());
        for (int i = 0; i < iMin; i++) {
            int iCompare = compare(arrayValue.getValues(i), arrayValue2.getValues(i));
            if (iCompare != 0) {
                return iCompare;
            }
        }
        return Util.compareIntegers(arrayValue.getValuesCount(), arrayValue2.getValuesCount());
    }

    public static int compareGeoPoints(LatLng latLng, LatLng latLng2) {
        int iCompareDoubles = Util.compareDoubles(latLng.getLatitude(), latLng2.getLatitude());
        return iCompareDoubles == 0 ? Util.compareDoubles(latLng.getLongitude(), latLng2.getLongitude()) : iCompareDoubles;
    }

    public static int compareMaps(MapValue mapValue, MapValue mapValue2) {
        Iterator it = new TreeMap(mapValue.getFieldsMap()).entrySet().iterator();
        Iterator it2 = new TreeMap(mapValue2.getFieldsMap()).entrySet().iterator();
        while (it.hasNext() && it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Map.Entry entry2 = (Map.Entry) it2.next();
            int iCompareTo = ((String) entry.getKey()).compareTo((String) entry2.getKey());
            if (iCompareTo != 0) {
                return iCompareTo;
            }
            int iCompare = compare((Value) entry.getValue(), (Value) entry2.getValue());
            if (iCompare != 0) {
                return iCompare;
            }
        }
        return Util.compareBooleans(it.hasNext(), it2.hasNext());
    }

    public static int compareNumbers(Value value, Value value2) {
        if (value.getValueTypeCase() == Value.ValueTypeCase.DOUBLE_VALUE) {
            double doubleValue = value.getDoubleValue();
            if (value2.getValueTypeCase() == Value.ValueTypeCase.DOUBLE_VALUE) {
                return Util.compareDoubles(doubleValue, value2.getDoubleValue());
            }
            if (value2.getValueTypeCase() == Value.ValueTypeCase.INTEGER_VALUE) {
                return Util.compareMixed(doubleValue, value2.getIntegerValue());
            }
        } else if (value.getValueTypeCase() == Value.ValueTypeCase.INTEGER_VALUE) {
            long integerValue = value.getIntegerValue();
            if (value2.getValueTypeCase() == Value.ValueTypeCase.INTEGER_VALUE) {
                return Util.compareLongs(integerValue, value2.getIntegerValue());
            }
            if (value2.getValueTypeCase() == Value.ValueTypeCase.DOUBLE_VALUE) {
                return Util.compareMixed(value2.getDoubleValue(), integerValue) * (-1);
            }
        }
        Assert.fail("Unexpected values: %s vs %s", value, value2);
        throw null;
    }

    public static int compareReferences(String str, String str2) {
        String[] strArrSplit = str.split("/", -1);
        String[] strArrSplit2 = str2.split("/", -1);
        int iMin = Math.min(strArrSplit.length, strArrSplit2.length);
        for (int i = 0; i < iMin; i++) {
            int iCompareTo = strArrSplit[i].compareTo(strArrSplit2[i]);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return Util.compareIntegers(strArrSplit.length, strArrSplit2.length);
    }

    public static int compareTimestamps(Timestamp timestamp, Timestamp timestamp2) {
        int iCompareLongs = Util.compareLongs(timestamp.getSeconds(), timestamp2.getSeconds());
        return iCompareLongs != 0 ? iCompareLongs : Util.compareIntegers(timestamp.getNanos(), timestamp2.getNanos());
    }

    public static boolean contains(ArrayValueOrBuilder arrayValueOrBuilder, Value value) {
        Iterator<Value> it = arrayValueOrBuilder.getValuesList().iterator();
        while (it.hasNext()) {
            if (equals(it.next(), value)) {
                return true;
            }
        }
        return false;
    }

    public static boolean equals(Value value, Value value2) {
        int iTypeOrder;
        if (value == value2) {
            return true;
        }
        if (value == null || value2 == null || (iTypeOrder = typeOrder(value)) != typeOrder(value2)) {
            return false;
        }
        if (iTypeOrder == 2) {
            return numberEquals(value, value2);
        }
        if (iTypeOrder == 4) {
            return ServerTimestamps.getLocalWriteTime(value).equals(ServerTimestamps.getLocalWriteTime(value2));
        }
        if (iTypeOrder != 9) {
            return iTypeOrder != 10 ? value.equals(value2) : objectEquals(value, value2);
        }
        return arrayEquals(value, value2);
    }

    public static Value getLowerBound(Value.ValueTypeCase valueTypeCase) {
        switch (valueTypeCase) {
            case NULL_VALUE:
                return NULL_VALUE;
            case BOOLEAN_VALUE:
                return Value.newBuilder().setBooleanValue(false).build();
            case INTEGER_VALUE:
            case DOUBLE_VALUE:
                return Value.newBuilder().setDoubleValue(Double.NaN).build();
            case TIMESTAMP_VALUE:
                return Value.newBuilder().setTimestampValue(Timestamp.newBuilder().setSeconds(Long.MIN_VALUE)).build();
            case STRING_VALUE:
                return Value.newBuilder().setStringValue("").build();
            case BYTES_VALUE:
                return Value.newBuilder().setBytesValue(ByteString.EMPTY).build();
            case REFERENCE_VALUE:
                return refValue(DatabaseId.EMPTY, DocumentKey.empty());
            case GEO_POINT_VALUE:
                return Value.newBuilder().setGeoPointValue(LatLng.newBuilder().setLatitude(-90.0d).setLongitude(-180.0d)).build();
            case ARRAY_VALUE:
                return Value.newBuilder().setArrayValue(ArrayValue.DEFAULT_INSTANCE).build();
            case MAP_VALUE:
                return Value.newBuilder().setMapValue(MapValue.DEFAULT_INSTANCE).build();
            default:
                throw new IllegalArgumentException(a.a("Unknown value type: ", valueTypeCase));
        }
    }

    public static Value getUpperBound(Value.ValueTypeCase valueTypeCase) {
        switch (valueTypeCase) {
            case NULL_VALUE:
                return getLowerBound(Value.ValueTypeCase.BOOLEAN_VALUE);
            case BOOLEAN_VALUE:
                return getLowerBound(Value.ValueTypeCase.INTEGER_VALUE);
            case INTEGER_VALUE:
            case DOUBLE_VALUE:
                return getLowerBound(Value.ValueTypeCase.TIMESTAMP_VALUE);
            case TIMESTAMP_VALUE:
                return getLowerBound(Value.ValueTypeCase.STRING_VALUE);
            case STRING_VALUE:
                return getLowerBound(Value.ValueTypeCase.BYTES_VALUE);
            case BYTES_VALUE:
                return getLowerBound(Value.ValueTypeCase.REFERENCE_VALUE);
            case REFERENCE_VALUE:
                return getLowerBound(Value.ValueTypeCase.GEO_POINT_VALUE);
            case GEO_POINT_VALUE:
                return getLowerBound(Value.ValueTypeCase.ARRAY_VALUE);
            case ARRAY_VALUE:
                return getLowerBound(Value.ValueTypeCase.MAP_VALUE);
            case MAP_VALUE:
                return MAX_VALUE;
            default:
                throw new IllegalArgumentException(a.a("Unknown value type: ", valueTypeCase));
        }
    }

    public static boolean isArray(@Nullable Value value) {
        return value != null && value.getValueTypeCase() == Value.ValueTypeCase.ARRAY_VALUE;
    }

    public static boolean isDouble(@Nullable Value value) {
        return value != null && value.getValueTypeCase() == Value.ValueTypeCase.DOUBLE_VALUE;
    }

    public static boolean isInteger(@Nullable Value value) {
        return value != null && value.getValueTypeCase() == Value.ValueTypeCase.INTEGER_VALUE;
    }

    public static boolean isMapValue(@Nullable Value value) {
        return value != null && value.getValueTypeCase() == Value.ValueTypeCase.MAP_VALUE;
    }

    public static boolean isMaxValue(Value value) {
        return equals(value, MAX_VALUE);
    }

    public static boolean isNanValue(@Nullable Value value) {
        return value != null && Double.isNaN(value.getDoubleValue());
    }

    public static boolean isNullValue(@Nullable Value value) {
        return value != null && value.getValueTypeCase() == Value.ValueTypeCase.NULL_VALUE;
    }

    public static boolean isNumber(@Nullable Value value) {
        return isInteger(value) || isDouble(value);
    }

    public static boolean isReferenceValue(@Nullable Value value) {
        return value != null && value.getValueTypeCase() == Value.ValueTypeCase.REFERENCE_VALUE;
    }

    @Nullable
    public static Value max(@Nullable Value value, @Nullable Value value2) {
        if (value == null && value2 == null) {
            return null;
        }
        if (value == null) {
            return value2;
        }
        return (value2 != null && compare(value, value2) <= 0) ? value2 : value;
    }

    @Nullable
    public static Value min(@Nullable Value value, @Nullable Value value2) {
        if (value == null && value2 == null) {
            return null;
        }
        if (value == null) {
            return value2;
        }
        return (value2 != null && compare(value, value2) >= 0) ? value2 : value;
    }

    public static boolean numberEquals(Value value, Value value2) {
        if (value.getValueTypeCase() == Value.ValueTypeCase.INTEGER_VALUE && value2.getValueTypeCase() == Value.ValueTypeCase.INTEGER_VALUE) {
            return value.getIntegerValue() == value2.getIntegerValue();
        }
        return value.getValueTypeCase() == Value.ValueTypeCase.DOUBLE_VALUE && value2.getValueTypeCase() == Value.ValueTypeCase.DOUBLE_VALUE && Double.doubleToLongBits(value.getDoubleValue()) == Double.doubleToLongBits(value2.getDoubleValue());
    }

    public static boolean objectEquals(Value value, Value value2) {
        MapValue mapValue = value.getMapValue();
        MapValue mapValue2 = value2.getMapValue();
        if (mapValue.getFieldsCount() != mapValue2.getFieldsCount()) {
            return false;
        }
        for (Map.Entry<String, Value> entry : mapValue.getFieldsMap().entrySet()) {
            if (!equals(entry.getValue(), mapValue2.getFieldsMap().get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }

    public static Value refValue(DatabaseId databaseId, DocumentKey documentKey) {
        return Value.newBuilder().setReferenceValue(String.format("projects/%s/databases/%s/documents/%s", databaseId.getProjectId(), databaseId.getDatabaseId(), documentKey.toString())).build();
    }

    public static int typeOrder(Value value) {
        switch (value.getValueTypeCase()) {
            case NULL_VALUE:
                return 0;
            case BOOLEAN_VALUE:
                return 1;
            case INTEGER_VALUE:
            case DOUBLE_VALUE:
                return 2;
            case TIMESTAMP_VALUE:
                return 3;
            case STRING_VALUE:
                return 5;
            case BYTES_VALUE:
                return 6;
            case REFERENCE_VALUE:
                return 7;
            case GEO_POINT_VALUE:
                return 8;
            case ARRAY_VALUE:
                return 9;
            case MAP_VALUE:
                return ServerTimestamps.isServerTimestamp(value) ? 4 : 10;
            default:
                StringBuilder sbA = a.a("Invalid value type: ");
                sbA.append(value.getValueTypeCase());
                Assert.fail(sbA.toString(), new Object[0]);
                throw null;
        }
    }
}
