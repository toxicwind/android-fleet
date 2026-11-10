package b.c.a.a.a;

import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class h extends b.c.a.a<Object> {
    @Override // b.c.a.a
    public Object parse(b.d.a.a.g gVar) {
        int iOrdinal = ((b.d.a.a.a.c) gVar).f70b.ordinal();
        if (iOrdinal == 1) {
            return b.c.a.b.b(Map.class).parse(gVar);
        }
        if (iOrdinal == 3) {
            return b.c.a.b.b(List.class).parse(gVar);
        }
        switch (iOrdinal) {
            case 6:
                return b.c.a.b.b(Map.class).parse(gVar);
            case 7:
                return gVar.s();
            case 8:
                return Long.valueOf(gVar.r());
            case 9:
                return Double.valueOf(gVar.q());
            case 10:
                return true;
            case 11:
                return false;
            case 12:
                return null;
            default:
                StringBuilder sbA = b.a.a.a.a.a("Invalid json token encountered: ");
                sbA.append(((b.d.a.a.a.c) gVar).f70b);
                throw new RuntimeException(sbA.toString());
        }
    }

    @Override // b.c.a.a
    public void parseField(Object obj, String str, b.d.a.a.g gVar) {
    }

    @Override // b.c.a.a
    public void serialize(Object obj, b.d.a.a.d dVar, boolean z) {
        if (obj == null) {
            dVar.s();
            return;
        }
        if (obj instanceof String) {
            dVar.c((String) obj);
            return;
        }
        if (obj instanceof Integer) {
            dVar.b(((Integer) obj).intValue());
            return;
        }
        if (obj instanceof Long) {
            dVar.g(((Long) obj).longValue());
            return;
        }
        if (obj instanceof Float) {
            dVar.a(((Float) obj).floatValue());
            return;
        }
        if (obj instanceof Double) {
            dVar.a(((Double) obj).doubleValue());
            return;
        }
        if (obj instanceof Boolean) {
            dVar.a(((Boolean) obj).booleanValue());
            return;
        }
        if (obj instanceof List) {
            b.c.a.b.b(List.class).serialize((List) obj, dVar, z);
            return;
        }
        if (obj instanceof Map) {
            b.c.a.b.b(Map.class).serialize((Map) obj, dVar, z);
            return;
        }
        b.c.a.a aVarB = b.c.a.b.b(obj.getClass());
        if (aVarB != null) {
            if (z) {
                dVar.u();
            }
            aVarB.serialize(obj, dVar, false);
            if (z) {
                dVar.r();
            }
        }
    }
}
