package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.util.EnumValues;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
@JacksonStdImpl
public class EnumSerializer extends StdScalarSerializer<Enum<?>> implements ContextualSerializer {
    private static final long serialVersionUID = 1;
    protected final Boolean _serializeAsIndex;
    protected final EnumValues _values;

    public EnumSerializer(EnumValues enumValues, Boolean bool) {
        super(enumValues.getEnumClass(), false);
        this._values = enumValues;
        this._serializeAsIndex = bool;
    }

    public static EnumSerializer construct(Class<?> cls, SerializationConfig serializationConfig, BeanDescription beanDescription, JsonFormat.Value value) {
        return new EnumSerializer(EnumValues.constructFromName(serializationConfig, cls), _isShapeWrittenUsingIndex(cls, value, true, null));
    }

    @Override // com.fasterxml.jackson.databind.ser.ContextualSerializer
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        Boolean bool_isShapeWrittenUsingIndex;
        JsonFormat.Value valueFindFormatOverrides = findFormatOverrides(serializerProvider, beanProperty, handledType());
        return (valueFindFormatOverrides == null || (bool_isShapeWrittenUsingIndex = _isShapeWrittenUsingIndex(handledType(), valueFindFormatOverrides, false, this._serializeAsIndex)) == this._serializeAsIndex) ? this : new EnumSerializer(this._values, bool_isShapeWrittenUsingIndex);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(Enum<?> r2, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (_serializeAsIndex(serializerProvider)) {
            jsonGenerator.writeNumber(r2.ordinal());
        } else if (serializerProvider.isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)) {
            jsonGenerator.writeString(r2.toString());
        } else {
            jsonGenerator.writeString(this._values.serializedValueFor(r2));
        }
    }

    protected final boolean _serializeAsIndex(SerializerProvider serializerProvider) {
        Boolean bool = this._serializeAsIndex;
        if (bool != null) {
            return bool.booleanValue();
        }
        return serializerProvider.isEnabled(SerializationFeature.WRITE_ENUMS_USING_INDEX);
    }

    protected static Boolean _isShapeWrittenUsingIndex(Class<?> cls, JsonFormat.Value value, boolean z, Boolean bool) {
        JsonFormat.Shape shape = value == null ? null : value.getShape();
        if (shape == null || shape == JsonFormat.Shape.ANY || shape == JsonFormat.Shape.SCALAR) {
            return bool;
        }
        if (shape == JsonFormat.Shape.STRING || shape == JsonFormat.Shape.NATURAL) {
            return Boolean.FALSE;
        }
        if (shape.isNumeric() || shape == JsonFormat.Shape.ARRAY) {
            return Boolean.TRUE;
        }
        Object[] objArr = new Object[3];
        objArr[0] = shape;
        objArr[1] = cls.getName();
        objArr[2] = z ? "class" : "property";
        throw new IllegalArgumentException(String.format("Unsupported serialization shape (%s) for Enum %s, not supported as %s annotation", objArr));
    }
}
