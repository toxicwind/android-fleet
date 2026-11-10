package androidx.core.view.accessibility;

import android.os.Build;
import android.os.Parcelable;
import android.view.View;
import android.view.accessibility.AccessibilityRecord;
import androidx.annotation.NonNull;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AccessibilityRecordCompat {
    public final AccessibilityRecord mRecord;

    @Deprecated
    public AccessibilityRecordCompat(Object record) {
        this.mRecord = (AccessibilityRecord) record;
    }

    @Deprecated
    public static AccessibilityRecordCompat obtain(AccessibilityRecordCompat record) {
        return new AccessibilityRecordCompat(AccessibilityRecord.obtain(record.mRecord));
    }

    @Deprecated
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AccessibilityRecordCompat)) {
            return false;
        }
        AccessibilityRecordCompat accessibilityRecordCompat = (AccessibilityRecordCompat) obj;
        AccessibilityRecord accessibilityRecord = this.mRecord;
        if (accessibilityRecord == null) {
            if (accessibilityRecordCompat.mRecord != null) {
                return false;
            }
        } else if (!accessibilityRecord.equals(accessibilityRecordCompat.mRecord)) {
            return false;
        }
        return true;
    }

    @Deprecated
    public int getAddedCount() {
        return this.mRecord.getAddedCount();
    }

    @Deprecated
    public CharSequence getBeforeText() {
        return this.mRecord.getBeforeText();
    }

    @Deprecated
    public CharSequence getClassName() {
        return this.mRecord.getClassName();
    }

    @Deprecated
    public CharSequence getContentDescription() {
        return this.mRecord.getContentDescription();
    }

    @Deprecated
    public int getCurrentItemIndex() {
        return this.mRecord.getCurrentItemIndex();
    }

    @Deprecated
    public int getFromIndex() {
        return this.mRecord.getFromIndex();
    }

    @Deprecated
    public Object getImpl() {
        return this.mRecord;
    }

    @Deprecated
    public int getItemCount() {
        return this.mRecord.getItemCount();
    }

    @Deprecated
    public int getMaxScrollX() {
        return getMaxScrollX(this.mRecord);
    }

    @Deprecated
    public int getMaxScrollY() {
        return getMaxScrollY(this.mRecord);
    }

    @Deprecated
    public Parcelable getParcelableData() {
        return this.mRecord.getParcelableData();
    }

    @Deprecated
    public int getRemovedCount() {
        return this.mRecord.getRemovedCount();
    }

    @Deprecated
    public int getScrollX() {
        return this.mRecord.getScrollX();
    }

    @Deprecated
    public int getScrollY() {
        return this.mRecord.getScrollY();
    }

    @Deprecated
    public AccessibilityNodeInfoCompat getSource() {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(this.mRecord.getSource());
    }

    @Deprecated
    public List<CharSequence> getText() {
        return this.mRecord.getText();
    }

    @Deprecated
    public int getToIndex() {
        return this.mRecord.getToIndex();
    }

    @Deprecated
    public int getWindowId() {
        return this.mRecord.getWindowId();
    }

    @Deprecated
    public int hashCode() {
        AccessibilityRecord accessibilityRecord = this.mRecord;
        if (accessibilityRecord == null) {
            return 0;
        }
        return accessibilityRecord.hashCode();
    }

    @Deprecated
    public boolean isChecked() {
        return this.mRecord.isChecked();
    }

    @Deprecated
    public boolean isEnabled() {
        return this.mRecord.isEnabled();
    }

    @Deprecated
    public boolean isFullScreen() {
        return this.mRecord.isFullScreen();
    }

    @Deprecated
    public boolean isPassword() {
        return this.mRecord.isPassword();
    }

    @Deprecated
    public boolean isScrollable() {
        return this.mRecord.isScrollable();
    }

    @Deprecated
    public void recycle() {
        this.mRecord.recycle();
    }

    @Deprecated
    public void setAddedCount(int addedCount) {
        this.mRecord.setAddedCount(addedCount);
    }

    @Deprecated
    public void setBeforeText(CharSequence beforeText) {
        this.mRecord.setBeforeText(beforeText);
    }

    @Deprecated
    public void setChecked(boolean isChecked) {
        this.mRecord.setChecked(isChecked);
    }

    @Deprecated
    public void setClassName(CharSequence className) {
        this.mRecord.setClassName(className);
    }

    @Deprecated
    public void setContentDescription(CharSequence contentDescription) {
        this.mRecord.setContentDescription(contentDescription);
    }

    @Deprecated
    public void setCurrentItemIndex(int currentItemIndex) {
        this.mRecord.setCurrentItemIndex(currentItemIndex);
    }

    @Deprecated
    public void setEnabled(boolean isEnabled) {
        this.mRecord.setEnabled(isEnabled);
    }

    @Deprecated
    public void setFromIndex(int fromIndex) {
        this.mRecord.setFromIndex(fromIndex);
    }

    @Deprecated
    public void setFullScreen(boolean isFullScreen) {
        this.mRecord.setFullScreen(isFullScreen);
    }

    @Deprecated
    public void setItemCount(int itemCount) {
        this.mRecord.setItemCount(itemCount);
    }

    @Deprecated
    public void setMaxScrollX(int maxScrollX) {
        AccessibilityRecord accessibilityRecord = this.mRecord;
        int i = Build.VERSION.SDK_INT;
        accessibilityRecord.setMaxScrollX(maxScrollX);
    }

    @Deprecated
    public void setMaxScrollY(int maxScrollY) {
        AccessibilityRecord accessibilityRecord = this.mRecord;
        int i = Build.VERSION.SDK_INT;
        accessibilityRecord.setMaxScrollY(maxScrollY);
    }

    @Deprecated
    public void setParcelableData(Parcelable parcelableData) {
        this.mRecord.setParcelableData(parcelableData);
    }

    @Deprecated
    public void setPassword(boolean isPassword) {
        this.mRecord.setPassword(isPassword);
    }

    @Deprecated
    public void setRemovedCount(int removedCount) {
        this.mRecord.setRemovedCount(removedCount);
    }

    @Deprecated
    public void setScrollX(int scrollX) {
        this.mRecord.setScrollX(scrollX);
    }

    @Deprecated
    public void setScrollY(int scrollY) {
        this.mRecord.setScrollY(scrollY);
    }

    @Deprecated
    public void setScrollable(boolean scrollable) {
        this.mRecord.setScrollable(scrollable);
    }

    @Deprecated
    public void setSource(View source) {
        this.mRecord.setSource(source);
    }

    @Deprecated
    public void setToIndex(int toIndex) {
        this.mRecord.setToIndex(toIndex);
    }

    public static int getMaxScrollX(AccessibilityRecord record) {
        int i = Build.VERSION.SDK_INT;
        return record.getMaxScrollX();
    }

    public static int getMaxScrollY(AccessibilityRecord record) {
        int i = Build.VERSION.SDK_INT;
        return record.getMaxScrollY();
    }

    @Deprecated
    public static AccessibilityRecordCompat obtain() {
        return new AccessibilityRecordCompat(AccessibilityRecord.obtain());
    }

    @Deprecated
    public void setSource(View root, int virtualDescendantId) {
        AccessibilityRecord accessibilityRecord = this.mRecord;
        int i = Build.VERSION.SDK_INT;
        accessibilityRecord.setSource(root, virtualDescendantId);
    }

    public static void setMaxScrollX(AccessibilityRecord record, int maxScrollX) {
        int i = Build.VERSION.SDK_INT;
        record.setMaxScrollX(maxScrollX);
    }

    public static void setMaxScrollY(AccessibilityRecord record, int maxScrollY) {
        int i = Build.VERSION.SDK_INT;
        record.setMaxScrollY(maxScrollY);
    }

    public static void setSource(@NonNull AccessibilityRecord record, View root, int virtualDescendantId) {
        int i = Build.VERSION.SDK_INT;
        record.setSource(root, virtualDescendantId);
    }
}
