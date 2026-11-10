package com.ju.lib.datalayer.database.asist;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.ju.lib.datalayer.database.JuOrmLog;

/* JADX INFO: loaded from: classes2.dex */
public class Querier {
    public static final String TAG = "Querier";

    public static abstract class CursorParser<T> {
        public boolean parse = true;

        public abstract void parseEachCursor(SQLiteDatabase sQLiteDatabase, Cursor cursor);

        public final void process(SQLiteDatabase sQLiteDatabase, Cursor cursor) {
            try {
                try {
                    cursor.moveToFirst();
                    while (this.parse && !cursor.isAfterLast()) {
                        parseEachCursor(sQLiteDatabase, cursor);
                        cursor.moveToNext();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    if (cursor == null) {
                        return;
                    }
                }
                cursor.close();
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }

        public T returnResult() {
            return null;
        }

        public final void stopParse() {
            this.parse = false;
        }
    }

    public static <T> T doQuery(SQLiteDatabase sQLiteDatabase, SQLStatement sQLStatement, CursorParser<T> cursorParser) {
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery(sQLStatement.sql, (String[]) sQLStatement.bindArgs);
        if (cursorRawQuery != null) {
            cursorParser.process(sQLiteDatabase, cursorRawQuery);
        } else {
            JuOrmLog.e(TAG, "doQuery() End : cursor is null");
        }
        return cursorParser.returnResult();
    }
}
