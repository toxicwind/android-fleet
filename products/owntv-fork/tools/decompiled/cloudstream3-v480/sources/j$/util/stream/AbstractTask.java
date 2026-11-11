package j$.util.stream;

import j$.util.Spliterator;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.ForkJoinPool;

/* JADX INFO: loaded from: classes3.dex */
abstract class AbstractTask extends CountedCompleter {
    private static final int LEAF_TARGET = ForkJoinPool.getCommonPoolParallelism() << 2;
    protected final PipelineHelper helper;
    protected AbstractTask leftChild;
    private Object localResult;
    protected AbstractTask rightChild;
    protected Spliterator spliterator;
    protected long targetSize;

    protected abstract Object doLeaf();

    protected abstract AbstractTask makeChild(Spliterator spliterator);

    protected AbstractTask(PipelineHelper pipelineHelper, Spliterator spliterator) {
        super(null);
        this.helper = pipelineHelper;
        this.spliterator = spliterator;
        this.targetSize = 0L;
    }

    protected AbstractTask(AbstractTask abstractTask, Spliterator spliterator) {
        super(abstractTask);
        this.spliterator = spliterator;
        this.helper = abstractTask.helper;
        this.targetSize = abstractTask.targetSize;
    }

    public static int getLeafTarget() {
        return LEAF_TARGET;
    }

    public static long suggestTargetSize(long j) {
        long leafTarget = j / ((long) getLeafTarget());
        if (leafTarget > 0) {
            return leafTarget;
        }
        return 1L;
    }

    protected final long getTargetSize(long j) {
        long j2 = this.targetSize;
        if (j2 != 0) {
            return j2;
        }
        long jSuggestTargetSize = suggestTargetSize(j);
        this.targetSize = jSuggestTargetSize;
        return jSuggestTargetSize;
    }

    @Override // java.util.concurrent.CountedCompleter, java.util.concurrent.ForkJoinTask
    public Object getRawResult() {
        return this.localResult;
    }

    @Override // java.util.concurrent.CountedCompleter, java.util.concurrent.ForkJoinTask
    protected void setRawResult(Object obj) {
        if (obj != null) {
            throw new IllegalStateException();
        }
    }

    protected Object getLocalResult() {
        return this.localResult;
    }

    protected void setLocalResult(Object obj) {
        this.localResult = obj;
    }

    protected boolean isLeaf() {
        return this.leftChild == null;
    }

    protected boolean isRoot() {
        return getParent() == null;
    }

    protected AbstractTask getParent() {
        return (AbstractTask) getCompleter();
    }

    @Override // java.util.concurrent.CountedCompleter
    public void compute() {
        Spliterator spliteratorTrySplit;
        Spliterator spliterator = this.spliterator;
        long jEstimateSize = spliterator.estimateSize();
        long targetSize = getTargetSize(jEstimateSize);
        boolean z = false;
        while (jEstimateSize > targetSize && (spliteratorTrySplit = spliterator.trySplit()) != null) {
            AbstractTask abstractTaskMakeChild = this.makeChild(spliteratorTrySplit);
            this.leftChild = abstractTaskMakeChild;
            AbstractTask abstractTaskMakeChild2 = this.makeChild(spliterator);
            this.rightChild = abstractTaskMakeChild2;
            this.setPendingCount(1);
            if (z) {
                spliterator = spliteratorTrySplit;
                this = abstractTaskMakeChild;
                abstractTaskMakeChild = abstractTaskMakeChild2;
            } else {
                this = abstractTaskMakeChild2;
            }
            z = !z;
            abstractTaskMakeChild.fork();
            jEstimateSize = spliterator.estimateSize();
        }
        this.setLocalResult(this.doLeaf());
        this.tryComplete();
    }

    @Override // java.util.concurrent.CountedCompleter
    public void onCompletion(CountedCompleter countedCompleter) {
        this.spliterator = null;
        this.rightChild = null;
        this.leftChild = null;
    }

    protected boolean isLeftmostNode() {
        while (this != null) {
            AbstractTask parent = this.getParent();
            if (parent != null && parent.leftChild != this) {
                return false;
            }
            this = parent;
        }
        return true;
    }
}
