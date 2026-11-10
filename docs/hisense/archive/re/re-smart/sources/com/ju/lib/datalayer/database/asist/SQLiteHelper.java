package com.ju.lib.datalayer.database.asist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* JADX INFO: loaded from: classes2.dex */
public class SQLiteHelper extends SQLiteOpenHelper {
    public OnUpdateListener onUpdateListener;

    public interface OnUpdateListener {
        void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2);

        void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2);
    }

    public SQLiteHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i, OnUpdateListener onUpdateListener) {
        super(context, str, cursorFactory, i);
        this.onUpdateListener = onUpdateListener;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        OnUpdateListener onUpdateListener = this.onUpdateListener;
        if (onUpdateListener != null) {
            onUpdateListener.onDowngrade(sQLiteDatabase, i, i2);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        OnUpdateListener onUpdateListener = this.onUpdateListener;
        if (onUpdateListener != null) {
            onUpdateListener.onUpgrade(sQLiteDatabase, i, i2);
        }
    }
}
