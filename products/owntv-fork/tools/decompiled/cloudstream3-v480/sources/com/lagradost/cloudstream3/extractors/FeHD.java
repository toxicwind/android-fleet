package com.lagradost.cloudstream3.extractors;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: XStreamCdn.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u001a\u0010\n\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/FeHD;", "Lcom/lagradost/cloudstream3/extractors/XStreamCdn;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "mainUrl", "getMainUrl", "domainUrl", "getDomainUrl", "setDomainUrl", "(Ljava/lang/String;)V", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class FeHD extends XStreamCdn {
    private final String name = "FeHD";
    private final String mainUrl = "https://fembed-hd.com";
    private String domainUrl = "fembed-hd.com";

    @Override // com.lagradost.cloudstream3.extractors.XStreamCdn, com.lagradost.cloudstream3.utils.ExtractorApi
    public String getName() {
        return this.name;
    }

    @Override // com.lagradost.cloudstream3.extractors.XStreamCdn, com.lagradost.cloudstream3.utils.ExtractorApi
    public String getMainUrl() {
        return this.mainUrl;
    }

    @Override // com.lagradost.cloudstream3.extractors.XStreamCdn
    public String getDomainUrl() {
        return this.domainUrl;
    }

    @Override // com.lagradost.cloudstream3.extractors.XStreamCdn
    public void setDomainUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.domainUrl = str;
    }
}
