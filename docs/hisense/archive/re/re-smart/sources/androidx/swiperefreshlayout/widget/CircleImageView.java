package androidx.swiperefreshlayout.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.view.animation.Animation;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes.dex */
public class CircleImageView extends ImageView {
    public static final int FILL_SHADOW_COLOR = 1023410176;
    public static final int KEY_SHADOW_COLOR = 503316480;
    public static final int SHADOW_ELEVATION = 4;
    public static final float SHADOW_RADIUS = 3.5f;
    public static final float X_OFFSET = 0.0f;
    public static final float Y_OFFSET = 1.75f;
    public Animation.AnimationListener mListener;
    public int mShadowRadius;

    private class OvalShadow extends OvalShape {
        public RadialGradient mRadialGradient;
        public Paint mShadowPaint = new Paint();

        public OvalShadow(int i) {
            CircleImageView.this.mShadowRadius = i;
            updateRadialGradient((int) rect().width());
        }

        private void updateRadialGradient(int i) {
            float f2 = i / 2;
            this.mRadialGradient = new RadialGradient(f2, f2, CircleImageView.this.mShadowRadius, new int[]{CircleImageView.FILL_SHADOW_COLOR, 0}, (float[]) null, Shader.TileMode.CLAMP);
            this.mShadowPaint.setShader(this.mRadialGradient);
        }

        @Override // android.graphics.drawable.shapes.OvalShape, android.graphics.drawable.shapes.RectShape, android.graphics.drawable.shapes.Shape
        public void draw(Canvas canvas, Paint paint) {
            int width = CircleImageView.this.getWidth() / 2;
            float f2 = width;
            float height = CircleImageView.this.getHeight() / 2;
            canvas.drawCircle(f2, height, f2, this.mShadowPaint);
            canvas.drawCircle(f2, height, width - CircleImageView.this.mShadowRadius, paint);
        }

        @Override // android.graphics.drawable.shapes.RectShape, android.graphics.drawable.shapes.Shape
        public void onResize(float f2, float f3) {
            super.onResize(f2, f3);
            updateRadialGradient((int) f2);
        }
    }

    public CircleImageView(Context context, int i) {
        super(context);
        float f2 = getContext().getResources().getDisplayMetrics().density;
        this.mShadowRadius = (int) (3.5f * f2);
        int i2 = Build.VERSION.SDK_INT;
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        ViewCompat.setElevation(this, f2 * 4.0f);
        shapeDrawable.getPaint().setColor(i);
        ViewCompat.setBackground(this, shapeDrawable);
    }

    private boolean elevationSupported() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @Override // android.view.View
    public void onAnimationEnd() {
        super.onAnimationEnd();
        Animation.AnimationListener animationListener = this.mListener;
        if (animationListener != null) {
            animationListener.onAnimationEnd(getAnimation());
        }
    }

    @Override // android.view.View
    public void onAnimationStart() {
        super.onAnimationStart();
        Animation.AnimationListener animationListener = this.mListener;
        if (animationListener != null) {
            animationListener.onAnimationStart(getAnimation());
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int i3 = Build.VERSION.SDK_INT;
    }

    public void setAnimationListener(Animation.AnimationListener animationListener) {
        this.mListener = animationListener;
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        if (getBackground() instanceof ShapeDrawable) {
            ((ShapeDrawable) getBackground()).getPaint().setColor(i);
        }
    }

    public void setBackgroundColorRes(int i) {
        setBackgroundColor(ContextCompat.getColor(getContext(), i));
    }
}
