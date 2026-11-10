package androidx.work.impl.model;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

/* JADX INFO: loaded from: classes.dex */
@Entity(foreignKeys = {@ForeignKey(childColumns = {"work_spec_id"}, entity = WorkSpec.class, onDelete = 5, onUpdate = 5, parentColumns = {"id"})}, indices = {@Index({"work_spec_id"})}, primaryKeys = {"name", "work_spec_id"})
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class WorkName {

    @NonNull
    @ColumnInfo(name = "name")
    public final String name;

    @NonNull
    @ColumnInfo(name = "work_spec_id")
    public final String workSpecId;

    public WorkName(@NonNull String name, @NonNull String workSpecId) {
        this.name = name;
        this.workSpecId = workSpecId;
    }
}
