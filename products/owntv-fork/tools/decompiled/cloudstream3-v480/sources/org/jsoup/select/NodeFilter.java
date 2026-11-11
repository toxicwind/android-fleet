package org.jsoup.select;

import org.jsoup.nodes.Node;

/* JADX INFO: loaded from: classes8.dex */
public interface NodeFilter {

    public enum FilterResult {
        CONTINUE,
        SKIP_CHILDREN,
        SKIP_ENTIRELY,
        REMOVE,
        STOP
    }

    FilterResult head(Node node, int i);

    FilterResult tail(Node node, int i);

    void traverse(Node node);

    /* JADX INFO: renamed from: org.jsoup.select.NodeFilter$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
    }
}
