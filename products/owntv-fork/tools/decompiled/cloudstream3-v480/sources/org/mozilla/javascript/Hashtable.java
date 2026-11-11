package org.mozilla.javascript;

import j$.util.Iterator;
import j$.util.Map;
import j$.util.function.BiFunction$CC;
import j$.util.function.Consumer$CC;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes8.dex */
public class Hashtable implements Serializable, Iterable<Entry> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long serialVersionUID = -7151554912419543747L;
    private final HashMap<Object, Entry> map = new HashMap<>();
    private Entry first = null;
    private Entry last = null;

    public static final class Entry implements Serializable {
        private static final long serialVersionUID = 4086572107122965503L;
        boolean deleted;
        final int hashCode;
        Object key;
        Entry next;
        Entry prev;
        Object value;

        Entry() {
            this.hashCode = 0;
        }

        Entry(Object obj, Object obj2) {
            if (obj instanceof Number) {
                if ((obj instanceof Double) || (obj instanceof BigInteger)) {
                    this.key = obj;
                } else {
                    this.key = Double.valueOf(((Number) obj).doubleValue());
                }
            } else if (obj instanceof ConsString) {
                this.key = obj.toString();
            } else {
                this.key = obj;
            }
            if (this.key == null || obj.equals(ScriptRuntime.negativeZeroObj)) {
                this.hashCode = 0;
            } else {
                this.hashCode = this.key.hashCode();
            }
            this.value = obj2;
        }

        public Object key() {
            return this.key;
        }

        public Object value() {
            return this.value;
        }

        void clear() {
            this.key = Undefined.instance;
            this.value = Undefined.instance;
            this.deleted = true;
        }

        public int hashCode() {
            return this.hashCode;
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            try {
                return ScriptRuntime.sameZero(this.key, ((Entry) obj).key);
            } catch (ClassCastException unused) {
                return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Entry makeDummy() {
        Entry entry = new Entry();
        entry.clear();
        return entry;
    }

    public int size() {
        return this.map.size();
    }

    public void put(Object obj, final Object obj2) {
        final Entry entry = new Entry(obj, obj2);
        Map.EL.compute(this.map, entry, new BiFunction() { // from class: org.mozilla.javascript.Hashtable$$ExternalSyntheticLambda1
            public /* synthetic */ BiFunction andThen(java.util.function.Function function) {
                return BiFunction$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.BiFunction
            public final Object apply(Object obj3, Object obj4) {
                return this.f$0.m10870lambda$put$0$orgmozillajavascriptHashtable(entry, obj2, obj3, (Hashtable.Entry) obj4);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$put$0$org-mozilla-javascript-Hashtable, reason: not valid java name */
    /* synthetic */ Entry m10870lambda$put$0$orgmozillajavascriptHashtable(Entry entry, Object obj, Object obj2, Entry entry2) {
        if (entry2 != null) {
            entry2.value = obj;
            return entry2;
        }
        if (this.first == null) {
            this.last = entry;
            this.first = entry;
            return entry;
        }
        this.last.next = entry;
        entry.prev = this.last;
        this.last = entry;
        return entry;
    }

    @Deprecated
    public Object get(Object obj) {
        Entry entry = this.map.get(new Entry(obj, null));
        if (entry == null) {
            return null;
        }
        return entry.value;
    }

    public Entry getEntry(Object obj) {
        return this.map.get(new Entry(obj, null));
    }

    public boolean has(Object obj) {
        return this.map.containsKey(new Entry(obj, null));
    }

    @Deprecated
    public Object delete(Object obj) {
        Entry entryRemove = this.map.remove(new Entry(obj, null));
        if (entryRemove == null) {
            return null;
        }
        if (entryRemove == this.first) {
            if (entryRemove == this.last) {
                entryRemove.clear();
                entryRemove.prev = null;
            } else {
                Entry entry = entryRemove.next;
                this.first = entry;
                entry.prev = null;
                if (this.first.next != null) {
                    this.first.next.prev = this.first;
                }
            }
        } else {
            Entry entry2 = entryRemove.prev;
            entry2.next = entryRemove.next;
            entryRemove.prev = null;
            if (entryRemove.next != null) {
                entryRemove.next.prev = entry2;
            } else {
                this.last = entry2;
            }
        }
        Object obj2 = entryRemove.value;
        entryRemove.clear();
        return obj2;
    }

    public boolean deleteEntry(Object obj) {
        Entry entryRemove = this.map.remove(new Entry(obj, null));
        if (entryRemove == null) {
            return false;
        }
        if (entryRemove == this.first) {
            if (entryRemove == this.last) {
                entryRemove.clear();
                entryRemove.prev = null;
            } else {
                Entry entry = entryRemove.next;
                this.first = entry;
                entry.prev = null;
                if (this.first.next != null) {
                    this.first.next.prev = this.first;
                }
            }
        } else {
            Entry entry2 = entryRemove.prev;
            entry2.next = entryRemove.next;
            entryRemove.prev = null;
            if (entryRemove.next != null) {
                entryRemove.next.prev = entry2;
            } else {
                this.last = entry2;
            }
        }
        entryRemove.clear();
        return true;
    }

    public void clear() {
        Iterator.EL.forEachRemaining(iterator(), new Consumer() { // from class: org.mozilla.javascript.Hashtable$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Hashtable.Entry) obj).clear();
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        });
        if (this.first != null) {
            Entry entryMakeDummy = makeDummy();
            this.last.next = entryMakeDummy;
            this.last = entryMakeDummy;
            this.first = entryMakeDummy;
        }
        this.map.clear();
    }

    @Override // java.lang.Iterable
    public java.util.Iterator<Entry> iterator() {
        return new Iter(this.first);
    }

    private static final class Iter implements java.util.Iterator<Entry> {
        private Entry pos;

        Iter(Entry entry) {
            Entry entryMakeDummy = Hashtable.makeDummy();
            entryMakeDummy.next = entry;
            this.pos = entryMakeDummy;
        }

        private void skipDeleted() {
            while (this.pos.next != null && this.pos.next.deleted) {
                this.pos = this.pos.next;
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            skipDeleted();
            Entry entry = this.pos;
            return (entry == null || entry.next == null) ? false : true;
        }

        @Override // java.util.Iterator
        public Entry next() {
            skipDeleted();
            Entry entry = this.pos;
            if (entry == null || entry.next == null) {
                throw new NoSuchElementException();
            }
            Entry entry2 = this.pos.next;
            this.pos = this.pos.next;
            return entry2;
        }
    }
}
