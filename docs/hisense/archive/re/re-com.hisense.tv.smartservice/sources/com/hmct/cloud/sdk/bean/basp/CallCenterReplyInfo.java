package com.hmct.cloud.sdk.bean.basp;

import com.hmct.cloud.sdk.bean.global.ReplyInfo;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CallCenterReplyInfo extends ReplyInfo {
    public static final long serialVersionUID = -1896405538765137580L;
    public List<Callcenter> callcenterlist;

    public List<Callcenter> getCallcenterlist() {
        return this.callcenterlist;
    }

    public void setCallcenterlist(List<Callcenter> list) {
        this.callcenterlist = list;
    }
}
