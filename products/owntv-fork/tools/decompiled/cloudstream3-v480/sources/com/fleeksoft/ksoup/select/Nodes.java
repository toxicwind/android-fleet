package com.fleeksoft.ksoup.select;

import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.fleeksoft.ksoup.helper.Validate;
import com.fleeksoft.ksoup.nodes.Node;
import com.fleeksoft.ksoup.ported.NodesIterator;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.jvm.internal.markers.KMutableList;

/* JADX INFO: compiled from: Nodes.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010)\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010+\n\u0002\b\u0006\b\u0017\u0018\u0000 H*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001HB\u0017\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0017\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\u0005\u0010\tB\u0017\b\u0017\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b¢\u0006\u0004\b\u0005\u0010\fB\u001d\b\u0017\u0012\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\r\"\u00028\u0000¢\u0006\u0004\b\u0005\u0010\u000eJ\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0096\u0082\u0004J\n\u0010\u0013\u001a\u00020\bH\u0096\u0080\u0004J\u0010\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0015H\u0096\u0082\u0004J\u000e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0016J\u0018\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0018j\b\u0012\u0004\u0012\u00028\u0000`\u0019H\u0016J\u000e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0016J\u0006\u0010\u001b\u001a\u00020\u001cJ\n\u0010\u001d\u001a\u00020\u001cH\u0096\u0080\u0004J\u0016\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u001f\u001a\u00020\u001cH\u0016J\u0016\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u001f\u001a\u00020\u001cH\u0016J\u0016\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u001f\u001a\u00020\u001cH\u0016J\u000f\u0010\"\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010#J\u000f\u0010$\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010#J\u001f\u0010%\u001a\u00028\u00002\u0006\u0010&\u001a\u00020\b2\u0006\u0010'\u001a\u00028\u0000H\u0096\u0082\b¢\u0006\u0002\u0010(J\u0017\u0010)\u001a\u00028\u00002\u0006\u0010&\u001a\u00020\bH\u0096\u0080\b¢\u0006\u0002\u0010*J\u0017\u0010\u001a\u001a\u00020\u00102\u0006\u0010'\u001a\u00028\u0000H\u0096\u0080\b¢\u0006\u0002\u0010+J\u0015\u0010,\u001a\u00028\u00002\u0006\u0010&\u001a\u00020\bH\u0016¢\u0006\u0002\u0010*J\u0012\u0010,\u001a\u00020\u00102\b\u0010'\u001a\u0004\u0018\u00010\u0012H\u0017J\n\u0010-\u001a\u00020.H\u0096\u0080\u0004J\u0006\u0010/\u001a\u00020.J\u0018\u00100\u001a\u00020\u00102\f\u00101\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0096\u0080\bJ\u0018\u00102\u001a\u00020\u00102\f\u00101\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0096\u0080\bJ\u001a\u00103\u001a\u00020\u00102\u0012\u00104\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u001005J\u001a\u00106\u001a\u00020.2\u0012\u00107\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000005J\u0017\u00108\u001a\u00020\u00102\u0006\u0010'\u001a\u00028\u0000H\u0097\u0081\b¢\u0006\u0002\u0010+J\u001f\u00108\u001a\u00020.2\u0006\u0010&\u001a\u00020\b2\u0006\u0010'\u001a\u00028\u0000H\u0096\u0081\u0004¢\u0006\u0002\u00109J\u0018\u0010:\u001a\u00020\u00102\f\u00101\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0097\u0081\bJ \u0010:\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\b2\f\u00101\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0097\u0081\bJ\u0017\u0010;\u001a\u00020\u00102\u0006\u0010'\u001a\u00028\u0000H\u0096\u0083\u0004¢\u0006\u0002\u0010+J\u0018\u0010<\u001a\u00020\u00102\f\u00101\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0096\u0081\u0004J\u0017\u0010=\u001a\u00028\u00002\u0006\u0010&\u001a\u00020\bH\u0096\u0083\u0004¢\u0006\u0002\u0010*J\u0017\u0010>\u001a\u00020\b2\u0006\u0010'\u001a\u00028\u0000H\u0096\u0081\u0004¢\u0006\u0002\u0010?J\n\u0010@\u001a\u00020\u0010H\u0096\u0081\u0004J\u0017\u0010A\u001a\u00020\b2\u0006\u0010'\u001a\u00028\u0000H\u0096\u0081\u0004¢\u0006\u0002\u0010?J\u0010\u0010B\u001a\b\u0012\u0004\u0012\u00028\u00000CH\u0096\u0081\u0004J\u0018\u0010B\u001a\b\u0012\u0004\u0012\u00028\u00000C2\u0006\u0010&\u001a\u00020\bH\u0096\u0081\u0004J \u0010D\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010E\u001a\u00020\b2\u0006\u0010F\u001a\u00020\bH\u0096\u0081\u0004R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\n\u0010G\u001a\u00020\bX\u0096\u0085\b¨\u0006I"}, d2 = {"Lcom/fleeksoft/ksoup/select/Nodes;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/fleeksoft/ksoup/nodes/Node;", "", "delegateList", "<init>", "(Ljava/util/List;)V", "initialCapacity", "", "(I)V", "nodes", "", "(Ljava/util/Collection;)V", "", "([Lcom/fleeksoft/ksoup/nodes/Node;)V", "equals", "", "other", "", "hashCode", "iterator", "", "clone", "asList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "remove", "outerHtml", "", "toString", TtmlNode.ANNOTATION_POSITION_BEFORE, "html", TtmlNode.ANNOTATION_POSITION_AFTER, "wrap", "first", "()Lcom/fleeksoft/ksoup/nodes/Node;", "last", "set", "index", "element", "(ILcom/fleeksoft/ksoup/nodes/Node;)Lcom/fleeksoft/ksoup/nodes/Node;", "removeAt", "(I)Lcom/fleeksoft/ksoup/nodes/Node;", "(Lcom/fleeksoft/ksoup/nodes/Node;)Z", "deselect", "clear", "", "deselectAll", "removeAll", "elements", "retainAll", "removeIf", "predicate", "Lkotlin/Function1;", "replaceAll", "operator", "add", "(ILcom/fleeksoft/ksoup/nodes/Node;)V", "addAll", "contains", "containsAll", "get", "indexOf", "(Lcom/fleeksoft/ksoup/nodes/Node;)I", "isEmpty", "lastIndexOf", "listIterator", "", "subList", "fromIndex", "toIndex", ContentDisposition.Parameters.Size, "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class Nodes<T extends Node> implements List<T>, KMutableList {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final List<T> delegateList;

    /* JADX WARN: Multi-variable type inference failed */
    public Nodes() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Override // java.util.List
    public void add(int index, T element) {
        Intrinsics.checkNotNullParameter(element, "element");
        this.delegateList.add(index, element);
    }

    @Override // java.util.List, java.util.Collection
    @IgnorableReturnValue
    public boolean add(T element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return this.delegateList.add(element);
    }

    @Override // java.util.List
    @IgnorableReturnValue
    public boolean addAll(int index, Collection<? extends T> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return this.delegateList.addAll(index, elements);
    }

    @Override // java.util.List, java.util.Collection
    @IgnorableReturnValue
    public boolean addAll(Collection<? extends T> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return this.delegateList.addAll(elements);
    }

    public boolean contains(T element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return this.delegateList.contains(element);
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return this.delegateList.containsAll(elements);
    }

    @Override // java.util.List
    public T get(int index) {
        return this.delegateList.get(index);
    }

    public int getSize() {
        return this.delegateList.size();
    }

    public int indexOf(T element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return this.delegateList.indexOf(element);
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return this.delegateList.isEmpty();
    }

    public int lastIndexOf(T element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return this.delegateList.lastIndexOf(element);
    }

    @Override // java.util.List
    public ListIterator<T> listIterator() {
        return this.delegateList.listIterator();
    }

    @Override // java.util.List
    public ListIterator<T> listIterator(int index) {
        return this.delegateList.listIterator(index);
    }

    @Override // java.util.List
    public List<T> subList(int fromIndex, int toIndex) {
        return this.delegateList.subList(fromIndex, toIndex);
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        return (T[]) CollectionToArray.toArray(this, array);
    }

    public Nodes(List<T> delegateList) {
        Intrinsics.checkNotNullParameter(delegateList, "delegateList");
        this.delegateList = delegateList;
    }

    @Override // java.util.List, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Node) {
            return contains((Node) obj);
        }
        return false;
    }

    @Override // java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof Node) {
            return indexOf((Node) obj);
        }
        return -1;
    }

    @Override // java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof Node) {
            return lastIndexOf((Node) obj);
        }
        return -1;
    }

    @Override // java.util.List
    public final /* bridge */ T remove(int i) {
        return (T) removeAt(i);
    }

    @Override // java.util.List, java.util.Collection
    public final /* bridge */ boolean remove(Object obj) {
        if (obj instanceof Node) {
            return remove((Node) obj);
        }
        return false;
    }

    @Override // java.util.List, java.util.Collection
    public final /* bridge */ int size() {
        return getSize();
    }

    public /* synthetic */ Nodes(ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new ArrayList() : arrayList);
    }

    @Override // java.util.List, java.util.Collection
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Nodes)) {
            return false;
        }
        Nodes nodes = (Nodes) other;
        if (size() != nodes.size()) {
            return false;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (!Intrinsics.areEqual(get(i), nodes.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.List, java.util.Collection
    public int hashCode() {
        return this.delegateList.hashCode();
    }

    /* JADX INFO: compiled from: Nodes.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\b\b\u0001\u0010\u0006*\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00060\tH\u0086\u0002J\u0019\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\b\b\u0001\u0010\u0006*\u00020\u0007H\u0086\u0002¨\u0006\n"}, d2 = {"Lcom/fleeksoft/ksoup/select/Nodes$Companion;", "", "<init>", "()V", "invoke", "Lcom/fleeksoft/ksoup/select/Nodes;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/fleeksoft/ksoup/nodes/Node;", "collection", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final <T extends Node> Nodes<T> invoke(Collection<? extends T> collection) {
            Intrinsics.checkNotNullParameter(collection, "collection");
            return new Nodes<>(collection);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final <T extends Node> Nodes<T> invoke() {
            return new Nodes<>(null, 1, 0 == true ? 1 : 0);
        }
    }

    public Nodes(int i) {
        this((List) new ArrayList(i));
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Nodes(Collection<? extends T> nodes) {
        this((List) new ArrayList(nodes));
        Intrinsics.checkNotNullParameter(nodes, "nodes");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Nodes(T... nodes) {
        this((List) new ArrayList(ArraysKt.asList(nodes)));
        Intrinsics.checkNotNullParameter(nodes, "nodes");
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<T> iterator() {
        return new NodesIterator(this.delegateList.iterator());
    }

    public Nodes<T> clone() {
        Nodes<T> nodes = new Nodes<>(size());
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            Node nodeClone2 = it.next().clone2();
            Intrinsics.checkNotNull(nodeClone2, "null cannot be cast to non-null type T of com.fleeksoft.ksoup.select.Nodes");
            nodes.add(nodeClone2);
        }
        return nodes;
    }

    public ArrayList<T> asList() {
        return new ArrayList<>(this);
    }

    public Nodes<T> remove() {
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            it.next().remove();
        }
        return this;
    }

    static final CharSequence outerHtml$lambda$0(Node it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.outerHtml();
    }

    public final String outerHtml() {
        return CollectionsKt.joinToString$default(this, "\n", null, null, 0, null, new Function1() { // from class: com.fleeksoft.ksoup.select.Nodes$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Nodes.outerHtml$lambda$0((Node) obj);
            }
        }, 30, null);
    }

    public String toString() {
        return outerHtml();
    }

    public Nodes<T> before(String html) {
        Intrinsics.checkNotNullParameter(html, "html");
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            it.next().before(html);
        }
        return this;
    }

    public Nodes<T> after(String html) {
        Intrinsics.checkNotNullParameter(html, "html");
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            it.next().after(html);
        }
        return this;
    }

    public Nodes<T> wrap(String html) {
        Intrinsics.checkNotNullParameter(html, "html");
        Validate.INSTANCE.notEmpty(html);
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            it.next().wrap(html);
        }
        return this;
    }

    public T first() {
        if (isEmpty()) {
            return null;
        }
        return (T) get(0);
    }

    public T last() {
        if (isEmpty()) {
            return null;
        }
        return (T) get(size() - 1);
    }

    @Override // java.util.List
    public T set(int index, T element) {
        Intrinsics.checkNotNullParameter(element, "element");
        Validate.expectNotNull$default(Validate.INSTANCE, element, null, 2, null);
        T t = this.delegateList.set(index, element);
        t.replaceWith(element);
        return t;
    }

    public T removeAt(int index) {
        T tRemove = this.delegateList.remove(index);
        tRemove.remove();
        return tRemove;
    }

    public boolean remove(T element) {
        Intrinsics.checkNotNullParameter(element, "element");
        int iIndexOf = indexOf((Object) element);
        if (iIndexOf == -1) {
            return false;
        }
        remove(iIndexOf);
        return true;
    }

    public T deselect(int index) {
        return this.delegateList.remove(index);
    }

    public boolean deselect(Object element) {
        return TypeIntrinsics.asMutableCollection(this.delegateList).remove(element);
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        remove();
        this.delegateList.clear();
    }

    public final void deselectAll() {
        this.delegateList.clear();
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        Iterator<?> it = elements.iterator();
        while (true) {
            boolean z = false;
            while (it.hasNext()) {
                if (remove(it.next()) || z) {
                    z = true;
                }
            }
            return z;
        }
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<?> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        ArrayList arrayList = new ArrayList();
        for (T t : this) {
            if (!elements.contains(t)) {
                arrayList.add(t);
            }
        }
        ArrayList arrayList2 = arrayList;
        if (arrayList2.isEmpty()) {
            return false;
        }
        removeAll(arrayList2);
        return true;
    }

    public final boolean removeIf(Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (T t : this) {
            if (predicate.invoke(t).booleanValue()) {
                arrayList.add(t);
            }
        }
        ArrayList arrayList2 = arrayList;
        if (arrayList2.isEmpty()) {
            return false;
        }
        removeAll(arrayList2);
        return true;
    }

    public final void replaceAll(Function1<? super T, ? extends T> operator) {
        Intrinsics.checkNotNullParameter(operator, "operator");
        int size = size();
        for (int i = 0; i < size; i++) {
            set(i, (Node) operator.invoke(get(i)));
        }
    }
}
