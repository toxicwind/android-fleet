package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
@DoNotMock("Create an AbstractIdleService")
@GwtIncompatible
public interface Service {

    public static abstract class Listener {
        public void failed(State state, Throwable th) {
        }

        public void running() {
        }

        public void starting() {
        }

        public void stopping(State state) {
        }

        public void terminated(State state) {
        }
    }

    public enum State {
        NEW { // from class: com.google.common.util.concurrent.Service.State.1
            @Override // com.google.common.util.concurrent.Service.State
            public boolean isTerminal() {
                return false;
            }
        },
        STARTING { // from class: com.google.common.util.concurrent.Service.State.2
            @Override // com.google.common.util.concurrent.Service.State
            public boolean isTerminal() {
                return false;
            }
        },
        RUNNING { // from class: com.google.common.util.concurrent.Service.State.3
            @Override // com.google.common.util.concurrent.Service.State
            public boolean isTerminal() {
                return false;
            }
        },
        STOPPING { // from class: com.google.common.util.concurrent.Service.State.4
            @Override // com.google.common.util.concurrent.Service.State
            public boolean isTerminal() {
                return false;
            }
        },
        TERMINATED { // from class: com.google.common.util.concurrent.Service.State.5
            @Override // com.google.common.util.concurrent.Service.State
            public boolean isTerminal() {
                return true;
            }
        },
        FAILED { // from class: com.google.common.util.concurrent.Service.State.6
            @Override // com.google.common.util.concurrent.Service.State
            public boolean isTerminal() {
                return true;
            }
        };

        public abstract boolean isTerminal();

        /* synthetic */ State(AnonymousClass1 anonymousClass1) {
        }
    }

    void addListener(Listener listener, Executor executor);

    void awaitRunning();

    void awaitRunning(long j, TimeUnit timeUnit);

    void awaitTerminated();

    void awaitTerminated(long j, TimeUnit timeUnit);

    Throwable failureCause();

    boolean isRunning();

    @CanIgnoreReturnValue
    Service startAsync();

    State state();

    @CanIgnoreReturnValue
    Service stopAsync();
}
