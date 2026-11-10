package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.PropertyWriter;

/* JADX INFO: loaded from: classes.dex */
public class MapProperty extends PropertyWriter {
    private static final BeanProperty BOGUS_PROP = new BeanProperty.Bogus();
    private static final long serialVersionUID = 1;
    protected Object _key;
    protected JsonSerializer<Object> _keySerializer;
    protected final BeanProperty _property;
    protected final TypeSerializer _typeSerializer;
    protected Object _value;
    protected JsonSerializer<Object> _valueSerializer;

    public MapProperty(TypeSerializer typeSerializer, BeanProperty beanProperty) {
        super(beanProperty == null ? PropertyMetadata.STD_REQUIRED_OR_OPTIONAL : beanProperty.getMetadata());
        this._typeSerializer = typeSerializer;
        this._property = beanProperty == null ? BOGUS_PROP : beanProperty;
    }

    public void reset(Object obj, Object obj2, JsonSerializer<Object> jsonSerializer, JsonSerializer<Object> jsonSerializer2) {
        this._key = obj;
        this._value = obj2;
        this._keySerializer = jsonSerializer;
        this._valueSerializer = jsonSerializer2;
    }

    @Override // com.fasterxml.jackson.databind.BeanProperty, com.fasterxml.jackson.databind.util.Named
    public String getName() {
        Object obj = this._key;
        if (obj instanceof String) {
            return (String) obj;
        }
        return String.valueOf(obj);
    }

    @Override // com.fasterxml.jackson.databind.BeanProperty
    public PropertyName getFullName() {
        return new PropertyName(getName());
    }

    @Override // com.fasterxml.jackson.databind.BeanProperty
    public JavaType getType() {
        return this._property.getType();
    }

    @Override // com.fasterxml.jackson.databind.BeanProperty
    public AnnotatedMember getMember() {
        return this._property.getMember();
    }
}
