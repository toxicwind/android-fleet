package com.fleeksoft.ksoup.select;

import androidx.core.app.NotificationCompat;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.fleeksoft.ksoup.exception.UncheckedIOException;
import com.fleeksoft.ksoup.helper.Validate;
import com.fleeksoft.ksoup.internal.Normalizer;
import com.fleeksoft.ksoup.internal.StringUtil;
import com.fleeksoft.ksoup.nodes.CDataNode;
import com.fleeksoft.ksoup.nodes.Comment;
import com.fleeksoft.ksoup.nodes.DataNode;
import com.fleeksoft.ksoup.nodes.LeafNode;
import com.fleeksoft.ksoup.nodes.Node;
import com.fleeksoft.ksoup.nodes.TextNode;
import com.fleeksoft.ksoup.parser.TokenQueue;
import com.fleeksoft.ksoup.ported.CoreFunctionsKt;
import com.google.android.gms.actions.SearchIntents;
import io.ktor.util.date.GMTDateParser;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.slf4j.Marker;

/* JADX INFO: compiled from: QueryParser.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 02\u00060\u0001j\u0002`\u0002:\u00010B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\fJ\u0006\u0010\u000e\u001a\u00020\fJ\u0006\u0010\u000f\u001a\u00020\fJ\b\u0010\u0010\u001a\u0004\u0018\u00010\fJ\b\u0010\u0011\u001a\u00020\fH\u0002J\b\u0010\u0012\u001a\u00020\fH\u0002J\b\u0010\u0013\u001a\u00020\fH\u0002J\b\u0010\u0014\u001a\u00020\fH\u0002J\b\u0010\u0015\u001a\u00020\fH\u0002J\b\u0010\u0016\u001a\u00020\fH\u0002J\u0010\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\bH\u0002J\u0018\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\nH\u0002J\b\u0010\u001c\u001a\u00020\u0004H\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\fH\u0002J\b\u0010 \u001a\u00020\fH\u0002J&\u0010!\u001a\u00020\f2\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020$0#2\b\u0010%\u001a\u0004\u0018\u00010\u0004H\u0002J\u0010\u0010&\u001a\u00020\f2\u0006\u0010'\u001a\u00020\nH\u0002J\u0010\u0010(\u001a\u00020\f2\u0006\u0010'\u001a\u00020\nH\u0002J\b\u0010)\u001a\u00020\fH\u0002J\u0010\u0010*\u001a\u00020\f2\u0006\u0010'\u001a\u00020\nH\u0002J\u0010\u0010+\u001a\u00020\f2\u0006\u0010'\u001a\u00020\nH\u0002J\t\u0010,\u001a\u00020\fH\u0082\u0002J\n\u0010-\u001a\u00020\u0004H\u0096\u0080\u0004J\b\u0010.\u001a\u00020/H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lcom/fleeksoft/ksoup/select/QueryParser;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", SearchIntents.EXTRA_QUERY, "", "<init>", "(Ljava/lang/String;)V", "tq", "Lcom/fleeksoft/ksoup/parser/TokenQueue;", "inNodeContext", "", "parse", "Lcom/fleeksoft/ksoup/select/Evaluator;", "parseSelectorGroup", "parseSelector", "parseSimpleSequence", "parseSubclass", "parsePseudoSelector", "parseNodeSelector", "byId", "byClass", "byTag", "byAttribute", "evaluatorForAttribute", "cq", "cssNthChild", "last", "ofType", "consumeParens", "consumeIndex", "", "has", "is", "parseNested", "func", "Lkotlin/Function1;", "Lcom/fleeksoft/ksoup/select/StructuralEvaluator;", NotificationCompat.CATEGORY_ERROR, "contains", "own", "containsWholeText", "containsData", "matches", "matchesWholeText", "not", "toString", "close", "", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class QueryParser implements AutoCloseable {
    private boolean inNodeContext;
    private final String query;
    private final TokenQueue tq;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final char[] Combinators = {Typography.greater, '+', '~'};
    private static final char[] SequenceEnders = {AbstractJsonLexerKt.COMMA, ')'};
    private static final String[] AttributeEvals = {"=", "!=", "^=", "$=", "*=", "~="};
    private static final Regex NthStepOffset = new Regex("(([+-])?(\\d+)?)n(\\s*([+-])?\\s*\\d+)?", RegexOption.IGNORE_CASE);
    private static final Regex NthOffset = new Regex("([+-])?(\\d+)");

    public /* synthetic */ QueryParser(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    private QueryParser(String str) {
        Validate.INSTANCE.notEmpty(str);
        String str2 = str;
        int length = str2.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean z2 = Intrinsics.compare((int) str2.charAt(!z ? i : length), 32) <= 0;
            if (z) {
                if (!z2) {
                    break;
                } else {
                    length--;
                }
            } else if (z2) {
                i++;
            } else {
                z = true;
            }
        }
        String string = str2.subSequence(i, length + 1).toString();
        this.query = string;
        this.tq = new TokenQueue(string);
    }

    public final Evaluator parse() {
        Evaluator selectorGroup = parseSelectorGroup();
        this.tq.consumeWhitespace();
        if (this.tq.isEmpty()) {
            return selectorGroup;
        }
        throw new Selector.SelectorParseException("Could not parse query '" + this.query + "': unexpected token at '" + this.tq.remainder() + "'");
    }

    public final Evaluator parseSelectorGroup() {
        Evaluator selector = parseSelector();
        while (this.tq.matchChomp(AbstractJsonLexerKt.COMMA)) {
            selector = INSTANCE.or(selector, parseSelector());
        }
        return selector;
    }

    public final Evaluator parseSelector() {
        StructuralEvaluator.Root simpleSequence;
        this.tq.consumeWhitespace();
        TokenQueue tokenQueue = this.tq;
        char[] cArr = Combinators;
        if (tokenQueue.matchesAny(Arrays.copyOf(cArr, cArr.length))) {
            simpleSequence = new StructuralEvaluator.Root();
        } else {
            simpleSequence = parseSimpleSequence();
        }
        while (true) {
            char cConsume = this.tq.consumeWhitespace() ? ' ' : (char) 0;
            TokenQueue tokenQueue2 = this.tq;
            char[] cArr2 = Combinators;
            boolean zMatchesAny = tokenQueue2.matchesAny(Arrays.copyOf(cArr2, cArr2.length));
            TokenQueue tokenQueue3 = this.tq;
            if (zMatchesAny) {
                cConsume = tokenQueue3.consume();
            } else {
                char[] cArr3 = SequenceEnders;
                if (!tokenQueue3.matchesAny(Arrays.copyOf(cArr3, cArr3.length))) {
                    break;
                }
                break;
            }
            if (cConsume == 0) {
                break;
            }
            simpleSequence = INSTANCE.combinator(simpleSequence, cConsume, parseSimpleSequence());
        }
        return simpleSequence;
    }

    public final Evaluator parseSimpleSequence() {
        Evaluator.AllElements allElementsByTag;
        this.tq.consumeWhitespace();
        if (this.tq.matchesWord() || this.tq.matches("*|")) {
            allElementsByTag = byTag();
        } else {
            allElementsByTag = this.tq.matchChomp(GMTDateParser.ANY) ? new Evaluator.AllElements() : null;
        }
        while (true) {
            Evaluator subclass = parseSubclass();
            if (subclass == null) {
                break;
            }
            allElementsByTag = INSTANCE.and(allElementsByTag, subclass);
        }
        if (allElementsByTag != null) {
            return allElementsByTag;
        }
        throw new Selector.SelectorParseException("Could not parse query '" + this.query + "': unexpected token at '" + this.tq.remainder() + "'");
    }

    public final Evaluator parseSubclass() {
        if (this.tq.matchChomp('#')) {
            return byId();
        }
        if (this.tq.matchChomp('.')) {
            return byClass();
        }
        if (this.tq.matches(AbstractJsonLexerKt.BEGIN_LIST)) {
            return byAttribute();
        }
        if (this.tq.matchChomp("::")) {
            return parseNodeSelector();
        }
        if (this.tq.matchChomp(AbstractJsonLexerKt.COLON)) {
            return parsePseudoSelector();
        }
        return null;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final Evaluator parsePseudoSelector() throws UncheckedIOException {
        String strConsumeCssIdentifier = this.tq.consumeCssIdentifier();
        switch (strConsumeCssIdentifier.hashCode()) {
            case -2141736343:
                if (strConsumeCssIdentifier.equals("containsData")) {
                    return containsData();
                }
                break;
            case -2136991809:
                if (strConsumeCssIdentifier.equals("first-child")) {
                    return new Evaluator.IsFirstChild();
                }
                break;
            case -1939921007:
                if (strConsumeCssIdentifier.equals("matchesWholeText")) {
                    return matchesWholeText(false);
                }
                break;
            case -1754914063:
                if (strConsumeCssIdentifier.equals("nth-child")) {
                    return cssNthChild(false, false);
                }
                break;
            case -1629748624:
                if (strConsumeCssIdentifier.equals("nth-last-child")) {
                    return cssNthChild(true, false);
                }
                break;
            case -947996741:
                if (strConsumeCssIdentifier.equals("only-child")) {
                    return new Evaluator.IsOnlyChild();
                }
                break;
            case -897532411:
                if (strConsumeCssIdentifier.equals("nth-of-type")) {
                    return cssNthChild(false, true);
                }
                break;
            case -872629820:
                if (strConsumeCssIdentifier.equals("nth-last-of-type")) {
                    return cssNthChild(true, true);
                }
                break;
            case -567445985:
                if (strConsumeCssIdentifier.equals("contains")) {
                    return contains(false);
                }
                break;
            case -55413797:
                if (strConsumeCssIdentifier.equals("containsWholeOwnText")) {
                    return containsWholeText(true);
                }
                break;
            case 3244:
                if (strConsumeCssIdentifier.equals("eq")) {
                    return new Evaluator.IndexEquals(consumeIndex());
                }
                break;
            case 3309:
                if (strConsumeCssIdentifier.equals("gt")) {
                    return new Evaluator.IndexGreaterThan(consumeIndex());
                }
                break;
            case 3370:
                if (strConsumeCssIdentifier.equals("is")) {
                    return is();
                }
                break;
            case 3464:
                if (strConsumeCssIdentifier.equals("lt")) {
                    return new Evaluator.IndexLessThan(consumeIndex());
                }
                break;
            case 103066:
                if (strConsumeCssIdentifier.equals("has")) {
                    return has();
                }
                break;
            case 109267:
                if (strConsumeCssIdentifier.equals("not")) {
                    return not();
                }
                break;
            case 3506402:
                if (strConsumeCssIdentifier.equals("root")) {
                    return new Evaluator.IsRoot();
                }
                break;
            case 93819220:
                if (strConsumeCssIdentifier.equals("blank")) {
                    return new NodeEvaluator.BlankValue();
                }
                break;
            case 96634189:
                if (strConsumeCssIdentifier.equals("empty")) {
                    return new Evaluator.IsEmpty();
                }
                break;
            case 208017639:
                if (strConsumeCssIdentifier.equals("containsOwn")) {
                    return contains(true);
                }
                break;
            case 614017170:
                if (strConsumeCssIdentifier.equals("matchText")) {
                    return new Evaluator.MatchText();
                }
                break;
            case 835834661:
                if (strConsumeCssIdentifier.equals("last-child")) {
                    return new Evaluator.IsLastChild();
                }
                break;
            case 840862003:
                if (strConsumeCssIdentifier.equals("matches")) {
                    return matches(false);
                }
                break;
            case 1255901423:
                if (strConsumeCssIdentifier.equals("matchesWholeOwnText")) {
                    return matchesWholeText(true);
                }
                break;
            case 1292941139:
                if (strConsumeCssIdentifier.equals("first-of-type")) {
                    return new Evaluator.IsFirstOfType();
                }
                break;
            case 1455900751:
                if (strConsumeCssIdentifier.equals("only-of-type")) {
                    return new Evaluator.IsOnlyOfType();
                }
                break;
            case 1870740819:
                if (strConsumeCssIdentifier.equals("matchesOwn")) {
                    return matches(true);
                }
                break;
            case 2014184485:
                if (strConsumeCssIdentifier.equals("containsWholeText")) {
                    return containsWholeText(false);
                }
                break;
            case 2025926969:
                if (strConsumeCssIdentifier.equals("last-of-type")) {
                    return new Evaluator.IsLastOfType();
                }
                break;
        }
        throw new Selector.SelectorParseException("Could not parse query '" + this.query + "': unexpected token at '" + this.tq.remainder() + "'");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:25:0x009b A[LOOP:0: B:23:0x0095->B:25:0x009b, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:30:0x00a5 A[SYNTHETIC] */
    private final Evaluator parseNodeSelector() throws UncheckedIOException {
        NodeEvaluator.InstanceType instanceType;
        Evaluator subclass;
        String strConsumeCssIdentifier = this.tq.consumeCssIdentifier();
        this.inNodeContext = true;
        switch (strConsumeCssIdentifier) {
            case "data":
                instanceType = new NodeEvaluator.InstanceType(Reflection.getOrCreateKotlinClass(DataNode.class), strConsumeCssIdentifier);
                while (true) {
                    subclass = parseSubclass();
                    if (subclass != null) {
                        this.inNodeContext = false;
                        return instanceType;
                    }
                    Companion companion = INSTANCE;
                    Intrinsics.checkNotNull(subclass);
                    instanceType = companion.and(instanceType, subclass);
                }
            case "node":
                instanceType = new NodeEvaluator.InstanceType(Reflection.getOrCreateKotlinClass(Node.class), strConsumeCssIdentifier);
                while (true) {
                    subclass = parseSubclass();
                    if (subclass != null) {
                        this.inNodeContext = false;
                        return instanceType;
                    }
                    Companion companion2 = INSTANCE;
                    Intrinsics.checkNotNull(subclass);
                    instanceType = companion2.and(instanceType, subclass);
                }
            case "text":
                instanceType = new NodeEvaluator.InstanceType(Reflection.getOrCreateKotlinClass(TextNode.class), strConsumeCssIdentifier);
                while (true) {
                    subclass = parseSubclass();
                    if (subclass != null) {
                        this.inNodeContext = false;
                        return instanceType;
                    }
                    Companion companion3 = INSTANCE;
                    Intrinsics.checkNotNull(subclass);
                    instanceType = companion3.and(instanceType, subclass);
                }
            case "cdata":
                instanceType = new NodeEvaluator.InstanceType(Reflection.getOrCreateKotlinClass(CDataNode.class), strConsumeCssIdentifier);
                while (true) {
                    subclass = parseSubclass();
                    if (subclass != null) {
                        this.inNodeContext = false;
                        return instanceType;
                    }
                    Companion companion4 = INSTANCE;
                    Intrinsics.checkNotNull(subclass);
                    instanceType = companion4.and(instanceType, subclass);
                }
            case "comment":
                instanceType = new NodeEvaluator.InstanceType(Reflection.getOrCreateKotlinClass(Comment.class), strConsumeCssIdentifier);
                while (true) {
                    subclass = parseSubclass();
                    if (subclass != null) {
                        this.inNodeContext = false;
                        return instanceType;
                    }
                    Companion companion5 = INSTANCE;
                    Intrinsics.checkNotNull(subclass);
                    instanceType = companion5.and(instanceType, subclass);
                }
            case "leafnode":
                instanceType = new NodeEvaluator.InstanceType(Reflection.getOrCreateKotlinClass(LeafNode.class), strConsumeCssIdentifier);
                while (true) {
                    subclass = parseSubclass();
                    if (subclass != null) {
                        this.inNodeContext = false;
                        return instanceType;
                    }
                    Companion companion6 = INSTANCE;
                    Intrinsics.checkNotNull(subclass);
                    instanceType = companion6.and(instanceType, subclass);
                }
            default:
                if (strConsumeCssIdentifier.equals("data")) {
                    instanceType = new NodeEvaluator.InstanceType(Reflection.getOrCreateKotlinClass(DataNode.class), strConsumeCssIdentifier);
                    while (true) {
                        subclass = parseSubclass();
                        if (subclass != null) {
                            this.inNodeContext = false;
                            return instanceType;
                        }
                        Companion companion7 = INSTANCE;
                        Intrinsics.checkNotNull(subclass);
                        instanceType = companion7.and(instanceType, subclass);
                    }
                }
                throw new Selector.SelectorParseException("Could not parse query '" + this.query + "': unknown node type '::" + strConsumeCssIdentifier + "'");
        }
    }

    private final Evaluator byId() throws UncheckedIOException {
        String strConsumeCssIdentifier = this.tq.consumeCssIdentifier();
        Validate.INSTANCE.notEmpty(strConsumeCssIdentifier);
        return new Evaluator.Id(strConsumeCssIdentifier);
    }

    private final Evaluator byClass() throws UncheckedIOException {
        String strConsumeCssIdentifier = this.tq.consumeCssIdentifier();
        Validate.INSTANCE.notEmpty(strConsumeCssIdentifier);
        String str = strConsumeCssIdentifier;
        int length = str.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean z2 = Intrinsics.compare((int) str.charAt(!z ? i : length), 32) <= 0;
            if (z) {
                if (!z2) {
                    break;
                }
                length--;
            } else if (z2) {
                i++;
            } else {
                z = true;
            }
        }
        return new Evaluator.Class(str.subSequence(i, length + 1).toString());
    }

    private final Evaluator byTag() {
        String strNormalize = Normalizer.INSTANCE.normalize(this.tq.consumeElementSelector());
        Validate.INSTANCE.notEmpty(strNormalize);
        if (StringsKt.startsWith$default(strNormalize, "*|", false, 2, (Object) null)) {
            String strSubstring = strNormalize.substring(2);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            return new CombiningEvaluator.Or(new Evaluator.Tag(strSubstring), new Evaluator.TagEndsWith(":" + strSubstring));
        }
        if (StringsKt.endsWith$default(strNormalize, "|*", false, 2, (Object) null)) {
            String strSubstring2 = strNormalize.substring(0, strNormalize.length() - 2);
            Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
            return new Evaluator.TagStartsWith(strSubstring2 + ":");
        }
        if (StringsKt.contains$default((CharSequence) strNormalize, (CharSequence) "|", false, 2, (Object) null)) {
            strNormalize = StringsKt.replace$default(strNormalize, "|", ":", false, 4, (Object) null);
        }
        return new Evaluator.Tag(strNormalize);
    }

    private final Evaluator byAttribute() {
        TokenQueue tokenQueue = new TokenQueue(this.tq.chompBalanced(AbstractJsonLexerKt.BEGIN_LIST, AbstractJsonLexerKt.END_LIST));
        try {
            Evaluator evaluatorEvaluatorForAttribute = evaluatorForAttribute(tokenQueue);
            AutoCloseableKt.closeFinally(tokenQueue, null);
            return evaluatorEvaluatorForAttribute;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                AutoCloseableKt.closeFinally(tokenQueue, th);
                throw th2;
            }
        }
    }

    private final Evaluator evaluatorForAttribute(TokenQueue cq) {
        Normalizer normalizer = Normalizer.INSTANCE;
        String[] strArr = AttributeEvals;
        String strNormalize = normalizer.normalize(cq.consumeToAny((String[]) Arrays.copyOf(strArr, strArr.length)));
        Validate.INSTANCE.notEmpty(strNormalize);
        Validate.INSTANCE.isFalse(Intrinsics.areEqual(strNormalize, "abs:"), "Absolute attribute key must have a name");
        cq.consumeWhitespace();
        if (cq.isEmpty()) {
            if (StringsKt.startsWith$default(strNormalize, "^", false, 2, (Object) null)) {
                String strSubstring = strNormalize.substring(1);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                return new Evaluator.AttributeStarting(strSubstring);
            }
            if (Intrinsics.areEqual(strNormalize, Marker.ANY_MARKER)) {
                return new Evaluator.AttributeStarting("");
            }
            return new Evaluator.Attribute(strNormalize);
        }
        if (cq.matchChomp('=')) {
            return new Evaluator.AttributeWithValue(strNormalize, cq.remainder());
        }
        if (cq.matchChomp("!=")) {
            return new Evaluator.AttributeWithValueNot(strNormalize, cq.remainder());
        }
        if (cq.matchChomp("^=")) {
            return new Evaluator.AttributeWithValueStarting(strNormalize, cq.remainder());
        }
        if (cq.matchChomp("$=")) {
            return new Evaluator.AttributeWithValueEnding(strNormalize, cq.remainder());
        }
        if (cq.matchChomp("*=")) {
            return new Evaluator.AttributeWithValueContaining(strNormalize, cq.remainder());
        }
        if (cq.matchChomp("~=")) {
            return new Evaluator.AttributeWithValueMatching(strNormalize, CoreFunctionsKt.jsSupportedRegex(cq.remainder()));
        }
        throw new Selector.SelectorParseException("Could not parse attribute query '" + this.query + "': unexpected token at '" + cq.remainder() + "'");
    }

    /* JADX WARN: Code duplicated, block: B:24:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:26:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:40:0x0118  */
    /* JADX WARN: Instruction removed from duplicated block: B:40:0x0118, please report this as an issue */
    private final Evaluator cssNthChild(boolean last, boolean ofType) {
        MatchResult matchResultMatchEntire;
        Pair pair;
        String strNormalize = Normalizer.INSTANCE.normalize(consumeParens());
        int i = 1;
        if (StringsKt.equals(strNormalize, "odd", true)) {
            pair = TuplesKt.to(2, 1);
        } else if (StringsKt.equals(strNormalize, "even", true)) {
            pair = TuplesKt.to(2, 0);
        } else {
            String str = strNormalize;
            MatchResult matchResultMatchEntire2 = NthStepOffset.matchEntire(str);
            if (matchResultMatchEntire2 != null) {
                String str2 = matchResultMatchEntire2.getGroupValues().get(2);
                String str3 = matchResultMatchEntire2.getGroupValues().get(3);
                String str4 = matchResultMatchEntire2.getGroupValues().get(4);
                if (str3.length() > 0) {
                    i = Integer.parseInt(new Regex("^\\+").replaceFirst(matchResultMatchEntire2.getGroupValues().get(1), ""));
                } else if (Intrinsics.areEqual(str2, "-")) {
                    i = -1;
                }
                String str5 = str4;
                Pair pair2 = TuplesKt.to(Integer.valueOf(i), Integer.valueOf(str5.length() > 0 ? Integer.parseInt(new Regex("^\\+").replaceFirst(str5, "")) : 0));
                if (pair2 == null) {
                    matchResultMatchEntire = NthOffset.matchEntire(str);
                    if (matchResultMatchEntire != null) {
                        pair = TuplesKt.to(0, Integer.valueOf(Integer.parseInt(new Regex("^\\+").replaceFirst(matchResultMatchEntire.getValue(), ""))));
                    } else {
                        throw new Selector.SelectorParseException("Could not parse nth-index '" + strNormalize + "': unexpected format");
                    }
                } else {
                    pair = pair2;
                }
            } else {
                matchResultMatchEntire = NthOffset.matchEntire(str);
                if (matchResultMatchEntire != null) {
                    pair = TuplesKt.to(0, Integer.valueOf(Integer.parseInt(new Regex("^\\+").replaceFirst(matchResultMatchEntire.getValue(), ""))));
                } else {
                    throw new Selector.SelectorParseException("Could not parse nth-index '" + strNormalize + "': unexpected format");
                }
            }
        }
        int iIntValue = ((Number) pair.component1()).intValue();
        int iIntValue2 = ((Number) pair.component2()).intValue();
        if (ofType && last) {
            return new Evaluator.IsNthLastOfType(iIntValue, iIntValue2);
        }
        if (ofType) {
            return new Evaluator.IsNthOfType(iIntValue, iIntValue2);
        }
        if (last) {
            return new Evaluator.IsNthLastChild(iIntValue, iIntValue2);
        }
        return new Evaluator.IsNthChild(iIntValue, iIntValue2);
    }

    private final String consumeParens() {
        return this.tq.chompBalanced('(', ')');
    }

    private final int consumeIndex() {
        String strConsumeParens = consumeParens();
        int length = strConsumeParens.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean z2 = Intrinsics.compare((int) strConsumeParens.charAt(!z ? i : length), 32) <= 0;
            if (z) {
                if (!z2) {
                    break;
                }
                length--;
            } else if (z2) {
                i++;
            } else {
                z = true;
            }
        }
        String string = strConsumeParens.subSequence(i, length + 1).toString();
        Validate.INSTANCE.isTrue(StringUtil.INSTANCE.isNumeric(string), "Index must be numeric");
        return Integer.parseInt(string);
    }

    private final Evaluator has() {
        return parseNested(new Function1() { // from class: com.fleeksoft.ksoup.select.QueryParser$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return QueryParser.has$lambda$0((Evaluator) obj);
            }
        }, ":has() must have a selector");
    }

    static final StructuralEvaluator has$lambda$0(Evaluator it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return new StructuralEvaluator.Has(it);
    }

    private final Evaluator is() {
        return parseNested(new Function1() { // from class: com.fleeksoft.ksoup.select.QueryParser$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return QueryParser.is$lambda$0((Evaluator) obj);
            }
        }, ":is() must have a selector");
    }

    static final StructuralEvaluator is$lambda$0(Evaluator it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return new StructuralEvaluator.Is(it);
    }

    private final Evaluator parseNested(Function1<? super Evaluator, ? extends StructuralEvaluator> func, String err) {
        Validate.INSTANCE.isTrue(this.tq.matchChomp('('), err);
        Evaluator selectorGroup = parseSelectorGroup();
        Validate.INSTANCE.isTrue(this.tq.matchChomp(')'), err);
        return func.invoke(selectorGroup);
    }

    private final Evaluator contains(boolean own) {
        String str = own ? ":containsOwn" : ":contains";
        String strUnescape = TokenQueue.INSTANCE.unescape(consumeParens());
        Validate.INSTANCE.notEmpty(strUnescape, str.concat("(text) query must not be empty"));
        if (this.inNodeContext) {
            return new NodeEvaluator.ContainsValue(strUnescape);
        }
        return own ? new Evaluator.ContainsOwnText(strUnescape) : new Evaluator.ContainsText(strUnescape);
    }

    private final Evaluator containsWholeText(boolean own) {
        String str = own ? ":containsWholeOwnText" : ":containsWholeText";
        String strUnescape = TokenQueue.INSTANCE.unescape(consumeParens());
        Validate.INSTANCE.notEmpty(strUnescape, str.concat("(text) query must not be empty"));
        return own ? new Evaluator.ContainsWholeOwnText(strUnescape) : new Evaluator.ContainsWholeText(strUnescape);
    }

    private final Evaluator containsData() {
        String strUnescape = TokenQueue.INSTANCE.unescape(consumeParens());
        Validate.INSTANCE.notEmpty(strUnescape, ":containsData(text) query must not be empty");
        return new Evaluator.ContainsData(strUnescape);
    }

    private final Evaluator matches(boolean own) {
        String str = own ? ":matchesOwn" : ":matches";
        String strConsumeParens = consumeParens();
        Validate.INSTANCE.notEmpty(strConsumeParens, str.concat("(regex) query must not be empty"));
        Regex regexJsSupportedRegex = CoreFunctionsKt.jsSupportedRegex(strConsumeParens);
        if (this.inNodeContext) {
            return new NodeEvaluator.MatchesValue(regexJsSupportedRegex);
        }
        if (own) {
            return new Evaluator.MatchesOwn(regexJsSupportedRegex);
        }
        return new Evaluator.Matches(regexJsSupportedRegex);
    }

    private final Evaluator matchesWholeText(boolean own) {
        String str = own ? ":matchesWholeOwnText" : ":matchesWholeText";
        String strConsumeParens = consumeParens();
        Validate.INSTANCE.notEmpty(strConsumeParens, str.concat("(regex) query must not be empty"));
        Regex regexJsSupportedRegex = CoreFunctionsKt.jsSupportedRegex(strConsumeParens);
        if (own) {
            return new Evaluator.MatchesWholeOwnText(regexJsSupportedRegex);
        }
        return new Evaluator.MatchesWholeText(regexJsSupportedRegex);
    }

    private final Evaluator not() {
        String strConsumeParens = consumeParens();
        Validate.INSTANCE.notEmpty(strConsumeParens, ":not(selector) subselect must not be empty");
        return new StructuralEvaluator.Not(INSTANCE.parse(strConsumeParens));
    }

    /* JADX INFO: renamed from: toString, reason: from getter */
    public String getQuery() {
        return this.query;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        this.tq.close();
    }

    /* JADX INFO: compiled from: QueryParser.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0019\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\tJ\u001e\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\fJ\u0016\u0010\u0012\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\fJ\u0018\u0010\u0013\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0011\u001a\u00020\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/fleeksoft/ksoup/select/QueryParser$Companion;", "", "<init>", "()V", "Combinators", "", "SequenceEnders", "AttributeEvals", "", "", "[Ljava/lang/String;", "parse", "Lcom/fleeksoft/ksoup/select/Evaluator;", SearchIntents.EXTRA_QUERY, "combinator", TtmlNode.LEFT, "", TtmlNode.RIGHT, "or", "and", "NthStepOffset", "Lkotlin/text/Regex;", "NthOffset", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Evaluator parse(String query) {
            Intrinsics.checkNotNullParameter(query, "query");
            try {
                QueryParser queryParser = new QueryParser(query, null);
                try {
                    Evaluator evaluator = queryParser.parse();
                    AutoCloseableKt.closeFinally(queryParser, null);
                    return evaluator;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        AutoCloseableKt.closeFinally(queryParser, th);
                        throw th2;
                    }
                }
            } catch (IllegalArgumentException e) {
                throw new Selector.SelectorParseException(e.getMessage());
            }
        }

        public final Evaluator combinator(Evaluator left, char combinator, Evaluator right) {
            Intrinsics.checkNotNullParameter(left, "left");
            Intrinsics.checkNotNullParameter(right, "right");
            if (combinator == ' ') {
                return and(new StructuralEvaluator.Ancestor(left), right);
            }
            if (combinator == '+') {
                return and(new StructuralEvaluator.ImmediatePreviousSibling(left), right);
            }
            if (combinator == '>') {
                StructuralEvaluator.ImmediateParentRun immediateParentRun = left instanceof StructuralEvaluator.ImmediateParentRun ? (StructuralEvaluator.ImmediateParentRun) left : null;
                if (immediateParentRun == null) {
                    immediateParentRun = new StructuralEvaluator.ImmediateParentRun(left);
                }
                immediateParentRun.add(right);
                return immediateParentRun;
            }
            if (combinator == '~') {
                return and(new StructuralEvaluator.PreviousSibling(left), right);
            }
            throw new Selector.SelectorParseException("Unknown combinator '" + combinator + "'");
        }

        public final Evaluator or(Evaluator left, Evaluator right) {
            Intrinsics.checkNotNullParameter(left, "left");
            Intrinsics.checkNotNullParameter(right, "right");
            if (left instanceof CombiningEvaluator.Or) {
                ((CombiningEvaluator.Or) left).add(right);
                return left;
            }
            return new CombiningEvaluator.Or(left, right);
        }

        public final Evaluator and(Evaluator left, Evaluator right) {
            Intrinsics.checkNotNullParameter(right, "right");
            if (left == null) {
                return right;
            }
            if (left instanceof CombiningEvaluator.And) {
                ((CombiningEvaluator.And) left).add(right);
                return left;
            }
            return new CombiningEvaluator.And(left, right);
        }
    }
}
