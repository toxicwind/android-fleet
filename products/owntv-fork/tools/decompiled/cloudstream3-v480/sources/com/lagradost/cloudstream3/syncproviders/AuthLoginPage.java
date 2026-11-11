package com.lagradost.cloudstream3.syncproviders;

import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AuthAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/AuthLoginPage;", "", ImagesContract.URL, "", "payload", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getUrl", "()Ljava/lang/String;", "getPayload", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class AuthLoginPage {
    private final String payload;
    private final String url;

    public static /* synthetic */ AuthLoginPage copy$default(AuthLoginPage authLoginPage, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = authLoginPage.url;
        }
        if ((i & 2) != 0) {
            str2 = authLoginPage.payload;
        }
        return authLoginPage.copy(str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPayload() {
        return this.payload;
    }

    public final AuthLoginPage copy(String url, String payload) {
        Intrinsics.checkNotNullParameter(url, "url");
        return new AuthLoginPage(url, payload);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AuthLoginPage)) {
            return false;
        }
        AuthLoginPage authLoginPage = (AuthLoginPage) other;
        return Intrinsics.areEqual(this.url, authLoginPage.url) && Intrinsics.areEqual(this.payload, authLoginPage.payload);
    }

    public int hashCode() {
        int iHashCode = this.url.hashCode() * 31;
        String str = this.payload;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "AuthLoginPage(url=" + this.url + ", payload=" + this.payload + ')';
    }

    public AuthLoginPage(String url, String str) {
        Intrinsics.checkNotNullParameter(url, "url");
        this.url = url;
        this.payload = str;
    }

    public /* synthetic */ AuthLoginPage(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : str2);
    }

    public final String getUrl() {
        return this.url;
    }

    public final String getPayload() {
        return this.payload;
    }
}
