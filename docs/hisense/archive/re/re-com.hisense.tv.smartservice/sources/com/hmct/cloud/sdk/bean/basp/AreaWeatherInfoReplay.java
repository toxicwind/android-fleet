package com.hmct.cloud.sdk.bean.basp;

import com.hmct.cloud.sdk.bean.global.ReplyInfo;

/* JADX INFO: loaded from: classes2.dex */
public class AreaWeatherInfoReplay extends ReplyInfo {
    public static final long serialVersionUID = -987464341300644198L;
    public int aqi;
    public String aqiPublishTime;
    public int humidity;
    public int pm25;
    public String publishTime;
    public int temperature;
    public String weatherCode;
    public String weatherName;
    public String windDirectionCode;
    public String windDirectionName;
    public int windForce;

    public int getAqi() {
        return this.aqi;
    }

    public String getAqiPublishTime() {
        return this.aqiPublishTime;
    }

    public int getHumidity() {
        return this.humidity;
    }

    public int getPm25() {
        return this.pm25;
    }

    public String getPublishTime() {
        return this.publishTime;
    }

    public int getTemperature() {
        return this.temperature;
    }

    public String getWeatherCode() {
        return this.weatherCode;
    }

    public String getWeatherName() {
        return this.weatherName;
    }

    public String getWindDirectionCode() {
        return this.windDirectionCode;
    }

    public String getWindDirectionName() {
        return this.windDirectionName;
    }

    public int getWindForce() {
        return this.windForce;
    }

    public void setAqi(int i) {
        this.aqi = i;
    }

    public void setAqiPublishTime(String str) {
        this.aqiPublishTime = str;
    }

    public void setHumidity(int i) {
        this.humidity = i;
    }

    public void setPm25(int i) {
        this.pm25 = i;
    }

    public void setPublishTime(String str) {
        this.publishTime = str;
    }

    public void setTemperature(int i) {
        this.temperature = i;
    }

    public void setWeatherCode(String str) {
        this.weatherCode = str;
    }

    public void setWeatherName(String str) {
        this.weatherName = str;
    }

    public void setWindDirectionCode(String str) {
        this.windDirectionCode = str;
    }

    public void setWindDirectionName(String str) {
        this.windDirectionName = str;
    }

    public void setWindForce(int i) {
        this.windForce = i;
    }
}
