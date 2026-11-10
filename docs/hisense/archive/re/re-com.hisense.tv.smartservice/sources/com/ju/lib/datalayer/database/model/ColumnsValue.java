package com.ju.lib.datalayer.database.model;

import com.ju.lib.datalayer.database.asist.Checker;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class ColumnsValue {
    public String[] columns;
    public Map<String, Object> map;
    public Object[] values;

    public ColumnsValue(Map<String, Object> map) {
        this.map = new HashMap();
        if (Checker.isEmpty(map)) {
            return;
        }
        this.columns = new String[map.size()];
        int i = 0;
        Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            this.columns[i] = it.next().getKey();
            i++;
        }
        this.map = map;
    }

    public boolean checkColumns() {
        if (this.columns != null) {
            return true;
        }
        throw new IllegalArgumentException("columns must not be null");
    }

    public Object getValue(String str) {
        return this.map.get(str);
    }

    public ColumnsValue(String[] strArr) {
        this.map = new HashMap();
        this.columns = strArr;
        for (String str : strArr) {
            this.map.put(str, null);
        }
    }

    public ColumnsValue(String[] strArr, Object[] objArr) {
        this.map = new HashMap();
        this.columns = strArr;
        int i = 0;
        if (objArr != null) {
            if (strArr.length == objArr.length) {
                int length = strArr.length;
                int i2 = 0;
                while (i < length) {
                    this.map.put(strArr[i], objArr[i2]);
                    i++;
                    i2++;
                }
                return;
            }
            throw new IllegalArgumentException("length of columns and values must be the same");
        }
        int length2 = strArr.length;
        while (i < length2) {
            this.map.put(strArr[i], null);
            i++;
        }
    }
}
