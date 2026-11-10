package c.a;

import com.google.common.annotations.VisibleForTesting;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: classes2.dex */
public final class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final r f1897a = new r(new InterfaceC0231o.a(), InterfaceC0231o.b.f1883a);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ConcurrentMap<String, InterfaceC0233q> f1898b = new ConcurrentHashMap();

    @VisibleForTesting
    public r(InterfaceC0233q... interfaceC0233qArr) {
        for (InterfaceC0233q interfaceC0233q : interfaceC0233qArr) {
            this.f1898b.put(interfaceC0233q.a(), interfaceC0233q);
        }
    }
}
