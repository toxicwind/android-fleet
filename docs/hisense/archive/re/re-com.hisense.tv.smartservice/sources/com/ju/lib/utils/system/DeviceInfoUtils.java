package com.ju.lib.utils.system;

import android.content.Context;
import android.text.TextUtils;
import com.hisense.hitv.hicloud.util.DeviceConfig;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceInfoUtils {
    public static String sDeviceId;

    public static String getDeviceId(Context context) {
        if (sDeviceId == null) {
            sDeviceId = DeviceConfig.getDeviceId(context);
        }
        return sDeviceId;
    }

    public static String getDeviceMessage() {
        String prop = DeviceConfig.getProp("ro.product.hisense.model");
        if (TextUtils.isEmpty(prop)) {
            prop = DeviceConfig.getProp("ro.build.product.name");
        }
        return TextUtils.isEmpty(prop) ? DeviceConfig.getProp("ro.product.series") : prop;
    }
}
