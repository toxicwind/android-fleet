package com.mediatek.twoworlds.tv.model;

import android.util.Log;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvParserIniInfoBase {
    public static final int DATA_TYPE_INT = 0;
    public static final int DATA_TYPE_STRING = 1;
    public static final int ERROR_CODE_FAIL = -1;
    public static final int ERROR_CODE_NOT_IMPLEMENT = -2;
    public static final int ERROR_CODE_SUCCESS = 0;
    public static final String TAG = "MtkTvParserIniInfoBase";
    public int dataType;
    public int errorCode;
    public int intData;
    public String stringData;

    public MtkTvParserIniInfoBase() {
        Log.d(TAG, "Enter MtkTvBookingBase struct Here.");
        this.errorCode = 0;
        this.dataType = 0;
        this.intData = 0;
        this.stringData = "";
    }

    public int getDataType() {
        a.b(a.a("getErrorCode"), this.dataType, TAG);
        return this.dataType;
    }

    public int getErrorCode() {
        a.b(a.a("getErrorCode"), this.errorCode, TAG);
        return this.errorCode;
    }

    public int getIntData() {
        a.b(a.a("getSignedData"), this.intData, TAG);
        return this.intData;
    }

    public String getStringData() {
        StringBuilder sbA = a.a("getStringData");
        sbA.append(this.stringData);
        Log.d(TAG, sbA.toString());
        return this.stringData;
    }

    public void setDataType(int i) {
        a.b("getErrorCode", i, TAG);
        this.dataType = i;
    }

    public void setErrorCode(int i) {
        a.b("getErrorCode", i, TAG);
        this.errorCode = i;
    }

    public void setIntData(int i) {
        a.b("setSignedData", i, TAG);
        this.intData = i;
    }

    public void setStringData(String str) {
        a.c("setStringData", str, TAG);
        this.stringData = str;
    }
}
