package androidx.legacy.app;

import android.R;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Build;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class ActionBarDrawerToggle implements DrawerLayout.DrawerListener {
    public static final int ID_HOME = 16908332;
    public static final String TAG = "ActionBarDrawerToggle";
    public static final int[] THEME_ATTRS = {R.attr.homeAsUpIndicator};
    public static final float TOGGLE_DRAWABLE_OFFSET = 0.33333334f;
    public final Activity mActivity;
    public final Delegate mActivityImpl;
    public final int mCloseDrawerContentDescRes;
    public Drawable mDrawerImage;
    public final int mDrawerImageResource;
    public boolean mDrawerIndicatorEnabled;
    public final DrawerLayout mDrawerLayout;
    public boolean mHasCustomUpIndicator;
    public Drawable mHomeAsUpIndicator;
    public final int mOpenDrawerContentDescRes;
    public SetIndicatorInfo mSetIndicatorInfo;
    public SlideDrawable mSlider;

    @Deprecated
    public interface Delegate {
        @Nullable
        Drawable getThemeUpIndicator();

        void setActionBarDescription(@StringRes int i);

        void setActionBarUpIndicator(Drawable drawable, @StringRes int i);
    }

    @Deprecated
    public interface DelegateProvider {
        @Nullable
        Delegate getDrawerToggleDelegate();
    }

    private static class SetIndicatorInfo {
        public Method mSetHomeActionContentDescription;
        public Method mSetHomeAsUpIndicator;
        public ImageView mUpIndicatorView;

        public SetIndicatorInfo(Activity activity) {
            try {
                this.mSetHomeAsUpIndicator = ActionBar.class.getDeclaredMethod("setHomeAsUpIndicator", Drawable.class);
                this.mSetHomeActionContentDescription = ActionBar.class.getDeclaredMethod("setHomeActionContentDescription", Integer.TYPE);
            } catch (NoSuchMethodException unused) {
                View viewFindViewById = activity.findViewById(16908332);
                if (viewFindViewById == null) {
                    return;
                }
                ViewGroup viewGroup = (ViewGroup) viewFindViewById.getParent();
                if (viewGroup.getChildCount() != 2) {
                    return;
                }
                View childAt = viewGroup.getChildAt(0);
                View childAt2 = childAt.getId() != 16908332 ? childAt : viewGroup.getChildAt(1);
                if (childAt2 instanceof ImageView) {
                    this.mUpIndicatorView = (ImageView) childAt2;
                }
            }
        }
    }

    private class SlideDrawable extends InsetDrawable implements Drawable.Callback {
        public final boolean mHasMirroring;
        public float mOffset;
        public float mPosition;
        public final Rect mTmpRect;

        public SlideDrawable(Drawable drawable) {
            super(drawable, 0);
            int i = Build.VERSION.SDK_INT;
            this.mHasMirroring = true;
            this.mTmpRect = new Rect();
        }

        @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
        public void draw(@NonNull Canvas canvas) {
            copyBounds(this.mTmpRect);
            canvas.save();
            boolean z = ViewCompat.getLayoutDirection(ActionBarDrawerToggle.this.mActivity.getWindow().getDecorView()) == 1;
            int i = z ? -1 : 1;
            float fWidth = this.mTmpRect.width();
            canvas.translate((-this.mOffset) * fWidth * this.mPosition * i, 0.0f);
            if (z && !this.mHasMirroring) {
                canvas.translate(fWidth, 0.0f);
                canvas.scale(-1.0f, 1.0f);
            }
            super.draw(canvas);
            canvas.restore();
        }

        public float getPosition() {
            return this.mPosition;
        }

        public void setOffset(float f2) {
            this.mOffset = f2;
            invalidateSelf();
        }

        public void setPosition(float f2) {
            this.mPosition = f2;
            invalidateSelf();
        }
    }

    public ActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, @DrawableRes int i, @StringRes int i2, @StringRes int i3) {
        this(activity, drawerLayout, !assumeMaterial(activity), i, i2, i3);
    }

    public static boolean assumeMaterial(Context context) {
        if (context.getApplicationInfo().targetSdkVersion < 21) {
            return false;
        }
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    private Drawable getThemeUpIndicator() {
        Delegate delegate = this.mActivityImpl;
        if (delegate != null) {
            return delegate.getThemeUpIndicator();
        }
        int i = Build.VERSION.SDK_INT;
        ActionBar actionBar = this.mActivity.getActionBar();
        TypedArray typedArrayObtainStyledAttributes = (actionBar != null ? actionBar.getThemedContext() : this.mActivity).obtainStyledAttributes(null, THEME_ATTRS, R.attr.actionBarStyle, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(0);
        typedArrayObtainStyledAttributes.recycle();
        return drawable;
    }

    private void setActionBarDescription(int i) {
        Delegate delegate = this.mActivityImpl;
        if (delegate != null) {
            delegate.setActionBarDescription(i);
            return;
        }
        int i2 = Build.VERSION.SDK_INT;
        ActionBar actionBar = this.mActivity.getActionBar();
        if (actionBar != null) {
            actionBar.setHomeActionContentDescription(i);
        }
    }

    private void setActionBarUpIndicator(Drawable drawable, int i) {
        Delegate delegate = this.mActivityImpl;
        if (delegate != null) {
            delegate.setActionBarUpIndicator(drawable, i);
            return;
        }
        int i2 = Build.VERSION.SDK_INT;
        ActionBar actionBar = this.mActivity.getActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(drawable);
            actionBar.setHomeActionContentDescription(i);
        }
    }

    public boolean isDrawerIndicatorEnabled() {
        return this.mDrawerIndicatorEnabled;
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (!this.mHasCustomUpIndicator) {
            this.mHomeAsUpIndicator = getThemeUpIndicator();
        }
        this.mDrawerImage = ContextCompat.getDrawable(this.mActivity, this.mDrawerImageResource);
        syncState();
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void onDrawerClosed(View view) {
        this.mSlider.setPosition(0.0f);
        if (this.mDrawerIndicatorEnabled) {
            setActionBarDescription(this.mOpenDrawerContentDescRes);
        }
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void onDrawerOpened(View view) {
        this.mSlider.setPosition(1.0f);
        if (this.mDrawerIndicatorEnabled) {
            setActionBarDescription(this.mCloseDrawerContentDescRes);
        }
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void onDrawerSlide(View view, float f2) {
        float position = this.mSlider.getPosition();
        this.mSlider.setPosition(f2 > 0.5f ? Math.max(position, Math.max(0.0f, f2 - 0.5f) * 2.0f) : Math.min(position, f2 * 2.0f));
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void onDrawerStateChanged(int i) {
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem == null || menuItem.getItemId() != 16908332 || !this.mDrawerIndicatorEnabled) {
            return false;
        }
        if (this.mDrawerLayout.isDrawerVisible(GravityCompat.START)) {
            this.mDrawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
        this.mDrawerLayout.openDrawer(GravityCompat.START);
        return true;
    }

    public void setDrawerIndicatorEnabled(boolean z) {
        if (z != this.mDrawerIndicatorEnabled) {
            if (z) {
                setActionBarUpIndicator(this.mSlider, this.mDrawerLayout.isDrawerOpen(GravityCompat.START) ? this.mCloseDrawerContentDescRes : this.mOpenDrawerContentDescRes);
            } else {
                setActionBarUpIndicator(this.mHomeAsUpIndicator, 0);
            }
            this.mDrawerIndicatorEnabled = z;
        }
    }

    public void setHomeAsUpIndicator(Drawable drawable) {
        if (drawable == null) {
            this.mHomeAsUpIndicator = getThemeUpIndicator();
            this.mHasCustomUpIndicator = false;
        } else {
            this.mHomeAsUpIndicator = drawable;
            this.mHasCustomUpIndicator = true;
        }
        if (this.mDrawerIndicatorEnabled) {
            return;
        }
        setActionBarUpIndicator(this.mHomeAsUpIndicator, 0);
    }

    public void syncState() {
        if (this.mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.mSlider.setPosition(1.0f);
        } else {
            this.mSlider.setPosition(0.0f);
        }
        if (this.mDrawerIndicatorEnabled) {
            setActionBarUpIndicator(this.mSlider, this.mDrawerLayout.isDrawerOpen(GravityCompat.START) ? this.mCloseDrawerContentDescRes : this.mOpenDrawerContentDescRes);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, boolean z, @DrawableRes int i, @StringRes int i2, @StringRes int i3) {
        this.mDrawerIndicatorEnabled = true;
        this.mActivity = activity;
        if (activity instanceof DelegateProvider) {
            this.mActivityImpl = ((DelegateProvider) activity).getDrawerToggleDelegate();
        } else {
            this.mActivityImpl = null;
        }
        this.mDrawerLayout = drawerLayout;
        this.mDrawerImageResource = i;
        this.mOpenDrawerContentDescRes = i2;
        this.mCloseDrawerContentDescRes = i3;
        this.mHomeAsUpIndicator = getThemeUpIndicator();
        this.mDrawerImage = ContextCompat.getDrawable(activity, i);
        this.mSlider = new SlideDrawable(this.mDrawerImage);
        this.mSlider.setOffset(z ? 0.33333334f : 0.0f);
    }

    public void setHomeAsUpIndicator(int i) {
        setHomeAsUpIndicator(i != 0 ? ContextCompat.getDrawable(this.mActivity, i) : null);
    }
}
