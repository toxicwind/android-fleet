package io.ktor.util;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.exoplayer.upstream.CmcdData;
import io.ktor.http.ContentDisposition;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.AbstractMutableCollection;
import kotlin.collections.AbstractMutableSet;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.jvm.internal.markers.KMutableMap;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: CaseInsensitiveMap.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010#\n\u0002\b\u0003\n\u0002\u0010'\n\u0002\b\u0002\n\u0002\u0010\u001f\n\u0002\b\t\u0018\u0000 J*\b\b\u0000\u0010\u0002*\u00020\u00012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00028\u00000\u0003:\u0005KLMNJB\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u0019\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0004H\u0096\u0080\u0004¢\u0006\u0004\b\t\u0010\nJ\u0019\u0010\f\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u000e\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0007\u001a\u00020\u0004H\u0096\u0082\u0004¢\u0006\u0004\b\u000e\u0010\u000fJ\u0011\u0010\u0010\u001a\u00020\bH\u0096\u0080\u0004¢\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0013\u001a\u00020\u0012H\u0096\u0080\u0004¢\u0006\u0004\b\u0013\u0010\u0006J#\u0010\u0014\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00028\u0000H\u0096\u0080\b¢\u0006\u0004\b\u0014\u0010\u0015J'\u0010\u0018\u001a\u00020\u00122\u0014\u0010\u0017\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020\u0004\u0012\u0004\u0012\u00028\u00000\u0016H\u0096\u0080\u0004¢\u0006\u0004\b\u0018\u0010\u0019J\u001b\u0010\u001a\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0007\u001a\u00020\u0004H\u0096\u0080\b¢\u0006\u0004\b\u001a\u0010\u000fJ\u001f\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u001f\u001a\u00020\b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u0096\u0082\u0004¢\u0006\u0004\b\u001f\u0010\rJ\u0011\u0010 \u001a\u00020\u001bH\u0096\u0080\u0004¢\u0006\u0004\b \u0010!J\u0017\u0010\"\u001a\u00020\u001b2\u0006\u0010\u0007\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\"\u0010#J\u000f\u0010$\u001a\u00020\u0012H\u0002¢\u0006\u0004\b$\u0010\u0006J\u0017\u0010&\u001a\u00020\u00122\u0006\u0010%\u001a\u00020\u001bH\u0002¢\u0006\u0004\b&\u0010'J\u000f\u0010(\u001a\u00020\u0012H\u0002¢\u0006\u0004\b(\u0010\u0006R\u001e\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040)8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b*\u0010+R\u001e\u0010,\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010)8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b,\u0010-R\u0016\u0010.\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b.\u0010/R\u0016\u00101\u001a\u0002008\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b1\u00102R\u0016\u00103\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b3\u0010/R\"\u00105\u001a\u000e\u0018\u000104R\b\u0012\u0004\u0012\u00028\u00000\u00008\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b5\u00106R\"\u00108\u001a\u000e\u0018\u000107R\b\u0012\u0004\u0012\u00028\u00000\u00008\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b8\u00109R\"\u0010;\u001a\u000e\u0018\u00010:R\b\u0012\u0004\u0012\u00028\u00000\u00008\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b;\u0010<R\u0015\u0010>\u001a\u00020\u001b8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b=\u0010!R\u001b\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00040?8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b@\u0010AR'\u0010E\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00028\u00000C0?8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\bD\u0010AR\u001b\u0010I\u001a\b\u0012\u0004\u0012\u00028\u00000F8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\bG\u0010H¨\u0006O"}, d2 = {"Lio/ktor/util/CaseInsensitiveMap;", "", "Value", "", "", "<init>", "()V", "key", "", "containsKey", "(Ljava/lang/String;)Z", ES6Iterator.VALUE_PROPERTY, "containsValue", Signatures.MATH_TO_BOOLEAN, "get", "(Ljava/lang/String;)Ljava/lang/Object;", "isEmpty", "()Z", "", "clear", "put", "(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "", TypedValues.TransitionType.S_FROM, "putAll", "(Ljava/util/Map;)V", "remove", "", "putWithoutTrackingOrderReturnIndex", "(Ljava/lang/String;Ljava/lang/Object;)I", "other", "equals", "hashCode", "()I", "findIndex", "(Ljava/lang/String;)I", "ensureCapacity", "newCapacity", "resize", "(I)V", "compactInsertionOrder", "", "keyStorage", "[Ljava/lang/String;", "valueStorage", "[Ljava/lang/Object;", "_size", "I", "", "insertionOrder", "[I", "insertionCount", "Lio/ktor/util/CaseInsensitiveMap$KeySet;", "cachedKeySet", "Lio/ktor/util/CaseInsensitiveMap$KeySet;", "Lio/ktor/util/CaseInsensitiveMap$EntrySet;", "cachedEntrySet", "Lio/ktor/util/CaseInsensitiveMap$EntrySet;", "Lio/ktor/util/CaseInsensitiveMap$ValueCollection;", "cachedValueCollection", "Lio/ktor/util/CaseInsensitiveMap$ValueCollection;", "getSize", ContentDisposition.Parameters.Size, "", "getKeys", "()Ljava/util/Set;", "keys", "", "getEntries", "entries", "", "getValues", "()Ljava/util/Collection;", "values", "Companion", "KeySet", "ValueCollection", "EntrySet", "MapEntry", "ktor-utils"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CaseInsensitiveMap<Value> implements Map<String, Value>, KMutableMap {
    private static final int INITIAL_CAPACITY = 8;
    private int _size;
    private CaseInsensitiveMap<Value>.EntrySet cachedEntrySet;
    private CaseInsensitiveMap<Value>.KeySet cachedKeySet;
    private CaseInsensitiveMap<Value>.ValueCollection cachedValueCollection;
    private int insertionCount;
    private static final Companion Companion = new Companion(null);
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    private static final Object[] EMPTY_ANY_ARRAY = new Object[0];
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private String[] keyStorage = EMPTY_STRING_ARRAY;
    private Object[] valueStorage = EMPTY_ANY_ARRAY;
    private int[] insertionOrder = EMPTY_INT_ARRAY;

    @Override // java.util.Map
    public final /* bridge */ boolean containsKey(Object obj) {
        if (obj instanceof String) {
            return containsKey((String) obj);
        }
        return false;
    }

    @Override // java.util.Map
    public final /* bridge */ Set<Map.Entry<String, Value>> entrySet() {
        return getEntries();
    }

    @Override // java.util.Map
    public final /* bridge */ Value get(Object obj) {
        if (obj instanceof String) {
            return get((String) obj);
        }
        return null;
    }

    @Override // java.util.Map
    public final /* bridge */ Set<String> keySet() {
        return getKeys();
    }

    @Override // java.util.Map
    public final /* bridge */ Value remove(Object obj) {
        if (obj instanceof String) {
            return remove((String) obj);
        }
        return null;
    }

    @Override // java.util.Map
    public final /* bridge */ int size() {
        return get_size();
    }

    @Override // java.util.Map
    public final /* bridge */ Collection<Value> values() {
        return getValues();
    }

    /* JADX INFO: renamed from: getSize, reason: from getter */
    public int get_size() {
        return this._size;
    }

    public boolean containsKey(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return findIndex(key) >= 0;
    }

    @Override // java.util.Map
    public boolean containsValue(Object value) {
        if (value == null || this._size == 0) {
            return false;
        }
        int length = this.valueStorage.length;
        for (int i = 0; i < length; i++) {
            if (this.keyStorage[i] != null && Intrinsics.areEqual(this.valueStorage[i], value)) {
                return true;
            }
        }
        return false;
    }

    public Value get(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        int iFindIndex = findIndex(key);
        if (iFindIndex >= 0) {
            return (Value) this.valueStorage[iFindIndex];
        }
        return null;
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this._size == 0;
    }

    @Override // java.util.Map
    public void clear() {
        if (this._size > 0) {
            ArraysKt.fill$default(this.keyStorage, (Object) null, 0, 0, 6, (Object) null);
            ArraysKt.fill$default(this.valueStorage, (Object) null, 0, 0, 6, (Object) null);
            ArraysKt.fill$default(this.insertionOrder, -1, 0, 0, 6, (Object) null);
            this._size = 0;
            this.insertionCount = 0;
        }
    }

    @Override // java.util.Map
    public Value put(String key, Value value) {
        int i;
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        if (this.keyStorage == EMPTY_STRING_ARRAY) {
            this.keyStorage = new String[8];
            this.valueStorage = new Object[8];
            int[] iArr = new int[8];
            for (int i2 = 0; i2 < 8; i2++) {
                iArr[i2] = -1;
            }
            this.insertionOrder = iArr;
        }
        int iCaseInsensitiveHashCode = Companion.caseInsensitiveHashCode(key);
        int length = (this.keyStorage.length - 1) & iCaseInsensitiveHashCode;
        while (true) {
            String str = this.keyStorage[length];
            if (str == null) {
                ensureCapacity();
                int length2 = this.keyStorage.length;
                while (true) {
                    i = iCaseInsensitiveHashCode & (length2 - 1);
                    String[] strArr = this.keyStorage;
                    if (strArr[i] == null) {
                        break;
                    }
                    iCaseInsensitiveHashCode = i + 1;
                    length2 = strArr.length;
                }
                if (this.insertionCount == this.insertionOrder.length) {
                    compactInsertionOrder();
                }
                this.keyStorage[i] = key;
                this.valueStorage[i] = value;
                int[] iArr2 = this.insertionOrder;
                int i3 = this.insertionCount;
                this.insertionCount = i3 + 1;
                iArr2[i3] = i;
                this._size++;
                return null;
            }
            if (StringsKt.equals(str, key, true)) {
                Object[] objArr = this.valueStorage;
                Value value2 = (Value) objArr[length];
                objArr[length] = value;
                return value2;
            }
            length = (length + 1) & (this.keyStorage.length - 1);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Value remove(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        int iFindIndex = findIndex(key);
        if (iFindIndex < 0) {
            return null;
        }
        Value value = (Value) this.valueStorage[iFindIndex];
        int i = this.insertionCount;
        for (int i2 = 0; i2 < i; i2++) {
            int[] iArr = this.insertionOrder;
            if (iArr[i2] == iFindIndex) {
                iArr[i2] = -1;
                break;
            }
        }
        String[] strArr = this.keyStorage;
        strArr[iFindIndex] = null;
        this.valueStorage[iFindIndex] = null;
        this._size--;
        int i3 = iFindIndex + 1;
        int length = strArr.length;
        while (true) {
            int i4 = i3 & (length - 1);
            String str = this.keyStorage[i4];
            if (str == null) {
                return value;
            }
            Intrinsics.checkNotNull(str);
            Object[] objArr = this.valueStorage;
            Object obj = objArr[i4];
            this.keyStorage[i4] = null;
            objArr[i4] = null;
            this._size--;
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type Value of io.ktor.util.CaseInsensitiveMap");
            int iPutWithoutTrackingOrderReturnIndex = putWithoutTrackingOrderReturnIndex(str, obj);
            int i5 = this.insertionCount;
            for (int i6 = 0; i6 < i5; i6++) {
                int[] iArr2 = this.insertionOrder;
                if (iArr2[i6] == i4) {
                    iArr2[i6] = iPutWithoutTrackingOrderReturnIndex;
                    break;
                }
            }
            i3 = i4 + 1;
            length = this.keyStorage.length;
        }
    }

    private final int putWithoutTrackingOrderReturnIndex(String key, Value value) {
        int iCaseInsensitiveHashCode = Companion.caseInsensitiveHashCode(key);
        int length = this.keyStorage.length;
        while (true) {
            int i = iCaseInsensitiveHashCode & (length - 1);
            String[] strArr = this.keyStorage;
            String str = strArr[i];
            if (str == null) {
                strArr[i] = key;
                this.valueStorage[i] = value;
                this._size++;
                return i;
            }
            if (StringsKt.equals(str, key, true)) {
                this.valueStorage[i] = value;
                return i;
            }
            iCaseInsensitiveHashCode = i + 1;
            length = this.keyStorage.length;
        }
    }

    public Set<String> getKeys() {
        CaseInsensitiveMap<Value>.KeySet keySet = this.cachedKeySet;
        if (keySet != null) {
            return keySet;
        }
        CaseInsensitiveMap<Value>.KeySet keySet2 = new KeySet();
        this.cachedKeySet = keySet2;
        return keySet2;
    }

    public Set<Map.Entry<String, Value>> getEntries() {
        CaseInsensitiveMap<Value>.EntrySet entrySet = this.cachedEntrySet;
        if (entrySet != null) {
            return entrySet;
        }
        CaseInsensitiveMap<Value>.EntrySet entrySet2 = new EntrySet();
        this.cachedEntrySet = entrySet2;
        return entrySet2;
    }

    public Collection<Value> getValues() {
        CaseInsensitiveMap<Value>.ValueCollection valueCollection = this.cachedValueCollection;
        if (valueCollection != null) {
            return valueCollection;
        }
        CaseInsensitiveMap<Value>.ValueCollection valueCollection2 = new ValueCollection();
        this.cachedValueCollection = valueCollection2;
        return valueCollection2;
    }

    @Override // java.util.Map
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof CaseInsensitiveMap)) {
            return false;
        }
        CaseInsensitiveMap caseInsensitiveMap = (CaseInsensitiveMap) other;
        if (caseInsensitiveMap.size() != this._size) {
            return false;
        }
        int length = this.keyStorage.length;
        for (int i = 0; i < length; i++) {
            String str = this.keyStorage[i];
            if (str != null) {
                if (!Intrinsics.areEqual(caseInsensitiveMap.get((Object) str), this.valueStorage[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override // java.util.Map
    public int hashCode() {
        int length = this.keyStorage.length;
        int iHashCode = 0;
        for (int i = 0; i < length; i++) {
            String str = this.keyStorage[i];
            if (str != null) {
                int iCaseInsensitiveHashCode = Companion.caseInsensitiveHashCode(str);
                Object obj = this.valueStorage[i];
                iHashCode += iCaseInsensitiveHashCode ^ (obj != null ? obj.hashCode() : 0);
            }
        }
        return iHashCode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int findIndex(String key) {
        if (this._size == 0) {
            return -1;
        }
        int iCaseInsensitiveHashCode = Companion.caseInsensitiveHashCode(key);
        int length = this.keyStorage.length;
        while (true) {
            int i = iCaseInsensitiveHashCode & (length - 1);
            String str = this.keyStorage[i];
            if (str == null) {
                return -1;
            }
            if (StringsKt.equals(str, key, true)) {
                return i;
            }
            iCaseInsensitiveHashCode = i + 1;
            length = this.keyStorage.length;
        }
    }

    private final void ensureCapacity() {
        int i = this._size * 4;
        String[] strArr = this.keyStorage;
        if (i >= strArr.length * 3) {
            resize(strArr.length * 2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void resize(int newCapacity) {
        String str;
        String[] strArr = this.keyStorage;
        Object[] objArr = this.valueStorage;
        int[] iArr = this.insertionOrder;
        int i = this.insertionCount;
        this.keyStorage = new String[newCapacity];
        this.valueStorage = new Object[newCapacity];
        int[] iArr2 = new int[newCapacity];
        for (int i2 = 0; i2 < newCapacity; i2++) {
            iArr2[i2] = -1;
        }
        this.insertionOrder = iArr2;
        this._size = 0;
        this.insertionCount = 0;
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = iArr[i3];
            if (i4 >= 0 && (str = strArr[i4]) != null) {
                Intrinsics.checkNotNull(str);
                Object obj = objArr[i4];
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type Value of io.ktor.util.CaseInsensitiveMap");
                put(str, obj);
            }
        }
    }

    private final void compactInsertionOrder() {
        int[] iArr;
        int i = this.insertionCount;
        if (i == 0) {
            return;
        }
        int i2 = 0;
        int i3 = 0;
        while (true) {
            iArr = this.insertionOrder;
            if (i2 >= i) {
                break;
            }
            int i4 = iArr[i2];
            if (i4 >= 0 && this.keyStorage[i4] != null) {
                iArr[i3] = i4;
                i3++;
            }
            i2++;
        }
        int length = iArr.length;
        for (int i5 = i3; i5 < length; i5++) {
            this.insertionOrder[i5] = -1;
        }
        this.insertionCount = i3;
    }

    /* JADX INFO: compiled from: CaseInsensitiveMap.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010)\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0002H\u0096\u0080\b¢\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0002H\u0096\u0082\u0004¢\u0006\u0004\b\t\u0010\bJ\u0019\u0010\n\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0002H\u0096\u0080\b¢\u0006\u0004\b\n\u0010\bJ\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\u000bH\u0096\u0082\u0004¢\u0006\u0004\b\f\u0010\rR\u0015\u0010\u0011\u001a\u00020\u000e8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0012"}, d2 = {"Lio/ktor/util/CaseInsensitiveMap$KeySet;", "Lkotlin/collections/AbstractMutableSet;", "", "<init>", "(Lio/ktor/util/CaseInsensitiveMap;)V", "element", "", "add", "(Ljava/lang/String;)Z", "contains", "remove", "", "iterator", "()Ljava/util/Iterator;", "", "getSize", "()I", ContentDisposition.Parameters.Size, "ktor-utils"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class KeySet extends AbstractMutableSet<String> {
        public KeySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final /* bridge */ boolean contains(Object obj) {
            if (obj instanceof String) {
                return contains((String) obj);
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final /* bridge */ boolean remove(Object obj) {
            if (obj instanceof String) {
                return remove((String) obj);
            }
            return false;
        }

        @Override // kotlin.collections.AbstractMutableSet
        public int getSize() {
            return ((CaseInsensitiveMap) CaseInsensitiveMap.this)._size;
        }

        @Override // kotlin.collections.AbstractMutableSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean add(String element) {
            Intrinsics.checkNotNullParameter(element, "element");
            throw new UnsupportedOperationException("CaseInsensitiveMap.keys does not support add");
        }

        public boolean contains(String element) {
            Intrinsics.checkNotNullParameter(element, "element");
            return CaseInsensitiveMap.this.containsKey((Object) element);
        }

        public boolean remove(String element) {
            Intrinsics.checkNotNullParameter(element, "element");
            return CaseInsensitiveMap.this.remove((Object) element) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<String> iterator() {
            return new CaseInsensitiveMap$KeySet$iterator$1(CaseInsensitiveMap.this);
        }
    }

    /* JADX INFO: compiled from: CaseInsensitiveMap.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010)\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00028\u0000H\u0096\u0080\b¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0096\u0082\u0004¢\u0006\u0004\b\t\u0010\nR\u0015\u0010\u000e\u001a\u00020\u000b8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u000f"}, d2 = {"Lio/ktor/util/CaseInsensitiveMap$ValueCollection;", "Lkotlin/collections/AbstractMutableCollection;", "<init>", "(Lio/ktor/util/CaseInsensitiveMap;)V", "element", "", "add", Signatures.MATH_TO_BOOLEAN, "", "iterator", "()Ljava/util/Iterator;", "", "getSize", "()I", ContentDisposition.Parameters.Size, "ktor-utils"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class ValueCollection extends AbstractMutableCollection<Value> {
        public ValueCollection() {
        }

        @Override // kotlin.collections.AbstractMutableCollection
        public int getSize() {
            return ((CaseInsensitiveMap) CaseInsensitiveMap.this)._size;
        }

        @Override // kotlin.collections.AbstractMutableCollection, java.util.AbstractCollection, java.util.Collection
        public boolean add(Value element) {
            Intrinsics.checkNotNullParameter(element, "element");
            throw new UnsupportedOperationException("CaseInsensitiveMap.values does not support add");
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<Value> iterator() {
            return new CaseInsensitiveMap$ValueCollection$iterator$1(CaseInsensitiveMap.this);
        }
    }

    /* JADX INFO: compiled from: CaseInsensitiveMap.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010'\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010)\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00028\u00000\u00020\u0001B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J%\u0010\b\u001a\u00020\u00072\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00028\u00000\u0002H\u0096\u0080\b¢\u0006\u0004\b\b\u0010\tJ#\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00028\u00000\u00020\nH\u0096\u0082\u0004¢\u0006\u0004\b\u000b\u0010\fR\u0015\u0010\u0010\u001a\u00020\r8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Lio/ktor/util/CaseInsensitiveMap$EntrySet;", "Lkotlin/collections/AbstractMutableSet;", "", "", "<init>", "(Lio/ktor/util/CaseInsensitiveMap;)V", "element", "", "add", "(Ljava/util/Map$Entry;)Z", "", "iterator", "()Ljava/util/Iterator;", "", "getSize", "()I", ContentDisposition.Parameters.Size, "ktor-utils"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class EntrySet extends AbstractMutableSet<Map.Entry<String, Value>> {
        public EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final /* bridge */ boolean contains(Object obj) {
            if (TypeIntrinsics.isMutableMapEntry(obj)) {
                return contains((Map.Entry<String, Object>) obj);
            }
            return false;
        }

        public /* bridge */ boolean contains(Map.Entry<String, Object> entry) {
            return super.contains((Object) entry);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final /* bridge */ boolean remove(Object obj) {
            if (TypeIntrinsics.isMutableMapEntry(obj)) {
                return remove((Map.Entry<String, Object>) obj);
            }
            return false;
        }

        public /* bridge */ boolean remove(Map.Entry<String, Object> entry) {
            return super.remove((Object) entry);
        }

        @Override // kotlin.collections.AbstractMutableSet
        public int getSize() {
            return ((CaseInsensitiveMap) CaseInsensitiveMap.this)._size;
        }

        @Override // kotlin.collections.AbstractMutableSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean add(Map.Entry<String, Value> element) {
            Intrinsics.checkNotNullParameter(element, "element");
            throw new UnsupportedOperationException("CaseInsensitiveMap.entries does not support add");
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<String, Value>> iterator() {
            return new CaseInsensitiveMap$EntrySet$iterator$1(CaseInsensitiveMap.this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: CaseInsensitiveMap.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010'\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\b\u0082\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00028\u00000\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00028\u0000¢\u0006\u0004\b\u0005\u0010\u0006J\u0019\u0010\b\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00028\u0000H\u0096\u0080\b¢\u0006\u0004\b\b\u0010\tJ\u001b\u0010\r\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0096\u0082\u0004¢\u0006\u0004\b\r\u0010\u000eJ\u0011\u0010\u0010\u001a\u00020\u000fH\u0096\u0080\u0004¢\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0012\u001a\u00020\u0002H\u0096\u0080\u0004¢\u0006\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0084\b¢\u0006\f\n\u0004\b\u0003\u0010\u0014\u001a\u0004\b\u0015\u0010\u0013R\u0016\u0010\u0004\u001a\u00028\u00008\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0004\u0010\u0016R\u0015\u0010\u0019\u001a\u00028\u00008VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001a"}, d2 = {"Lio/ktor/util/CaseInsensitiveMap$MapEntry;", "", "", "key", "_value", "<init>", "(Lio/ktor/util/CaseInsensitiveMap;Ljava/lang/String;Ljava/lang/Object;)V", "newValue", "setValue", "(Ljava/lang/Object;)Ljava/lang/Object;", "", "other", "", "equals", Signatures.MATH_TO_BOOLEAN, "", "hashCode", "()I", "toString", "()Ljava/lang/String;", "Ljava/lang/String;", "getKey", "Ljava/lang/Object;", "getValue", "()Ljava/lang/Object;", ES6Iterator.VALUE_PROPERTY, "ktor-utils"}, k = 1, mv = {2, 3, 0}, xi = 48)
    final class MapEntry implements Map.Entry<String, Value>, KMutableMap.Entry {
        private Value _value;
        private final String key;
        final /* synthetic */ CaseInsensitiveMap<Value> this$0;

        public MapEntry(CaseInsensitiveMap caseInsensitiveMap, String key, Value _value) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(_value, "_value");
            this.this$0 = caseInsensitiveMap;
            this.key = key;
            this._value = _value;
        }

        @Override // java.util.Map.Entry
        public String getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public Value getValue() {
            return this._value;
        }

        @Override // java.util.Map.Entry
        public Value setValue(Value newValue) {
            Intrinsics.checkNotNullParameter(newValue, "newValue");
            Value value = this._value;
            this._value = newValue;
            int iFindIndex = this.this$0.findIndex(getKey());
            if (iFindIndex >= 0) {
                ((CaseInsensitiveMap) this.this$0).valueStorage[iFindIndex] = newValue;
            }
            return value;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object other) {
            if (!(other instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) other;
            return Intrinsics.areEqual(getKey(), entry.getKey()) && Intrinsics.areEqual(getValue(), entry.getValue());
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return getValue().hashCode() ^ getKey().hashCode();
        }

        public String toString() {
            return getKey() + '=' + getValue();
        }
    }

    /* JADX INFO: compiled from: CaseInsensitiveMap.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u00068\u0002X\u0082T¢\u0006\u0006\n\u0004\b\t\u0010\nR\u001c\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lio/ktor/util/CaseInsensitiveMap$Companion;", "", "<init>", "()V", "", CmcdData.STREAMING_FORMAT_SS, "", "caseInsensitiveHashCode", "(Ljava/lang/String;)I", "INITIAL_CAPACITY", "I", "", "EMPTY_STRING_ARRAY", "[Ljava/lang/String;", "EMPTY_ANY_ARRAY", "[Ljava/lang/Object;", "", "EMPTY_INT_ARRAY", "[I", "ktor-utils"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int caseInsensitiveHashCode(String s) {
            int length = s.length();
            int lowerCase = 0;
            for (int i = 0; i < length; i++) {
                lowerCase = (lowerCase * 31) + Character.toLowerCase(s.charAt(i));
            }
            return lowerCase;
        }
    }

    @Override // java.util.Map
    public void putAll(Map<? extends String, ? extends Value> from) {
        Intrinsics.checkNotNullParameter(from, "from");
        for (Map.Entry<? extends String, ? extends Value> entry : from.entrySet()) {
            put(entry.getKey(), (Object) entry.getValue());
        }
    }
}
