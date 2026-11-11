package com.fleeksoft.ksoup.select;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.fleeksoft.ksoup.nodes.Node;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NodeVisitor.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0005H\u0016¨\u0006\u000bÀ\u0006\u0003"}, d2 = {"Lcom/fleeksoft/ksoup/select/NodeVisitor;", "", TtmlNode.TAG_HEAD, "", "node", "Lcom/fleeksoft/ksoup/nodes/Node;", "depth", "", "tail", "traverse", "root", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface NodeVisitor {
    void head(Node node, int depth);

    void tail(Node node, int depth);

    void traverse(Node root);

    /* JADX INFO: renamed from: com.fleeksoft.ksoup.select.NodeVisitor$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: NodeVisitor.kt */
    public final /* synthetic */ class CC {
        public static void $default$traverse(NodeVisitor _this, Node root) {
            Intrinsics.checkNotNullParameter(root, "root");
            NodeTraversor.INSTANCE.traverse(_this, root);
        }
    }

    /* JADX INFO: compiled from: NodeVisitor.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static void tail(NodeVisitor nodeVisitor, Node node, int i) {
            Intrinsics.checkNotNullParameter(node, "node");
            Intrinsics.checkNotNullParameter(node, "node");
        }

        @Deprecated
        public static void traverse(NodeVisitor nodeVisitor, Node root) {
            Intrinsics.checkNotNullParameter(root, "root");
            CC.$default$traverse(nodeVisitor, root);
        }
    }
}
