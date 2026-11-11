package io.ktor.http;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: HttpProtocolVersion.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\b\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0011\u0010\t\u001a\u00020\u0002H\u0096\u0080\u0004¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u000b\u0010\nJ\u0010\u0010\f\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u000e\u0010\rJ.\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0004HÆ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004¢\u0006\u0004\b\u0013\u0010\u0014J\u0011\u0010\u0015\u001a\u00020\u0004HÖ\u0081\u0004¢\u0006\u0004\b\u0015\u0010\rR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0016\u001a\u0004\b\u0017\u0010\nR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0018\u001a\u0004\b\u0019\u0010\rR\u0017\u0010\u0006\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0018\u001a\u0004\b\u001a\u0010\r¨\u0006\u001c"}, d2 = {"Lio/ktor/http/HttpProtocolVersion;", "", "", "name", "", "major", "minor", "<init>", "(Ljava/lang/String;II)V", "toString", "()Ljava/lang/String;", "component1", "component2", "()I", "component3", "copy", "(Ljava/lang/String;II)Lio/ktor/http/HttpProtocolVersion;", "other", "", "equals", Signatures.MATH_TO_BOOLEAN, "hashCode", "Ljava/lang/String;", "getName", "I", "getMajor", "getMinor", "Companion", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class HttpProtocolVersion {
    private final int major;
    private final int minor;
    private final String name;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final HttpProtocolVersion HTTP_3_0 = new HttpProtocolVersion("HTTP", 3, 0);
    private static final HttpProtocolVersion HTTP_2_0 = new HttpProtocolVersion("HTTP", 2, 0);
    private static final HttpProtocolVersion HTTP_1_1 = new HttpProtocolVersion("HTTP", 1, 1);
    private static final HttpProtocolVersion HTTP_1_0 = new HttpProtocolVersion("HTTP", 1, 0);
    private static final HttpProtocolVersion SPDY_3 = new HttpProtocolVersion("SPDY", 3, 0);
    private static final HttpProtocolVersion QUIC = new HttpProtocolVersion("QUIC", 1, 0);

    public static /* synthetic */ HttpProtocolVersion copy$default(HttpProtocolVersion httpProtocolVersion, String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = httpProtocolVersion.name;
        }
        if ((i3 & 2) != 0) {
            i = httpProtocolVersion.major;
        }
        if ((i3 & 4) != 0) {
            i2 = httpProtocolVersion.minor;
        }
        return httpProtocolVersion.copy(str, i, i2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getMajor() {
        return this.major;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getMinor() {
        return this.minor;
    }

    public final HttpProtocolVersion copy(String name, int major, int minor) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new HttpProtocolVersion(name, major, minor);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HttpProtocolVersion)) {
            return false;
        }
        HttpProtocolVersion httpProtocolVersion = (HttpProtocolVersion) other;
        return Intrinsics.areEqual(this.name, httpProtocolVersion.name) && this.major == httpProtocolVersion.major && this.minor == httpProtocolVersion.minor;
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + this.major) * 31) + this.minor;
    }

    /* JADX INFO: compiled from: HttpProtocolVersion.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0012\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\n\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006¢\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\u000e\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0014\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0013R\u0017\u0010\u0016\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0017\u0010\u0013R\u0017\u0010\u0018\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0011\u001a\u0004\b\u0019\u0010\u0013R\u0017\u0010\u001a\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u0011\u001a\u0004\b\u001b\u0010\u0013R\u0017\u0010\u001c\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u0011\u001a\u0004\b\u001d\u0010\u0013¨\u0006\u001e"}, d2 = {"Lio/ktor/http/HttpProtocolVersion$Companion;", "", "<init>", "()V", "", "name", "", "major", "minor", "Lio/ktor/http/HttpProtocolVersion;", "fromValue", "(Ljava/lang/String;II)Lio/ktor/http/HttpProtocolVersion;", "", ES6Iterator.VALUE_PROPERTY, "parse", "(Ljava/lang/CharSequence;)Lio/ktor/http/HttpProtocolVersion;", "HTTP_3_0", "Lio/ktor/http/HttpProtocolVersion;", "getHTTP_3_0", "()Lio/ktor/http/HttpProtocolVersion;", "HTTP_2_0", "getHTTP_2_0", "HTTP_1_1", "getHTTP_1_1", "HTTP_1_0", "getHTTP_1_0", "SPDY_3", "getSPDY_3", "QUIC", "getQUIC", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final HttpProtocolVersion getHTTP_3_0() {
            return HttpProtocolVersion.HTTP_3_0;
        }

        public final HttpProtocolVersion getHTTP_2_0() {
            return HttpProtocolVersion.HTTP_2_0;
        }

        public final HttpProtocolVersion getHTTP_1_1() {
            return HttpProtocolVersion.HTTP_1_1;
        }

        public final HttpProtocolVersion getHTTP_1_0() {
            return HttpProtocolVersion.HTTP_1_0;
        }

        public final HttpProtocolVersion getSPDY_3() {
            return HttpProtocolVersion.SPDY_3;
        }

        public final HttpProtocolVersion getQUIC() {
            return HttpProtocolVersion.QUIC;
        }

        public final HttpProtocolVersion fromValue(String name, int major, int minor) {
            Intrinsics.checkNotNullParameter(name, "name");
            if (Intrinsics.areEqual(name, "HTTP") && major == 1 && minor == 0) {
                return getHTTP_1_0();
            }
            if (Intrinsics.areEqual(name, "HTTP") && major == 1 && minor == 1) {
                return getHTTP_1_1();
            }
            if (Intrinsics.areEqual(name, "HTTP") && major == 2 && minor == 0) {
                return getHTTP_2_0();
            }
            return (Intrinsics.areEqual(name, "HTTP") && major == 3 && minor == 0) ? getHTTP_3_0() : new HttpProtocolVersion(name, major, minor);
        }

        public final HttpProtocolVersion parse(CharSequence value) {
            Intrinsics.checkNotNullParameter(value, "value");
            if (Intrinsics.areEqual(value, "HTTP/1.1")) {
                return getHTTP_1_1();
            }
            if (Intrinsics.areEqual(value, "HTTP/1.0")) {
                return getHTTP_1_0();
            }
            if (Intrinsics.areEqual(value, "HTTP/2.0")) {
                return getHTTP_2_0();
            }
            if (Intrinsics.areEqual(value, "HTTP/3.0")) {
                return getHTTP_3_0();
            }
            List listSplit$default = StringsKt.split$default(value, new String[]{"/", "."}, false, 0, 6, (Object) null);
            if (listSplit$default.size() == 3) {
                return fromValue((String) listSplit$default.get(0), Integer.parseInt((String) listSplit$default.get(1)), Integer.parseInt((String) listSplit$default.get(2)));
            }
            throw new IllegalStateException(("Failed to parse HttpProtocolVersion. Expected format: protocol/major.minor, but actual: " + ((Object) value)).toString());
        }
    }

    public HttpProtocolVersion(String name, int i, int i2) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
        this.major = i;
        this.minor = i2;
    }

    public final int getMajor() {
        return this.major;
    }

    public final int getMinor() {
        return this.minor;
    }

    public final String getName() {
        return this.name;
    }

    public String toString() {
        return this.name + '/' + this.major + '.' + this.minor;
    }
}
