package com.fleeksoft.ksoup.parser;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.fleeksoft.ksoup.helper.Validate;
import com.fleeksoft.ksoup.internal.StringUtil;
import com.fleeksoft.ksoup.nodes.Attribute;
import com.fleeksoft.ksoup.nodes.Attributes;
import com.fleeksoft.ksoup.nodes.Document;
import com.fleeksoft.ksoup.nodes.DocumentType;
import com.fleeksoft.ksoup.nodes.Element;
import com.fleeksoft.ksoup.nodes.Node;
import com.fleeksoft.ksoup.nodes.Range;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.ContentDisposition;
import io.ktor.http.ContentType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: HtmlTreeBuilderState.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0081\u0002\u0018\u0000 #2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0002\"#B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H&j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001b¨\u0006$"}, d2 = {"Lcom/fleeksoft/ksoup/parser/HtmlTreeBuilderState;", "", "<init>", "(Ljava/lang/String;I)V", "Initial", "BeforeHtml", "BeforeHead", "InHead", "InHeadNoscript", "AfterHead", "InBody", "Text", "InTable", "InTableText", "InCaption", "InColumnGroup", "InTableBody", "InRow", "InCell", "InSelect", "InSelectInTable", "InTemplate", "AfterBody", "InFrameset", "AfterFrameset", "AfterAfterBody", "AfterAfterFrameset", "ForeignContent", "process", "", "t", "Lcom/fleeksoft/ksoup/parser/Token;", "tb", "Lcom/fleeksoft/ksoup/parser/HtmlTreeBuilder;", "Constants", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public enum HtmlTreeBuilderState {
    Initial { // from class: com.fleeksoft.ksoup.parser.HtmlTreeBuilderState.Initial
        @Override // com.fleeksoft.ksoup.parser.HtmlTreeBuilderState
        public boolean process(Token t, HtmlTreeBuilder tb) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(tb, "tb");
            if (HtmlTreeBuilderState.INSTANCE.isWhitespace(t)) {
                return true;
            }
            if (t.isComment()) {
                tb.insertCommentNode(t.asComment());
            } else if (t.isDoctype()) {
                Token.Doctype doctypeAsDoctype = t.asDoctype();
                DocumentType documentType = new DocumentType(tb.getSettings().normalizeTag(doctypeAsDoctype.m8178getName()), doctypeAsDoctype.m8179getPublicIdentifier(), doctypeAsDoctype.m8180getSystemIdentifier());
                documentType.setPubSysKey(doctypeAsDoctype.getPubSysKey());
                DocumentType documentType2 = documentType;
                tb.getDocument().appendChild(documentType2);
                tb.onNodeInserted(documentType2);
                if (doctypeAsDoctype.getForceQuirks() || !Intrinsics.areEqual(documentType.name(), "html") || StringsKt.equals(documentType.publicId(), "HTML", true)) {
                    tb.getDocument().quirksMode(Document.QuirksMode.quirks);
                }
                tb.transition(HtmlTreeBuilderState.BeforeHtml);
            } else {
                tb.getDocument().quirksMode(Document.QuirksMode.quirks);
                tb.transition(HtmlTreeBuilderState.BeforeHtml);
                return tb.process(t);
            }
            return true;
        }
    },
    BeforeHtml { // from class: com.fleeksoft.ksoup.parser.HtmlTreeBuilderState.BeforeHtml
        @Override // com.fleeksoft.ksoup.parser.HtmlTreeBuilderState
        public boolean process(Token t, HtmlTreeBuilder tb) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(tb, "tb");
            if (t.isDoctype()) {
                tb.error(this);
                return false;
            }
            if (t.isComment()) {
                tb.insertCommentNode(t.asComment());
                return true;
            }
            if (HtmlTreeBuilderState.INSTANCE.isWhitespace(t)) {
                tb.insertCharacterNode(t.asCharacter());
                return true;
            }
            if (t.isStartTag() && Intrinsics.areEqual(t.asStartTag().retrieveNormalName(), "html")) {
                tb.insertElementFor(t.asStartTag());
                tb.transition(HtmlTreeBuilderState.BeforeHead);
                return true;
            }
            if (t.isEndTag() && StringUtil.INSTANCE.inSorted(t.asEndTag().retrieveNormalName(), Constants.INSTANCE.getBeforeHtmlToHead())) {
                return anythingElse(t, tb);
            }
            if (t.isEndTag()) {
                tb.error(this);
                return false;
            }
            return anythingElse(t, tb);
        }

        private final boolean anythingElse(Token t, HtmlTreeBuilder tb) {
            tb.processStartTag("html");
            tb.transition(HtmlTreeBuilderState.BeforeHead);
            return tb.process(t);
        }
    },
    BeforeHead { // from class: com.fleeksoft.ksoup.parser.HtmlTreeBuilderState.BeforeHead
        @Override // com.fleeksoft.ksoup.parser.HtmlTreeBuilderState
        public boolean process(Token t, HtmlTreeBuilder tb) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(tb, "tb");
            if (HtmlTreeBuilderState.INSTANCE.isWhitespace(t)) {
                tb.insertCharacterNode(t.asCharacter());
                return true;
            }
            if (t.isComment()) {
                tb.insertCommentNode(t.asComment());
                return true;
            }
            if (t.isDoctype()) {
                tb.error(this);
                return false;
            }
            if (t.isStartTag() && Intrinsics.areEqual(t.asStartTag().retrieveNormalName(), "html")) {
                return HtmlTreeBuilderState.InBody.process(t, tb);
            }
            if (t.isStartTag() && Intrinsics.areEqual(t.asStartTag().retrieveNormalName(), TtmlNode.TAG_HEAD)) {
                tb.setHeadElement(tb.insertElementFor(t.asStartTag()));
                tb.transition(HtmlTreeBuilderState.InHead);
                return true;
            }
            if (t.isEndTag() && StringUtil.INSTANCE.inSorted(t.asEndTag().retrieveNormalName(), Constants.INSTANCE.getBeforeHtmlToHead())) {
                tb.processStartTag(TtmlNode.TAG_HEAD);
                return tb.process(t);
            }
            if (t.isEndTag()) {
                tb.error(this);
                return false;
            }
            tb.processStartTag(TtmlNode.TAG_HEAD);
            return tb.process(t);
        }
    },
    InHead { // from class: com.fleeksoft.ksoup.parser.HtmlTreeBuilderState.InHead

        /* JADX INFO: compiled from: HtmlTreeBuilderState.kt */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[Token.TokenType.values().length];
                try {
                    iArr[Token.TokenType.Comment.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[Token.TokenType.Doctype.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[Token.TokenType.StartTag.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[Token.TokenType.EndTag.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        @Override // com.fleeksoft.ksoup.parser.HtmlTreeBuilderState
        public boolean process(Token t, HtmlTreeBuilder tb) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(tb, "tb");
            if (HtmlTreeBuilderState.INSTANCE.isWhitespace(t)) {
                tb.insertCharacterNode(t.asCharacter());
                return true;
            }
            int i = WhenMappings.$EnumSwitchMapping$0[t.getType().ordinal()];
            if (i == 1) {
                tb.insertCommentNode(t.asComment());
            } else {
                if (i == 2) {
                    tb.error(this);
                    return false;
                }
                if (i == 3) {
                    Token.StartTag startTagAsStartTag = t.asStartTag();
                    String strRetrieveNormalName = startTagAsStartTag.retrieveNormalName();
                    if (Intrinsics.areEqual(strRetrieveNormalName, "html")) {
                        return HtmlTreeBuilderState.InBody.process(t, tb);
                    }
                    if (StringUtil.INSTANCE.inSorted(strRetrieveNormalName, Constants.INSTANCE.getInHeadEmpty())) {
                        Element elementInsertEmptyElementFor = tb.insertEmptyElementFor(startTagAsStartTag);
                        if (Intrinsics.areEqual(strRetrieveNormalName, TtmlNode.RUBY_BASE) && elementInsertEmptyElementFor.hasAttr("href")) {
                            tb.maybeSetBaseUri(elementInsertEmptyElementFor);
                        }
                    } else if (Intrinsics.areEqual(strRetrieveNormalName, "meta")) {
                        tb.insertEmptyElementFor(startTagAsStartTag);
                    } else if (!Intrinsics.areEqual(strRetrieveNormalName, "title") && !StringUtil.INSTANCE.inSorted(strRetrieveNormalName, Constants.INSTANCE.getInHeadRaw())) {
                        if (Intrinsics.areEqual(strRetrieveNormalName, "noscript")) {
                            tb.insertElementFor(startTagAsStartTag);
                            tb.transition(HtmlTreeBuilderState.InHeadNoscript);
                        } else if (Intrinsics.areEqual(strRetrieveNormalName, "script")) {
                            Tokeniser tokeniser = tb.getTokeniser();
                            Intrinsics.checkNotNull(tokeniser);
                            tokeniser.transition(TokeniserState.ScriptData);
                            tb.markInsertionMode();
                            tb.transition(HtmlTreeBuilderState.Text);
                            tb.insertElementFor(startTagAsStartTag);
                        } else {
                            if (Intrinsics.areEqual(strRetrieveNormalName, TtmlNode.TAG_HEAD)) {
                                tb.error(this);
                                return false;
                            }
                            if (Intrinsics.areEqual(strRetrieveNormalName, "template")) {
                                tb.insertElementFor(startTagAsStartTag);
                                tb.insertMarkerToFormattingElements();
                                tb.framesetOk(false);
                                tb.transition(HtmlTreeBuilderState.InTemplate);
                                tb.pushTemplateMode(HtmlTreeBuilderState.InTemplate);
                            } else {
                                return anythingElse(t, tb);
                            }
                        }
                    } else {
                        HtmlTreeBuilderState.INSTANCE.handleTextState(startTagAsStartTag, tb, tb.tagFor(startTagAsStartTag).textState());
                    }
                } else if (i == 4) {
                    String strRetrieveNormalName2 = t.asEndTag().retrieveNormalName();
                    if (Intrinsics.areEqual(strRetrieveNormalName2, TtmlNode.TAG_HEAD)) {
                        tb.pop();
                        tb.transition(HtmlTreeBuilderState.AfterHead);
                    } else {
                        if (StringUtil.INSTANCE.inSorted(strRetrieveNormalName2, Constants.INSTANCE.getInHeadEnd())) {
                            return anythingElse(t, tb);
                        }
                        if (Intrinsics.areEqual(strRetrieveNormalName2, "template")) {
                            if (!tb.onStack(strRetrieveNormalName2)) {
                                tb.error(this);
                            } else {
                                tb.generateImpliedEndTags(true);
                                if (!tb.currentElementIs(strRetrieveNormalName2)) {
                                    tb.error(this);
                                }
                                tb.popStackToClose(strRetrieveNormalName2);
                                tb.clearFormattingElementsToLastMarker();
                                tb.popTemplateMode();
                                tb.resetInsertionMode();
                            }
                        } else {
                            tb.error(this);
                            return false;
                        }
                    }
                } else {
                    return anythingElse(t, tb);
                }
            }
            return true;
        }

        private final boolean anythingElse(Token t, TreeBuilder tb) {
            tb.processEndTag(TtmlNode.TAG_HEAD);
            return tb.process(t);
        }
    },
    InHeadNoscript { // from class: com.fleeksoft.ksoup.parser.HtmlTreeBuilderState.InHeadNoscript
        @Override // com.fleeksoft.ksoup.parser.HtmlTreeBuilderState
        public boolean process(Token t, HtmlTreeBuilder tb) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(tb, "tb");
            if (t.isDoctype()) {
                tb.error(this);
                return true;
            }
            if (t.isStartTag() && Intrinsics.areEqual(t.asStartTag().retrieveNormalName(), "html")) {
                return tb.process(t, HtmlTreeBuilderState.InBody);
            }
            if (t.isEndTag() && Intrinsics.areEqual(t.asEndTag().retrieveNormalName(), "noscript")) {
                tb.pop();
                tb.transition(HtmlTreeBuilderState.InHead);
                return true;
            }
            if (HtmlTreeBuilderState.INSTANCE.isWhitespace(t) || t.isComment() || (t.isStartTag() && StringUtil.INSTANCE.inSorted(t.asStartTag().retrieveNormalName(), Constants.INSTANCE.getInHeadNoScriptHead()))) {
                return tb.process(t, HtmlTreeBuilderState.InHead);
            }
            if (t.isEndTag() && Intrinsics.areEqual(t.asEndTag().retrieveNormalName(), "br")) {
                return anythingElse(t, tb);
            }
            if ((t.isStartTag() && StringUtil.INSTANCE.inSorted(t.asStartTag().retrieveNormalName(), Constants.INSTANCE.getInHeadNoscriptIgnore())) || t.isEndTag()) {
                tb.error(this);
                return false;
            }
            return anythingElse(t, tb);
        }

        private final boolean anythingElse(Token t, HtmlTreeBuilder tb) {
            tb.error(this);
            tb.insertCharacterNode(new Token.Character().data(t.toString()));
            return true;
        }
    },
    AfterHead { // from class: com.fleeksoft.ksoup.parser.HtmlTreeBuilderState.AfterHead
        @Override // com.fleeksoft.ksoup.parser.HtmlTreeBuilderState
        public boolean process(Token t, HtmlTreeBuilder tb) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(tb, "tb");
            if (HtmlTreeBuilderState.INSTANCE.isWhitespace(t)) {
                tb.insertCharacterNode(t.asCharacter());
                return true;
            }
            if (t.isComment()) {
                tb.insertCommentNode(t.asComment());
                return true;
            }
            if (t.isDoctype()) {
                tb.error(this);
                return true;
            }
            if (t.isStartTag()) {
                Token.StartTag startTagAsStartTag = t.asStartTag();
                String strRetrieveNormalName = startTagAsStartTag.retrieveNormalName();
                if (Intrinsics.areEqual(strRetrieveNormalName, "html")) {
                    return tb.process(t, HtmlTreeBuilderState.InBody);
                }
                if (Intrinsics.areEqual(strRetrieveNormalName, TtmlNode.TAG_BODY)) {
                    tb.insertElementFor(startTagAsStartTag);
                    tb.framesetOk(false);
                    tb.transition(HtmlTreeBuilderState.InBody);
                    return true;
                }
                if (Intrinsics.areEqual(strRetrieveNormalName, "frameset")) {
                    tb.insertElementFor(startTagAsStartTag);
                    tb.transition(HtmlTreeBuilderState.InFrameset);
                    return true;
                }
                if (StringUtil.INSTANCE.inSorted(strRetrieveNormalName, Constants.INSTANCE.getInBodyStartToHead())) {
                    tb.error(this);
                    Element headElement = tb.getHeadElement();
                    Intrinsics.checkNotNull(headElement);
                    tb.push(headElement);
                    tb.process(t, HtmlTreeBuilderState.InHead);
                    tb.removeFromStack(headElement);
                    return true;
                }
                if (Intrinsics.areEqual(strRetrieveNormalName, TtmlNode.TAG_HEAD)) {
                    tb.error(this);
                    return false;
                }
                anythingElse(t, tb);
                return true;
            }
            if (t.isEndTag()) {
                String strRetrieveNormalName2 = t.asEndTag().retrieveNormalName();
                if (StringUtil.INSTANCE.inSorted(strRetrieveNormalName2, Constants.INSTANCE.getAfterHeadBody())) {
                    anythingElse(t, tb);
                    return true;
                }
                if (Intrinsics.areEqual(strRetrieveNormalName2, "template")) {
                    tb.process(t, HtmlTreeBuilderState.InHead);
                    return true;
                }
                tb.error(this);
                return false;
            }
            anythingElse(t, tb);
            return true;
        }

        private final boolean anythingElse(Token t, HtmlTreeBuilder tb) {
            tb.processStartTag(TtmlNode.TAG_BODY);
            tb.framesetOk(true);
            return tb.process(t);
        }
    },
    InBody { // from class: com.fleeksoft.ksoup.parser.HtmlTreeBuilderState.InBody
        private final int MaxStackScan = 24;

        /* JADX INFO: compiled from: HtmlTreeBuilderState.kt */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[Token.TokenType.values().length];
                try {
                    iArr[Token.TokenType.Character.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[Token.TokenType.Comment.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[Token.TokenType.Doctype.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[Token.TokenType.StartTag.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[Token.TokenType.EndTag.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr[Token.TokenType.EOF.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        @Override // com.fleeksoft.ksoup.parser.HtmlTreeBuilderState
        public boolean process(Token t, HtmlTreeBuilder tb) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(tb, "tb");
            switch (WhenMappings.$EnumSwitchMapping$0[t.getType().ordinal()]) {
                case 1:
                    Token.Character characterAsCharacter = t.asCharacter();
                    if (tb.getFramesetOk() && HtmlTreeBuilderState.INSTANCE.isWhitespace(characterAsCharacter)) {
                        tb.reconstructFormattingElements();
                        tb.insertCharacterNode(characterAsCharacter);
                        return true;
                    }
                    tb.reconstructFormattingElements();
                    tb.insertCharacterNode(characterAsCharacter);
                    tb.framesetOk(false);
                    return true;
                case 2:
                    tb.insertCommentNode(t.asComment());
                    return true;
                case 3:
                    tb.error(this);
                    return false;
                case 4:
                    return inBodyStartTag(t, tb);
                case 5:
                    return inBodyEndTag(t, tb);
                case 6:
                    if (tb.templateModeSize() > 0) {
                        return tb.process(t, HtmlTreeBuilderState.InTemplate);
                    }
                    if (!tb.onStackNot(Constants.INSTANCE.getInBodyEndOtherErrors())) {
                        return true;
                    }
                    tb.error(this);
                    return true;
                default:
                    Validate.INSTANCE.wtf("Unexpected state: " + t.getType());
                    return true;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code duplicated, block: B:160:0x02d0  */
        /* JADX WARN: Code duplicated, block: B:162:0x02d6  */
        /* JADX WARN: Code duplicated, block: B:179:0x031a  */
        /* JADX WARN: Code duplicated, block: B:181:0x0321  */
        /* JADX WARN: Code duplicated, block: B:190:0x0344  */
        /* JADX WARN: Code duplicated, block: B:192:0x034b  */
        /* JADX WARN: Code duplicated, block: B:194:0x0354  */
        /* JADX WARN: Code duplicated, block: B:212:0x03ad  */
        /* JADX WARN: Code duplicated, block: B:230:0x03ee  */
        /* JADX WARN: Code duplicated, block: B:232:0x03fe  */
        /* JADX WARN: Code duplicated, block: B:234:0x0402  */
        /* JADX WARN: Code duplicated, block: B:240:0x0425  */
        /* JADX WARN: Code duplicated, block: B:248:0x0449  */
        /* JADX WARN: Code duplicated, block: B:28:0x006f  */
        /* JADX WARN: Code duplicated, block: B:296:0x04fe  */
        /* JADX WARN: Code duplicated, block: B:298:0x0504  */
        /* JADX WARN: Code duplicated, block: B:303:0x0519  */
        /* JADX WARN: Code duplicated, block: B:30:0x0075  */
        /* JADX WARN: Code duplicated, block: B:33:0x008e  */
        /* JADX WARN: Code duplicated, block: B:341:0x05d8  */
        /* JADX WARN: Code duplicated, block: B:344:0x05ec  */
        /* JADX WARN: Code duplicated, block: B:346:0x05f9  */
        /* JADX WARN: Code duplicated, block: B:347:0x0600  */
        /* JADX WARN: Code duplicated, block: B:349:0x0606  */
        /* JADX WARN: Code duplicated, block: B:350:0x060b  */
        /* JADX WARN: Code duplicated, block: B:352:0x0619  */
        /* JADX WARN: Code duplicated, block: B:354:0x061f  */
        /* JADX WARN: Code duplicated, block: B:356:0x0627  */
        /* JADX WARN: Code duplicated, block: B:358:0x0635  */
        /* JADX WARN: Code duplicated, block: B:360:0x063e  */
        /* JADX WARN: Code duplicated, block: B:362:0x064c  */
        /* JADX WARN: Code duplicated, block: B:363:0x0659  */
        /* JADX WARN: Code duplicated, block: B:365:0x0667  */
        /* JADX WARN: Code duplicated, block: B:366:0x066b  */
        /* JADX WARN: Code duplicated, block: B:368:0x0679  */
        /* JADX WARN: Code duplicated, block: B:370:0x067f  */
        /* JADX WARN: Code duplicated, block: B:384:0x040b A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:386:0x041d A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:388:0x0400 A[SYNTHETIC] */
        /* JADX WARN: Code restructure failed: missing block: B:323:0x059a, code lost:
        
            if (r3.nameIs(androidx.media3.extractor.text.ttml.TtmlNode.TAG_BODY) == false) goto L337;
         */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private final boolean inBodyStartTag(com.fleeksoft.ksoup.parser.Token r17, com.fleeksoft.ksoup.parser.HtmlTreeBuilder r18) throws com.fleeksoft.ksoup.exception.UncheckedIOException {
            /*
                Method dump skipped, instruction units count: 1914
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fleeksoft.ksoup.parser.HtmlTreeBuilderState.InBody.inBodyStartTag(com.fleeksoft.ksoup.parser.Token, com.fleeksoft.ksoup.parser.HtmlTreeBuilder):boolean");
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code duplicated, block: B:104:0x01aa  */
        /* JADX WARN: Code duplicated, block: B:106:0x01b0  */
        /* JADX WARN: Code duplicated, block: B:108:0x01b6  */
        /* JADX WARN: Code duplicated, block: B:110:0x01bf  */
        /* JADX WARN: Code duplicated, block: B:130:0x0212  */
        /* JADX WARN: Code duplicated, block: B:132:0x0220  */
        /* JADX WARN: Code duplicated, block: B:134:0x0225  */
        /* JADX WARN: Code duplicated, block: B:136:0x0233  */
        /* JADX WARN: Code duplicated, block: B:138:0x0239  */
        /* JADX WARN: Code duplicated, block: B:140:0x023f  */
        /* JADX WARN: Code duplicated, block: B:142:0x0248  */
        /* JADX WARN: Code duplicated, block: B:144:0x0251  */
        /* JADX WARN: Code duplicated, block: B:146:0x025f  */
        /* JADX WARN: Code duplicated, block: B:148:0x0267  */
        /* JADX WARN: Code duplicated, block: B:150:0x026d  */
        /* JADX WARN: Code duplicated, block: B:152:0x0273  */
        /* JADX WARN: Code duplicated, block: B:154:0x027c  */
        /* JADX WARN: Code duplicated, block: B:157:0x028a  */
        /* JADX WARN: Code duplicated, block: B:24:0x0058  */
        /* JADX WARN: Code duplicated, block: B:26:0x0064  */
        /* JADX WARN: Code duplicated, block: B:28:0x006a  */
        /* JADX WARN: Code duplicated, block: B:30:0x0073  */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0092, code lost:
        
            if (r1.equals("sarcasm") == false) goto L130;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x009c, code lost:
        
            if (r1.equals(androidx.media3.extractor.text.ttml.TtmlNode.TAG_SPAN) == false) goto L130;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x00a4, code lost:
        
            return anyOtherEndTag(r10, r11);
         */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private final boolean inBodyEndTag(com.fleeksoft.ksoup.parser.Token r10, com.fleeksoft.ksoup.parser.HtmlTreeBuilder r11) {
            /*
                Method dump skipped, instruction units count: 728
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fleeksoft.ksoup.parser.HtmlTreeBuilderState.InBody.inBodyEndTag(com.fleeksoft.ksoup.parser.Token, com.fleeksoft.ksoup.parser.HtmlTreeBuilder):boolean");
        }

        public final boolean anyOtherEndTag(Token t, HtmlTreeBuilder tb) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(tb, "tb");
            String normalName$ksoup_release = t.asEndTag().getNormalName();
            Intrinsics.checkNotNull(normalName$ksoup_release);
            ArrayList<Element> stack = tb.getStack();
            ArrayList arrayList = new ArrayList();
            for (Element element : stack) {
                if (element != null) {
                    arrayList.add(element);
                }
            }
            Element[] elementArr = (Element[]) arrayList.toArray(new Element[0]);
            ArrayList arrayListArrayListOf = CollectionsKt.arrayListOf(Arrays.copyOf(elementArr, elementArr.length));
            if (tb.getFromStack(normalName$ksoup_release) == null) {
                tb.error(this);
                return false;
            }
            int size = arrayListArrayListOf.size() - 1;
            if (size < 0) {
                return true;
            }
            while (true) {
                int i = size - 1;
                Object obj = arrayListArrayListOf.get(size);
                Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
                Element element2 = (Element) obj;
                if (element2.nameIs(normalName$ksoup_release)) {
                    tb.generateImpliedEndTags(normalName$ksoup_release);
                    if (!tb.currentElementIs(normalName$ksoup_release)) {
                        tb.error(this);
                    }
                    tb.popStackToClose(normalName$ksoup_release);
                    return true;
                }
                if (HtmlTreeBuilder.INSTANCE.isSpecial(element2)) {
                    tb.error(this);
                    return false;
                }
                if (i < 0) {
                    return true;
                }
                size = i;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        private final boolean inBodyEndTagAdoption(Token t, HtmlTreeBuilder tb) {
            Element element;
            Element element2;
            String normalName$ksoup_release = t.asEndTag().getNormalName();
            if (Intrinsics.areEqual(tb.currentElement().normalName(), normalName$ksoup_release) && !tb.isInActiveFormattingElements(tb.currentElement())) {
                tb.pop();
                return true;
            }
            boolean z = false;
            int i = 0;
            while (i < 8) {
                i++;
                int size = tb.getFormattingElements().size() - 1;
                while (true) {
                    element = null;
                    if (-1 >= size || (element2 = tb.getFormattingElements().get(size)) == null) {
                        element2 = null;
                        break;
                    }
                    if (Intrinsics.areEqual(element2.normalName(), normalName$ksoup_release)) {
                        break;
                    }
                    size--;
                }
                if (element2 == null) {
                    return anyOtherEndTag(t, tb);
                }
                if (!tb.onStack(element2)) {
                    tb.error(this);
                    tb.removeFromActiveFormattingElements(element2);
                    return true;
                }
                if (!HtmlTreeBuilder.inScope$default(tb, element2.normalName(), null, 2, null)) {
                    tb.error(this);
                    return z;
                }
                if (tb.currentElement() != element2) {
                    tb.error(this);
                }
                ArrayList<Element> stack = tb.getStack();
                int iLastIndexOf = stack.lastIndexOf(element2);
                if (iLastIndexOf != -1) {
                    int size2 = stack.size();
                    for (int i2 = iLastIndexOf + 1; i2 < size2; i2++) {
                        Element element3 = stack.get(i2);
                        HtmlTreeBuilder.Companion companion = HtmlTreeBuilder.INSTANCE;
                        Intrinsics.checkNotNull(element3);
                        if (companion.isSpecial(element3)) {
                            element = element3;
                            break;
                        }
                    }
                }
                if (element == null) {
                    while (tb.currentElement() != element2) {
                        tb.pop();
                    }
                    tb.pop();
                    tb.removeFromActiveFormattingElements(element2);
                    return true;
                }
                Element elementAboveOnStack = tb.aboveOnStack(element2);
                if (elementAboveOnStack == null) {
                    tb.error(this);
                    return true;
                }
                int iPositionOfElement = tb.positionOfElement(element2);
                int i3 = z;
                Element elementAboveOnStack2 = element;
                Element element4 = elementAboveOnStack2;
                while (true) {
                    i3++;
                    if (!tb.onStack(elementAboveOnStack2)) {
                        elementAboveOnStack2 = elementAboveOnStack2.parent();
                    } else {
                        elementAboveOnStack2 = tb.aboveOnStack(elementAboveOnStack2);
                    }
                    if (elementAboveOnStack2 == null || elementAboveOnStack2.nameIs(TtmlNode.TAG_BODY)) {
                        tb.error(this);
                        break;
                    }
                    if (elementAboveOnStack2 == element2) {
                        break;
                    }
                    if (i3 > 3 && tb.isInActiveFormattingElements(elementAboveOnStack2)) {
                        tb.removeFromActiveFormattingElements(elementAboveOnStack2);
                        break;
                    }
                    if (!tb.isInActiveFormattingElements(elementAboveOnStack2)) {
                        tb.removeFromStack(elementAboveOnStack2);
                    } else {
                        if (!tb.onStack(elementAboveOnStack2)) {
                            tb.error(this);
                            tb.removeFromActiveFormattingElements(elementAboveOnStack2);
                            break;
                        }
                        Element element5 = new Element(tb.tagFor(elementAboveOnStack2.nodeName(), elementAboveOnStack2.normalName(), tb.defaultNamespace(), ParseSettings.INSTANCE.getPreserveCase()), tb.getBaseUri());
                        tb.replaceActiveFormattingElement(elementAboveOnStack2, element5);
                        tb.replaceOnStack(elementAboveOnStack2, element5);
                        if (element4 == element) {
                            iPositionOfElement = tb.positionOfElement(element5) + 1;
                        }
                        element5.appendChild(element4);
                        elementAboveOnStack2 = element5;
                        element4 = elementAboveOnStack2;
                    }
                }
                elementAboveOnStack.appendChild(element4);
                Element element6 = new Element(element2.tag(), tb.getBaseUri());
                element6.attributes().addAll(element2.attributes());
                Iterator<Node> it = element.childNodes().iterator();
                while (it.hasNext()) {
                    element6.appendChild(it.next());
                }
                element.appendChild(element6);
                tb.removeFromActiveFormattingElements(element2);
                tb.pushWithBookmark(element6, iPositionOfElement);
                tb.removeFromStack(element2);
                tb.insertOnStackAfter(element, element6);
                z = false;
            }
            return true;
        }
    },
    Text { // from class: com.fleeksoft.ksoup.parser.HtmlTreeBuilderState.Text
        @Override // com.fleeksoft.ksoup.parser.HtmlTreeBuilderState
        public boolean process(Token t, HtmlTreeBuilder tb) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(tb, "tb");
            if (t.isCharacter()) {
                tb.insertCharacterNode(t.asCharacter());
                return true;
            }
            if (t.isEOF()) {
                tb.error(this);
                tb.pop();
                tb.transition(tb.getOriginalState());
                if (tb.getState() == this) {
                    tb.transition(HtmlTreeBuilderState.InBody);
                }
                return tb.process(t);
            }
            if (!t.isEndTag()) {
                return true;
            }
            tb.pop();
            tb.transition(tb.getOriginalState());
            return true;
        }
    },
    InTable { // from class: com.fleeksoft.ksoup.parser.HtmlTreeBuilderState.InTable
        @Override // com.fleeksoft.ksoup.parser.HtmlTreeBuilderState
        public boolean process(Token t, HtmlTreeBuilder tb) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(tb, "tb");
            if (t.isCharacter() && StringUtil.INSTANCE.inSorted(tb.currentElement().normalName(), Constants.INSTANCE.getInTableFoster())) {
                tb.resetPendingTableCharacters();
                tb.markInsertionMode();
                tb.transition(HtmlTreeBuilderState.InTableText);
                return tb.process(t);
            }
            if (t.isComment()) {
                tb.insertCommentNode(t.asComment());
                return true;
            }
            if (t.isDoctype()) {
                tb.error(this);
                return false;
            }
            if (t.isStartTag()) {
                Token.StartTag startTagAsStartTag = t.asStartTag();
                String strRetrieveNormalName = startTagAsStartTag.retrieveNormalName();
                if (Intrinsics.areEqual(strRetrieveNormalName, MediaTrack.ROLE_CAPTION)) {
                    tb.clearStackToTableContext();
                    tb.insertMarkerToFormattingElements();
                    tb.insertElementFor(startTagAsStartTag);
                    tb.transition(HtmlTreeBuilderState.InCaption);
                } else if (Intrinsics.areEqual(strRetrieveNormalName, "colgroup")) {
                    tb.clearStackToTableContext();
                    tb.insertElementFor(startTagAsStartTag);
                    tb.transition(HtmlTreeBuilderState.InColumnGroup);
                } else {
                    if (Intrinsics.areEqual(strRetrieveNormalName, "col")) {
                        tb.clearStackToTableContext();
                        tb.processStartTag("colgroup");
                        return tb.process(t);
                    }
                    if (StringUtil.INSTANCE.inSorted(strRetrieveNormalName, Constants.INSTANCE.getInTableToBody())) {
                        tb.clearStackToTableContext();
                        tb.insertElementFor(startTagAsStartTag);
                        tb.transition(HtmlTreeBuilderState.InTableBody);
                    } else {
                        if (StringUtil.INSTANCE.inSorted(strRetrieveNormalName, Constants.INSTANCE.getInTableAddBody())) {
                            tb.clearStackToTableContext();
                            tb.processStartTag("tbody");
                            return tb.process(t);
                        }
                        if (Intrinsics.areEqual(strRetrieveNormalName, "table")) {
                            tb.error(this);
                            if (!tb.inTableScope(strRetrieveNormalName)) {
                                return false;
                            }
                            tb.popStackToClose(strRetrieveNormalName);
                            if (!tb.resetInsertionMode()) {
                                tb.insertElementFor(startTagAsStartTag);
                                return true;
                            }
                            return tb.process(t);
                        }
                        if (StringUtil.INSTANCE.inSorted(strRetrieveNormalName, Constants.INSTANCE.getInTableToHead())) {
                            return tb.process(t, HtmlTreeBuilderState.InHead);
                        }
                        if (Intrinsics.areEqual(strRetrieveNormalName, TvContractCompat.PARAM_INPUT)) {
                            if (startTagAsStartTag.hasAttributes()) {
                                Attributes attributes = startTagAsStartTag.getAttributes();
                                Intrinsics.checkNotNull(attributes);
                                if (StringsKt.equals(attributes.get("type"), "hidden", true)) {
                                    tb.insertEmptyElementFor(startTagAsStartTag);
                                }
                            }
                            return anythingElse(t, tb);
                        }
                        if (Intrinsics.areEqual(strRetrieveNormalName, "form")) {
                            tb.error(this);
                            if (tb.getFormElement() != null || tb.onStack("template")) {
                                return false;
                            }
                            tb.insertFormElement(startTagAsStartTag, false, false);
                        } else {
                            return anythingElse(t, tb);
                        }
                    }
                }
                return true;
            }
            if (t.isEndTag()) {
                String strRetrieveNormalName2 = t.asEndTag().retrieveNormalName();
                if (Intrinsics.areEqual(strRetrieveNormalName2, "table")) {
                    if (!tb.inTableScope(strRetrieveNormalName2)) {
                        tb.error(this);
                        return false;
                    }
                    tb.popStackToClose("table");
                    tb.resetInsertionMode();
                } else {
                    if (StringUtil.INSTANCE.inSorted(strRetrieveNormalName2, Constants.INSTANCE.getInTableEndErr())) {
                        tb.error(this);
                        return false;
                    }
                    if (Intrinsics.areEqual(strRetrieveNormalName2, "template")) {
                        tb.process(t, HtmlTreeBuilderState.InHead);
                    } else {
                        return anythingElse(t, tb);
                    }
                }
                return true;
            }
            if (t.isEOF()) {
                if (tb.currentElementIs("html")) {
                    tb.error(this);
                }
                return true;
            }
            return anythingElse(t, tb);
        }

        public final boolean anythingElse(Token t, HtmlTreeBuilder tb) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(tb, "tb");
            tb.error(this);
            tb.setFosterInserts(true);
            tb.process(t, HtmlTreeBuilderState.InBody);
            tb.setFosterInserts(false);
            return true;
        }
    },
    InTableText { // from class: com.fleeksoft.ksoup.parser.HtmlTreeBuilderState.InTableText
        @Override // com.fleeksoft.ksoup.parser.HtmlTreeBuilderState
        public boolean process(Token t, HtmlTreeBuilder tb) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(tb, "tb");
            if (t.getType() == Token.TokenType.Character) {
                tb.addPendingTableCharacters(t.asCharacter());
                return true;
            }
            List<Token.Character> pendingTableCharacters = tb.getPendingTableCharacters();
            Intrinsics.checkNotNull(pendingTableCharacters);
            if (!pendingTableCharacters.isEmpty()) {
                Token currentToken = tb.getCurrentToken();
                List<Token.Character> pendingTableCharacters2 = tb.getPendingTableCharacters();
                Intrinsics.checkNotNull(pendingTableCharacters2);
                for (Token.Character character : pendingTableCharacters2) {
                    Token.Character character2 = character;
                    tb.setCurrentToken(character2);
                    if (!HtmlTreeBuilderState.INSTANCE.isWhitespace(character2)) {
                        tb.error(this);
                        if (StringUtil.INSTANCE.inSorted(tb.currentElement().normalName(), Constants.INSTANCE.getInTableFoster())) {
                            tb.setFosterInserts(true);
                            tb.process(character2, HtmlTreeBuilderState.InBody);
                            tb.setFosterInserts(false);
                        } else {
                            tb.process(character2, HtmlTreeBuilderState.InBody);
                        }
                    } else {
                        tb.insertCharacterNode(character);
                    }
                }
                tb.setCurrentToken(currentToken);
                tb.resetPendingTableCharacters();
            }
            tb.transition(tb.getOriginalState());
            return tb.process(t);
        }
    },
    InCaption { // from class: com.fleeksoft.ksoup.parser.HtmlTreeBuilderState.InCaption
        @Override // com.fleeksoft.ksoup.parser.HtmlTreeBuilderState
        public boolean process(Token t, HtmlTreeBuilder tb) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(tb, "tb");
            if (t.isEndTag() && Intrinsics.areEqual(t.asEndTag().retrieveNormalName(), MediaTrack.ROLE_CAPTION)) {
                if (!tb.inTableScope(MediaTrack.ROLE_CAPTION)) {
                    tb.error(this);
                    return false;
                }
                HtmlTreeBuilder.generateImpliedEndTags$default(tb, false, 1, null);
                if (!tb.currentElementIs(MediaTrack.ROLE_CAPTION)) {
                    tb.error(this);
                }
                tb.popStackToClose(MediaTrack.ROLE_CAPTION);
                tb.clearFormattingElementsToLastMarker();
                tb.transition(HtmlTreeBuilderState.InTable);
            } else if ((t.isStartTag() && StringUtil.INSTANCE.inSorted(t.asStartTag().retrieveNormalName(), Constants.INSTANCE.getInCellCol())) || (t.isEndTag() && Intrinsics.areEqual(t.asEndTag().retrieveNormalName(), "table"))) {
                if (!tb.inTableScope(MediaTrack.ROLE_CAPTION)) {
                    tb.error(this);
                    return false;
                }
                tb.generateImpliedEndTags(false);
                if (!tb.currentElementIs(MediaTrack.ROLE_CAPTION)) {
                    tb.error(this);
                }
                tb.popStackToClose(MediaTrack.ROLE_CAPTION);
                tb.clearFormattingElementsToLastMarker();
                tb.transition(HtmlTreeBuilderState.InTable);
                HtmlTreeBuilderState.InTable.process(t, tb);
            } else {
                if (t.isEndTag() && StringUtil.INSTANCE.inSorted(t.asEndTag().retrieveNormalName(), Constants.INSTANCE.getInCaptionIgnore())) {
                    tb.error(this);
                    return false;
                }
                return tb.process(t, HtmlTreeBuilderState.InBody);
            }
            return true;
        }
    },
    InColumnGroup { // from class: com.fleeksoft.ksoup.parser.HtmlTreeBuilderState.InColumnGroup

        /* JADX INFO: compiled from: HtmlTreeBuilderState.kt */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[Token.TokenType.values().length];
                try {
                    iArr[Token.TokenType.Comment.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[Token.TokenType.Doctype.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[Token.TokenType.StartTag.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[Token.TokenType.EndTag.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[Token.TokenType.EOF.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        @Override // com.fleeksoft.ksoup.parser.HtmlTreeBuilderState
        public boolean process(Token t, HtmlTreeBuilder tb) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(tb, "tb");
            if (HtmlTreeBuilderState.INSTANCE.isWhitespace(t)) {
                tb.insertCharacterNode(t.asCharacter());
                return true;
            }
            int i = WhenMappings.$EnumSwitchMapping$0[t.getType().ordinal()];
            if (i == 1) {
                tb.insertCommentNode(t.asComment());
            } else if (i == 2) {
                tb.error(this);
            } else {
                if (i == 3) {
                    Token.StartTag startTagAsStartTag = t.asStartTag();
                    String strRetrieveNormalName = startTagAsStartTag.retrieveNormalName();
                    int iHashCode = strRetrieveNormalName.hashCode();
                    if (iHashCode == -1321546630) {
                        if (strRetrieveNormalName.equals("template")) {
                            tb.process(t, HtmlTreeBuilderState.InHead);
                        }
                        return anythingElse(t, tb);
                    }
                    if (iHashCode != 98688) {
                        if (iHashCode == 3213227 && strRetrieveNormalName.equals("html")) {
                            return tb.process(t, HtmlTreeBuilderState.InBody);
                        }
                    } else if (strRetrieveNormalName.equals("col")) {
                        tb.insertEmptyElementFor(startTagAsStartTag);
                    }
                    return anythingElse(t, tb);
                }
                if (i != 4) {
                    if (i == 5) {
                        if (tb.currentElementIs("html")) {
                            return true;
                        }
                        return anythingElse(t, tb);
                    }
                    return anythingElse(t, tb);
                }
                String strRetrieveNormalName2 = t.asEndTag().retrieveNormalName();
                if (Intrinsics.areEqual(strRetrieveNormalName2, "colgroup")) {
                    if (!tb.currentElementIs(strRetrieveNormalName2)) {
                        tb.error(this);
                        return false;
                    }
                    tb.pop();
                    tb.transition(HtmlTreeBuilderState.InTable);
                } else {
                    if (!Intrinsics.areEqual(strRetrieveNormalName2, "template")) {
                        return anythingElse(t, tb);
                    }
                    tb.process(t, HtmlTreeBuilderState.InHead);
                }
            }
            return true;
        }

        private final boolean anythingElse(Token t, HtmlTreeBuilder tb) {
            if (!tb.currentElementIs("colgroup")) {
                tb.error(this);
                return false;
            }
            tb.pop();
            tb.transition(HtmlTreeBuilderState.InTable);
            tb.process(t);
            return true;
        }
    },
    InTableBody { // from class: com.fleeksoft.ksoup.parser.HtmlTreeBuilderState.InTableBody

        /* JADX INFO: compiled from: HtmlTreeBuilderState.kt */
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

        @Override // com.fleeksoft.ksoup.parser.HtmlTreeBuilderState
        public boolean process(Token t, HtmlTreeBuilder tb) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(tb, "tb");
            int i = WhenMappings.$EnumSwitchMapping$0[t.getType().ordinal()];
            if (i == 1) {
                Token.StartTag startTagAsStartTag = t.asStartTag();
                String strRetrieveNormalName = startTagAsStartTag.retrieveNormalName();
                if (Intrinsics.areEqual(strRetrieveNormalName, "tr")) {
                    tb.clearStackToTableBodyContext();
                    tb.insertElementFor(startTagAsStartTag);
                    tb.transition(HtmlTreeBuilderState.InRow);
                } else {
                    if (StringUtil.INSTANCE.inSorted(strRetrieveNormalName, Constants.INSTANCE.getInCellNames())) {
                        tb.error(this);
                        tb.processStartTag("tr");
                        return tb.process(startTagAsStartTag);
                    }
                    if (StringUtil.INSTANCE.inSorted(strRetrieveNormalName, Constants.INSTANCE.getInTableBodyExit())) {
                        return exitTableBody(t, tb);
                    }
                    return anythingElse(t, tb);
                }
            } else if (i == 2) {
                String strRetrieveNormalName2 = t.asEndTag().retrieveNormalName();
                if (StringUtil.INSTANCE.inSorted(strRetrieveNormalName2, Constants.INSTANCE.getInTableEndIgnore())) {
                    if (!tb.inTableScope(strRetrieveNormalName2)) {
                        tb.error(this);
                        return false;
                    }
                    tb.clearStackToTableBodyContext();
                    tb.pop();
                    tb.transition(HtmlTreeBuilderState.InTable);
                } else {
                    if (Intrinsics.areEqual(strRetrieveNormalName2, "table")) {
                        return exitTableBody(t, tb);
                    }
                    if (StringUtil.INSTANCE.inSorted(strRetrieveNormalName2, Constants.INSTANCE.getInTableBodyEndIgnore())) {
                        tb.error(this);
                        return false;
                    }
                    return anythingElse(t, tb);
                }
            } else {
                return anythingElse(t, tb);
            }
            return true;
        }

        private final boolean exitTableBody(Token t, HtmlTreeBuilder tb) {
            if (!tb.inTableScope("tbody") && !tb.inTableScope("thead") && !HtmlTreeBuilder.inScope$default(tb, "tfoot", null, 2, null)) {
                tb.error(this);
                return false;
            }
            tb.clearStackToTableBodyContext();
            tb.processEndTag(tb.currentElement().normalName());
            return tb.process(t);
        }

        private final boolean anythingElse(Token t, HtmlTreeBuilder tb) {
            return tb.process(t, HtmlTreeBuilderState.InTable);
        }
    },
    InRow { // from class: com.fleeksoft.ksoup.parser.HtmlTreeBuilderState.InRow
        @Override // com.fleeksoft.ksoup.parser.HtmlTreeBuilderState
        public boolean process(Token t, HtmlTreeBuilder tb) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(tb, "tb");
            if (t.isStartTag()) {
                Token.StartTag startTagAsStartTag = t.asStartTag();
                String strRetrieveNormalName = startTagAsStartTag.retrieveNormalName();
                if (StringUtil.INSTANCE.inSorted(strRetrieveNormalName, Constants.INSTANCE.getInCellNames())) {
                    tb.clearStackToTableRowContext();
                    tb.insertElementFor(startTagAsStartTag);
                    tb.transition(HtmlTreeBuilderState.InCell);
                    tb.insertMarkerToFormattingElements();
                    return true;
                }
                if (StringUtil.INSTANCE.inSorted(strRetrieveNormalName, Constants.INSTANCE.getInRowMissing())) {
                    if (!tb.inTableScope("tr")) {
                        tb.error(this);
                        return false;
                    }
                    tb.clearStackToTableRowContext();
                    tb.pop();
                    tb.transition(HtmlTreeBuilderState.InTableBody);
                    return tb.process(t);
                }
                return anythingElse(t, tb);
            }
            if (t.isEndTag()) {
                String strRetrieveNormalName2 = t.asEndTag().retrieveNormalName();
                if (Intrinsics.areEqual(strRetrieveNormalName2, "tr")) {
                    if (!tb.inTableScope(strRetrieveNormalName2)) {
                        tb.error(this);
                        return false;
                    }
                    tb.clearStackToTableRowContext();
                    tb.pop();
                    tb.transition(HtmlTreeBuilderState.InTableBody);
                    return true;
                }
                if (Intrinsics.areEqual(strRetrieveNormalName2, "table")) {
                    if (!tb.inTableScope("tr")) {
                        tb.error(this);
                        return false;
                    }
                    tb.clearStackToTableRowContext();
                    tb.pop();
                    tb.transition(HtmlTreeBuilderState.InTableBody);
                    return tb.process(t);
                }
                if (StringUtil.INSTANCE.inSorted(strRetrieveNormalName2, Constants.INSTANCE.getInTableToBody())) {
                    if (!tb.inTableScope(strRetrieveNormalName2)) {
                        tb.error(this);
                        return false;
                    }
                    if (!tb.inTableScope("tr")) {
                        return false;
                    }
                    tb.clearStackToTableRowContext();
                    tb.pop();
                    tb.transition(HtmlTreeBuilderState.InTableBody);
                    return tb.process(t);
                }
                if (StringUtil.INSTANCE.inSorted(strRetrieveNormalName2, Constants.INSTANCE.getInRowIgnore())) {
                    tb.error(this);
                    return false;
                }
                return anythingElse(t, tb);
            }
            return anythingElse(t, tb);
        }

        private final boolean anythingElse(Token t, HtmlTreeBuilder tb) {
            return tb.process(t, HtmlTreeBuilderState.InTable);
        }
    },
    InCell { // from class: com.fleeksoft.ksoup.parser.HtmlTreeBuilderState.InCell
        @Override // com.fleeksoft.ksoup.parser.HtmlTreeBuilderState
        public boolean process(Token t, HtmlTreeBuilder tb) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(tb, "tb");
            if (t.isEndTag()) {
                String strRetrieveNormalName = t.asEndTag().retrieveNormalName();
                if (StringUtil.INSTANCE.inSorted(strRetrieveNormalName, Constants.INSTANCE.getInCellNames())) {
                    if (!tb.inTableScope(strRetrieveNormalName)) {
                        tb.error(this);
                        tb.transition(HtmlTreeBuilderState.InRow);
                        return false;
                    }
                    HtmlTreeBuilder.generateImpliedEndTags$default(tb, false, 1, null);
                    if (!tb.currentElementIs(strRetrieveNormalName)) {
                        tb.error(this);
                    }
                    tb.popStackToClose(strRetrieveNormalName);
                    tb.clearFormattingElementsToLastMarker();
                    tb.transition(HtmlTreeBuilderState.InRow);
                    return true;
                }
                if (StringUtil.INSTANCE.inSorted(strRetrieveNormalName, Constants.INSTANCE.getInCellBody())) {
                    tb.error(this);
                    return false;
                }
                if (StringUtil.INSTANCE.inSorted(strRetrieveNormalName, Constants.INSTANCE.getInCellTable())) {
                    if (!tb.inTableScope(strRetrieveNormalName)) {
                        tb.error(this);
                        return false;
                    }
                    closeCell(tb);
                    return tb.process(t);
                }
                return anythingElse(t, tb);
            }
            if (t.isStartTag() && StringUtil.INSTANCE.inSorted(t.asStartTag().retrieveNormalName(), Constants.INSTANCE.getInCellCol())) {
                if (!tb.inTableScope("td") && !tb.inTableScope("th")) {
                    tb.error(this);
                    return false;
                }
                closeCell(tb);
                return tb.process(t);
            }
            return anythingElse(t, tb);
        }

        private final boolean anythingElse(Token t, HtmlTreeBuilder tb) {
            return tb.process(t, HtmlTreeBuilderState.InBody);
        }

        private final void closeCell(HtmlTreeBuilder tb) {
            if (tb.inTableScope("td")) {
                tb.processEndTag("td");
            } else {
                tb.processEndTag("th");
            }
        }
    },
    InSelect { // from class: com.fleeksoft.ksoup.parser.HtmlTreeBuilderState.InSelect

        /* JADX INFO: compiled from: HtmlTreeBuilderState.kt */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[Token.TokenType.values().length];
                try {
                    iArr[Token.TokenType.Character.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[Token.TokenType.Comment.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[Token.TokenType.Doctype.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[Token.TokenType.StartTag.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[Token.TokenType.EndTag.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr[Token.TokenType.EOF.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // com.fleeksoft.ksoup.parser.HtmlTreeBuilderState
        public boolean process(Token t, HtmlTreeBuilder tb) {
            Element elementAboveOnStack;
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(tb, "tb");
            switch (WhenMappings.$EnumSwitchMapping$0[t.getType().ordinal()]) {
                case 1:
                    tb.insertCharacterNode(t.asCharacter());
                    return true;
                case 2:
                    tb.insertCommentNode(t.asComment());
                    return true;
                case 3:
                    tb.error(this);
                    return false;
                case 4:
                    Token.StartTag startTagAsStartTag = t.asStartTag();
                    String normalName$ksoup_release = startTagAsStartTag.getNormalName();
                    Intrinsics.checkNotNull(normalName$ksoup_release);
                    if (Intrinsics.areEqual(normalName$ksoup_release, "html")) {
                        return tb.process(startTagAsStartTag, HtmlTreeBuilderState.InBody);
                    }
                    if (Intrinsics.areEqual(normalName$ksoup_release, "option")) {
                        if (tb.currentElementIs("option")) {
                            tb.processEndTag("option");
                        }
                        tb.insertElementFor(startTagAsStartTag);
                    } else {
                        if (!Intrinsics.areEqual(normalName$ksoup_release, "optgroup")) {
                            if (Intrinsics.areEqual(normalName$ksoup_release, "select")) {
                                tb.error(this);
                                return tb.processEndTag("select");
                            }
                            if (!StringUtil.INSTANCE.inSorted(normalName$ksoup_release, Constants.INSTANCE.getInSelectEnd())) {
                                return (Intrinsics.areEqual(normalName$ksoup_release, "script") || Intrinsics.areEqual(normalName$ksoup_release, "template")) ? tb.process(t, HtmlTreeBuilderState.InHead) : anythingElse(t, tb);
                            }
                            tb.error(this);
                            if (!tb.inSelectScope("select")) {
                                return false;
                            }
                            do {
                                tb.popStackToClose("select");
                                tb.resetInsertionMode();
                            } while (tb.inSelectScope("select"));
                            return tb.process(startTagAsStartTag);
                        }
                        if (tb.currentElementIs("option")) {
                            tb.processEndTag("option");
                        }
                        if (tb.currentElementIs("optgroup")) {
                            tb.processEndTag("optgroup");
                        }
                        tb.insertElementFor(startTagAsStartTag);
                    }
                    return true;
                case 5:
                    String normalName$ksoup_release2 = t.asEndTag().getNormalName();
                    Intrinsics.checkNotNull(normalName$ksoup_release2);
                    switch (normalName$ksoup_release2.hashCode()) {
                        case -1321546630:
                            if (normalName$ksoup_release2.equals("template")) {
                                return tb.process(t, HtmlTreeBuilderState.InHead);
                            }
                            return anythingElse(t, tb);
                        case -1010136971:
                            if (normalName$ksoup_release2.equals("option")) {
                                if (tb.currentElementIs("option")) {
                                    tb.pop();
                                } else {
                                    tb.error(this);
                                }
                                return true;
                            }
                            return anythingElse(t, tb);
                        case -906021636:
                            if (normalName$ksoup_release2.equals("select")) {
                                if (!tb.inSelectScope(normalName$ksoup_release2)) {
                                    tb.error(this);
                                    return false;
                                }
                                tb.popStackToClose(normalName$ksoup_release2);
                                tb.resetInsertionMode();
                                return true;
                            }
                            return anythingElse(t, tb);
                        case -80773204:
                            if (normalName$ksoup_release2.equals("optgroup")) {
                                if (tb.currentElementIs("option") && (elementAboveOnStack = tb.aboveOnStack(tb.currentElement())) != null && elementAboveOnStack.nameIs("optgroup")) {
                                    tb.processEndTag("option");
                                }
                                if (tb.currentElementIs("optgroup")) {
                                    tb.pop();
                                } else {
                                    tb.error(this);
                                }
                                return true;
                            }
                            return anythingElse(t, tb);
                        default:
                            return anythingElse(t, tb);
                    }
                case 6:
                    if (!tb.currentElementIs("html")) {
                        tb.error(this);
                    }
                    return true;
                default:
                    return anythingElse(t, tb);
            }
        }

        private final boolean anythingElse(Token t, HtmlTreeBuilder tb) {
            tb.error(this);
            return false;
        }
    },
    InSelectInTable { // from class: com.fleeksoft.ksoup.parser.HtmlTreeBuilderState.InSelectInTable
        @Override // com.fleeksoft.ksoup.parser.HtmlTreeBuilderState
        public boolean process(Token t, HtmlTreeBuilder tb) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(tb, "tb");
            if (t.isStartTag() && StringUtil.INSTANCE.inSorted(t.asStartTag().retrieveNormalName(), Constants.INSTANCE.getInSelectTableEnd())) {
                tb.error(this);
                tb.popStackToClose("select");
                tb.resetInsertionMode();
                return tb.process(t);
            }
            if (t.isEndTag() && StringUtil.INSTANCE.inSorted(t.asEndTag().retrieveNormalName(), Constants.INSTANCE.getInSelectTableEnd())) {
                tb.error(this);
                if (!tb.inTableScope(t.asEndTag().retrieveNormalName())) {
                    return false;
                }
                tb.popStackToClose("select");
                tb.resetInsertionMode();
                return tb.process(t);
            }
            return tb.process(t, HtmlTreeBuilderState.InSelect);
        }
    },
    InTemplate { // from class: com.fleeksoft.ksoup.parser.HtmlTreeBuilderState.InTemplate

        /* JADX INFO: compiled from: HtmlTreeBuilderState.kt */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[Token.TokenType.values().length];
                try {
                    iArr[Token.TokenType.Character.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[Token.TokenType.Comment.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[Token.TokenType.Doctype.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[Token.TokenType.StartTag.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[Token.TokenType.EndTag.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr[Token.TokenType.EOF.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        @Override // com.fleeksoft.ksoup.parser.HtmlTreeBuilderState
        public boolean process(Token t, HtmlTreeBuilder tb) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(tb, "tb");
            switch (WhenMappings.$EnumSwitchMapping$0[t.getType().ordinal()]) {
                case 1:
                case 2:
                case 3:
                    tb.process(t, HtmlTreeBuilderState.InBody);
                    return true;
                case 4:
                    String strRetrieveNormalName = t.asStartTag().retrieveNormalName();
                    if (StringUtil.INSTANCE.inSorted(strRetrieveNormalName, Constants.INSTANCE.getInTemplateToHead())) {
                        tb.process(t, HtmlTreeBuilderState.InHead);
                        return true;
                    }
                    if (StringUtil.INSTANCE.inSorted(strRetrieveNormalName, Constants.INSTANCE.getInTemplateToTable())) {
                        tb.popTemplateMode();
                        tb.pushTemplateMode(HtmlTreeBuilderState.InTable);
                        tb.transition(HtmlTreeBuilderState.InTable);
                        return tb.process(t);
                    }
                    if (Intrinsics.areEqual(strRetrieveNormalName, "col")) {
                        tb.popTemplateMode();
                        tb.pushTemplateMode(HtmlTreeBuilderState.InColumnGroup);
                        tb.transition(HtmlTreeBuilderState.InColumnGroup);
                        return tb.process(t);
                    }
                    if (Intrinsics.areEqual(strRetrieveNormalName, "tr")) {
                        tb.popTemplateMode();
                        tb.pushTemplateMode(HtmlTreeBuilderState.InTableBody);
                        tb.transition(HtmlTreeBuilderState.InTableBody);
                        return tb.process(t);
                    }
                    if (Intrinsics.areEqual(strRetrieveNormalName, "td") || Intrinsics.areEqual(strRetrieveNormalName, "th")) {
                        tb.popTemplateMode();
                        tb.pushTemplateMode(HtmlTreeBuilderState.InRow);
                        tb.transition(HtmlTreeBuilderState.InRow);
                        return tb.process(t);
                    }
                    tb.popTemplateMode();
                    tb.pushTemplateMode(HtmlTreeBuilderState.InBody);
                    tb.transition(HtmlTreeBuilderState.InBody);
                    return tb.process(t);
                case 5:
                    if (Intrinsics.areEqual(t.asEndTag().retrieveNormalName(), "template")) {
                        tb.process(t, HtmlTreeBuilderState.InHead);
                        return true;
                    }
                    tb.error(this);
                    return false;
                case 6:
                    if (!tb.onStack("template")) {
                        return true;
                    }
                    tb.error(this);
                    tb.popStackToClose("template");
                    tb.clearFormattingElementsToLastMarker();
                    tb.popTemplateMode();
                    tb.resetInsertionMode();
                    if (tb.getState() == this || tb.templateModeSize() >= 12) {
                        return true;
                    }
                    return tb.process(t);
                default:
                    Validate.INSTANCE.wtf("Unexpected state: " + t.getType());
                    return true;
            }
        }
    },
    AfterBody { // from class: com.fleeksoft.ksoup.parser.HtmlTreeBuilderState.AfterBody
        @Override // com.fleeksoft.ksoup.parser.HtmlTreeBuilderState
        public boolean process(Token t, HtmlTreeBuilder tb) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(tb, "tb");
            Element fromStack = tb.getFromStack("html");
            if (HtmlTreeBuilderState.INSTANCE.isWhitespace(t)) {
                if (fromStack != null) {
                    tb.insertCharacterToElement(t.asCharacter(), fromStack);
                    return true;
                }
                tb.process(t, HtmlTreeBuilderState.InBody);
                return true;
            }
            if (t.isComment()) {
                tb.insertCommentNode(t.asComment());
                return true;
            }
            if (t.isDoctype()) {
                tb.error(this);
                return false;
            }
            if (t.isStartTag() && Intrinsics.areEqual(t.asStartTag().retrieveNormalName(), "html")) {
                return tb.process(t, HtmlTreeBuilderState.InBody);
            }
            if (t.isEndTag() && Intrinsics.areEqual(t.asEndTag().retrieveNormalName(), "html")) {
                if (tb.getIsFragmentParsing()) {
                    tb.error(this);
                    return false;
                }
                if (fromStack != null) {
                    tb.trackNodePosition(fromStack, false);
                }
                tb.transition(HtmlTreeBuilderState.AfterAfterBody);
                return true;
            }
            if (t.isEOF()) {
                return true;
            }
            tb.error(this);
            tb.resetBody();
            return tb.process(t);
        }
    },
    InFrameset { // from class: com.fleeksoft.ksoup.parser.HtmlTreeBuilderState.InFrameset
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // com.fleeksoft.ksoup.parser.HtmlTreeBuilderState
        public boolean process(Token t, HtmlTreeBuilder tb) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(tb, "tb");
            if (HtmlTreeBuilderState.INSTANCE.isWhitespace(t)) {
                tb.insertCharacterNode(t.asCharacter());
            } else if (t.isComment()) {
                tb.insertCommentNode(t.asComment());
            } else {
                if (t.isDoctype()) {
                    tb.error(this);
                    return false;
                }
                if (t.isStartTag()) {
                    Token.StartTag startTagAsStartTag = t.asStartTag();
                    String strRetrieveNormalName = startTagAsStartTag.retrieveNormalName();
                    switch (strRetrieveNormalName.hashCode()) {
                        case -1644953643:
                            if (strRetrieveNormalName.equals("frameset")) {
                                tb.insertElementFor(startTagAsStartTag);
                                break;
                            }
                            tb.error(this);
                            return false;
                        case 3213227:
                            if (strRetrieveNormalName.equals("html")) {
                                return tb.process(startTagAsStartTag, HtmlTreeBuilderState.InBody);
                            }
                            tb.error(this);
                            return false;
                        case 97692013:
                            if (strRetrieveNormalName.equals(TypedValues.AttributesType.S_FRAME)) {
                                tb.insertEmptyElementFor(startTagAsStartTag);
                                break;
                            }
                            tb.error(this);
                            return false;
                        case 1192721831:
                            if (strRetrieveNormalName.equals("noframes")) {
                                return tb.process(startTagAsStartTag, HtmlTreeBuilderState.InHead);
                            }
                            tb.error(this);
                            return false;
                        default:
                            tb.error(this);
                            return false;
                    }
                }
                if (t.isEndTag() && Intrinsics.areEqual(t.asEndTag().retrieveNormalName(), "frameset")) {
                    if (!tb.currentElementIs("frameset")) {
                        tb.error(this);
                        return false;
                    }
                    tb.pop();
                    if (!tb.getIsFragmentParsing() && !tb.currentElementIs("frameset")) {
                        tb.transition(HtmlTreeBuilderState.AfterFrameset);
                    }
                } else {
                    if (!t.isEOF()) {
                        tb.error(this);
                        return false;
                    }
                    if (!tb.currentElementIs("html")) {
                        tb.error(this);
                    }
                }
            }
            return true;
        }
    },
    AfterFrameset { // from class: com.fleeksoft.ksoup.parser.HtmlTreeBuilderState.AfterFrameset
        @Override // com.fleeksoft.ksoup.parser.HtmlTreeBuilderState
        public boolean process(Token t, HtmlTreeBuilder tb) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(tb, "tb");
            if (HtmlTreeBuilderState.INSTANCE.isWhitespace(t)) {
                tb.insertCharacterNode(t.asCharacter());
                return true;
            }
            if (t.isComment()) {
                tb.insertCommentNode(t.asComment());
                return true;
            }
            if (t.isDoctype()) {
                tb.error(this);
                return false;
            }
            if (t.isStartTag() && Intrinsics.areEqual(t.asStartTag().retrieveNormalName(), "html")) {
                return tb.process(t, HtmlTreeBuilderState.InBody);
            }
            if (t.isEndTag() && Intrinsics.areEqual(t.asEndTag().retrieveNormalName(), "html")) {
                tb.transition(HtmlTreeBuilderState.AfterAfterFrameset);
                return true;
            }
            if (t.isStartTag() && Intrinsics.areEqual(t.asStartTag().retrieveNormalName(), "noframes")) {
                return tb.process(t, HtmlTreeBuilderState.InHead);
            }
            if (t.isEOF()) {
                return true;
            }
            tb.error(this);
            return false;
        }
    },
    AfterAfterBody { // from class: com.fleeksoft.ksoup.parser.HtmlTreeBuilderState.AfterAfterBody
        @Override // com.fleeksoft.ksoup.parser.HtmlTreeBuilderState
        public boolean process(Token t, HtmlTreeBuilder tb) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(tb, "tb");
            if (t.isComment()) {
                tb.insertCommentNode(t.asComment());
                return true;
            }
            if (t.isDoctype() || (t.isStartTag() && Intrinsics.areEqual(t.asStartTag().retrieveNormalName(), "html"))) {
                return tb.process(t, HtmlTreeBuilderState.InBody);
            }
            if (HtmlTreeBuilderState.INSTANCE.isWhitespace(t)) {
                tb.insertCharacterToElement(t.asCharacter(), tb.getDocument());
                return true;
            }
            if (t.isEOF()) {
                return true;
            }
            tb.error(this);
            tb.resetBody();
            return tb.process(t);
        }
    },
    AfterAfterFrameset { // from class: com.fleeksoft.ksoup.parser.HtmlTreeBuilderState.AfterAfterFrameset
        @Override // com.fleeksoft.ksoup.parser.HtmlTreeBuilderState
        public boolean process(Token t, HtmlTreeBuilder tb) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(tb, "tb");
            if (t.isComment()) {
                tb.insertCommentNode(t.asComment());
                return true;
            }
            if (t.isDoctype() || HtmlTreeBuilderState.INSTANCE.isWhitespace(t) || (t.isStartTag() && Intrinsics.areEqual(t.asStartTag().retrieveNormalName(), "html"))) {
                return tb.process(t, HtmlTreeBuilderState.InBody);
            }
            if (t.isEOF()) {
                return true;
            }
            if (t.isStartTag() && Intrinsics.areEqual(t.asStartTag().retrieveNormalName(), "noframes")) {
                return tb.process(t, HtmlTreeBuilderState.InHead);
            }
            tb.error(this);
            return false;
        }
    },
    ForeignContent { // from class: com.fleeksoft.ksoup.parser.HtmlTreeBuilderState.ForeignContent

        /* JADX INFO: compiled from: HtmlTreeBuilderState.kt */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[Token.TokenType.values().length];
                try {
                    iArr[Token.TokenType.Character.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[Token.TokenType.Comment.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[Token.TokenType.Doctype.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[Token.TokenType.StartTag.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[Token.TokenType.EndTag.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr[Token.TokenType.EOF.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.fleeksoft.ksoup.parser.HtmlTreeBuilderState
        public boolean process(Token t, HtmlTreeBuilder tb) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(tb, "tb");
            switch (WhenMappings.$EnumSwitchMapping$0[t.getType().ordinal()]) {
                case 1:
                    Token.Character characterAsCharacter = t.asCharacter();
                    if (HtmlTreeBuilderState.INSTANCE.isWhitespace(characterAsCharacter)) {
                        tb.insertCharacterNode(characterAsCharacter);
                    } else {
                        tb.insertCharacterNode(characterAsCharacter, true);
                        tb.framesetOk(false);
                    }
                    return true;
                case 2:
                    tb.insertCommentNode(t.asComment());
                    return true;
                case 3:
                    tb.error(this);
                    return true;
                case 4:
                    Token.StartTag startTagAsStartTag = t.asStartTag();
                    StringUtil stringUtil = StringUtil.INSTANCE;
                    String normalName$ksoup_release = startTagAsStartTag.getNormalName();
                    Intrinsics.checkNotNull(normalName$ksoup_release);
                    String[] inForeignToHtml = Constants.INSTANCE.getInForeignToHtml();
                    if (stringUtil.isIn(normalName$ksoup_release, (String[]) Arrays.copyOf(inForeignToHtml, inForeignToHtml.length))) {
                        return processAsHtml(t, tb);
                    }
                    if (StringsKt.equals$default(startTagAsStartTag.getNormalName(), ContentType.Font.TYPE, false, 2, null) && (startTagAsStartTag.hasAttributeIgnoreCase("color") || startTagAsStartTag.hasAttributeIgnoreCase("face") || startTagAsStartTag.hasAttributeIgnoreCase(ContentDisposition.Parameters.Size))) {
                        return processAsHtml(t, tb);
                    }
                    String strNamespace = tb.currentElement().tag().namespace();
                    tb.insertForeignElementFor(startTagAsStartTag, strNamespace);
                    String strValue = startTagAsStartTag.getTagName().value();
                    String normalName$ksoup_release2 = startTagAsStartTag.getNormalName();
                    Intrinsics.checkNotNull(normalName$ksoup_release2);
                    TokeniserState tokeniserStateTextState = tb.tagFor(strValue, normalName$ksoup_release2, strNamespace, tb.getSettings()).textState();
                    if (tokeniserStateTextState != null) {
                        if (Intrinsics.areEqual(startTagAsStartTag.getNormalName(), "script")) {
                            Tokeniser tokeniser = tb.getTokeniser();
                            Intrinsics.checkNotNull(tokeniser);
                            tokeniser.transition(TokeniserState.ScriptData);
                        } else {
                            Tokeniser tokeniser2 = tb.getTokeniser();
                            Intrinsics.checkNotNull(tokeniser2);
                            tokeniser2.transition(tokeniserStateTextState);
                        }
                    }
                    return true;
                case 5:
                    Token.EndTag endTagAsEndTag = t.asEndTag();
                    if (StringsKt.equals$default(endTagAsEndTag.getNormalName(), "br", false, 2, null) || StringsKt.equals$default(endTagAsEndTag.getNormalName(), TtmlNode.TAG_P, false, 2, null)) {
                        return processAsHtml(t, tb);
                    }
                    if (StringsKt.equals$default(endTagAsEndTag.getNormalName(), "script", false, 2, null) && tb.currentElementIs("script", "http://www.w3.org/2000/svg")) {
                        tb.pop();
                        return true;
                    }
                    ArrayList<Element> stack = tb.getStack();
                    ArrayList arrayList = new ArrayList();
                    for (Element element : stack) {
                        if (element != null) {
                            arrayList.add(element);
                        }
                    }
                    Element[] elementArr = (Element[]) arrayList.toArray(new Element[0]);
                    ArrayList arrayListArrayListOf = CollectionsKt.arrayListOf(Arrays.copyOf(elementArr, elementArr.length));
                    if (arrayListArrayListOf.isEmpty()) {
                        Validate.INSTANCE.wtf("Stack unexpectedly empty");
                    }
                    int size = arrayListArrayListOf.size() - 1;
                    Object obj = arrayListArrayListOf.get(size);
                    Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
                    Element element2 = (Element) obj;
                    if (!element2.nameIs(endTagAsEndTag.getNormalName())) {
                        tb.error(this);
                    }
                    while (size != 0) {
                        if (element2.nameIs(endTagAsEndTag.getNormalName())) {
                            tb.popStackToCloseAnyNamespace(element2.normalName());
                            return true;
                        }
                        size--;
                        Object obj2 = arrayListArrayListOf.get(size);
                        Intrinsics.checkNotNullExpressionValue(obj2, "get(...)");
                        element2 = (Element) obj2;
                        if (Intrinsics.areEqual(element2.tag().namespace(), "http://www.w3.org/1999/xhtml")) {
                            return processAsHtml(t, tb);
                        }
                    }
                    return true;
                case 6:
                    return true;
                default:
                    Validate.INSTANCE.wtf("Unexpected state: " + t.getType());
                    return true;
            }
        }

        public final boolean processAsHtml(Token t, HtmlTreeBuilder tb) {
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(tb, "tb");
            HtmlTreeBuilderState state = tb.getState();
            Intrinsics.checkNotNull(state);
            return state.process(t, tb);
        }
    };

    private static final String nullString = "\u0000";
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* synthetic */ HtmlTreeBuilderState(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public static EnumEntries<HtmlTreeBuilderState> getEntries() {
        return $ENTRIES;
    }

    public abstract boolean process(Token t, HtmlTreeBuilder tb);

    /* JADX INFO: compiled from: HtmlTreeBuilderState.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\bP\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0019\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0019\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u000b\u0010\bR\u0019\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\r\u0010\bR\u0019\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u000f\u0010\bR\u0019\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0011\u0010\bR\u0019\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0013\u0010\bR\u0019\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0015\u0010\bR\u0019\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0017\u0010\bR\u0019\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0019\u0010\bR\u0019\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u001b\u0010\bR\u0019\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u001d\u0010\bR\u0019\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u001f\u0010\bR\u0019\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b!\u0010\bR\u0019\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b#\u0010\bR\u0019\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b%\u0010\bR\u0019\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b'\u0010\bR\u0019\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b)\u0010\bR\u0019\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b+\u0010\bR\u0019\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b-\u0010\bR\u0019\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b/\u0010\bR\u0019\u00100\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b1\u0010\bR\u0019\u00102\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b3\u0010\bR\u0019\u00104\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b5\u0010\bR\u0019\u00106\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b7\u0010\bR\u0019\u00108\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b9\u0010\bR\u0019\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b;\u0010\bR\u0019\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b=\u0010\bR\u0019\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b?\u0010\bR\u0019\u0010@\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\bA\u0010\bR\u0019\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\bC\u0010\bR\u0019\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\bE\u0010\bR\u0019\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\bG\u0010\bR\u0019\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\bI\u0010\bR\u0019\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\bK\u0010\bR\u0019\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\bM\u0010\bR\u0019\u0010N\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\bO\u0010\bR\u0019\u0010P\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\bQ\u0010\bR\u0019\u0010R\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\bS\u0010\bR\u0019\u0010T\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\bU\u0010\b¨\u0006V"}, d2 = {"Lcom/fleeksoft/ksoup/parser/HtmlTreeBuilderState$Constants;", "", "<init>", "()V", "InHeadEmpty", "", "", "getInHeadEmpty", "()[Ljava/lang/String;", "[Ljava/lang/String;", "InHeadRaw", "getInHeadRaw", "InHeadEnd", "getInHeadEnd", "AfterHeadBody", "getAfterHeadBody", "BeforeHtmlToHead", "getBeforeHtmlToHead", "InHeadNoScriptHead", "getInHeadNoScriptHead", "InBodyStartToHead", "getInBodyStartToHead", "InBodyStartPClosers", "getInBodyStartPClosers", "Headings", "getHeadings", "InBodyStartLiBreakers", "getInBodyStartLiBreakers", "DdDt", "getDdDt", "InBodyStartApplets", "getInBodyStartApplets", "InBodyStartMedia", "getInBodyStartMedia", "InBodyStartInputAttribs", "getInBodyStartInputAttribs", "InBodyStartDrop", "getInBodyStartDrop", "InBodyEndClosers", "getInBodyEndClosers", "InBodyEndOtherErrors", "getInBodyEndOtherErrors", "InBodyEndAdoptionFormatters", "getInBodyEndAdoptionFormatters", "InTableToBody", "getInTableToBody", "InTableAddBody", "getInTableAddBody", "InTableToHead", "getInTableToHead", "InCellNames", "getInCellNames", "InCellBody", "getInCellBody", "InCellTable", "getInCellTable", "InCellCol", "getInCellCol", "InTableEndErr", "getInTableEndErr", "InTableFoster", "getInTableFoster", "InTableBodyExit", "getInTableBodyExit", "InTableBodyEndIgnore", "getInTableBodyEndIgnore", "InRowMissing", "getInRowMissing", "InRowIgnore", "getInRowIgnore", "InSelectEnd", "getInSelectEnd", "InSelectTableEnd", "getInSelectTableEnd", "InTableEndIgnore", "getInTableEndIgnore", "InHeadNoscriptIgnore", "getInHeadNoscriptIgnore", "InCaptionIgnore", "getInCaptionIgnore", "InTemplateToHead", "getInTemplateToHead", "InTemplateToTable", "getInTemplateToTable", "InForeignToHtml", "getInForeignToHtml", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Constants {
        public static final Constants INSTANCE = new Constants();
        private static final String[] InHeadEmpty = {TtmlNode.RUBY_BASE, "basefont", "bgsound", "command", "link"};
        private static final String[] InHeadRaw = {"noframes", "style"};
        private static final String[] InHeadEnd = {TtmlNode.TAG_BODY, "br", "html"};
        private static final String[] AfterHeadBody = {TtmlNode.TAG_BODY, "br", "html"};
        private static final String[] BeforeHtmlToHead = {TtmlNode.TAG_BODY, "br", TtmlNode.TAG_HEAD, "html"};
        private static final String[] InHeadNoScriptHead = {"basefont", "bgsound", "link", "meta", "noframes", "style"};
        private static final String[] InBodyStartToHead = {TtmlNode.RUBY_BASE, "basefont", "bgsound", "command", "link", "meta", "noframes", "script", "style", "template", "title"};
        private static final String[] InBodyStartPClosers = {"address", "article", "aside", "blockquote", TtmlNode.CENTER, "details", "dir", TtmlNode.TAG_DIV, CmcdConfiguration.KEY_DEADLINE, "fieldset", "figcaption", "figure", "footer", "header", "hgroup", "menu", "nav", "ol", TtmlNode.TAG_P, "section", "summary", "ul"};
        private static final String[] Headings = {"h1", "h2", "h3", "h4", "h5", "h6"};
        private static final String[] InBodyStartLiBreakers = {"address", TtmlNode.TAG_DIV, TtmlNode.TAG_P};
        private static final String[] DdDt = {"dd", "dt"};
        private static final String[] InBodyStartApplets = {"applet", "marquee", "object"};
        private static final String[] InBodyStartMedia = {"param", "source", "track"};
        private static final String[] InBodyStartInputAttribs = {"action", "name", "prompt"};
        private static final String[] InBodyStartDrop = {MediaTrack.ROLE_CAPTION, "col", "colgroup", TypedValues.AttributesType.S_FRAME, TtmlNode.TAG_HEAD, "tbody", "td", "tfoot", "th", "thead", "tr"};
        private static final String[] InBodyEndClosers = {"address", "article", "aside", "blockquote", "button", TtmlNode.CENTER, "details", "dir", TtmlNode.TAG_DIV, CmcdConfiguration.KEY_DEADLINE, "fieldset", "figcaption", "figure", "footer", "header", "hgroup", "listing", "menu", "nav", "ol", "pre", "section", "summary", "ul"};
        private static final String[] InBodyEndOtherErrors = {TtmlNode.TAG_BODY, "dd", "dt", "html", "li", "optgroup", "option", TtmlNode.TAG_P, "rb", "rp", "rt", "rtc", "tbody", "td", "tfoot", "th", "thead", "tr"};
        private static final String[] InBodyEndAdoptionFormatters = {CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "big", "code", "em", ContentType.Font.TYPE, CmcdData.OBJECT_TYPE_INIT_SEGMENT, "nobr", CmcdData.STREAMING_FORMAT_SS, "small", "strike", "strong", TtmlNode.TAG_TT, "u"};
        private static final String[] InTableToBody = {"tbody", "tfoot", "thead"};
        private static final String[] InTableAddBody = {"td", "th", "tr"};
        private static final String[] InTableToHead = {"script", "style", "template"};
        private static final String[] InCellNames = {"td", "th"};
        private static final String[] InCellBody = {TtmlNode.TAG_BODY, MediaTrack.ROLE_CAPTION, "col", "colgroup", "html"};
        private static final String[] InCellTable = {"table", "tbody", "tfoot", "thead", "tr"};
        private static final String[] InCellCol = {MediaTrack.ROLE_CAPTION, "col", "colgroup", "tbody", "td", "tfoot", "th", "thead", "tr"};
        private static final String[] InTableEndErr = {TtmlNode.TAG_BODY, MediaTrack.ROLE_CAPTION, "col", "colgroup", "html", "tbody", "td", "tfoot", "th", "thead", "tr"};
        private static final String[] InTableFoster = {"table", "tbody", "tfoot", "thead", "tr"};
        private static final String[] InTableBodyExit = {MediaTrack.ROLE_CAPTION, "col", "colgroup", "tbody", "tfoot", "thead"};
        private static final String[] InTableBodyEndIgnore = {TtmlNode.TAG_BODY, MediaTrack.ROLE_CAPTION, "col", "colgroup", "html", "td", "th", "tr"};
        private static final String[] InRowMissing = {MediaTrack.ROLE_CAPTION, "col", "colgroup", "tbody", "tfoot", "thead", "tr"};
        private static final String[] InRowIgnore = {TtmlNode.TAG_BODY, MediaTrack.ROLE_CAPTION, "col", "colgroup", "html", "td", "th"};
        private static final String[] InSelectEnd = {TvContractCompat.PARAM_INPUT, "keygen", "textarea"};
        private static final String[] InSelectTableEnd = {MediaTrack.ROLE_CAPTION, "table", "tbody", "td", "tfoot", "th", "thead", "tr"};
        private static final String[] InTableEndIgnore = {"tbody", "tfoot", "thead"};
        private static final String[] InHeadNoscriptIgnore = {TtmlNode.TAG_HEAD, "noscript"};
        private static final String[] InCaptionIgnore = {TtmlNode.TAG_BODY, "col", "colgroup", "html", "tbody", "td", "tfoot", "th", "thead", "tr"};
        private static final String[] InTemplateToHead = {TtmlNode.RUBY_BASE, "basefont", "bgsound", "link", "meta", "noframes", "script", "style", "template", "title"};
        private static final String[] InTemplateToTable = {MediaTrack.ROLE_CAPTION, "colgroup", "tbody", "tfoot", "thead"};
        private static final String[] InForeignToHtml = {"b", "big", "blockquote", TtmlNode.TAG_BODY, "br", TtmlNode.CENTER, "code", "dd", TtmlNode.TAG_DIV, CmcdConfiguration.KEY_DEADLINE, "dt", "em", "embed", "h1", "h2", "h3", "h4", "h5", "h6", TtmlNode.TAG_HEAD, "hr", CmcdData.OBJECT_TYPE_INIT_SEGMENT, "img", "li", "listing", "menu", "meta", "nobr", "ol", TtmlNode.TAG_P, "pre", TtmlNode.ATTR_TTS_RUBY, CmcdData.STREAMING_FORMAT_SS, "small", TtmlNode.TAG_SPAN, "strike", "strong", "sub", "sup", "table", TtmlNode.TAG_TT, "u", "ul", "var"};

        private Constants() {
        }

        public final String[] getInHeadEmpty() {
            return InHeadEmpty;
        }

        public final String[] getInHeadRaw() {
            return InHeadRaw;
        }

        public final String[] getInHeadEnd() {
            return InHeadEnd;
        }

        public final String[] getAfterHeadBody() {
            return AfterHeadBody;
        }

        public final String[] getBeforeHtmlToHead() {
            return BeforeHtmlToHead;
        }

        public final String[] getInHeadNoScriptHead() {
            return InHeadNoScriptHead;
        }

        public final String[] getInBodyStartToHead() {
            return InBodyStartToHead;
        }

        public final String[] getInBodyStartPClosers() {
            return InBodyStartPClosers;
        }

        public final String[] getHeadings() {
            return Headings;
        }

        public final String[] getInBodyStartLiBreakers() {
            return InBodyStartLiBreakers;
        }

        public final String[] getDdDt() {
            return DdDt;
        }

        public final String[] getInBodyStartApplets() {
            return InBodyStartApplets;
        }

        public final String[] getInBodyStartMedia() {
            return InBodyStartMedia;
        }

        public final String[] getInBodyStartInputAttribs() {
            return InBodyStartInputAttribs;
        }

        public final String[] getInBodyStartDrop() {
            return InBodyStartDrop;
        }

        public final String[] getInBodyEndClosers() {
            return InBodyEndClosers;
        }

        public final String[] getInBodyEndOtherErrors() {
            return InBodyEndOtherErrors;
        }

        public final String[] getInBodyEndAdoptionFormatters() {
            return InBodyEndAdoptionFormatters;
        }

        public final String[] getInTableToBody() {
            return InTableToBody;
        }

        public final String[] getInTableAddBody() {
            return InTableAddBody;
        }

        public final String[] getInTableToHead() {
            return InTableToHead;
        }

        public final String[] getInCellNames() {
            return InCellNames;
        }

        public final String[] getInCellBody() {
            return InCellBody;
        }

        public final String[] getInCellTable() {
            return InCellTable;
        }

        public final String[] getInCellCol() {
            return InCellCol;
        }

        public final String[] getInTableEndErr() {
            return InTableEndErr;
        }

        public final String[] getInTableFoster() {
            return InTableFoster;
        }

        public final String[] getInTableBodyExit() {
            return InTableBodyExit;
        }

        public final String[] getInTableBodyEndIgnore() {
            return InTableBodyEndIgnore;
        }

        public final String[] getInRowMissing() {
            return InRowMissing;
        }

        public final String[] getInRowIgnore() {
            return InRowIgnore;
        }

        public final String[] getInSelectEnd() {
            return InSelectEnd;
        }

        public final String[] getInSelectTableEnd() {
            return InSelectTableEnd;
        }

        public final String[] getInTableEndIgnore() {
            return InTableEndIgnore;
        }

        public final String[] getInHeadNoscriptIgnore() {
            return InHeadNoscriptIgnore;
        }

        public final String[] getInCaptionIgnore() {
            return InCaptionIgnore;
        }

        public final String[] getInTemplateToHead() {
            return InTemplateToHead;
        }

        public final String[] getInTemplateToTable() {
            return InTemplateToTable;
        }

        public final String[] getInForeignToHtml() {
            return InForeignToHtml;
        }
    }

    /* JADX INFO: compiled from: HtmlTreeBuilderState.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\"\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0002J\u0018\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\r2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/fleeksoft/ksoup/parser/HtmlTreeBuilderState$Companion;", "", "<init>", "()V", "nullString", "", "isWhitespace", "", "t", "Lcom/fleeksoft/ksoup/parser/Token;", "handleTextState", "", "startTag", "Lcom/fleeksoft/ksoup/parser/Token$StartTag;", "tb", "Lcom/fleeksoft/ksoup/parser/HtmlTreeBuilder;", "state", "Lcom/fleeksoft/ksoup/parser/TokeniserState;", "mergeAttributes", "source", "dest", "Lcom/fleeksoft/ksoup/nodes/Element;", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isWhitespace(Token t) {
            if (!t.isCharacter()) {
                return false;
            }
            return StringUtil.INSTANCE.isBlank(t.asCharacter().m8177getData());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void handleTextState(Token.StartTag startTag, HtmlTreeBuilder tb, TokeniserState state) {
            Tokeniser tokeniser;
            if (state != null && (tokeniser = tb.getTokeniser()) != null) {
                tokeniser.transition(state);
            }
            tb.markInsertionMode();
            tb.transition(HtmlTreeBuilderState.Text);
            tb.insertElementFor(startTag);
        }

        public final void mergeAttributes(Token.StartTag source, Element dest) {
            Intrinsics.checkNotNullParameter(source, "source");
            if (source.hasAttributes()) {
                Attributes attributes = source.getAttributes();
                Intrinsics.checkNotNull(attributes);
                for (Attribute attribute : attributes) {
                    Intrinsics.checkNotNull(dest);
                    Attributes attributes2 = dest.attributes();
                    if (!attributes2.hasKey(attribute.getKey())) {
                        Range.AttributeRange attributeRangeSourceRange = attribute.sourceRange();
                        attributes2.put(attribute);
                        if (source.getTrackSource()) {
                            attributes2.sourceRange(attribute.getKey(), attributeRangeSourceRange);
                        }
                    }
                }
            }
        }
    }
}
