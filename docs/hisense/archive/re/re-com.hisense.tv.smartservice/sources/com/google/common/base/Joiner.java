package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public class Joiner {
    public final String separator;

    /* JADX INFO: renamed from: com.google.common.base.Joiner$3, reason: invalid class name */
    public class AnonymousClass3 extends AbstractList<Object> {
        public final /* synthetic */ Object val$first;
        public final /* synthetic */ Object[] val$rest;
        public final /* synthetic */ Object val$second;

        public AnonymousClass3(Object[] objArr, Object obj, Object obj2) {
            this.val$rest = objArr;
            this.val$first = obj;
            this.val$second = obj2;
        }

        @Override // java.util.AbstractList, java.util.List
        public Object get(int i) {
            if (i != 0) {
                return i != 1 ? this.val$rest[i - 2] : this.val$second;
            }
            return this.val$first;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.val$rest.length + 2;
        }
    }

    public static final class MapJoiner {
        public final Joiner joiner;
        public final String keyValueSeparator;

        @CanIgnoreReturnValue
        public <A extends Appendable> A appendTo(A a2, Map<?, ?> map) {
            return (A) appendTo(a2, map.entrySet());
        }

        public String join(Map<?, ?> map) {
            return join(map.entrySet());
        }

        public MapJoiner useForNull(String str) {
            return new MapJoiner(this.joiner.useForNull(str), this.keyValueSeparator);
        }

        public MapJoiner(Joiner joiner, String str) {
            this.joiner = joiner;
            if (str == null) {
                throw new NullPointerException();
            }
            this.keyValueSeparator = str;
        }

        @CanIgnoreReturnValue
        public StringBuilder appendTo(StringBuilder sb, Map<?, ?> map) {
            return appendTo(sb, (Iterable<? extends Map.Entry<?, ?>>) map.entrySet());
        }

        @Beta
        public String join(Iterable<? extends Map.Entry<?, ?>> iterable) {
            return join(iterable.iterator());
        }

        @CanIgnoreReturnValue
        @Beta
        public <A extends Appendable> A appendTo(A a2, Iterable<? extends Map.Entry<?, ?>> iterable) {
            return (A) appendTo(a2, iterable.iterator());
        }

        @Beta
        public String join(Iterator<? extends Map.Entry<?, ?>> it) {
            return appendTo(new StringBuilder(), it).toString();
        }

        @CanIgnoreReturnValue
        @Beta
        public <A extends Appendable> A appendTo(A a2, Iterator<? extends Map.Entry<?, ?>> it) throws IOException {
            if (a2 != null) {
                if (it.hasNext()) {
                    Map.Entry<?, ?> next = it.next();
                    a2.append(this.joiner.toString(next.getKey()));
                    a2.append(this.keyValueSeparator);
                    a2.append(this.joiner.toString(next.getValue()));
                    while (it.hasNext()) {
                        a2.append(this.joiner.separator);
                        Map.Entry<?, ?> next2 = it.next();
                        a2.append(this.joiner.toString(next2.getKey()));
                        a2.append(this.keyValueSeparator);
                        a2.append(this.joiner.toString(next2.getValue()));
                    }
                }
                return a2;
            }
            throw new NullPointerException();
        }

        @CanIgnoreReturnValue
        @Beta
        public StringBuilder appendTo(StringBuilder sb, Iterable<? extends Map.Entry<?, ?>> iterable) {
            return appendTo(sb, iterable.iterator());
        }

        @CanIgnoreReturnValue
        @Beta
        public StringBuilder appendTo(StringBuilder sb, Iterator<? extends Map.Entry<?, ?>> it) {
            try {
                appendTo(sb, it);
                return sb;
            } catch (IOException e2) {
                throw new AssertionError(e2);
            }
        }
    }

    public Joiner(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.separator = str;
    }

    public static Iterable<Object> iterable(Object obj, Object obj2, Object[] objArr) {
        if (objArr != null) {
            return new AnonymousClass3(objArr, obj, obj2);
        }
        throw new NullPointerException();
    }

    public static Joiner on(String str) {
        return new Joiner(str);
    }

    @CanIgnoreReturnValue
    public <A extends Appendable> A appendTo(A a2, Iterable<?> iterable) {
        return (A) appendTo(a2, iterable.iterator());
    }

    public final String join(Iterable<?> iterable) {
        return join(iterable.iterator());
    }

    public Joiner skipNulls() {
        return new Joiner(this) { // from class: com.google.common.base.Joiner.2
            {
                AnonymousClass1 anonymousClass1 = null;
            }

            @Override // com.google.common.base.Joiner
            public <A extends Appendable> A appendTo(A a2, Iterator<?> it) throws IOException {
                Preconditions.checkNotNull(a2, "appendable");
                Preconditions.checkNotNull(it, "parts");
                while (it.hasNext()) {
                    Object next = it.next();
                    if (next != null) {
                        a2.append(Joiner.this.toString(next));
                        break;
                    }
                }
                while (it.hasNext()) {
                    Object next2 = it.next();
                    if (next2 != null) {
                        a2.append(Joiner.this.separator);
                        a2.append(Joiner.this.toString(next2));
                    }
                }
                return a2;
            }

            @Override // com.google.common.base.Joiner
            public Joiner useForNull(String str) {
                throw new UnsupportedOperationException("already specified skipNulls");
            }

            @Override // com.google.common.base.Joiner
            public MapJoiner withKeyValueSeparator(String str) {
                throw new UnsupportedOperationException("can't use .skipNulls() with maps");
            }
        };
    }

    public CharSequence toString(Object obj) {
        if (obj != null) {
            return obj instanceof CharSequence ? (CharSequence) obj : obj.toString();
        }
        throw new NullPointerException();
    }

    public Joiner useForNull(final String str) {
        if (str != null) {
            return new Joiner(this) { // from class: com.google.common.base.Joiner.1
                {
                    AnonymousClass1 anonymousClass1 = null;
                }

                @Override // com.google.common.base.Joiner
                public Joiner skipNulls() {
                    throw new UnsupportedOperationException("already specified useForNull");
                }

                @Override // com.google.common.base.Joiner
                public CharSequence toString(Object obj) {
                    return obj == null ? str : Joiner.this.toString(obj);
                }

                @Override // com.google.common.base.Joiner
                public Joiner useForNull(String str2) {
                    throw new UnsupportedOperationException("already specified useForNull");
                }
            };
        }
        throw new NullPointerException();
    }

    public MapJoiner withKeyValueSeparator(char c2) {
        return withKeyValueSeparator(String.valueOf(c2));
    }

    public static Joiner on(char c2) {
        return new Joiner(String.valueOf(c2));
    }

    @CanIgnoreReturnValue
    public <A extends Appendable> A appendTo(A a2, Iterator<?> it) throws IOException {
        if (a2 == null) {
            throw new NullPointerException();
        }
        if (it.hasNext()) {
            a2.append(toString(it.next()));
            while (it.hasNext()) {
                a2.append(this.separator);
                a2.append(toString(it.next()));
            }
        }
        return a2;
    }

    public final String join(Iterator<?> it) {
        return appendTo(new StringBuilder(), it).toString();
    }

    public MapJoiner withKeyValueSeparator(String str) {
        return new MapJoiner(this, str);
    }

    public final String join(Object[] objArr) {
        return join(Arrays.asList(objArr));
    }

    public Joiner(Joiner joiner) {
        this.separator = joiner.separator;
    }

    public final String join(Object obj, Object obj2, Object... objArr) {
        if (objArr != null) {
            return join(new AnonymousClass3(objArr, obj, obj2));
        }
        throw new NullPointerException();
    }

    public /* synthetic */ Joiner(Joiner joiner, AnonymousClass1 anonymousClass1) {
        this.separator = joiner.separator;
    }

    @CanIgnoreReturnValue
    public final <A extends Appendable> A appendTo(A a2, Object[] objArr) {
        return (A) appendTo(a2, Arrays.asList(objArr));
    }

    @CanIgnoreReturnValue
    public final StringBuilder appendTo(StringBuilder sb, Iterable<?> iterable) {
        return appendTo(sb, iterable.iterator());
    }

    @CanIgnoreReturnValue
    public final StringBuilder appendTo(StringBuilder sb, Iterator<?> it) {
        try {
            appendTo(sb, it);
            return sb;
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }

    @CanIgnoreReturnValue
    public final StringBuilder appendTo(StringBuilder sb, Object[] objArr) {
        return appendTo(sb, (Iterable<?>) Arrays.asList(objArr));
    }

    @CanIgnoreReturnValue
    public final <A extends Appendable> A appendTo(A a2, Object obj, Object obj2, Object... objArr) {
        if (objArr != null) {
            return (A) appendTo(a2, new AnonymousClass3(objArr, obj, obj2));
        }
        throw new NullPointerException();
    }

    @CanIgnoreReturnValue
    public final StringBuilder appendTo(StringBuilder sb, Object obj, Object obj2, Object... objArr) {
        if (objArr != null) {
            return appendTo(sb, (Iterable<?>) new AnonymousClass3(objArr, obj, obj2));
        }
        throw new NullPointerException();
    }
}
