package com.fleeksoft.ksoup.parser;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.fleeksoft.ksoup.helper.Validate;
import com.fleeksoft.ksoup.internal.Normalizer;
import com.fleeksoft.ksoup.internal.StringUtil;
import com.fleeksoft.ksoup.nodes.Attributes;
import com.fleeksoft.ksoup.nodes.CDataNode;
import com.fleeksoft.ksoup.nodes.Comment;
import com.fleeksoft.ksoup.nodes.DataNode;
import com.fleeksoft.ksoup.nodes.Document;
import com.fleeksoft.ksoup.nodes.Element;
import com.fleeksoft.ksoup.nodes.FormElement;
import com.fleeksoft.ksoup.nodes.Node;
import com.fleeksoft.ksoup.nodes.TextNode;
import com.google.android.gms.cast.MediaTrack;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: HtmlTreeBuilder.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0011\n\u0002\b0\n\u0002\u0010\b\n\u0002\b\u0017\b\u0016\u0018\u0000  \u00012\u00020\u0001:\u0002 \u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020\u0000H\u0016J$\u0010#\u001a\u00020$2\n\u0010%\u001a\u00060&j\u0002`'2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0016J\u0012\u0010,\u001a\u00020$2\b\u0010-\u001a\u0004\u0018\u00010\nH\u0016J\u000e\u0010.\u001a\b\u0012\u0004\u0012\u0002000/H\u0016J\u0010\u00101\u001a\u00020\b2\u0006\u00102\u001a\u000203H\u0016J\u0010\u00104\u001a\u00020\b2\u0006\u00102\u001a\u000203H\u0002J\u0016\u00101\u001a\u00020\b2\u0006\u00102\u001a\u0002032\u0006\u0010\u0004\u001a\u00020\u0005J\u0010\u00105\u001a\u00020$2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005J\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005J\u0006\u00106\u001a\u00020$J\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005J\u000e\u0010\u001a\u001a\u00020$2\u0006\u0010\u001a\u001a\u00020\bJ\u0006\u0010\u001a\u001a\u00020\bJ\u000e\u0010;\u001a\u00020$2\u0006\u0010<\u001a\u00020\nJ\u0010\u0010=\u001a\u00020$2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005J\u001e\u0010>\u001a\u00020\n2\u0006\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020)2\u0006\u0010B\u001a\u00020\bJ\u000e\u0010C\u001a\u00020\n2\u0006\u0010?\u001a\u00020@J\u0016\u0010D\u001a\u00020\n2\u0006\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020)J\u000e\u0010E\u001a\u00020\n2\u0006\u0010?\u001a\u00020@J\u001e\u0010F\u001a\u00020\f2\u0006\u0010?\u001a\u00020@2\u0006\u0010G\u001a\u00020\b2\u0006\u0010H\u001a\u00020\bJ\u0010\u0010I\u001a\u00020$2\u0006\u0010J\u001a\u00020\nH\u0002J\u000e\u0010K\u001a\u00020$2\u0006\u00102\u001a\u00020LJ\u000e\u0010M\u001a\u00020$2\u0006\u0010N\u001a\u00020\u0017J\u0016\u0010M\u001a\u00020$2\u0006\u0010N\u001a\u00020\u00172\u0006\u0010O\u001a\u00020\bJ\u0016\u0010P\u001a\u00020$2\u0006\u0010N\u001a\u00020\u00172\u0006\u0010J\u001a\u00020\nJ\u000e\u0010G\u001a\u00020\b2\u0006\u0010J\u001a\u00020\nJ\u0010\u0010G\u001a\u00020\b2\b\u0010Q\u001a\u0004\u0018\u00010)J\u0012\u0010R\u001a\u0004\u0018\u00010\n2\b\u0010Q\u001a\u0004\u0018\u00010)J\u000e\u0010S\u001a\u00020\b2\u0006\u0010J\u001a\u00020\nJ\u0010\u0010T\u001a\u00020$2\u0006\u0010U\u001a\u00020\nH\u0016J\u0010\u0010V\u001a\u0004\u0018\u00010\n2\u0006\u0010Q\u001a\u00020)J\u0010\u0010W\u001a\u0004\u0018\u00010\n2\u0006\u0010Q\u001a\u00020)J\u001f\u0010V\u001a\u00020$2\u0012\u0010X\u001a\n\u0012\u0006\b\u0001\u0012\u00020)0Y\"\u00020)¢\u0006\u0002\u0010ZJ\u0006\u0010[\u001a\u00020$J\u0006\u0010\\\u001a\u00020$J\u0006\u0010]\u001a\u00020$J!\u0010^\u001a\u00020$2\u0012\u0010_\u001a\n\u0012\u0006\b\u0001\u0012\u00020)0Y\"\u00020)H\u0002¢\u0006\u0002\u0010ZJ\u0010\u0010`\u001a\u0004\u0018\u00010\n2\u0006\u0010J\u001a\u00020\nJ\u0016\u0010a\u001a\u00020$2\u0006\u0010b\u001a\u00020\n2\u0006\u0010c\u001a\u00020\nJ\u0016\u0010d\u001a\u00020$2\u0006\u0010e\u001a\u00020\n2\u0006\u0010c\u001a\u00020\nJ\u0006\u0010f\u001a\u00020\bJ\u0006\u0010g\u001a\u00020$J3\u0010j\u001a\u00020\b2\u0006\u0010k\u001a\u00020)2\f\u0010l\u001a\b\u0012\u0004\u0012\u00020)0Y2\u000e\u0010m\u001a\n\u0012\u0004\u0012\u00020)\u0018\u00010YH\u0002¢\u0006\u0002\u0010nJ9\u0010j\u001a\u00020\b2\f\u0010o\u001a\b\u0012\u0004\u0012\u00020)0Y2\f\u0010l\u001a\b\u0012\u0004\u0012\u00020)0Y2\u000e\u0010m\u001a\n\u0012\u0004\u0012\u00020)\u0018\u00010YH\u0002¢\u0006\u0002\u0010pJ\u0019\u0010q\u001a\u00020\b2\f\u0010o\u001a\b\u0012\u0004\u0012\u00020)0Y¢\u0006\u0002\u0010rJ'\u0010q\u001a\u00020\b2\u0006\u0010k\u001a\u00020)2\u0010\b\u0002\u0010s\u001a\n\u0012\u0004\u0012\u00020)\u0018\u00010YH\u0007¢\u0006\u0002\u0010tJ\u000e\u0010u\u001a\u00020\b2\u0006\u0010k\u001a\u00020)J\u000e\u0010v\u001a\u00020\b2\u0006\u0010k\u001a\u00020)J\u000e\u0010w\u001a\u00020\b2\u0006\u0010k\u001a\u00020)J\u000e\u0010x\u001a\u00020\b2\u0006\u0010k\u001a\u00020)J\u0019\u0010y\u001a\u00020\b2\f\u0010z\u001a\b\u0012\u0004\u0012\u00020)0Y¢\u0006\u0002\u0010rJ\u0010\u0010{\u001a\u00020$2\b\u0010\t\u001a\u0004\u0018\u00010\nJ\b\u0010|\u001a\u0004\u0018\u00010\nJ\b\u0010}\u001a\u0004\u0018\u00010\fJ\u0010\u0010~\u001a\u00020$2\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\u0006\u0010\u007f\u001a\u00020$J\u000f\u0010\u0080\u0001\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010/J\u0010\u0010\u0081\u0001\u001a\u00020$2\u0007\u0010\u0082\u0001\u001a\u00020\u0017J\u0012\u0010\u0083\u0001\u001a\u00020$2\t\u0010\u0084\u0001\u001a\u0004\u0018\u00010)J\u0014\u0010\u0083\u0001\u001a\u00020$2\t\b\u0002\u0010\u0085\u0001\u001a\u00020\bH\u0007J\u0010\u0010\u0086\u0001\u001a\u00020$2\u0007\u0010\u0087\u0001\u001a\u00020)J\u000b\u0010\u0088\u0001\u001a\u0004\u0018\u00010\nH\u0002J\u0012\u0010\u0089\u0001\u001a\u00030\u008a\u00012\b\u0010J\u001a\u0004\u0018\u00010\nJ\t\u0010\u008b\u0001\u001a\u0004\u0018\u00010\nJ\u0010\u0010\u008c\u0001\u001a\u00020$2\u0007\u0010\u008d\u0001\u001a\u00020\nJ\u001a\u0010\u008e\u0001\u001a\u00020$2\u0007\u0010\u008d\u0001\u001a\u00020\n2\b\u0010\u008f\u0001\u001a\u00030\u008a\u0001J\u0010\u0010\u0090\u0001\u001a\u00020$2\u0007\u0010\u008d\u0001\u001a\u00020\nJ\u0007\u0010\u0091\u0001\u001a\u00020$J\u0007\u0010\u0092\u0001\u001a\u00020$J\u000f\u0010\u0093\u0001\u001a\u00020$2\u0006\u0010J\u001a\u00020\nJ\u000f\u0010\u0094\u0001\u001a\u00020\b2\u0006\u0010J\u001a\u00020\nJ\u0014\u0010\u0095\u0001\u001a\u0004\u0018\u00010\n2\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010)J\u0018\u0010\u0097\u0001\u001a\u00020$2\u0006\u0010e\u001a\u00020\n2\u0007\u0010\u008d\u0001\u001a\u00020\nJ\u0007\u0010\u0098\u0001\u001a\u00020$J\u0010\u0010\u0099\u0001\u001a\u00020$2\u0007\u0010\u009a\u0001\u001a\u000200J\u000f\u0010\u009b\u0001\u001a\u00020$2\u0006\u0010\u0004\u001a\u00020\u0005J\t\u0010\u009c\u0001\u001a\u0004\u0018\u00010\u0005J\b\u0010\u009d\u0001\u001a\u00030\u008a\u0001J\t\u0010\u009e\u0001\u001a\u0004\u0018\u00010\u0005J\u000b\u0010\u009f\u0001\u001a\u00020)H\u0096\u0080\u0004R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000RF\u0010\u0011\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\n0\u000fj\n\u0012\u0006\u0012\u0004\u0018\u00010\n`\u00102\u001a\u0010\u000e\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\n0\u000fj\n\u0012\u0006\u0012\u0004\u0018\u00010\n`\u0010@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\"\u0010\u0014\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000fj\n\u0012\u0004\u0012\u00020\u0005\u0018\u0001`\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001e\u0010\u001f\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001cR\u0011\u00107\u001a\u0002088F¢\u0006\u0006\u001a\u0004\b9\u0010:R\u0016\u0010h\u001a\b\u0012\u0004\u0012\u00020)0YX\u0082\u0004¢\u0006\u0004\n\u0002\u0010i¨\u0006¡\u0001"}, d2 = {"Lcom/fleeksoft/ksoup/parser/HtmlTreeBuilder;", "Lcom/fleeksoft/ksoup/parser/TreeBuilder;", "<init>", "()V", "state", "Lcom/fleeksoft/ksoup/parser/HtmlTreeBuilderState;", "originalState", "baseUriSetFromDoc", "", "headElement", "Lcom/fleeksoft/ksoup/nodes/Element;", "formElement", "Lcom/fleeksoft/ksoup/nodes/FormElement;", "contextElement", ES6Iterator.VALUE_PROPERTY, "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "formattingElements", "getFormattingElements", "()Ljava/util/ArrayList;", "tmplInsertMode", "pendingTableCharacters", "", "Lcom/fleeksoft/ksoup/parser/Token$Character;", "emptyEnd", "Lcom/fleeksoft/ksoup/parser/Token$EndTag;", "framesetOk", "isFosterInserts", "()Z", "setFosterInserts", "(Z)V", "isFragmentParsing", "defaultSettings", "Lcom/fleeksoft/ksoup/parser/ParseSettings;", "newInstance", "initialiseParse", "", TvContractCompat.PARAM_INPUT, "Ljava/io/Reader;", "Lcom/fleeksoft/io/Reader;", "baseUri", "", "parser", "Lcom/fleeksoft/ksoup/parser/Parser;", "initialiseParseFragment", "context", "completeParseFragment", "", "Lcom/fleeksoft/ksoup/nodes/Node;", "process", "token", "Lcom/fleeksoft/ksoup/parser/Token;", "useCurrentOrForeignInsert", "transition", "markInsertionMode", "document", "Lcom/fleeksoft/ksoup/nodes/Document;", "getDocument", "()Lcom/fleeksoft/ksoup/nodes/Document;", "maybeSetBaseUri", TtmlNode.RUBY_BASE, MediaRouteProviderProtocol.SERVICE_DATA_ERROR, "createElementFor", "startTag", "Lcom/fleeksoft/ksoup/parser/Token$StartTag;", "namespace", "forcePreserveCase", "insertElementFor", "insertForeignElementFor", "insertEmptyElementFor", "insertFormElement", "onStack", "checkTemplateStack", "doInsertElement", "el", "insertCommentNode", "Lcom/fleeksoft/ksoup/parser/Token$Comment;", "insertCharacterNode", "characterToken", "replace", "insertCharacterToElement", "elName", "getFromStack", "removeFromStack", "onStackPrunedForDepth", "element", "popStackToClose", "popStackToCloseAnyNamespace", "elNames", "", "([Ljava/lang/String;)V", "clearStackToTableContext", "clearStackToTableBodyContext", "clearStackToTableRowContext", "clearStackToContext", "nodeNames", "aboveOnStack", "insertOnStackAfter", TtmlNode.ANNOTATION_POSITION_AFTER, "inEl", "replaceOnStack", "out", "resetInsertionMode", "resetBody", "specificScopeTarget", "[Ljava/lang/String;", "inSpecificScope", "targetName", "baseTypes", "extraTypes", "(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;)Z", "targetNames", "([Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;)Z", "inScope", "([Ljava/lang/String;)Z", "extras", "(Ljava/lang/String;[Ljava/lang/String;)Z", "inListItemScope", "inButtonScope", "inTableScope", "inSelectScope", "onStackNot", "allowedTags", "setHeadElement", "getHeadElement", "getFormElement", "setFormElement", "resetPendingTableCharacters", "getPendingTableCharacters", "addPendingTableCharacters", "c", "generateImpliedEndTags", "excludeTag", "thorough", "closeElement", "name", "lastFormattingElement", "positionOfElement", "", "removeLastFormattingElement", "pushActiveFormattingElements", "in", "pushWithBookmark", "bookmark", "checkActiveFormattingElements", "reconstructFormattingElements", "clearFormattingElementsToLastMarker", "removeFromActiveFormattingElements", "isInActiveFormattingElements", "getActiveFormattingElement", "nodeName", "replaceActiveFormattingElement", "insertMarkerToFormattingElements", "insertInFosterParent", "inNode", "pushTemplateMode", "popTemplateMode", "templateModeSize", "currentTemplateMode", "toString", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class HtmlTreeBuilder extends TreeBuilder {
    public static final int MaxScopeSearchDepth = 100;
    private static final int maxQueueDepth = 256;
    private static final int maxUsedFormattingElements = 12;
    private boolean baseUriSetFromDoc;
    private Element contextElement;
    private Token.EndTag emptyEnd;
    private FormElement formElement;
    private boolean framesetOk;
    private Element headElement;
    private boolean isFosterInserts;
    private boolean isFragmentParsing;
    private HtmlTreeBuilderState originalState;
    private List<Token.Character> pendingTableCharacters;
    private HtmlTreeBuilderState state;
    private ArrayList<HtmlTreeBuilderState> tmplInsertMode;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String[] TagsSearchInScope = {"applet", MediaTrack.ROLE_CAPTION, "html", "marquee", "object", "table", "td", "template", "th"};
    private static final String[] TagSearchInScopeMath = {"annotation-xml", "mi", "mn", "mo", "ms", "mtext"};
    private static final String[] TagSearchInScopeSvg = {"desc", "foreignobject", "title"};
    private static final String[] TagSearchList = {"ol", "ul"};
    private static final String[] TagSearchButton = {"button"};
    private static final String[] TagSearchTableScope = {"html", "table"};
    private static final String[] TagSearchSelectScope = {"optgroup", "option"};
    private static final String[] TagSearchEndTags = {"dd", "dt", "li", "optgroup", "option", TtmlNode.TAG_P, "rb", "rp", "rt", "rtc"};
    private static final String[] TagThoroughSearchEndTags = {MediaTrack.ROLE_CAPTION, "colgroup", "dd", "dt", "li", "optgroup", "option", TtmlNode.TAG_P, "rb", "rp", "rt", "rtc", "tbody", "td", "tfoot", "th", "thead", "tr"};
    private static final String[] TagSearchSpecial = {"address", "applet", "area", "article", "aside", TtmlNode.RUBY_BASE, "basefont", "bgsound", "blockquote", TtmlNode.TAG_BODY, "br", "button", MediaTrack.ROLE_CAPTION, TtmlNode.CENTER, "col", "colgroup", "dd", "details", "dir", TtmlNode.TAG_DIV, CmcdConfiguration.KEY_DEADLINE, "dt", "embed", "fieldset", "figcaption", "figure", "footer", "form", TypedValues.AttributesType.S_FRAME, "frameset", "h1", "h2", "h3", "h4", "h5", "h6", TtmlNode.TAG_HEAD, "header", "hgroup", "hr", "html", "iframe", "img", TvContractCompat.PARAM_INPUT, "keygen", "li", "link", "listing", MediaTrack.ROLE_MAIN, "marquee", "menu", "meta", "nav", "noembed", "noframes", "noscript", "object", "ol", TtmlNode.TAG_P, "param", "plaintext", "pre", "script", "search", "section", "select", "source", "style", "summary", "table", "tbody", "td", "template", "textarea", "tfoot", "th", "thead", "title", "tr", "track", "ul", "wbr", "xmp"};
    private static String[] TagSearchSpecialMath = {"annotation-xml", "mi", "mn", "mo", "ms", "mtext"};
    private static final String[] TagMathMlTextIntegration = {"mi", "mn", "mo", "ms", "mtext"};
    private static final String[] TagSvgHtmlIntegration = {"desc", "foreignObject", "title"};
    private static final String[] TagFormListed = {"button", "fieldset", TvContractCompat.PARAM_INPUT, "keygen", "object", "output", "select", "textarea"};
    private ArrayList<Element> formattingElements = new ArrayList<>();
    private final String[] specificScopeTarget = {""};

    public final void generateImpliedEndTags() {
        generateImpliedEndTags$default(this, false, 1, null);
    }

    public final boolean inScope(String targetName) {
        Intrinsics.checkNotNullParameter(targetName, "targetName");
        return inScope$default(this, targetName, null, 2, null);
    }

    public final ArrayList<Element> getFormattingElements() {
        return this.formattingElements;
    }

    /* JADX INFO: renamed from: isFosterInserts, reason: from getter */
    public final boolean getIsFosterInserts() {
        return this.isFosterInserts;
    }

    public final void setFosterInserts(boolean z) {
        this.isFosterInserts = z;
    }

    /* JADX INFO: renamed from: isFragmentParsing, reason: from getter */
    public final boolean getIsFragmentParsing() {
        return this.isFragmentParsing;
    }

    @Override // com.fleeksoft.ksoup.parser.TreeBuilder
    public ParseSettings defaultSettings() {
        return ParseSettings.INSTANCE.getHtmlDefault();
    }

    @Override // com.fleeksoft.ksoup.parser.TreeBuilder
    public HtmlTreeBuilder newInstance() {
        return new HtmlTreeBuilder();
    }

    @Override // com.fleeksoft.ksoup.parser.TreeBuilder
    public void initialiseParse(Reader input, String baseUri, Parser parser) {
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(baseUri, "baseUri");
        Intrinsics.checkNotNullParameter(parser, "parser");
        super.initialiseParse(input, baseUri, parser);
        this.state = HtmlTreeBuilderState.Initial;
        this.originalState = null;
        this.baseUriSetFromDoc = false;
        this.headElement = null;
        this.formElement = null;
        this.contextElement = null;
        this.tmplInsertMode = new ArrayList<>();
        this.pendingTableCharacters = new ArrayList();
        this.emptyEnd = new Token.EndTag(this);
        this.framesetOk = true;
        this.isFosterInserts = false;
        this.isFragmentParsing = false;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0086  */
    /* JADX WARN: Code duplicated, block: B:25:0x0095  */
    /* JADX WARN: Code duplicated, block: B:26:0x00a0  */
    @Override // com.fleeksoft.ksoup.parser.TreeBuilder
    public void initialiseParseFragment(Element context) {
        TokeniserState tokeniserStateTextState;
        this.state = HtmlTreeBuilderState.Initial;
        this.isFragmentParsing = true;
        if (context != null) {
            String strNormalName = context.normalName();
            String strDefaultNamespace = defaultNamespace();
            ParseSettings settings = getSettings();
            Intrinsics.checkNotNull(settings);
            this.contextElement = new Element(tagFor(strNormalName, strNormalName, strDefaultNamespace, settings), getBaseUri());
            if (context.ownerDocument() != null) {
                Document doc = getDoc();
                Document documentOwnerDocument = context.ownerDocument();
                Intrinsics.checkNotNull(documentOwnerDocument);
                doc.quirksMode(documentOwnerDocument.getQuirksMode());
            }
            int iHashCode = strNormalName.hashCode();
            if (iHashCode != -1321546630) {
                if (iHashCode != -907685685) {
                    if (iHashCode == 1973234167 && strNormalName.equals("plaintext")) {
                        Tokeniser tokeniser = getTokeniser();
                        Intrinsics.checkNotNull(tokeniser);
                        tokeniser.transition(TokeniserState.PLAINTEXT);
                    } else {
                        Element element = this.contextElement;
                        Intrinsics.checkNotNull(element);
                        tokeniserStateTextState = element.tag().textState();
                        if (tokeniserStateTextState != null) {
                            Tokeniser tokeniser2 = getTokeniser();
                            Intrinsics.checkNotNull(tokeniser2);
                            tokeniser2.transition(tokeniserStateTextState);
                        } else {
                            Tokeniser tokeniser3 = getTokeniser();
                            Intrinsics.checkNotNull(tokeniser3);
                            tokeniser3.transition(TokeniserState.Data);
                        }
                    }
                } else if (strNormalName.equals("script")) {
                    Tokeniser tokeniser4 = getTokeniser();
                    Intrinsics.checkNotNull(tokeniser4);
                    tokeniser4.transition(TokeniserState.ScriptData);
                } else {
                    Element element2 = this.contextElement;
                    Intrinsics.checkNotNull(element2);
                    tokeniserStateTextState = element2.tag().textState();
                    if (tokeniserStateTextState != null) {
                        Tokeniser tokeniser5 = getTokeniser();
                        Intrinsics.checkNotNull(tokeniser5);
                        tokeniser5.transition(tokeniserStateTextState);
                    } else {
                        Tokeniser tokeniser6 = getTokeniser();
                        Intrinsics.checkNotNull(tokeniser6);
                        tokeniser6.transition(TokeniserState.Data);
                    }
                }
            } else if (strNormalName.equals("template")) {
                Tokeniser tokeniser7 = getTokeniser();
                if (tokeniser7 != null) {
                    tokeniser7.transition(TokeniserState.Data);
                }
                pushTemplateMode(HtmlTreeBuilderState.InTemplate);
            } else {
                Element element3 = this.contextElement;
                Intrinsics.checkNotNull(element3);
                tokeniserStateTextState = element3.tag().textState();
                if (tokeniserStateTextState != null) {
                    Tokeniser tokeniser8 = getTokeniser();
                    Intrinsics.checkNotNull(tokeniser8);
                    tokeniser8.transition(tokeniserStateTextState);
                } else {
                    Tokeniser tokeniser9 = getTokeniser();
                    Intrinsics.checkNotNull(tokeniser9);
                    tokeniser9.transition(TokeniserState.Data);
                }
            }
            Document doc2 = getDoc();
            Element element4 = this.contextElement;
            Intrinsics.checkNotNull(element4);
            doc2.appendChild(element4);
            Element element5 = this.contextElement;
            Intrinsics.checkNotNull(element5);
            push(element5);
            resetInsertionMode();
            while (context != null) {
                if (context instanceof FormElement) {
                    this.formElement = (FormElement) context;
                    return;
                }
                context = context.parent();
            }
        }
    }

    @Override // com.fleeksoft.ksoup.parser.TreeBuilder
    public List<Node> completeParseFragment() {
        Element element = this.contextElement;
        if (element != null) {
            Intrinsics.checkNotNull(element);
            List<Node> listSiblingNodes = element.siblingNodes();
            if (!listSiblingNodes.isEmpty()) {
                Element element2 = this.contextElement;
                Intrinsics.checkNotNull(element2);
                element2.insertChildren(-1, listSiblingNodes);
            }
            Element element3 = this.contextElement;
            Intrinsics.checkNotNull(element3);
            return element3.childNodes();
        }
        return getDoc().childNodes();
    }

    @Override // com.fleeksoft.ksoup.parser.TreeBuilder
    public boolean process(Token token) {
        Intrinsics.checkNotNullParameter(token, "token");
        HtmlTreeBuilderState htmlTreeBuilderState = useCurrentOrForeignInsert(token) ? this.state : HtmlTreeBuilderState.ForeignContent;
        Intrinsics.checkNotNull(htmlTreeBuilderState);
        return htmlTreeBuilderState.process(token, this);
    }

    private final boolean useCurrentOrForeignInsert(Token token) {
        if (getStack().isEmpty()) {
            return true;
        }
        Element elementCurrentElement = currentElement();
        String strNamespace = elementCurrentElement.tag().namespace();
        if (Intrinsics.areEqual("http://www.w3.org/1999/xhtml", strNamespace)) {
            return true;
        }
        Companion companion = INSTANCE;
        if (companion.isMathmlTextIntegration(elementCurrentElement) && ((token.isStartTag() && !Intrinsics.areEqual("mglyph", token.asStartTag().getNormalName()) && !Intrinsics.areEqual("malignmark", token.asStartTag().getNormalName())) || token.isCharacter())) {
            return true;
        }
        if (Intrinsics.areEqual("http://www.w3.org/1998/Math/MathML", strNamespace) && elementCurrentElement.nameIs("annotation-xml") && token.isStartTag() && Intrinsics.areEqual("svg", token.asStartTag().getNormalName())) {
            return true;
        }
        if (companion.isHtmlIntegration(elementCurrentElement) && (token.isStartTag() || token.isCharacter())) {
            return true;
        }
        return token.isEOF();
    }

    public final boolean process(Token token, HtmlTreeBuilderState state) {
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(state, "state");
        return state.process(token, this);
    }

    public final void transition(HtmlTreeBuilderState state) {
        this.state = state;
    }

    /* JADX INFO: renamed from: state, reason: from getter */
    public final HtmlTreeBuilderState getState() {
        return this.state;
    }

    public final void markInsertionMode() {
        this.originalState = this.state;
    }

    /* JADX INFO: renamed from: originalState, reason: from getter */
    public final HtmlTreeBuilderState getOriginalState() {
        return this.originalState;
    }

    public final void framesetOk(boolean framesetOk) {
        this.framesetOk = framesetOk;
    }

    /* JADX INFO: renamed from: framesetOk, reason: from getter */
    public final boolean getFramesetOk() {
        return this.framesetOk;
    }

    public final Document getDocument() {
        return getDoc();
    }

    public final void maybeSetBaseUri(Element base) {
        Intrinsics.checkNotNullParameter(base, "base");
        if (this.baseUriSetFromDoc) {
            return;
        }
        String strAbsUrl = base.absUrl("href");
        if (strAbsUrl.length() > 0) {
            setBaseUri(strAbsUrl);
            this.baseUriSetFromDoc = true;
            getDoc().setBaseUri(strAbsUrl);
        }
    }

    public final void error(HtmlTreeBuilderState state) {
        if (getParser().getErrors().canAddError()) {
            ParseErrorList errors = getParser().getErrors();
            CharacterReader reader = getReader();
            Token currentToken = getCurrentToken();
            Intrinsics.checkNotNull(currentToken);
            errors.add(new ParseError(reader, "Unexpected " + currentToken.tokenType() + " token [" + getCurrentToken() + "] when in state [" + state + "]"));
        }
    }

    public final Element createElementFor(Token.StartTag startTag, String namespace, boolean forcePreserveCase) {
        Intrinsics.checkNotNullParameter(startTag, "startTag");
        Intrinsics.checkNotNullParameter(namespace, "namespace");
        Attributes attributes = startTag.getAttributes();
        if (attributes != null && !attributes.isEmpty()) {
            if (!forcePreserveCase) {
                getSettings().normalizeAttributes(attributes);
            }
            if (attributes.deduplicate(getSettings()) > 0) {
                error("Dropped duplicate attribute(s) in tag [" + startTag.getNormalName() + "]");
            }
        }
        String strName = startTag.name();
        String normalName$ksoup_release = startTag.getNormalName();
        Intrinsics.checkNotNull(normalName$ksoup_release);
        ParseSettings preserveCase = forcePreserveCase ? ParseSettings.INSTANCE.getPreserveCase() : getSettings();
        Intrinsics.checkNotNull(preserveCase);
        Tag tagTagFor = tagFor(strName, normalName$ksoup_release, namespace, preserveCase);
        if (Intrinsics.areEqual(tagTagFor.normalName(), "form")) {
            return new FormElement(tagTagFor, null, attributes);
        }
        return new Element(tagTagFor, null, attributes);
    }

    public final Element insertElementFor(Token.StartTag startTag) {
        Intrinsics.checkNotNullParameter(startTag, "startTag");
        Element elementCreateElementFor = createElementFor(startTag, "http://www.w3.org/1999/xhtml", false);
        doInsertElement(elementCreateElementFor);
        if (startTag.getSelfClosing()) {
            Tag tag = elementCreateElementFor.tag();
            tag.setSeenSelfClose();
            if (!tag.isEmpty()) {
                if (tag.isKnownTag() && tag.isSelfClosing()) {
                    Tokeniser tokeniser = getTokeniser();
                    Intrinsics.checkNotNull(tokeniser);
                    tokeniser.transition(TokeniserState.Data);
                    Tokeniser tokeniser2 = getTokeniser();
                    Intrinsics.checkNotNull(tokeniser2);
                    Token.EndTag endTag = this.emptyEnd;
                    Intrinsics.checkNotNull(endTag);
                    tokeniser2.emit(endTag.reset().name(elementCreateElementFor.tagName()));
                } else {
                    Tokeniser tokeniser3 = getTokeniser();
                    Intrinsics.checkNotNull(tokeniser3);
                    tokeniser3.error("Tag [" + tag.normalName() + "] cannot be self-closing; not a void tag");
                }
            }
        }
        if (elementCreateElementFor.tag().isEmpty()) {
            pop();
        }
        return elementCreateElementFor;
    }

    public final Element insertForeignElementFor(Token.StartTag startTag, String namespace) {
        Intrinsics.checkNotNullParameter(startTag, "startTag");
        Intrinsics.checkNotNullParameter(namespace, "namespace");
        Element elementCreateElementFor = createElementFor(startTag, namespace, true);
        doInsertElement(elementCreateElementFor);
        if (startTag.getSelfClosing()) {
            elementCreateElementFor.tag().setSeenSelfClose();
            pop();
        }
        return elementCreateElementFor;
    }

    public final Element insertEmptyElementFor(Token.StartTag startTag) {
        Intrinsics.checkNotNullParameter(startTag, "startTag");
        Element elementCreateElementFor = createElementFor(startTag, "http://www.w3.org/1999/xhtml", false);
        doInsertElement(elementCreateElementFor);
        pop();
        return elementCreateElementFor;
    }

    public final FormElement insertFormElement(Token.StartTag startTag, boolean onStack, boolean checkTemplateStack) {
        Intrinsics.checkNotNullParameter(startTag, "startTag");
        Element elementCreateElementFor = createElementFor(startTag, "http://www.w3.org/1999/xhtml", false);
        Intrinsics.checkNotNull(elementCreateElementFor, "null cannot be cast to non-null type com.fleeksoft.ksoup.nodes.FormElement");
        FormElement formElement = (FormElement) elementCreateElementFor;
        if (!checkTemplateStack || !onStack("template")) {
            setFormElement(formElement);
        }
        doInsertElement(formElement);
        if (!onStack) {
            pop();
        }
        return formElement;
    }

    private final void doInsertElement(Element el) {
        enforceStackDepthLimit();
        if (this.formElement != null && Intrinsics.areEqual(el.tag().namespace(), "http://www.w3.org/1999/xhtml") && StringUtil.INSTANCE.inSorted(el.normalName(), TagFormListed)) {
            FormElement formElement = this.formElement;
            Intrinsics.checkNotNull(formElement);
            formElement.addElement(el);
        }
        if (getParser().getErrors().canAddError() && el.hasAttr(XmlTreeBuilder.XmlnsKey) && !Intrinsics.areEqual(el.attr(XmlTreeBuilder.XmlnsKey), el.tag().namespace())) {
            error("Invalid xmlns attribute [" + el.attr(XmlTreeBuilder.XmlnsKey) + "] on tag [" + el.tagName() + "]");
        }
        if (this.isFosterInserts && StringUtil.INSTANCE.inSorted(currentElement().normalName(), HtmlTreeBuilderState.Constants.INSTANCE.getInTableFoster())) {
            insertInFosterParent(el);
        } else {
            currentElement().appendChild(el);
        }
        push(el);
    }

    public final void insertCommentNode(Token.Comment token) {
        Intrinsics.checkNotNullParameter(token, "token");
        Comment comment = new Comment(token.getData());
        currentElement().appendChild(comment);
        onNodeInserted(comment);
    }

    public final void insertCharacterNode(Token.Character characterToken) {
        Intrinsics.checkNotNullParameter(characterToken, "characterToken");
        insertCharacterNode(characterToken, false);
    }

    public final void insertCharacterNode(Token.Character characterToken, boolean replace) {
        Intrinsics.checkNotNullParameter(characterToken, "characterToken");
        characterToken.normalizeNulls(replace);
        insertCharacterToElement(characterToken, currentElement());
    }

    public final void insertCharacterToElement(Token.Character characterToken, Element el) {
        TextNode textNode;
        Intrinsics.checkNotNullParameter(characterToken, "characterToken");
        Intrinsics.checkNotNullParameter(el, "el");
        String strM8177getData = characterToken.m8177getData();
        if (characterToken.isCData()) {
            textNode = new CDataNode(strM8177getData);
        } else if (el.tag().is(256)) {
            textNode = new DataNode(strM8177getData);
        } else {
            textNode = new TextNode(strM8177getData);
        }
        el.appendChild(textNode);
        onNodeInserted(textNode);
    }

    public final boolean onStack(Element el) {
        Intrinsics.checkNotNullParameter(el, "el");
        return INSTANCE.onStack(getStack(), el);
    }

    public final boolean onStack(String elName) {
        return getFromStack(elName) != null;
    }

    public final Element getFromStack(String elName) {
        int size = getStack().size();
        int i = size - 1;
        int i2 = i >= 256 ? size - 257 : 0;
        if (i2 > i) {
            return null;
        }
        while (true) {
            Element element = getStack().get(i);
            if (element != null && element.elementIs(elName, "http://www.w3.org/1999/xhtml")) {
                return element;
            }
            if (i == i2) {
                return null;
            }
            i--;
        }
    }

    public final boolean removeFromStack(Element el) {
        Intrinsics.checkNotNullParameter(el, "el");
        for (int size = getStack().size() - 1; -1 < size; size--) {
            Element element = getStack().get(size);
            Intrinsics.checkNotNull(element);
            if (element == el) {
                getStack().remove(size);
                onNodeClosed(el);
                return true;
            }
        }
        return false;
    }

    @Override // com.fleeksoft.ksoup.parser.TreeBuilder
    public void onStackPrunedForDepth(Element element) {
        Intrinsics.checkNotNullParameter(element, "element");
        if (element == this.headElement) {
            this.headElement = null;
        }
        if (element == this.formElement) {
            setFormElement(null);
        }
        removeFromActiveFormattingElements(element);
        if (element.nameIs("template")) {
            clearFormattingElementsToLastMarker();
            if (templateModeSize() > 0) {
                popTemplateMode();
            }
            resetInsertionMode();
        }
    }

    public final Element popStackToClose(String elName) {
        Intrinsics.checkNotNullParameter(elName, "elName");
        for (int size = getStack().size() - 1; -1 < size; size--) {
            Element elementPop = pop();
            if (elementPop != null && elementPop.elementIs(elName, "http://www.w3.org/1999/xhtml")) {
                return elementPop;
            }
        }
        return null;
    }

    public final Element popStackToCloseAnyNamespace(String elName) {
        Intrinsics.checkNotNullParameter(elName, "elName");
        for (int size = getStack().size() - 1; -1 < size; size--) {
            Element elementPop = pop();
            if (elementPop != null && elementPop.nameIs(elName)) {
                return elementPop;
            }
        }
        return null;
    }

    public final void popStackToClose(String... elNames) {
        Intrinsics.checkNotNullParameter(elNames, "elNames");
        int size = getStack().size();
        while (true) {
            size--;
            if (-1 >= size) {
                return;
            }
            Element elementPop = pop();
            Intrinsics.checkNotNull(elementPop);
            if (StringUtil.INSTANCE.inSorted(elementPop.normalName(), elNames) && Intrinsics.areEqual("http://www.w3.org/1999/xhtml", elementPop.tag().namespace())) {
                return;
            }
        }
    }

    public final void clearStackToTableContext() {
        clearStackToContext("table", "template");
    }

    public final void clearStackToTableBodyContext() {
        clearStackToContext("tbody", "tfoot", "thead", "template");
    }

    public final void clearStackToTableRowContext() {
        clearStackToContext("tr", "template");
    }

    private final void clearStackToContext(String... nodeNames) {
        Tag tag;
        int size = getStack().size();
        while (true) {
            size--;
            if (-1 >= size) {
                return;
            }
            Element element = getStack().get(size);
            if (Intrinsics.areEqual("http://www.w3.org/1999/xhtml", (element == null || (tag = element.tag()) == null) ? null : tag.namespace()) && (StringUtil.INSTANCE.isIn(element.normalName(), (String[]) Arrays.copyOf(nodeNames, nodeNames.length)) || element.nameIs("html"))) {
                return;
            } else {
                pop();
            }
        }
    }

    public final Element aboveOnStack(Element el) {
        Intrinsics.checkNotNullParameter(el, "el");
        if (!onStack(el)) {
            return null;
        }
        for (int size = getStack().size() - 1; size > 0; size--) {
            if (getStack().get(size) == el) {
                return getStack().get(size - 1);
            }
        }
        return null;
    }

    public final void insertOnStackAfter(Element after, Element inEl) {
        Intrinsics.checkNotNullParameter(after, "after");
        Intrinsics.checkNotNullParameter(inEl, "inEl");
        ArrayList<Element> arrayList = get_stack();
        Intrinsics.checkNotNull(arrayList);
        int iLastIndexOf = arrayList.lastIndexOf(after);
        if (iLastIndexOf == -1) {
            error("Did not find element on stack to insert after");
            ArrayList<Element> arrayList2 = get_stack();
            if (arrayList2 != null) {
                arrayList2.add(inEl);
                return;
            }
            return;
        }
        ArrayList<Element> arrayList3 = get_stack();
        if (arrayList3 != null) {
            arrayList3.add(iLastIndexOf + 1, inEl);
        }
    }

    public final void replaceOnStack(Element out, Element inEl) {
        Intrinsics.checkNotNullParameter(out, "out");
        Intrinsics.checkNotNullParameter(inEl, "inEl");
        INSTANCE.replaceInQueue(getStack(), out, inEl);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:112:0x010b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:117:0x015f A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:118:0x016b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:73:0x0109 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:95:0x015d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:97:0x0165 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:98:0x0167 A[LOOP:0: B:12:0x0028->B:98:0x0167, LOOP_END] */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final boolean resetInsertionMode() {
        String strNormalName;
        int size = getStack().size();
        int i = size - 1;
        int i2 = i >= 256 ? size - 257 : 0;
        HtmlTreeBuilderState htmlTreeBuilderState = this.state;
        if (getStack().size() == 0) {
            transition(HtmlTreeBuilderState.InBody);
        }
        if (i2 <= i) {
            boolean z = false;
            while (true) {
                Element element = getStack().get(i);
                if (i == i2) {
                    if (this.isFragmentParsing) {
                        element = this.contextElement;
                    }
                    z = true;
                }
                if (element == null || (strNormalName = element.normalName()) == null) {
                    strNormalName = "";
                }
                Intrinsics.checkNotNull(element);
                if (Intrinsics.areEqual("http://www.w3.org/1999/xhtml", element.tag().namespace())) {
                    switch (strNormalName.hashCode()) {
                        case -1644953643:
                            if (strNormalName.equals("frameset")) {
                                transition(HtmlTreeBuilderState.InFrameset);
                            } else if (z) {
                                transition(HtmlTreeBuilderState.InBody);
                            } else if (i != i2) {
                                i--;
                            }
                            break;
                        case -1321546630:
                            if (strNormalName.equals("template")) {
                                HtmlTreeBuilderState htmlTreeBuilderStateCurrentTemplateMode = currentTemplateMode();
                                if (htmlTreeBuilderStateCurrentTemplateMode == null) {
                                    throw new IllegalArgumentException("Bug: no template insertion mode on stack!".toString());
                                }
                                transition(htmlTreeBuilderStateCurrentTemplateMode);
                            } else if (z) {
                                transition(HtmlTreeBuilderState.InBody);
                            } else if (i != i2) {
                                i--;
                            }
                            break;
                        case -906021636:
                            if (strNormalName.equals("select")) {
                                transition(HtmlTreeBuilderState.InSelect);
                            } else if (z) {
                                transition(HtmlTreeBuilderState.InBody);
                            } else if (i != i2) {
                                i--;
                            }
                            break;
                        case -636197633:
                            if (strNormalName.equals("colgroup")) {
                                transition(HtmlTreeBuilderState.InColumnGroup);
                            } else if (z) {
                                transition(HtmlTreeBuilderState.InBody);
                            } else if (i != i2) {
                                i--;
                            }
                            break;
                        case 3696:
                            if (!strNormalName.equals("td")) {
                                if (z) {
                                    transition(HtmlTreeBuilderState.InBody);
                                } else if (i != i2) {
                                    i--;
                                }
                            } else if (!z) {
                                transition(HtmlTreeBuilderState.InCell);
                            } else if (z) {
                                transition(HtmlTreeBuilderState.InBody);
                            } else if (i != i2) {
                                i--;
                            }
                            break;
                        case 3700:
                            if (!strNormalName.equals("th")) {
                                if (z) {
                                    transition(HtmlTreeBuilderState.InBody);
                                } else if (i != i2) {
                                    i--;
                                }
                            } else if (!z) {
                                transition(HtmlTreeBuilderState.InCell);
                            } else if (z) {
                                transition(HtmlTreeBuilderState.InBody);
                            } else if (i != i2) {
                                i--;
                            }
                            break;
                        case 3710:
                            if (strNormalName.equals("tr")) {
                                transition(HtmlTreeBuilderState.InRow);
                            } else if (z) {
                                transition(HtmlTreeBuilderState.InBody);
                            } else if (i != i2) {
                                i--;
                            }
                            break;
                        case 3029410:
                            if (strNormalName.equals(TtmlNode.TAG_BODY)) {
                                transition(HtmlTreeBuilderState.InBody);
                            } else if (z) {
                                transition(HtmlTreeBuilderState.InBody);
                            } else if (i != i2) {
                                i--;
                            }
                            break;
                        case 3198432:
                            if (strNormalName.equals(TtmlNode.TAG_HEAD) && !z) {
                                transition(HtmlTreeBuilderState.InHead);
                            } else if (z) {
                                transition(HtmlTreeBuilderState.InBody);
                            } else if (i != i2) {
                                i--;
                            }
                            break;
                        case 3213227:
                            if (strNormalName.equals("html")) {
                                transition(this.headElement == null ? HtmlTreeBuilderState.BeforeHead : HtmlTreeBuilderState.AfterHead);
                            } else if (z) {
                                transition(HtmlTreeBuilderState.InBody);
                            } else if (i != i2) {
                                i--;
                            }
                            break;
                        case 110115790:
                            if (strNormalName.equals("table")) {
                                transition(HtmlTreeBuilderState.InTable);
                            } else if (z) {
                                transition(HtmlTreeBuilderState.InBody);
                            } else if (i != i2) {
                                i--;
                            }
                            break;
                        case 110157846:
                            if (strNormalName.equals("tbody")) {
                                transition(HtmlTreeBuilderState.InTableBody);
                            } else if (z) {
                                transition(HtmlTreeBuilderState.InBody);
                            } else if (i != i2) {
                                i--;
                            }
                            break;
                        case 110277346:
                            if (strNormalName.equals("tfoot")) {
                                transition(HtmlTreeBuilderState.InTableBody);
                            } else if (z) {
                                transition(HtmlTreeBuilderState.InBody);
                            } else if (i != i2) {
                                i--;
                            }
                            break;
                        case 110326868:
                            if (strNormalName.equals("thead")) {
                                transition(HtmlTreeBuilderState.InTableBody);
                            } else if (z) {
                                transition(HtmlTreeBuilderState.InBody);
                            } else if (i != i2) {
                                i--;
                            }
                            break;
                        case 552573414:
                            if (strNormalName.equals(MediaTrack.ROLE_CAPTION)) {
                                transition(HtmlTreeBuilderState.InCaption);
                            } else if (z) {
                                transition(HtmlTreeBuilderState.InBody);
                            } else if (i != i2) {
                                i--;
                            }
                            break;
                        default:
                            if (z) {
                                transition(HtmlTreeBuilderState.InBody);
                            } else if (i != i2) {
                                i--;
                            }
                            break;
                    }
                } else if (i != i2) {
                    i--;
                }
            }
        }
        return this.state != htmlTreeBuilderState;
    }

    public final void resetBody() {
        if (!onStack(TtmlNode.TAG_BODY)) {
            getStack().add(getDoc().body());
        }
        transition(HtmlTreeBuilderState.InBody);
    }

    private final boolean inSpecificScope(String targetName, String[] baseTypes, String[] extraTypes) {
        String[] strArr = this.specificScopeTarget;
        strArr[0] = targetName;
        return inSpecificScope(strArr, baseTypes, extraTypes);
    }

    private final boolean inSpecificScope(String[] targetNames, String[] baseTypes, String[] extraTypes) {
        ArrayList<Element> arrayList = get_stack();
        Intrinsics.checkNotNull(arrayList);
        for (int size = arrayList.size() - 1; -1 < size; size--) {
            ArrayList<Element> arrayList2 = get_stack();
            Intrinsics.checkNotNull(arrayList2);
            Element element = arrayList2.get(size);
            Intrinsics.checkNotNull(element);
            Element element2 = element;
            String strNormalName = element2.normalName();
            String strNamespace = element2.tag().namespace();
            if (Intrinsics.areEqual(strNamespace, "http://www.w3.org/1999/xhtml")) {
                if (StringUtil.INSTANCE.inSorted(strNormalName, targetNames)) {
                    return true;
                }
                if (StringUtil.INSTANCE.inSorted(strNormalName, baseTypes)) {
                    return false;
                }
                if (extraTypes != null && StringUtil.INSTANCE.inSorted(strNormalName, extraTypes)) {
                    return false;
                }
            } else if (!Arrays.equals(baseTypes, TagsSearchInScope)) {
                continue;
            } else {
                if (Intrinsics.areEqual(strNamespace, "http://www.w3.org/1998/Math/MathML") && StringUtil.INSTANCE.inSorted(strNormalName, TagSearchInScopeMath)) {
                    return false;
                }
                if (Intrinsics.areEqual(strNamespace, "http://www.w3.org/2000/svg") && StringUtil.INSTANCE.inSorted(strNormalName, TagSearchInScopeSvg)) {
                    return false;
                }
            }
        }
        return false;
    }

    public final boolean inScope(String[] targetNames) {
        Intrinsics.checkNotNullParameter(targetNames, "targetNames");
        return inSpecificScope(targetNames, TagsSearchInScope, (String[]) null);
    }

    public static /* synthetic */ boolean inScope$default(HtmlTreeBuilder htmlTreeBuilder, String str, String[] strArr, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: inScope");
        }
        if ((i & 2) != 0) {
            strArr = null;
        }
        return htmlTreeBuilder.inScope(str, strArr);
    }

    public final boolean inScope(String targetName, String[] extras) {
        Intrinsics.checkNotNullParameter(targetName, "targetName");
        return inSpecificScope(targetName, TagsSearchInScope, extras);
    }

    public final boolean inListItemScope(String targetName) {
        Intrinsics.checkNotNullParameter(targetName, "targetName");
        return inScope(targetName, TagSearchList);
    }

    public final boolean inButtonScope(String targetName) {
        Intrinsics.checkNotNullParameter(targetName, "targetName");
        return inScope(targetName, TagSearchButton);
    }

    public final boolean inTableScope(String targetName) {
        Intrinsics.checkNotNullParameter(targetName, "targetName");
        return inSpecificScope(targetName, TagSearchTableScope, (String[]) null);
    }

    public final boolean inSelectScope(String targetName) {
        Intrinsics.checkNotNullParameter(targetName, "targetName");
        for (int size = getStack().size() - 1; -1 < size; size--) {
            Element element = getStack().get(size);
            if (element != null) {
                String strNormalName = element.normalName();
                if (Intrinsics.areEqual(strNormalName, targetName)) {
                    return true;
                }
                if (!StringUtil.INSTANCE.inSorted(strNormalName, TagSearchSelectScope)) {
                    return false;
                }
            }
        }
        return false;
    }

    public final boolean onStackNot(String[] allowedTags) {
        Intrinsics.checkNotNullParameter(allowedTags, "allowedTags");
        ArrayList<Element> arrayList = get_stack();
        Intrinsics.checkNotNull(arrayList);
        for (int size = arrayList.size() - 1; -1 < size; size--) {
            ArrayList<Element> arrayList2 = get_stack();
            Intrinsics.checkNotNull(arrayList2);
            Element element = arrayList2.get(size);
            Intrinsics.checkNotNull(element);
            if (!StringUtil.INSTANCE.inSorted(element.normalName(), allowedTags)) {
                return true;
            }
        }
        return false;
    }

    public final void setHeadElement(Element headElement) {
        this.headElement = headElement;
    }

    public final Element getHeadElement() {
        return this.headElement;
    }

    public final FormElement getFormElement() {
        return this.formElement;
    }

    public final void setFormElement(FormElement formElement) {
        this.formElement = formElement;
    }

    public final void resetPendingTableCharacters() {
        List<Token.Character> list = this.pendingTableCharacters;
        if (list != null) {
            list.clear();
        }
    }

    public final List<Token.Character> getPendingTableCharacters() {
        return this.pendingTableCharacters;
    }

    public final void addPendingTableCharacters(Token.Character c) {
        Intrinsics.checkNotNullParameter(c, "c");
        Token.Character character = new Token.Character(c);
        List<Token.Character> list = this.pendingTableCharacters;
        Intrinsics.checkNotNull(list);
        list.add(character);
    }

    public final void generateImpliedEndTags(String excludeTag) {
        while (StringUtil.INSTANCE.inSorted(currentElement().normalName(), TagSearchEndTags)) {
            if (excludeTag != null && currentElementIs(excludeTag)) {
                return;
            } else {
                pop();
            }
        }
    }

    public static /* synthetic */ void generateImpliedEndTags$default(HtmlTreeBuilder htmlTreeBuilder, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: generateImpliedEndTags");
        }
        if ((i & 1) != 0) {
            z = false;
        }
        htmlTreeBuilder.generateImpliedEndTags(z);
    }

    public final void generateImpliedEndTags(boolean thorough) {
        String[] strArr = thorough ? TagThoroughSearchEndTags : TagSearchEndTags;
        while (Intrinsics.areEqual("http://www.w3.org/1999/xhtml", currentElement().tag().namespace()) && StringUtil.INSTANCE.inSorted(currentElement().normalName(), strArr)) {
            pop();
        }
    }

    public final void closeElement(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        generateImpliedEndTags(name);
        if (!Intrinsics.areEqual(name, currentElement().normalName())) {
            error(getState());
        }
        popStackToClose(name);
    }

    private final Element lastFormattingElement() {
        if (this.formattingElements.size() <= 0) {
            return null;
        }
        ArrayList<Element> arrayList = this.formattingElements;
        return arrayList.get(arrayList.size() - 1);
    }

    public final int positionOfElement(Element el) {
        int size = this.formattingElements.size();
        for (int i = 0; i < size; i++) {
            if (el == this.formattingElements.get(i)) {
                return i;
            }
        }
        return -1;
    }

    public final Element removeLastFormattingElement() {
        int size = this.formattingElements.size();
        if (size > 0) {
            return this.formattingElements.remove(size - 1);
        }
        return null;
    }

    public final void pushActiveFormattingElements(Element in) {
        Intrinsics.checkNotNullParameter(in, "in");
        checkActiveFormattingElements(in);
        this.formattingElements.add(in);
    }

    public final void pushWithBookmark(Element in, int bookmark) {
        Intrinsics.checkNotNullParameter(in, "in");
        checkActiveFormattingElements(in);
        try {
            this.formattingElements.add(bookmark, in);
        } catch (IndexOutOfBoundsException unused) {
            this.formattingElements.add(in);
        }
    }

    public final void checkActiveFormattingElements(Element in) {
        Intrinsics.checkNotNullParameter(in, "in");
        int size = this.formattingElements.size();
        int i = size - 1;
        int i2 = size - 13;
        int i3 = 0;
        if (i2 < 0) {
            i2 = 0;
        }
        if (i2 > i) {
            return;
        }
        while (true) {
            Element element = this.formattingElements.get(i);
            if (element == null) {
                return;
            }
            if (INSTANCE.isSameFormattingElement(in, element)) {
                i3++;
            }
            if (i3 == 3) {
                this.formattingElements.remove(i);
                return;
            } else if (i == i2) {
                return;
            } else {
                i--;
            }
        }
    }

    public final void reconstructFormattingElements() {
        Element elementLastFormattingElement;
        if (getStack().size() > 256 || (elementLastFormattingElement = lastFormattingElement()) == null || onStack(elementLastFormattingElement)) {
            return;
        }
        ArrayList<Element> arrayList = this.formattingElements;
        int size = arrayList != null ? arrayList.size() : 0;
        int i = size - 12;
        if (i < 0) {
            i = 0;
        }
        boolean z = true;
        int i2 = size - 1;
        int i3 = i2;
        while (i3 != i) {
            ArrayList<Element> arrayList2 = this.formattingElements;
            if (arrayList2 != null) {
                i3--;
                elementLastFormattingElement = arrayList2.get(i3);
            } else {
                elementLastFormattingElement = null;
            }
            if (elementLastFormattingElement == null || onStack(elementLastFormattingElement)) {
                z = false;
                break;
            }
        }
        while (true) {
            if (!z) {
                i3++;
                elementLastFormattingElement = this.formattingElements.get(i3);
            }
            Intrinsics.checkNotNull(elementLastFormattingElement);
            String strNodeName = elementLastFormattingElement.nodeName();
            String strNormalName = elementLastFormattingElement.normalName();
            String strDefaultNamespace = defaultNamespace();
            ParseSettings settings = getSettings();
            Intrinsics.checkNotNull(settings);
            Element element = new Element(tagFor(strNodeName, strNormalName, strDefaultNamespace, settings), null, elementLastFormattingElement.attributes().clone2());
            doInsertElement(element);
            this.formattingElements.set(i3, element);
            if (i3 == i2) {
                return;
            } else {
                z = false;
            }
        }
    }

    public final void clearFormattingElementsToLastMarker() {
        while (!this.formattingElements.isEmpty() && removeLastFormattingElement() != null) {
        }
    }

    public final void removeFromActiveFormattingElements(Element el) {
        Intrinsics.checkNotNullParameter(el, "el");
        int size = this.formattingElements.size() - 1;
        if (size < 0) {
            return;
        }
        while (true) {
            int i = size - 1;
            ArrayList<Element> arrayList = this.formattingElements;
            if ((arrayList != null ? arrayList.get(size) : null) == el) {
                this.formattingElements.remove(size);
                return;
            } else if (i < 0) {
                return;
            } else {
                size = i;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0034  */
    public final boolean isInActiveFormattingElements(Element el) {
        List listEmptyList;
        Intrinsics.checkNotNullParameter(el, "el");
        Companion companion = INSTANCE;
        ArrayList<Element> arrayList = this.formattingElements;
        if (arrayList != null) {
            ArrayList arrayList2 = new ArrayList();
            for (Element element : arrayList) {
                if (element != null) {
                    arrayList2.add(element);
                }
            }
            listEmptyList = CollectionsKt.toList(arrayList2);
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
        } else {
            listEmptyList = CollectionsKt.emptyList();
        }
        return companion.onStack(listEmptyList, el);
    }

    public final Element getActiveFormattingElement(String nodeName) {
        int size = this.formattingElements.size() - 1;
        if (size >= 0) {
            while (true) {
                int i = size - 1;
                ArrayList<Element> arrayList = this.formattingElements;
                Element element = arrayList != null ? arrayList.get(size) : null;
                if (element == null) {
                    break;
                }
                if (element.nameIs(nodeName)) {
                    return element;
                }
                if (i < 0) {
                    break;
                }
                size = i;
            }
        }
        return null;
    }

    public final void replaceActiveFormattingElement(Element out, Element in) {
        Intrinsics.checkNotNullParameter(out, "out");
        Intrinsics.checkNotNullParameter(in, "in");
        INSTANCE.replaceInQueue(this.formattingElements, out, in);
    }

    public final void insertMarkerToFormattingElements() {
        this.formattingElements.add(null);
    }

    public final void insertInFosterParent(Node inNode) {
        Element elementAboveOnStack;
        Intrinsics.checkNotNullParameter(inNode, "inNode");
        Element fromStack = getFromStack("table");
        boolean z = false;
        if (fromStack != null) {
            if (fromStack.parent() != null) {
                elementAboveOnStack = fromStack.parent();
                z = true;
            } else {
                elementAboveOnStack = aboveOnStack(fromStack);
            }
        } else {
            elementAboveOnStack = getStack().get(0);
        }
        if (z) {
            Intrinsics.checkNotNull(fromStack);
            fromStack.before(inNode);
        } else {
            Intrinsics.checkNotNull(elementAboveOnStack);
            elementAboveOnStack.appendChild(inNode);
        }
    }

    public final void pushTemplateMode(HtmlTreeBuilderState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        ArrayList<HtmlTreeBuilderState> arrayList = this.tmplInsertMode;
        if (arrayList != null) {
            arrayList.add(state);
        }
    }

    public final HtmlTreeBuilderState popTemplateMode() {
        ArrayList<HtmlTreeBuilderState> arrayList;
        ArrayList<HtmlTreeBuilderState> arrayList2 = this.tmplInsertMode;
        if (arrayList2 == null || arrayList2.isEmpty() || (arrayList = this.tmplInsertMode) == null) {
            return null;
        }
        Intrinsics.checkNotNull(arrayList);
        return arrayList.remove(arrayList.size() - 1);
    }

    public final int templateModeSize() {
        ArrayList<HtmlTreeBuilderState> arrayList = this.tmplInsertMode;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    public final HtmlTreeBuilderState currentTemplateMode() {
        ArrayList<HtmlTreeBuilderState> arrayList;
        ArrayList<HtmlTreeBuilderState> arrayList2 = this.tmplInsertMode;
        Intrinsics.checkNotNull(arrayList2);
        if (arrayList2.size() <= 0 || (arrayList = this.tmplInsertMode) == null) {
            return null;
        }
        Intrinsics.checkNotNull(arrayList);
        return arrayList.get(arrayList.size() - 1);
    }

    public String toString() {
        return "TreeBuilder{currentToken=" + getCurrentToken() + ", state=" + this.state + ", currentElement=" + currentElement() + "}";
    }

    /* JADX INFO: compiled from: HtmlTreeBuilder.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b \n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010*\u001a\u00020+2\u000e\u0010,\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010.0-2\u0006\u0010/\u001a\u00020.H\u0002J\u000e\u00101\u001a\u00020+2\u0006\u00102\u001a\u00020.J\u000e\u00103\u001a\u00020+2\u0006\u00102\u001a\u00020.J4\u00104\u001a\u0002052\u001a\u0010,\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010.06j\n\u0012\u0006\u0012\u0004\u0018\u00010.`72\u0006\u00108\u001a\u00020.2\u0006\u00109\u001a\u00020.H\u0002J\u000e\u0010:\u001a\u00020+2\u0006\u00102\u001a\u00020.J\u0018\u0010;\u001a\u00020+2\u0006\u0010<\u001a\u00020.2\u0006\u0010=\u001a\u00020.H\u0002R\u0019\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0019\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u000b\u0010\bR\u0019\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\r\u0010\bR\u0019\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u000f\u0010\bR\u0019\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0011\u0010\bR\u0019\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0013\u0010\bR\u0019\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0015\u0010\bR\u0019\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0017\u0010\bR\u0019\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0019\u0010\bR\u0019\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u001b\u0010\bR\"\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u001d\u0010\b\"\u0004\b\u001e\u0010\u001fR\u0019\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b!\u0010\bR\u0019\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b#\u0010\bR\u0019\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b%\u0010\bR\u0016\u0010&\u001a\u00020'8\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b(\u0010\u0003R\u000e\u0010)\u001a\u00020'X\u0082T¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020'X\u0082T¢\u0006\u0002\n\u0000¨\u0006>"}, d2 = {"Lcom/fleeksoft/ksoup/parser/HtmlTreeBuilder$Companion;", "", "<init>", "()V", "TagsSearchInScope", "", "", "getTagsSearchInScope", "()[Ljava/lang/String;", "[Ljava/lang/String;", "TagSearchInScopeMath", "getTagSearchInScopeMath", "TagSearchInScopeSvg", "getTagSearchInScopeSvg", "TagSearchList", "getTagSearchList", "TagSearchButton", "getTagSearchButton", "TagSearchTableScope", "getTagSearchTableScope", "TagSearchSelectScope", "getTagSearchSelectScope", "TagSearchEndTags", "getTagSearchEndTags", "TagThoroughSearchEndTags", "getTagThoroughSearchEndTags", "TagSearchSpecial", "getTagSearchSpecial", "TagSearchSpecialMath", "getTagSearchSpecialMath", "setTagSearchSpecialMath", "([Ljava/lang/String;)V", "TagMathMlTextIntegration", "getTagMathMlTextIntegration", "TagSvgHtmlIntegration", "getTagSvgHtmlIntegration", "TagFormListed", "getTagFormListed", "MaxScopeSearchDepth", "", "getMaxScopeSearchDepth$annotations", "maxQueueDepth", "onStack", "", "queue", "", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "maxUsedFormattingElements", "isMathmlTextIntegration", "el", "isHtmlIntegration", "replaceInQueue", "", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "out", "inEl", "isSpecial", "isSameFormattingElement", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Deprecated(message = "Not used anymore; configure parser depth via {@link Parser#setMaxDepth(int)}")
        public static /* synthetic */ void getMaxScopeSearchDepth$annotations() {
        }

        private Companion() {
        }

        public final String[] getTagsSearchInScope() {
            return HtmlTreeBuilder.TagsSearchInScope;
        }

        public final String[] getTagSearchInScopeMath() {
            return HtmlTreeBuilder.TagSearchInScopeMath;
        }

        public final String[] getTagSearchInScopeSvg() {
            return HtmlTreeBuilder.TagSearchInScopeSvg;
        }

        public final String[] getTagSearchList() {
            return HtmlTreeBuilder.TagSearchList;
        }

        public final String[] getTagSearchButton() {
            return HtmlTreeBuilder.TagSearchButton;
        }

        public final String[] getTagSearchTableScope() {
            return HtmlTreeBuilder.TagSearchTableScope;
        }

        public final String[] getTagSearchSelectScope() {
            return HtmlTreeBuilder.TagSearchSelectScope;
        }

        public final String[] getTagSearchEndTags() {
            return HtmlTreeBuilder.TagSearchEndTags;
        }

        public final String[] getTagThoroughSearchEndTags() {
            return HtmlTreeBuilder.TagThoroughSearchEndTags;
        }

        public final String[] getTagSearchSpecial() {
            return HtmlTreeBuilder.TagSearchSpecial;
        }

        public final String[] getTagSearchSpecialMath() {
            return HtmlTreeBuilder.TagSearchSpecialMath;
        }

        public final void setTagSearchSpecialMath(String[] strArr) {
            Intrinsics.checkNotNullParameter(strArr, "<set-?>");
            HtmlTreeBuilder.TagSearchSpecialMath = strArr;
        }

        public final String[] getTagMathMlTextIntegration() {
            return HtmlTreeBuilder.TagMathMlTextIntegration;
        }

        public final String[] getTagSvgHtmlIntegration() {
            return HtmlTreeBuilder.TagSvgHtmlIntegration;
        }

        public final String[] getTagFormListed() {
            return HtmlTreeBuilder.TagFormListed;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean onStack(List<? extends Element> queue, Element element) {
            int size = queue.size();
            int i = size - 1;
            int i2 = i >= 256 ? size - 257 : 0;
            if (i2 <= i) {
                while (queue.get(i) != element) {
                    if (i != i2) {
                        i--;
                    }
                }
                return true;
            }
            return false;
        }

        public final boolean isMathmlTextIntegration(Element el) {
            Intrinsics.checkNotNullParameter(el, "el");
            return Intrinsics.areEqual("http://www.w3.org/1998/Math/MathML", el.tag().namespace()) && StringUtil.INSTANCE.inSorted(el.normalName(), getTagMathMlTextIntegration());
        }

        public final boolean isHtmlIntegration(Element el) {
            Intrinsics.checkNotNullParameter(el, "el");
            if (Intrinsics.areEqual("http://www.w3.org/1998/Math/MathML", el.tag().namespace()) && el.nameIs("annotation-xml")) {
                String strNormalize = Normalizer.INSTANCE.normalize(el.attr("encoding"));
                if (Intrinsics.areEqual(strNormalize, "text/html") || Intrinsics.areEqual(strNormalize, "application/xhtml+xml")) {
                    return true;
                }
            }
            if (!Intrinsics.areEqual("http://www.w3.org/2000/svg", el.tag().namespace())) {
                return false;
            }
            StringUtil stringUtil = StringUtil.INSTANCE;
            String strTagName = el.tagName();
            String[] tagSvgHtmlIntegration = getTagSvgHtmlIntegration();
            return stringUtil.isIn(strTagName, (String[]) Arrays.copyOf(tagSvgHtmlIntegration, tagSvgHtmlIntegration.length));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void replaceInQueue(ArrayList<Element> queue, Element out, Element inEl) {
            int iLastIndexOf = queue.lastIndexOf(out);
            Validate.INSTANCE.isTrue(iLastIndexOf != -1);
            queue.set(iLastIndexOf, inEl);
        }

        public final boolean isSpecial(Element el) {
            Intrinsics.checkNotNullParameter(el, "el");
            String strNamespace = el.tag().namespace();
            String strNormalName = el.normalName();
            int iHashCode = strNamespace.hashCode();
            if (iHashCode == -2078206768) {
                if (strNamespace.equals("http://www.w3.org/1999/xhtml")) {
                    return StringUtil.INSTANCE.inSorted(strNormalName, getTagSearchSpecial());
                }
                return false;
            }
            if (iHashCode == -296110905) {
                if (strNamespace.equals("http://www.w3.org/2000/svg")) {
                    return StringUtil.INSTANCE.inSorted(strNormalName, getTagSvgHtmlIntegration());
                }
                return false;
            }
            if (iHashCode == 1725132794 && strNamespace.equals("http://www.w3.org/1998/Math/MathML")) {
                return StringUtil.INSTANCE.inSorted(strNormalName, getTagSearchSpecialMath());
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isSameFormattingElement(Element a, Element b) {
            return Intrinsics.areEqual(a.normalName(), b.normalName()) && Intrinsics.areEqual(a.attributes(), b.attributes());
        }
    }
}
