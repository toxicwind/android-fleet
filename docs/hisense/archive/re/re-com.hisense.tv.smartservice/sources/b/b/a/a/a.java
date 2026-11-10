package b.b.a.a;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public class a implements b.b.a.g<Bundle> {
    @Override // b.b.a.g
    public Class<Bundle> a() {
        return Bundle.class;
    }

    @Override // b.b.a.g
    public String a(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(bundle.getClass().getName() + " [" + b.b.a.g.f39a);
        for (String str : bundle.keySet()) {
            StringBuilder sbA = b.a.a.a.a.a("'%s' => %s ");
            sbA.append(b.b.a.g.f39a);
            sb.append(String.format(sbA.toString(), str, b.b.a.c.a.a(bundle.get(str))));
        }
        sb.append("]");
        return sb.toString();
    }
}
