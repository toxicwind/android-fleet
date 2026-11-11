package com.lagradost.cloudstream3.ui;

import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ControllerActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0013\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"getItemIndex", "", "Lcom/google/android/gms/cast/framework/media/RemoteMediaClient;", "(Lcom/google/android/gms/cast/framework/media/RemoteMediaClient;)Ljava/lang/Integer;", "app"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ControllerActivityKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Integer getItemIndex(RemoteMediaClient remoteMediaClient) {
        try {
            int[] itemIds = remoteMediaClient.getMediaQueue().getItemIds();
            Intrinsics.checkNotNullExpressionValue(itemIds, "getItemIds(...)");
            MediaQueueItem currentItem = remoteMediaClient.getCurrentItem();
            int iIndexOf = ArraysKt.indexOf(itemIds, currentItem != null ? currentItem.getItemId() : 0);
            if (iIndexOf < 0) {
                return null;
            }
            return Integer.valueOf(iIndexOf);
        } catch (Exception unused) {
            return null;
        }
    }
}
