package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.ArraySerializerBase;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
@JacksonStdImpl
public class StringArraySerializer extends ArraySerializerBase<String[]> implements ContextualSerializer {
    public static final StringArraySerializer instance;
    protected final JsonSerializer<Object> _elementSerializer;

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
        return this;
    }

    static {
        TypeFactory.defaultInstance().uncheckedSimpleType(String.class);
        instance = new StringArraySerializer();
    }

    protected StringArraySerializer() {
        super(String[].class);
        this._elementSerializer = null;
    }

    public StringArraySerializer(StringArraySerializer stringArraySerializer, BeanProperty beanProperty, JsonSerializer<?> jsonSerializer, Boolean bool) {
        super(stringArraySerializer, beanProperty, bool);
        this._elementSerializer = jsonSerializer;
    }

    @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase
    public JsonSerializer<?> _withResolved(BeanProperty beanProperty, Boolean bool) {
        return new StringArraySerializer(this, beanProperty, this._elementSerializer, bool);
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0018  */
    @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase, com.fasterxml.jackson.databind.ser.ContextualSerializer
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<Object> jsonSerializerSerializerInstance;
        Object objFindContentSerializer;
        if (beanProperty != null) {
            AnnotationIntrospector annotationIntrospector = serializerProvider.getAnnotationIntrospector();
            AnnotatedMember member = beanProperty.getMember();
            if (member == null || (objFindContentSerializer = annotationIntrospector.findContentSerializer(member)) == null) {
                jsonSerializerSerializerInstance = null;
            } else {
                jsonSerializerSerializerInstance = serializerProvider.serializerInstance(member, objFindContentSerializer);
            }
        } else {
            jsonSerializerSerializerInstance = null;
        }
        Boolean boolFindFormatFeature = findFormatFeature(serializerProvider, beanProperty, String[].class, JsonFormat.Feature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED);
        if (jsonSerializerSerializerInstance == null) {
            jsonSerializerSerializerInstance = this._elementSerializer;
        }
        JsonSerializer<?> jsonSerializerFindContextualConvertingSerializer = findContextualConvertingSerializer(serializerProvider, beanProperty, jsonSerializerSerializerInstance);
        if (jsonSerializerFindContextualConvertingSerializer == null) {
            jsonSerializerFindContextualConvertingSerializer = serializerProvider.findContentValueSerializer(String.class, beanProperty);
        }
        JsonSerializer<?> jsonSerializer = isDefaultSerializer(jsonSerializerFindContextualConvertingSerializer) ? null : jsonSerializerFindContextualConvertingSerializer;
        return (jsonSerializer == this._elementSerializer && boolFindFormatFeature == this._unwrapSingle) ? this : new StringArraySerializer(this, beanProperty, jsonSerializer, boolFindFormatFeature);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public boolean isEmpty(SerializerProvider serializerProvider, String[] strArr) {
        return strArr.length == 0;
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public boolean hasSingleElement(String[] strArr) {
        return strArr.length == 1;
    }

    @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase, com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(String[] strArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        int length = strArr.length;
        if (length == 1 && ((this._unwrapSingle == null && serializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) || this._unwrapSingle == Boolean.TRUE)) {
            serializeContents(strArr, jsonGenerator, serializerProvider);
            return;
        }
        jsonGenerator.writeStartArray(strArr, length);
        serializeContents(strArr, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndArray();
    }

    @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase
    public void serializeContents(String[] strArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        int length = strArr.length;
        if (length == 0) {
            return;
        }
        JsonSerializer<Object> jsonSerializer = this._elementSerializer;
        if (jsonSerializer != null) {
            serializeContentsSlow(strArr, jsonGenerator, serializerProvider, jsonSerializer);
            return;
        }
        for (int i = 0; i < length; i++) {
            if (strArr[i] == null) {
                jsonGenerator.writeNull();
            } else {
                jsonGenerator.writeString(strArr[i]);
            }
        }
    }

    private void serializeContentsSlow(String[] strArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) throws IOException {
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            if (strArr[i] == null) {
                serializerProvider.defaultSerializeNull(jsonGenerator);
            } else {
                jsonSerializer.serialize(strArr[i], jsonGenerator, serializerProvider);
            }
        }
    }
}
