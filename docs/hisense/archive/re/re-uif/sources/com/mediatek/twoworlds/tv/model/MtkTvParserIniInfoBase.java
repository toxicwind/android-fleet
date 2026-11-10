package com.mediatek.twoworlds.tv.model;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvParserIniInfoBase {
    public static final int DATA_TYPE_INT = 0;
    public static final int DATA_TYPE_STRING = 1;
    public static final int ERROR_CODE_FAIL = -1;
    public static final int ERROR_CODE_NOT_IMPLEMENT = -2;
    public static final int ERROR_CODE_SUCCESS = 0;
    private static final String TAG = "MtkTvParserIniInfoBase";
    private int dataType;
    private int errorCode;
    private int intData;
    private String stringData;

    public MtkTvParserIniInfoBase() {
        Log.d(TAG, "Enter MtkTvBookingBase struct Here.");
        this.errorCode = 0;
        this.dataType = 0;
        this.intData = 0;
        this.stringData = "";
    }

    public int getDataType() {
        Log.d(TAG, "getErrorCode" + this.dataType);
        return this.dataType;
    }

    public int getErrorCode() {
        Log.d(TAG, "getErrorCode" + this.errorCode);
        return this.errorCode;
    }

    public int getIntData() {
        Log.d(TAG, "getSignedData" + this.intData);
        return this.intData;
    }

    public String getStringData() {
        Log.d(TAG, "getStringData" + this.stringData);
        return this.stringData;
    }

    public void setDataType(int i) {
        Log.d(TAG, "getErrorCode" + i);
        this.dataType = i;
    }

    public void setErrorCode(int i) {
        Log.d(TAG, "getErrorCode" + i);
        this.errorCode = i;
    }

    public void setIntData(int i) {
        Log.d(TAG, "setSignedData" + i);
        this.intData = i;
    }

    public void setStringData(String str) {
        Log.d(TAG, "setStringData" + str);
        this.stringData = str;
    }
}
