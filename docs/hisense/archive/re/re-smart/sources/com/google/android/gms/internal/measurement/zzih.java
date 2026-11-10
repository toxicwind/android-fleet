package com.google.android.gms.internal.measurement;

import b.a.a.a.a;
import com.google.android.gms.internal.measurement.zzig;
import com.google.android.gms.internal.measurement.zzih;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class zzih<MessageType extends zzih<MessageType, BuilderType>, BuilderType extends zzig<MessageType, BuilderType>> implements zzlc {
    public int zzb = 0;

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> void zzbq(Iterable<T> iterable, List<? super T> list) {
        zzkf.zze(iterable);
        if (iterable instanceof zzkm) {
            List<?> listZzh = ((zzkm) iterable).zzh();
            zzkm zzkmVar = (zzkm) list;
            int size = list.size();
            for (Object obj : listZzh) {
                if (obj == null) {
                    int size2 = zzkmVar.size();
                    StringBuilder sb = new StringBuilder(37);
                    sb.append("Element at index ");
                    sb.append(size2 - size);
                    sb.append(" is null.");
                    String string = sb.toString();
                    int size3 = zzkmVar.size();
                    while (true) {
                        size3--;
                        if (size3 < size) {
                            throw new NullPointerException(string);
                        }
                        zzkmVar.remove(size3);
                    }
                } else if (obj instanceof zzix) {
                    zzkmVar.zzi((zzix) obj);
                } else {
                    zzkmVar.add((String) obj);
                }
            }
            return;
        }
        if (iterable instanceof zzlj) {
            list.addAll(iterable);
            return;
        }
        if ((list instanceof ArrayList) && (iterable instanceof Collection)) {
            ((ArrayList) list).ensureCapacity(iterable.size() + list.size());
        }
        int size4 = list.size();
        for (T t : iterable) {
            if (t == null) {
                int size5 = list.size();
                StringBuilder sb2 = new StringBuilder(37);
                sb2.append("Element at index ");
                sb2.append(size5 - size4);
                sb2.append(" is null.");
                String string2 = sb2.toString();
                int size6 = list.size();
                while (true) {
                    size6--;
                    if (size6 < size4) {
                        throw new NullPointerException(string2);
                    }
                    list.remove(size6);
                }
            } else {
                list.add(t);
            }
        }
    }

    public int zzbo() {
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzlc
    public final zzix zzbp() {
        try {
            int iZzbt = zzbt();
            zzix zzixVar = zzix.zzb;
            byte[] bArr = new byte[iZzbt];
            zzje zzjeVarZzC = zzje.zzC(bArr);
            zzbH(zzjeVarZzC);
            zzjeVarZzC.zzD();
            return new zziv(bArr);
        } catch (IOException e2) {
            String name = getClass().getName();
            throw new RuntimeException(a.a(new StringBuilder(name.length() + 72), "Serializing ", name, " to a ByteString threw an IOException (should never happen)."), e2);
        }
    }

    public void zzbr(int i) {
        throw null;
    }

    public final byte[] zzbs() {
        try {
            byte[] bArr = new byte[zzbt()];
            zzje zzjeVarZzC = zzje.zzC(bArr);
            zzbH(zzjeVarZzC);
            zzjeVarZzC.zzD();
            return bArr;
        } catch (IOException e2) {
            String name = getClass().getName();
            throw new RuntimeException(a.a(new StringBuilder(name.length() + 72), "Serializing ", name, " to a byte array threw an IOException (should never happen)."), e2);
        }
    }
}
