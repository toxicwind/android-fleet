package androidx.compose.runtime.saveable;

import androidx.collection.MutableScatterMap;
import androidx.collection.ScatterMapKt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: SaveableStateRegistry.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B9\u0012\u001c\u0010\u0002\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u0018\u00010\u0003\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u0007\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u0006H\u0016J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0012\u001a\u00020\u0004H\u0016J \u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00042\u000e\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u000fH\u0016J\u001c\u0010\u0016\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00050\u0003H\u0016R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0002\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u0018\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0012\u0012\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u000f0\u000e\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Landroidx/compose/runtime/saveable/SaveableStateRegistryImpl;", "Landroidx/compose/runtime/saveable/SaveableStateRegistry;", "restored", "", "", "", "", "canBeSaved", "Lkotlin/Function1;", "", "<init>", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)V", "Landroidx/collection/MutableScatterMap;", "valueProviders", "", "Lkotlin/Function0;", ES6Iterator.VALUE_PROPERTY, "consumeRestored", "key", "registerProvider", "Landroidx/compose/runtime/saveable/SaveableStateRegistry$Entry;", "valueProvider", "performSave", "runtime-saveable"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class SaveableStateRegistryImpl implements SaveableStateRegistry {
    private final Function1<Object, Boolean> canBeSaved;
    private final MutableScatterMap<String, List<Object>> restored;
    private MutableScatterMap<String, List<Function0<Object>>> valueProviders;

    public SaveableStateRegistryImpl(Map<String, ? extends List<? extends Object>> map, Function1<Object, Boolean> function1) {
        this.canBeSaved = function1;
        this.restored = (map == null || map.isEmpty()) ? null : SaveableStateRegistryKt.toMutableScatterMap(map);
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public boolean canBeSaved(Object value) {
        return this.canBeSaved.invoke(value).booleanValue();
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public Object consumeRestored(String key) {
        MutableScatterMap<String, List<Object>> mutableScatterMap;
        MutableScatterMap<String, List<Object>> mutableScatterMap2 = this.restored;
        List<Object> listRemove = mutableScatterMap2 != null ? mutableScatterMap2.remove(key) : null;
        List<Object> list = listRemove;
        if (list == null || list.isEmpty()) {
            return null;
        }
        if (listRemove.size() > 1 && (mutableScatterMap = this.restored) != null) {
            mutableScatterMap.put(key, listRemove.subList(1, listRemove.size()));
        }
        return listRemove.get(0);
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public SaveableStateRegistry.Entry registerProvider(final String key, final Function0<? extends Object> valueProvider) {
        if (SaveableStateRegistryKt.fastIsBlank(key)) {
            throw new IllegalArgumentException("Registered key is empty or blank".toString());
        }
        final MutableScatterMap<String, List<Function0<Object>>> mutableScatterMapMutableScatterMapOf = this.valueProviders;
        if (mutableScatterMapMutableScatterMapOf == null) {
            mutableScatterMapMutableScatterMapOf = ScatterMapKt.mutableScatterMapOf();
            this.valueProviders = mutableScatterMapMutableScatterMapOf;
        }
        ArrayList arrayList = mutableScatterMapMutableScatterMapOf.get(key);
        if (arrayList == null) {
            arrayList = new ArrayList();
            mutableScatterMapMutableScatterMapOf.set(key, arrayList);
        }
        arrayList.add(valueProvider);
        return new SaveableStateRegistry.Entry() { // from class: androidx.compose.runtime.saveable.SaveableStateRegistryImpl.registerProvider.3
            @Override // androidx.compose.runtime.saveable.SaveableStateRegistry.Entry
            public void unregister() {
                List<Function0<Object>> listRemove = mutableScatterMapMutableScatterMapOf.remove(key);
                if (listRemove != null) {
                    listRemove.remove(valueProvider);
                }
                List<Function0<Object>> list = listRemove;
                if (list == null || list.isEmpty()) {
                    return;
                }
                mutableScatterMapMutableScatterMapOf.set(key, listRemove);
            }
        };
    }

    /* JADX WARN: Code duplicated, block: B:36:0x009b  */
    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public Map<String, List<Object>> performSave() {
        char c;
        long j;
        long j2;
        long j3;
        long[] jArr;
        int i;
        int i2;
        MutableScatterMap<String, List<Object>> mutableScatterMap = this.restored;
        if (mutableScatterMap == null && this.valueProviders == null) {
            return MapsKt.emptyMap();
        }
        int i3 = 0;
        int size = mutableScatterMap != null ? mutableScatterMap.get_size() : 0;
        MutableScatterMap<String, List<Function0<Object>>> mutableScatterMap2 = this.valueProviders;
        HashMap map = new HashMap(size + (mutableScatterMap2 != null ? mutableScatterMap2.get_size() : 0));
        MutableScatterMap<String, List<Object>> mutableScatterMap3 = this.restored;
        char c2 = 7;
        long j4 = -9187201950435737472L;
        int i4 = 8;
        if (mutableScatterMap3 != null) {
            MutableScatterMap<String, List<Object>> mutableScatterMap4 = mutableScatterMap3;
            Object[] objArr = mutableScatterMap4.keys;
            Object[] objArr2 = mutableScatterMap4.values;
            long[] jArr2 = mutableScatterMap4.metadata;
            int length = jArr2.length - 2;
            if (length >= 0) {
                int i5 = 0;
                j2 = 128;
                while (true) {
                    long j5 = jArr2[i5];
                    j3 = 255;
                    if ((((~j5) << c2) & j5 & j4) != j4) {
                        int i6 = 8 - ((~(i5 - length)) >>> 31);
                        int i7 = 0;
                        while (i7 < i6) {
                            if ((j5 & 255) < 128) {
                                int i8 = (i5 << 3) + i7;
                                map.put((String) objArr[i8], (List) objArr2[i8]);
                            }
                            j5 >>= 8;
                            i7++;
                            c2 = c2;
                            j4 = j4;
                        }
                        c = c2;
                        j = j4;
                        if (i6 != 8) {
                            break;
                        }
                    } else {
                        c = c2;
                        j = j4;
                    }
                    if (i5 == length) {
                        break;
                    }
                    i5++;
                    c2 = c;
                    j4 = j;
                }
            } else {
                c = 7;
                j = -9187201950435737472L;
                j2 = 128;
                j3 = 255;
            }
        } else {
            c = 7;
            j = -9187201950435737472L;
            j2 = 128;
            j3 = 255;
        }
        MutableScatterMap<String, List<Function0<Object>>> mutableScatterMap5 = this.valueProviders;
        if (mutableScatterMap5 != null) {
            MutableScatterMap<String, List<Function0<Object>>> mutableScatterMap6 = mutableScatterMap5;
            Object[] objArr3 = mutableScatterMap6.keys;
            Object[] objArr4 = mutableScatterMap6.values;
            long[] jArr3 = mutableScatterMap6.metadata;
            int length2 = jArr3.length - 2;
            if (length2 >= 0) {
                int i9 = 0;
                while (true) {
                    long j6 = jArr3[i9];
                    if ((((~j6) << c) & j6 & j) != j) {
                        int i10 = 8 - ((~(i9 - length2)) >>> 31);
                        int i11 = i3;
                        while (i11 < i10) {
                            if ((j6 & j3) < j2) {
                                int i12 = (i9 << 3) + i11;
                                Object obj = objArr3[i12];
                                List list = (List) objArr4[i12];
                                String str = (String) obj;
                                i2 = i4;
                                if (list.size() == 1) {
                                    Object objInvoke = ((Function0) list.get(i3)).invoke();
                                    if (objInvoke != null) {
                                        if (!canBeSaved(objInvoke)) {
                                            throw new IllegalStateException(RememberSaveableKt.generateCannotBeSavedErrorMessage(objInvoke).toString());
                                        }
                                        Object[] objArr5 = new Object[1];
                                        objArr5[i3] = objInvoke;
                                        map.put(str, CollectionsKt.arrayListOf(objArr5));
                                    }
                                } else {
                                    HashMap map2 = map;
                                    int size2 = list.size();
                                    ArrayList arrayList = new ArrayList(size2);
                                    int i13 = 0;
                                    while (i13 < size2) {
                                        int i14 = i13;
                                        Object objInvoke2 = ((Function0) list.get(i13)).invoke();
                                        if (objInvoke2 != null && !canBeSaved(objInvoke2)) {
                                            throw new IllegalStateException(RememberSaveableKt.generateCannotBeSavedErrorMessage(objInvoke2).toString());
                                        }
                                        arrayList.add(objInvoke2);
                                        i13 = i14 + 1;
                                    }
                                    map2.put(str, arrayList);
                                }
                            } else {
                                i2 = i4;
                            }
                            j6 >>= i2;
                            i11++;
                            i4 = i2;
                            jArr3 = jArr3;
                            i3 = 0;
                        }
                        jArr = jArr3;
                        i = i4;
                        if (i10 != i) {
                            break;
                        }
                    } else {
                        jArr = jArr3;
                        i = i4;
                    }
                    if (i9 == length2) {
                        break;
                    }
                    i9++;
                    i4 = i;
                    jArr3 = jArr;
                    i3 = 0;
                }
            }
        }
        return map;
    }
}
