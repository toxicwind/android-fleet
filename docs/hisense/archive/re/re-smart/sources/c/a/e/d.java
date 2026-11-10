package c.a.e;

import c.a.AbstractC0218e;
import c.a.AbstractC0222g;
import c.a.C0220f;
import c.a.C0229m;
import c.a.C0238w;
import c.a.InterfaceC0227k;
import c.a.e.d;
import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public abstract class d<S extends d<S>> {
    public final C0220f callOptions;
    public final AbstractC0222g channel;

    public interface a<T extends d<T>> {
        T newStub(AbstractC0222g abstractC0222g, C0220f c0220f);
    }

    public d(AbstractC0222g abstractC0222g) {
        this(abstractC0222g, C0220f.f1812a);
    }

    public static <T extends d<T>> T newStub(a<T> aVar, AbstractC0222g abstractC0222g) {
        return (T) aVar.newStub(abstractC0222g, C0220f.f1812a);
    }

    public abstract S build(AbstractC0222g abstractC0222g, C0220f c0220f);

    public final C0220f getCallOptions() {
        return this.callOptions;
    }

    public final AbstractC0222g getChannel() {
        return this.channel;
    }

    public final S withCallCredentials(AbstractC0218e abstractC0218e) {
        return (S) build(this.channel, this.callOptions.a(abstractC0218e));
    }

    @Deprecated
    public final S withChannel(AbstractC0222g abstractC0222g) {
        return (S) build(abstractC0222g, this.callOptions);
    }

    public final S withCompression(String str) {
        return (S) build(this.channel, this.callOptions.a(str));
    }

    public final S withDeadline(C0238w c0238w) {
        return (S) build(this.channel, this.callOptions.a(c0238w));
    }

    public final S withDeadlineAfter(long j, TimeUnit timeUnit) {
        return (S) build(this.channel, this.callOptions.a(j, timeUnit));
    }

    public final S withExecutor(Executor executor) {
        return (S) build(this.channel, this.callOptions.a(executor));
    }

    public final S withInterceptors(InterfaceC0227k... interfaceC0227kArr) {
        return (S) build(C0229m.a(this.channel, Arrays.asList(interfaceC0227kArr)), this.callOptions);
    }

    public final S withMaxInboundMessageSize(int i) {
        return (S) build(this.channel, this.callOptions.a(i));
    }

    public final S withMaxOutboundMessageSize(int i) {
        return (S) build(this.channel, this.callOptions.b(i));
    }

    public final <T> S withOption(C0220f.a<T> aVar, T t) {
        return (S) build(this.channel, this.callOptions.a(aVar, t));
    }

    public final S withWaitForReady() {
        return (S) build(this.channel, this.callOptions.b());
    }

    public d(AbstractC0222g abstractC0222g, C0220f c0220f) {
        Preconditions.checkNotNull(abstractC0222g, "channel");
        this.channel = abstractC0222g;
        Preconditions.checkNotNull(c0220f, "callOptions");
        this.callOptions = c0220f;
    }

    public static <T extends d<T>> T newStub(a<T> aVar, AbstractC0222g abstractC0222g, C0220f c0220f) {
        return (T) aVar.newStub(abstractC0222g, c0220f);
    }
}
