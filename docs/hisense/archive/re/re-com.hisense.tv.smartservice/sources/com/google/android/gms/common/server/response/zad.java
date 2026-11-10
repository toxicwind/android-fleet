package com.google.android.gms.common.server.response;

import java.io.BufferedReader;

/* JADX INFO: loaded from: classes.dex */
public final class zad implements FastParser.zaa<Float> {
    @Override // com.google.android.gms.common.server.response.FastParser.zaa
    public final /* synthetic */ Float zah(FastParser fastParser, BufferedReader bufferedReader) {
        return Float.valueOf(fastParser.zag(bufferedReader));
    }
}
