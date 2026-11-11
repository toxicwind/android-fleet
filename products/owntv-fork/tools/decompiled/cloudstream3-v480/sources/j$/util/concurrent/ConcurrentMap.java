package j$.util.concurrent;

import j$.util.Map;
import j$.util.Objects;
import j$.util.function.BiConsumer$CC;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/* JADX INFO: loaded from: classes3.dex */
public interface ConcurrentMap<K, V> extends Map<K, V> {

    /* JADX INFO: renamed from: j$.util.concurrent.ConcurrentMap$-EL, reason: invalid class name */
    public final /* synthetic */ class EL {
        public static /* synthetic */ Object computeIfAbsent(java.util.concurrent.ConcurrentMap concurrentMap, Object obj, Function function) {
            return concurrentMap instanceof ConcurrentMap ? ((ConcurrentMap) concurrentMap).computeIfAbsent(obj, function) : CC.$default$computeIfAbsent(concurrentMap, obj, function);
        }

        public static /* synthetic */ void forEach(java.util.concurrent.ConcurrentMap concurrentMap, BiConsumer biConsumer) {
            if (concurrentMap instanceof ConcurrentMap) {
                ((ConcurrentMap) concurrentMap).forEach(biConsumer);
            } else {
                CC.$default$forEach(concurrentMap, biConsumer);
            }
        }
    }

    @Override // java.util.concurrent.ConcurrentMap, j$.util.concurrent.ConcurrentMap, j$.util.Map
    V compute(K k, BiFunction<? super K, ? super V, ? extends V> biFunction);

    @Override // java.util.concurrent.ConcurrentMap, j$.util.concurrent.ConcurrentMap, j$.util.Map
    V computeIfAbsent(K k, Function<? super K, ? extends V> function);

    @Override // java.util.concurrent.ConcurrentMap, j$.util.concurrent.ConcurrentMap, j$.util.Map
    V computeIfPresent(K k, BiFunction<? super K, ? super V, ? extends V> biFunction);

    @Override // java.util.concurrent.ConcurrentMap, j$.util.concurrent.ConcurrentMap, j$.util.Map
    void forEach(BiConsumer<? super K, ? super V> biConsumer);

    @Override // java.util.concurrent.ConcurrentMap, j$.util.concurrent.ConcurrentMap, j$.util.Map
    V getOrDefault(Object obj, V v);

    @Override // java.util.concurrent.ConcurrentMap, j$.util.concurrent.ConcurrentMap, j$.util.Map
    V merge(K k, V v, BiFunction<? super V, ? super V, ? extends V> biFunction);

    @Override // java.util.concurrent.ConcurrentMap, j$.util.concurrent.ConcurrentMap, j$.util.Map
    void replaceAll(BiFunction<? super K, ? super V, ? extends V> biFunction);

    /* JADX INFO: renamed from: j$.util.concurrent.ConcurrentMap$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static Object $default$getOrDefault(java.util.concurrent.ConcurrentMap concurrentMap, Object obj, Object obj2) {
            Object obj3 = concurrentMap.get(obj);
            return obj3 != null ? obj3 : obj2;
        }

        public static void $default$forEach(java.util.concurrent.ConcurrentMap concurrentMap, BiConsumer biConsumer) {
            Objects.requireNonNull(biConsumer);
            for (java.util.Map.Entry<K, V> entry : concurrentMap.entrySet()) {
                try {
                    biConsumer.accept(entry.getKey(), entry.getValue());
                } catch (IllegalStateException unused) {
                }
            }
        }

        public static void $default$replaceAll(final java.util.concurrent.ConcurrentMap concurrentMap, final BiFunction biFunction) {
            Objects.requireNonNull(biFunction);
            EL.forEach(concurrentMap, new BiConsumer() { // from class: j$.util.concurrent.ConcurrentMap.0
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CC.$private$lambda$replaceAll$0(concurrentMap, biFunction, obj, obj2);
                }

                public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
                    return BiConsumer$CC.$default$andThen(this, biConsumer);
                }
            });
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void $private$lambda$replaceAll$0(java.util.concurrent.ConcurrentMap concurrentMap, BiFunction biFunction, Object obj, Object obj2) {
            while (!concurrentMap.replace(obj, obj2, biFunction.apply(obj, obj2)) && (obj2 = concurrentMap.get(obj)) != null) {
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static Object $default$computeIfAbsent(java.util.concurrent.ConcurrentMap concurrentMap, Object obj, Function function) {
            Object objApply;
            Objects.requireNonNull(function);
            Object obj2 = concurrentMap.get(obj);
            if (obj2 != null || (objApply = function.apply(obj)) == null) {
                return obj2;
            }
            Object objPutIfAbsent = concurrentMap.putIfAbsent(obj, objApply);
            return objPutIfAbsent == null ? objApply : objPutIfAbsent;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static Object $default$computeIfPresent(java.util.concurrent.ConcurrentMap concurrentMap, Object obj, BiFunction biFunction) {
            Objects.requireNonNull(biFunction);
            while (true) {
                Object obj2 = concurrentMap.get(obj);
                if (obj2 == null) {
                    return null;
                }
                Object objApply = biFunction.apply(obj, obj2);
                if (objApply == null) {
                    if (concurrentMap.remove(obj, obj2)) {
                        return objApply;
                    }
                } else if (concurrentMap.replace(obj, obj2, objApply)) {
                    return objApply;
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static Object $default$compute(java.util.concurrent.ConcurrentMap concurrentMap, Object obj, BiFunction biFunction) {
            Object objApply;
            loop0: while (true) {
                Object objPutIfAbsent = concurrentMap.get(obj);
                do {
                    objApply = biFunction.apply(obj, objPutIfAbsent);
                    if (objApply != null) {
                        if (objPutIfAbsent != null) {
                            if (concurrentMap.replace(obj, objPutIfAbsent, objApply)) {
                                break;
                            }
                        } else {
                            objPutIfAbsent = concurrentMap.putIfAbsent(obj, objApply);
                        }
                    } else if (objPutIfAbsent == null || concurrentMap.remove(obj, objPutIfAbsent)) {
                        return null;
                    }
                } while (objPutIfAbsent != null);
            }
            return objApply;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static Object $default$merge(java.util.concurrent.ConcurrentMap concurrentMap, Object obj, Object obj2, BiFunction biFunction) {
            Objects.requireNonNull(biFunction);
            Objects.requireNonNull(obj2);
            while (true) {
                Object objPutIfAbsent = concurrentMap.get(obj);
                while (objPutIfAbsent == null) {
                    objPutIfAbsent = concurrentMap.putIfAbsent(obj, obj2);
                    if (objPutIfAbsent == null) {
                        return obj2;
                    }
                }
                Object objApply = biFunction.apply(objPutIfAbsent, obj2);
                if (objApply != null) {
                    if (concurrentMap.replace(obj, objPutIfAbsent, objApply)) {
                        return objApply;
                    }
                } else if (concurrentMap.remove(obj, objPutIfAbsent)) {
                    return null;
                }
            }
        }
    }
}
