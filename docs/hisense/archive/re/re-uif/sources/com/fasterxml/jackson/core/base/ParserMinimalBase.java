package com.fasterxml.jackson.core.base;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.exc.InputCoercionException;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import kotlin.jvm.internal.IntCompanionObject;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class ParserMinimalBase extends JsonParser {
    protected JsonToken _currToken;
    protected static final byte[] NO_BYTES = new byte[0];
    protected static final BigInteger BI_MIN_INT = BigInteger.valueOf(-2147483648L);
    protected static final BigInteger BI_MAX_INT = BigInteger.valueOf(2147483647L);
    protected static final BigInteger BI_MIN_LONG = BigInteger.valueOf(Long.MIN_VALUE);
    protected static final BigInteger BI_MAX_LONG = BigInteger.valueOf(LongCompanionObject.MAX_VALUE);
    protected static final BigDecimal BD_MIN_LONG = new BigDecimal(BI_MIN_LONG);
    protected static final BigDecimal BD_MAX_LONG = new BigDecimal(BI_MAX_LONG);
    protected static final BigDecimal BD_MIN_INT = new BigDecimal(BI_MIN_INT);
    protected static final BigDecimal BD_MAX_INT = new BigDecimal(BI_MAX_INT);

    protected abstract void _handleEOF() throws JsonParseException;

    protected ParserMinimalBase(int i) {
        super(i);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonToken currentToken() {
        return this._currToken;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int currentTokenId() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == null) {
            return 0;
        }
        return jsonToken.id();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonToken getCurrentToken() {
        return this._currToken;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int getCurrentTokenId() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == null) {
            return 0;
        }
        return jsonToken.id();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean hasCurrentToken() {
        return this._currToken != null;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean hasTokenId(int i) {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == null) {
            return i == 0;
        }
        return jsonToken.id() == i;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean hasToken(JsonToken jsonToken) {
        return this._currToken == jsonToken;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean isExpectedStartArrayToken() {
        return this._currToken == JsonToken.START_ARRAY;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean isExpectedStartObjectToken() {
        return this._currToken == JsonToken.START_OBJECT;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonToken nextValue() throws IOException {
        JsonToken jsonTokenNextToken = nextToken();
        return jsonTokenNextToken == JsonToken.FIELD_NAME ? nextToken() : jsonTokenNextToken;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonParser skipChildren() throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != JsonToken.START_OBJECT && jsonToken != JsonToken.START_ARRAY) {
            return this;
        }
        int i = 1;
        while (true) {
            JsonToken jsonTokenNextToken = nextToken();
            if (jsonTokenNextToken == null) {
                _handleEOF();
                return this;
            }
            if (jsonTokenNextToken.isStructStart()) {
                i++;
            } else if (jsonTokenNextToken.isStructEnd()) {
                i--;
                if (i == 0) {
                    return this;
                }
            } else if (jsonTokenNextToken == JsonToken.NOT_AVAILABLE) {
                _reportError("Not enough content available for `skipChildren()`: non-blocking parser? (%s)", getClass().getName());
                throw null;
            }
        }
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public void clearCurrentToken() {
        if (this._currToken != null) {
            this._currToken = null;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int getValueAsInt() throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return getIntValue();
        }
        return getValueAsInt(0);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int getValueAsInt(int i) throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return getIntValue();
        }
        if (jsonToken != null) {
            int iId = jsonToken.id();
            if (iId == 6) {
                String text = getText();
                if (_hasTextualNull(text)) {
                    return 0;
                }
                return NumberInput.parseAsInt(text, i);
            }
            switch (iId) {
                case 9:
                    return 1;
                case 10:
                case 11:
                    return 0;
                case 12:
                    Object embeddedObject = getEmbeddedObject();
                    if (embeddedObject instanceof Number) {
                        return ((Number) embeddedObject).intValue();
                    }
                default:
                    return i;
            }
        }
        return i;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public long getValueAsLong() throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return getLongValue();
        }
        return getValueAsLong(0L);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public long getValueAsLong(long j) throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return getLongValue();
        }
        if (jsonToken != null) {
            int iId = jsonToken.id();
            if (iId == 6) {
                String text = getText();
                if (_hasTextualNull(text)) {
                    return 0L;
                }
                return NumberInput.parseAsLong(text, j);
            }
            switch (iId) {
                case 9:
                    return 1L;
                case 10:
                case 11:
                    return 0L;
                case 12:
                    Object embeddedObject = getEmbeddedObject();
                    if (embeddedObject instanceof Number) {
                        return ((Number) embeddedObject).longValue();
                    }
                default:
                    return j;
            }
        }
        return j;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public String getValueAsString() throws IOException {
        return getValueAsString(null);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public String getValueAsString(String str) throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_STRING) {
            return getText();
        }
        if (jsonToken == JsonToken.FIELD_NAME) {
            return getCurrentName();
        }
        return (jsonToken == null || jsonToken == JsonToken.VALUE_NULL || !jsonToken.isScalarValue()) ? str : getText();
    }

    protected void _decodeBase64(String str, ByteArrayBuilder byteArrayBuilder, Base64Variant base64Variant) throws IOException {
        try {
            base64Variant.decode(str, byteArrayBuilder);
        } catch (IllegalArgumentException e) {
            _reportError(e.getMessage());
            throw null;
        }
    }

    protected boolean _hasTextualNull(String str) {
        return "null".equals(str);
    }

    protected void reportUnexpectedNumberChar(int i, String str) throws JsonParseException {
        String str2 = String.format("Unexpected character (%s) in numeric value", _getCharDesc(i));
        if (str != null) {
            str2 = str2 + ": " + str;
        }
        _reportError(str2);
        throw null;
    }

    protected void reportInvalidNumber(String str) throws JsonParseException {
        _reportError("Invalid numeric value: " + str);
        throw null;
    }

    protected void reportOverflowInt() throws IOException {
        reportOverflowInt(getText());
        throw null;
    }

    protected void reportOverflowInt(String str) throws IOException {
        reportOverflowInt(str, currentToken());
        throw null;
    }

    protected void reportOverflowInt(String str, JsonToken jsonToken) throws IOException {
        _reportInputCoercion(String.format("Numeric value (%s) out of range of int (%d - %s)", _longIntegerDesc(str), Integer.MIN_VALUE, Integer.valueOf(IntCompanionObject.MAX_VALUE)), jsonToken, Integer.TYPE);
        throw null;
    }

    protected void reportOverflowLong() throws IOException {
        reportOverflowLong(getText());
        throw null;
    }

    protected void reportOverflowLong(String str) throws IOException {
        reportOverflowLong(str, currentToken());
        throw null;
    }

    protected void reportOverflowLong(String str, JsonToken jsonToken) throws IOException {
        _reportInputCoercion(String.format("Numeric value (%s) out of range of long (%d - %s)", _longIntegerDesc(str), Long.MIN_VALUE, Long.valueOf(LongCompanionObject.MAX_VALUE)), jsonToken, Long.TYPE);
        throw null;
    }

    protected void _reportInputCoercion(String str, JsonToken jsonToken, Class<?> cls) throws InputCoercionException {
        throw new InputCoercionException(this, str, jsonToken, cls);
    }

    protected String _longIntegerDesc(String str) {
        int length = str.length();
        if (length < 1000) {
            return str;
        }
        if (str.startsWith(MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING)) {
            length--;
        }
        return String.format("[Integer with %d digits]", Integer.valueOf(length));
    }

    protected String _longNumberDesc(String str) {
        int length = str.length();
        if (length < 1000) {
            return str;
        }
        if (str.startsWith(MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING)) {
            length--;
        }
        return String.format("[number with %d characters]", Integer.valueOf(length));
    }

    protected void _reportUnexpectedChar(int i, String str) throws JsonParseException {
        if (i < 0) {
            _reportInvalidEOF();
            throw null;
        }
        String str2 = String.format("Unexpected character (%s)", _getCharDesc(i));
        if (str != null) {
            str2 = str2 + ": " + str;
        }
        _reportError(str2);
        throw null;
    }

    protected void _reportInvalidEOF() throws JsonParseException {
        _reportInvalidEOF(" in " + this._currToken, this._currToken);
        throw null;
    }

    protected void _reportInvalidEOFInValue(JsonToken jsonToken) throws JsonParseException {
        String str;
        if (jsonToken != JsonToken.VALUE_STRING) {
            str = (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT) ? " in a Number value" : " in a value";
        } else {
            str = " in a String value";
        }
        _reportInvalidEOF(str, jsonToken);
        throw null;
    }

    protected void _reportInvalidEOF(String str, JsonToken jsonToken) throws JsonParseException {
        throw new JsonEOFException(this, jsonToken, "Unexpected end-of-input" + str);
    }

    protected void _reportMissingRootWS(int i) throws JsonParseException {
        _reportUnexpectedChar(i, "Expected space separating root-level values");
        throw null;
    }

    protected void _throwInvalidSpace(int i) throws JsonParseException {
        _reportError("Illegal character (" + _getCharDesc((char) i) + "): only regular white space (\\r, \\n, \\t) is allowed between tokens");
        throw null;
    }

    protected static final String _getCharDesc(int i) {
        char c = (char) i;
        if (Character.isISOControl(c)) {
            return "(CTRL-CHAR, code " + i + ")";
        }
        if (i > 255) {
            return "'" + c + "' (code " + i + " / 0x" + Integer.toHexString(i) + ")";
        }
        return "'" + c + "' (code " + i + ")";
    }

    protected final void _reportError(String str) throws JsonParseException {
        throw _constructError(str);
    }

    protected final void _reportError(String str, Object obj) throws JsonParseException {
        throw _constructError(String.format(str, obj));
    }

    protected final void _reportError(String str, Object obj, Object obj2) throws JsonParseException {
        throw _constructError(String.format(str, obj, obj2));
    }

    protected final void _wrapError(String str, Throwable th) throws JsonParseException {
        throw _constructError(str, th);
    }

    protected final void _throwInternal() {
        VersionUtil.throwInternal();
        throw null;
    }

    protected final JsonParseException _constructError(String str, Throwable th) {
        return new JsonParseException(this, str, th);
    }
}
