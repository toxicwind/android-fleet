package com.mediatek.twoworlds.tv.model;

import android.util.Log;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvOpenVCHIPInfoBase {
    public static final String TAG = "MtkTvOpenVCHIPInfoBase";
    public int dimNum;
    public String dimText;
    public boolean isDimGrad;
    public int levelNum;
    public String levelText;
    public String lvlAbbrText;
    public int regionId;
    public int regionNum;
    public String regionText;
    public int regionVersion;

    public int getDimNum() {
        return this.dimNum;
    }

    public String getDimText() {
        return this.dimText;
    }

    public int getLevelNum() {
        return this.levelNum;
    }

    public String getLevelText() {
        return this.levelText;
    }

    public String getLvlAbbrText() {
        StringBuilder sbA = a.a("getLvlAbbrText:");
        sbA.append(this.lvlAbbrText);
        Log.d(TAG, sbA.toString());
        return this.lvlAbbrText;
    }

    public int getRegionId() {
        return this.regionId;
    }

    public int getRegionNum() {
        return this.regionNum;
    }

    public String getRegionText() {
        return this.regionText;
    }

    public int getRegionVersion() {
        return this.regionVersion;
    }

    public boolean isDimGrad() {
        return this.isDimGrad;
    }

    public void setDimGrad(boolean z) {
        this.isDimGrad = z;
    }

    public void setDimNum(int i) {
        this.dimNum = i;
    }

    public void setDimText(String str) {
        a.c("setDimText:", str, TAG);
        this.dimText = str;
    }

    public void setLevelNum(int i) {
        this.levelNum = i;
    }

    public void setLevelText(String str) {
        a.c("setLevelText:", str, TAG);
        this.levelText = str;
    }

    public void setLvlAbbrText(String str) {
        a.c("setLvlAbbrText:", str, TAG);
        this.lvlAbbrText = str;
    }

    public void setRegionId(int i) {
        this.regionId = i;
    }

    public void setRegionNum(int i) {
        this.regionNum = i;
    }

    public void setRegionText(String str) {
        a.c("setRegionText:", str, TAG);
        this.regionText = str;
    }

    public void setRegionVersion(int i) {
        this.regionVersion = i;
    }
}
