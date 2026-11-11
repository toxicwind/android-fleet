package androidx.compose.runtime.tooling;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: SourceInformation.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u000e"}, d2 = {"Landroidx/compose/runtime/tooling/ParameterSourceInformation;", "", "sortedIndex", "", "name", "", "inlineClass", "<init>", "(ILjava/lang/String;Ljava/lang/String;)V", "getSortedIndex", "()I", "getName", "()Ljava/lang/String;", "getInlineClass", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ParameterSourceInformation {
    public static final int $stable = 0;
    private final String inlineClass;
    private final String name;
    private final int sortedIndex;

    public ParameterSourceInformation(int i, String str, String str2) {
        this.sortedIndex = i;
        this.name = str;
        this.inlineClass = str2;
    }

    public /* synthetic */ ParameterSourceInformation(int i, String str, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? null : str, (i2 & 4) != 0 ? null : str2);
    }

    public final int getSortedIndex() {
        return this.sortedIndex;
    }

    public final String getName() {
        return this.name;
    }

    public final String getInlineClass() {
        return this.inlineClass;
    }
}
