package androidx.work.impl.model;

import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.media3.exoplayer.offline.DownloadService;
import androidx.room.RoomDatabase;
import androidx.room.RoomRawQuery;
import androidx.room.RoomSQLiteQuery;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.RelationUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.WorkInfo;
import androidx.work.impl.utils.NetworkRequestCompat;
import com.google.android.gms.actions.SearchIntents;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: RawWorkInfoDao_Impl.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nH\u0016J\u001c\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\f2\u0006\u0010\t\u001a\u00020\nH\u0016J\u001c\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u000e2\u0006\u0010\t\u001a\u00020\nH\u0016J*\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0018\u0010\u0013\u001a\u0014\u0012\u0004\u0012\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00160\u0014H\u0002J*\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0018\u0010\u0013\u001a\u0014\u0012\u0004\u0012\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00160\u0014H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Landroidx/work/impl/model/RawWorkInfoDao_Impl;", "Landroidx/work/impl/model/RawWorkInfoDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "getWorkInfoPojos", "", "Landroidx/work/impl/model/WorkSpec$WorkInfoPojo;", SearchIntents.EXTRA_QUERY, "Landroidx/sqlite/db/SupportSQLiteQuery;", "getWorkInfoPojosLiveData", "Landroidx/lifecycle/LiveData;", "getWorkInfoPojosFlow", "Lkotlinx/coroutines/flow/Flow;", "__fetchRelationshipWorkTagAsjavaLangString", "", "_connection", "Landroidx/sqlite/SQLiteConnection;", "_map", "Landroidx/collection/ArrayMap;", "", "", "__fetchRelationshipWorkProgressAsandroidxWorkData", "Landroidx/work/Data;", "Companion", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class RawWorkInfoDao_Impl implements RawWorkInfoDao {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final RoomDatabase __db;

    public RawWorkInfoDao_Impl(RoomDatabase __db) {
        Intrinsics.checkNotNullParameter(__db, "__db");
        this.__db = __db;
    }

    @Override // androidx.work.impl.model.RawWorkInfoDao
    public List<WorkSpec.WorkInfoPojo> getWorkInfoPojos(SupportSQLiteQuery query) {
        Intrinsics.checkNotNullParameter(query, "query");
        final RoomRawQuery roomRawQuery = RoomSQLiteQuery.INSTANCE.copyFrom(query).toRoomRawQuery();
        final String sql = roomRawQuery.getSql();
        return (List) DBUtil.performBlocking(this.__db, true, false, new Function1() { // from class: androidx.work.impl.model.RawWorkInfoDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return RawWorkInfoDao_Impl.getWorkInfoPojos$lambda$0(sql, roomRawQuery, this, (SQLiteConnection) obj);
            }
        });
    }

    static final List getWorkInfoPojos$lambda$0(String str, RoomRawQuery roomRawQuery, RawWorkInfoDao_Impl rawWorkInfoDao_Impl, SQLiteConnection _connection) throws Throwable {
        SQLiteStatement sQLiteStatement;
        int i;
        int i2;
        boolean z;
        boolean z2;
        boolean z3;
        int i3;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            roomRawQuery.getBindingFunction().invoke(sQLiteStatementPrepare);
            int columnIndex = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "id");
            int columnIndex2 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "state");
            int columnIndex3 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "output");
            int columnIndex4 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "initial_delay");
            int columnIndex5 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "interval_duration");
            int columnIndex6 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "flex_duration");
            int columnIndex7 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "run_attempt_count");
            int columnIndex8 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "backoff_policy");
            int columnIndex9 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "backoff_delay_duration");
            int columnIndex10 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "last_enqueue_time");
            int columnIndex11 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "period_count");
            int columnIndex12 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "generation");
            String str2 = "getValue(...)";
            int columnIndex13 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "next_schedule_time_override");
            int columnIndex14 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, DownloadService.KEY_STOP_REASON);
            int columnIndex15 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "required_network_type");
            int columnIndex16 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "required_network_request");
            int columnIndex17 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "requires_charging");
            int columnIndex18 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "requires_device_idle");
            int columnIndex19 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "requires_battery_not_low");
            int columnIndex20 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "requires_storage_not_low");
            int columnIndex21 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "trigger_content_update_delay");
            int columnIndex22 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "trigger_max_content_delay");
            int columnIndex23 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "content_uri_triggers");
            ArrayMap<String, List<String>> arrayMap = new ArrayMap<>();
            int i4 = columnIndex12;
            ArrayMap<String, List<Data>> arrayMap2 = new ArrayMap<>();
            while (sQLiteStatementPrepare.step()) {
                int i5 = columnIndex11;
                String text = sQLiteStatementPrepare.getText(columnIndex);
                if (!arrayMap.containsKey(text)) {
                    arrayMap.put(text, new ArrayList());
                }
                String text2 = sQLiteStatementPrepare.getText(columnIndex);
                if (!arrayMap2.containsKey(text2)) {
                    arrayMap2.put(text2, new ArrayList());
                }
                columnIndex11 = i5;
                columnIndex10 = columnIndex10;
            }
            int i6 = columnIndex10;
            int i7 = columnIndex11;
            sQLiteStatementPrepare.reset();
            rawWorkInfoDao_Impl.__fetchRelationshipWorkTagAsjavaLangString(_connection, arrayMap);
            rawWorkInfoDao_Impl.__fetchRelationshipWorkProgressAsandroidxWorkData(_connection, arrayMap2);
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (columnIndex == -1) {
                    throw new IllegalStateException("Missing value for a NON-NULL column 'id', found NULL value instead.".toString());
                }
                String text3 = sQLiteStatementPrepare.getText(columnIndex);
                if (columnIndex2 == -1) {
                    throw new IllegalStateException("Missing value for a NON-NULL column 'state', found NULL value instead.".toString());
                }
                WorkInfo.State stateIntToState = WorkTypeConverters.intToState((int) sQLiteStatementPrepare.getLong(columnIndex2));
                if (columnIndex3 == -1) {
                    throw new IllegalStateException("Missing value for a NON-NULL column 'output', found NULL value instead.".toString());
                }
                Data dataFromByteArray = Data.INSTANCE.fromByteArray(sQLiteStatementPrepare.getBlob(columnIndex3));
                long j = columnIndex4 == -1 ? 0L : sQLiteStatementPrepare.getLong(columnIndex4);
                long j2 = columnIndex5 == -1 ? 0L : sQLiteStatementPrepare.getLong(columnIndex5);
                long j3 = columnIndex6 == -1 ? 0L : sQLiteStatementPrepare.getLong(columnIndex6);
                boolean z4 = false;
                int i8 = columnIndex7 == -1 ? 0 : (int) sQLiteStatementPrepare.getLong(columnIndex7);
                if (columnIndex8 == -1) {
                    throw new IllegalStateException("Missing value for a NON-NULL column 'backoff_policy', found NULL value instead.".toString());
                }
                BackoffPolicy backoffPolicyIntToBackoffPolicy = WorkTypeConverters.intToBackoffPolicy((int) sQLiteStatementPrepare.getLong(columnIndex8));
                long j4 = columnIndex9 == -1 ? 0L : sQLiteStatementPrepare.getLong(columnIndex9);
                int i9 = i6;
                long j5 = i9 == -1 ? 0L : sQLiteStatementPrepare.getLong(i9);
                int i10 = i7;
                if (i10 == -1) {
                    i = 0;
                    i2 = -1;
                } else {
                    i = (int) sQLiteStatementPrepare.getLong(i10);
                    i2 = -1;
                }
                int i11 = i4;
                int i12 = i11 == i2 ? 0 : (int) sQLiteStatementPrepare.getLong(i11);
                int i13 = columnIndex13;
                long j6 = i13 == i2 ? 0L : sQLiteStatementPrepare.getLong(i13);
                int i14 = columnIndex14;
                int i15 = i14 == i2 ? 0 : (int) sQLiteStatementPrepare.getLong(i14);
                int i16 = columnIndex15;
                if (i16 == i2) {
                    throw new IllegalStateException("Missing value for a NON-NULL column 'required_network_type', found NULL value instead.".toString());
                }
                NetworkType networkTypeIntToNetworkType = WorkTypeConverters.intToNetworkType((int) sQLiteStatementPrepare.getLong(i16));
                int i17 = columnIndex16;
                if (i17 == i2) {
                    throw new IllegalStateException("Missing value for a NON-NULL column 'required_network_request', found NULL value instead.".toString());
                }
                NetworkRequestCompat networkRequest$work_runtime_release = WorkTypeConverters.toNetworkRequest$work_runtime_release(sQLiteStatementPrepare.getBlob(i17));
                int i18 = columnIndex17;
                if (i18 == i2) {
                    z = false;
                } else {
                    z = ((int) sQLiteStatementPrepare.getLong(i18)) != 0;
                }
                int i19 = columnIndex18;
                if (i19 == i2) {
                    z2 = false;
                } else {
                    z2 = ((int) sQLiteStatementPrepare.getLong(i19)) != 0;
                }
                int i20 = columnIndex19;
                if (i20 == i2) {
                    z3 = false;
                } else {
                    z3 = ((int) sQLiteStatementPrepare.getLong(i20)) != 0;
                }
                int i21 = columnIndex20;
                if (i21 == i2) {
                    i3 = i17;
                } else {
                    i3 = i17;
                    if (((int) sQLiteStatementPrepare.getLong(i21)) != 0) {
                        z4 = true;
                    }
                }
                int i22 = columnIndex21;
                boolean z5 = z4;
                long j7 = i22 == i2 ? 0L : sQLiteStatementPrepare.getLong(i22);
                int i23 = columnIndex22;
                long j8 = i23 == i2 ? 0L : sQLiteStatementPrepare.getLong(i23);
                int i24 = columnIndex23;
                if (i24 == i2) {
                    throw new IllegalStateException("Missing value for a NON-NULL column 'content_uri_triggers', found NULL value instead.".toString());
                }
                Constraints constraints = new Constraints(networkRequest$work_runtime_release, networkTypeIntToNetworkType, z, z2, z3, z5, j7, j8, WorkTypeConverters.byteArrayToSetOfTriggers(sQLiteStatementPrepare.getBlob(i24)));
                columnIndex23 = i24;
                Object value = MapsKt.getValue(arrayMap, sQLiteStatementPrepare.getText(columnIndex));
                String str3 = str2;
                Intrinsics.checkNotNullExpressionValue(value, str3);
                List list = (List) value;
                sQLiteStatement = sQLiteStatementPrepare;
                try {
                    Object value2 = MapsKt.getValue(arrayMap2, sQLiteStatementPrepare.getText(columnIndex));
                    Intrinsics.checkNotNullExpressionValue(value2, str3);
                    arrayList.add(new WorkSpec.WorkInfoPojo(text3, stateIntToState, dataFromByteArray, j, j2, j3, constraints, i8, backoffPolicyIntToBackoffPolicy, j4, j5, i, i12, j6, i15, list, (List) value2));
                    columnIndex21 = i22;
                    columnIndex22 = i23;
                    sQLiteStatementPrepare = sQLiteStatement;
                    columnIndex4 = columnIndex4;
                    columnIndex5 = columnIndex5;
                    str2 = str3;
                    columnIndex14 = i14;
                    columnIndex15 = i16;
                    columnIndex17 = i18;
                    columnIndex18 = i19;
                    arrayMap = arrayMap;
                    columnIndex13 = i13;
                    columnIndex19 = i20;
                    i7 = i10;
                    columnIndex2 = columnIndex2;
                    i4 = i11;
                    columnIndex16 = i3;
                    columnIndex20 = i21;
                    columnIndex3 = columnIndex3;
                    i6 = i9;
                } catch (Throwable th) {
                    th = th;
                    sQLiteStatement.close();
                    throw th;
                }
            }
            sQLiteStatementPrepare.close();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            sQLiteStatement = sQLiteStatementPrepare;
        }
    }

    @Override // androidx.work.impl.model.RawWorkInfoDao
    public LiveData<List<WorkSpec.WorkInfoPojo>> getWorkInfoPojosLiveData(SupportSQLiteQuery query) {
        Intrinsics.checkNotNullParameter(query, "query");
        final RoomRawQuery roomRawQuery = RoomSQLiteQuery.INSTANCE.copyFrom(query).toRoomRawQuery();
        final String sql = roomRawQuery.getSql();
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"WorkTag", "WorkProgress", "WorkSpec"}, false, new Function1() { // from class: androidx.work.impl.model.RawWorkInfoDao_Impl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return RawWorkInfoDao_Impl.getWorkInfoPojosLiveData$lambda$1(sql, roomRawQuery, this, (SQLiteConnection) obj);
            }
        });
    }

    static final List getWorkInfoPojosLiveData$lambda$1(String str, RoomRawQuery roomRawQuery, RawWorkInfoDao_Impl rawWorkInfoDao_Impl, SQLiteConnection _connection) throws Throwable {
        SQLiteStatement sQLiteStatement;
        int i;
        int i2;
        boolean z;
        boolean z2;
        boolean z3;
        int i3;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            roomRawQuery.getBindingFunction().invoke(sQLiteStatementPrepare);
            int columnIndex = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "id");
            int columnIndex2 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "state");
            int columnIndex3 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "output");
            int columnIndex4 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "initial_delay");
            int columnIndex5 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "interval_duration");
            int columnIndex6 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "flex_duration");
            int columnIndex7 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "run_attempt_count");
            int columnIndex8 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "backoff_policy");
            int columnIndex9 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "backoff_delay_duration");
            int columnIndex10 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "last_enqueue_time");
            int columnIndex11 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "period_count");
            int columnIndex12 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "generation");
            String str2 = "getValue(...)";
            int columnIndex13 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "next_schedule_time_override");
            int columnIndex14 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, DownloadService.KEY_STOP_REASON);
            int columnIndex15 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "required_network_type");
            int columnIndex16 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "required_network_request");
            int columnIndex17 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "requires_charging");
            int columnIndex18 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "requires_device_idle");
            int columnIndex19 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "requires_battery_not_low");
            int columnIndex20 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "requires_storage_not_low");
            int columnIndex21 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "trigger_content_update_delay");
            int columnIndex22 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "trigger_max_content_delay");
            int columnIndex23 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "content_uri_triggers");
            ArrayMap<String, List<String>> arrayMap = new ArrayMap<>();
            int i4 = columnIndex12;
            ArrayMap<String, List<Data>> arrayMap2 = new ArrayMap<>();
            while (sQLiteStatementPrepare.step()) {
                int i5 = columnIndex11;
                String text = sQLiteStatementPrepare.getText(columnIndex);
                if (!arrayMap.containsKey(text)) {
                    arrayMap.put(text, new ArrayList());
                }
                String text2 = sQLiteStatementPrepare.getText(columnIndex);
                if (!arrayMap2.containsKey(text2)) {
                    arrayMap2.put(text2, new ArrayList());
                }
                columnIndex11 = i5;
                columnIndex10 = columnIndex10;
            }
            int i6 = columnIndex10;
            int i7 = columnIndex11;
            sQLiteStatementPrepare.reset();
            rawWorkInfoDao_Impl.__fetchRelationshipWorkTagAsjavaLangString(_connection, arrayMap);
            rawWorkInfoDao_Impl.__fetchRelationshipWorkProgressAsandroidxWorkData(_connection, arrayMap2);
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (columnIndex == -1) {
                    throw new IllegalStateException("Missing value for a NON-NULL column 'id', found NULL value instead.".toString());
                }
                String text3 = sQLiteStatementPrepare.getText(columnIndex);
                if (columnIndex2 == -1) {
                    throw new IllegalStateException("Missing value for a NON-NULL column 'state', found NULL value instead.".toString());
                }
                WorkInfo.State stateIntToState = WorkTypeConverters.intToState((int) sQLiteStatementPrepare.getLong(columnIndex2));
                if (columnIndex3 == -1) {
                    throw new IllegalStateException("Missing value for a NON-NULL column 'output', found NULL value instead.".toString());
                }
                Data dataFromByteArray = Data.INSTANCE.fromByteArray(sQLiteStatementPrepare.getBlob(columnIndex3));
                long j = columnIndex4 == -1 ? 0L : sQLiteStatementPrepare.getLong(columnIndex4);
                long j2 = columnIndex5 == -1 ? 0L : sQLiteStatementPrepare.getLong(columnIndex5);
                long j3 = columnIndex6 == -1 ? 0L : sQLiteStatementPrepare.getLong(columnIndex6);
                boolean z4 = false;
                int i8 = columnIndex7 == -1 ? 0 : (int) sQLiteStatementPrepare.getLong(columnIndex7);
                if (columnIndex8 == -1) {
                    throw new IllegalStateException("Missing value for a NON-NULL column 'backoff_policy', found NULL value instead.".toString());
                }
                BackoffPolicy backoffPolicyIntToBackoffPolicy = WorkTypeConverters.intToBackoffPolicy((int) sQLiteStatementPrepare.getLong(columnIndex8));
                long j4 = columnIndex9 == -1 ? 0L : sQLiteStatementPrepare.getLong(columnIndex9);
                int i9 = i6;
                long j5 = i9 == -1 ? 0L : sQLiteStatementPrepare.getLong(i9);
                int i10 = i7;
                if (i10 == -1) {
                    i = 0;
                    i2 = -1;
                } else {
                    i = (int) sQLiteStatementPrepare.getLong(i10);
                    i2 = -1;
                }
                int i11 = i4;
                int i12 = i11 == i2 ? 0 : (int) sQLiteStatementPrepare.getLong(i11);
                int i13 = columnIndex13;
                long j6 = i13 == i2 ? 0L : sQLiteStatementPrepare.getLong(i13);
                int i14 = columnIndex14;
                int i15 = i14 == i2 ? 0 : (int) sQLiteStatementPrepare.getLong(i14);
                int i16 = columnIndex15;
                if (i16 == i2) {
                    throw new IllegalStateException("Missing value for a NON-NULL column 'required_network_type', found NULL value instead.".toString());
                }
                NetworkType networkTypeIntToNetworkType = WorkTypeConverters.intToNetworkType((int) sQLiteStatementPrepare.getLong(i16));
                int i17 = columnIndex16;
                if (i17 == i2) {
                    throw new IllegalStateException("Missing value for a NON-NULL column 'required_network_request', found NULL value instead.".toString());
                }
                NetworkRequestCompat networkRequest$work_runtime_release = WorkTypeConverters.toNetworkRequest$work_runtime_release(sQLiteStatementPrepare.getBlob(i17));
                int i18 = columnIndex17;
                if (i18 == i2) {
                    z = false;
                } else {
                    z = ((int) sQLiteStatementPrepare.getLong(i18)) != 0;
                }
                int i19 = columnIndex18;
                if (i19 == i2) {
                    z2 = false;
                } else {
                    z2 = ((int) sQLiteStatementPrepare.getLong(i19)) != 0;
                }
                int i20 = columnIndex19;
                if (i20 == i2) {
                    z3 = false;
                } else {
                    z3 = ((int) sQLiteStatementPrepare.getLong(i20)) != 0;
                }
                int i21 = columnIndex20;
                if (i21 == i2) {
                    i3 = i17;
                } else {
                    i3 = i17;
                    if (((int) sQLiteStatementPrepare.getLong(i21)) != 0) {
                        z4 = true;
                    }
                }
                int i22 = columnIndex21;
                boolean z5 = z4;
                long j7 = i22 == i2 ? 0L : sQLiteStatementPrepare.getLong(i22);
                int i23 = columnIndex22;
                long j8 = i23 == i2 ? 0L : sQLiteStatementPrepare.getLong(i23);
                int i24 = columnIndex23;
                if (i24 == i2) {
                    throw new IllegalStateException("Missing value for a NON-NULL column 'content_uri_triggers', found NULL value instead.".toString());
                }
                Constraints constraints = new Constraints(networkRequest$work_runtime_release, networkTypeIntToNetworkType, z, z2, z3, z5, j7, j8, WorkTypeConverters.byteArrayToSetOfTriggers(sQLiteStatementPrepare.getBlob(i24)));
                columnIndex23 = i24;
                Object value = MapsKt.getValue(arrayMap, sQLiteStatementPrepare.getText(columnIndex));
                String str3 = str2;
                Intrinsics.checkNotNullExpressionValue(value, str3);
                List list = (List) value;
                sQLiteStatement = sQLiteStatementPrepare;
                try {
                    Object value2 = MapsKt.getValue(arrayMap2, sQLiteStatementPrepare.getText(columnIndex));
                    Intrinsics.checkNotNullExpressionValue(value2, str3);
                    arrayList.add(new WorkSpec.WorkInfoPojo(text3, stateIntToState, dataFromByteArray, j, j2, j3, constraints, i8, backoffPolicyIntToBackoffPolicy, j4, j5, i, i12, j6, i15, list, (List) value2));
                    columnIndex21 = i22;
                    columnIndex22 = i23;
                    sQLiteStatementPrepare = sQLiteStatement;
                    columnIndex4 = columnIndex4;
                    columnIndex5 = columnIndex5;
                    str2 = str3;
                    columnIndex14 = i14;
                    columnIndex15 = i16;
                    columnIndex17 = i18;
                    columnIndex18 = i19;
                    arrayMap = arrayMap;
                    columnIndex13 = i13;
                    columnIndex19 = i20;
                    i7 = i10;
                    columnIndex2 = columnIndex2;
                    i4 = i11;
                    columnIndex16 = i3;
                    columnIndex20 = i21;
                    columnIndex3 = columnIndex3;
                    i6 = i9;
                } catch (Throwable th) {
                    th = th;
                    sQLiteStatement.close();
                    throw th;
                }
            }
            sQLiteStatementPrepare.close();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            sQLiteStatement = sQLiteStatementPrepare;
        }
    }

    @Override // androidx.work.impl.model.RawWorkInfoDao
    public Flow<List<WorkSpec.WorkInfoPojo>> getWorkInfoPojosFlow(SupportSQLiteQuery query) {
        Intrinsics.checkNotNullParameter(query, "query");
        final RoomRawQuery roomRawQuery = RoomSQLiteQuery.INSTANCE.copyFrom(query).toRoomRawQuery();
        final String sql = roomRawQuery.getSql();
        return FlowUtil.createFlow(this.__db, false, new String[]{"WorkTag", "WorkProgress", "WorkSpec"}, new Function1() { // from class: androidx.work.impl.model.RawWorkInfoDao_Impl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return RawWorkInfoDao_Impl.getWorkInfoPojosFlow$lambda$2(sql, roomRawQuery, this, (SQLiteConnection) obj);
            }
        });
    }

    static final List getWorkInfoPojosFlow$lambda$2(String str, RoomRawQuery roomRawQuery, RawWorkInfoDao_Impl rawWorkInfoDao_Impl, SQLiteConnection _connection) throws Throwable {
        SQLiteStatement sQLiteStatement;
        int i;
        int i2;
        boolean z;
        boolean z2;
        boolean z3;
        int i3;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            roomRawQuery.getBindingFunction().invoke(sQLiteStatementPrepare);
            int columnIndex = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "id");
            int columnIndex2 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "state");
            int columnIndex3 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "output");
            int columnIndex4 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "initial_delay");
            int columnIndex5 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "interval_duration");
            int columnIndex6 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "flex_duration");
            int columnIndex7 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "run_attempt_count");
            int columnIndex8 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "backoff_policy");
            int columnIndex9 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "backoff_delay_duration");
            int columnIndex10 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "last_enqueue_time");
            int columnIndex11 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "period_count");
            int columnIndex12 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "generation");
            String str2 = "getValue(...)";
            int columnIndex13 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "next_schedule_time_override");
            int columnIndex14 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, DownloadService.KEY_STOP_REASON);
            int columnIndex15 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "required_network_type");
            int columnIndex16 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "required_network_request");
            int columnIndex17 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "requires_charging");
            int columnIndex18 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "requires_device_idle");
            int columnIndex19 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "requires_battery_not_low");
            int columnIndex20 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "requires_storage_not_low");
            int columnIndex21 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "trigger_content_update_delay");
            int columnIndex22 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "trigger_max_content_delay");
            int columnIndex23 = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "content_uri_triggers");
            ArrayMap<String, List<String>> arrayMap = new ArrayMap<>();
            int i4 = columnIndex12;
            ArrayMap<String, List<Data>> arrayMap2 = new ArrayMap<>();
            while (sQLiteStatementPrepare.step()) {
                int i5 = columnIndex11;
                String text = sQLiteStatementPrepare.getText(columnIndex);
                if (!arrayMap.containsKey(text)) {
                    arrayMap.put(text, new ArrayList());
                }
                String text2 = sQLiteStatementPrepare.getText(columnIndex);
                if (!arrayMap2.containsKey(text2)) {
                    arrayMap2.put(text2, new ArrayList());
                }
                columnIndex11 = i5;
                columnIndex10 = columnIndex10;
            }
            int i6 = columnIndex10;
            int i7 = columnIndex11;
            sQLiteStatementPrepare.reset();
            rawWorkInfoDao_Impl.__fetchRelationshipWorkTagAsjavaLangString(_connection, arrayMap);
            rawWorkInfoDao_Impl.__fetchRelationshipWorkProgressAsandroidxWorkData(_connection, arrayMap2);
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (columnIndex == -1) {
                    throw new IllegalStateException("Missing value for a NON-NULL column 'id', found NULL value instead.".toString());
                }
                String text3 = sQLiteStatementPrepare.getText(columnIndex);
                if (columnIndex2 == -1) {
                    throw new IllegalStateException("Missing value for a NON-NULL column 'state', found NULL value instead.".toString());
                }
                WorkInfo.State stateIntToState = WorkTypeConverters.intToState((int) sQLiteStatementPrepare.getLong(columnIndex2));
                if (columnIndex3 == -1) {
                    throw new IllegalStateException("Missing value for a NON-NULL column 'output', found NULL value instead.".toString());
                }
                Data dataFromByteArray = Data.INSTANCE.fromByteArray(sQLiteStatementPrepare.getBlob(columnIndex3));
                long j = columnIndex4 == -1 ? 0L : sQLiteStatementPrepare.getLong(columnIndex4);
                long j2 = columnIndex5 == -1 ? 0L : sQLiteStatementPrepare.getLong(columnIndex5);
                long j3 = columnIndex6 == -1 ? 0L : sQLiteStatementPrepare.getLong(columnIndex6);
                boolean z4 = false;
                int i8 = columnIndex7 == -1 ? 0 : (int) sQLiteStatementPrepare.getLong(columnIndex7);
                if (columnIndex8 == -1) {
                    throw new IllegalStateException("Missing value for a NON-NULL column 'backoff_policy', found NULL value instead.".toString());
                }
                BackoffPolicy backoffPolicyIntToBackoffPolicy = WorkTypeConverters.intToBackoffPolicy((int) sQLiteStatementPrepare.getLong(columnIndex8));
                long j4 = columnIndex9 == -1 ? 0L : sQLiteStatementPrepare.getLong(columnIndex9);
                int i9 = i6;
                long j5 = i9 == -1 ? 0L : sQLiteStatementPrepare.getLong(i9);
                int i10 = i7;
                if (i10 == -1) {
                    i = 0;
                    i2 = -1;
                } else {
                    i = (int) sQLiteStatementPrepare.getLong(i10);
                    i2 = -1;
                }
                int i11 = i4;
                int i12 = i11 == i2 ? 0 : (int) sQLiteStatementPrepare.getLong(i11);
                int i13 = columnIndex13;
                long j6 = i13 == i2 ? 0L : sQLiteStatementPrepare.getLong(i13);
                int i14 = columnIndex14;
                int i15 = i14 == i2 ? 0 : (int) sQLiteStatementPrepare.getLong(i14);
                int i16 = columnIndex15;
                if (i16 == i2) {
                    throw new IllegalStateException("Missing value for a NON-NULL column 'required_network_type', found NULL value instead.".toString());
                }
                NetworkType networkTypeIntToNetworkType = WorkTypeConverters.intToNetworkType((int) sQLiteStatementPrepare.getLong(i16));
                int i17 = columnIndex16;
                if (i17 == i2) {
                    throw new IllegalStateException("Missing value for a NON-NULL column 'required_network_request', found NULL value instead.".toString());
                }
                NetworkRequestCompat networkRequest$work_runtime_release = WorkTypeConverters.toNetworkRequest$work_runtime_release(sQLiteStatementPrepare.getBlob(i17));
                int i18 = columnIndex17;
                if (i18 == i2) {
                    z = false;
                } else {
                    z = ((int) sQLiteStatementPrepare.getLong(i18)) != 0;
                }
                int i19 = columnIndex18;
                if (i19 == i2) {
                    z2 = false;
                } else {
                    z2 = ((int) sQLiteStatementPrepare.getLong(i19)) != 0;
                }
                int i20 = columnIndex19;
                if (i20 == i2) {
                    z3 = false;
                } else {
                    z3 = ((int) sQLiteStatementPrepare.getLong(i20)) != 0;
                }
                int i21 = columnIndex20;
                if (i21 == i2) {
                    i3 = i17;
                } else {
                    i3 = i17;
                    if (((int) sQLiteStatementPrepare.getLong(i21)) != 0) {
                        z4 = true;
                    }
                }
                int i22 = columnIndex21;
                boolean z5 = z4;
                long j7 = i22 == i2 ? 0L : sQLiteStatementPrepare.getLong(i22);
                int i23 = columnIndex22;
                long j8 = i23 == i2 ? 0L : sQLiteStatementPrepare.getLong(i23);
                int i24 = columnIndex23;
                if (i24 == i2) {
                    throw new IllegalStateException("Missing value for a NON-NULL column 'content_uri_triggers', found NULL value instead.".toString());
                }
                Constraints constraints = new Constraints(networkRequest$work_runtime_release, networkTypeIntToNetworkType, z, z2, z3, z5, j7, j8, WorkTypeConverters.byteArrayToSetOfTriggers(sQLiteStatementPrepare.getBlob(i24)));
                columnIndex23 = i24;
                Object value = MapsKt.getValue(arrayMap, sQLiteStatementPrepare.getText(columnIndex));
                String str3 = str2;
                Intrinsics.checkNotNullExpressionValue(value, str3);
                List list = (List) value;
                sQLiteStatement = sQLiteStatementPrepare;
                try {
                    Object value2 = MapsKt.getValue(arrayMap2, sQLiteStatementPrepare.getText(columnIndex));
                    Intrinsics.checkNotNullExpressionValue(value2, str3);
                    arrayList.add(new WorkSpec.WorkInfoPojo(text3, stateIntToState, dataFromByteArray, j, j2, j3, constraints, i8, backoffPolicyIntToBackoffPolicy, j4, j5, i, i12, j6, i15, list, (List) value2));
                    columnIndex21 = i22;
                    columnIndex22 = i23;
                    sQLiteStatementPrepare = sQLiteStatement;
                    columnIndex4 = columnIndex4;
                    columnIndex5 = columnIndex5;
                    str2 = str3;
                    columnIndex14 = i14;
                    columnIndex15 = i16;
                    columnIndex17 = i18;
                    columnIndex18 = i19;
                    arrayMap = arrayMap;
                    columnIndex13 = i13;
                    columnIndex19 = i20;
                    i7 = i10;
                    columnIndex2 = columnIndex2;
                    i4 = i11;
                    columnIndex16 = i3;
                    columnIndex20 = i21;
                    columnIndex3 = columnIndex3;
                    i6 = i9;
                } catch (Throwable th) {
                    th = th;
                    sQLiteStatement.close();
                    throw th;
                }
            }
            sQLiteStatementPrepare.close();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            sQLiteStatement = sQLiteStatementPrepare;
        }
    }

    private final void __fetchRelationshipWorkTagAsjavaLangString(final SQLiteConnection _connection, ArrayMap<String, List<String>> _map) {
        Set<String> setKeySet = _map.keySet();
        if (setKeySet.isEmpty()) {
            return;
        }
        if (_map.getSize() > 999) {
            RelationUtil.recursiveFetchArrayMap(_map, true, new Function1() { // from class: androidx.work.impl.model.RawWorkInfoDao_Impl$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return RawWorkInfoDao_Impl.__fetchRelationshipWorkTagAsjavaLangString$lambda$3(this.f$0, _connection, (ArrayMap) obj);
                }
            });
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT `tag`,`work_spec_id` FROM `WorkTag` WHERE `work_spec_id` IN (");
        StringUtil.appendPlaceholders(sb, setKeySet.size());
        sb.append(")");
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(string);
        Iterator<String> it = setKeySet.iterator();
        int i = 1;
        while (it.hasNext()) {
            sQLiteStatementPrepare.mo7925bindText(i, it.next());
            i++;
        }
        try {
            int columnIndex = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "work_spec_id");
            if (columnIndex != -1) {
                while (sQLiteStatementPrepare.step()) {
                    List<String> list = _map.get(sQLiteStatementPrepare.getText(columnIndex));
                    if (list != null) {
                        list.add(sQLiteStatementPrepare.getText(0));
                    }
                }
                sQLiteStatementPrepare.close();
                return;
            }
            sQLiteStatementPrepare.close();
        } catch (Throwable th) {
            sQLiteStatementPrepare.close();
            throw th;
        }
    }

    static final Unit __fetchRelationshipWorkTagAsjavaLangString$lambda$3(RawWorkInfoDao_Impl rawWorkInfoDao_Impl, SQLiteConnection sQLiteConnection, ArrayMap _tmpMap) {
        Intrinsics.checkNotNullParameter(_tmpMap, "_tmpMap");
        rawWorkInfoDao_Impl.__fetchRelationshipWorkTagAsjavaLangString(sQLiteConnection, _tmpMap);
        return Unit.INSTANCE;
    }

    private final void __fetchRelationshipWorkProgressAsandroidxWorkData(final SQLiteConnection _connection, ArrayMap<String, List<Data>> _map) {
        Set<String> setKeySet = _map.keySet();
        if (setKeySet.isEmpty()) {
            return;
        }
        if (_map.getSize() > 999) {
            RelationUtil.recursiveFetchArrayMap(_map, true, new Function1() { // from class: androidx.work.impl.model.RawWorkInfoDao_Impl$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return RawWorkInfoDao_Impl.__fetchRelationshipWorkProgressAsandroidxWorkData$lambda$4(this.f$0, _connection, (ArrayMap) obj);
                }
            });
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT `progress`,`work_spec_id` FROM `WorkProgress` WHERE `work_spec_id` IN (");
        StringUtil.appendPlaceholders(sb, setKeySet.size());
        sb.append(")");
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(string);
        Iterator<String> it = setKeySet.iterator();
        int i = 1;
        while (it.hasNext()) {
            sQLiteStatementPrepare.mo7925bindText(i, it.next());
            i++;
        }
        try {
            int columnIndex = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "work_spec_id");
            if (columnIndex != -1) {
                while (sQLiteStatementPrepare.step()) {
                    List<Data> list = _map.get(sQLiteStatementPrepare.getText(columnIndex));
                    if (list != null) {
                        list.add(Data.INSTANCE.fromByteArray(sQLiteStatementPrepare.getBlob(0)));
                    }
                }
                sQLiteStatementPrepare.close();
                return;
            }
            sQLiteStatementPrepare.close();
        } catch (Throwable th) {
            sQLiteStatementPrepare.close();
            throw th;
        }
    }

    static final Unit __fetchRelationshipWorkProgressAsandroidxWorkData$lambda$4(RawWorkInfoDao_Impl rawWorkInfoDao_Impl, SQLiteConnection sQLiteConnection, ArrayMap _tmpMap) {
        Intrinsics.checkNotNullParameter(_tmpMap, "_tmpMap");
        rawWorkInfoDao_Impl.__fetchRelationshipWorkProgressAsandroidxWorkData(sQLiteConnection, _tmpMap);
        return Unit.INSTANCE;
    }

    /* JADX INFO: compiled from: RawWorkInfoDao_Impl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Landroidx/work/impl/model/RawWorkInfoDao_Impl$Companion;", "", "<init>", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<KClass<?>> getRequiredConverters() {
            return CollectionsKt.emptyList();
        }
    }
}
