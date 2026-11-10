package com.mediatek.twoworlds.tv;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvGpioBase {
    public static final int GPIO_ID_MIN = 0;
    public static final int GPIO_MODE_INPUT = 0;
    public static final int GPIO_MODE_OUTPUT = 1;
    public static final int GPIO_STATUS_HIGH = 2;
    public static final int GPIO_STATUS_ID_INVALID = -1;
    public static final int GPIO_STATUS_LOW = 1;
    public static final int GPIO_STATUS_MODE_INVALID = -2;
    public static final int GPIO_STATUS_UNKNOWN = 0;
    public static final String TAG = "TV_MtkTvGPIOControl";

    public int applyGpioStatus(int i, int i2) {
        if (2 != i2 && 1 != i2) {
            return -1;
        }
        int iApplyGpioStatus_native = TVNativeWrapper.applyGpioStatus_native(i, i2);
        a.b("applyGpioStatus ret = ", iApplyGpioStatus_native, TAG);
        return iApplyGpioStatus_native;
    }

    public int getGpioIdNumber() {
        return 0;
    }

    public int queryGpioStatus(int i, int i2) {
        if (i2 < 0 || i2 > 1) {
            return -2;
        }
        int iQueryGpioStatus_native = TVNativeWrapper.queryGpioStatus_native(i, i2);
        a.b("queryGpioStatus ret = ", iQueryGpioStatus_native, TAG);
        return iQueryGpioStatus_native;
    }
}
