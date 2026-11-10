package com.hisense.serialInfoTest.model;

import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class SerialCommandsType {
    public static HashMap<Integer, String> mChannelMap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        mChannelMap = map;
        map.put(0, "qingdao");
        mChannelMap.put(1, "huangdao");
        mChannelMap.put(2, "guiyang");
        mChannelMap.put(3, "jiangmen");
        mChannelMap.put(4, "egypt");
        mChannelMap.put(5, "south africa");
        mChannelMap.put(7, "malaysia");
        mChannelMap.put(8, "malaysia");
        mChannelMap.put(9, "iran");
        mChannelMap.put(10, "indonesia");
        mChannelMap.put(11, "chech");
        mChannelMap.put(12, "mexico");
        mChannelMap.put(13, "ruassia");
        mChannelMap.put(6, "algeria");
        mChannelMap.put(14, "nigeria");
        mChannelMap.put(15, "pakistan");
        mChannelMap.put(0, "qingdao_hk");
        mChannelMap.put(1, "huangdao_hk");
        mChannelMap.put(2, "guiyang_hk");
        mChannelMap.put(3, "jiangmen_hk");
        mChannelMap.put(16, "telebalt");
        mChannelMap.put(17, "belarus");
        mChannelMap.put(18, "angola");
        mChannelMap.put(19, "polytron");
        mChannelMap.put(20, "brandt");
        mChannelMap.put(21, "kaliningrad");
    }

    public static String getChannelName(int i) {
        HashMap<Integer, String> map = mChannelMap;
        return (map == null || map.size() <= 0) ? "" : mChannelMap.get(Integer.valueOf(i));
    }
}
