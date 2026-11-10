package com.google.android.gms.common.server.response;

import java.io.BufferedReader;

/* JADX INFO: loaded from: classes.dex */
public final class zab implements FastParser.zaa<Integer> {
    @Override // com.google.android.gms.common.server.response.FastParser.zaa
    public final /* synthetic */ Integer zah(FastParser fastParser, BufferedReader bufferedReader) {
        return Integer.valueOf(fastParser.zad(bufferedReader));
    }
}
