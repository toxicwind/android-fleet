package androidx.work.impl.model;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: WorkNameDao.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\bH'J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u000b\u001a\u00020\bH'ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Landroidx/work/impl/model/WorkNameDao;", "", "insert", "", "workName", "Landroidx/work/impl/model/WorkName;", "getWorkSpecIdsWithName", "", "", "name", "getNamesForWorkSpecId", "workSpecId", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface WorkNameDao {
    List<String> getNamesForWorkSpecId(String workSpecId);

    List<String> getWorkSpecIdsWithName(String name);

    void insert(WorkName workName);
}
