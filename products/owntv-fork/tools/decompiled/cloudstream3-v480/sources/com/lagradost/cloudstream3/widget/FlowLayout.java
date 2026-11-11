package com.lagradost.cloudstream3.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.lagradost.cloudstream3.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FlowLayout.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001!B\u0013\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u001b\b\u0011\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\u0004\u0010\tJ\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000bH\u0014J0\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u000bH\u0014J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\u001d\u001a\u00020\u001cH\u0014J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001fH\u0014J\u0010\u0010 \u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u001fH\u0014R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\""}, d2 = {"Lcom/lagradost/cloudstream3/widget/FlowLayout;", "Landroid/view/ViewGroup;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "c", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "itemSpacing", "", "getItemSpacing", "()I", "setItemSpacing", "(I)V", "onMeasure", "", "widthMeasureSpec", "heightMeasureSpec", "onLayout", "b", "", TtmlNode.LEFT, "top", TtmlNode.RIGHT, "bottom", "generateLayoutParams", "Lcom/lagradost/cloudstream3/widget/FlowLayout$LayoutParams;", "generateDefaultLayoutParams", TtmlNode.TAG_P, "Landroid/view/ViewGroup$LayoutParams;", "checkLayoutParams", "LayoutParams", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class FlowLayout extends ViewGroup {
    private int itemSpacing;

    public final int getItemSpacing() {
        return this.itemSpacing;
    }

    public final void setItemSpacing(int i) {
        this.itemSpacing = i;
    }

    public FlowLayout(Context context) {
        super(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowLayout(Context c, AttributeSet attributeSet) {
        super(c, attributeSet);
        Intrinsics.checkNotNullParameter(c, "c");
        int[] FlowLayout_Layout = R.styleable.FlowLayout_Layout;
        Intrinsics.checkNotNullExpressionValue(FlowLayout_Layout, "FlowLayout_Layout");
        TypedArray typedArrayObtainStyledAttributes = c.obtainStyledAttributes(attributeSet, FlowLayout_Layout, 0, 0);
        this.itemSpacing = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.FlowLayout_Layout_itemSpacing, 0);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        int childCount = getChildCount();
        int i = 0;
        int iMax = 0;
        int iMax2 = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            Intrinsics.checkNotNull(childAt);
            if (childAt.getVisibility() == 0) {
                measureChild(childAt, widthMeasureSpec, heightMeasureSpec);
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i4 = i + measuredWidth;
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if ((i4 - (layoutParams instanceof ViewGroup.MarginLayoutParams ? ((ViewGroup.MarginLayoutParams) layoutParams).getMarginEnd() : 0)) - childAt.getPaddingEnd() > size) {
                    iMax = Math.max(iMax, i);
                    i2 += this.itemSpacing + measuredHeight;
                    i = 0;
                }
                iMax2 = Math.max(iMax2, measuredHeight + i2);
                int i5 = i + measuredWidth;
                int i6 = measuredWidth == 0 ? 0 : this.itemSpacing;
                ViewGroup.LayoutParams layoutParams2 = childAt.getLayoutParams();
                Intrinsics.checkNotNull(layoutParams2, "null cannot be cast to non-null type com.lagradost.cloudstream3.widget.FlowLayout.LayoutParams");
                LayoutParams layoutParams3 = (LayoutParams) layoutParams2;
                layoutParams3.setX(i);
                layoutParams3.setY(i2);
                i = i5 + i6;
            }
        }
        setMeasuredDimension(ViewGroup.resolveSize(Math.max(i, iMax), widthMeasureSpec), ViewGroup.resolveSize(iMax2, heightMeasureSpec));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean b, int left, int top, int right, int bottom) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type com.lagradost.cloudstream3.widget.FlowLayout.LayoutParams");
            LayoutParams layoutParams2 = (LayoutParams) layoutParams;
            childAt.layout(layoutParams2.getX(), layoutParams2.getY(), layoutParams2.getX() + childAt.getMeasuredWidth(), layoutParams2.getY() + childAt.getMeasuredHeight());
        }
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        return new LayoutParams(context, attrs);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        Intrinsics.checkNotNullParameter(p, "p");
        return new LayoutParams(p);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        Intrinsics.checkNotNullParameter(p, "p");
        return p instanceof LayoutParams;
    }

    /* JADX INFO: compiled from: FlowLayout.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B\u001b\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\u0019\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u0006\u0010\u000bB\u0013\b\u0016\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u0006\u0010\rB\u0013\b\u0010\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u0006\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014R\u001a\u0010\u0018\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0012\"\u0004\b\u001a\u0010\u0014¨\u0006\u001b"}, d2 = {"Lcom/lagradost/cloudstream3/widget/FlowLayout$LayoutParams;", "Landroid/view/ViewGroup$MarginLayoutParams;", "c", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "width", "", "height", "(II)V", "source", "(Landroid/view/ViewGroup$MarginLayoutParams;)V", "Landroid/view/ViewGroup$LayoutParams;", "(Landroid/view/ViewGroup$LayoutParams;)V", "spacing", "getSpacing", "()I", "setSpacing", "(I)V", "x", "getX", "setX", "y", "getY", "setY", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class LayoutParams extends ViewGroup.MarginLayoutParams {
        private int spacing;
        private int x;
        private int y;

        public final int getSpacing() {
            return this.spacing;
        }

        public final void setSpacing(int i) {
            this.spacing = i;
        }

        public final int getX() {
            return this.x;
        }

        public final void setX(int i) {
            this.x = i;
        }

        public final int getY() {
            return this.y;
        }

        public final void setY(int i) {
            this.y = i;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public LayoutParams(Context c, AttributeSet attributeSet) {
            super(c, attributeSet);
            Intrinsics.checkNotNullParameter(c, "c");
            this.spacing = -1;
            int[] FlowLayout_Layout = R.styleable.FlowLayout_Layout;
            Intrinsics.checkNotNullExpressionValue(FlowLayout_Layout, "FlowLayout_Layout");
            TypedArray typedArrayObtainStyledAttributes = c.obtainStyledAttributes(attributeSet, FlowLayout_Layout, 0, 0);
            this.spacing = 0;
            typedArrayObtainStyledAttributes.recycle();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.spacing = 0;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.spacing = -1;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.spacing = -1;
        }
    }
}
