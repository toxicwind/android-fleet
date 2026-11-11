package com.fleeksoft.ksoup.parser;

import androidx.core.app.NotificationCompat;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.fleeksoft.ksoup.internal.SharedConstants;
import com.fleeksoft.ksoup.nodes.Attributes;
import com.fleeksoft.ksoup.nodes.Document;
import com.fleeksoft.ksoup.nodes.Element;
import com.fleeksoft.ksoup.nodes.Node;
import com.fleeksoft.ksoup.nodes.Range;
import com.fleeksoft.ksoup.nodes.TagSet;
import com.fleeksoft.ksoup.select.NodeVisitor;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: TreeBuilder.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000¶\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010;\u001a\u00020)H&J\u001a\u0010B\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u0017j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0018`\u0019J$\u0010C\u001a\u00020D2\n\u0010E\u001a\u00060Fj\u0002`G2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0006\u0010H\u001a\u00020DJ\"\u0010I\u001a\u00020\u00132\n\u0010E\u001a\u00060Fj\u0002`G2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u0006\u001a\u00020\u0005J2\u0010J\u001a\b\u0012\u0004\u0012\u00020L0K2\n\u0010M\u001a\u00060Fj\u0002`G2\b\u0010N\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u0006\u001a\u00020\u0005J\u0012\u0010O\u001a\u00020D2\b\u0010N\u001a\u0004\u0018\u00010\u0018H\u0016J\u000e\u0010P\u001a\b\u0012\u0004\u0012\u00020L0KH&J\u0010\u00101\u001a\u00020D2\b\u00101\u001a\u0004\u0018\u000102J\b\u0010Q\u001a\u00020\u0000H&J\u0006\u0010R\u001a\u00020DJ\u0006\u0010S\u001a\u00020=J\u0010\u0010T\u001a\u00020=2\u0006\u0010U\u001a\u00020$H&J\u000e\u0010V\u001a\u00020=2\u0006\u0010W\u001a\u00020\u001dJ\u0018\u0010V\u001a\u00020=2\u0006\u0010W\u001a\u00020\u001d2\b\u0010X\u001a\u0004\u0018\u00010YJ\u000e\u0010Z\u001a\u00020=2\u0006\u0010W\u001a\u00020\u001dJ\n\u0010[\u001a\u0004\u0018\u00010\u0018H\u0016J\u000e\u0010\\\u001a\u00020D2\u0006\u0010]\u001a\u00020\u0018J\u0006\u0010^\u001a\u00020DJ\u0010\u0010_\u001a\u00020D2\u0006\u0010]\u001a\u00020\u0018H\u0016J\b\u0010`\u001a\u00020aH\u0016J\u0006\u0010b\u001a\u00020\u0018J\u0010\u0010c\u001a\u00020=2\b\u0010d\u001a\u0004\u0018\u00010\u001dJ\u001a\u0010c\u001a\u00020=2\b\u0010d\u001a\u0004\u0018\u00010\u001d2\b\u0010e\u001a\u0004\u0018\u00010\u001dJ\u0010\u0010f\u001a\u00020D2\u0006\u0010g\u001a\u00020\u001dH\u0004J&\u0010h\u001a\u00020i2\u0006\u0010j\u001a\u00020\u001d2\u0006\u0010d\u001a\u00020\u001d2\u0006\u0010e\u001a\u00020\u001d2\u0006\u0010*\u001a\u00020)J\u000e\u0010h\u001a\u00020i2\u0006\u0010U\u001a\u00020kJ\b\u0010l\u001a\u00020\u001dH\u0016J\b\u0010m\u001a\u00020-H\u0016J\u000e\u0010n\u001a\u00020D2\u0006\u0010o\u001a\u00020LJ\u000e\u0010p\u001a\u00020D2\u0006\u0010o\u001a\u00020LJ\u0016\u0010q\u001a\u00020D2\u0006\u0010o\u001a\u00020L2\u0006\u0010r\u001a\u00020=R$\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@@X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000b@BX\u0086.¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\"\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0004\u001a\u0004\u0018\u00010\u000f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0004\u001a\u00020\u0013@BX\u0086.¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016RN\u0010\u001a\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0018\u0018\u00010\u0017j\f\u0012\u0006\u0012\u0004\u0018\u00010\u0018\u0018\u0001`\u00192\u001e\u0010\u0004\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0018\u0018\u00010\u0017j\f\u0012\u0006\u0012\u0004\u0018\u00010\u0018\u0018\u0001`\u0019@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR$\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u0004\u001a\u00020\u001d@TX\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020$X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001e\u0010*\u001a\u00020)2\u0006\u0010\u0004\u001a\u00020)@BX\u0086.¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u001e\u0010.\u001a\u00020-2\u0006\u0010\u0004\u001a\u00020-@BX\u0086.¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u001c\u00101\u001a\u0004\u0018\u000102X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u000e\u00107\u001a\u000208X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020:X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010<\u001a\u00020=X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010A¨\u0006s"}, d2 = {"Lcom/fleeksoft/ksoup/parser/TreeBuilder;", "", "<init>", "()V", ES6Iterator.VALUE_PROPERTY, "Lcom/fleeksoft/ksoup/parser/Parser;", "parser", "getParser", "()Lcom/fleeksoft/ksoup/parser/Parser;", "setParser$ksoup_release", "(Lcom/fleeksoft/ksoup/parser/Parser;)V", "Lcom/fleeksoft/ksoup/parser/CharacterReader;", "reader", "getReader", "()Lcom/fleeksoft/ksoup/parser/CharacterReader;", "Lcom/fleeksoft/ksoup/parser/Tokeniser;", "tokeniser", "getTokeniser", "()Lcom/fleeksoft/ksoup/parser/Tokeniser;", "Lcom/fleeksoft/ksoup/nodes/Document;", "doc", "getDoc", "()Lcom/fleeksoft/ksoup/nodes/Document;", "Ljava/util/ArrayList;", "Lcom/fleeksoft/ksoup/nodes/Element;", "Lkotlin/collections/ArrayList;", "_stack", "get_stack", "()Ljava/util/ArrayList;", "", "baseUri", "getBaseUri", "()Ljava/lang/String;", "setBaseUri", "(Ljava/lang/String;)V", "currentToken", "Lcom/fleeksoft/ksoup/parser/Token;", "getCurrentToken", "()Lcom/fleeksoft/ksoup/parser/Token;", "setCurrentToken", "(Lcom/fleeksoft/ksoup/parser/Token;)V", "Lcom/fleeksoft/ksoup/parser/ParseSettings;", "settings", "getSettings", "()Lcom/fleeksoft/ksoup/parser/ParseSettings;", "Lcom/fleeksoft/ksoup/nodes/TagSet;", "tagSet", "getTagSet", "()Lcom/fleeksoft/ksoup/nodes/TagSet;", "nodeListener", "Lcom/fleeksoft/ksoup/select/NodeVisitor;", "getNodeListener", "()Lcom/fleeksoft/ksoup/select/NodeVisitor;", "setNodeListener", "(Lcom/fleeksoft/ksoup/select/NodeVisitor;)V", "start", "Lcom/fleeksoft/ksoup/parser/Token$StartTag;", TtmlNode.END, "Lcom/fleeksoft/ksoup/parser/Token$EndTag;", "defaultSettings", "trackSourceRange", "", "getTrackSourceRange", "()Z", "setTrackSourceRange", "(Z)V", "getStack", "initialiseParse", "", TvContractCompat.PARAM_INPUT, "Ljava/io/Reader;", "Lcom/fleeksoft/io/Reader;", "completeParse", "parse", "parseFragment", "", "Lcom/fleeksoft/ksoup/nodes/Node;", "inputFragment", "context", "initialiseParseFragment", "completeParseFragment", "newInstance", "runParser", "stepParser", "process", "token", "processStartTag", "name", "attrs", "Lcom/fleeksoft/ksoup/nodes/Attributes;", "processEndTag", "pop", "push", "element", "enforceStackDepthLimit", "onStackPrunedForDepth", "defaultMaxDepth", "", "currentElement", "currentElementIs", "normalName", "namespace", MediaRouteProviderProtocol.SERVICE_DATA_ERROR, NotificationCompat.CATEGORY_MESSAGE, "tagFor", "Lcom/fleeksoft/ksoup/parser/Tag;", "tagName", "Lcom/fleeksoft/ksoup/parser/Token$Tag;", "defaultNamespace", "defaultTagSet", "onNodeInserted", "node", "onNodeClosed", "trackNodePosition", "isStart", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class TreeBuilder {
    private ArrayList<Element> _stack;
    protected String baseUri;
    public Token currentToken;
    private Document doc;
    private Token.EndTag end;
    private NodeVisitor nodeListener;
    public Parser parser;
    private CharacterReader reader;
    private ParseSettings settings;
    private Token.StartTag start;
    private TagSet tagSet;
    private Tokeniser tokeniser;
    private boolean trackSourceRange;

    public abstract List<Node> completeParseFragment();

    public int defaultMaxDepth() {
        return 512;
    }

    public abstract ParseSettings defaultSettings();

    public void initialiseParseFragment(Element context) {
    }

    public abstract TreeBuilder newInstance();

    public void onStackPrunedForDepth(Element element) {
        Intrinsics.checkNotNullParameter(element, "element");
    }

    public abstract boolean process(Token token);

    public final Parser getParser() {
        Parser parser = this.parser;
        if (parser != null) {
            return parser;
        }
        Intrinsics.throwUninitializedPropertyAccessException("parser");
        return null;
    }

    public final void setParser$ksoup_release(Parser parser) {
        Intrinsics.checkNotNullParameter(parser, "<set-?>");
        this.parser = parser;
    }

    public final CharacterReader getReader() {
        CharacterReader characterReader = this.reader;
        if (characterReader != null) {
            return characterReader;
        }
        Intrinsics.throwUninitializedPropertyAccessException("reader");
        return null;
    }

    public final Tokeniser getTokeniser() {
        return this.tokeniser;
    }

    public final Document getDoc() {
        Document document = this.doc;
        if (document != null) {
            return document;
        }
        Intrinsics.throwUninitializedPropertyAccessException("doc");
        return null;
    }

    public final ArrayList<Element> get_stack() {
        return this._stack;
    }

    public String getBaseUri() {
        String str = this.baseUri;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("baseUri");
        return null;
    }

    protected void setBaseUri(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.baseUri = str;
    }

    public final Token getCurrentToken() {
        Token token = this.currentToken;
        if (token != null) {
            return token;
        }
        Intrinsics.throwUninitializedPropertyAccessException("currentToken");
        return null;
    }

    public final void setCurrentToken(Token token) {
        Intrinsics.checkNotNullParameter(token, "<set-?>");
        this.currentToken = token;
    }

    public final ParseSettings getSettings() {
        ParseSettings parseSettings = this.settings;
        if (parseSettings != null) {
            return parseSettings;
        }
        Intrinsics.throwUninitializedPropertyAccessException("settings");
        return null;
    }

    public final TagSet getTagSet() {
        TagSet tagSet = this.tagSet;
        if (tagSet != null) {
            return tagSet;
        }
        Intrinsics.throwUninitializedPropertyAccessException("tagSet");
        return null;
    }

    public final NodeVisitor getNodeListener() {
        return this.nodeListener;
    }

    public final void setNodeListener(NodeVisitor nodeVisitor) {
        this.nodeListener = nodeVisitor;
    }

    public final boolean getTrackSourceRange() {
        return this.trackSourceRange;
    }

    public final void setTrackSourceRange(boolean z) {
        this.trackSourceRange = z;
    }

    public final ArrayList<Element> getStack() {
        ArrayList<Element> arrayList = this._stack;
        Intrinsics.checkNotNull(arrayList);
        return arrayList;
    }

    public void initialiseParse(Reader input, String baseUri, Parser parser) {
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(baseUri, "baseUri");
        Intrinsics.checkNotNullParameter(parser, "parser");
        this.end = new Token.EndTag(this);
        this.doc = new Document(parser.defaultNamespace(), baseUri, null, 4, null);
        getDoc().parser(parser);
        setParser$ksoup_release(parser);
        this.settings = parser.getSettings();
        this.reader = new CharacterReader(input);
        this.trackSourceRange = parser.getIsTrackPosition();
        getReader().trackNewlines(parser.isTrackErrors() || this.trackSourceRange);
        if (parser.isTrackErrors()) {
            parser.getErrors().clear();
        }
        this.tokeniser = new Tokeniser(this);
        this._stack = new ArrayList<>(32);
        this.tagSet = parser.tagSet();
        Token.StartTag startTag = new Token.StartTag(this);
        this.start = startTag;
        setCurrentToken(startTag);
        setBaseUri(baseUri);
        onNodeInserted(getDoc());
    }

    public final void completeParse() {
        if (this.reader == null) {
            return;
        }
        getReader().close();
        this.tokeniser = null;
        this._stack = null;
    }

    public final Document parse(Reader input, String baseUri, Parser parser) {
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(baseUri, "baseUri");
        Intrinsics.checkNotNullParameter(parser, "parser");
        initialiseParse(input, baseUri, parser);
        runParser();
        return getDoc();
    }

    public final List<Node> parseFragment(Reader inputFragment, Element context, String baseUri, Parser parser) {
        Intrinsics.checkNotNullParameter(inputFragment, "inputFragment");
        Intrinsics.checkNotNullParameter(baseUri, "baseUri");
        Intrinsics.checkNotNullParameter(parser, "parser");
        initialiseParse(inputFragment, baseUri, parser);
        initialiseParseFragment(context);
        runParser();
        return completeParseFragment();
    }

    public final void nodeListener(NodeVisitor nodeListener) {
        this.nodeListener = nodeListener;
    }

    public final void runParser() {
        while (stepParser()) {
        }
        completeParse();
    }

    public final boolean stepParser() {
        if (getCurrentToken().getType() == Token.TokenType.EOF) {
            ArrayList<Element> arrayList = this._stack;
            if (arrayList == null) {
                return false;
            }
            if (arrayList != null && arrayList.isEmpty()) {
                onNodeClosed(getDoc());
                this._stack = null;
                return true;
            }
            pop();
            return true;
        }
        Tokeniser tokeniser = this.tokeniser;
        Intrinsics.checkNotNull(tokeniser);
        Token token = tokeniser.read();
        setCurrentToken(token);
        process(token);
        token.reset();
        return true;
    }

    public final boolean processStartTag(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Token.StartTag startTag = this.start;
        if (startTag == null) {
            Intrinsics.throwUninitializedPropertyAccessException("start");
            startTag = null;
        }
        if (getCurrentToken() == startTag) {
            return process(new Token.StartTag(this).name(name));
        }
        return process(startTag.reset().name(name));
    }

    public final boolean processStartTag(String name, Attributes attrs) {
        Intrinsics.checkNotNullParameter(name, "name");
        Token.StartTag startTag = this.start;
        if (startTag == null) {
            Intrinsics.throwUninitializedPropertyAccessException("start");
            startTag = null;
        }
        if (getCurrentToken() == startTag) {
            return process(new Token.StartTag(this).nameAttr(name, attrs));
        }
        startTag.reset();
        startTag.nameAttr(name, attrs);
        return process(startTag);
    }

    public final boolean processEndTag(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Token currentToken = getCurrentToken();
        Token.EndTag endTag = this.end;
        Token.EndTag endTag2 = null;
        if (endTag == null) {
            Intrinsics.throwUninitializedPropertyAccessException(TtmlNode.END);
            endTag = null;
        }
        if (currentToken == endTag) {
            return process(new Token.EndTag(this).name(name));
        }
        Token.EndTag endTag3 = this.end;
        if (endTag3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(TtmlNode.END);
        } else {
            endTag2 = endTag3;
        }
        return process(endTag2.reset().name(name));
    }

    public Element pop() {
        ArrayList<Element> arrayList;
        ArrayList<Element> arrayList2 = this._stack;
        Element elementRemove = null;
        Integer numValueOf = arrayList2 != null ? Integer.valueOf(arrayList2.size()) : null;
        if (numValueOf != null && (arrayList = this._stack) != null) {
            elementRemove = arrayList.remove(numValueOf.intValue() - 1);
        }
        if (elementRemove != null) {
            onNodeClosed(elementRemove);
        }
        return elementRemove;
    }

    public final void push(Element element) {
        Intrinsics.checkNotNullParameter(element, "element");
        getStack().add(element);
        onNodeInserted(element);
    }

    public final void enforceStackDepthLimit() {
        int maxDepthValue = getParser().getMaxDepthValue();
        if (maxDepthValue == Integer.MAX_VALUE) {
            return;
        }
        while (true) {
            ArrayList<Element> arrayList = this._stack;
            Intrinsics.checkNotNull(arrayList);
            if (arrayList.size() < maxDepthValue) {
                return;
            }
            Element elementPop = pop();
            if (elementPop != null) {
                onStackPrunedForDepth(elementPop);
            }
        }
    }

    public final Element currentElement() {
        Element doc;
        ArrayList<Element> arrayList = this._stack;
        int size = arrayList != null ? arrayList.size() : 0;
        if (size > 0) {
            ArrayList<Element> arrayList2 = this._stack;
            Intrinsics.checkNotNull(arrayList2);
            doc = arrayList2.get(size - 1);
            Intrinsics.checkNotNull(doc);
        } else {
            doc = getDoc();
        }
        return doc;
    }

    public final boolean currentElementIs(String normalName) {
        ArrayList<Element> arrayList = this._stack;
        if ((arrayList != null ? arrayList.size() : 0) == 0) {
            return false;
        }
        Element elementCurrentElement = currentElement();
        return Intrinsics.areEqual(elementCurrentElement.normalName(), normalName) && Intrinsics.areEqual(elementCurrentElement.tag().namespace(), "http://www.w3.org/1999/xhtml");
    }

    public final boolean currentElementIs(String normalName, String namespace) {
        ArrayList<Element> arrayList = this._stack;
        if ((arrayList != null ? arrayList.size() : 0) == 0) {
            return false;
        }
        Element elementCurrentElement = currentElement();
        return Intrinsics.areEqual(elementCurrentElement.normalName(), normalName) && Intrinsics.areEqual(elementCurrentElement.tag().namespace(), namespace);
    }

    protected final void error(String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        ParseErrorList errors = getParser().getErrors();
        if (errors.canAddError()) {
            errors.add(new ParseError(getReader(), msg));
        }
    }

    public final Tag tagFor(String tagName, String normalName, String namespace, ParseSettings settings) {
        Intrinsics.checkNotNullParameter(tagName, "tagName");
        Intrinsics.checkNotNullParameter(normalName, "normalName");
        Intrinsics.checkNotNullParameter(namespace, "namespace");
        Intrinsics.checkNotNullParameter(settings, "settings");
        TagSet tagSet = getTagSet();
        Intrinsics.checkNotNull(tagSet);
        return tagSet.valueOf(tagName, normalName, namespace, settings.getPreserveTagCase());
    }

    public final Tag tagFor(Token.Tag token) {
        Intrinsics.checkNotNullParameter(token, "token");
        TagSet tagSet = getTagSet();
        Intrinsics.checkNotNull(tagSet);
        String strName = token.name();
        String normalName$ksoup_release = token.getNormalName();
        Intrinsics.checkNotNull(normalName$ksoup_release);
        String strDefaultNamespace = defaultNamespace();
        ParseSettings settings = getSettings();
        Intrinsics.checkNotNull(settings);
        return tagSet.valueOf(strName, normalName$ksoup_release, strDefaultNamespace, settings.getPreserveTagCase());
    }

    public String defaultNamespace() {
        return "http://www.w3.org/1999/xhtml";
    }

    public TagSet defaultTagSet() {
        return TagSet.INSTANCE.Html();
    }

    public final void onNodeInserted(Node node) {
        Intrinsics.checkNotNullParameter(node, "node");
        trackNodePosition(node, true);
        NodeVisitor nodeVisitor = this.nodeListener;
        if (nodeVisitor != null) {
            nodeVisitor.head(node, getStack().size());
        }
    }

    public final void onNodeClosed(Node node) {
        Intrinsics.checkNotNullParameter(node, "node");
        trackNodePosition(node, false);
        NodeVisitor nodeVisitor = this.nodeListener;
        if (nodeVisitor != null) {
            ArrayList<Element> arrayList = this._stack;
            Intrinsics.checkNotNull(arrayList);
            nodeVisitor.tail(node, arrayList.size());
        }
    }

    public final void trackNodePosition(Node node, boolean isStart) {
        Intrinsics.checkNotNullParameter(node, "node");
        if (this.trackSourceRange) {
            Token currentToken = getCurrentToken();
            Intrinsics.checkNotNull(currentToken);
            int iStartPos = currentToken.startPos();
            int iEndPos = currentToken.getEndPos();
            if (node instanceof Element) {
                if (currentToken.isEOF()) {
                    if (((Element) node).endSourceRange().isTracked()) {
                        return;
                    } else {
                        iStartPos = getReader().pos();
                    }
                } else if (isStart) {
                    if (!currentToken.isStartTag() || !Intrinsics.areEqual(((Element) node).normalName(), currentToken.asStartTag().getNormalName())) {
                    }
                } else {
                    Element element = (Element) node;
                    if (!element.tag().isEmpty() && !element.tag().isSelfClosing() && (!currentToken.isEndTag() || !Intrinsics.areEqual(element.normalName(), currentToken.asEndTag().getNormalName()))) {
                    }
                }
                iEndPos = iStartPos;
            }
            node.attributes().userData(isStart ? SharedConstants.RangeKey : SharedConstants.EndRangeKey, new Range(new Range.Position(iStartPos, getReader().lineNumber(iStartPos), getReader().columnNumber(iStartPos)), new Range.Position(iEndPos, getReader().lineNumber(iEndPos), getReader().columnNumber(iEndPos))));
        }
    }
}
