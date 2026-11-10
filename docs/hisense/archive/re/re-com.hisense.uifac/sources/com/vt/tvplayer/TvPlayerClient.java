package com.vt.tvplayer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.Surface;

/* JADX INFO: loaded from: classes.dex */
public class TvPlayerClient {
    private IHMediaPlayerInterface mClient_playerInterface;
    private Surface mTvSurface = null;

    public TvPlayerClient(Context context) {
        Log.i("AntPlayer_Client", "TvPlayerClient() context:" + context);
        connectToTvplayerService(context);
    }

    private class ConnectCallBack implements ServiceConnection {
        private ConnectCallBack() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                TvPlayerClient.this.onTvPlayerServiceConnected(componentName, iBinder);
            } catch (RemoteException e) {
                Log.w("AntPlayer_Client", "onServiceConnected() RemoteException", e);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            try {
                TvPlayerClient.this.onTvPlayerServiceDisconnected(componentName);
            } catch (Exception e) {
                Log.w("AntPlayer_Client", "onServiceDisconnected() Exception", e);
            }
        }
    }

    private void connectToTvplayerService(Context context) {
        try {
            Log.d("AntPlayer_Client", "TvPlayerClient.connectToTvplayerService()");
            Intent intent = new Intent();
            intent.setClassName("com.vt.tvplayer", "com.vt.tvplayer.HMediaPlayerService");
            context.bindService(intent, new ConnectCallBack(), 1);
        } catch (Exception e) {
            Log.w("AntPlayer_Client", "connectToTvplayerService() Exception", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTvPlayerServiceConnected(ComponentName componentName, IBinder iBinder) throws RemoteException {
        Log.d("AntPlayer_Client", "TvPlayerClient.onServiceConnected(). surface=" + this.mTvSurface);
        IHMediaPlayerInterface iHMediaPlayerInterfaceAsInterface = IHMediaPlayerInterface.Stub.asInterface(iBinder);
        this.mClient_playerInterface = iHMediaPlayerInterfaceAsInterface;
        iHMediaPlayerInterfaceAsInterface.livetv_setSurface(this.mTvSurface);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTvPlayerServiceDisconnected(ComponentName componentName) {
        Log.d("AntPlayer_Client", "TvPlayerClient.onServiceDisconnected()");
        this.mClient_playerInterface = null;
    }

    public void setSurface(Surface surface) {
        Log.d("AntPlayer_Client", "TvPlayerClient.setTvSurface(). surface=" + surface);
        this.mTvSurface = surface;
        if (this.mClient_playerInterface == null) {
            Log.d("AntPlayer_Client", "TvPlayerClient.setTvSurface(). mClient_playerInterface is null");
            return;
        }
        try {
            Log.d("AntPlayer_Client", "TvPlayerClient.setTvSurface(). call livetv_setSurface()");
            this.mClient_playerInterface.livetv_setSurface(this.mTvSurface);
        } catch (RemoteException e) {
            Log.w("AntPlayer_Client", "setSurface() RemoteException", e);
        }
    }
}
