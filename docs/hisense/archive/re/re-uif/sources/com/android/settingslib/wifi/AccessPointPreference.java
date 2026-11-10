package com.android.settingslib.wifi;

import android.content.Context;
import android.graphics.drawable.StateListDrawable;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.preference.Preference;
import com.android.settingslib.R$dimen;
import com.android.settingslib.R$layout;
import com.android.settingslib.R$string;

/* JADX INFO: loaded from: classes.dex */
public class AccessPointPreference extends Preference {
    private static final int[] WIFI_CONNECTION_STRENGTH = {R$string.accessibility_no_wifi, R$string.accessibility_wifi_one_bar, R$string.accessibility_wifi_two_bars, R$string.accessibility_wifi_three_bars, R$string.accessibility_wifi_signal_full};
    private AccessPoint mAccessPoint;
    private final Runnable mNotifyChanged;
    private TextView mTitleView;

    static class IconInjector {
    }

    public static class UserBadgeCache {
    }

    public AccessPointPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mNotifyChanged = new Runnable() { // from class: com.android.settingslib.wifi.AccessPointPreference.1
            @Override // java.lang.Runnable
            public void run() {
                AccessPointPreference.this.notifyChanged();
            }
        };
    }

    AccessPointPreference(AccessPoint accessPoint, Context context, UserBadgeCache userBadgeCache, int i, boolean z, StateListDrawable stateListDrawable, int i2, IconInjector iconInjector) {
        super(context);
        this.mNotifyChanged = new Runnable() { // from class: com.android.settingslib.wifi.AccessPointPreference.1
            @Override // java.lang.Runnable
            public void run() {
                AccessPointPreference.this.notifyChanged();
            }
        };
        setLayoutResource(R$layout.preference_access_point);
        setWidgetLayoutResource(getWidgetLayoutResourceId());
        this.mAccessPoint = accessPoint;
        accessPoint.setTag(this);
        context.getResources().getDimensionPixelSize(R$dimen.wifi_preference_badge_padding);
    }

    protected int getWidgetLayoutResourceId() {
        return R$layout.access_point_friction_widget;
    }

    @Override // androidx.preference.Preference
    protected void notifyChanged() {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            postNotifyChanged();
        } else {
            super.notifyChanged();
        }
    }

    static void setTitle(AccessPointPreference accessPointPreference, AccessPoint accessPoint) {
        accessPointPreference.setTitle(accessPoint.getTitle());
    }

    static CharSequence buildContentDescription(Context context, Preference preference, AccessPoint accessPoint) {
        String string;
        CharSequence title = preference.getTitle();
        CharSequence summary = preference.getSummary();
        if (!TextUtils.isEmpty(summary)) {
            title = TextUtils.concat(title, ",", summary);
        }
        int level = accessPoint.getLevel();
        if (level >= 0) {
            int[] iArr = WIFI_CONNECTION_STRENGTH;
            if (level < iArr.length) {
                title = TextUtils.concat(title, ",", context.getString(iArr[level]));
            }
        }
        CharSequence[] charSequenceArr = new CharSequence[3];
        charSequenceArr[0] = title;
        charSequenceArr[1] = ",";
        if (accessPoint.getSecurity() == 0) {
            string = context.getString(R$string.accessibility_wifi_security_type_none);
        } else {
            string = context.getString(R$string.accessibility_wifi_security_type_secured);
        }
        charSequenceArr[2] = string;
        return TextUtils.concat(charSequenceArr);
    }

    private void postNotifyChanged() {
        TextView textView = this.mTitleView;
        if (textView != null) {
            textView.post(this.mNotifyChanged);
        }
    }
}
