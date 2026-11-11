package app.cash.zipline;

import androidx.core.app.NotificationCompat;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Call.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0010\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007\u0012\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\t\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\t¢\u0006\u0004\b\f\u0010\rJ\n\u0010\u0018\u001a\u00020\u0003H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0015\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0015\u0010\b\u001a\u0006\u0012\u0002\b\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015¨\u0006\u0019"}, d2 = {"Lapp/cash/zipline/Call;", "", "serviceName", "", NotificationCompat.CATEGORY_SERVICE, "Lapp/cash/zipline/ZiplineService;", "function", "Lapp/cash/zipline/ZiplineFunction;", "args", "", "encodedCall", "serviceNames", "<init>", "(Ljava/lang/String;Lapp/cash/zipline/ZiplineService;Lapp/cash/zipline/ZiplineFunction;Ljava/util/List;Ljava/lang/String;Ljava/util/List;)V", "getServiceName", "()Ljava/lang/String;", "getService", "()Lapp/cash/zipline/ZiplineService;", "getFunction", "()Lapp/cash/zipline/ZiplineFunction;", "getArgs", "()Ljava/util/List;", "getEncodedCall", "getServiceNames", "toString", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Call {
    private final List<?> args;
    private final String encodedCall;
    private final ZiplineFunction<?> function;
    private final ZiplineService service;
    private final String serviceName;
    private final List<String> serviceNames;

    public Call(String serviceName, ZiplineService service, ZiplineFunction<?> function, List<?> args, String encodedCall, List<String> serviceNames) {
        Intrinsics.checkNotNullParameter(serviceName, "serviceName");
        Intrinsics.checkNotNullParameter(service, "service");
        Intrinsics.checkNotNullParameter(function, "function");
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(encodedCall, "encodedCall");
        Intrinsics.checkNotNullParameter(serviceNames, "serviceNames");
        this.serviceName = serviceName;
        this.service = service;
        this.function = function;
        this.args = args;
        this.encodedCall = encodedCall;
        this.serviceNames = CollectionsKt.toList(serviceNames);
    }

    public final String getServiceName() {
        return this.serviceName;
    }

    public final ZiplineService getService() {
        return this.service;
    }

    public final ZiplineFunction<?> getFunction() {
        return this.function;
    }

    public final List<?> getArgs() {
        return this.args;
    }

    public final String getEncodedCall() {
        return this.encodedCall;
    }

    public final List<String> getServiceNames() {
        return this.serviceNames;
    }

    public String toString() {
        return "Call(receiver=" + this.service + ", function=" + this.function.getSignature() + ", args=" + this.args + ")";
    }
}
