package com.hisense.uifac.util;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothPaireObserver extends ContentObserver {
    private Context mContext;
    Handler mHandler;

    public BluetoothPaireObserver(Context context, Handler handler) {
        super(handler);
        this.mContext = context;
        this.mHandler = handler;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z) {
        super.onChange(z);
        Log.d("Fac_BluetoothPaireObserver", "When the function is called,the database value has been changed");
        int i = Settings.Global.getInt(this.mContext.getContentResolver(), "bluetooth_remote_connected", 0);
        Message messageObtain = Message.obtain();
        messageObtain.what = MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_PAUSE_STATUS;
        Log.d("Fac_BluetoothPaireObserver", "BluetoothPaireObserver status = " + i);
        if (1 == i) {
            messageObtain.obj = "Bluetooth Paired!!";
        } else {
            messageObtain.obj = "Bluetooth Unpaired!";
        }
        this.mHandler.sendMessage(messageObtain);
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z, Uri uri) {
        super.onChange(z, uri);
    }
}
