package com.hippo.unifile;

import android.content.res.AssetFileDescriptor;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes6.dex */
final class TrickRandomAccessFile extends RandomAccessFile {
    private static final Field FIELD_FD;
    private static final Method METHOD_CLOSE;
    private static final String TAG = "TrickRandomAccessFile";
    private AssetFileDescriptor mAfd;
    private ParcelFileDescriptor mPfd;

    static {
        Field declaredField;
        Method method = null;
        try {
            declaredField = RandomAccessFile.class.getDeclaredField("fd");
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            Log.e(TAG, "Can't get field RandomAccessFile.fd : " + e);
            declaredField = null;
        }
        FIELD_FD = declaredField;
        try {
            method = Class.forName("libcore.io.IoUtils").getMethod("close", FileDescriptor.class);
        } catch (ClassNotFoundException e2) {
            Log.e(TAG, "Can't get class libcore.io.IoUtils: " + e2);
        } catch (NoSuchMethodException e3) {
            Log.e(TAG, "Can't get method libcore.io.IoUtils.close(FileDescriptor): " + e3);
        }
        METHOD_CLOSE = method;
    }

    private TrickRandomAccessFile(String str) throws FileNotFoundException {
        super("/dev/random", str);
    }

    @Override // java.io.RandomAccessFile, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        ParcelFileDescriptor parcelFileDescriptor = this.mPfd;
        if (parcelFileDescriptor != null) {
            parcelFileDescriptor.close();
            this.mPfd = null;
        }
        AssetFileDescriptor assetFileDescriptor = this.mAfd;
        if (assetFileDescriptor != null) {
            assetFileDescriptor.close();
            this.mAfd = null;
        }
        super.close();
    }

    private static void checkReflection() throws IOException {
        if (FIELD_FD == null || METHOD_CLOSE == null) {
            throw new IOException("Can't get reflection stuff");
        }
    }

    static RandomAccessFile create(ParcelFileDescriptor parcelFileDescriptor, String str) throws IOException {
        if (parcelFileDescriptor == null) {
            throw new IOException("ParcelFileDescriptor is null");
        }
        checkReflection();
        try {
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            if (fileDescriptor == null) {
                throw new IOException("Can't get FileDescriptor");
            }
            TrickRandomAccessFile trickRandomAccessFileCreate = create(fileDescriptor, str);
            trickRandomAccessFileCreate.mPfd = parcelFileDescriptor;
            return trickRandomAccessFileCreate;
        } catch (IOException e) {
            parcelFileDescriptor.close();
            throw e;
        }
    }

    static RandomAccessFile create(AssetFileDescriptor assetFileDescriptor, String str) throws IOException {
        if (assetFileDescriptor == null) {
            throw new IOException("AssetFileDescriptor is null");
        }
        checkReflection();
        try {
            FileDescriptor fileDescriptor = assetFileDescriptor.getFileDescriptor();
            if (fileDescriptor == null) {
                throw new IOException("Can't get FileDescriptor");
            }
            TrickRandomAccessFile trickRandomAccessFileCreate = create(fileDescriptor, str);
            trickRandomAccessFileCreate.mAfd = assetFileDescriptor;
            return trickRandomAccessFileCreate;
        } catch (IOException e) {
            assetFileDescriptor.close();
            throw e;
        }
    }

    private static TrickRandomAccessFile create(FileDescriptor fileDescriptor, String str) throws IOException {
        try {
            TrickRandomAccessFile trickRandomAccessFile = new TrickRandomAccessFile(str);
            try {
                Field field = FIELD_FD;
                Object obj = field.get(trickRandomAccessFile);
                if (obj instanceof FileDescriptor) {
                    METHOD_CLOSE.invoke(null, (FileDescriptor) obj);
                }
                try {
                    field.set(trickRandomAccessFile, fileDescriptor);
                    return trickRandomAccessFile;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    trickRandomAccessFile.close();
                    throw new IOException(e.getMessage());
                }
            } catch (IllegalAccessException e2) {
                Log.e(TAG, "Failed to invoke libcore.io.IoUtils.close(FileDescriptor): " + e2);
                trickRandomAccessFile.close();
                throw new IOException(e2.getMessage());
            } catch (InvocationTargetException e3) {
                Log.e(TAG, "Failed to invoke libcore.io.IoUtils.close(FileDescriptor): " + e3);
                trickRandomAccessFile.close();
                throw new IOException(e3.getMessage());
            }
        } catch (FileNotFoundException unused) {
            throw new IOException("Can't create TrickRandomAccessFile");
        }
    }
}
