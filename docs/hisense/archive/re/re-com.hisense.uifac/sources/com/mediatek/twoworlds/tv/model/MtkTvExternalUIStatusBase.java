package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvExternalUIStatusBase {
    public static final int EXTERNAL_UI_ID_AUD_LANGUAGE_LST = 2;
    public static final int EXTERNAL_UI_ID_MENU = 0;
    public static final int EXTERNAL_UI_ID_SUBTITLE_OPT_LST = 1;
    public static final int EXTERNAL_UI_ID_UNKNOW = -1;
    private static final String TAG = "MtkTvExternalUIStatusBase";
    protected boolean isShow;
    protected int uiId;

    public MtkTvExternalUIStatusBase() {
        this.uiId = -1;
        this.isShow = false;
    }

    public MtkTvExternalUIStatusBase(int i, boolean z) {
        this.uiId = i;
        this.isShow = z;
    }

    public void setUIId(int i) {
        this.uiId = i;
    }

    public void setShow(boolean z) {
        this.isShow = z;
    }

    public int getUIId() {
        return this.uiId;
    }

    public boolean getShow() {
        return this.isShow;
    }
}
