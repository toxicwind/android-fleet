package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.core.util.JacksonFeatureSet;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualKeyDeserializer;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.deser.DeserializerCache;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;
import com.fasterxml.jackson.databind.deser.UnresolvedForwardReference;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.deser.impl.TypeWrappedDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.LinkedNode;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes5.dex */
public abstract class DeserializationContext extends DatabindContext implements Serializable {
    private static final long serialVersionUID = 1;
    protected transient ArrayBuilders _arrayBuilders;
    protected transient ContextAttributes _attributes;
    protected final DeserializerCache _cache;
    protected final DeserializationConfig _config;
    protected LinkedNode<JavaType> _currentType;
    protected transient DateFormat _dateFormat;
    protected final DeserializerFactory _factory;
    protected final int _featureFlags;
    protected final InjectableValues _injectableValues;
    protected transient ObjectBuffer _objectBuffer;
    protected transient JsonParser _parser;
    protected final JacksonFeatureSet<StreamReadCapability> _readCapabilities;
    protected final Class<?> _view;

    public abstract void checkUnresolvedObjectId() throws UnresolvedForwardReference;

    public abstract JsonDeserializer<Object> deserializerInstance(Annotated annotated, Object obj) throws JsonMappingException;

    public abstract ReadableObjectId findObjectId(Object obj, ObjectIdGenerator<?> objectIdGenerator, ObjectIdResolver objectIdResolver);

    public abstract KeyDeserializer keyDeserializerInstance(Annotated annotated, Object obj) throws JsonMappingException;

    protected DeserializationContext(DeserializerFactory deserializerFactory) {
        this(deserializerFactory, (DeserializerCache) null);
    }

    protected DeserializationContext(DeserializerFactory deserializerFactory, DeserializerCache deserializerCache) {
        if (deserializerFactory == null) {
            throw new NullPointerException("Cannot pass null DeserializerFactory");
        }
        this._factory = deserializerFactory;
        this._cache = deserializerCache == null ? new DeserializerCache() : deserializerCache;
        this._featureFlags = 0;
        this._readCapabilities = null;
        this._config = null;
        this._injectableValues = null;
        this._view = null;
        this._attributes = null;
    }

    protected DeserializationContext(DeserializationContext deserializationContext, DeserializerFactory deserializerFactory) {
        this._cache = deserializationContext._cache;
        this._factory = deserializerFactory;
        this._config = deserializationContext._config;
        this._featureFlags = deserializationContext._featureFlags;
        this._readCapabilities = deserializationContext._readCapabilities;
        this._view = deserializationContext._view;
        this._parser = deserializationContext._parser;
        this._injectableValues = deserializationContext._injectableValues;
        this._attributes = deserializationContext._attributes;
    }

    protected DeserializationContext(DeserializationContext deserializationContext, DeserializationConfig deserializationConfig, JsonParser jsonParser, InjectableValues injectableValues) {
        this._cache = deserializationContext._cache;
        this._factory = deserializationContext._factory;
        this._readCapabilities = jsonParser == null ? null : jsonParser.getReadCapabilities();
        this._config = deserializationConfig;
        this._featureFlags = deserializationConfig.getDeserializationFeatures();
        this._view = deserializationConfig.getActiveView();
        this._parser = jsonParser;
        this._injectableValues = injectableValues;
        this._attributes = deserializationConfig.getAttributes();
    }

    protected DeserializationContext(DeserializationContext deserializationContext, DeserializationConfig deserializationConfig) {
        this._cache = deserializationContext._cache;
        this._factory = deserializationContext._factory;
        this._readCapabilities = null;
        this._config = deserializationConfig;
        this._featureFlags = deserializationConfig.getDeserializationFeatures();
        this._view = null;
        this._parser = null;
        this._injectableValues = null;
        this._attributes = null;
    }

    protected DeserializationContext(DeserializationContext deserializationContext) {
        this._cache = new DeserializerCache();
        this._factory = deserializationContext._factory;
        this._config = deserializationContext._config;
        this._featureFlags = deserializationContext._featureFlags;
        this._readCapabilities = deserializationContext._readCapabilities;
        this._view = deserializationContext._view;
        this._injectableValues = null;
    }

    @Override // com.fasterxml.jackson.databind.DatabindContext
    public DeserializationConfig getConfig() {
        return this._config;
    }

    @Override // com.fasterxml.jackson.databind.DatabindContext
    public final Class<?> getActiveView() {
        return this._view;
    }

    @Override // com.fasterxml.jackson.databind.DatabindContext
    public final boolean canOverrideAccessModifiers() {
        return this._config.canOverrideAccessModifiers();
    }

    @Override // com.fasterxml.jackson.databind.DatabindContext
    public final boolean isEnabled(MapperFeature mapperFeature) {
        return this._config.isEnabled(mapperFeature);
    }

    @Override // com.fasterxml.jackson.databind.DatabindContext
    public final JsonFormat.Value getDefaultPropertyFormat(Class<?> cls) {
        return this._config.getDefaultPropertyFormat(cls);
    }

    @Override // com.fasterxml.jackson.databind.DatabindContext
    public final AnnotationIntrospector getAnnotationIntrospector() {
        return this._config.getAnnotationIntrospector();
    }

    @Override // com.fasterxml.jackson.databind.DatabindContext
    public final TypeFactory getTypeFactory() {
        return this._config.getTypeFactory();
    }

    @Override // com.fasterxml.jackson.databind.DatabindContext
    public JavaType constructSpecializedType(JavaType javaType, Class<?> cls) throws IllegalArgumentException {
        return javaType.hasRawClass(cls) ? javaType : getConfig().getTypeFactory().constructSpecializedType(javaType, cls, false);
    }

    @Override // com.fasterxml.jackson.databind.DatabindContext
    public Locale getLocale() {
        return this._config.getLocale();
    }

    @Override // com.fasterxml.jackson.databind.DatabindContext
    public TimeZone getTimeZone() {
        return this._config.getTimeZone();
    }

    @Override // com.fasterxml.jackson.databind.DatabindContext
    public Object getAttribute(Object obj) {
        return this._attributes.getAttribute(obj);
    }

    @Override // com.fasterxml.jackson.databind.DatabindContext
    public DeserializationContext setAttribute(Object obj, Object obj2) {
        this._attributes = this._attributes.withPerCallAttribute(obj, obj2);
        return this;
    }

    public JavaType getContextualType() {
        LinkedNode<JavaType> linkedNode = this._currentType;
        if (linkedNode == null) {
            return null;
        }
        return linkedNode.value();
    }

    public DeserializerFactory getFactory() {
        return this._factory;
    }

    public final boolean isEnabled(DeserializationFeature deserializationFeature) {
        return (this._featureFlags & deserializationFeature.getMask()) != 0;
    }

    public final boolean isEnabled(StreamReadCapability streamReadCapability) {
        return this._readCapabilities.isEnabled(streamReadCapability);
    }

    public final int getDeserializationFeatures() {
        return this._featureFlags;
    }

    public final boolean hasDeserializationFeatures(int i) {
        return (this._featureFlags & i) == i;
    }

    public final boolean hasSomeOfFeatures(int i) {
        return (this._featureFlags & i) != 0;
    }

    public final JsonParser getParser() {
        return this._parser;
    }

    public final Object findInjectableValue(Object obj, BeanProperty beanProperty, Object obj2) throws JsonMappingException {
        InjectableValues injectableValues = this._injectableValues;
        return injectableValues == null ? reportBadDefinition(ClassUtil.classOf(obj), String.format("No 'injectableValues' configured, cannot inject value with id [%s]", obj)) : injectableValues.findInjectableValue(obj, this, beanProperty, obj2);
    }

    public final Base64Variant getBase64Variant() {
        return this._config.getBase64Variant();
    }

    public final JsonNodeFactory getNodeFactory() {
        return this._config.getNodeFactory();
    }

    public CoercionAction findCoercionAction(LogicalType logicalType, Class<?> cls, CoercionInputShape coercionInputShape) {
        return this._config.findCoercionAction(logicalType, cls, coercionInputShape);
    }

    public CoercionAction findCoercionFromBlankString(LogicalType logicalType, Class<?> cls, CoercionAction coercionAction) {
        return this._config.findCoercionFromBlankString(logicalType, cls, coercionAction);
    }

    public TokenBuffer bufferForInputBuffering(JsonParser jsonParser) {
        return new TokenBuffer(jsonParser, this);
    }

    public final TokenBuffer bufferForInputBuffering() {
        return bufferForInputBuffering(getParser());
    }

    public TokenBuffer bufferAsCopyOfValue(JsonParser jsonParser) throws IOException {
        TokenBuffer tokenBufferBufferForInputBuffering = bufferForInputBuffering(jsonParser);
        tokenBufferBufferForInputBuffering.copyCurrentStructure(jsonParser);
        return tokenBufferBufferForInputBuffering;
    }

    public boolean hasValueDeserializerFor(JavaType javaType, AtomicReference<Throwable> atomicReference) {
        try {
            return this._cache.hasValueDeserializerFor(this, this._factory, javaType);
        } catch (DatabindException e) {
            if (atomicReference == null) {
                return false;
            }
            atomicReference.set(e);
            return false;
        } catch (RuntimeException e2) {
            if (atomicReference == null) {
                throw e2;
            }
            atomicReference.set(e2);
            return false;
        }
    }

    public final JsonDeserializer<Object> findContextualValueDeserializer(JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        JsonDeserializer<Object> jsonDeserializerFindValueDeserializer = this._cache.findValueDeserializer(this, this._factory, javaType);
        return jsonDeserializerFindValueDeserializer != null ? handleSecondaryContextualization(jsonDeserializerFindValueDeserializer, beanProperty, javaType) : jsonDeserializerFindValueDeserializer;
    }

    public final JsonDeserializer<Object> findNonContextualValueDeserializer(JavaType javaType) throws JsonMappingException {
        return this._cache.findValueDeserializer(this, this._factory, javaType);
    }

    public final JsonDeserializer<Object> findRootValueDeserializer(JavaType javaType) throws JsonMappingException {
        JsonDeserializer<Object> jsonDeserializerFindValueDeserializer = this._cache.findValueDeserializer(this, this._factory, javaType);
        if (jsonDeserializerFindValueDeserializer == null) {
            return null;
        }
        JsonDeserializer<?> jsonDeserializerHandleSecondaryContextualization = handleSecondaryContextualization(jsonDeserializerFindValueDeserializer, null, javaType);
        TypeDeserializer typeDeserializerFindTypeDeserializer = this._factory.findTypeDeserializer(this._config, javaType);
        return typeDeserializerFindTypeDeserializer != null ? new TypeWrappedDeserializer(typeDeserializerFindTypeDeserializer.forProperty(null), jsonDeserializerHandleSecondaryContextualization) : jsonDeserializerHandleSecondaryContextualization;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final KeyDeserializer findKeyDeserializer(JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        KeyDeserializer keyDeserializerFindKeyDeserializer;
        try {
            keyDeserializerFindKeyDeserializer = this._cache.findKeyDeserializer(this, this._factory, javaType);
        } catch (IllegalArgumentException e) {
            reportBadDefinition(javaType, ClassUtil.exceptionMessage(e));
            keyDeserializerFindKeyDeserializer = 0;
        }
        return keyDeserializerFindKeyDeserializer instanceof ContextualKeyDeserializer ? ((ContextualKeyDeserializer) keyDeserializerFindKeyDeserializer).createContextual(this, beanProperty) : keyDeserializerFindKeyDeserializer;
    }

    public final JavaType constructType(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        return this._config.constructType(cls);
    }

    public Class<?> findClass(String str) throws ClassNotFoundException {
        return getTypeFactory().findClass(str);
    }

    public final ObjectBuffer leaseObjectBuffer() {
        ObjectBuffer objectBuffer = this._objectBuffer;
        if (objectBuffer == null) {
            return new ObjectBuffer();
        }
        this._objectBuffer = null;
        return objectBuffer;
    }

    public final void returnObjectBuffer(ObjectBuffer objectBuffer) {
        if (this._objectBuffer == null || objectBuffer.initialCapacity() >= this._objectBuffer.initialCapacity()) {
            this._objectBuffer = objectBuffer;
        }
    }

    public final ArrayBuilders getArrayBuilders() {
        if (this._arrayBuilders == null) {
            this._arrayBuilders = new ArrayBuilders();
        }
        return this._arrayBuilders;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public JsonDeserializer<?> handlePrimaryContextualization(JsonDeserializer<?> jsonDeserializer, BeanProperty beanProperty, JavaType javaType) throws JsonMappingException {
        if (!(jsonDeserializer instanceof ContextualDeserializer)) {
            return jsonDeserializer;
        }
        this._currentType = new LinkedNode<>(javaType, this._currentType);
        try {
            return ((ContextualDeserializer) jsonDeserializer).createContextual(this, beanProperty);
        } finally {
            this._currentType = this._currentType.next();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public JsonDeserializer<?> handleSecondaryContextualization(JsonDeserializer<?> jsonDeserializer, BeanProperty beanProperty, JavaType javaType) throws JsonMappingException {
        if (!(jsonDeserializer instanceof ContextualDeserializer)) {
            return jsonDeserializer;
        }
        this._currentType = new LinkedNode<>(javaType, this._currentType);
        try {
            return ((ContextualDeserializer) jsonDeserializer).createContextual(this, beanProperty);
        } finally {
            this._currentType = this._currentType.next();
        }
    }

    public Date parseDate(String str) throws IllegalArgumentException {
        try {
            return _getDateFormat().parse(str);
        } catch (ParseException e) {
            throw new IllegalArgumentException(String.format("Failed to parse Date value '%s': %s", str, ClassUtil.exceptionMessage(e)));
        }
    }

    public Calendar constructCalendar(Date date) {
        Calendar calendar = Calendar.getInstance(getTimeZone());
        calendar.setTime(date);
        return calendar;
    }

    public String extractScalarFromObject(JsonParser jsonParser, JsonDeserializer<?> jsonDeserializer, Class<?> cls) throws IOException {
        return (String) handleUnexpectedToken(cls, jsonParser);
    }

    public <T> T readValue(JsonParser jsonParser, Class<T> cls) throws IOException {
        return (T) readValue(jsonParser, getTypeFactory().constructType(cls));
    }

    public <T> T readValue(JsonParser jsonParser, JavaType javaType) throws IOException {
        JsonDeserializer<Object> jsonDeserializerFindRootValueDeserializer = findRootValueDeserializer(javaType);
        if (jsonDeserializerFindRootValueDeserializer == null) {
            return (T) reportBadDefinition(javaType, "Could not find JsonDeserializer for type " + ClassUtil.getTypeDescription(javaType));
        }
        return (T) jsonDeserializerFindRootValueDeserializer.deserialize(jsonParser, this);
    }

    public <T> T readPropertyValue(JsonParser jsonParser, BeanProperty beanProperty, Class<T> cls) throws IOException {
        return (T) readPropertyValue(jsonParser, beanProperty, getTypeFactory().constructType(cls));
    }

    public <T> T readPropertyValue(JsonParser jsonParser, BeanProperty beanProperty, JavaType javaType) throws IOException {
        JsonDeserializer<Object> jsonDeserializerFindContextualValueDeserializer = findContextualValueDeserializer(javaType, beanProperty);
        if (jsonDeserializerFindContextualValueDeserializer == null) {
            return (T) reportBadDefinition(javaType, String.format("Could not find JsonDeserializer for type %s (via property %s)", ClassUtil.getTypeDescription(javaType), ClassUtil.nameOf(beanProperty)));
        }
        return (T) jsonDeserializerFindContextualValueDeserializer.deserialize(jsonParser, this);
    }

    public JsonNode readTree(JsonParser jsonParser) throws IOException {
        JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
        if (jsonTokenCurrentToken == null && (jsonTokenCurrentToken = jsonParser.nextToken()) == null) {
            return getNodeFactory().missingNode();
        }
        if (jsonTokenCurrentToken == JsonToken.VALUE_NULL) {
            return getNodeFactory().nullNode();
        }
        return (JsonNode) findRootValueDeserializer(this._config.constructType(JsonNode.class)).deserialize(jsonParser, this);
    }

    public <T> T readTreeAsValue(JsonNode jsonNode, Class<T> cls) throws IOException {
        if (jsonNode == null) {
            return null;
        }
        TreeTraversingParser treeTraversingParser_treeAsTokens = _treeAsTokens(jsonNode);
        try {
            T t = (T) readValue(treeTraversingParser_treeAsTokens, cls);
            if (treeTraversingParser_treeAsTokens != null) {
                treeTraversingParser_treeAsTokens.close();
            }
            return t;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (treeTraversingParser_treeAsTokens != null) {
                    try {
                        treeTraversingParser_treeAsTokens.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public <T> T readTreeAsValue(JsonNode jsonNode, JavaType javaType) throws IOException {
        if (jsonNode == null) {
            return null;
        }
        TreeTraversingParser treeTraversingParser_treeAsTokens = _treeAsTokens(jsonNode);
        try {
            T t = (T) readValue(treeTraversingParser_treeAsTokens, javaType);
            if (treeTraversingParser_treeAsTokens != null) {
                treeTraversingParser_treeAsTokens.close();
            }
            return t;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (treeTraversingParser_treeAsTokens != null) {
                    try {
                        treeTraversingParser_treeAsTokens.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    private TreeTraversingParser _treeAsTokens(JsonNode jsonNode) throws IOException {
        JsonParser jsonParser = this._parser;
        TreeTraversingParser treeTraversingParser = new TreeTraversingParser(jsonNode, jsonParser == null ? null : jsonParser.getCodec());
        treeTraversingParser.nextToken();
        return treeTraversingParser;
    }

    public boolean handleUnknownProperty(JsonParser jsonParser, JsonDeserializer<?> jsonDeserializer, Object obj, String str) throws IOException {
        LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers();
        while (problemHandlers != null) {
            DeserializationContext deserializationContext = this;
            JsonParser jsonParser2 = jsonParser;
            JsonDeserializer<?> jsonDeserializer2 = jsonDeserializer;
            Object obj2 = obj;
            String str2 = str;
            if (problemHandlers.value().handleUnknownProperty(deserializationContext, jsonParser2, jsonDeserializer2, obj2, str2)) {
                return true;
            }
            problemHandlers = problemHandlers.next();
            this = deserializationContext;
            jsonParser = jsonParser2;
            jsonDeserializer = jsonDeserializer2;
            obj = obj2;
            str = str2;
        }
        DeserializationContext deserializationContext2 = this;
        JsonParser jsonParser3 = jsonParser;
        JsonDeserializer<?> jsonDeserializer3 = jsonDeserializer;
        Object obj3 = obj;
        String str3 = str;
        if (!deserializationContext2.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)) {
            jsonParser3.skipChildren();
            return true;
        }
        throw UnrecognizedPropertyException.from(deserializationContext2._parser, obj3, str3, jsonDeserializer3 == null ? null : jsonDeserializer3.getKnownPropertyNames());
    }

    public Object handleWeirdKey(Class<?> cls, String str, String str2, Object... objArr) throws IOException {
        String str_format = _format(str2, objArr);
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            Object objHandleWeirdKey = problemHandlers.value().handleWeirdKey(this, cls, str, str_format);
            if (objHandleWeirdKey != DeserializationProblemHandler.NOT_HANDLED) {
                if (objHandleWeirdKey == null || cls.isInstance(objHandleWeirdKey)) {
                    return objHandleWeirdKey;
                }
                throw weirdStringException(str, cls, String.format("DeserializationProblemHandler.handleWeirdStringValue() for type %s returned value of type %s", ClassUtil.getClassDescription(cls), ClassUtil.getClassDescription(objHandleWeirdKey)));
            }
        }
        throw weirdKeyException(cls, str, str_format);
    }

    public Object handleWeirdStringValue(Class<?> cls, String str, String str2, Object... objArr) throws IOException {
        String str_format = _format(str2, objArr);
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            Object objHandleWeirdStringValue = problemHandlers.value().handleWeirdStringValue(this, cls, str, str_format);
            if (objHandleWeirdStringValue != DeserializationProblemHandler.NOT_HANDLED) {
                if (_isCompatible(cls, objHandleWeirdStringValue)) {
                    return objHandleWeirdStringValue;
                }
                throw weirdStringException(str, cls, String.format("DeserializationProblemHandler.handleWeirdStringValue() for type %s returned value of type %s", ClassUtil.getClassDescription(cls), ClassUtil.getClassDescription(objHandleWeirdStringValue)));
            }
        }
        throw weirdStringException(str, cls, str_format);
    }

    public Object handleWeirdNumberValue(Class<?> cls, Number number, String str, Object... objArr) throws IOException {
        String str_format = _format(str, objArr);
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            Object objHandleWeirdNumberValue = problemHandlers.value().handleWeirdNumberValue(this, cls, number, str_format);
            if (objHandleWeirdNumberValue != DeserializationProblemHandler.NOT_HANDLED) {
                if (_isCompatible(cls, objHandleWeirdNumberValue)) {
                    return objHandleWeirdNumberValue;
                }
                throw weirdNumberException(number, cls, _format("DeserializationProblemHandler.handleWeirdNumberValue() for type %s returned value of type %s", ClassUtil.getClassDescription(cls), ClassUtil.getClassDescription(objHandleWeirdNumberValue)));
            }
        }
        throw weirdNumberException(number, cls, str_format);
    }

    public Object handleWeirdNativeValue(JavaType javaType, Object obj, JsonParser jsonParser) throws IOException {
        Class<?> rawClass = javaType.getRawClass();
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            Object objHandleWeirdNativeValue = problemHandlers.value().handleWeirdNativeValue(this, javaType, obj, jsonParser);
            if (objHandleWeirdNativeValue != DeserializationProblemHandler.NOT_HANDLED) {
                if (objHandleWeirdNativeValue == null || rawClass.isInstance(objHandleWeirdNativeValue)) {
                    return objHandleWeirdNativeValue;
                }
                throw JsonMappingException.from(jsonParser, _format("DeserializationProblemHandler.handleWeirdNativeValue() for type %s returned value of type %s", ClassUtil.getClassDescription(javaType), ClassUtil.getClassDescription(objHandleWeirdNativeValue)));
            }
        }
        throw weirdNativeValueException(obj, rawClass);
    }

    public Object handleMissingInstantiator(Class<?> cls, ValueInstantiator valueInstantiator, JsonParser jsonParser, String str, Object... objArr) throws IOException {
        if (jsonParser == null) {
            jsonParser = getParser();
        }
        JsonParser jsonParser2 = jsonParser;
        String str_format = _format(str, objArr);
        LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers();
        while (problemHandlers != null) {
            DeserializationContext deserializationContext = this;
            Class<?> cls2 = cls;
            ValueInstantiator valueInstantiator2 = valueInstantiator;
            Object objHandleMissingInstantiator = problemHandlers.value().handleMissingInstantiator(deserializationContext, cls2, valueInstantiator2, jsonParser2, str_format);
            if (objHandleMissingInstantiator != DeserializationProblemHandler.NOT_HANDLED) {
                if (deserializationContext._isCompatible(cls2, objHandleMissingInstantiator)) {
                    return objHandleMissingInstantiator;
                }
                deserializationContext.reportBadDefinition(deserializationContext.constructType(cls2), String.format("DeserializationProblemHandler.handleMissingInstantiator() for type %s returned value of type %s", ClassUtil.getClassDescription(cls2), ClassUtil.getClassDescription(objHandleMissingInstantiator)));
            }
            problemHandlers = problemHandlers.next();
            this = deserializationContext;
            cls = cls2;
            valueInstantiator = valueInstantiator2;
        }
        DeserializationContext deserializationContext2 = this;
        Class<?> cls3 = cls;
        ValueInstantiator valueInstantiator3 = valueInstantiator;
        if (valueInstantiator3 == null) {
            return deserializationContext2.reportBadDefinition(cls3, String.format("Cannot construct instance of %s: %s", ClassUtil.nameOf(cls3), str_format));
        }
        if (!valueInstantiator3.canInstantiate()) {
            return deserializationContext2.reportBadDefinition(cls3, String.format("Cannot construct instance of %s (no Creators, like default constructor, exist): %s", ClassUtil.nameOf(cls3), str_format));
        }
        return deserializationContext2.reportInputMismatch(cls3, String.format("Cannot construct instance of %s (although at least one Creator exists): %s", ClassUtil.nameOf(cls3), str_format), new Object[0]);
    }

    public Object handleInstantiationProblem(Class<?> cls, Object obj, Throwable th) throws IOException {
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            Object objHandleInstantiationProblem = problemHandlers.value().handleInstantiationProblem(this, cls, obj, th);
            if (objHandleInstantiationProblem != DeserializationProblemHandler.NOT_HANDLED) {
                if (_isCompatible(cls, objHandleInstantiationProblem)) {
                    return objHandleInstantiationProblem;
                }
                reportBadDefinition(constructType(cls), String.format("DeserializationProblemHandler.handleInstantiationProblem() for type %s returned value of type %s", ClassUtil.getClassDescription(cls), ClassUtil.classNameOf(objHandleInstantiationProblem)));
            }
        }
        ClassUtil.throwIfIOE(th);
        if (!isEnabled(DeserializationFeature.WRAP_EXCEPTIONS)) {
            ClassUtil.throwIfRTE(th);
        }
        throw instantiationException(cls, th);
    }

    public Object handleUnexpectedToken(Class<?> cls, JsonParser jsonParser) throws IOException {
        return handleUnexpectedToken(constructType(cls), jsonParser.currentToken(), jsonParser, (String) null, new Object[0]);
    }

    public Object handleUnexpectedToken(Class<?> cls, JsonToken jsonToken, JsonParser jsonParser, String str, Object... objArr) throws IOException {
        return handleUnexpectedToken(constructType(cls), jsonToken, jsonParser, str, objArr);
    }

    public Object handleUnexpectedToken(JavaType javaType, JsonParser jsonParser) throws IOException {
        return handleUnexpectedToken(javaType, jsonParser.currentToken(), jsonParser, (String) null, new Object[0]);
    }

    public Object handleUnexpectedToken(JavaType javaType, JsonToken jsonToken, JsonParser jsonParser, String str, Object... objArr) throws IOException {
        String str_format = _format(str, objArr);
        LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers();
        while (problemHandlers != null) {
            JavaType javaType2 = javaType;
            DeserializationContext deserializationContext = this;
            JsonParser jsonParser2 = jsonParser;
            JsonToken jsonToken2 = jsonToken;
            Object objHandleUnexpectedToken = problemHandlers.value().handleUnexpectedToken(deserializationContext, javaType2, jsonToken2, jsonParser2, str_format);
            if (objHandleUnexpectedToken != DeserializationProblemHandler.NOT_HANDLED) {
                if (deserializationContext._isCompatible(javaType2.getRawClass(), objHandleUnexpectedToken)) {
                    return objHandleUnexpectedToken;
                }
                deserializationContext.reportBadDefinition(javaType2, String.format("DeserializationProblemHandler.handleUnexpectedToken() for type %s returned value of type %s", ClassUtil.getTypeDescription(javaType2), ClassUtil.classNameOf(objHandleUnexpectedToken)));
            }
            problemHandlers = problemHandlers.next();
            this = deserializationContext;
            javaType = javaType2;
            jsonToken = jsonToken2;
            jsonParser = jsonParser2;
        }
        JsonParser jsonParser3 = jsonParser;
        JsonToken jsonToken3 = jsonToken;
        JavaType javaType3 = javaType;
        DeserializationContext deserializationContext2 = this;
        if (str_format == null) {
            String typeDescription = ClassUtil.getTypeDescription(javaType3);
            if (jsonToken3 == null) {
                str_format = String.format("Unexpected end-of-input when trying read value of type %s", typeDescription);
            } else {
                str_format = String.format("Cannot deserialize value of type %s from %s (token `JsonToken.%s`)", typeDescription, deserializationContext2._shapeForToken(jsonToken3), jsonToken3);
            }
        }
        if (jsonToken3 != null && jsonToken3.isScalarValue()) {
            jsonParser3.getText();
        }
        deserializationContext2.reportInputMismatch(javaType3, str_format, new Object[0]);
        return null;
    }

    public JavaType handleUnknownTypeId(JavaType javaType, String str, TypeIdResolver typeIdResolver, String str2) throws IOException {
        LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers();
        while (problemHandlers != null) {
            DeserializationContext deserializationContext = this;
            JavaType javaType2 = javaType;
            String str3 = str;
            TypeIdResolver typeIdResolver2 = typeIdResolver;
            String str4 = str2;
            JavaType javaTypeHandleUnknownTypeId = problemHandlers.value().handleUnknownTypeId(deserializationContext, javaType2, str3, typeIdResolver2, str4);
            if (javaTypeHandleUnknownTypeId != null) {
                if (javaTypeHandleUnknownTypeId.hasRawClass(Void.class)) {
                    return null;
                }
                if (javaTypeHandleUnknownTypeId.isTypeOrSubTypeOf(javaType2.getRawClass())) {
                    return javaTypeHandleUnknownTypeId;
                }
                throw deserializationContext.invalidTypeIdException(javaType2, str3, "problem handler tried to resolve into non-subtype: " + ClassUtil.getTypeDescription(javaTypeHandleUnknownTypeId));
            }
            problemHandlers = problemHandlers.next();
            this = deserializationContext;
            javaType = javaType2;
            str = str3;
            typeIdResolver = typeIdResolver2;
            str2 = str4;
        }
        DeserializationContext deserializationContext2 = this;
        JavaType javaType3 = javaType;
        String str5 = str;
        String str6 = str2;
        if (deserializationContext2.isEnabled(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE)) {
            throw deserializationContext2.invalidTypeIdException(javaType3, str5, str6);
        }
        return null;
    }

    public JavaType handleMissingTypeId(JavaType javaType, TypeIdResolver typeIdResolver, String str) throws IOException {
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            JavaType javaTypeHandleMissingTypeId = problemHandlers.value().handleMissingTypeId(this, javaType, typeIdResolver, str);
            if (javaTypeHandleMissingTypeId != null) {
                if (javaTypeHandleMissingTypeId.hasRawClass(Void.class)) {
                    return null;
                }
                if (javaTypeHandleMissingTypeId.isTypeOrSubTypeOf(javaType.getRawClass())) {
                    return javaTypeHandleMissingTypeId;
                }
                throw invalidTypeIdException(javaType, null, "problem handler tried to resolve into non-subtype: " + ClassUtil.getTypeDescription(javaTypeHandleMissingTypeId));
            }
        }
        throw missingTypeIdException(javaType, str);
    }

    public void handleBadMerge(JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        if (isEnabled(MapperFeature.IGNORE_MERGE_FOR_UNMERGEABLE)) {
            return;
        }
        JavaType javaTypeConstructType = constructType(jsonDeserializer.handledType());
        throw InvalidDefinitionException.from(getParser(), String.format("Invalid configuration: values of type %s cannot be merged", ClassUtil.getTypeDescription(javaTypeConstructType)), javaTypeConstructType);
    }

    protected boolean _isCompatible(Class<?> cls, Object obj) {
        if (obj == null || cls.isInstance(obj)) {
            return true;
        }
        return cls.isPrimitive() && ClassUtil.wrapperType(cls).isInstance(obj);
    }

    public void reportWrongTokenException(JsonDeserializer<?> jsonDeserializer, JsonToken jsonToken, String str, Object... objArr) throws JsonMappingException {
        throw wrongTokenException(getParser(), jsonDeserializer.handledType(), jsonToken, _format(str, objArr));
    }

    public void reportWrongTokenException(JavaType javaType, JsonToken jsonToken, String str, Object... objArr) throws JsonMappingException {
        throw wrongTokenException(getParser(), javaType, jsonToken, _format(str, objArr));
    }

    public void reportWrongTokenException(Class<?> cls, JsonToken jsonToken, String str, Object... objArr) throws JsonMappingException {
        throw wrongTokenException(getParser(), cls, jsonToken, _format(str, objArr));
    }

    public <T> T reportUnresolvedObjectId(ObjectIdReader objectIdReader, Object obj) throws JsonMappingException {
        return (T) reportInputMismatch(objectIdReader.idProperty, String.format("No Object Id found for an instance of %s, to assign to property '%s'", ClassUtil.classNameOf(obj), objectIdReader.propertyName), new Object[0]);
    }

    public <T> T reportInputMismatch(JsonDeserializer<?> jsonDeserializer, String str, Object... objArr) throws JsonMappingException {
        throw MismatchedInputException.from(getParser(), jsonDeserializer.handledType(), _format(str, objArr));
    }

    public <T> T reportInputMismatch(Class<?> cls, String str, Object... objArr) throws JsonMappingException {
        throw MismatchedInputException.from(getParser(), cls, _format(str, objArr));
    }

    public <T> T reportInputMismatch(JavaType javaType, String str, Object... objArr) throws JsonMappingException {
        throw MismatchedInputException.from(getParser(), javaType, _format(str, objArr));
    }

    public <T> T reportInputMismatch(BeanProperty beanProperty, String str, Object... objArr) throws JsonMappingException {
        AnnotatedMember member;
        MismatchedInputException mismatchedInputExceptionFrom = MismatchedInputException.from(getParser(), beanProperty == null ? null : beanProperty.getType(), _format(str, objArr));
        if (beanProperty != null && (member = beanProperty.getMember()) != null) {
            mismatchedInputExceptionFrom.prependPath(member.getDeclaringClass(), beanProperty.getName());
            throw mismatchedInputExceptionFrom;
        }
        throw mismatchedInputExceptionFrom;
    }

    public <T> T reportPropertyInputMismatch(Class<?> cls, String str, String str2, Object... objArr) throws JsonMappingException {
        MismatchedInputException mismatchedInputExceptionFrom = MismatchedInputException.from(getParser(), cls, _format(str2, objArr));
        if (str != null) {
            mismatchedInputExceptionFrom.prependPath(cls, str);
            throw mismatchedInputExceptionFrom;
        }
        throw mismatchedInputExceptionFrom;
    }

    public <T> T reportPropertyInputMismatch(JavaType javaType, String str, String str2, Object... objArr) throws JsonMappingException {
        return (T) reportPropertyInputMismatch(javaType.getRawClass(), str, str2, objArr);
    }

    public <T> T reportBadCoercion(JsonDeserializer<?> jsonDeserializer, Class<?> cls, Object obj, String str, Object... objArr) throws JsonMappingException {
        throw InvalidFormatException.from(getParser(), _format(str, objArr), obj, cls);
    }

    public <T> T reportTrailingTokens(Class<?> cls, JsonParser jsonParser, JsonToken jsonToken) throws JsonMappingException {
        throw MismatchedInputException.from(jsonParser, cls, String.format("Trailing token (of type %s) found after value (bound as %s): not allowed as per `DeserializationFeature.FAIL_ON_TRAILING_TOKENS`", jsonToken, ClassUtil.nameOf(cls)));
    }

    @Deprecated
    public void reportWrongTokenException(JsonParser jsonParser, JsonToken jsonToken, String str, Object... objArr) throws JsonMappingException {
        throw wrongTokenException(jsonParser, jsonToken, _format(str, objArr));
    }

    @Deprecated
    public void reportUnknownProperty(Object obj, String str, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        if (isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)) {
            throw UnrecognizedPropertyException.from(this._parser, obj, str, jsonDeserializer == null ? null : jsonDeserializer.getKnownPropertyNames());
        }
    }

    @Deprecated
    public void reportMissingContent(String str, Object... objArr) throws JsonMappingException {
        throw MismatchedInputException.from(getParser(), (JavaType) null, "No content to map due to end-of-input");
    }

    public <T> T reportBadTypeDefinition(BeanDescription beanDescription, String str, Object... objArr) throws JsonMappingException {
        throw InvalidDefinitionException.from(this._parser, String.format("Invalid type definition for type %s: %s", ClassUtil.nameOf(beanDescription.getBeanClass()), _format(str, objArr)), beanDescription, (BeanPropertyDefinition) null);
    }

    public <T> T reportBadPropertyDefinition(BeanDescription beanDescription, BeanPropertyDefinition beanPropertyDefinition, String str, Object... objArr) throws JsonMappingException {
        throw InvalidDefinitionException.from(this._parser, String.format("Invalid definition for property %s (of type %s): %s", ClassUtil.nameOf(beanPropertyDefinition), ClassUtil.nameOf(beanDescription.getBeanClass()), _format(str, objArr)), beanDescription, beanPropertyDefinition);
    }

    @Override // com.fasterxml.jackson.databind.DatabindContext
    public <T> T reportBadDefinition(JavaType javaType, String str) throws JsonMappingException {
        throw InvalidDefinitionException.from(this._parser, str, javaType);
    }

    @Deprecated
    public <T> T reportBadMerge(JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        handleBadMerge(jsonDeserializer);
        return null;
    }

    public JsonMappingException wrongTokenException(JsonParser jsonParser, JavaType javaType, JsonToken jsonToken, String str) {
        return MismatchedInputException.from(jsonParser, javaType, _colonConcat(String.format("Unexpected token (%s), expected %s", jsonParser.currentToken(), jsonToken), str));
    }

    public JsonMappingException wrongTokenException(JsonParser jsonParser, Class<?> cls, JsonToken jsonToken, String str) {
        return MismatchedInputException.from(jsonParser, cls, _colonConcat(String.format("Unexpected token (%s), expected %s", jsonParser.currentToken(), jsonToken), str));
    }

    @Deprecated
    public JsonMappingException wrongTokenException(JsonParser jsonParser, JsonToken jsonToken, String str) {
        return wrongTokenException(jsonParser, (JavaType) null, jsonToken, str);
    }

    public JsonMappingException weirdKeyException(Class<?> cls, String str, String str2) {
        return InvalidFormatException.from(this._parser, String.format("Cannot deserialize Map key of type %s from String %s: %s", ClassUtil.nameOf(cls), _quotedString(str), str2), str, cls);
    }

    public JsonMappingException weirdStringException(String str, Class<?> cls, String str2) {
        return InvalidFormatException.from(this._parser, String.format("Cannot deserialize value of type %s from String %s: %s", ClassUtil.nameOf(cls), _quotedString(str), str2), str, cls);
    }

    public JsonMappingException weirdNumberException(Number number, Class<?> cls, String str) {
        return InvalidFormatException.from(this._parser, String.format("Cannot deserialize value of type %s from number %s: %s", ClassUtil.nameOf(cls), String.valueOf(number), str), number, cls);
    }

    public JsonMappingException weirdNativeValueException(Object obj, Class<?> cls) {
        return InvalidFormatException.from(this._parser, String.format("Cannot deserialize value of type %s from native value (`JsonToken.VALUE_EMBEDDED_OBJECT`) of type %s: incompatible types", ClassUtil.nameOf(cls), ClassUtil.classNameOf(obj)), obj, cls);
    }

    public JsonMappingException instantiationException(Class<?> cls, Throwable th) {
        String strExceptionMessage;
        if (th == null) {
            strExceptionMessage = "N/A";
        } else {
            strExceptionMessage = ClassUtil.exceptionMessage(th);
            if (strExceptionMessage == null) {
                strExceptionMessage = ClassUtil.nameOf(th.getClass());
            }
        }
        return ValueInstantiationException.from(this._parser, String.format("Cannot construct instance of %s, problem: %s", ClassUtil.nameOf(cls), strExceptionMessage), constructType(cls), th);
    }

    public JsonMappingException instantiationException(Class<?> cls, String str) {
        return ValueInstantiationException.from(this._parser, String.format("Cannot construct instance of %s: %s", ClassUtil.nameOf(cls), str), constructType(cls));
    }

    @Override // com.fasterxml.jackson.databind.DatabindContext
    public JsonMappingException invalidTypeIdException(JavaType javaType, String str, String str2) {
        return InvalidTypeIdException.from(this._parser, _colonConcat(String.format("Could not resolve type id '%s' as a subtype of %s", str, ClassUtil.getTypeDescription(javaType)), str2), javaType, str);
    }

    public JsonMappingException missingTypeIdException(JavaType javaType, String str) {
        return InvalidTypeIdException.from(this._parser, _colonConcat(String.format("Could not resolve subtype of %s", javaType), str), javaType, null);
    }

    @Deprecated
    public JsonMappingException unknownTypeException(JavaType javaType, String str, String str2) {
        return MismatchedInputException.from(this._parser, javaType, _colonConcat(String.format("Could not resolve type id '%s' into a subtype of %s", str, ClassUtil.getTypeDescription(javaType)), str2));
    }

    @Deprecated
    public JsonMappingException endOfInputException(Class<?> cls) {
        return MismatchedInputException.from(this._parser, cls, "Unexpected end-of-input when trying to deserialize a " + cls.getName());
    }

    @Deprecated
    public void reportMappingException(String str, Object... objArr) throws JsonMappingException {
        throw JsonMappingException.from(getParser(), _format(str, objArr));
    }

    @Deprecated
    public JsonMappingException mappingException(String str) {
        return JsonMappingException.from(getParser(), str);
    }

    @Deprecated
    public JsonMappingException mappingException(String str, Object... objArr) {
        return JsonMappingException.from(getParser(), _format(str, objArr));
    }

    @Deprecated
    public JsonMappingException mappingException(Class<?> cls) {
        return mappingException(cls, this._parser.currentToken());
    }

    @Deprecated
    public JsonMappingException mappingException(Class<?> cls, JsonToken jsonToken) {
        return JsonMappingException.from(this._parser, String.format("Cannot deserialize instance of %s out of %s token", ClassUtil.nameOf(cls), jsonToken));
    }

    @Deprecated
    protected DateFormat getDateFormat() {
        return _getDateFormat();
    }

    protected DateFormat _getDateFormat() {
        DateFormat dateFormat = this._dateFormat;
        if (dateFormat != null) {
            return dateFormat;
        }
        DateFormat dateFormat2 = (DateFormat) this._config.getDateFormat().clone();
        this._dateFormat = dateFormat2;
        return dateFormat2;
    }

    /* JADX INFO: renamed from: com.fasterxml.jackson.databind.DeserializationContext$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$core$JsonToken;

        static {
            int[] iArr = new int[JsonToken.values().length];
            $SwitchMap$com$fasterxml$jackson$core$JsonToken = iArr;
            try {
                iArr[JsonToken.START_OBJECT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.END_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.START_ARRAY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.END_ARRAY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_EMBEDDED_OBJECT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NULL.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.NOT_AVAILABLE.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    protected String _shapeForToken(JsonToken jsonToken) {
        if (jsonToken != null) {
            switch (AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken[jsonToken.ordinal()]) {
                case 1:
                case 2:
                case 3:
                    return "Object value";
                case 4:
                case 5:
                    return "Array value";
                case 6:
                case 7:
                    return "Boolean value";
                case 8:
                    return "Embedded Object";
                case 9:
                    return "Floating-point value";
                case 10:
                    return "Integer value";
                case 11:
                    return "String value";
                case 12:
                    return "Null value";
                default:
                    return "[Unavailable value]";
            }
        }
        return "<end of input>";
    }
}
