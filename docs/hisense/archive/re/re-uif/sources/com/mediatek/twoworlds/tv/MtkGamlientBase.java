package com.mediatek.twoworlds.tv;

import android.util.Log;
import com.mediatek.twoworlds.tv.model.MtkTvGamCmdInfoBase;

/* JADX INFO: loaded from: classes.dex */
public class MtkGamlientBase {
    public static final int BRDCST_RET_FAIL = -1;
    public static final String TAG = "[GAM]MtkTvGamClientBase";

    public int GamSendcmd(MtkTvGamCmdInfoBase mtkTvGamCmdInfoBase) {
        Log.d(TAG, "Enter GamSendcmd: \n");
        Log.d(TAG, "s_source = " + mtkTvGamCmdInfoBase.getsource() + ", s_destination = " + mtkTvGamCmdInfoBase.getdestination() + ", s_ack = " + mtkTvGamCmdInfoBase.getack() + ", s_command = " + mtkTvGamCmdInfoBase.getcommand() + ", s_parameter = " + mtkTvGamCmdInfoBase.getparameter() + "\n");
        if (mtkTvGamCmdInfoBase != null) {
            return TVNativeWrapper.GamSendcmd_native(mtkTvGamCmdInfoBase);
        }
        Log.e(TAG, "The 'cmdInfo' is null!\n");
        return -1;
    }
}
