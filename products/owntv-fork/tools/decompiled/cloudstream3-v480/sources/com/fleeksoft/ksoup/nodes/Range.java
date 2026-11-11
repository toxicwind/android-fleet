package com.fleeksoft.ksoup.nodes;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.fleeksoft.ksoup.internal.SharedConstants;
import com.fleeksoft.ksoup.internal.StringUtil;
import com.lagradost.cloudstream3.actions.temp.CloudStreamPackage;
import io.ktor.http.HttpHeaders;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Range.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\b\u0087\b\u0018\u0000 \u00172\u00020\u0001:\u0003\u0015\u0016\u0017B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\u0002\u001a\u00020\u0003J\u0006\u0010\u0004\u001a\u00020\u0003J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\bJ\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\u000bJ\n\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004J\t\u0010\u000f\u001a\u00020\u0003HÂ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÂ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u000b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\bHÖ\u0081\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/Range;", "", "start", "Lcom/fleeksoft/ksoup/nodes/Range$Position;", TtmlNode.END, "<init>", "(Lcom/fleeksoft/ksoup/nodes/Range$Position;Lcom/fleeksoft/ksoup/nodes/Range$Position;)V", "startPos", "", "endPos", "isTracked", "", "isImplicit", "toString", "", "component1", "component2", "copy", "equals", "other", "hashCode", HttpHeaders.Position, "AttributeRange", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class Range {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Range Untracked;
    private static final Position UntrackedPos;
    private final Position end;
    private final Position start;

    /* JADX INFO: renamed from: component1, reason: from getter */
    private final Position getStart() {
        return this.start;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    private final Position getEnd() {
        return this.end;
    }

    public static /* synthetic */ Range copy$default(Range range, Position position, Position position2, int i, Object obj) {
        if ((i & 1) != 0) {
            position = range.start;
        }
        if ((i & 2) != 0) {
            position2 = range.end;
        }
        return range.copy(position, position2);
    }

    public final Range copy(Position start, Position end) {
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(end, "end");
        return new Range(start, end);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Range)) {
            return false;
        }
        Range range = (Range) other;
        return Intrinsics.areEqual(this.start, range.start) && Intrinsics.areEqual(this.end, range.end);
    }

    public int hashCode() {
        return (this.start.hashCode() * 31) + this.end.hashCode();
    }

    public Range(Position start, Position end) {
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(end, "end");
        this.start = start;
        this.end = end;
    }

    public final Position start() {
        return this.start;
    }

    public final Position end() {
        return this.end;
    }

    public final int startPos() {
        return this.start.getPos$ksoup_release();
    }

    public final int endPos() {
        return this.end.getPos$ksoup_release();
    }

    public final boolean isTracked() {
        return !Intrinsics.areEqual(this, Untracked);
    }

    public final boolean isImplicit() {
        if (isTracked()) {
            return Intrinsics.areEqual(this.start, this.end);
        }
        return false;
    }

    public String toString() {
        return this.start + "-" + this.end;
    }

    /* JADX INFO: compiled from: Range.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\u0002\u001a\u00020\u0003J\u0006\u0010\u0004\u001a\u00020\u0003J\u0006\u0010\u0005\u001a\u00020\u0003J\u0006\u0010\n\u001a\u00020\u000bJ\n\u0010\f\u001a\u00020\rH\u0096\u0080\u0004J\u000e\u0010\u000e\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b\u000fJ\t\u0010\u0010\u001a\u00020\u0003HÂ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÂ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/Range$Position;", "", CloudStreamPackage.POSITION_EXTRA, "", "lineNumber", "columnNumber", "<init>", "(III)V", "getPos$ksoup_release", "()I", "isTracked", "", "toString", "", "component1", "component1$ksoup_release", "component2", "component3", "copy", "equals", "other", "hashCode", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Position {
        private final int columnNumber;
        private final int lineNumber;
        private final int pos;

        /* JADX INFO: renamed from: component2, reason: from getter */
        private final int getLineNumber() {
            return this.lineNumber;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        private final int getColumnNumber() {
            return this.columnNumber;
        }

        public static /* synthetic */ Position copy$default(Position position, int i, int i2, int i3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                i = position.pos;
            }
            if ((i4 & 2) != 0) {
                i2 = position.lineNumber;
            }
            if ((i4 & 4) != 0) {
                i3 = position.columnNumber;
            }
            return position.copy(i, i2, i3);
        }

        /* JADX INFO: renamed from: component1$ksoup_release, reason: from getter */
        public final int getPos() {
            return this.pos;
        }

        public final Position copy(int pos, int lineNumber, int columnNumber) {
            return new Position(pos, lineNumber, columnNumber);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Position)) {
                return false;
            }
            Position position = (Position) other;
            return this.pos == position.pos && this.lineNumber == position.lineNumber && this.columnNumber == position.columnNumber;
        }

        public int hashCode() {
            return (((this.pos * 31) + this.lineNumber) * 31) + this.columnNumber;
        }

        public Position(int i, int i2, int i3) {
            this.pos = i;
            this.lineNumber = i2;
            this.columnNumber = i3;
        }

        public final int getPos$ksoup_release() {
            return this.pos;
        }

        public final int pos() {
            return this.pos;
        }

        public final int lineNumber() {
            return this.lineNumber;
        }

        public final int columnNumber() {
            return this.columnNumber;
        }

        public final boolean isTracked() {
            return !Intrinsics.areEqual(this, Range.UntrackedPos);
        }

        public String toString() {
            return this.lineNumber + "," + this.columnNumber + ":" + this.pos;
        }
    }

    /* JADX INFO: compiled from: Range.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\u0002\u001a\u00020\u0003J\u0006\u0010\u0004\u001a\u00020\u0003J\n\u0010\u0007\u001a\u00020\bH\u0096\u0080\u0004J\t\u0010\t\u001a\u00020\u0003HÂ\u0003J\t\u0010\n\u001a\u00020\u0003HÂ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/Range$AttributeRange;", "", "nameRange", "Lcom/fleeksoft/ksoup/nodes/Range;", "valueRange", "<init>", "(Lcom/fleeksoft/ksoup/nodes/Range;Lcom/fleeksoft/ksoup/nodes/Range;)V", "toString", "", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class AttributeRange {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final AttributeRange UntrackedAttr = new AttributeRange(Range.Untracked, Range.Untracked);
        private final Range nameRange;
        private final Range valueRange;

        /* JADX INFO: renamed from: component1, reason: from getter */
        private final Range getNameRange() {
            return this.nameRange;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        private final Range getValueRange() {
            return this.valueRange;
        }

        public static /* synthetic */ AttributeRange copy$default(AttributeRange attributeRange, Range range, Range range2, int i, Object obj) {
            if ((i & 1) != 0) {
                range = attributeRange.nameRange;
            }
            if ((i & 2) != 0) {
                range2 = attributeRange.valueRange;
            }
            return attributeRange.copy(range, range2);
        }

        public final AttributeRange copy(Range nameRange, Range valueRange) {
            Intrinsics.checkNotNullParameter(nameRange, "nameRange");
            Intrinsics.checkNotNullParameter(valueRange, "valueRange");
            return new AttributeRange(nameRange, valueRange);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AttributeRange)) {
                return false;
            }
            AttributeRange attributeRange = (AttributeRange) other;
            return Intrinsics.areEqual(this.nameRange, attributeRange.nameRange) && Intrinsics.areEqual(this.valueRange, attributeRange.valueRange);
        }

        public int hashCode() {
            return (this.nameRange.hashCode() * 31) + this.valueRange.hashCode();
        }

        public AttributeRange(Range nameRange, Range valueRange) {
            Intrinsics.checkNotNullParameter(nameRange, "nameRange");
            Intrinsics.checkNotNullParameter(valueRange, "valueRange");
            this.nameRange = nameRange;
            this.valueRange = valueRange;
        }

        public final Range nameRange() {
            return this.nameRange;
        }

        public final Range valueRange() {
            return this.valueRange;
        }

        public String toString() {
            StringBuilder sbBorrowBuilder = StringUtil.INSTANCE.borrowBuilder();
            sbBorrowBuilder.append(this.nameRange);
            sbBorrowBuilder.append('=');
            sbBorrowBuilder.append(this.valueRange);
            StringUtil stringUtil = StringUtil.INSTANCE;
            Intrinsics.checkNotNull(sbBorrowBuilder);
            return stringUtil.releaseBuilder(sbBorrowBuilder);
        }

        /* JADX INFO: compiled from: Range.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/Range$AttributeRange$Companion;", "", "<init>", "()V", "UntrackedAttr", "Lcom/fleeksoft/ksoup/nodes/Range$AttributeRange;", "getUntrackedAttr", "()Lcom/fleeksoft/ksoup/nodes/Range$AttributeRange;", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final AttributeRange getUntrackedAttr() {
                return AttributeRange.UntrackedAttr;
            }
        }
    }

    /* JADX INFO: compiled from: Range.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/Range$Companion;", "", "<init>", "()V", "UntrackedPos", "Lcom/fleeksoft/ksoup/nodes/Range$Position;", "Untracked", "Lcom/fleeksoft/ksoup/nodes/Range;", "of", "node", "Lcom/fleeksoft/ksoup/nodes/Node;", "start", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Range of(Node node, boolean start) {
            Object objUserData;
            Intrinsics.checkNotNullParameter(node, "node");
            String str = start ? SharedConstants.RangeKey : SharedConstants.EndRangeKey;
            if (node.hasAttributes() && (objUserData = node.attributes().userData(str)) != null) {
                return (Range) objUserData;
            }
            return Range.Untracked;
        }
    }

    static {
        Position position = new Position(-1, -1, -1);
        UntrackedPos = position;
        Untracked = new Range(position, position);
    }
}
