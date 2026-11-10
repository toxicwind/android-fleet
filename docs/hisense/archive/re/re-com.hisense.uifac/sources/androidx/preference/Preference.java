package androidx.preference;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.AbsSavedState;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.res.TypedArrayUtils;
import java.util.List;
import kotlin.jvm.internal.IntCompanionObject;

/* JADX INFO: loaded from: classes.dex */
public class Preference implements Comparable<Preference> {
    private Context mContext;
    private Object mDefaultValue;
    private boolean mDependencyMet;
    private List<Preference> mDependents;
    private boolean mEnabled;
    private String mFragment;
    private boolean mHasSingleLineTitleAttr;
    private Drawable mIcon;
    private int mIconResId;
    private boolean mIconSpaceReserved;
    private Intent mIntent;
    private String mKey;
    private OnPreferenceChangeInternalListener mListener;
    private OnPreferenceChangeListener mOnChangeListener;
    private OnPreferenceClickListener mOnClickListener;
    private int mOrder;
    private boolean mParentDependencyMet;
    private boolean mPersistent;
    private PreferenceDataStore mPreferenceDataStore;
    private PreferenceManager mPreferenceManager;
    private boolean mRequiresKey;
    private boolean mSelectable;
    private boolean mShouldDisableView;
    private CharSequence mSummary;
    private SummaryProvider mSummaryProvider;
    private CharSequence mTitle;
    private int mWidgetLayoutResId;

    interface OnPreferenceChangeInternalListener {
        void onPreferenceChange(Preference preference);

        void onPreferenceHierarchyChange(Preference preference);
    }

    public interface OnPreferenceChangeListener {
        boolean onPreferenceChange(Preference preference, Object obj);
    }

    public interface OnPreferenceClickListener {
    }

    public interface SummaryProvider<T extends Preference> {
        CharSequence provideSummary(T t);
    }

    protected Object onGetDefaultValue(TypedArray typedArray, int i) {
        return null;
    }

    public void setLayoutResource(int i) {
    }

    public Preference(Context context, AttributeSet attributeSet, int i, int i2) {
        this.mOrder = IntCompanionObject.MAX_VALUE;
        this.mEnabled = true;
        this.mSelectable = true;
        this.mPersistent = true;
        this.mDependencyMet = true;
        this.mParentDependencyMet = true;
        this.mShouldDisableView = true;
        this.mContext = context;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Preference, i, i2);
        this.mIconResId = TypedArrayUtils.getResourceId(typedArrayObtainStyledAttributes, R$styleable.Preference_icon, R$styleable.Preference_android_icon, 0);
        this.mKey = TypedArrayUtils.getString(typedArrayObtainStyledAttributes, R$styleable.Preference_key, R$styleable.Preference_android_key);
        this.mTitle = TypedArrayUtils.getText(typedArrayObtainStyledAttributes, R$styleable.Preference_title, R$styleable.Preference_android_title);
        this.mSummary = TypedArrayUtils.getText(typedArrayObtainStyledAttributes, R$styleable.Preference_summary, R$styleable.Preference_android_summary);
        this.mOrder = TypedArrayUtils.getInt(typedArrayObtainStyledAttributes, R$styleable.Preference_order, R$styleable.Preference_android_order, IntCompanionObject.MAX_VALUE);
        this.mFragment = TypedArrayUtils.getString(typedArrayObtainStyledAttributes, R$styleable.Preference_fragment, R$styleable.Preference_android_fragment);
        TypedArrayUtils.getResourceId(typedArrayObtainStyledAttributes, R$styleable.Preference_layout, R$styleable.Preference_android_layout, R$layout.preference);
        this.mWidgetLayoutResId = TypedArrayUtils.getResourceId(typedArrayObtainStyledAttributes, R$styleable.Preference_widgetLayout, R$styleable.Preference_android_widgetLayout, 0);
        this.mEnabled = TypedArrayUtils.getBoolean(typedArrayObtainStyledAttributes, R$styleable.Preference_enabled, R$styleable.Preference_android_enabled, true);
        this.mSelectable = TypedArrayUtils.getBoolean(typedArrayObtainStyledAttributes, R$styleable.Preference_selectable, R$styleable.Preference_android_selectable, true);
        this.mPersistent = TypedArrayUtils.getBoolean(typedArrayObtainStyledAttributes, R$styleable.Preference_persistent, R$styleable.Preference_android_persistent, true);
        TypedArrayUtils.getString(typedArrayObtainStyledAttributes, R$styleable.Preference_dependency, R$styleable.Preference_android_dependency);
        int i3 = R$styleable.Preference_allowDividerAbove;
        TypedArrayUtils.getBoolean(typedArrayObtainStyledAttributes, i3, i3, this.mSelectable);
        int i4 = R$styleable.Preference_allowDividerBelow;
        TypedArrayUtils.getBoolean(typedArrayObtainStyledAttributes, i4, i4, this.mSelectable);
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.Preference_defaultValue)) {
            this.mDefaultValue = onGetDefaultValue(typedArrayObtainStyledAttributes, R$styleable.Preference_defaultValue);
        } else if (typedArrayObtainStyledAttributes.hasValue(R$styleable.Preference_android_defaultValue)) {
            this.mDefaultValue = onGetDefaultValue(typedArrayObtainStyledAttributes, R$styleable.Preference_android_defaultValue);
        }
        this.mShouldDisableView = TypedArrayUtils.getBoolean(typedArrayObtainStyledAttributes, R$styleable.Preference_shouldDisableView, R$styleable.Preference_android_shouldDisableView, true);
        boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(R$styleable.Preference_singleLineTitle);
        this.mHasSingleLineTitleAttr = zHasValue;
        if (zHasValue) {
            TypedArrayUtils.getBoolean(typedArrayObtainStyledAttributes, R$styleable.Preference_singleLineTitle, R$styleable.Preference_android_singleLineTitle, true);
        }
        this.mIconSpaceReserved = TypedArrayUtils.getBoolean(typedArrayObtainStyledAttributes, R$styleable.Preference_iconSpaceReserved, R$styleable.Preference_android_iconSpaceReserved, false);
        int i5 = R$styleable.Preference_isPreferenceVisible;
        TypedArrayUtils.getBoolean(typedArrayObtainStyledAttributes, i5, i5, true);
        int i6 = R$styleable.Preference_enableCopying;
        TypedArrayUtils.getBoolean(typedArrayObtainStyledAttributes, i6, i6, false);
        typedArrayObtainStyledAttributes.recycle();
    }

    public Preference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public Preference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, TypedArrayUtils.getAttr(context, R$attr.preferenceStyle, R.attr.preferenceStyle));
    }

    public Preference(Context context) {
        this(context, null);
    }

    public void setIntent(Intent intent) {
        this.mIntent = intent;
    }

    public PreferenceDataStore getPreferenceDataStore() {
        PreferenceDataStore preferenceDataStore = this.mPreferenceDataStore;
        if (preferenceDataStore != null) {
            return preferenceDataStore;
        }
        PreferenceManager preferenceManager = this.mPreferenceManager;
        if (preferenceManager != null) {
            return preferenceManager.getPreferenceDataStore();
        }
        return null;
    }

    public void setWidgetLayoutResource(int i) {
        this.mWidgetLayoutResId = i;
    }

    public final int getWidgetLayoutResource() {
        return this.mWidgetLayoutResId;
    }

    public void setOrder(int i) {
        if (i != this.mOrder) {
            this.mOrder = i;
            notifyHierarchyChanged();
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (TextUtils.equals(charSequence, this.mTitle)) {
            return;
        }
        this.mTitle = charSequence;
        notifyChanged();
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public void setIcon(Drawable drawable) {
        if (this.mIcon != drawable) {
            this.mIcon = drawable;
            this.mIconResId = 0;
            notifyChanged();
        }
    }

    public void setIcon(int i) {
        setIcon(AppCompatResources.getDrawable(this.mContext, i));
        this.mIconResId = i;
    }

    public Drawable getIcon() {
        int i;
        if (this.mIcon == null && (i = this.mIconResId) != 0) {
            this.mIcon = AppCompatResources.getDrawable(this.mContext, i);
        }
        return this.mIcon;
    }

    public CharSequence getSummary() {
        if (getSummaryProvider() != null) {
            return getSummaryProvider().provideSummary(this);
        }
        return this.mSummary;
    }

    public void setSummary(CharSequence charSequence) {
        if (getSummaryProvider() != null) {
            throw new IllegalStateException("Preference already has a SummaryProvider set.");
        }
        if (TextUtils.equals(this.mSummary, charSequence)) {
            return;
        }
        this.mSummary = charSequence;
        notifyChanged();
    }

    public boolean isEnabled() {
        return this.mEnabled && this.mDependencyMet && this.mParentDependencyMet;
    }

    public void setSelectable(boolean z) {
        if (this.mSelectable != z) {
            this.mSelectable = z;
            notifyChanged();
        }
    }

    public void setShouldDisableView(boolean z) {
        if (this.mShouldDisableView != z) {
            this.mShouldDisableView = z;
            notifyChanged();
        }
    }

    public void setKey(String str) {
        this.mKey = str;
        if (!this.mRequiresKey || hasKey()) {
            return;
        }
        requireKey();
    }

    public String getKey() {
        return this.mKey;
    }

    void requireKey() {
        if (TextUtils.isEmpty(this.mKey)) {
            throw new IllegalStateException("Preference does not have a key assigned.");
        }
        this.mRequiresKey = true;
    }

    public boolean hasKey() {
        return !TextUtils.isEmpty(this.mKey);
    }

    public boolean isPersistent() {
        return this.mPersistent;
    }

    protected boolean shouldPersist() {
        return this.mPreferenceManager != null && isPersistent() && hasKey();
    }

    public void setPersistent(boolean z) {
        this.mPersistent = z;
    }

    public void setIconSpaceReserved(boolean z) {
        if (this.mIconSpaceReserved != z) {
            this.mIconSpaceReserved = z;
            notifyChanged();
        }
    }

    public final void setSummaryProvider(SummaryProvider summaryProvider) {
        this.mSummaryProvider = summaryProvider;
        notifyChanged();
    }

    public final SummaryProvider getSummaryProvider() {
        return this.mSummaryProvider;
    }

    public boolean callChangeListener(Object obj) {
        OnPreferenceChangeListener onPreferenceChangeListener = this.mOnChangeListener;
        return onPreferenceChangeListener == null || onPreferenceChangeListener.onPreferenceChange(this, obj);
    }

    public void setOnPreferenceChangeListener(OnPreferenceChangeListener onPreferenceChangeListener) {
        this.mOnChangeListener = onPreferenceChangeListener;
    }

    public void setOnPreferenceClickListener(OnPreferenceClickListener onPreferenceClickListener) {
        this.mOnClickListener = onPreferenceClickListener;
    }

    public Context getContext() {
        return this.mContext;
    }

    @Override // java.lang.Comparable
    public int compareTo(Preference preference) {
        int i = this.mOrder;
        int i2 = preference.mOrder;
        if (i != i2) {
            return i - i2;
        }
        CharSequence charSequence = this.mTitle;
        CharSequence charSequence2 = preference.mTitle;
        if (charSequence == charSequence2) {
            return 0;
        }
        if (charSequence == null) {
            return 1;
        }
        if (charSequence2 == null) {
            return -1;
        }
        return charSequence.toString().compareToIgnoreCase(preference.mTitle.toString());
    }

    protected void notifyChanged() {
        OnPreferenceChangeInternalListener onPreferenceChangeInternalListener = this.mListener;
        if (onPreferenceChangeInternalListener != null) {
            onPreferenceChangeInternalListener.onPreferenceChange(this);
        }
    }

    protected void notifyHierarchyChanged() {
        OnPreferenceChangeInternalListener onPreferenceChangeInternalListener = this.mListener;
        if (onPreferenceChangeInternalListener != null) {
            onPreferenceChangeInternalListener.onPreferenceHierarchyChange(this);
        }
    }

    public void notifyDependencyChange(boolean z) {
        List<Preference> list = this.mDependents;
        if (list == null) {
            return;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            list.get(i).onDependencyChanged(this, z);
        }
    }

    public void onDependencyChanged(Preference preference, boolean z) {
        if (this.mDependencyMet == z) {
            this.mDependencyMet = !z;
            notifyDependencyChange(shouldDisableDependents());
            notifyChanged();
        }
    }

    public void onParentChanged(Preference preference, boolean z) {
        if (this.mParentDependencyMet == z) {
            this.mParentDependencyMet = !z;
            notifyDependencyChange(shouldDisableDependents());
            notifyChanged();
        }
    }

    public boolean shouldDisableDependents() {
        return !isEnabled();
    }

    private void tryCommit(SharedPreferences.Editor editor) {
        if (this.mPreferenceManager.shouldCommit()) {
            editor.apply();
        }
    }

    protected boolean persistString(String str) {
        if (!shouldPersist()) {
            return false;
        }
        if (TextUtils.equals(str, getPersistedString(null))) {
            return true;
        }
        PreferenceDataStore preferenceDataStore = getPreferenceDataStore();
        if (preferenceDataStore != null) {
            preferenceDataStore.putString(this.mKey, str);
        } else {
            SharedPreferences.Editor editor = this.mPreferenceManager.getEditor();
            editor.putString(this.mKey, str);
            tryCommit(editor);
        }
        return true;
    }

    protected String getPersistedString(String str) {
        if (!shouldPersist()) {
            return str;
        }
        PreferenceDataStore preferenceDataStore = getPreferenceDataStore();
        if (preferenceDataStore != null) {
            return preferenceDataStore.getString(this.mKey, str);
        }
        return this.mPreferenceManager.getSharedPreferences().getString(this.mKey, str);
    }

    protected boolean persistBoolean(boolean z) {
        if (!shouldPersist()) {
            return false;
        }
        if (z == getPersistedBoolean(!z)) {
            return true;
        }
        PreferenceDataStore preferenceDataStore = getPreferenceDataStore();
        if (preferenceDataStore != null) {
            preferenceDataStore.putBoolean(this.mKey, z);
        } else {
            SharedPreferences.Editor editor = this.mPreferenceManager.getEditor();
            editor.putBoolean(this.mKey, z);
            tryCommit(editor);
        }
        return true;
    }

    protected boolean getPersistedBoolean(boolean z) {
        if (!shouldPersist()) {
            return z;
        }
        PreferenceDataStore preferenceDataStore = getPreferenceDataStore();
        if (preferenceDataStore != null) {
            return preferenceDataStore.getBoolean(this.mKey, z);
        }
        return this.mPreferenceManager.getSharedPreferences().getBoolean(this.mKey, z);
    }

    public String toString() {
        return getFilterableStringBuilder().toString();
    }

    StringBuilder getFilterableStringBuilder() {
        StringBuilder sb = new StringBuilder();
        CharSequence title = getTitle();
        if (!TextUtils.isEmpty(title)) {
            sb.append(title);
            sb.append(' ');
        }
        CharSequence summary = getSummary();
        if (!TextUtils.isEmpty(summary)) {
            sb.append(summary);
            sb.append(' ');
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb;
    }

    public static class BaseSavedState extends AbsSavedState {
        public static final Parcelable.Creator<BaseSavedState> CREATOR = new Parcelable.Creator<BaseSavedState>() { // from class: androidx.preference.Preference.BaseSavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public BaseSavedState createFromParcel(Parcel parcel) {
                return new BaseSavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public BaseSavedState[] newArray(int i) {
                return new BaseSavedState[i];
            }
        };

        public BaseSavedState(Parcel parcel) {
            super(parcel);
        }
    }
}
