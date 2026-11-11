package j$.util.stream;

import j$.util.Spliterator;
import j$.util.concurrent.ConcurrentHashMap$SearchEntriesTask$0;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes3.dex */
abstract class AbstractShortCircuitTask extends AbstractTask {
    protected volatile boolean canceled;
    protected final AtomicReference sharedResult;

    protected abstract Object getEmptyResult();

    protected AbstractShortCircuitTask(PipelineHelper pipelineHelper, Spliterator spliterator) {
        super(pipelineHelper, spliterator);
        this.sharedResult = new AtomicReference(null);
    }

    protected AbstractShortCircuitTask(AbstractShortCircuitTask abstractShortCircuitTask, Spliterator spliterator) {
        super(abstractShortCircuitTask, spliterator);
        this.sharedResult = abstractShortCircuitTask.sharedResult;
    }

    @Override // j$.util.stream.AbstractTask, java.util.concurrent.CountedCompleter
    public void compute() {
        Object emptyResult;
        Spliterator spliteratorTrySplit;
        Spliterator spliterator = this.spliterator;
        long jEstimateSize = spliterator.estimateSize();
        long targetSize = getTargetSize(jEstimateSize);
        AtomicReference atomicReference = this.sharedResult;
        boolean z = false;
        while (true) {
            emptyResult = atomicReference.get();
            if (emptyResult != null) {
                break;
            }
            if (this.taskCanceled()) {
                emptyResult = this.getEmptyResult();
                break;
            }
            if (jEstimateSize <= targetSize || (spliteratorTrySplit = spliterator.trySplit()) == null) {
                emptyResult = this.doLeaf();
                break;
            }
            AbstractShortCircuitTask abstractShortCircuitTask = (AbstractShortCircuitTask) this.makeChild(spliteratorTrySplit);
            this.leftChild = abstractShortCircuitTask;
            AbstractShortCircuitTask abstractShortCircuitTask2 = (AbstractShortCircuitTask) this.makeChild(spliterator);
            this.rightChild = abstractShortCircuitTask2;
            this.setPendingCount(1);
            if (z) {
                spliterator = spliteratorTrySplit;
                this = abstractShortCircuitTask;
                abstractShortCircuitTask = abstractShortCircuitTask2;
            } else {
                this = abstractShortCircuitTask2;
            }
            z = !z;
            abstractShortCircuitTask.fork();
            jEstimateSize = spliterator.estimateSize();
        }
        this.setLocalResult(emptyResult);
        this.tryComplete();
    }

    protected void shortCircuit(Object obj) {
        if (obj != null) {
            ConcurrentHashMap$SearchEntriesTask$0.m(this.sharedResult, null, obj);
        }
    }

    @Override // j$.util.stream.AbstractTask
    protected void setLocalResult(Object obj) {
        if (!isRoot()) {
            super.setLocalResult(obj);
        } else if (obj != null) {
            ConcurrentHashMap$SearchEntriesTask$0.m(this.sharedResult, null, obj);
        }
    }

    @Override // j$.util.stream.AbstractTask, java.util.concurrent.CountedCompleter, java.util.concurrent.ForkJoinTask
    public Object getRawResult() {
        return getLocalResult();
    }

    @Override // j$.util.stream.AbstractTask
    public Object getLocalResult() {
        if (isRoot()) {
            Object obj = this.sharedResult.get();
            return obj == null ? getEmptyResult() : obj;
        }
        return super.getLocalResult();
    }

    protected void cancel() {
        this.canceled = true;
    }

    protected boolean taskCanceled() {
        boolean z = this.canceled;
        if (!z) {
            while (true) {
                this = (AbstractShortCircuitTask) this.getParent();
                if (z || this == null) {
                    break;
                }
                z = this.canceled;
            }
        }
        return z;
    }

    protected void cancelLaterNodes() {
        AbstractTask parent = getParent();
        while (true) {
            AbstractShortCircuitTask abstractShortCircuitTask = (AbstractShortCircuitTask) parent;
            AbstractShortCircuitTask abstractShortCircuitTask2 = this;
            this = abstractShortCircuitTask;
            if (this == null) {
                return;
            }
            if (this.leftChild == abstractShortCircuitTask2) {
                AbstractShortCircuitTask abstractShortCircuitTask3 = (AbstractShortCircuitTask) this.rightChild;
                if (!abstractShortCircuitTask3.canceled) {
                    abstractShortCircuitTask3.cancel();
                }
            }
            parent = this.getParent();
        }
    }
}
