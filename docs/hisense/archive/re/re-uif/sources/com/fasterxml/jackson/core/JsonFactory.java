package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.InputDecorator;
import com.fasterxml.jackson.core.io.OutputDecorator;
import com.fasterxml.jackson.core.io.UTF8Writer;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.fasterxml.jackson.core.json.ReaderBasedJsonParser;
import com.fasterxml.jackson.core.json.UTF8JsonGenerator;
import com.fasterxml.jackson.core.json.WriterBasedJsonGenerator;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.BufferRecyclers;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.io.Writer;

/* JADX INFO: loaded from: classes.dex */
public class JsonFactory extends TokenStreamFactory implements Serializable {
    private static final long serialVersionUID = 2;
    protected final transient ByteQuadsCanonicalizer _byteSymbolCanonicalizer;
    protected CharacterEscapes _characterEscapes;
    protected int _factoryFeatures;
    protected int _generatorFeatures;
    protected InputDecorator _inputDecorator;
    protected int _maximumNonEscapedChar;
    protected ObjectCodec _objectCodec;
    protected OutputDecorator _outputDecorator;
    protected int _parserFeatures;
    protected final char _quoteChar;
    protected final transient CharsToNameCanonicalizer _rootCharSymbols;
    protected SerializableString _rootValueSeparator;
    protected static final int DEFAULT_FACTORY_FEATURE_FLAGS = Feature.collectDefaults();
    protected static final int DEFAULT_PARSER_FEATURE_FLAGS = JsonParser.Feature.collectDefaults();
    protected static final int DEFAULT_GENERATOR_FEATURE_FLAGS = JsonGenerator.Feature.collectDefaults();
    public static final SerializableString DEFAULT_ROOT_VALUE_SEPARATOR = DefaultPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;

    public boolean canUseCharArrays() {
        return true;
    }

    public boolean requiresPropertyOrdering() {
        return false;
    }

    public enum Feature {
        INTERN_FIELD_NAMES(true),
        CANONICALIZE_FIELD_NAMES(true),
        FAIL_ON_SYMBOL_HASH_OVERFLOW(true),
        USE_THREAD_LOCAL_FOR_BUFFER_RECYCLING(true);

        private final boolean _defaultState;

        public static int collectDefaults() {
            int mask = 0;
            for (Feature feature : values()) {
                if (feature.enabledByDefault()) {
                    mask |= feature.getMask();
                }
            }
            return mask;
        }

        Feature(boolean z) {
            this._defaultState = z;
        }

        public boolean enabledByDefault() {
            return this._defaultState;
        }

        public boolean enabledIn(int i) {
            return (getMask() & i) != 0;
        }

        public int getMask() {
            return 1 << ordinal();
        }
    }

    public JsonFactory() {
        this(null);
    }

    public JsonFactory(ObjectCodec objectCodec) {
        this._rootCharSymbols = CharsToNameCanonicalizer.createRoot();
        this._byteSymbolCanonicalizer = ByteQuadsCanonicalizer.createRoot();
        this._factoryFeatures = DEFAULT_FACTORY_FEATURE_FLAGS;
        this._parserFeatures = DEFAULT_PARSER_FEATURE_FLAGS;
        this._generatorFeatures = DEFAULT_GENERATOR_FEATURE_FLAGS;
        this._rootValueSeparator = DEFAULT_ROOT_VALUE_SEPARATOR;
        this._objectCodec = objectCodec;
        this._quoteChar = '\"';
    }

    protected JsonFactory(JsonFactory jsonFactory, ObjectCodec objectCodec) {
        this._rootCharSymbols = CharsToNameCanonicalizer.createRoot();
        this._byteSymbolCanonicalizer = ByteQuadsCanonicalizer.createRoot();
        this._factoryFeatures = DEFAULT_FACTORY_FEATURE_FLAGS;
        this._parserFeatures = DEFAULT_PARSER_FEATURE_FLAGS;
        this._generatorFeatures = DEFAULT_GENERATOR_FEATURE_FLAGS;
        this._rootValueSeparator = DEFAULT_ROOT_VALUE_SEPARATOR;
        this._objectCodec = objectCodec;
        this._factoryFeatures = jsonFactory._factoryFeatures;
        this._parserFeatures = jsonFactory._parserFeatures;
        this._generatorFeatures = jsonFactory._generatorFeatures;
        this._inputDecorator = jsonFactory._inputDecorator;
        this._outputDecorator = jsonFactory._outputDecorator;
        this._characterEscapes = jsonFactory._characterEscapes;
        this._rootValueSeparator = jsonFactory._rootValueSeparator;
        this._maximumNonEscapedChar = jsonFactory._maximumNonEscapedChar;
        this._quoteChar = jsonFactory._quoteChar;
    }

    protected Object readResolve() {
        return new JsonFactory(this, this._objectCodec);
    }

    public JsonFactory setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
        return this;
    }

    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    public JsonParser createParser(Reader reader) throws IOException {
        IOContext iOContext_createContext = _createContext(reader, false);
        return _createParser(_decorate(reader, iOContext_createContext), iOContext_createContext);
    }

    public JsonParser createParser(byte[] bArr) throws IOException {
        InputStream inputStreamDecorate;
        IOContext iOContext_createContext = _createContext(bArr, true);
        InputDecorator inputDecorator = this._inputDecorator;
        if (inputDecorator != null && (inputStreamDecorate = inputDecorator.decorate(iOContext_createContext, bArr, 0, bArr.length)) != null) {
            return _createParser(inputStreamDecorate, iOContext_createContext);
        }
        return _createParser(bArr, 0, bArr.length, iOContext_createContext);
    }

    public JsonParser createParser(String str) throws IOException {
        int length = str.length();
        if (this._inputDecorator != null || length > 32768 || !canUseCharArrays()) {
            return createParser(new StringReader(str));
        }
        IOContext iOContext_createContext = _createContext(str, true);
        char[] cArrAllocTokenBuffer = iOContext_createContext.allocTokenBuffer(length);
        str.getChars(0, length, cArrAllocTokenBuffer, 0);
        return _createParser(cArrAllocTokenBuffer, 0, length, iOContext_createContext, true);
    }

    public JsonGenerator createGenerator(OutputStream outputStream, JsonEncoding jsonEncoding) throws IOException {
        IOContext iOContext_createContext = _createContext(outputStream, false);
        iOContext_createContext.setEncoding(jsonEncoding);
        if (jsonEncoding == JsonEncoding.UTF8) {
            return _createUTF8Generator(_decorate(outputStream, iOContext_createContext), iOContext_createContext);
        }
        return _createGenerator(_decorate(_createWriter(outputStream, jsonEncoding, iOContext_createContext), iOContext_createContext), iOContext_createContext);
    }

    public JsonGenerator createGenerator(Writer writer) throws IOException {
        IOContext iOContext_createContext = _createContext(writer, false);
        return _createGenerator(_decorate(writer, iOContext_createContext), iOContext_createContext);
    }

    protected JsonParser _createParser(InputStream inputStream, IOContext iOContext) throws IOException {
        return new ByteSourceJsonBootstrapper(iOContext, inputStream).constructParser(this._parserFeatures, this._objectCodec, this._byteSymbolCanonicalizer, this._rootCharSymbols, this._factoryFeatures);
    }

    protected JsonParser _createParser(Reader reader, IOContext iOContext) throws IOException {
        return new ReaderBasedJsonParser(iOContext, this._parserFeatures, reader, this._objectCodec, this._rootCharSymbols.makeChild(this._factoryFeatures));
    }

    protected JsonParser _createParser(char[] cArr, int i, int i2, IOContext iOContext, boolean z) throws IOException {
        return new ReaderBasedJsonParser(iOContext, this._parserFeatures, null, this._objectCodec, this._rootCharSymbols.makeChild(this._factoryFeatures), cArr, i, i + i2, z);
    }

    protected JsonParser _createParser(byte[] bArr, int i, int i2, IOContext iOContext) throws IOException {
        return new ByteSourceJsonBootstrapper(iOContext, bArr, i, i2).constructParser(this._parserFeatures, this._objectCodec, this._byteSymbolCanonicalizer, this._rootCharSymbols, this._factoryFeatures);
    }

    protected JsonGenerator _createGenerator(Writer writer, IOContext iOContext) throws IOException {
        WriterBasedJsonGenerator writerBasedJsonGenerator = new WriterBasedJsonGenerator(iOContext, this._generatorFeatures, this._objectCodec, writer, this._quoteChar);
        int i = this._maximumNonEscapedChar;
        if (i > 0) {
            writerBasedJsonGenerator.setHighestNonEscapedChar(i);
        }
        CharacterEscapes characterEscapes = this._characterEscapes;
        if (characterEscapes != null) {
            writerBasedJsonGenerator.setCharacterEscapes(characterEscapes);
        }
        SerializableString serializableString = this._rootValueSeparator;
        if (serializableString != DEFAULT_ROOT_VALUE_SEPARATOR) {
            writerBasedJsonGenerator.setRootValueSeparator(serializableString);
        }
        return writerBasedJsonGenerator;
    }

    protected JsonGenerator _createUTF8Generator(OutputStream outputStream, IOContext iOContext) throws IOException {
        UTF8JsonGenerator uTF8JsonGenerator = new UTF8JsonGenerator(iOContext, this._generatorFeatures, this._objectCodec, outputStream, this._quoteChar);
        int i = this._maximumNonEscapedChar;
        if (i > 0) {
            uTF8JsonGenerator.setHighestNonEscapedChar(i);
        }
        CharacterEscapes characterEscapes = this._characterEscapes;
        if (characterEscapes != null) {
            uTF8JsonGenerator.setCharacterEscapes(characterEscapes);
        }
        SerializableString serializableString = this._rootValueSeparator;
        if (serializableString != DEFAULT_ROOT_VALUE_SEPARATOR) {
            uTF8JsonGenerator.setRootValueSeparator(serializableString);
        }
        return uTF8JsonGenerator;
    }

    protected Writer _createWriter(OutputStream outputStream, JsonEncoding jsonEncoding, IOContext iOContext) throws IOException {
        if (jsonEncoding == JsonEncoding.UTF8) {
            return new UTF8Writer(iOContext, outputStream);
        }
        return new OutputStreamWriter(outputStream, jsonEncoding.getJavaName());
    }

    protected final Reader _decorate(Reader reader, IOContext iOContext) throws IOException {
        Reader readerDecorate;
        InputDecorator inputDecorator = this._inputDecorator;
        return (inputDecorator == null || (readerDecorate = inputDecorator.decorate(iOContext, reader)) == null) ? reader : readerDecorate;
    }

    protected final OutputStream _decorate(OutputStream outputStream, IOContext iOContext) throws IOException {
        OutputStream outputStreamDecorate;
        OutputDecorator outputDecorator = this._outputDecorator;
        return (outputDecorator == null || (outputStreamDecorate = outputDecorator.decorate(iOContext, outputStream)) == null) ? outputStream : outputStreamDecorate;
    }

    protected final Writer _decorate(Writer writer, IOContext iOContext) throws IOException {
        Writer writerDecorate;
        OutputDecorator outputDecorator = this._outputDecorator;
        return (outputDecorator == null || (writerDecorate = outputDecorator.decorate(iOContext, writer)) == null) ? writer : writerDecorate;
    }

    public BufferRecycler _getBufferRecycler() {
        if (Feature.USE_THREAD_LOCAL_FOR_BUFFER_RECYCLING.enabledIn(this._factoryFeatures)) {
            return BufferRecyclers.getBufferRecycler();
        }
        return new BufferRecycler();
    }

    protected IOContext _createContext(Object obj, boolean z) {
        return new IOContext(_getBufferRecycler(), obj, z);
    }
}
