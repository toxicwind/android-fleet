package org.schabi.newpipe.extractor.utils.jsextractor;

import java.util.Stack;
import org.schabi.newpipe.extractor.exceptions.ParsingException;

/* JADX INFO: loaded from: classes8.dex */
public class Lexer {
    private final EcmaScriptTokenStream stream;
    private final LookBehind lastThree = new LookBehind();
    private final Stack<Brace> braceStack = new Stack<>();
    private final Stack<Paren> parenStack = new Stack<>();

    private static class Paren {
        public final boolean conditional;
        public final boolean funcExpr;

        Paren(boolean z, boolean z2) {
            this.funcExpr = z;
            this.conditional = z2;
        }
    }

    private static class Brace {
        public final boolean isBlock;
        public final Paren paren;

        Brace(boolean z, Paren paren) {
            this.isBlock = z;
            this.paren = paren;
        }
    }

    private static class MetaToken {
        public final int lineno;
        public final Token token;

        MetaToken(Token token, int i) {
            this.token = token;
            this.lineno = i;
        }
    }

    private static class BraceMetaToken extends MetaToken {
        public final Brace brace;

        BraceMetaToken(Token token, int i, Brace brace) {
            super(token, i);
            this.brace = brace;
        }
    }

    private static class ParenMetaToken extends MetaToken {
        public final Paren paren;

        ParenMetaToken(Token token, int i, Paren paren) {
            super(token, i);
            this.paren = paren;
        }
    }

    private static class LookBehind {
        private final MetaToken[] list = new MetaToken[3];

        LookBehind() {
        }

        void push(MetaToken metaToken) {
            int i = 0;
            while (i < 3) {
                MetaToken[] metaTokenArr = this.list;
                MetaToken metaToken2 = metaTokenArr[i];
                metaTokenArr[i] = metaToken;
                i++;
                metaToken = metaToken2;
            }
        }

        MetaToken one() {
            return this.list[0];
        }

        MetaToken two() {
            return this.list[1];
        }

        MetaToken three() {
            return this.list[2];
        }

        boolean oneIs(Token token) {
            MetaToken metaToken = this.list[0];
            return metaToken != null && metaToken.token == token;
        }

        boolean twoIs(Token token) {
            MetaToken metaToken = this.list[1];
            return metaToken != null && metaToken.token == token;
        }

        boolean threeIs(Token token) {
            MetaToken metaToken = this.list[2];
            return metaToken != null && metaToken.token == token;
        }
    }

    public static class ParsedToken {
        public final int end;
        public final int start;
        public final Token token;

        ParsedToken(Token token, int i, int i2) {
            this.token = token;
            this.start = i;
            this.end = i2;
        }
    }

    public Lexer(String str) {
        this.stream = new EcmaScriptTokenStream(str, 0, false);
    }

    public ParsedToken getNextToken() throws ParsingException {
        Token tokenNextToken = this.stream.nextToken();
        if ((tokenNextToken == Token.DIV || tokenNextToken == Token.ASSIGN_DIV) && isRegexStart()) {
            this.stream.readRegExp(tokenNextToken);
            tokenNextToken = Token.REGEXP;
        }
        ParsedToken parsedToken = new ParsedToken(tokenNextToken, this.stream.tokenBeg, this.stream.tokenEnd);
        keepBooks(parsedToken);
        return parsedToken;
    }

    public boolean isBalanced() {
        return this.braceStack.isEmpty() && this.parenStack.isEmpty();
    }

    /* JADX INFO: renamed from: org.schabi.newpipe.extractor.utils.jsextractor.Lexer$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$schabi$newpipe$extractor$utils$jsextractor$Token;

        static {
            int[] iArr = new int[Token.values().length];
            $SwitchMap$org$schabi$newpipe$extractor$utils$jsextractor$Token = iArr;
            try {
                iArr[Token.LP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$schabi$newpipe$extractor$utils$jsextractor$Token[Token.LC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$schabi$newpipe$extractor$utils$jsextractor$Token[Token.RP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$schabi$newpipe$extractor$utils$jsextractor$Token[Token.RC.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$schabi$newpipe$extractor$utils$jsextractor$Token[Token.CASE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$schabi$newpipe$extractor$utils$jsextractor$Token[Token.COLON.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$schabi$newpipe$extractor$utils$jsextractor$Token[Token.RETURN.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$org$schabi$newpipe$extractor$utils$jsextractor$Token[Token.YIELD.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$org$schabi$newpipe$extractor$utils$jsextractor$Token[Token.YIELD_STAR.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    void keepBooks(ParsedToken parsedToken) throws ParsingException {
        if (parsedToken.token.isPunct) {
            int i = AnonymousClass1.$SwitchMap$org$schabi$newpipe$extractor$utils$jsextractor$Token[parsedToken.token.ordinal()];
            if (i == 1) {
                handleOpenParenBooks();
                return;
            }
            if (i == 2) {
                handleOpenBraceBooks();
                return;
            } else if (i == 3) {
                handleCloseParenBooks(parsedToken.start);
                return;
            } else if (i == 4) {
                handleCloseBraceBooks(parsedToken.start);
                return;
            }
        }
        if (parsedToken.token != Token.COMMENT) {
            this.lastThree.push(new MetaToken(parsedToken.token, this.stream.lineno));
        }
    }

    void handleOpenParenBooks() {
        boolean zOneIs = this.lastThree.oneIs(Token.FUNCTION);
        LookBehind lookBehind = this.lastThree;
        Paren paren = new Paren(!zOneIs ? !(lookBehind.twoIs(Token.FUNCTION) && this.lastThree.three() != null && checkForExpression(this.lastThree.three().token)) : lookBehind.two() == null || !checkForExpression(this.lastThree.two().token), this.lastThree.one() != null && this.lastThree.one().token.isConditional());
        this.parenStack.push(paren);
        this.lastThree.push(new ParenMetaToken(Token.LP, this.stream.lineno, paren));
    }

    void handleOpenBraceBooks() {
        boolean z = true;
        if (this.lastThree.one() != null) {
            int i = AnonymousClass1.$SwitchMap$org$schabi$newpipe$extractor$utils$jsextractor$Token[this.lastThree.one().token.ordinal()];
            if (i != 1 && i != 2) {
                switch (i) {
                    case 5:
                        z = false;
                        break;
                    case 6:
                        if (this.braceStack.isEmpty() || !this.braceStack.lastElement().isBlock) {
                            z = false;
                        }
                        break;
                    case 7:
                    case 8:
                    case 9:
                        if (this.lastThree.two() == null || this.lastThree.two().lineno == this.stream.lineno) {
                            z = false;
                        }
                        break;
                    default:
                        z = true ^ this.lastThree.one().token.isOp;
                        break;
                }
            } else {
                z = false;
            }
        }
        Brace brace = new Brace(z, ((this.lastThree.one() instanceof ParenMetaToken) && this.lastThree.one().token == Token.RP) ? ((ParenMetaToken) this.lastThree.one()).paren : null);
        this.braceStack.push(brace);
        this.lastThree.push(new BraceMetaToken(Token.LC, this.stream.lineno, brace));
    }

    void handleCloseParenBooks(int i) throws ParsingException {
        if (this.parenStack.isEmpty()) {
            throw new ParsingException("unmatched closing paren at " + i);
        }
        this.lastThree.push(new ParenMetaToken(Token.RP, this.stream.lineno, this.parenStack.pop()));
    }

    void handleCloseBraceBooks(int i) throws ParsingException {
        if (this.braceStack.isEmpty()) {
            throw new ParsingException("unmatched closing brace at " + i);
        }
        this.lastThree.push(new BraceMetaToken(Token.RC, this.stream.lineno, this.braceStack.pop()));
    }

    boolean checkForExpression(Token token) {
        return token.isOp || token == Token.RETURN || token == Token.CASE;
    }

    boolean isRegexStart() {
        if (this.lastThree.one() == null) {
            return true;
        }
        Token token = this.lastThree.one().token;
        if (token.isKeyw) {
            return token != Token.THIS;
        }
        if (token == Token.RP && (this.lastThree.one() instanceof ParenMetaToken)) {
            return ((ParenMetaToken) this.lastThree.one()).paren.conditional;
        }
        if (token != Token.RC || !(this.lastThree.one() instanceof BraceMetaToken)) {
            return token.isPunct && token != Token.RB;
        }
        BraceMetaToken braceMetaToken = (BraceMetaToken) this.lastThree.one();
        if (!braceMetaToken.brace.isBlock) {
            return false;
        }
        if (braceMetaToken.brace.paren != null) {
            return !braceMetaToken.brace.paren.funcExpr;
        }
        return true;
    }
}
