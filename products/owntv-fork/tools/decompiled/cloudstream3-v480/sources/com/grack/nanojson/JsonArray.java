package com.grack.nanojson;

import j$.util.function.Function$CC;
import j$.util.stream.Stream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;

/* JADX INFO: loaded from: classes6.dex */
public class JsonArray extends ArrayList<Object> {
    private static final long serialVersionUID = 1;

    public JsonArray() {
    }

    public JsonArray(int i) {
        super(i);
    }

    public JsonArray(Collection<?> collection) {
        super(collection);
    }

    public static JsonBuilder<JsonArray> builder() {
        return new JsonBuilder<>(new JsonArray());
    }

    public static JsonArray from(Object... objArr) {
        return new JsonArray(Arrays.asList(objArr));
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public Object get(int i) {
        if (i < size()) {
            return super.get(i);
        }
        return null;
    }

    public JsonArray getArray(int i) {
        return getArray(i, new JsonArray());
    }

    public JsonArray getArray(int i, JsonArray jsonArray) {
        Object obj = get(i);
        return obj instanceof JsonArray ? (JsonArray) obj : jsonArray;
    }

    public boolean getBoolean(int i) {
        return getBoolean(i, false);
    }

    public boolean getBoolean(int i, Boolean bool) {
        Object obj = get(i);
        return obj instanceof Boolean ? ((Boolean) obj).booleanValue() : bool.booleanValue();
    }

    public double getDouble(int i) {
        return getDouble(i, 0.0d);
    }

    public double getDouble(int i, double d) {
        Object obj = get(i);
        return obj instanceof Number ? ((Number) obj).doubleValue() : d;
    }

    public float getFloat(int i) {
        return getFloat(i, 0.0f);
    }

    public float getFloat(int i, float f) {
        Object obj = get(i);
        return obj instanceof Number ? ((Number) obj).floatValue() : f;
    }

    public int getInt(int i) {
        return getInt(i, 0);
    }

    public int getInt(int i, int i2) {
        Object obj = get(i);
        return obj instanceof Number ? ((Number) obj).intValue() : i2;
    }

    public long getLong(int i) {
        return getLong(i, 0L);
    }

    public long getLong(int i, long j) {
        Object obj = get(i);
        return obj instanceof Number ? ((Number) obj).longValue() : j;
    }

    public Number getNumber(int i) {
        return getNumber(i, null);
    }

    public Number getNumber(int i, Number number) {
        Object obj = get(i);
        return obj instanceof Number ? (Number) obj : number;
    }

    public JsonObject getObject(int i) {
        return getObject(i, new JsonObject());
    }

    public JsonObject getObject(int i, JsonObject jsonObject) {
        Object obj = get(i);
        return obj instanceof JsonObject ? (JsonObject) obj : jsonObject;
    }

    public String getString(int i) {
        return getString(i, null);
    }

    public String getString(int i, String str) {
        Object obj = get(i);
        return obj instanceof String ? (String) obj : str;
    }

    public boolean has(int i) {
        return i < size();
    }

    public boolean isBoolean(int i) {
        return get(i) instanceof Boolean;
    }

    public boolean isNull(int i) {
        return i < size() && get(i) == null;
    }

    public boolean isNumber(int i) {
        return get(i) instanceof Number;
    }

    public boolean isString(int i) {
        return get(i) instanceof String;
    }

    public <T> Stream<T> streamAs(final Class<T> cls) {
        Stream stream = j$.util.Collection.EL.stream(this);
        cls.getClass();
        Stream<T> streamFilter = stream.filter(new JsonArray$$ExternalSyntheticLambda0(cls));
        cls.getClass();
        return (Stream<T>) streamFilter.map(new Function() { // from class: com.grack.nanojson.JsonArray$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return cls.cast(obj);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        });
    }

    public Stream<JsonObject> streamAsJsonObjects() {
        return streamAs(JsonObject.class);
    }
}
