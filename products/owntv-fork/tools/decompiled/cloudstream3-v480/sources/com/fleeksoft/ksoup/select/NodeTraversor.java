package com.fleeksoft.ksoup.select;

import com.fleeksoft.ksoup.nodes.Element;
import com.fleeksoft.ksoup.nodes.Node;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NodeTraversor.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\f\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u000b¨\u0006\u000f"}, d2 = {"Lcom/fleeksoft/ksoup/select/NodeTraversor;", "", "<init>", "()V", "traverse", "", "visitor", "Lcom/fleeksoft/ksoup/select/NodeVisitor;", "root", "Lcom/fleeksoft/ksoup/nodes/Node;", "elements", "Lcom/fleeksoft/ksoup/select/Elements;", "filter", "Lcom/fleeksoft/ksoup/select/NodeFilter$FilterResult;", "Lcom/fleeksoft/ksoup/select/NodeFilter;", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class NodeTraversor {
    public static final NodeTraversor INSTANCE = new NodeTraversor();

    private NodeTraversor() {
    }

    public final void traverse(NodeVisitor visitor, Node root) {
        Intrinsics.checkNotNullParameter(visitor, "visitor");
        Intrinsics.checkNotNullParameter(root, "root");
        int i = 0;
        Node nodeChildNode = root;
        while (nodeChildNode != null) {
            Node nodeParentNode = nodeChildNode.parentNode();
            int iChildNodeSize = nodeParentNode != null ? nodeParentNode.childNodeSize() : 0;
            Node nodeNextSibling = nodeChildNode.nextSibling();
            visitor.head(nodeChildNode, i);
            if (nodeParentNode != null && !nodeChildNode.hasParent()) {
                if (iChildNodeSize == nodeParentNode.childNodeSize()) {
                    nodeChildNode = nodeParentNode.childNode(nodeChildNode.siblingIndex());
                } else if (nodeNextSibling == null) {
                    do {
                        i--;
                        visitor.tail(nodeParentNode, i);
                        if (nodeParentNode == root) {
                            break;
                        }
                        if (nodeParentNode.nextSibling() != null) {
                            nodeParentNode = nodeParentNode.nextSibling();
                            break;
                        }
                        nodeParentNode = nodeParentNode.parentNode();
                    } while (nodeParentNode != null);
                    if (nodeParentNode == root || nodeParentNode == null) {
                        return;
                    } else {
                        nodeChildNode = nodeParentNode;
                    }
                } else {
                    nodeChildNode = nodeNextSibling;
                }
            } else if (nodeChildNode.childNodeSize() > 0) {
                nodeChildNode = nodeChildNode.childNode(0);
                i++;
            } else {
                while (true) {
                    if (nodeChildNode == null) {
                        throw new Exception("as depth > 0, will have parent");
                    }
                    if (nodeChildNode.nextSibling() != null || i <= 0) {
                        break;
                    }
                    visitor.tail(nodeChildNode, i);
                    nodeChildNode = nodeChildNode.parentNode();
                    i--;
                }
                visitor.tail(nodeChildNode, i);
                if (Intrinsics.areEqual(nodeChildNode, root)) {
                    return;
                } else {
                    nodeChildNode = nodeChildNode.nextSibling();
                }
            }
        }
    }

    public final void traverse(NodeVisitor visitor, Elements elements) {
        Intrinsics.checkNotNullParameter(visitor, "visitor");
        Intrinsics.checkNotNullParameter(elements, "elements");
        Iterator<T> it = elements.iterator();
        while (it.hasNext()) {
            INSTANCE.traverse(visitor, (Element) it.next());
        }
    }

    public final NodeFilter.FilterResult filter(NodeFilter filter, Node root) {
        Node nodeNextSibling;
        Intrinsics.checkNotNullParameter(filter, "filter");
        Intrinsics.checkNotNullParameter(root, "root");
        int i = 0;
        Node nodeChildNode = root;
        while (nodeChildNode != null) {
            NodeFilter.FilterResult filterResultHead = filter.head(nodeChildNode, i);
            if (filterResultHead == NodeFilter.FilterResult.STOP) {
                return filterResultHead;
            }
            if (filterResultHead == NodeFilter.FilterResult.CONTINUE && nodeChildNode.childNodeSize() > 0) {
                nodeChildNode = nodeChildNode.childNode(0);
                i++;
            } else {
                while (true) {
                    if ((nodeChildNode != null ? nodeChildNode.nextSibling() : null) != null || i <= 0) {
                        break;
                    }
                    if ((filterResultHead == NodeFilter.FilterResult.CONTINUE || filterResultHead == NodeFilter.FilterResult.SKIP_CHILDREN) && (filterResultHead = filter.tail(nodeChildNode, i)) == NodeFilter.FilterResult.STOP) {
                        return filterResultHead;
                    }
                    nodeNextSibling = nodeChildNode != null ? nodeChildNode.parentNode() : null;
                    i--;
                    if (filterResultHead == NodeFilter.FilterResult.REMOVE && nodeChildNode != null) {
                        nodeChildNode.remove();
                    }
                    filterResultHead = NodeFilter.FilterResult.CONTINUE;
                    nodeChildNode = nodeNextSibling;
                }
                if ((filterResultHead == NodeFilter.FilterResult.CONTINUE || filterResultHead == NodeFilter.FilterResult.SKIP_CHILDREN) && (filterResultHead = filter.tail(nodeChildNode, i)) == NodeFilter.FilterResult.STOP) {
                    return filterResultHead;
                }
                if (Intrinsics.areEqual(nodeChildNode, root)) {
                    return filterResultHead;
                }
                nodeNextSibling = nodeChildNode != null ? nodeChildNode.nextSibling() : null;
                if (filterResultHead == NodeFilter.FilterResult.REMOVE && nodeChildNode != null) {
                    nodeChildNode.remove();
                }
                nodeChildNode = nodeNextSibling;
            }
        }
        return NodeFilter.FilterResult.CONTINUE;
    }

    public final void filter(NodeFilter filter, Elements elements) {
        Intrinsics.checkNotNullParameter(filter, "filter");
        Intrinsics.checkNotNullParameter(elements, "elements");
        Iterator<T> it = elements.iterator();
        while (it.hasNext()) {
            if (INSTANCE.filter(filter, (Element) it.next()) == NodeFilter.FilterResult.STOP) {
                return;
            }
        }
    }
}
