package io.ktor.util.debug.plugins;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: PluginsTrace.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\b\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0017\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0011\u0010\b\u001a\u00020\u0007H\u0096\u0080\u0004¢\u0006\u0004\b\b\u0010\tJ\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002HÆ\u0003¢\u0006\u0004\b\n\u0010\u000bJ \u0010\f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002HÆ\u0001¢\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u0011\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eHÖ\u0083\u0004¢\u0006\u0004\b\u0011\u0010\u0012J\u0011\u0010\u0014\u001a\u00020\u0013HÖ\u0081\u0004¢\u0006\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0016\u001a\u0004\b\u0017\u0010\u000b¨\u0006\u0019"}, d2 = {"Lio/ktor/util/debug/plugins/PluginsTrace;", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "", "Lio/ktor/util/debug/plugins/PluginTraceElement;", "eventOrder", "<init>", "(Ljava/util/List;)V", "", "toString", "()Ljava/lang/String;", "component1", "()Ljava/util/List;", "copy", "(Ljava/util/List;)Lio/ktor/util/debug/plugins/PluginsTrace;", "", "other", "", "equals", Signatures.MATH_TO_BOOLEAN, "", "hashCode", "()I", "Ljava/util/List;", "getEventOrder", "Key", "ktor-utils"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class PluginsTrace extends AbstractCoroutineContextElement {

    /* JADX INFO: renamed from: Key, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final List<PluginTraceElement> eventOrder;

    /* JADX WARN: Multi-variable type inference failed */
    public PluginsTrace() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PluginsTrace copy$default(PluginsTrace pluginsTrace, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = pluginsTrace.eventOrder;
        }
        return pluginsTrace.copy(list);
    }

    public final List<PluginTraceElement> component1() {
        return this.eventOrder;
    }

    public final PluginsTrace copy(List<PluginTraceElement> eventOrder) {
        Intrinsics.checkNotNullParameter(eventOrder, "eventOrder");
        return new PluginsTrace(eventOrder);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof PluginsTrace) && Intrinsics.areEqual(this.eventOrder, ((PluginsTrace) other).eventOrder);
    }

    public int hashCode() {
        return this.eventOrder.hashCode();
    }

    public /* synthetic */ PluginsTrace(ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new ArrayList() : arrayList);
    }

    public final List<PluginTraceElement> getEventOrder() {
        return this.eventOrder;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PluginsTrace(List<PluginTraceElement> eventOrder) {
        super(INSTANCE);
        Intrinsics.checkNotNullParameter(eventOrder, "eventOrder");
        this.eventOrder = eventOrder;
    }

    /* JADX INFO: renamed from: io.ktor.util.debug.plugins.PluginsTrace$Key, reason: from kotlin metadata */
    /* JADX INFO: compiled from: PluginsTrace.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lio/ktor/util/debug/plugins/PluginsTrace$Key;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lio/ktor/util/debug/plugins/PluginsTrace;", "<init>", "()V", "ktor-utils"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion implements CoroutineContext.Key<PluginsTrace> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public String toString() {
        return "PluginsTrace(" + CollectionsKt.joinToString$default(this.eventOrder, null, null, null, 0, null, null, 63, null) + ')';
    }
}
