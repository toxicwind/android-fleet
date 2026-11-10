package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.NumberOutput;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class UTF8JsonGenerator extends JsonGeneratorImpl {
    protected boolean _bufferRecyclable;
    protected char[] _charBuffer;
    protected final int _charBufferLength;
    protected byte[] _outputBuffer;
    protected final int _outputEnd;
    protected final int _outputMaxContiguous;
    protected final OutputStream _outputStream;
    protected int _outputTail;
    protected byte _quoteChar;
    private static final byte[] HEX_CHARS = CharTypes.copyHexBytes();
    private static final byte[] NULL_BYTES = {110, 117, 108, 108};
    private static final byte[] TRUE_BYTES = {116, 114, 117, 101};
    private static final byte[] FALSE_BYTES = {102, 97, 108, 115, 101};

    public UTF8JsonGenerator(IOContext iOContext, int i, ObjectCodec objectCodec, OutputStream outputStream, char c) {
        super(iOContext, i, objectCodec);
        this._outputStream = outputStream;
        this._quoteChar = (byte) c;
        if (c != '\"') {
            this._outputEscapes = CharTypes.get7BitOutputEscapes(c);
        }
        this._bufferRecyclable = true;
        byte[] bArrAllocWriteEncodingBuffer = iOContext.allocWriteEncodingBuffer();
        this._outputBuffer = bArrAllocWriteEncodingBuffer;
        int length = bArrAllocWriteEncodingBuffer.length;
        this._outputEnd = length;
        this._outputMaxContiguous = length >> 3;
        char[] cArrAllocConcatBuffer = iOContext.allocConcatBuffer();
        this._charBuffer = cArrAllocConcatBuffer;
        this._charBufferLength = cArrAllocConcatBuffer.length;
        if (isEnabled(JsonGenerator.Feature.ESCAPE_NON_ASCII)) {
            setHighestNonEscapedChar(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RATING_UNBLOCKED);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeFieldName(String str) throws IOException {
        if (this._cfgPrettyPrinter != null) {
            _writePPFieldName(str);
            return;
        }
        int iWriteFieldName = this._writeContext.writeFieldName(str);
        if (iWriteFieldName == 4) {
            _reportError("Can not write a field name, expecting a value");
            throw null;
        }
        if (iWriteFieldName == 1) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int i = this._outputTail;
            this._outputTail = i + 1;
            bArr[i] = 44;
        }
        if (this._cfgUnqNames) {
            _writeStringSegments(str, false);
            return;
        }
        int length = str.length();
        if (length > this._charBufferLength) {
            _writeStringSegments(str, true);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        int i3 = i2 + 1;
        this._outputTail = i3;
        bArr2[i2] = this._quoteChar;
        if (length <= this._outputMaxContiguous) {
            if (i3 + length > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(str, 0, length);
        } else {
            _writeStringSegments(str, 0, length);
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr3 = this._outputBuffer;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        bArr3[i4] = this._quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeFieldName(SerializableString serializableString) throws IOException {
        if (this._cfgPrettyPrinter != null) {
            _writePPFieldName(serializableString);
            return;
        }
        int iWriteFieldName = this._writeContext.writeFieldName(serializableString.getValue());
        if (iWriteFieldName == 4) {
            _reportError("Can not write a field name, expecting a value");
            throw null;
        }
        if (iWriteFieldName == 1) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int i = this._outputTail;
            this._outputTail = i + 1;
            bArr[i] = 44;
        }
        if (this._cfgUnqNames) {
            _writeUnq(serializableString);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        int i3 = i2 + 1;
        this._outputTail = i3;
        bArr2[i2] = this._quoteChar;
        int iAppendQuotedUTF8 = serializableString.appendQuotedUTF8(bArr2, i3);
        if (iAppendQuotedUTF8 < 0) {
            _writeBytes(serializableString.asQuotedUTF8());
        } else {
            this._outputTail += iAppendQuotedUTF8;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr3 = this._outputBuffer;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        bArr3[i4] = this._quoteChar;
    }

    private final void _writeUnq(SerializableString serializableString) throws IOException {
        int iAppendQuotedUTF8 = serializableString.appendQuotedUTF8(this._outputBuffer, this._outputTail);
        if (iAppendQuotedUTF8 < 0) {
            _writeBytes(serializableString.asQuotedUTF8());
        } else {
            this._outputTail += iAppendQuotedUTF8;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeStartArray() throws IOException {
        _verifyValueWrite("start an array");
        this._writeContext = this._writeContext.createChildArrayContext();
        PrettyPrinter prettyPrinter = this._cfgPrettyPrinter;
        if (prettyPrinter != null) {
            prettyPrinter.writeStartArray(this);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = 91;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeStartArray(int i) throws IOException {
        _verifyValueWrite("start an array");
        this._writeContext = this._writeContext.createChildArrayContext();
        PrettyPrinter prettyPrinter = this._cfgPrettyPrinter;
        if (prettyPrinter != null) {
            prettyPrinter.writeStartArray(this);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr[i2] = 91;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeEndArray() throws IOException {
        if (!this._writeContext.inArray()) {
            _reportError("Current context not Array but " + this._writeContext.typeDesc());
            throw null;
        }
        PrettyPrinter prettyPrinter = this._cfgPrettyPrinter;
        if (prettyPrinter != null) {
            prettyPrinter.writeEndArray(this, this._writeContext.getEntryCount());
        } else {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int i = this._outputTail;
            this._outputTail = i + 1;
            bArr[i] = 93;
        }
        this._writeContext = this._writeContext.clearAndGetParent();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeStartObject() throws IOException {
        _verifyValueWrite("start an object");
        this._writeContext = this._writeContext.createChildObjectContext();
        PrettyPrinter prettyPrinter = this._cfgPrettyPrinter;
        if (prettyPrinter != null) {
            prettyPrinter.writeStartObject(this);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = 123;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeStartObject(Object obj) throws IOException {
        _verifyValueWrite("start an object");
        this._writeContext = this._writeContext.createChildObjectContext(obj);
        PrettyPrinter prettyPrinter = this._cfgPrettyPrinter;
        if (prettyPrinter != null) {
            prettyPrinter.writeStartObject(this);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = 123;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeEndObject() throws IOException {
        if (!this._writeContext.inObject()) {
            _reportError("Current context not Object but " + this._writeContext.typeDesc());
            throw null;
        }
        PrettyPrinter prettyPrinter = this._cfgPrettyPrinter;
        if (prettyPrinter != null) {
            prettyPrinter.writeEndObject(this, this._writeContext.getEntryCount());
        } else {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int i = this._outputTail;
            this._outputTail = i + 1;
            bArr[i] = 125;
        }
        this._writeContext = this._writeContext.clearAndGetParent();
    }

    protected final void _writePPFieldName(String str) throws IOException {
        int iWriteFieldName = this._writeContext.writeFieldName(str);
        if (iWriteFieldName == 4) {
            _reportError("Can not write a field name, expecting a value");
            throw null;
        }
        if (iWriteFieldName == 1) {
            this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
        } else {
            this._cfgPrettyPrinter.beforeObjectEntries(this);
        }
        if (this._cfgUnqNames) {
            _writeStringSegments(str, false);
            return;
        }
        int length = str.length();
        if (length > this._charBufferLength) {
            _writeStringSegments(str, true);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = this._quoteChar;
        str.getChars(0, length, this._charBuffer, 0);
        if (length <= this._outputMaxContiguous) {
            if (this._outputTail + length > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(this._charBuffer, 0, length);
        } else {
            _writeStringSegments(this._charBuffer, 0, length);
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr2[i2] = this._quoteChar;
    }

    protected final void _writePPFieldName(SerializableString serializableString) throws IOException {
        int iWriteFieldName = this._writeContext.writeFieldName(serializableString.getValue());
        if (iWriteFieldName == 4) {
            _reportError("Can not write a field name, expecting a value");
            throw null;
        }
        if (iWriteFieldName == 1) {
            this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
        } else {
            this._cfgPrettyPrinter.beforeObjectEntries(this);
        }
        boolean z = !this._cfgUnqNames;
        if (z) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int i = this._outputTail;
            this._outputTail = i + 1;
            bArr[i] = this._quoteChar;
        }
        int iAppendQuotedUTF8 = serializableString.appendQuotedUTF8(this._outputBuffer, this._outputTail);
        if (iAppendQuotedUTF8 < 0) {
            _writeBytes(serializableString.asQuotedUTF8());
        } else {
            this._outputTail += iAppendQuotedUTF8;
        }
        if (z) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr2 = this._outputBuffer;
            int i2 = this._outputTail;
            this._outputTail = i2 + 1;
            bArr2[i2] = this._quoteChar;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeString(String str) throws IOException {
        _verifyValueWrite("write a string");
        if (str == null) {
            _writeNull();
            return;
        }
        int length = str.length();
        if (length > this._outputMaxContiguous) {
            _writeStringSegments(str, true);
            return;
        }
        if (this._outputTail + length >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = this._quoteChar;
        _writeStringSegment(str, 0, length);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr2[i2] = this._quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeString(char[] cArr, int i, int i2) throws IOException {
        _verifyValueWrite("write a string");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i3 = this._outputTail;
        int i4 = i3 + 1;
        this._outputTail = i4;
        bArr[i3] = this._quoteChar;
        if (i2 <= this._outputMaxContiguous) {
            if (i4 + i2 > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(cArr, i, i2);
        } else {
            _writeStringSegments(cArr, i, i2);
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i5 = this._outputTail;
        this._outputTail = i5 + 1;
        bArr2[i5] = this._quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeString(SerializableString serializableString) throws IOException {
        _verifyValueWrite("write a string");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        int i2 = i + 1;
        this._outputTail = i2;
        bArr[i] = this._quoteChar;
        int iAppendQuotedUTF8 = serializableString.appendQuotedUTF8(bArr, i2);
        if (iAppendQuotedUTF8 < 0) {
            _writeBytes(serializableString.asQuotedUTF8());
        } else {
            this._outputTail += iAppendQuotedUTF8;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr2[i3] = this._quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(String str) throws IOException {
        int length = str.length();
        char[] cArr = this._charBuffer;
        if (length <= cArr.length) {
            str.getChars(0, length, cArr, 0);
            writeRaw(cArr, 0, length);
        } else {
            writeRaw(str, 0, length);
        }
    }

    public void writeRaw(String str, int i, int i2) throws IOException {
        char c;
        char[] cArr = this._charBuffer;
        int length = cArr.length;
        if (i2 <= length) {
            str.getChars(i, i + i2, cArr, 0);
            writeRaw(cArr, 0, i2);
            return;
        }
        int i3 = this._outputEnd;
        int iMin = Math.min(length, (i3 >> 2) + (i3 >> 4));
        int i4 = iMin * 3;
        while (i2 > 0) {
            int iMin2 = Math.min(iMin, i2);
            str.getChars(i, i + iMin2, cArr, 0);
            if (this._outputTail + i4 > this._outputEnd) {
                _flushBuffer();
            }
            if (iMin2 > 1 && (c = cArr[iMin2 - 1]) >= 55296 && c <= 56319) {
                iMin2--;
            }
            _writeRawSegment(cArr, 0, iMin2);
            i += iMin2;
            i2 -= iMin2;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(SerializableString serializableString) throws IOException {
        int iAppendUnquotedUTF8 = serializableString.appendUnquotedUTF8(this._outputBuffer, this._outputTail);
        if (iAppendUnquotedUTF8 < 0) {
            _writeBytes(serializableString.asUnquotedUTF8());
        } else {
            this._outputTail += iAppendUnquotedUTF8;
        }
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public void writeRawValue(SerializableString serializableString) throws IOException {
        _verifyValueWrite("write a raw (unencoded) value");
        int iAppendUnquotedUTF8 = serializableString.appendUnquotedUTF8(this._outputBuffer, this._outputTail);
        if (iAppendUnquotedUTF8 < 0) {
            _writeBytes(serializableString.asUnquotedUTF8());
        } else {
            this._outputTail += iAppendUnquotedUTF8;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeRaw(char[] cArr, int i, int i2) throws IOException {
        int i3 = i2 + i2 + i2;
        int i4 = this._outputTail + i3;
        int i5 = this._outputEnd;
        if (i4 > i5) {
            if (i5 < i3) {
                _writeSegmentedRaw(cArr, i, i2);
                return;
            }
            _flushBuffer();
        }
        int i6 = i2 + i;
        while (i < i6) {
            do {
                char c = cArr[i];
                if (c <= 127) {
                    byte[] bArr = this._outputBuffer;
                    int i7 = this._outputTail;
                    this._outputTail = i7 + 1;
                    bArr[i7] = (byte) c;
                    i++;
                } else {
                    int i8 = i + 1;
                    char c2 = cArr[i];
                    if (c2 < 2048) {
                        byte[] bArr2 = this._outputBuffer;
                        int i9 = this._outputTail;
                        int i10 = i9 + 1;
                        this._outputTail = i10;
                        bArr2[i9] = (byte) ((c2 >> 6) | MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CONTENT_EXHAUSTED);
                        this._outputTail = i10 + 1;
                        bArr2[i10] = (byte) ((c2 & '?') | 128);
                        i = i8;
                    } else {
                        i = _outputRawMultiByteChar(c2, cArr, i8, i6);
                    }
                }
            } while (i < i6);
            return;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(char c) throws IOException {
        if (this._outputTail + 3 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        if (c <= 127) {
            int i = this._outputTail;
            this._outputTail = i + 1;
            bArr[i] = (byte) c;
        } else {
            if (c < 2048) {
                int i2 = this._outputTail;
                int i3 = i2 + 1;
                this._outputTail = i3;
                bArr[i2] = (byte) ((c >> 6) | MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CONTENT_EXHAUSTED);
                this._outputTail = i3 + 1;
                bArr[i3] = (byte) ((c & '?') | 128);
                return;
            }
            _outputRawMultiByteChar(c, null, 0, 0);
        }
    }

    private final void _writeSegmentedRaw(char[] cArr, int i, int i2) throws IOException {
        int i3 = this._outputEnd;
        byte[] bArr = this._outputBuffer;
        int i4 = i2 + i;
        while (i < i4) {
            do {
                char c = cArr[i];
                if (c < 128) {
                    if (this._outputTail >= i3) {
                        _flushBuffer();
                    }
                    int i5 = this._outputTail;
                    this._outputTail = i5 + 1;
                    bArr[i5] = (byte) c;
                    i++;
                } else {
                    if (this._outputTail + 3 >= this._outputEnd) {
                        _flushBuffer();
                    }
                    int i6 = i + 1;
                    char c2 = cArr[i];
                    if (c2 < 2048) {
                        int i7 = this._outputTail;
                        int i8 = i7 + 1;
                        this._outputTail = i8;
                        bArr[i7] = (byte) ((c2 >> 6) | MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CONTENT_EXHAUSTED);
                        this._outputTail = i8 + 1;
                        bArr[i8] = (byte) ((c2 & '?') | 128);
                        i = i6;
                    } else {
                        i = _outputRawMultiByteChar(c2, cArr, i6, i4);
                    }
                }
            } while (i < i4);
            return;
        }
    }

    private void _writeRawSegment(char[] cArr, int i, int i2) throws IOException {
        while (i < i2) {
            do {
                char c = cArr[i];
                if (c <= 127) {
                    byte[] bArr = this._outputBuffer;
                    int i3 = this._outputTail;
                    this._outputTail = i3 + 1;
                    bArr[i3] = (byte) c;
                    i++;
                } else {
                    int i4 = i + 1;
                    char c2 = cArr[i];
                    if (c2 < 2048) {
                        byte[] bArr2 = this._outputBuffer;
                        int i5 = this._outputTail;
                        int i6 = i5 + 1;
                        this._outputTail = i6;
                        bArr2[i5] = (byte) ((c2 >> 6) | MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CONTENT_EXHAUSTED);
                        this._outputTail = i6 + 1;
                        bArr2[i6] = (byte) ((c2 & '?') | 128);
                        i = i4;
                    } else {
                        i = _outputRawMultiByteChar(c2, cArr, i4, i2);
                    }
                }
            } while (i < i2);
            return;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeBinary(Base64Variant base64Variant, byte[] bArr, int i, int i2) throws IOException {
        _verifyValueWrite("write a binary value");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr2[i3] = this._quoteChar;
        _writeBinary(base64Variant, bArr, i, i2 + i);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr3 = this._outputBuffer;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        bArr3[i4] = this._quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public int writeBinary(Base64Variant base64Variant, InputStream inputStream, int i) throws IOException {
        _verifyValueWrite("write a binary value");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr[i2] = this._quoteChar;
        byte[] bArrAllocBase64Buffer = this._ioContext.allocBase64Buffer();
        try {
            if (i < 0) {
                i = _writeBinary(base64Variant, inputStream, bArrAllocBase64Buffer);
            } else {
                int i_writeBinary = _writeBinary(base64Variant, inputStream, bArrAllocBase64Buffer, i);
                if (i_writeBinary > 0) {
                    _reportError("Too few bytes available: missing " + i_writeBinary + " bytes (out of " + i + ")");
                    throw null;
                }
            }
            this._ioContext.releaseBase64Buffer(bArrAllocBase64Buffer);
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr2 = this._outputBuffer;
            int i3 = this._outputTail;
            this._outputTail = i3 + 1;
            bArr2[i3] = this._quoteChar;
            return i;
        } catch (Throwable th) {
            this._ioContext.releaseBase64Buffer(bArrAllocBase64Buffer);
            throw th;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(short s) throws IOException {
        _verifyValueWrite("write a number");
        if (this._outputTail + 6 >= this._outputEnd) {
            _flushBuffer();
        }
        if (this._cfgNumbersAsStrings) {
            _writeQuotedShort(s);
        } else {
            this._outputTail = NumberOutput.outputInt(s, this._outputBuffer, this._outputTail);
        }
    }

    private final void _writeQuotedShort(short s) throws IOException {
        if (this._outputTail + 8 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        int i2 = i + 1;
        this._outputTail = i2;
        bArr[i] = this._quoteChar;
        int iOutputInt = NumberOutput.outputInt(s, bArr, i2);
        this._outputTail = iOutputInt;
        byte[] bArr2 = this._outputBuffer;
        this._outputTail = iOutputInt + 1;
        bArr2[iOutputInt] = this._quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(int i) throws IOException {
        _verifyValueWrite("write a number");
        if (this._outputTail + 11 >= this._outputEnd) {
            _flushBuffer();
        }
        if (this._cfgNumbersAsStrings) {
            _writeQuotedInt(i);
        } else {
            this._outputTail = NumberOutput.outputInt(i, this._outputBuffer, this._outputTail);
        }
    }

    private final void _writeQuotedInt(int i) throws IOException {
        if (this._outputTail + 13 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i2 = this._outputTail;
        int i3 = i2 + 1;
        this._outputTail = i3;
        bArr[i2] = this._quoteChar;
        int iOutputInt = NumberOutput.outputInt(i, bArr, i3);
        this._outputTail = iOutputInt;
        byte[] bArr2 = this._outputBuffer;
        this._outputTail = iOutputInt + 1;
        bArr2[iOutputInt] = this._quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(long j) throws IOException {
        _verifyValueWrite("write a number");
        if (this._cfgNumbersAsStrings) {
            _writeQuotedLong(j);
            return;
        }
        if (this._outputTail + 21 >= this._outputEnd) {
            _flushBuffer();
        }
        this._outputTail = NumberOutput.outputLong(j, this._outputBuffer, this._outputTail);
    }

    private final void _writeQuotedLong(long j) throws IOException {
        if (this._outputTail + 23 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        int i2 = i + 1;
        this._outputTail = i2;
        bArr[i] = this._quoteChar;
        int iOutputLong = NumberOutput.outputLong(j, bArr, i2);
        this._outputTail = iOutputLong;
        byte[] bArr2 = this._outputBuffer;
        this._outputTail = iOutputLong + 1;
        bArr2[iOutputLong] = this._quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(BigInteger bigInteger) throws IOException {
        _verifyValueWrite("write a number");
        if (bigInteger == null) {
            _writeNull();
        } else if (this._cfgNumbersAsStrings) {
            _writeQuotedRaw(bigInteger.toString());
        } else {
            writeRaw(bigInteger.toString());
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(double d) throws IOException {
        if (this._cfgNumbersAsStrings || (NumberOutput.notFinite(d) && JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS.enabledIn(this._features))) {
            writeString(String.valueOf(d));
        } else {
            _verifyValueWrite("write a number");
            writeRaw(String.valueOf(d));
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(float f) throws IOException {
        if (this._cfgNumbersAsStrings || (NumberOutput.notFinite(f) && JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS.enabledIn(this._features))) {
            writeString(String.valueOf(f));
        } else {
            _verifyValueWrite("write a number");
            writeRaw(String.valueOf(f));
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(BigDecimal bigDecimal) throws IOException {
        _verifyValueWrite("write a number");
        if (bigDecimal == null) {
            _writeNull();
        } else if (this._cfgNumbersAsStrings) {
            _writeQuotedRaw(_asString(bigDecimal));
        } else {
            writeRaw(_asString(bigDecimal));
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(String str) throws IOException {
        _verifyValueWrite("write a number");
        if (this._cfgNumbersAsStrings) {
            _writeQuotedRaw(str);
        } else {
            writeRaw(str);
        }
    }

    private final void _writeQuotedRaw(String str) throws IOException {
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = this._quoteChar;
        writeRaw(str);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr2[i2] = this._quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeBoolean(boolean z) throws IOException {
        _verifyValueWrite("write a boolean value");
        if (this._outputTail + 5 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = z ? TRUE_BYTES : FALSE_BYTES;
        int length = bArr.length;
        System.arraycopy(bArr, 0, this._outputBuffer, this._outputTail, length);
        this._outputTail += length;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNull() throws IOException {
        _verifyValueWrite("write a null");
        _writeNull();
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase
    protected final void _verifyValueWrite(String str) throws IOException {
        byte b;
        int iWriteValue = this._writeContext.writeValue();
        if (this._cfgPrettyPrinter != null) {
            _verifyPrettyValueWrite(str, iWriteValue);
            return;
        }
        if (iWriteValue == 1) {
            b = 44;
        } else {
            if (iWriteValue != 2) {
                if (iWriteValue != 3) {
                    if (iWriteValue != 5) {
                        return;
                    }
                    _reportCantWriteValueExpectName(str);
                    throw null;
                }
                SerializableString serializableString = this._rootValueSeparator;
                if (serializableString != null) {
                    byte[] bArrAsUnquotedUTF8 = serializableString.asUnquotedUTF8();
                    if (bArrAsUnquotedUTF8.length > 0) {
                        _writeBytes(bArrAsUnquotedUTF8);
                        return;
                    }
                    return;
                }
                return;
            }
            b = 58;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = b;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator, java.io.Flushable
    public void flush() throws IOException {
        _flushBuffer();
        if (this._outputStream == null || !isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
            return;
        }
        this._outputStream.flush();
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
        if (this._outputBuffer != null && isEnabled(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT)) {
            while (true) {
                JsonStreamContext outputContext = getOutputContext();
                if (outputContext.inArray()) {
                    writeEndArray();
                } else if (!outputContext.inObject()) {
                    break;
                } else {
                    writeEndObject();
                }
            }
        }
        _flushBuffer();
        this._outputTail = 0;
        if (this._outputStream != null) {
            if (this._ioContext.isResourceManaged() || isEnabled(JsonGenerator.Feature.AUTO_CLOSE_TARGET)) {
                this._outputStream.close();
            } else if (isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
                this._outputStream.flush();
            }
        }
        _releaseBuffers();
    }

    protected void _releaseBuffers() {
        byte[] bArr = this._outputBuffer;
        if (bArr != null && this._bufferRecyclable) {
            this._outputBuffer = null;
            this._ioContext.releaseWriteEncodingBuffer(bArr);
        }
        char[] cArr = this._charBuffer;
        if (cArr != null) {
            this._charBuffer = null;
            this._ioContext.releaseConcatBuffer(cArr);
        }
    }

    private final void _writeBytes(byte[] bArr) throws IOException {
        int length = bArr.length;
        if (this._outputTail + length > this._outputEnd) {
            _flushBuffer();
            if (length > 512) {
                this._outputStream.write(bArr, 0, length);
                return;
            }
        }
        System.arraycopy(bArr, 0, this._outputBuffer, this._outputTail, length);
        this._outputTail += length;
    }

    private final void _writeStringSegments(String str, boolean z) throws IOException {
        if (z) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int i = this._outputTail;
            this._outputTail = i + 1;
            bArr[i] = this._quoteChar;
        }
        int length = str.length();
        int i2 = 0;
        while (length > 0) {
            int iMin = Math.min(this._outputMaxContiguous, length);
            if (this._outputTail + iMin > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(str, i2, iMin);
            i2 += iMin;
            length -= iMin;
        }
        if (z) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr2 = this._outputBuffer;
            int i3 = this._outputTail;
            this._outputTail = i3 + 1;
            bArr2[i3] = this._quoteChar;
        }
    }

    private final void _writeStringSegments(char[] cArr, int i, int i2) throws IOException {
        do {
            int iMin = Math.min(this._outputMaxContiguous, i2);
            if (this._outputTail + iMin > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(cArr, i, iMin);
            i += iMin;
            i2 -= iMin;
        } while (i2 > 0);
    }

    private final void _writeStringSegments(String str, int i, int i2) throws IOException {
        do {
            int iMin = Math.min(this._outputMaxContiguous, i2);
            if (this._outputTail + iMin > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(str, i, iMin);
            i += iMin;
            i2 -= iMin;
        } while (i2 > 0);
    }

    private final void _writeStringSegment(char[] cArr, int i, int i2) throws IOException {
        int i3 = i2 + i;
        int i4 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] iArr = this._outputEscapes;
        while (i < i3) {
            char c = cArr[i];
            if (c > 127 || iArr[c] != 0) {
                break;
            }
            bArr[i4] = (byte) c;
            i++;
            i4++;
        }
        this._outputTail = i4;
        if (i < i3) {
            if (this._characterEscapes != null) {
                _writeCustomStringSegment2(cArr, i, i3);
            } else if (this._maximumNonEscapedChar == 0) {
                _writeStringSegment2(cArr, i, i3);
            } else {
                _writeStringSegmentASCII2(cArr, i, i3);
            }
        }
    }

    private final void _writeStringSegment(String str, int i, int i2) throws IOException {
        int i3 = i2 + i;
        int i4 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] iArr = this._outputEscapes;
        while (i < i3) {
            char cCharAt = str.charAt(i);
            if (cCharAt > 127 || iArr[cCharAt] != 0) {
                break;
            }
            bArr[i4] = (byte) cCharAt;
            i++;
            i4++;
        }
        this._outputTail = i4;
        if (i < i3) {
            if (this._characterEscapes != null) {
                _writeCustomStringSegment2(str, i, i3);
            } else if (this._maximumNonEscapedChar == 0) {
                _writeStringSegment2(str, i, i3);
            } else {
                _writeStringSegmentASCII2(str, i, i3);
            }
        }
    }

    private final void _writeStringSegment2(char[] cArr, int i, int i2) throws IOException {
        if (this._outputTail + ((i2 - i) * 6) > this._outputEnd) {
            _flushBuffer();
        }
        int i_outputMultiByteChar = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] iArr = this._outputEscapes;
        while (i < i2) {
            i++;
            char c = cArr[i];
            if (c <= 127) {
                if (iArr[c] == 0) {
                    bArr[i_outputMultiByteChar] = (byte) c;
                    i_outputMultiByteChar++;
                } else {
                    int i3 = iArr[c];
                    if (i3 > 0) {
                        int i4 = i_outputMultiByteChar + 1;
                        bArr[i_outputMultiByteChar] = 92;
                        i_outputMultiByteChar = i4 + 1;
                        bArr[i4] = (byte) i3;
                    } else {
                        i_outputMultiByteChar = _writeGenericEscape(c, i_outputMultiByteChar);
                    }
                }
            } else if (c <= 2047) {
                int i5 = i_outputMultiByteChar + 1;
                bArr[i_outputMultiByteChar] = (byte) ((c >> 6) | MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CONTENT_EXHAUSTED);
                i_outputMultiByteChar = i5 + 1;
                bArr[i5] = (byte) ((c & '?') | 128);
            } else {
                i_outputMultiByteChar = _outputMultiByteChar(c, i_outputMultiByteChar);
            }
        }
        this._outputTail = i_outputMultiByteChar;
    }

    private final void _writeStringSegment2(String str, int i, int i2) throws IOException {
        if (this._outputTail + ((i2 - i) * 6) > this._outputEnd) {
            _flushBuffer();
        }
        int i_outputMultiByteChar = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] iArr = this._outputEscapes;
        while (i < i2) {
            i++;
            char cCharAt = str.charAt(i);
            if (cCharAt <= 127) {
                if (iArr[cCharAt] == 0) {
                    bArr[i_outputMultiByteChar] = (byte) cCharAt;
                    i_outputMultiByteChar++;
                } else {
                    int i3 = iArr[cCharAt];
                    if (i3 > 0) {
                        int i4 = i_outputMultiByteChar + 1;
                        bArr[i_outputMultiByteChar] = 92;
                        i_outputMultiByteChar = i4 + 1;
                        bArr[i4] = (byte) i3;
                    } else {
                        i_outputMultiByteChar = _writeGenericEscape(cCharAt, i_outputMultiByteChar);
                    }
                }
            } else if (cCharAt <= 2047) {
                int i5 = i_outputMultiByteChar + 1;
                bArr[i_outputMultiByteChar] = (byte) ((cCharAt >> 6) | MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CONTENT_EXHAUSTED);
                i_outputMultiByteChar = i5 + 1;
                bArr[i5] = (byte) ((cCharAt & '?') | 128);
            } else {
                i_outputMultiByteChar = _outputMultiByteChar(cCharAt, i_outputMultiByteChar);
            }
        }
        this._outputTail = i_outputMultiByteChar;
    }

    private final void _writeStringSegmentASCII2(char[] cArr, int i, int i2) throws IOException {
        if (this._outputTail + ((i2 - i) * 6) > this._outputEnd) {
            _flushBuffer();
        }
        int i_outputMultiByteChar = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] iArr = this._outputEscapes;
        int i3 = this._maximumNonEscapedChar;
        while (i < i2) {
            i++;
            char c = cArr[i];
            if (c <= 127) {
                if (iArr[c] == 0) {
                    bArr[i_outputMultiByteChar] = (byte) c;
                    i_outputMultiByteChar++;
                } else {
                    int i4 = iArr[c];
                    if (i4 > 0) {
                        int i5 = i_outputMultiByteChar + 1;
                        bArr[i_outputMultiByteChar] = 92;
                        i_outputMultiByteChar = i5 + 1;
                        bArr[i5] = (byte) i4;
                    } else {
                        i_outputMultiByteChar = _writeGenericEscape(c, i_outputMultiByteChar);
                    }
                }
            } else if (c > i3) {
                i_outputMultiByteChar = _writeGenericEscape(c, i_outputMultiByteChar);
            } else if (c <= 2047) {
                int i6 = i_outputMultiByteChar + 1;
                bArr[i_outputMultiByteChar] = (byte) ((c >> 6) | MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CONTENT_EXHAUSTED);
                i_outputMultiByteChar = i6 + 1;
                bArr[i6] = (byte) ((c & '?') | 128);
            } else {
                i_outputMultiByteChar = _outputMultiByteChar(c, i_outputMultiByteChar);
            }
        }
        this._outputTail = i_outputMultiByteChar;
    }

    private final void _writeStringSegmentASCII2(String str, int i, int i2) throws IOException {
        if (this._outputTail + ((i2 - i) * 6) > this._outputEnd) {
            _flushBuffer();
        }
        int i_outputMultiByteChar = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] iArr = this._outputEscapes;
        int i3 = this._maximumNonEscapedChar;
        while (i < i2) {
            i++;
            char cCharAt = str.charAt(i);
            if (cCharAt <= 127) {
                if (iArr[cCharAt] == 0) {
                    bArr[i_outputMultiByteChar] = (byte) cCharAt;
                    i_outputMultiByteChar++;
                } else {
                    int i4 = iArr[cCharAt];
                    if (i4 > 0) {
                        int i5 = i_outputMultiByteChar + 1;
                        bArr[i_outputMultiByteChar] = 92;
                        i_outputMultiByteChar = i5 + 1;
                        bArr[i5] = (byte) i4;
                    } else {
                        i_outputMultiByteChar = _writeGenericEscape(cCharAt, i_outputMultiByteChar);
                    }
                }
            } else if (cCharAt > i3) {
                i_outputMultiByteChar = _writeGenericEscape(cCharAt, i_outputMultiByteChar);
            } else if (cCharAt <= 2047) {
                int i6 = i_outputMultiByteChar + 1;
                bArr[i_outputMultiByteChar] = (byte) ((cCharAt >> 6) | MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CONTENT_EXHAUSTED);
                i_outputMultiByteChar = i6 + 1;
                bArr[i6] = (byte) ((cCharAt & '?') | 128);
            } else {
                i_outputMultiByteChar = _outputMultiByteChar(cCharAt, i_outputMultiByteChar);
            }
        }
        this._outputTail = i_outputMultiByteChar;
    }

    private final void _writeCustomStringSegment2(char[] cArr, int i, int i2) throws IOException {
        if (this._outputTail + ((i2 - i) * 6) > this._outputEnd) {
            _flushBuffer();
        }
        int i_outputMultiByteChar = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] iArr = this._outputEscapes;
        int i3 = this._maximumNonEscapedChar;
        if (i3 <= 0) {
            i3 = 65535;
        }
        CharacterEscapes characterEscapes = this._characterEscapes;
        while (i < i2) {
            i++;
            char c = cArr[i];
            if (c <= 127) {
                if (iArr[c] == 0) {
                    bArr[i_outputMultiByteChar] = (byte) c;
                    i_outputMultiByteChar++;
                } else {
                    int i4 = iArr[c];
                    if (i4 > 0) {
                        int i5 = i_outputMultiByteChar + 1;
                        bArr[i_outputMultiByteChar] = 92;
                        i_outputMultiByteChar = i5 + 1;
                        bArr[i5] = (byte) i4;
                    } else if (i4 == -2) {
                        SerializableString escapeSequence = characterEscapes.getEscapeSequence(c);
                        if (escapeSequence == null) {
                            _reportError("Invalid custom escape definitions; custom escape not found for character code 0x" + Integer.toHexString(c) + ", although was supposed to have one");
                            throw null;
                        }
                        i_outputMultiByteChar = _writeCustomEscape(bArr, i_outputMultiByteChar, escapeSequence, i2 - i);
                    } else {
                        i_outputMultiByteChar = _writeGenericEscape(c, i_outputMultiByteChar);
                    }
                }
            } else if (c > i3) {
                i_outputMultiByteChar = _writeGenericEscape(c, i_outputMultiByteChar);
            } else {
                SerializableString escapeSequence2 = characterEscapes.getEscapeSequence(c);
                if (escapeSequence2 != null) {
                    i_outputMultiByteChar = _writeCustomEscape(bArr, i_outputMultiByteChar, escapeSequence2, i2 - i);
                } else if (c <= 2047) {
                    int i6 = i_outputMultiByteChar + 1;
                    bArr[i_outputMultiByteChar] = (byte) ((c >> 6) | MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CONTENT_EXHAUSTED);
                    i_outputMultiByteChar = i6 + 1;
                    bArr[i6] = (byte) ((c & '?') | 128);
                } else {
                    i_outputMultiByteChar = _outputMultiByteChar(c, i_outputMultiByteChar);
                }
            }
        }
        this._outputTail = i_outputMultiByteChar;
    }

    private final void _writeCustomStringSegment2(String str, int i, int i2) throws IOException {
        if (this._outputTail + ((i2 - i) * 6) > this._outputEnd) {
            _flushBuffer();
        }
        int i_outputMultiByteChar = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] iArr = this._outputEscapes;
        int i3 = this._maximumNonEscapedChar;
        if (i3 <= 0) {
            i3 = 65535;
        }
        CharacterEscapes characterEscapes = this._characterEscapes;
        while (i < i2) {
            i++;
            char cCharAt = str.charAt(i);
            if (cCharAt <= 127) {
                if (iArr[cCharAt] == 0) {
                    bArr[i_outputMultiByteChar] = (byte) cCharAt;
                    i_outputMultiByteChar++;
                } else {
                    int i4 = iArr[cCharAt];
                    if (i4 > 0) {
                        int i5 = i_outputMultiByteChar + 1;
                        bArr[i_outputMultiByteChar] = 92;
                        i_outputMultiByteChar = i5 + 1;
                        bArr[i5] = (byte) i4;
                    } else if (i4 == -2) {
                        SerializableString escapeSequence = characterEscapes.getEscapeSequence(cCharAt);
                        if (escapeSequence == null) {
                            _reportError("Invalid custom escape definitions; custom escape not found for character code 0x" + Integer.toHexString(cCharAt) + ", although was supposed to have one");
                            throw null;
                        }
                        i_outputMultiByteChar = _writeCustomEscape(bArr, i_outputMultiByteChar, escapeSequence, i2 - i);
                    } else {
                        i_outputMultiByteChar = _writeGenericEscape(cCharAt, i_outputMultiByteChar);
                    }
                }
            } else if (cCharAt > i3) {
                i_outputMultiByteChar = _writeGenericEscape(cCharAt, i_outputMultiByteChar);
            } else {
                SerializableString escapeSequence2 = characterEscapes.getEscapeSequence(cCharAt);
                if (escapeSequence2 != null) {
                    i_outputMultiByteChar = _writeCustomEscape(bArr, i_outputMultiByteChar, escapeSequence2, i2 - i);
                } else if (cCharAt <= 2047) {
                    int i6 = i_outputMultiByteChar + 1;
                    bArr[i_outputMultiByteChar] = (byte) ((cCharAt >> 6) | MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CONTENT_EXHAUSTED);
                    i_outputMultiByteChar = i6 + 1;
                    bArr[i6] = (byte) ((cCharAt & '?') | 128);
                } else {
                    i_outputMultiByteChar = _outputMultiByteChar(cCharAt, i_outputMultiByteChar);
                }
            }
        }
        this._outputTail = i_outputMultiByteChar;
    }

    private final int _writeCustomEscape(byte[] bArr, int i, SerializableString serializableString, int i2) throws IOException {
        byte[] bArrAsUnquotedUTF8 = serializableString.asUnquotedUTF8();
        int length = bArrAsUnquotedUTF8.length;
        if (length > 6) {
            return _handleLongCustomEscape(bArr, i, this._outputEnd, bArrAsUnquotedUTF8, i2);
        }
        System.arraycopy(bArrAsUnquotedUTF8, 0, bArr, i, length);
        return i + length;
    }

    private final int _handleLongCustomEscape(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws IOException {
        int length = bArr2.length;
        if (i + length > i2) {
            this._outputTail = i;
            _flushBuffer();
            i = this._outputTail;
            if (length > bArr.length) {
                this._outputStream.write(bArr2, 0, length);
                return i;
            }
        }
        System.arraycopy(bArr2, 0, bArr, i, length);
        int i4 = i + length;
        if ((i3 * 6) + i4 <= i2) {
            return i4;
        }
        this._outputTail = i4;
        _flushBuffer();
        return this._outputTail;
    }

    protected final void _writeBinary(Base64Variant base64Variant, byte[] bArr, int i, int i2) throws IOException {
        int i3 = i2 - 3;
        int i4 = this._outputEnd - 6;
        int maxLineLength = base64Variant.getMaxLineLength() >> 2;
        while (i <= i3) {
            if (this._outputTail > i4) {
                _flushBuffer();
            }
            int i5 = i + 1;
            int i6 = i5 + 1;
            int i7 = ((bArr[i] << 8) | (bArr[i5] & 255)) << 8;
            int i8 = i6 + 1;
            int iEncodeBase64Chunk = base64Variant.encodeBase64Chunk(i7 | (bArr[i6] & 255), this._outputBuffer, this._outputTail);
            this._outputTail = iEncodeBase64Chunk;
            maxLineLength--;
            if (maxLineLength <= 0) {
                byte[] bArr2 = this._outputBuffer;
                int i9 = iEncodeBase64Chunk + 1;
                this._outputTail = i9;
                bArr2[iEncodeBase64Chunk] = 92;
                this._outputTail = i9 + 1;
                bArr2[i9] = 110;
                maxLineLength = base64Variant.getMaxLineLength() >> 2;
            }
            i = i8;
        }
        int i10 = i2 - i;
        if (i10 > 0) {
            if (this._outputTail > i4) {
                _flushBuffer();
            }
            int i11 = i + 1;
            int i12 = bArr[i] << 16;
            if (i10 == 2) {
                i12 |= (bArr[i11] & 255) << 8;
            }
            this._outputTail = base64Variant.encodeBase64Partial(i12, i10, this._outputBuffer, this._outputTail);
        }
    }

    protected final int _writeBinary(Base64Variant base64Variant, InputStream inputStream, byte[] bArr, int i) throws IOException {
        int i_readMore;
        int i2 = this._outputEnd - 6;
        int i3 = 2;
        int maxLineLength = base64Variant.getMaxLineLength() >> 2;
        int i4 = -3;
        int i5 = 0;
        int i_readMore2 = 0;
        while (i > 2) {
            if (i5 > i4) {
                i_readMore2 = _readMore(inputStream, bArr, i5, i_readMore2, i);
                if (i_readMore2 < 3) {
                    i5 = 0;
                    break;
                }
                i4 = i_readMore2 - 3;
                i5 = 0;
            }
            if (this._outputTail > i2) {
                _flushBuffer();
            }
            int i6 = i5 + 1;
            int i7 = bArr[i5] << 8;
            int i8 = i6 + 1;
            i5 = i8 + 1;
            i -= 3;
            int iEncodeBase64Chunk = base64Variant.encodeBase64Chunk((((bArr[i6] & 255) | i7) << 8) | (bArr[i8] & 255), this._outputBuffer, this._outputTail);
            this._outputTail = iEncodeBase64Chunk;
            maxLineLength--;
            if (maxLineLength <= 0) {
                byte[] bArr2 = this._outputBuffer;
                int i9 = iEncodeBase64Chunk + 1;
                this._outputTail = i9;
                bArr2[iEncodeBase64Chunk] = 92;
                this._outputTail = i9 + 1;
                bArr2[i9] = 110;
                maxLineLength = base64Variant.getMaxLineLength() >> 2;
            }
        }
        if (i <= 0 || (i_readMore = _readMore(inputStream, bArr, i5, i_readMore2, i)) <= 0) {
            return i;
        }
        if (this._outputTail > i2) {
            _flushBuffer();
        }
        int i10 = bArr[0] << 16;
        if (1 < i_readMore) {
            i10 |= (bArr[1] & 255) << 8;
        } else {
            i3 = 1;
        }
        this._outputTail = base64Variant.encodeBase64Partial(i10, i3, this._outputBuffer, this._outputTail);
        return i - i3;
    }

    protected final int _writeBinary(Base64Variant base64Variant, InputStream inputStream, byte[] bArr) throws IOException {
        int i = this._outputEnd - 6;
        int i2 = 2;
        int maxLineLength = base64Variant.getMaxLineLength() >> 2;
        int i3 = -3;
        int i4 = 0;
        int i_readMore = 0;
        int i5 = 0;
        while (true) {
            if (i4 > i3) {
                i_readMore = _readMore(inputStream, bArr, i4, i_readMore, bArr.length);
                if (i_readMore < 3) {
                    break;
                }
                i3 = i_readMore - 3;
                i4 = 0;
            }
            if (this._outputTail > i) {
                _flushBuffer();
            }
            int i6 = i4 + 1;
            int i7 = bArr[i4] << 8;
            int i8 = i6 + 1;
            i4 = i8 + 1;
            i5 += 3;
            int iEncodeBase64Chunk = base64Variant.encodeBase64Chunk((((bArr[i6] & 255) | i7) << 8) | (bArr[i8] & 255), this._outputBuffer, this._outputTail);
            this._outputTail = iEncodeBase64Chunk;
            maxLineLength--;
            if (maxLineLength <= 0) {
                byte[] bArr2 = this._outputBuffer;
                int i9 = iEncodeBase64Chunk + 1;
                this._outputTail = i9;
                bArr2[iEncodeBase64Chunk] = 92;
                this._outputTail = i9 + 1;
                bArr2[i9] = 110;
                maxLineLength = base64Variant.getMaxLineLength() >> 2;
            }
        }
        if (i_readMore <= 0) {
            return i5;
        }
        if (this._outputTail > i) {
            _flushBuffer();
        }
        int i10 = bArr[0] << 16;
        if (1 < i_readMore) {
            i10 |= (bArr[1] & 255) << 8;
        } else {
            i2 = 1;
        }
        int i11 = i5 + i2;
        this._outputTail = base64Variant.encodeBase64Partial(i10, i2, this._outputBuffer, this._outputTail);
        return i11;
    }

    private final int _readMore(InputStream inputStream, byte[] bArr, int i, int i2, int i3) throws IOException {
        int i4 = 0;
        while (i < i2) {
            bArr[i4] = bArr[i];
            i4++;
            i++;
        }
        int iMin = Math.min(i3, bArr.length);
        do {
            int i5 = iMin - i4;
            if (i5 == 0) {
                break;
            }
            int i6 = inputStream.read(bArr, i4, i5);
            if (i6 < 0) {
                return i4;
            }
            i4 += i6;
        } while (i4 < 3);
        return i4;
    }

    private final int _outputRawMultiByteChar(int i, char[] cArr, int i2, int i3) throws IOException {
        if (i >= 55296 && i <= 57343) {
            if (i2 >= i3 || cArr == null) {
                _reportError(String.format("Split surrogate on writeRaw() input (last character): first character 0x%4x", Integer.valueOf(i)));
                throw null;
            }
            _outputSurrogates(i, cArr[i2]);
            return i2 + 1;
        }
        byte[] bArr = this._outputBuffer;
        int i4 = this._outputTail;
        int i5 = i4 + 1;
        this._outputTail = i5;
        bArr[i4] = (byte) ((i >> 12) | MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_TYPE_MARLIN);
        int i6 = i5 + 1;
        this._outputTail = i6;
        bArr[i5] = (byte) (((i >> 6) & 63) | 128);
        this._outputTail = i6 + 1;
        bArr[i6] = (byte) ((i & 63) | 128);
        return i2;
    }

    protected final void _outputSurrogates(int i, int i2) throws IOException {
        int i_decodeSurrogate = _decodeSurrogate(i, i2);
        if (this._outputTail + 4 > this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i3 = this._outputTail;
        int i4 = i3 + 1;
        this._outputTail = i4;
        bArr[i3] = (byte) ((i_decodeSurrogate >> 18) | 240);
        int i5 = i4 + 1;
        this._outputTail = i5;
        bArr[i4] = (byte) (((i_decodeSurrogate >> 12) & 63) | 128);
        int i6 = i5 + 1;
        this._outputTail = i6;
        bArr[i5] = (byte) (((i_decodeSurrogate >> 6) & 63) | 128);
        this._outputTail = i6 + 1;
        bArr[i6] = (byte) ((i_decodeSurrogate & 63) | 128);
    }

    private final int _outputMultiByteChar(int i, int i2) throws IOException {
        byte[] bArr = this._outputBuffer;
        if (i >= 55296 && i <= 57343) {
            int i3 = i2 + 1;
            bArr[i2] = 92;
            int i4 = i3 + 1;
            bArr[i3] = 117;
            int i5 = i4 + 1;
            byte[] bArr2 = HEX_CHARS;
            bArr[i4] = bArr2[(i >> 12) & 15];
            int i6 = i5 + 1;
            bArr[i5] = bArr2[(i >> 8) & 15];
            int i7 = i6 + 1;
            bArr[i6] = bArr2[(i >> 4) & 15];
            int i8 = i7 + 1;
            bArr[i7] = bArr2[i & 15];
            return i8;
        }
        int i9 = i2 + 1;
        bArr[i2] = (byte) ((i >> 12) | MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_TYPE_MARLIN);
        int i10 = i9 + 1;
        bArr[i9] = (byte) (((i >> 6) & 63) | 128);
        int i11 = i10 + 1;
        bArr[i10] = (byte) ((i & 63) | 128);
        return i11;
    }

    private final void _writeNull() throws IOException {
        if (this._outputTail + 4 >= this._outputEnd) {
            _flushBuffer();
        }
        System.arraycopy(NULL_BYTES, 0, this._outputBuffer, this._outputTail, 4);
        this._outputTail += 4;
    }

    private int _writeGenericEscape(int i, int i2) throws IOException {
        int i3;
        byte[] bArr = this._outputBuffer;
        int i4 = i2 + 1;
        bArr[i2] = 92;
        int i5 = i4 + 1;
        bArr[i4] = 117;
        if (i > 255) {
            int i6 = 255 & (i >> 8);
            int i7 = i5 + 1;
            byte[] bArr2 = HEX_CHARS;
            bArr[i5] = bArr2[i6 >> 4];
            i3 = i7 + 1;
            bArr[i7] = bArr2[i6 & 15];
            i &= 255;
        } else {
            int i8 = i5 + 1;
            bArr[i5] = 48;
            i3 = i8 + 1;
            bArr[i8] = 48;
        }
        int i9 = i3 + 1;
        byte[] bArr3 = HEX_CHARS;
        bArr[i3] = bArr3[i >> 4];
        int i10 = i9 + 1;
        bArr[i9] = bArr3[i & 15];
        return i10;
    }

    protected final void _flushBuffer() throws IOException {
        int i = this._outputTail;
        if (i > 0) {
            this._outputTail = 0;
            this._outputStream.write(this._outputBuffer, 0, i);
        }
    }
}
