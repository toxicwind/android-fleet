package org.jsoup.select;

import org.jsoup.nodes.Node;

/* JADX INFO: loaded from: classes8.dex */
@FunctionalInterface
public interface NodeVisitor {
    void head(Node node, int i);

    void tail(Node node, int i);

    void traverse(Node node);

    /* JADX INFO: renamed from: org.jsoup.select.NodeVisitor$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$tail(NodeVisitor _this, Node node, int i) {
        }
    }
}
