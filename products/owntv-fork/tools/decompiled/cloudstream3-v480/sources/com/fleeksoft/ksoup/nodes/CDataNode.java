package com.fleeksoft.ksoup.nodes;

import com.fleeksoft.ksoup.internal.QuietAppendable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CDataNode.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u001d\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0010¢\u0006\u0002\b\rJ\b\u0010\u000e\u001a\u00020\u0000H\u0016¨\u0006\u000f"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/CDataNode;", "Lcom/fleeksoft/ksoup/nodes/TextNode;", "text", "", "<init>", "(Ljava/lang/String;)V", "nodeName", "outerHtmlHead", "", "accum", "Lcom/fleeksoft/ksoup/internal/QuietAppendable;", "out", "Lcom/fleeksoft/ksoup/nodes/Document$OutputSettings;", "outerHtmlHead$ksoup_release", "clone", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CDataNode extends TextNode {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CDataNode(String str) {
        super(str);
        Intrinsics.checkNotNull(str);
    }

    @Override // com.fleeksoft.ksoup.nodes.TextNode, com.fleeksoft.ksoup.nodes.Node
    public String nodeName() {
        return "#cdata";
    }

    @Override // com.fleeksoft.ksoup.nodes.TextNode
    public String text() {
        return getWholeText();
    }

    @Override // com.fleeksoft.ksoup.nodes.TextNode, com.fleeksoft.ksoup.nodes.Node
    public void outerHtmlHead$ksoup_release(QuietAppendable accum, Document.OutputSettings out) {
        Intrinsics.checkNotNullParameter(accum, "accum");
        Intrinsics.checkNotNullParameter(out, "out");
        accum.append("<![CDATA[").append(getWholeText()).append("]]>");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fleeksoft.ksoup.nodes.TextNode, com.fleeksoft.ksoup.nodes.Node, com.fleeksoft.ksoup.ported.KCloneable
    /* JADX INFO: renamed from: clone */
    public Node clone2() {
        return clone2();
    }
}
