package com.lagradost.cloudstream3;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.downloader.Request;
import org.schabi.newpipe.extractor.downloader.Response;
import org.schabi.newpipe.extractor.exceptions.ReCaptchaException;

/* JADX INFO: compiled from: DownloaderTestImpl.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/lagradost/cloudstream3/DownloaderTestImpl;", "Lorg/schabi/newpipe/extractor/downloader/Downloader;", "builder", "Lokhttp3/OkHttpClient$Builder;", "<init>", "(Lokhttp3/OkHttpClient$Builder;)V", "client", "Lokhttp3/OkHttpClient;", "execute", "Lorg/schabi/newpipe/extractor/downloader/Response;", "request", "Lorg/schabi/newpipe/extractor/downloader/Request;", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DownloaderTestImpl extends Downloader {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36";
    private static DownloaderTestImpl instance;
    private final OkHttpClient client;

    public /* synthetic */ DownloaderTestImpl(OkHttpClient.Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder);
    }

    private DownloaderTestImpl(OkHttpClient.Builder builder) {
        this.client = builder.readTimeout(30L, TimeUnit.SECONDS).build();
    }

    @Override // org.schabi.newpipe.extractor.downloader.Downloader
    public Response execute(Request request) throws ReCaptchaException, IOException {
        Intrinsics.checkNotNullParameter(request, "request");
        String strHttpMethod = request.httpMethod();
        Intrinsics.checkNotNullExpressionValue(strHttpMethod, "httpMethod(...)");
        String strUrl = request.url();
        Intrinsics.checkNotNullExpressionValue(strUrl, "url(...)");
        Map<String, List<String>> mapHeaders = request.headers();
        Intrinsics.checkNotNullExpressionValue(mapHeaders, "headers(...)");
        byte[] bArrDataToSend = request.dataToSend();
        okhttp3.Request.Builder builderAddHeader = new okhttp3.Request.Builder().method(strHttpMethod, bArrDataToSend != null ? RequestBody.INSTANCE.create(bArrDataToSend, (MediaType) null, 0, bArrDataToSend.length) : null).url(strUrl).addHeader("User-Agent", USER_AGENT);
        for (Map.Entry<String, List<String>> entry : mapHeaders.entrySet()) {
            String key = entry.getKey();
            List<String> value = entry.getValue();
            if (value.size() > 1) {
                builderAddHeader.removeHeader(key);
                Iterator<String> it = value.iterator();
                while (it.hasNext()) {
                    builderAddHeader.addHeader(key, it.next());
                }
            } else if (value.size() == 1) {
                builderAddHeader.header(key, value.get(0));
            }
        }
        okhttp3.Response responseExecute = this.client.newCall(builderAddHeader.build()).execute();
        if (responseExecute.code() == 429) {
            responseExecute.close();
            throw new ReCaptchaException("reCaptcha Challenge requested", strUrl);
        }
        return new Response(responseExecute.code(), responseExecute.message(), responseExecute.headers().toMultimap(), responseExecute.body().string(), responseExecute.request().url().getUrl());
    }

    /* JADX INFO: compiled from: DownloaderTestImpl.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010\t\u001a\u0004\u0018\u00010\nJ\b\u0010\u000b\u001a\u0004\u0018\u00010\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/lagradost/cloudstream3/DownloaderTestImpl$Companion;", "", "<init>", "()V", "USER_AGENT", "", "instance", "Lcom/lagradost/cloudstream3/DownloaderTestImpl;", "init", "builder", "Lokhttp3/OkHttpClient$Builder;", "getInstance", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DownloaderTestImpl init(OkHttpClient.Builder builder) {
            if (builder == null) {
                builder = new OkHttpClient.Builder();
            }
            DownloaderTestImpl.instance = new DownloaderTestImpl(builder, null);
            return DownloaderTestImpl.instance;
        }

        public final DownloaderTestImpl getInstance() {
            if (DownloaderTestImpl.instance == null) {
                init(null);
            }
            return DownloaderTestImpl.instance;
        }
    }
}
