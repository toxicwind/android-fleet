package androidx.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;

/* JADX INFO: loaded from: classes.dex */
public class ContentLoadingProgressBar extends ProgressBar {
    public static final int MIN_DELAY_MS = 500;
    public static final int MIN_SHOW_TIME_MS = 500;
    public final Runnable mDelayedHide;
    public final Runnable mDelayedShow;
    public boolean mDismissed;
    public boolean mPostedHide;
    public boolean mPostedShow;
    public long mStartTime;

    public ContentLoadingProgressBar(@NonNull Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @UiThread
    public void hideOnUiThread() {
        this.mDismissed = true;
        removeCallbacks(this.mDelayedShow);
        this.mPostedShow = false;
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = this.mStartTime;
        long j2 = jCurrentTimeMillis - j;
        if (j2 >= 500 || j == -1) {
            setVisibility(8);
        } else {
            if (this.mPostedHide) {
                return;
            }
            postDelayed(this.mDelayedHide, 500 - j2);
            this.mPostedHide = true;
        }
    }

    private void removeCallbacks() {
        removeCallbacks(this.mDelayedHide);
        removeCallbacks(this.mDelayedShow);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @UiThread
    public void showOnUiThread() {
        this.mStartTime = -1L;
        this.mDismissed = false;
        removeCallbacks(this.mDelayedHide);
        this.mPostedHide = false;
        if (this.mPostedShow) {
            return;
        }
        postDelayed(this.mDelayedShow, 500L);
        this.mPostedShow = true;
    }

    public /* synthetic */ void a() {
        this.mPostedHide = false;
        this.mStartTime = -1L;
        setVisibility(8);
    }

    public /* synthetic */ void b() {
        this.mPostedShow = false;
        if (this.mDismissed) {
            return;
        }
        this.mStartTime = System.currentTimeMillis();
        setVisibility(0);
    }

    public void hide() {
        post(new Runnable() { // from class: a.a.a.d
            @Override // java.lang.Runnable
            public final void run() {
                this.f3a.hideOnUiThread();
            }
        });
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        removeCallbacks();
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks();
    }

    public void show() {
        post(new Runnable() { // from class: a.a.a.b
            @Override // java.lang.Runnable
            public final void run() {
                this.f1a.showOnUiThread();
            }
        });
    }

    public ContentLoadingProgressBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        this.mStartTime = -1L;
        this.mPostedHide = false;
        this.mPostedShow = false;
        this.mDismissed = false;
        this.mDelayedHide = new Runnable() { // from class: a.a.a.a
            @Override // java.lang.Runnable
            public final void run() {
                this.f0a.a();
            }
        };
        this.mDelayedShow = new Runnable() { // from class: a.a.a.c
            @Override // java.lang.Runnable
            public final void run() {
                this.f2a.b();
            }
        };
    }
}
