package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import java.io.IOException;

/* JADX WARN: Unexpected interfaces in signature: [java.lang.Object, java.lang.Object] */
/* JADX INFO: loaded from: classes.dex */
@JacksonStdImpl
public class JsonValueSerializer extends StdSerializer<Object> implements ContextualSerializer {
    protected final AnnotatedMember _accessor;
    protected final boolean _forceTypeInformation;
    protected final BeanProperty _property;
    protected final JsonSerializer<Object> _valueSerializer;

    public JsonValueSerializer(AnnotatedMember annotatedMember, JsonSerializer<?> jsonSerializer) {
        super(annotatedMember.getType());
        this._accessor = annotatedMember;
        this._valueSerializer = jsonSerializer;
        this._property = null;
        this._forceTypeInformation = true;
    }

    public JsonValueSerializer(JsonValueSerializer jsonValueSerializer, BeanProperty beanProperty, JsonSerializer<?> jsonSerializer, boolean z) {
        super(_notNullClass(jsonValueSerializer.handledType()));
        this._accessor = jsonValueSerializer._accessor;
        this._valueSerializer = jsonSerializer;
        this._property = beanProperty;
        this._forceTypeInformation = z;
    }

    private static final Class<Object> _notNullClass(Class<?> cls) {
        return cls == null ? Object.class : cls;
    }

    public JsonValueSerializer withResolved(BeanProperty beanProperty, JsonSerializer<?> jsonSerializer, boolean z) {
        return (this._property == beanProperty && this._valueSerializer == jsonSerializer && z == this._forceTypeInformation) ? this : new JsonValueSerializer(this, beanProperty, jsonSerializer, z);
    }

    @Override // com.fasterxml.jackson.databind.ser.ContextualSerializer
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<?> jsonSerializer = this._valueSerializer;
        if (jsonSerializer == null) {
            JavaType type = this._accessor.getType();
            if (!serializerProvider.isEnabled(MapperFeature.USE_STATIC_TYPING) && !type.isFinal()) {
                return this;
            }
            JsonSerializer<Object> jsonSerializerFindPrimaryPropertySerializer = serializerProvider.findPrimaryPropertySerializer(type, beanProperty);
            return withResolved(beanProperty, jsonSerializerFindPrimaryPropertySerializer, isNaturalTypeWithStdHandling(type.getRawClass(), jsonSerializerFindPrimaryPropertySerializer));
        }
        return withResolved(beanProperty, serializerProvider.handlePrimaryContextualization(jsonSerializer, beanProperty), this._forceTypeInformation);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        try {
            Object value = this._accessor.getValue(obj);
            if (value == null) {
                serializerProvider.defaultSerializeNull(jsonGenerator);
                return;
            }
            JsonSerializer<Object> jsonSerializerFindTypedValueSerializer = this._valueSerializer;
            if (jsonSerializerFindTypedValueSerializer == null) {
                jsonSerializerFindTypedValueSerializer = serializerProvider.findTypedValueSerializer(value.getClass(), true, this._property);
            }
            jsonSerializerFindTypedValueSerializer.serialize(value, jsonGenerator, serializerProvider);
        } catch (Exception e) {
            wrapAndThrow(serializerProvider, e, obj, this._accessor.getName() + "()");
            throw null;
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        try {
            Object value = this._accessor.getValue(obj);
            if (value == null) {
                serializerProvider.defaultSerializeNull(jsonGenerator);
                return;
            }
            JsonSerializer<Object> jsonSerializerFindValueSerializer = this._valueSerializer;
            if (jsonSerializerFindValueSerializer == null) {
                jsonSerializerFindValueSerializer = serializerProvider.findValueSerializer(value.getClass(), this._property);
            } else if (this._forceTypeInformation) {
                WritableTypeId writableTypeIdWriteTypePrefix = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(obj, JsonToken.VALUE_STRING));
                jsonSerializerFindValueSerializer.serialize(value, jsonGenerator, serializerProvider);
                typeSerializer.writeTypeSuffix(jsonGenerator, writableTypeIdWriteTypePrefix);
                return;
            }
            jsonSerializerFindValueSerializer.serializeWithType(value, jsonGenerator, serializerProvider, new TypeSerializerRerouter(typeSerializer, obj));
        } catch (Exception e) {
            wrapAndThrow(serializerProvider, e, obj, this._accessor.getName() + "()");
            throw null;
        }
    }

    protected boolean isNaturalTypeWithStdHandling(Class<?> cls, JsonSerializer<?> jsonSerializer) {
        if (cls.isPrimitive()) {
            if (cls != Integer.TYPE && cls != Boolean.TYPE && cls != Double.TYPE) {
                return false;
            }
        } else if (cls != String.class && cls != Integer.class && cls != Boolean.class && cls != Double.class) {
            return false;
        }
        return isDefaultSerializer(jsonSerializer);
    }

    public String toString() {
        return "(@JsonValue serializer for method " + this._accessor.getDeclaringClass() + "#" + this._accessor.getName() + ")";
    }

    static class TypeSerializerRerouter extends TypeSerializer {
        protected final Object _forObject;
        protected final TypeSerializer _typeSerializer;

        public TypeSerializerRerouter(TypeSerializer typeSerializer, Object obj) {
            this._typeSerializer = typeSerializer;
            this._forObject = obj;
        }

        @Override // com.fasterxml.jackson.databind.jsontype.TypeSerializer
        public TypeSerializer forProperty(BeanProperty beanProperty) {
            throw new UnsupportedOperationException();
        }

        @Override // com.fasterxml.jackson.databind.jsontype.TypeSerializer
        public JsonTypeInfo.As getTypeInclusion() {
            return this._typeSerializer.getTypeInclusion();
        }

        @Override // com.fasterxml.jackson.databind.jsontype.TypeSerializer
        public String getPropertyName() {
            return this._typeSerializer.getPropertyName();
        }

        @Override // com.fasterxml.jackson.databind.jsontype.TypeSerializer
        public WritableTypeId writeTypePrefix(JsonGenerator jsonGenerator, WritableTypeId writableTypeId) throws IOException {
            writableTypeId.forValue = this._forObject;
            return this._typeSerializer.writeTypePrefix(jsonGenerator, writableTypeId);
        }

        @Override // com.fasterxml.jackson.databind.jsontype.TypeSerializer
        public WritableTypeId writeTypeSuffix(JsonGenerator jsonGenerator, WritableTypeId writableTypeId) throws IOException {
            return this._typeSerializer.writeTypeSuffix(jsonGenerator, writableTypeId);
        }
    }
}
