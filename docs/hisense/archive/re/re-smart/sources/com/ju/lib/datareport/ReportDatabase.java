package com.ju.lib.datareport;

import android.content.Context;
import b.a.a.a.a;
import com.ju.lib.datalayer.database.JuOrm;
import com.ju.lib.datalayer.database.asist.QueryBuilder;
import com.ju.lib.datalayer.database.asist.WhereBuilder;
import com.ju.lib.datalayer.database.config.DatabaseConfig;
import com.ju.lib.utils.log.LogUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ReportDatabase {
    public static final String DATABASE_NAME = "report.db";
    public static final int DB_VERSION = 1;
    public static ReportDatabase INSTANCE = null;
    public static final String TAG = "ReportDatabase";
    public JuOrm mJuOrm;

    public ReportDatabase(Context context) {
        DatabaseConfig databaseConfig = new DatabaseConfig(context, DATABASE_NAME);
        databaseConfig.debugged = false;
        databaseConfig.dbVersion = 1;
        this.mJuOrm = JuOrm.newSingleInstance(databaseConfig);
    }

    public static synchronized ReportDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ReportDatabase(context);
        }
        return INSTANCE;
    }

    public int delete(Collection<ReportBean> collection) {
        return this.mJuOrm.delete((Collection) collection);
    }

    public int deleteALL(Class<ReportBean> cls) {
        return this.mJuOrm.deleteAll(cls);
    }

    public int filter(Collection<String> collection) {
        LogUtil.i(TAG, a.a("filter: eventCodes = ", collection));
        return this.mJuOrm.delete(new WhereBuilder(ReportBean.class).notIn(ReportBean.Columns.EVENT_CODE, collection.toArray()));
    }

    public long getCount(int i) {
        return this.mJuOrm.queryCount(new QueryBuilder(ReportBean.class).whereEquals("type", Integer.valueOf(i)));
    }

    public long getCountAll() {
        return this.mJuOrm.queryCount(new QueryBuilder(ReportBean.class));
    }

    public StrategyBean getStrategyBean(String str) {
        ArrayList arrayListQuery = this.mJuOrm.query(new QueryBuilder(StrategyBean.class).whereEquals("app_key", str));
        if (arrayListQuery == null || arrayListQuery.isEmpty()) {
            LogUtil.i(TAG, "NO Strategy! ");
            return null;
        }
        String str2 = TAG;
        StringBuilder sbA = a.a("Strategy size is ");
        sbA.append(arrayListQuery.size());
        LogUtil.i(str2, sbA.toString());
        return (StrategyBean) arrayListQuery.get(0);
    }

    public long insert(ReportBean reportBean) {
        return this.mJuOrm.insert(reportBean);
    }

    public long putStrategyBean(StrategyBean strategyBean) {
        return this.mJuOrm.insert(strategyBean);
    }

    public List<ReportBean> query(String str, int i, int i2) {
        return this.mJuOrm.query(new QueryBuilder(ReportBean.class).whereEquals("type", Integer.valueOf(i)).whereAppendAnd().whereEquals("app_key", str).limit(0, i2));
    }

    public long getCount(String str, int i) {
        return this.mJuOrm.queryCount(new QueryBuilder(ReportBean.class).whereEquals("type", Integer.valueOf(i)).whereAppendAnd().whereEquals("app_key", str));
    }

    public int filter(String str, Collection<String> collection) {
        LogUtil.i(TAG, "filter: APP_KEY=:", str, ", eventCodes = ", collection);
        return this.mJuOrm.delete(new WhereBuilder(ReportBean.class).equals("app_key", str).and().notIn(ReportBean.Columns.EVENT_CODE, collection.toArray()));
    }

    public List<ReportBean> query(String str, int i) {
        return this.mJuOrm.query(new QueryBuilder(ReportBean.class).whereEquals("type", Integer.valueOf(i)).whereAppendAnd().whereEquals("app_key", str));
    }

    public List<ReportBean> query(int i) {
        return this.mJuOrm.query(new QueryBuilder(ReportBean.class).whereEquals("type", Integer.valueOf(i)));
    }

    public int filter(String str, int i, String str2) {
        LogUtil.i(TAG, a.a("filter: eventCodes = ", str2));
        return this.mJuOrm.delete(new WhereBuilder(ReportBean.class).equals("type", Integer.valueOf(i)).and().equals("app_key", str).and().equals(ReportBean.Columns.EVENT_CODE, str2));
    }

    public int filter(String str, int i) {
        LogUtil.i(TAG, "filter: key = " + str + ", type = " + i);
        return this.mJuOrm.delete(new WhereBuilder(ReportBean.class).equals("type", Integer.valueOf(i)).and().equals("app_key", str));
    }
}
