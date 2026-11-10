package com.hmct.cloud.sdk.bean.basp;

import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class Callcenter implements Serializable {
    public static final long serialVersionUID = -1673979282182241036L;
    public List<String> altcontactlist;
    public String country;
    public String email;
    public String faxnum;
    public String hotline;
    public String market;

    public List<String> getAltcontactlist() {
        return this.altcontactlist;
    }

    public String getCountry() {
        return this.country;
    }

    public String getEmail() {
        return this.email;
    }

    public String getFaxnum() {
        return this.faxnum;
    }

    public String getHotline() {
        return this.hotline;
    }

    public String getMarket() {
        return this.market;
    }

    public void setAltcontactlist(List<String> list) {
        this.altcontactlist = list;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public void setFaxnum(String str) {
        this.faxnum = str;
    }

    public void setHotline(String str) {
        this.hotline = str;
    }

    public void setMarket(String str) {
        this.market = str;
    }
}
