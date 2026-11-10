package c.a.b;

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import java.net.URI;

/* JADX INFO: loaded from: classes2.dex */
public final class Ra extends c.a.ja {
    @Override // c.a.ia.c
    public c.a.ia a(URI uri, c.a.ia.a aVar) {
        if (!"dns".equals(uri.getScheme())) {
            return null;
        }
        String path = uri.getPath();
        Preconditions.checkNotNull(path, "targetPath");
        Preconditions.checkArgument(path.startsWith("/"), "the path component (%s) of the target (%s) must start with '/'", path, uri);
        return new Pa(uri.getAuthority(), path.substring(1), aVar, C0149mb.n, new Stopwatch(), b.b.a.c.a.a(Ra.class.getClassLoader()));
    }

    @Override // c.a.ia.c
    public String a() {
        return "dns";
    }

    @Override // c.a.ja
    public boolean b() {
        return true;
    }

    @Override // c.a.ja
    public int c() {
        return 5;
    }
}
