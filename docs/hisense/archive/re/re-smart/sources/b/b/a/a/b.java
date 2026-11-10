package b.b.a.a;

import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public class b implements b.b.a.g<Collection> {
    @Override // b.b.a.g
    public String a(Collection collection) {
        String string;
        Collection collection2 = collection;
        String name = collection2.getClass().getName();
        StringBuilder sbA = b.a.a.a.a.a("%s size = %d [");
        sbA.append(b.b.a.g.f39a);
        String string2 = String.format(sbA.toString(), name, Integer.valueOf(collection2.size()));
        if (!collection2.isEmpty()) {
            int i = 0;
            for (Object obj : collection2) {
                StringBuilder sbA2 = b.a.a.a.a.a(string2);
                Object[] objArr = new Object[3];
                objArr[0] = Integer.valueOf(i);
                objArr[1] = b.b.a.c.a.a(obj);
                int i2 = i + 1;
                if (i < collection2.size() - 1) {
                    StringBuilder sbA3 = b.a.a.a.a.a(",");
                    sbA3.append(b.b.a.g.f39a);
                    string = sbA3.toString();
                } else {
                    string = b.b.a.g.f39a;
                }
                objArr[2] = string;
                sbA2.append(String.format("[%d]:%s%s", objArr));
                string2 = sbA2.toString();
                i = i2;
            }
        }
        return b.a.a.a.a.a(string2, "]");
    }

    @Override // b.b.a.g
    public Class<Collection> a() {
        return Collection.class;
    }
}
