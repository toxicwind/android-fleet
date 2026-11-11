package com.google.android.gms.internal.cast;

import com.google.android.gms.internal.cast.zzvz;
import com.google.android.gms.internal.cast.zzwa;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public abstract class zzwa<MessageType extends zzwa<MessageType, BuilderType>, BuilderType extends zzvz<MessageType, BuilderType>> implements zzyl {
    protected int zza = 0;

    protected static void zzt(Iterable iterable, List list) {
        byte[] bArr = zzxo.zzb;
        int size = ((Collection) iterable).size();
        if (list instanceof ArrayList) {
            ((ArrayList) list).ensureCapacity(list.size() + size);
        } else if (list instanceof zzyt) {
            ((zzyt) list).zzf(list.size() + size);
        }
        int size2 = list.size();
        List list2 = (List) iterable;
        int size3 = list2.size();
        for (int i = 0; i < size3; i++) {
            Object obj = list2.get(i);
            if (obj == null) {
                String str = "Element at index " + (list.size() - size2) + " is null.";
                int size4 = list.size();
                while (true) {
                    size4--;
                    if (size4 < size2) {
                        throw new NullPointerException(str);
                    }
                    list.remove(size4);
                }
            } else {
                list.add(obj);
            }
        }
    }

    public final byte[] zzP() {
        try {
            int iZzw = zzw();
            byte[] bArr = new byte[iZzw];
            int i = zzwu.zzb;
            zzwr zzwrVar = new zzwr(bArr, 0, iZzw);
            zzO(zzwrVar);
            zzwrVar.zzB();
            return bArr;
        } catch (IOException e) {
            throw new RuntimeException("Serializing " + getClass().getName() + " to a byte array threw an IOException (should never happen).", e);
        }
    }

    int zzr(zzyv zzyvVar) {
        throw null;
    }

    @Override // com.google.android.gms.internal.cast.zzyl
    public final zzwn zzs() {
        try {
            int iZzw = zzw();
            zzwn zzwnVar = zzwn.zzb;
            byte[] bArr = new byte[iZzw];
            int i = zzwu.zzb;
            zzwr zzwrVar = new zzwr(bArr, 0, iZzw);
            zzO(zzwrVar);
            zzwrVar.zzB();
            return new zzwl(bArr);
        } catch (IOException e) {
            throw new RuntimeException("Serializing " + getClass().getName() + " to a ByteString threw an IOException (should never happen).", e);
        }
    }
}
