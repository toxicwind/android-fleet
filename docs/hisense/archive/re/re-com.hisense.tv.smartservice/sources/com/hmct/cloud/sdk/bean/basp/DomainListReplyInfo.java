package com.hmct.cloud.sdk.bean.basp;

import com.hmct.cloud.sdk.bean.global.ReplyInfo;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DomainListReplyInfo extends ReplyInfo implements Serializable {
    public static final long serialVersionUID = -3116274298461641514L;
    public HashMap<String, List<String>> domainMap;

    public HashMap<String, List<String>> getDomainMap() {
        return this.domainMap;
    }

    public void setDomainMap(HashMap<String, List<String>> map) {
        this.domainMap = map;
    }
}
