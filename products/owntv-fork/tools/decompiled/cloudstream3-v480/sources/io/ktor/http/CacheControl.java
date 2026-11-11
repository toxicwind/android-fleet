package io.ktor.http;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: CacheControl.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b&\u0018\u00002\u00020\u0001:\u0004\t\n\u000b\fB\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lio/ktor/http/CacheControl;", "", "Lio/ktor/http/CacheControl$Visibility;", "visibility", "<init>", "(Lio/ktor/http/CacheControl$Visibility;)V", "Lio/ktor/http/CacheControl$Visibility;", "getVisibility", "()Lio/ktor/http/CacheControl$Visibility;", "Visibility", "NoCache", "NoStore", "MaxAge", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class CacheControl {
    private final Visibility visibility;

    public CacheControl(Visibility visibility) {
        this.visibility = visibility;
    }

    public final Visibility getVisibility() {
        return this.visibility;
    }

    /* JADX INFO: compiled from: CacheControl.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005R\u001a\u0010\u0003\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0006\u001a\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lio/ktor/http/CacheControl$Visibility;", "", "", "headerValue", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "Ljava/lang/String;", "getHeaderValue$ktor_http", "()Ljava/lang/String;", "Public", "Private", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public enum Visibility {
        Public("public"),
        Private("private");

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
        private final String headerValue;

        public static EnumEntries<Visibility> getEntries() {
            return $ENTRIES;
        }

        Visibility(String str) {
            this.headerValue = str;
        }

        /* JADX INFO: renamed from: getHeaderValue$ktor_http, reason: from getter */
        public final String getHeaderValue() {
            return this.headerValue;
        }
    }

    /* JADX INFO: compiled from: CacheControl.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010\u0007\u001a\u00020\u0006H\u0096\u0080\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u001b\u0010\f\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0096\u0082\u0004¢\u0006\u0004\b\f\u0010\rJ\u0011\u0010\u000f\u001a\u00020\u000eH\u0096\u0080\u0004¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lio/ktor/http/CacheControl$NoCache;", "Lio/ktor/http/CacheControl;", "Lio/ktor/http/CacheControl$Visibility;", "visibility", "<init>", "(Lio/ktor/http/CacheControl$Visibility;)V", "", "toString", "()Ljava/lang/String;", "", "other", "", "equals", Signatures.MATH_TO_BOOLEAN, "", "hashCode", "()I", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class NoCache extends CacheControl {
        public NoCache(Visibility visibility) {
            super(visibility);
        }

        public String toString() {
            if (getVisibility() == null) {
                return "no-cache";
            }
            return "no-cache, " + getVisibility().getHeaderValue();
        }

        public boolean equals(Object other) {
            return (other instanceof NoCache) && getVisibility() == ((NoCache) other).getVisibility();
        }

        public int hashCode() {
            Visibility visibility = getVisibility();
            if (visibility != null) {
                return visibility.hashCode();
            }
            return 0;
        }
    }

    /* JADX INFO: compiled from: CacheControl.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010\u0007\u001a\u00020\u0006H\u0096\u0080\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u001b\u0010\f\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0096\u0082\u0004¢\u0006\u0004\b\f\u0010\rJ\u0011\u0010\u000f\u001a\u00020\u000eH\u0096\u0080\u0004¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lio/ktor/http/CacheControl$NoStore;", "Lio/ktor/http/CacheControl;", "Lio/ktor/http/CacheControl$Visibility;", "visibility", "<init>", "(Lio/ktor/http/CacheControl$Visibility;)V", "", "toString", "()Ljava/lang/String;", "", "other", "", "equals", Signatures.MATH_TO_BOOLEAN, "", "hashCode", "()I", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class NoStore extends CacheControl {
        public NoStore(Visibility visibility) {
            super(visibility);
        }

        public String toString() {
            if (getVisibility() == null) {
                return "no-store";
            }
            return "no-store, " + getVisibility().getHeaderValue();
        }

        public boolean equals(Object other) {
            return (other instanceof NoStore) && ((NoStore) other).getVisibility() == getVisibility();
        }

        public int hashCode() {
            Visibility visibility = getVisibility();
            if (visibility != null) {
                return visibility.hashCode();
            }
            return 0;
        }
    }

    /* JADX INFO: compiled from: CacheControl.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u000f\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\n\u0010\u000bJ\u0011\u0010\r\u001a\u00020\fH\u0096\u0080\u0004¢\u0006\u0004\b\r\u0010\u000eJ\u001b\u0010\u0011\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0096\u0082\u0004¢\u0006\u0004\b\u0011\u0010\u0012J\u0011\u0010\u0013\u001a\u00020\u0002H\u0096\u0080\u0004¢\u0006\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0015\u001a\u0004\b\u0016\u0010\u0014R\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\u0007\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001a\u001a\u0004\b\u001d\u0010\u001c¨\u0006\u001e"}, d2 = {"Lio/ktor/http/CacheControl$MaxAge;", "Lio/ktor/http/CacheControl;", "", "maxAgeSeconds", "proxyMaxAgeSeconds", "", "mustRevalidate", "proxyRevalidate", "Lio/ktor/http/CacheControl$Visibility;", "visibility", "<init>", "(ILjava/lang/Integer;ZZLio/ktor/http/CacheControl$Visibility;)V", "", "toString", "()Ljava/lang/String;", "", "other", "equals", Signatures.MATH_TO_BOOLEAN, "hashCode", "()I", "I", "getMaxAgeSeconds", "Ljava/lang/Integer;", "getProxyMaxAgeSeconds", "()Ljava/lang/Integer;", "Z", "getMustRevalidate", "()Z", "getProxyRevalidate", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class MaxAge extends CacheControl {
        private final int maxAgeSeconds;
        private final boolean mustRevalidate;
        private final Integer proxyMaxAgeSeconds;
        private final boolean proxyRevalidate;

        public MaxAge(int i, Integer num, boolean z, boolean z2, Visibility visibility) {
            super(visibility);
            this.maxAgeSeconds = i;
            this.proxyMaxAgeSeconds = num;
            this.mustRevalidate = z;
            this.proxyRevalidate = z2;
        }

        public /* synthetic */ MaxAge(int i, Integer num, boolean z, boolean z2, Visibility visibility, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, (i2 & 2) != 0 ? null : num, (i2 & 4) != 0 ? false : z, (i2 & 8) != 0 ? false : z2, (i2 & 16) != 0 ? null : visibility);
        }

        public final int getMaxAgeSeconds() {
            return this.maxAgeSeconds;
        }

        public final Integer getProxyMaxAgeSeconds() {
            return this.proxyMaxAgeSeconds;
        }

        public final boolean getMustRevalidate() {
            return this.mustRevalidate;
        }

        public final boolean getProxyRevalidate() {
            return this.proxyRevalidate;
        }

        public String toString() {
            ArrayList arrayList = new ArrayList(5);
            arrayList.add("max-age=" + this.maxAgeSeconds);
            if (this.proxyMaxAgeSeconds != null) {
                arrayList.add("s-maxage=" + this.proxyMaxAgeSeconds.intValue());
            }
            if (this.mustRevalidate) {
                arrayList.add("must-revalidate");
            }
            if (this.proxyRevalidate) {
                arrayList.add("proxy-revalidate");
            }
            if (getVisibility() != null) {
                arrayList.add(getVisibility().getHeaderValue());
            }
            return CollectionsKt.joinToString$default(arrayList, ", ", null, null, 0, null, null, 62, null);
        }

        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if (!(other instanceof MaxAge)) {
                return false;
            }
            MaxAge maxAge = (MaxAge) other;
            return maxAge.maxAgeSeconds == this.maxAgeSeconds && Intrinsics.areEqual(maxAge.proxyMaxAgeSeconds, this.proxyMaxAgeSeconds) && maxAge.mustRevalidate == this.mustRevalidate && maxAge.proxyRevalidate == this.proxyRevalidate && maxAge.getVisibility() == getVisibility();
        }

        public int hashCode() {
            int i = this.maxAgeSeconds * 31;
            Integer num = this.proxyMaxAgeSeconds;
            int iIntValue = (((((i + (num != null ? num.intValue() : 0)) * 31) + UByte$$ExternalSyntheticBackport0.m(this.mustRevalidate)) * 31) + UByte$$ExternalSyntheticBackport0.m(this.proxyRevalidate)) * 31;
            Visibility visibility = getVisibility();
            return iIntValue + (visibility != null ? visibility.hashCode() : 0);
        }
    }
}
