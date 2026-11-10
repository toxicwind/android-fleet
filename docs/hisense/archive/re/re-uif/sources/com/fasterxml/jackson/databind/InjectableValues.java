package com.fasterxml.jackson.databind;

/* JADX INFO: loaded from: classes.dex */
public abstract class InjectableValues {
    public abstract Object findInjectableValue(Object obj, DeserializationContext deserializationContext, BeanProperty beanProperty, Object obj2) throws JsonMappingException;
}
