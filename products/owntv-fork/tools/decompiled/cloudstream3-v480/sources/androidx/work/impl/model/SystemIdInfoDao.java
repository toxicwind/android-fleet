package androidx.work.impl.model;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SystemIdInfoDao.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH'J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH'J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH'J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u000fH'ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0003"}, d2 = {"Landroidx/work/impl/model/SystemIdInfoDao;", "", "insertSystemIdInfo", "", "systemIdInfo", "Landroidx/work/impl/model/SystemIdInfo;", "getSystemIdInfo", "workSpecId", "", "generation", "", "id", "Landroidx/work/impl/model/WorkGenerationalId;", "removeSystemIdInfo", "getWorkSpecIds", "", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface SystemIdInfoDao {
    SystemIdInfo getSystemIdInfo(WorkGenerationalId id);

    SystemIdInfo getSystemIdInfo(String workSpecId, int generation);

    List<String> getWorkSpecIds();

    void insertSystemIdInfo(SystemIdInfo systemIdInfo);

    void removeSystemIdInfo(WorkGenerationalId id);

    void removeSystemIdInfo(String workSpecId);

    void removeSystemIdInfo(String workSpecId, int generation);

    /* JADX INFO: renamed from: androidx.work.impl.model.SystemIdInfoDao$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: SystemIdInfoDao.kt */
    public final /* synthetic */ class CC {
        public static SystemIdInfo $default$getSystemIdInfo(SystemIdInfoDao _this, WorkGenerationalId id) {
            Intrinsics.checkNotNullParameter(id, "id");
            return _this.getSystemIdInfo(id.getWorkSpecId(), id.getGeneration());
        }

        public static void $default$removeSystemIdInfo(SystemIdInfoDao _this, WorkGenerationalId id) {
            Intrinsics.checkNotNullParameter(id, "id");
            _this.removeSystemIdInfo(id.getWorkSpecId(), id.getGeneration());
        }
    }

    /* JADX INFO: compiled from: SystemIdInfoDao.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static SystemIdInfo getSystemIdInfo(SystemIdInfoDao systemIdInfoDao, WorkGenerationalId id) {
            Intrinsics.checkNotNullParameter(id, "id");
            return CC.$default$getSystemIdInfo(systemIdInfoDao, id);
        }

        @Deprecated
        public static void removeSystemIdInfo(SystemIdInfoDao systemIdInfoDao, WorkGenerationalId id) {
            Intrinsics.checkNotNullParameter(id, "id");
            CC.$default$removeSystemIdInfo(systemIdInfoDao, id);
        }
    }
}
