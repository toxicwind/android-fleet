package androidx.coordinatorlayout.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import androidx.customview.view.AbsSavedState;

/* JADX INFO: loaded from: classes.dex */
public class CoordinatorLayout$SavedState extends AbsSavedState {
    public static final Parcelable.Creator<CoordinatorLayout$SavedState> CREATOR = new Parcelable.ClassLoaderCreator<CoordinatorLayout$SavedState>() { // from class: androidx.coordinatorlayout.widget.CoordinatorLayout$SavedState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.ClassLoaderCreator
        public CoordinatorLayout$SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return new CoordinatorLayout$SavedState(parcel, classLoader);
        }

        @Override // android.os.Parcelable.Creator
        public CoordinatorLayout$SavedState createFromParcel(Parcel parcel) {
            return new CoordinatorLayout$SavedState(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        public CoordinatorLayout$SavedState[] newArray(int i) {
            return new CoordinatorLayout$SavedState[i];
        }
    };
    SparseArray<Parcelable> behaviorStates;

    public CoordinatorLayout$SavedState(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        int i = parcel.readInt();
        int[] iArr = new int[i];
        parcel.readIntArray(iArr);
        Parcelable[] parcelableArray = parcel.readParcelableArray(classLoader);
        this.behaviorStates = new SparseArray<>(i);
        for (int i2 = 0; i2 < i; i2++) {
            this.behaviorStates.append(iArr[i2], parcelableArray[i2]);
        }
    }

    @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        SparseArray<Parcelable> sparseArray = this.behaviorStates;
        int size = sparseArray != null ? sparseArray.size() : 0;
        parcel.writeInt(size);
        int[] iArr = new int[size];
        Parcelable[] parcelableArr = new Parcelable[size];
        for (int i2 = 0; i2 < size; i2++) {
            iArr[i2] = this.behaviorStates.keyAt(i2);
            parcelableArr[i2] = this.behaviorStates.valueAt(i2);
        }
        parcel.writeIntArray(iArr);
        parcel.writeParcelableArray(parcelableArr, i);
    }
}
