package app.cash.zipline;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CallResult.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\n\u0018\u00002\u00020\u0001B)\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lapp/cash/zipline/CallResult;", "", "result", "Lkotlin/Result;", "encodedResult", "", "serviceNames", "", "<init>", "(Ljava/lang/Object;Ljava/lang/String;Ljava/util/List;)V", "getResult-d1pmJ48", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getEncodedResult", "()Ljava/lang/String;", "getServiceNames", "()Ljava/util/List;", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CallResult {
    private final String encodedResult;
    private final Object result;
    private final List<String> serviceNames;

    public CallResult(Object obj, String encodedResult, List<String> serviceNames) {
        Intrinsics.checkNotNullParameter(encodedResult, "encodedResult");
        Intrinsics.checkNotNullParameter(serviceNames, "serviceNames");
        this.result = obj;
        this.encodedResult = encodedResult;
        this.serviceNames = CollectionsKt.toList(serviceNames);
    }

    /* JADX INFO: renamed from: getResult-d1pmJ48, reason: not valid java name and from getter */
    public final Object getResult() {
        return this.result;
    }

    public final String getEncodedResult() {
        return this.encodedResult;
    }

    public final List<String> getServiceNames() {
        return this.serviceNames;
    }
}
