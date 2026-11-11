package androidx.compose.runtime.snapshots;

import androidx.mediarouter.media.GlobalMediaRouter;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: SnapshotIdSet.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0016\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010(\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0001\u0018\u0000 ,2\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001:\u0001,B5\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\n\u0010\u0006\u001a\u00060\u0002j\u0002`\u0003\u0012\u000e\u0010\u0007\u001a\n\u0018\u00010\bj\u0004\u0018\u0001`\t¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\u000f2\n\u0010\u0010\u001a\u00060\u0002j\u0002`\u0003¢\u0006\u0002\u0010\u0011J\u0017\u0010\u0012\u001a\u00020\u00002\n\u0010\u0010\u001a\u00060\u0002j\u0002`\u0003¢\u0006\u0002\u0010\u0013J\u0017\u0010\u0014\u001a\u00020\u00002\n\u0010\u0010\u001a\u00060\u0002j\u0002`\u0003¢\u0006\u0002\u0010\u0013J\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0000J\u000e\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0000J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0000J\u0013\u0010\u001a\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u001bH\u0096\u0002J>\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u00002+\u0010\u001e\u001a'\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020\u00000\u001fH\u0082\bJ!\u0010#\u001a\u00020$2\u0016\u0010%\u001a\u0012\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020$0&H\u0086\bJ\u001b\u0010'\u001a\u00060\u0002j\u0002`\u00032\n\u0010(\u001a\u00060\u0002j\u0002`\u0003¢\u0006\u0002\u0010)J\b\u0010*\u001a\u00020+H\u0016R\u000e\u0010\u0004\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00060\u0002j\u0002`\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR\u0018\u0010\u0007\u001a\n\u0018\u00010\bj\u0004\u0018\u0001`\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\r¨\u0006-"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "", "", "Landroidx/compose/runtime/snapshots/SnapshotId;", "upperSet", "lowerSet", "lowerBound", "belowBound", "", "Landroidx/compose/runtime/snapshots/SnapshotIdArray;", "<init>", "(JJJ[J)V", "J", "[J", "get", "", "id", "(J)Z", "set", "(J)Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "clear", "andNot", "ids", "and", "or", "bits", "iterator", "", "fastFold", "initial", "operation", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "acc", "fastForEach", "", "block", "Lkotlin/Function1;", "lowest", "default", "(J)J", "toString", "", "Companion", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SnapshotIdSet implements Iterable<Long>, KMappedMarker {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final SnapshotIdSet EMPTY = new SnapshotIdSet(0, 0, 0, null);
    private final long[] belowBound;
    private final long lowerBound;
    private final long lowerSet;
    private final long upperSet;

    private SnapshotIdSet(long j, long j2, long j3, long[] jArr) {
        this.upperSet = j;
        this.lowerSet = j2;
        this.lowerBound = j3;
        this.belowBound = jArr;
    }

    public final boolean get(long id) {
        long[] jArr;
        long j = id - this.lowerBound;
        if (Intrinsics.compare(j, 0L) >= 0 && Intrinsics.compare(j, 64L) < 0) {
            return ((1 << ((int) j)) & this.lowerSet) != 0;
        }
        if (Intrinsics.compare(j, 64L) < 0 || Intrinsics.compare(j, 128L) >= 0) {
            return Intrinsics.compare(j, 0L) <= 0 && (jArr = this.belowBound) != null && SnapshotId_jvmKt.binarySearch(jArr, id) >= 0;
        }
        return ((1 << (((int) j) + (-64))) & this.upperSet) != 0;
    }

    public final SnapshotIdSet set(long id) {
        long j;
        long j2;
        long[] array;
        long j3 = id - this.lowerBound;
        long j4 = 0;
        if (Intrinsics.compare(j3, 0L) >= 0 && Intrinsics.compare(j3, 64L) < 0) {
            long j5 = 1 << ((int) j3);
            long j6 = this.lowerSet;
            if ((j6 & j5) == 0) {
                return new SnapshotIdSet(this.upperSet, j6 | j5, this.lowerBound, this.belowBound);
            }
        } else if (Intrinsics.compare(j3, 64L) >= 0 && Intrinsics.compare(j3, 128L) < 0) {
            long j7 = 1 << (((int) j3) - 64);
            long j8 = this.upperSet;
            if ((j8 & j7) == 0) {
                return new SnapshotIdSet(j8 | j7, this.lowerSet, this.lowerBound, this.belowBound);
            }
        } else if (Intrinsics.compare(j3, 128L) >= 0) {
            if (!get(id)) {
                long j9 = this.upperSet;
                long j10 = this.lowerSet;
                long j11 = this.lowerBound;
                long j12 = ((id + 1) / 64) * 64;
                if (Intrinsics.compare(j12, 0L) < 0) {
                    j12 = 9223372036854775680L;
                }
                SnapshotIdArrayBuilder snapshotIdArrayBuilder = null;
                long j13 = j9;
                while (true) {
                    if (Intrinsics.compare(j11, j12) >= 0) {
                        j = j10;
                        j2 = j11;
                        break;
                    }
                    if (j10 != j4) {
                        if (snapshotIdArrayBuilder == null) {
                            snapshotIdArrayBuilder = new SnapshotIdArrayBuilder(this.belowBound);
                        }
                        int i = 0;
                        while (i < 64) {
                            long j14 = j4;
                            if ((j10 & (1 << i)) != j4) {
                                snapshotIdArrayBuilder.add(((long) i) + j11);
                            }
                            i++;
                            j4 = j14;
                        }
                    }
                    j = j4;
                    if (j13 == j) {
                        j2 = j12;
                        break;
                    }
                    j11 += 64;
                    j10 = j13;
                    j4 = j;
                    j13 = j4;
                }
                if (snapshotIdArrayBuilder == null || (array = snapshotIdArrayBuilder.toArray()) == null) {
                    array = this.belowBound;
                }
                return new SnapshotIdSet(j13, j, j2, array).set(id);
            }
        } else {
            long[] jArr = this.belowBound;
            if (jArr == null) {
                return new SnapshotIdSet(this.upperSet, this.lowerSet, this.lowerBound, new long[]{id});
            }
            int iBinarySearch = SnapshotId_jvmKt.binarySearch(jArr, id);
            if (iBinarySearch < 0) {
                return new SnapshotIdSet(this.upperSet, this.lowerSet, this.lowerBound, SnapshotId_jvmKt.withIdInsertedAt(jArr, -(iBinarySearch + 1), id));
            }
        }
        return this;
    }

    public final SnapshotIdSet clear(long id) {
        long[] jArr;
        int iBinarySearch;
        long j = id - this.lowerBound;
        if (Intrinsics.compare(j, 0L) >= 0 && Intrinsics.compare(j, 64L) < 0) {
            long j2 = 1 << ((int) j);
            long j3 = this.lowerSet;
            if ((j3 & j2) != 0) {
                return new SnapshotIdSet(this.upperSet, j3 & (~j2), this.lowerBound, this.belowBound);
            }
        } else if (Intrinsics.compare(j, 64L) >= 0 && Intrinsics.compare(j, 128L) < 0) {
            long j4 = 1 << (((int) j) - 64);
            long j5 = this.upperSet;
            if ((j5 & j4) != 0) {
                return new SnapshotIdSet(j5 & (~j4), this.lowerSet, this.lowerBound, this.belowBound);
            }
        } else if (Intrinsics.compare(j, 0L) < 0 && (jArr = this.belowBound) != null && (iBinarySearch = SnapshotId_jvmKt.binarySearch(jArr, id)) >= 0) {
            return new SnapshotIdSet(this.upperSet, this.lowerSet, this.lowerBound, SnapshotId_jvmKt.withIdRemovedAt(jArr, iBinarySearch));
        }
        return this;
    }

    public final SnapshotIdSet andNot(SnapshotIdSet ids) {
        SnapshotIdSet snapshotIdSet = EMPTY;
        if (ids == snapshotIdSet) {
            return this;
        }
        if (this == snapshotIdSet) {
            return snapshotIdSet;
        }
        long j = ids.lowerBound;
        long j2 = this.lowerBound;
        if (j == j2) {
            long[] jArr = ids.belowBound;
            long[] jArr2 = this.belowBound;
            if (jArr == jArr2) {
                return new SnapshotIdSet((~ids.upperSet) & this.upperSet, this.lowerSet & (~ids.lowerSet), j2, jArr2);
            }
        }
        long[] jArr3 = ids.belowBound;
        if (jArr3 != null) {
            for (long j3 : jArr3) {
                this = this.clear(j3);
            }
        }
        if (ids.lowerSet != 0) {
            for (int i = 0; i < 64; i++) {
                if ((ids.lowerSet & (1 << i)) != 0) {
                    this = this.clear(ids.lowerBound + ((long) i));
                }
            }
        }
        if (ids.upperSet != 0) {
            for (int i2 = 0; i2 < 64; i2++) {
                if ((ids.upperSet & (1 << i2)) != 0) {
                    this = this.clear(ids.lowerBound + ((long) i2) + 64);
                }
            }
        }
        return this;
    }

    public final SnapshotIdSet and(SnapshotIdSet ids) {
        SnapshotIdSet snapshotIdSet = EMPTY;
        if (!Intrinsics.areEqual(ids, snapshotIdSet) && !Intrinsics.areEqual(this, snapshotIdSet)) {
            long j = ids.lowerBound;
            long j2 = this.lowerBound;
            if (j == j2) {
                long[] jArr = ids.belowBound;
                long[] jArr2 = this.belowBound;
                if (jArr == jArr2) {
                    long j3 = this.upperSet;
                    long j4 = ids.upperSet;
                    long j5 = j3 & j4;
                    long j6 = this.lowerSet;
                    long j7 = ids.lowerSet;
                    long j8 = j6 & j7;
                    if (j5 != 0 || j8 != 0 || jArr2 != null) {
                        return new SnapshotIdSet(j3 & j4, j6 & j7, j2, jArr2);
                    }
                }
            }
            int i = 0;
            if (this.belowBound == null) {
                long[] jArr3 = this.belowBound;
                if (jArr3 != null) {
                    for (long j9 : jArr3) {
                        if (ids.get(j9)) {
                            snapshotIdSet = snapshotIdSet.set(j9);
                        }
                    }
                }
                if (this.lowerSet != 0) {
                    for (int i2 = 0; i2 < 64; i2++) {
                        if ((this.lowerSet & (1 << i2)) != 0) {
                            long j10 = this.lowerBound + ((long) i2);
                            if (ids.get(j10)) {
                                snapshotIdSet = snapshotIdSet.set(j10);
                            }
                        }
                    }
                }
                if (this.upperSet != 0) {
                    while (i < 64) {
                        if ((this.upperSet & (1 << i)) != 0) {
                            long j11 = this.lowerBound + ((long) i) + 64;
                            if (ids.get(j11)) {
                                snapshotIdSet = snapshotIdSet.set(j11);
                            }
                        }
                        i++;
                    }
                }
                return snapshotIdSet;
            }
            long[] jArr4 = ids.belowBound;
            if (jArr4 != null) {
                for (long j12 : jArr4) {
                    if (get(j12)) {
                        snapshotIdSet = snapshotIdSet.set(j12);
                    }
                }
            }
            if (ids.lowerSet != 0) {
                for (int i3 = 0; i3 < 64; i3++) {
                    if ((ids.lowerSet & (1 << i3)) != 0) {
                        long j13 = ids.lowerBound + ((long) i3);
                        if (get(j13)) {
                            snapshotIdSet = snapshotIdSet.set(j13);
                        }
                    }
                }
            }
            if (ids.upperSet != 0) {
                while (i < 64) {
                    if ((ids.upperSet & (1 << i)) != 0) {
                        long j14 = ids.lowerBound + ((long) i) + 64;
                        if (get(j14)) {
                            snapshotIdSet = snapshotIdSet.set(j14);
                        }
                    }
                    i++;
                }
            }
            return snapshotIdSet;
        }
        return snapshotIdSet;
    }

    public final SnapshotIdSet or(SnapshotIdSet bits) {
        SnapshotIdSet snapshotIdSet = EMPTY;
        if (bits == snapshotIdSet) {
            return this;
        }
        if (this == snapshotIdSet) {
            return bits;
        }
        long j = bits.lowerBound;
        long j2 = this.lowerBound;
        if (j == j2) {
            long[] jArr = bits.belowBound;
            long[] jArr2 = this.belowBound;
            if (jArr == jArr2) {
                return new SnapshotIdSet(bits.upperSet | this.upperSet, this.lowerSet | bits.lowerSet, j2, jArr2);
            }
        }
        int i = 0;
        if (this.belowBound == null) {
            long[] jArr3 = this.belowBound;
            if (jArr3 != null) {
                for (long j3 : jArr3) {
                    bits = bits.set(j3);
                }
            }
            if (this.lowerSet != 0) {
                for (int i2 = 0; i2 < 64; i2++) {
                    if ((this.lowerSet & (1 << i2)) != 0) {
                        bits = bits.set(this.lowerBound + ((long) i2));
                    }
                }
            }
            if (this.upperSet != 0) {
                while (i < 64) {
                    if ((this.upperSet & (1 << i)) != 0) {
                        bits = bits.set(this.lowerBound + ((long) i) + 64);
                    }
                    i++;
                }
            }
            return bits;
        }
        long[] jArr4 = bits.belowBound;
        if (jArr4 != null) {
            for (long j4 : jArr4) {
                this = this.set(j4);
            }
        }
        if (bits.lowerSet != 0) {
            for (int i3 = 0; i3 < 64; i3++) {
                if ((bits.lowerSet & (1 << i3)) != 0) {
                    this = this.set(bits.lowerBound + ((long) i3));
                }
            }
        }
        if (bits.upperSet != 0) {
            while (i < 64) {
                if ((bits.upperSet & (1 << i)) != 0) {
                    this = this.set(bits.lowerBound + ((long) i) + 64);
                }
                i++;
            }
        }
        return this;
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.snapshots.SnapshotIdSet$iterator$1, reason: invalid class name */
    /* JADX INFO: compiled from: SnapshotIdSet.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "", "Landroidx/compose/runtime/snapshots/SnapshotId;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.runtime.snapshots.SnapshotIdSet$iterator$1", f = "SnapshotIdSet.kt", i = {0, 0, 1, 1, 2, 2}, l = {252, 256, GlobalMediaRouter.CallbackHandler.MSG_ROUTE_UNSELECTED}, m = "invokeSuspend", n = {"$this$sequence", "$this$forEach$iv", "$this$sequence", "index", "$this$sequence", "index"}, s = {"L$0", "L$1", "L$0", "I$0", "L$0", "I$0"})
    static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Long>, Continuation<? super Unit>, Object> {
        int I$0;
        int I$1;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = SnapshotIdSet.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SequenceScope<? super Long> sequenceScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:36:0x00cb  */
        /* JADX WARN: Code duplicated, block: B:38:0x00d8  */
        /* JADX WARN: Code duplicated, block: B:41:0x00f9  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0079 -> B:19:0x007d). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x009b -> B:30:0x00bb). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x00b8 -> B:30:0x00bb). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x00d6 -> B:43:0x00fb). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x00f9 -> B:42:0x00fa). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r21) {
            /*
                Method dump skipped, instruction units count: 256
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.SnapshotIdSet.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Override // java.lang.Iterable
    public Iterator<Long> iterator() {
        return SequencesKt.sequence(new AnonymousClass1(null)).iterator();
    }

    public final void fastForEach(Function1<? super Long, Unit> block) {
        long[] jArr = this.belowBound;
        if (jArr != null) {
            for (long j : jArr) {
                block.invoke(Long.valueOf(j));
            }
        }
        if (this.lowerSet != 0) {
            for (int i = 0; i < 64; i++) {
                if ((this.lowerSet & (1 << i)) != 0) {
                    block.invoke(Long.valueOf(this.lowerBound + ((long) i)));
                }
            }
        }
        if (this.upperSet != 0) {
            for (int i2 = 0; i2 < 64; i2++) {
                if ((this.upperSet & (1 << i2)) != 0) {
                    block.invoke(Long.valueOf(this.lowerBound + ((long) i2) + 64));
                }
            }
        }
    }

    public final long lowest(long j) {
        long[] jArr = this.belowBound;
        if (jArr == null) {
            long j2 = this.lowerSet;
            if (j2 != 0) {
                return this.lowerBound + ((long) Long.numberOfTrailingZeros(j2));
            }
            long j3 = this.upperSet;
            return j3 != 0 ? this.lowerBound + 64 + ((long) Long.numberOfTrailingZeros(j3)) : j;
        }
        return jArr[0];
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" [");
        SnapshotIdSet snapshotIdSet = this;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(snapshotIdSet, 10));
        Iterator<Long> it = snapshotIdSet.iterator();
        while (it.hasNext()) {
            arrayList.add(String.valueOf(it.next().longValue()));
        }
        sb.append(ListUtilsKt.fastJoinToString$default(arrayList, null, null, null, 0, null, null, 63, null));
        sb.append(AbstractJsonLexerKt.END_LIST);
        return sb.toString();
    }

    /* JADX INFO: compiled from: SnapshotIdSet.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotIdSet$Companion;", "", "<init>", "()V", "EMPTY", "Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "getEMPTY", "()Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SnapshotIdSet getEMPTY() {
            return SnapshotIdSet.EMPTY;
        }
    }

    private final SnapshotIdSet fastFold(SnapshotIdSet initial, Function2<? super SnapshotIdSet, ? super Long, SnapshotIdSet> operation) {
        long[] jArr = this.belowBound;
        if (jArr != null) {
            for (long j : jArr) {
                initial = operation.invoke(initial, Long.valueOf(j));
            }
        }
        if (this.lowerSet != 0) {
            for (int i = 0; i < 64; i++) {
                if ((this.lowerSet & (1 << i)) != 0) {
                    initial = operation.invoke(initial, Long.valueOf(this.lowerBound + ((long) i)));
                }
            }
        }
        if (this.upperSet != 0) {
            for (int i2 = 0; i2 < 64; i2++) {
                if ((this.upperSet & (1 << i2)) != 0) {
                    initial = operation.invoke(initial, Long.valueOf(this.lowerBound + ((long) i2) + 64));
                }
            }
        }
        return initial;
    }
}
