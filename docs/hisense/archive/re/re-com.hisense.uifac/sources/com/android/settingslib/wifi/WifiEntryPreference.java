package com.android.settingslib.wifi;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;
import android.view.View;
import androidx.preference.Preference;
import com.android.settingslib.R$attr;
import com.android.settingslib.R$id;
import com.android.settingslib.R$layout;
import com.android.settingslib.R$string;
import com.android.settingslib.Utils;
import com.android.wifitrackerlib.WifiEntry;

/* JADX INFO: loaded from: classes.dex */
public class WifiEntryPreference extends Preference implements WifiEntry.WifiEntryCallback, View.OnClickListener {
    private static final int[] FRICTION_ATTRS = {R$attr.wifi_friction};
    private static final int[] WIFI_CONNECTION_STRENGTH = {R$string.accessibility_no_wifi, R$string.accessibility_wifi_one_bar, R$string.accessibility_wifi_two_bars, R$string.accessibility_wifi_three_bars, R$string.accessibility_wifi_signal_full};
    private final IconInjector mIconInjector;
    private int mLevel;
    private OnButtonClickListener mOnButtonClickListener;
    private WifiEntry mWifiEntry;

    static class IconInjector {
        public abstract Drawable getIcon(int i);
    }

    public interface OnButtonClickListener {
        void onButtonClick(WifiEntryPreference wifiEntryPreference);
    }

    WifiEntryPreference(Context context, WifiEntry wifiEntry, IconInjector iconInjector) {
        super(context);
        this.mLevel = -1;
        setLayoutResource(R$layout.preference_access_point);
        setWidgetLayoutResource(R$layout.access_point_friction_widget);
        getFrictionStateListDrawable();
        this.mWifiEntry = wifiEntry;
        wifiEntry.setListener(this);
        this.mIconInjector = iconInjector;
        refresh();
    }

    public void refresh() {
        setTitle(this.mWifiEntry.getTitle());
        int level = this.mWifiEntry.getLevel();
        if (level != this.mLevel) {
            this.mLevel = level;
            updateIcon(level);
            notifyChanged();
        }
        setSummary(this.mWifiEntry.getSummary(false));
        buildContentDescription();
    }

    @Override // com.android.wifitrackerlib.WifiEntry.WifiEntryCallback
    public void onUpdated() {
        refresh();
    }

    private void updateIcon(int i) {
        if (i == -1) {
            setIcon((Drawable) null);
            return;
        }
        Drawable icon = this.mIconInjector.getIcon(i);
        if (icon != null) {
            icon.setTintList(Utils.getColorAttr(getContext(), R.attr.colorControlNormal));
            setIcon(icon);
        } else {
            setIcon((Drawable) null);
        }
    }

    private StateListDrawable getFrictionStateListDrawable() {
        TypedArray typedArrayObtainStyledAttributes;
        try {
            typedArrayObtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(FRICTION_ATTRS);
        } catch (Resources.NotFoundException unused) {
            typedArrayObtainStyledAttributes = null;
        }
        if (typedArrayObtainStyledAttributes != null) {
            return (StateListDrawable) typedArrayObtainStyledAttributes.getDrawable(0);
        }
        return null;
    }

    CharSequence buildContentDescription() {
        String string;
        Context context = getContext();
        CharSequence title = getTitle();
        CharSequence summary = getSummary();
        if (!TextUtils.isEmpty(summary)) {
            title = TextUtils.concat(title, ",", summary);
        }
        int level = this.mWifiEntry.getLevel();
        if (level >= 0) {
            int[] iArr = WIFI_CONNECTION_STRENGTH;
            if (level < iArr.length) {
                title = TextUtils.concat(title, ",", context.getString(iArr[level]));
            }
        }
        CharSequence[] charSequenceArr = new CharSequence[3];
        charSequenceArr[0] = title;
        charSequenceArr[1] = ",";
        if (this.mWifiEntry.getSecurity() == 0) {
            string = context.getString(R$string.accessibility_wifi_security_type_none);
        } else {
            string = context.getString(R$string.accessibility_wifi_security_type_secured);
        }
        charSequenceArr[2] = string;
        return TextUtils.concat(charSequenceArr);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        OnButtonClickListener onButtonClickListener;
        if (view.getId() != R$id.icon_button || (onButtonClickListener = this.mOnButtonClickListener) == null) {
            return;
        }
        onButtonClickListener.onButtonClick(this);
    }
}
