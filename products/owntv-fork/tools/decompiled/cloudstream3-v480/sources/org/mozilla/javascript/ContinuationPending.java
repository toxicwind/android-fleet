package org.mozilla.javascript;

/* JADX INFO: loaded from: classes8.dex */
public class ContinuationPending extends RuntimeException {
    private static final long serialVersionUID = 4956008116771118856L;
    private Object applicationState;
    private NativeContinuation continuationState;

    protected ContinuationPending(NativeContinuation nativeContinuation) {
        this.continuationState = nativeContinuation;
    }

    public Object getContinuation() {
        return this.continuationState;
    }

    public void setContinuation(NativeContinuation nativeContinuation) {
        this.continuationState = nativeContinuation;
    }

    NativeContinuation getContinuationState() {
        return this.continuationState;
    }

    public void setApplicationState(Object obj) {
        this.applicationState = obj;
    }

    public Object getApplicationState() {
        return this.applicationState;
    }
}
