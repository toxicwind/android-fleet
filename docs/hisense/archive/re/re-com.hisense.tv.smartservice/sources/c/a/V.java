package c.a;

import com.google.common.base.MoreObjects;
import com.ju.lib.datareport.StrategyBean;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public abstract class V extends U.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ia.b f856a = new ia.b(new a());

    private static final class a {
        public String toString() {
            return "service config is unused";
        }
    }

    public ia.b a(Map<String, ?> map) {
        return f856a;
    }

    public abstract String a();

    public abstract int b();

    public abstract boolean c();

    public final boolean equals(Object obj) {
        return this == obj;
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public final String toString() {
        return MoreObjects.toStringHelper(this).add(StrategyBean.Columns.POLICY, a()).add("priority", b()).add("available", c()).toString();
    }
}
