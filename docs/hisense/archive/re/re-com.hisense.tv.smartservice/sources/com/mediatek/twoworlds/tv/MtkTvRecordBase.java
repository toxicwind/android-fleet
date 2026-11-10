package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;
import com.mediatek.twoworlds.tv.model.MtkTvBookingBase;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvRecordBase {
    public static final int MAX_REMINDER_SLOT_NUM = 20;
    public static final int NAV_REC_PVR_SRC_CHECK_RES_AV_ATV = 3;
    public static final int NAV_REC_PVR_SRC_CHECK_RES_AV_UNBLOCK = 5;
    public static final int NAV_REC_PVR_SRC_CHECK_RES_CI_NOTALLOW = 4;
    public static final int NAV_REC_PVR_SRC_CHECK_RES_CONFLICT = 2;
    public static final int NAV_REC_PVR_SRC_CHECK_RES_FAIL = 1;
    public static final int NAV_REC_PVR_SRC_CHECK_RES_OK = 0;
    public static final String TAG = "MtkTvRecordBase ";
    public MtkTvRecordAdaptorBase mRecordAdaptor;

    public enum APP_CFG_RMDR_MODE_T {
        APP_CFG_RMDR_MODE_NONE,
        APP_CFG_RMDR_MODE_RMDR,
        APP_CFG_RMDR_MODE_RECD,
        APP_CFG_RMDR_MODE_CI
    }

    public enum RecordBaseErrorID {
        RECORD_PVR_ERR_ID_NONE,
        RECORD_PVR_ERR_ID_RECORDING,
        RECORD_PVR_ERR_ID_UNKNOWN_SRC,
        RECORD_PVR_ERR_ID_FEATURE_NOT_SUPPORTED,
        RECORD_PVR_ERR_ID_INSUFFICIENT_RESOURCE,
        RECORD_PVR_ERR_ID_AV_STREAM_NOT_AVAILABLE,
        RECORD_PVR_ERR_ID_STREAM_NOT_AUTHORIZED,
        RECORD_PVR_ERR_ID_INPUT_LOCKED,
        RECORD_PVR_ERR_ID_DISK_NOT_READY,
        RECORD_PVR_ERR_ID_DISK_TOO_SMALL,
        RECORD_PVR_ERR_ID_DISK_FULL,
        RECORD_PVR_ERR_ID_VIDEO_RESOLUTION_ERROR,
        RECORD_PVR_ERR_ID_INTERNAL_ERROR,
        RECORD_PVR_ERR_ID_ATV_IS_OFF,
        RECORD_PVR_ERR_ID_PVR_DIABLE_BY_PIP_POP,
        RECORD_PVR_ERR_ID_PVR_DIABLE_BY_WRONG_VIDEO_INFO,
        RECORD_PVR_ERR_ID_PVR_DIABLE_BY_NO_AUDIO,
        RECORD_PVR_ERR_ID_PVR_DIABLE_BY_SCRAMBLE_AV,
        RECORD_PVR_ERR_ID_PVR_MODE_OFF,
        RECORD_PVR_ERR_ID_START_FAILED_EAS,
        RECORD_PVR_ERR_ID_START_FAILED_UPDATER,
        RECORD_PVR_ERR_ID_START_FAILED_OAD,
        RECORD_PVR_ERR_ID_START_FAILED_TV_NET_SVC,
        RECORD_PVR_ERR_ID_START_FAILED_MMP,
        RECORD_PVR_ERR_ID_CAM_PIN_CODE_INVALID,
        RECORD_PVR_ERR_ID_REACH_UPPER_LIMIT,
        RECORD_PVR_ERR_ID_PVR_DISABLE_BY_ECI_PROTECT,
        RECORD_PVR_ERR_ID_LAST
    }

    public enum RecordDeviceStatus {
        NAV_REC_DEV_STATUS_NO_DEVICE,
        NAV_REC_DEV_STATUS_INSETTED,
        NAV_REC_DEV_STATUS_REMOVED,
        NAV_REC_DEV_STATUS_FORMATED,
        NAV_REC_DEV_STATUS_SELECTED,
        NAV_REC_DEV_STATUS_DEVICE_AVAIL,
        NAV_REC_DEV_STATUS_CHG_ANOTHER_AVAIL,
        NAV_REC_DEV_STATUS_READY,
        NAV_REC_DEV_STATUS_DEVICE_FULL,
        NAV_REC_DEV_STATUS_DEVICE_TOO_SMALL,
        NAV_REC_DEV_STATUS_DEVICE_READ_ONLY
    }

    public enum RecordNotifyCode {
        REC_PVR_NTFY_STATUS_CHANGE,
        REC_PVR_NTFY_STRG_STATUS_CHANGE,
        REC_PVR_NTFY_DEV_TEST_PROGRESS,
        REC_PVR_NTFY_DEV_TEST_COMPLETE,
        REC_PVR_NTFY_DEV_TEST_CANCELED,
        REC_PVR_NTFY_DEV_TEST_FAILED,
        REC_PVR_NTFY_LAST
    }

    public enum RecordNotifyMsgType {
        RECORD_PVR_NTFY_VIEW_HANDLE_KEY_EVENT,
        RECORD_PVR_NTFY_CONTROLLER_SET_MAX_DURAIOTN,
        RECORD_PVR_NTFY_VIEW_STATUS_RECORDING,
        RECORD_PVR_NTFY_VIEW_STATUS_STOPPED,
        RECORD_PVR_NTFY_VIEW_STATUS_SAVING,
        RECORD_PVR_NTFY_VIEW_STRG_STATUS_CHANGE,
        RECORD_PVR_NTFY_VIEW_DATA_SOURCE_UPDATE_CHANNEL_INFO,
        RECORD_PVR_NTFY_VIEW_DATA_SOURCE_UPDATE_RECORDING_STATUS,
        RECORD_PVR_NTFY_VIEW_INSERT_PVR_FILE,
        RECORD_PVR_NTFY_VIEW_ERROR_CODE,
        RECORD_PVR_NTFY_VIEW_ICL_MAIN_SVC,
        RECORD_PVR_NTFY_VIEW_ICL_SUB_SVC,
        RECORD_PVR_NTFY_VIEW_SOURCE_CHANGE,
        RECORD_PVR_NTFY_VIEW_SCHEDULE_START,
        RECORD_PVR_NTFY_VIEW_INTERNAL_ERROR,
        RECORD_PVR_NTFY_VIEW_UPDATE_EVERY_5_SECONDS,
        RECORD_PVR_NTFY_VIEW_ECP_NOT_CONTENT,
        RECORD_PVR_NTFY_STATUS_RECORDING,
        RECORD_PVR_NTFY_STATUS_STOPPED,
        RECORD_PVR_NTFY_STATUS_SAVING,
        RECORD_PVR_NTFY_CONTROLER_STOP_PVR,
        RECORD_PVR_NTFY_CONTROLLER_CHANNEL_READY,
        RECORD_PVR_NTFY_CONTROLLER_SOURCE_READY,
        RECORD_PVR_NTFY_CONTROLLER_DELAY_PVR_START_OK,
        RECORD_PVR_NTFY_CONTROLLER_SWITCH_CONTEXT,
        RECORD_PVR_NTFY_CONTROLLER_CAM_PIN_CHANGE,
        RECORD_PVR_NTFY_CONTROLLER_SET_CONFLICT_FEATURE,
        RECORD_PVR_NTFY_DELAY_PVR_START_OK,
        RECORD_PVR_NTFY_SWITCH_CONTEXT,
        RECORD_PVR_NTFY_CAM_PIN_CHANGE,
        RECORD_PVR_NTFY_INSERT_PVR_FILE,
        RECORD_PVR_NTFY_ICL_MAIN_SVC,
        RECORD_PVR_NTFY_ICL_SUB_SVC,
        RECORD_PVR_NTFY_CONTROLER_INTERNAL_ERROR,
        RECORD_PVR_NTFY_CONTROLER_DEVICE_ERROR,
        RECORD_PVR_NTFY_STRG_STATUS_CHANGE,
        RECORD_PVR_NTFY_ERROR_CODE,
        RECORD_PVR_NTFY_SOURCE_READY,
        RECORD_PVR_NTFY_SPEED_TEST_PROGRESS,
        RECORD_PVR_NTFY_SPEED_TEST_COMPLETE,
        RECORD_PVR_NTFY_SPEED_TEST_CANCELED,
        RECORD_PVR_NTFY_SPEED_TEST_FAILED,
        RECORD_PVR_NTFY_VIEW_ICL_CHANGE_CHANNEL,
        RECORD_PVR_NTFY_VIEW_ICL_CHANGE_INPUT,
        RECORD_PVR_NTFY_CONTROLLER_EVENT_DURATION_UPDATE,
        RECORD_PVR_NTFY_CONTROLLER_REMAIN_DURATION_UPDATE,
        RECORD_PVR_NTFY_CONTROLLER_FAILED_SCHEDULE_NOTIFY,
        RECORD_PVR_NTFY_CONTROLLER_SIGNAL_LOSS,
        RECORD_PVR_NTFY_LAST_ENTRY
    }

    public enum RecordPvrMode {
        PVR_MODE_NORMAL,
        PVR_MODE_SCHEDULE_NORMAL,
        PVR_MODE_SCHEDULE_BG,
        PVR_MODE_SCHEDULE_ACCURATE,
        PVR_MODE_NORMAL_ACCURATE,
        PVR_MODE_SCHEDULE_BG_ACCURATE
    }

    public enum RecordPvrStatus {
        RECORD_PVR_UNKNOWN,
        RECORD_PVR_RECORDING,
        RECORD_PVR_SAVING,
        RECORD_PVR_STOPPED,
        RECORD_PVR_SCHEDULING
    }

    public enum RecordSrcType {
        RECORD_PVR_SRC_TYPE_UNKNOWN,
        RECORD_PVR_SRC_TYPE_ATV,
        RECORD_PVR_SRC_TYPE_DTV,
        RECORD_PVR_SRC_TYPE_COMPOSITE,
        RECORD_PVR_SRC_TYPE_S_VIDEO,
        RECORD_PVR_SRC_TYPE_SCART,
        RECORD_PVR_SRC_TYPE_MMP,
        RECORD_PVR_SRC_TYPE_COMPONENT,
        RECORD_PVR_SRC_TYPE_VGA,
        RECORD_PVR_SRC_TYPE_HDMI
    }

    public enum RecordStopReason {
        RECORD_PVR_STOP_REASON_DISK_NOT_READY,
        RECORD_PVR_STOP_REASON_INPUT_BLOCK,
        RECORD_PVR_STOP_REASON_SVC_CHANGE,
        RECORD_PVR_STOP_REASON_SVC_STOP,
        RECORD_PVR_STOP_REASON_SVL_UPDATE,
        RECORD_PVR_STOP_REASON_POWER_OFF,
        RECORD_PVR_STOP_REASON_NO_SIGNAL,
        RECORD_PVR_STOP_REASON_ACFG_CHG,
        RECORD_PVR_STOP_REASON_REACH_DUR,
        RECORD_PVR_STOP_REASON_BY_USER,
        RECORD_PVR_STOP_REASON_BY_OTHER_OPERATION,
        RECORD_PVR_STOP_REASON_EAS,
        RECORD_PVR_STOP_REASON_DISK_FULL,
        RECORD_PVR_STOP_REASON_SCRAMBLED_STREAM,
        RECORD_PVR_STOP_REASON_BY_START_EPG,
        RECORD_PVR_STOP_REASON_BY_START_PIPPOP,
        RECORD_PVR_STOP_REASON_BY_START_MMP,
        RECORD_PVR_STOP_REASON_BY_CHANGE_CHANNEL,
        RECORD_PVR_STOP_REASON_DO_CONFLICT_ACTION,
        RECORD_PVR_STOP_REASON_BY_BG_PVR,
        RECORD_PVR_STOP_REASON_ACCIDENT,
        RECORD_PVR_STOP_REASON_LAST_ENTRY
    }

    public MtkTvRecordBase() {
        Log.d(TAG, "Enter MtkTvRecordBase struct Here.");
        this.mRecordAdaptor = new MtkTvRecordAdaptorBase();
    }

    public static int SelectSvc(int i, int i2, int i3) {
        return TVNativeWrapper.recordPVRSelectSvc(i, i2, i3);
    }

    public static int close(int i) {
        Log.d(TAG, "Enter getRegisterInformation Here");
        return TVNativeWrapper.recordClosePVR(i);
    }

    public static String getDisk() {
        return TVNativeWrapper.getRecordDisk();
    }

    public static int getErrorID() {
        return TVNativeWrapper.recordgetErrorID();
    }

    public static int getErrorIDByHandle(int i) {
        return TVNativeWrapper.recordgetErrorIDByHandle(i);
    }

    public static int getRecordModeByHandle(int i) {
        return TVNativeWrapper.getRecordModeByHandle_native(i);
    }

    public static long getRecordingFilesize() {
        return TVNativeWrapper.recordGetRecordingFilesize();
    }

    public static int getRecordingPosition() {
        return TVNativeWrapper.getRecordingPosition();
    }

    public static int getRecordingPositionByHandle(int i) {
        return TVNativeWrapper.getRecordingPositionByHandle(i);
    }

    public static int getSrcType() {
        return TVNativeWrapper.recordGetPVRSrcType();
    }

    public static int getSrcTypeByHandle(int i) {
        return TVNativeWrapper.recordGetPVRSrcTypeByHandle(i);
    }

    public static int getStatus() {
        return TVNativeWrapper.recordGetPVRStatus();
    }

    public static int getStatusByHandle(int i) {
        return TVNativeWrapper.recordGetPVRStatusByHandle(i);
    }

    public static long getStorageFreeSize() {
        return TVNativeWrapper.getStorageFreeSize_native();
    }

    public static long getStorageSize() {
        return TVNativeWrapper.getStorageSize_native();
    }

    public static int open(int i) {
        Log.d(TAG, "Enter getRegisterInformation Here");
        return TVNativeWrapper.recordOpenPVR(i);
    }

    public static boolean setDisk(String str) {
        TVNativeWrapper.SetRecordDisk(str);
        return true;
    }

    public static boolean setDiskByHandle(int i, String str) {
        TVNativeWrapper.SetRecordDiskByHandle(i, str);
        return true;
    }

    public static int setFileLockChannel(String str, int i) {
        return TVNativeWrapper.setFileLockChannel(str, i);
    }

    public static int setFileLockInput(String str, int i) {
        return TVNativeWrapper.setFileLockInput(str, i);
    }

    public static boolean setFileName(String str) {
        return true;
    }

    public static int setRecordModeByHandle(int i, int i2) {
        return TVNativeWrapper.setRecordModeByHandle_native(i, i2);
    }

    public static int start(int i) {
        Log.d(TAG, "Enter start Here");
        return TVNativeWrapper.recordStartPVR(i);
    }

    public static int startByHandle(int i) {
        Log.d(TAG, "Enter getRegisterInformation Here");
        return TVNativeWrapper.recordStartPVRByHandle(i);
    }

    public static boolean startSpeedTest(String str, int i) {
        return TVNativeWrapper.recordStartSpeedTest(str, i);
    }

    public static int stop() {
        Log.d(TAG, "Enter stop Here");
        return TVNativeWrapper.recordStopPVR();
    }

    public static int stopByHandle(int i) {
        return TVNativeWrapper.recordStopPVRByHandle(i);
    }

    public int addBooking(int i, int i2) {
        Log.d(TAG, "Enter addBooking Here.");
        if (i < 0 || i2 < 0) {
            return -1;
        }
        return this.mRecordAdaptor.addBooking(i, i2);
    }

    public int deleteBooking(int i) {
        a.b("Enter deleteBooking Here.", i, TAG);
        return this.mRecordAdaptor.delBooking(i);
    }

    public List<MtkTvBookingBase> getBookingList() {
        return this.mRecordAdaptor.getBookingList();
    }

    public String getRecordingFileName() {
        Log.d(TAG, "Enter getRecordingFile Here.");
        String recordingFileName = this.mRecordAdaptor.getRecordingFileName();
        if (recordingFileName != null) {
            a.c("File: ", recordingFileName, TAG);
        }
        return recordingFileName;
    }

    public String getRecordingFileNameByHandle(int i) {
        Log.d(TAG, "Enter getRecordingFile Here.");
        String recordingFileNameByHandle = this.mRecordAdaptor.getRecordingFileNameByHandle(i);
        if (recordingFileNameByHandle != null) {
            a.c("File: ", recordingFileNameByHandle, TAG);
        }
        return recordingFileNameByHandle;
    }

    public boolean getRegisterInformation(String str) {
        Log.d(TAG, "Enter getRegisterInformation Here");
        return this.mRecordAdaptor.getRegisterInformation(str);
    }

    public int replaceBooking(int i, MtkTvBookingBase mtkTvBookingBase) {
        if (mtkTvBookingBase == null) {
            return -1;
        }
        a.b("Enter replaceBooking Here.", mtkTvBookingBase, TAG);
        this.mRecordAdaptor.replaceBooking(i, mtkTvBookingBase);
        this.mRecordAdaptor.updateBooking();
        return 0;
    }

    public boolean setRegisterInformation(String str) {
        a.c("Enter setGegisterInformation Here,  file = ", str, TAG);
        return this.mRecordAdaptor.setRegisterInformation(str);
    }

    public static int open(int i, boolean z) {
        Log.d(TAG, "Ex Open Enter getRegisterInformation Here");
        return TVNativeWrapper.recordOpenPVREx(i, z);
    }

    public int addBooking(int i, long j, int i2, String str) {
        Log.d(TAG, "Enter addRecord Here.channelId:" + i + ",startTime:" + j + ",duration:" + i2 + ", firstEventTitle:" + str);
        if (j < 0 || i2 < 0 || i < 0) {
            return -1;
        }
        return this.mRecordAdaptor.addBooking(i, j, i2, str);
    }

    public int addBooking(MtkTvBookingBase mtkTvBookingBase) {
        if (mtkTvBookingBase == null) {
            return -1;
        }
        a.b("Enter addBooing Here.", mtkTvBookingBase, TAG);
        this.mRecordAdaptor.addBooking(mtkTvBookingBase);
        this.mRecordAdaptor.updateBooking();
        return 0;
    }
}
