package androidx.compose.runtime.tooling;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;

/* JADX INFO: compiled from: SourceInformation.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000e¨\u0006\u000f"}, d2 = {"Landroidx/compose/runtime/tooling/LocationSourceInformation;", "", "lineNumber", "", TypedValues.CycleType.S_WAVE_OFFSET, "length", "isRepeatable", "", "<init>", "(IIIZ)V", "getLineNumber", "()I", "getOffset", "getLength", "()Z", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class LocationSourceInformation {
    public static final int $stable = 0;
    private final boolean isRepeatable;
    private final int length;
    private final int lineNumber;
    private final int offset;

    public LocationSourceInformation(int i, int i2, int i3, boolean z) {
        this.lineNumber = i;
        this.offset = i2;
        this.length = i3;
        this.isRepeatable = z;
    }

    public final int getLineNumber() {
        return this.lineNumber;
    }

    public final int getOffset() {
        return this.offset;
    }

    public final int getLength() {
        return this.length;
    }

    /* JADX INFO: renamed from: isRepeatable, reason: from getter */
    public final boolean getIsRepeatable() {
        return this.isRepeatable;
    }
}
