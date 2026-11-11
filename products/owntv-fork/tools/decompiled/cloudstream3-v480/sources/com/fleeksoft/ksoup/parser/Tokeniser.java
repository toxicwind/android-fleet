package com.fleeksoft.ksoup.parser;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import androidx.work.WorkInfo;
import com.fleeksoft.ksoup.exception.UncheckedIOException;
import com.fleeksoft.ksoup.helper.Validate;
import com.fleeksoft.ksoup.internal.StringUtil;
import com.fleeksoft.ksoup.nodes.Document;
import com.fleeksoft.ksoup.nodes.Entities;
import com.fleeksoft.ksoup.ported.CoreExtensionsKt;
import io.ktor.http.ContentType;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import kotlin.text.Typography;

/* JADX INFO: compiled from: Tokeniser.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\u001e\u0018\u0000 \\2\u00020\u0001:\u0001\\B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u00109\u001a\u00020\rJ\u000e\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020\rJ\u000e\u0010:\u001a\u00020;2\u0006\u0010=\u001a\u000201J\u000e\u0010:\u001a\u00020;2\u0006\u0010>\u001a\u00020?J\u000e\u0010:\u001a\u00020;2\u0006\u0010@\u001a\u000207J\u000e\u0010A\u001a\u00020;2\u0006\u0010B\u001a\u00020\u000bJ\u000e\u0010C\u001a\u00020;2\u0006\u0010B\u001a\u00020\u000bJ\u001f\u0010D\u001a\u0004\u0018\u0001072\b\u0010E\u001a\u0004\u0018\u00010?2\u0006\u0010F\u001a\u00020\u000f¢\u0006\u0002\u0010GJ\u000e\u0010H\u001a\u00020\u001d2\u0006\u0010I\u001a\u00020\u000fJ\u000e\u0010J\u001a\u00020-2\u0006\u0010K\u001a\u00020\u000fJ\u0006\u0010L\u001a\u00020;J\u0006\u0010M\u001a\u00020;J\u0006\u0010N\u001a\u00020;J\u0006\u0010O\u001a\u00020;J\u0006\u0010P\u001a\u00020;J\u0006\u0010Q\u001a\u00020;J\u0006\u0010R\u001a\u00020;J\u0006\u0010S\u001a\u00020\u000fJ\b\u0010T\u001a\u0004\u0018\u000101J\u0006\u0010U\u001a\u000201J\u0010\u0010V\u001a\u00020;2\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u0010\u0010W\u001a\u00020;2\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u0010\u0010X\u001a\u00020;2\u0006\u0010Y\u001a\u000201H\u0002J\u000e\u0010V\u001a\u00020;2\u0006\u0010Z\u001a\u000201J\u000e\u0010[\u001a\u0002012\u0006\u0010F\u001a\u00020\u000fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010$\u001a\u00020%¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0011\u0010(\u001a\u00020)¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010,\u001a\u00020-¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0010\u00100\u001a\u0004\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00102\u001a\u0004\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u000204X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000204X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006]"}, d2 = {"Lcom/fleeksoft/ksoup/parser/Tokeniser;", "", "treeBuilder", "Lcom/fleeksoft/ksoup/parser/TreeBuilder;", "<init>", "(Lcom/fleeksoft/ksoup/parser/TreeBuilder;)V", "reader", "Lcom/fleeksoft/ksoup/parser/CharacterReader;", "errors", "Lcom/fleeksoft/ksoup/parser/ParseErrorList;", "state", "Lcom/fleeksoft/ksoup/parser/TokeniserState;", "emitPending", "Lcom/fleeksoft/ksoup/parser/Token;", "isEmitPending", "", "dataBuffer", "Lcom/fleeksoft/ksoup/parser/TokenData;", "getDataBuffer", "()Lcom/fleeksoft/ksoup/parser/TokenData;", "syntax", "Lcom/fleeksoft/ksoup/nodes/Document$OutputSettings$Syntax;", "getSyntax", "()Lcom/fleeksoft/ksoup/nodes/Document$OutputSettings$Syntax;", "startPending", "Lcom/fleeksoft/ksoup/parser/Token$StartTag;", "endPending", "Lcom/fleeksoft/ksoup/parser/Token$EndTag;", "tagPending", "Lcom/fleeksoft/ksoup/parser/Token$Tag;", "getTagPending", "()Lcom/fleeksoft/ksoup/parser/Token$Tag;", "setTagPending", "(Lcom/fleeksoft/ksoup/parser/Token$Tag;)V", "charPending", "Lcom/fleeksoft/ksoup/parser/Token$Character;", "doctypePending", "Lcom/fleeksoft/ksoup/parser/Token$Doctype;", "getDoctypePending", "()Lcom/fleeksoft/ksoup/parser/Token$Doctype;", "commentPending", "Lcom/fleeksoft/ksoup/parser/Token$Comment;", "getCommentPending", "()Lcom/fleeksoft/ksoup/parser/Token$Comment;", "xmlDeclPending", "Lcom/fleeksoft/ksoup/parser/Token$XmlDecl;", "getXmlDeclPending", "()Lcom/fleeksoft/ksoup/parser/Token$XmlDecl;", "lastStartTag", "", "lastStartCloseSeq", "markupStartPos", "", "charStartPos", "codepointHolder", "", "multipointHolder", "read", "emit", "", "token", "str", "c", "", "codepoints", "transition", "newState", "advanceTransition", "consumeCharacterReference", "additionalAllowedCharacter", "inAttribute", "(Ljava/lang/Character;Z)[I", "createTagPending", "start", "createXmlDeclPending", "isDeclaration", "emitTagPending", "createCommentPending", "emitCommentPending", "createBogusCommentPending", "createDoctypePending", "emitDoctypePending", "createTempBuffer", "isAppropriateEndTagToken", "appropriateEndTagName", "appropriateEndTagSeq", MediaRouteProviderProtocol.SERVICE_DATA_ERROR, "eofError", "characterReferenceError", ContentType.Message.TYPE, "errorMsg", "unescapeEntities", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Tokeniser {
    public static final char ReplacementChar = 65533;
    public static final int win1252ExtensionsStart = 128;
    private final Token.Character charPending;
    private int charStartPos;
    private final int[] codepointHolder;
    private final Token.Comment commentPending;
    private final TokenData dataBuffer;
    private final Token.Doctype doctypePending;
    private Token emitPending;
    private final Token.EndTag endPending;
    private final ParseErrorList errors;
    private boolean isEmitPending;
    private String lastStartCloseSeq;
    private String lastStartTag;
    private int markupStartPos;
    private final int[] multipointHolder;
    private final CharacterReader reader;
    private final Token.StartTag startPending;
    private TokeniserState state;
    private final Document.OutputSettings.Syntax syntax;
    private Token.Tag tagPending;
    private final Token.XmlDecl xmlDeclPending;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final char[] notCharRefCharsSorted = ArraysKt.sortedArray(new char[]{'\t', '\n', '\r', '\f', ' ', Typography.less, Typography.amp});
    private static final int[] win1252Extensions = {8364, 129, 8218, TypedValues.CycleType.TYPE_VISIBILITY, 8222, 8230, 8224, 8225, 710, 8240, 352, 8249, 338, 141, 381, 143, 144, 8216, 8217, 8220, 8221, 8226, 8211, 8212, 732, 8482, 353, 8250, 339, 157, 382, 376};

    /* JADX INFO: compiled from: Tokeniser.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Token.TokenType.values().length];
            try {
                iArr[Token.TokenType.StartTag.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Token.TokenType.EndTag.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public Tokeniser(TreeBuilder treeBuilder) {
        Intrinsics.checkNotNullParameter(treeBuilder, "treeBuilder");
        this.reader = treeBuilder.getReader();
        this.errors = treeBuilder.getParser().getErrors();
        this.state = TokeniserState.Data;
        this.dataBuffer = new TokenData();
        this.syntax = treeBuilder instanceof XmlTreeBuilder ? Document.OutputSettings.Syntax.xml : Document.OutputSettings.Syntax.html;
        Token.StartTag startTag = new Token.StartTag(treeBuilder);
        this.startPending = startTag;
        this.endPending = new Token.EndTag(treeBuilder);
        this.tagPending = startTag;
        this.charPending = new Token.Character();
        this.doctypePending = new Token.Doctype();
        this.commentPending = new Token.Comment();
        this.xmlDeclPending = new Token.XmlDecl(treeBuilder);
        this.codepointHolder = new int[1];
        this.multipointHolder = new int[2];
    }

    public final TokenData getDataBuffer() {
        return this.dataBuffer;
    }

    public final Document.OutputSettings.Syntax getSyntax() {
        return this.syntax;
    }

    public final Token.Tag getTagPending() {
        return this.tagPending;
    }

    public final void setTagPending(Token.Tag tag) {
        Intrinsics.checkNotNullParameter(tag, "<set-?>");
        this.tagPending = tag;
    }

    public final Token.Doctype getDoctypePending() {
        return this.doctypePending;
    }

    public final Token.Comment getCommentPending() {
        return this.commentPending;
    }

    public final Token.XmlDecl getXmlDeclPending() {
        return this.xmlDeclPending;
    }

    public final Token read() {
        while (!this.isEmitPending) {
            this.state.read(this, this.reader);
        }
        if (this.charPending.getData().hasData()) {
            return this.charPending;
        }
        this.isEmitPending = false;
        Token token = this.emitPending;
        if (token != null) {
            return token;
        }
        throw new IllegalStateException("Required value was null.".toString());
    }

    public final void emit(Token token) {
        Intrinsics.checkNotNullParameter(token, "token");
        Validate.INSTANCE.isFalse(this.isEmitPending);
        this.emitPending = token;
        this.isEmitPending = true;
        token.startPos(this.markupStartPos);
        token.endPos(this.reader.pos());
        this.charStartPos = this.reader.pos();
        int i = WhenMappings.$EnumSwitchMapping$0[token.getType().ordinal()];
        if (i == 1) {
            this.lastStartTag = ((Token.StartTag) token).name();
            this.lastStartCloseSeq = null;
        } else {
            if (i != 2) {
                return;
            }
            Token.EndTag endTag = (Token.EndTag) token;
            if (endTag.hasAttributes()) {
                error("Attributes incorrectly present on end tag [/" + endTag.retrieveNormalName() + "]");
            }
        }
    }

    public final void emit(String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        this.charPending.append(str);
        this.charPending.startPos(this.charStartPos);
        this.charPending.endPos(this.reader.pos());
    }

    public final void emit(char c) {
        this.charPending.getData().append(c);
        this.charPending.startPos(this.charStartPos);
        this.charPending.endPos(this.reader.pos());
    }

    public final void emit(int[] codepoints) {
        Intrinsics.checkNotNullParameter(codepoints, "codepoints");
        emit(CoreExtensionsKt.codePointsToString(codepoints));
    }

    public final void transition(TokeniserState newState) {
        Intrinsics.checkNotNullParameter(newState, "newState");
        if (newState == TokeniserState.TagOpen) {
            this.markupStartPos = this.reader.pos();
        }
        this.state = newState;
    }

    public final void advanceTransition(TokeniserState newState) {
        Intrinsics.checkNotNullParameter(newState, "newState");
        transition(newState);
        this.reader.advance();
    }

    public final int[] consumeCharacterReference(Character additionalAllowedCharacter, boolean inAttribute) {
        int i;
        if (this.reader.isEmpty()) {
            return null;
        }
        if (additionalAllowedCharacter != null) {
            if (additionalAllowedCharacter.charValue() == this.reader.current()) {
                return null;
            }
        }
        if (this.reader.matchesAnySorted(notCharRefCharsSorted)) {
            return null;
        }
        int[] iArr = this.codepointHolder;
        this.reader.mark();
        boolean zMatchConsume = this.reader.matchConsume("#");
        CharacterReader characterReader = this.reader;
        if (zMatchConsume) {
            boolean zMatchConsumeIgnoreCase = characterReader.matchConsumeIgnoreCase("X");
            CharacterReader characterReader2 = this.reader;
            String strConsumeHexSequence = zMatchConsumeIgnoreCase ? characterReader2.consumeHexSequence() : characterReader2.consumeDigitSequence();
            if (strConsumeHexSequence.length() == 0) {
                characterReferenceError("numeric reference with no numerals");
                this.reader.rewindToMark();
                return null;
            }
            this.reader.unmark();
            if (!this.reader.matchConsume(";")) {
                characterReferenceError("missing semicolon on [&#" + strConsumeHexSequence + "]");
            }
            try {
                i = Integer.parseInt(strConsumeHexSequence, CharsKt.checkRadix(zMatchConsumeIgnoreCase ? 16 : 10));
            } catch (NumberFormatException unused) {
                i = -1;
            }
            if (i == -1 || i > 1114111) {
                characterReferenceError("character [" + i + "] outside of valid range");
                iArr[0] = 65533;
            } else {
                if (i >= 128) {
                    int[] iArr2 = win1252Extensions;
                    if (i < iArr2.length + 128) {
                        characterReferenceError("character [" + i + "] is not a valid unicode code point");
                        i = iArr2[i + WorkInfo.STOP_REASON_FOREGROUND_SERVICE_TIMEOUT];
                    }
                }
                iArr[0] = i;
            }
            return iArr;
        }
        String strConsumeLetterThenDigitSequence = characterReader.consumeLetterThenDigitSequence();
        boolean zMatches = this.reader.matches(';');
        if (!Entities.INSTANCE.isBaseNamedEntity(strConsumeLetterThenDigitSequence) && (!Entities.INSTANCE.isNamedEntity(strConsumeLetterThenDigitSequence) || !zMatches)) {
            this.reader.rewindToMark();
            if (zMatches) {
                characterReferenceError("invalid named reference [" + strConsumeLetterThenDigitSequence + "]");
            }
            if (inAttribute) {
                return null;
            }
            strConsumeLetterThenDigitSequence = Entities.INSTANCE.findPrefix(strConsumeLetterThenDigitSequence);
            if (strConsumeLetterThenDigitSequence.length() == 0) {
                return null;
            }
            this.reader.matchConsume(strConsumeLetterThenDigitSequence);
        }
        if (inAttribute && (this.reader.matchesAsciiAlpha() || this.reader.matchesDigit() || this.reader.matchesAny('=', '-', '_'))) {
            this.reader.rewindToMark();
            return null;
        }
        this.reader.unmark();
        if (!this.reader.matchConsume(";")) {
            characterReferenceError("missing semicolon on [&" + strConsumeLetterThenDigitSequence + "]");
        }
        int iCodepointsForName = Entities.INSTANCE.codepointsForName(strConsumeLetterThenDigitSequence, this.multipointHolder);
        if (iCodepointsForName == 1) {
            iArr[0] = this.multipointHolder[0];
            return iArr;
        }
        if (iCodepointsForName == 2) {
            return this.multipointHolder;
        }
        Validate.INSTANCE.fail("Unexpected characters returned for " + strConsumeLetterThenDigitSequence);
        return this.multipointHolder;
    }

    public final Token.Tag createTagPending(boolean start) {
        Token.Tag tagReset = start ? this.startPending.reset() : this.endPending.reset();
        this.tagPending = tagReset;
        return tagReset;
    }

    public final Token.XmlDecl createXmlDeclPending(boolean isDeclaration) {
        Token.XmlDecl xmlDeclReset = this.xmlDeclPending.reset();
        xmlDeclReset.setDeclaration(isDeclaration);
        this.tagPending = xmlDeclReset;
        return xmlDeclReset;
    }

    public final void emitTagPending() {
        this.tagPending.finaliseTag();
        emit(this.tagPending);
    }

    public final void createCommentPending() {
        this.commentPending.reset();
    }

    public final void emitCommentPending() {
        emit(this.commentPending);
    }

    public final void createBogusCommentPending() {
        this.commentPending.reset();
        this.commentPending.setBogus(true);
    }

    public final void createDoctypePending() {
        this.doctypePending.reset();
    }

    public final void emitDoctypePending() {
        emit(this.doctypePending);
    }

    public final void createTempBuffer() {
        this.dataBuffer.reset();
    }

    public final boolean isAppropriateEndTagToken() {
        return this.lastStartTag != null && StringsKt.equals(this.tagPending.name(), this.lastStartTag, true);
    }

    /* JADX INFO: renamed from: appropriateEndTagName, reason: from getter */
    public final String getLastStartTag() {
        return this.lastStartTag;
    }

    public final String appropriateEndTagSeq() {
        if (this.lastStartCloseSeq == null) {
            this.lastStartCloseSeq = "</" + this.lastStartTag;
        }
        String str = this.lastStartCloseSeq;
        Intrinsics.checkNotNull(str);
        return str;
    }

    public final void error(TokeniserState state) {
        if (this.errors.canAddError()) {
            ParseErrorList parseErrorList = this.errors;
            CharacterReader characterReader = this.reader;
            parseErrorList.add(new ParseError(characterReader, "Unexpected character '" + characterReader.current() + "' in input state [" + state + "]"));
        }
    }

    public final void eofError(TokeniserState state) {
        if (this.errors.canAddError()) {
            this.errors.add(new ParseError(this.reader, "Unexpectedly reached end of file (EOF) in input state [" + state + "]"));
        }
    }

    private final void characterReferenceError(String message) {
        if (this.errors.canAddError()) {
            this.errors.add(new ParseError(this.reader, "Invalid character reference: " + message));
        }
    }

    public final void error(String errorMsg) {
        Intrinsics.checkNotNullParameter(errorMsg, "errorMsg");
        if (this.errors.canAddError()) {
            this.errors.add(new ParseError(this.reader, errorMsg));
        }
    }

    public final String unescapeEntities(boolean inAttribute) throws UncheckedIOException {
        StringBuilder sbBorrowBuilder = StringUtil.INSTANCE.borrowBuilder();
        while (!this.reader.isEmpty()) {
            sbBorrowBuilder.append(this.reader.consumeTo(Typography.amp));
            if (this.reader.matches(Typography.amp)) {
                this.reader.consume();
                int[] iArrConsumeCharacterReference = consumeCharacterReference(null, inAttribute);
                if (iArrConsumeCharacterReference == null || iArrConsumeCharacterReference.length == 0) {
                    sbBorrowBuilder.append(Typography.amp);
                } else {
                    sbBorrowBuilder.appendCodePoint(iArrConsumeCharacterReference[0]);
                    if (iArrConsumeCharacterReference.length == 2) {
                        sbBorrowBuilder.appendCodePoint(iArrConsumeCharacterReference[1]);
                    }
                }
            }
        }
        return StringUtil.INSTANCE.releaseBuilder(sbBorrowBuilder);
    }

    /* JADX INFO: compiled from: Tokeniser.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/fleeksoft/ksoup/parser/Tokeniser$Companion;", "", "<init>", "()V", "ReplacementChar", "", "notCharRefCharsSorted", "", "win1252ExtensionsStart", "", "win1252Extensions", "", "getWin1252Extensions", "()[I", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int[] getWin1252Extensions() {
            return Tokeniser.win1252Extensions;
        }
    }
}
