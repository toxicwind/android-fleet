package kotlinx.datetime.format;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LocalDateFormat.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006BA\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\u0004\u0012\u0006\u0010\u000b\u001a\u00020\u0004\u0012\u0006\u0010\f\u001a\u00020\u0004\u0012\u0006\u0010\r\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u000eJ\b\u0010\u0011\u001a\u00020\u0004H\u0016J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0018"}, d2 = {"Lkotlinx/datetime/format/DayOfWeekNames;", "", "names", "", "", "<init>", "(Ljava/util/List;)V", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getNames", "()Ljava/util/List;", "toString", "equals", "", "other", "hashCode", "", "Companion", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class DayOfWeekNames {
    private final List<String> names;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final DayOfWeekNames ENGLISH_FULL = new DayOfWeekNames(CollectionsKt.listOf((Object[]) new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"}));
    private static final DayOfWeekNames ENGLISH_ABBREVIATED = new DayOfWeekNames(CollectionsKt.listOf((Object[]) new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"}));

    public DayOfWeekNames(List<String> names) {
        Intrinsics.checkNotNullParameter(names, "names");
        this.names = names;
        if (names.size() != 7) {
            throw new IllegalArgumentException("Day of week names must contain exactly 7 elements".toString());
        }
        Iterator<Integer> it = CollectionsKt.getIndices(names).iterator();
        while (it.hasNext()) {
            int iNextInt = ((IntIterator) it).nextInt();
            if (this.names.get(iNextInt).length() <= 0) {
                throw new IllegalArgumentException("A day-of-week name can not be empty".toString());
            }
            for (int i = 0; i < iNextInt; i++) {
                if (Intrinsics.areEqual(this.names.get(iNextInt), this.names.get(i))) {
                    throw new IllegalArgumentException(("Day-of-week names must be unique, but '" + this.names.get(iNextInt) + "' was repeated").toString());
                }
            }
        }
    }

    public final List<String> getNames() {
        return this.names;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DayOfWeekNames(String monday, String tuesday, String wednesday, String thursday, String friday, String saturday, String sunday) {
        this(CollectionsKt.listOf((Object[]) new String[]{monday, tuesday, wednesday, thursday, friday, saturday, sunday}));
        Intrinsics.checkNotNullParameter(monday, "monday");
        Intrinsics.checkNotNullParameter(tuesday, "tuesday");
        Intrinsics.checkNotNullParameter(wednesday, "wednesday");
        Intrinsics.checkNotNullParameter(thursday, "thursday");
        Intrinsics.checkNotNullParameter(friday, "friday");
        Intrinsics.checkNotNullParameter(saturday, "saturday");
        Intrinsics.checkNotNullParameter(sunday, "sunday");
    }

    /* JADX INFO: compiled from: LocalDateFormat.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, d2 = {"Lkotlinx/datetime/format/DayOfWeekNames$Companion;", "", "<init>", "()V", "ENGLISH_FULL", "Lkotlinx/datetime/format/DayOfWeekNames;", "getENGLISH_FULL", "()Lkotlinx/datetime/format/DayOfWeekNames;", "ENGLISH_ABBREVIATED", "getENGLISH_ABBREVIATED", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DayOfWeekNames getENGLISH_FULL() {
            return DayOfWeekNames.ENGLISH_FULL;
        }

        public final DayOfWeekNames getENGLISH_ABBREVIATED() {
            return DayOfWeekNames.ENGLISH_ABBREVIATED;
        }
    }

    /* JADX INFO: renamed from: kotlinx.datetime.format.DayOfWeekNames$toString$1, reason: invalid class name */
    /* JADX INFO: compiled from: LocalDateFormat.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<String, String> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(1, String.class, "toString", "toString()Ljava/lang/String;", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public final String invoke(String p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return p0.toString();
        }
    }

    public String toString() {
        return CollectionsKt.joinToString$default(this.names, ", ", "DayOfWeekNames(", ")", 0, null, AnonymousClass1.INSTANCE, 24, null);
    }

    public boolean equals(Object other) {
        return (other instanceof DayOfWeekNames) && Intrinsics.areEqual(this.names, ((DayOfWeekNames) other).names);
    }

    public int hashCode() {
        return this.names.hashCode();
    }
}
