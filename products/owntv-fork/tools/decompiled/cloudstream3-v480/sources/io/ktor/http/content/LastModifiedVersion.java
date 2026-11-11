package io.ktor.http.content;

import io.ktor.http.DateUtilsKt;
import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import io.ktor.util.date.DateKt;
import io.ktor.util.date.GMTDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: Versions.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u001b\u0010\u000e\u001a\u00020\r2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0010\u001a\u00020\r2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\u000b¢\u0006\u0004\b\u0010\u0010\u000fJ\u0017\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J!\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000b*\b\u0012\u0004\u0012\u00020\u00160\u000bH\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\u001a\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\u001b\u0010\u001f\u001a\u00020\r2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001dHÖ\u0083\u0004¢\u0006\u0004\b\u001f\u0010 J\u0011\u0010\"\u001a\u00020!HÖ\u0081\u0004¢\u0006\u0004\b\"\u0010#J\u0011\u0010$\u001a\u00020\u0016HÖ\u0081\u0004¢\u0006\u0004\b$\u0010%R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010&\u001a\u0004\b'\u0010\u001aR\u0014\u0010(\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010&¨\u0006)"}, d2 = {"Lio/ktor/http/content/LastModifiedVersion;", "Lio/ktor/http/content/Version;", "Lio/ktor/util/date/GMTDate;", "lastModified", "<init>", "(Lio/ktor/util/date/GMTDate;)V", "Lio/ktor/http/Headers;", "requestHeaders", "Lio/ktor/http/content/VersionCheckResult;", "check", "(Lio/ktor/http/Headers;)Lio/ktor/http/content/VersionCheckResult;", "", "dates", "", "ifModifiedSince", "(Ljava/util/List;)Z", "ifUnmodifiedSince", "Lio/ktor/http/HeadersBuilder;", "builder", "", "appendHeadersTo", "(Lio/ktor/http/HeadersBuilder;)V", "", "parseDates", "(Ljava/util/List;)Ljava/util/List;", "component1", "()Lio/ktor/util/date/GMTDate;", "copy", "(Lio/ktor/util/date/GMTDate;)Lio/ktor/http/content/LastModifiedVersion;", "", "other", "equals", Signatures.MATH_TO_BOOLEAN, "", "hashCode", "()I", "toString", "()Ljava/lang/String;", "Lio/ktor/util/date/GMTDate;", "getLastModified", "truncatedModificationDate", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class LastModifiedVersion implements Version {
    private final GMTDate lastModified;
    private final GMTDate truncatedModificationDate;

    public static /* synthetic */ LastModifiedVersion copy$default(LastModifiedVersion lastModifiedVersion, GMTDate gMTDate, int i, Object obj) {
        if ((i & 1) != 0) {
            gMTDate = lastModifiedVersion.lastModified;
        }
        return lastModifiedVersion.copy(gMTDate);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final GMTDate getLastModified() {
        return this.lastModified;
    }

    public final LastModifiedVersion copy(GMTDate lastModified) {
        Intrinsics.checkNotNullParameter(lastModified, "lastModified");
        return new LastModifiedVersion(lastModified);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof LastModifiedVersion) && Intrinsics.areEqual(this.lastModified, ((LastModifiedVersion) other).lastModified);
    }

    public int hashCode() {
        return this.lastModified.hashCode();
    }

    public String toString() {
        return "LastModifiedVersion(lastModified=" + this.lastModified + ')';
    }

    public LastModifiedVersion(GMTDate lastModified) {
        Intrinsics.checkNotNullParameter(lastModified, "lastModified");
        this.lastModified = lastModified;
        this.truncatedModificationDate = DateKt.truncateToSeconds(lastModified);
    }

    public final GMTDate getLastModified() {
        return this.lastModified;
    }

    @Override // io.ktor.http.content.Version
    public VersionCheckResult check(Headers requestHeaders) {
        Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
        List<String> all = requestHeaders.getAll("If-Modified-Since");
        List<GMTDate> dates = all != null ? parseDates(all) : null;
        if (dates != null && !ifModifiedSince(dates)) {
            return VersionCheckResult.NOT_MODIFIED;
        }
        List<String> all2 = requestHeaders.getAll("If-Unmodified-Since");
        List<GMTDate> dates2 = all2 != null ? parseDates(all2) : null;
        if (dates2 != null && !ifUnmodifiedSince(dates2)) {
            return VersionCheckResult.PRECONDITION_FAILED;
        }
        return VersionCheckResult.OK;
    }

    public final boolean ifModifiedSince(List<GMTDate> dates) {
        Intrinsics.checkNotNullParameter(dates, "dates");
        List<GMTDate> list = dates;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (this.truncatedModificationDate.compareTo((GMTDate) it.next()) > 0) {
                return true;
            }
        }
        return false;
    }

    public final boolean ifUnmodifiedSince(List<GMTDate> dates) {
        Intrinsics.checkNotNullParameter(dates, "dates");
        List<GMTDate> list = dates;
        if ((list instanceof Collection) && list.isEmpty()) {
            return true;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (this.truncatedModificationDate.compareTo((GMTDate) it.next()) > 0) {
                return false;
            }
        }
        return true;
    }

    @Override // io.ktor.http.content.Version
    public void appendHeadersTo(HeadersBuilder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        builder.set("Last-Modified", DateUtilsKt.toHttpDate(this.lastModified));
    }

    private final List<GMTDate> parseDates(List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (!StringsKt.isBlank((String) obj)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (true) {
            GMTDate gMTDateFromHttpToGmtDate = null;
            if (!it.hasNext()) {
                break;
            }
            try {
                gMTDateFromHttpToGmtDate = DateUtilsKt.fromHttpToGmtDate((String) it.next());
            } catch (Throwable unused) {
            }
            if (gMTDateFromHttpToGmtDate != null) {
                arrayList2.add(gMTDateFromHttpToGmtDate);
            }
        }
        ArrayList arrayList3 = arrayList2;
        if (arrayList3.isEmpty()) {
            return null;
        }
        return arrayList3;
    }
}
