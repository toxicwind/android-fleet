package a.b.a.a.c;

import com.ju.lib.datacommunication.network.http.core.HttpException;
import com.ju.lib.datacommunication.network.http.core.s;
import com.ju.lib.datacommunication.network.http.e;
import com.ju.lib.datacommunication.network.http.h.d;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private HashMap f2a;

    protected static String a(String str, String str2, boolean z) {
        StringBuilder sb;
        if (z) {
            sb = new StringBuilder("https://" + str);
        } else {
            sb = new StringBuilder("http://" + str);
        }
        sb.append("/").append(str2);
        return sb.toString();
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0044 A[Catch: HttpException -> 0x007c, TryCatch #0 {HttpException -> 0x007c, blocks: (B:4:0x000a, B:6:0x0010, B:7:0x0029, B:10:0x0044, B:12:0x004a, B:13:0x0063, B:8:0x002e), top: B:19:0x000a }] */
    /* JADX WARN: Code duplicated, block: B:12:0x004a A[Catch: HttpException -> 0x007c, TryCatch #0 {HttpException -> 0x007c, blocks: (B:4:0x000a, B:6:0x0010, B:7:0x0029, B:10:0x0044, B:12:0x004a, B:13:0x0063, B:8:0x002e), top: B:19:0x000a }] */
    /* JADX WARN: Code duplicated, block: B:13:0x0063 A[Catch: HttpException -> 0x007c, TRY_LEAVE, TryCatch #0 {HttpException -> 0x007c, blocks: (B:4:0x000a, B:6:0x0010, B:7:0x0029, B:10:0x0044, B:12:0x004a, B:13:0x0063, B:8:0x002e), top: B:19:0x000a }] */
    /* JADX WARN: Code duplicated, block: B:8:0x002e A[Catch: HttpException -> 0x007c, TryCatch #0 {HttpException -> 0x007c, blocks: (B:4:0x000a, B:6:0x0010, B:7:0x0029, B:10:0x0044, B:12:0x004a, B:13:0x0063, B:8:0x002e), top: B:19:0x000a }] */
    /* JADX WARN: Instruction removed from duplicated block: B:12:0x004a, please report this as an issue */
    protected static String d(e eVar, String str, Map map, com.ju.lib.datacommunication.network.http.core.signature.b bVar, String str2, String str3, Map map2) {
        d dVar;
        s sVarB;
        if (map2 != null) {
            try {
                if (map2.isEmpty()) {
                    d dVar2 = (d) eVar.c().g(str);
                    dVar2.i(map);
                    dVar = (d) dVar2.f(bVar);
                } else {
                    d dVar3 = (d) eVar.c().g(str);
                    dVar3.i(map);
                    dVar = (d) ((d) dVar3.d(map2)).f(bVar);
                }
                sVarB = dVar.b();
                if (sVarB != null) {
                    if (sVarB.d()) {
                        String strA = sVarB.a().a();
                        a.b.a.a.d.c.a("HttpHandler", "result=" + strA);
                        str2 = strA;
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append(sVarB.b());
                        str2 = str2.replace(str3, sb.toString().replace("\"", ""));
                    }
                }
            } catch (HttpException e) {
                e.printStackTrace();
                str2 = str2.replace(str3, a.b.a.a.d.c.h(e + ":" + e.getMessage() + ":" + e.getCause()).replace("\"", ""));
            }
        } else {
            d dVar4 = (d) eVar.c().g(str);
            dVar4.i(map);
            dVar = (d) dVar4.f(bVar);
            sVarB = dVar.b();
            if (sVarB != null) {
                if (sVarB.d()) {
                    String strA2 = sVarB.a().a();
                    a.b.a.a.d.c.a("HttpHandler", "result=" + strA2);
                    str2 = strA2;
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(sVarB.b());
                    str2 = str2.replace(str3, sb2.toString().replace("\"", ""));
                }
            }
        }
        a.b.a.a.d.c.a("HttpHandler", "result=" + str2);
        return str2;
    }

    protected final void b(HashMap map) {
        HashMap map2 = this.f2a;
        if (map2 == null || map2.isEmpty()) {
            return;
        }
        map.putAll(this.f2a);
    }

    protected final String c(e eVar, String str, Map map, com.ju.lib.datacommunication.network.http.core.signature.b bVar, String str2, String str3) {
        return d(eVar, str, map, bVar, str2, str3, null);
    }
}
