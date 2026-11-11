package androidx.compose.runtime;

import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ObjectIntMap;
import androidx.collection.ScatterSet;
import androidx.compose.animation.core.MutatorMutex$$ExternalSyntheticBackportWithForwarding0;
import androidx.compose.runtime.changelist.ChangeList;
import androidx.compose.runtime.collection.ScatterSetWrapper;
import androidx.compose.runtime.collection.ScopeMap;
import androidx.compose.runtime.internal.RememberEventDispatcher;
import androidx.compose.runtime.internal.Trace;
import androidx.compose.runtime.snapshots.ReaderKind;
import androidx.compose.runtime.snapshots.StateObject;
import androidx.compose.runtime.snapshots.StateObjectImpl;
import androidx.compose.runtime.tooling.CompositionObserver;
import androidx.compose.runtime.tooling.CompositionObserverHandle;
import androidx.compose.runtime.tooling.ObservableComposition;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Composition.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000®\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006B'\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\r\u0010\u000eJ \u0010g\u001a\u00020\\2\u0011\u0010h\u001a\r\u0012\u0004\u0012\u00020\\0[¢\u0006\u0002\b]H\u0016¢\u0006\u0002\u0010aJ \u0010i\u001a\u00020\\2\u0011\u0010h\u001a\r\u0012\u0004\u0012\u00020\\0[¢\u0006\u0002\b]H\u0016¢\u0006\u0002\u0010aJ \u0010j\u001a\u00020k2\u0011\u0010h\u001a\r\u0012\u0004\u0012\u00020\\0[¢\u0006\u0002\b]H\u0016¢\u0006\u0002\u0010lJ \u0010m\u001a\u00020k2\u0011\u0010h\u001a\r\u0012\u0004\u0012\u00020\\0[¢\u0006\u0002\b]H\u0016¢\u0006\u0002\u0010lJ\u001d\u0010n\u001a\u00020\\2\u000e\u0010o\u001a\n\u0012\u0004\u0012\u00020q\u0018\u00010pH\u0000¢\u0006\u0002\brJ \u0010s\u001a\u00020\\2\u0011\u0010h\u001a\r\u0012\u0004\u0012\u00020\\0[¢\u0006\u0002\b]H\u0002¢\u0006\u0002\u0010aJ(\u0010t\u001a\u00020k2\u0006\u0010u\u001a\u00020<2\u0011\u0010h\u001a\r\u0012\u0004\u0012\u00020\\0[¢\u0006\u0002\b]H\u0002¢\u0006\u0002\u0010vJ \u0010w\u001a\u00020\\2\u0011\u0010h\u001a\r\u0012\u0004\u0012\u00020\\0[¢\u0006\u0002\b]H\u0002¢\u0006\u0002\u0010aJ\b\u0010x\u001a\u00020\\H\u0002J\b\u0010y\u001a\u00020<H\u0002J\u0010\u0010z\u001a\u00020{2\u0006\u0010|\u001a\u00020}H\u0016J\u000e\u0010~\u001a\u00020\\2\u0006\u0010\u007f\u001a\u00020HJ\t\u0010\u0080\u0001\u001a\u00020\\H\u0002J\t\u0010\u0081\u0001\u001a\u00020\\H\u0002J\t\u0010\u0082\u0001\u001a\u00020\\H\u0002J!\u0010\u0083\u0001\u001a\u00020\\2\u0011\u0010h\u001a\r\u0012\u0004\u0012\u00020\\0[¢\u0006\u0002\b]H\u0016¢\u0006\u0002\u0010aJ\u000f\u0010\u0084\u0001\u001a\u00020\\H\u0000¢\u0006\u0003\b\u0085\u0001J\t\u0010\u0086\u0001\u001a\u00020\\H\u0016J\u0018\u0010\u0089\u0001\u001a\u00020\\2\r\u0010\u008a\u0001\u001a\b\u0012\u0004\u0012\u00020\u00130(H\u0016J\u0018\u0010\u008b\u0001\u001a\u00020<2\r\u0010\u008a\u0001\u001a\b\u0012\u0004\u0012\u00020\u00130(H\u0016J\u0018\u0010\u008c\u0001\u001a\u00020\\2\r\u0010\u008d\u0001\u001a\b\u0012\u0004\u0012\u00020\\0[H\u0016J,\u0010\u008e\u0001\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u00130\u008f\u0001032\b\u0010\u0090\u0001\u001a\u00030\u0091\u0001H\u0000¢\u0006\u0003\b\u0092\u0001J\u001b\u0010\u0093\u0001\u001a\u00020\\2\u0007\u0010\u0094\u0001\u001a\u00020\u00132\u0007\u0010\u0095\u0001\u001a\u00020<H\u0002J!\u0010\u0093\u0001\u001a\u00020\\2\r\u0010\u008a\u0001\u001a\b\u0012\u0004\u0012\u00020\u00130(2\u0007\u0010\u0095\u0001\u001a\u00020<H\u0002J\t\u0010\u0096\u0001\u001a\u00020\\H\u0002J\u0012\u0010\u0097\u0001\u001a\u00020\\2\u0007\u0010\u0094\u0001\u001a\u00020\u0013H\u0016J\u0012\u0010\u0098\u0001\u001a\u00020\\2\u0007\u0010\u0094\u0001\u001a\u00020\u0013H\u0002J\u0012\u0010\u0099\u0001\u001a\u00020\\2\u0007\u0010\u0094\u0001\u001a\u00020\u0013H\u0016J\t\u0010\u009a\u0001\u001a\u00020<H\u0016J)\u0010\u009b\u0001\u001a\u00020\\2\u001e\u0010\u009c\u0001\u001a\u0019\u0012\u0015\u0012\u0013\u0012\u0005\u0012\u00030\u009d\u0001\u0012\u0007\u0012\u0005\u0018\u00010\u009d\u00010\u008f\u000103H\u0016J\u0012\u0010\u009e\u0001\u001a\u00020\\2\u0007\u0010W\u001a\u00030\u009f\u0001H\u0016J\u0011\u0010 \u0001\u001a\u00020\\2\u0006\u00106\u001a\u000207H\u0002J\t\u0010¡\u0001\u001a\u00020\\H\u0016J\t\u0010¢\u0001\u001a\u00020\\H\u0016J\t\u0010£\u0001\u001a\u00020\\H\u0016JL\u0010¤\u0001\u001a\u0003H¥\u0001\"\u0005\b\u0000\u0010¥\u000122\u0010\u008d\u0001\u001a-\u0012!\u0012\u001f\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u00130$¢\u0006\u000e\b§\u0001\u0012\t\b¨\u0001\u0012\u0004\b\b(6\u0012\u0005\u0012\u0003H¥\u00010¦\u0001H\u0082\b¢\u0006\u0003\u0010©\u0001J(\u0010ª\u0001\u001a\u0003H¥\u0001\"\u0005\b\u0000\u0010¥\u00012\u000e\u0010\u008d\u0001\u001a\t\u0012\u0005\u0012\u0003H¥\u00010[H\u0082\b¢\u0006\u0003\u0010«\u0001J\t\u0010¬\u0001\u001a\u00020\\H\u0016J\t\u0010\u00ad\u0001\u001a\u00020\\H\u0016J\t\u0010®\u0001\u001a\u00020\\H\u0016J;\u0010¯\u0001\u001a\u0003H°\u0001\"\u0005\b\u0000\u0010°\u00012\t\u0010±\u0001\u001a\u0004\u0018\u00010\u00012\u0007\u0010²\u0001\u001a\u00020H2\u000e\u0010\u008d\u0001\u001a\t\u0012\u0005\u0012\u0003H°\u00010[H\u0016¢\u0006\u0003\u0010³\u0001J\u0015\u0010´\u0001\u001a\u0004\u0018\u00010C2\b\u0010B\u001a\u0004\u0018\u00010CH\u0016J\u001e\u0010µ\u0001\u001a\u00030¶\u00012\u0007\u0010·\u0001\u001a\u00020%2\t\u0010¸\u0001\u001a\u0004\u0018\u00010\u0013H\u0016J\u0012\u0010¹\u0001\u001a\u00020\\2\u0007\u0010·\u0001\u001a\u00020%H\u0016J)\u0010º\u0001\u001a\u0005\u0018\u0001H¥\u0001\"\u0005\b\u0000\u0010¥\u00012\u000e\u0010\u007f\u001a\n\u0012\u0005\u0012\u0003H¥\u00010»\u0001H\u0016¢\u0006\u0003\u0010¼\u0001J\u001d\u0010½\u0001\u001a\u00020<2\u0007\u0010·\u0001\u001a\u00020%2\t\u0010¸\u0001\u001a\u0004\u0018\u00010\u0013H\u0002J(\u0010¾\u0001\u001a\u00030¶\u00012\u0007\u0010·\u0001\u001a\u00020%2\b\u0010\u0090\u0001\u001a\u00030\u0091\u00012\t\u0010¸\u0001\u001a\u0004\u0018\u00010\u0013H\u0002J!\u0010¿\u0001\u001a\u00020\\2\u0007\u0010¸\u0001\u001a\u00020\u00132\u0007\u0010·\u0001\u001a\u00020%H\u0000¢\u0006\u0003\bÀ\u0001J\u001b\u0010Á\u0001\u001a\u00020\\2\n\u0010W\u001a\u0006\u0012\u0002\b\u00030/H\u0000¢\u0006\u0003\bÂ\u0001J\u001e\u0010Ã\u0001\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u00130$H\u0002¢\u0006\u0006\bÄ\u0001\u0010Å\u0001J\u0011\u0010Æ\u0001\u001a\u00020\\2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J(\u0010Ç\u0001\u001a\u0003H¥\u0001\"\u0005\b\u0000\u0010¥\u00012\u000e\u0010\u008d\u0001\u001a\t\u0012\u0005\u0012\u0003H¥\u00010[H\u0082\b¢\u0006\u0003\u0010«\u0001J\n\u0010|\u001a\u0004\u0018\u00010}H\u0002J\t\u0010È\u0001\u001a\u00020\\H\u0016J\u000f\u0010É\u0001\u001a\u00020HH\u0000¢\u0006\u0003\bÊ\u0001R\u0013\u0010\u0007\u001a\u00020\b8G¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0012\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0011\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0012j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0013`\u0014X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0015R\u0014\u0010\u0016\u001a\u00060\u0013j\u0002`\u0017X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018R\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u001fX\u0080\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b \u0010\u001d\u001a\u0004\b!\u0010\"R\u001c\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020%0$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010&R\u001a\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00130(8AX\u0080\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*R\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020%0,X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010-\u001a\b\u0012\u0004\u0012\u00020%0,X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010.\u001a\u0012\u0012\u0004\u0012\u00020\u0013\u0012\b\u0012\u0006\u0012\u0002\b\u00030/0$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010&R\u001a\u00100\u001a\b\u0012\u0004\u0012\u00020\u00130(8AX\u0080\u0004¢\u0006\u0006\u001a\u0004\b1\u0010*R\u001a\u00102\u001a\b\u0012\u0004\u0012\u00020%038AX\u0080\u0004¢\u0006\u0006\u001a\u0004\b4\u00105R\u000e\u00106\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u00109\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020%0$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010&R\u001c\u0010:\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u00130$X\u0082\u000e¢\u0006\u0004\n\u0002\u0010&R \u0010;\u001a\u00020<X\u0080\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b=\u0010\u001d\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\u0010\u0010B\u001a\u0004\u0018\u00010CX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010D\u001a\u0004\u0018\u00010EX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010F\u001a\u0004\u0018\u00010\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020HX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010I\u001a\u00020JX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bK\u0010LR\u000e\u0010M\u001a\u00020NX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010O\u001a\u00020PX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010RR\u0010\u0010S\u001a\u0004\u0018\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\bT\u0010UR\u0011\u0010V\u001a\u00020<¢\u0006\b\n\u0000\u001a\u0004\bV\u0010?R\u000e\u0010W\u001a\u00020HX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010X\u001a\u00020<8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bY\u0010?R'\u0010Z\u001a\r\u0012\u0004\u0012\u00020\\0[¢\u0006\u0002\b]X\u0086\u000e¢\u0006\u0010\n\u0002\u0010b\u001a\u0004\b^\u0010_\"\u0004\b`\u0010aR\u0014\u0010c\u001a\u00020<8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bc\u0010?R\u0014\u0010d\u001a\u00020<8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bd\u0010?R\u0014\u0010e\u001a\u00020<8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bf\u0010?R\u0016\u0010\u0087\u0001\u001a\u00020<8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0088\u0001\u0010?¨\u0006Ë\u0001"}, d2 = {"Landroidx/compose/runtime/CompositionImpl;", "Landroidx/compose/runtime/ControlledComposition;", "Landroidx/compose/runtime/ReusableComposition;", "Landroidx/compose/runtime/RecomposeScopeOwner;", "Landroidx/compose/runtime/CompositionServices;", "Landroidx/compose/runtime/PausableComposition;", "Landroidx/compose/runtime/tooling/ObservableComposition;", "parent", "Landroidx/compose/runtime/CompositionContext;", "applier", "Landroidx/compose/runtime/Applier;", "recomposeContext", "Lkotlin/coroutines/CoroutineContext;", "<init>", "(Landroidx/compose/runtime/CompositionContext;Landroidx/compose/runtime/Applier;Lkotlin/coroutines/CoroutineContext;)V", "getParent", "()Landroidx/compose/runtime/CompositionContext;", "pendingModifications", "Ljava/util/concurrent/atomic/AtomicReference;", "", "Landroidx/compose/runtime/internal/AtomicReference;", "Ljava/util/concurrent/atomic/AtomicReference;", "lock", "Landroidx/compose/runtime/platform/SynchronizedObject;", "Ljava/lang/Object;", "abandonSet", "", "Landroidx/compose/runtime/RememberObserver;", "getAbandonSet$annotations", "()V", "slotTable", "Landroidx/compose/runtime/SlotTable;", "getSlotTable$runtime$annotations", "getSlotTable$runtime", "()Landroidx/compose/runtime/SlotTable;", "observations", "Landroidx/compose/runtime/collection/ScopeMap;", "Landroidx/compose/runtime/RecomposeScopeImpl;", "Landroidx/collection/MutableScatterMap;", "observedObjects", "", "getObservedObjects$runtime", "()Ljava/util/Set;", "invalidatedScopes", "Landroidx/collection/MutableScatterSet;", "conditionallyInvalidatedScopes", "derivedStates", "Landroidx/compose/runtime/DerivedState;", "derivedStateDependencies", "getDerivedStateDependencies$runtime", "conditionalScopes", "", "getConditionalScopes$runtime", "()Ljava/util/List;", "changes", "Landroidx/compose/runtime/changelist/ChangeList;", "lateChanges", "observationsProcessed", "invalidations", "pendingInvalidScopes", "", "getPendingInvalidScopes$runtime$annotations", "getPendingInvalidScopes$runtime", "()Z", "setPendingInvalidScopes$runtime", "(Z)V", "shouldPause", "Landroidx/compose/runtime/ShouldPauseCallback;", "pendingPausedComposition", "Landroidx/compose/runtime/PausedCompositionImpl;", "invalidationDelegate", "invalidationDelegateGroup", "", "observerHolder", "Landroidx/compose/runtime/CompositionObserverHolder;", "getObserverHolder$runtime", "()Landroidx/compose/runtime/CompositionObserverHolder;", "rememberManager", "Landroidx/compose/runtime/internal/RememberEventDispatcher;", "composer", "Landroidx/compose/runtime/ComposerImpl;", "getComposer$runtime", "()Landroidx/compose/runtime/ComposerImpl;", "_recomposeContext", "getRecomposeContext", "()Lkotlin/coroutines/CoroutineContext;", "isRoot", "state", "areChildrenComposing", "getAreChildrenComposing", "composable", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "getComposable", "()Lkotlin/jvm/functions/Function2;", "setComposable", "(Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "isComposing", "isDisposed", "hasPendingChanges", "getHasPendingChanges", "setContent", "content", "setContentWithReuse", "setPausableContent", "Landroidx/compose/runtime/PausedComposition;", "(Lkotlin/jvm/functions/Function2;)Landroidx/compose/runtime/PausedComposition;", "setPausableContentWithReuse", "pausedCompositionFinished", "ignoreSet", "Landroidx/collection/ScatterSet;", "Landroidx/compose/runtime/RememberObserverHolder;", "pausedCompositionFinished$runtime", "composeInitial", "composeInitialPaused", "reusable", "(ZLkotlin/jvm/functions/Function2;)Landroidx/compose/runtime/PausedComposition;", "composeInitialWithReuse", "ensureRunning", "clearDeactivated", "setObserver", "Landroidx/compose/runtime/tooling/CompositionObserverHandle;", "observer", "Landroidx/compose/runtime/tooling/CompositionObserver;", "invalidateGroupsWithKey", "key", "drainPendingModificationsForCompositionLocked", "drainPendingModificationsLocked", "drainPendingModificationsOutOfBandLocked", "composeContent", "updateMovingInvalidations", "updateMovingInvalidations$runtime", "dispose", "hasInvalidations", "getHasInvalidations", "recordModificationsOf", "values", "observesAnyOf", "prepareCompose", "block", "extractInvalidationsOf", "Lkotlin/Pair;", LinkHeader.Parameters.Anchor, "Landroidx/compose/runtime/Anchor;", "extractInvalidationsOf$runtime", "addPendingInvalidationsLocked", ES6Iterator.VALUE_PROPERTY, "forgetConditionalScopes", "cleanUpDerivedStateObservations", "recordReadOf", "invalidateScopeOfLocked", "recordWriteOf", "recompose", "insertMovableContent", "references", "Landroidx/compose/runtime/MovableContentStateReference;", "disposeUnusedMovableContent", "Landroidx/compose/runtime/MovableContentState;", "applyChangesInLocked", "applyChanges", "applyLateChanges", "changesApplied", "guardInvalidationsLocked", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "guardChanges", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "abandonChanges", "invalidateAll", "verifyConsistent", "delegateInvalidations", "R", TypedValues.TransitionType.S_TO, "groupIndex", "(Landroidx/compose/runtime/ControlledComposition;ILkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "getAndSetShouldPauseCallback", "invalidate", "Landroidx/compose/runtime/InvalidationResult;", "scope", "instance", "recomposeScopeReleased", "getCompositionService", "Landroidx/compose/runtime/CompositionServiceKey;", "(Landroidx/compose/runtime/CompositionServiceKey;)Ljava/lang/Object;", "tryImminentInvalidation", "invalidateChecked", "removeObservation", "removeObservation$runtime", "removeDerivedStateObservation", "removeDerivedStateObservation$runtime", "takeInvalidations", "takeInvalidations-afanTW4", "()Landroidx/collection/MutableScatterMap;", "validateRecomposeScopeAnchors", "trackAbandonedValues", "deactivate", "composerStacksSizes", "composerStacksSizes$runtime", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class CompositionImpl implements ControlledComposition, ReusableComposition, RecomposeScopeOwner, CompositionServices, PausableComposition, ObservableComposition {
    public static final int $stable = 8;
    private final CoroutineContext _recomposeContext;
    private final Set<RememberObserver> abandonSet;
    private final Applier<?> applier;
    private final ChangeList changes;
    private Function2<? super Composer, ? super Integer, Unit> composable;
    private final ComposerImpl composer;
    private final MutableScatterSet<RecomposeScopeImpl> conditionallyInvalidatedScopes;
    private final MutableScatterMap<Object, Object> derivedStates;
    private final MutableScatterSet<RecomposeScopeImpl> invalidatedScopes;
    private CompositionImpl invalidationDelegate;
    private int invalidationDelegateGroup;
    private MutableScatterMap<Object, Object> invalidations;
    private final boolean isRoot;
    private final ChangeList lateChanges;
    private final Object lock;
    private final MutableScatterMap<Object, Object> observations;
    private final MutableScatterMap<Object, Object> observationsProcessed;
    private final CompositionObserverHolder observerHolder;
    private final CompositionContext parent;
    private boolean pendingInvalidScopes;
    private final AtomicReference<Object> pendingModifications;
    private PausedCompositionImpl pendingPausedComposition;
    private final RememberEventDispatcher rememberManager;
    private ShouldPauseCallback shouldPause;
    private final SlotTable slotTable;
    private int state;

    private static /* synthetic */ void getAbandonSet$annotations() {
    }

    public static /* synthetic */ void getPendingInvalidScopes$runtime$annotations() {
    }

    public static /* synthetic */ void getSlotTable$runtime$annotations() {
    }

    public CompositionImpl(CompositionContext compositionContext, Applier<?> applier, CoroutineContext coroutineContext) {
        this.parent = compositionContext;
        this.applier = applier;
        DefaultConstructorMarker defaultConstructorMarker = null;
        this.pendingModifications = new AtomicReference<>(null);
        this.lock = new Object();
        int i = 0;
        int i2 = 1;
        Set<RememberObserver> setAsMutableSet = new MutableScatterSet(i, i2, defaultConstructorMarker).asMutableSet();
        this.abandonSet = setAsMutableSet;
        SlotTable slotTable = new SlotTable();
        if (compositionContext.getCollectingCallByInformation$runtime()) {
            slotTable.collectCalledByInformation();
        }
        if (compositionContext.getCollectingSourceInformation()) {
            slotTable.collectSourceInformation();
        }
        this.slotTable = slotTable;
        this.observations = ScopeMap.m3554constructorimpl$default(null, 1, null);
        this.invalidatedScopes = new MutableScatterSet<>(i, i2, defaultConstructorMarker);
        this.conditionallyInvalidatedScopes = new MutableScatterSet<>(i, i2, defaultConstructorMarker);
        this.derivedStates = ScopeMap.m3554constructorimpl$default(null, 1, null);
        ChangeList changeList = new ChangeList();
        this.changes = changeList;
        ChangeList changeList2 = new ChangeList();
        this.lateChanges = changeList2;
        this.observationsProcessed = ScopeMap.m3554constructorimpl$default(null, 1, null);
        this.invalidations = ScopeMap.m3554constructorimpl$default(null, 1, null);
        CompositionObserverHolder compositionObserverHolder = new CompositionObserverHolder(null, false, compositionContext, 3, null);
        this.observerHolder = compositionObserverHolder;
        this.rememberManager = new RememberEventDispatcher();
        ComposerImpl composerImpl = new ComposerImpl(applier, compositionContext, slotTable, setAsMutableSet, changeList, changeList2, compositionObserverHolder, this);
        compositionContext.registerComposer$runtime(composerImpl);
        this.composer = composerImpl;
        this._recomposeContext = coroutineContext;
        this.isRoot = compositionContext instanceof Recomposer;
        this.composable = ComposableSingletons$CompositionKt.INSTANCE.getLambda$954879418$runtime();
    }

    public /* synthetic */ CompositionImpl(CompositionContext compositionContext, Applier applier, CoroutineContext coroutineContext, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(compositionContext, applier, (i & 4) != 0 ? null : coroutineContext);
    }

    public final CompositionContext getParent() {
        return this.parent;
    }

    /* JADX INFO: renamed from: getSlotTable$runtime, reason: from getter */
    public final SlotTable getSlotTable() {
        return this.slotTable;
    }

    public final Set<Object> getObservedObjects$runtime() {
        return this.observations.asMap().keySet();
    }

    public final Set<Object> getDerivedStateDependencies$runtime() {
        return this.derivedStates.asMap().keySet();
    }

    public final List<RecomposeScopeImpl> getConditionalScopes$runtime() {
        return CollectionsKt.toList(this.conditionallyInvalidatedScopes.asSet());
    }

    /* JADX INFO: renamed from: getPendingInvalidScopes$runtime, reason: from getter */
    public final boolean getPendingInvalidScopes() {
        return this.pendingInvalidScopes;
    }

    public final void setPendingInvalidScopes$runtime(boolean z) {
        this.pendingInvalidScopes = z;
    }

    /* JADX INFO: renamed from: getObserverHolder$runtime, reason: from getter */
    public final CompositionObserverHolder getObserverHolder() {
        return this.observerHolder;
    }

    /* JADX INFO: renamed from: getComposer$runtime, reason: from getter */
    public final ComposerImpl getComposer() {
        return this.composer;
    }

    public final CoroutineContext getRecomposeContext() {
        CoroutineContext coroutineContext = this._recomposeContext;
        return coroutineContext == null ? this.parent.getRecomposeCoroutineContext$runtime() : coroutineContext;
    }

    /* JADX INFO: renamed from: isRoot, reason: from getter */
    public final boolean getIsRoot() {
        return this.isRoot;
    }

    private final boolean getAreChildrenComposing() {
        return this.composer.getAreChildrenComposing$runtime();
    }

    public final Function2<Composer, Integer, Unit> getComposable() {
        return this.composable;
    }

    public final void setComposable(Function2<? super Composer, ? super Integer, Unit> function2) {
        this.composable = function2;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean isComposing() {
        return this.composer.getIsComposing();
    }

    @Override // androidx.compose.runtime.Composition
    public boolean isDisposed() {
        return this.state == 3;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean getHasPendingChanges() {
        boolean hasPendingChanges$runtime;
        synchronized (this.lock) {
            hasPendingChanges$runtime = this.composer.getHasPendingChanges$runtime();
        }
        return hasPendingChanges$runtime;
    }

    @Override // androidx.compose.runtime.Composition
    public void setContent(Function2<? super Composer, ? super Integer, Unit> content) {
        boolean zClearDeactivated = clearDeactivated();
        ensureRunning();
        if (zClearDeactivated) {
            composeInitialWithReuse(content);
        } else {
            composeInitial(content);
        }
    }

    @Override // androidx.compose.runtime.ReusableComposition
    public void setContentWithReuse(Function2<? super Composer, ? super Integer, Unit> content) {
        clearDeactivated();
        ensureRunning();
        composeInitialWithReuse(content);
    }

    @Override // androidx.compose.runtime.PausableComposition
    public PausedComposition setPausableContent(Function2<? super Composer, ? super Integer, Unit> content) {
        return composeInitialPaused(clearDeactivated(), content);
    }

    @Override // androidx.compose.runtime.PausableComposition
    public PausedComposition setPausableContentWithReuse(Function2<? super Composer, ? super Integer, Unit> content) {
        clearDeactivated();
        ensureRunning();
        return composeInitialPaused(true, content);
    }

    public final void pausedCompositionFinished$runtime(ScatterSet<RememberObserverHolder> ignoreSet) {
        this.pendingPausedComposition = null;
        if (ignoreSet != null) {
            this.rememberManager.ignoreForgotten(ignoreSet);
            this.state = 2;
        }
    }

    private final void composeInitial(Function2<? super Composer, ? super Integer, Unit> content) {
        this.composable = content;
        this.parent.composeInitial$runtime(this, content);
    }

    private final PausedComposition composeInitialPaused(boolean reusable, Function2<? super Composer, ? super Integer, Unit> content) {
        if (!(this.pendingPausedComposition == null)) {
            PreconditionsKt.throwIllegalStateException("A pausable composition is in progress");
        }
        PausedCompositionImpl pausedCompositionImpl = new PausedCompositionImpl(this, this.parent, this.composer, this.abandonSet, content, reusable, this.applier, this.lock);
        this.pendingPausedComposition = pausedCompositionImpl;
        return pausedCompositionImpl;
    }

    private final void composeInitialWithReuse(Function2<? super Composer, ? super Integer, Unit> content) {
        this.composer.startReuseFromRoot();
        composeInitial(content);
        this.composer.endReuseFromRoot();
    }

    private final void ensureRunning() {
        String str;
        int i = this.state;
        if (!(i == 0)) {
            if (i == 1) {
                str = "The composition should be activated before setting content.";
            } else if (i == 2) {
                str = "A previous pausable composition for this composition was cancelled. This composition must be disposed.";
            } else if (i == 3) {
                str = "The composition is disposed";
            } else {
                str = "";
            }
            PreconditionsKt.throwIllegalStateException(str);
        }
        if (this.pendingPausedComposition == null) {
            return;
        }
        PreconditionsKt.throwIllegalStateException("A pausable composition is in progress");
    }

    private final boolean clearDeactivated() {
        boolean z;
        synchronized (this.lock) {
            z = true;
            if (this.state != 1) {
                z = false;
            }
            if (z) {
                this.state = 0;
            }
        }
        return z;
    }

    @Override // androidx.compose.runtime.tooling.ObservableComposition
    public CompositionObserverHandle setObserver(final CompositionObserver observer) {
        synchronized (this.lock) {
            this.observerHolder.setObserver(observer);
            this.observerHolder.setRoot(true);
            Unit unit = Unit.INSTANCE;
        }
        return new CompositionObserverHandle() { // from class: androidx.compose.runtime.CompositionImpl.setObserver.2
            @Override // androidx.compose.runtime.tooling.CompositionObserverHandle
            public void dispose() {
                Object obj = CompositionImpl.this.lock;
                CompositionImpl compositionImpl = CompositionImpl.this;
                CompositionObserver compositionObserver = observer;
                synchronized (obj) {
                    if (Intrinsics.areEqual(compositionImpl.getObserverHolder().getObserver(), compositionObserver)) {
                        compositionImpl.getObserverHolder().setObserver(null);
                        compositionImpl.getObserverHolder().setRoot(false);
                    }
                    Unit unit2 = Unit.INSTANCE;
                }
            }
        };
    }

    public final void invalidateGroupsWithKey(int key) {
        List<RecomposeScopeImpl> listInvalidateGroupsWithKey$runtime;
        synchronized (this.lock) {
            listInvalidateGroupsWithKey$runtime = this.slotTable.invalidateGroupsWithKey$runtime(key);
        }
        if (listInvalidateGroupsWithKey$runtime != null) {
            int size = listInvalidateGroupsWithKey$runtime.size();
            for (int i = 0; i < size; i++) {
                if (listInvalidateGroupsWithKey$runtime.get(i).invalidateForResult(null) != InvalidationResult.IGNORED) {
                }
            }
            return;
        }
        if (this.composer.forceRecomposeScopes$runtime()) {
            this.parent.invalidate$runtime(this);
        }
    }

    private final void drainPendingModificationsForCompositionLocked() {
        Object andSet = this.pendingModifications.getAndSet(CompositionKt.PendingApplyNoModifications);
        if (andSet != null) {
            if (Intrinsics.areEqual(andSet, CompositionKt.PendingApplyNoModifications)) {
                ComposerKt.composeRuntimeError("pending composition has not been applied");
                throw new KotlinNothingValueException();
            }
            if (andSet instanceof Set) {
                addPendingInvalidationsLocked((Set<? extends Object>) andSet, true);
                return;
            }
            if (andSet instanceof Object[]) {
                for (Set<? extends Object> set : (Set[]) andSet) {
                    addPendingInvalidationsLocked(set, true);
                }
                return;
            }
            ComposerKt.composeRuntimeError("corrupt pendingModifications drain: " + this.pendingModifications);
            throw new KotlinNothingValueException();
        }
    }

    private final void drainPendingModificationsLocked() {
        Object andSet = this.pendingModifications.getAndSet(null);
        if (Intrinsics.areEqual(andSet, CompositionKt.PendingApplyNoModifications)) {
            return;
        }
        if (andSet instanceof Set) {
            addPendingInvalidationsLocked((Set<? extends Object>) andSet, false);
            return;
        }
        if (andSet instanceof Object[]) {
            for (Set<? extends Object> set : (Set[]) andSet) {
                addPendingInvalidationsLocked(set, false);
            }
            return;
        }
        if (andSet == null) {
            ComposerKt.composeRuntimeError("calling recordModificationsOf and applyChanges concurrently is not supported");
            throw new KotlinNothingValueException();
        }
        ComposerKt.composeRuntimeError("corrupt pendingModifications drain: " + this.pendingModifications);
        throw new KotlinNothingValueException();
    }

    private final void drainPendingModificationsOutOfBandLocked() {
        Object andSet = this.pendingModifications.getAndSet(SetsKt.emptySet());
        if (Intrinsics.areEqual(andSet, CompositionKt.PendingApplyNoModifications) || andSet == null) {
            return;
        }
        if (andSet instanceof Set) {
            addPendingInvalidationsLocked((Set<? extends Object>) andSet, false);
            return;
        }
        if (andSet instanceof Object[]) {
            for (Set<? extends Object> set : (Set[]) andSet) {
                addPendingInvalidationsLocked(set, false);
            }
            return;
        }
        ComposerKt.composeRuntimeError("corrupt pendingModifications drain: " + this.pendingModifications);
        throw new KotlinNothingValueException();
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void composeContent(Function2<? super Composer, ? super Integer, Unit> content) {
        try {
            synchronized (this.lock) {
                drainPendingModificationsForCompositionLocked();
                MutableScatterMap<Object, Object> mutableScatterMapM3390takeInvalidationsafanTW4 = m3390takeInvalidationsafanTW4();
                try {
                    this.composer.m3387composeContentZbOJvo$runtime(mutableScatterMapM3390takeInvalidationsafanTW4, content, this.shouldPause);
                    Unit unit = Unit.INSTANCE;
                    Unit unit2 = Unit.INSTANCE;
                } catch (Throwable th) {
                    this.invalidations = mutableScatterMapM3390takeInvalidationsafanTW4;
                    throw th;
                }
            }
            Unit unit3 = Unit.INSTANCE;
        } catch (Throwable th2) {
            try {
                if (!this.abandonSet.isEmpty()) {
                    RememberEventDispatcher rememberEventDispatcher = this.rememberManager;
                    try {
                        rememberEventDispatcher.prepare(this.abandonSet, this.composer.getErrorContext$runtime());
                        rememberEventDispatcher.dispatchAbandons();
                    } finally {
                        rememberEventDispatcher.clear();
                    }
                }
                throw th2;
            } catch (Throwable th3) {
                abandonChanges();
                throw th3;
            }
        }
    }

    public final void updateMovingInvalidations$runtime() {
        synchronized (this.lock) {
            drainPendingModificationsOutOfBandLocked();
            MutableScatterMap<Object, Object> mutableScatterMapM3390takeInvalidationsafanTW4 = m3390takeInvalidationsafanTW4();
            try {
                this.composer.m3389updateComposerInvalidationsRY85e9Y(mutableScatterMapM3390takeInvalidationsafanTW4);
                Unit unit = Unit.INSTANCE;
                Unit unit2 = Unit.INSTANCE;
            } catch (Throwable th) {
                this.invalidations = mutableScatterMapM3390takeInvalidationsafanTW4;
                throw th;
            }
        }
    }

    @Override // androidx.compose.runtime.Composition
    public void dispose() {
        synchronized (this.lock) {
            if (this.composer.getIsComposing()) {
                PreconditionsKt.throwIllegalStateException("Composition is disposed while composing. If dispose is triggered by a call in @Composable function, consider wrapping it with SideEffect block.");
            }
            if (this.state != 3) {
                this.state = 3;
                this.composable = ComposableSingletons$CompositionKt.INSTANCE.getLambda$1918065384$runtime();
                ChangeList deferredChanges$runtime = this.composer.getDeferredChanges();
                if (deferredChanges$runtime != null) {
                    applyChangesInLocked(deferredChanges$runtime);
                }
                boolean z = this.slotTable.getGroupsSize() > 0;
                if (z || !this.abandonSet.isEmpty()) {
                    RememberEventDispatcher rememberEventDispatcher = this.rememberManager;
                    try {
                        rememberEventDispatcher.prepare(this.abandonSet, this.composer.getErrorContext$runtime());
                        if (z) {
                            this.applier.onBeginChanges();
                            SlotWriter slotWriterOpenWriter = this.slotTable.openWriter();
                            try {
                                ComposerKt.removeCurrentGroup(slotWriterOpenWriter, this.rememberManager);
                                Unit unit = Unit.INSTANCE;
                                slotWriterOpenWriter.close(true);
                                this.applier.clear();
                                this.applier.onEndChanges();
                                rememberEventDispatcher.dispatchRememberObservers();
                            } catch (Throwable th) {
                                slotWriterOpenWriter.close(false);
                                throw th;
                            }
                        }
                        rememberEventDispatcher.dispatchAbandons();
                        rememberEventDispatcher.clear();
                    } catch (Throwable th2) {
                        rememberEventDispatcher.clear();
                        throw th2;
                    }
                }
                this.composer.dispose$runtime();
            }
            Unit unit2 = Unit.INSTANCE;
        }
        this.parent.unregisterComposition$runtime(this);
    }

    @Override // androidx.compose.runtime.Composition
    public boolean getHasInvalidations() {
        boolean z;
        synchronized (this.lock) {
            z = ScopeMap.m3559getSizeimpl(this.invalidations) > 0;
        }
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.runtime.ControlledComposition
    public void recordModificationsOf(Set<? extends Object> values) {
        Object obj;
        Object objPlus;
        do {
            obj = this.pendingModifications.get();
            if (obj == null || Intrinsics.areEqual(obj, CompositionKt.PendingApplyNoModifications)) {
                objPlus = values;
            } else if (obj instanceof Set) {
                objPlus = new Set[]{obj, values};
            } else {
                if (!(obj instanceof Object[])) {
                    throw new IllegalStateException(("corrupt pendingModifications: " + this.pendingModifications).toString());
                }
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.collections.Set<kotlin.Any>>");
                objPlus = ArraysKt.plus((Set<? extends Object>[]) obj, values);
            }
        } while (!MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(this.pendingModifications, obj, objPlus));
        if (obj == null) {
            synchronized (this.lock) {
                drainPendingModificationsLocked();
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void prepareCompose(Function0<Unit> block) {
        this.composer.prepareCompose$runtime(block);
    }

    public final List<Pair<RecomposeScopeImpl, Object>> extractInvalidationsOf$runtime(Anchor anchor) {
        long[] jArr;
        char c;
        long j;
        int i;
        int i2;
        boolean zIsEmpty;
        int i3;
        int i4;
        long j2;
        boolean z;
        if (ScopeMap.m3559getSizeimpl(this.invalidations) > 0) {
            ArrayList arrayList = new ArrayList();
            SlotTable slotTable = this.slotTable;
            MutableScatterMap<Object, Object> mutableScatterMap = this.invalidations;
            long[] jArr2 = mutableScatterMap.metadata;
            int length = jArr2.length - 2;
            if (length >= 0) {
                int i5 = 0;
                while (true) {
                    long j3 = jArr2[i5];
                    char c2 = 7;
                    long j4 = -9187201950435737472L;
                    if ((((~j3) << 7) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i6 = 8;
                        int i7 = 8 - ((~(i5 - length)) >>> 31);
                        int i8 = 0;
                        while (i8 < i7) {
                            if ((j3 & 255) < 128) {
                                int i9 = (i5 << 3) + i8;
                                c = c2;
                                Object obj = mutableScatterMap.keys[i9];
                                j = j4;
                                Object obj2 = mutableScatterMap.values[i9];
                                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type Key of androidx.compose.runtime.collection.ScopeMap");
                                boolean z2 = true;
                                if (obj2 instanceof MutableScatterSet) {
                                    Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type androidx.collection.MutableScatterSet<Scope of androidx.compose.runtime.collection.ScopeMap>");
                                    MutableScatterSet mutableScatterSet = (MutableScatterSet) obj2;
                                    Object[] objArr = mutableScatterSet.elements;
                                    int i10 = i6;
                                    long[] jArr3 = mutableScatterSet.metadata;
                                    int length2 = jArr3.length - 2;
                                    if (length2 >= 0) {
                                        int i11 = i8;
                                        int i12 = 0;
                                        while (true) {
                                            long j5 = jArr3[i12];
                                            long[] jArr4 = jArr3;
                                            Object obj3 = obj;
                                            if ((((~j5) << c) & j5 & j) != j) {
                                                int i13 = 8 - ((~(i12 - length2)) >>> 31);
                                                int i14 = 0;
                                                while (i14 < i13) {
                                                    if ((j5 & 255) < 128) {
                                                        i3 = i11;
                                                        int i15 = (i12 << 3) + i14;
                                                        i4 = i14;
                                                        Object obj4 = objArr[i15];
                                                        j2 = j5;
                                                        RecomposeScopeImpl recomposeScopeImpl = (RecomposeScopeImpl) obj3;
                                                        Anchor anchor2 = recomposeScopeImpl.getAnchor();
                                                        if (anchor2 == null || !slotTable.inGroup(anchor, anchor2)) {
                                                            z = false;
                                                        } else {
                                                            arrayList.add(TuplesKt.to(recomposeScopeImpl, obj4));
                                                            z = true;
                                                        }
                                                        if (z) {
                                                            mutableScatterSet.removeElementAt(i15);
                                                        }
                                                    } else {
                                                        i3 = i11;
                                                        i4 = i14;
                                                        j2 = j5;
                                                    }
                                                    j5 = j2 >> i10;
                                                    i14 = i4 + 1;
                                                    i11 = i3;
                                                }
                                                i = i11;
                                                if (i13 != i10) {
                                                    break;
                                                }
                                            } else {
                                                i = i11;
                                            }
                                            if (i12 == length2) {
                                                break;
                                            }
                                            i12++;
                                            obj = obj3;
                                            jArr3 = jArr4;
                                            i11 = i;
                                            i10 = 8;
                                        }
                                    } else {
                                        i = i8;
                                    }
                                    zIsEmpty = mutableScatterSet.isEmpty();
                                } else {
                                    i = i8;
                                    Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type Scope of androidx.compose.runtime.collection.ScopeMap");
                                    RecomposeScopeImpl recomposeScopeImpl2 = (RecomposeScopeImpl) obj;
                                    Anchor anchor3 = recomposeScopeImpl2.getAnchor();
                                    if (anchor3 == null || !slotTable.inGroup(anchor, anchor3)) {
                                        z2 = false;
                                    } else {
                                        arrayList.add(TuplesKt.to(recomposeScopeImpl2, obj2));
                                    }
                                    zIsEmpty = z2;
                                }
                                if (zIsEmpty) {
                                    mutableScatterMap.removeValueAt(i9);
                                }
                                i2 = 8;
                            } else {
                                jArr2 = jArr2;
                                j3 = j3;
                                c = c2;
                                j = j4;
                                i = i8;
                                i2 = i6;
                            }
                            i8 = i + 1;
                            i6 = i2;
                            j3 >>= i2;
                            c2 = c;
                            j4 = j;
                            jArr2 = jArr2;
                        }
                        jArr = jArr2;
                        if (i7 != i6) {
                            break;
                        }
                    } else {
                        jArr = jArr2;
                    }
                    if (i5 == length) {
                        break;
                    }
                    i5++;
                    jArr2 = jArr;
                }
            }
            return arrayList;
        }
        return CollectionsKt.emptyList();
    }

    private final void addPendingInvalidationsLocked(Object value, boolean forgetConditionalScopes) {
        Object obj = this.observations.get(value);
        if (obj == null) {
            return;
        }
        if (obj instanceof MutableScatterSet) {
            MutableScatterSet mutableScatterSet = (MutableScatterSet) obj;
            Object[] objArr = mutableScatterSet.elements;
            long[] jArr = mutableScatterSet.metadata;
            int length = jArr.length - 2;
            if (length < 0) {
                return;
            }
            int i = 0;
            while (true) {
                long j = jArr[i];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i2 = 8 - ((~(i - length)) >>> 31);
                    for (int i3 = 0; i3 < i2; i3++) {
                        if ((255 & j) < 128) {
                            RecomposeScopeImpl recomposeScopeImpl = (RecomposeScopeImpl) objArr[(i << 3) + i3];
                            if (!ScopeMap.m3561removeimpl(this.observationsProcessed, value, recomposeScopeImpl) && recomposeScopeImpl.invalidateForResult(value) != InvalidationResult.IGNORED) {
                                if (recomposeScopeImpl.isConditional() && !forgetConditionalScopes) {
                                    this.conditionallyInvalidatedScopes.add(recomposeScopeImpl);
                                } else {
                                    this.invalidatedScopes.add(recomposeScopeImpl);
                                }
                            }
                        }
                        j >>= 8;
                    }
                    if (i2 != 8) {
                        return;
                    }
                }
                if (i == length) {
                    return;
                } else {
                    i++;
                }
            }
        } else {
            RecomposeScopeImpl recomposeScopeImpl2 = (RecomposeScopeImpl) obj;
            if (ScopeMap.m3561removeimpl(this.observationsProcessed, value, recomposeScopeImpl2) || recomposeScopeImpl2.invalidateForResult(value) == InvalidationResult.IGNORED) {
                return;
            }
            if (recomposeScopeImpl2.isConditional() && !forgetConditionalScopes) {
                this.conditionallyInvalidatedScopes.add(recomposeScopeImpl2);
            } else {
                this.invalidatedScopes.add(recomposeScopeImpl2);
            }
        }
    }

    private final void cleanUpDerivedStateObservations() {
        long j;
        char c;
        long j2;
        long j3;
        long[] jArr;
        long[] jArr2;
        int i;
        long j4;
        char c2;
        long j5;
        int i2;
        long[] jArr3;
        int i3;
        int i4;
        MutableScatterMap<Object, Object> mutableScatterMap = this.derivedStates;
        long[] jArr4 = mutableScatterMap.metadata;
        int length = jArr4.length - 2;
        long j6 = 255;
        char c3 = 7;
        long j7 = -9187201950435737472L;
        int i5 = 8;
        if (length >= 0) {
            int i6 = 0;
            while (true) {
                long j8 = jArr4[i6];
                j3 = 128;
                if ((((~j8) << c3) & j8 & j7) != j7) {
                    int i7 = 8 - ((~(i6 - length)) >>> 31);
                    int i8 = 0;
                    while (i8 < i7) {
                        if ((j8 & j6) < 128) {
                            j4 = j6;
                            int i9 = (i6 << 3) + i8;
                            Object obj = mutableScatterMap.keys[i9];
                            Object obj2 = mutableScatterMap.values[i9];
                            c2 = c3;
                            boolean zIsEmpty = true;
                            if (obj2 instanceof MutableScatterSet) {
                                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type androidx.collection.MutableScatterSet<Scope of androidx.compose.runtime.collection.ScopeMap>");
                                MutableScatterSet mutableScatterSet = (MutableScatterSet) obj2;
                                Object[] objArr = mutableScatterSet.elements;
                                j5 = j7;
                                long[] jArr5 = mutableScatterSet.metadata;
                                int length2 = jArr5.length - 2;
                                if (length2 >= 0) {
                                    int i10 = i5;
                                    int i11 = 0;
                                    while (true) {
                                        long j9 = jArr5[i11];
                                        Object[] objArr2 = objArr;
                                        long[] jArr6 = jArr5;
                                        if ((((~j9) << c2) & j9 & j5) != j5) {
                                            int i12 = 8 - ((~(i11 - length2)) >>> 31);
                                            int i13 = 0;
                                            while (i13 < i12) {
                                                if ((j9 & j4) < 128) {
                                                    jArr3 = jArr4;
                                                    int i14 = (i11 << 3) + i13;
                                                    i3 = i8;
                                                    i4 = i13;
                                                    if (!ScopeMap.m3555containsimpl(this.observations, (DerivedState) objArr2[i14])) {
                                                        mutableScatterSet.removeElementAt(i14);
                                                    }
                                                } else {
                                                    jArr3 = jArr4;
                                                    i3 = i8;
                                                    i4 = i13;
                                                }
                                                j9 >>= i10;
                                                i13 = i4 + 1;
                                                jArr4 = jArr3;
                                                i8 = i3;
                                            }
                                            jArr2 = jArr4;
                                            i = i8;
                                            if (i12 != i10) {
                                                break;
                                            }
                                        } else {
                                            jArr2 = jArr4;
                                            i = i8;
                                        }
                                        int i15 = i11;
                                        if (i15 == length2) {
                                            break;
                                        }
                                        i11 = i15 + 1;
                                        objArr = objArr2;
                                        jArr5 = jArr6;
                                        jArr4 = jArr2;
                                        i8 = i;
                                        i10 = 8;
                                    }
                                } else {
                                    jArr2 = jArr4;
                                    i = i8;
                                }
                                zIsEmpty = mutableScatterSet.isEmpty();
                            } else {
                                jArr2 = jArr4;
                                i = i8;
                                j5 = j7;
                                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type Scope of androidx.compose.runtime.collection.ScopeMap");
                                if (ScopeMap.m3555containsimpl(this.observations, (DerivedState) obj2)) {
                                    zIsEmpty = false;
                                }
                            }
                            if (zIsEmpty) {
                                mutableScatterMap.removeValueAt(i9);
                            }
                            i2 = 8;
                        } else {
                            jArr2 = jArr4;
                            i = i8;
                            j4 = j6;
                            c2 = c3;
                            j5 = j7;
                            i2 = i5;
                        }
                        j8 >>= i2;
                        i8 = i + 1;
                        i5 = i2;
                        c3 = c2;
                        j6 = j4;
                        j7 = j5;
                        jArr4 = jArr2;
                    }
                    jArr = jArr4;
                    j = j6;
                    c = c3;
                    j2 = j7;
                    if (i7 != i5) {
                        break;
                    }
                } else {
                    jArr = jArr4;
                    j = j6;
                    c = c3;
                    j2 = j7;
                }
                if (i6 == length) {
                    break;
                }
                i6++;
                c3 = c;
                j6 = j;
                j7 = j2;
                jArr4 = jArr;
                i5 = 8;
            }
        } else {
            j = 255;
            c = 7;
            j2 = -9187201950435737472L;
            j3 = 128;
        }
        if (!this.conditionallyInvalidatedScopes.isNotEmpty()) {
            return;
        }
        MutableScatterSet<RecomposeScopeImpl> mutableScatterSet2 = this.conditionallyInvalidatedScopes;
        Object[] objArr3 = mutableScatterSet2.elements;
        long[] jArr7 = mutableScatterSet2.metadata;
        int length3 = jArr7.length - 2;
        if (length3 < 0) {
            return;
        }
        int i16 = 0;
        while (true) {
            long j10 = jArr7[i16];
            if ((((~j10) << c) & j10 & j2) != j2) {
                int i17 = 8 - ((~(i16 - length3)) >>> 31);
                for (int i18 = 0; i18 < i17; i18++) {
                    if ((j10 & j) < j3) {
                        int i19 = (i16 << 3) + i18;
                        if (!((RecomposeScopeImpl) objArr3[i19]).isConditional()) {
                            mutableScatterSet2.removeElementAt(i19);
                        }
                    }
                    j10 >>= 8;
                }
                if (i17 != 8) {
                    return;
                }
            }
            if (i16 == length3) {
                return;
            } else {
                i16++;
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition, androidx.compose.runtime.RecomposeScopeOwner
    public void recordReadOf(Object value) {
        RecomposeScopeImpl currentRecomposeScope$runtime;
        int i;
        int i2;
        if (getAreChildrenComposing() || (currentRecomposeScope$runtime = this.composer.getCurrentRecomposeScope$runtime()) == null) {
            return;
        }
        int i3 = 1;
        currentRecomposeScope$runtime.setUsed(true);
        boolean zRecordRead = currentRecomposeScope$runtime.recordRead(value);
        CompositionObserver compositionObserverObserver = observer();
        if (compositionObserverObserver != null) {
            compositionObserverObserver.onReadInScope(currentRecomposeScope$runtime, value);
        }
        if (zRecordRead) {
            return;
        }
        if (value instanceof StateObjectImpl) {
            ReaderKind.Companion companion = ReaderKind.INSTANCE;
            ((StateObjectImpl) value).m3592recordReadInh_f27i8$runtime(ReaderKind.m3579constructorimpl(1));
        }
        ScopeMap.m3548addimpl(this.observations, value, currentRecomposeScope$runtime);
        if (value instanceof DerivedState) {
            DerivedState<?> derivedState = (DerivedState) value;
            DerivedState.Record<?> currentRecord = derivedState.getCurrentRecord();
            ScopeMap.m3563removeScopeimpl(this.derivedStates, value);
            ObjectIntMap<StateObject> dependencies = currentRecord.getDependencies();
            Object[] objArr = dependencies.keys;
            long[] jArr = dependencies.metadata;
            int length = jArr.length - 2;
            if (length >= 0) {
                int i4 = 0;
                while (true) {
                    long j = jArr[i4];
                    if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i5 = 8;
                        int i6 = 8 - ((~(i4 - length)) >>> 31);
                        int i7 = 0;
                        while (i7 < i6) {
                            if ((j & 255) < 128) {
                                i2 = i3;
                                StateObject stateObject = (StateObject) objArr[(i4 << 3) + i7];
                                if (stateObject instanceof StateObjectImpl) {
                                    ReaderKind.Companion companion2 = ReaderKind.INSTANCE;
                                    ((StateObjectImpl) stateObject).m3592recordReadInh_f27i8$runtime(ReaderKind.m3579constructorimpl(i2));
                                }
                                ScopeMap.m3548addimpl(this.derivedStates, stateObject, value);
                            } else {
                                i2 = i3;
                                i5 = i5;
                            }
                            j >>= i5;
                            i7++;
                            i3 = i2;
                            i5 = i5;
                        }
                        i = i3;
                        if (i6 != i5) {
                            break;
                        }
                    } else {
                        i = i3;
                    }
                    if (i4 == length) {
                        break;
                    }
                    i4++;
                    i3 = i;
                }
            }
            currentRecomposeScope$runtime.recordDerivedStateValue(derivedState, currentRecord.getCurrentValue());
        }
    }

    private final void invalidateScopeOfLocked(Object value) {
        Object obj = this.observations.get(value);
        if (obj == null) {
            return;
        }
        if (obj instanceof MutableScatterSet) {
            MutableScatterSet mutableScatterSet = (MutableScatterSet) obj;
            Object[] objArr = mutableScatterSet.elements;
            long[] jArr = mutableScatterSet.metadata;
            int length = jArr.length - 2;
            if (length < 0) {
                return;
            }
            int i = 0;
            while (true) {
                long j = jArr[i];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i2 = 8 - ((~(i - length)) >>> 31);
                    for (int i3 = 0; i3 < i2; i3++) {
                        if ((255 & j) < 128) {
                            RecomposeScopeImpl recomposeScopeImpl = (RecomposeScopeImpl) objArr[(i << 3) + i3];
                            if (recomposeScopeImpl.invalidateForResult(value) == InvalidationResult.IMMINENT) {
                                ScopeMap.m3548addimpl(this.observationsProcessed, value, recomposeScopeImpl);
                            }
                        }
                        j >>= 8;
                    }
                    if (i2 != 8) {
                        return;
                    }
                }
                if (i == length) {
                    return;
                } else {
                    i++;
                }
            }
        } else {
            RecomposeScopeImpl recomposeScopeImpl2 = (RecomposeScopeImpl) obj;
            if (recomposeScopeImpl2.invalidateForResult(value) == InvalidationResult.IMMINENT) {
                ScopeMap.m3548addimpl(this.observationsProcessed, value, recomposeScopeImpl2);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0056 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:21:0x0058 A[Catch: all -> 0x0064, LOOP:0: B:11:0x0021->B:21:0x0058, LOOP_END, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x000e, B:8:0x0012, B:11:0x0021, B:13:0x0031, B:15:0x003d, B:17:0x0046, B:18:0x0050, B:21:0x0058, B:22:0x005b, B:23:0x0060), top: B:29:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:32:0x0060 A[EDGE_INSN: B:32:0x0060->B:23:0x0060 BREAK  A[LOOP:0: B:11:0x0021->B:21:0x0058], SYNTHETIC] */
    @Override // androidx.compose.runtime.ControlledComposition
    public void recordWriteOf(Object value) {
        synchronized (this.lock) {
            invalidateScopeOfLocked(value);
            Object obj = this.derivedStates.get(value);
            if (obj != null) {
                if (!(obj instanceof MutableScatterSet)) {
                    invalidateScopeOfLocked((DerivedState) obj);
                } else {
                    MutableScatterSet mutableScatterSet = (MutableScatterSet) obj;
                    Object[] objArr = mutableScatterSet.elements;
                    long[] jArr = mutableScatterSet.metadata;
                    int length = jArr.length - 2;
                    if (length >= 0) {
                        int i = 0;
                        while (true) {
                            long j = jArr[i];
                            if ((((~j) << 7) & j & (-9187201950435737472L)) == -9187201950435737472L) {
                                if (i != length) {
                                    break;
                                    break;
                                }
                                i++;
                            } else {
                                int i2 = 8 - ((~(i - length)) >>> 31);
                                for (int i3 = 0; i3 < i2; i3++) {
                                    if ((255 & j) < 128) {
                                        invalidateScopeOfLocked((DerivedState) objArr[(i << 3) + i3]);
                                    }
                                    j >>= 8;
                                }
                                if (i2 != 8) {
                                    break;
                                } else if (i != length) {
                                    break;
                                } else {
                                    i++;
                                }
                            }
                        }
                    }
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean recompose() {
        synchronized (this.lock) {
            PausedCompositionImpl pausedCompositionImpl = this.pendingPausedComposition;
            if (pausedCompositionImpl != null && !pausedCompositionImpl.isRecomposing$runtime()) {
                pausedCompositionImpl.markIncomplete$runtime();
                return false;
            }
            drainPendingModificationsForCompositionLocked();
            try {
                MutableScatterMap<Object, Object> mutableScatterMapM3390takeInvalidationsafanTW4 = m3390takeInvalidationsafanTW4();
                try {
                    boolean zM3388recomposeaFTiNEg$runtime = this.composer.m3388recomposeaFTiNEg$runtime(mutableScatterMapM3390takeInvalidationsafanTW4, this.shouldPause);
                    if (!zM3388recomposeaFTiNEg$runtime) {
                        drainPendingModificationsLocked();
                    }
                    return zM3388recomposeaFTiNEg$runtime;
                } catch (Throwable th) {
                    this.invalidations = mutableScatterMapM3390takeInvalidationsafanTW4;
                    throw th;
                }
            } catch (Throwable th2) {
                try {
                    if (!this.abandonSet.isEmpty()) {
                        RememberEventDispatcher rememberEventDispatcher = this.rememberManager;
                        try {
                            rememberEventDispatcher.prepare(this.abandonSet, this.composer.getErrorContext$runtime());
                            rememberEventDispatcher.dispatchAbandons();
                        } finally {
                            rememberEventDispatcher.clear();
                        }
                    }
                    throw th2;
                } catch (Throwable th3) {
                    abandonChanges();
                    throw th3;
                }
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void disposeUnusedMovableContent(MovableContentState state) {
        RememberEventDispatcher rememberEventDispatcher = this.rememberManager;
        try {
            rememberEventDispatcher.prepare(this.abandonSet, this.composer.getErrorContext$runtime());
            SlotWriter slotWriterOpenWriter = state.getSlotTable().openWriter();
            try {
                ComposerKt.removeCurrentGroup(slotWriterOpenWriter, this.rememberManager);
                Unit unit = Unit.INSTANCE;
                slotWriterOpenWriter.close(true);
                rememberEventDispatcher.dispatchRememberObservers();
                rememberEventDispatcher.clear();
            } catch (Throwable th) {
                slotWriterOpenWriter.close(false);
                throw th;
            }
        } catch (Throwable th2) {
            rememberEventDispatcher.clear();
            throw th2;
        }
    }

    private final void applyChangesInLocked(ChangeList changes) {
        RememberEventDispatcher rememberManager$runtime;
        long[] jArr;
        long[] jArr2;
        long j;
        char c;
        long j2;
        int i;
        boolean zIsEmpty;
        long[] jArr3;
        RecordingApplier<Object> pausableApplier$runtime;
        this.rememberManager.prepare(this.abandonSet, this.composer.getErrorContext$runtime());
        try {
            if (!changes.isEmpty()) {
                Object objBeginSection = Trace.INSTANCE.beginSection("Compose:applyChanges");
                try {
                    PausedCompositionImpl pausedCompositionImpl = this.pendingPausedComposition;
                    RecordingApplier<Object> recordingApplier = (pausedCompositionImpl == null || (pausableApplier$runtime = pausedCompositionImpl.getPausableApplier$runtime()) == null) ? this.applier : pausableApplier$runtime;
                    PausedCompositionImpl pausedCompositionImpl2 = this.pendingPausedComposition;
                    if (pausedCompositionImpl2 == null || (rememberManager$runtime = pausedCompositionImpl2.getRememberManager()) == null) {
                        rememberManager$runtime = this.rememberManager;
                    }
                    recordingApplier.onBeginChanges();
                    SlotWriter slotWriterOpenWriter = this.slotTable.openWriter();
                    int i2 = 0;
                    try {
                        changes.executeAndFlushAllPendingChanges(recordingApplier, slotWriterOpenWriter, rememberManager$runtime, this.composer.getErrorContext$runtime());
                        Unit unit = Unit.INSTANCE;
                        slotWriterOpenWriter.close(true);
                        recordingApplier.onEndChanges();
                        Unit unit2 = Unit.INSTANCE;
                        Trace.INSTANCE.endSection(objBeginSection);
                        this.rememberManager.dispatchRememberObservers();
                        this.rememberManager.dispatchSideEffects();
                        if (this.pendingInvalidScopes) {
                            Object objBeginSection2 = Trace.INSTANCE.beginSection("Compose:unobserve");
                            try {
                                this.pendingInvalidScopes = false;
                                MutableScatterMap<Object, Object> mutableScatterMap = this.observations;
                                long[] jArr4 = mutableScatterMap.metadata;
                                int length = jArr4.length - 2;
                                if (length >= 0) {
                                    int i3 = 0;
                                    while (true) {
                                        long j3 = jArr4[i3];
                                        char c2 = 7;
                                        long j4 = -9187201950435737472L;
                                        if ((((~j3) << 7) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                                            int i4 = 8;
                                            int i5 = 8 - ((~(i3 - length)) >>> 31);
                                            int i6 = i2;
                                            while (i6 < i5) {
                                                if ((j3 & 255) < 128) {
                                                    int i7 = (i3 << 3) + i6;
                                                    c = c2;
                                                    Object obj = mutableScatterMap.keys[i7];
                                                    Object obj2 = mutableScatterMap.values[i7];
                                                    j2 = j4;
                                                    if (obj2 instanceof MutableScatterSet) {
                                                        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type androidx.collection.MutableScatterSet<Scope of androidx.compose.runtime.collection.ScopeMap>");
                                                        MutableScatterSet mutableScatterSet = (MutableScatterSet) obj2;
                                                        Object[] objArr = mutableScatterSet.elements;
                                                        long[] jArr5 = mutableScatterSet.metadata;
                                                        int length2 = jArr5.length - 2;
                                                        if (length2 >= 0) {
                                                            j = j3;
                                                            int i8 = i4;
                                                            int i9 = 0;
                                                            while (true) {
                                                                long j5 = jArr5[i9];
                                                                Object[] objArr2 = objArr;
                                                                long[] jArr6 = jArr5;
                                                                if ((((~j5) << c) & j5 & j2) != j2) {
                                                                    int i10 = 8 - ((~(i9 - length2)) >>> 31);
                                                                    int i11 = 0;
                                                                    while (i11 < i10) {
                                                                        if ((j5 & 255) < 128) {
                                                                            jArr3 = jArr4;
                                                                            int i12 = (i9 << 3) + i11;
                                                                            if (!((RecomposeScopeImpl) objArr2[i12]).getValid()) {
                                                                                mutableScatterSet.removeElementAt(i12);
                                                                            }
                                                                        } else {
                                                                            jArr3 = jArr4;
                                                                        }
                                                                        j5 >>= i8;
                                                                        i11++;
                                                                        jArr4 = jArr3;
                                                                    }
                                                                    jArr2 = jArr4;
                                                                    if (i10 != i8) {
                                                                        break;
                                                                    }
                                                                } else {
                                                                    jArr2 = jArr4;
                                                                }
                                                                if (i9 == length2) {
                                                                    break;
                                                                }
                                                                i9++;
                                                                objArr = objArr2;
                                                                jArr5 = jArr6;
                                                                jArr4 = jArr2;
                                                                i8 = 8;
                                                            }
                                                        } else {
                                                            jArr2 = jArr4;
                                                            j = j3;
                                                        }
                                                        zIsEmpty = mutableScatterSet.isEmpty();
                                                    } else {
                                                        jArr2 = jArr4;
                                                        j = j3;
                                                        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type Scope of androidx.compose.runtime.collection.ScopeMap");
                                                        zIsEmpty = !((RecomposeScopeImpl) obj2).getValid();
                                                    }
                                                    if (zIsEmpty) {
                                                        mutableScatterMap.removeValueAt(i7);
                                                    }
                                                    i = 8;
                                                } else {
                                                    jArr2 = jArr4;
                                                    j = j3;
                                                    c = c2;
                                                    j2 = j4;
                                                    i = i4;
                                                }
                                                j3 = j >> i;
                                                i6++;
                                                i4 = i;
                                                c2 = c;
                                                j4 = j2;
                                                jArr4 = jArr2;
                                            }
                                            jArr = jArr4;
                                            if (i5 != i4) {
                                                break;
                                            }
                                        } else {
                                            jArr = jArr4;
                                        }
                                        if (i3 == length) {
                                            break;
                                        }
                                        i3++;
                                        jArr4 = jArr;
                                        i2 = 0;
                                    }
                                }
                                cleanUpDerivedStateObservations();
                                Unit unit3 = Unit.INSTANCE;
                                Trace.INSTANCE.endSection(objBeginSection2);
                            } catch (Throwable th) {
                                Trace.INSTANCE.endSection(objBeginSection2);
                                throw th;
                            }
                        }
                        try {
                            if (this.lateChanges.isEmpty() && this.pendingPausedComposition == null) {
                                this.rememberManager.dispatchAbandons();
                            }
                        } finally {
                            this.rememberManager.clear();
                        }
                    } catch (Throwable th2) {
                        slotWriterOpenWriter.close(false);
                        throw th2;
                    }
                } catch (Throwable th3) {
                    Trace.INSTANCE.endSection(objBeginSection);
                    throw th3;
                }
            } else {
                try {
                    if (this.lateChanges.isEmpty() && this.pendingPausedComposition == null) {
                        this.rememberManager.dispatchAbandons();
                    }
                } finally {
                    this.rememberManager.clear();
                }
            }
        } catch (Throwable th4) {
            try {
                if (this.lateChanges.isEmpty() && this.pendingPausedComposition == null) {
                    this.rememberManager.dispatchAbandons();
                }
                throw th4;
            } finally {
                this.rememberManager.clear();
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void applyChanges() {
        synchronized (this.lock) {
            try {
                applyChangesInLocked(this.changes);
                drainPendingModificationsLocked();
                Unit unit = Unit.INSTANCE;
                Unit unit2 = Unit.INSTANCE;
            } catch (Throwable th) {
                try {
                    if (!this.abandonSet.isEmpty()) {
                        RememberEventDispatcher rememberEventDispatcher = this.rememberManager;
                        try {
                            rememberEventDispatcher.prepare(this.abandonSet, this.composer.getErrorContext$runtime());
                            rememberEventDispatcher.dispatchAbandons();
                        } finally {
                            rememberEventDispatcher.clear();
                        }
                    }
                    throw th;
                } catch (Throwable th2) {
                    abandonChanges();
                    throw th2;
                }
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void applyLateChanges() {
        synchronized (this.lock) {
            try {
                if (this.lateChanges.isNotEmpty()) {
                    applyChangesInLocked(this.lateChanges);
                }
                Unit unit = Unit.INSTANCE;
                Unit unit2 = Unit.INSTANCE;
            } catch (Throwable th) {
                try {
                    if (!this.abandonSet.isEmpty()) {
                        RememberEventDispatcher rememberEventDispatcher = this.rememberManager;
                        try {
                            rememberEventDispatcher.prepare(this.abandonSet, this.composer.getErrorContext$runtime());
                            rememberEventDispatcher.dispatchAbandons();
                        } finally {
                            rememberEventDispatcher.clear();
                        }
                    }
                    throw th;
                } catch (Throwable th2) {
                    abandonChanges();
                    throw th2;
                }
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void changesApplied() {
        synchronized (this.lock) {
            try {
                this.composer.changesApplied$runtime();
                if (!this.abandonSet.isEmpty()) {
                    RememberEventDispatcher rememberEventDispatcher = this.rememberManager;
                    try {
                        rememberEventDispatcher.prepare(this.abandonSet, this.composer.getErrorContext$runtime());
                        rememberEventDispatcher.dispatchAbandons();
                        rememberEventDispatcher.clear();
                    } catch (Throwable th) {
                        rememberEventDispatcher.clear();
                        throw th;
                    }
                }
                Unit unit = Unit.INSTANCE;
                Unit unit2 = Unit.INSTANCE;
            } catch (Throwable th2) {
                try {
                    if (!this.abandonSet.isEmpty()) {
                        RememberEventDispatcher rememberEventDispatcher2 = this.rememberManager;
                        try {
                            rememberEventDispatcher2.prepare(this.abandonSet, this.composer.getErrorContext$runtime());
                            rememberEventDispatcher2.dispatchAbandons();
                        } finally {
                            rememberEventDispatcher2.clear();
                        }
                    }
                    throw th2;
                } catch (Throwable th3) {
                    abandonChanges();
                    throw th3;
                }
            }
        }
    }

    private final <T> T guardInvalidationsLocked(Function1<? super ScopeMap<RecomposeScopeImpl, Object>, ? extends T> block) {
        MutableScatterMap<Object, Object> mutableScatterMapM3390takeInvalidationsafanTW4 = m3390takeInvalidationsafanTW4();
        try {
            return block.invoke(ScopeMap.m3551boximpl(mutableScatterMapM3390takeInvalidationsafanTW4));
        } catch (Throwable th) {
            this.invalidations = mutableScatterMapM3390takeInvalidationsafanTW4;
            throw th;
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void abandonChanges() {
        this.pendingModifications.set(null);
        this.changes.clear();
        this.lateChanges.clear();
        if (this.abandonSet.isEmpty()) {
            return;
        }
        RememberEventDispatcher rememberEventDispatcher = this.rememberManager;
        try {
            rememberEventDispatcher.prepare(this.abandonSet, this.composer.getErrorContext$runtime());
            rememberEventDispatcher.dispatchAbandons();
        } finally {
            rememberEventDispatcher.clear();
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void invalidateAll() {
        synchronized (this.lock) {
            for (Object obj : this.slotTable.getSlots()) {
                RecomposeScopeImpl recomposeScopeImpl = obj instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) obj : null;
                if (recomposeScopeImpl != null) {
                    recomposeScopeImpl.invalidate();
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void verifyConsistent() {
        synchronized (this.lock) {
            if (!isComposing()) {
                this.composer.verifyConsistent$runtime();
                this.slotTable.verifyWellFormed();
                validateRecomposeScopeAnchors(this.slotTable);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public <R> R delegateInvalidations(ControlledComposition to, int groupIndex, Function0<? extends R> block) {
        if (to != null && !Intrinsics.areEqual(to, this) && groupIndex >= 0) {
            this.invalidationDelegate = (CompositionImpl) to;
            this.invalidationDelegateGroup = groupIndex;
            try {
                return block.invoke();
            } finally {
                this.invalidationDelegate = null;
                this.invalidationDelegateGroup = 0;
            }
        }
        return block.invoke();
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public ShouldPauseCallback getAndSetShouldPauseCallback(ShouldPauseCallback shouldPause) {
        ShouldPauseCallback shouldPauseCallback = this.shouldPause;
        this.shouldPause = shouldPause;
        return shouldPauseCallback;
    }

    @Override // androidx.compose.runtime.RecomposeScopeOwner
    public InvalidationResult invalidate(RecomposeScopeImpl scope, Object instance) {
        CompositionObserver compositionObserverObserver;
        CompositionImpl compositionImpl;
        if (scope.getDefaultsInScope()) {
            scope.setDefaultsInvalid(true);
        }
        Anchor anchor = scope.getAnchor();
        if (anchor == null || !anchor.getValid()) {
            return InvalidationResult.IGNORED;
        }
        if (this.slotTable.ownsAnchor(anchor)) {
            if (!scope.getCanRecompose()) {
                return InvalidationResult.IGNORED;
            }
            InvalidationResult invalidationResultInvalidateChecked = invalidateChecked(scope, anchor, instance);
            if (invalidationResultInvalidateChecked != InvalidationResult.IGNORED && (compositionObserverObserver = observer()) != null) {
                compositionObserverObserver.onScopeInvalidated(scope, instance);
            }
            return invalidationResultInvalidateChecked;
        }
        synchronized (this.lock) {
            compositionImpl = this.invalidationDelegate;
        }
        if (compositionImpl != null && compositionImpl.tryImminentInvalidation(scope, instance)) {
            return InvalidationResult.IMMINENT;
        }
        return InvalidationResult.IGNORED;
    }

    @Override // androidx.compose.runtime.RecomposeScopeOwner
    public void recomposeScopeReleased(RecomposeScopeImpl scope) {
        this.pendingInvalidScopes = true;
        CompositionObserver compositionObserverObserver = observer();
        if (compositionObserverObserver != null) {
            compositionObserverObserver.onScopeDisposed(scope);
        }
    }

    @Override // androidx.compose.runtime.CompositionServices
    public <T> T getCompositionService(CompositionServiceKey<T> key) {
        if (Intrinsics.areEqual(key, CompositionKt.getObservableCompositionServiceKey())) {
            return (T) this;
        }
        return null;
    }

    private final boolean tryImminentInvalidation(RecomposeScopeImpl scope, Object instance) {
        return isComposing() && this.composer.tryImminentInvalidation$runtime(scope, instance);
    }

    /* JADX WARN: Code duplicated, block: B:43:0x0098 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:44:0x009a A[Catch: all -> 0x00c3, LOOP:0: B:30:0x0059->B:44:0x009a, LOOP_END, TryCatch #0 {, blocks: (B:4:0x000b, B:6:0x0010, B:12:0x001f, B:14:0x0025, B:18:0x002b, B:19:0x0034, B:22:0x003a, B:23:0x0041, B:25:0x0047, B:27:0x004b, B:30:0x0059, B:32:0x0069, B:34:0x0075, B:36:0x007f, B:40:0x008e, B:44:0x009a, B:45:0x009d, B:48:0x00a2), top: B:62:0x000b }] */
    /* JADX WARN: Code duplicated, block: B:48:0x00a2 A[Catch: all -> 0x00c3, EDGE_INSN: B:48:0x00a2->B:49:0x00a7 BREAK  A[LOOP:0: B:30:0x0059->B:44:0x009a], TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x000b, B:6:0x0010, B:12:0x001f, B:14:0x0025, B:18:0x002b, B:19:0x0034, B:22:0x003a, B:23:0x0041, B:25:0x0047, B:27:0x004b, B:30:0x0059, B:32:0x0069, B:34:0x0075, B:36:0x007f, B:40:0x008e, B:44:0x009a, B:45:0x009d, B:48:0x00a2), top: B:62:0x000b }] */
    /* JADX WARN: Code duplicated, block: B:65:0x00a2 A[SYNTHETIC] */
    private final InvalidationResult invalidateChecked(RecomposeScopeImpl scope, Anchor anchor, Object instance) {
        synchronized (this.lock) {
            CompositionImpl compositionImpl = this.invalidationDelegate;
            CompositionImpl compositionImpl2 = null;
            if (compositionImpl != null) {
                if (!this.slotTable.groupContainsAnchor(this.invalidationDelegateGroup, anchor)) {
                    compositionImpl = null;
                }
                compositionImpl2 = compositionImpl;
            }
            if (compositionImpl2 == null) {
                if (!tryImminentInvalidation(scope, instance)) {
                    if (instance == null) {
                        ScopeMap.m3565setimpl(this.invalidations, scope, ScopeInvalidated.INSTANCE);
                    } else {
                        boolean z = instance instanceof DerivedState;
                        MutableScatterMap<Object, Object> mutableScatterMap = this.invalidations;
                        if (!z) {
                            ScopeMap.m3565setimpl(mutableScatterMap, scope, ScopeInvalidated.INSTANCE);
                        } else {
                            Object obj = mutableScatterMap.get(scope);
                            if (obj == null) {
                                ScopeMap.m3548addimpl(this.invalidations, scope, instance);
                                break;
                            }
                            if (!(obj instanceof MutableScatterSet)) {
                                if (obj != ScopeInvalidated.INSTANCE) {
                                    ScopeMap.m3548addimpl(this.invalidations, scope, instance);
                                    break;
                                }
                            } else {
                                MutableScatterSet mutableScatterSet = (MutableScatterSet) obj;
                                Object[] objArr = mutableScatterSet.elements;
                                long[] jArr = mutableScatterSet.metadata;
                                int length = jArr.length - 2;
                                if (length < 0) {
                                    ScopeMap.m3548addimpl(this.invalidations, scope, instance);
                                    break;
                                }
                                int i = 0;
                                loop0: while (true) {
                                    long j = jArr[i];
                                    if ((((~j) << 7) & j & (-9187201950435737472L)) == -9187201950435737472L) {
                                        if (i == length) {
                                            ScopeMap.m3548addimpl(this.invalidations, scope, instance);
                                            break;
                                        }
                                        i++;
                                    } else {
                                        int i2 = 8;
                                        int i3 = 8 - ((~(i - length)) >>> 31);
                                        int i4 = 0;
                                        while (i4 < i3) {
                                            if ((j & 255) < 128 && objArr[(i << 3) + i4] == ScopeInvalidated.INSTANCE) {
                                                break loop0;
                                            }
                                            j >>= i2;
                                            i4++;
                                            i2 = i2;
                                        }
                                        if (i3 == i2) {
                                            if (i == length) {
                                                i++;
                                            }
                                        }
                                        ScopeMap.m3548addimpl(this.invalidations, scope, instance);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    return InvalidationResult.IMMINENT;
                }
            }
            if (compositionImpl2 != null) {
                return compositionImpl2.invalidateChecked(scope, anchor, instance);
            }
            this.parent.invalidate$runtime(this);
            return isComposing() ? InvalidationResult.DEFERRED : InvalidationResult.SCHEDULED;
        }
    }

    public final void removeObservation$runtime(Object instance, RecomposeScopeImpl scope) {
        ScopeMap.m3561removeimpl(this.observations, instance, scope);
    }

    public final void removeDerivedStateObservation$runtime(DerivedState<?> state) {
        if (ScopeMap.m3555containsimpl(this.observations, state)) {
            return;
        }
        ScopeMap.m3563removeScopeimpl(this.derivedStates, state);
    }

    /* JADX INFO: renamed from: takeInvalidations-afanTW4, reason: not valid java name */
    private final MutableScatterMap<Object, Object> m3390takeInvalidationsafanTW4() {
        MutableScatterMap<Object, Object> mutableScatterMap = this.invalidations;
        this.invalidations = ScopeMap.m3554constructorimpl$default(null, 1, null);
        return mutableScatterMap;
    }

    private final void validateRecomposeScopeAnchors(SlotTable slotTable) {
        Object[] slots = slotTable.getSlots();
        ArrayList arrayList = new ArrayList();
        for (Object obj : slots) {
            RecomposeScopeImpl recomposeScopeImpl = obj instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) obj : null;
            if (recomposeScopeImpl != null) {
                arrayList.add(recomposeScopeImpl);
            }
        }
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        for (int i = 0; i < size; i++) {
            RecomposeScopeImpl recomposeScopeImpl2 = (RecomposeScopeImpl) arrayList2.get(i);
            Anchor anchor = recomposeScopeImpl2.getAnchor();
            if (anchor != null && !slotTable.slotsOf$runtime(anchor.toIndexFor(slotTable)).contains(recomposeScopeImpl2)) {
                PreconditionsKt.throwIllegalStateException("Misaligned anchor " + anchor + " in scope " + recomposeScopeImpl2 + " encountered, scope found at " + ArraysKt.indexOf((RecomposeScopeImpl[]) slotTable.getSlots(), recomposeScopeImpl2));
            }
        }
    }

    private final <T> T trackAbandonedValues(Function0<? extends T> block) {
        try {
            return block.invoke();
        } catch (Throwable th) {
            if (!this.abandonSet.isEmpty()) {
                RememberEventDispatcher rememberEventDispatcher = this.rememberManager;
                try {
                    rememberEventDispatcher.prepare(this.abandonSet, this.composer.getErrorContext$runtime());
                    rememberEventDispatcher.dispatchAbandons();
                } finally {
                    rememberEventDispatcher.clear();
                }
            }
            throw th;
        }
    }

    private final CompositionObserver observer() {
        return this.observerHolder.current();
    }

    @Override // androidx.compose.runtime.ReusableComposition
    public void deactivate() {
        synchronized (this.lock) {
            if (!(this.pendingPausedComposition == null)) {
                PreconditionsKt.throwIllegalStateException("Deactivate is not supported while pausable composition is in progress");
            }
            boolean z = this.slotTable.getGroupsSize() > 0;
            if (z || !this.abandonSet.isEmpty()) {
                Object objBeginSection = Trace.INSTANCE.beginSection("Compose:deactivate");
                try {
                    RememberEventDispatcher rememberEventDispatcher = this.rememberManager;
                    try {
                        rememberEventDispatcher.prepare(this.abandonSet, this.composer.getErrorContext$runtime());
                        if (z) {
                            this.applier.onBeginChanges();
                            SlotWriter slotWriterOpenWriter = this.slotTable.openWriter();
                            try {
                                ComposerKt.deactivateCurrentGroup(slotWriterOpenWriter, this.rememberManager);
                                Unit unit = Unit.INSTANCE;
                                slotWriterOpenWriter.close(true);
                                this.applier.onEndChanges();
                                rememberEventDispatcher.dispatchRememberObservers();
                            } catch (Throwable th) {
                                slotWriterOpenWriter.close(false);
                                throw th;
                            }
                        }
                        rememberEventDispatcher.dispatchAbandons();
                        rememberEventDispatcher.clear();
                        Unit unit2 = Unit.INSTANCE;
                        Trace.INSTANCE.endSection(objBeginSection);
                    } catch (Throwable th2) {
                        rememberEventDispatcher.clear();
                        throw th2;
                    }
                } catch (Throwable th3) {
                    Trace.INSTANCE.endSection(objBeginSection);
                    throw th3;
                }
            }
            ScopeMap.m3552clearimpl(this.observations);
            ScopeMap.m3552clearimpl(this.derivedStates);
            ScopeMap.m3552clearimpl(this.invalidations);
            this.changes.clear();
            this.lateChanges.clear();
            this.composer.deactivate$runtime();
            this.state = 1;
            Unit unit3 = Unit.INSTANCE;
        }
    }

    public final int composerStacksSizes$runtime() {
        return this.composer.stacksSize$runtime();
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0057 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:21:0x0059 A[LOOP:0: B:7:0x0016->B:21:0x0059, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:34:0x007d A[SYNTHETIC] */
    @Override // androidx.compose.runtime.ControlledComposition
    public boolean observesAnyOf(Set<? extends Object> values) {
        if (values instanceof ScatterSetWrapper) {
            ScatterSet set$runtime = ((ScatterSetWrapper) values).getSet$runtime();
            Object[] objArr = set$runtime.elements;
            long[] jArr = set$runtime.metadata;
            int length = jArr.length - 2;
            if (length >= 0) {
                int i = 0;
                loop0: while (true) {
                    long j = jArr[i];
                    if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i2 = 8 - ((~(i - length)) >>> 31);
                        for (int i3 = 0; i3 < i2; i3++) {
                            if ((255 & j) < 128) {
                                Object obj = objArr[(i << 3) + i3];
                                if (ScopeMap.m3555containsimpl(this.observations, obj) || ScopeMap.m3555containsimpl(this.derivedStates, obj)) {
                                    break loop0;
                                }
                            }
                            j >>= 8;
                        }
                        if (i2 == 8) {
                            if (i != length) {
                                i++;
                            }
                        }
                    } else if (i != length) {
                        i++;
                    }
                }
                return true;
            }
        } else {
            for (Object obj2 : values) {
                if (ScopeMap.m3555containsimpl(this.observations, obj2) || ScopeMap.m3555containsimpl(this.derivedStates, obj2)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:239:0x01a9 A[EDGE_INSN: B:239:0x01a9->B:80:0x01a9 BREAK  A[LOOP:13: B:67:0x016d->B:78:0x01a1], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:42:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:77:0x019f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:78:0x01a1 A[LOOP:13: B:67:0x016d->B:78:0x01a1, LOOP_END] */
    private final void addPendingInvalidationsLocked(Set<? extends Object> values, boolean forgetConditionalScopes) {
        long j;
        long j2;
        long j3;
        char c;
        long[] jArr;
        String str;
        int i;
        int i2;
        String str2;
        int i3;
        int i4;
        long j4;
        boolean zContains;
        String str3;
        int i5;
        long[] jArr2;
        int i6;
        long[] jArr3;
        int i7;
        int i8;
        long j5;
        int i9;
        boolean zIsEmpty;
        long[] jArr4;
        long[] jArr5;
        long j6;
        long[] jArr6;
        int i10;
        long[] jArr7;
        int i11;
        char c2;
        int i12;
        int i13;
        Object obj = null;
        int i14 = 8;
        if (values instanceof ScatterSetWrapper) {
            ScatterSet set$runtime = ((ScatterSetWrapper) values).getSet$runtime();
            Object[] objArr = set$runtime.elements;
            long[] jArr8 = set$runtime.metadata;
            int length = jArr8.length - 2;
            if (length >= 0) {
                int i15 = 0;
                j = 128;
                j2 = 255;
                while (true) {
                    long j7 = jArr8[i15];
                    char c3 = 7;
                    j3 = -9187201950435737472L;
                    if ((((~j7) << 7) & j7 & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i16 = 8 - ((~(i15 - length)) >>> 31);
                        int i17 = 0;
                        while (i17 < i16) {
                            if ((j7 & 255) < 128) {
                                Object obj2 = objArr[(i15 << 3) + i17];
                                c2 = c3;
                                if (obj2 instanceof RecomposeScopeImpl) {
                                    ((RecomposeScopeImpl) obj2).invalidateForResult(obj);
                                    jArr7 = jArr8;
                                    i11 = i15;
                                    i12 = length;
                                } else {
                                    addPendingInvalidationsLocked(obj2, forgetConditionalScopes);
                                    Object obj3 = this.derivedStates.get(obj2);
                                    if (obj3 == null) {
                                        jArr7 = jArr8;
                                        i11 = i15;
                                        i12 = length;
                                    } else if (obj3 instanceof MutableScatterSet) {
                                        MutableScatterSet mutableScatterSet = (MutableScatterSet) obj3;
                                        Object[] objArr2 = mutableScatterSet.elements;
                                        long[] jArr9 = mutableScatterSet.metadata;
                                        int length2 = jArr9.length - 2;
                                        if (length2 >= 0) {
                                            i11 = i15;
                                            int i18 = 0;
                                            while (true) {
                                                long j8 = jArr9[i18];
                                                int i19 = i14;
                                                i12 = length;
                                                if ((((~j8) << c2) & j8 & (-9187201950435737472L)) != -9187201950435737472L) {
                                                    int i20 = 8 - ((~(i18 - length2)) >>> 31);
                                                    int i21 = 0;
                                                    while (i21 < i20) {
                                                        if ((j8 & 255) < 128) {
                                                            addPendingInvalidationsLocked((DerivedState) objArr2[(i18 << 3) + i21], forgetConditionalScopes);
                                                        }
                                                        j8 >>= i19;
                                                        i21++;
                                                        jArr8 = jArr8;
                                                    }
                                                    jArr7 = jArr8;
                                                    if (i20 != i19) {
                                                        break;
                                                    }
                                                } else {
                                                    jArr7 = jArr8;
                                                }
                                                if (i18 == length2) {
                                                    break;
                                                }
                                                i18++;
                                                length = i12;
                                                jArr8 = jArr7;
                                                i14 = 8;
                                            }
                                        } else {
                                            jArr7 = jArr8;
                                            i11 = i15;
                                            i12 = length;
                                        }
                                    } else {
                                        jArr7 = jArr8;
                                        i11 = i15;
                                        i12 = length;
                                        addPendingInvalidationsLocked((DerivedState) obj3, forgetConditionalScopes);
                                    }
                                    Unit unit = Unit.INSTANCE;
                                }
                                i13 = 8;
                            } else {
                                jArr7 = jArr8;
                                i11 = i15;
                                c2 = c3;
                                i12 = length;
                                i13 = i14;
                            }
                            j7 >>= i13;
                            i17++;
                            i15 = i11;
                            i14 = i13;
                            c3 = c2;
                            length = i12;
                            jArr8 = jArr7;
                            obj = null;
                        }
                        jArr6 = jArr8;
                        int i22 = i15;
                        c = c3;
                        int i23 = length;
                        if (i16 != i14) {
                            break;
                        }
                        i10 = i22;
                        length = i23;
                    } else {
                        jArr6 = jArr8;
                        c = 7;
                        i10 = i15;
                    }
                    if (i10 == length) {
                        break;
                    }
                    i15 = i10 + 1;
                    jArr8 = jArr6;
                    obj = null;
                    i14 = 8;
                }
            } else {
                j = 128;
                j2 = 255;
                j3 = -9187201950435737472L;
                c = 7;
            }
        } else {
            j = 128;
            j2 = 255;
            j3 = -9187201950435737472L;
            c = 7;
            for (Object obj4 : values) {
                if (obj4 instanceof RecomposeScopeImpl) {
                    ((RecomposeScopeImpl) obj4).invalidateForResult(null);
                } else {
                    addPendingInvalidationsLocked(obj4, forgetConditionalScopes);
                    Object obj5 = this.derivedStates.get(obj4);
                    if (obj5 != null) {
                        if (obj5 instanceof MutableScatterSet) {
                            MutableScatterSet mutableScatterSet2 = (MutableScatterSet) obj5;
                            Object[] objArr3 = mutableScatterSet2.elements;
                            long[] jArr10 = mutableScatterSet2.metadata;
                            int length3 = jArr10.length - 2;
                            if (length3 >= 0) {
                                int i24 = 0;
                                while (true) {
                                    long j9 = jArr10[i24];
                                    if ((((~j9) << 7) & j9 & (-9187201950435737472L)) == -9187201950435737472L) {
                                        if (i24 != length3) {
                                            break;
                                            break;
                                        }
                                        i24++;
                                    } else {
                                        int i25 = 8 - ((~(i24 - length3)) >>> 31);
                                        for (int i26 = 0; i26 < i25; i26++) {
                                            if ((j9 & 255) < 128) {
                                                addPendingInvalidationsLocked((DerivedState) objArr3[(i24 << 3) + i26], forgetConditionalScopes);
                                            }
                                            j9 >>= 8;
                                        }
                                        if (i25 != 8) {
                                            break;
                                        } else if (i24 != length3) {
                                            break;
                                        } else {
                                            i24++;
                                        }
                                    }
                                }
                            }
                        } else {
                            addPendingInvalidationsLocked((DerivedState) obj5, forgetConditionalScopes);
                        }
                    }
                    Unit unit2 = Unit.INSTANCE;
                }
            }
        }
        MutableScatterSet<RecomposeScopeImpl> mutableScatterSet3 = this.conditionallyInvalidatedScopes;
        MutableScatterSet<RecomposeScopeImpl> mutableScatterSet4 = this.invalidatedScopes;
        String str4 = "null cannot be cast to non-null type androidx.collection.MutableScatterSet<Scope of androidx.compose.runtime.collection.ScopeMap>";
        if (forgetConditionalScopes && mutableScatterSet3.isNotEmpty()) {
            MutableScatterMap<Object, Object> mutableScatterMap = this.observations;
            long[] jArr11 = mutableScatterMap.metadata;
            int length4 = jArr11.length - 2;
            if (length4 >= 0) {
                int i27 = 0;
                while (true) {
                    long j10 = jArr11[i27];
                    if ((((~j10) << c) & j10 & j3) != j3) {
                        int i28 = 8 - ((~(i27 - length4)) >>> 31);
                        int i29 = 0;
                        while (i29 < i28) {
                            if ((j10 & j2) < j) {
                                int i30 = (i27 << 3) + i29;
                                Object obj6 = mutableScatterMap.keys[i30];
                                Object obj7 = mutableScatterMap.values[i30];
                                if (obj7 instanceof MutableScatterSet) {
                                    Intrinsics.checkNotNull(obj7, "null cannot be cast to non-null type androidx.collection.MutableScatterSet<Scope of androidx.compose.runtime.collection.ScopeMap>");
                                    MutableScatterSet mutableScatterSet5 = (MutableScatterSet) obj7;
                                    Object[] objArr4 = mutableScatterSet5.elements;
                                    jArr3 = jArr11;
                                    long[] jArr12 = mutableScatterSet5.metadata;
                                    j5 = j10;
                                    int length5 = jArr12.length - 2;
                                    i7 = length4;
                                    i8 = i27;
                                    if (length5 >= 0) {
                                        int i31 = 0;
                                        while (true) {
                                            long j11 = jArr12[i31];
                                            i9 = i28;
                                            Object[] objArr5 = objArr4;
                                            if ((((~j11) << c) & j11 & j3) != j3) {
                                                int i32 = 8 - ((~(i31 - length5)) >>> 31);
                                                int i33 = 0;
                                                while (i33 < i32) {
                                                    if ((j11 & j2) < j) {
                                                        jArr5 = jArr12;
                                                        int i34 = (i31 << 3) + i33;
                                                        j6 = j11;
                                                        RecomposeScopeImpl recomposeScopeImpl = (RecomposeScopeImpl) objArr5[i34];
                                                        if (mutableScatterSet3.contains(recomposeScopeImpl) || mutableScatterSet4.contains(recomposeScopeImpl)) {
                                                            mutableScatterSet5.removeElementAt(i34);
                                                        }
                                                    } else {
                                                        jArr5 = jArr12;
                                                        j6 = j11;
                                                    }
                                                    j11 = j6 >> 8;
                                                    i33++;
                                                    jArr12 = jArr5;
                                                }
                                                jArr4 = jArr12;
                                                if (i32 != 8) {
                                                    break;
                                                }
                                            } else {
                                                jArr4 = jArr12;
                                            }
                                            if (i31 == length5) {
                                                break;
                                            }
                                            i31++;
                                            i28 = i9;
                                            objArr4 = objArr5;
                                            jArr12 = jArr4;
                                        }
                                    } else {
                                        i9 = i28;
                                    }
                                    zIsEmpty = mutableScatterSet5.isEmpty();
                                } else {
                                    jArr3 = jArr11;
                                    i7 = length4;
                                    i8 = i27;
                                    j5 = j10;
                                    i9 = i28;
                                    Intrinsics.checkNotNull(obj7, "null cannot be cast to non-null type Scope of androidx.compose.runtime.collection.ScopeMap");
                                    RecomposeScopeImpl recomposeScopeImpl2 = (RecomposeScopeImpl) obj7;
                                    zIsEmpty = mutableScatterSet3.contains(recomposeScopeImpl2) || mutableScatterSet4.contains(recomposeScopeImpl2);
                                }
                                if (zIsEmpty) {
                                    mutableScatterMap.removeValueAt(i30);
                                }
                            } else {
                                jArr3 = jArr11;
                                i7 = length4;
                                i8 = i27;
                                j5 = j10;
                                i9 = i28;
                            }
                            j10 = j5 >> 8;
                            i29++;
                            jArr11 = jArr3;
                            length4 = i7;
                            i27 = i8;
                            i28 = i9;
                        }
                        jArr2 = jArr11;
                        int i35 = length4;
                        int i36 = i27;
                        if (i28 != 8) {
                            break;
                        }
                        length4 = i35;
                        i6 = i36;
                    } else {
                        jArr2 = jArr11;
                        i6 = i27;
                    }
                    if (i6 == length4) {
                        break;
                    }
                    i27 = i6 + 1;
                    jArr11 = jArr2;
                }
            }
            mutableScatterSet3.clear();
            cleanUpDerivedStateObservations();
            return;
        }
        if (mutableScatterSet4.isNotEmpty()) {
            MutableScatterMap<Object, Object> mutableScatterMap2 = this.observations;
            long[] jArr13 = mutableScatterMap2.metadata;
            int length6 = jArr13.length - 2;
            if (length6 >= 0) {
                int i37 = 0;
                while (true) {
                    long j12 = jArr13[i37];
                    if ((((~j12) << c) & j12 & j3) != j3) {
                        int i38 = 8 - ((~(i37 - length6)) >>> 31);
                        int i39 = 0;
                        while (i39 < i38) {
                            if ((j12 & j2) < j) {
                                int i40 = (i37 << 3) + i39;
                                Object obj8 = mutableScatterMap2.keys[i40];
                                Object obj9 = mutableScatterMap2.values[i40];
                                if (obj9 instanceof MutableScatterSet) {
                                    Intrinsics.checkNotNull(obj9, str4);
                                    MutableScatterSet mutableScatterSet6 = (MutableScatterSet) obj9;
                                    Object[] objArr6 = mutableScatterSet6.elements;
                                    long[] jArr14 = mutableScatterSet6.metadata;
                                    int length7 = jArr14.length - 2;
                                    if (length7 >= 0) {
                                        j4 = j12;
                                        int i41 = 0;
                                        while (true) {
                                            long j13 = jArr14[i41];
                                            i3 = length6;
                                            i4 = i37;
                                            if ((((~j13) << c) & j13 & j3) != j3) {
                                                int i42 = 8 - ((~(i41 - length7)) >>> 31);
                                                int i43 = 0;
                                                while (i43 < i42) {
                                                    if ((j13 & j2) < j) {
                                                        str3 = str4;
                                                        int i44 = (i41 << 3) + i43;
                                                        i5 = i43;
                                                        if (mutableScatterSet4.contains((RecomposeScopeImpl) objArr6[i44])) {
                                                            mutableScatterSet6.removeElementAt(i44);
                                                        }
                                                    } else {
                                                        str3 = str4;
                                                        i5 = i43;
                                                    }
                                                    j13 >>= 8;
                                                    i43 = i5 + 1;
                                                    str4 = str3;
                                                }
                                                str2 = str4;
                                                if (i42 != 8) {
                                                    break;
                                                }
                                            } else {
                                                str2 = str4;
                                            }
                                            if (i41 == length7) {
                                                break;
                                            }
                                            i41++;
                                            length6 = i3;
                                            i37 = i4;
                                            str4 = str2;
                                        }
                                    } else {
                                        str2 = str4;
                                        i3 = length6;
                                        i4 = i37;
                                        j4 = j12;
                                    }
                                    zContains = mutableScatterSet6.isEmpty();
                                } else {
                                    str2 = str4;
                                    i3 = length6;
                                    i4 = i37;
                                    j4 = j12;
                                    Intrinsics.checkNotNull(obj9, "null cannot be cast to non-null type Scope of androidx.compose.runtime.collection.ScopeMap");
                                    zContains = mutableScatterSet4.contains((RecomposeScopeImpl) obj9);
                                }
                                if (zContains) {
                                    mutableScatterMap2.removeValueAt(i40);
                                }
                            } else {
                                jArr13 = jArr13;
                                str2 = str4;
                                i3 = length6;
                                i4 = i37;
                                j4 = j12;
                            }
                            j12 = j4 >> 8;
                            i39++;
                            jArr13 = jArr13;
                            length6 = i3;
                            i37 = i4;
                            str4 = str2;
                        }
                        jArr = jArr13;
                        str = str4;
                        i = length6;
                        int i45 = i37;
                        if (i38 != 8) {
                            break;
                        } else {
                            i2 = i45;
                        }
                    } else {
                        jArr = jArr13;
                        str = str4;
                        i = length6;
                        i2 = i37;
                    }
                    int i46 = i;
                    if (i2 == i46) {
                        break;
                    }
                    i37 = i2 + 1;
                    length6 = i46;
                    jArr13 = jArr;
                    str4 = str;
                }
            }
            cleanUpDerivedStateObservations();
            mutableScatterSet4.clear();
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void insertMovableContent(List<Pair<MovableContentStateReference, MovableContentStateReference>> references) {
        int size = references.size();
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= size) {
                z = true;
                break;
            } else if (!Intrinsics.areEqual(references.get(i).getFirst().getComposition(), this)) {
                break;
            } else {
                i++;
            }
        }
        if (!z) {
            ComposerKt.composeImmediateRuntimeError("Check failed");
        }
        try {
            this.composer.insertMovableContentReferences(references);
            Unit unit = Unit.INSTANCE;
        } catch (Throwable th) {
            try {
                if (!this.abandonSet.isEmpty()) {
                    RememberEventDispatcher rememberEventDispatcher = this.rememberManager;
                    try {
                        rememberEventDispatcher.prepare(this.abandonSet, this.composer.getErrorContext$runtime());
                        rememberEventDispatcher.dispatchAbandons();
                    } finally {
                        rememberEventDispatcher.clear();
                    }
                }
                throw th;
            } catch (Throwable th2) {
                abandonChanges();
                throw th2;
            }
        }
    }

    private final <T> T guardChanges(Function0<? extends T> block) {
        try {
            return block.invoke();
        } catch (Throwable th) {
            try {
                if (!this.abandonSet.isEmpty()) {
                    RememberEventDispatcher rememberEventDispatcher = this.rememberManager;
                    try {
                        rememberEventDispatcher.prepare(this.abandonSet, this.composer.getErrorContext$runtime());
                        rememberEventDispatcher.dispatchAbandons();
                    } finally {
                        rememberEventDispatcher.clear();
                    }
                }
                throw th;
            } catch (Throwable th2) {
                abandonChanges();
                throw th2;
            }
        }
    }
}
