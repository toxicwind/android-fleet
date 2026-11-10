package androidx.lifecycle;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.internal.FastSafeIterableMap;
import androidx.arch.core.internal.SafeIterableMap;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class LifecycleRegistry extends Lifecycle {
    public final WeakReference<LifecycleOwner> mLifecycleOwner;
    public FastSafeIterableMap<LifecycleObserver, ObserverWithState> mObserverMap = new FastSafeIterableMap<>();
    public int mAddingObserverCounter = 0;
    public boolean mHandlingEvent = false;
    public boolean mNewEventOccurred = false;
    public ArrayList<Lifecycle.State> mParentStates = new ArrayList<>();
    public Lifecycle.State mState = Lifecycle.State.INITIALIZED;

    /* JADX INFO: renamed from: androidx.lifecycle.LifecycleRegistry$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$androidx$lifecycle$Lifecycle$Event;
        public static final /* synthetic */ int[] $SwitchMap$androidx$lifecycle$Lifecycle$State;

        static {
            int[] iArr = new int[Lifecycle.State.values().length];
            $SwitchMap$androidx$lifecycle$Lifecycle$State = iArr;
            try {
                Lifecycle.State state = Lifecycle.State.INITIALIZED;
                iArr[1] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                int[] iArr2 = $SwitchMap$androidx$lifecycle$Lifecycle$State;
                Lifecycle.State state2 = Lifecycle.State.CREATED;
                iArr2[2] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                int[] iArr3 = $SwitchMap$androidx$lifecycle$Lifecycle$State;
                Lifecycle.State state3 = Lifecycle.State.STARTED;
                iArr3[3] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                int[] iArr4 = $SwitchMap$androidx$lifecycle$Lifecycle$State;
                Lifecycle.State state4 = Lifecycle.State.RESUMED;
                iArr4[4] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                int[] iArr5 = $SwitchMap$androidx$lifecycle$Lifecycle$State;
                Lifecycle.State state5 = Lifecycle.State.DESTROYED;
                iArr5[0] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr6 = new int[Lifecycle.Event.values().length];
            $SwitchMap$androidx$lifecycle$Lifecycle$Event = iArr6;
            try {
                Lifecycle.Event event = Lifecycle.Event.ON_CREATE;
                iArr6[0] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                int[] iArr7 = $SwitchMap$androidx$lifecycle$Lifecycle$Event;
                Lifecycle.Event event2 = Lifecycle.Event.ON_STOP;
                iArr7[4] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                int[] iArr8 = $SwitchMap$androidx$lifecycle$Lifecycle$Event;
                Lifecycle.Event event3 = Lifecycle.Event.ON_START;
                iArr8[1] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                int[] iArr9 = $SwitchMap$androidx$lifecycle$Lifecycle$Event;
                Lifecycle.Event event4 = Lifecycle.Event.ON_PAUSE;
                iArr9[3] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                int[] iArr10 = $SwitchMap$androidx$lifecycle$Lifecycle$Event;
                Lifecycle.Event event5 = Lifecycle.Event.ON_RESUME;
                iArr10[2] = 5;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                int[] iArr11 = $SwitchMap$androidx$lifecycle$Lifecycle$Event;
                Lifecycle.Event event6 = Lifecycle.Event.ON_DESTROY;
                iArr11[5] = 6;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                int[] iArr12 = $SwitchMap$androidx$lifecycle$Lifecycle$Event;
                Lifecycle.Event event7 = Lifecycle.Event.ON_ANY;
                iArr12[6] = 7;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    public static class ObserverWithState {
        public LifecycleEventObserver mLifecycleObserver;
        public Lifecycle.State mState;

        public ObserverWithState(LifecycleObserver lifecycleObserver, Lifecycle.State state) {
            this.mLifecycleObserver = Lifecycling.lifecycleEventObserver(lifecycleObserver);
            this.mState = state;
        }

        public void dispatchEvent(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            Lifecycle.State stateAfter = LifecycleRegistry.getStateAfter(event);
            this.mState = LifecycleRegistry.min(this.mState, stateAfter);
            this.mLifecycleObserver.onStateChanged(lifecycleOwner, event);
            this.mState = stateAfter;
        }
    }

    public LifecycleRegistry(@NonNull LifecycleOwner lifecycleOwner) {
        this.mLifecycleOwner = new WeakReference<>(lifecycleOwner);
    }

    private void backwardPass(LifecycleOwner lifecycleOwner) {
        Iterator<Map.Entry<LifecycleObserver, ObserverWithState>> itDescendingIterator = this.mObserverMap.descendingIterator();
        while (itDescendingIterator.hasNext() && !this.mNewEventOccurred) {
            Map.Entry<LifecycleObserver, ObserverWithState> next = itDescendingIterator.next();
            ObserverWithState value = next.getValue();
            while (value.mState.compareTo(this.mState) > 0 && !this.mNewEventOccurred && this.mObserverMap.contains(next.getKey())) {
                Lifecycle.Event eventDownEvent = downEvent(value.mState);
                pushParentState(getStateAfter(eventDownEvent));
                value.dispatchEvent(lifecycleOwner, eventDownEvent);
                popParentState();
            }
        }
    }

    private Lifecycle.State calculateTargetState(LifecycleObserver lifecycleObserver) {
        Map.Entry<LifecycleObserver, ObserverWithState> entryCeil = this.mObserverMap.ceil(lifecycleObserver);
        Lifecycle.State state = null;
        Lifecycle.State state2 = entryCeil != null ? entryCeil.getValue().mState : null;
        if (!this.mParentStates.isEmpty()) {
            ArrayList<Lifecycle.State> arrayList = this.mParentStates;
            state = arrayList.get(arrayList.size() - 1);
        }
        return min(min(this.mState, state2), state);
    }

    public static Lifecycle.Event downEvent(Lifecycle.State state) {
        int iOrdinal = state.ordinal();
        if (iOrdinal == 0) {
            throw new IllegalArgumentException();
        }
        if (iOrdinal == 1) {
            throw new IllegalArgumentException();
        }
        if (iOrdinal == 2) {
            return Lifecycle.Event.ON_DESTROY;
        }
        if (iOrdinal == 3) {
            return Lifecycle.Event.ON_STOP;
        }
        if (iOrdinal == 4) {
            return Lifecycle.Event.ON_PAUSE;
        }
        throw new IllegalArgumentException("Unexpected state value " + state);
    }

    private void forwardPass(LifecycleOwner lifecycleOwner) {
        SafeIterableMap<LifecycleObserver, ObserverWithState>.IteratorWithAdditions iteratorWithAdditions = this.mObserverMap.iteratorWithAdditions();
        while (iteratorWithAdditions.hasNext() && !this.mNewEventOccurred) {
            Map.Entry next = iteratorWithAdditions.next();
            ObserverWithState observerWithState = (ObserverWithState) next.getValue();
            while (observerWithState.mState.compareTo(this.mState) < 0 && !this.mNewEventOccurred && this.mObserverMap.contains((LifecycleObserver) next.getKey())) {
                pushParentState(observerWithState.mState);
                observerWithState.dispatchEvent(lifecycleOwner, upEvent(observerWithState.mState));
                popParentState();
            }
        }
    }

    public static Lifecycle.State getStateAfter(Lifecycle.Event event) {
        int iOrdinal = event.ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal != 1) {
                if (iOrdinal == 2) {
                    return Lifecycle.State.RESUMED;
                }
                if (iOrdinal != 3) {
                    if (iOrdinal != 4) {
                        if (iOrdinal == 5) {
                            return Lifecycle.State.DESTROYED;
                        }
                        throw new IllegalArgumentException("Unexpected event value " + event);
                    }
                }
            }
            return Lifecycle.State.STARTED;
        }
        return Lifecycle.State.CREATED;
    }

    private boolean isSynced() {
        if (this.mObserverMap.size() == 0) {
            return true;
        }
        Lifecycle.State state = this.mObserverMap.eldest().getValue().mState;
        Lifecycle.State state2 = this.mObserverMap.newest().getValue().mState;
        return state == state2 && this.mState == state2;
    }

    public static Lifecycle.State min(@NonNull Lifecycle.State state, @Nullable Lifecycle.State state2) {
        return (state2 == null || state2.compareTo(state) >= 0) ? state : state2;
    }

    private void moveToState(Lifecycle.State state) {
        if (this.mState == state) {
            return;
        }
        this.mState = state;
        if (this.mHandlingEvent || this.mAddingObserverCounter != 0) {
            this.mNewEventOccurred = true;
            return;
        }
        this.mHandlingEvent = true;
        sync();
        this.mHandlingEvent = false;
    }

    private void popParentState() {
        ArrayList<Lifecycle.State> arrayList = this.mParentStates;
        arrayList.remove(arrayList.size() - 1);
    }

    private void pushParentState(Lifecycle.State state) {
        this.mParentStates.add(state);
    }

    private void sync() {
        LifecycleOwner lifecycleOwner = this.mLifecycleOwner.get();
        if (lifecycleOwner == null) {
            throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is alreadygarbage collected. It is too late to change lifecycle state.");
        }
        while (!isSynced()) {
            this.mNewEventOccurred = false;
            if (this.mState.compareTo(this.mObserverMap.eldest().getValue().mState) < 0) {
                backwardPass(lifecycleOwner);
            }
            Map.Entry<LifecycleObserver, ObserverWithState> entryNewest = this.mObserverMap.newest();
            if (!this.mNewEventOccurred && entryNewest != null && this.mState.compareTo(entryNewest.getValue().mState) > 0) {
                forwardPass(lifecycleOwner);
            }
        }
        this.mNewEventOccurred = false;
    }

    public static Lifecycle.Event upEvent(Lifecycle.State state) {
        int iOrdinal = state.ordinal();
        if (iOrdinal == 0 || iOrdinal == 1) {
            return Lifecycle.Event.ON_CREATE;
        }
        if (iOrdinal == 2) {
            return Lifecycle.Event.ON_START;
        }
        if (iOrdinal == 3) {
            return Lifecycle.Event.ON_RESUME;
        }
        if (iOrdinal == 4) {
            throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException("Unexpected state value " + state);
    }

    @Override // androidx.lifecycle.Lifecycle
    public void addObserver(@NonNull LifecycleObserver lifecycleObserver) {
        LifecycleOwner lifecycleOwner;
        Lifecycle.State state = this.mState;
        Lifecycle.State state2 = Lifecycle.State.DESTROYED;
        if (state != state2) {
            state2 = Lifecycle.State.INITIALIZED;
        }
        ObserverWithState observerWithState = new ObserverWithState(lifecycleObserver, state2);
        if (this.mObserverMap.putIfAbsent(lifecycleObserver, observerWithState) == null && (lifecycleOwner = this.mLifecycleOwner.get()) != null) {
            boolean z = this.mAddingObserverCounter != 0 || this.mHandlingEvent;
            Lifecycle.State stateCalculateTargetState = calculateTargetState(lifecycleObserver);
            this.mAddingObserverCounter++;
            while (observerWithState.mState.compareTo(stateCalculateTargetState) < 0 && this.mObserverMap.contains(lifecycleObserver)) {
                pushParentState(observerWithState.mState);
                observerWithState.dispatchEvent(lifecycleOwner, upEvent(observerWithState.mState));
                popParentState();
                stateCalculateTargetState = calculateTargetState(lifecycleObserver);
            }
            if (!z) {
                sync();
            }
            this.mAddingObserverCounter--;
        }
    }

    @Override // androidx.lifecycle.Lifecycle
    @NonNull
    public Lifecycle.State getCurrentState() {
        return this.mState;
    }

    public int getObserverCount() {
        return this.mObserverMap.size();
    }

    public void handleLifecycleEvent(@NonNull Lifecycle.Event event) {
        moveToState(getStateAfter(event));
    }

    @MainThread
    @Deprecated
    public void markState(@NonNull Lifecycle.State state) {
        setCurrentState(state);
    }

    @Override // androidx.lifecycle.Lifecycle
    public void removeObserver(@NonNull LifecycleObserver lifecycleObserver) {
        this.mObserverMap.remove(lifecycleObserver);
    }

    @MainThread
    public void setCurrentState(@NonNull Lifecycle.State state) {
        moveToState(state);
    }
}
