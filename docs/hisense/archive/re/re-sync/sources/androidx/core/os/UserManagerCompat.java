package androidx.core.os;

import android.content.Context;
import android.os.UserManager;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public class UserManagerCompat {
    public static boolean isUserUnlocked(@NonNull Context context) {
        return ((UserManager) context.getSystemService(UserManager.class)).isUserUnlocked();
    }
}
