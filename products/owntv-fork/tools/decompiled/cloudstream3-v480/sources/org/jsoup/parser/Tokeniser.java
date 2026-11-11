package org.jsoup.parser;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.work.WorkInfo;
import java.util.Arrays;
import kotlin.text.Typography;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities;

/* JADX INFO: loaded from: classes8.dex */
final class Tokeniser {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final char[] notCharRefCharsSorted;
    static final char replacementChar = 65533;
    static final int[] win1252Extensions;
    static final int win1252ExtensionsStart = 128;
    final Token.EndTag endPending;
    private final ParseErrorList errors;
    private String lastStartCloseSeq;
    private String lastStartTag;
    private int markupStartPos;
    private final CharacterReader reader;
    final Token.StartTag startPending;
    final Document.OutputSettings.Syntax syntax;
    Token.Tag tagPending;
    final Token.XmlDecl xmlDeclPending;
    private TokeniserState state = TokeniserState.Data;
    private Token emitPending = null;
    private boolean isEmitPending = false;
    final TokenData dataBuffer = new TokenData();
    final Token.Character charPending = new Token.Character();
    final Token.Doctype doctypePending = new Token.Doctype();
    final Token.Comment commentPending = new Token.Comment();
    private int charStartPos = 0;
    private final int[] codepointHolder = new int[1];
    private final int[] multipointHolder = new int[2];

    static {
        char[] cArr = {'\t', '\n', '\r', '\f', ' ', Typography.less, Typography.amp};
        notCharRefCharsSorted = cArr;
        win1252Extensions = new int[]{8364, 129, 8218, TypedValues.CycleType.TYPE_VISIBILITY, 8222, 8230, 8224, 8225, 710, 8240, 352, 8249, 338, 141, 381, 143, 144, 8216, 8217, 8220, 8221, 8226, 8211, 8212, 732, 8482, 353, 8250, 339, 157, 382, 376};
        Arrays.sort(cArr);
    }

    Tokeniser(TreeBuilder treeBuilder) {
        this.syntax = treeBuilder instanceof XmlTreeBuilder ? Document.OutputSettings.Syntax.xml : Document.OutputSettings.Syntax.html;
        Token.StartTag startTag = new Token.StartTag(treeBuilder);
        this.startPending = startTag;
        this.tagPending = startTag;
        this.endPending = new Token.EndTag(treeBuilder);
        this.xmlDeclPending = new Token.XmlDecl(treeBuilder);
        this.reader = treeBuilder.reader;
        this.errors = treeBuilder.parser.getErrors();
    }

    Token read() {
        while (!this.isEmitPending) {
            this.state.read(this, this.reader);
        }
        if (this.charPending.data.hasData()) {
            return this.charPending;
        }
        this.isEmitPending = false;
        return this.emitPending;
    }

    void emit(Token token) {
        Validate.isFalse(this.isEmitPending);
        this.emitPending = token;
        this.isEmitPending = true;
        token.startPos(this.markupStartPos);
        token.endPos(this.reader.pos());
        this.charStartPos = this.reader.pos();
        if (token.type == Token.TokenType.StartTag) {
            this.lastStartTag = ((Token.StartTag) token).name();
            this.lastStartCloseSeq = null;
        } else if (token.type == Token.TokenType.EndTag) {
            Token.EndTag endTag = (Token.EndTag) token;
            if (endTag.hasAttributes()) {
                error("Attributes incorrectly present on end tag [/%s]", endTag.normalName());
            }
        }
    }

    void emit(String str) {
        this.charPending.append(str);
        this.charPending.startPos(this.charStartPos);
        this.charPending.endPos(this.reader.pos());
    }

    void emit(char c) {
        this.charPending.data.append(c);
        this.charPending.startPos(this.charStartPos);
        this.charPending.endPos(this.reader.pos());
    }

    void emit(int[] iArr) {
        emit(new String(iArr, 0, iArr.length));
    }

    void transition(TokeniserState tokeniserState) {
        if (tokeniserState == TokeniserState.TagOpen) {
            this.markupStartPos = this.reader.pos();
        }
        this.state = tokeniserState;
    }

    void advanceTransition(TokeniserState tokeniserState) {
        transition(tokeniserState);
        this.reader.advance();
    }

    int[] consumeCharacterReference(Character ch, boolean z) {
        int iIntValue;
        if (this.reader.isEmpty()) {
            return null;
        }
        if ((ch != null && ch.charValue() == this.reader.current()) || this.reader.matchesAnySorted(notCharRefCharsSorted)) {
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
            if (strConsumeHexSequence.isEmpty()) {
                characterReferenceError("numeric reference with no numerals", new Object[0]);
                this.reader.rewindToMark();
                return null;
            }
            this.reader.unmark();
            if (!this.reader.matchConsume(";")) {
                characterReferenceError("missing semicolon on [&#%s]", strConsumeHexSequence);
            }
            try {
                iIntValue = Integer.valueOf(strConsumeHexSequence, zMatchConsumeIgnoreCase ? 16 : 10).intValue();
            } catch (NumberFormatException unused) {
                iIntValue = -1;
            }
            if (iIntValue == -1 || iIntValue > 1114111) {
                characterReferenceError("character [%s] outside of valid range", Integer.valueOf(iIntValue));
                iArr[0] = 65533;
            } else {
                if (iIntValue >= 128) {
                    int[] iArr2 = win1252Extensions;
                    if (iIntValue < iArr2.length + 128) {
                        characterReferenceError("character [%s] is not a valid unicode code point", Integer.valueOf(iIntValue));
                        iIntValue = iArr2[iIntValue + WorkInfo.STOP_REASON_FOREGROUND_SERVICE_TIMEOUT];
                    }
                }
                iArr[0] = iIntValue;
            }
            return iArr;
        }
        String strConsumeLetterThenDigitSequence = characterReader.consumeLetterThenDigitSequence();
        boolean zMatches = this.reader.matches(';');
        if (!Entities.isBaseNamedEntity(strConsumeLetterThenDigitSequence) && (!Entities.isNamedEntity(strConsumeLetterThenDigitSequence) || !zMatches)) {
            this.reader.rewindToMark();
            if (zMatches) {
                characterReferenceError("invalid named reference [%s]", strConsumeLetterThenDigitSequence);
            }
            if (z) {
                return null;
            }
            strConsumeLetterThenDigitSequence = Entities.findPrefix(strConsumeLetterThenDigitSequence);
            if (strConsumeLetterThenDigitSequence.isEmpty()) {
                return null;
            }
            this.reader.matchConsume(strConsumeLetterThenDigitSequence);
        }
        if (z && (this.reader.matchesAsciiAlpha() || this.reader.matchesDigit() || this.reader.matchesAny('=', '-', '_'))) {
            this.reader.rewindToMark();
            return null;
        }
        this.reader.unmark();
        if (!this.reader.matchConsume(";")) {
            characterReferenceError("missing semicolon on [&%s]", strConsumeLetterThenDigitSequence);
        }
        int iCodepointsForName = Entities.codepointsForName(strConsumeLetterThenDigitSequence, this.multipointHolder);
        if (iCodepointsForName == 1) {
            iArr[0] = this.multipointHolder[0];
            return iArr;
        }
        if (iCodepointsForName == 2) {
            return this.multipointHolder;
        }
        Validate.fail("Unexpected characters returned for " + strConsumeLetterThenDigitSequence);
        return this.multipointHolder;
    }

    Token.Tag createTagPending(boolean z) {
        Token.Tag tagReset = z ? this.startPending.reset() : this.endPending.reset();
        this.tagPending = tagReset;
        return tagReset;
    }

    Token.XmlDecl createXmlDeclPending(boolean z) {
        Token.XmlDecl xmlDeclReset = this.xmlDeclPending.reset();
        xmlDeclReset.isDeclaration = z;
        this.tagPending = xmlDeclReset;
        return xmlDeclReset;
    }

    void emitTagPending() {
        this.tagPending.finaliseTag();
        emit(this.tagPending);
    }

    void createCommentPending() {
        this.commentPending.reset();
    }

    void emitCommentPending() {
        emit(this.commentPending);
    }

    void createBogusCommentPending() {
        this.commentPending.reset();
        this.commentPending.bogus = true;
    }

    void createDoctypePending() {
        this.doctypePending.reset();
    }

    void emitDoctypePending() {
        emit(this.doctypePending);
    }

    void createTempBuffer() {
        this.dataBuffer.reset();
    }

    boolean isAppropriateEndTagToken() {
        return this.lastStartTag != null && this.tagPending.name().equalsIgnoreCase(this.lastStartTag);
    }

    String appropriateEndTagName() {
        return this.lastStartTag;
    }

    String appropriateEndTagSeq() {
        if (this.lastStartCloseSeq == null) {
            this.lastStartCloseSeq = "</" + this.lastStartTag;
        }
        return this.lastStartCloseSeq;
    }

    void error(TokeniserState tokeniserState) {
        if (this.errors.canAddError()) {
            ParseErrorList parseErrorList = this.errors;
            CharacterReader characterReader = this.reader;
            parseErrorList.add(new ParseError(characterReader, "Unexpected character '%s' in input state [%s]", Character.valueOf(characterReader.current()), tokeniserState));
        }
    }

    void eofError(TokeniserState tokeniserState) {
        if (this.errors.canAddError()) {
            this.errors.add(new ParseError(this.reader, "Unexpectedly reached end of file (EOF) in input state [%s]", tokeniserState));
        }
    }

    private void characterReferenceError(String str, Object... objArr) {
        if (this.errors.canAddError()) {
            this.errors.add(new ParseError(this.reader, String.format("Invalid character reference: " + str, objArr)));
        }
    }

    void error(String str) {
        if (this.errors.canAddError()) {
            this.errors.add(new ParseError(this.reader, str));
        }
    }

    void error(String str, Object... objArr) {
        if (this.errors.canAddError()) {
            this.errors.add(new ParseError(this.reader, str, objArr));
        }
    }

    String unescapeEntities(boolean z) {
        StringBuilder sbBorrowBuilder = StringUtil.borrowBuilder();
        while (!this.reader.isEmpty()) {
            sbBorrowBuilder.append(this.reader.consumeTo(Typography.amp));
            if (this.reader.matches(Typography.amp)) {
                this.reader.consume();
                int[] iArrConsumeCharacterReference = consumeCharacterReference(null, z);
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
        return StringUtil.releaseBuilder(sbBorrowBuilder);
    }
}
