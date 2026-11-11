package com.fleeksoft.ksoup.parser;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.fleeksoft.ksoup.helper.Validate;
import com.fleeksoft.ksoup.nodes.Document;
import com.fleeksoft.ksoup.nodes.Element;
import com.fleeksoft.ksoup.nodes.Node;
import com.fleeksoft.ksoup.nodes.TagSet;
import com.fleeksoft.ksoup.ported.KCloneable;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Parser.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0007\u0018\u0000 52\b\u0012\u0004\u0012\u00020\u00000\u0001:\u00015B\u0011\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0012\u0012\u0006\u0010\u0006\u001a\u00020\u0000¢\u0006\u0004\b\u0004\u0010\u0007J\u0006\u0010\u0017\u001a\u00020\u0000J\b\u0010\u0018\u001a\u00020\u0000H\u0016J\u0016\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001cJ\u001c\u0010\u0019\u001a\u00020\u001a2\n\u0010\u001e\u001a\u00060\u001fj\u0002` 2\u0006\u0010\u001d\u001a\u00020\u001cH\u0007J&\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"2\u0006\u0010$\u001a\u00020\u001c2\b\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010\u001d\u001a\u00020\u001cJ,\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"2\n\u0010$\u001a\u00060\u001fj\u0002` 2\b\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010\u001d\u001a\u00020\u001cH\u0007J\u0006\u0010'\u001a\u00020\u0003J\u0006\u0010(\u001a\u00020\u0014J\u000e\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\u0012J\u0006\u0010+\u001a\u00020\tJ\u000e\u0010,\u001a\u00020\u00002\u0006\u0010-\u001a\u00020\u0014J\u0010\u0010\n\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010.\u001a\u00020\u00002\u0006\u0010/\u001a\u00020\u0012J\u0006\u00100\u001a\u00020\u0012J\u0010\u0010\f\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\rH\u0007J\u0006\u0010\f\u001a\u00020\rJ\u0016\u00101\u001a\u00020\u001c2\u0006\u00102\u001a\u00020\u001c2\u0006\u00103\u001a\u00020\u0014J\u0006\u00104\u001a\u00020\u001cR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u0004\u0018\u00010\r8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u00060\u000fj\u0002`\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0014@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u00066"}, d2 = {"Lcom/fleeksoft/ksoup/parser/Parser;", "Lcom/fleeksoft/ksoup/ported/KCloneable;", "treeBuilder", "Lcom/fleeksoft/ksoup/parser/TreeBuilder;", "<init>", "(Lcom/fleeksoft/ksoup/parser/TreeBuilder;)V", "copy", "(Lcom/fleeksoft/ksoup/parser/Parser;)V", "errors", "Lcom/fleeksoft/ksoup/parser/ParseErrorList;", "settings", "Lcom/fleeksoft/ksoup/parser/ParseSettings;", "tagSet", "Lcom/fleeksoft/ksoup/nodes/TagSet;", "lock", "", "Lco/touchlab/stately/concurrency/Synchronizable;", "maxDepthValue", "", ES6Iterator.VALUE_PROPERTY, "", "isTrackPosition", "()Z", "newInstance", "clone", "parseInput", "Lcom/fleeksoft/ksoup/nodes/Document;", TvContractCompat.PARAM_INPUT, "", "baseUri", "reader", "Ljava/io/Reader;", "Lcom/fleeksoft/io/Reader;", "parseFragmentInput", "", "Lcom/fleeksoft/ksoup/nodes/Node;", "fragment", "context", "Lcom/fleeksoft/ksoup/nodes/Element;", "getTreeBuilder", "isTrackErrors", "setTrackErrors", "maxErrors", "getErrors", "setTrackPosition", "trackPosition", "setMaxDepth", "maxDepth", "getMaxDepth", "unescape", TypedValues.Custom.S_STRING, "inAttribute", "defaultNamespace", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Parser implements KCloneable<Parser> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String NamespaceHtml = "http://www.w3.org/1999/xhtml";
    public static final String NamespaceMathml = "http://www.w3.org/1998/Math/MathML";
    public static final String NamespaceSvg = "http://www.w3.org/2000/svg";
    public static final String NamespaceXml = "http://www.w3.org/XML/1998/namespace";
    private ParseErrorList errors;
    private boolean isTrackPosition;
    private final Object lock;
    private int maxDepthValue;
    private ParseSettings settings;
    private TagSet tagSet;
    private TreeBuilder treeBuilder;

    /* JADX INFO: renamed from: isTrackPosition, reason: from getter */
    public final boolean getIsTrackPosition() {
        return this.isTrackPosition;
    }

    public Parser(TreeBuilder treeBuilder) {
        Intrinsics.checkNotNullParameter(treeBuilder, "treeBuilder");
        this.lock = new Object();
        this.treeBuilder = treeBuilder;
        this.settings = treeBuilder.defaultSettings();
        this.errors = ParseErrorList.INSTANCE.noTracking();
        this.maxDepthValue = treeBuilder.defaultMaxDepth();
    }

    public final Parser newInstance() {
        return new Parser(this);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fleeksoft.ksoup.ported.KCloneable
    /* JADX INFO: renamed from: clone */
    public Parser clone2() {
        return new Parser(this);
    }

    private Parser(Parser parser) {
        this.lock = new Object();
        this.treeBuilder = parser.treeBuilder.newInstance();
        this.errors = new ParseErrorList(parser.errors);
        this.settings = new ParseSettings(parser.settings);
        this.isTrackPosition = parser.isTrackPosition;
        this.maxDepthValue = parser.maxDepthValue;
        this.tagSet = new TagSet(parser.tagSet());
    }

    public final Document parseInput(String input, String baseUri) {
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(baseUri, "baseUri");
        return parseInput(new StringReader(input), baseUri);
    }

    public final Document parseInput(final Reader reader, final String baseUri) {
        Object objInvoke;
        Intrinsics.checkNotNullParameter(reader, "reader");
        Intrinsics.checkNotNullParameter(baseUri, "baseUri");
        Object obj = this.lock;
        Function0 function0 = new Function0() { // from class: com.fleeksoft.ksoup.parser.Parser$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Parser parser = this.f$0;
                return parser.treeBuilder.parse(reader, baseUri, parser);
            }
        };
        synchronized (obj) {
            objInvoke = function0.invoke();
        }
        return (Document) objInvoke;
    }

    public final List<Node> parseFragmentInput(String fragment, Element context, String baseUri) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(baseUri, "baseUri");
        return parseFragmentInput(new StringReader(fragment), context, baseUri);
    }

    public final List<Node> parseFragmentInput(final Reader fragment, final Element context, final String baseUri) {
        Object objInvoke;
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(baseUri, "baseUri");
        Object obj = this.lock;
        Function0 function0 = new Function0() { // from class: com.fleeksoft.ksoup.parser.Parser$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Parser parser = this.f$0;
                return parser.treeBuilder.parseFragment(fragment, context, baseUri, parser);
            }
        };
        synchronized (obj) {
            objInvoke = function0.invoke();
        }
        return (List) objInvoke;
    }

    public final TreeBuilder getTreeBuilder() {
        return this.treeBuilder;
    }

    public final boolean isTrackErrors() {
        return this.errors.getMaxSize() > 0;
    }

    public final Parser setTrackErrors(int maxErrors) {
        this.errors = maxErrors > 0 ? ParseErrorList.INSTANCE.tracking(maxErrors) : ParseErrorList.INSTANCE.noTracking();
        return this;
    }

    public final ParseErrorList getErrors() {
        return this.errors;
    }

    public final Parser setTrackPosition(boolean trackPosition) {
        this.isTrackPosition = trackPosition;
        return this;
    }

    public final Parser settings(ParseSettings settings) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        this.settings = settings;
        return this;
    }

    /* JADX INFO: renamed from: settings, reason: from getter */
    public final ParseSettings getSettings() {
        return this.settings;
    }

    public final Parser setMaxDepth(int maxDepth) {
        Validate.INSTANCE.isTrue(maxDepth >= 1, "maxDepth must be >= 1");
        this.maxDepthValue = maxDepth;
        return this;
    }

    /* JADX INFO: renamed from: getMaxDepth, reason: from getter */
    public final int getMaxDepthValue() {
        return this.maxDepthValue;
    }

    public final Parser tagSet(TagSet tagSet) {
        Intrinsics.checkNotNullParameter(tagSet, "tagSet");
        this.tagSet = new TagSet(tagSet);
        return this;
    }

    public final TagSet tagSet() {
        if (this.tagSet == null) {
            this.tagSet = this.treeBuilder.defaultTagSet();
        }
        TagSet tagSet = this.tagSet;
        Intrinsics.checkNotNull(tagSet);
        return tagSet;
    }

    public final String unescape(String string, boolean inAttribute) {
        Intrinsics.checkNotNullParameter(string, "string");
        if (StringsKt.indexOf$default((CharSequence) string, Typography.amp, 0, false, 6, (Object) null) < 0) {
            return string;
        }
        this.treeBuilder.initialiseParse(new StringReader(string), "", this);
        return new Tokeniser(this.treeBuilder).unescapeEntities(inAttribute);
    }

    public final String defaultNamespace() {
        return getTreeBuilder().defaultNamespace();
    }

    /* JADX INFO: compiled from: Parser.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005J2\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\f\u001a\u00020\u00052\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014J\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005J\u0016\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005J\u0016\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u001bJ\u0006\u0010\u001c\u001a\u00020\u001dJ\u0006\u0010\u001e\u001a\u00020\u001dR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/fleeksoft/ksoup/parser/Parser$Companion;", "", "<init>", "()V", "NamespaceHtml", "", "NamespaceXml", "NamespaceMathml", "NamespaceSvg", "parse", "Lcom/fleeksoft/ksoup/nodes/Document;", "html", "baseUri", "parseFragment", "", "Lcom/fleeksoft/ksoup/nodes/Node;", "fragmentHtml", "context", "Lcom/fleeksoft/ksoup/nodes/Element;", "errorList", "Lcom/fleeksoft/ksoup/parser/ParseErrorList;", "parseXmlFragment", "fragmentXml", "parseBodyFragment", "bodyHtml", "unescapeEntities", "inAttribute", "", "htmlParser", "Lcom/fleeksoft/ksoup/parser/Parser;", "xmlParser", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Document parse(String html, String baseUri) {
            Intrinsics.checkNotNullParameter(html, "html");
            Intrinsics.checkNotNullParameter(baseUri, "baseUri");
            HtmlTreeBuilder htmlTreeBuilder = new HtmlTreeBuilder();
            return htmlTreeBuilder.parse(new StringReader(html), baseUri, new Parser(htmlTreeBuilder));
        }

        public static /* synthetic */ List parseFragment$default(Companion companion, String str, Element element, String str2, ParseErrorList parseErrorList, int i, Object obj) {
            if ((i & 8) != 0) {
                parseErrorList = null;
            }
            return companion.parseFragment(str, element, str2, parseErrorList);
        }

        public final List<Node> parseFragment(String fragmentHtml, Element context, String baseUri, ParseErrorList errorList) {
            Intrinsics.checkNotNullParameter(fragmentHtml, "fragmentHtml");
            Intrinsics.checkNotNullParameter(baseUri, "baseUri");
            HtmlTreeBuilder htmlTreeBuilder = new HtmlTreeBuilder();
            Parser parser = new Parser(htmlTreeBuilder);
            if (errorList != null) {
                parser.errors = errorList;
            }
            return htmlTreeBuilder.parseFragment(new StringReader(fragmentHtml), context, baseUri, parser);
        }

        public final List<Node> parseXmlFragment(String fragmentXml, String baseUri) {
            Intrinsics.checkNotNullParameter(fragmentXml, "fragmentXml");
            Intrinsics.checkNotNullParameter(baseUri, "baseUri");
            XmlTreeBuilder xmlTreeBuilder = new XmlTreeBuilder();
            return xmlTreeBuilder.parseFragment(new StringReader(fragmentXml), null, baseUri, new Parser(xmlTreeBuilder));
        }

        public final Document parseBodyFragment(String bodyHtml, String baseUri) {
            Intrinsics.checkNotNullParameter(bodyHtml, "bodyHtml");
            Intrinsics.checkNotNullParameter(baseUri, "baseUri");
            Document documentCreateShell = Document.INSTANCE.createShell(baseUri);
            Element elementBody = documentCreateShell.body();
            elementBody.appendChildren(parseFragment$default(this, bodyHtml, elementBody, baseUri, null, 8, null));
            return documentCreateShell;
        }

        public final String unescapeEntities(String html, boolean inAttribute) {
            Intrinsics.checkNotNullParameter(html, "html");
            return StringsKt.indexOf$default((CharSequence) html, Typography.amp, 0, false, 6, (Object) null) < 0 ? html : htmlParser().unescape(html, inAttribute);
        }

        public final Parser htmlParser() {
            return new Parser(new HtmlTreeBuilder());
        }

        public final Parser xmlParser() {
            return new Parser(new XmlTreeBuilder()).setMaxDepth(Integer.MAX_VALUE);
        }
    }
}
