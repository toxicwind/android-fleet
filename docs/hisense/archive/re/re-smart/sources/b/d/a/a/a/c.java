package b.d.a.a.a;

import b.d.a.a.f;
import b.d.a.a.g;
import b.d.a.a.j;

/* JADX INFO: loaded from: classes.dex */
public abstract class c extends g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public j f70b;

    public void a(int i, String str) throws f {
        if (i < 0) {
            x();
            throw null;
        }
        StringBuilder sbA = b.a.a.a.a.a("Unexpected character (");
        sbA.append(b(i));
        sbA.append(")");
        String string = sbA.toString();
        if (str != null) {
            string = b.a.a.a.a.a(string, ": ", str);
        }
        throw b(string);
    }

    public void b(int i, String str) throws f {
        if (!a(g.a.ALLOW_UNQUOTED_CONTROL_CHARS) || i > 32) {
            StringBuilder sbA = b.a.a.a.a.a("Illegal unquoted character (");
            sbA.append(b((char) i));
            sbA.append("): has to be escaped using backslash to be included in ");
            sbA.append(str);
            throw b(sbA.toString());
        }
    }

    public void c(String str) throws f {
        throw b("Unexpected end-of-input" + str);
    }

    @Override // b.d.a.a.g
    public g v() {
        j jVar = this.f70b;
        if (jVar != j.START_OBJECT && jVar != j.START_ARRAY) {
            return this;
        }
        int i = 1;
        while (true) {
            j jVarU = u();
            if (jVarU == null) {
                w();
                return this;
            }
            if (jVarU.s) {
                i++;
            } else if (jVarU.t && (i = i - 1) == 0) {
                return this;
            }
        }
    }

    public abstract void w();

    public void x() throws f {
        StringBuilder sbA = b.a.a.a.a.a(" in ");
        sbA.append(this.f70b);
        c(sbA.toString());
        throw null;
    }

    public void y() throws f {
        c(" in a value");
        throw null;
    }

    public void c(int i) throws f {
        StringBuilder sbA = b.a.a.a.a.a("Illegal character (");
        sbA.append(b((char) i));
        sbA.append("): only regular white space (\\r, \\n, \\t) is allowed between tokens");
        throw b(sbA.toString());
    }

    public static final String b(int i) {
        char c2 = (char) i;
        if (Character.isISOControl(c2)) {
            return b.a.a.a.a.g("(CTRL-CHAR, code ", i, ")");
        }
        if (i > 255) {
            return "'" + c2 + "' (code " + i + " / 0x" + Integer.toHexString(i) + ")";
        }
        return "'" + c2 + "' (code " + i + ")";
    }

    public char a(char c2) throws f {
        if (a(g.a.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER)) {
            return c2;
        }
        if (c2 == '\'' && a(g.a.ALLOW_SINGLE_QUOTES)) {
            return c2;
        }
        StringBuilder sbA = b.a.a.a.a.a("Unrecognized character escape ");
        sbA.append(b(c2));
        throw b(sbA.toString());
    }

    public final void a(String str, Throwable th) throws f {
        throw new f(str, p(), th);
    }
}
