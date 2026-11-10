package com.ju.lib.datalayer.database.asist;

import android.database.sqlite.SQLiteDatabase;

/* JADX INFO: loaded from: classes2.dex */
public class Transaction {
    public static final String TAG = "Transaction";

    public interface Worker<T> {
        T doTransaction(SQLiteDatabase sQLiteDatabase);
    }

    public static <T> T execute(SQLiteDatabase sQLiteDatabase, Worker<T> worker) {
        T tDoTransaction;
        sQLiteDatabase.beginTransaction();
        try {
            try {
                tDoTransaction = worker.doTransaction(sQLiteDatabase);
                try {
                    sQLiteDatabase.setTransactionSuccessful();
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                }
            } finally {
                sQLiteDatabase.endTransaction();
            }
        } catch (Exception e3) {
            e = e3;
            tDoTransaction = null;
        }
        return tDoTransaction;
    }
}
