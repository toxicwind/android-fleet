package com.hisense.panelprotect;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class PanelProtectManager {
    public static AudioManager mAudioManager;
    private static PanelProtectManager mInstance;
    private Context mContext;
    private ArrayList<StatusChangeListener> mListeners = new ArrayList<>();
    private IPanelProtectService mService = null;
    private ServiceConnection mServiceConnection = new ServiceConnection() { // from class: com.hisense.panelprotect.PanelProtectManager.3
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d("PanelProtectManager", "onServiceConnected()");
            PanelProtectManager.this.mService = IPanelProtectService.Stub.asInterface(iBinder);
            if (PanelProtectManager.this.mService != null) {
                try {
                    PanelProtectManager.this.mService.registerStatusChangeListener(PanelProtectManager.this.mServiceCallback);
                    return;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return;
                }
            }
            Log.e("PanelProtectManager", "onServiceConnected, service is null");
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d("PanelProtectManager", "onServiceDisconnected()");
            PanelProtectManager.this.mService = null;
        }
    };
    private IStatusChangeListener.Stub mServiceCallback = new IStatusChangeListener.Stub() { // from class: com.hisense.panelprotect.PanelProtectManager.4
        @Override // com.hisense.panelprotect.IStatusChangeListener
        public void onStatusChanged(int i) {
            Log.i("PanelProtectManager", "onStatusChanged status:" + i);
            Iterator it = PanelProtectManager.this.mListeners.iterator();
            while (it.hasNext()) {
                ((StatusChangeListener) it.next()).onStatusChanged(i);
            }
        }
    };

    public interface StatusChangeListener {
        void onStatusChanged(int i);
    }

    public static PanelProtectManager getInstance(Context context) {
        synchronized (PanelProtectManager.class) {
            if (mInstance == null) {
                mInstance = new PanelProtectManager(context);
            }
        }
        if (mAudioManager == null) {
            mAudioManager = (AudioManager) context.getSystemService("audio");
        }
        return mInstance;
    }

    public boolean startOffrs(int i) {
        Log.i("PanelProtectManager", "call startOffrs, value: " + i + ",service is " + this.mService);
        if (this.mService == null) {
            return false;
        }
        try {
            Log.i("PanelProtectManager", "call startOffrs, mAudioManager_new: " + mAudioManager);
            if (mAudioManager != null && !mAudioManager.isStreamMute(3)) {
                mAudioManager.setStreamMute(3, true);
            }
            return this.mService.startOffrs(i, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean startJb(int i) {
        Log.i("PanelProtectManager", "call startJb, value: " + i + ",service is ," + this.mService);
        if (this.mService == null) {
            return false;
        }
        try {
            if (mAudioManager != null && !mAudioManager.isStreamMute(3)) {
                mAudioManager.setStreamMute(3, true);
            }
            return this.mService.startJb(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    private PanelProtectManager(Context context) {
        this.mContext = null;
        this.mContext = context;
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.hisense.panelprotect", "com.hisense.panelprotect.PanelProtectService"));
        this.mContext.bindService(intent, this.mServiceConnection, 1);
        HandlerThread handlerThread = new HandlerThread("DownloadDialog");
        handlerThread.start();
        new Handler(handlerThread.getLooper());
        Log.d("PanelProtectManager", "mContext.bindService success");
    }

    public void finalize() {
        try {
            if (this.mService != null) {
                Log.i("PanelProtectManager", "finalize called, call mService.unregisterStatusChangeListener now.\n");
                this.mService.unregisterStatusChangeListener(this.mServiceCallback);
            }
            this.mContext.unbindService(this.mServiceConnection);
            this.mContext = null;
            this.mService = null;
            mInstance = null;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        try {
            super.finalize();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
