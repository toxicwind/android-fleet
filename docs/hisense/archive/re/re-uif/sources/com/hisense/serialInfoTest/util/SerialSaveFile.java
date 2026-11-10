package com.hisense.serialInfoTest.util;

import android.content.Context;
import android.util.Log;
import java.io.FileWriter;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class SerialSaveFile {
    public FileWriter mFileWriter = null;

    public void initFileWriter(Context context, String str) {
        if (this.mFileWriter == null) {
            try {
                this.mFileWriter = new FileWriter(context.getFilesDir().getPath() + "/" + str);
            } catch (IOException e) {
                Log.d("Fac_SerialSaveFile", "init FileWriter failed");
                e.printStackTrace();
            }
        }
    }

    public void saveResult(char c) {
        FileWriter fileWriter = this.mFileWriter;
        if (fileWriter == null) {
            Log.i("Fac_SerialSaveFile", "mFileWriter is null");
            return;
        }
        try {
            fileWriter.append(c);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("Fac_SerialSaveFile", "setting boolean result error");
        }
    }

    public void closeWriter() {
        FileWriter fileWriter = this.mFileWriter;
        if (fileWriter != null) {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
