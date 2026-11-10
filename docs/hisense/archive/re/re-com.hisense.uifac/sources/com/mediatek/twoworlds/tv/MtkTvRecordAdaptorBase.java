package com.mediatek.twoworlds.tv;

import android.util.Log;
import com.mediatek.twoworlds.tv.model.MtkTvBookingBase;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvRecordAdaptorBase {
    private static final String TAG = "MtkTvRecordAdaptorBase ";

    public MtkTvRecordAdaptorBase() {
        Log.d(TAG, "Enter MtkTvRecordAdaptorBase struct Here.");
    }

    public void updateBooking() {
        TVNativeWrapper.updateBooking_native();
    }

    public List<MtkTvBookingBase> getBookingList() {
        ArrayList arrayList;
        Log.d(TAG, "Enter getRecordList Here.");
        synchronized (this) {
            arrayList = new ArrayList();
            int bookingCount_native = TVNativeWrapper.getBookingCount_native();
            for (int i = 0; i < bookingCount_native; i++) {
                arrayList.add(getBookingByIndex(i));
            }
            Log.d(TAG, "List  size = " + arrayList.size());
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                Log.d(TAG, ((MtkTvBookingBase) arrayList.get(i2)).toString());
            }
        }
        return arrayList;
    }

    protected MtkTvBookingBase getBookingByIndex(int i) {
        MtkTvBookingBase mtkTvBookingBase = new MtkTvBookingBase();
        mtkTvBookingBase.setBookingId(i);
        mtkTvBookingBase.setChannelId(TVNativeWrapper.getBookingChannelId(i));
        mtkTvBookingBase.setDeviceIndex(TVNativeWrapper.getBookingDeviceIndex(i));
        mtkTvBookingBase.setEventTitle(TVNativeWrapper.getBookingEventTitle(i));
        mtkTvBookingBase.setGenre(TVNativeWrapper.getBookingGenre(i));
        mtkTvBookingBase.setInfoData(TVNativeWrapper.getBookingInfoData(i));
        mtkTvBookingBase.setRecordDelay(TVNativeWrapper.getBookingRecordDelay(i));
        mtkTvBookingBase.setRecordDuration(TVNativeWrapper.getBookingRecordDuration(i));
        mtkTvBookingBase.setRecordMode(TVNativeWrapper.getBookingRecordMode(i));
        mtkTvBookingBase.setRecordStartTime(TVNativeWrapper.getBookingStartTime(i));
        mtkTvBookingBase.setRepeatMode(TVNativeWrapper.getBookingRepeatMode(i));
        mtkTvBookingBase.setSourceType(TVNativeWrapper.getBookingSourceType(i));
        mtkTvBookingBase.setTunerType(TVNativeWrapper.getBookingTunerType(i));
        mtkTvBookingBase.setType(TVNativeWrapper.getBookingType(i));
        mtkTvBookingBase.setEventId(TVNativeWrapper.getBookingEventId(i));
        mtkTvBookingBase.setUrlInfo(TVNativeWrapper.getBookingUrlInfo(i));
        return mtkTvBookingBase;
    }

    protected void setBookingByIndex(int i, MtkTvBookingBase mtkTvBookingBase) {
        TVNativeWrapper.setBookingChannelId(i, mtkTvBookingBase.getChannelId());
        TVNativeWrapper.setBookingDeviceIndex(i, mtkTvBookingBase.getDeviceIndex());
        TVNativeWrapper.setBookingEventTitle(i, mtkTvBookingBase.getEventTitle());
        TVNativeWrapper.setBookingGenre(i, mtkTvBookingBase.getGenre());
        TVNativeWrapper.setBookingInfoData(i, mtkTvBookingBase.getInfoData());
        TVNativeWrapper.setBookingRecordDelay(i, mtkTvBookingBase.getRecordDelay());
        TVNativeWrapper.setBookingRecordDuration(i, mtkTvBookingBase.getRecordDuration());
        TVNativeWrapper.setBookingRecordMode(i, mtkTvBookingBase.getRecordMode());
        TVNativeWrapper.setBookingStartTime(i, mtkTvBookingBase.getRecordStartTime());
        TVNativeWrapper.setBookingRepeatMode(i, mtkTvBookingBase.getRepeatMode());
        TVNativeWrapper.setBookingSourceType(i, mtkTvBookingBase.getSourceType());
        TVNativeWrapper.setBookingTunerType(i, mtkTvBookingBase.getTunerType());
        TVNativeWrapper.setBookingType(i, mtkTvBookingBase.getType());
        TVNativeWrapper.setBookingEventId(i, mtkTvBookingBase.getEventId());
        TVNativeWrapper.setBookingUrlInfo(i, mtkTvBookingBase.getUrlInfo());
    }

    public int addBooking(int i, int i2) {
        Log.d(TAG, "Enter addRecord Here.");
        if (i < 0 || i2 < 0) {
            return -1;
        }
        return TVNativeWrapper.addBooking_native(i, i2) ? 0 : -2;
    }

    public int addBooking(int i, long j, int i2, String str) {
        Log.d(TAG, "Enter addRecord Here.channelId:" + i + ",startTime:" + j + ",duration:" + i2 + ", firstEventTitle:" + str);
        if (j < 0 || i2 < 0 || i < 0) {
            return -1;
        }
        return TVNativeWrapper.addBooking_native(i, j, i2, str) ? 0 : -2;
    }

    public int addBooking(MtkTvBookingBase mtkTvBookingBase) {
        setBookingByIndex(TVNativeWrapper.getBookingCount_native(), mtkTvBookingBase);
        return 0;
    }

    public int replaceBooking(int i, MtkTvBookingBase mtkTvBookingBase) {
        setBookingByIndex(i, mtkTvBookingBase);
        return 0;
    }

    public int delBooking(int i) {
        return TVNativeWrapper.delBooking_native(i);
    }

    public String getRecordingFileName() {
        return TVNativeWrapper.getRecordingFile_native();
    }

    public String getRecordingFileNameByHandle(int i) {
        return TVNativeWrapper.getRecordingFileByHandle_native(i);
    }

    public boolean setRegisterInformation(String str) {
        return TVNativeWrapper.setRecordRegisterFile_native(str);
    }

    public boolean getRegisterInformation(String str) {
        return TVNativeWrapper.checkRecordRegisterFile_native(str);
    }
}
