package io.ktor.http.auth;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: DigestAlgorithm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\t\u001a\u0004\b\f\u0010\u000bR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\r\u001a\u0004\b\u0006\u0010\u000e¨\u0006\u0010"}, d2 = {"Lio/ktor/http/auth/DigestAlgorithm;", "", "", "name", "hashName", "", "isSession", "<init>", "(Ljava/lang/String;Ljava/lang/String;Z)V", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "getHashName", "Z", "()Z", "Companion", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DigestAlgorithm {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final List<DigestAlgorithm> DEFAULT_ALGORITHMS;
    private static final DigestAlgorithm MD5;
    private static final DigestAlgorithm MD5_SESS;
    private static final DigestAlgorithm SHA_256;
    private static final DigestAlgorithm SHA_256_SESS;
    private static final DigestAlgorithm SHA_512_256;
    private static final DigestAlgorithm SHA_512_256_SESS;
    private final String hashName;
    private final boolean isSession;
    private final String name;

    public DigestAlgorithm(String name, String hashName, boolean z) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(hashName, "hashName");
        this.name = name;
        this.hashName = hashName;
        this.isSession = z;
    }

    public final String getName() {
        return this.name;
    }

    public final String getHashName() {
        return this.hashName;
    }

    /* JADX INFO: renamed from: isSession, reason: from getter */
    public final boolean getIsSession() {
        return this.isSession;
    }

    /* JADX INFO: compiled from: DigestAlgorithm.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010 \n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bR \u0010\t\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\t\u0010\n\u0012\u0004\b\r\u0010\u0003\u001a\u0004\b\u000b\u0010\fR \u0010\u000e\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u000e\u0010\n\u0012\u0004\b\u0010\u0010\u0003\u001a\u0004\b\u000f\u0010\fR\u0017\u0010\u0011\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0011\u0010\n\u001a\u0004\b\u0012\u0010\fR\u0017\u0010\u0013\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0013\u0010\n\u001a\u0004\b\u0014\u0010\fR\u0017\u0010\u0015\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0015\u0010\n\u001a\u0004\b\u0016\u0010\fR\u0017\u0010\u0017\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0017\u0010\n\u001a\u0004\b\u0018\u0010\fR#\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u00198\u0006¢\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u0012\u0004\b\u001e\u0010\u0003\u001a\u0004\b\u001c\u0010\u001d¨\u0006\u001f"}, d2 = {"Lio/ktor/http/auth/DigestAlgorithm$Companion;", "", "<init>", "()V", "", "name", "Lio/ktor/http/auth/DigestAlgorithm;", TypedValues.TransitionType.S_FROM, "(Ljava/lang/String;)Lio/ktor/http/auth/DigestAlgorithm;", "MD5", "Lio/ktor/http/auth/DigestAlgorithm;", "getMD5", "()Lio/ktor/http/auth/DigestAlgorithm;", "getMD5$annotations", "MD5_SESS", "getMD5_SESS", "getMD5_SESS$annotations", "SHA_256", "getSHA_256", "SHA_256_SESS", "getSHA_256_SESS", "SHA_512_256", "getSHA_512_256", "SHA_512_256_SESS", "getSHA_512_256_SESS", "", "DEFAULT_ALGORITHMS", "Ljava/util/List;", "getDEFAULT_ALGORITHMS", "()Ljava/util/List;", "getDEFAULT_ALGORITHMS$annotations", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getDEFAULT_ALGORITHMS$annotations() {
        }

        @Deprecated(message = "MD5 is deprecated because it is not secure")
        public static /* synthetic */ void getMD5$annotations() {
        }

        @Deprecated(message = "MD5-sess is deprecated because it is not secure")
        public static /* synthetic */ void getMD5_SESS$annotations() {
        }

        private Companion() {
        }

        public final DigestAlgorithm getMD5() {
            return DigestAlgorithm.MD5;
        }

        public final DigestAlgorithm getMD5_SESS() {
            return DigestAlgorithm.MD5_SESS;
        }

        public final DigestAlgorithm getSHA_256() {
            return DigestAlgorithm.SHA_256;
        }

        public final DigestAlgorithm getSHA_256_SESS() {
            return DigestAlgorithm.SHA_256_SESS;
        }

        public final DigestAlgorithm getSHA_512_256() {
            return DigestAlgorithm.SHA_512_256;
        }

        public final DigestAlgorithm getSHA_512_256_SESS() {
            return DigestAlgorithm.SHA_512_256_SESS;
        }

        public final List<DigestAlgorithm> getDEFAULT_ALGORITHMS() {
            return DigestAlgorithm.DEFAULT_ALGORITHMS;
        }

        public final DigestAlgorithm from(String name) {
            Object next;
            Intrinsics.checkNotNullParameter(name, "name");
            Iterator<T> it = getDEFAULT_ALGORITHMS().iterator();
            while (it.hasNext()) {
                next = it.next();
                if (StringsKt.equals(((DigestAlgorithm) next).getName(), name, true)) {
                    return (DigestAlgorithm) next;
                }
            }
            next = null;
            return (DigestAlgorithm) next;
        }
    }

    static {
        DigestAlgorithm digestAlgorithm = new DigestAlgorithm("MD5", "MD5", false);
        MD5 = digestAlgorithm;
        DigestAlgorithm digestAlgorithm2 = new DigestAlgorithm("MD5-sess", "MD5", true);
        MD5_SESS = digestAlgorithm2;
        DigestAlgorithm digestAlgorithm3 = new DigestAlgorithm("SHA-256", "SHA-256", false);
        SHA_256 = digestAlgorithm3;
        DigestAlgorithm digestAlgorithm4 = new DigestAlgorithm("SHA-256-sess", "SHA-256", true);
        SHA_256_SESS = digestAlgorithm4;
        DigestAlgorithm digestAlgorithm5 = new DigestAlgorithm("SHA-512-256", "SHA-512/256", false);
        SHA_512_256 = digestAlgorithm5;
        DigestAlgorithm digestAlgorithm6 = new DigestAlgorithm("SHA-512-256-sess", "SHA-512/256", true);
        SHA_512_256_SESS = digestAlgorithm6;
        DEFAULT_ALGORITHMS = CollectionsKt.listOf((Object[]) new DigestAlgorithm[]{digestAlgorithm5, digestAlgorithm6, digestAlgorithm3, digestAlgorithm4, digestAlgorithm, digestAlgorithm2});
    }
}
