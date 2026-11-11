package com.lagradost.nicehttp;

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

/* JADX INFO: compiled from: Dns.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\u001a \u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¨\u0006\u0006"}, d2 = {"addGenericDns", "Lokhttp3/OkHttpClient$Builder;", ImagesContract.URL, "", "ips", "", "library"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class DnsKt {
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
}
