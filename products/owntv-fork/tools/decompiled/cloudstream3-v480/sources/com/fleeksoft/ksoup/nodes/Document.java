package com.fleeksoft.ksoup.nodes;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.discord.panels.BuildConfig;
import com.fleeksoft.charset.Charsets;
import com.fleeksoft.ksoup.helper.Validate;
import com.fleeksoft.ksoup.internal.StringUtil;
import com.fleeksoft.ksoup.parser.ParseSettings;
import com.fleeksoft.ksoup.parser.Parser;
import com.fleeksoft.ksoup.parser.Tag;
import com.fleeksoft.ksoup.ported.KCloneable;
import com.fleeksoft.ksoup.select.Evaluator;
import io.ktor.http.auth.HttpAuthHeader;
import java.nio.charset.Charset;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Document.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 .2\u00020\u0001:\u0003,-.B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bB\u0013\b\u0017\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\nJ\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003J\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\b\u0010\u0011\u001a\u00020\u0001H\u0002J\u0006\u0010\u0012\u001a\u00020\u0001J\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001J\u0006\u0010\u0014\u001a\u00020\u0001J\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0019\u001a\u00020\u0003J\u0006\u0010\u001a\u001a\u00020\u0003J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001a\u001a\u00020\u0003H\u0007J\u000e\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0003J\b\u0010\u001e\u001a\u00020\u0003H\u0016J\u0010\u0010\u001f\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020\u0003H\u0016J\b\u0010 \u001a\u00020\u0003H\u0016J\u0014\u0010!\u001a\u00020\u001b2\n\u0010!\u001a\u00060\"j\u0002`#H\u0007J\n\u0010!\u001a\u00060\"j\u0002`#J\b\u0010$\u001a\u00020\u0000H\u0016J\r\u0010%\u001a\u00020&H\u0010¢\u0006\u0002\b'J\b\u0010(\u001a\u00020\u0000H\u0016J\b\u0010)\u001a\u00020\u001bH\u0002J\b\u0010*\u001a\u00020+H\u0002J\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\r\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006H\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/Document;", "Lcom/fleeksoft/ksoup/nodes/Element;", "namespace", "", "location", "parser", "Lcom/fleeksoft/ksoup/parser/Parser;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/fleeksoft/ksoup/parser/Parser;)V", "baseUri", "(Ljava/lang/String;)V", "outputSettings", "Lcom/fleeksoft/ksoup/nodes/Document$OutputSettings;", "quirksMode", "Lcom/fleeksoft/ksoup/nodes/Document$QuirksMode;", "documentType", "Lcom/fleeksoft/ksoup/nodes/DocumentType;", "htmlEl", TtmlNode.TAG_HEAD, "headOrNull", TtmlNode.TAG_BODY, "forms", "", "Lcom/fleeksoft/ksoup/nodes/FormElement;", "expectForm", "cssQuery", "title", "", "createElement", "tagName", "outerHtml", "text", "nodeName", HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "Lcom/fleeksoft/charset/Charset;", "clone", "createClone", "Lcom/fleeksoft/ksoup/nodes/Node;", "createClone$ksoup_release", "shallowClone", "ensureMetaCharsetElement", "ensureXmlDecl", "Lcom/fleeksoft/ksoup/nodes/XmlDeclaration;", "OutputSettings", "QuirksMode", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Document extends Element {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Evaluator titleEval = new Evaluator.Tag("title");
    private final String location;
    private final String namespace;
    private OutputSettings outputSettings;
    private Parser parser;
    private QuirksMode quirksMode;

    /* JADX INFO: compiled from: Document.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/Document$QuirksMode;", "", "<init>", "(Ljava/lang/String;I)V", "noQuirks", "quirks", "limitedQuirks", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public enum QuirksMode {
        noQuirks,
        quirks,
        limitedQuirks;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<QuirksMode> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: compiled from: Document.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[OutputSettings.Syntax.values().length];
            try {
                iArr[OutputSettings.Syntax.html.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[OutputSettings.Syntax.xml.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public /* synthetic */ Document(String str, String str2, Parser parser, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? Parser.INSTANCE.htmlParser() : parser);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Document(String namespace, String str, Parser parser) {
        super(new Tag("#root", namespace), str);
        Intrinsics.checkNotNullParameter(namespace, "namespace");
        Intrinsics.checkNotNullParameter(parser, "parser");
        this.namespace = namespace;
        this.location = str;
        this.parser = parser;
        this.outputSettings = new OutputSettings(null, null, false, false, 0, 0, null, 127, null);
        this.quirksMode = QuirksMode.noQuirks;
    }

    public Document(String str) {
        this("http://www.w3.org/1999/xhtml", str, null, 4, null);
    }

    /* JADX INFO: renamed from: location, reason: from getter */
    public final String getLocation() {
        return this.location;
    }

    public final DocumentType documentType() {
        for (Node node : getChildNodes()) {
            if (node instanceof DocumentType) {
                return (DocumentType) node;
            }
            if (!(node instanceof LeafNode)) {
                return null;
            }
        }
        return null;
    }

    private final Element htmlEl() {
        for (Element elementFirstElementChild = firstElementChild(); elementFirstElementChild != null; elementFirstElementChild = elementFirstElementChild.nextElementSibling()) {
            if (elementFirstElementChild.nameIs("html")) {
                return elementFirstElementChild;
            }
        }
        return Element.appendElement$default(this, "html", null, 2, null);
    }

    public final Element head() {
        Element elementHtmlEl = htmlEl();
        for (Element elementFirstElementChild = elementHtmlEl.firstElementChild(); elementFirstElementChild != null; elementFirstElementChild = elementFirstElementChild.nextElementSibling()) {
            if (elementFirstElementChild.nameIs(TtmlNode.TAG_HEAD)) {
                return elementFirstElementChild;
            }
        }
        return Element.prependElement$default(elementHtmlEl, TtmlNode.TAG_HEAD, null, 2, null);
    }

    public final Element headOrNull() {
        for (Element elementFirstElementChild = htmlEl().firstElementChild(); elementFirstElementChild != null; elementFirstElementChild = elementFirstElementChild.nextElementSibling()) {
            if (elementFirstElementChild.nameIs(TtmlNode.TAG_HEAD)) {
                return elementFirstElementChild;
            }
        }
        return null;
    }

    public final Element body() {
        Element elementHtmlEl = htmlEl();
        for (Element elementFirstElementChild = elementHtmlEl.firstElementChild(); elementFirstElementChild != null; elementFirstElementChild = elementFirstElementChild.nextElementSibling()) {
            if (elementFirstElementChild.nameIs(TtmlNode.TAG_BODY) || elementFirstElementChild.nameIs("frameset")) {
                return elementFirstElementChild;
            }
        }
        return Element.appendElement$default(elementHtmlEl, TtmlNode.TAG_BODY, null, 2, null);
    }

    public final List<FormElement> forms() {
        return select("form").forms();
    }

    public final FormElement expectForm(String cssQuery) {
        Intrinsics.checkNotNullParameter(cssQuery, "cssQuery");
        for (Element element : select(cssQuery)) {
            if (element instanceof FormElement) {
                return (FormElement) element;
            }
        }
        Validate.INSTANCE.fail("No form elements matched the query '" + cssQuery + "' in the document.");
        return null;
    }

    public final String title() {
        Element elementSelectFirst = head().selectFirst(titleEval);
        return elementSelectFirst != null ? StringsKt.trim((CharSequence) StringUtil.INSTANCE.normaliseWhitespace(elementSelectFirst.text())).toString() : "";
    }

    public final void title(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        Element elementSelectFirst = head().selectFirst(titleEval);
        if (elementSelectFirst == null) {
            elementSelectFirst = Element.appendElement$default(head(), "title", null, 2, null);
        }
        elementSelectFirst.text(title);
    }

    public final Element createElement(String tagName) {
        Intrinsics.checkNotNullParameter(tagName, "tagName");
        return new Element(this.parser.tagSet().valueOf(tagName, this.parser.defaultNamespace(), ParseSettings.INSTANCE.getPreserveCase()), Element.INSTANCE.searchUpForAttribute(this, Element.INSTANCE.getBaseUriKey()));
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public String outerHtml() {
        return super.html();
    }

    @Override // com.fleeksoft.ksoup.nodes.Element
    public Element text(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        body().text(text);
        return this;
    }

    @Override // com.fleeksoft.ksoup.nodes.Element, com.fleeksoft.ksoup.nodes.Node
    public String nodeName() {
        return "#document";
    }

    public final void charset(Charset charset) {
        Intrinsics.checkNotNullParameter(charset, "charset");
        this.outputSettings.charset(charset);
        ensureMetaCharsetElement();
    }

    public final Charset charset() {
        return this.outputSettings.charset();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fleeksoft.ksoup.nodes.Element, com.fleeksoft.ksoup.nodes.Node, com.fleeksoft.ksoup.ported.KCloneable
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public Node clone2() {
        Element elementClone2 = super.clone2();
        Intrinsics.checkNotNull(elementClone2, "null cannot be cast to non-null type com.fleeksoft.ksoup.nodes.Document");
        Document document = (Document) elementClone2;
        document.outputSettings = this.outputSettings.clone2();
        Attributes attributes$ksoup_release = getAttributes();
        document.setAttributes$ksoup_release(attributes$ksoup_release != null ? attributes$ksoup_release.clone2() : null);
        return document;
    }

    @Override // com.fleeksoft.ksoup.nodes.Element, com.fleeksoft.ksoup.nodes.Node
    public Node createClone$ksoup_release() {
        Document document = new Document(this.namespace, this.location, null, 4, null);
        document.outputSettings = this.outputSettings;
        document.parser = this.parser;
        document.setAttributes$ksoup_release(getAttributes());
        document.setChildNodes(getChildNodes());
        document.setTag(getTag());
        document.setBaseUri(baseUri());
        return document;
    }

    @Override // com.fleeksoft.ksoup.nodes.Element, com.fleeksoft.ksoup.nodes.Node
    public Document shallowClone() {
        Document document = new Document(tag().namespace(), baseUri(), this.parser);
        Attributes attributes$ksoup_release = getAttributes();
        document.setAttributes$ksoup_release(attributes$ksoup_release != null ? attributes$ksoup_release.clone2() : null);
        document.outputSettings = this.outputSettings.clone2();
        return document;
    }

    private final void ensureMetaCharsetElement() {
        int i = WhenMappings.$EnumSwitchMapping$0[getOutputSettings().syntax().ordinal()];
        if (i != 1) {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            XmlDeclaration xmlDeclarationEnsureXmlDecl = ensureXmlDecl();
            xmlDeclarationEnsureXmlDecl.attr("version", BuildConfig.VERSION_NAME);
            xmlDeclarationEnsureXmlDecl.attr("encoding", charset().name());
            return;
        }
        Element elementSelectFirst = selectFirst("meta[charset]");
        if (elementSelectFirst != null) {
            elementSelectFirst.attr(HttpAuthHeader.Parameters.Charset, charset().name());
        } else {
            Element.appendElement$default(head(), "meta", null, 2, null).attr(HttpAuthHeader.Parameters.Charset, charset().name());
        }
        select("meta[name=charset]").remove();
    }

    private final XmlDeclaration ensureXmlDecl() {
        Node nodeFirstChild = firstChild();
        if (nodeFirstChild instanceof XmlDeclaration) {
            XmlDeclaration xmlDeclaration = (XmlDeclaration) nodeFirstChild;
            if (Intrinsics.areEqual(xmlDeclaration.name(), "xml")) {
                return xmlDeclaration;
            }
        }
        XmlDeclaration xmlDeclaration2 = new XmlDeclaration("xml", false);
        prependChild(xmlDeclaration2);
        return xmlDeclaration2;
    }

    /* JADX INFO: compiled from: Document.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\"BQ\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\f\b\u0002\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\u0006\u0010\u0002\u001a\u00020\u0003J\u0010\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003H\u0007J\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006J\u0014\u0010\u0004\u001a\u00020\u00002\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006H\u0007J\u0010\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0011H\u0007J\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\r\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\bH\u0007J\u0006\u0010\t\u001a\u00020\bJ\u0010\u0010\t\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\bH\u0007J\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010\n\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0006\u0010\f\u001a\u00020\u000bJ\u0010\u0010\f\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u000bH\u0007J\b\u0010\u0014\u001a\u00020\u0000H\u0016J\t\u0010\u0015\u001a\u00020\u0003HÂ\u0003J\r\u0010\u0016\u001a\u00060\u0005j\u0002`\u0006HÂ\u0003J\t\u0010\u0017\u001a\u00020\bHÂ\u0003J\t\u0010\u0018\u001a\u00020\bHÂ\u0003J\t\u0010\u0019\u001a\u00020\u000bHÂ\u0003J\t\u0010\u001a\u001a\u00020\u000bHÂ\u0003J\t\u0010\u001b\u001a\u00020\u000eHÂ\u0003JS\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\f\b\u0002\u0010\u0004\u001a\u00060\u0005j\u0002`\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000eHÆ\u0001J\u0014\u0010\u001d\u001a\u00020\b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0083\u0004J\n\u0010 \u001a\u00020\u000bHÖ\u0081\u0004J\n\u0010!\u001a\u00020\u0011HÖ\u0081\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/Document$OutputSettings;", "Lcom/fleeksoft/ksoup/ported/KCloneable;", "escapeMode", "Lcom/fleeksoft/ksoup/nodes/Entities$EscapeMode;", HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "Lcom/fleeksoft/charset/Charset;", "prettyPrint", "", "outline", "indentAmount", "", "maxPaddingWidth", "syntax", "Lcom/fleeksoft/ksoup/nodes/Document$OutputSettings$Syntax;", "<init>", "(Lcom/fleeksoft/ksoup/nodes/Entities$EscapeMode;Ljava/nio/charset/Charset;ZZIILcom/fleeksoft/ksoup/nodes/Document$OutputSettings$Syntax;)V", "", "pretty", "outlineMode", "clone", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "", "hashCode", "toString", "Syntax", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class OutputSettings implements KCloneable<OutputSettings> {
        private Charset charset;
        private Entities.EscapeMode escapeMode;
        private int indentAmount;
        private int maxPaddingWidth;
        private boolean outline;
        private boolean prettyPrint;
        private Syntax syntax;

        /* JADX INFO: compiled from: Document.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/Document$OutputSettings$Syntax;", "", "<init>", "(Ljava/lang/String;I)V", "html", "xml", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public enum Syntax {
            html,
            xml;

            private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

            public static EnumEntries<Syntax> getEntries() {
                return $ENTRIES;
            }
        }

        public OutputSettings() {
            this(null, null, false, false, 0, 0, null, 127, null);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        private final Entities.EscapeMode getEscapeMode() {
            return this.escapeMode;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        private final Charset getCharset() {
            return this.charset;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        private final boolean getPrettyPrint() {
            return this.prettyPrint;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        private final boolean getOutline() {
            return this.outline;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        private final int getIndentAmount() {
            return this.indentAmount;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        private final int getMaxPaddingWidth() {
            return this.maxPaddingWidth;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        private final Syntax getSyntax() {
            return this.syntax;
        }

        public static /* synthetic */ OutputSettings copy$default(OutputSettings outputSettings, Entities.EscapeMode escapeMode, Charset charset, boolean z, boolean z2, int i, int i2, Syntax syntax, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                escapeMode = outputSettings.escapeMode;
            }
            if ((i3 & 2) != 0) {
                charset = outputSettings.charset;
            }
            if ((i3 & 4) != 0) {
                z = outputSettings.prettyPrint;
            }
            if ((i3 & 8) != 0) {
                z2 = outputSettings.outline;
            }
            if ((i3 & 16) != 0) {
                i = outputSettings.indentAmount;
            }
            if ((i3 & 32) != 0) {
                i2 = outputSettings.maxPaddingWidth;
            }
            if ((i3 & 64) != 0) {
                syntax = outputSettings.syntax;
            }
            int i4 = i2;
            Syntax syntax2 = syntax;
            int i5 = i;
            boolean z3 = z;
            return outputSettings.copy(escapeMode, charset, z3, z2, i5, i4, syntax2);
        }

        public final OutputSettings copy(Entities.EscapeMode escapeMode, Charset charset, boolean prettyPrint, boolean outline, int indentAmount, int maxPaddingWidth, Syntax syntax) {
            Intrinsics.checkNotNullParameter(escapeMode, "escapeMode");
            Intrinsics.checkNotNullParameter(charset, "charset");
            Intrinsics.checkNotNullParameter(syntax, "syntax");
            return new OutputSettings(escapeMode, charset, prettyPrint, outline, indentAmount, maxPaddingWidth, syntax);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof OutputSettings)) {
                return false;
            }
            OutputSettings outputSettings = (OutputSettings) other;
            return this.escapeMode == outputSettings.escapeMode && Intrinsics.areEqual(this.charset, outputSettings.charset) && this.prettyPrint == outputSettings.prettyPrint && this.outline == outputSettings.outline && this.indentAmount == outputSettings.indentAmount && this.maxPaddingWidth == outputSettings.maxPaddingWidth && this.syntax == outputSettings.syntax;
        }

        public int hashCode() {
            return (((((((((((this.escapeMode.hashCode() * 31) + this.charset.hashCode()) * 31) + UByte$$ExternalSyntheticBackport0.m(this.prettyPrint)) * 31) + UByte$$ExternalSyntheticBackport0.m(this.outline)) * 31) + this.indentAmount) * 31) + this.maxPaddingWidth) * 31) + this.syntax.hashCode();
        }

        public String toString() {
            return "OutputSettings(escapeMode=" + this.escapeMode + ", charset=" + this.charset + ", prettyPrint=" + this.prettyPrint + ", outline=" + this.outline + ", indentAmount=" + this.indentAmount + ", maxPaddingWidth=" + this.maxPaddingWidth + ", syntax=" + this.syntax + ")";
        }

        public OutputSettings(Entities.EscapeMode escapeMode, Charset charset, boolean z, boolean z2, int i, int i2, Syntax syntax) {
            Intrinsics.checkNotNullParameter(escapeMode, "escapeMode");
            Intrinsics.checkNotNullParameter(charset, "charset");
            Intrinsics.checkNotNullParameter(syntax, "syntax");
            this.escapeMode = escapeMode;
            this.charset = charset;
            this.prettyPrint = z;
            this.outline = z2;
            this.indentAmount = i;
            this.maxPaddingWidth = i2;
            this.syntax = syntax;
        }

        public /* synthetic */ OutputSettings(Entities.EscapeMode escapeMode, Charset charset, boolean z, boolean z2, int i, int i2, Syntax syntax, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? Entities.EscapeMode.base : escapeMode, (i3 & 2) != 0 ? Charsets.INSTANCE.getUTF8() : charset, (i3 & 4) != 0 ? true : z, (i3 & 8) != 0 ? false : z2, (i3 & 16) != 0 ? 1 : i, (i3 & 32) != 0 ? 30 : i2, (i3 & 64) != 0 ? Syntax.html : syntax);
        }

        public final Entities.EscapeMode escapeMode() {
            return this.escapeMode;
        }

        public final OutputSettings escapeMode(Entities.EscapeMode escapeMode) {
            Intrinsics.checkNotNullParameter(escapeMode, "escapeMode");
            this.escapeMode = escapeMode;
            return this;
        }

        public final Charset charset() {
            return this.charset;
        }

        public final OutputSettings charset(Charset charset) {
            Intrinsics.checkNotNullParameter(charset, "charset");
            this.charset = charset;
            return this;
        }

        public final OutputSettings charset(String charset) {
            Intrinsics.checkNotNullParameter(charset, "charset");
            charset(Charsets.INSTANCE.forName(charset));
            return this;
        }

        public final Syntax syntax() {
            return this.syntax;
        }

        public final OutputSettings syntax(Syntax syntax) {
            Intrinsics.checkNotNullParameter(syntax, "syntax");
            this.syntax = syntax;
            if (syntax == Syntax.xml) {
                escapeMode(Entities.EscapeMode.xhtml);
            }
            return this;
        }

        public final boolean prettyPrint() {
            return this.prettyPrint;
        }

        public final OutputSettings prettyPrint(boolean pretty) {
            this.prettyPrint = pretty;
            return this;
        }

        public final boolean outline() {
            return this.outline;
        }

        public final OutputSettings outline(boolean outlineMode) {
            this.outline = outlineMode;
            return this;
        }

        public final int indentAmount() {
            return this.indentAmount;
        }

        public final OutputSettings indentAmount(int indentAmount) {
            Validate.INSTANCE.isTrue(indentAmount >= 0);
            this.indentAmount = indentAmount;
            return this;
        }

        public final int maxPaddingWidth() {
            return this.maxPaddingWidth;
        }

        public final OutputSettings maxPaddingWidth(int maxPaddingWidth) {
            Validate.INSTANCE.isTrue(maxPaddingWidth >= -1);
            this.maxPaddingWidth = maxPaddingWidth;
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.fleeksoft.ksoup.ported.KCloneable
        /* JADX INFO: renamed from: clone */
        public OutputSettings clone2() {
            return copy$default(this, null, null, false, false, 0, 0, null, 127, null);
        }
    }

    /* JADX INFO: renamed from: outputSettings, reason: from getter */
    public final OutputSettings getOutputSettings() {
        return this.outputSettings;
    }

    public final Document outputSettings(OutputSettings outputSettings) {
        Intrinsics.checkNotNullParameter(outputSettings, "outputSettings");
        this.outputSettings = outputSettings;
        return this;
    }

    /* JADX INFO: renamed from: quirksMode, reason: from getter */
    public final QuirksMode getQuirksMode() {
        return this.quirksMode;
    }

    public final Document quirksMode(QuirksMode quirksMode) {
        Intrinsics.checkNotNullParameter(quirksMode, "quirksMode");
        this.quirksMode = quirksMode;
        return this;
    }

    /* JADX INFO: renamed from: parser, reason: from getter */
    public final Parser getParser() {
        return this.parser;
    }

    public final Document parser(Parser parser) {
        Intrinsics.checkNotNullParameter(parser, "parser");
        this.parser = parser;
        return this;
    }

    /* JADX INFO: compiled from: Document.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/Document$Companion;", "", "<init>", "()V", "createShell", "Lcom/fleeksoft/ksoup/nodes/Document;", "baseUri", "", "titleEval", "Lcom/fleeksoft/ksoup/select/Evaluator;", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Document createShell(String baseUri) {
            Intrinsics.checkNotNullParameter(baseUri, "baseUri");
            Document document = new Document(baseUri);
            Element elementAppendElement$default = Element.appendElement$default(document, "html", null, 2, null);
            Element.appendElement$default(elementAppendElement$default, TtmlNode.TAG_HEAD, null, 2, null);
            Element.appendElement$default(elementAppendElement$default, TtmlNode.TAG_BODY, null, 2, null);
            return document;
        }
    }
}
