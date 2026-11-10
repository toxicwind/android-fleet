package vendor.hisense.mw.hs_av_service.V1_0;

import b.a.a.a.a;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class SoundMode {
    public static final int SPORT = 2;
    public static final int STARDARN = 0;
    public static final int VIVID = 1;

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("STARDARN");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("VIVID");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("SPORT");
            i2 |= 2;
        }
        if (i != i2) {
            StringBuilder sbA = a.a("0x");
            sbA.append(Integer.toHexString(i & (~i2)));
            arrayList.add(sbA.toString());
        }
        return String.join(" | ", arrayList);
    }

    public static final String toString(int i) {
        if (i == 0) {
            return "STARDARN";
        }
        if (i == 1) {
            return "VIVID";
        }
        if (i == 2) {
            return "SPORT";
        }
        StringBuilder sbA = a.a("0x");
        sbA.append(Integer.toHexString(i));
        return sbA.toString();
    }
}
