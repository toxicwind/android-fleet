package app.cash.zipline.internal.bridge;

import app.cash.zipline.CallResult;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: calls.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B)\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\u0004\b\b\u0010\tR\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u00118F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lapp/cash/zipline/internal/bridge/EncodedResultOrCallback;", "", ES6Iterator.VALUE_PROPERTY, "Lapp/cash/zipline/internal/bridge/ResultOrCallback;", "json", "", "serviceNames", "", "<init>", "(Lapp/cash/zipline/internal/bridge/ResultOrCallback;Ljava/lang/String;Ljava/util/List;)V", "getValue", "()Lapp/cash/zipline/internal/bridge/ResultOrCallback;", "getJson", "()Ljava/lang/String;", "getServiceNames", "()Ljava/util/List;", "callResult", "Lapp/cash/zipline/CallResult;", "getCallResult", "()Lapp/cash/zipline/CallResult;", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class EncodedResultOrCallback {
    private final String json;
    private final List<String> serviceNames;
    private final ResultOrCallback<?> value;

    public EncodedResultOrCallback(ResultOrCallback<?> value, String json, List<String> serviceNames) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(serviceNames, "serviceNames");
        this.value = value;
        this.json = json;
        this.serviceNames = CollectionsKt.toList(serviceNames);
    }

    public final ResultOrCallback<?> getValue() {
        return this.value;
    }

    public final String getJson() {
        return this.json;
    }

    public final List<String> getServiceNames() {
        return this.serviceNames;
    }

    public final CallResult getCallResult() {
        Result<?> resultM8087getResultxLWZpok = this.value.m8087getResultxLWZpok();
        if (resultM8087getResultxLWZpok != null) {
            return new CallResult(resultM8087getResultxLWZpok.getValue(), this.json, this.serviceNames);
        }
        return null;
    }
}
