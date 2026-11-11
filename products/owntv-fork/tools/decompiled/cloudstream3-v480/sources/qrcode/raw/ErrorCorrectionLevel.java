package qrcode.raw;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: QRCodeEnums.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, d2 = {"Lqrcode/raw/ErrorCorrectionLevel;", "", ES6Iterator.VALUE_PROPERTY, "", "maxTypeNum", "<init>", "(Ljava/lang/String;III)V", "getValue", "()I", "getMaxTypeNum", "LOW", "MEDIUM", "HIGH", "VERY_HIGH", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public enum ErrorCorrectionLevel {
    LOW(1, 21),
    MEDIUM(0, 25),
    HIGH(3, 30),
    VERY_HIGH(2, 34);

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final int maxTypeNum;
    private final int value;

    public static EnumEntries<ErrorCorrectionLevel> getEntries() {
        return $ENTRIES;
    }

    ErrorCorrectionLevel(int i, int i2) {
        this.value = i;
        this.maxTypeNum = i2;
    }

    public final int getMaxTypeNum() {
        return this.maxTypeNum;
    }

    public final int getValue() {
        return this.value;
    }
}
