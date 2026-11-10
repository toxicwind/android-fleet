package com.google.common.eventbus;

import b.a.a.a.a;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.primitives.Primitives;
import com.google.common.reflect.TypeToken;
import com.google.common.util.concurrent.UncheckedExecutionException;
import com.google.j2objc.annotations.Weak;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: loaded from: classes.dex */
public final class SubscriberRegistry {

    @Weak
    public final EventBus bus;
    public final ConcurrentMap<Class<?>, CopyOnWriteArraySet<Subscriber>> subscribers = new ConcurrentHashMap();
    public static final LoadingCache<Class<?>, ImmutableList<Method>> subscriberMethodsCache = new CacheBuilder().weakKeys().build(new CacheLoader<Class<?>, ImmutableList<Method>>() { // from class: com.google.common.eventbus.SubscriberRegistry.1
        @Override // com.google.common.cache.CacheLoader
        public ImmutableList<Method> load(Class<?> cls) {
            return SubscriberRegistry.getAnnotatedMethodsNotCached(cls);
        }
    });
    public static final LoadingCache<Class<?>, ImmutableSet<Class<?>>> flattenHierarchyCache = new CacheBuilder().weakKeys().build(new CacheLoader<Class<?>, ImmutableSet<Class<?>>>() { // from class: com.google.common.eventbus.SubscriberRegistry.2
        @Override // com.google.common.cache.CacheLoader
        public ImmutableSet<Class<?>> load(Class<?> cls) {
            return ImmutableSet.copyOf(new TypeToken.SimpleTypeToken(cls).getTypes().rawTypes());
        }
    });

    private static final class MethodIdentifier {
        public final String name;
        public final List<Class<?>> parameterTypes;

        public MethodIdentifier(Method method) {
            this.name = method.getName();
            this.parameterTypes = Arrays.asList(method.getParameterTypes());
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof MethodIdentifier)) {
                return false;
            }
            MethodIdentifier methodIdentifier = (MethodIdentifier) obj;
            return this.name.equals(methodIdentifier.name) && this.parameterTypes.equals(methodIdentifier.parameterTypes);
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.name, this.parameterTypes});
        }
    }

    public SubscriberRegistry(EventBus eventBus) {
        if (eventBus == null) {
            throw new NullPointerException();
        }
        this.bus = eventBus;
    }

    private Multimap<Class<?>, Subscriber> findAllSubscribers(Object obj) {
        HashMultimap hashMultimap = new HashMultimap();
        UnmodifiableIterator<Method> it = getAnnotatedMethods(obj.getClass()).iterator();
        while (it.hasNext()) {
            Method next = it.next();
            hashMultimap.put(next.getParameterTypes()[0], Subscriber.create(this.bus, obj, next));
        }
        return hashMultimap;
    }

    @VisibleForTesting
    public static ImmutableSet<Class<?>> flattenHierarchy(Class<?> cls) {
        try {
            return flattenHierarchyCache.getUnchecked(cls);
        } catch (UncheckedExecutionException e2) {
            Throwables.propagate(e2.getCause());
            throw null;
        }
    }

    public static ImmutableList<Method> getAnnotatedMethods(Class<?> cls) {
        try {
            return subscriberMethodsCache.getUnchecked(cls);
        } catch (UncheckedExecutionException e2) {
            Throwables.throwIfUnchecked(e2.getCause());
            throw e2;
        }
    }

    public static ImmutableList<Method> getAnnotatedMethodsNotCached(Class<?> cls) {
        Set<Class<? super T>> setRawTypes = new TypeToken.SimpleTypeToken(cls).getTypes().rawTypes();
        HashMap map = new HashMap();
        Iterator it = setRawTypes.iterator();
        while (it.hasNext()) {
            for (Method method : ((Class) it.next()).getDeclaredMethods()) {
                if (method.isAnnotationPresent(Subscribe.class) && !method.isSynthetic()) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    Preconditions.checkArgument(parameterTypes.length == 1, "Method %s has @Subscribe annotation but has %s parameters. Subscriber methods must have exactly 1 parameter.", (Object) method, parameterTypes.length);
                    Preconditions.checkArgument(!parameterTypes[0].isPrimitive(), "@Subscribe method %s's parameter is %s. Subscriber methods cannot accept primitives. Consider changing the parameter to %s.", method, parameterTypes[0].getName(), Primitives.wrap(parameterTypes[0]).getSimpleName());
                    MethodIdentifier methodIdentifier = new MethodIdentifier(method);
                    if (!map.containsKey(methodIdentifier)) {
                        map.put(methodIdentifier, method);
                    }
                }
            }
        }
        return ImmutableList.copyOf(map.values());
    }

    public Iterator<Subscriber> getSubscribers(Object obj) {
        ImmutableSet<Class<?>> immutableSetFlattenHierarchy = flattenHierarchy(obj.getClass());
        ArrayList arrayListNewArrayListWithCapacity = Lists.newArrayListWithCapacity(immutableSetFlattenHierarchy.size());
        UnmodifiableIterator<Class<?>> it = immutableSetFlattenHierarchy.iterator();
        while (it.hasNext()) {
            CopyOnWriteArraySet<Subscriber> copyOnWriteArraySet = this.subscribers.get(it.next());
            if (copyOnWriteArraySet != null) {
                arrayListNewArrayListWithCapacity.add(copyOnWriteArraySet.iterator());
            }
        }
        return new Iterators.ConcatenatedIterator(arrayListNewArrayListWithCapacity.iterator());
    }

    @VisibleForTesting
    public Set<Subscriber> getSubscribersForTesting(Class<?> cls) {
        return (Set) MoreObjects.firstNonNull(this.subscribers.get(cls), ImmutableSet.of());
    }

    public void register(Object obj) {
        for (Map.Entry<Class<?>, Collection<Subscriber>> entry : findAllSubscribers(obj).asMap().entrySet()) {
            Class<?> key = entry.getKey();
            Collection<Subscriber> value = entry.getValue();
            CopyOnWriteArraySet<Subscriber> copyOnWriteArraySet = this.subscribers.get(key);
            if (copyOnWriteArraySet == null) {
                CopyOnWriteArraySet<Subscriber> copyOnWriteArraySet2 = new CopyOnWriteArraySet<>();
                copyOnWriteArraySet = (CopyOnWriteArraySet) MoreObjects.firstNonNull(this.subscribers.putIfAbsent(key, copyOnWriteArraySet2), copyOnWriteArraySet2);
            }
            copyOnWriteArraySet.addAll(value);
        }
    }

    public void unregister(Object obj) {
        for (Map.Entry<Class<?>, Collection<Subscriber>> entry : findAllSubscribers(obj).asMap().entrySet()) {
            Class<?> key = entry.getKey();
            Collection<Subscriber> value = entry.getValue();
            CopyOnWriteArraySet<Subscriber> copyOnWriteArraySet = this.subscribers.get(key);
            if (copyOnWriteArraySet == null || !copyOnWriteArraySet.removeAll(value)) {
                String strValueOf = String.valueOf(obj);
                throw new IllegalArgumentException(a.a(strValueOf.length() + 65, "missing event subscriber for an annotated method. Is ", strValueOf, " registered?"));
            }
        }
    }
}
