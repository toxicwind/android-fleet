package com.grack.nanojson;

import io.ktor.util.date.GMTDateParser;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: classes6.dex */
final class JsonTokener {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int BUFFER_ROOM = 256;
    static final int BUFFER_SIZE = 32768;
    static final int MAX_ESCAPE = 5;
    static final int TOKEN_ARRAY_END = 4;
    static final int TOKEN_ARRAY_START = 11;
    static final int TOKEN_COLON = 2;
    static final int TOKEN_COMMA = 1;
    static final int TOKEN_EOF = 0;
    static final int TOKEN_FALSE = 7;
    static final int TOKEN_NULL = 5;
    static final int TOKEN_NUMBER = 9;
    static final int TOKEN_OBJECT_END = 3;
    static final int TOKEN_OBJECT_START = 10;
    static final int TOKEN_SEMI_STRING = 12;
    static final int TOKEN_STRING = 8;
    static final int TOKEN_TRUE = 6;
    static final int TOKEN_VALUE_MIN = 5;
    private final char[] buffer;
    private int bufferLength;
    private int charOffset;
    private boolean eof;
    protected int index;
    protected boolean isDouble;
    private int linePos;
    private final Reader reader;
    protected StringBuilder reusableBuffer;
    private int rowPos;
    private int tokenCharOffset;
    private int tokenCharPos;
    private final boolean utf8;
    private int utf8adjust;
    static final char[] TRUE = {'r', AbstractJsonLexerKt.UNICODE_ESC, 'e'};
    static final char[] FALSE = {'a', 'l', GMTDateParser.SECONDS, 'e'};
    static final char[] NULL = {AbstractJsonLexerKt.UNICODE_ESC, 'l', 'l'};

    private static final class PseudoUtf8Reader extends Reader {
        private byte[] buf = new byte[32768];
        private final InputStream buffered;

        PseudoUtf8Reader(InputStream inputStream) {
            this.buffered = inputStream;
        }

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        @Override // java.io.Reader
        public int read(char[] cArr, int i, int i2) throws IOException {
            int i3 = this.buffered.read(this.buf, i, i2);
            for (int i4 = i; i4 < i + i3; i4++) {
                cArr[i4] = (char) this.buf[i4];
            }
            return i3;
        }
    }

    JsonTokener(InputStream inputStream) throws JsonParserException {
        Charset charsetForName;
        this.linePos = 1;
        this.buffer = new char[32768];
        this.reusableBuffer = new StringBuilder();
        if (!(inputStream instanceof BufferedInputStream) && !(inputStream instanceof ByteArrayInputStream)) {
            inputStream = new BufferedInputStream(inputStream);
        }
        inputStream.mark(4);
        try {
            int[] iArr = {inputStream.read(), inputStream.read(), inputStream.read(), inputStream.read()};
            int i = iArr[0];
            if (i != 239 || iArr[1] != 187 || iArr[2] != 191) {
                if (i == 0 && iArr[1] == 0 && iArr[2] == 254 && iArr[3] == 255) {
                    charsetForName = Charset.forName("UTF-32BE");
                } else if (i == 255 && iArr[1] == 254 && iArr[2] == 0 && iArr[3] == 0) {
                    charsetForName = Charset.forName("UTF-32LE");
                } else {
                    if (i == 254 && iArr[1] == 255) {
                        charsetForName = StandardCharsets.UTF_16BE;
                        inputStream.reset();
                    } else if (i == 255 && iArr[1] == 254) {
                        charsetForName = StandardCharsets.UTF_16LE;
                        inputStream.reset();
                    } else {
                        if (i == 0 && iArr[1] == 0 && iArr[2] == 0 && iArr[3] != 0) {
                            charsetForName = Charset.forName("UTF-32BE");
                        } else if (i != 0 && iArr[1] == 0 && iArr[2] == 0 && iArr[3] == 0) {
                            charsetForName = Charset.forName("UTF-32LE");
                        } else if (i == 0 && iArr[1] != 0 && iArr[2] == 0 && iArr[3] != 0) {
                            charsetForName = StandardCharsets.UTF_16BE;
                        } else if (i == 0 || iArr[1] != 0 || iArr[2] == 0 || iArr[3] != 0) {
                            inputStream.reset();
                            this.reader = new PseudoUtf8Reader(inputStream);
                        } else {
                            charsetForName = StandardCharsets.UTF_16LE;
                        }
                        inputStream.reset();
                    }
                    inputStream.read();
                    inputStream.read();
                }
                this.reader = new InputStreamReader(inputStream, charsetForName);
                this.utf8 = false;
                init();
            }
            inputStream.reset();
            inputStream.read();
            inputStream.read();
            inputStream.read();
            this.reader = new PseudoUtf8Reader(inputStream);
            this.utf8 = true;
            init();
        } catch (IOException e) {
            throw new JsonParserException(e, "IOException while detecting charset", 1, 1, 0);
        }
    }

    JsonTokener(Reader reader) throws JsonParserException {
        this.linePos = 1;
        this.buffer = new char[32768];
        this.reusableBuffer = new StringBuilder();
        this.reader = reader;
        this.utf8 = false;
        init();
    }

    private int advanceChar() throws JsonParserException {
        if (this.eof) {
            return -1;
        }
        char[] cArr = this.buffer;
        int i = this.index;
        char c = cArr[i];
        if (c == '\n') {
            this.linePos++;
            this.rowPos = i + 1 + this.charOffset;
            this.utf8adjust = 0;
        }
        int i2 = i + 1;
        this.index = i2;
        if (i2 >= this.bufferLength) {
            this.eof = refillBuffer();
        }
        return c;
    }

    /* JADX WARN: Code duplicated, block: B:53:0x01a0 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:54:0x01a1  */
    private void consumeTokenStringUtf8Char(char c) throws JsonParserException {
        int i;
        ensureBuffer(5);
        int i2 = c & 240;
        if (i2 == 128 || i2 == 144 || i2 == 160 || i2 == 176) {
            throw createParseException(null, "Illegal UTF-8 continuation byte: 0x" + Integer.toHexString(c & 255), false);
        }
        if (i2 != 192) {
            if (i2 != 208) {
                if (i2 == 224) {
                    char[] cArr = this.buffer;
                    int i3 = this.index;
                    int i4 = i3 + 1;
                    this.index = i4;
                    int i5 = ((c & 15) << 12) | ((cArr[i3] & '?') << 6);
                    this.index = i3 + 2;
                    char c2 = (char) (i5 | (cArr[i4] & '?'));
                    this.utf8adjust += 2;
                    if ((c2 >= 55296 && c2 <= 56319) || (c2 >= 56320 && c2 <= 57343)) {
                        throw createParseException(null, "Illegal UTF-8 codepoint: 0x" + Integer.toHexString(c2), false);
                    }
                    this.reusableBuffer.append(c2);
                } else if (i2 == 240) {
                    if ((c & 15) >= 5) {
                        throw createParseException(null, "Illegal UTF-8 byte: 0x" + Integer.toHexString(c & 255), false);
                    }
                    int i6 = (c & '\f') >> 2;
                    if (i6 == 0 || i6 == 1) {
                        StringBuilder sb = this.reusableBuffer;
                        char[] cArr2 = this.buffer;
                        int i7 = this.index;
                        int i8 = i7 + 1;
                        this.index = i8;
                        int i9 = ((c & 7) << 18) | ((cArr2[i7] & '?') << 12);
                        int i10 = i7 + 2;
                        this.index = i10;
                        int i11 = i9 | ((cArr2[i8] & '?') << 6);
                        this.index = i7 + 3;
                        sb.appendCodePoint(i11 | (cArr2[i10] & '?'));
                        i = this.utf8adjust + 3;
                    } else {
                        if (i6 == 2) {
                            char[] cArr3 = this.buffer;
                            int i12 = this.index;
                            int i13 = i12 + 1;
                            this.index = i13;
                            int i14 = ((c & 3) << 24) | ((cArr3[i12] & '?') << 18);
                            int i15 = i12 + 2;
                            this.index = i15;
                            int i16 = i14 | ((cArr3[i13] & '?') << 12);
                            int i17 = i12 + 3;
                            this.index = i17;
                            int i18 = i16 | ((cArr3[i15] & '?') << 6);
                            this.index = i12 + 4;
                            throw createParseException(null, "Unable to represent codepoint 0x" + Integer.toHexString(i18 | (cArr3[i17] & '?')) + " in a Java string", false);
                        }
                        if (i6 == 3) {
                            char[] cArr4 = this.buffer;
                            int i19 = this.index;
                            int i20 = i19 + 1;
                            this.index = i20;
                            int i21 = ((c & 1) << 30) | ((cArr4[i19] & '?') << 24);
                            int i22 = i19 + 2;
                            this.index = i22;
                            int i23 = i21 | ((cArr4[i20] & '?') << 18);
                            int i24 = i19 + 3;
                            this.index = i24;
                            int i25 = i23 | ((cArr4[i22] & '?') << 12);
                            int i26 = i19 + 4;
                            this.index = i26;
                            int i27 = i25 | ((cArr4[i24] & '?') << 6);
                            this.index = i19 + 5;
                            throw createParseException(null, "Unable to represent codepoint 0x" + Integer.toHexString(i27 | (cArr4[i26] & '?')) + " in a Java string", false);
                        }
                    }
                }
                if (this.index > this.bufferLength) {
                    throw createParseException(null, "UTF-8 codepoint was truncated", false);
                }
            }
            this.utf8adjust = i;
            if (this.index > this.bufferLength) {
                throw createParseException(null, "UTF-8 codepoint was truncated", false);
            }
        }
        if ((c & 14) == 0) {
            throw createParseException(null, "Illegal UTF-8 byte: 0x" + Integer.toHexString(c & 255), false);
        }
        char[] cArr5 = this.buffer;
        int i28 = this.index;
        this.index = i28 + 1;
        this.reusableBuffer.append((char) (((c & 31) << 6) | (cArr5[i28] & '?')));
        i = this.utf8adjust + 1;
        this.utf8adjust = i;
        if (this.index > this.bufferLength) {
            throw createParseException(null, "UTF-8 codepoint was truncated", false);
        }
    }

    private void consumeWhitespace() throws JsonParserException {
        int iEnsureBuffer;
        do {
            iEnsureBuffer = ensureBuffer(256);
            for (int i = 0; i < iEnsureBuffer; i++) {
                char c = this.buffer[this.index];
                if (!isWhitespace(c)) {
                    fixupAfterRawBufferRead();
                    return;
                }
                if (c == '\n') {
                    this.linePos++;
                    this.rowPos = this.index + 1 + this.charOffset;
                    this.utf8adjust = 0;
                }
                this.index++;
            }
        } while (iEnsureBuffer > 0);
        this.eof = true;
    }

    private void init() throws JsonParserException {
        this.eof = refillBuffer();
        consumeWhitespace();
    }

    private boolean isDigitCharacter(int i) {
        return (i >= 48 && i <= 57) || i == 101 || i == 69 || i == 46 || i == 43 || i == 45;
    }

    private int peekChar() {
        if (this.eof) {
            return -1;
        }
        return this.buffer[this.index];
    }

    private boolean refillBuffer() throws JsonParserException {
        try {
            Reader reader = this.reader;
            char[] cArr = this.buffer;
            int i = reader.read(cArr, 0, cArr.length);
            if (i <= 0) {
                return true;
            }
            this.charOffset += this.bufferLength;
            this.index = 0;
            this.bufferLength = i;
            return false;
        } catch (IOException e) {
            throw createParseException(e, "IOException", true);
        }
    }

    private char stringChar() throws JsonParserException {
        char[] cArr = this.buffer;
        int i = this.index;
        this.index = i + 1;
        char c = cArr[i];
        if (c < ' ') {
            throwControlCharacterException(c);
        }
        return c;
    }

    private void throwControlCharacterException(char c) throws JsonParserException {
        if (c == '\n') {
            this.linePos++;
            this.rowPos = this.index + 1 + this.charOffset;
            this.utf8adjust = 0;
        }
        throw createParseException(null, "Strings may not contain control characters: 0x" + Integer.toString(c, 16), false);
    }

    int advanceCharFast() {
        char[] cArr = this.buffer;
        int i = this.index;
        char c = cArr[i];
        if (c == '\n') {
            this.linePos++;
            this.rowPos = i + 1 + this.charOffset;
            this.utf8adjust = 0;
        }
        this.index = i + 1;
        return c;
    }

    int advanceToToken(boolean z) throws JsonParserException {
        int iAdvanceChar;
        do {
            iAdvanceChar = advanceChar();
        } while (isWhitespace(iAdvanceChar));
        int i = this.index;
        int i2 = this.charOffset;
        this.tokenCharPos = ((i + i2) - this.rowPos) - this.utf8adjust;
        this.tokenCharOffset = i2 + i;
        if (iAdvanceChar == -1) {
            return 0;
        }
        if (iAdvanceChar == 34 || iAdvanceChar == 39) {
            consumeTokenString(iAdvanceChar);
            return 8;
        }
        if (iAdvanceChar == 91) {
            return 11;
        }
        if (iAdvanceChar == 93) {
            return 4;
        }
        if (iAdvanceChar == 102) {
            try {
                consumeKeyword((char) iAdvanceChar, FALSE);
                return 7;
            } catch (JsonParserException e) {
                if (!z) {
                    throw e;
                }
            }
        } else if (iAdvanceChar == 110) {
            try {
                consumeKeyword((char) iAdvanceChar, NULL);
                return 5;
            } catch (JsonParserException e2) {
                if (!z) {
                    throw e2;
                }
            }
        } else {
            if (iAdvanceChar != 116) {
                if (iAdvanceChar == 123) {
                    return 10;
                }
                if (iAdvanceChar == 125) {
                    return 3;
                }
                switch (iAdvanceChar) {
                    case 43:
                    case 46:
                        throw createParseException(null, "Numbers may not start with '" + ((char) iAdvanceChar) + "'", true);
                    case 44:
                        return 1;
                    case 45:
                        break;
                    default:
                        switch (iAdvanceChar) {
                            case 48:
                            case 49:
                            case 50:
                            case 51:
                            case 52:
                            case 53:
                            case 54:
                            case 55:
                            case 56:
                            case 57:
                                break;
                            case 58:
                                return 2;
                            default:
                                if (z) {
                                    this.index = i - 1;
                                    consumeTokenSemiString();
                                    return 12;
                                }
                                if (isAsciiLetter(iAdvanceChar)) {
                                    throw createHelpfulException((char) iAdvanceChar, null, 0);
                                }
                                throw createParseException(null, "Unexpected character: " + ((char) iAdvanceChar), true);
                        }
                        break;
                }
                consumeTokenNumber((char) iAdvanceChar);
                return 9;
            }
            try {
                consumeKeyword((char) iAdvanceChar, TRUE);
                return 6;
            } catch (JsonParserException e3) {
                if (!z) {
                    throw e3;
                }
            }
        }
        this.index = i - 1;
        consumeTokenSemiString();
        return 12;
    }

    void consumeKeyword(char c, char[] cArr) throws JsonParserException {
        if (ensureBuffer(cArr.length) < cArr.length) {
            throw createHelpfulException(c, cArr, 0);
        }
        for (int i = 0; i < cArr.length; i++) {
            char[] cArr2 = this.buffer;
            int i2 = this.index;
            this.index = i2 + 1;
            if (cArr2[i2] != cArr[i]) {
                throw createHelpfulException(c, cArr, i);
            }
        }
        fixupAfterRawBufferRead();
        int iPeekChar = peekChar();
        if (iPeekChar != 9 && iPeekChar != 10 && iPeekChar != 13 && iPeekChar != 32 && iPeekChar != 44 && iPeekChar != 58 && iPeekChar != 91 && iPeekChar != 93 && iPeekChar != 123 && iPeekChar != 125) {
            throw createHelpfulException(c, cArr, cArr.length);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:32:0x0074  */
    /* JADX WARN: Code duplicated, block: B:59:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:64:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:65:0x00b6  */
    void consumeTokenNumber(char c) throws JsonParserException {
        Exception exc;
        byte b;
        byte b2;
        int i = 0;
        this.reusableBuffer.setLength(0);
        this.reusableBuffer.append(c);
        this.isDouble = false;
        char c2 = 3;
        byte b3 = c == '-' ? (byte) 1 : c == '0' ? (byte) 3 : (byte) 2;
        while (true) {
            int iEnsureBuffer = ensureBuffer(256);
            exc = null;
            b = 8;
            if (iEnsureBuffer != 0) {
                int i2 = i;
                while (true) {
                    if (i2 < iEnsureBuffer) {
                        char c3 = this.buffer[this.index];
                        if (isDigitCharacter(c3)) {
                            switch (b3) {
                                case 1:
                                    if (c3 == '0') {
                                        b3 = 3;
                                    } else if (c3 > '0' && c3 <= '9') {
                                        b3 = 2;
                                    } else {
                                        b3 = -1;
                                    }
                                    break;
                                case 2:
                                case 3:
                                    if (c3 >= '0' && c3 <= '9' && b3 == 2) {
                                        b3 = 2;
                                    } else if (c3 == '.') {
                                        this.isDouble = true;
                                        b2 = 4;
                                        b3 = b2;
                                    } else if (c3 == 'e' || c3 == 'E') {
                                        this.isDouble = true;
                                        b3 = 6;
                                    } else {
                                        b3 = -1;
                                    }
                                    break;
                                case 4:
                                case 5:
                                    if (c3 >= '0' && c3 <= '9') {
                                        b3 = 5;
                                    } else if ((c3 == 'e' || c3 == 'E') && b3 == 5) {
                                        this.isDouble = true;
                                        b3 = 6;
                                    } else {
                                        b3 = -1;
                                    }
                                    break;
                                case 6:
                                case 7:
                                    if (c3 == '+' || (c3 == '-' && b3 == 6)) {
                                        b2 = 7;
                                        b3 = b2;
                                    } else if (c3 >= '0' && c3 <= '9') {
                                        b3 = 8;
                                    } else {
                                        b3 = -1;
                                    }
                                    break;
                                case 8:
                                    if (c3 >= '0' && c3 <= '9') {
                                        b3 = 8;
                                    } else {
                                        b3 = -1;
                                    }
                                    break;
                                default:
                                    b3 = -1;
                                    break;
                            }
                            this.reusableBuffer.append(c3);
                            this.index++;
                            if (b3 == -1) {
                                throw createParseException(null, "Malformed number: " + ((Object) this.reusableBuffer), true);
                            }
                            i2++;
                            c2 = 3;
                            i = 0;
                            exc = null;
                            b = 8;
                        }
                    }
                }
            }
        }
        if (b3 != 2 && b3 != c2 && b3 != 5 && b3 != b) {
            throw createParseException(exc, "Malformed number: " + ((Object) this.reusableBuffer), true);
        }
        if (b3 == c2 && c == '-') {
            this.isDouble = true;
        }
        fixupAfterRawBufferRead();
    }

    void consumeTokenSemiString() throws JsonParserException {
        StringBuilder sb;
        int i;
        this.reusableBuffer.setLength(0);
        while (true) {
            int i2 = 256;
            int iEnsureBuffer = ensureBuffer(256);
            boolean z = true;
            if (iEnsureBuffer == 0) {
                throw createParseException(null, "String was not terminated before end of input", true);
            }
            for (int i3 = 0; i3 < iEnsureBuffer; i3++) {
                char cStringChar = stringChar();
                if (!isWhitespace(cStringChar)) {
                    char c = AbstractJsonLexerKt.COLON;
                    if (cStringChar != ':') {
                        char c2 = AbstractJsonLexerKt.COMMA;
                        if (cStringChar == '\\' || (this.utf8 && (cStringChar & 128) != 0)) {
                            this.reusableBuffer.append(this.buffer, (this.index - i3) - 1, i3);
                            this.index--;
                            while (true) {
                                int iEnsureBuffer2 = ensureBuffer(i2);
                                if (iEnsureBuffer2 == 0) {
                                    throw createParseException(null, "String was not terminated before end of input", z);
                                }
                                int i4 = this.index + iEnsureBuffer2;
                                while (true) {
                                    int i5 = this.index;
                                    if (i5 >= i4) {
                                        int i6 = this.bufferLength;
                                        if (i5 > i6) {
                                            this.index = i6;
                                            throw createParseException(null, "EOF encountered in the middle of a string escape", false);
                                        }
                                        i2 = 256;
                                        z = true;
                                        c = AbstractJsonLexerKt.COLON;
                                        c2 = AbstractJsonLexerKt.COMMA;
                                        break;
                                    }
                                    char cStringChar2 = stringChar();
                                    if (this.utf8 && (cStringChar2 & 128) != 0) {
                                        consumeTokenStringUtf8Char(cStringChar2);
                                        break;
                                    }
                                    if (cStringChar2 != '\t' && cStringChar2 != '\n' && cStringChar2 != '\r' && cStringChar2 != ' ') {
                                        if (cStringChar2 != c2) {
                                            if (cStringChar2 != c) {
                                                if (cStringChar2 != '{' && cStringChar2 != '}') {
                                                    switch (cStringChar2) {
                                                        case '[':
                                                        case ']':
                                                            break;
                                                        case '\\':
                                                            if (i4 - this.index < 5) {
                                                                int iEnsureBuffer3 = ensureBuffer(5);
                                                                int i7 = this.index;
                                                                int i8 = i7 + iEnsureBuffer3;
                                                                if (this.buffer[i7] == 'u' && iEnsureBuffer3 < 5) {
                                                                    this.index = this.bufferLength;
                                                                    throw createParseException(null, "EOF encountered in the middle of a string escape", false);
                                                                }
                                                                i4 = i8;
                                                            }
                                                            char[] cArr = this.buffer;
                                                            int i9 = this.index;
                                                            this.index = i9 + 1;
                                                            char c3 = cArr[i9];
                                                            if (c3 == '\"' || c3 == '/' || c3 == '\\') {
                                                                this.reusableBuffer.append(c3);
                                                            } else {
                                                                if (c3 == 'b') {
                                                                    sb = this.reusableBuffer;
                                                                    cStringChar2 = '\b';
                                                                } else if (c3 == 'f') {
                                                                    sb = this.reusableBuffer;
                                                                    cStringChar2 = '\f';
                                                                } else if (c3 == 'n') {
                                                                    sb = this.reusableBuffer;
                                                                    cStringChar2 = '\n';
                                                                } else if (c3 == 'r') {
                                                                    this.reusableBuffer.append('\r');
                                                                } else if (c3 == 't') {
                                                                    this.reusableBuffer.append('\t');
                                                                } else {
                                                                    if (c3 != 'u') {
                                                                        throw createParseException(null, "Invalid escape: \\" + c3, false);
                                                                    }
                                                                    int i10 = 0;
                                                                    for (int i11 = 0; i11 < 4; i11++) {
                                                                        int i12 = i10 << 4;
                                                                        char[] cArr2 = this.buffer;
                                                                        int i13 = this.index;
                                                                        this.index = i13 + 1;
                                                                        char c4 = cArr2[i13];
                                                                        if (c4 >= '0' && c4 <= '9') {
                                                                            i = c4 - '0';
                                                                        } else if (c4 >= 'A' && c4 <= 'F') {
                                                                            i = c4 - '7';
                                                                        } else {
                                                                            if (c4 < 'a' || c4 > 'f') {
                                                                                StringBuilder sb2 = new StringBuilder("Expected unicode hex escape character: ");
                                                                                sb2.append(c4);
                                                                                sb2.append(" (");
                                                                                sb2.append((int) c4);
                                                                                sb2.append(")");
                                                                                throw createParseException(null, sb2.toString(), false);
                                                                            }
                                                                            i = c4 - 'W';
                                                                        }
                                                                        i10 = i12 | i;
                                                                    }
                                                                    sb = this.reusableBuffer;
                                                                    cStringChar2 = (char) i10;
                                                                }
                                                                sb.append(cStringChar2);
                                                            }
                                                            i2 = 256;
                                                            z = true;
                                                            c = AbstractJsonLexerKt.COLON;
                                                            c2 = AbstractJsonLexerKt.COMMA;
                                                            break;
                                                        default:
                                                            sb = this.reusableBuffer;
                                                            sb.append(cStringChar2);
                                                            i2 = 256;
                                                            z = true;
                                                            c = AbstractJsonLexerKt.COLON;
                                                            c2 = AbstractJsonLexerKt.COMMA;
                                                            break;
                                                    }
                                                }
                                            }
                                        }
                                        throw createParseException(null, "Invalid character in semi-string: " + cStringChar2, false);
                                    }
                                    fixupAfterRawBufferRead();
                                    return;
                                }
                            }
                        }
                        if (cStringChar == '[' || cStringChar == ']' || cStringChar == '{' || cStringChar == '}' || cStringChar == ',') {
                            throw createParseException(null, "Invalid character in semi-string: " + cStringChar, false);
                        }
                    }
                }
                this.reusableBuffer.append(this.buffer, (this.index - i3) - 1, i3);
                fixupAfterRawBufferRead();
                return;
            }
            this.reusableBuffer.append(this.buffer, this.index - iEnsureBuffer, iEnsureBuffer);
        }
    }

    void consumeTokenString(int i) throws JsonParserException {
        StringBuilder sb;
        int i2;
        this.reusableBuffer.setLength(0);
        while (true) {
            int i3 = 256;
            int iEnsureBuffer = ensureBuffer(256);
            if (iEnsureBuffer == 0) {
                throw createParseException(null, "String was not terminated before end of input", true);
            }
            for (int i4 = 0; i4 < iEnsureBuffer; i4++) {
                char cStringChar = stringChar();
                if (cStringChar == i) {
                    this.reusableBuffer.append(this.buffer, (this.index - i4) - 1, i4);
                    fixupAfterRawBufferRead();
                    return;
                }
                if (cStringChar == '\\' || (this.utf8 && (cStringChar & 128) != 0)) {
                    this.reusableBuffer.append(this.buffer, (this.index - i4) - 1, i4);
                    this.index--;
                    while (true) {
                        int iEnsureBuffer2 = ensureBuffer(i3);
                        if (iEnsureBuffer2 == 0) {
                            throw createParseException(null, "String was not terminated before end of input", true);
                        }
                        int i5 = this.index + iEnsureBuffer2;
                        while (true) {
                            int i6 = this.index;
                            if (i6 >= i5) {
                                int i7 = this.bufferLength;
                                if (i6 <= i7) {
                                    i3 = 256;
                                    break;
                                } else {
                                    this.index = i7;
                                    throw createParseException(null, "EOF encountered in the middle of a string escape", false);
                                }
                            }
                            char cStringChar2 = stringChar();
                            if (this.utf8 && (cStringChar2 & 128) != 0) {
                                consumeTokenStringUtf8Char(cStringChar2);
                                break;
                            }
                            if (cStringChar2 == '\"' || cStringChar2 == '\'') {
                                if (cStringChar2 == i) {
                                    fixupAfterRawBufferRead();
                                    return;
                                }
                                sb = this.reusableBuffer;
                            } else {
                                if (cStringChar2 != '\\') {
                                    this.reusableBuffer.append(cStringChar2);
                                } else {
                                    if (i5 - this.index < 5) {
                                        int iEnsureBuffer3 = ensureBuffer(5);
                                        int i8 = this.index;
                                        int i9 = i8 + iEnsureBuffer3;
                                        if (this.buffer[i8] == 'u' && iEnsureBuffer3 < 5) {
                                            this.index = this.bufferLength;
                                            throw createParseException(null, "EOF encountered in the middle of a string escape", false);
                                        }
                                        i5 = i9;
                                    }
                                    char[] cArr = this.buffer;
                                    int i10 = this.index;
                                    this.index = i10 + 1;
                                    char c = cArr[i10];
                                    if (c == '\"' || c == '\'' || c == '/' || c == '\\') {
                                        this.reusableBuffer.append(c);
                                    } else if (c == 'b') {
                                        sb = this.reusableBuffer;
                                        cStringChar2 = '\b';
                                    } else if (c == 'f') {
                                        sb = this.reusableBuffer;
                                        cStringChar2 = '\f';
                                    } else if (c == 'n') {
                                        sb = this.reusableBuffer;
                                        cStringChar2 = '\n';
                                    } else if (c == 'r') {
                                        sb = this.reusableBuffer;
                                        cStringChar2 = '\r';
                                    } else if (c == 't') {
                                        sb = this.reusableBuffer;
                                        cStringChar2 = '\t';
                                    } else {
                                        if (c != 'u') {
                                            throw createParseException(null, "Invalid escape: \\" + c, false);
                                        }
                                        int i11 = 0;
                                        for (int i12 = 0; i12 < 4; i12++) {
                                            int i13 = i11 << 4;
                                            char[] cArr2 = this.buffer;
                                            int i14 = this.index;
                                            this.index = i14 + 1;
                                            char c2 = cArr2[i14];
                                            if (c2 >= '0' && c2 <= '9') {
                                                i2 = c2 - '0';
                                            } else if (c2 >= 'A' && c2 <= 'F') {
                                                i2 = c2 - '7';
                                            } else {
                                                if (c2 < 'a' || c2 > 'f') {
                                                    StringBuilder sb2 = new StringBuilder("Expected unicode hex escape character: ");
                                                    sb2.append(c2);
                                                    sb2.append(" (");
                                                    sb2.append((int) c2);
                                                    sb2.append(")");
                                                    throw createParseException(null, sb2.toString(), false);
                                                }
                                                i2 = c2 - 'W';
                                            }
                                            i11 = i13 | i2;
                                        }
                                        sb = this.reusableBuffer;
                                        cStringChar2 = (char) i11;
                                    }
                                }
                                i3 = 256;
                            }
                            sb.append(cStringChar2);
                            i3 = 256;
                        }
                    }
                }
            }
            this.reusableBuffer.append(this.buffer, this.index - iEnsureBuffer, iEnsureBuffer);
        }
    }

    JsonParserException createHelpfulException(char c, char[] cArr, int i) throws JsonParserException {
        StringBuilder sb = new StringBuilder();
        sb.append(c);
        String str = "";
        sb.append(cArr == null ? "" : new String(cArr, 0, i));
        StringBuilder sb2 = new StringBuilder(sb.toString());
        while (isAsciiLetter(peekChar()) && sb2.length() < 15) {
            sb2.append((char) advanceChar());
        }
        StringBuilder sb3 = new StringBuilder("Unexpected token '");
        sb3.append((Object) sb2);
        sb3.append("'");
        if (cArr != null) {
            str = ". Did you mean '" + c + new String(cArr) + "'?";
        }
        sb3.append(str);
        return createParseException(null, sb3.toString(), true);
    }

    JsonParserException createParseException(Exception exc, String str, boolean z) {
        if (z) {
            return new JsonParserException(exc, str + " on line " + this.linePos + ", char " + this.tokenCharPos, this.linePos, this.tokenCharPos, this.tokenCharOffset);
        }
        int iMax = Math.max(1, ((this.index + this.charOffset) - this.rowPos) - this.utf8adjust);
        return new JsonParserException(exc, str + " on line " + this.linePos + ", char " + iMax, this.linePos, iMax, this.index + this.charOffset);
    }

    int ensureBuffer(int i) throws JsonParserException {
        int i2;
        int i3 = this.bufferLength;
        int i4 = i3 - i;
        int i5 = this.index;
        if (i4 >= i5) {
            return i;
        }
        if (i5 > 0) {
            this.charOffset += i5;
            int i6 = i3 - i5;
            this.bufferLength = i6;
            char[] cArr = this.buffer;
            System.arraycopy(cArr, i5, cArr, 0, i6);
            this.index = 0;
        }
        do {
            try {
                char[] cArr2 = this.buffer;
                int length = cArr2.length;
                int i7 = this.bufferLength;
                if (length <= i7) {
                    throw new IOException("Unexpected internal error");
                }
                int i8 = this.reader.read(cArr2, i7, cArr2.length - i7);
                int i9 = this.bufferLength;
                if (i8 <= 0) {
                    return i9 - this.index;
                }
                i2 = i9 + i8;
                this.bufferLength = i2;
            } catch (IOException e) {
                throw createParseException(e, "IOException", true);
            }
        } while (i2 <= i);
        return i2 - this.index;
    }

    void fixupAfterRawBufferRead() throws JsonParserException {
        if (this.index >= this.bufferLength) {
            this.eof = refillBuffer();
        }
    }

    boolean isAsciiLetter(int i) {
        if (i < 65 || i > 90) {
            return i >= 97 && i <= 122;
        }
        return true;
    }

    boolean isWhitespace(int i) {
        return i == 32 || i == 10 || i == 13 || i == 9;
    }

    int tokenChar() throws JsonParserException {
        int iAdvanceChar;
        do {
            iAdvanceChar = advanceChar();
        } while (isWhitespace(iAdvanceChar));
        return iAdvanceChar;
    }
}
