package com.hisense.uifac.util;

import android.content.Context;
import android.content.Intent;
import android.hardware.hdmi.HdmiDeviceInfo;
import android.media.tv.TvContract;
import android.media.tv.TvInputInfo;
import android.media.tv.TvInputManager;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.hisense.tvcm.FacPropertyDemo;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ChangeSourceUtil {
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static boolean changeSourceByName(String str, Context context) {
        Log.d("Fac_changeSource", "the source name is" + str);
        List<TvInputInfo> tvInputList = ((TvInputManager) context.getSystemService("tv_input")).getTvInputList();
        if (!TextUtils.isEmpty(str)) {
            Uri uriChangetoHdmi = null;
            byte b = -1;
            int iHashCode = str.hashCode();
            if (iHashCode != 2690) {
                if (iHashCode != 604213863) {
                    switch (iHashCode) {
                        case 2126463401:
                            if (str.equals("HDMI 1")) {
                                b = 1;
                            }
                            break;
                        case 2126463402:
                            if (str.equals("HDMI 2")) {
                                b = 2;
                            }
                            break;
                        case 2126463403:
                            if (str.equals("HDMI 3")) {
                                b = 3;
                            }
                            break;
                        case 2126463404:
                            if (str.equals("HDMI 4")) {
                                b = 4;
                            }
                            break;
                    }
                } else if (str.equals("Composite")) {
                    b = 5;
                }
            } else if (str.equals("TV")) {
                b = 0;
            }
            if (b == 0) {
                uriChangetoHdmi = Uri.parse("content://android.media.tv/channel");
            } else if (b == 1) {
                uriChangetoHdmi = changetoHdmi(tvInputList, 1, context, str);
            } else if (b == 2) {
                uriChangetoHdmi = changetoHdmi(tvInputList, 2, context, str);
            } else if (b == 3) {
                uriChangetoHdmi = changetoHdmi(tvInputList, 3, context, str);
            } else if (b == 4) {
                uriChangetoHdmi = changetoHdmi(tvInputList, 4, context, str);
            } else if (b == 5) {
                uriChangetoHdmi = changetoComposite(tvInputList);
            }
            Log.d("Fac_changeSource", "uri" + uriChangetoHdmi);
            if (uriChangetoHdmi != null) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                if (FacPropertyDemo.HIFAC_MARKET_JP) {
                    intent.setPackage("com.regza.app");
                } else {
                    intent.setPackage("com.mediatek.wwtv.tvcenter");
                }
                intent.setData(uriChangetoHdmi);
                intent.setFlags(268435456);
                context.startActivity(intent);
                return true;
            }
        } else {
            Log.d("Fac_changeSource", "the source name can not be null");
        }
        return false;
    }

    private static Uri changetoHdmi(List<TvInputInfo> list, int i, Context context, String str) {
        for (TvInputInfo tvInputInfo : list) {
            Log.d("Fac_changeSource", "tvInputInfo:" + tvInputInfo);
            if (1007 == tvInputInfo.getType()) {
                CharSequence charSequenceLoadLabel = tvInputInfo.loadLabel(context);
                HdmiDeviceInfo hdmiDeviceInfo = tvInputInfo.getHdmiDeviceInfo();
                Log.d("Fac_changeSource", "label" + ((Object) charSequenceLoadLabel) + "tvInputInfo.getHdmiDeviceInfo:" + hdmiDeviceInfo);
                if (charSequenceLoadLabel != null && str.equals(charSequenceLoadLabel.toString())) {
                    return TvContract.buildChannelUriForPassthroughInput(tvInputInfo.getId());
                }
                if (hdmiDeviceInfo != null) {
                    Log.d("Fac_changeSource", "portId:" + hdmiDeviceInfo.getPortId() + "deviceid:" + hdmiDeviceInfo.getDeviceId() + "displayname:" + hdmiDeviceInfo.getDisplayName());
                    if (i == tvInputInfo.getHdmiDeviceInfo().getPortId()) {
                        return TvContract.buildChannelUriForPassthroughInput(tvInputInfo.getId());
                    }
                } else {
                    continue;
                }
            }
        }
        return null;
    }

    private static Uri changetoComposite(List<TvInputInfo> list) {
        for (TvInputInfo tvInputInfo : list) {
            Log.d("Fac_changeSource", "tvInputInfo:" + tvInputInfo);
            if (1001 == tvInputInfo.getType()) {
                return TvContract.buildChannelUriForPassthroughInput(tvInputInfo.getId());
            }
        }
        return null;
    }
}
