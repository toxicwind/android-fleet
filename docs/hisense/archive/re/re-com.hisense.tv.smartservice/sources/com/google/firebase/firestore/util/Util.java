package com.google.firebase.firestore.util;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import b.a.a.a.a;
import b.e.b.d.g.t;
import c.a.Aa;
import c.a.Ba;
import c.a.za;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.cloud.datastore.core.number.NumberComparisonHelper;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.util.Util;
import com.google.protobuf.ByteString;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedSet;

/* JADX INFO: loaded from: classes.dex */
public class Util {
    public static final String AUTO_ID_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public static final int AUTO_ID_LENGTH = 20;
    public static final Random rand = new SecureRandom();
    public static final Continuation<Void, Void> VOID_ERROR_TRANSFORMER = new Continuation() { // from class: b.e.b.d.g.o
        @Override // com.google.android.gms.tasks.Continuation
        public final Object then(Task task) {
            return Util.a(task);
        }
    };

    public static /* synthetic */ Void a(Task task) throws Exception {
        if (task.isSuccessful()) {
            return (Void) task.getResult();
        }
        Exception excConvertStatusException = convertStatusException(task.getException());
        if (excConvertStatusException instanceof FirebaseFirestoreException) {
            throw excConvertStatusException;
        }
        throw new FirebaseFirestoreException(excConvertStatusException.getMessage(), FirebaseFirestoreException.Code.UNKNOWN, excConvertStatusException);
    }

    @Nullable
    public static <T> T advanceIterator(Iterator<T> it) {
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }

    public static String autoId() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            sb.append(AUTO_ID_ALPHABET.charAt(rand.nextInt(62)));
        }
        return sb.toString();
    }

    public static List<Object> collectUpdateArguments(int i, Object obj, Object obj2, Object... objArr) {
        if (objArr.length % 2 == 1) {
            throw new IllegalArgumentException("Missing value in call to update().  There must be an even number of arguments that alternate between field names and values");
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(obj);
        arrayList.add(obj2);
        Collections.addAll(arrayList, objArr);
        for (int i2 = 0; i2 < arrayList.size(); i2 += 2) {
            Object obj3 = arrayList.get(i2);
            if (!(obj3 instanceof String) && !(obj3 instanceof FieldPath)) {
                StringBuilder sbA = a.a("Excepted field name at argument position ");
                sbA.append(i2 + i + 1);
                sbA.append(" but got ");
                sbA.append(obj3);
                sbA.append(" in call to update.  The arguments to update should alternate between field names and values");
                throw new IllegalArgumentException(sbA.toString());
            }
        }
        return arrayList;
    }

    public static <T extends Comparable<T>> Comparator<T> comparator() {
        return t.f563a;
    }

    public static int compareBooleans(boolean z, boolean z2) {
        if (z == z2) {
            return 0;
        }
        return z ? 1 : -1;
    }

    public static int compareByteArrays(byte[] bArr, byte[] bArr2) {
        int iMin = Math.min(bArr.length, bArr2.length);
        for (int i = 0; i < iMin; i++) {
            int i2 = bArr[i] & 255;
            int i3 = bArr2[i] & 255;
            if (i2 < i3) {
                return -1;
            }
            if (i2 > i3) {
                return 1;
            }
        }
        return compareIntegers(bArr.length, bArr2.length);
    }

    public static int compareByteStrings(ByteString byteString, ByteString byteString2) {
        int iMin = Math.min(byteString.size(), byteString2.size());
        for (int i = 0; i < iMin; i++) {
            int iByteAt = byteString.byteAt(i) & 255;
            int iByteAt2 = byteString2.byteAt(i) & 255;
            if (iByteAt < iByteAt2) {
                return -1;
            }
            if (iByteAt > iByteAt2) {
                return 1;
            }
        }
        return compareIntegers(byteString.size(), byteString2.size());
    }

    public static int compareDoubles(double d2, double d3) {
        return NumberComparisonHelper.firestoreCompareDoubles(d2, d3);
    }

    public static int compareIntegers(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i > i2 ? 1 : 0;
    }

    public static int compareLongs(long j, long j2) {
        return NumberComparisonHelper.compareLongs(j, j2);
    }

    public static int compareMixed(double d2, long j) {
        return NumberComparisonHelper.firestoreCompareDoubleWithLong(d2, j);
    }

    public static Exception convertStatusException(Exception exc) {
        if (exc instanceof Aa) {
            return exceptionFromStatus(((Aa) exc).f779a);
        }
        return exc instanceof Ba ? exceptionFromStatus(((Ba) exc).f786a) : exc;
    }

    public static Exception convertThrowableToException(Throwable th) {
        return th instanceof Exception ? convertStatusException((Exception) th) : new Exception(th);
    }

    public static void crashMainThread(final RuntimeException runtimeException) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: b.e.b.d.g.p
            @Override // java.lang.Runnable
            public final void run() {
                Util.a(runtimeException);
                throw null;
            }
        });
    }

    public static <T> void diffCollections(Collection<T> collection, Collection<T> collection2, Comparator<T> comparator, Consumer<T> consumer, Consumer<T> consumer2) {
        ArrayList arrayList = new ArrayList(collection);
        Collections.sort(arrayList, comparator);
        ArrayList arrayList2 = new ArrayList(collection2);
        Collections.sort(arrayList2, comparator);
        diffCollections(arrayList.iterator(), arrayList2.iterator(), comparator, consumer, consumer2);
    }

    public static FirebaseFirestoreException exceptionFromStatus(za zaVar) {
        Aa aaA = zaVar.a();
        return new FirebaseFirestoreException(aaA.getMessage(), FirebaseFirestoreException.Code.fromValue(zaVar.p.s), aaA);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <K, V> Map<K, V> firstNEntries(Map<K, V> map, int i, final Comparator<V> comparator) {
        if (map.size() <= i) {
            return map;
        }
        ArrayList arrayList = new ArrayList(map.entrySet());
        Collections.sort(arrayList, new Comparator() { // from class: b.e.b.d.g.q
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return comparator.compare(((Map.Entry) obj).getValue(), ((Map.Entry) obj2).getValue());
            }
        });
        HashMap map2 = new HashMap();
        for (int i2 = 0; i2 < i; i2++) {
            map2.put(((Map.Entry) arrayList.get(i2)).getKey(), ((Map.Entry) arrayList.get(i2)).getValue());
        }
        return map2;
    }

    public static <T> int nullSafeCompare(@Nullable T t, @Nullable T t2, Comparator<T> comparator) {
        if (t != null && t2 != null) {
            return comparator.compare(t, t2);
        }
        if (t == null) {
            return t2 == null ? 0 : -1;
        }
        return 1;
    }

    public static StringBuilder repeatSequence(CharSequence charSequence, int i, CharSequence charSequence2) {
        StringBuilder sb = new StringBuilder();
        if (i != 0) {
            sb.append(charSequence);
            for (int i2 = 1; i2 < i; i2++) {
                sb.append(charSequence2);
                sb.append(charSequence);
            }
        }
        return sb;
    }

    public static String toDebugString(ByteString byteString) {
        int size = byteString.size();
        StringBuilder sb = new StringBuilder(size * 2);
        for (int i = 0; i < size; i++) {
            int iByteAt = byteString.byteAt(i) & 255;
            sb.append(Character.forDigit(iByteAt >>> 4, 16));
            sb.append(Character.forDigit(iByteAt & 15, 16));
        }
        return sb.toString();
    }

    public static String typeName(@Nullable Object obj) {
        return obj == null ? "null" : obj.getClass().getName();
    }

    public static <K, V> Iterable<V> values(final Iterable<Map.Entry<K, V>> iterable) {
        return new Iterable() { // from class: b.e.b.d.g.r
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return Util.a(iterable);
            }
        };
    }

    public static Continuation<Void, Void> voidErrorTransformer() {
        return VOID_ERROR_TRANSFORMER;
    }

    public static <T extends Comparable<T>> int nullSafeCompare(@Nullable T t, @Nullable T t2) {
        return nullSafeCompare(t, t2, t.f563a);
    }

    public static <T extends Comparable<T>> void diffCollections(SortedSet<T> sortedSet, SortedSet<T> sortedSet2, Consumer<T> consumer, Consumer<T> consumer2) {
        diffCollections(sortedSet.iterator(), sortedSet2.iterator(), sortedSet.comparator(), consumer, consumer2);
    }

    public static /* synthetic */ void a(RuntimeException runtimeException) {
        throw runtimeException;
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0023  */
    public static <T> void diffCollections(Iterator<T> it, Iterator<T> it2, Comparator<? super T> comparator, Consumer<T> consumer, Consumer<T> consumer2) {
        T t = (Object) advanceIterator(it);
        T t2 = (Object) advanceIterator(it2);
        while (true) {
            if (t == null && t2 == null) {
                return;
            }
            boolean z = false;
            boolean z2 = true;
            if (t != null && t2 != null) {
                int iCompare = comparator.compare(t, t2);
                if (iCompare >= 0) {
                    if (iCompare > 0) {
                        z2 = false;
                        z = true;
                    } else {
                        z2 = false;
                    }
                }
            } else if (t == null) {
                z2 = false;
                z = true;
            }
            if (z) {
                consumer.accept(t2);
                t2 = (Object) advanceIterator(it2);
            } else if (z2) {
                consumer2.accept(t);
                t = (Object) advanceIterator(it);
            } else {
                t = (Object) advanceIterator(it);
                t2 = (Object) advanceIterator(it2);
            }
        }
    }

    public static /* synthetic */ Iterator a(Iterable iterable) {
        final Iterator it = iterable.iterator();
        return new Iterator<V>() { // from class: com.google.firebase.firestore.util.Util.1
            @Override // java.util.Iterator
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override // java.util.Iterator
            public V next() {
                return (V) ((Map.Entry) it.next()).getValue();
            }
        };
    }
}
