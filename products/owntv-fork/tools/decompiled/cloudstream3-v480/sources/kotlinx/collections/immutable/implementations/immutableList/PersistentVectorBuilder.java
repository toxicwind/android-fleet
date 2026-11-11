package kotlinx.collections.immutable.implementations.immutableList;

import androidx.exifinterface.media.ExifInterface;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.collections.AbstractMutableList;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.collections.immutable.PersistentList;
import kotlinx.collections.immutable.internal.CommonFunctionsKt;
import kotlinx.collections.immutable.internal.ListImplementation;
import kotlinx.collections.immutable.internal.MutabilityOwnership;
import org.mozilla.javascript.ES6Iterator;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: PersistentVectorBuilder.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010(\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010!\n\u0002\b\u0007\n\u0002\u0010)\n\u0000\n\u0002\u0010+\n\u0000\n\u0002\u0010*\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B?\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0010\u0010\u0006\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u0007\u0012\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\r\u0010!\u001a\u00020\u000bH\u0000¢\u0006\u0002\b\"J\u000e\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0016J\b\u0010$\u001a\u00020\u000bH\u0002J\u0010\u0010%\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u000bH\u0002J\b\u0010%\u001a\u00020\u000bH\u0002J\u001d\u0010&\u001a\u00020'2\u000e\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007H\u0002¢\u0006\u0002\u0010)J'\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0010\u0010(\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u0007H\u0002¢\u0006\u0002\u0010+J-\u0010,\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u000e\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010-\u001a\u00020\u000bH\u0002¢\u0006\u0002\u0010.J\u001f\u0010/\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\b\u00100\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0002\u00101J\u0015\u00102\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007H\u0002¢\u0006\u0002\u0010\u0018J\u0015\u00103\u001a\u00020'2\u0006\u00100\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00104J?\u00105\u001a\u0002062\u0010\u0010\u0016\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00072\u000e\u00107\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u000e\u00108\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007H\u0002¢\u0006\u0002\u00109J?\u0010:\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0010\u0010\u0016\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00072\u000e\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010;\u001a\u00020\u000bH\u0002¢\u0006\u0002\u0010<J\u0016\u0010=\u001a\u00020'2\f\u0010>\u001a\b\u0012\u0004\u0012\u00028\u00000?H\u0016J=\u0010@\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u000e\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010A\u001a\u00020\u000b2\u000e\u0010B\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0CH\u0002¢\u0006\u0002\u0010DJE\u0010E\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0010\u0010\u0016\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00072\u0006\u0010$\u001a\u00020\u000b2\u0014\u0010F\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00070\u0007H\u0002¢\u0006\u0002\u0010GJM\u0010H\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0010\u0010\u0016\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00072\u0006\u0010$\u001a\u00020\u000b2\u0006\u0010;\u001a\u00020\u000b2\u0014\u0010I\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00070CH\u0002¢\u0006\u0002\u0010JJ\u001d\u00103\u001a\u0002062\u0006\u0010K\u001a\u00020\u000b2\u0006\u00100\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010LJ/\u0010M\u001a\u0002062\u0010\u0010\u0016\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00072\u0006\u0010K\u001a\u00020\u000b2\u0006\u00100\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010NJG\u0010O\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u000e\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010;\u001a\u00020\u000b2\u0006\u0010K\u001a\u00020\u000b2\b\u00100\u001a\u0004\u0018\u00010\b2\u0006\u0010P\u001a\u00020QH\u0002¢\u0006\u0002\u0010RJ\u001e\u0010=\u001a\u00020'2\u0006\u0010K\u001a\u00020\u000b2\f\u0010>\u001a\b\u0012\u0004\u0012\u00028\u00000?H\u0016J[\u0010O\u001a\u0002062\f\u0010>\u001a\b\u0012\u0004\u0012\u00028\u00000?2\u0006\u0010K\u001a\u00020\u000b2\u0006\u0010S\u001a\u00020\u000b2\u0016\u0010F\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00070\u00072\u0006\u0010T\u001a\u00020\u000b2\u000e\u0010U\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007H\u0002¢\u0006\u0002\u0010VJU\u0010W\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010X\u001a\u00020\u000b2\u0006\u0010S\u001a\u00020\u000b2\u0016\u0010F\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00070\u00072\u0006\u0010T\u001a\u00020\u000b2\u000e\u0010U\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007H\u0002¢\u0006\u0002\u0010YJk\u0010Z\u001a\u0002062\f\u0010>\u001a\b\u0012\u0004\u0012\u00028\u00000?2\u0006\u0010K\u001a\u00020\u000b2\u000e\u0010[\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010\\\u001a\u00020\u000b2\u0016\u0010F\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00070\u00072\u0006\u0010T\u001a\u00020\u000b2\u000e\u0010U\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007H\u0002¢\u0006\u0002\u0010]J\u0016\u0010^\u001a\u00028\u00002\u0006\u0010K\u001a\u00020\u000bH\u0096\u0002¢\u0006\u0002\u0010_J\u001d\u0010`\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010K\u001a\u00020\u000bH\u0002¢\u0006\u0002\u0010aJ\u0015\u0010b\u001a\u00028\u00002\u0006\u0010K\u001a\u00020\u000bH\u0016¢\u0006\u0002\u0010_J9\u0010c\u001a\u0004\u0018\u00010\b2\u0010\u0010\u0016\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00072\u0006\u0010$\u001a\u00020\u000b2\u0006\u0010;\u001a\u00020\u000b2\u0006\u0010K\u001a\u00020\u000bH\u0002¢\u0006\u0002\u0010dJ=\u0010e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u000e\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010;\u001a\u00020\u000b2\u0006\u0010K\u001a\u00020\u000b2\u0006\u0010f\u001a\u00020QH\u0002¢\u0006\u0002\u0010gJ/\u0010h\u001a\u0002062\u0010\u0010\u0016\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00072\u0006\u0010$\u001a\u00020\u000b2\u0006\u0010;\u001a\u00020\u000bH\u0002¢\u0006\u0002\u0010iJ?\u0010j\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00072\u000e\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010;\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020\u000b2\u0006\u0010f\u001a\u00020QH\u0002¢\u0006\u0002\u0010gJ\u0016\u0010k\u001a\u00020'2\f\u0010>\u001a\b\u0012\u0004\u0012\u00028\u00000?H\u0016J\u001a\u0010l\u001a\u00020'2\u0012\u0010m\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020'0nJ\u001c\u0010k\u001a\u00020'2\u0012\u0010m\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020'0nH\u0002J/\u0010o\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00072\u000e\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010\u001f\u001a\u00020\u000bH\u0002¢\u0006\u0002\u0010.J5\u0010p\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u000e\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010K\u001a\u00020\u000b2\u0006\u0010;\u001a\u00020\u000bH\u0002¢\u0006\u0002\u0010qJ,\u0010r\u001a\u00020\u000b2\u0012\u0010m\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020'0n2\u0006\u0010%\u001a\u00020\u000b2\u0006\u0010s\u001a\u00020QH\u0002JA\u0010k\u001a\u00020\u000b2\u0012\u0010m\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020'0n2\u000e\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010t\u001a\u00020\u000b2\u0006\u0010s\u001a\u00020QH\u0002¢\u0006\u0002\u0010uJu\u0010v\u001a\u00020\u000b2\u0012\u0010m\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020'0n2\u000e\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010t\u001a\u00020\u000b2\u0006\u0010w\u001a\u00020\u000b2\u0006\u0010s\u001a\u00020Q2\u0014\u0010x\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00070y2\u0014\u0010F\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00070yH\u0002¢\u0006\u0002\u0010zJ\u001e\u0010{\u001a\u00028\u00002\u0006\u0010K\u001a\u00020\u000b2\u0006\u00100\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010|JE\u0010}\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u000e\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010;\u001a\u00020\u000b2\u0006\u0010K\u001a\u00020\u000b2\u0006\u0010~\u001a\u00028\u00002\u0006\u0010\u007f\u001a\u00020QH\u0002¢\u0006\u0002\u0010RJ\u0011\u0010\u0080\u0001\u001a\t\u0012\u0004\u0012\u00028\u00000\u0081\u0001H\u0096\u0002J\u0010\u0010\u0082\u0001\u001a\t\u0012\u0004\u0012\u00028\u00000\u0083\u0001H\u0016J\u0018\u0010\u0082\u0001\u001a\t\u0012\u0004\u0012\u00028\u00000\u0083\u00012\u0006\u0010K\u001a\u00020\u000bH\u0016J \u0010\u0084\u0001\u001a\u0011\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00070\u0085\u00012\u0006\u0010K\u001a\u00020\u000bH\u0002R\u001a\u0010\n\u001a\u00020\u000bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R:\u0010\u0016\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00072\u0010\u0010\u0015\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u0007@BX\u0080\u000e¢\u0006\u0010\n\u0002\u0010\u001b\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR6\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u000e\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007@BX\u0080\u000e¢\u0006\u0010\n\u0002\u0010\u001b\u001a\u0004\b\u001d\u0010\u0018\"\u0004\b\u001e\u0010\u001aR\u001e\u0010\u001f\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000b@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u000f¨\u0006\u0086\u0001"}, d2 = {"Lkotlinx/collections/immutable/implementations/immutableList/PersistentVectorBuilder;", ExifInterface.LONGITUDE_EAST, "Lkotlin/collections/AbstractMutableList;", "Lkotlinx/collections/immutable/PersistentList$Builder;", "vector", "Lkotlinx/collections/immutable/PersistentList;", "vectorRoot", "", "", "vectorTail", "rootShift", "", "<init>", "(Lkotlinx/collections/immutable/PersistentList;[Ljava/lang/Object;[Ljava/lang/Object;I)V", "getRootShift$kotlinx_collections_immutable", "()I", "setRootShift$kotlinx_collections_immutable", "(I)V", "builtVector", "ownership", "Lkotlinx/collections/immutable/internal/MutabilityOwnership;", ES6Iterator.VALUE_PROPERTY, "root", "getRoot$kotlinx_collections_immutable", "()[Ljava/lang/Object;", "setRoot", "([Ljava/lang/Object;)V", "[Ljava/lang/Object;", "tail", "getTail$kotlinx_collections_immutable", "setTail", ContentDisposition.Parameters.Size, "getSize", "getModCount", "getModCount$kotlinx_collections_immutable", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "rootSize", "tailSize", "isMutable", "", "buffer", "([Ljava/lang/Object;)Z", "makeMutable", "([Ljava/lang/Object;)[Ljava/lang/Object;", "makeMutableShiftingRight", "distance", "([Ljava/lang/Object;I)[Ljava/lang/Object;", "mutableBufferWith", "element", "(Ljava/lang/Object;)[Ljava/lang/Object;", "mutableBuffer", "add", Signatures.MATH_TO_BOOLEAN, "pushFilledTail", "", "filledTail", "newTail", "([Ljava/lang/Object;[Ljava/lang/Object;[Ljava/lang/Object;)V", "pushTail", "shift", "([Ljava/lang/Object;[Ljava/lang/Object;I)[Ljava/lang/Object;", "addAll", "elements", "", "copyToBuffer", "bufferIndex", "sourceIterator", "", "([Ljava/lang/Object;ILjava/util/Iterator;)[Ljava/lang/Object;", "pushBuffersIncreasingHeightIfNeeded", "buffers", "([Ljava/lang/Object;I[[Ljava/lang/Object;)[Ljava/lang/Object;", "pushBuffers", "buffersIterator", "([Ljava/lang/Object;IILjava/util/Iterator;)[Ljava/lang/Object;", "index", "(ILjava/lang/Object;)V", "insertIntoTail", "([Ljava/lang/Object;ILjava/lang/Object;)V", "insertIntoRoot", "elementCarry", "Lkotlinx/collections/immutable/implementations/immutableList/ObjectRef;", "([Ljava/lang/Object;IILjava/lang/Object;Lkotlinx/collections/immutable/implementations/immutableList/ObjectRef;)[Ljava/lang/Object;", "rightShift", "nullBuffers", "nextBuffer", "(Ljava/util/Collection;II[[Ljava/lang/Object;I[Ljava/lang/Object;)V", "shiftLeafBuffers", "startLeafIndex", "(II[[Ljava/lang/Object;I[Ljava/lang/Object;)[Ljava/lang/Object;", "splitToBuffers", "startBuffer", "startBufferSize", "(Ljava/util/Collection;I[Ljava/lang/Object;I[[Ljava/lang/Object;I[Ljava/lang/Object;)V", "get", "(I)Ljava/lang/Object;", "bufferFor", "(I)[Ljava/lang/Object;", "removeAt", "removeFromTailAt", "([Ljava/lang/Object;III)Ljava/lang/Object;", "removeFromRootAt", "tailCarry", "([Ljava/lang/Object;IILkotlinx/collections/immutable/implementations/immutableList/ObjectRef;)[Ljava/lang/Object;", "pullLastBufferFromRoot", "([Ljava/lang/Object;II)V", "pullLastBuffer", "removeAll", "removeAllWithPredicate", "predicate", "Lkotlin/Function1;", "retainFirst", "nullifyAfter", "([Ljava/lang/Object;II)[Ljava/lang/Object;", "removeAllFromTail", "bufferRef", "bufferSize", "(Lkotlin/jvm/functions/Function1;[Ljava/lang/Object;ILkotlinx/collections/immutable/implementations/immutableList/ObjectRef;)I", "recyclableRemoveAll", "toBufferSize", "recyclableBuffers", "", "(Lkotlin/jvm/functions/Function1;[Ljava/lang/Object;IILkotlinx/collections/immutable/implementations/immutableList/ObjectRef;Ljava/util/List;Ljava/util/List;)I", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "setInRoot", "e", "oldElementCarry", "iterator", "", "listIterator", "", "leafBufferIterator", "", "kotlinx-collections-immutable"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class PersistentVectorBuilder<E> extends AbstractMutableList<E> implements PersistentList.Builder<E> {
    private PersistentList<? extends E> builtVector;
    private MutabilityOwnership ownership;
    private Object[] root;
    private int rootShift;
    private int size;
    private Object[] tail;

    public PersistentVectorBuilder(PersistentList<? extends E> vector, Object[] objArr, Object[] vectorTail, int i) {
        Intrinsics.checkNotNullParameter(vector, "vector");
        Intrinsics.checkNotNullParameter(vectorTail, "vectorTail");
        this.rootShift = i;
        this.builtVector = vector;
        this.ownership = new MutabilityOwnership();
        this.root = objArr;
        this.tail = vectorTail;
        this.size = vector.size();
    }

    /* JADX INFO: renamed from: getRootShift$kotlinx_collections_immutable, reason: from getter */
    public final int getRootShift() {
        return this.rootShift;
    }

    public final void setRootShift$kotlinx_collections_immutable(int i) {
        this.rootShift = i;
    }

    /* JADX INFO: renamed from: getRoot$kotlinx_collections_immutable, reason: from getter */
    public final Object[] getRoot() {
        return this.root;
    }

    private final void setRoot(Object[] objArr) {
        if (objArr != this.root) {
            this.builtVector = null;
            this.root = objArr;
        }
    }

    /* JADX INFO: renamed from: getTail$kotlinx_collections_immutable, reason: from getter */
    public final Object[] getTail() {
        return this.tail;
    }

    private final void setTail(Object[] objArr) {
        if (objArr != this.tail) {
            this.builtVector = null;
            this.tail = objArr;
        }
    }

    @Override // kotlin.collections.AbstractMutableList
    public int getSize() {
        return this.size;
    }

    public final int getModCount$kotlinx_collections_immutable() {
        return this.modCount;
    }

    @Override // kotlinx.collections.immutable.PersistentCollection.Builder
    public PersistentList<E> build() {
        PersistentVector persistentVector = this.builtVector;
        if (persistentVector == null) {
            Object[] objArr = this.root;
            Object[] objArr2 = this.tail;
            this.ownership = new MutabilityOwnership();
            if (objArr == null) {
                if (objArr2.length == 0) {
                    persistentVector = (PersistentList<? extends E>) UtilsKt.persistentVectorOf();
                } else {
                    Object[] objArrCopyOf = Arrays.copyOf(objArr2, size());
                    Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
                    persistentVector = new SmallPersistentVector(objArrCopyOf);
                }
            } else {
                persistentVector = new PersistentVector(objArr, objArr2, size(), this.rootShift);
            }
            this.builtVector = (PersistentList<? extends E>) persistentVector;
        }
        return persistentVector;
    }

    private final int rootSize() {
        if (size() <= 32) {
            return 0;
        }
        return UtilsKt.rootSize(size());
    }

    private final int tailSize(int size) {
        return size <= 32 ? size : size - UtilsKt.rootSize(size);
    }

    private final int tailSize() {
        return tailSize(size());
    }

    private final boolean isMutable(Object[] buffer) {
        return buffer.length == 33 && buffer[32] == this.ownership;
    }

    private final Object[] makeMutable(Object[] buffer) {
        if (buffer == null) {
            return mutableBuffer();
        }
        return isMutable(buffer) ? buffer : ArraysKt.copyInto$default(buffer, mutableBuffer(), 0, 0, RangesKt.coerceAtMost(buffer.length, 32), 6, (Object) null);
    }

    private final Object[] makeMutableShiftingRight(Object[] buffer, int distance) {
        if (isMutable(buffer)) {
            return ArraysKt.copyInto(buffer, buffer, distance, 0, 32 - distance);
        }
        return ArraysKt.copyInto(buffer, mutableBuffer(), distance, 0, 32 - distance);
    }

    private final Object[] mutableBufferWith(Object element) {
        Object[] objArr = new Object[33];
        objArr[0] = element;
        objArr[32] = this.ownership;
        return objArr;
    }

    private final Object[] mutableBuffer() {
        Object[] objArr = new Object[33];
        objArr[32] = this.ownership;
        return objArr;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E element) {
        this.modCount++;
        int iTailSize = tailSize();
        if (iTailSize < 32) {
            Object[] objArrMakeMutable = makeMutable(this.tail);
            objArrMakeMutable[iTailSize] = element;
            setTail(objArrMakeMutable);
            this.size = size() + 1;
        } else {
            pushFilledTail(this.root, this.tail, mutableBufferWith(element));
        }
        return true;
    }

    private final void pushFilledTail(Object[] root, Object[] filledTail, Object[] newTail) {
        int size = size() >> 5;
        int i = this.rootShift;
        if (size > (1 << i)) {
            setRoot(pushTail(mutableBufferWith(root), filledTail, this.rootShift + 5));
            setTail(newTail);
            this.rootShift += 5;
            this.size = size() + 1;
            return;
        }
        if (root == null) {
            setRoot(filledTail);
            setTail(newTail);
            this.size = size() + 1;
        } else {
            setRoot(pushTail(root, filledTail, i));
            setTail(newTail);
            this.size = size() + 1;
        }
    }

    private final Object[] pushTail(Object[] root, Object[] tail, int shift) {
        int iIndexSegment = UtilsKt.indexSegment(size() - 1, shift);
        Object[] objArrMakeMutable = makeMutable(root);
        if (shift == 5) {
            objArrMakeMutable[iIndexSegment] = tail;
            return objArrMakeMutable;
        }
        objArrMakeMutable[iIndexSegment] = pushTail((Object[]) objArrMakeMutable[iIndexSegment], tail, shift - 5);
        return objArrMakeMutable;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (elements.isEmpty()) {
            return false;
        }
        this.modCount++;
        int iTailSize = tailSize();
        Iterator<? extends E> it = elements.iterator();
        if (32 - iTailSize >= elements.size()) {
            setTail(copyToBuffer(makeMutable(this.tail), iTailSize, it));
            this.size = size() + elements.size();
        } else {
            int size = ((elements.size() + iTailSize) - 1) / 32;
            Object[][] objArr = new Object[size][];
            objArr[0] = copyToBuffer(makeMutable(this.tail), iTailSize, it);
            for (int i = 1; i < size; i++) {
                objArr[i] = copyToBuffer(mutableBuffer(), 0, it);
            }
            setRoot(pushBuffersIncreasingHeightIfNeeded(this.root, rootSize(), objArr));
            setTail(copyToBuffer(mutableBuffer(), 0, it));
            this.size = size() + elements.size();
        }
        return true;
    }

    private final Object[] copyToBuffer(Object[] buffer, int bufferIndex, Iterator<? extends Object> sourceIterator) {
        while (bufferIndex < 32 && sourceIterator.hasNext()) {
            buffer[bufferIndex] = sourceIterator.next();
            bufferIndex++;
        }
        return buffer;
    }

    private final Object[] pushBuffersIncreasingHeightIfNeeded(Object[] root, int rootSize, Object[][] buffers) {
        Object[] objArrMakeMutable;
        Iterator<Object[]> it = ArrayIteratorKt.iterator(buffers);
        int i = rootSize >> 5;
        int i2 = this.rootShift;
        if (i < (1 << i2)) {
            objArrMakeMutable = pushBuffers(root, rootSize, i2, it);
        } else {
            objArrMakeMutable = makeMutable(root);
        }
        while (it.hasNext()) {
            this.rootShift += 5;
            objArrMakeMutable = mutableBufferWith(objArrMakeMutable);
            int i3 = this.rootShift;
            pushBuffers(objArrMakeMutable, 1 << i3, i3, it);
        }
        return objArrMakeMutable;
    }

    private final Object[] pushBuffers(Object[] root, int rootSize, int shift, Iterator<Object[]> buffersIterator) {
        if (!buffersIterator.hasNext()) {
            throw new IllegalStateException("Check failed.");
        }
        if (shift < 0) {
            throw new IllegalStateException("Check failed.");
        }
        if (shift == 0) {
            return buffersIterator.next();
        }
        Object[] objArrMakeMutable = makeMutable(root);
        int iIndexSegment = UtilsKt.indexSegment(rootSize, shift);
        int i = shift - 5;
        objArrMakeMutable[iIndexSegment] = pushBuffers((Object[]) objArrMakeMutable[iIndexSegment], rootSize, i, buffersIterator);
        while (true) {
            iIndexSegment++;
            if (iIndexSegment >= 32 || !buffersIterator.hasNext()) {
                break;
            }
            objArrMakeMutable[iIndexSegment] = pushBuffers((Object[]) objArrMakeMutable[iIndexSegment], 0, i, buffersIterator);
        }
        return objArrMakeMutable;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
    public void add(int index, E element) {
        ListImplementation.checkPositionIndex$kotlinx_collections_immutable(index, size());
        if (index == size()) {
            add(element);
            return;
        }
        this.modCount++;
        int iRootSize = rootSize();
        if (index >= iRootSize) {
            insertIntoTail(this.root, index - iRootSize, element);
            return;
        }
        ObjectRef objectRef = new ObjectRef(null);
        Object[] objArr = this.root;
        Intrinsics.checkNotNull(objArr);
        insertIntoTail(insertIntoRoot(objArr, this.rootShift, index, element, objectRef), 0, objectRef.getValue());
    }

    private final void insertIntoTail(Object[] root, int index, E element) {
        int iTailSize = tailSize();
        Object[] objArrMakeMutable = makeMutable(this.tail);
        Object[] objArr = this.tail;
        if (iTailSize < 32) {
            ArraysKt.copyInto(objArr, objArrMakeMutable, index + 1, index, iTailSize);
            objArrMakeMutable[index] = element;
            setRoot(root);
            setTail(objArrMakeMutable);
            this.size = size() + 1;
            return;
        }
        Object obj = objArr[31];
        ArraysKt.copyInto(objArr, objArrMakeMutable, index + 1, index, 31);
        objArrMakeMutable[index] = element;
        pushFilledTail(root, objArrMakeMutable, mutableBufferWith(obj));
    }

    private final Object[] insertIntoRoot(Object[] root, int shift, int index, Object element, ObjectRef elementCarry) {
        Object obj;
        int iIndexSegment = UtilsKt.indexSegment(index, shift);
        if (shift == 0) {
            elementCarry.setValue(root[31]);
            Object[] objArrCopyInto = ArraysKt.copyInto(root, makeMutable(root), iIndexSegment + 1, iIndexSegment, 31);
            objArrCopyInto[iIndexSegment] = element;
            return objArrCopyInto;
        }
        Object[] objArrMakeMutable = makeMutable(root);
        int i = shift - 5;
        Object obj2 = objArrMakeMutable[iIndexSegment];
        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        objArrMakeMutable[iIndexSegment] = insertIntoRoot((Object[]) obj2, i, index, element, elementCarry);
        while (true) {
            iIndexSegment++;
            if (iIndexSegment >= 32 || (obj = objArrMakeMutable[iIndexSegment]) == null) {
                break;
            }
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            objArrMakeMutable[iIndexSegment] = insertIntoRoot((Object[]) obj, i, 0, elementCarry.getValue(), elementCarry);
        }
        return objArrMakeMutable;
    }

    @Override // java.util.AbstractList, java.util.List
    public boolean addAll(int index, Collection<? extends E> elements) {
        PersistentVectorBuilder<E> persistentVectorBuilder;
        Collection<? extends E> collection;
        Object[] objArrCopyInto;
        Object[][] objArr;
        Intrinsics.checkNotNullParameter(elements, "elements");
        ListImplementation.checkPositionIndex$kotlinx_collections_immutable(index, size());
        if (index == size()) {
            return addAll(elements);
        }
        if (elements.isEmpty()) {
            return false;
        }
        this.modCount++;
        int i = (index >> 5) << 5;
        int size = (((size() - i) + elements.size()) - 1) / 32;
        if (size == 0) {
            CommonFunctionsKt.m10467assert(index >= rootSize());
            int i2 = index & 31;
            int size2 = ((index + elements.size()) - 1) & 31;
            Object[] objArr2 = this.tail;
            Object[] objArrCopyInto2 = ArraysKt.copyInto(objArr2, makeMutable(objArr2), size2 + 1, i2, tailSize());
            copyToBuffer(objArrCopyInto2, i2, elements.iterator());
            setTail(objArrCopyInto2);
            this.size = size() + elements.size();
            return true;
        }
        Object[][] objArr3 = new Object[size][];
        int iTailSize = tailSize();
        int iTailSize2 = tailSize(size() + elements.size());
        if (index >= rootSize()) {
            objArrCopyInto = mutableBuffer();
            objArr = objArr3;
            persistentVectorBuilder = this;
            collection = elements;
            persistentVectorBuilder.splitToBuffers(collection, index, this.tail, iTailSize, objArr, size, objArrCopyInto);
        } else {
            persistentVectorBuilder = this;
            collection = elements;
            Object[] objArr4 = persistentVectorBuilder.tail;
            if (iTailSize2 > iTailSize) {
                int i3 = iTailSize2 - iTailSize;
                Object[] objArrMakeMutableShiftingRight = persistentVectorBuilder.makeMutableShiftingRight(objArr4, i3);
                persistentVectorBuilder.insertIntoRoot(collection, index, i3, objArr3, size, objArrMakeMutableShiftingRight);
                objArr = objArr3;
                objArrCopyInto = objArrMakeMutableShiftingRight;
            } else {
                int i4 = iTailSize - iTailSize2;
                objArrCopyInto = ArraysKt.copyInto(objArr4, persistentVectorBuilder.mutableBuffer(), 0, i4, iTailSize);
                int i5 = 32 - i4;
                Object[] objArrMakeMutableShiftingRight2 = persistentVectorBuilder.makeMutableShiftingRight(persistentVectorBuilder.tail, i5);
                int i6 = size - 1;
                objArr3[i6] = objArrMakeMutableShiftingRight2;
                persistentVectorBuilder.insertIntoRoot(collection, index, i5, objArr3, i6, objArrMakeMutableShiftingRight2);
                collection = collection;
                objArr = objArr3;
                persistentVectorBuilder = persistentVectorBuilder;
            }
        }
        persistentVectorBuilder.setRoot(persistentVectorBuilder.pushBuffersIncreasingHeightIfNeeded(persistentVectorBuilder.root, i, objArr));
        persistentVectorBuilder.setTail(objArrCopyInto);
        persistentVectorBuilder.size = persistentVectorBuilder.size() + collection.size();
        return true;
    }

    private final void insertIntoRoot(Collection<? extends E> elements, int index, int rightShift, Object[][] buffers, int nullBuffers, Object[] nextBuffer) {
        Object[] objArr;
        if (this.root == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        int i = index >> 5;
        Object[] objArrShiftLeafBuffers = shiftLeafBuffers(i, rightShift, buffers, nullBuffers, nextBuffer);
        int iRootSize = nullBuffers - (((rootSize() >> 5) - 1) - i);
        if (iRootSize < nullBuffers) {
            Object[] objArr2 = buffers[iRootSize];
            Intrinsics.checkNotNull(objArr2);
            objArr = objArr2;
        } else {
            objArr = nextBuffer;
        }
        splitToBuffers(elements, index, objArrShiftLeafBuffers, 32, buffers, iRootSize, objArr);
    }

    private final Object[] shiftLeafBuffers(int startLeafIndex, int rightShift, Object[][] buffers, int nullBuffers, Object[] nextBuffer) {
        if (this.root == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        ListIterator<Object[]> listIteratorLeafBufferIterator = leafBufferIterator(rootSize() >> 5);
        while (listIteratorLeafBufferIterator.previousIndex() != startLeafIndex) {
            Object[] objArrPrevious = listIteratorLeafBufferIterator.previous();
            ArraysKt.copyInto(objArrPrevious, nextBuffer, 0, 32 - rightShift, 32);
            nextBuffer = makeMutableShiftingRight(objArrPrevious, rightShift);
            nullBuffers--;
            buffers[nullBuffers] = nextBuffer;
        }
        return listIteratorLeafBufferIterator.previous();
    }

    private final void splitToBuffers(Collection<? extends E> elements, int index, Object[] startBuffer, int startBufferSize, Object[][] buffers, int nullBuffers, Object[] nextBuffer) {
        Object[] objArrMutableBuffer;
        if (nullBuffers < 1) {
            throw new IllegalStateException("Check failed.");
        }
        Object[] objArrMakeMutable = makeMutable(startBuffer);
        buffers[0] = objArrMakeMutable;
        int i = index & 31;
        int size = ((index + elements.size()) - 1) & 31;
        int i2 = (startBufferSize - i) + size;
        if (i2 < 32) {
            ArraysKt.copyInto(objArrMakeMutable, nextBuffer, size + 1, i, startBufferSize);
        } else {
            int i3 = i2 - 31;
            if (nullBuffers == 1) {
                objArrMutableBuffer = objArrMakeMutable;
            } else {
                objArrMutableBuffer = mutableBuffer();
                nullBuffers--;
                buffers[nullBuffers] = objArrMutableBuffer;
            }
            int i4 = startBufferSize - i3;
            ArraysKt.copyInto(objArrMakeMutable, nextBuffer, 0, i4, startBufferSize);
            ArraysKt.copyInto(objArrMakeMutable, objArrMutableBuffer, size + 1, i, i4);
            nextBuffer = objArrMutableBuffer;
        }
        Iterator<? extends E> it = elements.iterator();
        copyToBuffer(objArrMakeMutable, i, it);
        for (int i5 = 1; i5 < nullBuffers; i5++) {
            buffers[i5] = copyToBuffer(mutableBuffer(), 0, it);
        }
        copyToBuffer(nextBuffer, 0, it);
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int index) {
        ListImplementation.checkElementIndex$kotlinx_collections_immutable(index, size());
        return (E) bufferFor(index)[index & 31];
    }

    private final Object[] bufferFor(int index) {
        if (rootSize() <= index) {
            return this.tail;
        }
        Object[] objArr = this.root;
        Intrinsics.checkNotNull(objArr);
        for (int i = this.rootShift; i > 0; i -= 5) {
            Object[] objArr2 = objArr[UtilsKt.indexSegment(index, i)];
            Intrinsics.checkNotNull(objArr2, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            objArr = objArr2;
        }
        return objArr;
    }

    @Override // kotlin.collections.AbstractMutableList
    public E removeAt(int index) {
        ListImplementation.checkElementIndex$kotlinx_collections_immutable(index, size());
        this.modCount++;
        int iRootSize = rootSize();
        if (index >= iRootSize) {
            return (E) removeFromTailAt(this.root, iRootSize, this.rootShift, index - iRootSize);
        }
        ObjectRef objectRef = new ObjectRef(this.tail[0]);
        Object[] objArr = this.root;
        Intrinsics.checkNotNull(objArr);
        removeFromTailAt(removeFromRootAt(objArr, this.rootShift, index, objectRef), iRootSize, this.rootShift, 0);
        return (E) objectRef.getValue();
    }

    private final Object removeFromTailAt(Object[] root, int rootSize, int shift, int index) {
        int size = size() - rootSize;
        CommonFunctionsKt.m10467assert(index < size);
        Object[] objArr = this.tail;
        if (size == 1) {
            Object obj = objArr[0];
            pullLastBufferFromRoot(root, rootSize, shift);
            return obj;
        }
        Object obj2 = objArr[index];
        Object[] objArrCopyInto = ArraysKt.copyInto(objArr, makeMutable(objArr), index, index + 1, size);
        objArrCopyInto[size - 1] = null;
        setRoot(root);
        setTail(objArrCopyInto);
        this.size = (rootSize + size) - 1;
        this.rootShift = shift;
        return obj2;
    }

    private final Object[] removeFromRootAt(Object[] root, int shift, int index, ObjectRef tailCarry) {
        int iIndexSegment = UtilsKt.indexSegment(index, shift);
        if (shift == 0) {
            Object obj = root[iIndexSegment];
            Object[] objArrCopyInto = ArraysKt.copyInto(root, makeMutable(root), iIndexSegment, iIndexSegment + 1, 32);
            objArrCopyInto[31] = tailCarry.getValue();
            tailCarry.setValue(obj);
            return objArrCopyInto;
        }
        int iIndexSegment2 = root[31] == null ? UtilsKt.indexSegment(rootSize() - 1, shift) : 31;
        Object[] objArrMakeMutable = makeMutable(root);
        int i = shift - 5;
        int i2 = iIndexSegment + 1;
        if (i2 <= iIndexSegment2) {
            while (true) {
                Object obj2 = objArrMakeMutable[iIndexSegment2];
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
                objArrMakeMutable[iIndexSegment2] = removeFromRootAt((Object[]) obj2, i, 0, tailCarry);
                if (iIndexSegment2 == i2) {
                    break;
                }
                iIndexSegment2--;
            }
        }
        Object obj3 = objArrMakeMutable[iIndexSegment];
        Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        objArrMakeMutable[iIndexSegment] = removeFromRootAt((Object[]) obj3, i, index, tailCarry);
        return objArrMakeMutable;
    }

    private final void pullLastBufferFromRoot(Object[] root, int rootSize, int shift) {
        if (shift == 0) {
            setRoot(null);
            if (root == null) {
                root = new Object[0];
            }
            setTail(root);
            this.size = rootSize;
            this.rootShift = shift;
            return;
        }
        ObjectRef objectRef = new ObjectRef(null);
        Intrinsics.checkNotNull(root);
        Object[] objArrPullLastBuffer = pullLastBuffer(root, shift, rootSize, objectRef);
        Intrinsics.checkNotNull(objArrPullLastBuffer);
        Object value = objectRef.getValue();
        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        setTail((Object[]) value);
        this.size = rootSize;
        if (objArrPullLastBuffer[1] == null) {
            setRoot((Object[]) objArrPullLastBuffer[0]);
            this.rootShift = shift - 5;
        } else {
            setRoot(objArrPullLastBuffer);
            this.rootShift = shift;
        }
    }

    private final Object[] pullLastBuffer(Object[] root, int shift, int rootSize, ObjectRef tailCarry) {
        Object[] objArrPullLastBuffer;
        int iIndexSegment = UtilsKt.indexSegment(rootSize - 1, shift);
        if (shift == 5) {
            tailCarry.setValue(root[iIndexSegment]);
            objArrPullLastBuffer = null;
        } else {
            Object obj = root[iIndexSegment];
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            objArrPullLastBuffer = pullLastBuffer((Object[]) obj, shift - 5, rootSize, tailCarry);
        }
        if (objArrPullLastBuffer == null && iIndexSegment == 0) {
            return null;
        }
        Object[] objArrMakeMutable = makeMutable(root);
        objArrMakeMutable[iIndexSegment] = objArrPullLastBuffer;
        return objArrMakeMutable;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(final Collection<?> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (elements.isEmpty()) {
            return false;
        }
        return removeAllWithPredicate(new Function1() { // from class: kotlinx.collections.immutable.implementations.immutableList.PersistentVectorBuilder$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(elements.contains(obj));
            }
        });
    }

    public final boolean removeAllWithPredicate(Function1<? super E, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        boolean zRemoveAll = removeAll(predicate);
        if (zRemoveAll) {
            this.modCount++;
        }
        return zRemoveAll;
    }

    private final boolean removeAll(Function1<? super E, Boolean> predicate) {
        int iTailSize = tailSize();
        ObjectRef objectRef = new ObjectRef(null);
        if (this.root == null) {
            return removeAllFromTail(predicate, iTailSize, objectRef) != iTailSize;
        }
        ListIterator<Object[]> listIteratorLeafBufferIterator = leafBufferIterator(0);
        int iRemoveAll = 32;
        while (iRemoveAll == 32 && listIteratorLeafBufferIterator.hasNext()) {
            iRemoveAll = removeAll(predicate, listIteratorLeafBufferIterator.next(), 32, objectRef);
        }
        if (iRemoveAll == 32) {
            CommonFunctionsKt.m10467assert(!listIteratorLeafBufferIterator.hasNext());
            int iRemoveAllFromTail = removeAllFromTail(predicate, iTailSize, objectRef);
            if (iRemoveAllFromTail == 0) {
                pullLastBufferFromRoot(this.root, size(), this.rootShift);
            }
            return iRemoveAllFromTail != iTailSize;
        }
        int iPreviousIndex = listIteratorLeafBufferIterator.previousIndex() << 5;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int iRecyclableRemoveAll = iRemoveAll;
        while (listIteratorLeafBufferIterator.hasNext()) {
            iRecyclableRemoveAll = recyclableRemoveAll(predicate, listIteratorLeafBufferIterator.next(), 32, iRecyclableRemoveAll, objectRef, arrayList2, arrayList);
        }
        int iRecyclableRemoveAll2 = recyclableRemoveAll(predicate, this.tail, iTailSize, iRecyclableRemoveAll, objectRef, arrayList2, arrayList);
        Object value = objectRef.getValue();
        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        Object[] objArr = (Object[]) value;
        ArraysKt.fill(objArr, (Object) null, iRecyclableRemoveAll2, 32);
        boolean zIsEmpty = arrayList.isEmpty();
        Object[] objArrPushBuffers = this.root;
        if (zIsEmpty) {
            Intrinsics.checkNotNull(objArrPushBuffers);
        } else {
            objArrPushBuffers = pushBuffers(objArrPushBuffers, iPreviousIndex, this.rootShift, arrayList.iterator());
        }
        int size = iPreviousIndex + (arrayList.size() << 5);
        setRoot(retainFirst(objArrPushBuffers, size));
        setTail(objArr);
        this.size = size + iRecyclableRemoveAll2;
        return true;
    }

    private final Object[] retainFirst(Object[] root, int size) {
        if ((size & 31) != 0) {
            throw new IllegalStateException("Check failed.");
        }
        if (size == 0) {
            this.rootShift = 0;
            return null;
        }
        int i = size - 1;
        while (true) {
            int i2 = this.rootShift;
            if ((i >> i2) == 0) {
                this.rootShift = i2 - 5;
                Object[] objArr = root[0];
                Intrinsics.checkNotNull(objArr, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
                root = objArr;
            } else {
                return nullifyAfter(root, i, i2);
            }
        }
    }

    private final Object[] nullifyAfter(Object[] root, int index, int shift) {
        if (shift < 0) {
            throw new IllegalStateException("Check failed.");
        }
        if (shift == 0) {
            return root;
        }
        int iIndexSegment = UtilsKt.indexSegment(index, shift);
        Object obj = root[iIndexSegment];
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        Object objNullifyAfter = nullifyAfter((Object[]) obj, index, shift - 5);
        if (iIndexSegment < 31) {
            int i = iIndexSegment + 1;
            if (root[i] != null) {
                if (isMutable(root)) {
                    ArraysKt.fill(root, (Object) null, i, 32);
                }
                root = ArraysKt.copyInto(root, mutableBuffer(), 0, 0, i);
            }
        }
        if (objNullifyAfter == root[iIndexSegment]) {
            return root;
        }
        Object[] objArrMakeMutable = makeMutable(root);
        objArrMakeMutable[iIndexSegment] = objNullifyAfter;
        return objArrMakeMutable;
    }

    private final int removeAllFromTail(Function1<? super E, Boolean> predicate, int tailSize, ObjectRef bufferRef) {
        int iRemoveAll = removeAll(predicate, this.tail, tailSize, bufferRef);
        if (iRemoveAll == tailSize) {
            CommonFunctionsKt.m10467assert(bufferRef.getValue() == this.tail);
            return tailSize;
        }
        Object value = bufferRef.getValue();
        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        Object[] objArr = (Object[]) value;
        ArraysKt.fill(objArr, (Object) null, iRemoveAll, tailSize);
        setTail(objArr);
        this.size = size() - (tailSize - iRemoveAll);
        return iRemoveAll;
    }

    private final int removeAll(Function1<? super E, Boolean> predicate, Object[] buffer, int bufferSize, ObjectRef bufferRef) {
        Object[] objArrMakeMutable = buffer;
        int i = bufferSize;
        boolean z = false;
        for (int i2 = 0; i2 < bufferSize; i2++) {
            Object obj = buffer[i2];
            if (predicate.invoke(obj).booleanValue()) {
                if (!z) {
                    objArrMakeMutable = makeMutable(buffer);
                    z = true;
                    i = i2;
                }
            } else if (z) {
                objArrMakeMutable[i] = obj;
                i++;
            }
        }
        bufferRef.setValue(objArrMakeMutable);
        return i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final int recyclableRemoveAll(Function1<? super E, Boolean> predicate, Object[] buffer, int bufferSize, int toBufferSize, ObjectRef bufferRef, List<Object[]> recyclableBuffers, List<Object[]> buffers) {
        Object[] objArrMutableBuffer;
        if (isMutable(buffer)) {
            recyclableBuffers.add(buffer);
        }
        Object value = bufferRef.getValue();
        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        Object[] objArr = (Object[]) value;
        Object[] objArr2 = objArr;
        for (int i = 0; i < bufferSize; i++) {
            Object obj = buffer[i];
            if (!predicate.invoke(obj).booleanValue()) {
                if (toBufferSize == 32) {
                    if (!recyclableBuffers.isEmpty()) {
                        objArrMutableBuffer = recyclableBuffers.remove(recyclableBuffers.size() - 1);
                    } else {
                        objArrMutableBuffer = mutableBuffer();
                    }
                    objArr2 = objArrMutableBuffer;
                    toBufferSize = 0;
                }
                objArr2[toBufferSize] = obj;
                toBufferSize++;
            }
        }
        bufferRef.setValue(objArr2);
        if (objArr != bufferRef.getValue()) {
            buffers.add(objArr);
        }
        return toBufferSize;
    }

    @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
    public E set(int index, E element) {
        ListImplementation.checkElementIndex$kotlinx_collections_immutable(index, size());
        if (rootSize() <= index) {
            Object[] objArrMakeMutable = makeMutable(this.tail);
            if (objArrMakeMutable != this.tail) {
                this.modCount++;
            }
            int i = index & 31;
            E e = (E) objArrMakeMutable[i];
            objArrMakeMutable[i] = element;
            setTail(objArrMakeMutable);
            return e;
        }
        ObjectRef objectRef = new ObjectRef(null);
        Object[] objArr = this.root;
        Intrinsics.checkNotNull(objArr);
        setRoot(setInRoot(objArr, this.rootShift, index, element, objectRef));
        return (E) objectRef.getValue();
    }

    private final Object[] setInRoot(Object[] root, int shift, int index, E e, ObjectRef oldElementCarry) {
        int iIndexSegment = UtilsKt.indexSegment(index, shift);
        Object[] objArrMakeMutable = makeMutable(root);
        if (shift == 0) {
            if (objArrMakeMutable != root) {
                this.modCount++;
            }
            oldElementCarry.setValue(objArrMakeMutable[iIndexSegment]);
            objArrMakeMutable[iIndexSegment] = e;
            return objArrMakeMutable;
        }
        Object obj = objArrMakeMutable[iIndexSegment];
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        objArrMakeMutable[iIndexSegment] = setInRoot((Object[]) obj, shift - 5, index, e, oldElementCarry);
        return objArrMakeMutable;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator<E> iterator() {
        return listIterator();
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator<E> listIterator(int index) {
        ListImplementation.checkPositionIndex$kotlinx_collections_immutable(index, size());
        return new PersistentVectorMutableIterator(this, index);
    }

    private final ListIterator<Object[]> leafBufferIterator(int index) {
        if (this.root == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        int iRootSize = rootSize() >> 5;
        ListImplementation.checkPositionIndex$kotlinx_collections_immutable(index, iRootSize);
        int i = this.rootShift;
        if (i == 0) {
            Object[] objArr = this.root;
            Intrinsics.checkNotNull(objArr);
            return new SingleElementListIterator(objArr, index);
        }
        Object[] objArr2 = this.root;
        Intrinsics.checkNotNull(objArr2);
        return new TrieIterator(objArr2, index, iRootSize, i / 5);
    }
}
