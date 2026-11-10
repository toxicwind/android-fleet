package com.hisense.uifac.util;

import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FacFileUtils {
    public static String[] getFileNameList(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        return new File(str).list();
    }

    public static List<String> getContentFromFile(String str) {
        try {
            File file = new File(str);
            if (!file.exists()) {
                Log.i("Fac_FacFileUtils", "file is not exit!!");
                return null;
            }
            ArrayList arrayList = new ArrayList();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            if (line == null) {
                Log.i("Fac_FacFileUtils", "fileContent is null");
                bufferedReader.close();
                return null;
            }
            Log.i("Fac_FacFileUtils", "first line content is " + line);
            arrayList.add(line);
            int i = 1;
            while (true) {
                String line2 = bufferedReader.readLine();
                if (line2 != null) {
                    Log.i("Fac_FacFileUtils", "fileContent current fileLine is " + i);
                    Log.i("Fac_FacFileUtils", "fileContent content is " + line2);
                    i++;
                    arrayList.add(line2);
                } else {
                    Log.i("Fac_FacFileUtils", "fileContent fileLine is " + i);
                    bufferedReader.close();
                    return arrayList;
                }
            }
        } catch (IOException unused) {
            return null;
        }
    }
}
