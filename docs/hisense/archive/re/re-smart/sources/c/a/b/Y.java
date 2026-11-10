package c.a.b;

import c.a.EnumC0234s;
import com.google.common.base.Preconditions;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
public final class Y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ArrayList<a> f1189a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile EnumC0234s f1190b = EnumC0234s.IDLE;

    private static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Runnable f1191a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Executor f1192b;

        public a(Runnable runnable, Executor executor) {
            this.f1191a = runnable;
            this.f1192b = executor;
        }
    }

    public void a(Runnable runnable, Executor executor, EnumC0234s enumC0234s) {
        Preconditions.checkNotNull(runnable, "callback");
        Preconditions.checkNotNull(executor, "executor");
        Preconditions.checkNotNull(enumC0234s, FirebaseAnalytics.Param.SOURCE);
        a aVar = new a(runnable, executor);
        if (this.f1190b != enumC0234s) {
            aVar.f1192b.execute(aVar.f1191a);
        } else {
            this.f1189a.add(aVar);
        }
    }

    public void a(EnumC0234s enumC0234s) {
        Preconditions.checkNotNull(enumC0234s, "newState");
        if (this.f1190b == enumC0234s || this.f1190b == EnumC0234s.SHUTDOWN) {
            return;
        }
        this.f1190b = enumC0234s;
        if (this.f1189a.isEmpty()) {
            return;
        }
        ArrayList<a> arrayList = this.f1189a;
        this.f1189a = new ArrayList<>();
        for (a aVar : arrayList) {
            aVar.f1192b.execute(aVar.f1191a);
        }
    }
}
