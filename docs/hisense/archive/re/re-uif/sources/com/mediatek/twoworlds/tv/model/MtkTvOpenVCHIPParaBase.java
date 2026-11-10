package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes.dex */
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
    private int dimIndex;
    private int levelIndex;
    private int openVCHIPParaType;
    private int regionIndex;

    public int getOpenVCHIPParaType() {
        return this.openVCHIPParaType;
    }

    public void setOpenVCHIPParaType(int i) {
        this.openVCHIPParaType = i;
    }

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

    public int getLevelIndex() {
        return this.levelIndex;
    }

    public void setLevelIndex(int i) {
        this.levelIndex = i;
    }
}
