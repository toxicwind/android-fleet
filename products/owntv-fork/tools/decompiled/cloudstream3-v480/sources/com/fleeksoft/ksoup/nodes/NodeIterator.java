package com.fleeksoft.ksoup.nodes;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import com.fleeksoft.ksoup.nodes.Node;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: NodeIterator.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010)\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u0000 \u0017*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001\u0017B\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0004\u001a\u00020\u0002J\n\u0010\u0011\u001a\u00020\u0012H\u0096\u0082\u0004J\u000f\u0010\n\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\u0013J\b\u0010\u0014\u001a\u00020\u0010H\u0002J\u000f\u0010\u0015\u001a\u0004\u0018\u00018\u0000H\u0002¢\u0006\u0002\u0010\u0013J\n\u0010\u0016\u001a\u00020\u0010H\u0096\u0080\u0004R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0002X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\u0002X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0002X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0002X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/NodeIterator;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/fleeksoft/ksoup/nodes/Node;", "", "start", "type", "Lkotlin/reflect/KClass;", "<init>", "(Lcom/fleeksoft/ksoup/nodes/Node;Lkotlin/reflect/KClass;)V", "root", "next", "Lcom/fleeksoft/ksoup/nodes/Node;", "current", "previous", "currentParent", "restart", "", "hasNext", "", "()Lcom/fleeksoft/ksoup/nodes/Node;", "maybeFindNext", "findNextNode", "remove", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class NodeIterator<T extends Node> implements Iterator<T>, KMutableIterator {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private Node current;
    private Node currentParent;
    private T next;
    private Node previous;
    private Node root;
    private final KClass<T> type;

    public NodeIterator(Node start, KClass<T> type) {
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(type, "type");
        this.type = type;
        restart(start);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void restart(Node start) {
        Intrinsics.checkNotNullParameter(start, "start");
        if (this.type.isInstance(start)) {
            this.next = start;
        }
        this.current = start;
        this.previous = start;
        this.root = start;
        this.currentParent = start != 0 ? start.parent() : null;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        maybeFindNext();
        return this.next != null;
    }

    @Override // java.util.Iterator
    public T next() {
        maybeFindNext();
        T t = this.next;
        if (t == null) {
            throw new NoSuchElementException();
        }
        Intrinsics.checkNotNull(t);
        this.previous = this.current;
        T t2 = this.next;
        this.current = t2;
        this.currentParent = t2 != null ? t2.parent() : null;
        this.next = null;
        return t;
    }

    private final void maybeFindNext() {
        Node node;
        if (this.next != null) {
            return;
        }
        if (this.currentParent != null && ((node = this.current) == null || !node.hasParent())) {
            this.current = this.previous;
        }
        this.next = (T) findNextNode();
    }

    private final T findNextNode() {
        Node nodeParent = (T) this.current;
        do {
            Intrinsics.checkNotNull(nodeParent);
            if (nodeParent.childNodeSize() > 0) {
                nodeParent = (T) nodeParent.childNode(0);
            } else if (Intrinsics.areEqual(this.root, nodeParent)) {
                nodeParent = (T) null;
            } else if (nodeParent.nextSibling() != null) {
                nodeParent = (T) nodeParent.nextSibling();
            } else {
                do {
                    nodeParent = nodeParent.parent();
                    if (nodeParent == null || Intrinsics.areEqual(this.root, nodeParent)) {
                    }
                    return null;
                } while (nodeParent.nextSibling() == null);
                nodeParent = (T) nodeParent.nextSibling();
            }
            if (nodeParent == null) {
                return null;
            }
        } while (!this.type.isInstance(nodeParent));
        if (nodeParent instanceof Node) {
            return (T) nodeParent;
        }
        return null;
    }

    @Override // java.util.Iterator
    public void remove() {
        Node node = this.current;
        if (node != null) {
            node.remove();
        }
    }

    /* JADX INFO: compiled from: NodeIterator.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\u0006¨\u0006\b"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/NodeIterator$Companion;", "", "<init>", "()V", TypedValues.TransitionType.S_FROM, "Lcom/fleeksoft/ksoup/nodes/NodeIterator;", "Lcom/fleeksoft/ksoup/nodes/Node;", "start", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final NodeIterator<Node> from(Node start) {
            Intrinsics.checkNotNullParameter(start, "start");
            return new NodeIterator<>(start, Reflection.getOrCreateKotlinClass(Node.class));
        }
    }
}
