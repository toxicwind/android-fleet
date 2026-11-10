package com.mediatek.twoworlds.tv;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.RemoteException;
import com.mediatek.twoworlds.tv.common.MtkTvIntentBase;
import com.mediatek.twoworlds.tv.common.MtkTvIntentNotifyCodeBase;
import com.mediatek.twoworlds.tv.model.MtkTvCIMMIEnqBase;
import com.mediatek.twoworlds.tv.model.MtkTvCIMMIMenuBase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import mediatek.sysprop.VendorProperties;

/* JADX INFO: loaded from: classes.dex */
public class TVCallback {
    private static final String INTENT_ACTION_SERIAL_CONTROL = "com.jamdeo.intent.SERIAL_CONTROL";
    private static final String TAG = "TVCallback";
    private static Context mContext;
    private static Handler mThreadHandler;
    private boolean callBackDebug = true;
    private List<String> callBacks = new ArrayList();
    private static final CallbackList<MtkTvTVCallbackHandler> clientCallbacks = new CallbackList<>();
    private static final Map<String, ArrayList<MtkTvTVCallbackHandler>> mCfgValChgCallBacks = new HashMap();
    private static final TVCallback mCallback = new TVCallback();
    private static PendingIntent pendItforBGM = null;
    private static PendingIntent pendItforNORMAL_ON = null;
    private static int mLastDtValue = -1;
    private static HandlerThread mHandlerThead = null;

    private TVCallback() {
        if (mHandlerThead == null) {
            HandlerThread handlerThread = new HandlerThread(TAG);
            mHandlerThead = handlerThread;
            handlerThread.start();
            mThreadHandler = new Handler(mHandlerThead.getLooper());
        }
    }

    public static TVCallback getTVCallback() {
        return mCallback;
    }

    public static void setContext(Context context) {
        mContext = context;
        TVNativeWrapper.registerDefaultCallback_native();
    }

    public void destroyCallBack() {
        synchronized (TVCallback.class) {
            TvDebugLog.d(TAG, "destroyCallBack:");
            if (this.callBackDebug) {
                for (int i = 0; i < this.callBacks.size(); i++) {
                    TvDebugLog.d(TAG, this.callBacks.get(i));
                }
            }
        }
    }

    protected void registerCallback(MtkTvTVCallbackHandler mtkTvTVCallbackHandler) {
        synchronized (TVCallback.class) {
            TvDebugLog.d(TAG, "registerCallback:" + mtkTvTVCallbackHandler);
            if (mtkTvTVCallbackHandler != null) {
                clientCallbacks.add(mtkTvTVCallbackHandler);
                if (this.callBackDebug) {
                    this.callBacks.add(mtkTvTVCallbackHandler.toString());
                }
            }
        }
        TVNativeWrapper.registerDefaultCallback_native();
    }

    protected void unregisterCallback(MtkTvTVCallbackHandler mtkTvTVCallbackHandler) {
        synchronized (TVCallback.class) {
            TvDebugLog.d(TAG, "unregisterCallback:" + mtkTvTVCallbackHandler);
            if (mtkTvTVCallbackHandler != null) {
                clientCallbacks.remove(mtkTvTVCallbackHandler);
                if (this.callBackDebug) {
                    this.callBacks.remove(mtkTvTVCallbackHandler.toString());
                }
                if (this.callBacks.size() == 0) {
                    TVNativeWrapper.unregisterDefaultCallback_native();
                }
            }
        }
    }

    public int addListenerConfig(MtkTvTVCallbackHandler mtkTvTVCallbackHandler, String str) {
        TvDebugLog.d(TAG, "addListenerConfig: " + mtkTvTVCallbackHandler.toString() + ", " + str);
        synchronized (MtkTvConfig.class) {
            ArrayList<MtkTvTVCallbackHandler> arrayList = mCfgValChgCallBacks.get(str);
            if (arrayList == null) {
                TVNativeWrapper.addConfigListener_native(str);
                TvDebugLog.d(TAG, "addListenerConfig: new, create list");
                ArrayList<MtkTvTVCallbackHandler> arrayList2 = new ArrayList<>();
                arrayList2.add(mtkTvTVCallbackHandler);
                mCfgValChgCallBacks.put(str, arrayList2);
            } else {
                if (!arrayList.contains(mtkTvTVCallbackHandler)) {
                    arrayList.add(mtkTvTVCallbackHandler);
                }
                TvDebugLog.d(TAG, "addListenerConfig: add to list," + arrayList.size());
            }
        }
        return 0;
    }

    public int removeListenerConfig(MtkTvTVCallbackHandler mtkTvTVCallbackHandler, String str) {
        TvDebugLog.d(TAG, "removeListenerConfig: " + mtkTvTVCallbackHandler.toString() + ", " + str);
        ArrayList arrayList = new ArrayList();
        synchronized (MtkTvConfig.class) {
            try {
                if (str == null) {
                    Iterator<String> it = mCfgValChgCallBacks.keySet().iterator();
                    while (it.hasNext()) {
                        arrayList.add(it.next().toString());
                    }
                } else {
                    arrayList.add(str);
                }
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    removeOneListenerConfig(mtkTvTVCallbackHandler, (String) it2.next());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return 0;
    }

    private int removeOneListenerConfig(MtkTvTVCallbackHandler mtkTvTVCallbackHandler, String str) {
        synchronized (MtkTvConfig.class) {
            ArrayList<MtkTvTVCallbackHandler> arrayList = mCfgValChgCallBacks.get(str);
            if (arrayList != null) {
                arrayList.remove(mtkTvTVCallbackHandler);
                if (arrayList.size() == 0) {
                    mCfgValChgCallBacks.remove(str);
                    TVNativeWrapper.removeConfigListener_native(str);
                }
            }
        }
        return 0;
    }

    protected static int DO_notifySvctxNotificationCode(int i) {
        TvDebugLog.d(TAG, "(From JNI) notifySvctxNotificationCode=" + i);
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            for (int i2 = 0; i2 < iBeginBroadcast; i2++) {
                try {
                    clientCallbacks.getBroadcastItem(i2).notifySvctxNotificationCode(i);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return 0;
    }

    protected static int DO_notifySvctxNotificationCode(int i, int i2) {
        TvDebugLog.d(TAG, "(From JNI) notifySvctxNotificationCode(path = " + i + ",code = " + i2 + ")");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            for (int i3 = 0; i3 < iBeginBroadcast; i3++) {
                try {
                    clientCallbacks.getBroadcastItem(i3).notifySvctxNotificationCodebyPath(i, i2);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return 0;
    }

    protected static int DO_notifyOtherMessage(int i, int i2, int i3) {
        TvDebugLog.d(TAG, "(From JNI) a1=" + i + " a2=" + i2 + " a3=" + i3);
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            for (int i4 = 0; i4 < iBeginBroadcast; i4++) {
                try {
                    clientCallbacks.getBroadcastItem(i4).notifyOtherMessage(i, i2, i3);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return 0;
    }

    protected static int DO_notifyConfigMessage(int i, int i2) {
        int iNotifyConfigMessage;
        TvDebugLog.d(TAG, "notifyConfigMessage notifyId=" + i + " data=" + i2);
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyConfigMessage = 0;
            for (int i3 = 0; i3 < iBeginBroadcast; i3++) {
                try {
                    iNotifyConfigMessage = clientCallbacks.getBroadcastItem(i3).notifyConfigMessage(i, i2);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyConfigMessage;
    }

    protected static int DO_notifyChannelListUpdateMsg(int i, int i2, int i3) {
        int iNotifyChannelListUpdateMsg;
        TvDebugLog.d(TAG, "DO_notifyChannelListUpdateMsg: condition = " + i + ", reason = " + i2 + ", data = " + i3 + "\n");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyChannelListUpdateMsg = 0;
            for (int i4 = 0; i4 < iBeginBroadcast; i4++) {
                try {
                    iNotifyChannelListUpdateMsg = clientCallbacks.getBroadcastItem(i4).notifyChannelListUpdateMsg(i, i2, i3);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyChannelListUpdateMsg;
    }

    protected static int DO_notifyListModeUpdateMsg(int i, int i2) {
        int iNotifyListModeUpdateMsg;
        TvDebugLog.d(TAG, "DO_notifyListModeUpdateMsg: oldMode = " + i + ", newMode = " + i2 + "\n");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyListModeUpdateMsg = 0;
            for (int i3 = 0; i3 < iBeginBroadcast; i3++) {
                try {
                    iNotifyListModeUpdateMsg = clientCallbacks.getBroadcastItem(i3).notifyListModeUpdateMsg(i, i2);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyListModeUpdateMsg;
    }

    protected static int DO_notifyOclScanInfo(int i, int i2) {
        int iNotifyOclScanInfo;
        TvDebugLog.d(TAG, "DO_notifyOclScanInfo: msgId = " + i + ", channelNum = " + i2 + "\n");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyOclScanInfo = 0;
            for (int i3 = 0; i3 < iBeginBroadcast; i3++) {
                try {
                    iNotifyOclScanInfo = clientCallbacks.getBroadcastItem(i3).notifyOclScanInfo(i, i2);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyOclScanInfo;
    }

    protected static int DO_notifyBisskeyUpdateMsg(int i, int i2, int i3) {
        int iNotifyBisskeyUpdateMsg;
        TvDebugLog.d(TAG, "DO_notifyBisskeyUpdateMsg: condition = " + i + ", reason = " + i2 + ", data = " + i3 + "\n");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyBisskeyUpdateMsg = 0;
            for (int i4 = 0; i4 < iBeginBroadcast; i4++) {
                try {
                    iNotifyBisskeyUpdateMsg = clientCallbacks.getBroadcastItem(i4).notifyBisskeyUpdateMsg(i, i2, i3);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyBisskeyUpdateMsg;
    }

    protected static int DO_notifySatlListUpdateMsg(int i, int i2, int i3) {
        int iNotifySatlListUpdateMsg;
        TvDebugLog.d(TAG, "DO_notifySatlListUpdateMsg: condition = " + i + ", reason = " + i2 + ", data = " + i3 + "\n");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifySatlListUpdateMsg = 0;
            for (int i4 = 0; i4 < iBeginBroadcast; i4++) {
                try {
                    iNotifySatlListUpdateMsg = clientCallbacks.getBroadcastItem(i4).notifySatlListUpdateMsg(i, i2, i3);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifySatlListUpdateMsg;
    }

    protected static int DO_notifyTvproviderUpdateMsg(int i, int i2, int[] iArr, int[] iArr2) {
        int iNotifyTvproviderUpdateMsg;
        TvDebugLog.d(TAG, "DO_notifyTvproviderUpdateMsg");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyTvproviderUpdateMsg = 0;
            for (int i3 = 0; i3 < iBeginBroadcast; i3++) {
                try {
                    iNotifyTvproviderUpdateMsg = clientCallbacks.getBroadcastItem(i3).notifyTvproviderUpdateMsg(i, i2, iArr, iArr2);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyTvproviderUpdateMsg;
    }

    public static int DO_notifyConcernColumnUpdateMsg(int i, int i2, int[] iArr, int[] iArr2) throws RemoteException {
        int iNotifyConcernColumnUpdateMsg;
        TvDebugLog.d(TAG, "DO_notifyConcernColumnUpdateMsg");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyConcernColumnUpdateMsg = 0;
            for (int i3 = 0; i3 < iBeginBroadcast; i3++) {
                try {
                    iNotifyConcernColumnUpdateMsg = clientCallbacks.getBroadcastItem(i3).notifyConcernColumnUpdateMsg(i, i2, iArr, iArr2);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyConcernColumnUpdateMsg;
    }

    protected static int DO_notifySvlIdUpdateMsg(int i, int i2, int i3) {
        int iNotifySvlIdUpdateMsg;
        TvDebugLog.d(TAG, "DO_notifySvlIdUpdateMsg: condition = " + i + ", reason = " + i2 + ", data = " + i3 + "\n");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifySvlIdUpdateMsg = 0;
            for (int i4 = 0; i4 < iBeginBroadcast; i4++) {
                try {
                    iNotifySvlIdUpdateMsg = clientCallbacks.getBroadcastItem(i4).notifySvlIdUpdateMsg(i, i2, i3);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifySvlIdUpdateMsg;
    }

    protected static int DO_notifyTslIdUpdateMsg(int i, int i2, int i3) {
        int iNotifyTslIdUpdateMsg;
        TvDebugLog.d(TAG, "DO_notifyTslIdUpdateMsg: condition = " + i + ", reason = " + i2 + ", data = " + i3 + "\n");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyTslIdUpdateMsg = 0;
            for (int i4 = 0; i4 < iBeginBroadcast; i4++) {
                try {
                    iNotifyTslIdUpdateMsg = clientCallbacks.getBroadcastItem(i4).notifyTslIdUpdateMsg(i, i2, i3);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyTslIdUpdateMsg;
    }

    protected static int DO_notifyNativeAppStatus(int i, boolean z) {
        int iNotifyNativeAppStatus;
        TvDebugLog.d(TAG, "notifyNativeAppStatus nativeAppId=" + i + " show=" + z);
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyNativeAppStatus = 0;
            for (int i2 = 0; i2 < iBeginBroadcast; i2++) {
                try {
                    iNotifyNativeAppStatus = clientCallbacks.getBroadcastItem(i2).notifyNativeAppStatus(i, z);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyNativeAppStatus;
    }

    protected static int DO_notifyShowOSDMessage(int i, int i2) {
        int iNotifyShowOSDMessage;
        TvDebugLog.d(TAG, "DO_notifyShowOSDMessage stringId=" + i + " msgType=" + i2);
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyShowOSDMessage = 0;
            for (int i3 = 0; i3 < iBeginBroadcast; i3++) {
                try {
                    iNotifyShowOSDMessage = clientCallbacks.getBroadcastItem(i3).notifyShowOSDMessage(i, i2);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyShowOSDMessage;
    }

    protected static int DO_notifyHideOSDMessage() {
        int iNotifyHideOSDMessage;
        TvDebugLog.d(TAG, "DO_notifyHideOSDMessage");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyHideOSDMessage = 0;
            for (int i = 0; i < iBeginBroadcast; i++) {
                try {
                    iNotifyHideOSDMessage = clientCallbacks.getBroadcastItem(i).notifyHideOSDMessage();
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyHideOSDMessage;
    }

    protected static int DO_notifyAmpVolCtrlMessage(int i, boolean z) {
        int iNotifyAmpVolCtrlMessage;
        TvDebugLog.d(TAG, "DO_notifyAmpVolCtrlMessage");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyAmpVolCtrlMessage = 0;
            for (int i2 = 0; i2 < iBeginBroadcast; i2++) {
                try {
                    iNotifyAmpVolCtrlMessage = clientCallbacks.getBroadcastItem(i2).notifyAmpVolCtrlMessage(i, z);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyAmpVolCtrlMessage;
    }

    protected static int DO_notifyCecNotificationCode(int i) {
        int iNotifyCecNotificationCode;
        TvDebugLog.d(TAG, "DO_notifyCecNotificationCode");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyCecNotificationCode = 0;
            for (int i2 = 0; i2 < iBeginBroadcast; i2++) {
                try {
                    iNotifyCecNotificationCode = clientCallbacks.getBroadcastItem(i2).notifyCecNotificationCode(i);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyCecNotificationCode;
    }

    protected static int DO_notifyCecFrameInfo(int i, int i2, int i3, int[] iArr, int i4) {
        int iNotifyCecFrameInfo;
        TvDebugLog.d(TAG, "DO_notifyCecFrameInfo");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyCecFrameInfo = 0;
            for (int i5 = 0; i5 < iBeginBroadcast; i5++) {
                try {
                    iNotifyCecFrameInfo = clientCallbacks.getBroadcastItem(i5).notifyCecFrameInfo(i, i2, i3, iArr, i4);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyCecFrameInfo;
    }

    protected static int DO_notifySysAudMod(int i) {
        int iNotifySysAudMod;
        TvDebugLog.d(TAG, "DO_notifySysAudMod");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifySysAudMod = 0;
            for (int i2 = 0; i2 < iBeginBroadcast; i2++) {
                try {
                    iNotifySysAudMod = clientCallbacks.getBroadcastItem(i2).notifySysAudMod(i);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifySysAudMod;
    }

    protected static int DO_notifyCecActiveSource(int i, int i2, boolean z) {
        int iNotifyCecActiveSource;
        TvDebugLog.d(TAG, "DO_notifyCecActiveSource");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyCecActiveSource = 0;
            for (int i3 = 0; i3 < iBeginBroadcast; i3++) {
                try {
                    iNotifyCecActiveSource = clientCallbacks.getBroadcastItem(i3).notifyCecActiveSource(i, i2, z);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyCecActiveSource;
    }

    protected static int DO_notifyMhlScratchpadData(int i, int i2, int i3, int[] iArr) {
        int iNotifyMhlScratchpadData;
        TvDebugLog.d(TAG, "DO_notifyMhlScratchpadData");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyMhlScratchpadData = 0;
            for (int i4 = 0; i4 < iBeginBroadcast; i4++) {
                try {
                    iNotifyMhlScratchpadData = clientCallbacks.getBroadcastItem(i4).notifyMhlScratchpadData(i, i2, i3, iArr);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyMhlScratchpadData;
    }

    protected static int DO_notifyDeviceDiscovery() {
        int iNotifyDeviceDiscovery;
        TvDebugLog.d(TAG, "DO_notifyDeviceDiscovery");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyDeviceDiscovery = 0;
            for (int i = 0; i < iBeginBroadcast; i++) {
                try {
                    iNotifyDeviceDiscovery = clientCallbacks.getBroadcastItem(i).notifyDeviceDiscovery();
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyDeviceDiscovery;
    }

    protected static int DO_notifyUiMsDisplay(int i, boolean z) {
        int iNotifyUiMsDisplay;
        TvDebugLog.d(TAG, "DO_notifyUiMsDisplay");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyUiMsDisplay = 0;
            for (int i2 = 0; i2 < iBeginBroadcast; i2++) {
                try {
                    iNotifyUiMsDisplay = clientCallbacks.getBroadcastItem(i2).notifyUiMsDisplay(i, z);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyUiMsDisplay;
    }

    protected static int DO_notifySpdInfoFrame(int i, int i2, int i3, int i4, int[] iArr, int[] iArr2, int i5) throws RemoteException {
        int iNotifySpdInfoFrame;
        TvDebugLog.d(TAG, "DO_notifySpdInfoFrame");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifySpdInfoFrame = 0;
            for (int i6 = 0; i6 < iBeginBroadcast; i6++) {
                try {
                    iNotifySpdInfoFrame = clientCallbacks.getBroadcastItem(i6).notifySpdInfoFrame(i, i2, i3, i4, iArr, iArr2, i5);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifySpdInfoFrame;
    }

    protected static int DO_notifyEventNotificationCode(int i, int i2, int i3, int i4) {
        int iNotifyEventNotification;
        TvDebugLog.d(TAG, "DO_notifyEventNotificationCode");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyEventNotification = 0;
            for (int i5 = 0; i5 < iBeginBroadcast; i5++) {
                try {
                    iNotifyEventNotification = clientCallbacks.getBroadcastItem(i5).notifyEventNotification(i, i2, i3, i4);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyEventNotification;
    }

    protected static int DO_notifyRecordPBCallback(int i, int i2, int i3, int i4) {
        int iNotifyRecordPBNotification;
        TvDebugLog.d(TAG, "DO_notifyRecordPBCallback");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyRecordPBNotification = 0;
            for (int i5 = 0; i5 < iBeginBroadcast; i5++) {
                try {
                    iNotifyRecordPBNotification = clientCallbacks.getBroadcastItem(i5).notifyRecordPBNotification(i, i2, i3, i4);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyRecordPBNotification;
    }

    protected static int DO_notifyLinuxPowerRequest(int i, final int i2) {
        TvDebugLog.d(TAG, "notifyLinuxPowerRequest action=" + i + " tag=" + i2);
        synchronized (TVCallback.class) {
            try {
                if (i == 0) {
                    new Thread(new Runnable() { // from class: com.mediatek.twoworlds.tv.TVCallback.1
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                Intent intent = new Intent();
                                intent.addFlags(268435488);
                                intent.setAction(MtkTvIntentNotifyCodeBase.MTK_INTENT_STRING);
                                intent.putExtra("code", 1);
                                intent.putExtra("reason", i2);
                                if (TVCallback.mContext != null) {
                                    TVCallback.mContext.sendBroadcast(intent);
                                }
                            } catch (Exception e) {
                                TvDebugLog.d(TVCallback.TAG, "RemoteException:" + e.getMessage());
                            }
                        }
                    }).start();
                } else if (i == 1) {
                    TvDebugLog.d(TAG, "Power on by linux is not implemented.\n");
                } else if (i == 2) {
                    new Thread(new Runnable() { // from class: com.mediatek.twoworlds.tv.TVCallback.2
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                Intent intent = new Intent();
                                intent.addFlags(268435488);
                                intent.setAction(MtkTvIntentBase.MTK_INTENT_BGM_FINISHED_STRING);
                                if (TVCallback.mContext != null) {
                                    TVCallback.mContext.sendBroadcast(intent);
                                }
                            } catch (Exception e) {
                                TvDebugLog.d(TVCallback.TAG, "RemoteException:" + e.getMessage());
                            }
                        }
                    }).start();
                } else if (i == 3) {
                    new Thread(new Runnable() { // from class: com.mediatek.twoworlds.tv.TVCallback.3
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                Intent intent = new Intent();
                                intent.addFlags(268435488);
                                intent.setAction(MtkTvIntentBase.MTK_INTENT_BGM_POWER_ON_INTERRUPT_STRING);
                                if (TVCallback.mContext != null) {
                                    TVCallback.mContext.sendBroadcast(intent);
                                }
                            } catch (Exception e) {
                                TvDebugLog.d(TVCallback.TAG, "RemoteException:" + e.getMessage());
                            }
                        }
                    }).start();
                } else if (i == 10) {
                    new Thread(new Runnable() { // from class: com.mediatek.twoworlds.tv.TVCallback.4
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                Intent intent = new Intent();
                                intent.addFlags(268435488);
                                intent.setAction("mtk.intent.rtctimer.set.bgm");
                                if (TVCallback.mContext != null) {
                                    long j = ((long) i2) * 1000;
                                    long jCurrentTimeMillis = System.currentTimeMillis();
                                    AlarmManager alarmManager = (AlarmManager) TVCallback.mContext.getSystemService("alarm");
                                    if (TVCallback.pendItforBGM != null) {
                                        alarmManager.cancel(TVCallback.pendItforBGM);
                                        PendingIntent unused = TVCallback.pendItforBGM = null;
                                    }
                                    if (TVCallback.pendItforNORMAL_ON != null) {
                                        alarmManager.cancel(TVCallback.pendItforNORMAL_ON);
                                        PendingIntent unused2 = TVCallback.pendItforNORMAL_ON = null;
                                    }
                                    PendingIntent broadcast = PendingIntent.getBroadcast(TVCallback.mContext, 0, intent, 134217728);
                                    alarmManager.setExact(0, j, broadcast);
                                    PendingIntent unused3 = TVCallback.pendItforBGM = broadcast;
                                    TvDebugLog.d(TVCallback.TAG, "Set AlarmManager for Linux RTC = " + j + ", Current system RTC = " + jCurrentTimeMillis);
                                }
                            } catch (Exception e) {
                                TvDebugLog.d(TVCallback.TAG, "RemoteException:" + e.getMessage());
                            }
                        }
                    }).start();
                } else if (i == 11) {
                    new Thread(new Runnable() { // from class: com.mediatek.twoworlds.tv.TVCallback.5
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                Intent intent = new Intent();
                                intent.addFlags(268435488);
                                intent.setAction("mtk.intent.rtctimer.set.normal_wakeup");
                                if (TVCallback.mContext != null) {
                                    long j = ((long) i2) * 1000;
                                    long jCurrentTimeMillis = System.currentTimeMillis();
                                    AlarmManager alarmManager = (AlarmManager) TVCallback.mContext.getSystemService("alarm");
                                    if (TVCallback.pendItforBGM != null) {
                                        alarmManager.cancel(TVCallback.pendItforBGM);
                                        PendingIntent unused = TVCallback.pendItforBGM = null;
                                    }
                                    if (TVCallback.pendItforNORMAL_ON != null) {
                                        alarmManager.cancel(TVCallback.pendItforNORMAL_ON);
                                        PendingIntent unused2 = TVCallback.pendItforNORMAL_ON = null;
                                    }
                                    PendingIntent broadcast = PendingIntent.getBroadcast(TVCallback.mContext, 0, intent, 134217728);
                                    alarmManager.setExact(0, j, broadcast);
                                    PendingIntent unused3 = TVCallback.pendItforNORMAL_ON = broadcast;
                                    TvDebugLog.d(TVCallback.TAG, "Set AlarmManager for Linux RTC = " + j + ", Current system RTC = " + jCurrentTimeMillis);
                                }
                            } catch (Exception e) {
                                TvDebugLog.d(TVCallback.TAG, "RemoteException:" + e.getMessage());
                            }
                        }
                    }).start();
                } else if (i == 12) {
                    new Thread(new Runnable() { // from class: com.mediatek.twoworlds.tv.TVCallback.6
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                if (TVCallback.mContext != null) {
                                    AlarmManager alarmManager = (AlarmManager) TVCallback.mContext.getSystemService("alarm");
                                    if (TVCallback.pendItforBGM != null) {
                                        alarmManager.cancel(TVCallback.pendItforBGM);
                                        PendingIntent unused = TVCallback.pendItforBGM = null;
                                    }
                                    if (TVCallback.pendItforNORMAL_ON != null) {
                                        alarmManager.cancel(TVCallback.pendItforNORMAL_ON);
                                        PendingIntent unused2 = TVCallback.pendItforNORMAL_ON = null;
                                    }
                                    TvDebugLog.d(TVCallback.TAG, "Disable AlarmManager for Linux RTC.");
                                }
                                Intent intent = new Intent();
                                intent.addFlags(4);
                                intent.setAction(MtkTvIntentNotifyCodeBase.MTK_INTENT_STRING);
                                intent.putExtra("code", 2);
                                if (TVCallback.mContext != null) {
                                    TVCallback.mContext.sendBroadcast(intent);
                                }
                            } catch (Exception e) {
                                TvDebugLog.d(TVCallback.TAG, "RemoteException:" + e.getMessage());
                            }
                        }
                    }).start();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return 0;
    }

    protected static int DO_notifyScanNotification(int i, int i2, int i3, int i4) {
        int iNotifyScanNotification;
        TvDebugLog.d(TAG, "DO_notifyScanNotification");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyScanNotification = 0;
            for (int i5 = 0; i5 < iBeginBroadcast; i5++) {
                try {
                    iNotifyScanNotification = clientCallbacks.getBroadcastItem(i5).notifyScanNotification(i, i2, i3, i4);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyScanNotification;
    }

    protected static int DO_notifySimulated3dAutoTurnOff() {
        int iNotifySimulated3dAutoTurnOff;
        TvDebugLog.d(TAG, "DO_notifySimulated3dAutoTurnOff");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifySimulated3dAutoTurnOff = 0;
            for (int i = 0; i < iBeginBroadcast; i++) {
                try {
                    iNotifySimulated3dAutoTurnOff = clientCallbacks.getBroadcastItem(i).notifySimulated3dAutoTurnOff();
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifySimulated3dAutoTurnOff;
    }

    protected static int DO_notifySleepTimerChange(int i, int i2) {
        TvDebugLog.d(TAG, "DO_notifySleepTimerChange type=" + i + ", remaining time =" + i2);
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            for (int i3 = 0; i3 < iBeginBroadcast; i3++) {
                try {
                    clientCallbacks.getBroadcastItem(i3).notifySleepTimerChange(i, i2);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return 0;
    }

    protected static int DO_notifyTimeSyncSourceChange(int i) {
        TvDebugLog.d(TAG, "DO_notifyTimeSyncSourceChange source=" + i);
        new Thread(new Runnable() { // from class: com.mediatek.twoworlds.tv.TVCallback.7
            @Override // java.lang.Runnable
            public void run() {
                Intent intent = new Intent();
                intent.setAction(MtkTvIntentBase.MTK_INTENT_TIME_SYNC_SOURCE_CHANGED);
                if (TVCallback.mContext != null) {
                    TVCallback.mContext.sendBroadcast(intent);
                }
            }
        }).start();
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void syncTimeFromLinuxToAndroid() {
        Context context;
        MtkTvTimeBase mtkTvTimeBase = new MtkTvTimeBase();
        if (mtkTvTimeBase.getTimeSyncSource() != 1 || (context = mContext) == null) {
            TvDebugLog.d(TAG, "{DT}sync src not come from TS, DO NOT sync Time from linux to android ");
            return;
        }
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        long broadcastTimeInUtcSeconds = mtkTvTimeBase.getBroadcastTimeInUtcSeconds();
        TvDebugLog.d(TAG, "{DT} set time " + broadcastTimeInUtcSeconds + " to alarm manager");
        try {
            alarmManager.setTime(broadcastTimeInUtcSeconds * 1000);
        } catch (Exception e) {
            TvDebugLog.d(TAG, "{DT}sync Time from linux to android fail", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void syncTimeZoneFromLinuxToAndroid() {
        Context context;
        String str;
        MtkTvTimeBase mtkTvTimeBase = new MtkTvTimeBase();
        if (mtkTvTimeBase.getTimeSyncSource() != 1 || (context = mContext) == null) {
            TvDebugLog.d(TAG, "{DT}sync src not come from TS, DO NOT sync TZ from linux to android ");
            return;
        }
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        TimeZone timeZone = TimeZone.getDefault();
        int timeZone2 = ((int) mtkTvTimeBase.getTimeZone()) + mtkTvTimeBase.getDst();
        if (timeZone2 == timeZone.getOffset(System.currentTimeMillis()) / 1000) {
            TvDebugLog.d(TAG, "{DT} no need to sync time zone from Linux to Android, totalOffset = " + timeZone2);
            return;
        }
        int i = timeZone2 / MtkTvTimeFormatBase.SECONDS_PER_HOUR;
        if (timeZone2 > 0) {
            str = "Etc/GMT" + MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING;
        } else {
            str = "Etc/GMT+";
        }
        String str2 = str + String.format("%d", Integer.valueOf(i));
        TvDebugLog.d(TAG, "{DT} need sync time zone to Android ,timezone = " + str2);
        try {
            alarmManager.setTimeZone(str2);
        } catch (Exception e) {
            TvDebugLog.d(TAG, "{DT}sync timezone from linux to android fail", e);
        }
    }

    protected static int DO_notifyDtEvent(int i, final int i2) {
        boolean z;
        boolean z2;
        TvDebugLog.d(TAG, "DO_notifyDtEvent cond = " + i);
        final int iIntValue = VendorProperties.mtk_system_timesync_existed().orElse(0).intValue();
        boolean z3 = true;
        if (i <= 4) {
            if (i2 == mLastDtValue && i != 4) {
                z3 = false;
            }
            mLastDtValue = i2;
            z = false;
            z2 = false;
        } else if (i != 3 && i != 4 && i != 5 && i != 8 && i != 9 && i != 10 && i != 13) {
            z = false;
            z3 = false;
            z2 = false;
        } else if (i != 13) {
            z2 = false;
            z = true;
            z3 = false;
        } else {
            z = false;
            z2 = true;
            z3 = false;
        }
        if (iIntValue == 0) {
            TvDebugLog.d(TAG, "{DT} ro.vendor.mtk.system.timesync.existed is false, disable sync time/tz to android\r\n");
            z = false;
        } else {
            z2 = false;
        }
        if (z3) {
            new Thread(new Runnable() { // from class: com.mediatek.twoworlds.tv.TVCallback.8
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Intent intent = new Intent();
                        intent.setAction(MtkTvIntentBase.MTK_INTENT_TIME_TS_UTC_UPDATE);
                        if (TVCallback.mContext != null) {
                            TVCallback.mContext.sendBroadcast(intent);
                        }
                    } catch (Exception e) {
                        TvDebugLog.d(TVCallback.TAG, "{DT} sendBroadcastAsUser failed.", e);
                    }
                    if (iIntValue == 1) {
                        TVCallback.syncTimeFromLinuxToAndroid();
                        TVCallback.syncTimeZoneFromLinuxToAndroid();
                    }
                }
            }).start();
        }
        if (z) {
            new Thread(new Runnable() { // from class: com.mediatek.twoworlds.tv.TVCallback.9
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Intent intent = new Intent();
                        intent.setAction(MtkTvIntentBase.MTK_INTENT_TIME_ZONE_CHANGED);
                        intent.putExtra("TimeZone", i2);
                        if (TVCallback.mContext != null) {
                            TVCallback.mContext.sendBroadcast(intent);
                        }
                        if (iIntValue == 1) {
                            TVCallback.syncTimeZoneFromLinuxToAndroid();
                        }
                    } catch (Exception e) {
                        TvDebugLog.d(TVCallback.TAG, "{DT} sendBroadcastAsUser failed.", e);
                    }
                }
            }).start();
        }
        if (z2) {
            new Thread(new Runnable() { // from class: com.mediatek.twoworlds.tv.TVCallback.10
                @Override // java.lang.Runnable
                public void run() {
                    Intent intent = new Intent();
                    intent.setAction(MtkTvIntentBase.MTK_INTENT_TIME_SYS_BRDCST_DIFF_CHANGED);
                    intent.putExtra("TimeSysBrdcstDiff", i2);
                    if (TVCallback.mContext != null) {
                        TVCallback.mContext.sendBroadcast(intent);
                    }
                }
            }).start();
        }
        return 0;
    }

    protected static int DO_notifyOADMessage(int i, String str, int i2, boolean z, int i3) {
        TvDebugLog.d(TAG, "DO_notifyOADMessage messageType=" + i + ", dataString =" + str + ", progress =" + i2 + ", autoDld =" + z);
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            for (int i4 = 0; i4 < iBeginBroadcast; i4++) {
                try {
                    clientCallbacks.getBroadcastItem(i4).notifyOADMessage(i, str, i2, z, i3);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return 0;
    }

    protected static int DO_notifyMHEG5Message(int i, int i2, int i3, int i4) {
        TvDebugLog.d(TAG, "(From JNI)notifyMHEG5Message=" + i);
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            for (int i5 = 0; i5 < iBeginBroadcast; i5++) {
                try {
                    clientCallbacks.getBroadcastItem(i5).notifyMHEG5Message(i, i2, i3, i4);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return 0;
    }

    protected static int DO_notifyMHEG5LanuchHbbtv(String str, int i) {
        TvDebugLog.d(TAG, "(From JNI)DO_notifyMHEG5LanuchHbbtv.");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            for (int i2 = 0; i2 < iBeginBroadcast; i2++) {
                try {
                    clientCallbacks.getBroadcastItem(i2).notifyMHEG5LanuchHbbtv(str, i);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return 0;
    }

    protected static int DO_notifyMHEG5MimeTypeSupport(String str, boolean[] zArr, int i) {
        TvDebugLog.d(TAG, "(From JNI)DO_notifyMHEG5MimeTypeSupport.");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            for (int i2 = 0; i2 < iBeginBroadcast; i2++) {
                try {
                    clientCallbacks.getBroadcastItem(i2).notifyMHEG5MimeTypeSupport(str, zArr, i);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return 0;
    }

    protected static int DO_notifyEWSPAMessage(int i) {
        TvDebugLog.d(TAG, "(From JNI)notifyEWSPAMessage=" + i);
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            for (int i2 = 0; i2 < iBeginBroadcast; i2++) {
                try {
                    clientCallbacks.getBroadcastItem(i2).notifyEWSPAMessage(i);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return 0;
    }

    protected static int DO_notifyMHPMessage(int i, int i2, int i3, int i4) {
        TvDebugLog.d(TAG, "(From JNI)notifyMHPMessage=" + i);
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            for (int i5 = 0; i5 < iBeginBroadcast; i5++) {
                try {
                    clientCallbacks.getBroadcastItem(i5).notifyMHPMessage(i, i2, i3, i4);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return 0;
    }

    protected static int DO_notifyBannerMessage(int i, int i2, int i3, int i4) {
        int iNotifyBannerMessage;
        TvDebugLog.d(TAG, "DO_notifyBannerMessage msgType=" + i + ", msgName=" + i2);
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyBannerMessage = 0;
            for (int i5 = 0; i5 < iBeginBroadcast; i5++) {
                try {
                    iNotifyBannerMessage = clientCallbacks.getBroadcastItem(i5).notifyBannerMessage(i, i2, i3, i4);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyBannerMessage;
    }

    protected static int DO_notifyEASMessage(int i, int i2, int i3, int i4) {
        int iNotifyEASMessage;
        TvDebugLog.d(TAG, "DO_notifyEASMessage type=" + i + ", argv1 =" + i2);
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyEASMessage = 0;
            for (int i5 = 0; i5 < iBeginBroadcast; i5++) {
                try {
                    iNotifyEASMessage = clientCallbacks.getBroadcastItem(i5).notifyEASMessage(i, i2, i3, i4);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyEASMessage;
    }

    protected static int DO_notifyRecordCallback(int i, int i2, int i3) {
        int iNotifyRecordNotification;
        TvDebugLog.d(TAG, "DO_notifyRecordCallback");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyRecordNotification = 0;
            for (int i4 = 0; i4 < iBeginBroadcast; i4++) {
                try {
                    iNotifyRecordNotification = clientCallbacks.getBroadcastItem(i4).notifyRecordNotification(i, i2, i3);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyRecordNotification;
    }

    protected static int DO_notifyTimeshiftCallback(int i, long j) {
        int iNotifyTimeshiftNotification;
        TvDebugLog.d(TAG, "DO_notifyTimeshiftCallback");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyTimeshiftNotification = 0;
            for (int i2 = 0; i2 < iBeginBroadcast; i2++) {
                try {
                    iNotifyTimeshiftNotification = clientCallbacks.getBroadcastItem(i2).notifyTimeshiftNotification(i, j);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyTimeshiftNotification;
    }

    protected static int DO_notifyTimeshiftRecordStatus(int i, long j) {
        int iNotifyTimeshiftRecordStatus;
        TvDebugLog.d(TAG, "DO_notifyTimeshiftRecordStatus");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyTimeshiftRecordStatus = 0;
            for (int i2 = 0; i2 < iBeginBroadcast; i2++) {
                try {
                    iNotifyTimeshiftRecordStatus = clientCallbacks.getBroadcastItem(i2).notifyTimeshiftRecordStatus(i, j);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyTimeshiftRecordStatus;
    }

    protected static int DO_notifyTimeshiftNoDiskFile(long j) {
        int iNotifyTimeshiftNoDiskFile;
        TvDebugLog.d(TAG, "DO_notifyTimeshiftNoDiskFile");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyTimeshiftNoDiskFile = 0;
            for (int i = 0; i < iBeginBroadcast; i++) {
                try {
                    iNotifyTimeshiftNoDiskFile = clientCallbacks.getBroadcastItem(i).notifyTimeshiftNoDiskFile(j);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyTimeshiftNoDiskFile;
    }

    protected static int DO_notifyTimeshiftSpeedUpdate(int i) {
        int iNotifyTimeshiftSpeedUpdate;
        TvDebugLog.d(TAG, "DO_notifyTimeshiftSpeedUpdate");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyTimeshiftSpeedUpdate = 0;
            for (int i2 = 0; i2 < iBeginBroadcast; i2++) {
                try {
                    iNotifyTimeshiftSpeedUpdate = clientCallbacks.getBroadcastItem(i2).notifyTimeshiftSpeedUpdate(i);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyTimeshiftSpeedUpdate;
    }

    protected static int DO_notifyTimeshiftPlaybackStatusUpdate(int i) {
        int iNotifyTimeshiftPlaybackStatusUpdate;
        TvDebugLog.d(TAG, "DO_notifyTimeshiftPlaybackStatusUpdate");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyTimeshiftPlaybackStatusUpdate = 0;
            for (int i2 = 0; i2 < iBeginBroadcast; i2++) {
                try {
                    iNotifyTimeshiftPlaybackStatusUpdate = clientCallbacks.getBroadcastItem(i2).notifyTimeshiftPlaybackStatusUpdate(i);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyTimeshiftPlaybackStatusUpdate;
    }

    protected static int DO_notifyTimeshiftStorageRemoved(int i) {
        int iNotifyTimeshiftStorageRemoved;
        TvDebugLog.d(TAG, "DO_notifyTimeshiftStorageRemoved");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyTimeshiftStorageRemoved = 0;
            for (int i2 = 0; i2 < iBeginBroadcast; i2++) {
                try {
                    iNotifyTimeshiftStorageRemoved = clientCallbacks.getBroadcastItem(i2).notifyTimeshiftStorageRemoved();
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyTimeshiftStorageRemoved;
    }

    protected static int DO_notifyCIMessage(int i, int i2, int i3, int i4, MtkTvCIMMIMenuBase mtkTvCIMMIMenuBase, MtkTvCIMMIEnqBase mtkTvCIMMIEnqBase) {
        TvDebugLog.d(TAG, "DO_notifyCIMessage messageType=" + i2 + ", slid_id =" + i);
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            for (int i5 = 0; i5 < iBeginBroadcast; i5++) {
                try {
                    clientCallbacks.getBroadcastItem(i5).notifyCIMessage(i, i2, i3, i4, mtkTvCIMMIMenuBase, mtkTvCIMMIEnqBase);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return 0;
    }

    protected static int DO_notifyAVModeMessage(int i, int i2, int i3, int i4) {
        int iNotifyAVModeMessage;
        TvDebugLog.d(TAG, "DO_notifyAVModeMessage");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyAVModeMessage = 0;
            for (int i5 = 0; i5 < iBeginBroadcast; i5++) {
                try {
                    iNotifyAVModeMessage = clientCallbacks.getBroadcastItem(i5).notifyAVModeMessage(i, i2, i3, i4);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyAVModeMessage;
    }

    protected static int DO_notifyOpenVCHIPMessage(int i, int i2, int i3, int i4) {
        int iNotifyOpenVCHIPMessage;
        TvDebugLog.d(TAG, "notifyOpenVCHIPMessage type=" + i + ", argv1 =" + i2);
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyOpenVCHIPMessage = 0;
            for (int i5 = 0; i5 < iBeginBroadcast; i5++) {
                try {
                    iNotifyOpenVCHIPMessage = clientCallbacks.getBroadcastItem(i5).notifyOpenVCHIPMessage(i, i2, i3, i4);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyOpenVCHIPMessage;
    }

    protected static int DO_notifyATSCEventMessage(int i, int i2, int i3, long j) {
        int iNotifyATSCEventMessage;
        TvDebugLog.d(TAG, "DO_notifyATSCEventMessage");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyATSCEventMessage = 0;
            for (int i4 = 0; i4 < iBeginBroadcast; i4++) {
                try {
                    iNotifyATSCEventMessage = clientCallbacks.getBroadcastItem(i4).notifyATSCEventMessage(i, i2, i3, j);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyATSCEventMessage;
    }

    protected static int DO_notifyPWDDialogMessage(int i, int i2, int i3, int i4) {
        int iNotifyPWDDialogMessage;
        TvDebugLog.d(TAG, "DO_notifyPWDDialogMessage");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyPWDDialogMessage = 0;
            for (int i5 = 0; i5 < iBeginBroadcast; i5++) {
                try {
                    iNotifyPWDDialogMessage = clientCallbacks.getBroadcastItem(i5).notifyPWDDialogMessage(i, i2, i3, i4);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyPWDDialogMessage;
    }

    protected static int DO_notifyScreenSaverMessage(int i, int i2, int i3, int i4) {
        TvDebugLog.d(TAG, "(From JNI)notifyScreenSaverMessage=" + i);
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            for (int i5 = 0; i5 < iBeginBroadcast; i5++) {
                try {
                    clientCallbacks.getBroadcastItem(i5).notifyScreenSaverMessage(i, i2, i3, i4);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return 0;
    }

    protected static int DO_notifyWarningMessage(int i, int i2, String str, int i3, int i4) {
        TvDebugLog.d(TAG, "(From JNI)notifyWarningMessage=" + i);
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            for (int i5 = 0; i5 < iBeginBroadcast; i5++) {
                try {
                    clientCallbacks.getBroadcastItem(i5).notifyWarningMessage(i, i2, str, i3, i4);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return 0;
    }

    protected static int DO_notifyTeletextMessage(int i, int i2, int i3, int i4) {
        TvDebugLog.d(TAG, "(From JNI)notifyTeletextMessage messageID=" + i);
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            for (int i5 = 0; i5 < iBeginBroadcast; i5++) {
                try {
                    clientCallbacks.getBroadcastItem(i5).notifyTeletextMessage(i, i2, i3, i4);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return 0;
    }

    protected static int DO_notifyGingaMessage(int i, String str, String str2) {
        TvDebugLog.d(TAG, "(From JNI)notifyGingaMessage \n");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            for (int i2 = 0; i2 < iBeginBroadcast; i2++) {
                try {
                    clientCallbacks.getBroadcastItem(i2).notifyGingaMessage(i, str, str2);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return 0;
    }

    protected static int DO_notifyGingaVolumeChanged(int i, int i2) {
        TvDebugLog.d(TAG, "(From JNI)DO_notifyGingaVolumeChanged \n");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            for (int i3 = 0; i3 < iBeginBroadcast; i3++) {
                try {
                    clientCallbacks.getBroadcastItem(i3).notifyGingaVolumeChanged(i, i2);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return 0;
    }

    protected static int DO_notifyGingaUpdateMessage(int i, String str, int i2) {
        TvDebugLog.d(TAG, "(From JNI)DO_notifyGingaUpdateMessage \n");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            for (int i3 = 0; i3 < iBeginBroadcast; i3++) {
                try {
                    clientCallbacks.getBroadcastItem(i3).notifyGingaUpdateMessage(i, str, i2);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return 0;
    }

    protected static int DO_notifyHBBTVMessage(int i, int[] iArr, int i2) {
        TvDebugLog.d(TAG, "(From JNI)DO_notifyHBBTVMessage callbackType=" + i);
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            for (int i3 = 0; i3 < iBeginBroadcast; i3++) {
                try {
                    clientCallbacks.getBroadcastItem(i3).notifyHBBTVMessage(i, iArr, i2);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return 0;
    }

    protected static int Do_notifyFeatureMessage(int i, int i2, int i3, int i4) {
        int iNotifyFeatureMessage;
        TvDebugLog.d(TAG, "Do_notifyFeatureMessage");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyFeatureMessage = 0;
            for (int i5 = 0; i5 < iBeginBroadcast; i5++) {
                try {
                    iNotifyFeatureMessage = clientCallbacks.getBroadcastItem(i5).notifyFeatureMessage(i, i2, i3, i4);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyFeatureMessage;
    }

    protected static int Do_notifyBroadcastMessage(int i, int i2, int i3, int i4) {
        int iNotifyBroadcastMessage;
        TvDebugLog.d(TAG, "Do_notifyBroadcastMessage");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyBroadcastMessage = 0;
            for (int i5 = 0; i5 < iBeginBroadcast; i5++) {
                try {
                    iNotifyBroadcastMessage = clientCallbacks.getBroadcastItem(i5).notifyBroadcastMessage(i, i2, i3, i4);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyBroadcastMessage;
    }

    protected static int DO_notifyPipPopMessage(int i, int i2, int i3, int i4) {
        int iNotifyPipPopMessage;
        TvDebugLog.d(TAG, "DO_notifyPipPopMessage");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyPipPopMessage = 0;
            for (int i5 = 0; i5 < iBeginBroadcast; i5++) {
                try {
                    iNotifyPipPopMessage = clientCallbacks.getBroadcastItem(i5).notifyPipPopMessage(i, i2, i3, i4);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyPipPopMessage;
    }

    protected static int DO_notifyUpgradeMessage(int i, int i2, int i3, int i4) {
        TvDebugLog.d(TAG, "DO_notifyUpgradeMessage messageType=" + i + ", arg1 =" + i2 + ", arg2 =" + i3);
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            for (int i5 = 0; i5 < iBeginBroadcast; i5++) {
                try {
                    clientCallbacks.getBroadcastItem(i5).notifyUpgradeMessage(i, i2, i3, i4);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return 0;
    }

    protected static int DO_notifyNoUsedkeyMessage(int i, int i2, int i3, int i4) {
        TvDebugLog.d(TAG, "DO_notifyNoUsedkeyMessage messageType=" + i);
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            for (int i5 = 0; i5 < iBeginBroadcast; i5++) {
                try {
                    clientCallbacks.getBroadcastItem(i5).notifyNoUsedkeyMessage(i, i2, i3, i4);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return 0;
    }

    protected static int DO_notifyCDTLogoMessage(int i, int i2, int i3, int i4) {
        TvDebugLog.d(TAG, "DO_notifyCDTChLogoMessage messageType=" + i);
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            for (int i5 = 0; i5 < iBeginBroadcast; i5++) {
                try {
                    clientCallbacks.getBroadcastItem(i5).notifyCDTLogoMessage(i, i2, i3, i4);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return 0;
    }

    protected static int DO_notifyGpioStatus(int i, int i2) {
        TvDebugLog.d(TAG, "DO_notifyGpioStatus gpioId=" + i + ", status =" + i2);
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            for (int i3 = 0; i3 < iBeginBroadcast; i3++) {
                try {
                    TvDebugLog.d(TAG, "callback : " + clientCallbacks.getBroadcastItem(i3));
                    clientCallbacks.getBroadcastItem(i3).notifyGpioStatus(i, i2);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return 0;
    }

    protected static int DO_notifyUARTSerialPortCallback(int i, int i2, int i3, byte[] bArr) {
        TvDebugLog.d(TAG, "DO_notifyUARTSerialPortCallback uartSerialID=" + i + ",ioNotifyCond =" + i2 + ",eventCode=" + i3 + ",data=" + bArr);
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            for (int i4 = 0; i4 < iBeginBroadcast; i4++) {
                try {
                    TvDebugLog.d(TAG, "callback : " + clientCallbacks.getBroadcastItem(i4));
                    clientCallbacks.getBroadcastItem(i4).notifyUARTSerialPortCallback(i, i2, i3, bArr);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return 0;
    }

    protected static int DO_notifySubtitleMsg(int i, int i2, int i3, int i4) {
        int iNotifySubtitleMsg;
        TvDebugLog.d(TAG, "DO_notifySubtitleMsg");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifySubtitleMsg = 0;
            for (int i5 = 0; i5 < iBeginBroadcast; i5++) {
                try {
                    iNotifySubtitleMsg = clientCallbacks.getBroadcastItem(i5).notifySubtitleMsg(i, i2, i3, i4);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifySubtitleMsg;
    }

    protected static int DO_notifyInputSignalChanged(int i, boolean z) {
        int iNotifyInputSignalChanged;
        TvDebugLog.d(TAG, "DO_notifyInputSignalChanged");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyInputSignalChanged = 0;
            for (int i2 = 0; i2 < iBeginBroadcast; i2++) {
                try {
                    iNotifyInputSignalChanged = clientCallbacks.getBroadcastItem(i2).notifyInputSignalChanged(i, z);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyInputSignalChanged;
    }

    protected static int DO_notifyVdpMuteAllFinished(int i) {
        int iNotifyVdpMuteAllFinished;
        TvDebugLog.d(TAG, "DO_notifyVdpMuteAllFinished");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyVdpMuteAllFinished = 0;
            for (int i2 = 0; i2 < iBeginBroadcast; i2++) {
                try {
                    iNotifyVdpMuteAllFinished = clientCallbacks.getBroadcastItem(i2).notifyVdpMuteAllFinished(i);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyVdpMuteAllFinished;
    }

    protected static int DO_queryNetworkType() {
        int type;
        TvDebugLog.d(TAG, "DO_queryNetworkType");
        if (mContext == null) {
            return -1;
        }
        synchronized (TVCallback.class) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) mContext.getSystemService("connectivity")).getActiveNetworkInfo();
            type = 0;
            if (activeNetworkInfo != null) {
                type = activeNetworkInfo.getType();
                TvDebugLog.d(TAG, "DO_queryNetworkType getType=" + type);
            } else {
                TvDebugLog.d(TAG, "DO_queryNetworkType getType failed0");
            }
        }
        return type;
    }

    protected static int DO_queryWifiState() {
        int iOrdinal;
        TvDebugLog.d(TAG, "DO_queryWifiState");
        if (mContext == null) {
            return -1;
        }
        synchronized (TVCallback.class) {
            NetworkInfo networkInfo = ((ConnectivityManager) mContext.getSystemService("connectivity")).getNetworkInfo(1);
            iOrdinal = 0;
            if (networkInfo != null) {
                iOrdinal = networkInfo.getState().ordinal();
                TvDebugLog.d(TAG, "DO_queryWifiState getState=" + iOrdinal);
            } else {
                TvDebugLog.d(TAG, "DO_queryWifiState getState failed0");
            }
        }
        return iOrdinal;
    }

    protected static int DO_queryEthernetState() {
        int iOrdinal;
        TvDebugLog.d(TAG, "DO_queryEthernetState");
        if (mContext == null) {
            return -1;
        }
        synchronized (TVCallback.class) {
            NetworkInfo networkInfo = ((ConnectivityManager) mContext.getSystemService("connectivity")).getNetworkInfo(9);
            iOrdinal = 0;
            if (networkInfo != null) {
                iOrdinal = networkInfo.getState().ordinal();
                TvDebugLog.d(TAG, "DO_queryEthernetState getState=" + iOrdinal);
            } else {
                TvDebugLog.d(TAG, "DO_queryEthernetState getState failed0");
            }
        }
        return iOrdinal;
    }

    public static int DO_notifyConfigValuechanged(final String str) {
        TvDebugLog.d(TAG, "DO_notifyConfigValuechanged cfgId=" + str);
        mThreadHandler.post(new Runnable() { // from class: com.mediatek.twoworlds.tv.TVCallback.11
            @Override // java.lang.Runnable
            public void run() {
                ArrayList arrayList;
                synchronized (MtkTvConfig.class) {
                    arrayList = (ArrayList) TVCallback.mCfgValChgCallBacks.get(str);
                }
                synchronized (TVCallback.class) {
                    if (arrayList == null) {
                        return;
                    }
                    int iBeginBroadcast = TVCallback.clientCallbacks.beginBroadcast();
                    for (int i = 0; i < iBeginBroadcast; i++) {
                        try {
                            if (arrayList.contains(TVCallback.clientCallbacks.getBroadcastItem(i))) {
                                ((MtkTvTVCallbackHandler) TVCallback.clientCallbacks.getBroadcastItem(i)).notifyConfigValuechanged(str);
                            }
                        } catch (Exception e) {
                            TvDebugLog.d(TVCallback.TAG, "RemoteException:" + e.getMessage());
                        }
                    }
                    TVCallback.clientCallbacks.finishBroadcast();
                }
            }
        });
        return 0;
    }

    protected static int DO_notifyBMLMessage(int i, int[] iArr, int i2) {
        TvDebugLog.d(TAG, "(From JNI)DO_notifyBMLMessage callbackType=" + i);
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            for (int i3 = 0; i3 < iBeginBroadcast; i3++) {
                try {
                    clientCallbacks.getBroadcastItem(i3).notifyBMLMessage(i, iArr, i2);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return 0;
    }

    /* JADX WARN: Code duplicated, block: B:26:0x004b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public static int DO_notifySerialExternalControlCallback(int i, byte[] bArr) throws RemoteException {
        int iBeginBroadcast;
        int i2;
        TvDebugLog.d(TAG, "DO_notifySerialExternalControlCallback action=" + i + ",data=" + bArr);
        synchronized (TVCallback.class) {
            if (i == -1610612736) {
                Intent intent = new Intent(INTENT_ACTION_SERIAL_CONTROL);
                intent.addFlags(268435456);
                if (mContext != null) {
                    mContext.sendBroadcast(intent);
                }
                TvDebugLog.d(TAG, "sent foreground broadcast com.jamdeo.intent.SERIAL_CONTROL");
                iBeginBroadcast = clientCallbacks.beginBroadcast();
                for (i2 = 0; i2 < iBeginBroadcast; i2++) {
                    try {
                        TvDebugLog.d(TAG, "callback : " + clientCallbacks.getBroadcastItem(i2));
                        clientCallbacks.getBroadcastItem(i2).notifySerialExternalControlCallback(i, bArr);
                    } catch (Exception e) {
                        TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                    }
                }
                clientCallbacks.finishBroadcast();
            } else {
                iBeginBroadcast = clientCallbacks.beginBroadcast();
                while (i2 < iBeginBroadcast) {
                    TvDebugLog.d(TAG, "callback : " + clientCallbacks.getBroadcastItem(i2));
                    clientCallbacks.getBroadcastItem(i2).notifySerialExternalControlCallback(i, bArr);
                }
                clientCallbacks.finishBroadcast();
            }
            throw th;
        }
        return 0;
    }

    protected static int DO_notifyDrmpKeyUpdateStatus(int i) {
        TvDebugLog.d(TAG, "(From JNI)DO_notifyDrmpKeyUpdateStatus result=" + i);
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            for (int i2 = 0; i2 < iBeginBroadcast; i2++) {
                try {
                    clientCallbacks.getBroadcastItem(i2).notifyDrmpKeyUpdateStatus(i);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return 0;
    }

    protected static int DO_notifyMdsMessage(String str, int i) {
        TvDebugLog.d(TAG, "(From JNI)DO_notifyMdsMessage. sMdsMsg = " + str);
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            for (int i2 = 0; i2 < iBeginBroadcast; i2++) {
                try {
                    clientCallbacks.getBroadcastItem(i2).notifyMdsMessage(str, i);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return 0;
    }

    protected static int DO_notifyVideoInfoMessage(int i, int i2, int i3, int i4, int i5, int i6, int i7) throws RemoteException {
        int iNotifyVideoInfoMessage;
        TvDebugLog.d(TAG, "DO_notifyVideoInfoMessage");
        synchronized (TVCallback.class) {
            int iBeginBroadcast = clientCallbacks.beginBroadcast();
            iNotifyVideoInfoMessage = 0;
            for (int i8 = 0; i8 < iBeginBroadcast; i8++) {
                try {
                    iNotifyVideoInfoMessage = clientCallbacks.getBroadcastItem(i8).notifyVideoInfoMessage(i, i2, i3, i4, i5, i6, i7);
                } catch (Exception e) {
                    TvDebugLog.d(TAG, "RemoteException:" + e.getMessage());
                }
            }
            clientCallbacks.finishBroadcast();
        }
        return iNotifyVideoInfoMessage;
    }

    private static class CallbackList<T> extends ArrayList<T> {
        public void finishBroadcast() {
        }

        private CallbackList() {
        }

        public int beginBroadcast() {
            return size();
        }

        T getBroadcastItem(int i) {
            return get(i);
        }
    }
}
