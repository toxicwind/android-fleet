package androidx.work.impl.model;

import androidx.lifecycle.LiveData;
import androidx.work.Data;
import androidx.work.WorkInfo;
import java.util.List;
import kotlin.Metadata;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: WorkSpecDao.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0014\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH'J\u0012\u0010\t\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\bH'J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\bH'J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u000bH'J\u0014\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u000b0\u0010H'J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\bH'J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\bH'J\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH'J\u0018\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u0019H'J\u0018\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u001cH'J\u0010\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\bH'J\u0010\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\bH'J\u0018\u0010\u001f\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010 \u001a\u00020\u001cH'J\u0018\u0010!\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\"\u001a\u00020\u0012H'J\u0012\u0010#\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0007\u001a\u00020\bH'J\u0012\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010\u0007\u001a\u00020\bH'J\u001c\u0010&\u001a\b\u0012\u0004\u0012\u00020%0\u000b2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\b0\u000bH'J\"\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\u000b0\u00102\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\b0\u000bH'J\"\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\u000b0*2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\b0\u000bH'J\u0016\u0010+\u001a\b\u0012\u0004\u0012\u00020%0\u000b2\u0006\u0010,\u001a\u00020\bH'J\u001c\u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\u000b0*2\u0006\u0010,\u001a\u00020\bH'J\u001c\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\u000b0\u00102\u0006\u0010,\u001a\u00020\bH'J\u0016\u0010/\u001a\b\u0012\u0004\u0012\u00020%0\u000b2\u0006\u0010\r\u001a\u00020\bH'J\u001c\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\u000b0\u00102\u0006\u0010\r\u001a\u00020\bH'J\u001c\u00101\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\u000b0*2\u0006\u0010\r\u001a\u00020\bH'J\u0016\u00102\u001a\b\u0012\u0004\u0012\u00020\u00190\u000b2\u0006\u0010\u0007\u001a\u00020\bH'J\u0016\u00103\u001a\b\u0012\u0004\u0012\u00020\b0\u000b2\u0006\u0010,\u001a\u00020\bH'J\u0016\u00104\u001a\b\u0012\u0004\u0012\u00020\b0\u000b2\u0006\u0010\r\u001a\u00020\bH'J\u000e\u00105\u001a\b\u0012\u0004\u0012\u00020\b0\u000bH'J\u000e\u00106\u001a\b\u0012\u0004\u0012\u0002070*H'J\u0018\u00108\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\b2\u0006\u00109\u001a\u00020\u001cH'J\u0018\u0010:\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u00102\u0006\u0010\u0007\u001a\u00020\bH'J\b\u0010;\u001a\u00020\u0012H'J\u0016\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b2\u0006\u0010=\u001a\u00020\u0012H'J\u000e\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bH'J\u0016\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b2\u0006\u0010@\u001a\u00020\u0012H'J\u000e\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bH'J\u000e\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bH'J\u0016\u0010C\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b2\u0006\u0010D\u001a\u00020\u001cH'J\b\u0010E\u001a\u00020\u0003H'J\u0010\u0010F\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH'J\u0010\u0010G\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\b\u0010H\u001a\u00020\u0012H'J\u0018\u0010I\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010J\u001a\u00020\u0012H'ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006KÀ\u0006\u0001"}, d2 = {"Landroidx/work/impl/model/WorkSpecDao;", "", "insertWorkSpec", "", "workSpec", "Landroidx/work/impl/model/WorkSpec;", "delete", "id", "", "getWorkSpec", "getWorkSpecIdAndStatesForName", "", "Landroidx/work/impl/model/WorkSpec$IdAndState;", "name", "getAllWorkSpecIds", "getAllWorkSpecIdsLiveData", "Landroidx/lifecycle/LiveData;", "setState", "", "state", "Landroidx/work/WorkInfo$State;", "setCancelledState", "incrementPeriodCount", "setOutput", "output", "Landroidx/work/Data;", "setLastEnqueueTime", "enqueueTime", "", "incrementWorkSpecRunAttemptCount", "resetWorkSpecRunAttemptCount", "setNextScheduleTimeOverride", "nextScheduleTimeOverrideMillis", "resetWorkSpecNextScheduleTimeOverride", "overrideGeneration", "getState", "getWorkStatusPojoForId", "Landroidx/work/impl/model/WorkSpec$WorkInfoPojo;", "getWorkStatusPojoForIds", "ids", "getWorkStatusPojoLiveDataForIds", "getWorkStatusPojoFlowDataForIds", "Lkotlinx/coroutines/flow/Flow;", "getWorkStatusPojoForTag", "tag", "getWorkStatusPojoFlowForTag", "getWorkStatusPojoLiveDataForTag", "getWorkStatusPojoForName", "getWorkStatusPojoLiveDataForName", "getWorkStatusPojoFlowForName", "getInputsFromPrerequisites", "getUnfinishedWorkWithTag", "getUnfinishedWorkWithName", "getAllUnfinishedWork", "hasUnfinishedWorkFlow", "", "markWorkSpecScheduled", "startTime", "getScheduleRequestedAtLiveData", "resetScheduledState", "getEligibleWorkForScheduling", "schedulerLimit", "getEligibleWorkForSchedulingWithContentUris", "getAllEligibleWorkSpecsForScheduling", "maxLimit", "getScheduledWork", "getRunningWork", "getRecentlyCompletedWork", "startingAt", "pruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast", "incrementGeneration", "updateWorkSpec", "countNonFinishedContentUriTriggerWorkers", "setStopReason", "stopReason", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface WorkSpecDao {
    int countNonFinishedContentUriTriggerWorkers();

    void delete(String id);

    List<WorkSpec> getAllEligibleWorkSpecsForScheduling(int maxLimit);

    List<String> getAllUnfinishedWork();

    List<String> getAllWorkSpecIds();

    LiveData<List<String>> getAllWorkSpecIdsLiveData();

    List<WorkSpec> getEligibleWorkForScheduling(int schedulerLimit);

    List<WorkSpec> getEligibleWorkForSchedulingWithContentUris();

    List<Data> getInputsFromPrerequisites(String id);

    List<WorkSpec> getRecentlyCompletedWork(long startingAt);

    List<WorkSpec> getRunningWork();

    LiveData<Long> getScheduleRequestedAtLiveData(String id);

    List<WorkSpec> getScheduledWork();

    WorkInfo.State getState(String id);

    List<String> getUnfinishedWorkWithName(String name);

    List<String> getUnfinishedWorkWithTag(String tag);

    WorkSpec getWorkSpec(String id);

    List<WorkSpec.IdAndState> getWorkSpecIdAndStatesForName(String name);

    Flow<List<WorkSpec.WorkInfoPojo>> getWorkStatusPojoFlowDataForIds(List<String> ids);

    Flow<List<WorkSpec.WorkInfoPojo>> getWorkStatusPojoFlowForName(String name);

    Flow<List<WorkSpec.WorkInfoPojo>> getWorkStatusPojoFlowForTag(String tag);

    WorkSpec.WorkInfoPojo getWorkStatusPojoForId(String id);

    List<WorkSpec.WorkInfoPojo> getWorkStatusPojoForIds(List<String> ids);

    List<WorkSpec.WorkInfoPojo> getWorkStatusPojoForName(String name);

    List<WorkSpec.WorkInfoPojo> getWorkStatusPojoForTag(String tag);

    LiveData<List<WorkSpec.WorkInfoPojo>> getWorkStatusPojoLiveDataForIds(List<String> ids);

    LiveData<List<WorkSpec.WorkInfoPojo>> getWorkStatusPojoLiveDataForName(String name);

    LiveData<List<WorkSpec.WorkInfoPojo>> getWorkStatusPojoLiveDataForTag(String tag);

    Flow<Boolean> hasUnfinishedWorkFlow();

    void incrementGeneration(String id);

    void incrementPeriodCount(String id);

    int incrementWorkSpecRunAttemptCount(String id);

    void insertWorkSpec(WorkSpec workSpec);

    int markWorkSpecScheduled(String id, long startTime);

    void pruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast();

    int resetScheduledState();

    void resetWorkSpecNextScheduleTimeOverride(String id, int overrideGeneration);

    int resetWorkSpecRunAttemptCount(String id);

    int setCancelledState(String id);

    void setLastEnqueueTime(String id, long enqueueTime);

    void setNextScheduleTimeOverride(String id, long nextScheduleTimeOverrideMillis);

    void setOutput(String id, Data output);

    int setState(WorkInfo.State state, String id);

    void setStopReason(String id, int stopReason);

    void updateWorkSpec(WorkSpec workSpec);
}
