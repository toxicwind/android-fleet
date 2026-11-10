package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.util.RequestPayload;

/* JADX INFO: loaded from: classes.dex */
public class JsonParseException extends StreamReadException {
    private static final long serialVersionUID = 2;

    public JsonParseException(JsonParser jsonParser, String str) {
        super(jsonParser, str);
    }

    public JsonParseException(JsonParser jsonParser, String str, Throwable th) {
        super(jsonParser, str, th);
    }

    public JsonParseException withRequestPayload(RequestPayload requestPayload) {
        this._requestPayload = requestPayload;
        return this;
    }

    @Override // com.fasterxml.jackson.core.exc.StreamReadException, com.fasterxml.jackson.core.JsonProcessingException
    public JsonParser getProcessor() {
        return super.getProcessor();
    }

    @Override // com.fasterxml.jackson.core.exc.StreamReadException, com.fasterxml.jackson.core.JsonProcessingException, java.lang.Throwable
    public String getMessage() {
        return super.getMessage();
    }
}
