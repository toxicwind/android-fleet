package com.ju.lib.datalayer.database.config;

import android.content.Context;
import b.a.a.a.a;
import com.ju.lib.datalayer.database.asist.Checker;
import com.ju.lib.datalayer.database.asist.SQLiteHelper;
import com.ju.lib.datalayer.database.cache.HashMapCache;
import com.ju.lib.datalayer.database.cache.LruMapCache;
import com.ju.lib.datalayer.database.cache.base.IDatabaseCache;
import com.ju.lib.datalayer.database.cache.manager.CacheManager;

/* JADX INFO: loaded from: classes2.dex */
public class DatabaseConfig {
    public static final String DEFAULT_DB_NAME = "ju_orm.db";
    public static final int DEFAULT_DB_VERSION = 1;
    public IDatabaseCache cache;
    public Context context;
    public String dbName;
    public int dbVersion;
    public boolean debugged;
    public boolean needCache;
    public SQLiteHelper.OnUpdateListener onUpdateListener;

    public DatabaseConfig(Context context) {
        this(context, DEFAULT_DB_NAME);
    }

    public String toString() {
        StringBuilder sbA = a.a("DataBaseConfig [mContext=");
        sbA.append(this.context);
        sbA.append(", mDbName=");
        sbA.append(this.dbName);
        sbA.append(", mDbVersion=");
        sbA.append(this.dbVersion);
        sbA.append(", mOnUpdateListener=");
        return a.a(sbA, this.onUpdateListener, "]");
    }

    public DatabaseConfig(Context context, String str) {
        this(context, str, false, 1, null);
    }

    public DatabaseConfig(Context context, String str, boolean z, int i, SQLiteHelper.OnUpdateListener onUpdateListener) {
        this.debugged = false;
        this.dbName = DEFAULT_DB_NAME;
        this.dbVersion = 1;
        this.needCache = false;
        this.cache = new LruMapCache();
        this.context = context.getApplicationContext();
        if (!Checker.isEmpty(str)) {
            this.dbName = str;
        }
        if (i > 1) {
            this.dbVersion = i;
        }
        this.debugged = z;
        this.onUpdateListener = onUpdateListener;
        CacheManager.sDatabaseCache = HashMapCache.class;
    }
}
