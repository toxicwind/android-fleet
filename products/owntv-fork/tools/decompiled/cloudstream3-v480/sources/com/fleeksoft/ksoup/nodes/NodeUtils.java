package com.fleeksoft.ksoup.nodes;

import androidx.exifinterface.media.ExifInterface;
import com.fleeksoft.ksoup.parser.HtmlTreeBuilder;
import com.fleeksoft.ksoup.parser.Parser;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* JADX INFO: compiled from: NodeUtils.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007J,\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\f0\u000b\"\b\b\u0000\u0010\f*\u00020\u00072\u0006\u0010\r\u001a\u00020\u00072\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\f0\u000f¨\u0006\u0010"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/NodeUtils;", "", "<init>", "()V", "outputSettings", "Lcom/fleeksoft/ksoup/nodes/Document$OutputSettings;", "node", "Lcom/fleeksoft/ksoup/nodes/Node;", "parser", "Lcom/fleeksoft/ksoup/parser/Parser;", "stream", "Lkotlin/sequences/Sequence;", ExifInterface.GPS_DIRECTION_TRUE, "start", "type", "Lkotlin/reflect/KClass;", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class NodeUtils {
    public static final NodeUtils INSTANCE = new NodeUtils();

    private NodeUtils() {
    }

    public final Document.OutputSettings outputSettings(Node node) {
        Document.OutputSettings outputSettings;
        Intrinsics.checkNotNullParameter(node, "node");
        Document documentOwnerDocument = node.ownerDocument();
        return (documentOwnerDocument == null || (outputSettings = documentOwnerDocument.getOutputSettings()) == null) ? new Document("").getOutputSettings() : outputSettings;
    }

    public final Parser parser(Node node) {
        Parser parser;
        Intrinsics.checkNotNullParameter(node, "node");
        Document documentOwnerDocument = node.ownerDocument();
        return (documentOwnerDocument == null || (parser = documentOwnerDocument.getParser()) == null) ? new Parser(new HtmlTreeBuilder()) : parser;
    }

    public final <T extends Node> Sequence<T> stream(Node start, KClass<T> type) {
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(type, "type");
        return SequencesKt.asSequence(new NodeIterator(start, type));
    }
}
