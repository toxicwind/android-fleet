package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;
import com.mediatek.twoworlds.tv.model.MtkTvParserIniInfoBase;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvParserIniBase {
    public final String TAG = "MtkTvParserIniBase";

    public String getFileBasePath() {
        Log.d("MtkTvParserIniBase", "Enter getFileBasePath Here..");
        String fileBasePath_native = TVNativeWrapper.getFileBasePath_native();
        a.a("&&&&&, MtkTvParserIniBase.getFileBasePath. Name =", fileBasePath_native, ".\n", "MtkTvParserIniBase");
        return fileBasePath_native;
    }

    public MtkTvParserIniInfoBase getIntConfigData(String str, String str2) {
        Log.d("MtkTvParserIniBase", "Enter getUnsignedConfigData Here..");
        return TVNativeWrapper.getIntConfigData_native(str, str2);
    }

    public MtkTvParserIniInfoBase getStringConfigData(String str, String str2) {
        Log.d("MtkTvParserIniBase", "Enter getStringConfigData Here..");
        return TVNativeWrapper.getStringConfigData_native(str, str2);
    }
}
