package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public abstract class TypeSerializerBase extends TypeSerializer {
    protected final TypeIdResolver _idResolver;
    protected final BeanProperty _property;

    @Override // com.fasterxml.jackson.databind.jsontype.TypeSerializer
    public String getPropertyName() {
        return null;
    }

    protected void handleMissingId(Object obj) {
    }

    protected TypeSerializerBase(TypeIdResolver typeIdResolver, BeanProperty beanProperty) {
        this._idResolver = typeIdResolver;
        this._property = beanProperty;
    }

    @Override // com.fasterxml.jackson.databind.jsontype.TypeSerializer
    public WritableTypeId writeTypePrefix(JsonGenerator jsonGenerator, WritableTypeId writableTypeId) throws IOException {
        _generateTypeId(writableTypeId);
        jsonGenerator.writeTypePrefix(writableTypeId);
        return writableTypeId;
    }

    @Override // com.fasterxml.jackson.databind.jsontype.TypeSerializer
    public WritableTypeId writeTypeSuffix(JsonGenerator jsonGenerator, WritableTypeId writableTypeId) throws IOException {
        jsonGenerator.writeTypeSuffix(writableTypeId);
        return writableTypeId;
    }

    protected void _generateTypeId(WritableTypeId writableTypeId) {
        String strIdFromValueAndType;
        if (writableTypeId.id == null) {
            Object obj = writableTypeId.forValue;
            Class<?> cls = writableTypeId.forValueType;
            if (cls == null) {
                strIdFromValueAndType = idFromValue(obj);
            } else {
                strIdFromValueAndType = idFromValueAndType(obj, cls);
            }
            writableTypeId.id = strIdFromValueAndType;
        }
    }

    protected String idFromValue(Object obj) {
        String strIdFromValue = this._idResolver.idFromValue(obj);
        if (strIdFromValue == null) {
            handleMissingId(obj);
        }
        return strIdFromValue;
    }

    protected String idFromValueAndType(Object obj, Class<?> cls) {
        String strIdFromValueAndType = this._idResolver.idFromValueAndType(obj, cls);
        if (strIdFromValueAndType == null) {
            handleMissingId(obj);
        }
        return strIdFromValueAndType;
    }
}
