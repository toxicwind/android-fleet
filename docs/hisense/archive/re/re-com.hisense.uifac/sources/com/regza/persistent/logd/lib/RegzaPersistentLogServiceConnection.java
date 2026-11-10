package com.regza.persistent.logd.lib;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.regza.persistent.logd.IRegzaPersistentLogService;
import com.regza.persistent.logd.IRegzaPersistentLogServiceCallback;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class RegzaPersistentLogServiceConnection {
    private static final String TAG = "RegzaPersistentLogServiceConnection";
    private static final BlockingQueue<Boolean> mBindServiceQueue = new ArrayBlockingQueue(1);
    private static final BlockingQueue<Boolean> mSaveLogQueue = new ArrayBlockingQueue(1);
    private Context mContext;
    private IRegzaPersistentLogService mIService;
    private BIND_STATUS mBindStatus = BIND_STATUS.non;
    private final Object lock = new Object();
    private IRegzaPersistentLogServiceCallback mICallback = new IRegzaPersistentLogServiceCallback.Stub(this) { // from class: com.regza.persistent.logd.lib.RegzaPersistentLogServiceConnection.1
        @Override // com.regza.persistent.logd.IRegzaPersistentLogServiceCallback
        public void onLogSaved(int i) throws RemoteException {
            Log.d(RegzaPersistentLogServiceConnection.TAG, "onLogSaved result:" + i);
            RegzaPersistentLogServiceConnection.mSaveLogQueue.offer(Boolean.valueOf(i == 0));
        }
    };

    enum BIND_STATUS {
        non,
        bind_request,
        binding,
        unbind
    }

    public RegzaPersistentLogServiceConnection(Context context) {
        this.mContext = null;
        this.mContext = context;
    }

    public boolean executeLogSave() {
        if (isBindingService()) {
            return executeUsbSaveLog();
        }
        return syncBindService(this.mContext);
    }

    private boolean isBindingService() {
        return this.mBindStatus == BIND_STATUS.binding;
    }

    private boolean syncBindService(Context context) {
        mBindServiceQueue.clear();
        Intent intent = new Intent("com.regza.persistent.logd.RegzaPersistentLogService");
        intent.setPackage("com.regza.persistent.logd");
        context.bindService(intent, 1, Executors.newSingleThreadExecutor(), new ServiceConnection() { // from class: com.regza.persistent.logd.lib.RegzaPersistentLogServiceConnection.2
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                synchronized (RegzaPersistentLogServiceConnection.this.lock) {
                    Log.d(RegzaPersistentLogServiceConnection.TAG, "onServiceConnected()");
                    RegzaPersistentLogServiceConnection.this.mIService = IRegzaPersistentLogService.Stub.asInterface(iBinder);
                    RegzaPersistentLogServiceConnection.mBindServiceQueue.offer(Boolean.TRUE);
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                Log.d(RegzaPersistentLogServiceConnection.TAG, "onServiceDisconnected()");
                RegzaPersistentLogServiceConnection.this.mBindStatus = BIND_STATUS.unbind;
            }
        });
        try {
            this.mBindStatus = BIND_STATUS.bind_request;
            Boolean boolPoll = mBindServiceQueue.poll(10000L, TimeUnit.MILLISECONDS);
            if (boolPoll == null) {
                Log.d(TAG, "bindService() Time out");
                this.mBindStatus = BIND_STATUS.non;
                return false;
            }
            if (boolPoll.booleanValue()) {
                this.mBindStatus = BIND_STATUS.binding;
                return executeUsbSaveLog();
            }
            this.mBindStatus = BIND_STATUS.non;
            return true;
        } catch (InterruptedException e) {
            Log.d(TAG, "InterruptedException:" + e);
            return false;
        }
    }

    private boolean executeUsbSaveLog() {
        Boolean bool = Boolean.FALSE;
        mSaveLogQueue.clear();
        Executors.newSingleThreadExecutor().submit(new Runnable() { // from class: com.regza.persistent.logd.lib.-$$Lambda$RegzaPersistentLogServiceConnection$T4825XQbsWkgokAKqsI7DGtmcpM
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$executeUsbSaveLog$0$RegzaPersistentLogServiceConnection();
            }
        });
        try {
            Boolean boolPoll = mSaveLogQueue.poll(10000L, TimeUnit.MILLISECONDS);
            if (boolPoll == null) {
                Log.d(TAG, "saveLog() Time out");
            } else {
                bool = boolPoll;
            }
        } catch (InterruptedException e) {
            Log.d(TAG, "InterruptedException:" + e);
        }
        return bool.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$executeUsbSaveLog$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$executeUsbSaveLog$0$RegzaPersistentLogServiceConnection() {
        try {
            this.mIService.saveLog(this.mICallback);
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException:" + e);
            mSaveLogQueue.offer(Boolean.FALSE);
        }
    }
}
