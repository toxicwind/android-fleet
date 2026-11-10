package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import androidx.core.content.res.TypedArrayUtils;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.IntCompanionObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class PreferenceGroup extends Preference {
    final SimpleArrayMap<String, Long> mIdRecycleCache;
    private List<Preference> mPreferences;

    public PreferenceGroup(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mIdRecycleCache = new SimpleArrayMap<>();
        new Handler();
        this.mPreferences = new ArrayList();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.PreferenceGroup, i, i2);
        int i3 = R$styleable.PreferenceGroup_orderingFromXml;
        TypedArrayUtils.getBoolean(typedArrayObtainStyledAttributes, i3, i3, true);
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.PreferenceGroup_initialExpandedChildrenCount)) {
            int i4 = R$styleable.PreferenceGroup_initialExpandedChildrenCount;
            setInitialExpandedChildrenCount(TypedArrayUtils.getInt(typedArrayObtainStyledAttributes, i4, i4, IntCompanionObject.MAX_VALUE));
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public PreferenceGroup(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public PreferenceGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void setInitialExpandedChildrenCount(int i) {
        if (i == Integer.MAX_VALUE || hasKey()) {
            return;
        }
        Log.e("PreferenceGroup", getClass().getSimpleName() + " should have a key defined if it contains an expandable preference");
    }

    public int getPreferenceCount() {
        return this.mPreferences.size();
    }

    public Preference getPreference(int i) {
        return this.mPreferences.get(i);
    }

    @Override // androidx.preference.Preference
    public void notifyDependencyChange(boolean z) {
        super.notifyDependencyChange(z);
        int preferenceCount = getPreferenceCount();
        for (int i = 0; i < preferenceCount; i++) {
            getPreference(i).onParentChanged(this, z);
        }
    }

    static class SavedState extends Preference.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: androidx.preference.PreferenceGroup.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        int mInitialExpandedChildrenCount;

        SavedState(Parcel parcel) {
            super(parcel);
            this.mInitialExpandedChildrenCount = parcel.readInt();
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.mInitialExpandedChildrenCount);
        }
    }
}
