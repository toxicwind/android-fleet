package io.ktor.http.auth;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: DigestAlgorithm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Lio/ktor/http/auth/DigestQop;", "", "", ES6Iterator.VALUE_PROPERTY, "<init>", "(Ljava/lang/String;)V", "Ljava/lang/String;", "getValue", "()Ljava/lang/String;", "Companion", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DigestQop {
    private static final DigestQop AUTH;
    private static final DigestQop AUTH_INT;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final List<DigestQop> DEFAULT_QOPS;
    private final String value;

    /* JADX INFO: compiled from: DigestAlgorithm.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u001d\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lio/ktor/http/auth/DigestQop$Companion;", "", "<init>", "()V", "", ES6Iterator.VALUE_PROPERTY, "Lio/ktor/http/auth/DigestQop;", TypedValues.TransitionType.S_FROM, "(Ljava/lang/String;)Lio/ktor/http/auth/DigestQop;", "AUTH", "Lio/ktor/http/auth/DigestQop;", "getAUTH", "()Lio/ktor/http/auth/DigestQop;", "AUTH_INT", "getAUTH_INT", "", "DEFAULT_QOPS", "Ljava/util/List;", "getDEFAULT_QOPS", "()Ljava/util/List;", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DigestQop getAUTH() {
            return DigestQop.AUTH;
        }

        public final DigestQop getAUTH_INT() {
            return DigestQop.AUTH_INT;
        }

        public final List<DigestQop> getDEFAULT_QOPS() {
            return DigestQop.DEFAULT_QOPS;
        }

        public final DigestQop from(String value) {
            Object next;
            Intrinsics.checkNotNullParameter(value, "value");
            Iterator<T> it = getDEFAULT_QOPS().iterator();
            while (it.hasNext()) {
                next = it.next();
                if (StringsKt.equals(((DigestQop) next).getValue(), value, true)) {
                    return (DigestQop) next;
                }
            }
            next = null;
            return (DigestQop) next;
        }
    }

    public DigestQop(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.value = value;
    }

    public final String getValue() {
        return this.value;
    }

    static {
        DigestQop digestQop = new DigestQop("auth");
        AUTH = digestQop;
        DigestQop digestQop2 = new DigestQop("auth-int");
        AUTH_INT = digestQop2;
        DEFAULT_QOPS = CollectionsKt.listOf((Object[]) new DigestQop[]{digestQop, digestQop2});
    }
}
