package com.lagradost.cloudstream3.utils;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.slf4j.Marker;

/* JADX INFO: compiled from: JsInterpreter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007J\b\u0010\t\u001a\u00020\bH\u0002J\b\u0010\n\u001a\u00020\u000bH\u0002J\b\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\bH\u0002J\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0007H\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\bH\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\bH\u0002J\b\u0010\u001d\u001a\u00020\bH\u0002J\b\u0010\u001e\u001a\u00020\bH\u0002J\b\u0010\u001f\u001a\u00020\bH\u0002J\b\u0010 \u001a\u00020\bH\u0002J\b\u0010!\u001a\u00020\bH\u0002J\b\u0010\"\u001a\u00020\bH\u0002J\b\u0010#\u001a\u00020\bH\u0002J\b\u0010$\u001a\u00020\bH\u0002J\b\u0010%\u001a\u00020\bH\u0002J\b\u0010&\u001a\u00020\bH\u0002J\b\u0010'\u001a\u00020\bH\u0002J\b\u0010(\u001a\u00020\bH\u0002J\b\u0010)\u001a\u00020\bH\u0002J\b\u0010*\u001a\u00020\bH\u0002JG\u0010+\u001a\u00020\b2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\b0-2*\u0010.\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020\u0010000/\"\u000e\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020\u001000H\u0002¢\u0006\u0002\u00102J\b\u00103\u001a\u00020\bH\u0002J\b\u00104\u001a\u00020\bH\u0002J\b\u00105\u001a\u00020\bH\u0002J\b\u00106\u001a\u00020\bH\u0002J\u000e\u00107\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0002J\b\u00108\u001a\u00020\bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lcom/lagradost/cloudstream3/utils/Parser;", "", "lex", "Lcom/lagradost/cloudstream3/utils/Lexer;", "<init>", "(Lcom/lagradost/cloudstream3/utils/Lexer;)V", "parseProgram", "", "Lcom/lagradost/cloudstream3/utils/Node;", "parseStatement", "parseBlock", "Lcom/lagradost/cloudstream3/utils/BlockStmt;", "parseVarDecl", "Lcom/lagradost/cloudstream3/utils/VarDecl;", "parseFunctionDecl", "parseParams", "", "parseReturn", "Lcom/lagradost/cloudstream3/utils/ReturnStmt;", "parseIf", "Lcom/lagradost/cloudstream3/utils/IfStmt;", "parseWhile", "Lcom/lagradost/cloudstream3/utils/WhileStmt;", "parseFor", "parseTryCatch", "Lcom/lagradost/cloudstream3/utils/TryCatch;", "parseThrow", "Lcom/lagradost/cloudstream3/utils/ThrowStmt;", "parseExprStmt", "parseSeq", "parseAssign", "parseTernary", "parseOr", "parseAnd", "parseBitor", "parseBitxor", "parseBitand", "parseEq", "parseRel", "parseShift", "parseAdd", "parseMul", "parsePow", "parseBin", "next", "Lkotlin/Function0;", "ops", "", "Lkotlin/Pair;", "Lcom/lagradost/cloudstream3/utils/TT;", "(Lkotlin/jvm/functions/Function0;[Lkotlin/Pair;)Lcom/lagradost/cloudstream3/utils/Node;", "parseUnary", "parseNew", "parsePostfix", "parseCall", "parseArgs", "parsePrimary", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class Parser {
    private final Lexer lex;

    /* JADX INFO: compiled from: JsInterpreter.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TT.values().length];
            try {
                iArr[TT.LBRACE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TT.SEMI.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TT.IDENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[TT.EQ.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[TT.PLUSEQ.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[TT.MINUSEQ.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[TT.STAREQ.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[TT.SLASHEQ.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[TT.PERCENTEQ.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[TT.POWEQ.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[TT.MINUS.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[TT.PLUS.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[TT.NOT.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[TT.TILDE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr[TT.PLUSPLUS.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr[TT.MINUSMINUS.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr[TT.DOT.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr[TT.LBRACKET.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                iArr[TT.LPAREN.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                iArr[TT.STRING.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                iArr[TT.NUMBER.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public Parser(Lexer lex) {
        Intrinsics.checkNotNullParameter(lex, "lex");
        this.lex = lex;
    }

    public final List<Node> parseProgram() {
        ArrayList arrayList = new ArrayList();
        while (Lexer.peek$default(this.lex, 0, 1, null).getType() != TT.EOF) {
            arrayList.add(parseStatement());
        }
        return arrayList;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x006c, code lost:
    
        if (r0.equals("const") == false) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0091, code lost:
    
        if (r0.equals("var") == false) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00ac, code lost:
    
        if (r0.equals("let") == false) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00b5, code lost:
    
        return parseVarDecl();
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final com.lagradost.cloudstream3.utils.Node parseStatement() {
        /*
            Method dump skipped, instruction units count: 352
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.utils.Parser.parseStatement():com.lagradost.cloudstream3.utils.Node");
    }

    private final BlockStmt parseBlock() {
        this.lex.expect(TT.LBRACE);
        ArrayList arrayList = new ArrayList();
        while (Lexer.peek$default(this.lex, 0, 1, null).getType() != TT.RBRACE && Lexer.peek$default(this.lex, 0, 1, null).getType() != TT.EOF) {
            arrayList.add(parseStatement());
        }
        this.lex.expect(TT.RBRACE);
        return new BlockStmt(arrayList);
    }

    private final VarDecl parseVarDecl() {
        this.lex.consume();
        ArrayList arrayList = new ArrayList();
        do {
            arrayList.add(TuplesKt.to(this.lex.expect(TT.IDENT).getRaw(), this.lex.matchIf(TT.EQ) ? parseAssign() : null));
        } while (this.lex.matchIf(TT.COMMA));
        this.lex.matchIf(TT.SEMI);
        return new VarDecl(arrayList);
    }

    private final Node parseFunctionDecl() {
        this.lex.consume();
        String raw = Lexer.peek$default(this.lex, 0, 1, null).getType() == TT.IDENT ? this.lex.consume().getRaw() : null;
        List<String> params = parseParams();
        List<Node> stmts = parseBlock().getStmts();
        return raw != null ? new VarDecl(CollectionsKt.listOf(TuplesKt.to(raw, new FuncExpr(raw, params, stmts)))) : new FuncExpr(raw, params, stmts);
    }

    private final List<String> parseParams() {
        this.lex.expect(TT.LPAREN);
        ArrayList arrayList = new ArrayList();
        while (Lexer.peek$default(this.lex, 0, 1, null).getType() != TT.RPAREN && Lexer.peek$default(this.lex, 0, 1, null).getType() != TT.EOF) {
            arrayList.add(this.lex.expect(TT.IDENT).getRaw());
            if (!this.lex.matchIf(TT.COMMA)) {
                break;
            }
        }
        this.lex.expect(TT.RPAREN);
        return arrayList;
    }

    private final ReturnStmt parseReturn() {
        this.lex.consume();
        Node assign = null;
        if (Lexer.peek$default(this.lex, 0, 1, null).getType() != TT.SEMI && Lexer.peek$default(this.lex, 0, 1, null).getType() != TT.RBRACE && Lexer.peek$default(this.lex, 0, 1, null).getType() != TT.EOF) {
            assign = parseAssign();
        }
        this.lex.matchIf(TT.SEMI);
        return new ReturnStmt(assign);
    }

    private final IfStmt parseIf() {
        this.lex.consume();
        this.lex.expect(TT.LPAREN);
        Node assign = parseAssign();
        this.lex.expect(TT.RPAREN);
        Node statement = parseStatement();
        Node statement2 = null;
        if (Lexer.peek$default(this.lex, 0, 1, null).getType() == TT.IDENT && Intrinsics.areEqual(Lexer.peek$default(this.lex, 0, 1, null).getRaw(), "else")) {
            this.lex.consume();
            statement2 = parseStatement();
        }
        return new IfStmt(assign, statement, statement2);
    }

    private final WhileStmt parseWhile() {
        this.lex.consume();
        this.lex.expect(TT.LPAREN);
        Node assign = parseAssign();
        this.lex.expect(TT.RPAREN);
        return new WhileStmt(assign, parseStatement());
    }

    private final Node parseFor() {
        Node assign;
        VarDecl varDecl;
        this.lex.consume();
        this.lex.expect(TT.LPAREN);
        if (Lexer.peek$default(this.lex, 0, 1, null).getType() == TT.IDENT && (Intrinsics.areEqual(Lexer.peek$default(this.lex, 0, 1, null).getRaw(), "var") || Intrinsics.areEqual(Lexer.peek$default(this.lex, 0, 1, null).getRaw(), "let") || Intrinsics.areEqual(Lexer.peek$default(this.lex, 0, 1, null).getRaw(), "const"))) {
            Lexer.peek$default(this.lex, 0, 1, null);
            this.lex.consume();
            String raw = this.lex.expect(TT.IDENT).getRaw();
            if (Lexer.peek$default(this.lex, 0, 1, null).getType() == TT.IDENT && Intrinsics.areEqual(Lexer.peek$default(this.lex, 0, 1, null).getRaw(), "in")) {
                this.lex.consume();
                Node assign2 = parseAssign();
                this.lex.expect(TT.RPAREN);
                return new ForInStmt(raw, assign2, parseStatement());
            }
            if (Lexer.peek$default(this.lex, 0, 1, null).getType() != TT.SEMI) {
                varDecl = new VarDecl(CollectionsKt.listOf(TuplesKt.to(raw, this.lex.matchIf(TT.EQ) ? parseAssign() : null)));
            } else {
                varDecl = new VarDecl(CollectionsKt.listOf(TuplesKt.to(raw, null)));
            }
            this.lex.matchIf(TT.SEMI);
            Node assign3 = Lexer.peek$default(this.lex, 0, 1, null).getType() != TT.SEMI ? parseAssign() : null;
            this.lex.matchIf(TT.SEMI);
            assign = Lexer.peek$default(this.lex, 0, 1, null).getType() != TT.RPAREN ? parseAssign() : null;
            this.lex.expect(TT.RPAREN);
            return new ForStmt(varDecl, assign3, assign, parseStatement());
        }
        Node assign4 = Lexer.peek$default(this.lex, 0, 1, null).getType() != TT.SEMI ? parseAssign() : null;
        this.lex.matchIf(TT.SEMI);
        Node assign5 = Lexer.peek$default(this.lex, 0, 1, null).getType() != TT.SEMI ? parseAssign() : null;
        this.lex.matchIf(TT.SEMI);
        assign = Lexer.peek$default(this.lex, 0, 1, null).getType() != TT.RPAREN ? parseAssign() : null;
        this.lex.expect(TT.RPAREN);
        return new ForStmt(assign4, assign5, assign, parseStatement());
    }

    private final TryCatch parseTryCatch() {
        String raw;
        List<Node> stmts;
        this.lex.consume();
        List<Node> stmts2 = parseBlock().getStmts();
        List<Node> stmts3 = null;
        if (Lexer.peek$default(this.lex, 0, 1, null).getType() == TT.IDENT && Intrinsics.areEqual(Lexer.peek$default(this.lex, 0, 1, null).getRaw(), "catch")) {
            this.lex.consume();
            if (this.lex.matchIf(TT.LPAREN)) {
                raw = this.lex.expect(TT.IDENT).getRaw();
                this.lex.expect(TT.RPAREN);
            } else {
                raw = null;
            }
            stmts = parseBlock().getStmts();
        } else {
            raw = null;
            stmts = null;
        }
        if (Lexer.peek$default(this.lex, 0, 1, null).getType() == TT.IDENT && Intrinsics.areEqual(Lexer.peek$default(this.lex, 0, 1, null).getRaw(), "finally")) {
            this.lex.consume();
            stmts3 = parseBlock().getStmts();
        }
        return new TryCatch(stmts2, raw, stmts, stmts3);
    }

    private final ThrowStmt parseThrow() {
        this.lex.consume();
        Node assign = parseAssign();
        this.lex.matchIf(TT.SEMI);
        return new ThrowStmt(assign);
    }

    private final Node parseExprStmt() {
        Node seq = parseSeq();
        this.lex.matchIf(TT.SEMI);
        return new ExprStmt(seq);
    }

    private final Node parseSeq() {
        Node assign = parseAssign();
        if (Lexer.peek$default(this.lex, 0, 1, null).getType() != TT.COMMA) {
            return assign;
        }
        List listMutableListOf = CollectionsKt.mutableListOf(assign);
        while (this.lex.matchIf(TT.COMMA)) {
            listMutableListOf.add(parseAssign());
        }
        return new SeqExpr(listMutableListOf);
    }

    private final Node parseAssign() {
        String str;
        Node ternary = parseTernary();
        switch (WhenMappings.$EnumSwitchMapping$0[Lexer.peek$default(this.lex, 0, 1, null).getType().ordinal()]) {
            case 4:
                str = "=";
                break;
            case 5:
                str = "+=";
                break;
            case 6:
                str = "-=";
                break;
            case 7:
                str = "*=";
                break;
            case 8:
                str = "/=";
                break;
            case 9:
                str = "%=";
                break;
            case 10:
                str = "**=";
                break;
            default:
                return ternary;
        }
        this.lex.consume();
        return new AssignExpr(str, ternary, parseAssign());
    }

    private final Node parseTernary() {
        Node or = parseOr();
        if (!this.lex.matchIf(TT.QUESTION)) {
            return or;
        }
        Node assign = parseAssign();
        this.lex.expect(TT.COLON);
        return new CondExpr(or, assign, parseAssign());
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.Parser$parseOr$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: JsInterpreter.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C09431 extends FunctionReferenceImpl implements Function0<Node> {
        C09431(Object obj) {
            super(0, obj, Parser.class, "parseAnd", "parseAnd()Lcom/lagradost/cloudstream3/utils/Node;", 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Node invoke() {
            return ((Parser) this.receiver).parseAnd();
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.Parser$parseAnd$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: JsInterpreter.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C09371 extends FunctionReferenceImpl implements Function0<Node> {
        C09371(Object obj) {
            super(0, obj, Parser.class, "parseBitor", "parseBitor()Lcom/lagradost/cloudstream3/utils/Node;", 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Node invoke() {
            return ((Parser) this.receiver).parseBitor();
        }
    }

    private final Node parseOr() {
        return parseBin(new C09431(this), TuplesKt.to(TT.OR, "||"));
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.Parser$parseBitor$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: JsInterpreter.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C09391 extends FunctionReferenceImpl implements Function0<Node> {
        C09391(Object obj) {
            super(0, obj, Parser.class, "parseBitxor", "parseBitxor()Lcom/lagradost/cloudstream3/utils/Node;", 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Node invoke() {
            return ((Parser) this.receiver).parseBitxor();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Node parseAnd() {
        return parseBin(new C09371(this), TuplesKt.to(TT.AND, "&&"));
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.Parser$parseBitxor$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: JsInterpreter.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C09401 extends FunctionReferenceImpl implements Function0<Node> {
        C09401(Object obj) {
            super(0, obj, Parser.class, "parseBitand", "parseBitand()Lcom/lagradost/cloudstream3/utils/Node;", 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Node invoke() {
            return ((Parser) this.receiver).parseBitand();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Node parseBitor() {
        return parseBin(new C09391(this), TuplesKt.to(TT.PIPE, "|"));
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.Parser$parseBitand$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: JsInterpreter.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C09381 extends FunctionReferenceImpl implements Function0<Node> {
        C09381(Object obj) {
            super(0, obj, Parser.class, "parseEq", "parseEq()Lcom/lagradost/cloudstream3/utils/Node;", 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Node invoke() {
            return ((Parser) this.receiver).parseEq();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Node parseBitxor() {
        return parseBin(new C09401(this), TuplesKt.to(TT.CARET, "^"));
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.Parser$parseEq$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: JsInterpreter.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C09411 extends FunctionReferenceImpl implements Function0<Node> {
        C09411(Object obj) {
            super(0, obj, Parser.class, "parseRel", "parseRel()Lcom/lagradost/cloudstream3/utils/Node;", 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Node invoke() {
            return ((Parser) this.receiver).parseRel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Node parseBitand() {
        return parseBin(new C09381(this), TuplesKt.to(TT.AMP, "&"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Node parseEq() {
        return parseBin(new C09411(this), TuplesKt.to(TT.EQEQ, "=="), TuplesKt.to(TT.EQEQEQ, "==="), TuplesKt.to(TT.NEQ, "!="), TuplesKt.to(TT.NEQEQ, "!=="));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Node parseRel() {
        BinExpr binExpr;
        Node shift = parseShift();
        while (true) {
            TT type = Lexer.peek$default(this.lex, 0, 1, null).getType();
            TT tt = TT.LT;
            Lexer lexer = this.lex;
            if (type == tt) {
                lexer.consume();
                binExpr = new BinExpr("<", shift, parseShift());
            } else {
                TT type2 = Lexer.peek$default(lexer, 0, 1, null).getType();
                TT tt2 = TT.LTEQ;
                Lexer lexer2 = this.lex;
                if (type2 == tt2) {
                    lexer2.consume();
                    binExpr = new BinExpr("<=", shift, parseShift());
                } else {
                    TT type3 = Lexer.peek$default(lexer2, 0, 1, null).getType();
                    TT tt3 = TT.GT;
                    Lexer lexer3 = this.lex;
                    if (type3 == tt3) {
                        lexer3.consume();
                        binExpr = new BinExpr(">", shift, parseShift());
                    } else {
                        TT type4 = Lexer.peek$default(lexer3, 0, 1, null).getType();
                        TT tt4 = TT.GTEQ;
                        Lexer lexer4 = this.lex;
                        if (type4 == tt4) {
                            lexer4.consume();
                            binExpr = new BinExpr(">=", shift, parseShift());
                        } else if (Lexer.peek$default(lexer4, 0, 1, null).getType() == TT.IDENT && Intrinsics.areEqual(Lexer.peek$default(this.lex, 0, 1, null).getRaw(), "in")) {
                            this.lex.consume();
                            binExpr = new BinExpr("in", shift, parseShift());
                        } else {
                            if (Lexer.peek$default(this.lex, 0, 1, null).getType() != TT.IDENT || !Intrinsics.areEqual(Lexer.peek$default(this.lex, 0, 1, null).getRaw(), "instanceof")) {
                                break;
                            }
                            this.lex.consume();
                            binExpr = new BinExpr("instanceof", shift, parseShift());
                        }
                    }
                }
            }
            shift = binExpr;
        }
        return shift;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.Parser$parseShift$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: JsInterpreter.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C09441 extends FunctionReferenceImpl implements Function0<Node> {
        C09441(Object obj) {
            super(0, obj, Parser.class, "parseAdd", "parseAdd()Lcom/lagradost/cloudstream3/utils/Node;", 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Node invoke() {
            return ((Parser) this.receiver).parseAdd();
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.Parser$parseAdd$1, reason: invalid class name */
    /* JADX INFO: compiled from: JsInterpreter.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function0<Node> {
        AnonymousClass1(Object obj) {
            super(0, obj, Parser.class, "parseMul", "parseMul()Lcom/lagradost/cloudstream3/utils/Node;", 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Node invoke() {
            return ((Parser) this.receiver).parseMul();
        }
    }

    private final Node parseShift() {
        return parseBin(new C09441(this), TuplesKt.to(TT.LSHIFT, "<<"), TuplesKt.to(TT.RSHIFT, ">>"), TuplesKt.to(TT.URSHIFT, ">>>"));
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.Parser$parseMul$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: JsInterpreter.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C09421 extends FunctionReferenceImpl implements Function0<Node> {
        C09421(Object obj) {
            super(0, obj, Parser.class, "parsePow", "parsePow()Lcom/lagradost/cloudstream3/utils/Node;", 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Node invoke() {
            return ((Parser) this.receiver).parsePow();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Node parseAdd() {
        return parseBin(new AnonymousClass1(this), TuplesKt.to(TT.PLUS, Marker.ANY_NON_NULL_MARKER), TuplesKt.to(TT.MINUS, "-"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Node parseMul() {
        return parseBin(new C09421(this), TuplesKt.to(TT.STAR, Marker.ANY_MARKER), TuplesKt.to(TT.SLASH, "/"), TuplesKt.to(TT.PERCENT, "%"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Node parsePow() {
        Node unary = parseUnary();
        if (Lexer.peek$default(this.lex, 0, 1, null).getType() != TT.POW) {
            return unary;
        }
        this.lex.consume();
        return new BinExpr("**", unary, parsePow());
    }

    private final Node parseBin(Function0<? extends Node> next, Pair<? extends TT, String>... ops) {
        Pair<? extends TT, String> pair;
        Node nodeInvoke = next.invoke();
        while (true) {
            Node node = nodeInvoke;
            int length = ops.length;
            int i = 0;
            while (true) {
                pair = null;
                if (i >= length) {
                    break;
                }
                Pair<? extends TT, String> pair2 = ops[i];
                if (pair2.getFirst() == Lexer.peek$default(this.lex, 0, 1, null).getType()) {
                    pair = pair2;
                    break;
                }
                i++;
            }
            if (pair == null) {
                return node;
            }
            this.lex.consume();
            nodeInvoke = new BinExpr(pair.getSecond(), node, next.invoke());
        }
    }

    private final Node parseUnary() {
        int i = WhenMappings.$EnumSwitchMapping$0[Lexer.peek$default(this.lex, 0, 1, null).getType().ordinal()];
        if (i != 3) {
            switch (i) {
                case 11:
                    this.lex.consume();
                    return new UnaryExpr("-", parseUnary(), true);
                case 12:
                    this.lex.consume();
                    return new UnaryExpr(Marker.ANY_NON_NULL_MARKER, parseUnary(), true);
                case 13:
                    this.lex.consume();
                    return new UnaryExpr("!", parseUnary(), true);
                case 14:
                    this.lex.consume();
                    return new UnaryExpr("~", parseUnary(), true);
                case 15:
                    this.lex.consume();
                    return new UnaryExpr("++", parsePostfix(), true);
                case 16:
                    this.lex.consume();
                    return new UnaryExpr("--", parsePostfix(), true);
                default:
                    return parsePostfix();
            }
        }
        String raw = Lexer.peek$default(this.lex, 0, 1, null).getRaw();
        int iHashCode = raw.hashCode();
        if (iHashCode != -858802543) {
            if (iHashCode != 108960) {
                if (iHashCode == 3625364 && raw.equals("void")) {
                    this.lex.consume();
                    parseUnary();
                    return UndefinedLit.INSTANCE;
                }
            } else if (raw.equals("new")) {
                return parseNew();
            }
        } else if (raw.equals("typeof")) {
            this.lex.consume();
            return new TypeofExpr(parseUnary());
        }
        return parsePostfix();
    }

    private final Node parseNew() {
        MemberExpr memberExpr;
        this.lex.consume();
        NewExpr newExpr = new NewExpr(parsePrimary(), Lexer.peek$default(this.lex, 0, 1, null).getType() == TT.LPAREN ? parseArgs() : CollectionsKt.emptyList());
        while (true) {
            switch (WhenMappings.$EnumSwitchMapping$0[Lexer.peek$default(this.lex, 0, 1, null).getType().ordinal()]) {
                case 17:
                    this.lex.consume();
                    memberExpr = new MemberExpr(newExpr, new StrLit(this.lex.expect(TT.IDENT).getRaw()), false);
                    break;
                case 18:
                    this.lex.consume();
                    Node assign = parseAssign();
                    this.lex.expect(TT.RBRACKET);
                    memberExpr = new MemberExpr(newExpr, assign, true);
                    break;
                case 19:
                    newExpr = new CallExpr(newExpr, parseArgs());
                    continue;
                default:
                    return newExpr;
            }
            newExpr = memberExpr;
        }
    }

    private final Node parsePostfix() {
        UnaryExpr unaryExpr;
        Node call = parseCall();
        while (true) {
            int i = WhenMappings.$EnumSwitchMapping$0[Lexer.peek$default(this.lex, 0, 1, null).getType().ordinal()];
            if (i == 15) {
                this.lex.consume();
                unaryExpr = new UnaryExpr("++", call, false);
            } else {
                if (i != 16) {
                    return call;
                }
                this.lex.consume();
                unaryExpr = new UnaryExpr("--", call, false);
            }
            call = unaryExpr;
        }
    }

    private final Node parseCall() {
        MemberExpr memberExpr;
        Node primary = parsePrimary();
        while (true) {
            switch (WhenMappings.$EnumSwitchMapping$0[Lexer.peek$default(this.lex, 0, 1, null).getType().ordinal()]) {
                case 17:
                    this.lex.consume();
                    memberExpr = new MemberExpr(primary, new StrLit(this.lex.expect(TT.IDENT).getRaw()), false);
                    break;
                case 18:
                    this.lex.consume();
                    Node assign = parseAssign();
                    this.lex.expect(TT.RBRACKET);
                    memberExpr = new MemberExpr(primary, assign, true);
                    break;
                case 19:
                    primary = new CallExpr(primary, parseArgs());
                    continue;
                default:
                    return primary;
            }
            primary = memberExpr;
        }
    }

    private final List<Node> parseArgs() {
        this.lex.expect(TT.LPAREN);
        ArrayList arrayList = new ArrayList();
        while (Lexer.peek$default(this.lex, 0, 1, null).getType() != TT.RPAREN && Lexer.peek$default(this.lex, 0, 1, null).getType() != TT.EOF) {
            arrayList.add(parseAssign());
            if (!this.lex.matchIf(TT.COMMA)) {
                break;
            }
        }
        this.lex.expect(TT.RPAREN);
        return arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x007b  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final Node parsePrimary() {
        String raw;
        long jLongValue;
        Double doubleOrNull;
        int i = 0;
        Token tokenPeek$default = Lexer.peek$default(this.lex, 0, 1, null);
        int i2 = WhenMappings.$EnumSwitchMapping$0[tokenPeek$default.getType().ordinal()];
        if (i2 == 1) {
            this.lex.consume();
            ArrayList arrayList = new ArrayList();
            while (Lexer.peek$default(this.lex, 0, 1, null).getType() != TT.RBRACE && Lexer.peek$default(this.lex, 0, 1, null).getType() != TT.EOF) {
                int i3 = WhenMappings.$EnumSwitchMapping$0[Lexer.peek$default(this.lex, 0, 1, null).getType().ordinal()];
                if (i3 == 3 || i3 == 20) {
                    raw = this.lex.consume().getRaw();
                } else {
                    Lexer lexer = this.lex;
                    raw = i3 != 21 ? lexer.consume().getRaw() : lexer.consume().getRaw();
                }
                this.lex.expect(TT.COLON);
                arrayList.add(TuplesKt.to(raw, parseAssign()));
                this.lex.matchIf(TT.COMMA);
            }
            this.lex.expect(TT.RBRACE);
            return new ObjLit(arrayList);
        }
        if (i2 == 3) {
            String raw2 = tokenPeek$default.getRaw();
            this.lex.consume();
            switch (raw2.hashCode()) {
                case -1038130864:
                    if (raw2.equals("undefined")) {
                        return UndefinedLit.INSTANCE;
                    }
                    break;
                case 3392903:
                    if (raw2.equals(AbstractJsonLexerKt.NULL)) {
                        return NullLit.INSTANCE;
                    }
                    break;
                case 3569038:
                    if (raw2.equals("true")) {
                        return new BoolLit(true);
                    }
                    break;
                case 97196323:
                    if (raw2.equals("false")) {
                        return new BoolLit(false);
                    }
                    break;
                case 1380938712:
                    if (raw2.equals("function")) {
                        return new FuncExpr(Lexer.peek$default(this.lex, 0, 1, null).getType() == TT.IDENT ? this.lex.consume().getRaw() : null, parseParams(), parseBlock().getStmts());
                    }
                    break;
            }
            return new Ident(raw2);
        }
        switch (i2) {
            case 18:
                this.lex.consume();
                ArrayList arrayList2 = new ArrayList();
                while (Lexer.peek$default(this.lex, 0, 1, null).getType() != TT.RBRACKET && Lexer.peek$default(this.lex, 0, 1, null).getType() != TT.EOF) {
                    if (Lexer.peek$default(this.lex, 0, 1, null).getType() == TT.COMMA) {
                        arrayList2.add(UndefinedLit.INSTANCE);
                        this.lex.consume();
                    } else {
                        arrayList2.add(parseAssign());
                        if (Lexer.peek$default(this.lex, 0, 1, null).getType() != TT.COMMA) {
                            this.lex.expect(TT.RBRACKET);
                            return new ArrayLit(arrayList2);
                        }
                        this.lex.consume();
                    }
                }
                this.lex.expect(TT.RBRACKET);
                return new ArrayLit(arrayList2);
            case 19:
                this.lex.consume();
                Node seq = parseSeq();
                this.lex.expect(TT.RPAREN);
                return seq;
            case 20:
                this.lex.consume();
                return new StrLit(tokenPeek$default.getRaw());
            case 21:
                this.lex.consume();
                String raw3 = tokenPeek$default.getRaw();
                double dDoubleValue = Double.NaN;
                if (StringsKt.startsWith$default(raw3, "0x", false, 2, (Object) null) || StringsKt.startsWith$default(raw3, "0X", false, 2, (Object) null)) {
                    Long longOrNull = StringsKt.toLongOrNull(StringsKt.drop(raw3, 2), 16);
                    if (longOrNull != null) {
                        jLongValue = longOrNull.longValue();
                        dDoubleValue = jLongValue;
                    }
                } else if (raw3.length() <= 1 || raw3.charAt(0) != '0') {
                    doubleOrNull = StringsKt.toDoubleOrNull(raw3);
                    if (doubleOrNull != null) {
                        dDoubleValue = doubleOrNull.doubleValue();
                    }
                } else {
                    String str = raw3;
                    while (true) {
                        if (i < str.length()) {
                            char cCharAt = str.charAt(i);
                            if ('0' > cCharAt || cCharAt >= '8') {
                                doubleOrNull = StringsKt.toDoubleOrNull(raw3);
                                if (doubleOrNull != null) {
                                    dDoubleValue = doubleOrNull.doubleValue();
                                }
                            } else {
                                i++;
                            }
                        } else {
                            Long longOrNull2 = StringsKt.toLongOrNull(raw3, 8);
                            if (longOrNull2 != null) {
                                jLongValue = longOrNull2.longValue();
                                dDoubleValue = jLongValue;
                            }
                        }
                    }
                }
                return new NumLit(dDoubleValue);
            default:
                this.lex.consume();
                return UndefinedLit.INSTANCE;
        }
    }
}
