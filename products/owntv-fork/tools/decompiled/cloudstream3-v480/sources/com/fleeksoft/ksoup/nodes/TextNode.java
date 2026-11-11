package com.fleeksoft.ksoup.nodes;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.fleeksoft.ksoup.helper.Validate;
import com.fleeksoft.ksoup.internal.QuietAppendable;
import com.fleeksoft.ksoup.internal.StringUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

/* JADX INFO: compiled from: TextNode.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0002\u001a\u00020\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003J\u0006\u0010\u0007\u001a\u00020\u0003J\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\fJ\u001d\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0010¢\u0006\u0002\b\u0013J\n\u0010\u0014\u001a\u00020\u0003H\u0096\u0080\u0004J\r\u0010\u0015\u001a\u00020\u0016H\u0010¢\u0006\u0002\b\u0017J\b\u0010\u0018\u001a\u00020\u0000H\u0016¨\u0006\u001a"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/TextNode;", "Lcom/fleeksoft/ksoup/nodes/LeafNode;", "text", "", "<init>", "(Ljava/lang/String;)V", "nodeName", "getWholeText", "isBlank", "", "splitText", TypedValues.CycleType.S_WAVE_OFFSET, "", "outerHtmlHead", "", "accum", "Lcom/fleeksoft/ksoup/internal/QuietAppendable;", "out", "Lcom/fleeksoft/ksoup/nodes/Document$OutputSettings;", "outerHtmlHead$ksoup_release", "toString", "createClone", "Lcom/fleeksoft/ksoup/nodes/Node;", "createClone$ksoup_release", "clone", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class TextNode extends LeafNode {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextNode(String text) {
        super(text);
        Intrinsics.checkNotNullParameter(text, "text");
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public String nodeName() {
        return "#text";
    }

    public String text() {
        return StringUtil.INSTANCE.normaliseWhitespace(getWholeText());
    }

    public final TextNode text(String text) {
        coreValue(text);
        return this;
    }

    public final String getWholeText() {
        return coreValue();
    }

    public final boolean isBlank() {
        return StringUtil.INSTANCE.isBlank(coreValue());
    }

    public final TextNode splitText(int offset) {
        String strCoreValue = coreValue();
        Validate.INSTANCE.isTrue(offset >= 0, "Split offset must be not be negative");
        Validate.INSTANCE.isTrue(offset < strCoreValue.length(), "Split offset must not be greater than current text length");
        String strSubstring = strCoreValue.substring(0, offset);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        String strSubstring2 = strCoreValue.substring(offset);
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
        text(strSubstring);
        TextNode textNode = new TextNode(strSubstring2);
        if (get_parentNode() != null) {
            Element element = get_parentNode();
            Intrinsics.checkNotNull(element);
            element.addChildren(siblingIndex() + 1, textNode);
        }
        return textNode;
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public void outerHtmlHead$ksoup_release(QuietAppendable accum, Document.OutputSettings out) {
        Intrinsics.checkNotNullParameter(accum, "accum");
        Intrinsics.checkNotNullParameter(out, "out");
        Entities.INSTANCE.escape(accum, coreValue(), out, 1);
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public String toString() {
        return outerHtml();
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public Node createClone$ksoup_release() {
        TextNode textNode = new TextNode("");
        textNode.setValue$ksoup_release(getValue());
        return textNode;
    }

    @Override // com.fleeksoft.ksoup.nodes.Node, com.fleeksoft.ksoup.ported.KCloneable
    /* JADX INFO: renamed from: clone */
    public Node clone2() {
        Node nodeClone = super.clone2();
        Intrinsics.checkNotNull(nodeClone, "null cannot be cast to non-null type com.fleeksoft.ksoup.nodes.TextNode");
        return (TextNode) nodeClone;
    }

    /* JADX INFO: compiled from: TextNode.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007J\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007J\u0019\u0010\u000b\u001a\u00020\f2\n\u0010\r\u001a\u00060\u000ej\u0002`\u000fH\u0000¢\u0006\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/TextNode$Companion;", "", "<init>", "()V", "createFromEncoded", "Lcom/fleeksoft/ksoup/nodes/TextNode;", "encodedText", "", "normaliseWhitespace", "text", "stripLeadingWhitespace", "lastCharIsWhitespace", "", "sb", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "lastCharIsWhitespace$ksoup_release", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TextNode createFromEncoded(String encodedText) {
            Intrinsics.checkNotNullParameter(encodedText, "encodedText");
            return new TextNode(Entities.INSTANCE.unescape(encodedText));
        }

        public final String normaliseWhitespace(String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            return StringUtil.INSTANCE.normaliseWhitespace(text);
        }

        public final String stripLeadingWhitespace(String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            return new Regex("^\\s+").replaceFirst(text, "");
        }

        public final boolean lastCharIsWhitespace$ksoup_release(StringBuilder sb) {
            Intrinsics.checkNotNullParameter(sb, "sb");
            return sb.length() > 0 && sb.charAt(sb.length() - 1) == ' ';
        }
    }
}
