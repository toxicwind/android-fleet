package androidx.compose.runtime;

import androidx.collection.MutableObjectList;
import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ObjectList;
import androidx.collection.ObjectListKt;
import androidx.collection.ScatterMap;
import androidx.collection.ScatterMapKt;
import androidx.collection.ScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.runtime.collection.MultiValueMap;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.collection.ScatterSetWrapper;
import androidx.compose.runtime.collection.ScatterSetWrapperKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.ExtensionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentSet;
import androidx.compose.runtime.internal.SnapshotThreadLocal;
import androidx.compose.runtime.internal.Trace;
import androidx.compose.runtime.internal.Utils_androidKt;
import androidx.compose.runtime.snapshots.MutableSnapshot;
import androidx.compose.runtime.snapshots.ObserverHandle;
import androidx.compose.runtime.snapshots.ReaderKind;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotApplyResult;
import androidx.compose.runtime.snapshots.StateObjectImpl;
import androidx.compose.runtime.snapshots.TransparentObserverMutableSnapshot;
import androidx.compose.runtime.snapshots.TransparentObserverSnapshot;
import androidx.compose.runtime.tooling.CompositionData;
import androidx.compose.runtime.tooling.CompositionObserverHandle;
import androidx.compose.runtime.tooling.CompositionObserverKt;
import androidx.compose.runtime.tooling.CompositionRegistrationObserver;
import androidx.compose.runtime.tooling.ObservableComposition;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import com.google.protobuf.DescriptorProtos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ExceptionsKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Recomposer.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000â\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u0000 ã\u00012\u00020\u0001:\nß\u0001à\u0001á\u0001â\u0001ã\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010N\u001a\n\u0012\u0004\u0012\u000200\u0018\u00010/H\u0002J\u0006\u0010\\\u001a\u00020]J\b\u0010^\u001a\u000204H\u0002J\u001d\u0010^\u001a\u0002002\u0012\u0010_\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u0002000`H\u0082\bJ\u0010\u0010a\u001a\u0002002\u0006\u0010b\u001a\u00020\u0012H\u0002J\u000e\u0010c\u001a\u000200H\u0086@¢\u0006\u0002\u0010dJ&\u0010e\u001a\u0002002\u0006\u0010f\u001a\u00020\u00142\n\b\u0002\u0010g\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010h\u001a\u000204H\u0002J\u0017\u0010i\u001a\u0002002\f\u0010j\u001a\b\u0012\u0004\u0012\u0002000kH\u0082\bJ\u000e\u0010l\u001a\b\u0012\u0004\u0012\u00020\u00170\u0019H\u0002J\u000e\u0010m\u001a\b\u0012\u0004\u0012\u00020\u00170\u0019H\u0002J\b\u0010n\u001a\u000200H\u0002J\u0010\u0010o\u001a\u0002002\u0006\u0010p\u001a\u00020\u0017H\u0002J\u0010\u0010q\u001a\u0002002\u0006\u0010p\u001a\u00020\u0017H\u0002J\u0010\u0010r\u001a\u0002002\u0006\u0010p\u001a\u00020\u0017H\u0002J\u0010\u0010s\u001a\u0002002\u0006\u0010p\u001a\u00020\u0017H\u0002J\u0015\u0010t\u001a\u00020u2\u0006\u0010v\u001a\u00020KH\u0000¢\u0006\u0002\bwJ\n\u0010x\u001a\u0004\u0018\u000106H\u0002J\b\u0010y\u001a\u000200H\u0002J\u0010\u0010z\u001a\u0002002\u0006\u0010p\u001a\u00020\u0017H\u0002J\u0016\u0010{\u001a\u0002002\u0006\u0010B\u001a\u00020\u0003H\u0087@¢\u0006\u0002\u0010|J!\u0010}\u001a\u0002002\u0006\u0010~\u001a\u00020\u007f2\b\u0010\u0080\u0001\u001a\u00030\u0081\u0001H\u0082@¢\u0006\u0003\u0010\u0082\u0001J\u000f\u0010\u0085\u0001\u001a\u000200H\u0082@¢\u0006\u0002\u0010dJT\u0010\u0086\u0001\u001a\u0002002B\u0010j\u001a>\b\u0001\u0012\u0005\u0012\u00030\u0088\u0001\u0012\u0015\u0012\u00130\u007f¢\u0006\u000e\b\u0089\u0001\u0012\t\b\u008a\u0001\u0012\u0004\b\b(~\u0012\u000b\u0012\t\u0012\u0004\u0012\u0002000\u008b\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0087\u0001¢\u0006\u0003\b\u008c\u0001H\u0082@¢\u0006\u0003\u0010\u008d\u0001J\u0007\u0010\u008e\u0001\u001a\u000200J\u0007\u0010\u008f\u0001\u001a\u000200J\u000f\u0010\u0090\u0001\u001a\u000200H\u0086@¢\u0006\u0002\u0010dJ/\u0010\u0091\u0001\u001a\u0002002\u0006\u0010p\u001a\u00020\u00172\u0013\u0010\u0092\u0001\u001a\u000e\u0012\u0004\u0012\u0002000k¢\u0006\u0003\b\u0093\u0001H\u0010¢\u0006\u0006\b\u0094\u0001\u0010\u0095\u0001J@\u0010\u0096\u0001\u001a\t\u0012\u0004\u0012\u00020=0\u0097\u00012\u0006\u0010p\u001a\u00020\u00172\b\u0010\u0098\u0001\u001a\u00030\u0099\u00012\u0013\u0010\u0092\u0001\u001a\u000e\u0012\u0004\u0012\u0002000k¢\u0006\u0003\b\u0093\u0001H\u0010¢\u0006\u0006\b\u009a\u0001\u0010\u009b\u0001J8\u0010\u009c\u0001\u001a\t\u0012\u0004\u0012\u00020=0\u0097\u00012\u0006\u0010p\u001a\u00020\u00172\b\u0010\u0098\u0001\u001a\u00030\u0099\u00012\u000e\u0010\u009d\u0001\u001a\t\u0012\u0004\u0012\u00020=0\u0097\u0001H\u0010¢\u0006\u0003\b\u009e\u0001J\u0018\u0010\u009f\u0001\u001a\u0002002\u0007\u0010 \u0001\u001a\u00020=H\u0010¢\u0006\u0003\b¡\u0001J\u0011\u0010¢\u0001\u001a\u0002002\u0006\u0010p\u001a\u00020\u0017H\u0002J$\u0010£\u0001\u001a\u0004\u0018\u00010\u00172\u0006\u0010p\u001a\u00020\u00172\u000f\u0010¤\u0001\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u001bH\u0002J/\u0010¥\u0001\u001a\b\u0012\u0004\u0012\u00020\u00170\u00192\r\u0010¦\u0001\u001a\b\u0012\u0004\u0012\u00020 0\u00192\u000f\u0010¤\u0001\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u001bH\u0002J\t\u0010§\u0001\u001a\u000200H\u0002J\u001d\u0010¨\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u0002000`2\u0006\u0010p\u001a\u00020\u0017H\u0002J.\u0010©\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u0002000`2\u0006\u0010p\u001a\u00020\u00172\u000f\u0010¤\u0001\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u001bH\u0002J@\u0010ª\u0001\u001a\u0003H«\u0001\"\u0005\b\u0000\u0010«\u00012\u0006\u0010p\u001a\u00020\u00172\u000f\u0010¤\u0001\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u001b2\r\u0010j\u001a\t\u0012\u0005\u0012\u0003H«\u00010kH\u0082\b¢\u0006\u0003\u0010¬\u0001J\u0013\u0010\u00ad\u0001\u001a\u0002002\b\u0010®\u0001\u001a\u00030¯\u0001H\u0002J\u000f\u0010¶\u0001\u001a\u000200H\u0086@¢\u0006\u0002\u0010dJ\u0007\u0010·\u0001\u001a\u000200J\u0007\u0010¸\u0001\u001a\u000200J\u001f\u0010Â\u0001\u001a\u0002002\u000e\u0010Ã\u0001\u001a\t\u0012\u0005\u0012\u00030Ä\u00010-H\u0010¢\u0006\u0003\bÅ\u0001J\u0017\u0010Æ\u0001\u001a\u0002002\u0006\u0010p\u001a\u00020\u0017H\u0010¢\u0006\u0003\bÇ\u0001J\u0017\u0010È\u0001\u001a\u0002002\u0006\u0010p\u001a\u00020\u0017H\u0010¢\u0006\u0003\bÉ\u0001J\u0017\u0010Ê\u0001\u001a\u0002002\u0006\u0010p\u001a\u00020\u0017H\u0010¢\u0006\u0003\bË\u0001J\u0018\u0010Ì\u0001\u001a\u0002002\u0007\u0010 \u0001\u001a\u00020=H\u0010¢\u0006\u0003\bÍ\u0001J\u0018\u0010Î\u0001\u001a\u0002002\u0007\u0010Ï\u0001\u001a\u00020 H\u0010¢\u0006\u0003\bÐ\u0001J\u0018\u0010Ñ\u0001\u001a\u0002002\u0007\u0010Ï\u0001\u001a\u00020 H\u0010¢\u0006\u0003\bÒ\u0001J/\u0010Ó\u0001\u001a\u0002002\u0007\u0010Ï\u0001\u001a\u00020 2\u0007\u0010Ô\u0001\u001a\u00020)2\f\u0010Õ\u0001\u001a\u0007\u0012\u0002\b\u00030Ö\u0001H\u0010¢\u0006\u0003\b×\u0001J\u0017\u0010Ø\u0001\u001a\u0002002\u0006\u0010p\u001a\u00020\u0017H\u0010¢\u0006\u0003\bÙ\u0001J\u001a\u0010Ú\u0001\u001a\u0004\u0018\u00010)2\u0007\u0010Ï\u0001\u001a\u00020 H\u0010¢\u0006\u0003\bÛ\u0001R\u001e\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00060\u000ej\u0002`\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00170\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010!\u001a\u0016\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0#\u0012\u0004\u0012\u00020 0\"X\u0082\u0004¢\u0006\u0004\n\u0002\u0010$R\u000e\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010'\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020)0(X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010*\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020 0\"X\u0082\u0004¢\u0006\u0004\n\u0002\u0010$R\u0016\u0010+\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010,\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010-X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010.\u001a\n\u0012\u0004\u0012\u000200\u0018\u00010/X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u000202X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u000204X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00105\u001a\u0004\u0018\u000106X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u000204X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00108\u001a\b\u0012\u0004\u0012\u00020:09X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010;\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020=\u0018\u00010\u001b0<X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020?X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010AR\u0014\u0010B\u001a\u00020\u00038PX\u0090\u0004¢\u0006\u0006\u001a\u0004\bC\u0010AR\u0014\u0010D\u001a\u0002048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bE\u0010FR\u0014\u0010G\u001a\u0002048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bH\u0010FR\u001c\u0010I\u001a\n\u0012\u0004\u0012\u00020K\u0018\u00010JX\u0082\u000e¢\u0006\b\n\u0000\u0012\u0004\bL\u0010MR\u0014\u0010O\u001a\u0002048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bP\u0010FR \u0010Q\u001a\b\u0012\u0004\u0012\u00020:0R8FX\u0087\u0004¢\u0006\f\u0012\u0004\bS\u0010M\u001a\u0004\bT\u0010UR\u0017\u0010V\u001a\b\u0012\u0004\u0012\u00020:0W8F¢\u0006\u0006\u001a\u0004\bX\u0010YR\u0012\u0010Z\u001a\u00060[R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0083\u0001\u001a\u0002048BX\u0082\u0004¢\u0006\u0007\u001a\u0005\b\u0084\u0001\u0010FR\u0013\u0010°\u0001\u001a\u0002048F¢\u0006\u0007\u001a\u0005\b±\u0001\u0010FR\u0016\u0010²\u0001\u001a\u0002048BX\u0082\u0004¢\u0006\u0007\u001a\u0005\b³\u0001\u0010FR\u0016\u0010´\u0001\u001a\u0002048BX\u0082\u0004¢\u0006\u0007\u001a\u0005\bµ\u0001\u0010FR\u001b\u0010¹\u0001\u001a\u00070\u0007j\u0003`º\u00018PX\u0090\u0004¢\u0006\u0007\u001a\u0005\b»\u0001\u0010\nR\u0016\u0010¼\u0001\u001a\u0002048PX\u0090\u0004¢\u0006\u0007\u001a\u0005\b½\u0001\u0010FR\u0016\u0010¾\u0001\u001a\u0002048PX\u0090\u0004¢\u0006\u0007\u001a\u0005\b¿\u0001\u0010FR\u0016\u0010À\u0001\u001a\u0002048PX\u0090\u0004¢\u0006\u0007\u001a\u0005\bÁ\u0001\u0010FR\u0019\u0010p\u001a\u0005\u0018\u00010Ü\u00018PX\u0090\u0004¢\u0006\b\u001a\u0006\bÝ\u0001\u0010Þ\u0001¨\u0006ä\u0001"}, d2 = {"Landroidx/compose/runtime/Recomposer;", "Landroidx/compose/runtime/CompositionContext;", "effectCoroutineContext", "Lkotlin/coroutines/CoroutineContext;", "<init>", "(Lkotlin/coroutines/CoroutineContext;)V", ES6Iterator.VALUE_PROPERTY, "", "changeCount", "getChangeCount", "()J", "broadcastFrameClock", "Landroidx/compose/runtime/BroadcastFrameClock;", "stateLock", "", "Landroidx/compose/runtime/platform/SynchronizedObject;", "Ljava/lang/Object;", "runnerJob", "Lkotlinx/coroutines/Job;", "closeCause", "", "_knownCompositions", "", "Landroidx/compose/runtime/ControlledComposition;", "_knownCompositionsCache", "", "snapshotInvalidations", "Landroidx/collection/MutableScatterSet;", "compositionInvalidations", "Landroidx/compose/runtime/collection/MutableVector;", "compositionsAwaitingApply", "movableContentAwaitingInsert", "Landroidx/compose/runtime/MovableContentStateReference;", "movableContentRemoved", "Landroidx/compose/runtime/collection/MultiValueMap;", "Landroidx/compose/runtime/MovableContent;", "Landroidx/collection/MutableScatterMap;", "movableContentNestedStatesAvailable", "Landroidx/compose/runtime/NestedContentMap;", "movableContentStatesAvailable", "Landroidx/collection/MutableScatterMap;", "Landroidx/compose/runtime/MovableContentState;", "movableContentNestedExtractionsPending", "failedCompositions", "compositionsRemoved", "", "workContinuation", "Lkotlinx/coroutines/CancellableContinuation;", "", "concurrentCompositionsOutstanding", "", "isClosed", "", "errorState", "Landroidx/compose/runtime/Recomposer$RecomposerErrorState;", "frameClockPaused", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Landroidx/compose/runtime/Recomposer$State;", "pausedScopes", "Landroidx/compose/runtime/internal/SnapshotThreadLocal;", "Landroidx/compose/runtime/RecomposeScopeImpl;", "effectJob", "Lkotlinx/coroutines/CompletableJob;", "getEffectCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "recomposeCoroutineContext", "getRecomposeCoroutineContext$runtime", "hasBroadcastFrameClockAwaitersLocked", "getHasBroadcastFrameClockAwaitersLocked", "()Z", "hasBroadcastFrameClockAwaiters", "getHasBroadcastFrameClockAwaiters", "registrationObservers", "Landroidx/collection/MutableObjectList;", "Landroidx/compose/runtime/tooling/CompositionRegistrationObserver;", "getRegistrationObservers$annotations", "()V", "deriveStateLocked", "shouldKeepRecomposing", "getShouldKeepRecomposing", "state", "Lkotlinx/coroutines/flow/Flow;", "getState$annotations", "getState", "()Lkotlinx/coroutines/flow/Flow;", "currentState", "Lkotlinx/coroutines/flow/StateFlow;", "getCurrentState", "()Lkotlinx/coroutines/flow/StateFlow;", "recomposerInfo", "Landroidx/compose/runtime/Recomposer$RecomposerInfoImpl;", "asRecomposerInfo", "Landroidx/compose/runtime/RecomposerInfo;", "recordComposerModifications", "onEachInvalidComposition", "Lkotlin/Function1;", "registerRunnerJob", "callingJob", "runRecomposeAndApplyChanges", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processCompositionError", "e", "failedInitialComposition", "recoverable", "withTransparentSnapshot", "block", "Lkotlin/Function0;", "knownCompositions", "knownCompositionsLocked", "clearKnownCompositionsLocked", "removeKnownCompositionLocked", "composition", "addKnownCompositionLocked", "registerCompositionLocked", "unregisterCompositionLocked", "addCompositionRegistrationObserver", "Landroidx/compose/runtime/tooling/CompositionObserverHandle;", "observer", "addCompositionRegistrationObserver$runtime", "resetErrorState", "retryFailedCompositions", "recordFailedCompositionLocked", "runRecomposeConcurrentlyAndApplyChanges", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "runFrameLoop", "parentFrameClock", "Landroidx/compose/runtime/MonotonicFrameClock;", "frameSignal", "Landroidx/compose/runtime/ProduceFrameSignal;", "(Landroidx/compose/runtime/MonotonicFrameClock;Landroidx/compose/runtime/ProduceFrameSignal;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hasSchedulingWork", "getHasSchedulingWork", "awaitWorkAvailable", "recompositionRunner", "Lkotlin/Function3;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/ParameterName;", "name", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancel", "close", "join", "composeInitial", "content", "Landroidx/compose/runtime/Composable;", "composeInitial$runtime", "(Landroidx/compose/runtime/ControlledComposition;Lkotlin/jvm/functions/Function2;)V", "composeInitialPaused", "Landroidx/collection/ScatterSet;", "shouldPause", "Landroidx/compose/runtime/ShouldPauseCallback;", "composeInitialPaused$runtime", "(Landroidx/compose/runtime/ControlledComposition;Landroidx/compose/runtime/ShouldPauseCallback;Lkotlin/jvm/functions/Function2;)Landroidx/collection/ScatterSet;", "recomposePaused", "invalidScopes", "recomposePaused$runtime", "reportPausedScope", "scope", "reportPausedScope$runtime", "performInitialMovableContentInserts", "performRecompose", "modifiedValues", "performInsertValues", "references", "discardUnusedMovableContentState", "readObserverOf", "writeObserverOf", "composing", ExifInterface.GPS_DIRECTION_TRUE, "(Landroidx/compose/runtime/ControlledComposition;Landroidx/collection/MutableScatterSet;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "applyAndCheck", "snapshot", "Landroidx/compose/runtime/snapshots/MutableSnapshot;", "hasPendingWork", "getHasPendingWork", "hasFrameWorkLocked", "getHasFrameWorkLocked", "hasConcurrentFrameWorkLocked", "getHasConcurrentFrameWorkLocked", "awaitIdle", "pauseCompositionFrameClock", "resumeCompositionFrameClock", "compositeKeyHashCode", "Landroidx/compose/runtime/CompositeKeyHashCode;", "getCompositeKeyHashCode$runtime", "collectingCallByInformation", "getCollectingCallByInformation$runtime", "collectingParameterInformation", "getCollectingParameterInformation$runtime", "collectingSourceInformation", "getCollectingSourceInformation$runtime", "recordInspectionTable", "table", "Landroidx/compose/runtime/tooling/CompositionData;", "recordInspectionTable$runtime", "registerComposition", "registerComposition$runtime", "unregisterComposition", "unregisterComposition$runtime", "invalidate", "invalidate$runtime", "invalidateScope", "invalidateScope$runtime", "insertMovableContent", TypedValues.Custom.S_REFERENCE, "insertMovableContent$runtime", "deletedMovableContent", "deletedMovableContent$runtime", "movableContentStateReleased", "data", "applier", "Landroidx/compose/runtime/Applier;", "movableContentStateReleased$runtime", "reportRemovedComposition", "reportRemovedComposition$runtime", "movableContentStateResolve", "movableContentStateResolve$runtime", "Landroidx/compose/runtime/Composition;", "getComposition$runtime", "()Landroidx/compose/runtime/Composition;", "State", "RecomposerInfoImpl", "HotReloadable", "RecomposerErrorState", "Companion", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class Recomposer extends CompositionContext {
    private final List<ControlledComposition> _knownCompositions;
    private List<? extends ControlledComposition> _knownCompositionsCache;
    private final MutableStateFlow<State> _state;
    private final BroadcastFrameClock broadcastFrameClock;
    private long changeCount;
    private Throwable closeCause;
    private final MutableVector<ControlledComposition> compositionInvalidations;
    private final List<ControlledComposition> compositionsAwaitingApply;
    private Set<ControlledComposition> compositionsRemoved;
    private int concurrentCompositionsOutstanding;
    private final CoroutineContext effectCoroutineContext;
    private final CompletableJob effectJob;
    private RecomposerErrorState errorState;
    private List<ControlledComposition> failedCompositions;
    private boolean frameClockPaused;
    private boolean isClosed;
    private final List<MovableContentStateReference> movableContentAwaitingInsert;
    private final MutableScatterMap<Object, Object> movableContentNestedExtractionsPending;
    private final NestedContentMap movableContentNestedStatesAvailable;
    private final MutableScatterMap<Object, Object> movableContentRemoved;
    private final MutableScatterMap<MovableContentStateReference, MovableContentState> movableContentStatesAvailable;
    private final SnapshotThreadLocal<MutableScatterSet<RecomposeScopeImpl>> pausedScopes;
    private final RecomposerInfoImpl recomposerInfo;
    private MutableObjectList<CompositionRegistrationObserver> registrationObservers;
    private Job runnerJob;
    private MutableScatterSet<Object> snapshotInvalidations;
    private final Object stateLock;
    private CancellableContinuation<? super Unit> workContinuation;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final MutableStateFlow<PersistentSet<RecomposerInfoImpl>> _runningRecomposers = StateFlowKt.MutableStateFlow(ExtensionsKt.persistentSetOf());
    private static final AtomicReference<Boolean> _hotReloadEnabled = new AtomicReference<>(false);

    /* JADX INFO: compiled from: Recomposer.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Landroidx/compose/runtime/Recomposer$State;", "", "<init>", "(Ljava/lang/String;I)V", "ShutDown", "ShuttingDown", "Inactive", "InactivePendingWork", "Idle", "PendingWork", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public enum State {
        ShutDown,
        ShuttingDown,
        Inactive,
        InactivePendingWork,
        Idle,
        PendingWork;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<State> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$runFrameLoop$1, reason: invalid class name */
    /* JADX INFO: compiled from: Recomposer.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.runtime.Recomposer", f = "Recomposer.kt", i = {0, 0, 0, 0, 1, 1, 1, 1}, l = {AnalyticsListener.EVENT_AUDIO_CODEC_ERROR, 1036}, m = "runFrameLoop", n = {"parentFrameClock", "frameSignal", "toRecompose", "toApply", "parentFrameClock", "frameSignal", "toRecompose", "toApply"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Recomposer.this.runFrameLoop(null, null, this);
        }
    }

    private static /* synthetic */ void getRegistrationObservers$annotations() {
    }

    @Deprecated(message = "Replaced by currentState as a StateFlow", replaceWith = @ReplaceWith(expression = "currentState", imports = {}))
    public static /* synthetic */ void getState$annotations() {
    }

    @Override // androidx.compose.runtime.CompositionContext
    /* JADX INFO: renamed from: getCollectingParameterInformation$runtime */
    public boolean getCollectingParameterInformation() {
        return false;
    }

    @Override // androidx.compose.runtime.CompositionContext
    /* JADX INFO: renamed from: getCompositeKeyHashCode$runtime */
    public long getCompositeKeyHashCode() {
        return 1000L;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public Composition getComposition$runtime() {
        return null;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void recordInspectionTable$runtime(Set<CompositionData> table) {
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void registerComposition$runtime(ControlledComposition composition) {
    }

    public Recomposer(CoroutineContext coroutineContext) {
        BroadcastFrameClock broadcastFrameClock = new BroadcastFrameClock(new Function0() { // from class: androidx.compose.runtime.Recomposer$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Recomposer.broadcastFrameClock$lambda$2(this.f$0);
            }
        });
        this.broadcastFrameClock = broadcastFrameClock;
        this.stateLock = new Object();
        this._knownCompositions = new ArrayList();
        this.snapshotInvalidations = new MutableScatterSet<>(0, 1, null);
        this.compositionInvalidations = new MutableVector<>(new ControlledComposition[16], 0);
        this.compositionsAwaitingApply = new ArrayList();
        this.movableContentAwaitingInsert = new ArrayList();
        this.movableContentRemoved = MultiValueMap.m3533constructorimpl$default(null, 1, null);
        this.movableContentNestedStatesAvailable = new NestedContentMap();
        this.movableContentStatesAvailable = ScatterMapKt.mutableScatterMapOf();
        this.movableContentNestedExtractionsPending = MultiValueMap.m3533constructorimpl$default(null, 1, null);
        this._state = StateFlowKt.MutableStateFlow(State.Inactive);
        this.pausedScopes = new SnapshotThreadLocal<>();
        CompletableJob completableJobJob = JobKt.Job((Job) coroutineContext.get(Job.INSTANCE));
        completableJobJob.invokeOnCompletion(new Function1() { // from class: androidx.compose.runtime.Recomposer$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Recomposer.effectJob$lambda$10$lambda$9(this.f$0, (Throwable) obj);
            }
        });
        this.effectJob = completableJobJob;
        this.effectCoroutineContext = coroutineContext.plus(broadcastFrameClock).plus(completableJobJob);
        this.recomposerInfo = new RecomposerInfoImpl();
    }

    public final long getChangeCount() {
        return this.changeCount;
    }

    static final Unit broadcastFrameClock$lambda$2(Recomposer recomposer) {
        CancellableContinuation<Unit> cancellableContinuationDeriveStateLocked;
        synchronized (recomposer.stateLock) {
            cancellableContinuationDeriveStateLocked = recomposer.deriveStateLocked();
            if (recomposer._state.getValue().compareTo(State.ShuttingDown) <= 0) {
                throw ExceptionsKt.CancellationException("Recomposer shutdown; frame clock awaiter will never resume", recomposer.closeCause);
            }
        }
        if (cancellableContinuationDeriveStateLocked != null) {
            Result.Companion companion = Result.INSTANCE;
            cancellableContinuationDeriveStateLocked.resumeWith(Result.m8936constructorimpl(Unit.INSTANCE));
        }
        return Unit.INSTANCE;
    }

    static final Unit effectJob$lambda$10$lambda$9(final Recomposer recomposer, final Throwable th) {
        CancellableContinuation<? super Unit> cancellableContinuation;
        CancellableContinuation<? super Unit> cancellableContinuation2;
        CancellationException CancellationException = ExceptionsKt.CancellationException("Recomposer effect job completed", th);
        synchronized (recomposer.stateLock) {
            Job job = recomposer.runnerJob;
            cancellableContinuation = null;
            if (job != null) {
                recomposer._state.setValue(State.ShuttingDown);
                if (!recomposer.isClosed) {
                    job.cancel(CancellationException);
                } else {
                    cancellableContinuation2 = recomposer.workContinuation;
                    if (cancellableContinuation2 != null) {
                    }
                    recomposer.workContinuation = null;
                    job.invokeOnCompletion(new Function1() { // from class: androidx.compose.runtime.Recomposer$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return Recomposer.effectJob$lambda$10$lambda$9$lambda$8$lambda$7(this.f$0, th, (Throwable) obj);
                        }
                    });
                    cancellableContinuation = cancellableContinuation2;
                }
                cancellableContinuation2 = null;
                recomposer.workContinuation = null;
                job.invokeOnCompletion(new Function1() { // from class: androidx.compose.runtime.Recomposer$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Recomposer.effectJob$lambda$10$lambda$9$lambda$8$lambda$7(this.f$0, th, (Throwable) obj);
                    }
                });
                cancellableContinuation = cancellableContinuation2;
            } else {
                recomposer.closeCause = CancellationException;
                recomposer._state.setValue(State.ShutDown);
                Unit unit = Unit.INSTANCE;
            }
        }
        if (cancellableContinuation != null) {
            Result.Companion companion = Result.INSTANCE;
            cancellableContinuation.resumeWith(Result.m8936constructorimpl(Unit.INSTANCE));
        }
        return Unit.INSTANCE;
    }

    static final Unit effectJob$lambda$10$lambda$9$lambda$8$lambda$7(Recomposer recomposer, Throwable th, Throwable th2) {
        synchronized (recomposer.stateLock) {
            if (th == null) {
                th = null;
            } else if (th2 != null) {
                if (th2 instanceof CancellationException) {
                    th2 = null;
                }
                if (th2 != null) {
                    kotlin.ExceptionsKt.addSuppressed(th, th2);
                }
            }
            recomposer.closeCause = th;
            recomposer._state.setValue(State.ShutDown);
            Unit unit = Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public CoroutineContext getEffectCoroutineContext() {
        return this.effectCoroutineContext;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public CoroutineContext getRecomposeCoroutineContext$runtime() {
        return EmptyCoroutineContext.INSTANCE;
    }

    private final boolean getHasBroadcastFrameClockAwaitersLocked() {
        return !this.frameClockPaused && this.broadcastFrameClock.getHasAwaiters();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getHasBroadcastFrameClockAwaiters() {
        boolean hasBroadcastFrameClockAwaitersLocked;
        synchronized (this.stateLock) {
            hasBroadcastFrameClockAwaitersLocked = getHasBroadcastFrameClockAwaitersLocked();
        }
        return hasBroadcastFrameClockAwaitersLocked;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CancellableContinuation<Unit> deriveStateLocked() {
        State state;
        int i = 0;
        int i2 = 1;
        DefaultConstructorMarker defaultConstructorMarker = null;
        if (this._state.getValue().compareTo(State.ShuttingDown) <= 0) {
            clearKnownCompositionsLocked();
            this.snapshotInvalidations = new MutableScatterSet<>(i, i2, defaultConstructorMarker);
            this.compositionInvalidations.clear();
            this.compositionsAwaitingApply.clear();
            this.movableContentAwaitingInsert.clear();
            this.failedCompositions = null;
            CancellableContinuation<? super Unit> cancellableContinuation = this.workContinuation;
            if (cancellableContinuation != null) {
                CancellableContinuation.CC.cancel$default(cancellableContinuation, null, 1, null);
            }
            this.workContinuation = null;
            this.errorState = null;
            return null;
        }
        if (this.errorState != null) {
            state = State.Inactive;
        } else if (this.runnerJob == null) {
            this.snapshotInvalidations = new MutableScatterSet<>(i, i2, defaultConstructorMarker);
            this.compositionInvalidations.clear();
            state = getHasBroadcastFrameClockAwaitersLocked() ? State.InactivePendingWork : State.Inactive;
        } else if (this.compositionInvalidations.getSize() != 0 || this.snapshotInvalidations.isNotEmpty() || !this.compositionsAwaitingApply.isEmpty() || !this.movableContentAwaitingInsert.isEmpty() || this.concurrentCompositionsOutstanding > 0 || getHasBroadcastFrameClockAwaitersLocked() || MultiValueMap.m3541isNotEmptyimpl(this.movableContentRemoved)) {
            state = State.PendingWork;
        } else {
            state = State.Idle;
        }
        this._state.setValue(state);
        if (state != State.PendingWork) {
            return null;
        }
        CancellableContinuation cancellableContinuation2 = this.workContinuation;
        this.workContinuation = null;
        return cancellableContinuation2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getShouldKeepRecomposing() {
        boolean z;
        synchronized (this.stateLock) {
            z = this.isClosed;
        }
        if (!z) {
            return true;
        }
        Iterator<Job> it = this.effectJob.getChildren().iterator();
        while (it.hasNext()) {
            if (it.next().isActive()) {
                return true;
            }
        }
        return false;
    }

    public final Flow<State> getState() {
        return getCurrentState();
    }

    public final StateFlow<State> getCurrentState() {
        return this._state;
    }

    /* JADX INFO: compiled from: Recomposer.kt */
    @Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eJ\b\u0010 \u001a\u0004\u0018\u00010!J\u0006\u0010\"\u001a\u00020\u001aR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006#"}, d2 = {"Landroidx/compose/runtime/Recomposer$RecomposerInfoImpl;", "Landroidx/compose/runtime/RecomposerInfo;", "<init>", "(Landroidx/compose/runtime/Recomposer;)V", "state", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/compose/runtime/Recomposer$State;", "getState", "()Lkotlinx/coroutines/flow/Flow;", "hasPendingWork", "", "getHasPendingWork", "()Z", "changeCount", "", "getChangeCount", "()J", "currentError", "Landroidx/compose/runtime/RecomposerErrorInfo;", "getCurrentError", "()Landroidx/compose/runtime/RecomposerErrorInfo;", "observe", "Landroidx/compose/runtime/tooling/CompositionObserverHandle;", "observer", "Landroidx/compose/runtime/tooling/CompositionRegistrationObserver;", "invalidateGroupsWithKey", "", "key", "", "saveStateAndDisposeForHotReload", "", "Landroidx/compose/runtime/Recomposer$HotReloadable;", "resetErrorState", "Landroidx/compose/runtime/Recomposer$RecomposerErrorState;", "retryFailedCompositions", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private final class RecomposerInfoImpl implements RecomposerInfo {
        public RecomposerInfoImpl() {
        }

        @Override // androidx.compose.runtime.RecomposerInfo
        public Flow<State> getState() {
            return Recomposer.this.getCurrentState();
        }

        @Override // androidx.compose.runtime.RecomposerInfo
        public boolean getHasPendingWork() {
            return Recomposer.this.getHasPendingWork();
        }

        @Override // androidx.compose.runtime.RecomposerInfo
        public long getChangeCount() {
            return Recomposer.this.getChangeCount();
        }

        public final RecomposerErrorInfo getCurrentError() {
            RecomposerErrorState recomposerErrorState;
            Object obj = Recomposer.this.stateLock;
            Recomposer recomposer = Recomposer.this;
            synchronized (obj) {
                recomposerErrorState = recomposer.errorState;
            }
            return recomposerErrorState;
        }

        @Override // androidx.compose.runtime.RecomposerInfo
        public CompositionObserverHandle observe(CompositionRegistrationObserver observer) {
            return CompositionObserverKt.observe(Recomposer.this, observer);
        }

        public final void invalidateGroupsWithKey(int key) {
            List listKnownCompositions = Recomposer.this.knownCompositions();
            ArrayList arrayList = new ArrayList(listKnownCompositions.size());
            int size = listKnownCompositions.size();
            for (int i = 0; i < size; i++) {
                ControlledComposition controlledComposition = (ControlledComposition) listKnownCompositions.get(i);
                CompositionImpl compositionImpl = controlledComposition instanceof CompositionImpl ? (CompositionImpl) controlledComposition : null;
                if (compositionImpl != null) {
                    arrayList.add(compositionImpl);
                }
            }
            ArrayList arrayList2 = arrayList;
            int size2 = arrayList2.size();
            for (int i2 = 0; i2 < size2; i2++) {
                ((CompositionImpl) arrayList2.get(i2)).invalidateGroupsWithKey(key);
            }
        }

        public final List<HotReloadable> saveStateAndDisposeForHotReload() {
            List listKnownCompositions = Recomposer.this.knownCompositions();
            ArrayList arrayList = new ArrayList(listKnownCompositions.size());
            int size = listKnownCompositions.size();
            for (int i = 0; i < size; i++) {
                ControlledComposition controlledComposition = (ControlledComposition) listKnownCompositions.get(i);
                CompositionImpl compositionImpl = controlledComposition instanceof CompositionImpl ? (CompositionImpl) controlledComposition : null;
                if (compositionImpl != null) {
                    arrayList.add(compositionImpl);
                }
            }
            ArrayList arrayList2 = arrayList;
            ArrayList arrayList3 = new ArrayList(arrayList2.size());
            int size2 = arrayList2.size();
            for (int i2 = 0; i2 < size2; i2++) {
                HotReloadable hotReloadable = new HotReloadable((CompositionImpl) arrayList2.get(i2));
                hotReloadable.clearContent();
                arrayList3.add(hotReloadable);
            }
            return arrayList3;
        }

        public final RecomposerErrorState resetErrorState() {
            return Recomposer.this.resetErrorState();
        }

        public final void retryFailedCompositions() {
            Recomposer.this.retryFailedCompositions();
        }
    }

    /* JADX INFO: compiled from: Recomposer.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u000b\u001a\u00020\bJ\u0006\u0010\f\u001a\u00020\bJ\u0006\u0010\r\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\b\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\n¨\u0006\u000e"}, d2 = {"Landroidx/compose/runtime/Recomposer$HotReloadable;", "", "composition", "Landroidx/compose/runtime/CompositionImpl;", "<init>", "(Landroidx/compose/runtime/CompositionImpl;)V", "composable", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "Lkotlin/jvm/functions/Function2;", "clearContent", "resetContent", "recompose", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final class HotReloadable {
        private Function2<? super Composer, ? super Integer, Unit> composable;
        private final CompositionImpl composition;

        public HotReloadable(CompositionImpl compositionImpl) {
            this.composition = compositionImpl;
            this.composable = compositionImpl.getComposable();
        }

        public final void clearContent() {
            if (this.composition.getIsRoot()) {
                this.composition.setContent(ComposableSingletons$RecomposerKt.INSTANCE.m3384getLambda$1091980426$runtime());
            }
        }

        public final void resetContent() {
            this.composition.setComposable(this.composable);
        }

        public final void recompose() {
            if (this.composition.getIsRoot()) {
                this.composition.setContent(this.composable);
            }
        }
    }

    /* JADX INFO: compiled from: Recomposer.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/compose/runtime/Recomposer$RecomposerErrorState;", "Landroidx/compose/runtime/RecomposerErrorInfo;", "recoverable", "", "cause", "", "<init>", "(ZLjava/lang/Throwable;)V", "getRecoverable", "()Z", "getCause", "()Ljava/lang/Throwable;", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final class RecomposerErrorState implements RecomposerErrorInfo {
        private final Throwable cause;
        private final boolean recoverable;

        public RecomposerErrorState(boolean z, Throwable th) {
            this.recoverable = z;
            this.cause = th;
        }

        @Override // androidx.compose.runtime.RecomposerErrorInfo
        public boolean getRecoverable() {
            return this.recoverable;
        }

        @Override // androidx.compose.runtime.RecomposerErrorInfo
        public Throwable getCause() {
            return this.cause;
        }
    }

    public final RecomposerInfo asRecomposerInfo() {
        return this.recomposerInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean recordComposerModifications() {
        boolean hasFrameWorkLocked;
        CollectionsKt.emptyList();
        synchronized (this.stateLock) {
            if (this.snapshotInvalidations.isEmpty()) {
                return getHasFrameWorkLocked();
            }
            List<ControlledComposition> listKnownCompositionsLocked = knownCompositionsLocked();
            Set<? extends Object> setWrapIntoSet = ScatterSetWrapperKt.wrapIntoSet(this.snapshotInvalidations);
            int i = 0;
            this.snapshotInvalidations = new MutableScatterSet<>(i, 1, null);
            try {
                Recomposer recomposer = this;
                int size = listKnownCompositionsLocked.size();
                while (i < size) {
                    listKnownCompositionsLocked.get(i).recordModificationsOf(setWrapIntoSet);
                    if (this._state.getValue().compareTo(State.ShuttingDown) <= 0) {
                        break;
                    }
                    i++;
                }
                synchronized (this.stateLock) {
                    if (deriveStateLocked() != null) {
                        throw new IllegalStateException("called outside of runRecomposeAndApplyChanges".toString());
                    }
                    hasFrameWorkLocked = getHasFrameWorkLocked();
                }
                return hasFrameWorkLocked;
            } catch (Throwable th) {
                synchronized (this.stateLock) {
                    this.snapshotInvalidations.addAll(setWrapIntoSet);
                    throw th;
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void recordComposerModifications(Function1<? super ControlledComposition, Unit> onEachInvalidComposition) {
        MutableScatterSet mutableScatterSet;
        int i;
        synchronized (this.stateLock) {
            mutableScatterSet = this.snapshotInvalidations;
            i = 0;
            if (mutableScatterSet.isNotEmpty()) {
                this.snapshotInvalidations = new MutableScatterSet(i, 1, null);
            }
        }
        Set<? extends Object> setWrapIntoSet = ScatterSetWrapperKt.wrapIntoSet(mutableScatterSet);
        if (!setWrapIntoSet.isEmpty()) {
            List listKnownCompositionsLocked = knownCompositionsLocked();
            int size = listKnownCompositionsLocked.size();
            for (int i2 = 0; i2 < size; i2++) {
                ((ControlledComposition) listKnownCompositionsLocked.get(i2)).recordModificationsOf(setWrapIntoSet);
            }
        }
        MutableVector mutableVector = this.compositionInvalidations;
        T[] tArr = mutableVector.content;
        int size2 = mutableVector.getSize();
        while (i < size2) {
            onEachInvalidComposition.invoke(tArr[i]);
            i++;
        }
        this.compositionInvalidations.clear();
        synchronized (this.stateLock) {
            if (deriveStateLocked() != null) {
                throw new IllegalStateException("called outside of runRecomposeAndApplyChanges".toString());
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void registerRunnerJob(Job callingJob) {
        synchronized (this.stateLock) {
            Throwable th = this.closeCause;
            if (th != null) {
                throw th;
            }
            if (this._state.getValue().compareTo(State.ShuttingDown) <= 0) {
                throw new IllegalStateException("Recomposer shut down".toString());
            }
            if (this.runnerJob != null) {
                throw new IllegalStateException("Recomposer already running".toString());
            }
            this.runnerJob = callingJob;
            deriveStateLocked();
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$runRecomposeAndApplyChanges$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Recomposer.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "parentFrameClock", "Landroidx/compose/runtime/MonotonicFrameClock;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.runtime.Recomposer$runRecomposeAndApplyChanges$2", f = "Recomposer.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {587, 598}, m = "invokeSuspend", n = {"parentFrameClock", "toRecompose", "toInsert", "toApply", "toLateApply", "toComplete", "modifiedValues", "modifiedValuesSet", "alreadyComposed", "parentFrameClock", "toRecompose", "toInsert", "toApply", "toLateApply", "toComplete", "modifiedValues", "modifiedValuesSet", "alreadyComposed"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8"})
    static final class C05742 extends SuspendLambda implements Function3<CoroutineScope, MonotonicFrameClock, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        int label;

        C05742(Continuation<? super C05742> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, MonotonicFrameClock monotonicFrameClock, Continuation<? super Unit> continuation) {
            C05742 c05742 = Recomposer.this.new C05742(continuation);
            c05742.L$0 = monotonicFrameClock;
            return c05742.invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:14:0x00bd  */
        /* JADX WARN: Code duplicated, block: B:17:0x00dd  */
        /* JADX WARN: Code duplicated, block: B:20:0x00eb  */
        /* JADX WARN: Code duplicated, block: B:23:0x0110  */
        /* JADX WARN: Code duplicated, block: B:25:0x011e  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0110 -> B:24:0x0118). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x011e -> B:12:0x00b5). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r17) {
            /*
                Method dump skipped, instruction units count: 298
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.Recomposer.C05742.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX WARN: Code duplicated, block: B:19:0x0075 A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:20:0x0077 A[Catch: all -> 0x0109, LOOP:1: B:10:0x0043->B:20:0x0077, LOOP_END, TryCatch #0 {, blocks: (B:4:0x0009, B:6:0x0019, B:7:0x0028, B:10:0x0043, B:12:0x004f, B:14:0x0059, B:16:0x005f, B:17:0x006f, B:22:0x007c, B:25:0x008d, B:27:0x0097, B:29:0x00a1, B:31:0x00a7, B:32:0x00b1, B:35:0x00b9, B:36:0x00bc, B:39:0x00d0, B:41:0x00da, B:43:0x00e4, B:45:0x00ea, B:46:0x00f7, B:49:0x00ff, B:50:0x0102, B:20:0x0077), top: B:56:0x0009 }] */
        /* JADX WARN: Code duplicated, block: B:34:0x00b7 A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:35:0x00b9 A[Catch: all -> 0x0109, LOOP:3: B:25:0x008d->B:35:0x00b9, LOOP_END, TryCatch #0 {, blocks: (B:4:0x0009, B:6:0x0019, B:7:0x0028, B:10:0x0043, B:12:0x004f, B:14:0x0059, B:16:0x005f, B:17:0x006f, B:22:0x007c, B:25:0x008d, B:27:0x0097, B:29:0x00a1, B:31:0x00a7, B:32:0x00b1, B:35:0x00b9, B:36:0x00bc, B:39:0x00d0, B:41:0x00da, B:43:0x00e4, B:45:0x00ea, B:46:0x00f7, B:49:0x00ff, B:50:0x0102, B:20:0x0077), top: B:56:0x0009 }] */
        /* JADX WARN: Code duplicated, block: B:48:0x00fd A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:49:0x00ff A[Catch: all -> 0x0109, LOOP:5: B:39:0x00d0->B:49:0x00ff, LOOP_END, TryCatch #0 {, blocks: (B:4:0x0009, B:6:0x0019, B:7:0x0028, B:10:0x0043, B:12:0x004f, B:14:0x0059, B:16:0x005f, B:17:0x006f, B:22:0x007c, B:25:0x008d, B:27:0x0097, B:29:0x00a1, B:31:0x00a7, B:32:0x00b1, B:35:0x00b9, B:36:0x00bc, B:39:0x00d0, B:41:0x00da, B:43:0x00e4, B:45:0x00ea, B:46:0x00f7, B:49:0x00ff, B:50:0x0102, B:20:0x0077), top: B:56:0x0009 }] */
        /* JADX WARN: Code duplicated, block: B:60:0x007c A[EDGE_INSN: B:60:0x007c->B:22:0x007c BREAK  A[LOOP:1: B:10:0x0043->B:20:0x0077], SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:65:0x00bc A[EDGE_INSN: B:65:0x00bc->B:36:0x00bc BREAK  A[LOOP:3: B:25:0x008d->B:35:0x00b9], SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:69:0x0102 A[EDGE_INSN: B:69:0x0102->B:50:0x0102 BREAK  A[LOOP:5: B:39:0x00d0->B:49:0x00ff], SYNTHETIC] */
        private static final void invokeSuspend$clearRecompositionState(Recomposer recomposer, List<ControlledComposition> list, List<MovableContentStateReference> list2, List<ControlledComposition> list3, MutableScatterSet<ControlledComposition> mutableScatterSet, MutableScatterSet<ControlledComposition> mutableScatterSet2, MutableScatterSet<Object> mutableScatterSet3, MutableScatterSet<ControlledComposition> mutableScatterSet4) {
            long j;
            synchronized (recomposer.stateLock) {
                list.clear();
                list2.clear();
                int size = list3.size();
                for (int i = 0; i < size; i++) {
                    ControlledComposition controlledComposition = list3.get(i);
                    controlledComposition.abandonChanges();
                    recomposer.recordFailedCompositionLocked(controlledComposition);
                }
                list3.clear();
                MutableScatterSet<ControlledComposition> mutableScatterSet5 = mutableScatterSet;
                Object[] objArr = mutableScatterSet5.elements;
                long[] jArr = mutableScatterSet5.metadata;
                int length = jArr.length - 2;
                if (length >= 0) {
                    int i2 = 0;
                    while (true) {
                        long j2 = jArr[i2];
                        j = 255;
                        if ((((~j2) << 7) & j2 & (-9187201950435737472L)) == -9187201950435737472L) {
                            if (i2 != length) {
                                break;
                                break;
                            }
                            i2++;
                        } else {
                            int i3 = 8 - ((~(i2 - length)) >>> 31);
                            for (int i4 = 0; i4 < i3; i4++) {
                                if ((j2 & 255) < 128) {
                                    ControlledComposition controlledComposition2 = (ControlledComposition) objArr[(i2 << 3) + i4];
                                    controlledComposition2.abandonChanges();
                                    recomposer.recordFailedCompositionLocked(controlledComposition2);
                                }
                                j2 >>= 8;
                            }
                            if (i3 != 8) {
                                break;
                            } else if (i2 != length) {
                                break;
                            } else {
                                i2++;
                            }
                        }
                    }
                } else {
                    j = 255;
                }
                mutableScatterSet.clear();
                MutableScatterSet<ControlledComposition> mutableScatterSet6 = mutableScatterSet2;
                Object[] objArr2 = mutableScatterSet6.elements;
                long[] jArr2 = mutableScatterSet6.metadata;
                int length2 = jArr2.length - 2;
                if (length2 >= 0) {
                    int i5 = 0;
                    while (true) {
                        long j3 = jArr2[i5];
                        if ((((~j3) << 7) & j3 & (-9187201950435737472L)) == -9187201950435737472L) {
                            if (i5 != length2) {
                                break;
                                break;
                            }
                            i5++;
                        } else {
                            int i6 = 8 - ((~(i5 - length2)) >>> 31);
                            for (int i7 = 0; i7 < i6; i7++) {
                                if ((j3 & j) < 128) {
                                    ((ControlledComposition) objArr2[(i5 << 3) + i7]).changesApplied();
                                }
                                j3 >>= 8;
                            }
                            if (i6 != 8) {
                                break;
                            } else if (i5 != length2) {
                                break;
                            } else {
                                i5++;
                            }
                        }
                    }
                }
                mutableScatterSet2.clear();
                mutableScatterSet3.clear();
                MutableScatterSet<ControlledComposition> mutableScatterSet7 = mutableScatterSet4;
                Object[] objArr3 = mutableScatterSet7.elements;
                long[] jArr3 = mutableScatterSet7.metadata;
                int length3 = jArr3.length - 2;
                if (length3 >= 0) {
                    int i8 = 0;
                    while (true) {
                        long j4 = jArr3[i8];
                        if ((((~j4) << 7) & j4 & (-9187201950435737472L)) == -9187201950435737472L) {
                            if (i8 != length3) {
                                break;
                                break;
                            }
                            i8++;
                        } else {
                            int i9 = 8 - ((~(i8 - length3)) >>> 31);
                            for (int i10 = 0; i10 < i9; i10++) {
                                if ((j4 & j) < 128) {
                                    ControlledComposition controlledComposition3 = (ControlledComposition) objArr3[(i8 << 3) + i10];
                                    controlledComposition3.abandonChanges();
                                    recomposer.recordFailedCompositionLocked(controlledComposition3);
                                }
                                j4 >>= 8;
                            }
                            if (i9 != 8) {
                                break;
                            } else if (i8 != length3) {
                                break;
                            } else {
                                i8++;
                            }
                        }
                    }
                }
                mutableScatterSet4.clear();
                Unit unit = Unit.INSTANCE;
            }
        }

        private static final void invokeSuspend$fillToInsert(List<MovableContentStateReference> list, Recomposer recomposer) {
            list.clear();
            synchronized (recomposer.stateLock) {
                List list2 = recomposer.movableContentAwaitingInsert;
                int size = list2.size();
                for (int i = 0; i < size; i++) {
                    list.add((MovableContentStateReference) list2.get(i));
                }
                recomposer.movableContentAwaitingInsert.clear();
                Unit unit = Unit.INSTANCE;
            }
        }

        /* JADX WARN: Code duplicated, block: B:235:0x01f6 A[EDGE_INSN: B:235:0x01f6->B:99:0x01f6 BREAK  A[LOOP:4: B:88:0x01c6->B:98:0x01f3], SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:240:0x0177 A[EDGE_INSN: B:240:0x0177->B:69:0x0177 BREAK  A[LOOP:6: B:57:0x0142->B:67:0x0172], SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:66:0x0170 A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:67:0x0172 A[LOOP:6: B:57:0x0142->B:67:0x0172, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:97:0x01f1 A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:98:0x01f3 A[LOOP:4: B:88:0x01c6->B:98:0x01f3, LOOP_END] */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r14v3, types: [T[]] */
        /* JADX WARN: Type inference failed for: r2v5, types: [T[], java.lang.Object[]] */
        /* JADX WARN: Type inference failed for: r3v19 */
        /* JADX WARN: Type inference failed for: r3v20, types: [int] */
        /* JADX WARN: Type inference failed for: r3v21 */
        /* JADX WARN: Type inference failed for: r3v22, types: [int] */
        /* JADX WARN: Type inference failed for: r3v25 */
        /* JADX WARN: Type inference failed for: r3v26 */
        /* JADX WARN: Type inference failed for: r4v15, types: [int] */
        /* JADX WARN: Type inference failed for: r4v21 */
        /* JADX WARN: Type inference failed for: r4v22 */
        static final Unit invokeSuspend$lambda$22(Recomposer recomposer, MutableScatterSet mutableScatterSet, MutableScatterSet mutableScatterSet2, List list, List list2, MutableScatterSet mutableScatterSet3, List list3, MutableScatterSet mutableScatterSet4, Set set, long j) {
            boolean z;
            char c;
            long j2;
            long j3;
            Recomposer recomposer2 = recomposer;
            if (recomposer2.getHasBroadcastFrameClockAwaiters()) {
                Object objBeginSection = Trace.INSTANCE.beginSection("Recomposer:animation");
                try {
                    recomposer2.broadcastFrameClock.sendFrame(j);
                    Snapshot.INSTANCE.sendApplyNotifications();
                    Unit unit = Unit.INSTANCE;
                    Trace.INSTANCE.endSection(objBeginSection);
                } catch (Throwable th) {
                    Trace.INSTANCE.endSection(objBeginSection);
                    throw th;
                }
            }
            Object objBeginSection2 = Trace.INSTANCE.beginSection("Recomposer:recompose");
            try {
                recomposer2.recordComposerModifications();
                synchronized (recomposer2.stateLock) {
                    MutableVector mutableVector = recomposer2.compositionInvalidations;
                    Object[] objArr = mutableVector.content;
                    int size = mutableVector.getSize();
                    z = false;
                    for (int i = 0; i < size; i++) {
                        list.add((ControlledComposition) objArr[i]);
                    }
                    recomposer2.compositionInvalidations.clear();
                    Unit unit2 = Unit.INSTANCE;
                }
                mutableScatterSet.clear();
                mutableScatterSet2.clear();
                while (true) {
                    if (list.isEmpty() && list2.isEmpty()) {
                        break;
                    }
                    try {
                        int size2 = list.size();
                        for (int i2 = 0; i2 < size2; i2++) {
                            ControlledComposition controlledComposition = (ControlledComposition) list.get(i2);
                            ControlledComposition controlledCompositionPerformRecompose = recomposer2.performRecompose(controlledComposition, mutableScatterSet);
                            if (controlledCompositionPerformRecompose != null) {
                                list3.add(controlledCompositionPerformRecompose);
                                Unit unit3 = Unit.INSTANCE;
                                Unit unit4 = Unit.INSTANCE;
                            }
                            mutableScatterSet2.add(controlledComposition);
                        }
                        list.clear();
                        if (mutableScatterSet.isNotEmpty() || recomposer2.compositionInvalidations.getSize() != 0) {
                            synchronized (recomposer2.stateLock) {
                                List listKnownCompositionsLocked = recomposer2.knownCompositionsLocked();
                                int size3 = listKnownCompositionsLocked.size();
                                for (int i3 = 0; i3 < size3; i3++) {
                                    ControlledComposition controlledComposition2 = (ControlledComposition) listKnownCompositionsLocked.get(i3);
                                    if (!mutableScatterSet2.contains(controlledComposition2) && controlledComposition2.observesAnyOf(set)) {
                                        list.add(controlledComposition2);
                                    }
                                }
                                MutableVector mutableVector2 = recomposer2.compositionInvalidations;
                                int size4 = mutableVector2.getSize();
                                int i4 = 0;
                                for (int i5 = 0; i5 < size4; i5++) {
                                    ControlledComposition controlledComposition3 = (ControlledComposition) mutableVector2.content[i5];
                                    if (!mutableScatterSet2.contains(controlledComposition3) && !list.contains(controlledComposition3)) {
                                        list.add(controlledComposition3);
                                        i4++;
                                    } else if (i4 > 0) {
                                        mutableVector2.content[i5 - i4] = mutableVector2.content[i5];
                                    }
                                }
                                int i6 = size4 - i4;
                                ArraysKt.fill((Object[]) mutableVector2.content, (Object) null, i6, size4);
                                mutableVector2.setSize(i6);
                                Unit unit5 = Unit.INSTANCE;
                            }
                        }
                        if (list.isEmpty()) {
                            try {
                                invokeSuspend$fillToInsert(list2, recomposer2);
                                while (!list2.isEmpty()) {
                                    mutableScatterSet3.plusAssign((Iterable) recomposer2.performInsertValues(list2, mutableScatterSet));
                                    invokeSuspend$fillToInsert(list2, recomposer2);
                                }
                            } catch (Throwable th2) {
                                Recomposer.processCompositionError$default(recomposer2, th2, null, true, 2, null);
                                invokeSuspend$clearRecompositionState(recomposer, list, list2, list3, mutableScatterSet3, mutableScatterSet4, mutableScatterSet, mutableScatterSet2);
                                Unit unit6 = Unit.INSTANCE;
                                Trace.INSTANCE.endSection(objBeginSection2);
                                return unit6;
                            }
                        } else {
                            recomposer2 = recomposer;
                        }
                        z = false;
                    } catch (Throwable th3) {
                        try {
                            Recomposer.processCompositionError$default(recomposer, th3, null, true, 2, null);
                            invokeSuspend$clearRecompositionState(recomposer, list, list2, list3, mutableScatterSet3, mutableScatterSet4, mutableScatterSet, mutableScatterSet2);
                            Unit unit7 = Unit.INSTANCE;
                            list.clear();
                            Trace.INSTANCE.endSection(objBeginSection2);
                            return unit7;
                        } catch (Throwable th4) {
                            list.clear();
                            throw th4;
                        }
                    }
                    Trace.INSTANCE.endSection(objBeginSection2);
                    throw th;
                }
                Snapshot current = Snapshot.INSTANCE.getCurrent();
                TransparentObserverSnapshot transparentObserverMutableSnapshot = current instanceof MutableSnapshot ? new TransparentObserverMutableSnapshot((MutableSnapshot) current, null, null, true, false) : new TransparentObserverSnapshot(current, null, true, z);
                try {
                    Snapshot snapshotMakeCurrent = transparentObserverMutableSnapshot.makeCurrent();
                    try {
                        if (!list3.isEmpty()) {
                            recomposer2.changeCount = recomposer2.getChangeCount() + 1;
                            try {
                                int size5 = list3.size();
                                for (?? r3 = z; r3 < size5; r3++) {
                                    mutableScatterSet4.add((ControlledComposition) list3.get(r3));
                                }
                                int size6 = list3.size();
                                for (?? r4 = z; r4 < size6; r4++) {
                                    ((ControlledComposition) list3.get(r4)).applyChanges();
                                }
                                list3.clear();
                            } catch (Throwable th5) {
                                try {
                                    Recomposer.processCompositionError$default(recomposer2, th5, null, false, 6, null);
                                    invokeSuspend$clearRecompositionState(recomposer, list, list2, list3, mutableScatterSet3, mutableScatterSet4, mutableScatterSet, mutableScatterSet2);
                                    Unit unit8 = Unit.INSTANCE;
                                    list3.clear();
                                    transparentObserverMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                                    transparentObserverMutableSnapshot.dispose();
                                    Trace.INSTANCE.endSection(objBeginSection2);
                                    return unit8;
                                } catch (Throwable th6) {
                                    list3.clear();
                                    throw th6;
                                }
                            }
                        }
                        if (mutableScatterSet3.isNotEmpty()) {
                            try {
                                mutableScatterSet4.plusAssign((ScatterSet) mutableScatterSet3);
                                MutableScatterSet mutableScatterSet5 = mutableScatterSet3;
                                Object[] objArr2 = mutableScatterSet5.elements;
                                long[] jArr = mutableScatterSet5.metadata;
                                c = 7;
                                int length = jArr.length - 2;
                                j2 = 128;
                                ?? r5 = z;
                                if (length >= 0) {
                                    while (true) {
                                        long j4 = jArr[r5];
                                        j3 = 255;
                                        if ((((~j4) << 7) & j4 & (-9187201950435737472L)) == -9187201950435737472L) {
                                            if (r5 != length) {
                                                break;
                                                break;
                                            }
                                            r5++;
                                        } else {
                                            int i7 = 8 - ((~(r5 - length)) >>> 31);
                                            for (int i8 = 0; i8 < i7; i8++) {
                                                if ((j4 & 255) < 128) {
                                                    ((ControlledComposition) objArr2[(r5 << 3) + i8]).applyLateChanges();
                                                }
                                                j4 >>= 8;
                                            }
                                            if (i7 != 8) {
                                                break;
                                            }
                                            if (r5 != length) {
                                                break;
                                            }
                                            r5++;
                                        }
                                    }
                                } else {
                                    j3 = 255;
                                }
                                mutableScatterSet3.clear();
                            } catch (Throwable th7) {
                                try {
                                    Recomposer.processCompositionError$default(recomposer, th7, null, false, 6, null);
                                    invokeSuspend$clearRecompositionState(recomposer, list, list2, list3, mutableScatterSet3, mutableScatterSet4, mutableScatterSet, mutableScatterSet2);
                                    Unit unit9 = Unit.INSTANCE;
                                    mutableScatterSet3.clear();
                                    transparentObserverMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                                    transparentObserverMutableSnapshot.dispose();
                                    Trace.INSTANCE.endSection(objBeginSection2);
                                    return unit9;
                                } catch (Throwable th8) {
                                    mutableScatterSet3.clear();
                                    throw th8;
                                }
                            }
                        } else {
                            c = 7;
                            j2 = 128;
                            j3 = 255;
                        }
                        if (mutableScatterSet4.isNotEmpty()) {
                            try {
                                MutableScatterSet mutableScatterSet6 = mutableScatterSet4;
                                Object[] objArr3 = mutableScatterSet6.elements;
                                long[] jArr2 = mutableScatterSet6.metadata;
                                int length2 = jArr2.length - 2;
                                if (length2 >= 0) {
                                    int i9 = 0;
                                    while (true) {
                                        long j5 = jArr2[i9];
                                        if ((((~j5) << c) & j5 & (-9187201950435737472L)) == -9187201950435737472L) {
                                            if (i9 != length2) {
                                                break;
                                                break;
                                            }
                                            i9++;
                                        } else {
                                            int i10 = 8 - ((~(i9 - length2)) >>> 31);
                                            for (int i11 = 0; i11 < i10; i11++) {
                                                if ((j5 & j3) < j2) {
                                                    ((ControlledComposition) objArr3[(i9 << 3) + i11]).changesApplied();
                                                }
                                                j5 >>= 8;
                                            }
                                            if (i10 != 8) {
                                                break;
                                            }
                                            if (i9 != length2) {
                                                break;
                                            }
                                            i9++;
                                        }
                                    }
                                }
                                mutableScatterSet4.clear();
                            } catch (Throwable th9) {
                                try {
                                    Recomposer.processCompositionError$default(recomposer, th9, null, false, 6, null);
                                    invokeSuspend$clearRecompositionState(recomposer, list, list2, list3, mutableScatterSet3, mutableScatterSet4, mutableScatterSet, mutableScatterSet2);
                                    Unit unit10 = Unit.INSTANCE;
                                    mutableScatterSet4.clear();
                                    transparentObserverMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                                    transparentObserverMutableSnapshot.dispose();
                                    Trace.INSTANCE.endSection(objBeginSection2);
                                    return unit10;
                                } catch (Throwable th10) {
                                    mutableScatterSet4.clear();
                                    throw th10;
                                }
                            }
                        }
                        Unit unit11 = Unit.INSTANCE;
                        transparentObserverMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                        transparentObserverMutableSnapshot.dispose();
                        synchronized (recomposer.stateLock) {
                            recomposer.deriveStateLocked();
                        }
                        Snapshot.INSTANCE.notifyObjectsInitialized();
                        mutableScatterSet2.clear();
                        mutableScatterSet.clear();
                        recomposer.compositionsRemoved = null;
                        Unit unit12 = Unit.INSTANCE;
                        Trace.INSTANCE.endSection(objBeginSection2);
                        return Unit.INSTANCE;
                    } catch (Throwable th11) {
                        transparentObserverMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                        throw th11;
                    }
                } catch (Throwable th12) {
                    transparentObserverMutableSnapshot.dispose();
                    throw th12;
                }
            } catch (Throwable th13) {
                Trace.INSTANCE.endSection(objBeginSection2);
                throw th13;
            }
        }
    }

    public final Object runRecomposeAndApplyChanges(Continuation<? super Unit> continuation) {
        Object objRecompositionRunner = recompositionRunner(new C05742(null), continuation);
        return objRecompositionRunner == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objRecompositionRunner : Unit.INSTANCE;
    }

    static /* synthetic */ void processCompositionError$default(Recomposer recomposer, Throwable th, ControlledComposition controlledComposition, boolean z, int i, Object obj) throws Throwable {
        if ((i & 2) != 0) {
            controlledComposition = null;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        recomposer.processCompositionError(th, controlledComposition, z);
    }

    private final void processCompositionError(Throwable e, ControlledComposition failedInitialComposition, boolean recoverable) throws Throwable {
        int i = 0;
        if (_hotReloadEnabled.get().booleanValue() && !(e instanceof ComposeRuntimeError)) {
            synchronized (this.stateLock) {
                Utils_androidKt.logError("Error was captured in composition while live edit was enabled.", e);
                this.compositionsAwaitingApply.clear();
                this.compositionInvalidations.clear();
                this.snapshotInvalidations = new MutableScatterSet<>(i, 1, null);
                this.movableContentAwaitingInsert.clear();
                MultiValueMap.m3531clearimpl(this.movableContentRemoved);
                this.movableContentStatesAvailable.clear();
                this.errorState = new RecomposerErrorState(recoverable, e);
                if (failedInitialComposition != null) {
                    recordFailedCompositionLocked(failedInitialComposition);
                }
                deriveStateLocked();
            }
            return;
        }
        synchronized (this.stateLock) {
            RecomposerErrorState recomposerErrorState = this.errorState;
            if (recomposerErrorState == null) {
                this.errorState = new RecomposerErrorState(false, e);
                Unit unit = Unit.INSTANCE;
            } else {
                throw recomposerErrorState.getCause();
            }
        }
        throw e;
    }

    private final void withTransparentSnapshot(Function0<Unit> block) {
        TransparentObserverSnapshot transparentObserverSnapshot;
        Snapshot current = Snapshot.INSTANCE.getCurrent();
        if (current instanceof MutableSnapshot) {
            transparentObserverSnapshot = new TransparentObserverMutableSnapshot((MutableSnapshot) current, null, null, true, false);
        } else {
            transparentObserverSnapshot = new TransparentObserverSnapshot(current, null, true, false);
        }
        try {
            Snapshot snapshotMakeCurrent = transparentObserverSnapshot.makeCurrent();
            try {
                block.invoke();
                transparentObserverSnapshot.restoreCurrent(snapshotMakeCurrent);
                transparentObserverSnapshot.dispose();
            } catch (Throwable th) {
                transparentObserverSnapshot.restoreCurrent(snapshotMakeCurrent);
                throw th;
            }
        } catch (Throwable th2) {
            transparentObserverSnapshot.dispose();
            throw th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<ControlledComposition> knownCompositions() {
        List<ControlledComposition> listKnownCompositionsLocked;
        synchronized (this.stateLock) {
            listKnownCompositionsLocked = knownCompositionsLocked();
        }
        return listKnownCompositionsLocked;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<ControlledComposition> knownCompositionsLocked() {
        List list = this._knownCompositionsCache;
        if (list != null) {
            return list;
        }
        List<ControlledComposition> list2 = this._knownCompositions;
        ArrayList arrayListEmptyList = list2.isEmpty() ? CollectionsKt.emptyList() : new ArrayList(list2);
        this._knownCompositionsCache = arrayListEmptyList;
        return arrayListEmptyList;
    }

    private final void clearKnownCompositionsLocked() {
        Iterator<T> it = knownCompositionsLocked().iterator();
        while (it.hasNext()) {
            unregisterCompositionLocked((ControlledComposition) it.next());
        }
        this._knownCompositions.clear();
        this._knownCompositionsCache = CollectionsKt.emptyList();
    }

    private final void removeKnownCompositionLocked(ControlledComposition composition) {
        if (this._knownCompositions.remove(composition)) {
            this._knownCompositionsCache = null;
            unregisterCompositionLocked(composition);
        }
    }

    private final void addKnownCompositionLocked(ControlledComposition composition) {
        this._knownCompositions.add(composition);
        this._knownCompositionsCache = null;
    }

    private final void registerCompositionLocked(ControlledComposition composition) {
        MutableObjectList<CompositionRegistrationObserver> mutableObjectList = this.registrationObservers;
        if (mutableObjectList != null) {
            MutableObjectList<CompositionRegistrationObserver> mutableObjectList2 = mutableObjectList;
            Object[] objArr = mutableObjectList2.content;
            int i = mutableObjectList2._size;
            for (int i2 = 0; i2 < i; i2++) {
                CompositionRegistrationObserver compositionRegistrationObserver = (CompositionRegistrationObserver) objArr[i2];
                if (composition instanceof ObservableComposition) {
                    compositionRegistrationObserver.onCompositionRegistered((ObservableComposition) composition);
                }
            }
        }
    }

    private final void unregisterCompositionLocked(ControlledComposition composition) {
        MutableObjectList<CompositionRegistrationObserver> mutableObjectList = this.registrationObservers;
        if (mutableObjectList != null) {
            MutableObjectList<CompositionRegistrationObserver> mutableObjectList2 = mutableObjectList;
            Object[] objArr = mutableObjectList2.content;
            int i = mutableObjectList2._size;
            for (int i2 = 0; i2 < i; i2++) {
                CompositionRegistrationObserver compositionRegistrationObserver = (CompositionRegistrationObserver) objArr[i2];
                if (composition instanceof ObservableComposition) {
                    compositionRegistrationObserver.onCompositionUnregistered((ObservableComposition) composition);
                }
            }
        }
    }

    public final CompositionObserverHandle addCompositionRegistrationObserver$runtime(final CompositionRegistrationObserver observer) {
        synchronized (this.stateLock) {
            MutableObjectList<CompositionRegistrationObserver> mutableObjectList = this.registrationObservers;
            int i = 0;
            if (mutableObjectList == null) {
                mutableObjectList = new MutableObjectList<>(i, 1, null);
                this.registrationObservers = mutableObjectList;
            }
            mutableObjectList.add(observer);
            List<ControlledComposition> list = this._knownCompositions;
            int size = list.size();
            while (i < size) {
                ControlledComposition controlledComposition = list.get(i);
                if (controlledComposition instanceof ObservableComposition) {
                    observer.onCompositionRegistered((ObservableComposition) controlledComposition);
                }
                i++;
            }
            Unit unit = Unit.INSTANCE;
        }
        return new CompositionObserverHandle() { // from class: androidx.compose.runtime.Recomposer$addCompositionRegistrationObserver$2
            @Override // androidx.compose.runtime.tooling.CompositionObserverHandle
            public void dispose() {
                Object obj = this.this$0.stateLock;
                Recomposer recomposer = this.this$0;
                CompositionRegistrationObserver compositionRegistrationObserver = observer;
                synchronized (obj) {
                    MutableObjectList mutableObjectList2 = recomposer.registrationObservers;
                    if (mutableObjectList2 != null) {
                        Boolean.valueOf(mutableObjectList2.remove(compositionRegistrationObserver));
                    }
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final RecomposerErrorState resetErrorState() {
        RecomposerErrorState recomposerErrorState;
        synchronized (this.stateLock) {
            recomposerErrorState = this.errorState;
            if (recomposerErrorState != null) {
                this.errorState = null;
                deriveStateLocked();
            }
        }
        return recomposerErrorState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void retryFailedCompositions() {
        List<ControlledComposition> list;
        int i;
        synchronized (this.stateLock) {
            list = this.failedCompositions;
            this.failedCompositions = null;
        }
        if (list == null) {
            return;
        }
        while (true) {
            i = 0;
            try {
                if (list.isEmpty()) {
                    break;
                }
                ControlledComposition controlledComposition = (ControlledComposition) CollectionsKt.removeLast(list);
                if (controlledComposition instanceof CompositionImpl) {
                    ((CompositionImpl) controlledComposition).invalidateAll();
                    ((CompositionImpl) controlledComposition).setContent(((CompositionImpl) controlledComposition).getComposable());
                    if (this.errorState != null) {
                        break;
                    }
                }
            } catch (Throwable th) {
                if (!list.isEmpty()) {
                    synchronized (this.stateLock) {
                        int size = list.size();
                        while (i < size) {
                            recordFailedCompositionLocked(list.get(i));
                            i++;
                        }
                        Unit unit = Unit.INSTANCE;
                    }
                }
                throw th;
            }
        }
        if (list.isEmpty()) {
            return;
        }
        synchronized (this.stateLock) {
            int size2 = list.size();
            while (i < size2) {
                recordFailedCompositionLocked(list.get(i));
                i++;
            }
            Unit unit2 = Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void recordFailedCompositionLocked(ControlledComposition composition) {
        ArrayList arrayList = this.failedCompositions;
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.failedCompositions = arrayList;
        }
        if (!arrayList.contains(composition)) {
            arrayList.add(composition);
        }
        removeKnownCompositionLocked(composition);
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$runRecomposeConcurrentlyAndApplyChanges$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Recomposer.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "parentFrameClock", "Landroidx/compose/runtime/MonotonicFrameClock;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.runtime.Recomposer$runRecomposeConcurrentlyAndApplyChanges$2", f = "Recomposer.kt", i = {0, 0, 0, 1}, l = {DescriptorProtos.Edition.EDITION_PROTO2_VALUE, 1018, 1019}, m = "invokeSuspend", n = {"recomposeCoroutineScope", "frameSignal", "frameLoop", "frameLoop"}, s = {"L$0", "L$1", "L$2", "L$0"})
    static final class C05752 extends SuspendLambda implements Function3<CoroutineScope, MonotonicFrameClock, Continuation<? super Unit>, Object> {
        final /* synthetic */ CoroutineContext $recomposeCoroutineContext;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        Object L$2;
        int label;
        final /* synthetic */ Recomposer this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05752(CoroutineContext coroutineContext, Recomposer recomposer, Continuation<? super C05752> continuation) {
            super(3, continuation);
            this.$recomposeCoroutineContext = coroutineContext;
            this.this$0 = recomposer;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, MonotonicFrameClock monotonicFrameClock, Continuation<? super Unit> continuation) {
            C05752 c05752 = new C05752(this.$recomposeCoroutineContext, this.this$0, continuation);
            c05752.L$0 = coroutineScope;
            c05752.L$1 = monotonicFrameClock;
            return c05752.invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:22:0x00b5  */
        /* JADX WARN: Code duplicated, block: B:74:0x019e  */
        /* JADX WARN: Code duplicated, block: B:77:0x01b8 A[PHI: r2
          0x01b8: PHI (r2v7 kotlinx.coroutines.Job) = (r2v5 kotlinx.coroutines.Job), (r2v11 kotlinx.coroutines.Job) binds: [B:75:0x01b5, B:10:0x0022] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x00c6, code lost:
        
            if (r18.this$0.awaitWorkAvailable(r18) == r1) goto L79;
         */
        /* JADX WARN: Code restructure failed: missing block: B:78:0x01c3, code lost:
        
            if (kotlinx.coroutines.JobKt.cancelAndJoin(r2, r18) == r1) goto L79;
         */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x00c6 -> B:25:0x00ca). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r19) {
            /*
                Method dump skipped, instruction units count: 457
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.Recomposer.C05752.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final Object runRecomposeConcurrentlyAndApplyChanges(CoroutineContext coroutineContext, Continuation<? super Unit> continuation) {
        Object objRecompositionRunner = recompositionRunner(new C05752(coroutineContext, this, null), continuation);
        return objRecompositionRunner == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objRecompositionRunner : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:21:0x0081  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0098, code lost:
    
        if (r5.withFrameNanos(r9, r0) == r1) goto L24;
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0098 -> B:13:0x003c). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object runFrameLoop(androidx.compose.runtime.MonotonicFrameClock r7, androidx.compose.runtime.ProduceFrameSignal r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof androidx.compose.runtime.Recomposer.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r9
            androidx.compose.runtime.Recomposer$runFrameLoop$1 r0 = (androidx.compose.runtime.Recomposer.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            androidx.compose.runtime.Recomposer$runFrameLoop$1 r0 = new androidx.compose.runtime.Recomposer$runFrameLoop$1
            r0.<init>(r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L5d
            if (r2 == r4) goto L49
            if (r2 != r3) goto L41
            java.lang.Object r7 = r0.L$3
            java.util.List r7 = (java.util.List) r7
            java.lang.Object r8 = r0.L$2
            java.util.List r8 = (java.util.List) r8
            java.lang.Object r2 = r0.L$1
            androidx.compose.runtime.ProduceFrameSignal r2 = (androidx.compose.runtime.ProduceFrameSignal) r2
            java.lang.Object r5 = r0.L$0
            androidx.compose.runtime.MonotonicFrameClock r5 = (androidx.compose.runtime.MonotonicFrameClock) r5
            kotlin.ResultKt.throwOnFailure(r9)
        L3c:
            r9 = r8
            r8 = r2
            r2 = r7
            r7 = r5
            goto L6e
        L41:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L49:
            java.lang.Object r7 = r0.L$3
            java.util.List r7 = (java.util.List) r7
            java.lang.Object r8 = r0.L$2
            java.util.List r8 = (java.util.List) r8
            java.lang.Object r2 = r0.L$1
            androidx.compose.runtime.ProduceFrameSignal r2 = (androidx.compose.runtime.ProduceFrameSignal) r2
            java.lang.Object r5 = r0.L$0
            androidx.compose.runtime.MonotonicFrameClock r5 = (androidx.compose.runtime.MonotonicFrameClock) r5
            kotlin.ResultKt.throwOnFailure(r9)
            goto L85
        L5d:
            kotlin.ResultKt.throwOnFailure(r9)
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.List r9 = (java.util.List) r9
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.List r2 = (java.util.List) r2
        L6e:
            java.lang.Object r5 = r6.stateLock
            r0.L$0 = r7
            r0.L$1 = r8
            r0.L$2 = r9
            r0.L$3 = r2
            r0.label = r4
            java.lang.Object r5 = r8.awaitFrameRequest(r5, r0)
            if (r5 != r1) goto L81
            goto L9a
        L81:
            r5 = r7
            r7 = r2
            r2 = r8
            r8 = r9
        L85:
            androidx.compose.runtime.Recomposer$$ExternalSyntheticLambda5 r9 = new androidx.compose.runtime.Recomposer$$ExternalSyntheticLambda5
            r9.<init>()
            r0.L$0 = r5
            r0.L$1 = r2
            r0.L$2 = r8
            r0.L$3 = r7
            r0.label = r3
            java.lang.Object r9 = r5.withFrameNanos(r9, r0)
            if (r9 != r1) goto L3c
        L9a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.Recomposer.runFrameLoop(androidx.compose.runtime.MonotonicFrameClock, androidx.compose.runtime.ProduceFrameSignal, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static final CancellableContinuation runFrameLoop$lambda$51(Recomposer recomposer, List list, List list2, ProduceFrameSignal produceFrameSignal, long j) {
        int i;
        CancellableContinuation<Unit> cancellableContinuationDeriveStateLocked;
        if (recomposer.getHasBroadcastFrameClockAwaiters()) {
            Object objBeginSection = Trace.INSTANCE.beginSection("Recomposer:animation");
            try {
                recomposer.broadcastFrameClock.sendFrame(j);
                Snapshot.INSTANCE.sendApplyNotifications();
                Unit unit = Unit.INSTANCE;
                Trace.INSTANCE.endSection(objBeginSection);
            } catch (Throwable th) {
                Trace.INSTANCE.endSection(objBeginSection);
                throw th;
            }
        }
        Object objBeginSection2 = Trace.INSTANCE.beginSection("Recomposer:recompose");
        try {
            recomposer.recordComposerModifications();
            synchronized (recomposer.stateLock) {
                List<ControlledComposition> list3 = recomposer.compositionsAwaitingApply;
                int size = list3.size();
                i = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    list2.add(list3.get(i2));
                }
                recomposer.compositionsAwaitingApply.clear();
                MutableVector<ControlledComposition> mutableVector = recomposer.compositionInvalidations;
                ControlledComposition[] controlledCompositionArr = mutableVector.content;
                int size2 = mutableVector.getSize();
                for (int i3 = 0; i3 < size2; i3++) {
                    list.add(controlledCompositionArr[i3]);
                }
                recomposer.compositionInvalidations.clear();
                produceFrameSignal.takeFrameRequestLocked();
                Unit unit2 = Unit.INSTANCE;
            }
            MutableScatterSet<Object> mutableScatterSet = new MutableScatterSet<>(i, 1, null);
            try {
                int size3 = list.size();
                for (int i4 = 0; i4 < size3; i4++) {
                    ControlledComposition controlledCompositionPerformRecompose = recomposer.performRecompose((ControlledComposition) list.get(i4), mutableScatterSet);
                    if (controlledCompositionPerformRecompose != null) {
                        list2.add(controlledCompositionPerformRecompose);
                    }
                }
                list.clear();
                if (!list2.isEmpty()) {
                    recomposer.changeCount++;
                }
                try {
                    int size4 = list2.size();
                    while (i < size4) {
                        ((ControlledComposition) list2.get(i)).applyChanges();
                        i++;
                    }
                    list2.clear();
                    synchronized (recomposer.stateLock) {
                        cancellableContinuationDeriveStateLocked = recomposer.deriveStateLocked();
                    }
                    Trace.INSTANCE.endSection(objBeginSection2);
                    return cancellableContinuationDeriveStateLocked;
                } catch (Throwable th2) {
                    list2.clear();
                    throw th2;
                }
            } catch (Throwable th3) {
                list.clear();
                throw th3;
            }
        } catch (Throwable th4) {
            Trace.INSTANCE.endSection(objBeginSection2);
            throw th4;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getHasSchedulingWork() {
        boolean z;
        synchronized (this.stateLock) {
            z = this.snapshotInvalidations.isNotEmpty() || this.compositionInvalidations.getSize() != 0 || getHasBroadcastFrameClockAwaitersLocked();
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object awaitWorkAvailable(Continuation<? super Unit> continuation) {
        if (getHasSchedulingWork()) {
            return Unit.INSTANCE;
        }
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        synchronized (this.stateLock) {
            if (!getHasSchedulingWork()) {
                this.workContinuation = cancellableContinuationImpl2;
                cancellableContinuationImpl2 = null;
            }
        }
        if (cancellableContinuationImpl2 != null) {
            Result.Companion companion = Result.INSTANCE;
            cancellableContinuationImpl2.resumeWith(Result.m8936constructorimpl(Unit.INSTANCE));
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$recompositionRunner$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Recomposer.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.runtime.Recomposer$recompositionRunner$2", f = "Recomposer.kt", i = {0, 0}, l = {1159}, m = "invokeSuspend", n = {"callingJob", "unregisterApplyObserver"}, s = {"L$0", "L$1"})
    static final class C05732 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function3<CoroutineScope, MonotonicFrameClock, Continuation<? super Unit>, Object> $block;
        final /* synthetic */ MonotonicFrameClock $parentFrameClock;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C05732(Function3<? super CoroutineScope, ? super MonotonicFrameClock, ? super Continuation<? super Unit>, ? extends Object> function3, MonotonicFrameClock monotonicFrameClock, Continuation<? super C05732> continuation) {
            super(2, continuation);
            this.$block = function3;
            this.$parentFrameClock = monotonicFrameClock;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05732 c05732 = Recomposer.this.new C05732(this.$block, this.$parentFrameClock, continuation);
            c05732.L$0 = obj;
            return c05732;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05732) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:24:0x0099 A[Catch: all -> 0x00ae, TryCatch #3 {, blocks: (B:22:0x0093, B:24:0x0099, B:25:0x009c), top: B:52:0x0093 }] */
        /* JADX WARN: Code duplicated, block: B:38:0x00c7 A[Catch: all -> 0x00da, TryCatch #2 {, blocks: (B:36:0x00c1, B:38:0x00c7, B:39:0x00ca), top: B:50:0x00c1 }] */
        /* JADX WARN: Code duplicated, block: B:50:0x00c1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:52:0x0093 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Job job;
            ObserverHandle observerHandle;
            Throwable th;
            Object obj2;
            Recomposer recomposer;
            Object obj3;
            Recomposer recomposer2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                observerHandle = (ObserverHandle) this.L$1;
                job = (Job) this.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    observerHandle.dispose();
                    obj3 = Recomposer.this.stateLock;
                    recomposer2 = Recomposer.this;
                    synchronized (obj3) {
                        if (recomposer2.runnerJob == job) {
                            recomposer2.runnerJob = null;
                        }
                        recomposer2.deriveStateLocked();
                    }
                    Recomposer.INSTANCE.removeRunning(Recomposer.this.recomposerInfo);
                    return Unit.INSTANCE;
                } catch (Throwable th2) {
                    th = th2;
                    observerHandle.dispose();
                    obj2 = Recomposer.this.stateLock;
                    recomposer = Recomposer.this;
                    synchronized (obj2) {
                        if (recomposer.runnerJob == job) {
                            recomposer.runnerJob = null;
                        }
                        recomposer.deriveStateLocked();
                        Recomposer.INSTANCE.removeRunning(Recomposer.this.recomposerInfo);
                        throw th;
                    }
                }
            }
            ResultKt.throwOnFailure(obj);
            job = JobKt.getJob(((CoroutineScope) this.L$0).getCoroutineContext());
            Recomposer.this.registerRunnerJob(job);
            Snapshot.Companion companion = Snapshot.INSTANCE;
            final Recomposer recomposer3 = Recomposer.this;
            ObserverHandle observerHandleRegisterApplyObserver = companion.registerApplyObserver(new Function2() { // from class: androidx.compose.runtime.Recomposer$recompositionRunner$2$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj4, Object obj5) {
                    return Recomposer.C05732.invokeSuspend$lambda$2(recomposer3, (Set) obj4, (Snapshot) obj5);
                }
            });
            Recomposer.INSTANCE.addRunning(Recomposer.this.recomposerInfo);
            try {
                List listKnownCompositions = Recomposer.this.knownCompositions();
                int size = listKnownCompositions.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((ControlledComposition) listKnownCompositions.get(i2)).invalidateAll();
                }
                this.L$0 = job;
                this.L$1 = observerHandleRegisterApplyObserver;
                this.label = 1;
                if (CoroutineScopeKt.coroutineScope(new C01232(this.$block, this.$parentFrameClock, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                observerHandle = observerHandleRegisterApplyObserver;
                observerHandle.dispose();
                obj3 = Recomposer.this.stateLock;
                recomposer2 = Recomposer.this;
                synchronized (obj3) {
                    if (recomposer2.runnerJob == job) {
                        recomposer2.runnerJob = null;
                    }
                    recomposer2.deriveStateLocked();
                    Recomposer.INSTANCE.removeRunning(Recomposer.this.recomposerInfo);
                    return Unit.INSTANCE;
                }
            } catch (Throwable th3) {
                observerHandle = observerHandleRegisterApplyObserver;
                th = th3;
                observerHandle.dispose();
                obj2 = Recomposer.this.stateLock;
                recomposer = Recomposer.this;
                synchronized (obj2) {
                    if (recomposer.runnerJob == job) {
                        recomposer.runnerJob = null;
                    }
                    recomposer.deriveStateLocked();
                }
                Recomposer.INSTANCE.removeRunning(Recomposer.this.recomposerInfo);
                throw th;
            }
        }

        /* JADX WARN: Code duplicated, block: B:22:0x0073 A[Catch: all -> 0x00c2, TryCatch #0 {, blocks: (B:4:0x0007, B:6:0x001b, B:8:0x0024, B:11:0x0035, B:13:0x0045, B:15:0x0051, B:17:0x005a, B:19:0x0063, B:22:0x0073, B:23:0x0076, B:26:0x007e, B:36:0x00a9, B:27:0x0081, B:28:0x0087, B:30:0x008d, B:32:0x0095, B:35:0x00a5), top: B:47:0x0007 }] */
        /* JADX WARN: Code duplicated, block: B:25:0x007c A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:26:0x007e A[Catch: all -> 0x00c2, LOOP:0: B:11:0x0035->B:26:0x007e, LOOP_END, TryCatch #0 {, blocks: (B:4:0x0007, B:6:0x001b, B:8:0x0024, B:11:0x0035, B:13:0x0045, B:15:0x0051, B:17:0x005a, B:19:0x0063, B:22:0x0073, B:23:0x0076, B:26:0x007e, B:36:0x00a9, B:27:0x0081, B:28:0x0087, B:30:0x008d, B:32:0x0095, B:35:0x00a5), top: B:47:0x0007 }] */
        /* JADX WARN: Code duplicated, block: B:50:0x00a9 A[EDGE_INSN: B:50:0x00a9->B:36:0x00a9 BREAK  A[LOOP:0: B:11:0x0035->B:26:0x007e], SYNTHETIC] */
        static final Unit invokeSuspend$lambda$2(Recomposer recomposer, Set set, Snapshot snapshot) {
            CancellableContinuation cancellableContinuationDeriveStateLocked;
            synchronized (recomposer.stateLock) {
                if (((State) recomposer._state.getValue()).compareTo(State.Idle) >= 0) {
                    MutableScatterSet mutableScatterSet = recomposer.snapshotInvalidations;
                    if (set instanceof ScatterSetWrapper) {
                        ScatterSet set$runtime = ((ScatterSetWrapper) set).getSet$runtime();
                        Object[] objArr = set$runtime.elements;
                        long[] jArr = set$runtime.metadata;
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
                                            Object obj = objArr[(i << 3) + i3];
                                            if (obj instanceof StateObjectImpl) {
                                                ReaderKind.Companion companion = ReaderKind.INSTANCE;
                                                if (((StateObjectImpl) obj).m3591isReadInh_f27i8$runtime(ReaderKind.m3579constructorimpl(1))) {
                                                    mutableScatterSet.add(obj);
                                                }
                                            } else {
                                                mutableScatterSet.add(obj);
                                            }
                                        }
                                        j >>= 8;
                                    }
                                    if (i2 != 8) {
                                        break;
                                    }
                                    if (i != length) {
                                        break;
                                    }
                                    i++;
                                }
                            }
                        }
                    } else {
                        for (Object obj2 : set) {
                            if (obj2 instanceof StateObjectImpl) {
                                ReaderKind.Companion companion2 = ReaderKind.INSTANCE;
                                if (!((StateObjectImpl) obj2).m3591isReadInh_f27i8$runtime(ReaderKind.m3579constructorimpl(1))) {
                                }
                            }
                            mutableScatterSet.add(obj2);
                        }
                    }
                    cancellableContinuationDeriveStateLocked = recomposer.deriveStateLocked();
                } else {
                    cancellableContinuationDeriveStateLocked = null;
                }
            }
            if (cancellableContinuationDeriveStateLocked != null) {
                Result.Companion companion3 = Result.INSTANCE;
                cancellableContinuationDeriveStateLocked.resumeWith(Result.m8936constructorimpl(Unit.INSTANCE));
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$recompositionRunner$2$2, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: Recomposer.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.runtime.Recomposer$recompositionRunner$2$2", f = "Recomposer.kt", i = {}, l = {1159}, m = "invokeSuspend", n = {}, s = {})
        static final class C01232 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function3<CoroutineScope, MonotonicFrameClock, Continuation<? super Unit>, Object> $block;
            final /* synthetic */ MonotonicFrameClock $parentFrameClock;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C01232(Function3<? super CoroutineScope, ? super MonotonicFrameClock, ? super Continuation<? super Unit>, ? extends Object> function3, MonotonicFrameClock monotonicFrameClock, Continuation<? super C01232> continuation) {
                super(2, continuation);
                this.$block = function3;
                this.$parentFrameClock = monotonicFrameClock;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C01232 c01232 = new C01232(this.$block, this.$parentFrameClock, continuation);
                c01232.L$0 = obj;
                return c01232;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01232) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                    Function3<CoroutineScope, MonotonicFrameClock, Continuation<? super Unit>, Object> function3 = this.$block;
                    MonotonicFrameClock monotonicFrameClock = this.$parentFrameClock;
                    this.label = 1;
                    if (function3.invoke(coroutineScope, monotonicFrameClock, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object recompositionRunner(Function3<? super CoroutineScope, ? super MonotonicFrameClock, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super Unit> continuation) {
        Object objWithContext = BuildersKt.withContext(this.broadcastFrameClock, new C05732(function3, MonotonicFrameClockKt.getMonotonicFrameClock(continuation.getContext()), null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    public final void cancel() {
        synchronized (this.stateLock) {
            if (this._state.getValue().compareTo(State.Idle) >= 0) {
                this._state.setValue(State.ShuttingDown);
            }
            Unit unit = Unit.INSTANCE;
        }
        Job.CC.cancel$default((Job) this.effectJob, (CancellationException) null, 1, (Object) null);
    }

    public final void close() {
        if (this.effectJob.complete()) {
            synchronized (this.stateLock) {
                this.isClosed = true;
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$join$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Recomposer.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Landroidx/compose/runtime/Recomposer$State;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.runtime.Recomposer$join$2", f = "Recomposer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C05722 extends SuspendLambda implements Function2<State, Continuation<? super Boolean>, Object> {
        /* synthetic */ Object L$0;
        int label;

        C05722(Continuation<? super C05722> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05722 c05722 = new C05722(continuation);
            c05722.L$0 = obj;
            return c05722;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(State state, Continuation<? super Boolean> continuation) {
            return ((C05722) create(state, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Boxing.boxBoolean(((State) this.L$0) == State.ShutDown);
        }
    }

    public final Object join(Continuation<? super Unit> continuation) {
        Object objFirst = FlowKt.first(getCurrentState(), new C05722(null), continuation);
        return objFirst == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFirst : Unit.INSTANCE;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void composeInitial$runtime(ControlledComposition composition, Function2<? super Composer, ? super Integer, Unit> content) throws Throwable {
        boolean z;
        boolean zIsComposing = composition.isComposing();
        synchronized (this.stateLock) {
            if (this._state.getValue().compareTo(State.ShuttingDown) > 0) {
                boolean zContains = knownCompositionsLocked().contains(composition);
                z = !zContains;
                if (!zContains) {
                    registerCompositionLocked(composition);
                }
            } else {
                z = true;
            }
        }
        try {
            MutableSnapshot mutableSnapshotTakeMutableSnapshot = Snapshot.INSTANCE.takeMutableSnapshot(readObserverOf(composition), writeObserverOf(composition, null));
            try {
                MutableSnapshot mutableSnapshot = mutableSnapshotTakeMutableSnapshot;
                Snapshot snapshotMakeCurrent = mutableSnapshot.makeCurrent();
                try {
                    composition.composeContent(content);
                    Unit unit = Unit.INSTANCE;
                    mutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                    applyAndCheck(mutableSnapshotTakeMutableSnapshot);
                    synchronized (this.stateLock) {
                        if (this._state.getValue().compareTo(State.ShuttingDown) > 0) {
                            if (!knownCompositionsLocked().contains(composition)) {
                                addKnownCompositionLocked(composition);
                            }
                        } else {
                            unregisterCompositionLocked(composition);
                        }
                        Unit unit2 = Unit.INSTANCE;
                    }
                    if (!zIsComposing) {
                        Snapshot.INSTANCE.notifyObjectsInitialized();
                    }
                    try {
                        performInitialMovableContentInserts(composition);
                        try {
                            composition.applyChanges();
                            composition.applyLateChanges();
                            if (zIsComposing) {
                                return;
                            }
                            Snapshot.INSTANCE.notifyObjectsInitialized();
                        } catch (Throwable th) {
                            processCompositionError$default(this, th, null, false, 6, null);
                        }
                    } catch (Throwable th2) {
                        processCompositionError(th2, composition, true);
                    }
                } catch (Throwable th3) {
                    mutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                    throw th3;
                }
            } catch (Throwable th4) {
                applyAndCheck(mutableSnapshotTakeMutableSnapshot);
                throw th4;
            }
        } catch (Throwable th5) {
            if (z) {
                synchronized (this.stateLock) {
                    unregisterCompositionLocked(composition);
                    Unit unit3 = Unit.INSTANCE;
                }
            }
            processCompositionError(th5, composition, true);
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public ScatterSet<RecomposeScopeImpl> recomposePaused$runtime(ControlledComposition composition, ShouldPauseCallback shouldPause, ScatterSet<RecomposeScopeImpl> invalidScopes) {
        try {
            recordComposerModifications();
            composition.recordModificationsOf(ScatterSetWrapperKt.wrapIntoSet(invalidScopes));
            ShouldPauseCallback andSetShouldPauseCallback = composition.getAndSetShouldPauseCallback(shouldPause);
            try {
                ControlledComposition controlledCompositionPerformRecompose = performRecompose(composition, null);
                if (controlledCompositionPerformRecompose != null) {
                    performInitialMovableContentInserts(composition);
                    controlledCompositionPerformRecompose.applyChanges();
                    controlledCompositionPerformRecompose.applyLateChanges();
                }
                MutableScatterSet<RecomposeScopeImpl> mutableScatterSet = this.pausedScopes.get();
                MutableScatterSet<RecomposeScopeImpl> mutableScatterSetEmptyScatterSet = mutableScatterSet != null ? mutableScatterSet : ScatterSetKt.emptyScatterSet();
                composition.getAndSetShouldPauseCallback(andSetShouldPauseCallback);
                this.pausedScopes.set(null);
                return mutableScatterSetEmptyScatterSet;
            } catch (Throwable th) {
                composition.getAndSetShouldPauseCallback(andSetShouldPauseCallback);
                throw th;
            }
        } catch (Throwable th2) {
            this.pausedScopes.set(null);
            throw th2;
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void reportPausedScope$runtime(RecomposeScopeImpl scope) {
        MutableScatterSet<RecomposeScopeImpl> mutableScatterSetMutableScatterSetOf = this.pausedScopes.get();
        if (mutableScatterSetMutableScatterSetOf == null) {
            mutableScatterSetMutableScatterSetOf = ScatterSetKt.mutableScatterSetOf();
            this.pausedScopes.set(mutableScatterSetMutableScatterSetOf);
        }
        mutableScatterSetMutableScatterSetOf.add(scope);
    }

    private final void performInitialMovableContentInserts(ControlledComposition composition) {
        synchronized (this.stateLock) {
            List<MovableContentStateReference> list = this.movableContentAwaitingInsert;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (Intrinsics.areEqual(list.get(i).getComposition(), composition)) {
                    Unit unit = Unit.INSTANCE;
                    ArrayList arrayList = new ArrayList();
                    performInitialMovableContentInserts$fillToInsert(arrayList, this, composition);
                    while (!arrayList.isEmpty()) {
                        performInsertValues(arrayList, null);
                        performInitialMovableContentInserts$fillToInsert(arrayList, this, composition);
                    }
                    return;
                }
            }
        }
    }

    private static final void performInitialMovableContentInserts$fillToInsert(List<MovableContentStateReference> list, Recomposer recomposer, ControlledComposition controlledComposition) {
        list.clear();
        synchronized (recomposer.stateLock) {
            Iterator<MovableContentStateReference> it = recomposer.movableContentAwaitingInsert.iterator();
            while (it.hasNext()) {
                MovableContentStateReference next = it.next();
                if (Intrinsics.areEqual(next.getComposition(), controlledComposition)) {
                    list.add(next);
                    it.remove();
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ControlledComposition performRecompose(final ControlledComposition composition, final MutableScatterSet<Object> modifiedValues) {
        Set<ControlledComposition> set;
        if (composition.isComposing() || composition.isDisposed() || ((set = this.compositionsRemoved) != null && set.contains(composition))) {
            return null;
        }
        MutableSnapshot mutableSnapshotTakeMutableSnapshot = Snapshot.INSTANCE.takeMutableSnapshot(readObserverOf(composition), writeObserverOf(composition, modifiedValues));
        try {
            MutableSnapshot mutableSnapshot = mutableSnapshotTakeMutableSnapshot;
            Snapshot snapshotMakeCurrent = mutableSnapshot.makeCurrent();
            if (modifiedValues != null) {
                try {
                    if (modifiedValues.isNotEmpty()) {
                        composition.prepareCompose(new Function0() { // from class: androidx.compose.runtime.Recomposer$$ExternalSyntheticLambda6
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return Recomposer.performRecompose$lambda$69$lambda$68(modifiedValues, composition);
                            }
                        });
                    }
                } catch (Throwable th) {
                    mutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                    throw th;
                }
            }
            boolean zRecompose = composition.recompose();
            mutableSnapshot.restoreCurrent(snapshotMakeCurrent);
            applyAndCheck(mutableSnapshotTakeMutableSnapshot);
            if (zRecompose) {
                return composition;
            }
            return null;
        } catch (Throwable th2) {
            applyAndCheck(mutableSnapshotTakeMutableSnapshot);
            throw th2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0040 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:15:0x0042 A[LOOP:0: B:5:0x000d->B:15:0x0042, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:19:0x0045 A[EDGE_INSN: B:19:0x0045->B:16:0x0045 BREAK  A[LOOP:0: B:5:0x000d->B:15:0x0042], SYNTHETIC] */
    static final Unit performRecompose$lambda$69$lambda$68(MutableScatterSet mutableScatterSet, ControlledComposition controlledComposition) {
        MutableScatterSet mutableScatterSet2 = mutableScatterSet;
        Object[] objArr = mutableScatterSet2.elements;
        long[] jArr = mutableScatterSet2.metadata;
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
                            controlledComposition.recordWriteOf(objArr[(i << 3) + i3]);
                        }
                        j >>= 8;
                    }
                    if (i2 != 8) {
                        break;
                    }
                    if (i != length) {
                        break;
                    }
                    i++;
                }
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void discardUnusedMovableContentState() {
        int i;
        MutableObjectList mutableObjectListEmptyObjectList;
        synchronized (this.stateLock) {
            if (MultiValueMap.m3541isNotEmptyimpl(this.movableContentRemoved)) {
                ObjectList objectListM3546valuesimpl = MultiValueMap.m3546valuesimpl(this.movableContentRemoved);
                MultiValueMap.m3531clearimpl(this.movableContentRemoved);
                this.movableContentNestedStatesAvailable.clear();
                MultiValueMap.m3531clearimpl(this.movableContentNestedExtractionsPending);
                MutableObjectList mutableObjectList = new MutableObjectList(objectListM3546valuesimpl.getSize());
                Object[] objArr = objectListM3546valuesimpl.content;
                int i2 = objectListM3546valuesimpl._size;
                for (int i3 = 0; i3 < i2; i3++) {
                    MovableContentStateReference movableContentStateReference = (MovableContentStateReference) objArr[i3];
                    mutableObjectList.add(TuplesKt.to(movableContentStateReference, this.movableContentStatesAvailable.get(movableContentStateReference)));
                }
                mutableObjectListEmptyObjectList = mutableObjectList;
                this.movableContentStatesAvailable.clear();
            } else {
                mutableObjectListEmptyObjectList = ObjectListKt.emptyObjectList();
            }
        }
        Object[] objArr2 = mutableObjectListEmptyObjectList.content;
        int i4 = mutableObjectListEmptyObjectList._size;
        for (i = 0; i < i4; i++) {
            Pair pair = (Pair) objArr2[i];
            MovableContentStateReference movableContentStateReference2 = (MovableContentStateReference) pair.component1();
            MovableContentState movableContentState = (MovableContentState) pair.component2();
            if (movableContentState != null) {
                movableContentStateReference2.getComposition().disposeUnusedMovableContent(movableContentState);
            }
        }
    }

    private final Function1<Object, Unit> readObserverOf(final ControlledComposition composition) {
        return new Function1() { // from class: androidx.compose.runtime.Recomposer$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Recomposer.readObserverOf$lambda$86(composition, obj);
            }
        };
    }

    static final Unit readObserverOf$lambda$86(ControlledComposition controlledComposition, Object obj) {
        controlledComposition.recordReadOf(obj);
        return Unit.INSTANCE;
    }

    private final Function1<Object, Unit> writeObserverOf(final ControlledComposition composition, final MutableScatterSet<Object> modifiedValues) {
        return new Function1() { // from class: androidx.compose.runtime.Recomposer$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Recomposer.writeObserverOf$lambda$87(composition, modifiedValues, obj);
            }
        };
    }

    static final Unit writeObserverOf$lambda$87(ControlledComposition controlledComposition, MutableScatterSet mutableScatterSet, Object obj) {
        controlledComposition.recordWriteOf(obj);
        if (mutableScatterSet != null) {
            mutableScatterSet.add(obj);
        }
        return Unit.INSTANCE;
    }

    private final <T> T composing(ControlledComposition composition, MutableScatterSet<Object> modifiedValues, Function0<? extends T> block) {
        MutableSnapshot mutableSnapshotTakeMutableSnapshot = Snapshot.INSTANCE.takeMutableSnapshot(readObserverOf(composition), writeObserverOf(composition, modifiedValues));
        try {
            MutableSnapshot mutableSnapshot = mutableSnapshotTakeMutableSnapshot;
            Snapshot snapshotMakeCurrent = mutableSnapshot.makeCurrent();
            try {
                T tInvoke = block.invoke();
                mutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                applyAndCheck(mutableSnapshotTakeMutableSnapshot);
                return tInvoke;
            } catch (Throwable th) {
                mutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                throw th;
            }
        } catch (Throwable th2) {
            applyAndCheck(mutableSnapshotTakeMutableSnapshot);
            throw th2;
        }
    }

    private final void applyAndCheck(MutableSnapshot snapshot) {
        try {
            if (snapshot.apply() instanceof SnapshotApplyResult.Failure) {
                throw new IllegalStateException("Unsupported concurrent change during composition. A state object was modified by composition as well as being modified outside composition.".toString());
            }
            snapshot.dispose();
        } catch (Throwable th) {
            snapshot.dispose();
            throw th;
        }
    }

    public final boolean getHasPendingWork() {
        boolean z;
        synchronized (this.stateLock) {
            z = this.snapshotInvalidations.isNotEmpty() || this.compositionInvalidations.getSize() != 0 || this.concurrentCompositionsOutstanding > 0 || !this.compositionsAwaitingApply.isEmpty() || getHasBroadcastFrameClockAwaitersLocked() || MultiValueMap.m3541isNotEmptyimpl(this.movableContentRemoved);
        }
        return z;
    }

    private final boolean getHasFrameWorkLocked() {
        return this.compositionInvalidations.getSize() != 0 || getHasBroadcastFrameClockAwaitersLocked() || MultiValueMap.m3541isNotEmptyimpl(this.movableContentRemoved);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getHasConcurrentFrameWorkLocked() {
        return !this.compositionsAwaitingApply.isEmpty() || getHasBroadcastFrameClockAwaitersLocked();
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$awaitIdle$2, reason: invalid class name */
    /* JADX INFO: compiled from: Recomposer.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Landroidx/compose/runtime/Recomposer$State;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.runtime.Recomposer$awaitIdle$2", f = "Recomposer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<State, Continuation<? super Boolean>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(State state, Continuation<? super Boolean> continuation) {
            return ((AnonymousClass2) create(state, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Boxing.boxBoolean(((State) this.L$0).compareTo(State.Idle) > 0);
        }
    }

    public final Object awaitIdle(Continuation<? super Unit> continuation) {
        Object objCollect = FlowKt.collect(FlowKt.takeWhile(getCurrentState(), new AnonymousClass2(null)), continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    public final void pauseCompositionFrameClock() {
        synchronized (this.stateLock) {
            this.frameClockPaused = true;
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void resumeCompositionFrameClock() {
        CancellableContinuation<Unit> cancellableContinuationDeriveStateLocked;
        synchronized (this.stateLock) {
            if (this.frameClockPaused) {
                this.frameClockPaused = false;
                cancellableContinuationDeriveStateLocked = deriveStateLocked();
            } else {
                cancellableContinuationDeriveStateLocked = null;
            }
        }
        if (cancellableContinuationDeriveStateLocked != null) {
            Result.Companion companion = Result.INSTANCE;
            cancellableContinuationDeriveStateLocked.resumeWith(Result.m8936constructorimpl(Unit.INSTANCE));
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public boolean getCollectingCallByInformation$runtime() {
        return _hotReloadEnabled.get().booleanValue();
    }

    @Override // androidx.compose.runtime.CompositionContext
    /* JADX INFO: renamed from: getCollectingSourceInformation$runtime */
    public boolean getCollectingSourceInformation() {
        return ComposerKt.getComposeStackTraceEnabled();
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void unregisterComposition$runtime(ControlledComposition composition) {
        synchronized (this.stateLock) {
            removeKnownCompositionLocked(composition);
            this.compositionInvalidations.remove(composition);
            this.compositionsAwaitingApply.remove(composition);
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void invalidate$runtime(ControlledComposition composition) {
        CancellableContinuation<Unit> cancellableContinuationDeriveStateLocked;
        synchronized (this.stateLock) {
            if (this.compositionInvalidations.contains(composition)) {
                cancellableContinuationDeriveStateLocked = null;
            } else {
                this.compositionInvalidations.add(composition);
                cancellableContinuationDeriveStateLocked = deriveStateLocked();
            }
        }
        if (cancellableContinuationDeriveStateLocked != null) {
            Result.Companion companion = Result.INSTANCE;
            cancellableContinuationDeriveStateLocked.resumeWith(Result.m8936constructorimpl(Unit.INSTANCE));
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void invalidateScope$runtime(RecomposeScopeImpl scope) {
        CancellableContinuation<Unit> cancellableContinuationDeriveStateLocked;
        synchronized (this.stateLock) {
            this.snapshotInvalidations.add(scope);
            cancellableContinuationDeriveStateLocked = deriveStateLocked();
        }
        if (cancellableContinuationDeriveStateLocked != null) {
            Result.Companion companion = Result.INSTANCE;
            cancellableContinuationDeriveStateLocked.resumeWith(Result.m8936constructorimpl(Unit.INSTANCE));
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void insertMovableContent$runtime(MovableContentStateReference reference) {
        CancellableContinuation<Unit> cancellableContinuationDeriveStateLocked;
        synchronized (this.stateLock) {
            this.movableContentAwaitingInsert.add(reference);
            cancellableContinuationDeriveStateLocked = deriveStateLocked();
        }
        if (cancellableContinuationDeriveStateLocked != null) {
            Result.Companion companion = Result.INSTANCE;
            cancellableContinuationDeriveStateLocked.resumeWith(Result.m8936constructorimpl(Unit.INSTANCE));
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void deletedMovableContent$runtime(MovableContentStateReference reference) {
        CancellableContinuation<Unit> cancellableContinuationDeriveStateLocked;
        synchronized (this.stateLock) {
            MultiValueMap.m3529addimpl(this.movableContentRemoved, reference.getContent$runtime(), reference);
            if (reference.getNestedReferences$runtime() != null) {
                deletedMovableContent$lambda$96$recordNestedStatesOf(this, reference, reference);
            }
            cancellableContinuationDeriveStateLocked = deriveStateLocked();
        }
        if (cancellableContinuationDeriveStateLocked != null) {
            Result.Companion companion = Result.INSTANCE;
            cancellableContinuationDeriveStateLocked.resumeWith(Result.m8936constructorimpl(Unit.INSTANCE));
        }
    }

    private static final void deletedMovableContent$lambda$96$recordNestedStatesOf(Recomposer recomposer, MovableContentStateReference movableContentStateReference, MovableContentStateReference movableContentStateReference2) {
        List<MovableContentStateReference> nestedReferences$runtime = movableContentStateReference2.getNestedReferences$runtime();
        if (nestedReferences$runtime != null) {
            int size = nestedReferences$runtime.size();
            for (int i = 0; i < size; i++) {
                MovableContentStateReference movableContentStateReference3 = nestedReferences$runtime.get(i);
                recomposer.movableContentNestedStatesAvailable.add(movableContentStateReference3.getContent$runtime(), new NestedMovableContent(movableContentStateReference3, movableContentStateReference));
                deletedMovableContent$lambda$96$recordNestedStatesOf(recomposer, movableContentStateReference, movableContentStateReference3);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0068 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:19:0x006a A[Catch: all -> 0x0071, LOOP:0: B:9:0x002d->B:19:0x006a, LOOP_END, TryCatch #0 {, blocks: (B:4:0x0009, B:6:0x001a, B:9:0x002d, B:11:0x003d, B:13:0x0049, B:15:0x0052, B:16:0x0062, B:19:0x006a, B:20:0x006d), top: B:26:0x0009 }] */
    /* JADX WARN: Code duplicated, block: B:29:0x006d A[EDGE_INSN: B:29:0x006d->B:20:0x006d BREAK  A[LOOP:0: B:9:0x002d->B:19:0x006a], SYNTHETIC] */
    @Override // androidx.compose.runtime.CompositionContext
    public void movableContentStateReleased$runtime(MovableContentStateReference reference, MovableContentState data, Applier<?> applier) {
        synchronized (this.stateLock) {
            this.movableContentStatesAvailable.set(reference, data);
            ObjectList<MovableContentStateReference> objectListM3538getimpl = MultiValueMap.m3538getimpl(this.movableContentNestedExtractionsPending, reference);
            if (objectListM3538getimpl.isNotEmpty()) {
                ScatterMap<MovableContentStateReference, MovableContentState> scatterMapExtractNestedStates$runtime = data.extractNestedStates$runtime(applier, objectListM3538getimpl);
                Object[] objArr = scatterMapExtractNestedStates$runtime.keys;
                Object[] objArr2 = scatterMapExtractNestedStates$runtime.values;
                long[] jArr = scatterMapExtractNestedStates$runtime.metadata;
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
                                    int i4 = (i << 3) + i3;
                                    Object obj = objArr[i4];
                                    this.movableContentStatesAvailable.set((MovableContentStateReference) obj, (MovableContentState) objArr2[i4]);
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
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void reportRemovedComposition$runtime(ControlledComposition composition) {
        synchronized (this.stateLock) {
            LinkedHashSet linkedHashSet = this.compositionsRemoved;
            if (linkedHashSet == null) {
                linkedHashSet = new LinkedHashSet();
                this.compositionsRemoved = linkedHashSet;
            }
            linkedHashSet.add(composition);
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public MovableContentState movableContentStateResolve$runtime(MovableContentStateReference reference) {
        MovableContentState movableContentStateRemove;
        synchronized (this.stateLock) {
            movableContentStateRemove = this.movableContentStatesAvailable.remove(reference);
        }
        return movableContentStateRemove;
    }

    /* JADX INFO: compiled from: Recomposer.kt */
    @Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0000¢\u0006\u0002\b\u0015J\u0015\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000bH\u0000¢\u0006\u0002\b\u0019J\u0014\u0010\u001a\u001a\u00020\u00172\n\u0010\u001b\u001a\u00060\u0007R\u00020\bH\u0002J\u0014\u0010\u001c\u001a\u00020\u00172\n\u0010\u001b\u001a\u00060\u0007R\u00020\bH\u0002J\r\u0010\u001d\u001a\u00020\u0001H\u0000¢\u0006\u0002\b\u001eJ\u0015\u0010\u001f\u001a\u00020\u00172\u0006\u0010 \u001a\u00020\u0001H\u0000¢\u0006\u0002\b!J\u0015\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020$H\u0000¢\u0006\u0002\b%J\u0013\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'H\u0000¢\u0006\u0002\b)J\r\u0010*\u001a\u00020\u0017H\u0000¢\u0006\u0002\b+R\u001e\u0010\u0004\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0007R\u00020\b0\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000f8F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006,"}, d2 = {"Landroidx/compose/runtime/Recomposer$Companion;", "", "<init>", "()V", "_runningRecomposers", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentSet;", "Landroidx/compose/runtime/Recomposer$RecomposerInfoImpl;", "Landroidx/compose/runtime/Recomposer;", "_hotReloadEnabled", "Ljava/util/concurrent/atomic/AtomicReference;", "", "Landroidx/compose/runtime/internal/AtomicReference;", "Ljava/util/concurrent/atomic/AtomicReference;", "runningRecomposers", "Lkotlinx/coroutines/flow/StateFlow;", "", "Landroidx/compose/runtime/RecomposerInfo;", "getRunningRecomposers", "()Lkotlinx/coroutines/flow/StateFlow;", "currentRunningRecomposers", "currentRunningRecomposers$runtime", "setHotReloadEnabled", "", ES6Iterator.VALUE_PROPERTY, "setHotReloadEnabled$runtime", "addRunning", "info", "removeRunning", "saveStateAndDisposeForHotReload", "saveStateAndDisposeForHotReload$runtime", "loadStateAndComposeForHotReload", "token", "loadStateAndComposeForHotReload$runtime", "invalidateGroupsWithKey", "key", "", "invalidateGroupsWithKey$runtime", "getCurrentErrors", "", "Landroidx/compose/runtime/RecomposerErrorInfo;", "getCurrentErrors$runtime", "clearErrors", "clearErrors$runtime", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final StateFlow<Set<RecomposerInfo>> getRunningRecomposers() {
            return Recomposer._runningRecomposers;
        }

        public final Set<RecomposerInfo> currentRunningRecomposers$runtime() {
            return (Set) Recomposer._runningRecomposers.getValue();
        }

        public final void setHotReloadEnabled$runtime(boolean value) {
            Recomposer._hotReloadEnabled.set(Boolean.valueOf(value));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void addRunning(RecomposerInfoImpl info) {
            PersistentSet persistentSet;
            PersistentSet persistentSetAdd;
            do {
                persistentSet = (PersistentSet) Recomposer._runningRecomposers.getValue();
                persistentSetAdd = persistentSet.add(info);
                if (persistentSet == persistentSetAdd) {
                    return;
                }
            } while (!Recomposer._runningRecomposers.compareAndSet(persistentSet, persistentSetAdd));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void removeRunning(RecomposerInfoImpl info) {
            PersistentSet persistentSet;
            PersistentSet persistentSetRemove;
            do {
                persistentSet = (PersistentSet) Recomposer._runningRecomposers.getValue();
                persistentSetRemove = persistentSet.remove(info);
                if (persistentSet == persistentSetRemove) {
                    return;
                }
            } while (!Recomposer._runningRecomposers.compareAndSet(persistentSet, persistentSetRemove));
        }

        public final Object saveStateAndDisposeForHotReload$runtime() {
            Recomposer._hotReloadEnabled.set(true);
            Iterable iterable = (Iterable) Recomposer._runningRecomposers.getValue();
            ArrayList arrayList = new ArrayList();
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                CollectionsKt.addAll(arrayList, ((RecomposerInfoImpl) it.next()).saveStateAndDisposeForHotReload());
            }
            return arrayList;
        }

        public final void loadStateAndComposeForHotReload$runtime(Object token) {
            Recomposer._hotReloadEnabled.set(true);
            Iterator it = ((Iterable) Recomposer._runningRecomposers.getValue()).iterator();
            while (it.hasNext()) {
                ((RecomposerInfoImpl) it.next()).resetErrorState();
            }
            Intrinsics.checkNotNull(token, "null cannot be cast to non-null type kotlin.collections.List<androidx.compose.runtime.Recomposer.HotReloadable>");
            List list = (List) token;
            List list2 = list;
            int size = list2.size();
            for (int i = 0; i < size; i++) {
                ((HotReloadable) list.get(i)).resetContent();
            }
            int size2 = list2.size();
            for (int i2 = 0; i2 < size2; i2++) {
                ((HotReloadable) list.get(i2)).recompose();
            }
            Iterator it2 = ((Iterable) Recomposer._runningRecomposers.getValue()).iterator();
            while (it2.hasNext()) {
                ((RecomposerInfoImpl) it2.next()).retryFailedCompositions();
            }
        }

        public final void invalidateGroupsWithKey$runtime(int key) {
            Recomposer._hotReloadEnabled.set(true);
            for (RecomposerInfoImpl recomposerInfoImpl : (Iterable) Recomposer._runningRecomposers.getValue()) {
                RecomposerErrorInfo currentError = recomposerInfoImpl.getCurrentError();
                if (currentError == null || currentError.getRecoverable()) {
                    recomposerInfoImpl.resetErrorState();
                    recomposerInfoImpl.invalidateGroupsWithKey(key);
                    recomposerInfoImpl.retryFailedCompositions();
                }
            }
        }

        public final List<RecomposerErrorInfo> getCurrentErrors$runtime() {
            Iterable iterable = (Iterable) Recomposer._runningRecomposers.getValue();
            ArrayList arrayList = new ArrayList();
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                RecomposerErrorInfo currentError = ((RecomposerInfoImpl) it.next()).getCurrentError();
                if (currentError != null) {
                    arrayList.add(currentError);
                }
            }
            return arrayList;
        }

        public final void clearErrors$runtime() {
            Iterable iterable = (Iterable) Recomposer._runningRecomposers.getValue();
            ArrayList arrayList = new ArrayList();
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                RecomposerErrorState recomposerErrorStateResetErrorState = ((RecomposerInfoImpl) it.next()).resetErrorState();
                if (recomposerErrorStateResetErrorState != null) {
                    arrayList.add(recomposerErrorStateResetErrorState);
                }
            }
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public ScatterSet<RecomposeScopeImpl> composeInitialPaused$runtime(ControlledComposition composition, ShouldPauseCallback shouldPause, Function2<? super Composer, ? super Integer, Unit> content) {
        try {
            ShouldPauseCallback andSetShouldPauseCallback = composition.getAndSetShouldPauseCallback(shouldPause);
            try {
                composeInitial$runtime(composition, content);
                MutableScatterSet<RecomposeScopeImpl> mutableScatterSet = this.pausedScopes.get();
                MutableScatterSet<RecomposeScopeImpl> mutableScatterSetEmptyScatterSet = mutableScatterSet != null ? mutableScatterSet : ScatterSetKt.emptyScatterSet();
                composition.getAndSetShouldPauseCallback(andSetShouldPauseCallback);
                this.pausedScopes.set(null);
                return mutableScatterSetEmptyScatterSet;
            } catch (Throwable th) {
                composition.getAndSetShouldPauseCallback(andSetShouldPauseCallback);
                throw th;
            }
        } catch (Throwable th2) {
            this.pausedScopes.set(null);
            throw th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<ControlledComposition> performInsertValues(List<MovableContentStateReference> references, MutableScatterSet<Object> modifiedValues) {
        Iterator it;
        HashMap map;
        ArrayList arrayList;
        NestedMovableContent nestedMovableContentRemoveLast;
        HashMap map2 = new HashMap(references.size());
        int size = references.size();
        for (int i = 0; i < size; i++) {
            MovableContentStateReference movableContentStateReference = references.get(i);
            ControlledComposition composition = movableContentStateReference.getComposition();
            HashMap map3 = map2;
            Object arrayList2 = map3.get(composition);
            if (arrayList2 == null) {
                arrayList2 = new ArrayList();
                map3.put(composition, arrayList2);
            }
            ((ArrayList) arrayList2).add(movableContentStateReference);
        }
        HashMap map4 = map2;
        Iterator it2 = map4.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it2.next();
            ControlledComposition controlledComposition = (ControlledComposition) entry.getKey();
            List list = (List) entry.getValue();
            if (controlledComposition.isComposing()) {
                ComposerKt.composeImmediateRuntimeError("Check failed");
            }
            MutableSnapshot mutableSnapshotTakeMutableSnapshot = Snapshot.INSTANCE.takeMutableSnapshot(readObserverOf(controlledComposition), writeObserverOf(controlledComposition, modifiedValues));
            try {
                MutableSnapshot mutableSnapshot = mutableSnapshotTakeMutableSnapshot;
                Snapshot snapshotMakeCurrent = mutableSnapshot.makeCurrent();
                try {
                    synchronized (this.stateLock) {
                        ArrayList arrayList3 = new ArrayList(list.size());
                        int size2 = list.size();
                        int i2 = 0;
                        while (i2 < size2) {
                            ArrayList arrayList4 = arrayList3;
                            MovableContentStateReference movableContentStateReference2 = (MovableContentStateReference) list.get(i2);
                            Iterator it3 = it2;
                            Object objM3543removeLastimpl = MultiValueMap.m3543removeLastimpl(this.movableContentRemoved, movableContentStateReference2.getContent$runtime());
                            MovableContentStateReference movableContentStateReference3 = (MovableContentStateReference) objM3543removeLastimpl;
                            if (movableContentStateReference3 != null) {
                                this.movableContentNestedStatesAvailable.usedContainer(movableContentStateReference3);
                            }
                            arrayList4.add(TuplesKt.to(movableContentStateReference2, objM3543removeLastimpl));
                            i2++;
                            it2 = it3;
                            map4 = map4;
                        }
                        it = it2;
                        map = map4;
                        arrayList = arrayList3;
                        if (ComposeRuntimeFlags.isMovingNestedMovableContentEnabled) {
                            int size3 = arrayList.size();
                            for (int i3 = 0; i3 < size3; i3++) {
                                Pair<MovableContentStateReference, MovableContentStateReference> pair = arrayList.get(i3);
                                if (pair.getSecond() == null && this.movableContentNestedStatesAvailable.contains(pair.getFirst().getContent$runtime())) {
                                    ArrayList<Pair> arrayList5 = arrayList;
                                    ArrayList arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList5, 10));
                                    for (Pair pair2 : arrayList5) {
                                        if (pair2.getSecond() == null && (nestedMovableContentRemoveLast = this.movableContentNestedStatesAvailable.removeLast(((MovableContentStateReference) pair2.getFirst()).getContent$runtime())) != null) {
                                            MovableContentStateReference content = nestedMovableContentRemoveLast.getContent();
                                            MultiValueMap.m3529addimpl(this.movableContentNestedExtractionsPending, nestedMovableContentRemoveLast.getContainer(), content);
                                            pair2 = TuplesKt.to(pair2.getFirst(), content);
                                        }
                                        arrayList6.add(pair2);
                                    }
                                    arrayList = arrayList6;
                                    break;
                                }
                            }
                        }
                    }
                    int size4 = arrayList.size();
                    for (int i4 = 0; i4 < size4; i4++) {
                        if (arrayList.get(i4).getSecond() != null) {
                            int size5 = arrayList.size();
                            for (int i5 = 0; i5 < size5; i5++) {
                                if (arrayList.get(i5).getSecond() == null) {
                                    ArrayList arrayList7 = new ArrayList(arrayList.size());
                                    int size6 = arrayList.size();
                                    for (int i6 = 0; i6 < size6; i6++) {
                                        Pair<MovableContentStateReference, MovableContentStateReference> pair3 = arrayList.get(i6);
                                        MovableContentStateReference first = pair3.getSecond() == null ? pair3.getFirst() : null;
                                        if (first != null) {
                                            arrayList7.add(first);
                                        }
                                    }
                                    ArrayList arrayList8 = arrayList7;
                                    synchronized (this.stateLock) {
                                        CollectionsKt.addAll(this.movableContentAwaitingInsert, arrayList8);
                                        Unit unit = Unit.INSTANCE;
                                    }
                                    ArrayList arrayList9 = new ArrayList(arrayList.size());
                                    int size7 = arrayList.size();
                                    for (int i7 = 0; i7 < size7; i7++) {
                                        Pair<MovableContentStateReference, MovableContentStateReference> pair4 = arrayList.get(i7);
                                        if (pair4.getSecond() != null) {
                                            arrayList9.add(pair4);
                                        }
                                    }
                                    arrayList = arrayList9;
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    controlledComposition.insertMovableContent(arrayList);
                    Unit unit2 = Unit.INSTANCE;
                    mutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                    applyAndCheck(mutableSnapshotTakeMutableSnapshot);
                    it2 = it;
                    map4 = map;
                } catch (Throwable th) {
                    mutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                    throw th;
                }
            } catch (Throwable th2) {
                applyAndCheck(mutableSnapshotTakeMutableSnapshot);
                throw th2;
            }
        }
        return CollectionsKt.toList(map4.keySet());
    }
}
