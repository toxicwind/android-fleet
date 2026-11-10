package c.a;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: renamed from: c.a.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0236u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f1911a = Logger.getLogger(C0236u.class.getName());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C0236u f1912b = new C0236u();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1913c = 0;

    /* JADX INFO: renamed from: c.a.u$a */
    public interface a {
    }

    /* JADX INFO: renamed from: c.a.u$b */
    private static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f1914a;

        static {
            c fa;
            AtomicReference atomicReference = new AtomicReference();
            try {
                fa = (c) Class.forName("io.grpc.override.ContextStorageOverride").asSubclass(c.class).getConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (ClassNotFoundException e2) {
                atomicReference.set(e2);
                fa = new Fa();
            } catch (Exception e3) {
                throw new RuntimeException("Storage override failed to initialize", e3);
            }
            f1914a = fa;
            Throwable th = (Throwable) atomicReference.get();
            if (th != null) {
                C0236u.f1911a.log(Level.FINE, "Storage override doesn't exist. Using default", th);
            }
        }
    }

    /* JADX INFO: renamed from: c.a.u$c */
    public static abstract class c {
        public abstract C0236u a();
    }

    public C0236u() {
        if (this.f1913c == 1000) {
            f1911a.log(Level.SEVERE, "Context ancestry chain length is abnormally long. This suggests an error in application code. Length exceeded: 1000", (Throwable) new Exception());
        }
    }

    public static C0236u c() {
        C0236u c0236uA = b.f1914a.a();
        return c0236uA == null ? f1912b : c0236uA;
    }

    public C0236u a() {
        C0236u c0236uA = ((Fa) b.f1914a).a();
        Fa.f804b.set(this);
        return c0236uA == null ? f1912b : c0236uA;
    }

    public void a(a aVar) {
    }

    public Throwable b() {
        return null;
    }

    public C0238w d() {
        return null;
    }

    public boolean e() {
        return false;
    }

    public void a(C0236u c0236u) {
        a(c0236u, "toAttach");
        if (((Fa) b.f1914a).a() != this) {
            Fa.f803a.log(Level.SEVERE, "Context was not attached when detaching", new Throwable().fillInStackTrace());
        }
        if (c0236u != f1912b) {
            Fa.f804b.set(c0236u);
        } else {
            Fa.f804b.set(null);
        }
    }

    public void a(a aVar, Executor executor) {
        a(aVar, "cancellationListener");
        a(executor, "executor");
    }

    public static <T> T a(T t, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }
}
