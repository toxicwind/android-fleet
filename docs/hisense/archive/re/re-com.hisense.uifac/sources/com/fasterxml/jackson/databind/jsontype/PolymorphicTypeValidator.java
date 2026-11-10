package com.fasterxml.jackson.databind.jsontype;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public abstract class PolymorphicTypeValidator implements Serializable {
    private static final long serialVersionUID = 1;

    public static abstract class Base extends PolymorphicTypeValidator implements Serializable {
        private static final long serialVersionUID = 1;
    }

    public enum Validity {
        ALLOWED,
        DENIED,
        INDETERMINATE
    }

    public abstract Validity validateBaseType(MapperConfig<?> mapperConfig, JavaType javaType);

    public abstract Validity validateSubClassName(MapperConfig<?> mapperConfig, JavaType javaType, String str) throws JsonMappingException;

    public abstract Validity validateSubType(MapperConfig<?> mapperConfig, JavaType javaType, JavaType javaType2) throws JsonMappingException;
}
