package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvOpenVCHIPParaBase {
    public static final int OPEN_VCHIP_KEY_GET_DIM_GRAD = 6;
    public static final int OPEN_VCHIP_KEY_GET_DIM_NUM = 4;
    public static final int OPEN_VCHIP_KEY_GET_DIM_TEXT = 5;
    public static final int OPEN_VCHIP_KEY_GET_LVL_ABBR = 8;
    public static final int OPEN_VCHIP_KEY_GET_LVL_NUM = 7;
    public static final int OPEN_VCHIP_KEY_GET_LVL_TEXT = 9;
    public static final int OPEN_VCHIP_KEY_GET_RGN_ID = 3;
    public static final int OPEN_VCHIP_KEY_GET_RGN_NUM = 0;
    public static final int OPEN_VCHIP_KEY_GET_RGN_TEXT = 1;
    public static final int OPEN_VCHIP_KEY_GET_RGN_VER = 2;
    public int dimIndex;
    public int levelIndex;
    public int openVCHIPParaType;
    public int regionIndex;

    public int getDimIndex() {
        return this.dimIndex;
    }

    public int getLevelIndex() {
        return this.levelIndex;
    }

    public int getOpenVCHIPParaType() {
        return this.openVCHIPParaType;
    }

    public int getRegionIndex() {
        return this.regionIndex;
    }

    public void setDimIndex(int i) {
        this.dimIndex = i;
    }

    public void setLevelIndex(int i) {
        this.levelIndex = i;
    }

    public void setOpenVCHIPParaType(int i) {
        this.openVCHIPParaType = i;
    }

    public void setRegionIndex(int i) {
        this.regionIndex = i;
    }
}
