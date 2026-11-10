package com.ju.lib.datalayer.database.api;

import com.ju.lib.datalayer.database.asist.QueryBuilder;
import com.ju.lib.datalayer.database.asist.WhereBuilder;
import com.ju.lib.datalayer.database.config.DatabaseConfig;
import com.ju.lib.datalayer.database.model.ColumnsValue;
import com.ju.lib.datalayer.database.model.ConflictAlgorithm;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: classes2.dex */
public interface IDatabase {
    void close();

    int delete(WhereBuilder whereBuilder);

    <T> int delete(Class<T> cls, long j, long j2, String str);

    int delete(Object obj);

    <T> int delete(Collection<T> collection);

    <T> int deleteAll(Class<T> cls);

    boolean deleteDatabase();

    boolean dropTable(Class<?> cls);

    boolean dropTable(String str);

    DatabaseConfig getDatabaseConfig();

    <T> int insert(Collection<T> collection);

    <T> int insert(Collection<T> collection, ConflictAlgorithm conflictAlgorithm);

    long insert(Object obj);

    long insert(Object obj, ConflictAlgorithm conflictAlgorithm);

    <T> ArrayList<T> query(QueryBuilder<T> queryBuilder);

    <T> ArrayList<T> queryAll(Class<T> cls);

    <T> T queryById(long j, Class<T> cls);

    <T> T queryById(String str, Class<T> cls);

    long queryCount(QueryBuilder queryBuilder);

    <T> long queryCount(Class<T> cls);

    <T> int save(Collection<T> collection);

    long save(Object obj);

    int update(WhereBuilder whereBuilder, ColumnsValue columnsValue, ConflictAlgorithm conflictAlgorithm);

    int update(Object obj);

    int update(Object obj, ColumnsValue columnsValue, ConflictAlgorithm conflictAlgorithm);

    int update(Object obj, ConflictAlgorithm conflictAlgorithm);

    <T> int update(Collection<T> collection);

    <T> int update(Collection<T> collection, ColumnsValue columnsValue, ConflictAlgorithm conflictAlgorithm);

    <T> int update(Collection<T> collection, ConflictAlgorithm conflictAlgorithm);
}
