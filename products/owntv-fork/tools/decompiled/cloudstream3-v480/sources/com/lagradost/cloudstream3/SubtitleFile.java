package com.lagradost.cloudstream3;

import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.utils.SubtitleHelper;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MainAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B/\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0014\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bB\u0019\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\tJ\u001a\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\u0017\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006HÆ\u0003J5\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0016\b\u0002\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006HÂ\u0001J\u0014\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001d\u001a\u00020\u001eHÖ\u0081\u0004J\n\u0010\u001f\u001a\u00020\u0003HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR(\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u000b¨\u0006 "}, d2 = {"Lcom/lagradost/cloudstream3/SubtitleFile;", "", "lang", "", ImagesContract.URL, "headers", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "(Ljava/lang/String;Ljava/lang/String;)V", "getLang", "()Ljava/lang/String;", "setLang", "(Ljava/lang/String;)V", "getUrl", "setUrl", "getHeaders", "()Ljava/util/Map;", "setHeaders", "(Ljava/util/Map;)V", "langTag", "getLangTag", "copy", "component1", "component2", "component3", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SubtitleFile {
    private Map<String, String> headers;
    private String lang;
    private String url;

    private final SubtitleFile copy(String lang, String url, Map<String, String> headers) {
        return new SubtitleFile(lang, url, headers);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ SubtitleFile copy$default(SubtitleFile subtitleFile, String str, String str2, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            str = subtitleFile.lang;
        }
        if ((i & 2) != 0) {
            str2 = subtitleFile.url;
        }
        if ((i & 4) != 0) {
            map = subtitleFile.headers;
        }
        return subtitleFile.copy(str, str2, map);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getLang() {
        return this.lang;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    public final Map<String, String> component3() {
        return this.headers;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SubtitleFile)) {
            return false;
        }
        SubtitleFile subtitleFile = (SubtitleFile) other;
        return Intrinsics.areEqual(this.lang, subtitleFile.lang) && Intrinsics.areEqual(this.url, subtitleFile.url) && Intrinsics.areEqual(this.headers, subtitleFile.headers);
    }

    public int hashCode() {
        int iHashCode = ((this.lang.hashCode() * 31) + this.url.hashCode()) * 31;
        Map<String, String> map = this.headers;
        return iHashCode + (map == null ? 0 : map.hashCode());
    }

    public String toString() {
        return "SubtitleFile(lang=" + this.lang + ", url=" + this.url + ", headers=" + this.headers + ')';
    }

    private SubtitleFile(String str, String str2, Map<String, String> map) {
        this.lang = str;
        this.url = str2;
        this.headers = map;
    }

    public final String getLang() {
        return this.lang;
    }

    public final void setLang(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.lang = str;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.url = str;
    }

    public final Map<String, String> getHeaders() {
        return this.headers;
    }

    public final void setHeaders(Map<String, String> map) {
        this.headers = map;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated(level = DeprecationLevel.WARNING, message = "Use newSubtitleFile method")
    public SubtitleFile(String lang, String url) {
        this(lang, url, null);
        Intrinsics.checkNotNullParameter(lang, "lang");
        Intrinsics.checkNotNullParameter(url, "url");
    }

    public final String getLangTag() {
        String strFromCodeToLangTagIETF = SubtitleHelper.INSTANCE.fromCodeToLangTagIETF(this.lang);
        return strFromCodeToLangTagIETF == null ? SubtitleHelper.INSTANCE.fromLanguageToTagIETF(this.lang, true) : strFromCodeToLangTagIETF;
    }

    public static /* synthetic */ SubtitleFile copy$default(SubtitleFile subtitleFile, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = subtitleFile.lang;
        }
        if ((i & 2) != 0) {
            str2 = subtitleFile.url;
        }
        return subtitleFile.copy(str, str2);
    }

    public final SubtitleFile copy(String lang, String url) {
        Intrinsics.checkNotNullParameter(lang, "lang");
        Intrinsics.checkNotNullParameter(url, "url");
        return new SubtitleFile(lang, url, this.headers);
    }
}
