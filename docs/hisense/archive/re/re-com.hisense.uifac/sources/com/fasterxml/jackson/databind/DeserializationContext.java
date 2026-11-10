package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualKeyDeserializer;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.deser.DeserializerCache;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;
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
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.LinkedNode;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public abstract class DeserializationContext extends DatabindContext implements Serializable {
    private static final long serialVersionUID = 1;
    protected transient ArrayBuilders _arrayBuilders;
    protected final DeserializerCache _cache;
    protected final DeserializationConfig _config;
    protected LinkedNode<JavaType> _currentType;
    protected transient DateFormat _dateFormat;
    protected final DeserializerFactory _factory;
    protected final int _featureFlags;
    protected final InjectableValues _injectableValues;
    protected transient ObjectBuffer _objectBuffer;
    protected transient JsonParser _parser;
    protected final Class<?> _view;

    public abstract JsonDeserializer<Object> deserializerInstance(Annotated annotated, Object obj) throws JsonMappingException;

    public abstract ReadableObjectId findObjectId(Object obj, ObjectIdGenerator<?> objectIdGenerator, ObjectIdResolver objectIdResolver);

    public abstract KeyDeserializer keyDeserializerInstance(Annotated annotated, Object obj) throws JsonMappingException;

    protected DeserializationContext(DeserializerFactory deserializerFactory, DeserializerCache deserializerCache) {
        if (deserializerFactory == null) {
            throw new NullPointerException("Cannot pass null DeserializerFactory");
        }
        this._factory = deserializerFactory;
        this._cache = deserializerCache == null ? new DeserializerCache() : deserializerCache;
        this._featureFlags = 0;
        this._config = null;
        this._injectableValues = null;
        this._view = null;
    }

    protected DeserializationContext(DeserializationContext deserializationContext, DeserializationConfig deserializationConfig, JsonParser jsonParser, InjectableValues injectableValues) {
        this._cache = deserializationContext._cache;
        this._factory = deserializationContext._factory;
        this._config = deserializationConfig;
        this._featureFlags = deserializationConfig.getDeserializationFeatures();
        this._view = deserializationConfig.getActiveView();
        this._parser = jsonParser;
        this._injectableValues = injectableValues;
        deserializationConfig.getAttributes();
    }

    @Override // com.fasterxml.jackson.databind.DatabindContext
    public DeserializationConfig getConfig() {
        return this._config;
    }

    public final Class<?> getActiveView() {
        return this._view;
    }

    public final boolean canOverrideAccessModifiers() {
        return this._config.canOverrideAccessModifiers();
    }

    public final boolean isEnabled(MapperFeature mapperFeature) {
        return this._config.isEnabled(mapperFeature);
    }

    public final JsonFormat.Value getDefaultPropertyFormat(Class<?> cls) {
        return this._config.getDefaultPropertyFormat(cls);
    }

    public final AnnotationIntrospector getAnnotationIntrospector() {
        return this._config.getAnnotationIntrospector();
    }

    @Override // com.fasterxml.jackson.databind.DatabindContext
    public final TypeFactory getTypeFactory() {
        return this._config.getTypeFactory();
    }

    public JavaType constructSpecializedType(JavaType javaType, Class<?> cls) throws IllegalArgumentException {
        return javaType.hasRawClass(cls) ? javaType : getConfig().getTypeFactory().constructSpecializedType(javaType, cls, false);
    }

    public Locale getLocale() {
        return this._config.getLocale();
    }

    public TimeZone getTimeZone() {
        return this._config.getTimeZone();
    }

    public final boolean isEnabled(DeserializationFeature deserializationFeature) {
        return (this._featureFlags & deserializationFeature.getMask()) != 0;
    }

    public final int getDeserializationFeatures() {
        return this._featureFlags;
    }

    public final boolean hasSomeOfFeatures(int i) {
        return (this._featureFlags & i) != 0;
    }

    public final JsonParser getParser() {
        return this._parser;
    }

    public final Object findInjectableValue(Object obj, BeanProperty beanProperty, Object obj2) throws JsonMappingException {
        if (this._injectableValues == null) {
            reportBadDefinition(ClassUtil.classOf(obj), String.format("No 'injectableValues' configured, cannot inject value with id [%s]", obj));
        }
        return this._injectableValues.findInjectableValue(obj, this, beanProperty, obj2);
    }

    public final Base64Variant getBase64Variant() {
        return this._config.getBase64Variant();
    }

    public final JsonNodeFactory getNodeFactory() {
        return this._config.getNodeFactory();
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
        KeyDeserializer keyDeserializerFindKeyDeserializer = this._cache.findKeyDeserializer(this, this._factory, javaType);
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
        boolean z = jsonDeserializer instanceof ContextualDeserializer;
        JsonDeserializer<?> jsonDeserializer2 = jsonDeserializer;
        if (z) {
            this._currentType = new LinkedNode<>(javaType, this._currentType);
            try {
                JsonDeserializer<?> jsonDeserializerCreateContextual = ((ContextualDeserializer) jsonDeserializer).createContextual(this, beanProperty);
            } finally {
                this._currentType = this._currentType.next();
            }
        }
        return jsonDeserializer2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public JsonDeserializer<?> handleSecondaryContextualization(JsonDeserializer<?> jsonDeserializer, BeanProperty beanProperty, JavaType javaType) throws JsonMappingException {
        boolean z = jsonDeserializer instanceof ContextualDeserializer;
        JsonDeserializer<?> jsonDeserializer2 = jsonDeserializer;
        if (z) {
            this._currentType = new LinkedNode<>(javaType, this._currentType);
            try {
                JsonDeserializer<?> jsonDeserializerCreateContextual = ((ContextualDeserializer) jsonDeserializer).createContextual(this, beanProperty);
            } finally {
                this._currentType = this._currentType.next();
            }
        }
        return jsonDeserializer2;
    }

    public Date parseDate(String str) throws IllegalArgumentException {
        try {
            return getDateFormat().parse(str);
        } catch (ParseException e) {
            throw new IllegalArgumentException(String.format("Failed to parse Date value '%s': %s", str, ClassUtil.exceptionMessage(e)));
        }
    }

    public Calendar constructCalendar(Date date) {
        Calendar calendar = Calendar.getInstance(getTimeZone());
        calendar.setTime(date);
        return calendar;
    }

    public boolean handleUnknownProperty(JsonParser jsonParser, JsonDeserializer<?> jsonDeserializer, Object obj, String str) throws IOException {
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            if (problemHandlers.value().handleUnknownProperty(this, jsonParser, jsonDeserializer, obj, str)) {
                return true;
            }
        }
        if (!isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)) {
            jsonParser.skipChildren();
            return true;
        }
        throw UnrecognizedPropertyException.from(this._parser, obj, str, jsonDeserializer == null ? null : jsonDeserializer.getKnownPropertyNames());
    }

    public Object handleWeirdKey(Class<?> cls, String str, String str2, Object... objArr) throws IOException {
        String str_format = _format(str2, objArr);
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            problemHandlers.value().handleWeirdKey(this, cls, str, str_format);
            Object obj = DeserializationProblemHandler.NOT_HANDLED;
        }
        throw weirdKeyException(cls, str, str_format);
    }

    public Object handleWeirdStringValue(Class<?> cls, String str, String str2, Object... objArr) throws IOException {
        String str_format = _format(str2, objArr);
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            problemHandlers.value().handleWeirdStringValue(this, cls, str, str_format);
            Object obj = DeserializationProblemHandler.NOT_HANDLED;
        }
        throw weirdStringException(str, cls, str_format);
    }

    public Object handleWeirdNumberValue(Class<?> cls, Number number, String str, Object... objArr) throws IOException {
        String str_format = _format(str, objArr);
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            problemHandlers.value().handleWeirdNumberValue(this, cls, number, str_format);
            Object obj = DeserializationProblemHandler.NOT_HANDLED;
        }
        throw weirdNumberException(number, cls, str_format);
    }

    public Object handleWeirdNativeValue(JavaType javaType, Object obj, JsonParser jsonParser) throws IOException {
        Class<?> rawClass = javaType.getRawClass();
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            problemHandlers.value().handleWeirdNativeValue(this, javaType, obj, jsonParser);
            Object obj2 = DeserializationProblemHandler.NOT_HANDLED;
        }
        throw weirdNativeValueException(obj, rawClass);
    }

    public Object handleMissingInstantiator(Class<?> cls, ValueInstantiator valueInstantiator, JsonParser jsonParser, String str, Object... objArr) throws IOException {
        if (jsonParser == null) {
            jsonParser = getParser();
        }
        String str_format = _format(str, objArr);
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            problemHandlers.value().handleMissingInstantiator(this, cls, valueInstantiator, jsonParser, str_format);
            Object obj = DeserializationProblemHandler.NOT_HANDLED;
        }
        if (valueInstantiator == null) {
            return reportBadDefinition(cls, String.format("Cannot construct instance of %s: %s", ClassUtil.nameOf(cls), str_format));
        }
        if (!valueInstantiator.canInstantiate()) {
            return reportBadDefinition(cls, String.format("Cannot construct instance of %s (no Creators, like default constructor, exist): %s", ClassUtil.nameOf(cls), str_format));
        }
        reportInputMismatch(cls, String.format("Cannot construct instance of %s (although at least one Creator exists): %s", ClassUtil.nameOf(cls), str_format), new Object[0]);
        throw null;
    }

    public Object handleInstantiationProblem(Class<?> cls, Object obj, Throwable th) throws IOException {
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            problemHandlers.value().handleInstantiationProblem(this, cls, obj, th);
            Object obj2 = DeserializationProblemHandler.NOT_HANDLED;
        }
        ClassUtil.throwIfIOE(th);
        if (!isEnabled(DeserializationFeature.WRAP_EXCEPTIONS)) {
            ClassUtil.throwIfRTE(th);
        }
        throw instantiationException(cls, th);
    }

    public Object handleUnexpectedToken(Class<?> cls, JsonParser jsonParser) throws IOException {
        handleUnexpectedToken(constructType(cls), jsonParser.getCurrentToken(), jsonParser, (String) null, new Object[0]);
        throw null;
    }

    public Object handleUnexpectedToken(Class<?> cls, JsonToken jsonToken, JsonParser jsonParser, String str, Object... objArr) throws IOException {
        handleUnexpectedToken(constructType(cls), jsonToken, jsonParser, str, objArr);
        throw null;
    }

    public Object handleUnexpectedToken(JavaType javaType, JsonParser jsonParser) throws IOException {
        handleUnexpectedToken(javaType, jsonParser.getCurrentToken(), jsonParser, (String) null, new Object[0]);
        throw null;
    }

    public Object handleUnexpectedToken(JavaType javaType, JsonToken jsonToken, JsonParser jsonParser, String str, Object... objArr) throws IOException {
        String str_format = _format(str, objArr);
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            problemHandlers.value().handleUnexpectedToken(this, javaType, jsonToken, jsonParser, str_format);
            Object obj = DeserializationProblemHandler.NOT_HANDLED;
        }
        if (str_format == null) {
            if (jsonToken == null) {
                str_format = String.format("Unexpected end-of-input when binding data into %s", ClassUtil.getTypeDescription(javaType));
            } else {
                str_format = String.format("Cannot deserialize instance of %s out of %s token", ClassUtil.getTypeDescription(javaType), jsonToken);
            }
        }
        reportInputMismatch(javaType, str_format, new Object[0]);
        throw null;
    }

    public JavaType handleUnknownTypeId(JavaType javaType, String str, TypeIdResolver typeIdResolver, String str2) throws IOException {
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            JavaType javaTypeHandleUnknownTypeId = problemHandlers.value().handleUnknownTypeId(this, javaType, str, typeIdResolver, str2);
            if (javaTypeHandleUnknownTypeId != null) {
                if (javaTypeHandleUnknownTypeId.hasRawClass(Void.class)) {
                    return null;
                }
                if (javaTypeHandleUnknownTypeId.isTypeOrSubTypeOf(javaType.getRawClass())) {
                    return javaTypeHandleUnknownTypeId;
                }
                throw invalidTypeIdException(javaType, str, "problem handler tried to resolve into non-subtype: " + ClassUtil.getTypeDescription(javaTypeHandleUnknownTypeId));
            }
        }
        if (isEnabled(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE)) {
            throw invalidTypeIdException(javaType, str, str2);
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
        reportInputMismatch(objectIdReader.idProperty, String.format("No Object Id found for an instance of %s, to assign to property '%s'", ClassUtil.classNameOf(obj), objectIdReader.propertyName), new Object[0]);
        throw null;
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
        reportPropertyInputMismatch(javaType.getRawClass(), str, str2, objArr);
        throw null;
    }

    public <T> T reportTrailingTokens(Class<?> cls, JsonParser jsonParser, JsonToken jsonToken) throws JsonMappingException {
        throw MismatchedInputException.from(jsonParser, cls, String.format("Trailing token (of type %s) found after value (bound as %s): not allowed as per `DeserializationFeature.FAIL_ON_TRAILING_TOKENS`", jsonToken, ClassUtil.nameOf(cls)));
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

    public JsonMappingException wrongTokenException(JsonParser jsonParser, JavaType javaType, JsonToken jsonToken, String str) {
        return MismatchedInputException.from(jsonParser, javaType, _colonConcat(String.format("Unexpected token (%s), expected %s", jsonParser.getCurrentToken(), jsonToken), str));
    }

    public JsonMappingException wrongTokenException(JsonParser jsonParser, Class<?> cls, JsonToken jsonToken, String str) {
        return MismatchedInputException.from(jsonParser, cls, _colonConcat(String.format("Unexpected token (%s), expected %s", jsonParser.getCurrentToken(), jsonToken), str));
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

    @Override // com.fasterxml.jackson.databind.DatabindContext
    public JsonMappingException invalidTypeIdException(JavaType javaType, String str, String str2) {
        return InvalidTypeIdException.from(this._parser, _colonConcat(String.format("Could not resolve type id '%s' as a subtype of %s", str, ClassUtil.getTypeDescription(javaType)), str2), javaType, str);
    }

    public JsonMappingException missingTypeIdException(JavaType javaType, String str) {
        return InvalidTypeIdException.from(this._parser, _colonConcat(String.format("Missing type id when trying to resolve subtype of %s", javaType), str), javaType, null);
    }

    protected DateFormat getDateFormat() {
        DateFormat dateFormat = this._dateFormat;
        if (dateFormat != null) {
            return dateFormat;
        }
        DateFormat dateFormat2 = (DateFormat) this._config.getDateFormat().clone();
        this._dateFormat = dateFormat2;
        return dateFormat2;
    }
}
