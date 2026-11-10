package com.hisense.tvs;

import android.util.Log;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.regza.cesi.CesiConnection;
import com.regza.cesi.Settings;
import java.util.concurrent.Semaphore;
import org.jdeferred2.DoneCallback;
import org.jdeferred2.FailCallback;

/* JADX INFO: loaded from: classes.dex */
public class FacTvsInterface {
    private static FacTvsInterface mFacTvsInterface;
    CesiConnection mCesiConnection;
    private boolean mIsConnected;
    private Object mResultObject;
    String cesiIp = "127.0.0.1";
    String TAG = "FacTvsInterface";
    private int ret = -1;

    private FacTvsInterface() {
        initTvsSocket();
    }

    public static synchronized FacTvsInterface getInstance() {
        if (mFacTvsInterface == null) {
            mFacTvsInterface = new FacTvsInterface();
        }
        return mFacTvsInterface;
    }

    public void initTvsSocket() {
        openCesi();
    }

    public JsonNode getConstants() {
        CesiConnection cesiConnection = this.mCesiConnection;
        if (cesiConnection == null) {
            return null;
        }
        return cesiConnection.getConstants();
    }

    private synchronized void openCesi() {
        try {
            final Semaphore semaphore = new Semaphore(0);
            Log.i(this.TAG, "openCesi start");
            CesiConnection cesiConnection = new CesiConnection(new Settings(null, 100L, true));
            this.mCesiConnection = cesiConnection;
            cesiConnection.open(this.cesiIp, 7681).done(new DoneCallback() { // from class: com.hisense.tvs.FacTvsInterface.2
                @Override // org.jdeferred2.DoneCallback
                public void onDone(Object obj) {
                    FacTvsInterface.this.mIsConnected = true;
                    semaphore.release();
                    Log.i(FacTvsInterface.this.TAG, "openCesi Succ!");
                }
            }).fail(new FailCallback() { // from class: com.hisense.tvs.FacTvsInterface.1
                @Override // org.jdeferred2.FailCallback
                public void onFail(Object obj) {
                    FacTvsInterface.this.mIsConnected = false;
                    semaphore.release();
                    Log.i(FacTvsInterface.this.TAG, "openCesi failed!");
                }
            });
            semaphore.acquire();
            threadSleep(500L);
            semaphore.release();
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(this.TAG, "openCesi e  = " + e);
        }
    }

    public synchronized boolean isConnection() {
        if (this.mCesiConnection == null) {
            return false;
        }
        return this.mCesiConnection.isOpen();
    }

    public synchronized Object sendMessage(String str, JsonNode jsonNode) {
        Log.e(this.TAG, "api sendMessage function = " + str);
        final Semaphore semaphore = new Semaphore(0);
        try {
            if (!isConnection()) {
                openCesi();
            }
            if (!isConnection()) {
                Log.e(this.TAG, "api sendMessage no connect, return");
                return null;
            }
            this.ret = -1;
            this.mCesiConnection.sendMessage(str, jsonNode).done(new DoneCallback() { // from class: com.hisense.tvs.FacTvsInterface.4
                @Override // org.jdeferred2.DoneCallback
                public void onDone(Object obj) {
                    Log.e(FacTvsInterface.this.TAG, "api sendMessage onDone result = " + obj);
                    FacTvsInterface.this.ret = 0;
                    FacTvsInterface.this.mResultObject = obj;
                    semaphore.release();
                    Log.e(FacTvsInterface.this.TAG, "api sendMessage onDone result2 = " + obj);
                }
            }).fail(new FailCallback() { // from class: com.hisense.tvs.FacTvsInterface.3
                @Override // org.jdeferred2.FailCallback
                public void onFail(Object obj) {
                    FacTvsInterface.this.ret = -1;
                    if (obj == null) {
                        obj = "failed";
                    }
                    Log.i(FacTvsInterface.this.TAG, "sendMessage failed!" + obj.toString());
                    FacTvsInterface.this.mResultObject = obj;
                    semaphore.release();
                }
            });
            Log.i(this.TAG, "sendMessage mSemaphore count1 = " + semaphore.availablePermits());
            semaphore.acquire();
            ((ObjectNode) jsonNode).put("result", this.ret);
            semaphore.release();
            Log.i(this.TAG, "sendMessage mSemaphore count2 = " + semaphore.availablePermits());
            return this.mResultObject;
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(this.TAG, "sendMessage e  = " + e);
        }
    }

    public synchronized ObjectNode getJson() {
        return this.mCesiConnection.createJson();
    }

    private void threadSleep(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
