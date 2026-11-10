package androidx.core.view;

import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class DragStartHelper {
    public boolean mDragging;
    public int mLastTouchX;
    public int mLastTouchY;
    public final OnDragStartListener mListener;
    public final View.OnLongClickListener mLongClickListener = new View.OnLongClickListener() { // from class: androidx.core.view.DragStartHelper.1
        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View v) {
            return DragStartHelper.this.onLongClick(v);
        }
    };
    public final View.OnTouchListener mTouchListener = new View.OnTouchListener() { // from class: androidx.core.view.DragStartHelper.2
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View v, MotionEvent event) {
            return DragStartHelper.this.onTouch(v, event);
        }
    };
    public final View mView;

    public interface OnDragStartListener {
        boolean onDragStart(View v, DragStartHelper helper);
    }

    public DragStartHelper(View view, OnDragStartListener listener) {
        this.mView = view;
        this.mListener = listener;
    }

    public void attach() {
        this.mView.setOnLongClickListener(this.mLongClickListener);
        this.mView.setOnTouchListener(this.mTouchListener);
    }

    public void detach() {
        this.mView.setOnLongClickListener(null);
        this.mView.setOnTouchListener(null);
    }

    public void getTouchPosition(Point point) {
        point.set(this.mLastTouchX, this.mLastTouchY);
    }

    public boolean onLongClick(View v) {
        return this.mListener.onDragStart(v, this);
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0048  */
    public boolean onTouch(View v, MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        int action = event.getAction();
        if (action == 0) {
            this.mLastTouchX = x;
            this.mLastTouchY = y;
        } else if (action == 1) {
            this.mDragging = false;
        } else if (action != 2) {
            if (action == 3) {
                this.mDragging = false;
            }
        } else if (MotionEventCompat.isFromSource(event, 8194) && (event.getButtonState() & 1) != 0 && !this.mDragging && (this.mLastTouchX != x || this.mLastTouchY != y)) {
            this.mLastTouchX = x;
            this.mLastTouchY = y;
            this.mDragging = this.mListener.onDragStart(v, this);
            return this.mDragging;
        }
        return false;
    }
}
