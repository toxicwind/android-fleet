package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvExternalUIStatusBase {
    public static final int EXTERNAL_UI_ID_AUD_LANGUAGE_LST = 2;
    public static final int EXTERNAL_UI_ID_MENU = 0;
    public static final int EXTERNAL_UI_ID_SUBTITLE_OPT_LST = 1;
    public static final int EXTERNAL_UI_ID_UNKNOW = -1;
    public static final String TAG = "MtkTvExternalUIStatusBase";
    public boolean isShow;
    public int uiId;

    public MtkTvExternalUIStatusBase() {
        this.uiId = -1;
        this.isShow = false;
    }

    public boolean getShow() {
        return this.isShow;
    }

    public int getUIId() {
        return this.uiId;
    }

    public void setShow(boolean z) {
        this.isShow = z;
    }

    public void setUIId(int i) {
        this.uiId = i;
    }

    public MtkTvExternalUIStatusBase(int i, boolean z) {
        this.uiId = i;
        this.isShow = z;
    }
}
