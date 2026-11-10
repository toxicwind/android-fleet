package c.a.b;

import c.a.C0219ea;
import com.google.common.base.Charsets;
import java.nio.charset.Charset;
import okhttp3.internal.http2.Header;

/* JADX INFO: renamed from: c.a.b.rb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0173rb extends AbstractC0087a.c {
    public static final c.a.P.a<Integer> r = new C0169qb();
    public static final C0219ea.e<Integer> s = c.a.P.a(Header.RESPONSE_STATUS_UTF8, r);
    public c.a.za t;
    public C0219ea u;
    public Charset v;
    public boolean w;

    public AbstractC0173rb(int i, Ld ld, Td td) {
        super(i, ld, td);
        this.v = Charsets.UTF_8;
    }

    public static Charset b(C0219ea c0219ea) {
        String str = (String) c0219ea.b(C0149mb.g);
        if (str != null) {
            String[] strArrSplit = str.split("charset=", 2);
            try {
                return Charset.forName(strArrSplit[strArrSplit.length - 1].trim());
            } catch (Exception unused) {
            }
        }
        return Charsets.UTF_8;
    }

    public static void c(C0219ea c0219ea) {
        c0219ea.a(s);
        c0219ea.a(c.a.Q.f843b);
        c0219ea.a(c.a.Q.f842a);
    }

    public final c.a.za d(C0219ea c0219ea) {
        Integer num = (Integer) c0219ea.b(s);
        if (num == null) {
            return c.a.za.k.b("Missing HTTP status code");
        }
        String str = (String) c0219ea.b(C0149mb.g);
        if (C0149mb.b(str)) {
            return null;
        }
        return C0149mb.a(num.intValue()).a("invalid content-type: " + str);
    }
}
