package com.jaredrummler.android.colorpicker;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import androidx.core.graphics.ColorUtils;

/* JADX INFO: loaded from: classes6.dex */
class ColorPaletteAdapter extends BaseAdapter {
    final int colorShape;
    final int[] colors;
    final OnColorSelectedListener listener;
    int selectedPosition;

    interface OnColorSelectedListener {
        void onColorSelected(int i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    ColorPaletteAdapter(OnColorSelectedListener onColorSelectedListener, int[] iArr, int i, int i2) {
        this.listener = onColorSelectedListener;
        this.colors = iArr;
        this.selectedPosition = i;
        this.colorShape = i2;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.colors.length;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return Integer.valueOf(this.colors[i]);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        View view2;
        if (view == null) {
            viewHolder = new ViewHolder(viewGroup.getContext());
            view2 = viewHolder.view;
        } else {
            viewHolder = (ViewHolder) view.getTag();
            view2 = view;
        }
        viewHolder.setup(i);
        return view2;
    }

    void selectNone() {
        this.selectedPosition = -1;
        notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    final class ViewHolder {
        final ColorPanelView colorPanelView;
        final ImageView imageView;
        final int originalBorderColor;
        final View view;

        ViewHolder(Context context) {
            int i;
            if (ColorPaletteAdapter.this.colorShape == 0) {
                i = R.layout.cpv_color_item_square;
            } else {
                i = R.layout.cpv_color_item_circle;
            }
            View viewInflate = View.inflate(context, i, null);
            this.view = viewInflate;
            ColorPanelView colorPanelView = (ColorPanelView) viewInflate.findViewById(R.id.cpv_color_panel_view);
            this.colorPanelView = colorPanelView;
            this.imageView = (ImageView) viewInflate.findViewById(R.id.cpv_color_image_view);
            this.originalBorderColor = colorPanelView.getBorderColor();
            viewInflate.setTag(this);
        }

        void setup(int i) {
            int i2 = ColorPaletteAdapter.this.colors[i];
            int iAlpha = Color.alpha(i2);
            this.colorPanelView.setColor(i2);
            this.imageView.setImageResource(ColorPaletteAdapter.this.selectedPosition == i ? R.drawable.cpv_preset_checked : 0);
            if (iAlpha != 255) {
                ColorPanelView colorPanelView = this.colorPanelView;
                if (iAlpha <= 165) {
                    colorPanelView.setBorderColor(i2 | (-16777216));
                    this.imageView.setColorFilter(-16777216, PorterDuff.Mode.SRC_IN);
                } else {
                    colorPanelView.setBorderColor(this.originalBorderColor);
                    this.imageView.setColorFilter(-1, PorterDuff.Mode.SRC_IN);
                }
            } else {
                setColorFilter(i);
            }
            setOnClickListener(i);
        }

        private void setOnClickListener(final int i) {
            this.colorPanelView.setOnClickListener(new View.OnClickListener() { // from class: com.jaredrummler.android.colorpicker.ColorPaletteAdapter$ViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m8377x7eae5cfd(i, view);
                }
            });
            this.colorPanelView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.jaredrummler.android.colorpicker.ColorPaletteAdapter$ViewHolder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    return this.f$0.m8378x6fffec7e(view);
                }
            });
        }

        /* JADX INFO: renamed from: lambda$setOnClickListener$0$com-jaredrummler-android-colorpicker-ColorPaletteAdapter$ViewHolder, reason: not valid java name */
        /* synthetic */ void m8377x7eae5cfd(int i, View view) {
            if (ColorPaletteAdapter.this.selectedPosition != i) {
                ColorPaletteAdapter.this.selectedPosition = i;
                ColorPaletteAdapter.this.notifyDataSetChanged();
            }
            ColorPaletteAdapter.this.listener.onColorSelected(ColorPaletteAdapter.this.colors[i]);
        }

        /* JADX INFO: renamed from: lambda$setOnClickListener$1$com-jaredrummler-android-colorpicker-ColorPaletteAdapter$ViewHolder, reason: not valid java name */
        /* synthetic */ boolean m8378x6fffec7e(View view) {
            this.colorPanelView.showHint();
            return true;
        }

        private void setColorFilter(int i) {
            if (i == ColorPaletteAdapter.this.selectedPosition && ColorUtils.calculateLuminance(ColorPaletteAdapter.this.colors[i]) >= 0.65d) {
                this.imageView.setColorFilter(-16777216, PorterDuff.Mode.SRC_IN);
            } else {
                this.imageView.setColorFilter((ColorFilter) null);
            }
        }
    }
}
