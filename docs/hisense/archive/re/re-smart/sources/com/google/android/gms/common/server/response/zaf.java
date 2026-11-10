package com.google.android.gms.common.server.response;

import java.io.BufferedReader;

/* JADX INFO: loaded from: classes.dex */
public final class zaf implements FastParser.zaa<Boolean> {
    @Override // com.google.android.gms.common.server.response.FastParser.zaa
    public final /* synthetic */ Boolean zah(FastParser fastParser, BufferedReader bufferedReader) {
        return Boolean.valueOf(fastParser.zaa(bufferedReader, false));
    }
}
