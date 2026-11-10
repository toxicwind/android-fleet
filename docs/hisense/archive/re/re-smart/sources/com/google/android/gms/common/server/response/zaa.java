package com.google.android.gms.common.server.response;

import java.io.BufferedReader;

/* JADX INFO: loaded from: classes.dex */
public final class zaa implements FastParser.zaa<Long> {
    @Override // com.google.android.gms.common.server.response.FastParser.zaa
    public final /* synthetic */ Long zah(FastParser fastParser, BufferedReader bufferedReader) {
        return Long.valueOf(fastParser.zae(bufferedReader));
    }
}
