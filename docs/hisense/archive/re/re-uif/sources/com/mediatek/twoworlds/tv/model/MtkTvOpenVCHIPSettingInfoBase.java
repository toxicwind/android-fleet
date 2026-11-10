package com.mediatek.twoworlds.tv.model;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvOpenVCHIPSettingInfoBase {
    public static final int LEVEL_BLOCK_LEN = 255;
    private static final String TAG = "MtkTvOpenVCHIPSettingInfo";
    private int dimIndex;
    private byte[] lvlBlockData = new byte[255];
    private int regionIndex;
    private int unratedBlock;

    public int getRegionIndex() {
        return this.regionIndex;
    }

    public void setRegionIndex(int i) {
        this.regionIndex = i;
    }

    public int getDimIndex() {
        return this.dimIndex;
    }

    public void setDimIndex(int i) {
        this.dimIndex = i;
    }

    public int getUnratedBlock() {
        return this.unratedBlock;
    }

    public void setUnratedBlock(int i) {
        this.unratedBlock = i;
    }

    public byte[] getLvlBlockData() {
        return this.lvlBlockData;
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
}
