package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@Beta
@GwtCompatible
public final class ArrayBasedEscaperMap {
    public static final char[][] EMPTY_REPLACEMENT_ARRAY = (char[][]) Array.newInstance((Class<?>) char.class, 0, 0);
    public final char[][] replacementArray;

    public ArrayBasedEscaperMap(char[][] cArr) {
        this.replacementArray = cArr;
    }

    public static ArrayBasedEscaperMap create(Map<Character, String> map) {
        return new ArrayBasedEscaperMap(createReplacementArray(map));
    }

    @VisibleForTesting
    public static char[][] createReplacementArray(Map<Character, String> map) {
        if (map == null) {
            throw new NullPointerException();
        }
        if (map.isEmpty()) {
            return EMPTY_REPLACEMENT_ARRAY;
        }
        char[][] cArr = new char[((Character) Collections.max(map.keySet())).charValue() + 1][];
        Iterator<Character> it = map.keySet().iterator();
        while (it.hasNext()) {
            char cCharValue = it.next().charValue();
            cArr[cCharValue] = map.get(Character.valueOf(cCharValue)).toCharArray();
        }
        return cArr;
    }

    public char[][] getReplacementArray() {
        return this.replacementArray;
    }
}
