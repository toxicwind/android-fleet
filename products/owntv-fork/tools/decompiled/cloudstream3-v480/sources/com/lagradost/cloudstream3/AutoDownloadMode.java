package com.lagradost.cloudstream3;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: MainAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\r"}, d2 = {"Lcom/lagradost/cloudstream3/AutoDownloadMode;", "", ES6Iterator.VALUE_PROPERTY, "", "<init>", "(Ljava/lang/String;II)V", "getValue", "()I", "Disable", "FilterByLang", "All", "NsfwOnly", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public enum AutoDownloadMode {
    Disable(0),
    FilterByLang(1),
    All(2),
    NsfwOnly(3);

    private final int value;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public static EnumEntries<AutoDownloadMode> getEntries() {
        return $ENTRIES;
    }

    AutoDownloadMode(int i) {
        this.value = i;
    }

    public final int getValue() {
        return this.value;
    }

    /* JADX INFO: compiled from: MainAPI.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0086\u0004¨\u0006\b"}, d2 = {"Lcom/lagradost/cloudstream3/AutoDownloadMode$Companion;", "", "<init>", "()V", "getEnum", "Lcom/lagradost/cloudstream3/AutoDownloadMode;", ES6Iterator.VALUE_PROPERTY, "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AutoDownloadMode getEnum(int value) {
            AutoDownloadMode next;
            Iterator<AutoDownloadMode> it = AutoDownloadMode.getEntries().iterator();
            while (it.hasNext()) {
                next = it.next();
                if (next.getValue() == value) {
                    return next;
                }
            }
            next = null;
            return next;
        }
    }
}
