package com.lagradost.cloudstream3;

import androidx.core.app.NotificationCompat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MainAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÆ\u0003J3\u0010\u001a\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001e\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u001f\u001a\u00020\u0003HÖ\u0081\u0004R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR \u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u000b\"\u0004\b\u0011\u0010\rR\u001e\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006 "}, d2 = {"Lcom/lagradost/cloudstream3/ProvidersInfoJson;", "", "name", "", ImagesContract.URL, "credentials", NotificationCompat.CATEGORY_STATUS, "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getUrl", "setUrl", "getCredentials", "setCredentials", "getStatus", "()I", "setStatus", "(I)V", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class ProvidersInfoJson {

    @JsonProperty("credentials")
    private String credentials;

    @JsonProperty("name")
    private String name;

    @JsonProperty(NotificationCompat.CATEGORY_STATUS)
    private int status;

    @JsonProperty(ImagesContract.URL)
    private String url;

    public static /* synthetic */ ProvidersInfoJson copy$default(ProvidersInfoJson providersInfoJson, String str, String str2, String str3, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = providersInfoJson.name;
        }
        if ((i2 & 2) != 0) {
            str2 = providersInfoJson.url;
        }
        if ((i2 & 4) != 0) {
            str3 = providersInfoJson.credentials;
        }
        if ((i2 & 8) != 0) {
            i = providersInfoJson.status;
        }
        return providersInfoJson.copy(str, str2, str3, i);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getCredentials() {
        return this.credentials;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    public final ProvidersInfoJson copy(@JsonProperty("name") String name, @JsonProperty(ImagesContract.URL) String url, @JsonProperty("credentials") String credentials, @JsonProperty(NotificationCompat.CATEGORY_STATUS) int status) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        return new ProvidersInfoJson(name, url, credentials, status);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProvidersInfoJson)) {
            return false;
        }
        ProvidersInfoJson providersInfoJson = (ProvidersInfoJson) other;
        return Intrinsics.areEqual(this.name, providersInfoJson.name) && Intrinsics.areEqual(this.url, providersInfoJson.url) && Intrinsics.areEqual(this.credentials, providersInfoJson.credentials) && this.status == providersInfoJson.status;
    }

    public int hashCode() {
        int iHashCode = ((this.name.hashCode() * 31) + this.url.hashCode()) * 31;
        String str = this.credentials;
        return ((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.status;
    }

    public String toString() {
        return "ProvidersInfoJson(name=" + this.name + ", url=" + this.url + ", credentials=" + this.credentials + ", status=" + this.status + ')';
    }

    public ProvidersInfoJson(@JsonProperty("name") String name, @JsonProperty(ImagesContract.URL) String url, @JsonProperty("credentials") String str, @JsonProperty(NotificationCompat.CATEGORY_STATUS) int i) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        this.name = name;
        this.url = url;
        this.credentials = str;
        this.status = i;
    }

    public /* synthetic */ ProvidersInfoJson(String str, String str2, String str3, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i2 & 4) != 0 ? null : str3, i);
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.url = str;
    }

    public final String getCredentials() {
        return this.credentials;
    }

    public final void setCredentials(String str) {
        this.credentials = str;
    }

    public final int getStatus() {
        return this.status;
    }

    public final void setStatus(int i) {
        this.status = i;
    }
}
