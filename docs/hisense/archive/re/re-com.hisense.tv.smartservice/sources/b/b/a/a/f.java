package b.b.a.a;

import java.lang.ref.Reference;

/* JADX INFO: loaded from: classes.dex */
public class f implements b.b.a.g<Reference> {
    @Override // b.b.a.g
    public String a(Reference reference) {
        Reference reference2 = reference;
        Object obj = reference2.get();
        StringBuilder sb = new StringBuilder(reference2.getClass().getSimpleName() + "<" + obj.getClass().getSimpleName() + "> {");
        StringBuilder sbA = b.a.a.a.a.a("→");
        sbA.append(b.b.a.c.a.a(obj));
        sb.append(sbA.toString());
        return sb.toString() + "}";
    }

    @Override // b.b.a.g
    public Class<Reference> a() {
        return Reference.class;
    }
}
