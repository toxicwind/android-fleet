package j$.util;

import java.security.AccessController;
import java.security.PrivilegedAction;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes3.dex */
public abstract class Tripwire {
    static final boolean ENABLED = ((Boolean) AccessController.doPrivileged(new PrivilegedAction() { // from class: j$.util.Tripwire.0
        @Override // java.security.PrivilegedAction
        public final Object run() {
            return Tripwire.lambda$static$0();
        }
    })).booleanValue();

    static /* synthetic */ Boolean lambda$static$0() {
        return Boolean.valueOf(Boolean.getBoolean("org.openjdk.java.util.stream.tripwire"));
    }

    static void trip(Class cls, String str) {
        throw new UnsupportedOperationException(cls + " tripwire tripped but logging not supported: " + str);
    }
}
