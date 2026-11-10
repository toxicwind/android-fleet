package com.fasterxml.jackson.core.filter;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class FilteringParserDelegate extends JsonParserDelegate {
    protected boolean _allowMultipleMatches;
    protected JsonToken _currToken;
    protected TokenFilterContext _exposedContext;
    protected TokenFilterContext _headContext;

    @Deprecated
    protected boolean _includeImmediateParent;
    protected boolean _includePath;
    protected TokenFilter _itemFilter;
    protected int _matchCount;

    public FilteringParserDelegate(JsonParser jsonParser, TokenFilter tokenFilter, boolean z, boolean z2) {
        super(jsonParser);
        this._itemFilter = tokenFilter;
        this._headContext = TokenFilterContext.createRootContext(tokenFilter);
        this._includePath = z;
        this._allowMultipleMatches = z2;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonToken getCurrentToken() {
        return this._currToken;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonToken currentToken() {
        return this._currToken;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public final int getCurrentTokenId() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == null) {
            return 0;
        }
        return jsonToken.id();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public final int currentTokenId() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == null) {
            return 0;
        }
        return jsonToken.id();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean hasCurrentToken() {
        return this._currToken != null;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean hasTokenId(int i) {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == null) {
            return i == 0;
        }
        return jsonToken.id() == i;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public final boolean hasToken(JsonToken jsonToken) {
        return this._currToken == jsonToken;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean isExpectedStartArrayToken() {
        return this._currToken == JsonToken.START_ARRAY;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean isExpectedStartObjectToken() {
        return this._currToken == JsonToken.START_OBJECT;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonLocation getCurrentLocation() {
        return this.delegate.getCurrentLocation();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonStreamContext getParsingContext() {
        return _filterContext();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public String getCurrentName() throws IOException {
        JsonStreamContext jsonStreamContext_filterContext = _filterContext();
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.START_OBJECT || jsonToken == JsonToken.START_ARRAY) {
            JsonStreamContext parent = jsonStreamContext_filterContext.getParent();
            if (parent == null) {
                return null;
            }
            return parent.getCurrentName();
        }
        return jsonStreamContext_filterContext.getCurrentName();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public void clearCurrentToken() {
        if (this._currToken != null) {
            this._currToken = null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:116:0x016f  */
    /* JADX WARN: Code duplicated, block: B:123:0x0194  */
    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonToken nextToken() throws IOException {
        JsonToken jsonToken_nextTokenWithBuffering;
        boolean zIsStartHandled;
        TokenFilter filter;
        JsonToken jsonToken_nextTokenWithBuffering2;
        JsonToken jsonToken_nextTokenWithBuffering3;
        JsonToken jsonToken;
        if (!this._allowMultipleMatches && (jsonToken = this._currToken) != null && this._exposedContext == null && jsonToken.isScalarValue() && !this._headContext.isStartHandled() && !this._includePath && this._itemFilter == TokenFilter.INCLUDE_ALL) {
            this._currToken = null;
            return null;
        }
        TokenFilterContext tokenFilterContextFindChildOf = this._exposedContext;
        if (tokenFilterContextFindChildOf != null) {
            while (true) {
                JsonToken jsonTokenNextTokenToRead = tokenFilterContextFindChildOf.nextTokenToRead();
                if (jsonTokenNextTokenToRead != null) {
                    this._currToken = jsonTokenNextTokenToRead;
                    return jsonTokenNextTokenToRead;
                }
                TokenFilterContext tokenFilterContext = this._headContext;
                if (tokenFilterContextFindChildOf == tokenFilterContext) {
                    this._exposedContext = null;
                    if (!tokenFilterContextFindChildOf.inArray()) {
                        break;
                    }
                    JsonToken currentToken = this.delegate.getCurrentToken();
                    this._currToken = currentToken;
                    return currentToken;
                }
                tokenFilterContextFindChildOf = tokenFilterContext.findChildOf(tokenFilterContextFindChildOf);
                this._exposedContext = tokenFilterContextFindChildOf;
                if (tokenFilterContextFindChildOf == null) {
                    throw _constructError("Unexpected problem: chain of filtered context broken");
                }
            }
        }
        JsonToken jsonTokenNextToken = this.delegate.nextToken();
        if (jsonTokenNextToken == null) {
            this._currToken = jsonTokenNextToken;
            return jsonTokenNextToken;
        }
        int iId = jsonTokenNextToken.id();
        if (iId == 1) {
            TokenFilter tokenFilter = this._itemFilter;
            if (tokenFilter == TokenFilter.INCLUDE_ALL) {
                this._headContext = this._headContext.createChildObjectContext(tokenFilter, true);
                this._currToken = jsonTokenNextToken;
                return jsonTokenNextToken;
            }
            if (tokenFilter == null) {
                this.delegate.skipChildren();
            } else {
                this._headContext.checkValue(tokenFilter);
                if (tokenFilter == null) {
                    this.delegate.skipChildren();
                } else {
                    if (tokenFilter != TokenFilter.INCLUDE_ALL) {
                        tokenFilter.filterStartObject();
                    }
                    this._itemFilter = tokenFilter;
                    if (tokenFilter == TokenFilter.INCLUDE_ALL) {
                        this._headContext = this._headContext.createChildObjectContext(tokenFilter, true);
                        this._currToken = jsonTokenNextToken;
                        return jsonTokenNextToken;
                    }
                    TokenFilterContext tokenFilterContextCreateChildObjectContext = this._headContext.createChildObjectContext(tokenFilter, false);
                    this._headContext = tokenFilterContextCreateChildObjectContext;
                    if (this._includePath && (jsonToken_nextTokenWithBuffering = _nextTokenWithBuffering(tokenFilterContextCreateChildObjectContext)) != null) {
                        this._currToken = jsonToken_nextTokenWithBuffering;
                        return jsonToken_nextTokenWithBuffering;
                    }
                }
            }
        } else if (iId == 2) {
            zIsStartHandled = this._headContext.isStartHandled();
            filter = this._headContext.getFilter();
            if (filter != null && filter != TokenFilter.INCLUDE_ALL) {
                filter.filterFinishArray();
            }
            TokenFilterContext parent = this._headContext.getParent();
            this._headContext = parent;
            this._itemFilter = parent.getFilter();
            if (zIsStartHandled) {
                this._currToken = jsonTokenNextToken;
                return jsonTokenNextToken;
            }
        } else if (iId == 3) {
            TokenFilter tokenFilter2 = this._itemFilter;
            if (tokenFilter2 == TokenFilter.INCLUDE_ALL) {
                this._headContext = this._headContext.createChildArrayContext(tokenFilter2, true);
                this._currToken = jsonTokenNextToken;
                return jsonTokenNextToken;
            }
            if (tokenFilter2 == null) {
                this.delegate.skipChildren();
            } else {
                this._headContext.checkValue(tokenFilter2);
                if (tokenFilter2 == null) {
                    this.delegate.skipChildren();
                } else {
                    if (tokenFilter2 != TokenFilter.INCLUDE_ALL) {
                        tokenFilter2.filterStartArray();
                    }
                    this._itemFilter = tokenFilter2;
                    if (tokenFilter2 == TokenFilter.INCLUDE_ALL) {
                        this._headContext = this._headContext.createChildArrayContext(tokenFilter2, true);
                        this._currToken = jsonTokenNextToken;
                        return jsonTokenNextToken;
                    }
                    TokenFilterContext tokenFilterContextCreateChildArrayContext = this._headContext.createChildArrayContext(tokenFilter2, false);
                    this._headContext = tokenFilterContextCreateChildArrayContext;
                    if (this._includePath && (jsonToken_nextTokenWithBuffering2 = _nextTokenWithBuffering(tokenFilterContextCreateChildArrayContext)) != null) {
                        this._currToken = jsonToken_nextTokenWithBuffering2;
                        return jsonToken_nextTokenWithBuffering2;
                    }
                }
            }
        } else if (iId == 4) {
            zIsStartHandled = this._headContext.isStartHandled();
            filter = this._headContext.getFilter();
            if (filter != null) {
                filter.filterFinishArray();
            }
            TokenFilterContext parent2 = this._headContext.getParent();
            this._headContext = parent2;
            this._itemFilter = parent2.getFilter();
            if (zIsStartHandled) {
                this._currToken = jsonTokenNextToken;
                return jsonTokenNextToken;
            }
        } else if (iId == 5) {
            String currentName = this.delegate.getCurrentName();
            TokenFilter fieldName = this._headContext.setFieldName(currentName);
            if (fieldName == TokenFilter.INCLUDE_ALL) {
                this._itemFilter = fieldName;
                if (!this._includePath && this._includeImmediateParent && !this._headContext.isStartHandled()) {
                    jsonTokenNextToken = this._headContext.nextTokenToRead();
                    this._exposedContext = this._headContext;
                }
                this._currToken = jsonTokenNextToken;
                return jsonTokenNextToken;
            }
            if (fieldName == null) {
                this.delegate.nextToken();
                this.delegate.skipChildren();
            } else {
                fieldName.includeProperty(currentName);
                if (fieldName == null) {
                    this.delegate.nextToken();
                    this.delegate.skipChildren();
                } else {
                    this._itemFilter = fieldName;
                    if (fieldName == TokenFilter.INCLUDE_ALL) {
                        if (_verifyAllowedMatches()) {
                            if (this._includePath) {
                                this._currToken = jsonTokenNextToken;
                                return jsonTokenNextToken;
                            }
                        } else {
                            this.delegate.nextToken();
                            this.delegate.skipChildren();
                        }
                    }
                    if (this._includePath && (jsonToken_nextTokenWithBuffering3 = _nextTokenWithBuffering(this._headContext)) != null) {
                        this._currToken = jsonToken_nextTokenWithBuffering3;
                        return jsonToken_nextTokenWithBuffering3;
                    }
                }
            }
        } else {
            TokenFilter tokenFilter3 = this._itemFilter;
            if (tokenFilter3 == TokenFilter.INCLUDE_ALL) {
                this._currToken = jsonTokenNextToken;
                return jsonTokenNextToken;
            }
            if (tokenFilter3 != null) {
                this._headContext.checkValue(tokenFilter3);
                if ((tokenFilter3 == TokenFilter.INCLUDE_ALL || (tokenFilter3 != null && tokenFilter3.includeValue(this.delegate))) && _verifyAllowedMatches()) {
                    this._currToken = jsonTokenNextToken;
                    return jsonTokenNextToken;
                }
            }
        }
        return _nextToken2();
    }

    protected final JsonToken _nextToken2() throws IOException {
        JsonToken jsonToken_nextTokenWithBuffering;
        JsonToken jsonToken_nextTokenWithBuffering2;
        JsonToken jsonToken_nextTokenWithBuffering3;
        while (true) {
            JsonToken jsonTokenNextToken = this.delegate.nextToken();
            if (jsonTokenNextToken == null) {
                this._currToken = jsonTokenNextToken;
                return jsonTokenNextToken;
            }
            int iId = jsonTokenNextToken.id();
            if (iId == 1) {
                TokenFilter tokenFilter = this._itemFilter;
                if (tokenFilter == TokenFilter.INCLUDE_ALL) {
                    this._headContext = this._headContext.createChildObjectContext(tokenFilter, true);
                    this._currToken = jsonTokenNextToken;
                    return jsonTokenNextToken;
                }
                if (tokenFilter == null) {
                    this.delegate.skipChildren();
                } else {
                    this._headContext.checkValue(tokenFilter);
                    if (tokenFilter == null) {
                        this.delegate.skipChildren();
                    } else {
                        if (tokenFilter != TokenFilter.INCLUDE_ALL) {
                            tokenFilter.filterStartObject();
                        }
                        this._itemFilter = tokenFilter;
                        if (tokenFilter == TokenFilter.INCLUDE_ALL) {
                            this._headContext = this._headContext.createChildObjectContext(tokenFilter, true);
                            this._currToken = jsonTokenNextToken;
                            return jsonTokenNextToken;
                        }
                        TokenFilterContext tokenFilterContextCreateChildObjectContext = this._headContext.createChildObjectContext(tokenFilter, false);
                        this._headContext = tokenFilterContextCreateChildObjectContext;
                        if (this._includePath && (jsonToken_nextTokenWithBuffering3 = _nextTokenWithBuffering(tokenFilterContextCreateChildObjectContext)) != null) {
                            this._currToken = jsonToken_nextTokenWithBuffering3;
                            return jsonToken_nextTokenWithBuffering3;
                        }
                    }
                }
            } else {
                if (iId != 2) {
                    if (iId == 3) {
                        TokenFilter tokenFilter2 = this._itemFilter;
                        if (tokenFilter2 == TokenFilter.INCLUDE_ALL) {
                            this._headContext = this._headContext.createChildArrayContext(tokenFilter2, true);
                            this._currToken = jsonTokenNextToken;
                            return jsonTokenNextToken;
                        }
                        if (tokenFilter2 == null) {
                            this.delegate.skipChildren();
                        } else {
                            this._headContext.checkValue(tokenFilter2);
                            if (tokenFilter2 == null) {
                                this.delegate.skipChildren();
                            } else {
                                if (tokenFilter2 != TokenFilter.INCLUDE_ALL) {
                                    tokenFilter2.filterStartArray();
                                }
                                this._itemFilter = tokenFilter2;
                                if (tokenFilter2 == TokenFilter.INCLUDE_ALL) {
                                    this._headContext = this._headContext.createChildArrayContext(tokenFilter2, true);
                                    this._currToken = jsonTokenNextToken;
                                    return jsonTokenNextToken;
                                }
                                TokenFilterContext tokenFilterContextCreateChildArrayContext = this._headContext.createChildArrayContext(tokenFilter2, false);
                                this._headContext = tokenFilterContextCreateChildArrayContext;
                                if (this._includePath && (jsonToken_nextTokenWithBuffering2 = _nextTokenWithBuffering(tokenFilterContextCreateChildArrayContext)) != null) {
                                    this._currToken = jsonToken_nextTokenWithBuffering2;
                                    return jsonToken_nextTokenWithBuffering2;
                                }
                            }
                        }
                    } else if (iId != 4) {
                        if (iId == 5) {
                            String currentName = this.delegate.getCurrentName();
                            TokenFilter fieldName = this._headContext.setFieldName(currentName);
                            if (fieldName == TokenFilter.INCLUDE_ALL) {
                                this._itemFilter = fieldName;
                                this._currToken = jsonTokenNextToken;
                                return jsonTokenNextToken;
                            }
                            if (fieldName == null) {
                                this.delegate.nextToken();
                                this.delegate.skipChildren();
                            } else {
                                fieldName.includeProperty(currentName);
                                if (fieldName == null) {
                                    this.delegate.nextToken();
                                    this.delegate.skipChildren();
                                } else {
                                    this._itemFilter = fieldName;
                                    if (fieldName == TokenFilter.INCLUDE_ALL) {
                                        if (_verifyAllowedMatches() && this._includePath) {
                                            this._currToken = jsonTokenNextToken;
                                            return jsonTokenNextToken;
                                        }
                                    } else if (this._includePath && (jsonToken_nextTokenWithBuffering = _nextTokenWithBuffering(this._headContext)) != null) {
                                        this._currToken = jsonToken_nextTokenWithBuffering;
                                        return jsonToken_nextTokenWithBuffering;
                                    }
                                }
                            }
                        } else {
                            TokenFilter tokenFilter3 = this._itemFilter;
                            if (tokenFilter3 == TokenFilter.INCLUDE_ALL) {
                                this._currToken = jsonTokenNextToken;
                                return jsonTokenNextToken;
                            }
                            if (tokenFilter3 != null) {
                                this._headContext.checkValue(tokenFilter3);
                                if (tokenFilter3 == TokenFilter.INCLUDE_ALL || (tokenFilter3 != null && tokenFilter3.includeValue(this.delegate))) {
                                    if (_verifyAllowedMatches()) {
                                        this._currToken = jsonTokenNextToken;
                                        return jsonTokenNextToken;
                                    }
                                }
                            } else {
                                continue;
                            }
                        }
                    }
                }
                boolean zIsStartHandled = this._headContext.isStartHandled();
                TokenFilter filter = this._headContext.getFilter();
                if (filter != null && filter != TokenFilter.INCLUDE_ALL) {
                    filter.filterFinishArray();
                }
                TokenFilterContext parent = this._headContext.getParent();
                this._headContext = parent;
                this._itemFilter = parent.getFilter();
                if (zIsStartHandled) {
                    this._currToken = jsonTokenNextToken;
                    return jsonTokenNextToken;
                }
            }
        }
    }

    protected final JsonToken _nextTokenWithBuffering(TokenFilterContext tokenFilterContext) throws IOException {
        while (true) {
            JsonToken jsonTokenNextToken = this.delegate.nextToken();
            if (jsonTokenNextToken == null) {
                return jsonTokenNextToken;
            }
            int iId = jsonTokenNextToken.id();
            boolean z = false;
            if (iId == 1) {
                TokenFilter tokenFilter = this._itemFilter;
                if (tokenFilter == TokenFilter.INCLUDE_ALL) {
                    this._headContext = this._headContext.createChildObjectContext(tokenFilter, true);
                    return jsonTokenNextToken;
                }
                if (tokenFilter == null) {
                    this.delegate.skipChildren();
                } else {
                    this._headContext.checkValue(tokenFilter);
                    if (tokenFilter == null) {
                        this.delegate.skipChildren();
                    } else {
                        if (tokenFilter != TokenFilter.INCLUDE_ALL) {
                            tokenFilter.filterStartObject();
                        }
                        this._itemFilter = tokenFilter;
                        if (tokenFilter == TokenFilter.INCLUDE_ALL) {
                            this._headContext = this._headContext.createChildObjectContext(tokenFilter, true);
                            return _nextBuffered(tokenFilterContext);
                        }
                        this._headContext = this._headContext.createChildObjectContext(tokenFilter, false);
                    }
                }
            } else {
                if (iId != 2) {
                    if (iId == 3) {
                        TokenFilterContext tokenFilterContext2 = this._headContext;
                        TokenFilter tokenFilter2 = this._itemFilter;
                        tokenFilterContext2.checkValue(tokenFilter2);
                        if (tokenFilter2 == null) {
                            this.delegate.skipChildren();
                        } else {
                            if (tokenFilter2 != TokenFilter.INCLUDE_ALL) {
                                tokenFilter2.filterStartArray();
                            }
                            this._itemFilter = tokenFilter2;
                            if (tokenFilter2 == TokenFilter.INCLUDE_ALL) {
                                this._headContext = this._headContext.createChildArrayContext(tokenFilter2, true);
                                return _nextBuffered(tokenFilterContext);
                            }
                            this._headContext = this._headContext.createChildArrayContext(tokenFilter2, false);
                        }
                    } else if (iId != 4) {
                        if (iId == 5) {
                            String currentName = this.delegate.getCurrentName();
                            TokenFilter fieldName = this._headContext.setFieldName(currentName);
                            if (fieldName == TokenFilter.INCLUDE_ALL) {
                                this._itemFilter = fieldName;
                                return _nextBuffered(tokenFilterContext);
                            }
                            if (fieldName == null) {
                                this.delegate.nextToken();
                                this.delegate.skipChildren();
                            } else {
                                fieldName.includeProperty(currentName);
                                if (fieldName == null) {
                                    this.delegate.nextToken();
                                    this.delegate.skipChildren();
                                } else {
                                    this._itemFilter = fieldName;
                                    if (fieldName != TokenFilter.INCLUDE_ALL) {
                                        continue;
                                    } else {
                                        if (_verifyAllowedMatches()) {
                                            return _nextBuffered(tokenFilterContext);
                                        }
                                        this._itemFilter = this._headContext.setFieldName(currentName);
                                    }
                                }
                            }
                        } else {
                            TokenFilter tokenFilter3 = this._itemFilter;
                            if (tokenFilter3 == TokenFilter.INCLUDE_ALL) {
                                return _nextBuffered(tokenFilterContext);
                            }
                            if (tokenFilter3 != null) {
                                this._headContext.checkValue(tokenFilter3);
                                if (tokenFilter3 == TokenFilter.INCLUDE_ALL || (tokenFilter3 != null && tokenFilter3.includeValue(this.delegate))) {
                                    if (_verifyAllowedMatches()) {
                                        return _nextBuffered(tokenFilterContext);
                                    }
                                }
                            } else {
                                continue;
                            }
                        }
                    }
                }
                TokenFilter filter = this._headContext.getFilter();
                if (filter != null && filter != TokenFilter.INCLUDE_ALL) {
                    filter.filterFinishArray();
                }
                if ((this._headContext == tokenFilterContext) && this._headContext.isStartHandled()) {
                    z = true;
                }
                TokenFilterContext parent = this._headContext.getParent();
                this._headContext = parent;
                this._itemFilter = parent.getFilter();
                if (z) {
                    return jsonTokenNextToken;
                }
            }
        }
    }

    private JsonToken _nextBuffered(TokenFilterContext tokenFilterContext) throws IOException {
        this._exposedContext = tokenFilterContext;
        JsonToken jsonTokenNextTokenToRead = tokenFilterContext.nextTokenToRead();
        if (jsonTokenNextTokenToRead != null) {
            return jsonTokenNextTokenToRead;
        }
        while (tokenFilterContext != this._headContext) {
            tokenFilterContext = this._exposedContext.findChildOf(tokenFilterContext);
            this._exposedContext = tokenFilterContext;
            if (tokenFilterContext == null) {
                throw _constructError("Unexpected problem: chain of filtered context broken");
            }
            JsonToken jsonTokenNextTokenToRead2 = tokenFilterContext.nextTokenToRead();
            if (jsonTokenNextTokenToRead2 != null) {
                return jsonTokenNextTokenToRead2;
            }
        }
        throw _constructError("Internal error: failed to locate expected buffered tokens");
    }

    private final boolean _verifyAllowedMatches() throws IOException {
        if (this._matchCount != 0 && !this._allowMultipleMatches) {
            return false;
        }
        this._matchCount++;
        return true;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
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
                return this;
            }
            if (jsonTokenNextToken.isStructStart()) {
                i++;
            } else if (jsonTokenNextToken.isStructEnd() && (i = i - 1) == 0) {
                return this;
            }
        }
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public String getText() throws IOException {
        return this.delegate.getText();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean hasTextCharacters() {
        return this.delegate.hasTextCharacters();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public char[] getTextCharacters() throws IOException {
        return this.delegate.getTextCharacters();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public int getTextLength() throws IOException {
        return this.delegate.getTextLength();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public int getTextOffset() throws IOException {
        return this.delegate.getTextOffset();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public BigInteger getBigIntegerValue() throws IOException {
        return this.delegate.getBigIntegerValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public byte getByteValue() throws IOException {
        return this.delegate.getByteValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public short getShortValue() throws IOException {
        return this.delegate.getShortValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public BigDecimal getDecimalValue() throws IOException {
        return this.delegate.getDecimalValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public double getDoubleValue() throws IOException {
        return this.delegate.getDoubleValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public float getFloatValue() throws IOException {
        return this.delegate.getFloatValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public int getIntValue() throws IOException {
        return this.delegate.getIntValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public long getLongValue() throws IOException {
        return this.delegate.getLongValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonParser.NumberType getNumberType() throws IOException {
        return this.delegate.getNumberType();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public Number getNumberValue() throws IOException {
        return this.delegate.getNumberValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public int getValueAsInt() throws IOException {
        return this.delegate.getValueAsInt();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public int getValueAsInt(int i) throws IOException {
        return this.delegate.getValueAsInt(i);
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public long getValueAsLong() throws IOException {
        return this.delegate.getValueAsLong();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public long getValueAsLong(long j) throws IOException {
        return this.delegate.getValueAsLong(j);
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public String getValueAsString() throws IOException {
        return this.delegate.getValueAsString();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public String getValueAsString(String str) throws IOException {
        return this.delegate.getValueAsString(str);
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public Object getEmbeddedObject() throws IOException {
        return this.delegate.getEmbeddedObject();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException {
        return this.delegate.getBinaryValue(base64Variant);
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) throws IOException {
        return this.delegate.readBinaryValue(base64Variant, outputStream);
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonLocation getTokenLocation() {
        return this.delegate.getTokenLocation();
    }

    protected JsonStreamContext _filterContext() {
        TokenFilterContext tokenFilterContext = this._exposedContext;
        return tokenFilterContext != null ? tokenFilterContext : this._headContext;
    }
}
