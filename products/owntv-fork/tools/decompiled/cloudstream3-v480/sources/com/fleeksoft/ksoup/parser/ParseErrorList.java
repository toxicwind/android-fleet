package com.fleeksoft.ksoup.parser;

import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableList;

/* JADX INFO: compiled from: ParseErrorList.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0007\n\u0002\u0010)\n\u0002\b\u0002\n\u0002\u0010+\n\u0002\b\u000b\u0018\u0000 +2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001+B\u0019\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0010\u0012\u0006\u0010\b\u001a\u00020\u0000¢\u0006\u0004\b\u0006\u0010\tJ\u0006\u0010\f\u001a\u00020\rJ\r\u0010\u000e\u001a\u00020\u0000H\u0000¢\u0006\u0002\b\u000fJ\u0012\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0002H\u0097\u0081\bJ\u001a\u0010\u0010\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0002H\u0096\u0081\u0004J\u0018\u0010\u0014\u001a\u00020\r2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\u0016H\u0097\u0081\bJ \u0010\u0014\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00042\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\u0016H\u0097\u0081\bJ\n\u0010\u0017\u001a\u00020\u0012H\u0096\u0081\u0004J\u0012\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0002H\u0096\u0083\u0004J\u0018\u0010\u0019\u001a\u00020\r2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\u0016H\u0096\u0081\u0004J\u0012\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0004H\u0096\u0083\u0004J\u0012\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0002H\u0096\u0081\u0004J\n\u0010\u001c\u001a\u00020\rH\u0096\u0081\u0004J\u0010\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00020\u001eH\u0096\u0083\u0004J\u0012\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0002H\u0096\u0081\u0004J\u0010\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00020!H\u0096\u0081\u0004J\u0018\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00020!2\u0006\u0010\u0013\u001a\u00020\u0004H\u0096\u0081\u0004J\u0012\u0010\"\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0002H\u0097\u0081\bJ\u0018\u0010#\u001a\u00020\r2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\u0016H\u0097\u0081\bJ\u0012\u0010$\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0004H\u0097\u0081\bJ\u0018\u0010%\u001a\u00020\r2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\u0016H\u0097\u0081\bJ\u001a\u0010&\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0002H\u0097\u0083\bJ \u0010'\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010(\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u0004H\u0096\u0081\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\n\u0010*\u001a\u00020\u0004X\u0096\u0085\b¨\u0006,"}, d2 = {"Lcom/fleeksoft/ksoup/parser/ParseErrorList;", "", "Lcom/fleeksoft/ksoup/parser/ParseError;", "initialCapacity", "", "maxSize", "<init>", "(II)V", "copy", "(Lcom/fleeksoft/ksoup/parser/ParseErrorList;)V", "getMaxSize", "()I", "canAddError", "", "clone", "clone$ksoup_release", "add", "element", "", "index", "addAll", "elements", "", "clear", "contains", "containsAll", "get", "indexOf", "isEmpty", "iterator", "", "lastIndexOf", "listIterator", "", "remove", "removeAll", "removeAt", "retainAll", "set", "subList", "fromIndex", "toIndex", ContentDisposition.Parameters.Size, "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ParseErrorList implements List<ParseError>, KMutableList {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int INITIAL_CAPACITY = 16;
    private final /* synthetic */ List<ParseError> $$delegate_0;
    private final int initialCapacity;
    private final int maxSize;

    public /* synthetic */ ParseErrorList(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2);
    }

    @Override // java.util.List
    public void add(int index, ParseError element) {
        Intrinsics.checkNotNullParameter(element, "element");
        this.$$delegate_0.add(index, element);
    }

    @Override // java.util.List, java.util.Collection
    @IgnorableReturnValue
    public boolean add(ParseError element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return this.$$delegate_0.add(element);
    }

    @Override // java.util.List
    @IgnorableReturnValue
    public boolean addAll(int index, Collection<? extends ParseError> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return this.$$delegate_0.addAll(index, elements);
    }

    @Override // java.util.List, java.util.Collection
    @IgnorableReturnValue
    public boolean addAll(Collection<? extends ParseError> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return this.$$delegate_0.addAll(elements);
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        this.$$delegate_0.clear();
    }

    public boolean contains(ParseError element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return this.$$delegate_0.contains(element);
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return this.$$delegate_0.containsAll(elements);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.List
    public ParseError get(int index) {
        return this.$$delegate_0.get(index);
    }

    public int getSize() {
        return this.$$delegate_0.size();
    }

    public int indexOf(ParseError element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return this.$$delegate_0.indexOf(element);
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return this.$$delegate_0.isEmpty();
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<ParseError> iterator() {
        return this.$$delegate_0.iterator();
    }

    public int lastIndexOf(ParseError element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return this.$$delegate_0.lastIndexOf(element);
    }

    @Override // java.util.List
    public ListIterator<ParseError> listIterator() {
        return this.$$delegate_0.listIterator();
    }

    @Override // java.util.List
    public ListIterator<ParseError> listIterator(int index) {
        return this.$$delegate_0.listIterator(index);
    }

    @IgnorableReturnValue
    public boolean remove(ParseError element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return this.$$delegate_0.remove(element);
    }

    @Override // java.util.List, java.util.Collection
    @IgnorableReturnValue
    public boolean removeAll(Collection<?> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return this.$$delegate_0.removeAll(elements);
    }

    @IgnorableReturnValue
    public ParseError removeAt(int index) {
        return this.$$delegate_0.remove(index);
    }

    @Override // java.util.List, java.util.Collection
    @IgnorableReturnValue
    public boolean retainAll(Collection<?> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return this.$$delegate_0.retainAll(elements);
    }

    @Override // java.util.List
    @IgnorableReturnValue
    public ParseError set(int index, ParseError element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return this.$$delegate_0.set(index, element);
    }

    @Override // java.util.List
    public List<ParseError> subList(int fromIndex, int toIndex) {
        return this.$$delegate_0.subList(fromIndex, toIndex);
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

    private ParseErrorList(int i, int i2) {
        this.$$delegate_0 = new ArrayList();
        this.initialCapacity = i;
        this.maxSize = i2;
    }

    @Override // java.util.List, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof ParseError) {
            return contains((ParseError) obj);
        }
        return false;
    }

    @Override // java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof ParseError) {
            return indexOf((ParseError) obj);
        }
        return -1;
    }

    @Override // java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof ParseError) {
            return lastIndexOf((ParseError) obj);
        }
        return -1;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.List
    public final /* bridge */ ParseError remove(int i) {
        return removeAt(i);
    }

    @Override // java.util.List, java.util.Collection
    public final /* bridge */ boolean remove(Object obj) {
        if (obj instanceof ParseError) {
            return remove((ParseError) obj);
        }
        return false;
    }

    @Override // java.util.List, java.util.Collection
    public final /* bridge */ int size() {
        return getSize();
    }

    public final int getMaxSize() {
        return this.maxSize;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ParseErrorList(ParseErrorList copy) {
        this(copy.initialCapacity, copy.maxSize);
        Intrinsics.checkNotNullParameter(copy, "copy");
    }

    public final boolean canAddError() {
        return size() < this.maxSize;
    }

    public final ParseErrorList clone$ksoup_release() {
        ParseErrorList parseErrorList = new ParseErrorList(this.initialCapacity, this.maxSize);
        parseErrorList.addAll(this);
        return parseErrorList;
    }

    /* JADX INFO: compiled from: ParseErrorList.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/fleeksoft/ksoup/parser/ParseErrorList$Companion;", "", "<init>", "()V", "INITIAL_CAPACITY", "", "noTracking", "Lcom/fleeksoft/ksoup/parser/ParseErrorList;", "tracking", "maxSize", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ParseErrorList noTracking() {
            int i = 0;
            return new ParseErrorList(i, i, null);
        }

        public final ParseErrorList tracking(int maxSize) {
            return new ParseErrorList(16, maxSize, null);
        }
    }
}
