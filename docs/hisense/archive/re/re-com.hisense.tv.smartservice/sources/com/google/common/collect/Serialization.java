package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@GwtIncompatible
public final class Serialization {
    public static <T> FieldSetter<T> getFieldSetter(Class<T> cls, String str) {
        try {
            return new FieldSetter<>(cls.getDeclaredField(str), null);
        } catch (NoSuchFieldException e2) {
            throw new AssertionError(e2);
        }
    }

    public static <K, V> void populateMap(Map<K, V> map, ObjectInputStream objectInputStream) {
        populateMap(map, objectInputStream, objectInputStream.readInt());
    }

    public static <K, V> void populateMultimap(Multimap<K, V> multimap, ObjectInputStream objectInputStream) throws IOException {
        populateMultimap(multimap, objectInputStream, objectInputStream.readInt());
    }

    public static <E> void populateMultiset(Multiset<E> multiset, ObjectInputStream objectInputStream) {
        populateMultiset(multiset, objectInputStream, objectInputStream.readInt());
    }

    public static int readCount(ObjectInputStream objectInputStream) {
        return objectInputStream.readInt();
    }

    public static <K, V> void writeMap(Map<K, V> map, ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(map.size());
        for (Map.Entry<K, V> entry : map.entrySet()) {
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeObject(entry.getValue());
        }
    }

    public static <K, V> void writeMultimap(Multimap<K, V> multimap, ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(multimap.asMap().size());
        for (Map.Entry<K, Collection<V>> entry : multimap.asMap().entrySet()) {
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeInt(entry.getValue().size());
            Iterator<V> it = entry.getValue().iterator();
            while (it.hasNext()) {
                objectOutputStream.writeObject(it.next());
            }
        }
    }

    public static <E> void writeMultiset(Multiset<E> multiset, ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(multiset.entrySet().size());
        for (Multiset.Entry<E> entry : multiset.entrySet()) {
            objectOutputStream.writeObject(entry.getElement());
            objectOutputStream.writeInt(entry.getCount());
        }
    }

    static final class FieldSetter<T> {
        public final Field field;

        public FieldSetter(Field field) {
            this.field = field;
            field.setAccessible(true);
        }

        public void set(T t, Object obj) {
            try {
                this.field.set(t, obj);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }

        public void set(T t, int i) {
            try {
                this.field.set(t, Integer.valueOf(i));
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }

        public /* synthetic */ FieldSetter(Field field, AnonymousClass1 anonymousClass1) {
            this.field = field;
            field.setAccessible(true);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <K, V> void populateMap(Map<K, V> map, ObjectInputStream objectInputStream, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            map.put(objectInputStream.readObject(), objectInputStream.readObject());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <K, V> void populateMultimap(Multimap<K, V> multimap, ObjectInputStream objectInputStream, int i) throws IOException {
        for (int i2 = 0; i2 < i; i2++) {
            Collection collection = multimap.get(objectInputStream.readObject());
            int i3 = objectInputStream.readInt();
            for (int i4 = 0; i4 < i3; i4++) {
                collection.add(objectInputStream.readObject());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <E> void populateMultiset(Multiset<E> multiset, ObjectInputStream objectInputStream, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            multiset.add(objectInputStream.readObject(), objectInputStream.readInt());
        }
    }
}
