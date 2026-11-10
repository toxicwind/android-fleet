package com.mediatek.twoworlds.tv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/* JADX INFO: compiled from: MtkTvKeyEventBase.java */
/* JADX INFO: loaded from: classes.dex */
class KeyMapReader {
    protected HashMap<Integer, Integer> Android_to_MTK_keyCode;

    /* JADX WARN: Code duplicated, block: B:23:0x0035 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:24:0x0036  */
    public KeyMapReader(String str) throws Exception {
        BufferedReader bufferedReader;
        HashMap<Integer, Integer> map = new HashMap<>();
        this.Android_to_MTK_keyCode = map;
        BufferedReader bufferedReader2 = null;
        Exception e = null;
        try {
            map.clear();
            bufferedReader = new BufferedReader(new FileReader(str));
            try {
                read(bufferedReader);
            } catch (Exception e2) {
                e = e2;
                e = e;
                if (bufferedReader != null) {
                }
                if (e == null) {
                    throw e;
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
        } catch (Exception e3) {
            e = e3;
            bufferedReader = null;
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            bufferedReader.close();
        } catch (Exception e4) {
            e = e4;
        }
        if (e == null) {
            throw e;
        }
    }

    protected void read(BufferedReader bufferedReader) throws IOException {
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                return;
            } else {
                parseLine(line);
            }
        }
    }

    protected void parseLine(String str) {
        String strTrim = str.trim();
        if (strTrim.indexOf("#") != 0) {
            String[] strArrSplit = strTrim.split("\\s+");
            this.Android_to_MTK_keyCode.put(new Integer(Integer.parseInt(strArrSplit[3])), new Integer(Integer.parseInt(strArrSplit[1], 16)));
        }
    }

    public int getMTKKeyCode(int i) {
        if (this.Android_to_MTK_keyCode.containsKey(Integer.valueOf(i))) {
            return this.Android_to_MTK_keyCode.get(Integer.valueOf(i)).intValue();
        }
        return -1;
    }
}
