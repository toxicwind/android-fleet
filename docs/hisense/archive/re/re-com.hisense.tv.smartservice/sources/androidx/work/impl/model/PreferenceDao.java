package androidx.work.impl.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

/* JADX INFO: loaded from: classes.dex */
@Dao
public interface PreferenceDao {
    @Nullable
    @Query("SELECT long_value FROM Preference where `key`=:key")
    Long getLongValue(@NonNull String key);

    @NonNull
    @Query("SELECT long_value FROM Preference where `key`=:key")
    LiveData<Long> getObservableLongValue(@NonNull String key);

    @Insert(onConflict = 1)
    void insertPreference(@NonNull Preference preference);
}
