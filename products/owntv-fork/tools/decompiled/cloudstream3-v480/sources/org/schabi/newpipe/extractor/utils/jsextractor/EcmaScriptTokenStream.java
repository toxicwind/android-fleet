package org.schabi.newpipe.extractor.utils.jsextractor;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.common.base.Ascii;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.ScriptRuntime;
import org.schabi.newpipe.extractor.exceptions.ParsingException;

/* JADX INFO: loaded from: classes8.dex */
class EcmaScriptTokenStream {
    private static final char BYTE_ORDER_MARK = 65279;
    private static final int EOF_CHAR = -1;
    private static final char NUMERIC_SEPARATOR = '_';
    private static final int REPORT_NUMBER_FORMAT_ERROR = -2;
    private boolean dirtyLine;
    int lineno;
    private final String sourceString;
    private final boolean strictMode;
    private int stringBufferTop;
    int tokenBeg;
    int tokenEnd;
    private int ungetCursor;
    private char[] stringBuffer = new char[128];
    private final int[] ungetBuffer = new int[3];
    private int lineEndChar = -1;
    private int sourceCursor = 0;
    private int cursor = 0;

    private static boolean isAlpha(int i) {
        if (i <= 90) {
            return 65 <= i;
        }
        return 97 <= i && i <= 122;
    }

    private static boolean isDigit(int i) {
        return 48 <= i && i <= 57;
    }

    private static boolean isDualDigit(int i) {
        return 48 == i || i == 49;
    }

    private static boolean isHexDigit(int i) {
        if (48 <= i && i <= 57) {
            return true;
        }
        if (97 > i || i > 102) {
            return 65 <= i && i <= 70;
        }
        return true;
    }

    private static boolean isOctalDigit(int i) {
        return 48 <= i && i <= 55;
    }

    EcmaScriptTokenStream(String str, int i, boolean z) {
        this.sourceString = str;
        this.lineno = i;
        this.strictMode = z;
    }

    private Token stringToKeyword(String str) {
        return stringToKeywordForES(str, this.strictMode);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private static Token stringToKeywordForES(String str, boolean z) {
        str.hashCode();
        byte b = -1;
        switch (str.hashCode()) {
            case -1335458389:
                if (str.equals("delete")) {
                    b = 0;
                }
                break;
            case -1305664359:
                if (str.equals("extends")) {
                    b = 1;
                }
                break;
            case -1289153612:
                if (str.equals("export")) {
                    b = 2;
                }
                break;
            case -1184795739:
                if (str.equals("import")) {
                    b = 3;
                }
                break;
            case -977423767:
                if (str.equals("public")) {
                    b = 4;
                }
                break;
            case -934396624:
                if (str.equals("return")) {
                    b = 5;
                }
                break;
            case -915384400:
                if (str.equals("implements")) {
                    b = 6;
                }
                break;
            case -892481938:
                if (str.equals("static")) {
                    b = 7;
                }
                break;
            case -889473228:
                if (str.equals("switch")) {
                    b = 8;
                }
                break;
            case -858802543:
                if (str.equals("typeof")) {
                    b = 9;
                }
                break;
            case -853259901:
                if (str.equals("finally")) {
                    b = 10;
                }
                break;
            case -807062458:
                if (str.equals("package")) {
                    b = 11;
                }
                break;
            case -608539730:
                if (str.equals("protected")) {
                    b = Ascii.FF;
                }
                break;
            case -567202649:
                if (str.equals("continue")) {
                    b = Ascii.CR;
                }
                break;
            case -314497661:
                if (str.equals("private")) {
                    b = Ascii.SO;
                }
                break;
            case 3211:
                if (str.equals("do")) {
                    b = Ascii.SI;
                }
                break;
            case 3357:
                if (str.equals("if")) {
                    b = Ascii.DLE;
                }
                break;
            case 3365:
                if (str.equals("in")) {
                    b = 17;
                }
                break;
            case 101577:
                if (str.equals("for")) {
                    b = Ascii.DC2;
                }
                break;
            case 107035:
                if (str.equals("let")) {
                    b = 19;
                }
                break;
            case 108960:
                if (str.equals("new")) {
                    b = Ascii.DC4;
                }
                break;
            case 115131:
                if (str.equals("try")) {
                    b = Ascii.NAK;
                }
                break;
            case 116519:
                if (str.equals("var")) {
                    b = Ascii.SYN;
                }
                break;
            case 3046192:
                if (str.equals("case")) {
                    b = Ascii.ETB;
                }
                break;
            case 3116345:
                if (str.equals("else")) {
                    b = Ascii.CAN;
                }
                break;
            case 3118337:
                if (str.equals("enum")) {
                    b = Ascii.EM;
                }
                break;
            case 3392903:
                if (str.equals(AbstractJsonLexerKt.NULL)) {
                    b = Ascii.SUB;
                }
                break;
            case 3559070:
                if (str.equals("this")) {
                    b = Ascii.ESC;
                }
                break;
            case 3569038:
                if (str.equals("true")) {
                    b = Ascii.FS;
                }
                break;
            case 3625364:
                if (str.equals("void")) {
                    b = Ascii.GS;
                }
                break;
            case 3649734:
                if (str.equals(JsonPOJOBuilder.DEFAULT_WITH_PREFIX)) {
                    b = Ascii.RS;
                }
                break;
            case 93223254:
                if (str.equals("await")) {
                    b = Ascii.US;
                }
                break;
            case 94001407:
                if (str.equals("break")) {
                    b = 32;
                }
                break;
            case 94432955:
                if (str.equals("catch")) {
                    b = 33;
                }
                break;
            case 94742904:
                if (str.equals("class")) {
                    b = 34;
                }
                break;
            case 94844771:
                if (str.equals("const")) {
                    b = 35;
                }
                break;
            case 97196323:
                if (str.equals("false")) {
                    b = 36;
                }
                break;
            case 109801339:
                if (str.equals("super")) {
                    b = 37;
                }
                break;
            case 110339814:
                if (str.equals("throw")) {
                    b = 38;
                }
                break;
            case 113101617:
                if (str.equals("while")) {
                    b = 39;
                }
                break;
            case 114974605:
                if (str.equals("yield")) {
                    b = 40;
                }
                break;
            case 502623545:
                if (str.equals("interface")) {
                    b = 41;
                }
                break;
            case 547812385:
                if (str.equals("debugger")) {
                    b = 42;
                }
                break;
            case 902025516:
                if (str.equals("instanceof")) {
                    b = 43;
                }
                break;
            case 1380938712:
                if (str.equals("function")) {
                    b = 44;
                }
                break;
            case 1544803905:
                if (str.equals("default")) {
                    b = 45;
                }
                break;
        }
        switch (b) {
            case 0:
                return Token.DELPROP;
            case 1:
            case 25:
            case 31:
            case 34:
            case 37:
                return Token.RESERVED;
            case 2:
                return Token.EXPORT;
            case 3:
                return Token.IMPORT;
            case 4:
            case 6:
            case 7:
            case 11:
            case 12:
            case 14:
            case 41:
                if (z) {
                    return Token.RESERVED;
                }
                break;
            case 5:
                return Token.RETURN;
            case 8:
                return Token.SWITCH;
            case 9:
                return Token.TYPEOF;
            case 10:
                return Token.FINALLY;
            case 13:
                return Token.CONTINUE;
            case 15:
                return Token.DO;
            case 16:
                return Token.IF;
            case 17:
                return Token.IN;
            case 18:
                return Token.FOR;
            case 19:
                return Token.LET;
            case 20:
                return Token.NEW;
            case 21:
                return Token.TRY;
            case 22:
                return Token.VAR;
            case 23:
                return Token.CASE;
            case 24:
                return Token.ELSE;
            case 26:
                return Token.NULL;
            case 27:
                return Token.THIS;
            case 28:
                return Token.TRUE;
            case 29:
                return Token.VOID;
            case 30:
                return Token.WITH;
            case 32:
                return Token.BREAK;
            case 33:
                return Token.CATCH;
            case 35:
                return Token.CONST;
            case 36:
                return Token.FALSE;
            case 38:
                return Token.THROW;
            case 39:
                return Token.WHILE;
            case 40:
                return Token.YIELD;
            case 42:
                return Token.DEBUGGER;
            case 43:
                return Token.INSTANCEOF;
            case 44:
                return Token.FUNCTION;
            case 45:
                return Token.DEFAULT;
        }
        return Token.EOF;
    }

    /* JADX WARN: Code duplicated, block: B:185:0x0224 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:187:0x0227  */
    /* JADX WARN: Code duplicated, block: B:286:0x030d  */
    /* JADX WARN: Code duplicated, block: B:298:0x0325  */
    /* JADX WARN: Code duplicated, block: B:463:0x0239 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:464:0x021b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:465:0x0234 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:466:0x022d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:467:0x022b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:470:0x021e A[SYNTHETIC] */
    /* JADX WARN: Failed to find 'out' block for switch in B:312:0x0342. Please report as an issue. */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:185:0x0224 -> B:181:0x021b). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    final org.schabi.newpipe.extractor.utils.jsextractor.Token getToken() throws org.schabi.newpipe.extractor.exceptions.ParsingException {
        /*
            Method dump skipped, instruction units count: 1304
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.schabi.newpipe.extractor.utils.jsextractor.EcmaScriptTokenStream.getToken():org.schabi.newpipe.extractor.utils.jsextractor.Token");
    }

    private int readDigits(int i, int i2) {
        if (isDigit(i, i2)) {
            addToString(i2);
            i2 = getChar();
            if (i2 == -1) {
                return -1;
            }
            while (true) {
                if (i2 == 95) {
                    i2 = getChar();
                    if (i2 == 10 || i2 == -1) {
                        return -2;
                    }
                    if (!isDigit(i, i2)) {
                        ungetChar(i2);
                        return 95;
                    }
                    addToString(95);
                } else if (isDigit(i, i2)) {
                    addToString(i2);
                    i2 = getChar();
                    if (i2 == -1) {
                        return -1;
                    }
                }
            }
        }
        return i2;
    }

    private static boolean isDigit(int i, int i2) {
        if (i == 10 && isDigit(i2)) {
            return true;
        }
        if (i == 16 && isHexDigit(i2)) {
            return true;
        }
        if (i == 8 && isOctalDigit(i2)) {
            return true;
        }
        return i == 2 && isDualDigit(i2);
    }

    private static boolean isJSSpace(int i) {
        if (i <= 127) {
            return i == 32 || i == 9 || i == 12 || i == 11;
        }
        return i == 160 || i == 65279 || Character.getType((char) i) == 12;
    }

    private static boolean isJSFormatChar(int i) {
        return i > 127 && Character.getType((char) i) == 16;
    }

    void readRegExp(Token token) throws ParsingException {
        int charIgnoreLineEnd;
        int i = this.tokenBeg;
        this.stringBufferTop = 0;
        if (token == Token.ASSIGN_DIV) {
            addToString(61);
        } else {
            if (token != Token.DIV) {
                Kit.codeBug();
            }
            if (peekChar() == 42) {
                this.tokenEnd = this.cursor - 1;
                throw new ParsingException("msg.unterminated.re.lit");
            }
        }
        boolean z = false;
        while (true) {
            int i2 = getChar();
            if (i2 == 47 && !z) {
                while (true) {
                    charIgnoreLineEnd = getCharIgnoreLineEnd();
                    if ("gimysu".indexOf(charIgnoreLineEnd) == -1) {
                        break;
                    } else {
                        addToString(charIgnoreLineEnd);
                    }
                }
                if (isAlpha(charIgnoreLineEnd)) {
                    throw new ParsingException("msg.invalid.re.flag");
                }
                ungetCharIgnoreLineEnd(charIgnoreLineEnd);
                this.tokenEnd = i + this.stringBufferTop + 2;
                return;
            }
            if (i2 == 10 || i2 == -1) {
                throw new ParsingException("msg.unterminated.re.lit");
            }
            if (i2 == 92) {
                addToString(i2);
                i2 = getChar();
                if (i2 == 10 || i2 == -1) {
                    throw new ParsingException("msg.unterminated.re.lit");
                }
            } else if (i2 == 91) {
                z = true;
            } else if (i2 == 93) {
                z = false;
            }
            addToString(i2);
        }
    }

    private String getStringFromBuffer() {
        this.tokenEnd = this.cursor;
        return new String(this.stringBuffer, 0, this.stringBufferTop);
    }

    private void addToString(int i) {
        int i2 = this.stringBufferTop;
        char[] cArr = this.stringBuffer;
        if (i2 == cArr.length) {
            char[] cArr2 = new char[cArr.length * 2];
            System.arraycopy(cArr, 0, cArr2, 0, i2);
            this.stringBuffer = cArr2;
        }
        this.stringBuffer[i2] = (char) i;
        this.stringBufferTop = i2 + 1;
    }

    private void ungetChar(int i) {
        int i2 = this.ungetCursor;
        if (i2 != 0 && this.ungetBuffer[i2 - 1] == 10) {
            Kit.codeBug();
        }
        int[] iArr = this.ungetBuffer;
        int i3 = this.ungetCursor;
        this.ungetCursor = i3 + 1;
        iArr[i3] = i;
        this.cursor--;
    }

    private boolean matchChar(int i) {
        int charIgnoreLineEnd = getCharIgnoreLineEnd();
        if (charIgnoreLineEnd == i) {
            this.tokenEnd = this.cursor;
            return true;
        }
        ungetCharIgnoreLineEnd(charIgnoreLineEnd);
        return false;
    }

    private int peekChar() {
        int i = getChar();
        ungetChar(i);
        return i;
    }

    private int getChar() {
        return getChar(true, false);
    }

    private int getChar(boolean z) {
        return getChar(z, false);
    }

    private int getChar(boolean z, boolean z2) {
        int i;
        int i2 = this.ungetCursor;
        if (i2 != 0) {
            this.cursor++;
            int[] iArr = this.ungetBuffer;
            int i3 = i2 - 1;
            this.ungetCursor = i3;
            return iArr[i3];
        }
        while (this.sourceCursor != this.sourceString.length()) {
            this.cursor++;
            String str = this.sourceString;
            int i4 = this.sourceCursor;
            this.sourceCursor = i4 + 1;
            char cCharAt = str.charAt(i4);
            if (!z2 && (i = this.lineEndChar) >= 0) {
                if (i == 13 && cCharAt == '\n') {
                    this.lineEndChar = 10;
                } else {
                    this.lineEndChar = -1;
                    this.lineno++;
                }
            }
            if (cCharAt <= 127) {
                if (cCharAt == '\n' || cCharAt == '\r') {
                    this.lineEndChar = cCharAt;
                    return 10;
                }
            } else if (cCharAt != 65279) {
                if (!z || !isJSFormatChar(cCharAt)) {
                    if (ScriptRuntime.isJSLineTerminator(cCharAt)) {
                        this.lineEndChar = cCharAt;
                        return 10;
                    }
                }
            }
            return cCharAt;
        }
        return -1;
    }

    private int getCharIgnoreLineEnd() {
        return getChar(true, true);
    }

    private int getCharIgnoreLineEnd(boolean z) {
        return getChar(z, true);
    }

    private void ungetCharIgnoreLineEnd(int i) {
        int[] iArr = this.ungetBuffer;
        int i2 = this.ungetCursor;
        this.ungetCursor = i2 + 1;
        iArr[i2] = i;
        this.cursor--;
    }

    private void skipLine() {
        int i;
        do {
            i = getChar();
            if (i == -1) {
                break;
            }
        } while (i != 10);
        ungetChar(i);
        this.tokenEnd = this.cursor;
    }

    public Token nextToken() throws ParsingException {
        Token token = getToken();
        while (true) {
            if (token != Token.EOL && token != Token.COMMENT) {
                return token;
            }
            token = getToken();
        }
    }
}
