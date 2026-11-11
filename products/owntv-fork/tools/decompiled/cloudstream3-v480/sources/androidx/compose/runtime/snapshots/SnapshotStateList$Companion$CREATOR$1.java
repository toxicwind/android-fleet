package androidx.compose.runtime.snapshots;

import android.os.Parcel;
import android.os.Parcelable;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: SnapshotStateList.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000/\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00020\u0001J\"\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0018\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J%\u0010\t\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"androidx/compose/runtime/snapshots/SnapshotStateList$Companion$CREATOR$1", "Landroid/os/Parcelable$ClassLoaderCreator;", "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "", "createFromParcel", "parcel", "Landroid/os/Parcel;", "loader", "Ljava/lang/ClassLoader;", "newArray", "", ContentDisposition.Parameters.Size, "", "(I)[Landroidx/compose/runtime/snapshots/SnapshotStateList;", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SnapshotStateList$Companion$CREATOR$1 implements Parcelable.ClassLoaderCreator<SnapshotStateList<Object>> {
    SnapshotStateList$Companion$CREATOR$1() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.ClassLoaderCreator
    public SnapshotStateList<Object> createFromParcel(final Parcel parcel, final ClassLoader loader) {
        if (loader == null) {
            loader = getClass().getClassLoader();
        }
        return SnapshotStateListKt.SnapshotStateList(parcel.readInt(), new Function1() { // from class: androidx.compose.runtime.snapshots.SnapshotStateList$Companion$CREATOR$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Parcel parcel2 = parcel;
                ClassLoader classLoader = loader;
                ((Integer) obj).intValue();
                return parcel2.readValue(classLoader);
            }
        });
    }

    @Override // android.os.Parcelable.Creator
    public SnapshotStateList<Object> createFromParcel(Parcel parcel) {
        return createFromParcel(parcel, (ClassLoader) null);
    }

    @Override // android.os.Parcelable.Creator
    public SnapshotStateList<Object>[] newArray(int size) {
        return new SnapshotStateList[size];
    }
}
