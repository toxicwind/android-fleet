package com.mediatek.twoworlds.tv.model;

import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvOpenVCHIPSettingInfoBase {
    public static final int LEVEL_BLOCK_LEN = 255;
    public static final String TAG = "MtkTvOpenVCHIPSettingInfo";
    public int dimIndex;
    public byte[] lvlBlockData = new byte[255];
    public int regionIndex;
    public int unratedBlock;

    public int getDimIndex() {
        return this.dimIndex;
    }

    public byte[] getLvlBlockData() {
        return this.lvlBlockData;
    }

    public int getRegionIndex() {
        return this.regionIndex;
    }

    public int getUnratedBlock() {
        return this.unratedBlock;
    }

    public void setDimIndex(int i) {
        this.dimIndex = i;
    }

    public void setLvlBlockData(byte[] bArr) {
        if (bArr == null) {
            Log.d(TAG, "lvlBlockData is null \n");
        } else if (bArr.length > 255) {
            Log.d(TAG, "setLvlBlockData fail, length more than 255!\n");
        } else {
            System.arraycopy(bArr, 0, this.lvlBlockData, 0, bArr.length);
        }
    }

    public void setRegionIndex(int i) {
        this.regionIndex = i;
    }

    public void setUnratedBlock(int i) {
        this.unratedBlock = i;
    }
}
