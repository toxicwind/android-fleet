package c.a;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: renamed from: c.a.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0240y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Joiner f1928a = Joiner.on(',');

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C0240y f1929b = new C0240y(InterfaceC0231o.b.f1883a, false, new C0240y(new InterfaceC0231o.a(), true, new C0240y()));

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Map<String, a> f1930c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final byte[] f1931d;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: c.a.y$a */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final InterfaceC0239x f1932a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f1933b;

        public a(InterfaceC0239x interfaceC0239x, boolean z) {
            Preconditions.checkNotNull(interfaceC0239x, "decompressor");
            this.f1932a = interfaceC0239x;
            this.f1933b = z;
        }
    }

    public C0240y(InterfaceC0239x interfaceC0239x, boolean z, C0240y c0240y) {
        String strA = interfaceC0239x.a();
        Preconditions.checkArgument(!strA.contains(","), "Comma is currently not allowed in message encoding");
        int size = c0240y.f1930c.size();
        LinkedHashMap linkedHashMap = new LinkedHashMap(c0240y.f1930c.containsKey(interfaceC0239x.a()) ? size : size + 1);
        for (a aVar : c0240y.f1930c.values()) {
            String strA2 = aVar.f1932a.a();
            if (!strA2.equals(strA)) {
                linkedHashMap.put(strA2, new a(aVar.f1932a, aVar.f1933b));
            }
        }
        linkedHashMap.put(strA, new a(interfaceC0239x, z));
        this.f1930c = Collections.unmodifiableMap(linkedHashMap);
        Joiner joiner = f1928a;
        HashSet hashSet = new HashSet(this.f1930c.size());
        for (Map.Entry<String, a> entry : this.f1930c.entrySet()) {
            if (entry.getValue().f1933b) {
                hashSet.add(entry.getKey());
            }
        }
        this.f1931d = joiner.join(Collections.unmodifiableSet(hashSet)).getBytes(Charset.forName("US-ASCII"));
    }

    public C0240y() {
        this.f1930c = new LinkedHashMap(0);
        this.f1931d = new byte[0];
    }
}
