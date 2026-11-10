package com.google.firebase.remoteconfig.internal;

import android.content.Context;
import androidx.annotation.AnyThread;
import androidx.annotation.GuardedBy;
import androidx.annotation.VisibleForTesting;
import e.b.b;
import e.b.d;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@AnyThread
public class ConfigStorageClient {
    public static final String JSON_STRING_ENCODING = "UTF-8";

    @GuardedBy("ConfigStorageClient.class")
    public static final Map<String, ConfigStorageClient> clientInstances = new HashMap();
    public final Context context;
    public final String fileName;

    public ConfigStorageClient(Context context, String str) {
        this.context = context;
        this.fileName = str;
    }

    @VisibleForTesting
    public static synchronized void clearInstancesForTest() {
        clientInstances.clear();
    }

    public static synchronized ConfigStorageClient getInstance(Context context, String str) {
        if (!clientInstances.containsKey(str)) {
            clientInstances.put(str, new ConfigStorageClient(context, str));
        }
        return clientInstances.get(str);
    }

    public synchronized Void clear() {
        this.context.deleteFile(this.fileName);
        return null;
    }

    public String getFileName() {
        return this.fileName;
    }

    public synchronized ConfigContainer read() {
        FileInputStream fileInputStreamOpenFileInput;
        Throwable th;
        try {
            try {
                fileInputStreamOpenFileInput = this.context.openFileInput(this.fileName);
                try {
                    byte[] bArr = new byte[fileInputStreamOpenFileInput.available()];
                    fileInputStreamOpenFileInput.read(bArr, 0, bArr.length);
                    ConfigContainer configContainerCopyOf = ConfigContainer.copyOf(new d(new String(bArr, "UTF-8")));
                    fileInputStreamOpenFileInput.close();
                    return configContainerCopyOf;
                } catch (b | FileNotFoundException unused) {
                    if (fileInputStreamOpenFileInput != null) {
                        fileInputStreamOpenFileInput.close();
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (fileInputStreamOpenFileInput != null) {
                        fileInputStreamOpenFileInput.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                throw th3;
            }
        } catch (b | FileNotFoundException unused2) {
            fileInputStreamOpenFileInput = null;
        } catch (Throwable th4) {
            fileInputStreamOpenFileInput = null;
            th = th4;
        }
    }

    public synchronized Void write(ConfigContainer configContainer) {
        FileOutputStream fileOutputStreamOpenFileOutput = this.context.openFileOutput(this.fileName, 0);
        try {
            fileOutputStreamOpenFileOutput.write(configContainer.toString().getBytes("UTF-8"));
            fileOutputStreamOpenFileOutput.close();
        } catch (Throwable th) {
            fileOutputStreamOpenFileOutput.close();
            throw th;
        }
        return null;
    }
}
