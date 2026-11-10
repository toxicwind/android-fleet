package b.b.a.a;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class d implements b.b.a.g<Map> {
    @Override // b.b.a.g
    public String a(Map map) {
        Map map2 = map;
        String string = map2.getClass().getName() + " [" + b.b.a.g.f39a;
        for (Object obj : map2.keySet()) {
            StringBuilder sbA = b.a.a.a.a.a("%s -> %s");
            sbA.append(b.b.a.g.f39a);
            String string2 = sbA.toString();
            Object objA = map2.get(obj);
            if (objA != null) {
                if (objA instanceof String) {
                    objA = b.a.a.a.a.a("\"", objA, "\"");
                } else if (objA instanceof Character) {
                    objA = b.a.a.a.a.a("'", objA, "'");
                }
            }
            StringBuilder sbA2 = b.a.a.a.a.a(string);
            sbA2.append(String.format(string2, b.b.a.c.a.a(obj), b.b.a.c.a.a(objA)));
            string = sbA2.toString();
        }
        return b.a.a.a.a.a(string, "]");
    }

    @Override // b.b.a.g
    public Class<Map> a() {
        return Map.class;
    }
}
