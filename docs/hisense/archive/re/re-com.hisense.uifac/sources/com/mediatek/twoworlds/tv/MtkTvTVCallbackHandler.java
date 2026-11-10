package com.mediatek.twoworlds.tv;

import android.os.RemoteException;
import com.mediatek.twoworlds.tv.model.MtkTvCIMMIEnqBase;
import com.mediatek.twoworlds.tv.model.MtkTvCIMMIMenuBase;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvTVCallbackHandler {
    private static final String TAG = "MtkTvTVCallbackHandler";

    public int notifyCCMessage(int i, int i2, int i3, int i4) throws RemoteException {
        return 0;
    }

    public int notifyCIMessage(int i, int i2, int i3, int i4, MtkTvCIMMIMenuBase mtkTvCIMMIMenuBase, MtkTvCIMMIEnqBase mtkTvCIMMIEnqBase) throws RemoteException {
        return 0;
    }

    public int notifyCecActiveSource(int i, int i2, boolean z) throws RemoteException {
        return 0;
    }

    public int notifyCecFrameInfo(int i, int i2, int i3, int[] iArr, int i4) throws RemoteException {
        return 0;
    }

    public int notifyInputSourceMessage(int i, int i2, int i3, int i4) throws RemoteException {
        return 0;
    }

    public int notifyMhlScratchpadData(int i, int i2, int i3, int[] iArr) throws RemoteException {
        return 0;
    }

    public int notifyNoUsedkeyMessage(int i, int i2, int i3, long j) throws RemoteException {
        return 0;
    }

    public int notifySpdInfoFrame(int i, int i2, int i3, int i4, int[] iArr, int[] iArr2, int i5) throws RemoteException {
        return 0;
    }

    public int notifySysAudMod(int i) throws RemoteException {
        return 0;
    }

    public int notifyUpgradeMessage(int i, int i2, int i3, int i4) throws RemoteException {
        return 0;
    }

    public MtkTvTVCallbackHandler() {
        synchronized (MtkTvTVCallbackHandler.class) {
            try {
                TVCallback.getTVCallback().registerCallback(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void removeListener() {
        try {
            TVCallback.getTVCallback().unregisterCallback(this);
            TVCallback.getTVCallback().removeListenerConfig(this, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int notifySvctxNotificationCode(int i) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifySvctxNotificationCode=" + i);
        return 0;
    }

    public int notifySvctxNotificationCodebyPath(int i, int i2) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifySvctxNotificationCodebyPath(path = " + i + ",code = " + i2 + ")");
        return 0;
    }

    public int notifyOtherMessage(int i, int i2, int i3) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyOtherMessage a1=" + i + " a2=" + i2 + " a3=" + i3);
        return 0;
    }

    public int notifyConfigMessage(int i, int i2) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyConfigMessage notifyId=" + i + " data=" + i2);
        return 0;
    }

    public int notifyChannelListUpdateMsg(int i, int i2, int i3) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyChannelListChanged: condition = " + i + ", reason = " + i2 + ", data = " + i3 + "\n");
        return 0;
    }

    public int notifyListModeUpdateMsg(int i, int i2) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyListModeUpdateMsg: oldMode = " + i + ", newMode = " + i2 + "\n");
        return 0;
    }

    public int notifyOclScanInfo(int i, int i2) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyOclScanInfo: msgId = " + i + ", channelNum = " + i2 + "\n");
        return 0;
    }

    public int notifyBisskeyUpdateMsg(int i, int i2, int i3) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyBisskeyUpdateMsg: condition = " + i + ", reason = " + i2 + ", data = " + i3 + "\n");
        return 0;
    }

    public int notifySatlListUpdateMsg(int i, int i2, int i3) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifySatlListUpdateMsg: condition = " + i + ", reason = " + i2 + ", data = " + i3 + "\n");
        return 0;
    }

    public int notifyTvproviderUpdateMsg(int i, int i2, int[] iArr, int[] iArr2) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyTvproviderUpdateMsg: svlid = " + i + ", count = " + i2 + "\n");
        return 0;
    }

    public int notifyConcernColumnUpdateMsg(int i, int i2, int[] iArr, int[] iArr2) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyConcernColumnUpdateMsg: svlid = " + i + ", count = " + i2 + "\n");
        return 0;
    }

    public int notifySvlIdUpdateMsg(int i, int i2, int i3) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifySvlIdUpdateMsg: svlid = " + i + "\n");
        return 0;
    }

    public int notifyTslIdUpdateMsg(int i, int i2, int i3) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyTslIdUpdateMsg: condition = " + i + ",reason = " + i2 + ",data = " + i3 + "\n");
        return 0;
    }

    public int notifyShowOSDMessage(int i, int i2) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyShowOSDMessage stringId=" + i + " msgType=" + i2);
        return 0;
    }

    public int notifyHideOSDMessage() throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyHideOSDMessage");
        return 0;
    }

    public int notifyNativeAppStatus(int i, boolean z) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyNativeAppStatus nativeAppId=" + i + " show=" + z);
        return 0;
    }

    public int notifyAmpVolCtrlMessage(int i, boolean z) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyAmpVolCtrlMessage volume=" + i + " isMute=" + z);
        return 0;
    }

    public int notifyCecNotificationCode(int i) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyCecNotificationCode volume=" + i);
        return 0;
    }

    public int notifyDeviceDiscovery() throws RemoteException {
        TvDebugLog.d(TAG, "Device Discovery Finish");
        return 0;
    }

    public int notifyUiMsDisplay(int i, boolean z) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyUiMsDisplay uiType=" + i + " show=" + z);
        return 0;
    }

    public int notifyEventNotification(int i, int i2, int i3, long j) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyEventNotification type=" + i + "argv1=" + i2 + "argv2=" + i3 + "argv3=" + j);
        return 0;
    }

    public int notifyRecordPBNotification(int i, int i2, int i3, long j) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyRecordPBNotification type=" + i + "argv1=" + i2 + "argv2=" + i3 + "argv3=" + j);
        return 0;
    }

    public int notifyScanNotification(int i, int i2, int i3, int i4) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyScanNotification msg_id=" + i + "scanProgress=" + i2 + "channelNum=" + i3 + "argv4=" + i4);
        return 0;
    }

    public int notifySimulated3dAutoTurnOff() throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifySimulated3dAutoTurnOff:");
        TvDebugLog.d(TAG, "====>Simulated 3D mode has been turned off because 1 hour has elapsed. Press the 3D key to re-start Simulated 3D mode.");
        return 0;
    }

    public int notifySleepTimerChange(int i, int i2) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifySimulated3dAutoTurnOff:");
        TvDebugLog.d(TAG, "notifySleepTimerChange type=" + i + ", remaining time =" + i2);
        return 0;
    }

    public int notifyPipPopMessage(int i, int i2, int i3, int i4) throws RemoteException {
        TvDebugLog.d(TAG, "notifyPipPopMessage updateType=" + i);
        return 0;
    }

    public int notifyAVModeMessage(int i, int i2, int i3, int i4) throws RemoteException {
        TvDebugLog.d(TAG, "notifyAVModeMessage updateType=" + i);
        return 0;
    }

    public int notifyGingaMessage(int i, String str, String str2) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyGingaMessage updateType=" + i + " app_id=" + str + " sAppName=" + str2);
        return 0;
    }

    public int notifyGingaUpdateMessage(int i, String str, int i2) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyGingaUpdateMessage updateType=" + i + "sJsonData =" + str + "datalen =" + i2);
        return 0;
    }

    public int notifyGingaVolumeChanged(int i, int i2) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyGingaVolumeChanged updateType=" + i + " level=" + i2);
        return 0;
    }

    public int notifyMHEG5Message(int i, int i2, int i3, int i4) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyMHEG5Message=" + i);
        return 0;
    }

    public int notifyMHEG5LanuchHbbtv(String str, int i) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyMHEG5LanuchHbbtv.");
        return 0;
    }

    public int notifyMHEG5MimeTypeSupport(String str, boolean[] zArr, int i) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyMHEG5MimeTypeSupport.");
        return 0;
    }

    public int notifyEWSPAMessage(int i) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyEWSPAMessage=" + i);
        return 0;
    }

    public int notifyMHPMessage(int i, int i2, int i3, int i4) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyMHPMessage=" + i);
        return 0;
    }

    public int notifyOADMessage(int i, String str, int i2, boolean z, int i3) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyOADMessage messageType=" + i + ", scheduleInfo =" + str + ", progress =" + i2 + ", autoDld =" + z);
        return 0;
    }

    public int notifyHBBTVMessage(int i, int[] iArr, int i2) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyHBBTVMessage=" + i);
        return 0;
    }

    public int notifyWarningMessage(int i, int i2, String str, int i3, int i4) throws RemoteException {
        TvDebugLog.d(TAG, "notifyWarningMessage msg_id=" + i);
        return 0;
    }

    public int notifyEASMessage(int i, int i2, int i3, int i4) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyEASMessage:");
        TvDebugLog.d(TAG, "notifyEASMessage updateType=" + i + ", channel_change =" + i2);
        return 0;
    }

    public int notifyTeletextMessage(int i, int i2, int i3, int i4) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyTeletextMessage messageID=" + i);
        return 0;
    }

    public int notifyFeatureMessage(int i, int i2, int i3, int i4) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyFeatureMessage type=" + i + "argv1=" + i2);
        return 0;
    }

    public int notifyBroadcastMessage(int i, int i2, int i3, int i4) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyBroadcastMessage type=" + i + "argv1=" + i2);
        return 0;
    }

    public int notifyBannerMessage(int i, int i2, int i3, int i4) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyBannerMessage msgType=" + i + ", msgName=" + i2);
        return 0;
    }

    public int notifyPWDDialogMessage(int i, int i2, int i3, int i4) throws RemoteException {
        TvDebugLog.d(TAG, "notifyPWDDialogMessage updateType=" + i);
        return 0;
    }

    public int notifyScreenSaverMessage(int i, int i2, int i3, int i4) throws RemoteException {
        TvDebugLog.d(TAG, "notifyScreenSaverMessage msg_id=" + i);
        return 0;
    }

    public int notifyATSCEventMessage(int i, int i2, int i3, long j) throws RemoteException {
        TvDebugLog.d(TAG, "notifyATSCEventMessage updateType=" + i + ",arg1=" + i2 + ",arg2=" + i3 + ",arg3=" + j);
        return 0;
    }

    public int notifyOpenVCHIPMessage(int i, int i2, int i3, long j) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyOpenVCHIPMessage:");
        TvDebugLog.d(TAG, "notifyOpenVCHIPMessage updateType=" + i);
        return 0;
    }

    public int notifyRecordNotification(int i, int i2, int i3) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyRecordNotification type=" + i + "argv1=" + i2 + "argv2=" + i3);
        return 0;
    }

    public int notifyTimeshiftNotification(int i, long j) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyTimeshiftNotification type=" + i + "argv1=" + j);
        return 0;
    }

    public int notifyTimeshiftRecordStatus(int i, long j) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyTimeshiftRecordStatus status=" + i + "argv1=" + j);
        return 0;
    }

    public int notifyTimeshiftNoDiskFile(long j) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyTimeshiftNoDiskFile argv1=" + j);
        return 0;
    }

    public int notifyTimeshiftSpeedUpdate(int i) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyTimeshiftSpeedUpdate speed=" + i);
        return 0;
    }

    public int notifyTimeshiftPlaybackStatusUpdate(int i) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyTimeshiftPlaybackStatusUpdate status=" + i);
        return 0;
    }

    public int notifyTimeshiftStorageRemoved() throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyTimeshiftStorageRemoved");
        return 0;
    }

    public int notifyCDTLogoMessage(int i, int i2, int i3, int i4) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyCDTLogoMessage type=" + i + "argv1=" + i2);
        return 0;
    }

    public int notifyGpioStatus(int i, int i2) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyGpioStatus gpioId=" + i + ", status =" + i2);
        return 0;
    }

    public int notifyUARTSerialPortCallback(int i, int i2, int i3, byte[] bArr) {
        TvDebugLog.d(TAG, "(Default Handler) notifyUARTSerialPortCallback uartSerialID=" + i + ", ioNotifyCond=" + i2 + ", eventCode=" + i3 + ",data=" + bArr);
        return 0;
    }

    public int notifySubtitleMsg(int i, int i2, int i3, int i4) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifySubtitleMsg msg_id=" + i + "argv1=" + i2 + "argv2=" + i3 + "argv3=" + i4);
        return 0;
    }

    public int notifyInputSignalChanged(int i, boolean z) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyInputSignalChanged inputId=" + i + "hasSignal=" + z);
        return 0;
    }

    public int notifyVdpMuteAllFinished(int i) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyVdpMuteAllFinished vdpId=" + i);
        return 0;
    }

    public final int addListenerConfig(String str) {
        try {
            TVCallback.getTVCallback().addListenerConfig(this, str);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public final int removeListenerConfig(String str) {
        TvDebugLog.d(TAG, "removeListenerConfig: cfgId = " + str);
        try {
            TVCallback.getTVCallback().removeListenerConfig(this, str);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int notifyConfigValuechanged(String str) throws Exception {
        TvDebugLog.d(TAG, "(Default Handler) notifyConfigValuechanged cfgId=" + str);
        return 0;
    }

    public int notifyBMLMessage(int i, int[] iArr, int i2) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyBMLMessage=" + i);
        return 0;
    }

    public int notifySerialExternalControlCallback(int i, byte[] bArr) {
        TvDebugLog.d(TAG, "(Default Handler) notifySerialExternalControlCallback action=" + i + ",data=" + bArr);
        return 0;
    }

    public int notifyMdsMessage(String str, int i) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyMdsMessage. sMdsMsg = " + str);
        return 0;
    }

    public int notifyVideoInfoMessage(int i, int i2, int i3, int i4, int i5, int i6, int i7) throws RemoteException {
        TvDebugLog.d(TAG, "notifyVideoInfoMessage updateType=" + i);
        return 0;
    }

    public int notifyDrmpKeyUpdateStatus(int i) throws RemoteException {
        TvDebugLog.d(TAG, "(Default Handler) notifyDrmpKeyUpdateStatus result=" + i);
        return 0;
    }
}
