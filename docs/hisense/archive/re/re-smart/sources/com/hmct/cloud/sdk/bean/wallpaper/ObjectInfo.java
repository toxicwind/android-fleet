package com.hmct.cloud.sdk.bean.wallpaper;

import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ObjectInfo implements Serializable {
    public static final long serialVersionUID = -1377681242698998722L;
    public int displayOrder;
    public long id;
    public List<MetaInfo> meta;
    public int type;
    public String url;

    public int getDisplayOrder() {
        return this.displayOrder;
    }

    public long getId() {
        return this.id;
    }

    public List<MetaInfo> getMeta() {
        return this.meta;
    }

    public int getType() {
        return this.type;
    }

    public String getUrl() {
        return this.url;
    }

    public void setDisplayOrder(int i) {
        this.displayOrder = i;
    }

    public void setId(long j) {
        this.id = j;
    }

    public void setMeta(List<MetaInfo> list) {
        this.meta = list;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
