package androidx.work;

import android.content.ComponentName;
import androidx.exifinterface.media.ExifInterface;
import androidx.work.impl.utils.EnqueueUtilsKt;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WorkerParameters.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a!\u0010\u0003\u001a\u00020\u0002\"\n\b\u0000\u0010\u0004\u0018\u0001*\u00020\u0005*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0086\b\u001a\u001a\u0010\u0003\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007\u001a \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000bH\u0007\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u000bH\u0007¨\u0006\u000e"}, d2 = {"isRemoteWorkRequest", "", "Landroidx/work/WorkerParameters;", "usingRemoteService", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/work/ListenableWorker;", "componentName", "Landroid/content/ComponentName;", "workerClassName", "", "buildDelegatedRemoteRequestData", "Landroidx/work/Data;", "delegatedWorkerName", "inputData", "work-runtime_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class WorkerParametersExtensions {
    public static final boolean isRemoteWorkRequest(WorkerParameters workerParameters) {
        Intrinsics.checkNotNullParameter(workerParameters, "<this>");
        Data inputData = workerParameters.getInputData();
        Intrinsics.checkNotNullExpressionValue(inputData, "getInputData(...)");
        return isRemoteWorkRequest(inputData);
    }

    public static final /* synthetic */ <T extends ListenableWorker> WorkerParameters usingRemoteService(WorkerParameters workerParameters, ComponentName componentName) {
        Intrinsics.checkNotNullParameter(workerParameters, "<this>");
        Intrinsics.checkNotNullParameter(componentName, "componentName");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        String name = ListenableWorker.class.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        return usingRemoteService(workerParameters, name, componentName);
    }

    public static final WorkerParameters usingRemoteService(WorkerParameters workerParameters, String workerClassName, ComponentName componentName) {
        Intrinsics.checkNotNullParameter(workerParameters, "<this>");
        Intrinsics.checkNotNullParameter(workerClassName, "workerClassName");
        Intrinsics.checkNotNullParameter(componentName, "componentName");
        UUID id = workerParameters.getId();
        Data inputData = workerParameters.getInputData();
        Intrinsics.checkNotNullExpressionValue(inputData, "getInputData(...)");
        return new WorkerParameters(id, buildDelegatedRemoteRequestData(workerClassName, componentName, inputData), workerParameters.getTags(), workerParameters.getRuntimeExtras(), workerParameters.getRunAttemptCount(), workerParameters.getGeneration(), workerParameters.getBackgroundExecutor(), workerParameters.getWorkerContext(), workerParameters.getTaskExecutor(), workerParameters.getWorkerFactory(), workerParameters.getProgressUpdater(), workerParameters.getForegroundUpdater());
    }

    public static final Data buildDelegatedRemoteRequestData(String delegatedWorkerName, ComponentName componentName, Data inputData) {
        Intrinsics.checkNotNullParameter(delegatedWorkerName, "delegatedWorkerName");
        Intrinsics.checkNotNullParameter(componentName, "componentName");
        Intrinsics.checkNotNullParameter(inputData, "inputData");
        Data.Builder builder = new Data.Builder();
        builder.putAll(inputData).putString(EnqueueUtilsKt.ARGUMENT_SERVICE_PACKAGE_NAME, componentName.getPackageName()).putString(EnqueueUtilsKt.ARGUMENT_SERVICE_CLASS_NAME, componentName.getClassName()).putString(EnqueueUtilsKt.ARGUMENT_REMOTE_LISTENABLE_WORKER_NAME, delegatedWorkerName);
        return builder.build();
    }

    public static final boolean isRemoteWorkRequest(Data data) {
        Intrinsics.checkNotNullParameter(data, "<this>");
        return data.hasKeyWithValueOfType(EnqueueUtilsKt.ARGUMENT_SERVICE_PACKAGE_NAME, String.class) && data.hasKeyWithValueOfType(EnqueueUtilsKt.ARGUMENT_SERVICE_CLASS_NAME, String.class) && data.hasKeyWithValueOfType(EnqueueUtilsKt.ARGUMENT_REMOTE_LISTENABLE_WORKER_NAME, String.class);
    }
}
