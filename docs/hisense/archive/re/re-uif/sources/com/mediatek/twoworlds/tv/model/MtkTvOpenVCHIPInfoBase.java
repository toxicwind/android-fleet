package com.mediatek.twoworlds.tv.model;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvOpenVCHIPInfoBase {
    private static final String TAG = "MtkTvOpenVCHIPInfoBase";
    private int dimNum;
    private String dimText;
    private boolean isDimGrad;
    private int levelNum;
    private String levelText;
    private String lvlAbbrText;
    private int regionId;
    private int regionNum;
    private String regionText;
    private int regionVersion;

    public int getRegionNum() {
        return this.regionNum;
    }

    public void setRegionNum(int i) {
        this.regionNum = i;
    }

    public int getDimNum() {
        return this.dimNum;
    }

    public void setDimNum(int i) {
        this.dimNum = i;
    }

    public int getLevelNum() {
        return this.levelNum;
    }

    public void setLevelNum(int i) {
        this.levelNum = i;
    }

    public int getRegionVersion() {
        return this.regionVersion;
    }

    public void setRegionVersion(int i) {
        this.regionVersion = i;
    }

    public int getRegionId() {
        return this.regionId;
    }

    public void setRegionId(int i) {
        this.regionId = i;
    }

    public boolean isDimGrad() {
        return this.isDimGrad;
    }

    public void setDimGrad(boolean z) {
        this.isDimGrad = z;
    }

    public String getRegionText() {
        return this.regionText;
    }

    public void setRegionText(String str) {
        Log.d(TAG, "setRegionText:" + str);
        this.regionText = str;
    }

    public String getDimText() {
        return this.dimText;
    }

    public void setDimText(String str) {
        Log.d(TAG, "setDimText:" + str);
        this.dimText = str;
    }

    public String getLevelText() {
        return this.levelText;
    }

    public void setLevelText(String str) {
        Log.d(TAG, "setLevelText:" + str);
        this.levelText = str;
    }

    public String getLvlAbbrText() {
        Log.d(TAG, "getLvlAbbrText:" + this.lvlAbbrText);
        return this.lvlAbbrText;
    }

    public void setLvlAbbrText(String str) {
        Log.d(TAG, "setLvlAbbrText:" + str);
        this.lvlAbbrText = str;
    }
}
