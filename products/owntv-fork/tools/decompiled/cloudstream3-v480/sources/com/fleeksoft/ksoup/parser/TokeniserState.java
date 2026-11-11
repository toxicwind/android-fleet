package com.fleeksoft.ksoup.parser;

import androidx.media3.exoplayer.upstream.CmcdData;
import com.fleeksoft.ksoup.exception.UncheckedIOException;
import com.fleeksoft.ksoup.nodes.Document;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Typography;

/* JADX INFO: compiled from: TokeniserState.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\bG\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0081\u0002\u0018\u0000 N2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001NB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020MH&j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,j\u0002\b-j\u0002\b.j\u0002\b/j\u0002\b0j\u0002\b1j\u0002\b2j\u0002\b3j\u0002\b4j\u0002\b5j\u0002\b6j\u0002\b7j\u0002\b8j\u0002\b9j\u0002\b:j\u0002\b;j\u0002\b<j\u0002\b=j\u0002\b>j\u0002\b?j\u0002\b@j\u0002\bAj\u0002\bBj\u0002\bCj\u0002\bDj\u0002\bEj\u0002\bFj\u0002\bG¨\u0006O"}, d2 = {"Lcom/fleeksoft/ksoup/parser/TokeniserState;", "", "<init>", "(Ljava/lang/String;I)V", "Data", "CharacterReferenceInData", "Rcdata", "CharacterReferenceInRcdata", "Rawtext", "ScriptData", "PLAINTEXT", "TagOpen", "EndTagOpen", "TagName", "RcdataLessthanSign", "RCDATAEndTagOpen", "RCDATAEndTagName", "RawtextLessthanSign", "RawtextEndTagOpen", "RawtextEndTagName", "ScriptDataLessthanSign", "ScriptDataEndTagOpen", "ScriptDataEndTagName", "ScriptDataEscapeStart", "ScriptDataEscapeStartDash", "ScriptDataEscaped", "ScriptDataEscapedDash", "ScriptDataEscapedDashDash", "ScriptDataEscapedLessthanSign", "ScriptDataEscapedEndTagOpen", "ScriptDataEscapedEndTagName", "ScriptDataDoubleEscapeStart", "ScriptDataDoubleEscaped", "ScriptDataDoubleEscapedDash", "ScriptDataDoubleEscapedDashDash", "ScriptDataDoubleEscapedLessthanSign", "ScriptDataDoubleEscapeEnd", "BeforeAttributeName", "AttributeName", "AfterAttributeName", "BeforeAttributeValue", "AttributeValue_doubleQuoted", "AttributeValue_singleQuoted", "AttributeValue_unquoted", "AfterAttributeValue_quoted", "SelfClosingStartTag", "BogusComment", "MarkupDeclarationOpen", "MarkupProcessingOpen", "CommentStart", "CommentStartDash", "Comment", "CommentEndDash", "CommentEnd", "CommentEndBang", "Doctype", "BeforeDoctypeName", "DoctypeName", "AfterDoctypeName", "AfterDoctypePublicKeyword", "BeforeDoctypePublicIdentifier", "DoctypePublicIdentifier_doubleQuoted", "DoctypePublicIdentifier_singleQuoted", "AfterDoctypePublicIdentifier", "BetweenDoctypePublicAndSystemIdentifiers", "AfterDoctypeSystemKeyword", "BeforeDoctypeSystemIdentifier", "DoctypeSystemIdentifier_doubleQuoted", "DoctypeSystemIdentifier_singleQuoted", "AfterDoctypeSystemIdentifier", "BogusDoctype", "CdataSection", "read", "", "t", "Lcom/fleeksoft/ksoup/parser/Tokeniser;", "r", "Lcom/fleeksoft/ksoup/parser/CharacterReader;", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public enum TokeniserState {
    Data { // from class: com.fleeksoft.ksoup.parser.TokeniserState.Data
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            char cCurrent = r.current();
            if (cCurrent == 0) {
                t.error(this);
                t.emit(r.consume());
            } else {
                if (cCurrent == '&') {
                    t.advanceTransition(TokeniserState.CharacterReferenceInData);
                    return;
                }
                if (cCurrent == '<') {
                    t.advanceTransition(TokeniserState.TagOpen);
                } else if (cCurrent == 65535) {
                    t.emit(new Token.EOF());
                } else {
                    t.emit(r.consumeData());
                }
            }
        }
    },
    CharacterReferenceInData { // from class: com.fleeksoft.ksoup.parser.TokeniserState.CharacterReferenceInData
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            TokeniserState.INSTANCE.readCharRef(t, TokeniserState.Data);
        }
    },
    Rcdata { // from class: com.fleeksoft.ksoup.parser.TokeniserState.Rcdata
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            char cCurrent = r.current();
            if (cCurrent == 0) {
                t.error(this);
                r.advance();
                t.emit((char) 65533);
            } else {
                if (cCurrent == '&') {
                    t.advanceTransition(TokeniserState.CharacterReferenceInRcdata);
                    return;
                }
                if (cCurrent == '<') {
                    t.advanceTransition(TokeniserState.RcdataLessthanSign);
                } else if (cCurrent == 65535) {
                    t.emit(new Token.EOF());
                } else {
                    t.emit(r.consumeData());
                }
            }
        }
    },
    CharacterReferenceInRcdata { // from class: com.fleeksoft.ksoup.parser.TokeniserState.CharacterReferenceInRcdata
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            TokeniserState.INSTANCE.readCharRef(t, TokeniserState.Rcdata);
        }
    },
    Rawtext { // from class: com.fleeksoft.ksoup.parser.TokeniserState.Rawtext
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            TokeniserState.INSTANCE.readRawData(t, r, this, TokeniserState.RawtextLessthanSign);
        }
    },
    ScriptData { // from class: com.fleeksoft.ksoup.parser.TokeniserState.ScriptData
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            TokeniserState.INSTANCE.readRawData(t, r, this, TokeniserState.ScriptDataLessthanSign);
        }
    },
    PLAINTEXT { // from class: com.fleeksoft.ksoup.parser.TokeniserState.PLAINTEXT
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            char cCurrent = r.current();
            if (cCurrent == 0) {
                t.error(this);
                r.advance();
                t.emit((char) 65533);
            } else if (cCurrent == 65535) {
                t.emit(new Token.EOF());
            } else {
                t.emit(r.consumeTo((char) 0));
            }
        }
    },
    TagOpen { // from class: com.fleeksoft.ksoup.parser.TokeniserState.TagOpen
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            char cCurrent = r.current();
            if (cCurrent == '!') {
                t.advanceTransition(TokeniserState.MarkupDeclarationOpen);
                return;
            }
            if (cCurrent == '/') {
                t.advanceTransition(TokeniserState.EndTagOpen);
                return;
            }
            if (cCurrent == '?') {
                if (t.getSyntax() == Document.OutputSettings.Syntax.xml) {
                    t.advanceTransition(TokeniserState.MarkupProcessingOpen);
                    return;
                } else {
                    t.createBogusCommentPending();
                    t.transition(TokeniserState.BogusComment);
                    return;
                }
            }
            if (r.matchesAsciiAlpha()) {
                t.createTagPending(true);
                t.transition(TokeniserState.TagName);
            } else {
                t.error(this);
                t.emit(Typography.less);
                t.transition(TokeniserState.Data);
            }
        }
    },
    EndTagOpen { // from class: com.fleeksoft.ksoup.parser.TokeniserState.EndTagOpen
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            if (r.isEmpty()) {
                t.eofError(this);
                t.emit("</");
                t.transition(TokeniserState.Data);
            } else if (r.matchesAsciiAlpha()) {
                t.createTagPending(false);
                t.transition(TokeniserState.TagName);
            } else if (r.matches(Typography.greater)) {
                t.error(this);
                t.advanceTransition(TokeniserState.Data);
            } else {
                t.error(this);
                t.createBogusCommentPending();
                t.getCommentPending().append('/');
                t.transition(TokeniserState.BogusComment);
            }
        }
    },
    TagName { // from class: com.fleeksoft.ksoup.parser.TokeniserState.TagName
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            t.getTagPending().appendTagName(r.consumeTagName());
            char cConsume = r.consume();
            if (cConsume != 0) {
                if (cConsume != ' ') {
                    if (cConsume == '/') {
                        t.transition(TokeniserState.SelfClosingStartTag);
                        return;
                    }
                    if (cConsume == '>') {
                        t.emitTagPending();
                        t.transition(TokeniserState.Data);
                        return;
                    } else if (cConsume == 65535) {
                        t.eofError(this);
                        t.transition(TokeniserState.Data);
                        return;
                    } else if (cConsume != '\t' && cConsume != '\n' && cConsume != '\f' && cConsume != '\r') {
                        t.getTagPending().appendTagName(cConsume);
                        return;
                    }
                }
                t.transition(TokeniserState.BeforeAttributeName);
                return;
            }
            t.getTagPending().appendTagName(TokeniserState.replacementStr);
        }
    },
    RcdataLessthanSign { // from class: com.fleeksoft.ksoup.parser.TokeniserState.RcdataLessthanSign
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            if (r.matches('/')) {
                t.createTempBuffer();
                t.advanceTransition(TokeniserState.RCDATAEndTagOpen);
                return;
            }
            if (r.getReadFully() && r.matchesAsciiAlpha() && t.getLastStartTag() != null && !r.containsIgnoreCase(t.appropriateEndTagSeq())) {
                Token.Tag tagCreateTagPending = t.createTagPending(false);
                String strAppropriateEndTagName = t.getLastStartTag();
                if (strAppropriateEndTagName == null) {
                    strAppropriateEndTagName = "";
                }
                t.setTagPending(tagCreateTagPending.name(strAppropriateEndTagName));
                t.emitTagPending();
                t.transition(TokeniserState.TagOpen);
                return;
            }
            t.emit(Typography.less);
            t.transition(TokeniserState.Rcdata);
        }
    },
    RCDATAEndTagOpen { // from class: com.fleeksoft.ksoup.parser.TokeniserState.RCDATAEndTagOpen
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            if (r.matchesAsciiAlpha()) {
                t.createTagPending(false);
                t.getTagPending().appendTagName(r.current());
                t.getDataBuffer().append(r.current());
                t.advanceTransition(TokeniserState.RCDATAEndTagName);
                return;
            }
            t.emit("</");
            t.transition(TokeniserState.Rcdata);
        }
    },
    RCDATAEndTagName { // from class: com.fleeksoft.ksoup.parser.TokeniserState.RCDATAEndTagName
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            if (r.matchesAsciiAlpha()) {
                String strConsumeTagName = r.consumeTagName();
                t.getTagPending().appendTagName(strConsumeTagName);
                t.getDataBuffer().append(strConsumeTagName);
                return;
            }
            char cConsume = r.consume();
            if (cConsume == '\t' || cConsume == '\n' || cConsume == '\f' || cConsume == '\r' || cConsume == ' ') {
                if (t.isAppropriateEndTagToken()) {
                    t.transition(TokeniserState.BeforeAttributeName);
                    return;
                } else {
                    anythingElse(t, r);
                    return;
                }
            }
            if (cConsume == '/') {
                if (t.isAppropriateEndTagToken()) {
                    t.transition(TokeniserState.SelfClosingStartTag);
                    return;
                } else {
                    anythingElse(t, r);
                    return;
                }
            }
            if (cConsume == '>') {
                if (t.isAppropriateEndTagToken()) {
                    t.emitTagPending();
                    t.transition(TokeniserState.Data);
                    return;
                } else {
                    anythingElse(t, r);
                    return;
                }
            }
            anythingElse(t, r);
        }

        private final void anythingElse(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            t.emit("</");
            t.emit(t.getDataBuffer().value());
            r.unconsume();
            t.transition(TokeniserState.Rcdata);
        }
    },
    RawtextLessthanSign { // from class: com.fleeksoft.ksoup.parser.TokeniserState.RawtextLessthanSign
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            if (r.matches('/')) {
                t.createTempBuffer();
                t.advanceTransition(TokeniserState.RawtextEndTagOpen);
            } else {
                t.emit(Typography.less);
                t.transition(TokeniserState.Rawtext);
            }
        }
    },
    RawtextEndTagOpen { // from class: com.fleeksoft.ksoup.parser.TokeniserState.RawtextEndTagOpen
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            TokeniserState.INSTANCE.readEndTag(t, r, TokeniserState.RawtextEndTagName, TokeniserState.Rawtext);
        }
    },
    RawtextEndTagName { // from class: com.fleeksoft.ksoup.parser.TokeniserState.RawtextEndTagName
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            TokeniserState.INSTANCE.handleDataEndTag(t, r, TokeniserState.Rawtext);
        }
    },
    ScriptDataLessthanSign { // from class: com.fleeksoft.ksoup.parser.TokeniserState.ScriptDataLessthanSign
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            char cConsume = r.consume();
            if (cConsume == '!') {
                t.emit("<!");
                t.transition(TokeniserState.ScriptDataEscapeStart);
                return;
            }
            if (cConsume == '/') {
                t.createTempBuffer();
                t.transition(TokeniserState.ScriptDataEndTagOpen);
            } else if (cConsume == 65535) {
                t.emit(Typography.less);
                t.eofError(this);
                t.transition(TokeniserState.Data);
            } else {
                t.emit(Typography.less);
                r.unconsume();
                t.transition(TokeniserState.ScriptData);
            }
        }
    },
    ScriptDataEndTagOpen { // from class: com.fleeksoft.ksoup.parser.TokeniserState.ScriptDataEndTagOpen
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            TokeniserState.INSTANCE.readEndTag(t, r, TokeniserState.ScriptDataEndTagName, TokeniserState.ScriptData);
        }
    },
    ScriptDataEndTagName { // from class: com.fleeksoft.ksoup.parser.TokeniserState.ScriptDataEndTagName
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            TokeniserState.INSTANCE.handleDataEndTag(t, r, TokeniserState.ScriptData);
        }
    },
    ScriptDataEscapeStart { // from class: com.fleeksoft.ksoup.parser.TokeniserState.ScriptDataEscapeStart
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            if (r.matches('-')) {
                t.emit('-');
                t.advanceTransition(TokeniserState.ScriptDataEscapeStartDash);
            } else {
                t.transition(TokeniserState.ScriptData);
            }
        }
    },
    ScriptDataEscapeStartDash { // from class: com.fleeksoft.ksoup.parser.TokeniserState.ScriptDataEscapeStartDash
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            if (r.matches('-')) {
                t.emit('-');
                t.advanceTransition(TokeniserState.ScriptDataEscapedDashDash);
            } else {
                t.transition(TokeniserState.ScriptData);
            }
        }
    },
    ScriptDataEscaped { // from class: com.fleeksoft.ksoup.parser.TokeniserState.ScriptDataEscaped
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            if (r.isEmpty()) {
                t.eofError(this);
                t.transition(TokeniserState.Data);
                return;
            }
            char cCurrent = r.current();
            if (cCurrent == 0) {
                t.error(this);
                r.advance();
                t.emit((char) 65533);
            } else if (cCurrent == '-') {
                t.emit('-');
                t.advanceTransition(TokeniserState.ScriptDataEscapedDash);
            } else if (cCurrent == '<') {
                t.advanceTransition(TokeniserState.ScriptDataEscapedLessthanSign);
            } else {
                t.emit(r.consumeToAny('-', Typography.less, 0));
            }
        }
    },
    ScriptDataEscapedDash { // from class: com.fleeksoft.ksoup.parser.TokeniserState.ScriptDataEscapedDash
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            if (r.isEmpty()) {
                t.eofError(this);
                t.transition(TokeniserState.Data);
                return;
            }
            char cConsume = r.consume();
            if (cConsume == 0) {
                t.error(this);
                t.emit((char) 65533);
                t.transition(TokeniserState.ScriptDataEscaped);
            } else if (cConsume == '-') {
                t.emit(cConsume);
                t.transition(TokeniserState.ScriptDataEscapedDashDash);
            } else if (cConsume == '<') {
                t.transition(TokeniserState.ScriptDataEscapedLessthanSign);
            } else {
                t.emit(cConsume);
                t.transition(TokeniserState.ScriptDataEscaped);
            }
        }
    },
    ScriptDataEscapedDashDash { // from class: com.fleeksoft.ksoup.parser.TokeniserState.ScriptDataEscapedDashDash
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            if (r.isEmpty()) {
                t.eofError(this);
                t.transition(TokeniserState.Data);
                return;
            }
            char cConsume = r.consume();
            if (cConsume == 0) {
                t.error(this);
                t.emit((char) 65533);
                t.transition(TokeniserState.ScriptDataEscaped);
            } else {
                if (cConsume == '-') {
                    t.emit(cConsume);
                    return;
                }
                if (cConsume == '<') {
                    t.transition(TokeniserState.ScriptDataEscapedLessthanSign);
                } else if (cConsume == '>') {
                    t.emit(cConsume);
                    t.transition(TokeniserState.ScriptData);
                } else {
                    t.emit(cConsume);
                    t.transition(TokeniserState.ScriptDataEscaped);
                }
            }
        }
    },
    ScriptDataEscapedLessthanSign { // from class: com.fleeksoft.ksoup.parser.TokeniserState.ScriptDataEscapedLessthanSign
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            if (r.matchesAsciiAlpha()) {
                t.createTempBuffer();
                t.getDataBuffer().append(r.current());
                t.emit(Typography.less);
                t.emit(r.current());
                t.advanceTransition(TokeniserState.ScriptDataDoubleEscapeStart);
                return;
            }
            if (r.matches('/')) {
                t.createTempBuffer();
                t.advanceTransition(TokeniserState.ScriptDataEscapedEndTagOpen);
            } else {
                t.emit(Typography.less);
                t.transition(TokeniserState.ScriptDataEscaped);
            }
        }
    },
    ScriptDataEscapedEndTagOpen { // from class: com.fleeksoft.ksoup.parser.TokeniserState.ScriptDataEscapedEndTagOpen
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            if (r.matchesAsciiAlpha()) {
                t.createTagPending(false);
                t.getTagPending().appendTagName(r.current());
                t.getDataBuffer().append(r.current());
                t.advanceTransition(TokeniserState.ScriptDataEscapedEndTagName);
                return;
            }
            t.emit("</");
            t.transition(TokeniserState.ScriptDataEscaped);
        }
    },
    ScriptDataEscapedEndTagName { // from class: com.fleeksoft.ksoup.parser.TokeniserState.ScriptDataEscapedEndTagName
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            TokeniserState.INSTANCE.handleDataEndTag(t, r, TokeniserState.ScriptDataEscaped);
        }
    },
    ScriptDataDoubleEscapeStart { // from class: com.fleeksoft.ksoup.parser.TokeniserState.ScriptDataDoubleEscapeStart
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            TokeniserState.INSTANCE.handleDataDoubleEscapeTag(t, r, TokeniserState.ScriptDataDoubleEscaped, TokeniserState.ScriptDataEscaped);
        }
    },
    ScriptDataDoubleEscaped { // from class: com.fleeksoft.ksoup.parser.TokeniserState.ScriptDataDoubleEscaped
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            char cCurrent = r.current();
            if (cCurrent == 0) {
                t.error(this);
                r.advance();
                t.emit((char) 65533);
            } else if (cCurrent == '-') {
                t.emit(cCurrent);
                t.advanceTransition(TokeniserState.ScriptDataDoubleEscapedDash);
            } else if (cCurrent == '<') {
                t.emit(cCurrent);
                t.advanceTransition(TokeniserState.ScriptDataDoubleEscapedLessthanSign);
            } else if (cCurrent == 65535) {
                t.eofError(this);
                t.transition(TokeniserState.Data);
            } else {
                t.emit(r.consumeToAny('-', Typography.less, 0));
            }
        }
    },
    ScriptDataDoubleEscapedDash { // from class: com.fleeksoft.ksoup.parser.TokeniserState.ScriptDataDoubleEscapedDash
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            char cConsume = r.consume();
            if (cConsume == 0) {
                t.error(this);
                t.emit((char) 65533);
                t.transition(TokeniserState.ScriptDataDoubleEscaped);
            } else if (cConsume == '-') {
                t.emit(cConsume);
                t.transition(TokeniserState.ScriptDataDoubleEscapedDashDash);
            } else if (cConsume == '<') {
                t.emit(cConsume);
                t.transition(TokeniserState.ScriptDataDoubleEscapedLessthanSign);
            } else if (cConsume == 65535) {
                t.eofError(this);
                t.transition(TokeniserState.Data);
            } else {
                t.emit(cConsume);
                t.transition(TokeniserState.ScriptDataDoubleEscaped);
            }
        }
    },
    ScriptDataDoubleEscapedDashDash { // from class: com.fleeksoft.ksoup.parser.TokeniserState.ScriptDataDoubleEscapedDashDash
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            char cConsume = r.consume();
            if (cConsume == 0) {
                t.error(this);
                t.emit((char) 65533);
                t.transition(TokeniserState.ScriptDataDoubleEscaped);
                return;
            }
            if (cConsume == '-') {
                t.emit(cConsume);
                return;
            }
            if (cConsume == '<') {
                t.emit(cConsume);
                t.transition(TokeniserState.ScriptDataDoubleEscapedLessthanSign);
            } else if (cConsume == '>') {
                t.emit(cConsume);
                t.transition(TokeniserState.ScriptData);
            } else if (cConsume == 65535) {
                t.eofError(this);
                t.transition(TokeniserState.Data);
            } else {
                t.emit(cConsume);
                t.transition(TokeniserState.ScriptDataDoubleEscaped);
            }
        }
    },
    ScriptDataDoubleEscapedLessthanSign { // from class: com.fleeksoft.ksoup.parser.TokeniserState.ScriptDataDoubleEscapedLessthanSign
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            if (r.matches('/')) {
                t.emit('/');
                t.createTempBuffer();
                t.advanceTransition(TokeniserState.ScriptDataDoubleEscapeEnd);
                return;
            }
            t.transition(TokeniserState.ScriptDataDoubleEscaped);
        }
    },
    ScriptDataDoubleEscapeEnd { // from class: com.fleeksoft.ksoup.parser.TokeniserState.ScriptDataDoubleEscapeEnd
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            TokeniserState.INSTANCE.handleDataDoubleEscapeTag(t, r, TokeniserState.ScriptDataEscaped, TokeniserState.ScriptDataDoubleEscaped);
        }
    },
    BeforeAttributeName { // from class: com.fleeksoft.ksoup.parser.TokeniserState.BeforeAttributeName
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            char cConsume = r.consume();
            if (cConsume == 0) {
                r.unconsume();
                t.error(this);
                t.getTagPending().newAttribute();
                t.transition(TokeniserState.AttributeName);
                return;
            }
            if (cConsume != ' ') {
                if (cConsume != '\"' && cConsume != '\'') {
                    if (cConsume == '/') {
                        t.transition(TokeniserState.SelfClosingStartTag);
                        return;
                    }
                    if (cConsume == 65535) {
                        t.eofError(this);
                        t.transition(TokeniserState.Data);
                        return;
                    }
                    if (cConsume == '\t' || cConsume == '\n' || cConsume == '\f' || cConsume == '\r') {
                        return;
                    }
                    switch (cConsume) {
                        case '=':
                            break;
                        case '>':
                            t.emitTagPending();
                            t.transition(TokeniserState.Data);
                            break;
                        case '?':
                            if (!(t.getTagPending() instanceof Token.XmlDecl)) {
                                t.getTagPending().newAttribute();
                                r.unconsume();
                                t.transition(TokeniserState.AttributeName);
                            }
                            break;
                        default:
                            t.getTagPending().newAttribute();
                            r.unconsume();
                            t.transition(TokeniserState.AttributeName);
                            break;
                    }
                    return;
                }
                t.error(this);
                t.getTagPending().newAttribute();
                t.getTagPending().appendAttributeName(cConsume, r.pos() - 1, r.pos());
                t.transition(TokeniserState.AttributeName);
            }
        }
    },
    AttributeName { // from class: com.fleeksoft.ksoup.parser.TokeniserState.AttributeName
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            int iPos = r.pos();
            char[] attributeNameCharsSorted = TokeniserState.INSTANCE.getAttributeNameCharsSorted();
            t.getTagPending().appendAttributeName(r.consumeToAnySorted(Arrays.copyOf(attributeNameCharsSorted, attributeNameCharsSorted.length)), iPos, r.pos());
            int iPos2 = r.pos();
            char cConsume = r.consume();
            if (cConsume == '\t' || cConsume == '\n' || cConsume == '\f' || cConsume == '\r' || cConsume == ' ') {
                t.transition(TokeniserState.AfterAttributeName);
                return;
            }
            if (cConsume != '\"' && cConsume != '\'') {
                if (cConsume == '/') {
                    t.transition(TokeniserState.SelfClosingStartTag);
                    return;
                }
                if (cConsume != 65535) {
                    switch (cConsume) {
                        case '<':
                            break;
                        case '=':
                            t.transition(TokeniserState.BeforeAttributeValue);
                            break;
                        case '>':
                            t.emitTagPending();
                            t.transition(TokeniserState.Data);
                            break;
                        case '?':
                            if (t.getSyntax() == Document.OutputSettings.Syntax.xml && (t.getTagPending() instanceof Token.XmlDecl)) {
                                t.transition(TokeniserState.AfterAttributeName);
                            } else {
                                t.getTagPending().appendAttributeName(cConsume, iPos2, r.pos());
                            }
                            break;
                        default:
                            t.getTagPending().appendAttributeName(cConsume, iPos2, r.pos());
                            break;
                    }
                    return;
                }
                t.eofError(this);
                t.transition(TokeniserState.Data);
                return;
            }
            t.error(this);
            t.getTagPending().appendAttributeName(cConsume, iPos2, r.pos());
        }
    },
    AfterAttributeName { // from class: com.fleeksoft.ksoup.parser.TokeniserState.AfterAttributeName
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            char cConsume = r.consume();
            if (cConsume == 0) {
                t.error(this);
                t.getTagPending().appendAttributeName((char) 65533, r.pos() - 1, r.pos());
                t.transition(TokeniserState.AttributeName);
                return;
            }
            if (cConsume != ' ') {
                if (cConsume != '\"' && cConsume != '\'') {
                    if (cConsume == '/') {
                        t.transition(TokeniserState.SelfClosingStartTag);
                        return;
                    }
                    if (cConsume == 65535) {
                        t.eofError(this);
                        t.transition(TokeniserState.Data);
                        return;
                    }
                    if (cConsume == '\t' || cConsume == '\n' || cConsume == '\f' || cConsume == '\r') {
                        return;
                    }
                    switch (cConsume) {
                        case '<':
                            break;
                        case '=':
                            t.transition(TokeniserState.BeforeAttributeValue);
                            break;
                        case '>':
                            t.emitTagPending();
                            t.transition(TokeniserState.Data);
                            break;
                        default:
                            t.getTagPending().newAttribute();
                            r.unconsume();
                            t.transition(TokeniserState.AttributeName);
                            break;
                    }
                    return;
                }
                t.error(this);
                t.getTagPending().newAttribute();
                t.getTagPending().appendAttributeName(cConsume, r.pos() - 1, r.pos());
                t.transition(TokeniserState.AttributeName);
            }
        }
    },
    BeforeAttributeValue { // from class: com.fleeksoft.ksoup.parser.TokeniserState.BeforeAttributeValue
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            char cConsume = r.consume();
            if (cConsume == 0) {
                t.error(this);
                t.getTagPending().appendAttributeValue((char) 65533, r.pos() - 1, r.pos());
                t.transition(TokeniserState.AttributeValue_unquoted);
                return;
            }
            if (cConsume != ' ') {
                if (cConsume == '\"') {
                    t.transition(TokeniserState.AttributeValue_doubleQuoted);
                    return;
                }
                if (cConsume != '`') {
                    if (cConsume == 65535) {
                        t.eofError(this);
                        t.emitTagPending();
                        t.transition(TokeniserState.Data);
                        return;
                    }
                    if (cConsume == '\t' || cConsume == '\n' || cConsume == '\f' || cConsume == '\r') {
                        return;
                    }
                    if (cConsume == '&') {
                        r.unconsume();
                        t.transition(TokeniserState.AttributeValue_unquoted);
                        return;
                    }
                    if (cConsume == '\'') {
                        t.transition(TokeniserState.AttributeValue_singleQuoted);
                        return;
                    }
                    switch (cConsume) {
                        case '<':
                        case '=':
                            break;
                        case '>':
                            t.error(this);
                            t.emitTagPending();
                            t.transition(TokeniserState.Data);
                            break;
                        default:
                            r.unconsume();
                            t.transition(TokeniserState.AttributeValue_unquoted);
                            break;
                    }
                    return;
                }
                t.error(this);
                t.getTagPending().appendAttributeValue(cConsume, r.pos() - 1, r.pos());
                t.transition(TokeniserState.AttributeValue_unquoted);
            }
        }
    },
    AttributeValue_doubleQuoted { // from class: com.fleeksoft.ksoup.parser.TokeniserState.AttributeValue_doubleQuoted
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            int iPos = r.pos();
            String strConsumeAttributeQuoted = r.consumeAttributeQuoted(false);
            if (strConsumeAttributeQuoted.length() > 0) {
                t.getTagPending().appendAttributeValue(strConsumeAttributeQuoted, iPos, r.pos());
            } else {
                t.getTagPending().setEmptyAttributeValue();
            }
            int iPos2 = r.pos();
            char cConsume = r.consume();
            if (cConsume == 0) {
                t.error(this);
                t.getTagPending().appendAttributeValue((char) 65533, iPos2, r.pos());
                return;
            }
            if (cConsume == '\"') {
                t.transition(TokeniserState.AfterAttributeValue_quoted);
                return;
            }
            if (cConsume != '&') {
                if (cConsume == 65535) {
                    t.eofError(this);
                    t.transition(TokeniserState.Data);
                    return;
                } else {
                    t.getTagPending().appendAttributeValue(cConsume, iPos2, r.pos());
                    return;
                }
            }
            int[] iArrConsumeCharacterReference = t.consumeCharacterReference('\"', true);
            if (iArrConsumeCharacterReference != null) {
                t.getTagPending().appendAttributeValue(iArrConsumeCharacterReference, iPos2, r.pos());
            } else {
                t.getTagPending().appendAttributeValue(Typography.amp, iPos2, r.pos());
            }
        }
    },
    AttributeValue_singleQuoted { // from class: com.fleeksoft.ksoup.parser.TokeniserState.AttributeValue_singleQuoted
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            int iPos = r.pos();
            String strConsumeAttributeQuoted = r.consumeAttributeQuoted(true);
            if (strConsumeAttributeQuoted.length() > 0) {
                t.getTagPending().appendAttributeValue(strConsumeAttributeQuoted, iPos, r.pos());
            } else {
                t.getTagPending().setEmptyAttributeValue();
            }
            int iPos2 = r.pos();
            char cConsume = r.consume();
            if (cConsume == 0) {
                t.error(this);
                t.getTagPending().appendAttributeValue((char) 65533, iPos2, r.pos());
                return;
            }
            if (cConsume == 65535) {
                t.eofError(this);
                t.transition(TokeniserState.Data);
                return;
            }
            if (cConsume != '&') {
                if (cConsume == '\'') {
                    t.transition(TokeniserState.AfterAttributeValue_quoted);
                    return;
                } else {
                    t.getTagPending().appendAttributeValue(cConsume, iPos2, r.pos());
                    return;
                }
            }
            int[] iArrConsumeCharacterReference = t.consumeCharacterReference('\'', true);
            if (iArrConsumeCharacterReference != null) {
                t.getTagPending().appendAttributeValue(iArrConsumeCharacterReference, iPos2, r.pos());
            } else {
                t.getTagPending().appendAttributeValue(Typography.amp, iPos2, r.pos());
            }
        }
    },
    AttributeValue_unquoted { // from class: com.fleeksoft.ksoup.parser.TokeniserState.AttributeValue_unquoted
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            int iPos = r.pos();
            char[] attributeValueUnquoted = TokeniserState.INSTANCE.getAttributeValueUnquoted();
            String strConsumeToAnySorted = r.consumeToAnySorted(Arrays.copyOf(attributeValueUnquoted, attributeValueUnquoted.length));
            if (strConsumeToAnySorted.length() > 0) {
                t.getTagPending().appendAttributeValue(strConsumeToAnySorted, iPos, r.pos());
            }
            int iPos2 = r.pos();
            char cConsume = r.consume();
            if (cConsume != 0) {
                if (cConsume != ' ') {
                    if (cConsume != '\"' && cConsume != '`') {
                        if (cConsume == 65535) {
                            t.eofError(this);
                            t.transition(TokeniserState.Data);
                            return;
                        }
                        if (cConsume != '\t' && cConsume != '\n' && cConsume != '\f' && cConsume != '\r') {
                            if (cConsume == '&') {
                                int[] iArrConsumeCharacterReference = t.consumeCharacterReference(Character.valueOf(Typography.greater), true);
                                if (iArrConsumeCharacterReference != null) {
                                    t.getTagPending().appendAttributeValue(iArrConsumeCharacterReference, iPos2, r.pos());
                                    return;
                                } else {
                                    t.getTagPending().appendAttributeValue(Typography.amp, iPos2, r.pos());
                                    return;
                                }
                            }
                            if (cConsume != '\'') {
                                switch (cConsume) {
                                    case '<':
                                    case '=':
                                        break;
                                    case '>':
                                        t.emitTagPending();
                                        t.transition(TokeniserState.Data);
                                        break;
                                    default:
                                        t.getTagPending().appendAttributeValue(cConsume, iPos2, r.pos());
                                        break;
                                }
                                return;
                            }
                        }
                    }
                    t.error(this);
                    t.getTagPending().appendAttributeValue(cConsume, iPos2, r.pos());
                    return;
                }
                t.transition(TokeniserState.BeforeAttributeName);
                return;
            }
            t.error(this);
            t.getTagPending().appendAttributeValue((char) 65533, iPos2, r.pos());
        }
    },
    AfterAttributeValue_quoted { // from class: com.fleeksoft.ksoup.parser.TokeniserState.AfterAttributeValue_quoted
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            char cConsume = r.consume();
            if (cConsume == '\t' || cConsume == '\n' || cConsume == '\f' || cConsume == '\r' || cConsume == ' ') {
                t.transition(TokeniserState.BeforeAttributeName);
                return;
            }
            if (cConsume == '/') {
                t.transition(TokeniserState.SelfClosingStartTag);
                return;
            }
            if (cConsume == 65535) {
                t.eofError(this);
                t.transition(TokeniserState.Data);
                return;
            }
            if (cConsume == '>') {
                t.emitTagPending();
                t.transition(TokeniserState.Data);
            } else {
                if (cConsume == '?') {
                    if (t.getTagPending() instanceof Token.XmlDecl) {
                        return;
                    }
                    r.unconsume();
                    t.error(this);
                    t.transition(TokeniserState.BeforeAttributeName);
                    return;
                }
                r.unconsume();
                t.error(this);
                t.transition(TokeniserState.BeforeAttributeName);
            }
        }
    },
    SelfClosingStartTag { // from class: com.fleeksoft.ksoup.parser.TokeniserState.SelfClosingStartTag
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            char cConsume = r.consume();
            if (cConsume == '>') {
                t.getTagPending().setSelfClosing(true);
                t.emitTagPending();
                t.transition(TokeniserState.Data);
            } else if (cConsume == 65535) {
                t.eofError(this);
                t.transition(TokeniserState.Data);
            } else {
                r.unconsume();
                t.error(this);
                t.transition(TokeniserState.BeforeAttributeName);
            }
        }
    },
    BogusComment { // from class: com.fleeksoft.ksoup.parser.TokeniserState.BogusComment
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            t.getCommentPending().append(r.consumeTo(Typography.greater));
            char cCurrent = r.current();
            if (cCurrent == '>' || cCurrent == 65535) {
                r.consume();
                t.emitCommentPending();
                t.transition(TokeniserState.Data);
            }
        }
    },
    MarkupDeclarationOpen { // from class: com.fleeksoft.ksoup.parser.TokeniserState.MarkupDeclarationOpen
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            if (r.matchConsume("--")) {
                t.createCommentPending();
                t.transition(TokeniserState.CommentStart);
                return;
            }
            if (r.matchConsumeIgnoreCase("DOCTYPE")) {
                t.transition(TokeniserState.Doctype);
                return;
            }
            if (r.matchConsume("[CDATA[")) {
                t.createTempBuffer();
                t.transition(TokeniserState.CdataSection);
            } else if (t.getSyntax() == Document.OutputSettings.Syntax.xml && r.matchesAsciiAlpha()) {
                t.createXmlDeclPending(true);
                t.transition(TokeniserState.TagName);
            } else {
                t.error(this);
                t.createBogusCommentPending();
                t.transition(TokeniserState.BogusComment);
            }
        }
    },
    MarkupProcessingOpen { // from class: com.fleeksoft.ksoup.parser.TokeniserState.MarkupProcessingOpen
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            if (r.matchesAsciiAlpha()) {
                t.createXmlDeclPending(false);
                t.transition(TokeniserState.TagName);
            } else {
                t.error(this);
                t.createBogusCommentPending();
                t.getCommentPending().append('?');
                t.transition(TokeniserState.BogusComment);
            }
        }
    },
    CommentStart { // from class: com.fleeksoft.ksoup.parser.TokeniserState.CommentStart
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            char cConsume = r.consume();
            if (cConsume == 0) {
                t.error(this);
                t.getCommentPending().append((char) 65533);
                t.transition(TokeniserState.Comment);
                return;
            }
            if (cConsume == '-') {
                t.transition(TokeniserState.CommentStartDash);
                return;
            }
            if (cConsume == '>') {
                t.error(this);
                t.emitCommentPending();
                t.transition(TokeniserState.Data);
            } else if (cConsume == 65535) {
                t.eofError(this);
                t.emitCommentPending();
                t.transition(TokeniserState.Data);
            } else {
                r.unconsume();
                t.transition(TokeniserState.Comment);
            }
        }
    },
    CommentStartDash { // from class: com.fleeksoft.ksoup.parser.TokeniserState.CommentStartDash
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            char cConsume = r.consume();
            if (cConsume == 0) {
                t.error(this);
                t.getCommentPending().append((char) 65533);
                t.transition(TokeniserState.Comment);
                return;
            }
            if (cConsume == '-') {
                t.transition(TokeniserState.CommentEnd);
                return;
            }
            if (cConsume == '>') {
                t.error(this);
                t.emitCommentPending();
                t.transition(TokeniserState.Data);
            } else if (cConsume == 65535) {
                t.eofError(this);
                t.emitCommentPending();
                t.transition(TokeniserState.Data);
            } else {
                t.getCommentPending().append(cConsume);
                t.transition(TokeniserState.Comment);
            }
        }
    },
    Comment { // from class: com.fleeksoft.ksoup.parser.TokeniserState.Comment
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            char cCurrent = r.current();
            if (cCurrent == 0) {
                t.error(this);
                r.advance();
                t.getCommentPending().append((char) 65533);
            } else if (cCurrent == '-') {
                t.advanceTransition(TokeniserState.CommentEndDash);
            } else {
                if (cCurrent == 65535) {
                    t.eofError(this);
                    t.emitCommentPending();
                    t.transition(TokeniserState.Data);
                    return;
                }
                t.getCommentPending().append(r.consumeToAny('-', 0));
            }
        }
    },
    CommentEndDash { // from class: com.fleeksoft.ksoup.parser.TokeniserState.CommentEndDash
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            char cConsume = r.consume();
            if (cConsume == 0) {
                t.error(this);
                t.getCommentPending().append('-').append((char) 65533);
                t.transition(TokeniserState.Comment);
            } else {
                if (cConsume == '-') {
                    t.transition(TokeniserState.CommentEnd);
                    return;
                }
                if (cConsume == 65535) {
                    t.eofError(this);
                    t.emitCommentPending();
                    t.transition(TokeniserState.Data);
                } else {
                    t.getCommentPending().append('-').append(cConsume);
                    t.transition(TokeniserState.Comment);
                }
            }
        }
    },
    CommentEnd { // from class: com.fleeksoft.ksoup.parser.TokeniserState.CommentEnd
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            char cConsume = r.consume();
            if (cConsume == 0) {
                t.error(this);
                t.getCommentPending().append("--").append((char) 65533);
                t.transition(TokeniserState.Comment);
                return;
            }
            if (cConsume == '!') {
                t.transition(TokeniserState.CommentEndBang);
                return;
            }
            if (cConsume == '-') {
                t.getCommentPending().append('-');
                return;
            }
            if (cConsume == '>') {
                t.emitCommentPending();
                t.transition(TokeniserState.Data);
            } else if (cConsume == 65535) {
                t.eofError(this);
                t.emitCommentPending();
                t.transition(TokeniserState.Data);
            } else {
                t.getCommentPending().append("--").append(cConsume);
                t.transition(TokeniserState.Comment);
            }
        }
    },
    CommentEndBang { // from class: com.fleeksoft.ksoup.parser.TokeniserState.CommentEndBang
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            char cConsume = r.consume();
            if (cConsume == 0) {
                t.error(this);
                t.getCommentPending().append("--!").append((char) 65533);
                t.transition(TokeniserState.Comment);
                return;
            }
            if (cConsume == '-') {
                t.getCommentPending().append("--!");
                t.transition(TokeniserState.CommentEndDash);
                return;
            }
            if (cConsume == '>') {
                t.emitCommentPending();
                t.transition(TokeniserState.Data);
            } else if (cConsume == 65535) {
                t.eofError(this);
                t.emitCommentPending();
                t.transition(TokeniserState.Data);
            } else {
                t.getCommentPending().append("--!").append(cConsume);
                t.transition(TokeniserState.Comment);
            }
        }
    },
    Doctype { // from class: com.fleeksoft.ksoup.parser.TokeniserState.Doctype
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            char cConsume = r.consume();
            if (cConsume == '\t' || cConsume == '\n' || cConsume == '\f' || cConsume == '\r' || cConsume == ' ') {
                t.transition(TokeniserState.BeforeDoctypeName);
                return;
            }
            if (cConsume == '>') {
                t.error(this);
                t.createDoctypePending();
                t.getDoctypePending().setForceQuirks(true);
                t.emitDoctypePending();
                t.transition(TokeniserState.Data);
                return;
            }
            if (cConsume == 65535) {
                Doctype doctype = this;
                t.eofError(doctype);
                t.error(doctype);
                t.createDoctypePending();
                t.getDoctypePending().setForceQuirks(true);
                t.emitDoctypePending();
                t.transition(TokeniserState.Data);
                return;
            }
            t.error(this);
            t.transition(TokeniserState.BeforeDoctypeName);
        }
    },
    BeforeDoctypeName { // from class: com.fleeksoft.ksoup.parser.TokeniserState.BeforeDoctypeName
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            if (r.matchesAsciiAlpha()) {
                t.createDoctypePending();
                t.transition(TokeniserState.DoctypeName);
                return;
            }
            char cConsume = r.consume();
            if (cConsume == 0) {
                t.error(this);
                t.createDoctypePending();
                t.getDoctypePending().getName().append((char) 65533);
                t.transition(TokeniserState.DoctypeName);
                return;
            }
            if (cConsume != ' ') {
                if (cConsume == 65535) {
                    t.eofError(this);
                    t.createDoctypePending();
                    t.getDoctypePending().setForceQuirks(true);
                    t.emitDoctypePending();
                    t.transition(TokeniserState.Data);
                    return;
                }
                if (cConsume == '\t' || cConsume == '\n' || cConsume == '\f' || cConsume == '\r') {
                    return;
                }
                t.createDoctypePending();
                t.getDoctypePending().getName().append(cConsume);
                t.transition(TokeniserState.DoctypeName);
            }
        }
    },
    DoctypeName { // from class: com.fleeksoft.ksoup.parser.TokeniserState.DoctypeName
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            if (r.matchesAsciiAlpha()) {
                t.getDoctypePending().getName().append(r.consumeLetterSequence());
                return;
            }
            char cConsume = r.consume();
            if (cConsume != 0) {
                if (cConsume != ' ') {
                    if (cConsume == '>') {
                        t.emitDoctypePending();
                        t.transition(TokeniserState.Data);
                        return;
                    }
                    if (cConsume == 65535) {
                        t.eofError(this);
                        t.getDoctypePending().setForceQuirks(true);
                        t.emitDoctypePending();
                        t.transition(TokeniserState.Data);
                        return;
                    }
                    if (cConsume != '\t' && cConsume != '\n' && cConsume != '\f' && cConsume != '\r') {
                        t.getDoctypePending().getName().append(cConsume);
                        return;
                    }
                }
                t.transition(TokeniserState.AfterDoctypeName);
                return;
            }
            t.error(this);
            t.getDoctypePending().getName().append((char) 65533);
        }
    },
    AfterDoctypeName { // from class: com.fleeksoft.ksoup.parser.TokeniserState.AfterDoctypeName
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            if (r.isEmpty()) {
                t.eofError(this);
                t.getDoctypePending().setForceQuirks(true);
                t.emitDoctypePending();
                t.transition(TokeniserState.Data);
                return;
            }
            if (r.matchesAny('\t', '\n', '\r', '\f', ' ')) {
                r.advance();
                return;
            }
            if (r.matches(Typography.greater)) {
                t.emitDoctypePending();
                t.advanceTransition(TokeniserState.Data);
                return;
            }
            if (r.matchConsumeIgnoreCase("PUBLIC")) {
                t.getDoctypePending().setPubSysKey("PUBLIC");
                t.transition(TokeniserState.AfterDoctypePublicKeyword);
            } else if (r.matchConsumeIgnoreCase("SYSTEM")) {
                t.getDoctypePending().setPubSysKey("SYSTEM");
                t.transition(TokeniserState.AfterDoctypeSystemKeyword);
            } else {
                t.error(this);
                t.getDoctypePending().setForceQuirks(true);
                t.advanceTransition(TokeniserState.BogusDoctype);
            }
        }
    },
    AfterDoctypePublicKeyword { // from class: com.fleeksoft.ksoup.parser.TokeniserState.AfterDoctypePublicKeyword
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            char cConsume = r.consume();
            if (cConsume == '\t' || cConsume == '\n' || cConsume == '\f' || cConsume == '\r' || cConsume == ' ') {
                t.transition(TokeniserState.BeforeDoctypePublicIdentifier);
                return;
            }
            if (cConsume == '\"') {
                t.error(this);
                t.transition(TokeniserState.DoctypePublicIdentifier_doubleQuoted);
                return;
            }
            if (cConsume == '\'') {
                t.error(this);
                t.transition(TokeniserState.DoctypePublicIdentifier_singleQuoted);
                return;
            }
            if (cConsume == '>') {
                t.error(this);
                t.getDoctypePending().setForceQuirks(true);
                t.emitDoctypePending();
                t.transition(TokeniserState.Data);
                return;
            }
            if (cConsume == 65535) {
                t.eofError(this);
                t.getDoctypePending().setForceQuirks(true);
                t.emitDoctypePending();
                t.transition(TokeniserState.Data);
                return;
            }
            t.error(this);
            t.getDoctypePending().setForceQuirks(true);
            t.transition(TokeniserState.BogusDoctype);
        }
    },
    BeforeDoctypePublicIdentifier { // from class: com.fleeksoft.ksoup.parser.TokeniserState.BeforeDoctypePublicIdentifier
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            char cConsume = r.consume();
            if (cConsume == '\t' || cConsume == '\n' || cConsume == '\f' || cConsume == '\r' || cConsume == ' ') {
                return;
            }
            if (cConsume == '\"') {
                t.transition(TokeniserState.DoctypePublicIdentifier_doubleQuoted);
                return;
            }
            if (cConsume == '\'') {
                t.transition(TokeniserState.DoctypePublicIdentifier_singleQuoted);
                return;
            }
            if (cConsume == '>') {
                t.error(this);
                t.getDoctypePending().setForceQuirks(true);
                t.emitDoctypePending();
                t.transition(TokeniserState.Data);
                return;
            }
            if (cConsume == 65535) {
                t.eofError(this);
                t.getDoctypePending().setForceQuirks(true);
                t.emitDoctypePending();
                t.transition(TokeniserState.Data);
                return;
            }
            t.error(this);
            t.getDoctypePending().setForceQuirks(true);
            t.transition(TokeniserState.BogusDoctype);
        }
    },
    DoctypePublicIdentifier_doubleQuoted { // from class: com.fleeksoft.ksoup.parser.TokeniserState.DoctypePublicIdentifier_doubleQuoted
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            char cConsume = r.consume();
            if (cConsume == 0) {
                t.error(this);
                t.getDoctypePending().getPublicIdentifier().append((char) 65533);
                return;
            }
            if (cConsume == '\"') {
                t.transition(TokeniserState.AfterDoctypePublicIdentifier);
                return;
            }
            if (cConsume == '>') {
                t.error(this);
                t.getDoctypePending().setForceQuirks(true);
                t.emitDoctypePending();
                t.transition(TokeniserState.Data);
                return;
            }
            if (cConsume == 65535) {
                t.eofError(this);
                t.getDoctypePending().setForceQuirks(true);
                t.emitDoctypePending();
                t.transition(TokeniserState.Data);
                return;
            }
            t.getDoctypePending().getPublicIdentifier().append(cConsume);
        }
    },
    DoctypePublicIdentifier_singleQuoted { // from class: com.fleeksoft.ksoup.parser.TokeniserState.DoctypePublicIdentifier_singleQuoted
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            char cConsume = r.consume();
            if (cConsume == 0) {
                t.error(this);
                t.getDoctypePending().getPublicIdentifier().append((char) 65533);
                return;
            }
            if (cConsume == '\'') {
                t.transition(TokeniserState.AfterDoctypePublicIdentifier);
                return;
            }
            if (cConsume == '>') {
                t.error(this);
                t.getDoctypePending().setForceQuirks(true);
                t.emitDoctypePending();
                t.transition(TokeniserState.Data);
                return;
            }
            if (cConsume == 65535) {
                t.eofError(this);
                t.getDoctypePending().setForceQuirks(true);
                t.emitDoctypePending();
                t.transition(TokeniserState.Data);
                return;
            }
            t.getDoctypePending().getPublicIdentifier().append(cConsume);
        }
    },
    AfterDoctypePublicIdentifier { // from class: com.fleeksoft.ksoup.parser.TokeniserState.AfterDoctypePublicIdentifier
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            char cConsume = r.consume();
            if (cConsume == '\t' || cConsume == '\n' || cConsume == '\f' || cConsume == '\r' || cConsume == ' ') {
                t.transition(TokeniserState.BetweenDoctypePublicAndSystemIdentifiers);
                return;
            }
            if (cConsume == '\"') {
                t.error(this);
                t.transition(TokeniserState.DoctypeSystemIdentifier_doubleQuoted);
                return;
            }
            if (cConsume == '\'') {
                t.error(this);
                t.transition(TokeniserState.DoctypeSystemIdentifier_singleQuoted);
                return;
            }
            if (cConsume == '>') {
                t.emitDoctypePending();
                t.transition(TokeniserState.Data);
            } else {
                if (cConsume == 65535) {
                    t.eofError(this);
                    t.getDoctypePending().setForceQuirks(true);
                    t.emitDoctypePending();
                    t.transition(TokeniserState.Data);
                    return;
                }
                t.error(this);
                t.getDoctypePending().setForceQuirks(true);
                t.transition(TokeniserState.BogusDoctype);
            }
        }
    },
    BetweenDoctypePublicAndSystemIdentifiers { // from class: com.fleeksoft.ksoup.parser.TokeniserState.BetweenDoctypePublicAndSystemIdentifiers
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            char cConsume = r.consume();
            if (cConsume == '\t' || cConsume == '\n' || cConsume == '\f' || cConsume == '\r' || cConsume == ' ') {
                return;
            }
            if (cConsume == '\"') {
                t.error(this);
                t.transition(TokeniserState.DoctypeSystemIdentifier_doubleQuoted);
                return;
            }
            if (cConsume == '\'') {
                t.error(this);
                t.transition(TokeniserState.DoctypeSystemIdentifier_singleQuoted);
                return;
            }
            if (cConsume == '>') {
                t.emitDoctypePending();
                t.transition(TokeniserState.Data);
            } else {
                if (cConsume == 65535) {
                    t.eofError(this);
                    t.getDoctypePending().setForceQuirks(true);
                    t.emitDoctypePending();
                    t.transition(TokeniserState.Data);
                    return;
                }
                t.error(this);
                t.getDoctypePending().setForceQuirks(true);
                t.transition(TokeniserState.BogusDoctype);
            }
        }
    },
    AfterDoctypeSystemKeyword { // from class: com.fleeksoft.ksoup.parser.TokeniserState.AfterDoctypeSystemKeyword
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            char cConsume = r.consume();
            if (cConsume == '\t' || cConsume == '\n' || cConsume == '\f' || cConsume == '\r' || cConsume == ' ') {
                t.transition(TokeniserState.BeforeDoctypeSystemIdentifier);
                return;
            }
            if (cConsume == '\"') {
                t.error(this);
                t.transition(TokeniserState.DoctypeSystemIdentifier_doubleQuoted);
                return;
            }
            if (cConsume == '\'') {
                t.error(this);
                t.transition(TokeniserState.DoctypeSystemIdentifier_singleQuoted);
                return;
            }
            if (cConsume == '>') {
                t.error(this);
                t.getDoctypePending().setForceQuirks(true);
                t.emitDoctypePending();
                t.transition(TokeniserState.Data);
                return;
            }
            if (cConsume == 65535) {
                t.eofError(this);
                t.getDoctypePending().setForceQuirks(true);
                t.emitDoctypePending();
                t.transition(TokeniserState.Data);
                return;
            }
            t.error(this);
            t.getDoctypePending().setForceQuirks(true);
            t.emitDoctypePending();
        }
    },
    BeforeDoctypeSystemIdentifier { // from class: com.fleeksoft.ksoup.parser.TokeniserState.BeforeDoctypeSystemIdentifier
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            char cConsume = r.consume();
            if (cConsume == '\t' || cConsume == '\n' || cConsume == '\f' || cConsume == '\r' || cConsume == ' ') {
                return;
            }
            if (cConsume == '\"') {
                t.transition(TokeniserState.DoctypeSystemIdentifier_doubleQuoted);
                return;
            }
            if (cConsume == '\'') {
                t.transition(TokeniserState.DoctypeSystemIdentifier_singleQuoted);
                return;
            }
            if (cConsume == '>') {
                t.error(this);
                t.getDoctypePending().setForceQuirks(true);
                t.emitDoctypePending();
                t.transition(TokeniserState.Data);
                return;
            }
            if (cConsume == 65535) {
                t.eofError(this);
                t.getDoctypePending().setForceQuirks(true);
                t.emitDoctypePending();
                t.transition(TokeniserState.Data);
                return;
            }
            t.error(this);
            t.getDoctypePending().setForceQuirks(true);
            t.transition(TokeniserState.BogusDoctype);
        }
    },
    DoctypeSystemIdentifier_doubleQuoted { // from class: com.fleeksoft.ksoup.parser.TokeniserState.DoctypeSystemIdentifier_doubleQuoted
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            char cConsume = r.consume();
            if (cConsume == 0) {
                t.error(this);
                t.getDoctypePending().getSystemIdentifier().append((char) 65533);
                return;
            }
            if (cConsume == '\"') {
                t.transition(TokeniserState.AfterDoctypeSystemIdentifier);
                return;
            }
            if (cConsume == '>') {
                t.error(this);
                t.getDoctypePending().setForceQuirks(true);
                t.emitDoctypePending();
                t.transition(TokeniserState.Data);
                return;
            }
            if (cConsume == 65535) {
                t.eofError(this);
                t.getDoctypePending().setForceQuirks(true);
                t.emitDoctypePending();
                t.transition(TokeniserState.Data);
                return;
            }
            t.getDoctypePending().getSystemIdentifier().append(cConsume);
        }
    },
    DoctypeSystemIdentifier_singleQuoted { // from class: com.fleeksoft.ksoup.parser.TokeniserState.DoctypeSystemIdentifier_singleQuoted
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            char cConsume = r.consume();
            if (cConsume == 0) {
                t.error(this);
                t.getDoctypePending().getSystemIdentifier().append((char) 65533);
                return;
            }
            if (cConsume == '\'') {
                t.transition(TokeniserState.AfterDoctypeSystemIdentifier);
                return;
            }
            if (cConsume == '>') {
                t.error(this);
                t.getDoctypePending().setForceQuirks(true);
                t.emitDoctypePending();
                t.transition(TokeniserState.Data);
                return;
            }
            if (cConsume == 65535) {
                t.eofError(this);
                t.getDoctypePending().setForceQuirks(true);
                t.emitDoctypePending();
                t.transition(TokeniserState.Data);
                return;
            }
            t.getDoctypePending().getSystemIdentifier().append(cConsume);
        }
    },
    AfterDoctypeSystemIdentifier { // from class: com.fleeksoft.ksoup.parser.TokeniserState.AfterDoctypeSystemIdentifier
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            char cConsume = r.consume();
            if (cConsume == '\t' || cConsume == '\n' || cConsume == '\f' || cConsume == '\r' || cConsume == ' ') {
                return;
            }
            if (cConsume == '>') {
                t.emitDoctypePending();
                t.transition(TokeniserState.Data);
            } else {
                if (cConsume == 65535) {
                    t.eofError(this);
                    t.getDoctypePending().setForceQuirks(true);
                    t.emitDoctypePending();
                    t.transition(TokeniserState.Data);
                    return;
                }
                t.error(this);
                t.transition(TokeniserState.BogusDoctype);
            }
        }
    },
    BogusDoctype { // from class: com.fleeksoft.ksoup.parser.TokeniserState.BogusDoctype
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            char cConsume = r.consume();
            if (cConsume == '>') {
                t.emitDoctypePending();
                t.transition(TokeniserState.Data);
            } else {
                if (cConsume != 65535) {
                    return;
                }
                t.emitDoctypePending();
                t.transition(TokeniserState.Data);
            }
        }
    },
    CdataSection { // from class: com.fleeksoft.ksoup.parser.TokeniserState.CdataSection
        @Override // com.fleeksoft.ksoup.parser.TokeniserState
        public void read(Tokeniser t, CharacterReader r) throws UncheckedIOException {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(r, "r");
            t.getDataBuffer().append(r.consumeTo("]]>"));
            if (r.matchConsume("]]>") || r.isEmpty()) {
                t.emit(new Token.CData(t.getDataBuffer().value()));
                t.transition(TokeniserState.Data);
            }
        }
    };

    private static final char eof = 65535;
    public static final char nullChar = 0;
    private static final char replacementChar = 65533;
    private static final String replacementStr = "�";
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final char[] attributeNameCharsSorted = {'\t', '\n', '\f', '\r', ' ', '\"', '\'', '/', Typography.less, '=', Typography.greater, '?'};
    private static final char[] attributeValueUnquoted = {0, '\t', '\n', '\f', '\r', ' ', '\"', Typography.amp, '\'', Typography.less, '=', Typography.greater, '`'};

    /* synthetic */ TokeniserState(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public static EnumEntries<TokeniserState> getEntries() {
        return $ENTRIES;
    }

    public abstract void read(Tokeniser t, CharacterReader r);

    /* JADX INFO: compiled from: TokeniserState.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J(\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0017H\u0002J\u0018\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u0017H\u0002J(\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u0017H\u0002J(\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u0017H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u000e\u0010\f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/fleeksoft/ksoup/parser/TokeniserState$Companion;", "", "<init>", "()V", "nullChar", "", "attributeNameCharsSorted", "", "getAttributeNameCharsSorted", "()[C", "attributeValueUnquoted", "getAttributeValueUnquoted", "replacementChar", "replacementStr", "", "eof", "handleDataEndTag", "", "t", "Lcom/fleeksoft/ksoup/parser/Tokeniser;", "r", "Lcom/fleeksoft/ksoup/parser/CharacterReader;", "elseTransition", "Lcom/fleeksoft/ksoup/parser/TokeniserState;", "readRawData", "current", "advance", "readCharRef", "readEndTag", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "handleDataDoubleEscapeTag", "primary", "fallback", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final char[] getAttributeNameCharsSorted() {
            return TokeniserState.attributeNameCharsSorted;
        }

        public final char[] getAttributeValueUnquoted() {
            return TokeniserState.attributeValueUnquoted;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void handleDataEndTag(Tokeniser t, CharacterReader r, TokeniserState elseTransition) throws UncheckedIOException {
            if (r.matchesAsciiAlpha()) {
                String strConsumeTagName = r.consumeTagName();
                t.getTagPending().appendTagName(strConsumeTagName);
                t.getDataBuffer().append(strConsumeTagName);
                return;
            }
            if (t.isAppropriateEndTagToken() && !r.isEmpty()) {
                char cConsume = r.consume();
                if (cConsume == '\t' || cConsume == '\n' || cConsume == '\f' || cConsume == '\r' || cConsume == ' ') {
                    t.transition(TokeniserState.BeforeAttributeName);
                    return;
                }
                if (cConsume == '/') {
                    t.transition(TokeniserState.SelfClosingStartTag);
                    return;
                } else {
                    if (cConsume == '>') {
                        t.emitTagPending();
                        t.transition(TokeniserState.Data);
                        return;
                    }
                    t.getDataBuffer().append(cConsume);
                }
            }
            t.emit("</");
            t.emit(t.getDataBuffer().value());
            t.transition(elseTransition);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void readRawData(Tokeniser t, CharacterReader r, TokeniserState current, TokeniserState advance) throws UncheckedIOException {
            char cCurrent = r.current();
            if (cCurrent == 0) {
                t.error(current);
                r.advance();
                t.emit((char) 65533);
            } else if (cCurrent == '<') {
                t.advanceTransition(advance);
            } else if (cCurrent == 65535) {
                t.emit(new Token.EOF());
            } else {
                t.emit(r.consumeRawData());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void readCharRef(Tokeniser t, TokeniserState advance) {
            int[] iArrConsumeCharacterReference = t.consumeCharacterReference(null, false);
            if (iArrConsumeCharacterReference == null) {
                t.emit(Typography.amp);
            } else {
                t.emit(iArrConsumeCharacterReference);
            }
            t.transition(advance);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void readEndTag(Tokeniser t, CharacterReader r, TokeniserState a, TokeniserState b) {
            if (r.matchesAsciiAlpha()) {
                t.createTagPending(false);
                t.transition(a);
            } else {
                t.emit("</");
                t.transition(b);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void handleDataDoubleEscapeTag(Tokeniser t, CharacterReader r, TokeniserState primary, TokeniserState fallback) throws UncheckedIOException {
            if (r.matchesAsciiAlpha()) {
                String strConsumeLetterSequence = r.consumeLetterSequence();
                t.getDataBuffer().append(strConsumeLetterSequence);
                t.emit(strConsumeLetterSequence);
                return;
            }
            char cConsume = r.consume();
            if (cConsume == '\t' || cConsume == '\n' || cConsume == '\f' || cConsume == '\r' || cConsume == ' ' || cConsume == '/' || cConsume == '>') {
                if (Intrinsics.areEqual(t.getDataBuffer().value(), "script")) {
                    t.transition(primary);
                } else {
                    t.transition(fallback);
                }
                t.emit(cConsume);
                return;
            }
            r.unconsume();
            t.transition(fallback);
        }
    }
}
