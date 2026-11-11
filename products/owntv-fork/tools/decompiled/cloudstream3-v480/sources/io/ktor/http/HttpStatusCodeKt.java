package io.ktor.http;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HttpStatusCode.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u0015\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0000H\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0011\u0010\u0005\u001a\u00020\u0004*\u00020\u0001¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"", "Lio/ktor/http/HttpStatusCode;", "allStatusCodes", "()Ljava/util/List;", "", "isSuccess", "(Lio/ktor/http/HttpStatusCode;)Z", "ktor-http"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class HttpStatusCodeKt {
    public static final List<HttpStatusCode> allStatusCodes() {
        return CollectionsKt.listOf((Object[]) new HttpStatusCode[]{HttpStatusCode.INSTANCE.getContinue(), HttpStatusCode.INSTANCE.getSwitchingProtocols(), HttpStatusCode.INSTANCE.getProcessing(), HttpStatusCode.INSTANCE.getOK(), HttpStatusCode.INSTANCE.getCreated(), HttpStatusCode.INSTANCE.getAccepted(), HttpStatusCode.INSTANCE.getNonAuthoritativeInformation(), HttpStatusCode.INSTANCE.getNoContent(), HttpStatusCode.INSTANCE.getResetContent(), HttpStatusCode.INSTANCE.getPartialContent(), HttpStatusCode.INSTANCE.getMultiStatus(), HttpStatusCode.INSTANCE.getMultipleChoices(), HttpStatusCode.INSTANCE.getMovedPermanently(), HttpStatusCode.INSTANCE.getFound(), HttpStatusCode.INSTANCE.getSeeOther(), HttpStatusCode.INSTANCE.getNotModified(), HttpStatusCode.INSTANCE.getUseProxy(), HttpStatusCode.INSTANCE.getSwitchProxy(), HttpStatusCode.INSTANCE.getTemporaryRedirect(), HttpStatusCode.INSTANCE.getPermanentRedirect(), HttpStatusCode.INSTANCE.getBadRequest(), HttpStatusCode.INSTANCE.getUnauthorized(), HttpStatusCode.INSTANCE.getPaymentRequired(), HttpStatusCode.INSTANCE.getForbidden(), HttpStatusCode.INSTANCE.getNotFound(), HttpStatusCode.INSTANCE.getMethodNotAllowed(), HttpStatusCode.INSTANCE.getNotAcceptable(), HttpStatusCode.INSTANCE.getProxyAuthenticationRequired(), HttpStatusCode.INSTANCE.getRequestTimeout(), HttpStatusCode.INSTANCE.getConflict(), HttpStatusCode.INSTANCE.getGone(), HttpStatusCode.INSTANCE.getLengthRequired(), HttpStatusCode.INSTANCE.getPreconditionFailed(), HttpStatusCode.INSTANCE.getPayloadTooLarge(), HttpStatusCode.INSTANCE.getRequestURITooLong(), HttpStatusCode.INSTANCE.getUnsupportedMediaType(), HttpStatusCode.INSTANCE.getRequestedRangeNotSatisfiable(), HttpStatusCode.INSTANCE.getExpectationFailed(), HttpStatusCode.INSTANCE.getUnprocessableEntity(), HttpStatusCode.INSTANCE.getLocked(), HttpStatusCode.INSTANCE.getFailedDependency(), HttpStatusCode.INSTANCE.getTooEarly(), HttpStatusCode.INSTANCE.getUpgradeRequired(), HttpStatusCode.INSTANCE.getTooManyRequests(), HttpStatusCode.INSTANCE.getRequestHeaderFieldTooLarge(), HttpStatusCode.INSTANCE.getInternalServerError(), HttpStatusCode.INSTANCE.getNotImplemented(), HttpStatusCode.INSTANCE.getBadGateway(), HttpStatusCode.INSTANCE.getServiceUnavailable(), HttpStatusCode.INSTANCE.getGatewayTimeout(), HttpStatusCode.INSTANCE.getVersionNotSupported(), HttpStatusCode.INSTANCE.getVariantAlsoNegotiates(), HttpStatusCode.INSTANCE.getInsufficientStorage()});
    }

    public static final boolean isSuccess(HttpStatusCode httpStatusCode) {
        Intrinsics.checkNotNullParameter(httpStatusCode, "<this>");
        int value = httpStatusCode.getValue();
        return 200 <= value && value < 300;
    }
}
