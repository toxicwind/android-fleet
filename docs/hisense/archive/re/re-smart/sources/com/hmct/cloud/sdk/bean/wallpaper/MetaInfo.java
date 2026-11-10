package com.hmct.cloud.sdk.bean.wallpaper;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class MetaInfo implements Serializable {
    public static final long serialVersionUID = 3966638177685859996L;
    public String desc;
    public int languageId;
    public String name;
    public String picture;

    public String getDesc() {
        return this.desc;
    }

    public int getLanguageId() {
        return this.languageId;
    }

    public String getName() {
        return this.name;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public void setLanguageId(int i) {
        this.languageId = i;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPicture(String str) {
        this.picture = str;
    }
}
