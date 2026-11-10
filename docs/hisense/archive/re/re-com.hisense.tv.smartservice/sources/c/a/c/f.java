package c.a.c;

import c.a.C0219ea;
import c.a.P;
import c.a.b.C0149mb;
import c.a.b.Qd;
import com.google.common.base.Preconditions;
import com.google.firebase.installations.Utils;
import com.google.firebase.storage.network.NetworkRequest;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c.a.c.a.a.d f1689a = new c.a.c.a.a.d(c.a.c.a.a.d.f1569d, "https");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c.a.c.a.a.d f1690b = new c.a.c.a.a.d(c.a.c.a.a.d.f1569d, "http");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c.a.c.a.a.d f1691c = new c.a.c.a.a.d(c.a.c.a.a.d.f1567b, NetworkRequest.POST);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final c.a.c.a.a.d f1692d = new c.a.c.a.a.d(c.a.c.a.a.d.f1567b, NetworkRequest.GET);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final c.a.c.a.a.d f1693e = new c.a.c.a.a.d(C0149mb.g.f1805c, "application/grpc");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final c.a.c.a.a.d f1694f = new c.a.c.a.a.d("te", "trailers");

    public static List<c.a.c.a.a.d> a(C0219ea c0219ea, String str, String str2, String str3, boolean z, boolean z2) {
        Preconditions.checkNotNull(c0219ea, "headers");
        Preconditions.checkNotNull(str, "defaultPath");
        Preconditions.checkNotNull(str2, "authority");
        c0219ea.a(C0149mb.g);
        c0219ea.a(C0149mb.h);
        c0219ea.a(C0149mb.i);
        ArrayList arrayList = new ArrayList(P.a(c0219ea) + 7);
        if (z2) {
            arrayList.add(f1690b);
        } else {
            arrayList.add(f1689a);
        }
        if (z) {
            arrayList.add(f1692d);
        } else {
            arrayList.add(f1691c);
        }
        arrayList.add(new c.a.c.a.a.d(c.a.c.a.a.d.f1570e, str2));
        arrayList.add(new c.a.c.a.a.d(c.a.c.a.a.d.f1568c, str));
        arrayList.add(new c.a.c.a.a.d(C0149mb.i.f1805c, str3));
        arrayList.add(f1693e);
        arrayList.add(f1694f);
        byte[][] bArrA = Qd.a(c0219ea);
        for (int i = 0; i < bArrA.length; i += 2) {
            d.j jVarA = d.j.a(bArrA[i]);
            String strJ = jVarA.j();
            if ((strJ.startsWith(Utils.APP_ID_IDENTIFICATION_SUBSTRING) || C0149mb.g.f1805c.equalsIgnoreCase(strJ) || C0149mb.i.f1805c.equalsIgnoreCase(strJ)) ? false : true) {
                arrayList.add(new c.a.c.a.a.d(jVarA, d.j.a(bArrA[i + 1])));
            }
        }
        return arrayList;
    }
}
