package c.a.b;

import com.google.common.base.Preconditions;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

/* JADX INFO: renamed from: c.a.b.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0176s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f1452a = Logger.getLogger(C0176s.class.getName());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f1453b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final AtomicLong f1454c = new AtomicLong();

    /* JADX INFO: renamed from: c.a.b.s$a */
    public final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final long f1455a;

        public /* synthetic */ a(long j, r rVar) {
            this.f1455a = j;
        }
    }

    public C0176s(String str, long j) {
        Preconditions.checkArgument(j > 0, "value must be positive");
        this.f1453b = str;
        this.f1454c.set(j);
    }

    public a a() {
        return new a(this.f1454c.get(), null);
    }
}
