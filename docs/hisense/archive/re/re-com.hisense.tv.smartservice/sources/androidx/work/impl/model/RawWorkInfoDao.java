package androidx.work.impl.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.RawQuery;
import androidx.sqlite.db.SupportSQLiteQuery;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Dao
public interface RawWorkInfoDao {
    @NonNull
    @RawQuery(observedEntities = {WorkSpec.class})
    List<WorkSpec.WorkInfoPojo> getWorkInfoPojos(@NonNull SupportSQLiteQuery query);

    @NonNull
    @RawQuery(observedEntities = {WorkSpec.class})
    LiveData<List<WorkSpec.WorkInfoPojo>> getWorkInfoPojosLiveData(@NonNull SupportSQLiteQuery query);
}
