package com.lagradost.cloudstream3.utils.videoskip;

import com.lagradost.cloudstream3.R;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: SkipAPI.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\r\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0013\b\u0002\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/SkipType;", "", "res", "", "<init>", "(Ljava/lang/String;II)V", "getRes", "()I", "Opening", "Ending", "Recap", "MixedOpening", "MixedEnding", "Credits", "Intro", "Preview", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public enum SkipType {
    Opening(R.string.skip_type_op),
    Ending(R.string.skip_type_ed),
    Recap(R.string.skip_type_recap),
    MixedOpening(R.string.skip_type_mixed_op),
    MixedEnding(R.string.skip_type_mixed_ed),
    Credits(R.string.skip_type_credits),
    Intro(R.string.skip_type_intro),
    Preview(R.string.skip_type_preview);

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final int res;

    public static EnumEntries<SkipType> getEntries() {
        return $ENTRIES;
    }

    SkipType(int i) {
        this.res = i;
    }

    public final int getRes() {
        return this.res;
    }
}
