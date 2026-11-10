package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.filter.FilteringParserDelegate;
import com.fasterxml.jackson.core.filter.TokenFilter;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ObjectReader extends ObjectCodec implements Serializable {
    private static final long serialVersionUID = 2;
    protected final DeserializationConfig _config;
    protected final DefaultDeserializationContext _context;
    protected final DataFormatReaders _dataFormatReaders = null;
    private final TokenFilter _filter = null;
    protected final InjectableValues _injectableValues;
    protected final JsonFactory _parserFactory;
    protected final JsonDeserializer<Object> _rootDeserializer;
    protected final ConcurrentHashMap<JavaType, JsonDeserializer<Object>> _rootDeserializers;
    protected final FormatSchema _schema;
    protected final boolean _unwrapRoot;
    protected final Object _valueToUpdate;
    protected final JavaType _valueType;

    protected ObjectReader(ObjectMapper objectMapper, DeserializationConfig deserializationConfig, JavaType javaType, Object obj, FormatSchema formatSchema, InjectableValues injectableValues) {
        this._config = deserializationConfig;
        this._context = objectMapper._deserializationContext;
        this._rootDeserializers = objectMapper._rootDeserializers;
        this._parserFactory = objectMapper._jsonFactory;
        this._valueType = javaType;
        this._valueToUpdate = obj;
        this._schema = formatSchema;
        this._injectableValues = injectableValues;
        this._unwrapRoot = deserializationConfig.useRootWrapping();
        this._rootDeserializer = _prefetchRootDeserializer(javaType);
    }

    protected JsonToken _initForReading(DeserializationContext deserializationContext, JsonParser jsonParser) throws IOException {
        FormatSchema formatSchema = this._schema;
        if (formatSchema != null) {
            jsonParser.setSchema(formatSchema);
        }
        this._config.initialize(jsonParser);
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken != null || (currentToken = jsonParser.nextToken()) != null) {
            return currentToken;
        }
        deserializationContext.reportInputMismatch(this._valueType, "No content to map due to end-of-input", new Object[0]);
        throw null;
    }

    public JsonParser createParser(byte[] bArr) throws IOException {
        _assertNotNull("content", bArr);
        return this._parserFactory.createParser(bArr);
    }

    public <T> T readValue(byte[] bArr) throws IOException {
        if (this._dataFormatReaders != null) {
            return (T) _detectBindAndClose(bArr, 0, bArr.length);
        }
        return (T) _bindAndClose(_considerFilter(createParser(bArr), false));
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    public void writeValue(JsonGenerator jsonGenerator, Object obj) throws IOException {
        throw new UnsupportedOperationException("Not implemented for ObjectReader");
    }

    protected Object _bindAndClose(JsonParser jsonParser) throws IOException {
        Object objDeserialize;
        try {
            DefaultDeserializationContext defaultDeserializationContextCreateDeserializationContext = createDeserializationContext(jsonParser);
            JsonToken jsonToken_initForReading = _initForReading(defaultDeserializationContextCreateDeserializationContext, jsonParser);
            if (jsonToken_initForReading == JsonToken.VALUE_NULL) {
                if (this._valueToUpdate == null) {
                    objDeserialize = _findRootDeserializer(defaultDeserializationContextCreateDeserializationContext).getNullValue(defaultDeserializationContextCreateDeserializationContext);
                } else {
                    objDeserialize = this._valueToUpdate;
                }
            } else if (jsonToken_initForReading == JsonToken.END_ARRAY || jsonToken_initForReading == JsonToken.END_OBJECT) {
                objDeserialize = this._valueToUpdate;
            } else {
                JsonDeserializer<Object> jsonDeserializer_findRootDeserializer = _findRootDeserializer(defaultDeserializationContextCreateDeserializationContext);
                if (this._unwrapRoot) {
                    objDeserialize = _unwrapAndDeserialize(jsonParser, defaultDeserializationContextCreateDeserializationContext, this._valueType, jsonDeserializer_findRootDeserializer);
                } else if (this._valueToUpdate == null) {
                    objDeserialize = jsonDeserializer_findRootDeserializer.deserialize(jsonParser, defaultDeserializationContextCreateDeserializationContext);
                } else {
                    jsonDeserializer_findRootDeserializer.deserialize(jsonParser, defaultDeserializationContextCreateDeserializationContext, this._valueToUpdate);
                    objDeserialize = this._valueToUpdate;
                }
            }
            if (this._config.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)) {
                _verifyNoTrailingTokens(jsonParser, defaultDeserializationContextCreateDeserializationContext, this._valueType);
            }
            if (jsonParser != null) {
                jsonParser.close();
            }
            return objDeserialize;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (jsonParser != null) {
                    try {
                        jsonParser.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    protected Object _unwrapAndDeserialize(JsonParser jsonParser, DeserializationContext deserializationContext, JavaType javaType, JsonDeserializer<Object> jsonDeserializer) throws IOException {
        Object objDeserialize;
        String simpleName = this._config.findRootName(javaType).getSimpleName();
        JsonToken currentToken = jsonParser.getCurrentToken();
        JsonToken jsonToken = JsonToken.START_OBJECT;
        if (currentToken != jsonToken) {
            deserializationContext.reportWrongTokenException(javaType, jsonToken, "Current token not START_OBJECT (needed to unwrap root name '%s'), but %s", simpleName, jsonParser.getCurrentToken());
            throw null;
        }
        JsonToken jsonTokenNextToken = jsonParser.nextToken();
        JsonToken jsonToken2 = JsonToken.FIELD_NAME;
        if (jsonTokenNextToken != jsonToken2) {
            deserializationContext.reportWrongTokenException(javaType, jsonToken2, "Current token not FIELD_NAME (to contain expected root name '%s'), but %s", simpleName, jsonParser.getCurrentToken());
            throw null;
        }
        String currentName = jsonParser.getCurrentName();
        if (!simpleName.equals(currentName)) {
            deserializationContext.reportPropertyInputMismatch(javaType, currentName, "Root name '%s' does not match expected ('%s') for type %s", currentName, simpleName, javaType);
            throw null;
        }
        jsonParser.nextToken();
        Object obj = this._valueToUpdate;
        if (obj == null) {
            objDeserialize = jsonDeserializer.deserialize(jsonParser, deserializationContext);
        } else {
            jsonDeserializer.deserialize(jsonParser, deserializationContext, obj);
            objDeserialize = this._valueToUpdate;
        }
        JsonToken jsonTokenNextToken2 = jsonParser.nextToken();
        JsonToken jsonToken3 = JsonToken.END_OBJECT;
        if (jsonTokenNextToken2 != jsonToken3) {
            deserializationContext.reportWrongTokenException(javaType, jsonToken3, "Current token not END_OBJECT (to match wrapper object with root name '%s'), but %s", simpleName, jsonParser.getCurrentToken());
            throw null;
        }
        if (this._config.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)) {
            _verifyNoTrailingTokens(jsonParser, deserializationContext, this._valueType);
        }
        return objDeserialize;
    }

    protected JsonParser _considerFilter(JsonParser jsonParser, boolean z) {
        return (this._filter == null || FilteringParserDelegate.class.isInstance(jsonParser)) ? jsonParser : new FilteringParserDelegate(jsonParser, this._filter, false, z);
    }

    protected final void _verifyNoTrailingTokens(JsonParser jsonParser, DeserializationContext deserializationContext, JavaType javaType) throws IOException {
        Object obj;
        JsonToken jsonTokenNextToken = jsonParser.nextToken();
        if (jsonTokenNextToken != null) {
            Class<?> clsRawClass = ClassUtil.rawClass(javaType);
            if (clsRawClass == null && (obj = this._valueToUpdate) != null) {
                clsRawClass = obj.getClass();
            }
            deserializationContext.reportTrailingTokens(clsRawClass, jsonParser, jsonTokenNextToken);
            throw null;
        }
    }

    protected Object _detectBindAndClose(byte[] bArr, int i, int i2) throws IOException {
        DataFormatReaders.Match matchFindFormat = this._dataFormatReaders.findFormat(bArr, i, i2);
        if (!matchFindFormat.hasMatch()) {
            _reportUnkownFormat(this._dataFormatReaders, matchFindFormat);
            throw null;
        }
        return matchFindFormat.getReader()._bindAndClose(matchFindFormat.createParserWithMatch());
    }

    protected void _reportUnkownFormat(DataFormatReaders dataFormatReaders, DataFormatReaders.Match match) throws JsonProcessingException {
        throw new JsonParseException(null, "Cannot detect format from input, does not look like any of detectable formats " + dataFormatReaders.toString());
    }

    protected DefaultDeserializationContext createDeserializationContext(JsonParser jsonParser) {
        return this._context.createInstance(this._config, jsonParser, this._injectableValues);
    }

    protected JsonDeserializer<Object> _findRootDeserializer(DeserializationContext deserializationContext) throws JsonMappingException {
        JsonDeserializer<Object> jsonDeserializer = this._rootDeserializer;
        if (jsonDeserializer != null) {
            return jsonDeserializer;
        }
        JavaType javaType = this._valueType;
        if (javaType == null) {
            deserializationContext.reportBadDefinition((JavaType) null, "No value type configured for ObjectReader");
            throw null;
        }
        JsonDeserializer<Object> jsonDeserializer2 = this._rootDeserializers.get(javaType);
        if (jsonDeserializer2 != null) {
            return jsonDeserializer2;
        }
        JsonDeserializer<Object> jsonDeserializerFindRootValueDeserializer = deserializationContext.findRootValueDeserializer(javaType);
        if (jsonDeserializerFindRootValueDeserializer == null) {
            deserializationContext.reportBadDefinition(javaType, "Cannot find a deserializer for type " + javaType);
            throw null;
        }
        this._rootDeserializers.put(javaType, jsonDeserializerFindRootValueDeserializer);
        return jsonDeserializerFindRootValueDeserializer;
    }

    protected JsonDeserializer<Object> _prefetchRootDeserializer(JavaType javaType) {
        if (javaType == null || !this._config.isEnabled(DeserializationFeature.EAGER_DESERIALIZER_FETCH)) {
            return null;
        }
        JsonDeserializer<Object> jsonDeserializer = this._rootDeserializers.get(javaType);
        if (jsonDeserializer != null) {
            return jsonDeserializer;
        }
        try {
            JsonDeserializer<Object> jsonDeserializerFindRootValueDeserializer = createDeserializationContext(null).findRootValueDeserializer(javaType);
            if (jsonDeserializerFindRootValueDeserializer != null) {
                try {
                    this._rootDeserializers.put(javaType, jsonDeserializerFindRootValueDeserializer);
                } catch (JsonProcessingException unused) {
                    return jsonDeserializerFindRootValueDeserializer;
                }
            }
            return jsonDeserializerFindRootValueDeserializer;
        } catch (JsonProcessingException unused2) {
            return jsonDeserializer;
        }
    }

    protected final void _assertNotNull(String str, Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException(String.format("argument \"%s\" is null", str));
        }
    }
}
