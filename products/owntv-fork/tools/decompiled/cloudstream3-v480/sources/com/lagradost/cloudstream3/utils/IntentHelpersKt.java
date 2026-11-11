package com.lagradost.cloudstream3.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IntentHelpers.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000(\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a$\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b¢\u0006\u0002\u0010\u0005\u001a\u001c\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007¨\u0006\r"}, d2 = {"getSafeParcelableExtra", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/content/Intent;", "key", "", "(Landroid/content/Intent;Ljava/lang/String;)Ljava/lang/Object;", "registerBroadcastReceiver", "", "Landroid/content/Context;", "receiver", "Landroid/content/BroadcastReceiver;", "actionFilter", "Landroid/content/IntentFilter;", "app"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class IntentHelpersKt {
    public static final /* synthetic */ <T> T getSafeParcelableExtra(Intent intent, String key) {
        Intrinsics.checkNotNullParameter(intent, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        if (Build.VERSION.SDK_INT < 33) {
            return (T) intent.getParcelableExtra(key);
        }
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) intent.getParcelableExtra(key, Object.class);
    }

    public static final void registerBroadcastReceiver(Context context, BroadcastReceiver receiver, IntentFilter actionFilter) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(receiver, "receiver");
        Intrinsics.checkNotNullParameter(actionFilter, "actionFilter");
        if (Build.VERSION.SDK_INT >= 33) {
            context.registerReceiver(receiver, actionFilter, 4);
        } else {
            context.registerReceiver(receiver, actionFilter);
        }
    }
}
