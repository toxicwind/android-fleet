package app.cash.zipline;

import androidx.core.app.NotificationCompat;
import app.cash.zipline.internal.bridge.CallChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JniCallChannel.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J!\u0010\b\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\tH\u0082 J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tH\u0016J!\u0010\f\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\tH\u0082 R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lapp/cash/zipline/JniCallChannel;", "Lapp/cash/zipline/internal/bridge/CallChannel;", "quickJs", "Lapp/cash/zipline/QuickJs;", "instance", "", "<init>", "(Lapp/cash/zipline/QuickJs;J)V", NotificationCompat.CATEGORY_CALL, "", "callJson", "context", "disconnect", "", "instanceName", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JniCallChannel implements CallChannel {
    private final long instance;
    private final QuickJs quickJs;

    private final native String call(long context, long instance, String callJson);

    private final native boolean disconnect(long context, long instance, String instanceName);

    public JniCallChannel(QuickJs quickJs, long j) {
        Intrinsics.checkNotNullParameter(quickJs, "quickJs");
        this.quickJs = quickJs;
        this.instance = j;
    }

    @Override // app.cash.zipline.internal.bridge.CallChannel
    public String call(String callJson) {
        Intrinsics.checkNotNullParameter(callJson, "callJson");
        return call(this.quickJs.getContext(), this.instance, callJson);
    }

    @Override // app.cash.zipline.internal.bridge.CallChannel
    public boolean disconnect(String instanceName) {
        Intrinsics.checkNotNullParameter(instanceName, "instanceName");
        return disconnect(this.quickJs.getContext(), this.instance, instanceName);
    }
}
