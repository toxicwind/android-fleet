package com.mediatek.twoworlds.tv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class KeyMapReader {
    public HashMap<Integer, Integer> Android_to_MTK_keyCode = new HashMap<>();

    /* JADX WARN: Code duplicated, block: B:23:0x0037 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:24:0x0038  */
    public KeyMapReader(String str) throws Exception {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        Exception e2 = null;
        try {
            try {
                this.Android_to_MTK_keyCode.clear();
                bufferedReader = new BufferedReader(new FileReader(str));
                try {
                    read(bufferedReader);
                } catch (Exception e3) {
                    e = e3;
                    e2 = e;
                    if (bufferedReader != null) {
                    }
                    if (e2 == null) {
                        throw e2;
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedReader2 = bufferedReader;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (Exception unused) {
                        }
                    }
                    throw th;
                }
            } catch (Exception e4) {
                e = e4;
                bufferedReader = null;
            } catch (Throwable th2) {
                th = th2;
            }
            bufferedReader.close();
        } catch (Exception e5) {
            e2 = e5;
        }
        if (e2 == null) {
            throw e2;
        }
    }

    public int getMTKKeyCode(int i) {
        if (this.Android_to_MTK_keyCode.containsKey(Integer.valueOf(i))) {
            return this.Android_to_MTK_keyCode.get(Integer.valueOf(i)).intValue();
        }
        return -1;
    }

    public void parseLine(String str) {
        String strTrim = str.trim();
        if (strTrim.indexOf("#") != 0) {
            String[] strArrSplit = strTrim.split("\\s+");
            this.Android_to_MTK_keyCode.put(new Integer(Integer.parseInt(strArrSplit[3])), new Integer(Integer.parseInt(strArrSplit[1], 16)));
        }
    }

    public void read(BufferedReader bufferedReader) throws IOException {
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                return;
            } else {
                parseLine(line);
            }
        }
    }
}
