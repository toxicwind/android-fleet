package b.c.a;

/* JADX INFO: loaded from: classes.dex */
public class c extends RuntimeException {
    /* JADX WARN: Illegal instructions before constructor call */
    public c(Class cls) {
        StringBuilder sbA = b.a.a.a.a.a("Class ");
        sbA.append(cls.getCanonicalName());
        sbA.append(" could not be mapped to a JSON object. Perhaps it hasn't been annotated with @JsonObject?");
        super(sbA.toString());
    }
}
