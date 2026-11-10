package com.hisense.serialInfoTest.model;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.hisense.tvcm.FacPropertyDemo;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class IpcManagerService extends Service {
    int[] finalType;
    ArrayList<Integer> supportSourceTilValue;
    private List<FacPropertyDemo.SourceEnum> mSupportedSourceList = null;
    private Binder mBinder = new IpcManager.Stub() { // from class: com.hisense.serialInfoTest.model.IpcManagerService.1
        @Override // com.hisense.serialInfoTest.model.IpcManager
        public int[] getSupportSources() throws RemoteException {
            Log.d("Fac_IpcManagerService", "mBinder getSupportSources--> we have  " + IpcManagerService.this.finalType.length + "  sources");
            return IpcManagerService.this.finalType;
        }
    };

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Log.d("Fac_IpcManagerService", "IpcManagerService-->onBind called");
        return this.mBinder;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mSupportedSourceList = FacPropertyDemo.SourceEnum.getSupportedSourceList();
        Log.d("Fac_IpcManagerService", "IpcManagerService-->onCreate we have " + this.mSupportedSourceList.size() + " sources");
        this.supportSourceTilValue = new ArrayList<>();
        for (int i = 0; i < this.mSupportedSourceList.size(); i++) {
            Log.d("Fac_IpcManagerService", "source tilvalue = " + this.mSupportedSourceList.get(i).getTilValue());
            this.supportSourceTilValue.add(Integer.valueOf(this.mSupportedSourceList.get(i).getTilValue()));
        }
        this.finalType = new int[this.supportSourceTilValue.size()];
        for (int i2 = 0; i2 < this.supportSourceTilValue.size(); i2++) {
            this.finalType[i2] = this.supportSourceTilValue.get(i2).intValue();
        }
        Log.d("Fac_IpcManagerService", "IpcManagerService-->onCreate finalType.lenth is :" + this.finalType.length);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
