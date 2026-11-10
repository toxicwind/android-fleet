package com.mediatek.twoworlds.tv;

import android.util.Log;
import com.mediatek.twoworlds.tv.common.MtkTvChCommonBase;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvVolCtrlBase {
    private static final String TAG = "MtkTvVolCtrl";

    public enum SpeakerType {
        AUDDEC_SPK_MODE_LR,
        AUDDEC_SPK_MODE_LL,
        AUDDEC_SPK_MODE_RR,
        AUDDEC_SPK_MODE_MIX,
        AUDDEC_SPK_MODE_INVALID
    }

    public enum VolType {
        VOL_TV_SPEAKER,
        VOL_HEAD_PHONE,
        VOL_FLAG
    }

    private static int getVolType(VolType volType) {
        if (VolType.VOL_TV_SPEAKER == volType) {
            return 0;
        }
        return (VolType.VOL_HEAD_PHONE != volType && VolType.VOL_FLAG == volType) ? 2 : 1;
    }

    public int incrVolume() {
        Log.d(TAG, "Enter vol_incr.");
        int volume = getVolume();
        int iIncrVolume_native = 100;
        if (MtkTvChCommonBase.isEmulator()) {
            Log.i(TAG, "[Emulator] incrVolume() is called!");
            iIncrVolume_native = 0;
        } else if (volume < 100) {
            iIncrVolume_native = TVNativeWrapper.incrVolume_native();
        }
        Log.d(TAG, "incrVolume " + iIncrVolume_native);
        Log.d(TAG, "Leave vol_incr.");
        return iIncrVolume_native;
    }

    public int incrVolume(VolType volType) {
        Log.d(TAG, "Enter vol_incr.");
        int volume = getVolume(volType);
        int iIncrVolume_native = 100;
        if (MtkTvChCommonBase.isEmulator()) {
            Log.i(TAG, "[Emulator] incrVolume() is called!");
            iIncrVolume_native = 0;
        } else if (volume < 100) {
            iIncrVolume_native = TVNativeWrapper.incrVolume_native(getVolType(volType));
        }
        Log.d(TAG, "incrVolume " + iIncrVolume_native);
        Log.d(TAG, "Leave vol_incr.");
        return iIncrVolume_native;
    }

    public int decrVolume() {
        Log.d(TAG, "Enter vol_decr.");
        int volume = getVolume();
        int iDecrVolume_native = 0;
        if (MtkTvChCommonBase.isEmulator()) {
            Log.i(TAG, "[Emulator] incrVolume() is called!");
        } else if (volume > 0) {
            iDecrVolume_native = TVNativeWrapper.decrVolume_native();
        }
        Log.d(TAG, "decrVolume " + iDecrVolume_native);
        Log.d(TAG, "Leave vol_decr.");
        return iDecrVolume_native;
    }

    public int decrVolume(VolType volType) {
        Log.d(TAG, "Enter vol_decr.");
        int volume = getVolume(volType);
        int iDecrVolume_native = 0;
        if (MtkTvChCommonBase.isEmulator()) {
            Log.i(TAG, "[Emulator] incrVolume() is called!");
        } else if (volume > 0) {
            iDecrVolume_native = TVNativeWrapper.decrVolume_native(getVolType(volType));
        }
        Log.d(TAG, "decrVolume " + iDecrVolume_native);
        Log.d(TAG, "Leave vol_decr.");
        return iDecrVolume_native;
    }

    public int setVolume(int i) {
        Log.d(TAG, "Enter set_vol.");
        int volume_native = -1;
        if (MtkTvChCommonBase.isEmulator()) {
            Log.i(TAG, "[Emulator] setVolume_native() is called!");
            if (i >= 0 && i <= 100) {
                volume_native = 0;
            }
        } else if (i >= 0 && i <= 100) {
            volume_native = TVNativeWrapper.setVolume_native(i);
        }
        Log.d(TAG, "setVolume_native " + volume_native);
        Log.d(TAG, "Leave set_vol.");
        return volume_native;
    }

    public int setVolume(VolType volType, int i) {
        Log.d(TAG, "Enter set_vol.");
        int volume_native = -1;
        if (MtkTvChCommonBase.isEmulator()) {
            Log.i(TAG, "[Emulator] setVolume_native() is called!");
            if (i >= 0 && i <= 100) {
                volume_native = 0;
            }
        } else if (i >= 0 && i <= 100) {
            volume_native = TVNativeWrapper.setVolume_native(getVolType(volType), i);
        }
        Log.d(TAG, "setVolume_native " + volume_native);
        Log.d(TAG, "Leave set_vol.");
        return volume_native;
    }

    public int getVolume() {
        int volume_native;
        Log.d(TAG, "Enter get_vol.");
        if (MtkTvChCommonBase.isEmulator()) {
            Log.i(TAG, "[Emulator] getVolume_native() is called!");
            volume_native = 0;
        } else {
            volume_native = TVNativeWrapper.getVolume_native();
        }
        Log.d(TAG, "get_vol " + volume_native);
        Log.d(TAG, "Leave get_vol.");
        return volume_native;
    }

    public int getVolume(VolType volType) {
        int volume_native;
        Log.d(TAG, "Enter get_vol.");
        if (MtkTvChCommonBase.isEmulator()) {
            Log.i(TAG, "[Emulator] getVolume_native() is called!");
            volume_native = 0;
        } else {
            volume_native = TVNativeWrapper.getVolume_native(getVolType(volType));
        }
        Log.d(TAG, "get_vol " + volume_native);
        Log.d(TAG, "Leave get_vol.");
        return volume_native;
    }

    public int setMute(boolean z) {
        int mute_native;
        Log.d(TAG, "Enter set_mute.");
        if (MtkTvChCommonBase.isEmulator()) {
            Log.i(TAG, "[Emulator] setMute_native() is called!");
            mute_native = 0;
        } else {
            mute_native = TVNativeWrapper.setMute_native(z);
        }
        Log.d(TAG, "setMute_native " + mute_native);
        Log.d(TAG, "Leave set_mute.");
        return mute_native;
    }

    public int setMute(VolType volType, boolean z) {
        int mute_native;
        Log.d(TAG, "Enter set_mute.");
        if (MtkTvChCommonBase.isEmulator()) {
            Log.i(TAG, "[Emulator] setMute_native() is called!");
            mute_native = 0;
        } else {
            mute_native = TVNativeWrapper.setMute_native(getVolType(volType), z);
        }
        Log.d(TAG, "setMute_native " + mute_native);
        Log.d(TAG, "Leave set_mute.");
        return mute_native;
    }

    public boolean getMute() {
        boolean mute_native;
        Log.d(TAG, "Enter get_mute.");
        if (MtkTvChCommonBase.isEmulator()) {
            Log.i(TAG, "[Emulator] getMute_native() is called!");
            mute_native = false;
        } else {
            mute_native = TVNativeWrapper.getMute_native();
        }
        Log.d(TAG, "getMute " + mute_native);
        Log.d(TAG, "Leave get_mute.");
        return mute_native;
    }

    public boolean getMute(VolType volType) {
        boolean mute_native;
        Log.d(TAG, "Enter get_mute.");
        if (MtkTvChCommonBase.isEmulator()) {
            Log.i(TAG, "[Emulator] getMute_native() is called!");
            mute_native = false;
        } else {
            mute_native = TVNativeWrapper.getMute_native(getVolType(volType));
        }
        Log.d(TAG, "getMute " + mute_native);
        Log.d(TAG, "Leave get_mute.");
        return mute_native;
    }

    public SpeakerType getSpeakerOutMode() {
        int iHighLevel_native = TVNativeWrapper.HighLevel_native(113, 0, 0, 0, 0, 0, 0);
        if (iHighLevel_native == 0) {
            return SpeakerType.AUDDEC_SPK_MODE_LR;
        }
        if (iHighLevel_native == 1) {
            return SpeakerType.AUDDEC_SPK_MODE_LL;
        }
        if (iHighLevel_native == 2) {
            return SpeakerType.AUDDEC_SPK_MODE_RR;
        }
        if (iHighLevel_native == 3) {
            return SpeakerType.AUDDEC_SPK_MODE_MIX;
        }
        return SpeakerType.AUDDEC_SPK_MODE_INVALID;
    }

    /* JADX INFO: renamed from: com.mediatek.twoworlds.tv.MtkTvVolCtrlBase$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$mediatek$twoworlds$tv$MtkTvVolCtrlBase$SpeakerType;

        static {
            int[] iArr = new int[SpeakerType.values().length];
            $SwitchMap$com$mediatek$twoworlds$tv$MtkTvVolCtrlBase$SpeakerType = iArr;
            try {
                iArr[SpeakerType.AUDDEC_SPK_MODE_LR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$mediatek$twoworlds$tv$MtkTvVolCtrlBase$SpeakerType[SpeakerType.AUDDEC_SPK_MODE_LL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$mediatek$twoworlds$tv$MtkTvVolCtrlBase$SpeakerType[SpeakerType.AUDDEC_SPK_MODE_RR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$mediatek$twoworlds$tv$MtkTvVolCtrlBase$SpeakerType[SpeakerType.AUDDEC_SPK_MODE_MIX.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public int setSpeakerOutMode(SpeakerType speakerType) {
        int i = AnonymousClass1.$SwitchMap$com$mediatek$twoworlds$tv$MtkTvVolCtrlBase$SpeakerType[speakerType.ordinal()];
        int i2 = 3;
        int i3 = 1;
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    i3 = 2;
                } else if (i != 4) {
                    return -1;
                }
            }
            return TVNativeWrapper.HighLevel_native(114, i3, 0, 0, 0, 0, 0);
        }
        i2 = 0;
        i3 = i2;
        return TVNativeWrapper.HighLevel_native(114, i3, 0, 0, 0, 0, 0);
    }
}
