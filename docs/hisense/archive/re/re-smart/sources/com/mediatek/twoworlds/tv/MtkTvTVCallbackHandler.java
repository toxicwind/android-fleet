package com.mediatek.twoworlds.tv;

import b.a.a.a.a;
import com.mediatek.twoworlds.tv.model.MtkTvCIMMIEnqBase;
import com.mediatek.twoworlds.tv.model.MtkTvCIMMIMenuBase;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvTVCallbackHandler {
    public static final String TAG = "MtkTvTVCallbackHandler";

    public MtkTvTVCallbackHandler() {
        synchronized (MtkTvTVCallbackHandler.class) {
            try {
                TVCallback.mCallback.registerCallback(this);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public final int addListenerConfig(String str) {
        try {
            TVCallback.mCallback.addListenerConfig(this, str);
            return 0;
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public int notifyATSCEventMessage(int i, int i2, int i3, long j) {
        StringBuilder sbB = a.b("notifyATSCEventMessage updateType=", i, ",arg1=", i2, ",arg2=");
        sbB.append(i3);
        sbB.append(",arg3=");
        sbB.append(j);
        TvDebugLog.d(TAG, sbB.toString());
        return 0;
    }

    public int notifyAVModeMessage(int i, int i2, int i3, int i4) {
        a.d("notifyAVModeMessage updateType=", i, TAG);
        return 0;
    }

    public int notifyAmpVolCtrlMessage(int i, boolean z) {
        TvDebugLog.d(TAG, "(Default Handler) notifyAmpVolCtrlMessage volume=" + i + " isMute=" + z);
        return 0;
    }

    public int notifyBMLMessage(int i, int[] iArr, int i2) {
        a.d("(Default Handler) notifyBMLMessage=", i, TAG);
        return 0;
    }

    public int notifyBannerMessage(int i, int i2, int i3, int i4) {
        TvDebugLog.d(TAG, "(Default Handler) notifyBannerMessage msgType=" + i + ", msgName=" + i2);
        return 0;
    }

    public int notifyBisskeyUpdateMsg(int i, int i2, int i3) {
        StringBuilder sbB = a.b("(Default Handler) notifyBisskeyUpdateMsg: condition = ", i, ", reason = ", i2, ", data = ");
        sbB.append(i3);
        sbB.append("\n");
        TvDebugLog.d(TAG, sbB.toString());
        return 0;
    }

    public int notifyBroadcastMessage(int i, int i2, int i3, int i4) {
        TvDebugLog.d(TAG, "(Default Handler) notifyBroadcastMessage type=" + i + "argv1=" + i2);
        return 0;
    }

    public int notifyCCMessage(int i, int i2, int i3, int i4) {
        return 0;
    }

    public int notifyCDTLogoMessage(int i, int i2, int i3, int i4) {
        TvDebugLog.d(TAG, "(Default Handler) notifyCDTLogoMessage type=" + i + "argv1=" + i2);
        return 0;
    }

    public int notifyCIMessage(int i, int i2, int i3, int i4, MtkTvCIMMIMenuBase mtkTvCIMMIMenuBase, MtkTvCIMMIEnqBase mtkTvCIMMIEnqBase) {
        return 0;
    }

    public int notifyCecActiveSource(int i, int i2, boolean z) {
        return 0;
    }

    public int notifyCecFrameInfo(int i, int i2, int i3, int[] iArr, int i4) {
        return 0;
    }

    public int notifyCecNotificationCode(int i) {
        a.d("(Default Handler) notifyCecNotificationCode volume=", i, TAG);
        return 0;
    }

    public int notifyChannelListUpdateMsg(int i, int i2, int i3) {
        StringBuilder sbB = a.b("(Default Handler) notifyChannelListChanged: condition = ", i, ", reason = ", i2, ", data = ");
        sbB.append(i3);
        sbB.append("\n");
        TvDebugLog.d(TAG, sbB.toString());
        return 0;
    }

    public int notifyConcernColumnUpdateMsg(int i, int i2, int[] iArr, int[] iArr2) {
        TvDebugLog.d(TAG, a.a("(Default Handler) notifyConcernColumnUpdateMsg: svlid = ", i, ", count = ", i2, "\n"));
        return 0;
    }

    public int notifyConfigMessage(int i, int i2) {
        TvDebugLog.d(TAG, "(Default Handler) notifyConfigMessage notifyId=" + i + " data=" + i2);
        return 0;
    }

    public int notifyConfigValuechanged(String str) {
        TvDebugLog.d(TAG, "(Default Handler) notifyConfigValuechanged cfgId=" + str);
        return 0;
    }

    public int notifyDeviceDiscovery() {
        TvDebugLog.d(TAG, "Device Discovery Finish");
        return 0;
    }

    public int notifyDrmpKeyUpdateStatus(int i) {
        a.d("(Default Handler) notifyDrmpKeyUpdateStatus result=", i, TAG);
        return 0;
    }

    public int notifyEASMessage(int i, int i2, int i3, int i4) {
        TvDebugLog.d(TAG, "(Default Handler) notifyEASMessage:");
        TvDebugLog.d(TAG, "notifyEASMessage updateType=" + i + ", channel_change =" + i2);
        return 0;
    }

    public int notifyEWSPAMessage(int i) {
        a.d("(Default Handler) notifyEWSPAMessage=", i, TAG);
        return 0;
    }

    public int notifyEventNotification(int i, int i2, int i3, long j) {
        StringBuilder sbB = a.b("(Default Handler) notifyEventNotification type=", i, "argv1=", i2, "argv2=");
        sbB.append(i3);
        sbB.append("argv3=");
        sbB.append(j);
        TvDebugLog.d(TAG, sbB.toString());
        return 0;
    }

    public int notifyFeatureMessage(int i, int i2, int i3, int i4) {
        TvDebugLog.d(TAG, "(Default Handler) notifyFeatureMessage type=" + i + "argv1=" + i2);
        return 0;
    }

    public int notifyGingaMessage(int i, String str, String str2) {
        TvDebugLog.d(TAG, "(Default Handler) notifyGingaMessage updateType=" + i + " app_id=" + str + " sAppName=" + str2);
        return 0;
    }

    public int notifyGingaUpdateMessage(int i, String str, int i2) {
        TvDebugLog.d(TAG, "(Default Handler) notifyGingaUpdateMessage updateType=" + i + "sJsonData =" + str + "datalen =" + i2);
        return 0;
    }

    public int notifyGingaVolumeChanged(int i, int i2) {
        TvDebugLog.d(TAG, "(Default Handler) notifyGingaVolumeChanged updateType=" + i + " level=" + i2);
        return 0;
    }

    public int notifyGpioStatus(int i, int i2) {
        TvDebugLog.d(TAG, "(Default Handler) notifyGpioStatus gpioId=" + i + ", status =" + i2);
        return 0;
    }

    public int notifyHBBTVMessage(int i, int[] iArr, int i2) {
        a.d("(Default Handler) notifyHBBTVMessage=", i, TAG);
        return 0;
    }

    public int notifyHideOSDMessage() {
        TvDebugLog.d(TAG, "(Default Handler) notifyHideOSDMessage");
        return 0;
    }

    public int notifyInputSignalChanged(int i, boolean z) {
        TvDebugLog.d(TAG, "(Default Handler) notifyInputSignalChanged inputId=" + i + "hasSignal=" + z);
        return 0;
    }

    public int notifyInputSourceMessage(int i, int i2, int i3, int i4) {
        return 0;
    }

    public int notifyListModeUpdateMsg(int i, int i2) {
        TvDebugLog.d(TAG, a.a("(Default Handler) notifyListModeUpdateMsg: oldMode = ", i, ", newMode = ", i2, "\n"));
        return 0;
    }

    public int notifyMHEG5LanuchHbbtv(String str, int i) {
        TvDebugLog.d(TAG, "(Default Handler) notifyMHEG5LanuchHbbtv.");
        return 0;
    }

    public int notifyMHEG5Message(int i, int i2, int i3, int i4) {
        a.d("(Default Handler) notifyMHEG5Message=", i, TAG);
        return 0;
    }

    public int notifyMHEG5MimeTypeSupport(String str, boolean[] zArr, int i) {
        TvDebugLog.d(TAG, "(Default Handler) notifyMHEG5MimeTypeSupport.");
        return 0;
    }

    public int notifyMHPMessage(int i, int i2, int i3, int i4) {
        a.d("(Default Handler) notifyMHPMessage=", i, TAG);
        return 0;
    }

    public int notifyMdsMessage(String str, int i) {
        TvDebugLog.d(TAG, "(Default Handler) notifyMdsMessage. sMdsMsg = " + str);
        return 0;
    }

    public int notifyMhlScratchpadData(int i, int i2, int i3, int[] iArr) {
        return 0;
    }

    public int notifyNativeAppStatus(int i, boolean z) {
        TvDebugLog.d(TAG, "(Default Handler) notifyNativeAppStatus nativeAppId=" + i + " show=" + z);
        return 0;
    }

    public int notifyNoUsedkeyMessage(int i, int i2, int i3, long j) {
        return 0;
    }

    public int notifyOADMessage(int i, String str, int i2, boolean z, int i3) {
        TvDebugLog.d(TAG, "(Default Handler) notifyOADMessage messageType=" + i + ", scheduleInfo =" + str + ", progress =" + i2 + ", autoDld =" + z);
        return 0;
    }

    public int notifyOclScanInfo(int i, int i2) {
        TvDebugLog.d(TAG, a.a("(Default Handler) notifyOclScanInfo: msgId = ", i, ", channelNum = ", i2, "\n"));
        return 0;
    }

    public int notifyOpenVCHIPMessage(int i, int i2, int i3, long j) {
        TvDebugLog.d(TAG, "(Default Handler) notifyOpenVCHIPMessage:");
        TvDebugLog.d(TAG, "notifyOpenVCHIPMessage updateType=" + i);
        return 0;
    }

    public int notifyOtherMessage(int i, int i2, int i3) {
        StringBuilder sbB = a.b("(Default Handler) notifyOtherMessage a1=", i, " a2=", i2, " a3=");
        sbB.append(i3);
        TvDebugLog.d(TAG, sbB.toString());
        return 0;
    }

    public int notifyPWDDialogMessage(int i, int i2, int i3, int i4) {
        a.d("notifyPWDDialogMessage updateType=", i, TAG);
        return 0;
    }

    public int notifyPipPopMessage(int i, int i2, int i3, int i4) {
        a.d("notifyPipPopMessage updateType=", i, TAG);
        return 0;
    }

    public int notifyRecordNotification(int i, int i2, int i3) {
        StringBuilder sbB = a.b("(Default Handler) notifyRecordNotification type=", i, "argv1=", i2, "argv2=");
        sbB.append(i3);
        TvDebugLog.d(TAG, sbB.toString());
        return 0;
    }

    public int notifyRecordPBNotification(int i, int i2, int i3, long j) {
        StringBuilder sbB = a.b("(Default Handler) notifyRecordPBNotification type=", i, "argv1=", i2, "argv2=");
        sbB.append(i3);
        sbB.append("argv3=");
        sbB.append(j);
        TvDebugLog.d(TAG, sbB.toString());
        return 0;
    }

    public int notifySatlListUpdateMsg(int i, int i2, int i3) {
        StringBuilder sbB = a.b("(Default Handler) notifySatlListUpdateMsg: condition = ", i, ", reason = ", i2, ", data = ");
        sbB.append(i3);
        sbB.append("\n");
        TvDebugLog.d(TAG, sbB.toString());
        return 0;
    }

    public int notifyScanNotification(int i, int i2, int i3, int i4) {
        StringBuilder sbB = a.b("(Default Handler) notifyScanNotification msg_id=", i, "scanProgress=", i2, "channelNum=");
        sbB.append(i3);
        sbB.append("argv4=");
        sbB.append(i4);
        TvDebugLog.d(TAG, sbB.toString());
        return 0;
    }

    public int notifyScreenSaverMessage(int i, int i2, int i3, int i4) {
        a.d("notifyScreenSaverMessage msg_id=", i, TAG);
        return 0;
    }

    public int notifySerialExternalControlCallback(int i, byte[] bArr) {
        TvDebugLog.d(TAG, "(Default Handler) notifySerialExternalControlCallback action=" + i + ",data=" + bArr);
        return 0;
    }

    public int notifyShowOSDMessage(int i, int i2) {
        TvDebugLog.d(TAG, "(Default Handler) notifyShowOSDMessage stringId=" + i + " msgType=" + i2);
        return 0;
    }

    public int notifySimulated3dAutoTurnOff() {
        TvDebugLog.d(TAG, "(Default Handler) notifySimulated3dAutoTurnOff:");
        TvDebugLog.d(TAG, "====>Simulated 3D mode has been turned off because 1 hour has elapsed. Press the 3D key to re-start Simulated 3D mode.");
        return 0;
    }

    public int notifySleepTimerChange(int i, int i2) {
        TvDebugLog.d(TAG, "(Default Handler) notifySimulated3dAutoTurnOff:");
        TvDebugLog.d(TAG, "notifySleepTimerChange type=" + i + ", remaining time =" + i2);
        return 0;
    }

    public int notifySpdInfoFrame(int i, int i2, int i3, int i4, int[] iArr, int[] iArr2, int i5) {
        return 0;
    }

    public int notifySubtitleMsg(int i, int i2, int i3, int i4) {
        StringBuilder sbB = a.b("(Default Handler) notifySubtitleMsg msg_id=", i, "argv1=", i2, "argv2=");
        sbB.append(i3);
        sbB.append("argv3=");
        sbB.append(i4);
        TvDebugLog.d(TAG, sbB.toString());
        return 0;
    }

    public int notifySvctxNotificationCode(int i) {
        a.d("(Default Handler) notifySvctxNotificationCode=", i, TAG);
        return 0;
    }

    public int notifySvctxNotificationCodebyPath(int i, int i2) {
        TvDebugLog.d(TAG, a.a("(Default Handler) notifySvctxNotificationCodebyPath(path = ", i, ",code = ", i2, ")"));
        return 0;
    }

    public int notifySvlIdUpdateMsg(int i, int i2, int i3) {
        TvDebugLog.d(TAG, "(Default Handler) notifySvlIdUpdateMsg: svlid = " + i + "\n");
        return 0;
    }

    public int notifySysAudMod(int i) {
        return 0;
    }

    public int notifyTeletextMessage(int i, int i2, int i3, int i4) {
        a.d("(Default Handler) notifyTeletextMessage messageID=", i, TAG);
        return 0;
    }

    public int notifyTimeshiftNoDiskFile(long j) {
        TvDebugLog.d(TAG, "(Default Handler) notifyTimeshiftNoDiskFile argv1=" + j);
        return 0;
    }

    public int notifyTimeshiftNotification(int i, long j) {
        TvDebugLog.d(TAG, "(Default Handler) notifyTimeshiftNotification type=" + i + "argv1=" + j);
        return 0;
    }

    public int notifyTimeshiftPlaybackStatusUpdate(int i) {
        a.d("(Default Handler) notifyTimeshiftPlaybackStatusUpdate status=", i, TAG);
        return 0;
    }

    public int notifyTimeshiftRecordStatus(int i, long j) {
        TvDebugLog.d(TAG, "(Default Handler) notifyTimeshiftRecordStatus status=" + i + "argv1=" + j);
        return 0;
    }

    public int notifyTimeshiftSpeedUpdate(int i) {
        a.d("(Default Handler) notifyTimeshiftSpeedUpdate speed=", i, TAG);
        return 0;
    }

    public int notifyTimeshiftStorageRemoved() {
        TvDebugLog.d(TAG, "(Default Handler) notifyTimeshiftStorageRemoved");
        return 0;
    }

    public int notifyTslIdUpdateMsg(int i, int i2, int i3) {
        StringBuilder sbB = a.b("(Default Handler) notifyTslIdUpdateMsg: condition = ", i, ",reason = ", i2, ",data = ");
        sbB.append(i3);
        sbB.append("\n");
        TvDebugLog.d(TAG, sbB.toString());
        return 0;
    }

    public int notifyTvproviderUpdateMsg(int i, int i2, int[] iArr, int[] iArr2) {
        TvDebugLog.d(TAG, a.a("(Default Handler) notifyTvproviderUpdateMsg: svlid = ", i, ", count = ", i2, "\n"));
        return 0;
    }

    public int notifyUARTSerialPortCallback(int i, int i2, int i3, byte[] bArr) {
        StringBuilder sbB = a.b("(Default Handler) notifyUARTSerialPortCallback uartSerialID=", i, ", ioNotifyCond=", i2, ", eventCode=");
        sbB.append(i3);
        sbB.append(",data=");
        sbB.append(bArr);
        TvDebugLog.d(TAG, sbB.toString());
        return 0;
    }

    public int notifyUiMsDisplay(int i, boolean z) {
        TvDebugLog.d(TAG, "(Default Handler) notifyUiMsDisplay uiType=" + i + " show=" + z);
        return 0;
    }

    public int notifyUpgradeMessage(int i, int i2, int i3, int i4) {
        return 0;
    }

    public int notifyVdpMuteAllFinished(int i) {
        a.d("(Default Handler) notifyVdpMuteAllFinished vdpId=", i, TAG);
        return 0;
    }

    public int notifyVideoInfoMessage(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        a.d("notifyVideoInfoMessage updateType=", i, TAG);
        return 0;
    }

    public int notifyWarningMessage(int i, int i2, String str, int i3, int i4) {
        a.d("notifyWarningMessage msg_id=", i, TAG);
        return 0;
    }

    public void removeListener() {
        try {
            TVCallback.mCallback.unregisterCallback(this);
            TVCallback.mCallback.removeListenerConfig(this, null);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public final int removeListenerConfig(String str) {
        TvDebugLog.d(TAG, "removeListenerConfig: cfgId = " + str);
        try {
            TVCallback.mCallback.removeListenerConfig(this, str);
            return 0;
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }
}
