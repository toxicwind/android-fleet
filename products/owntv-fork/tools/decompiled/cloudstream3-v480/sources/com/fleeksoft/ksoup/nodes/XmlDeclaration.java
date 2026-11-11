package com.fleeksoft.ksoup.nodes;

import com.fleeksoft.ksoup.internal.QuietAppendable;
import com.fleeksoft.ksoup.internal.StringUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: XmlDeclaration.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\b\u001a\u00020\u0003H\u0016J\u0006\u0010\u0002\u001a\u00020\u0003J\u0006\u0010\t\u001a\u00020\u0003J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u001d\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0010¢\u0006\u0002\b\u0010J\u001d\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0010¢\u0006\u0002\b\u0012J\n\u0010\u0013\u001a\u00020\u0003H\u0096\u0080\u0004J\r\u0010\u0014\u001a\u00020\u0015H\u0010¢\u0006\u0002\b\u0016J\b\u0010\u0017\u001a\u00020\u0000H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/XmlDeclaration;", "Lcom/fleeksoft/ksoup/nodes/LeafNode;", "name", "", "isDeclaration", "", "<init>", "(Ljava/lang/String;Z)V", "nodeName", "getWholeDeclaration", "", "accum", "Lcom/fleeksoft/ksoup/internal/QuietAppendable;", "out", "Lcom/fleeksoft/ksoup/nodes/Document$OutputSettings;", "outerHtmlHead", "outerHtmlHead$ksoup_release", "outerHtmlTail", "outerHtmlTail$ksoup_release", "toString", "createClone", "Lcom/fleeksoft/ksoup/nodes/Node;", "createClone$ksoup_release", "clone", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class XmlDeclaration extends LeafNode {
    private final boolean isDeclaration;

    @Override // com.fleeksoft.ksoup.nodes.LeafNode, com.fleeksoft.ksoup.nodes.Node
    public void outerHtmlTail$ksoup_release(QuietAppendable accum, Document.OutputSettings out) {
        Intrinsics.checkNotNullParameter(accum, "accum");
        Intrinsics.checkNotNullParameter(out, "out");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public XmlDeclaration(String name, boolean z) {
        super(name);
        Intrinsics.checkNotNullParameter(name, "name");
        this.isDeclaration = z;
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public String nodeName() {
        return "#declaration";
    }

    public final String name() {
        return coreValue();
    }

    public final String getWholeDeclaration() {
        StringBuilder sbBorrowBuilder = StringUtil.INSTANCE.borrowBuilder();
        getWholeDeclaration(QuietAppendable.INSTANCE.wrap(sbBorrowBuilder), new Document.OutputSettings(null, null, false, false, 0, 0, null, 127, null));
        return StringsKt.trim((CharSequence) StringUtil.INSTANCE.releaseBuilder(sbBorrowBuilder)).toString();
    }

    private final void getWholeDeclaration(QuietAppendable accum, Document.OutputSettings out) {
        for (Attribute attribute : attributes()) {
            String key = attribute.getKey();
            String value = attribute.getValue();
            if (!Intrinsics.areEqual(key, nodeName())) {
                accum.append(' ');
                accum.append(key);
                if (value.length() > 0) {
                    accum.append("=\"");
                    Entities.INSTANCE.escape(accum, value, out, 2);
                    accum.append('\"');
                }
            }
        }
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public void outerHtmlHead$ksoup_release(QuietAppendable accum, Document.OutputSettings out) {
        Intrinsics.checkNotNullParameter(accum, "accum");
        Intrinsics.checkNotNullParameter(out, "out");
        accum.append("<").append(this.isDeclaration ? "!" : "?").append(coreValue());
        getWholeDeclaration(accum, out);
        accum.append(this.isDeclaration ? "" : "?").append(">");
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public String toString() {
        return outerHtml();
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public Node createClone$ksoup_release() {
        Object value$ksoup_release = getValue();
        Intrinsics.checkNotNull(value$ksoup_release, "null cannot be cast to non-null type kotlin.String");
        return new XmlDeclaration((String) value$ksoup_release, this.isDeclaration);
    }

    @Override // com.fleeksoft.ksoup.nodes.Node, com.fleeksoft.ksoup.ported.KCloneable
    /* JADX INFO: renamed from: clone */
    public Node clone2() {
        return clone2();
    }
}
