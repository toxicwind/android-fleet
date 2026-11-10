package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
@JacksonStdImpl
public final class BooleanSerializer extends StdScalarSerializer<Object> implements ContextualSerializer {
    private static final long serialVersionUID = 1;
    protected final boolean _forPrimitive;

    public BooleanSerializer(boolean z) {
        super(z ? Boolean.TYPE : Boolean.class, false);
        this._forPrimitive = z;
    }

    @Override // com.fasterxml.jackson.databind.ser.ContextualSerializer
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JsonFormat.Value valueFindFormatOverrides = findFormatOverrides(serializerProvider, beanProperty, Boolean.class);
        return (valueFindFormatOverrides == null || !valueFindFormatOverrides.getShape().isNumeric()) ? this : new AsNumber(this._forPrimitive);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeBoolean(Boolean.TRUE.equals(obj));
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdScalarSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public final void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        jsonGenerator.writeBoolean(Boolean.TRUE.equals(obj));
    }

    static final class AsNumber extends StdScalarSerializer<Object> implements ContextualSerializer {
        private static final long serialVersionUID = 1;
        protected final boolean _forPrimitive;

        public AsNumber(boolean z) {
            super(z ? Boolean.TYPE : Boolean.class, false);
            this._forPrimitive = z;
        }

        @Override // com.fasterxml.jackson.databind.JsonSerializer
        public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeNumber(!Boolean.FALSE.equals(obj) ? 1 : 0);
        }

        @Override // com.fasterxml.jackson.databind.ser.std.StdScalarSerializer, com.fasterxml.jackson.databind.JsonSerializer
        public final void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
            jsonGenerator.writeBoolean(Boolean.TRUE.equals(obj));
        }

        @Override // com.fasterxml.jackson.databind.ser.ContextualSerializer
        public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
            JsonFormat.Value valueFindFormatOverrides = findFormatOverrides(serializerProvider, beanProperty, Boolean.class);
            return (valueFindFormatOverrides == null || valueFindFormatOverrides.getShape().isNumeric()) ? this : new BooleanSerializer(this._forPrimitive);
        }
    }
}
