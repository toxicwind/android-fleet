package com.hmct.cloud.sdk.bean.wallpaper;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ThemeOrderReply extends ThemeBaseReply {
    public static final long serialVersionUID = 4304190825383197673L;
    public int count;
    public List<OrderInfo> orderList;

    public int getCount() {
        return this.count;
    }

    public List<OrderInfo> getOrderList() {
        return this.orderList;
    }

    public void setCount(int i) {
        this.count = i;
    }

    public void setOrderList(List<OrderInfo> list) {
        this.orderList = list;
    }
}
