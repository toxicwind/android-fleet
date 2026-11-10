package com.ju.lib.utils.base.persistence;

import android.os.Parcel;
import android.os.Parcelable;
import com.ju.lib.utils.log.LogUtil;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class ParcelablePersistence {
    public static final String TAG = "ParcelablePersistence";

    public <T extends Parcelable> T read(String str, ClassLoader classLoader) {
        return (T) read(new File(str), classLoader);
    }

    public boolean write(String str, Object obj) {
        return write(new File(str), obj);
    }

    public <T extends Parcelable> T read(File file, ClassLoader classLoader) {
        T t = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            Parcel parcelObtain = Parcel.obtain();
            parcelObtain.unmarshall(bArr, 0, bArr.length);
            parcelObtain.setDataPosition(0);
            t = (T) parcelObtain.readParcelable(classLoader);
            fileInputStream.close();
            return t;
        } catch (Exception e2) {
            e2.printStackTrace();
            return t;
        }
    }

    public boolean write(File file, Object obj) {
        if (!(obj instanceof Parcelable)) {
            LogUtil.w(TAG, "write(File path, Object bean)  ,", obj, "  is not Parcelable");
            return false;
        }
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        LogUtil.d(TAG, "write(File path, Object bean),  bean : ", obj, ",  path : ", file);
        Parcelable parcelable = (Parcelable) obj;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file, false);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            Parcel parcelObtain = Parcel.obtain();
            parcelObtain.writeParcelable(parcelable, 0);
            bufferedOutputStream.write(parcelObtain.marshall());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (Exception e3) {
            e3.printStackTrace();
            LogUtil.e(TAG, "write(File path, Object bean)  e : ", e3.getMessage());
            return false;
        }
    }
}
