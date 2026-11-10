package com.google.firebase.firestore.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.a.a.a.a;
import com.google.firebase.firestore.model.mutation.FieldMask;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.v1.MapValue;
import com.google.firestore.v1.Value;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class ObjectValue implements Cloneable {
    public final Map<String, Object> overlayMap;
    public Value partialValue;

    public ObjectValue(Value value) {
        this.overlayMap = new HashMap();
        Assert.hardAssert(value.getValueTypeCase() == Value.ValueTypeCase.MAP_VALUE, "ObjectValues should be backed by a MapValue", new Object[0]);
        Assert.hardAssert(!ServerTimestamps.isServerTimestamp(value), "ServerTimestamps should not be used as an ObjectValue", new Object[0]);
        this.partialValue = value;
    }

    @Nullable
    private MapValue applyOverlay(FieldPath fieldPath, Map<String, Object> map) {
        Value valueExtractNestedValue = extractNestedValue(this.partialValue, fieldPath);
        MapValue.Builder builder = Values.isMapValue(valueExtractNestedValue) ? valueExtractNestedValue.getMapValue().toBuilder() : MapValue.newBuilder();
        boolean z = false;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Map) {
                MapValue mapValueApplyOverlay = applyOverlay(fieldPath.append(key), (Map) value);
                if (mapValueApplyOverlay != null) {
                    builder.putFields(key, Value.newBuilder().setMapValue(mapValueApplyOverlay).build());
                    z = true;
                }
            } else {
                if (value instanceof Value) {
                    builder.putFields(key, (Value) value);
                } else if (builder.containsFields(key)) {
                    Assert.hardAssert(value == null, "Expected entry to be a Map, a Value or null", new Object[0]);
                    builder.removeFields(key);
                }
                z = true;
            }
        }
        if (z) {
            return builder.build();
        }
        return null;
    }

    private Value buildProto() {
        synchronized (this.overlayMap) {
            MapValue mapValueApplyOverlay = applyOverlay(FieldPath.EMPTY_PATH, this.overlayMap);
            if (mapValueApplyOverlay != null) {
                this.partialValue = Value.newBuilder().setMapValue(mapValueApplyOverlay).build();
                this.overlayMap.clear();
            }
        }
        return this.partialValue;
    }

    private FieldMask extractFieldMask(MapValue mapValue) {
        HashSet hashSet = new HashSet();
        for (Map.Entry<String, Value> entry : mapValue.getFieldsMap().entrySet()) {
            FieldPath fieldPathFromSingleSegment = FieldPath.fromSingleSegment(entry.getKey());
            if (Values.isMapValue(entry.getValue())) {
                Set<FieldPath> mask = extractFieldMask(entry.getValue().getMapValue()).getMask();
                if (mask.isEmpty()) {
                    hashSet.add(fieldPathFromSingleSegment);
                } else {
                    Iterator<FieldPath> it = mask.iterator();
                    while (it.hasNext()) {
                        hashSet.add(fieldPathFromSingleSegment.append(it.next()));
                    }
                }
            } else {
                hashSet.add(fieldPathFromSingleSegment);
            }
        }
        return new FieldMask(hashSet);
    }

    @Nullable
    private Value extractNestedValue(Value value, FieldPath fieldPath) {
        if (fieldPath.isEmpty()) {
            return value;
        }
        for (int i = 0; i < fieldPath.length() - 1; i++) {
            value = value.getMapValue().getFieldsOrDefault(fieldPath.getSegment(i), null);
            if (!Values.isMapValue(value)) {
                return null;
            }
        }
        return value.getMapValue().getFieldsOrDefault(fieldPath.getLastSegment(), null);
    }

    public static ObjectValue fromMap(Map<String, Value> map) {
        return new ObjectValue(Value.newBuilder().setMapValue(MapValue.newBuilder().putAllFields(map)).build());
    }

    private void setOverlay(FieldPath fieldPath, @Nullable Value value) {
        Map<String, Object> map;
        Map<String, Object> map2 = this.overlayMap;
        for (int i = 0; i < fieldPath.length() - 1; i++) {
            String segment = fieldPath.getSegment(i);
            Object obj = map2.get(segment);
            if (obj instanceof Map) {
                map = (Map) obj;
            } else {
                if (obj instanceof Value) {
                    Value value2 = (Value) obj;
                    if (value2.getValueTypeCase() == Value.ValueTypeCase.MAP_VALUE) {
                        HashMap map3 = new HashMap(value2.getMapValue().getFieldsMap());
                        map2.put(segment, map3);
                        map2 = map3;
                    }
                }
                map = new HashMap<>();
                map2.put(segment, map);
            }
            map2 = map;
        }
        map2.put(fieldPath.getLastSegment(), value);
    }

    public void delete(FieldPath fieldPath) {
        Assert.hardAssert(!fieldPath.isEmpty(), "Cannot delete field for empty path on ObjectValue", new Object[0]);
        setOverlay(fieldPath, null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ObjectValue) {
            return Values.equals(buildProto(), ((ObjectValue) obj).buildProto());
        }
        return false;
    }

    @Nullable
    public Value get(FieldPath fieldPath) {
        return extractNestedValue(buildProto(), fieldPath);
    }

    public FieldMask getFieldMask() {
        return extractFieldMask(buildProto().getMapValue());
    }

    public Map<String, Value> getFieldsMap() {
        return buildProto().getMapValue().getFieldsMap();
    }

    public int hashCode() {
        return buildProto().hashCode();
    }

    public void set(FieldPath fieldPath, Value value) {
        Assert.hardAssert(!fieldPath.isEmpty(), "Cannot set field for empty path on ObjectValue", new Object[0]);
        setOverlay(fieldPath, value);
    }

    public void setAll(Map<FieldPath, Value> map) {
        for (Map.Entry<FieldPath, Value> entry : map.entrySet()) {
            FieldPath key = entry.getKey();
            if (entry.getValue() == null) {
                delete(key);
            } else {
                set(key, entry.getValue());
            }
        }
    }

    @NonNull
    public String toString() {
        StringBuilder sbA = a.a("ObjectValue{internalValue=");
        sbA.append(Values.canonicalId(buildProto()));
        sbA.append('}');
        return sbA.toString();
    }

    @NonNull
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public ObjectValue m4clone() {
        return new ObjectValue(buildProto());
    }

    public ObjectValue() {
        this(Value.newBuilder().setMapValue(MapValue.DEFAULT_INSTANCE).build());
    }
}
