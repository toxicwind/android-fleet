package kotlinx.io;

import com.google.common.util.concurrent.Striped$SmallLazyStriped$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SegmentPool.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0017\u001a\u00020\tH\u0087\u0080\u0004J\n\u0010\u0018\u001a\u00020\tH\u0083\u0080\u0004J\u0012\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\tH\u0087\u0080\u0004J\u0012\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\tH\u0083\u0080\u0004J\n\u0010\u001d\u001a\u00020\u001eH\u0087\u0080\u0004J\n\u0010\u001f\u001a\u00020\u0005H\u0082\u0080\u0004J\n\u0010 \u001a\u00020\u0005H\u0082\u0080\u0004J\u0012\u0010!\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020#H\u0082\u0080\u0004R\u0015\u0010\u0004\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000f\u0010\b\u001a\u00020\tX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0015\u0010\n\u001a\u00020\u0005X\u0080\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u000f\u0010\f\u001a\u00020\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\r\u001a\u00020\u000eX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0015\u0010\u000f\u001a\u00020\u0005X\u0080\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0007R\u000f\u0010\u0011\u001a\u00020\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0017\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0013X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0017\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0013X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0015\u0010\u0015\u001a\u00020\u00058FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0007¨\u0006$"}, d2 = {"Lkotlinx/io/SegmentPool;", "", "<init>", "()V", "MAX_SIZE", "", "getMAX_SIZE", "()I", "LOCK", "Lkotlinx/io/Segment;", "HASH_BUCKET_COUNT", "getHASH_BUCKET_COUNT$kotlinx_io_core", "HASH_BUCKET_COUNT_L2", "DEFAULT_SECOND_LEVEL_POOL_TOTAL_SIZE", "", "SECOND_LEVEL_POOL_TOTAL_SIZE", "getSECOND_LEVEL_POOL_TOTAL_SIZE$kotlinx_io_core", "SECOND_LEVEL_POOL_BUCKET_SIZE", "hashBuckets", "Ljava/util/concurrent/atomic/AtomicReferenceArray;", "hashBucketsL2", "byteCount", "getByteCount", "take", "takeL2", "recycle", "", "segment", "recycleL2", "tracker", "Lkotlinx/io/SegmentCopyTracker;", "l1BucketId", "l2BucketId", "bucketId", "mask", "", "kotlinx-io-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SegmentPool {
    private static final String DEFAULT_SECOND_LEVEL_POOL_TOTAL_SIZE;
    private static final int HASH_BUCKET_COUNT;
    private static final int HASH_BUCKET_COUNT_L2;
    private static final int SECOND_LEVEL_POOL_BUCKET_SIZE;
    private static final int SECOND_LEVEL_POOL_TOTAL_SIZE;
    private static final AtomicReferenceArray<Segment> hashBuckets;
    private static final AtomicReferenceArray<Segment> hashBucketsL2;
    public static final SegmentPool INSTANCE = new SegmentPool();
    private static final int MAX_SIZE = 65536;
    private static final Segment LOCK = Segment.INSTANCE.new$kotlinx_io_core(new byte[0], 0, 0, null, false);

    private SegmentPool() {
    }

    static {
        int iHighestOneBit = Integer.highestOneBit((Runtime.getRuntime().availableProcessors() * 2) - 1);
        HASH_BUCKET_COUNT = iHighestOneBit;
        int iCoerceAtLeast = RangesKt.coerceAtLeast(iHighestOneBit / 2, 1);
        HASH_BUCKET_COUNT_L2 = iCoerceAtLeast;
        String str = Intrinsics.areEqual(System.getProperty("java.vm.name"), "Dalvik") ? "0" : "4194304";
        DEFAULT_SECOND_LEVEL_POOL_TOTAL_SIZE = str;
        String property = System.getProperty("kotlinx.io.pool.size.bytes", str);
        Intrinsics.checkNotNullExpressionValue(property, "getProperty(...)");
        Integer intOrNull = StringsKt.toIntOrNull(property);
        int iCoerceAtLeast2 = intOrNull != null ? RangesKt.coerceAtLeast(intOrNull.intValue(), 0) : 0;
        SECOND_LEVEL_POOL_TOTAL_SIZE = iCoerceAtLeast2;
        SECOND_LEVEL_POOL_BUCKET_SIZE = RangesKt.coerceAtLeast(iCoerceAtLeast2 / iCoerceAtLeast, 8192);
        hashBuckets = new AtomicReferenceArray<>(iHighestOneBit);
        hashBucketsL2 = new AtomicReferenceArray<>(iCoerceAtLeast);
    }

    public final int getMAX_SIZE() {
        return MAX_SIZE;
    }

    public final int getHASH_BUCKET_COUNT$kotlinx_io_core() {
        return HASH_BUCKET_COUNT;
    }

    public final int getSECOND_LEVEL_POOL_TOTAL_SIZE$kotlinx_io_core() {
        return SECOND_LEVEL_POOL_TOTAL_SIZE;
    }

    public final int getByteCount() {
        Segment segment = hashBuckets.get(l1BucketId());
        if (segment == null) {
            return 0;
        }
        return segment.getLimit();
    }

    @JvmStatic
    public static final Segment take() {
        Segment segment;
        Segment andSet;
        AtomicReferenceArray<Segment> atomicReferenceArray = hashBuckets;
        int iL1BucketId = INSTANCE.l1BucketId();
        do {
            segment = LOCK;
            andSet = atomicReferenceArray.getAndSet(iL1BucketId, segment);
        } while (Intrinsics.areEqual(andSet, segment));
        if (andSet == null) {
            atomicReferenceArray.set(iL1BucketId, null);
            if (SECOND_LEVEL_POOL_TOTAL_SIZE > 0) {
                return takeL2();
            }
            return Segment.INSTANCE.new$kotlinx_io_core();
        }
        atomicReferenceArray.set(iL1BucketId, andSet.getNext());
        andSet.setNext(null);
        andSet.setLimit(0);
        return andSet;
    }

    @JvmStatic
    private static final Segment takeL2() {
        AtomicReferenceArray<Segment> atomicReferenceArray = hashBucketsL2;
        int iL2BucketId = INSTANCE.l2BucketId();
        int i = 0;
        while (true) {
            Segment segment = LOCK;
            Segment andSet = atomicReferenceArray.getAndSet(iL2BucketId, segment);
            if (!Intrinsics.areEqual(andSet, segment)) {
                if (andSet == null) {
                    atomicReferenceArray.set(iL2BucketId, null);
                    int i2 = HASH_BUCKET_COUNT_L2;
                    if (i >= i2) {
                        return Segment.INSTANCE.new$kotlinx_io_core();
                    }
                    iL2BucketId = (iL2BucketId + 1) & (i2 - 1);
                    i++;
                } else {
                    atomicReferenceArray.set(iL2BucketId, andSet.getNext());
                    andSet.setNext(null);
                    andSet.setLimit(0);
                    return andSet;
                }
            }
        }
    }

    @JvmStatic
    public static final void recycle(Segment segment) {
        Intrinsics.checkNotNullParameter(segment, "segment");
        if (segment.getNext() != null || segment.getPrev() != null) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        SegmentCopyTracker copyTracker$kotlinx_io_core = segment.getCopyTracker();
        if (copyTracker$kotlinx_io_core != null && copyTracker$kotlinx_io_core.removeCopy()) {
            return;
        }
        AtomicReferenceArray<Segment> atomicReferenceArray = hashBuckets;
        int iL1BucketId = INSTANCE.l1BucketId();
        segment.setPos(0);
        segment.owner = true;
        while (true) {
            Segment segment2 = atomicReferenceArray.get(iL1BucketId);
            if (segment2 != LOCK) {
                int limit = segment2 != null ? segment2.getLimit() : 0;
                if (limit >= MAX_SIZE) {
                    if (SECOND_LEVEL_POOL_TOTAL_SIZE > 0) {
                        recycleL2(segment);
                        return;
                    }
                    return;
                } else {
                    segment.setNext(segment2);
                    segment.setLimit(limit + 8192);
                    if (Striped$SmallLazyStriped$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceArray, iL1BucketId, segment2, segment)) {
                        return;
                    }
                }
            }
        }
    }

    @JvmStatic
    private static final void recycleL2(Segment segment) {
        segment.setPos(0);
        segment.owner = true;
        int iL2BucketId = INSTANCE.l2BucketId();
        AtomicReferenceArray<Segment> atomicReferenceArray = hashBucketsL2;
        int i = 0;
        while (true) {
            Segment segment2 = atomicReferenceArray.get(iL2BucketId);
            if (segment2 != LOCK) {
                int limit = (segment2 != null ? segment2.getLimit() : 0) + 8192;
                if (limit > SECOND_LEVEL_POOL_BUCKET_SIZE) {
                    int i2 = HASH_BUCKET_COUNT_L2;
                    if (i >= i2) {
                        return;
                    }
                    i++;
                    iL2BucketId = (iL2BucketId + 1) & (i2 - 1);
                } else {
                    segment.setNext(segment2);
                    segment.setLimit(limit);
                    if (Striped$SmallLazyStriped$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceArray, iL2BucketId, segment2, segment)) {
                        return;
                    }
                }
            }
        }
    }

    @JvmStatic
    public static final SegmentCopyTracker tracker() {
        return new RefCountingCopyTracker();
    }

    private final int l1BucketId() {
        return bucketId(((long) HASH_BUCKET_COUNT) - 1);
    }

    private final int l2BucketId() {
        return bucketId(((long) HASH_BUCKET_COUNT_L2) - 1);
    }

    private final int bucketId(long mask) {
        return (int) (mask & Thread.currentThread().getId());
    }
}
