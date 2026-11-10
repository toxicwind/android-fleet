package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public abstract class AbstractIterator<T> extends UnmodifiableIterator<T> {
    public T next;
    public State state = State.NOT_READY;

    /* JADX INFO: renamed from: com.google.common.collect.AbstractIterator$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$common$collect$AbstractIterator$State = new int[State.values().length];

        static {
            try {
                $SwitchMap$com$google$common$collect$AbstractIterator$State[State.DONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$common$collect$AbstractIterator$State[State.READY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private enum State {
        READY,
        NOT_READY,
        DONE,
        FAILED
    }

    private boolean tryToComputeNext() {
        this.state = State.FAILED;
        this.next = computeNext();
        if (this.state == State.DONE) {
            return false;
        }
        this.state = State.READY;
        return true;
    }

    public abstract T computeNext();

    @CanIgnoreReturnValue
    public final T endOfData() {
        this.state = State.DONE;
        return null;
    }

    @Override // java.util.Iterator
    @CanIgnoreReturnValue
    public final boolean hasNext() {
        Preconditions.checkState(this.state != State.FAILED);
        int iOrdinal = this.state.ordinal();
        if (iOrdinal == 0) {
            return true;
        }
        if (iOrdinal != 2) {
            return tryToComputeNext();
        }
        return false;
    }

    @Override // java.util.Iterator
    @CanIgnoreReturnValue
    public final T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.state = State.NOT_READY;
        T t = this.next;
        this.next = null;
        return t;
    }

    public final T peek() {
        if (hasNext()) {
            return this.next;
        }
        throw new NoSuchElementException();
    }
}
