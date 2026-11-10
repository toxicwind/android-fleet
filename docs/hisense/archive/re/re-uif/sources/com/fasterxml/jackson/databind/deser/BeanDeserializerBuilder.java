package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdValueProperty;
import com.fasterxml.jackson.databind.deser.impl.ValueInjector;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.Annotations;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class BeanDeserializerBuilder {
    protected SettableAnyProperty _anySetter;
    protected HashMap<String, SettableBeanProperty> _backRefProperties;
    protected final BeanDescription _beanDesc;
    protected AnnotatedMethod _buildMethod;
    protected final DeserializationConfig _config;
    protected final DeserializationContext _context;
    protected HashSet<String> _ignorableProps;
    protected boolean _ignoreAllUnknown;
    protected List<ValueInjector> _injectables;
    protected ObjectIdReader _objectIdReader;
    protected final Map<String, SettableBeanProperty> _properties = new LinkedHashMap();
    protected ValueInstantiator _valueInstantiator;

    public BeanDeserializerBuilder(BeanDescription beanDescription, DeserializationContext deserializationContext) {
        this._beanDesc = beanDescription;
        this._context = deserializationContext;
        this._config = deserializationContext.getConfig();
    }

    public void addOrReplaceProperty(SettableBeanProperty settableBeanProperty, boolean z) {
        this._properties.put(settableBeanProperty.getName(), settableBeanProperty);
    }

    public void addProperty(SettableBeanProperty settableBeanProperty) {
        SettableBeanProperty settableBeanPropertyPut = this._properties.put(settableBeanProperty.getName(), settableBeanProperty);
        if (settableBeanPropertyPut == null || settableBeanPropertyPut == settableBeanProperty) {
            return;
        }
        throw new IllegalArgumentException("Duplicate property '" + settableBeanProperty.getName() + "' for " + this._beanDesc.getType());
    }

    public void addBackReferenceProperty(String str, SettableBeanProperty settableBeanProperty) {
        if (this._backRefProperties == null) {
            this._backRefProperties = new HashMap<>(4);
        }
        settableBeanProperty.fixAccess(this._config);
        this._backRefProperties.put(str, settableBeanProperty);
    }

    public void addInjectable(PropertyName propertyName, JavaType javaType, Annotations annotations, AnnotatedMember annotatedMember, Object obj) {
        if (this._injectables == null) {
            this._injectables = new ArrayList();
        }
        boolean zCanOverrideAccessModifiers = this._config.canOverrideAccessModifiers();
        boolean z = zCanOverrideAccessModifiers && this._config.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS);
        if (zCanOverrideAccessModifiers) {
            annotatedMember.fixAccess(z);
        }
        this._injectables.add(new ValueInjector(propertyName, javaType, annotatedMember, obj));
    }

    public void addIgnorable(String str) {
        if (this._ignorableProps == null) {
            this._ignorableProps = new HashSet<>();
        }
        this._ignorableProps.add(str);
    }

    public void addCreatorProperty(SettableBeanProperty settableBeanProperty) {
        addProperty(settableBeanProperty);
    }

    public void setAnySetter(SettableAnyProperty settableAnyProperty) {
        if (this._anySetter != null && settableAnyProperty != null) {
            throw new IllegalStateException("_anySetter already set to non-null");
        }
        this._anySetter = settableAnyProperty;
    }

    public void setIgnoreUnknownProperties(boolean z) {
        this._ignoreAllUnknown = z;
    }

    public void setValueInstantiator(ValueInstantiator valueInstantiator) {
        this._valueInstantiator = valueInstantiator;
    }

    public void setObjectIdReader(ObjectIdReader objectIdReader) {
        this._objectIdReader = objectIdReader;
    }

    public void setPOJOBuilder(AnnotatedMethod annotatedMethod, JsonPOJOBuilder.Value value) {
        this._buildMethod = annotatedMethod;
    }

    public SettableBeanProperty findProperty(PropertyName propertyName) {
        return this._properties.get(propertyName.getSimpleName());
    }

    public SettableAnyProperty getAnySetter() {
        return this._anySetter;
    }

    public ValueInstantiator getValueInstantiator() {
        return this._valueInstantiator;
    }

    public List<ValueInjector> getInjectables() {
        return this._injectables;
    }

    public ObjectIdReader getObjectIdReader() {
        return this._objectIdReader;
    }

    public AnnotatedMethod getBuildMethod() {
        return this._buildMethod;
    }

    public boolean hasIgnorable(String str) {
        HashSet<String> hashSet = this._ignorableProps;
        return hashSet != null && hashSet.contains(str);
    }

    public JsonDeserializer<?> build() {
        boolean z;
        Collection<SettableBeanProperty> collectionValues = this._properties.values();
        _fixAccess(collectionValues);
        BeanPropertyMap beanPropertyMapConstruct = BeanPropertyMap.construct(this._config, collectionValues, _collectAliases(collectionValues));
        beanPropertyMapConstruct.assignIndexes();
        boolean z2 = !this._config.isEnabled(MapperFeature.DEFAULT_VIEW_INCLUSION);
        if (!z2) {
            Iterator<SettableBeanProperty> it = collectionValues.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = z2;
                    break;
                }
                if (it.next().hasViews()) {
                    z = true;
                    break;
                }
            }
        } else {
            z = z2;
            break;
        }
        if (this._objectIdReader != null) {
            beanPropertyMapConstruct = beanPropertyMapConstruct.withProperty(new ObjectIdValueProperty(this._objectIdReader, PropertyMetadata.STD_REQUIRED));
        }
        return new BeanDeserializer(this, this._beanDesc, beanPropertyMapConstruct, this._backRefProperties, this._ignorableProps, this._ignoreAllUnknown, z);
    }

    public AbstractDeserializer buildAbstract() {
        return new AbstractDeserializer(this, this._beanDesc, this._backRefProperties, this._properties);
    }

    public JsonDeserializer<?> buildBuilderBased(JavaType javaType, String str) throws JsonMappingException {
        AnnotatedMethod annotatedMethod = this._buildMethod;
        boolean z = true;
        if (annotatedMethod == null) {
            if (!str.isEmpty()) {
                this._context.reportBadDefinition(this._beanDesc.getType(), String.format("Builder class %s does not have build method (name: '%s')", this._beanDesc.getBeanClass().getName(), str));
                throw null;
            }
        } else {
            Class<?> rawReturnType = annotatedMethod.getRawReturnType();
            Class<?> rawClass = javaType.getRawClass();
            if (rawReturnType != rawClass && !rawReturnType.isAssignableFrom(rawClass) && !rawClass.isAssignableFrom(rawReturnType)) {
                this._context.reportBadDefinition(this._beanDesc.getType(), String.format("Build method '%s' has wrong return type (%s), not compatible with POJO type (%s)", this._buildMethod.getFullName(), rawReturnType.getName(), javaType.getRawClass().getName()));
                throw null;
            }
        }
        Collection<SettableBeanProperty> collectionValues = this._properties.values();
        _fixAccess(collectionValues);
        BeanPropertyMap beanPropertyMapConstruct = BeanPropertyMap.construct(this._config, collectionValues, _collectAliases(collectionValues));
        beanPropertyMapConstruct.assignIndexes();
        boolean z2 = !this._config.isEnabled(MapperFeature.DEFAULT_VIEW_INCLUSION);
        if (!z2) {
            Iterator<SettableBeanProperty> it = collectionValues.iterator();
            do {
                if (!it.hasNext()) {
                    z = z2;
                    break;
                }
            } while (!it.next().hasViews());
        } else {
            z = z2;
            break;
        }
        if (this._objectIdReader != null) {
            beanPropertyMapConstruct = beanPropertyMapConstruct.withProperty(new ObjectIdValueProperty(this._objectIdReader, PropertyMetadata.STD_REQUIRED));
        }
        return createBuilderBasedDeserializer(javaType, beanPropertyMapConstruct, z);
    }

    protected JsonDeserializer<?> createBuilderBasedDeserializer(JavaType javaType, BeanPropertyMap beanPropertyMap, boolean z) {
        return new BuilderBasedDeserializer(this, this._beanDesc, javaType, beanPropertyMap, this._backRefProperties, this._ignorableProps, this._ignoreAllUnknown, z);
    }

    protected void _fixAccess(Collection<SettableBeanProperty> collection) {
        Iterator<SettableBeanProperty> it = collection.iterator();
        while (it.hasNext()) {
            it.next().fixAccess(this._config);
        }
        SettableAnyProperty settableAnyProperty = this._anySetter;
        if (settableAnyProperty != null) {
            settableAnyProperty.fixAccess(this._config);
        }
        AnnotatedMethod annotatedMethod = this._buildMethod;
        if (annotatedMethod != null) {
            annotatedMethod.fixAccess(this._config.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
        }
    }

    protected Map<String, List<PropertyName>> _collectAliases(Collection<SettableBeanProperty> collection) {
        AnnotationIntrospector annotationIntrospector = this._config.getAnnotationIntrospector();
        HashMap map = null;
        if (annotationIntrospector != null) {
            for (SettableBeanProperty settableBeanProperty : collection) {
                List<PropertyName> listFindPropertyAliases = annotationIntrospector.findPropertyAliases(settableBeanProperty.getMember());
                if (listFindPropertyAliases != null && !listFindPropertyAliases.isEmpty()) {
                    if (map == null) {
                        map = new HashMap();
                    }
                    map.put(settableBeanProperty.getName(), listFindPropertyAliases);
                }
            }
        }
        return map == null ? Collections.emptyMap() : map;
    }
}
