package c.a.b;

import androidx.core.app.NotificationCompat;
import c.a.AbstractC0230n;
import c.a.C0219ea;
import com.google.common.base.Preconditions;
import com.ju.lib.datareport.dynamic.DynamicDomainHelper;

/* JADX INFO: loaded from: classes2.dex */
public final class Ta extends Ic {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1153b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c.a.za f1154c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final N.a f1155d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final AbstractC0230n[] f1156e;

    public Ta(c.a.za zaVar, N.a aVar, AbstractC0230n[] abstractC0230nArr) {
        Preconditions.checkArgument(!zaVar.c(), "error must not be OK");
        this.f1154c = zaVar;
        this.f1155d = aVar;
        this.f1156e = abstractC0230nArr;
    }

    @Override // c.a.b.Ic, c.a.b.M
    public void a(N n) {
        Preconditions.checkState(!this.f1153b, "already started");
        this.f1153b = true;
        for (AbstractC0230n abstractC0230n : this.f1156e) {
            abstractC0230n.a(this.f1154c);
        }
        n.a(this.f1154c, this.f1155d, new C0219ea());
    }

    @Override // c.a.b.Ic, c.a.b.M
    public void a(C0198wb c0198wb) {
        c0198wb.a(DynamicDomainHelper.DYNAMIC_ERROR, this.f1154c);
        c0198wb.a(NotificationCompat.CATEGORY_PROGRESS, this.f1155d);
    }
}
