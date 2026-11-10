package c.a.b;

import c.a.AbstractC0225i;
import com.google.common.base.Preconditions;
import java.text.MessageFormat;
import java.util.logging.Level;

/* JADX INFO: loaded from: classes2.dex */
public final class B extends AbstractC0225i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final D f895a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Pd f896b;

    public B(D d2, Pd pd) {
        Preconditions.checkNotNull(d2, "tracer");
        this.f895a = d2;
        Preconditions.checkNotNull(pd, "time");
        this.f896b = pd;
    }

    @Override // c.a.AbstractC0225i
    public void a(AbstractC0225i.a aVar, String str) {
        c.a.J.a aVar2;
        c.a.O o = this.f895a.f929c;
        Level levelA = a(aVar);
        if (D.f927a.isLoggable(levelA)) {
            D.a(o, levelA, str);
        }
        if (!(aVar != AbstractC0225i.a.DEBUG && this.f895a.a()) || aVar == AbstractC0225i.a.DEBUG) {
            return;
        }
        D d2 = this.f895a;
        int iOrdinal = aVar.ordinal();
        if (iOrdinal != 2) {
            aVar2 = iOrdinal != 3 ? c.a.J.a.CT_INFO : c.a.J.a.CT_ERROR;
        } else {
            aVar2 = c.a.J.a.CT_WARNING;
        }
        c.a.J.a aVar3 = aVar2;
        Long lValueOf = Long.valueOf(((Od) this.f896b).a());
        Preconditions.checkNotNull(str, "description");
        Preconditions.checkNotNull(aVar3, "severity");
        Preconditions.checkNotNull(lValueOf, "timestampNanos");
        Preconditions.checkState(true, "at least one of channelRef and subchannelRef must be null");
        d2.b(new c.a.J(str, aVar3, lValueOf.longValue(), null, null, null));
    }

    @Override // c.a.AbstractC0225i
    public void a(AbstractC0225i.a aVar, String str, Object... objArr) {
        c.a.J.a aVar2;
        boolean z = false;
        String str2 = ((aVar != AbstractC0225i.a.DEBUG && this.f895a.a()) || D.f927a.isLoggable(a(aVar))) ? MessageFormat.format(str, objArr) : null;
        c.a.O o = this.f895a.f929c;
        Level levelA = a(aVar);
        if (D.f927a.isLoggable(levelA)) {
            D.a(o, levelA, str2);
        }
        if (aVar != AbstractC0225i.a.DEBUG && this.f895a.a()) {
            z = true;
        }
        if (!z || aVar == AbstractC0225i.a.DEBUG) {
            return;
        }
        D d2 = this.f895a;
        int iOrdinal = aVar.ordinal();
        if (iOrdinal == 2) {
            aVar2 = c.a.J.a.CT_WARNING;
        } else if (iOrdinal != 3) {
            aVar2 = c.a.J.a.CT_INFO;
        } else {
            aVar2 = c.a.J.a.CT_ERROR;
        }
        c.a.J.a aVar3 = aVar2;
        Long lValueOf = Long.valueOf(((Od) this.f896b).a());
        Preconditions.checkNotNull(str2, "description");
        Preconditions.checkNotNull(aVar3, "severity");
        Preconditions.checkNotNull(lValueOf, "timestampNanos");
        Preconditions.checkState(true, "at least one of channelRef and subchannelRef must be null");
        d2.b(new c.a.J(str2, aVar3, lValueOf.longValue(), null, null, null));
    }

    public static Level a(AbstractC0225i.a aVar) {
        int iOrdinal = aVar.ordinal();
        if (iOrdinal == 2) {
            return Level.FINER;
        }
        if (iOrdinal != 3) {
            return Level.FINEST;
        }
        return Level.FINE;
    }
}
