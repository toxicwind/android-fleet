package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class StdArraySerializers {
    protected static final HashMap<String, JsonSerializer<?>> _arraySerializers;

    static {
        HashMap<String, JsonSerializer<?>> map = new HashMap<>();
        _arraySerializers = map;
        map.put(boolean[].class.getName(), new BooleanArraySerializer());
        _arraySerializers.put(byte[].class.getName(), new ByteArraySerializer());
        _arraySerializers.put(char[].class.getName(), new CharArraySerializer());
        _arraySerializers.put(short[].class.getName(), new ShortArraySerializer());
        _arraySerializers.put(int[].class.getName(), new IntArraySerializer());
        _arraySerializers.put(long[].class.getName(), new LongArraySerializer());
        _arraySerializers.put(float[].class.getName(), new FloatArraySerializer());
        _arraySerializers.put(double[].class.getName(), new DoubleArraySerializer());
    }

    public static JsonSerializer<?> findStandardImpl(Class<?> cls) {
        return _arraySerializers.get(cls.getName());
    }

    protected static abstract class TypedPrimitiveArraySerializer<T> extends ArraySerializerBase<T> {
        @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
        public final ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return this;
        }

        protected TypedPrimitiveArraySerializer(Class<T> cls) {
            super(cls);
        }

        protected TypedPrimitiveArraySerializer(TypedPrimitiveArraySerializer<T> typedPrimitiveArraySerializer, BeanProperty beanProperty, Boolean bool) {
            super(typedPrimitiveArraySerializer, beanProperty, bool);
        }
    }

    @JacksonStdImpl
    public static class BooleanArraySerializer extends ArraySerializerBase<boolean[]> {
        @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
        public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return this;
        }

        static {
            TypeFactory.defaultInstance().uncheckedSimpleType(Boolean.class);
        }

        public BooleanArraySerializer() {
            super(boolean[].class);
        }

        protected BooleanArraySerializer(BooleanArraySerializer booleanArraySerializer, BeanProperty beanProperty, Boolean bool) {
            super(booleanArraySerializer, beanProperty, bool);
        }

        @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase
        public JsonSerializer<?> _withResolved(BeanProperty beanProperty, Boolean bool) {
            return new BooleanArraySerializer(this, beanProperty, bool);
        }

        @Override // com.fasterxml.jackson.databind.JsonSerializer
        public boolean isEmpty(SerializerProvider serializerProvider, boolean[] zArr) {
            return zArr.length == 0;
        }

        @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
        public boolean hasSingleElement(boolean[] zArr) {
            return zArr.length == 1;
        }

        @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase, com.fasterxml.jackson.databind.JsonSerializer
        public final void serialize(boolean[] zArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            int length = zArr.length;
            if (length == 1 && _shouldUnwrapSingle(serializerProvider)) {
                serializeContents(zArr, jsonGenerator, serializerProvider);
                return;
            }
            jsonGenerator.writeStartArray(zArr, length);
            serializeContents(zArr, jsonGenerator, serializerProvider);
            jsonGenerator.writeEndArray();
        }

        @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase
        public void serializeContents(boolean[] zArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            for (boolean z : zArr) {
                jsonGenerator.writeBoolean(z);
            }
        }
    }

    @JacksonStdImpl
    public static class ShortArraySerializer extends TypedPrimitiveArraySerializer<short[]> {
        static {
            TypeFactory.defaultInstance().uncheckedSimpleType(Short.TYPE);
        }

        public ShortArraySerializer() {
            super(short[].class);
        }

        public ShortArraySerializer(ShortArraySerializer shortArraySerializer, BeanProperty beanProperty, Boolean bool) {
            super(shortArraySerializer, beanProperty, bool);
        }

        @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase
        public JsonSerializer<?> _withResolved(BeanProperty beanProperty, Boolean bool) {
            return new ShortArraySerializer(this, beanProperty, bool);
        }

        @Override // com.fasterxml.jackson.databind.JsonSerializer
        public boolean isEmpty(SerializerProvider serializerProvider, short[] sArr) {
            return sArr.length == 0;
        }

        @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
        public boolean hasSingleElement(short[] sArr) {
            return sArr.length == 1;
        }

        @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase, com.fasterxml.jackson.databind.JsonSerializer
        public final void serialize(short[] sArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            int length = sArr.length;
            if (length == 1 && _shouldUnwrapSingle(serializerProvider)) {
                serializeContents(sArr, jsonGenerator, serializerProvider);
                return;
            }
            jsonGenerator.writeStartArray(sArr, length);
            serializeContents(sArr, jsonGenerator, serializerProvider);
            jsonGenerator.writeEndArray();
        }

        @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase
        public void serializeContents(short[] sArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            for (short s : sArr) {
                jsonGenerator.writeNumber((int) s);
            }
        }
    }

    @JacksonStdImpl
    public static class CharArraySerializer extends StdSerializer<char[]> {
        public CharArraySerializer() {
            super(char[].class);
        }

        @Override // com.fasterxml.jackson.databind.JsonSerializer
        public boolean isEmpty(SerializerProvider serializerProvider, char[] cArr) {
            return cArr.length == 0;
        }

        @Override // com.fasterxml.jackson.databind.JsonSerializer
        public void serialize(char[] cArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if (serializerProvider.isEnabled(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS)) {
                jsonGenerator.writeStartArray(cArr, cArr.length);
                _writeArrayContents(jsonGenerator, cArr);
                jsonGenerator.writeEndArray();
                return;
            }
            jsonGenerator.writeString(cArr, 0, cArr.length);
        }

        @Override // com.fasterxml.jackson.databind.JsonSerializer
        public void serializeWithType(char[] cArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
            WritableTypeId writableTypeIdWriteTypePrefix;
            if (serializerProvider.isEnabled(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS)) {
                writableTypeIdWriteTypePrefix = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(cArr, JsonToken.START_ARRAY));
                _writeArrayContents(jsonGenerator, cArr);
            } else {
                writableTypeIdWriteTypePrefix = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(cArr, JsonToken.VALUE_STRING));
                jsonGenerator.writeString(cArr, 0, cArr.length);
            }
            typeSerializer.writeTypeSuffix(jsonGenerator, writableTypeIdWriteTypePrefix);
        }

        private final void _writeArrayContents(JsonGenerator jsonGenerator, char[] cArr) throws IOException {
            int length = cArr.length;
            for (int i = 0; i < length; i++) {
                jsonGenerator.writeString(cArr, i, 1);
            }
        }
    }

    @JacksonStdImpl
    public static class IntArraySerializer extends ArraySerializerBase<int[]> {
        @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
        public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return this;
        }

        static {
            TypeFactory.defaultInstance().uncheckedSimpleType(Integer.TYPE);
        }

        public IntArraySerializer() {
            super(int[].class);
        }

        protected IntArraySerializer(IntArraySerializer intArraySerializer, BeanProperty beanProperty, Boolean bool) {
            super(intArraySerializer, beanProperty, bool);
        }

        @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase
        public JsonSerializer<?> _withResolved(BeanProperty beanProperty, Boolean bool) {
            return new IntArraySerializer(this, beanProperty, bool);
        }

        @Override // com.fasterxml.jackson.databind.JsonSerializer
        public boolean isEmpty(SerializerProvider serializerProvider, int[] iArr) {
            return iArr.length == 0;
        }

        @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
        public boolean hasSingleElement(int[] iArr) {
            return iArr.length == 1;
        }

        @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase, com.fasterxml.jackson.databind.JsonSerializer
        public final void serialize(int[] iArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if (iArr.length == 1 && _shouldUnwrapSingle(serializerProvider)) {
                serializeContents(iArr, jsonGenerator, serializerProvider);
            } else {
                jsonGenerator.writeArray(iArr, 0, iArr.length);
            }
        }

        @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase
        public void serializeContents(int[] iArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            for (int i : iArr) {
                jsonGenerator.writeNumber(i);
            }
        }
    }

    @JacksonStdImpl
    public static class LongArraySerializer extends TypedPrimitiveArraySerializer<long[]> {
        static {
            TypeFactory.defaultInstance().uncheckedSimpleType(Long.TYPE);
        }

        public LongArraySerializer() {
            super(long[].class);
        }

        public LongArraySerializer(LongArraySerializer longArraySerializer, BeanProperty beanProperty, Boolean bool) {
            super(longArraySerializer, beanProperty, bool);
        }

        @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase
        public JsonSerializer<?> _withResolved(BeanProperty beanProperty, Boolean bool) {
            return new LongArraySerializer(this, beanProperty, bool);
        }

        @Override // com.fasterxml.jackson.databind.JsonSerializer
        public boolean isEmpty(SerializerProvider serializerProvider, long[] jArr) {
            return jArr.length == 0;
        }

        @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
        public boolean hasSingleElement(long[] jArr) {
            return jArr.length == 1;
        }

        @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase, com.fasterxml.jackson.databind.JsonSerializer
        public final void serialize(long[] jArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if (jArr.length == 1 && _shouldUnwrapSingle(serializerProvider)) {
                serializeContents(jArr, jsonGenerator, serializerProvider);
            } else {
                jsonGenerator.writeArray(jArr, 0, jArr.length);
            }
        }

        @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase
        public void serializeContents(long[] jArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            for (long j : jArr) {
                jsonGenerator.writeNumber(j);
            }
        }
    }

    @JacksonStdImpl
    public static class FloatArraySerializer extends TypedPrimitiveArraySerializer<float[]> {
        static {
            TypeFactory.defaultInstance().uncheckedSimpleType(Float.TYPE);
        }

        public FloatArraySerializer() {
            super(float[].class);
        }

        public FloatArraySerializer(FloatArraySerializer floatArraySerializer, BeanProperty beanProperty, Boolean bool) {
            super(floatArraySerializer, beanProperty, bool);
        }

        @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase
        public JsonSerializer<?> _withResolved(BeanProperty beanProperty, Boolean bool) {
            return new FloatArraySerializer(this, beanProperty, bool);
        }

        @Override // com.fasterxml.jackson.databind.JsonSerializer
        public boolean isEmpty(SerializerProvider serializerProvider, float[] fArr) {
            return fArr.length == 0;
        }

        @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
        public boolean hasSingleElement(float[] fArr) {
            return fArr.length == 1;
        }

        @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase, com.fasterxml.jackson.databind.JsonSerializer
        public final void serialize(float[] fArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            int length = fArr.length;
            if (length == 1 && _shouldUnwrapSingle(serializerProvider)) {
                serializeContents(fArr, jsonGenerator, serializerProvider);
                return;
            }
            jsonGenerator.writeStartArray(fArr, length);
            serializeContents(fArr, jsonGenerator, serializerProvider);
            jsonGenerator.writeEndArray();
        }

        @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase
        public void serializeContents(float[] fArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            for (float f : fArr) {
                jsonGenerator.writeNumber(f);
            }
        }
    }

    @JacksonStdImpl
    public static class DoubleArraySerializer extends ArraySerializerBase<double[]> {
        @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
        public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return this;
        }

        static {
            TypeFactory.defaultInstance().uncheckedSimpleType(Double.TYPE);
        }

        public DoubleArraySerializer() {
            super(double[].class);
        }

        protected DoubleArraySerializer(DoubleArraySerializer doubleArraySerializer, BeanProperty beanProperty, Boolean bool) {
            super(doubleArraySerializer, beanProperty, bool);
        }

        @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase
        public JsonSerializer<?> _withResolved(BeanProperty beanProperty, Boolean bool) {
            return new DoubleArraySerializer(this, beanProperty, bool);
        }

        @Override // com.fasterxml.jackson.databind.JsonSerializer
        public boolean isEmpty(SerializerProvider serializerProvider, double[] dArr) {
            return dArr.length == 0;
        }

        @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
        public boolean hasSingleElement(double[] dArr) {
            return dArr.length == 1;
        }

        @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase, com.fasterxml.jackson.databind.JsonSerializer
        public final void serialize(double[] dArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if (dArr.length == 1 && _shouldUnwrapSingle(serializerProvider)) {
                serializeContents(dArr, jsonGenerator, serializerProvider);
            } else {
                jsonGenerator.writeArray(dArr, 0, dArr.length);
            }
        }

        @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase
        public void serializeContents(double[] dArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            for (double d : dArr) {
                jsonGenerator.writeNumber(d);
            }
        }
    }
}
