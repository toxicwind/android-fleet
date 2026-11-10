package androidx.room.util;

import android.database.Cursor;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.sqlite.db.SupportSQLiteDatabase;
import b.a.a.a.a;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class FtsTableInfo {
    public static final String[] FTS_OPTIONS = {"tokenize=", "compress=", "content=", "languageid=", "matchinfo=", "notindexed=", "order=", "prefix=", "uncompress="};
    public final Set<String> columns;
    public final String name;
    public final Set<String> options;

    public FtsTableInfo(String str, Set<String> set, Set<String> set2) {
        this.name = str;
        this.columns = set;
        this.options = set2;
    }

    /* JADX WARN: Code duplicated, block: B:33:0x0088  */
    /* JADX WARN: Code duplicated, block: B:35:0x008e  */
    /* JADX WARN: Code duplicated, block: B:36:0x0096  */
    /* JADX WARN: Code duplicated, block: B:38:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:60:0x00a5 A[SYNTHETIC] */
    @VisibleForTesting
    public static Set<String> parseOptions(String str) {
        if (str.isEmpty()) {
            return new HashSet();
        }
        String strSubstring = str.substring(str.indexOf(40) + 1, str.lastIndexOf(41));
        ArrayList<String> arrayList = new ArrayList();
        ArrayDeque arrayDeque = new ArrayDeque();
        int i = -1;
        for (int i2 = 0; i2 < strSubstring.length(); i2++) {
            char cCharAt = strSubstring.charAt(i2);
            if (cCharAt == '\"' || cCharAt == '\'') {
                if (arrayDeque.isEmpty()) {
                    arrayDeque.push(Character.valueOf(cCharAt));
                } else if (((Character) arrayDeque.peek()).charValue() == cCharAt) {
                    arrayDeque.pop();
                }
            } else if (cCharAt != ',') {
                if (cCharAt != '[') {
                    if (cCharAt != ']') {
                        if (cCharAt == '`') {
                            if (arrayDeque.isEmpty()) {
                                arrayDeque.push(Character.valueOf(cCharAt));
                            } else if (((Character) arrayDeque.peek()).charValue() == cCharAt) {
                                arrayDeque.pop();
                            }
                        }
                    } else if (!arrayDeque.isEmpty() && ((Character) arrayDeque.peek()).charValue() == '[') {
                        arrayDeque.pop();
                    }
                } else if (arrayDeque.isEmpty()) {
                    arrayDeque.push(Character.valueOf(cCharAt));
                }
            } else if (arrayDeque.isEmpty()) {
                arrayList.add(strSubstring.substring(i + 1, i2).trim());
                i = i2;
            }
        }
        arrayList.add(strSubstring.substring(i + 1).trim());
        HashSet hashSet = new HashSet();
        for (String str2 : arrayList) {
            for (String str3 : FTS_OPTIONS) {
                if (str2.startsWith(str3)) {
                    hashSet.add(str2);
                }
            }
        }
        return hashSet;
    }

    public static FtsTableInfo read(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
        return new FtsTableInfo(str, readColumns(supportSQLiteDatabase, str), readOptions(supportSQLiteDatabase, str));
    }

    public static Set<String> readColumns(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
        Cursor cursorQuery = supportSQLiteDatabase.query("PRAGMA table_info(`" + str + "`)");
        HashSet hashSet = new HashSet();
        try {
            if (cursorQuery.getColumnCount() > 0) {
                int columnIndex = cursorQuery.getColumnIndex("name");
                while (cursorQuery.moveToNext()) {
                    hashSet.add(cursorQuery.getString(columnIndex));
                }
            }
            return hashSet;
        } finally {
            cursorQuery.close();
        }
    }

    public static Set<String> readOptions(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
        Cursor cursorQuery = supportSQLiteDatabase.query("SELECT * FROM sqlite_master WHERE `name` = '" + str + "'");
        try {
            return parseOptions(cursorQuery.moveToFirst() ? cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("sql")) : "");
        } finally {
            cursorQuery.close();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || FtsTableInfo.class != obj.getClass()) {
            return false;
        }
        FtsTableInfo ftsTableInfo = (FtsTableInfo) obj;
        String str = this.name;
        if (str == null ? ftsTableInfo.name != null : !str.equals(ftsTableInfo.name)) {
            return false;
        }
        Set<String> set = this.columns;
        if (set == null ? ftsTableInfo.columns != null : !set.equals(ftsTableInfo.columns)) {
            return false;
        }
        Set<String> set2 = this.options;
        if (set2 != null) {
            return set2.equals(ftsTableInfo.options);
        }
        return ftsTableInfo.options == null;
    }

    public int hashCode() {
        String str = this.name;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        Set<String> set = this.columns;
        int iHashCode2 = (iHashCode + (set != null ? set.hashCode() : 0)) * 31;
        Set<String> set2 = this.options;
        return iHashCode2 + (set2 != null ? set2.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sbA = a.a("FtsTableInfo{name='");
        a.a(sbA, this.name, '\'', ", columns=");
        sbA.append(this.columns);
        sbA.append(", options=");
        return a.a(sbA, (Object) this.options, '}');
    }

    public FtsTableInfo(String str, Set<String> set, String str2) {
        this.name = str;
        this.columns = set;
        this.options = parseOptions(str2);
    }
}
