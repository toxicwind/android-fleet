package androidx.work.impl.model;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WorkTagDao.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\"\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH'J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n2\u0006\u0010\u000b\u001a\u00020\bH'J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\n2\u0006\u0010\u0007\u001a\u00020\bH'J\u001e\u0010\r\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u000fH\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0003"}, d2 = {"Landroidx/work/impl/model/WorkTagDao;", "", "insert", "", "workTag", "Landroidx/work/impl/model/WorkTag;", "deleteByWorkSpecId", "id", "", "getWorkSpecIdsWithTag", "", "tag", "getTagsForWorkSpecId", "insertTags", "tags", "", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface WorkTagDao {
    void deleteByWorkSpecId(String id);

    List<String> getTagsForWorkSpecId(String id);

    List<String> getWorkSpecIdsWithTag(String tag);

    void insert(WorkTag workTag);

    void insertTags(String id, Set<String> tags);

    /* JADX INFO: renamed from: androidx.work.impl.model.WorkTagDao$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: WorkTagDao.kt */
    public final /* synthetic */ class CC {
        public static void $default$insertTags(WorkTagDao _this, String id, Set tags) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(tags, "tags");
            Iterator it = tags.iterator();
            while (it.hasNext()) {
                _this.insert(new WorkTag((String) it.next(), id));
            }
        }
    }

    /* JADX INFO: compiled from: WorkTagDao.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static void insertTags(WorkTagDao workTagDao, String id, Set<String> tags) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(tags, "tags");
            CC.$default$insertTags(workTagDao, id, tags);
        }
    }
}
