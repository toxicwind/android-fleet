package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.impl.NullsConstantProvider;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.AccessPattern;
import java.io.IOException;
import java.util.EnumSet;

/* JADX INFO: loaded from: classes.dex */
public class EnumSetDeserializer extends StdDeserializer<EnumSet<?>> implements ContextualDeserializer {
    private static final long serialVersionUID = 1;
    protected JsonDeserializer<Enum<?>> _enumDeserializer;
    protected final JavaType _enumType;
    protected final NullValueProvider _nullProvider;
    protected final boolean _skipNullValues;
    protected final Boolean _unwrapSingle;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public /* bridge */ /* synthetic */ Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException {
        EnumSet<?> enumSet = (EnumSet) obj;
        deserialize(jsonParser, deserializationContext, enumSet);
        return enumSet;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public EnumSetDeserializer(JavaType javaType, JsonDeserializer<?> jsonDeserializer) {
        super((Class<?>) EnumSet.class);
        this._enumType = javaType;
        if (!javaType.isEnumType()) {
            throw new IllegalArgumentException("Type " + javaType + " not Java Enum type");
        }
        this._enumDeserializer = jsonDeserializer;
        this._unwrapSingle = null;
        this._nullProvider = null;
        this._skipNullValues = false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected EnumSetDeserializer(EnumSetDeserializer enumSetDeserializer, JsonDeserializer<?> jsonDeserializer, NullValueProvider nullValueProvider, Boolean bool) {
        super(enumSetDeserializer);
        this._enumType = enumSetDeserializer._enumType;
        this._enumDeserializer = jsonDeserializer;
        this._nullProvider = nullValueProvider;
        this._skipNullValues = NullsConstantProvider.isSkipper(nullValueProvider);
        this._unwrapSingle = bool;
    }

    public EnumSetDeserializer withResolved(JsonDeserializer<?> jsonDeserializer, NullValueProvider nullValueProvider, Boolean bool) {
        return (this._unwrapSingle == bool && this._enumDeserializer == jsonDeserializer && this._nullProvider == jsonDeserializer) ? this : new EnumSetDeserializer(this, jsonDeserializer, nullValueProvider, bool);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public boolean isCachable() {
        return this._enumType.getValueHandler() == null;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
        return Boolean.TRUE;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Object getEmptyValue(DeserializationContext deserializationContext) throws JsonMappingException {
        return constructSet();
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public AccessPattern getEmptyAccessPattern() {
        return AccessPattern.DYNAMIC;
    }

    @Override // com.fasterxml.jackson.databind.deser.ContextualDeserializer
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        JsonDeserializer<?> jsonDeserializerHandleSecondaryContextualization;
        Boolean boolFindFormatFeature = findFormatFeature(deserializationContext, beanProperty, EnumSet.class, JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        JsonDeserializer<Enum<?>> jsonDeserializer = this._enumDeserializer;
        if (jsonDeserializer == null) {
            jsonDeserializerHandleSecondaryContextualization = deserializationContext.findContextualValueDeserializer(this._enumType, beanProperty);
        } else {
            jsonDeserializerHandleSecondaryContextualization = deserializationContext.handleSecondaryContextualization(jsonDeserializer, beanProperty, this._enumType);
        }
        return withResolved(jsonDeserializerHandleSecondaryContextualization, findContentNullProvider(deserializationContext, beanProperty, jsonDeserializerHandleSecondaryContextualization), boolFindFormatFeature);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public EnumSet<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        EnumSet<?> enumSetConstructSet = constructSet();
        if (!jsonParser.isExpectedStartArrayToken()) {
            handleNonArray(jsonParser, deserializationContext, enumSetConstructSet);
            return enumSetConstructSet;
        }
        _deserialize(jsonParser, deserializationContext, enumSetConstructSet);
        return enumSetConstructSet;
    }

    public EnumSet<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, EnumSet<?> enumSet) throws IOException {
        if (!jsonParser.isExpectedStartArrayToken()) {
            handleNonArray(jsonParser, deserializationContext, enumSet);
            return enumSet;
        }
        _deserialize(jsonParser, deserializationContext, enumSet);
        return enumSet;
    }

    protected final EnumSet<?> _deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, EnumSet enumSet) throws IOException {
        Enum<?> enumDeserialize;
        while (true) {
            try {
                JsonToken jsonTokenNextToken = jsonParser.nextToken();
                if (jsonTokenNextToken == JsonToken.END_ARRAY) {
                    return enumSet;
                }
                if (jsonTokenNextToken == JsonToken.VALUE_NULL) {
                    if (!this._skipNullValues) {
                        enumDeserialize = (Enum) this._nullProvider.getNullValue(deserializationContext);
                    }
                } else {
                    enumDeserialize = this._enumDeserializer.deserialize(jsonParser, deserializationContext);
                }
                if (enumDeserialize != null) {
                    enumSet.add(enumDeserialize);
                }
            } catch (Exception e) {
                throw JsonMappingException.wrapWithPath(e, enumSet, enumSet.size());
            }
        }
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }

    private EnumSet constructSet() {
        return EnumSet.noneOf(this._enumType.getRawClass());
    }

    protected EnumSet<?> handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext, EnumSet enumSet) throws IOException {
        Boolean bool = this._unwrapSingle;
        if (!(bool == Boolean.TRUE || (bool == null && deserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)))) {
            deserializationContext.handleUnexpectedToken(EnumSet.class, jsonParser);
            throw null;
        }
        if (jsonParser.hasToken(JsonToken.VALUE_NULL)) {
            deserializationContext.handleUnexpectedToken(this._enumType, jsonParser);
            throw null;
        }
        try {
            Enum<?> enumDeserialize = this._enumDeserializer.deserialize(jsonParser, deserializationContext);
            if (enumDeserialize != null) {
                enumSet.add(enumDeserialize);
            }
            return enumSet;
        } catch (Exception e) {
            throw JsonMappingException.wrapWithPath(e, enumSet, enumSet.size());
        }
    }
}
