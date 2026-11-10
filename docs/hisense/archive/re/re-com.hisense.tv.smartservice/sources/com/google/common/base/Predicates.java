package com.google.common.base;

import b.a.a.a.a;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(emulated = true)
public final class Predicates {

    /* JADX INFO: Access modifiers changed from: private */
    public static class CompositionPredicate<A, B> implements Predicate<A>, Serializable {
        public static final long serialVersionUID = 0;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final Function<A, ? extends B> f1951f;
        public final Predicate<B> p;

        @Override // com.google.common.base.Predicate
        public boolean apply(A a2) {
            return this.p.apply(this.f1951f.apply(a2));
        }

        @Override // com.google.common.base.Predicate
        public boolean equals(Object obj) {
            if (!(obj instanceof CompositionPredicate)) {
                return false;
            }
            CompositionPredicate compositionPredicate = (CompositionPredicate) obj;
            return this.f1951f.equals(compositionPredicate.f1951f) && this.p.equals(compositionPredicate.p);
        }

        public int hashCode() {
            return this.f1951f.hashCode() ^ this.p.hashCode();
        }

        public String toString() {
            String strValueOf = String.valueOf(this.p);
            String strValueOf2 = String.valueOf(this.f1951f);
            return a.b(strValueOf2.length() + strValueOf.length() + 2, strValueOf, "(", strValueOf2, ")");
        }

        public CompositionPredicate(Predicate<B> predicate, Function<A, ? extends B> function) {
            if (predicate == null) {
                throw new NullPointerException();
            }
            this.p = predicate;
            if (function == null) {
                throw new NullPointerException();
            }
            this.f1951f = function;
        }
    }

    @GwtIncompatible
    private static class ContainsPatternFromStringPredicate extends ContainsPatternPredicate {
        public static final long serialVersionUID = 0;

        public ContainsPatternFromStringPredicate(String str) {
            super(Platform.compilePattern(str));
        }

        @Override // com.google.common.base.Predicates.ContainsPatternPredicate
        public String toString() {
            String strPattern = this.pattern.pattern();
            return a.a(a.a((Object) strPattern, 28), "Predicates.containsPattern(", strPattern, ")");
        }
    }

    @GwtIncompatible
    private static class ContainsPatternPredicate implements Predicate<CharSequence>, Serializable {
        public static final long serialVersionUID = 0;
        public final CommonPattern pattern;

        public ContainsPatternPredicate(CommonPattern commonPattern) {
            if (commonPattern == null) {
                throw new NullPointerException();
            }
            this.pattern = commonPattern;
        }

        @Override // com.google.common.base.Predicate
        public boolean equals(Object obj) {
            if (!(obj instanceof ContainsPatternPredicate)) {
                return false;
            }
            ContainsPatternPredicate containsPatternPredicate = (ContainsPatternPredicate) obj;
            return Objects.equal(this.pattern.pattern(), containsPatternPredicate.pattern.pattern()) && this.pattern.flags() == containsPatternPredicate.pattern.flags();
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.pattern.pattern(), Integer.valueOf(this.pattern.flags())});
        }

        public String toString() {
            String string = MoreObjects.toStringHelper(this.pattern).add("pattern", this.pattern.pattern()).add("pattern.flags", this.pattern.flags()).toString();
            return a.a(a.a((Object) string, 21), "Predicates.contains(", string, ")");
        }

        @Override // com.google.common.base.Predicate
        public boolean apply(CharSequence charSequence) {
            return this.pattern.matcher(charSequence).find();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static class NotPredicate<T> implements Predicate<T>, Serializable {
        public static final long serialVersionUID = 0;
        public final Predicate<T> predicate;

        public NotPredicate(Predicate<T> predicate) {
            if (predicate == null) {
                throw new NullPointerException();
            }
            this.predicate = predicate;
        }

        @Override // com.google.common.base.Predicate
        public boolean apply(T t) {
            return !this.predicate.apply(t);
        }

        @Override // com.google.common.base.Predicate
        public boolean equals(Object obj) {
            if (obj instanceof NotPredicate) {
                return this.predicate.equals(((NotPredicate) obj).predicate);
            }
            return false;
        }

        public int hashCode() {
            return ~this.predicate.hashCode();
        }

        public String toString() {
            String strValueOf = String.valueOf(this.predicate);
            return a.a(strValueOf.length() + 16, "Predicates.not(", strValueOf, ")");
        }
    }

    enum ObjectPredicate implements Predicate<Object> {
        ALWAYS_TRUE { // from class: com.google.common.base.Predicates.ObjectPredicate.1
            @Override // com.google.common.base.Predicate
            public boolean apply(Object obj) {
                return true;
            }

            @Override // java.lang.Enum
            public String toString() {
                return "Predicates.alwaysTrue()";
            }
        },
        ALWAYS_FALSE { // from class: com.google.common.base.Predicates.ObjectPredicate.2
            @Override // com.google.common.base.Predicate
            public boolean apply(Object obj) {
                return false;
            }

            @Override // java.lang.Enum
            public String toString() {
                return "Predicates.alwaysFalse()";
            }
        },
        IS_NULL { // from class: com.google.common.base.Predicates.ObjectPredicate.3
            @Override // com.google.common.base.Predicate
            public boolean apply(Object obj) {
                return obj == null;
            }

            @Override // java.lang.Enum
            public String toString() {
                return "Predicates.isNull()";
            }
        },
        NOT_NULL { // from class: com.google.common.base.Predicates.ObjectPredicate.4
            @Override // com.google.common.base.Predicate
            public boolean apply(Object obj) {
                return obj != null;
            }

            @Override // java.lang.Enum
            public String toString() {
                return "Predicates.notNull()";
            }
        };

        public <T> Predicate<T> withNarrowedType() {
            return this;
        }

        /* synthetic */ ObjectPredicate(AnonymousClass1 anonymousClass1) {
        }
    }

    @GwtIncompatible
    private static class SubtypeOfPredicate implements Predicate<Class<?>>, Serializable {
        public static final long serialVersionUID = 0;
        public final Class<?> clazz;

        public SubtypeOfPredicate(Class<?> cls) {
            if (cls == null) {
                throw new NullPointerException();
            }
            this.clazz = cls;
        }

        @Override // com.google.common.base.Predicate
        public boolean equals(Object obj) {
            return (obj instanceof SubtypeOfPredicate) && this.clazz == ((SubtypeOfPredicate) obj).clazz;
        }

        public int hashCode() {
            return this.clazz.hashCode();
        }

        public String toString() {
            String name = this.clazz.getName();
            return a.a(name.length() + 22, "Predicates.subtypeOf(", name, ")");
        }

        @Override // com.google.common.base.Predicate
        public boolean apply(Class<?> cls) {
            return this.clazz.isAssignableFrom(cls);
        }

        public /* synthetic */ SubtypeOfPredicate(Class cls, AnonymousClass1 anonymousClass1) {
            if (cls != null) {
                this.clazz = cls;
                return;
            }
            throw new NullPointerException();
        }
    }

    @GwtCompatible(serializable = true)
    public static <T> Predicate<T> alwaysFalse() {
        return ObjectPredicate.ALWAYS_FALSE.withNarrowedType();
    }

    @GwtCompatible(serializable = true)
    public static <T> Predicate<T> alwaysTrue() {
        return ObjectPredicate.ALWAYS_TRUE.withNarrowedType();
    }

    public static <T> Predicate<T> and(Iterable<? extends Predicate<? super T>> iterable) {
        return new AndPredicate(defensiveCopy(iterable), null);
    }

    public static <T> List<Predicate<? super T>> asList(Predicate<? super T> predicate, Predicate<? super T> predicate2) {
        return Arrays.asList(predicate, predicate2);
    }

    public static <A, B> Predicate<A> compose(Predicate<B> predicate, Function<A, ? extends B> function) {
        return new CompositionPredicate(predicate, function);
    }

    @GwtIncompatible("java.util.regex.Pattern")
    public static Predicate<CharSequence> contains(Pattern pattern) {
        return new ContainsPatternPredicate(new JdkPattern(pattern));
    }

    @GwtIncompatible
    public static Predicate<CharSequence> containsPattern(String str) {
        return new ContainsPatternFromStringPredicate(str);
    }

    public static <T> List<T> defensiveCopy(T... tArr) {
        return defensiveCopy(Arrays.asList(tArr));
    }

    public static <T> Predicate<T> equalTo(T t) {
        return t == null ? isNull() : new IsEqualToPredicate(t, null);
    }

    public static <T> Predicate<T> in(Collection<? extends T> collection) {
        return new InPredicate(collection, null);
    }

    @GwtIncompatible
    public static Predicate<Object> instanceOf(Class<?> cls) {
        return new InstanceOfPredicate(cls, null);
    }

    @GwtCompatible(serializable = true)
    public static <T> Predicate<T> isNull() {
        return ObjectPredicate.IS_NULL.withNarrowedType();
    }

    public static <T> Predicate<T> not(Predicate<T> predicate) {
        return new NotPredicate(predicate);
    }

    @GwtCompatible(serializable = true)
    public static <T> Predicate<T> notNull() {
        return ObjectPredicate.NOT_NULL.withNarrowedType();
    }

    public static <T> Predicate<T> or(Iterable<? extends Predicate<? super T>> iterable) {
        return new OrPredicate(defensiveCopy(iterable), null);
    }

    @Beta
    @GwtIncompatible
    public static Predicate<Class<?>> subtypeOf(Class<?> cls) {
        return new SubtypeOfPredicate(cls, null);
    }

    public static String toStringHelper(String str, Iterable<?> iterable) {
        StringBuilder sb = new StringBuilder("Predicates.");
        sb.append(str);
        sb.append('(');
        boolean z = true;
        for (Object obj : iterable) {
            if (!z) {
                sb.append(',');
            }
            sb.append(obj);
            z = false;
        }
        sb.append(')');
        return sb.toString();
    }

    private static class AndPredicate<T> implements Predicate<T>, Serializable {
        public static final long serialVersionUID = 0;
        public final List<? extends Predicate<? super T>> components;

        public AndPredicate(List<? extends Predicate<? super T>> list) {
            this.components = list;
        }

        @Override // com.google.common.base.Predicate
        public boolean apply(T t) {
            for (int i = 0; i < this.components.size(); i++) {
                if (!this.components.get(i).apply(t)) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.common.base.Predicate
        public boolean equals(Object obj) {
            if (obj instanceof AndPredicate) {
                return this.components.equals(((AndPredicate) obj).components);
            }
            return false;
        }

        public int hashCode() {
            return this.components.hashCode() + 306654252;
        }

        public String toString() {
            return Predicates.toStringHelper("and", this.components);
        }

        public /* synthetic */ AndPredicate(List list, AnonymousClass1 anonymousClass1) {
            this.components = list;
        }
    }

    private static class IsEqualToPredicate<T> implements Predicate<T>, Serializable {
        public static final long serialVersionUID = 0;
        public final T target;

        public IsEqualToPredicate(T t) {
            this.target = t;
        }

        @Override // com.google.common.base.Predicate
        public boolean apply(T t) {
            return this.target.equals(t);
        }

        @Override // com.google.common.base.Predicate
        public boolean equals(Object obj) {
            if (obj instanceof IsEqualToPredicate) {
                return this.target.equals(((IsEqualToPredicate) obj).target);
            }
            return false;
        }

        public int hashCode() {
            return this.target.hashCode();
        }

        public String toString() {
            String strValueOf = String.valueOf(this.target);
            return a.a(strValueOf.length() + 20, "Predicates.equalTo(", strValueOf, ")");
        }

        /* JADX WARN: Multi-variable type inference failed */
        public /* synthetic */ IsEqualToPredicate(Object obj, AnonymousClass1 anonymousClass1) {
            this.target = obj;
        }
    }

    private static class OrPredicate<T> implements Predicate<T>, Serializable {
        public static final long serialVersionUID = 0;
        public final List<? extends Predicate<? super T>> components;

        public OrPredicate(List<? extends Predicate<? super T>> list) {
            this.components = list;
        }

        @Override // com.google.common.base.Predicate
        public boolean apply(T t) {
            for (int i = 0; i < this.components.size(); i++) {
                if (this.components.get(i).apply(t)) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.google.common.base.Predicate
        public boolean equals(Object obj) {
            if (obj instanceof OrPredicate) {
                return this.components.equals(((OrPredicate) obj).components);
            }
            return false;
        }

        public int hashCode() {
            return this.components.hashCode() + 87855567;
        }

        public String toString() {
            return Predicates.toStringHelper("or", this.components);
        }

        public /* synthetic */ OrPredicate(List list, AnonymousClass1 anonymousClass1) {
            this.components = list;
        }
    }

    @SafeVarargs
    public static <T> Predicate<T> and(Predicate<? super T>... predicateArr) {
        return new AndPredicate(defensiveCopy(predicateArr), null);
    }

    public static <T> List<T> defensiveCopy(Iterable<T> iterable) {
        ArrayList arrayList = new ArrayList();
        for (T t : iterable) {
            Preconditions.checkNotNull(t);
            arrayList.add(t);
        }
        return arrayList;
    }

    @SafeVarargs
    public static <T> Predicate<T> or(Predicate<? super T>... predicateArr) {
        return new OrPredicate(defensiveCopy(predicateArr), null);
    }

    private static class InPredicate<T> implements Predicate<T>, Serializable {
        public static final long serialVersionUID = 0;
        public final Collection<?> target;

        public InPredicate(Collection<?> collection) {
            if (collection == null) {
                throw new NullPointerException();
            }
            this.target = collection;
        }

        @Override // com.google.common.base.Predicate
        public boolean apply(T t) {
            try {
                return this.target.contains(t);
            } catch (ClassCastException | NullPointerException unused) {
                return false;
            }
        }

        @Override // com.google.common.base.Predicate
        public boolean equals(Object obj) {
            if (obj instanceof InPredicate) {
                return this.target.equals(((InPredicate) obj).target);
            }
            return false;
        }

        public int hashCode() {
            return this.target.hashCode();
        }

        public String toString() {
            String strValueOf = String.valueOf(this.target);
            return a.a(strValueOf.length() + 15, "Predicates.in(", strValueOf, ")");
        }

        public /* synthetic */ InPredicate(Collection collection, AnonymousClass1 anonymousClass1) {
            if (collection != null) {
                this.target = collection;
                return;
            }
            throw new NullPointerException();
        }
    }

    @GwtIncompatible
    private static class InstanceOfPredicate implements Predicate<Object>, Serializable {
        public static final long serialVersionUID = 0;
        public final Class<?> clazz;

        public InstanceOfPredicate(Class<?> cls) {
            if (cls == null) {
                throw new NullPointerException();
            }
            this.clazz = cls;
        }

        @Override // com.google.common.base.Predicate
        public boolean apply(Object obj) {
            return this.clazz.isInstance(obj);
        }

        @Override // com.google.common.base.Predicate
        public boolean equals(Object obj) {
            return (obj instanceof InstanceOfPredicate) && this.clazz == ((InstanceOfPredicate) obj).clazz;
        }

        public int hashCode() {
            return this.clazz.hashCode();
        }

        public String toString() {
            String name = this.clazz.getName();
            return a.a(name.length() + 23, "Predicates.instanceOf(", name, ")");
        }

        public /* synthetic */ InstanceOfPredicate(Class cls, AnonymousClass1 anonymousClass1) {
            if (cls != null) {
                this.clazz = cls;
                return;
            }
            throw new NullPointerException();
        }
    }

    public static <T> Predicate<T> and(Predicate<? super T> predicate, Predicate<? super T> predicate2) {
        if (predicate == null) {
            throw new NullPointerException();
        }
        if (predicate2 != null) {
            return new AndPredicate(asList(predicate, predicate2), null);
        }
        throw new NullPointerException();
    }

    public static <T> Predicate<T> or(Predicate<? super T> predicate, Predicate<? super T> predicate2) {
        if (predicate == null) {
            throw new NullPointerException();
        }
        if (predicate2 != null) {
            return new OrPredicate(asList(predicate, predicate2), null);
        }
        throw new NullPointerException();
    }
}
