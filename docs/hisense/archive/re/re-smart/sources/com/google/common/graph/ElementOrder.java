package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.errorprone.annotations.Immutable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
@Immutable
@Beta
public final class ElementOrder<T> {
    public final Comparator<T> comparator;
    public final Type type;

    /* JADX INFO: renamed from: com.google.common.graph.ElementOrder$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$common$graph$ElementOrder$Type = new int[Type.values().length];

        static {
            try {
                $SwitchMap$com$google$common$graph$ElementOrder$Type[Type.UNORDERED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$common$graph$ElementOrder$Type[Type.INSERTION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$common$graph$ElementOrder$Type[Type.STABLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$common$graph$ElementOrder$Type[Type.SORTED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public enum Type {
        UNORDERED,
        STABLE,
        INSERTION,
        SORTED
    }

    public ElementOrder(Type type, Comparator<T> comparator) {
        if (type == null) {
            throw new NullPointerException();
        }
        this.type = type;
        this.comparator = comparator;
        Preconditions.checkState((type == Type.SORTED) == (comparator != null));
    }

    public static <S> ElementOrder<S> insertion() {
        return new ElementOrder<>(Type.INSERTION, null);
    }

    public static <S extends Comparable<? super S>> ElementOrder<S> natural() {
        return new ElementOrder<>(Type.SORTED, Ordering.natural());
    }

    public static <S> ElementOrder<S> sorted(Comparator<S> comparator) {
        Type type = Type.SORTED;
        if (comparator != null) {
            return new ElementOrder<>(type, comparator);
        }
        throw new NullPointerException();
    }

    public static <S> ElementOrder<S> stable() {
        return new ElementOrder<>(Type.STABLE, null);
    }

    public static <S> ElementOrder<S> unordered() {
        return new ElementOrder<>(Type.UNORDERED, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T1 extends T> ElementOrder<T1> cast() {
        return this;
    }

    public Comparator<T> comparator() {
        Comparator<T> comparator = this.comparator;
        if (comparator != null) {
            return comparator;
        }
        throw new UnsupportedOperationException("This ordering does not define a comparator.");
    }

    public <K extends T, V> Map<K, V> createMap(int i) {
        int iOrdinal = this.type.ordinal();
        if (iOrdinal == 0) {
            return Maps.newHashMapWithExpectedSize(i);
        }
        if (iOrdinal == 1 || iOrdinal == 2) {
            return Maps.newLinkedHashMapWithExpectedSize(i);
        }
        if (iOrdinal == 3) {
            return new TreeMap(comparator());
        }
        throw new AssertionError();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ElementOrder)) {
            return false;
        }
        ElementOrder elementOrder = (ElementOrder) obj;
        return this.type == elementOrder.type && Objects.equal(this.comparator, elementOrder.comparator);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.type, this.comparator});
    }

    public String toString() {
        MoreObjects.ToStringHelper toStringHelperAdd = MoreObjects.toStringHelper(this).add("type", this.type);
        Comparator<T> comparator = this.comparator;
        if (comparator != null) {
            toStringHelperAdd.add("comparator", comparator);
        }
        return toStringHelperAdd.toString();
    }

    public Type type() {
        return this.type;
    }
}
