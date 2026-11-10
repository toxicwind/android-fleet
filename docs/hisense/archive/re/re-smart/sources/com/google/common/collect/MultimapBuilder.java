package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public abstract class MultimapBuilder<K0, V0> {
    public static final int DEFAULT_EXPECTED_KEYS = 8;

    /* JADX INFO: renamed from: com.google.common.collect.MultimapBuilder$4, reason: invalid class name */
    public class AnonymousClass4 extends MultimapBuilderWithKeys<K0> {
        public final /* synthetic */ Class val$keyClass;

        public AnonymousClass4(Class cls) {
            this.val$keyClass = cls;
        }

        @Override // com.google.common.collect.MultimapBuilder.MultimapBuilderWithKeys
        public <K extends K0, V> Map<K, Collection<V>> createMap() {
            return new EnumMap(this.val$keyClass);
        }
    }

    private static final class ArrayListSupplier<V> implements Supplier<List<V>>, Serializable {
        public final int expectedValuesPerKey;

        public ArrayListSupplier(int i) {
            CollectPreconditions.checkNonnegative(i, "expectedValuesPerKey");
            this.expectedValuesPerKey = i;
        }

        @Override // com.google.common.base.Supplier
        public List<V> get() {
            return new ArrayList(this.expectedValuesPerKey);
        }
    }

    private static final class EnumSetSupplier<V extends Enum<V>> implements Supplier<Set<V>>, Serializable {
        public final Class<V> clazz;

        public EnumSetSupplier(Class<V> cls) {
            if (cls == null) {
                throw new NullPointerException();
            }
            this.clazz = cls;
        }

        @Override // com.google.common.base.Supplier
        public Set<V> get() {
            return EnumSet.noneOf(this.clazz);
        }
    }

    private static final class HashSetSupplier<V> implements Supplier<Set<V>>, Serializable {
        public final int expectedValuesPerKey;

        public HashSetSupplier(int i) {
            CollectPreconditions.checkNonnegative(i, "expectedValuesPerKey");
            this.expectedValuesPerKey = i;
        }

        @Override // com.google.common.base.Supplier
        public Set<V> get() {
            return new CompactHashSet(this.expectedValuesPerKey);
        }
    }

    private static final class LinkedHashSetSupplier<V> implements Supplier<Set<V>>, Serializable {
        public final int expectedValuesPerKey;

        public LinkedHashSetSupplier(int i) {
            CollectPreconditions.checkNonnegative(i, "expectedValuesPerKey");
            this.expectedValuesPerKey = i;
        }

        @Override // com.google.common.base.Supplier
        public Set<V> get() {
            return new CompactLinkedHashSet(this.expectedValuesPerKey);
        }
    }

    private enum LinkedListSupplier implements Supplier<List<Object>> {
        INSTANCE;

        public static <V> Supplier<List<V>> instance() {
            return INSTANCE;
        }

        @Override // com.google.common.base.Supplier
        public List<Object> get() {
            return new LinkedList();
        }
    }

    public static abstract class ListMultimapBuilder<K0, V0> extends MultimapBuilder<K0, V0> {
        public ListMultimapBuilder() {
            super(null);
        }

        @Override // com.google.common.collect.MultimapBuilder
        public abstract <K extends K0, V extends V0> ListMultimap<K, V> build();

        @Override // com.google.common.collect.MultimapBuilder
        public <K extends K0, V extends V0> ListMultimap<K, V> build(Multimap<? extends K, ? extends V> multimap) {
            Multimap<K, V> multimapBuild = build();
            multimapBuild.putAll(multimap);
            return (ListMultimap) multimapBuild;
        }
    }

    public static abstract class MultimapBuilderWithKeys<K0> {
        public static final int DEFAULT_EXPECTED_VALUES_PER_KEY = 2;

        public ListMultimapBuilder<K0, Object> arrayListValues() {
            return arrayListValues(2);
        }

        public abstract <K extends K0, V> Map<K, Collection<V>> createMap();

        public <V0 extends Enum<V0>> SetMultimapBuilder<K0, V0> enumSetValues(final Class<V0> cls) {
            Preconditions.checkNotNull(cls, "valueClass");
            return new SetMultimapBuilder<K0, V0>() { // from class: com.google.common.collect.MultimapBuilder.MultimapBuilderWithKeys.6
                @Override // com.google.common.collect.MultimapBuilder.SetMultimapBuilder, com.google.common.collect.MultimapBuilder
                public <K extends K0, V extends V0> SetMultimap<K, V> build() {
                    return new Multimaps.CustomSetMultimap(MultimapBuilderWithKeys.this.createMap(), new EnumSetSupplier(cls));
                }
            };
        }

        public SetMultimapBuilder<K0, Object> hashSetValues() {
            return hashSetValues(2);
        }

        public SetMultimapBuilder<K0, Object> linkedHashSetValues() {
            return linkedHashSetValues(2);
        }

        public ListMultimapBuilder<K0, Object> linkedListValues() {
            return new ListMultimapBuilder<K0, Object>() { // from class: com.google.common.collect.MultimapBuilder.MultimapBuilderWithKeys.2
                @Override // com.google.common.collect.MultimapBuilder.ListMultimapBuilder, com.google.common.collect.MultimapBuilder
                public <K extends K0, V> ListMultimap<K, V> build() {
                    return new Multimaps.CustomListMultimap(MultimapBuilderWithKeys.this.createMap(), LinkedListSupplier.INSTANCE);
                }
            };
        }

        public SortedSetMultimapBuilder<K0, Comparable> treeSetValues() {
            return treeSetValues(Ordering.natural());
        }

        public ListMultimapBuilder<K0, Object> arrayListValues(final int i) {
            CollectPreconditions.checkNonnegative(i, "expectedValuesPerKey");
            return new ListMultimapBuilder<K0, Object>() { // from class: com.google.common.collect.MultimapBuilder.MultimapBuilderWithKeys.1
                @Override // com.google.common.collect.MultimapBuilder.ListMultimapBuilder, com.google.common.collect.MultimapBuilder
                public <K extends K0, V> ListMultimap<K, V> build() {
                    return new Multimaps.CustomListMultimap(MultimapBuilderWithKeys.this.createMap(), new ArrayListSupplier(i));
                }
            };
        }

        public SetMultimapBuilder<K0, Object> hashSetValues(final int i) {
            CollectPreconditions.checkNonnegative(i, "expectedValuesPerKey");
            return new SetMultimapBuilder<K0, Object>() { // from class: com.google.common.collect.MultimapBuilder.MultimapBuilderWithKeys.3
                @Override // com.google.common.collect.MultimapBuilder.SetMultimapBuilder, com.google.common.collect.MultimapBuilder
                public <K extends K0, V> SetMultimap<K, V> build() {
                    return new Multimaps.CustomSetMultimap(MultimapBuilderWithKeys.this.createMap(), new HashSetSupplier(i));
                }
            };
        }

        public SetMultimapBuilder<K0, Object> linkedHashSetValues(final int i) {
            CollectPreconditions.checkNonnegative(i, "expectedValuesPerKey");
            return new SetMultimapBuilder<K0, Object>() { // from class: com.google.common.collect.MultimapBuilder.MultimapBuilderWithKeys.4
                @Override // com.google.common.collect.MultimapBuilder.SetMultimapBuilder, com.google.common.collect.MultimapBuilder
                public <K extends K0, V> SetMultimap<K, V> build() {
                    return new Multimaps.CustomSetMultimap(MultimapBuilderWithKeys.this.createMap(), new LinkedHashSetSupplier(i));
                }
            };
        }

        public <V0> SortedSetMultimapBuilder<K0, V0> treeSetValues(final Comparator<V0> comparator) {
            Preconditions.checkNotNull(comparator, "comparator");
            return new SortedSetMultimapBuilder<K0, V0>() { // from class: com.google.common.collect.MultimapBuilder.MultimapBuilderWithKeys.5
                @Override // com.google.common.collect.MultimapBuilder.SortedSetMultimapBuilder, com.google.common.collect.MultimapBuilder.SetMultimapBuilder, com.google.common.collect.MultimapBuilder
                public <K extends K0, V extends V0> SortedSetMultimap<K, V> build() {
                    return new Multimaps.CustomSortedSetMultimap(MultimapBuilderWithKeys.this.createMap(), new TreeSetSupplier(comparator));
                }
            };
        }
    }

    public static abstract class SetMultimapBuilder<K0, V0> extends MultimapBuilder<K0, V0> {
        public SetMultimapBuilder() {
            super(null);
        }

        @Override // com.google.common.collect.MultimapBuilder
        public abstract <K extends K0, V extends V0> SetMultimap<K, V> build();

        @Override // com.google.common.collect.MultimapBuilder
        public <K extends K0, V extends V0> SetMultimap<K, V> build(Multimap<? extends K, ? extends V> multimap) {
            Multimap<K, V> multimapBuild = build();
            multimapBuild.putAll(multimap);
            return (SetMultimap) multimapBuild;
        }
    }

    public static abstract class SortedSetMultimapBuilder<K0, V0> extends SetMultimapBuilder<K0, V0> {
        @Override // com.google.common.collect.MultimapBuilder.SetMultimapBuilder, com.google.common.collect.MultimapBuilder
        public abstract <K extends K0, V extends V0> SortedSetMultimap<K, V> build();

        @Override // com.google.common.collect.MultimapBuilder.SetMultimapBuilder, com.google.common.collect.MultimapBuilder
        public <K extends K0, V extends V0> SortedSetMultimap<K, V> build(Multimap<? extends K, ? extends V> multimap) {
            Multimap<K, V> multimapBuild = build();
            multimapBuild.putAll(multimap);
            return (SortedSetMultimap) multimapBuild;
        }
    }

    private static final class TreeSetSupplier<V> implements Supplier<SortedSet<V>>, Serializable {
        public final Comparator<? super V> comparator;

        public TreeSetSupplier(Comparator<? super V> comparator) {
            if (comparator == null) {
                throw new NullPointerException();
            }
            this.comparator = comparator;
        }

        @Override // com.google.common.base.Supplier
        public SortedSet<V> get() {
            return new TreeSet(this.comparator);
        }
    }

    public MultimapBuilder() {
    }

    public static <K0 extends Enum<K0>> MultimapBuilderWithKeys<K0> enumKeys(Class<K0> cls) {
        if (cls != null) {
            return new AnonymousClass4(cls);
        }
        throw new NullPointerException();
    }

    public static MultimapBuilderWithKeys<Object> hashKeys() {
        return hashKeys(8);
    }

    public static MultimapBuilderWithKeys<Object> linkedHashKeys() {
        return linkedHashKeys(8);
    }

    public static MultimapBuilderWithKeys<Comparable> treeKeys() {
        return treeKeys(Ordering.natural());
    }

    public abstract <K extends K0, V extends V0> Multimap<K, V> build();

    public <K extends K0, V extends V0> Multimap<K, V> build(Multimap<? extends K, ? extends V> multimap) {
        Multimap<K, V> multimapBuild = build();
        multimapBuild.putAll(multimap);
        return multimapBuild;
    }

    public /* synthetic */ MultimapBuilder(AnonymousClass1 anonymousClass1) {
    }

    public static MultimapBuilderWithKeys<Object> hashKeys(final int i) {
        CollectPreconditions.checkNonnegative(i, "expectedKeys");
        return new MultimapBuilderWithKeys<Object>() { // from class: com.google.common.collect.MultimapBuilder.1
            @Override // com.google.common.collect.MultimapBuilder.MultimapBuilderWithKeys
            public <K, V> Map<K, Collection<V>> createMap() {
                return new CompactHashMap(i);
            }
        };
    }

    public static MultimapBuilderWithKeys<Object> linkedHashKeys(final int i) {
        CollectPreconditions.checkNonnegative(i, "expectedKeys");
        return new MultimapBuilderWithKeys<Object>() { // from class: com.google.common.collect.MultimapBuilder.2
            @Override // com.google.common.collect.MultimapBuilder.MultimapBuilderWithKeys
            public <K, V> Map<K, Collection<V>> createMap() {
                return new CompactLinkedHashMap(i);
            }
        };
    }

    public static <K0> MultimapBuilderWithKeys<K0> treeKeys(final Comparator<K0> comparator) {
        if (comparator != null) {
            return new MultimapBuilderWithKeys<K0>() { // from class: com.google.common.collect.MultimapBuilder.3
                @Override // com.google.common.collect.MultimapBuilder.MultimapBuilderWithKeys
                public <K extends K0, V> Map<K, Collection<V>> createMap() {
                    return new TreeMap(comparator);
                }
            };
        }
        throw new NullPointerException();
    }
}
