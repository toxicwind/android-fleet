package com.android.settingslib.drawer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public abstract class SwitchController {
    private String mAuthority;

    protected abstract String getErrorMessage(boolean z);

    protected abstract MetaData getMetaData();

    public abstract String getSwitchKey();

    protected abstract boolean isChecked();

    protected abstract boolean onCheckedChanged(boolean z);

    public void notifySummaryChanged(Context context) {
        if (this instanceof DynamicSummary) {
            notifyChanged(context, "getDynamicSummary");
        }
    }

    void setAuthority(String str) {
        this.mAuthority = str;
    }

    Bundle getBundle() {
        MetaData metaData = getMetaData();
        if (metaData == null) {
            throw new NullPointerException("Should not return null in getMetaData()");
        }
        Bundle bundleBuild = metaData.build();
        String string = new Uri.Builder().scheme("content").authority(this.mAuthority).build().toString();
        bundleBuild.putString("com.android.settings.keyhint", getSwitchKey());
        bundleBuild.putString("com.android.settings.switch_uri", string);
        if (this instanceof ProviderIcon) {
            bundleBuild.putString("com.android.settings.icon_uri", string);
        }
        if (this instanceof DynamicTitle) {
            bundleBuild.putString("com.android.settings.title_uri", string);
        }
        if (this instanceof DynamicSummary) {
            bundleBuild.putString("com.android.settings.summary_uri", string);
        }
        return bundleBuild;
    }

    private void notifyChanged(Context context, String str) {
        context.getContentResolver().notifyChange(TileUtils.buildUri(this.mAuthority, str, getSwitchKey()), null);
    }

    protected static class MetaData {
        private String mCategory;
        private int mIcon;
        private int mIconBackgroundArgb;
        private int mIconBackgroundHint;
        private Boolean mIconTintable;
        private int mOrder;
        private String mSummary;
        private int mSummaryId;
        private String mTitle;
        private int mTitleId;

        /* JADX INFO: Access modifiers changed from: private */
        public Bundle build() {
            Bundle bundle = new Bundle();
            bundle.putString("com.android.settings.category", this.mCategory);
            int i = this.mOrder;
            if (i != 0) {
                bundle.putInt("com.android.settings.order", i);
            }
            int i2 = this.mIcon;
            if (i2 != 0) {
                bundle.putInt("com.android.settings.icon", i2);
            }
            int i3 = this.mIconBackgroundHint;
            if (i3 != 0) {
                bundle.putInt("com.android.settings.bg.hint", i3);
            }
            int i4 = this.mIconBackgroundArgb;
            if (i4 != 0) {
                bundle.putInt("com.android.settings.bg.argb", i4);
            }
            Boolean bool = this.mIconTintable;
            if (bool != null) {
                bundle.putBoolean("com.android.settings.icon_tintable", bool.booleanValue());
            }
            int i5 = this.mTitleId;
            if (i5 != 0) {
                bundle.putInt("com.android.settings.title", i5);
            } else {
                String str = this.mTitle;
                if (str != null) {
                    bundle.putString("com.android.settings.title", str);
                }
            }
            int i6 = this.mSummaryId;
            if (i6 != 0) {
                bundle.putInt("com.android.settings.summary", i6);
            } else {
                String str2 = this.mSummary;
                if (str2 != null) {
                    bundle.putString("com.android.settings.summary", str2);
                }
            }
            return bundle;
        }
    }
}
