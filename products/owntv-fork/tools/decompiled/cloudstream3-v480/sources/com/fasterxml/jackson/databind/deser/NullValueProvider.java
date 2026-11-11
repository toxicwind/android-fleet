package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.util.AccessPattern;

/* JADX INFO: loaded from: classes5.dex */
public interface NullValueProvider {
    Object getAbsentValue(DeserializationContext deserializationContext) throws JsonMappingException;

    AccessPattern getNullAccessPattern();

    Object getNullValue(DeserializationContext deserializationContext) throws JsonMappingException;

    /* JADX INFO: renamed from: com.fasterxml.jackson.databind.deser.NullValueProvider$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
    }
}
