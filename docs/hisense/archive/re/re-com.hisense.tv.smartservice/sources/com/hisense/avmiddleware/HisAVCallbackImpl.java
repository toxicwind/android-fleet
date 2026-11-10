package com.hisense.avmiddleware;

import b.a.a.a.a;
import java.util.ArrayList;
import vendor.hisense.mw.hs_av_service.V1_0.IHisAVApiCallback;

/* JADX INFO: loaded from: classes2.dex */
public class HisAVCallbackImpl extends IHisAVApiCallback.Stub {
    public static final int HIS_EVENT_AUDIO_END = 65539;
    public static final int HIS_EVENT_AUDIO_START = 65537;
    public static final int HIS_EVENT_PICTURE_MODE = 131074;
    public static final int HIS_EVENT_SOUND_MODE = 65538;
    public static final int HIS_EVENT_VIDEO_END = 131075;
    public static final int HIS_EVENT_VIDEO_START = 131073;
    public static final String TAG = "HISMW@Callback_JAVA";
    public static int mReturnVal = -1;

    public HisAVCallbackImpl() {
        HisLogger.d(TAG, "HisAVCallbackImpl ++");
        HisLogger.d(TAG, "HisAVCallbackImpl --");
    }

    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAVApiCallback
    public int notify(int i, ArrayList<Byte> arrayList, int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append("HisAVCallbackImpl notify event_id: ");
        sb.append(i);
        sb.append(",data");
        sb.append(arrayList);
        sb.append(",len");
        a.c(sb, i2, TAG);
        switch (i) {
            case HIS_EVENT_AUDIO_START /* 65537 */:
            case HIS_EVENT_SOUND_MODE /* 65538 */:
            case HIS_EVENT_AUDIO_END /* 65539 */:
                return 1;
            default:
                switch (i) {
                    case HIS_EVENT_VIDEO_START /* 131073 */:
                    case HIS_EVENT_PICTURE_MODE /* 131074 */:
                    case HIS_EVENT_VIDEO_END /* 131075 */:
                        return 1;
                    default:
                        return 0;
                }
        }
    }
}
