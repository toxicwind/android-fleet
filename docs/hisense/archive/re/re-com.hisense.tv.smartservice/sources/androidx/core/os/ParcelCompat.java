package androidx.core.os;

import android.os.Parcel;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public final class ParcelCompat {
    public static boolean readBoolean(@NonNull Parcel in) {
        return in.readInt() != 0;
    }

    public static void writeBoolean(@NonNull Parcel parcel, boolean z) {
        parcel.writeInt(z ? 1 : 0);
    }
}
