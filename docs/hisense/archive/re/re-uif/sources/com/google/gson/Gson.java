package com.google.gson;

import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.GsonInternalAccess;
import com.google.gson.internal.Primitives;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.internal.bind.BigDecimalTypeAdapter;
import com.google.gson.internal.bind.BigIntegerTypeAdapter;
import com.google.gson.internal.bind.CollectionTypeAdapterFactory;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.google.gson.internal.bind.MapTypeAdapterFactory;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.internal.bind.SqlDateTypeAdapter;
import com.google.gson.internal.bind.TimeTypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class Gson {
    private final ThreadLocal<Map<TypeToken<?>, FutureTypeAdapter<?>>> calls;
    private final ConstructorConstructor constructorConstructor;
    private final List<TypeAdapterFactory> factories;
    private final boolean serializeNulls;
    private final Map<TypeToken<?>, TypeAdapter<?>> typeTokenCache;

    public Gson() {
        this(Excluder.DEFAULT, FieldNamingPolicy.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, LongSerializationPolicy.DEFAULT, Collections.emptyList());
    }

    Gson(Excluder excluder, FieldNamingStrategy fieldNamingStrategy, Map<Type, InstanceCreator<?>> map, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, LongSerializationPolicy longSerializationPolicy, List<TypeAdapterFactory> list) {
        this.calls = new ThreadLocal<Map<TypeToken<?>, FutureTypeAdapter<?>>>(this) { // from class: com.google.gson.Gson.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // java.lang.ThreadLocal
            public Map<TypeToken<?>, FutureTypeAdapter<?>> initialValue() {
                return new HashMap();
            }
        };
        this.typeTokenCache = Collections.synchronizedMap(new HashMap());
        ConstructorConstructor constructorConstructor = new ConstructorConstructor(map);
        this.constructorConstructor = constructorConstructor;
        this.serializeNulls = z;
        ReflectiveTypeAdapterFactory reflectiveTypeAdapterFactory = new ReflectiveTypeAdapterFactory(constructorConstructor, fieldNamingStrategy, excluder);
        ConstructorConstructor constructorConstructor2 = new ConstructorConstructor();
        ArrayList arrayList = new ArrayList();
        arrayList.add(TypeAdapters.STRING_FACTORY);
        arrayList.add(TypeAdapters.INTEGER_FACTORY);
        arrayList.add(TypeAdapters.BOOLEAN_FACTORY);
        arrayList.add(TypeAdapters.BYTE_FACTORY);
        arrayList.add(TypeAdapters.SHORT_FACTORY);
        arrayList.add(TypeAdapters.newFactory(Long.TYPE, Long.class, longAdapter(longSerializationPolicy)));
        arrayList.add(TypeAdapters.newFactory(Double.TYPE, Double.class, doubleAdapter(z6)));
        arrayList.add(TypeAdapters.newFactory(Float.TYPE, Float.class, floatAdapter(z6)));
        arrayList.add(excluder);
        arrayList.add(TypeAdapters.NUMBER_FACTORY);
        arrayList.add(TypeAdapters.CHARACTER_FACTORY);
        arrayList.add(TypeAdapters.STRING_BUILDER_FACTORY);
        arrayList.add(TypeAdapters.STRING_BUFFER_FACTORY);
        arrayList.add(TypeAdapters.newFactory(BigDecimal.class, new BigDecimalTypeAdapter()));
        arrayList.add(TypeAdapters.newFactory(BigInteger.class, new BigIntegerTypeAdapter()));
        arrayList.add(TypeAdapters.JSON_ELEMENT_FACTORY);
        arrayList.add(ObjectTypeAdapter.FACTORY);
        arrayList.addAll(list);
        arrayList.add(new CollectionTypeAdapterFactory(constructorConstructor2));
        arrayList.add(TypeAdapters.URL_FACTORY);
        arrayList.add(TypeAdapters.URI_FACTORY);
        arrayList.add(TypeAdapters.UUID_FACTORY);
        arrayList.add(TypeAdapters.LOCALE_FACTORY);
        arrayList.add(TypeAdapters.INET_ADDRESS_FACTORY);
        arrayList.add(TypeAdapters.BIT_SET_FACTORY);
        arrayList.add(DateTypeAdapter.FACTORY);
        arrayList.add(TypeAdapters.CALENDAR_FACTORY);
        arrayList.add(TimeTypeAdapter.FACTORY);
        arrayList.add(SqlDateTypeAdapter.FACTORY);
        arrayList.add(TypeAdapters.TIMESTAMP_FACTORY);
        arrayList.add(new MapTypeAdapterFactory(constructorConstructor2, z2));
        arrayList.add(ArrayTypeAdapter.FACTORY);
        arrayList.add(TypeAdapters.ENUM_FACTORY);
        arrayList.add(TypeAdapters.CLASS_FACTORY);
        arrayList.add(reflectiveTypeAdapterFactory);
        this.factories = Collections.unmodifiableList(arrayList);
    }

    private TypeAdapter<Number> doubleAdapter(boolean z) {
        if (z) {
            return TypeAdapters.DOUBLE;
        }
        return new TypeAdapter<Number>() { // from class: com.google.gson.Gson.4
            @Override // com.google.gson.TypeAdapter
            public Number read(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                return Double.valueOf(jsonReader.nextDouble());
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, Number number) throws IOException {
                if (number == null) {
                    jsonWriter.nullValue();
                    return;
                }
                Gson.this.checkValidFloatingPoint(number.doubleValue());
                jsonWriter.value(number);
            }
        };
    }

    private TypeAdapter<Number> floatAdapter(boolean z) {
        if (z) {
            return TypeAdapters.FLOAT;
        }
        return new TypeAdapter<Number>() { // from class: com.google.gson.Gson.5
            @Override // com.google.gson.TypeAdapter
            public Number read(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                return Float.valueOf((float) jsonReader.nextDouble());
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, Number number) throws IOException {
                if (number == null) {
                    jsonWriter.nullValue();
                    return;
                }
                Gson.this.checkValidFloatingPoint(number.floatValue());
                jsonWriter.value(number);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkValidFloatingPoint(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new IllegalArgumentException(d + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialDoubleValues() method.");
        }
    }

    private TypeAdapter<Number> longAdapter(LongSerializationPolicy longSerializationPolicy) {
        if (longSerializationPolicy == LongSerializationPolicy.DEFAULT) {
            return TypeAdapters.LONG;
        }
        return new TypeAdapter<Number>(this) { // from class: com.google.gson.Gson.6
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.gson.TypeAdapter
            public Number read(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                return Long.valueOf(jsonReader.nextLong());
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, Number number) throws IOException {
                if (number == null) {
                    jsonWriter.nullValue();
                } else {
                    jsonWriter.value(number.toString());
                }
            }
        };
    }

    public <T> TypeAdapter<T> getAdapter(TypeToken<T> typeToken) {
        TypeAdapter<T> typeAdapter = (TypeAdapter) this.typeTokenCache.get(typeToken);
        if (typeAdapter != null) {
            return typeAdapter;
        }
        Map map = this.calls.get();
        FutureTypeAdapter futureTypeAdapter = (FutureTypeAdapter) map.get(typeToken);
        if (futureTypeAdapter != null) {
            return futureTypeAdapter;
        }
        FutureTypeAdapter futureTypeAdapter2 = new FutureTypeAdapter();
        map.put(typeToken, futureTypeAdapter2);
        try {
            Iterator<TypeAdapterFactory> it = this.factories.iterator();
            while (it.hasNext()) {
                TypeAdapter<T> typeAdapterCreate = it.next().create(this, typeToken);
                if (typeAdapterCreate != null) {
                    futureTypeAdapter2.setDelegate(typeAdapterCreate);
                    this.typeTokenCache.put(typeToken, typeAdapterCreate);
                    map.remove(typeToken);
                    return typeAdapterCreate;
                }
            }
            throw new IllegalArgumentException("GSON cannot handle " + typeToken);
        } catch (Throwable th) {
            map.remove(typeToken);
            throw th;
        }
    }

    static {
        GsonInternalAccess.INSTANCE = new GsonInternalAccess() { // from class: com.google.gson.Gson.7
            @Override // com.google.gson.internal.GsonInternalAccess
            public <T> TypeAdapter<T> getNextAdapter(Gson gson, TypeAdapterFactory typeAdapterFactory, TypeToken<T> typeToken) {
                boolean z = false;
                for (TypeAdapterFactory typeAdapterFactory2 : gson.factories) {
                    if (z) {
                        TypeAdapter<T> typeAdapterCreate = typeAdapterFactory2.create(gson, typeToken);
                        if (typeAdapterCreate != null) {
                            return typeAdapterCreate;
                        }
                    } else if (typeAdapterFactory2 == typeAdapterFactory) {
                        z = true;
                    }
                }
                throw new IllegalArgumentException("GSON cannot serialize " + typeToken);
            }
        };
    }

    public <T> TypeAdapter<T> getAdapter(Class<T> cls) {
        return getAdapter(TypeToken.get((Class) cls));
    }

    public <T> T fromJson(String str, Class<T> cls) throws JsonSyntaxException {
        return (T) Primitives.wrap(cls).cast(fromJson(str, (Type) cls));
    }

    public <T> T fromJson(String str, Type type) throws JsonSyntaxException {
        if (str == null) {
            return null;
        }
        return (T) fromJson(new StringReader(str), type);
    }

    public <T> T fromJson(Reader reader, Type type) throws JsonSyntaxException, JsonIOException {
        JsonReader jsonReader = new JsonReader(reader);
        T t = (T) fromJson(jsonReader, type);
        assertFullConsumption(t, jsonReader);
        return t;
    }

    private static void assertFullConsumption(Object obj, JsonReader jsonReader) {
        if (obj != null) {
            try {
                if (jsonReader.peek() == JsonToken.END_DOCUMENT) {
                } else {
                    throw new JsonIOException("JSON document was not fully consumed.");
                }
            } catch (MalformedJsonException e) {
                throw new JsonSyntaxException(e);
            } catch (IOException e2) {
                throw new JsonIOException(e2);
            }
        }
    }

    public <T> T fromJson(JsonReader jsonReader, Type type) throws JsonSyntaxException, JsonIOException {
        boolean zIsLenient = jsonReader.isLenient();
        boolean z = true;
        jsonReader.setLenient(true);
        try {
            try {
                try {
                    jsonReader.peek();
                    z = false;
                    T t = getAdapter(TypeToken.get(type)).read(jsonReader);
                    jsonReader.setLenient(zIsLenient);
                    return t;
                } catch (IOException e) {
                    throw new JsonSyntaxException(e);
                }
            } catch (EOFException e2) {
                if (!z) {
                    throw new JsonSyntaxException(e2);
                }
                jsonReader.setLenient(zIsLenient);
                return null;
            } catch (IllegalStateException e3) {
                throw new JsonSyntaxException(e3);
            }
        } catch (Throwable th) {
            jsonReader.setLenient(zIsLenient);
            throw th;
        }
    }

    static class FutureTypeAdapter<T> extends TypeAdapter<T> {
        private TypeAdapter<T> delegate;

        FutureTypeAdapter() {
        }

        public void setDelegate(TypeAdapter<T> typeAdapter) {
            if (this.delegate != null) {
                throw new AssertionError();
            }
            this.delegate = typeAdapter;
        }

        @Override // com.google.gson.TypeAdapter
        public T read(JsonReader jsonReader) throws IOException {
            TypeAdapter<T> typeAdapter = this.delegate;
            if (typeAdapter == null) {
                throw new IllegalStateException();
            }
            return typeAdapter.read(jsonReader);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, T t) throws IOException {
            TypeAdapter<T> typeAdapter = this.delegate;
            if (typeAdapter == null) {
                throw new IllegalStateException();
            }
            typeAdapter.write(jsonWriter, t);
        }
    }

    public String toString() {
        return "{serializeNulls:" + this.serializeNulls + "factories:" + this.factories + ",instanceCreators:" + this.constructorConstructor + "}";
    }
}
