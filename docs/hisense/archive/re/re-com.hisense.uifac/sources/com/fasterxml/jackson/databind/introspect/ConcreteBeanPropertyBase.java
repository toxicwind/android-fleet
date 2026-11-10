package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class ConcreteBeanPropertyBase implements BeanProperty, Serializable {
    private static final long serialVersionUID = 1;
    protected transient List<PropertyName> _aliases;
    protected final PropertyMetadata _metadata;

    protected ConcreteBeanPropertyBase(PropertyMetadata propertyMetadata) {
        this._metadata = propertyMetadata == null ? PropertyMetadata.STD_REQUIRED_OR_OPTIONAL : propertyMetadata;
    }

    protected ConcreteBeanPropertyBase(ConcreteBeanPropertyBase concreteBeanPropertyBase) {
        this._metadata = concreteBeanPropertyBase._metadata;
    }

    public boolean isRequired() {
        return this._metadata.isRequired();
    }

    @Override // com.fasterxml.jackson.databind.BeanProperty
    public PropertyMetadata getMetadata() {
        return this._metadata;
    }

    @Override // com.fasterxml.jackson.databind.BeanProperty
    public JsonFormat.Value findPropertyFormat(MapperConfig<?> mapperConfig, Class<?> cls) {
        AnnotatedMember member;
        JsonFormat.Value defaultPropertyFormat = mapperConfig.getDefaultPropertyFormat(cls);
        AnnotationIntrospector annotationIntrospector = mapperConfig.getAnnotationIntrospector();
        JsonFormat.Value valueFindFormat = (annotationIntrospector == null || (member = getMember()) == null) ? null : annotationIntrospector.findFormat(member);
        if (defaultPropertyFormat == null) {
            return valueFindFormat == null ? BeanProperty.EMPTY_FORMAT : valueFindFormat;
        }
        return valueFindFormat == null ? defaultPropertyFormat : defaultPropertyFormat.withOverrides(valueFindFormat);
    }

    @Override // com.fasterxml.jackson.databind.BeanProperty
    public JsonInclude.Value findPropertyInclusion(MapperConfig<?> mapperConfig, Class<?> cls) {
        AnnotationIntrospector annotationIntrospector = mapperConfig.getAnnotationIntrospector();
        AnnotatedMember member = getMember();
        if (member == null) {
            return mapperConfig.getDefaultPropertyInclusion(cls);
        }
        JsonInclude.Value defaultInclusion = mapperConfig.getDefaultInclusion(cls, member.getRawType());
        if (annotationIntrospector == null) {
            return defaultInclusion;
        }
        JsonInclude.Value valueFindPropertyInclusion = annotationIntrospector.findPropertyInclusion(member);
        return defaultInclusion == null ? valueFindPropertyInclusion : defaultInclusion.withOverrides(valueFindPropertyInclusion);
    }

    public List<PropertyName> findAliases(MapperConfig<?> mapperConfig) {
        AnnotatedMember member;
        List<PropertyName> listEmptyList = this._aliases;
        if (listEmptyList == null) {
            AnnotationIntrospector annotationIntrospector = mapperConfig.getAnnotationIntrospector();
            if (annotationIntrospector != null && (member = getMember()) != null) {
                listEmptyList = annotationIntrospector.findPropertyAliases(member);
            }
            if (listEmptyList == null) {
                listEmptyList = Collections.emptyList();
            }
            this._aliases = listEmptyList;
        }
        return listEmptyList;
    }
}
