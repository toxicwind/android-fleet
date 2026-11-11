package androidx.work.impl.model;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: DependencyDao.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH'J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b2\u0006\u0010\b\u001a\u00020\tH'J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\u000b2\u0006\u0010\b\u001a\u00020\tH'J\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH'ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Landroidx/work/impl/model/DependencyDao;", "", "insertDependency", "", "dependency", "Landroidx/work/impl/model/Dependency;", "hasCompletedAllPrerequisites", "", "id", "", "getPrerequisites", "", "getDependentWorkIds", "hasDependents", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface DependencyDao {
    List<String> getDependentWorkIds(String id);

    List<String> getPrerequisites(String id);

    boolean hasCompletedAllPrerequisites(String id);

    boolean hasDependents(String id);

    void insertDependency(Dependency dependency);
}
