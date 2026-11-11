package io.ktor.http.auth;

import androidx.media3.exoplayer.RendererCapabilities;
import io.ktor.http.CodecsKt;
import io.ktor.http.HeaderValueParam;
import io.ktor.http.HeaderValueWithParametersKt;
import io.ktor.http.parsing.ParseException;
import io.ktor.util.CryptoKt__CryptoJvmKt;
import io.ktor.util.Hash;
import io.ktor.util.TextKt;
import io.ktor.utils.io.charsets.CharsetJVMKt;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;
import org.mozilla.javascript.optimizer.Signatures;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: compiled from: HttpAuthHeader.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u000e2\u00020\u0001:\u0004\u000f\u0010\u000e\u0011B\u0011\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\b\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0006H&¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\b\u001a\u00020\u0002H&¢\u0006\u0004\b\b\u0010\nJ\u0011\u0010\u000b\u001a\u00020\u0002H\u0096\u0080\u0004¢\u0006\u0004\b\u000b\u0010\nR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\f\u001a\u0004\b\r\u0010\n\u0082\u0001\u0002\u0012\u0013¨\u0006\u0014"}, d2 = {"Lio/ktor/http/auth/HttpAuthHeader;", "", "", "authScheme", "<init>", "(Ljava/lang/String;)V", "Lio/ktor/http/auth/HeaderValueEncoding;", "encoding", "render", "(Lio/ktor/http/auth/HeaderValueEncoding;)Ljava/lang/String;", "()Ljava/lang/String;", "toString", "Ljava/lang/String;", "getAuthScheme", "Companion", "Single", "Parameterized", "Parameters", "Lio/ktor/http/auth/HttpAuthHeader$Parameterized;", "Lio/ktor/http/auth/HttpAuthHeader$Single;", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class HttpAuthHeader {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String authScheme;

    public /* synthetic */ HttpAuthHeader(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    public abstract String render();

    public abstract String render(HeaderValueEncoding encoding);

    private HttpAuthHeader(String str) {
        this.authScheme = str;
        if (HttpAuthHeaderKt.token68Pattern.matches(str)) {
            return;
        }
        throw new ParseException("Invalid authScheme value: it should be token, but instead it is " + str, null, 2, null);
    }

    public final String getAuthScheme() {
        return this.authScheme;
    }

    /* JADX INFO: compiled from: HttpAuthHeader.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u0007\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\u0007\u0010\u000bJ\u001b\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0096\u0082\u0004¢\u0006\u0004\b\u000f\u0010\u0010J\u0011\u0010\u0012\u001a\u00020\u0011H\u0096\u0080\u0004¢\u0006\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0014\u001a\u0004\b\u0015\u0010\b¨\u0006\u0016"}, d2 = {"Lio/ktor/http/auth/HttpAuthHeader$Single;", "Lio/ktor/http/auth/HttpAuthHeader;", "", "authScheme", "blob", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "render", "()Ljava/lang/String;", "Lio/ktor/http/auth/HeaderValueEncoding;", "encoding", "(Lio/ktor/http/auth/HeaderValueEncoding;)Ljava/lang/String;", "", "other", "", "equals", Signatures.MATH_TO_BOOLEAN, "", "hashCode", "()I", "Ljava/lang/String;", "getBlob", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Single extends HttpAuthHeader {
        private final String blob;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Single(String authScheme, String blob) {
            super(authScheme, null);
            Intrinsics.checkNotNullParameter(authScheme, "authScheme");
            Intrinsics.checkNotNullParameter(blob, "blob");
            this.blob = blob;
            if (!HttpAuthHeaderKt.token68Pattern.matches(blob)) {
                throw new ParseException("Invalid blob value: it should be token68", null, 2, null);
            }
        }

        public final String getBlob() {
            return this.blob;
        }

        @Override // io.ktor.http.auth.HttpAuthHeader
        public String render() {
            return getAuthScheme() + ' ' + this.blob;
        }

        @Override // io.ktor.http.auth.HttpAuthHeader
        public String render(HeaderValueEncoding encoding) {
            Intrinsics.checkNotNullParameter(encoding, "encoding");
            return render();
        }

        public boolean equals(Object other) {
            if (!(other instanceof Single)) {
                return false;
            }
            Single single = (Single) other;
            return StringsKt.equals(single.getAuthScheme(), getAuthScheme(), true) && StringsKt.equals(single.blob, this.blob, true);
        }

        public int hashCode() {
            Hash hash = Hash.INSTANCE;
            String lowerCase = getAuthScheme().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            String lowerCase2 = this.blob.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
            return hash.combine(lowerCase, lowerCase2);
        }
    }

    /* JADX INFO: compiled from: HttpAuthHeader.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\r\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nB/\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u000b\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\fJ\u001d\u0010\u000f\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0011\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0002¢\u0006\u0004\b\u0011\u0010\u0010J\u0017\u0010\u0012\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0014\u001a\u0004\u0018\u00010\u00022\u0006\u0010\r\u001a\u00020\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\u0016\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0012\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0012\u0010\u0018J\u001b\u0010\u001c\u001a\u00020\u001b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0096\u0082\u0004¢\u0006\u0004\b\u001c\u0010\u001dJ\u0011\u0010\u001f\u001a\u00020\u001eH\u0096\u0080\u0004¢\u0006\u0004\b\u001f\u0010 R\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010!\u001a\u0004\b\"\u0010#R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010$\u001a\u0004\b%\u0010&¨\u0006'"}, d2 = {"Lio/ktor/http/auth/HttpAuthHeader$Parameterized;", "Lio/ktor/http/auth/HttpAuthHeader;", "", "authScheme", "", "Lio/ktor/http/HeaderValueParam;", "parameters", "Lio/ktor/http/auth/HeaderValueEncoding;", "encoding", "<init>", "(Ljava/lang/String;Ljava/util/List;Lio/ktor/http/auth/HeaderValueEncoding;)V", "", "(Ljava/lang/String;Ljava/util/Map;Lio/ktor/http/auth/HeaderValueEncoding;)V", "name", ES6Iterator.VALUE_PROPERTY, "withParameter", "(Ljava/lang/String;Ljava/lang/String;)Lio/ktor/http/auth/HttpAuthHeader$Parameterized;", "withReplacedParameter", "render", "(Lio/ktor/http/auth/HeaderValueEncoding;)Ljava/lang/String;", "parameter", "(Ljava/lang/String;)Ljava/lang/String;", "encode", "(Ljava/lang/String;Lio/ktor/http/auth/HeaderValueEncoding;)Ljava/lang/String;", "()Ljava/lang/String;", "", "other", "", "equals", Signatures.MATH_TO_BOOLEAN, "", "hashCode", "()I", "Ljava/util/List;", "getParameters", "()Ljava/util/List;", "Lio/ktor/http/auth/HeaderValueEncoding;", "getEncoding", "()Lio/ktor/http/auth/HeaderValueEncoding;", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Parameterized extends HttpAuthHeader {
        private final HeaderValueEncoding encoding;
        private final List<HeaderValueParam> parameters;

        /* JADX INFO: compiled from: HttpAuthHeader.kt */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[HeaderValueEncoding.values().length];
                try {
                    iArr[HeaderValueEncoding.QUOTED_WHEN_REQUIRED.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[HeaderValueEncoding.QUOTED_ALWAYS.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[HeaderValueEncoding.URI_ENCODE.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Parameterized(String authScheme, List<HeaderValueParam> parameters, HeaderValueEncoding encoding) {
            super(authScheme, null);
            Intrinsics.checkNotNullParameter(authScheme, "authScheme");
            Intrinsics.checkNotNullParameter(parameters, "parameters");
            Intrinsics.checkNotNullParameter(encoding, "encoding");
            this.parameters = parameters;
            this.encoding = encoding;
            Iterator<T> it = parameters.iterator();
            while (it.hasNext()) {
                if (!HttpAuthHeaderKt.token68Pattern.matches(((HeaderValueParam) it.next()).getName())) {
                    throw new ParseException("Parameter name should be a token", null, 2, null);
                }
            }
        }

        public final List<HeaderValueParam> getParameters() {
            return this.parameters;
        }

        public /* synthetic */ Parameterized(String str, List list, HeaderValueEncoding headerValueEncoding, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (List<HeaderValueParam>) list, (i & 4) != 0 ? HeaderValueEncoding.QUOTED_WHEN_REQUIRED : headerValueEncoding);
        }

        public final HeaderValueEncoding getEncoding() {
            return this.encoding;
        }

        public /* synthetic */ Parameterized(String str, Map map, HeaderValueEncoding headerValueEncoding, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (Map<String, String>) map, (i & 4) != 0 ? HeaderValueEncoding.QUOTED_WHEN_REQUIRED : headerValueEncoding);
        }

        public Parameterized(String authScheme, Map<String, String> parameters, HeaderValueEncoding encoding) {
            Intrinsics.checkNotNullParameter(authScheme, "authScheme");
            Intrinsics.checkNotNullParameter(parameters, "parameters");
            Intrinsics.checkNotNullParameter(encoding, "encoding");
            Set<Map.Entry<String, String>> setEntrySet = parameters.entrySet();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(setEntrySet, 10));
            Iterator<T> it = setEntrySet.iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                arrayList.add(new HeaderValueParam((String) entry.getKey(), (String) entry.getValue()));
            }
            this(authScheme, arrayList, encoding);
        }

        public final Parameterized withParameter(String name, String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            return new Parameterized(getAuthScheme(), (List<HeaderValueParam>) CollectionsKt.plus((Collection<? extends HeaderValueParam>) this.parameters, new HeaderValueParam(name, value)), this.encoding);
        }

        public final Parameterized withReplacedParameter(String name, String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            Iterator<HeaderValueParam> it = this.parameters.iterator();
            boolean z = false;
            int i = 0;
            while (true) {
                if (!it.hasNext()) {
                    i = -1;
                    break;
                }
                if (Intrinsics.areEqual(it.next().getName(), name)) {
                    break;
                }
                i++;
            }
            if (i == -1) {
                return withParameter(name, value);
            }
            List<HeaderValueParam> list = this.parameters;
            ArrayList arrayList = new ArrayList();
            for (HeaderValueParam headerValueParam : list) {
                if (Intrinsics.areEqual(headerValueParam.getName(), name)) {
                    if (z) {
                        headerValueParam = null;
                    } else {
                        headerValueParam = new HeaderValueParam(name, value);
                        z = true;
                    }
                }
                if (headerValueParam != null) {
                    arrayList.add(headerValueParam);
                }
            }
            return new Parameterized(getAuthScheme(), arrayList, this.encoding);
        }

        @Override // io.ktor.http.auth.HttpAuthHeader
        public String render(final HeaderValueEncoding encoding) {
            Intrinsics.checkNotNullParameter(encoding, "encoding");
            if (this.parameters.isEmpty()) {
                return getAuthScheme();
            }
            return CollectionsKt.joinToString$default(this.parameters, ", ", getAuthScheme() + ' ', null, 0, null, new Function1() { // from class: io.ktor.http.auth.HttpAuthHeader$Parameterized$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return HttpAuthHeader.Parameterized.render$lambda$0(this.f$0, encoding, (HeaderValueParam) obj);
                }
            }, 28, null);
        }

        static final CharSequence render$lambda$0(Parameterized parameterized, HeaderValueEncoding headerValueEncoding, HeaderValueParam it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return it.getName() + '=' + parameterized.encode(it.getValue(), headerValueEncoding);
        }

        public final String parameter(String name) {
            Object next;
            Intrinsics.checkNotNullParameter(name, "name");
            Iterator<T> it = this.parameters.iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!Intrinsics.areEqual(((HeaderValueParam) next).getName(), name));
            HeaderValueParam headerValueParam = (HeaderValueParam) next;
            if (headerValueParam != null) {
                return headerValueParam.getValue();
            }
            return null;
        }

        private final String encode(String str, HeaderValueEncoding headerValueEncoding) {
            int i = WhenMappings.$EnumSwitchMapping$0[headerValueEncoding.ordinal()];
            if (i == 1) {
                return HeaderValueWithParametersKt.escapeIfNeeded(str);
            }
            if (i == 2) {
                return HeaderValueWithParametersKt.quote(str);
            }
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            return CodecsKt.encodeURLParameter$default(str, false, 1, null);
        }

        @Override // io.ktor.http.auth.HttpAuthHeader
        public String render() {
            return render(this.encoding);
        }

        public boolean equals(Object other) {
            if (!(other instanceof Parameterized)) {
                return false;
            }
            Parameterized parameterized = (Parameterized) other;
            return StringsKt.equals(parameterized.getAuthScheme(), getAuthScheme(), true) && Intrinsics.areEqual(parameterized.parameters, this.parameters);
        }

        public int hashCode() {
            Hash hash = Hash.INSTANCE;
            String lowerCase = getAuthScheme().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            return hash.combine(lowerCase, this.parameters);
        }
    }

    public String toString() {
        return render();
    }

    /* JADX INFO: compiled from: HttpAuthHeader.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\n\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u000e\u0010\b\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u0007¢\u0006\u0004\b\n\u0010\u000bJ!\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u000e\u0010\u000fJ}\u0010\u001a\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0010\u001a\u00020\u00042\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u00112\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010\u0017\u001a\u00020\u00162\u0010\b\u0002\u0010\b\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u00072\b\b\u0002\u0010\u0018\u001a\u00020\u00142\u000e\b\u0002\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00040\u0011¢\u0006\u0004\b\u001a\u0010\u001bJS\u0010\u001a\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0010\u001a\u00020\u00042\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u00112\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010\u0017\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u001a\u0010\u001c¨\u0006\u001d"}, d2 = {"Lio/ktor/http/auth/HttpAuthHeader$Companion;", "", "<init>", "()V", "", Parameters.Realm, "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", Parameters.Charset, "Lio/ktor/http/auth/HttpAuthHeader$Parameterized;", "basicAuthChallenge", "(Ljava/lang/String;Ljava/nio/charset/Charset;)Lio/ktor/http/auth/HttpAuthHeader$Parameterized;", "scheme", "Lio/ktor/http/auth/HttpAuthHeader;", "bearerAuthChallenge", "(Ljava/lang/String;Ljava/lang/String;)Lio/ktor/http/auth/HttpAuthHeader;", "nonce", "", "domain", "opaque", "", "stale", "Lio/ktor/http/auth/DigestAlgorithm;", "algorithm", "userhash", "qop", "digestAuthChallenge", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/Boolean;Lio/ktor/http/auth/DigestAlgorithm;Ljava/nio/charset/Charset;ZLjava/util/List;)Lio/ktor/http/auth/HttpAuthHeader$Parameterized;", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;)Lio/ktor/http/auth/HttpAuthHeader$Parameterized;", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Parameterized basicAuthChallenge(String realm, Charset charset) {
            Intrinsics.checkNotNullParameter(realm, "realm");
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put(Parameters.Realm, realm);
            if (charset != null) {
                linkedHashMap.put(Parameters.Charset, CharsetJVMKt.getName(charset));
            }
            return new Parameterized(AuthScheme.Basic, linkedHashMap, (HeaderValueEncoding) null, 4, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ HttpAuthHeader bearerAuthChallenge$default(Companion companion, String str, String str2, int i, Object obj) {
            if ((i & 2) != 0) {
                str2 = null;
            }
            return companion.bearerAuthChallenge(str, str2);
        }

        public final HttpAuthHeader bearerAuthChallenge(String scheme, String realm) {
            Intrinsics.checkNotNullParameter(scheme, "scheme");
            return new Parameterized(scheme, realm == null ? MapsKt.emptyMap() : MapsKt.mapOf(TuplesKt.to(Parameters.Realm, realm)), (HeaderValueEncoding) null, 4, (DefaultConstructorMarker) null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Parameterized digestAuthChallenge$default(Companion companion, String str, String str2, List list, String str3, Boolean bool, DigestAlgorithm digestAlgorithm, Charset charset, boolean z, List list2, int i, Object obj) {
            return companion.digestAuthChallenge(str, (i & 2) != 0 ? CryptoKt__CryptoJvmKt.generateNonceBlocking$default(0, 1, null) : str2, (i & 4) != 0 ? CollectionsKt.emptyList() : list, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : bool, (i & 32) != 0 ? DigestAlgorithm.INSTANCE.getSHA_256() : digestAlgorithm, (i & 64) == 0 ? charset : null, (i & 128) == 0 ? z : false, (i & 256) != 0 ? CollectionsKt.emptyList() : list2);
        }

        public final Parameterized digestAuthChallenge(String realm, String nonce, List<String> domain, String opaque, Boolean stale, DigestAlgorithm algorithm, Charset charset, boolean userhash, List<String> qop) {
            Intrinsics.checkNotNullParameter(realm, "realm");
            Intrinsics.checkNotNullParameter(nonce, "nonce");
            Intrinsics.checkNotNullParameter(domain, "domain");
            Intrinsics.checkNotNullParameter(algorithm, "algorithm");
            Intrinsics.checkNotNullParameter(qop, "qop");
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put(Parameters.Realm, HeaderValueWithParametersKt.quote(realm));
            linkedHashMap.put("nonce", HeaderValueWithParametersKt.quote(nonce));
            if (!domain.isEmpty()) {
                linkedHashMap.put("domain", HeaderValueWithParametersKt.quote(CollectionsKt.joinToString$default(domain, Stream.ID_UNKNOWN, null, null, 0, null, null, 62, null)));
            }
            if (opaque != null) {
                linkedHashMap.put("opaque", HeaderValueWithParametersKt.quote(opaque));
            }
            if (stale != null) {
                linkedHashMap.put("stale", String.valueOf(stale.booleanValue()));
            }
            linkedHashMap.put("algorithm", algorithm.getName());
            if (charset != null) {
                linkedHashMap.put(Parameters.Charset, TextKt.toUpperCasePreservingASCIIRules(CharsetJVMKt.getName(charset)));
            }
            if (userhash) {
                linkedHashMap.put("userhash", "true");
            }
            if (!qop.isEmpty()) {
                linkedHashMap.put("qop", HeaderValueWithParametersKt.quote(CollectionsKt.joinToString$default(qop, ",", null, null, 0, null, null, 62, null)));
            }
            return new Parameterized(AuthScheme.Digest, linkedHashMap, HeaderValueEncoding.QUOTED_WHEN_REQUIRED);
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Maintained for binary compatibility")
        public final Parameterized digestAuthChallenge(String realm, String nonce, List<String> domain, String opaque, Boolean stale, String algorithm) {
            Intrinsics.checkNotNullParameter(realm, "realm");
            Intrinsics.checkNotNullParameter(nonce, "nonce");
            Intrinsics.checkNotNullParameter(domain, "domain");
            Intrinsics.checkNotNullParameter(algorithm, "algorithm");
            DigestAlgorithm digestAlgorithmFrom = DigestAlgorithm.INSTANCE.from(algorithm);
            if (digestAlgorithmFrom == null) {
                throw new IllegalArgumentException(("Unsupported algorithm: " + algorithm).toString());
            }
            return digestAuthChallenge$default(this, realm, nonce, domain, opaque, stale, digestAlgorithmFrom, null, false, null, RendererCapabilities.DECODER_SUPPORT_MASK, null);
        }
    }

    /* JADX INFO: compiled from: HttpAuthHeader.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000f\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\t\u0010\u0006R\u0014\u0010\n\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u000b\u0010\u0006R\u0014\u0010\f\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\r\u0010\u0006R\u0014\u0010\u000e\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u000e\u0010\u0006R\u0014\u0010\u000f\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u000f\u0010\u0006R\u0014\u0010\u0010\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0010\u0010\u0006R\u0014\u0010\u0011\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0011\u0010\u0006R\u0014\u0010\u0012\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0012\u0010\u0006¨\u0006\u0013"}, d2 = {"Lio/ktor/http/auth/HttpAuthHeader$Parameters;", "", "<init>", "()V", "", "Realm", "Ljava/lang/String;", "Charset", "OAuthCallback", "OAuthConsumerKey", "OAuthNonce", "OAuthToken", "OAuthTokenSecret", "OAuthVerifier", "OAuthSignatureMethod", "OAuthTimestamp", "OAuthVersion", "OAuthSignature", "OAuthCallbackConfirmed", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Parameters {
        public static final String Charset = "charset";
        public static final Parameters INSTANCE = new Parameters();
        public static final String OAuthCallback = "oauth_callback";
        public static final String OAuthCallbackConfirmed = "oauth_callback_confirmed";
        public static final String OAuthConsumerKey = "oauth_consumer_key";
        public static final String OAuthNonce = "oauth_nonce";
        public static final String OAuthSignature = "oauth_signature";
        public static final String OAuthSignatureMethod = "oauth_signature_method";
        public static final String OAuthTimestamp = "oauth_timestamp";
        public static final String OAuthToken = "oauth_token";
        public static final String OAuthTokenSecret = "oauth_token_secret";
        public static final String OAuthVerifier = "oauth_verifier";
        public static final String OAuthVersion = "oauth_version";
        public static final String Realm = "realm";

        private Parameters() {
        }
    }
}
