package c.a.a;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import c.a.AbstractC0226j;
import c.a.C;
import c.a.C0220f;
import c.a.C0223ga;
import c.a.EnumC0234s;
import c.a.X;
import c.a.Y;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public final class d extends C<d> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Y<?> f866a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f867b;

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public static final class a extends X {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final X f868a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Context f869b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final ConnectivityManager f870c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Object f871d = new Object();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Runnable f872e;

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: c.a.a.d$a$a, reason: collision with other inner class name */
        @TargetApi(24)
        class C0006a extends ConnectivityManager.NetworkCallback {
            public /* synthetic */ C0006a(c.a.a.a aVar) {
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onAvailable(Network network) {
                a.this.f868a.c();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        class b extends BroadcastReceiver {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public boolean f874a = false;

            public /* synthetic */ b(c.a.a.a aVar) {
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                boolean z = this.f874a;
                this.f874a = activeNetworkInfo != null && activeNetworkInfo.isConnected();
                if (!this.f874a || z) {
                    return;
                }
                a.this.f868a.c();
            }
        }

        @VisibleForTesting
        public a(X x, Context context) {
            this.f868a = x;
            this.f869b = context;
            if (context == null) {
                this.f870c = null;
                return;
            }
            this.f870c = (ConnectivityManager) context.getSystemService("connectivity");
            try {
                f();
            } catch (SecurityException e2) {
                Log.w("AndroidChannelBuilder", "Failed to configure network monitoring. Does app have ACCESS_NETWORK_STATE permission?", e2);
            }
        }

        @Override // c.a.X
        public boolean a(long j, TimeUnit timeUnit) {
            return this.f868a.a(j, timeUnit);
        }

        @Override // c.a.AbstractC0222g
        public String b() {
            return this.f868a.b();
        }

        @Override // c.a.X
        public void c() {
            this.f868a.c();
        }

        @Override // c.a.X
        public X d() {
            g();
            return this.f868a.d();
        }

        @Override // c.a.X
        public X e() {
            g();
            return this.f868a.e();
        }

        public final void f() {
            int i = Build.VERSION.SDK_INT;
            c.a.a.a aVar = null;
            if (this.f870c != null) {
                C0006a c0006a = new C0006a(aVar);
                this.f870c.registerDefaultNetworkCallback(c0006a);
                this.f872e = new c.a.a.b(this, c0006a);
            } else {
                b bVar = new b(aVar);
                this.f869b.registerReceiver(bVar, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                this.f872e = new c(this, bVar);
            }
        }

        public final void g() {
            synchronized (this.f871d) {
                if (this.f872e != null) {
                    this.f872e.run();
                    this.f872e = null;
                }
            }
        }

        @Override // c.a.AbstractC0222g
        public <RequestT, ResponseT> AbstractC0226j<RequestT, ResponseT> a(C0223ga<RequestT, ResponseT> c0223ga, C0220f c0220f) {
            return this.f868a.a(c0223ga, c0220f);
        }

        @Override // c.a.X
        public EnumC0234s a(boolean z) {
            return this.f868a.a(z);
        }

        @Override // c.a.X
        public void a(EnumC0234s enumC0234s, Runnable runnable) {
            this.f868a.a(enumC0234s, runnable);
        }
    }

    static {
        try {
            Class.forName("c.a.c.h");
        } catch (ClassNotFoundException unused) {
        }
    }

    public d(Y<?> y) {
        Preconditions.checkNotNull(y, "delegateBuilder");
        this.f866a = y;
    }

    @Override // c.a.Y
    public X a() {
        return new a(this.f866a.a(), this.f867b);
    }
}
