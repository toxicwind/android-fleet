package androidx.core.widget;

import android.os.Build;
import android.widget.ListView;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public class ListViewAutoScrollHelper extends AutoScrollHelper {
    public final ListView mTarget;

    public ListViewAutoScrollHelper(@NonNull ListView target) {
        super(target);
        this.mTarget = target;
    }

    @Override // androidx.core.widget.AutoScrollHelper
    public boolean canTargetScrollHorizontally(int direction) {
        return false;
    }

    @Override // androidx.core.widget.AutoScrollHelper
    public boolean canTargetScrollVertically(int direction) {
        ListView listView = this.mTarget;
        int count = listView.getCount();
        if (count == 0) {
            return false;
        }
        int childCount = listView.getChildCount();
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int i = firstVisiblePosition + childCount;
        if (direction > 0) {
            if (i >= count && listView.getChildAt(childCount - 1).getBottom() <= listView.getHeight()) {
                return false;
            }
        } else {
            if (direction >= 0) {
                return false;
            }
            if (firstVisiblePosition <= 0 && listView.getChildAt(0).getTop() >= 0) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.core.widget.AutoScrollHelper
    public void scrollTargetBy(int deltaX, int deltaY) {
        ListView listView = this.mTarget;
        int i = Build.VERSION.SDK_INT;
        listView.scrollListBy(deltaY);
    }
}
