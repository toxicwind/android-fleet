package com.hmct.cloud.sdk.bean.basp;

import com.hmct.cloud.sdk.bean.global.ReplyInfo;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AreaWeatherForecastInfoReply extends ReplyInfo {
    public static final long serialVersionUID = 1618116577735206340L;
    public String publishTime;
    public List<WeatherForecastInfo> weatherForecastList;

    public String getPublishTime() {
        return this.publishTime;
    }

    public List<WeatherForecastInfo> getWeatherForecastList() {
        return this.weatherForecastList;
    }

    public void setPublishTime(String str) {
        this.publishTime = str;
    }

    public void setWeatherForecastList(List<WeatherForecastInfo> list) {
        this.weatherForecastList = list;
    }
}
