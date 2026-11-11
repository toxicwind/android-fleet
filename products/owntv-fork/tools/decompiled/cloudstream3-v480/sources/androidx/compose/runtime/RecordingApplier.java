package androidx.compose.runtime;

import androidx.collection.MutableIntList;
import androidx.collection.MutableObjectList;
import androidx.compose.runtime.internal.RememberEventDispatcher;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.mozilla.javascript.ES6Iterator;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;

/* JADX INFO: compiled from: PausableComposition.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u0000 **\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001*B\u000f\u0012\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0005J\b\u0010\u0013\u001a\u00020\u0011H\u0016J\u0018\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0016J \u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0016J\b\u0010\u001b\u001a\u00020\u0011H\u0016J\u001d\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001eJ\u001d\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001eJ3\u0010 \u001a\u00020\u00112\u001f\u0010!\u001a\u001b\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\n\u0012\u0004\u0012\u00020\u00110\"¢\u0006\u0002\b#2\b\u0010$\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010%\u001a\u00020\u0011H\u0016J\u001c\u0010&\u001a\u00020\u00112\f\u0010'\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010(\u001a\u00020)R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u00028\u0000X\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u0005¨\u0006+"}, d2 = {"Landroidx/compose/runtime/RecordingApplier;", "N", "Landroidx/compose/runtime/Applier;", "root", "<init>", "(Ljava/lang/Object;)V", "operations", "Landroidx/collection/MutableIntList;", "instances", "Landroidx/collection/MutableObjectList;", "", "current", "getCurrent", "()Ljava/lang/Object;", "setCurrent", "Ljava/lang/Object;", "down", "", "node", "up", "remove", "index", "", PeertubeParsingHelper.COUNT_KEY, "move", TypedValues.TransitionType.S_FROM, TypedValues.TransitionType.S_TO, "clear", "insertBottomUp", "instance", "(ILjava/lang/Object;)V", "insertTopDown", "apply", "block", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", ES6Iterator.VALUE_PROPERTY, "reuse", "playTo", "applier", "rememberManager", "Landroidx/compose/runtime/internal/RememberEventDispatcher;", "Companion", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class RecordingApplier<N> implements Applier<N> {
    public static final int APPLY = 7;
    public static final int CLEAR = 4;
    public static final int DOWN = 1;
    public static final int INSERT_BOTTOM_UP = 5;
    public static final int INSERT_TOP_DOWN = 6;
    public static final int MOVE = 3;
    public static final int REMOVE = 2;
    public static final int REUSE = 8;
    public static final int UP = 0;
    private N current;
    public static final int $stable = 8;
    private final MutableIntList operations = new MutableIntList(0, 1, null);
    private final MutableObjectList<Object> instances = new MutableObjectList<>(0, 1, null);

    @Override // androidx.compose.runtime.Applier
    public /* synthetic */ void onBeginChanges() {
        Applier.CC.$default$onBeginChanges(this);
    }

    @Override // androidx.compose.runtime.Applier
    public /* synthetic */ void onEndChanges() {
        Applier.CC.$default$onEndChanges(this);
    }

    public RecordingApplier(N n) {
        this.current = n;
    }

    @Override // androidx.compose.runtime.Applier
    public N getCurrent() {
        return this.current;
    }

    public void setCurrent(N n) {
        this.current = n;
    }

    @Override // androidx.compose.runtime.Applier
    public void down(N node) {
        this.operations.add(1);
        this.instances.add(node);
    }

    @Override // androidx.compose.runtime.Applier
    public void up() {
        this.operations.add(0);
    }

    @Override // androidx.compose.runtime.Applier
    public void remove(int index, int count) {
        this.operations.add(2);
        this.operations.add(index);
        this.operations.add(count);
    }

    @Override // androidx.compose.runtime.Applier
    public void move(int from, int to, int count) {
        this.operations.add(3);
        this.operations.add(from);
        this.operations.add(to);
        this.operations.add(count);
    }

    @Override // androidx.compose.runtime.Applier
    public void clear() {
        this.operations.add(4);
    }

    @Override // androidx.compose.runtime.Applier
    public void insertBottomUp(int index, N instance) {
        this.operations.add(5);
        this.operations.add(index);
        this.instances.add(instance);
    }

    @Override // androidx.compose.runtime.Applier
    public void insertTopDown(int index, N instance) {
        this.operations.add(6);
        this.operations.add(index);
        this.instances.add(instance);
    }

    @Override // androidx.compose.runtime.Applier
    public void apply(Function2<? super N, Object, Unit> block, Object value) {
        this.operations.add(7);
        this.instances.add(block);
        this.instances.add(value);
    }

    @Override // androidx.compose.runtime.Applier
    public void reuse() {
        this.operations.add(8);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void playTo(Applier<N> applier, RememberEventDispatcher rememberManager) {
        Exception exc;
        int i;
        MutableIntList mutableIntList = this.operations;
        int i2 = mutableIntList._size;
        MutableObjectList<Object> mutableObjectList = this.instances;
        MutableObjectList mutableObjectList2 = new MutableObjectList(0, 1, null);
        applier.onBeginChanges();
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            int i5 = i3 + 1;
            try {
                try {
                    switch (mutableIntList.get(i3)) {
                        case 0:
                            applier.up();
                            i3 = i5;
                            break;
                        case 1:
                            int i6 = i4 + 1;
                            applier.down(mutableObjectList.get(i4));
                            i4 = i6;
                            i3 = i5;
                            break;
                        case 2:
                            int i7 = i3 + 2;
                            i3 += 3;
                            applier.remove(mutableIntList.get(i5), mutableIntList.get(i7));
                            break;
                        case 3:
                            int i8 = i3 + 2;
                            try {
                                int i9 = i3 + 3;
                                try {
                                    i3 += 4;
                                    applier.move(mutableIntList.get(i5), mutableIntList.get(i8), mutableIntList.get(i9));
                                } catch (Exception e) {
                                    exc = e;
                                    i5 = i9;
                                    throw new ComposePausableCompositionException(mutableObjectList, mutableObjectList2, mutableIntList, i5, exc);
                                }
                            } catch (Exception e2) {
                                exc = e2;
                                i5 = i8;
                            }
                            break;
                        case 4:
                            applier.clear();
                            i3 = i5;
                            break;
                        case 5:
                            i3 += 2;
                            i = i4 + 1;
                            applier.insertBottomUp(mutableIntList.get(i5), mutableObjectList.get(i4));
                            i4 = i;
                            break;
                        case 6:
                            i3 += 2;
                            try {
                                i = i4 + 1;
                                applier.insertTopDown(mutableIntList.get(i5), mutableObjectList.get(i4));
                                i4 = i;
                            } catch (Exception e3) {
                                exc = e3;
                                i5 = i3;
                                throw new ComposePausableCompositionException(mutableObjectList, mutableObjectList2, mutableIntList, i5, exc);
                            }
                            break;
                        case 7:
                            int i10 = i4 + 1;
                            Object obj = mutableObjectList.get(i4);
                            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type @[ExtensionFunctionType] kotlin.Function2<kotlin.Any?, kotlin.Any?, kotlin.Unit>");
                            i4 += 2;
                            applier.apply((Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(obj, 2), mutableObjectList.get(i10));
                            i3 = i5;
                            break;
                        case 8:
                            Object current = applier.getCurrent();
                            if (current instanceof ComposeNodeLifecycleCallback) {
                                rememberManager.dispatchOnDeactivateIfNecessary((ComposeNodeLifecycleCallback) current);
                            }
                            mutableObjectList2.add(current);
                            applier.reuse();
                            i3 = i5;
                            break;
                        default:
                            i3 = i5;
                            break;
                    }
                } catch (Exception e4) {
                    exc = e4;
                }
            } catch (Throwable th) {
                applier.onEndChanges();
                throw th;
            }
        }
        if (!(i4 == mutableObjectList.getSize())) {
            ComposerKt.composeImmediateRuntimeError("Applier operation size mismatch");
        }
        mutableObjectList.clear();
        mutableIntList.clear();
        applier.onEndChanges();
    }
}
