package com.google.android.exoplayer.dash.mpd;

import com.google.android.exoplayer.drm.DrmInitData;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.Util;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class ContentProtection {
    public final DrmInitData.SchemeInitData data;
    public final String schemeUriId;
    public final UUID uuid;

    public ContentProtection(String str, UUID uuid, DrmInitData.SchemeInitData schemeInitData) {
        Assertions.checkNotNull(str);
        this.schemeUriId = str;
        this.uuid = uuid;
        this.data = schemeInitData;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ContentProtection)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        ContentProtection contentProtection = (ContentProtection) obj;
        return this.schemeUriId.equals(contentProtection.schemeUriId) && Util.areEqual(this.uuid, contentProtection.uuid) && Util.areEqual(this.data, contentProtection.data);
    }

    public int hashCode() {
        int iHashCode = this.schemeUriId.hashCode() * 37;
        UUID uuid = this.uuid;
        int iHashCode2 = (iHashCode + (uuid != null ? uuid.hashCode() : 0)) * 37;
        DrmInitData.SchemeInitData schemeInitData = this.data;
        return iHashCode2 + (schemeInitData != null ? schemeInitData.hashCode() : 0);
    }
}
