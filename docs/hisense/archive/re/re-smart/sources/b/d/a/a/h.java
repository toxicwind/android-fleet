package b.d.a.a;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class h extends IOException {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public e f219a;

    public h(String str, e eVar) {
        super(str);
        this.f219a = eVar;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        String message = super.getMessage();
        if (message == null) {
            message = "N/A";
        }
        e eVar = this.f219a;
        if (eVar == null) {
            return message;
        }
        StringBuilder sb = new StringBuilder(100);
        sb.append(message);
        if (eVar != null) {
            sb.append('\n');
            sb.append(" at ");
            StringBuilder sb2 = new StringBuilder(80);
            sb2.append("[Source: ");
            Object obj = eVar.f193e;
            if (obj == null) {
                sb2.append("UNKNOWN");
            } else {
                sb2.append(obj.toString());
            }
            sb2.append("; line: ");
            sb2.append(eVar.f191c);
            sb2.append(", column: ");
            sb2.append(eVar.f192d);
            sb2.append(']');
            sb.append(sb2.toString());
        }
        return sb.toString();
    }

    @Override // java.lang.Throwable
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append(": ");
        String message = super.getMessage();
        if (message == null) {
            message = "N/A";
        }
        e eVar = this.f219a;
        if (eVar != null) {
            StringBuilder sb2 = new StringBuilder(100);
            sb2.append(message);
            sb2.append('\n');
            sb2.append(" at ");
            StringBuilder sb3 = new StringBuilder(80);
            sb3.append("[Source: ");
            Object obj = eVar.f193e;
            if (obj == null) {
                sb3.append("UNKNOWN");
            } else {
                sb3.append(obj.toString());
            }
            sb3.append("; line: ");
            sb3.append(eVar.f191c);
            sb3.append(", column: ");
            sb3.append(eVar.f192d);
            sb3.append(']');
            sb2.append(sb3.toString());
            message = sb2.toString();
        }
        sb.append(message);
        return sb.toString();
    }

    public h(String str, e eVar, Throwable th) {
        super(str);
        if (th != null) {
            initCause(th);
        }
        this.f219a = eVar;
    }
}
