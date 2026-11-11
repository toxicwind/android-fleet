package org.jsoup.select;

import java.util.Iterator;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

/* JADX INFO: loaded from: classes8.dex */
public class NodeTraversor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final byte AfterHead = 1;
    private static final byte VisitHead = 0;
    private static final byte VisitTail = 2;

    public static void traverse(NodeVisitor nodeVisitor, Node node) {
        Validate.notNull(nodeVisitor);
        Validate.notNull(node);
        Node nodeNextSibling = node.nextSibling();
        char c = 0;
        int i = 0;
        while (true) {
            if (c == 0) {
                Node nodeParentNode = node.parentNode();
                Node nodeNextSibling2 = node.nextSibling();
                int iSiblingIndex = nodeParentNode != null ? node.siblingIndex() : 0;
                nodeVisitor.head(node, i);
                if (nodeParentNode != null && node.parentNode() != nodeParentNode) {
                    node = iSiblingIndex < nodeParentNode.childNodeSize() ? nodeParentNode.childNode(iSiblingIndex) : null;
                    Node node2 = i == 0 ? nodeNextSibling : nodeNextSibling2;
                    if (node == null || node == node2) {
                        if (i == 0) {
                            return;
                        }
                        if (nodeNextSibling2 == null || nodeNextSibling2.parentNode() != nodeParentNode) {
                            i--;
                            c = 2;
                            node = nodeParentNode;
                        } else {
                            node = nodeNextSibling2;
                        }
                    }
                }
                c = 1;
            } else {
                if (c == 1 && node.childNodeSize() > 0) {
                    node = node.childNode(0);
                    i++;
                } else {
                    nodeVisitor.tail(node, i);
                    Node nodeNextSibling3 = node.nextSibling();
                    if (i == 0) {
                        if (nodeNextSibling3 == null || nodeNextSibling3 == nodeNextSibling) {
                            return;
                        }
                    } else if (nodeNextSibling3 == null) {
                        node = node.parentNode();
                        i--;
                        c = 2;
                    }
                    node = nodeNextSibling3;
                }
                c = 0;
            }
        }
    }

    public static void traverse(NodeVisitor nodeVisitor, Elements elements) {
        Validate.notNull(nodeVisitor);
        Validate.notNull(elements);
        Iterator it = elements.iterator();
        while (it.hasNext()) {
            traverse(nodeVisitor, (Element) it.next());
        }
    }

    public static NodeFilter.FilterResult filter(NodeFilter nodeFilter, Node node) {
        Node nodeChildNode = node;
        int i = 0;
        while (nodeChildNode != null) {
            NodeFilter.FilterResult filterResultHead = nodeFilter.head(nodeChildNode, i);
            if (filterResultHead == NodeFilter.FilterResult.STOP) {
                return filterResultHead;
            }
            if (filterResultHead == NodeFilter.FilterResult.CONTINUE && nodeChildNode.childNodeSize() > 0) {
                nodeChildNode = nodeChildNode.childNode(0);
                i++;
            } else {
                while (nodeChildNode.nextSibling() == null && i > 0) {
                    if ((filterResultHead == NodeFilter.FilterResult.CONTINUE || filterResultHead == NodeFilter.FilterResult.SKIP_CHILDREN) && (filterResultHead = nodeFilter.tail(nodeChildNode, i)) == NodeFilter.FilterResult.STOP) {
                        return filterResultHead;
                    }
                    Node nodeParentNode = nodeChildNode.parentNode();
                    i--;
                    if (filterResultHead == NodeFilter.FilterResult.REMOVE) {
                        nodeChildNode.remove();
                    }
                    filterResultHead = NodeFilter.FilterResult.CONTINUE;
                    nodeChildNode = nodeParentNode;
                }
                if ((filterResultHead == NodeFilter.FilterResult.CONTINUE || filterResultHead == NodeFilter.FilterResult.SKIP_CHILDREN) && (filterResultHead = nodeFilter.tail(nodeChildNode, i)) == NodeFilter.FilterResult.STOP) {
                    return filterResultHead;
                }
                if (nodeChildNode == node) {
                    return filterResultHead;
                }
                Node nodeNextSibling = nodeChildNode.nextSibling();
                if (filterResultHead == NodeFilter.FilterResult.REMOVE) {
                    nodeChildNode.remove();
                }
                nodeChildNode = nodeNextSibling;
            }
        }
        return NodeFilter.FilterResult.CONTINUE;
    }

    public static void filter(NodeFilter nodeFilter, Elements elements) {
        Validate.notNull(nodeFilter);
        Validate.notNull(elements);
        Iterator it = elements.iterator();
        while (it.hasNext() && filter(nodeFilter, (Element) it.next()) != NodeFilter.FilterResult.STOP) {
        }
    }
}
