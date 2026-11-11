package kotlinx.datetime.internal.format.parser;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Parser.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u0002B/\u0012\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004\u0012\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00000\u0004¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\f\u001a\u00020\rH\u0016R\u001d\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n¨\u0006\u000e"}, d2 = {"Lkotlinx/datetime/internal/format/parser/ParserStructure;", "Output", "", "operations", "", "Lkotlinx/datetime/internal/format/parser/ParserOperation;", "followedBy", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "getOperations", "()Ljava/util/List;", "getFollowedBy", "toString", "", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ParserStructure<Output> {
    private final List<ParserStructure<Output>> followedBy;
    private final List<ParserOperation<Output>> operations;

    /* JADX WARN: Multi-variable type inference failed */
    public ParserStructure(List<? extends ParserOperation<? super Output>> operations, List<? extends ParserStructure<? super Output>> followedBy) {
        Intrinsics.checkNotNullParameter(operations, "operations");
        Intrinsics.checkNotNullParameter(followedBy, "followedBy");
        this.operations = operations;
        this.followedBy = followedBy;
    }

    public final List<ParserOperation<Output>> getOperations() {
        return this.operations;
    }

    public final List<ParserStructure<Output>> getFollowedBy() {
        return this.followedBy;
    }

    public String toString() {
        return CollectionsKt.joinToString$default(this.operations, ", ", null, null, 0, null, null, 62, null) + '(' + CollectionsKt.joinToString$default(this.followedBy, ";", null, null, 0, null, null, 62, null) + ')';
    }
}
