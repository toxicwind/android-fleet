package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;

/* JADX INFO: loaded from: classes.dex */
public interface NullValueProvider {
    Object getNullValue(DeserializationContext deserializationContext) throws JsonMappingException;
}
