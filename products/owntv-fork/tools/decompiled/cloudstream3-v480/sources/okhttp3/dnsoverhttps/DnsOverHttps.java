package okhttp3.dnsoverhttps;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dns;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.publicsuffix.PublicSuffixDatabase;
import okio.ByteString;

/* JADX INFO: compiled from: DnsOverHttps.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 *2\u00020\u0001:\u0002)*B9\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007¢\u0006\u0004\b\u000b\u0010\fJ\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J>\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\u00142\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00112\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00120\u001b2\u0010\u0010\u001c\u001a\f\u0012\b\u0012\u00060\u001dj\u0002`\u001e0\u001bH\u0002J8\u0010\u001f\u001a\u00020\u00172\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0013\u001a\u00020\u00142\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00120\u001b2\u0010\u0010\u001c\u001a\f\u0012\b\u0012\u00060\u001dj\u0002`\u001e0\u001bH\u0002J(\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0010\u0010\u001c\u001a\f\u0012\b\u0012\u00060\u001dj\u0002`\u001e0\u0011H\u0002J\u001e\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010 \u001a\u00020!H\u0002J\u0018\u0010%\u001a\u00020&2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010'\u001a\u00020(H\u0002R\u0013\u0010\u0002\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\rR\u0013\u0010\u0004\u001a\u00020\u00058\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000eR\u0013\u0010\u0006\u001a\u00020\u00078\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000fR\u0013\u0010\b\u001a\u00020\u00078\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u000fR\u0013\u0010\t\u001a\u00020\u00078\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u000fR\u0013\u0010\n\u001a\u00020\u00078\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000f¨\u0006+"}, d2 = {"Lokhttp3/dnsoverhttps/DnsOverHttps;", "Lokhttp3/Dns;", "client", "Lokhttp3/OkHttpClient;", ImagesContract.URL, "Lokhttp3/HttpUrl;", "includeIPv6", "", "post", "resolvePrivateAddresses", "resolvePublicAddresses", "<init>", "(Lokhttp3/OkHttpClient;Lokhttp3/HttpUrl;ZZZZ)V", "()Lokhttp3/OkHttpClient;", "()Lokhttp3/HttpUrl;", "()Z", "lookup", "", "Ljava/net/InetAddress;", "hostname", "", "lookupHttps", "executeRequests", "", "networkRequests", "Lokhttp3/Call;", "responses", "", "failures", "Ljava/lang/Exception;", "Lkotlin/Exception;", "processResponse", "response", "Lokhttp3/Response;", "results", "throwBestFailure", "readResponse", "buildRequest", "Lokhttp3/Request;", "type", "", "Builder", "Companion", "okhttp-dnsoverhttps"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DnsOverHttps implements Dns {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final MediaType DNS_MESSAGE = MediaType.INSTANCE.get("application/dns-message");
    public static final int MAX_RESPONSE_SIZE = 65536;
    private final OkHttpClient client;
    private final boolean includeIPv6;
    private final boolean post;
    private final boolean resolvePrivateAddresses;
    private final boolean resolvePublicAddresses;
    private final HttpUrl url;

    public DnsOverHttps(OkHttpClient client, HttpUrl url, boolean z, boolean z2, boolean z3, boolean z4) {
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(url, "url");
        this.client = client;
        this.url = url;
        this.includeIPv6 = z;
        this.post = z2;
        this.resolvePrivateAddresses = z3;
        this.resolvePublicAddresses = z4;
    }

    /* JADX INFO: renamed from: client, reason: from getter */
    public final OkHttpClient getClient() {
        return this.client;
    }

    /* JADX INFO: renamed from: url, reason: from getter */
    public final HttpUrl getUrl() {
        return this.url;
    }

    /* JADX INFO: renamed from: includeIPv6, reason: from getter */
    public final boolean getIncludeIPv6() {
        return this.includeIPv6;
    }

    /* JADX INFO: renamed from: post, reason: from getter */
    public final boolean getPost() {
        return this.post;
    }

    /* JADX INFO: renamed from: resolvePrivateAddresses, reason: from getter */
    public final boolean getResolvePrivateAddresses() {
        return this.resolvePrivateAddresses;
    }

    /* JADX INFO: renamed from: resolvePublicAddresses, reason: from getter */
    public final boolean getResolvePublicAddresses() {
        return this.resolvePublicAddresses;
    }

    @Override // okhttp3.Dns
    public List<InetAddress> lookup(String hostname) throws UnknownHostException {
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        if (!this.resolvePrivateAddresses || !this.resolvePublicAddresses) {
            boolean zIsPrivateHost$okhttp_dnsoverhttps = INSTANCE.isPrivateHost$okhttp_dnsoverhttps(hostname);
            if (zIsPrivateHost$okhttp_dnsoverhttps && !this.resolvePrivateAddresses) {
                throw new UnknownHostException("private hosts not resolved");
            }
            if (!zIsPrivateHost$okhttp_dnsoverhttps && !this.resolvePublicAddresses) {
                throw new UnknownHostException("public hosts not resolved");
            }
        }
        return lookupHttps(hostname);
    }

    private final List<InetAddress> lookupHttps(String hostname) throws Exception {
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        listCreateListBuilder.add(this.client.newCall(buildRequest(hostname, 1)));
        if (this.includeIPv6) {
            listCreateListBuilder.add(this.client.newCall(buildRequest(hostname, 28)));
        }
        List<? extends Call> listBuild = CollectionsKt.build(listCreateListBuilder);
        ArrayList arrayList = new ArrayList(2);
        ArrayList arrayList2 = new ArrayList(5);
        ArrayList arrayList3 = arrayList;
        executeRequests(hostname, listBuild, arrayList2, arrayList3);
        ArrayList arrayListThrowBestFailure = arrayList2;
        if (arrayListThrowBestFailure.isEmpty()) {
            arrayListThrowBestFailure = throwBestFailure(hostname, arrayList3);
        }
        return arrayListThrowBestFailure;
    }

    private final void executeRequests(final String hostname, List<? extends Call> networkRequests, final List<InetAddress> responses, final List<Exception> failures) {
        final CountDownLatch countDownLatch = new CountDownLatch(networkRequests.size());
        Iterator<? extends Call> it = networkRequests.iterator();
        while (it.hasNext()) {
            it.next().enqueue(new Callback() { // from class: okhttp3.dnsoverhttps.DnsOverHttps.executeRequests.1
                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException e) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(e, "e");
                    List<Exception> list = failures;
                    synchronized (list) {
                        list.add(e);
                    }
                    countDownLatch.countDown();
                }

                @Override // okhttp3.Callback
                public void onResponse(Call call, Response response) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(response, "response");
                    this.processResponse(response, hostname, responses, failures);
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            failures.add(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void processResponse(Response response, String hostname, List<InetAddress> results, List<Exception> failures) {
        try {
            List<InetAddress> response2 = readResponse(hostname, response);
            synchronized (results) {
                results.addAll(response2);
            }
        } catch (Exception e) {
            synchronized (failures) {
                failures.add(e);
            }
        }
    }

    private final List<InetAddress> throwBestFailure(String hostname, List<? extends Exception> failures) throws Exception {
        if (failures.isEmpty()) {
            throw new UnknownHostException(hostname);
        }
        Exception exc = failures.get(0);
        if (exc instanceof UnknownHostException) {
            throw exc;
        }
        UnknownHostException unknownHostException = new UnknownHostException(hostname);
        unknownHostException.initCause(exc);
        int size = failures.size();
        for (int i = 1; i < size; i++) {
            ExceptionsKt.addSuppressed(unknownHostException, failures.get(i));
        }
        throw unknownHostException;
    }

    private final List<InetAddress> readResponse(String hostname, Response response) throws Exception {
        if (response.cacheResponse() == null && response.protocol() != Protocol.HTTP_2 && response.protocol() != Protocol.QUIC) {
            Platform.log$default(Platform.INSTANCE.get(), "Incorrect protocol: " + response.protocol(), 5, null, 4, null);
        }
        Response response2 = response;
        try {
            if (!response.getIsSuccessful()) {
                throw new IOException("response: " + response.code() + ' ' + response.message());
            }
            ResponseBody responseBodyBody = response.body();
            if (responseBodyBody.getContentLength() > 65536) {
                throw new IOException("response size exceeds limit (65536 bytes): " + responseBodyBody.getContentLength() + " bytes");
            }
            List<InetAddress> listDecodeAnswers = DnsRecordCodec.INSTANCE.decodeAnswers(hostname, responseBodyBody.getSource().readByteString());
            CloseableKt.closeFinally(response2, null);
            return listDecodeAnswers;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(response2, th);
                throw th2;
            }
        }
    }

    private final Request buildRequest(String hostname, int type) {
        Request.Builder builder = new Request.Builder();
        MediaType mediaType = DNS_MESSAGE;
        Request.Builder builderHeader = builder.header("Accept", mediaType.toString());
        ByteString byteStringEncodeQuery = DnsRecordCodec.INSTANCE.encodeQuery(hostname, type);
        HttpUrl httpUrl = this.url;
        if (this.post) {
            builderHeader.url(httpUrl).cacheUrlOverride(httpUrl.newBuilder().addQueryParameter("hostname", hostname).build()).post(RequestBody.INSTANCE.create(byteStringEncodeQuery, mediaType));
        } else {
            builderHeader.url(httpUrl.newBuilder().addQueryParameter("dns", StringsKt.replace$default(byteStringEncodeQuery.base64Url(), "=", "", false, 4, (Object) null)).build());
        }
        return builderHeader.build();
    }

    /* JADX INFO: compiled from: DnsOverHttps.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010,\u001a\u00020-J\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0011J\u000e\u0010&\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0011J\u000e\u0010)\u001a\u00020\u00002\u0006\u0010)\u001a\u00020\u0011J\u0016\u0010\u001f\u001a\u00020\u00002\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010 J\u001f\u0010\u001f\u001a\u00020\u00002\u0012\u0010\u001f\u001a\n\u0012\u0006\b\u0001\u0012\u00020!0.\"\u00020!¢\u0006\u0002\u0010/J\u000e\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001aR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0011X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0013\"\u0004\b\u0018\u0010\u0015R\u001a\u0010\u0019\u001a\u00020\u001aX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\"\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010 X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001a\u0010&\u001a\u00020\u0011X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0013\"\u0004\b(\u0010\u0015R\u001a\u0010)\u001a\u00020\u0011X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0013\"\u0004\b+\u0010\u0015¨\u00060"}, d2 = {"Lokhttp3/dnsoverhttps/DnsOverHttps$Builder;", "", "<init>", "()V", "client", "Lokhttp3/OkHttpClient;", "getClient$okhttp_dnsoverhttps", "()Lokhttp3/OkHttpClient;", "setClient$okhttp_dnsoverhttps", "(Lokhttp3/OkHttpClient;)V", ImagesContract.URL, "Lokhttp3/HttpUrl;", "getUrl$okhttp_dnsoverhttps", "()Lokhttp3/HttpUrl;", "setUrl$okhttp_dnsoverhttps", "(Lokhttp3/HttpUrl;)V", "includeIPv6", "", "getIncludeIPv6$okhttp_dnsoverhttps", "()Z", "setIncludeIPv6$okhttp_dnsoverhttps", "(Z)V", "post", "getPost$okhttp_dnsoverhttps", "setPost$okhttp_dnsoverhttps", "systemDns", "Lokhttp3/Dns;", "getSystemDns$okhttp_dnsoverhttps", "()Lokhttp3/Dns;", "setSystemDns$okhttp_dnsoverhttps", "(Lokhttp3/Dns;)V", "bootstrapDnsHosts", "", "Ljava/net/InetAddress;", "getBootstrapDnsHosts$okhttp_dnsoverhttps", "()Ljava/util/List;", "setBootstrapDnsHosts$okhttp_dnsoverhttps", "(Ljava/util/List;)V", "resolvePrivateAddresses", "getResolvePrivateAddresses$okhttp_dnsoverhttps", "setResolvePrivateAddresses$okhttp_dnsoverhttps", "resolvePublicAddresses", "getResolvePublicAddresses$okhttp_dnsoverhttps", "setResolvePublicAddresses$okhttp_dnsoverhttps", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lokhttp3/dnsoverhttps/DnsOverHttps;", "", "([Ljava/net/InetAddress;)Lokhttp3/dnsoverhttps/DnsOverHttps$Builder;", "okhttp-dnsoverhttps"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Builder {
        private List<? extends InetAddress> bootstrapDnsHosts;
        private OkHttpClient client;
        private boolean post;
        private boolean resolvePrivateAddresses;
        private HttpUrl url;
        private boolean includeIPv6 = true;
        private Dns systemDns = Dns.SYSTEM;
        private boolean resolvePublicAddresses = true;

        /* JADX INFO: renamed from: getClient$okhttp_dnsoverhttps, reason: from getter */
        public final OkHttpClient getClient() {
            return this.client;
        }

        public final void setClient$okhttp_dnsoverhttps(OkHttpClient okHttpClient) {
            this.client = okHttpClient;
        }

        /* JADX INFO: renamed from: getUrl$okhttp_dnsoverhttps, reason: from getter */
        public final HttpUrl getUrl() {
            return this.url;
        }

        public final void setUrl$okhttp_dnsoverhttps(HttpUrl httpUrl) {
            this.url = httpUrl;
        }

        /* JADX INFO: renamed from: getIncludeIPv6$okhttp_dnsoverhttps, reason: from getter */
        public final boolean getIncludeIPv6() {
            return this.includeIPv6;
        }

        public final void setIncludeIPv6$okhttp_dnsoverhttps(boolean z) {
            this.includeIPv6 = z;
        }

        /* JADX INFO: renamed from: getPost$okhttp_dnsoverhttps, reason: from getter */
        public final boolean getPost() {
            return this.post;
        }

        public final void setPost$okhttp_dnsoverhttps(boolean z) {
            this.post = z;
        }

        /* JADX INFO: renamed from: getSystemDns$okhttp_dnsoverhttps, reason: from getter */
        public final Dns getSystemDns() {
            return this.systemDns;
        }

        public final void setSystemDns$okhttp_dnsoverhttps(Dns dns) {
            Intrinsics.checkNotNullParameter(dns, "<set-?>");
            this.systemDns = dns;
        }

        public final List<InetAddress> getBootstrapDnsHosts$okhttp_dnsoverhttps() {
            return this.bootstrapDnsHosts;
        }

        public final void setBootstrapDnsHosts$okhttp_dnsoverhttps(List<? extends InetAddress> list) {
            this.bootstrapDnsHosts = list;
        }

        /* JADX INFO: renamed from: getResolvePrivateAddresses$okhttp_dnsoverhttps, reason: from getter */
        public final boolean getResolvePrivateAddresses() {
            return this.resolvePrivateAddresses;
        }

        public final void setResolvePrivateAddresses$okhttp_dnsoverhttps(boolean z) {
            this.resolvePrivateAddresses = z;
        }

        /* JADX INFO: renamed from: getResolvePublicAddresses$okhttp_dnsoverhttps, reason: from getter */
        public final boolean getResolvePublicAddresses() {
            return this.resolvePublicAddresses;
        }

        public final void setResolvePublicAddresses$okhttp_dnsoverhttps(boolean z) {
            this.resolvePublicAddresses = z;
        }

        public final DnsOverHttps build() {
            OkHttpClient okHttpClient = this.client;
            if (okHttpClient == null) {
                throw new NullPointerException("client not set");
            }
            OkHttpClient okHttpClientBuild = okHttpClient.newBuilder().dns(DnsOverHttps.INSTANCE.buildBootstrapClient(this)).build();
            HttpUrl httpUrl = this.url;
            if (httpUrl != null) {
                return new DnsOverHttps(okHttpClientBuild, httpUrl, this.includeIPv6, this.post, this.resolvePrivateAddresses, this.resolvePublicAddresses);
            }
            throw new IllegalStateException("url not set".toString());
        }

        public final Builder client(OkHttpClient client) {
            Intrinsics.checkNotNullParameter(client, "client");
            this.client = client;
            return this;
        }

        public final Builder url(HttpUrl url) {
            Intrinsics.checkNotNullParameter(url, "url");
            this.url = url;
            return this;
        }

        public final Builder includeIPv6(boolean includeIPv6) {
            this.includeIPv6 = includeIPv6;
            return this;
        }

        public final Builder post(boolean post) {
            this.post = post;
            return this;
        }

        public final Builder resolvePrivateAddresses(boolean resolvePrivateAddresses) {
            this.resolvePrivateAddresses = resolvePrivateAddresses;
            return this;
        }

        public final Builder resolvePublicAddresses(boolean resolvePublicAddresses) {
            this.resolvePublicAddresses = resolvePublicAddresses;
            return this;
        }

        public final Builder bootstrapDnsHosts(List<? extends InetAddress> bootstrapDnsHosts) {
            this.bootstrapDnsHosts = bootstrapDnsHosts;
            return this;
        }

        public final Builder bootstrapDnsHosts(InetAddress... bootstrapDnsHosts) {
            Intrinsics.checkNotNullParameter(bootstrapDnsHosts, "bootstrapDnsHosts");
            return bootstrapDnsHosts(ArraysKt.toList(bootstrapDnsHosts));
        }

        public final Builder systemDns(Dns systemDns) {
            Intrinsics.checkNotNullParameter(systemDns, "systemDns");
            this.systemDns = systemDns;
            return this;
        }
    }

    /* JADX INFO: compiled from: DnsOverHttps.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0015\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lokhttp3/dnsoverhttps/DnsOverHttps$Companion;", "", "<init>", "()V", "DNS_MESSAGE", "Lokhttp3/MediaType;", "getDNS_MESSAGE", "()Lokhttp3/MediaType;", "MAX_RESPONSE_SIZE", "", "buildBootstrapClient", "Lokhttp3/Dns;", "builder", "Lokhttp3/dnsoverhttps/DnsOverHttps$Builder;", "isPrivateHost", "", "host", "", "isPrivateHost$okhttp_dnsoverhttps", "okhttp-dnsoverhttps"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MediaType getDNS_MESSAGE() {
            return DnsOverHttps.DNS_MESSAGE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Dns buildBootstrapClient(Builder builder) {
            List<InetAddress> bootstrapDnsHosts$okhttp_dnsoverhttps = builder.getBootstrapDnsHosts$okhttp_dnsoverhttps();
            if (bootstrapDnsHosts$okhttp_dnsoverhttps != null) {
                HttpUrl url = builder.getUrl();
                Intrinsics.checkNotNull(url);
                return new BootstrapDns(url.host(), bootstrapDnsHosts$okhttp_dnsoverhttps);
            }
            return builder.getSystemDns();
        }

        public final boolean isPrivateHost$okhttp_dnsoverhttps(String host) {
            Intrinsics.checkNotNullParameter(host, "host");
            return PublicSuffixDatabase.INSTANCE.get().getEffectiveTldPlusOne(host) == null;
        }
    }
}
