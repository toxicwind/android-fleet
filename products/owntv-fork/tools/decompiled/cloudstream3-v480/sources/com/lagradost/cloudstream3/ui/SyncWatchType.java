package com.lagradost.cloudstream3.ui;

import com.lagradost.cloudstream3.R;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: WatchType.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0011\b\u0086\u0081\u0002\u0018\u0000 \u00132\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0013B%\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012¨\u0006\u0014"}, d2 = {"Lcom/lagradost/cloudstream3/ui/SyncWatchType;", "", "internalId", "", "stringRes", "iconRes", "<init>", "(Ljava/lang/String;IIII)V", "getInternalId", "()I", "getStringRes", "getIconRes", "NONE", "WATCHING", "COMPLETED", "ONHOLD", "DROPPED", "PLANTOWATCH", "REWATCHING", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public enum SyncWatchType {
    NONE(-1, R.string.type_none, R.drawable.ic_baseline_add_24),
    WATCHING(0, R.string.type_watching, R.drawable.ic_baseline_bookmark_24),
    COMPLETED(1, R.string.type_completed, R.drawable.ic_baseline_bookmark_24),
    ONHOLD(2, R.string.type_on_hold, R.drawable.ic_baseline_bookmark_24),
    DROPPED(3, R.string.type_dropped, R.drawable.ic_baseline_bookmark_24),
    PLANTOWATCH(4, R.string.type_plan_to_watch, R.drawable.ic_baseline_bookmark_24),
    REWATCHING(5, R.string.type_re_watching, R.drawable.ic_baseline_bookmark_24);

    private final int iconRes;
    private final int internalId;
    private final int stringRes;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public static EnumEntries<SyncWatchType> getEntries() {
        return $ENTRIES;
    }

    SyncWatchType(int i, int i2, int i3) {
        this.internalId = i;
        this.stringRes = i2;
        this.iconRes = i3;
    }

    public final int getIconRes() {
        return this.iconRes;
    }

    public final int getInternalId() {
        return this.internalId;
    }

    public final int getStringRes() {
        return this.stringRes;
    }

    /* JADX INFO: compiled from: WatchType.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lcom/lagradost/cloudstream3/ui/SyncWatchType$Companion;", "", "<init>", "()V", "fromInternalId", "Lcom/lagradost/cloudstream3/ui/SyncWatchType;", "id", "", "(Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/ui/SyncWatchType;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SyncWatchType fromInternalId(Integer id) {
            SyncWatchType next;
            Iterator<SyncWatchType> it = SyncWatchType.getEntries().iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                int internalId = next.getInternalId();
                if (id != null && internalId == id.intValue()) {
                    break;
                }
            }
            SyncWatchType syncWatchType = next;
            return syncWatchType == null ? SyncWatchType.NONE : syncWatchType;
        }
    }
}
