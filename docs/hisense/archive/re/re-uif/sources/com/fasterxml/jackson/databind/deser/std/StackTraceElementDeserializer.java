package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class StackTraceElementDeserializer extends StdScalarDeserializer<StackTraceElement> {
    private static final long serialVersionUID = 1;

    public StackTraceElementDeserializer() {
        super((Class<?>) StackTraceElement.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public StackTraceElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        int i_parseIntPrimitive;
        JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
        if (jsonTokenCurrentToken != JsonToken.START_OBJECT) {
            if (jsonTokenCurrentToken == JsonToken.START_ARRAY && deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                StackTraceElement stackTraceElementDeserialize = deserialize(jsonParser, deserializationContext);
                if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                    return stackTraceElementDeserialize;
                }
                handleMissingEndArrayForSingle(jsonParser, deserializationContext);
                throw null;
            }
            deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser);
            throw null;
        }
        int i = -1;
        String text = "";
        String text2 = text;
        String text3 = text2;
        String text4 = null;
        String text5 = null;
        String text6 = null;
        while (true) {
            JsonToken jsonTokenNextValue = jsonParser.nextValue();
            if (jsonTokenNextValue != JsonToken.END_OBJECT) {
                String currentName = jsonParser.getCurrentName();
                if ("className".equals(currentName)) {
                    text = jsonParser.getText();
                } else if ("classLoaderName".equals(currentName)) {
                    text6 = jsonParser.getText();
                } else if ("fileName".equals(currentName)) {
                    text3 = jsonParser.getText();
                } else if ("lineNumber".equals(currentName)) {
                    if (jsonTokenNextValue.isNumeric()) {
                        i_parseIntPrimitive = jsonParser.getIntValue();
                    } else {
                        i_parseIntPrimitive = _parseIntPrimitive(jsonParser, deserializationContext);
                    }
                    i = i_parseIntPrimitive;
                } else if ("methodName".equals(currentName)) {
                    text2 = jsonParser.getText();
                } else if (!"nativeMethod".equals(currentName)) {
                    if ("moduleName".equals(currentName)) {
                        text4 = jsonParser.getText();
                    } else if ("moduleVersion".equals(currentName)) {
                        text5 = jsonParser.getText();
                    } else if (!"declaringClass".equals(currentName) && !"format".equals(currentName)) {
                        handleUnknownProperty(jsonParser, deserializationContext, this._valueClass, currentName);
                    }
                }
                jsonParser.skipChildren();
            } else {
                return constructValue(deserializationContext, text, text2, text3, i, text4, text5, text6);
            }
        }
    }

    protected StackTraceElement constructValue(DeserializationContext deserializationContext, String str, String str2, String str3, int i, String str4, String str5, String str6) {
        return new StackTraceElement(str, str2, str3, i);
    }
}
