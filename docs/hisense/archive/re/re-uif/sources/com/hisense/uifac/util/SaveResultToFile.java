package com.hisense.uifac.util;

import android.content.Context;
import android.util.Log;
import java.io.FileWriter;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class SaveResultToFile {
    public FileWriter mFileWriter = null;

    public void initFileWriter(Context context, String str) {
        if (this.mFileWriter == null) {
            try {
                this.mFileWriter = new FileWriter(context.getFilesDir().getPath() + "/" + str);
            } catch (IOException e) {
                Log.d("Fac_SaveResultToFile", "init FileWriter failed");
                e.printStackTrace();
            }
        }
    }

    public void saveResult(String str, boolean z) {
        FileWriter fileWriter = this.mFileWriter;
        if (fileWriter == null) {
            Log.i("Fac_SaveResultToFile", "mFileWriter is null");
            return;
        }
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("-----");
            sb.append(z ? "OK" : "NG");
            sb.append("\n");
            fileWriter.append((CharSequence) sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("Fac_SaveResultToFile", "setting boolean result error");
        }
    }

    public void saveResult(String str, String str2) {
        FileWriter fileWriter = this.mFileWriter;
        if (fileWriter == null) {
            Log.i("Fac_SaveResultToFile", "mFileWriter is null");
            return;
        }
        try {
            fileWriter.append((CharSequence) (str + "-----" + str2 + "\n"));
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("Fac_SaveResultToFile", "setting string result error");
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
