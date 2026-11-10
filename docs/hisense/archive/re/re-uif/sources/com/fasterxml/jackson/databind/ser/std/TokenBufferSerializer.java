package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
@JacksonStdImpl
public class TokenBufferSerializer extends StdSerializer<TokenBuffer> {
    public TokenBufferSerializer() {
        super(TokenBuffer.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(TokenBuffer tokenBuffer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        tokenBuffer.serialize(jsonGenerator);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serializeWithType(TokenBuffer tokenBuffer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        WritableTypeId writableTypeIdWriteTypePrefix = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(tokenBuffer, JsonToken.VALUE_EMBEDDED_OBJECT));
        serialize(tokenBuffer, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffix(jsonGenerator, writableTypeIdWriteTypePrefix);
    }
}
