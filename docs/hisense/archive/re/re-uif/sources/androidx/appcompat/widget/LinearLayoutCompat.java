package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.appcompat.R$styleable;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes.dex */
public class LinearLayoutCompat extends ViewGroup {
    private boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mGravity;
    private int[] mMaxAscent;
    private int[] mMaxDescent;
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;

    int getChildrenSkipCount(View view, int i) {
        return 0;
    }

    int getLocationOffset(View view) {
        return 0;
    }

    int getNextLocationOffset(View view) {
        return 0;
    }

    int measureNullChild(int i) {
        return 0;
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public LinearLayoutCompat(Context context) {
        this(context, null);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBaselineAligned = true;
        this.mBaselineAlignedChildIndex = -1;
        this.mBaselineChildTop = 0;
        this.mGravity = 8388659;
        TintTypedArray tintTypedArrayObtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, R$styleable.LinearLayoutCompat, i, 0);
        ViewCompat.saveAttributeDataForStyleable(this, context, R$styleable.LinearLayoutCompat, attributeSet, tintTypedArrayObtainStyledAttributes.getWrappedTypeArray(), i, 0);
        int i2 = tintTypedArrayObtainStyledAttributes.getInt(R$styleable.LinearLayoutCompat_android_orientation, -1);
        if (i2 >= 0) {
            setOrientation(i2);
        }
        int i3 = tintTypedArrayObtainStyledAttributes.getInt(R$styleable.LinearLayoutCompat_android_gravity, -1);
        if (i3 >= 0) {
            setGravity(i3);
        }
        boolean z = tintTypedArrayObtainStyledAttributes.getBoolean(R$styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (!z) {
            setBaselineAligned(z);
        }
        this.mWeightSum = tintTypedArrayObtainStyledAttributes.getFloat(R$styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.mBaselineAlignedChildIndex = tintTypedArrayObtainStyledAttributes.getInt(R$styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.mUseLargestChild = tintTypedArrayObtainStyledAttributes.getBoolean(R$styleable.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(tintTypedArrayObtainStyledAttributes.getDrawable(R$styleable.LinearLayoutCompat_divider));
        this.mShowDividers = tintTypedArrayObtainStyledAttributes.getInt(R$styleable.LinearLayoutCompat_showDividers, 0);
        this.mDividerPadding = tintTypedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.LinearLayoutCompat_dividerPadding, 0);
        tintTypedArrayObtainStyledAttributes.recycle();
    }

    public Drawable getDividerDrawable() {
        return this.mDivider;
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable == this.mDivider) {
            return;
        }
        this.mDivider = drawable;
        if (drawable != null) {
            this.mDividerWidth = drawable.getIntrinsicWidth();
            this.mDividerHeight = drawable.getIntrinsicHeight();
        } else {
            this.mDividerWidth = 0;
            this.mDividerHeight = 0;
        }
        setWillNotDraw(drawable == null);
        requestLayout();
    }

    public int getDividerWidth() {
        return this.mDividerWidth;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mDivider == null) {
            return;
        }
        if (this.mOrientation == 1) {
            drawDividersVertical(canvas);
        } else {
            drawDividersHorizontal(canvas);
        }
    }

    void drawDividersVertical(Canvas canvas) {
        int bottom;
        int virtualChildCount = getVirtualChildCount();
        for (int i = 0; i < virtualChildCount; i++) {
            View virtualChildAt = getVirtualChildAt(i);
            if (virtualChildAt != null && virtualChildAt.getVisibility() != 8 && hasDividerBeforeChildAt(i)) {
                drawHorizontalDivider(canvas, (virtualChildAt.getTop() - ((ViewGroup.MarginLayoutParams) ((LayoutParams) virtualChildAt.getLayoutParams())).topMargin) - this.mDividerHeight);
            }
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 == null) {
                bottom = (getHeight() - getPaddingBottom()) - this.mDividerHeight;
            } else {
                bottom = virtualChildAt2.getBottom() + ((ViewGroup.MarginLayoutParams) ((LayoutParams) virtualChildAt2.getLayoutParams())).bottomMargin;
            }
            drawHorizontalDivider(canvas, bottom);
        }
    }

    void drawDividersHorizontal(Canvas canvas) {
        int right;
        int left;
        int i;
        int left2;
        int virtualChildCount = getVirtualChildCount();
        boolean zIsLayoutRtl = ViewUtils.isLayoutRtl(this);
        for (int i2 = 0; i2 < virtualChildCount; i2++) {
            View virtualChildAt = getVirtualChildAt(i2);
            if (virtualChildAt != null && virtualChildAt.getVisibility() != 8 && hasDividerBeforeChildAt(i2)) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (zIsLayoutRtl) {
                    left2 = virtualChildAt.getRight() + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
                } else {
                    left2 = (virtualChildAt.getLeft() - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin) - this.mDividerWidth;
                }
                drawVerticalDivider(canvas, left2);
            }
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 != null) {
                LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                if (zIsLayoutRtl) {
                    left = virtualChildAt2.getLeft() - ((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin;
                    i = this.mDividerWidth;
                    right = left - i;
                } else {
                    right = virtualChildAt2.getRight() + ((ViewGroup.MarginLayoutParams) layoutParams2).rightMargin;
                }
            } else if (zIsLayoutRtl) {
                right = getPaddingLeft();
            } else {
                left = getWidth() - getPaddingRight();
                i = this.mDividerWidth;
                right = left - i;
            }
            drawVerticalDivider(canvas, right);
        }
    }

    void drawHorizontalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, i, (getWidth() - getPaddingRight()) - this.mDividerPadding, this.mDividerHeight + i);
        this.mDivider.draw(canvas);
    }

    void drawVerticalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(i, getPaddingTop() + this.mDividerPadding, this.mDividerWidth + i, (getHeight() - getPaddingBottom()) - this.mDividerPadding);
        this.mDivider.draw(canvas);
    }

    public void setBaselineAligned(boolean z) {
        this.mBaselineAligned = z;
    }

    @Override // android.view.View
    public int getBaseline() {
        int i;
        if (this.mBaselineAlignedChildIndex < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i2 = this.mBaselineAlignedChildIndex;
        if (childCount <= i2) {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
        View childAt = getChildAt(i2);
        int baseline = childAt.getBaseline();
        if (baseline == -1) {
            if (this.mBaselineAlignedChildIndex == 0) {
                return -1;
            }
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
        }
        int bottom = this.mBaselineChildTop;
        if (this.mOrientation == 1 && (i = this.mGravity & 112) != 48) {
            if (i == 16) {
                bottom += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.mTotalLength) / 2;
            } else if (i == 80) {
                bottom = ((getBottom() - getTop()) - getPaddingBottom()) - this.mTotalLength;
            }
        }
        return bottom + ((ViewGroup.MarginLayoutParams) ((LayoutParams) childAt.getLayoutParams())).topMargin + baseline;
    }

    View getVirtualChildAt(int i) {
        return getChildAt(i);
    }

    int getVirtualChildCount() {
        return getChildCount();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.mOrientation == 1) {
            measureVertical(i, i2);
        } else {
            measureHorizontal(i, i2);
        }
    }

    protected boolean hasDividerBeforeChildAt(int i) {
        if (i == 0) {
            return (this.mShowDividers & 1) != 0;
        }
        if (i == getChildCount()) {
            return (this.mShowDividers & 4) != 0;
        }
        if ((this.mShowDividers & 2) == 0) {
            return false;
        }
        for (int i2 = i - 1; i2 >= 0; i2--) {
            if (getChildAt(i2).getVisibility() != 8) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:150:0x0327  */
    /* JADX WARN: Code duplicated, block: B:156:0x0335  */
    void measureVertical(int i, int i2) {
        int i3;
        int iCombineMeasuredStates;
        int iMax;
        int i4;
        int i5;
        int i6;
        boolean z;
        boolean z2;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        View view;
        int iMax2;
        boolean z3;
        int iMax3;
        this.mTotalLength = 0;
        int virtualChildCount = getVirtualChildCount();
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int i16 = this.mBaselineAlignedChildIndex;
        boolean z4 = this.mUseLargestChild;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int iMax4 = 0;
        int i20 = 0;
        int childrenSkipCount = 0;
        boolean z5 = false;
        boolean z6 = false;
        float f = 0.0f;
        boolean z7 = true;
        while (true) {
            int i21 = 8;
            int i22 = iMax4;
            if (childrenSkipCount < virtualChildCount) {
                View virtualChildAt = getVirtualChildAt(childrenSkipCount);
                if (virtualChildAt == null) {
                    this.mTotalLength += measureNullChild(childrenSkipCount);
                    i11 = virtualChildCount;
                    i12 = mode2;
                    iMax4 = i22;
                } else {
                    int i23 = i17;
                    if (virtualChildAt.getVisibility() == 8) {
                        childrenSkipCount += getChildrenSkipCount(virtualChildAt, childrenSkipCount);
                        i11 = virtualChildCount;
                        iMax4 = i22;
                        i17 = i23;
                        i12 = mode2;
                    } else {
                        if (hasDividerBeforeChildAt(childrenSkipCount)) {
                            this.mTotalLength += this.mDividerHeight;
                        }
                        LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                        float f2 = layoutParams.weight;
                        float f3 = f + f2;
                        if (mode2 == 1073741824 && ((ViewGroup.MarginLayoutParams) layoutParams).height == 0 && f2 > 0.0f) {
                            int i24 = this.mTotalLength;
                            this.mTotalLength = Math.max(i24, ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + i24 + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
                            iMax2 = i19;
                            view = virtualChildAt;
                            i14 = i20;
                            z5 = true;
                            i9 = i23;
                            i10 = i18;
                            i11 = virtualChildCount;
                            i12 = mode2;
                            i13 = i22;
                            i15 = childrenSkipCount;
                        } else {
                            int i25 = i18;
                            if (((ViewGroup.MarginLayoutParams) layoutParams).height != 0 || layoutParams.weight <= 0.0f) {
                                i8 = Integer.MIN_VALUE;
                            } else {
                                ((ViewGroup.MarginLayoutParams) layoutParams).height = -2;
                                i8 = 0;
                            }
                            i9 = i23;
                            int i26 = i8;
                            i10 = i25;
                            int i27 = i19;
                            i11 = virtualChildCount;
                            i12 = mode2;
                            i13 = i22;
                            i14 = i20;
                            i15 = childrenSkipCount;
                            measureChildBeforeLayout(virtualChildAt, childrenSkipCount, i, 0, i2, f3 == 0.0f ? this.mTotalLength : 0);
                            if (i26 != Integer.MIN_VALUE) {
                                ((ViewGroup.MarginLayoutParams) layoutParams).height = i26;
                            }
                            int measuredHeight = virtualChildAt.getMeasuredHeight();
                            int i28 = this.mTotalLength;
                            view = virtualChildAt;
                            this.mTotalLength = Math.max(i28, i28 + measuredHeight + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin + getNextLocationOffset(view));
                            iMax2 = z4 ? Math.max(measuredHeight, i27) : i27;
                        }
                        if (i16 >= 0 && i16 == i15 + 1) {
                            this.mBaselineChildTop = this.mTotalLength;
                        }
                        if (i15 < i16 && layoutParams.weight > 0.0f) {
                            throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
                        }
                        if (mode == 1073741824 || ((ViewGroup.MarginLayoutParams) layoutParams).width != -1) {
                            z3 = false;
                        } else {
                            z3 = true;
                            z6 = true;
                        }
                        int i29 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
                        int measuredWidth = view.getMeasuredWidth() + i29;
                        int iMax5 = Math.max(i10, measuredWidth);
                        int iCombineMeasuredStates2 = View.combineMeasuredStates(i9, view.getMeasuredState());
                        z7 = z7 && ((ViewGroup.MarginLayoutParams) layoutParams).width == -1;
                        if (layoutParams.weight > 0.0f) {
                            if (!z3) {
                                i29 = measuredWidth;
                            }
                            iMax4 = Math.max(i13, i29);
                            iMax3 = i14;
                        } else {
                            if (!z3) {
                                i29 = measuredWidth;
                            }
                            iMax3 = Math.max(i14, i29);
                            iMax4 = i13;
                        }
                        int childrenSkipCount2 = getChildrenSkipCount(view, i15) + i15;
                        i19 = iMax2;
                        f = f3;
                        i20 = iMax3;
                        i17 = iCombineMeasuredStates2;
                        childrenSkipCount = childrenSkipCount2;
                        i18 = iMax5;
                    }
                }
                childrenSkipCount++;
                mode2 = i12;
                virtualChildCount = i11;
            } else {
                int i30 = i17;
                int i31 = i19;
                int i32 = i20;
                int i33 = virtualChildCount;
                int i34 = mode2;
                int iMax6 = i18;
                if (this.mTotalLength > 0) {
                    i3 = i33;
                    if (hasDividerBeforeChildAt(i3)) {
                        this.mTotalLength += this.mDividerHeight;
                    }
                } else {
                    i3 = i33;
                }
                if (z4 && (i34 == Integer.MIN_VALUE || i34 == 0)) {
                    this.mTotalLength = 0;
                    int childrenSkipCount3 = 0;
                    while (childrenSkipCount3 < i3) {
                        View virtualChildAt2 = getVirtualChildAt(childrenSkipCount3);
                        if (virtualChildAt2 == null) {
                            this.mTotalLength += measureNullChild(childrenSkipCount3);
                        } else if (virtualChildAt2.getVisibility() == i21) {
                            childrenSkipCount3 += getChildrenSkipCount(virtualChildAt2, childrenSkipCount3);
                        } else {
                            LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                            int i35 = this.mTotalLength;
                            this.mTotalLength = Math.max(i35, i35 + i31 + ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin + getNextLocationOffset(virtualChildAt2));
                        }
                        childrenSkipCount3++;
                        i21 = 8;
                    }
                }
                int paddingTop = this.mTotalLength + getPaddingTop() + getPaddingBottom();
                this.mTotalLength = paddingTop;
                int iResolveSizeAndState = View.resolveSizeAndState(Math.max(paddingTop, getSuggestedMinimumHeight()), i2, 0);
                int i36 = (16777215 & iResolveSizeAndState) - this.mTotalLength;
                if (z5 || (i36 != 0 && f > 0.0f)) {
                    float f4 = this.mWeightSum;
                    if (f4 > 0.0f) {
                        f = f4;
                    }
                    this.mTotalLength = 0;
                    int i37 = i36;
                    int i38 = i32;
                    iCombineMeasuredStates = i30;
                    int i39 = 0;
                    while (i39 < i3) {
                        View virtualChildAt3 = getVirtualChildAt(i39);
                        if (virtualChildAt3.getVisibility() == 8) {
                            i4 = i37;
                        } else {
                            LayoutParams layoutParams3 = (LayoutParams) virtualChildAt3.getLayoutParams();
                            float f5 = layoutParams3.weight;
                            if (f5 > 0.0f) {
                                int i40 = (int) ((i37 * f5) / f);
                                float f6 = f - f5;
                                i4 = i37 - i40;
                                int childMeasureSpec = ViewGroup.getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + ((ViewGroup.MarginLayoutParams) layoutParams3).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams3).rightMargin, ((ViewGroup.MarginLayoutParams) layoutParams3).width);
                                if (((ViewGroup.MarginLayoutParams) layoutParams3).height == 0) {
                                    i7 = 1073741824;
                                    if (i34 == 1073741824) {
                                        if (i40 <= 0) {
                                            i40 = 0;
                                        }
                                        virtualChildAt3.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(i40, 1073741824));
                                    }
                                    iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, virtualChildAt3.getMeasuredState() & (-256));
                                    f = f6;
                                } else {
                                    i7 = 1073741824;
                                }
                                int measuredHeight2 = virtualChildAt3.getMeasuredHeight() + i40;
                                if (measuredHeight2 < 0) {
                                    measuredHeight2 = 0;
                                }
                                virtualChildAt3.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(measuredHeight2, i7));
                                iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, virtualChildAt3.getMeasuredState() & (-256));
                                f = f6;
                            } else {
                                i4 = i37;
                            }
                            int i41 = ((ViewGroup.MarginLayoutParams) layoutParams3).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams3).rightMargin;
                            int measuredWidth2 = virtualChildAt3.getMeasuredWidth() + i41;
                            iMax6 = Math.max(iMax6, measuredWidth2);
                            float f7 = f;
                            if (mode != 1073741824) {
                                i5 = iCombineMeasuredStates;
                                i6 = -1;
                                z = ((ViewGroup.MarginLayoutParams) layoutParams3).width == -1;
                                if (!z) {
                                    i41 = measuredWidth2;
                                }
                                int iMax7 = Math.max(i38, i41);
                                if (z7 || ((ViewGroup.MarginLayoutParams) layoutParams3).width != i6) {
                                    z2 = false;
                                } else {
                                    z2 = true;
                                }
                                int i42 = this.mTotalLength;
                                this.mTotalLength = Math.max(i42, virtualChildAt3.getMeasuredHeight() + i42 + ((ViewGroup.MarginLayoutParams) layoutParams3).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams3).bottomMargin + getNextLocationOffset(virtualChildAt3));
                                z7 = z2;
                                iCombineMeasuredStates = i5;
                                i38 = iMax7;
                                f = f7;
                            } else {
                                i5 = iCombineMeasuredStates;
                                i6 = -1;
                            }
                            if (!z) {
                                i41 = measuredWidth2;
                            }
                            int iMax8 = Math.max(i38, i41);
                            if (z7) {
                                z2 = false;
                            } else {
                                z2 = false;
                            }
                            int i43 = this.mTotalLength;
                            this.mTotalLength = Math.max(i43, virtualChildAt3.getMeasuredHeight() + i43 + ((ViewGroup.MarginLayoutParams) layoutParams3).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams3).bottomMargin + getNextLocationOffset(virtualChildAt3));
                            z7 = z2;
                            iCombineMeasuredStates = i5;
                            i38 = iMax8;
                            f = f7;
                        }
                        i39++;
                        i37 = i4;
                    }
                    this.mTotalLength += getPaddingTop() + getPaddingBottom();
                    iMax = i38;
                } else {
                    iMax = Math.max(i32, i22);
                    if (z4 && i34 != 1073741824) {
                        for (int i44 = 0; i44 < i3; i44++) {
                            View virtualChildAt4 = getVirtualChildAt(i44);
                            if (virtualChildAt4 != null && virtualChildAt4.getVisibility() != 8 && ((LayoutParams) virtualChildAt4.getLayoutParams()).weight > 0.0f) {
                                virtualChildAt4.measure(View.MeasureSpec.makeMeasureSpec(virtualChildAt4.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(i31, 1073741824));
                            }
                        }
                    }
                    iCombineMeasuredStates = i30;
                }
                if (z7 || mode == 1073741824) {
                    iMax = iMax6;
                }
                setMeasuredDimension(View.resolveSizeAndState(Math.max(iMax + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i, iCombineMeasuredStates), iResolveSizeAndState);
                if (z6) {
                    forceUniformWidth(i3, i2);
                    return;
                }
                return;
            }
        }
    }

    private void forceUniformWidth(int i, int i2) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (((ViewGroup.MarginLayoutParams) layoutParams).width == -1) {
                    int i4 = ((ViewGroup.MarginLayoutParams) layoutParams).height;
                    ((ViewGroup.MarginLayoutParams) layoutParams).height = virtualChildAt.getMeasuredHeight();
                    measureChildWithMargins(virtualChildAt, iMakeMeasureSpec, 0, i2, 0);
                    ((ViewGroup.MarginLayoutParams) layoutParams).height = i4;
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:197:0x0455  */
    /* JADX WARN: Code duplicated, block: B:60:0x0179  */
    /* JADX WARN: Code duplicated, block: B:67:0x019b  */
    /* JADX WARN: Code duplicated, block: B:74:0x01c7  */
    /* JADX WARN: Code duplicated, block: B:77:0x01cf A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:79:0x01d2  */
    /* JADX WARN: Code duplicated, block: B:81:0x01da  */
    /* JADX WARN: Code duplicated, block: B:84:0x01df  */
    void measureHorizontal(int i, int i2) {
        int[] iArr;
        int i3;
        int iMax;
        int iMax2;
        int i4;
        int i5;
        int baseline;
        int i6;
        int i7;
        byte b;
        int i8;
        int i9;
        boolean z;
        boolean z2;
        View view;
        int i10;
        boolean z3;
        int i11;
        int measuredHeight;
        int childrenSkipCount;
        int baseline2;
        int i12;
        this.mTotalLength = 0;
        int virtualChildCount = getVirtualChildCount();
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (this.mMaxAscent == null || this.mMaxDescent == null) {
            this.mMaxAscent = new int[4];
            this.mMaxDescent = new int[4];
        }
        int[] iArr2 = this.mMaxAscent;
        int[] iArr3 = this.mMaxDescent;
        iArr2[3] = -1;
        iArr2[2] = -1;
        iArr2[1] = -1;
        iArr2[0] = -1;
        iArr3[3] = -1;
        iArr3[2] = -1;
        iArr3[1] = -1;
        iArr3[0] = -1;
        boolean z4 = this.mBaselineAligned;
        boolean z5 = this.mUseLargestChild;
        int i13 = 1073741824;
        boolean z6 = mode == 1073741824;
        int childrenSkipCount2 = 0;
        int iMax3 = 0;
        int iMax4 = 0;
        int iMax5 = 0;
        int iMax6 = 0;
        boolean z7 = false;
        int iCombineMeasuredStates = 0;
        boolean z8 = false;
        boolean z9 = true;
        float f = 0.0f;
        while (true) {
            iArr = iArr3;
            if (childrenSkipCount2 >= virtualChildCount) {
                break;
            }
            View virtualChildAt = getVirtualChildAt(childrenSkipCount2);
            if (virtualChildAt == null) {
                this.mTotalLength += measureNullChild(childrenSkipCount2);
            } else {
                if (virtualChildAt.getVisibility() == 8) {
                    childrenSkipCount2 += getChildrenSkipCount(virtualChildAt, childrenSkipCount2);
                } else {
                    if (hasDividerBeforeChildAt(childrenSkipCount2)) {
                        this.mTotalLength += this.mDividerWidth;
                    }
                    LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                    float f2 = layoutParams.weight;
                    float f3 = f + f2;
                    if (mode != i13 || ((ViewGroup.MarginLayoutParams) layoutParams).width != 0 || f2 <= 0.0f) {
                        if (((ViewGroup.MarginLayoutParams) layoutParams).width != 0 || layoutParams.weight <= 0.0f) {
                            b = -2;
                            i8 = Integer.MIN_VALUE;
                        } else {
                            b = -2;
                            ((ViewGroup.MarginLayoutParams) layoutParams).width = -2;
                            i8 = 0;
                        }
                        i9 = childrenSkipCount2;
                        int i14 = i8;
                        z = z5;
                        z2 = z4;
                        measureChildBeforeLayout(virtualChildAt, i9, i, f3 == 0.0f ? this.mTotalLength : 0, i2, 0);
                        if (i14 != Integer.MIN_VALUE) {
                            ((ViewGroup.MarginLayoutParams) layoutParams).width = i14;
                        }
                        int measuredWidth = virtualChildAt.getMeasuredWidth();
                        if (z6) {
                            view = virtualChildAt;
                            this.mTotalLength += ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + measuredWidth + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin + getNextLocationOffset(view);
                        } else {
                            view = virtualChildAt;
                            int i15 = this.mTotalLength;
                            this.mTotalLength = Math.max(i15, i15 + measuredWidth + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin + getNextLocationOffset(view));
                        }
                        if (z) {
                            iMax3 = Math.max(measuredWidth, iMax3);
                        }
                    } else {
                        if (z6) {
                            this.mTotalLength += ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
                        } else {
                            int i16 = this.mTotalLength;
                            this.mTotalLength = Math.max(i16, ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + i16 + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin);
                        }
                        if (z4) {
                            int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                            virtualChildAt.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                            i9 = childrenSkipCount2;
                            z = z5;
                            z2 = z4;
                            view = virtualChildAt;
                        } else {
                            i9 = childrenSkipCount2;
                            z = z5;
                            z2 = z4;
                            view = virtualChildAt;
                            z7 = true;
                            i10 = 1073741824;
                        }
                        if (mode2 == i10 && ((ViewGroup.MarginLayoutParams) layoutParams).height == -1) {
                            z3 = true;
                            z8 = true;
                        } else {
                            z3 = false;
                        }
                        i11 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
                        measuredHeight = view.getMeasuredHeight() + i11;
                        iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, view.getMeasuredState());
                        if (z2 && (baseline2 = view.getBaseline()) != -1) {
                            i12 = layoutParams.gravity;
                            if (i12 < 0) {
                                i12 = this.mGravity;
                            }
                            int i17 = (((i12 & 112) >> 4) & (-2)) >> 1;
                            iArr2[i17] = Math.max(iArr2[i17], baseline2);
                            iArr[i17] = Math.max(iArr[i17], measuredHeight - baseline2);
                        }
                        iMax4 = Math.max(iMax4, measuredHeight);
                        if (z9 || ((ViewGroup.MarginLayoutParams) layoutParams).height != -1) {
                            z9 = false;
                        } else {
                            z9 = true;
                        }
                        if (layoutParams.weight > 0.0f) {
                            if (!z3) {
                                i11 = measuredHeight;
                            }
                            iMax6 = Math.max(iMax6, i11);
                        } else {
                            int i18 = iMax6;
                            if (!z3) {
                                i11 = measuredHeight;
                            }
                            iMax5 = Math.max(iMax5, i11);
                            iMax6 = i18;
                        }
                        int i19 = i9;
                        childrenSkipCount = getChildrenSkipCount(view, i19) + i19;
                        f = f3;
                    }
                    i10 = 1073741824;
                    if (mode2 == i10) {
                        z3 = false;
                    } else {
                        z3 = false;
                    }
                    i11 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
                    measuredHeight = view.getMeasuredHeight() + i11;
                    iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, view.getMeasuredState());
                    if (z2) {
                        i12 = layoutParams.gravity;
                        if (i12 < 0) {
                            i12 = this.mGravity;
                        }
                        int i110 = (((i12 & 112) >> 4) & (-2)) >> 1;
                        iArr2[i110] = Math.max(iArr2[i110], baseline2);
                        iArr[i110] = Math.max(iArr[i110], measuredHeight - baseline2);
                    }
                    iMax4 = Math.max(iMax4, measuredHeight);
                    if (z9) {
                        z9 = false;
                    } else {
                        z9 = false;
                    }
                    if (layoutParams.weight > 0.0f) {
                        if (!z3) {
                            i11 = measuredHeight;
                        }
                        iMax6 = Math.max(iMax6, i11);
                    } else {
                        int i111 = iMax6;
                        if (!z3) {
                            i11 = measuredHeight;
                        }
                        iMax5 = Math.max(iMax5, i11);
                        iMax6 = i111;
                    }
                    int i112 = i9;
                    childrenSkipCount = getChildrenSkipCount(view, i112) + i112;
                    f = f3;
                }
                int i20 = childrenSkipCount + 1;
                iArr3 = iArr;
                z5 = z;
                z4 = z2;
                i13 = i10;
                childrenSkipCount2 = i20;
            }
            z = z5;
            z2 = z4;
            int i21 = i13;
            childrenSkipCount = childrenSkipCount2;
            i10 = i21;
            int i22 = childrenSkipCount + 1;
            iArr3 = iArr;
            z5 = z;
            z4 = z2;
            i13 = i10;
            childrenSkipCount2 = i22;
        }
        boolean z10 = z5;
        boolean z11 = z4;
        int i23 = iMax4;
        int i24 = iMax5;
        int i25 = iMax6;
        int i26 = iCombineMeasuredStates;
        if (this.mTotalLength > 0 && hasDividerBeforeChildAt(virtualChildCount)) {
            this.mTotalLength += this.mDividerWidth;
        }
        int iMax7 = (iArr2[1] == -1 && iArr2[0] == -1 && iArr2[2] == -1 && iArr2[3] == -1) ? i23 : Math.max(i23, Math.max(iArr2[3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))) + Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))));
        if (z10 && (mode == Integer.MIN_VALUE || mode == 0)) {
            this.mTotalLength = 0;
            int childrenSkipCount3 = 0;
            while (childrenSkipCount3 < virtualChildCount) {
                View virtualChildAt2 = getVirtualChildAt(childrenSkipCount3);
                if (virtualChildAt2 == null) {
                    this.mTotalLength += measureNullChild(childrenSkipCount3);
                } else if (virtualChildAt2.getVisibility() == 8) {
                    childrenSkipCount3 += getChildrenSkipCount(virtualChildAt2, childrenSkipCount3);
                } else {
                    LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                    if (z6) {
                        this.mTotalLength += ((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin + iMax3 + ((ViewGroup.MarginLayoutParams) layoutParams2).rightMargin + getNextLocationOffset(virtualChildAt2);
                    } else {
                        int i27 = this.mTotalLength;
                        this.mTotalLength = Math.max(i27, i27 + iMax3 + ((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams2).rightMargin + getNextLocationOffset(virtualChildAt2));
                    }
                    childrenSkipCount3++;
                    iMax7 = iMax7;
                }
                childrenSkipCount3++;
                iMax7 = iMax7;
            }
        }
        int i28 = iMax7;
        int paddingLeft = this.mTotalLength + getPaddingLeft() + getPaddingRight();
        this.mTotalLength = paddingLeft;
        int iResolveSizeAndState = View.resolveSizeAndState(Math.max(paddingLeft, getSuggestedMinimumWidth()), i, 0);
        int i29 = (16777215 & iResolveSizeAndState) - this.mTotalLength;
        if (z7 || (i29 != 0 && f > 0.0f)) {
            float f4 = this.mWeightSum;
            if (f4 > 0.0f) {
                f = f4;
            }
            iArr2[3] = -1;
            iArr2[2] = -1;
            iArr2[1] = -1;
            iArr2[0] = -1;
            iArr[3] = -1;
            iArr[2] = -1;
            iArr[1] = -1;
            iArr[0] = -1;
            this.mTotalLength = 0;
            int i30 = i24;
            int iMax8 = -1;
            int iCombineMeasuredStates2 = i26;
            int i31 = 0;
            while (i31 < virtualChildCount) {
                View virtualChildAt3 = getVirtualChildAt(i31);
                if (virtualChildAt3 == null || virtualChildAt3.getVisibility() == 8) {
                    i4 = i29;
                    virtualChildCount = virtualChildCount;
                } else {
                    LayoutParams layoutParams3 = (LayoutParams) virtualChildAt3.getLayoutParams();
                    float f5 = layoutParams3.weight;
                    if (f5 > 0.0f) {
                        int i32 = (int) ((i29 * f5) / f);
                        float f6 = f - f5;
                        int i33 = i29 - i32;
                        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, getPaddingTop() + getPaddingBottom() + ((ViewGroup.MarginLayoutParams) layoutParams3).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams3).bottomMargin, ((ViewGroup.MarginLayoutParams) layoutParams3).height);
                        if (((ViewGroup.MarginLayoutParams) layoutParams3).width == 0) {
                            i7 = 1073741824;
                            if (mode == 1073741824) {
                                if (i32 <= 0) {
                                    i32 = 0;
                                }
                                virtualChildAt3.measure(View.MeasureSpec.makeMeasureSpec(i32, 1073741824), childMeasureSpec);
                            }
                            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates2, virtualChildAt3.getMeasuredState() & (-16777216));
                            f = f6;
                            i4 = i33;
                        } else {
                            i7 = 1073741824;
                        }
                        int measuredWidth2 = virtualChildAt3.getMeasuredWidth() + i32;
                        if (measuredWidth2 < 0) {
                            measuredWidth2 = 0;
                        }
                        virtualChildAt3.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth2, i7), childMeasureSpec);
                        iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates2, virtualChildAt3.getMeasuredState() & (-16777216));
                        f = f6;
                        i4 = i33;
                    } else {
                        i4 = i29;
                    }
                    if (z6) {
                        this.mTotalLength += virtualChildAt3.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) layoutParams3).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams3).rightMargin + getNextLocationOffset(virtualChildAt3);
                    } else {
                        int i34 = this.mTotalLength;
                        this.mTotalLength = Math.max(i34, virtualChildAt3.getMeasuredWidth() + i34 + ((ViewGroup.MarginLayoutParams) layoutParams3).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams3).rightMargin + getNextLocationOffset(virtualChildAt3));
                    }
                    boolean z12 = mode2 != 1073741824 && ((ViewGroup.MarginLayoutParams) layoutParams3).height == -1;
                    int i35 = ((ViewGroup.MarginLayoutParams) layoutParams3).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams3).bottomMargin;
                    int measuredHeight2 = virtualChildAt3.getMeasuredHeight() + i35;
                    iMax8 = Math.max(iMax8, measuredHeight2);
                    if (!z12) {
                        i35 = measuredHeight2;
                    }
                    int iMax9 = Math.max(i30, i35);
                    if (z9) {
                        i5 = -1;
                        boolean z13 = ((ViewGroup.MarginLayoutParams) layoutParams3).height == -1;
                        if (z11 && (baseline = virtualChildAt3.getBaseline()) != i5) {
                            i6 = layoutParams3.gravity;
                            if (i6 < 0) {
                                i6 = this.mGravity;
                            }
                            int i36 = (((i6 & 112) >> 4) & (-2)) >> 1;
                            iArr2[i36] = Math.max(iArr2[i36], baseline);
                            iArr[i36] = Math.max(iArr[i36], measuredHeight2 - baseline);
                        }
                        z9 = z13;
                        i30 = iMax9;
                        f = f;
                    } else {
                        i5 = -1;
                    }
                    if (z11) {
                        i6 = layoutParams3.gravity;
                        if (i6 < 0) {
                            i6 = this.mGravity;
                        }
                        int i37 = (((i6 & 112) >> 4) & (-2)) >> 1;
                        iArr2[i37] = Math.max(iArr2[i37], baseline);
                        iArr[i37] = Math.max(iArr[i37], measuredHeight2 - baseline);
                    }
                    z9 = z13;
                    i30 = iMax9;
                    f = f;
                }
                i31++;
                i29 = i4;
                virtualChildCount = virtualChildCount;
            }
            i3 = virtualChildCount;
            this.mTotalLength += getPaddingLeft() + getPaddingRight();
            iMax = (iArr2[1] == -1 && iArr2[0] == -1 && iArr2[2] == -1 && iArr2[3] == -1) ? iMax8 : Math.max(iMax8, Math.max(iArr2[3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))) + Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))));
            iMax2 = i30;
            i26 = iCombineMeasuredStates2;
        } else {
            iMax2 = Math.max(i24, i25);
            if (z10 && mode != 1073741824) {
                for (int i38 = 0; i38 < virtualChildCount; i38++) {
                    View virtualChildAt4 = getVirtualChildAt(i38);
                    if (virtualChildAt4 != null && virtualChildAt4.getVisibility() != 8 && ((LayoutParams) virtualChildAt4.getLayoutParams()).weight > 0.0f) {
                        virtualChildAt4.measure(View.MeasureSpec.makeMeasureSpec(iMax3, 1073741824), View.MeasureSpec.makeMeasureSpec(virtualChildAt4.getMeasuredHeight(), 1073741824));
                    }
                }
            }
            i3 = virtualChildCount;
            iMax = i28;
        }
        if (z9 || mode2 == 1073741824) {
            iMax2 = iMax;
        }
        setMeasuredDimension(iResolveSizeAndState | (i26 & (-16777216)), View.resolveSizeAndState(Math.max(iMax2 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i2, i26 << 16));
        if (z8) {
            forceUniformHeight(i3, i);
        }
    }

    private void forceUniformHeight(int i, int i2) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (((ViewGroup.MarginLayoutParams) layoutParams).height == -1) {
                    int i4 = ((ViewGroup.MarginLayoutParams) layoutParams).width;
                    ((ViewGroup.MarginLayoutParams) layoutParams).width = virtualChildAt.getMeasuredWidth();
                    measureChildWithMargins(virtualChildAt, i2, 0, iMakeMeasureSpec, 0);
                    ((ViewGroup.MarginLayoutParams) layoutParams).width = i4;
                }
            }
        }
    }

    void measureChildBeforeLayout(View view, int i, int i2, int i3, int i4, int i5) {
        measureChildWithMargins(view, i2, i3, i4, i5);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.mOrientation == 1) {
            layoutVertical(i, i2, i3, i4);
        } else {
            layoutHorizontal(i, i2, i3, i4);
        }
    }

    /* JADX WARN: Code duplicated, block: B:29:0x009f  */
    void layoutVertical(int i, int i2, int i3, int i4) {
        int paddingTop;
        int i5;
        int i6;
        int i7;
        int i8;
        int paddingLeft = getPaddingLeft();
        int i9 = i3 - i;
        int paddingRight = i9 - getPaddingRight();
        int paddingRight2 = (i9 - paddingLeft) - getPaddingRight();
        int virtualChildCount = getVirtualChildCount();
        int i10 = this.mGravity;
        int i11 = i10 & 112;
        int i12 = i10 & 8388615;
        if (i11 == 16) {
            paddingTop = getPaddingTop() + (((i4 - i2) - this.mTotalLength) / 2);
        } else if (i11 == 80) {
            paddingTop = ((getPaddingTop() + i4) - i2) - this.mTotalLength;
        } else {
            paddingTop = getPaddingTop();
        }
        int childrenSkipCount = 0;
        while (childrenSkipCount < virtualChildCount) {
            View virtualChildAt = getVirtualChildAt(childrenSkipCount);
            if (virtualChildAt == null) {
                paddingTop += measureNullChild(childrenSkipCount);
            } else {
                if (virtualChildAt.getVisibility() != 8) {
                    int measuredWidth = virtualChildAt.getMeasuredWidth();
                    int measuredHeight = virtualChildAt.getMeasuredHeight();
                    LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                    int i13 = layoutParams.gravity;
                    if (i13 < 0) {
                        i13 = i12;
                    }
                    int absoluteGravity = GravityCompat.getAbsoluteGravity(i13, ViewCompat.getLayoutDirection(this)) & 7;
                    if (absoluteGravity == 1) {
                        i5 = ((paddingRight2 - measuredWidth) / 2) + paddingLeft + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
                        i6 = ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
                    } else {
                        if (absoluteGravity == 5) {
                            i5 = paddingRight - measuredWidth;
                            i6 = ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
                        } else {
                            i7 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + paddingLeft;
                        }
                        int i14 = i7;
                        if (hasDividerBeforeChildAt(childrenSkipCount)) {
                            paddingTop += this.mDividerHeight;
                        }
                        int i15 = paddingTop + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
                        setChildFrame(virtualChildAt, i14, i15 + getLocationOffset(virtualChildAt), measuredWidth, measuredHeight);
                        int nextLocationOffset = i15 + measuredHeight + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin + getNextLocationOffset(virtualChildAt);
                        childrenSkipCount += getChildrenSkipCount(virtualChildAt, childrenSkipCount);
                        paddingTop = nextLocationOffset;
                        i8 = 1;
                    }
                    i7 = i5 - i6;
                    int i16 = i7;
                    if (hasDividerBeforeChildAt(childrenSkipCount)) {
                        paddingTop += this.mDividerHeight;
                    }
                    int i17 = paddingTop + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
                    setChildFrame(virtualChildAt, i16, i17 + getLocationOffset(virtualChildAt), measuredWidth, measuredHeight);
                    int nextLocationOffset2 = i17 + measuredHeight + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin + getNextLocationOffset(virtualChildAt);
                    childrenSkipCount += getChildrenSkipCount(virtualChildAt, childrenSkipCount);
                    paddingTop = nextLocationOffset2;
                    i8 = 1;
                }
                childrenSkipCount += i8;
            }
            i8 = 1;
            childrenSkipCount += i8;
        }
    }

    /* JADX WARN: Code duplicated, block: B:29:0x00af  */
    /* JADX WARN: Code duplicated, block: B:32:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:34:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:36:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:37:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:39:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:40:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:42:0x00df  */
    /* JADX WARN: Code duplicated, block: B:44:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:47:0x00ff  */
    void layoutHorizontal(int i, int i2, int i3, int i4) {
        int paddingLeft;
        int i5;
        int i6;
        boolean z;
        int baseline;
        int i7;
        int i8;
        int measuredHeight;
        boolean zIsLayoutRtl = ViewUtils.isLayoutRtl(this);
        int paddingTop = getPaddingTop();
        int i9 = i4 - i2;
        int paddingBottom = i9 - getPaddingBottom();
        int paddingBottom2 = (i9 - paddingTop) - getPaddingBottom();
        int virtualChildCount = getVirtualChildCount();
        int i10 = this.mGravity;
        int i11 = i10 & 112;
        boolean z2 = this.mBaselineAligned;
        int[] iArr = this.mMaxAscent;
        int[] iArr2 = this.mMaxDescent;
        int absoluteGravity = GravityCompat.getAbsoluteGravity(8388615 & i10, ViewCompat.getLayoutDirection(this));
        boolean z3 = true;
        if (absoluteGravity == 1) {
            paddingLeft = getPaddingLeft() + (((i3 - i) - this.mTotalLength) / 2);
        } else if (absoluteGravity == 5) {
            paddingLeft = ((getPaddingLeft() + i3) - i) - this.mTotalLength;
        } else {
            paddingLeft = getPaddingLeft();
        }
        if (zIsLayoutRtl) {
            i5 = virtualChildCount - 1;
            i6 = -1;
        } else {
            i5 = 0;
            i6 = 1;
        }
        int childrenSkipCount = 0;
        while (childrenSkipCount < virtualChildCount) {
            int i12 = i5 + (i6 * childrenSkipCount);
            View virtualChildAt = getVirtualChildAt(i12);
            if (virtualChildAt == null) {
                paddingLeft += measureNullChild(i12);
                z = z3;
            } else {
                if (virtualChildAt.getVisibility() != 8) {
                    int measuredWidth = virtualChildAt.getMeasuredWidth();
                    int measuredHeight2 = virtualChildAt.getMeasuredHeight();
                    LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                    int i13 = childrenSkipCount;
                    if (z2) {
                        virtualChildCount = virtualChildCount;
                        baseline = ((ViewGroup.MarginLayoutParams) layoutParams).height != -1 ? virtualChildAt.getBaseline() : -1;
                        i7 = layoutParams.gravity;
                        if (i7 < 0) {
                            i7 = i11;
                        }
                        i8 = i7 & 112;
                        i11 = i11;
                        if (i8 != 16) {
                            if (i8 != 48) {
                                measuredHeight = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + paddingTop;
                                if (baseline != -1) {
                                    z = true;
                                    measuredHeight += iArr[1] - baseline;
                                }
                            } else if (i8 != 80) {
                                measuredHeight = paddingTop;
                            } else {
                                measuredHeight = (paddingBottom - measuredHeight2) - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
                                if (baseline != -1) {
                                    measuredHeight -= iArr2[2] - (virtualChildAt.getMeasuredHeight() - baseline);
                                }
                            }
                            z = true;
                        } else {
                            z = true;
                            measuredHeight = ((((paddingBottom2 - measuredHeight2) / 2) + paddingTop) + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin) - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
                        }
                        if (hasDividerBeforeChildAt(i12)) {
                            paddingLeft += this.mDividerWidth;
                        }
                        int i14 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + paddingLeft;
                        paddingTop = paddingTop;
                        setChildFrame(virtualChildAt, i14 + getLocationOffset(virtualChildAt), measuredHeight, measuredWidth, measuredHeight2);
                        int nextLocationOffset = i14 + measuredWidth + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin + getNextLocationOffset(virtualChildAt);
                        childrenSkipCount = i13 + getChildrenSkipCount(virtualChildAt, i12);
                        paddingLeft = nextLocationOffset;
                    } else {
                        virtualChildCount = virtualChildCount;
                    }
                    i7 = layoutParams.gravity;
                    if (i7 < 0) {
                        i7 = i11;
                    }
                    i8 = i7 & 112;
                    i11 = i11;
                    if (i8 != 16) {
                        if (i8 != 48) {
                            measuredHeight = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + paddingTop;
                            if (baseline != -1) {
                                z = true;
                                measuredHeight += iArr[1] - baseline;
                            }
                        } else if (i8 != 80) {
                            measuredHeight = paddingTop;
                        } else {
                            measuredHeight = (paddingBottom - measuredHeight2) - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
                            if (baseline != -1) {
                                measuredHeight -= iArr2[2] - (virtualChildAt.getMeasuredHeight() - baseline);
                            }
                        }
                        z = true;
                    } else {
                        z = true;
                        measuredHeight = ((((paddingBottom2 - measuredHeight2) / 2) + paddingTop) + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin) - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
                    }
                    if (hasDividerBeforeChildAt(i12)) {
                        paddingLeft += this.mDividerWidth;
                    }
                    int i15 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + paddingLeft;
                    paddingTop = paddingTop;
                    setChildFrame(virtualChildAt, i15 + getLocationOffset(virtualChildAt), measuredHeight, measuredWidth, measuredHeight2);
                    int nextLocationOffset2 = i15 + measuredWidth + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin + getNextLocationOffset(virtualChildAt);
                    childrenSkipCount = i13 + getChildrenSkipCount(virtualChildAt, i12);
                    paddingLeft = nextLocationOffset2;
                } else {
                    z = true;
                }
                childrenSkipCount++;
                virtualChildCount = virtualChildCount;
                i11 = i11;
                z3 = z;
                paddingTop = paddingTop;
            }
            childrenSkipCount++;
            virtualChildCount = virtualChildCount;
            i11 = i11;
            z3 = z;
            paddingTop = paddingTop;
        }
    }

    private void setChildFrame(View view, int i, int i2, int i3, int i4) {
        view.layout(i, i2, i3 + i, i4 + i2);
    }

    public void setOrientation(int i) {
        if (this.mOrientation != i) {
            this.mOrientation = i;
            requestLayout();
        }
    }

    public void setGravity(int i) {
        if (this.mGravity != i) {
            if ((8388615 & i) == 0) {
                i |= 8388611;
            }
            if ((i & 112) == 0) {
                i |= 48;
            }
            this.mGravity = i;
            requestLayout();
        }
    }

    public int getGravity() {
        return this.mGravity;
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        int i = this.mOrientation;
        if (i == 0) {
            return new LayoutParams(-2, -2);
        }
        if (i == 1) {
            return new LayoutParams(-1, -2);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int gravity;
        public float weight;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.gravity = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.LinearLayoutCompat_Layout);
            this.weight = typedArrayObtainStyledAttributes.getFloat(R$styleable.LinearLayoutCompat_Layout_android_layout_weight, 0.0f);
            this.gravity = typedArrayObtainStyledAttributes.getInt(R$styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            typedArrayObtainStyledAttributes.recycle();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.gravity = -1;
            this.weight = 0.0f;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = -1;
        }
    }
}
