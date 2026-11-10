package c.a.b;

import com.google.common.base.Preconditions;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public final class Sa implements InterfaceC0196w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Random f1139a = new Random();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f1140b = TimeUnit.SECONDS.toNanos(1);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f1141c = TimeUnit.MINUTES.toNanos(2);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public double f1142d = 1.6d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public double f1143e = 0.2d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f1144f = this.f1140b;

    public static final class a implements InterfaceC0196w.a {
        public InterfaceC0196w a() {
            return new Sa();
        }
    }

    public long a() {
        long j = this.f1144f;
        double d2 = j;
        this.f1144f = Math.min((long) (this.f1142d * d2), this.f1141c);
        double d3 = this.f1143e;
        double d4 = (-d3) * d2;
        double d5 = d3 * d2;
        Preconditions.checkArgument(d5 >= d4);
        return j + ((long) ((this.f1139a.nextDouble() * (d5 - d4)) + d4));
    }
}
