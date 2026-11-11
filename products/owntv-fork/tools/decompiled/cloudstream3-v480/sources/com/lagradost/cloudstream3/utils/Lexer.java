package com.lagradost.cloudstream3.utils;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.lagradost.cloudstream3.actions.temp.CloudStreamPackage;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import org.slf4j.Marker;

/* JADX INFO: compiled from: JsInterpreter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\f\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0011H\u0002J\b\u0010\u0013\u001a\u00020\u0011H\u0002J\u0010\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0016H\u0002J\b\u0010\u0019\u001a\u00020\u0011H\u0002J\b\u0010\u001a\u001a\u00020\u0011H\u0002J\u0010\u0010\u001b\u001a\u00020\u000e2\b\b\u0002\u0010\u001c\u001a\u00020\u0007J\u0006\u0010\u001d\u001a\u00020\u000eJ\u000e\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020 J\u000e\u0010!\u001a\u00020\"2\u0006\u0010\u001f\u001a\u00020 R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/lagradost/cloudstream3/utils/Lexer;", "", "src", "", "<init>", "(Ljava/lang/String;)V", CloudStreamPackage.POSITION_EXTRA, "", "getPos", "()I", "setPos", "(I)V", "tokens", "", "Lcom/lagradost/cloudstream3/utils/Token;", "idx", "tokenize", "", "skipWhitespaceAndComments", "readNumber", "readString", "quote", "", "unescape", "c", "readIdent", "readOp", "peek", TypedValues.CycleType.S_WAVE_OFFSET, "consume", "expect", "t", "Lcom/lagradost/cloudstream3/utils/TT;", "matchIf", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class Lexer {
    private int idx;
    private int pos;
    private final String src;
    private final List<Token> tokens;

    private final char unescape(char c) {
        if (c == '\"') {
            return '\"';
        }
        if (c == '\'') {
            return '\'';
        }
        if (c == '\\') {
            return '\\';
        }
        if (c == '`') {
            return '`';
        }
        if (c == 'b') {
            return '\b';
        }
        if (c == 'n') {
            return '\n';
        }
        if (c == 'r') {
            return '\r';
        }
        if (c != 't') {
            return c;
        }
        return '\t';
    }

    public Lexer(String src) {
        Intrinsics.checkNotNullParameter(src, "src");
        this.src = src;
        this.tokens = new ArrayList();
        tokenize();
    }

    public final int getPos() {
        return this.pos;
    }

    public final void setPos(int i) {
        this.pos = i;
    }

    private final void tokenize() {
        while (this.pos < this.src.length()) {
            skipWhitespaceAndComments();
            if (this.pos >= this.src.length()) {
                break;
            }
            char cCharAt = this.src.charAt(this.pos);
            if (Character.isDigit(cCharAt) || (cCharAt == '.' && this.pos + 1 < this.src.length() && Character.isDigit(this.src.charAt(this.pos + 1)))) {
                readNumber();
            } else if (cCharAt == '\"' || cCharAt == '\'' || cCharAt == '`') {
                readString(cCharAt);
            } else if (Character.isLetter(cCharAt) || cCharAt == '_' || cCharAt == '$') {
                readIdent();
            } else {
                readOp();
            }
        }
        this.tokens.add(new Token(TT.EOF, "", this.pos));
    }

    private final void skipWhitespaceAndComments() {
        while (this.pos < this.src.length()) {
            char cCharAt = this.src.charAt(this.pos);
            if (CharsKt.isWhitespace(cCharAt)) {
                this.pos++;
            } else {
                if (cCharAt != '/' || this.pos + 1 >= this.src.length()) {
                    return;
                }
                char cCharAt2 = this.src.charAt(this.pos + 1);
                if (cCharAt2 == '*') {
                    int iIndexOf$default = StringsKt.indexOf$default((CharSequence) this.src, "*/", this.pos + 2, false, 4, (Object) null);
                    this.pos = iIndexOf$default < 0 ? this.src.length() : iIndexOf$default + 2;
                } else {
                    if (cCharAt2 != '/') {
                        return;
                    }
                    int iIndexOf$default2 = StringsKt.indexOf$default((CharSequence) this.src, '\n', this.pos, false, 4, (Object) null);
                    this.pos = iIndexOf$default2 < 0 ? this.src.length() : iIndexOf$default2 + 1;
                }
            }
        }
    }

    private final void readNumber() {
        int i = this.pos;
        if (i + 1 < this.src.length() && this.src.charAt(this.pos) == '0' && (this.src.charAt(this.pos + 1) == 'x' || this.src.charAt(this.pos + 1) == 'X')) {
            this.pos += 2;
            while (this.pos < this.src.length() && Character.isLetterOrDigit(this.src.charAt(this.pos))) {
                this.pos++;
            }
        } else {
            while (this.pos < this.src.length() && (Character.isDigit(this.src.charAt(this.pos)) || this.src.charAt(this.pos) == '.')) {
                this.pos++;
            }
            if (this.pos < this.src.length() && (this.src.charAt(this.pos) == 'e' || this.src.charAt(this.pos) == 'E')) {
                int i2 = this.pos + 1;
                this.pos = i2;
                if (i2 < this.src.length() && (this.src.charAt(this.pos) == '+' || this.src.charAt(this.pos) == '-')) {
                    this.pos++;
                }
                while (this.pos < this.src.length() && Character.isDigit(this.src.charAt(this.pos))) {
                    this.pos++;
                }
            }
        }
        List<Token> list = this.tokens;
        TT tt = TT.NUMBER;
        String strSubstring = this.src.substring(i, this.pos);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        list.add(new Token(tt, strSubstring, i));
    }

    private final void readString(char quote) {
        int i = this.pos;
        this.pos = i + 1;
        StringBuilder sb = new StringBuilder();
        if (quote == '`') {
            while (this.pos < this.src.length() && this.src.charAt(this.pos) != '`') {
                if (this.src.charAt(this.pos) == '\\' && this.pos + 1 < this.src.length()) {
                    String str = this.src;
                    int i2 = this.pos + 1;
                    this.pos = i2;
                    sb.append(unescape(str.charAt(i2)));
                    int i3 = this.pos;
                    this.pos = i3 + 1;
                    Integer.valueOf(i3);
                } else {
                    String str2 = this.src;
                    int i4 = this.pos;
                    this.pos = i4 + 1;
                    sb.append(str2.charAt(i4));
                }
            }
            if (this.pos < this.src.length()) {
                this.pos++;
            }
        } else {
            while (this.pos < this.src.length() && this.src.charAt(this.pos) != quote) {
                if (this.src.charAt(this.pos) == '\\' && this.pos + 1 < this.src.length()) {
                    String str3 = this.src;
                    int i5 = this.pos + 1;
                    this.pos = i5;
                    sb.append(unescape(str3.charAt(i5)));
                    int i6 = this.pos;
                    this.pos = i6 + 1;
                    Integer.valueOf(i6);
                } else {
                    String str4 = this.src;
                    int i7 = this.pos;
                    this.pos = i7 + 1;
                    sb.append(str4.charAt(i7));
                }
            }
            if (this.pos < this.src.length()) {
                this.pos++;
            }
        }
        List<Token> list = this.tokens;
        TT tt = TT.STRING;
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        list.add(new Token(tt, string, i));
    }

    private final void readIdent() {
        int i = this.pos;
        while (this.pos < this.src.length() && (Character.isLetterOrDigit(this.src.charAt(this.pos)) || this.src.charAt(this.pos) == '_' || this.src.charAt(this.pos) == '$')) {
            this.pos++;
        }
        List<Token> list = this.tokens;
        TT tt = TT.IDENT;
        String strSubstring = this.src.substring(i, this.pos);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        list.add(new Token(tt, strSubstring, i));
    }

    private final void readOp() {
        Token op$adv;
        TT tt;
        TT tt2;
        TT tt3;
        TT tt4;
        int i = this.pos;
        String str = this.src;
        this.pos = i + 1;
        char cCharAt = str.charAt(i);
        char cCharAt2 = this.pos < this.src.length() ? this.src.charAt(this.pos) : (char) 0;
        char cCharAt3 = this.pos + 1 < this.src.length() ? this.src.charAt(this.pos + 1) : (char) 0;
        if (cCharAt != '!') {
            if (cCharAt != '[') {
                if (cCharAt != '%') {
                    if (cCharAt != '&') {
                        if (cCharAt != ']') {
                            if (cCharAt == '^') {
                                op$adv = new Token(TT.CARET, "^", i);
                            } else {
                                switch (cCharAt) {
                                    case '(':
                                        op$adv = new Token(TT.LPAREN, "(", i);
                                        break;
                                    case ')':
                                        op$adv = new Token(TT.RPAREN, ")", i);
                                        break;
                                    case '*':
                                        if (cCharAt2 == '*' && cCharAt3 == '=') {
                                            op$adv = readOp$adv(this, i, TT.POWEQ, 2);
                                        } else if (cCharAt2 == '*') {
                                            op$adv = readOp$adv(this, i, TT.POW, 1);
                                        } else if (cCharAt2 == '=') {
                                            op$adv = readOp$adv(this, i, TT.STAREQ, 1);
                                        } else {
                                            op$adv = new Token(TT.STAR, Marker.ANY_MARKER, i);
                                        }
                                        break;
                                    case '+':
                                        if (cCharAt2 == '+') {
                                            tt = TT.PLUSPLUS;
                                        } else if (cCharAt2 != '=') {
                                            op$adv = new Token(TT.PLUS, Marker.ANY_NON_NULL_MARKER, i);
                                        } else {
                                            tt = TT.PLUSEQ;
                                        }
                                        op$adv = readOp$adv(this, i, tt, 1);
                                        break;
                                    case ',':
                                        op$adv = new Token(TT.COMMA, ",", i);
                                        break;
                                    case '-':
                                        if (cCharAt2 == '-') {
                                            tt2 = TT.MINUSMINUS;
                                        } else if (cCharAt2 != '=') {
                                            op$adv = new Token(TT.MINUS, "-", i);
                                        } else {
                                            tt2 = TT.MINUSEQ;
                                        }
                                        op$adv = readOp$adv(this, i, tt2, 1);
                                        break;
                                    case '.':
                                        op$adv = new Token(TT.DOT, ".", i);
                                        break;
                                    case '/':
                                        op$adv = cCharAt2 != '=' ? new Token(TT.SLASH, "/", i) : readOp$adv(this, i, TT.SLASHEQ, 1);
                                        break;
                                    default:
                                        switch (cCharAt) {
                                            case ':':
                                                op$adv = new Token(TT.COLON, ":", i);
                                                break;
                                            case ';':
                                                op$adv = new Token(TT.SEMI, ";", i);
                                                break;
                                            case '<':
                                                if (cCharAt2 == '<') {
                                                    tt3 = TT.LSHIFT;
                                                } else if (cCharAt2 != '=') {
                                                    op$adv = new Token(TT.LT, "<", i);
                                                } else {
                                                    tt3 = TT.LTEQ;
                                                }
                                                op$adv = readOp$adv(this, i, tt3, 1);
                                                break;
                                            case '=':
                                                if (cCharAt2 == '=' && cCharAt3 == '=') {
                                                    op$adv = readOp$adv(this, i, TT.EQEQEQ, 2);
                                                } else {
                                                    op$adv = cCharAt2 != '=' ? new Token(TT.EQ, "=", i) : readOp$adv(this, i, TT.EQEQ, 1);
                                                }
                                                break;
                                            case '>':
                                                if (cCharAt2 == '>' && cCharAt3 == '>') {
                                                    op$adv = readOp$adv(this, i, TT.URSHIFT, 2);
                                                } else {
                                                    if (cCharAt2 == '>') {
                                                        tt4 = TT.RSHIFT;
                                                    } else if (cCharAt2 != '=') {
                                                        op$adv = new Token(TT.GT, ">", i);
                                                    } else {
                                                        tt4 = TT.GTEQ;
                                                    }
                                                    op$adv = readOp$adv(this, i, tt4, 1);
                                                }
                                                break;
                                            case '?':
                                                op$adv = new Token(TT.QUESTION, "?", i);
                                                break;
                                            default:
                                                switch (cCharAt) {
                                                    case '{':
                                                        op$adv = new Token(TT.LBRACE, "{", i);
                                                        break;
                                                    case '|':
                                                        op$adv = cCharAt2 != '|' ? new Token(TT.PIPE, "|", i) : readOp$adv(this, i, TT.OR, 1);
                                                        break;
                                                    case '}':
                                                        op$adv = new Token(TT.RBRACE, "}", i);
                                                        break;
                                                    case '~':
                                                        op$adv = new Token(TT.TILDE, "~", i);
                                                        break;
                                                    default:
                                                        op$adv = new Token(TT.SEMI, "", i);
                                                        break;
                                                }
                                                break;
                                        }
                                        break;
                                }
                            }
                        } else {
                            op$adv = new Token(TT.RBRACKET, "]", i);
                        }
                    } else {
                        op$adv = cCharAt2 == '&' ? readOp$adv(this, i, TT.AND, 1) : new Token(TT.AMP, "&", i);
                    }
                } else {
                    op$adv = cCharAt2 == '=' ? readOp$adv(this, i, TT.PERCENTEQ, 1) : new Token(TT.PERCENT, "%", i);
                }
            } else {
                op$adv = new Token(TT.LBRACKET, "[", i);
            }
        } else if (cCharAt2 == '=' && cCharAt3 == '=') {
            op$adv = readOp$adv(this, i, TT.NEQEQ, 2);
        } else {
            op$adv = cCharAt2 == '=' ? readOp$adv(this, i, TT.NEQ, 1) : new Token(TT.NOT, "!", i);
        }
        this.tokens.add(op$adv);
    }

    private static final Token readOp$adv(Lexer lexer, int i, TT tt, int i2) {
        lexer.pos += i2;
        return new Token(tt, "", i);
    }

    static /* synthetic */ Token readOp$adv$default(Lexer lexer, int i, TT tt, int i2, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            i2 = 0;
        }
        return readOp$adv(lexer, i, tt, i2);
    }

    public static /* synthetic */ Token peek$default(Lexer lexer, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return lexer.peek(i);
    }

    public final Token peek(int offset) {
        List<Token> list = this.tokens;
        int i = this.idx + offset;
        return (i < 0 || i >= list.size()) ? (Token) CollectionsKt.last((List) this.tokens) : list.get(i);
    }

    public final Token consume() {
        List<Token> list = this.tokens;
        int i = this.idx;
        this.idx = i + 1;
        return list.get(i);
    }

    public final Token expect(TT t) {
        Intrinsics.checkNotNullParameter(t, "t");
        Token tokenConsume = consume();
        if (tokenConsume.getType() == t) {
            return tokenConsume;
        }
        throw new RuntimeException("Expected " + t + " got " + tokenConsume.getType() + " ('" + tokenConsume.getRaw() + "') at " + tokenConsume.getPos());
    }

    public final boolean matchIf(TT t) {
        Intrinsics.checkNotNullParameter(t, "t");
        if (peek$default(this, 0, 1, null).getType() != t) {
            return false;
        }
        consume();
        return true;
    }
}
