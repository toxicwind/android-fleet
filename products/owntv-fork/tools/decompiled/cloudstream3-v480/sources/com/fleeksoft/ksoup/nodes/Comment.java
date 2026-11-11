package com.fleeksoft.ksoup.nodes;

import com.fleeksoft.ksoup.internal.QuietAppendable;
import com.fleeksoft.ksoup.parser.Parser;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Comment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u0006\u0010\u0007\u001a\u00020\u0003J\u0010\u0010\b\u001a\u00020\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003J\u001d\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0010¢\u0006\u0002\b\u000fJ\r\u0010\u0010\u001a\u00020\u0011H\u0010¢\u0006\u0002\b\u0012J\b\u0010\u0013\u001a\u00020\u0000H\u0016J\u0006\u0010\u0014\u001a\u00020\u0015J\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017¨\u0006\u0019"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/Comment;", "Lcom/fleeksoft/ksoup/nodes/LeafNode;", "data", "", "<init>", "(Ljava/lang/String;)V", "nodeName", "getData", "setData", "outerHtmlHead", "", "accum", "Lcom/fleeksoft/ksoup/internal/QuietAppendable;", "out", "Lcom/fleeksoft/ksoup/nodes/Document$OutputSettings;", "outerHtmlHead$ksoup_release", "createClone", "Lcom/fleeksoft/ksoup/nodes/Node;", "createClone$ksoup_release", "clone", "isXmlDeclaration", "", "asXmlDeclaration", "Lcom/fleeksoft/ksoup/nodes/XmlDeclaration;", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Comment extends LeafNode {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Comment(String data) {
        super(data);
        Intrinsics.checkNotNullParameter(data, "data");
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public String nodeName() {
        return "#comment";
    }

    public final String getData() {
        return coreValue();
    }

    public final Comment setData(String data) {
        coreValue(data);
        return this;
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public void outerHtmlHead$ksoup_release(QuietAppendable accum, Document.OutputSettings out) {
        Intrinsics.checkNotNullParameter(accum, "accum");
        Intrinsics.checkNotNullParameter(out, "out");
        accum.append("<!--").append(getData()).append("-->");
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public Node createClone$ksoup_release() {
        Object value$ksoup_release = getValue();
        Intrinsics.checkNotNull(value$ksoup_release, "null cannot be cast to non-null type kotlin.String");
        return new Comment((String) value$ksoup_release);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fleeksoft.ksoup.nodes.Node, com.fleeksoft.ksoup.ported.KCloneable
    /* JADX INFO: renamed from: clone */
    public Node clone2() {
        Node nodeClone = super.clone2();
        Intrinsics.checkNotNull(nodeClone, "null cannot be cast to non-null type com.fleeksoft.ksoup.nodes.Comment");
        return (Comment) nodeClone;
    }

    public final boolean isXmlDeclaration() {
        return INSTANCE.isXmlDeclarationData(getData());
    }

    public final XmlDeclaration asXmlDeclaration() {
        List<Node> fragmentInput = Parser.INSTANCE.xmlParser().parseFragmentInput("<" + getData() + ">", (Element) null, "");
        if (fragmentInput.isEmpty() || !(fragmentInput.get(0) instanceof XmlDeclaration)) {
            return null;
        }
        Node node = fragmentInput.get(0);
        Intrinsics.checkNotNull(node, "null cannot be cast to non-null type com.fleeksoft.ksoup.nodes.XmlDeclaration");
        return (XmlDeclaration) node;
    }

    /* JADX INFO: compiled from: Comment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002¨\u0006\b"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/Comment$Companion;", "", "<init>", "()V", "isXmlDeclarationData", "", "data", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isXmlDeclarationData(String data) {
            return data.length() > 1 && (StringsKt.startsWith$default(data, "!", false, 2, (Object) null) || StringsKt.startsWith$default(data, "?", false, 2, (Object) null));
        }
    }
}
