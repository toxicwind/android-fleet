package com.hmct.cloud.sdk.bean.wallpaper;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ThemeSearchReply extends ThemeBaseReply {
    public static final long serialVersionUID = -7179739781148447655L;
    public int count;
    public List<ResourceInfo> resource;

    public int getCount() {
        return this.count;
    }

    public List<ResourceInfo> getResource() {
        return this.resource;
    }

    public void setCount(int i) {
        this.count = i;
    }

    public void setResource(List<ResourceInfo> list) {
        this.resource = list;
    }
}
