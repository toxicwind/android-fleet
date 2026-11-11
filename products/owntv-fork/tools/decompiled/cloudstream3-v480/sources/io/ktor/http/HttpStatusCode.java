package io.ktor.http;

import androidx.compose.runtime.ComposerKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.cast.MediaError;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: HttpStatusCode.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\b\u0086\b\u0018\u0000 \u001c2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001cB\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0011\u0010\b\u001a\u00020\u0004H\u0096\u0080\u0004¢\u0006\u0004\b\b\u0010\tJ\u001b\u0010\r\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0096\u0082\u0004¢\u0006\u0004\b\r\u0010\u000eJ\u0011\u0010\u000f\u001a\u00020\u0002H\u0096\u0080\u0004¢\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0011J\u0019\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0000H\u0096\u0082\u0004¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0014\u0010\u0010J\u0010\u0010\u0015\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u0015\u0010\tJ$\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004HÆ\u0001¢\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\u0010R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001a\u001a\u0004\b\u001b\u0010\t¨\u0006\u001d"}, d2 = {"Lio/ktor/http/HttpStatusCode;", "", "", ES6Iterator.VALUE_PROPERTY, "", "description", "<init>", "(ILjava/lang/String;)V", "toString", "()Ljava/lang/String;", "", "other", "", "equals", Signatures.MATH_TO_BOOLEAN, "hashCode", "()I", "(Ljava/lang/String;)Lio/ktor/http/HttpStatusCode;", "compareTo", "(Lio/ktor/http/HttpStatusCode;)I", "component1", "component2", "copy", "(ILjava/lang/String;)Lio/ktor/http/HttpStatusCode;", "I", "getValue", "Ljava/lang/String;", "getDescription", "Companion", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class HttpStatusCode implements Comparable<HttpStatusCode> {
    private static final List<HttpStatusCode> allStatusCodes;
    private static final Map<Integer, HttpStatusCode> statusCodesMap;
    private final String description;
    private final int value;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final HttpStatusCode Continue = new HttpStatusCode(100, "Continue");
    private static final HttpStatusCode SwitchingProtocols = new HttpStatusCode(101, "Switching Protocols");
    private static final HttpStatusCode Processing = new HttpStatusCode(102, "Processing");
    private static final HttpStatusCode OK = new HttpStatusCode(200, "OK");
    private static final HttpStatusCode Created = new HttpStatusCode(201, "Created");
    private static final HttpStatusCode Accepted = new HttpStatusCode(202, "Accepted");
    private static final HttpStatusCode NonAuthoritativeInformation = new HttpStatusCode(203, "Non-Authoritative Information");
    private static final HttpStatusCode NoContent = new HttpStatusCode(ComposerKt.providerMapsKey, "No Content");
    private static final HttpStatusCode ResetContent = new HttpStatusCode(205, "Reset Content");
    private static final HttpStatusCode PartialContent = new HttpStatusCode(ComposerKt.referenceKey, "Partial Content");
    private static final HttpStatusCode MultiStatus = new HttpStatusCode(ComposerKt.reuseKey, "Multi-Status");
    private static final HttpStatusCode MultipleChoices = new HttpStatusCode(300, "Multiple Choices");
    private static final HttpStatusCode MovedPermanently = new HttpStatusCode(301, "Moved Permanently");
    private static final HttpStatusCode Found = new HttpStatusCode(302, "Found");
    private static final HttpStatusCode SeeOther = new HttpStatusCode(303, "See Other");
    private static final HttpStatusCode NotModified = new HttpStatusCode(304, "Not Modified");
    private static final HttpStatusCode UseProxy = new HttpStatusCode(305, "Use Proxy");
    private static final HttpStatusCode SwitchProxy = new HttpStatusCode(306, "Switch Proxy");
    private static final HttpStatusCode TemporaryRedirect = new HttpStatusCode(307, "Temporary Redirect");
    private static final HttpStatusCode PermanentRedirect = new HttpStatusCode(308, "Permanent Redirect");
    private static final HttpStatusCode BadRequest = new HttpStatusCode(400, "Bad Request");
    private static final HttpStatusCode Unauthorized = new HttpStatusCode(TypedValues.CycleType.TYPE_CURVE_FIT, "Unauthorized");
    private static final HttpStatusCode PaymentRequired = new HttpStatusCode(TypedValues.CycleType.TYPE_VISIBILITY, "Payment Required");
    private static final HttpStatusCode Forbidden = new HttpStatusCode(TypedValues.CycleType.TYPE_ALPHA, "Forbidden");
    private static final HttpStatusCode NotFound = new HttpStatusCode(404, "Not Found");
    private static final HttpStatusCode MethodNotAllowed = new HttpStatusCode(405, "Method Not Allowed");
    private static final HttpStatusCode NotAcceptable = new HttpStatusCode(406, "Not Acceptable");
    private static final HttpStatusCode ProxyAuthenticationRequired = new HttpStatusCode(407, "Proxy Authentication Required");
    private static final HttpStatusCode RequestTimeout = new HttpStatusCode(408, "Request Timeout");
    private static final HttpStatusCode Conflict = new HttpStatusCode(409, "Conflict");
    private static final HttpStatusCode Gone = new HttpStatusCode(410, "Gone");
    private static final HttpStatusCode LengthRequired = new HttpStatusCode(MediaError.DetailedErrorCode.HLS_MANIFEST_MASTER, "Length Required");
    private static final HttpStatusCode PreconditionFailed = new HttpStatusCode(MediaError.DetailedErrorCode.HLS_MANIFEST_PLAYLIST, "Precondition Failed");
    private static final HttpStatusCode PayloadTooLarge = new HttpStatusCode(413, "Payload Too Large");
    private static final HttpStatusCode RequestURITooLong = new HttpStatusCode(414, "Request-URI Too Long");
    private static final HttpStatusCode UnsupportedMediaType = new HttpStatusCode(415, "Unsupported Media Type");
    private static final HttpStatusCode RequestedRangeNotSatisfiable = new HttpStatusCode(TypedValues.CycleType.TYPE_PATH_ROTATE, "Requested Range Not Satisfiable");
    private static final HttpStatusCode ExpectationFailed = new HttpStatusCode(417, "Expectation Failed");
    private static final HttpStatusCode UnprocessableEntity = new HttpStatusCode(422, "Unprocessable Entity");
    private static final HttpStatusCode Locked = new HttpStatusCode(423, "Locked");
    private static final HttpStatusCode FailedDependency = new HttpStatusCode(TypedValues.CycleType.TYPE_WAVE_OFFSET, "Failed Dependency");
    private static final HttpStatusCode TooEarly = new HttpStatusCode(TypedValues.CycleType.TYPE_WAVE_PHASE, "Too Early");
    private static final HttpStatusCode UpgradeRequired = new HttpStatusCode(426, "Upgrade Required");
    private static final HttpStatusCode TooManyRequests = new HttpStatusCode(429, "Too Many Requests");
    private static final HttpStatusCode RequestHeaderFieldTooLarge = new HttpStatusCode(MediaError.DetailedErrorCode.SMOOTH_MANIFEST, "Request Header Fields Too Large");
    private static final HttpStatusCode InternalServerError = new HttpStatusCode(500, "Internal Server Error");
    private static final HttpStatusCode NotImplemented = new HttpStatusCode(TypedValues.PositionType.TYPE_TRANSITION_EASING, "Not Implemented");
    private static final HttpStatusCode BadGateway = new HttpStatusCode(TypedValues.PositionType.TYPE_DRAWPATH, "Bad Gateway");
    private static final HttpStatusCode ServiceUnavailable = new HttpStatusCode(TypedValues.PositionType.TYPE_PERCENT_WIDTH, "Service Unavailable");
    private static final HttpStatusCode GatewayTimeout = new HttpStatusCode(TypedValues.PositionType.TYPE_PERCENT_HEIGHT, "Gateway Timeout");
    private static final HttpStatusCode VersionNotSupported = new HttpStatusCode(TypedValues.PositionType.TYPE_SIZE_PERCENT, "HTTP Version Not Supported");
    private static final HttpStatusCode VariantAlsoNegotiates = new HttpStatusCode(TypedValues.PositionType.TYPE_PERCENT_X, "Variant Also Negotiates");
    private static final HttpStatusCode InsufficientStorage = new HttpStatusCode(TypedValues.PositionType.TYPE_PERCENT_Y, "Insufficient Storage");

    public static /* synthetic */ HttpStatusCode copy$default(HttpStatusCode httpStatusCode, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = httpStatusCode.value;
        }
        if ((i2 & 2) != 0) {
            str = httpStatusCode.description;
        }
        return httpStatusCode.copy(i, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getValue() {
        return this.value;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    public final HttpStatusCode copy(int value, String description) {
        Intrinsics.checkNotNullParameter(description, "description");
        return new HttpStatusCode(value, description);
    }

    public HttpStatusCode(int i, String description) {
        Intrinsics.checkNotNullParameter(description, "description");
        this.value = i;
        this.description = description;
    }

    public final String getDescription() {
        return this.description;
    }

    public final int getValue() {
        return this.value;
    }

    public String toString() {
        return this.value + ' ' + this.description;
    }

    public boolean equals(Object other) {
        return (other instanceof HttpStatusCode) && ((HttpStatusCode) other).value == this.value;
    }

    public int hashCode() {
        return this.value;
    }

    public final HttpStatusCode description(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return copy$default(this, 0, value, 1, null);
    }

    @Override // java.lang.Comparable
    public int compareTo(HttpStatusCode other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return this.value - other.value;
    }

    /* JADX INFO: compiled from: HttpStatusCode.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\bn\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u000f\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\fR\u0017\u0010\u0011\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0011\u0010\n\u001a\u0004\b\u0012\u0010\fR\u0017\u0010\u0013\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0013\u0010\n\u001a\u0004\b\u0014\u0010\fR\u0017\u0010\u0015\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0015\u0010\n\u001a\u0004\b\u0016\u0010\fR\u0017\u0010\u0017\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0017\u0010\n\u001a\u0004\b\u0018\u0010\fR\u0017\u0010\u0019\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0019\u0010\n\u001a\u0004\b\u001a\u0010\fR\u0017\u0010\u001b\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u001b\u0010\n\u001a\u0004\b\u001c\u0010\fR\u0017\u0010\u001d\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u001d\u0010\n\u001a\u0004\b\u001e\u0010\fR\u0017\u0010\u001f\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u001f\u0010\n\u001a\u0004\b \u0010\fR\u0017\u0010!\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b!\u0010\n\u001a\u0004\b\"\u0010\fR\u0017\u0010#\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b#\u0010\n\u001a\u0004\b$\u0010\fR\u0017\u0010%\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b%\u0010\n\u001a\u0004\b&\u0010\fR\u0017\u0010'\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b'\u0010\n\u001a\u0004\b(\u0010\fR\u0017\u0010)\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b)\u0010\n\u001a\u0004\b*\u0010\fR\u0017\u0010+\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b+\u0010\n\u001a\u0004\b,\u0010\fR\u0017\u0010-\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b-\u0010\n\u001a\u0004\b.\u0010\fR\u0017\u0010/\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b/\u0010\n\u001a\u0004\b0\u0010\fR\u0017\u00101\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b1\u0010\n\u001a\u0004\b2\u0010\fR\u0017\u00103\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b3\u0010\n\u001a\u0004\b4\u0010\fR\u0017\u00105\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b5\u0010\n\u001a\u0004\b6\u0010\fR\u0017\u00107\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b7\u0010\n\u001a\u0004\b8\u0010\fR\u0017\u00109\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b9\u0010\n\u001a\u0004\b:\u0010\fR\u0017\u0010;\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b;\u0010\n\u001a\u0004\b<\u0010\fR\u0017\u0010=\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b=\u0010\n\u001a\u0004\b>\u0010\fR\u0017\u0010?\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b?\u0010\n\u001a\u0004\b@\u0010\fR\u0017\u0010A\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\bA\u0010\n\u001a\u0004\bB\u0010\fR\u0017\u0010C\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\bC\u0010\n\u001a\u0004\bD\u0010\fR\u0017\u0010E\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\bE\u0010\n\u001a\u0004\bF\u0010\fR\u0017\u0010G\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\bG\u0010\n\u001a\u0004\bH\u0010\fR\u0017\u0010I\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\bI\u0010\n\u001a\u0004\bJ\u0010\fR\u0017\u0010K\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\bK\u0010\n\u001a\u0004\bL\u0010\fR\u0017\u0010M\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\bM\u0010\n\u001a\u0004\bN\u0010\fR\u0017\u0010O\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\bO\u0010\n\u001a\u0004\bP\u0010\fR\u0017\u0010Q\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\bQ\u0010\n\u001a\u0004\bR\u0010\fR\u0017\u0010S\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\bS\u0010\n\u001a\u0004\bT\u0010\fR\u0017\u0010U\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\bU\u0010\n\u001a\u0004\bV\u0010\fR\u0017\u0010W\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\bW\u0010\n\u001a\u0004\bX\u0010\fR\u0017\u0010Y\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\bY\u0010\n\u001a\u0004\bZ\u0010\fR\u0017\u0010[\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b[\u0010\n\u001a\u0004\b\\\u0010\fR\u0017\u0010]\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b]\u0010\n\u001a\u0004\b^\u0010\fR\u0017\u0010_\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b_\u0010\n\u001a\u0004\b`\u0010\fR\u0017\u0010a\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\ba\u0010\n\u001a\u0004\bb\u0010\fR\u0017\u0010c\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\bc\u0010\n\u001a\u0004\bd\u0010\fR\u0017\u0010e\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\be\u0010\n\u001a\u0004\bf\u0010\fR\u0017\u0010g\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\bg\u0010\n\u001a\u0004\bh\u0010\fR\u0017\u0010i\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\bi\u0010\n\u001a\u0004\bj\u0010\fR\u0017\u0010k\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\bk\u0010\n\u001a\u0004\bl\u0010\fR\u0017\u0010m\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\bm\u0010\n\u001a\u0004\bn\u0010\fR\u0017\u0010o\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\bo\u0010\n\u001a\u0004\bp\u0010\fR\u0017\u0010q\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\bq\u0010\n\u001a\u0004\br\u0010\fR\u0017\u0010s\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\bs\u0010\n\u001a\u0004\bt\u0010\fR\u001d\u0010v\u001a\b\u0012\u0004\u0012\u00020\u00060u8\u0006¢\u0006\f\n\u0004\bv\u0010w\u001a\u0004\bx\u0010yR \u0010{\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060z8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b{\u0010|¨\u0006}"}, d2 = {"Lio/ktor/http/HttpStatusCode$Companion;", "", "<init>", "()V", "", ES6Iterator.VALUE_PROPERTY, "Lio/ktor/http/HttpStatusCode;", "fromValue", "(I)Lio/ktor/http/HttpStatusCode;", "Continue", "Lio/ktor/http/HttpStatusCode;", "getContinue", "()Lio/ktor/http/HttpStatusCode;", "SwitchingProtocols", "getSwitchingProtocols", "Processing", "getProcessing", "OK", "getOK", "Created", "getCreated", "Accepted", "getAccepted", "NonAuthoritativeInformation", "getNonAuthoritativeInformation", "NoContent", "getNoContent", "ResetContent", "getResetContent", "PartialContent", "getPartialContent", "MultiStatus", "getMultiStatus", "MultipleChoices", "getMultipleChoices", "MovedPermanently", "getMovedPermanently", "Found", "getFound", "SeeOther", "getSeeOther", "NotModified", "getNotModified", "UseProxy", "getUseProxy", "SwitchProxy", "getSwitchProxy", "TemporaryRedirect", "getTemporaryRedirect", "PermanentRedirect", "getPermanentRedirect", "BadRequest", "getBadRequest", "Unauthorized", "getUnauthorized", "PaymentRequired", "getPaymentRequired", "Forbidden", "getForbidden", "NotFound", "getNotFound", "MethodNotAllowed", "getMethodNotAllowed", "NotAcceptable", "getNotAcceptable", "ProxyAuthenticationRequired", "getProxyAuthenticationRequired", "RequestTimeout", "getRequestTimeout", "Conflict", "getConflict", "Gone", "getGone", "LengthRequired", "getLengthRequired", "PreconditionFailed", "getPreconditionFailed", "PayloadTooLarge", "getPayloadTooLarge", "RequestURITooLong", "getRequestURITooLong", "UnsupportedMediaType", "getUnsupportedMediaType", "RequestedRangeNotSatisfiable", "getRequestedRangeNotSatisfiable", "ExpectationFailed", "getExpectationFailed", "UnprocessableEntity", "getUnprocessableEntity", "Locked", "getLocked", "FailedDependency", "getFailedDependency", "TooEarly", "getTooEarly", "UpgradeRequired", "getUpgradeRequired", "TooManyRequests", "getTooManyRequests", "RequestHeaderFieldTooLarge", "getRequestHeaderFieldTooLarge", "InternalServerError", "getInternalServerError", "NotImplemented", "getNotImplemented", "BadGateway", "getBadGateway", "ServiceUnavailable", "getServiceUnavailable", "GatewayTimeout", "getGatewayTimeout", "VersionNotSupported", "getVersionNotSupported", "VariantAlsoNegotiates", "getVariantAlsoNegotiates", "InsufficientStorage", "getInsufficientStorage", "", "allStatusCodes", "Ljava/util/List;", "getAllStatusCodes", "()Ljava/util/List;", "", "statusCodesMap", "Ljava/util/Map;", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final HttpStatusCode getContinue() {
            return HttpStatusCode.Continue;
        }

        public final HttpStatusCode getSwitchingProtocols() {
            return HttpStatusCode.SwitchingProtocols;
        }

        public final HttpStatusCode getProcessing() {
            return HttpStatusCode.Processing;
        }

        public final HttpStatusCode getOK() {
            return HttpStatusCode.OK;
        }

        public final HttpStatusCode getCreated() {
            return HttpStatusCode.Created;
        }

        public final HttpStatusCode getAccepted() {
            return HttpStatusCode.Accepted;
        }

        public final HttpStatusCode getNonAuthoritativeInformation() {
            return HttpStatusCode.NonAuthoritativeInformation;
        }

        public final HttpStatusCode getNoContent() {
            return HttpStatusCode.NoContent;
        }

        public final HttpStatusCode getResetContent() {
            return HttpStatusCode.ResetContent;
        }

        public final HttpStatusCode getPartialContent() {
            return HttpStatusCode.PartialContent;
        }

        public final HttpStatusCode getMultiStatus() {
            return HttpStatusCode.MultiStatus;
        }

        public final HttpStatusCode getMultipleChoices() {
            return HttpStatusCode.MultipleChoices;
        }

        public final HttpStatusCode getMovedPermanently() {
            return HttpStatusCode.MovedPermanently;
        }

        public final HttpStatusCode getFound() {
            return HttpStatusCode.Found;
        }

        public final HttpStatusCode getSeeOther() {
            return HttpStatusCode.SeeOther;
        }

        public final HttpStatusCode getNotModified() {
            return HttpStatusCode.NotModified;
        }

        public final HttpStatusCode getUseProxy() {
            return HttpStatusCode.UseProxy;
        }

        public final HttpStatusCode getSwitchProxy() {
            return HttpStatusCode.SwitchProxy;
        }

        public final HttpStatusCode getTemporaryRedirect() {
            return HttpStatusCode.TemporaryRedirect;
        }

        public final HttpStatusCode getPermanentRedirect() {
            return HttpStatusCode.PermanentRedirect;
        }

        public final HttpStatusCode getBadRequest() {
            return HttpStatusCode.BadRequest;
        }

        public final HttpStatusCode getUnauthorized() {
            return HttpStatusCode.Unauthorized;
        }

        public final HttpStatusCode getPaymentRequired() {
            return HttpStatusCode.PaymentRequired;
        }

        public final HttpStatusCode getForbidden() {
            return HttpStatusCode.Forbidden;
        }

        public final HttpStatusCode getNotFound() {
            return HttpStatusCode.NotFound;
        }

        public final HttpStatusCode getMethodNotAllowed() {
            return HttpStatusCode.MethodNotAllowed;
        }

        public final HttpStatusCode getNotAcceptable() {
            return HttpStatusCode.NotAcceptable;
        }

        public final HttpStatusCode getProxyAuthenticationRequired() {
            return HttpStatusCode.ProxyAuthenticationRequired;
        }

        public final HttpStatusCode getRequestTimeout() {
            return HttpStatusCode.RequestTimeout;
        }

        public final HttpStatusCode getConflict() {
            return HttpStatusCode.Conflict;
        }

        public final HttpStatusCode getGone() {
            return HttpStatusCode.Gone;
        }

        public final HttpStatusCode getLengthRequired() {
            return HttpStatusCode.LengthRequired;
        }

        public final HttpStatusCode getPreconditionFailed() {
            return HttpStatusCode.PreconditionFailed;
        }

        public final HttpStatusCode getPayloadTooLarge() {
            return HttpStatusCode.PayloadTooLarge;
        }

        public final HttpStatusCode getRequestURITooLong() {
            return HttpStatusCode.RequestURITooLong;
        }

        public final HttpStatusCode getUnsupportedMediaType() {
            return HttpStatusCode.UnsupportedMediaType;
        }

        public final HttpStatusCode getRequestedRangeNotSatisfiable() {
            return HttpStatusCode.RequestedRangeNotSatisfiable;
        }

        public final HttpStatusCode getExpectationFailed() {
            return HttpStatusCode.ExpectationFailed;
        }

        public final HttpStatusCode getUnprocessableEntity() {
            return HttpStatusCode.UnprocessableEntity;
        }

        public final HttpStatusCode getLocked() {
            return HttpStatusCode.Locked;
        }

        public final HttpStatusCode getFailedDependency() {
            return HttpStatusCode.FailedDependency;
        }

        public final HttpStatusCode getTooEarly() {
            return HttpStatusCode.TooEarly;
        }

        public final HttpStatusCode getUpgradeRequired() {
            return HttpStatusCode.UpgradeRequired;
        }

        public final HttpStatusCode getTooManyRequests() {
            return HttpStatusCode.TooManyRequests;
        }

        public final HttpStatusCode getRequestHeaderFieldTooLarge() {
            return HttpStatusCode.RequestHeaderFieldTooLarge;
        }

        public final HttpStatusCode getInternalServerError() {
            return HttpStatusCode.InternalServerError;
        }

        public final HttpStatusCode getNotImplemented() {
            return HttpStatusCode.NotImplemented;
        }

        public final HttpStatusCode getBadGateway() {
            return HttpStatusCode.BadGateway;
        }

        public final HttpStatusCode getServiceUnavailable() {
            return HttpStatusCode.ServiceUnavailable;
        }

        public final HttpStatusCode getGatewayTimeout() {
            return HttpStatusCode.GatewayTimeout;
        }

        public final HttpStatusCode getVersionNotSupported() {
            return HttpStatusCode.VersionNotSupported;
        }

        public final HttpStatusCode getVariantAlsoNegotiates() {
            return HttpStatusCode.VariantAlsoNegotiates;
        }

        public final HttpStatusCode getInsufficientStorage() {
            return HttpStatusCode.InsufficientStorage;
        }

        public final List<HttpStatusCode> getAllStatusCodes() {
            return HttpStatusCode.allStatusCodes;
        }

        public final HttpStatusCode fromValue(int value) {
            HttpStatusCode httpStatusCode = (HttpStatusCode) HttpStatusCode.statusCodesMap.get(Integer.valueOf(value));
            return httpStatusCode == null ? new HttpStatusCode(value, "Unknown Status Code") : httpStatusCode;
        }
    }

    static {
        List<HttpStatusCode> listAllStatusCodes = HttpStatusCodeKt.allStatusCodes();
        allStatusCodes = listAllStatusCodes;
        List<HttpStatusCode> list = listAllStatusCodes;
        LinkedHashMap linkedHashMap = new LinkedHashMap(kotlin.ranges.RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list, 10)), 16));
        for (Object obj : list) {
            linkedHashMap.put(Integer.valueOf(((HttpStatusCode) obj).value), obj);
        }
        statusCodesMap = linkedHashMap;
    }
}
