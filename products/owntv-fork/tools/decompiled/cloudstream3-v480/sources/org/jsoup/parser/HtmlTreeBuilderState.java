package org.jsoup.parser;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.google.android.gms.cast.MediaTrack;
import com.google.common.base.Ascii;
import dev.whyoleg.cryptography.serialization.asn1.internal.DerTagKt;
import io.ktor.http.ContentDisposition;
import io.ktor.http.ContentType;
import java.util.ArrayList;
import java.util.Iterator;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.Range;

/* JADX INFO: loaded from: classes8.dex */
enum HtmlTreeBuilderState {
    Initial { // from class: org.jsoup.parser.HtmlTreeBuilderState.1
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                return true;
            }
            if (token.isComment()) {
                htmlTreeBuilder.insertCommentNode(token.asComment());
            } else if (token.isDoctype()) {
                Token.Doctype doctypeAsDoctype = token.asDoctype();
                DocumentType documentType = new DocumentType(htmlTreeBuilder.settings.normalizeTag(doctypeAsDoctype.getName()), doctypeAsDoctype.getPublicIdentifier(), doctypeAsDoctype.getSystemIdentifier());
                documentType.setPubSysKey(doctypeAsDoctype.getPubSysKey());
                htmlTreeBuilder.getDocument().appendChild(documentType);
                htmlTreeBuilder.onNodeInserted(documentType);
                if (doctypeAsDoctype.isForceQuirks() || !documentType.name().equals("html") || documentType.publicId().equalsIgnoreCase("HTML")) {
                    htmlTreeBuilder.getDocument().quirksMode(Document.QuirksMode.quirks);
                }
                htmlTreeBuilder.transition(BeforeHtml);
            } else {
                htmlTreeBuilder.getDocument().quirksMode(Document.QuirksMode.quirks);
                htmlTreeBuilder.transition(BeforeHtml);
                return htmlTreeBuilder.process(token);
            }
            return true;
        }
    },
    BeforeHtml { // from class: org.jsoup.parser.HtmlTreeBuilderState.2
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            }
            if (!token.isComment()) {
                if (HtmlTreeBuilderState.isWhitespace(token)) {
                    htmlTreeBuilder.insertCharacterNode(token.asCharacter());
                    return true;
                }
                if (token.isStartTag() && token.asStartTag().normalName().equals("html")) {
                    htmlTreeBuilder.insertElementFor(token.asStartTag());
                    htmlTreeBuilder.transition(BeforeHead);
                    return true;
                }
                if (token.isEndTag() && StringUtil.inSorted(token.asEndTag().normalName(), Constants.BeforeHtmlToHead)) {
                    return anythingElse(token, htmlTreeBuilder);
                }
                if (token.isEndTag()) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                return anythingElse(token, htmlTreeBuilder);
            }
            htmlTreeBuilder.insertCommentNode(token.asComment());
            return true;
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.processStartTag("html");
            htmlTreeBuilder.transition(BeforeHead);
            return htmlTreeBuilder.process(token);
        }
    },
    BeforeHead { // from class: org.jsoup.parser.HtmlTreeBuilderState.3
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insertCharacterNode(token.asCharacter());
                return true;
            }
            if (token.isComment()) {
                htmlTreeBuilder.insertCommentNode(token.asComment());
                return true;
            }
            if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            }
            if (token.isStartTag() && token.asStartTag().normalName().equals("html")) {
                return InBody.process(token, htmlTreeBuilder);
            }
            if (token.isStartTag() && token.asStartTag().normalName().equals(TtmlNode.TAG_HEAD)) {
                htmlTreeBuilder.setHeadElement(htmlTreeBuilder.insertElementFor(token.asStartTag()));
                htmlTreeBuilder.transition(InHead);
                return true;
            }
            if (token.isEndTag() && StringUtil.inSorted(token.asEndTag().normalName(), Constants.BeforeHtmlToHead)) {
                htmlTreeBuilder.processStartTag(TtmlNode.TAG_HEAD);
                return htmlTreeBuilder.process(token);
            }
            if (token.isEndTag()) {
                htmlTreeBuilder.error(this);
                return false;
            }
            htmlTreeBuilder.processStartTag(TtmlNode.TAG_HEAD);
            return htmlTreeBuilder.process(token);
        }
    },
    InHead { // from class: org.jsoup.parser.HtmlTreeBuilderState.4
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insertCharacterNode(token.asCharacter());
                return true;
            }
            int i = AnonymousClass25.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()];
            if (i == 1) {
                htmlTreeBuilder.insertCommentNode(token.asComment());
            } else {
                if (i == 2) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                if (i == 3) {
                    Token.StartTag startTagAsStartTag = token.asStartTag();
                    String strNormalName = startTagAsStartTag.normalName();
                    if (strNormalName.equals("html")) {
                        return InBody.process(token, htmlTreeBuilder);
                    }
                    if (StringUtil.inSorted(strNormalName, Constants.InHeadEmpty)) {
                        Element elementInsertEmptyElementFor = htmlTreeBuilder.insertEmptyElementFor(startTagAsStartTag);
                        if (strNormalName.equals(TtmlNode.RUBY_BASE) && elementInsertEmptyElementFor.hasAttr("href")) {
                            htmlTreeBuilder.maybeSetBaseUri(elementInsertEmptyElementFor);
                        }
                    } else if (strNormalName.equals("meta")) {
                        htmlTreeBuilder.insertEmptyElementFor(startTagAsStartTag);
                    } else if (strNormalName.equals("title") || StringUtil.inSorted(strNormalName, Constants.InHeadRaw)) {
                        HtmlTreeBuilderState.HandleTextState(startTagAsStartTag, htmlTreeBuilder, htmlTreeBuilder.tagFor(startTagAsStartTag).textState());
                    } else if (strNormalName.equals("noscript")) {
                        htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                        htmlTreeBuilder.transition(InHeadNoscript);
                    } else if (strNormalName.equals("script")) {
                        htmlTreeBuilder.tokeniser.transition(TokeniserState.ScriptData);
                        htmlTreeBuilder.markInsertionMode();
                        htmlTreeBuilder.transition(Text);
                        htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                    } else {
                        if (strNormalName.equals(TtmlNode.TAG_HEAD)) {
                            htmlTreeBuilder.error(this);
                            return false;
                        }
                        if (strNormalName.equals("template")) {
                            htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                            htmlTreeBuilder.insertMarkerToFormattingElements();
                            htmlTreeBuilder.framesetOk(false);
                            htmlTreeBuilder.transition(InTemplate);
                            htmlTreeBuilder.pushTemplateMode(InTemplate);
                        } else {
                            return anythingElse(token, htmlTreeBuilder);
                        }
                    }
                } else if (i == 4) {
                    String strNormalName2 = token.asEndTag().normalName();
                    if (strNormalName2.equals(TtmlNode.TAG_HEAD)) {
                        htmlTreeBuilder.pop();
                        htmlTreeBuilder.transition(AfterHead);
                    } else {
                        if (StringUtil.inSorted(strNormalName2, Constants.InHeadEnd)) {
                            return anythingElse(token, htmlTreeBuilder);
                        }
                        if (strNormalName2.equals("template")) {
                            if (!htmlTreeBuilder.onStack(strNormalName2)) {
                                htmlTreeBuilder.error(this);
                            } else {
                                htmlTreeBuilder.generateImpliedEndTags(true);
                                if (!htmlTreeBuilder.currentElementIs(strNormalName2)) {
                                    htmlTreeBuilder.error(this);
                                }
                                htmlTreeBuilder.popStackToClose(strNormalName2);
                                htmlTreeBuilder.clearFormattingElementsToLastMarker();
                                htmlTreeBuilder.popTemplateMode();
                                htmlTreeBuilder.resetInsertionMode();
                            }
                        } else {
                            htmlTreeBuilder.error(this);
                            return false;
                        }
                    }
                } else {
                    return anythingElse(token, htmlTreeBuilder);
                }
            }
            return true;
        }

        private boolean anythingElse(Token token, TreeBuilder treeBuilder) {
            treeBuilder.processEndTag(TtmlNode.TAG_HEAD);
            return treeBuilder.process(token);
        }
    },
    InHeadNoscript { // from class: org.jsoup.parser.HtmlTreeBuilderState.5
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return true;
            }
            if (token.isStartTag() && token.asStartTag().normalName().equals("html")) {
                return htmlTreeBuilder.process(token, InBody);
            }
            if (!token.isEndTag() || !token.asEndTag().normalName().equals("noscript")) {
                if (HtmlTreeBuilderState.isWhitespace(token) || token.isComment() || (token.isStartTag() && StringUtil.inSorted(token.asStartTag().normalName(), Constants.InHeadNoScriptHead))) {
                    return htmlTreeBuilder.process(token, InHead);
                }
                if (token.isEndTag() && token.asEndTag().normalName().equals("br")) {
                    return anythingElse(token, htmlTreeBuilder);
                }
                if ((token.isStartTag() && StringUtil.inSorted(token.asStartTag().normalName(), Constants.InHeadNoscriptIgnore)) || token.isEndTag()) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                return anythingElse(token, htmlTreeBuilder);
            }
            htmlTreeBuilder.pop();
            htmlTreeBuilder.transition(InHead);
            return true;
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.error(this);
            htmlTreeBuilder.insertCharacterNode(new Token.Character().data(token.toString()));
            return true;
        }
    },
    AfterHead { // from class: org.jsoup.parser.HtmlTreeBuilderState.6
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insertCharacterNode(token.asCharacter());
                return true;
            }
            if (token.isComment()) {
                htmlTreeBuilder.insertCommentNode(token.asComment());
                return true;
            }
            if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return true;
            }
            if (token.isStartTag()) {
                Token.StartTag startTagAsStartTag = token.asStartTag();
                String strNormalName = startTagAsStartTag.normalName();
                if (strNormalName.equals("html")) {
                    return htmlTreeBuilder.process(token, InBody);
                }
                if (strNormalName.equals(TtmlNode.TAG_BODY)) {
                    htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                    htmlTreeBuilder.framesetOk(false);
                    htmlTreeBuilder.transition(InBody);
                    return true;
                }
                if (strNormalName.equals("frameset")) {
                    htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                    htmlTreeBuilder.transition(InFrameset);
                    return true;
                }
                if (StringUtil.inSorted(strNormalName, Constants.InBodyStartToHead)) {
                    htmlTreeBuilder.error(this);
                    Element headElement = htmlTreeBuilder.getHeadElement();
                    htmlTreeBuilder.push(headElement);
                    htmlTreeBuilder.process(token, InHead);
                    htmlTreeBuilder.removeFromStack(headElement);
                    return true;
                }
                if (strNormalName.equals(TtmlNode.TAG_HEAD)) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                anythingElse(token, htmlTreeBuilder);
                return true;
            }
            if (token.isEndTag()) {
                String strNormalName2 = token.asEndTag().normalName();
                if (StringUtil.inSorted(strNormalName2, Constants.AfterHeadBody)) {
                    anythingElse(token, htmlTreeBuilder);
                    return true;
                }
                if (strNormalName2.equals("template")) {
                    htmlTreeBuilder.process(token, InHead);
                    return true;
                }
                htmlTreeBuilder.error(this);
                return false;
            }
            anythingElse(token, htmlTreeBuilder);
            return true;
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.processStartTag(TtmlNode.TAG_BODY);
            htmlTreeBuilder.framesetOk(true);
            return htmlTreeBuilder.process(token);
        }
    },
    InBody { // from class: org.jsoup.parser.HtmlTreeBuilderState.7
        private static final int MaxStackScan = 24;

        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            switch (AnonymousClass25.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()]) {
                case 1:
                    htmlTreeBuilder.insertCommentNode(token.asComment());
                    return true;
                case 2:
                    htmlTreeBuilder.error(this);
                    return false;
                case 3:
                    return inBodyStartTag(token, htmlTreeBuilder);
                case 4:
                    return inBodyEndTag(token, htmlTreeBuilder);
                case 5:
                    Token.Character characterAsCharacter = token.asCharacter();
                    if (htmlTreeBuilder.framesetOk() && HtmlTreeBuilderState.isWhitespace(characterAsCharacter)) {
                        htmlTreeBuilder.reconstructFormattingElements();
                        htmlTreeBuilder.insertCharacterNode(characterAsCharacter);
                        return true;
                    }
                    htmlTreeBuilder.reconstructFormattingElements();
                    htmlTreeBuilder.insertCharacterNode(characterAsCharacter);
                    htmlTreeBuilder.framesetOk(false);
                    return true;
                case 6:
                    if (htmlTreeBuilder.templateModeSize() > 0) {
                        return htmlTreeBuilder.process(token, InTemplate);
                    }
                    if (!htmlTreeBuilder.onStackNot(Constants.InBodyEndOtherErrors)) {
                        return true;
                    }
                    htmlTreeBuilder.error(this);
                    return true;
                default:
                    Validate.wtf("Unexpected state: " + token.type);
                    return true;
            }
        }

        /* JADX WARN: Code duplicated, block: B:348:0x0553  */
        /* JADX WARN: Code duplicated, block: B:377:0x05df  */
        /* JADX WARN: Type inference failed for: r3v3 */
        /* JADX WARN: Type inference failed for: r3v4, types: [boolean, int] */
        /* JADX WARN: Type inference failed for: r3v5 */
        private boolean inBodyStartTag(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            ?? r3;
            byte b;
            Token.StartTag startTagAsStartTag = token.asStartTag();
            String strNormalName = startTagAsStartTag.normalName();
            strNormalName.hashCode();
            byte b2 = -1;
            switch (strNormalName.hashCode()) {
                case -1644953643:
                    if (strNormalName.equals("frameset")) {
                        b2 = 0;
                    }
                    break;
                case -1377687758:
                    if (strNormalName.equals("button")) {
                        b2 = 1;
                    }
                    break;
                case -1191214428:
                    if (strNormalName.equals("iframe")) {
                        b2 = 2;
                    }
                    break;
                case -1134665583:
                    if (strNormalName.equals("keygen")) {
                        b = 3;
                        b2 = b;
                    }
                    break;
                case -1010136971:
                    if (strNormalName.equals("option")) {
                        b = 4;
                        b2 = b;
                    }
                    break;
                case -1003243718:
                    if (strNormalName.equals("textarea")) {
                        b = 5;
                        b2 = b;
                    }
                    break;
                case -906021636:
                    if (strNormalName.equals("select")) {
                        b = 6;
                        b2 = b;
                    }
                    break;
                case -891985998:
                    if (strNormalName.equals("strike")) {
                        b = 7;
                        b2 = b;
                    }
                    break;
                case -891980137:
                    if (strNormalName.equals("strong")) {
                        b = 8;
                        b2 = b;
                    }
                    break;
                case -80773204:
                    if (strNormalName.equals("optgroup")) {
                        b = 9;
                        b2 = b;
                    }
                    break;
                case 97:
                    if (strNormalName.equals(CmcdData.OBJECT_TYPE_AUDIO_ONLY)) {
                        b = 10;
                        b2 = b;
                    }
                    break;
                case 98:
                    if (strNormalName.equals("b")) {
                        b = 11;
                        b2 = b;
                    }
                    break;
                case 105:
                    if (strNormalName.equals(CmcdData.OBJECT_TYPE_INIT_SEGMENT)) {
                        b = Ascii.FF;
                        b2 = b;
                    }
                    break;
                case 115:
                    if (strNormalName.equals(CmcdData.STREAMING_FORMAT_SS)) {
                        b = Ascii.CR;
                        b2 = b;
                    }
                    break;
                case 117:
                    if (strNormalName.equals("u")) {
                        b = Ascii.SO;
                        b2 = b;
                    }
                    break;
                case 3152:
                    if (strNormalName.equals("br")) {
                        b = Ascii.SI;
                        b2 = b;
                    }
                    break;
                case 3200:
                    if (strNormalName.equals("dd")) {
                        b = Ascii.DLE;
                        b2 = b;
                    }
                    break;
                case 3216:
                    if (strNormalName.equals("dt")) {
                        b = 17;
                        b2 = b;
                    }
                    break;
                case 3240:
                    if (strNormalName.equals("em")) {
                        b = Ascii.DC2;
                        b2 = b;
                    }
                    break;
                case 3273:
                    if (strNormalName.equals("h1")) {
                        b = 19;
                        b2 = b;
                    }
                    break;
                case 3274:
                    if (strNormalName.equals("h2")) {
                        b = Ascii.DC4;
                        b2 = b;
                    }
                    break;
                case 3275:
                    if (strNormalName.equals("h3")) {
                        b = Ascii.NAK;
                        b2 = b;
                    }
                    break;
                case 3276:
                    if (strNormalName.equals("h4")) {
                        b = Ascii.SYN;
                        b2 = b;
                    }
                    break;
                case 3277:
                    if (strNormalName.equals("h5")) {
                        b = Ascii.ETB;
                        b2 = b;
                    }
                    break;
                case 3278:
                    if (strNormalName.equals("h6")) {
                        b2 = Ascii.CAN;
                    }
                    break;
                case 3338:
                    if (strNormalName.equals("hr")) {
                        b2 = 25;
                    }
                    break;
                case 3453:
                    if (strNormalName.equals("li")) {
                        b = Ascii.SUB;
                        b2 = b;
                    }
                    break;
                case 3632:
                    if (strNormalName.equals("rb")) {
                        b = Ascii.ESC;
                        b2 = b;
                    }
                    break;
                case 3646:
                    if (strNormalName.equals("rp")) {
                        b = Ascii.FS;
                        b2 = b;
                    }
                    break;
                case 3650:
                    if (strNormalName.equals("rt")) {
                        b = Ascii.GS;
                        b2 = b;
                    }
                    break;
                case 3712:
                    if (strNormalName.equals(TtmlNode.TAG_TT)) {
                        b = Ascii.RS;
                        b2 = b;
                    }
                    break;
                case 97536:
                    if (strNormalName.equals("big")) {
                        b = Ascii.US;
                        b2 = b;
                    }
                    break;
                case 104387:
                    if (strNormalName.equals("img")) {
                        b = 32;
                        b2 = b;
                    }
                    break;
                case 111267:
                    if (strNormalName.equals("pre")) {
                        b = 33;
                        b2 = b;
                    }
                    break;
                case 113249:
                    if (strNormalName.equals("rtc")) {
                        b = 34;
                        b2 = b;
                    }
                    break;
                case 114276:
                    if (strNormalName.equals("svg")) {
                        b = 35;
                        b2 = b;
                    }
                    break;
                case 117511:
                    if (strNormalName.equals("wbr")) {
                        b = 36;
                        b2 = b;
                    }
                    break;
                case 118811:
                    if (strNormalName.equals("xmp")) {
                        b = 37;
                        b2 = b;
                    }
                    break;
                case 3002509:
                    if (strNormalName.equals("area")) {
                        b = 38;
                        b2 = b;
                    }
                    break;
                case 3029410:
                    if (strNormalName.equals(TtmlNode.TAG_BODY)) {
                        b = 39;
                        b2 = b;
                    }
                    break;
                case 3059181:
                    if (strNormalName.equals("code")) {
                        b = 40;
                        b2 = b;
                    }
                    break;
                case 3148879:
                    if (strNormalName.equals(ContentType.Font.TYPE)) {
                        b = 41;
                        b2 = b;
                    }
                    break;
                case 3148996:
                    if (strNormalName.equals("form")) {
                        b = 42;
                        b2 = b;
                    }
                    break;
                case 3213227:
                    if (strNormalName.equals("html")) {
                        b = 43;
                        b2 = b;
                    }
                    break;
                case 3344136:
                    if (strNormalName.equals("math")) {
                        b = 44;
                        b2 = b;
                    }
                    break;
                case 3386833:
                    if (strNormalName.equals("nobr")) {
                        b = 45;
                        b2 = b;
                    }
                    break;
                case 3536714:
                    if (strNormalName.equals(TtmlNode.TAG_SPAN)) {
                        b = 46;
                        b2 = b;
                    }
                    break;
                case 96620249:
                    if (strNormalName.equals("embed")) {
                        b = 47;
                        b2 = b;
                    }
                    break;
                case 100313435:
                    if (strNormalName.equals("image")) {
                        b = DerTagKt.DerTag_SEQUENCE;
                        b2 = b;
                    }
                    break;
                case 100358090:
                    if (strNormalName.equals(TvContractCompat.PARAM_INPUT)) {
                        b = 49;
                        b2 = b;
                    }
                    break;
                case 109548807:
                    if (strNormalName.equals("small")) {
                        b = 50;
                        b2 = b;
                    }
                    break;
                case 110115790:
                    if (strNormalName.equals("table")) {
                        b = 51;
                        b2 = b;
                    }
                    break;
                case 181975684:
                    if (strNormalName.equals("listing")) {
                        b = 52;
                        b2 = b;
                    }
                    break;
                case 1973234167:
                    if (strNormalName.equals("plaintext")) {
                        b = 53;
                        b2 = b;
                    }
                    break;
                case 2115613112:
                    if (strNormalName.equals("noembed")) {
                        b = 54;
                        b2 = b;
                    }
                    break;
            }
            switch (b2) {
                case 0:
                    htmlTreeBuilder.error(this);
                    ArrayList<Element> stack = htmlTreeBuilder.getStack();
                    if (stack.size() < 2) {
                        return false;
                    }
                    if (stack.size() > 2) {
                        r3 = 1;
                        if (!stack.get(1).nameIs(TtmlNode.TAG_BODY)) {
                            return false;
                        }
                    } else {
                        r3 = 1;
                    }
                    if (!htmlTreeBuilder.framesetOk()) {
                        return false;
                    }
                    Element element = stack.get(r3);
                    if (element.parent() != null) {
                        element.remove();
                    }
                    while (stack.size() > r3) {
                        stack.remove(stack.size() - r3);
                    }
                    htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                    htmlTreeBuilder.transition(InFrameset);
                    return r3;
                case 1:
                    if (htmlTreeBuilder.inButtonScope("button")) {
                        htmlTreeBuilder.error(this);
                        htmlTreeBuilder.processEndTag("button");
                        htmlTreeBuilder.process(startTagAsStartTag);
                    } else {
                        htmlTreeBuilder.reconstructFormattingElements();
                        htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                        htmlTreeBuilder.framesetOk(false);
                    }
                    return true;
                case 2:
                    htmlTreeBuilder.framesetOk(false);
                    HtmlTreeBuilderState.HandleTextState(startTagAsStartTag, htmlTreeBuilder, htmlTreeBuilder.tagFor(startTagAsStartTag).textState());
                    return true;
                case 3:
                case 15:
                case 32:
                case 36:
                case 38:
                case 47:
                    htmlTreeBuilder.reconstructFormattingElements();
                    htmlTreeBuilder.insertEmptyElementFor(startTagAsStartTag);
                    htmlTreeBuilder.framesetOk(false);
                    return true;
                case 4:
                case 9:
                    if (htmlTreeBuilder.currentElementIs("option")) {
                        htmlTreeBuilder.processEndTag("option");
                    }
                    htmlTreeBuilder.reconstructFormattingElements();
                    htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                    return true;
                case 5:
                    htmlTreeBuilder.framesetOk(false);
                    HtmlTreeBuilderState.HandleTextState(startTagAsStartTag, htmlTreeBuilder, htmlTreeBuilder.tagFor(startTagAsStartTag).textState());
                    return true;
                case 6:
                    htmlTreeBuilder.reconstructFormattingElements();
                    htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                    htmlTreeBuilder.framesetOk(false);
                    if (!startTagAsStartTag.selfClosing) {
                        HtmlTreeBuilderState htmlTreeBuilderStateState = htmlTreeBuilder.state();
                        if (htmlTreeBuilderStateState.equals(InTable) || htmlTreeBuilderStateState.equals(InCaption) || htmlTreeBuilderStateState.equals(InTableBody) || htmlTreeBuilderStateState.equals(InRow) || htmlTreeBuilderStateState.equals(InCell)) {
                            htmlTreeBuilder.transition(InSelectInTable);
                        } else {
                            htmlTreeBuilder.transition(InSelect);
                        }
                    }
                    return true;
                case 7:
                case 8:
                case 11:
                case 12:
                case 13:
                case 14:
                case 18:
                case 30:
                case 31:
                case 40:
                case 41:
                case 50:
                    htmlTreeBuilder.reconstructFormattingElements();
                    htmlTreeBuilder.pushActiveFormattingElements(htmlTreeBuilder.insertElementFor(startTagAsStartTag));
                    return true;
                case 10:
                    if (htmlTreeBuilder.getActiveFormattingElement(CmcdData.OBJECT_TYPE_AUDIO_ONLY) != null) {
                        htmlTreeBuilder.error(this);
                        htmlTreeBuilder.processEndTag(CmcdData.OBJECT_TYPE_AUDIO_ONLY);
                        Element fromStack = htmlTreeBuilder.getFromStack(CmcdData.OBJECT_TYPE_AUDIO_ONLY);
                        if (fromStack != null) {
                            htmlTreeBuilder.removeFromActiveFormattingElements(fromStack);
                            htmlTreeBuilder.removeFromStack(fromStack);
                        }
                    }
                    htmlTreeBuilder.reconstructFormattingElements();
                    htmlTreeBuilder.pushActiveFormattingElements(htmlTreeBuilder.insertElementFor(startTagAsStartTag));
                    return true;
                case 16:
                case 17:
                    htmlTreeBuilder.framesetOk(false);
                    ArrayList<Element> stack2 = htmlTreeBuilder.getStack();
                    int size = stack2.size();
                    int i = size - 1;
                    int i2 = i >= 24 ? size - 25 : 0;
                    while (i >= i2) {
                        Element element2 = stack2.get(i);
                        if (StringUtil.inSorted(element2.normalName(), Constants.DdDt)) {
                            htmlTreeBuilder.processEndTag(element2.normalName());
                        } else if (!HtmlTreeBuilder.isSpecial(element2) || StringUtil.inSorted(element2.normalName(), Constants.InBodyStartLiBreakers)) {
                            i--;
                        }
                        if (htmlTreeBuilder.inButtonScope(TtmlNode.TAG_P)) {
                            htmlTreeBuilder.processEndTag(TtmlNode.TAG_P);
                        }
                        htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                        return true;
                    }
                    if (htmlTreeBuilder.inButtonScope(TtmlNode.TAG_P)) {
                        htmlTreeBuilder.processEndTag(TtmlNode.TAG_P);
                    }
                    htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                    return true;
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                    if (htmlTreeBuilder.inButtonScope(TtmlNode.TAG_P)) {
                        htmlTreeBuilder.processEndTag(TtmlNode.TAG_P);
                    }
                    if (StringUtil.inSorted(htmlTreeBuilder.currentElement().normalName(), Constants.Headings)) {
                        htmlTreeBuilder.error(this);
                        htmlTreeBuilder.pop();
                    }
                    htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                    return true;
                case 25:
                    if (htmlTreeBuilder.inButtonScope(TtmlNode.TAG_P)) {
                        htmlTreeBuilder.processEndTag(TtmlNode.TAG_P);
                    }
                    htmlTreeBuilder.insertEmptyElementFor(startTagAsStartTag);
                    htmlTreeBuilder.framesetOk(false);
                    return true;
                case 26:
                    htmlTreeBuilder.framesetOk(false);
                    ArrayList<Element> stack3 = htmlTreeBuilder.getStack();
                    for (int size2 = stack3.size() - 1; size2 > 0; size2--) {
                        Element element3 = stack3.get(size2);
                        if (element3.nameIs("li")) {
                            htmlTreeBuilder.processEndTag("li");
                        } else if (!HtmlTreeBuilder.isSpecial(element3) || StringUtil.inSorted(element3.normalName(), Constants.InBodyStartLiBreakers)) {
                        }
                        if (htmlTreeBuilder.inButtonScope(TtmlNode.TAG_P)) {
                            htmlTreeBuilder.processEndTag(TtmlNode.TAG_P);
                        }
                        htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                        return true;
                    }
                    if (htmlTreeBuilder.inButtonScope(TtmlNode.TAG_P)) {
                        htmlTreeBuilder.processEndTag(TtmlNode.TAG_P);
                    }
                    htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                    return true;
                case 27:
                case 34:
                    if (htmlTreeBuilder.inScope(TtmlNode.ATTR_TTS_RUBY)) {
                        htmlTreeBuilder.generateImpliedEndTags();
                        if (!htmlTreeBuilder.currentElementIs(TtmlNode.ATTR_TTS_RUBY)) {
                            htmlTreeBuilder.error(this);
                        }
                    }
                    htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                    return true;
                case 28:
                case 29:
                    if (htmlTreeBuilder.inScope(TtmlNode.ATTR_TTS_RUBY)) {
                        htmlTreeBuilder.generateImpliedEndTags("rtc");
                        if (!htmlTreeBuilder.currentElementIs("rtc") && !htmlTreeBuilder.currentElementIs(TtmlNode.ATTR_TTS_RUBY)) {
                            htmlTreeBuilder.error(this);
                        }
                    }
                    htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                    return true;
                case 33:
                case 52:
                    if (htmlTreeBuilder.inButtonScope(TtmlNode.TAG_P)) {
                        htmlTreeBuilder.processEndTag(TtmlNode.TAG_P);
                    }
                    htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                    htmlTreeBuilder.reader.matchConsume("\n");
                    htmlTreeBuilder.framesetOk(false);
                    return true;
                case 35:
                    htmlTreeBuilder.reconstructFormattingElements();
                    htmlTreeBuilder.insertForeignElementFor(startTagAsStartTag, "http://www.w3.org/2000/svg");
                    return true;
                case 37:
                    if (htmlTreeBuilder.inButtonScope(TtmlNode.TAG_P)) {
                        htmlTreeBuilder.processEndTag(TtmlNode.TAG_P);
                    }
                    htmlTreeBuilder.reconstructFormattingElements();
                    htmlTreeBuilder.framesetOk(false);
                    HtmlTreeBuilderState.HandleTextState(startTagAsStartTag, htmlTreeBuilder, htmlTreeBuilder.tagFor(startTagAsStartTag).textState());
                    return true;
                case 39:
                    htmlTreeBuilder.error(this);
                    ArrayList<Element> stack4 = htmlTreeBuilder.getStack();
                    if (stack4.size() < 2) {
                        return false;
                    }
                    if ((stack4.size() > 2 && !stack4.get(1).nameIs(TtmlNode.TAG_BODY)) || htmlTreeBuilder.onStack("template")) {
                        return false;
                    }
                    htmlTreeBuilder.framesetOk(false);
                    Element fromStack2 = htmlTreeBuilder.getFromStack(TtmlNode.TAG_BODY);
                    if (fromStack2 != null) {
                        HtmlTreeBuilderState.mergeAttributes(startTagAsStartTag, fromStack2);
                    }
                    return true;
                case 42:
                    if (htmlTreeBuilder.getFormElement() != null && !htmlTreeBuilder.onStack("template")) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    if (htmlTreeBuilder.inButtonScope(TtmlNode.TAG_P)) {
                        htmlTreeBuilder.closeElement(TtmlNode.TAG_P);
                    }
                    htmlTreeBuilder.insertFormElement(startTagAsStartTag, true, true);
                    return true;
                case 43:
                    htmlTreeBuilder.error(this);
                    if (htmlTreeBuilder.onStack("template")) {
                        return false;
                    }
                    if (htmlTreeBuilder.getStack().size() > 0) {
                        HtmlTreeBuilderState.mergeAttributes(startTagAsStartTag, htmlTreeBuilder.getStack().get(0));
                    }
                    return true;
                case 44:
                    htmlTreeBuilder.reconstructFormattingElements();
                    htmlTreeBuilder.insertForeignElementFor(startTagAsStartTag, "http://www.w3.org/1998/Math/MathML");
                    return true;
                case 45:
                    htmlTreeBuilder.reconstructFormattingElements();
                    if (htmlTreeBuilder.inScope("nobr")) {
                        htmlTreeBuilder.error(this);
                        htmlTreeBuilder.processEndTag("nobr");
                        htmlTreeBuilder.reconstructFormattingElements();
                    }
                    htmlTreeBuilder.pushActiveFormattingElements(htmlTreeBuilder.insertElementFor(startTagAsStartTag));
                    return true;
                case 46:
                    htmlTreeBuilder.reconstructFormattingElements();
                    htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                    return true;
                case 48:
                    if (htmlTreeBuilder.getFromStack("svg") == null) {
                        return htmlTreeBuilder.process(startTagAsStartTag.name("img"));
                    }
                    htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                    return true;
                case 49:
                    htmlTreeBuilder.reconstructFormattingElements();
                    if (!htmlTreeBuilder.insertEmptyElementFor(startTagAsStartTag).attr("type").equalsIgnoreCase("hidden")) {
                        htmlTreeBuilder.framesetOk(false);
                    }
                    return true;
                case 51:
                    if (htmlTreeBuilder.getDocument().quirksMode() != Document.QuirksMode.quirks && htmlTreeBuilder.inButtonScope(TtmlNode.TAG_P)) {
                        htmlTreeBuilder.processEndTag(TtmlNode.TAG_P);
                    }
                    htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                    htmlTreeBuilder.framesetOk(false);
                    htmlTreeBuilder.transition(InTable);
                    return true;
                case 53:
                    if (htmlTreeBuilder.inButtonScope(TtmlNode.TAG_P)) {
                        htmlTreeBuilder.processEndTag(TtmlNode.TAG_P);
                    }
                    htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                    htmlTreeBuilder.tokeniser.transition(TokeniserState.PLAINTEXT);
                    return true;
                case 54:
                    HtmlTreeBuilderState.HandleTextState(startTagAsStartTag, htmlTreeBuilder, htmlTreeBuilder.tagFor(startTagAsStartTag).textState());
                    return true;
                default:
                    Tag tagTagFor = htmlTreeBuilder.tagFor(startTagAsStartTag);
                    TokeniserState tokeniserStateTextState = tagTagFor.textState();
                    if (tokeniserStateTextState != null) {
                        HtmlTreeBuilderState.HandleTextState(startTagAsStartTag, htmlTreeBuilder, tokeniserStateTextState);
                    } else if (!tagTagFor.isKnownTag()) {
                        htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                    } else if (StringUtil.inSorted(strNormalName, Constants.InBodyStartPClosers)) {
                        if (htmlTreeBuilder.inButtonScope(TtmlNode.TAG_P)) {
                            htmlTreeBuilder.processEndTag(TtmlNode.TAG_P);
                        }
                        htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                    } else {
                        if (StringUtil.inSorted(strNormalName, Constants.InBodyStartToHead)) {
                            return htmlTreeBuilder.process(token, InHead);
                        }
                        if (StringUtil.inSorted(strNormalName, Constants.InBodyStartApplets)) {
                            htmlTreeBuilder.reconstructFormattingElements();
                            htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                            htmlTreeBuilder.insertMarkerToFormattingElements();
                            htmlTreeBuilder.framesetOk(false);
                        } else if (StringUtil.inSorted(strNormalName, Constants.InBodyStartMedia)) {
                            htmlTreeBuilder.insertEmptyElementFor(startTagAsStartTag);
                        } else {
                            if (StringUtil.inSorted(strNormalName, Constants.InBodyStartDrop)) {
                                htmlTreeBuilder.error(this);
                                return false;
                            }
                            htmlTreeBuilder.reconstructFormattingElements();
                            htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                        }
                    }
                    return true;
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        private boolean inBodyEndTag(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            Token.EndTag endTagAsEndTag = token.asEndTag();
            String strNormalName = endTagAsEndTag.normalName();
            strNormalName.hashCode();
            byte b = -1;
            switch (strNormalName.hashCode()) {
                case -1321546630:
                    if (strNormalName.equals("template")) {
                        b = 0;
                    }
                    break;
                case 112:
                    if (strNormalName.equals(TtmlNode.TAG_P)) {
                        b = 1;
                    }
                    break;
                case 3152:
                    if (strNormalName.equals("br")) {
                        b = 2;
                    }
                    break;
                case 3200:
                    if (strNormalName.equals("dd")) {
                        b = 3;
                    }
                    break;
                case 3216:
                    if (strNormalName.equals("dt")) {
                        b = 4;
                    }
                    break;
                case 3273:
                    if (strNormalName.equals("h1")) {
                        b = 5;
                    }
                    break;
                case 3274:
                    if (strNormalName.equals("h2")) {
                        b = 6;
                    }
                    break;
                case 3275:
                    if (strNormalName.equals("h3")) {
                        b = 7;
                    }
                    break;
                case 3276:
                    if (strNormalName.equals("h4")) {
                        b = 8;
                    }
                    break;
                case 3277:
                    if (strNormalName.equals("h5")) {
                        b = 9;
                    }
                    break;
                case 3278:
                    if (strNormalName.equals("h6")) {
                        b = 10;
                    }
                    break;
                case 3453:
                    if (strNormalName.equals("li")) {
                        b = 11;
                    }
                    break;
                case 3029410:
                    if (strNormalName.equals(TtmlNode.TAG_BODY)) {
                        b = Ascii.FF;
                    }
                    break;
                case 3148996:
                    if (strNormalName.equals("form")) {
                        b = Ascii.CR;
                    }
                    break;
                case 3213227:
                    if (strNormalName.equals("html")) {
                        b = Ascii.SO;
                    }
                    break;
                case 3536714:
                    if (strNormalName.equals(TtmlNode.TAG_SPAN)) {
                        b = Ascii.SI;
                    }
                    break;
                case 1869063452:
                    if (strNormalName.equals("sarcasm")) {
                        b = Ascii.DLE;
                    }
                    break;
            }
            switch (b) {
                case 0:
                    htmlTreeBuilder.process(token, InHead);
                    return true;
                case 1:
                    if (!htmlTreeBuilder.inButtonScope(strNormalName)) {
                        htmlTreeBuilder.error(this);
                        htmlTreeBuilder.processStartTag(strNormalName);
                        return htmlTreeBuilder.process(endTagAsEndTag);
                    }
                    htmlTreeBuilder.generateImpliedEndTags(strNormalName);
                    if (!htmlTreeBuilder.currentElementIs(strNormalName)) {
                        htmlTreeBuilder.error(this);
                    }
                    htmlTreeBuilder.popStackToClose(strNormalName);
                    return true;
                case 2:
                    htmlTreeBuilder.error(this);
                    htmlTreeBuilder.processStartTag("br");
                    return false;
                case 3:
                case 4:
                    if (!htmlTreeBuilder.inScope(strNormalName)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.generateImpliedEndTags(strNormalName);
                    if (!htmlTreeBuilder.currentElementIs(strNormalName)) {
                        htmlTreeBuilder.error(this);
                    }
                    htmlTreeBuilder.popStackToClose(strNormalName);
                    return true;
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                    if (!htmlTreeBuilder.inScope(Constants.Headings)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.generateImpliedEndTags(strNormalName);
                    if (!htmlTreeBuilder.currentElementIs(strNormalName)) {
                        htmlTreeBuilder.error(this);
                    }
                    htmlTreeBuilder.popStackToClose(Constants.Headings);
                    return true;
                case 11:
                    if (!htmlTreeBuilder.inListItemScope(strNormalName)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.generateImpliedEndTags(strNormalName);
                    if (!htmlTreeBuilder.currentElementIs(strNormalName)) {
                        htmlTreeBuilder.error(this);
                    }
                    htmlTreeBuilder.popStackToClose(strNormalName);
                    return true;
                case 12:
                    if (!htmlTreeBuilder.inScope(TtmlNode.TAG_BODY)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    if (htmlTreeBuilder.onStackNot(Constants.InBodyEndOtherErrors)) {
                        htmlTreeBuilder.error(this);
                    }
                    htmlTreeBuilder.trackNodePosition(htmlTreeBuilder.getFromStack(TtmlNode.TAG_BODY), false);
                    htmlTreeBuilder.transition(AfterBody);
                    return true;
                case 13:
                    if (!htmlTreeBuilder.onStack("template")) {
                        FormElement formElement = htmlTreeBuilder.getFormElement();
                        htmlTreeBuilder.setFormElement(null);
                        if (formElement == null || !htmlTreeBuilder.inScope(strNormalName)) {
                            htmlTreeBuilder.error(this);
                            return false;
                        }
                        htmlTreeBuilder.generateImpliedEndTags();
                        if (!htmlTreeBuilder.currentElementIs(strNormalName)) {
                            htmlTreeBuilder.error(this);
                        }
                        htmlTreeBuilder.removeFromStack(formElement);
                    } else {
                        if (!htmlTreeBuilder.inScope(strNormalName)) {
                            htmlTreeBuilder.error(this);
                            return false;
                        }
                        htmlTreeBuilder.generateImpliedEndTags();
                        if (!htmlTreeBuilder.currentElementIs(strNormalName)) {
                            htmlTreeBuilder.error(this);
                        }
                        htmlTreeBuilder.popStackToClose(strNormalName);
                    }
                    return true;
                case 14:
                    if (!htmlTreeBuilder.onStack(TtmlNode.TAG_BODY)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    if (htmlTreeBuilder.onStackNot(Constants.InBodyEndOtherErrors)) {
                        htmlTreeBuilder.error(this);
                    }
                    htmlTreeBuilder.transition(AfterBody);
                    return htmlTreeBuilder.process(token);
                case 15:
                case 16:
                    return anyOtherEndTag(token, htmlTreeBuilder);
                default:
                    if (StringUtil.inSorted(strNormalName, Constants.InBodyEndAdoptionFormatters)) {
                        return inBodyEndTagAdoption(token, htmlTreeBuilder);
                    }
                    if (StringUtil.inSorted(strNormalName, Constants.InBodyEndClosers)) {
                        if (!htmlTreeBuilder.inScope(strNormalName)) {
                            htmlTreeBuilder.error(this);
                            return false;
                        }
                        htmlTreeBuilder.generateImpliedEndTags();
                        if (!htmlTreeBuilder.currentElementIs(strNormalName)) {
                            htmlTreeBuilder.error(this);
                        }
                        htmlTreeBuilder.popStackToClose(strNormalName);
                    } else if (StringUtil.inSorted(strNormalName, Constants.InBodyStartApplets)) {
                        if (!htmlTreeBuilder.inScope("name")) {
                            if (!htmlTreeBuilder.inScope(strNormalName)) {
                                htmlTreeBuilder.error(this);
                                return false;
                            }
                            htmlTreeBuilder.generateImpliedEndTags();
                            if (!htmlTreeBuilder.currentElementIs(strNormalName)) {
                                htmlTreeBuilder.error(this);
                            }
                            htmlTreeBuilder.popStackToClose(strNormalName);
                            htmlTreeBuilder.clearFormattingElementsToLastMarker();
                        }
                    } else {
                        return anyOtherEndTag(token, htmlTreeBuilder);
                    }
                    return true;
            }
        }

        boolean anyOtherEndTag(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            String str = token.asEndTag().normalName;
            ArrayList<Element> stack = htmlTreeBuilder.getStack();
            if (htmlTreeBuilder.getFromStack(str) == null) {
                htmlTreeBuilder.error(this);
                return false;
            }
            for (int size = stack.size() - 1; size >= 0; size--) {
                Element element = stack.get(size);
                if (element.nameIs(str)) {
                    htmlTreeBuilder.generateImpliedEndTags(str);
                    if (!htmlTreeBuilder.currentElementIs(str)) {
                        htmlTreeBuilder.error(this);
                    }
                    htmlTreeBuilder.popStackToClose(str);
                    break;
                }
                if (HtmlTreeBuilder.isSpecial(element)) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
            }
            return true;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r10v1 */
        /* JADX WARN: Type inference failed for: r10v2, types: [int] */
        /* JADX WARN: Type inference failed for: r10v3, types: [int] */
        /* JADX WARN: Type inference failed for: r19v0, types: [org.jsoup.parser.HtmlTreeBuilder] */
        /* JADX WARN: Type inference failed for: r4v0 */
        /* JADX WARN: Type inference failed for: r4v1, types: [boolean, int] */
        /* JADX WARN: Type inference failed for: r4v11 */
        /* JADX WARN: Type inference failed for: r4v2 */
        /* JADX WARN: Type inference failed for: r4v7 */
        /* JADX WARN: Type inference failed for: r4v8 */
        /* JADX WARN: Type inference failed for: r6v3, types: [int] */
        private boolean inBodyEndTagAdoption(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            Element element;
            Element element2;
            String str = token.asEndTag().normalName;
            ?? r4 = 1;
            if (htmlTreeBuilder.currentElement().normalName().equals(str) && !htmlTreeBuilder.isInActiveFormattingElements(htmlTreeBuilder.currentElement())) {
                htmlTreeBuilder.pop();
                return true;
            }
            boolean z = false;
            int i = 0;
            while (i < 8) {
                i++;
                int size = htmlTreeBuilder.formattingElements.size() - r4;
                while (true) {
                    element = null;
                    if (size < 0 || (element2 = htmlTreeBuilder.formattingElements.get(size)) == null) {
                        element2 = null;
                        break;
                    }
                    if (element2.normalName().equals(str)) {
                        break;
                    }
                    size--;
                }
                if (element2 == null) {
                    return anyOtherEndTag(token, htmlTreeBuilder);
                }
                if (!htmlTreeBuilder.onStack(element2)) {
                    htmlTreeBuilder.error(this);
                    htmlTreeBuilder.removeFromActiveFormattingElements(element2);
                    return r4;
                }
                if (!htmlTreeBuilder.inScope(element2.normalName())) {
                    htmlTreeBuilder.error(this);
                    return z;
                }
                if (htmlTreeBuilder.currentElement() != element2) {
                    htmlTreeBuilder.error(this);
                }
                ArrayList<Element> stack = htmlTreeBuilder.getStack();
                int iLastIndexOf = stack.lastIndexOf(element2);
                if (iLastIndexOf != -1) {
                    while (true) {
                        iLastIndexOf++;
                        if (iLastIndexOf >= stack.size()) {
                            break;
                        }
                        Element element3 = stack.get(iLastIndexOf);
                        if (HtmlTreeBuilder.isSpecial(element3)) {
                            element = element3;
                            break;
                        }
                    }
                }
                if (element == null) {
                    while (htmlTreeBuilder.currentElement() != element2) {
                        htmlTreeBuilder.pop();
                    }
                    htmlTreeBuilder.pop();
                    htmlTreeBuilder.removeFromActiveFormattingElements(element2);
                    return r4;
                }
                Element elementAboveOnStack = htmlTreeBuilder.aboveOnStack(element2);
                if (elementAboveOnStack == null) {
                    htmlTreeBuilder.error(this);
                    return r4;
                }
                int iPositionOfElement = htmlTreeBuilder.positionOfElement(element2);
                ?? r10 = z;
                Element elementAboveOnStack2 = element;
                Element element4 = elementAboveOnStack2;
                while (true) {
                    r10 += r4 == true ? 1 : 0;
                    if (!htmlTreeBuilder.onStack(elementAboveOnStack2)) {
                        elementAboveOnStack2 = elementAboveOnStack2.parent();
                    } else {
                        elementAboveOnStack2 = htmlTreeBuilder.aboveOnStack(elementAboveOnStack2);
                    }
                    if (elementAboveOnStack2 == null || elementAboveOnStack2.nameIs(TtmlNode.TAG_BODY)) {
                        htmlTreeBuilder.error(this);
                    } else if (elementAboveOnStack2 != element2) {
                        if (r10 > 3 && htmlTreeBuilder.isInActiveFormattingElements(elementAboveOnStack2)) {
                            htmlTreeBuilder.removeFromActiveFormattingElements(elementAboveOnStack2);
                        } else if (!htmlTreeBuilder.isInActiveFormattingElements(elementAboveOnStack2)) {
                            htmlTreeBuilder.removeFromStack(elementAboveOnStack2);
                        } else if (!htmlTreeBuilder.onStack(elementAboveOnStack2)) {
                            htmlTreeBuilder.error(this);
                            htmlTreeBuilder.removeFromActiveFormattingElements(elementAboveOnStack2);
                        } else {
                            String strNodeName = elementAboveOnStack2.nodeName();
                            String strNormalName = elementAboveOnStack2.normalName();
                            String strDefaultNamespace = htmlTreeBuilder.defaultNamespace();
                            boolean z2 = r4 == true ? 1 : 0;
                            Element element5 = new Element(htmlTreeBuilder.tagFor(strNodeName, strNormalName, strDefaultNamespace, ParseSettings.preserveCase), htmlTreeBuilder.getBaseUri());
                            htmlTreeBuilder.replaceActiveFormattingElement(elementAboveOnStack2, element5);
                            htmlTreeBuilder.replaceOnStack(elementAboveOnStack2, element5);
                            if (element4 == element) {
                                iPositionOfElement = htmlTreeBuilder.positionOfElement(element5) + 1;
                            }
                            element5.appendChild(element4);
                            elementAboveOnStack2 = element5;
                            element4 = elementAboveOnStack2;
                            r4 = z2 ? 1 : 0;
                        }
                    }
                    elementAboveOnStack.appendChild(element4);
                    Element element6 = new Element(element2.tag(), htmlTreeBuilder.getBaseUri());
                    element6.attributes().addAll(element2.attributes());
                    Iterator<Node> it = element.childNodes().iterator();
                    while (it.hasNext()) {
                        element6.appendChild(it.next());
                    }
                    element.appendChild(element6);
                    htmlTreeBuilder.removeFromActiveFormattingElements(element2);
                    htmlTreeBuilder.pushWithBookmark(element6, iPositionOfElement);
                    htmlTreeBuilder.removeFromStack(element2);
                    htmlTreeBuilder.insertOnStackAfter(element, element6);
                    r4 = r4 == true ? 1 : 0;
                    z = false;
                }
            }
            return r4;
        }
    },
    Text { // from class: org.jsoup.parser.HtmlTreeBuilderState.8
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isCharacter()) {
                htmlTreeBuilder.insertCharacterNode(token.asCharacter());
                return true;
            }
            if (token.isEOF()) {
                htmlTreeBuilder.error(this);
                htmlTreeBuilder.pop();
                htmlTreeBuilder.transition(htmlTreeBuilder.originalState());
                if (htmlTreeBuilder.state() == Text) {
                    htmlTreeBuilder.transition(InBody);
                }
                return htmlTreeBuilder.process(token);
            }
            if (!token.isEndTag()) {
                return true;
            }
            htmlTreeBuilder.pop();
            htmlTreeBuilder.transition(htmlTreeBuilder.originalState());
            return true;
        }
    },
    InTable { // from class: org.jsoup.parser.HtmlTreeBuilderState.9
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isCharacter() && StringUtil.inSorted(htmlTreeBuilder.currentElement().normalName(), Constants.InTableFoster)) {
                htmlTreeBuilder.resetPendingTableCharacters();
                htmlTreeBuilder.markInsertionMode();
                htmlTreeBuilder.transition(InTableText);
                return htmlTreeBuilder.process(token);
            }
            if (token.isComment()) {
                htmlTreeBuilder.insertCommentNode(token.asComment());
                return true;
            }
            if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            }
            if (token.isStartTag()) {
                Token.StartTag startTagAsStartTag = token.asStartTag();
                String strNormalName = startTagAsStartTag.normalName();
                if (strNormalName.equals(MediaTrack.ROLE_CAPTION)) {
                    htmlTreeBuilder.clearStackToTableContext();
                    htmlTreeBuilder.insertMarkerToFormattingElements();
                    htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                    htmlTreeBuilder.transition(InCaption);
                } else if (strNormalName.equals("colgroup")) {
                    htmlTreeBuilder.clearStackToTableContext();
                    htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                    htmlTreeBuilder.transition(InColumnGroup);
                } else {
                    if (strNormalName.equals("col")) {
                        htmlTreeBuilder.clearStackToTableContext();
                        htmlTreeBuilder.processStartTag("colgroup");
                        return htmlTreeBuilder.process(token);
                    }
                    if (StringUtil.inSorted(strNormalName, Constants.InTableToBody)) {
                        htmlTreeBuilder.clearStackToTableContext();
                        htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                        htmlTreeBuilder.transition(InTableBody);
                    } else {
                        if (StringUtil.inSorted(strNormalName, Constants.InTableAddBody)) {
                            htmlTreeBuilder.clearStackToTableContext();
                            htmlTreeBuilder.processStartTag("tbody");
                            return htmlTreeBuilder.process(token);
                        }
                        if (strNormalName.equals("table")) {
                            htmlTreeBuilder.error(this);
                            if (!htmlTreeBuilder.inTableScope(strNormalName)) {
                                return false;
                            }
                            htmlTreeBuilder.popStackToClose(strNormalName);
                            if (!htmlTreeBuilder.resetInsertionMode()) {
                                htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                                return true;
                            }
                            return htmlTreeBuilder.process(token);
                        }
                        if (StringUtil.inSorted(strNormalName, Constants.InTableToHead)) {
                            return htmlTreeBuilder.process(token, InHead);
                        }
                        if (strNormalName.equals(TvContractCompat.PARAM_INPUT)) {
                            if (!startTagAsStartTag.hasAttributes() || !startTagAsStartTag.attributes.get("type").equalsIgnoreCase("hidden")) {
                                return anythingElse(token, htmlTreeBuilder);
                            }
                            htmlTreeBuilder.insertEmptyElementFor(startTagAsStartTag);
                        } else if (strNormalName.equals("form")) {
                            htmlTreeBuilder.error(this);
                            if (htmlTreeBuilder.getFormElement() != null || htmlTreeBuilder.onStack("template")) {
                                return false;
                            }
                            htmlTreeBuilder.insertFormElement(startTagAsStartTag, false, false);
                        } else {
                            return anythingElse(token, htmlTreeBuilder);
                        }
                    }
                }
                return true;
            }
            if (token.isEndTag()) {
                String strNormalName2 = token.asEndTag().normalName();
                if (strNormalName2.equals("table")) {
                    if (!htmlTreeBuilder.inTableScope(strNormalName2)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.popStackToClose("table");
                    htmlTreeBuilder.resetInsertionMode();
                } else {
                    if (StringUtil.inSorted(strNormalName2, Constants.InTableEndErr)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    if (strNormalName2.equals("template")) {
                        htmlTreeBuilder.process(token, InHead);
                    } else {
                        return anythingElse(token, htmlTreeBuilder);
                    }
                }
                return true;
            }
            if (token.isEOF()) {
                if (htmlTreeBuilder.currentElementIs("html")) {
                    htmlTreeBuilder.error(this);
                }
                return true;
            }
            return anythingElse(token, htmlTreeBuilder);
        }

        boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.error(this);
            htmlTreeBuilder.setFosterInserts(true);
            htmlTreeBuilder.process(token, InBody);
            htmlTreeBuilder.setFosterInserts(false);
            return true;
        }
    },
    InTableText { // from class: org.jsoup.parser.HtmlTreeBuilderState.10
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.type == Token.TokenType.Character) {
                htmlTreeBuilder.addPendingTableCharacters(token.asCharacter());
                return true;
            }
            if (htmlTreeBuilder.getPendingTableCharacters().size() > 0) {
                Token token2 = htmlTreeBuilder.currentToken;
                for (Token.Character character : htmlTreeBuilder.getPendingTableCharacters()) {
                    htmlTreeBuilder.currentToken = character;
                    if (!HtmlTreeBuilderState.isWhitespace(character)) {
                        htmlTreeBuilder.error(this);
                        if (StringUtil.inSorted(htmlTreeBuilder.currentElement().normalName(), Constants.InTableFoster)) {
                            htmlTreeBuilder.setFosterInserts(true);
                            htmlTreeBuilder.process(character, InBody);
                            htmlTreeBuilder.setFosterInserts(false);
                        } else {
                            htmlTreeBuilder.process(character, InBody);
                        }
                    } else {
                        htmlTreeBuilder.insertCharacterNode(character);
                    }
                }
                htmlTreeBuilder.currentToken = token2;
                htmlTreeBuilder.resetPendingTableCharacters();
            }
            htmlTreeBuilder.transition(htmlTreeBuilder.originalState());
            return htmlTreeBuilder.process(token);
        }
    },
    InCaption { // from class: org.jsoup.parser.HtmlTreeBuilderState.11
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isEndTag() && token.asEndTag().normalName().equals(MediaTrack.ROLE_CAPTION)) {
                if (!htmlTreeBuilder.inTableScope(MediaTrack.ROLE_CAPTION)) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                htmlTreeBuilder.generateImpliedEndTags();
                if (!htmlTreeBuilder.currentElementIs(MediaTrack.ROLE_CAPTION)) {
                    htmlTreeBuilder.error(this);
                }
                htmlTreeBuilder.popStackToClose(MediaTrack.ROLE_CAPTION);
                htmlTreeBuilder.clearFormattingElementsToLastMarker();
                htmlTreeBuilder.transition(InTable);
                return true;
            }
            if ((token.isStartTag() && StringUtil.inSorted(token.asStartTag().normalName(), Constants.InCellCol)) || (token.isEndTag() && token.asEndTag().normalName().equals("table"))) {
                if (!htmlTreeBuilder.inTableScope(MediaTrack.ROLE_CAPTION)) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                htmlTreeBuilder.generateImpliedEndTags(false);
                if (!htmlTreeBuilder.currentElementIs(MediaTrack.ROLE_CAPTION)) {
                    htmlTreeBuilder.error(this);
                }
                htmlTreeBuilder.popStackToClose(MediaTrack.ROLE_CAPTION);
                htmlTreeBuilder.clearFormattingElementsToLastMarker();
                htmlTreeBuilder.transition(InTable);
                InTable.process(token, htmlTreeBuilder);
                return true;
            }
            if (token.isEndTag() && StringUtil.inSorted(token.asEndTag().normalName(), Constants.InCaptionIgnore)) {
                htmlTreeBuilder.error(this);
                return false;
            }
            return htmlTreeBuilder.process(token, InBody);
        }
    },
    InColumnGroup { // from class: org.jsoup.parser.HtmlTreeBuilderState.12
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insertCharacterNode(token.asCharacter());
                return true;
            }
            int i = AnonymousClass25.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()];
            if (i != 1) {
                if (i == 2) {
                    htmlTreeBuilder.error(this);
                } else if (i == 3) {
                    Token.StartTag startTagAsStartTag = token.asStartTag();
                    String strNormalName = startTagAsStartTag.normalName();
                    strNormalName.hashCode();
                    switch (strNormalName) {
                        case "template":
                            htmlTreeBuilder.process(token, InHead);
                            break;
                        case "col":
                            htmlTreeBuilder.insertEmptyElementFor(startTagAsStartTag);
                            break;
                        case "html":
                            return htmlTreeBuilder.process(token, InBody);
                        default:
                            return anythingElse(token, htmlTreeBuilder);
                    }
                } else {
                    if (i != 4) {
                        if (i == 6) {
                            if (htmlTreeBuilder.currentElementIs("html")) {
                                return true;
                            }
                            return anythingElse(token, htmlTreeBuilder);
                        }
                        return anythingElse(token, htmlTreeBuilder);
                    }
                    String strNormalName2 = token.asEndTag().normalName();
                    strNormalName2.hashCode();
                    if (strNormalName2.equals("template")) {
                        htmlTreeBuilder.process(token, InHead);
                    } else if (strNormalName2.equals("colgroup")) {
                        if (!htmlTreeBuilder.currentElementIs(strNormalName2)) {
                            htmlTreeBuilder.error(this);
                            return false;
                        }
                        htmlTreeBuilder.pop();
                        htmlTreeBuilder.transition(InTable);
                    } else {
                        return anythingElse(token, htmlTreeBuilder);
                    }
                }
            } else {
                htmlTreeBuilder.insertCommentNode(token.asComment());
            }
            return true;
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (!htmlTreeBuilder.currentElementIs("colgroup")) {
                htmlTreeBuilder.error(this);
                return false;
            }
            htmlTreeBuilder.pop();
            htmlTreeBuilder.transition(InTable);
            htmlTreeBuilder.process(token);
            return true;
        }
    },
    InTableBody { // from class: org.jsoup.parser.HtmlTreeBuilderState.13
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            int i = AnonymousClass25.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()];
            if (i == 3) {
                Token.StartTag startTagAsStartTag = token.asStartTag();
                String strNormalName = startTagAsStartTag.normalName();
                if (strNormalName.equals("tr")) {
                    htmlTreeBuilder.clearStackToTableBodyContext();
                    htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                    htmlTreeBuilder.transition(InRow);
                    return true;
                }
                if (StringUtil.inSorted(strNormalName, Constants.InCellNames)) {
                    htmlTreeBuilder.error(this);
                    htmlTreeBuilder.processStartTag("tr");
                    return htmlTreeBuilder.process(startTagAsStartTag);
                }
                if (StringUtil.inSorted(strNormalName, Constants.InTableBodyExit)) {
                    return exitTableBody(token, htmlTreeBuilder);
                }
                return anythingElse(token, htmlTreeBuilder);
            }
            if (i == 4) {
                String strNormalName2 = token.asEndTag().normalName();
                if (StringUtil.inSorted(strNormalName2, Constants.InTableEndIgnore)) {
                    if (!htmlTreeBuilder.inTableScope(strNormalName2)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.clearStackToTableBodyContext();
                    htmlTreeBuilder.pop();
                    htmlTreeBuilder.transition(InTable);
                    return true;
                }
                if (strNormalName2.equals("table")) {
                    return exitTableBody(token, htmlTreeBuilder);
                }
                if (StringUtil.inSorted(strNormalName2, Constants.InTableBodyEndIgnore)) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                return anythingElse(token, htmlTreeBuilder);
            }
            return anythingElse(token, htmlTreeBuilder);
        }

        private boolean exitTableBody(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (!htmlTreeBuilder.inTableScope("tbody") && !htmlTreeBuilder.inTableScope("thead") && !htmlTreeBuilder.inScope("tfoot")) {
                htmlTreeBuilder.error(this);
                return false;
            }
            htmlTreeBuilder.clearStackToTableBodyContext();
            htmlTreeBuilder.processEndTag(htmlTreeBuilder.currentElement().normalName());
            return htmlTreeBuilder.process(token);
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            return htmlTreeBuilder.process(token, InTable);
        }
    },
    InRow { // from class: org.jsoup.parser.HtmlTreeBuilderState.14
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isStartTag()) {
                Token.StartTag startTagAsStartTag = token.asStartTag();
                String strNormalName = startTagAsStartTag.normalName();
                if (StringUtil.inSorted(strNormalName, Constants.InCellNames)) {
                    htmlTreeBuilder.clearStackToTableRowContext();
                    htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                    htmlTreeBuilder.transition(InCell);
                    htmlTreeBuilder.insertMarkerToFormattingElements();
                    return true;
                }
                if (StringUtil.inSorted(strNormalName, Constants.InRowMissing)) {
                    if (!htmlTreeBuilder.inTableScope("tr")) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.clearStackToTableRowContext();
                    htmlTreeBuilder.pop();
                    htmlTreeBuilder.transition(InTableBody);
                    return htmlTreeBuilder.process(token);
                }
                return anythingElse(token, htmlTreeBuilder);
            }
            if (token.isEndTag()) {
                String strNormalName2 = token.asEndTag().normalName();
                if (strNormalName2.equals("tr")) {
                    if (!htmlTreeBuilder.inTableScope(strNormalName2)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.clearStackToTableRowContext();
                    htmlTreeBuilder.pop();
                    htmlTreeBuilder.transition(InTableBody);
                    return true;
                }
                if (strNormalName2.equals("table")) {
                    if (!htmlTreeBuilder.inTableScope("tr")) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.clearStackToTableRowContext();
                    htmlTreeBuilder.pop();
                    htmlTreeBuilder.transition(InTableBody);
                    return htmlTreeBuilder.process(token);
                }
                if (StringUtil.inSorted(strNormalName2, Constants.InTableToBody)) {
                    if (!htmlTreeBuilder.inTableScope(strNormalName2)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    if (!htmlTreeBuilder.inTableScope("tr")) {
                        return false;
                    }
                    htmlTreeBuilder.clearStackToTableRowContext();
                    htmlTreeBuilder.pop();
                    htmlTreeBuilder.transition(InTableBody);
                    return htmlTreeBuilder.process(token);
                }
                if (StringUtil.inSorted(strNormalName2, Constants.InRowIgnore)) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                return anythingElse(token, htmlTreeBuilder);
            }
            return anythingElse(token, htmlTreeBuilder);
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            return htmlTreeBuilder.process(token, InTable);
        }
    },
    InCell { // from class: org.jsoup.parser.HtmlTreeBuilderState.15
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isEndTag()) {
                String strNormalName = token.asEndTag().normalName();
                if (StringUtil.inSorted(strNormalName, Constants.InCellNames)) {
                    if (!htmlTreeBuilder.inTableScope(strNormalName)) {
                        htmlTreeBuilder.error(this);
                        htmlTreeBuilder.transition(InRow);
                        return false;
                    }
                    htmlTreeBuilder.generateImpliedEndTags();
                    if (!htmlTreeBuilder.currentElementIs(strNormalName)) {
                        htmlTreeBuilder.error(this);
                    }
                    htmlTreeBuilder.popStackToClose(strNormalName);
                    htmlTreeBuilder.clearFormattingElementsToLastMarker();
                    htmlTreeBuilder.transition(InRow);
                    return true;
                }
                if (StringUtil.inSorted(strNormalName, Constants.InCellBody)) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                if (StringUtil.inSorted(strNormalName, Constants.InCellTable)) {
                    if (!htmlTreeBuilder.inTableScope(strNormalName)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    closeCell(htmlTreeBuilder);
                    return htmlTreeBuilder.process(token);
                }
                return anythingElse(token, htmlTreeBuilder);
            }
            if (token.isStartTag() && StringUtil.inSorted(token.asStartTag().normalName(), Constants.InCellCol)) {
                if (!htmlTreeBuilder.inTableScope("td") && !htmlTreeBuilder.inTableScope("th")) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                closeCell(htmlTreeBuilder);
                return htmlTreeBuilder.process(token);
            }
            return anythingElse(token, htmlTreeBuilder);
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            return htmlTreeBuilder.process(token, InBody);
        }

        private void closeCell(HtmlTreeBuilder htmlTreeBuilder) {
            if (htmlTreeBuilder.inTableScope("td")) {
                htmlTreeBuilder.processEndTag("td");
            } else {
                htmlTreeBuilder.processEndTag("th");
            }
        }
    },
    InSelect { // from class: org.jsoup.parser.HtmlTreeBuilderState.16
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            switch (AnonymousClass25.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()]) {
                case 1:
                    htmlTreeBuilder.insertCommentNode(token.asComment());
                    return true;
                case 2:
                    htmlTreeBuilder.error(this);
                    return false;
                case 3:
                    Token.StartTag startTagAsStartTag = token.asStartTag();
                    String strNormalName = startTagAsStartTag.normalName();
                    if (strNormalName.equals("html")) {
                        return htmlTreeBuilder.process(startTagAsStartTag, InBody);
                    }
                    if (strNormalName.equals("option")) {
                        if (htmlTreeBuilder.currentElementIs("option")) {
                            htmlTreeBuilder.processEndTag("option");
                        }
                        htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                    } else if (strNormalName.equals("optgroup")) {
                        if (htmlTreeBuilder.currentElementIs("option")) {
                            htmlTreeBuilder.processEndTag("option");
                        }
                        if (htmlTreeBuilder.currentElementIs("optgroup")) {
                            htmlTreeBuilder.processEndTag("optgroup");
                        }
                        htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                    } else {
                        if (strNormalName.equals("select")) {
                            htmlTreeBuilder.error(this);
                            return htmlTreeBuilder.processEndTag("select");
                        }
                        if (StringUtil.inSorted(strNormalName, Constants.InSelectEnd)) {
                            htmlTreeBuilder.error(this);
                            if (!htmlTreeBuilder.inSelectScope("select")) {
                                return false;
                            }
                            do {
                                htmlTreeBuilder.popStackToClose("select");
                                htmlTreeBuilder.resetInsertionMode();
                            } while (htmlTreeBuilder.inSelectScope("select"));
                            return htmlTreeBuilder.process(startTagAsStartTag);
                        }
                        if (strNormalName.equals("script") || strNormalName.equals("template")) {
                            return htmlTreeBuilder.process(token, InHead);
                        }
                        return anythingElse(token, htmlTreeBuilder);
                    }
                    return true;
                case 4:
                    String strNormalName2 = token.asEndTag().normalName();
                    strNormalName2.hashCode();
                    switch (strNormalName2) {
                        case "template":
                            return htmlTreeBuilder.process(token, InHead);
                        case "option":
                            if (htmlTreeBuilder.currentElementIs("option")) {
                                htmlTreeBuilder.pop();
                            } else {
                                htmlTreeBuilder.error(this);
                            }
                            return true;
                        case "select":
                            if (!htmlTreeBuilder.inSelectScope(strNormalName2)) {
                                htmlTreeBuilder.error(this);
                                return false;
                            }
                            htmlTreeBuilder.popStackToClose(strNormalName2);
                            htmlTreeBuilder.resetInsertionMode();
                            return true;
                        case "optgroup":
                            if (htmlTreeBuilder.currentElementIs("option") && htmlTreeBuilder.aboveOnStack(htmlTreeBuilder.currentElement()) != null && htmlTreeBuilder.aboveOnStack(htmlTreeBuilder.currentElement()).nameIs("optgroup")) {
                                htmlTreeBuilder.processEndTag("option");
                            }
                            if (htmlTreeBuilder.currentElementIs("optgroup")) {
                                htmlTreeBuilder.pop();
                            } else {
                                htmlTreeBuilder.error(this);
                            }
                            return true;
                        default:
                            return anythingElse(token, htmlTreeBuilder);
                    }
                case 5:
                    htmlTreeBuilder.insertCharacterNode(token.asCharacter());
                    return true;
                case 6:
                    if (!htmlTreeBuilder.currentElementIs("html")) {
                        htmlTreeBuilder.error(this);
                    }
                    return true;
                default:
                    return anythingElse(token, htmlTreeBuilder);
            }
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.error(this);
            return false;
        }
    },
    InSelectInTable { // from class: org.jsoup.parser.HtmlTreeBuilderState.17
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isStartTag() && StringUtil.inSorted(token.asStartTag().normalName(), Constants.InSelectTableEnd)) {
                htmlTreeBuilder.error(this);
                htmlTreeBuilder.popStackToClose("select");
                htmlTreeBuilder.resetInsertionMode();
                return htmlTreeBuilder.process(token);
            }
            if (token.isEndTag() && StringUtil.inSorted(token.asEndTag().normalName(), Constants.InSelectTableEnd)) {
                htmlTreeBuilder.error(this);
                if (!htmlTreeBuilder.inTableScope(token.asEndTag().normalName())) {
                    return false;
                }
                htmlTreeBuilder.popStackToClose("select");
                htmlTreeBuilder.resetInsertionMode();
                return htmlTreeBuilder.process(token);
            }
            return htmlTreeBuilder.process(token, InSelect);
        }
    },
    InTemplate { // from class: org.jsoup.parser.HtmlTreeBuilderState.18
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            switch (AnonymousClass25.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()]) {
                case 1:
                case 2:
                case 5:
                    htmlTreeBuilder.process(token, InBody);
                    return true;
                case 3:
                    String strNormalName = token.asStartTag().normalName();
                    if (StringUtil.inSorted(strNormalName, Constants.InTemplateToHead)) {
                        htmlTreeBuilder.process(token, InHead);
                        return true;
                    }
                    if (StringUtil.inSorted(strNormalName, Constants.InTemplateToTable)) {
                        htmlTreeBuilder.popTemplateMode();
                        htmlTreeBuilder.pushTemplateMode(InTable);
                        htmlTreeBuilder.transition(InTable);
                        return htmlTreeBuilder.process(token);
                    }
                    if (strNormalName.equals("col")) {
                        htmlTreeBuilder.popTemplateMode();
                        htmlTreeBuilder.pushTemplateMode(InColumnGroup);
                        htmlTreeBuilder.transition(InColumnGroup);
                        return htmlTreeBuilder.process(token);
                    }
                    if (strNormalName.equals("tr")) {
                        htmlTreeBuilder.popTemplateMode();
                        htmlTreeBuilder.pushTemplateMode(InTableBody);
                        htmlTreeBuilder.transition(InTableBody);
                        return htmlTreeBuilder.process(token);
                    }
                    if (strNormalName.equals("td") || strNormalName.equals("th")) {
                        htmlTreeBuilder.popTemplateMode();
                        htmlTreeBuilder.pushTemplateMode(InRow);
                        htmlTreeBuilder.transition(InRow);
                        return htmlTreeBuilder.process(token);
                    }
                    htmlTreeBuilder.popTemplateMode();
                    htmlTreeBuilder.pushTemplateMode(InBody);
                    htmlTreeBuilder.transition(InBody);
                    return htmlTreeBuilder.process(token);
                case 4:
                    if (token.asEndTag().normalName().equals("template")) {
                        htmlTreeBuilder.process(token, InHead);
                        return true;
                    }
                    htmlTreeBuilder.error(this);
                    return false;
                case 6:
                    if (!htmlTreeBuilder.onStack("template")) {
                        return true;
                    }
                    htmlTreeBuilder.error(this);
                    htmlTreeBuilder.popStackToClose("template");
                    htmlTreeBuilder.clearFormattingElementsToLastMarker();
                    htmlTreeBuilder.popTemplateMode();
                    htmlTreeBuilder.resetInsertionMode();
                    if (htmlTreeBuilder.state() == InTemplate || htmlTreeBuilder.templateModeSize() >= 12) {
                        return true;
                    }
                    return htmlTreeBuilder.process(token);
                default:
                    Validate.wtf("Unexpected state: " + token.type);
                    return true;
            }
        }
    },
    AfterBody { // from class: org.jsoup.parser.HtmlTreeBuilderState.19
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            Element fromStack = htmlTreeBuilder.getFromStack("html");
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                if (fromStack != null) {
                    htmlTreeBuilder.insertCharacterToElement(token.asCharacter(), fromStack);
                    return true;
                }
                htmlTreeBuilder.process(token, InBody);
                return true;
            }
            if (token.isComment()) {
                htmlTreeBuilder.insertCommentNode(token.asComment());
                return true;
            }
            if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            }
            if (token.isStartTag() && token.asStartTag().normalName().equals("html")) {
                return htmlTreeBuilder.process(token, InBody);
            }
            if (token.isEndTag() && token.asEndTag().normalName().equals("html")) {
                if (htmlTreeBuilder.isFragmentParsing()) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                if (fromStack != null) {
                    htmlTreeBuilder.trackNodePosition(fromStack, false);
                }
                htmlTreeBuilder.transition(AfterAfterBody);
                return true;
            }
            if (token.isEOF()) {
                return true;
            }
            htmlTreeBuilder.error(this);
            htmlTreeBuilder.resetBody();
            return htmlTreeBuilder.process(token);
        }
    },
    InFrameset { // from class: org.jsoup.parser.HtmlTreeBuilderState.20
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insertCharacterNode(token.asCharacter());
            } else if (token.isComment()) {
                htmlTreeBuilder.insertCommentNode(token.asComment());
            } else {
                if (token.isDoctype()) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                if (token.isStartTag()) {
                    Token.StartTag startTagAsStartTag = token.asStartTag();
                    String strNormalName = startTagAsStartTag.normalName();
                    strNormalName.hashCode();
                    switch (strNormalName) {
                        case "frameset":
                            htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                            break;
                        case "html":
                            return htmlTreeBuilder.process(startTagAsStartTag, InBody);
                        case "frame":
                            htmlTreeBuilder.insertEmptyElementFor(startTagAsStartTag);
                            break;
                        case "noframes":
                            return htmlTreeBuilder.process(startTagAsStartTag, InHead);
                        default:
                            htmlTreeBuilder.error(this);
                            return false;
                    }
                } else if (token.isEndTag() && token.asEndTag().normalName().equals("frameset")) {
                    if (!htmlTreeBuilder.currentElementIs("frameset")) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.pop();
                    if (!htmlTreeBuilder.isFragmentParsing() && !htmlTreeBuilder.currentElementIs("frameset")) {
                        htmlTreeBuilder.transition(AfterFrameset);
                    }
                } else if (token.isEOF()) {
                    if (!htmlTreeBuilder.currentElementIs("html")) {
                        htmlTreeBuilder.error(this);
                    }
                } else {
                    htmlTreeBuilder.error(this);
                    return false;
                }
            }
            return true;
        }
    },
    AfterFrameset { // from class: org.jsoup.parser.HtmlTreeBuilderState.21
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insertCharacterNode(token.asCharacter());
                return true;
            }
            if (token.isComment()) {
                htmlTreeBuilder.insertCommentNode(token.asComment());
                return true;
            }
            if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            }
            if (token.isStartTag() && token.asStartTag().normalName().equals("html")) {
                return htmlTreeBuilder.process(token, InBody);
            }
            if (token.isEndTag() && token.asEndTag().normalName().equals("html")) {
                htmlTreeBuilder.transition(AfterAfterFrameset);
                return true;
            }
            if (token.isStartTag() && token.asStartTag().normalName().equals("noframes")) {
                return htmlTreeBuilder.process(token, InHead);
            }
            if (token.isEOF()) {
                return true;
            }
            htmlTreeBuilder.error(this);
            return false;
        }
    },
    AfterAfterBody { // from class: org.jsoup.parser.HtmlTreeBuilderState.22
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isComment()) {
                htmlTreeBuilder.insertCommentNode(token.asComment());
                return true;
            }
            if (!token.isDoctype() && (!token.isStartTag() || !token.asStartTag().normalName().equals("html"))) {
                if (HtmlTreeBuilderState.isWhitespace(token)) {
                    htmlTreeBuilder.insertCharacterToElement(token.asCharacter(), htmlTreeBuilder.getDocument());
                    return true;
                }
                if (token.isEOF()) {
                    return true;
                }
                htmlTreeBuilder.error(this);
                htmlTreeBuilder.resetBody();
                return htmlTreeBuilder.process(token);
            }
            return htmlTreeBuilder.process(token, InBody);
        }
    },
    AfterAfterFrameset { // from class: org.jsoup.parser.HtmlTreeBuilderState.23
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isComment()) {
                htmlTreeBuilder.insertCommentNode(token.asComment());
                return true;
            }
            if (token.isDoctype() || HtmlTreeBuilderState.isWhitespace(token) || (token.isStartTag() && token.asStartTag().normalName().equals("html"))) {
                return htmlTreeBuilder.process(token, InBody);
            }
            if (token.isEOF()) {
                return true;
            }
            if (token.isStartTag() && token.asStartTag().normalName().equals("noframes")) {
                return htmlTreeBuilder.process(token, InHead);
            }
            htmlTreeBuilder.error(this);
            return false;
        }
    },
    ForeignContent { // from class: org.jsoup.parser.HtmlTreeBuilderState.24
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            switch (AnonymousClass25.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()]) {
                case 1:
                    htmlTreeBuilder.insertCommentNode(token.asComment());
                    return true;
                case 2:
                    htmlTreeBuilder.error(this);
                    return true;
                case 3:
                    Token.StartTag startTagAsStartTag = token.asStartTag();
                    if (StringUtil.in(startTagAsStartTag.normalName, Constants.InForeignToHtml)) {
                        return processAsHtml(token, htmlTreeBuilder);
                    }
                    if (startTagAsStartTag.normalName.equals(ContentType.Font.TYPE) && (startTagAsStartTag.hasAttributeIgnoreCase("color") || startTagAsStartTag.hasAttributeIgnoreCase("face") || startTagAsStartTag.hasAttributeIgnoreCase(ContentDisposition.Parameters.Size))) {
                        return processAsHtml(token, htmlTreeBuilder);
                    }
                    String strNamespace = htmlTreeBuilder.currentElement().tag().namespace();
                    htmlTreeBuilder.insertForeignElementFor(startTagAsStartTag, strNamespace);
                    TokeniserState tokeniserStateTextState = htmlTreeBuilder.tagFor(startTagAsStartTag.tagName.value(), startTagAsStartTag.normalName, strNamespace, htmlTreeBuilder.settings).textState();
                    if (tokeniserStateTextState != null) {
                        if (startTagAsStartTag.normalName.equals("script")) {
                            htmlTreeBuilder.tokeniser.transition(TokeniserState.ScriptData);
                        } else {
                            htmlTreeBuilder.tokeniser.transition(tokeniserStateTextState);
                        }
                    }
                    return true;
                case 4:
                    Token.EndTag endTagAsEndTag = token.asEndTag();
                    if (endTagAsEndTag.normalName.equals("br") || endTagAsEndTag.normalName.equals(TtmlNode.TAG_P)) {
                        return processAsHtml(token, htmlTreeBuilder);
                    }
                    if (endTagAsEndTag.normalName.equals("script") && htmlTreeBuilder.currentElementIs("script", "http://www.w3.org/2000/svg")) {
                        htmlTreeBuilder.pop();
                        return true;
                    }
                    ArrayList<Element> stack = htmlTreeBuilder.getStack();
                    if (stack.isEmpty()) {
                        Validate.wtf("Stack unexpectedly empty");
                    }
                    int size = stack.size() - 1;
                    Element element = stack.get(size);
                    if (!element.nameIs(endTagAsEndTag.normalName)) {
                        htmlTreeBuilder.error(this);
                    }
                    while (size != 0) {
                        if (element.nameIs(endTagAsEndTag.normalName)) {
                            htmlTreeBuilder.popStackToCloseAnyNamespace(element.normalName());
                            return true;
                        }
                        size--;
                        element = stack.get(size);
                        if (element.tag().namespace().equals("http://www.w3.org/1999/xhtml")) {
                            return processAsHtml(token, htmlTreeBuilder);
                        }
                    }
                    return true;
                case 5:
                    Token.Character characterAsCharacter = token.asCharacter();
                    if (HtmlTreeBuilderState.isWhitespace(characterAsCharacter)) {
                        htmlTreeBuilder.insertCharacterNode(characterAsCharacter);
                    } else {
                        htmlTreeBuilder.insertCharacterNode(characterAsCharacter, true);
                        htmlTreeBuilder.framesetOk(false);
                    }
                    return true;
                case 6:
                    return true;
                default:
                    Validate.wtf("Unexpected state: " + token.type);
                    return true;
            }
        }

        boolean processAsHtml(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            return htmlTreeBuilder.state().process(token, htmlTreeBuilder);
        }
    };

    private static final String nullString = String.valueOf((char) 0);

    abstract boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder);

    /* JADX INFO: renamed from: org.jsoup.parser.HtmlTreeBuilderState$25, reason: invalid class name */
    static /* synthetic */ class AnonymousClass25 {
        static final /* synthetic */ int[] $SwitchMap$org$jsoup$parser$Token$TokenType;

        static {
            int[] iArr = new int[Token.TokenType.values().length];
            $SwitchMap$org$jsoup$parser$Token$TokenType = iArr;
            try {
                iArr[Token.TokenType.Comment.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jsoup$parser$Token$TokenType[Token.TokenType.Doctype.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jsoup$parser$Token$TokenType[Token.TokenType.StartTag.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$jsoup$parser$Token$TokenType[Token.TokenType.EndTag.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$jsoup$parser$Token$TokenType[Token.TokenType.Character.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$jsoup$parser$Token$TokenType[Token.TokenType.EOF.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void mergeAttributes(Token.StartTag startTag, Element element) {
        if (startTag.hasAttributes()) {
            for (Attribute attribute : startTag.attributes) {
                Attributes attributes = element.attributes();
                if (!attributes.hasKey(attribute.getKey())) {
                    Range.AttributeRange attributeRangeSourceRange = attribute.sourceRange();
                    attributes.put(attribute);
                    if (startTag.trackSource) {
                        attributes.sourceRange(attribute.getKey(), attributeRangeSourceRange);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isWhitespace(Token token) {
        if (token.isCharacter()) {
            return StringUtil.isBlank(token.asCharacter().getData());
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void HandleTextState(Token.StartTag startTag, HtmlTreeBuilder htmlTreeBuilder, TokeniserState tokeniserState) {
        if (tokeniserState != null) {
            htmlTreeBuilder.tokeniser.transition(tokeniserState);
        }
        htmlTreeBuilder.markInsertionMode();
        htmlTreeBuilder.transition(Text);
        htmlTreeBuilder.insertElementFor(startTag);
    }

    static final class Constants {
        static final String[] InHeadEmpty = {TtmlNode.RUBY_BASE, "basefont", "bgsound", "command", "link"};
        static final String[] InHeadRaw = {"noframes", "style"};
        static final String[] InHeadEnd = {TtmlNode.TAG_BODY, "br", "html"};
        static final String[] AfterHeadBody = {TtmlNode.TAG_BODY, "br", "html"};
        static final String[] BeforeHtmlToHead = {TtmlNode.TAG_BODY, "br", TtmlNode.TAG_HEAD, "html"};
        static final String[] InHeadNoScriptHead = {"basefont", "bgsound", "link", "meta", "noframes", "style"};
        static final String[] InBodyStartToHead = {TtmlNode.RUBY_BASE, "basefont", "bgsound", "command", "link", "meta", "noframes", "script", "style", "template", "title"};
        static final String[] InBodyStartPClosers = {"address", "article", "aside", "blockquote", TtmlNode.CENTER, "details", "dir", TtmlNode.TAG_DIV, CmcdConfiguration.KEY_DEADLINE, "fieldset", "figcaption", "figure", "footer", "header", "hgroup", "menu", "nav", "ol", TtmlNode.TAG_P, "section", "summary", "ul"};
        static final String[] Headings = {"h1", "h2", "h3", "h4", "h5", "h6"};
        static final String[] InBodyStartLiBreakers = {"address", TtmlNode.TAG_DIV, TtmlNode.TAG_P};
        static final String[] DdDt = {"dd", "dt"};
        static final String[] InBodyStartApplets = {"applet", "marquee", "object"};
        static final String[] InBodyStartMedia = {"param", "source", "track"};
        static final String[] InBodyStartInputAttribs = {"action", "name", "prompt"};
        static final String[] InBodyStartDrop = {MediaTrack.ROLE_CAPTION, "col", "colgroup", TypedValues.AttributesType.S_FRAME, TtmlNode.TAG_HEAD, "tbody", "td", "tfoot", "th", "thead", "tr"};
        static final String[] InBodyEndClosers = {"address", "article", "aside", "blockquote", "button", TtmlNode.CENTER, "details", "dir", TtmlNode.TAG_DIV, CmcdConfiguration.KEY_DEADLINE, "fieldset", "figcaption", "figure", "footer", "header", "hgroup", "listing", "menu", "nav", "ol", "pre", "section", "summary", "ul"};
        static final String[] InBodyEndOtherErrors = {TtmlNode.TAG_BODY, "dd", "dt", "html", "li", "optgroup", "option", TtmlNode.TAG_P, "rb", "rp", "rt", "rtc", "tbody", "td", "tfoot", "th", "thead", "tr"};
        static final String[] InBodyEndAdoptionFormatters = {CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "big", "code", "em", ContentType.Font.TYPE, CmcdData.OBJECT_TYPE_INIT_SEGMENT, "nobr", CmcdData.STREAMING_FORMAT_SS, "small", "strike", "strong", TtmlNode.TAG_TT, "u"};
        static final String[] InTableToBody = {"tbody", "tfoot", "thead"};
        static final String[] InTableAddBody = {"td", "th", "tr"};
        static final String[] InTableToHead = {"script", "style", "template"};
        static final String[] InCellNames = {"td", "th"};
        static final String[] InCellBody = {TtmlNode.TAG_BODY, MediaTrack.ROLE_CAPTION, "col", "colgroup", "html"};
        static final String[] InCellTable = {"table", "tbody", "tfoot", "thead", "tr"};
        static final String[] InCellCol = {MediaTrack.ROLE_CAPTION, "col", "colgroup", "tbody", "td", "tfoot", "th", "thead", "tr"};
        static final String[] InTableEndErr = {TtmlNode.TAG_BODY, MediaTrack.ROLE_CAPTION, "col", "colgroup", "html", "tbody", "td", "tfoot", "th", "thead", "tr"};
        static final String[] InTableFoster = {"table", "tbody", "tfoot", "thead", "tr"};
        static final String[] InTableBodyExit = {MediaTrack.ROLE_CAPTION, "col", "colgroup", "tbody", "tfoot", "thead"};
        static final String[] InTableBodyEndIgnore = {TtmlNode.TAG_BODY, MediaTrack.ROLE_CAPTION, "col", "colgroup", "html", "td", "th", "tr"};
        static final String[] InRowMissing = {MediaTrack.ROLE_CAPTION, "col", "colgroup", "tbody", "tfoot", "thead", "tr"};
        static final String[] InRowIgnore = {TtmlNode.TAG_BODY, MediaTrack.ROLE_CAPTION, "col", "colgroup", "html", "td", "th"};
        static final String[] InSelectEnd = {TvContractCompat.PARAM_INPUT, "keygen", "textarea"};
        static final String[] InSelectTableEnd = {MediaTrack.ROLE_CAPTION, "table", "tbody", "td", "tfoot", "th", "thead", "tr"};
        static final String[] InTableEndIgnore = {"tbody", "tfoot", "thead"};
        static final String[] InHeadNoscriptIgnore = {TtmlNode.TAG_HEAD, "noscript"};
        static final String[] InCaptionIgnore = {TtmlNode.TAG_BODY, "col", "colgroup", "html", "tbody", "td", "tfoot", "th", "thead", "tr"};
        static final String[] InTemplateToHead = {TtmlNode.RUBY_BASE, "basefont", "bgsound", "link", "meta", "noframes", "script", "style", "template", "title"};
        static final String[] InTemplateToTable = {MediaTrack.ROLE_CAPTION, "colgroup", "tbody", "tfoot", "thead"};
        static final String[] InForeignToHtml = {"b", "big", "blockquote", TtmlNode.TAG_BODY, "br", TtmlNode.CENTER, "code", "dd", TtmlNode.TAG_DIV, CmcdConfiguration.KEY_DEADLINE, "dt", "em", "embed", "h1", "h2", "h3", "h4", "h5", "h6", TtmlNode.TAG_HEAD, "hr", CmcdData.OBJECT_TYPE_INIT_SEGMENT, "img", "li", "listing", "menu", "meta", "nobr", "ol", TtmlNode.TAG_P, "pre", TtmlNode.ATTR_TTS_RUBY, CmcdData.STREAMING_FORMAT_SS, "small", TtmlNode.TAG_SPAN, "strike", "strong", "sub", "sup", "table", TtmlNode.TAG_TT, "u", "ul", "var"};

        Constants() {
        }
    }
}
