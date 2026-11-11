package com.lagradost.nicehttp;

import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ImagesContract;
import io.ktor.http.ContentDisposition;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import okhttp3.Headers;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal._UtilCommonKt;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/* JADX INFO: compiled from: NiceResponse.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0012\u0018\u0000 B2\u00020\u0001:\u0001BB\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u00105\u001a\u0002H6\"\n\b\u0000\u00106\u0018\u0001*\u00020\u0001H\u0086\b¢\u0006\u0002\u00107J\u001c\u00108\u001a\u0004\u0018\u0001H6\"\n\b\u0000\u00106\u0018\u0001*\u00020\u0001H\u0086\b¢\u0006\u0002\u00107J\u001a\u0010?\u001a\u0002H6\"\n\b\u0000\u00106\u0018\u0001*\u00020\u0001H\u0086\b¢\u0006\u0002\u00107J\u001c\u0010@\u001a\u0004\u0018\u0001H6\"\n\b\u0000\u00106\u0018\u0001*\u00020\u0001H\u0086\b¢\u0006\u0002\u00107J\n\u0010A\u001a\u00020\u000fH\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0014\u001a\u00020\u000f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0015\u0010\u0011R'\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u00188FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u0013\u001a\u0004\b\u0019\u0010\u001aR\u001b\u0010\u001c\u001a\u00020\u001d8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b \u0010\u0013\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010!\u001a\u00020\"¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010%\u001a\u00020&¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u001d\u0010)\u001a\u0004\u0018\u00010*8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b-\u0010\u0013\u001a\u0004\b+\u0010,R\u0011\u0010.\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u001b\u00100\u001a\u0002018FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b4\u0010\u0013\u001a\u0004\b2\u00103R\u001b\u00109\u001a\u00020\u000f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b;\u0010\u0013\u001a\u0004\b:\u0010\u0011R\u001b\u0010<\u001a\u0002018FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b>\u0010\u0013\u001a\u0004\b=\u00103¨\u0006C"}, d2 = {"Lcom/lagradost/nicehttp/NiceResponse;", "", "okhttpResponse", "Lokhttp3/Response;", "parser", "Lcom/lagradost/nicehttp/ResponseParser;", "<init>", "(Lokhttp3/Response;Lcom/lagradost/nicehttp/ResponseParser;)V", "getOkhttpResponse", "()Lokhttp3/Response;", "getParser", "()Lcom/lagradost/nicehttp/ResponseParser;", "consumedBody", "", "text", "", "getText", "()Ljava/lang/String;", "text$delegate", "Lkotlin/Lazy;", ImagesContract.URL, "getUrl", "url$delegate", "cookies", "", "getCookies", "()Ljava/util/Map;", "cookies$delegate", TtmlNode.TAG_BODY, "Lokhttp3/ResponseBody;", "getBody", "()Lokhttp3/ResponseBody;", "body$delegate", "code", "", "getCode", "()I", "headers", "Lokhttp3/Headers;", "getHeaders", "()Lokhttp3/Headers;", ContentDisposition.Parameters.Size, "", "getSize", "()Ljava/lang/Long;", "size$delegate", "isSuccessful", "()Z", "document", "Lorg/jsoup/nodes/Document;", "getDocument", "()Lorg/jsoup/nodes/Document;", "document$delegate", "parsed", ExifInterface.GPS_DIRECTION_TRUE, "()Ljava/lang/Object;", "parsedSafe", "textLarge", "getTextLarge", "textLarge$delegate", "documentLarge", "getDocumentLarge", "documentLarge$delegate", "parsedLarge", "parsedSafeLarge", "toString", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class NiceResponse {
    public static final long MAX_TEXT_SIZE = 5000000;

    /* JADX INFO: renamed from: body$delegate, reason: from kotlin metadata */
    private final Lazy body;
    private final int code;
    private boolean consumedBody;

    /* JADX INFO: renamed from: cookies$delegate, reason: from kotlin metadata */
    private final Lazy cookies;

    /* JADX INFO: renamed from: document$delegate, reason: from kotlin metadata */
    private final Lazy document;

    /* JADX INFO: renamed from: documentLarge$delegate, reason: from kotlin metadata */
    private final Lazy documentLarge;
    private final Headers headers;
    private final boolean isSuccessful;
    private final Response okhttpResponse;
    private final ResponseParser parser;

    /* JADX INFO: renamed from: size$delegate, reason: from kotlin metadata */
    private final Lazy size;

    /* JADX INFO: renamed from: text$delegate, reason: from kotlin metadata */
    private final Lazy text;

    /* JADX INFO: renamed from: textLarge$delegate, reason: from kotlin metadata */
    private final Lazy textLarge;

    /* JADX INFO: renamed from: url$delegate, reason: from kotlin metadata */
    private final Lazy url;

    public NiceResponse(Response okhttpResponse, ResponseParser responseParser) {
        Intrinsics.checkNotNullParameter(okhttpResponse, "okhttpResponse");
        this.okhttpResponse = okhttpResponse;
        this.parser = responseParser;
        this.text = LazyKt.lazy(new Function0() { // from class: com.lagradost.nicehttp.NiceResponse$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NiceResponse.text_delegate$lambda$0(this.f$0);
            }
        });
        this.url = LazyKt.lazy(new Function0() { // from class: com.lagradost.nicehttp.NiceResponse$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.okhttpResponse.request().url().getUrl();
            }
        });
        this.cookies = LazyKt.lazy(new Function0() { // from class: com.lagradost.nicehttp.NiceResponse$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NiceResponseKt.getCookies(this.f$0.okhttpResponse);
            }
        });
        this.body = LazyKt.lazy(new Function0() { // from class: com.lagradost.nicehttp.NiceResponse$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.okhttpResponse.body();
            }
        });
        this.code = okhttpResponse.code();
        this.headers = okhttpResponse.headers();
        this.size = LazyKt.lazy(new Function0() { // from class: com.lagradost.nicehttp.NiceResponse$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NiceResponse.size_delegate$lambda$0(this.f$0);
            }
        });
        this.isSuccessful = okhttpResponse.getIsSuccessful();
        this.document = LazyKt.lazy(new Function0() { // from class: com.lagradost.nicehttp.NiceResponse$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Jsoup.parse(this.f$0.getText());
            }
        });
        this.textLarge = LazyKt.lazy(new Function0() { // from class: com.lagradost.nicehttp.NiceResponse$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NiceResponse.textLarge_delegate$lambda$0(this.f$0);
            }
        });
        this.documentLarge = LazyKt.lazy(new Function0() { // from class: com.lagradost.nicehttp.NiceResponse$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Jsoup.parse(this.f$0.getTextLarge());
            }
        });
    }

    public final Response getOkhttpResponse() {
        return this.okhttpResponse;
    }

    public final ResponseParser getParser() {
        return this.parser;
    }

    public final String getText() {
        return (String) this.text.getValue();
    }

    static final String text_delegate$lambda$0(NiceResponse niceResponse) {
        synchronized (niceResponse) {
            boolean z = niceResponse.consumedBody;
            niceResponse.consumedBody = true;
            if (z) {
                return niceResponse.getTextLarge();
            }
            Long size = niceResponse.getSize();
            if (size != null && size.longValue() > 5000000) {
                throw new IllegalStateException("Called .text on a text file with Content-Length header > 5000000 bytes, this throws an exception to prevent OOM. If you know what you are doing, use .body/textLarge/documentLarge to download larger files");
            }
            return OkioHelper.INSTANCE.readLimited(niceResponse.getBody(), 5000000L);
        }
    }

    public final String getUrl() {
        return (String) this.url.getValue();
    }

    public final Map<String, String> getCookies() {
        return (Map) this.cookies.getValue();
    }

    public final ResponseBody getBody() {
        return (ResponseBody) this.body.getValue();
    }

    public final int getCode() {
        return this.code;
    }

    public final Headers getHeaders() {
        return this.headers;
    }

    public final Long getSize() {
        return (Long) this.size.getValue();
    }

    static final Long size_delegate$lambda$0(NiceResponse niceResponse) {
        String str = niceResponse.okhttpResponse.headers().get("content-length");
        if (str != null) {
            return StringsKt.toLongOrNull(str);
        }
        return null;
    }

    /* JADX INFO: renamed from: isSuccessful, reason: from getter */
    public final boolean getIsSuccessful() {
        return this.isSuccessful;
    }

    public final Document getDocument() {
        Object value = this.document.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (Document) value;
    }

    public final /* synthetic */ <T> T parsed() {
        ResponseParser parser = getParser();
        Intrinsics.checkNotNull(parser);
        String text = getText();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) parser.parse(text, Reflection.getOrCreateKotlinClass(Object.class));
    }

    public final /* synthetic */ <T> T parsedSafe() {
        try {
            ResponseParser parser = getParser();
            Intrinsics.checkNotNull(parser);
            String text = getText();
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            return (T) parser.parseSafe(text, Reflection.getOrCreateKotlinClass(Object.class));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final String getTextLarge() {
        return (String) this.textLarge.getValue();
    }

    static final String textLarge_delegate$lambda$0(NiceResponse niceResponse) {
        String strString;
        synchronized (niceResponse) {
            boolean z = niceResponse.consumedBody;
            niceResponse.consumedBody = true;
            if (z) {
                System.out.println((Object) "Warning: Using textLarge after body is already consumed. Defaulting to text.");
                strString = niceResponse.getText();
            } else {
                strString = niceResponse.getBody().string();
                _UtilCommonKt.closeQuietly(niceResponse.getBody());
            }
        }
        return strString;
    }

    public final Document getDocumentLarge() {
        Object value = this.documentLarge.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (Document) value;
    }

    public final /* synthetic */ <T> T parsedLarge() {
        ResponseParser parser = getParser();
        Intrinsics.checkNotNull(parser);
        String textLarge = getTextLarge();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) parser.parse(textLarge, Reflection.getOrCreateKotlinClass(Object.class));
    }

    public final /* synthetic */ <T> T parsedSafeLarge() {
        try {
            ResponseParser parser = getParser();
            Intrinsics.checkNotNull(parser);
            String textLarge = getTextLarge();
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            return (T) parser.parseSafe(textLarge, Reflection.getOrCreateKotlinClass(Object.class));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String toString() {
        return getText();
    }
}
