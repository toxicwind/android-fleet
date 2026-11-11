package com.fleeksoft.ksoup.select;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.fleeksoft.ksoup.nodes.Node;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NodeFilter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0001\fJ\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u001a\u0010\b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005H\u0016¨\u0006\rÀ\u0006\u0003"}, d2 = {"Lcom/fleeksoft/ksoup/select/NodeFilter;", "", TtmlNode.TAG_HEAD, "Lcom/fleeksoft/ksoup/select/NodeFilter$FilterResult;", "node", "Lcom/fleeksoft/ksoup/nodes/Node;", "depth", "", "tail", "traverse", "", "root", "FilterResult", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface NodeFilter {

    /* JADX INFO: compiled from: NodeFilter.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/fleeksoft/ksoup/select/NodeFilter$FilterResult;", "", "<init>", "(Ljava/lang/String;I)V", "CONTINUE", "SKIP_CHILDREN", "SKIP_ENTIRELY", "REMOVE", "STOP", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public enum FilterResult {
        CONTINUE,
        SKIP_CHILDREN,
        SKIP_ENTIRELY,
        REMOVE,
        STOP;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<FilterResult> getEntries() {
            return $ENTRIES;
        }
    }

    FilterResult head(Node node, int depth);

    FilterResult tail(Node node, int depth);

    void traverse(Node root);

    /* JADX INFO: renamed from: com.fleeksoft.ksoup.select.NodeFilter$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: NodeFilter.kt */
    public final /* synthetic */ class CC {
        public static FilterResult $default$tail(NodeFilter _this, Node node, int i) {
            return FilterResult.CONTINUE;
        }

        public static void $default$traverse(NodeFilter _this, Node root) {
            Intrinsics.checkNotNullParameter(root, "root");
            NodeTraversor.INSTANCE.filter(_this, root);
        }
    }

    /* JADX INFO: compiled from: NodeFilter.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static FilterResult tail(NodeFilter nodeFilter, Node node, int i) {
            return CC.$default$tail(nodeFilter, node, i);
        }

        @Deprecated
        public static void traverse(NodeFilter nodeFilter, Node root) {
            Intrinsics.checkNotNullParameter(root, "root");
            CC.$default$traverse(nodeFilter, root);
        }
    }
}
