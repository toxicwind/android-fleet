package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvGpio extends MtkTvGpioBase {
    public static final int GPIO_ID_COMPONENT = 3;
    public static final int GPIO_ID_CVBS = 1;
    public static final int GPIO_ID_HEADPHONE = 4;
    public static final int GPIO_ID_MHL_CBUS = 5;
    public static final int GPIO_ID_MINI_CVBS = 2;

    @Override // com.mediatek.twoworlds.tv.MtkTvGpioBase
    public int getGpioIdNumber() {
        return 5;
    }
}
