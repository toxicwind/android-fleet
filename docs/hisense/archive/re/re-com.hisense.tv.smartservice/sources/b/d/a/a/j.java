package b.d.a.a;

import com.google.firebase.installations.local.IidStore;

/* JADX INFO: loaded from: classes.dex */
public enum j {
    NOT_AVAILABLE(null, -1),
    START_OBJECT(IidStore.JSON_ENCODED_PREFIX, 1),
    END_OBJECT("}", 2),
    START_ARRAY("[", 3),
    END_ARRAY("]", 4),
    FIELD_NAME(null, 5),
    VALUE_EMBEDDED_OBJECT(null, 12),
    VALUE_STRING(null, 6),
    VALUE_NUMBER_INT(null, 7),
    VALUE_NUMBER_FLOAT(null, 8),
    VALUE_TRUE("true", 9),
    VALUE_FALSE("false", 10),
    VALUE_NULL("null", 11);

    public final String o;
    public final char[] p;
    public final byte[] q;
    public final int r;
    public final boolean s;
    public final boolean t;

    j(String str, int i) {
        if (str == null) {
            this.o = null;
            this.p = null;
            this.q = null;
        } else {
            this.o = str;
            this.p = str.toCharArray();
            int length = this.p.length;
            this.q = new byte[length];
            for (int i2 = 0; i2 < length; i2++) {
                this.q[i2] = (byte) this.p[i2];
            }
        }
        this.r = i;
        if (i != 10) {
        }
        if (i != 7) {
        }
        this.s = i == 1 || i == 3;
        this.t = i == 2 || i == 4;
        if (this.s || this.t || i != 5) {
        }
    }

    public final String a() {
        return this.o;
    }
}
