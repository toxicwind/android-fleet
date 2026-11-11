package com.fleeksoft.ksoup.parser;

import androidx.tvprovider.media.tv.TvContractCompat;
import com.fleeksoft.ksoup.internal.SharedConstants;
import com.fleeksoft.ksoup.nodes.Attribute;
import com.fleeksoft.ksoup.nodes.Attributes;
import com.fleeksoft.ksoup.nodes.CDataNode;
import com.fleeksoft.ksoup.nodes.Comment;
import com.fleeksoft.ksoup.nodes.DataNode;
import com.fleeksoft.ksoup.nodes.Document;
import com.fleeksoft.ksoup.nodes.DocumentType;
import com.fleeksoft.ksoup.nodes.Element;
import com.fleeksoft.ksoup.nodes.Entities;
import com.fleeksoft.ksoup.nodes.LeafNode;
import com.fleeksoft.ksoup.nodes.Node;
import com.fleeksoft.ksoup.nodes.TagSet;
import com.fleeksoft.ksoup.nodes.TextNode;
import com.fleeksoft.ksoup.nodes.XmlDeclaration;
import com.fleeksoft.ksoup.select.Elements;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: XmlTreeBuilder.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 82\u00020\u0001:\u00018B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\t\u001a\u00020\nH\u0016J$\u0010\u000b\u001a\u00020\f2\n\u0010\r\u001a\u00060\u000ej\u0002`\u000f2\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0012\u0010\u0013\u001a\u00020\f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u000e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0016J\u001e\u0010\u0019\u001a\u00020\u001a2\n\u0010\r\u001a\u00060\u000ej\u0002`\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0007J\u001a\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\r\u001a\u00020\u00072\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0007J\b\u0010\u001b\u001a\u00020\u0000H\u0016J\b\u0010\u001c\u001a\u00020\u0007H\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0016J\u000e\u0010%\u001a\u00020\f2\u0006\u0010&\u001a\u00020'J\u0010\u0010(\u001a\u00020\f2\b\u0010)\u001a\u0004\u0018\u00010*J\u000e\u0010+\u001a\u00020\f2\u0006\u0010,\u001a\u00020-J\u000e\u0010.\u001a\u00020\f2\u0006\u0010#\u001a\u00020/J\u000e\u00100\u001a\u00020\f2\u0006\u0010#\u001a\u000201J\u000e\u00102\u001a\u00020\f2\u0006\u0010#\u001a\u000203J\n\u00104\u001a\u0004\u0018\u00010\u0015H\u0016J\u0010\u00105\u001a\u00020\f2\u0006\u00106\u001a\u000207H\u0002R0\u0010\u0004\u001a$\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lcom/fleeksoft/ksoup/parser/XmlTreeBuilder;", "Lcom/fleeksoft/ksoup/parser/TreeBuilder;", "<init>", "()V", "namespacesStack", "Lkotlin/collections/ArrayDeque;", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "defaultSettings", "Lcom/fleeksoft/ksoup/parser/ParseSettings;", "initialiseParse", "", TvContractCompat.PARAM_INPUT, "Ljava/io/Reader;", "Lcom/fleeksoft/io/Reader;", "baseUri", "parser", "Lcom/fleeksoft/ksoup/parser/Parser;", "initialiseParseFragment", "context", "Lcom/fleeksoft/ksoup/nodes/Element;", "completeParseFragment", "", "Lcom/fleeksoft/ksoup/nodes/Node;", "parse", "Lcom/fleeksoft/ksoup/nodes/Document;", "newInstance", "defaultNamespace", "defaultTagSet", "Lcom/fleeksoft/ksoup/nodes/TagSet;", "defaultMaxDepth", "", "process", "", "token", "Lcom/fleeksoft/ksoup/parser/Token;", "insertElementFor", "startTag", "Lcom/fleeksoft/ksoup/parser/Token$StartTag;", "insertLeafNode", "node", "Lcom/fleeksoft/ksoup/nodes/LeafNode;", "insertCommentFor", "commentToken", "Lcom/fleeksoft/ksoup/parser/Token$Comment;", "insertCharacterFor", "Lcom/fleeksoft/ksoup/parser/Token$Character;", "insertDoctypeFor", "Lcom/fleeksoft/ksoup/parser/Token$Doctype;", "insertXmlDeclarationFor", "Lcom/fleeksoft/ksoup/parser/Token$XmlDecl;", "pop", "popStackToClose", "endTag", "Lcom/fleeksoft/ksoup/parser/Token$EndTag;", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class XmlTreeBuilder extends TreeBuilder {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String XmlnsKey = "xmlns";
    public static final String XmlnsPrefix = "xmlns:";
    private static final int maxQueueDepth = 256;
    private final ArrayDeque<HashMap<String, String>> namespacesStack = new ArrayDeque<>();

    /* JADX INFO: compiled from: XmlTreeBuilder.kt */
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
            try {
                iArr[Token.TokenType.Comment.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Token.TokenType.Character.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[Token.TokenType.Doctype.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[Token.TokenType.XmlDecl.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[Token.TokenType.EOF.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // com.fleeksoft.ksoup.parser.TreeBuilder
    public int defaultMaxDepth() {
        return Integer.MAX_VALUE;
    }

    @Override // com.fleeksoft.ksoup.parser.TreeBuilder
    public ParseSettings defaultSettings() {
        return ParseSettings.INSTANCE.getPreserveCase();
    }

    @Override // com.fleeksoft.ksoup.parser.TreeBuilder
    public void initialiseParse(Reader input, String baseUri, Parser parser) {
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(baseUri, "baseUri");
        Intrinsics.checkNotNullParameter(parser, "parser");
        super.initialiseParse(input, baseUri, parser);
        getDoc().getOutputSettings().syntax(Document.OutputSettings.Syntax.xml).escapeMode(Entities.EscapeMode.xhtml).prettyPrint(false);
        this.namespacesStack.clear();
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> map2 = map;
        map2.put("xml", "http://www.w3.org/XML/1998/namespace");
        map2.put("", "http://www.w3.org/XML/1998/namespace");
        this.namespacesStack.add(map);
    }

    @Override // com.fleeksoft.ksoup.parser.TreeBuilder
    public void initialiseParseFragment(Element context) {
        Tokeniser tokeniser;
        super.initialiseParseFragment(context);
        if (context == null) {
            return;
        }
        TokeniserState tokeniserStateTextState = context.tag().textState();
        if (tokeniserStateTextState != null && (tokeniser = getTokeniser()) != null) {
            tokeniser.transition(tokeniserStateTextState);
        }
        Elements elementsParents = context.parents();
        elementsParents.add(0, context);
        int size = elementsParents.size();
        while (true) {
            size--;
            if (-1 >= size) {
                return;
            }
            Element element = elementsParents.get(size);
            HashMap<String, String> map = new HashMap<>(this.namespacesStack.first());
            this.namespacesStack.addFirst(map);
            if (element.attributesSize() > 0) {
                INSTANCE.processNamespaces(element.attributes(), map);
            }
        }
    }

    @Override // com.fleeksoft.ksoup.parser.TreeBuilder
    public List<Node> completeParseFragment() {
        return getDoc().childNodes();
    }

    public static /* synthetic */ Document parse$default(XmlTreeBuilder xmlTreeBuilder, Reader reader, String str, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: parse");
        }
        if ((i & 2) != 0) {
            str = null;
        }
        return xmlTreeBuilder.parse(reader, str);
    }

    public final Document parse(Reader input, String baseUri) {
        Intrinsics.checkNotNullParameter(input, "input");
        if (baseUri == null) {
            baseUri = "";
        }
        return parse(input, baseUri, new Parser(this));
    }

    public static /* synthetic */ Document parse$default(XmlTreeBuilder xmlTreeBuilder, String str, String str2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: parse");
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        return xmlTreeBuilder.parse(str, str2);
    }

    public final Document parse(String input, String baseUri) {
        Intrinsics.checkNotNullParameter(input, "input");
        StringReader stringReader = new StringReader(input);
        if (baseUri == null) {
            baseUri = "";
        }
        return parse(stringReader, baseUri, new Parser(this));
    }

    @Override // com.fleeksoft.ksoup.parser.TreeBuilder
    public XmlTreeBuilder newInstance() {
        return new XmlTreeBuilder();
    }

    @Override // com.fleeksoft.ksoup.parser.TreeBuilder
    public String defaultNamespace() {
        return "http://www.w3.org/XML/1998/namespace";
    }

    @Override // com.fleeksoft.ksoup.parser.TreeBuilder
    public TagSet defaultTagSet() {
        return new TagSet();
    }

    @Override // com.fleeksoft.ksoup.parser.TreeBuilder
    public boolean process(Token token) {
        Intrinsics.checkNotNullParameter(token, "token");
        setCurrentToken(token);
        switch (WhenMappings.$EnumSwitchMapping$0[token.getType().ordinal()]) {
            case 1:
                insertElementFor(token.asStartTag());
                return true;
            case 2:
                popStackToClose(token.asEndTag());
                return true;
            case 3:
                insertCommentFor(token.asComment());
                return true;
            case 4:
                insertCharacterFor(token.asCharacter());
                return true;
            case 5:
                insertDoctypeFor(token.asDoctype());
                return true;
            case 6:
                insertXmlDeclarationFor(token.asXmlDecl());
                return true;
            case 7:
                return true;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final void insertElementFor(Token.StartTag startTag) {
        Tokeniser tokeniser;
        Intrinsics.checkNotNullParameter(startTag, "startTag");
        HashMap<String, String> mapFirstOrNull = this.namespacesStack.firstOrNull();
        if (mapFirstOrNull == null) {
            mapFirstOrNull = new HashMap<>();
        }
        HashMap<String, String> map = new HashMap<>(mapFirstOrNull);
        this.namespacesStack.addFirst(map);
        Attributes attributes = startTag.getAttributes();
        if (attributes != null) {
            getSettings().normalizeAttributes(attributes);
            attributes.deduplicate(getSettings());
            Companion companion = INSTANCE;
            companion.processNamespaces(attributes, map);
            companion.applyNamespacesToAttributes(attributes, map);
        }
        enforceStackDepthLimit();
        String strValue = startTag.getTagName().value();
        String strResolveNamespace = INSTANCE.resolveNamespace(strValue, map);
        String normalName$ksoup_release = startTag.getNormalName();
        Intrinsics.checkNotNull(normalName$ksoup_release);
        Intrinsics.checkNotNull(strResolveNamespace);
        Tag tagTagFor = tagFor(strValue, normalName$ksoup_release, strResolveNamespace, getSettings());
        Element element = new Element(tagTagFor, null, attributes);
        currentElement().appendChild(element);
        push(element);
        if (startTag.getSelfClosing()) {
            tagTagFor.setSeenSelfClose();
            pop();
        } else {
            if (tagTagFor.isEmpty()) {
                pop();
                return;
            }
            TokeniserState tokeniserStateTextState = tagTagFor.textState();
            if (tokeniserStateTextState != null && (tokeniser = getTokeniser()) != null) {
                tokeniser.transition(tokeniserStateTextState);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void insertLeafNode(LeafNode node) {
        Element elementCurrentElement = currentElement();
        Intrinsics.checkNotNull(node);
        LeafNode leafNode = node;
        elementCurrentElement.appendChild(leafNode);
        onNodeInserted(leafNode);
    }

    public final void insertCommentFor(Token.Comment commentToken) {
        Intrinsics.checkNotNullParameter(commentToken, "commentToken");
        insertLeafNode(new Comment(commentToken.getData()));
    }

    public final void insertCharacterFor(Token.Character token) {
        TextNode dataNode;
        Intrinsics.checkNotNullParameter(token, "token");
        String strM8177getData = token.m8177getData();
        if (token.isCData()) {
            dataNode = new CDataNode(strM8177getData);
        } else {
            dataNode = currentElement().tag().is(256) ? new DataNode(strM8177getData) : new TextNode(strM8177getData);
        }
        insertLeafNode(dataNode);
    }

    public final void insertDoctypeFor(Token.Doctype token) {
        Intrinsics.checkNotNullParameter(token, "token");
        DocumentType documentType = new DocumentType(getSettings().normalizeTag(token.m8178getName()), token.m8179getPublicIdentifier(), token.m8180getSystemIdentifier());
        documentType.setPubSysKey(token.getPubSysKey());
        insertLeafNode(documentType);
    }

    public final void insertXmlDeclarationFor(Token.XmlDecl token) {
        Intrinsics.checkNotNullParameter(token, "token");
        XmlDeclaration xmlDeclaration = new XmlDeclaration(token.name(), token.getIsDeclaration());
        Attributes attributes = token.getAttributes();
        if (attributes != null) {
            xmlDeclaration.attributes().addAll(attributes);
        }
        insertLeafNode(xmlDeclaration);
    }

    @Override // com.fleeksoft.ksoup.parser.TreeBuilder
    public Element pop() {
        this.namespacesStack.remove(0);
        return super.pop();
    }

    private final void popStackToClose(Token.EndTag endTag) {
        Element element;
        String strNormalizeTag = getSettings().normalizeTag(endTag.name());
        int size = getStack().size();
        int i = size + (-1) >= 256 ? size - 257 : 0;
        int size2 = getStack().size() - 1;
        if (i > size2) {
            element = null;
            break;
        }
        while (true) {
            element = getStack().get(size2);
            Intrinsics.checkNotNull(element);
            if (!Intrinsics.areEqual(element.nodeName(), strNormalizeTag)) {
                if (size2 == i) {
                    element = null;
                    break;
                }
                size2--;
            } else {
                break;
            }
        }
        if (element == null) {
            return;
        }
        int size3 = getStack().size();
        do {
            size3--;
            if (-1 >= size3) {
                return;
            }
        } while (pop() != element);
    }

    /* JADX INFO: compiled from: XmlTreeBuilder.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J2\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\"\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u000ej\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u000fJ4\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\"\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u000ej\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u000fH\u0002J6\u0010\u0011\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0012\u001a\u00020\u00072\"\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u000ej\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u000fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/fleeksoft/ksoup/parser/XmlTreeBuilder$Companion;", "", "<init>", "()V", "maxQueueDepth", "", "XmlnsKey", "", "XmlnsPrefix", "processNamespaces", "", "attributes", "Lcom/fleeksoft/ksoup/nodes/Attributes;", "namespaces", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "applyNamespacesToAttributes", "resolveNamespace", "tagName", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void processNamespaces(Attributes attributes, HashMap<String, String> namespaces) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            Intrinsics.checkNotNullParameter(namespaces, "namespaces");
            for (Attribute attribute : attributes) {
                String key = attribute.getKey();
                String value = attribute.getValue();
                if (Intrinsics.areEqual(key, XmlTreeBuilder.XmlnsKey)) {
                    namespaces.put("", value);
                } else if (StringsKt.startsWith$default(key, XmlTreeBuilder.XmlnsPrefix, false, 2, (Object) null)) {
                    String strSubstring = key.substring(6);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                    namespaces.put(strSubstring, value);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void applyNamespacesToAttributes(Attributes attributes, HashMap<String, String> namespaces) {
            String str;
            HashMap map = new HashMap();
            Iterator<Attribute> it = attributes.iterator();
            while (it.hasNext()) {
                String strPrefix = it.next().prefix();
                if (strPrefix.length() != 0 && !Intrinsics.areEqual(strPrefix, XmlTreeBuilder.XmlnsKey) && (str = namespaces.get(strPrefix)) != null) {
                    map.put(SharedConstants.XmlnsAttr + strPrefix, str);
                }
            }
            for (Map.Entry entry : map.entrySet()) {
                attributes.userData((String) entry.getKey(), entry.getValue());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String resolveNamespace(String tagName, HashMap<String, String> namespaces) {
            String str = namespaces.get("");
            int iIndexOf$default = StringsKt.indexOf$default((CharSequence) tagName, AbstractJsonLexerKt.COLON, 0, false, 6, (Object) null);
            if (iIndexOf$default <= 0) {
                return str;
            }
            String strSubstring = tagName.substring(0, iIndexOf$default);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            return namespaces.containsKey(strSubstring) ? namespaces.get(strSubstring) : str;
        }
    }
}
