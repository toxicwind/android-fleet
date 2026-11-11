package app.cash.zipline.internal.bridge;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;

/* JADX INFO: compiled from: CallChannel.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\ba\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H'J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0003H'¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lapp/cash/zipline/internal/bridge/CallChannel;", "", NotificationCompat.CATEGORY_CALL, "", "callJson", "disconnect", "", "instanceName", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface CallChannel {
    String call(String callJson);

    boolean disconnect(String instanceName);
}
