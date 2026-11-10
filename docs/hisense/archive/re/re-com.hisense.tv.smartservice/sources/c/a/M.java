package c.a;

import androidx.core.app.NotificationCompat;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public abstract class M {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0086b.C0007b<M> f832a = new C0086b.C0007b<>("io.grpc.config-selector");

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final za f833a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Object f834b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public InterfaceC0227k f835c;

        public /* synthetic */ a(za zaVar, Object obj, InterfaceC0227k interfaceC0227k, L l) {
            Preconditions.checkNotNull(zaVar, NotificationCompat.CATEGORY_STATUS);
            this.f833a = zaVar;
            this.f834b = obj;
            this.f835c = interfaceC0227k;
        }
    }

    public abstract a a(U.e eVar);
}
