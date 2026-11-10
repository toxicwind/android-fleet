package com.android.settingslib.drawer;

import android.content.pm.ActivityInfo;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public class ActivityTile extends Tile {
    public ActivityTile(ActivityInfo activityInfo, String str) {
        super(activityInfo, str);
        setMetaData(activityInfo.metaData);
    }

    ActivityTile(Parcel parcel) {
        super(parcel);
    }
}
