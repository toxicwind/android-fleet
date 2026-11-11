package io.ktor.http;

import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: Ranges.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lio/ktor/http/ContentRange;", "", "<init>", "()V", "Bounded", "TailFrom", TextFieldImplKt.SuffixId, "Lio/ktor/http/ContentRange$Bounded;", "Lio/ktor/http/ContentRange$Suffix;", "Lio/ktor/http/ContentRange$TailFrom;", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class ContentRange {
    public /* synthetic */ ContentRange(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private ContentRange() {
    }

    /* JADX INFO: compiled from: Ranges.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0011\u0010\b\u001a\u00020\u0007H\u0096\u0080\u0004¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\f\u0010\u000bJ$\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u001b\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fHÖ\u0083\u0004¢\u0006\u0004\b\u0012\u0010\u0013J\u0011\u0010\u0015\u001a\u00020\u0014HÖ\u0081\u0004¢\u0006\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0017\u001a\u0004\b\u0018\u0010\u000bR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0017\u001a\u0004\b\u0019\u0010\u000b¨\u0006\u001a"}, d2 = {"Lio/ktor/http/ContentRange$Bounded;", "Lio/ktor/http/ContentRange;", "", TypedValues.TransitionType.S_FROM, TypedValues.TransitionType.S_TO, "<init>", "(JJ)V", "", "toString", "()Ljava/lang/String;", "component1", "()J", "component2", "copy", "(JJ)Lio/ktor/http/ContentRange$Bounded;", "", "other", "", "equals", Signatures.MATH_TO_BOOLEAN, "", "hashCode", "()I", "J", "getFrom", "getTo", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Bounded extends ContentRange {
        private final long from;
        private final long to;

        public static /* synthetic */ Bounded copy$default(Bounded bounded, long j, long j2, int i, Object obj) {
            if ((i & 1) != 0) {
                j = bounded.from;
            }
            if ((i & 2) != 0) {
                j2 = bounded.to;
            }
            return bounded.copy(j, j2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getFrom() {
            return this.from;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getTo() {
            return this.to;
        }

        public final Bounded copy(long from, long to) {
            return new Bounded(from, to);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Bounded)) {
                return false;
            }
            Bounded bounded = (Bounded) other;
            return this.from == bounded.from && this.to == bounded.to;
        }

        public int hashCode() {
            return (UByte$$ExternalSyntheticBackport0.m(this.from) * 31) + UByte$$ExternalSyntheticBackport0.m(this.to);
        }

        public Bounded(long j, long j2) {
            super(null);
            this.from = j;
            this.to = j2;
        }

        public final long getFrom() {
            return this.from;
        }

        public final long getTo() {
            return this.to;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.from);
            sb.append('-');
            sb.append(this.to);
            return sb.toString();
        }
    }

    /* JADX INFO: compiled from: Ranges.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010\u0007\u001a\u00020\u0006H\u0096\u0080\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004¢\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0013\u001a\u00020\u0012HÖ\u0081\u0004¢\u0006\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0015\u001a\u0004\b\u0016\u0010\n¨\u0006\u0017"}, d2 = {"Lio/ktor/http/ContentRange$TailFrom;", "Lio/ktor/http/ContentRange;", "", TypedValues.TransitionType.S_FROM, "<init>", "(J)V", "", "toString", "()Ljava/lang/String;", "component1", "()J", "copy", "(J)Lio/ktor/http/ContentRange$TailFrom;", "", "other", "", "equals", Signatures.MATH_TO_BOOLEAN, "", "hashCode", "()I", "J", "getFrom", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class TailFrom extends ContentRange {
        private final long from;

        public static /* synthetic */ TailFrom copy$default(TailFrom tailFrom, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                j = tailFrom.from;
            }
            return tailFrom.copy(j);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getFrom() {
            return this.from;
        }

        public final TailFrom copy(long from) {
            return new TailFrom(from);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof TailFrom) && this.from == ((TailFrom) other).from;
        }

        public int hashCode() {
            return UByte$$ExternalSyntheticBackport0.m(this.from);
        }

        public TailFrom(long j) {
            super(null);
            this.from = j;
        }

        public final long getFrom() {
            return this.from;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.from);
            sb.append('-');
            return sb.toString();
        }
    }

    /* JADX INFO: compiled from: Ranges.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010\u0007\u001a\u00020\u0006H\u0096\u0080\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004¢\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0013\u001a\u00020\u0012HÖ\u0081\u0004¢\u0006\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0015\u001a\u0004\b\u0016\u0010\n¨\u0006\u0017"}, d2 = {"Lio/ktor/http/ContentRange$Suffix;", "Lio/ktor/http/ContentRange;", "", "lastCount", "<init>", "(J)V", "", "toString", "()Ljava/lang/String;", "component1", "()J", "copy", "(J)Lio/ktor/http/ContentRange$Suffix;", "", "other", "", "equals", Signatures.MATH_TO_BOOLEAN, "", "hashCode", "()I", "J", "getLastCount", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Suffix extends ContentRange {
        private final long lastCount;

        public static /* synthetic */ Suffix copy$default(Suffix suffix, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                j = suffix.lastCount;
            }
            return suffix.copy(j);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getLastCount() {
            return this.lastCount;
        }

        public final Suffix copy(long lastCount) {
            return new Suffix(lastCount);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Suffix) && this.lastCount == ((Suffix) other).lastCount;
        }

        public int hashCode() {
            return UByte$$ExternalSyntheticBackport0.m(this.lastCount);
        }

        public Suffix(long j) {
            super(null);
            this.lastCount = j;
        }

        public final long getLastCount() {
            return this.lastCount;
        }

        public String toString() {
            return "-" + this.lastCount;
        }
    }
}
