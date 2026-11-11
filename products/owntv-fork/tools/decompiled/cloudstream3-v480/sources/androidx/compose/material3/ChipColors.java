package androidx.compose.material3;

import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Chip.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0002\u0010\u000bJ\u001d\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0017H\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019J`\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\u00172\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u001f\u001a\u00020 H\u0016J\u001d\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0017H\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b!\u0010\u0019J\u001d\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0017H\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\"\u0010\u0019J\u001d\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0017H\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b#\u0010\u0019R\u0019\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0019\u0010\u0007\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u000f\u0010\rR\u0019\u0010\b\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u0010\u0010\rR\u0019\u0010\t\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u0011\u0010\rR\u0019\u0010\n\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u0012\u0010\rR\u0019\u0010\u0004\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u0013\u0010\rR\u0019\u0010\u0005\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u0014\u0010\rR\u0019\u0010\u0006\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u0015\u0010\r\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006$"}, d2 = {"Landroidx/compose/material3/ChipColors;", "", "containerColor", "Landroidx/compose/ui/graphics/Color;", "labelColor", "leadingIconContentColor", "trailingIconContentColor", "disabledContainerColor", "disabledLabelColor", "disabledLeadingIconContentColor", "disabledTrailingIconContentColor", "(JJJJJJJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getContainerColor-0d7_KjU", "()J", "J", "getDisabledContainerColor-0d7_KjU", "getDisabledLabelColor-0d7_KjU", "getDisabledLeadingIconContentColor-0d7_KjU", "getDisabledTrailingIconContentColor-0d7_KjU", "getLabelColor-0d7_KjU", "getLeadingIconContentColor-0d7_KjU", "getTrailingIconContentColor-0d7_KjU", "enabled", "", "containerColor-vNxB06k$material3_release", "(Z)J", "copy", "copy-FD3wquc", "(JJJJJJJJ)Landroidx/compose/material3/ChipColors;", "equals", "other", "hashCode", "", "labelColor-vNxB06k$material3_release", "leadingIconContentColor-vNxB06k$material3_release", "trailingIconContentColor-vNxB06k$material3_release", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ChipColors {
    public static final int $stable = 0;
    private final long containerColor;
    private final long disabledContainerColor;
    private final long disabledLabelColor;
    private final long disabledLeadingIconContentColor;
    private final long disabledTrailingIconContentColor;
    private final long labelColor;
    private final long leadingIconContentColor;
    private final long trailingIconContentColor;

    public /* synthetic */ ChipColors(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3, j4, j5, j6, j7, j8);
    }

    private ChipColors(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8) {
        this.containerColor = j;
        this.labelColor = j2;
        this.leadingIconContentColor = j3;
        this.trailingIconContentColor = j4;
        this.disabledContainerColor = j5;
        this.disabledLabelColor = j6;
        this.disabledLeadingIconContentColor = j7;
        this.disabledTrailingIconContentColor = j8;
    }

    /* JADX INFO: renamed from: getContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getContainerColor() {
        return this.containerColor;
    }

    /* JADX INFO: renamed from: getLabelColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getLabelColor() {
        return this.labelColor;
    }

    /* JADX INFO: renamed from: getLeadingIconContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getLeadingIconContentColor() {
        return this.leadingIconContentColor;
    }

    /* JADX INFO: renamed from: getTrailingIconContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getTrailingIconContentColor() {
        return this.trailingIconContentColor;
    }

    /* JADX INFO: renamed from: getDisabledContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledContainerColor() {
        return this.disabledContainerColor;
    }

    /* JADX INFO: renamed from: getDisabledLabelColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledLabelColor() {
        return this.disabledLabelColor;
    }

    /* JADX INFO: renamed from: getDisabledLeadingIconContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledLeadingIconContentColor() {
        return this.disabledLeadingIconContentColor;
    }

    /* JADX INFO: renamed from: getDisabledTrailingIconContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledTrailingIconContentColor() {
        return this.disabledTrailingIconContentColor;
    }

    /* JADX INFO: renamed from: copy-FD3wquc$default, reason: not valid java name */
    public static /* synthetic */ ChipColors m1571copyFD3wquc$default(ChipColors chipColors, long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, int i, Object obj) {
        long j9;
        long j10;
        long j11 = (i & 1) != 0 ? chipColors.containerColor : j;
        long j12 = (i & 2) != 0 ? chipColors.labelColor : j2;
        long j13 = (i & 4) != 0 ? chipColors.leadingIconContentColor : j3;
        long j14 = (i & 8) != 0 ? chipColors.trailingIconContentColor : j4;
        long j15 = (i & 16) != 0 ? chipColors.disabledContainerColor : j5;
        long j16 = (i & 32) != 0 ? chipColors.disabledLabelColor : j6;
        long j17 = (i & 64) != 0 ? chipColors.disabledLeadingIconContentColor : j7;
        if ((i & 128) != 0) {
            j9 = chipColors.disabledTrailingIconContentColor;
            j10 = j11;
        } else {
            j9 = j8;
            j10 = j11;
        }
        return chipColors.m1573copyFD3wquc(j10, j12, j13, j14, j15, j16, j17, j9);
    }

    /* JADX INFO: renamed from: copy-FD3wquc, reason: not valid java name */
    public final ChipColors m1573copyFD3wquc(long containerColor, long labelColor, long leadingIconContentColor, long trailingIconContentColor, long disabledContainerColor, long disabledLabelColor, long disabledLeadingIconContentColor, long disabledTrailingIconContentColor) {
        return new ChipColors(containerColor != 16 ? containerColor : this.containerColor, labelColor != 16 ? labelColor : this.labelColor, leadingIconContentColor != 16 ? leadingIconContentColor : this.leadingIconContentColor, trailingIconContentColor != 16 ? trailingIconContentColor : this.trailingIconContentColor, disabledContainerColor != 16 ? disabledContainerColor : this.disabledContainerColor, disabledLabelColor != 16 ? disabledLabelColor : this.disabledLabelColor, disabledLeadingIconContentColor != 16 ? disabledLeadingIconContentColor : this.disabledLeadingIconContentColor, disabledTrailingIconContentColor != 16 ? disabledTrailingIconContentColor : this.disabledTrailingIconContentColor, null);
    }

    /* JADX INFO: renamed from: containerColor-vNxB06k$material3_release, reason: not valid java name */
    public final long m1572containerColorvNxB06k$material3_release(boolean enabled) {
        return enabled ? this.containerColor : this.disabledContainerColor;
    }

    /* JADX INFO: renamed from: labelColor-vNxB06k$material3_release, reason: not valid java name */
    public final long m1582labelColorvNxB06k$material3_release(boolean enabled) {
        return enabled ? this.labelColor : this.disabledLabelColor;
    }

    /* JADX INFO: renamed from: leadingIconContentColor-vNxB06k$material3_release, reason: not valid java name */
    public final long m1583leadingIconContentColorvNxB06k$material3_release(boolean enabled) {
        return enabled ? this.leadingIconContentColor : this.disabledLeadingIconContentColor;
    }

    /* JADX INFO: renamed from: trailingIconContentColor-vNxB06k$material3_release, reason: not valid java name */
    public final long m1584trailingIconContentColorvNxB06k$material3_release(boolean enabled) {
        return enabled ? this.trailingIconContentColor : this.disabledTrailingIconContentColor;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof ChipColors)) {
            return false;
        }
        ChipColors chipColors = (ChipColors) other;
        return Color.m4153equalsimpl0(this.containerColor, chipColors.containerColor) && Color.m4153equalsimpl0(this.labelColor, chipColors.labelColor) && Color.m4153equalsimpl0(this.leadingIconContentColor, chipColors.leadingIconContentColor) && Color.m4153equalsimpl0(this.trailingIconContentColor, chipColors.trailingIconContentColor) && Color.m4153equalsimpl0(this.disabledContainerColor, chipColors.disabledContainerColor) && Color.m4153equalsimpl0(this.disabledLabelColor, chipColors.disabledLabelColor) && Color.m4153equalsimpl0(this.disabledLeadingIconContentColor, chipColors.disabledLeadingIconContentColor) && Color.m4153equalsimpl0(this.disabledTrailingIconContentColor, chipColors.disabledTrailingIconContentColor);
    }

    public int hashCode() {
        return (((((((((((((Color.m4159hashCodeimpl(this.containerColor) * 31) + Color.m4159hashCodeimpl(this.labelColor)) * 31) + Color.m4159hashCodeimpl(this.leadingIconContentColor)) * 31) + Color.m4159hashCodeimpl(this.trailingIconContentColor)) * 31) + Color.m4159hashCodeimpl(this.disabledContainerColor)) * 31) + Color.m4159hashCodeimpl(this.disabledLabelColor)) * 31) + Color.m4159hashCodeimpl(this.disabledLeadingIconContentColor)) * 31) + Color.m4159hashCodeimpl(this.disabledTrailingIconContentColor);
    }
}
