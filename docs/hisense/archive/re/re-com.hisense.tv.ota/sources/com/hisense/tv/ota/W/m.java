package com.hisense.tv.ota.W;

import android.text.TextUtils;
import android.util.Log;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class m {
    public static void a(File file) {
        if (file == null) {
            return;
        }
        Log.i("Utils", String.format("Path:[%s]  CanExecute:[%b]  CanWrite:[%b]  CanRead:[%b]", file.getPath(), Boolean.valueOf(file.canExecute()), Boolean.valueOf(file.canWrite()), Boolean.valueOf(file.canRead())));
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return new File(str).exists();
        } catch (Exception e) {
            Log.e("Utils", "isFileExist exception", e);
            return false;
        }
    }
}
