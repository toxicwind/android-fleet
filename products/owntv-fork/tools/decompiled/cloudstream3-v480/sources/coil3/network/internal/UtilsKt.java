package coil3.network.internal;

import androidx.media3.extractor.text.ttml.TtmlNode;
import coil3.disk.DiskCache;
import coil3.network.NetworkHeaders;
import coil3.network.NetworkResponse;
import coil3.network.NetworkResponseBody;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import okio.Buffer;

/* JADX INFO: compiled from: utils.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\f\u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0000\u001a\u0012\u0010\u0007\u001a\u00020\b*\u00020\tH\u0080@¢\u0006\u0002\u0010\n\u001a\u0015\u0010\u0012\u001a\u00020\u0013*\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0080\u0002\u001a\f\u0010\u0015\u001a\u00020\t*\u00020\u0016H\u0000\u001a\u0010\u0010\u0017\u001a\u00020\u0005*\u00060\u0018j\u0002`\u0019H\u0000\"\u000e\u0010\u000b\u001a\u00020\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0010X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\u0010X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"append", "Lcoil3/network/NetworkHeaders$Builder;", "line", "", "abortQuietly", "", "Lcoil3/disk/DiskCache$Editor;", "readBuffer", "Lokio/Buffer;", "Lcoil3/network/NetworkResponseBody;", "(Lcoil3/network/NetworkResponseBody;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "CACHE_CONTROL", "CONTENT_TYPE", "HTTP_METHOD_GET", "MIME_TYPE_TEXT_PLAIN", "HTTP_RESPONSE_OK", "", "HTTP_RESPONSE_NOT_MODIFIED", "plus", "Lcoil3/network/NetworkHeaders;", "other", "requireBody", "Lcoil3/network/NetworkResponse;", "closeQuietly", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "coil-network-core_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class UtilsKt {
    public static final String CACHE_CONTROL = "Cache-Control";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String HTTP_METHOD_GET = "GET";
    public static final int HTTP_RESPONSE_NOT_MODIFIED = 304;
    public static final int HTTP_RESPONSE_OK = 200;
    public static final String MIME_TYPE_TEXT_PLAIN = "text/plain";

    /* JADX INFO: renamed from: coil3.network.internal.UtilsKt$readBuffer$1, reason: invalid class name */
    /* JADX INFO: compiled from: utils.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "coil3.network.internal.UtilsKt", f = "utils.kt", i = {0, 0, 0, 0}, l = {31}, m = "readBuffer", n = {"$this$readBuffer", TtmlNode.TAG_BODY, "buffer", "$i$a$-use-UtilsKt$readBuffer$2"}, s = {"L$0", "L$2", "L$3", "I$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return UtilsKt.readBuffer(null, this);
        }
    }

    public static final NetworkHeaders.Builder append(NetworkHeaders.Builder builder, String str) {
        int iIndexOf$default = StringsKt.indexOf$default((CharSequence) str, AbstractJsonLexerKt.COLON, 0, false, 6, (Object) null);
        if (iIndexOf$default == -1) {
            throw new IllegalArgumentException(("Unexpected header: " + str).toString());
        }
        String strSubstring = str.substring(0, iIndexOf$default);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        String string = StringsKt.trim((CharSequence) strSubstring).toString();
        String strSubstring2 = str.substring(iIndexOf$default + 1);
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
        builder.add(string, strSubstring2);
        return builder;
    }

    public static final void abortQuietly(DiskCache.Editor editor) {
        try {
            editor.abort();
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object readBuffer(NetworkResponseBody networkResponseBody, Continuation<? super Buffer> continuation) {
        AnonymousClass1 anonymousClass1;
        AutoCloseable autoCloseable;
        Buffer buffer;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = anonymousClass1.I$0;
            buffer = (Buffer) anonymousClass1.L$3;
            autoCloseable = (AutoCloseable) anonymousClass1.L$1;
            try {
                ResultKt.throwOnFailure(obj);
                AutoCloseableKt.closeFinally(autoCloseable, null);
                return buffer;
            } catch (Throwable th) {
                th = th;
                try {
                    throw th;
                } catch (Throwable th2) {
                    AutoCloseableKt.closeFinally(autoCloseable, th);
                    throw th2;
                }
            }
        }
        ResultKt.throwOnFailure(obj);
        NetworkResponseBody networkResponseBody2 = networkResponseBody;
        try {
            NetworkResponseBody networkResponseBody3 = networkResponseBody2;
            Buffer buffer2 = new Buffer();
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(networkResponseBody);
            anonymousClass1.L$1 = networkResponseBody2;
            anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(networkResponseBody3);
            anonymousClass1.L$3 = buffer2;
            anonymousClass1.I$0 = 0;
            anonymousClass1.label = 1;
            if (networkResponseBody3.writeTo(buffer2, anonymousClass1) == coroutine_suspended) {
                return coroutine_suspended;
            }
            autoCloseable = networkResponseBody2;
            buffer = buffer2;
            AutoCloseableKt.closeFinally(autoCloseable, null);
            return buffer;
        } catch (Throwable th3) {
            th = th3;
            autoCloseable = networkResponseBody2;
            throw th;
        }
    }

    public static final NetworkHeaders plus(NetworkHeaders networkHeaders, NetworkHeaders networkHeaders2) {
        NetworkHeaders.Builder builderNewBuilder = networkHeaders.newBuilder();
        for (Map.Entry<String, List<String>> entry : networkHeaders2.asMap().entrySet()) {
            builderNewBuilder.set(entry.getKey(), entry.getValue());
        }
        return builderNewBuilder.build();
    }

    public static final NetworkResponseBody requireBody(NetworkResponse networkResponse) {
        NetworkResponseBody body = networkResponse.getBody();
        if (body != null) {
            return body;
        }
        throw new IllegalStateException("body == null".toString());
    }

    public static final void closeQuietly(AutoCloseable autoCloseable) {
        try {
            UByte$$ExternalSyntheticBackport0.m9014m((Object) autoCloseable);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception unused) {
        }
    }
}
