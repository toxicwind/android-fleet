package io.ktor.http.content;

import io.ktor.http.ApplicationResponsePropertiesKt;
import io.ktor.http.HeaderValue;
import io.ktor.http.HeaderValueWithParametersKt;
import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpHeaderValueParserKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;
import org.mozilla.javascript.optimizer.Signatures;
import org.slf4j.Marker;

/* JADX INFO: compiled from: Versions.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\b\u0086\b\u0018\u0000 .2\u00020\u0001:\u0001.B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u000e\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0010\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0010\u0010\u000fJ\u001b\u0010\u0013\u001a\u00020\n2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00000\u0011¢\u0006\u0004\b\u0013\u0010\u0014J\u001b\u0010\u000e\u001a\u00020\n2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00000\u0011¢\u0006\u0004\b\u000e\u0010\u0014J\u0017\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001d\u0010\u001eJ$\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004HÆ\u0001¢\u0006\u0004\b\u001f\u0010 J\u001b\u0010\"\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010!HÖ\u0083\u0004¢\u0006\u0004\b\"\u0010#J\u0011\u0010%\u001a\u00020$HÖ\u0081\u0004¢\u0006\u0004\b%\u0010&J\u0011\u0010'\u001a\u00020\u0002HÖ\u0081\u0004¢\u0006\u0004\b'\u0010\u001cR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010(\u001a\u0004\b)\u0010\u001cR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010*\u001a\u0004\b+\u0010\u001eR\u0014\u0010,\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b,\u0010(R\u0014\u0010-\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b-\u0010(¨\u0006/"}, d2 = {"Lio/ktor/http/content/EntityTagVersion;", "Lio/ktor/http/content/Version;", "", "etag", "", "weak", "<init>", "(Ljava/lang/String;Z)V", "Lio/ktor/http/Headers;", "requestHeaders", "Lio/ktor/http/content/VersionCheckResult;", "check", "(Lio/ktor/http/Headers;)Lio/ktor/http/content/VersionCheckResult;", "other", "match", "(Lio/ktor/http/content/EntityTagVersion;)Z", "weakMatch", "", "givenNoneMatchEtags", "noneMatch", "(Ljava/util/List;)Lio/ktor/http/content/VersionCheckResult;", "givenMatchEtags", "Lio/ktor/http/HeadersBuilder;", "builder", "", "appendHeadersTo", "(Lio/ktor/http/HeadersBuilder;)V", "component1", "()Ljava/lang/String;", "component2", "()Z", "copy", "(Ljava/lang/String;Z)Lio/ktor/http/content/EntityTagVersion;", "", "equals", Signatures.MATH_TO_BOOLEAN, "", "hashCode", "()I", "toString", "Ljava/lang/String;", "getEtag", "Z", "getWeak", "opaque", "normalized", "Companion", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class EntityTagVersion implements Version {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final EntityTagVersion STAR = new EntityTagVersion(Marker.ANY_MARKER, false);
    private final String etag;
    private final String normalized;
    private final String opaque;
    private final boolean weak;

    public static /* synthetic */ EntityTagVersion copy$default(EntityTagVersion entityTagVersion, String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = entityTagVersion.etag;
        }
        if ((i & 2) != 0) {
            z = entityTagVersion.weak;
        }
        return entityTagVersion.copy(str, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getEtag() {
        return this.etag;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getWeak() {
        return this.weak;
    }

    public final EntityTagVersion copy(String etag, boolean weak) {
        Intrinsics.checkNotNullParameter(etag, "etag");
        return new EntityTagVersion(etag, weak);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EntityTagVersion)) {
            return false;
        }
        EntityTagVersion entityTagVersion = (EntityTagVersion) other;
        return Intrinsics.areEqual(this.etag, entityTagVersion.etag) && this.weak == entityTagVersion.weak;
    }

    public int hashCode() {
        return (this.etag.hashCode() * 31) + UByte$$ExternalSyntheticBackport0.m(this.weak);
    }

    public String toString() {
        return "EntityTagVersion(etag=" + this.etag + ", weak=" + this.weak + ')';
    }

    public EntityTagVersion(String etag, boolean z) {
        Intrinsics.checkNotNullParameter(etag, "etag");
        this.etag = etag;
        this.weak = z;
        String strQuote = (Intrinsics.areEqual(etag, Marker.ANY_MARKER) || StringsKt.startsWith$default(etag, "\"", false, 2, (Object) null)) ? etag : HeaderValueWithParametersKt.quote(etag);
        this.opaque = strQuote;
        this.normalized = z ? "W/" + strQuote : strQuote;
        if (z && Intrinsics.areEqual(etag, STAR.etag)) {
            throw new IllegalArgumentException("Entity tag '*' could not be weak.".toString());
        }
        int length = etag.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = this.etag.charAt(i);
            if ((Intrinsics.compare((int) cCharAt, 32) <= 0 || cCharAt == '\"') && i != 0 && i != StringsKt.getLastIndex(this.etag)) {
                throw new IllegalArgumentException(("Character '" + cCharAt + "' is not allowed in entity-tag.").toString());
            }
        }
    }

    public final String getEtag() {
        return this.etag;
    }

    public final boolean getWeak() {
        return this.weak;
    }

    @Override // io.ktor.http.content.Version
    public VersionCheckResult check(Headers requestHeaders) {
        List<EntityTagVersion> list;
        VersionCheckResult versionCheckResultMatch;
        List<EntityTagVersion> list2;
        VersionCheckResult versionCheckResultNoneMatch;
        Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
        String str = requestHeaders.get("If-None-Match");
        if (str != null && (list2 = INSTANCE.parse(str)) != null && (versionCheckResultNoneMatch = noneMatch(list2)) != VersionCheckResult.OK) {
            return versionCheckResultNoneMatch;
        }
        String str2 = requestHeaders.get("If-Match");
        return (str2 == null || (list = INSTANCE.parse(str2)) == null || (versionCheckResultMatch = match(list)) == VersionCheckResult.OK) ? VersionCheckResult.OK : versionCheckResultMatch;
    }

    public final boolean match(EntityTagVersion other) {
        Intrinsics.checkNotNullParameter(other, "other");
        if (this.weak || other.weak) {
            return false;
        }
        return weakMatch(other);
    }

    private final boolean weakMatch(EntityTagVersion other) {
        EntityTagVersion entityTagVersion = STAR;
        if (Intrinsics.areEqual(this, entityTagVersion) || Intrinsics.areEqual(other, entityTagVersion)) {
            return true;
        }
        return Intrinsics.areEqual(this.opaque, other.opaque);
    }

    public final VersionCheckResult noneMatch(List<EntityTagVersion> givenNoneMatchEtags) {
        Intrinsics.checkNotNullParameter(givenNoneMatchEtags, "givenNoneMatchEtags");
        if (givenNoneMatchEtags.contains(STAR)) {
            return VersionCheckResult.OK;
        }
        List<EntityTagVersion> list = givenNoneMatchEtags;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (weakMatch((EntityTagVersion) it.next())) {
                    return VersionCheckResult.NOT_MODIFIED;
                }
            }
        }
        return VersionCheckResult.OK;
    }

    public final VersionCheckResult match(List<EntityTagVersion> givenMatchEtags) {
        Intrinsics.checkNotNullParameter(givenMatchEtags, "givenMatchEtags");
        if (!givenMatchEtags.isEmpty() && !givenMatchEtags.contains(STAR)) {
            Iterator<EntityTagVersion> it = givenMatchEtags.iterator();
            while (it.hasNext()) {
                if (match(it.next())) {
                    return VersionCheckResult.OK;
                }
            }
            return VersionCheckResult.PRECONDITION_FAILED;
        }
        return VersionCheckResult.OK;
    }

    @Override // io.ktor.http.content.Version
    public void appendHeadersTo(HeadersBuilder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        ApplicationResponsePropertiesKt.etag(builder, this.normalized);
    }

    /* JADX INFO: compiled from: Versions.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\u000b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0004¢\u0006\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lio/ktor/http/content/EntityTagVersion$Companion;", "", "<init>", "()V", "", "headerValue", "", "Lio/ktor/http/content/EntityTagVersion;", "parse", "(Ljava/lang/String;)Ljava/util/List;", ES6Iterator.VALUE_PROPERTY, "parseSingle", "(Ljava/lang/String;)Lio/ktor/http/content/EntityTagVersion;", "STAR", "Lio/ktor/http/content/EntityTagVersion;", "getSTAR", "()Lio/ktor/http/content/EntityTagVersion;", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final EntityTagVersion getSTAR() {
            return EntityTagVersion.STAR;
        }

        public final List<EntityTagVersion> parse(String headerValue) {
            Intrinsics.checkNotNullParameter(headerValue, "headerValue");
            List<HeaderValue> headerValue2 = HttpHeaderValueParserKt.parseHeaderValue(headerValue);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(headerValue2, 10));
            for (HeaderValue headerValue3 : headerValue2) {
                if (headerValue3.getQuality() != 1.0d) {
                    throw new IllegalStateException(("entity-tag quality parameter is not allowed: " + headerValue3.getQuality() + '.').toString());
                }
                if (!headerValue3.getParams().isEmpty()) {
                    throw new IllegalStateException(("entity-tag parameters are not allowed: " + headerValue3.getParams() + '.').toString());
                }
                arrayList.add(EntityTagVersion.INSTANCE.parseSingle(headerValue3.getValue()));
            }
            return arrayList;
        }

        public final EntityTagVersion parseSingle(String value) {
            boolean z;
            Intrinsics.checkNotNullParameter(value, "value");
            if (Intrinsics.areEqual(value, Marker.ANY_MARKER)) {
                return getSTAR();
            }
            if (StringsKt.startsWith$default(value, "W/", false, 2, (Object) null)) {
                value = StringsKt.drop(value, 2);
                z = true;
            } else {
                z = false;
            }
            if (!StringsKt.startsWith$default(value, "\"", false, 2, (Object) null)) {
                value = HeaderValueWithParametersKt.quote(value);
            }
            return new EntityTagVersion(value, z);
        }
    }
}
