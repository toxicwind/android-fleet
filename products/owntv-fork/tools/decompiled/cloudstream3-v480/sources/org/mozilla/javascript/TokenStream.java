package org.mozilla.javascript;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.common.base.Ascii;
import j$.util.Map;
import j$.util.stream.IntStream;
import java.io.IOException;
import java.io.Reader;
import java.math.BigInteger;
import java.util.HashMap;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: classes8.dex */
class TokenStream implements Parser.CurrentPositionReporter {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final char BYTE_ORDER_MARK = 65279;
    private static final int EOF_CHAR = -1;
    private static final char NUMERIC_SEPARATOR = '_';
    private static final int REPORT_NUMBER_FORMAT_ERROR = -2;
    private BigInteger bigInt;
    Token.CommentType commentType;
    int cursor;
    private boolean dirtyLine;
    private boolean isBinary;
    private boolean isHex;
    private boolean isOctal;
    private boolean isOldOctal;
    private int lastLineEnd;
    int lineno;
    private double number;
    private Parser parser;
    private int quoteChar;
    String regExpFlags;
    private char[] sourceBuffer;
    int sourceCursor;
    private int sourceEnd;
    private Reader sourceReader;
    private String sourceString;
    private int stringBufferTop;
    int tokenBeg;
    int tokenEnd;
    private int tokenStartLastLineEnd;
    private int tokenStartLineno;
    private int ungetCursor;
    private boolean xmlIsAttribute;
    private boolean xmlIsTagContent;
    private int xmlOpenTagsCount;
    private StringBuilder rawString = new StringBuilder();
    private String string = "";
    private char[] stringBuffer = new char[128];
    private final HashMap<String, String> allStrings = new HashMap<>();
    private final int[] ungetBuffer = new int[3];
    private boolean hitEOF = false;
    private int lineStart = 0;
    private int lineEndChar = -1;
    private String commentPrefix = "";
    private int commentCursor = -1;

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

    TokenStream(Parser parser, Reader reader, String str, int i) {
        this.parser = parser;
        this.lineno = i;
        if (reader != null) {
            if (str != null) {
                Kit.codeBug();
            }
            this.sourceReader = reader;
            this.sourceBuffer = new char[512];
            this.sourceEnd = 0;
        } else {
            if (str == null) {
                Kit.codeBug();
            }
            this.sourceString = str;
            this.sourceEnd = str.length();
        }
        this.cursor = 0;
        this.sourceCursor = 0;
    }

    String tokenToString(int i) {
        return "";
    }

    static boolean isKeyword(String str, int i, boolean z) {
        return stringToKeyword(str, i, z) != 0;
    }

    private static int stringToKeyword(String str, int i, boolean z) {
        if (i < 200) {
            return stringToKeywordForJS(str);
        }
        return stringToKeywordForES(str, z);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private static int stringToKeywordForJS(String str) {
        str.hashCode();
        int i = 58;
        byte b = -1;
        switch (str.hashCode()) {
            case -1888027236:
                if (str.equals("volatile")) {
                    b = 0;
                }
                break;
            case -1466596076:
                if (str.equals("synchronized")) {
                    b = 1;
                }
                break;
            case -1335458389:
                if (str.equals("delete")) {
                    b = 2;
                }
                break;
            case -1325958191:
                if (str.equals("double")) {
                    b = 3;
                }
                break;
            case -1305664359:
                if (str.equals("extends")) {
                    b = 4;
                }
                break;
            case -1289153612:
                if (str.equals("export")) {
                    b = 5;
                }
                break;
            case -1184795739:
                if (str.equals("import")) {
                    b = 6;
                }
                break;
            case -1052618729:
                if (str.equals("native")) {
                    b = 7;
                }
                break;
            case -977423767:
                if (str.equals("public")) {
                    b = 8;
                }
                break;
            case -934396624:
                if (str.equals("return")) {
                    b = 9;
                }
                break;
            case -915384400:
                if (str.equals("implements")) {
                    b = 10;
                }
                break;
            case -892481938:
                if (str.equals("static")) {
                    b = 11;
                }
                break;
            case -889473228:
                if (str.equals("switch")) {
                    b = Ascii.FF;
                }
                break;
            case -874432947:
                if (str.equals("throws")) {
                    b = Ascii.CR;
                }
                break;
            case -858802543:
                if (str.equals("typeof")) {
                    b = Ascii.SO;
                }
                break;
            case -853259901:
                if (str.equals("finally")) {
                    b = Ascii.SI;
                }
                break;
            case -807062458:
                if (str.equals("package")) {
                    b = Ascii.DLE;
                }
                break;
            case -608539730:
                if (str.equals("protected")) {
                    b = 17;
                }
                break;
            case -567202649:
                if (str.equals("continue")) {
                    b = Ascii.DC2;
                }
                break;
            case -314497661:
                if (str.equals("private")) {
                    b = 19;
                }
                break;
            case 3211:
                if (str.equals("do")) {
                    b = Ascii.DC4;
                }
                break;
            case 3357:
                if (str.equals("if")) {
                    b = Ascii.NAK;
                }
                break;
            case 3365:
                if (str.equals("in")) {
                    b = Ascii.SYN;
                }
                break;
            case 101577:
                if (str.equals("for")) {
                    b = Ascii.ETB;
                }
                break;
            case 104431:
                if (str.equals("int")) {
                    b = Ascii.CAN;
                }
                break;
            case 107035:
                if (str.equals("let")) {
                    b = Ascii.EM;
                }
                break;
            case 108960:
                if (str.equals("new")) {
                    b = Ascii.SUB;
                }
                break;
            case 115131:
                if (str.equals("try")) {
                    b = Ascii.ESC;
                }
                break;
            case 116519:
                if (str.equals("var")) {
                    b = Ascii.FS;
                }
                break;
            case 3039496:
                if (str.equals("byte")) {
                    b = Ascii.GS;
                }
                break;
            case 3046192:
                if (str.equals("case")) {
                    b = 30;
                }
                break;
            case 3052374:
                if (str.equals("char")) {
                    b = 31;
                }
                break;
            case 3116345:
                if (str.equals("else")) {
                    b = 32;
                }
                break;
            case 3118337:
                if (str.equals("enum")) {
                    b = 33;
                }
                break;
            case 3178851:
                if (str.equals("goto")) {
                    b = 34;
                }
                break;
            case 3327612:
                if (str.equals("long")) {
                    b = 35;
                }
                break;
            case 3392903:
                if (str.equals(AbstractJsonLexerKt.NULL)) {
                    b = 36;
                }
                break;
            case 3559070:
                if (str.equals("this")) {
                    b = 37;
                }
                break;
            case 3569038:
                if (str.equals("true")) {
                    b = 38;
                }
                break;
            case 3625364:
                if (str.equals("void")) {
                    b = 39;
                }
                break;
            case 3649734:
                if (str.equals(JsonPOJOBuilder.DEFAULT_WITH_PREFIX)) {
                    b = 40;
                }
                break;
            case 64711720:
                if (str.equals(TypedValues.Custom.S_BOOLEAN)) {
                    b = 41;
                }
                break;
            case 94001407:
                if (str.equals("break")) {
                    b = 42;
                }
                break;
            case 94432955:
                if (str.equals("catch")) {
                    b = 43;
                }
                break;
            case 94742904:
                if (str.equals("class")) {
                    b = 44;
                }
                break;
            case 94844771:
                if (str.equals("const")) {
                    b = 45;
                }
                break;
            case 97196323:
                if (str.equals("false")) {
                    b = 46;
                }
                break;
            case 97436022:
                if (str.equals("final")) {
                    b = 47;
                }
                break;
            case 97526364:
                if (str.equals(TypedValues.Custom.S_FLOAT)) {
                    b = 48;
                }
                break;
            case 109413500:
                if (str.equals("short")) {
                    b = 49;
                }
                break;
            case 109801339:
                if (str.equals("super")) {
                    b = 50;
                }
                break;
            case 110339814:
                if (str.equals("throw")) {
                    b = 51;
                }
                break;
            case 113101617:
                if (str.equals("while")) {
                    b = 52;
                }
                break;
            case 114974605:
                if (str.equals("yield")) {
                    b = 53;
                }
                break;
            case 502623545:
                if (str.equals("interface")) {
                    b = 54;
                }
                break;
            case 547812385:
                if (str.equals("debugger")) {
                    b = 55;
                }
                break;
            case 902025516:
                if (str.equals("instanceof")) {
                    b = 56;
                }
                break;
            case 1052746378:
                if (str.equals("transient")) {
                    b = 57;
                }
                break;
            case 1380938712:
                if (str.equals("function")) {
                    b = 58;
                }
                break;
            case 1544803905:
                if (str.equals("default")) {
                    b = 59;
                }
                break;
            case 1732898850:
                if (str.equals("abstract")) {
                    b = 60;
                }
                break;
        }
        switch (b) {
            case 0:
            case 1:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 10:
            case 11:
            case 13:
            case 16:
            case 17:
            case 19:
            case 24:
            case 29:
            case 31:
            case 33:
            case 34:
            case 35:
            case 41:
            case 44:
            case 47:
            case 48:
            case 49:
            case 50:
            case 54:
            case 57:
            case 60:
                i = 140;
                break;
            case 2:
                i = 31;
                break;
            case 9:
                i = 4;
                break;
            case 12:
                i = 127;
                break;
            case 14:
                i = 32;
                break;
            case 15:
                i = 138;
                break;
            case 18:
                i = 134;
                break;
            case 20:
                i = 131;
                break;
            case 21:
                i = 125;
                break;
            case 22:
                i = 57;
                break;
            case 23:
                i = 132;
                break;
            case 25:
                i = 167;
                break;
            case 26:
                i = 30;
                break;
            case 27:
                i = 90;
                break;
            case 28:
                i = 135;
                break;
            case 30:
                i = 128;
                break;
            case 32:
                i = 126;
                break;
            case 36:
                i = 47;
                break;
            case 37:
                i = 48;
                break;
            case 38:
                i = 50;
                break;
            case 39:
                i = 139;
                break;
            case 40:
                i = 136;
                break;
            case 42:
                i = 133;
                break;
            case 43:
                i = 137;
                break;
            case 45:
                i = 168;
                break;
            case 46:
                i = 49;
                break;
            case 51:
                i = 55;
                break;
            case 52:
                i = 130;
                break;
            case 53:
                i = 78;
                break;
            case 55:
                i = 174;
                break;
            case 56:
                break;
            case 58:
                i = 122;
                break;
            case 59:
                i = 129;
                break;
            default:
                i = 0;
                break;
        }
        if (i == 0) {
            return 0;
        }
        return i & 255;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private static int stringToKeywordForES(String str, boolean z) {
        str.hashCode();
        int i = 32;
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
                    b = 30;
                }
                break;
            case 93223254:
                if (str.equals("await")) {
                    b = 31;
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
                i = 31;
                break;
            case 1:
            case 2:
            case 3:
            case 25:
            case 31:
            case 34:
                i = 140;
                break;
            case 4:
                i = !z ? 0 : 140;
                break;
            case 5:
                i = 4;
                break;
            case 6:
                i = !z ? 0 : 140;
                break;
            case 7:
                i = !z ? 0 : 140;
                break;
            case 8:
                i = 127;
                break;
            case 9:
                break;
            case 10:
                i = 138;
                break;
            case 11:
                i = !z ? 0 : 140;
                break;
            case 12:
                i = !z ? 0 : 140;
                break;
            case 13:
                i = 134;
                break;
            case 14:
                i = !z ? 0 : 140;
                break;
            case 15:
                i = 131;
                break;
            case 16:
                i = 125;
                break;
            case 17:
                i = 57;
                break;
            case 18:
                i = 132;
                break;
            case 19:
                i = 167;
                break;
            case 20:
                i = 30;
                break;
            case 21:
                i = 90;
                break;
            case 22:
                i = 135;
                break;
            case 23:
                i = 128;
                break;
            case 24:
                i = 126;
                break;
            case 26:
                i = 47;
                break;
            case 27:
                i = 48;
                break;
            case 28:
                i = 50;
                break;
            case 29:
                i = 139;
                break;
            case 30:
                i = 136;
                break;
            case 32:
                i = 133;
                break;
            case 33:
                i = 137;
                break;
            case 35:
                i = 168;
                break;
            case 36:
                i = 49;
                break;
            case 37:
                i = 79;
                break;
            case 38:
                i = 55;
                break;
            case 39:
                i = 130;
                break;
            case 40:
                i = 78;
                break;
            case 41:
                i = !z ? 0 : 140;
                break;
            case 42:
                i = 174;
                break;
            case 43:
                i = 58;
                break;
            case 44:
                i = 122;
                break;
            case 45:
                i = 129;
                break;
            default:
                i = 0;
                break;
        }
        if (i == 0) {
            return 0;
        }
        return i & 255;
    }

    private static boolean isValidIdentifierName(String str) {
        int[] array = IntStream.VivifiedWrapper.convert(str.codePoints()).toArray();
        int length = array.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = array[i];
            int i4 = i2 + 1;
            if (i2 == 0) {
                if (i3 != 36 && i3 != 95 && !Character.isUnicodeIdentifierStart(i3)) {
                    return false;
                }
            } else if (i3 != 36 && i3 != 8204 && i3 != 8205 && !Character.isUnicodeIdentifierPart(i3)) {
                return false;
            }
            i++;
            i2 = i4;
        }
        return true;
    }

    final String getSourceString() {
        return this.sourceString;
    }

    @Override // org.mozilla.javascript.Parser.CurrentPositionReporter
    public int getLineno() {
        return this.lineno;
    }

    public int getTokenStartLineno() {
        return this.tokenStartLineno;
    }

    final String getString() {
        return this.string;
    }

    final char getQuoteChar() {
        return (char) this.quoteChar;
    }

    final double getNumber() {
        return this.number;
    }

    final BigInteger getBigInt() {
        return this.bigInt;
    }

    final boolean isNumericBinary() {
        return this.isBinary;
    }

    final boolean isNumericOldOctal() {
        return this.isOldOctal;
    }

    final boolean isNumericOctal() {
        return this.isOctal;
    }

    final boolean isNumericHex() {
        return this.isHex;
    }

    final boolean eof() {
        return this.hitEOF;
    }

    /* JADX WARN: Code duplicated, block: B:281:0x038b  */
    /* JADX WARN: Code duplicated, block: B:284:0x0390  */
    /* JADX WARN: Code duplicated, block: B:402:0x04af  */
    /* JADX WARN: Code duplicated, block: B:590:0x0711  */
    /* JADX WARN: Code duplicated, block: B:593:0x071a  */
    /* JADX WARN: Code duplicated, block: B:595:0x071e  */
    /* JADX WARN: Code duplicated, block: B:599:0x072e  */
    /* JADX WARN: Code duplicated, block: B:601:0x0738  */
    /* JADX WARN: Code duplicated, block: B:607:0x0747  */
    /* JADX WARN: Code duplicated, block: B:638:0x0371 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:639:0x039d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:640:0x0396 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:641:0x0394 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:644:0x0378 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:661:0x0723 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:282:0x038d -> B:274:0x0371). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    final int getToken() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 1928
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.TokenStream.getToken():int");
    }

    private int readDigits(int i, int i2) throws IOException {
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

    private String internString(String str) {
        String str2 = (String) Map.EL.putIfAbsent(this.allStrings, str, str);
        return str2 == null ? str : str2;
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

    void readRegExp(int i) throws IOException {
        int i2;
        int i3 = this.tokenBeg;
        this.stringBufferTop = 0;
        if (i == 111) {
            addToString(61);
        } else {
            if (i != 24) {
                Kit.codeBug();
            }
            if (peekChar() == 42) {
                this.tokenEnd = this.cursor - 1;
                this.string = new String(this.stringBuffer, 0, this.stringBufferTop);
                this.parser.reportError("msg.unterminated.re.lit");
                return;
            }
        }
        boolean z = false;
        while (true) {
            i2 = getChar();
            if (i2 == 47 && !z) {
                int i4 = this.stringBufferTop;
                while (true) {
                    if (!matchChar(103)) {
                        if (!matchChar(105)) {
                            if (!matchChar(109)) {
                                if (!matchChar(115)) {
                                    if (!matchChar(121)) {
                                        break;
                                    } else {
                                        addToString(121);
                                    }
                                } else {
                                    addToString(115);
                                }
                            } else {
                                addToString(109);
                            }
                        } else {
                            addToString(105);
                        }
                    } else {
                        addToString(103);
                    }
                }
                this.tokenEnd = i3 + this.stringBufferTop + 2;
                if (isAlpha(peekChar())) {
                    this.parser.reportError("msg.invalid.re.flag", String.valueOf(Character.toChars(peekChar())));
                }
                this.string = new String(this.stringBuffer, 0, i4);
                this.regExpFlags = new String(this.stringBuffer, i4, this.stringBufferTop - i4);
                return;
            }
            if (i2 == 10 || i2 == -1) {
                break;
            }
            if (i2 == 92) {
                addToString(i2);
                i2 = getChar();
                if (i2 == 10 || i2 == -1) {
                    ungetChar(i2);
                    this.tokenEnd = this.cursor - 1;
                    this.string = new String(this.stringBuffer, 0, this.stringBufferTop);
                    this.parser.reportError("msg.unterminated.re.lit");
                    return;
                }
            } else if (i2 == 91) {
                z = true;
            } else if (i2 == 93) {
                z = false;
            }
            addToString(i2);
        }
        ungetChar(i2);
        this.tokenEnd = this.cursor - 1;
        this.string = new String(this.stringBuffer, 0, this.stringBufferTop);
        this.parser.reportError("msg.unterminated.re.lit");
    }

    String readAndClearRegExpFlags() {
        String str = this.regExpFlags;
        this.regExpFlags = null;
        return str;
    }

    String getRawString() {
        if (this.rawString.length() == 0) {
            return "";
        }
        return this.rawString.toString();
    }

    private int getTemplateLiteralChar() throws IOException {
        int charIgnoreLineEnd = getCharIgnoreLineEnd(false);
        if (charIgnoreLineEnd == 10) {
            int i = this.lineEndChar;
            if (i != 13) {
                if (i == 8232 || i == 8233) {
                    charIgnoreLineEnd = i;
                }
            } else if (charAt(this.cursor) == 10) {
                getCharIgnoreLineEnd(false);
            }
            this.lineEndChar = -1;
            this.lineStart = this.sourceCursor - 1;
            this.lineno++;
        }
        this.rawString.append((char) charIgnoreLineEnd);
        return charIgnoreLineEnd;
    }

    private void ungetTemplateLiteralChar(int i) {
        ungetCharIgnoreLineEnd(i);
        StringBuilder sb = this.rawString;
        sb.setLength(sb.length() - 1);
    }

    private boolean matchTemplateLiteralChar(int i) throws IOException {
        int templateLiteralChar = getTemplateLiteralChar();
        if (templateLiteralChar == i) {
            return true;
        }
        ungetTemplateLiteralChar(templateLiteralChar);
        return false;
    }

    private int peekTemplateLiteralChar() throws IOException {
        int templateLiteralChar = getTemplateLiteralChar();
        ungetTemplateLiteralChar(templateLiteralChar);
        return templateLiteralChar;
    }

    int readTemplateLiteral(boolean z) throws IOException {
        this.rawString.setLength(0);
        this.stringBufferTop = 0;
        boolean z2 = false;
        while (true) {
            int templateLiteralChar = getTemplateLiteralChar();
            if (templateLiteralChar == -1) {
                this.string = z2 ? null : getStringFromBuffer();
                this.tokenEnd = this.cursor - 1;
                this.parser.reportError("msg.unexpected.eof");
                return -1;
            }
            if (templateLiteralChar == 36) {
                if (matchTemplateLiteralChar(123)) {
                    StringBuilder sb = this.rawString;
                    sb.setLength(sb.length() - 2);
                    this.string = z2 ? null : getStringFromBuffer();
                    this.tokenEnd = this.cursor - 1;
                    return 182;
                }
                addToString(templateLiteralChar);
            } else if (templateLiteralChar == 92) {
                int templateLiteralChar2 = getTemplateLiteralChar();
                if (templateLiteralChar2 == 10) {
                    continue;
                } else {
                    if (templateLiteralChar2 == 98) {
                        templateLiteralChar2 = 8;
                    } else if (templateLiteralChar2 == 102) {
                        templateLiteralChar2 = 12;
                    } else if (templateLiteralChar2 == 110) {
                        templateLiteralChar2 = 10;
                    } else if (templateLiteralChar2 == 114) {
                        templateLiteralChar2 = 13;
                    } else if (templateLiteralChar2 == 120) {
                        int i = 0;
                        int iXDigitToInt = 0;
                        while (true) {
                            if (i >= 2) {
                                templateLiteralChar2 = iXDigitToInt;
                            } else if (peekTemplateLiteralChar() == 96) {
                                templateLiteralChar2 = -1;
                            } else {
                                iXDigitToInt = Kit.xDigitToInt(getTemplateLiteralChar(), iXDigitToInt);
                                i++;
                            }
                        }
                        if (templateLiteralChar2 < 0) {
                            if (!z) {
                                this.parser.reportError("msg.syntax");
                                return -1;
                            }
                            z2 = true;
                        }
                    } else if (templateLiteralChar2 != 8232 && templateLiteralChar2 != 8233) {
                        switch (templateLiteralChar2) {
                            case 48:
                                int iPeekTemplateLiteralChar = peekTemplateLiteralChar();
                                if (iPeekTemplateLiteralChar >= 48 && iPeekTemplateLiteralChar <= 57) {
                                    if (!z) {
                                        this.parser.reportError("msg.syntax");
                                        return -1;
                                    }
                                    z2 = true;
                                } else {
                                    templateLiteralChar2 = 0;
                                }
                                break;
                            case 49:
                            case 50:
                            case 51:
                            case 52:
                            case 53:
                            case 54:
                            case 55:
                            case 56:
                            case 57:
                                if (!z) {
                                    this.parser.reportError("msg.syntax");
                                    return -1;
                                }
                                z2 = true;
                                break;
                            default:
                                switch (templateLiteralChar2) {
                                    case 116:
                                        templateLiteralChar2 = 9;
                                        break;
                                    case 117:
                                        if (!matchTemplateLiteralChar(123)) {
                                            int i2 = 0;
                                            int iXDigitToInt2 = 0;
                                            while (true) {
                                                if (i2 >= 4) {
                                                    templateLiteralChar2 = iXDigitToInt2;
                                                } else if (peekTemplateLiteralChar() == 96) {
                                                    templateLiteralChar2 = -1;
                                                } else {
                                                    iXDigitToInt2 = Kit.xDigitToInt(getTemplateLiteralChar(), iXDigitToInt2);
                                                    i2++;
                                                }
                                            }
                                            if (templateLiteralChar2 < 0) {
                                                if (!z) {
                                                    this.parser.reportError("msg.syntax");
                                                    return -1;
                                                }
                                                z2 = true;
                                            }
                                        } else {
                                            templateLiteralChar2 = 0;
                                            while (true) {
                                                if (peekTemplateLiteralChar() == 96) {
                                                    templateLiteralChar2 = -1;
                                                } else {
                                                    int templateLiteralChar3 = getTemplateLiteralChar();
                                                    if (templateLiteralChar3 == -1) {
                                                        this.parser.reportError("msg.syntax");
                                                        return -1;
                                                    }
                                                    if (templateLiteralChar3 != 125) {
                                                        templateLiteralChar2 = Kit.xDigitToInt(templateLiteralChar3, templateLiteralChar2);
                                                    }
                                                }
                                            }
                                            if (templateLiteralChar2 < 0 || templateLiteralChar2 > 1114111) {
                                                if (!z) {
                                                    this.parser.reportError("msg.syntax");
                                                    return -1;
                                                }
                                                z2 = true;
                                            } else if (templateLiteralChar2 > 65535) {
                                                addToString(Character.highSurrogate(templateLiteralChar2));
                                                addToString(Character.lowSurrogate(templateLiteralChar2));
                                            }
                                        }
                                        break;
                                    case 118:
                                        templateLiteralChar2 = 11;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                        }
                    }
                    addToString(templateLiteralChar2);
                }
            } else {
                if (templateLiteralChar == 96) {
                    StringBuilder sb2 = this.rawString;
                    sb2.setLength(sb2.length() - 1);
                    this.string = z2 ? null : getStringFromBuffer();
                    int i3 = this.sourceCursor;
                    this.cursor = i3;
                    this.tokenEnd = i3;
                    return 180;
                }
                addToString(templateLiteralChar);
            }
        }
    }

    boolean isXMLAttribute() {
        return this.xmlIsAttribute;
    }

    int getFirstXMLToken() throws IOException {
        this.xmlOpenTagsCount = 0;
        this.xmlIsAttribute = false;
        this.xmlIsTagContent = false;
        if (!canUngetChar()) {
            return -1;
        }
        ungetChar(60);
        return getNextXMLToken();
    }

    int getNextXMLToken() throws IOException {
        this.tokenStartLastLineEnd = this.lastLineEnd;
        this.tokenStartLineno = this.lineno;
        this.tokenBeg = this.cursor;
        this.stringBufferTop = 0;
        while (true) {
            int i = getChar();
            if (i != -1) {
                if (this.xmlIsTagContent) {
                    if (i == 9 || i == 10 || i == 13 || i == 32) {
                        addToString(i);
                    } else if (i == 34 || i == 39) {
                        addToString(i);
                        if (!readQuotedString(i)) {
                            return -1;
                        }
                    } else if (i == 47) {
                        addToString(i);
                        if (peekChar() == 62) {
                            addToString(getChar());
                            this.xmlIsTagContent = false;
                            this.xmlOpenTagsCount--;
                        }
                    } else {
                        if (i == 123) {
                            ungetChar(i);
                            this.string = getStringFromBuffer();
                            return 159;
                        }
                        if (i == 61) {
                            addToString(i);
                            this.xmlIsAttribute = true;
                        } else if (i == 62) {
                            addToString(i);
                            this.xmlIsTagContent = false;
                            this.xmlIsAttribute = false;
                        } else {
                            addToString(i);
                            this.xmlIsAttribute = false;
                        }
                    }
                    if (!this.xmlIsTagContent && this.xmlOpenTagsCount == 0) {
                        this.string = getStringFromBuffer();
                        int i2 = this.sourceCursor;
                        this.cursor = i2;
                        this.tokenEnd = i2;
                        return 162;
                    }
                } else if (i == 60) {
                    addToString(i);
                    int iPeekChar = peekChar();
                    if (iPeekChar == 33) {
                        addToString(getChar());
                        int iPeekChar2 = peekChar();
                        if (iPeekChar2 == 45) {
                            addToString(getChar());
                            int i3 = getChar();
                            if (i3 == 45) {
                                addToString(i3);
                                if (!readXmlComment()) {
                                    return -1;
                                }
                            } else {
                                this.stringBufferTop = 0;
                                this.string = null;
                                this.parser.addError("msg.XML.bad.form");
                                return -1;
                            }
                        } else if (iPeekChar2 == 91) {
                            addToString(getChar());
                            if (getChar() == 67 && getChar() == 68 && getChar() == 65 && getChar() == 84 && getChar() == 65 && getChar() == 91) {
                                addToString(67);
                                addToString(68);
                                addToString(65);
                                addToString(84);
                                addToString(65);
                                addToString(91);
                                if (!readCDATA()) {
                                    return -1;
                                }
                            } else {
                                this.stringBufferTop = 0;
                                this.string = null;
                                this.parser.addError("msg.XML.bad.form");
                                return -1;
                            }
                        } else if (!readEntity()) {
                            return -1;
                        }
                    } else if (iPeekChar == 47) {
                        addToString(getChar());
                        int i4 = this.xmlOpenTagsCount;
                        if (i4 == 0) {
                            this.stringBufferTop = 0;
                            this.string = null;
                            this.parser.addError("msg.XML.bad.form");
                            return -1;
                        }
                        this.xmlIsTagContent = true;
                        this.xmlOpenTagsCount = i4 - 1;
                    } else if (iPeekChar == 63) {
                        addToString(getChar());
                        if (!readPI()) {
                            return -1;
                        }
                    } else {
                        this.xmlIsTagContent = true;
                        this.xmlOpenTagsCount++;
                    }
                } else {
                    if (i == 123) {
                        ungetChar(i);
                        this.string = getStringFromBuffer();
                        return 159;
                    }
                    addToString(i);
                }
            } else {
                this.tokenEnd = this.cursor;
                this.stringBufferTop = 0;
                this.string = null;
                this.parser.addError("msg.XML.bad.form");
                return -1;
            }
        }
    }

    private boolean readQuotedString(int i) throws IOException {
        int i2;
        do {
            i2 = getChar();
            if (i2 != -1) {
                addToString(i2);
            } else {
                this.stringBufferTop = 0;
                this.string = null;
                this.parser.addError("msg.XML.bad.form");
                return false;
            }
        } while (i2 != i);
        return true;
    }

    private boolean readXmlComment() throws IOException {
        int i = getChar();
        while (i != -1) {
            addToString(i);
            if (i == 45 && peekChar() == 45) {
                i = getChar();
                addToString(i);
                if (peekChar() == 62) {
                    addToString(getChar());
                    return true;
                }
            } else {
                i = getChar();
            }
        }
        this.stringBufferTop = 0;
        this.string = null;
        this.parser.addError("msg.XML.bad.form");
        return false;
    }

    private boolean readCDATA() throws IOException {
        int i = getChar();
        while (i != -1) {
            addToString(i);
            if (i == 93 && peekChar() == 93) {
                i = getChar();
                addToString(i);
                if (peekChar() == 62) {
                    addToString(getChar());
                    return true;
                }
            } else {
                i = getChar();
            }
        }
        this.stringBufferTop = 0;
        this.string = null;
        this.parser.addError("msg.XML.bad.form");
        return false;
    }

    private boolean readEntity() throws IOException {
        int i = getChar();
        int i2 = 1;
        while (i != -1) {
            addToString(i);
            if (i == 60) {
                i2++;
            } else if (i == 62 && (i2 = i2 - 1) == 0) {
                return true;
            }
            i = getChar();
        }
        this.stringBufferTop = 0;
        this.string = null;
        this.parser.addError("msg.XML.bad.form");
        return false;
    }

    private boolean readPI() throws IOException {
        while (true) {
            int i = getChar();
            if (i != -1) {
                addToString(i);
                if (i == 63 && peekChar() == 62) {
                    addToString(getChar());
                    return true;
                }
            } else {
                this.stringBufferTop = 0;
                this.string = null;
                this.parser.addError("msg.XML.bad.form");
                return false;
            }
        }
    }

    private String getStringFromBuffer() {
        this.tokenEnd = this.cursor;
        return new String(this.stringBuffer, 0, this.stringBufferTop);
    }

    private void addToString(int i) {
        int i2 = this.stringBufferTop;
        int iCharCount = Character.charCount(i);
        int i3 = i2 + iCharCount;
        char[] cArr = this.stringBuffer;
        if (i3 >= cArr.length) {
            char[] cArr2 = new char[cArr.length * 2];
            System.arraycopy(cArr, 0, cArr2, 0, i2);
            this.stringBuffer = cArr2;
        }
        char[] cArr3 = this.stringBuffer;
        if (iCharCount == 1) {
            cArr3[i2] = (char) i;
        } else {
            cArr3[i2] = Character.highSurrogate(i);
            this.stringBuffer[i2 + 1] = Character.lowSurrogate(i);
        }
        this.stringBufferTop = i3;
    }

    private boolean canUngetChar() {
        int i = this.ungetCursor;
        return i == 0 || this.ungetBuffer[i - 1] != 10;
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

    private boolean matchChar(int i) throws IOException {
        int charIgnoreLineEnd = getCharIgnoreLineEnd();
        if (charIgnoreLineEnd == i) {
            this.tokenEnd = this.cursor;
            return true;
        }
        ungetCharIgnoreLineEnd(charIgnoreLineEnd);
        return false;
    }

    private int peekChar() throws IOException {
        int i = getChar();
        ungetChar(i);
        return i;
    }

    private int getChar() throws IOException {
        return getChar(true, false);
    }

    private int getChar(boolean z) throws IOException {
        return getChar(z, false);
    }

    private int getChar(boolean z, boolean z2) throws IOException {
        int i;
        int i2;
        int i3 = this.ungetCursor;
        if (i3 != 0) {
            this.cursor++;
            int[] iArr = this.ungetBuffer;
            int i4 = i3 - 1;
            this.ungetCursor = i4;
            return iArr[i4];
        }
        while (true) {
            String str = this.sourceString;
            int i5 = this.sourceCursor;
            if (str != null) {
                if (i5 == this.sourceEnd) {
                    this.hitEOF = true;
                    return -1;
                }
                this.cursor++;
                int iCodePointAt = str.codePointAt(i5);
                this.sourceCursor += Character.charCount(iCodePointAt);
                i = iCodePointAt;
            } else {
                if (i5 == this.sourceEnd && !fillSourceBuffer()) {
                    this.hitEOF = true;
                    return -1;
                }
                this.cursor++;
                char[] cArr = this.sourceBuffer;
                int i6 = this.sourceCursor;
                this.sourceCursor = i6 + 1;
                i = cArr[i6];
            }
            if (!z2 && (i2 = this.lineEndChar) >= 0) {
                if (i2 == 13 && i == 10) {
                    this.lineEndChar = 10;
                } else {
                    this.lineEndChar = -1;
                    this.lineStart = this.sourceCursor - 1;
                    this.lastLineEnd = this.tokenEnd;
                    this.lineno++;
                }
            }
            if (i <= 127) {
                if (i == 10 || i == 13) {
                    this.lineEndChar = i;
                    return 10;
                }
            } else if (i != 65279) {
                if (!z || !isJSFormatChar(i)) {
                    if (ScriptRuntime.isJSLineTerminator(i)) {
                        this.lineEndChar = i;
                        return 10;
                    }
                }
            }
            return i;
        }
    }

    private int getCharIgnoreLineEnd() throws IOException {
        return getChar(true, true);
    }

    private int getCharIgnoreLineEnd(boolean z) throws IOException {
        return getChar(z, true);
    }

    private void ungetCharIgnoreLineEnd(int i) {
        int[] iArr = this.ungetBuffer;
        int i2 = this.ungetCursor;
        this.ungetCursor = i2 + 1;
        iArr[i2] = i;
        this.cursor--;
    }

    private void skipLine() throws IOException {
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

    @Override // org.mozilla.javascript.Parser.CurrentPositionReporter
    public int getOffset() {
        int i = this.sourceCursor - this.lineStart;
        return this.lineEndChar >= 0 ? i - 1 : i;
    }

    private final int charAt(int i) {
        if (i < 0) {
            return -1;
        }
        String str = this.sourceString;
        int i2 = this.sourceEnd;
        if (str != null) {
            if (i >= i2) {
                return -1;
            }
            return str.charAt(i);
        }
        if (i >= i2) {
            int i3 = this.sourceCursor;
            try {
                if (!fillSourceBuffer()) {
                    return -1;
                }
                i -= i3 - this.sourceCursor;
            } catch (IOException unused) {
                return -1;
            }
        }
        return this.sourceBuffer[i];
    }

    private final String substring(int i, int i2) {
        String str = this.sourceString;
        if (str != null) {
            return str.substring(i, i2);
        }
        return new String(this.sourceBuffer, i, i2 - i);
    }

    @Override // org.mozilla.javascript.Parser.CurrentPositionReporter
    public String getLine() {
        int i;
        int i2 = this.sourceCursor;
        int i3 = this.lineEndChar;
        if (i3 >= 0) {
            i = i2 - 1;
            if (i3 == 10 && charAt(i2 - 2) == 13) {
                i = i2 - 2;
            }
        } else {
            int i4 = i2 - this.lineStart;
            while (true) {
                int iCharAt = charAt(this.lineStart + i4);
                if (iCharAt == -1 || ScriptRuntime.isJSLineTerminator(iCharAt)) {
                    break;
                }
                i4++;
            }
            i = this.lineStart + i4;
        }
        return substring(this.lineStart, i);
    }

    final String getLine(int i, int[] iArr) {
        int i2 = (this.cursor + this.ungetCursor) - i;
        int i3 = this.sourceCursor;
        if (i2 > i3) {
            return null;
        }
        int i4 = 0;
        int i5 = 0;
        while (i2 > 0) {
            int iCharAt = charAt(i3 - 1);
            if (ScriptRuntime.isJSLineTerminator(iCharAt)) {
                if (iCharAt == 10 && charAt(i3 - 2) == 13) {
                    i2--;
                    i3--;
                }
                i4++;
                i5 = i3 - 1;
            }
            i2--;
            i3--;
        }
        int i6 = 0;
        while (true) {
            if (i3 <= 0) {
                i3 = 0;
                break;
            }
            if (ScriptRuntime.isJSLineTerminator(charAt(i3 - 1))) {
                break;
            }
            i3--;
            i6++;
        }
        iArr[0] = (this.lineno - i4) + (this.lineEndChar >= 0 ? 1 : 0);
        iArr[1] = i6;
        if (i4 == 0) {
            return getLine();
        }
        return substring(i3, i5);
    }

    private boolean fillSourceBuffer() throws IOException {
        if (this.sourceString != null) {
            Kit.codeBug();
        }
        if (this.sourceEnd == this.sourceBuffer.length) {
            if (this.lineStart != 0 && !isMarkingComment()) {
                char[] cArr = this.sourceBuffer;
                int i = this.lineStart;
                System.arraycopy(cArr, i, cArr, 0, this.sourceEnd - i);
                int i2 = this.sourceEnd;
                int i3 = this.lineStart;
                this.sourceEnd = i2 - i3;
                this.sourceCursor -= i3;
                this.lineStart = 0;
            } else {
                char[] cArr2 = this.sourceBuffer;
                char[] cArr3 = new char[cArr2.length * 2];
                System.arraycopy(cArr2, 0, cArr3, 0, this.sourceEnd);
                this.sourceBuffer = cArr3;
            }
        }
        Reader reader = this.sourceReader;
        char[] cArr4 = this.sourceBuffer;
        int i4 = this.sourceEnd;
        int i5 = reader.read(cArr4, i4, cArr4.length - i4);
        if (i5 < 0) {
            return false;
        }
        this.sourceEnd += i5;
        return true;
    }

    public int getCursor() {
        return this.cursor;
    }

    public int getTokenBeg() {
        return this.tokenBeg;
    }

    public int getTokenEnd() {
        return this.tokenEnd;
    }

    public int getTokenLength() {
        return this.tokenEnd - this.tokenBeg;
    }

    public Token.CommentType getCommentType() {
        return this.commentType;
    }

    private void markCommentStart() {
        markCommentStart("");
    }

    private void markCommentStart(String str) {
        if (!this.parser.compilerEnv.isRecordingComments() || this.sourceReader == null) {
            return;
        }
        this.commentPrefix = str;
        this.commentCursor = this.sourceCursor - 1;
    }

    private boolean isMarkingComment() {
        return this.commentCursor != -1;
    }

    final String getAndResetCurrentComment() {
        if (this.sourceString != null) {
            if (isMarkingComment()) {
                Kit.codeBug();
            }
            return this.sourceString.substring(this.tokenBeg, this.tokenEnd);
        }
        if (!isMarkingComment()) {
            Kit.codeBug();
        }
        StringBuilder sb = new StringBuilder(this.commentPrefix);
        sb.append(this.sourceBuffer, this.commentCursor, getTokenLength() - this.commentPrefix.length());
        this.commentCursor = -1;
        return sb.toString();
    }

    private static String convertLastCharToHex(String str) {
        int length = str.length() - 1;
        StringBuilder sb = new StringBuilder(str.substring(0, length));
        sb.append("\\u");
        String hexString = Integer.toHexString(str.charAt(length));
        for (int i = 0; i < 4 - hexString.length(); i++) {
            sb.append('0');
        }
        sb.append(hexString);
        return sb.toString();
    }

    @Override // org.mozilla.javascript.Parser.CurrentPositionReporter
    public int getPosition() {
        return this.tokenBeg;
    }

    @Override // org.mozilla.javascript.Parser.CurrentPositionReporter
    public int getLength() {
        return this.tokenEnd - this.tokenBeg;
    }

    public int getTokenColumn() {
        return (this.tokenBeg - this.tokenStartLastLineEnd) + 1;
    }
}
