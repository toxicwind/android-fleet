package com.google.firebase.storage;

import androidx.annotation.NonNull;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class StorageTaskManager {
    public static final StorageTaskManager _instance = new StorageTaskManager();
    public final Map<String, WeakReference<StorageTask<?>>> inProgressTasks = new HashMap();
    public final Object syncObject = new Object();

    public static StorageTaskManager getInstance() {
        return _instance;
    }

    public void ensureRegistered(StorageTask<?> storageTask) {
        synchronized (this.syncObject) {
            this.inProgressTasks.put(storageTask.getStorage().toString(), new WeakReference<>(storageTask));
        }
    }

    public List<FileDownloadTask> getDownloadTasksUnder(@NonNull StorageReference storageReference) {
        List<FileDownloadTask> listUnmodifiableList;
        synchronized (this.syncObject) {
            ArrayList arrayList = new ArrayList();
            String string = storageReference.toString();
            for (Map.Entry<String, WeakReference<StorageTask<?>>> entry : this.inProgressTasks.entrySet()) {
                if (entry.getKey().startsWith(string)) {
                    StorageTask<?> storageTask = entry.getValue().get();
                    if (storageTask instanceof FileDownloadTask) {
                        arrayList.add((FileDownloadTask) storageTask);
                    }
                }
            }
            listUnmodifiableList = Collections.unmodifiableList(arrayList);
        }
        return listUnmodifiableList;
    }

    public List<UploadTask> getUploadTasksUnder(@NonNull StorageReference storageReference) {
        List<UploadTask> listUnmodifiableList;
        synchronized (this.syncObject) {
            ArrayList arrayList = new ArrayList();
            String string = storageReference.toString();
            for (Map.Entry<String, WeakReference<StorageTask<?>>> entry : this.inProgressTasks.entrySet()) {
                if (entry.getKey().startsWith(string)) {
                    StorageTask<?> storageTask = entry.getValue().get();
                    if (storageTask instanceof UploadTask) {
                        arrayList.add((UploadTask) storageTask);
                    }
                }
            }
            listUnmodifiableList = Collections.unmodifiableList(arrayList);
        }
        return listUnmodifiableList;
    }

    public void unRegister(StorageTask<?> storageTask) {
        synchronized (this.syncObject) {
            String string = storageTask.getStorage().toString();
            WeakReference<StorageTask<?>> weakReference = this.inProgressTasks.get(string);
            StorageTask<?> storageTask2 = weakReference != null ? weakReference.get() : null;
            if (storageTask2 == null || storageTask2 == storageTask) {
                this.inProgressTasks.remove(string);
            }
        }
    }
}
