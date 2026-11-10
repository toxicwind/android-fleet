package okhttp3.logging;

import b.a.a.a.a;
import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedLong;
import com.google.firebase.installations.remote.FirebaseInstallationServiceClient;
import com.hisense.support.tv.HisenseCountryLanInfo;
import d.g;
import d.i;
import d.n;
import java.io.EOFException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.platform.Platform;

/* JADX INFO: loaded from: classes2.dex */
public final class HttpLoggingInterceptor implements Interceptor {
    public static final Charset UTF8 = Charset.forName("UTF-8");
    public volatile Set<String> headersToRedact;
    public volatile Level level;
    public final Logger logger;

    public enum Level {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }

    public interface Logger {
        public static final Logger DEFAULT = new Logger() { // from class: okhttp3.logging.HttpLoggingInterceptor.Logger.1
            @Override // okhttp3.logging.HttpLoggingInterceptor.Logger
            public void log(String str) {
                Platform.PLATFORM.log(4, str, null);
            }
        };

        void log(String str);
    }

    public HttpLoggingInterceptor() {
        this(Logger.DEFAULT);
    }

    public static boolean bodyHasUnknownEncoding(Headers headers) {
        String str = headers.get("Content-Encoding");
        return (str == null || str.equalsIgnoreCase("identity") || str.equalsIgnoreCase(FirebaseInstallationServiceClient.GZIP_CONTENT_ENCODING)) ? false : true;
    }

    public static boolean isPlaintext(g gVar) {
        try {
            g gVar2 = new g();
            gVar.a(gVar2, 0L, gVar.f2102c < 64 ? gVar.f2102c : 64L);
            for (int i = 0; i < 16 && !gVar2.e(); i++) {
                int iP = gVar2.p();
                if (Character.isISOControl(iP) && !Character.isWhitespace(iP)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }

    private void logHeader(Headers headers, int i) {
        String strValue = this.headersToRedact.contains(headers.name(i)) ? "██" : headers.value(i);
        this.logger.log(headers.name(i) + ": " + strValue);
    }

    public Level getLevel() {
        return this.level;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws Exception {
        String string;
        char c2;
        String string2;
        Long lValueOf;
        Level level = this.level;
        Request request = chain.request();
        if (level == Level.NONE) {
            return chain.proceed(request);
        }
        boolean z = level == Level.BODY;
        boolean z2 = z || level == Level.HEADERS;
        RequestBody requestBodyBody = request.body();
        boolean z3 = requestBodyBody != null;
        Connection connection = chain.connection();
        StringBuilder sbA = a.a("--> ");
        sbA.append(request.method());
        sbA.append(Ascii.CASE_MASK);
        sbA.append(request.url());
        if (connection != null) {
            StringBuilder sbA2 = a.a(" ");
            sbA2.append(connection.protocol());
            string = sbA2.toString();
        } else {
            string = "";
        }
        sbA.append(string);
        String string3 = sbA.toString();
        if (!z2 && z3) {
            StringBuilder sbB = a.b(string3, " (");
            sbB.append(requestBodyBody.contentLength());
            sbB.append("-byte body)");
            string3 = sbB.toString();
        }
        this.logger.log(string3);
        if (z2) {
            if (z3) {
                if (requestBodyBody.contentType() != null) {
                    Logger logger = this.logger;
                    StringBuilder sbA3 = a.a("Content-Type: ");
                    sbA3.append(requestBodyBody.contentType());
                    logger.log(sbA3.toString());
                }
                if (requestBodyBody.contentLength() != -1) {
                    Logger logger2 = this.logger;
                    StringBuilder sbA4 = a.a("Content-Length: ");
                    sbA4.append(requestBodyBody.contentLength());
                    logger2.log(sbA4.toString());
                }
            }
            Headers headers = request.headers();
            int size = headers.size();
            for (int i = 0; i < size; i++) {
                String strName = headers.name(i);
                if (!"Content-Type".equalsIgnoreCase(strName) && !"Content-Length".equalsIgnoreCase(strName)) {
                    logHeader(headers, i);
                }
            }
            if (!z || !z3) {
                Logger logger3 = this.logger;
                StringBuilder sbA5 = a.a("--> END ");
                sbA5.append(request.method());
                logger3.log(sbA5.toString());
            } else if (bodyHasUnknownEncoding(request.headers())) {
                Logger logger4 = this.logger;
                StringBuilder sbA6 = a.a("--> END ");
                sbA6.append(request.method());
                sbA6.append(" (encoded body omitted)");
                logger4.log(sbA6.toString());
            } else {
                g gVar = new g();
                requestBodyBody.writeTo(gVar);
                Charset charset = UTF8;
                MediaType mediaTypeContentType = requestBodyBody.contentType();
                if (mediaTypeContentType != null) {
                    charset = mediaTypeContentType.charset(UTF8);
                }
                this.logger.log("");
                if (isPlaintext(gVar)) {
                    this.logger.log(gVar.a(charset));
                    Logger logger5 = this.logger;
                    StringBuilder sbA7 = a.a("--> END ");
                    sbA7.append(request.method());
                    sbA7.append(" (");
                    sbA7.append(requestBodyBody.contentLength());
                    sbA7.append("-byte body)");
                    logger5.log(sbA7.toString());
                } else {
                    Logger logger6 = this.logger;
                    StringBuilder sbA8 = a.a("--> END ");
                    sbA8.append(request.method());
                    sbA8.append(" (binary ");
                    sbA8.append(requestBodyBody.contentLength());
                    sbA8.append("-byte body omitted)");
                    logger6.log(sbA8.toString());
                }
            }
        }
        long jNanoTime = System.nanoTime();
        try {
            Response responseProceed = chain.proceed(request);
            long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - jNanoTime);
            ResponseBody responseBodyBody = responseProceed.body();
            long jContentLength = responseBodyBody.contentLength();
            String str = jContentLength != -1 ? jContentLength + "-byte" : "unknown-length";
            Logger logger7 = this.logger;
            StringBuilder sbA9 = a.a("<-- ");
            sbA9.append(responseProceed.code());
            if (responseProceed.message().isEmpty()) {
                c2 = ' ';
                string2 = "";
            } else {
                StringBuilder sb = new StringBuilder();
                c2 = Ascii.CASE_MASK;
                sb.append(Ascii.CASE_MASK);
                sb.append(responseProceed.message());
                string2 = sb.toString();
            }
            sbA9.append(string2);
            sbA9.append(c2);
            sbA9.append(responseProceed.request().url());
            sbA9.append(" (");
            sbA9.append(millis);
            sbA9.append(HisenseCountryLanInfo.LOCALE_MAL_CODE);
            sbA9.append(!z2 ? a.a(", ", str, " body") : "");
            sbA9.append(')');
            logger7.log(sbA9.toString());
            if (z2) {
                Headers headers2 = responseProceed.headers();
                int size2 = headers2.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    logHeader(headers2, i2);
                }
                if (!z || !HttpHeaders.hasBody(responseProceed)) {
                    this.logger.log("<-- END HTTP");
                } else if (bodyHasUnknownEncoding(responseProceed.headers())) {
                    this.logger.log("<-- END HTTP (encoded body omitted)");
                } else {
                    i iVarSource = responseBodyBody.source();
                    iVarSource.request(UnsignedLong.UNSIGNED_MASK);
                    g gVarA = iVarSource.a();
                    n nVar = null;
                    if (FirebaseInstallationServiceClient.GZIP_CONTENT_ENCODING.equalsIgnoreCase(headers2.get("Content-Encoding"))) {
                        lValueOf = Long.valueOf(gVarA.f2102c);
                        try {
                            n nVar2 = new n(gVarA.m8clone());
                            try {
                                gVarA = new g();
                                gVarA.a(nVar2);
                                nVar2.close();
                            } catch (Throwable th) {
                                th = th;
                                nVar = nVar2;
                                if (nVar != null) {
                                    nVar.close();
                                }
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } else {
                        lValueOf = null;
                    }
                    Charset charset2 = UTF8;
                    MediaType mediaTypeContentType2 = responseBodyBody.contentType();
                    if (mediaTypeContentType2 != null) {
                        charset2 = mediaTypeContentType2.charset(UTF8);
                    }
                    if (!isPlaintext(gVarA)) {
                        this.logger.log("");
                        Logger logger8 = this.logger;
                        StringBuilder sbA10 = a.a("<-- END HTTP (binary ");
                        sbA10.append(gVarA.f2102c);
                        sbA10.append("-byte body omitted)");
                        logger8.log(sbA10.toString());
                        return responseProceed;
                    }
                    if (jContentLength != 0) {
                        this.logger.log("");
                        this.logger.log(gVarA.m8clone().a(charset2));
                    }
                    if (lValueOf != null) {
                        Logger logger9 = this.logger;
                        StringBuilder sbA11 = a.a("<-- END HTTP (");
                        sbA11.append(gVarA.f2102c);
                        sbA11.append("-byte, ");
                        sbA11.append(lValueOf);
                        sbA11.append("-gzipped-byte body)");
                        logger9.log(sbA11.toString());
                    } else {
                        Logger logger10 = this.logger;
                        StringBuilder sbA12 = a.a("<-- END HTTP (");
                        sbA12.append(gVarA.f2102c);
                        sbA12.append("-byte body)");
                        logger10.log(sbA12.toString());
                    }
                }
            }
            return responseProceed;
        } catch (Exception e2) {
            this.logger.log("<-- HTTP FAILED: " + e2);
            throw e2;
        }
    }

    public void redactHeader(String str) {
        TreeSet treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        treeSet.addAll(this.headersToRedact);
        treeSet.add(str);
        this.headersToRedact = treeSet;
    }

    public HttpLoggingInterceptor setLevel(Level level) {
        if (level == null) {
            throw new NullPointerException("level == null. Use Level.NONE instead.");
        }
        this.level = level;
        return this;
    }

    public HttpLoggingInterceptor(Logger logger) {
        this.headersToRedact = Collections.emptySet();
        this.level = Level.NONE;
        this.logger = logger;
    }
}
