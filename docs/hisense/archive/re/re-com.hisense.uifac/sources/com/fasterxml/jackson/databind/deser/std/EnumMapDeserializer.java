package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.util.EnumMap;

/* JADX INFO: loaded from: classes.dex */
public class EnumMapDeserializer extends ContainerDeserializerBase<EnumMap<?, ?>> implements ContextualDeserializer, ResolvableDeserializer {
    private static final long serialVersionUID = 1;
    protected JsonDeserializer<Object> _delegateDeserializer;
    protected final Class<?> _enumClass;
    protected KeyDeserializer _keyDeserializer;
    protected PropertyBasedCreator _propertyBasedCreator;
    protected JsonDeserializer<Object> _valueDeserializer;
    protected final ValueInstantiator _valueInstantiator;
    protected final TypeDeserializer _valueTypeDeserializer;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public /* bridge */ /* synthetic */ Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException {
        EnumMap enumMap = (EnumMap) obj;
        deserialize(jsonParser, deserializationContext, enumMap);
        return enumMap;
    }

    public EnumMapDeserializer(JavaType javaType, ValueInstantiator valueInstantiator, KeyDeserializer keyDeserializer, JsonDeserializer<?> jsonDeserializer, TypeDeserializer typeDeserializer, NullValueProvider nullValueProvider) {
        super(javaType, nullValueProvider, (Boolean) null);
        this._enumClass = javaType.getKeyType().getRawClass();
        this._keyDeserializer = keyDeserializer;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = typeDeserializer;
        this._valueInstantiator = valueInstantiator;
    }

    protected EnumMapDeserializer(EnumMapDeserializer enumMapDeserializer, KeyDeserializer keyDeserializer, JsonDeserializer<?> jsonDeserializer, TypeDeserializer typeDeserializer, NullValueProvider nullValueProvider) {
        super(enumMapDeserializer, nullValueProvider, enumMapDeserializer._unwrapSingle);
        this._enumClass = enumMapDeserializer._enumClass;
        this._keyDeserializer = keyDeserializer;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = typeDeserializer;
        this._valueInstantiator = enumMapDeserializer._valueInstantiator;
        this._delegateDeserializer = enumMapDeserializer._delegateDeserializer;
        this._propertyBasedCreator = enumMapDeserializer._propertyBasedCreator;
    }

    public EnumMapDeserializer withResolved(KeyDeserializer keyDeserializer, JsonDeserializer<?> jsonDeserializer, TypeDeserializer typeDeserializer, NullValueProvider nullValueProvider) {
        return (keyDeserializer == this._keyDeserializer && nullValueProvider == this._nullProvider && jsonDeserializer == this._valueDeserializer && typeDeserializer == this._valueTypeDeserializer) ? this : new EnumMapDeserializer(this, keyDeserializer, jsonDeserializer, typeDeserializer, nullValueProvider);
    }

    @Override // com.fasterxml.jackson.databind.deser.ResolvableDeserializer
    public void resolve(DeserializationContext deserializationContext) throws JsonMappingException {
        ValueInstantiator valueInstantiator = this._valueInstantiator;
        if (valueInstantiator != null) {
            if (valueInstantiator.canCreateUsingDelegate()) {
                JavaType delegateType = this._valueInstantiator.getDelegateType(deserializationContext.getConfig());
                if (delegateType == null) {
                    JavaType javaType = this._containerType;
                    deserializationContext.reportBadDefinition(javaType, String.format("Invalid delegate-creator definition for %s: value instantiator (%s) returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'", javaType, this._valueInstantiator.getClass().getName()));
                    throw null;
                }
                this._delegateDeserializer = findDeserializer(deserializationContext, delegateType, null);
                return;
            }
            if (this._valueInstantiator.canCreateUsingArrayDelegate()) {
                JavaType arrayDelegateType = this._valueInstantiator.getArrayDelegateType(deserializationContext.getConfig());
                if (arrayDelegateType == null) {
                    JavaType javaType2 = this._containerType;
                    deserializationContext.reportBadDefinition(javaType2, String.format("Invalid delegate-creator definition for %s: value instantiator (%s) returned true for 'canCreateUsingArrayDelegate()', but null for 'getArrayDelegateType()'", javaType2, this._valueInstantiator.getClass().getName()));
                    throw null;
                }
                this._delegateDeserializer = findDeserializer(deserializationContext, arrayDelegateType, null);
                return;
            }
            if (this._valueInstantiator.canCreateFromObjectWith()) {
                this._propertyBasedCreator = PropertyBasedCreator.construct(deserializationContext, this._valueInstantiator, this._valueInstantiator.getFromObjectArguments(deserializationContext.getConfig()), deserializationContext.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES));
            }
        }
    }

    @Override // com.fasterxml.jackson.databind.deser.ContextualDeserializer
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        JsonDeserializer<?> jsonDeserializerHandleSecondaryContextualization;
        KeyDeserializer keyDeserializerFindKeyDeserializer = this._keyDeserializer;
        if (keyDeserializerFindKeyDeserializer == null) {
            keyDeserializerFindKeyDeserializer = deserializationContext.findKeyDeserializer(this._containerType.getKeyType(), beanProperty);
        }
        JsonDeserializer<?> jsonDeserializer = this._valueDeserializer;
        JavaType contentType = this._containerType.getContentType();
        if (jsonDeserializer == null) {
            jsonDeserializerHandleSecondaryContextualization = deserializationContext.findContextualValueDeserializer(contentType, beanProperty);
        } else {
            jsonDeserializerHandleSecondaryContextualization = deserializationContext.handleSecondaryContextualization(jsonDeserializer, beanProperty, contentType);
        }
        TypeDeserializer typeDeserializerForProperty = this._valueTypeDeserializer;
        if (typeDeserializerForProperty != null) {
            typeDeserializerForProperty = typeDeserializerForProperty.forProperty(beanProperty);
        }
        return withResolved(keyDeserializerFindKeyDeserializer, jsonDeserializerHandleSecondaryContextualization, typeDeserializerForProperty, findContentNullProvider(deserializationContext, beanProperty, jsonDeserializerHandleSecondaryContextualization));
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public boolean isCachable() {
        return this._valueDeserializer == null && this._keyDeserializer == null && this._valueTypeDeserializer == null;
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase
    public JsonDeserializer<Object> getContentDeserializer() {
        return this._valueDeserializer;
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase, com.fasterxml.jackson.databind.JsonDeserializer
    public Object getEmptyValue(DeserializationContext deserializationContext) throws JsonMappingException {
        return constructMap(deserializationContext);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public EnumMap<?, ?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (this._propertyBasedCreator != null) {
            return _deserializeUsingProperties(jsonParser, deserializationContext);
        }
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            return (EnumMap) this._valueInstantiator.createUsingDelegate(deserializationContext, jsonDeserializer.deserialize(jsonParser, deserializationContext));
        }
        JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
        if (jsonTokenCurrentToken != JsonToken.START_OBJECT && jsonTokenCurrentToken != JsonToken.FIELD_NAME && jsonTokenCurrentToken != JsonToken.END_OBJECT) {
            if (jsonTokenCurrentToken == JsonToken.VALUE_STRING) {
                return (EnumMap) this._valueInstantiator.createFromString(deserializationContext, jsonParser.getText());
            }
            return _deserializeFromEmpty(jsonParser, deserializationContext);
        }
        EnumMap<?, ?> enumMapConstructMap = constructMap(deserializationContext);
        deserialize(jsonParser, deserializationContext, (EnumMap) enumMapConstructMap);
        return enumMapConstructMap;
    }

    public EnumMap<?, ?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, EnumMap enumMap) throws IOException {
        String currentName;
        Object objDeserializeWithType;
        jsonParser.setCurrentValue(enumMap);
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        if (jsonParser.isExpectedStartObjectToken()) {
            currentName = jsonParser.nextFieldName();
        } else {
            JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
            if (jsonTokenCurrentToken != JsonToken.FIELD_NAME) {
                if (jsonTokenCurrentToken == JsonToken.END_OBJECT) {
                    return enumMap;
                }
                deserializationContext.reportWrongTokenException(this, JsonToken.FIELD_NAME, (String) null, new Object[0]);
                throw null;
            }
            currentName = jsonParser.getCurrentName();
        }
        while (currentName != null) {
            Enum r5 = (Enum) this._keyDeserializer.deserializeKey(currentName, deserializationContext);
            JsonToken jsonTokenNextToken = jsonParser.nextToken();
            if (r5 == null) {
                if (!deserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                    deserializationContext.handleWeirdStringValue(this._enumClass, currentName, "value not one of declared Enum instance names for %s", this._containerType.getKeyType());
                    throw null;
                }
                jsonParser.skipChildren();
            } else {
                try {
                    if (jsonTokenNextToken == JsonToken.VALUE_NULL) {
                        if (!this._skipNullValues) {
                            objDeserializeWithType = this._nullProvider.getNullValue(deserializationContext);
                        }
                    } else if (typeDeserializer == null) {
                        objDeserializeWithType = jsonDeserializer.deserialize(jsonParser, deserializationContext);
                    } else {
                        objDeserializeWithType = jsonDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
                    }
                    enumMap.put(r5, objDeserializeWithType);
                } catch (Exception e) {
                    wrapAndThrow(e, enumMap, currentName);
                    throw null;
                }
            }
            currentName = jsonParser.nextFieldName();
        }
        return enumMap;
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromObject(jsonParser, deserializationContext);
    }

    protected EnumMap<?, ?> constructMap(DeserializationContext deserializationContext) throws JsonMappingException {
        ValueInstantiator valueInstantiator = this._valueInstantiator;
        if (valueInstantiator == null) {
            return new EnumMap<>(this._enumClass);
        }
        try {
            if (!valueInstantiator.canCreateUsingDefault()) {
                return (EnumMap) deserializationContext.handleMissingInstantiator(handledType(), getValueInstantiator(), null, "no default constructor found", new Object[0]);
            }
            return (EnumMap) this._valueInstantiator.createUsingDefault(deserializationContext);
        } catch (IOException e) {
            ClassUtil.throwAsMappingException(deserializationContext, e);
            throw null;
        }
    }

    public EnumMap<?, ?> _deserializeUsingProperties(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String currentName;
        Object objDeserializeWithType;
        PropertyBasedCreator propertyBasedCreator = this._propertyBasedCreator;
        PropertyValueBuffer propertyValueBufferStartBuilding = propertyBasedCreator.startBuilding(jsonParser, deserializationContext, null);
        if (jsonParser.isExpectedStartObjectToken()) {
            currentName = jsonParser.nextFieldName();
        } else {
            currentName = jsonParser.hasToken(JsonToken.FIELD_NAME) ? jsonParser.getCurrentName() : null;
        }
        while (currentName != null) {
            JsonToken jsonTokenNextToken = jsonParser.nextToken();
            SettableBeanProperty settableBeanPropertyFindCreatorProperty = propertyBasedCreator.findCreatorProperty(currentName);
            if (settableBeanPropertyFindCreatorProperty != null) {
                if (propertyValueBufferStartBuilding.assignParameter(settableBeanPropertyFindCreatorProperty, settableBeanPropertyFindCreatorProperty.deserialize(jsonParser, deserializationContext))) {
                    jsonParser.nextToken();
                    try {
                        EnumMap<?, ?> enumMap = (EnumMap) propertyBasedCreator.build(deserializationContext, propertyValueBufferStartBuilding);
                        deserialize(jsonParser, deserializationContext, (EnumMap) enumMap);
                        return enumMap;
                    } catch (Exception e) {
                        wrapAndThrow(e, this._containerType.getRawClass(), currentName);
                        throw null;
                    }
                }
            } else {
                Enum r5 = (Enum) this._keyDeserializer.deserializeKey(currentName, deserializationContext);
                if (r5 == null) {
                    if (!deserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                        deserializationContext.handleWeirdStringValue(this._enumClass, currentName, "value not one of declared Enum instance names for %s", this._containerType.getKeyType());
                        throw null;
                    }
                    jsonParser.nextToken();
                    jsonParser.skipChildren();
                } else {
                    try {
                        if (jsonTokenNextToken == JsonToken.VALUE_NULL) {
                            if (!this._skipNullValues) {
                                objDeserializeWithType = this._nullProvider.getNullValue(deserializationContext);
                            }
                        } else if (this._valueTypeDeserializer == null) {
                            objDeserializeWithType = this._valueDeserializer.deserialize(jsonParser, deserializationContext);
                        } else {
                            objDeserializeWithType = this._valueDeserializer.deserializeWithType(jsonParser, deserializationContext, this._valueTypeDeserializer);
                        }
                        propertyValueBufferStartBuilding.bufferMapProperty(r5, objDeserializeWithType);
                    } catch (Exception e2) {
                        wrapAndThrow(e2, this._containerType.getRawClass(), currentName);
                        throw null;
                    }
                }
            }
            currentName = jsonParser.nextFieldName();
        }
        try {
            return (EnumMap) propertyBasedCreator.build(deserializationContext, propertyValueBufferStartBuilding);
        } catch (Exception e3) {
            wrapAndThrow(e3, this._containerType.getRawClass(), currentName);
            throw null;
        }
    }
}
