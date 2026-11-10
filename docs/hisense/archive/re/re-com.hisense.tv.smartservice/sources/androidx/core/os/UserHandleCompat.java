package androidx.core.os;

import android.os.Build;
import android.os.UserHandle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(17)
public class UserHandleCompat {

    @Nullable
    public static Method sGetUserIdMethod;

    @Nullable
    public static Constructor<UserHandle> sUserHandleConstructor;

    @RequiresApi(24)
    private static class Api24Impl {
        @NonNull
        public static UserHandle getUserHandleForUid(int uid) {
            return UserHandle.getUserHandleForUid(uid);
        }
    }

    public static Method getGetUserIdMethod() {
        if (sGetUserIdMethod == null) {
            sGetUserIdMethod = UserHandle.class.getDeclaredMethod("getUserId", Integer.TYPE);
            sGetUserIdMethod.setAccessible(true);
        }
        return sGetUserIdMethod;
    }

    public static Constructor<UserHandle> getUserHandleConstructor() {
        if (sUserHandleConstructor == null) {
            sUserHandleConstructor = UserHandle.class.getDeclaredConstructor(Integer.TYPE);
            sUserHandleConstructor.setAccessible(true);
        }
        return sUserHandleConstructor;
    }

    @NonNull
    public static UserHandle getUserHandleForUid(int uid) {
        int i = Build.VERSION.SDK_INT;
        return UserHandle.getUserHandleForUid(uid);
    }
}
