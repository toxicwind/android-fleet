package com.android.settingslib.drawer;

import android.content.pm.ProviderInfo;
import android.os.Bundle;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public class ProviderTile extends Tile {
    public ProviderTile(ProviderInfo providerInfo, String str, Bundle bundle) {
        super(providerInfo, str);
        setMetaData(bundle);
        String str2 = providerInfo.authority;
        bundle.getString("com.android.settings.keyhint");
    }

    ProviderTile(Parcel parcel) {
        super(parcel);
        String str = ((ProviderInfo) this.mComponentInfo).authority;
        getMetaData().getString("com.android.settings.keyhint");
    }
}
