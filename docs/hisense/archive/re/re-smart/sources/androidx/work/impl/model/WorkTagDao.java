package androidx.work.impl.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Dao
public interface WorkTagDao {
    @Query("SELECT DISTINCT tag FROM worktag WHERE work_spec_id=:id")
    List<String> getTagsForWorkSpecId(String id);

    @Query("SELECT work_spec_id FROM worktag WHERE tag=:tag")
    List<String> getWorkSpecIdsWithTag(String tag);

    @Insert(onConflict = 5)
    void insert(WorkTag workTag);
}
