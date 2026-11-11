package kotlinx.datetime.format;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.internal.format.DecimalFractionFieldFormatDirective;

/* JADX INFO: compiled from: LocalTimeFormat.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00132\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0013B'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007¢\u0006\u0004\b\b\u0010\tJ\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0004H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0014"}, d2 = {"Lkotlinx/datetime/format/FractionalSecondDirective;", "Lkotlinx/datetime/internal/format/DecimalFractionFieldFormatDirective;", "Lkotlinx/datetime/format/TimeFieldContainer;", "minDigits", "", "maxDigits", "zerosToAdd", "", "<init>", "(IILjava/util/List;)V", "builderRepresentation", "", "getBuilderRepresentation", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "Companion", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class FractionalSecondDirective extends DecimalFractionFieldFormatDirective<TimeFieldContainer> {
    private final int maxDigits;
    private final int minDigits;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final List<Integer> NO_EXTRA_ZEROS = CollectionsKt.listOf((Object[]) new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0});
    private static final List<Integer> GROUP_BY_THREE = CollectionsKt.listOf((Object[]) new Integer[]{2, 1, 0, 2, 1, 0, 2, 1, 0});

    public /* synthetic */ FractionalSecondDirective(int i, int i2, List list, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, (i3 & 4) != 0 ? NO_EXTRA_ZEROS : list);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FractionalSecondDirective(int i, int i2, List<Integer> zerosToAdd) {
        super(TimeFields.INSTANCE.getFractionOfSecond(), i, i2, zerosToAdd);
        Intrinsics.checkNotNullParameter(zerosToAdd, "zerosToAdd");
        this.minDigits = i;
        this.maxDigits = i2;
    }

    @Override // kotlinx.datetime.internal.format.FieldFormatDirective
    public String getBuilderRepresentation() {
        int i = this.minDigits;
        if (i == 1 && this.maxDigits == 9) {
            return "secondFraction()";
        }
        int i2 = this.maxDigits;
        if (i == 1) {
            return "secondFraction(maxLength = " + i2 + ')';
        }
        if (i2 == 1) {
            return "secondFraction(minLength = " + this.minDigits + ')';
        }
        int i3 = this.minDigits;
        if (i2 == i) {
            return "secondFraction(" + i3 + ')';
        }
        return "secondFraction(" + i3 + ", " + this.maxDigits + ')';
    }

    public boolean equals(Object other) {
        if (!(other instanceof FractionalSecondDirective)) {
            return false;
        }
        FractionalSecondDirective fractionalSecondDirective = (FractionalSecondDirective) other;
        return this.minDigits == fractionalSecondDirective.minDigits && this.maxDigits == fractionalSecondDirective.maxDigits;
    }

    public int hashCode() {
        return (this.minDigits * 31) + this.maxDigits;
    }

    /* JADX INFO: compiled from: LocalTimeFormat.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lkotlinx/datetime/format/FractionalSecondDirective$Companion;", "", "<init>", "()V", "NO_EXTRA_ZEROS", "", "", "getNO_EXTRA_ZEROS", "()Ljava/util/List;", "GROUP_BY_THREE", "getGROUP_BY_THREE", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<Integer> getNO_EXTRA_ZEROS() {
            return FractionalSecondDirective.NO_EXTRA_ZEROS;
        }

        public final List<Integer> getGROUP_BY_THREE() {
            return FractionalSecondDirective.GROUP_BY_THREE;
        }
    }
}
