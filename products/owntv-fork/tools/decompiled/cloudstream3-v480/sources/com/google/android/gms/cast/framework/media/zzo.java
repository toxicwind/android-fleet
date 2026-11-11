package com.google.android.gms.cast.framework.media;

import android.util.SparseIntArray;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.internal.CastUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzo extends RemoteMediaClient.Callback {
    final /* synthetic */ MediaQueue zza;

    public zzo(MediaQueue mediaQueue) {
        this.zza = mediaQueue;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void onStatusUpdated() {
        MediaQueue mediaQueue = this.zza;
        long jZzp = mediaQueue.zzp();
        if (jZzp != mediaQueue.zza) {
            mediaQueue.zza = jZzp;
            mediaQueue.zzl();
            if (mediaQueue.zza != 0) {
                mediaQueue.zzo();
            }
        }
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void zzb(int[] iArr) {
        MediaQueue mediaQueue = this.zza;
        List listZzd = CastUtils.zzd(iArr);
        if (mediaQueue.zzb.equals(listZzd)) {
            return;
        }
        mediaQueue.zzx();
        mediaQueue.zzd.evictAll();
        mediaQueue.zze.clear();
        mediaQueue.zzb = listZzd;
        MediaQueue.zzk(mediaQueue);
        mediaQueue.zzv();
        mediaQueue.zzu();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void zzd(MediaQueueItem[] mediaQueueItemArr) {
        HashSet hashSet = new HashSet();
        MediaQueue mediaQueue = this.zza;
        List list = mediaQueue.zze;
        list.clear();
        for (MediaQueueItem mediaQueueItem : mediaQueueItemArr) {
            int itemId = mediaQueueItem.getItemId();
            mediaQueue.zzd.put(Integer.valueOf(itemId), mediaQueueItem);
            int i = mediaQueue.zzc.get(itemId, -1);
            if (i == -1) {
                mediaQueue.zzo();
                return;
            }
            hashSet.add(Integer.valueOf(i));
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            int i2 = mediaQueue.zzc.get(((Integer) it.next()).intValue(), -1);
            if (i2 != -1) {
                hashSet.add(Integer.valueOf(i2));
            }
        }
        list.clear();
        ArrayList arrayList = new ArrayList(hashSet);
        Collections.sort(arrayList);
        mediaQueue.zzx();
        mediaQueue.zzw(CastUtils.zzf(arrayList));
        mediaQueue.zzu();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void zze(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            MediaQueue mediaQueue = this.zza;
            mediaQueue.zzd.remove(Integer.valueOf(i));
            SparseIntArray sparseIntArray = mediaQueue.zzc;
            int i2 = sparseIntArray.get(i, -1);
            if (i2 == -1) {
                mediaQueue.zzo();
                return;
            } else {
                sparseIntArray.delete(i);
                arrayList.add(Integer.valueOf(i2));
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        Collections.sort(arrayList);
        MediaQueue mediaQueue2 = this.zza;
        mediaQueue2.zzx();
        mediaQueue2.zzb.removeAll(CastUtils.zzd(iArr));
        MediaQueue.zzk(mediaQueue2);
        MediaQueue.zzf(mediaQueue2, CastUtils.zzf(arrayList));
        mediaQueue2.zzu();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void zzf(List list, List list2, int i) {
        int size;
        ArrayList arrayList = new ArrayList();
        if (i == 0) {
            size = this.zza.zzb.size();
        } else {
            boolean zIsEmpty = list2.isEmpty();
            MediaQueue mediaQueue = this.zza;
            if (zIsEmpty) {
                mediaQueue.zzg.w("Received a Queue Reordered message with an empty reordered items IDs list.", new Object[0]);
                size = -1;
            } else {
                SparseIntArray sparseIntArray = mediaQueue.zzc;
                size = sparseIntArray.get(i, -1);
                if (size == -1) {
                    size = sparseIntArray.get(((Integer) list2.get(0)).intValue(), -1);
                }
            }
        }
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            int iIntValue = ((Integer) it.next()).intValue();
            MediaQueue mediaQueue2 = this.zza;
            int i2 = mediaQueue2.zzc.get(iIntValue, -1);
            if (i2 == -1) {
                mediaQueue2.zzo();
                return;
            }
            arrayList.add(Integer.valueOf(i2));
        }
        MediaQueue mediaQueue3 = this.zza;
        mediaQueue3.zzx();
        mediaQueue3.zzb = list;
        MediaQueue.zzk(mediaQueue3);
        MediaQueue.zzg(mediaQueue3, arrayList, size);
        mediaQueue3.zzu();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void zzg(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            MediaQueue mediaQueue = this.zza;
            mediaQueue.zzd.remove(Integer.valueOf(i));
            int i2 = mediaQueue.zzc.get(i, -1);
            if (i2 == -1) {
                mediaQueue.zzo();
                return;
            }
            arrayList.add(Integer.valueOf(i2));
        }
        Collections.sort(arrayList);
        MediaQueue mediaQueue2 = this.zza;
        mediaQueue2.zzx();
        mediaQueue2.zzw(CastUtils.zzf(arrayList));
        mediaQueue2.zzu();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void zzh() {
        this.zza.zzo();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void zzc(int[] iArr, int i) {
        int size;
        MediaQueue mediaQueue = this.zza;
        if (i == 0) {
            size = mediaQueue.zzb.size();
        } else {
            size = mediaQueue.zzc.get(i, -1);
            if (size == -1) {
                mediaQueue.zzo();
                return;
            }
        }
        int length = iArr.length;
        MediaQueue mediaQueue2 = this.zza;
        mediaQueue2.zzx();
        mediaQueue2.zzb.addAll(size, CastUtils.zzd(iArr));
        MediaQueue.zzk(mediaQueue2);
        MediaQueue.zze(mediaQueue2, size, length);
        mediaQueue2.zzu();
    }
}
