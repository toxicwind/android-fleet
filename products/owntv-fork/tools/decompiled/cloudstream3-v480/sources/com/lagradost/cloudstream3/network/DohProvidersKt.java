package com.lagradost.cloudstream3.network;

import com.google.android.gms.common.internal.ImagesContract;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.dnsoverhttps.DnsOverHttps;

/* JADX INFO: compiled from: DohProviders.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\b\u001a \u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u001a\n\u0010\u0006\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u0007\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\b\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\t\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\n\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u000b\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\f\u001a\u00020\u0001*\u00020\u0001¨\u0006\r"}, d2 = {"addGenericDns", "Lokhttp3/OkHttpClient$Builder;", ImagesContract.URL, "", "ips", "", "addGoogleDns", "addCloudFlareDns", "addAdGuardDns", "addDNSWatchDns", "addQuad9Dns", "addDnsSbDns", "addCanadianShieldDns", "app"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class DohProvidersKt {
    public static final OkHttpClient.Builder addGenericDns(OkHttpClient.Builder builder, String url, List<String> ips) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(ips, "ips");
        DnsOverHttps.Builder builderUrl = new DnsOverHttps.Builder().client(builder.build()).url(HttpUrl.INSTANCE.get(url));
        List<String> list = ips;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(InetAddress.getByName((String) it.next()));
        }
        return builder.dns(builderUrl.bootstrapDnsHosts(arrayList).build());
    }

    public static final OkHttpClient.Builder addGoogleDns(OkHttpClient.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        return addGenericDns(builder, "https://dns.google/dns-query", CollectionsKt.listOf((Object[]) new String[]{"8.8.4.4", "8.8.8.8"}));
    }

    public static final OkHttpClient.Builder addCloudFlareDns(OkHttpClient.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        return addGenericDns(builder, "https://cloudflare-dns.com/dns-query", CollectionsKt.listOf((Object[]) new String[]{"1.1.1.1", "1.0.0.1", "2606:4700:4700::1111", "2606:4700:4700::1001"}));
    }

    public static final OkHttpClient.Builder addAdGuardDns(OkHttpClient.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        return addGenericDns(builder, "https://dns.adguard.com/dns-query", CollectionsKt.listOf((Object[]) new String[]{"94.140.14.140", "94.140.14.141"}));
    }

    public static final OkHttpClient.Builder addDNSWatchDns(OkHttpClient.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        return addGenericDns(builder, "https://resolver2.dns.watch/dns-query", CollectionsKt.listOf((Object[]) new String[]{"84.200.69.80", "84.200.70.40"}));
    }

    public static final OkHttpClient.Builder addQuad9Dns(OkHttpClient.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        return addGenericDns(builder, "https://dns.quad9.net/dns-query", CollectionsKt.listOf((Object[]) new String[]{"9.9.9.9", "149.112.112.112"}));
    }

    public static final OkHttpClient.Builder addDnsSbDns(OkHttpClient.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        return addGenericDns(builder, "https://doh.dns.sb/dns-query", CollectionsKt.listOf((Object[]) new String[]{"185.222.222.222", "45.11.45.11"}));
    }

    public static final OkHttpClient.Builder addCanadianShieldDns(OkHttpClient.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        return addGenericDns(builder, "https://private.canadianshield.cira.ca/dns-query", CollectionsKt.listOf((Object[]) new String[]{"149.112.121.10", "149.112.122.10"}));
    }
}
