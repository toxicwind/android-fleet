package com.google.firebase.heartbeatinfo;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.Task;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface HeartBeatInfo {

    public enum HeartBeat {
        NONE(0),
        SDK(1),
        GLOBAL(2),
        COMBINED(3);

        public final int code;

        HeartBeat(int i) {
            this.code = i;
        }

        public int getCode() {
            return this.code;
        }
    }

    @NonNull
    Task<List<HeartBeatResult>> getAndClearStoredHeartBeatInfo();

    @NonNull
    HeartBeat getHeartBeatCode(@NonNull String str);

    @NonNull
    Task<Void> storeHeartBeatInfo(@NonNull String str);
}
