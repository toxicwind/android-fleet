package com.mediatek.twoworlds.tv;

import android.util.Log;
import com.mediatek.twoworlds.tv.model.MtkTvGamCmdInfoBase;

/* JADX INFO: loaded from: classes2.dex */
public class MtkGamlientBase {
    public static final int BRDCST_RET_FAIL = -1;
    public static final String TAG = "[GAM]MtkTvGamClientBase";

    public int GamSendcmd(MtkTvGamCmdInfoBase mtkTvGamCmdInfoBase) {
        Log.d(TAG, "Enter GamSendcmd: \n");
        Log.d(TAG, "s_source = " + mtkTvGamCmdInfoBase.getsource() + ", s_destination = " + mtkTvGamCmdInfoBase.getdestination() + ", s_ack = " + mtkTvGamCmdInfoBase.getack() + ", s_command = " + mtkTvGamCmdInfoBase.getcommand() + ", s_parameter = " + mtkTvGamCmdInfoBase.getparameter() + "\n");
        return TVNativeWrapper.GamSendcmd_native(mtkTvGamCmdInfoBase);
    }
}
