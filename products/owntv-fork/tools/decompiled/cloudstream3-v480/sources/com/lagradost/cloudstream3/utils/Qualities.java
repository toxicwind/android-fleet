package com.lagradost.cloudstream3.utils;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: ExtractorApi.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0013\b\u0086\u0081\u0002\u0018\u0000 \u00152\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0015B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014¨\u0006\u0016"}, d2 = {"Lcom/lagradost/cloudstream3/utils/Qualities;", "", ES6Iterator.VALUE_PROPERTY, "", "defaultPriority", "<init>", "(Ljava/lang/String;III)V", "getValue", "()I", "setValue", "(I)V", "getDefaultPriority", "Unknown", "P144", "P240", "P360", "P480", "P720", "P1080", "P1440", "P2160", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public enum Qualities {
    Unknown(400, 4),
    P144(144, 0),
    P240(240, 2),
    P360(360, 3),
    P480(480, 4),
    P720(720, 5),
    P1080(1080, 6),
    P1440(1440, 7),
    P2160(2160, 8);

    private final int defaultPriority;
    private int value;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public static EnumEntries<Qualities> getEntries() {
        return $ENTRIES;
    }

    Qualities(int i, int i2) {
        this.value = i;
        this.defaultPriority = i2;
    }

    public final int getDefaultPriority() {
        return this.defaultPriority;
    }

    public final int getValue() {
        return this.value;
    }

    public final void setValue(int i) {
        this.value = i;
    }

    /* JADX INFO: compiled from: ExtractorApi.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0007¨\u0006\u000b"}, d2 = {"Lcom/lagradost/cloudstream3/utils/Qualities$Companion;", "", "<init>", "()V", "getStringByInt", "", "qual", "", "(Ljava/lang/Integer;)Ljava/lang/String;", "getStringByIntFull", "quality", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getStringByInt(Integer qual) {
            if (qual != null && qual.intValue() == 0) {
                return "Auto";
            }
            int value = Qualities.Unknown.getValue();
            if (qual != null && qual.intValue() == value) {
                return "";
            }
            int value2 = Qualities.P2160.getValue();
            if (qual != null && qual.intValue() == value2) {
                return "4K";
            }
            if (qual == null) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            sb.append(qual.intValue());
            sb.append('p');
            return sb.toString();
        }

        public final String getStringByIntFull(int quality) {
            if (quality == 0) {
                return "Auto";
            }
            if (quality == Qualities.Unknown.getValue()) {
                return "Unknown";
            }
            if (quality == Qualities.P2160.getValue()) {
                return "4K";
            }
            StringBuilder sb = new StringBuilder();
            sb.append(quality);
            sb.append('p');
            return sb.toString();
        }
    }
}
