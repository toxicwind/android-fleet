package com.fasterxml.jackson.databind.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes.dex */
public class JsonMapper extends ObjectMapper {
    private static final long serialVersionUID = 1;

    public JsonMapper() {
        this(new JsonFactory());
    }

    public JsonMapper(JsonFactory jsonFactory) {
        super(jsonFactory);
    }
}
