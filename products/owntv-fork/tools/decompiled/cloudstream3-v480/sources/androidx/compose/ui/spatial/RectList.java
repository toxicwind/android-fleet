package androidx.compose.ui.spatial;

import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.media3.exoplayer.upstream.CmcdData;
import io.ktor.http.ContentDisposition;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: RectList.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\t\u0010\f\u001a\u00020\bH\u0082\bJ \u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0005H\u0002JL\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\b2\b\b\u0002\u0010\u0018\u001a\u00020\b2\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001aJ\u000e\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\bJ.\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\bJ\u001e\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aJ.\u0010\u001f\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\bJ\u001e\u0010 \u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\b2\u0006\u0010\"\u001a\u00020\b2\u0006\u0010#\u001a\u00020\bJ \u0010 \u001a\u00020\u000e2\u0006\u0010$\u001a\u00020%2\u0006\u0010\"\u001a\u00020\b2\u0006\u0010#\u001a\u00020\bH\u0002J\u000e\u0010&\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\bJ4\u0010'\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\b2$\u0010(\u001a \u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000e0)J(\u0010*\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\b2\u0018\u0010(\u001a\u0014\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u000e0+J\u0011\u0010,\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\bH\u0086\u0002J\u000e\u0010-\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\bJ\u000e\u0010.\u001a\u00020%2\u0006\u0010\u0013\u001a\u00020\bJ=\u0010/\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\b2\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000e00H\u0086\bJ=\u00101\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\b2\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000e00H\u0086\bJ5\u00102\u001a\u00020\u000e2*\u0010(\u001a&\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000e03H\u0086\bJ-\u0010/\u001a\u00020\u000e2\u0006\u00104\u001a\u00020\b2\u0006\u00105\u001a\u00020\b2\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000e00H\u0086\bJ=\u00106\u001a\u00020\u000e2\u0006\u00107\u001a\u00020\b2*\u0010(\u001a&\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000e03H\u0086\bJ5\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\bH\u0000¢\u0006\u0002\b;JÇ\u0001\u0010<\u001a\u00020\u000e2\u0006\u0010:\u001a\u00020\b2\u0006\u0010=\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\b2\u008b\u0001\u0010(\u001a\u0086\u0001\u0012\u0013\u0012\u00110\b¢\u0006\f\b?\u0012\b\b@\u0012\u0004\b\b(A\u0012\u0013\u0012\u00110\b¢\u0006\f\b?\u0012\b\b@\u0012\u0004\b\b(!\u0012\u0013\u0012\u00110\b¢\u0006\f\b?\u0012\b\b@\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\b¢\u0006\f\b?\u0012\b\b@\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\b¢\u0006\f\b?\u0012\b\b@\u0012\u0004\b\b(\u0016\u0012\u0013\u0012\u00110\b¢\u0006\f\b?\u0012\b\b@\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u000e0>H\u0086\bJ1\u0010B\u001a\u00020\b2\u0006\u0010:\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\bH\u0086\bJ\u0006\u0010C\u001a\u00020\u000eJ\u0006\u0010D\u001a\u00020\u000eJ)\u0010E\u001a\u00020\u000e2\u001e\u0010(\u001a\u001a\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u000e0FH\u0086\bJ\u0006\u0010G\u001a\u00020HR\u0012\u0010\u0004\u001a\u00020\u00058\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u00020\u00058\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00020\b8\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006I"}, d2 = {"Landroidx/compose/ui/spatial/RectList;", "", "<init>", "()V", "items", "", "stack", "itemsSize", "", ContentDisposition.Parameters.Size, "getSize", "()I", "allocateItemsIndex", "resizeStorage", "", "actualSize", "currentSize", "currentItems", "insert", ES6Iterator.VALUE_PROPERTY, CmcdData.STREAM_TYPE_LIVE, "t", "r", "b", "parentId", "focusable", "", "gesturable", "remove", "update", "updateFlagsFor", "move", "updateSubhierarchy", "id", "deltaX", "deltaY", "stackMeta", "", "markUpdated", "withRect", "block", "Lkotlin/Function4;", "withTopLeftBottomRight", "Lkotlin/Function2;", "contains", "indexOf", "metaFor", "forEachIntersection", "Lkotlin/Function1;", "forEachGesturableIntersection", "forEachRect", "Lkotlin/Function5;", "x", "y", "forEachIntersectingRectWithValueAt", "index", "neighborsScoredByDistance", "", "searchAxis", "neighborsScoredByDistance$ui_release", "findKNearestNeighbors", "k", "Lkotlin/Function6;", "Lkotlin/ParameterName;", "name", "score", "findNearestNeighbor", "defragment", "clearUpdated", "forEachUpdatedRect", "Lkotlin/Function3;", "debugString", "", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class RectList {
    public static final int $stable = 8;
    public int itemsSize;
    public long[] items = new long[192];
    public long[] stack = new long[192];

    public final int getSize() {
        return this.itemsSize / 3;
    }

    private final int allocateItemsIndex() {
        long[] jArr = this.items;
        int i = this.itemsSize;
        int i2 = i + 3;
        this.itemsSize = i2;
        int length = jArr.length;
        if (length <= i2) {
            resizeStorage(length, i, jArr);
        }
        return i;
    }

    private final void resizeStorage(int actualSize, int currentSize, long[] currentItems) {
        int iMax = Math.max(actualSize * 2, currentSize + 3);
        long[] jArrCopyOf = Arrays.copyOf(currentItems, iMax);
        Intrinsics.checkNotNullExpressionValue(jArrCopyOf, "copyOf(...)");
        this.items = jArrCopyOf;
        long[] jArrCopyOf2 = Arrays.copyOf(this.stack, iMax);
        Intrinsics.checkNotNullExpressionValue(jArrCopyOf2, "copyOf(...)");
        this.stack = jArrCopyOf2;
    }

    public final boolean remove(int value) {
        int i = value & RectListKt.Lower26Bits;
        long[] jArr = this.items;
        int i2 = this.itemsSize;
        for (int i3 = 0; i3 < jArr.length - 2 && i3 < i2; i3 += 3) {
            int i4 = i3 + 2;
            if ((((int) jArr[i4]) & RectListKt.Lower26Bits) == i) {
                jArr[i3] = -1;
                jArr[i3 + 1] = -1;
                jArr[i4] = 2305843009213693951L;
                return true;
            }
        }
        return false;
    }

    public final boolean update(int value, int l, int t, int r, int b) {
        int i = value & RectListKt.Lower26Bits;
        long[] jArr = this.items;
        int i2 = this.itemsSize;
        for (int i3 = 0; i3 < jArr.length - 2 && i3 < i2; i3 += 3) {
            int i4 = i3 + 2;
            long j = jArr[i4];
            if ((((int) j) & RectListKt.Lower26Bits) == i) {
                jArr[i3] = (((long) l) << 32) | (((long) t) & 4294967295L);
                jArr[i3 + 1] = (((long) r) << 32) | (4294967295L & ((long) b));
                jArr[i4] = LockFreeTaskQueueCore.CLOSED_MASK | j;
                return true;
            }
        }
        return false;
    }

    public final boolean updateFlagsFor(int value, boolean focusable, boolean gesturable) {
        int i = value & RectListKt.Lower26Bits;
        long[] jArr = this.items;
        int i2 = this.itemsSize;
        for (int i3 = 0; i3 < jArr.length - 2 && i3 < i2; i3 += 3) {
            int i4 = i3 + 2;
            long j = jArr[i4];
            if ((((int) j) & RectListKt.Lower26Bits) == i) {
                jArr[i4] = (4611686018427387903L & j) | ((focusable ? 1L : 0L) * 4611686018427387904L) | ((gesturable ? 1L : 0L) * Long.MIN_VALUE);
                return true;
            }
        }
        return false;
    }

    public final boolean move(int value, int l, int t, int r, int b) {
        int i = value & RectListKt.Lower26Bits;
        long[] jArr = this.items;
        int i2 = this.itemsSize;
        for (int i3 = 0; i3 < jArr.length - 2 && i3 < i2; i3 += 3) {
            int i4 = i3 + 2;
            long j = jArr[i4];
            if ((((int) j) & RectListKt.Lower26Bits) == i) {
                long j2 = jArr[i3];
                jArr[i3] = (((long) t) & 4294967295L) | (((long) l) << 32);
                int i5 = i3;
                jArr[i3 + 1] = (((long) b) & 4294967295L) | (((long) r) << 32);
                jArr[i4] = LockFreeTaskQueueCore.CLOSED_MASK | j;
                int i6 = l - ((int) (j2 >> 32));
                int i7 = t - ((int) j2);
                if ((i6 != 0) | (i7 != 0)) {
                    updateSubhierarchy((RectListKt.EverythingButParentId & j) | (((long) ((i5 + 3) & RectListKt.Lower26Bits)) << 26), i6, i7);
                }
                return true;
            }
        }
        return false;
    }

    public final void updateSubhierarchy(int id, int deltaX, int deltaY) {
        updateSubhierarchy((((long) (this.itemsSize & 511)) << 52) | ((long) (id & RectListKt.Lower26Bits)), deltaX, deltaY);
    }

    private final void updateSubhierarchy(long stackMeta, int deltaX, int deltaY) {
        int i;
        char c;
        char c2;
        long[] jArr = this.items;
        long[] jArr2 = this.stack;
        int size = getSize();
        jArr2[0] = stackMeta;
        int i2 = 1;
        while (i2 > 0) {
            i2--;
            long j = jArr2[i2];
            int i3 = RectListKt.Lower26Bits;
            int i4 = ((int) j) & RectListKt.Lower26Bits;
            char c3 = 26;
            int i5 = ((int) (j >> 26)) & RectListKt.Lower26Bits;
            char c4 = '4';
            int i6 = ((int) (j >> 52)) & 511;
            int i7 = i6 == 511 ? size : i6 + i5;
            if (i5 < 0) {
                return;
            }
            while (i5 < jArr.length - 2 && i5 < i7) {
                int i8 = i5 + 2;
                long j2 = jArr[i8];
                if ((((int) (j2 >> c3)) & i3) == i4) {
                    long j3 = jArr[i5];
                    int i9 = i5 + 1;
                    i = i3;
                    c = c3;
                    long j4 = jArr[i9];
                    c2 = c4;
                    jArr[i5] = (((long) (((int) j3) + deltaY)) & 4294967295L) | (((long) (((int) (j3 >> 32)) + deltaX)) << 32);
                    jArr[i9] = (((long) (((int) j4) + deltaY)) & 4294967295L) | (((long) (((int) (j4 >> 32)) + deltaX)) << 32);
                    jArr[i8] = LockFreeTaskQueueCore.CLOSED_MASK | j2;
                    if ((((int) (j2 >> c2)) & 511) > 0) {
                        jArr2[i2] = (RectListKt.EverythingButParentId & j2) | (((long) ((i5 + 3) & i)) << c);
                        i2++;
                    }
                } else {
                    i = i3;
                    c = c3;
                    c2 = c4;
                }
                i5 += 3;
                i3 = i;
                c3 = c;
                c4 = c2;
            }
        }
    }

    public final void markUpdated(int value) {
        int i = value & RectListKt.Lower26Bits;
        long[] jArr = this.items;
        int i2 = this.itemsSize;
        for (int i3 = 0; i3 < jArr.length - 2 && i3 < i2; i3 += 3) {
            int i4 = i3 + 2;
            long j = jArr[i4];
            if ((((int) j) & RectListKt.Lower26Bits) == i) {
                jArr[i4] = LockFreeTaskQueueCore.CLOSED_MASK | j;
                return;
            }
        }
    }

    public final boolean withRect(int value, Function4<? super Integer, ? super Integer, ? super Integer, ? super Integer, Unit> block) {
        int i = value & RectListKt.Lower26Bits;
        long[] jArr = this.items;
        int i2 = this.itemsSize;
        for (int i3 = 0; i3 < jArr.length - 2 && i3 < i2; i3 += 3) {
            if ((((int) jArr[i3 + 2]) & RectListKt.Lower26Bits) == i) {
                long j = jArr[i3];
                long j2 = jArr[i3 + 1];
                block.invoke(Integer.valueOf((int) (j >> 32)), Integer.valueOf((int) j), Integer.valueOf((int) (j2 >> 32)), Integer.valueOf((int) j2));
                return true;
            }
        }
        return false;
    }

    public final boolean withTopLeftBottomRight(int value, Function2<? super Long, ? super Long, Unit> block) {
        int i = value & RectListKt.Lower26Bits;
        long[] jArr = this.items;
        int i2 = this.itemsSize;
        for (int i3 = 0; i3 < jArr.length - 2 && i3 < i2; i3 += 3) {
            if ((((int) jArr[i3 + 2]) & RectListKt.Lower26Bits) == i) {
                block.invoke(Long.valueOf(jArr[i3]), Long.valueOf(jArr[i3 + 1]));
                return true;
            }
        }
        return false;
    }

    public final boolean contains(int value) {
        int i = value & RectListKt.Lower26Bits;
        long[] jArr = this.items;
        int i2 = this.itemsSize;
        for (int i3 = 0; i3 < jArr.length - 2 && i3 < i2; i3 += 3) {
            if ((((int) jArr[i3 + 2]) & RectListKt.Lower26Bits) == i) {
                return true;
            }
        }
        return false;
    }

    public final int indexOf(int value) {
        int i = value & RectListKt.Lower26Bits;
        long[] jArr = this.items;
        int i2 = this.itemsSize;
        for (int i3 = 0; i3 < jArr.length - 2 && i3 < i2; i3 += 3) {
            if ((((int) jArr[i3 + 2]) & RectListKt.Lower26Bits) == i) {
                return i3;
            }
        }
        return -1;
    }

    public final long metaFor(int value) {
        int i = value & RectListKt.Lower26Bits;
        long[] jArr = this.items;
        int i2 = this.itemsSize;
        for (int i3 = 0; i3 < jArr.length - 2 && i3 < i2; i3 += 3) {
            long j = jArr[i3 + 2];
            if ((((int) j) & RectListKt.Lower26Bits) == i) {
                return j;
            }
        }
        return RectListKt.TombStone;
    }

    public final void forEachIntersection(int l, int t, int r, int b, Function1<? super Integer, Unit> block) {
        long j = (((long) l) << 32) | (((long) t) & 4294967295L);
        long j2 = (((long) r) << 32) | (((long) b) & 4294967295L);
        long[] jArr = this.items;
        int i = this.itemsSize;
        for (int i2 = 0; i2 < jArr.length - 2 && i2 < i; i2 += 3) {
            if (((((j2 - jArr[i2]) - InlineClassHelperKt.Uint64Low32) | ((jArr[i2 + 1] - j) - InlineClassHelperKt.Uint64Low32)) & (-9223372034707292160L)) == 0) {
                block.invoke(Integer.valueOf(((int) jArr[i2 + 2]) & RectListKt.Lower26Bits));
            }
        }
    }

    public final void forEachGesturableIntersection(int l, int t, int r, int b, Function1<? super Integer, Unit> block) {
        long j = (((long) l) << 32) | (((long) t) & 4294967295L);
        long j2 = (((long) r) << 32) | (((long) b) & 4294967295L);
        long[] jArr = this.items;
        int i = this.itemsSize;
        for (int i2 = 0; i2 < jArr.length - 2 && i2 < i; i2 += 3) {
            long j3 = jArr[i2 + 2];
            if ((((int) (j3 >> 63)) & 1) != 0) {
                if (((((j2 - jArr[i2]) - InlineClassHelperKt.Uint64Low32) | ((jArr[i2 + 1] - j) - InlineClassHelperKt.Uint64Low32)) & (-9223372034707292160L)) == 0) {
                    block.invoke(Integer.valueOf(((int) j3) & RectListKt.Lower26Bits));
                }
            }
        }
    }

    public final void forEachRect(Function5<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, Unit> block) {
        long[] jArr = this.items;
        int i = this.itemsSize;
        for (int i2 = 0; i2 < jArr.length - 2 && i2 < i; i2 += 3) {
            long j = jArr[i2];
            long j2 = jArr[i2 + 1];
            block.invoke(Integer.valueOf(((int) jArr[i2 + 2]) & RectListKt.Lower26Bits), Integer.valueOf((int) (j >> 32)), Integer.valueOf((int) j), Integer.valueOf((int) (j2 >> 32)), Integer.valueOf((int) j2));
        }
    }

    public final void forEachIntersection(int x, int y, Function1<? super Integer, Unit> block) {
        long j = (((long) y) & 4294967295L) | (((long) x) << 32);
        long[] jArr = this.items;
        int i = this.itemsSize;
        for (int i2 = 0; i2 < jArr.length - 2 && i2 < i; i2 += 3) {
            if (((((j - jArr[i2]) - InlineClassHelperKt.Uint64Low32) | ((jArr[i2 + 1] - j) - InlineClassHelperKt.Uint64Low32)) & (-9223372034707292160L)) == 0) {
                block.invoke(Integer.valueOf(((int) jArr[i2 + 2]) & RectListKt.Lower26Bits));
            }
        }
    }

    public final void forEachIntersectingRectWithValueAt(int index, Function5<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, Unit> block) {
        long[] jArr = this.items;
        int i = this.itemsSize;
        long j = jArr[index];
        long j2 = jArr[index + 1];
        for (int i2 = 0; i2 < jArr.length - 2 && i2 < i; i2 += 3) {
            if (i2 != index) {
                long j3 = jArr[i2];
                long j4 = jArr[i2 + 1];
                if (((((j2 - j3) - InlineClassHelperKt.Uint64Low32) | ((j4 - j) - InlineClassHelperKt.Uint64Low32)) & (-9223372034707292160L)) == 0) {
                    block.invoke(Integer.valueOf((int) (j3 >> 32)), Integer.valueOf((int) j3), Integer.valueOf((int) (j4 >> 32)), Integer.valueOf((int) j4), Integer.valueOf(((int) jArr[i2 + 2]) & RectListKt.Lower26Bits));
                }
            }
        }
    }

    public final int[] neighborsScoredByDistance$ui_release(int searchAxis, int l, int t, int r, int b) {
        long[] jArr = this.items;
        int i = this.itemsSize / 3;
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2 * 3;
            if (i3 < 0 || i3 >= jArr.length - 1) {
                break;
            }
            long j = jArr[i3];
            long j2 = jArr[i3 + 1];
            iArr[i2] = RectListKt.distanceScore(searchAxis, l, t, r, b, (int) (j >> 32), (int) j, (int) (j2 >> 32), (int) j2);
        }
        return iArr;
    }

    public final void findKNearestNeighbors(int searchAxis, int k, int l, int t, int r, int b, Function6<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, Unit> block) {
        int[] iArrNeighborsScoredByDistance$ui_release = neighborsScoredByDistance$ui_release(searchAxis, l, t, r, b);
        long[] jArr = this.items;
        int i = 1;
        int i2 = 0;
        int i3 = 0;
        while (i2 <= k) {
            int iMin = Integer.MAX_VALUE;
            int i4 = 0;
            while (i4 < iArrNeighborsScoredByDistance$ui_release.length) {
                int i5 = iArrNeighborsScoredByDistance$ui_release[i4];
                if (i5 > i) {
                    iMin = Math.min(iMin, i5);
                }
                if (i5 == i) {
                    int i6 = i4 * 3;
                    long j = jArr[i6];
                    long j2 = jArr[i6 + 1];
                    block.invoke(Integer.valueOf(i5), Integer.valueOf(((int) jArr[i6 + 2]) & RectListKt.Lower26Bits), Integer.valueOf((int) (j >> 32)), Integer.valueOf((int) j), Integer.valueOf((int) (j2 >> 32)), Integer.valueOf((int) j2));
                    i3++;
                    if (i3 == k) {
                        return;
                    }
                }
                i4++;
                i = i;
            }
            i2++;
            i = iMin;
        }
    }

    public final int findNearestNeighbor(int searchAxis, int l, int t, int r, int b) {
        long[] jArr = this.items;
        int i = this.itemsSize;
        int i2 = Integer.MAX_VALUE;
        int i3 = -1;
        for (int i4 = 0; i4 < jArr.length - 2 && i4 < i; i4 += 3) {
            long j = jArr[i4];
            int i5 = i4 + 1;
            long j2 = jArr[i5];
            int iDistanceScore = RectListKt.distanceScore(searchAxis, l, t, r, b, (int) (j >> 32), (int) j, (int) (j2 >> 32), (int) j2);
            boolean z = (iDistanceScore < i2) & (iDistanceScore > 0);
            if (z) {
                i2 = iDistanceScore;
            }
            if (z) {
                i3 = i5;
            }
        }
        if (i3 < 0 || i3 >= jArr.length) {
            return -1;
        }
        return ((int) jArr[i3]) & RectListKt.Lower26Bits;
    }

    public final void defragment() {
        long[] jArr = this.items;
        int i = this.itemsSize;
        long[] jArr2 = this.stack;
        int i2 = 0;
        for (int i3 = 0; i3 < jArr.length - 2 && i2 < jArr2.length - 2 && i3 < i; i3 += 3) {
            int i4 = i3 + 2;
            if (jArr[i4] != RectListKt.TombStone) {
                jArr2[i2] = jArr[i3];
                jArr2[i2 + 1] = jArr[i3 + 1];
                jArr2[i2 + 2] = jArr[i4];
                i2 += 3;
            }
        }
        this.itemsSize = i2;
        this.items = jArr2;
        this.stack = jArr;
    }

    public final void clearUpdated() {
        long[] jArr = this.items;
        int i = this.itemsSize;
        for (int i2 = 0; i2 < jArr.length - 2 && i2 < i; i2 += 3) {
            int i3 = i2 + 2;
            jArr[i3] = jArr[i3] & (-2305843009213693953L);
        }
    }

    public final void forEachUpdatedRect(Function3<? super Integer, ? super Long, ? super Long, Unit> block) {
        long[] jArr = this.items;
        int i = this.itemsSize;
        for (int i2 = 0; i2 < jArr.length - 2 && i2 < i; i2 += 3) {
            long j = jArr[i2 + 2];
            if ((((int) (j >> 61)) & 1) != 0) {
                block.invoke(Integer.valueOf(((int) j) & RectListKt.Lower26Bits), Long.valueOf(jArr[i2]), Long.valueOf(jArr[i2 + 1]));
            }
        }
    }

    public final String debugString() {
        StringBuilder sb = new StringBuilder();
        long[] jArr = this.items;
        int i = this.itemsSize;
        for (int i2 = 0; i2 < jArr.length - 2 && i2 < i; i2 += 3) {
            long j = jArr[i2];
            long j2 = jArr[i2 + 1];
            long j3 = jArr[i2 + 2];
            sb.append("id=" + (((int) j3) & RectListKt.Lower26Bits) + ", rect=[" + ((int) (j >> 32)) + AbstractJsonLexerKt.COMMA + ((int) j) + AbstractJsonLexerKt.COMMA + ((int) (j2 >> 32)) + AbstractJsonLexerKt.COMMA + ((int) j2) + "], parent=" + (((int) (j3 >> 26)) & RectListKt.Lower26Bits));
            Intrinsics.checkNotNullExpressionValue(sb, "append(...)");
            sb.append('\n');
            Intrinsics.checkNotNullExpressionValue(sb, "append(...)");
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    public final void insert(int value, int l, int t, int r, int b, int parentId, boolean focusable, boolean gesturable) {
        long[] jArr = this.items;
        int i = this.itemsSize;
        int i2 = i + 3;
        this.itemsSize = i2;
        int length = jArr.length;
        if (length <= i2) {
            resizeStorage(length, i, jArr);
        }
        long[] jArr2 = this.items;
        jArr2[i] = (((long) l) << 32) | (((long) t) & 4294967295L);
        jArr2[i + 1] = (((long) r) << 32) | (((long) b) & 4294967295L);
        long j = ((gesturable ? 1L : 0L) << 63) | ((focusable ? 1L : 0L) << 62) | LockFreeTaskQueueCore.CLOSED_MASK;
        int i3 = parentId & RectListKt.Lower26Bits;
        jArr2[i + 2] = j | (((long) i3) << 26) | ((long) (value & RectListKt.Lower26Bits));
        if (parentId < 0) {
            return;
        }
        for (int i4 = i - 3; i4 >= 0; i4 -= 3) {
            int i5 = i4 + 2;
            long j2 = jArr2[i5];
            if ((((int) j2) & RectListKt.Lower26Bits) == i3) {
                jArr2[i5] = (j2 & RectListKt.EverythingButLastChildOffset) | (((long) ((i - i4) & 511)) << 52);
                return;
            }
        }
    }
}
